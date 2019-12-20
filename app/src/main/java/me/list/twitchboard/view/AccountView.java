package me.list.twitchboard.view;

/**
 * Created by masterjefferson on 7/31/2016.
 */
public interface AccountView {

    void setUserDisplayName(String displayName);
    void setUserName(String userName);
    void setUserImageURL(String url);
    void confirmDeAuthorizationWithUser(ConfirmationHandler confirmationHandler);
    void restartApplication();
}
