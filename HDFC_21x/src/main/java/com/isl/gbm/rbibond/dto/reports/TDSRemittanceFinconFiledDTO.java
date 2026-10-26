package com.isl.gbm.rbibond.dto.reports;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TDSRemittanceFinconFiledDTO {
    private String coCode;
    private String refNo;
    private String bla;
    private String holderName;
    private String address1;
    private String address2;
    private String city;
    private String pin;
    private String state;
    private String country;
    private String section;
    private String panNo;
    private String grossAmount;
    private String taxRate;
    private String totalTaxAmount;
    private String paymentDate;
    private String basicTax;
    private String surchargeAmount;
    private String cessAmount;
    private String taxDepDate;
    private String lowerDeductionStatus;
    private String category;
    private String interestPayoutfortheYear;
    private String waiverDocument;
    private String flag;
    private String schemeCode;
    private String fromDate;
    private String toDate;
    private String schemeName;
    private String schemeStatus;
    private String pageNo;
    private String pageSize;
    private String searchValue;
    private String sortOrder;
    private String sortField;

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getSchemeStatus() {
        return schemeStatus;
    }

    public void setSchemeStatus(String schemeStatus) {
        this.schemeStatus = schemeStatus;
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

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getBla() {
        return bla;
    }

    public void setBla(String bla) {
        this.bla = bla;
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

    public String getCoCode() {
        return coCode;
    }

    public void setCoCode(String coCode) {
        this.coCode = coCode;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(String grossAmount) {
        this.grossAmount = grossAmount;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getTotalTaxAmount() {
        return totalTaxAmount;
    }

    public void setTotalTaxAmount(String totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getBasicTax() {
        return basicTax;
    }

    public void setBasicTax(String basicTax) {
        this.basicTax = basicTax;
    }

    public String getSurchargeAmount() {
        return surchargeAmount;
    }

    public void setSurchargeAmount(String surchargeAmount) {
        this.surchargeAmount = surchargeAmount;
    }

    public String getCessAmount() {
        return cessAmount;
    }

    public void setCessAmount(String cessAmount) {
        this.cessAmount = cessAmount;
    }

    public String getTaxDepDate() {
        return taxDepDate;
    }

    public void setTaxDepDate(String taxDepDate) {
        this.taxDepDate = taxDepDate;
    }

    public String getLowerDeductionStatus() {
        return lowerDeductionStatus;
    }

    public void setLowerDeductionStatus(String lowerDeductionStatus) {
        this.lowerDeductionStatus = lowerDeductionStatus;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInterestPayoutfortheYear() {
        return interestPayoutfortheYear;
    }

    public void setInterestPayoutfortheYear(String interestPayoutfortheYear) {
        this.interestPayoutfortheYear = interestPayoutfortheYear;
    }

    public String getWaiverDocument() {
        return waiverDocument;
    }

    public void setWaiverDocument(String waiverDocument) {
        this.waiverDocument = waiverDocument;
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
}
