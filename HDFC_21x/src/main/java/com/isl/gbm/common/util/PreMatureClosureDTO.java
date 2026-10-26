package com.isl.gbm.common.util;

import com.isl.gbm.transaction.channel.GbmTransactionMasterDto;

import java.util.List;
import java.util.Map;

public class PreMatureClosureDTO {

    private String custId;

    private String oprBranchId;

    public String getOprBranchId() {
        return oprBranchId;
    }

    public void setOprBranchId(String oprBranchId) {
        this.oprBranchId = oprBranchId;
    }

    private String ppfAcctId;

    private String details;

    private String paymentThrough;

    private String guardianId;

    private List<Map<String, Object>> acctOldNewMapDetails;

    private String creditActNum;

    private String debitActNum;

    private String creditFlag;

    private String debitFlag;

    private String totDepAmt;

    private String intRate;

    private String intAmt;

    private String intToDate;

    private String intFromDate;

    private String creditActType;

    private String debitActType;

    private String amt;

    private String remarks;

    private String createdBy;

    private String createdDate;

    private String verifiedBy;

    private String verifiedDate;

    private String verifyStatus;

    private String closureReason;

    private String paidTo;

    private String recIntAmt;

    private String recIntFrom;

    private String recIntTo;

    private String preIntAmt;

    private String preIntTo;

    private String preIntFrom;

    private String matPeriodDate;

    private String settledTo;


    private String gbmTranId;

    private String channelType;

    private List<GbmTransactionMasterDto> tranList;

    private String cbsTranId;

    private String cbsTranStatus;

    private String cbsTranDescription;

    private String penaltyAmt;

    private String payeeName;

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPenaltyAmt() {
        return penaltyAmt;
    }

    public void setPenaltyAmt(String penaltyAmt) {
        this.penaltyAmt = penaltyAmt;
    }

    public String getGbmTranId() {
        return gbmTranId;
    }

    public void setGbmTranId(String gbmTranId) {
        this.gbmTranId = gbmTranId;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public List<GbmTransactionMasterDto> getTranList() {
        return tranList;
    }

    public void setTranList(List<GbmTransactionMasterDto> tranList) {
        this.tranList = tranList;
    }

    public String getCbsTranId() {
        return cbsTranId;
    }

    public void setCbsTranId(String cbsTranId) {
        this.cbsTranId = cbsTranId;
    }

    public String getCbsTranStatus() {
        return cbsTranStatus;
    }

    public void setCbsTranStatus(String cbsTranStatus) {
        this.cbsTranStatus = cbsTranStatus;
    }

    public String getCbsTranDescription() {
        return cbsTranDescription;
    }

    public void setCbsTranDescription(String cbsTranDescription) {
        this.cbsTranDescription = cbsTranDescription;
    }

    private List<Map<String, Object>> nomineeDetails;

    public String getMatPeriodDate() {
        return matPeriodDate;
    }

    public void setMatPeriodDate(String matPeriodDate) {
        this.matPeriodDate = matPeriodDate;
    }

    public String getSettledTo() {
        return settledTo;
    }

    public void setSettledTo(String settledTo) {
        this.settledTo = settledTo;
    }

    public List<Map<String, Object>> getNomineeDetails() {
        return nomineeDetails;
    }

    public void setNomineeDetails(List<Map<String, Object>> nomineeDetails) {
        this.nomineeDetails = nomineeDetails;
    }

    public String getRecIntAmt() {
        return recIntAmt;
    }

    public void setRecIntAmt(String recIntAmt) {
        this.recIntAmt = recIntAmt;
    }

    public String getRecIntFrom() {
        return recIntFrom;
    }

    public void setRecIntFrom(String recIntFrom) {
        this.recIntFrom = recIntFrom;
    }

    public String getRecIntTo() {
        return recIntTo;
    }

    public void setRecIntTo(String recIntTo) {
        this.recIntTo = recIntTo;
    }

    public String getPreIntAmt() {
        return preIntAmt;
    }

    public void setPreIntAmt(String preIntAmt) {
        this.preIntAmt = preIntAmt;
    }

    public String getPreIntTo() {
        return preIntTo;
    }

    public void setPreIntTo(String preIntTo) {
        this.preIntTo = preIntTo;
    }

    public String getPreIntFrom() {
        return preIntFrom;
    }

    public void setPreIntFrom(String preIntFrom) {
        this.preIntFrom = preIntFrom;
    }

    private List<GuardianDetailsDTO> guardianDetails;

    public List<GuardianDetailsDTO> getGuardianDetails() {
        return guardianDetails;
    }

    public void setGuardianDetails(List<GuardianDetailsDTO> guardianDetails) {
        this.guardianDetails = guardianDetails;
    }

    public String getPaidTo() {
        return paidTo;
    }

    public void setPaidTo(String paidTo) {
        this.paidTo = paidTo;
    }

    public String getGuardianId() {
        return guardianId;
    }

    public void setGuardianId(String guardianId) {
        this.guardianId = guardianId;
    }

    public String getClosureReason() {
        return closureReason;
    }

    public void setClosureReason(String closureReason) {
        this.closureReason = closureReason;
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

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public List<Map<String, Object>> getAcctOldNewMapDetails() {
        return acctOldNewMapDetails;
    }

    public void setAcctOldNewMapDetails(List<Map<String, Object>> acctOldNewMapDetails) {
        this.acctOldNewMapDetails = acctOldNewMapDetails;
    }

    public String getCreditActNum() {
        return creditActNum;
    }

    public void setCreditActNum(String creditActNum) {
        this.creditActNum = creditActNum;
    }

    public String getDebitActNum() {
        return debitActNum;
    }

    public void setDebitActNum(String debitActNum) {
        this.debitActNum = debitActNum;
    }

    public String getCreditFlag() {
        return creditFlag;
    }

    public void setCreditFlag(String creditFlag) {
        this.creditFlag = creditFlag;
    }

    public String getDebitFlag() {
        return debitFlag;
    }

    public void setDebitFlag(String debitFlag) {
        this.debitFlag = debitFlag;
    }

    public String getTotDepAmt() {
        return totDepAmt;
    }

    public void setTotDepAmt(String totDepAmt) {
        this.totDepAmt = totDepAmt;
    }

    public String getIntRate() {
        return intRate;
    }

    public void setIntRate(String intRate) {
        this.intRate = intRate;
    }

    public String getIntAmt() {
        return intAmt;
    }

    public void setIntAmt(String intAmt) {
        this.intAmt = intAmt;
    }

    public String getIntToDate() {
        return intToDate;
    }

    public void setIntToDate(String intToDate) {
        this.intToDate = intToDate;
    }

    public String getIntFromDate() {
        return intFromDate;
    }

    public void setIntFromDate(String intFromDate) {
        this.intFromDate = intFromDate;
    }

    public String getCreditActType() {
        return creditActType;
    }

    public void setCreditActType(String creditActType) {
        this.creditActType = creditActType;
    }

    public String getDebitActType() {
        return debitActType;
    }

    public void setDebitActType(String debitActType) {
        this.debitActType = debitActType;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getPpfAcctId() {
        return ppfAcctId;
    }

    public void setPpfAcctId(String ppfAcctId) {
        this.ppfAcctId = ppfAcctId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPaymentThrough() {
        return paymentThrough;
    }

    public void setPaymentThrough(String paymentThrough) {
        this.paymentThrough = paymentThrough;
    }
}