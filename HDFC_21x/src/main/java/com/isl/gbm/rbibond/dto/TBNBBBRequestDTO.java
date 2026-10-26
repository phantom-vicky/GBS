package com.isl.gbm.rbibond.dto;

import com.opencsv.bean.CsvBindByPosition;
import org.javafunk.excelparser.annotations.ExcelObject;
import org.javafunk.excelparser.annotations.ParseType;
import org.springframework.validation.annotation.Validated;

@ExcelObject(parseType = ParseType.ROW, start = 2)
@Validated
public class TBNBBBRequestDTO {

    @CsvBindByPosition(position = 0)
    private String scheme;

    @CsvBindByPosition(position = 1)
    private String pickupPointCode;

    @CsvBindByPosition(position = 2)
    private String checkListNumber;

    @CsvBindByPosition(position = 3)
    private String depositDate;

    @CsvBindByPosition(position = 4)
    private String activationDate;

    @CsvBindByPosition(position = 5)
    private String dispatchBankArrangement;

    @CsvBindByPosition(position = 6)
    private String clientType;

    @CsvBindByPosition(position = 7)
    private String depositSlipNumber;

    @CsvBindByPosition(position = 8)
    private String instrumentNumber;

    @CsvBindByPosition(position = 9)
    private String draweeBankCode;

    @CsvBindByPosition(position = 10)
    private String micrCode;

    @CsvBindByPosition(position = 11)
    private String clientCode;

    @CsvBindByPosition(position = 12)
    private String clearingLocationCode;

    @CsvBindByPosition(position = 13)
    private String dispatchBankCode;

    @CsvBindByPosition(position = 14)
    private String clientArrangement;

    @CsvBindByPosition(position = 15)
    private String instrumentAmount;

    @CsvBindByPosition(position = 16)
    private String productCode;

    @CsvBindByPosition(position = 17)
    private String blank;

    @CsvBindByPosition(position = 18)
    private String applicantName;

    @CsvBindByPosition(position = 19)
    private String operatingInstructions;

    @CsvBindByPosition(position = 20)
    private String cohCoi;

    @CsvBindByPosition(position = 21)
    private String existingBlaNumber;

    @CsvBindByPosition(position = 22)
    private String typeOfApplicant;

    @CsvBindByPosition(position = 23)
    private String panNo;

    @CsvBindByPosition(position = 24)
    private String emailAddressOfFirstApplicant;

    @CsvBindByPosition(position = 25)
    private String bankNameInterestPayment;

    @CsvBindByPosition(position = 26)
    private String accountNoInterestPayment;

    @CsvBindByPosition(position = 27)
    private String ifscCodeInterestPayment;

    @CsvBindByPosition(position = 28)
    private String mobileNo;

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getPickupPointCode() {
        return pickupPointCode;
    }

    public void setPickupPointCode(String pickupPointCode) {
        this.pickupPointCode = pickupPointCode;
    }

    public String getCheckListNumber() {
        return checkListNumber;
    }

    public void setCheckListNumber(String checkListNumber) {
        this.checkListNumber = checkListNumber;
    }

    public String getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(String depositDate) {
        this.depositDate = depositDate;
    }

    public String getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(String activationDate) {
        this.activationDate = activationDate;
    }

    public String getDispatchBankArrangement() {
        return dispatchBankArrangement;
    }

    public void setDispatchBankArrangement(String dispatchBankArrangement) {
        this.dispatchBankArrangement = dispatchBankArrangement;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
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

    public String getDraweeBankCode() {
        return draweeBankCode;
    }

    public void setDraweeBankCode(String draweeBankCode) {
        this.draweeBankCode = draweeBankCode;
    }

    public String getMicrCode() {
        return micrCode;
    }

    public void setMicrCode(String micrCode) {
        this.micrCode = micrCode;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getClearingLocationCode() {
        return clearingLocationCode;
    }

    public void setClearingLocationCode(String clearingLocationCode) {
        this.clearingLocationCode = clearingLocationCode;
    }

    public String getDispatchBankCode() {
        return dispatchBankCode;
    }

    public void setDispatchBankCode(String dispatchBankCode) {
        this.dispatchBankCode = dispatchBankCode;
    }

    public String getClientArrangement() {
        return clientArrangement;
    }

    public void setClientArrangement(String clientArrangement) {
        this.clientArrangement = clientArrangement;
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

    public String getBlank() {
        return blank;
    }

    public void setBlank(String blank) {
        this.blank = blank;
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

    public String getCohCoi() {
        return cohCoi;
    }

    public void setCohCoi(String cohCoi) {
        this.cohCoi = cohCoi;
    }

    public String getExistingBlaNumber() {
        return existingBlaNumber;
    }

    public void setExistingBlaNumber(String existingBlaNumber) {
        this.existingBlaNumber = existingBlaNumber;
    }

    public String getTypeOfApplicant() {
        return typeOfApplicant;
    }

    public void setTypeOfApplicant(String typeOfApplicant) {
        this.typeOfApplicant = typeOfApplicant;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getEmailAddressOfFirstApplicant() {
        return emailAddressOfFirstApplicant;
    }

    public void setEmailAddressOfFirstApplicant(String emailAddressOfFirstApplicant) {
        this.emailAddressOfFirstApplicant = emailAddressOfFirstApplicant;
    }

    public String getBankNameInterestPayment() {
        return bankNameInterestPayment;
    }

    public void setBankNameInterestPayment(String bankNameInterestPayment) {
        this.bankNameInterestPayment = bankNameInterestPayment;
    }

    public String getAccountNoInterestPayment() {
        return accountNoInterestPayment;
    }

    public void setAccountNoInterestPayment(String accountNoInterestPayment) {
        this.accountNoInterestPayment = accountNoInterestPayment;
    }

    public String getIfscCodeInterestPayment() {
        return ifscCodeInterestPayment;
    }

    public void setIfscCodeInterestPayment(String ifscCodeInterestPayment) {
        this.ifscCodeInterestPayment = ifscCodeInterestPayment;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
