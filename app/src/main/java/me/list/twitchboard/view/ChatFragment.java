package me.list.twitchboard.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.list.twitchboard.R;
import me.list.twitchboard.TwitchBoard;
import me.list.twitchboard.presenter.ChatPresenter;
import me.list.twitchboard.twitch.IrcListener;
import me.list.twitchboard.twitch.TwitchApi;
import me.list.twitchboard.twitch.TwitchApiImpl;
import me.list.twitchboard.twitch.TwitchIrcClient;
import me.list.twitchboard.twitch.model.Channel;
import okhttp3.OkHttpClient;

/**
 * Created by masterjefferson on 7/23/2016.
 */
public class ChatFragment extends Fragment implements ChatView {
    @BindView(R.id.ChatView_RootLayout)   LinearLayout rootLayout;
    @BindView(R.id.ChatView_MessageView)  ListView     messageView;
    @BindView(R.id.ChatView_MessageEntry) EditText     messageField;
    @BindView(R.id.ChatView_SendButton)   Button       sendButton;

    private Unbinder      unbinder;
    private ChatPresenter presenter;

    //region Fragment Lifecycle

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.chat_view, container, false);
        unbinder = ButterKnife.bind(this, root);
        initPresenter();
        return root;
    }

    //endregion

    //region View Listeners

    @OnClick(R.id.ChatView_SendButton)
    void onSendButtonClick() {
        //TODO send the message
    }

    //endregion

    //region ChatView Implementation

    @Override
    public void showNotification(String msg) {
        Snackbar.make(rootLayout, msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showSentMessage(String msg) {

    }

    //endregion

    //region Initialization Helpers

    private void onPresenterInitialized() {
        presenter.connectToChat();
    }

    //TODO make this not complete shit
    private void initPresenter() {
        SharedPreferences prefs = getActivity()
                .getSharedPreferences(TwitchBoard.PREFS_AUTH, Context.MODE_PRIVATE);
        final String token = prefs.getString(TwitchBoard.KEY_AUTH_TOKEN, "invalid");
        TwitchApi api = new TwitchApiImpl(token, new OkHttpClient());
        api.getChannel(new TwitchApi.ChannelCallback() {
            @Override
            public void onGetChannel(Channel channel) {
                presenter = new ChatPresenter(
                        ChatFragment.this,
                        new IrcClientStub());
                onPresenterInitialized();
            }
        });
    }

    private class IrcClientStub implements TwitchIrcClient {

        private static final String TAG = "IrcClientStub";

        @Override
        public void connectToTwitchChat() {
            Log.d(TAG, "connectToTwitchChat() called with: " + "");
        }

        @Override
        public void disconnectFromTwitch() {
            Log.d(TAG, "disconnectFromTwitch() called with: " + "");
        }

        @Override
        public void sendChannelMessage(String message) {
            Log.d(TAG, "sendChannelMessage() called with: " + "message = [" + message + "]");
        }

        @Override
        public void setIrcListener(IrcListener listener) {
            Log.d(TAG, "setIrcListener() called with: " + "listener = [" + listener + "]");
        }
    }

    //endregion

}
