package me.list.twitchboard.presenter;

import org.junit.Before;
import org.junit.Test;

import me.list.twitchboard.TwitchBoard;
import me.list.twitchboard.storage.SharedPrefsWrapper;
import me.list.twitchboard.view.LoginView;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by masterjefferson on 7/17/2016.
 */
public class LoginPresenterTests {

    LoginView mockView;
    SharedPrefsWrapper mockPrefsWrapper;
    LoginPresenter presenter;

    @Before
    public void setup() {
        mockView = mock(LoginView.class);
        mockPrefsWrapper = mock(SharedPrefsWrapper.class);
        presenter = new LoginPresenter(mockView, mockPrefsWrapper);
    }

    @Test
    public void shouldShowNotificationWhenAuthorizeClicked() {
        presenter.authorizeClicked();
        String expectedMessage = "Connecting to Twitch...";
        verify(mockView).showNotification(expectedMessage);
    }

    @Test
    public void shouldOpenAuthorizationPageWhenAuthorizeClicked() {
        presenter.authorizeClicked();
        verify(mockView).showURL(any(String.class));
    }

    @Test
    public void shouldMoveToNextActivityIfUrlHasToken() {
        String testURL = getTestAuthRedirectURL("testtoken1235");
        presenter.onPageLoadStarted(testURL);
        verify(mockView).finish();
    }

    @Test
    public void shouldPersistTokenIfURLHasToken() {
        String expectedToken = "testtoken1235";
        String testURL = getTestAuthRedirectURL(expectedToken);
        presenter.onPageLoadStarted(testURL);
        verify(mockPrefsWrapper).putString(TwitchBoard.KEY_AUTH_TOKEN, expectedToken);

    }

    private String getTestAuthRedirectURL(String token) {
        return String.format(
                "http://localhost/#access_token=%s&scope=",
                token
        );
    }

}
