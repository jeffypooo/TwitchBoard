package me.list.twitchboard.twitch;

import me.list.twitchboard.twitch.model.User;

/**
 * Created by masterjefferson on 7/10/2016.
 */
public interface UserCallback {

    void didGetUser(User user);

}
