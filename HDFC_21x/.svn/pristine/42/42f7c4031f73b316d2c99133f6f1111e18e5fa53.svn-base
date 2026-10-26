package com.isl.gbm.user.management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * The Class RolePermission.
 */
@Entity
@Table(name = "GBM_USER_MGMT_ROLE_PERMISSION")
public class RolePermission {

    /**
     * The id.
     */
    @Id
    @Column(name = "id", updatable = false)
    @SequenceGenerator(name = "rolePermissionsSequence", sequenceName = "SEQ_USER_MGMT_ROLE_PERMISSION", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rolePermissionsSequence")
    private Integer id;

    /**
     * The role id.
     */
    @Column(name = "role_d")
    private Integer roleId;

    /**
     * The permission id.
     */
    @Column(name = "permission_id")
    private Integer permissionId;


    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_DATE")
    private String createdDate;

    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @Column(name = "LAST_MODIFIED_DATE")
    private String lastModifiedDate;

    @Column(name = "VERIFIED_BY")
    private String verifiedBy;

    @Column(name = "VERIFIED_DATE")
    private String verifiedDate;

    @Column(name = "VERIFIED_STATUS")
    private String verifyStatus;

    @Column(name = "AUDIT_CRE_FLG")
    private String auditClrFlg;

    @Column(name = "IS_DELETED")
    private String isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public String getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(String verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getAuditClrFlg() {
        return auditClrFlg;
    }

    public void setAuditClrFlg(String auditClrFlg) {
        this.auditClrFlg = auditClrFlg;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * Gets the role id.
     *
     * @return the role id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * Sets the role id.
     *
     * @param roleId the new role id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * Gets the permission id.
     *
     * @return the permission id
     */
    public Integer getPermissionId() {
        return permissionId;
    }

    /**
     * Sets the permission id.
     *
     * @param permissionId the new permission id
     */
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

}
