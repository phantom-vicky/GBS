/**
 *
 */
package com.isl.gbm.cbs.integration.parser.model;

import com.isl.gbm.common.util.CommonConstants;
import org.hibernate.validator.constraints.NotBlank;
import org.javafunk.excelparser.annotations.ExcelField;
import org.javafunk.excelparser.annotations.ExcelObject;
import org.javafunk.excelparser.annotations.ParseType;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * The Class BranchBanksExcelRows.
 *
 * @author harishbabu.t
 */
@ExcelObject(parseType = ParseType.ROW, start = 2)
@Validated
public class BankBranchExcelRow {

    /** The sol id. */
    @ExcelField(position = 1)
    @NotBlank(message = "SolId should not be blank")
    @Size(max = 5, message = "SolId should not have more than 5 characters")
    private String solId;

    /** The sol desc. */
    @ExcelField(position = 2)
    @NotBlank(message = "Sol Description should not be blank")
    @Size(max = 30, message = "Sol Description should not have more than 30 characters")
    private String solDesc;

    /** The bank code. */
    @ExcelField(position = 3)
    @Size(max = 10, message = "Bank Code should not have more than 10 characters")
    private String bankCode;

    /** The branch code. */
//    @ExcelField(position = 4)
//    @NotBlank(message = "Branch Code should not be blank")
//    @Size(max = 6, message = "Branch Code should not have more than 6 characters")
//    private String branchCode;

    /** The micr certre code. */
    @ExcelField(position = 4)
//    @NotBlank(message = "MICR Center Code should not be blank")
    @Size(max = 6, message = "MICR Center Code should not have more than 6 characters")
    private String micrCertreCode;

    /** The micr bank code. */
    @ExcelField(position = 5)
//    @NotBlank(message = "MICR Bank Code should not be blank")
    @Size(max = 12, message = "MICR Bank Code should not have more than 12 characters")
    private String micrBankCode;

    /** The micr branch code. */
    @ExcelField(position = 6)
//    @NotBlank(message = "MICR Branch Code should not be blank")
    @Size(max = 12, message = "MICR Branch Code should not have more than 12 characters")
    private String micrBranchCode;

    /** The addr 1. */
    @ExcelField(position = 7)
    @NotBlank(message = "Address1 should not be blank")
    @Size(max = 30, message = "Address1 should not have more than 30 characters")
    private String addr1;

    /** The addr 2. */
    @ExcelField(position = 8)
    @NotBlank(message = "Address2 should not be blank")
    @Size(max = 30, message = "Address2 should not have more than 30 characters")
    private String addr2;

    /** The city code. */
    @ExcelField(position = 9)
//    @NotBlank(message = "City Code should not be blank")
    @Size(max = 20, message = "City Name should not have more than 20 characters")
    private String cityCode;

    /** The state code. */
    @ExcelField(position = 10)
//    @NotBlank(message = "State Code should not be blank")
    @Size(max = 50, message = "State Name should not have more than 50 characters")
    private String stateCode;

    /** The pin code. */
    @ExcelField(position = 11)
    @NotBlank(message = "PinCode should not be blank")
    @Size(max = 6, message = "PinCode should not have more than 6 characters")
    @Pattern(regexp = "^[0-9]{1,6}$", message = "PinCode should contain only digits")
    private String pinCode;

    /** The pan gir number. */
    @ExcelField(position = 12)
//    @NotBlank(message = "PanGirNumber should not be blank")
    @Pattern(regexp = CommonConstants.PAN_PATTERN, message = "Invalid PAN number")
    @Size(max = 10, message = "PanGirNumber should not have more than 10 characters")
    private String panGirNumber;

    /** The tax dedn details. */
    @ExcelField(position = 13)
//    @NotBlank(message = "Tax dend Details should not be blank")
    @Pattern(regexp = CommonConstants.TAN_PATTERN, message = "Invalid TAN number")
    @Size(max = 25, message = "Tax dend Details should not have more than 25 characters")
    private String taxDednDetails;

    /** The tds circle name. */
    @ExcelField(position = 14)
//    @NotBlank(message = "Tds Circle Name should not be blank")
    @Size(max = 6, message = "Tds Circle Name should not have more than 6 characters")
    private String tdsCircleName;

    /** The division name. */
    @ExcelField(position = 15)
//    @NotBlank(message = "Division Name should not be blank")
    @Size(max = 12, message = "Division Name should not have more than 50 characters")
    private String divisionName;

