/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.parser.model;

import org.hibernate.validator.constraints.NotBlank;
import org.javafunk.excelparser.annotations.ExcelField;
import org.javafunk.excelparser.annotations.ExcelObject;
import org.javafunk.excelparser.annotations.ParseType;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@ExcelObject(parseType = ParseType.ROW, start = 2)
@Validated
public class
OtherBankDetailsExcelRows {

    @ExcelField(position = 1)
    @NotBlank(message = "Bank code should not be blank")
    @Size(max = 8, message = "Bank code should not have more than 8 characters")
    private String bankCode;

    @ExcelField(position = 2)
    @Size(max = 50, message = "Bank Name should not have more than 50 characters")
    private String bankName;

    @ExcelField(position = 3)
    @Size(max = 50, message = "Bank Short Name should not have more than 50 characters")
    private String bankShortName;

    /**
     * The additional text 1.
     */
    @ExcelField(position = 4)
    @Size(max = 100, message = "Address One should not have more than 100 characters")
    private String addOne;

    /**
     * The additional text 2.
     */
    @ExcelField(position = 5)
    @Size(max = 100, message = "Address two should not have more than 100 characters")
    private String addTwo;

    /**
     * The additional text 3.
     */
    @ExcelField(position = 6)
    @Size(max = 50, message = "City should not have more than 50 characters")
    private String city;
    /**
     * The additional text 3.
     */
    @ExcelField(position = 7)
    @Size(max = 6, message = "PinCode should not have more than 6 characters")
    @Pattern(regexp = "^[0-9]{1,6}$", message = "PinCode should contain only digits")
    private String pinCode;

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
        final OtherBankDetailsExcelRows other = (OtherBankDetailsExcelRows) obj;

        if (this.bankCode == null) {
            if (other.bankCode != null) {
                return false;
            }
        } else if (!this.bankCode.equals(other.bankCode)) {
            return false;
        }

        return true;
    }

    public String getAddOne() {
        return addOne;
    }

    public void setAddOne(String addOne) {
        this.addOne = addOne;
    }

    public String getAddTwo() {
        return addTwo;
    }

    public void setAddTwo(String addTwo) {
        this.addTwo = addTwo;
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

    public String getBankCode() {
        return this.bankCode;
    }

    public String getBankName() {
        return this.bankName;
    }

    public String getBankShortName() {
        return this.bankShortName;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = (PRIME * result) + (this.bankCode == null ? 0 : this.bankCode.hashCode());
        return result;
    }


    public void setBankCode(final String bankCode) {
        this.bankCode = bankCode;
    }

    public void setBankName(final String bankName) {
        this.bankName = bankName;
    }

    public void setBankShortName(final String bankShortName) {
        this.bankShortName = bankShortName;
    }

}
