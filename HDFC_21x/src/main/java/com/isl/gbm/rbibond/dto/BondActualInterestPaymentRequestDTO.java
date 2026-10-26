package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BondActualInterestPaymentRequestDTO {

    private String bodDate;
    private String moduleName;
    @NotBlank(message = "Scheme Code not Blank")
    private String schemeCode;
    private String schemeName;
    private String verifyStatus;
    private String schemeStatus;
    @NotBlank(message = "Half Year not Blank")
    private String halfYear;
    @NotBlank(message = "Year Not Blank")
    @Pattern(regexp = "\\d{4}", message = "Year must be a 4-digit number")
    private String year;
    private String modeOfPayment;
    private String branchRefNo;
    private String blaNo;
    private String closureType;
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
    private String panNoName;
    private String interestPayOut8Per;
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
    private String pendingInterest;
    private String currentHalfYearlyInterest;
    private String futureInterest;
    private String grossInterestAmount;
    private String intPaid;
    private String flag;
    private String detailsOrSubmit;
    private String paymentDate;
    private String returnReason;
    private String returnAmt;
    private String reInitiatedAmt;
    private String reInitiatedDate;
    private String paymentDateOfReturn;
    private String deleteRemark;
    private String modeOfHolding;
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
    private String  totalPayOutOfTheYear;
    private String  downloadType;
    private String paymentStatus;

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(String downloadType) {
        this.downloadType = downloadType;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

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

    public String getTotalPayOutOfTheYear() {
        return totalPayOutOfTheYear;
    }

    public void setTotalPayOutOfTheYear(String totalPayOutOfTheYear) {
        this.totalPayOutOfTheYear = totalPayOutOfTheYear;
    }

    @Override
    public String toString() {
        return "BondActualInterestPaymentRequestDTO{" +
                "bodDate='" + bodDate + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", schemeCode='" + schemeCode + '\'' +
                ", schemeName='" + schemeName + '\'' +
                ", schemeStatus='" + schemeStatus + '\'' +
                ", halfYear='" + halfYear + '\'' +
                ", year='" + year + '\'' +
                ", modeOfPayment='" + modeOfPayment + '\'' +
                ", branchRefNo='" + branchRefNo + '\'' +
                ", blaNo='" + blaNo + '\'' +
                ", closureType='" + closureType + '\'' +
                ", refNo='" + refNo + '\'' +
                ", appName1='" + appName1 + '\'' +
                ", appName2='" + appName2 + '\'' +
                ", appName3='" + appName3 + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", recName='" + recName + '\'' +
                ", recParentName='" + recParentName + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", appStatus='" + appStatus + '\'' +
                ", investmentAmount='" + investmentAmount + '\'' +
                ", cfd='" + cfd + '\'' +
                ", maturityDate='" + maturityDate + '\'' +
                ", checkerRemarkForClosure='" + checkerRemarkForClosure + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", subscriptionCount='" + subscriptionCount + '\'' +
                ", bankSortCode='" + bankSortCode + '\'' +
                ", bankName='" + bankName + '\'' +
                ", panNoName='" + panNoName + '\'' +
                ", interestPayOut8Per='" + interestPayOut8Per + '\'' +
                ", nonCumulativeInt='" + nonCumulativeInt + '\'' +
                ", cumulativeInt='" + cumulativeInt + '\'' +
                ", interestPayOut7Per='" + interestPayOut7Per + '\'' +
                ", nonCumulativeIntFor7='" + nonCumulativeIntFor7 + '\'' +
                ", cumulativeIntFor7='" + cumulativeIntFor7 + '\'' +
                ", interestPayOut85Per='" + interestPayOut85Per + '\'' +
                ", nonCumulativeIntFor85='" + nonCumulativeIntFor85 + '\'' +
                ", cumulativeIntFor85='" + cumulativeIntFor85 + '\'' +
                ", total='" + total + '\'' +
                ", inOperativePAN='" + inOperativePAN + '\'' +
                ", nonFilers='" + nonFilers + '\'' +
                ", age='" + age + '\'' +
                ", taxableLimit='" + taxableLimit + '\'' +
                ", waiverDocument='" + waiverDocument + '\'' +
                ", waiverDocumentRefNo='" + waiverDocumentRefNo + '\'' +
                ", rrbTDSRate='" + rrbTDSRate + '\'' +
                ", thresholdExceedAmount='" + thresholdExceedAmount + '\'' +
                ", thresholdExceedTDSAmount='" + thresholdExceedTDSAmount + '\'' +
                ", exemptionRate='" + exemptionRate + '\'' +
                ", exemptionAmount='" + exemptionAmount + '\'' +
                ", exemptionTDSAmount='" + exemptionTDSAmount + '\'' +
                ", surcharge='" + surcharge + '\'' +
                ", eduCess='" + eduCess + '\'' +
                ", baseRateAmount='" + baseRateAmount + '\'' +
                ", surchargeAmount='" + surchargeAmount + '\'' +
                ", eduCessAmount='" + eduCessAmount + '\'' +
                ", tdsAmount='" + tdsAmount + '\'' +
                ", tdsRecoveryAmount='" + tdsRecoveryAmount + '\'' +
                ", tdsRefundAmount='" + tdsRefundAmount + '\'' +
                ", intRate='" + intRate + '\'' +
                ", lastIntPaid='" + lastIntPaid + '\'' +
                ", pendingInterest='" + pendingInterest + '\'' +
                ", currentHalfYearlyInterest='" + currentHalfYearlyInterest + '\'' +
                ", futureInterest='" + futureInterest + '\'' +
                ", grossInterestAmount='" + grossInterestAmount + '\'' +
                ", intPaid='" + intPaid + '\'' +
                ", detailsOrSubmit='" + detailsOrSubmit + '\'' +
                ", paymentDate='" + paymentDate + '\'' +
                ", returnReason='" + returnReason + '\'' +
                ", returnAmt='" + returnAmt + '\'' +
                ", reInitiatedAmt='" + reInitiatedAmt + '\'' +
                ", reInitiatedDate='" + reInitiatedDate + '\'' +
                ", paymentDateOfReturn='" + paymentDateOfReturn + '\'' +
                ", deleteRemark='" + deleteRemark + '\'' +
                ", modeOfHolding='" + modeOfHolding + '\'' +
                ", name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", principalAmt='" + principalAmt + '\'' +
                ", totalAmtToBePaid='" + totalAmtToBePaid + '\'' +
                ", paymentDateAtEnd='" + paymentDateAtEnd + '\'' +
                ", closureRemark='" + closureRemark + '\'' +
                ", reInitiatedPaymentMode='" + reInitiatedPaymentMode + '\'' +
                ", recordsToBeRejected=" + recordsToBeRejected +
                ", recordsToBeVerified=" + recordsToBeVerified +
                ", verifyArrayObject=" + verifyArrayObject +
                ", totalPayOutOfTheYear=" + totalPayOutOfTheYear +
                '}';
    }

    public String getRejectionRemark() {
        return rejectionRemark;
    }

    public void setRejectionRemark(String rejectionRemark) {
        this.rejectionRemark = rejectionRemark;
    }

    public String getTrailRunType() {
        return trailRunType;
    }

    public void setTrailRunType(String trailRunType) {
        this.trailRunType = trailRunType;
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

    public List<VerifyArrayObject> getVerifyArrayObject() {
        return verifyArrayObject;
    }

    public void setVerifyArrayObject(List<VerifyArrayObject> verifyArrayObject) {
        this.verifyArrayObject = verifyArrayObject;
    }

    public String getCheckerRemarkForClosure() {
        return checkerRemarkForClosure;
    }

    public void setCheckerRemarkForClosure(String checkerRemarkForClosure) {
        this.checkerRemarkForClosure = checkerRemarkForClosure;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getClosureType() {
        return closureType;
    }

    public void setClosureType(String closureType) {
        this.closureType = closureType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModeOfHolding() {
        return modeOfHolding;
    }

    public void setModeOfHolding(String modeOfHolding) {
        this.modeOfHolding = modeOfHolding;
    }

    public String getDeleteRemark() {
        return deleteRemark;
    }

    public void setDeleteRemark(String deleteRemark) {
        this.deleteRemark = deleteRemark;
    }

    public List<String> getRecordsToBeVerified() {
        return recordsToBeVerified;
    }

    public void setRecordsToBeVerified(List<String> recordsToBeVerified) {
        this.recordsToBeVerified = recordsToBeVerified;
    }

    public List<String> getRecordsToBeRejected() {
        return recordsToBeRejected;
    }

    public void setRecordsToBeRejected(List<String> recordsToBeRejected) {
        this.recordsToBeRejected = recordsToBeRejected;
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

    public String getDetailsOrSubmit() {
        return detailsOrSubmit;
    }

    public void setDetailsOrSubmit(String detailsOrSubmit) {
        this.detailsOrSubmit = detailsOrSubmit;
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

    public String getBaseRateAmount() {
        return baseRateAmount;
    }

    public void setBaseRateAmount(String baseRateAmount) {
        this.baseRateAmount = baseRateAmount;
    }

    public String getBodDate() {
        return bodDate;
    }

    public void setBodDate(String bodDate) {
        this.bodDate = bodDate;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

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

    public String getSchemeStatus() {
        return schemeStatus;
    }

    public void setSchemeStatus(String schemeStatus) {
        this.schemeStatus = schemeStatus;
    }

    public String getHalfYear() {
        return halfYear;
    }

    public void setHalfYear(String halfYear) {
        this.halfYear = halfYear;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
