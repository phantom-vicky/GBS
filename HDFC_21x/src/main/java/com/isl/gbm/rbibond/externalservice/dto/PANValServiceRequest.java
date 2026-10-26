package com.isl.gbm.rbibond.externalservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "PANValServiceRequest", namespace = "panval.isd.xsd.hdfcbank.com")
@XmlType(propOrder = {"serviceUser", "servicePassword", "consumerName", "nsdlUserName", "panDetails", "reqDateTime", "reqNo", "costCenterNo", "filler1", "filler2", "filler3", "filler4", "filler5"})
public class PANValServiceRequest {

    private String serviceUser;
    private String servicePassword;
    @NotNull
    private String consumerName;
    @NotNull
    private String nsdlUserName;
    private PANDetails panDetails;
    @NotNull
    private String reqDateTime;
    @NotNull
    private String reqNo;
    @NotNull
    private String costCenterNo;
    private String filler1;
    private String filler2;
    private String filler3;
    private String filler4;
    private String filler5;

    @XmlElement(name = "Service_User")
    public String getServiceUser() {
        return serviceUser;
    }

    public void setServiceUser(String serviceUser) {
        this.serviceUser = serviceUser;
    }

    @XmlElement(name = "Service_Password")
    public String getServicePassword() {
        return servicePassword;
    }

    public void setServicePassword(String servicePassword) {
        this.servicePassword = servicePassword;
    }

    @XmlElement(name = "Consumer_Name")
    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    @XmlElement(name = "NSDL_User_Name")
    public String getNsdlUserName() {
        return nsdlUserName;
    }

    public void setNsdlUserName(String nsdlUserName) {
        this.nsdlUserName = nsdlUserName;
    }

    @XmlElement(name = "PAN_Details")
    public PANDetails getPanDetails() {
        return panDetails;
    }

    public void setPanDetails(PANDetails panDetails) {
        this.panDetails = panDetails;
    }

    @XmlElement(name = "Req_Date_Time")
    public String getReqDateTime() {
        return reqDateTime;
    }

    public void setReqDateTime(String reqDateTime) {
        this.reqDateTime = reqDateTime;
    }

    @XmlElement(name = "Req_No")
    public String getReqNo() {
        return reqNo;
    }

    public void setReqNo(String reqNo) {
        this.reqNo = reqNo;
    }

    @XmlElement(name = "Cost_Center_No")
    public String getCostCenterNo() {
        return costCenterNo;
    }

    public void setCostCenterNo(String costCenterNo) {
        this.costCenterNo = costCenterNo;
    }

    @XmlElement(name = "Filler1")
    public String getFiller1() {
        return filler1;
    }

    public void setFiller1(String filler1) {
        this.filler1 = filler1;
    }

    @XmlElement(name = "Filler2")
    public String getFiller2() {
        return filler2;
    }

    public void setFiller2(String filler2) {
        this.filler2 = filler2;
    }

    @XmlElement(name = "Filler3")
    public String getFiller3() {
        return filler3;
    }

    public void setFiller3(String filler3) {
        this.filler3 = filler3;
    }

    @XmlElement(name = "Filler4")
    public String getFiller4() {
        return filler4;
    }

    public void setFiller4(String filler4) {
        this.filler4 = filler4;
    }

    @XmlElement(name = "Filler5")
    public String getFiller5() {
        return filler5;
    }

    public void setFiller5(String filler5) {
        this.filler5 = filler5;
    }
}

