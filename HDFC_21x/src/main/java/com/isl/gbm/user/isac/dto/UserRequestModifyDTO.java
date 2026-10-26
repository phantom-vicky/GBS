package com.isl.gbm.user.isac.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class UserRequestModifyDTO {

    @NotBlank(message = "User ID is Required")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "User ID cannot contain spaces or special characters")
    @Size(min = 1, max = 20)
    private String userID;
    //@NotBlank(message = "Employee Code is Required")
   // @Size(min=0, max = 20, message="EmployeeCode length must be between 1 to 20")
    private String employeeCode;

    //@NotBlank(message = "Employee Name is Required")
   // @Size(min=0, max=100, message="EmployeeName length must be between 1 to 100")
    private String employeeName;

    //@NotBlank(message = "Branch Name is Required")
   // @Size(min = 0, max=50, message="BranchName length must be between 1 to 50")
    private String branchName;

    //@NotBlank(message = "Branch Code is Required")
    //@Size(min=0, max=10 , message ="BranchCode length must be between 1 to 10")
    private String branchCode;


    //@NotBlank(message = "Department Code is Required")
    //@Size(min=1, max=5 , message ="DepartmentCode length must be between 1 to 5")
    private String departmentCode;
    //@NotBlank(message = "Department Name is Required")
    @Size(min=0,max=50 , message ="DepartmentName length must be between 1 to 50")
    private String departmentName;

    @Email(message = "Invalid email format")
    private String email;
   // @Size(min=0,max=10 , message ="Mobile.No length must be 10")
    private String mobileNo;
    private String address;
    private String supervisorCode;
    //@NotBlank(message = "Role Name is Required")
    @Pattern(regexp = "^[a-zA-Z _]*$", message = "RoleName cannot contain numeric or special characters")
    private String roleName;
    @NotBlank(message = "InitiatedBy is Required")
    @Size(min = 1, max = 20,message="InitiatedBy length must be between 1 to 20")
    private String initiatedBy;
    @NotBlank(message = "ApprovedBy is Required")
    @Size(min = 1, max = 20,message="ApprovedBy length must be between 1 to 20")
    private String approvedBy;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID.toUpperCase();
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode.toUpperCase();
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName.toUpperCase();
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName.toUpperCase();
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode.toUpperCase();
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode.toUpperCase();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName.toUpperCase();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toUpperCase();
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
        this.address = address.toUpperCase();
    }

    public String getSupervisorCode() {
        return supervisorCode;
    }

    public void setSupervisorCode(String supervisorCode) {
        this.supervisorCode = supervisorCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName.toUpperCase();
    }

    public String getInitiatedBy() {
        return initiatedBy;
    }

    public void setInitiatedBy(String initiatedBy) {
        this.initiatedBy = initiatedBy.toUpperCase();
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy.toUpperCase();
    }

    public String getiSACTicketNo() {
        return iSACTicketNo;
    }

    public void setiSACTicketNo(String iSACTicketNo) {
        this.iSACTicketNo = iSACTicketNo;
    }

    @NotBlank(message = "iSACTicketNo is Required")
    private String iSACTicketNo;

    @Override
    public String toString() {
        return "{" +
                "userID='" + userID + '\'' +
                ", employeeCode='" + employeeCode + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", branchName='" + branchName + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", departmentCode='" + departmentCode + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", address='" + address + '\'' +
                ", supervisorCode='" + supervisorCode + '\'' +
                ", roleName='" + roleName + '\'' +
                ", initiatedBy='" + initiatedBy + '\'' +
                ", approvedBy='" + approvedBy + '\'' +
                ", iSACTicketNo='" + iSACTicketNo + '\'' +
                '}';
    }
}
