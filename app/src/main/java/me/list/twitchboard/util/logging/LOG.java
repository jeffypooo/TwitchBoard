package me.list.twitchboard.util.logging;

import android.util.Log;

/**
 * Created by masterjefferson on 7/10/2016.
 */
public class LOG {

    public static void v(String tag, String fmt, Object... args) {
        Log.v(tag, String.format(fmt, args));
    }

    public static void d(String tag, String fmt, Object... args) {
        Log.d(tag, String.format(fmt, args));
    }

    public static void w(String tag, String fmt, Object... args) {
        Log.w(tag, String.format(fmt, args));
    }

    public static void e(String tag, String fmt, Object... args) {
        Log.e(tag, String.format(fmt, args));
    }

    public static void e(String tag, Throwable e, String fmt, Object... args) {
        Log.e(tag, String.format(fmt, args), e);
    }
}
