package com.isl.gbm.rbibond.dto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import jakarta.validation.constraints.NotBlank;
import org.javafunk.excelparser.annotations.ExcelObject;
import org.javafunk.excelparser.annotations.ParseType;
import org.springframework.validation.annotation.Validated;

@ExcelObject(parseType = ParseType.ROW, start = 2)
@Validated
public class RRBNBRequestDTO {
    @CsvBindByPosition(position = 0)
    private String tranType;

    @CsvBindByPosition(position = 1)
    private String entityId;

    @CsvBindByPosition(position = 2)
    private String typeOfEntry;

    @CsvBindByPosition(position = 3)
    private String debitCreditFlag;

    @CsvBindByPosition(position = 4)
    private String investmentAmount;

    @CsvBindByPosition(position = 5)
    private String valueDate;

    @CsvBindByPosition(position = 6)
    private String postingDate;

    @CsvBindByPosition(position = 7)
    private String productCode;

    @CsvBindByPosition(position = 8)
    private String pickupLocation;

    @CsvBindByPosition(position = 9)
    private String pickupPoint;

    @CsvBindByPosition(position = 10)
    private String pickupDate;

    @CsvBindByPosition(position = 11)
    private String depositSlipNumber;

    @CsvBindByPosition(position = 12)
    private String depositDate;

    @CsvBindByPosition(position = 13)
    private String depositAmount;

    @CsvBindByPosition(position = 14)
    private String numberOfInstruments;

    @CsvBindByPosition(position = 15)
    private String depositRemarks;

    @CsvBindByPosition(position = 16)
    private String instrumentNumber;

    @CsvBindByPosition(position = 17)
    private String draweeBankDescription;

    @CsvBindByPosition(position = 18)
    private String locDescription;

    @CsvBindByPosition(position = 19)
    private String instrumentAmount;

    @CsvBindByPosition(position = 20)
    private String instrumentDate;

    @CsvBindByPosition(position = 21)
    private String drawerName;

    @CsvBindByPosition(position = 22)
    private String applicantName;

    @CsvBindByPosition(position = 23)
    private String operatingInstructions;

    @CsvBindByPosition(position = 24)
    private String checkListNumber;

    @CsvBindByPosition(position = 25)
    private String cohCoi;

    @CsvBindByPosition(position = 26)
    private String scheme;

    @CsvBindByPosition(position = 27)
    private String bla;

    @CsvBindByPosition(position = 28)
    private String type;

    @CsvBindByPosition(position = 29)
    private String pan;

    @CsvBindByPosition(position = 30)
    private String email;

    @CsvBindByPosition(position = 31)
    private String bankName;

    @CsvBindByPosition(position = 32)
    private String accountNumber;

    @CsvBindByPosition(position = 33)
    private String ifsc;

    @CsvBindByPosition(position = 34)
    private String mobile;

    @CsvBindByPosition(position = 35)
    private String collectionBranch;

    @CsvBindByPosition(position = 36)
    private String gender;

    @CsvBindByPosition(position = 37)
    private String dateOfBirth;

    @CsvBindByPosition(position = 38)
    private String address1;

    @CsvBindByPosition(position = 39)
    private String address2;

    @CsvBindByPosition(position = 40)
    private String city;

    @CsvBindByPosition(position = 41)
    private String state;

    @CsvBindByPosition(position = 42)
    private String pincode;

    @CsvBindByPosition(position = 43)
    private String telephone;

    @CsvBindByPosition(position = 44)
    private String modeOfInvestment;

    @CsvBindByPosition(position = 45)
    private String branchCode;

    @CsvBindByPosition(position = 46)
    private String brokerCode;

    @CsvBindByPosition(position = 47)
    private String nomineeFirstName;

    @CsvBindByPosition(position = 48)
    private String nomineeMiddleName;

    @CsvBindByPosition(position = 49)
    private String nomineeLastName;

    @CsvBindByPosition(position = 50)
    private String nomineeAddress1;

    @CsvBindByPosition(position = 51)
    private String nomineeAddress2;

    @CsvBindByPosition(position = 52)
    private String nomineeCity;

    @CsvBindByPosition(position = 53)
    private String nomineeState;

    @CsvBindByPosition(position = 54)
    private String nomineeCountry;

    @CsvBindByPosition(position = 55)
    private String nomineePincode;

    @CsvBindByPosition(position = 56)
    private String nomineePhoneNo;

    @CsvBindByPosition(position = 57)
    private String nomineeAmount;

    @CsvBindByPosition(position = 58)
    private String applicantRelation;

    @CsvBindByPosition(position = 59)
    private String minorFlagging;

    @CsvBindByPosition(position = 60)
    private String minorGender;

    @CsvBindByPosition(position = 61)
    private String nomineeDob;

    @CsvBindByPosition(position = 62)
    private String guardiansName;

    @CsvBindByPosition(position = 63)
    private String relationshipWithGuardian;

    private String nomineePercentage;

