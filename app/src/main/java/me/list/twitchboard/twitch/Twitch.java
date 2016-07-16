package me.list.twitchboard.twitch;

import me.list.twitchboard.twitch.model.Channel;
import me.list.twitchboard.twitch.model.User;

/**
 * Created by masterjefferson on 7/16/2016.
 */
public interface Twitch {
    String APP_NAME = "Twitch_Board";
    String CLIENT_ID = "l4g05we5eikm1285ffehr41z29q6d0e";
    String CLIENT_REDIRECT_URL = "http://localhost";

    void getUser(UserCallback callback);

    void getChannel(ChannelCallback callback, User user);

    void updateChannel(Channel channel, User user);


}
