package me.list.twitchboard.twitch.model;

/**
 * Created by masterjefferson on 7/10/2016.
 */
public class Channel {

    public static final String KEY_STATUS = "status";
    public static final String KEY_GAME = "game";

    private String status, game;

    public Channel(String status, String game) {
        this.status = status;
        this.game = game;
    }

    public String getStatus() {
        return this.status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getGame() {
        return this.game;
    }


    public void setGame(String game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return String.format("[status='%s', game='%s']", status, game);
    }
}
