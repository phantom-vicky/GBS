package com.isl.gbm.cbs.integration.dto;

/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class SolDetailsIntegrationDto.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SolDetailsIntegrationDto {

    /**
     * The sol id.
     */
    private String solId;

    /**
     * The sol description.
     */
    private String solDescription;

    /**
     * The bank code.
     */
    private String bankCode;

    /**
     * The branch code.
     */
    private String branchCode;

    /**
     * The micr certre code.
     */
    private String micrCertreCode;

    /**
     * The micr bank code.
     */
    private String micrBankCode;

    /**
     * The micr branch code.
     */
    private String micrBranchCode;

    /**
     * The address 1.
     */
    private String address1;

    /**
     * The address 2.
     */
    private String address2;

    /**
     * The city code.
     */
    private String cityCode;

    /**
     * The state code.
     */
    private String stateCode;

    /**
     * The pin code.
     */
    private String pinCode;

    /**
     * The pan number.
     */
    private String panNumber;

    /**
     * The tan number.
     */
    private String tanNumber;

    /**
     * The tds circle name.
     */
    private String tdsCircleName;

    /**
     * The division name.
     */
    private String divisionName;

    /**
     * The circl office name.
     */
    private String circlOfficeName;

    /**
     * The uniform br code.
     */
    private String uniformBrCode;

    /**
     * Gets the address 1.
     *
     * @return the address 1
     */
    public String getAddress1() {
        return this.address1;
    }

    /**
     * Gets the address 2.
     *
     * @return the address 2
     */
    public String getAddress2() {
        return this.address2;
    }

    /**
     * Gets the bank code.
     *
     * @return the bank code
     */
    public String getBankCode() {
        return this.bankCode;
    }

    /**
     * Gets the branch code.
     *
     * @return the branch code
     */
    public String getBranchCode() {
        return this.branchCode;
    }

    /**
     * Gets the circl office name.
     *
     * @return the circl office name
     */
    public String getCirclOfficeName() {
        return this.circlOfficeName;
    }

    /**
     * Gets the city code.
     *
     * @return the city code
     */
    public String getCityCode() {
        return this.cityCode;
    }

    /**
     * Gets the division name.
     *
     * @return the division name
     */
    public String getDivisionName() {
        return this.divisionName;
    }

    /**
     * Gets the micr bank code.
     *
     * @return the micr bank code
     */
    public String getMicrBankCode() {
        return this.micrBankCode;
    }

    /**
     * Gets the micr branch code.
     *
     * @return the micr branch code
     */
    public String getMicrBranchCode() {
        return this.micrBranchCode;
    }

    /**
     * Gets the micr certre code.
     *
     * @return the micr certre code
     */
    public String getMicrCertreCode() {
        return this.micrCertreCode;
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
     * Gets the pin code.
     *
     * @return the pin code
     */
    public String getPinCode() {
        return this.pinCode;
    }

    /**
     * Gets the sol description.
     *
     * @return the sol description
     */
    public String getSolDescription() {
        return this.solDescription;
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
     * Gets the state code.
     *
     * @return the state code
     */
    public String getStateCode() {
        return this.stateCode;
    }

    /**
     * Gets the tan number.
     *
     * @return the tan number
     */
    public String getTanNumber() {
        return this.tanNumber;
    }

    /**
     * Gets the tds circle name.
     *
     * @return the tds circle name
     */
    public String getTdsCircleName() {
        return this.tdsCircleName;
    }

    /**
     * Gets the uniform br code.
     *
     * @return the uniform br code
     */
    public String getUniformBrCode() {
        return this.uniformBrCode;
    }

    /**
     * Sets the address 1.
     *
     * @param address1 the new address 1
     */
    public void setAddress1(final String address1) {
        this.address1 = address1;
    }

    /**
     * Sets the address 2.
     *
     * @param address2 the new address 2
     */
    public void setAddress2(final String address2) {
        this.address2 = address2;
    }

    /**
     * Sets the bank code.
     *
     * @param bankCode the new bank code
     */
    public void setBankCode(final String bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * Sets the branch code.
     *
     * @param branchCode the new branch code
     */
    public void setBranchCode(final String branchCode) {
        this.branchCode = branchCode;
    }

    /**
     * Sets the circl office name.
     *
     * @param circlOfficeName the new circl office name
     */
    public void setCirclOfficeName(final String circlOfficeName) {
        this.circlOfficeName = circlOfficeName;
    }

    /**
     * Sets the city code.
     *
     * @param cityCode the new city code
     */
    public void setCityCode(final String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * Sets the division name.
     *
     * @param divisionName the new division name
     */
    public void setDivisionName(final String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * Sets the micr bank code.
     *
     * @param micrBankCode the new micr bank code
     */
    public void setMicrBankCode(final String micrBankCode) {
        this.micrBankCode = micrBankCode;
    }

    /**
     * Sets the micr branch code.
     *
     * @param micrBranchCode the new micr branch code
     */
    public void setMicrBranchCode(final String micrBranchCode) {
        this.micrBranchCode = micrBranchCode;
    }

    /**
     * Sets the micr certre code.
     *
     * @param micrCertreCode the new micr certre code
     */
    public void setMicrCertreCode(final String micrCertreCode) {
        this.micrCertreCode = micrCertreCode;
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
     * Sets the pin code.
     *
     * @param pinCode the new pin code
     */
    public void setPinCode(final String pinCode) {
        this.pinCode = pinCode;
    }

    /**
     * Sets the sol description.
     *
     * @param solDescription the new sol description
     */
    public void setSolDescription(final String solDescription) {
        this.solDescription = solDescription;
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
     * Sets the state code.
     *
     * @param stateCode the new state code
     */
    public void setStateCode(final String stateCode) {
        this.stateCode = stateCode;
    }

    /**
     * Sets the tan number.
     *
     * @param tanNumber the new tan number
     */
    public void setTanNumber(final String tanNumber) {
        this.tanNumber = tanNumber;
    }

    /**
     * Sets the tds circle name.
     *
     * @param tdsCircleName the new tds circle name
     */
    public void setTdsCircleName(final String tdsCircleName) {
        this.tdsCircleName = tdsCircleName;
    }

    /**
     * Sets the uniform br code.
     *
     * @param uniformBrCode the new uniform br code
     */
    public void setUniformBrCode(final String uniformBrCode) {
        this.uniformBrCode = uniformBrCode;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SolDetailsIntegrationDto [solId=" + this.solId + ", solDescription=" + this.solDescription
                + ", bankCode=" + this.bankCode + ", branchCode=" + this.branchCode + ", micrCertreCode="
                + this.micrCertreCode + ", micrBankCode=" + this.micrBankCode + ", micrBranchCode="
                + this.micrBranchCode + ", address1=" + this.address1 + ", address2=" + this.address2 + ", cityCode="
                + this.cityCode + ", stateCode=" + this.stateCode + ", pinCode=" + this.pinCode + ", panNumber="
                + this.panNumber + ", tanNumber=" + this.tanNumber + ", tdsCircleName=" + this.tdsCircleName
                + ", divisionName=" + this.divisionName + ", circlOfficeName=" + this.circlOfficeName
                + ", uniformBrCode=" + this.uniformBrCode + "]";
    }

}
