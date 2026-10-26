package com.isl.gbm.rbibond.dto.scrolls;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppendixFourMarchDTO {
    private String schemeCode;
    private String schemeName;
    private String schemeStatus;
    private String fromDate;
    private String toDate;
    private String flag;

    private String PartANCblano;
    private String PartANCamount;
    private String PartANCcasAdvNoCasDate;
    private String PartANCDueDate;

    private String PartACblano;
    private String PartACamount;
    private String PartACcasAdvNoCasDate;
    private String PartACDueDate;

    public String getPartACblano() {
        return PartACblano;
    }

    public void setPartACblano(String partACblano) {
        PartACblano = partACblano;
    }

    public String getPartACamount() {
        return PartACamount;
    }

    public void setPartACamount(String partACamount) {
        PartACamount = partACamount;
    }

    public String getPartACcasAdvNoCasDate() {
        return PartACcasAdvNoCasDate;
    }

    public void setPartACcasAdvNoCasDate(String partACcasAdvNoCasDate) {
        PartACcasAdvNoCasDate = partACcasAdvNoCasDate;
    }

    public String getPartACDueDate() {
        return PartACDueDate;
    }

    public void setPartACDueDate(String partACDueDate) {
        PartACDueDate = partACDueDate;
    }

    public String getPartANCblano() {
        return PartANCblano;
    }

    public void setPartANCblano(String partANCblano) {
        PartANCblano = partANCblano;
    }

    public String getPartANCamount() {
        return PartANCamount;
    }

    public void setPartANCamount(String partANCamount) {
        PartANCamount = partANCamount;
    }

    public String getPartANCcasAdvNoCasDate() {
        return PartANCcasAdvNoCasDate;
    }

    public void setPartANCcasAdvNoCasDate(String partANCcasAdvNoCasDate) {
        PartANCcasAdvNoCasDate = partANCcasAdvNoCasDate;
    }

    public String getPartANCDueDate() {
        return PartANCDueDate;
    }

    public void setPartANCDueDate(String partANCDueDate) {
        PartANCDueDate = partANCDueDate;
    }

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
