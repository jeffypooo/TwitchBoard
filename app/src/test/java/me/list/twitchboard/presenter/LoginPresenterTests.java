package me.list.twitchboard.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import me.list.twitchboard.TwitchBoard;
import me.list.twitchboard.storage.SharedPrefsWrapper;
import me.list.twitchboard.twitch.TwitchApi;
import me.list.twitchboard.twitch.model.Channel;
import me.list.twitchboard.view.LoginView;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by masterjefferson on 7/17/2016.
 */
public class LoginPresenterTests {

    @Mock
    LoginView mockView;
    @Mock
    SharedPrefsWrapper mockPrefsWrapper;
    @Mock
    TwitchApi mockApi;

    LoginPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new LoginPresenter(mockView, mockPrefsWrapper, mockApi);
    }

    @Test
    public void shouldImmediatelyAuthorizeIfSavedTokenIsValid() {
        when(mockPrefsWrapper.getString(TwitchBoard.KEY_AUTH_TOKEN))
                .thenReturn("testToken12345");
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                TwitchApi.ChannelCallback cb = (TwitchApi.ChannelCallback) invocation.getArguments()[0];
                cb.onGetChannel(new Channel()
                        .withStatus("test status")
                        .withGame("test game")
                );
                return null;
            }
        }).when(mockApi).getChannel(any(TwitchApi.ChannelCallback.class));
        presenter.verifyExistingToken();
        verify(mockView).authorized();
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
        verify(mockView).authorized();
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
