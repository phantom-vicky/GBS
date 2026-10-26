package com.isl.gbm.rbibond.dto.scrolls;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BondPrincipalRepayDTO {

    private String branchId;
    private String branchName;
    private String schemeCode;
    private String fromDate;
    private String toDate;
    private String sNo;
    private String blaNo;
    private String dueDateRepay;
    private String dateOfRepayment;
    private String amtOfRepay;
    private String creditBankAcc;
    private String remarks;
    private String initials;
    private String grandTotal;

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo;
    }

    public String getBlaNo() {
        return blaNo;
    }

    public void setBlaNo(String blaNo) {
        this.blaNo = blaNo;
    }

    public String getDueDateRepay() {
        return dueDateRepay;
    }

    public void setDueDateRepay(String dueDateRepay) {
        this.dueDateRepay = dueDateRepay;
    }

    public String getDateOfRepayment() {
        return dateOfRepayment;
    }

    public void setDateOfRepayment(String dateOfRepayment) {
        this.dateOfRepayment = dateOfRepayment;
    }

    public String getAmtOfRepay() {
        return amtOfRepay;
    }

    public void setAmtOfRepay(String amtOfRepay) {
        this.amtOfRepay = amtOfRepay;
    }

    public String getCreditBankAcc() {
        return creditBankAcc;
    }

    public void setCreditBankAcc(String creditBankAcc) {
        this.creditBankAcc = creditBankAcc;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }
}
