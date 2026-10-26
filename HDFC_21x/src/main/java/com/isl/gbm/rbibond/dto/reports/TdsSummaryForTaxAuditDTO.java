package com.isl.gbm.rbibond.dto.reports;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TdsSummaryForTaxAuditDTO {

    private String moduleName;
    private String financialYear;
    private String month;
    private String challanSerialNo;
    private String forTheMonthOf;
    private String taxDeposited;
    private String pageNo;
    private String pageSize;
    private String searchValue;
    private String sortOrder;
    private String sortField;
    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getTaxDeposited() {
        return taxDeposited;
    }

    public void setTaxDeposited(String taxDeposited) {
        this.taxDeposited = taxDeposited;
    }

    public String getQuaterlyForm() {
        return quaterlyForm;
    }

    public void setQuaterlyForm(String quaterlyForm) {
        this.quaterlyForm = quaterlyForm;
    }

    private String quaterlyForm;
    public String getChallanSerialNo() {
        return challanSerialNo;
    }

    public void setChallanSerialNo(String challanSerialNo) {
        this.challanSerialNo = challanSerialNo;
    }

    public String getForTheMonthOf() {
        return forTheMonthOf;
    }

    public void setForTheMonthOf(String forTheMonthOf) {
        this.forTheMonthOf = forTheMonthOf;
    }

    public String getQuater() {
        return quater;
    }

    public void setQuater(String quater) {
        this.quater = quater;
    }

    public String getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(String dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public String getNatureOfPayment() {
        return natureOfPayment;
    }

    public void setNatureOfPayment(String natureOfPayment) {
        this.natureOfPayment = natureOfPayment;
    }

    public String getTotalAmountPaid() {
        return totalAmountPaid;
    }

    public void setTotalAmountPaid(String totalAmountPaid) {
        this.totalAmountPaid = totalAmountPaid;
    }

    public String getTotalTaxDeducted() {
        return totalTaxDeducted;
    }

    public void setTotalTaxDeducted(String totalTaxDeducted) {
        this.totalTaxDeducted = totalTaxDeducted;
    }

    public String getTotalChallanAmount() {
        return totalChallanAmount;
    }

    public void setTotalChallanAmount(String totalChallanAmount) {
        this.totalChallanAmount = totalChallanAmount;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getDifference() {
        return difference;
    }

    public void setDifference(String difference) {
        this.difference = difference;
    }

    public String getTeamsRevertForDifference() {
        return teamsRevertForDifference;
    }

    public void setTeamsRevertForDifference(String teamsRevertForDifference) {
        this.teamsRevertForDifference = teamsRevertForDifference;
    }

    private String quater;
    private String dateOfPayment;
    private String natureOfPayment;
    private String totalAmountPaid;

    private String totalTaxDeducted;
    private String totalChallanAmount ; //tax deposited
    private String team;
    private String difference;
    private String teamsRevertForDifference;



    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
