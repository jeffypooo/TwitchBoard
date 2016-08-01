package me.list.twitchboard.presenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.list.twitchboard.TwitchBoard;
import me.list.twitchboard.storage.SharedPrefsWrapper;
import me.list.twitchboard.twitch.TwitchApi;
import me.list.twitchboard.twitch.UrlFactory;
import me.list.twitchboard.twitch.model.Channel;
import me.list.twitchboard.view.LoginView;

import static me.list.twitchboard.twitch.AuthScope.CHANNEL_EDITOR;
import static me.list.twitchboard.twitch.AuthScope.CHANNEL_FEED_EDIT;
import static me.list.twitchboard.twitch.AuthScope.CHANNEL_READ;
import static me.list.twitchboard.twitch.AuthScope.CHANNEL_SUBSCRIPTIONS;
import static me.list.twitchboard.twitch.AuthScope.CHAT_LOGIN;
import static me.list.twitchboard.twitch.AuthScope.USER_BLOCKS_EDIT;
import static me.list.twitchboard.twitch.AuthScope.USER_FOLLOWS_EDIT;
import static me.list.twitchboard.twitch.AuthScope.USER_READ;

/**
 * Created by masterjefferson on 7/17/2016.
 */
public class LoginPresenter {

    private final LoginView          loginView;
    private final SharedPrefsWrapper prefsWrapper;
    private final TwitchApi          twitchApi;

    public LoginPresenter(LoginView loginView,
                          SharedPrefsWrapper prefsWrapper,
                          TwitchApi twitchApi) {
        this.loginView = loginView;
        this.prefsWrapper = prefsWrapper;
        this.twitchApi = twitchApi;
    }

    public void verifyExistingToken() {
        String token = prefsWrapper.getString(TwitchBoard.KEY_AUTH_TOKEN);
        twitchApi.setOAuthToken(token);
        twitchApi.getChannel(new TwitchApi.ChannelCallback() {
            @Override
            public void onGetChannel(Channel channel) {
                loginView.authorized();
            }
        });
    }

    public void authorizeClicked() {
        loginView.showNotification("Connecting to Twitch...");
        String authURL = UrlFactory.userAuthURL(
                TwitchBoard.TWITCH_CLIENT_ID,
                TwitchBoard.TWITCH_CLIENT_REDIRECT_URI,
                USER_READ.raw,
                USER_BLOCKS_EDIT.raw,
                USER_FOLLOWS_EDIT.raw,
                CHANNEL_READ.raw,
                CHANNEL_EDITOR.raw,
                CHANNEL_SUBSCRIPTIONS.raw,
                CHAT_LOGIN.raw,
                CHANNEL_FEED_EDIT.raw
        );
        loginView.showURL(authURL);
    }

    public void onPageLoadStarted(String url) {
        if (url.startsWith(TwitchBoard.TWITCH_CLIENT_REDIRECT_URI)) {
            Pattern pattern = Pattern.compile("#access_token=(.*)&");
            Matcher matcher = pattern.matcher(url);
            if (matcher.find()) {
                prefsWrapper.putString(TwitchBoard.KEY_AUTH_TOKEN, matcher.group(1));
                loginView.authorized();
            }
        }
    }

}
