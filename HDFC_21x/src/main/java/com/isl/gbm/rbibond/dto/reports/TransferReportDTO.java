package com.isl.gbm.rbibond.dto.reports;

public class TransferReportDTO {
    private String userName;
    private String solId;
    private String schemeCode;
    private String schemeName;
    private String schemeStatus;
    private String blaNo;
    private String name;
    private String panNo;
    private String fromDate;
    private String flag;
    private String holderName;
    private  String modeOfHolding;


    private String branchId;
    private String branchName;
    private String moduleName;
    private Integer sNo;
    private String bondSchemeName;
    private String transferType;
    private String fromBlaModeOfHolding;
    private String fromBlaAccountNumber;
    private String transferorHolderName1;
    private String transferorHolder1PanNo;
    private String transferorHolderName2;
    private String transferorHolder2PanNo;
    private String transferorHolderName3;
    private String transferorHolder3PanNo;

    private Integer transferorSubscriptionCount;
    private String transferorSubscriptionId;
    private String cfdDate;
    private String maturityDate;
    private String subscriptionAmount;
    private String toBlaAccountNumber;
    private String toBlaModeOfHolding;
    private String transfereeHolderName1;
    private String transfereeHolder1PanNo;
    private String transfereeHolderName2;
    private String transfereeHolder2PanNo;
    private String transfereeHolderName3;
    private String transfereeHolder3PanNo;
    private String dateOfTransfer;
    private String remarks;
    private Integer transferredSubscriptionCount;
    private String transferredSubscriptionId;
    private String transferredCfdDate;
    private String transferredMaturityDate;
    private String transferredSubscriptionAmount;
    private String  pageSizeIn;
    private String   pageNoIn;

    public String getPageNoIn() {
        return pageNoIn;
    }

    public void setPageNoIn(String pageNoIn) {
        this.pageNoIn = pageNoIn;
    }

    public String getPageSizeIn() {
        return pageSizeIn;
    }

    public void setPageSizeIn(String pageSizeIn) {
        this.pageSizeIn = pageSizeIn;
    }

    public String getBondSchemeName() {
        return bondSchemeName;
    }

    public void setBondSchemeName(String bondSchemeName) {
        this.bondSchemeName = bondSchemeName;
    }

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

    public String getCfdDate() {
        return cfdDate;
    }

    public void setCfdDate(String cfdDate) {
        this.cfdDate = cfdDate;
    }

    public String getDateOfTransfer() {
        return dateOfTransfer;
    }

    public void setDateOfTransfer(String dateOfTransfer) {
        this.dateOfTransfer = dateOfTransfer;
    }

    public String getFromBlaAccountNumber() {
        return fromBlaAccountNumber;
    }

    public void setFromBlaAccountNumber(String fromBlaAccountNumber) {
        this.fromBlaAccountNumber = fromBlaAccountNumber;
    }

    public String getFromBlaModeOfHolding() {
        return fromBlaModeOfHolding;
    }

