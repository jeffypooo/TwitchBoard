package me.list.twitchboard.presenter;

import me.list.twitchboard.twitch.TwitchApi;
import me.list.twitchboard.twitch.model.Channel;
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
    }

}
