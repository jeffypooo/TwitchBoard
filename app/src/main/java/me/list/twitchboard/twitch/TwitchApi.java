package me.list.twitchboard.twitch;

import me.list.twitchboard.twitch.model.Channel;

/**
 * Created by masterjefferson on 7/16/2016.
 */
public interface TwitchApi {

    interface ChannelCallback {
        void onGetChannel(Channel channel);
    }

    void getChannel(ChannelCallback callback);
    void updateChannel(String status, String game, ChannelCallback callback);

}
