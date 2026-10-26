/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.isl.gbm.common.util.CommonConstants;
import org.hibernate.validator.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The Class BranchesoftheBankDto.
 *
 * @author harishbabu.t
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankBranchDto {

    /**
     * The Id.
     */
    private int id;

    /**
     * The sol id.
     */
    @NotBlank(message = "Sol Id cannot be blank")
    @Size(max = 15, message = "Max length of sol id should be 15")
    private String solId;

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    private String id1;

    /**
     * The sol desc.
     */
    @NotBlank(message = "Sol description cannot be blank")
    @Size(max = 130, message = "Max length of sol description should be 130")
    private String solDesc;

    /**
     * The bank code.
     */
//    @NotBlank(message = "Bank code cannot be blank")
//    @Size(max = 6, message = "Max length of bank code should be 6")
    private String bankCode;

    /**
     * The branch code.
     */
//    @NotBlank(message = "Branch code cannot be blank")
//    @Size(max = 6, message = "Max length of branch code should be 6")
    private String branchCode;

    /**
     * The micr certre code.
     */
//    @NotBlank(message = "Micr code cannot be blank")
//    @Size(max = 3, message = "Max length of micr centre code should be 3")
    private String micrCertreCode;

    /**
     * The micr bank code.
     */
//    @NotBlank(message = "Micr bank code cannot be blank")
//    @Size(max = 3, message = "Max length of micr bank code should be 3")
    private String micrBankCode;


    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    /**
     * The micr branch code.
     */
//    @NotBlank(message = "Micr branch code cannot be blank")
//    @Size(max = 3, message = "Max length of micr branch code should be 3")
    private String micrBranchCode;

    /**
     * The addr 1.
     */
    @Size(max = 45, message = "Max length of adress1 should be 45")
    private String addr1;

    /**
     * The addr 2.
     */
    @Size(max = 45, message = "Max length of adress2 should be 45")
    private String addr2;

    /**
     * The city code.
     */
//    @NotBlank(message = "City code cannot be blank")
    @Size(max = 6, message = "Max length of city code should be 6")
    private String cityCode;

    /**
     * The state code.
     */
//    @NotBlank(message = "State code cannot be blank")
    @Size(max = 3, message = "Max length of state code should be 3")
    private String stateCode;

    /**
     * The pin code.
     */
    @NotBlank(message = "Pin code cannot be blank")
    @Size(max = 6, message = "Max length of pin code should be 6")
    private String pinCode;

    /**
     * The pan gir number.
     */
//    @NotBlank(message = "PAN number cannot be blank")
    @Pattern(regexp = CommonConstants.PAN_PATTERN, message = "Invalid PAN number")
    private String panGirNumber;

    /**
     * The tax dedn details.
     */
//    @NotBlank(message = "TAN number cannot be blank")
    @Pattern(regexp = CommonConstants.TAN_PATTERN, message = "Invalid TAN number")
    private String taxDednDetails;

    /**
     * The tds circle name.
     */
//    @NotBlank(message = "TDS circle name cannot be blank")
    @Size(max = 50, message = "Max length of tds circle name should be 50")
    private String tdsCircleName;

    /**
     * The division name.
     */
//    @NotBlank(message = "Division name cannot be blank")
    @Size(max = 50, message = "Max length of division name should be 50")
    private String divisionName;

    /**
     * The circl office name.
     */
//    @NotBlank(message = "Circle office name cannot be blank")
    @Size(max = 50, message = "Max length of circle office name should be 50")
    private String circlOfficeName;

    /**
     * The uniform br code.
     */
//    @NotBlank(message = "Uniform BR code cannot be blank")
    @Size(max = 15, message = "Max length of uniform br code should be 15")
    private String uniformBrCode;


//   	@NotBlank(message = "Branch Location cannot be blank")
	@Size(max = 120, message = "Max length of uniform br code should be 120")
	private String branchLocation;
    /**
     * The delete flag.
     */
    private boolean deleteFlag;

    /**
     * The verified flag.
     */
    private boolean verifiedFlag;

    /**
     * The file name.
     */
    @Size(max = 100, message = "File name should not exceed 100 characters")
    private String fileName;

    @Pattern(regexp = CommonConstants.IFSC_PATTERN, message = "Invalid IFSC Code")
    private String ifscCode;
    /**
     * The created by.
     */
    private String createdBy;

    /**
     * The created date.
     */
    private Date createdDate;

    private String remarks;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    private String createdDat;

    /**
     * The last modified by.
     */
    private String lastModifiedBy;

    /**
     * The last modified date.
     */
    private Date lastModifiedDate;

    /**
     * The verified by.
     */
    private String verifiedBy;

    private String typeFlag;




    public String getTypeFlag() {
        return typeFlag;
    }

    public void setTypeFlag(String typeFlag) {
        this.typeFlag = typeFlag;
    }

    /**
     * The verified date.
     */
    private Date verifiedDate;

    /**
     * The additional text 1.
     */
    @Size(max = 40, message = "Max length of additional text1 should be 40")
    private String additionalText1;

    /**
     * The additional text 2.
     */
    @Size(max = 40, message = "Max length of additional text2 should be 40")
    private String additionalText2;

    /**
     * The additional text 3.
     */
    @Size(max = 40, message = "Max length of additional text3 should be 40")
    private String additionalText3;

    /**
     * The status.
     */
    @Min(value = 1, message = "Invalid branch status")
    @Max(value = 3, message = "Invalid branch status")
    private int branchStatus = 1;

    private String Status;

    private String zoneCode;

    private String zoneName;
    @NotBlank(message = "PDO Code cannot be blank")
    private String pdoCode;
    @NotBlank(message = "Short Name cannot be blank")
    private String shortName;
    //@NotBlank(message = "Branch Email ID cannot be blank")
    @Email(message = "Email should be valid")
    private String branchEmailId;

    public int getBranchStatus() {
        return branchStatus;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    /**
     * The branch status description.
     */
    private String branchStatusDescription;

    /**
     * Gets the additional text 1.
     *
     * @return the additionalText1
     */
    public String getAdditionalText1() {
        return this.additionalText1;
    }

    /**
     * Gets the additional text 2.
     *
     * @return the additionalText2
     */
    public String getAdditionalText2() {
        return this.additionalText2;
    }

    /**
     * Gets the additional text 3.
     *
     * @return the additionalText3
     */
    public String getAdditionalText3() {
        return this.additionalText3;
    }

    /**
     * Gets the addr 1.
     *
     * @return the addr1
     */
    public String getAddr1() {
        return this.addr1;
    }

    /**
     * Gets the addr 2.
     *
     * @return the addr2
     */
    public String getAddr2() {
        return this.addr2;
    }

    /**
     * Gets the bank code.
     *
     * @return the bankCode
     */
    public String getBankCode() {
        return this.bankCode;
    }

    /**
     * Gets the branch code.
     *
     * @return the branchCode
     */
    public String getBranchCode() {
        return this.branchCode;
    }

    /**
     * @return the branchStatus
     */


    /**
     * @return the branchStatusDescription
     */
    public String getBranchStatusDescription() {
        return this.branchStatusDescription;
    }

    /**
     * Gets the circl office name.
     *
     * @return the circlOfficeName
     */
    public String getCirclOfficeName() {
        return this.circlOfficeName;
    }

    /**
     * Gets the city code.
     *
     * @return the cityCode
     */
    public String getCityCode() {
        return this.cityCode;
    }

    /**
     * Gets the created by.
     *
     * @return the createdBy
     */
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Gets the created date.
     *
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return this.createdDate;
    }

    /**
     * Gets the division name.
     *
     * @return the divisionName
     */
    public String getDivisionName() {
        return this.divisionName;
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getBranchEmailId() {
        return branchEmailId;
    }

    public void setBranchEmailId(String branchEmailId) {
        this.branchEmailId = branchEmailId;
    }

    /**
     * Gets the file name.
     *
     * @return the fileName
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
     * @return the lastModifiedBy
     */
    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    /**
     * Gets the last modified date.
     *
     * @return the lastModifiedDate
     */
    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    /**
     * Gets the micr bank code.
     *
     * @return the micrBankCode
     */
    public String getMicrBankCode() {
        return this.micrBankCode;
    }

    /**
     * Gets the micr branch code.
     *
     * @return the micrBranchCode
     */
    public String getMicrBranchCode() {
        return this.micrBranchCode;
    }

    /**
     * Gets the micr certre code.
     *
     * @return the micrCertreCode
     */
    public String getMicrCertreCode() {
        return this.micrCertreCode;
    }

    /**
     * Gets the pan gir number.
     *
     * @return the panGirNumber
     */
    public String getPanGirNumber() {
        return this.panGirNumber;
    }

    /**
     * Gets the pin code.
     *
     * @return the pinCode
     */
    public String getPinCode() {
        return this.pinCode;
    }

    /**
     * Gets the sol desc.
     *
     * @return the solDesc
     */
    public String getSolDesc() {
        return this.solDesc;
    }

    /**
     * Gets the sol id.
     *
     * @return the solId
     */
    public String getSolId() {
        return this.solId;
    }

    /**
     * Gets the state code.
     *
     * @return the stateCode
     */
    public String getStateCode() {
        return this.stateCode;
    }

    /**
     * Gets the tax dedn details.
     *
     * @return the taxDednDetails
     */
    public String getTaxDednDetails() {
        return this.taxDednDetails;
    }

    /**
     * Gets the tds circle name.
     *
     * @return the tdsCircleName
     */
    public String getTdsCircleName() {
        return this.tdsCircleName;
    }

    /**
     * Gets the uniform br code.
     *
     * @return the uniformBrCode
     */
    public String getUniformBrCode() {
        return this.uniformBrCode;
    }

    /**
     * Gets the verified by.
     *
     * @return the verifiedBy
     */
    public String getVerifiedBy() {
        return this.verifiedBy;
    }

    /**
     * Gets the verified date.
     *
     * @return the verifiedDate
     */
    public Date getVerifiedDate() {
        return this.verifiedDate;
    }

    /**
     * Checks if is delete flag.
     *
     * @return the deleteFlag
     */
    public boolean isDeleteFlag() {
        return this.deleteFlag;
    }

    /**
     * Checks if is verified flag.
     *
     * @return the verifiedFlag
     */
    public boolean isVerifiedFlag() {
        return this.verifiedFlag;
    }

    /**
     * Sets the additional text 1.
     *
     * @param additionalText1 the additionalText1 to set
     */
    public void setAdditionalText1(final String additionalText1) {
        this.additionalText1 = additionalText1;
    }

    /**
     * Sets the additional text 2.
     *
     * @param additionalText2 the additionalText2 to set
     */
    public void setAdditionalText2(final String additionalText2) {
        this.additionalText2 = additionalText2;
    }

    /**
     * Sets the additional text 3.
     *
     * @param additionalText3 the additionalText3 to set
     */
    public void setAdditionalText3(final String additionalText3) {
        this.additionalText3 = additionalText3;
    }

    /**
     * Sets the addr 1.
     *
     * @param addr1 the addr1 to set
     */
    public void setAddr1(final String addr1) {
        this.addr1 = addr1;
    }

    /**
     * Sets the addr 2.
     *
     * @param addr2 the addr2 to set
     */
    public void setAddr2(final String addr2) {
        this.addr2 = addr2;
    }

    /**
     * Sets the bank code.
     *
     * @param bankCode the bankCode to set
     */
    public void setBankCode(final String bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * Sets the branch code.
     *
     * @param branchCode the branchCode to set
     */
    public void setBranchCode(final String branchCode) {
        this.branchCode = branchCode;
    }

    /**
     * @param branchStatus the branchStatus to set
     */
    public void setBranchStatus(final int branchStatus) {
        this.branchStatus = branchStatus;
    }

    /**
     * @param branchStatusDescription the branchStatusDescription to set
     */
    public void setBranchStatusDescription(final String branchStatusDescription) {
        this.branchStatusDescription = branchStatusDescription;
    }

    /**
     * Sets the circl office name.
     *
     * @param circlOfficeName the circlOfficeName to set
     */
    public void setCirclOfficeName(final String circlOfficeName) {
        this.circlOfficeName = circlOfficeName;
    }

    private String verifiedStatus;
    private String prevOperationType;

    public String getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setVerifiedStatus(String verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    public String getPrevOperationType() {
        return prevOperationType;
    }

    public void setPrevOperationType(String prevOperationType) {
        this.prevOperationType = prevOperationType;
    }

    public String getCreatedDate1() {
        return createdDate1;
    }

    public void setCreatedDate1(String createdDate1) {
        this.createdDate1 = createdDate1;
    }

    public String getVerifiedDate1() {
        return verifiedDate1;
    }

    public void setVerifiedDate1(String verifiedDate1) {
        this.verifiedDate1 = verifiedDate1;
    }

    public String getModifiedDate1() {
        return modifiedDate1;
    }

    public void setModifiedDate1(String modifiedDate1) {
        this.modifiedDate1 = modifiedDate1;
    }

    public String getPkColVal() {
        return pkColVal;
    }

    public void setPkColVal(String pkColVal) {
        this.pkColVal = pkColVal;
    }

    public List<Map<String, Object>> getOldNewValueList() {
        return oldNewValueList;
    }

    public void setOldNewValueList(List<Map<String, Object>> oldNewValueList) {
        this.oldNewValueList = oldNewValueList;
    }

    private  String createdDate1;
    private  String verifiedDate1;
    private  String modifiedDate1;
    private  String pkColVal;
    private List<Map<String, Object>> oldNewValueList;

    /**
     * Sets the city code.
     *
     * @param cityCode the cityCode to set
     */
    public void setCityCode(final String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * Sets the created by.
     *
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Sets the created date.
     *
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Sets the delete flag.
     *
     * @param deleteFlag the deleteFlag to set
     */
    public void setDeleteFlag(final boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * Sets the division name.
     *
     * @param divisionName the divisionName to set
     */
    public void setDivisionName(final String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * Sets the file name.
     *
     * @param fileName the fileName to set
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Sets the id.
     *
     * @param id the id to set
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Sets the last modified by.
     *
     * @param lastModifiedBy the lastModifiedBy to set
     */
    public void setLastModifiedBy(final String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * Sets the last modified date.
     *
     * @param lastModifiedDate the lastModifiedDate to set
     */
    public void setLastModifiedDate(final Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * Sets the micr bank code.
     *
     * @param micrBankCode the micrBankCode to set
     */
    public void setMicrBankCode(final String micrBankCode) {
        this.micrBankCode = micrBankCode;
    }

    /**
     * Sets the micr branch code.
     *
     * @param micrBranchCode the micrBranchCode to set
     */
    public void setMicrBranchCode(final String micrBranchCode) {
        this.micrBranchCode = micrBranchCode;
    }

    /**
     * Sets the micr certre code.
     *
     * @param micrCertreCode the micrCertreCode to set
     */
    public void setMicrCertreCode(final String micrCertreCode) {
        this.micrCertreCode = micrCertreCode;
    }

    /**
     * Sets the pan gir number.
     *
     * @param panGirNumber the panGirNumber to set
     */
    public void setPanGirNumber(final String panGirNumber) {
        this.panGirNumber = panGirNumber;
    }

    /**
     * Sets the pin code.
     *
     * @param pinCode the pinCode to set
     */
    public void setPinCode(final String pinCode) {
        this.pinCode = pinCode;
    }

    /**
     * Sets the sol desc.
     *
     * @param solDesc the solDesc to set
     */
    public void setSolDesc(final String solDesc) {
        this.solDesc = solDesc;
    }

    /**
     * Sets the sol id.
     *
     * @param solId the solId to set
     */
    public void setSolId(final String solId) {
        this.solId = solId;
    }

    /**
     * Sets the state code.
     *
     * @param stateCode the stateCode to set
     */
    public void setStateCode(final String stateCode) {
        this.stateCode = stateCode;
    }

    /**
     * Sets the tax dedn details.
     *
     * @param taxDednDetails the taxDednDetails to set
     */
    public void setTaxDednDetails(final String taxDednDetails) {
        this.taxDednDetails = taxDednDetails;
    }

    /**
     * Sets the tds circle name.
     *
     * @param tdsCircleName the tdsCircleName to set
     */
    public void setTdsCircleName(final String tdsCircleName) {
        this.tdsCircleName = tdsCircleName;
    }

    /**
     * Sets the uniform br code.
     *
     * @param uniformBrCode the uniformBrCode to set
     */
    public void setUniformBrCode(final String uniformBrCode) {
        this.uniformBrCode = uniformBrCode;
    }

    /**
     * Sets the verified by.
     *
     * @param verifiedBy the verifiedBy to set
     */
    public void setVerifiedBy(final String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    /**
     * Sets the verified date.
     *
     * @param verifiedDate the verifiedDate to set
     */
    public void setVerifiedDate(final Date verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    /**
     * Sets the verified flag.
     *
     * @param verifiedFlag the verifiedFlag to set
     */
    public void setVerifiedFlag(final boolean verifiedFlag) {
        this.verifiedFlag = verifiedFlag;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */

    /**
     	 * Gets the branchLocation.
     	 *
     	 * @return the branchLocation
     	 */
	public String getBranchLocation() {
        		return branchLocation;
        	}
	/**
 	 * Sets the branchLocation.
 	 *
 	 * @param branchLocation
 	 *            the new branchLocation to set
 	 */

    public void setBranchLocation(String branchLocation) {
        this.branchLocation = branchLocation;
    }

    public String getCreatedDat() {
        return createdDat;
    }

    public void setCreatedDat(String createdDat) {
        this.createdDat = createdDat;
    }

    @Override
    public String toString() {
        return "BankBranchDto{" +
                "id=" + id +
                ", solId='" + solId + '\'' +
                ", id1='" + id1 + '\'' +
                ", solDesc='" + solDesc + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", micrCertreCode='" + micrCertreCode + '\'' +
                ", micrBankCode='" + micrBankCode + '\'' +
                ", micrBranchCode='" + micrBranchCode + '\'' +
                ", addr1='" + addr1 + '\'' +
                ", addr2='" + addr2 + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", stateCode='" + stateCode + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", panGirNumber='" + panGirNumber + '\'' +
                ", taxDednDetails='" + taxDednDetails + '\'' +
                ", tdsCircleName='" + tdsCircleName + '\'' +
                ", divisionName='" + divisionName + '\'' +
                ", circlOfficeName='" + circlOfficeName + '\'' +
                ", uniformBrCode='" + uniformBrCode + '\'' +
                ", branchLocation='" + branchLocation + '\'' +
                ", deleteFlag=" + deleteFlag +
                ", verifiedFlag=" + verifiedFlag +
                ", fileName='" + fileName + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", createdDat='" + createdDat + '\'' +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                ", verifiedBy='" + verifiedBy + '\'' +
                ", verifiedDate=" + verifiedDate +
                ", additionalText1='" + additionalText1 + '\'' +
                ", additionalText2='" + additionalText2 + '\'' +
                ", additionalText3='" + additionalText3 + '\'' +
                ", branchStatus=" + branchStatus +
                ", Status='" + Status + '\'' +
                ", branchStatusDescription='" + branchStatusDescription + '\'' +
                ", verifiedStatus='" + verifiedStatus + '\'' +
                ", prevOperationType='" + prevOperationType + '\'' +
                ", createdDate1='" + createdDate1 + '\'' +
                ", verifiedDate1='" + verifiedDate1 + '\'' +
                ", modifiedDate1='" + modifiedDate1 + '\'' +
                ", pkColVal='" + pkColVal + '\'' +
                ", oldNewValueList=" + oldNewValueList +
                '}';
    }
}
