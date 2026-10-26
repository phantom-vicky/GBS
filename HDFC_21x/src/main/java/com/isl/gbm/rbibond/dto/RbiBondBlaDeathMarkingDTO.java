package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RbiBondBlaDeathMarkingDTO {



    private String PanNo;
    private String PanId;
    private String schemeCode;
    private String bondHolderName;
    private String  dodDate;
    private String  certDate;
    private String  markingDate;
    private String  remarks;
    private String createdBy;
    private String createdDate;
    private String verifiedBy;
    private String verifiedDate;

    public void setPanNo(String panNo) {
        PanNo = panNo;
    }

    public String getRejectRemarks() {
        return rejectRemarks;
    }

    public void setRejectRemarks(String rejectRemarks) {
        this.rejectRemarks = rejectRemarks;
    }

    private String verifiedStatus;
    private String  rejectRemarks;
    private List<Map<String,String>> holderDetails=new ArrayList<Map<String,String>>();


    public String getPanNo() {

        return PanNo;
    }

    public void setPanNumber(String PanNo) {

        this.PanNo = PanNo;
    }

    public String getBondHolderName() {
        return bondHolderName;
    }

    public void setBondHolderName(String bondHolderName) {
        this.bondHolderName = bondHolderName;
    }

    public String getDodDate() {
        return dodDate;
    }

    public void setDodDate(String dodDate) {
        this.dodDate = dodDate;
    }

    public String getCertDate() {
        return certDate;
    }

    public void setCertDate(String certDate) {
        this.certDate = certDate;
    }

    public String getMarkingDate() {
        return markingDate;
    }

    public void setMarkingDate(String markingDate) {
        this.markingDate = markingDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPanId() {
        return PanId;
    }

    public void setPanId(String panId) {
        PanId = panId;
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

    public String getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setVerifiedStatus(String verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }


    public List<Map<String, String>> getHolderDetails() {
        return holderDetails;
    }

    public void setHolderDetails(List<Map<String, String>> holderDetails) {
        this.holderDetails = holderDetails;
    }


    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }
}
