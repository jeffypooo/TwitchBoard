package me.list.twitchboard.twitch;

import com.google.common.collect.ImmutableList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by masterjefferson on 8/2/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class TwitchPircbotxWrapperInterfaceTest {

    static final String testNick      = "testName";
    static final String testUserName  = "testUserName";
    static final String testAuthToken = "testToken";

    @Mock PircBotFactory mockBotFactory;
    @Mock PircBotX       mockBot;
    @Mock IrcListener    mockListener;

    TwitchIrcClient client;

    @Before
    public void setup() {
        stubBotFactory();
        client = new TwitchPircbotxWrapper(mockBotFactory, testNick, testUserName, testAuthToken);
        client.setIrcListener(mockListener);
    }

    @Test
    public void shouldMakeBotWhenConnectingToTwitch() {
        client.connectToTwitchChat();
        verifyAndCaptureMakeBotConfig();
    }

    @Test
    public void shouldUseExpectedServerWithBotConfig() {
        client.connectToTwitchChat();
        Configuration config = verifyAndCaptureMakeBotConfig();
        ImmutableList<Configuration.ServerEntry> servers = config.getServers();
        assertThat(servers.size(), equalTo(1));
        Configuration.ServerEntry server = servers.get(0);
        assertThat(server.getHostname(), equalTo("irc.chat.twitch.tv"));
    }
    
    @Test
    public void shouldUseExpectedNameWithBotConfig() {
        client.connectToTwitchChat();
        Configuration config = verifyAndCaptureMakeBotConfig();
        assertThat(config.getName(), equalTo(testNick));
    }

    @Test
    public void shouldUseExpectedUserNameWithBotConfig() {
        client.connectToTwitchChat();
        Configuration config = verifyAndCaptureMakeBotConfig();
        assertThat(config.getLogin(), equalTo(testUserName));
    }

    @Test
    public void shouldStartBot() throws IOException, IrcException {
        client.connectToTwitchChat();
        verify(mockBot).startBot();

    }

    @Test
    public void shouldUserExpectedOAuthAsServerPasswordWithBotConfig() {
        client.connectToTwitchChat();
        Configuration config = verifyAndCaptureMakeBotConfig();
        assertThat(config.getServerPassword(), equalTo(testAuthToken));
    }

    private Configuration verifyAndCaptureMakeBotConfig() {
        ArgumentCaptor<Configuration> captor = ArgumentCaptor.forClass(Configuration.class);
        verify(mockBotFactory).makeBot(captor.capture());
        return captor.getValue();
    }

    private void stubBotFactory() {
        when(mockBotFactory.makeBot(any(Configuration.class)))
                .thenReturn(mockBot);
    }

}
