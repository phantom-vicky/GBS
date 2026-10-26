package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BondInterestPaymentFileHeaderDTO {

    //   Other Bank Customer Details For Headers
    private String achTranCode;
    private String control1;
    private String userName;
    private String control2;
    private String achFileNo;
    private String control3;
    private String control4;
    private String ledgerFolioNo;
    private String userDefLimit;
    private String totalAmt;
    private String settlementDate;
    private String itemSeqNo;
    private String checkSum;
    private String filler1;
    private String userNo;
    private String userRef;
    private String sponsorBankIFSCMICR;
    private String userBankAccNo;
    private String totalItems;
    private String settlementCycle;
    private String filler2;

    @Override
    public String toString() {
        return "BondInterestPaymentFileHeaderDTO{" +
                "achTranCode='" + achTranCode + '\'' +
                ", control1='" + control1 + '\'' +
                ", userName='" + userName + '\'' +
                ", control2='" + control2 + '\'' +
                ", achFileNo='" + achFileNo + '\'' +
                ", control3='" + control3 + '\'' +
                ", control4='" + control4 + '\'' +
                ", ledgerFolioNo='" + ledgerFolioNo + '\'' +
                ", userDefLimit='" + userDefLimit + '\'' +
                ", totalAmt='" + totalAmt + '\'' +
                ", settlementDate='" + settlementDate + '\'' +
                ", itemSeqNo='" + itemSeqNo + '\'' +
                ", checkSum='" + checkSum + '\'' +
                ", filler1='" + filler1 + '\'' +
                ", userNo='" + userNo + '\'' +
                ", userRef='" + userRef + '\'' +
                ", sponsorBankIFSCMICR='" + sponsorBankIFSCMICR + '\'' +
                ", userBankAccNo='" + userBankAccNo + '\'' +
                ", totalItems='" + totalItems + '\'' +
                ", settlementCycle='" + settlementCycle + '\'' +
                ", filler2='" + filler2 + '\'' +
                '}';
    }

    public String getAchTranCode() {
        return achTranCode;
    }

    public void setAchTranCode(String achTranCode) {
        this.achTranCode = achTranCode;
    }

    public String getControl1() {
        return control1;
    }

    public void setControl1(String control1) {
        this.control1 = control1;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getControl2() {
        return control2;
    }

    public void setControl2(String control2) {
        this.control2 = control2;
    }

    public String getAchFileNo() {
        return achFileNo;
    }

    public void setAchFileNo(String achFileNo) {
        this.achFileNo = achFileNo;
    }

    public String getControl3() {
        return control3;
    }

    public void setControl3(String control3) {
        this.control3 = control3;
    }

    public String getControl4() {
        return control4;
    }

    public void setControl4(String control4) {
        this.control4 = control4;
    }

    public String getLedgerFolioNo() {
        return ledgerFolioNo;
    }

    public void setLedgerFolioNo(String ledgerFolioNo) {
        this.ledgerFolioNo = ledgerFolioNo;
    }

    public String getUserDefLimit() {
        return userDefLimit;
    }

    public void setUserDefLimit(String userDefLimit) {
        this.userDefLimit = userDefLimit;
    }

    public String getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(String totalAmt) {
        this.totalAmt = totalAmt;
    }

    public String getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getItemSeqNo() {
        return itemSeqNo;
    }

    public void setItemSeqNo(String itemSeqNo) {
        this.itemSeqNo = itemSeqNo;
    }

    public String getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }

    public String getFiller1() {
        return filler1;
    }

    public void setFiller1(String filler1) {
        this.filler1 = filler1;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserRef() {
        return userRef;
    }

    public void setUserRef(String userRef) {
        this.userRef = userRef;
    }

    public String getSponsorBankIFSCMICR() {
        return sponsorBankIFSCMICR;
    }

    public void setSponsorBankIFSCMICR(String sponsorBankIFSCMICR) {
        this.sponsorBankIFSCMICR = sponsorBankIFSCMICR;
    }

    public String getUserBankAccNo() {
        return userBankAccNo;
    }

    public void setUserBankAccNo(String userBankAccNo) {
        this.userBankAccNo = userBankAccNo;
    }

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public String getSettlementCycle() {
        return settlementCycle;
    }

    public void setSettlementCycle(String settlementCycle) {
        this.settlementCycle = settlementCycle;
    }

    public String getFiller2() {
        return filler2;
    }

    public void setFiller2(String filler2) {
        this.filler2 = filler2;
    }
}
