package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BranchTranTypeDTO {

    private String levelId;

    private List<HashMap<String, String>> levelsOut;

    private List<HashMap<String, String>> branchesOut;

    private List<HashMap<String, String>> refIdOut;

    private List<String> branchesToAdd;

    private String tranTypeOld;

    private String tranTypeNew;

    private String refId;

    private String branchId;

    private String id;

    private String createdBy;

    private String createdDate;

    private String lastModifiedBy;

    private String lastModifiedDate;

    private String verifiedBy;

    private String verifiedDate;

    private String verifiedStatus;

    /*Input flg*/
    private String refBranchFlg;

    private String tranType;

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public List<HashMap<String, String>> getLevelsOut() {
        return levelsOut;
    }

    public void setLevelsOut(List<HashMap<String, String>> levelsOut) {
        this.levelsOut = levelsOut;
    }

    public List<HashMap<String, String>> getBranchesOut() {
        return branchesOut;
    }

    public void setBranchesOut(List<HashMap<String, String>> branchesOut) {
        this.branchesOut = branchesOut;
    }

    public List<HashMap<String, String>> getRefIdOut() {
        return refIdOut;
    }

    public void setRefIdOut(List<HashMap<String, String>> refIdOut) {
        this.refIdOut = refIdOut;
    }

    public List<String> getBranchesToAdd() {
        return branchesToAdd;
    }

    public void setBranchesToAdd(List<String> branchesToAdd) {
        this.branchesToAdd = branchesToAdd;
    }

    public String getTranTypeOld() {
        return tranTypeOld;
    }

    public void setTranTypeOld(String tranTypeOld) {
        this.tranTypeOld = tranTypeOld;
    }

    public String getTranTypeNew() {
        return tranTypeNew;
    }

    public void setTranTypeNew(String tranTypeNew) {
        this.tranTypeNew = tranTypeNew;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public String getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(String verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    public String getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setVerifiedStatus(String verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    public String getRefBranchFlg() {
        return refBranchFlg;
    }

    public void setRefBranchFlg(String refBranchFlg) {
        this.refBranchFlg = refBranchFlg;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }
}
