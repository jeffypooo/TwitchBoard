package me.list.twitchboard.twitch;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

/**
 * Created by masterjefferson on 8/2/2016.
 */
public interface PircBotFactory {

    PircBotX makeBot(Configuration configuration);

}
