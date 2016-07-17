package me.list.twitchboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.list.twitchboard.presenter.DashboardPresenter;
import me.list.twitchboard.twitch.TwitchApiImpl;
import me.list.twitchboard.view.DashboardView;
import okhttp3.OkHttpClient;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DashboardActivity extends AppCompatActivity implements DashboardView {

    //region Views

    @BindView(R.id.Dashboard_EditText_Status)
    EditText statusField;
    @BindView(R.id.Dashboard_EditText_Game)
    EditText gameField;
    @BindView(R.id.Dashboard_Button_Update)
    Button updateButton;
    @BindView(R.id.Dashboard_Layout)
    LinearLayout rootLayout;

    //endregion

    DashboardPresenter presenter;

    //region Activity Lifecycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        initPresenter();
        refreshChannelInfo();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    //endregion

    //region DashboardView implementation

    @Override
    public void setStatusText(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                statusField.setText(text);
            }
        });
    }

    @Override
    public void setGameText(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gameField.setText(text);
            }
        });
    }

    @Override
    public void showUpdateConfirmation(String status, String game) {
        String msg = String.format("Updated: \"%s\" - playing \"%s\"", status, game);
        Snackbar.make(rootLayout, msg, Snackbar.LENGTH_LONG).show();
    }

    //endregion

    //region Initialization

    @Nullable
    private void initPresenter() {
        presenter = new DashboardPresenter(
                this,
                new TwitchApiImpl(loadAuthToken(), new OkHttpClient())
        );
    }


    @Nullable
    private String loadAuthToken() {
        SharedPreferences prefs = getSharedPreferences(TwitchBoard.PREFS_AUTH, MODE_PRIVATE);
        return prefs.getString(TwitchBoard.KEY_AUTH_TOKEN, null);
    }


    //endregion

    @OnClick(R.id.Dashboard_Button_Update)
    void updateClicked() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        presenter.onUpdateClicked(getStatusText(), getGameText());
    }

    private String getStatusText() {
        return statusField.getText().toString();
    }

    private String getGameText() {
        return gameField.getText().toString();
    }

    private void refreshChannelInfo() {
        presenter.loadChannel();
    }


}
