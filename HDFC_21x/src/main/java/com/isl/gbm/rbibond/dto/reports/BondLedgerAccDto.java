package com.isl.gbm.rbibond.dto.reports;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BondLedgerAccDto {

    private String schemeName;
    private String schemeCode;
    private String bondType;
    private String blaNo;
    private String fromDate;
    private String toDate;
    private String amtOfSubs;
    private String dateOfTransaction;
    private String casAdviceNoDate;
    private String transferSaleBurchase;
    private String transferNominalValue;
    private String transferNominalAmt;
    private String repaymntNominalAmt;
    private String repaymntMaturityValue;
    private String remarks;
    private String nominalAmt;
    private String maturityVal;
    private String dueDateOfRepayment;
    private String repayment;
    private String balance;
    private String holderName;
    private String modeOfHolding;
    private String flag;
    private String cacAck;
    private String flg;
    private String blaflg;
    private String addressOfTheHolder;
    private String subsId;
    private String nominationId;
    private String schemeStatus;
    private String initials;


    private String panNo;

    //pagination control
    private String downloadType;
    private List<Map<String, Object>> data;
    private int maxPages;
    private String isDownload;

    private String pageNo;
    private String pageSize;
    private String searchValue;
    private String sortOrder;
    private String sortField;


    public String getPageSize() {
        return pageSize;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getSchemeStatus() {
        return schemeStatus;
    }

    public void setSchemeStatus(String schemeStatus) {
        this.schemeStatus = schemeStatus;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
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

    public int getMaxPages() {
        return maxPages;
    }

    public void setMaxPages(int maxPages) {
        this.maxPages = maxPages;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(String isDownload) {
        this.isDownload = isDownload;
    }

    public String getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(String downloadType) {
        this.downloadType = downloadType;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }


    public void setTransferSaleBurchase(String transferSaleBurchase) {
        this.transferSaleBurchase = transferSaleBurchase;
    }


    public void setTransferNominalValue(String transferNominalValue) {
        this.transferNominalValue = transferNominalValue;
    }

    public String getTransferNominalAmt() {
        return transferNominalAmt;
    }

    public void setTransferNominalAmt(String transferNominalAmt) {
        this.transferNominalAmt = transferNominalAmt;
    }

    public String getRepaymntNominalAmt() {
        return repaymntNominalAmt;
    }

    public void setRepaymntNominalAmt(String repaymntNominalAmt) {
        this.repaymntNominalAmt = repaymntNominalAmt;
    }

    public String getTransferSaleBurchase() {
        return transferSaleBurchase;
    }

    public String getTransferNominalValue() {
        return transferNominalValue;
    }

    public String getRepaymntMaturityValue() {
        return repaymntMaturityValue;
    }

    public void setRepaymntMaturityValue(String repaymntMaturityValue) {
        this.repaymntMaturityValue = repaymntMaturityValue;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getNominationId() {
        return nominationId;
    }

    public void setNominationId(String nominationId) {
        this.nominationId = nominationId;
    }

    public String getSubsId() {
        return subsId;
    }

    public void setSubsId(String subsId) {
        this.subsId = subsId;
    }

    public String getAddressOfTheHolder() {
        return addressOfTheHolder;
    }

    public void setAddressOfTheHolder(String addressOfTheHolder) {
        this.addressOfTheHolder = addressOfTheHolder;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getBondType() {
        return bondType;
    }

    public void setBondType(String bondType) {
        this.bondType = bondType;
    }

    public String getBlaNo() {
        return blaNo;
    }

    public void setBlaNo(String blaNo) {
        this.blaNo = blaNo;
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

    public String getAmtOfSubs() {
        return amtOfSubs;
    }

    public void setAmtOfSubs(String amtOfSubs) {
        this.amtOfSubs = amtOfSubs;
    }

    public String getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(String dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public String getCasAdviceNoDate() {
        return casAdviceNoDate;
    }

    public void setCasAdviceNoDate(String casAdviceNoDate) {
        this.casAdviceNoDate = casAdviceNoDate;
    }

    public String getNominalAmt() {
        return nominalAmt;
    }

    public void setNominalAmt(String nominalAmt) {
        this.nominalAmt = nominalAmt;
    }

    public String getMaturityVal() {
        return maturityVal;
    }

    public void setMaturityVal(String maturityVal) {
        this.maturityVal = maturityVal;
    }

    public String getDueDateOfRepayment() {
        return dueDateOfRepayment;
    }

    public void setDueDateOfRepayment(String dueDateOfRepayment) {
        this.dueDateOfRepayment = dueDateOfRepayment;
    }

    public String getRepayment() {
        return repayment;
    }

    public void setRepayment(String repayment) {
        this.repayment = repayment;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getModeOfHolding() {
        return modeOfHolding;
    }

    public void setModeOfHolding(String modeOfHolding) {
        this.modeOfHolding = modeOfHolding;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCacAck() {
        return cacAck;
    }

    public void setCacAck(String cacAck) {
        this.cacAck = cacAck;
    }

    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public String getBlaflg() {
        return blaflg;
    }

    public void setBlaflg(String blaflg) {
        this.blaflg = blaflg;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }
}
