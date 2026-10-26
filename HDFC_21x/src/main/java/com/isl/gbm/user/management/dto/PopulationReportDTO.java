package com.isl.gbm.user.management.dto;

public class PopulationReportDTO {

    private String date;
    private String id;
    private String userId;
    private String userName;
    private String employeeCode;
    private String branchCode;
    private String branchName;
    private String departmentCode;
    private String departmentName;
    private String roleName;
    private String status;
    private String creationDateTime;
    private String lastPwdChangedDateTime;
    private String lastLoginDateTime;
    private String lastModifiedIdName;
    private String lastModifiedDateTime;
    private String lastCheckerId;
    private String lastCheckerDateTime;
    private String disabledDateTime;
    private String deletionDateTime;
    private boolean sftpFlag;

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    private  String pageNo;
    private  String pageSize;
    private String searchValue;



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getLastPwdChangedDateTime() {
        return lastPwdChangedDateTime;
    }

    public void setLastPwdChangedDateTime(String lastPwdChangedDateTime) {
        this.lastPwdChangedDateTime = lastPwdChangedDateTime;
    }

    public String getLastLoginDateTime() {
        return lastLoginDateTime;
    }

    public void setLastLoginDateTime(String lastLoginDateTime) {
        this.lastLoginDateTime = lastLoginDateTime;
    }

    public String getLastModifiedIdName() {
        return lastModifiedIdName;
    }

    public void setLastModifiedIdName(String lastModifiedIdName) {
        this.lastModifiedIdName = lastModifiedIdName;
    }

    public String getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(String lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public String getLastCheckerId() {
        return lastCheckerId;
    }

    public void setLastCheckerId(String lastCheckerId) {
        this.lastCheckerId = lastCheckerId;
    }

    public String getLastCheckerDateTime() {
        return lastCheckerDateTime;
    }

    public void setLastCheckerDateTime(String lastCheckerDateTime) {
        this.lastCheckerDateTime = lastCheckerDateTime;
    }

    public String getDisabledDateTime() {
        return disabledDateTime;
    }

    public void setDisabledDateTime(String disabledDateTime) {
        this.disabledDateTime = disabledDateTime;
    }

    public String getDeletionDateTime() {
        return deletionDateTime;
    }

    public void setDeletionDateTime(String deletionDateTime) {
        this.deletionDateTime = deletionDateTime;
    }

    public String getCheckerDateTime() {
        return checkerDateTime;
    }

    public void setCheckerDateTime(String checkerDateTime) {
        this.checkerDateTime = checkerDateTime;
    }

    private String checkerDateTime;

    public boolean isSftpFlag() {
        return sftpFlag;
    }

    public void setSftpFlag(boolean sftpFlag) {
        this.sftpFlag = sftpFlag;
    }
}
