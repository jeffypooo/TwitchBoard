package me.list.twitchboard.twitch.model;

public class StreamLinks {

    private String self;

    /**
     * @return The self
     */
    public String getSelf() {
        return self;
    }

    /**
     * @param self The self
     */
    public void setSelf(String self) {
        this.self = self;
    }

    public StreamLinks withSelf(String self) {
        this.self = self;
        return this;
    }

}
