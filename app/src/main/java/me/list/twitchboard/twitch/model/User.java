
package me.list.twitchboard.twitch.model;

public class User {

    private String        type;
    private String        name;
    private String        createdAt;
    private String        updatedAt;
    private UserLinks     links;
    private String        logo;
    private Integer       id;
    private String        displayName;
    private String        email;
    private Boolean       partnered;
    private String        bio;
    private Notifications notifications;

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

    public User withType(String type) {
        this.type = type;
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

    public User withName(String name) {
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

    public User withCreatedAt(String createdAt) {
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

    public User withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    /**
     * @return The links
     */
    public UserLinks getLinks() {
        return links;
    }

    /**
     * @param links The _links
     */
    public void setLinks(UserLinks links) {
        this.links = links;
    }

    public User withLinks(UserLinks links) {
        this.links = links;
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

    public User withLogo(String logo) {
        this.logo = logo;
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

    public User withId(Integer id) {
        this.id = id;
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

    public User withDisplayName(String displayName) {
        this.displayName = displayName;
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

    public User withEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * @return The partnered
     */
    public Boolean getPartnered() {
        return partnered;
    }

    /**
     * @param partnered The partnered
     */
    public void setPartnered(Boolean partnered) {
        this.partnered = partnered;
    }

    public User withPartnered(Boolean partnered) {
        this.partnered = partnered;
        return this;
    }

    /**
     * @return The bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * @param bio The bio
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    public User withBio(String bio) {
        this.bio = bio;
        return this;
    }

    /**
     * @return The notifications
     */
    public Notifications getNotifications() {
        return notifications;
    }

    /**
     * @param notifications The notifications
     */
    public void setNotifications(Notifications notifications) {
        this.notifications = notifications;
    }

    public User withNotifications(Notifications notifications) {
        this.notifications = notifications;
        return this;
    }

}
