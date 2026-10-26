/*
 *
 */
package com.isl.gbm.cbs.integration.parser.model;

import org.hibernate.validator.constraints.NotBlank;
import org.javafunk.excelparser.annotations.ExcelField;
import org.javafunk.excelparser.annotations.ExcelObject;
import org.javafunk.excelparser.annotations.ParseType;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Size;

/**
 * The Class TransactionCodesExcelRow.
 */

@ExcelObject(parseType = ParseType.ROW, start = 2)
@Validated
public class TransactionCodesExcelRow {

    /**
     * The clg tran code.
     */
    @ExcelField(position = 1)
    @NotBlank(message = "transaction code should not be blank")
    @Size(max = 5, message = "transaction code should not have morethan 5 characters")
    private String clgTranCode;

    /**
     * The clg tran code desc.
     */
    @ExcelField(position = 2)
    @NotBlank(message = "transaction code description should not be blank")
    @Size(max = 40, message = "transaction code description should not have morethan 40 characters")
    private String clgTranCodeDesc;

    /**
     * The instrument type.
     */
    @ExcelField(position = 3)
    @NotBlank(message = "instrument type should not be blank")
    @Size(max = 5, message = "instrument type should not have morethan 5 characters")
    private String instrumentType;

    /**
     * The scheme code.
     */
    @ExcelField(position = 4)
    @NotBlank(message = "scheme code should not be blank")
    @Size(max = 5, message = "scheme code should not have morethan 5 characters")
    private String schemeCode;

    /**
     * The add text 1.
     */
    @ExcelField(position = 5)
    private String addText1;

    /**
     * The add text 2.
     */
    @ExcelField(position = 6)
    private String addText2;

    /**
     * The add text 3.
     */
    @ExcelField(position = 7)
    private String addText3;

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
        final TransactionCodesExcelRow other = (TransactionCodesExcelRow) obj;

        if (this.clgTranCode == null) {
            if (other.clgTranCode != null) {
                return false;
            }
        } else if (!this.clgTranCode.equals(other.clgTranCode)) {
            return false;
        }

        return true;
    }

    /**
     * Gets the adds the text 1.
     *
     * @return the adds the text 1
     */
    public String getAddText1() {
        return this.addText1;
    }

    /**
     * Gets the adds the text 2.
     *
     * @return the adds the text 2
     */
    public String getAddText2() {
        return this.addText2;
    }

    /**
     * Gets the adds the text 3.
     *
     * @return the adds the text 3
     */
    public String getAddText3() {
        return this.addText3;
    }

    /**
     * Gets the clg tran code.
     *
     * @return the clg tran code
     */
    public String getClgTranCode() {
        return this.clgTranCode;
    }

    /**
     * Gets the clg tran code desc.
     *
     * @return the clg tran code desc
     */
    public String getClgTranCodeDesc() {
        return this.clgTranCodeDesc;
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

        result = (PRIME * result) + (this.clgTranCode == null ? 0 : this.clgTranCode.hashCode());

        return result;
    }

    /**
     * Sets the adds the text 1.
     *
     * @param addText1 the new adds the text 1
     */
    public void setAddText1(final String addText1) {
        this.addText1 = addText1;
    }

    /**
     * Sets the adds the text 2.
     *
     * @param addText2 the new adds the text 2
     */
    public void setAddText2(final String addText2) {
        this.addText2 = addText2;
    }

    /**
     * Sets the adds the text 3.
     *
     * @param addText3 the new adds the text 3
     */
    public void setAddText3(final String addText3) {
        this.addText3 = addText3;
    }

    /**
     * Sets the clg tran code.
     *
     * @param clgTranCode the new clg tran code
     */
    public void setClgTranCode(final String clgTranCode) {
        this.clgTranCode = clgTranCode;
    }

    /**
     * Sets the clg tran code desc.
     *
     * @param clgTranCodeDesc the new clg tran code desc
     */
    public void setClgTranCodeDesc(final String clgTranCodeDesc) {
        this.clgTranCodeDesc = clgTranCodeDesc;
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
