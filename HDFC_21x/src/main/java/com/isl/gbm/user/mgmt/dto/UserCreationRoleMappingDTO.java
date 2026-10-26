package com.isl.gbm.user.mgmt.dto;


import java.util.List;


public class UserCreationRoleMappingDTO {

    private String username;
    private String employeeCode;
    private String employeeName;
    private String branchCode;
    private String branchName;
    private String departmentCode;
    private String departmentName;
    private String email;
    private String mobileNo;
    private String address;
    private String supervisorCode;
    private String roleId;
    private String password;
    private String userId;
    private String roleName;
    private String roleDescription;
    private String userStatus;
    private String userStatusTR;
    private String previousOperationType;
    private String remarks;
    private String authStatus;

    //new value
    private UserCreationRoleMappingDTO newValue;
    private UserCreationRoleMappingDTO oldValue;
    private List<UserCreationRoleMappingDTO> userList;

    private String createdBy;
    private String createdDate;
    private String modifiedBy;
    private String modifiedDate;
    private String verifiedBy;
    private String verifiedDate;
    private String verifiedStatus;
    private String lockedTimeDate;

    public UserCreationRoleMappingDTO getOldValue() {
        return oldValue;
    }

    public void setOldValue(UserCreationRoleMappingDTO oldValue) {
        this.oldValue = oldValue;
    }

    public String getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSupervisorCode() {
        return supervisorCode;
    }

    public void setSupervisorCode(String supervisorCode) {
        this.supervisorCode = supervisorCode;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserStatusTR() {
        return userStatusTR;
    }

    public void setUserStatusTR(String userStatusTR) {
        this.userStatusTR = userStatusTR;
    }

    public String getPreviousOperationType() {
        return previousOperationType;
    }

    public void setPreviousOperationType(String previousOperationType) {
        this.previousOperationType = previousOperationType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public UserCreationRoleMappingDTO getNewValue() {
        return newValue;
    }

    public void setNewValue(UserCreationRoleMappingDTO newValue) {
        this.newValue = newValue;
    }

    public List<UserCreationRoleMappingDTO> getUserList() {
        return userList;
    }

    public void setUserList(List<UserCreationRoleMappingDTO> userList) {
        this.userList = userList;
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

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
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

    public String getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setVerifiedStatus(String verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    public String getLockedTimeDate() {
        return lockedTimeDate;
    }

    public void setLockedTimeDate(String lockedTimeDate) {
        this.lockedTimeDate = lockedTimeDate;
    }
}
