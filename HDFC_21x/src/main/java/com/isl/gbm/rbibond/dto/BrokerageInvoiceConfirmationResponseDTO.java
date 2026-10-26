package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrokerageInvoiceConfirmationResponseDTO {

    private String invoiceNumber;
    private String invoiceDate;
    private String brokerName;
    private String brokerCode;
    private String brokerPan;
    private String brokerEmailId;
    private String grossBrokerage;
    private String gst;
    private String netBrokerage;
    private String vbsRefNum;
    private String invRecieveDate;
    private String invRaisedOnDate;
    private String vbsMakerId;
    private String vbsCheckerId;

    public String vbmsReferenceNumber;
    public String paymentRef;
    public String paidOn;
    public String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVbmsReferenceNumber() {
        return vbmsReferenceNumber;
    }

    public void setVbmsReferenceNumber(String vbmsReferenceNumber) {
        this.vbmsReferenceNumber = vbmsReferenceNumber;
    }

    public String getPaymentRef() {
        return paymentRef;
    }

    public void setPaymentRef(String paymentRef) {
        this.paymentRef = paymentRef;
    }

    public String getPaidOn() {
        return paidOn;
    }

    public void setPaidOn(String paidOn) {
        this.paidOn = paidOn;
    }

    public String getVbsRefNum() {
        return vbsRefNum;
    }

    public void setVbsRefNum(String vbsRefNum) {
        this.vbsRefNum = vbsRefNum;
    }

    public String getInvRecieveDate() {
        return invRecieveDate;
    }

    public void setInvRecieveDate(String invRecieveDate) {
        this.invRecieveDate = invRecieveDate;
    }

    public String getInvRaisedOnDate() {
        return invRaisedOnDate;
    }

    public void setInvRaisedOnDate(String invRaisedOnDate) {
        this.invRaisedOnDate = invRaisedOnDate;
    }

    public String getVbsMakerId() {
        return vbsMakerId;
    }

    public void setVbsMakerId(String vbsMakerId) {
        this.vbsMakerId = vbsMakerId;
    }

    public String getVbsCheckerId() {
        return vbsCheckerId;
    }

    public void setVbsCheckerId(String vbsCheckerId) {
        this.vbsCheckerId = vbsCheckerId;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getBrokerCode() {
        return brokerCode;
    }

    public void setBrokerCode(String brokerCode) {
        this.brokerCode = brokerCode;
    }

    public String getBrokerPan() {
        return brokerPan;
    }

    public void setBrokerPan(String brokerPan) {
        this.brokerPan = brokerPan;
    }

    public String getBrokerEmailId() {
        return brokerEmailId;
    }

    public void setBrokerEmailId(String brokerEmailId) {
        this.brokerEmailId = brokerEmailId;
    }

    public String getGrossBrokerage() {
        return grossBrokerage;
    }

    public void setGrossBrokerage(String grossBrokerage) {
        this.grossBrokerage = grossBrokerage;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getNetBrokerage() {
        return netBrokerage;
    }

    public void setNetBrokerage(String netBrokerage) {
        this.netBrokerage = netBrokerage;
    }

}
