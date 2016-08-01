
package me.list.twitchboard.twitch.model;

public class Notifications {

    private Boolean email;
    private Boolean push;

    /**
     * @return The email
     */
    public Boolean getEmail() {
        return email;
    }

    /**
     * @param email The email
     */
    public void setEmail(Boolean email) {
        this.email = email;
    }

    public Notifications withEmail(Boolean email) {
        this.email = email;
        return this;
    }

    /**
     * @return The push
     */
    public Boolean getPush() {
        return push;
    }

    /**
     * @param push The push
     */
    public void setPush(Boolean push) {
        this.push = push;
    }

    public Notifications withPush(Boolean push) {
        this.push = push;
        return this;
    }

}
