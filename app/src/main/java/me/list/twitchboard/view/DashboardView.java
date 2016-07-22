package me.list.twitchboard.view;

/**
 * Created by masterjefferson on 7/16/2016.
 */
public interface DashboardView {

    void setStatusText(String text);

    void setGameText(String text);

    void setViewerCount(int count);

    void setTotalViewCount(int count);

    void setFollowerCount(int count);

    void showUpdateConfirmation(String name, String game);

}
