package me.list.twitchboard.presenter;

import android.support.annotation.Nullable;

import me.list.twitchboard.twitch.TwitchApi;
import me.list.twitchboard.twitch.model.Channel;
import me.list.twitchboard.twitch.model.Stream;
import me.list.twitchboard.view.DashboardView;

/**
 * Created by masterjefferson on 7/16/2016.
 */
public class DashboardPresenter {

    private final DashboardView dashboardView;
    private final TwitchApi twitchApi;

    public DashboardPresenter(DashboardView dashboardView, TwitchApi twitchApi) {
        this.dashboardView = dashboardView;
        this.twitchApi = twitchApi;
    }

    public void loadChannel() {
        this.twitchApi.getChannel(new TwitchApi.ChannelCallback() {
            @Override
            public void onGetChannel(Channel channel) {
                updateChannelInfo(channel);
            }
        });
    }

    public void refreshChannelStats() {
        twitchApi.getStream(new TwitchApi.StreamCallback() {
            @Override
            public void onGetStream(@Nullable Stream stream) {
                updateStreamInfo(stream);
            }
        });
    }

    public void onUpdateClicked(String status, String game) {
        this.twitchApi.updateChannel(status, game, new TwitchApi.ChannelCallback() {
            @Override
            public void onGetChannel(Channel channel) {
                dashboardView.showUpdateConfirmation(channel.getStatus(), channel.getGame());
                updateChannelInfo(channel);
            }
        });
    }

    private void updateChannelInfo(Channel channel) {
        this.dashboardView.setStatusText(channel.getStatus());
        this.dashboardView.setGameText(channel.getGame());
        this.dashboardView.setTotalViewCount(channel.getViews());
        this.dashboardView.setFollowerCount(channel.getFollowers());
    }

    private void updateStreamInfo(@Nullable Stream stream) {
        if (stream != null) {
            dashboardView.setViewerCount(stream.getViewers());
        }
    }



}
