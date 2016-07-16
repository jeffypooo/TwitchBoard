package me.list.twitchboard.twitch.model;

/**
 * Created by masterjefferson on 7/10/2016.
 */
public class BaseChannel implements Channel {

    public static final String KEY_STATUS = "status";
    public static final String KEY_GAME = "game";

    private String status, game;

    public BaseChannel(String status, String game) {
        this.status = status;
        this.game = game;
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getGame() {
        return this.game;
    }

    @Override
    public void setGame(String game) {
        this.game = game;
    }
}
