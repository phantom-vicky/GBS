package com.isl.gbm.rbibond.dto;

import org.javafunk.excelparser.annotations.ExcelField;
import org.javafunk.excelparser.annotations.ExcelObject;
import org.javafunk.excelparser.annotations.ParseType;
import org.springframework.validation.annotation.Validated;

@ExcelObject(parseType = ParseType.ROW, start = 2)
@Validated
public class PanStatusUploadForTdsFileUploadDTO {
    @ExcelField(position = 1)
    private String oldPan;
    @ExcelField(position = 2)
    private String oldDob;
    @ExcelField(position = 3)
    private String oldName;
    @ExcelField(position = 4)
    private String oldPanStatus;
    @ExcelField(position = 5)
    private String newPanStatus;
    @ExcelField(position = 6)
    private String oldAadharSeedingStatus;
    @ExcelField(position = 7)
    private String newAadharSeedingStatus;


    public String getOldPan() {
        return oldPan;
    }

    public void setOldPan(String oldPan) {
        this.oldPan = oldPan;
    }

    public String getOldDob() {
        return oldDob;
    }

    public void setOldDob(String oldDob) {
        this.oldDob = oldDob;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getOldPanStatus() {
        return oldPanStatus;
    }

    public void setOldPanStatus(String oldPanStatus) {
        this.oldPanStatus = oldPanStatus;
    }

    public String getNewPanStatus() {
        return newPanStatus;
    }

    public void setNewPanStatus(String newPanStatus) {
        this.newPanStatus = newPanStatus;
    }

    public String getOldAadharSeedingStatus() {
        return oldAadharSeedingStatus;
    }

    public void setOldAadharSeedingStatus(String oldAadharSeedingStatus) {
        this.oldAadharSeedingStatus = oldAadharSeedingStatus;
    }

    public String getNewAadharSeedingStatus() {
        return newAadharSeedingStatus;
    }

    public void setNewAadharSeedingStatus(String newAadharSeedingStatus) {
        this.newAadharSeedingStatus = newAadharSeedingStatus;
    }
    private String seedingStatusDesc;

    public String getSeedingStatusDesc() {
        return seedingStatusDesc;
    }

    public void setSeedingStatusDesc(String seedingStatusDesc) {
        this.seedingStatusDesc = seedingStatusDesc;
    }
}
