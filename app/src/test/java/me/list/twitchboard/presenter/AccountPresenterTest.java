package me.list.twitchboard.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import me.list.twitchboard.TwitchBoard;
import me.list.twitchboard.storage.SharedPrefsWrapper;
import me.list.twitchboard.twitch.TwitchApi;
import me.list.twitchboard.twitch.model.User;
import me.list.twitchboard.view.AccountView;
import me.list.twitchboard.view.ConfirmationHandler;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by masterjefferson on 7/31/2016.
 */
@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class AccountPresenterTest {

    @Mock SharedPrefsWrapper mockPrefsWrapper;
    @Mock TwitchApi          mockTwitchApi;
    @Mock AccountView        mockAccountView;

    AccountPresenter presenter;

    @Before
    public void setup() {
        presenter = new AccountPresenter(mockPrefsWrapper, mockTwitchApi, mockAccountView);
    }

    @Test
    public void shouldSetDisplayName() {
        final String testDisplayName = "John Doe";
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                TwitchApi.GetCallback<User> cb = (TwitchApi.GetCallback<User>) invocation
                        .getArguments()[0];
                cb.onReceived(new User()
                        .withDisplayName(testDisplayName)
                );
                return null;
            }
        }).when(mockTwitchApi).getUser(any(TwitchApi.GetCallback.class));
        presenter.loadUserInfo();
        verify(mockAccountView).setUserDisplayName(testDisplayName);
    }

    @Test
    public void shouldSetUserName() {
        final String testName = "username";
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                TwitchApi.GetCallback<User> cb = (TwitchApi.GetCallback<User>) invocation
                        .getArguments()[0];
                cb.onReceived(new User()
                        .withName(testName)
                );
                return null;
            }
        }).when(mockTwitchApi).getUser(any(TwitchApi.GetCallback.class));
        presenter.loadUserInfo();
        verify(mockAccountView).setUserName(testName);
    }

    @Test
    public void shouldSetUserImageURL() {
        final String testURL = "www.google.com";
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                TwitchApi.GetCallback<User> cb = (TwitchApi.GetCallback<User>) invocation
                        .getArguments()[0];
                cb.onReceived(new User()
                        .withLogo(testURL)
                );
                return null;
            }
        }).when(mockTwitchApi).getUser(any(TwitchApi.GetCallback.class));
        presenter.loadUserInfo();
        verify(mockAccountView).setUserImageURL(testURL);
    }

    @Test
    public void shouldShowConfirmationWhenDeAuthorizeClicked() {
        presenter.onDeAuthorizeClicked();
        verify(mockAccountView).confirmDeAuthorizationWithUser(any(ConfirmationHandler.class));
    }

    @Test
    public void shouldRestartApplicationWhenDeAuthConfirmed() {
        ConfirmationHandler handler = simulateDeAuthAndCaptureHandler();
        handler.confirm();
        verify(mockAccountView).restartApplication();
    }

    @Test
    public void shouldRemoveOAuthTokenWhenDeAuthConfirmed() {
        ConfirmationHandler handler = simulateDeAuthAndCaptureHandler();
        handler.confirm();
        verify(mockPrefsWrapper).remove(TwitchBoard.KEY_AUTH_TOKEN);
    }

    @Test
    public void shouldDoNothingWhenDeAuthDenied() {
        ConfirmationHandler handler = simulateDeAuthAndCaptureHandler();
        handler.deny();
        verifyNoMoreInteractions(mockAccountView);
    }

    @Test
    public void shouldDisplayUserNameAsDisplayNameIfDisplayNameNull() {
        final String testUserName = "test_username";
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                TwitchApi.GetCallback<User> cb = (TwitchApi.GetCallback<User>) invocation
                        .getArguments()[0];
                cb.onReceived(new User()
                        .withName(testUserName));
                return null;
            }
        }).when(mockTwitchApi).getUser(any(TwitchApi.GetCallback.class));
        presenter.loadUserInfo();
        verify(mockAccountView).setUserName(testUserName);
        verify(mockAccountView).setUserDisplayName(testUserName);
    }

    private ConfirmationHandler simulateDeAuthAndCaptureHandler() {
        ArgumentCaptor<ConfirmationHandler> captor = ArgumentCaptor.forClass(ConfirmationHandler.class);
        presenter.onDeAuthorizeClicked();
        verify(mockAccountView).confirmDeAuthorizationWithUser(captor.capture());
        return captor.getValue();
    }

}
