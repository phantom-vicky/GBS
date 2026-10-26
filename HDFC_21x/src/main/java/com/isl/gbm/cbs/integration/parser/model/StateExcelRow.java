package com.isl.gbm.cbs.integration.parser.model;

import com.isl.gbm.cbs.integration.validator.SpecialCharacterValidation;
import org.javafunk.excelparser.annotations.ExcelField;
import org.javafunk.excelparser.annotations.ExcelObject;
import org.javafunk.excelparser.annotations.ParseType;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.Pattern;

@ExcelObject(parseType = ParseType.ROW, start = 2)
@Validated
public class StateExcelRow {

    /** The sol id. */
    @ExcelField(position = 1)
    @SpecialCharacterValidation(message = "State Field contains special characters")
    private String state;

    /** The sol desc. */
    @ExcelField(position = 2)
    @SpecialCharacterValidation(message = "City Field contains special characters")
    private String city;

    /** The bank code. */
    @ExcelField(position = 3)
    @SpecialCharacterValidation(message = "PinCode Field contains special characters")
    @Pattern(regexp = "\\d{6}", message = "PinCode must be a 6-digit numeric value")
    private String pinCode;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getPostOfficeName() {
        return postOfficeName;
    }

    public void setPostOfficeName(String postOfficeName) {
        this.postOfficeName = postOfficeName;
    }

    /** The branch code. */
    @ExcelField(position = 4)
    //@SpecialCharacterValidation(message = "Post Office Name Field contains special characters")
    private String postOfficeName;
}
