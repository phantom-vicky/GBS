package com.isl.gbm.common.util;

import java.util.List;
import java.util.Map;

public class GuardianDetailsDTO {

    private String name;

    private String adhaarNum;

    private String panNum;

    private List<Map<String, Object>> guardianDetails;

    public List<Map<String, Object>> getGuardianDetails() {
        return guardianDetails;
    }

    public void setGuardianDetails(List<Map<String, Object>> guardianDetails) {
        this.guardianDetails = guardianDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdhaarNum() {
        return adhaarNum;
    }

    public void setAdhaarNum(String adhaarNum) {
        this.adhaarNum = adhaarNum;
    }

    public String getPanNum() {
        return panNum;
    }

    public void setPanNum(String panNum) {
        this.panNum = panNum;
    }

    public String getGuardianId() {
        return guardianId;
    }

    public void setGuardianId(String guardianId) {
        this.guardianId = guardianId;
    }

    private String guardianId;

    private String relation;

    private String acctNum;

    private String acctBranch;

    private String sharePercent;

    private String amt;

    private String srlNumber;

    private String paymentThrough;

    private String remarks;

    public String getPaymentThrough() {
        return paymentThrough;
    }

    public void setPaymentThrough(String paymentThrough) {
        this.paymentThrough = paymentThrough;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSrlNumber() {
        return srlNumber;
    }

    public void setSrlNumber(String srlNumber) {
        this.srlNumber = srlNumber;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getAcctNum() {
        return acctNum;
    }

    public void setAcctNum(String acctNum) {
        this.acctNum = acctNum;
    }

    public String getAcctBranch() {
        return acctBranch;
    }

    public void setAcctBranch(String acctBranch) {
        this.acctBranch = acctBranch;
    }

    public String getSharePercent() {
        return sharePercent;
    }

    public void setSharePercent(String sharePercent) {
        this.sharePercent = sharePercent;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }
}
