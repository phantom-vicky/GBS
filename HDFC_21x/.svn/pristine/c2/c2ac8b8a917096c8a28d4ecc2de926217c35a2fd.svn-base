/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.model;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * The Class BankBranchMaster.
 */
@Entity
@Table(name = "GBM_BANK_BRANCHES")
@DynamicUpdate
@SelectBeforeUpdate
public class BankBranchMaster implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The id.
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BRANCHES_OF_THE_BANKS")
    @SequenceGenerator(name = "SEQ_BRANCHES_OF_THE_BANKS", sequenceName = "SEQ_BRANCHES_OF_THE_BANKS", allocationSize = 1)
    private int id;

    /**
     * The sol id.
     */
    @Column(name = "SOL_ID")
    private String solId;

    /**
     * The sol desc.
     */
    @Column(name = "SOL_DESC")
    private String solDesc;

    /**
     * The bank code.
     */
    @Column(name = "BANK_CODE")
    private String bankCode;

    /**
     * The branch code.
     */
    @Column(name = "BR_CODE")
    private String branchCode;

    /**
     * The micr certre code.
     */
    @Column(name = "MICR_CENTRE_CODE")
    private String micrCertreCode;

    /**
     * The micr bank code.
     */
    @Column(name = "MICR_BANK_CODE")
    private String micrBankCode;

    /**
     * The micr branch code.
     */
    @Column(name = "MICR_BRANCH_CODE")
    private String micrBranchCode;

    /**
     * The addr 1.
     */
    @Column(name = "ADDR_1")
    private String addr1;

    /**
     * The addr 2.
     */
    @Column(name = "ADDR_2")
    private String addr2;

    /**
     * The city code.
     */
    @Column(name = "CITY_CODE")
    private String cityCode;

    /**
     * The state code.
     */
    @Column(name = "STATE_CODE")
    private String stateCode;

    /**
     * The pin code.
     */
    @Column(name = "PIN_CODE")
    private String pinCode;

    /**
     * The pan gir number.
     */
    @Column(name = "PAN_GIR_NO")
    private String panGirNumber;

    /**
     * The tax dedn details.
     */
    @Column(name = "TAX_DEDN_DETAILS")
    private String taxDednDetails;

    /**
     * The tds circle name.
     */
    @Column(name = "TDS_CIRCLE_NAME")
    private String tdsCircleName;

    /**
     * The division name.
     */
    @Column(name = "DIVISION_NAME")
    private String divisionName;

    /**
     * The circl office name.
     */
    @Column(name = "CIRCLE_OFFICE_NAME")
    private String circlOfficeName;

    /**
     * The uniform br code.
     */
    @Column(name = "UNIFORM_BR_CODE")
    private String uniformBrCode;

    /**
     * The delete flag.
     */
    @Column(name = "DEL_FLG")
    private boolean deleteFlag;

    /**
     * The verified flag.
     */
    @Column(name = "VER_FLG")
    private boolean verifiedFlag;

    /**
     * The file name.
     */
    @Column(name = "FILENAME")
    private String fileName;

    /**
     * The created by.
     */
    @Column(name = "CREATED_BY", updatable = false)
    private String createdBy;

    /**
     * The created date.
     */
    @Column(name = "CREATED_DATE", updatable = false)
    private Date createdDate;

    /**
     * The last modified by.
     */
    @Column(name = "LAST_MOD_BY")
    private String lastModifiedBy;

    /**
     * The last modified date.
     */
    @Column(name = "LAST_MOD_DATE")
    private Date lastModifiedDate;

    /**
     * The verified by.
     */
    @Column(name = "VERIFIED_BY")
    private String verifiedBy;

    /**
     * The verified date.
     */
    @Column(name = "VERIFIED_DATE")
    private Date verifiedDate;

    /**
     * The additional text 1.
     */
    @Column(name = "ADDL_TEXT1")
    private String additionalText1;

    /**
     * The additional text 2.
     */
    @Column(name = "ADDL_TEXT2")
    private String additionalText2;

    /**
     * The additional text 3.
     */
    @Column(name = "ADDL_TEXT3")
    private String additionalText3;

  	/** The additional text 3. */
  	@Column(name = "BRANCH_LOCATION")
	private String branchLocation;
    /**
     * The status.
     */
    @Column(name = "ZONE_CODE")
    private String zoneCode;

    @Column(name = "ZONE_NAME")
    private String zoneName;
    @Column(name = "PDO_CODE")
    private String pdoCode;

    @Column(name = "EMAIL_ID")
    private String emailID;

    @Column(name = "BANK_IFSC_CODE")
    private String ifscCode;

    public boolean isVerifiedFlag() {
        return verifiedFlag;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getPdoCode() {
        return pdoCode;
    }

    public void setPdoCode(String pdoCode) {
        this.pdoCode = pdoCode;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Column(name = "SHORT_NAME")
    private String shortName;
    @Column(name = "status")
    private int status;

    /**
     * The auditable.
     */
    @Column(name = "auditable")
    private Boolean auditable = Boolean.FALSE;
    /**
 	 * Gets the verified by.
 	 *
 	 * @return the verified by
 	 */
	public String getBranchLocation() {
        		return branchLocation;
        	}

        	/**
 	 * Sets the branchLocation.
 	 *
 	 * @param branchLocation
 	 *            the new branchLocation
 	 */

    public void setBranchLocation(String branchLocation) {
        this.branchLocation = branchLocation;
    }

    /**
     * Gets the additional text 1.
     *
     * @return the additional text 1
     */
    public String getAdditionalText1() {
        return this.additionalText1;
    }

    /**
     * Gets the additional text 2.
     *
     * @return the additional text 2
     */
    public String getAdditionalText2() {
        return this.additionalText2;
    }

    /**
     * Gets the additional text 3.
     *
     * @return the additional text 3
     */
    public String getAdditionalText3() {
        return this.additionalText3;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    /**
     * Sets the branchLocation.
     *
     * @param ifscCode
     *            the new branchLocation
     */

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }


    /**
     * Gets the addr 1.
     *
     * @return the addr 1
     */
    public String getAddr1() {
        return this.addr1;
    }

    /**
     * Gets the addr 2.
     *
     * @return the addr 2
     */
    public String getAddr2() {
        return this.addr2;
    }

    /**
     * Gets the auditable.
     *
     * @return the auditable
     */
    public Boolean getAuditable() {
        return this.auditable;
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
     * Gets the created by.
     *
     * @return the created by
     */
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Gets the created date.
     *
     * @return the created date
     */
    public Date getCreatedDate() {
        return this.createdDate;
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
     * Gets the file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets the last modified by.
     *
     * @return the last modified by
     */
    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    /**
     * Gets the last modified date.
     *
     * @return the last modified date
     */
    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
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
     * Gets the pan gir number.
     *
     * @return the pan gir number
     */
    public String getPanGirNumber() {
        return this.panGirNumber;
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
     * Gets the sol desc.
     *
     * @return the sol desc
     */
    public String getSolDesc() {
        return this.solDesc;
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
     * Gets the status.
     *
     * @return the status
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * Gets the tax dedn details.
     *
     * @return the tax dedn details
     */
    public String getTaxDednDetails() {
        return this.taxDednDetails;
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
     * Gets the verified by.
     *
     * @return the verified by
     */
    public String getVerifiedBy() {
        return this.verifiedBy;
    }

    /**
     * Gets the verified date.
     *
     * @return the verified date
     */
    public Date getVerifiedDate() {
        return this.verifiedDate;
    }

    /**
     * Gets the verified flag.
     *
     * @return the verified flag
     */
    public boolean getVerifiedFlag() {
        return this.verifiedFlag;
    }

    /**
     * Checks if is delete flag.
     *
     * @return true, if is delete flag
     */
    public boolean isDeleteFlag() {
        return this.deleteFlag;
    }

    /**
     * Sets the additional text 1.
     *
     * @param additionalText1 the new additional text 1
     */
    public void setAdditionalText1(final String additionalText1) {
        this.additionalText1 = additionalText1;
    }

    /**
     * Sets the additional text 2.
     *
     * @param additionalText2 the new additional text 2
     */
    public void setAdditionalText2(final String additionalText2) {
        this.additionalText2 = additionalText2;
    }

    /**
     * Sets the additional text 3.
     *
     * @param additionalText3 the new additional text 3
     */
    public void setAdditionalText3(final String additionalText3) {
        this.additionalText3 = additionalText3;
    }

    /**
     * Sets the addr 1.
     *
     * @param addr1 the new addr 1
     */
    public void setAddr1(final String addr1) {
        this.addr1 = addr1;
    }

    /**
     * Sets the addr 2.
     *
     * @param addr2 the new addr 2
     */
    public void setAddr2(final String addr2) {
        this.addr2 = addr2;
    }

    /**
     * Sets the auditable.
     *
     * @param auditable the new auditable
     */
    public void setAuditable(final Boolean auditable) {
        this.auditable = auditable;
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
     * Sets the created by.
     *
     * @param createdBy the new created by
     */
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Sets the created date.
     *
     * @param createdDate the new created date
     */
    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Sets the delete flag.
     *
     * @param deleteFlag the new delete flag
     */
    public void setDeleteFlag(final boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
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
     * Sets the file name.
     *
     * @param fileName the new file name
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Sets the last modified by.
     *
     * @param lastModifiedBy the new last modified by
     */
    public void setLastModifiedBy(final String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * Sets the last modified date.
     *
     * @param lastModifiedDate the new last modified date
     */
    public void setLastModifiedDate(final Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
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
     * Sets the pan gir number.
     *
     * @param panGirNumber the new pan gir number
     */
    public void setPanGirNumber(final String panGirNumber) {
        this.panGirNumber = panGirNumber;
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
     * Sets the sol desc.
     *
     * @param solDesc the new sol desc
     */
    public void setSolDesc(final String solDesc) {
        this.solDesc = solDesc;
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
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(final int status) {
        this.status = status;
    }

    /**
     * Sets the tax dedn details.
     *
     * @param taxDednDetails the new tax dedn details
     */
    public void setTaxDednDetails(final String taxDednDetails) {
        this.taxDednDetails = taxDednDetails;
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

    /**
     * Sets the verified by.
     *
     * @param verifiedBy the new verified by
     */
    public void setVerifiedBy(final String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    /**
     * Sets the verified date.
     *
     * @param verifiedDate the new verified date
     */
    public void setVerifiedDate(final Date verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    /**
     * Sets the verified flag.
     *
     * @param verifiedFlag the new verified flag
     */
    public void setVerifiedFlag(final boolean verifiedFlag) {
        this.verifiedFlag = verifiedFlag;
    }

}
