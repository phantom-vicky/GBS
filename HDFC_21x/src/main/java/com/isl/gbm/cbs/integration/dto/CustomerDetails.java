package com.isl.gbm.cbs.integration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDetails {


    /**
     * The sol id.
     */
    private String solId;

    /**
     * The customer id.
     */
    private String customerId;

    /**
     * The cif id.
     */
    private String cifId;

    /**
     * The customer title code.
     */
    private String customerTitleCode;

    /**
     * The customer name.
     */
    private String customerName;

    /**
     * The customer sex.
     */
    private String customerSex;

    /**
     * The dob.
     */
    private Date dob;

    /**
     * The age.
     */
    private String age;

    /**
     * The pan number.
     */
    private String panNumber;

    /**
     * The national id card number.
     */
    private String nationalIdCardNumber;

    /**
     * The passport number.
     */
    private String passportNumber;

    /**
     * The account number.
     */
    private String accountNumber;

    /**
     * The scheme type.
     */
    private String schemeType;

    private String customerminor;
    private String fatherName;
    private String motherName;
    private String aadharNumber;



    /**
     * The clearing balance amount.
     */
    private BigDecimal clearingBalanceAmount;

    /**
     * The mobile number.
     */
    private String mobileNumber;

    /**
     * The organization key.
     */
    private String organizationKey;

    /**
     * The address line 1.
     */
    private String addressLine1;

    /**
     * The address line 2.
     */
    private String addressLine2;

    /**
     * The address line 3.
     */
    private String addressLine3;

    /**
     * The city.
     */
    private String city;

    /**
     * The state.
     */
    private String state;

    /**
     * The country.
     */
    private String country;

    /**
     * The zip code.
     */
    private String zipCode;

    /**
     * The phone number.
     */
    private String phoneNumber;

    /**
     * The cell number.
     */
    private String cellNumber;

    /**
     * The email.
     */
    private String email;

    private String bankName;

    private String branchName;

    private String micrCode;

    private String ifscCode;

    private String customerFName;

    private String customerMName;

    private String customerLName;

    public String getCustomerminor() {

        return customerminor;
    }

    public void setCustomerminor(String customerminor) {
        this.customerminor = customerminor;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getCustomerFName() {
        return customerFName;
    }

    public void setCustomerFName(String customerFName) {
        this.customerFName = customerFName;
    }

    public String getCustomerMName() {
        return customerMName;
    }

    public void setCustomerMName(String customerMName) {
        this.customerMName = customerMName;
    }

    public String getCustomerLName() {
        return customerLName;
    }

    public void setCustomerLName(String customerLName) {
        this.customerLName = customerLName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getMicrCode() {
        return micrCode;
    }

    public void setMicrCode(String micrCode) {
        this.micrCode = micrCode;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    /**
     * Gets the account number.
     *
     * @return the account number
     */
    public String getAccountNumber() {
        return this.accountNumber;
    }

    /**
     * Gets the address line 1.
     *
     * @return the address line 1
     */
    public String getAddressLine1() {
        return this.addressLine1;
    }

    /**
     * Gets the address line 2.
     *
     * @return the address line 2
     */
    public String getAddressLine2() {
        return this.addressLine2;
    }

    /**
     * Gets the address line 3.
     *
     * @return the address line 3
     */
    public String getAddressLine3() {
        return this.addressLine3;
    }

    /**
     * Gets the age.
     *
     * @return the age
     */
    public String getAge() {
        return this.age;
    }

    /**
     * Gets the cell number.
     *
     * @return the cell number
     */
    public String getCellNumber() {
        return this.cellNumber;
    }

    /**
     * Gets the cif id.
     *
     * @return the cif id
     */
    public String getCifId() {
        return this.cifId;
    }

    /**
     * Gets the city.
     *
     * @return the city
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Gets the clearing balance amount.
     *
     * @return the clearing balance amount
     */
    public BigDecimal getClearingBalanceAmount() {
        return this.clearingBalanceAmount;
    }

    /**
     * Gets the country.
     *
     * @return the country
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Gets the customer id.
     *
     * @return the customer id
     */
    public String getCustomerId() {
        return this.customerId;
    }

    /**
     * Gets the customer name.
     *
     * @return the customer name
     */
    public String getCustomerName() {
        return this.customerName;
    }

    /**
     * Gets the customer sex.
     *
     * @return the customer sex
     */
    public String getCustomerSex() {
        return this.customerSex;
    }

    /**
     * Gets the customer title code.
     *
     * @return the customer title code
     */
    public String getCustomerTitleCode() {
        return this.customerTitleCode;
    }

    /**
     * Gets the dob.
     *
     * @return the dob
     */
    public Date getDob() {
        return this.dob;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Gets the mobile number.
     *
     * @return the mobile number
     */
    public String getMobileNumber() {
        return this.mobileNumber;
    }

    /**
     * Gets the national id card number.
     *
     * @return the national id card number
     */
    public String getNationalIdCardNumber() {
        return this.nationalIdCardNumber;
    }

    /**
     * Gets the organization key.
     *
     * @return the organization key
     */
    public String getOrganizationKey() {
        return this.organizationKey;
    }

    /**
     * Gets the pan number.
     *
     * @return the pan number
     */
    public String getPanNumber() {
        return this.panNumber;
    }

    /**
     * Gets the passport number.
     *
     * @return the passport number
     */
    public String getPassportNumber() {
        return this.passportNumber;
    }

    /**
     * Gets the phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Gets the scheme type.
     *
     * @return the scheme type
     */
    public String getSchemeType() {
        return this.schemeType;
    }

    /**
     * Gets the sol id.
     *
     * @return the sol id
     */
    public String getSolId() {
        return this.solId;
    }

    /**
     * Gets the state.
     *
     * @return the state
     */
    public String getState() {
        return this.state;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
        return this.zipCode;
    }

    /**
     * Sets the account number.
     *
     * @param accountNumber the new account number
     */
    public void setAccountNumber(final String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Sets the address line 1.
     *
     * @param addressLine1 the new address line 1
     */
    public void setAddressLine1(final String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * Sets the address line 2.
     *
     * @param addressLine2 the new address line 2
     */
    public void setAddressLine2(final String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * Sets the address line 3.
     *
     * @param addressLine3 the new address line 3
     */
    public void setAddressLine3(final String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    /**
     * Sets the age.
     *
     * @param age the new age
     */
    public void setAge(final String age) {
        this.age = age;
    }

    /**
     * Sets the cell number.
     *
     * @param cellNumber the new cell number
     */
    public void setCellNumber(final String cellNumber) {
        this.cellNumber = cellNumber;
    }

    /**
     * Sets the cif id.
     *
     * @param cifId the new cif id
     */
    public void setCifId(final String cifId) {
        this.cifId = cifId;
    }

    /**
     * Sets the city.
     *
     * @param city the new city
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * Sets the clearing balance amount.
     *
     * @param clearingBalanceAmount the new clearing balance amount
     */
    public void setClearingBalanceAmount(final BigDecimal clearingBalanceAmount) {
        this.clearingBalanceAmount = clearingBalanceAmount;
    }

    /**
     * Sets the country.
     *
     * @param country the new country
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * Sets the customer id.
     *
     * @param customerId the new customer id
     */
    public void setCustomerId(final String customerId) {
        this.customerId = customerId;
    }

    /**
     * Sets the customer name.
     *
     * @param customerName the new customer name
     */
    public void setCustomerName(final String customerName) {
        this.customerName = customerName;
    }

    /**
     * Sets the customer sex.
     *
     * @param customerSex the new customer sex
     */
    public void setCustomerSex(final String customerSex) {
        this.customerSex = customerSex;
    }

    /**
     * Sets the customer title code.
     *
     * @param customerTitleCode the new customer title code
     */
    public void setCustomerTitleCode(final String customerTitleCode) {
        this.customerTitleCode = customerTitleCode;
    }

    /**
     * Sets the dob.
     *
     * @param dob the new dob
     */
    public void setDob(final Date dob) {
        this.dob = dob;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Sets the mobile number.
     *
     * @param mobileNumber the new mobile number
     */
    public void setMobileNumber(final String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * Sets the national id card number.
     *
     * @param nationalIdCardNumber the new national id card number
     */
    public void setNationalIdCardNumber(final String nationalIdCardNumber) {
        this.nationalIdCardNumber = nationalIdCardNumber;
    }

    /**
     * Sets the organization key.
     *
     * @param organizationKey the new organization key
     */
    public void setOrganizationKey(final String organizationKey) {
        this.organizationKey = organizationKey;
    }

    /**
     * Sets the pan number.
     *
     * @param panNumber the new pan number
     */
    public void setPanNumber(final String panNumber) {
        this.panNumber = panNumber;
    }

    /**
     * Sets the passport number.
     *
     * @param passportNumber the new passport number
     */
    public void setPassportNumber(final String passportNumber) {
        this.passportNumber = passportNumber;
    }

    /**
     * Sets the phone number.
     *
     * @param phoneNumber the new phone number
     */
    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Sets the scheme type.
     *
     * @param schemeType the new scheme type
     */
    public void setSchemeType(final String schemeType) {
        this.schemeType = schemeType;
    }

    /**
     * Sets the sol id.
     *
     * @param solId the new sol id
     */
    public void setSolId(final String solId) {
        this.solId = solId;
    }

    /**
     * Sets the state.
     *
     * @param state the new state
     */
    public void setState(final String state) {
        this.state = state;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(final String zipCode) {
        this.zipCode = zipCode;
    }


}
