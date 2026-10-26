package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrokerageInvoiceConfirmationVerifyDTO {

    public String schemeCode;
    public String vbmsReferenceNumber;
    public String paymentRef;
    public String paidOn;
    public String invoiceReceiveDate;
    public String invoiceRaisedOn;
    public String vbmsMaker;

    public String remarks;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getBondScheme() {
        return bondScheme;
    }

    public void setBondScheme(String bondScheme) {
        this.bondScheme = bondScheme;
    }

    public String vbmsChecker;

    private  String agencyCode;
    private String invoiceNumber;
    private String bondScheme;


    public String getInvoiceReceiveDate() {
        return invoiceReceiveDate;
    }

    public void setInvoiceReceiveDate(String invoiceReceiveDate) {
        this.invoiceReceiveDate = invoiceReceiveDate;
    }

    public String getInvoiceRaisedOn() {
        return invoiceRaisedOn;
    }

    public void setInvoiceRaisedOn(String invoiceRaisedOn) {
        this.invoiceRaisedOn = invoiceRaisedOn;
    }

    public String getVbmsMaker() {
        return vbmsMaker;
    }

    public void setVbmsMaker(String vbmsMaker) {
        this.vbmsMaker = vbmsMaker;
    }

    public String getVbmsChecker() {
        return vbmsChecker;
    }

    public void setVbmsChecker(String vbmsChecker) {
        this.vbmsChecker = vbmsChecker;
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

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public List<BrokerageInvoiceConfirmationResponseDTO> getBrokerageInvoiceConfirmationResponseDTOList() {
        return brokerageInvoiceConfirmationResponseDTOList;
    }

    public void setBrokerageInvoiceConfirmationResponseDTOList(List<BrokerageInvoiceConfirmationResponseDTO> brokerageInvoiceConfirmationResponseDTOList) {
        this.brokerageInvoiceConfirmationResponseDTOList = brokerageInvoiceConfirmationResponseDTOList;
    }

    public List<BrokerageInvoiceConfirmationResponseDTO> brokerageInvoiceConfirmationResponseDTOList;
}
