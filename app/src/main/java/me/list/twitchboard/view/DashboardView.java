package me.list.twitchboard.view;

/**
 * Created by masterjefferson on 7/16/2016.
 */
public interface DashboardView {

    void setStatusText(String text);

    void setGameText(String text);

    void showUpdateConfirmation(String name, String game);

}
