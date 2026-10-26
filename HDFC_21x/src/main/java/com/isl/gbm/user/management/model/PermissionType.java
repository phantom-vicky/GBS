package com.isl.gbm.user.management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The Class PermissionType.
 */
@Entity
@Table(name = "GBM_USER_MGMT_PERMISSION_TYPES")
public class PermissionType {

    /**
     * The name.
     */
    @Id
    @Column(name = "type")
    private String type;

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


}
