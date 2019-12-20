package me.list.twitchboard.storage;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import me.list.twitchboard.SystemLoggingRoboTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by masterjefferson on 7/17/2016.
 */
public class SharedPrefsWrapperTest extends SystemLoggingRoboTest {

    SharedPrefsWrapper wrapper;

    @Before
    public void setup() {
        wrapper = new SharedPrefsWrapperImpl(RuntimeEnvironment.application, "test_prefs");
    }

    @Test
    public void shouldPersistString() {
        putStringAndAssert("test_key", "test_value");
    }

    @Test
    public void shouldRemoveString() {
        String key = "test_key";
        String value = "test_value";
        putStringAndAssert(key, value);
        wrapper.remove(key);
        assertThat(wrapper.getString(key), is(nullValue()));
    }

    private void putStringAndAssert(String key, String value) {
        wrapper.putString(key, value);
        assertThat(wrapper.getString(key), equalTo(value));
    }

    @Test
    public void shouldClearAllPreferences() {
        putStringAndAssert("one", "foo");
        putStringAndAssert("two", "bar");
        wrapper.clearPreferences();
    }

}
