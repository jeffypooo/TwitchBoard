package me.list.twitchboard.twitch;

/**
 * Created by masterjefferson on 7/23/2016.
 */
public interface TwitchIrcClient {
    void connectToTwitchChat();
    void disconnectFromTwitch();
    void sendChannelMessage(String message);
    void setIrcListener(IrcListener listener);

}
