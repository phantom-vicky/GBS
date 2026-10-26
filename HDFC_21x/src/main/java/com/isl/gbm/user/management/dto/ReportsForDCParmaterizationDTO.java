package com.isl.gbm.user.management.dto;

public class ReportsForDCParmaterizationDTO {

    private String tableShortName;
    private String moduleId;
    private String linkName;
    private String operation;
    private String creModVerBy;
    private String recordStatusl;
    private String fromDate;
    private String toDate;

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

    public String getRecordStatusl() {
        return recordStatusl;
    }

    public void setRecordStatusl(String recordStatusl) {
        this.recordStatusl = recordStatusl;
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

    @Override
    public String toString() {
        return "ReportsForDCParmaterizationResponseDTO{" +
                "tableShortName='" + tableShortName + '\'' +
                ", moduleId='" + moduleId + '\'' +
                ", linkName='" + linkName + '\'' +
                ", operation='" + operation + '\'' +
                ", creModVerBy='" + creModVerBy + '\'' +
                ", recordStatusl='" + recordStatusl + '\'' +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                '}';
    }
}
