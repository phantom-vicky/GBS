package com.isl.gbm.rbibond.dto.scrolls;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SatatementOfChargesBlaDTO {
    private String schemeCode;
    private String fromdate;
    private String todate;
    private String office;
    private String newbla;
    private String existingbla;
    private String totalclaim;
    private String amountofservicecharges;
    private String branch;
    private String branchname;
    private String newblaamt;
    private String existingblaamt;
    private String total;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public String getNewblaamt() {
        return newblaamt;
    }

    public void setNewblaamt(String newblaamt) {
        this.newblaamt = newblaamt;
    }

    public String getExistingblaamt() {
        return existingblaamt;
    }

    public void setExistingblaamt(String existingblaamt) {
        this.existingblaamt = existingblaamt;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getNewbla() {
        return newbla;
    }

    public void setNewbla(String newbla) {
        this.newbla = newbla;
    }

    public String getExistingbla() {
        return existingbla;
    }

    public void setExistingbla(String existingbla) {
        this.existingbla = existingbla;
    }

    public String getTotalclaim() {
        return totalclaim;
    }

    public void setTotalclaim(String totalclaim) {
        this.totalclaim = totalclaim;
    }

    public String getAmountofservicecharges() {
        return amountofservicecharges;
    }

    public void setAmountofservicecharges(String amountofservicecharges) {
        this.amountofservicecharges = amountofservicecharges;
    }
}
