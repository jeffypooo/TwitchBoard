package me.list.twitchboard.twitch;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import me.list.twitchboard.twitch.model.BaseChannel;
import me.list.twitchboard.twitch.model.BaseUser;
import me.list.twitchboard.twitch.model.Channel;
import me.list.twitchboard.twitch.model.User;
import me.list.twitchboard.util.logging.LOG;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by masterjefferson on 7/9/2016.
 */
public class TwitchApi {

    public static final String APP_NAME = "Twitch_Board";
    public static final String CLIENT_ID = "l4g05we5eikm1285ffehr41z29q6d0e";
    public static final String CLIENT_REDIRECT_URL = "http://localhost";
    private static final String TAG = TwitchApi.class.getSimpleName();

    private static final String HEADER_ACCEPT = "Accept";
    private static final String MEDIA_TYPE_TWITCH_JSON = "application/vnd.twitchtv.v3+json";
    private static final String HEADER_AUTH = "Authorization";

    private final String authToken;
    private final OkHttpClient okHttpClient;

    public TwitchApi(String authToken) {
        this.authToken = authToken;
        this.okHttpClient = new OkHttpClient();
    }

    public void getUser(final UserCallback callback) {
        final Request request = new Request.Builder()
                .addHeader(HEADER_ACCEPT, MEDIA_TYPE_TWITCH_JSON)
                .addHeader(HEADER_AUTH, getAuthHeaderArg(this.authToken))
                .url("https://api.twitch.tv/kraken/user")
                .build();
        this.okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LOG.e(TAG, e, "call failed!");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                try {
                    JSONObject json = new JSONObject(body);
                    String name = json.getString(BaseUser.KEY_NAME);
                    String displayName = json.getString(BaseUser.KEY_DISPLAY_NAME);
                    String bio = json.getString(BaseUser.KEY_BIO);
                    callback.didGetUser(new BaseUser(name, displayName, bio));
                } catch (JSONException e) {
                    LOG.e(TAG, e, "json parse failed! source: %s", body);
                }
            }
        });
    }

    public void getChannel(final ChannelCallback callback, User user) {
        final Request request = new Request.Builder()
                .addHeader(HEADER_ACCEPT, MEDIA_TYPE_TWITCH_JSON)
                .url("https://api.twitch.tv/kraken/channels/" + user.name())
                .build();
        this.okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LOG.e(TAG, e, "call failed");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                try {
                    JSONObject json = new JSONObject(body);
                    String status = json.getString(BaseChannel.KEY_STATUS);
                    String game = json.getString(BaseChannel.KEY_GAME);
                    callback.didGetChannel(new BaseChannel(status, game));
                } catch (JSONException e) {
                    LOG.e(TAG, e, "json parse failed! source: %s", body);
                }
            }
        });
    }

    public void updateChannel(Channel channel) {
        final Request request = new Request.Builder()
                .addHeader(HEADER_ACCEPT, MEDIA_TYPE_TWITCH_JSON)
                .addHeader(HEADER_AUTH, getAuthHeaderArg(this.authToken))
                .build();
        JSONObject json = new JSONObject();
}

    private static String getAuthHeaderArg(String token) {
        return String.format("OAuth %s", token);
    }
}
