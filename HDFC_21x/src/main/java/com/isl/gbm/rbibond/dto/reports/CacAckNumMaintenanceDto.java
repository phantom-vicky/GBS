package com.isl.gbm.rbibond.dto.reports;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.isl.gbm.common.dto.ColumnValueDTO;

import jakarta.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CacAckNumMaintenanceDto {
    @NotBlank(message = "Sub Date Should Not Be Blank ")
    private String subsDate;
    private String subsId;
    private String schemeName;
    @NotBlank(message = "Scheme Code Should Not Be Blank ")
    private String schemeCode;
    private String subsFlg;
    private String bondLedgeAccNum;
    private String holderName;
    private String subsAmt;
    private String bondType;
    private String brkAdviceNumber;



    @NotBlank(message = "CAS ACK NUM Should Not Be Blank ")
    private String casAckNum;

    @NotBlank(message = "CAS ACK DATE Should Not Be Blank ")
    private String casAckDate;
    @NotBlank(message = "CAS TRAN DATE Should Not Be Blank ")
    private String casTranDate;
    private String dateFlg;
    private String subsIdFlg;
    private String flg;
    private String checkBox;
    private String model1;
    private List<Map<String, Object>> oldNewValueList;
    private String blaNum;

    public String getBrkAdviceNumber() {
        return brkAdviceNumber;
    }

    public void setBrkAdviceNumber(String brkAdviceNumber) {
        this.brkAdviceNumber = brkAdviceNumber;
    }

    public String getTotalSubScriptionAmt() {
        return totalSubScriptionAmt;
    }

    public void setTotalSubScriptionAmt(String totalSubScriptionAmt) {
        this.totalSubScriptionAmt = totalSubScriptionAmt;
    }

    private String flgg;
    private String referenceId;
    private String  totalSubScriptionAmt;
    private List<CacAckNumMaintenanceDto> list;
    List<HashMap<String, String>> cacAckNumMaintenanceList;
    private List<HashMap<String, String>> cacAckNumMaintenanceListt;

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public List<CacAckNumMaintenanceDto> getList() {
        return list;
    }

    public void setList(List<CacAckNumMaintenanceDto> list) {
        this.list = list;
    }

    public List<HashMap<String, String>> getCacAckNumMaintenanceListt() {
        return cacAckNumMaintenanceListt;
    }

    public void setCacAckNumMaintenanceListt(List<HashMap<String, String>> cacAckNumMaintenanceListt) {
        this.cacAckNumMaintenanceListt = cacAckNumMaintenanceListt;
    }

    public List<HashMap<String, String>> getCacAckNumMaintenanceList() {
        return cacAckNumMaintenanceList;
    }

    public void setCacAckNumMaintenanceList(List<HashMap<String, String>> cacAckNumMaintenanceList) {
        this.cacAckNumMaintenanceList = cacAckNumMaintenanceList;
    }

    private String createdBy;
    private String createdDate;
    private String lastModifiedBy;
    private String lastModifiedDate;
    private String verifiedBy;
    private String verifiedDate;
    private String verifiedStatus;
    private String deleteRemarks;
    private String previousOperationType;
    private String pkColumnValue;

    public String getDeleteRemarks() {
        return deleteRemarks;
    }

    public void setDeleteRemarks(String deleteRemarks) {
        this.deleteRemarks = deleteRemarks;
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

    public String getFlgg() {
        return flgg;
    }

    public void setFlgg(String flgg) {
        this.flgg = flgg;
    }

    public String getModel1() {
        return model1;
    }

    public void setModel1(String model1) {
        this.model1 = model1;
    }

    public String getBlaNum() {
        return blaNum;
    }

    public void setBlaNum(String blaNum) {
        this.blaNum = blaNum;
    }

    private List<List<ColumnValueDTO>> columnValueDetailsList;

    public List<List<ColumnValueDTO>> getColumnValueDetailsList() {
        return columnValueDetailsList;
    }

    public void setColumnValueDetailsList(List<List<ColumnValueDTO>> columnValueDetailsList) {
        this.columnValueDetailsList = columnValueDetailsList;
    }

    public List<Map<String, Object>> getOldNewValueList() {
        return oldNewValueList;
    }

    public void setOldNewValueList(List<Map<String, Object>> oldNewValueList) {
        this.oldNewValueList = oldNewValueList;
    }

    public String getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(String checkBox) {
        this.checkBox = checkBox;
    }

    private List<CacAckNumMaintenanceDto> cacAckNumList;

    public List<CacAckNumMaintenanceDto> getCacAckNumList() {
        return cacAckNumList;
    }

    public void setCacAckNumList(List<CacAckNumMaintenanceDto> cacAckNumList) {
        this.cacAckNumList = cacAckNumList;
    }

    public String getDateFlg() {
        return dateFlg;
    }

    public void setDateFlg(String dateFlg) {
        this.dateFlg = dateFlg;
    }

    public String getSubsIdFlg() {
        return subsIdFlg;
    }

    public void setSubsIdFlg(String subsIdFlg) {
        this.subsIdFlg = subsIdFlg;
    }

    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public String getCasAckNum() {
        return casAckNum;
    }

    public void setCasAckNum(String casAckNum) {
        this.casAckNum = casAckNum;
    }

    public String getCasAckDate() {
        return casAckDate;
    }

    public void setCasAckDate(String casAckDate) {
        this.casAckDate = casAckDate;
    }

    public String getBondLedgeAccNum() {
        return bondLedgeAccNum;
    }

    public void setBondLedgeAccNum(String bondLedgeAccNum) {
        this.bondLedgeAccNum = bondLedgeAccNum;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getSubsAmt() {
        return subsAmt;
    }

    public void setSubsAmt(String subsAmt) {
        this.subsAmt = subsAmt;
    }

    public String getBondType() {
        return bondType;
    }

    public void setBondType(String bondType) {
        this.bondType = bondType;
    }

    public String getSubsDate() {
        return subsDate;
    }

    public void setSubsDate(String subsDate) {
        this.subsDate = subsDate;
    }

    public String getSubsId() {
        return subsId;
    }

    public void setSubsId(String subsId) {
        this.subsId = subsId;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getSubsFlg() {
        return subsFlg;
    }

    public void setSubsFlg(String subsFlg) {
        this.subsFlg = subsFlg;
    }

    public String getCasTranDate() {
        return casTranDate;
    }

    public void setCasTranDate(String casTranDate) {
        this.casTranDate = casTranDate;
    }
}
