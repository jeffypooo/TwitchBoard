package me.list.twitchboard.presenter;

import me.list.twitchboard.twitch.IrcListener;
import me.list.twitchboard.twitch.TwitchIrcClient;
import me.list.twitchboard.view.ChatView;

/**
 * Created by masterjefferson on 7/23/2016.
 */
public class ChatPresenter implements IrcListener {

    private final ChatView chatView;
    private final TwitchIrcClient ircClient;

    public ChatPresenter(ChatView chatView, TwitchIrcClient ircClient) {
        this.chatView = chatView;
        this.ircClient = ircClient;
        this.ircClient.setIrcListener(this);
    }

    public void connectToChat() {
        ircClient.connectToTwitchChat();
    }

    public void onSendClick(String message) {
        ircClient.sendChannelMessage(message);
    }

    @Override
    public void onConnected() {
        chatView.showNotification("Chat connected.");
    }

    @Override
    public void onDisconnected() {
        chatView.showNotification("Chat disconnected.");
    }

    @Override
    public void onError(Throwable e) {
        chatView.showNotification("An error occurred with chat.");
    }
}
