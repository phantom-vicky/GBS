package com.isl.gbm.common.dto;

import java.util.List;

public class LevelMasterDto {

    private String schemeCode;

    private String levelsCount;

    private List<List<ColumnValueDTO>> levelHierachyList;

    private List<LevelMasterSubDto> levelMasterSubDtoList;

    private String createdBy;

    private String createdDate;

    private String verifiedBy;

    private String verifiedDate;

    private String modifiedBy;

    private String modifiedDate;

    private String verifiedStatus;

    private String operationType;

    private String stateTaxCode;
    private String remarks;

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getLevelsCount() {
        return levelsCount;
    }

    public void setLevelsCount(String levelsCount) {
        this.levelsCount = levelsCount;
    }

    public List<List<ColumnValueDTO>> getLevelHierachyList() {
        return levelHierachyList;
    }

    public void setLevelHierachyList(List<List<ColumnValueDTO>> levelHierachyList) {
        this.levelHierachyList = levelHierachyList;
    }

    public List<LevelMasterSubDto> getLevelMasterSubDtoList() {
        return levelMasterSubDtoList;
    }

    public void setLevelMasterSubDtoList(List<LevelMasterSubDto> levelMasterSubDtoList) {
        this.levelMasterSubDtoList = levelMasterSubDtoList;
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

    public String getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setVerifiedStatus(String verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getStateTaxCode() {
        return stateTaxCode;
    }

    public void setStateTaxCode(String stateTaxCode) {
        this.stateTaxCode = stateTaxCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
