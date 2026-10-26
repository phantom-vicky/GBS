package com.isl.gbm.rbibond.util;

import org.springframework.context.ApplicationEvent;

import java.util.List;

public class RbiBulkPaymentEvent  extends ApplicationEvent {

    private String eventType;
    private String branchId;
    private String blaNum;
    private String bondSubId;
    private String centralizedFlag;
    private String processId;
    private String interestForHalfYear;
    private String year;
    private String moduleId;
    private List<String>  validationBatchList;

    public RbiBulkPaymentEvent(Object source, String eventType, String branchId, String blaNum, String bondSubId, String centralizedFlag, String processId, String interestForHalfYear, String year ,List<String> validationBatchList, String moduleId) {
        super(source);
        this.eventType = eventType;
        this.branchId = branchId;
        this.blaNum = blaNum;
        this.processId = processId;
        this.bondSubId = bondSubId;
        this.centralizedFlag = centralizedFlag;
        this.validationBatchList = validationBatchList;
        this.interestForHalfYear = interestForHalfYear;
        this.year = year;
        this.moduleId = moduleId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBlaNum() {
        return blaNum;
    }

    public void setBlaNum(String blaNum) {
        this.blaNum = blaNum;
    }

    public String getBondSubId() {
        return bondSubId;
    }

    public void setBondSubId(String bondSubId) {
        this.bondSubId = bondSubId;
    }

    public String getCentralizedFlag() {
        return centralizedFlag;
    }

    public void setCentralizedFlag(String centralizedFlag) {
        this.centralizedFlag = centralizedFlag;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public List<String> getValidationBatchList() {
        return validationBatchList;
    }

    public void setValidationBatchList(List<String> validationBatchList) {
        this.validationBatchList = validationBatchList;
    }

    public String getInterestForHalfYear() {
        return interestForHalfYear;
    }

    public void setInterestForHalfYear(String interestForHalfYear) {
        this.interestForHalfYear = interestForHalfYear;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


}
