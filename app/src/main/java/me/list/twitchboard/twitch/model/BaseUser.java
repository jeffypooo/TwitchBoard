package me.list.twitchboard.twitch.model;

import me.list.twitchboard.twitch.model.User;

/**
 * Created by masterjefferson on 7/10/2016.
 */
public class BaseUser implements User {

    public static final String KEY_NAME = "name";
    public static final String KEY_DISPLAY_NAME = "display_name";
    public static final String KEY_BIO = "bio";

    private String name, displayName, bio;

    public BaseUser(String name, String displayName, String bio) {
        this.name = name;
        this.displayName = displayName;
        this.bio = bio;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String displayName() {
        return this.displayName;
    }

    @Override
    public String bio() {
        return this.bio;
    }
}
