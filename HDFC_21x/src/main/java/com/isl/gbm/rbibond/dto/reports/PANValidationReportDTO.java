package com.isl.gbm.rbibond.dto.reports;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.List;
public class PANValidationReportDTO {

    private String userName;
    private String solId;
    private String schemeCode;
    private String schemeName;
    private String schemeStatus;
    private String blaNo;
    private String name;
    private String panNo;
    private String panHldName;
    private String subscriptionId;
    private String bondScheme;
    private String status;
    private String fromDate;
    private String flagIn;
    private String toDate;
    private String pageNoIn;
    private String pageSizeIn;
    private String searchValIn;
    private String bondType;
    private String maturityAmt;
    private String bondAddress;
    private String nomineeId;
    private String bondHolderName;
    private String modeOfHolding;
    private String depositeDate;
    private String schemeFlag;
    private String notificationNo;
    private String notificationDate;

    private String singleblaNo;
    private String firstJointBla;
    private String secondJoinBla;
    private String appDate;
    private String singleHoldName;
    private String singleHoldAdd;
    private String firstHoldName;
    private String firstHoldAdd;
    private String secondHoldName;
    private String secondHoldAdd;
    private String nomineeName;
    private String nomineeDate;
    private String holderType;
    private String subscriptionAmt;
    private String holderName;
    private String accOpenDate;
    private String appNo;
    private String maturityDate;
    private String nasikDate;
    private String intrestRate;
    private String city;
    private String state;
    private String pinCode;
    private String certificateNo;
    private String transferDate;
    private String transferAmt;
    private String repaymentDate;
    private String repaymentAmt;
    private String investmentDate;
    private String investmentAmt;
    private String balance;
    private String branchCode;
    private String initialUndrBankSeal;
    private String intPaymtDate1;
    private String intPaymtDate2;
    private String totalInvestmentAmt,totaltTransferAmt,totalRepaymentAmt;
    private String receivingOffice;
    private String triggedDate;
    private String  panTriggeredFrom;
    private  String triggedMakerID;
    private String triggedCheckerID;
    private  String newPANNO;
    private String  newName;
    private String DOB;
    private String newDOB;
    private String aadharSeeding;
    private String isSendNSDL;
    private String sendNSDLDate;
    private String panNoStatus;
    private String nameStatus;
private String aadharStatus;
private String fileType;
private String remark;
private  String uploadBy;
private String uploadDate;
private String modifyBy;
private  String modifyDate;
private String reSendNSDL;
    private String reSendNSDLDate;
    private  String  netBankingDate;
    private String auditDate;
    private  String uploadFileName;
private  String panTriggered;
    private String DOBStatus;

    public String getDOBStatus() {
        return DOBStatus;
    }

    public void setDOBStatus(String DOBStatus) {
        this.DOBStatus = DOBStatus;
    }


    public String getPanTriggered() {
        return panTriggered;
    }

