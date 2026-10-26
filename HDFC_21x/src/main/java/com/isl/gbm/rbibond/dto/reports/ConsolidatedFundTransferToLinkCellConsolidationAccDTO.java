package com.isl.gbm.rbibond.dto.reports;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.isl.gbm.transaction.channel.GbmTransactionMasterDto;

import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsolidatedFundTransferToLinkCellConsolidationAccDTO {


    private String fromDate;

    private String toDate;

    private String debitFlag;
    private String creditFlag;
    private List<GbmTransactionMasterDto> tranList;

    private String cashAccNo;
    private String cashAccType;
    private String tranAccNo;
    private String tranAccType;
    private String clearAccNo;
    private String clearAccType;
    private String inetAccNo;
    private String inetAccType;
    private String creditAccNo;
    private String creditAccType;


    private List<ConsolidatedFundTransferToLinkCellConsolidationAccDTO> tranDetailsList;
    private List<ConsolidatedFundTransferToLinkCellConsolidationAccResponseDTO> tranDetailsList2;

    public List<ConsolidatedFundTransferToLinkCellConsolidationAccResponseDTO> getTranDetailsList2() {
        return tranDetailsList2;
    }

    public void setTranDetailsList2(List<ConsolidatedFundTransferToLinkCellConsolidationAccResponseDTO> tranDetailsList2) {
        this.tranDetailsList2 = tranDetailsList2;
    }

    private List<Map> dateList;

    private String totalCollAmt;
    private String gbmTranId;



    private String createdBy;
    private String createdDate;
    private String verifiedBy;
    private String verifiedDate;
    private String verifiedStatus;


    private String modeOfTransaction;
    private String debitAccNo;
    private String debitAccType;
    private String collAmt;

    private String channelType;
    private String cbsTranId;
    private String cbsTranStatus;
    private String cbsTranDescription;
    private String schemCode;
    private String screenTranCode;

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
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

    public String getSchemCode() {
        return schemCode;
    }

    public void setSchemCode(String schemCode) {
        this.schemCode = schemCode;
    }

    public String getScreenTranCode() {
        return screenTranCode;
    }

    public void setScreenTranCode(String screenTranCode) {
        this.screenTranCode = screenTranCode;
    }

    public String getModeOfTransaction() {
        return modeOfTransaction;
    }

    public void setModeOfTransaction(String modeOfTransaction) {
        this.modeOfTransaction = modeOfTransaction;
    }


    public String getDebitAccNo() {
        return debitAccNo;
    }

    public void setDebitAccNo(String debitAccNo) {
        this.debitAccNo = debitAccNo;
    }

    public String getDebitAccType() {
        return debitAccType;
    }

    public void setDebitAccType(String debitAccType) {
        this.debitAccType = debitAccType;
    }

    public String getCollAmt() {
        return collAmt;
    }

    public void setCollAmt(String collAmt) {
        this.collAmt = collAmt;
    }

    public String getCashAccNo() {
        return cashAccNo;
    }

    public void setCashAccNo(String cashAccNo) {
        this.cashAccNo = cashAccNo;
    }

    public String getCashAccType() {
        return cashAccType;
    }

    public void setCashAccType(String cashAccType) {
        this.cashAccType = cashAccType;
    }

    public String getTranAccNo() {
        return tranAccNo;
    }

    public void setTranAccNo(String tranAccNo) {
        this.tranAccNo = tranAccNo;
    }

    public String getTranAccType() {
        return tranAccType;
    }

    public void setTranAccType(String tranAccType) {
        this.tranAccType = tranAccType;
    }

    public String getClearAccNo() {
        return clearAccNo;
    }

    public void setClearAccNo(String clearAccNo) {
        this.clearAccNo = clearAccNo;
    }

    public String getClearAccType() {
        return clearAccType;
    }

    public void setClearAccType(String clearAccType) {
        this.clearAccType = clearAccType;
    }

    public String getInetAccNo() {
        return inetAccNo;
    }

    public void setInetAccNo(String inetAccNo) {
        this.inetAccNo = inetAccNo;
    }

    public String getInetAccType() {
        return inetAccType;
    }

    public void setInetAccType(String inetAccType) {
        this.inetAccType = inetAccType;
    }
    public String getCreditAccNo() {
        return creditAccNo;
    }

    public void setCreditAccNo(String creditAccNo) {
        this.creditAccNo = creditAccNo;
    }

    public String getCreditAccType() {
        return creditAccType;
    }

    public void setCreditAccType(String creditAccType) {
        this.creditAccType = creditAccType;
    }

    public String getGbmTranId() {
        return gbmTranId;
    }

    public void setGbmTranId(String gbmTranId) {
        this.gbmTranId = gbmTranId;
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

    public List<ConsolidatedFundTransferToLinkCellConsolidationAccDTO> getTranDetailsList() {
        return tranDetailsList;
    }

    public void setTranDetailsList(List<ConsolidatedFundTransferToLinkCellConsolidationAccDTO> tranDetailsList) {
        this.tranDetailsList = tranDetailsList;
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

    public String getTotalCollAmt() {
        return totalCollAmt;
    }

    public void setTotalCollAmt(String totalCollAmt) {
        this.totalCollAmt = totalCollAmt;
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

    public String getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setVerifiedStatus(String verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    public List<Map> getDateList() {
        return dateList;
    }

    public void setDateList(List<Map> dateList) {
        this.dateList = dateList;
    }

    public List<GbmTransactionMasterDto> getTranList() {
        return tranList;
    }
    public void setTranList(List<GbmTransactionMasterDto> tranList) {this.tranList = tranList; }
}
