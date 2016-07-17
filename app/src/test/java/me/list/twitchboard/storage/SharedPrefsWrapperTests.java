package me.list.twitchboard.storage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import me.list.twitchboard.BuildConfig;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by masterjefferson on 7/17/2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 21, constants = BuildConfig.class)
public class SharedPrefsWrapperTests {

    SharedPrefsWrapper wrapper;

    @Before
    public void setup() {
        wrapper = new SharedPrefsWrapperImpl(RuntimeEnvironment.application, "test_prefs");
    }

    @Test
    public void shouldPersistString() {
        String testKey = "test_key";
        String testValue = "test";
        wrapper.putString("test_key", "test");
        assertThat(wrapper.getString(testKey), equalTo(testValue));
    }

}
