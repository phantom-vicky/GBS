package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.isl.gbm.transaction.channel.GbmTransactionMasterDto;

import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BondInwardOutwardDTO {


    private String createdBy;
    private String createdDate;
    private String lastModifiedBy;
    private String lastModifiedDate;
    private String verifiedBy;
    private String verifiedDate;
    private String verifiedStatus;
    private String previousOperationType;
    private String pkColumnValue;
    private String id;

    private String brokerId;
    private String brokerAgencyCode;
    private String brokerName;
    private String gstNo;
    private String pan;
    private String mobileNumber;
    private String emailId;
    private String grossBrokerageAmt;
    private String sgst;
    private String cgst;
    private String igst;
    private String netPayableAmount;
    private String remarks;
    private String fromDate;
    private String toDate;
    private String creditAccNo;
    private String creditAccType;
    private String debitAccNo;
    private String debitAccType;
    private String totalAmount;
    private String flag;

    private String channelType;
    private String cbsTranId;
    private String gbsTranId;
    private String cbsTranStatus;
    private String cbsTranStatusDescription;
    private String transactionType;
    private String tranStatus;
    private String outwardRefNo;
    private String podConsignmentNumber;

    public String getPodConsignmentNumber() {
        return podConsignmentNumber;
    }

    public void setPodConsignmentNumber(String podConsignmentNumber) {
        this.podConsignmentNumber = podConsignmentNumber;
    }

    public String getOutwardRefNo() {
        return outwardRefNo;
    }

    public void setOutwardRefNo(String outwardRefNo) {
        this.outwardRefNo = outwardRefNo;
    }

    public String getTranStatus() {
        return tranStatus;
    }

    public String getIgst() {
        return igst;
    }

    public void setIgst(String igst) {
        this.igst = igst;
    }

    public void setTranStatus(String tranStatus) {
        this.tranStatus = tranStatus;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getBrokerAgencyCode() {
        return brokerAgencyCode;
    }

    public void setBrokerAgencyCode(String brokerAgencyCode) {
        this.brokerAgencyCode = brokerAgencyCode;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getGrossBrokerageAmt() {
        return grossBrokerageAmt;
    }

    public void setGrossBrokerageAmt(String grossBrokerageAmt) {
        this.grossBrokerageAmt = grossBrokerageAmt;
    }

    public String getSgst() {
        return sgst;
    }

    public void setSgst(String sgst) {
        this.sgst = sgst;
    }

    public String getCgst() {
        return cgst;
    }

    public void setCgst(String cgst) {
        this.cgst = cgst;
    }

    public String getNetPayableAmount() {
        return netPayableAmount;
    }

    public void setNetPayableAmount(String netPayableAmount) {
        this.netPayableAmount = netPayableAmount;
    }

    //flg
    private String debitAccNumFlgOut;

    public String getDebitAccNumFlgOut() {
        return debitAccNumFlgOut;
    }

    public void setDebitAccNumFlgOut(String debitAccNumFlgOut) {
        this.debitAccNumFlgOut = debitAccNumFlgOut;
    }

    private List<String> listOfStrings;
    private List<BondInwardOutwardDTO> inwDetailsList;
    private byte[] file;
    private List<Map<String, String>> torchOne;
    private List<Map<String, String>> torchTwo;
    private List<Map<String, String>> torchThree;
    private List<Map<String, String>> torchFour;
    private List<GbmTransactionMasterDto> gbmTransactionMasterDtoList;

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getGbsTranId() {
        return gbsTranId;
    }

    public void setGbsTranId(String gbsTranId) {
        this.gbsTranId = gbsTranId;
    }

    public String getCreditAccType() {
        return creditAccType;
    }

    public void setCreditAccType(String creditAccType) {
        this.creditAccType = creditAccType;
    }

    public String getDebitAccType() {
        return debitAccType;
    }

    public void setDebitAccType(String debitAccType) {
        this.debitAccType = debitAccType;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<BondInwardOutwardDTO> getInwDetailsList() {
        return inwDetailsList;
    }

    public void setInwDetailsList(List<BondInwardOutwardDTO> inwDetailsList) {
        this.inwDetailsList = inwDetailsList;
    }

    public String getCreditAccNo() {
        return creditAccNo;
    }

    public void setCreditAccNo(String creditAccNo) {
        this.creditAccNo = creditAccNo;
    }

    public String getDebitAccNo() {
        return debitAccNo;
    }

    public void setDebitAccNo(String debitAccNo) {
        this.debitAccNo = debitAccNo;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public String getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(String verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    public String getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setVerifiedStatus(String verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    public String getPreviousOperationType() {
        return previousOperationType;
    }

    public void setPreviousOperationType(String previousOperationType) {
        this.previousOperationType = previousOperationType;
    }

    public String getPkColumnValue() {
        return pkColumnValue;
    }

    public void setPkColumnValue(String pkColumnValue) {
        this.pkColumnValue = pkColumnValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBlaNo() {
        return blaNo;
    }

    public void setBlaNo(String blaNo) {
        this.blaNo = blaNo;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getCbsTranId() {
        return cbsTranId;
    }

    public void setCbsTranId(String cbsTranId) {
        this.cbsTranId = cbsTranId;
    }

    public String getCbsTranStatus() {
        return cbsTranStatus;
    }

    public void setCbsTranStatus(String cbsTranStatus) {
        this.cbsTranStatus = cbsTranStatus;
    }

    public String getCbsTranStatusDescription() {
        return cbsTranStatusDescription;
    }

    public void setCbsTranStatusDescription(String cbsTranStatusDescription) {
        this.cbsTranStatusDescription = cbsTranStatusDescription;
    }


    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public List<String> getListOfStrings() {
        return listOfStrings;
    }

    public void setListOfStrings(List<String> listOfStrings) {
        this.listOfStrings = listOfStrings;
    }

    public List<Map<String, String>> getTorchOne() {
        return torchOne;
    }

    public void setTorchOne(List<Map<String, String>> torchOne) {
        this.torchOne = torchOne;
    }

    public List<Map<String, String>> getTorchTwo() {
        return torchTwo;
    }

    public void setTorchTwo(List<Map<String, String>> torchTwo) {
        this.torchTwo = torchTwo;
    }

    public List<Map<String, String>> getTorchThree() {
        return torchThree;
    }

    public void setTorchThree(List<Map<String, String>> torchThree) {
        this.torchThree = torchThree;
    }

    public List<Map<String, String>> getTorchFour() {
        return torchFour;
    }

    public void setTorchFour(List<Map<String, String>> torchFour) {
        this.torchFour = torchFour;
    }

    public List<GbmTransactionMasterDto> getGbmTransactionMasterDtoList() {
        return gbmTransactionMasterDtoList;
    }

    public void setGbmTransactionMasterDtoList(List<GbmTransactionMasterDto> gbmTransactionMasterDtoList) {
        this.gbmTransactionMasterDtoList = gbmTransactionMasterDtoList;
    }
    private String screenType;
    private String requestType;
    private String uniqueRefNo;
    private String schemeCode;
    private String schemeName;
    private String ftsAppNum;
    private String nameOfTheBondHolder;
    private String userName;
    private String panNo;
    private String blaNo;
    private String boSubsId;
    private String desc;
    private String inwDate;
    private String inwardRemarks;
    private String inRefNo;
    private String requestCode;
    private String requestDesc;
    private String modeOfHolding;
    private String brokerCode;

    public String getBrokerCode() {
        return brokerCode;
    }

    public void setBrokerCode(String brokerCode) {
        this.brokerCode = brokerCode;
    }

    public String getInwardRemarks() {
        return inwardRemarks;
    }

    public void setInwardRemarks(String inwardRemarks) {
        this.inwardRemarks = inwardRemarks;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getModeOfHolding() {
        return modeOfHolding;
    }

    public void setModeOfHolding(String modeOfHolding) {
        this.modeOfHolding = modeOfHolding;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public String getRequestDesc() {
        return requestDesc;
    }

    public void setRequestDesc(String requestDesc) {
        this.requestDesc = requestDesc;
    }

    public String getScreenType() {
        return screenType;
    }

    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getUniqueRefNo() {
        return uniqueRefNo;
    }

    public void setUniqueRefNo(String uniqueRefNo) {
        this.uniqueRefNo = uniqueRefNo;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getFtsAppNum() {
        return ftsAppNum;
    }

    public void setFtsAppNum(String ftsAppNum) {
        this.ftsAppNum = ftsAppNum;
    }

    public String getNameOfTheBondHolder() {
        return nameOfTheBondHolder;
    }

    public void setNameOfTheBondHolder(String nameOfTheBondHolder) {
        this.nameOfTheBondHolder = nameOfTheBondHolder;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getBoSubsId() {
        return boSubsId;
    }

    public void setBoSubsId(String boSubsId) {
        this.boSubsId = boSubsId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getInwDate() {
        return inwDate;
    }

    public void setInwDate(String inwDate) {
        this.inwDate = inwDate;
    }

    public String getInRefNo() {
        return inRefNo;
    }

    public void setInRefNo(String inRefNo) {
        this.inRefNo = inRefNo;
    }
}
