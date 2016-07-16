
package me.list.twitchboard.twitch.model;

import java.util.HashMap;
import java.util.Map;


public class Channel {

    private Boolean mature;
    private String status;
    private String broadcasterLanguage;
    private String displayName;
    private String game;
    private Object delay;
    private String language;
    private Integer id;
    private String name;
    private String createdAt;
    private String updatedAt;
    private String logo;
    private String banner;
    private String videoBanner;
    private Object background;
    private String profileBanner;
    private String profileBannerBackgroundColor;
    private Boolean partner;
    private String url;
    private Integer views;
    private Integer followers;
    private ChannelLinks _links;
    private String email;
    private String streamKey;
    private Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * @return The mature
     */
    public Boolean getMature() {
        return mature;
    }

    /**
     * @param mature The mature
     */
    public void setMature(Boolean mature) {
        this.mature = mature;
    }

    public Channel withMature(Boolean mature) {
        this.mature = mature;
        return this;
    }

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public Channel withStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * @return The broadcasterLanguage
     */
    public String getBroadcasterLanguage() {
        return broadcasterLanguage;
    }

    /**
     * @param broadcasterLanguage The broadcaster_language
     */
    public void setBroadcasterLanguage(String broadcasterLanguage) {
        this.broadcasterLanguage = broadcasterLanguage;
    }

    public Channel withBroadcasterLanguage(String broadcasterLanguage) {
        this.broadcasterLanguage = broadcasterLanguage;
        return this;
    }

    /**
     * @return The displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName The display_name
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Channel withDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * @return The game
     */
    public String getGame() {
        return game;
    }

    /**
     * @param game The game
     */
    public void setGame(String game) {
        this.game = game;
    }

    public Channel withGame(String game) {
        this.game = game;
        return this;
    }

    /**
     * @return The delay
     */
    public Object getDelay() {
        return delay;
    }

    /**
     * @param delay The delay
     */
    public void setDelay(Object delay) {
        this.delay = delay;
    }

    public Channel withDelay(Object delay) {
        this.delay = delay;
        return this;
    }

    /**
     * @return The language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language The language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    public Channel withLanguage(String language) {
        this.language = language;
        return this;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The _id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public Channel withId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    public Channel withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Channel withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * @return The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Channel withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    /**
     * @return The logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     * @param logo The logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Channel withLogo(String logo) {
        this.logo = logo;
        return this;
    }

    /**
     * @return The banner
     */
    public String getBanner() {
        return banner;
    }

    /**
     * @param banner The banner
     */
    public void setBanner(String banner) {
        this.banner = banner;
    }

    public Channel withBanner(String banner) {
        this.banner = banner;
        return this;
    }

    /**
     * @return The videoBanner
     */
    public String getVideoBanner() {
        return videoBanner;
    }

    /**
     * @param videoBanner The video_banner
     */
    public void setVideoBanner(String videoBanner) {
        this.videoBanner = videoBanner;
    }

    public Channel withVideoBanner(String videoBanner) {
        this.videoBanner = videoBanner;
        return this;
    }

    /**
     * @return The background
     */
    public Object getBackground() {
        return background;
    }

    /**
     * @param background The background
     */
    public void setBackground(Object background) {
        this.background = background;
    }

    public Channel withBackground(Object background) {
        this.background = background;
        return this;
    }

    /**
     * @return The profileBanner
     */
    public String getProfileBanner() {
        return profileBanner;
    }

    /**
     * @param profileBanner The profile_banner
     */
    public void setProfileBanner(String profileBanner) {
        this.profileBanner = profileBanner;
    }

    public Channel withProfileBanner(String profileBanner) {
        this.profileBanner = profileBanner;
        return this;
    }

    /**
     * @return The profileBannerBackgroundColor
     */
    public String getProfileBannerBackgroundColor() {
        return profileBannerBackgroundColor;
    }

    /**
     * @param profileBannerBackgroundColor The profile_banner_background_color
     */
    public void setProfileBannerBackgroundColor(String profileBannerBackgroundColor) {
        this.profileBannerBackgroundColor = profileBannerBackgroundColor;
    }

    public Channel withProfileBannerBackgroundColor(String profileBannerBackgroundColor) {
        this.profileBannerBackgroundColor = profileBannerBackgroundColor;
        return this;
    }

    /**
     * @return The partner
     */
    public Boolean getPartner() {
        return partner;
    }

    /**
     * @param partner The partner
     */
    public void setPartner(Boolean partner) {
        this.partner = partner;
    }

    public Channel withPartner(Boolean partner) {
        this.partner = partner;
        return this;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public Channel withUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * @return The views
     */
    public Integer getViews() {
        return views;
    }

    /**
     * @param views The views
     */
    public void setViews(Integer views) {
        this.views = views;
    }

    public Channel withViews(Integer views) {
        this.views = views;
        return this;
    }

    /**
     * @return The followers
     */
    public Integer getFollowers() {
        return followers;
    }

    /**
     * @param followers The followers
     */
    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public Channel withFollowers(Integer followers) {
        this.followers = followers;
        return this;
    }

    /**
     * @return The _links
     */
    public ChannelLinks get_links() {
        return _links;
    }

    /**
     * @param _links The _links
     */
    public void set_links(ChannelLinks _links) {
        this._links = _links;
    }

    public Channel withLinks(ChannelLinks channelLinks) {
        this._links = channelLinks;
        return this;
    }

    /**
     * @return The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public Channel withEmail(String email) {
        this.email = email;
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

    public Channel withStreamKey(String streamKey) {
        this.streamKey = streamKey;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Channel withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "mature=" + mature +
                ",\n status='" + status + '\'' +
                ",\n broadcasterLanguage='" + broadcasterLanguage + '\'' +
                ",\n displayName='" + displayName + '\'' +
                ",\n game='" + game + '\'' +
                ",\n delay=" + delay +
                ",\n language='" + language + '\'' +
                ",\n id=" + id +
                ",\n name='" + name + '\'' +
                ",\n createdAt='" + createdAt + '\'' +
                ",\n updatedAt='" + updatedAt + '\'' +
                ",\n logo='" + logo + '\'' +
                ",\n banner='" + banner + '\'' +
                ",\n videoBanner='" + videoBanner + '\'' +
                ",\n background=" + background +
                ",\n profileBanner='" + profileBanner + '\'' +
                ",\n profileBannerBackgroundColor='" + profileBannerBackgroundColor + '\'' +
                ",\n partner=" + partner +
                ",\n url='" + url + '\'' +
                ",\n views=" + views +
                ",\n followers=" + followers +
                ",\n _links=" + _links +
                ",\n email='" + email + '\'' +
                ",\n streamKey='" + streamKey + '\'' +
                ",\n additionalProperties=" + additionalProperties +
                '}';
    }
}


