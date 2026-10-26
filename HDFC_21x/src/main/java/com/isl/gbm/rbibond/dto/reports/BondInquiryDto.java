package com.isl.gbm.rbibond.dto.reports;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BondInquiryDto {


    private String schemeCode;
    private String schemeName;
    private String schemeStatus;
    private String blaNo;
    private String holderName;
    private String subsId;
    private String dateOfSubs;
    private String maturityDate;
    private String modeOfHolding;
    private String subsAmount;
    private String accClosedDate;
    private String totSubsAmt;
    private String blaNoFrom;
    private String blaNoTo;
    private String address;
    private String isNri;
    private String isMinor;
    private String holderType;
    private String guadId;
    private String guadName;
    private String fromDate;
    private String toDate;
    private String subsStatus;
    private String subsAmtFrom;
    private String subsAmtTo;
    private String transferType;
    private String transferOutToBank;
    private String transferOutToBankBranch;
    private String transferOutDate;
    private String transferInBank;
    private String transferInBankBranch;
    private String transferInDate;
    //    Pagination
    private String sortOrder;
    private String sortField;
    private int pageSize;
    private int pageNo;
    private int maxPages;
    private String searchValue;

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

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
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


    public String getModeOfInvestment() {
        return modeOfInvestment;
    }

    public void setModeOfInvestment(String modeOfInvestment) {
        this.modeOfInvestment = modeOfInvestment;
    }

    public String getHolderPanNo() {
        return holderPanNo;
    }

    public void setHolderPanNo(String holderPanNo) {
        this.holderPanNo = holderPanNo;
    }

    private String modeOfInvestment;
    private String holderPanNo;


    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
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

    public String getDateOfSubs() {
        return dateOfSubs;
    }

    public void setDateOfSubs(String dateOfSubs) {
        this.dateOfSubs = dateOfSubs;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getModeOfHolding() {
        return modeOfHolding;
    }

    public void setModeOfHolding(String modeOfHolding) {
        this.modeOfHolding = modeOfHolding;
    }

    public String getSubsAmount() {
        return subsAmount;
    }

    public void setSubsAmount(String subsAmount) {
        this.subsAmount = subsAmount;
    }

    public String getAccClosedDate() {
        return accClosedDate;
    }

    public void setAccClosedDate(String accClosedDate) {
        this.accClosedDate = accClosedDate;
    }

    public String getTotSubsAmt() {
        return totSubsAmt;
    }

    public void setTotSubsAmt(String totSubsAmt) {
        this.totSubsAmt = totSubsAmt;
    }

    public String getBlaNoFrom() {
        return blaNoFrom;
    }

    public void setBlaNoFrom(String blaNoFrom) {
        this.blaNoFrom = blaNoFrom;
    }

    public String getBlaNoTo() {
        return blaNoTo;
    }

    public void setBlaNoTo(String blaNoTo) {
        this.blaNoTo = blaNoTo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsNri() {
        return isNri;
    }

    public void setIsNri(String isNri) {
        this.isNri = isNri;
    }

    public String getIsMinor() {
        return isMinor;
    }

    public void setIsMinor(String isMinor) {
        this.isMinor = isMinor;
    }

    public String getHolderType() {
        return holderType;
    }

    public void setHolderType(String holderType) {
        this.holderType = holderType;
    }

    public String getGuadId() {
        return guadId;
    }

    public void setGuadId(String guadId) {
        this.guadId = guadId;
    }

    public String getGuadName() {
        return guadName;
    }

    public void setGuadName(String guadName) {
        this.guadName = guadName;
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

    public String getSubsStatus() {
        return subsStatus;
    }

    public void setSubsStatus(String subsStatus) {
        this.subsStatus = subsStatus;
    }

    public String getSubsAmtFrom() {
        return subsAmtFrom;
    }

    public void setSubsAmtFrom(String subsAmtFrom) {
        this.subsAmtFrom = subsAmtFrom;
    }

    public String getSubsAmtTo() {
        return subsAmtTo;
    }

    public void setSubsAmtTo(String subsAmtTo) {
        this.subsAmtTo = subsAmtTo;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getTransferOutToBank() {
        return transferOutToBank;
    }

    public void setTransferOutToBank(String transferOutToBank) {
        this.transferOutToBank = transferOutToBank;
    }

    public String getTransferOutToBankBranch() {
        return transferOutToBankBranch;
    }

    public void setTransferOutToBankBranch(String transferOutToBankBranch) {
        this.transferOutToBankBranch = transferOutToBankBranch;
    }

    public String getTransferOutDate() {
        return transferOutDate;
    }

    public void setTransferOutDate(String transferOutDate) {
        this.transferOutDate = transferOutDate;
    }

    public String getTransferInBank() {
        return transferInBank;
    }

    public void setTransferInBank(String transferInBank) {
        this.transferInBank = transferInBank;
    }

    public String getTransferInBankBranch() {
        return transferInBankBranch;
    }

    public void setTransferInBankBranch(String transferInBankBranch) {
        this.transferInBankBranch = transferInBankBranch;
    }

    public String getTransferInDate() {
        return transferInDate;
    }

    public void setTransferInDate(String transferInDate) {
        this.transferInDate = transferInDate;
    }

}
