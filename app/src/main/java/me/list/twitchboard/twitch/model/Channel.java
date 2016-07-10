package me.list.twitchboard.twitch.model;

import android.support.annotation.Nullable;

/**
 * Created by masterjefferson on 7/10/2016.
 */
public interface Channel {
    @Nullable
    String getStatus();

    @Nullable
    String getGame();
}
