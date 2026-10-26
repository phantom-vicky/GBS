package com.isl.gbm.rbibond.dto.reports;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrokerageClaimMonthlyDTO {
    private String pageNo;
    private String pageSize;
    private String searchValue;
    private String sortOrder;

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
    private String brokerageClaimApril;
    private String schemeCode;
    private String schemeName;
    private String schemeStatus;
    private String brokerageToBank;
    private String brokeragePayment;
    private String commission;
    private String brokerage100;
    private String grossBrokerage;
    private String netBrokerage;
    private String gst;
    private String code;
    private String glNo;
    private String glDescription;
    private String amountDr;
    private String amountCr;
    private String narrationtoBeCaptured;
    private String blaNo;
    private String investAmt;
    private String casTransferDate;
    private String maturityDate;
    private String scheme;
    private String cfd;
    private String branchrefno;
    private String panNo;
    private String bankName;
    private String adviceNo;
    private String appName;
    private String addrs1;
    private String addrs2;
    private String city;
    private String state;
    private String pincode;
    private String branchName;
    private String rcptDate;
    private String groupDate;
    private String hdfcBrokerCode;
    private String brokerName;
    private String addr1;
    private String addr2;
    private String city1;
    private String state1;
    private String pincode1;
    private String phone;
    private String paymentMode;
    private String accTypeCode;
    private String bankAcc;
    private String micrCode;
    private String appNo;
    private String status;
    private String deStatus;
    private String brokerAmt;
    private String grossBrok;
    private String netBrok;
    private String gst1;
    private String brokerAgencyCode;
    private String branchId;
    private String verifystatus;
    private String flag;
    private String fromDate;
    private String toDate;
    private String dirGrossAmt;
    private String dirNetAmt;
    private String dirGst;
    private String othersGrossAmt;
    private String othersNetAmt;
    private String othersGst;
    private String commissionGross;
    private String commissionNet;
    private String commissionGst;

    public String getDirNetAmt() {
        return dirNetAmt;
    }

    public void setDirNetAmt(String dirNetAmt) {
        this.dirNetAmt = dirNetAmt;
    }

    public String getDirGst() {
        return dirGst;
    }

    public void setDirGst(String dirGst) {
        this.dirGst = dirGst;
    }

    public String getOthersGrossAmt() {
        return othersGrossAmt;
    }

    public void setOthersGrossAmt(String othersGrossAmt) {
        this.othersGrossAmt = othersGrossAmt;
    }

    public String getOthersNetAmt() {
        return othersNetAmt;
    }

    public void setOthersNetAmt(String othersNetAmt) {
        this.othersNetAmt = othersNetAmt;
    }

    public String getOthersGst() {
        return othersGst;
    }

    public void setOthersGst(String othersGst) {
        this.othersGst = othersGst;
    }

    public String getCommissionGross() {
        return commissionGross;
    }

    public void setCommissionGross(String commissionGross) {
        this.commissionGross = commissionGross;
    }

    public String getCommissionNet() {
        return commissionNet;
    }

    public void setCommissionNet(String commissionNet) {
        this.commissionNet = commissionNet;
    }

    public String getCommissionGst() {
        return commissionGst;
    }

    public void setCommissionGst(String commissionGst) {
        this.commissionGst = commissionGst;
    }

    public String getDirGrossAmt() {
        return dirGrossAmt;
    }

    public void setDirGrossAmt(String dirGrossAmt) {
        this.dirGrossAmt = dirGrossAmt;
    }

    public String getBrokerageClaimApril() {
        return brokerageClaimApril;
    }

    public void setBrokerageClaimApril(String brokerageClaimApril) {
        this.brokerageClaimApril = brokerageClaimApril;
    }

    public String getBrokerageToBank() {
        return brokerageToBank;
    }

    public void setBrokerageToBank(String brokerageToBank) {
        this.brokerageToBank = brokerageToBank;
    }

    public String getBrokeragePayment() {
        return brokeragePayment;
    }

    public void setBrokeragePayment(String brokeragePayment) {
        this.brokeragePayment = brokeragePayment;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getBrokerage100() {
        return brokerage100;
    }

    public void setBrokerage100(String brokerage100) {
        this.brokerage100 = brokerage100;
    }

    public String getGrossBrokerage() {
        return grossBrokerage;
    }

    public void setGrossBrokerage(String grossBrokerage) {
        this.grossBrokerage = grossBrokerage;
    }

    public String getNetBrokerage() {
        return netBrokerage;
    }

    public void setNetBrokerage(String netBrokerage) {
        this.netBrokerage = netBrokerage;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGlNo() {
        return glNo;
    }

    public void setGlNo(String glNo) {
        this.glNo = glNo;
    }

    public String getGlDescription() {
        return glDescription;
    }

    public void setGlDescription(String glDescription) {
        this.glDescription = glDescription;
    }

    public String getAmountDr() {
        return amountDr;
    }

    public void setAmountDr(String amountDr) {
        this.amountDr = amountDr;
    }

    public String getAmountCr() {
        return amountCr;
    }

    public void setAmountCr(String amountCr) {
        this.amountCr = amountCr;
    }

    public String getNarrationtoBeCaptured() {
        return narrationtoBeCaptured;
    }

    public void setNarrationtoBeCaptured(String narrationtoBeCaptured) {
        this.narrationtoBeCaptured = narrationtoBeCaptured;
    }

    public String getBlaNo() {
        return blaNo;
    }

    public void setBlaNo(String blaNo) {
        this.blaNo = blaNo;
    }

    public String getInvestAmt() {
        return investAmt;
    }

    public void setInvestAmt(String investAmt) {
        this.investAmt = investAmt;
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

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getCfd() {
        return cfd;
    }

    public void setCfd(String cfd) {
        this.cfd = cfd;
    }

    public String getBranchrefno() {
        return branchrefno;
    }

    public void setBranchrefno(String branchrefno) {
        this.branchrefno = branchrefno;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAdviceNo() {
        return adviceNo;
    }

    public void setAdviceNo(String adviceNo) {
        this.adviceNo = adviceNo;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAddrs1() {
        return addrs1;
    }

    public void setAddrs1(String addrs1) {
        this.addrs1 = addrs1;
    }

    public String getAddrs2() {
        return addrs2;
    }

    public void setAddrs2(String addrs2) {
        this.addrs2 = addrs2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getRcptDate() {
        return rcptDate;
    }

    public void setRcptDate(String rcptDate) {
        this.rcptDate = rcptDate;
    }

    public String getGroupDate() {
        return groupDate;
    }

    public void setGroupDate(String groupDate) {
        this.groupDate = groupDate;
    }

    public String getHdfcBrokerCode() {
        return hdfcBrokerCode;
    }

    public void setHdfcBrokerCode(String hdfcBrokerCode) {
        this.hdfcBrokerCode = hdfcBrokerCode;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getCity1() {
        return city1;
    }

    public void setCity1(String city1) {
        this.city1 = city1;
    }

    public String getState1() {
        return state1;
    }

    public void setState1(String state1) {
        this.state1 = state1;
    }

    public String getPincode1() {
        return pincode1;
    }

    public void setPincode1(String pincode1) {
        this.pincode1 = pincode1;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getAccTypeCode() {
        return accTypeCode;
    }

    public void setAccTypeCode(String accTypeCode) {
        this.accTypeCode = accTypeCode;
    }

    public String getBankAcc() {
        return bankAcc;
    }

    public void setBankAcc(String bankAcc) {
        this.bankAcc = bankAcc;
    }

    public String getMicrCode() {
        return micrCode;
    }

    public void setMicrCode(String micrCode) {
        this.micrCode = micrCode;
    }

    public String getAppNo() {
        return appNo;
    }

    public void setAppNo(String appNo) {
        this.appNo = appNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeStatus() {
        return deStatus;
    }

    public void setDeStatus(String deStatus) {
        this.deStatus = deStatus;
    }

    public String getBrokerAmt() {
        return brokerAmt;
    }

    public void setBrokerAmt(String brokerAmt) {
        this.brokerAmt = brokerAmt;
    }

    public String getGrossBrok() {
        return grossBrok;
    }

    public void setGrossBrok(String grossBrok) {
        this.grossBrok = grossBrok;
    }

    public String getNetBrok() {
        return netBrok;
    }

    public void setNetBrok(String netBrok) {
        this.netBrok = netBrok;
    }

    public String getGst1() {
        return gst1;
    }

    public void setGst1(String gst1) {
        this.gst1 = gst1;
    }

    public String getBrokerAgencyCode() {
        return brokerAgencyCode;
    }

    public void setBrokerAgencyCode(String brokerAgencyCode) {
        this.brokerAgencyCode = brokerAgencyCode;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getVerifystatus() {
        return verifystatus;
    }

    public void setVerifystatus(String verifystatus) {
        this.verifystatus = verifystatus;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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
}