    public void setPanTriggered(String panTriggered) {
        this.panTriggered = panTriggered;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getUploadBy() {
        return uploadBy;
    }

    public void setUploadBy(String uploadBy) {
        this.uploadBy = uploadBy;
    }

    public String getTriggedMakerID() {
        return triggedMakerID;
    }

    public void setTriggedMakerID(String triggedMakerID) {
        this.triggedMakerID = triggedMakerID;
    }

    public String getTriggedCheckerID() {
        return triggedCheckerID;
    }

    public void setTriggedCheckerID(String triggedCheckerID) {
        this.triggedCheckerID = triggedCheckerID;
    }

    public String getSendNSDLDate() {
        return sendNSDLDate;
    }

    public void setSendNSDLDate(String sendNSDLDate) {
        this.sendNSDLDate = sendNSDLDate;
    }

    public String getReSendNSDLDate() {
        return reSendNSDLDate;
    }

    public void setReSendNSDLDate(String reSendNSDLDate) {
        this.reSendNSDLDate = reSendNSDLDate;
    }

    public String getReSendNSDL() {
        return reSendNSDL;
    }

    public void setReSendNSDL(String reSendNSDL) {
        this.reSendNSDL = reSendNSDL;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPanTriggeredFrom() {
        return panTriggeredFrom;
    }

    public void setPanTriggeredFrom(String panTriggeredFrom) {
        this.panTriggeredFrom = panTriggeredFrom;
    }

    public String getPanNoStatus() {
        return panNoStatus;
    }

    public void setPanNoStatus(String panNoStatus) {
        this.panNoStatus = panNoStatus;
    }

    public String getNewPANNO() {
        return newPANNO;
    }

    public void setNewPANNO(String newPANNO) {
        this.newPANNO = newPANNO;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNewDOB() {
        return newDOB;
    }

    public void setNewDOB(String newDOB) {
        this.newDOB = newDOB;
    }

    public String getNetBankingDate() {
        return netBankingDate;
    }

    public void setNetBankingDate(String netBankingDate) {
        this.netBankingDate = netBankingDate;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getFileType() {
        return fileType;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getIsSendNSDL() {
        return isSendNSDL;
    }

    public void setIsSendNSDL(String isSendNSDL) {
        this.isSendNSDL = isSendNSDL;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
    }

    public String getAadharStatus() {
        return aadharStatus;
    }

    public void setAadharStatus(String aadharStatus) {
        this.aadharStatus = aadharStatus;
    }

    public String getAadharSeeding() {
        return aadharSeeding;
    }

    public void setAadharSeeding(String aadharSeeding) {
        this.aadharSeeding = aadharSeeding;
    }


    public String getTriggedDate() {
        return triggedDate;
    }

    public void setTriggedDate(String triggedDate) {
        this.triggedDate = triggedDate;
    }

    public String getBondScheme() {
        return bondScheme;
    }

    public void setBondScheme(String bondScheme) {
        this.bondScheme = bondScheme;
    }

    public String getPageSizeIn() {
        return pageSizeIn;
    }

    public void setPageSizeIn(String pageSizeIn) {
        this.pageSizeIn = pageSizeIn;
    }



    public String getSearchValIn() {
        return searchValIn;
    }

    public void setSearchValIn(String searchValIn) {
        this.searchValIn = searchValIn;
    }



    public String getPageNoIn() {
        return pageNoIn;
    }

    public void setPageNoIn(String pageNoIn) {
        this.pageNoIn = pageNoIn;
    }

    public String getFlagIn() {
        return flagIn;
    }

    public void setFlagIn(String flagIn) {
        this.flagIn = flagIn;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceivingOffice() {
        return receivingOffice;
    }

    public void setReceivingOffice(String receivingOffice) {
        this.receivingOffice = receivingOffice;
    }

    public String getNotificationNo() {
        return notificationNo;
    }

    public void setNotificationNo(String notificationNo) {
        this.notificationNo = notificationNo;
    }

    public String getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(String notificationDate) {
        this.notificationDate = notificationDate;
    }

    public String getTotalInvestmentAmt() {
        return totalInvestmentAmt;
    }

    public void setTotalInvestmentAmt(String totalInvestmentAmt) {
        this.totalInvestmentAmt = totalInvestmentAmt;
    }

    public String getTotaltTransferAmt() {
        return totaltTransferAmt;
    }

    public void setTotaltTransferAmt(String totaltTransferAmt) {
        this.totaltTransferAmt = totaltTransferAmt;
    }

    public String getTotalRepaymentAmt() {
        return totalRepaymentAmt;
    }

    public void setTotalRepaymentAmt(String totalRepaymentAmt) {
        this.totalRepaymentAmt = totalRepaymentAmt;
    }

    private List<BondCertificateInvtReportDTO> torchOne;
    private List<BondCertificateInvtReportDTO> torchTwo;
    private List<HashMap<String, String>> torchValue;

    public List<HashMap<String, String>> getTorchValue() {
        return torchValue;
    }

    public void setTorchValue(List<HashMap<String, String>> torchValue) {
        this.torchValue = torchValue;
    }

    public String getPanHldName() {
        return panHldName;
    }

    public void setPanHldName(String panHldName) {
        this.panHldName = panHldName;
    }

    public String getSingleblaNo() {
        return singleblaNo;
    }

    public void setSingleblaNo(String singleblaNo) {
        this.singleblaNo = singleblaNo;
    }

    public String getFirstJointBla() {
        return firstJointBla;
    }

    public void setFirstJointBla(String firstJointBla) {
        this.firstJointBla = firstJointBla;
    }

    public String getSecondJoinBla() {
        return secondJoinBla;
    }

    public void setSecondJoinBla(String secondJoinBla) {
        this.secondJoinBla = secondJoinBla;
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    public String getSingleHoldName() {
        return singleHoldName;
    }

    public void setSingleHoldName(String singleHoldName) {
        this.singleHoldName = singleHoldName;
    }

    public String getSingleHoldAdd() {
        return singleHoldAdd;
    }

    public void setSingleHoldAdd(String singleHoldAdd) {
        this.singleHoldAdd = singleHoldAdd;
    }

    public String getFirstHoldName() {
        return firstHoldName;
    }

    public void setFirstHoldName(String firstHoldName) {
        this.firstHoldName = firstHoldName;
    }

    public String getFirstHoldAdd() {
        return firstHoldAdd;
    }

    public void setFirstHoldAdd(String firstHoldAdd) {
        this.firstHoldAdd = firstHoldAdd;
    }

    public String getSecondHoldName() {
        return secondHoldName;
    }

    public void setSecondHoldName(String secondHoldName) {
        this.secondHoldName = secondHoldName;
    }

    public String getSecondHoldAdd() {
        return secondHoldAdd;
    }

    public void setSecondHoldAdd(String secondHoldAdd) {
        this.secondHoldAdd = secondHoldAdd;
    }

    public String getBondAddress() {
        return bondAddress;
    }

    public void setBondAddress(String bondAddress) {
        this.bondAddress = bondAddress;
    }

    public List<BondCertificateInvtReportDTO> getTorchOne() {
        return torchOne;
    }

    public void setTorchOne(List<BondCertificateInvtReportDTO> torchOne) {
        this.torchOne = torchOne;
    }

    public List<BondCertificateInvtReportDTO> getTorchTwo() {
        return torchTwo;
    }

    public void setTorchTwo(List<BondCertificateInvtReportDTO> torchTwo) {
        this.torchTwo = torchTwo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSolId() {
        return solId;
    }

    public void setSolId(String solId) {
        this.solId = solId;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getBlaNo() {
        return blaNo;
    }

    public void setBlaNo(String blaNo) {
        this.blaNo = blaNo;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getNomineeId() {
        return nomineeId;
    }

    public void setNomineeId(String nomineeId) {
        this.nomineeId = nomineeId;
    }

    public String getNomineeName() {
        return nomineeName;
    }

    public void setNomineeName(String nomineeName) {
        this.nomineeName = nomineeName;
    }

    public String getNomineeDate() {
        return nomineeDate;
    }

    public void setNomineeDate(String nomineeDate) {
        this.nomineeDate = nomineeDate;
    }

    public String getBondHolderName() {
        return bondHolderName;
    }

    public void setBondHolderName(String bondHolderName) {
        this.bondHolderName = bondHolderName;
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

    public String getSubscriptionAmt() {
        return subscriptionAmt;
    }

    public void setSubscriptionAmt(String subscriptionAmt) {
        this.subscriptionAmt = subscriptionAmt;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getAccOpenDate() {
        return accOpenDate;
    }

    public void setAccOpenDate(String accOpenDate) {
        this.accOpenDate = accOpenDate;
    }

    public String getAppNo() {
        return appNo;
    }

    public void setAppNo(String appNo) {
        this.appNo = appNo;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
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

    public String getBondType() {
        return bondType;
    }

    public void setBondType(String bondType) {
        this.bondType = bondType;
    }

    public String getMaturityAmt() {
        return maturityAmt;
    }

    public void setMaturityAmt(String maturityAmt) {
        this.maturityAmt = maturityAmt;
    }

    public String getDepositeDate() {
        return depositeDate;
    }

    public void setDepositeDate(String depositeDate) {
        this.depositeDate = depositeDate;
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

    public String getSchemeFlag() {
        return schemeFlag;
    }

    public void setSchemeFlag(String schemeFlag) {
        this.schemeFlag = schemeFlag;
    }

    public String getNasikDate() {
        return nasikDate;
    }

    public void setNasikDate(String nasikDate) {
        this.nasikDate = nasikDate;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getIntrestRate() {
        return intrestRate;
    }

    public void setIntrestRate(String intrestRate) {
        this.intrestRate = intrestRate;
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

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }

    public String getTransferAmt() {
        return transferAmt;
    }

    public void setTransferAmt(String transferAmt) {
        this.transferAmt = transferAmt;
    }

    public String getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(String repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public String getRepaymentAmt() {
        return repaymentAmt;
    }

    public void setRepaymentAmt(String repaymentAmt) {
        this.repaymentAmt = repaymentAmt;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getInitialUndrBankSeal() {
        return initialUndrBankSeal;
    }

    public void setInitialUndrBankSeal(String initialUndrBankSeal) {
        this.initialUndrBankSeal = initialUndrBankSeal;
    }

    public String getIntPaymtDate1() {
        return intPaymtDate1;
    }

    public void setIntPaymtDate1(String intPaymtDate1) {
        this.intPaymtDate1 = intPaymtDate1;
    }

    public String getIntPaymtDate2() {
        return intPaymtDate2;
    }

    public void setIntPaymtDate2(String intPaymtDate2) {
        this.intPaymtDate2 = intPaymtDate2;
    }

    public String getInvestmentDate() {
        return investmentDate;
    }

    public void setInvestmentDate(String investmentDate) {
        this.investmentDate = investmentDate;
    }

    public String getInvestmentAmt() {
        return investmentAmt;
    }

    public void setInvestmentAmt(String investmentAmt) {
        this.investmentAmt = investmentAmt;
    }

}