    /** The circl office name. */
    @ExcelField(position = 16)
//    @NotBlank(message = "Circle Ofice Name should not be blank")
    @Size(max = 30, message = "Circle Ofice Name should not have more than 30 characters")
    private String circlOfficeName;

    /** The uniform br code. */
    @ExcelField(position = 17)
//    @NotBlank(message = "Uniform Branch Code should not be blank")
    @Size(max = 6, message = "Uniform Branch Code should not have more than 6 characters")
    private String uniformBrCode;

    /** Branch Location */
    @ExcelField(position = 18)
//    @NotBlank(message = "Branch Location should not be blank")
    @Size(max = 120, message = "Branch Location should not have more than 120 characters")
    private String branchLocation;

    @ExcelField(position = 19)
//    @NotBlank(message = "Branch Location should not be blank")
    @Size(max = 3, message = "Zone Code should not have more than 3 characters")
    private String zoneCode;

    @ExcelField(position = 20)
//    @NotBlank(message = "Branch Location should not be blank")
    @Size(max = 30, message = "Zone Name should not have more than 30 characters")
    private String zoneName;

    @ExcelField(position = 21)
    @NotBlank(message = "PDO Code should not be blank")
    @Size(max = 30, message = "PDO Code should not have more than 30 characters")
    private String pdoCode;

    @ExcelField(position = 22)
    @NotBlank(message = "Short Name should not be blank")
    @Size(max = 10, message = "Short Name should not have more than 10 characters")
    private String shortName;

    @ExcelField(position = 23)
    //@NotBlank(message = "Branch Email ID cannot be blank")
    @Pattern(
            regexp = "^(?!\\s)[a-zA-Z0-9_%+-][a-zA-Z0-9._%+-]*@(?i)(hdfcbank\\.com|hdfcbank\\.co|hdfcbank\\.in)$",
            message = "Invalid email format. Only HDFC domain emails are allowed"
    )
    @Email(message = "Email should be valid")
    private String branchEmailId;

    @ExcelField(position = 24)  // Adjust position as per your Excel sheet
    @Pattern(regexp = "(Active|Closed|Merged)?", message = "Branch status must be Active, Closed, or Merged")
//    @Null(message = "Branch status must be null or Active, Closed, or Merge")
    private String branchStatus;

    @ExcelField(position = 25)  // Adjust position as per your Excel sheet
    @Pattern(regexp = CommonConstants.IFSC_PATTERN, message = "Invalid IFSC Code")
//    @Null(message = "Branch status must be null or Active, Closed, or Merge")
    private String branchIfscCode;

    public String getBranchIfscCode() {
        return branchIfscCode;
    }

    public void setBranchIfscCode(String branchIfscCode) {
        this.branchIfscCode = branchIfscCode;
    }

    public String getBranchStatus() {
        return branchStatus;
    }

