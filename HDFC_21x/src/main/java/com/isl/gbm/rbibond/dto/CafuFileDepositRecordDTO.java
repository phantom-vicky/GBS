package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CafuFileDepositRecordDTO {

    private String recordIdentifier;
    private String clientCode;
    private String pickUpPoint;
    private String depositRemarks;
    private String depositSlipNo;
    private String depositDate;
    private String totalNoOfInstruments;
    private String totalAmount;
    private String clientArrg;

    public String getRecordIdentifier() {
        return recordIdentifier;
    }

    public void setRecordIdentifier(String recordIdentifier) {
        this.recordIdentifier = recordIdentifier;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getPickUpPoint() {
        return pickUpPoint;
    }

    public void setPickUpPoint(String pickUpPoint) {
        this.pickUpPoint = pickUpPoint;
    }

    public String getDepositRemarks() {
        return depositRemarks;
    }

    public void setDepositRemarks(String depositRemarks) {
        this.depositRemarks = depositRemarks;
    }

    public String getDepositSlipNo() {
        return depositSlipNo;
    }

    public void setDepositSlipNo(String depositSlipNo) {
        this.depositSlipNo = depositSlipNo;
    }

    public String getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(String depositDate) {
        this.depositDate = depositDate;
    }

    public String getTotalNoOfInstruments() {
        return totalNoOfInstruments;
    }

    public void setTotalNoOfInstruments(String totalNoOfInstruments) {
        this.totalNoOfInstruments = totalNoOfInstruments;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getClientArrg() {
        return clientArrg;
    }

    public void setClientArrg(String clientArrg) {
        this.clientArrg = clientArrg;
    }
}