    public String getNomineeAmount() {
        return nomineeAmount;
    }

    public void setNomineeAmount(String nomineeAmount) {
        this.nomineeAmount = nomineeAmount;
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

    public String getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(String investmentAmount) {
        this.investmentAmount = investmentAmount;
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

    public String getDepositSlipNumber() {
        return depositSlipNumber;
    }

    public void setDepositSlipNumber(String depositSlipNumber) {
        this.depositSlipNumber = depositSlipNumber;
    }

    public String getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(String depositDate) {
        this.depositDate = depositDate;
    }

    public String getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(String depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getNumberOfInstruments() {
        return numberOfInstruments;
    }

    public void setNumberOfInstruments(String numberOfInstruments) {
        this.numberOfInstruments = numberOfInstruments;
    }

    public String getDepositRemarks() {
        return depositRemarks;
    }

    public void setDepositRemarks(String depositRemarks) {
        this.depositRemarks = depositRemarks;
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

    public String getLocDescription() {
        return locDescription;
    }

    public void setLocDescription(String locDescription) {
        this.locDescription = locDescription;
    }

    public String getInstrumentAmount() {
        return instrumentAmount;
    }

    public void setInstrumentAmount(String instrumentAmount) {
        this.instrumentAmount = instrumentAmount;
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

    public String getCohCoi() {
        return cohCoi;
    }

    public void setCohCoi(String cohCoi) {
        this.cohCoi = cohCoi;
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getModeOfInvestment() {
        return modeOfInvestment;
    }

    public void setModeOfInvestment(String modeOfInvestment) {
        this.modeOfInvestment = modeOfInvestment;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBrokerCode() {
        return brokerCode;
    }

    public void setBrokerCode(String brokerCode) {
        this.brokerCode = brokerCode;
    }

    public String getNomineeFirstName() {
        return nomineeFirstName;
    }

    public void setNomineeFirstName(String nomineeFirstName) {
        this.nomineeFirstName = nomineeFirstName;
    }

    public String getNomineeMiddleName() {
        return nomineeMiddleName;
    }

    public void setNomineeMiddleName(String nomineeMiddleName) {
        this.nomineeMiddleName = nomineeMiddleName;
    }

    public String getNomineeLastName() {
        return nomineeLastName;
    }

    public void setNomineeLastName(String nomineeLastName) {
        this.nomineeLastName = nomineeLastName;
    }

    public String getNomineeAddress1() {
        return nomineeAddress1;
    }

    public void setNomineeAddress1(String nomineeAddress1) {
        this.nomineeAddress1 = nomineeAddress1;
    }

    public String getNomineeAddress2() {
        return nomineeAddress2;
    }

    public void setNomineeAddress2(String nomineeAddress2) {
        this.nomineeAddress2 = nomineeAddress2;
    }

    public String getNomineeCity() {
        return nomineeCity;
    }

    public void setNomineeCity(String nomineeCity) {
        this.nomineeCity = nomineeCity;
    }

    public String getNomineeState() {
        return nomineeState;
    }

    public void setNomineeState(String nomineeState) {
        this.nomineeState = nomineeState;
    }

    public String getNomineeCountry() {
        return nomineeCountry;
    }

    public void setNomineeCountry(String nomineeCountry) {
        this.nomineeCountry = nomineeCountry;
    }

    public String getNomineePincode() {
        return nomineePincode;
    }

    public void setNomineePincode(String nomineePincode) {
        this.nomineePincode = nomineePincode;
    }

    public String getNomineePhoneNo() {
        return nomineePhoneNo;
    }

    public void setNomineePhoneNo(String nomineePhoneNo) {
        this.nomineePhoneNo = nomineePhoneNo;
    }

    public String getNomineePercentage() {
        return nomineePercentage;
    }

    public void setNomineePercentage(String nomineePercentage) {
        this.nomineePercentage = nomineePercentage;
    }

    public String getApplicantRelation() {
        return applicantRelation;
    }

    public void setApplicantRelation(String applicantRelation) {
        this.applicantRelation = applicantRelation;
    }

    public String getMinorFlagging() {
        return minorFlagging;
    }

    public void setMinorFlagging(String minorFlagging) {
        this.minorFlagging = minorFlagging;
    }

    public String getMinorGender() {
        return minorGender;
    }

    public void setMinorGender(String minorGender) {
        this.minorGender = minorGender;
    }

    public String getNomineeDob() {
        return nomineeDob;
    }

    public void setNomineeDob(String nomineeDob) {
        this.nomineeDob = nomineeDob;
    }

    public String getGuardiansName() {
        return guardiansName;
    }

    public void setGuardiansName(String guardiansName) {
        this.guardiansName = guardiansName;
    }

    public String getRelationshipWithGuardian() {
        return relationshipWithGuardian;
    }

    public void setRelationshipWithGuardian(String relationshipWithGuardian) {
        this.relationshipWithGuardian = relationshipWithGuardian;
    }
}
