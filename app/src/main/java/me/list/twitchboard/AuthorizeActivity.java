package me.list.twitchboard;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuthorizeActivity extends AppCompatActivity {

    public static final String EXTRA_AUTH_URL = AuthorizeActivity.class.getCanonicalName() +
            "AUTH_HTML";

    public static final String EXTRA_AUTH_TOKEN = AuthorizeActivity.class.getCanonicalName() +
            "AUTH_TOKEN";

    public static final int AUTHORIZE_USER_REQ = 10;
    public static final int AUTHORIZE_SUCCESS = 20;
    public static final int AUTHORIZE_FAILURE = -20;

    @BindView(R.id.Authorize_WebView)
    WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorize);
        ButterKnife.bind(this);
        String authUrl = getAuthURL();
        if (authUrl == null) {
            showErrorMessageAndFinish("No Response", "Oops! Looks like Twitch didn't respond...");
        }
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.setWebViewClient(new AuthHookClient(new AuthHookClient.Callback() {
            @Override
            public void didAuthorize(String authToken) {
                setResult(AUTHORIZE_SUCCESS, new Intent().putExtra(EXTRA_AUTH_TOKEN, authToken));
                finish();
            }

            @Override
            public void didNotAuthorize() {
                setResult(AUTHORIZE_FAILURE);
                finish();
            }
        })); //so that links load in our view
        this.webView.loadUrl(authUrl);
    }

    @Nullable
    private String getAuthURL() {
        return getIntent().getStringExtra(EXTRA_AUTH_URL);
    }

    private void showErrorMessageAndFinish(String title, String msg) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(msg)
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        finish();
                    }
                })
                .setCancelable(false)
                .show();

    }

    private static class AuthHookClient extends WebViewClient {

        interface Callback {
            void didAuthorize(String authToken);
            void didNotAuthorize();
        }

        private final String TAG = AuthHookClient.class.getSimpleName();

        private final Callback callback;

        public AuthHookClient(Callback callback) {
            this.callback = callback;
        }



        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.v(TAG, "onPageStarted: " + url);
            if (url.startsWith(TwitchBoard.TWITCH_CLIENT_REDIRECT_URI)) {
                view.stopLoading();
                Pattern pattern = Pattern.compile("#access_token=(.*)&");
                Matcher matcher = pattern.matcher(url);
                if (matcher.find()) {
                    notifyCallback(matcher.group(1));
                }
            } else {
                super.onPageStarted(view, url, favicon);
            }
        }

        private void notifyCallback(String token) {
            if (callback != null) {
                callback.didAuthorize(token);
            }
        }
    }
}
