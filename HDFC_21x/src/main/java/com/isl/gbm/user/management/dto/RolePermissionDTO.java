package com.isl.gbm.user.management.dto;

import java.util.List;

public class RolePermissionDTO {

    private String id;

    private String roleId;

    private String roleName;

    private String roleNameNew;


    private String remarks;

    private String preOpr;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    private String disableRole;

    private String disableRoleNew;


    public String getDisableRole() {
        return disableRole;
    }

    public void setDisableRole(String disableRole) {
        this.disableRole = disableRole;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    private String roleDescription;

    private String roleDescriptionNew;

    public String getRoleNameNew() {
        return roleNameNew;
    }

    public void setRoleNameNew(String roleNameNew) {
        this.roleNameNew = roleNameNew;
    }

    public String getPreOpr() {
        return preOpr;
    }

    public void setPreOpr(String preOpr) {
        this.preOpr = preOpr;
    }

    public String getDisableRoleNew() {
        return disableRoleNew;
    }

    public void setDisableRoleNew(String disableRoleNew) {
        this.disableRoleNew = disableRoleNew;
    }

    public String getRoleDescriptionNew() {
        return roleDescriptionNew;
    }

    public void setRoleDescriptionNew(String roleDescriptionNew) {
        this.roleDescriptionNew = roleDescriptionNew;
    }

    private String permissionId;

    private String createdBy;

    private String createdDate;

    private String lastModifiedBy;

    private String lastModifiedDate;

    private String verifiedBy;

    private String verifiedDate;

    private String verifyStatus;

    private String auditClrFlg;

    private String isDeleted;

    private List<PermissionDetailsDTO> mappedPermissionList;

    private List<PermissionDetailsDTO> unmappedPermissionList;

    private List<String> permissionList;


    public List<String> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<String> permissionList) {
        this.permissionList = permissionList;
    }

    public List<PermissionDetailsDTO> getMappedPermissionList() {
        return mappedPermissionList;
    }

    public void setMappedPermissionList(List<PermissionDetailsDTO> mappedPermissionList) {
        this.mappedPermissionList = mappedPermissionList;
    }

    public List<PermissionDetailsDTO> getUnmappedPermissionList() {
        return unmappedPermissionList;
    }

    public void setUnmappedPermissionList(List<PermissionDetailsDTO> unmappedPermissionList) {
        this.unmappedPermissionList = unmappedPermissionList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
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
}
