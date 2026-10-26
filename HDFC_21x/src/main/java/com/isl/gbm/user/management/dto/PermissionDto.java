package com.isl.gbm.user.management.dto;

import org.hibernate.validator.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import java.util.Date;

/**
 * The Class PermissionDto.
 */
public class PermissionDto {

    /**
     * The id.
     */
    private Integer id;

    /**
     * The feature id.
     */
    @NotNull(message = "Feature Id cannot be blank")
    private Integer featureId;

    /**
     * The name.
     */
    @NotBlank(message = "Permission type cannot be blank")
    private String type;

    /**
     * The decription.
     */
    private String description;

    /**
     * The display name.
     */
    @NotBlank(message = "Display name cannot be blank")
    private String displayName;

    /**
     * The created date.
     */
    private Date createdDate;

    /**
     * The created by username.
     */
    private String createdByUsername;

    /**
     * The last modified date.
     */
    private Date lastModifiedDate;

    /**
     * The last modified by username.
     */
    private String lastModifiedByUsername;

    /**
     * The feature name.
     */
    private String featureName;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the feature id.
     *
     * @return the feature id
     */
    public Integer getFeatureId() {
        return featureId;
    }

    /**
     * Sets the feature id.
     *
     * @param featureId the new feature id
     */
    public void setFeatureId(Integer featureId) {
        this.featureId = featureId;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the display name.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the display name.
     *
     * @param displayName the new display name
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the created date.
     *
     * @return the created date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the created date.
     *
     * @param createdDate the new created date
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets the created by username.
     *
     * @return the created by username
     */
    public String getCreatedByUsername() {
        return createdByUsername;
    }

    /**
     * Sets the created by username.
     *
     * @param createdByUsername the new created by username
     */
    public void setCreatedByUsername(String createdByUsername) {
        this.createdByUsername = createdByUsername;
    }

    /**
     * Gets the last modified date.
     *
     * @return the last modified date
     */
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * Sets the last modified date.
     *
     * @param lastModifiedDate the new last modified date
     */
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * Gets the last modified by username.
     *
     * @return the last modified by username
     */
    public String getLastModifiedByUsername() {
        return lastModifiedByUsername;
    }

    /**
     * Sets the last modified by username.
     *
     * @param lastModifiedByUsername the new last modified by username
     */
    public void setLastModifiedByUsername(String lastModifiedByUsername) {
        this.lastModifiedByUsername = lastModifiedByUsername;
    }

    /**
     * Gets the feature name.
     *
     * @return the feature name
     */
    public String getFeatureName() {
        return featureName;
    }

    /**
     * Sets the feature name.
     *
     * @param featureName the new feature name
     */
    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PermissionDto [id=" + id + ", featureId=" + featureId + ", type=" + type + ", description="
                + description + ", displayName=" + displayName + ", createdDate=" + createdDate + ", createdByUsername="
                + createdByUsername + ", lastModifiedDate=" + lastModifiedDate + ", lastModifiedByUsername="
                + lastModifiedByUsername + ", featureName=" + featureName + "]";
    }

}
