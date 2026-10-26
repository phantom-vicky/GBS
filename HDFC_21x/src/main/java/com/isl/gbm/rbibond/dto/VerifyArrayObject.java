package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VerifyArrayObject {

    private String blaNo;
    private String subId;
    private String panNo;
    private String paymentDate;

    @Override
    public String toString() {
        return "VerifyArrayObject{" +
                "blaNo='" + blaNo + '\'' +
                ", subId='" + subId + '\'' +
                ", panNo='" + panNo + '\'' +
                ", paymentDate='" + paymentDate + '\'' +
                '}';
    }

    public String getBlaNo() {
        return blaNo;
    }

    public void setBlaNo(String blaNo) {
        this.blaNo = blaNo;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
}
