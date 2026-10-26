package com.isl.gbm.rbibond.dto.scrolls;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppendixFourMar65DTO {

    private String schemeCode;
    private String schemeName;
    private String schemeStatus;
    private String fromDate;
    private String toDate;
    private String flag;

    public String getBlaNo() {
        return blaNo;
    }

    public void setBlaNo(String blaNo) {
        this.blaNo = blaNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCas() {
        return cas;
    }

    public void setCas(String cas) {
        this.cas = cas;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getTransferBranchName() {
        return transferBranchName;
    }

    public void setTransferBranchName(String transferBranchName) {
        this.transferBranchName = transferBranchName;
    }

    public String getRepaymentOn() {
        return repaymentOn;
    }

    public void setRepaymentOn(String repaymentOn) {
        this.repaymentOn = repaymentOn;
    }

    private  String blaNo;
    private String amount;
    private String cas;
    private String dueDate;
    private String transferBranchName;
    private  String repaymentOn;


   private  String cumBlaNo;
   private String cumAmount;
   private String cumCas;
    private String  cumDueDate;
    private String cumTransferBranch;
    private String cumRepayMentOn;
    private String cumSum;
    private String cumCount;

    public String getCumBlaNo() {
        return cumBlaNo;
    }

    public void setCumBlaNo(String cumBlaNo) {
        this.cumBlaNo = cumBlaNo;
    }

    public String getCumAmount() {
        return cumAmount;
    }

    public void setCumAmount(String cumAmount) {
        this.cumAmount = cumAmount;
    }

    public String getCumCas() {
        return cumCas;
    }

    public void setCumCas(String cumCas) {
        this.cumCas = cumCas;
    }

    public String getCumDueDate() {
        return cumDueDate;
    }

    public void setCumDueDate(String cumDueDate) {
        this.cumDueDate = cumDueDate;
    }

    public String getCumTransferBranch() {
        return cumTransferBranch;
    }

    public void setCumTransferBranch(String cumTransferBranch) {
        this.cumTransferBranch = cumTransferBranch;
    }

    public String getCumRepayMentOn() {
        return cumRepayMentOn;
    }

    public void setCumRepayMentOn(String cumRepayMentOn) {
        this.cumRepayMentOn = cumRepayMentOn;
    }

    public String getCumSum() {
        return cumSum;
    }

    public void setCumSum(String cumSum) {
        this.cumSum = cumSum;
    }

    public String getCumCount() {
        return cumCount;
    }

    public void setCumCount(String cumCount) {
        this.cumCount = cumCount;
    }



    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    private String count;
    private  String  sum;



    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
