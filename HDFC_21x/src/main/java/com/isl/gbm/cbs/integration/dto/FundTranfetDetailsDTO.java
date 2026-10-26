package com.isl.gbm.cbs.integration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FundTranfetDetailsDTO {

    private String cBSReferenceNo;

    private String transReferenceNo;

    private String status;

    private String statusCode;

    private String reason;

    private Date TranDate;

    public Date getTranDate() {
        return TranDate;
    }

    public void setTranDate(Date tranDate) {
        TranDate = tranDate;
    }

    public String getcBSReferenceNo() {
        return cBSReferenceNo;
    }

    public void setcBSReferenceNo(String cBSReferenceNo) {
        this.cBSReferenceNo = cBSReferenceNo;
    }

    public String getTransReferenceNo() {
        return transReferenceNo;
    }

    public void setTransReferenceNo(String transReferenceNo) {
        this.transReferenceNo = transReferenceNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
