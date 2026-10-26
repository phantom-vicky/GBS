package com.isl.gbm.user.management.dto;

import java.util.List;

public class ReportOnBranchWiseDeptWiseDTO {

    private String inputFlag;
    private String dcId;
    private String dcDescription;
    private String branchId;
    private String branchDescription;
    private String deptCode;
    private String deptDescription;
    private String userId;
    private String userName;

    private List<ReportOnBranchWiseDeptWiseResponseDTO> reportOnBranchWiseDeptWiseResponseDTOList;

    public static class ReportOnBranchWiseDeptWiseResponseDTO {
        private String branchId;
        private String userId;
        private String employeeId;
        private String employeeName;
        private String deptCode;
        private String roleId;
        private String accessPermissions;

        public String getBranchId() {
            return branchId;
        }

        public void setBranchId(String branchId) {
            this.branchId = branchId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(String employeeId) {
            this.employeeId = employeeId;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }

        public String getDeptCode() {
            return deptCode;
        }

        public void setDeptCode(String deptCode) {
            this.deptCode = deptCode;
        }

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }

        public String getAccessPermissions() {
            return accessPermissions;
        }

        public void setAccessPermissions(String accessPermissions) {
            this.accessPermissions = accessPermissions;
        }
    }

    public String getInputFlag() {
        return inputFlag;
    }

    public void setInputFlag(String inputFlag) {
        this.inputFlag = inputFlag;
    }

    public String getDcId() {
        return dcId;
    }

    public void setDcId(String dcId) {
        this.dcId = dcId;
    }

    public String getDcDescription() {
        return dcDescription;
    }

    public void setDcDescription(String dcDescription) {
        this.dcDescription = dcDescription;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchDescription() {
        return branchDescription;
    }

    public void setBranchDescription(String branchDescription) {
        this.branchDescription = branchDescription;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptDescription() {
        return deptDescription;
    }

    public void setDeptDescription(String deptDescription) {
        this.deptDescription = deptDescription;
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

    public List<ReportOnBranchWiseDeptWiseResponseDTO> getReportOnBranchWiseDeptWiseResponseDTOList() {
        return reportOnBranchWiseDeptWiseResponseDTOList;
    }

    public void setReportOnBranchWiseDeptWiseResponseDTOList(List<ReportOnBranchWiseDeptWiseResponseDTO> reportOnBranchWiseDeptWiseResponseDTOList) {
        this.reportOnBranchWiseDeptWiseResponseDTOList = reportOnBranchWiseDeptWiseResponseDTOList;
    }
}
