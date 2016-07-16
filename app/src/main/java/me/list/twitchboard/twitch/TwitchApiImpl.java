package me.list.twitchboard.twitch;

import android.util.Log;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import me.list.twitchboard.twitch.model.Channel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by masterjefferson on 7/16/2016.
 */
public class TwitchApiImpl implements TwitchApi {

    private static final String API_URL_BASE = "https://api.twitch.tv/kraken";
    private static final String TAG = "TwitchApiImpl";
    private final OkHttpClient httpClient;
    private final String oauthToken;

    public TwitchApiImpl(String oauthToken, OkHttpClient httpClient) {
        this.oauthToken = oauthToken;
        this.httpClient = httpClient;
    }

    @Override
    public void getChannel(final ChannelCallback callback) {
        Request request = new Request.Builder()
                .url(API_URL_BASE + "/channel")
                .addHeader("Accept", "application/vnd.twitchtv.v3+json")
                .addHeader("Authorization", "OAuth " + this.oauthToken)
                .build();
        this.httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure() called with: " + "call = [" + call + "], e = [" + e + "]");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Moshi moshi = new Moshi.Builder().build();
                JsonAdapter<Channel> channelAdapter = moshi.adapter(Channel.class);
                Channel channel = channelAdapter.fromJson(response.body().source());
                callback.onGetChannel(channel);
            }
        });
    }

    @Override
    public void updateChannel(String status, String game, ChannelCallback callback) {

    }
}
