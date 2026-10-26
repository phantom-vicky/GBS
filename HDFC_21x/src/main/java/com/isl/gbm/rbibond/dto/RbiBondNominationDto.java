package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.isl.gbm.common.dto.ColumnValueDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RbiBondNominationDto {

    @NotBlank(message = "BLA NO not blank")
    private String blaNo;
    private String bondSubsId;
//    private String nameOfTheBondHolder;
    private String singleBondLedgerAccNum;
    private String firstJointHolderBlaNo;
    private String firstJointHolderName;
    private String secJointHolderBlaNo;
    private String secJointHolderName;
    private String accOpenDate;
    private String maturityDate;
    private String jointHolderType;
    private String modeOfHolding;
    private String modeOfHolding1;
    private String verifiedStatus;
    private String subsAmount;
    private String jointHolderName;
    private String pkColValue;
    private String previousOperationType;
    private String moduleId;
    private String modifiedBy;
    private String modifiedDate;
    private String verifiedBy;
    private String verifiedDate;
    private String createdBy;
    private String createdDate;
    private String nominationId;
    private String schemeCode;
    private String schemeName;

    private String nomineeFName;
    private String nomineeMName;
    private String nomineeLName;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String pinCode;
    private String phoneNo;

    private String sharePer;
    private String operativeAccNo;
    private String operativeAccBranchId;

    private String isNomineeMinor;


    private String relationWithHolder;
    private String dobOfMinor;
    private String guardFName;
    private String guardMName;
    private String guardLName;
    private String guardAddress1;
    private String guardAddress2;
    private String guardCity;
    private String guardState;
    private String guardPinCode;
    private String guardMobileNo;
    private String guardEmailId;
    private String bondHolderName;
    private String  panNum;
    private String subscriptionId;
    private String deleteRemark;
    private String subDesc;
    private String preOrpTyp;
    private String schemeStatus;
    private String nomineeStatusId;
    private String id;
    private String blaid;
    private String blaNoFlag;
    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getBlaNoFlag() {
        return blaNoFlag;
    }

    public void setBlaNoFlag(String blaNoFlag) {
        this.blaNoFlag = blaNoFlag;
    }

    private List<RbiBondNominationDto> RbiSchemeList;
    private List<RbiBondNominationDto> rbiBondNominationDtoSubsIdList;
    private List<RbiBondNominationDto> rbiBondNominationDtoNomDetList;
    public List<RbiBondNominationDto> getRbiSchemeList() {
        return RbiSchemeList;
    }

    public void setRbiSchemeList(List<RbiBondNominationDto> rbiSchemeList) {
        RbiSchemeList = rbiSchemeList;
    }

    private List<RbiBondNominationDto> RbiBondNominationDtoList;

    public List<RbiBondNominationDto> getRbiBondNominationDtoList() {
        return RbiBondNominationDtoList;
    }

    public void setRbiBondNominationDtoList(List<RbiBondNominationDto> rbiBondNominationDtoList) {
        RbiBondNominationDtoList = rbiBondNominationDtoList;
    }

    private List<Map<String, Object>> oldNewValueMap=new ArrayList<Map<String, Object>>();

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    private List<Map<String, Object>> oldNewValueList;

    private List<HashMap<String, String>> torchValue;

    private List<List<ColumnValueDTO>> nominationAddDtoList;
    private List<List<ColumnValueDTO>> nominationModifyDtoList;
    private List<List<ColumnValueDTO>> nominationDelDtoList;



    private Map<String, Object> masterOldNewValueModel;

    public String getBlaNo() {
        return blaNo;
    }

    public void setBlaNo(String blaNo) {
        this.blaNo = blaNo;
    }

    public String getBondSubsId() {
        return bondSubsId;
    }

    public void setBondSubsId(String bondSubsId) {
        this.bondSubsId = bondSubsId;
    }

    public String getSingleBondLedgerAccNum() {
        return singleBondLedgerAccNum;
    }

    public void setSingleBondLedgerAccNum(String singleBondLedgerAccNum) {
        this.singleBondLedgerAccNum = singleBondLedgerAccNum;
    }

    public String getFirstJointHolderBlaNo() {
        return firstJointHolderBlaNo;
    }

    public void setFirstJointHolderBlaNo(String firstJointHolderBlaNo) {
        this.firstJointHolderBlaNo = firstJointHolderBlaNo;
    }

    public String getFirstJointHolderName() {
        return firstJointHolderName;
    }

    public void setFirstJointHolderName(String firstJointHolderName) {
        this.firstJointHolderName = firstJointHolderName;
    }

    public String getSecJointHolderBlaNo() {
        return secJointHolderBlaNo;
    }

    public void setSecJointHolderBlaNo(String secJointHolderBlaNo) {
        this.secJointHolderBlaNo = secJointHolderBlaNo;
    }

    public String getSecJointHolderName() {
        return secJointHolderName;
    }

    public void setSecJointHolderName(String secJointHolderName) {
        this.secJointHolderName = secJointHolderName;
    }

    public String getAccOpenDate() {
        return accOpenDate;
    }

    public void setAccOpenDate(String accOpenDate) {
        this.accOpenDate = accOpenDate;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getJointHolderType() {
        return jointHolderType;
    }

    public void setJointHolderType(String jointHolderType) {
        this.jointHolderType = jointHolderType;
    }

    public String getModeOfHolding() {
        return modeOfHolding;
    }

    public void setModeOfHolding(String modeOfHolding) {
        this.modeOfHolding = modeOfHolding;
    }

    public String getModeOfHolding1() {
        return modeOfHolding1;
    }

    public void setModeOfHolding1(String modeOfHolding1) {
        this.modeOfHolding1 = modeOfHolding1;
    }

    public String getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setVerifiedStatus(String verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    public String getSubsAmount() {
        return subsAmount;
    }

    public void setSubsAmount(String subsAmount) {
        this.subsAmount = subsAmount;
    }

    public String getJointHolderName() {
        return jointHolderName;
    }

    public void setJointHolderName(String jointHolderName) {
        this.jointHolderName = jointHolderName;
    }

    public String getPkColValue() {
        return pkColValue;
    }

    public void setPkColValue(String pkColValue) {
        this.pkColValue = pkColValue;
    }

    public String getPreviousOperationType() {
        return previousOperationType;
    }

    public void setPreviousOperationType(String previousOperationType) {
        this.previousOperationType = previousOperationType;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
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

    public String getNominationId() {
        return nominationId;
    }

    public void setNominationId(String nominationId) {
        this.nominationId = nominationId;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getNomineeFName() {
        return nomineeFName;
    }

    public void setNomineeFName(String nomineeFName) {
        this.nomineeFName = nomineeFName;
    }

    public String getNomineeMName() {
        return nomineeMName;
    }

    public void setNomineeMName(String nomineeMName) {
        this.nomineeMName = nomineeMName;
    }

    public String getNomineeLName() {
        return nomineeLName;
    }

    public void setNomineeLName(String nomineeLName) {
        this.nomineeLName = nomineeLName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getSharePer() {
        return sharePer;
    }

    public void setSharePer(String sharePer) {
        this.sharePer = sharePer;
    }

    public String getOperativeAccNo() {
        return operativeAccNo;
    }

    public void setOperativeAccNo(String operativeAccNo) {
        this.operativeAccNo = operativeAccNo;
    }

    public String getOperativeAccBranchId() {
        return operativeAccBranchId;
    }

    public void setOperativeAccBranchId(String operativeAccBranchId) {
        this.operativeAccBranchId = operativeAccBranchId;
    }

    public String getIsNomineeMinor() {
        return isNomineeMinor;
    }

    public void setIsNomineeMinor(String isNomineeMinor) {
        this.isNomineeMinor = isNomineeMinor;
    }

    public String getRelationWithHolder() {
        return relationWithHolder;
    }

    public void setRelationWithHolder(String relationWithHolder) {
        this.relationWithHolder = relationWithHolder;
    }

    public String getDobOfMinor() {
        return dobOfMinor;
    }

    public void setDobOfMinor(String dobOfMinor) {
        this.dobOfMinor = dobOfMinor;
    }

    public String getGuardFName() {
        return guardFName;
    }

    public void setGuardFName(String guardFName) {
        this.guardFName = guardFName;
    }

    public String getGuardMName() {
        return guardMName;
    }

    public void setGuardMName(String guardMName) {
        this.guardMName = guardMName;
    }

    public String getGuardLName() {
        return guardLName;
    }

    public void setGuardLName(String guardLName) {
        this.guardLName = guardLName;
    }

    public String getGuardAddress1() {
        return guardAddress1;
    }

    public void setGuardAddress1(String guardAddress1) {
        this.guardAddress1 = guardAddress1;
    }

    public String getGuardAddress2() {
        return guardAddress2;
    }

    public void setGuardAddress2(String guardAddress2) {
        this.guardAddress2 = guardAddress2;
    }

    public String getGuardCity() {
        return guardCity;
    }

    public void setGuardCity(String guardCity) {
        this.guardCity = guardCity;
    }

    public String getGuardState() {
        return guardState;
    }

    public void setGuardState(String guardState) {
        this.guardState = guardState;
    }

    public String getGuardPinCode() {
        return guardPinCode;
    }

    public void setGuardPinCode(String guardPinCode) {
        this.guardPinCode = guardPinCode;
    }

    public String getGuardMobileNo() {
        return guardMobileNo;
    }

    public void setGuardMobileNo(String guardMobileNo) {
        this.guardMobileNo = guardMobileNo;
    }

    public String getGuardEmailId() {
        return guardEmailId;
    }

    public void setGuardEmailId(String guardEmailId) {
        this.guardEmailId = guardEmailId;
    }

    public String getBondHolderName() {
        return bondHolderName;
    }

    public void setBondHolderName(String bondHolderName) {
        this.bondHolderName = bondHolderName;
    }

    public String getPanNum() {
        return panNum;
    }

    public void setPanNum(String panNum) {
        this.panNum = panNum;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getDeleteRemark() {
        return deleteRemark;
    }

    public void setDeleteRemark(String deleteRemark) {
        this.deleteRemark = deleteRemark;
    }

    public String getSubDesc() {
        return subDesc;
    }

    public void setSubDesc(String subDesc) {
        this.subDesc = subDesc;
    }

    public String getPreOrpTyp() {
        return preOrpTyp;
    }

    public void setPreOrpTyp(String preOrpTyp) {
        this.preOrpTyp = preOrpTyp;
    }

    public String getSchemeStatus() {
        return schemeStatus;
    }

    public void setSchemeStatus(String schemeStatus) {
        this.schemeStatus = schemeStatus;
    }

    public String getNomineeStatusId() {
        return nomineeStatusId;
    }

    public void setNomineeStatusId(String nomineeStatusId) {
        this.nomineeStatusId = nomineeStatusId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBlaid() {
        return blaid;
    }

    public void setBlaid(String blaid) {
        this.blaid = blaid;
    }

    public List<Map<String, Object>> getOldNewValueMap() {
        return oldNewValueMap;
    }

    public void setOldNewValueMap(List<Map<String, Object>> oldNewValueMap) {
        this.oldNewValueMap = oldNewValueMap;
    }

    public List<Map<String, Object>> getOldNewValueList() {
        return oldNewValueList;
    }

    public void setOldNewValueList(List<Map<String, Object>> oldNewValueList) {
        this.oldNewValueList = oldNewValueList;
    }

    public List<HashMap<String, String>> getTorchValue() {
        return torchValue;
    }

    public void setTorchValue(List<HashMap<String, String>> torchValue) {
        this.torchValue = torchValue;
    }

    public List<List<ColumnValueDTO>> getNominationAddDtoList() {
        return nominationAddDtoList;
    }

    public void setNominationAddDtoList(List<List<ColumnValueDTO>> nominationAddDtoList) {
        this.nominationAddDtoList = nominationAddDtoList;
    }

    public List<List<ColumnValueDTO>> getNominationModifyDtoList() {
        return nominationModifyDtoList;
    }

    public void setNominationModifyDtoList(List<List<ColumnValueDTO>> nominationModifyDtoList) {
        this.nominationModifyDtoList = nominationModifyDtoList;
    }

    public List<List<ColumnValueDTO>> getNominationDelDtoList() {
        return nominationDelDtoList;
    }

    public void setNominationDelDtoList(List<List<ColumnValueDTO>> nominationDelDtoList) {
        this.nominationDelDtoList = nominationDelDtoList;
    }

    public Map<String, Object> getMasterOldNewValueModel() {
        return masterOldNewValueModel;
    }

    public void setMasterOldNewValueModel(Map<String, Object> masterOldNewValueModel) {
        this.masterOldNewValueModel = masterOldNewValueModel;
    }

    public List<RbiBondNominationDto> getRbiBondNominationDtoSubsIdList() {
        return rbiBondNominationDtoSubsIdList;
    }

    public void setRbiBondNominationDtoSubsIdList(List<RbiBondNominationDto> rbiBondNominationDtoSubsIdList) {
        this.rbiBondNominationDtoSubsIdList = rbiBondNominationDtoSubsIdList;
    }

    public List<RbiBondNominationDto> getRbiBondNominationDtoNomDetList() {
        return rbiBondNominationDtoNomDetList;
    }

    public void setRbiBondNominationDtoNomDetList(List<RbiBondNominationDto> rbiBondNominationDtoNomDetList) {
        this.rbiBondNominationDtoNomDetList = rbiBondNominationDtoNomDetList;
    }
}
