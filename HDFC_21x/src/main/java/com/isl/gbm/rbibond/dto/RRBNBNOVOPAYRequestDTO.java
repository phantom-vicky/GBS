package com.isl.gbm.rbibond.dto;

import com.opencsv.bean.CsvBindByPosition;
import org.javafunk.excelparser.annotations.ExcelObject;
import org.javafunk.excelparser.annotations.ParseType;
import org.springframework.validation.annotation.Validated;

@ExcelObject(parseType = ParseType.ROW, start = 2)
@Validated
public class RRBNBNOVOPAYRequestDTO {
    @CsvBindByPosition(position = 0)
    private String scheme;

    @CsvBindByPosition(position = 1)
    private String pickupPoint;

    @CsvBindByPosition(position = 2)
    private String applicationNumber;

    @CsvBindByPosition(position = 3)
    private String depositDate;

    @CsvBindByPosition(position = 4)
    private String depositSlipNumber;

    @CsvBindByPosition(position = 5)
    private String instrumentNumber;

    @CsvBindByPosition(position = 6)
    private String draweeBankDescription;

    @CsvBindByPosition(position = 7)
    private String instrumentAmount;

    @CsvBindByPosition(position = 8)
    private String productCode;

    @CsvBindByPosition(position = 9)
    private String depositRemarks;

    @CsvBindByPosition(position = 10)
    private String firstNameApplicant;

    @CsvBindByPosition(position = 11)
    private String firstApplicantCustId;

    @CsvBindByPosition(position = 12)
    private String modeOfHolding;

    @CsvBindByPosition(position = 13)
    private String cohCoi;

    @CsvBindByPosition(position = 14)
    private String applicantStatus;

    @CsvBindByPosition(position = 15)
    private String panNo;

    @CsvBindByPosition(position = 16)
    private String motherMaidenName;

    @CsvBindByPosition(position = 17)
    private String emailAddress;

    @CsvBindByPosition(position = 18)
    private String bankName;

    @CsvBindByPosition(position = 19)
    private String accountNo;

    @CsvBindByPosition(position = 20)
    private String ifscCode;

    @CsvBindByPosition(position = 21)
    private String telephones;

    @CsvBindByPosition(position = 22)
    private String collectionBranch;

    @CsvBindByPosition(position = 23)
    private String gender;

    @CsvBindByPosition(position = 24)
    private String dateOfBirth;

    @CsvBindByPosition(position = 25)
    private String communicationAddress1;

    @CsvBindByPosition(position = 26)
    private String communicationAddress2;

    @CsvBindByPosition(position = 27)
    private String city;

    @CsvBindByPosition(position = 28)
    private String state;

    @CsvBindByPosition(position = 29)
    private String pincode;

    @CsvBindByPosition(position = 30)
    private String guardianName;

    @CsvBindByPosition(position = 31)
    private String relationshipWithMinor;

    @CsvBindByPosition(position = 32)
    private String communicationAddress;

    @CsvBindByPosition(position = 33)
    private String guardianEmailAddress;

    @CsvBindByPosition(position = 34)
    private String modeOfInvestment;

    @CsvBindByPosition(position = 35)
    private String chequeDd;

    @CsvBindByPosition(position = 36)
    private String branchCode;

    @CsvBindByPosition(position = 37)
    private String brokersNameAndCode;

    @CsvBindByPosition(position = 38)
    private String nomineeOneFirstName;

    @CsvBindByPosition(position = 39)
    private String nomineeOneSecondName;

    @CsvBindByPosition(position = 40)
    private String nomineeOneThirdName;

    @CsvBindByPosition(position = 41)
    private String nomineeOneAddress1;

    @CsvBindByPosition(position = 42)
    private String nomineeOneAddress2;

    @CsvBindByPosition(position = 43)
    private String nomineeOneCity;

    @CsvBindByPosition(position = 44)
    private String nomineeOneState;

    @CsvBindByPosition(position = 45)
    private String nomineeOnePincode;

    @CsvBindByPosition(position = 46)
    private String nomineeOneCountry;

    @CsvBindByPosition(position = 47)
    private String nomineeOnePhoneNo;

    @CsvBindByPosition(position = 48)
    private String nomineeOneAmount;

