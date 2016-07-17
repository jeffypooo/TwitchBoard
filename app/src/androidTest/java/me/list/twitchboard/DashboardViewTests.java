package me.list.twitchboard;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.list.twitchboard.view.DashboardView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by masterjefferson on 7/16/2016.
 */
@RunWith(AndroidJUnit4.class)
public class DashboardViewTests {

    @Rule
    public final ActivityTestRule<DashboardActivity> activityTestRule =
            new ActivityTestRule<>(DashboardActivity.class);

    @Test
    public void shouldUpdateTitleField() {
        DashboardView view = activityTestRule.getActivity();
        String expected = "test title";
        view.setStatusText(expected);
        onView(withId(R.id.Dashboard_EditText_Status))
                .check(matches(withText(expected)));
    }

    @Test
    public void shouldUpdateGameField() {
        DashboardView view = activityTestRule.getActivity();
        String expected = "test game";
        view.setGameText(expected);
        onView(withId(R.id.Dashboard_EditText_Game))
                .check(matches(withText(expected)));
    }

}
