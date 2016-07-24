package me.list.twitchboard.twitch;

import android.support.annotation.Nullable;
import android.util.Log;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import me.list.twitchboard.twitch.model.Channel;
import me.list.twitchboard.twitch.model.Stream;
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
    private static final String API_CHANNEL_STREAM_BASE = API_URL_BASE + "/streams";
    private static final String HEADER_ACCEPT = "Accept";
    private static final String HEADER_AUTH = "Authorization";
    private static final String MEDIA_TWITCH_JSON_V3 = "application/vnd.twitchtv.v3+json";
    private static final String MEDIA_JSON = "application/json";
    private static final String TAG = "TwitchApiImpl";
    private final OkHttpClient httpClient;
    @Nullable
    private String oauthToken;

    public TwitchApiImpl(@Nullable String oauthToken, OkHttpClient httpClient) {
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

    @Override
    public void getStream(StreamCallback callback) {
        
    }

    @Override
    public void setOAuthToken(String token) {

    }

    @Override
    public void getChannel2(final MyCallback<Channel> channelCallback) {
        Request request = getStandardAuthRequestBuilder(API_URL_CHANNEL_READ).build();
        this.httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                channelCallback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {

                } else {
                    // work out what went wrong
                    switch (response.code()) {
                        case 401:
                            channelCallback.onFailure(new RuntimeException("unauthorized"));
                            break;
                        default:
                            channelCallback.onFailure(new RuntimeException("unknown"));
                    }
                }
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

    private static class StreamRequestCallback implements Callback {

        private final StreamCallback streamCallback;

        StreamRequestCallback(StreamCallback streamCallback) {
            this.streamCallback = streamCallback;
        }

        @Override
        public void onFailure(Call call, IOException e) {
            Log.d(TAG, "onFailure() called with: " + "call = [" + call + "], e = [" + e + "]");
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                Moshi moshi = new Moshi.Builder().build();
                JsonAdapter<Stream> streamAdapter = moshi.adapter(Stream.class);
                String body = response.body().string();
                Stream stream = streamAdapter.fromJson(body);
                streamCallback.onGetStream(stream);
            }
        }
    }

}
