package me.list.twitchboard.twitch.model;

import java.util.HashMap;
import java.util.Map;

public class ChannelLinks {

    private String self;
    private String follows;
    private String commercial;
    private String streamKey;
    private String chat;
    private String features;
    private String subscriptions;
    private String editors;
    private String teams;
    private String videos;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    public ChannelLinks withSelf(String self) {
        this.self = self;
        return this;
    }

    /**
     * @return The follows
     */
    public String getFollows() {
        return follows;
    }

    /**
     * @param follows The follows
     */
    public void setFollows(String follows) {
        this.follows = follows;
    }

    public ChannelLinks withFollows(String follows) {
        this.follows = follows;
        return this;
    }

    /**
     * @return The commercial
     */
    public String getCommercial() {
        return commercial;
    }

    /**
     * @param commercial The commercial
     */
    public void setCommercial(String commercial) {
        this.commercial = commercial;
    }

    public ChannelLinks withCommercial(String commercial) {
        this.commercial = commercial;
        return this;
    }

    /**
     * @return The streamKey
     */
    public String getStreamKey() {
        return streamKey;
    }

    /**
     * @param streamKey The stream_key
     */
    public void setStreamKey(String streamKey) {
        this.streamKey = streamKey;
    }

    public ChannelLinks withStreamKey(String streamKey) {
        this.streamKey = streamKey;
        return this;
    }

    /**
     * @return The chat
     */
    public String getChat() {
        return chat;
    }

    /**
     * @param chat The chat
     */
    public void setChat(String chat) {
        this.chat = chat;
    }

    public ChannelLinks withChat(String chat) {
        this.chat = chat;
        return this;
    }

    /**
     * @return The features
     */
    public String getFeatures() {
        return features;
    }

    /**
     * @param features The features
     */
    public void setFeatures(String features) {
        this.features = features;
    }

    public ChannelLinks withFeatures(String features) {
        this.features = features;
        return this;
    }

    /**
     * @return The subscriptions
     */
    public String getSubscriptions() {
        return subscriptions;
    }

    /**
     * @param subscriptions The subscriptions
     */
    public void setSubscriptions(String subscriptions) {
        this.subscriptions = subscriptions;
    }

    public ChannelLinks withSubscriptions(String subscriptions) {
        this.subscriptions = subscriptions;
        return this;
    }

    /**
     * @return The editors
     */
    public String getEditors() {
        return editors;
    }

    /**
     * @param editors The editors
     */
    public void setEditors(String editors) {
        this.editors = editors;
    }

    public ChannelLinks withEditors(String editors) {
        this.editors = editors;
        return this;
    }

    /**
     * @return The teams
     */
    public String getTeams() {
        return teams;
    }

    /**
     * @param teams The teams
     */
    public void setTeams(String teams) {
        this.teams = teams;
    }

    public ChannelLinks withTeams(String teams) {
        this.teams = teams;
        return this;
    }

    /**
     * @return The videos
     */
    public String getVideos() {
        return videos;
    }

    /**
     * @param videos The videos
     */
    public void setVideos(String videos) {
        this.videos = videos;
    }

    public ChannelLinks withVideos(String videos) {
        this.videos = videos;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public ChannelLinks withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return "ChannelLinks{" +
                "self='" + self + '\'' +
                ", follows='" + follows + '\'' +
                ", commercial='" + commercial + '\'' +
                ", streamKey='" + streamKey + '\'' +
                ", chat='" + chat + '\'' +
                ", features='" + features + '\'' +
                ", subscriptions='" + subscriptions + '\'' +
                ", editors='" + editors + '\'' +
                ", teams='" + teams + '\'' +
                ", videos='" + videos + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
