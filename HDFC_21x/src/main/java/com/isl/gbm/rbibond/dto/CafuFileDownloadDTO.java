package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CafuFileDownloadDTO {

    private String moduleName;
    private String  schemeName;
    @NotBlank(message = "File Type must")
    private String fileType;
    @NotBlank(message = "File Mode must")
    private String fileMode;
    @NotBlank(message = "Liquidation Date must")
    private String liquidationDate;
    @NotBlank(message = "Scheme Code must")
    private String subCode;
    private String subCodeDesc;
    private String schemeStatus;
    private String fileData;
    private String fileName;


    public String getFileMode() {
        return fileMode;
    }

    public void setFileMode(String fileMode) {
        this.fileMode = fileMode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubCodeDesc() {
        return subCodeDesc;
    }

    public void setSubCodeDesc(String subCodeDesc) {
        this.subCodeDesc = subCodeDesc;
    }

    public String getSchemeStatus() {
        return schemeStatus;
    }

    public void setSchemeStatus(String schemeStatus) {
        this.schemeStatus = schemeStatus;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getLiquidationDate() {
        return liquidationDate;
    }

    public void setLiquidationDate(String liquidationDate) {
        this.liquidationDate = liquidationDate;
    }


    public String getFileData() {
        return fileData;
    }

    public void setFileData(String fileData) {
        this.fileData = fileData;
    }

}
