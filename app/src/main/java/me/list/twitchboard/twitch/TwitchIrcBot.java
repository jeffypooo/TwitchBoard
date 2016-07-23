package me.list.twitchboard.twitch;

import android.support.annotation.Nullable;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;

import java.io.IOException;

/**
 * Created by masterjefferson on 7/23/2016.
 */
public class TwitchIrcBot extends PircBot implements TwitchIrcClient {

    private static final String TWITCH_HOST = "irc.chat.twitch.tv";
    private static final int TWITCH_IRC_PORT = 6667;

    private final String username, oAuthToken;
    @Nullable
    private IrcListener listener;

    public TwitchIrcBot(String username, String oAuthToken) {
        this.username = username;
        this.oAuthToken = oAuthToken;
        setVerbose(true);
        setName(username);
    }

    //region TwitchIrcClient implementation

    @Override
    public void connectToTwitchChat() {
        try {
            connect(TWITCH_HOST, TWITCH_IRC_PORT, "oauth:" + oAuthToken);
        } catch (IOException | IrcException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnectFromTwitch() {
        disconnect();
    }

    @Override
    public void sendChannelMessage(String message) {
        sendMessage("#" + username, message);
    }

    @Override
    public void setIrcListener(@Nullable IrcListener listener) {
        this.listener = listener;
    }

    //endregion

    //region PircBot Overrides

    @Override
    protected void onConnect() {
        joinChannel("#masterjefferson");
    }

    @Override
    protected void onJoin(String channel, String sender, String login, String hostname) {
        super.onJoin(channel, sender, login, hostname);
        if (listener != null) {
            listener.onConnected();
        }
    }

    @Override
    protected void onDisconnect() {
        if (listener != null) {
            listener.onDisconnected();
        }
    }

    //endregion

    @Nullable
    public IrcListener getListener() {
        return listener;
    }
}
