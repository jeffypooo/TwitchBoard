
package me.list.twitchboard.twitch.model;

public class Preview {

    private String small;
    private String medium;
    private String large;
    private String template;

    /**
     * 
     * @return
     *     The small
     */
    public String getSmall() {
        return small;
    }

    /**
     * 
     * @param small
     *     The small
     */
    public void setSmall(String small) {
        this.small = small;
    }

    public Preview withSmall(String small) {
        this.small = small;
        return this;
    }

    /**
     * 
     * @return
     *     The medium
     */
    public String getMedium() {
        return medium;
    }

    /**
     * 
     * @param medium
     *     The medium
     */
    public void setMedium(String medium) {
        this.medium = medium;
    }

    public Preview withMedium(String medium) {
        this.medium = medium;
        return this;
    }

    /**
     * 
     * @return
     *     The large
     */
    public String getLarge() {
        return large;
    }

    /**
     * 
     * @param large
     *     The large
     */
    public void setLarge(String large) {
        this.large = large;
    }

    public Preview withLarge(String large) {
        this.large = large;
        return this;
    }

    /**
     * 
     * @return
     *     The template
     */
    public String getTemplate() {
        return template;
    }

    /**
     * 
     * @param template
     *     The template
     */
    public void setTemplate(String template) {
        this.template = template;
    }

    public Preview withTemplate(String template) {
        this.template = template;
        return this;
    }

}
