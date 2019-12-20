
package me.list.twitchboard.twitch.model;

public class UserLinks {

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

    public UserLinks withSelf(String self) {
        this.self = self;
        return this;
    }

}
