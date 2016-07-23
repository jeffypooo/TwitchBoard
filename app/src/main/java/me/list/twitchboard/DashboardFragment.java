package me.list.twitchboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.list.twitchboard.presenter.DashboardPresenter;
import me.list.twitchboard.twitch.TwitchApiImpl;
import me.list.twitchboard.view.DashboardView;
import okhttp3.OkHttpClient;

public class DashboardFragment extends Fragment implements DashboardView {
    private static final String TAG = "DashboardFragment";

    //region Views

    @BindView(R.id.Dashboard_EditText_Status)
    EditText statusField;
    @BindView(R.id.Dashboard_EditText_Game)
    EditText gameField;
    @BindView(R.id.Dashboard_Button_Update)
    Button updateButton;
    @BindView(R.id.Dashboard_CurrentViewerCount)
    TextView currentViewerCount;
    @BindView(R.id.Dashboard_ViewCount)
    TextView totalViewCount;
    @BindView(R.id.Dashboard_FollowerCount)
    TextView followerCount;
    @BindView(R.id.Dashboard_Layout)
    LinearLayout rootLayout;

    private Unbinder unbinder;

    //endregion

    DashboardPresenter presenter;
    RefreshTask refreshTask;

    //region Fragment Lifecycle

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dashboard_view, container, false);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initPresenter();
        refreshTask = new RefreshTask();
        refreshTask.start();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        refreshTask.cancelled.set(true);
        refreshTask = null;
    }

    //endregion

    //region DashboardView implementation

    @Override
    public void setStatusText(final String text) {
        threadSafeTextViewUpdate(statusField, text);
    }

    @Override
    public void setGameText(final String text) {
        threadSafeTextViewUpdate(gameField, text);
    }

    @Override
    public void setViewerCount(final int count) {
        if (count < 0) {
            threadSafeTextViewUpdate(currentViewerCount, "-");
        } else {
            threadSafeTextViewUpdate(currentViewerCount, Integer.toString(count));
        }
    }

    @Override
    public void setTotalViewCount(int count) {
        threadSafeTextViewUpdate(totalViewCount, Integer.toString(count));
    }

    @Override
    public void setFollowerCount(int count) {
        threadSafeTextViewUpdate(followerCount, Integer.toString(count));
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
        SharedPreferences prefs = getActivity()
                .getSharedPreferences(TwitchBoard.PREFS_AUTH, Context.MODE_PRIVATE);
        return prefs.getString(TwitchBoard.KEY_AUTH_TOKEN, null);
    }


    //endregion

    //region Listeners

    @OnClick(R.id.Dashboard_Button_Update)
    void updateClicked() {
        InputMethodManager imm = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = getActivity().getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(getActivity());
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        presenter.onUpdateClicked(getStatusText(), getGameText());
    }

    //endregion

    //region Helpers

    private String getStatusText() {
        return statusField.getText().toString();
    }

    private String getGameText() {
        return gameField.getText().toString();
    }

    private void threadSafeTextViewUpdate(final TextView view, final String text) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.setText(text);
            }
        });
    }

    private void refreshChannelInfo() {
        Log.d(TAG, "refreshing...");
        presenter.loadChannel();
        presenter.refreshChannelStats();
    }

    //endregion

    //TODO should probably move this into the presenter or abstract and pass to presenter
    private class RefreshTask extends Thread {

        private final AtomicBoolean cancelled = new AtomicBoolean(false);
        private long intervalMillis = 30000;

        RefreshTask(long intervalMillis) {
            this.intervalMillis = intervalMillis;
        }

        RefreshTask() {
        }

        @Override
        public void run() {
            while (!cancelled.get()) {
                try {
                    refreshChannelInfo();
                    Thread.sleep(intervalMillis);
                } catch (InterruptedException e) {
                    if (cancelled.get()) {
                        break;
                    }
                }

            }
        }


    }

}
