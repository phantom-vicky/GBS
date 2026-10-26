package com.isl.gbm.rbibond.dto.reports;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FutureTdsDto {
    private String schemeCode;
    private String schemeName;
    private String flag;
    private String schemeStatus;
    private String fromDate;
    private String toDate;
    private String menu;
    private String schemeId;
    private String branchRefNo;
    private String blaNo;
    private String applNameWithHuf;
    private String applName1;
    private String applName2;
    private String applName3;
    private String casTransferDate;

    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    public String getBranchRefNo() {
        return branchRefNo;
    }

    public void setBranchRefNo(String branchRefNo) {
        this.branchRefNo = branchRefNo;
    }

    public String getBlaNo() {
        return blaNo;
    }

    public void setBlaNo(String blaNo) {
        this.blaNo = blaNo;
    }

    public String getApplNameWithHuf() {
        return applNameWithHuf;
    }

    public void setApplNameWithHuf(String applNameWithHuf) {
        this.applNameWithHuf = applNameWithHuf;
    }

    public String getApplName1() {
        return applName1;
    }

    public void setApplName1(String applName1) {
        this.applName1 = applName1;
    }

    public String getApplName2() {
        return applName2;
    }

    public void setApplName2(String applName2) {
        this.applName2 = applName2;
    }

    public String getApplName3() {
        return applName3;
    }

    public void setApplName3(String applName3) {
        this.applName3 = applName3;
    }

    public String getCasTransferDate() {
        return casTransferDate;
    }

    public void setCasTransferDate(String casTransferDate) {
        this.casTransferDate = casTransferDate;
    }

    public String getApplStatus() {
        return applStatus;
    }

    public void setApplStatus(String applStatus) {
        this.applStatus = applStatus;
    }

    public String getRcptDate() {
        return rcptDate;
    }

    public void setRcptDate(String rcptDate) {
        this.rcptDate = rcptDate;
    }

    public String getInvestAmt() {
        return investAmt;
    }

    public void setInvestAmt(String investAmt) {
        this.investAmt = investAmt;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getCfd() {
        return cfd;
    }

    public void setCfd(String cfd) {
        this.cfd = cfd;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getIntRate() {
        return intRate;
    }

    public void setIntRate(String intRate) {
        this.intRate = intRate;
    }

    public String getLastIntPaidAmt() {
        return lastIntPaidAmt;
    }

    public void setLastIntPaidAmt(String lastIntPaidAmt) {
        this.lastIntPaidAmt = lastIntPaidAmt;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getFutureInterestStatus() {
        return futureInterestStatus;
    }

    public void setFutureInterestStatus(String futureInterestStatus) {
        this.futureInterestStatus = futureInterestStatus;
    }

    public String getNew1() {
        return new1;
    }

    public void setNew1(String new1) {
        this.new1 = new1;
    }

    public String getNew2() {
        return new2;
    }

    public void setNew2(String new2) {
        this.new2 = new2;
    }

    public String getNew3() {
        return new3;
    }

    public void setNew3(String new3) {
        this.new3 = new3;
    }

    public String getNew4() {
        return new4;
    }

    public void setNew4(String new4) {
        this.new4 = new4;
    }

    public String getNew5() {
        return new5;
    }

    public void setNew5(String new5) {
        this.new5 = new5;
    }

    private String applStatus;
    private String rcptDate;
    private String investAmt;
    private String scheme;
    private String cfd;
    private String maturityDate;
    private String intRate;
    private String lastIntPaidAmt;
    private String panNo;
    private String futureInterestStatus;
    private String new1;
    private String new2;
    private String new3;
    private String new4;
    private String new5;



    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getSchemeStatus() {
        return schemeStatus;
    }

    public void setSchemeStatus(String schemeStatus) {
        this.schemeStatus = schemeStatus;
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

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
}
