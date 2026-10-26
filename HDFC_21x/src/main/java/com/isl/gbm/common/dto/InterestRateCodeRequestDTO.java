package com.isl.gbm.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InterestRateCodeRequestDTO {

    private String id;

    private String sNo;

    private String branchId;

    private String branchDescription;

    private String moduleName;

    private String interestRateCode;

    private String interestRateCodeDescription;

    private String version;

    private String effectiveFromDate;

    private String effectiveToDate;

    private String interestRate;

    private String remarks;

    private String createdBy;

    private String createdDate;

    private String modifiedBy;

    private String modifiedDate;

    private String verifiedBy;

    private String verifiedDate;

    private String isDeleted;

    private String isActive;

    private String verifiedStatus;

    private String previousOperationType;

    private String isVersionFlag;

    private String moduleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private List<Map<String, Object>> kvpInterestRateCodeMaintenanceList;

    private List<Map<String, Object>> kvpInterestRateCodeTorchList;

    private List<Map<String, String>> versionTorch;

    private List<Map<String, String>> oldList;

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo;
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

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getInterestRateCode() {
        return interestRateCode;
    }

    public void setInterestRateCode(String interestRateCode) {
        this.interestRateCode = interestRateCode;
    }

    public String getInterestRateCodeDescription() {
        return interestRateCodeDescription;
    }

    public void setInterestRateCodeDescription(String interestRateCodeDescription) {
        this.interestRateCodeDescription = interestRateCodeDescription;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEffectiveFromDate() {
        return effectiveFromDate;
    }

    public void setEffectiveFromDate(String effectiveFromDate) {
        this.effectiveFromDate = effectiveFromDate;
    }

    public String getEffectiveToDate() {
        return effectiveToDate;
    }

    public void setEffectiveToDate(String effectiveToDate) {
        this.effectiveToDate = effectiveToDate;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
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

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setVerifiedStatus(String verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    public String getPreviousOperationType() {
        return previousOperationType;
    }

    public void setPreviousOperationType(String previousOperationType) {
        this.previousOperationType = previousOperationType;
    }

    public String getIsVersionFlag() {
        return isVersionFlag;
    }

    public void setIsVersionFlag(String isVersionFlag) {
        this.isVersionFlag = isVersionFlag;
    }

    public List<Map<String, Object>> getKvpInterestRateCodeMaintenanceList() {
        return kvpInterestRateCodeMaintenanceList;
    }

    public void setKvpInterestRateCodeMaintenanceList(List<Map<String, Object>> kvpInterestRateCodeMaintenanceList) {
        this.kvpInterestRateCodeMaintenanceList = kvpInterestRateCodeMaintenanceList;
    }

    public List<Map<String, Object>> getKvpInterestRateCodeTorchList() {
        return kvpInterestRateCodeTorchList;
    }

    public void setKvpInterestRateCodeTorchList(List<Map<String, Object>> kvpInterestRateCodeTorchList) {
        this.kvpInterestRateCodeTorchList = kvpInterestRateCodeTorchList;
    }

    public List<Map<String, String>> getVersionTorch() {
        return versionTorch;
    }

    public void setVersionTorch(List<Map<String, String>> versionTorch) {
        this.versionTorch = versionTorch;
    }

    public List<Map<String, String>> getOldList() {
        return oldList;
    }

    public void setOldList(List<Map<String, String>> oldList) {
        this.oldList = oldList;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }
}
