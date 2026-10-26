package com.isl.gbm.common.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TocSlabDetailsDto {

    private String schemeCode;

    private String versionNo;

    private String effeFromDate;
    private String rejectionRemarks;

    private String roundOff;

    private String challanType;

    public String getRejectionRemarks() {
        return rejectionRemarks;
    }

    public void setRejectionRemarks(String rejectionRemarks) {
        this.rejectionRemarks = rejectionRemarks;
    }

    private List<HashMap<String, String>> schemeTorchList;

    public List<HashMap<String, String>> getSchemeTorchList() {
        return schemeTorchList;
    }

    public void setSchemeTorchList(List<HashMap<String, String>> schemeTorchList) {
        this.schemeTorchList = schemeTorchList;
    }

    public String getBankGstTinNo() {
        return bankGstTinNo;
    }

    public void setBankGstTinNo(String bankGstTinNo) {
        this.bankGstTinNo = bankGstTinNo;
    }

    public String getCgst() {
        return cgst;
    }

    public void setCgst(String cgst) {
        this.cgst = cgst;
    }

    public String getSgst() {
        return sgst;
    }

    public void setSgst(String sgst) {
        this.sgst = sgst;
    }

    public List<TocSlabSubDetailsDTO> getTocSlabSubDetailsDTOList() {
        return tocSlabSubDetailsDTOList;
    }

    public void setTocSlabSubDetailsDTOList(List<TocSlabSubDetailsDTO> tocSlabSubDetailsDTOList) {
        this.tocSlabSubDetailsDTOList = tocSlabSubDetailsDTOList;
    }

    private String bankGstTinNo;
    private String cgst;
    private String sgst;

    private List<TocSlabSubDetailsDTO> tocSlabSubDetailsDTOList;

    private List<ColumnValueDTO> moduleDetailsList;

    private List<List<ColumnValueDTO>> columnValueDetailsList;

    private String createdBy;

    private String createdDate;

    private String verifiedStatus;

    private String modifiedBy;

    private String modifiedDate;

    private String verifiedBy;

    private String verifiedDate;

    private List schemCodeList;

    private List effeFromDateList;

    private String previousOperationType;

    private List<Map<String, Object>> oldNewValueList;

    private List<HashMap<String, String>> levelsOut;

    private List<HashMap<String, String>> versionOut;

    private String remarks;

    private String effectiveFromDateOut;

    private List<Integer> ids;

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getEffeFromDate() {
        return effeFromDate;
    }

    public void setEffeFromDate(String effeFromDate) {
        this.effeFromDate = effeFromDate;
    }

    public String getRoundOff() {
        return roundOff;
    }

    public void setRoundOff(String roundOff) {
        this.roundOff = roundOff;
    }

    public List<TocSlabSubDetailsDTO> getSlabSubDetailsDTOList() {
        return tocSlabSubDetailsDTOList;
    }

    public void setSlabSubDetailsDTOList(List<TocSlabSubDetailsDTO> slabSubDetailsDTOList) {
        this.tocSlabSubDetailsDTOList = slabSubDetailsDTOList;
    }

    public String getChallanType() {
        return challanType;
    }

    public void setChallanType(String challanType) {
        this.challanType = challanType;
    }


    public List<ColumnValueDTO> getModuleDetailsList() {
        return moduleDetailsList;
    }

    public void setModuleDetailsList(List<ColumnValueDTO> moduleDetailsList) {
        this.moduleDetailsList = moduleDetailsList;
    }

    public List<List<ColumnValueDTO>> getColumnValueDetailsList() {
        return columnValueDetailsList;
    }

    public void setColumnValueDetailsList(List<List<ColumnValueDTO>> columnValueDetailsList) {
        this.columnValueDetailsList = columnValueDetailsList;
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

    public String getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setVerifiedStatus(String verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
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

    public List getSchemCodeList() {
        return schemCodeList;
    }

    public void setSchemCodeList(List schemCodeList) {
        this.schemCodeList = schemCodeList;
    }

    public List getEffeFromDateList() {
        return effeFromDateList;
    }

    public void setEffeFromDateList(List effeFromDateList) {
        this.effeFromDateList = effeFromDateList;
    }

    public String getPreviousOperationType() {
        return previousOperationType;
    }

    public void setPreviousOperationType(String previousOperationType) {
        this.previousOperationType = previousOperationType;
    }

    public List<Map<String, Object>> getOldNewValueList() {
        return oldNewValueList;
    }

    public void setOldNewValueList(List<Map<String, Object>> oldNewValueList) {
        this.oldNewValueList = oldNewValueList;
    }

    public List<HashMap<String, String>> getLevelsOut() {
        return levelsOut;
    }

    public void setLevelsOut(List<HashMap<String, String>> levelsOut) {
        this.levelsOut = levelsOut;
    }

    public List<HashMap<String, String>> getVersionOut() {
        return versionOut;
    }

    public void setVersionOut(List<HashMap<String, String>> versionOut) {
        this.versionOut = versionOut;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getEffectiveFromDateOut() {
        return effectiveFromDateOut;
    }

    public void setEffectiveFromDateOut(String effectiveFromDateOut) {
        this.effectiveFromDateOut = effectiveFromDateOut;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
