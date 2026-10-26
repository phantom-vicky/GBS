package com.isl.gbm.rbibond.dto.reports;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class bondSubscriptionwithoutnominationDTO {

    private String blaNumber;
    private String name;
    private String panNo;
    private String subcriptionId;
    private String dateofsubscription;
    private String subcriptionamount;
    private String subcriptionstatus;
    private String total;
    private String NameoftheBondScheme;
    private String moduleId;
    private String bondholdername;
    private String modeofholding;
    private String schemeCode;
    private String singlebondledgeraccount;
    private String holderType;


    public String getBlaNumber() {
        return blaNumber;
    }

    public void setBlaNumber(String blaNumber) {
        this.blaNumber = blaNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getSubcriptionId() {
        return subcriptionId;
    }

    public void setSubcriptionId(String subcriptionId) {
        this.subcriptionId = subcriptionId;
    }

    public String getDateofsubscription() {
        return dateofsubscription;
    }

    public void setDateofsubscription(String dateofsubscription) {
        this.dateofsubscription = dateofsubscription;
    }

    public String getSubcriptionamount() {
        return subcriptionamount;
    }

    public void setSubcriptionamount(String subcriptionamount) {
        this.subcriptionamount = subcriptionamount;
    }

    public String getSubcriptionstatus() {
        return subcriptionstatus;
    }

    public void setSubcriptionstatus(String subcriptionstatus) {
        this.subcriptionstatus = subcriptionstatus;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getNameoftheBondScheme() {
        return NameoftheBondScheme;
    }

    public void setNameoftheBondScheme(String nameoftheBondScheme) {
        NameoftheBondScheme = nameoftheBondScheme;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getBondholdername() {
        return bondholdername;
    }

    public void setBondholdername(String bondholdername) {
        this.bondholdername = bondholdername;
    }

    public String getModeofholding() {
        return modeofholding;
    }

    public void setModeofholding(String modeofholding) {
        this.modeofholding = modeofholding;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getSinglebondledgeraccount() {
        return singlebondledgeraccount;
    }

    public void setSinglebondledgeraccount(String singlebondledgeraccount) {
        this.singlebondledgeraccount = singlebondledgeraccount;
    }

    public String getHolderType() {
        return holderType;
    }

    public void setHolderType(String holderType) {
        this.holderType = holderType;
    }

    @Override
    public String toString() {
        return "bondSubscriptionwithoutnominationDTO{" +
                "blaNumber='" + blaNumber + '\'' +
                ", name='" + name + '\'' +
                ", panNo='" + panNo + '\'' +
                ", subcriptionId='" + subcriptionId + '\'' +
                ", dateofsubscription='" + dateofsubscription + '\'' +
                ", subcriptionamount='" + subcriptionamount + '\'' +
                ", subcriptionstatus='" + subcriptionstatus + '\'' +
                ", total='" + total + '\'' +
                ", NameoftheBondScheme='" + NameoftheBondScheme + '\'' +
                ", moduleId='" + moduleId + '\'' +
                ", bondholdername='" + bondholdername + '\'' +
                ", modeofholding='" + modeofholding + '\'' +
                ", schemeCode='" + schemeCode + '\'' +
                ", singlebondledgeraccount='" + singlebondledgeraccount + '\'' +
                ", holderType='" + holderType + '\'' +
                '}';
    }
}
