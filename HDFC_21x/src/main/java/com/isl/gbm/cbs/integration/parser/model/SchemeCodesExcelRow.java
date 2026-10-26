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
 * The Class SchemeCodesExcelRow.
 */
@ExcelObject(parseType = ParseType.ROW, start = 2)
@Validated
public class SchemeCodesExcelRow {

    /**
     * The scheme code.
     */
    @ExcelField(position = 1)
    @NotBlank(message = "scheme code should not be blank")
    @Size(max = 5, message = "scheme code should not have morethan 5 characters")
    private String schemeCode;

    /**
     * The description.
     */
    @ExcelField(position = 2)
    @NotBlank(message = "description should not be blank")
    @Size(max = 80, message = "description should not have morethan 80 characters")
    private String description;

    /**
     * The gl sub head code.
     */
    @ExcelField(position = 3)
    @NotBlank(message = "slgSubHead code should not be blank")
    @Size(max = 5, message = "slgSubHead code should not have morethan 5 characters")
    private String glSubHeadCode;

    /**
     * The scheme type.
     */
    @ExcelField(position = 4)
    @NotBlank(message = "schemeType should not be blank")
    @Size(max = 3, message = "schemeType should not have morethan 3 characters")
    private String schemeType;

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
        final SchemeCodesExcelRow other = (SchemeCodesExcelRow) obj;
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
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the gl sub head code.
     *
     * @return the gl sub head code
     */
    public String getGlSubHeadCode() {
        return this.glSubHeadCode;
    }

    /**
     * Gets the scheme code.
     *
     * @return the scheme code
     */
    public String getSchemeCode() {
        return this.schemeCode;
    }

    /**
     * Gets the scheme type.
     *
     * @return the scheme type
     */
    public String getSchemeType() {
        return this.schemeType;
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
        result = (PRIME * result) + (this.schemeCode == null ? 0 : this.schemeCode.hashCode());
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
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Sets the gl sub head code.
     *
     * @param glSubHeadCode the new gl sub head code
     */
    public void setGlSubHeadCode(final String glSubHeadCode) {
        this.glSubHeadCode = glSubHeadCode;
    }

    /**
     * Sets the scheme code.
     *
     * @param schemeCode the new scheme code
     */
    public void setSchemeCode(final String schemeCode) {
        this.schemeCode = schemeCode;
    }

    /**
     * Sets the scheme type.
     *
     * @param schemeType the new scheme type
     */
    public void setSchemeType(final String schemeType) {
        this.schemeType = schemeType;
    }

}
