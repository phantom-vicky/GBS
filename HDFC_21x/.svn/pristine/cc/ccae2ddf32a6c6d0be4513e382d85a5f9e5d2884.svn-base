package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.isl.gbm.cbs.integration.dto.TranDetailsDTO;
import com.isl.gbm.common.dto.AccountValidationDto;
import com.isl.gbm.common.dto.TranStatusDto;
import com.isl.gbm.transaction.channel.GbmTransactionMasterDto;

import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RbiBondCentralizedInterestTrailRunDtoRequest {


    private String moduleId;
    private String schemeCode;
    private String branchId;
    private String dateyear;
    private String valueFlag;
    private String operationType;
    private String blaNo;
    private String interestForHalfYear;
    private String year;
    private String processId;
    private String bondSubsId;
    private String typeIn;
    private List<AccountValidationDto> cbsAcctDet;
    private String centralizedFlag;
    private String validationBatchId;
    private List<GbmTransactionMasterDto> acctDetailsList;
    private List<TranDetailsDTO> accDetailsIn;
    private String downloadType;
    private List<Map<String, Object>> data;
    private List<String> uncheckedList;
    private List<String> checkedList;
    private List<String> validationBatchList;
    private Integer validationBatchCurrentPointer = -1;
    private List<String> transactionBatchList;
    private Integer transactionBatchCurrentPointer = -1;
    private String searchValue;
    private String schemeName;

    public List<String> getUncheckedList() {
        return uncheckedList;
    }

    public void setUncheckedList(List<String> uncheckedList) {
        this.uncheckedList = uncheckedList;
    }

    public List<String> getCheckedList() {
        return checkedList;
    }

    public void setCheckedList(List<String> checkedList) {
        this.checkedList = checkedList;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    private String sortOrder;
    private String sortField;
    private int pageSize;
    private int pageNo;
    private int maxPages;
    private String isDownload;

    public String getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(String isDownload) {
        this.isDownload = isDownload;
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

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getMaxPages() {
        return maxPages;
    }

    public void setMaxPages(int maxPages) {
        this.maxPages = maxPages;
    }

    private String gbmTranIdOut;
    private String cbsTranIdOut;
    private String cbsTranStatusOut;
    private String cbsTranStatusDesc;


    private List<TranStatusDto> paymentStatusOut;
    private String completedFlag;

    public String getDateyear() {
        return dateyear;
    }

    public void setDateyear(String dateyear) {
        this.dateyear = dateyear;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getCompletedFlag() {
        return completedFlag;
    }

    public void setCompletedFlag(String completedFlag) {
        this.completedFlag = completedFlag;
    }

    public List<TranStatusDto> getPaymentStatusOut() {
        return paymentStatusOut;
    }

    public void setPaymentStatusOut(List<TranStatusDto> paymentStatusOut) {
        this.paymentStatusOut = paymentStatusOut;
    }

    public String getGbmTranIdOut() {
        return gbmTranIdOut;
    }

    public void setGbmTranIdOut(String gbmTranIdOut) {
        this.gbmTranIdOut = gbmTranIdOut;
    }

    public String getCbsTranIdOut() {
        return cbsTranIdOut;
    }

    public void setCbsTranIdOut(String cbsTranIdOut) {
        this.cbsTranIdOut = cbsTranIdOut;
    }

    public String getCbsTranStatusOut() {
        return cbsTranStatusOut;
    }

    public void setCbsTranStatusOut(String cbsTranStatusOut) {
        this.cbsTranStatusOut = cbsTranStatusOut;
    }

    public String getCbsTranStatusDesc() {
        return cbsTranStatusDesc;
    }

    public void setCbsTranStatusDesc(String cbsTranStatusDesc) {
        this.cbsTranStatusDesc = cbsTranStatusDesc;
    }

    public List<String> getValidationBatchList() {
        return validationBatchList;
    }

    public void setValidationBatchList(List<String> validationBatchList) {
        this.validationBatchList = validationBatchList;
    }

    public Integer getValidationBatchCurrentPointer() {
        return validationBatchCurrentPointer;
    }

    public void setValidationBatchCurrentPointer(Integer validationBatchCurrentPointer) {
        this.validationBatchCurrentPointer = validationBatchCurrentPointer;
    }

    public List<String> getTransactionBatchList() {
        return transactionBatchList;
    }

    public void setTransactionBatchList(List<String> transactionBatchList) {
        this.transactionBatchList = transactionBatchList;
    }

    public Integer getTransactionBatchCurrentPointer() {
        return transactionBatchCurrentPointer;
    }

    public void setTransactionBatchCurrentPointer(Integer transactionBatchCurrentPointer) {
        this.transactionBatchCurrentPointer = transactionBatchCurrentPointer;
    }

    public List<TranDetailsDTO> getAccDetailsIn() {
        return accDetailsIn;
    }

    public void setAccDetailsIn(List<TranDetailsDTO> accDetailsIn) {
        this.accDetailsIn = accDetailsIn;
    }

    public String getValidationBatchId() {
        return validationBatchId;
    }

    public void setValidationBatchId(String validationBatchId) {
        this.validationBatchId = validationBatchId;
    }

    public List<GbmTransactionMasterDto> getAcctDetailsList() {
        return acctDetailsList;
    }

    public void setAcctDetailsList(List<GbmTransactionMasterDto> acctDetailsList) {
        this.acctDetailsList = acctDetailsList;
    }

    public String getCentralizedFlag() {
        return centralizedFlag;
    }

    public void setCentralizedFlag(String centralizedFlag) {
        this.centralizedFlag = centralizedFlag;
    }

    public String getTypeIn() {
        return typeIn;
    }

    public void setTypeIn(String typeIn) {
        this.typeIn = typeIn;
    }

    public List<AccountValidationDto> getCbsAcctDet() {
        return cbsAcctDet;
    }

    public void setCbsAcctDet(List<AccountValidationDto> cbsAcctDet) {
        this.cbsAcctDet = cbsAcctDet;
    }

//    public List<AccountValidationDto> cbsAccValidation;
//
//    public List<AccountValidationDto> getCbsAccValidation() {
//        return cbsAccValidation;
//    }
//
//    public void setCbsAccValidation(List<AccountValidationDto> cbsAccValidation) {
//        this.cbsAccValidation = cbsAccValidation;
//    }

    public String getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(String downloadType) {
        this.downloadType = downloadType;
    }

    public String getBondSubsId() {
        return bondSubsId;
    }

    public void setBondSubsId(String bondSubsId) {
        this.bondSubsId = bondSubsId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getValueFlag() {
        return valueFlag;
    }

    public void setValueFlag(String valueFlag) {
        this.valueFlag = valueFlag;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getBlaNo() {
        return blaNo;
    }

    public void setBlaNo(String blaNo) {
        this.blaNo = blaNo;
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

    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
