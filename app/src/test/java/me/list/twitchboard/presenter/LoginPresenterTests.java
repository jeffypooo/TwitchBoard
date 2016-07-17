package me.list.twitchboard.presenter;

import org.junit.Before;
import org.junit.Test;

import me.list.twitchboard.view.LoginView;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by masterjefferson on 7/17/2016.
 */
public class LoginPresenterTests {

    LoginView mockView;
    LoginPresenter presenter;

    @Before
    public void setup() {
        mockView = mock(LoginView.class);
        presenter = new LoginPresenter(mockView);
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

    private String getTestAuthRedirectURL(String token) {
        return String.format(
                "https://localhost/#access_token=%s&scope=",
                token
        );
    }

}
