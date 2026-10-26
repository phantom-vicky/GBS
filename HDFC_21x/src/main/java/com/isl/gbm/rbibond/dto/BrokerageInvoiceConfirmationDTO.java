package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrokerageInvoiceConfirmationDTO {

    private String schemeCode;
    private  String agencyCode;
    private String invoiceNumber;
    private String bondScheme;
    private String subFromDate;
    private String subToDate;

    public String vbmsReferenceNumber;
    public String paymentRef;
    public String paidOn;
    public String remarks;
    public String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    private List<BrokerageInvoiceConfirmationResponseDTO> brokerageInvoiceConfirmationResponseDTOList;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<BrokerageInvoiceConfirmationResponseDTO> getBrokerageInvoiceConfirmationResponseDTOList() {
        return brokerageInvoiceConfirmationResponseDTOList;
    }

    public void setBrokerageInvoiceConfirmationResponseDTOList(
            List<BrokerageInvoiceConfirmationResponseDTO> brokerageInvoiceConfirmationResponseDTOList) {
        this.brokerageInvoiceConfirmationResponseDTOList = brokerageInvoiceConfirmationResponseDTOList;
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

    public List<HashMap<String, String>> getAgencyOuts() {
        return agencyOuts;
    }

    public void setAgencyOuts(List<HashMap<String, String>> agencyOuts) {
        this.agencyOuts = agencyOuts;
    }

    private List<HashMap<String, String>> agencyOuts;

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
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

    public String getSubFromDate() {
        return subFromDate;
    }

    public void setSubFromDate(String subFromDate) {
        this.subFromDate = subFromDate;
    }

    public String getSubToDate() {
        return subToDate;
    }

    public void setSubToDate(String subToDate) {
        this.subToDate = subToDate;
    }
}
