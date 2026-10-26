package com.isl.gbm.rbibond.dto.reports;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BondPrecloserRepayReportDTO {
    private String fromDate;
    private String toDate;
    private String schemeName;
    private String schemeCode;
    private String panNo;
    private String blaNo;
    private String holderName;
    private String subscriptionId;
    private String subscriptionDate;
    private String subscriptionAmt;
    private String dueDateRepay;
    private String datePreclosRepay;
    private String grossAmt;
    private String tdsAmt;
    private String tdsPercentage;
    private String repayAmt;
    private String creditAccNo;
    private String status;
    private String flag;
    private String pageNo;
    private String pageSize;
    private String searchValue;
    private String sortOrder;
    private  String  makerId;
    private  String  makerDate;
    private  String  checkerId;
    private  String  checkerDate;
private String casDate;
private String oldBondSubscriptionId;
private  String  oldBlaNo;
private String investmentHoldingYear;

    public String getCasDate() {
        return casDate;
    }

    public void setCasDate(String casDate) {
        this.casDate = casDate;
    }

    public String getInvestmentHoldingYear() {
        return investmentHoldingYear;
    }

    public void setInvestmentHoldingYear(String investmentHoldingYear) {
        this.investmentHoldingYear = investmentHoldingYear;
    }

    public String getOldBlaNo() {
        return oldBlaNo;
    }

    public void setOldBlaNo(String oldBlaNo) {
        this.oldBlaNo = oldBlaNo;
    }

    public String getOldBondSubscriptionId() {
        return oldBondSubscriptionId;
    }

    public void setOldBondSubscriptionId(String oldBondSubscriptionId) {
        this.oldBondSubscriptionId = oldBondSubscriptionId;
    }

    public String getCheckerDate() {
        return checkerDate;
    }

    public void setCheckerDate(String checkerDate) {
        this.checkerDate = checkerDate;
    }

    public String getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(String checkerId) {
        this.checkerId = checkerId;
    }

    public String getMakerDate() {
        return makerDate;
    }

    public void setMakerDate(String makerDate) {
        this.makerDate = makerDate;
    }

    public String getMakerId() {
        return makerId;
    }

    public void setMakerId(String makerId) {
        this.makerId = makerId;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    private String sortField;

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

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
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

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(String subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public String getSubscriptionAmt() {
        return subscriptionAmt;
    }

    public void setSubscriptionAmt(String subscriptionAmt) {
        this.subscriptionAmt = subscriptionAmt;
    }

    public String getDueDateRepay() {
        return dueDateRepay;
    }

    public void setDueDateRepay(String dueDateRepay) {
        this.dueDateRepay = dueDateRepay;
    }

    public String getDatePreclosRepay() {
        return datePreclosRepay;
    }

    public void setDatePreclosRepay(String datePreclosRepay) {
        this.datePreclosRepay = datePreclosRepay;
    }

    public String getGrossAmt() {
        return grossAmt;
    }

    public void setGrossAmt(String grossAmt) {
        this.grossAmt = grossAmt;
    }

    public String getTdsAmt() {
        return tdsAmt;
    }

    public void setTdsAmt(String tdsAmt) {
        this.tdsAmt = tdsAmt;
    }

    public String getTdsPercentage() {
        return tdsPercentage;
    }

    public void setTdsPercentage(String tdsPercentage) {
        this.tdsPercentage = tdsPercentage;
    }

    public String getRepayAmt() {
        return repayAmt;
    }

    public void setRepayAmt(String repayAmt) {
        this.repayAmt = repayAmt;
    }

    public String getCreditAccNo() {
        return creditAccNo;
    }

    public void setCreditAccNo(String creditAccNo) {
        this.creditAccNo = creditAccNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
