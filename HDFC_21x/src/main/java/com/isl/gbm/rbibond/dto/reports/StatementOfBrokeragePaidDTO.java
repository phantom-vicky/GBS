package com.isl.gbm.rbibond.dto.reports;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatementOfBrokeragePaidDTO {
    private String schemeCode;
    private String schemeName;
    private String schemeStatus;
    private String reportType;
    private String status;
    private String fromDate;
    private String toDate;
    private String flag;

    private String blaNumber;
    private String customerName;
    private String subscriptionID;
    private String subscriptionAmount;
    private String cumulative;
    private String nonCumulative;
    private String customerStatus;
    private String invoiceNumber;
    private String invoiceDate;
    private String brokerID;
    private String brokerPAN;
    private String brokerEmailId;
    private String brokerName;
    private String transactionStatus;
    private String grossBrokerage;
    private String GST;
    private String netBrokeragePaid;
    private String brokeragePaidDate;
    private String brokerageTransactionInitiatedDate;
    private String reasonForUnpaid;


    private String brokerCode;
    private String gstPercent;
    private String gstAmount;
    private String remarks;

    private String pageNo;
    private String pageSize;
    private String searchValue;
    private String sortOrder;
    private String sortField;

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

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

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getBlaNumber() {
        return blaNumber;
    }

    public void setBlaNumber(String blaNumber) {
        this.blaNumber = blaNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSubscriptionID() {
        return subscriptionID;
    }

    public void setSubscriptionID(String subscriptionID) {
        this.subscriptionID = subscriptionID;
    }

    public String getSubscriptionAmount() {
        return subscriptionAmount;
    }

    public void setSubscriptionAmount(String subscriptionAmount) {
        this.subscriptionAmount = subscriptionAmount;
    }

    public String getCumulative() {
        return cumulative;
    }

    public void setCumulative(String cumulative) {
        this.cumulative = cumulative;
    }

    public String getNonCumulative() {
        return nonCumulative;
    }

    public void setNonCumulative(String nonCumulative) {
        this.nonCumulative = nonCumulative;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getBrokerID() {
        return brokerID;
    }

    public void setBrokerID(String brokerID) {
        this.brokerID = brokerID;
    }

    public String getBrokerPAN() {
        return brokerPAN;
    }

    public void setBrokerPAN(String brokerPAN) {
        this.brokerPAN = brokerPAN;
    }

    public String getBrokerEmailId() {
        return brokerEmailId;
    }

    public void setBrokerEmailId(String brokerEmailId) {
        this.brokerEmailId = brokerEmailId;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getGrossBrokerage() {
        return grossBrokerage;
    }

    public void setGrossBrokerage(String grossBrokerage) {
        this.grossBrokerage = grossBrokerage;
    }

    public String getGST() {
        return GST;
    }

    public void setGST(String GST) {
        this.GST = GST;
    }

    public String getNetBrokeragePaid() {
        return netBrokeragePaid;
    }

    public void setNetBrokeragePaid(String netBrokeragePaid) {
        this.netBrokeragePaid = netBrokeragePaid;
    }

    public String getBrokeragePaidDate() {
        return brokeragePaidDate;
    }

    public void setBrokeragePaidDate(String brokeragePaidDate) {
        this.brokeragePaidDate = brokeragePaidDate;
    }

    public String getBrokerageTransactionInitiatedDate() {
        return brokerageTransactionInitiatedDate;
    }

    public void setBrokerageTransactionInitiatedDate(String brokerageTransactionInitiatedDate) {
        this.brokerageTransactionInitiatedDate = brokerageTransactionInitiatedDate;
    }

    public String getReasonForUnpaid() {
        return reasonForUnpaid;
    }

    public void setReasonForUnpaid(String reasonForUnpaid) {
        this.reasonForUnpaid = reasonForUnpaid;
    }

    public String getBrokerCode() {
        return brokerCode;
    }

    public void setBrokerCode(String brokerCode) {
        this.brokerCode = brokerCode;
    }

    public String getGstPercent() {
        return gstPercent;
    }

    public void setGstPercent(String gstPercent) {
        this.gstPercent = gstPercent;
    }

    public String getGstAmount() {
        return gstAmount;
    }

    public void setGstAmount(String gstAmount) {
        this.gstAmount = gstAmount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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
