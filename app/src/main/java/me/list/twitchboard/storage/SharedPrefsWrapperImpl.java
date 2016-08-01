package me.list.twitchboard.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

import me.list.twitchboard.util.logging.LOG;

/**
 * Created by masterjefferson on 7/17/2016.
 */
public class SharedPrefsWrapperImpl implements SharedPrefsWrapper {

    private static final String TAG = "SharedPrefsWrapperImpl";
    private final WeakReference<Context> contextRef;
    private final String                 prefsName;

    public SharedPrefsWrapperImpl(Context context, String prefsName) {
        this.contextRef = new WeakReference<>(context);
        this.prefsName = prefsName;
    }

    @Override
    public void putString(String key, String value) {
        SharedPreferences.Editor editor = getEditor();
        if (editor != null) {
            LOG.d(TAG, "putString: %s:%s", key, value);
            editor.putString(key, value);
            editor.commit();
        }
    }

    @Override
    public String getString(String key) {
        SharedPreferences preferences = getPrefs();
        if (preferences != null) {
            return preferences.getString(key, null);
        }
        return null;
    }

    @Override
    public void remove(String key) {
        SharedPreferences.Editor editor = getEditor();
        if (editor != null) {
            LOG.d(TAG, "remove: %s", key);
            editor.remove(key);
            editor.commit();
        }
    }

    @Override
    public void clearPreferences() {
        SharedPreferences.Editor editor = getEditor();
        if (editor != null) {
            editor.clear();
            editor.commit();
        }
    }

    @Nullable
    private SharedPreferences.Editor getEditor() {
        SharedPreferences prefs = getPrefs();
        if (prefs != null) {
            return prefs.edit();
        }
        return null;
    }

    @Nullable
    private SharedPreferences getPrefs() {
        Context context = contextRef.get();
        if (context != null) {
            return context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        }
        return null;
    }
}
