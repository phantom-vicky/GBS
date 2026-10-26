package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PreMaturityRequestDTO {

    private String schemeCode;
    private String schemeName;
    private String blaNo;
    private String panNo;
    private String flag;
    private String bondHolderName;
    private String totalAmt;
    private String rejectionRemarks;
    private List<PreMaturityListDTO> preMaturityList;
    private List<String> listOfString;

    public String getRejectionRemarks() {
        return rejectionRemarks;
    }

    public void setRejectionRemarks(String rejectionRemarks) {
        this.rejectionRemarks = rejectionRemarks;
    }

    public List<String> getListOfString() {
        return listOfString;
    }

    public void setListOfString(List<String> listOfString) {
        this.listOfString = listOfString;
    }

    public String getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(String totalAmt) {
        this.totalAmt = totalAmt;
    }

    public List<PreMaturityListDTO> getPreMaturityList() {
        return preMaturityList;
    }

    public void setPreMaturityList(List<PreMaturityListDTO> preMaturityList) {
        this.preMaturityList = preMaturityList;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getBlaNo() {
        return blaNo;
    }

    public void setBlaNo(String blaNo) {
        this.blaNo = blaNo;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getBondHolderName() {
        return bondHolderName;
    }

    public void setBondHolderName(String bondHolderName) {
        this.bondHolderName = bondHolderName;
    }

    @Override
    public String toString() {
        return "PreMaturityRequestDTO{" +
                "schemeCode='" + schemeCode + '\'' +
                ", schemeName='" + schemeName + '\'' +
                ", blaNo='" + blaNo + '\'' +
                ", panNo='" + panNo + '\'' +
                ", bondHolderName='" + bondHolderName + '\'' +
                ", preMaturityList=" + preMaturityList +
                '}';
    }
}
