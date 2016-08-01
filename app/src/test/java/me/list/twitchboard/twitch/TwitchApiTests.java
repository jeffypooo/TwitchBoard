package me.list.twitchboard.twitch;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import me.list.twitchboard.twitch.model.Channel;
import me.list.twitchboard.twitch.model.Stream;
import me.list.twitchboard.twitch.model.StreamContainer;
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
    //    static final String testAuthToken = "ponh9x5093wvi4z2zaxtf2iecm7m6p";
    static final String testAuthToken = "invalid";
    private final CountDownLatch latch = new CountDownLatch(1);

    @Before
    public void setup() {
        //TODO replace with mock
        httpClient = new OkHttpClient();
        twitchApi = new TwitchApiImpl(testAuthToken, httpClient);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldHandleAuthenticationError() throws InterruptedException {
        // given
        final String[] errorMessage = new String[1];
        TwitchApi.GetCallback callback = mock(TwitchApi.GetCallback.class);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Exception exception = (Exception) invocation.getArguments()[0];
                errorMessage[0] = exception.getMessage();
                latch.countDown();
                return null;
            }
        }).when(callback).onFailure(any(Exception.class));

        // when
        twitchApi.getChannel2(callback);
        latch.await(5, TimeUnit.SECONDS);

        // then
        Assert.assertEquals("unauthorized", errorMessage[0]);
    }

    //TODO test with mocks instead of real objects
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

    //TODO test with mocks instead of hitting actual API
    @Test
    public void shouldGetExpectedStream() throws InterruptedException {
        TwitchApi.StreamCallback callback = mock(TwitchApi.StreamCallback.class);
        final CountDownLatch streamGetLatch = new CountDownLatch(1);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                StreamContainer s = (StreamContainer) invocation.getArguments()[0];
                System.out.println("onGetStream: " + s);
                streamGetLatch.countDown();
                return null;
            }
        }).when(callback).onGetStream(any(Stream.class));
        twitchApi.getStream(callback);
        streamGetLatch.await(5, TimeUnit.SECONDS);
    }

    @Test
    public void shouldGetExpectedChat() {
        twitchApi.connectToChat();
    }

    //TODO test with mocks instead of real objects
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
        twitchApi.updateChannel("test status!!!", "test game", callback);
        verify(callback, timeout(5000)).onGetChannel(any(Channel.class));
    }

}
