package com.isl.gbm.user.management.dto;

import java.util.List;
import java.util.Map;

public class AccessControlMatrixReportDTO {
    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    private String branchId;
    private String branchName;
    private String roleName;

    public String getRoleFlag() {
        return roleFlag;
    }

    public void setRoleFlag(String roleFlag) {
        this.roleFlag = roleFlag;
    }

    private String roleFlag;

    private List<Map<String, String>> branchList;

    public List<Map<String, String>> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Map<String, String>> roleList) {
        this.roleList = roleList;
    }

    private List<Map<String, String>> roleList;

    private List<Map<String, String>> details;

    public List<Details> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<Details> detailList) {
        this.detailList = detailList;
    }

    private List<AccessControlMatrixReportDTO.Details> detailList;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }



    public List<Map<String, String>> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<Map<String, String>> branchList) {
        this.branchList = branchList;
    }

    public List<Map<String, String>> getDetails() {
        return details;
    }

    public void setDetails(List<Map<String, String>> details) {
        this.details = details;
    }

    public static class Details {


        public String getRoles() {
            return roles;
        }

        public void setRoles(String roles) {
            this.roles = roles;
        }

        public String getPermissions() {
            return permissions;
        }

        public void setPermissions(String permissions) {
            this.permissions = permissions;
        }

        public String getCreatedByCreatedDate() {
            return createdByCreatedDate;
        }

        public void setCreatedByCreatedDate(String createdByCreatedDate) {
            this.createdByCreatedDate = createdByCreatedDate;
        }

        public String getModifiedByModifiedDate() {
            return modifiedByModifiedDate;
        }

        public void setModifiedByModifiedDate(String modifiedByModifiedDate) {
            this.modifiedByModifiedDate = modifiedByModifiedDate;
        }

        public String getVerifiedByVerifiedDate() {
            return verifiedByVerifiedDate;
        }

        public void setVerifiedByVerifiedDate(String verifiedByVerifiedDate) {
            this.verifiedByVerifiedDate = verifiedByVerifiedDate;
        }

        private String roles ;
        private String permissions ;
        private String createdByCreatedDate;
        private String modifiedByModifiedDate;
        private String verifiedByVerifiedDate;


    }
}
