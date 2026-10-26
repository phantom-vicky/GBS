package com.isl.gbm.rbibond.dto.reports;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NetBankingTransectionPdfDTO {


    private String pan;

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getBla() {
        return bla;
    }

    public void setBla(String bla) {
        this.bla = bla;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
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

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

//    public String getModeOfHolding() {
//        return modeOfHolding;
//    }
//
//    public void setModeOfHolding(String modeOfHolding) {
//        this.modeOfHolding = modeOfHolding;
//    }

    public String getNoOfJointHolders() {
        return noOfJointHolders;
    }

    public void setNoOfJointHolders(String noOfJointHolders) {
        this.noOfJointHolders = noOfJointHolders;
    }

    public String getSubscriptionAmount() {
        return subscriptionAmount;
    }

    public void setSubscriptionAmount(String subscriptionAmount) {
        this.subscriptionAmount = subscriptionAmount;
    }

    private String bla;

    private String subscriptionId;

    private String fromDate;

    private String toDate;

    private String holderName;



    private String noOfJointHolders;

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    private String subscriptionAmount;

    private String schemeCode;

    private String  flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

//Application Details

    private String branchName;

    private  String applicationNumber;

    private String nameInFullOne;

    private String GenderOne;

    private String dobOne;

    private String panNoOne;



    private  String  telePhone;

    private String emailAddress;



    private String nameInFullTwo;

    private String GenderTwo;

    private String dobTwo;

    private String panNoTwo;


    private String nameInFullThree;

    private String GenderThree;

    private String dobThree;

    private String panNoThree;


  //  Guardian Details


    private String guardianNameInFullOne;

    private  String relationshipwithMinor;

    private String guardianGender;

    private String guardiandob;

    private String guardianPanNo;

    private String guardianCommunicationAddress;

    private  String  guardianTelePhone;

    private String guardianEmailAddress;


    // Other Details


    private String chequeNumber;

    private String  ddNumber;

    private String dated;

    private String investAmt;


    // BANK PARTICULARS



    private String branchNameAddress;

    private  String accountNumber;





    //  FlotingRates

     private String dateofIssue;



    private String dateofRepayment;

    private String fullNameWithAddressOfNominee;

    private String dateOfBirth;

    private String relationShipToHolder;

    private String statusResidentNri;

    private String particularsOfBankAccount;

    private  String bondHolderName;


    private  String panNum;

    private  String BlaNo;


    private List<HashMap<String, String>> torchValue;



    private List<HashMap<String, String>> branchDetails;

    public List<HashMap<String, String>> getHolderOtherDetails() {
        return HolderOtherDetails;
    }

    public void setHolderOtherDetails(List<HashMap<String, String>> holderOtherDetails) {
        HolderOtherDetails = holderOtherDetails;
    }

    public List<HashMap<String, String>> getGaurdianDetails() {
        return gaurdianDetails;
    }

    public void setGaurdianDetails(List<HashMap<String, String>> gaurdianDetails) {
        this.gaurdianDetails = gaurdianDetails;
    }

    public List<HashMap<String, String>> getApplicationDetails() {
        return applicationDetails;
    }

    public void setApplicationDetails(List<HashMap<String, String>> applicationDetails) {
        this.applicationDetails = applicationDetails;
    }

    public List<HashMap<String, String>> getParticulerBankDetails() {
        return particulerBankDetails;
    }

    public void setParticulerBankDetails(List<HashMap<String, String>> particulerBankDetails) {
        this.particulerBankDetails = particulerBankDetails;
    }

    public List<HashMap<String, String>> getNomineeDetails() {
        return nomineeDetails;
    }

    public void setNomineeDetails(List<HashMap<String, String>> nomineeDetails) {
        this.nomineeDetails = nomineeDetails;
    }

    private List<HashMap<String, String>> HolderOtherDetails;
    private List<HashMap<String, String>> gaurdianDetails;
    private List<HashMap<String, String>> applicationDetails;
    private List<HashMap<String, String>> particulerBankDetails;
    private List<HashMap<String, String>> nomineeDetails;

    public List<HashMap<String, String>> getBranchDetails() {
        return branchDetails;
    }

    public void setBranchDetails(List<HashMap<String, String>> branchDetails) {
        this.branchDetails = branchDetails;
    }

    public List<HashMap<String, String>> getTorchValue() {
        return torchValue;
    }

    public void setTorchValue(List<HashMap<String, String>> torchValue) {
        this.torchValue = torchValue;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public String getNameInFullOne() {
        return nameInFullOne;
    }

    public void setNameInFullOne(String nameInFullOne) {
        this.nameInFullOne = nameInFullOne;
    }

    public String getGenderOne() {
        return GenderOne;
    }

    public void setGenderOne(String genderOne) {
        GenderOne = genderOne;
    }

    public String getDobOne() {
        return dobOne;
    }

    public void setDobOne(String dobOne) {
        this.dobOne = dobOne;
    }

    public String getPanNoOne() {
        return panNoOne;
    }

    public void setPanNoOne(String panNoOne) {
        this.panNoOne = panNoOne;
    }

//    public String getCommunicationAddress() {
//        return communicationAddress;
//    }
//
//    public void setCommunicationAddress(String communicationAddress) {
//        this.communicationAddress = communicationAddress;
//    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getNameInFullTwo() {
        return nameInFullTwo;
    }

    public void setNameInFullTwo(String nameInFullTwo) {
        this.nameInFullTwo = nameInFullTwo;
    }

    public String getGenderTwo() {
        return GenderTwo;
    }

    public void setGenderTwo(String genderTwo) {
        GenderTwo = genderTwo;
    }

    public String getDobTwo() {
        return dobTwo;
    }

    public void setDobTwo(String dobTwo) {
        this.dobTwo = dobTwo;
    }

    public String getPanNoTwo() {
        return panNoTwo;
    }

    public void setPanNoTwo(String panNoTwo) {
        this.panNoTwo = panNoTwo;
    }

    public String getNameInFullThree() {
        return nameInFullThree;
    }

    public void setNameInFullThree(String nameInFullThree) {
        this.nameInFullThree = nameInFullThree;
    }

    public String getGenderThree() {
        return GenderThree;
    }

    public void setGenderThree(String genderThree) {
        GenderThree = genderThree;
    }

    public String getDobThree() {
        return dobThree;
    }

    public void setDobThree(String dobThree) {
        this.dobThree = dobThree;
    }

    public String getPanNoThree() {
        return panNoThree;
    }

    public void setPanNoThree(String panNoThree) {
        this.panNoThree = panNoThree;
    }

    public String getGuardianNameInFullOne() {
        return guardianNameInFullOne;
    }

    public void setGuardianNameInFullOne(String guardianNameInFullOne) {
        this.guardianNameInFullOne = guardianNameInFullOne;
    }

    public String getRelationshipwithMinor() {
        return relationshipwithMinor;
    }

    public void setRelationshipwithMinor(String relationshipwithMinor) {
        this.relationshipwithMinor = relationshipwithMinor;
    }

    public String getGuardianGender() {
        return guardianGender;
    }

    public void setGuardianGender(String guardianGender) {
        this.guardianGender = guardianGender;
    }

    public String getGuardiandob() {
        return guardiandob;
    }

    public void setGuardiandob(String guardiandob) {
        this.guardiandob = guardiandob;
    }

    public String getGuardianPanNo() {
        return guardianPanNo;
    }

    public void setGuardianPanNo(String guardianPanNo) {
        this.guardianPanNo = guardianPanNo;
    }

    public String getGuardianCommunicationAddress() {
        return guardianCommunicationAddress;
    }

    public void setGuardianCommunicationAddress(String guardianCommunicationAddress) {
        this.guardianCommunicationAddress = guardianCommunicationAddress;
    }

    public String getGuardianTelePhone() {
        return guardianTelePhone;
    }

    public void setGuardianTelePhone(String guardianTelePhone) {
        this.guardianTelePhone = guardianTelePhone;
    }

    public String getGuardianEmailAddress() {
        return guardianEmailAddress;
    }

    public void setGuardianEmailAddress(String guardianEmailAddress) {
        this.guardianEmailAddress = guardianEmailAddress;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public String getDdNumber() {
        return ddNumber;
    }

    public void setDdNumber(String ddNumber) {
        this.ddNumber = ddNumber;
    }

    public String getDated() {
        return dated;
    }

    public void setDated(String dated) {
        this.dated = dated;
    }

    public String getInvestAmt() {
        return investAmt;
    }

    public void setInvestAmt(String investAmt) {
        this.investAmt = investAmt;
    }

//    public String getAccountHolderName() {
//        return accountHolderName;
//    }
//
//    public void setAccountHolderName(String accountHolderName) {
//        this.accountHolderName = accountHolderName;
//    }

    public String getBranchNameAddress() {
        return branchNameAddress;
    }

    public void setBranchNameAddress(String branchNameAddress) {
        this.branchNameAddress = branchNameAddress;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

//    public String getIfscCode() {
//        return ifscCode;
//    }
//
//    public void setIfscCode(String ifscCode) {
//        this.ifscCode = ifscCode;
//    }

    public String getDateofIssue() {
        return dateofIssue;
    }

    public void setDateofIssue(String dateofIssue) {
        this.dateofIssue = dateofIssue;
    }

//    public String getAmount() {
//        return amount;
//    }
//
//    public void setAmount(String amount) {
//        this.amount = amount;
//    }

    public String getDateofRepayment() {
        return dateofRepayment;
    }

    public void setDateofRepayment(String dateofRepayment) {
        this.dateofRepayment = dateofRepayment;
    }

    public String getFullNameWithAddressOfNominee() {
        return fullNameWithAddressOfNominee;
    }

    public void setFullNameWithAddressOfNominee(String fullNameWithAddressOfNominee) {
        this.fullNameWithAddressOfNominee = fullNameWithAddressOfNominee;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRelationShipToHolder() {
        return relationShipToHolder;
    }

    public void setRelationShipToHolder(String relationShipToHolder) {
        this.relationShipToHolder = relationShipToHolder;
    }

    public String getStatusResidentNri() {
        return statusResidentNri;
    }

    public void setStatusResidentNri(String statusResidentNri) {
        this.statusResidentNri = statusResidentNri;
    }

    public String getParticularsOfBankAccount() {
        return particularsOfBankAccount;
    }

    public void setParticularsOfBankAccount(String particularsOfBankAccount) {
        this.particularsOfBankAccount = particularsOfBankAccount;
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

    public String getBlaNo() {
        return BlaNo;
    }

    public void setBlaNo(String blaNo) {
        BlaNo = blaNo;
    }
}


   //branch Details

//    private  String brokerCode;
//    private String  branchCode;
//    private String bankCode;
//    private String addressOfBranch;
//    private String bankBranchStamp;
//
//
//
//    // application Details
//
//    private String nameInFull;
//    private String gender;
//    private String dob;
//    private String motherName;
//    private String communicationAddress;
//    private String mobileNumber;
//    private String emailId;
//
//
//
//
//    //gurdian Details
//
//
//        private String relationWithMinor;
//        private String gurdianCumAddress;
//        private String guardianMobileNumber;
//        private String guardianEmailId;
//
//
//
//        // other bank details
//
//
//        private String accountHolderName;
//        private String bankName;
//        private String bankMicrCode;
//        private String branchMicrCode;
//        private String accountNo;
//        private String ifscCode;
//        private String accountType;
//
//
//
//      //  particuler Bank Details
//
//            private String applicationStatus;
//           private String modeOfHolding;
//            private String modeOfInvestMent;
//            private String investmentAmount;
//            private String ddAccNo;
//
//
//
//       // nominee details
//
//
//            private String amount;
//            private String name;
//            private String addressOfNominee;
//            private String relationWithHolder;
//            private String statusOfResident;

