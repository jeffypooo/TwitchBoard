package me.list.twitchboard.twitch;

import android.util.Log;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import me.list.twitchboard.twitch.model.Channel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by masterjefferson on 7/16/2016.
 */
public class TwitchApiImpl implements TwitchApi {

    private static final String API_URL_BASE = "https://api.twitch.tv/kraken";
    private static final String API_URL_CHANNEL_READ = API_URL_BASE + "/channel";
    private static final String HEADER_ACCEPT = "Accept";
    private static final String HEADER_AUTH = "Authorization";
    private static final String MEDIA_TWITCH_JSON_V3 = "application/vnd.twitchtv.v3+json";
    private static final String MEDIA_JSON = "application/json";
    private static final String TAG = "TwitchApiImpl";
    private final OkHttpClient httpClient;
    private final String oauthToken;

    public TwitchApiImpl(String oauthToken, OkHttpClient httpClient) {
        this.oauthToken = oauthToken;
        this.httpClient = httpClient;
    }

    @Override
    public void getChannel(final ChannelCallback callback) {
        Request request = getStandardAuthRequestBuilder(API_URL_CHANNEL_READ).build();
        this.httpClient.newCall(request).enqueue(new ChannelRequestCallback(callback));
    }

    @Override
    public void updateChannel(final String status, final String game, final ChannelCallback callback) {
        getChannel(new ChannelCallback() {
            @Override
            public void onGetChannel(Channel channel) {
                //TODO is there a better way to do this?
                String json = getChannelPutJson(status, game);
                Request request = getStandardAuthRequestBuilder(channel.getLinks().getSelf())
                        .put(RequestBody.create(
                                MediaType.parse("application/json"),
                                json
                        ))
                        .build();
                httpClient.newCall(request).enqueue(new ChannelRequestCallback(callback));
            }
        });
    }

    private String getAuthArg() {
        return "OAuth " + oauthToken;
    }

    private String getChannelPutJson(String status, String game) {
        return String.format(
                "{\"channel\":{\"status\": \"%s\",\"game\": \"%s\"}}",
                status,
                game
        );
    }

    private Request.Builder getStandardAuthRequestBuilder(String url) {
        return new Request.Builder()
                .url(url)
                .addHeader(HEADER_ACCEPT, MEDIA_TWITCH_JSON_V3)
                .addHeader(HEADER_AUTH, getAuthArg());
    }

    private static class ChannelRequestCallback implements Callback {

        private final ChannelCallback channelCallback;

        ChannelRequestCallback(ChannelCallback channelCallback) {
            this.channelCallback = channelCallback;
        }

        @Override
        public void onFailure(Call call, IOException e) {
            Log.d(TAG, "onFailure() called with: " + "call = [" + call + "], e = [" + e + "]");
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                Moshi moshi = new Moshi.Builder().build();
                JsonAdapter<Channel> channelAdapter = moshi.adapter(Channel.class);
                String body = response.body().string();
                Channel channel = channelAdapter.fromJson(body);
                this.channelCallback.onGetChannel(channel);
            }
        }
    }

}