    @CsvBindByPosition(position = 49)
    private String nomineeOneRelationshipToHolder;

    @CsvBindByPosition(position = 50)
    private String nomineeOneMinorFlagging;

    @CsvBindByPosition(position = 51)
    private String nomineeOneMinorGender;

    @CsvBindByPosition(position = 52)
    private String nomineeOneDob;

    @CsvBindByPosition(position = 53)
    private String nomineeOneGuardianName;

    @CsvBindByPosition(position = 54)
    private String nomineeOneRelationshipWithMinor;

    @CsvBindByPosition(position = 55)
    private String statusResidentNri;

    @CsvBindByPosition(position = 56)
    private String secondApplicantName;

    @CsvBindByPosition(position = 57)
    private String secondApplicantCustId;

    @CsvBindByPosition(position = 58)
    private String secondApplicantGender;

    @CsvBindByPosition(position = 59)
    private String secondApplicantDob;

    @CsvBindByPosition(position = 60)
    private String secondApplicantPanNo;

    @CsvBindByPosition(position = 61)
    private String thirdApplicantName;

    @CsvBindByPosition(position = 62)
    private String thirdApplicantCustId;

    @CsvBindByPosition(position = 63)
    private String thirdApplicantGender;

    @CsvBindByPosition(position = 64)
    private String thirdApplicantDob;

    @CsvBindByPosition(position = 65)
    private String thirdApplicantPanNo;

    @CsvBindByPosition(position = 66)
    private String accountType;

    @CsvBindByPosition(position = 67)
    private String nomineeTwoFirstName;

    @CsvBindByPosition(position = 68)
    private String nomineeTwoSecondName;

    @CsvBindByPosition(position = 69)
    private String nomineeTwoThirdName;

    @CsvBindByPosition(position = 70)
    private String nomineeTwoAddress1;

    @CsvBindByPosition(position = 71)
    private String nomineeTwoAddress2;

    @CsvBindByPosition(position = 72)
    private String nomineeTwoCity;

    @CsvBindByPosition(position = 73)
    private String nomineeTwoState;

    @CsvBindByPosition(position = 74)
    private String nomineeTwoPincode;

    @CsvBindByPosition(position = 75)
    private String nomineeTwoCountry;

    @CsvBindByPosition(position = 76)
    private String nomineeTwoPhoneNo;

    @CsvBindByPosition(position = 77)
    private String nomineeTwoAmount;

    @CsvBindByPosition(position = 78)
    private String nomineeTwoRelationshipToHolder;

    @CsvBindByPosition(position = 79)
    private String nomineeTwoMinorFlagging;

    private String nomineeOnePercentage;
    private String nomineeTwoPercentage;

    public String getNomineeOneAmount() {
        return nomineeOneAmount;
    }

    public void setNomineeOneAmount(String nomineeOneAmount) {
        this.nomineeOneAmount = nomineeOneAmount;
    }

    public String getNomineeTwoAmount() {
        return nomineeTwoAmount;
    }

    public void setNomineeTwoAmount(String nomineeTwoAmount) {
        this.nomineeTwoAmount = nomineeTwoAmount;
    }

    public String getFirstApplicantCustId() {
        return firstApplicantCustId;
    }

    public void setFirstApplicantCustId(String firstApplicantCustId) {
        this.firstApplicantCustId = firstApplicantCustId;
    }

    public String getThirdApplicantCustId() {
        return thirdApplicantCustId;
    }

