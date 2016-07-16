package me.list.twitchboard.twitch;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

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
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import retrofit2.Retrofit;

/**
 * Created by masterjefferson on 7/9/2016.
 */
public class TwitchApi implements Twitch {

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

    @Override
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

    @Override
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

    @Override
    public void updateChannel(Channel channel, User user) {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<BaseChannel> adapter = moshi.adapter(BaseChannel.class);
        String json = adapter.toJson((BaseChannel) channel); //FIXME naughty cast
        final Request request = new Request.Builder()
                .url("https://api.twitch.tv/kraken/channels/" + user.name())
                .addHeader(HEADER_ACCEPT, MEDIA_TYPE_TWITCH_JSON)
                .addHeader(HEADER_AUTH, getAuthHeaderArg(this.authToken))
                .put(RequestBody.create(
                        MediaType.parse(MEDIA_TYPE_TWITCH_JSON),
                        json
                ))
                .build();
        this.okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LOG.e(TAG, "call failed! %s", call);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LOG.d(TAG, "twitch: %s", response.body().string());
            }
        });
    }

    private static String getAuthHeaderArg(String token) {
        return String.format("OAuth %s", token);
    }
}
