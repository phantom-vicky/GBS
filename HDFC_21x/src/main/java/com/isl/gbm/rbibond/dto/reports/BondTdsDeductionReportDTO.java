package com.isl.gbm.rbibond.dto.reports;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BondTdsDeductionReportDTO {
    private String operationType;
    private String schemeCode;
    private String tdsDeductFrom;
    private String tdsDeductTo;
    private String id;
    private String userName;
    private String branchId;
    private String blaNo;
    private String branchName;
    private String bondHolderName;
    private String modeOfHolding;
    private String pan;
    private String subscriptionId;
    private String subscriptionAmt;
    private String dateOfTds;
    private String interestAmt;
    private String TdsAmt;
    private String netAmt;
    private Double interestTotal;
    private Double TdsTotal;
    private Double netAmtTotal;

    private String currentTds;
    private String arrearTds;
    private List<Map<String, Object>> data;
    private List<BondTdsDeductionReportDTO> bondTdsDeductionReportDTOList;

    public List<BondTdsDeductionReportDTO> getBondTdsDeductionReportDTOList() {
        return bondTdsDeductionReportDTOList;
    }

    public void setBondTdsDeductionReportDTOList(List<BondTdsDeductionReportDTO> bondTdsDeductionReportDTOList) {
        this.bondTdsDeductionReportDTOList = bondTdsDeductionReportDTOList;
    }
    private String searchValue;
    private String sortOrder;

    private String sortField;

    private int pageSize;

    private int pageNo;

    private int maxPages;

    private String isDownload;

    private String downloadType;

    public String getCurrentTds() {
        return currentTds;
    }

    public void setCurrentTds(String currentTds) {
        this.currentTds = currentTds;
    }

    public String getArrearTds() {
        return arrearTds;
    }

    public void setArrearTds(String arrearTds) {
        this.arrearTds = arrearTds;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public String getDateOfTds() {
        return dateOfTds;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
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

    private List<HashMap<String, String>> branchIdTorchOut;

    private List<HashMap<String, String>> blaNumberTorchOut;

    public List<HashMap<String, String>> getBranchIdTorchOut() {
        return branchIdTorchOut;
    }

    public void setBranchIdTorchOut(List<HashMap<String, String>> branchIdTorchOut) {
        this.branchIdTorchOut = branchIdTorchOut;
    }

    public List<HashMap<String, String>> getBlaNumberTorchOut() {
        return blaNumberTorchOut;
    }

    public void setBlaNumberTorchOut(List<HashMap<String, String>> blaNumberTorchOut) {
        this.blaNumberTorchOut = blaNumberTorchOut;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    private List<BondTdsDeductionReportDTO> torchOne;

    public List<BondTdsDeductionReportDTO> getTorchOne() {
        return torchOne;
    }

    public void setTorchOne(List<BondTdsDeductionReportDTO> torchOne) {
        this.torchOne = torchOne;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getTdsDeductFrom() {
        return tdsDeductFrom;
    }

    public void setTdsDeductFrom(String tdsDeductFrom) {
        this.tdsDeductFrom = tdsDeductFrom;
    }

    public String getTdsDeductTo() {
        return tdsDeductTo;
    }

    public void setTdsDeductTo(String tdsDeductTo) {
        this.tdsDeductTo = tdsDeductTo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBondHolderName() {
        return bondHolderName;
    }

    public void setBondHolderName(String bondHolderName) {
        this.bondHolderName = bondHolderName;
    }

    public String getModeOfHolding() {
        return modeOfHolding;
    }

    public void setModeOfHolding(String modeOfHolding) {
        this.modeOfHolding = modeOfHolding;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriptionAmt() {
        return subscriptionAmt;
    }

    public void setSubscriptionAmt(String subscriptionAmt) {
        this.subscriptionAmt = subscriptionAmt;
    }


    public void setDateOfTds(String dateOfTds) {
        this.dateOfTds = dateOfTds;
    }

    public String getInterestAmt() {
        return interestAmt;
    }

    public void setInterestAmt(String interestAmt) {
        this.interestAmt = interestAmt;
    }

    public String getTdsAmt() {
        return TdsAmt;
    }

    public void setTdsAmt(String tdsAmt) {
        TdsAmt = tdsAmt;
    }

    public String getNetAmt() {
        return netAmt;
    }

    public void setNetAmt(String netAmt) {
        this.netAmt = netAmt;
    }

    public Double getInterestTotal() {
        return interestTotal;
    }

    public void setInterestTotal(Double interestTotal) {
        this.interestTotal = interestTotal;
    }

    public Double getTdsTotal() {
        return TdsTotal;
    }

    public void setTdsTotal(Double tdsTotal) {
        TdsTotal = tdsTotal;
    }

    public Double getNetAmtTotal() {
        return netAmtTotal;
    }

    public void setNetAmtTotal(Double netAmtTotal) {
        this.netAmtTotal = netAmtTotal;
    }
}
