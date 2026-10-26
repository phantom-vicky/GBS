package com.isl.gbm.rbibond.externalservice.dto;

public class PanValResponseDTO {

    private String returnCode;
    private String returnDescription;
    private String responseTime;
    private String reqNo;
    private String pan;
    private String panStatus;
    private String panStatusDescription;
    private String name;
    private String panNameDescription;
    private String fatherName;
    private String dob;
    private String panDobDescription;
    private String returnMessage;

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public String getPanStatusDescription() {
        return panStatusDescription;
    }

    public void setPanStatusDescription(String panStatusDescription) {
        this.panStatusDescription = panStatusDescription;
    }

    public String getPanNameDescription() {
        return panNameDescription;
    }

    public void setPanNameDescription(String panNameDescription) {
        this.panNameDescription = panNameDescription;
    }

    public String getPanDobDescription() {
        return panDobDescription;
    }

    public void setPanDobDescription(String panDobDescription) {
        this.panDobDescription = panDobDescription;
    }

    private String seedingStatus;

    private String seedingStatusDescription;

    public String getSeedingStatusDescription() {
        return seedingStatusDescription;
    }

    public void setSeedingStatusDescription(String seedingStatusDescription) {
        this.seedingStatusDescription = seedingStatusDescription;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnDescription() {
        return returnDescription;
    }

    public void setReturnDescription(String returnDescription) {
        this.returnDescription = returnDescription;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getReqNo() {
        return reqNo;
    }

    public void setReqNo(String reqNo) {
        this.reqNo = reqNo;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSeedingStatus() {
        return seedingStatus;
    }

    public void setSeedingStatus(String seedingStatus) {
        this.seedingStatus = seedingStatus;
    }

}
