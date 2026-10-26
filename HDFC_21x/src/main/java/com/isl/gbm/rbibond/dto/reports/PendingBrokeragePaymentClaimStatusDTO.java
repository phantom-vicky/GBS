package com.isl.gbm.rbibond.dto.reports;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class  PendingBrokeragePaymentClaimStatusDTO {

    private String status;
    private String fromDate;
    private String toDate;
    private String schemeName;
    private String schemeCode;

    private String month;
    private String count;
    private String investmentAmount;
    private String sumOfInvestmentAmountHeader1;
    private String sumOfGrossBrokerage;

    // Header 2
    private String brokerCode;
    private String brokerName;
    private String sumOfInvestAmountHeader2;
    private String grossBrokerage;

    // Address Information
    private String address1;
    private String address2;
    private String city;
    private String pinCode;
    private String state;

    // Financial and Vendor Info
    private String netBrokerage;
    private String gst18Percent;
    private String vendorCodes;
    private String hsnCodes;
    private String rcmStatus;
    private String invoiceNumber;
    private String invoiceDate;
    private String vbms;
    private String date;
    private String maker;
    private String creditDate;
    private String amount;
    private String difference;
    private String receivedOn;
    private String rejectedOn;
    private String pod;
    private String rcm;
    private String remarks;
    private String tat;
    private String cpuRemarks;
    private String reason;
    private String remarks1;

    // Contact Information
    private String phone;
    private String emailId;
    private String queryRemarks;

    private String casTransferDate;
    private String maturityDate;
    private String cfd;
    private String branchRefNo;
    private String adviceNo;
    private String applicantName;
    private String applicantNO;
    private String branchName;
    private String receiptDate;
    private String groupCode;
    private String paymentMode;
    private String accountTypeCode;
    private String bankAccount;

    //pagination control
    private String downloadType;
    private List<Map<String, Object>> data;
    private String sortOrder;
    private String sortField;
    private int pageSize;
    private int pageNo;
    private int maxPages;
    private String searchValue;
    private String interestPayFrom;
    private String interestPayTo;
    private String isDownload;

    private String micrCode;
    private String deStatus;
    private String brokerAmount;
    private String hdfcBrokerCode;
    private String hdfcBranchCode;
    private String blaNo;
    private String blaAddress;
    private String blaAddress1;
    private String blaCity;
    private String blaState;
    private String blaPincode;
    private String brokerId;
    private String channelType;
    private String brokerStatus;


    public String getHdfcBrokerCode() {
        return hdfcBrokerCode;
    }

    public void setHdfcBrokerCode(String hdfcBrokerCode) {
        this.hdfcBrokerCode = hdfcBrokerCode;
    }

    public String getBlaPincode() {
        return blaPincode;
    }

    public void setBlaPincode(String blaPincode) {
        this.blaPincode = blaPincode;
    }

    public String getBlaCity() {
        return blaCity;
    }

    public void setBlaCity(String blaCity) {
        this.blaCity = blaCity;
    }

    public String getBlaState() {
        return blaState;
    }

    public void setBlaState(String blaState) {
        this.blaState = blaState;
    }

    public String getBlaAddress() {
        return blaAddress;
    }

    public void setBlaAddress(String blaAddress) {
        this.blaAddress = blaAddress;
    }

    public String getBlaAddress1() {
        return blaAddress1;
    }

    public void setBlaAddress1(String blaAddress1) {
        this.blaAddress1 = blaAddress1;
    }

    public String getBrokerStatus() {
        return brokerStatus;
    }

    public void setBrokerStatus(String brokerStatus) {
        this.brokerStatus = brokerStatus;
    }

    public String getRemarks1() {
        return remarks1;
    }

    public void setRemarks1(String remarks1) {
        this.remarks1 = remarks1;
    }

    public String getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getIsDownload() { return isDownload; }

    public void setIsDownload(String isDownload) { this.isDownload = isDownload; }

    public String getInterestPayFrom() {
        return interestPayFrom;
    }

    public void setInterestPayFrom(String interestPayFrom) {
        this.interestPayFrom = interestPayFrom;
    }

    public String getInterestPayTo() {
        return interestPayTo;
    }

    public void setInterestPayTo(String interestPayTo) {
        this.interestPayTo = interestPayTo;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
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

    public String getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(String downloadType) {
        this.downloadType = downloadType;
    }

    public String getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(String investmentAmount) {
        this.investmentAmount = investmentAmount;
    }

    public String getCasTransferDate() {
        return casTransferDate;
    }

    public void setCasTransferDate(String casTransferDate) {
        this.casTransferDate = casTransferDate;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getCfd() {
        return cfd;
    }

    public void setCfd(String cfd) {
        this.cfd = cfd;
    }

    public String getBranchRefNo() {
        return branchRefNo;
    }

    public void setBranchRefNo(String branchRefNo) {
        this.branchRefNo = branchRefNo;
    }

    public String getAdviceNo() {
        return adviceNo;
    }

    public void setAdviceNo(String adviceNo) {
        this.adviceNo = adviceNo;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantNO() {
        return applicantNO;
    }

    public void setApplicantNO(String applicantNO) {
        this.applicantNO = applicantNO;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getAccountTypeCode() {
        return accountTypeCode;
    }

    public void setAccountTypeCode(String accountTypeCode) {
        this.accountTypeCode = accountTypeCode;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getMicrCode() {
        return micrCode;
    }

    public void setMicrCode(String micrCode) {
        this.micrCode = micrCode;
    }

    public String getDeStatus() {
        return deStatus;
    }

    public void setDeStatus(String deStatus) {
        this.deStatus = deStatus;
    }

    public String getBrokerAmount() {
        return brokerAmount;
    }

    public void setBrokerAmount(String brokerAmount) {
        this.brokerAmount = brokerAmount;
    }

    public String getHdfcBranchCode() {
        return hdfcBranchCode;
    }

    public void setHdfcBranchCode(String hdfcBranchCode) {
        this.hdfcBranchCode = hdfcBranchCode;
    }

    public String getBlaNo() {
        return blaNo;
    }

    public void setBlaNo(String blaNo) {
        this.blaNo = blaNo;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSumOfInvestmentAmountHeader1() {
        return sumOfInvestmentAmountHeader1;
    }

    public void setSumOfInvestmentAmountHeader1(String sumOfInvestmentAmountHeader1) {
        this.sumOfInvestmentAmountHeader1 = sumOfInvestmentAmountHeader1;
    }

    public String getSumOfGrossBrokerage() {
        return sumOfGrossBrokerage;
    }

    public void setSumOfGrossBrokerage(String sumOfGrossBrokerage) {
        this.sumOfGrossBrokerage = sumOfGrossBrokerage;
    }

    public String getBrokerCode() {
        return brokerCode;
    }

    public void setBrokerCode(String brokerCode) {
        this.brokerCode = brokerCode;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getSumOfInvestAmountHeader2() {
        return sumOfInvestAmountHeader2;
    }

    public void setSumOfInvestAmountHeader2(String sumOfInvestAmountHeader2) {
        this.sumOfInvestAmountHeader2 = sumOfInvestAmountHeader2;
    }

    public String getGrossBrokerage() {
        return grossBrokerage;
    }

    public void setGrossBrokerage(String grossBrokerage) {
        this.grossBrokerage = grossBrokerage;
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

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNetBrokerage() {
        return netBrokerage;
    }

    public void setNetBrokerage(String netBrokerage) {
        this.netBrokerage = netBrokerage;
    }

    public String getGst18Percent() {
        return gst18Percent;
    }

    public void setGst18Percent(String gst18Percent) {
        this.gst18Percent = gst18Percent;
    }

    public String getVendorCodes() {
        return vendorCodes;
    }

    public void setVendorCodes(String vendorCodes) {
        this.vendorCodes = vendorCodes;
    }

    public String getHsnCodes() {
        return hsnCodes;
    }

    public void setHsnCodes(String hsnCodes) {
        this.hsnCodes = hsnCodes;
    }

    public String getRcmStatus() {
        return rcmStatus;
    }

    public void setRcmStatus(String rcmStatus) {
        this.rcmStatus = rcmStatus;
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

    public String getVbms() {
        return vbms;
    }

    public void setVbms(String vbms) {
        this.vbms = vbms;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getCreditDate() {
        return creditDate;
    }

    public void setCreditDate(String creditDate) {
        this.creditDate = creditDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDifference() {
        return difference;
    }

    public void setDifference(String difference) {
        this.difference = difference;
    }

    public String getReceivedOn() {
        return receivedOn;
    }

    public void setReceivedOn(String receivedOn) {
        this.receivedOn = receivedOn;
    }

    public String getRejectedOn() {
        return rejectedOn;
    }

    public void setRejectedOn(String rejectedOn) {
        this.rejectedOn = rejectedOn;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    public String getRcm() {
        return rcm;
    }

    public void setRcm(String rcm) {
        this.rcm = rcm;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTat() {
        return tat;
    }

    public void setTat(String tat) {
        this.tat = tat;
    }

    public String getCpuRemarks() {
        return cpuRemarks;
    }

    public void setCpuRemarks(String cpuRemarks) {
        this.cpuRemarks = cpuRemarks;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getQueryRemarks() {
        return queryRemarks;
    }

    public void setQueryRemarks(String queryRemarks) {
        this.queryRemarks = queryRemarks;
    }

    //////////////////
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

    @Override
    public String toString() {
        return "PendingBrokeragePaymentClaimStatusDTO{" +
                "status='" + status + '\'' +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                ", schemeName='" + schemeName + '\'' +
                ", schemeCode='" + schemeCode + '\'' +
                '}';
    }
}
