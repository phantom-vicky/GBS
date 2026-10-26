package com.isl.gbm.rbibond.dto.reports;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TdsDeductionDTO {

    private String schemeName;
    private String schemeCode;
    private String schemestatus;
    private String panNo;
    private String blaNo;
    private String customerName;
    private String subsId;
    private String financialYear;
    private String typeofCertificate;
    private String taxExcemlimit;
    private String lownotaxper;
    private String panStatus;
    private String interestPaymentDate;
    private String totalInterest;
    private String tdsAmt;
    private String tdsDeductionper;
    private String tdsDeductionAmt;
    private String netInterestAmt;
    private String flag;
    private String holderName;
    private String modeofHolding;
    private String pageNo;
    private String pageSize;
    private String searchValue;
    private String sortOrder;
    private String sortField;
    private String downloadType;

    public String getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(String downloadType) {
        this.downloadType = downloadType;
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

    public String getSchemestatus() {
        return schemestatus;
    }

    public void setSchemestatus(String schemestatus) {
        this.schemestatus = schemestatus;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSubsId() {
        return subsId;
    }

    public void setSubsId(String subsId) {
        this.subsId = subsId;
    }

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public String getTypeofCertificate() {
        return typeofCertificate;
    }

    public void setTypeofCertificate(String typeofCertificate) {
        this.typeofCertificate = typeofCertificate;
    }

    public String getTaxExcemlimit() {
        return taxExcemlimit;
    }

    public void setTaxExcemlimit(String taxExcemlimit) {
        this.taxExcemlimit = taxExcemlimit;
    }

    public String getLownotaxper() {
        return lownotaxper;
    }

    public void setLownotaxper(String lownotaxper) {
        this.lownotaxper = lownotaxper;
    }

    public String getPanStatus() {
        return panStatus;
    }

    public void setPanStatus(String panStatus) {
        this.panStatus = panStatus;
    }

    public String getInterestPaymentDate() {
        return interestPaymentDate;
    }

    public void setInterestPaymentDate(String interestPaymentDate) {
        this.interestPaymentDate = interestPaymentDate;
    }

    public String getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(String totalInterest) {
        this.totalInterest = totalInterest;
    }

    public String getTdsAmt() {
        return tdsAmt;
    }

    public void setTdsAmt(String tdsAmt) {
        this.tdsAmt = tdsAmt;
    }

    public String getTdsDeductionper() {
        return tdsDeductionper;
    }

    public void setTdsDeductionper(String tdsDeductionper) {
        this.tdsDeductionper = tdsDeductionper;
    }

    public String getTdsDeductionAmt() {
        return tdsDeductionAmt;
    }

    public void setTdsDeductionAmt(String tdsDeductionAmt) {
        this.tdsDeductionAmt = tdsDeductionAmt;
    }

    public String getNetInterestAmt() {
        return netInterestAmt;
    }

    public void setNetInterestAmt(String netInterestAmt) {
        this.netInterestAmt = netInterestAmt;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getModeofHolding() {
        return modeofHolding;
    }

    public void setModeofHolding(String modeofHolding) {
        this.modeofHolding = modeofHolding;
    }
}

