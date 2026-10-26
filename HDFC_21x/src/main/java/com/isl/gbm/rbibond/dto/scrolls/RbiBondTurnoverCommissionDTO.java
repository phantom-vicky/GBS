package com.isl.gbm.rbibond.dto.scrolls;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.bouncycastle.math.ec.custom.sec.SecT113R1Curve;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RbiBondTurnoverCommissionDTO {

    private String branchId;
    private String branchName;
    private String fromDate;
    private String toDate;
    private String sNo;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    private String amount;

    public String getNoOfTransections() {
        return noOfTransections;
    }

    public void setNoOfTransections(String noOfTransections) {
        this.noOfTransections = noOfTransections;
    }

    private String branchOffice;
    private String principalAmountrepaid;
    private String interestAmountpPaid ;
    private String totalPayment;
    private String turnoverCommission;
    private String totalClaim;
    private String schemeCode;
    private String module;
    private String noOfTransections;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    private String financialYear;
    private String quarter;
    private String flag;


    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }


    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
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

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo;
    }

    public String getBranchOffice() {
        return branchOffice;
    }

    public void setBranchOffice(String branchOffice) {
        this.branchOffice = branchOffice;
    }

    public String getPrincipalAmountrepaid() {
        return principalAmountrepaid;
    }

    public void setPrincipalAmountrepaid(String principalAmountrepaid) {
        this.principalAmountrepaid = principalAmountrepaid;
    }

    public String getInterestAmountpPaid() {
        return interestAmountpPaid;
    }

    public void setInterestAmountpPaid(String interestAmountpPaid) {
        this.interestAmountpPaid = interestAmountpPaid;
    }

    public String getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(String totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getTurnoverCommission() {
        return turnoverCommission;
    }

    public void setTurnoverCommission(String turnoverCommission) {
        this.turnoverCommission = turnoverCommission;
    }

    public String getTotalClaim() {
        return totalClaim;
    }

    public void setTotalClaim(String totalClaim) {
        this.totalClaim = totalClaim;
    }


}
