package com.isl.gbm.rbibond.dto.reports;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlaStatusReportDTO {
    private String toDate;
    private String fromDate;
    private String NameoftheBondScheme;
    private String subcriptionamount;

    public String getSubcriptionamount() {
        return subcriptionamount;
    }

    public void setSubcriptionamount(String subcriptionamount) {
        this.subcriptionamount = subcriptionamount;
    }

    private String schemeCode;
    private String dateofsubscription;
    private String blanumber;
    private String holdername;
    private String subcriptionId;
    private String bondtype;
    private String maturitydate;
    private String total;
    private String modeofholding;
    private String p_sub_sts_in;
    private String subcriptionstatus;

    public String getSubcriptionstatus() {
        return subcriptionstatus;
    }

    public void setSubcriptionstatus(String subcriptionstatus) {
        this.subcriptionstatus = subcriptionstatus;
    }

    public String getP_sub_sts_in() {
        return p_sub_sts_in;
    }

    public void setP_sub_sts_in(String p_sub_sts_in) {
        this.p_sub_sts_in = p_sub_sts_in;
    }

    public String getModeofholding() {
        return modeofholding;
    }

    public void setModeofholding(String modeofholding) {
        this.modeofholding = modeofholding;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDateofsubscription() {
        return dateofsubscription;
    }

    public void setDateofsubscription(String dateofsubscription) {
        this.dateofsubscription = dateofsubscription;
    }

    public String getBlanumber() {
        return blanumber;
    }

    public void setBlanumber(String blanumber) {
        this.blanumber = blanumber;
    }

    public String getHoldername() {
        return holdername;
    }

    public void setHoldername(String holdername) {
        this.holdername = holdername;
    }

    public String getSubcriptionId() {
        return subcriptionId;
    }

    public void setSubcriptionId(String subcriptionId) {
        this.subcriptionId = subcriptionId;
    }

    public String getBondtype() {
        return bondtype;
    }

    public void setBondtype(String bondtype) {
        this.bondtype = bondtype;
    }

    public String getMaturitydate() {
        return maturitydate;
    }

    public void setMaturitydate(String maturitydate) {
        this.maturitydate = maturitydate;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getNameoftheBondScheme() {
        return NameoftheBondScheme;
    }

    public void setNameoftheBondScheme(String nameoftheBondScheme) {
        NameoftheBondScheme = nameoftheBondScheme;
    }

}
