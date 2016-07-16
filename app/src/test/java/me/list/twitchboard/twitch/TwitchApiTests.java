package me.list.twitchboard.twitch;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import me.list.twitchboard.twitch.model.Channel;
import okhttp3.OkHttpClient;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

/**
 * Created by masterjefferson on 7/16/2016.
 */
public class TwitchApiTests {

    TwitchApi twitchApi;
    OkHttpClient httpClient;
    static final String testAuthToken = "bcyo1mfhlzz22m07jc13ejgnc62613";

    @Before
    public void setup() {
        httpClient = new OkHttpClient();
        twitchApi = new TwitchApiImpl(testAuthToken, httpClient);
    }

    @Test
    public void shouldGetExpectedChannel() {
        TwitchApi.ChannelCallback callback = mock(TwitchApi.ChannelCallback.class);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Channel c = (Channel) invocation.getArguments()[0];
                System.out.println("onGetChannel: " + c);
                return null;
            }
        }).when(callback).onGetChannel(any(Channel.class));
        twitchApi.getChannel(callback);
        verify(callback, timeout(1000)).onGetChannel(any(Channel.class));
    }

    @Test
    public void shouldUpdateChannel() {
        TwitchApi.ChannelCallback callback = mock(TwitchApi.ChannelCallback.class);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Channel c = (Channel) invocation.getArguments()[0];
                System.out.println("onGetChannel: " + c);
                return null;
            }
        }).when(callback).onGetChannel(any(Channel.class));
        twitchApi.updateChannel("test status", "Overwatch", callback);
        verify(callback, timeout(1000)).onGetChannel(any(Channel.class));
    }

}
