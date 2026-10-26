package com.isl.gbm.rbibond.dto.reports;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubsAmtBLAMaturedAccClosedDTO {
    private String schemeName;
    private String schemeCode;
    private String schemeStatus;
    private String fromDate;
    private String toDate;
    private String flag;
    private String blaFrom;
    private String blaTo;
    private String fromAmount;
    private String toAmount;

    private String blaNumber;
    private String holderName;
    private String address;
    private String isNRI;
    private String isMinor;
    private String modeOfHolding;
    private String holderType;

    private String panNumber;
    private String subscriptionID;
    private String dateOfSubscription;
    private String maturityDate;
    private String subscriptionAmount;
    private String accountClosedDate;

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

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getBlaFrom() {
        return blaFrom;
    }

    public void setBlaFrom(String blaFrom) {
        this.blaFrom = blaFrom;
    }

    public String getBlaTo() {
        return blaTo;
    }

    public void setBlaTo(String blaTo) {
        this.blaTo = blaTo;
    }

    public String getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(String fromAmount) {
        this.fromAmount = fromAmount;
    }

    public String getToAmount() {
        return toAmount;
    }

    public void setToAmount(String toAmount) {
        this.toAmount = toAmount;
    }

    public String getBlaNumber() {
        return blaNumber;
    }

    public void setBlaNumber(String blaNumber) {
        this.blaNumber = blaNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsNRI() {
        return isNRI;
    }

    public void setIsNRI(String isNRI) {
        this.isNRI = isNRI;
    }

    public String getIsMinor() {
        return isMinor;
    }

    public void setIsMinor(String isMinor) {
        this.isMinor = isMinor;
    }

    public String getModeOfHolding() {
        return modeOfHolding;
    }

    public void setModeOfHolding(String modeOfHolding) {
        this.modeOfHolding = modeOfHolding;
    }

    public String getHolderType() {
        return holderType;
    }

    public void setHolderType(String holderType) {
        this.holderType = holderType;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getSubscriptionID() {
        return subscriptionID;
    }

    public void setSubscriptionID(String subscriptionID) {
        this.subscriptionID = subscriptionID;
    }

    public String getDateOfSubscription() {
        return dateOfSubscription;
    }

    public void setDateOfSubscription(String dateOfSubscription) {
        this.dateOfSubscription = dateOfSubscription;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getSubscriptionAmount() {
        return subscriptionAmount;
    }

    public void setSubscriptionAmount(String subscriptionAmount) {
        this.subscriptionAmount = subscriptionAmount;
    }

    public String getAccountClosedDate() {
        return accountClosedDate;
    }

    public void setAccountClosedDate(String accountClosedDate) {
        this.accountClosedDate = accountClosedDate;
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
