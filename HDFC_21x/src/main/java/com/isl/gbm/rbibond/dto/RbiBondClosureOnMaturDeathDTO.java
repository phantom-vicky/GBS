package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RbiBondClosureOnMaturDeathDTO {

    private String flag;
    private String blaNo;
    private String moduleId;
    private String bondHolderName;
    private String modeOfHolding;
    private String verifiedStatus;
    private String boSubsId;
    private String schemeCode;
    private String schemeName;
    private String legalPanNo;
    private String schemeStatus;
    private String maturityDate;
    private String holderType;
    private String modeOfHoldingCode;
    private String jointHolderCode;
    private String isNri;
    private String subsDate;
    private String subsAmt;
    private String bondType;
    private String intPaidUpto;
    private String intCalFromDate;
    private String intCalToDate;
    private String lastHalfYrIntRate;
    private String lastHalfYrIntAmt;
    private String penality;
    private String netIntLastHalfYr;
    private String pendIntLastHalfYr;
    private String totIntBefTDS;
    private String tdsAmt;
    private String firstName;
    private String dateOfBirthOfMinor;
    private String totIntAftTDS;
    private String amtPaid;
    private String modeOfInterestPay;
    private String credAccNum;
    private String oprAccNum;
    private String oprAccBrId;
    private String bankName;
    private String brCode;
    private String ifscCode;
    private String payeeName;
    private String firstJointBondHolderBLA;
    private String firstJointBondHolderName;
    private String secondJointBondHolderBLA;
    private String secondJointBondHolderName;
    private String closureRemarks;
    private String islastYearIntPaid;
    private String debBondSusAcc;
    private String debBondSundryAcc;
    private String credAccNumber;
    private String credBondTdsAcc;
    private String closureType;

    private String legalheirName;
    private String relationShip;

    private String brokenIntRate;
    private String brokenIntAmt;

    private String debBondSusAccFlag;
    private String debBondSundryAccFlag;
    private String credBondTdsAccFlag;
    private String ddPoAccNumFlag;
    private String ddPoAccNum;

    private String holderTypeCode;
    private String panNo;
    private List<RbiBondClosureOnMaturDeathNomiineeDTO> panNumList;
    private String detailsOrSubmit;
    private List<RbiBondClosureOnMaturDeathDTO> blaDetailsList;
    private List<RbiBondClosureOnMaturDeathDTO> bondSubsList;

    private List<RbiBondClosureOnMaturDeathDTO>  nomineeDetailList;
    private List<RbiBondClosureOnMaturDeathDTO.RbiBondClosureOnMaturDeathDTO1>  nomineeDetailList1;



    public List<RbiBondClosureOnMaturDeathDTO> getBlaDetailsList() {
        return blaDetailsList;
    }

    public void setBlaDetailsList(List<RbiBondClosureOnMaturDeathDTO> blaDetailsList) {
        this.blaDetailsList = blaDetailsList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDateOfBirthOfMinor() {
        return dateOfBirthOfMinor;
    }

    public void setDateOfBirthOfMinor(String dateOfBirthOfMinor) {
        this.dateOfBirthOfMinor = dateOfBirthOfMinor;
    }

    public List<RbiBondClosureOnMaturDeathDTO> getBondSubsList() {
        return bondSubsList;
    }

    public void setBondSubsList(List<RbiBondClosureOnMaturDeathDTO> bondSubsList) {
        this.bondSubsList = bondSubsList;
    }


    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getHolderTypeCode() {
        return holderTypeCode;
    }

    public void setHolderTypeCode(String holderTypeCode) {
        this.holderTypeCode = holderTypeCode;
    }


    public String getDebBondSusAccFlag() {
        return debBondSusAccFlag;
    }

    public void setDebBondSusAccFlag(String debBondSusAccFlag) {
        this.debBondSusAccFlag = debBondSusAccFlag;
    }

    public String getDebBondSundryAccFlag() {
        return debBondSundryAccFlag;
    }

    public void setDebBondSundryAccFlag(String debBondSundryAccFlag) {
        this.debBondSundryAccFlag = debBondSundryAccFlag;
    }

    public String getCredBondTdsAccFlag() {
        return credBondTdsAccFlag;
    }

    public void setCredBondTdsAccFlag(String credBondTdsAccFlag) {
        this.credBondTdsAccFlag = credBondTdsAccFlag;
    }

    public String getDdPoAccNumFlag() {
        return ddPoAccNumFlag;
    }

    public void setDdPoAccNumFlag(String ddPoAccNumFlag) {
        this.ddPoAccNumFlag = ddPoAccNumFlag;
    }

    public String getDdPoAccNum() {
        return ddPoAccNum;
    }

    public void setDdPoAccNum(String ddPoAccNum) {
        this.ddPoAccNum = ddPoAccNum;
    }

    public String getBrokenIntRate() {
        return brokenIntRate;
    }

    public void setBrokenIntRate(String brokenIntRate) {
        this.brokenIntRate = brokenIntRate;
    }

    public String getBrokenIntAmt() {
        return brokenIntAmt;
    }

    public void setBrokenIntAmt(String brokenIntAmt) {
        this.brokenIntAmt = brokenIntAmt;
    }

    private String initalNomineeAccNo;
    private String initalNomineeBranchId;

    public String getInitalNomineeAccNo() {
        return initalNomineeAccNo;
    }

    public void setInitalNomineeAccNo(String initalNomineeAccNo) {
        this.initalNomineeAccNo = initalNomineeAccNo;
    }

    public String getInitalNomineeBranchId() {
        return initalNomineeBranchId;
    }

    public void setInitalNomineeBranchId(String initalNomineeBranchId) {
        this.initalNomineeBranchId = initalNomineeBranchId;
    }

    public String getLegalheirName() {
        return legalheirName;
    }

    public void setLegalheirName(String legalheirName) {
        this.legalheirName = legalheirName;
    }

    public String getRelationShip() {
        return relationShip;
    }

    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }

    public String getLegalPanNo() {
        return legalPanNo;
    }

    public void setLegalPanNo(String legalPanNo) {
        this.legalPanNo = legalPanNo;
    }

    private String createdBy;
    private String createdDate;
    private String verifiedBy;
    private String verifiedDate;
    private String gbmTranId;
    private String accClosedDate;

    private  String  proceedsToPaidCode;
    private String pendingInterest;


    private String modeOfPayment;
    private String operativeAccNum;
    private String branchRefNo;
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
    private String accountNo;
    private String subscriptionCount;
    private String bankSortCode;
    private String panNoName;
    private String interestPayOut8Per;

    public String getTotalPayoutOfTheYear() {
        return totalPayoutOfTheYear;
    }

    public void setTotalPayoutOfTheYear(String totalPayoutOfTheYear) {
        this.totalPayoutOfTheYear = totalPayoutOfTheYear;
    }

    private String totalPayoutOfTheYear;

    public String getInterestPayOut85Per() {
        return interestPayOut85Per;
    }

    public void setInterestPayOut85Per(String interestPayOut85Per) {
        this.interestPayOut85Per = interestPayOut85Per;
    }

    public String getNonCumulativeIntFor85() {
        return nonCumulativeIntFor85;
    }

    public void setNonCumulativeIntFor85(String nonCumulativeIntFor85) {
        this.nonCumulativeIntFor85 = nonCumulativeIntFor85;
    }

    public String getCumulativeIntFor85() {
        return cumulativeIntFor85;
    }

    public void setCumulativeIntFor85(String cumulativeIntFor85) {
        this.cumulativeIntFor85 = cumulativeIntFor85;
    }

    private String nonCumulativeInt;
    private String cumulativeInt;
    private String interestPayOut7Per;
    private String nonCumulativeIntFor7;
    private String cumulativeIntFor7;

    private String interestPayOut85Per;
    private String nonCumulativeIntFor85;
    private String cumulativeIntFor85;

    private String total;
    private String inOperativePAN;
    private String nonFilers;
    private String age;
    private String taxableLimit;
    private String waiverDocument;
    private String waiverDocumentRefNo;
    private String rrbTDSRate;
    private String tdsRate;
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
    private String trailRunType;
    private String lastIntPaid;
    private String currentHalfYearlyInterest;
    private String futureInterest;
    private String grossInterestAmount;
    private String intPaid;
    private String dod;
    private String dodCertReceiptDate;
    private String paymentDate;
    private String returnReason;
    private String returnAmt;
    private String reInitiatedAmt;
    private String reInitiatedDate;
    private String paymentDateOfReturn;
    private String deleteRemark;
    private String name;
    private String dob;
    private String principalAmt;
    private String totalAmtToBePaid;
    private String paymentDateAtEnd;
    private String closureRemark;
    private String fromDate;
    private String toDate;
    private String checkerRemarkForClosure;
    private String rejectionRemark;
    private String reInitiatedPaymentMode;
    private List<String> recordsToBeRejected;
    private List<String> recordsToBeVerified;
    private List<VerifyArrayObject> verifyArrayObject;

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

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getDod() {
        return dod;
    }

    public void setDod(String dod) {
        this.dod = dod;
    }

    public String getDodCertReceiptDate() {
        return dodCertReceiptDate;
    }

    public void setDodCertReceiptDate(String dodCertReceiptDate) {
        this.dodCertReceiptDate = dodCertReceiptDate;
    }

    public String getAppName1() {
        return appName1;
    }

    public void setAppName1(String appName1) {
        this.appName1 = appName1;
    }

    public String getOperativeAccNum() {
        return operativeAccNum;
    }

    public void setOperativeAccNum(String operativeAccNum) {
        this.operativeAccNum = operativeAccNum;
    }

    public String getAppName2() {
        return appName2;
    }

    public void setAppName2(String appName2) {
        this.appName2 = appName2;
    }

    public List<RbiBondClosureOnMaturDeathDTO> getNomineeDetailList() {
        return nomineeDetailList;
    }

    public void setNomineeDetailList(List<RbiBondClosureOnMaturDeathDTO> nomineeDetailList) {
        this.nomineeDetailList = nomineeDetailList;
    }

    public List<RbiBondClosureOnMaturDeathDTO1> getNomineeDetailList1() {
        return nomineeDetailList1;
    }

    public void setNomineeDetailList1(List<RbiBondClosureOnMaturDeathDTO1> nomineeDetailList1) {
        this.nomineeDetailList1 = nomineeDetailList1;
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

    public String getTdsRate() {
        return tdsRate;
    }

    public void setTdsRate(String tdsRate) {
        this.tdsRate = tdsRate;
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

    public String getPanNoName() {
        return panNoName;
    }

    public void setPanNoName(String panNoName) {
        this.panNoName = panNoName;
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

    public String getTrailRunType() {
        return trailRunType;
    }

    public void setTrailRunType(String trailRunType) {
        this.trailRunType = trailRunType;
    }

    public String getLastIntPaid() {
        return lastIntPaid;
    }

    public void setLastIntPaid(String lastIntPaid) {
        this.lastIntPaid = lastIntPaid;
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

    public String getDeleteRemark() {
        return deleteRemark;
    }

    public void setDeleteRemark(String deleteRemark) {
        this.deleteRemark = deleteRemark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPrincipalAmt() {
        return principalAmt;
    }

    public void setPrincipalAmt(String principalAmt) {
        this.principalAmt = principalAmt;
    }

    public String getTotalAmtToBePaid() {
        return totalAmtToBePaid;
    }

    public void setTotalAmtToBePaid(String totalAmtToBePaid) {
        this.totalAmtToBePaid = totalAmtToBePaid;
    }

    public String getPaymentDateAtEnd() {
        return paymentDateAtEnd;
    }

    public void setPaymentDateAtEnd(String paymentDateAtEnd) {
        this.paymentDateAtEnd = paymentDateAtEnd;
    }

    public String getClosureRemark() {
        return closureRemark;
    }

    public void setClosureRemark(String closureRemark) {
        this.closureRemark = closureRemark;
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

    public String getCheckerRemarkForClosure() {
        return checkerRemarkForClosure;
    }

    public void setCheckerRemarkForClosure(String checkerRemarkForClosure) {
        this.checkerRemarkForClosure = checkerRemarkForClosure;
    }

    public String getRejectionRemark() {
        return rejectionRemark;
    }

    public void setRejectionRemark(String rejectionRemark) {
        this.rejectionRemark = rejectionRemark;
    }

    public String getReInitiatedPaymentMode() {
        return reInitiatedPaymentMode;
    }

    public void setReInitiatedPaymentMode(String reInitiatedPaymentMode) {
        this.reInitiatedPaymentMode = reInitiatedPaymentMode;
    }

    public List<String> getRecordsToBeRejected() {
        return recordsToBeRejected;
    }

    public void setRecordsToBeRejected(List<String> recordsToBeRejected) {
        this.recordsToBeRejected = recordsToBeRejected;
    }

    public List<String> getRecordsToBeVerified() {
        return recordsToBeVerified;
    }

    public void setRecordsToBeVerified(List<String> recordsToBeVerified) {
        this.recordsToBeVerified = recordsToBeVerified;
    }

    public List<VerifyArrayObject> getVerifyArrayObject() {
        return verifyArrayObject;
    }

    public void setVerifyArrayObject(List<VerifyArrayObject> verifyArrayObject) {
        this.verifyArrayObject = verifyArrayObject;
    }

    public String getPendingInterest() {
        return pendingInterest;
    }

    public void setPendingInterest(String pendingInterest) {
        this.pendingInterest = pendingInterest;
    }

    public String getProceedsToPaidCode() {
        return proceedsToPaidCode;
    }

    public void setProceedsToPaidCode(String proceedsToPaidCode) {
        this.proceedsToPaidCode = proceedsToPaidCode;
    }

    public String getAccClosedDate() {
        return accClosedDate;
    }

    public void setAccClosedDate(String accClosedDate) {
        this.accClosedDate = accClosedDate;
    }

    private String penalityToBeRecovered;
    private String proceedsToBePaid;
    private String dodBondHolder;
    private String dateOfReceiptDod;
    private String nomineeName;
    private String nomineeDOB;
    private String sharePercentage;
    private String shareAmountSettled;
    private String accNum;
    private String accBranch;
    private String nomineeId;
    private String remarks;

    public String getNomineeDOB() {
        return nomineeDOB;
    }

    public void setNomineeDOB(String nomineeDOB) {
        this.nomineeDOB = nomineeDOB;
    }

    public String getAccNum() {
        return accNum;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }

    public String getAccBranch() {
        return accBranch;
    }

    public void setAccBranch(String accBranch) {
        this.accBranch = accBranch;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getNomineeName() {
        return nomineeName;
    }

    public void setNomineeName(String nomineeName) {
        this.nomineeName = nomineeName;
    }

    public String getSharePercentage() {
        return sharePercentage;
    }

    public void setSharePercentage(String sharePercentage) {
        this.sharePercentage = sharePercentage;
    }

    public String getShareAmountSettled() {
        return shareAmountSettled;
    }

    public void setShareAmountSettled(String shareAmountSettled) {
        this.shareAmountSettled = shareAmountSettled;
    }


    public String getNomineeId() {
        return nomineeId;
    }

    public void setNomineeId(String nomineeId) {
        this.nomineeId = nomineeId;
    }

    public String getDodBondHolder() {
        return dodBondHolder;
    }

    public void setDodBondHolder(String dodBondHolder) {
        this.dodBondHolder = dodBondHolder;
    }

    public String getDateOfReceiptDod() {
        return dateOfReceiptDod;
    }

    public void setDateOfReceiptDod(String dateOfReceiptDod) {
        this.dateOfReceiptDod = dateOfReceiptDod;
    }

    public String getPenalityToBeRecovered() {
        return penalityToBeRecovered;
    }

    public void setPenalityToBeRecovered(String penalityToBeRecovered) {
        this.penalityToBeRecovered = penalityToBeRecovered;
    }

    public String getProceedsToBePaid() {
        return proceedsToBePaid;
    }

    public void setProceedsToBePaid(String proceedsToBePaid) {
        this.proceedsToBePaid = proceedsToBePaid;
    }

    public String getGbmTranId() {
        return gbmTranId;
    }

    public void setGbmTranId(String gbmTranId) {
        this.gbmTranId = gbmTranId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public String getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(String verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    public String getClosureType() {
        return closureType;
    }

    public void setClosureType(String closureType) {
        this.closureType = closureType;
    }

    private Object bondDetails;
    private List<RbiBondClosureOnMaturDeathNomiineeDTO> nomineeDetails;
    private List<RbiBondClosureOnMaturDeathLegalHierDTO> legalHeirDeatails;

    public List<RbiBondClosureOnMaturDeathNomiineeDTO> getNomineeDetails() {
        return nomineeDetails;
    }

    public void setNomineeDetails(List<RbiBondClosureOnMaturDeathNomiineeDTO> nomineeDetails) {
        this.nomineeDetails = nomineeDetails;
    }

    public List<RbiBondClosureOnMaturDeathLegalHierDTO> getLegalHeirDeatails() {
        return legalHeirDeatails;
    }

    public void setLegalHeirDeatails(List<RbiBondClosureOnMaturDeathLegalHierDTO> legalHeirDeatails) {
        this.legalHeirDeatails = legalHeirDeatails;
    }

    public Object getBondDetails() {
        return bondDetails;
    }

    public void setBondDetails(Object bondDetails) {
        this.bondDetails = bondDetails;
    }

    public String getDebBondSusAcc() {
        return debBondSusAcc;
    }

    public void setDebBondSusAcc(String debBondSusAcc) {
        this.debBondSusAcc = debBondSusAcc;
    }

    public String getDebBondSundryAcc() {
        return debBondSundryAcc;
    }

    public void setDebBondSundryAcc(String debBondSundryAcc) {
        this.debBondSundryAcc = debBondSundryAcc;
    }

    public String getCredAccNumber() {
        return credAccNumber;
    }

    public void setCredAccNumber(String credAccNumber) {
        this.credAccNumber = credAccNumber;
    }

    public String getCredBondTdsAcc() {
        return credBondTdsAcc;
    }

    public void setCredBondTdsAcc(String credBondTdsAcc) {
        this.credBondTdsAcc = credBondTdsAcc;
    }

    public String getModeOfInterestPay() {
        return modeOfInterestPay;
    }

    public void setModeOfInterestPay(String modeOfInterestPay) {
        this.modeOfInterestPay = modeOfInterestPay;
    }

    public String getIslastYearIntPaid() {
        return islastYearIntPaid;
    }

    public void setIslastYearIntPaid(String islastYearIntPaid) {
        this.islastYearIntPaid = islastYearIntPaid;
    }

    public String getClosureRemarks() {
        return closureRemarks;
    }

    public void setClosureRemarks(String closureRemarks) {
        this.closureRemarks = closureRemarks;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getAmtPaid() {
        return amtPaid;
    }

    public void setAmtPaid(String amtPaid) {
        this.amtPaid = amtPaid;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getBlaNo() {
        return blaNo;
    }

    public void setBlaNo(String blaNo) {
        this.blaNo = blaNo;
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

    public String getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setVerifiedStatus(String verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    public String getBoSubsId() {
        return boSubsId;
    }

    public void setBoSubsId(String boSubsId) {
        this.boSubsId = boSubsId;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getHolderType() {
        return holderType;
    }

    public void setHolderType(String holderType) {
        this.holderType = holderType;
    }

    public String getModeOfHoldingCode() {
        return modeOfHoldingCode;
    }

    public void setModeOfHoldingCode(String modeOfHoldingCode) {
        this.modeOfHoldingCode = modeOfHoldingCode;
    }

    public String getJointHolderCode() {
        return jointHolderCode;
    }

    public void setJointHolderCode(String jointHolderCode) {
        this.jointHolderCode = jointHolderCode;
    }

    public String getIsNri() {
        return isNri;
    }

    public void setIsNri(String isNri) {
        this.isNri = isNri;
    }

    public String getSubsDate() {
        return subsDate;
    }

    public void setSubsDate(String subsDate) {
        this.subsDate = subsDate;
    }

    public String getSubsAmt() {
        return subsAmt;
    }

    public void setSubsAmt(String subsAmt) {
        this.subsAmt = subsAmt;
    }

    public String getBondType() {
        return bondType;
    }

    public void setBondType(String bondType) {
        this.bondType = bondType;
    }

    public String getIntPaidUpto() {
        return intPaidUpto;
    }

    public void setIntPaidUpto(String intPaidUpto) {
        this.intPaidUpto = intPaidUpto;
    }

    public String getIntCalFromDate() {
        return intCalFromDate;
    }

    public void setIntCalFromDate(String intCalFromDate) {
        this.intCalFromDate = intCalFromDate;
    }

    public String getIntCalToDate() {
        return intCalToDate;
    }

    public void setIntCalToDate(String intCalToDate) {
        this.intCalToDate = intCalToDate;
    }

    public String getLastHalfYrIntRate() {
        return lastHalfYrIntRate;
    }

    public void setLastHalfYrIntRate(String lastHalfYrIntRate) {
        this.lastHalfYrIntRate = lastHalfYrIntRate;
    }

    public String getLastHalfYrIntAmt() {
        return lastHalfYrIntAmt;
    }

    public void setLastHalfYrIntAmt(String lastHalfYrIntAmt) {
        this.lastHalfYrIntAmt = lastHalfYrIntAmt;
    }

    public String getPenality() {
        return penality;
    }

    public void setPenality(String penality) {
        this.penality = penality;
    }

    public String getNetIntLastHalfYr() {
        return netIntLastHalfYr;
    }

    public void setNetIntLastHalfYr(String netIntLastHalfYr) {
        this.netIntLastHalfYr = netIntLastHalfYr;
    }

    public String getPendIntLastHalfYr() {
        return pendIntLastHalfYr;
    }

    public void setPendIntLastHalfYr(String pendIntLastHalfYr) {
        this.pendIntLastHalfYr = pendIntLastHalfYr;
    }

    public String getTotIntBefTDS() {
        return totIntBefTDS;
    }

    public void setTotIntBefTDS(String totIntBefTDS) {
        this.totIntBefTDS = totIntBefTDS;
    }

    public String getTdsAmt() {
        return tdsAmt;
    }

    public void setTdsAmt(String tdsAmt) {
        this.tdsAmt = tdsAmt;
    }

    public String getTotIntAftTDS() {
        return totIntAftTDS;
    }

    public void setTotIntAftTDS(String totIntAftTDS) {
        this.totIntAftTDS = totIntAftTDS;
    }



    public String getCredAccNum() {
        return credAccNum;
    }

    public void setCredAccNum(String credAccNum) {
        this.credAccNum = credAccNum;
    }

    public String getOprAccNum() {
        return oprAccNum;
    }

    public void setOprAccNum(String oprAccNum) {
        this.oprAccNum = oprAccNum;
    }

    public String getOprAccBrId() {
        return oprAccBrId;
    }

    public void setOprAccBrId(String oprAccBrId) {
        this.oprAccBrId = oprAccBrId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBrCode() {
        return brCode;
    }

    public void setBrCode(String brCode) {
        this.brCode = brCode;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getFirstJointBondHolderBLA() {
        return firstJointBondHolderBLA;
    }

    public void setFirstJointBondHolderBLA(String firstJointBondHolderBLA) {
        this.firstJointBondHolderBLA = firstJointBondHolderBLA;
    }

    public String getFirstJointBondHolderName() {
        return firstJointBondHolderName;
    }

    public void setFirstJointBondHolderName(String firstJointBondHolderName) {
        this.firstJointBondHolderName = firstJointBondHolderName;
    }

    public String getSecondJointBondHolderBLA() {
        return secondJointBondHolderBLA;
    }

    public void setSecondJointBondHolderBLA(String secondJointBondHolderBLA) {
        this.secondJointBondHolderBLA = secondJointBondHolderBLA;
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

    public List<RbiBondClosureOnMaturDeathNomiineeDTO> getPanNumList() {
        return panNumList;
    }

    public void setPanNumList(List<RbiBondClosureOnMaturDeathNomiineeDTO> panNumList) {
        this.panNumList = panNumList;
    }

    public String getDetailsOrSubmit() {
        return detailsOrSubmit;
    }

    public void setDetailsOrSubmit(String detailsOrSubmit) {
        this.detailsOrSubmit = detailsOrSubmit;
    }

    public void setSchemeStatus(String schemeStatus) {
        this.schemeStatus = schemeStatus;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getSecondJointBondHolderName() {
        return secondJointBondHolderName;
    }

    public void setSecondJointBondHolderName(String secondJointBondHolderName) {
        this.secondJointBondHolderName = secondJointBondHolderName;
    }



    public class RbiBondClosureOnMaturDeathDTO1 {
        private String firstName;
        private String panNo;
        private Date dateOfBirthOfMinor;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getPanNo() {
            return panNo;
        }

        public void setPanNo(String panNo) {
            this.panNo = panNo;
        }

        public Date getDateOfBirthOfMinor() {
            return dateOfBirthOfMinor;
        }

        public void setDateOfBirthOfMinor(Date dateOfBirthOfMinor) {
            this.dateOfBirthOfMinor = dateOfBirthOfMinor;
        }

        // Getters and setters
    }
}
