
package me.list.twitchboard.twitch.model;

import android.support.annotation.Nullable;

public class StreamContainer {

    private StreamContainerLinks _links;
    @Nullable
    private Stream stream;

    /**
     * 
     * @return
     *     The _links
     */
    public StreamContainerLinks getLinks() {
        return _links;
    }

    /**
     * 
     * @param _links
     *     The _links
     */
    public void setLinks(StreamContainerLinks _links) {
        this._links = _links;
    }

    public StreamContainer withLinks(StreamContainerLinks streamContainerLinks) {
        this._links = streamContainerLinks;
        return this;
    }

    /**
     * 
     * @return
     *     The stream
     */
    @Nullable
    public Stream getStream() {
        return stream;
    }

    /**
     * 
     * @param stream
     *     The stream
     */
    public void setStream(Stream stream) {
        this.stream = stream;
    }

    public StreamContainer withStream(Stream stream) {
        this.stream = stream;
        return this;
    }

}
