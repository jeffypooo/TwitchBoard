package me.list.twitchboard.twitch;

/**
 * Created by masterjefferson on 7/23/2016.
 */
public interface IrcListener {
    void onConnected();

    void onDisconnected();

    void onError(Throwable e);
}
