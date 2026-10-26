package com.isl.gbm.user.management.dto;

import java.util.List;

public class AuditReportDTO {
    private String tableShortName;
    private String moduleId;
    private String linkName;
    private String operation;
    private String creModVerBy;
    private String recordStatus;
    private String fromDate;
    private String toDate;
    private String branchId;
    private String treasuryCode;
    private String stateCode;

    private List<AuditReportResponseDTO> auditReportResponseDTOS;

    public static class AuditReportResponseDTO {
        private String auditRefNum;
        private String tableName;
        private String keyVal;
        private String remarks;
        private List<ModifiedValuesDTO> modifiedValuesDTOList;
        private String createdBy;
        private String createdDate;
        private String modifiedBy;
        private String modifiedDate;
        private String authorisedBy;
        private String authorisedDate;
        private String moduleId;
        private String linkName;
        private String deletedBy;
        private String deletedDate;
        private String entityId;
        private String entityAccNo;

        public List<ModifiedValuesDTO> getModifiedValuesDTOList() {
            return modifiedValuesDTOList;
        }

        public void setModifiedValuesDTOList(List<ModifiedValuesDTO> modifiedValuesDTOList) {
            this.modifiedValuesDTOList = modifiedValuesDTOList;
        }

        public String getDeletedBy() {
            return deletedBy;
        }

        public void setDeletedBy(String deletedBy) {
            this.deletedBy = deletedBy;
        }

        public String getDeletedDate() {
            return deletedDate;
        }

        public void setDeletedDate(String deletedDate) {
            this.deletedDate = deletedDate;
        }

        public String getEntityId() {
            return entityId;
        }

        public void setEntityId(String entityId) {
            this.entityId = entityId;
        }

        public String getEntityAccNo() {
            return entityAccNo;
        }

        public void setEntityAccNo(String entityAccNo) {
            this.entityAccNo = entityAccNo;
        }

        public String getModuleId() {
            return moduleId;
        }

        public void setModuleId(String moduleId) {
            this.moduleId = moduleId;
        }

        public String getLinkName() {
            return linkName;
        }

        public void setLinkName(String linkName) {
            this.linkName = linkName;
        }

        public String getAuditRefNum() {
            return auditRefNum;
        }

        public void setAuditRefNum(String auditRefNum) {
            this.auditRefNum = auditRefNum;
        }

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getKeyVal() {
            return keyVal;
        }

        public void setKeyVal(String keyVal) {
            this.keyVal = keyVal;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
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

        public String getAuthorisedBy() {
            return authorisedBy;
        }

        public void setAuthorisedBy(String authorisedBy) {
            this.authorisedBy = authorisedBy;
        }

        public String getAuthorisedDate() {
            return authorisedDate;
        }

        public void setAuthorisedDate(String authorisedDate) {
            this.authorisedDate = authorisedDate;
        }

        public static class ModifiedValuesDTO implements Cloneable {
            private String colName;
            private String oldValue;
            private String newValue;

            public String getColName() {
                return colName;
            }

            public void setColName(String colName) {
                this.colName = colName;
            }

            public String getOldValue() {
                return oldValue;
            }

            public void setOldValue(String oldValue) {
                this.oldValue = oldValue;
            }

            public String getNewValue() {
                return newValue;
            }

            public void setNewValue(String newValue) {
                this.newValue = newValue;
            }

            @Override
            public Object clone() throws CloneNotSupportedException {
                return super.clone();
            }
        }

    }

    public String getTableShortName() {
        return tableShortName;
    }

    public void setTableShortName(String tableShortName) {
        this.tableShortName = tableShortName;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getCreModVerBy() {
        return creModVerBy;
    }

    public void setCreModVerBy(String creModVerBy) {
        this.creModVerBy = creModVerBy;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public List<AuditReportResponseDTO> getAuditReportResponseDTOS() {
        return auditReportResponseDTOS;
    }

    public void setAuditReportResponseDTOS(List<AuditReportResponseDTO> auditReportResponseDTOS) {
        this.auditReportResponseDTOS = auditReportResponseDTOS;
    }

    public String getTreasuryCode() {
        return treasuryCode;
    }

    public void setTreasuryCode(String treasuryCode) {
        this.treasuryCode = treasuryCode;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
}
