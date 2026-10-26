package com.isl.gbm.rbibond.dto.reports;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TdsDeductionReportDTO {
    private String operationType;
    private String schemeCode;
    private String tdsDeductFrom;
    private String tdsDeductTo;
    private String id;
    private String userName;
    private String branchId;
    private String blaNo;
    private String branchName;
    private String bondHolderName;
    private String modeOfHolding;
    private String pan;
    private String subscriptionId;
    private String subscriptionAmt;
    private String dateOfTds;
    private String interestAmt;
    private String TdsAmt;
    private String netAmt;
    private Double interestTotal;
    private Double TdsTotal;
    private Double netAmtTotal;
    private List<TdsDeductionReportDTO> tdsDeductionReportDTOList;

    public List<TdsDeductionReportDTO> getTdsDeductionReportDTOList() {
        return tdsDeductionReportDTOList;
    }

    public void setTdsDeductionReportDTOList(List<TdsDeductionReportDTO> tdsDeductionReportDTOList) {
        this.tdsDeductionReportDTOList = tdsDeductionReportDTOList;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getTdsDeductFrom() {
        return tdsDeductFrom;
    }

    public void setTdsDeductFrom(String tdsDeductFrom) {
        this.tdsDeductFrom = tdsDeductFrom;
    }

    public String getTdsDeductTo() {
        return tdsDeductTo;
    }

    public void setTdsDeductTo(String tdsDeductTo) {
        this.tdsDeductTo = tdsDeductTo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBlaNo() {
        return blaNo;
    }

    public void setBlaNo(String blaNo) {
        this.blaNo = blaNo;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBondHolderName() {
        return bondHolderName;
    }

    public void setBondHolderName(String bondHolderName) {
        this.bondHolderName = bondHolderName;
    }

    public String getModeOfHolding() {
        return modeOfHolding;
    }

    public void setModeOfHolding(String modeOfHolding) {
        this.modeOfHolding = modeOfHolding;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriptionAmt() {
        return subscriptionAmt;
    }

    public void setSubscriptionAmt(String subscriptionAmt) {
        this.subscriptionAmt = subscriptionAmt;
    }

    public String getDateOfTds() {
        return dateOfTds;
    }

    public void setDateOfTds(String dateOfTds) {
        this.dateOfTds = dateOfTds;
    }

    public String getInterestAmt() {
        return interestAmt;
    }

    public void setInterestAmt(String interestAmt) {
        this.interestAmt = interestAmt;
    }

    public String getTdsAmt() {
        return TdsAmt;
    }

    public void setTdsAmt(String tdsAmt) {
        TdsAmt = tdsAmt;
    }

    public String getNetAmt() {
        return netAmt;
    }

    public void setNetAmt(String netAmt) {
        this.netAmt = netAmt;
    }

    public Double getInterestTotal() {
        return interestTotal;
    }

    public void setInterestTotal(Double interestTotal) {
        this.interestTotal = interestTotal;
    }

    public Double getTdsTotal() {
        return TdsTotal;
    }

    public void setTdsTotal(Double tdsTotal) {
        TdsTotal = tdsTotal;
    }

    public Double getNetAmtTotal() {
        return netAmtTotal;
    }

    public void setNetAmtTotal(Double netAmtTotal) {
        this.netAmtTotal = netAmtTotal;
    }
}
