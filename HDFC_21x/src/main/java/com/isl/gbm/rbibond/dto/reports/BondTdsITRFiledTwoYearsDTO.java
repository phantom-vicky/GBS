package com.isl.gbm.rbibond.dto.reports;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BondTdsITRFiledTwoYearsDTO {
    private String assessmentYear;
    private String ackNumber;
    private String returnIncome;

    public String getAssessmentYear() {
        return assessmentYear;
    }

    public void setAssessmentYear(String assessmentYear) {
        this.assessmentYear = assessmentYear;
    }

    public String getAckNumber() {
        return ackNumber;
    }

    public void setAckNumber(String ackNumber) {
        this.ackNumber = ackNumber;
    }

    public String getReturnIncome() {
        return returnIncome;
    }

    public void setReturnIncome(String returnIncome) {
        this.returnIncome = returnIncome;
    }
}
