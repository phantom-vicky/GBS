/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.parser.model;

import com.isl.gbm.cbs.integration.common.ErrorMessages;
import org.hibernate.validator.constraints.NotBlank;
import org.javafunk.excelparser.annotations.ExcelField;
import org.javafunk.excelparser.annotations.ExcelObject;
import org.javafunk.excelparser.annotations.ParseType;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Size;

/**
 * The Class BranchIfscDetailsExcelRow.
 */
@ExcelObject(parseType = ParseType.ROW, start = 2)
@Validated
public class BranchIfscDetailsExcelRow {

    /**
     * The bank code.
     */
    @ExcelField(position = 1)
    @NotBlank(message = "Bank code should not be blank")
    @Size(max = 15, message = "Bank code should not have morethan 15 characters")
    private String bankCode;

    /**
     * The branch code.
     */
    @ExcelField(position = 2)
    @NotBlank(message = "Branch code should not be blank")
    @Size(max = 15, message = "Branch code should not have morethan 15 characters")
    private String branchCode;

    /**
     * The bank identifier.
     */
    @ExcelField(position = 3)
    @NotBlank(message = "Bank identifier should not be blank")
    @Size(max = 15, message = "Bank identifier should not have morethan 15 characters")
    private String bankIdentifier;

    /**
     * The paysys id.
     */
    @ExcelField(position = 4)
    @NotBlank(message = "Paysys Id should not be blank")
    @Size(max = 15, message = "Paysys Id should not have morethan 15 characters")
    private String paysysId;

    /**
     * The delete flag.
     */
    @ExcelField(position = 5)
    private String deleteFlag;

    /**
     * The additional text 1.
     */
    @ExcelField(position = 6)
    @Size(max = 40, message = ErrorMessages.ADDL_TEXT1_MAX_LENGTH_MSG)
    private String additionalText1;

    /**
     * The additional text 2.
     */
    @ExcelField(position = 7)
    @Size(max = 40, message = ErrorMessages.ADDL_TEXT2_MAX_LENGTH_MSG)
    private String additionalText2;

    /**
     * The additional text 3.
     */
    @ExcelField(position = 8)
    @Size(max = 40, message = ErrorMessages.ADDL_TEXT3_MAX_LENGTH_MSG)
    private String additionalText3;

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
        final BranchIfscDetailsExcelRow other = (BranchIfscDetailsExcelRow) obj;

        if (this.bankCode == null) {
            if (other.bankCode != null) {
                return false;
            }
        } else if (!this.bankCode.equals(other.bankCode)) {
            return false;
        }
        if (this.branchCode == null) {
            if (other.branchCode != null) {
                return false;
            }
        } else if (!this.branchCode.equals(other.branchCode)) {
            return false;
        }

        return true;
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

    /**
     * Gets the bank code.
     *
     * @return the bank code
     */
    public String getBankCode() {
        return this.bankCode;
    }

    /**
     * Gets the bank identifier.
     *
     * @return the bank identifier
     */
    public String getBankIdentifier() {
        return this.bankIdentifier;
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
     * Gets the delete flag.
     *
     * @return the delete flag
     */
    public String getDeleteFlag() {
        return this.deleteFlag;
    }

    /**
     * Gets the paysys id.
     *
     * @return the paysys id
     */
    public String getPaysysId() {
        return this.paysysId;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = (PRIME * result) + ((this.bankCode == null) ? 0 : this.bankCode.hashCode());
        result = (PRIME * result) + ((this.branchCode == null) ? 0 : this.branchCode.hashCode());
        return result;
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
     * Sets the bank code.
     *
     * @param bankCode the new bank code
     */
    public void setBankCode(final String bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * Sets the bank identifier.
     *
     * @param bankIdentifier the new bank identifier
     */
    public void setBankIdentifier(final String bankIdentifier) {
        this.bankIdentifier = bankIdentifier;
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
     * Sets the delete flag.
     *
     * @param deleteFlag the new delete flag
     */
    public void setDeleteFlag(final String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * Sets the paysys id.
     *
     * @param paysysId the new paysys id
     */
    public void setPaysysId(final String paysysId) {
        this.paysysId = paysysId;
    }

}
