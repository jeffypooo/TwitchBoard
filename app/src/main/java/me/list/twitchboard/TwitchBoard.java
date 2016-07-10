package me.list.twitchboard;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by masterjefferson on 7/9/2016.
 */
public class TwitchBoard extends Application {

    public static final String PREFS_AUTH = TwitchBoard.class.getCanonicalName() + ".PREFS_AUTH";
    public static final String KEY_AUTH_TOKEN = TwitchBoard.class.getCanonicalName() + ".KEY_AUTH_TOKEN";

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .build());
    }
}
