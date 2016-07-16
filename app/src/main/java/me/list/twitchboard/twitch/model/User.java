package me.list.twitchboard.twitch.model;

/**
 * Created by masterjefferson on 7/10/2016.
 */
public class User {

    public static final String KEY_NAME = "name";
    public static final String KEY_DISPLAY_NAME = "display_name";
    public static final String KEY_BIO = "bio";

    public String name, displayName, bio;

    public User(String name, String displayName, String bio) {
        this.name = name;
        this.displayName = displayName;
        this.bio = bio;
    }

    public String name() {
        return this.name;
    }

    public String displayName() {
        return this.displayName;
    }

    public String bio() {
        return this.bio;
    }
}
