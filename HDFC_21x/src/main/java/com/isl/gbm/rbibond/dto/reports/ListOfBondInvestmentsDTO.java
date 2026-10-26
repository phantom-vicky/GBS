package com.isl.gbm.rbibond.dto.reports;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListOfBondInvestmentsDTO {
    public String getBlaNumber() {
        return blaNumber;
    }

    public void setBlaNumber(String blaNumber) {
        this.blaNumber = blaNumber;
    }

    private String blaNumber;
    private String schemeName;
    private String subsDate;
    private String subsAmount;

    //Pagination
    private int pageSize;
    private int pageNo;
    private int maxPages;
    private String sortOrder;
    private String sortField;
    private String searchValue;

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

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
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


    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getSubsDate() {
        return subsDate;
    }

    public void setSubsDate(String subsDate) {
        this.subsDate = subsDate;
    }

    public String getSubsAmount() {
        return subsAmount;
    }

    public void setSubsAmount(String subsAmount) {
        this.subsAmount = subsAmount;
    }
}
