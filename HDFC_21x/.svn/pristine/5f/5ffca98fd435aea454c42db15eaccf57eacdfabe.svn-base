package com.isl.gbm.rbibond.dto.reports;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrokerageInvoiceDownloadDTO {


    @NotBlank(message ="Schme Code Should Not Be Blank")
    private String schemeCode;
    private String subDesc;
    private String schemeStatus;
    @NotBlank(message ="From Date Should Not Be Blank")
    private String fromDate;
    @NotBlank(message = "Schme Code Should Not Be Blank")
    private String toDate;
    private String brokerCode;
    private String invoiceNum;
    private String brokerId;
    private List<BrokerageInvoiceDownloadDTO> brokerResList;

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public List<BrokerageInvoiceDownloadDTO> getBrokerResList() {
        return brokerResList;
    }

    public void setBrokerResList(List<BrokerageInvoiceDownloadDTO> brokerResList) {
        this.brokerResList = brokerResList;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getSubDesc() {
        return subDesc;
    }

    public void setSubDesc(String subDesc) {
        this.subDesc = subDesc;
    }

    public String getSchemeStatus() {
        return schemeStatus;
    }

    public void setSchemeStatus(String schemeStatus) {
        this.schemeStatus = schemeStatus;
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

    public String getBrokerCode() {
        return brokerCode;
    }

    public void setBrokerCode(String brokerCode) {
        this.brokerCode = brokerCode;
    }

    public String getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId;
    }
}
