package com.isl.gbm.rbibond.dto.reports;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BondInterestCalculatedDTO {
    private String schemeCode;
    private String branchId;
    private String interestPaid;
    private String financialYear;
    private String isDownload;
    private String downloadType;
//    private String BranchId;
    private String BLANo;
    private String HolderName;
    private String SubscriptionDate;
    private String SubscriptionAccountNo;
    private String SubscriptionAmount;
    private String InterestPaymentmode;
    private String GrossInterestAmount;
    private String Reasons;
    private String sortField;
    private String sortOrder;
    private int pageNo;
    private int maxPages;
    private String searchValue;
    private int pageSize;
    private String schemeName;
    private String branchname;

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    private List<Map<String, Object>> data;

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    public String getBLANo() {
        return BLANo;
    }

    public void setBLANo(String BLANo) {
        this.BLANo = BLANo;
    }

    public String getHolderName() {
        return HolderName;
    }

    public void setHolderName(String holderName) {
        HolderName = holderName;
    }

    public String getSubscriptionDate() {
        return SubscriptionDate;
    }

    public void setSubscriptionDate(String subscriptionDate) {
        SubscriptionDate = subscriptionDate;
    }

    public String getSubscriptionAccountNo() {
        return SubscriptionAccountNo;
    }

    public void setSubscriptionAccountNo(String subscriptionAccountNo) {
        SubscriptionAccountNo = subscriptionAccountNo;
    }

    public String getSubscriptionAmount() {
        return SubscriptionAmount;
    }

    public void setSubscriptionAmount(String subscriptionAmount) {
        SubscriptionAmount = subscriptionAmount;
    }

    public String getInterestPaymentmode() {
        return InterestPaymentmode;
    }

    public void setInterestPaymentmode(String interestPaymentmode) {
        InterestPaymentmode = interestPaymentmode;
    }

    public String getGrossInterestAmount() {
        return GrossInterestAmount;
    }

    public void setGrossInterestAmount(String grossInterestAmount) {
        GrossInterestAmount = grossInterestAmount;
    }

    public String getReasons() {
        return Reasons;
    }

    public void setReasons(String reasons) {
        Reasons = reasons;
    }


    public String getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(String isDownload) {
        this.isDownload = isDownload;
    }

    public String getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(String downloadType) {
        this.downloadType = downloadType;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getMaxPages() {
        return maxPages;
    }

    public void setMaxPages(int maxPages) {
        this.maxPages = maxPages;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }



    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getInterestPaid() {
        return interestPaid;
    }

    public void setInterestPaid(String interestPaid) {
        this.interestPaid = interestPaid;
    }

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }
}