    public void setFromBlaModeOfHolding(String fromBlaModeOfHolding) {
        this.fromBlaModeOfHolding = fromBlaModeOfHolding;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getsNo() {
        return sNo;
    }

    public void setsNo(Integer sNo) {
        this.sNo = sNo;
    }

    public String getSubscriptionAmount() {
        return subscriptionAmount;
    }

    public void setSubscriptionAmount(String subscriptionAmount) {
        this.subscriptionAmount = subscriptionAmount;
    }

    public String getToBlaAccountNumber() {
        return toBlaAccountNumber;
    }

    public void setToBlaAccountNumber(String toBlaAccountNumber) {
        this.toBlaAccountNumber = toBlaAccountNumber;
    }

    public String getToBlaModeOfHolding() {
        return toBlaModeOfHolding;
    }

    public void setToBlaModeOfHolding(String toBlaModeOfHolding) {
        this.toBlaModeOfHolding = toBlaModeOfHolding;
    }

    public String getTransfereeHolder1PanNo() {
        return transfereeHolder1PanNo;
    }

    public void setTransfereeHolder1PanNo(String transfereeHolder1PanNo) {
        this.transfereeHolder1PanNo = transfereeHolder1PanNo;
    }

    public String getTransfereeHolder2PanNo() {
        return transfereeHolder2PanNo;
    }

    public void setTransfereeHolder2PanNo(String transfereeHolder2PanNo) {
        this.transfereeHolder2PanNo = transfereeHolder2PanNo;
    }

    public String getTransfereeHolder3PanNo() {
        return transfereeHolder3PanNo;
    }

    public void setTransfereeHolder3PanNo(String transfereeHolder3PanNo) {
        this.transfereeHolder3PanNo = transfereeHolder3PanNo;
    }

    public String getTransfereeHolderName1() {
        return transfereeHolderName1;
    }

    public void setTransfereeHolderName1(String transfereeHolderName1) {
        this.transfereeHolderName1 = transfereeHolderName1;
    }

    public String getTransfereeHolderName2() {
        return transfereeHolderName2;
    }

    public void setTransfereeHolderName2(String transfereeHolderName2) {
        this.transfereeHolderName2 = transfereeHolderName2;
    }

    public String getTransfereeHolderName3() {
        return transfereeHolderName3;
    }

    public void setTransfereeHolderName3(String transfereeHolderName3) {
        this.transfereeHolderName3 = transfereeHolderName3;
    }

    public String getTransferorHolder1PanNo() {
        return transferorHolder1PanNo;
    }

    public void setTransferorHolder1PanNo(String transferorHolder1PanNo) {
        this.transferorHolder1PanNo = transferorHolder1PanNo;
    }

    public String getTransferorHolder2PanNo() {
        return transferorHolder2PanNo;
    }

    public void setTransferorHolder2PanNo(String transferorHolder2PanNo) {
        this.transferorHolder2PanNo = transferorHolder2PanNo;
    }

    public String getTransferorHolder3PanNo() {
        return transferorHolder3PanNo;
    }

    public void setTransferorHolder3PanNo(String transferorHolder3PanNo) {
        this.transferorHolder3PanNo = transferorHolder3PanNo;
    }

    public String getTransferorHolderName1() {
        return transferorHolderName1;
    }

    public void setTransferorHolderName1(String transferorHolderName1) {
        this.transferorHolderName1 = transferorHolderName1;
    }

    public String getTransferorHolderName2() {
        return transferorHolderName2;
    }

    public void setTransferorHolderName2(String transferorHolderName2) {
        this.transferorHolderName2 = transferorHolderName2;
    }

    public String getTransferorHolderName3() {
        return transferorHolderName3;
    }

    public void setTransferorHolderName3(String transferorHolderName3) {
        this.transferorHolderName3 = transferorHolderName3;
    }

    public Integer getTransferorSubscriptionCount() {
        return transferorSubscriptionCount;
    }

    public void setTransferorSubscriptionCount(Integer transferorSubscriptionCount) {
        this.transferorSubscriptionCount = transferorSubscriptionCount;
    }

    public String getTransferorSubscriptionId() {
        return transferorSubscriptionId;
    }

    public void setTransferorSubscriptionId(String transferorSubscriptionId) {
        this.transferorSubscriptionId = transferorSubscriptionId;
    }

    public String getTransferredCfdDate() {
        return transferredCfdDate;
    }

    public void setTransferredCfdDate(String transferredCfdDate) {
        this.transferredCfdDate = transferredCfdDate;
    }

    public String getTransferredMaturityDate() {
        return transferredMaturityDate;
    }

    public void setTransferredMaturityDate(String transferredMaturityDate) {
        this.transferredMaturityDate = transferredMaturityDate;
    }

    public String getTransferredSubscriptionAmount() {
        return transferredSubscriptionAmount;
    }

    public void setTransferredSubscriptionAmount(String transferredSubscriptionAmount) {
        this.transferredSubscriptionAmount = transferredSubscriptionAmount;
    }

    public Integer getTransferredSubscriptionCount() {
        return transferredSubscriptionCount;
    }

    public void setTransferredSubscriptionCount(Integer transferredSubscriptionCount) {
        this.transferredSubscriptionCount = transferredSubscriptionCount;
    }

    public String getTransferredSubscriptionId() {
        return transferredSubscriptionId;
    }

    public void setTransferredSubscriptionId(String transferredSubscriptionId) {
        this.transferredSubscriptionId = transferredSubscriptionId;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getModeOfHolding() {
        return modeOfHolding;
    }

    public void setModeOfHolding(String modeOfHolding) {
        this.modeOfHolding = modeOfHolding;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

   /* public String getTransFlag() {
        return transFlag;
    }

    public void setTransFlag(String transFlag) {
        this.transFlag = transFlag;
    }*/

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    private String toDate;


    public String getBlaNo() {
        return blaNo;
    }

    public void setBlaNo(String blaNo) {
        this.blaNo = blaNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
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

    public String getSolId() {
        return solId;
    }

    public void setSolId(String solId) {
        this.solId = solId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
