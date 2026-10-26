package com.isl.gbm.cbs.integration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriberDTO {

    private String cbsCustId;

    private String apyCustId;

    private String custIdFlag;

    private String siRegNo;

    private String siExeDate;

    private String siEnableFlag;

    private String remarks;

    private String bankCode;

    private String micrCode;

    private String ifscCode;

    private String regDate;

    private String branchName;

    private String isCraRejected;

    public String getIsCraRejected() {
        return isCraRejected;
    }

    public void setIsCraRejected(String isCraRejected) {
        this.isCraRejected = isCraRejected;
    }

    private List<SubscriberDetailsDTO> subscriberDetails;

    public List<SubscriberDetailsDTO> getSubscriberDetails() {
        return subscriberDetails;
    }

    public void setSubscriberDetails(List<SubscriberDetailsDTO> subscriberDetails) {
        this.subscriberDetails = subscriberDetails;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getMicrCode() {
        return micrCode;
    }

    public void setMicrCode(String micrCode) {
        this.micrCode = micrCode;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getSiEnableFlag() {
        return siEnableFlag;
    }

    public void setSiEnableFlag(String siEnableFlag) {
        this.siEnableFlag = siEnableFlag;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSiRegNo() {
        return siRegNo;
    }

    public void setSiRegNo(String siRegNo) {
        this.siRegNo = siRegNo;
    }

    public String getSiExeDate() {
        return siExeDate;
    }

    public void setSiExeDate(String siExeDate) {
        this.siExeDate = siExeDate;
    }

    private String createdBy;

    private String createdDate;

    private String modifiedBy;

    private String modifiedDate;

    private String verifiedBy;

    private String verifiedDate;

    private String verifyStatus;

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
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

    public String getCbsCustId() {
        return cbsCustId;
    }

    public void setCbsCustId(String cbsCustId) {
        this.cbsCustId = cbsCustId;
    }

    public String getApyCustId() {
        return apyCustId;
    }

    public void setApyCustId(String apyCustId) {
        this.apyCustId = apyCustId;
    }

    public List<com.isl.gbm.common.dto.ColumnValueDTO> getValueList() {
        return valueList;
    }

    public void setValueList(List<com.isl.gbm.common.dto.ColumnValueDTO> valueList) {
        this.valueList = valueList;
    }

    private List<com.isl.gbm.common.dto.ColumnValueDTO> valueList;

    private List<com.isl.gbm.common.dto.ColumnValueDTO> valueTransferList;

    private List<PropertyValueDTO> propertyList;

    private Map<String, String> valueMap;

    private Map<String, Object> oldNewValueMap;

    public Map<String, String> getValueMap() {
        return valueMap;
    }

    public void setValueMap(Map<String, String> valueMap) {
        this.valueMap = valueMap;
    }

    public Map<String, Object> getOldNewValueMap() {
        return oldNewValueMap;
    }

    public void setOldNewValueMap(Map<String, Object> oldNewValueMap) {
        this.oldNewValueMap = oldNewValueMap;
    }

    private Map<String, Object> siDetails;

    private String preOpr;

    private List<Map<String, Object>> masterDetailsValueModel;

    private List<Map<String, Object>> pranDetailsValueModel;

    public List<Map<String, Object>> getMasterDetailsValueModel() {
        return masterDetailsValueModel;
    }

    public void setMasterDetailsValueModel(List<Map<String, Object>> masterDetailsValueModel) {
        this.masterDetailsValueModel = masterDetailsValueModel;
    }

    public List<Map<String, Object>> getPranDetailsValueModel() {
        return pranDetailsValueModel;
    }

    public void setPranDetailsValueModel(List<Map<String, Object>> pranDetailsValueModel) {
        this.pranDetailsValueModel = pranDetailsValueModel;
    }

    public Map<String, Object> getSiDetails() {
        return siDetails;
    }

    public void setSiDetails(Map<String, Object> siDetails) {
        this.siDetails = siDetails;
    }

    public String getPreOpr() {
        return preOpr;
    }

    public void setPreOpr(String preOpr) {
        this.preOpr = preOpr;
    }


    public List<PropertyValueDTO> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<PropertyValueDTO> propertyList) {
        this.propertyList = propertyList;
    }

    public List<com.isl.gbm.common.dto.ColumnValueDTO> getValueTransferList() {
        return valueTransferList;
    }

    public void setValueTransferList(List<com.isl.gbm.common.dto.ColumnValueDTO> valueTransferList) {
        this.valueTransferList = valueTransferList;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getCustIdFlag() {
        return custIdFlag;
    }

    public void setCustIdFlag(String custIdFlag) {
        this.custIdFlag = custIdFlag;
    }
}
