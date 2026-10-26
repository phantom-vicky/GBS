package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CafuFileChequeRecordDTO {

    private String recordIdentifier;
    private String instrumentNumber;
    private String draweeBankCode;
    private String routingCode;
    private String instrumentAmount;
    private String instrumentDate;
    private String drawerName;
    private String clearingLocation;
    private String enrichment;
    private String chksum;
    private String email1stApplicant;
    private String mobileNo;
    private String panNo;
    private String typeOfApplicant;
    private String checkListNumber;
    private String existingBlaNumber;
    private String cohCoi;
    private String bankNameInterestPayment;
    private String accountNoInterestPayment;
    private String ifscCodeInterestPayment;
    private String operatingInstructions;
    private String schCode;

    public String getEmail1stApplicant() {
        return email1stApplicant;
    }

    public void setEmail1stApplicant(String email1stApplicant) {
        this.email1stApplicant = email1stApplicant;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getTypeOfApplicant() {
        return typeOfApplicant;
    }

    public void setTypeOfApplicant(String typeOfApplicant) {
        this.typeOfApplicant = typeOfApplicant;
    }

    public String getCheckListNumber() {
        return checkListNumber;
    }

    public void setCheckListNumber(String checkListNumber) {
        this.checkListNumber = checkListNumber;
    }

    public String getExistingBlaNumber() {
        return existingBlaNumber;
    }

    public void setExistingBlaNumber(String existingBlaNumber) {
        this.existingBlaNumber = existingBlaNumber;
    }

    public String getCohCoi() {
        return cohCoi;
    }

    public void setCohCoi(String cohCoi) {
        this.cohCoi = cohCoi;
    }

    public String getBankNameInterestPayment() {
        return bankNameInterestPayment;
    }

    public void setBankNameInterestPayment(String bankNameInterestPayment) {
        this.bankNameInterestPayment = bankNameInterestPayment;
    }

    public String getAccountNoInterestPayment() {
        return accountNoInterestPayment;
    }

    public void setAccountNoInterestPayment(String accountNoInterestPayment) {
        this.accountNoInterestPayment = accountNoInterestPayment;
    }

    public String getIfscCodeInterestPayment() {
        return ifscCodeInterestPayment;
    }

    public void setIfscCodeInterestPayment(String ifscCodeInterestPayment) {
        this.ifscCodeInterestPayment = ifscCodeInterestPayment;
    }

    public String getOperatingInstructions() {
        return operatingInstructions;
    }

    public void setOperatingInstructions(String operatingInstructions) {
        this.operatingInstructions = operatingInstructions;
    }

    public String getSchCode() {
        return schCode;
    }

    public void setSchCode(String schCode) {
        this.schCode = schCode;
    }

    public String getRecordIdentifier() {
        return recordIdentifier;
    }

    public void setRecordIdentifier(String recordIdentifier) {
        this.recordIdentifier = recordIdentifier;
    }

    public String getInstrumentNumber() {
        return instrumentNumber;
    }

    public void setInstrumentNumber(String instrumentNumber) {
        this.instrumentNumber = instrumentNumber;
    }

    public String getDraweeBankCode() {
        return draweeBankCode;
    }

    public void setDraweeBankCode(String draweeBankCode) {
        this.draweeBankCode = draweeBankCode;
    }

    public String getRoutingCode() {
        return routingCode;
    }

    public void setRoutingCode(String routingCode) {
        this.routingCode = routingCode;
    }

    public String getInstrumentAmount() {
        return instrumentAmount;
    }

    public void setInstrumentAmount(String instrumentAmount) {
        this.instrumentAmount = instrumentAmount;
    }

    public String getInstrumentDate() {
        return instrumentDate;
    }

    public void setInstrumentDate(String instrumentDate) {
        this.instrumentDate = instrumentDate;
    }

    public String getDrawerName() {
        return drawerName;
    }

    public void setDrawerName(String drawerName) {
        this.drawerName = drawerName;
    }

    public String getClearingLocation() {
        return clearingLocation;
    }

    public void setClearingLocation(String clearingLocation) {
        this.clearingLocation = clearingLocation;
    }

    public String getEnrichment() {
        return enrichment;
    }

    public void setEnrichment(String enrichment) {
        this.enrichment = enrichment;
    }

    public String getChksum() {
        return chksum;
    }

    public void setChksum(String chksum) {
        this.chksum = chksum;
    }
}
