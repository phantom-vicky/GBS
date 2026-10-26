package com.isl.gbm.rbibond.dto.scrolls;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnnualReportDto {

    private String flag;
    private String reportSerialNo;

    private String orginalSerialNo;
    private String personName;
    private String personType;
    private String customerIdentity;
    private String gender;
    private String fatherName;
    private String pan;
    private String aadharNo;
    private String form60Acknowledgment;
    private String identificationType;
    private String identificationNo;
    private String dateOfBirth;
    private String nationality;
    private String addressType;
    private String fixedAddress;
    private String city;
    private String postCode;
    private String state;
    private String countryCode;
    private String business;
    private String primaryStdCode;
    private String primaryPhoneNo;
    private String primaryMobileNo;
    private String secondaryStdCode;
    private String secondaryPhoneNo;
    private String secondaryMobileNo;
    private String email;
    private String remarks;
    private String productType;
    private String aggregateGrossAmountRecieved;
    private String AggregateGrossAmountCash;
    private String AggregateGrossAmountPaid;
    private String transactionSummaryRemarks;
    private String productIdentifier;

    private String relatedInstituteRefNo;
    private String financialTransactionDtlsRemarks;
    private String schemeName;
    private String schemeCode;
    private String fromDate;
    private String toDate;
    private String financialYear;
    private String lastDateOfTransaction;
    private String amountPaidToPerson;
    private String relatedAccountNumber;
    private String relatedInstituteName;
    private String relatedRefNo;
    private String schemeStatus;

    public String getRelatedRefNo() {
        return relatedRefNo;
    }

    public void setRelatedRefNo(String relatedRefNo) {
        this.relatedRefNo = relatedRefNo;
    }

    public String getAmountRecieved() {
        return amountRecieved;
    }

    public void setAmountRecieved(String amountRecieved) {
        this.amountRecieved = amountRecieved;
    }

    public String getCashAmountRecieved() {
        return cashAmountRecieved;
    }

    public void setCashAmountRecieved(String cashAmountRecieved) {
        this.cashAmountRecieved = cashAmountRecieved;
    }

    private String amountRecieved;
    private String cashAmountRecieved;

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



    public String getReportSerialNo() {

        return reportSerialNo;
    }

    public void setReportSerialNo(String reportSerialNo) {
        this.reportSerialNo = reportSerialNo;
    }

    public String getOrginalSerialNo() {
        return orginalSerialNo;
    }

    public void setOrginalSerialNo(String orginalSerialNo) {
        this.orginalSerialNo = orginalSerialNo;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getCustomerIdentity() {
        return customerIdentity;
    }

    public void setCustomerIdentity(String customerIdentity) {
        this.customerIdentity = customerIdentity;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public String getForm60Acknowledgment() {
        return form60Acknowledgment;
    }

    public void setForm60Acknowledgment(String form60Acknowledgment) {
        this.form60Acknowledgment = form60Acknowledgment;
    }

    public String getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

    public String getIdentificationNo() {
        return identificationNo;
    }

    public void setIdentificationNo(String identificationNo) {
        this.identificationNo = identificationNo;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getFixedAddress() {
        return fixedAddress;
    }

    public void setFixedAddress(String fixedAddress) {
        this.fixedAddress = fixedAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getPrimaryStdCode() {
        return primaryStdCode;
    }

    public void setPrimaryStdCode(String primaryStdCode) {
        this.primaryStdCode = primaryStdCode;
    }

    public String getPrimaryPhoneNo() {
        return primaryPhoneNo;
    }

    public void setPrimaryPhoneNo(String primaryPhoneNo) {
        this.primaryPhoneNo = primaryPhoneNo;
    }

    public String getPrimaryMobileNo() {
        return primaryMobileNo;
    }

    public void setPrimaryMobileNo(String primaryMobileNo) {
        this.primaryMobileNo = primaryMobileNo;
    }

    public String getSecondaryStdCode() {
        return secondaryStdCode;
    }

    public void setSecondaryStdCode(String secondaryStdCode) {
        this.secondaryStdCode = secondaryStdCode;
    }

    public String getSecondaryPhoneNo() {
        return secondaryPhoneNo;
    }

    public void setSecondaryPhoneNo(String secondaryPhoneNo) {
        this.secondaryPhoneNo = secondaryPhoneNo;
    }

    public String getSecondaryMobileNo() {
        return secondaryMobileNo;
    }

    public void setSecondaryMobileNo(String secondaryMobileNo) {
        this.secondaryMobileNo = secondaryMobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getAggregateGrossAmountRecieved() {
        return aggregateGrossAmountRecieved;
    }

    public void setAggregateGrossAmountRecieved(String aggregateGrossAmountRecieved) {
        this.aggregateGrossAmountRecieved = aggregateGrossAmountRecieved;
    }

    public String getAggregateGrossAmountCash() {
        return AggregateGrossAmountCash;
    }

    public void setAggregateGrossAmountCash(String aggregateGrossAmountCash) {
        AggregateGrossAmountCash = aggregateGrossAmountCash;
    }

    public String getAggregateGrossAmountPaid() {
        return AggregateGrossAmountPaid;
    }

    public void setAggregateGrossAmountPaid(String aggregateGrossAmountPaid) {
        AggregateGrossAmountPaid = aggregateGrossAmountPaid;
    }

    public String getTransactionSummaryRemarks() {
        return transactionSummaryRemarks;
    }

    public void setTransactionSummaryRemarks(String transactionSummaryRemarks) {
        this.transactionSummaryRemarks = transactionSummaryRemarks;
    }

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public void setProductIdentifier(String productIdentifier) {
        this.productIdentifier = productIdentifier;
    }

    public String getLastDateOfTransaction() {
        return lastDateOfTransaction;
    }

    public void setLastDateOfTransaction(String lastDateOfTransaction) {
        this.lastDateOfTransaction = lastDateOfTransaction;
    }

    public String getAmountPaidToPerson() {
        return amountPaidToPerson;
    }

    public void setAmountPaidToPerson(String amountPaidToPerson) {
        this.amountPaidToPerson = amountPaidToPerson;
    }

    public String getRelatedAccountNumber() {
        return relatedAccountNumber;
    }

    public void setRelatedAccountNumber(String relatedAccountNumber) {
        this.relatedAccountNumber = relatedAccountNumber;
    }

    public String getRelatedInstituteName() {
        return relatedInstituteName;
    }

    public void setRelatedInstituteName(String relatedInstituteName) {
        this.relatedInstituteName = relatedInstituteName;
    }

    public String getRelatedInstituteRefNo() {
        return relatedInstituteRefNo;
    }

    public void setRelatedInstituteRefNo(String relatedInstituteRefNo) {
        this.relatedInstituteRefNo = relatedInstituteRefNo;
    }

    public String getFinancialTransactionDtlsRemarks() {
        return financialTransactionDtlsRemarks;
    }

    public void setFinancialTransactionDtlsRemarks(String financialTransactionDtlsRemarks) {
        this.financialTransactionDtlsRemarks = financialTransactionDtlsRemarks;
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

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }







}
