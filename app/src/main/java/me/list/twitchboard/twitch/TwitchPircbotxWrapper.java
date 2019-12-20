package me.list.twitchboard.twitch;

import android.support.annotation.Nullable;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.ListenerAdapter;

import java.io.IOException;

/**
 * Created by masterjefferson on 8/2/2016.
 */
public class TwitchPircbotxWrapper extends ListenerAdapter implements TwitchIrcClient {

    private static final String TWITCH_IRC_URL = "irc.chat.twitch.tv";

    private final PircBotFactory botFactory;
    private final String         nick;
    private final String         username;
    private final String         oauthToken;
    @Nullable
    private       IrcListener    listener;
    private       PircBotX       ircBot;

    public TwitchPircbotxWrapper(PircBotFactory botFactory,
                                 String nick,
                                 String username,
                                 String oauthToken) {
        this.botFactory = botFactory;
        this.nick = nick;
        this.username = username;
        this.oauthToken = oauthToken;
    }

    @Override
    public void connectToTwitchChat() {
        ircBot = botFactory.makeBot(new Configuration.Builder()
                .addServer(TWITCH_IRC_URL)
                .setName(nick)
                .setLogin(username)
                .setServerPassword(oauthToken)
                .buildConfiguration());
        try {
            ircBot.startBot();
        } catch (IOException | IrcException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnectFromTwitch() {

    }

    @Override
    public void sendChannelMessage(String message) {

    }

    @Override
    public void setIrcListener(IrcListener listener) {
        this.listener = listener;
    }

    private void notifyError(Throwable e) {
        if (listener != null) {
            listener.onError(e);
        }
    }
}
