
package me.list.twitchboard.twitch.model;

public class StreamContainerLinks {

    private String channel;
    private String self;

    /**
     * 
     * @return
     *     The channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * 
     * @param channel
     *     The channel
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    public StreamContainerLinks withChannel(String channel) {
        this.channel = channel;
        return this;
    }

    /**
     * 
     * @return
     *     The self
     */
    public String getSelf() {
        return self;
    }

    /**
     * 
     * @param self
     *     The self
     */
    public void setSelf(String self) {
        this.self = self;
    }

    public StreamContainerLinks withSelf(String self) {
        this.self = self;
        return this;
    }

}