    public void setBranchStatus(String branchStatus) {
        this.branchStatus = branchStatus;
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

    public void setBranchEmailId(String email) {
        this.branchEmailId = email != null ? email.toLowerCase() : null;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final BankBranchExcelRow other = (BankBranchExcelRow) obj;
        if (this.solId == null) {
            if (other.solId != null) {
                return false;
            }
        } else if (!this.solId.equals(other.solId)) {
            return false;
        }
        return true;
    }

    /**
     * Gets the additional text 1.
     *
     * @return the additionalText1
     */
//    public String getAdditionalText1() {
//        return this.additionalText1;
//    }

    /**
     * Gets the additional text 2.
     *
     * @return the additionalText2
     */
//    public String getAdditionalText2() {
//        return this.additionalText2;
//    }

    /**
     * Gets the additional text 3.
     *
     * @return the additionalText3
     */
//    public String getAdditionalText3() {
//        return this.additionalText3;
//    }

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
//    public String getBranchCode() {
//        return this.branchCode;
//    }

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
     * Gets the division name.
     *
     * @return the divisionName
     */
    public String getDivisionName() {
        return this.divisionName;
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

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */

    /**
         * Gets the Branch Location.
          *
          * @return the branchLocation
          */
    public String getBranchLocation() {
                return branchLocation;
    }
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = (PRIME * result) + (this.solId == null ? 0 : this.solId.hashCode());
        return result;
    }


    /**
     +     * Sets the branch location.
     +     *
     +     * @param branchLocation
     +     *            the new branchLocation to set
     +     */
    public void setBranchLocation(String branchLocation) {
                this.branchLocation = branchLocation;
    }
    /**
     * Sets the additional text 1.
     *
     * @param additionalText1
     *            the additionalText1 to set
     */
//    public void setAdditionalText1(final String additionalText1) {
//        this.additionalText1 = additionalText1;
//    }

    /**
     * Sets the additional text 2.
     *
     * @param additionalText2
     *            the additionalText2 to set
     */
//    public void setAdditionalText2(final String additionalText2) {
//        this.additionalText2 = additionalText2;
//    }

    /**
     * Sets the additional text 3.
     *
     * @param additionalText3
     *            the additionalText3 to set
     */
//    public void setAdditionalText3(final String additionalText3) {
//        this.additionalText3 = additionalText3;
//    }

    /**
     * Sets the addr 1.
     *
     * @param addr1
     *            the addr1 to set
     */
    public void setAddr1(final String addr1) {
        this.addr1 = addr1;
    }

    /**
     * Sets the addr 2.
     *
     * @param addr2
     *            the addr2 to set
     */
    public void setAddr2(final String addr2) {
        this.addr2 = addr2;
    }

    /**
     * Sets the bank code.
     *
     * @param bankCode
     *            the bankCode to set
     */
    public void setBankCode(final String bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * Sets the branch code.
     *
     * @param branchCode
     *            the branchCode to set
     */
//    public void setBranchCode(final String branchCode) {
//        this.branchCode = branchCode;
//    }

    /**
     * Sets the circl office name.
     *
     * @param circlOfficeName
     *            the circlOfficeName to set
     */
    public void setCirclOfficeName(final String circlOfficeName) {
        this.circlOfficeName = circlOfficeName;
    }

    /**
     * Sets the city code.
     *
     * @param cityCode
     *            the cityCode to set
     */
    public void setCityCode(final String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * Sets the division name.
     *
     * @param divisionName
     *            the divisionName to set
     */
    public void setDivisionName(final String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * Sets the micr bank code.
     *
     * @param micrBankCode
     *            the micrBankCode to set
     */
    public void setMicrBankCode(final String micrBankCode) {
        this.micrBankCode = micrBankCode;
    }

    /**
     * Sets the micr branch code.
     *
     * @param micrBranchCode
     *            the micrBranchCode to set
     */
    public void setMicrBranchCode(final String micrBranchCode) {
        this.micrBranchCode = micrBranchCode;
    }

    /**
     * Sets the micr certre code.
     *
     * @param micrCertreCode
     *            the micrCertreCode to set
     */
    public void setMicrCertreCode(final String micrCertreCode) {
        this.micrCertreCode = micrCertreCode;
    }

    /**
     * Sets the pan gir number.
     *
     * @param panGirNumber
     *            the panGirNumber to set
     */
    public void setPanGirNumber(final String panGirNumber) {
        this.panGirNumber = panGirNumber;
    }

    /**
     * Sets the pin code.
     *
     * @param pinCode
     *            the pinCode to set
     */
    public void setPinCode(final String pinCode) {
        this.pinCode = pinCode;
    }

    /**
     * Sets the sol desc.
     *
     * @param solDesc
     *            the solDesc to set
     */
    public void setSolDesc(final String solDesc) {
        this.solDesc = solDesc;
    }

    /**
     * Sets the sol id.
     *
     * @param solId
     *            the solId to set
     */
    public void setSolId(final String solId) {
        this.solId = solId;
    }

    /**
     * Sets the state code.
     *
     * @param stateCode
     *            the stateCode to set
     */
    public void setStateCode(final String stateCode) {
        this.stateCode = stateCode;
    }

    /**
     * Sets the tax dedn details.
     *
     * @param taxDednDetails
     *            the taxDednDetails to set
     */
    public void setTaxDednDetails(final String taxDednDetails) {
        this.taxDednDetails = taxDednDetails;
    }

    /**
     * Sets the tds circle name.
     *
     * @param tdsCircleName
     *            the tdsCircleName to set
     */
    public void setTdsCircleName(final String tdsCircleName) {
        this.tdsCircleName = tdsCircleName;
    }

    /**
     * Sets the uniform br code.
     *
     * @param uniformBrCode
     *            the uniformBrCode to set
     */
    public void setUniformBrCode(final String uniformBrCode) {
        this.uniformBrCode = uniformBrCode;
    }

}
