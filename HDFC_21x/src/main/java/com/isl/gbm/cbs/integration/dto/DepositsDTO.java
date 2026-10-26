package com.isl.gbm.cbs.integration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.isl.gbm.transaction.channel.GbmTransactionMasterDto;

import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepositsDTO {

    private List<Map<String, Object>> acctDetails;

    private List<Map<String, Object>> tranDetails;

    private List<Map<String, Object>> nomineeDetails;

    private List<Map<String, Object>> instDetails;

    private List<Map<String, Object>> depositDetails;

    private String depAmount;

    private String finYearAmt;

    private String creditActNum;

    private String debitActNum;

    private String debitFlag;

    private String creditFlag;

    private String custId;

    private String ssaAcctId;

    private String depositAmt;

    private String tranType;

    private String creditActType;

    private String debitActType;

    private String instType;
    private String instNum;
    private String zoneCode;
    private String isInterSolTorch;
    private String isInterSol;
    private String instDate;

    private String zoneDate;

    private String setId;

    private String valueDate;

    private String remarks;


    private String createdBy;

    private String createdDate;

    private String verifiedBy;

    private String verifiedDate;

    private String verifyStatus;

    private String gbmTranId;

    private String channelType;

    private String cbsTranId;

    private String cbsTranStatus;

    private String cbsTranDescription;

    private String isCbsTransaction;

    private String baseBranch;

    public String getBaseBranch() {
        return baseBranch;
    }

    public String getIsInterSolTorch() {
        return isInterSolTorch;
    }

    public void setIsInterSolTorch(String isInterSolTorch) {
        this.isInterSolTorch = isInterSolTorch;
    }

    public String getIsInterSol() {
        return isInterSol;
    }

    public void setIsInterSol(String isInterSol) {
        this.isInterSol = isInterSol;
    }

    public void setBaseBranch(String baseBranch) {
        this.baseBranch = baseBranch;
    }

    public String getIsCbsTransaction() {
        return isCbsTransaction;
    }

    public void setIsCbsTransaction(String isCbsTransaction) {
        this.isCbsTransaction = isCbsTransaction;
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

    private Map<String, Object> acctOldNewMap;

    private Map<String, Object> tranOldNewMap;

    private Map<String, Object> nomOldNewMap;

    private Map<String, Object> depOldNewMap;

    private List<Map<String, Object>> nomineeOldNewDetails;

    private List<Map<String, Object>> instOldNewDetails;

    private List<GbmTransactionMasterDto> tranList;

    public List<GbmTransactionMasterDto> getTranList() {
        return tranList;
    }

    public void setTranList(List<GbmTransactionMasterDto> tranList) {
        this.tranList = tranList;
    }

    public List<Map<String, Object>> getInstOldNewDetails() {
        return instOldNewDetails;
    }

    public void setInstOldNewDetails(List<Map<String, Object>> instOldNewDetails) {
        this.instOldNewDetails = instOldNewDetails;
    }

    public List<Map<String, Object>> getNomineeOldNewDetails() {
        return nomineeOldNewDetails;
    }

    public void setNomineeOldNewDetails(List<Map<String, Object>> nomineeOldNewDetails) {
        this.nomineeOldNewDetails = nomineeOldNewDetails;
    }

    public Map<String, Object> getAcctOldNewMap() {
        return acctOldNewMap;
    }

    public void setAcctOldNewMap(Map<String, Object> acctOldNewMap) {
        this.acctOldNewMap = acctOldNewMap;
    }

    public Map<String, Object> getTranOldNewMap() {
        return tranOldNewMap;
    }

    public void setTranOldNewMap(Map<String, Object> tranOldNewMap) {
        this.tranOldNewMap = tranOldNewMap;
    }

    public Map<String, Object> getNomOldNewMap() {
        return nomOldNewMap;
    }

    public void setNomOldNewMap(Map<String, Object> nomOldNewMap) {
        this.nomOldNewMap = nomOldNewMap;
    }

    public Map<String, Object> getDepOldNewMap() {
        return depOldNewMap;
    }

    public void setDepOldNewMap(Map<String, Object> depOldNewMap) {
        this.depOldNewMap = depOldNewMap;
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

    private List<DepositDetailsDTO> depositList;

    public List<DepositDetailsDTO> getDepositList() {
        return depositList;
    }

    public void setDepositList(List<DepositDetailsDTO> depositList) {
        this.depositList = depositList;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getSsaAcctId() {
        return ssaAcctId;
    }

    public void setSsaAcctId(String ssaAcctId) {
        this.ssaAcctId = ssaAcctId;
    }

    public String getDepositAmt() {
        return depositAmt;
    }

    public void setDepositAmt(String depositAmt) {
        this.depositAmt = depositAmt;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
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

    public String getInstType() {
        return instType;
    }

    public void setInstType(String instType) {
        this.instType = instType;
    }

    public String getInstNum() {
        return instNum;
    }

    public void setInstNum(String instNum) {
        this.instNum = instNum;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getInstDate() {
        return instDate;
    }

    public void setInstDate(String instDate) {
        this.instDate = instDate;
    }

    public String getZoneDate() {
        return zoneDate;
    }

    public void setZoneDate(String zoneDate) {
        this.zoneDate = zoneDate;
    }

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getDebitFlag() {
        return debitFlag;
    }

    public void setDebitFlag(String debitFlag) {
        this.debitFlag = debitFlag;
    }

    public String getCreditFlag() {
        return creditFlag;
    }

    public void setCreditFlag(String creditFlag) {
        this.creditFlag = creditFlag;
    }

    public String getDepAmount() {
        return depAmount;
    }

    public String getFinYearAmt() {
        return finYearAmt;
    }

    public void setFinYearAmt(String finYearAmt) {
        this.finYearAmt = finYearAmt;
    }

    public void setDepAmount(String depAmount) {
        this.depAmount = depAmount;
    }

    public List<Map<String, Object>> getTranDetails() {
        return tranDetails;
    }

    public void setTranDetails(List<Map<String, Object>> tranDetails) {
        this.tranDetails = tranDetails;
    }

    public List<Map<String, Object>> getNomineeDetails() {
        return nomineeDetails;
    }

    public void setNomineeDetails(List<Map<String, Object>> nomineeDetails) {
        this.nomineeDetails = nomineeDetails;
    }

    public List<Map<String, Object>> getInstDetails() {
        return instDetails;
    }

    public void setInstDetails(List<Map<String, Object>> instDetails) {
        this.instDetails = instDetails;
    }

    public List<Map<String, Object>> getDepositDetails() {
        return depositDetails;
    }

    public void setDepositDetails(List<Map<String, Object>> depositDetails) {
        this.depositDetails = depositDetails;
    }

    public List<Map<String, Object>> getAcctDetails() {
        return acctDetails;
    }

    public void setAcctDetails(List<Map<String, Object>> acctDetails) {
        this.acctDetails = acctDetails;
    }
}
