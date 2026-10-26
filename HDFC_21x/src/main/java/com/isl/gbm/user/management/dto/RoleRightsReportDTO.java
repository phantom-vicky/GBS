package com.isl.gbm.user.management.dto;

public class RoleRightsReportDTO {

    private String date;
    private String roleName;
    private String roleDesc;
    private String rightsAssigned;
    private String roleDescription;
    private String rightsAssignedFlg;
    private String roleStatus;
    private String departmentCode;
    private String pageNo;
    private String pageSize;
    private String searchValue;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String getRightsAssigned() {
        return rightsAssigned;
    }

    public void setRightsAssigned(String rightsAssigned) {
        this.rightsAssigned = rightsAssigned;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getRightsAssignedFlg() {
        return rightsAssignedFlg;
    }

    public void setRightsAssignedFlg(String rightsAssignedFlg) {
        this.rightsAssignedFlg = rightsAssignedFlg;
    }

    public String getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getTypeAccess() {
        return typeAccess;
    }

    public void setTypeAccess(String typeAccess) {
        this.typeAccess = typeAccess;
    }

    public String getLastCheckerDate() {
        return lastCheckerDate;
    }

    public void setLastCheckerDate(String lastCheckerDate) {
        this.lastCheckerDate = lastCheckerDate;
    }

    private String typeAccess;
    private String lastCheckerDate;

}
