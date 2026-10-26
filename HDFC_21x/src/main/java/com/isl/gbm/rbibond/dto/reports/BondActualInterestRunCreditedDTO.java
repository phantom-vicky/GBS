package com.isl.gbm.rbibond.dto.reports;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BondActualInterestRunCreditedDTO {
    private String modeOfPayment;
    private String branchRefNo;
    private String blaNo;
    private String refNo;
    private String appName1;
    private String appName2;
    private String appName3;
    private String address1;
    private String address2;
    private String recName;
    private String recParentName;
    private String pinCode;
    private String phoneNo;
    private String appStatus;
    private String investmentAmount;
    private String cfd;
    private String maturityDate;
    private String accountNo;
    private String subscriptionCount;
    private String bankSortCode;
    private String bankName;
    private String panNo;
    private String interestPayOut8Per;
    private String nonCumulativeInt;
    private String cumulativeInt;
    private String interestPayOut7Per;
    private String nonCumulativeIntFor7;
    private String cumulativeIntFor7;
    private String total;
    private String inOperativePAN;
    private String nonFilers;
    private String age;
    private String taxableLimit;
    private String waiverDocument;
    private String waiverDocumentRefNo;
    private String rrbTDSRate;
    private String thresholdExceedAmount;
    private String thresholdExceedTDSAmount;
    private String exemptionRate;
    private String exemptionAmount;
    private String exemptionTDSAmount;
    private String surcharge;
    private String eduCess;
    private String baseRateAmount;
    private String surchargeAmount;
    private String eduCessAmount;
    private String tdsAmount;
    private String tdsRecoveryAmount;
    private String tdsRefundAmount;
    private String intRate;
    private String lastIntPaid;
    private String pendingInterest;
    private String currentHalfYearlyInterest;
    private String futureInterest;
    private String grossInterestAmount;
    private String intPaid;
    private String paymentDate;
    private String returnReason;
    private String returnAmt;
    private String reInitiatedAmt;
    private String reInitiatedDate;
    private String paymentDateOfReturn;
    private String reInitiatedPaymentMode;
    private String flag;
    private String subscriptionId;
    private String holderName;
    private String bondType;
    private String subscriptionDate;
    private String subscriptionAmt;
    private String schemeName;
    private String schemeCode;
    private String schemeStatus;
    private String fromDate;
    private String toDate;
    private String modeOfHolding;
    private String pageNo;
    private String pageSize;
    private String searchValue;
    private String sortOrder;

    public String getPanNoName() {
        return panNoName;
    }

    public void setPanNoName(String panNoName) {
        this.panNoName = panNoName;
    }

    private String sortField;
    private String downloadType;
    private String panNoName;

    public String getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(String downloadType) {
        this.downloadType = downloadType;
    }

    public String getModeOfHolding() {
        return modeOfHolding;
    }

    public void setModeOfHolding(String modeOfHolding) {
        this.modeOfHolding = modeOfHolding;
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

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getSchemeStatus() {
        return schemeStatus;
    }

    public void setSchemeStatus(String schemeStatus) {
        this.schemeStatus = schemeStatus;
    }

    public String getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(String subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public String getSubscriptionAmt() {
        return subscriptionAmt;
    }

    public void setSubscriptionAmt(String subscriptionAmt) {
        this.subscriptionAmt = subscriptionAmt;
    }

    public String getBondType() {
        return bondType;
    }

    public void setBondType(String bondType) {
        this.bondType = bondType;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
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

    public String getAppName1() {
        return appName1;
    }

    public void setAppName1(String appName1) {
        this.appName1 = appName1;
    }

    public String getAppName2() {
        return appName2;
    }

    public void setAppName2(String appName2) {
        this.appName2 = appName2;
    }

    public String getAppName3() {
        return appName3;
    }

    public void setAppName3(String appName3) {
        this.appName3 = appName3;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
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

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public String getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(String investmentAmount) {
        this.investmentAmount = investmentAmount;
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



    public String getInterestPayOut8Per() {
        return interestPayOut8Per;
    }

    public void setInterestPayOut8Per(String interestPayOut8Per) {
        this.interestPayOut8Per = interestPayOut8Per;
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

    public String getInterestPayOut7Per() {
        return interestPayOut7Per;
    }

    public void setInterestPayOut7Per(String interestPayOut7Per) {
        this.interestPayOut7Per = interestPayOut7Per;
    }

    public String getNonCumulativeIntFor7() {
        return nonCumulativeIntFor7;
    }

    public void setNonCumulativeIntFor7(String nonCumulativeIntFor7) {
        this.nonCumulativeIntFor7 = nonCumulativeIntFor7;
    }

    public String getCumulativeIntFor7() {
        return cumulativeIntFor7;
    }

    public void setCumulativeIntFor7(String cumulativeIntFor7) {
        this.cumulativeIntFor7 = cumulativeIntFor7;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getInOperativePAN() {
        return inOperativePAN;
    }

    public void setInOperativePAN(String inOperativePAN) {
        this.inOperativePAN = inOperativePAN;
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

    public String getRrbTDSRate() {
        return rrbTDSRate;
    }

    public void setRrbTDSRate(String rrbTDSRate) {
        this.rrbTDSRate = rrbTDSRate;
    }

    public String getThresholdExceedAmount() {
        return thresholdExceedAmount;
    }

    public void setThresholdExceedAmount(String thresholdExceedAmount) {
        this.thresholdExceedAmount = thresholdExceedAmount;
    }

    public String getThresholdExceedTDSAmount() {
        return thresholdExceedTDSAmount;
    }

    public void setThresholdExceedTDSAmount(String thresholdExceedTDSAmount) {
        this.thresholdExceedTDSAmount = thresholdExceedTDSAmount;
    }

    public String getExemptionRate() {
        return exemptionRate;
    }

    public void setExemptionRate(String exemptionRate) {
        this.exemptionRate = exemptionRate;
    }

    public String getExemptionAmount() {
        return exemptionAmount;
    }

    public void setExemptionAmount(String exemptionAmount) {
        this.exemptionAmount = exemptionAmount;
    }

    public String getExemptionTDSAmount() {
        return exemptionTDSAmount;
    }

    public void setExemptionTDSAmount(String exemptionTDSAmount) {
        this.exemptionTDSAmount = exemptionTDSAmount;
    }

    public String getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(String surcharge) {
        this.surcharge = surcharge;
    }

    public String getEduCess() {
        return eduCess;
    }

    public void setEduCess(String eduCess) {
        this.eduCess = eduCess;
    }

    public String getBaseRateAmount() {
        return baseRateAmount;
    }

    public void setBaseRateAmount(String baseRateAmount) {
        this.baseRateAmount = baseRateAmount;
    }

    public String getSurchargeAmount() {
        return surchargeAmount;
    }

    public void setSurchargeAmount(String surchargeAmount) {
        this.surchargeAmount = surchargeAmount;
    }

    public String getEduCessAmount() {
        return eduCessAmount;
    }

    public void setEduCessAmount(String eduCessAmount) {
        this.eduCessAmount = eduCessAmount;
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

    public String getIntPaid() {
        return intPaid;
    }

    public void setIntPaid(String intPaid) {
        this.intPaid = intPaid;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public String getReturnAmt() {
        return returnAmt;
    }

    public void setReturnAmt(String returnAmt) {
        this.returnAmt = returnAmt;
    }

    public String getReInitiatedAmt() {
        return reInitiatedAmt;
    }

    public void setReInitiatedAmt(String reInitiatedAmt) {
        this.reInitiatedAmt = reInitiatedAmt;
    }

    public String getReInitiatedDate() {
        return reInitiatedDate;
    }

    public void setReInitiatedDate(String reInitiatedDate) {
        this.reInitiatedDate = reInitiatedDate;
    }

    public String getPaymentDateOfReturn() {
        return paymentDateOfReturn;
    }

    public void setPaymentDateOfReturn(String paymentDateOfReturn) {
        this.paymentDateOfReturn = paymentDateOfReturn;
    }

    public String getReInitiatedPaymentMode() {
        return reInitiatedPaymentMode;
    }

    public void setReInitiatedPaymentMode(String reInitiatedPaymentMode) {
        this.reInitiatedPaymentMode = reInitiatedPaymentMode;
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
