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

import jakarta.validation.constraints.Size;

/**
 * The Class InstrumentDetailsExcelRow.
 */
@ExcelObject(parseType = ParseType.ROW, start = 2)
public class InstrumentDetailsExcelRow {

    /**
     * The dr cr indicator.
     */
    @ExcelField(position = 1)
    private String drCrIndicator;

    /**
     * The instrument type.
     */
    @ExcelField(position = 2)
    @NotBlank(message = ErrorMessages.INSTRUMENT_TYPE_EMPTY_MSG)
    @Size(max = 5, message = ErrorMessages.INSTRUMENT_TYPE_MAX_LENGTH)
    private String instrumentType;

    /**
     * The scheme code.
     */
    @ExcelField(position = 3)
    @NotBlank(message = ErrorMessages.SCHEME_CODE_EMPTY_MSG)
    @Size(max = 5, message = ErrorMessages.SCHEME_CODE_MAX_LENGTH)
    private String schemeCode;

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
        final InstrumentDetailsExcelRow other = (InstrumentDetailsExcelRow) obj;

        if (this.instrumentType == null) {
            if (other.instrumentType != null) {
                return false;
            }
        } else if (!this.instrumentType.equals(other.instrumentType)) {
            return false;
        }
        if (this.schemeCode == null) {
            if (other.schemeCode != null) {
                return false;
            }
        } else if (!this.schemeCode.equals(other.schemeCode)) {
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
     * Gets the dr cr indicator.
     *
     * @return the dr cr indicator
     */
    public String getDrCrIndicator() {
        return this.drCrIndicator;
    }

    /**
     * Gets the instrument type.
     *
     * @return the instrument type
     */
    public String getInstrumentType() {
        return this.instrumentType;
    }

    /**
     * Gets the scheme code.
     *
     * @return the scheme code
     */
    public String getSchemeCode() {
        return this.schemeCode;
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
        result = (PRIME * result) + ((this.instrumentType == null) ? 0 : this.instrumentType.hashCode());
        result = (PRIME * result) + ((this.schemeCode == null) ? 0 : this.schemeCode.hashCode());
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
     * Sets the dr cr indicator.
     *
     * @param drCrIndicator the new dr cr indicator
     */
    public void setDrCrIndicator(final String drCrIndicator) {
        this.drCrIndicator = drCrIndicator;
    }

    /**
     * Sets the instrument type.
     *
     * @param instrumentType the new instrument type
     */
    public void setInstrumentType(final String instrumentType) {
        this.instrumentType = instrumentType;
    }

    /**
     * Sets the scheme code.
     *
     * @param schemeCode the new scheme code
     */
    public void setSchemeCode(final String schemeCode) {
        this.schemeCode = schemeCode;
    }

}
