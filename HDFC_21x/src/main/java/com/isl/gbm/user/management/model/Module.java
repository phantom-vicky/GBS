package com.isl.gbm.user.management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Module model class.
 *
 * @author anoop.kumar
 */
@Entity
@Table(name = "GBM_USER_MGMT_MODULES")
public class Module {

    /**
     * The id.
     */
    @Id
    @Column(name = "id", updatable = false)
    private String id;

    /**
     * Module Name.
     */
    @Column(name = "name")
    private String name;

    /**
     * Module Description.
     */
    @Column(name = "description")
    private String description;

    /**
     * Display Name.
     */
    @Column(name = "display_name")
    private String displayName;

    /**
     * Module Status.
     */
    @Column(name = "status")
    private Integer status;

    /**
     * Gets the status.
     *
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
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

}
