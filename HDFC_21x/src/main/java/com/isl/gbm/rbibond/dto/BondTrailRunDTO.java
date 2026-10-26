package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BondTrailRunDTO {

    private String schCode;
    private String panNo;
    private String blaNo;
    private String subId;
    private String subAmt;
    private String accOpeningDate;
    private String rateOfInterest;
    private String interestAmt;

    @Override
    public String toString() {
        return "BondTrailRunDTO{" +
                "schCode='" + schCode + '\'' +
                ", panNo='" + panNo + '\'' +
                ", blaNo='" + blaNo + '\'' +
                ", subId='" + subId + '\'' +
                ", subAmt='" + subAmt + '\'' +
                ", accOpeningDate='" + accOpeningDate + '\'' +
                ", rateOfInterest='" + rateOfInterest + '\'' +
                ", interestAmt='" + interestAmt + '\'' +
                '}';
    }

    public String getSchCode() {
        return schCode;
    }

    public void setSchCode(String schCode) {
        this.schCode = schCode;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getBlaNo() {
        return blaNo;
    }

    public void setBlaNo(String blaNo) {
        this.blaNo = blaNo;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getSubAmt() {
        return subAmt;
    }

    public void setSubAmt(String subAmt) {
        this.subAmt = subAmt;
    }

    public String getAccOpeningDate() {
        return accOpeningDate;
    }

    public void setAccOpeningDate(String accOpeningDate) {
        this.accOpeningDate = accOpeningDate;
    }

    public String getRateOfInterest() {
        return rateOfInterest;
    }

    public void setRateOfInterest(String rateOfInterest) {
        this.rateOfInterest = rateOfInterest;
    }

    public String getInterestAmt() {
        return interestAmt;
    }

    public void setInterestAmt(String interestAmt) {
        this.interestAmt = interestAmt;
    }

}
