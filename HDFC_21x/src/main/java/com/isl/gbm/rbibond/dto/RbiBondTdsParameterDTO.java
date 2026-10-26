package com.isl.gbm.rbibond.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.isl.gbm.common.dto.ColumnValueDTO;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RbiBondTdsParameterDTO {

    private String effFromDate;
    private String moduleId;
    private String moduleName;
    private String tdsParamCode;
    private String tdsParamCodeDesc;
    private String versionNo;
    private String rejectionRemarks;
    private String fifteenGlimit;
    private String fifteenHlimit;



    private String exceptionForSeniorCitizen;
    private String exceptionForOthers;
    private String tdsRateWithPan;
    private String tdsRateWithoutPan;

    //private String tdsAnnualInterest;
   // private String tdsAnnualInterestless;
//    private String tdsRatePan;
//    private String tdsRateNoPan;
    private String educationCess;
    private String otherCess;
    private String remark;
    private String id;



    private String createdBy;
    private String createdDate;
    private String modifiedBy;
    private String modifiedDate;
    private String verifiedBy;
    private String verifiedDate;
    private String verifiedStatus;
    private String prevOperType;
    private List<HashMap<String, String>> tdsCode;
    private List<HashMap<String, String>> versionNoList;
    private List<Map<String, Object>> oldNewValueList;
    private List<TdsParameterDTO> modifyValueList;
    private String pkColumnValue;

    @Valid
    private List<ColumnValueDTO> columnValues;

    private Map<String, Object> masterOldNewValueModel;

    public List<ColumnValueDTO> getColumnValues() {
        return columnValues;
    }

    public void setColumnValues(List<ColumnValueDTO> columnValues) {
        this.columnValues = columnValues;
    }

    public Map<String, Object> getMasterOldNewValueModel() {
        return masterOldNewValueModel;
    }

    public void setMasterOldNewValueModel(Map<String, Object> masterOldNewValueModel) {
        this.masterOldNewValueModel = masterOldNewValueModel;
    }

    public String getPkColumnValue() {
        return pkColumnValue;
    }

    public void setPkColumnValue(String pkColumnValue) {
        this.pkColumnValue = pkColumnValue;
    }

    public String getExceptionForSeniorCitizen() {
        return exceptionForSeniorCitizen;
    }

    public void setExceptionForSeniorCitizen(String exceptionForSeniorCitizen) {
        this.exceptionForSeniorCitizen = exceptionForSeniorCitizen;
    }

    public String getFifteenGlimit() {
        return fifteenGlimit;
    }

    public void setFifteenGlimit(String fifteenGlimit) {
        this.fifteenGlimit = fifteenGlimit;
    }

    public String getFifteenHlimit() {
        return fifteenHlimit;
    }

    public void setFifteenHlimit(String fifteenHlimit) {
        this.fifteenHlimit = fifteenHlimit;
    }

    public String getRejectionRemarks() {
        return rejectionRemarks;
    }

    public void setRejectionRemarks(String rejectionRemarks) {
        this.rejectionRemarks = rejectionRemarks;
    }

    public String getExceptionForOthers() {
        return exceptionForOthers;
    }

    public void setExceptionForOthers(String exceptionForOthers) {
        this.exceptionForOthers = exceptionForOthers;
    }

    public String getTdsRateWithPan() {
        return tdsRateWithPan;
    }

    public void setTdsRateWithPan(String tdsRateWithPan) {
        this.tdsRateWithPan = tdsRateWithPan;
    }

    public String getTdsRateWithoutPan() {
        return tdsRateWithoutPan;
    }

    public void setTdsRateWithoutPan(String tdsRateWithoutPan) {
        this.tdsRateWithoutPan = tdsRateWithoutPan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEffFromDate() {
        return effFromDate;
    }

    public void setEffFromDate(String effFromDate) {
        this.effFromDate = effFromDate;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getTdsParamCode() {
        return tdsParamCode;
    }

    public void setTdsParamCode(String tdsParamCode) {
        this.tdsParamCode = tdsParamCode;
    }

    public String getTdsParamCodeDesc() {
        return tdsParamCodeDesc;
    }

    public void setTdsParamCodeDesc(String tdsParamCodeDesc) {
        this.tdsParamCodeDesc = tdsParamCodeDesc;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }


    public String getEducationCess() {
        return educationCess;
    }

    public void setEducationCess(String educationCess) {
        this.educationCess = educationCess;
    }

    public String getOtherCess() {
        return otherCess;
    }

    public void setOtherCess(String otherCess) {
        this.otherCess = otherCess;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
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

    public String getPrevOperType() {
        return prevOperType;
    }

    public void setPrevOperType(String prevOperType) {
        this.prevOperType = prevOperType;
    }

    public List<HashMap<String, String>> getTdsCode() {
        return tdsCode;
    }

    public void setTdsCode(List<HashMap<String, String>> tdsCode) {
        this.tdsCode = tdsCode;
    }

    public List<HashMap<String, String>> getVersionNoList() {
        return versionNoList;
    }

    public void setVersionNoList(List<HashMap<String, String>> versionNoList) {
        this.versionNoList = versionNoList;
    }

    public List<Map<String, Object>> getOldNewValueList() {
        return oldNewValueList;
    }

    public void setOldNewValueList(List<Map<String, Object>> oldNewValueList) {
        this.oldNewValueList = oldNewValueList;
    }

    public List<TdsParameterDTO> getModifyValueList() {
        return modifyValueList;
    }

    public void setModifyValueList(List<TdsParameterDTO> modifyValueList) {
        this.modifyValueList = modifyValueList;
    }

    @Override
    public String toString() {
        return "RbiBondTdsParameterDTO{" +
                "effFromDate='" + effFromDate + '\'' +
                ", moduleId='" + moduleId + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", tdsParamCode='" + tdsParamCode + '\'' +
                ", tdsParamCodeDesc='" + tdsParamCodeDesc + '\'' +
                ", versionNo='" + versionNo + '\'' +
                ", rejectionRemarks='" + rejectionRemarks + '\'' +
                ", fifteenGlimit='" + fifteenGlimit + '\'' +
                ", fifteenHlimit='" + fifteenHlimit + '\'' +
                ", exceptionForSeniorCitizen='" + exceptionForSeniorCitizen + '\'' +
                ", exceptionForOthers='" + exceptionForOthers + '\'' +
                ", tdsRateWithPan='" + tdsRateWithPan + '\'' +
                ", tdsRateWithoutPan='" + tdsRateWithoutPan + '\'' +
                ", educationCess='" + educationCess + '\'' +
                ", otherCess='" + otherCess + '\'' +
                ", remark='" + remark + '\'' +
                ", id='" + id + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", modifiedDate='" + modifiedDate + '\'' +
                ", verifiedBy='" + verifiedBy + '\'' +
                ", verifiedDate='" + verifiedDate + '\'' +
                ", verifiedStatus='" + verifiedStatus + '\'' +
                ", prevOperType='" + prevOperType + '\'' +
                ", tdsCode=" + tdsCode +
                ", versionNoList=" + versionNoList +
                ", oldNewValueList=" + oldNewValueList +
                ", modifyValueList=" + modifyValueList +
                ", pkColumnValue='" + pkColumnValue + '\'' +
                ", columnValues=" + columnValues +
                ", masterOldNewValueModel=" + masterOldNewValueModel +
                '}';
    }
}
