package me.list.twitchboard;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

/**
 * Created by masterjefferson on 7/31/2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 21, constants = BuildConfig.class)
public class SystemLoggingRoboTest {

    @BeforeClass
    public static void initLogging() {
        ShadowLog.stream = System.out;
    }

}
