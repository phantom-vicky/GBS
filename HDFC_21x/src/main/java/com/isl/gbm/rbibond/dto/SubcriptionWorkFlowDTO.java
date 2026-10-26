package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.isl.gbm.common.dto.ColumnValueDTO;
import com.isl.gbm.transaction.channel.GbmTransactionMasterDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubcriptionWorkFlowDTO {

    private String scssCustomerId;

    private String scssAccountNumber;

    private String accountHolderType;

    private String primaryCustomerMasterName;

    private String secondaryAccountHolderName;

    private String depositAmount;

    private String totalDepositAmount;

    private String transactionType;

    private String setId;

    private String branchId;

    private String operativeAccountNumber;

    private String setNum;

    private String isExtended;

//    private String instrumentType;

    private String instrumentNumber;

//    private String instrumentDate;

//    private String tranCode;

//    private String sortCode;

    private String instAlpha;

//    private String zoneCode;

//    private String zoneDate;

    private String instrumentBankCode;

    private String instrumentBranchCode;



    private String micrCode;

    private String interestPaymentMode;

//    private String remarks;

    private String neftBankCode;

    private String neftBranchCode;

    private String neftIfscCode;

    private String neftNameOfThePayee;

    private String neftOperativeAccountNumber;

//    private List<ScssAccountOpeningNomineeDetailsDto> nomineeDetailsList;

    private List<HashMap<String, String>> customerIdOut;

    private List<HashMap<String, String>> customerMasterOut;

    private List<HashMap<String, String>> scssAccountNumberOut;

    private List<HashMap<String, String>> scssAccountDetailsOut;

    private List<HashMap<String, String>> nomineeDetailsOut;

    private List<HashMap<String, String>> transactionDetailsOut;

    private List<HashMap<String, String>> instrumentDetailsOut;

    private List<HashMap<String, String>> neftDetailsOut;

    private List<HashMap<String, String>> matrixOut;

    private String totalDepositAmt;

//    private String isNominee;

    private List<List<ColumnValueDTO>> transactionDetailsIn;

    private List<List<ColumnValueDTO>> accountDetails;

    private List<List<ColumnValueDTO>> nomineeDetails;

    private List<List<ColumnValueDTO>> tranDetails;

    private List<List<ColumnValueDTO>> extensionDetails;

    private String creditAccountNumber;

    private String debitAccountNumber;

    private String creditAccountNumberType;

    private String debitAccountNumberType;

    private String gbmTranId;

    private String totalAmount;

    private String ddPayAccountNumber;

    private String debitAccountNumberFlag;

    private String creditAccountNumberFlag;

    private String ddPayAccountNumberFlag;

    private String createdBy;

    private String createdDate;

    private String lastModifiedBy;

    private String lastModifiedDate;

    private String verifiedBy;

    private String verifiedDate;

    private String verifiedStatus;

    private String previousOperationType;

    private String channelTypeOut;

    private String gbmTranIdOut;

    private String solIdOut;

    private String tranDateOut;

    private String moduleIdOut;

    private String transactionCodeOut;

    private String debitTranLegOut;

    private String creditTranLegOut;

    private String creditAmountOut;

    private String debitAmountOut;

    private String cbsTranId;

    private String cbsTranStatus;

    private String cbsTranStatusDescription;

    private String solDate;

    private String cbsSetNumber;

    private String cbsPartTranSerialNumber;

    private TransferInAccountOpeningDto transferInAccountOpeningDto;

    private String accountOpenedDate;


    //flg
    private String bondCashAccFlgOut;
    private String bondClearingAccFlgOut;
    private String bondCollectionAccFlgOut;
    private String ddPoAccFlgOut;
    private String neftPlaceholderFlgOut;

    public String getBondCashAccFlgOut() {
        return bondCashAccFlgOut;
    }

    public void setBondCashAccFlgOut(String bondCashAccFlgOut) {
        this.bondCashAccFlgOut = bondCashAccFlgOut;
    }

    public String getBondClearingAccFlgOut() {
        return bondClearingAccFlgOut;
    }

    public void setBondClearingAccFlgOut(String bondClearingAccFlgOut) {
        this.bondClearingAccFlgOut = bondClearingAccFlgOut;
    }

    public String getBondCollectionAccFlgOut() {
        return bondCollectionAccFlgOut;
    }

    public void setBondCollectionAccFlgOut(String bondCollectionAccFlgOut) {
        this.bondCollectionAccFlgOut = bondCollectionAccFlgOut;
    }

    public String getDdPoAccFlgOut() {
        return ddPoAccFlgOut;
    }

    public void setDdPoAccFlgOut(String ddPoAccFlgOut) {
        this.ddPoAccFlgOut = ddPoAccFlgOut;
    }

    public String getNeftPlaceholderFlgOut() {
        return neftPlaceholderFlgOut;
    }

    public void setNeftPlaceholderFlgOut(String neftPlaceholderFlgOut) {
        this.neftPlaceholderFlgOut = neftPlaceholderFlgOut;
    }

    //    private String maturityDate;

    private String interestMaturityRate;

    private String extendedMaturityDate;

    private String extendedInterestMaturityRate;

    private String neftFlagOut;

    private String accountHolderNumber;

    private String cashLimitOut;

    private String interestRateOut;

    private String periodOut;

    private String mofDate;

    private String mofReferenceNumber;

    private String depositMultiples;

    private String minCashLimit;

    public String getMinCashLimit() {
        return minCashLimit;
    }

    public void setMinCashLimit(String minCashLimit) {
        this.minCashLimit = minCashLimit;
    }

    public String getDepositMultiples() {
        return depositMultiples;
    }

    public void setDepositMultiples(String depositMultiples) {
        this.depositMultiples = depositMultiples;
    }

    private String cashLimitForCash;
    private String cashLimitForClearingTransfer;

    public String getCashLimitForCash() {
        return cashLimitForCash;
    }

    public void setCashLimitForCash(String cashLimitForCash) {
        this.cashLimitForCash = cashLimitForCash;
    }

    public String getCashLimitForClearingTransfer() {
        return cashLimitForClearingTransfer;
    }

    public void setCashLimitForClearingTransfer(String cashLimitForClearingTransfer) {
        this.cashLimitForClearingTransfer = cashLimitForClearingTransfer;
    }

    private String mofFlag;

    private String amountValidationFlag;
//    private String applicationNo;
    private String blaNo;
//    private String schemeCode;
//    private String periodOfDeposit;
//    private String rateOfInterest;
    private List<HashMap<String, String>> torchValue;
    private String bondCashAccount;
    private String bondClearingAccount;
    private String bondCollectionAccount;
    private String creditAccount;
    private String ddPoAccountNo;
    private String neftPlaceHolderAccount;


    ////add data
    private String schemeCode;
    private String singleBondAccNo;
    private String modeOfHolding;
    private String bondHolderName;
    private String firstJointHolderBlaNo;
    private String firstJointHolderName;
    private String secondJointHolderBlaNo;
    private String secondJointHolderName;
    private String isNri;
    private String applicationNo;
    private String applicationDate;
    private String brokerAgencyCode;
    private String subscriptionAmount;
    private String bondType;
    private String accountOpenDate;
    private String valueDate;
    private String periodOfDeposit;
    private String maturityDate;
    private String rateOfInterest;
    private String modeOfInterestPayment;
    private String operativeAccNo;
    private String operativeAccBranchId;
    private String ddPoAccNo;
    private String bankCode;
    private String branchCode;
    private String ifscCodeOfCreditBank;
    private String nameOfThePayee;
    private String creditAccType;
    private String neftCreditAccNo;
    private String tranDebitAccNo;
    private String creditAccNo;
    private String tranMode;
    private String debitAccNo;
    private String debitAccType;
    private String branchid;
    private String instrumentType;
    private String instrumentAmount;
    private String instrumentNo;
    private String instrumentDate;
    private String tranCode;
    private String zoneCode;
    private String zoneDate;
    private String bankCodeNonMicr;
    private String branchCodeNonMicr;
    private String sortCode;
    private String remarks;
    private String isNominee;
    private String totalSharingPercent;


    private String subscriptionId;
    private String cbsTranDescription;

//    private String gbmTranId;
    private String blaNum;
    private String id;


    List<com.isl.gbm.transaction.channel.GbmTransactionMasterDto> gbmTransactionMasterDtoList;


    private String pkColumnValue;
    private String channelType;
    private String lastVerifiedBy;
    private String lastVerifiedDate;
    private List<Map<String, Object>> masterDetailsValueModel;
    private List<Map<String, Object>> nomineeValueModel;


    public List<List<ColumnValueDTO>> getTranDetails() {
        return tranDetails;
    }

    public void setTranDetails(List<List<ColumnValueDTO>> tranDetails) {
        this.tranDetails = tranDetails;
    }

    public String getCbsTranDescription() {
        return cbsTranDescription;
    }

    public void setCbsTranDescription(String cbsTranDescription) {
        this.cbsTranDescription = cbsTranDescription;
    }

    public List<GbmTransactionMasterDto> getGbmTransactionMasterDtoList() {
        return gbmTransactionMasterDtoList;
    }

    public void setGbmTransactionMasterDtoList(List<GbmTransactionMasterDto> gbmTransactionMasterDtoList) {
        this.gbmTransactionMasterDtoList = gbmTransactionMasterDtoList;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Map<String, Object>> getMasterDetailsValueModel() {
        return masterDetailsValueModel;
    }

    public void setMasterDetailsValueModel(List<Map<String, Object>> masterDetailsValueModel) {
        this.masterDetailsValueModel = masterDetailsValueModel;
    }

    public List<Map<String, Object>> getNomineeValueModel() {
        return nomineeValueModel;
    }

    public void setNomineeValueModel(List<Map<String, Object>> nomineeValueModel) {
        this.nomineeValueModel = nomineeValueModel;
    }

    public String getPkColumnValue() {
        return pkColumnValue;
    }

    public void setPkColumnValue(String pkColumnValue) {
        this.pkColumnValue = pkColumnValue;
    }

    public String getLastVerifiedBy() {
        return lastVerifiedBy;
    }

    public void setLastVerifiedBy(String lastVerifiedBy) {
        this.lastVerifiedBy = lastVerifiedBy;
    }

    public String getLastVerifiedDate() {
        return lastVerifiedDate;
    }

    public void setLastVerifiedDate(String lastVerifiedDate) {
        this.lastVerifiedDate = lastVerifiedDate;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getBlaNum() {
        return blaNum;
    }

    public void setBlaNum(String blaNum) {
        this.blaNum = blaNum;
    }

    private List<NomineeDetails> nomineeDetailsList;

    public String getTotalSharingPercent() {
        return totalSharingPercent;
    }

    public void setTotalSharingPercent(String totalSharingPercent) {
        this.totalSharingPercent = totalSharingPercent;
    }

    public List<NomineeDetails> getNomineeDetailsList() {
        return nomineeDetailsList;
    }

    public void setNomineeDetailsList(List<NomineeDetails> nomineeDetailsList) {
        this.nomineeDetailsList = nomineeDetailsList;
    }

    public String getSingleBondAccNo() {
        return singleBondAccNo;
    }

    public void setSingleBondAccNo(String singleBondAccNo) {
        this.singleBondAccNo = singleBondAccNo;
    }

    public String getModeOfHolding() {
        return modeOfHolding;
    }

    public void setModeOfHolding(String modeOfHolding) {
        this.modeOfHolding = modeOfHolding;
    }

    public String getBondHolderName() {
        return bondHolderName;
    }

    public void setBondHolderName(String bondHolderName) {
        this.bondHolderName = bondHolderName;
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

    public String getSecondJointHolderBlaNo() {
        return secondJointHolderBlaNo;
    }

    public void setSecondJointHolderBlaNo(String secondJointHolderBlaNo) {
        this.secondJointHolderBlaNo = secondJointHolderBlaNo;
    }

    public String getSecondJointHolderName() {
        return secondJointHolderName;
    }

    public void setSecondJointHolderName(String secondJointHolderName) {
        this.secondJointHolderName = secondJointHolderName;
    }

    public String getIsNri() {
        return isNri;
    }

    public void setIsNri(String isNri) {
        this.isNri = isNri;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getBrokerAgencyCode() {
        return brokerAgencyCode;
    }

    public void setBrokerAgencyCode(String brokerAgencyCode) {
        this.brokerAgencyCode = brokerAgencyCode;
    }

    public String getSubscriptionAmount() {
        return subscriptionAmount;
    }

    public void setSubscriptionAmount(String subscriptionAmount) {
        this.subscriptionAmount = subscriptionAmount;
    }

    public String getBondType() {
        return bondType;
    }

    public void setBondType(String bondType) {
        this.bondType = bondType;
    }

    public String getAccountOpenDate() {
        return accountOpenDate;
    }

    public void setAccountOpenDate(String accountOpenDate) {
        this.accountOpenDate = accountOpenDate;
    }

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    public String getModeOfInterestPayment() {
        return modeOfInterestPayment;
    }

    public void setModeOfInterestPayment(String modeOfInterestPayment) {
        this.modeOfInterestPayment = modeOfInterestPayment;
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

    public String getDdPoAccNo() {
        return ddPoAccNo;
    }

    public void setDdPoAccNo(String ddPoAccNo) {
        this.ddPoAccNo = ddPoAccNo;
    }

    public String getIfscCodeOfCreditBank() {
        return ifscCodeOfCreditBank;
    }

    public void setIfscCodeOfCreditBank(String ifscCodeOfCreditBank) {
        this.ifscCodeOfCreditBank = ifscCodeOfCreditBank;
    }

    public String getNameOfThePayee() {
        return nameOfThePayee;
    }

    public void setNameOfThePayee(String nameOfThePayee) {
        this.nameOfThePayee = nameOfThePayee;
    }

    public String getCreditAccType() {
        return creditAccType;
    }

    public void setCreditAccType(String creditAccType) {
        this.creditAccType = creditAccType;
    }

    public String getNeftCreditAccNo() {
        return neftCreditAccNo;
    }

    public void setNeftCreditAccNo(String neftCreditAccNo) {
        this.neftCreditAccNo = neftCreditAccNo;
    }

    public String getTranDebitAccNo() {
        return tranDebitAccNo;
    }

    public void setTranDebitAccNo(String tranDebitAccNo) {
        this.tranDebitAccNo = tranDebitAccNo;
    }

    public String getCreditAccNo() {
        return creditAccNo;
    }

    public void setCreditAccNo(String creditAccNo) {
        this.creditAccNo = creditAccNo;
    }

    public String getTranMode() {
        return tranMode;
    }

    public void setTranMode(String tranMode) {
        this.tranMode = tranMode;
    }

    public String getDebitAccNo() {
        return debitAccNo;
    }

    public void setDebitAccNo(String debitAccNo) {
        this.debitAccNo = debitAccNo;
    }

    public String getDebitAccType() {
        return debitAccType;
    }

    public void setDebitAccType(String debitAccType) {
        this.debitAccType = debitAccType;
    }

    public String getBranchid() {
        return branchid;
    }

    public void setBranchid(String branchid) {
        this.branchid = branchid;
    }

    public String getInstrumentAmount() {
        return instrumentAmount;
    }

    public void setInstrumentAmount(String instrumentAmount) {
        this.instrumentAmount = instrumentAmount;
    }

    public String getInstrumentNo() {
        return instrumentNo;
    }

    public void setInstrumentNo(String instrumentNo) {
        this.instrumentNo = instrumentNo;
    }

    public String getZoneDate() {
        return zoneDate;
    }

    public void setZoneDate(String zoneDate) {
        this.zoneDate = zoneDate;
    }

    public String getBankCodeNonMicr() {
        return bankCodeNonMicr;
    }

    public void setBankCodeNonMicr(String bankCodeNonMicr) {
        this.bankCodeNonMicr = bankCodeNonMicr;
    }

    public String getBranchCodeNonMicr() {
        return branchCodeNonMicr;
    }

    public void setBranchCodeNonMicr(String branchCodeNonMicr) {
        this.branchCodeNonMicr = branchCodeNonMicr;
    }

//    public void setNomineeDetailsList(List<NomineeDetails> nomineeDetailsList) {
//        this.nomineeDetailsList = nomineeDetailsList;
//    }

    public static class NomineeDetails {
        private String firstName;
        private String middleName;
        private String lastName;
        private String addressOne;
        private String addressTwo;
        private String city;
        private String state;
        private String pinCode;
        private String phoneNo;
        private String sharePercentage;
        private String operativeAccNo;
        private String operativeAccBranchId;
        private String isNomineeMinor;
        private String relationWithHolder;
        private String dateOfBirthOfMinor;
        private String guardianFirstName;
        private String guardianMiddleName;
        private String guardianLastName;
        private String guardianAddressOne;
        private String guardianAddressTwo;
        private String guardianCity;
        private String guardianState;
        private String guardianPinCode;
        private String guardianMobileNo;
        private String guardianEmail;
        private String nomineeStatus;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getAddressOne() {
            return addressOne;
        }

        public void setAddressOne(String addressOne) {
            this.addressOne = addressOne;
        }

        public String getAddressTwo() {
            return addressTwo;
        }

        public void setAddressTwo(String addressTwo) {
            this.addressTwo = addressTwo;
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

        public String getSharePercentage() {
            return sharePercentage;
        }

        public void setSharePercentage(String sharePercentage) {
            this.sharePercentage = sharePercentage;
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

        public String getDateOfBirthOfMinor() {
            return dateOfBirthOfMinor;
        }

        public void setDateOfBirthOfMinor(String dateOfBirthOfMinor) {
            this.dateOfBirthOfMinor = dateOfBirthOfMinor;
        }

        public String getGuardianFirstName() {
            return guardianFirstName;
        }

        public void setGuardianFirstName(String guardianFirstName) {
            this.guardianFirstName = guardianFirstName;
        }

        public String getGuardianMiddleName() {
            return guardianMiddleName;
        }

        public void setGuardianMiddleName(String guardianMiddleName) {
            this.guardianMiddleName = guardianMiddleName;
        }

        public String getGuardianLastName() {
            return guardianLastName;
        }

        public void setGuardianLastName(String guardianLastName) {
            this.guardianLastName = guardianLastName;
        }

        public String getGuardianAddressOne() {
            return guardianAddressOne;
        }

        public void setGuardianAddressOne(String guardianAddressOne) {
            this.guardianAddressOne = guardianAddressOne;
        }

        public String getGuardianAddressTwo() {
            return guardianAddressTwo;
        }

        public void setGuardianAddressTwo(String guardianAddressTwo) {
            this.guardianAddressTwo = guardianAddressTwo;
        }

        public String getGuardianCity() {
            return guardianCity;
        }

        public void setGuardianCity(String guardianCity) {
            this.guardianCity = guardianCity;
        }

        public String getGuardianState() {
            return guardianState;
        }

        public void setGuardianState(String guardianState) {
            this.guardianState = guardianState;
        }

        public String getGuardianPinCode() {
            return guardianPinCode;
        }

        public void setGuardianPinCode(String guardianPinCode) {
            this.guardianPinCode = guardianPinCode;
        }

        public String getGuardianMobileNo() {
            return guardianMobileNo;
        }

        public void setGuardianMobileNo(String guardianMobileNo) {
            this.guardianMobileNo = guardianMobileNo;
        }

        public String getGuardianEmail() {
            return guardianEmail;
        }

        public void setGuardianEmail(String guardianEmail) {
            this.guardianEmail = guardianEmail;
        }

        public String getNomineeStatus() {
            return nomineeStatus;
        }

        public void setNomineeStatus(String nomineeStatus) {
            this.nomineeStatus = nomineeStatus;
        }
    }




    public String getDdPoAccountNo() {
        return ddPoAccountNo;
    }

    public void setDdPoAccountNo(String ddPoAccountNo) {
        this.ddPoAccountNo = ddPoAccountNo;
    }

    public String getBondCashAccount() {
        return bondCashAccount;
    }

    public void setBondCashAccount(String bondCashAccount) {
        this.bondCashAccount = bondCashAccount;
    }

    public String getBondClearingAccount() {
        return bondClearingAccount;
    }

    public void setBondClearingAccount(String bondClearingAccount) {
        this.bondClearingAccount = bondClearingAccount;
    }

    public String getBondCollectionAccount() {
        return bondCollectionAccount;
    }

    public void setBondCollectionAccount(String bondCollectionAccount) {
        this.bondCollectionAccount = bondCollectionAccount;
    }

    public String getNeftPlaceHolderAccount() {
        return neftPlaceHolderAccount;
    }

    public void setNeftPlaceHolderAccount(String neftPlaceHolderAccount) {
        this.neftPlaceHolderAccount = neftPlaceHolderAccount;
    }

    public String getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }

    public List<HashMap<String, String>> getTorchValue() {
        return torchValue;
    }

    public void setTorchValue(List<HashMap<String, String>> torchValue) {
        this.torchValue = torchValue;
    }

    public String getRateOfInterest() {
        return rateOfInterest;
    }

    public void setRateOfInterest(String rateOfInterest) {
        this.rateOfInterest = rateOfInterest;
    }

    public String getPeriodOfDeposit() {
        return periodOfDeposit;
    }

    public void setPeriodOfDeposit(String periodOfDeposit) {
        this.periodOfDeposit = periodOfDeposit;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getBlaNo() {
        return blaNo;
    }

    public void setBlaNo(String blaNo) {
        this.blaNo = blaNo;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getSetNum() {
        return setNum;
    }

    public void setSetNum(String setNum) {
        this.setNum = setNum;
    }

    public String getAmountValidationFlag() {
        return amountValidationFlag;
    }

    public void setAmountValidationFlag(String amountValidationFlag) {
        this.amountValidationFlag = amountValidationFlag;
    }

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    public String getTotalDepositAmt() {
        return totalDepositAmt;
    }

    public void setTotalDepositAmt(String totalDepositAmt) {
        this.totalDepositAmt = totalDepositAmt;
    }

    public String getMofFlag() {
        return mofFlag;
    }

    public void setMofFlag(String mofFlag) {
        this.mofFlag = mofFlag;
    }

    public String getMofDate() {
        return mofDate;
    }

    public void setMofDate(String mofDate) {
        this.mofDate = mofDate;
    }

    public String getMofReferenceNumber() {
        return mofReferenceNumber;
    }

    public void setMofReferenceNumber(String mofReferenceNumber) {
        this.mofReferenceNumber = mofReferenceNumber;
    }

    public String getInterestRateOut() {
        return interestRateOut;
    }

    public void setInterestRateOut(String interestRateOut) {
        this.interestRateOut = interestRateOut;
    }

    public String getPeriodOut() {
        return periodOut;
    }

    public void setPeriodOut(String periodOut) {
        this.periodOut = periodOut;
    }

    public String getCashLimitOut() {
        return cashLimitOut;
    }

    public void setCashLimitOut(String cashLimitOut) {
        this.cashLimitOut = cashLimitOut;
    }

    public String getAccountHolderNumber() {
        return accountHolderNumber;
    }

    public void setAccountHolderNumber(String accountHolderNumber) {
        this.accountHolderNumber = accountHolderNumber;
    }

    public String getNeftFlagOut() {
        return neftFlagOut;
    }

    public void setNeftFlagOut(String neftFlagOut) {
        this.neftFlagOut = neftFlagOut;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getInterestMaturityRate() {
        return interestMaturityRate;
    }

    public void setInterestMaturityRate(String interestMaturityRate) {
        this.interestMaturityRate = interestMaturityRate;
    }

    public String getExtendedMaturityDate() {
        return extendedMaturityDate;
    }

    public void setExtendedMaturityDate(String extendedMaturityDate) {
        this.extendedMaturityDate = extendedMaturityDate;
    }

    public String getExtendedInterestMaturityRate() {
        return extendedInterestMaturityRate;
    }

    public void setExtendedInterestMaturityRate(String extendedInterestMaturityRate) {
        this.extendedInterestMaturityRate = extendedInterestMaturityRate;
    }

    public String getAccountOpenedDate() {
        return accountOpenedDate;
    }

    public void setAccountOpenedDate(String accountOpenedDate) {
        this.accountOpenedDate = accountOpenedDate;
    }

    private String pTranId;

    public String getpTranId() {
        return pTranId;
    }

    public void setpTranId(String pTranId) {
        this.pTranId = pTranId;
    }

    public TransferInAccountOpeningDto getTransferInAccountOpeningDto() {
        return transferInAccountOpeningDto;
    }

    public void setTransferInAccountOpeningDto(TransferInAccountOpeningDto transferInAccountOpeningDto) {
        this.transferInAccountOpeningDto = transferInAccountOpeningDto;
    }

    public String getCbsSetNumber() {
        return cbsSetNumber;
    }

    public void setCbsSetNumber(String cbsSetNumber) {
        this.cbsSetNumber = cbsSetNumber;
    }

    public String getCbsPartTranSerialNumber() {
        return cbsPartTranSerialNumber;
    }

    public void setCbsPartTranSerialNumber(String cbsPartTranSerialNumber) {
        this.cbsPartTranSerialNumber = cbsPartTranSerialNumber;
    }

    public String getSolDate() {
        return solDate;
    }

    public void setSolDate(String solDate) {
        this.solDate = solDate;
    }

    public String getInstAlpha() {
        return instAlpha;
    }

    public void setInstAlpha(String instAlpha) {
        this.instAlpha = instAlpha;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public String getInstrumentBankCode() {
        return instrumentBankCode;
    }

    public void setInstrumentBankCode(String instrumentBankCode) {
        this.instrumentBankCode = instrumentBankCode;
    }

    public String getInstrumentBranchCode() {
        return instrumentBranchCode;
    }

    public void setInstrumentBranchCode(String instrumentBranchCode) {
        this.instrumentBranchCode = instrumentBranchCode;
    }

    private List<ScssGttAccountOpeningDto> scssGttAccountOpeningDtoList;

    private String nomineeId;

    public String getNomineeId() {
        return nomineeId;
    }

    public void setNomineeId(String nomineeId) {
        this.nomineeId = nomineeId;
    }

    public List<ScssGttAccountOpeningDto> getScssGttAccountOpeningDtoList() {
        return scssGttAccountOpeningDtoList;
    }

    public void setScssGttAccountOpeningDtoList(List<ScssGttAccountOpeningDto> scssGttAccountOpeningDtoList) {
        this.scssGttAccountOpeningDtoList = scssGttAccountOpeningDtoList;
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

    public String getDebitTranLegOut() {
        return debitTranLegOut;
    }

    public void setDebitTranLegOut(String debitTranLegOut) {
        this.debitTranLegOut = debitTranLegOut;
    }

    public String getCreditTranLegOut() {
        return creditTranLegOut;
    }

    public void setCreditTranLegOut(String creditTranLegOut) {
        this.creditTranLegOut = creditTranLegOut;
    }

    public String getCreditAmountOut() {
        return creditAmountOut;
    }

    public void setCreditAmountOut(String creditAmountOut) {
        this.creditAmountOut = creditAmountOut;
    }

    public String getDebitAmountOut() {
        return debitAmountOut;
    }

    public void setDebitAmountOut(String debitAmountOut) {
        this.debitAmountOut = debitAmountOut;
    }

    public String getChannelTypeOut() {
        return channelTypeOut;
    }

    public void setChannelTypeOut(String channelTypeOut) {
        this.channelTypeOut = channelTypeOut;
    }

    public String getGbmTranIdOut() {
        return gbmTranIdOut;
    }

    public void setGbmTranIdOut(String gbmTranIdOut) {
        this.gbmTranIdOut = gbmTranIdOut;
    }

    public String getSolIdOut() {
        return solIdOut;
    }

    public void setSolIdOut(String solIdOut) {
        this.solIdOut = solIdOut;
    }

    public String getTranDateOut() {
        return tranDateOut;
    }

    public void setTranDateOut(String tranDateOut) {
        this.tranDateOut = tranDateOut;
    }

    public String getModuleIdOut() {
        return moduleIdOut;
    }

    public void setModuleIdOut(String moduleIdOut) {
        this.moduleIdOut = moduleIdOut;
    }

    public String getTransactionCodeOut() {
        return transactionCodeOut;
    }

    public void setTransactionCodeOut(String transactionCodeOut) {
        this.transactionCodeOut = transactionCodeOut;
    }

    public String getPreviousOperationType() {
        return previousOperationType;
    }

    public void setPreviousOperationType(String previousOperationType) {
        this.previousOperationType = previousOperationType;
    }

    public List<HashMap<String, String>> getScssAccountNumberOut() {
        return scssAccountNumberOut;
    }

    public void setScssAccountNumberOut(List<HashMap<String, String>> scssAccountNumberOut) {
        this.scssAccountNumberOut = scssAccountNumberOut;
    }

    public List<HashMap<String, String>> getScssAccountDetailsOut() {
        return scssAccountDetailsOut;
    }

    public void setScssAccountDetailsOut(List<HashMap<String, String>> scssAccountDetailsOut) {
        this.scssAccountDetailsOut = scssAccountDetailsOut;
    }

    public List<HashMap<String, String>> getNomineeDetailsOut() {
        return nomineeDetailsOut;
    }

    public void setNomineeDetailsOut(List<HashMap<String, String>> nomineeDetailsOut) {
        this.nomineeDetailsOut = nomineeDetailsOut;
    }

    public List<HashMap<String, String>> getTransactionDetailsOut() {
        return transactionDetailsOut;
    }

    public void setTransactionDetailsOut(List<HashMap<String, String>> transactionDetailsOut) {
        this.transactionDetailsOut = transactionDetailsOut;
    }

    public List<HashMap<String, String>> getInstrumentDetailsOut() {
        return instrumentDetailsOut;
    }

    public void setInstrumentDetailsOut(List<HashMap<String, String>> instrumentDetailsOut) {
        this.instrumentDetailsOut = instrumentDetailsOut;
    }

    public List<HashMap<String, String>> getNeftDetailsOut() {
        return neftDetailsOut;
    }

    public void setNeftDetailsOut(List<HashMap<String, String>> neftDetailsOut) {
        this.neftDetailsOut = neftDetailsOut;
    }

    public List<HashMap<String, String>> getMatrixOut() {
        return matrixOut;
    }

    public void setMatrixOut(List<HashMap<String, String>> matrixOut) {
        this.matrixOut = matrixOut;
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

    public String getScssAccountNumber() {
        return scssAccountNumber;
    }

    public void setScssAccountNumber(String scssAccountNumber) {
        this.scssAccountNumber = scssAccountNumber;
    }

    public String getDdPayAccountNumber() {
        return ddPayAccountNumber;
    }

    public void setDdPayAccountNumber(String ddPayAccountNumber) {
        this.ddPayAccountNumber = ddPayAccountNumber;
    }


    public String getDebitAccountNumberFlag() {
        return debitAccountNumberFlag;
    }

    public void setDebitAccountNumberFlag(String debitAccountNumberFlag) {
        this.debitAccountNumberFlag = debitAccountNumberFlag;
    }

    public String getCreditAccountNumberFlag() {
        return creditAccountNumberFlag;
    }

    public void setCreditAccountNumberFlag(String creditAccountNumberFlag) {
        this.creditAccountNumberFlag = creditAccountNumberFlag;
    }

    public String getDdPayAccountNumberFlag() {
        return ddPayAccountNumberFlag;
    }

    public void setDdPayAccountNumberFlag(String ddPayAccountNumberFlag) {
        this.ddPayAccountNumberFlag = ddPayAccountNumberFlag;
    }

    public String getGbmTranId() {
        return gbmTranId;
    }

    public void setGbmTranId(String gbmTranId) {
        this.gbmTranId = gbmTranId;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCreditAccountNumber() {
        return creditAccountNumber;
    }

    public void setCreditAccountNumber(String creditAccountNumber) {
        this.creditAccountNumber = creditAccountNumber;
    }

    public String getDebitAccountNumber() {
        return debitAccountNumber;
    }

    public void setDebitAccountNumber(String debitAccountNumber) {
        this.debitAccountNumber = debitAccountNumber;
    }

    public String getCreditAccountNumberType() {
        return creditAccountNumberType;
    }

    public void setCreditAccountNumberType(String creditAccountNumberType) {
        this.creditAccountNumberType = creditAccountNumberType;
    }

    public String getDebitAccountNumberType() {
        return debitAccountNumberType;
    }

    public void setDebitAccountNumberType(String debitAccountNumberType) {
        this.debitAccountNumberType = debitAccountNumberType;
    }

    public List<List<ColumnValueDTO>> getTransactionDetailsIn() {
        return transactionDetailsIn;
    }

    public void setTransactionDetailsIn(List<List<ColumnValueDTO>> transactionDetailsIn) {
        this.transactionDetailsIn = transactionDetailsIn;
    }

    public List<List<ColumnValueDTO>> getAccountDetails() {
        return accountDetails;
    }

    public void setAccountDetails(List<List<ColumnValueDTO>> accountDetails) {
        this.accountDetails = accountDetails;
    }

    public List<List<ColumnValueDTO>> getNomineeDetails() {
        return nomineeDetails;
    }

    public void setNomineeDetails(List<List<ColumnValueDTO>> nomineeDetails) {
        this.nomineeDetails = nomineeDetails;
    }

    public String getIsNominee() {
        return isNominee;
    }

    public void setIsNominee(String isNominee) {
        this.isNominee = isNominee;
    }

    public List<HashMap<String, String>> getCustomerMasterOut() {
        return customerMasterOut;
    }

    public void setCustomerMasterOut(List<HashMap<String, String>> customerMasterOut) {
        this.customerMasterOut = customerMasterOut;
    }

    public List<HashMap<String, String>> getCustomerIdOut() {
        return customerIdOut;
    }

    public void setCustomerIdOut(List<HashMap<String, String>> customerIdOut) {
        this.customerIdOut = customerIdOut;
    }

//    public List<ScssAccountOpeningNomineeDetailsDto> getNomineeDetailsList() {
//        return nomineeDetailsList;
//    }
//
//    public void setNomineeDetailsList(List<ScssAccountOpeningNomineeDetailsDto> nomineeDetailsList) {
//        this.nomineeDetailsList = nomineeDetailsList;
//    }

    public String getScssCustomerId() {
        return scssCustomerId;
    }

    public void setScssCustomerId(String scssCustomerId) {
        this.scssCustomerId = scssCustomerId;
    }

    public String getAccountHolderType() {
        return accountHolderType;
    }

    public void setAccountHolderType(String accountHolderType) {
        this.accountHolderType = accountHolderType;
    }

    public String getPrimaryCustomerMasterName() {
        return primaryCustomerMasterName;
    }

    public void setPrimaryCustomerMasterName(String primaryCustomerMasterName) {
        this.primaryCustomerMasterName = primaryCustomerMasterName;
    }

    public String getSecondaryAccountHolderName() {
        return secondaryAccountHolderName;
    }

    public void setSecondaryAccountHolderName(String secondaryAccountHolderName) {
        this.secondaryAccountHolderName = secondaryAccountHolderName;
    }

    public String getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(String depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getTotalDepositAmount() {
        return totalDepositAmount;
    }

    public void setTotalDepositAmount(String totalDepositAmount) {
        this.totalDepositAmount = totalDepositAmount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getOperativeAccountNumber() {
        return operativeAccountNumber;
    }

    public void setOperativeAccountNumber(String operativeAccountNumber) {
        this.operativeAccountNumber = operativeAccountNumber;
    }

    public String getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    }

    public String getInstrumentNumber() {
        return instrumentNumber;
    }

    public void setInstrumentNumber(String instrumentNumber) {
        this.instrumentNumber = instrumentNumber;
    }

    public String getInstrumentDate() {
        return instrumentDate;
    }

    public void setInstrumentDate(String instrumentDate) {
        this.instrumentDate = instrumentDate;
    }

    public String getTranCode() {
        return tranCode;
    }

    public void setTranCode(String tranCode) {
        this.tranCode = tranCode;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

//    public String getZoneDate() {
//        return zoneDate;
//    }
//
//    public void setZoneDate(String zoneDate) {
//        this.zoneDate = zoneDate;
//    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getMicrCode() {
        return micrCode;
    }

    public void setMicrCode(String micrCode) {
        this.micrCode = micrCode;
    }

    public String getInterestPaymentMode() {
        return interestPaymentMode;
    }

    public void setInterestPaymentMode(String interestPaymentMode) {
        this.interestPaymentMode = interestPaymentMode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getNeftBankCode() {
        return neftBankCode;
    }

    public void setNeftBankCode(String neftBankCode) {
        this.neftBankCode = neftBankCode;
    }

    public String getNeftBranchCode() {
        return neftBranchCode;
    }

    public void setNeftBranchCode(String neftBranchCode) {
        this.neftBranchCode = neftBranchCode;
    }

    public String getNeftIfscCode() {
        return neftIfscCode;
    }

    public void setNeftIfscCode(String neftIfscCode) {
        this.neftIfscCode = neftIfscCode;
    }

    public String getNeftNameOfThePayee() {
        return neftNameOfThePayee;
    }

    public void setNeftNameOfThePayee(String neftNameOfThePayee) {
        this.neftNameOfThePayee = neftNameOfThePayee;
    }

    public String getNeftOperativeAccountNumber() {
        return neftOperativeAccountNumber;
    }

    public void setNeftOperativeAccountNumber(String neftOperativeAccountNumber) {
        this.neftOperativeAccountNumber = neftOperativeAccountNumber;
    }

    public List<List<ColumnValueDTO>> getExtensionDetails() {
        return extensionDetails;
    }

    public void setExtensionDetails(List<List<ColumnValueDTO>> extensionDetails) {
        this.extensionDetails = extensionDetails;
    }

    public String getIsExtended() {
        return isExtended;
    }

    public void setIsExtended(String isExtended) {
        this.isExtended = isExtended;
    }
}
