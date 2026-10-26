package com.isl.gbm.rbibond.dto.reports;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BondInterestTrialRunEligibleAccountsDto {

    // Basic Details From BLA cum Subscription
    private String modeOfPayment;
    private String branchRefNo;
    private String blaNo;
    private String refNo;
    private String applName1;
    private String applName2;
    private String applName3;
    private String interestHalfYear;
    private String year;
    private String name;
    private String schemeName;
    private String nonCumulativeInt8;
    private String cumulatinveInt8;
    private String interestPayout7;
    private String nonCumulativeInt7;
    private String cumulativeInt7;
    private String schemeStatus;
    private String schemeCode;

    private String interestPayout8;
    private String intPaidAmt;
    private String cumulatinveInt7;
    private String flag;


    private String addr1;
    private String addr2;
    private String recName;
    private String recParentName;
    private String pinCode;
    private String phoneNo;
    private String applStatus;
    private String investmentAmt;
    private String cfd;
    private String matDate;
    private String accountNo;
    private String subscriptionCount;
    private String bankSortCode;
    private String bankName;

    // Other Schemes Interest Related Info
    private String panNo;
    private String interest8PercentPayout;
    private String nonCumulativeInt;
    private String cumulativeInt;
    private String interest7_75PercentPayout;
    private String nonCumulativeInt775;
    private String cumulativeInt775;
    private String total;
    private String panStatus;
    private String inOperativePan;
    private String nonFilers;

    // TDS Exemption Details
    private String age;
    private String taxableLimit;
    private String waiverDocument;
    private String waiverDocumentRefNo;
    private String rrbTdsRate;
    private String thresholdExceedAmount;
    private String thresholdExceedTdsAmount;
    private String exemptionRatePercent;
    private String exemptionAmount;
    private String exemptionTdsAmount;
    private String surchargePercent;
    private String eduCessPercent;
    private String baseRateAmt;
    private String surchargeAmt;
    private String eduCessAmt;
    private String tdsAmount;
    private String tdsRecoveryAmount;
    private String tdsRefundAmount;

    // FRSB Interest Payout Details
    private String intRate;
    private String lastIntPaid;
    private String pendingInterest;
    private String currentHalfYearlyInterest;
    private String futureInterest;
    private String grossInterestAmount;
    private String intPaidNetInterestAmount;

    private String pageNo;
    private String pageSize;
    private String searchValue;
    private String sortOrder;
    private String sortField;

    public String getIntPaidAmt() {
        return intPaidAmt;
    }

    public void setIntPaidAmt(String intPaidAmt) {
        this.intPaidAmt = intPaidAmt;
    }


    public String getInterestPayout8() {
        return interestPayout8;
    }

    public void setInterestPayout8(String interestPayout8) {
        this.interestPayout8 = interestPayout8;
    }



    public String getCumulativeInt7() {
        return cumulativeInt7;
    }

    public void setCumulativeInt7(String cumulativeInt7) {
        this.cumulativeInt7 = cumulativeInt7;
    }

    public String getNonCumulativeInt8() {
        return nonCumulativeInt8;
    }

    public void setNonCumulativeInt8(String nonCumulativeInt8) {
        this.nonCumulativeInt8 = nonCumulativeInt8;
    }

    public String getCumulatinveInt8() {
        return cumulatinveInt8;
    }

    public void setCumulatinveInt8(String cumulatinveInt8) {
        this.cumulatinveInt8 = cumulatinveInt8;
    }

    public String getInterestPayout7() {
        return interestPayout7;
    }

    public void setInterestPayout7(String interestPayout7) {
        this.interestPayout7 = interestPayout7;
    }

    public String getNonCumulativeInt7() {
        return nonCumulativeInt7;
    }

    public void setNonCumulativeInt7(String nonCumulativeInt7) {
        this.nonCumulativeInt7 = nonCumulativeInt7;
    }

    public String getCumulatinveInt7() {
        return cumulatinveInt7;
    }

    public void setCumulatinveInt7(String cumulatinveInt7) {
        this.cumulatinveInt7 = cumulatinveInt7;
    }



    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


    public String getInterestHalfYear() {
        return interestHalfYear;
    }

    public void setInterestHalfYear(String interestHalfYear) {
        this.interestHalfYear = interestHalfYear;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }




    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
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

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
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

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
    }

    public String getRecParentName() {
        return recParentName;
    }

    public void setRecParentName(String recParentName) {
        this.recParentName = recParentName;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getApplStatus() {
        return applStatus;
    }

    public void setApplStatus(String applStatus) {
        this.applStatus = applStatus;
    }

    public String getInvestmentAmt() {
        return investmentAmt;
    }

    public void setInvestmentAmt(String investmentAmt) {
        this.investmentAmt = investmentAmt;
    }

    public String getCfd() {
        return cfd;
    }

    public void setCfd(String cfd) {
        this.cfd = cfd;
    }

    public String getMatDate() {
        return matDate;
    }

    public void setMatDate(String matDate) {
        this.matDate = matDate;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getSubscriptionCount() {
        return subscriptionCount;
    }

    public void setSubscriptionCount(String subscriptionCount) {
        this.subscriptionCount = subscriptionCount;
    }

    public String getBankSortCode() {
        return bankSortCode;
    }

    public void setBankSortCode(String bankSortCode) {
        this.bankSortCode = bankSortCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getInterest8PercentPayout() {
        return interest8PercentPayout;
    }

    public void setInterest8PercentPayout(String interest8PercentPayout) {
        this.interest8PercentPayout = interest8PercentPayout;
    }

    public String getNonCumulativeInt() {
        return nonCumulativeInt;
    }

    public void setNonCumulativeInt(String nonCumulativeInt) {
        this.nonCumulativeInt = nonCumulativeInt;
    }

    public String getCumulativeInt() {
        return cumulativeInt;
    }

    public void setCumulativeInt(String cumulativeInt) {
        this.cumulativeInt = cumulativeInt;
    }

    public String getInterest7_75PercentPayout() {
        return interest7_75PercentPayout;
    }

    public void setInterest7_75PercentPayout(String interest7_75PercentPayout) {
        this.interest7_75PercentPayout = interest7_75PercentPayout;
    }

    public String getNonCumulativeInt775() {
        return nonCumulativeInt775;
    }

    public void setNonCumulativeInt775(String nonCumulativeInt775) {
        this.nonCumulativeInt775 = nonCumulativeInt775;
    }

    public String getCumulativeInt775() {
        return cumulativeInt775;
    }

    public void setCumulativeInt775(String cumulativeInt775) {
        this.cumulativeInt775 = cumulativeInt775;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPanStatus() {
        return panStatus;
    }

    public void setPanStatus(String panStatus) {
        this.panStatus = panStatus;
    }

    public String getInOperativePan() {
        return inOperativePan;
    }

    public void setInOperativePan(String inOperativePan) {
        this.inOperativePan = inOperativePan;
    }

    public String getNonFilers() {
        return nonFilers;
    }

    public void setNonFilers(String nonFilers) {
        this.nonFilers = nonFilers;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTaxableLimit() {
        return taxableLimit;
    }

    public void setTaxableLimit(String taxableLimit) {
        this.taxableLimit = taxableLimit;
    }

    public String getWaiverDocument() {
        return waiverDocument;
    }

    public void setWaiverDocument(String waiverDocument) {
        this.waiverDocument = waiverDocument;
    }

    public String getWaiverDocumentRefNo() {
        return waiverDocumentRefNo;
    }

    public void setWaiverDocumentRefNo(String waiverDocumentRefNo) {
        this.waiverDocumentRefNo = waiverDocumentRefNo;
    }

    public String getRrbTdsRate() {
        return rrbTdsRate;
    }

    public void setRrbTdsRate(String rrbTdsRate) {
        this.rrbTdsRate = rrbTdsRate;
    }

    public String getThresholdExceedAmount() {
        return thresholdExceedAmount;
    }

    public void setThresholdExceedAmount(String thresholdExceedAmount) {
        this.thresholdExceedAmount = thresholdExceedAmount;
    }

    public String getThresholdExceedTdsAmount() {
        return thresholdExceedTdsAmount;
    }

    public void setThresholdExceedTdsAmount(String thresholdExceedTdsAmount) {
        this.thresholdExceedTdsAmount = thresholdExceedTdsAmount;
    }

    public String getExemptionRatePercent() {
        return exemptionRatePercent;
    }

    public void setExemptionRatePercent(String exemptionRatePercent) {
        this.exemptionRatePercent = exemptionRatePercent;
    }

    public String getExemptionAmount() {
        return exemptionAmount;
    }

    public void setExemptionAmount(String exemptionAmount) {
        this.exemptionAmount = exemptionAmount;
    }

    public String getExemptionTdsAmount() {
        return exemptionTdsAmount;
    }

    public void setExemptionTdsAmount(String exemptionTdsAmount) {
        this.exemptionTdsAmount = exemptionTdsAmount;
    }

    public String getSurchargePercent() {
        return surchargePercent;
    }

    public void setSurchargePercent(String surchargePercent) {
        this.surchargePercent = surchargePercent;
    }

    public String getEduCessPercent() {
        return eduCessPercent;
    }

    public void setEduCessPercent(String eduCessPercent) {
        this.eduCessPercent = eduCessPercent;
    }

    public String getBaseRateAmt() {
        return baseRateAmt;
    }

    public void setBaseRateAmt(String baseRateAmt) {
        this.baseRateAmt = baseRateAmt;
    }

    public String getSurchargeAmt() {
        return surchargeAmt;
    }

    public void setSurchargeAmt(String surchargeAmt) {
        this.surchargeAmt = surchargeAmt;
    }

    public String getEduCessAmt() {
        return eduCessAmt;
    }

    public void setEduCessAmt(String eduCessAmt) {
        this.eduCessAmt = eduCessAmt;
    }

    public String getTdsAmount() {
        return tdsAmount;
    }

    public void setTdsAmount(String tdsAmount) {
        this.tdsAmount = tdsAmount;
    }

    public String getTdsRecoveryAmount() {
        return tdsRecoveryAmount;
    }

    public void setTdsRecoveryAmount(String tdsRecoveryAmount) {
        this.tdsRecoveryAmount = tdsRecoveryAmount;
    }

    public String getTdsRefundAmount() {
        return tdsRefundAmount;
    }

    public void setTdsRefundAmount(String tdsRefundAmount) {
        this.tdsRefundAmount = tdsRefundAmount;
    }

    public String getIntRate() {
        return intRate;
    }

    public void setIntRate(String intRate) {
        this.intRate = intRate;
    }

    public String getLastIntPaid() {
        return lastIntPaid;
    }

    public void setLastIntPaid(String lastIntPaid) {
        this.lastIntPaid = lastIntPaid;
    }

    public String getPendingInterest() {
        return pendingInterest;
    }

    public void setPendingInterest(String pendingInterest) {
        this.pendingInterest = pendingInterest;
    }

    public String getCurrentHalfYearlyInterest() {
        return currentHalfYearlyInterest;
    }

    public void setCurrentHalfYearlyInterest(String currentHalfYearlyInterest) {
        this.currentHalfYearlyInterest = currentHalfYearlyInterest;
    }

    public String getFutureInterest() {
        return futureInterest;
    }

    public void setFutureInterest(String futureInterest) {
        this.futureInterest = futureInterest;
    }

    public String getGrossInterestAmount() {
        return grossInterestAmount;
    }

    public void setGrossInterestAmount(String grossInterestAmount) {
        this.grossInterestAmount = grossInterestAmount;
    }

    public String getIntPaidNetInterestAmount() {
        return intPaidNetInterestAmount;
    }

    public void setIntPaidNetInterestAmount(String intPaidNetInterestAmount) {
        this.intPaidNetInterestAmount = intPaidNetInterestAmount;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchValue() {
        return searchValue;
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
}
