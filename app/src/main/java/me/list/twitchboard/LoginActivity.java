package me.list.twitchboard;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ViewSwitcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.list.twitchboard.view.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private static final String TAG = "LoginActivity";

    @BindView(R.id.Login_ViewSwitcher)
    ViewSwitcher viewSwitcher;
    @BindView(R.id.Login_WebView_AuthorizeWebView)
    WebView webView;
    @BindView(R.id.Login_Button_Authorize)
    Button authorizeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    public void showNotification(String message) {
        Snackbar.make(viewSwitcher, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showURL(String url) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                viewSwitcher.showNext();
            }
        });
    }
}
