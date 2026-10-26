package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CrisTranRepResDTO {

    private String bankTranDate;
    private String payTranDate;
    private String effFromDate;
    private String effToDate;
    private String cusName;
    private String cusAlphaCode;
    private String cusNumCode;
    private String modeOfPayment;
    private String tranAmt;
    private String lcAmt;
    private String zoneAlphaCode;
    private String zoneNumCode;
    private String zoneName;
    private String railHead;
    private String responseCode;
    private String crisId;
    private String bankRefNum;
    private String creditNo;
    private String debitNo;
    private String enteredTS;
    private String postedTS;
    private String retryCount;
    private String retryTS;
    private String invoiceNo;
    private String invoiceDate;
    private String source;
    private String destination;
    private String consigner;
    private String consignee;
    private String commodity;
    private String fcProceesFlg;
    private String fcProcessRemarks;
    private String remarks;
    private String errorCode;

    public String getFcProcessRemarks() {
        return fcProcessRemarks;
    }

    public void setFcProcessRemarks(String fcProcessRemarks) {
        this.fcProcessRemarks = fcProcessRemarks;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getBankTranDate() {
        return bankTranDate;
    }

    public void setBankTranDate(String bankTranDate) {
        this.bankTranDate = bankTranDate;
    }

    public String getPayTranDate() {
        return payTranDate;
    }

    public void setPayTranDate(String payTranDate) {
        this.payTranDate = payTranDate;
    }

    public String getEffFromDate() {
        return effFromDate;
    }

    public void setEffFromDate(String effFromDate) {
        this.effFromDate = effFromDate;
    }

    public String getEffToDate() {
        return effToDate;
    }

    public void setEffToDate(String effToDate) {
        this.effToDate = effToDate;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusAlphaCode() {
        return cusAlphaCode;
    }

    public void setCusAlphaCode(String cusAlphaCode) {
        this.cusAlphaCode = cusAlphaCode;
    }

    public String getCusNumCode() {
        return cusNumCode;
    }

    public void setCusNumCode(String cusNumCode) {
        this.cusNumCode = cusNumCode;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public String getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(String tranAmt) {
        this.tranAmt = tranAmt;
    }

    public String getLcAmt() {
        return lcAmt;
    }

    public void setLcAmt(String lcAmt) {
        this.lcAmt = lcAmt;
    }

    public String getZoneAlphaCode() {
        return zoneAlphaCode;
    }

    public void setZoneAlphaCode(String zoneAlphaCode) {
        this.zoneAlphaCode = zoneAlphaCode;
    }

    public String getZoneNumCode() {
        return zoneNumCode;
    }

    public void setZoneNumCode(String zoneNumCode) {
        this.zoneNumCode = zoneNumCode;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getRailHead() {
        return railHead;
    }

    public void setRailHead(String railHead) {
        this.railHead = railHead;
    }

    public String getCrisId() {
        return crisId;
    }

    public void setCrisId(String crisId) {
        this.crisId = crisId;
    }

    public String getBankRefNum() {
        return bankRefNum;
    }

    public void setBankRefNum(String bankRefNum) {
        this.bankRefNum = bankRefNum;
    }

    public String getCreditNo() {
        return creditNo;
    }

    public void setCreditNo(String creditNo) {
        this.creditNo = creditNo;
    }

    public String getDebitNo() {
        return debitNo;
    }

    public void setDebitNo(String debitNo) {
        this.debitNo = debitNo;
    }

    public String getEnteredTS() {
        return enteredTS;
    }

    public void setEnteredTS(String enteredTS) {
        this.enteredTS = enteredTS;
    }

    public String getPostedTS() {
        return postedTS;
    }

    public void setPostedTS(String postedTS) {
        this.postedTS = postedTS;
    }

    public String getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(String retryCount) {
        this.retryCount = retryCount;
    }

    public String getRetryTS() {
        return retryTS;
    }

    public void setRetryTS(String retryTS) {
        this.retryTS = retryTS;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getConsigner() {
        return consigner;
    }

    public void setConsigner(String consigner) {
        this.consigner = consigner;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getFcProceesFlg() {
        return fcProceesFlg;
    }

    public void setFcProceesFlg(String fcProceesFlg) {
        this.fcProceesFlg = fcProceesFlg;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