    public void setThirdApplicantCustId(String thirdApplicantCustId) {
        this.thirdApplicantCustId = thirdApplicantCustId;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getPickupPoint() {
        return pickupPoint;
    }

    public void setPickupPoint(String pickupPoint) {
        this.pickupPoint = pickupPoint;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public String getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(String depositDate) {
        this.depositDate = depositDate;
    }

    public String getDepositSlipNumber() {
        return depositSlipNumber;
    }

    public void setDepositSlipNumber(String depositSlipNumber) {
        this.depositSlipNumber = depositSlipNumber;
    }

    public String getInstrumentNumber() {
        return instrumentNumber;
    }

    public void setInstrumentNumber(String instrumentNumber) {
        this.instrumentNumber = instrumentNumber;
    }

    public String getDraweeBankDescription() {
        return draweeBankDescription;
    }

    public void setDraweeBankDescription(String draweeBankDescription) {
        this.draweeBankDescription = draweeBankDescription;
    }

    public String getInstrumentAmount() {
        return instrumentAmount;
    }

    public void setInstrumentAmount(String instrumentAmount) {
        this.instrumentAmount = instrumentAmount;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDepositRemarks() {
        return depositRemarks;
    }

    public void setDepositRemarks(String depositRemarks) {
        this.depositRemarks = depositRemarks;
    }

    public String getFirstNameApplicant() {
        return firstNameApplicant;
    }

    public void setFirstNameApplicant(String firstNameApplicant) {
        this.firstNameApplicant = firstNameApplicant;
    }

    public String getModeOfHolding() {
        return modeOfHolding;
    }

    public void setModeOfHolding(String modeOfHolding) {
        this.modeOfHolding = modeOfHolding;
    }

    public String getCohCoi() {
        return cohCoi;
    }

    public void setCohCoi(String cohCoi) {
        this.cohCoi = cohCoi;
    }

    public String getApplicantStatus() {
        return applicantStatus;
    }

    public void setApplicantStatus(String applicantStatus) {
        this.applicantStatus = applicantStatus;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getMotherMaidenName() {
        return motherMaidenName;
    }

    public void setMotherMaidenName(String motherMaidenName) {
        this.motherMaidenName = motherMaidenName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getTelephones() {
        return telephones;
    }

    public void setTelephones(String telephones) {
        this.telephones = telephones;
    }

    public String getCollectionBranch() {
        return collectionBranch;
    }

    public void setCollectionBranch(String collectionBranch) {
        this.collectionBranch = collectionBranch;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCommunicationAddress1() {
        return communicationAddress1;
    }

    public void setCommunicationAddress1(String communicationAddress1) {
        this.communicationAddress1 = communicationAddress1;
    }

    public String getCommunicationAddress2() {
        return communicationAddress2;
    }

    public void setCommunicationAddress2(String communicationAddress2) {
        this.communicationAddress2 = communicationAddress2;
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

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getRelationshipWithMinor() {
        return relationshipWithMinor;
    }

    public void setRelationshipWithMinor(String relationshipWithMinor) {
        this.relationshipWithMinor = relationshipWithMinor;
    }

    public String getCommunicationAddress() {
        return communicationAddress;
    }

    public void setCommunicationAddress(String communicationAddress) {
        this.communicationAddress = communicationAddress;
    }

    public String getGuardianEmailAddress() {
        return guardianEmailAddress;
    }

    public void setGuardianEmailAddress(String guardianEmailAddress) {
        this.guardianEmailAddress = guardianEmailAddress;
    }

    public String getModeOfInvestment() {
        return modeOfInvestment;
    }

    public void setModeOfInvestment(String modeOfInvestment) {
        this.modeOfInvestment = modeOfInvestment;
    }

    public String getChequeDd() {
        return chequeDd;
    }

    public void setChequeDd(String chequeDd) {
        this.chequeDd = chequeDd;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBrokersNameAndCode() {
        return brokersNameAndCode;
    }

    public void setBrokersNameAndCode(String brokersNameAndCode) {
        this.brokersNameAndCode = brokersNameAndCode;
    }

    public String getNomineeOneFirstName() {
        return nomineeOneFirstName;
    }

    public void setNomineeOneFirstName(String nomineeOneFirstName) {
        this.nomineeOneFirstName = nomineeOneFirstName;
    }

    public String getNomineeOneSecondName() {
        return nomineeOneSecondName;
    }

    public void setNomineeOneSecondName(String nomineeOneSecondName) {
        this.nomineeOneSecondName = nomineeOneSecondName;
    }

    public String getNomineeOneThirdName() {
        return nomineeOneThirdName;
    }

    public void setNomineeOneThirdName(String nomineeOneThirdName) {
        this.nomineeOneThirdName = nomineeOneThirdName;
    }

    public String getNomineeOneAddress1() {
        return nomineeOneAddress1;
    }

    public void setNomineeOneAddress1(String nomineeOneAddress1) {
        this.nomineeOneAddress1 = nomineeOneAddress1;
    }

    public String getNomineeOneAddress2() {
        return nomineeOneAddress2;
    }

    public void setNomineeOneAddress2(String nomineeOneAddress2) {
        this.nomineeOneAddress2 = nomineeOneAddress2;
    }

    public String getNomineeOneCity() {
        return nomineeOneCity;
    }

    public void setNomineeOneCity(String nomineeOneCity) {
        this.nomineeOneCity = nomineeOneCity;
    }

    public String getNomineeOneState() {
        return nomineeOneState;
    }

    public void setNomineeOneState(String nomineeOneState) {
        this.nomineeOneState = nomineeOneState;
    }

    public String getNomineeOnePincode() {
        return nomineeOnePincode;
    }

    public void setNomineeOnePincode(String nomineeOnePincode) {
        this.nomineeOnePincode = nomineeOnePincode;
    }

    public String getNomineeOneCountry() {
        return nomineeOneCountry;
    }

    public void setNomineeOneCountry(String nomineeOneCountry) {
        this.nomineeOneCountry = nomineeOneCountry;
    }

    public String getNomineeOnePhoneNo() {
        return nomineeOnePhoneNo;
    }

    public void setNomineeOnePhoneNo(String nomineeOnePhoneNo) {
        this.nomineeOnePhoneNo = nomineeOnePhoneNo;
    }

    public String getNomineeOnePercentage() {
        return nomineeOnePercentage;
    }

    public void setNomineeOnePercentage(String nomineeOnePercentage) {
        this.nomineeOnePercentage = nomineeOnePercentage;
    }

    public String getNomineeOneRelationshipToHolder() {
        return nomineeOneRelationshipToHolder;
    }

    public void setNomineeOneRelationshipToHolder(String nomineeOneRelationshipToHolder) {
        this.nomineeOneRelationshipToHolder = nomineeOneRelationshipToHolder;
    }

    public String getNomineeOneMinorFlagging() {
        return nomineeOneMinorFlagging;
    }

    public void setNomineeOneMinorFlagging(String nomineeOneMinorFlagging) {
        this.nomineeOneMinorFlagging = nomineeOneMinorFlagging;
    }

    public String getNomineeOneMinorGender() {
        return nomineeOneMinorGender;
    }

    public void setNomineeOneMinorGender(String nomineeOneMinorGender) {
        this.nomineeOneMinorGender = nomineeOneMinorGender;
    }

    public String getNomineeOneDob() {
        return nomineeOneDob;
    }

    public void setNomineeOneDob(String nomineeOneDob) {
        this.nomineeOneDob = nomineeOneDob;
    }

    public String getNomineeOneGuardianName() {
        return nomineeOneGuardianName;
    }

    public void setNomineeOneGuardianName(String nomineeOneGuardianName) {
        this.nomineeOneGuardianName = nomineeOneGuardianName;
    }

    public String getNomineeOneRelationshipWithMinor() {
        return nomineeOneRelationshipWithMinor;
    }

    public void setNomineeOneRelationshipWithMinor(String nomineeOneRelationshipWithMinor) {
        this.nomineeOneRelationshipWithMinor = nomineeOneRelationshipWithMinor;
    }

    public String getStatusResidentNri() {
        return statusResidentNri;
    }

    public void setStatusResidentNri(String statusResidentNri) {
        this.statusResidentNri = statusResidentNri;
    }

    public String getSecondApplicantName() {
        return secondApplicantName;
    }

    public void setSecondApplicantName(String secondApplicantName) {
        this.secondApplicantName = secondApplicantName;
    }

    public String getSecondApplicantCustId() {
        return secondApplicantCustId;
    }

    public void setSecondApplicantCustId(String secondApplicantCustId) {
        this.secondApplicantCustId = secondApplicantCustId;
    }

    public String getSecondApplicantGender() {
        return secondApplicantGender;
    }

    public void setSecondApplicantGender(String secondApplicantGender) {
        this.secondApplicantGender = secondApplicantGender;
    }

    public String getSecondApplicantDob() {
        return secondApplicantDob;
    }

    public void setSecondApplicantDob(String secondApplicantDob) {
        this.secondApplicantDob = secondApplicantDob;
    }

    public String getSecondApplicantPanNo() {
        return secondApplicantPanNo;
    }

    public void setSecondApplicantPanNo(String secondApplicantPanNo) {
        this.secondApplicantPanNo = secondApplicantPanNo;
    }

    public String getThirdApplicantName() {
        return thirdApplicantName;
    }

    public void setThirdApplicantName(String thirdApplicantName) {
        this.thirdApplicantName = thirdApplicantName;
    }

    public String getThirdApplicantGender() {
        return thirdApplicantGender;
    }

    public void setThirdApplicantGender(String thirdApplicantGender) {
        this.thirdApplicantGender = thirdApplicantGender;
    }

    public String getThirdApplicantDob() {
        return thirdApplicantDob;
    }

    public void setThirdApplicantDob(String thirdApplicantDob) {
        this.thirdApplicantDob = thirdApplicantDob;
    }

    public String getThirdApplicantPanNo() {
        return thirdApplicantPanNo;
    }

    public void setThirdApplicantPanNo(String thirdApplicantPanNo) {
        this.thirdApplicantPanNo = thirdApplicantPanNo;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getNomineeTwoFirstName() {
        return nomineeTwoFirstName;
    }

    public void setNomineeTwoFirstName(String nomineeTwoFirstName) {
        this.nomineeTwoFirstName = nomineeTwoFirstName;
    }

    public String getNomineeTwoSecondName() {
        return nomineeTwoSecondName;
    }

    public void setNomineeTwoSecondName(String nomineeTwoSecondName) {
        this.nomineeTwoSecondName = nomineeTwoSecondName;
    }

    public String getNomineeTwoThirdName() {
        return nomineeTwoThirdName;
    }

    public void setNomineeTwoThirdName(String nomineeTwoThirdName) {
        this.nomineeTwoThirdName = nomineeTwoThirdName;
    }

    public String getNomineeTwoAddress1() {
        return nomineeTwoAddress1;
    }

    public void setNomineeTwoAddress1(String nomineeTwoAddress1) {
        this.nomineeTwoAddress1 = nomineeTwoAddress1;
    }

    public String getNomineeTwoAddress2() {
        return nomineeTwoAddress2;
    }

    public void setNomineeTwoAddress2(String nomineeTwoAddress2) {
        this.nomineeTwoAddress2 = nomineeTwoAddress2;
    }

    public String getNomineeTwoCity() {
        return nomineeTwoCity;
    }

    public void setNomineeTwoCity(String nomineeTwoCity) {
        this.nomineeTwoCity = nomineeTwoCity;
    }

    public String getNomineeTwoState() {
        return nomineeTwoState;
    }

    public void setNomineeTwoState(String nomineeTwoState) {
        this.nomineeTwoState = nomineeTwoState;
    }

    public String getNomineeTwoPincode() {
        return nomineeTwoPincode;
    }

    public void setNomineeTwoPincode(String nomineeTwoPincode) {
        this.nomineeTwoPincode = nomineeTwoPincode;
    }

    public String getNomineeTwoCountry() {
        return nomineeTwoCountry;
    }

    public void setNomineeTwoCountry(String nomineeTwoCountry) {
        this.nomineeTwoCountry = nomineeTwoCountry;
    }

    public String getNomineeTwoPhoneNo() {
        return nomineeTwoPhoneNo;
    }

    public void setNomineeTwoPhoneNo(String nomineeTwoPhoneNo) {
        this.nomineeTwoPhoneNo = nomineeTwoPhoneNo;
    }

    public String getNomineeTwoPercentage() {
        return nomineeTwoPercentage;
    }

    public void setNomineeTwoPercentage(String nomineeTwoPercentage) {
        this.nomineeTwoPercentage = nomineeTwoPercentage;
    }

    public String getNomineeTwoRelationshipToHolder() {
        return nomineeTwoRelationshipToHolder;
    }

    public void setNomineeTwoRelationshipToHolder(String nomineeTwoRelationshipToHolder) {
        this.nomineeTwoRelationshipToHolder = nomineeTwoRelationshipToHolder;
    }

    public String getNomineeTwoMinorFlagging() {
        return nomineeTwoMinorFlagging;
    }

    public void setNomineeTwoMinorFlagging(String nomineeTwoMinorFlagging) {
        this.nomineeTwoMinorFlagging = nomineeTwoMinorFlagging;
    }
}
