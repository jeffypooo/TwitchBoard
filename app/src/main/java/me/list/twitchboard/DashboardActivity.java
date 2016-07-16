package me.list.twitchboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DashboardActivity extends AppCompatActivity {

    //region Views

    @BindView(R.id.Dashboard_EditText_Status)
    EditText statusField;
    @BindView(R.id.Dashboard_EditText_Game)
    EditText gameField;
    @BindView(R.id.Dashboard_Button_Update)
    Button updateButton;

    //endregion


    //region Activity Lifecycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        initTwitchAPI();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    //endregion

    //region Initialization

    @Nullable
    private void initTwitchAPI() {

    }


    @Nullable
    private String loadAuthToken() {
        SharedPreferences prefs = getSharedPreferences(TwitchBoard.PREFS_AUTH, MODE_PRIVATE);
        return prefs.getString(TwitchBoard.KEY_AUTH_TOKEN, null);
    }


    //endregion

}
