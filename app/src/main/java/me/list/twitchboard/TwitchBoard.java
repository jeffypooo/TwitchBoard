package me.list.twitchboard;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by masterjefferson on 7/9/2016.
 */
public class TwitchBoard extends Application {

    public static final String PREFS_AUTH                 = TwitchBoard.class.getCanonicalName()
            + ".PREFS_AUTH";
    public static final String KEY_AUTH_TOKEN             = TwitchBoard.class.getCanonicalName()
            + ".KEY_AUTH_TOKEN";
    public static final String TWITCH_CLIENT_ID           = "l4g05we5eikm1285ffehr41z29q6d0e";
    public static final String TWITCH_CLIENT_REDIRECT_URI = "http://localhost";

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .build());
    }
}
