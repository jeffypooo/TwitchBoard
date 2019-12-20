package me.list.twitchboard.storage;

import android.support.annotation.Nullable;

/**
 * Created by masterjefferson on 7/17/2016.
 */
public interface SharedPrefsWrapper {

    void putString(String key, String value);
    @Nullable String getString(String key);
    void remove(String key);
    void clearPreferences();

}
