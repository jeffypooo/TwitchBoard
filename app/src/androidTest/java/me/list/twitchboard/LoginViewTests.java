package me.list.twitchboard;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by masterjefferson on 7/17/2016.
 */
@RunWith(AndroidJUnit4.class)
public class LoginViewTests {

    @Rule
    public final ActivityTestRule<LoginActivity> activityTestRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void shouldStartShowingStartingLayout() {
        onView(withId(R.id.Login_Layout_Start))
                .check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void shouldSwitchToWebViewWhenShowURLCalled() {
        activityTestRule.getActivity().showURL("www.google.com");
        onView(withId(R.id.Login_WebView_AuthorizeWebView))
                .check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void shouldOpenURLWhenShowURLCalled() {
        activityTestRule.getActivity().showURL("www.google.com");
//        onWebView(withId(R.id.Login_WebView_AuthorizeWebView))
//                .check(webMatches())
    }

    @Test
    public void shouldShowNotificationSnackbarWhenShowNotificationCalled() {
        String expected = "test message";
        activityTestRule.getActivity().showNotification(expected);
        onView(withId(android.support.design.R.id.snackbar_text))
                .check(matches(withText(expected)));
    }


}
