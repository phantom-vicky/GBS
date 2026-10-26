/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.parser.model;

import com.isl.gbm.cbs.integration.common.ErrorMessages;
import org.hibernate.validator.constraints.NotBlank;
import org.javafunk.excelparser.annotations.ExcelField;
import org.javafunk.excelparser.annotations.ExcelObject;
import org.javafunk.excelparser.annotations.ParseType;

import jakarta.validation.constraints.Size;

/**
 * The Class OfficeAccountExcelRows.
 */
@ExcelObject(parseType = ParseType.ROW, start = 2)
public class OfficeAccountExcelRows {

    /**
     * The bacid.
     */
    @ExcelField(position = 1)
    @NotBlank(message = "Bac Id cannot be blank")
    @Size(max = 16, message = "Bac Id should not have morethan 16 characters")
    private String bacid;

    /**
     * The bacid desc.
     */
    @ExcelField(position = 2)
    @NotBlank(message = "Bac Description cannot be blank")
    @Size(max = 40, message = "Bac Description should not have morethan 40 characters")
    private String bacidDesc;

    /**
     * The schm type.
     */
    @ExcelField(position = 3)
    @NotBlank(message = "Scheme Type cannot be blank")
    @Size(max = 5, message = "Scheme Type should not have morethan 5 characters")
    private String schmType;

    /**
     * The addtional text 1.
     */
    @ExcelField(position = 4)
    @Size(max = 40, message = ErrorMessages.ADDL_TEXT1_MAX_LENGTH_MSG)
    private String addtionalText1;

    /**
     * The addtional text 2.
     */
    @ExcelField(position = 5)
    @Size(max = 40, message = ErrorMessages.ADDL_TEXT2_MAX_LENGTH_MSG)
    private String addtionalText2;

    /**
     * The addtional text 3.
     */
    @ExcelField(position = 6)
    @Size(max = 40, message = ErrorMessages.ADDL_TEXT3_MAX_LENGTH_MSG)
    private String addtionalText3;

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
        final OfficeAccountExcelRows other = (OfficeAccountExcelRows) obj;

        if (this.bacid == null) {
            if (other.bacid != null) {
                return false;
            }
        } else if (!this.bacid.equals(other.bacid)) {
            return false;
        }
        if (this.schmType == null) {
            if (other.schmType != null) {
                return false;
            }
        } else if (!this.schmType.equals(other.schmType)) {
            return false;
        }
        return true;
    }

    /**
     * Gets the addtional text 1.
     *
     * @return the addtional text 1
     */
    public String getAddtionalText1() {
        return this.addtionalText1;
    }

    /**
     * Gets the addtional text 2.
     *
     * @return the addtional text 2
     */
    public String getAddtionalText2() {
        return this.addtionalText2;
    }

    /**
     * Gets the addtional text 3.
     *
     * @return the addtional text 3
     */
    public String getAddtionalText3() {
        return this.addtionalText3;
    }

    /**
     * Gets the bacid.
     *
     * @return the bacid
     */
    public String getBacid() {
        return this.bacid;
    }

    /**
     * Gets the bacid desc.
     *
     * @return the bacid desc
     */
    public String getBacidDesc() {
        return this.bacidDesc;
    }

    /**
     * Gets the schm type.
     *
     * @return the schm type
     */
    public String getSchmType() {
        return this.schmType;
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
        result = (PRIME * result) + (this.addtionalText1 == null ? 0 : this.addtionalText1.hashCode());
        result = (PRIME * result) + (this.addtionalText2 == null ? 0 : this.addtionalText2.hashCode());
        result = (PRIME * result) + (this.addtionalText3 == null ? 0 : this.addtionalText3.hashCode());
        result = (PRIME * result) + (this.bacid == null ? 0 : this.bacid.hashCode());
        result = (PRIME * result) + (this.bacidDesc == null ? 0 : this.bacidDesc.hashCode());
        result = (PRIME * result) + (this.schmType == null ? 0 : this.schmType.hashCode());
        return result;
    }

    /**
     * Sets the addtional text 1.
     *
     * @param addtionalText1 the new addtional text 1
     */
    public void setAddtionalText1(final String addtionalText1) {
        this.addtionalText1 = addtionalText1;
    }

    /**
     * Sets the addtional text 2.
     *
     * @param addtionalText2 the new addtional text 2
     */
    public void setAddtionalText2(final String addtionalText2) {
        this.addtionalText2 = addtionalText2;
    }

    /**
     * Sets the addtional text 3.
     *
     * @param addtionalText3 the new addtional text 3
     */
    public void setAddtionalText3(final String addtionalText3) {
        this.addtionalText3 = addtionalText3;
    }

    /**
     * Sets the bacid.
     *
     * @param bacid the new bacid
     */
    public void setBacid(final String bacid) {
        this.bacid = bacid;
    }

    /**
     * Sets the bacid desc.
     *
     * @param bacidDesc the new bacid desc
     */
    public void setBacidDesc(final String bacidDesc) {
        this.bacidDesc = bacidDesc;
    }

    /**
     * Sets the schm type.
     *
     * @param schmType the new schm type
     */
    public void setSchmType(final String schmType) {
        this.schmType = schmType;
    }

}
