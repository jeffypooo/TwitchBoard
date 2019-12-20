package me.list.twitchboard.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import me.list.twitchboard.twitch.TwitchApi;
import me.list.twitchboard.twitch.model.Channel;
import me.list.twitchboard.twitch.model.Stream;
import me.list.twitchboard.view.DashboardView;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by masterjefferson on 7/16/2016.
 */
public class DashboardPresenterTest {

    DashboardPresenter presenter;
    DashboardView mockView;
    TwitchApi mockApi;

    @Before
    public void setup() {
        mockView = mock(DashboardView.class);
        mockApi = mock(TwitchApi.class);
        presenter = new DashboardPresenter(mockView, mockApi);
    }

    @Test
    public void shouldSetChannelInformation() {
        String name = "test name";
        String status = "test status";
        String game = "test game";
        final Channel apiResult = new Channel()
                .withName(name)
                .withStatus(status)
                .withGame(game)
                .withViews(1000)
                .withFollowers(5000);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                TwitchApi.ChannelCallback cb = (TwitchApi.ChannelCallback) invocation.getArguments()[0];
                cb.onGetChannel(apiResult);
                return null;
            }
        }).when(mockApi).getChannel(any(TwitchApi.ChannelCallback.class));
        presenter.loadChannel();
        verify(mockView).setStatusText(status);
        verify(mockView).setGameText(game);
        verify(mockView).setTotalViewCount(1000);
        verify(mockView).setFollowerCount(5000);
    }

    @Test
    public void shouldShowUpdateConfirmationAndSetInformation() {
        String name = "test name";
        String status = "test status";
        String game = "test game";
        final Channel apiResult = new Channel()
                .withName(name)
                .withStatus(status)
                .withGame(game)
                .withViews(5)
                .withFollowers(5);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                TwitchApi.ChannelCallback cb = (TwitchApi.ChannelCallback) invocation.getArguments()[2];
                cb.onGetChannel(apiResult);
                return null;
            }
        }).when(mockApi).updateChannel(
                any(String.class),
                any(String.class),
                any(TwitchApi.ChannelCallback.class)
        );
        presenter.onUpdateClicked(status, game);
        verify(mockView).showUpdateConfirmation(status, game);
        verify(mockView).setStatusText(status);
        verify(mockView).setGameText(game);
    }

    @Test
    public void shouldUpdateViewCount() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                TwitchApi.StreamCallback cb = (TwitchApi.StreamCallback) invocation.getArguments()[0];
                cb.onGetStream(new Stream()
                                .withGame("test game")
                                .withViewers(1000));
                return null;
            }
        }).when(mockApi).getStream(any(TwitchApi.StreamCallback.class));
        presenter.loadStream();
        verify(mockView).setViewerCount(1000);
    }

    @Test
    public void shouldUpdateStreamStatusWithOnline() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                TwitchApi.StreamCallback cb = (TwitchApi.StreamCallback) invocation.getArguments()[0];
                cb.onGetStream(new Stream()
                        .withGame("test game")
                        .withViewers(1000));
                return null;
            }
        }).when(mockApi).getStream(any(TwitchApi.StreamCallback.class));
        presenter.loadStream();
        verify(mockView).setStreamStatus(true);
    }

    @Test
    public void shouldUpdateStreamStatusWithOffline() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                TwitchApi.StreamCallback cb = (TwitchApi.StreamCallback) invocation.getArguments()[0];
                cb.onGetStream(null);
                return null;
            }
        }).when(mockApi).getStream(any(TwitchApi.StreamCallback.class));
        presenter.loadStream();
        verify(mockView).setStreamStatus(false);
    }

}
