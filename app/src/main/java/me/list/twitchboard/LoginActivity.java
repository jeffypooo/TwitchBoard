package me.list.twitchboard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ViewSwitcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.list.twitchboard.presenter.LoginPresenter;
import me.list.twitchboard.storage.SharedPrefsWrapperImpl;
import me.list.twitchboard.view.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private static final String TAG = "LoginActivity";

    @BindView(R.id.Login_ViewSwitcher)
    ViewSwitcher viewSwitcher;
    @BindView(R.id.Login_WebView_AuthorizeWebView)
    WebView webView;
    @BindView(R.id.Login_Button_Authorize)
    Button authorizeButton;

    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initPresenter();
        initWebView();
    }

    @OnClick(R.id.Login_Button_Authorize)
    void onAuthorizeClicked() {
        presenter.authorizeClicked();
    }

    @Override
    public void showNotification(String message) {
        Snackbar.make(viewSwitcher, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showURL(final String url) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                viewSwitcher.showNext();
                webView.loadUrl(url);
            }
        });
    }

    @Override
    public void authorized() {
        startActivity(new Intent(this, DashboardActivity.class));
        finish();
    }

    private void initPresenter() {
        presenter = new LoginPresenter(
                this,
                new SharedPrefsWrapperImpl(this, TwitchBoard.PREFS_AUTH)
        );
    }

    @SuppressLint("SetJavaScriptEnabled") //https://github.com/justintv/Twitch-API/issues/574
    private void initWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewHook(presenter));
    }

    private static class WebViewHook extends WebViewClient {

        private final LoginPresenter presenter;

        public WebViewHook(LoginPresenter presenter) {
            this.presenter = presenter;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            presenter.onPageLoadStarted(url);
            super.onPageStarted(view, url, favicon);
        }
    }
}
