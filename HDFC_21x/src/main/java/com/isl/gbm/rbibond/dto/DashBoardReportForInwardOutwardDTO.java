package com.isl.gbm.rbibond.dto;

import java.util.List;
import java.util.Map;

public class DashBoardReportForInwardOutwardDTO {

    private String schemeCode;
    private String requestCode;
    private String date;
    private String bondBlaNumber;
    private String maturityDate;
    private String modeOfHolding;
    //    private String inwardRefNumber;
//    private String outwardRefNumber;
    private String inwardRefDate;
    private String outwardDate;
    private String status;
    private String reqDescription;
    private String outwardReason;
    private String bondType;
    private String fromDate;
    private int pageNo;
    private int maxPages;
    private String Fts;
    private String Subscription;
    private String UserName;
//    private String RequestDescription;
    private  String inwardRefNo;
    private  String inwardDate;
    private  String outwardRefNo;
private String SubscriptionID;
    private String noOfDays;
    private String podNumber;

    public String getModeOfHolding() {
        return modeOfHolding;
    }

    public void setModeOfHolding(String modeOfHolding) {
        this.modeOfHolding = modeOfHolding;
    }
    public String getSubscriptionID() {
        return SubscriptionID;
    }

    public void setSubscriptionID(String subscriptionID) {
        SubscriptionID = subscriptionID;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    private String toDate;
    private String schemeName;

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

    private String total;
    //    private String interestPayFrom;
//    private String interestPayTo;
    private String sortOrder;
    private String name;
    private String blaNo;
    private String panNo;

    public String getSubStatus() {
        return subStatus;
    }

    public void setSubStatus(String subStatus) {
        this.subStatus = subStatus;
    }

    private String flag;
    private String schemeDescription;
    private String schemeStatus;
    private  String subStatus;
    public String getSchemeStatus() {
        return schemeStatus;
    }

    public void setSchemeStatus(String schemeStatus) {
        this.schemeStatus = schemeStatus;
    }

    public String getSchemeDescription() {
        return schemeDescription;
    }

    public void setSchemeDescription(String schemeDescription) {
        this.schemeDescription = schemeDescription;
    }

    private String sortField;
    private String isDownload;
    private List<Map<String, Object>> data;
    private String searchValue;
    private int pageSize;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBondBlaNumber() {
        return bondBlaNumber;
    }

    public void setBondBlaNumber(String bondBlaNumber) {
        this.bondBlaNumber = bondBlaNumber;
    }


    public String getBondType() {
        return bondType;
    }

    public void setBondType(String bondType) {
        this.bondType = bondType;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlaNo() {
        return blaNo;
    }

    public void setBlaNo(String blaNo) {
        this.blaNo = blaNo;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(String isDownload) {
        this.isDownload = isDownload;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
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



    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

//    public String getInwardRefNumber() {
//        return inwardRefNumber;
//    }

//    public void setInwardRefNumber(String inwardRefNumber) {
//        this.inwardRefNumber = inwardRefNumber;
//    }

//    public String getOutwardRefNumber() {
//        return outwardRefNumber;
//    }

//    public void setOutwardRefNumber(String outwardRefNumber) {
//        this.outwardRefNumber = outwardRefNumber;
//    }

    public String getInwardRefDate() {
        return inwardRefDate;
    }

    public void setInwardRefDate(String inwardRefDate) {
        this.inwardRefDate = inwardRefDate;
    }

    public String getOutwardDate() {
        return outwardDate;
    }

    public void setOutwardDate(String outwardDate) {
        this.outwardDate = outwardDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public String getReqDescription() {
        return reqDescription;
    }

    public void setReqDescription(String reqDescription) {
        this.reqDescription = reqDescription;
    }


    public void setFts(String fts) {
        Fts = fts;
    }


//    public String getRequestDescription() {
//        return RequestDescription;
//    }


    public String getFts() {
        return Fts;
    }

    public String getSubscription() {
        return Subscription;
    }

    public void setSubscription(String subscription) {
        Subscription = subscription;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

//    public void setRequestDescription(String RequestDescription) {
//        this.RequestDescription = RequestDescription;
//    }

    public String getInwardRefNo() {
        return inwardRefNo;
    }

    public void setInwardRefNo(String inwardRefNo) {
        this.inwardRefNo = inwardRefNo;
    }

    public String getInwardDate() {
        return inwardDate;
    }

    public void setInwardDate(String inwardDate) {
        this.inwardDate = inwardDate;
    }

    public String getOutwardRefNo() {
        return outwardRefNo;
    }

    public void setOutwardRefNo(String outwardRefNo) {
        this.outwardRefNo = outwardRefNo;
    }

    public String getOutwardReason() {
        return outwardReason;
    }

    public void setOutwardReason(String outwardReason) {
        this.outwardReason = outwardReason;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public void setNoOfDays(String noOfDays) {this.noOfDays = noOfDays;}
    public String getNoOfDays() {return noOfDays;}

    public void setPodNumber(String podNumber) { this.podNumber = podNumber;}
    public String getPodNumber() {return podNumber;}
}


