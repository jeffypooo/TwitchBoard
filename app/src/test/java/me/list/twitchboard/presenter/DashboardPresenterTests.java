package me.list.twitchboard.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import me.list.twitchboard.presenter.DashboardPresenter;
import me.list.twitchboard.twitch.TwitchApi;
import me.list.twitchboard.twitch.model.Channel;
import me.list.twitchboard.view.DashboardView;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by masterjefferson on 7/16/2016.
 */
public class DashboardPresenterTests {

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
        String status = "test status";
        String game = "test game";
        final Channel apiResult = new Channel(status, game);
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
    }

    @Test
    public void shouldShowUpdateConfirmationAndSetInformation() {
        String status = "test status";
        String game = "test game";
        final Channel apiResult = new Channel(status, game);
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


}
