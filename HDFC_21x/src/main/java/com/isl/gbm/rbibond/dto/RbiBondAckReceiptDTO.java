package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RbiBondAckReceiptDTO {
    private String transDate;
    private String bondHolderName;
    private String transactionMode;
    private String debitAccNum;
    private String branchId;
    private String instrumentType;
    private String instrumentNum;
    private String instrumentDate;
    private String amount;
    private String delDate;
    private String remarks;
    private String userName;
    private String solId;
    private String schemeCode;
    private String solDate;
    private String applicationNo;
    private String applicationDate;
    private String bankAgencyCode;
    private String borkerName;
    private String brokerId;
    private String Id;
    private String accountOpening;
    private String numId;

    public String getNumId() {
        return numId;
    }

    public void setNumId(String numId) {
        this.numId = numId;
    }

    public String getAccountOpening() {
        return accountOpening;
    }

    public void setAccountOpening(String accountOpening) {
        this.accountOpening = accountOpening;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
    //    private String schemeCode;


    public String getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId;
    }

    public String getBankAgencyCode() {
        return bankAgencyCode;
    }

    public void setBankAgencyCode(String bankAgencyCode) {
        this.bankAgencyCode = bankAgencyCode;
    }

    public String getBorkerName() {
        return borkerName;
    }

    public void setBorkerName(String borkerName) {
        this.borkerName = borkerName;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    private List<RbiBondAckReceiptDTO> rbiBondAckReceiptDTOlist;

    public List<RbiBondAckReceiptDTO> getRbiBondAckReceiptDTOlist() {
        return rbiBondAckReceiptDTOlist;
    }

    public void setRbiBondAckReceiptDTOlist(List<RbiBondAckReceiptDTO> rbiBondAckReceiptDTOlist) {
        this.rbiBondAckReceiptDTOlist = rbiBondAckReceiptDTOlist;
    }

    public String getSolDate() {
        return solDate;
    }

    public void setSolDate(String solDate) {
        this.solDate = solDate;
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

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getBondHolderName() {
        return bondHolderName;
    }

    public void setBondHolderName(String bondHolderName) {
        this.bondHolderName = bondHolderName;
    }

    public String getTransactionMode() {
        return transactionMode;
    }

    public void setTransactionMode(String transactionMode) {
        this.transactionMode = transactionMode;
    }

    public String getDebitAccNum() {
        return debitAccNum;
    }

    public void setDebitAccNum(String debitAccNum) {
        this.debitAccNum = debitAccNum;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    }

    public String getInstrumentNum() {
        return instrumentNum;
    }

    public void setInstrumentNum(String instrumentNum) {
        this.instrumentNum = instrumentNum;
    }

    public String getInstrumentDate() {
        return instrumentDate;
    }

    public void setInstrumentDate(String instrumentDate) {
        this.instrumentDate = instrumentDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDelDate() {
        return delDate;
    }

    public void setDelDate(String delDate) {
        this.delDate = delDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
