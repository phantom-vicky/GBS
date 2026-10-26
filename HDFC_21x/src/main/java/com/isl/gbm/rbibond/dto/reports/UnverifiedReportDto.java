package com.isl.gbm.rbibond.dto.reports;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UnverifiedReportDto {
    private String branchId;
    private String blaNo;
    private String holderName;
    private String subsId;
    private String screenName;
    private String dateOfCreation;
    private String createdOrModifiedBy;
    private String schemeName;
    List<HashMap<String, String>> branchList;
    List<HashMap<String, String>> userList;
    List<UnverifiedReportDto> resultFinalList;
    private String bFlg;
    private String uFlg;
    private String operationFlag;
    private String createdBy;
    private String modifiedBy;
    private String branchDescription;


    public String getBranchDescription() {
        return branchDescription;
    }

    public void setBranchDescription(String branchDescription) {
        this.branchDescription = branchDescription;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getOperationFlag() {
        return operationFlag;
    }

    public void setOperationFlag(String operationFlag) {
        this.operationFlag = operationFlag;
    }

    public String getCreatedOrModifiedBy() {
        return createdOrModifiedBy;
    }

    public void setCreatedOrModifiedBy(String createdOrModifiedBy) {
        this.createdOrModifiedBy = createdOrModifiedBy;
    }

    public String getbFlg() {
        return bFlg;
    }

    public void setbFlg(String bFlg) {
        this.bFlg = bFlg;
    }

    public String getuFlg() {
        return uFlg;
    }

    public void setuFlg(String uFlg) {
        this.uFlg = uFlg;
    }

    public List<UnverifiedReportDto> getResultFinalList() {
        return resultFinalList;
    }

    public void setResultFinalList(List<UnverifiedReportDto> resultFinalList) {
        this.resultFinalList = resultFinalList;
    }

    public List<HashMap<String, String>> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<HashMap<String, String>> branchList) {
        this.branchList = branchList;
    }

    public List<HashMap<String, String>> getUserList() {
        return userList;
    }

    public void setUserList(List<HashMap<String, String>> userList) {
        this.userList = userList;
    }


    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
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

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getSubsId() {
        return subsId;
    }

    public void setSubsId(String subsId) {
        this.subsId = subsId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }


}
