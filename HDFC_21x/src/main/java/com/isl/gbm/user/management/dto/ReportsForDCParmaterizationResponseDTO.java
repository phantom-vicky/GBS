package com.isl.gbm.user.management.dto;

public class ReportsForDCParmaterizationResponseDTO implements Cloneable {

    private String auditRefNum;
    private String tableName;
    private String keyVal;
    private String colName;
    private String oldName;
    private String newName;
    private String remarks;
    private String createdBy;
    private String createdDate;
    private String modifiedBy;
    private String modifiedDate;
    private String authorisedBy;
    private String authorisedDate;

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

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
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

    @Override
    public String toString() {
        return "ReportsForDCParmaterizationResponseDTO{" +
                "auditRefNum='" + auditRefNum + '\'' +
                ", tableName='" + tableName + '\'' +
                ", keyVal='" + keyVal + '\'' +
                ", colName='" + colName + '\'' +
                ", oldName='" + oldName + '\'' +
                ", newName='" + newName + '\'' +
                ", remarks='" + remarks + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", modifiedDate='" + modifiedDate + '\'' +
                ", authorisedBy='" + authorisedBy + '\'' +
                ", authorisedDate='" + authorisedDate + '\'' +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
