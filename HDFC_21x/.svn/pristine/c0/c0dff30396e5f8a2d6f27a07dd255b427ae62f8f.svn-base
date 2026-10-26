package com.isl.gbm.rbibond.batch.RbiBulkPayment;

import java.util.List;

public class RbiBondInterestPaymentBatchDto {
    private String processId;
    private String centralizedFlag;
    private String branchId;
    private String bondSubsId;
    private String blaNo;
    private String interestForHalfYear;
    private String year;
    private String moduleId;

    private List<String> validationBatchList;
    private Integer validationBatchCurrentPointer = -1;
    private List<String> transactionBatchList;
    private Integer transactionBatchCurrentPointer = -1;

    public RbiBondInterestPaymentBatchDto(String processId,String interestForHalfYear,String year, String centralizedFlag, String branchId, String bondSubsId, String blaNo, String moduleId, List<String> validationBatchList) {
        this.processId = processId;
        this.interestForHalfYear = interestForHalfYear;
        this.year = year;
        this.centralizedFlag = centralizedFlag;
        this.branchId = branchId;
        this.bondSubsId = bondSubsId;
        this.blaNo = blaNo;
        this.moduleId = moduleId;
        this.validationBatchList = validationBatchList;
    }

    public RbiBondInterestPaymentBatchDto(String processId,String interestForHalfYear,String year, String centralizedFlag, String branchId, String bondSubsId, List<String> transactionBatchList, String blaNo, String moduleId) {
        this.processId = processId;
        this.interestForHalfYear = interestForHalfYear;
        this.year = year;
        this.centralizedFlag = centralizedFlag;
        this.branchId = branchId;
        this.bondSubsId = bondSubsId;
        this.blaNo = blaNo;
        this.transactionBatchList = transactionBatchList;
        this.moduleId = moduleId;
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

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getCentralizedFlag() {
        return centralizedFlag;
    }

    public void setCentralizedFlag(String centralizedFlag) {
        this.centralizedFlag = centralizedFlag;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBondSubsId() {
        return bondSubsId;
    }

    public void setBondSubsId(String bondSubsId) {
        this.bondSubsId = bondSubsId;
    }

    public String getBlaNo() {
        return blaNo;
    }

    public void setBlaNo(String blaNo) {
        this.blaNo = blaNo;
    }

    public Integer getValidationBatchCurrentPointer() {
        return validationBatchCurrentPointer;
    }

    public void setValidationBatchCurrentPointer(Integer validationBatchCurrentPointer) {
        this.validationBatchCurrentPointer = validationBatchCurrentPointer;
    }

    public Integer getTransactionBatchCurrentPointer() {
        return transactionBatchCurrentPointer;
    }

    public void setTransactionBatchCurrentPointer(Integer transactionBatchCurrentPointer) {
        this.transactionBatchCurrentPointer = transactionBatchCurrentPointer;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public List<String> getTransactionBatchList() {
        return transactionBatchList;
    }

    public void setTransactionBatchList(List<String> transactionBatchList) {
        this.transactionBatchList = transactionBatchList;
        this.transactionBatchCurrentPointer = -1;
    }

    public List<String> getValidationBatchList() {
        return validationBatchList;
    }

    public void setValidationBatchList(List<String> validationBatchList) {
        this.validationBatchList = validationBatchList;
        this.validationBatchCurrentPointer = -1;
    }

    public synchronized String fetchNextValidationBatch() {
        this.validationBatchCurrentPointer++;
        if (this.validationBatchCurrentPointer >= validationBatchList.size())
            return null;
        return validationBatchList.get(this.validationBatchCurrentPointer);
    }

    public synchronized String fetchNextTransactionBatch() {
        this.transactionBatchCurrentPointer++;
        if (this.transactionBatchCurrentPointer >= transactionBatchList.size())
            return null;
        return transactionBatchList.get(this.transactionBatchCurrentPointer);
    }
}
