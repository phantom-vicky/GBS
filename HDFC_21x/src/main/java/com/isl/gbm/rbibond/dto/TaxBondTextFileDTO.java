package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaxBondTextFileDTO {

    // Main Transaction Details
    private String tranType;
    private String entityId;
    private String typeOfEntry;
    private String debitCreditFlag;
    private String accEntryAmount;
    private String valueDate;
    private String postingDate;
    private String productCode;
    private String pickupLocation;
    private String pickupPoint;
    private String pickupDate;
    private String depositSlipNmbr;
    private String depositDate;
    private String depositAmnt;
    private String noOfInst;
    private String depositRemarks;
    private String instNmbr;
    private String draweeBankDescription;
    private String locDescription;
    private String instrumentAmnt;
    private String instrumentDate;
    private String drawerName;

    // Applicant Details
    private String applicantName;
    private String operatingInstructions;
    private String checkListNumber;
    private String coh;
    private String scheme;
    private String bla;
    private String type;
    private String pan;
    private String email;
    private String bankName;
    private String acctNo;
    private String ifsc;
    private String mobile;
    private String brokerCode;
    private String gender;
    private String collectionBranch;
    private String communicationAddress;
    private String dateOfBirth;
    private String applicantStatus;
    private String modeOfInvestment;
    private String nameAndAddressNomination;
    private String fullName;
    private String dob;
    private String relationshipToHolder;
    private String nomineePercentage;
    private String nameNominationInCaseOfMinor;
    private String nominationDob;
    private String nameInFullSecondApplicants;
    private String secondApplicantsGender;
    private String secondApplicantsPan;
    private String nameInFullGuardianDetails;
    private String relationshipWithMinor;
    private String guardianGender;
    private String guardianCommunicationAddress;
    private String header;

    public String getBrokerCode() {
        return brokerCode;
    }

    public void setBrokerCode(String brokerCode) {
        this.brokerCode = brokerCode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCollectionBranch() {
        return collectionBranch;
    }

    public void setCollectionBranch(String collectionBranch) {
        this.collectionBranch = collectionBranch;
    }

    public String getCommunicationAddress() {
        return communicationAddress;
    }

    public void setCommunicationAddress(String communicationAddress) {
        this.communicationAddress = communicationAddress;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getApplicantStatus() {
        return applicantStatus;
    }

    public void setApplicantStatus(String applicantStatus) {
        this.applicantStatus = applicantStatus;
    }

    public String getModeOfInvestment() {
        return modeOfInvestment;
    }

    public void setModeOfInvestment(String modeOfInvestment) {
        this.modeOfInvestment = modeOfInvestment;
    }

    public String getNameAndAddressNomination() {
        return nameAndAddressNomination;
    }

    public void setNameAndAddressNomination(String nameAndAddressNomination) {
        this.nameAndAddressNomination = nameAndAddressNomination;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getRelationshipToHolder() {
        return relationshipToHolder;
    }

    public void setRelationshipToHolder(String relationshipToHolder) {
        this.relationshipToHolder = relationshipToHolder;
    }

    public String getNomineePercentage() {
        return nomineePercentage;
    }

    public void setNomineePercentage(String nomineePercentage) {
        this.nomineePercentage = nomineePercentage;
    }

    public String getNameNominationInCaseOfMinor() {
        return nameNominationInCaseOfMinor;
    }

    public void setNameNominationInCaseOfMinor(String nameNominationInCaseOfMinor) {
        this.nameNominationInCaseOfMinor = nameNominationInCaseOfMinor;
    }

    public String getNominationDob() {
        return nominationDob;
    }

    public void setNominationDob(String nominationDob) {
        this.nominationDob = nominationDob;
    }

    public String getNameInFullSecondApplicants() {
        return nameInFullSecondApplicants;
    }

    public void setNameInFullSecondApplicants(String nameInFullSecondApplicants) {
        this.nameInFullSecondApplicants = nameInFullSecondApplicants;
    }

    public String getSecondApplicantsGender() {
        return secondApplicantsGender;
    }

    public void setSecondApplicantsGender(String secondApplicantsGender) {
        this.secondApplicantsGender = secondApplicantsGender;
    }

    public String getSecondApplicantsPan() {
        return secondApplicantsPan;
    }

    public void setSecondApplicantsPan(String secondApplicantsPan) {
        this.secondApplicantsPan = secondApplicantsPan;
    }

    public String getNameInFullGuardianDetails() {
        return nameInFullGuardianDetails;
    }

    public void setNameInFullGuardianDetails(String nameInFullGuardianDetails) {
        this.nameInFullGuardianDetails = nameInFullGuardianDetails;
    }

    public String getRelationshipWithMinor() {
        return relationshipWithMinor;
    }

    public void setRelationshipWithMinor(String relationshipWithMinor) {
        this.relationshipWithMinor = relationshipWithMinor;
    }

    public String getGuardianGender() {
        return guardianGender;
    }

    public void setGuardianGender(String guardianGender) {
        this.guardianGender = guardianGender;
    }

    public String getGuardianCommunicationAddress() {
        return guardianCommunicationAddress;
    }

    public void setGuardianCommunicationAddress(String guardianCommunicationAddress) {
        this.guardianCommunicationAddress = guardianCommunicationAddress;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getTypeOfEntry() {
        return typeOfEntry;
    }

    public void setTypeOfEntry(String typeOfEntry) {
        this.typeOfEntry = typeOfEntry;
    }

    public String getDebitCreditFlag() {
        return debitCreditFlag;
    }

    public void setDebitCreditFlag(String debitCreditFlag) {
        this.debitCreditFlag = debitCreditFlag;
    }

    public String getAccEntryAmount() {
        return accEntryAmount;
    }

    public void setAccEntryAmount(String accEntryAmount) {
        this.accEntryAmount = accEntryAmount;
    }

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    public String getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(String postingDate) {
        this.postingDate = postingDate;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getPickupPoint() {
        return pickupPoint;
    }

    public void setPickupPoint(String pickupPoint) {
        this.pickupPoint = pickupPoint;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getDepositSlipNmbr() {
        return depositSlipNmbr;
    }

    public void setDepositSlipNmbr(String depositSlipNmbr) {
        this.depositSlipNmbr = depositSlipNmbr;
    }

    public String getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(String depositDate) {
        this.depositDate = depositDate;
    }

    public String getDepositAmnt() {
        return depositAmnt;
    }

    public void setDepositAmnt(String depositAmnt) {
        this.depositAmnt = depositAmnt;
    }

    public String getNoOfInst() {
        return noOfInst;
    }

    public void setNoOfInst(String noOfInst) {
        this.noOfInst = noOfInst;
    }

    public String getDepositRemarks() {
        return depositRemarks;
    }

    public void setDepositRemarks(String depositRemarks) {
        this.depositRemarks = depositRemarks;
    }

    public String getInstNmbr() {
        return instNmbr;
    }

    public void setInstNmbr(String instNmbr) {
        this.instNmbr = instNmbr;
    }

    public String getDraweeBankDescription() {
        return draweeBankDescription;
    }

    public void setDraweeBankDescription(String draweeBankDescription) {
        this.draweeBankDescription = draweeBankDescription;
    }

    public String getLocDescription() {
        return locDescription;
    }

    public void setLocDescription(String locDescription) {
        this.locDescription = locDescription;
    }

    public String getInstrumentAmnt() {
        return instrumentAmnt;
    }

    public void setInstrumentAmnt(String instrumentAmnt) {
        this.instrumentAmnt = instrumentAmnt;
    }

    public String getInstrumentDate() {
        return instrumentDate;
    }

    public void setInstrumentDate(String instrumentDate) {
        this.instrumentDate = instrumentDate;
    }

    public String getDrawerName() {
        return drawerName;
    }

    public void setDrawerName(String drawerName) {
        this.drawerName = drawerName;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getOperatingInstructions() {
        return operatingInstructions;
    }

    public void setOperatingInstructions(String operatingInstructions) {
        this.operatingInstructions = operatingInstructions;
    }

    public String getCheckListNumber() {
        return checkListNumber;
    }

    public void setCheckListNumber(String checkListNumber) {
        this.checkListNumber = checkListNumber;
    }

    public String getCoh() {
        return coh;
    }

    public void setCoh(String coh) {
        this.coh = coh;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getBla() {
        return bla;
    }

    public void setBla(String bla) {
        this.bla = bla;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
