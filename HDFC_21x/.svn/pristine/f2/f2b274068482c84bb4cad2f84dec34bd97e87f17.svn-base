package com.isl.gbm.rbibond.dto;

import com.opencsv.bean.CsvBindByPosition;
import org.javafunk.excelparser.annotations.ExcelField;
import org.javafunk.excelparser.annotations.ExcelObject;
import org.javafunk.excelparser.annotations.ParseType;
import org.springframework.validation.annotation.Validated;
@Validated
@ExcelObject(parseType = ParseType.ROW, start = 2)
public class NsdlPanStatusDTO {
    @ExcelField(position = 1)
    private String pan;
    @ExcelField(position = 2)
    private String panStatus;
    @ExcelField(position = 3)
    private String panAsPerNsdl;
    @ExcelField(position = 4)
    private String name;
    @ExcelField(position = 5)
    private String nameStatus;
    @ExcelField(position = 6)
    private String nameAsPerNsdl;
    @ExcelField(position = 7)
    private String dob;
    @ExcelField(position = 8)
    private String dobStatus;
    @ExcelField(position = 9)
    private String dobAsPerNsdl;
    @ExcelField(position = 10)
    private String remarks;

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getPanStatus() {
        return panStatus;
    }

    public void setPanStatus(String panStatus) {
        this.panStatus = panStatus;
    }

    public String getPanAsPerNsdl() {
        return panAsPerNsdl;
    }

    public void setPanAsPerNsdl(String panAsPerNsdl) {
        this.panAsPerNsdl = panAsPerNsdl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    public String getNameAsPerNsdl() {
        return nameAsPerNsdl;
    }

    public void setNameAsPerNsdl(String nameAsPerNsdl) {
        this.nameAsPerNsdl = nameAsPerNsdl;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDobStatus() {
        return dobStatus;
    }

    public void setDobStatus(String dobStatus) {
        this.dobStatus = dobStatus;
    }

    public String getDobAsPerNsdl() {
        return dobAsPerNsdl;
    }

    public void setDobAsPerNsdl(String dobAsPerNsdl) {
        this.dobAsPerNsdl = dobAsPerNsdl;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "NsdlPanStatusDTO{" +
               // "pan='" + pan + '\'' +
                ", panStatus='" + panStatus + '\'' +
                ", panAsPerNsdl='" + panAsPerNsdl + '\'' +
                ", name='" + name + '\'' +
                ", nameStatus='" + nameStatus + '\'' +
                ", nameAsPerNsdl='" + nameAsPerNsdl + '\'' +
                ", dob='" + dob + '\'' +
                ", dobStatus='" + dobStatus + '\'' +
                ", dobAsPerNsdl='" + dobAsPerNsdl + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
