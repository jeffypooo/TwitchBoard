package me.list.twitchboard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.list.twitchboard.twitch.UrlFactory;
import me.list.twitchboard.util.logging.LOG;

import static me.list.twitchboard.twitch.AuthScope.CHANNEL_EDITOR;
import static me.list.twitchboard.twitch.AuthScope.CHANNEL_FEED_EDIT;
import static me.list.twitchboard.twitch.AuthScope.CHANNEL_READ;
import static me.list.twitchboard.twitch.AuthScope.CHANNEL_SUBSCRIPTIONS;
import static me.list.twitchboard.twitch.AuthScope.CHAT_LOGIN;
import static me.list.twitchboard.twitch.AuthScope.USER_BLOCKS_EDIT;
import static me.list.twitchboard.twitch.AuthScope.USER_FOLLOWS_EDIT;
import static me.list.twitchboard.twitch.AuthScope.USER_READ;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    @BindView(R.id.Login_Button_Authorize)
    Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        if (loadAuthToken() != null) {
            loginFinished();
        }
    }


    @OnClick(R.id.Login_Button_Authorize)
    public void didClickAuthorize(View view) {
        Intent intent = new Intent(this, AuthorizeActivity.class);
        intent.putExtra(AuthorizeActivity.EXTRA_AUTH_URL, getDefaultAuthURL());
        startActivityForResult(intent, AuthorizeActivity.AUTHORIZE_USER_REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AuthorizeActivity.AUTHORIZE_USER_REQ) {
            if (resultCode == AuthorizeActivity.AUTHORIZE_SUCCESS) {
                String authToken = data.getStringExtra(AuthorizeActivity.EXTRA_AUTH_TOKEN);
                persistAuthToken(authToken);
                loginFinished();
            } else {
                LOG.w(TAG, "AUTH FAILED");
            }
        }
    }

    //TODO this code may not be what we want for auth persistence...

    private void loginFinished() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }

    @Nullable
    private String loadAuthToken() {
        SharedPreferences prefs = getAuthPrefs();
        return prefs.getString(TwitchBoard.KEY_AUTH_TOKEN, null);
    }

    @SuppressLint("CommitPrefEdits")
    private void persistAuthToken(String token) {
        LOG.d(TAG, "persisting auth token: %s", token);
        SharedPreferences.Editor prefsEditor = getAuthPrefs().edit();
        prefsEditor.putString(TwitchBoard.KEY_AUTH_TOKEN, token);
        prefsEditor.commit();
    }

    private SharedPreferences getAuthPrefs() {
        return getSharedPreferences(TwitchBoard.PREFS_AUTH, MODE_PRIVATE);
    }

    private String getDefaultAuthURL() {
        return UrlFactory.userAuthURL(
                TwitchBoard.TWITCH_CLIENT_ID,
                TwitchBoard.TWITCH_CLIENT_REDIRECT_URI,
                USER_READ.raw,
                USER_BLOCKS_EDIT.raw,
                USER_FOLLOWS_EDIT.raw,
                CHANNEL_READ.raw,
                CHANNEL_EDITOR.raw,
                CHANNEL_SUBSCRIPTIONS.raw,
                CHAT_LOGIN.raw,
                CHANNEL_FEED_EDIT.raw);
    }
}
