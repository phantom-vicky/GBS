package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RBIBondPaymentFileDetails {

    private String gbmDestPath;
    private List<String> fileNameList;
    private String message;

    @Override
    public String toString() {
        return "RBIBondPaymentFileDetails{" +
                "gbmDestPath='" + gbmDestPath + '\'' +
                "message='" + message + '\'' +
                ", fileNameList=" + fileNameList +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGbmDestPath() {
        return gbmDestPath;
    }

    public void setGbmDestPath(String gbmDestPath) {
        this.gbmDestPath = gbmDestPath;
    }

    public List<String> getFileNameList() {
        return fileNameList;
    }

    public void setFileNameList(List<String> fileNameList) {
        this.fileNameList = fileNameList;
    }
}
