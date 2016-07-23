
package me.list.twitchboard.twitch.model;

public class Stream {

    private String game;
    private Integer viewers;
    private Double averageFps;
    private Integer delay;
    private Integer videoHeight;
    private Boolean isPlaylist;
    private String createdAt;
    private Integer id;
    private Preview preview;
    private StreamLinks _links;

    /**
     * 
     * @return
     *     The game
     */
    public String getGame() {
        return game;
    }

    /**
     * 
     * @param game
     *     The game
     */
    public void setGame(String game) {
        this.game = game;
    }

    public Stream withGame(String game) {
        this.game = game;
        return this;
    }

    /**
     * 
     * @return
     *     The viewers
     */
    public Integer getViewers() {
        return viewers == null ? 0 : viewers;
    }

    /**
     * 
     * @param viewers
     *     The viewers
     */
    public void setViewers(Integer viewers) {
        this.viewers = viewers;
    }

    public Stream withViewers(Integer viewers) {
        this.viewers = viewers;
        return this;
    }

    /**
     * 
     * @return
     *     The averageFps
     */
    public Double getAverageFps() {
        return averageFps;
    }

    /**
     * 
     * @param averageFps
     *     The average_fps
     */
    public void setAverageFps(Double averageFps) {
        this.averageFps = averageFps;
    }

    public Stream withAverageFps(Double averageFps) {
        this.averageFps = averageFps;
        return this;
    }

    /**
     * 
     * @return
     *     The delay
     */
    public Integer getDelay() {
        return delay;
    }

    /**
     * 
     * @param delay
     *     The delay
     */
    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public Stream withDelay(Integer delay) {
        this.delay = delay;
        return this;
    }

    /**
     * 
     * @return
     *     The videoHeight
     */
    public Integer getVideoHeight() {
        return videoHeight;
    }

    /**
     * 
     * @param videoHeight
     *     The video_height
     */
    public void setVideoHeight(Integer videoHeight) {
        this.videoHeight = videoHeight;
    }

    public Stream withVideoHeight(Integer videoHeight) {
        this.videoHeight = videoHeight;
        return this;
    }

    /**
     * 
     * @return
     *     The isPlaylist
     */
    public Boolean getIsPlaylist() {
        return isPlaylist;
    }

    /**
     * 
     * @param isPlaylist
     *     The is_playlist
     */
    public void setIsPlaylist(Boolean isPlaylist) {
        this.isPlaylist = isPlaylist;
    }

    public Stream withIsPlaylist(Boolean isPlaylist) {
        this.isPlaylist = isPlaylist;
        return this;
    }

    /**
     * 
     * @return
     *     The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 
     * @param createdAt
     *     The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Stream withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The _id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public Stream withId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * 
     * @return
     *     The preview
     */
    public Preview getPreview() {
        return preview;
    }

    /**
     * 
     * @param preview
     *     The preview
     */
    public void setPreview(Preview preview) {
        this.preview = preview;
    }

    public Stream withPreview(Preview preview) {
        this.preview = preview;
        return this;
    }

    /**
     * 
     * @return
     *     The _links
     */
    public StreamLinks getLinks() {
        return _links;
    }

    /**
     * 
     * @param links
     *     The _links
     */
    public void setLinks(StreamLinks links) {
        this._links = links;
    }

    public Stream withLinks(StreamLinks links) {
        this._links = links;
        return this;
    }

}
