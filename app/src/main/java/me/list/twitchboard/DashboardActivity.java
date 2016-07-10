package me.list.twitchboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.list.twitchboard.twitch.ChannelCallback;
import me.list.twitchboard.twitch.TwitchApi;
import me.list.twitchboard.twitch.UserCallback;
import me.list.twitchboard.twitch.model.Channel;
import me.list.twitchboard.twitch.model.User;
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

    private TwitchApi twitchApi;

    //region Activity Lifecycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        initTwitchAPI();
        loadChannelData();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    //endregion

    //region Initialization

    @Nullable
    private void initTwitchAPI() {
        String authToken = loadAuthToken();
        if (authToken != null) {
            this.twitchApi = new TwitchApi(authToken);
        }
    }

    private void loadChannelData() {
        if (this.twitchApi != null) {
            twitchApi.getUser(new UserCallback() {
                @Override
                public void didGetUser(User user) {
                    twitchApi.getChannel(new ChannelCB(DashboardActivity.this), user);
                }
            });
        }
    }

    @Nullable
    private String loadAuthToken() {
        SharedPreferences prefs = getSharedPreferences(TwitchBoard.PREFS_AUTH, MODE_PRIVATE);
        return prefs.getString(TwitchBoard.KEY_AUTH_TOKEN, null);
    }

    private void initUpdateButton() {
        this.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO call TwitchApi
            }
        });
    }

    //endregion

    //region Callback Classes

    private static class ChannelCB extends CallbackBase implements ChannelCallback {

        ChannelCB(DashboardActivity activity) {
            super(activity);
        }

        @Override
        public void didGetChannel(final Channel channel) {
            final DashboardActivity activity = getActivity();
            if (activity != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String status = channel.getStatus();
                        String game = channel.getGame();
                        if (status != null) {
                            activity.statusField.setText(status);
                        }
                        if (game != null) {
                            activity.gameField.setText(game);
                        }
                    }
                });
            }
        }
    }

    static abstract class CallbackBase {

        protected WeakReference<DashboardActivity> activityRef;

        CallbackBase(DashboardActivity activity) {
            this.activityRef = new WeakReference<>(activity);
        }

        @Nullable
        protected DashboardActivity getActivity() {
            return this.activityRef.get();
        }

    }

    //endregion
}
