package me.list.twitchboard;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.webkit.WebView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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

    /**
     * for preventing https://github.com/justintv/Twitch-API/issues/574
     */
    @Test
    public void shouldHaveJSEnabledOnWebView() throws InterruptedException {
        LoginActivity activity = activityTestRule.getActivity();
        final WebView view = activity.webView;
        final CountDownLatch latch = new CountDownLatch(1);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                assertThat(view.getSettings().getJavaScriptEnabled(), is(true));
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS); //TODO is this even doing what I think it is?
    }

    @Test
    public void shouldSwitchToWebViewWhenShowURLCalled() {
        activityTestRule.getActivity().showURL("www.google.com");
        onView(withId(R.id.Login_WebView_AuthorizeWebView))
                .check(matches(isCompletelyDisplayed()));
    }

//    @Test
//    public void shouldGoToExpectedURLWhenShowURLCalled() throws InterruptedException {
//        LoginActivity activity = activityTestRule.getActivity();
//        final String expected = "www.google.com";
//        activity.showURL(expected);
//        final WebView view = activity.webView;
//        final CountDownLatch latch = new CountDownLatch(1);
//        activity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                assertThat(view.getUrl(), equalTo(expected));
//                latch.countDown();
//            }
//        });
//        latch.await(1, TimeUnit.SECONDS); //TODO is this even doing what I think it is?
//    }

//    @Test
//    public void shouldOpenURLWhenShowURLCalled() {
//        activityTestRule.getActivity().showURL("www.google.com");
//        onWebView(withId(R.id.Login_WebView_AuthorizeWebView))
//                .check(webContent(DomMatchers.elementById("base", )))
//    }

    @Test
    public void shouldShowNotificationSnackbarWhenShowNotificationCalled() {
        String expected = "test message";
        activityTestRule.getActivity().showNotification(expected);
        onView(withId(android.support.design.R.id.snackbar_text))
                .check(matches(withText(expected)));
    }


}
