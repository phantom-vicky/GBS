package com.isl.gbm.rbibond.dto.reports;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlaModificationReportDto {

    private String fromDate;
    private String toDate;
    private String schemeCode;
    private String schemeName;
    private String flag;
    private String schemeStatus;
    private String schemeId;
    private String inwardRefNo;
    private String inwardRefDate;
    private String blaNo;
    private String firstApplicant;
    private String oldValue;
    private String secondApplicant;
    private String oldSecondApplicant;
    private String thirdApplicant;
    private String oldThirdApplicant;
    private String address;
    private String oldAddress;
    private String modeOfHolding;
    private String oldModeOfHolding;
    private String dob;
    private String oldDob;
    private String modeOfPayment;
    private String oldModeOfPayment;
    private String accountType;
    private String oldAccountType;
    private String accountNo;
    private String oldAccountNo;
    private String micr;
    private String oldMicr;
    private String applStatus;
    private String oldApplStatus;
    private String panNo;
    private String oldPanNo;
    private String checkerId;
    private String checkerDate;
    private String makerId;
    private String makerDate;
    private String authorizedStatus;
    private String hdfcBranchCode;
    private String oldHdfcBranchCode;
    private String remarks;

    private String email;
    private String oldEmail;
    private  String mobileNo;
    private String oldMobileNo;
    private String nomineeOne;

    public String getJointHolderType() {
        return jointHolderType;
    }

    public void setJointHolderType(String jointHolderType) {
        this.jointHolderType = jointHolderType;
    }

    private String nomineeOneOld;
    private String nomineeTwo;
    private String nomineeTwoOld;
    private String nomineeThree;
    private String nomineeThreeOld;
    private String nomineeFour;

    private String jointHolderType;

    private  String oldApplicant;
    private String applicant;

    public String getOldApplicant() {
        return oldApplicant;
    }

    public void setOldApplicant(String oldApplicant) {
        this.oldApplicant = oldApplicant;
    }


    private String oldFirstApplicant;


    public String getOldFirstApplicant() {
        return oldFirstApplicant;
    }

    public void setOldFirstApplicant(String oldFirstApplicant) {
        this.oldFirstApplicant = oldFirstApplicant;
    }



    private String addressOne;
    private String addressTwo;
    private String addressThree;

    private String oldAddressOne;
    private String oldAddressTwo;
    private String oldAddressThree;

    private String dobOne;
    private String dobTwo;
    private String dobThree;

    private String oldDobOne;
    private String oldDobTwo;
    private String oldDobThree;

    private String emailOne;
    private String emailTwo;
    private String emailThree;

    private String oldEmailOne;
    private String oldEmailTwo;
    private String oldEmailThree;

    private String mobileNoOne;
    private String mobileNoTwo;
    private String mobileNoThree;

    private String oldMobileNoOne;
    private String oldMobileNoTwo;

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getAddressOne() {
        return addressOne;
    }

    public void setAddressOne(String addressOne) {
        this.addressOne = addressOne;
    }

    public String getAddressTwo() {
        return addressTwo;
    }

    public void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }

    public String getAddressThree() {
        return addressThree;
    }

    public void setAddressThree(String addressThree) {
        this.addressThree = addressThree;
    }

    public String getOldAddressOne() {
        return oldAddressOne;
    }

    public void setOldAddressOne(String oldAddressOne) {
        this.oldAddressOne = oldAddressOne;
    }

    public String getOldAddressTwo() {
        return oldAddressTwo;
    }

    public void setOldAddressTwo(String oldAddressTwo) {
        this.oldAddressTwo = oldAddressTwo;
    }

    public String getOldAddressThree() {
        return oldAddressThree;
    }

    public void setOldAddressThree(String oldAddressThree) {
        this.oldAddressThree = oldAddressThree;
    }

    public String getDobOne() {
        return dobOne;
    }

    public void setDobOne(String dobOne) {
        this.dobOne = dobOne;
    }

    public String getDobTwo() {
        return dobTwo;
    }

    public void setDobTwo(String dobTwo) {
        this.dobTwo = dobTwo;
    }

    public String getDobThree() {
        return dobThree;
    }

    public void setDobThree(String dobThree) {
        this.dobThree = dobThree;
    }

    public String getOldDobOne() {
        return oldDobOne;
    }

    public void setOldDobOne(String oldDobOne) {
        this.oldDobOne = oldDobOne;
    }

    public String getOldDobTwo() {
        return oldDobTwo;
    }

    public void setOldDobTwo(String oldDobTwo) {
        this.oldDobTwo = oldDobTwo;
    }

    public String getOldDobThree() {
        return oldDobThree;
    }

    public void setOldDobThree(String oldDobThree) {
        this.oldDobThree = oldDobThree;
    }

    public String getEmailOne() {
        return emailOne;
    }

    public void setEmailOne(String emailOne) {
        this.emailOne = emailOne;
    }

    public String getEmailTwo() {
        return emailTwo;
    }

    public void setEmailTwo(String emailTwo) {
        this.emailTwo = emailTwo;
    }

    public String getEmailThree() {
        return emailThree;
    }

    public void setEmailThree(String emailThree) {
        this.emailThree = emailThree;
    }

    public String getOldEmailOne() {
        return oldEmailOne;
    }

    public void setOldEmailOne(String oldEmailOne) {
        this.oldEmailOne = oldEmailOne;
    }

    public String getOldEmailTwo() {
        return oldEmailTwo;
    }

    public void setOldEmailTwo(String oldEmailTwo) {
        this.oldEmailTwo = oldEmailTwo;
    }

    public String getOldEmailThree() {
        return oldEmailThree;
    }

    public void setOldEmailThree(String oldEmailThree) {
        this.oldEmailThree = oldEmailThree;
    }

    public String getMobileNoOne() {
        return mobileNoOne;
    }

    public void setMobileNoOne(String mobileNoOne) {
        this.mobileNoOne = mobileNoOne;
    }

    public String getMobileNoTwo() {
        return mobileNoTwo;
    }

    public void setMobileNoTwo(String mobileNoTwo) {
        this.mobileNoTwo = mobileNoTwo;
    }

    public String getMobileNoThree() {
        return mobileNoThree;
    }

    public void setMobileNoThree(String mobileNoThree) {
        this.mobileNoThree = mobileNoThree;
    }

    public String getOldMobileNoOne() {
        return oldMobileNoOne;
    }

    public void setOldMobileNoOne(String oldMobileNoOne) {
        this.oldMobileNoOne = oldMobileNoOne;
    }

    public String getOldMobileNoTwo() {
        return oldMobileNoTwo;
    }

    public void setOldMobileNoTwo(String oldMobileNoTwo) {
        this.oldMobileNoTwo = oldMobileNoTwo;
    }

    public String getOldMobileNoThree() {
        return oldMobileNoThree;
    }

    public void setOldMobileNoThree(String oldMobileNoThree) {
        this.oldMobileNoThree = oldMobileNoThree;
    }

    private String oldMobileNoThree;

    public String getNomineeFour() {
        return nomineeFour;
    }

    public void setNomineeFour(String nomineeFour) {
        this.nomineeFour = nomineeFour;
    }

    public String getNomineeFourOld() {
        return nomineeFourOld;
    }

    public void setNomineeFourOld(String nomineeFourOld) {
        this.nomineeFourOld = nomineeFourOld;
    }

    private String nomineeFourOld;
    private String panOne;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOldEmail() {
        return oldEmail;
    }

    public void setOldEmail(String oldEmail) {
        this.oldEmail = oldEmail;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getOldMobileNo() {
        return oldMobileNo;
    }

    public void setOldMobileNo(String oldMobileNo) {
        this.oldMobileNo = oldMobileNo;
    }

    public String getNomineeOne() {
        return nomineeOne;
    }

    public void setNomineeOne(String nomineeOne) {
        this.nomineeOne = nomineeOne;
    }

    public String getNomineeOneOld() {
        return nomineeOneOld;
    }

    public void setNomineeOneOld(String nomineeOneOld) {
        this.nomineeOneOld = nomineeOneOld;
    }

    public String getNomineeTwo() {
        return nomineeTwo;
    }

    public void setNomineeTwo(String nomineeTwo) {
        this.nomineeTwo = nomineeTwo;
    }

    public String getNomineeTwoOld() {
        return nomineeTwoOld;
    }

    public void setNomineeTwoOld(String nomineeTwoOld) {
        this.nomineeTwoOld = nomineeTwoOld;
    }

    public String getNomineeThree() {
        return nomineeThree;
    }

    public void setNomineeThree(String nomineeThree) {
        this.nomineeThree = nomineeThree;
    }

    public String getNomineeThreeOld() {
        return nomineeThreeOld;
    }

    public void setNomineeThreeOld(String nomineeThreeOld) {
        this.nomineeThreeOld = nomineeThreeOld;
    }

    public String getPanOne() {
        return panOne;
    }

    public void setPanOne(String panOne) {
        this.panOne = panOne;
    }

    public String getPanOneOld() {
        return panOneOld;
    }

    public void setPanOneOld(String panOneOld) {
        this.panOneOld = panOneOld;
    }

    public String getPanTwo() {
        return panTwo;
    }

    public void setPanTwo(String panTwo) {
        this.panTwo = panTwo;
    }

    public String getPanTwoOld() {
        return panTwoOld;
    }

    public void setPanTwoOld(String panTwoOld) {
        this.panTwoOld = panTwoOld;
    }

    public String getPanThree() {
        return panThree;
    }

    public void setPanThree(String panThree) {
        this.panThree = panThree;
    }

    public String getPanThreeOld() {
        return panThreeOld;
    }

    public void setPanThreeOld(String panThreeOld) {
        this.panThreeOld = panThreeOld;
    }

    private String panOneOld;
    private String panTwo;
    private String panTwoOld;
    private String panThree;
    private String panThreeOld;


    private String pageNo;
    private String pageSize;
    private String searchValue;
    private String sortOrder;
    private String sortField;


    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
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

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    public String getInwardRefNo() {
        return inwardRefNo;
    }

    public void setInwardRefNo(String inwardRefNo) {
        this.inwardRefNo = inwardRefNo;
    }

    public String getInwardRefDate() {
        return inwardRefDate;
    }

    public void setInwardRefDate(String inwardRefDate) {
        this.inwardRefDate = inwardRefDate;
    }

    public String getBlaNo() {
        return blaNo;
    }

    public void setBlaNo(String blaNo) {
        this.blaNo = blaNo;
    }

    public String getFirstApplicant() {
        return firstApplicant;
    }

    public void setFirstApplicant(String firstApplicant) {
        this.firstApplicant = firstApplicant;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getSecondApplicant() {
        return secondApplicant;
    }

    public void setSecondApplicant(String secondApplicant) {
        this.secondApplicant = secondApplicant;
    }

    public String getOldSecondApplicant() {
        return oldSecondApplicant;
    }

    public void setOldSecondApplicant(String oldSecondApplicant) {
        this.oldSecondApplicant = oldSecondApplicant;
    }

    public String getThirdApplicant() {
        return thirdApplicant;
    }

    public void setThirdApplicant(String thirdApplicant) {
        this.thirdApplicant = thirdApplicant;
    }

    public String getOldThirdApplicant() {
        return oldThirdApplicant;
    }

    public void setOldThirdApplicant(String oldThirdApplicant) {
        this.oldThirdApplicant = oldThirdApplicant;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOldAddress() {
        return oldAddress;
    }

    public void setOldAddress(String oldAddress) {
        this.oldAddress = oldAddress;
    }

    public String getModeOfHolding() {
        return modeOfHolding;
    }

    public void setModeOfHolding(String modeOfHolding) {
        this.modeOfHolding = modeOfHolding;
    }

    public String getOldModeOfHolding() {
        return oldModeOfHolding;
    }

    public void setOldModeOfHolding(String oldModeOfHolding) {
        this.oldModeOfHolding = oldModeOfHolding;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getOldDob() {
        return oldDob;
    }

    public void setOldDob(String oldDob) {
        this.oldDob = oldDob;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public String getOldModeOfPayment() {
        return oldModeOfPayment;
    }

    public void setOldModeOfPayment(String oldModeOfPayment) {
        this.oldModeOfPayment = oldModeOfPayment;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getOldAccountType() {
        return oldAccountType;
    }

    public void setOldAccountType(String oldAccountType) {
        this.oldAccountType = oldAccountType;
    }

    public String getOldAccountNo() {
        return oldAccountNo;
    }

    public void setOldAccountNo(String oldAccountNo) {
        this.oldAccountNo = oldAccountNo;
    }

    public String getMicr() {
        return micr;
    }

    public void setMicr(String micr) {
        this.micr = micr;
    }

    public String getOldMicr() {
        return oldMicr;
    }

    public void setOldMicr(String oldMicr) {
        this.oldMicr = oldMicr;
    }

    public String getApplStatus() {
        return applStatus;
    }

    public void setApplStatus(String applStatus) {
        this.applStatus = applStatus;
    }

    public String getOldApplStatus() {
        return oldApplStatus;
    }

    public void setOldApplStatus(String oldApplStatus) {
        this.oldApplStatus = oldApplStatus;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getOldPanNo() {
        return oldPanNo;
    }

    public void setOldPanNo(String oldPanNo) {
        this.oldPanNo = oldPanNo;
    }

    public String getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(String checkerId) {
        this.checkerId = checkerId;
    }

    public String getCheckerDate() {
        return checkerDate;
    }

    public void setCheckerDate(String checkerDate) {
        this.checkerDate = checkerDate;
    }

    public String getMakerId() {
        return makerId;
    }

    public void setMakerId(String makerId) {
        this.makerId = makerId;
    }

    public String getMakerDate() {
        return makerDate;
    }

    public void setMakerDate(String makerDate) {
        this.makerDate = makerDate;
    }

    public String getAuthorizedStatus() {
        return authorizedStatus;
    }

    public void setAuthorizedStatus(String authorizedStatus) {
        this.authorizedStatus = authorizedStatus;
    }

    public String getHdfcBranchCode() {
        return hdfcBranchCode;
    }

    public void setHdfcBranchCode(String hdfcBranchCode) {
        this.hdfcBranchCode = hdfcBranchCode;
    }

    public String getOldHdfcBranchCode() {
        return oldHdfcBranchCode;
    }

    public void setOldHdfcBranchCode(String oldHdfcBranchCode) {
        this.oldHdfcBranchCode = oldHdfcBranchCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
