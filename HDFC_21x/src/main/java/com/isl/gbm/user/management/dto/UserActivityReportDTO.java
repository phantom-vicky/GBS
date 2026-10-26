package com.isl.gbm.user.management.dto;

import java.util.List;
import java.util.Map;

public class UserActivityReportDTO {

    private String branchId;
    private String branchName;
    private String fromDate;
    private String toDate;
    private String userName;
    private String moduleId;

    private String linkName;
    private String auditRefNum;
    private String modifiedFieldsData;
    private String remarks;
    private String createdBy;
    private String createdOn;
    private String modifiedBy;
    private String modifiedOn;
    private String moduleName;

    private String sortField;
    private String sortOrder;
    private int pageNo;
    private int maxPages;
    private String searchValue;
    private int pageSize;
    private String downloadType;


    private String isDownload;


    private List<Map<String, Object>> data;


    public String getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(String downloadType) {
        this.downloadType = downloadType;
    }

    public String getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(String isDownload) {
        this.isDownload = isDownload;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getMaxPages() {
        return maxPages;
    }

    public void setMaxPages(int maxPages) {
        this.maxPages = maxPages;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getModifiedFieldsData() {
        return modifiedFieldsData;
    }

    public void setModifiedFieldsData(String modifiedFieldsData) {
        this.modifiedFieldsData = modifiedFieldsData;
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

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getAuthorizedBy() {
        return authorizedBy;
    }

    public void setAuthorizedBy(String authorizedBy) {
        this.authorizedBy = authorizedBy;
    }

    public String getAuthorizedOn() {
        return authorizedOn;
    }

    public void setAuthorizedOn(String authorizedOn) {
        this.authorizedOn = authorizedOn;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public String getDeletedOn() {
        return deletedOn;
    }

    public void setDeletedOn(String deletedOn) {
        this.deletedOn = deletedOn;
    }

    public String getModuleAccountNumber() {
        return moduleAccountNumber;
    }

    public void setModuleAccountNumber(String moduleAccountNumber) {
        this.moduleAccountNumber = moduleAccountNumber;
    }

    public String getNameoftheTreasury() {
        return nameoftheTreasury;
    }

    public void setNameoftheTreasury(String nameoftheTreasury) {
        this.nameoftheTreasury = nameoftheTreasury;
    }

    private String authorizedBy;
    private String authorizedOn;
    private String deletedBy;
    private String deletedOn;
    private String moduleAccountNumber;
    private String nameoftheTreasury ;







    }
