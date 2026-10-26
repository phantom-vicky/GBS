package com.isl.gbm.user.management.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.Date;
import java.util.Set;

/**
 * The Class Role.
 */
@Entity
@Table(name = "GBM_USER_MGMT_ROLES")
public class Role {

    /**
     * The id.
     */
    @Id
    @Column(name = "id", updatable = false)
    @SequenceGenerator(name = "rolesSequence", sequenceName = "SEQ_USER_MGMT_ROLES", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rolesSequence")
    private Integer id;

    /**
     * The name.
     */
    @Column(name = "name")
    private String name;

    /**
     * The description.
     */
    @Column(name = "description")
    private String description;

    /**
     * The disable role.
     */
    @Column(name = "disable_role")
    private Integer disableRole;

    /**
     * The permissions.
     */
    @OneToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "GBM_USER_MGMT_ROLE_PERMISSION", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions;

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

    @Column(name = "VERIFIED_STATUS")
    private Character verifiedStatus;
    @Column(name = "VERIFIED_BY")
    private String verifiedBy;

    public Character getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setVerifiedStatus(Character verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public Date getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(Date verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    public Character getAuditCreFlg() {
        return auditCreFlg;
    }

    public void setAuditCreFlg(Character auditCreFlg) {
        this.auditCreFlg = auditCreFlg;
    }

    @Column(name = "VERIFIED_DATE")
    private Date verifiedDate;
    @Column(name = "AUDIT_CRE_FLG")
    private Character auditCreFlg;

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
     * Gets the permissions.
     *
     * @return the permissions
     */
    public Set<Permission> getPermissions() {
        return permissions;
    }

    /**
     * Sets the permissions.
     *
     * @param permissions the new permissions
     */
    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
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
     * Gets the disable role.
     *
     * @return the disable role
     */
    public Integer getDisableRole() {
        return disableRole;
    }

    /**
     * Sets the disable role.
     *
     * @param disableRole the new disable role
     */
    public void setDisableRole(Integer disableRole) {
        this.disableRole = disableRole;
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
