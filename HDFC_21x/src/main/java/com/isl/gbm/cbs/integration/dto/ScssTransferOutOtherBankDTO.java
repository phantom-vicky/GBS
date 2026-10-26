package com.isl.gbm.cbs.integration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.isl.gbm.common.dto.ColumnValueDTO;
import com.isl.gbm.rbibond.dto.ScssGttAccountOpeningDto;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScssTransferOutOtherBankDTO {

    private String schemeCode;

    private String scssCustId;

    private String scssCustomerName;

    private String scssAcctNum;

    private String accountType;

    private String creditAcctNum;

    private String debitAcctNum;

    private String debitFlag;

    private String creditFlag;

    private String transferDebitFlag;

    private String transferCreditFlag;

    private String transferDebitAcctNum;

    private String transfercreditAcctNum;

    private String branchOut;

    private String transferFeeAmt;

    private String tranOut;

    private String depositAmt;

    private String transferBankId;

    private String transferBranchId;

    private String transferThrough;

    private String proceedCreditAcctNum;

    private String transferWaiver;

    private String remarks;

    private String gbmTranId;

    private String tranDate;

    private String cbsTranId;

    private String cbsTranStatus;

    private String cbsTranStatusDescription;

    private String channelType;

    private String screenTranCode;

    private String creditAmt;

    private String creditTranLeg;

    private String debitAmt;

    private String debitTranLeg;

    private String verifiedStatus;

    private String transferType;

    private List<List<ColumnValueDTO>> customerMasterDetailsDtoList;

    private List<Map<String, Object>> masterDetailsValueModel;

    private List<Map<String, Object>> nominationDetailsValueModel;

    public List<ScssTransferOutOtherBankDTO> scssTransferOutOtherBankDTOList;

    public List<ScssGttAccountOpeningDto> scssGttAccountOpeningDtoList;

    public String getScssCustomerName() {
        return scssCustomerName;
    }

    public void setScssCustomerName(String scssCustomerName) {
        this.scssCustomerName = scssCustomerName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public List<ScssTransferOutOtherBankDTO> getScssTransferOutOtherBankDTOList() {
        return scssTransferOutOtherBankDTOList;
    }

    public void setScssTransferOutOtherBankDTOList(List<ScssTransferOutOtherBankDTO> scssTransferOutOtherBankDTOList) {
        this.scssTransferOutOtherBankDTOList = scssTransferOutOtherBankDTOList;
    }

    public String getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setVerifiedStatus(String verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    public String getScssCustId() {
        return scssCustId;
    }

    public void setScssCustId(String scssCustId) {
        this.scssCustId = scssCustId;
    }

    public String getScssAcctNum() {
        return scssAcctNum;
    }

    public void setScssAcctNum(String scssAcctNum) {
        this.scssAcctNum = scssAcctNum;
    }

    public List<Map<String, Object>> getMasterDetailsValueModel() {
        return masterDetailsValueModel;
    }

    public void setMasterDetailsValueModel(List<Map<String, Object>> masterDetailsValueModel) {
        this.masterDetailsValueModel = masterDetailsValueModel;
    }

    public List<Map<String, Object>> getNominationDetailsValueModel() {
        return nominationDetailsValueModel;
    }

    public void setNominationDetailsValueModel(List<Map<String, Object>> nominationDetailsValueModel) {
        this.nominationDetailsValueModel = nominationDetailsValueModel;
    }

    public String getCreditAcctNum() {
        return creditAcctNum;
    }

    public void setCreditAcctNum(String creditAcctNum) {
        this.creditAcctNum = creditAcctNum;
    }

    public String getDebitAcctNum() {
        return debitAcctNum;
    }

    public void setDebitAcctNum(String debitAcctNum) {
        this.debitAcctNum = debitAcctNum;
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

    public String getTransferDebitFlag() {
        return transferDebitFlag;
    }

    public void setTransferDebitFlag(String transferDebitFlag) {
        this.transferDebitFlag = transferDebitFlag;
    }

    public String getTransferCreditFlag() {
        return transferCreditFlag;
    }

    public void setTransferCreditFlag(String transferCreditFlag) {
        this.transferCreditFlag = transferCreditFlag;
    }

    public String getTransferDebitAcctNum() {
        return transferDebitAcctNum;
    }

    public void setTransferDebitAcctNum(String transferDebitAcctNum) {
        this.transferDebitAcctNum = transferDebitAcctNum;
    }

    public String getTransfercreditAcctNum() {
        return transfercreditAcctNum;
    }

    public void setTransfercreditAcctNum(String transfercreditAcctNum) {
        this.transfercreditAcctNum = transfercreditAcctNum;
    }

    public String getBranchOut() {
        return branchOut;
    }

    public void setBranchOut(String branchOut) {
        this.branchOut = branchOut;
    }

    public String getTransferFeeAmt() {
        return transferFeeAmt;
    }

    public void setTransferFeeAmt(String transferFeeAmt) {
        this.transferFeeAmt = transferFeeAmt;
    }


    public String getTranOut() {
        return tranOut;
    }

    public void setTranOut(String tranOut) {
        this.tranOut = tranOut;
    }

    public String getDepositAmt() {
        return depositAmt;
    }

    public void setDepositAmt(String depositAmt) {
        this.depositAmt = depositAmt;
    }

    public String getTransferBankId() {
        return transferBankId;
    }

    public void setTransferBankId(String transferBankId) {
        this.transferBankId = transferBankId;
    }

    public String getTransferBranchId() {
        return transferBranchId;
    }

    public void setTransferBranchId(String transferBranchId) {
        this.transferBranchId = transferBranchId;
    }

    public String getTransferThrough() {
        return transferThrough;
    }

    public void setTransferThrough(String transferThrough) {
        this.transferThrough = transferThrough;
    }

    public String getProceedCreditAcctNum() {
        return proceedCreditAcctNum;
    }

    public void setProceedCreditAcctNum(String proceedCreditAcctNum) {
        this.proceedCreditAcctNum = proceedCreditAcctNum;
    }

    public String getTransferWaiver() {
        return transferWaiver;
    }

    public void setTransferWaiver(String transferWaiver) {
        this.transferWaiver = transferWaiver;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getGbmTranId() {
        return gbmTranId;
    }

    public void setGbmTranId(String gbmTranId) {
        this.gbmTranId = gbmTranId;
    }

    public List<List<ColumnValueDTO>> getCustomerMasterDetailsDtoList() {
        return customerMasterDetailsDtoList;
    }

    public void setCustomerMasterDetailsDtoList(List<List<ColumnValueDTO>> customerMasterDetailsDtoList) {
        this.customerMasterDetailsDtoList = customerMasterDetailsDtoList;
    }

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
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

    public String getCbsTranStatusDescription() {
        return cbsTranStatusDescription;
    }

    public void setCbsTranStatusDescription(String cbsTranStatusDescription) {
        this.cbsTranStatusDescription = cbsTranStatusDescription;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public List<ScssGttAccountOpeningDto> getScssGttAccountOpeningDtoList() {
        return scssGttAccountOpeningDtoList;
    }

    public void setScssGttAccountOpeningDtoList(List<ScssGttAccountOpeningDto> scssGttAccountOpeningDtoList) {
        this.scssGttAccountOpeningDtoList = scssGttAccountOpeningDtoList;
    }

    public String getScreenTranCode() {
        return screenTranCode;
    }

    public void setScreenTranCode(String screenTranCode) {
        this.screenTranCode = screenTranCode;
    }

    public String getCreditAmt() {
        return creditAmt;
    }

    public void setCreditAmt(String creditAmt) {
        this.creditAmt = creditAmt;
    }

    public String getCreditTranLeg() {
        return creditTranLeg;
    }

    public void setCreditTranLeg(String creditTranLeg) {
        this.creditTranLeg = creditTranLeg;
    }

    public String getDebitAmt() {
        return debitAmt;
    }

    public void setDebitAmt(String debitAmt) {
        this.debitAmt = debitAmt;
    }

    public String getDebitTranLeg() {
        return debitTranLeg;
    }

    public void setDebitTranLeg(String debitTranLeg) {
        this.debitTranLeg = debitTranLeg;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }
}
