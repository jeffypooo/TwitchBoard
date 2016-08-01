package me.list.twitchboard.presenter;

import me.list.twitchboard.TwitchBoard;
import me.list.twitchboard.storage.SharedPrefsWrapper;
import me.list.twitchboard.twitch.TwitchApi;
import me.list.twitchboard.twitch.model.User;
import me.list.twitchboard.view.AccountView;
import me.list.twitchboard.view.ConfirmationHandler;

/**
 * Created by masterjefferson on 7/31/2016.
 */
public class AccountPresenter {


    private final SharedPrefsWrapper prefsWrapper;
    private final TwitchApi          twitchApi;
    private final AccountView        accountView;

    public AccountPresenter(SharedPrefsWrapper prefsWrapper,
                            TwitchApi twitchApi,
                            AccountView accountView) {
        this.prefsWrapper = prefsWrapper;
        this.twitchApi = twitchApi;
        this.accountView = accountView;
    }

    public void loadUserInfo() {
        twitchApi.getUser(new TwitchApi.GetCallback<User>() {
            @Override
            public void onReceived(User user) {
                updateUserInfoViews(user);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    public void onDeAuthorizeClicked() {
        accountView.confirmDeAuthorizationWithUser(new ConfirmationHandler() {
            @Override
            public void confirm() {
                prefsWrapper.remove(TwitchBoard.KEY_AUTH_TOKEN);
                accountView.restartApplication();
            }

            @Override
            public void deny() {
                //no-op
            }
        });
    }

    private void updateUserInfoViews(User user) {
        String name = user.getName();
        //the username should never be null...
        String displayName = user.getDisplayName() == null ? name : user.getDisplayName();
        accountView.setUserDisplayName(displayName);
        accountView.setUserName(name);
        accountView.setUserImageURL(user.getLogo());
    }

}
