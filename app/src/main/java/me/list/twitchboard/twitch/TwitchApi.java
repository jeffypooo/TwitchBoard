package me.list.twitchboard.twitch;

import android.support.annotation.Nullable;

import me.list.twitchboard.twitch.model.Channel;
import me.list.twitchboard.twitch.model.Stream;

/**
 * Created by masterjefferson on 7/16/2016.
 */
public interface TwitchApi {

    interface ChannelCallback {
        void onGetChannel(Channel channel);
    }

    interface StreamCallback {
        void onGetStream(@Nullable Stream stream);
    }

    void getChannel(ChannelCallback callback);

    void getStream(StreamCallback callback);

    void updateChannel(String status, String game, ChannelCallback callback);

    void setOAuthToken(String token);

    void connectToChat();

}
