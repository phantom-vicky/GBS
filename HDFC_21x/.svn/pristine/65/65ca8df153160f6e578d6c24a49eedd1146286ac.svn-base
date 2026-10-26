package com.isl.gbm.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isl.gbm.cbs.integration.dto.GbmTransactionMasterDto;
import com.isl.gbm.cbs.integration.dto.TranDetailsDTO;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class SiExecutionDto implements Cloneable {
    private String processId;

    @NotEmpty
    private String frequency;

    @NotEmpty
    private String noOfInstallmentsOrDues;

    @JsonIgnore
    private String typeIn;

    @JsonIgnore
    private List<TranDetailsDTO> accDetailsIn;

    @JsonIgnore
    private List<GbmTransactionMasterDto> accDetailsOut;

    @JsonIgnore
    private List<com.isl.gbm.transaction.channel.GbmTransactionMasterDto> tranList;

    @JsonIgnore
    private String recordRemainingFlag = "Y";

    @JsonIgnore
    private String gbmTranId;

    @JsonIgnore
    private String cbsTranId;

    @JsonIgnore
    private String transactionStatus;

    @JsonIgnore
    private String transactionStatusDesc;

    @JsonIgnore
    private String batchRemainingFlag = "Y";

    @JsonIgnore
    private String validationRemainingFlag = "N";

    private String batchCompletedFlag = "N";

    private List<TranDet> completedBatchesDet;

    @JsonIgnore
    private List<AccountValidationDto> cbsAcctDet;

    private String validationBatchId;

    private String channelTypeOut;

    private String gbmTranIdOut;

    private String solIdOut;

    private String tranDateOut;

    private String schemeCodeOut;

    private String schemeTranCodeOut;

    private String cbsTranIdOut;

    private String cbsTranStatusOut;

    private String cbsTranStatusDesc;

    public String getValidationBatchId() {
        return validationBatchId;
    }

    public void setValidationBatchId(String validationBatchId) {
        this.validationBatchId = validationBatchId;
    }

    public String getChannelTypeOut() {
        return channelTypeOut;
    }

    public void setChannelTypeOut(String channelTypeOut) {
        this.channelTypeOut = channelTypeOut;
    }

    public String getGbmTranIdOut() {
        return gbmTranIdOut;
    }

    public void setGbmTranIdOut(String gbmTranIdOut) {
        this.gbmTranIdOut = gbmTranIdOut;
    }

    public String getSolIdOut() {
        return solIdOut;
    }

    public void setSolIdOut(String solIdOut) {
        this.solIdOut = solIdOut;
    }

    public String getTranDateOut() {
        return tranDateOut;
    }

    public void setTranDateOut(String tranDateOut) {
        this.tranDateOut = tranDateOut;
    }

    public String getSchemeCodeOut() {
        return schemeCodeOut;
    }

    public void setSchemeCodeOut(String schemeCodeOut) {
        this.schemeCodeOut = schemeCodeOut;
    }

    public String getSchemeTranCodeOut() {
        return schemeTranCodeOut;
    }

    public void setSchemeTranCodeOut(String schemeTranCodeOut) {
        this.schemeTranCodeOut = schemeTranCodeOut;
    }

    public String getCbsTranIdOut() {
        return cbsTranIdOut;
    }

    public void setCbsTranIdOut(String cbsTranIdOut) {
        this.cbsTranIdOut = cbsTranIdOut;
    }

    public String getCbsTranStatusOut() {
        return cbsTranStatusOut;
    }

    public void setCbsTranStatusOut(String cbsTranStatusOut) {
        this.cbsTranStatusOut = cbsTranStatusOut;
    }

    public String getCbsTranStatusDesc() {
        return cbsTranStatusDesc;
    }

    public void setCbsTranStatusDesc(String cbsTranStatusDesc) {
        this.cbsTranStatusDesc = cbsTranStatusDesc;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getNoOfInstallmentsOrDues() {
        return noOfInstallmentsOrDues;
    }

    public void setNoOfInstallmentsOrDues(String noOfInstallmentsOrDues) {
        this.noOfInstallmentsOrDues = noOfInstallmentsOrDues;
    }

    public String getTypeIn() {
        return typeIn;
    }

    public void setTypeIn(String typeIn) {
        this.typeIn = typeIn;
    }

    public List<TranDetailsDTO> getAccDetailsIn() {
        return accDetailsIn;
    }

    public void setAccDetailsIn(List<TranDetailsDTO> accDetailsIn) {
        this.accDetailsIn = accDetailsIn;
    }

    public List<GbmTransactionMasterDto> getAccDetailsOut() {
        return accDetailsOut;
    }

    public void setAccDetailsOut(List<GbmTransactionMasterDto> accDetailsOut) {
        this.accDetailsOut = accDetailsOut;
    }

    public List<com.isl.gbm.transaction.channel.GbmTransactionMasterDto> getTranList() {
        return tranList;
    }

    public void setTranList(List<com.isl.gbm.transaction.channel.GbmTransactionMasterDto> tranList) {
        this.tranList = tranList;
    }

    public String getRecordRemainingFlag() {
        return recordRemainingFlag;
    }

    public void setRecordRemainingFlag(String recordRemainingFlag) {
        this.recordRemainingFlag = recordRemainingFlag;
    }

    public String getGbmTranId() {
        return gbmTranId;
    }

    public void setGbmTranId(String gbmTranId) {
        this.gbmTranId = gbmTranId;
    }

    public String getCbsTranId() {
        return cbsTranId;
    }

    public void setCbsTranId(String cbsTranId) {
        this.cbsTranId = cbsTranId;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTransactionStatusDesc() {
        return transactionStatusDesc;
    }

    public void setTransactionStatusDesc(String transactionStatusDesc) {
        this.transactionStatusDesc = transactionStatusDesc;
    }

    public String getBatchRemainingFlag() {
        return batchRemainingFlag;
    }

    public void setBatchRemainingFlag(String batchRemainingFlag) {
        this.batchRemainingFlag = batchRemainingFlag;
    }

    public String getValidationRemainingFlag() {
        return validationRemainingFlag;
    }

    public void setValidationRemainingFlag(String validationRemainingFlag) {
        this.validationRemainingFlag = validationRemainingFlag;
    }

    public String getBatchCompletedFlag() {
        return batchCompletedFlag;
    }

    public void setBatchCompletedFlag(String batchCompletedFlag) {
        this.batchCompletedFlag = batchCompletedFlag;
    }

    public List<TranDet> getCompletedBatchesDet() {
        return completedBatchesDet;
    }

    public void setCompletedBatchesDet(List<TranDet> completedBatchesDet) {
        this.completedBatchesDet = completedBatchesDet;
    }

    public List<AccountValidationDto> getCbsAcctDet() {
        return cbsAcctDet;
    }

    public void setCbsAcctDet(List<AccountValidationDto> cbsAcctDet) {
        this.cbsAcctDet = cbsAcctDet;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    public static class TranDet {
        private String gbmTranId;

        private String cbsTranId;

        public String getGbmTranId() {
            return gbmTranId;
        }

        public void setGbmTranId(String gbmTranId) {
            this.gbmTranId = gbmTranId;
        }

        public String getCbsTranId() {
            return cbsTranId;
        }

        public void setCbsTranId(String cbsTranId) {
            this.cbsTranId = cbsTranId;
        }

    }

}
