package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FreezeUnFreezeWrapperDTO {

    private String amountFreeze;
    private String interestFreeze;
    private String transferAnotherBank;
    private String makerRemarks;
    private String checkerRemarks;
    private List<FreezUnfreezDTO> freezeUnfreezeDTOList;
    private List<OldValueNewValueDTO> oldValueNewValueList;
    private String modifiedBy;
    private String modifiedDate;
    private String verifiedBy;
    private String verifiedDate;
    private String createdBy;
    private String createdDate;
    private String verifiedStatus;
    private String pkColValue;
    private String preOrpTyp;
    private String colNo;
    private String amountFreezeOldValue;
    private String amountFreezeNewValue;
    private String interestFreezeOldValue;
    private String interestFreezeNewValue;
    private String transferAnotherBankOldValue;
    private String transferAnotherBankNewValue;
    private String makerRemarksOldValue;
    private String makerRemarksNewValue;
    private String checkerRemarksOldValue;
    private String checkerRemarksNewValue;

    public String getMakerRemarksOldValue() {
        return makerRemarksOldValue;
    }

    public void setMakerRemarksOldValue(String makerRemarksOldValue) {
        this.makerRemarksOldValue = makerRemarksOldValue;
    }

    public String getMakerRemarksNewValue() {
        return makerRemarksNewValue;
    }

    public void setMakerRemarksNewValue(String makerRemarksNewValue) {
        this.makerRemarksNewValue = makerRemarksNewValue;
    }

    public String getCheckerRemarksOldValue() {
        return checkerRemarksOldValue;
    }

    public void setCheckerRemarksOldValue(String checkerRemarksOldValue) {
        this.checkerRemarksOldValue = checkerRemarksOldValue;
    }

    public String getCheckerRemarksNewValue() {
        return checkerRemarksNewValue;
    }

    public void setCheckerRemarksNewValue(String checkerRemarksNewValue) {
        this.checkerRemarksNewValue = checkerRemarksNewValue;
    }

    public String getColNo() {
        return colNo;
    }

    public void setColNo(String colNo) {
        this.colNo = colNo;
    }

    public String getAmountFreezeOldValue() {
        return amountFreezeOldValue;
    }

    public void setAmountFreezeOldValue(String amountFreezeOldValue) {
        this.amountFreezeOldValue = amountFreezeOldValue;
    }

    public String getAmountFreezeNewValue() {
        return amountFreezeNewValue;
    }

    public void setAmountFreezeNewValue(String amountFreezeNewValue) {
        this.amountFreezeNewValue = amountFreezeNewValue;
    }

    public String getInterestFreezeOldValue() {
        return interestFreezeOldValue;
    }

    public void setInterestFreezeOldValue(String interestFreezeOldValue) {
        this.interestFreezeOldValue = interestFreezeOldValue;
    }

    public String getInterestFreezeNewValue() {
        return interestFreezeNewValue;
    }

    public void setInterestFreezeNewValue(String interestFreezeNewValue) {
        this.interestFreezeNewValue = interestFreezeNewValue;
    }

    public String getTransferAnotherBankOldValue() {
        return transferAnotherBankOldValue;
    }

    public void setTransferAnotherBankOldValue(String transferAnotherBankOldValue) {
        this.transferAnotherBankOldValue = transferAnotherBankOldValue;
    }

    public String getTransferAnotherBankNewValue() {
        return transferAnotherBankNewValue;
    }

    public void setTransferAnotherBankNewValue(String transferAnotherBankNewValue) {
        this.transferAnotherBankNewValue = transferAnotherBankNewValue;
    }

    @Override
    public String toString() {
        return "FreezeUnFreezeWrapperDTO{" +
                "amountFreeze='" + amountFreeze + '\'' +
                ", interestFreeze='" + interestFreeze + '\'' +
                ", transferAnotherBank='" + transferAnotherBank + '\'' +
                ", makerRemarks='" + makerRemarks + '\'' +
                ", checkerRemarks='" + checkerRemarks + '\'' +
                ", freezUnfreezeDTO=" + freezeUnfreezeDTOList +
                '}';
    }

    public List<OldValueNewValueDTO> getOldValueNewValueList() {
        return oldValueNewValueList;
    }

    public void setOldValueNewValueList(List<OldValueNewValueDTO> oldValueNewValueList) {
        this.oldValueNewValueList = oldValueNewValueList;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public String getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(String verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setVerifiedStatus(String verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    public String getPkColValue() {
        return pkColValue;
    }

    public void setPkColValue(String pkColValue) {
        this.pkColValue = pkColValue;
    }

    public String getPreOrpTyp() {
        return preOrpTyp;
    }

    public void setPreOrpTyp(String preOrpTyp) {
        this.preOrpTyp = preOrpTyp;
    }

    public String getAmountFreeze() {
        return amountFreeze;
    }

    public void setAmountFreeze(String amountFreeze) {
        this.amountFreeze = amountFreeze;
    }

    public String getInterestFreeze() {
        return interestFreeze;
    }

    public void setInterestFreeze(String interestFreeze) {
        this.interestFreeze = interestFreeze;
    }

    public String getTransferAnotherBank() {
        return transferAnotherBank;
    }

    public void setTransferAnotherBank(String transferAnotherBank) {
        this.transferAnotherBank = transferAnotherBank;
    }

    public String getMakerRemarks() {
        return makerRemarks;
    }

    public void setMakerRemarks(String makerRemarks) {
        this.makerRemarks = makerRemarks;
    }

    public String getCheckerRemarks() {
        return checkerRemarks;
    }

    public void setCheckerRemarks(String checkerRemarks) {
        this.checkerRemarks = checkerRemarks;
    }

    public List<FreezUnfreezDTO> getFreezeUnfreezeDTOList() {
        return freezeUnfreezeDTOList;
    }

    public void setFreezeUnfreezeDTOList(List<FreezUnfreezDTO> freezeUnfreezeDTOList) {
        this.freezeUnfreezeDTOList = freezeUnfreezeDTOList;
    }
}

