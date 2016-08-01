package me.list.twitchboard.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.processphoenix.ProcessPhoenix;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.list.twitchboard.R;
import me.list.twitchboard.TwitchBoard;
import me.list.twitchboard.presenter.AccountPresenter;
import me.list.twitchboard.storage.SharedPrefsWrapper;
import me.list.twitchboard.storage.SharedPrefsWrapperImpl;
import me.list.twitchboard.twitch.TwitchApi;
import me.list.twitchboard.twitch.TwitchApiImpl;
import okhttp3.OkHttpClient;

/**
 * Created by masterjefferson on 7/31/2016.
 */
public class AccountFragment extends ThreadSafeFragment implements AccountView {

    @BindView(R.id.Account_ImageView_UserImage)  ImageView userImage;
    @BindView(R.id.Account_TextView_DisplayName) TextView  displayNameText;
    @BindView(R.id.Account_TextView_UserName)    TextView  userNameText;
    @BindView(R.id.Account_Button_DeAuthButton)  Button    deAuthorizeButton;

    private Unbinder         unbinder;
    private AccountPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.account_view, container, false);
        unbinder = ButterKnife.bind(this, root);
        initPresenter();
        presenter.loadUserInfo();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setUserDisplayName(String displayName) {
        threadSafeTextViewUpdate(displayNameText, displayName);
    }

    @Override
    public void setUserName(String userName) {
        threadSafeTextViewUpdate(userNameText, userName);
    }

    @Override
    public void setUserImageURL(String url) {
        threadSafeImageViewUpdate(userImage, url);
    }

    @Override
    public void confirmDeAuthorizationWithUser(final ConfirmationHandler confirmationHandler) {
        new AlertDialog.Builder(getContext())
                .setTitle("Confirm De-Authorization")
                .setMessage("Are you sure you want to disconnect your account?")

                .setPositiveButton("I'm Sure", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                        confirmationHandler.confirm();
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void restartApplication() {
        ProcessPhoenix.triggerRebirth(getContext());
    }

    @OnClick(R.id.Account_Button_DeAuthButton)
    void onDeAuthorizeClicked() {
        presenter.onDeAuthorizeClicked();
    }

    private void initPresenter() {
        SharedPrefsWrapper prefsWrapper = new SharedPrefsWrapperImpl(getContext(), TwitchBoard
                .PREFS_AUTH);
        TwitchApi twitchApi = new TwitchApiImpl(loadAuthToken(), new OkHttpClient());
        presenter = new AccountPresenter(prefsWrapper, twitchApi, this);
    }

    @Nullable
    private String loadAuthToken() {
        SharedPreferences prefs = getActivity()
                .getSharedPreferences(TwitchBoard.PREFS_AUTH, Context.MODE_PRIVATE);
        return prefs.getString(TwitchBoard.KEY_AUTH_TOKEN, null);
    }

}
