package com.isl.gbm.rbibond.dto.scrolls;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InterestScrollReportDTO {
    private String schemeCode;
    private String schemeName;
    private String schemeStatus;
    private String fromDate;
    private String toDate;
    private String flag;
    private String blaNumber;
    private String principalAmount;
    private String dueDate;
    private String grossAmount;
    private String interestPaidForLastBrokenPeriod;
    private String interestPaidForFirstBrokenPeriod;
    private String creditedToBankAccount;
    private String amountPaid;
    private String remarks;
    private String initials;

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

    public String getBlaNumber() {
        return blaNumber;
    }

    public void setBlaNumber(String blaNumber) {
        this.blaNumber = blaNumber;
    }

    public String getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(String principalAmount) {
        this.principalAmount = principalAmount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(String grossAmount) {
        this.grossAmount = grossAmount;
    }

    public String getInterestPaidForLastBrokenPeriod() {
        return interestPaidForLastBrokenPeriod;
    }

    public void setInterestPaidForLastBrokenPeriod(String interestPaidForLastBrokenPeriod) {
        this.interestPaidForLastBrokenPeriod = interestPaidForLastBrokenPeriod;
    }

    public String getInterestPaidForFirstBrokenPeriod() {
        return interestPaidForFirstBrokenPeriod;
    }

    public void setInterestPaidForFirstBrokenPeriod(String interestPaidForFirstBrokenPeriod) {
        this.interestPaidForFirstBrokenPeriod = interestPaidForFirstBrokenPeriod;
    }

    public String getCreditedToBankAccount() {
        return creditedToBankAccount;
    }

    public void setCreditedToBankAccount(String creditedToBankAccount) {
        this.creditedToBankAccount = creditedToBankAccount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }
}
