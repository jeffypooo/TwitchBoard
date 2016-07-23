package me.list.twitchboard.twitch;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

/**
 * Created by masterjefferson on 7/23/2016.
 */
public class TwitchIrcClientTests {
    static final long DEFAULT_TIMEOUT = 5000;

    TwitchIrcClient twitchIrcClient;

    @Mock
    IrcListener mockListener;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        twitchIrcClient = new TwitchIrcBot("masterjefferson", "ponh9x5093wvi4z2zaxtf2iecm7m6p");
        twitchIrcClient.setIrcListener(mockListener);
    }

    @Test
    public void shouldConnectToTwitch() throws InterruptedException {
        twitchIrcClient.connectToTwitchChat();
        verify(mockListener, timeout(DEFAULT_TIMEOUT)).onConnected();
    }

    @Test
    public void shouldDisconnectFromTwitch() throws InterruptedException {
        shouldConnectToTwitch();
        twitchIrcClient.disconnectFromTwitch();
        verify(mockListener, timeout(DEFAULT_TIMEOUT)).onDisconnected();
    }

}
