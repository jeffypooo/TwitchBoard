package me.list.twitchboard.twitch;

import android.support.annotation.Nullable;
import android.util.Log;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import me.list.twitchboard.twitch.model.Channel;
import me.list.twitchboard.twitch.model.StreamContainer;
import me.list.twitchboard.twitch.model.User;
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
    //log tag
    private static final String TAG = "TwitchApiImpl";

    private static final String API_URL_BASE              = "https://api.twitch.tv/kraken";
    private static final String API_OAUTH_URL_CHANNEL     = API_URL_BASE + "/channel";
    private static final String API_OAUTH_URL_USER        = API_URL_BASE + "/user";
    private static final String API_OAUTH_URL_STREAM_BASE = API_URL_BASE + "/streams";
    private static final String HEADER_ACCEPT             = "Accept";
    private static final String HEADER_AUTH               = "Authorization";
    private static final String MEDIATYPE_TWITCH_JSON_V3  = "application/vnd.twitchtv.v3+json";
    private static final String MEDIATYPE_JSON            = "application/json";

    private final     OkHttpClient httpClient;
    private final     Moshi        moshi;
    private @Nullable String       oauthToken;

    public TwitchApiImpl(@Nullable String oauthToken, OkHttpClient httpClient) {
        this.oauthToken = oauthToken;
        this.httpClient = httpClient;
        this.moshi = new Moshi.Builder().build(); //FIXME should probably inject this...
    }

    @Override
    public void getChannel(final ChannelCallback callback) {
        Request request = getStandardAuthRequestBuilder(API_OAUTH_URL_CHANNEL).build();
        this.httpClient.newCall(request).enqueue(new ChannelRequestCallback(callback));
    }

    @Override
    public void updateChannel(final String status,
                              final String game,
                              final ChannelCallback callback) {
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
    public void getStream(final StreamCallback callback) {
        getChannel(new ChannelCallback() {
            @Override
            public void onGetChannel(Channel channel) {
                Request.Builder streamReq = getStandardAuthRequestBuilder(
                        API_OAUTH_URL_STREAM_BASE + "/" + channel.getName());
                httpClient.newCall(streamReq.build()).enqueue(new StreamRequestCallback(callback));
            }
        });
    }

    @Override
    public void setOAuthToken(String token) {
        this.oauthToken = token;
    }

    @Override
    public void getChannel2(final GetCallback<Channel> channelCallback) {
        Request request = getStandardAuthRequestBuilder(API_OAUTH_URL_CHANNEL).build();
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

    @Override
    public void getUser(final GetCallback<User> userCallback) {
        Request request = getStandardAuthRequestBuilder(API_OAUTH_URL_USER).build();
        httpClient.newCall(request).enqueue(
                new MoshiAdapterCallback<>(userCallback, moshi.adapter(User.class))
        );
    }

    @Override
    public void connectToChat() {

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
                .addHeader(HEADER_ACCEPT, MEDIATYPE_TWITCH_JSON_V3)
                .addHeader(HEADER_AUTH, getAuthArg());
    }

    private static class MoshiAdapterCallback<T> implements Callback {

        private final GetCallback<T> getCallback;
        private final JsonAdapter<T> moshiAdapter;

        MoshiAdapterCallback(GetCallback<T> getCallback, JsonAdapter<T> moshiAdapter) {
            this.getCallback = getCallback;
            this.moshiAdapter = moshiAdapter;
        }

        @Override
        public void onFailure(Call call, IOException e) {
            getCallback.onFailure(e);
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                String body = response.body().string();
                T obj = moshiAdapter.fromJson(body);
                getCallback.onReceived(obj);
            }
        }
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
                JsonAdapter<StreamContainer> streamAdapter = moshi.adapter(StreamContainer.class);
                String body = response.body().string();
                StreamContainer container = streamAdapter.fromJson(body);
                streamCallback.onGetStream(container.getStream());
            }
        }
    }

}
