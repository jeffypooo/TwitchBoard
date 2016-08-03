package me.list.twitchboard.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import me.list.twitchboard.twitch.IrcListener;
import me.list.twitchboard.twitch.TwitchIrcClient;
import me.list.twitchboard.view.ChatView;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

/**
 * Created by masterjefferson on 7/23/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ChatPresenterTest {

    @Mock
    ChatView        mockView;
    @Mock
    TwitchIrcClient mockClient;

    ChatPresenter presenter;

    @Before
    public void setup() {
        presenter = new ChatPresenter(mockView, mockClient);
    }

    @Test
    public void shouldHaveSetIrcListener() {
        verify(mockClient).setIrcListener(any(IrcListener.class));
    }

    @Test
    public void shouldShowConnectedConfirmation() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                presenter.onConnected();
                return null;
            }
        }).when(mockClient).connectToTwitchChat();
        presenter.connectToChat();
        verify(mockView).showNotification("Chat connected.");
    }

    @Test
    public void shouldShowDisconnectedNotification() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                presenter.onDisconnected();
                return null;
            }
        }).when(mockClient).connectToTwitchChat();
        presenter.connectToChat();
        verify(mockView).showNotification("Chat disconnected.");
    }

    @Test
    public void shouldShowErrorNotification() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                presenter.onError(new RuntimeException("stub"));
                return null;
            }
        }).when(mockClient).connectToTwitchChat();
        presenter.connectToChat();
        verify(mockView).showNotification("An error occurred with chat.");
    }

    @Test
    public void shouldSendMessageWhenSendPressed() {
        presenter.onSendClick("test");
        verify(mockClient).sendChannelMessage("test");
    }

    @Test
    public void shouldDisplaySentMessageWhenSendPressed() {
        presenter.onSendClick("test");
        verify(mockView).showSentMessage("test");
    }

}
