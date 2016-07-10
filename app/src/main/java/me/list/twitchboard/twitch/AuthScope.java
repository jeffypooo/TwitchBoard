package me.list.twitchboard.twitch;

/**
 * Created by masterjefferson on 7/9/2016.
 */
public enum AuthScope {

    USER_READ("user_read"),
    USER_BLOCKS_EDIT("user_blocks_edit"),
    USER_BLOCKS_READ("user_blocks_read"),
    USER_FOLLOWS_EDIT("user_follows_edit"),
    CHANNEL_READ("channel_read"),
    CHANNEL_EDITOR("channel_editor"),
    CHANNEL_COMMERCIAL("channel_commercial"),
    CHANNEL_STREAM("channel_stream"),
    CHANNEL_SUBSCRIPTIONS("channel_subscriptions"),
    USER_SUBSCRIPTIONS("user_subscriptions"),
    CHANNEL_CHECK_SUBSCRIPTION("channel_check_subscription"),
    CHAT_LOGIN("chat_login"),
    CHANNEL_FEED_READ("channel_feed_read"),
    CHANNEL_FEED_EDIT("channel_feed_edit");

    public String raw;

    AuthScope(String raw) {
        this.raw = raw;
    }
}
