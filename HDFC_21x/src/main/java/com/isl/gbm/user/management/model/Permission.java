package com.isl.gbm.user.management.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.Date;

/**
 * The Class Permission.
 */
@Entity
@Table(name = "GBM_USER_MGMT_PERMISSIONS")
public class Permission {

    /**
     * The id.
     */
    @Id
    @Column(name = "id", updatable = false)
    @SequenceGenerator(name = "permissionsSequence", sequenceName = "SEQ_USER_MGMT_PERMISSIONS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permissionsSequence")
    private Integer id;

    /**
     * The feature id.
     */
    @Column(name = "feature_id")
    private Integer featureId;

    /**
     * The type.
     */
    @Column(name = "type")
    private String type;

    /**
     * The description.
     */
    @Column(name = "description")
    private String description;

    /**
     * The display name.
     */
    @Column(name = "display_name")
    private String displayName;

    /**
     * The created date.
     */
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    /**
     * The created by.
     */
    @Column(name = "created_by")
    private String createdBy;

    /**
     * The last modified date.
     */
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    /**
     * The last modified by.
     */
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

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
     * Gets the created by.
     *
     * @return the created by
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the created by.
     *
     * @param createdBy the new created by
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the last modified by.
     *
     * @return the last modified by
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * Sets the last modified by.
     *
     * @param lastModifiedBy the new last modified by
     */
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
