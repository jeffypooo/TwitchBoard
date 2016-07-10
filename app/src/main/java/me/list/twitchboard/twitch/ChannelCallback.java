package me.list.twitchboard.twitch;

import me.list.twitchboard.twitch.model.Channel;

/**
 * Created by masterjefferson on 7/10/2016.
 */
public interface ChannelCallback {

    void didGetChannel(Channel channel);
}
