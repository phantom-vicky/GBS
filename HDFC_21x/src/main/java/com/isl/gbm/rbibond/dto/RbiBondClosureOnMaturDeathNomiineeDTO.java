package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RbiBondClosureOnMaturDeathNomiineeDTO {

   public String  nomineeId;
    public String  nomineeName;
    public String  nomineeDob;
    public String  sharePercentage;
    public String  shareAmountSettled;
    public String  modeOfInterestPay;
    public String  relationShip;
    public String   accBranch;
    private String  panNo;
    public String remarks;
    public String accNum;
    public String dateOfBirthOfMinor;
    public String nomineeDOB;

    public String getNomineeDOB() {
        return nomineeDOB;
    }

    public void setNomineeDOB(String nomineeDOB) {
        this.nomineeDOB = nomineeDOB;
    }

    public String getRelationShip() {
        return relationShip;
    }

    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }

    public String getDateOfBirthOfMinor() {
        return dateOfBirthOfMinor;
    }

    public void setDateOfBirthOfMinor(String dateOfBirthOfMinor) {
        this.dateOfBirthOfMinor = dateOfBirthOfMinor;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getNomineeDob() {
        return nomineeDob;
    }

    public void setNomineeDob(String nomineeDob) {
        this.nomineeDob = nomineeDob;
    }

    public String getAccBranch() {
        return accBranch;
    }

    public void setAccBranch(String accBranch) {
        this.accBranch = accBranch;
    }

    public String getAccNum() {
        return accNum;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }



    public String getNomineeId() {
        return nomineeId;
    }

    public void setNomineeId(String nomineeId) {
        this.nomineeId = nomineeId;
    }

    public String getNomineeName() {
        return nomineeName;
    }

    public void setNomineeName(String nomineeName) {
        this.nomineeName = nomineeName;
    }

    public String getSharePercentage() {
        return sharePercentage;
    }

    public void setSharePercentage(String sharePercentage) {
        this.sharePercentage = sharePercentage;
    }

    public String getShareAmountSettled() {
        return shareAmountSettled;
    }

    public void setShareAmountSettled(String shareAmountSettled) {
        this.shareAmountSettled = shareAmountSettled;
    }


    public String getModeOfInterestPay() {
        return modeOfInterestPay;
    }

    public void setModeOfInterestPay(String modeOfInterestPay) {
        this.modeOfInterestPay = modeOfInterestPay;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
