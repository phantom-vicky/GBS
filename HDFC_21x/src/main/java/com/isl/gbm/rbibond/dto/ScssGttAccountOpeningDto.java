package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScssGttAccountOpeningDto {

    private String scssAccountNumber;

    private String scssTranLeg;

    private String scssAmount;

    public String getScssAccountNumber() {
        return scssAccountNumber;
    }

    public void setScssAccountNumber(String scssAccountNumber) {
        this.scssAccountNumber = scssAccountNumber;
    }

    public String getScssTranLeg() {
        return scssTranLeg;
    }

    public void setScssTranLeg(String scssTranLeg) {
        this.scssTranLeg = scssTranLeg;
    }

    public String getScssAmount() {
        return scssAmount;
    }

    public void setScssAmount(String scssAmount) {
        this.scssAmount = scssAmount;
    }
}
