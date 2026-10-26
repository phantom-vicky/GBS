package com.isl.gbm.rbibond.dto.reports;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BondDailyRemLcDTO {

    private String schemeCode;
    private String blaNo;
    private String holderName;
    private String subsId;
    private String culAmount;
    private String nonCulAmount;
    private String total;
    private String totCulAmt;
    private String totNonCulAmt;
    private String totAmt;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getBlaNo() {
        return blaNo;
    }

    public void setBlaNo(String blaNo) {
        this.blaNo = blaNo;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getSubsId() {
        return subsId;
    }

    public void setSubsId(String subsId) {
        this.subsId = subsId;
    }

    public String getCulAmount() {
        return culAmount;
    }

    public void setCulAmount(String culAmount) {
        this.culAmount = culAmount;
    }

    public String getNonCulAmount() {
        return nonCulAmount;
    }

    public void setNonCulAmount(String nonCulAmount) {
        this.nonCulAmount = nonCulAmount;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotCulAmt() {
        return totCulAmt;
    }

    public void setTotCulAmt(String totCulAmt) {
        this.totCulAmt = totCulAmt;
    }

    public String getTotNonCulAmt() {
        return totNonCulAmt;
    }

    public void setTotNonCulAmt(String totNonCulAmt) {
        this.totNonCulAmt = totNonCulAmt;
    }

    public String getTotAmt() {
        return totAmt;
    }

    public void setTotAmt(String totAmt) {
        this.totAmt = totAmt;
    }
}
