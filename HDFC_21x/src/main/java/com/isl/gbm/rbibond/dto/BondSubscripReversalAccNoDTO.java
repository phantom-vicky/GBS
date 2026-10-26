package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.isl.gbm.rbibond.dto.marker.BondSubscripReversalAccNoDTOAdd;
import com.isl.gbm.rbibond.dto.marker.BondSubscripReversalAccNoDTOVerifyDelete;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BondSubscripReversalAccNoDTO {
    private String id;
    private String branchId;
    private String branchName;
    @NotBlank(message = "Scheme Code not Black",groups = {BondSubscripReversalAccNoDTOAdd.class, BondSubscripReversalAccNoDTOVerifyDelete.class} )
    private String schemeCode;
    private String schemeName;
    private String schemeStatus;
    private String sNo;
    private String count;
    private String flag;
    @NotBlank(message = "Reversal ID not Black",groups = {BondSubscripReversalAccNoDTOVerifyDelete.class} )
    private String reversalId;
    @NotBlank(message = "From BLA No not Black",groups = {BondSubscripReversalAccNoDTOAdd.class})
    private String fromBlaNo;
    @NotBlank(message = "To BLA No not Black",groups = {BondSubscripReversalAccNoDTOAdd.class})
    private String toBlaNo;
    @NotBlank(message = "Subscription ID not Black",groups = {BondSubscripReversalAccNoDTOAdd.class})
    private String subscriptionId;
    @NotBlank(message = "To Subscription ID not Black",groups = {BondSubscripReversalAccNoDTOAdd.class})
    private String toSubscriptionId;
    @NotBlank(message = "Subscription Date not Black",groups = {BondSubscripReversalAccNoDTOAdd.class})
    private String subscriptionDate;
    @NotBlank(message = "Subscription Amount not Black",groups = {BondSubscripReversalAccNoDTOAdd.class})
    @Pattern(
            regexp = "^[0-9]+(\\.[0-9]{1,2})?$",
            message = "Subscription Amount must be a positive number with up to two decimal places"
            ,groups = {BondSubscripReversalAccNoDTOAdd.class}
    )
    private String subscriptionAmt;
    @NotBlank(message = "Remarks not Black",groups = {BondSubscripReversalAccNoDTOAdd.class})
    private String remarks;
    private String createdBy;
    private String verifyBy;
    private String verifyStatus;
    private String verifyDate;
    private String createDate;
    private String modifyBy;
    private String modifyDate;
    private String blaNo;
    private String bondHolderName;
    private String modeOfHolding;
    private List<BondSubscripReversalAccNoDTO> cursorOut;
    private String panNum;

    public String getToSubscriptionId() {
        return toSubscriptionId;
    }

    public void setToSubscriptionId(String toSubscriptionId) {
        this.toSubscriptionId = toSubscriptionId;
    }

    public String getPanNum() {
        return panNum;
    }

    public void setPanNum(String panNum) {
        this.panNum = panNum;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSchemeStatus() {
        return schemeStatus;
    }

    public void setSchemeStatus(String schemeStatus) {
        this.schemeStatus = schemeStatus;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<BondSubscripReversalAccNoDTO> getCursorOut() {
        return cursorOut;
    }

    public void setCursorOut(List<BondSubscripReversalAccNoDTO> cursorOut) {
        this.cursorOut = cursorOut;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBondHolderName() {
        return bondHolderName;
    }

    public void setBondHolderName(String bondHolderName) {
        this.bondHolderName = bondHolderName;
    }

    public String getModeOfHolding() {
        return modeOfHolding;
    }

    public void setModeOfHolding(String modeOfHolding) {
        this.modeOfHolding = modeOfHolding;
    }

    public String getBlaNo() {
        return blaNo;
    }

    public void setBlaNo(String blaNo) {
        this.blaNo = blaNo;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo;
    }

    public String getReversalId() {
        return reversalId;
    }

    public void setReversalId(String reversalId) {
        this.reversalId = reversalId;
    }

    public String getFromBlaNo() {
        return fromBlaNo;
    }

    public void setFromBlaNo(String fromBlaNo) {
        this.fromBlaNo = fromBlaNo;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(String subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public String getSubscriptionAmt() {
        return subscriptionAmt;
    }

    public void setSubscriptionAmt(String subscriptionAmt) {
        this.subscriptionAmt = subscriptionAmt;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getToBlaNo() {
        return toBlaNo;
    }

    public void setToBlaNo(String toBlaNo) {
        this.toBlaNo = toBlaNo;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getVerifyBy() {
        return verifyBy;
    }

    public void setVerifyBy(String verifyBy) {
        this.verifyBy = verifyBy;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getVerifyDate() {
        return verifyDate;
    }

    public void setVerifyDate(String verifyDate) {
        this.verifyDate = verifyDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }
}
