package com.isl.gbm.rbibond.dto;
import org.javafunk.excelparser.annotations.ExcelField;
import org.javafunk.excelparser.annotations.ExcelObject;
import org.javafunk.excelparser.annotations.ParseType;
import org.springframework.validation.annotation.Validated;

@ExcelObject(parseType = ParseType.ROW, start = 2)
@Validated
public class IncomeTaxPanStatusDTO {
    @ExcelField(position = 1)
    private String panNo;
    @ExcelField(position = 2)
    private String itrFlagStatus;
    @ExcelField(position = 3)
    private String incomeTaxDate; // Use java.time.LocalDate for date

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getItrFlagStatus() {
        return itrFlagStatus;
    }

    public void setItrFlagStatus(String itrFlagStatus) {
        this.itrFlagStatus = itrFlagStatus;
    }

    public String getIncomeTaxDate() {
        return incomeTaxDate;
    }

    public void setIncomeTaxDate(String incomeTaxDate) {
        this.incomeTaxDate = incomeTaxDate;
    }

    @Override
    public String toString() {
        return "IncomeTaxPanStatusDTO{" +
               // "panNo='" + panNo + '\'' +
                ", itrFlagStatus='" + itrFlagStatus + '\'' +
                ", incomeTaxDate='" + incomeTaxDate + '\'' +
                '}';
    }
}
