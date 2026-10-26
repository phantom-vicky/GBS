(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("modeOfHoldingChangeController", modeOfHoldingChangeController);

  modeOfHoldingChangeController.$inject = ["$scope", "$filter", "$state", "$rootScope", "$stateParams", "$location", "$timeout", "modeOfHoldingService", "commonService"];

  function modeOfHoldingChangeController($scope, $filter, $state, $rootScope, $stateParams, $location, $timeout, modeOfHoldingService, commonService) {
    var vm = this;
    //	    vm.panPattern = /[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}/;

    vm.ifscPattern = /[a-zA-Z]{4}[0-9]{1}[a-zA-Z0-9]{6}/;
    vm.validationMessages = "";
    vm.modeOfHolding = ""; // "Single", "Joint", "Jointly", or "HUF"
    vm.validatePanLetter = validatePanLetter;
    vm.getAddPanTorchBlaNoTorchDetails = getAddPanTorchBlaNoTorchDetails;
    vm.getVerifyPanTorchBlaNoTorchDetails = getVerifyPanTorchBlaNoTorchDetails;
    vm.getInquiryPanTorchBlaNoTorchDetails = getInquiryPanTorchBlaNoTorchDetails;
   vm.tabReferenceAdd = '';

   vm.tabReferenceInq = '';

        vm.tabReferenceVerify = '';
    vm.showCancelConfirmation = showCancelConfirmation;
    vm.redirectToInputPage = redirectToInputPage;
    vm.getMinorFlag = getMinorFlag;
    //vm.getScssCommonMetaData = getScssCommonMetaData;
    vm.itemsPerPage = "5";
     vm.currentPage = 1;
    vm.scssOtherBanksDetails = [{}];
    vm.customerMasterDetails = [];
    vm.customerMasterJointDetails = [];
    vm.customerMasterFormDetails = {};
    vm.getState = getState;
    vm.getClearAllData = getClearAllData;
    vm.getClearAllDatas = getClearAllDatas;
    vm.getSolDetails = getSolDetails;
    vm.getAddModuleName = getAddModuleName;
    vm.getVerifyModuleName = getVerifyModuleName;
    vm.getInquiryModuleName = getInquiryModuleName;
    vm.getSchemeTypeListOperative = getSchemeTypeListOperative;
    vm.getSchemeTypeListOperativeJoint = getSchemeTypeListOperativeJoint;
    vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
    vm.panPattern = /[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}/;
    vm.emailPattern = /[^@]+@[^@]+\.[a-zA-Z]{2,6}/;
    vm.optionToggled = optionToggled;
    vm.addRange = addRange;
    vm.addRanges = addRanges;
    vm.addGuardian = addGuardian;
    vm.addGuardianTwo = addGuardianTwo;
    vm.addGuardianThree = addGuardianThree;
    vm.isDateEntered = isDateEntered;
    vm.isDateEntered1 = isDateEntered1;
    vm.isDateEntered2 = isDateEntered2;
    vm.removeRanges = removeRanges;
    vm.checkAge = checkAge;
    vm.removeRange = removeRange;
    vm.titleChange = titleChange;
    vm.getblaNo = getblaNo;
    vm.getblaNoInq = getblaNoInq;
    vm.getsubscriptionIdMod = getsubscriptionIdMod;
    vm.getsubscriptionIdInq = getsubscriptionIdInq;
    vm.alert = alert;
    vm.getModeofHolding = getModeofHolding;
    vm.getModifyPantorch = getModifyPantorch;
    vm.addcustomerMaster = addcustomerMaster;
    vm.convertYNToBoolean = convertYNToBoolean;
    vm.getDashBoard = getDashBoard;
    vm.getMaturityDate = getMaturityDate;
    vm.handleHdfcSelection = handleHdfcSelection;
    vm.getAccountType = getAccountType;
    vm.convertNumberToWords = convertNumberToWords;
    vm.convertChunk = convertChunk;
    vm.getInquiryRoles=getInquiryRoles;
    vm.getCbsCustFstJoinDetails = getCbsCustFstJoinDetails;
    vm.getCbsCustScndJoinDetails = getCbsCustScndJoinDetails;
    vm.getBlaAccNoDetails = getBlaAccNoDetails;
    vm.getScssJointCustDetails = getScssJointCustDetails;
    vm.getCurrentFinancialYear = getCurrentFinancialYear;
    vm.getVerifySelectDetails = getVerifySelectDetails;
    vm.verifyCustomerMaster = verifyCustomerMaster;
    vm.getInquirySelectDetails = getInquirySelectDetails;
    vm.depositDateValidation = depositDateValidation;
    vm.maturityDateValid = maturityDateValid;
    vm.maturityDateValidation = maturityDateValidation;
    vm.accountHolderDateValidation = accountHolderDateValidation;
    vm.dateOfRetirementValid = dateOfRetirementValid;
    vm.getDisableTorchFlag = getDisableTorchFlag;
    vm.getTypeofHolding = getTypeofHolding;
    vm.getFormDetails = getFormDetails;
    vm.getKycDetails = getKycDetails;
    vm.getGuardianDetails = getGuardianDetails;
    vm.getGuardianDetailsMetaData = getGuardianDetailsMetaData;
    vm.getWboBranchCode = getWboBranchCode;
    vm.getModuleNameAdd = getModuleNameAdd;
    vm.formFifteenGValidation = formFifteenGValidation;
    vm.convertBooleanToYN = convertBooleanToYN;
    vm.minAgeC = minAgeC;
    vm.prevStep = prevStep;
    vm.nextTab = nextTab;
    vm.prevTab = prevTab;
    vm.convertDateFormat = convertDateFormat;

    vm.getCbsCustFstJoinDetailsTwo = getCbsCustFstJoinDetailsTwo;
    vm.getBlaAccNoDetailsTwo = getBlaAccNoDetailsTwo;
    vm.getGuardianDetailsTwo = getGuardianDetailsTwo;
    vm.optionToggledTwo = optionToggledTwo;
    vm.formFifteenGValidationTwo = formFifteenGValidationTwo;
		vm.getCbsCustFstJoinDetailsThree = getCbsCustFstJoinDetailsThree;
		vm.getBlaAccNoDetailsThree = getBlaAccNoDetailsThree;
		vm.getGuardianDetailsThree = getGuardianDetailsThree;
		vm.optionToggledThree = optionToggledThree;
		vm.formFifteenGValidationThree = formFifteenGValidationThree;
		vm.getSchemeTypeListOperativeThree = getSchemeTypeListOperativeThree;
		vm.getSchemeTypeListOperativeTwo = getSchemeTypeListOperativeTwo;
		vm.formFifteenGValidationM = formFifteenGValidationM;
		vm.formFifteenGValidationTwoM = formFifteenGValidationTwoM;
		vm.formFifteenGValidationThreenM = formFifteenGValidationThreenM;
		vm.validateCount = validateCount;
		vm.orderArray = orderArray;
		vm.checkAge = checkAge;
		vm.dodRecVal = dodRecVal;
		vm.dodVal = dodVal;
		vm.addCoperson = addCoperson;
		vm.addDet = addDet;
		vm.validatePan = validatePan;
		vm.getFtsDetails = getFtsDetails;
		vm.deleteGuardian = deleteGuardian;
		vm.deleteGuardianTwo = deleteGuardianTwo;
		vm.deleteGuardianThree = deleteGuardianThree;
		vm.fromToDateValidation = fromToDateValidation;
		vm.splitName = splitName;
		vm.convertAmountToWords = convertAmountToWords;
		vm.amountToWords= amountToWords;
		vm.nonHdfcTorch = nonHdfcTorch;
		vm.currentDate = new Date();
		//sub

    vm.errorMsg = [];
    vm.accountOpening = [];
    vm.nominationDetails = [{}];
    vm.accountNumberDetails = [];
    vm.accountOpeningMaster = [];
    vm.errorNomineeOperativeAcctNo = [];
    vm.loginSolId = $rootScope.globals.currentUser.solId;
    vm.systemDate = $rootScope.globals.currentUser.systemDate;
    //        vm.currentDate = $rootScope.globals.currentUser.systemDate;
    vm.solDate = $filter("date")(vm.systemDate, "dd/MM/yyyy");
    vm.getCustomerId = getCustomerId;
    vm.updateTransactionMode = updateTransactionMode;
    vm.validateAddSelect = validateAddSelect;
    vm.dateValidation = dateValidation;
    vm.addRange = addRange;
    vm.bondCashAccFlgOut;
    vm.removeNominee = removeNominee;
    vm.getTransactionModes = getTransactionModes;
    vm.getInterestPaymentMode = getInterestPaymentMode;
    vm.getSolDetails = getSolDetails;
    vm.getOtherBankDetailsTorch = getOtherBankDetailsTorch;
    vm.getOtherBankDetailsTorchM = getOtherBankDetailsTorchM;
    vm.getSubCodeList = getSubCodeList;

    vm.validateSubscriptionAmountM = validateSubscriptionAmountM;
    vm.depositAmountValidation = depositAmountValidation;
    vm.getSchemeTypeListOperative = getSchemeTypeListOperative;
    vm.getInstrumentType = getInstrumentType;
    vm.getTranCode = getTranCode;
    vm.getZoneCode = getZoneCode;
    vm.getBankCode = getBankCode;
    vm.getBranchCode = getBranchCode;
    vm.getZoneBankCode = getZoneBankCode;
    vm.getZoneBranchCode = getZoneBranchCode;
    vm.getRelationWithHolder = getRelationWithHolder;
    vm.getStateList = getStateList;
    vm.instrumentTypeForClearing = instrumentTypeForClearing;
    vm.validateSortNum = validateSortNum;
    vm.validateMultiples = validateMultiples;
    vm.calculateSum = calculateSum;
    vm.calculateSumM = calculateSumM;
    vm.submitAddForm = submitAddForm;
    vm.validateSubscriptionAmount = validateSubscriptionAmount;
    //        vm.submitAddAccountOpening = submitAddAccountOpening;
    vm.decimalConvert = decimalConvert;
    vm.validateInstNum = validateInstNum;
    vm.getCustomerIdForVerify = getCustomerIdForVerify;
    vm.getAccountNumberForVerify = getAccountNumberForVerify;
    vm.getVerifyDeleteSelect = getVerifyDeleteSelect;
    vm.getInquirySelect = getInquirySelect;
    vm.getCustomerIdForInquiry = getCustomerIdForInquiry;
    vm.getAccountNumberForInquiry = getAccountNumberForInquiry;
    vm.submitVerifyForm = submitVerifyForm;
    vm.submitDeleteForm = submitDeleteForm;
    vm.getAccountNumberForModify = getAccountNumberForModify;
    vm.getCustomerIdForModify = getCustomerIdForModify;
    vm.getModifySelect = getModifySelect;
    vm.submitModifyNominationForm = submitModifyNominationForm;
    vm.submitModify = submitModify;
    vm.calculateAge = calculateAge;
    vm.instrumentDateValidation = instrumentDateValidation;
    vm.getSchemeTypeListOperativeNominee = getSchemeTypeListOperativeNominee;
    vm.getAccountPage = getAccountPage;
    vm.getNominationPage = getNominationPage;
    vm.getMatrixPage = getMatrixPage;
    vm.getDebitAccountName = getDebitAccountName;
    vm.getCbsDetails = getCbsDetails;
    vm.neftPlaceholderFlgOut;
    vm.getDDPOAccountNumberForModify = getDDPOAccountNumberForModify;
    vm.submitModifyInterestDetail = submitModifyInterestDetail;
    vm.getCashValidation = getCashValidation;
    vm.getTranCodeDetails = getTranCodeDetails;
    vm.getBankCodeDetails = getBankCodeDetails;
    vm.getZoneCodeDetails = getZoneCodeDetails;
    //  vm.getBranchCodeDetails = getBranchCodeDetails;
    vm.getCodeDescription = getCodeDescription;
    vm.dateOfBirthOptionsFunc = dateOfBirthOptionsFunc;
    vm.dateOfBirthOptionsFuncMajor = dateOfBirthOptionsFuncMajor;
    vm.getMicrFlag = getMicrFlag;
    vm.instrumentAmountValidation = instrumentAmountValidation;
    vm.setIdValidation = setIdValidation;
    vm.zoneDateValidation = zoneDateValidation;
    vm.validateSubscriptionAmountM = validateSubscriptionAmountM;
    vm.depositAmountValidation = depositAmountValidation;
    vm.getSchemeTypeListOperative = getSchemeTypeListOperative;
    vm.getInstrumentType = getInstrumentType;
    vm.getTranCode = getTranCode;
    vm.getZoneCode = getZoneCode;
    vm.getBankCode = getBankCode;
    vm.getBranchCode = getBranchCode;
    vm.getZoneBankCode = getZoneBankCode;
    vm.getZoneBranchCode = getZoneBranchCode;
    vm.getRelationWithHolder = getRelationWithHolder;
    vm.getStateList = getStateList;
    vm.instrumentTypeForClearing = instrumentTypeForClearing;
    vm.validateSortNum = validateSortNum;
    vm.validateMultiples = validateMultiples;
    vm.calculateSum = calculateSum;
    vm.calculateSumM = calculateSumM;
    vm.validateSubscriptionAmount = validateSubscriptionAmount;
    //        vm.submitAddAccountOpening = submitAddAccountOpening;
    vm.decimalConvert = decimalConvert;
    vm.validateInstNum = validateInstNum;
    vm.getCustomerIdForVerify = getCustomerIdForVerify;
    vm.getAccountNumberForVerify = getAccountNumberForVerify;
    vm.getVerifyDeleteSelect = getVerifyDeleteSelect;
    vm.getInquirySelect = getInquirySelect;
    vm.getCustomerIdForInquiry = getCustomerIdForInquiry;
    vm.getAccountNumberForInquiry = getAccountNumberForInquiry;
    vm.submitVerifyForm = submitVerifyForm;
    vm.submitDeleteForm = submitDeleteForm;
    vm.getAccountNumberForModify = getAccountNumberForModify;
    vm.getCustomerIdForModify = getCustomerIdForModify;
    vm.getModifySelect = getModifySelect;
    vm.submitModifyNominationForm = submitModifyNominationForm;
    vm.submitModify = submitModify;
    vm.calculateAge = calculateAge;
    vm.instrumentDateValidation = instrumentDateValidation;
    vm.getSchemeTypeListOperativeNominee = getSchemeTypeListOperativeNominee;
    vm.getAccountPage = getAccountPage;
    vm.getNominationPage = getNominationPage;
    vm.getMatrixPage = getMatrixPage;
    vm.getDebitAccountName = getDebitAccountName;
    vm.getCbsDetails = getCbsDetails;
    vm.neftPlaceholderFlgOut;
    vm.textableDatePicker=textableDatePicker;
    vm.autoslashing=autoslashing;
vm.textableDatePickerYear=textableDatePickerYear;
vm.futureNotAllowed=futureNotAllowed;
function futureNotAllowed(id,valueDate)
{
 commonService.futureNotAllowed(id,valueDate);
 var today = new Date();
       var birthDate = new Date(valueDate);
       var age = today.getFullYear() - birthDate.getFullYear();
       var m = today.getMonth() - birthDate.getMonth();
       if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
         age--;
       }
       vm.isMinor = age < 18;
        if (!vm.isMinor && vm.modeOfHolding === 'MH005') {
           vm.fromDate1 = null;
           Swal.fire("Warning","Legal Guardian is not allowed for above 18 years.","warning");
         }else if(valueDate){
             var today = new Date();
             today.setHours(0, 0, 0, 0);
             var birthDate = new Date(valueDate);
             if (isNaN(birthDate.getTime())) {
                 return false;
             }
             birthDate.setHours(0, 0, 0, 0);
             // Future date validation
             if (birthDate > today) {
                 if (id === 'effFromDate1') {
                     vm.fromDate1 = null;
                 }
                 document.getElementById(id).value = '';
                 Swal.fire("Error!", "Please don't enter future date ", "error");
                 return false;
             }
             }
}

function textableDatePickerYear(id,valueDate){
 commonService.textableDatePicker(id,valueDate);
 // Pattern validation after commonService
     var dateValue = document.getElementById(id).value;
     if (dateValue) {
         var datePattern = /^(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[0-2])\/(17|18|19|20|21)\d\d$/;
         if (!datePattern.test(dateValue)) {
             if (id === 'effFromDate1') {
             vm.fromDate1 = null;
             }else if(id === 'dateofBirth1'){
             vm.fromDate1 = null;
             }else if(id === 'effFromDate2'){
             vm.fromDate2 = null;
             }else if(id === 'effFromDate3'){
             vm.fromDate3 = null;
             }
             document.getElementById(id).value = '';
             Swal.fire("Error!", "Please enter a valid date in DD/MM/YYYY format.", "error");
             return false;
         }
     }
     return true;
}
    function textableDatePicker(id,valueDate)
    {
     commonService.textableDatePicker(id,valueDate);
    }
    function autoslashing(id)
    {
     commonService.autoslashing(id);
    }


    vm.getDDPOAccountNumberForModify = getDDPOAccountNumberForModify;
    vm.submitModifyInterestDetail = submitModifyInterestDetail;
    vm.getCashValidation = getCashValidation;
    vm.getTranCodeDetails = getTranCodeDetails;
    vm.getBankCodeDetails = getBankCodeDetails;
    vm.getZoneCodeDetails = getZoneCodeDetails;
    //  vm.getBranchCodeDetails = getBranchCodeDetails;
    vm.getCodeDescription = getCodeDescription;
    vm.dateOfBirthOptionsFunc = dateOfBirthOptionsFunc;
    vm.getMicrFlag = getMicrFlag;
    vm.instrumentAmountValidation = instrumentAmountValidation;
    vm.setIdValidation = setIdValidation;
    vm.zoneDateValidation = zoneDateValidation;

    vm.getModuleNameAdd = getModuleNameAdd;
    //        vm.getAddBlaTorch = getAddBlaTorch;
    vm.getAddBlaDetails = getAddBlaDetails;
    vm.convertLabelValue = convertLabelValue;
    vm.getApplicationNoTorch = getApplicationNoTorch;
    vm.getApplicationDetails = getApplicationDetails;
    vm.getBrokerTorch = getBrokerTorch;
    vm.getModifyBrokerTorch = getModifyBrokerTorch;
    vm.getPantorch = getPantorch;
    vm.getModeOfInterestPayment = getModeOfInterestPayment;
    //        vm.validateOperativeAccNum = validateOperativeAccNum;
    vm.validateOperativeAccNumM = validateOperativeAccNumM;
    vm.validateOperativeAccNumNominee = validateOperativeAccNumNominee;
    vm.getGuardianDetailsMetaData = getGuardianDetailsMetaData;
    vm.modeOfInterestPaymentValidation = modeOfInterestPaymentValidation;
    vm.modeOfInterestPaymentValidationM = modeOfInterestPaymentValidationM;
    vm.validateNominee = validateNominee;
    vm.emailPattern = /[^@]+@[^@]+\.[a-zA-Z]{2,6}/;
    //        vm.inquiryBlaTorch = inquiryBlaTorch;
    vm.inquirySubscriptionTorch = inquirySubscriptionTorch;
    vm.showNomineeConfirmation = showNomineeConfirmation;
    vm.alert = alert;
    vm.verifyBlaTorch = verifyBlaTorch;
    vm.verifySubscriptionTorch = verifySubscriptionTorch;
    vm.modifyBlaTorch = modifyBlaTorch;
    vm.modifySubscriptionTorch = modifySubscriptionTorch;
    vm.validateOperativeAccNumForDebit = validateOperativeAccNumForDebit;
    vm.validateOperativeAccNumForDebitModify = validateOperativeAccNumForDebitModify;

    vm.getOtherBankDetailsMicrTorchM = getOtherBankDetailsMicrTorchM;
    vm.isMinor = isMinor;
    vm.updateMinorStatusForAll = updateMinorStatusForAll;
    vm.getAddressOne = getAddressOne;
    vm.getAddressTwo = getAddressTwo;
    vm.getAddressThree = getAddressThree;
    vm.getAddressNominee = getAddressNominee;
    vm.getAddressGuardianNominee = getAddressGuardianNominee;

    vm.getModAddressOne = getModAddressOne;
    vm.getModAddressTwo = getModAddressTwo;
    vm.getModAddressThree = getModAddressThree;
    vm.getModAddressNominee = getModAddressNominee;
    vm.getModAddressGuardianNominee = getModAddressGuardianNominee;

    vm.updateMinorStatus2 = updateMinorStatus2;
    vm.updateMinorStatus3 = updateMinorStatus3;

    //
    getCurrentFinancialYear();

    vm.loginSolDate = $rootScope.globals.currentUser.solDate;

    vm.genderListMetaData = [
      {
        label: "MALE",
        value: "M",
      },
      {
        label: "FEMALE",
        value: "F",
      },
      {
        label: "OTHERS",
        value: "O",
      },
    ];

    vm.supportingDocumentsMetaData = [
      {
        label: "No",
        value: "N",
      },
      {
        label: "Yes",
        value: "Y",
      },
    ];
    vm.yesNo = [
      {
        label: "No",
        value: "N",
      },
      {
        label: "Yes",
        value: "Y",
      },
    ];
    vm.bondTypeMetaData = [
      {
        label: "Cummulative",
        value: "C",
      },
      {
        label: "Non-Cummulative",
        value: "NC",
      },
      {
        label: "Both",
        value: "B",
      },
    ];

    vm.stateMetaData = [
          { label: "JAMMU AND KASHMIR", value: "S001" },
          { label: "HIMACHAL PRADESH", value: "S002" },
          { label: "PUNJAB", value: "S003" },
          { label: "CHANDIGARH", value: "S004" },
          { label: "UTTARANCHAL", value: "S005" },
          { label: "HARYANA", value: "S006" },
          { label: "NEW DELHI", value: "S007" },
          { label: "RAJASTHAN", value: "S008" },
          { label: "UTTAR PRADESH", value: "S009" },
          { label: "BIHAR", value: "S010" },
          { label: "SIKKIM", value: "S011" },
          { label: "ARUNACHAL PRADESH", value: "S012" },
          { label: "NAGALAND", value: "S013" },
          { label: "MANIPUR", value: "S014" },
          { label: "MIZORAM", value: "S015" },
          { label: "TRIPURA", value: "S016" },
          { label: "MEGHALAYA", value: "S017" },
          { label: "ASSAM", value: "S018" },
          { label: "WEST BENGAL", value: "S019" },
          { label: "JHARKHAND", value: "S020" },
          { label: "ORISSA", value: "S021" },
          { label: "CHHATTISGARH", value: "S022" },
          { label: "MADHYA PRADESH", value: "S023" },
          { label: "GUJARAT", value: "S024" },
          { label: "DIU AND DAMAN", value: "S025" },
          { label: "DADRA AND NAGAR HAVELI", value: "S026" },
          { label: "MAHARASHTRA", value: "S027" },
          { label: "KARNATAKA", value: "S029" },
          { label: "GOA", value: "S030" },
          { label: "LAKSHADWEEP", value: "S031" },
          { label: "KERALA", value: "S032" },
          { label: "TAMIL NADU", value: "S033" },
          { label: "PUDUCHERRY", value: "S034" },
          { label: "ANDAMAN AND NICOBAR ISLANDS", value: "S035" },
          { label: "TELANGANA", value: "S036" },
          { label: "ANDHRA PRADESH", value: "S037" },
          { label: "UTTARAKHAND", value: "S038" },
          { label: "DELHI", value: "S039" },
          { label: "ODISHA", value: "S040" },
          { label: "OTHER COUNTRY", value: "S096" },
          { label: "OTHER TERRITORY", value: "S097" }
        ];

    // Initialize modeOfHoldingList with data
    vm.modeOfHoldingList = [
      {
        DESCRIPTION: "SINGLE",
        SUBCODE: "MH001",
      }
    ];
    vm.selectedModeOfHolding = "MH001";
    vm.blaTypeList = [
      {
        DESCRIPTION:"NEW",
        SUBCODE: "N",
      },
      {
        DESCRIPTION:"EXISTING",
        SUBCODE: "E",
      }
    ];
    if ($state.current.name == "mode_of_holding_change.add_detail") {
      if ($stateParams.obj) {
        vm.modeOfHolding = $stateParams.obj.modeOfHolding;
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.blaFlag = $stateParams.obj.blaFlag;
        vm.panNo = $stateParams.obj.panNo;
        vm.blaType = $stateParams.obj.blaType;
        vm.bondHolderName = $stateParams.obj.bondHolderName;
        if(null!=$stateParams.obj.fromDate1){vm.fromDate1 = $stateParams.obj.fromDate1;}
        vm.blaNo = $stateParams.obj.blaNo;
        getCurrentFinancialYear();
        titleChange();
        getTypeofHolding();
        getFormDetails();
        getKycDetails();
        getGuardianDetailsMetaData();
        getTransactionModes();
        getSchemeTypeListOperative();
        if(vm.blaType==='E'){
        addDet();
        getInquiryRoles();
        }else{
        if(vm.fromDate1){
         vm.customerMasterDetails.bondHldDob = convertDateFormat($filter("date")(vm.fromDate1, "dd-MMM-yyyy"));}
          const nameComponents = splitName(vm.bondHolderName);
          vm.customerMasterDetails.bondHldFirstName = nameComponents.firstName;
          vm.customerMasterDetails.bondHldMidName = nameComponents.middleName;
          vm.customerMasterDetails.bondHldLastName = nameComponents.lastName;
          vm.customerMasterDetails.panNo = vm.panNo;
          getInquiryRoles();
          addDet();
          }
        if (vm.bondHolderName) {
          splitName(vm.bondHolderName);
        }
        if (vm.secondJointHolderName) {
          splitName(vm.secondJointHolderName);
        }
        if (vm.thirdJointHolderName) {
          splitName(vm.thirdJointHolderName);
        }
        getModeOfInterestPayment();
        getSegment();
        getAccountType();
        getState();
        if(null!=$stateParams.obj.fromDate1){isMinorSingle();}
        updateMinorStatusForAll();
        validateNominee();
      }
    }
    else if ($state.current.name == "mode_of_holding_change.verify_detail") {
      if ($stateParams.obj) {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.fromBlaNo = $stateParams.obj.fromBlaNo;
        vm.blaNo = $stateParams.obj.blaNo;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.modeOfHolding = $stateParams.obj.modeOfHolding;
        vm.panNo = $stateParams.obj.panNo;
        vm.bondHolderName = $stateParams.obj.bondHolderName;
        getVerifySelectDetails("select");
        getCurrentFinancialYear();
        titleChange();
        getTypeofHolding();
        getFormDetails();
        getKycDetails();
        getGuardianDetailsMetaData();
        getModeofHolding();
        getState();
        getModeOfInterestPayment();
        getSegment();
        getAccountType();
        getTransactionModes();
      }
    } else if ($state.current.name == "mode_of_holding_change.delete_detail") {
      if ($stateParams.obj) {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.blaNo = $stateParams.obj.blaNo;
        vm.schemeName = $stateParams.obj.schemeName;
        getVerifySelectDetails("select");
        getCurrentFinancialYear();
        getTypeofHolding();
        getFormDetails();
        getKycDetails();
        getGuardianDetailsMetaData();
        getModeofHolding();
        getState();
      }
    } else if ($state.current.name == "mode_of_holding_change.list_detail") {
      if ($stateParams.obj) {
        vm.fromBlaNo = $stateParams.obj.fromBlaNo;
        vm.blaNo = $stateParams.obj.blaNo;
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.panNo = $stateParams.obj.panNo;
        vm.bondHolderName = $stateParams.obj.bondHolderName;
        getInquirySelectDetails("select");
        getCurrentFinancialYear();
        titleChange();
        getTypeofHolding();
        getFormDetails();
        getKycDetails();
        getGuardianDetailsMetaData();
        getModeofHolding();
        getState();
        getModeOfInterestPayment();
        getSegment();
        getAccountType();
        getTransactionModes();
      }
    } else if ($state.current.name == "mode_of_holding_change.add") {
      getDashBoard();
    } else if ($state.current.name == "mode_of_holding_change.add_input") {
      if ($stateParams.obj) {
        vm.id = $stateParams.obj.id;
        vm.modeOfHolding = $stateParams.obj.modeOfHolding;
        vm.noOfJointAcc = $stateParams.obj.noOfJointAcc;
        vm.flag = $stateParams.obj.flag;
        getDashBoard();
      }
    }

    function redirectToInputPage(state) {
      $state.go(state);
    }

    function convertLabelValue(list) {
      for (var item in list) {
        list[item].value = list[item].SUBCODE;
        list[item].label = list[item].DESCRIPTION;
      }
      return list;
    }

    function fromToDateValidation() {
      // Assuming vm.currentDate is defined elsewhere
      vm.currentDate = new Date(); // Ensure current date is set

      // Set min/max date options
      vm.dateToOptions = {
        minDate: vm.fromDate,
        maxDate: vm.currentDate,
      };

      vm.valueDateOptions = {
        minDate: vm.accountOpeningDate, // Min date based on Account Open Date
        maxDate: vm.currentDate, // Set maximum date to current date
      };

      // Reset value date if it violates the new minDate
      if (vm.valueDate && new Date(vm.valueDate) < vm.valueDateOptions.minDate) {
        vm.valueDate = null; // Reset value date if it violates minDate
      }
    }
    function getAddPanTorchBlaNoTorchDetails(flg) {
      if(null!=vm.schemeCode){
        vm.loader = true;
        if(flg==='P'){
          var requestData = {
            'schemeCode':vm.schemeCode,
            'modeOfHolding':vm.selectedModeOfHolding,
            'blaNo':vm.blaNo,
            'panNum':vm.panNo,
            'flag':flg
          };
        }else{
          var requestData = {
            'schemeCode':vm.schemeCode,
            'modeOfHolding':vm.selectedModeOfHolding,
            'blaNo':vm.blaNo,
            'flag':flg
          };
        }
        modeOfHoldingService.getAddPanTorchBlaNoTorchDetails(requestData).then(function (response) {
          vm.loader = false;
          if(flg==='P'){
            vm.PanTorch = response.data.result[0];
            vm.bondHolderName=vm.PanTorch.panHldName;
            if (vm.PanTorch.fromDate1) {
              vm.fromDate1 = parseFlexibleDate(vm.PanTorch.fromDate1);
            }
            // vm.fromDate1=vm.PanTorch.fromDate1;
            vm.panNo=vm.PanTorch.panNum;
          }else{
            vm.blaDataList = response.data.result;
            angular.element('#blaModel').modal('toggle');
          }
        }, function (error) {
          vm.loader = false;
          if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
            vm.noDataFound = error.data.message;
            Swal.fire("Warning!",vm.noDataFound,"warning");
          }
          else{
            handleErrorResponse(error);
            $state.go("mode_of_holding_change.inquiry");
          }
        });
      }else{
        Swal.fire("Warning!","Kindly Select SchemeName", "warning");
        $state.go('mode_of_holding_change.add_input');
      }
    }
     function getVerifyPanTorchBlaNoTorchDetails(flg) {
          if(null!=vm.panNo&&vm.panNo.length==10){
            if(!vm.panPattern.test(vm.panNo.toUpperCase())){
              Swal.fire("Warning!","INVALID PAN NO", "warning");
              return false;
            }
          }
          if(null!=vm.schemeCode){
            vm.loader = true;
            if(flg==='P'){
              var requestData = {
                'schemeCode':vm.schemeCode,
                'modeOfHolding':vm.selectedModeOfHolding,
                'blaType':vm.blaType,
                'blaNo':vm.blaNo,
                'panNum':vm.panNo,
                'bondHolderName':vm.bondHolderName,
                'flag':flg
              };
            }else if(flg==='F'){
               var requestData = {
                 'schemeCode':vm.schemeCode,
                 'modeOfHolding':vm.selectedModeOfHolding,
                 'blaType':vm.blaType,
                 'fromBlaNo':vm.fromBlaNo,
                 'panNum':vm.panNo,
                 'bondHolderName':vm.bondHolderName,
                 'flag':flg
               };
             }else{
              var requestData = {
                'schemeCode':vm.schemeCode,
                'modeOfHolding':vm.selectedModeOfHolding,
                'blaType':vm.blaType,
                'fromBlaNo':vm.fromBlaNo,
                'blaNo':vm.blaNo,
                'panNum':vm.panNo,
                'bondHolderName':vm.bondHolderName,
                'flag':flg
              };
            }
            modeOfHoldingService.getVerifyPanTorchBlaNoTorchDetails(requestData).then(function (response) {
              vm.loader = false;
              if(flg==='P'){
                vm.PanTorch = response.data.result;
                angular.element('#panModel').modal('toggle');
              }else if(flg==='F'){
                vm.fromBlaDataList = response.data.result;
                angular.element('#fromBlaModel').modal('toggle');
              }else{
                vm.blaDataList = response.data.result;
                angular.element('#blaModel').modal('toggle');
              }
            }, function (error) {
              vm.loader = false;
              if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
                vm.noDataFound = error.data.message;
                Swal.fire("Warning!",vm.noDataFound,"warning");
              }
              else{
                handleErrorResponse(error);
                $state.go("mode_of_holding_change.inquiry");
              }
            });
          }else{
            Swal.fire("Warning!","Kindly Select SchemeName", "warning");
            $state.go('mode_of_holding_change.verify');
          }
        }
        
    // Helper function to parse dates in multiple formats (dd/MM/yyyy, dd-MM-yyyy, dd-MMM-yyyy, timestamp)
    function parseFlexibleDate(dateValue) {
      if (!dateValue) return null;
      
      // If already a Date object
      if (dateValue instanceof Date) {
        return dateValue;
      }
      
      // If it's a number (timestamp)
      if (typeof dateValue === 'number') {
        return new Date(dateValue);
      }
      
      // If it's a string
      if (typeof dateValue === 'string') {
        var monthMap = {
          'JAN': 0, 'FEB': 1, 'MAR': 2, 'APR': 3, 'MAY': 4, 'JUN': 5,
          'JUL': 6, 'AUG': 7, 'SEP': 8, 'OCT': 9, 'NOV': 10, 'DEC': 11
        };
        
        // Try dd/MM/yyyy format
        if (dateValue.indexOf('/') > -1) {
          var parts = dateValue.split('/');
          if (parts.length === 3) {
            return new Date(parseInt(parts[2]), parseInt(parts[1]) - 1, parseInt(parts[0]));
          }
        }
        
        // Try dd-MM-yyyy or dd-MMM-yyyy format
        if (dateValue.indexOf('-') > -1) {
          var parts = dateValue.split('-');
          if (parts.length === 3) {
            var day = parseInt(parts[0]);
            var year = parseInt(parts[2]);
            var month;
            
            // Check if month is numeric or text
            if (isNaN(parts[1])) {
              month = monthMap[parts[1].toUpperCase()];
            } else {
              month = parseInt(parts[1]) - 1;
            }
            
            if (!isNaN(day) && month !== undefined && !isNaN(year)) {
              return new Date(year, month, day);
            }
          }
        }
        
        // Try parsing as ISO format or other standard formats
        var parsed = new Date(dateValue);
        if (!isNaN(parsed.getTime())) {
          return parsed;
        }
      }
      
      console.error('Could not parse date:', dateValue);
      return null;
    }
         function getInquiryPanTorchBlaNoTorchDetails(flg) {
              if(null!=vm.panNo&&vm.panNo.length==10){
                if(!vm.panPattern.test(vm.panNo.toUpperCase())){
                  Swal.fire("Warning!","INVALID PAN NO", "warning");
                  return false;
                }
              }
              if(null!=vm.schemeCode){
                vm.loader = true;
                if(flg==='P'){
                  var requestData = {
                    'schemeCode':vm.schemeCode,
                    'modeOfHolding':vm.selectedModeOfHolding,
                    'blaType':vm.blaType,
                    'blaNo':vm.blaNo,
                    'panNum':vm.panNo,
                    'bondHolderName':vm.bondHolderName,
                    'flag':flg
                  };
                }else if(flg==='F'){
                   var requestData = {
                     'schemeCode':vm.schemeCode,
                     'modeOfHolding':vm.selectedModeOfHolding,
                     'blaType':vm.blaType,
                     'fromBlaNo':vm.fromBlaNo,
                     'panNum':vm.panNo,
                     'bondHolderName':vm.bondHolderName,
                     'flag':flg
                   };
                 }else{
                  var requestData = {
                    'schemeCode':vm.schemeCode,
                    'modeOfHolding':vm.selectedModeOfHolding,
                    'blaType':vm.blaType,
                    'fromBlaNo':vm.fromBlaNo,
                    'blaNo':vm.blaNo,
                    'panNum':vm.panNo,
                    'bondHolderName':vm.bondHolderName,
                    'flag':flg
                  };
                }
                modeOfHoldingService.getInquiryPanTorchBlaNoTorchDetails(requestData).then(function (response) {
                  vm.loader = false;
                  if(flg==='P'){
                    vm.PanTorch = response.data.result;
                    angular.element('#panModel').modal('toggle');
                  }else if(flg==='F'){
                       vm.fromBlaDataList = response.data.result;
                       angular.element('#fromBlaModel').modal('toggle');
                     }else{
                    vm.blaDataList = response.data.result;
                    angular.element('#blaModel').modal('toggle');
                  }
                }, function (error) {
                  vm.loader = false;
                  if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
                    vm.noDataFound = error.data.message;
                    Swal.fire("Warning!",vm.noDataFound,"warning");
                  }
                  else{
                    handleErrorResponse(error);
                    $state.go("mode_of_holding_change.inquiry");
                  }
                });
              }else{
                Swal.fire("Warning!","Kindly Select SchemeName", "warning");
                $state.go('mode_of_holding_change.inquiry');
              }
            }
            vm.indianNumberFormat=indianNumberFormat;
                            function indianNumberFormat(value) {
                              if (isNaN(value)|| null == value) return value;
                              let numStr = value.toString();
                              let [whole, decimal] = numStr.split('.');
                              if (whole.length <= 3) {
                                  return decimal ? whole + '.' + decimal : whole + '.00';
                                }
                              let lastThree = whole.slice(-3);  // last three digits
                              let otherNumbers = whole.slice(0, whole.length - 3);
                              if (otherNumbers !== '') {
                                lastThree = ',' + lastThree;
                              }
                              let formattedWhole = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ',') + lastThree;
                            if (decimal) {
                                return formattedWhole + '.' + decimal;
                              } else {
                                return formattedWhole+'.00';
                              }
                            }
    function formFifteenGValidation(value) {
      if (vm.customerMasterDetails.bondHldDob) {
        vm.customerMasterDetails.dateSubmission15g15htax = "";
        var year = parseInt($filter("date")(vm.customerMasterDetails.bondHldDob, "yyyy"));
        var cYear = parseInt($filter("date")(new Date(), "yyyy"));
        var age = cYear - year;
        if (value == "TF001") {
          //15g
          if (age > 60) {
            Swal.fire("Error!", "Age of the holder is NOT BELOW 60 years", "error");
            vm.customerMasterDetails.form15g15htaxExeCerti = "";
          }
          vm.customerMasterDetails.tdsRateApplicable = "0";
        }
        if (value == "TF002") {
          //15h
          if (age <= 60) {
            Swal.fire("Error!", "Age of the holder is BELOW 60 years", "error");
            vm.customerMasterDetails.form15g15htaxExeCerti = "";
          }
          vm.customerMasterDetails.tdsRateApplicable = "0";
        }
        if (value == "TF003") {
          //tax excemption cert
          vm.customerMasterDetails.tdsRateApplicable = "0";
        }
        if (value == "NONE") {
          vm.customerMasterDetails.tdsRateApplicable = vm.tdsRateApplicableResponse;
        }
      } else {
        Swal.fire("Error!", "Please select a age", "error");
        vm.customerMasterDetails.form15g15htaxExeCerti = "";
      }
    }

    function nonHdfcTorch(flag) {
      if (flag == "torch") {
        vm.loader = true;
        vm.searchSolId = "";
        var requestData = {};
        modeOfHoldingService.getNonHdfcTorch(requestData).then(
          function (response) {
            vm.loader = false;
            vm.nonHdfcTorchList = response.data.result.torchValue;
            angular.element("#rbiCodeModal").modal("toggle");
          },
          function (error) {
            handleErrorResponse(error);
            vm.loader = false;
          }
        );
      } else {
        vm.loader = true;
        vm.searchSolId = "";
        var requestData = {
          bankCodeNonMicr: vm.bankCodeNonMicr,
        };
        modeOfHoldingService.getNonHdfcBranchTorch(requestData).then(
          function (response) {
            vm.loader = false;
            vm.nonHdfcTorchList = response.data.result.torchValue;
            angular.element("#rbiCodeModal1").modal("toggle");
          },
          function (error) {
            handleErrorResponse(error);
            vm.loader = false;
          }
        );
      }
    }
    vm.validateInput = validateInput;

    function validateInput(event, id) {
      const textBox = document.getElementById(id);
      var charCode = event.which || event.keyCode;
      var charTyped = String.fromCharCode(charCode);
      if (textBox) {
        const cursorPosition = textBox.selectionStart;
        if (cursorPosition == 0 && charTyped === " ") {
          event.preventDefault();
          return;
        }
      }
      var currentValue = event.target.value + charTyped;
      var pattern = /^[a-zA-Z\s]*'?([a-zA-Z\s]*'?)*$/;
      if (!pattern.test(currentValue)) {
        event.preventDefault();
      }
    }

    function formFifteenGValidationM(value) {
      if (vm.customerMasterDetails.C013.oldValue) {
        var year = parseInt($filter("date")(vm.customerMasterDetails.C013.oldValue, "yyyy"));
        var cYear = parseInt($filter("date")(new Date(), "yyyy"));
        var age = cYear - year;
        if (value == "TF001") {
          //15g
          if (age > 60) {
            Swal.fire("Error!", "Age of the holder is NOT BELOW 60 years", "error");
            vm.customerMasterDetails.C038.oldValue = "";
          }
          vm.customerMasterDetails.C064.oldValue = "0";
        }
        if (value == "TF002") {
          //15h
          if (age <= 60) {
            Swal.fire("Error!", "Age of the holder is BELOW 60 years", "error");
            vm.customerMasterDetails.C038.oldValue = "";
          }
          vm.customerMasterDetails.C064.oldValue = "0";
        }
        if (value == "TF003") {
          //tax excemption cert
          vm.customerMasterDetails.C064.oldValue = "0";
        }
        if (value == "NONE") {
          try {
            vm.customerMasterDetails.C039.oldValue = "";
          } catch (e) {}

          vm.customerMasterDetails.C064.oldValue = vm.tdsRateApplicableResponse;
        }
      } else {
        Swal.fire("Error!", "Please select a age", "error");
        vm.customerMasterDetails.C038.oldValue = "";
      }
    }

    function splitName(fullName) {
      fullName = fullName.trim();

      // Split the name by spaces
      let nameParts = fullName.split(/\s+/); // Split by one or more spaces

      let firstName = "";
      let middleName = "";
      let lastName = "";

      if (nameParts.length === 1) {
        firstName = nameParts[0];
      } else if (nameParts.length === 2) {
        firstName = nameParts[0];
        lastName = nameParts[1];
      } else if (nameParts.length >= 3) {
        // If there are three or more parts
        firstName = nameParts[0];
        lastName = nameParts[nameParts.length - 1];
        middleName = nameParts.slice(1, nameParts.length - 1).join(" "); // Join the middle parts
      }

      return {
        firstName,
        middleName,
        lastName,
      };
    }

    function addRanges() {
      if (!vm.coparcenerslist || !Array.isArray(vm.coparcenerslist)) {
        vm.coparcenerslist = [];
      }

      vm.coparcenerslist.push({});
      vm.enableDelete = true;
    }

    function removeRanges(id, index) {
      if (!vm.coparcenerslist || !Array.isArray(vm.coparcenerslist) || index < 0 || index >= vm.coparcenerslist.length) {
        return;
      }

      vm.coparcenerslist.splice(index, 1);

      if (id) {
        vm.resultDelArray.push({
          colName: "Id",
          value: id,
        });
      }

      if (vm.coparcenerslist.length === 0) {
        vm.enableDelete = false;
      }
    }

    function alert(item) {
      for (let i = 0; i < vm.schemeList.length; i++) {
        if (vm.schemeList[i].subCode == item) {
          vm.schemeName = vm.schemeList[i].subCodeDescription;
          //vm.cusName = vm.custList[i].cusName;
          //  vm.cusAlphaCode = vm.custList[i].cusAlphaCode;
          // vm.id = vm.custList[i].id;
        }
      }
    }

    function deleteGuardian() {
      if (vm.guardianDetails.length > 0) {
        vm.guardianDetails.splice(0, 1);
        if (vm.guardianDetails.length === 0) {
          vm.showGuardianDetails = false;
        }
      }
    }

    vm.guardianDetails = [];
    vm.showGuardianDetails = false;

    function addGuardian() {
      if (vm.guardianDetailsValueModel.C014 && vm.guardianDetailsValueModel.C014.oldValue) {
        // Log data before adding
        console.log("Adding Guardian Details: ", vm.guardianDetailsValueModel);

        vm.guardianDetails.push(angular.copy(vm.guardianDetailsValueModel));

        console.log("Updated Guardian Details Array: ", vm.guardianDetails);

        vm.showGuardianDetails = true;
        vm.guardianDetailsValueModelOne.C003.oldValue = vm.guardianDetailsValueModel.C003.oldValue;
        vm.guardianDetailsValueModelOne = {
          C003: {
            oldValue: vm.guardianDetailsValueModelOne.C003.oldValue,
          },
          C005: {
            oldValue: vm.guardianDetailsValueModelOne.C005.oldValue,
          },
          C006: {
            oldValue: vm.guardianDetailsValueModelOne.C006.oldValue,
          },
          C007: {
            oldValue: vm.guardianDetailsValueModelOne.C007.oldValue,
          },
          C008: {
            oldValue: vm.guardianDetailsValueModelOne.C008.oldValue,
          },
          C009: {
            oldValue: vm.guardianDetailsValueModelOne.C009.oldValue,
          },
          C010: {
            oldValue: vm.guardianDetailsValueModelOne.C010.oldValue,
          },
          C011: {
            oldValue: vm.guardianDetailsValueModelOne.C011.oldValue,
          },
          C012: {
            oldValue: vm.guardianDetailsValueModelOne.C012.oldValue,
          },
          C013: {
            oldValue: vm.guardianDetailsValueModelOne.C013.oldValue,
          },
          C014: {
            oldValue: vm.guardianDetailsValueModelOne.C014.oldValue,
          }, // Clear only the Date of Death of Guardian
          C015: {
            oldValue: vm.guardianDetailsValueModelOne.C015.oldValue,
          },
          C016: {
            oldValue: vm.guardianDetailsValueModelOne.C016.oldValue,
          },
        };
        vm.guardianDetails.push(convertToColumnValuesArrayList(vm.guardianDetailsValueModelOne));
      }
    }

    function isDateEntered() {
      // Check if vm.guardianDetailsValueModel exists and has the property C014 with oldValue
      return vm.guardianDetailsValueModel && vm.guardianDetailsValueModel.C014 && vm.guardianDetailsValueModel.C014.oldValue;
    }

    function isDateEntered1() {
      // Check if vm.guardianDetailsValueModel2 exists and has the property C014 with oldValue
      return vm.guardianDetailsValueModel2 && vm.guardianDetailsValueModel2.C014 && vm.guardianDetailsValueModel2.C014.oldValue;
    }

    function isDateEntered2() {
      // Check if vm.guardianDetailsValueModel3 exists and has the property C014 with oldValue
      return vm.guardianDetailsValueModel3 && vm.guardianDetailsValueModel3.C014 && vm.guardianDetailsValueModel3.C014.oldValue;
    }

    function deleteGuardianTwo() {
      if (vm.guardianDetails.length > 0) {
        vm.guardianDetails.splice(0, 1);
        if (vm.guardianDetails.length === 0) {
          vm.showGuardianDetailsTwo = false;
        }
      }
    }

    function addGuardianTwo() {
      if (vm.guardianDetailsValueModel2.C014 && vm.guardianDetailsValueModel2.C014.oldValue) {
        // Log data before adding
        console.log("Adding Guardian Details: ", vm.guardianDetailsValueModelTwo);

        // Push a copy of the new guardian details to the array
        vm.guardianDetails.push(angular.copy(vm.guardianDetailsValueModelTwo));

        // Log the updated array
        console.log("Updated Guardian Details Array: ", vm.guardianDetails);

        // Show the guardian details table
        vm.showGuardianDetailsTwo = true;
        // Clear or reset the guardianDetailsValueModel if needed
        vm.guardianDetailsValueModelTwo.C003.oldValue = vm.guardianDetailsValueModelTwo.C003.oldValue;
        vm.guardianDetailsValueModelTwo = {
          C003: {
            oldValue: vm.guardianDetailsValueModelTwo.C003.oldValue,
          },
          C005: {
            oldValue: vm.guardianDetailsValueModelTwo.C005.oldValue,
          },
          C006: {
            oldValue: vm.guardianDetailsValueModelTwo.C006.oldValue,
          },
          C007: {
            oldValue: vm.guardianDetailsValueModelTwo.C007.oldValue,
          },
          C008: {
            oldValue: vm.guardianDetailsValueModelTwo.C008.oldValue,
          },
          C009: {
            oldValue: vm.guardianDetailsValueModelTwo.C009.oldValue,
          },
          C010: {
            oldValue: vm.guardianDetailsValueModelTwo.C010.oldValue,
          },
          C011: {
            oldValue: vm.guardianDetailsValueModelTwo.C011.oldValue,
          },
          C012: {
            oldValue: vm.guardianDetailsValueModelTwo.C012.oldValue,
          },
          C013: {
            oldValue: vm.guardianDetailsValueModelTwo.C013.oldValue,
          },
          C014: {
            oldValue: vm.guardianDetailsValueModelTwo.C014.oldValue,
          }, // Clear only the Date of Death of Guardian
          C015: {
            oldValue: vm.guardianDetailsValueModelTwo.C015.oldValue,
          },
          C016: {
            oldValue: vm.guardianDetailsValueModelTwo.C016.oldValue,
          },
        };
        vm.guardianDetails.push(convertToColumnValuesArrayList(vm.guardianDetailsValueModelTwo));
      }
    }

    function deleteGuardianThree() {
      if (vm.guardianDetails.length > 0) {
        vm.guardianDetails.splice(0, 1);
        if (vm.guardianDetails.length === 0) {
          vm.showGuardianDetailsThree = false;
        }
      }
    }

    function addGuardianThree() {
      if (vm.guardianDetailsValueModel3.C014 && vm.guardianDetailsValueModel3.C014.oldValue) {
        // Log data before adding
        console.log("Adding Guardian Details: ", vm.guardianDetailsValueModelTwo);

        // Push a copy of the new guardian details to the array
        vm.guardianDetails.push(angular.copy(vm.guardianDetailsValueModelTwo));

        // Log the updated array
        console.log("Updated Guardian Details Array: ", vm.guardianDetails);

        // Show the guardian details table
        vm.showGuardianDetailsThree = true;
        // Clear or reset the guardianDetailsValueModel if needed
        vm.guardianDetailsValueModelThree.C003.oldValue = vm.guardianDetailsValueModelThree.C003.oldValue;
        vm.guardianDetailsValueModelThree = {
          C003: {
            oldValue: vm.guardianDetailsValueModelThree.C003.oldValue,
          },
          C005: {
            oldValue: vm.guardianDetailsValueModelThree.C005.oldValue,
          },
          C006: {
            oldValue: vm.guardianDetailsValueModelThree.C006.oldValue,
          },
          C007: {
            oldValue: vm.guardianDetailsValueModelThree.C007.oldValue,
          },
          C008: {
            oldValue: vm.guardianDetailsValueModelThree.C008.oldValue,
          },
          C009: {
            oldValue: vm.guardianDetailsValueModelThree.C009.oldValue,
          },
          C010: {
            oldValue: vm.guardianDetailsValueModelThree.C010.oldValue,
          },
          C011: {
            oldValue: vm.guardianDetailsValueModelThree.C011.oldValue,
          },
          C012: {
            oldValue: vm.guardianDetailsValueModelThree.C012.oldValue,
          },
          C013: {
            oldValue: vm.guardianDetailsValueModelThree.C013.oldValue,
          },
          C014: {
            oldValue: vm.guardianDetailsValueModelThree.C014.oldValue,
          }, // Clear only the Date of Death of Guardian
          C015: {
            oldValue: vm.guardianDetailsValueModelThree.C015.oldValue,
          },
          C016: {
            oldValue: vm.guardianDetailsValueModelThree.C016.oldValue,
          },
        };
        vm.guardianDetails.push(convertToColumnValuesArrayList(vm.guardianDetailsValueModelThree));
      }
    }

    function getDashBoard() {
      vm.loader = true;

      if (vm.id == null || vm.id == undefined) {
        var requestData = {};
        modeOfHoldingService.getAddDashBoard(requestData).then(
          function (response) {
            vm.loader = false;
            vm.netBankingList = response.data.result;
            if (vm.netBankingList) {
              Swal.fire("Warning!", response.data.message, "warning");
            }
          },
          function (error) {
            vm.loader = false;
            handleErrorResponse(error);
          }
        );
      } else {
        var requestData = {
          id: vm.id,
          modeOfHolding: vm.modeOfHolding,
        };

        modeOfHoldingService.getAddDashBoard(requestData).then(
          (response) => {
            vm.loader = false;
            vm.netBankingList = response.data.result[0];
            vm.pan_No1 = response.data.result[0].pan_No1;
            vm.pan_No2 = response.data.result[0].pan_No2;
            vm.pan_No3 = response.data.result[0].pan_No3;
            vm.secondJointHolderName = response.data.result[0].secondJointHolderName;
            vm.bondHolderName = response.data.result[0].bondHolderName;
            vm.thirdJointHolderName = response.data.result[0].thirdJointHolderName;
            vm.modeOfHoldingCode = response.data.result[0].modeOfHolding;
            vm.schemeCode = response.data.result[0].schemeCode;
            vm.schemeName = response.data.result[0].schemeName;
            vm.moduleCode = response.data.result[0].moduleCode;
            vm.ftsAppNum = response.data.result[0].ftsAppNum;
            vm.noOfJointAcc = response.data.result[0].noOfJointAcc;
            vm.liquidationDate = response.data.result[0].liquidationDate;
            const bondHldDob = response.data.result[0].bondHldDob;
            console.log("bondHldDob:", bondHldDob); // Log to check the value
            vm.modeOfHolding = response.data.result[0].modeOfHolding;
//            vm.modeOfHoldingList.forEach(
//                       element => {
//                           if(element.SUBCODE == vm.modeOfHoldingCode)
//                                   vm.modeOfHoldingDesc = element.DESCRIPTION;
//                       }
//                       );

            if (bondHldDob) {
              // Check if bondHldDob is defined
              const dateParts = bondHldDob.split("-");
              const monthMap = {
                JAN: 0,
                FEB: 1,
                MAR: 2,
                APR: 3,
                MAY: 4,
                JUN: 5,
                JUL: 6,
                AUG: 7,
                SEP: 8,
                OCT: 9,
                NOV: 10,
                DEC: 11,
              };

              const day = parseInt(dateParts[0], 10);
              const month = monthMap[dateParts[1]];
              const year = parseInt(dateParts[2], 10);

              vm.fromDate1 = new Date(year, month, day);
              console.log("Parsed fromDate1:", vm.fromDate1); // Log the parsed date
            } else {
              console.warn("bondHldDob is not defined or empty"); // Warn if the value is not available
            }

            if (response.data.result[0].bondHldDobTwo) {
              const bondHldDobTwo = response.data.result[0].bondHldDobTwo;

              if (bondHldDobTwo) {
                const dateParts = bondHldDobTwo.split("-");
                const monthMap = {
                  JAN: 0,
                  FEB: 1,
                  MAR: 2,
                  APR: 3,
                  MAY: 4,
                  JUN: 5,
                  JUL: 6,
                  AUG: 7,
                  SEP: 8,
                  OCT: 9,
                  NOV: 10,
                  DEC: 11,
                };

                const day = parseInt(dateParts[0], 10);
                const month = monthMap[dateParts[1]];
                const year = parseInt(dateParts[2], 10);

                vm.fromDate2 = new Date(year, month, day);
                console.log("Parsed fromDate2:", vm.fromDate2); // Log the parsed date
              } else {
                console.warn("bondHldDobTwo is not defined or empty"); // Warn if the value is not available
              }
            }
            if (response.data.result[0].bondHldDobThree) {
              const bondHldDobThree = response.data.result[0].bondHldDobThree;
              if (bondHldDobThree) {
                const dateParts = bondHldDobThree.split("-");
                const monthMap = {
                  JAN: 0,
                  FEB: 1,
                  MAR: 2,
                  APR: 3,
                  MAY: 4,
                  JUN: 5,
                  JUL: 6,
                  AUG: 7,
                  SEP: 8,
                  OCT: 9,
                  NOV: 10,
                  DEC: 11,
                };

                const day = parseInt(dateParts[0], 10);
                const month = monthMap[dateParts[1]];
                const year = parseInt(dateParts[2], 10);

                vm.fromDate3 = new Date(year, month, day);
                console.log("Parsed fromDate3:", vm.fromDate3); // Log the parsed date
              } else {
                console.warn("bondHldDobThree is not defined or empty"); // Warn if the value is not available
              }
            }
          },
          function (error) {
            vm.loader = false;
            handleErrorResponse(error);
          }
        );
      }
    }

    function validateCount(value) {
      try {
        var intee = parseInt(value);
        if (intee <= 3 && intee > 1) {
        } else {
          vm.noOfJointAcc = "";
        }
      } catch (error) {
        vm.noOfJointAcc = "";
      }
    }
    vm.getInquiryModifyModuleName=getInquiryModifyModuleName;
    function getInquiryModifyModuleName(flag) {
      vm.loader = true;
      vm.referenceId = flag;
      var requestData = {
      referenceId: vm.referenceId,
      };
      modeOfHoldingService.getInquiryModifyModuleName(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result;
          angular.element("#schemeModel").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }
    function getAddModuleName() {
      vm.loader = true;

      var requestData = {};
      modeOfHoldingService.getRbiBondModuleNameDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result;
          angular.element("#schemeModel").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }
      function getVerifyModuleName() {
          vm.loader = true;

          var requestData = {};
          modeOfHoldingService.getVerifyModuleName(requestData).then(
            function (response) {
              vm.loader = false;
              vm.schemeList = response.data.result;
              angular.element("#schemeModel").modal("toggle");
            },
            function (error) {
              handleErrorResponse(error);
              vm.loader = false;
            }
          );
        }
          function getInquiryModuleName() {
              vm.loader = true;
              var requestData = {};
              modeOfHoldingService.getInquiryModuleName(requestData).then(
                function (response) {
                  vm.loader = false;
                  vm.schemeList = response.data.result;
                  angular.element("#schemeModel").modal("toggle");
                },
                function (error) {
                  handleErrorResponse(error);
                  vm.loader = false;
                }
              );
            }

    function getMinorFlag() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        panNum: vm.panNo,
        bondHolderName: vm.bondHolderName,
        modeOfHolding: vm.selectedModeOfHolding
      };
      modeOfHoldingService.getMinorDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result.torchValue;
          if (null==vm.schemeList || vm.schemeList.length == 0) {
            Swal.fire({
              title: "No Bla Number Found",
              text: "",
              type: "warning",
              confirmButtonText: "OK",
              confirmButtonClass: "btn-primary",
              closeOnConfirm: true,
            });
          } else {
            angular.element("#MinorModel").modal("toggle");
          }
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }
    function getWboBranchCode() {
      vm.loader = true;

      var requestData = {};
      modeOfHoldingService.getWboBranchDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.getWboBranchDetails = response.data.result;
          angular.element("#branchDetailsModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function getFtsDetails() {
      vm.loader = true;
      var objRequest;

      // Define the request data object
      var requestData = {
        objRequest: {
          Cust_ID: "123456",
          Account_Number: "7890",
          Barcode: "ABC123456",
          Form_Status: "processed",
          Status: "Production",
          System_Type: "CRM",
          IsData: "Yes",
        },
      };

      var RequestBody = JSON.parse(JSON.stringify(requestData));
      modeOfHoldingService.getFtsDetails(RequestBody).then(
        function (response) {
          vm.loader = false;
          if (response.data && response.data.result) {
            vm.ftsDetails = response.data.result;
          }
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function validatePan() {
      vm.loader = true;
      var requestData = {
            panNum: vm.panNo,
            schemeCode: vm.schemeCode,
            bondHolderName: vm.bondHolderName,
            dob:$filter("date")(vm.fromDate1, "dd-MMM-yyyy")
      };
      modeOfHoldingService.validatePan(requestData).then(
        function (response) {
          vm.loader = false;
          if (response.data && response.data.result&&response.data.result.panStatus==='E'&&response.data.result.processStatus==='Y') {
            vm.panDetails = response.data.result; // Assuming outputData contains an array of results
            vm.isPanValid = true; // PAN is valid
            Swal.fire("Success!", "Pan Validation Success", "success");
          }else if (response.data==="SUCCESS"){
          vm.isPanValid = true; // PAN is valid
          Swal.fire("Success!", "Pan Validation Success", "success");
          }else {
            vm.panDetails = null;
            vm.isPanValid = true; // PAN is invalid
            Swal.fire("Success!", vm.panDetails.panStatusDescription, "success");
          }
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
          vm.isPanValid = true; // Error in PAN validation
        }
      );
    }

    vm.getDocumentDetails=getDocumentDetails;
         function getDocumentDetails() {
              var requestData = {
              };
              modeOfHoldingService.getDocumentDetails(requestData).then(
                function (response) {
                  vm.getDocumentList = response.data.result;
                  angular.forEach(vm.getDocumentList, function (value) {
                      if (null!=vm.customerMasterDetails) {
                          setDocumentFlags(vm.customerMasterDetails, value.code);
                      }
                  });
                },
                function (error) {
                  handleErrorResponse(error);
                }
              );
            }
            vm.setDocumentFlags=setDocumentFlags;
        function setDocumentFlags(target, code) {
            switch (code) {
                case "001":
                    vm.birthCertificate = true;
                    break;
                case "002":
                    vm.passport = true;
                    break;
                case "003":
                    vm.seniorCitizenCard = true;
                    break;
                case "004":
                    vm.voterIdCard = true;
                    break;
                case "005":
                    vm.panCard = true;
                    break;
                case "006":
                    vm.drivingLicence = true;
                    break;
                case "007":
                    vm.rationCard = true;
                    break;
                case "008":
                    vm.detailsForOthers = true;
                    break;
            }
        }
    vm.getBlaAccNo=getBlaAccNo;
 function getBlaAccNo() {
                                    vm.loader = true;
                                    var requestData = {
                                        'flag'       : 'FB',
                                        'panNum': vm.panNo,
                                        //'modeOfHolding': vm.modeOfHolding,
                                        'schemeCode' : vm.schemeCode,
                                        'fromBlaNo'  : vm.blaAccNo,
                                    }
                                    console.log("Payload ",requestData);
                                    modeOfHoldingService.getDetails(requestData).then(function(response) {
                                        vm.loader = false;
                                        vm.blaAccNoList = response.data.result;
                                        vm.isFromBlaValid=true;
                                        angular.element('#blaAccNoModal').modal('toggle');
                                    }, function(error) {
                                        handleErrorResponse(error);
                                        vm.loader = false;
                                    });
                                }
                                vm.getToBlaNo=getToBlaNo;
                            function getToBlaNo(toBlaNo){
                                vm.isFromBlaValid=true;
                                }
                 vm.getSubscriptionId=getSubscriptionId;
        function getSubscriptionId(blaAccNo) {
                                        vm.loader = true;
                                        var requestData = {
                                            'flag'      : 'S',
                                            'schemeCode': vm.schemeCode,
                                            'panNum': vm.panNo,
                                            'modeOfHolding': vm.modeOfHolding,
                                            'fromBlaNo' : vm.blaAccNo
                                        }
                                        modeOfHoldingService.getDetails(requestData).then(function(response) {
                                            vm.loader = false;
                                            vm.subscriptionIdList = response.data.result.cursorOut;
                                            vm.count=response.data.result.count;
                                            vm.isFromBlaValid=true;
                                        }, function(error) {
                                            handleErrorResponse(error);
                                            vm.loader = false;
                                            vm.isFromBlaValid=false;
                                        });
                                     }
         vm.getBlaAccNoTo=getBlaAccNoTo;
         function getBlaAccNoTo() {
                                             vm.loader = true;
                                             var requestData = {
                                                'flag'           : 'TB',
                                                //'fromBlaNo'      : vm.blaAccNo,
                                                'schemeCode'     : vm.schemeCode,
                                                'toBlaNo'        : vm.blaAccNoTo,
                                                'modeOfHolding':vm.modeOfHolding,
                                                'panNum':vm.panNo
                                             }
                                             modeOfHoldingService.getDetails(requestData).then(function(response) {
                                                 vm.loader = false;
                                                 vm.blaAccNoToList = response.data.result;
                                                 angular.element('#blaAccNoToModal').modal('toggle');
                                             }, function(error) {
                                                 handleErrorResponse(error);
                                                 vm.loader = false;
                                             });
                                         }
    function addDet() {
      vm.loader = true;
        var requestData = {
          schemeCode: vm.schemeCode,
          schemeName: vm.schemeName,
          flag:'D',
          modeOfHolding: vm.modeOfHolding,
          panNum: vm.panNo,
          id: vm.id,
          bondHolderName: vm.bondHolderName,
          blaNo: vm.blaNo
        };
        modeOfHoldingService.addDet(requestData).then(function (response) {
            vm.loader = false;
            if (response && response.data && response.data.result) {
             if(vm.blaNo){
              vm.blaAccNo=vm.blaNo.substring(3);
              }
              var result = response.data.result;
              if (result.dataCopersondetailsList1 && result.dataCopersondetailsList1.length > 0) {
                var firstDetails = result.dataCopersondetailsList1[0];
                vm.DetailsList = firstDetails;
                if (firstDetails.accountOpeningDate) {
                  vm.OpeningDate = convertDateFormat(firstDetails.accountOpeningDate);
                  vm.accountOpeningDate = vm.OpeningDate;
                }
                if (firstDetails.valueDate) {
                  vm.valueOpeningDate = convertDateFormat(firstDetails.valueDate);
                  vm.valueDate = vm.valueOpeningDate;
                }
                vm.customerMasterDetails.modeOfInterestPayment = firstDetails.modeOfInterestPayment;
                vm.customerMasterDetails.otherDocuments = firstDetails.otherDocuments;
                vm.customerMasterDetails.operativeAccNumber = firstDetails.operativeAccNumber;
                vm.bondType = firstDetails.bondType;
                vm.brokerId = firstDetails.brokerAgencyCode;
                vm.subscriptionAmount = "";//firstDetails.subscriptionAmount;
                if (null!=vm.subscriptionAmount) {
                  convertAmountToWords(vm.subscriptionAmount);
                }
                vm.instrumentAmount = firstDetails.subscriptionAmount;
                vm.instrumentNo = firstDetails.instrumentNo;
                vm.transactionMode = firstDetails.transactionMode;
                if (firstDetails.instrumentDate) {
                  vm.instruDate = convertDateFormat(firstDetails.instrumentDate);
                  vm.instrumentDate = vm.instruDate;
                }
              }
              if (result.instrumentDetailsOut && result.instrumentDetailsOut.length > 0) {
                var firstDetails = result.instrumentDetailsOut[0];
                vm.DetailsList = firstDetails;
                if (firstDetails.accountOpeningDate) {
                  vm.OpeningDate = convertDateFormat(firstDetails.accountOpeningDate);
                  vm.accountOpeningDate = vm.OpeningDate;
                }
                if (firstDetails.valueDate) {
                  vm.valueOpeningDate = convertDateFormat(firstDetails.valueDate);
                  vm.valueDate = vm.valueOpeningDate;
                }
                vm.customerMasterDetails.modeOfInterestPayment = firstDetails.modeOfInterestPayment;
                vm.customerMasterDetails.operativeAccNumber = firstDetails.operativeAccNumber;
                vm.bondType = firstDetails.bondType;
                vm.brokerId = firstDetails.brokerAgencyCode;
                vm.subscriptionAmount = "";//firstDetails.subscriptionAmount;
                if (null!=vm.subscriptionAmount) {
                  convertAmountToWords(vm.subscriptionAmount);
                }
                vm.instrumentAmount = firstDetails.subscriptionAmount;
                vm.instrumentNo = firstDetails.instrumentNo;
                vm.transactionMode = firstDetails.transactionMode;
                if (firstDetails.instrumentDate) {
                  vm.instruDate = convertDateFormat(firstDetails.instrumentDate);
                  vm.instrumentDate = vm.instruDate;
                }
              }
              vm.blaFlag = result.blaFlag;
              vm.periodOfDeposit = result.periodOfDeposit;
              vm.rateOfInterest = result.rateOfInterest;
              vm.schemeBondType = result.schemeBondType;
              vm.BlaFlagNo = result.instrumentDetailsOut;
              vm.cashinDet = result.customerMasterOut;
              vm.guardDet = result.guardianDetailsOut;
              vm.bondCashLimit = result.cashLimitForCash;
              vm.bondClearingTransferLimit = result.cashLimitForClearingTransfer;
              vm.depositMultiples = result.depositMultiples;
              vm.minCashLimit = result.minCashLimit;
              vm.aadharCapture = result.aadharCapture;
              vm.aadharMandatory = result.aadharMandatory;
              vm.aadharDisplay = result.aadharDisplay;
              vm.blaNoOut = result.blaNoOut;
              if (vm.valueDate && vm.periodOfDeposit) {
                getMaturityDate(vm.valueDate, vm.periodOfDeposit);
              }
              if (vm.blaNoOut) {
                if (vm.modeOfHolding == "MH001" || vm.modeOfHolding == "MH004" || vm.modeOfHolding == "MH005") {
                  if (vm.blaNoOut[0]) {
                    vm.singleBondAccNo = vm.blaNoOut[0].blaNo;
                  }
                  if (vm.blaNoOut[0]) {
                    vm.bondHolderName = vm.blaNoOut[0].nameOfTheBondHolder;
                  }
                } else {
                  if (vm.blaNoOut[0]) {
                    vm.firstJointHolderBlaNo = vm.blaNoOut[0].blaNo;

                    if (vm.blaNoOut[0]) {
                      vm.firstJointHolderName = vm.blaNoOut[0].nameOfTheBondHolder;
                    }
                  }

                  if (vm.blaNoOut[1]) {
                    vm.secondJointHolderBlaNo = vm.blaNoOut[1].blaNo;

                    if (vm.blaNoOut[1]) {
                      vm.secondJointHolderName = vm.blaNoOut[1].nameOfTheBondHolder;
                    }
                  }
                }
              }
              if (vm.blaFlag == "N") {
                if (vm.bondHolderName) {
                  const nameComponents = splitName(vm.bondHolderName);
                  vm.customerMasterDetails.bondHldFirstName = nameComponents.firstName;
                  vm.customerMasterDetails.bondHldMidName = nameComponents.middleName;
                  vm.customerMasterDetails.bondHldLastName = nameComponents.lastName;
                  vm.customerMasterDetails.bondHldDob = vm.customerMasterOut?.bondHldDob || '';
                  if(vm.fromDate1){
                  vm.customerMasterDetails.bondHldDob = convertDateFormat($filter("date")(vm.fromDate1, "dd-MMM-yyyy"));}
                  vm.customerMasterDetails.nameOfthePayee = nameComponents.firstName+' '+nameComponents.middleName+' '+nameComponents.lastName;
                  vm.customerMasterDetails.panNo = vm.panNo;
                }
              }
              if (Array.isArray(vm.BlaFlagNo)) {
                vm.titleText =
                  vm.BlaFlagNo.length === 1 && (vm.noOfJointAcc == undefined || vm.noOfJointAcc == null || isNaN(vm.noOfJointAcc)) ? "BOND HOLDER DETAILS" : "FIRST JOINT BOND HOLDER DETAILS";

                // Check if the properties exist before assigning them
                if (vm.BlaFlagNo[0]) {
                  vm.customerMasterDetails = vm.BlaFlagNo[0];
                }

                if (vm.BlaFlagNo[1]) {
                  vm.customerMasterDetailsTwo = vm.BlaFlagNo[1];
                }
                if (vm.bondHolderName && vm.customerMasterDetails) {
                  const nameComponents = splitName(vm.bondHolderName);
                  vm.customerMasterDetails.bondHldFirstName = nameComponents.firstName;
                  vm.customerMasterDetails.bondHldMidName = nameComponents.middleName;
                  vm.customerMasterDetails.bondHldLastName = nameComponents.lastName;
                 vm.customerMasterDetails.bondHldDob = vm.customerMasterOut.bondHldDob;
                  if(vm.fromDate1){
                  vm.customerMasterDetails.bondHldDob = convertDateFormat($filter("date")(vm.fromDate1, "dd-MMM-yyyy"));}
                  vm.customerMasterDetails.panNo = vm.pan_No1;
                }
              }

              if (Array.isArray(vm.cashinDet)) {
                let orderedDetails = [];

                for (let i = 0; i < vm.cashinDet.length; i++) {
                  const detail = vm.cashinDet[i];

                  if (detail.jointHolderType == 1 || detail.jointHolderType == "S") {
                    orderedDetails[0] = detail; // First joint holder
                  } else if (detail.jointHolderType == 2) {
                    orderedDetails[1] = detail; // Second joint holder
                  }
                }

                vm.titleText =
                  vm.cashinDet.length === 1 && (vm.noOfJointAcc === undefined || vm.noOfJointAcc === null || isNaN(vm.noOfJointAcc)) ? "BOND HOLDER DETAILS" : "FIRST JOINT BOND HOLDER DETAILS";

                // Bind customer details based on orderedDetails
                if (orderedDetails[0]) {
                  vm.customerMasterDetails = orderedDetails[0]; // Bind first detail if it exists
                }

                if (vm.noOfJointAcc >= 2 && orderedDetails[1]) {
                  vm.customerMasterDetailsTwo = orderedDetails[1]; // Bind second detail if it exists
                }

                if (vm.customerMasterDetails.bondHldDob) {
                  vm.dateOfBirth = convertDateFormat($filter("date")(vm.fromDate1, "dd-MMM-yyyy"));
                  vm.customerMasterDetails.bondHldDob = vm.dateOfBirth;

                  //                                                                    if (vm.customerMasterDetails.bondHldFirstName && vm.customerMasterDetails) {
                  //                                                                         const nameComponents = splitName(vm.customerMasterDetails.bondHldFirstName);
                  //                                                                         vm.customerMasterDetails.bondHldFirstName = nameComponents.firstName;
                  //                                                                         vm.customerMasterDetails.bondHldMidName = nameComponents.middleName;
                  //                                                                         vm.customerMasterDetails.bondHldLastName = nameComponents.lastName;
                  //
                  //                                                                     }
                }
                if (vm.customerMasterDetailsTwo) {
                  vm.dateOfBirth = convertDateFormat(vm.customerMasterDetailsTwo.bondHldDob);
                  vm.customerMasterDetailsTwo.bondHldDob = vm.dateOfBirth;
                  if (vm.customerMasterDetailsTwo.isMinor) {
                    vm.customerMasterDetailsTwoFlag = vm.customerMasterDetailsTwo.isMinor;
                  }
                  //                        vm.customerMasterDetailsTwo.bondHldDob = $filter('date')(vm.customerMasterDetailsTwo.bondHldDob, "dd-MM-yyyy");
                }
              }
              if (Array.isArray(vm.guardDet)) {
                let orderedDetails1 = [];
                for (let i = 0; i < vm.guardDet.length; i++) {
                  const detail = vm.guardDet[i];
                    orderedDetails1[0] = detail;
                }
                // Bind customer details based on orderedDetails
                if (orderedDetails1[0]) {
                  vm.guardianDetailsValueModel = orderedDetails1[0]; // Bind first detail if it exists
                }
                if (vm.guardDet.length > 0) {
                  if (vm.guardianDetailsValueModel) {
                    vm.customerMasterDetails.guardianId = vm.guardianDetailsValueModel.guardianId;
                    vm.customerMasterDetails.guardianFirstName = vm.guardianDetailsValueModel.guardianFirstName;
                    vm.customerMasterDetails.guardianMiddleName = vm.guardianDetailsValueModel.guardianMiddleName;
                    vm.customerMasterDetails.guardianLastName = vm.guardianDetailsValueModel.guardianLastName;
                    vm.customerMasterDetails.relationWithMinor = vm.guardianDetailsValueModel.relationWithMinor;
                    if (vm.guardianDetailsValueModel.guardianDateOfBirth) {
                      vm.guardianDateOfBirth = convertDateFormat(vm.guardianDetailsValueModel.guardianDateOfBirth);
                      vm.customerMasterDetails.guardianDateOfBirth = vm.guardianDateOfBirth;
                    }
                    vm.customerMasterDetails.guardianCommunicationAddress = vm.guardianDetailsValueModel.guardianCommunicationAddress;
                    vm.customerMasterDetails.guardianMobileNo = vm.guardianDetailsValueModel.mobileNo;
                    vm.customerMasterDetails.guardianEmailId = vm.guardianDetailsValueModel.guardianEmailId;
                  }
                }
              }
              vm.accountOpeningDate = vm.systemDate;
              if(vm.blaFlag==='Y'){
              if(null!=vm.customerMasterDetails.blaNo){
              vm.blaAccNoTo=vm.customerMasterDetails.blaNo;
              }
              vm.isFromBlaValid=false;
              vm.isToBlaValid=false;
              }else if(vm.blaFlag==='N'){
              vm.isFromBlaValid=false;
              }
              vm.tabReferenceAdd = (vm.blaFlag === 'Y') ? 'customerMasterForm1' : 'customerMasterForm';
               if (vm.blaFlag === "Y") {
                           Swal.fire({
                              title: "Warning!",
                             text: "Bond Ledger Account Number Already Exists for " + vm.customerMasterDetails.blaNo,
                              icon: "warning", // use "icon", not "type"
                              showCancelButton: true,
                              confirmButtonText: "Yes",
                              cancelButtonText: "Cancel",
                              customClass: {
                                confirmButton: "btn-primary",
                                cancelButton: "btn-cancel"
                              },
                              buttonsStyling: false
                            }).then((result) => {
                               if (result.isConfirmed) {
                                $timeout(function () {
                                  var $tabLink = $("#subscriptionForm");
                                  if ($tabLink.length) {
                                    $tabLink.tab("show");
                                  }
                                }, 100);
                              } else {
                                $state.go("mode_of_holding_change.add_input");
                              }
                                }
                              );
                            }
              getDocumentDetails();
            } else {
              console.error("Response structure is not as expected:", response);
            }
          })
          .catch(function (error) {
            handleErrorResponse(error);
            $state.go("mode_of_holding_change.list");
            vm.loader = false;
          });

    }

    function getMaturityDate(valueDate, periodOfDeposit) {
      if (vm.periodOfDeposit !== null && vm.valueDate) {
        var valueDate = new Date(vm.valueDate);

        var numberOfMonths = vm.periodOfDeposit;

        var maturityDate = new Date(vm.valueDate);

        //            maturityDate.setMonth(maturityDate.getMonth() + numberOfMonths);
        maturityDate.setMonth(parseInt(maturityDate.getMonth()) + parseInt(numberOfMonths));
        vm.maturityDate = $filter("date")(maturityDate, "dd/MM/yyyy");
      }
    }

    function getModuleNameAdd() {
      vm.loader = true;
      vm.referenceId = "ACTSCH";
      var requestData = {
        referenceId: vm.referenceId,
      };
      modeOfHoldingService.getRbiBondModuleNameDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result;
        },
        function (error) {
          handleErrorResponse(error);
          $state.go("bla_acc_opening_cum_subscription.list");
          vm.loader = false;
        }
      );
    }

    function getPantorch() {
      var requestData = {
        schemeCode: vm.schemeCode,
        bondHolderName: vm.bondHolderName,
        panNum: vm.panNum,
      };
      modeOfHoldingService.blaTorch(requestData).then(
        function (response) {
          vm.loader = false;
          //                                                       vm.PanTorch = response.data.result;
          vm.PanTorch = response.data.result.torchValue;
          angular.element("#panModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function getModifyPantorch() {
      var requestData = {
        schemeCode: vm.schemeCode,
        bondHolderName: vm.bondHolderName,
        panNum: vm.panNum,
      };
      modeOfHoldingService.modifyPanTorch(requestData).then(
        function (response) {
          vm.loader = false;
          //                                                       vm.PanTorch = response.data.result;
          vm.PanTorch = response.data.result.torchValue;
          angular.element("#panModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function getCurrentFinancialYear() {
      vm.fiscalyear = "";
      var today = new Date();
      if (today.getMonth() + 1 <= 3) {
        vm.fiscalyear = today.getFullYear() - 1 + "-" + today.getFullYear();
      } else {
        vm.fiscalyear = today.getFullYear() + "-" + (today.getFullYear() + 1);
      }
      vm.currentFinancialYear = vm.fiscalyear;
      vm.customerMasterDetails.finYear = vm.fiscalyear;
      //          vm.customerMasterDetailsTwo.finYear = vm.fiscalyear;
      //          vm.customerMasterDetailsThree.finYear = vm.fiscalyear;
      return vm.fiscalyear;
    }

    function getModeofHolding() {
      var requestData = {
        referenceId: "MODE_OF_HOLDING",
      };
      modeOfHoldingService.getReferenceData(requestData).then(
        function (response) {
          vm.modeOfHoldingList = response.data.result;
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }

    function getTypeofHolding() {
      var requestData = {
        referenceId: "HOLDER_TYPE",
      };
      modeOfHoldingService.getReferenceData(requestData).then(
        function (response) {
          vm.holderTypeList = response.data.result;
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }

    function getFormDetails() {
      var requestData = {
        referenceId: "FORM",
      };
      modeOfHoldingService.getReferenceData(requestData).then(
        function (response) {
          vm.formList = response.data.result;
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }

    function getKycDetails() {
      var requestData = {
        referenceId: "KYC_DOCS",
      };
      modeOfHoldingService.getReferenceData(requestData).then(
        function (response) {
          vm.kycList = response.data.result;
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }

    function getGuardianDetailsMetaData() {
      var requestData = {
        referenceId: "RELATION_WITH_MINOR",
      };
      modeOfHoldingService.getReferenceData(requestData).then(
        function (response) {
          vm.guardianDetailsListMetaData = response.data.result;
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }
    /* Check box selection */
    function selectedChecklist() {
      angular.forEach(vm.serviceCodeDetailsList, function (item) {
        if (item.selected) {
          vm.selectedCheckListArray.push(item.serCode);
        }
      });
      if (vm.selectedCheckListArray.length == "0") {
        Swal.fire("Please select atleast one request type");
      } else {
        getMonitorServiceReport();
      }
    }

    vm.displayRemarks = true;
    vm.reqField = true;

    function optionToggled() {
      vm.isAllSelected = [];
      angular.forEach(vm.kycList, function (item) {
        vm.isAllSelected.push(item.selected);
      });
      console.log(vm.isAllSelected);
      if (vm.isAllSelected[0] == true) {
        vm.customerMasterDetails.birthCertificate = "Yes";
      }
      if (vm.isAllSelected[1] == true) {
        vm.customerMasterDetails.passport = "Yes";
      }
      if (vm.isAllSelected[2] == true) {
        vm.customerMasterDetails.seniorCitizenCard = "Yes";
      }
      if (vm.isAllSelected[3] == true) {
        vm.customerMasterDetails.voterIdCard = "Yes";
      }
      if (vm.isAllSelected[4] == true) {
        vm.customerMasterDetails.panCard = "Yes";
      }
      if (vm.isAllSelected[5] == true) {
        vm.customerMasterDetails.drivingLicence = "Yes";
      }
      if (vm.isAllSelected[6] == true) {
        vm.customerMasterDetails.rationCard = "Yes";
      }
      if (vm.isAllSelected[7] == true) {
        vm.customerMasterDetails.otherDocuments = "Yes";
      }
    }

    function getState() {
      vm.listType = "STATES OF INDIA";
      var requestData = {
        referenceId: vm.listType,
      };
      modeOfHoldingService.getReferenceData(requestData).then(
        function (response) {
          vm.stateListValues = response.data.result;
          vm.stateList = [];
          angular.forEach(vm.stateListValues, function (item) {
            vm.stateList.push({
              subCodeDescription: item.DESCRIPTION,
              subCode: item.SUBCODE,
            });
          });
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }

    /* Get Sol Details */
    function getSolDetails(solId, scopeVariable, flag) {
      vm.tempSolIDScopeVariable = scopeVariable;
      vm.searchSolId = "";
      vm.loader = true;
      commonService.getSolAllDetails(solId, function (response) {
        if (response.success) {
          vm.loader = false;
          vm.solDetails = response.data;
          if (flag == "normal") {
            angular.element("#solIDModal").modal("toggle");
          } else {
            angular.element("#solIDJointModal").modal("toggle");
          }
        } else {
          Swal.fire("Error!", response.message, "error");
          vm.loader = false;
        }
      });
    }

    /* Get CBS Customer Id Details */
    function getCbsCustFstJoinDetails(accountNumber) {
      vm.loader = true;
      const accountNumberPattern = /^\d{10,15}$/;

      if (!accountNumberPattern.test(accountNumber)) {
        alert("Account number must be numeric and between 10 to 15 digits.");
        return;
      }

      modeOfHoldingService.fetchCbsCustId(accountNumber).then(
        (response) => {
          vm.loader = false;
          vm.cbsDetail = response.data.result.responseString.customerDetailsDTO[0];

          vm.customerMasterDetails.bondHldFirstName = vm.cbsDetail.customerFullName;
          vm.customerMasterDetails.bondHldMidName = vm.cbsDetail.customerMiddleName;
          vm.customerMasterDetails.bondHldLastName = vm.cbsDetail.customerLastName;
          vm.customerMasterDetails.fatherName = vm.cbsDetail.fatherName;
          vm.customerMasterDetails.motherName = vm.cbsDetail.motherName;
          vm.customerMasterDetails.gender = vm.cbsDetail.txtCustSex;
          vm.customerMasterDetails.bondHldDob = convertToDateObject(vm.cbsDetail.dateOfBirth);
          vm.customerMasterDetails.isMinor = vm.cbsDetail.isMinor;
          vm.customerMasterDetails.bondHldAddOne = vm.cbsDetail.txtCustadrAdd1;
          vm.customerMasterDetails.bondHldAddTwo = vm.cbsDetail.txtCustadrAdd2;
          vm.customerMasterDetails.bondHldAddThree = vm.cbsDetail.txtCustadrAdd3;
          vm.customerMasterDetails.bondHldCity = vm.cbsDetail.namHoldadrCity;
          vm.customerMasterDetails.bondHldPinCode = vm.cbsDetail.pinCode;
          vm.customerMasterDetails.bondHolderPhoneNumber = vm.cbsDetail.rmPhoneNo;
          vm.customerMasterDetails.bondHldMobNum = vm.cbsDetail.refPhoneMobile;
          vm.customerMasterDetails.bondHldEmail = vm.cbsDetail.refCustEmail;
          vm.customerMasterDetails.operativeAccNum = vm.cbsDetail.operativeAccNo;
          vm.customerMasterDetails.operativeAccBranchId = vm.cbsDetail.operativeBranchId;
          vm.customerMasterDetails.aadhaarNo = vm.cbsDetail.codAadhaarNo;
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
          vm.errmsgSingle = "CBS Failure";
          vm.customerMasterDetails.cbsCustId = "";
        }
      );
    }

    function getAddressOne(pinCode) {
      vm.loader = true;

      // Include the pin code in the requestData object
      var requestData = {
        pinCode: pinCode,
      };

      // Call the fetchState method and pass the requestData with pinCode
      modeOfHoldingService
        .fetchState(requestData)
        .then(function (response) {
          vm.loader = false;
          // Check if response contains the expected data
          if (response.data.result) {
            vm.cbsDetail = response.data.result[0];
            vm.customerMasterDetails.bondHldState = vm.cbsDetail.bondHldState;
            vm.customerMasterDetails.bondHldCity = vm.cbsDetail.bondHldCity;
          } else {
            vm.errmsgSingle = "No data found for the given pin code Enter Manually";
          }
        })
        .catch(function (error) {
          vm.loader = false;
          handleErrorResponse(error); // Handle any error that occurs
          vm.errmsgSingle = "No data found for the given pin code Enter Manually";
          vm.customerMasterDetails.bondHldState = "";
          vm.customerMasterDetails.bondHldCity = "";
        });
    }
     function getAddressNominee(item,pinCode) {
          vm.loader = true;

          // Include the pin code in the requestData object
          var requestData = {
            pinCode: pinCode,
          };

          // Call the fetchState method and pass the requestData with pinCode
          modeOfHoldingService
            .fetchState(requestData)
            .then(function (response) {
              vm.loader = false;
              // Check if response contains the expected data
              if (response.data.result) {
                vm.cbsDetail = response.data.result[0];
                item.state = vm.cbsDetail.bondHldState;
                item.city = vm.cbsDetail.bondHldCity;
              } else {
                vm.errmsgSingle = "No data found for the given pin code Enter Manually";
              }
            })
            .catch(function (error) {
              vm.loader = false;
              handleErrorResponse(error); // Handle any error that occurs
              vm.errmsgSingle = "No data found for the given pin code Enter Manually";
              item.state = "";
item.city = "";
            });
        }

         function getAddressGuardianNominee(item,pinCode) {
                  vm.loader = true;

                  // Include the pin code in the requestData object
                  var requestData = {
                    pinCode: pinCode,
                  };

                  // Call the fetchState method and pass the requestData with pinCode
                  modeOfHoldingService
                    .fetchState(requestData)
                    .then(function (response) {
                      vm.loader = false;
                      // Check if response contains the expected data
                      if (response.data.result) {
                        vm.cbsDetail = response.data.result[0];
                        item.guardianState = vm.cbsDetail.bondHldState;
                        item.guardianCity = vm.cbsDetail.bondHldCity;
                      } else {
                        vm.errmsgSingle = "No data found for the given pin code Enter Manually";
                      }
                    })
                    .catch(function (error) {
                      vm.loader = false;
                      handleErrorResponse(error); // Handle any error that occurs
                      vm.errmsgSingle = "No data found for the given pin code Enter Manually";
                      item.guardianState = "";
                      item.guardianCity = "";
                    });
                }
    function getAddressTwo(pinCode) {
      vm.loader = true;

      // Include the pin code in the requestData object
      var requestData = {
        pinCode: pinCode,
      };

      // Call the fetchState method and pass the requestData with pinCode
      modeOfHoldingService
        .fetchState(requestData)
        .then(function (response) {
          vm.loader = false;
          // Check if response contains the expected data
          if (response.data.result) {
            vm.cbsDetail = response.data.result[0];
            vm.customerMasterDetailsTwo.bondHldState = vm.cbsDetail.bondHldState;
            vm.customerMasterDetailsTwo.bondHldCity = vm.cbsDetail.bondHldCity;
          } else {
            vm.errmsgSingle = "No data found for the given pin code";
          }
        })
        .catch(function (error) {
          vm.loader = false;
          handleErrorResponse(error); // Handle any error that occurs
          vm.errmsgSingle = "No data found for the given pin code";
          vm.customerMasterDetailsTwo.bondHldState = "";
          vm.customerMasterDetailsTwo.bondHldCity = "";
        });
    }
    function getAddressThree(pinCode) {
      vm.loader = true;

      // Include the pin code in the requestData object
      var requestData = {
        pinCode: pinCode,
      };

      // Call the fetchState method and pass the requestData with pinCode
      modeOfHoldingService
        .fetchState(requestData)
        .then(function (response) {
          vm.loader = false;
          // Check if response contains the expected data
          if (response.data.result) {
            vm.cbsDetail = response.data.result[0];
            vm.customerMasterDetailsThree.bondHldState = vm.cbsDetail.bondHldState;
            vm.customerMasterDetailsThree.bondHldCity = vm.cbsDetail.bondHldCity;
          } else {
            vm.errmsgSingle = "No data found for the given pin code";
          }
        })
        .catch(function (error) {
          vm.loader = false;
          handleErrorResponse(error); // Handle any error that occurs
          vm.errmsgSingle = "No data found for the given pin code";
          vm.customerMasterDetailsThree.bondHldState = "";
          vm.customerMasterDetailsThree.bondHldCity = "";
        });
    }

     function getModAddressOne(pinCode) {
          vm.loader = true;

          // Include the pin code in the requestData object
          var requestData = {
            pinCode: pinCode,
          };

          // Call the fetchState method and pass the requestData with pinCode
          modeOfHoldingService
            .fetchState(requestData)
            .then(function (response) {
              vm.loader = false;
              // Check if response contains the expected data
              if (response.data.result) {
                vm.cbsDetail = response.data.result[0];
                vm.customerMasterDetails.C023.oldValue = vm.cbsDetail.bondHldState;
                vm.customerMasterDetails.C022.oldValue = vm.cbsDetail.bondHldCity;
              } else {
                vm.errmsgSingle = "No data found for the given pin code Enter Manually";
              }
            })
            .catch(function (error) {
              vm.loader = false;
              handleErrorResponse(error); // Handle any error that occurs
              vm.errmsgSingle = "No data found for the given pin code Enter Manually";
                vm.customerMasterDetails.C023.oldValue = "";
              vm.customerMasterDetails.C022.oldValue = "";
            });
        }
         function getModAddressNominee(item,pinCode) {
              vm.loader = true;

              // Include the pin code in the requestData object
              var requestData = {
                pinCode: pinCode,
              };

              // Call the fetchState method and pass the requestData with pinCode
              modeOfHoldingService
                .fetchState(requestData)
                .then(function (response) {
                  vm.loader = false;
                  // Check if response contains the expected data
                  if (response.data.result) {
                    vm.cbsDetail = response.data.result[0];
                    item.C009.oldValue = vm.cbsDetail.bondHldState;
                    item.C008.oldValue = vm.cbsDetail.bondHldCity;
                  } else {
                    vm.errmsgSingle = "No data found for the given pin code Enter Manually";
                  }
                })
                .catch(function (error) {
                  vm.loader = false;
                  handleErrorResponse(error); // Handle any error that occurs
                  vm.errmsgSingle = "No data found for the given pin code Enter Manually";
                  item.C009.oldValue = "";
                  item.C008.oldValue = "";
                });
            }

             function getModAddressGuardianNominee(item,pinCode) {
                      vm.loader = true;

                      // Include the pin code in the requestData object
                      var requestData = {
                        pinCode: pinCode,
                      };

                      // Call the fetchState method and pass the requestData with pinCode
                      modeOfHoldingService
                        .fetchState(requestData)
                        .then(function (response) {
                          vm.loader = false;
                          // Check if response contains the expected data
                          if (response.data.result) {
                            vm.cbsDetail = response.data.result[0];
                            item.C023.oldValue = vm.cbsDetail.bondHldState;
                            item.C022.oldValue = vm.cbsDetail.bondHldCity;
                          } else {
                            vm.errmsgSingle = "No data found for the given pin code Enter Manually";
                          }
                        })
                        .catch(function (error) {
                          vm.loader = false;
                          handleErrorResponse(error); // Handle any error that occurs
                          vm.errmsgSingle = "No data found for the given pin code Enter Manually";
                          item.C023.oldValue = "";
                          item.C022.oldValue = "";
                        });
                    }
        function getModAddressTwo(pinCode) {
          vm.loader = true;

          // Include the pin code in the requestData object
          var requestData = {
            pinCode: pinCode,
          };

          // Call the fetchState method and pass the requestData with pinCode
          modeOfHoldingService
            .fetchState(requestData)
            .then(function (response) {
              vm.loader = false;
              // Check if response contains the expected data
              if (response.data.result) {
                vm.cbsDetail = response.data.result[0];
                vm.customerMasterDetailsTwo.C023.oldValue = vm.cbsDetail.bondHldState;
                vm.customerMasterDetailsTwo.C022.oldValue = vm.cbsDetail.bondHldCity;
              } else {
                vm.errmsgSingle = "No data found for the given pin code";
              }
            })
            .catch(function (error) {
              vm.loader = false;
              handleErrorResponse(error); // Handle any error that occurs
              vm.errmsgSingle = "No data found for the given pin code";
              vm.customerMasterDetailsTwo.C023.oldValue = "";
                vm.customerMasterDetailsTwo.C022.oldValue= "";
            });
        }
        function getModAddressThree(pinCode) {
          vm.loader = true;

          // Include the pin code in the requestData object
          var requestData = {
            pinCode: pinCode,
          };

          // Call the fetchState method and pass the requestData with pinCode
          modeOfHoldingService
            .fetchState(requestData)
            .then(function (response) {
              vm.loader = false;
              // Check if response contains the expected data
              if (response.data.result) {
                vm.cbsDetail = response.data.result[0];
                vm.customerMasterDetailsThree.C023.oldValue = vm.cbsDetail.bondHldState;
                vm.customerMasterDetailsThree.C022.oldValue = vm.cbsDetail.bondHldCity;
              } else {
                vm.errmsgSingle = "No data found for the given pin code";
              }
            })
            .catch(function (error) {
              vm.loader = false;
              handleErrorResponse(error); // Handle any error that occurs
              vm.errmsgSingle = "No data found for the given pin code";
              vm.customerMasterDetailsThree.C023.oldValue = "";
              vm.customerMasterDetailsThree.C022.oldValue = "";
            });
        }

    function minAgeC() {
      var currentDate = new Date();
      var result = subtractYears(currentDate, 18);
    }

    /* Get CBS Customer Id Second Join Details */
    function getCbsCustScndJoinDetails() {
      vm.loader = true;
      vm.custId = vm.customerMasterJointDetails.cbsCustId;
      modeOfHoldingService.fetchCbsCustId(vm.custId).then(function (response) {
        if (response) {
          vm.getCbsScndJoinDetailsChck = response.data.status;
          vm.getCbsScndJoinDetails = response.data.customerDetails;
          if (vm.getCbsScndJoinDetailsChck != "N" && vm.getCbsScndJoinDetails != null) {
            vm.customerMasterJointDetails.C026 = vm.getCbsScndJoinDetails.panNumber;
            vm.customerMasterJointDetails.C024 = vm.getCbsScndJoinDetails.solId;
            vm.customerMasterJointDetails.C012 = vm.getCbsScndJoinDetails.addressLine1;
            vm.customerMasterJointDetails.C013 = vm.getCbsScndJoinDetails.addressLine2;
            vm.customerMasterJointDetails.C014 = vm.getCbsScndJoinDetails.addressLine3;
            //vm.customerMasterJointDetails.C026 = vm.getCbsScndJoinDetails.panNumber;
            vm.customerMasterJointDetails.C015 = vm.getCbsScndJoinDetails.city;
            vm.customerMasterJointDetails.C016 = vm.getCbsScndJoinDetails.state;
            vm.customerMasterJointDetails.C017 = vm.getCbsScndJoinDetails.zipCode;
            vm.customerMasterJointDetails.C018 = vm.getCbsScndJoinDetails.phoneNumber;
            vm.customerMasterJointDetails.C019 = vm.getCbsScndJoinDetails.email;
            vm.errmsgJoint = "";

            getTdsApplicableRate();
          } else {
            vm.errmsgJoint = "No Data Found";
          }

          vm.loader = false;
        } else {
          Swal.fire("Error!", response.message, "error");
          vm.loader = false;
        }
      });
    }
    /* Get bla acc Details */
    function getBlaAccNoDetails(flag) {
      vm.loader = true;

      if (flag == "torch") {
        var requestData = {
          schemeCode: vm.schemeCode,
          modeOfHolding: vm.modeOfHolding,
        };
        modeOfHoldingService.fetchBlaAccNoDetails(requestData).then(
          (response) => {
            vm.loader = false;
            vm.blaNoList = response.data.result;
            if (vm.modeOfHolding == "MH002" || vm.modeOfHolding == "MH003") {
              angular.element("#blaJointModal").modal("toggle");
            } else {
              angular.element("#blaModal").modal("toggle");
            }
          },
          function (error) {
            vm.loader = false;
            handleErrorResponse(error);
          }
        );
      } else {
        var requestData = {
          schemeCode: vm.schemeCode,
          modeOfHolding: vm.modeOfHolding,
          blaNum: vm.customerMaster.blaAccNo,
          jointHolderNo: vm.jointHolderTypeCode,
        };
        modeOfHoldingService.fetchBlaAccNoDetails(requestData).then(
          (response) => {
            vm.loader = false;
            vm.blaAccDetails = response.data.result[0];
            vm.customerMasterDetails.blaAccNo = vm.blaAccDetails.blaNo;
            vm.customerMasterDetails.bondHldFirstName = vm.blaAccDetails.bondHolderFirstName;
            vm.customerMasterDetails.bondHldMidName = vm.blaAccDetails.bondHolderMiddleName;
            vm.customerMasterDetails.bondHldLastName = vm.blaAccDetails.bondHolderLastName;
            vm.customerMasterDetails.fatherName = vm.blaAccDetails.fatherName;
            vm.customerMasterDetails.motherName = vm.blaAccDetails.motherName;
            vm.customerMasterDetails.gender = vm.blaAccDetails.gender;
            vm.customerMasterDetails.bondHldDob = convertToDateObject(vm.blaAccDetails.dateOfBirth);
            vm.customerMasterDetails.isMinor = vm.blaAccDetails.isMinor;
            vm.customerMasterDetails.bondHldAddOne = vm.blaAccDetails.addressOne;
            vm.customerMasterDetails.bondHldAddTwo = vm.blaAccDetails.addressTwo;
            vm.customerMasterDetails.bondHldAddThree = vm.blaAccDetails.addressThree;
            vm.customerMasterDetails.bondHldCity = vm.blaAccDetails.city;
            vm.customerMasterDetails.bondHldPinCode = vm.blaAccDetails.pinCode;
            vm.customerMasterDetails.bondHolderPhoneNumber = vm.blaAccDetails.phoneNumber;
            vm.customerMasterDetails.bondHldMobNum = vm.blaAccDetails.mobileNumber;
            vm.customerMasterDetails.bondHldEmail = vm.blaAccDetails.email;
            vm.customerMasterDetails.operativeAccNum = vm.blaAccDetails.operativeAccNo;
            vm.customerMasterDetails.operativeAccBranchId = vm.blaAccDetails.operativeBranchId;
            vm.customerMasterDetails.panNo = vm.blaAccDetails.pan;
          },
          function (error) {
            vm.loader = false;
            handleErrorResponse(error);
          }
        );
      }
    }

    function getGuardianDetails(flag) {
      vm.loader = true;

      if (flag == "select") {
        var requestData = {
          schemeCode: vm.schemeCode,
          panNum : vm.customerMasterDetails.panNo,
        };
        modeOfHoldingService.fetchGuardianDetails(requestData).then(
          (response) => {
            vm.loader = false;
            vm.guardianDetailsList = response.data.result;
            angular.element("#guardianModal").modal("toggle");
          },
          function (error) {
            vm.loader = false;
            handleErrorResponse(error);
          }
        );
      } else {
        var requestData = {
          schemeCode: vm.schemeCode,
          guardianId: vm.customerMasterDetails.guardianId,
        };
        modeOfHoldingService.fetchGuardianDetails(requestData).then(
          (response) => {
            vm.loader = false;
            vm.guardianDetail = response.data.result[0];

            vm.customerMasterDetails.guardianFirstName = vm.guardianDetail.guardianFirstName;
            vm.customerMasterDetails.guardianMiddleName = vm.guardianDetail.guardianMiddleName;
            vm.customerMasterDetails.guardianLastName = vm.guardianDetail.guardianLastName;
            vm.customerMasterDetails.relationWithMinor = vm.guardianDetail.relationWithMinor;
            vm.guardianDateOfBirth = convertDateFormat(vm.guardianDetail.guardianDateOfBirth);
            vm.customerMasterDetails.guardianDateOfBirth = vm.guardianDateOfBirth;

            vm.customerMasterDetails.guardianCommunicationAddress = vm.guardianDetail.guardianCommunicationAddress;
            vm.customerMasterDetails.guardianMobileNo = vm.guardianDetail.mobileNo;
            vm.customerMasterDetails.guardianEmailId = vm.guardianDetail.email;
          },
          function (error) {
            vm.loader = false;
            handleErrorResponse(error);
          }
        );
      }
    }

    function convertDateFormat(dateString) {
      var monthMap = {
        JAN: 1,
        FEB: 2,
        MAR: 3,
        APR: 4,
        MAY: 5,
        JUN: 6,
        JUL: 7,
        AUG: 8,
        SEP: 9,
        OCT: 10,
        NOV: 11,
        DEC: 12,
      };

      if (!dateString || typeof dateString !== "string") {
        throw new Error("Invalid date string");
      }

      var parts = dateString.split("-");
      if (parts.length !== 3) {
        throw new Error('Invalid date format. Expected "dd-MMM-yyyy".');
      }

      var day = parseInt(parts[0], 10);
      var month = monthMap[parts[1].toUpperCase()];
      var year = parseInt(parts[2], 10);

      if (!month) {
        throw new Error("Invalid month abbreviation");
      }

      return new Date(year, month - 1, day); // Convert to Date object
    }

    // Function to clear guardian details
    vm.clearGuardianDetails = function () {
      vm.customerMasterDetails.guardianId = "";
      vm.customerMasterDetails.guardianFirstName = "";
      vm.customerMasterDetails.guardianMiddleName = "";
      vm.customerMasterDetails.guardianLastName = "";
      vm.customerMasterDetails.guardianDateOfBirth = "";
      vm.customerMasterDetails.guardianCommunicationAddress = "";
      vm.customerMasterDetails.guardianMobileNo = "";
      vm.customerMasterDetails.guardianEmailId = "";
    };

    /* Get SCSS Joint Customer Id Details */
    function getScssJointCustDetails() {
      vm.loader = true;
      vm.scsscustId = vm.customerMasterJointDetails.scssCustId;
      vm.holderNum = vm.holderNum = "2";
      modeOfHoldingService.fetchScssCustId(vm.scsscustId, vm.holderNum).then(function (response) {
        if (response) {
          if (vm.scsscustId != "" && vm.holderNum == 2) {
            vm.getScssJointDetailsChck = response.data.result;
            vm.getScssJointDetails = response.data.result[0];
            if (vm.getScssJointDetailsChck.length > 0) {
              vm.customerMasterJointDetails.C026 = vm.getScssJointDetails.holderPanNumber;
              vm.customerMasterJointDetails.C024 = vm.getScssJointDetails.branchId;
              vm.customerMasterJointDetails.C012 = vm.getScssJointDetails.holderaddress1;
              vm.customerMasterJointDetails.C013 = vm.getScssJointDetails.holderaddress2;
              vm.customerMasterJointDetails.C014 = vm.getScssJointDetails.holderaddress3;
              //vm.customerMasterJointDetails.C026 = vm.getScssJointDetails.holderPanNumber;
              vm.customerMasterJointDetails.C015 = vm.getScssJointDetails.holderCity;
              vm.customerMasterJointDetails.C017 = vm.getScssJointDetails.holderPinCode;
              vm.customerMasterJointDetails.C018 = vm.getScssJointDetails.holderPhoneNo;
              vm.customerMasterJointDetails.C019 = vm.getScssJointDetails.holderEmail;

              vm.customerMasterJointDetails.C008 = vm.getScssJointDetails.holderFirstName;
              vm.customerMasterJointDetails.C009 = vm.getScssJointDetails.holderMiddleName;
              vm.customerMasterJointDetails.C010 = vm.getScssJointDetails.holderLastName;
              vm.customerMasterJointDetails.C027 = vm.getScssJointDetails.form49A;
              vm.customerMasterJointDetails.C028 = vm.getScssJointDetails.form60;
              vm.customerMasterJointDetails.C025 = vm.getScssJointDetails.holderOperativeAcctNo;
              vm.customerMasterJointDetails.C011 = vm.getScssJointDetails.holderType;
              vm.customerMasterJointDetails.C029 = vm.getScssJointDetails.holderAadharNo;
              vm.customerMasterJointDetails.C003 = vm.getScssJointDetails.finYear;
              vm.customerMasterJointDetails.C004 = vm.getScssJointDetails.form15G15H;
              vm.customerMasterJointDetails.C020 = convertToDateObject(vm.getScssJointDetails.holderDateOfBirth);
              vm.customerMasterFormDetails.C005 = convertToDateObject(vm.getScssJointDetails.dateOfSubmission);
              vm.customerMasterJointDetails.C022 = convertToDateObject(vm.getScssJointDetails.holderDateOfRetirement);
              vm.customerMasterJointDetails.C023 = convertToDateObject(vm.getScssJointDetails.holderDateOfTermBenefits);
              vm.errorMessageJoint = "";

              getTdsApplicableRate();
            } else {
              vm.errorMessageJoint = "No Data Found";
            }
          }

          vm.loader = false;
        } else {
          Swal.fire("Error!", response.message, "error");
          vm.loader = false;
        }
      });
    }

    function getblaNo() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        schemeName: vm.schemeName,
        panNum: vm.panNum,
        bondHolderName: vm.bondHolderName,
        blaNo: vm.blaNo,
      };
      modeOfHoldingService.blaNo(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaDataList = response.data.result.torchValue;
          angular.element("#blaModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    // Function to handle changes when a new option is selected
    vm.onModeOfHoldingChange = function () {
      console.log("Selected SUBCODE:", vm.modeOfHolding);
      // Add any additional logic needed when selection changes
    };

    function getblaNoInq() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        schemeName: vm.schemeName,
        panNum: vm.panNum,
        bondHolderName: vm.bondHolderName,
        blaNo: vm.blaNo,
      };
      modeOfHoldingService.getblaNoInq(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaDataList = response.data.result.torchValue;
          angular.element("#blaModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function getsubscriptionIdMod() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        schemeName: vm.schemeName,
        panNum: vm.panNum,
        bondHolderName: vm.bondHolderName,
        blaNo: vm.blaNo,
        subscriptionId: vm.subscriptionId,
      };
      modeOfHoldingService.getsubscriptionIdMod(requestData).then(
        function (response) {
          vm.loader = false;
          vm.subscriptionList = response.data.result.torchValue;
          angular.element("#subscriptionModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function getsubscriptionIdInq() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        schemeName: vm.schemeName,
        panNum: vm.panNum,
        bondHolderName: vm.bondHolderName,
        blaNo: vm.blaNo,
        subscriptionId: vm.subscriptionId,
      };
      modeOfHoldingService.getsubscriptionIdInq(requestData).then(
        function (response) {
          vm.loader = false;
          vm.subscriptionList = response.data.result.torchValue;

          angular.element("#subscriptionModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }
    /*Operative Acct Num*/
    function getSchemeTypeListOperative(acctNum, flag) {
      vm.loader = true;
      vm.customerMasterDetails.operativeAccBranchId = "";
      if (acctNum) {
        commonService.getCustomAcctInqApi(
          acctNum.toUpperCase(),
          function (response) {
            if (response.success) {
              vm.accList = response.data;
              vm.loader = false;
              if (vm.accList.length > 0) {
                if (vm.accList[0].status == "S") {
                  if (vm.accList[0].accountFreezeStatus) {
                    vm.cbsErrorMessage = "Account is Friezed";
                    vm.customerMasterDetails.operativeAccNum = "";
                  } else {
                    vm.customerMasterDetails.operativeAccBranchId = vm.accList[0].solID;
                    vm.cbsErrorMessage = null;
                  }
                }
                if (vm.accList[0].status == "F") {
                  vm.cbsErrorMessage = "Invalid  Account  Number";
                }
              } else {
                vm.cbsErrorMessage = "Invalid  Account  Number";
              }
            } else {
              Swal.fire("Error!", response.message, "error");
              vm.loader = false;
              vm.customerMasterDetails.operativeAccNum = "";
            }
          },
          function (err) {
            handleErrorResponse(err);
            vm.loader = false;
            vm.customerMasterDetails.operativeAccNum = "";
          }
        );
      } else {
        vm.loader = false;
      }
    }
    /*Operative Acct Num Joint*/
    function getSchemeTypeListOperativeJoint(solId, acctNum, flag) {
      vm.loader = true;
      if (acctNum) {
        commonService.getSchemeTypeListAcc(
          acctNum.toUpperCase(),
          function (response) {
            if (response.success) {
              vm.accList = response.data;
              vm.loader = false;
              if (vm.accList.length > 0) {
                vm.customerMasterJointDetails.acctNum = vm.accList[0].forAccountId;
                vm.customerMasterJointDetails.C025 = vm.accList[0].forAccountId;
                vm.customerMasterJointDetails.acctName = vm.accList[0].accountName;
                vm.errorMessageJointAcnt = "";
              } else {
                vm.errorMessageJointAcnt = "Account Number failed to validate";
                vm.customerMasterJointDetails.acctNum = "";
              }
            } else {
              Swal.fire("Error!", response.message, "error");
              vm.loader = false;
            }
          },
          function (err) {
            handleErrorResponse(err);
            vm.loader = false;
          }
        );
      } else {
        vm.loader = false;
      }
    }

    function addRange() {
      if (vm.scssOtherBanksDetails.length <= 14) {
        vm.scssOtherBanksDetails.push({});
        vm.disableDelete = true;
      }
    }

    function removeRange() {
      if (vm.scssOtherBanksDetails.length > 1) {
        vm.lastRow = vm.scssOtherBanksDetails.length - 1;
        vm.scssOtherBanksDetails.splice(vm.lastRow, 1);
        if (vm.lastRow == 1) {
          vm.disableDelete = false;
        }
      } else {
        vm.disableDelete = false;
      }
    }

    function titleChange() {
      if (vm.modeOfHolding == "MH002" || vm.modeOfHolding == "MH003") {
        vm.titleText = "FIRST JOINT ACCOUNT HOLDER DETAILS";
      } else {
        vm.titleText = "BOND HOLDER DETAILS";
      }
    }

    const convertToColumnValuesArrayTemp = (objectToBeConvert) => {
      if (typeof objectToBeConvert === "object") {
        let resultArray = [];
        for (let key of Object.keys(objectToBeConvert)) {
          if (objectToBeConvert[key])
            resultArray.push({
              colName: "C003",
              value: objectToBeConvert[key].value,
            });
          if (objectToBeConvert[key].value == "KD009") {
            resultArray.push({
              colName: "C004",
              value: vm.documentsDetailsList.C004,
            }); //Remarks
          }
        }
        if (vm.modeOfHolding == "MH001") {
          //Holder Number
          resultArray.push({
            colName: "C002",
            value: "1",
          });
        } else {
          resultArray.push({
            colName: "C002",
            value: "2",
          });
        }
        return resultArray;
      } else {
        throw "Only objects can be converted";
      }
    };
    const convertToColumnValuesArrayTempModify = (objectToBeConvert) => {
      if (typeof objectToBeConvert === "object") {
        let resultArray = [];
        for (let key of Object.keys(objectToBeConvert)) {
          if (objectToBeConvert[key])
            resultArray.push({
              colName: "C003",
              value: objectToBeConvert[key].value,
            });
          resultArray.push({
            colName: "ID",
            value: objectToBeConvert[key].ID ? objectToBeConvert[key].ID : "",
          });
          if (objectToBeConvert[key].value == "KD009") {
            resultArray.push({
              colName: "C004",
              value: vm.documentsDetailsList.C004,
            }); //Remarks
          }
        }

        if (vm.getScssDetails.masterDetailsValueModel[0].C002 == "MH001") {
          //Holder Number
          resultArray.push({
            colName: "C002",
            value: "1",
          });
        } else {
          resultArray.push({
            colName: "C002",
            value: "2",
          });
        }
        return resultArray;
      } else {
        throw "Only objects can be converted";
      }
    };

    /* Add Submit */
    function addcustomerMaster() {


      // If validation passes, show confirmation dialog
//        Swal.fire(
//          {
//            title: "Are you sure?",
//            text: "Do you really want to add this record?",
//            type: "warning",
//            showCancelButton: true,
//            cancelButtonClass: "btn-cancel",
//            confirmButtonClass: "btn-primary",
//            confirmButtonText: "Yes",
//            closeOnConfirm: false,
//          },
//          function () {



             Swal.fire({
               title: "Are you sure?",
               text: "Do you want to Really Add this record?",
               icon: "warning", // use "icon", not "type"
               showCancelButton: true,
               confirmButtonText: "Yes",
               cancelButtonText: "Cancel",
               customClass: {
                 confirmButton: "btn-primary",
                 cancelButton: "btn-cancel"
               },
               buttonsStyling: false
             }).then((result) => {

                    if (result.isConfirmed) {
            vm.loader = true;

            vm.resultArray = [];
            vm.resultArray2 = [];

            vm.customerMasterDetails.bondHldDob = $filter("date")(vm.customerMasterDetails.bondHldDob, "dd-MMM-yyyy");
            vm.customerMasterDetails.guardianDateOfBirth = $filter("date")(vm.customerMasterDetails.guardianDateOfBirth, "dd-MMM-yyyy");

            try {
              if (vm.customerMasterDetails) {
                vm.customerMasterDetails.birthCertificate = vm.customerMasterDetails.birthCertificate ? "Y" : "N";
                vm.customerMasterDetails.passport = vm.customerMasterDetails.passport ? "Y" : "N";
                vm.customerMasterDetails.seniorCitizenCard = vm.customerMasterDetails.seniorCitizenCard ? "Y" : "N";
                vm.customerMasterDetails.voterIdCard = vm.customerMasterDetails.voterIdCard ? "Y" : "N";
                vm.customerMasterDetails.panCard = vm.customerMasterDetails.panCard ? "Y" : "N";
                vm.customerMasterDetails.drivingLicence = vm.customerMasterDetails.drivingLicence ? "Y" : "N";
                vm.customerMasterDetails.rationCard = vm.customerMasterDetails.rationCard ? "Y" : "N";
                vm.customerMasterDetails.otherDocuments = vm.customerMasterDetails.otherDocuments ? "Y" : "N";
              }
            } catch (e) {}

            try {
              if (vm.customerMasterDetailsTwo) {
                vm.customerMasterDetailsTwo.birthCertificate = vm.customerMasterDetailsTwo.birthCertificate ? "Y" : "N";
                vm.customerMasterDetailsTwo.passport = vm.customerMasterDetailsTwo.passport ? "Y" : "N";
                vm.customerMasterDetailsTwo.seniorCitizenCard = vm.customerMasterDetailsTwo.seniorCitizenCard ? "Y" : "N";
                vm.customerMasterDetailsTwo.voterIdCard = vm.customerMasterDetailsTwo.voterIdCard ? "Y" : "N";
                vm.customerMasterDetailsTwo.panCard = vm.customerMasterDetailsTwo.panCard ? "Y" : "N";
                vm.customerMasterDetailsTwo.drivingLicence = vm.customerMasterDetailsTwo.drivingLicence ? "Y" : "N";
                vm.customerMasterDetailsTwo.rationCard = vm.customerMasterDetailsTwo.rationCard ? "Y" : "N";
                vm.customerMasterDetailsTwo.otherDocuments = vm.customerMasterDetailsTwo.otherDocuments ? "Y" : "N";
              }
            } catch (e) {}

            try {
              if (vm.customerMasterDetailsThree) {
                vm.customerMasterDetailsThree.birthCertificate = vm.customerMasterDetailsThree.birthCertificate ? "Y" : "N";
                vm.customerMasterDetailsThree.passport = vm.customerMasterDetailsThree.passport ? "Y" : "N";
                vm.customerMasterDetailsThree.seniorCitizenCard = vm.customerMasterDetailsThree.seniorCitizenCard ? "Y" : "N";
                vm.customerMasterDetailsThree.voterIdCard = vm.customerMasterDetailsThree.voterIdCard ? "Y" : "N";
                vm.customerMasterDetailsThree.panCard = vm.customerMasterDetailsThree.panCard ? "Y" : "N";
                vm.customerMasterDetailsThree.drivingLicence = vm.customerMasterDetailsThree.drivingLicence ? "Y" : "N";
                vm.customerMasterDetailsThree.rationCard = vm.customerMasterDetailsThree.rationCard ? "Y" : "N";
                vm.customerMasterDetailsThree.otherDocuments = vm.customerMasterDetailsThree.otherDocuments ? "Y" : "N";
              }
            } catch (e) {}

            try {
              vm.customerMasterDetailsTwo.bondHldDob = $filter("date")(vm.customerMasterDetailsTwo.bondHldDob, "dd-MMM-yyyy");
              vm.customerMasterDetailsTwo.isMinor = vm.customerMasterDetailsTwoFlag;
            } catch (err) {}
            try {
              vm.customerMasterDetailsTwo.dateSubmission15g15htax = $filter("date")(vm.customerMasterDetailsTwo.dateSubmission15g15htax, "dd-MMM-yyyy");
            } catch (err) {}
            try {
              vm.customerMasterDetailsTwo.guardianDateOfBirth = $filter("date")(vm.customerMasterDetailsTwo.guardianDateOfBirth, "dd-MMM-yyyy");
            } catch (err) {}

            try {
              vm.customerMasterDetailsThree.bondHldDob = $filter("date")(vm.customerMasterDetailsThree.bondHldDob, "dd-MMM-yyyy");
              vm.customerMasterDetailsThree.isMinor = vm.customerMasterDetailsThreeFlag;
            } catch (err) {}
            try {
              vm.customerMasterDetailsThree.dateSubmission15g15htax = $filter("date")(vm.customerMasterDetailsThree.dateSubmission15g15htax, "dd-MMM-yyyy");
            } catch (err) {}
            try {
              vm.customerMasterDetailsThree.guardianDateOfBirth = $filter("date")(vm.customerMasterDetailsThree.guardianDateOfBirth, "dd-MMM-yyyy");
            } catch (err) {}

            var objOne = Object.fromEntries(Object.entries(vm.customerMasterDetails));
            objOne.jointHolderType = "S";
            if (vm.customerMasterDetailsTwo) {
              var objTwo = Object.fromEntries(Object.entries(vm.customerMasterDetailsTwo));
              objOne.jointHolderType = "1";
              objTwo.jointHolderType = "2";
              vm.resultArray.push(objTwo);
            }
            if (vm.customerMasterDetailsThree) {
              var objThree = Object.fromEntries(Object.entries(vm.customerMasterDetailsThree));
              objThree.jointHolderType = "3";
              vm.resultArray.push(objThree);
            }

            //                         angular.forEach(vm.coparcenerslist, function(item) {
            //                             // Check if coparcenersDob exists and if it's valid before applying the date filter
            //                             if (item.coparcenersDob) {
            //                                 item.coparcenersDob = $filter('date')(item.coparcenersDob, 'dd-MMM-yyyy');
            //                             }
            //                             // Push the item to resultArray2
            //                             vm.resultArray2.push(item);
            //                         });
            angular.forEach(vm.coparcenerslist, function (item) {
              // Remove $$hashKey from each item
              delete item.$$hashKey;

              // Check if coparcenersDob exists and if it's valid before applying the date filter
              if (item.coparcenersDob) {
                item.coparcenersDob = $filter("date")(item.coparcenersDob, "dd-MMM-yyyy");
              }

              // Push the item to resultArray2
              vm.resultArray2.push(item);
            });

            // If coparcenerslist is empty, push an empty object into resultArray2
            if (vm.resultArray2.length === 0) {
              //                             vm.resultArray2.push({});
            }

            vm.resultArray.push(objOne);

            if (vm.modeOfHolding == "MH002" || vm.modeOfHolding == "MH003") {
              vm.resultArray = orderArray(vm.resultArray);
            }

            var requestData = {
              dataList: vm.resultArray,
              dataCopersondetailsList: vm.resultArray2,
              schemeCode: vm.schemeCode,
              modeOfHolding: vm.modeOfHolding,
              id: vm.id,
              singleBondAccNo: vm.singleBondAccNo,
              firstJointHolderBlaNo: vm.firstJointHolderBlaNo,
              secondJointHolderName: vm.secondJointHolderName,
              thirdJointHolderBlaNo: vm.thirdJointHolderBlaNo,
              applicationNo: vm.applicationNo,
              brokerAgencyCode: vm.brokerId,
              subscriptionAmount: vm.subscriptionAmount,
              blaType: vm.blaFlag,
              accountOpenDate: $filter("date")(vm.accountOpeningDate, "dd-MMM-yyyy"),
              valueDate: $filter("date")(vm.valueDate, "dd-MMM-yyyy"),
              periodOfDeposit: vm.periodOfDeposit,
              maturityDate: $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.maturityDate)),
              rateOfInterest: vm.rateOfInterest,
              modeOfInterestPayment: vm.modeOfInterestPayment,
              tranMode: vm.transactionMode,
              instrumentAmount: vm.instrumentAmount,
              instrumentNo: vm.instrumentNo,
              instrumentType: vm.instrumentType,
              instrumentDate: $filter("date")(vm.instrumentDate, "dd-MMM-yyyy"),
              tranCode: vm.tranCode,
              zoneCode: vm.zoneCode,
              zoneDate: $filter("date")(vm.zoneDate, "dd-MMM-yyyy"),
              sortCode: vm.sortCode,
              bankCodeNonMicr: vm.bankCodeNonMicr,
              branchCodeNonMicr: vm.branchCodeNonMicr,
              debitAccNo: vm.debitAccNo,
              remarks: vm.remarks,
              isNominee: vm.isNominee,
              wboBranchCode: vm.wboBranchCode,
              wboBranchName: vm.wboBranchName,
              segment: vm.segment,
              wordsInRupees: vm.wordsInRupees,
              totalSharingPercent: vm.total,
              applicationDate: $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.applicationDate)),
            };
            requestData.jointHolderNo = vm.noOfJointAcc;
            if (vm.isNominee == "N") {
              requestData.nomineeDetailsList = null;
            } else {
              // Process nomination details
              angular.forEach(vm.nominationDetails, function (item) {
                // Remove $$hashKey from each nominee object
                delete item.$$hashKey;
                delete item.dateOfBirthOptions;

                // Format date of birth of minor
                item.dateOfBirthOfMinor = $filter("date")(item.dateOfBirthOfMinor, "dd-MMM-yyyy");
              });
              requestData.nomineeDetailsList = vm.nominationDetails;
            }

            modeOfHoldingService.addSubmit(requestData).then(
              (response) => {
                vm.loader = false;
                vm.responseResult = response.data.result;
                var message = "Bond Ledger Account Created Successfully";
                if ((vm.modeOfHolding == "MH001") && vm.blaFlag == "Y") {
                  message = message + "\nBLA No: " + vm.blaNoOut[0].blaNo;
                  if (vm.responseResult.singleGuardianId) {
                    message = message + "\nGuardian ID: " + vm.responseResult.singleGuardianId;
                  }
                  if (vm.responseResult.subscriptionId) {
                    message = message + "\nSubscription ID: " + vm.responseResult.subscriptionId;
                  }
                } else if ((vm.modeOfHolding == "MH001") && vm.blaFlag == "N") {
                  message = message + "\nBLA No: " + vm.responseResult.singleBlaNo;
                  if (vm.responseResult.singleGuardianId) {
                    message = message + "\nGuardian ID: " + vm.responseResult.singleGuardianId;
                  }
                  if (vm.responseResult.subscriptionId) {
                    message = message + "\nSubscription ID: " + vm.responseResult.subscriptionId;
                  }
                } else {
                  if (vm.responseResult.subscriptionId) {
                    message = message + "\nSubscription ID: " + vm.responseResult.subscriptionId;
                  }
                }
                Swal.fire("Success!", message, "success");
                $state.go("bla_acc_opening_cum_subscription.list");
              },
              function (error) {
                vm.loader = false;
                handleErrorResponse(error);
                $state.go("bla_acc_opening_cum_subscription.list");
              }
            );
         } }
        );
    }

    function handleHdfcSelection() {
      // Clear the operative account number when selection changes
      if (vm.customerMasterDetails.hdfcOtherBank === "N") {
        vm.customerMasterDetails.operativeAccNum = "";
      }
      if (vm.customerMasterDetailsTwo) {
        if (vm.customerMasterDetailsTwo.hdfcOtherBank === "N") {
          vm.customerMasterDetailsTwo.operativeAccNum = "";
        }
      }
      if (vm.customerMasterDetailsThree) {
        if (vm.customerMasterDetailsThree.hdfcOtherBank === "N") {
          vm.customerMasterDetailsThree.operativeAccNum = "";
        }
      }
    }

    function orderArray(list) {
      //this function will come joint holder greater than or equal to 2
      vm.tempArray = [];
      vm.tempArray.push("0");
      vm.tempArray.push("1");
      if (list.length > 2) {
        vm.tempArray.push("2");
      }
      angular.forEach(list, function (item) {
        if (item.jointHolderType == "1") {
          vm.tempArray.splice(0, "0", item);
        }
        if (item.jointHolderType == "2") {
          vm.tempArray.splice(1, "1", item);
        }
        if (item.jointHolderType == "3") {
          vm.tempArray.splice(2, "2", item);
        }
      });

      var value = vm.tempArray.filter((value) => {
        if (value == "0") {
        } else if (value == "1") {
        } else if (value == "2") {
        } else {
          return value;
        }
      });
      return value;
    }
    function convertYNToBoolean(value) {
      return value === "Y" || value === true;
    }


    function convertBooleanToYN(value) {
      return value ? "Y" : "N";
    }

    //Verify Select
    function getVerifySelectDetails() {
      vm.loader = true;
        var requestData = {
          schemeCode: vm.schemeCode,
          fromBlaNo: vm.fromBlaNo,
          blaNo: vm.blaNo,
          flag: 'D',
          panNum: vm.panNo,
          bondHolderName: vm.bondHolderName,
          modeOfHolding: vm.selectedModeOfHolding
        };
        modeOfHoldingService.verifySelect(requestData).then(
          function (response) {
            vm.loader = false;
            vm.getDetails = response.data.result;
            vm.reversalObj = response.data.result.reversalObj;
            vm.subscriptionIdList = response.data.result.reversalList;
             vm.amtInWords ="NDF";
           /*  if(vm.getDetails.subscriptionDetailsValueModel[0] &&
             vm.getDetails.subscriptionDetailsValueModel[0].C012)
           {
              vm.amtInWords = vm.getDetails.subscriptionDetailsValueModel[0].C044?.oldValue || amountToWords(vm.getDetails.subscriptionDetailsValueModel[0].C012.oldValue);
           }*/
            if (vm.getDetails.masterDetailsValueModel.length == 1) {
              vm.titleText = "BOND HOLDER DETAILS";
              if(vm.getDetails.masterDetailsValueModel[0] && vm.getDetails.masterDetailsValueModel[0].C050?.oldValue)
              {
              var flag='N';
            getbankNameM(vm.getDetails.masterDetailsValueModel[0].C050?.oldValue,flag);
              }
            if(vm.getDetails.masterDetailsValueModel[0] && vm.getDetails.masterDetailsValueModel[0].C050?.newValue)
            {
            var flag='Y';
            getbankNameM(vm.getDetails.masterDetailsValueModel[0].C050?.newValue,flag);
            }
            } else {
              vm.titleText = "FIRST JOINT BOND HOLDER DETAILS";
              vm.tempArray = [];
              vm.tempArray.push("0");
              vm.tempArray.push("1");
              if (vm.getDetails.masterDetailsValueModel.length > 2) {
                vm.tempArray.push("2");
              }
              angular.forEach(vm.getDetails.masterDetailsValueModel, function (item) {
                if (item.C048) {
                  if (item.C048.oldValue == "1") {
                    vm.tempArray.splice(0, "0", item);
                  }
                  if (item.C048.oldValue == "2") {
                    vm.tempArray.splice(1, "1", item);
                  }
                  if (item.C048.oldValue == "3") {
                    vm.tempArray.splice(2, "2", item);
                  }
                }
              });
              var value = vm.tempArray.filter((value) => {
                if (value == "0") {
                } else if (value == "1") {
                } else if (value == "2") {
                } else {
                  return value;
                }
              });
              vm.getDetails.masterDetailsValueModel = value;
               if(vm.getDetails.masterDetailsValueModel[0] && vm.getDetails.masterDetailsValueModel[0].C050?.oldValue)
                {
                var flag='N';
              getbankNameM(vm.getDetails.masterDetailsValueModel[0].C050?.oldValue,flag);
                }
              if(vm.getDetails.masterDetailsValueModel[0] && vm.getDetails.masterDetailsValueModel[0].C050?.newValue)
              {
              var flag='Y';
              getbankNameM(vm.getDetails.masterDetailsValueModel[0].C050?.newValue,flag);
              }
              angular.forEach(vm.getDetails.guardianDetailsValueModel, function (item) {
                if (item.C003.oldValue == "1") {
                  vm.tempArray.splice(0, "0", item);
                }
                if (item.C003.oldValue == "2") {
                  vm.tempArray.splice(1, "1", item);
                }
                if (item.C003.oldValue == "3") {
                  vm.tempArray.splice(2, "2", item);
                }
              });
              var value1 = vm.tempArray.filter((value1) => {
                if (value1 == "0") {
                } else if (value1 == "1") {
                } else if (value1 == "2") {
                } else {
                  return value1;
                }
              });
              vm.getDetails.guardianDetailsValueModel = value1;
            }
          },
          function (error) {
            vm.loader = false;
            handleErrorResponse(error);
            $state.go("mode_of_holding_change.list");
          }
        );
    }
    vm.getbankNameM=getbankNameM;
    function getbankNameM(branchCode,flag) {
                      var requestData = {
                        referenceId: branchCode,
                      };
                      modeOfHoldingService.getbankName(requestData).then(
                        function (response) {
                        if(flag=="N"){
                         vm.bankNameOldVal1 = response.data.result[0].bank_name;}
                        else if(flag=="Y"){
                         vm.bankNameNewVal1 = response.data.result[0].bank_name;}
                        },
                        function (error) {
                  handleErrorResponse(error);
                        });
                    }
    /* Verify Submit */
    function verifyCustomerMaster() {
//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to Really Verify this record?",
//          type: "warning",
//          showCancelButton: true,
//          cancelButtonClass: "btn-cancel",
//          confirmButtonClass: "btn-primary",
//          confirmButtonText: "Yes",
//          closeOnConfirm: false,
//        },
//        function () {




          Swal.fire({
            title: "Are you sure?",
            text: "Do you want to Really Verify this record?",
            icon: "warning", // use "icon", not "type"
            showCancelButton: true,
            confirmButtonText: "Yes",
            cancelButtonText: "Cancel",
            customClass: {
              confirmButton: "btn-primary",
              cancelButton: "btn-cancel"
            },
            buttonsStyling: false
          }).then((result) => {

                 if (result.isConfirmed) {
          vm.loader = true;
          vm.resultArray1 = [];
          angular.forEach(vm.subscriptionIdList, function (item) {
            // Remove $$hashKey from each item
          delete item.$$hashKey;
            vm.resultArray1.push(item);
          });
          let requestData = {
            schemeCode: vm.schemeCode,
            fromBlaNo: vm.reversalObj.fromBlaNo,
            blaNo: vm.reversalObj.toBlaNo,
            modeOfHolding: vm.reversalObj.modeOfHolding,
            bondHolderName: vm.reversalObj.bondHolderName,
            panNum: vm.reversalObj.panNum,
            blaGroupId: vm.getDetails.blaGroupId,
            reversalList:vm.resultArray1
          };
          modeOfHoldingService.verifySubmit(requestData).then(
            (response) => {
              vm.loader = false;
                var message = "BLA/Subscription Verified Successfully\n";
                vm.modeOfHolding = vm.getDetails.masterDetailsValueModel[0].C003.oldValue;
                if (vm.modeOfHolding === "MH001") {
                    if (vm.reversalObj && vm.reversalObj.toBlaNo) {
                        message += "\nBLA No: " + vm.reversalObj.toBlaNo +"\n";
                    }
                    if (vm.resultArray1 && vm.resultArray1.length > 0) {
                        var subscriptionIds = vm.resultArray1
                            .filter(function(item) {
                                return item && item.toSubscriptionId;
                            })
                            .map(function(item) {
                                return item.toSubscriptionId;
                            })
                            .join(", ");
                        if (subscriptionIds) {
                            message += "\nSUBSCRIPTION Id(s): " + subscriptionIds+"\n";
                        }
                    }
                    if (vm.getDetails && vm.getDetails.blaGroupId) {
                        message += "\nBLA GROUP Id: " + vm.getDetails.blaGroupId;
                    }
                }
                Swal.fire("Success!", message, "success");
              location.href = "#!/mode_of_holding_change/action/inquiry";
            },function (error) {
              vm.loader = false;
              handleErrorResponse(error);
              location.href = "#!/mode_of_holding_change/action/verify";
            }
          );
       } }
      );
    }
    vm.getRemarksList = function () {
      vm.referenceId = "056";
      var requestData = { referenceId: vm.referenceId };

      modeOfHoldingService.getRemarksList(requestData).then(
        function (response) {
          vm.remarksList = response.data.result || []; // Ensure it's an array
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    };

    vm.rejectReason = function () {
      angular.element("#permissionMappingDelete").modal("toggle");
    };

    vm.deleteSubmit = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        blaGroupId: vm.getDetails.blaGroupId,
        modeOfHolding: vm.modeOfHolding,
        panNum: vm.panNo,
        bondHolderName: vm.bondHolderName,
        fromBlaNo: vm.reversalObj.fromBlaNo,
        blaNo: vm.reversalObj.toBlaNo,
        rejectionRemarks: vm.rejectionRemarks
      };
      modeOfHoldingService.deleteSubmit(requestData).then(
        function (response) {
          if (response.data.message == "SUCCESS") {
            Swal.fire("Success!", "Rejected Successfully", "success");
            location.href = "#!/mode_of_holding_change/action/inquiry";
          } else {
            Swal.fire("WARNING!", response.data.message, "error");
            location.href = "#!/mode_of_holding_change/action/verify/detail";
          }
          const backdrop = document.querySelector(".modal-backdrop");
          if (backdrop) {
            backdrop.parentNode.removeChild(backdrop);
            document.body.classList.remove("modal-open");
          }
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
          const backdrop = document.querySelector(".modal-backdrop");
          if (backdrop) {
            backdrop.parentNode.removeChild(backdrop);
            document.body.classList.remove("modal-open");
          }
          location.href = "#!/mode_of_holding_change/action/verify/detail";
        }
      );
    };

    //Inquiry Select
    function getInquirySelectDetails(flag) {
      vm.loader = true;
        var requestData = {
          schemeCode: vm.schemeCode,
          fromBlaNo: vm.fromBlaNo,
          blaNo: vm.blaNo,
          flag: 'D',
          schemeName: vm.schemeName,
          panNum: vm.panNo,
          modeOfHolding: vm.selectedModeOfHolding,
          bondHolderName: vm.bondHolderName
        };
        modeOfHoldingService.inquirySelect(requestData).then(
          function (response) {
            vm.loader = false;
            vm.getDetails = response.data.result;
            vm.reversalObj = response.data.result.reversalObj;
            vm.subscriptionIdList = response.data.result.reversalList;
            vm.amtInWords ="NDF"
           /*if(vm.getDetails.subscriptionDetailsValueModel[0] &&
             vm.getDetails.subscriptionDetailsValueModel[0].C012)
           {
              vm.amtInWords = vm.getDetails.subscriptionDetailsValueModel[0].C044?.oldValue || amountToWords(vm.getDetails.subscriptionDetailsValueModel[0].C012.oldValue);
           }*/
            if (vm.getDetails.masterDetailsValueModel.length == 1) {
              vm.titleText = "BOND HOLDER DETAILS";
              if(vm.getDetails.masterDetailsValueModel[0] && vm.getDetails.masterDetailsValueModel[0].C050?.oldValue)
                {
                var flag='N';
              getbankNameM(vm.getDetails.masterDetailsValueModel[0].C050?.oldValue,flag);
                }
              if(vm.getDetails.masterDetailsValueModel[0] && vm.getDetails.masterDetailsValueModel[0].C050?.newValue)
              {
              var flag='Y';
              getbankNameM(vm.getDetails.masterDetailsValueModel[0].C050?.newValue,flag);
              }
            } else {
              vm.titleText = "FIRST JOINT BOND HOLDER DETAILS";

              vm.tempArray = [];
              vm.tempArray.push("0");
              vm.tempArray.push("1");
              if (vm.getDetails.masterDetailsValueModel.length > 2) {
                vm.tempArray.push("2");
              }

              angular.forEach(vm.getDetails.masterDetailsValueModel, function (item) {
                if (item.C048.oldValue == "1") {
                  vm.tempArray.splice(0, "0", item);
                }
                if (item.C048.oldValue == "2") {
                  vm.tempArray.splice(1, "1", item);
                }
                if (item.C048.oldValue == "3") {
                  vm.tempArray.splice(2, "2", item);
                }
              });
              //
              var value = vm.tempArray.filter((value) => {
                if (value == "0") {
                } else if (value == "1") {
                } else if (value == "2") {
                } else {
                  return value;
                }
              });
              vm.getDetails.masterDetailsValueModel = value;
            if(vm.getDetails.masterDetailsValueModel[0] && vm.getDetails.masterDetailsValueModel[0].C050?.oldValue)
              {
              var flag='N';
            getbankNameM(vm.getDetails.masterDetailsValueModel[0].C050?.oldValue,flag);
              }
            if(vm.getDetails.masterDetailsValueModel[0] && vm.getDetails.masterDetailsValueModel[0].C050?.newValue)
            {
            var flag='Y';
            getbankNameM(vm.getDetails.masterDetailsValueModel[0].C050?.newValue,flag);
            }
              angular.forEach(vm.getDetails.guardianDetailsValueModel, function (item) {
                if (item.C003.oldValue == "1") {
                  vm.tempArray.splice(0, "0", item);
                }
                if (item.C003.oldValue == "2") {
                  vm.tempArray.splice(1, "1", item);
                }
                if (item.C003.oldValue == "3") {
                  vm.tempArray.splice(2, "2", item);
                }
              });
              //
              var value1 = vm.tempArray.filter((value1) => {
                if (value1 == "0") {
                } else if (value1 == "1") {
                } else if (value1 == "2") {
                } else {
                  return value1;
                }
              });
              vm.getDetails.guardianDetailsValueModel = value1;
            }
          },
          function (error) {
            vm.loader = false;
            handleErrorResponse(error);
            $state.go("mode_of_holding_change.list");
          }
        );
    }
    function accountHolderDateValidation(minAge) {
      var solDate = vm.loginSolDate;
      var maxYear = convertToDateObject(solDate);
      maxYear.setFullYear(maxYear.getFullYear() - 18);
      var maxDate = maxYear;
      vm.dobCalenderOptions = {
        //minDate: minDate,
        maxDate: maxDate,
        showWeeks: false,
      };
      vm.customerMasterDetails.C020 = "";
    }

    function depositDateValidation() {
      vm.depositDate = {
        maxDate: convertToDateObject(vm.loginSolDate),
        showWeeks: false,
      };
    }

    function maturityDateValid(tempMatDate) {
      vm.maturityDate = {
        minDate: tempMatDate,
        showWeeks: false,
      };
    }

    function dateOfRetirementValid() {
      vm.customerMasterDetails.C022 = {
        minDate: convertToDateObject(vm.loginSolDate),
        showWeeks: false,
      };
    }

    function maturityDateValidation() {
      angular.forEach(vm.scssOtherBanksDetails, function (item) {
        item.C006 = item.C006;
        vm.maturityDate = {
          minDate: item.C006,
          showWeeks: false,
        };
      });
    }
    vm.retirDatePickerValidation = {
      showWeeks: false,
      maxDate: convertToDateObject(vm.loginSolDate),
    };
    // format date from dd/MM/yyyy to yyyy-MM-dd
    function formatDate(tempDate) {
      return $filter("date")(tempDate, "dd-MMM-yyyy");
    }

    function dodVal() {
      vm.dodCalenderOptions = {
        maxDate: convertToDateObject(vm.loginSolDate),
        showWeeks: false,
      };
    }

    function dodRecVal(recDate) {
      vm.dodRecCalenderOptions = {
        minDate: recDate,
        showWeeks: false,
      };
    }
    // Cancel Confirmation
    function showCancelConfirmation(state) {
//      Swal.fire(
//        {
//          title: "Confirm Cancel",
//          text: "Do you really want to cancel?",
//          type: "warning",
//          showCancelButton: true,
//          cancelButtonClass: "btn-cancel",
//          confirmButtonClass: "btn-danger",
//          confirmButtonText: "Yes",
//          cancelButtonText: "No",
//          closeOnConfirm: true,
//        },
//        function () {

            Swal.fire({
              title: "Are you sure?",
              text: "Do you want to Really Verify this record?",
              icon: "warning", // use "icon", not "type"
              showCancelButton: true,
              confirmButtonText: "Yes",
              cancelButtonText: "Cancel",
              customClass: {
                confirmButton: "btn-primary",
                cancelButton: "btn-cancel"
              },
              buttonsStyling: false
            }).then((result) => {

                   if (result.isConfirmed) {
          $state.go(state);
     }   }
      );
    }

    function showNomineeConfirmation() {
      if (vm.customerMasterDetailsThreeFlag === "Y" || vm.customerMasterDetails.isMinor === "Y" || vm.customerMasterDetailsTwoFlag === "Y") {
        if (vm.isNominee === "N") {
          Swal.fire(
            {
              title: "Warning",
              text: "Do you really wish to add this record without Nominee?",
              type: "warning",
              confirmButtonText: "OK",
              confirmButtonClass: "btn-primary",
            },
            function (isConfirm) {
              if (isConfirm) {
                // If the user confirms, clear the nominee selection
                // or set it to 'N' depending on your logic
              } else {
                vm.isNominee = "";
                // If the user cancels, you can retain the current selection
                // No action needed if the user clicks "No"
              }
            }
          );
        }
      }
    }

    function getDisableTorchFlag(customerId) {
      if (customerId) {
        if (customerId.length >= 5) {
          vm.disableTorchFlag = false;
        } else {
          vm.disableTorchFlag = true;
        }
      } else {
        vm.disableTorchFlag = true;
      }
    }

    function getCbsCustFstJoinDetailsTwo(accountNumber) {
      vm.loader = true;
      const accountNumberPattern = /^\d{10,15}$/;

      if (!accountNumberPattern.test(accountNumber)) {
        alert("Account number must be numeric and between 10 to 15 digits.");
        return;
      }

      modeOfHoldingService.fetchCbsCustId(accountNumber).then(
        (response) => {
          vm.loader = false;
          vm.cbsDetail = response.data.result.responseString.customerDetailsDTO[0];

          vm.customerMasterDetailsTwo.bondHldFirstName = vm.cbsDetail.customerFullName;
          vm.customerMasterDetailsTwo.bondHldMidName = vm.cbsDetail.customerMiddleName;
          vm.customerMasterDetailsTwo.bondHldLastName = vm.cbsDetail.customerLastName;
          vm.customerMasterDetailsTwo.fatherName = vm.cbsDetail.fatherName;
          vm.customerMasterDetailsTwo.motherName = vm.cbsDetail.motherName;
          vm.customerMasterDetailsTwo.gender = vm.cbsDetail.txtCustSex;
          vm.customerMasterDetailsTwo.bondHldDob = convertToDateObject(vm.cbsDetail.dateOfBirth);
          vm.customerMasterDetailsTwo.isMinor = vm.cbsDetail.isMinor;
          vm.customerMasterDetailsTwo.bondHldAddOne = vm.cbsDetail.txtCustadrAdd1;
          vm.customerMasterDetailsTwo.bondHldAddTwo = vm.cbsDetail.txtCustadrAdd2;
          vm.customerMasterDetailsTwo.bondHldAddThree = vm.cbsDetail.txtCustadrAdd3;
          vm.customerMasterDetailsTwo.bondHldCity = vm.cbsDetail.namHoldadrCity;
          vm.customerMasterDetailsTwo.bondHldPinCode = vm.cbsDetail.pinCode;
          vm.customerMasterDetailsTwo.bondHolderPhoneNumber = vm.cbsDetail.rmPhoneNo;
          vm.customerMasterDetailsTwo.bondHldMobNum = vm.cbsDetail.refPhoneMobile;
          vm.customerMasterDetailsTwo.bondHldEmail = vm.cbsDetail.refCustEmail;
          vm.customerMasterDetailsTwo.operativeAccNum = vm.cbsDetail.operativeAccNo;
          vm.customerMasterDetailsTwo.operativeAccBranchId = vm.cbsDetail.operativeBranchId;
          vm.customerMasterDetailsTwo.aadhaarNo = vm.cbsDetail.codAadhaarNo;
        },
        function (error) {
          vm.loader = false;
          vm.errmsgSingle = "CBS Failure";
          handleErrorResponse(error);
          vm.customerMasterDetailsTwo.cbsCustId = "";
        }
      );
    }

    function getBlaAccNoDetailsTwo(flag) {
      vm.loader = true;

      if (flag == "torch") {
        var requestData = {
          schemeCode: vm.schemeCode,
          modeOfHolding: vm.modeOfHolding,
        };
        modeOfHoldingService.fetchBlaAccNoDetails(requestData).then(
          (response) => {
            vm.loader = false;
            vm.blaNoListTwo = response.data.result;
            if (vm.modeOfHolding == "MH002" || vm.modeOfHolding == "MH003") {
              angular.element("#blaJointModalTwo").modal("toggle");
            } else {
              angular.element("#blaModalTwo").modal("toggle");
            }
          },
          function (error) {
            vm.loader = false;
            handleErrorResponse(error);
          }
        );
      } else {
        var requestData = {
          schemeCode: vm.schemeCode,
          modeOfHolding: vm.modeOfHolding,
          blaNum: vm.customerMasterTwo.blaAccNo,
          jointHolderNo: vm.jointHolderTypeCodeTwo,
        };
        modeOfHoldingService.fetchBlaAccNoDetails(requestData).then(
          (response) => {
            vm.loader = false;
            vm.blaAccDetailsTwo = response.data.result[0];
            vm.customerMasterDetailsTwo.blaAccNo = vm.blaAccDetailsTwo.blaNo;
            vm.customerMasterDetailsTwo.bondHldFirstName = vm.blaAccDetailsTwo.bondHolderFirstName;
            vm.customerMasterDetailsTwo.bondHldMidName = vm.blaAccDetailsTwo.bondHolderMiddleName;
            vm.customerMasterDetailsTwo.bondHldLastName = vm.blaAccDetailsTwo.bondHolderLastName;
            vm.customerMasterDetailsTwo.fatherName = vm.blaAccDetailsTwo.fatherName;
            vm.customerMasterDetailsTwo.motherName = vm.blaAccDetailsTwo.motherName;
            vm.customerMasterDetailsTwo.gender = vm.blaAccDetailsTwo.gender;
            vm.customerMasterDetailsTwo.bondHldDob = convertToDateObject(vm.blaAccDetailsTwo.dateOfBirth);
            vm.customerMasterDetailsTwo.isMinor = vm.blaAccDetailsTwo.isMinor;
            vm.customerMasterDetailsTwo.bondHldAddOne = vm.blaAccDetailsTwo.addressOne;
            vm.customerMasterDetailsTwo.bondHldAddTwo = vm.blaAccDetailsTwo.addressTwo;
            vm.customerMasterDetailsTwo.bondHldAddThree = vm.blaAccDetailsTwo.addressThree;
            vm.customerMasterDetailsTwo.bondHldCity = vm.blaAccDetailsTwo.city;
            vm.customerMasterDetailsTwo.bondHldPinCode = vm.blaAccDetailsTwo.pinCode;
            vm.customerMasterDetailsTwo.bondHolderPhoneNumber = vm.blaAccDetailsTwo.phoneNumber;
            vm.customerMasterDetailsTwo.bondHldMobNum = vm.blaAccDetailsTwo.mobileNumber;
            vm.customerMasterDetailsTwo.bondHldEmail = vm.blaAccDetailsTwo.email;
            vm.customerMasterDetailsTwo.operativeAccNum = vm.blaAccDetailsTwo.operativeAccNo;
            vm.customerMasterDetailsTwo.operativeAccBranchId = vm.blaAccDetailsTwo.operativeBranchId;
            vm.customerMasterDetailsTwo.panNo = vm.blaAccDetailsTwo.pan;
          },
          function (error) {
            vm.loader = false;
            handleErrorResponse(error);
          }
        );
      }
    }

    function getClearAllData(x) {
      vm.customerMasterDetails.blaAccNo = null;
      if (x == 1) {
        var z = Object.keys(vm.customerMasterDetails);
        for (var i = 0; i < z.length; i++) {
          vm.customerMasterDetails[z[i]] = "";
        }
      } else if (x == 2) {
        var z = Object.keys(vm.customerMasterDetailsTwo);
        for (var i = 0; i < z.length; i++) {
          vm.customerMasterDetailsTwo[z[i]] = "";
        }
      } else if (x == 3) {
        var z = Object.keys(vm.customerMasterDetailsThree);
        for (var i = 0; i < z.length; i++) {
          vm.customerMasterDetailsTwo[z[i]] = "";
        }
      }
    }

    function getClearAllDatas() {
      //                                         if(x ==1)
      //                                         {
      var z = Object.keys(vm.customerMasterDetails);
      for (var i = 0; i < z.length; i++) {
        vm.customerMasterDetails[z[i]] = "";
      }
    }

    // Add coperson function
    vm.copersons = [
      {
        name: "",
        relationship: "",
        dob: "",
        datePickerOpened: false,
      },
    ];

    function addCoperson() {
      vm.copersons.push({
        name: "",
        relationship: "",
        dob: "",
        datePickerOpened: false,
      });
    }

    // Delete coperson function
    //                                          function deleteCoperson(index){
    ////                                          vm.deleteCoperson = function(index) {
    //                                              vm.copersons.splice(index, 1);
    //                                          };

    vm.deleteLastCoperson = function () {
      if (vm.copersons.length > 0) {
        vm.copersons.splice(-1, 1); // Remove the last item from the array
      }
    };

    //                                          // Initialize modeOfHolding (assuming it comes from your data or service)
    //                                          vm.modeOfHolding = 'HUF';
    //                                      };

    function getGuardianDetailsTwo(flag) {
      vm.loader = true;

      if (flag == "select") {
        var requestData = {
          schemeCode: vm.schemeCode,
        };
        modeOfHoldingService.fetchGuardianDetails(requestData).then(
          (response) => {
            vm.loader = false;
            vm.guardianDetailsListTwo = response.data.result;
            angular.element("#guardianModalTwo").modal("toggle");
          },
          function (error) {
            vm.loader = false;
            handleErrorResponse(error);
          }
        );
      } else {
        var requestData = {
          schemeCode: vm.schemeCode,
          guardianId: vm.customerMasterDetailsTwo.guardianId,
        };
        modeOfHoldingService.fetchGuardianDetails(requestData).then(
          (response) => {
            vm.loader = false;
            vm.guardianDetailTwo = response.data.result[0];

            vm.customerMasterDetailsTwo.guardianFirstName = vm.guardianDetailTwo.guardianFirstName;
            vm.customerMasterDetailsTwo.guardianMiddleName = vm.guardianDetailTwo.guardianMiddleName;
            vm.customerMasterDetailsTwo.guardianLastName = vm.guardianDetailTwo.guardianLastName;
            vm.customerMasterDetailsTwo.relationWithMinor = vm.guardianDetailTwo.relationWithMinor;
            //                                    vm.customerMasterDetailsTwo.guardianDateOfBirth = convertToDateObject(vm.guardianDetailTwo.dateOfBirth);
            vm.guardianDateOfBirth = convertDateFormat(vm.guardianDetailTwo.guardianDateOfBirth);
            vm.customerMasterDetailsTwo.guardianDateOfBirth = vm.guardianDateOfBirth;
            vm.customerMasterDetailsTwo.guardianCommunicationAddress = vm.guardianDetailTwo.address;
            vm.customerMasterDetailsTwo.guardianMobileNo = vm.guardianDetailTwo.mobileNo;
            vm.customerMasterDetailsTwo.guardianEmailId = vm.guardianDetailTwo.email;
          },
          function (error) {
            vm.loader = false;
            handleErrorResponse(error);
          }
        );
      }
    }

    function optionToggledTwo() {
      vm.isAllSelected = [];
      angular.forEach(vm.kycList, function (item) {
        vm.isAllSelected.push(item.selected);
      });
    }

    function formFifteenGValidationTwo(value) {
      if (vm.customerMasterDetailsTwo.bondHldDob) {
        vm.customerMasterDetailsTwo.dateSubmission15g15htax = "";
        var year = parseInt($filter("date")(vm.customerMasterDetailsTwo.bondHldDob, "yyyy"));
        var cYear = parseInt($filter("date")(new Date(), "yyyy"));
        var age = cYear - year;
        if (value == "TF001") {
          //15g
          if (age > 60) {
            Swal.fire("Error!", "Age of the holder is NOT BELOW 60 years", "error");
            vm.customerMasterDetailsTwo.form15g15htaxExeCerti = "";
          }
          vm.customerMasterDetailsTwo.tdsRateApplicable = "0";
        }
        if (value == "TF002") {
          //15h
          if (age <= 60) {
            Swal.fire("Error!", "Age of the holder is BELOW 60 years", "error");
            vm.customerMasterDetailsTwo.form15g15htaxExeCerti = "";
          }
          vm.customerMasterDetailsTwo.tdsRateApplicable = "0";
        }
        if (value == "TF003") {
          //tax excemption cert
          vm.customerMasterDetailsTwo.tdsRateApplicable = "0";
        }
        if (value == "NONE") {
          vm.customerMasterDetailsTwo.tdsRateApplicable = vm.tdsRateApplicableResponse;
        }
      } else {
        Swal.fire("Error!", "Please select a age", "error");
        vm.customerMasterDetailsTwo.form15g15htaxExeCerti = "";
      }
    }

    function formFifteenGValidationTwoM(value) {
      if (vm.customerMasterDetailsTwo.C013.oldValue) {
        var year = parseInt($filter("date")(vm.customerMasterDetailsTwo.C013.oldValue, "yyyy"));
        var cYear = parseInt($filter("date")(new Date(), "yyyy"));
        var age = cYear - year;
        if (value == "TF001") {
          //15g
          if (age > 60) {
            Swal.fire("Error!", "Age of the holder is NOT BELOW 60 years", "error");
            vm.customerMasterDetailsTwo.C038.oldValue = "";
          }
          vm.customerMasterDetailsTwo.C064.oldValue = "0";
        }
        if (value == "TF002") {
          //15h
          if (age <= 60) {
            Swal.fire("Error!", "Age of the holder is BELOW 60 years", "error");
            vm.customerMasterDetailsTwo.C038.oldValue = "";
          }
          vm.customerMasterDetailsTwo.C064.oldValue = "0";
        }
        if (value == "TF003") {
          //tax excemption cert
          vm.customerMasterDetailsTwo.C064.oldValue = "0";
        }
        if (value == "NONE") {
          try {
            vm.customerMasterDetailsTwo.C039.oldValue = "";
          } catch (e) {}
          vm.customerMasterDetailsTwo.C064.oldValue = vm.tdsRateApplicableResponse;
        }
      } else {
        Swal.fire("Error!", "Please select a age", "error");
        vm.customerMasterDetailsTwo.C038.oldValue = "";
      }
    }

    function getCbsCustFstJoinDetailsThree(accountNumber) {
      vm.loader = true;
      const accountNumberPattern = /^\d{10,15}$/;

      if (!accountNumberPattern.test(accountNumber)) {
        alert("Account number must be numeric and between 10 to 15 digits.");
        return;
      }

      modeOfHoldingService.fetchCbsCustId(accountNumber).then(
        (response) => {
          vm.loader = false;
          vm.cbsDetail = response.data.result.responseString.customerDetailsDTO[0];

          vm.customerMasterDetailsThree.bondHldFirstName = vm.cbsDetail.customerFullName;
          vm.customerMasterDetailsThree.bondHldMidName = vm.cbsDetail.customerMiddleName;
          vm.customerMasterDetailsThree.bondHldLastName = vm.cbsDetail.customerLastName;
          vm.customerMasterDetailsThree.fatherName = vm.cbsDetail.fatherName;
          vm.customerMasterDetailsThree.motherName = vm.cbsDetail.motherName;
          vm.customerMasterDetailsThree.gender = vm.cbsDetail.txtCustSex;
          vm.customerMasterDetailsThree.bondHldDob = convertToDateObject(vm.cbsDetail.dateOfBirth);
          vm.customerMasterDetailsThree.isMinor = vm.cbsDetail.isMinor;
          vm.customerMasterDetailsThree.bondHldAddOne = vm.cbsDetail.txtCustadrAdd1;
          vm.customerMasterDetailsThree.bondHldAddTwo = vm.cbsDetail.txtCustadrAdd2;
          vm.customerMasterDetailsThree.bondHldAddThree = vm.cbsDetail.txtCustadrAdd3;
          vm.customerMasterDetailsThree.bondHldCity = vm.cbsDetail.namHoldadrCity;
          vm.customerMasterDetailsThree.bondHldPinCode = vm.cbsDetail.pinCode;
          vm.customerMasterDetailsThree.bondHolderPhoneNumber = vm.cbsDetail.rmPhoneNo;
          vm.customerMasterDetailsThree.bondHldMobNum = vm.cbsDetail.refPhoneMobile;
          vm.customerMasterDetailsThree.bondHldEmail = vm.cbsDetail.refCustEmail;
          vm.customerMasterDetailsThree.operativeAccNum = vm.cbsDetail.operativeAccNo;
          vm.customerMasterDetailsThree.operativeAccBranchId = vm.cbsDetail.operativeBranchId;
          vm.customerMasterDetailsThree.aadhaarNo = vm.cbsDetail.codAadhaarNo;
        },
        function (error) {
          vm.loader = false;
          vm.errmsgSingle = "CBS Failure";
          handleErrorResponse(error);
          vm.customerMasterDetailsThree.cbsCustId = "";
        }
      );
    }

    function getBlaAccNoDetailsThree(flag) {
      vm.loader = true;

      if (flag == "torch") {
        var requestData = {
          schemeCode: vm.schemeCode,
          modeOfHolding: vm.modeOfHolding,
        };
        modeOfHoldingService.fetchBlaAccNoDetails(requestData).then(
          (response) => {
            vm.loader = false;
            vm.blaNoListThree = response.data.result;
            if (vm.modeOfHolding == "MH002" || vm.modeOfHolding == "MH003") {
              angular.element("#blaJointModalThree").modal("toggle");
            } else {
              angular.element("#blaModal").modal("toggle");
            }
          },
          function (error) {
            vm.loader = false;
            handleErrorResponse(error);
          }
        );
      } else {
        var requestData = {
          schemeCode: vm.schemeCode,
          modeOfHolding: vm.modeOfHolding,
          blaNum: vm.customerMasterThree.blaAccNo,
          jointHolderNo: vm.jointHolderTypeCodeThree,
        };

        modeOfHoldingService.fetchBlaAccNoDetails(requestData).then(
          (response) => {
            vm.loader = false;
            vm.blaAccDetailsThree = response.data.result[0];
            vm.customerMasterDetailsThree.blaAccNo = vm.blaAccDetailsThree.blaNo;
            vm.customerMasterDetailsThree.bondHldFirstName = vm.blaAccDetailsThree.bondHolderFirstName;
            vm.customerMasterDetailsThree.bondHldMidName = vm.blaAccDetailsThree.bondHolderMiddleName;
            vm.customerMasterDetailsThree.bondHldLastName = vm.blaAccDetailsThree.bondHolderLastName;
            vm.customerMasterDetailsThree.fatherName = vm.blaAccDetailsThree.fatherName;
            vm.customerMasterDetailsThree.motherName = vm.blaAccDetailsThree.motherName;
            vm.customerMasterDetailsThree.gender = vm.blaAccDetailsThree.gender;
            vm.customerMasterDetailsThree.bondHldDob = convertToDateObject(vm.blaAccDetailsThree.dateOfBirth);
            vm.customerMasterDetailsThree.isMinor = vm.blaAccDetailsThree.isMinor;
            vm.customerMasterDetailsThree.bondHldAddOne = vm.blaAccDetailsThree.addressOne;
            vm.customerMasterDetailsThree.bondHldAddTwo = vm.blaAccDetailsThree.addressTwo;
            vm.customerMasterDetailsThree.bondHldAddThree = vm.blaAccDetailsThree.addressThree;
            vm.customerMasterDetailsThree.bondHldCity = vm.blaAccDetailsThree.city;
            vm.customerMasterDetailsThree.bondHldPinCode = vm.blaAccDetailsThree.pinCode;
            vm.customerMasterDetailsThree.bondHolderPhoneNumber = vm.blaAccDetailsThree.phoneNumber;
            vm.customerMasterDetailsThree.bondHldMobNum = vm.blaAccDetailsThree.mobileNumber;
            vm.customerMasterDetailsThree.bondHldEmail = vm.blaAccDetailsThree.email;
            vm.customerMasterDetailsThree.operativeAccNum = vm.blaAccDetailsThree.operativeAccNo;
            vm.customerMasterDetailsThree.operativeAccBranchId = vm.blaAccDetailsThree.operativeBranchId;
            vm.customerMasterDetailsThree.panNo = vm.blaAccDetailsThree.pan;
          },
          function (error) {
            vm.loader = false;
            handleErrorResponse(error);
          }
        );
      }
    }

    function getGuardianDetailsThree(flag) {
      vm.loader = true;

      if (flag == "select") {
        var requestData = {
          schemeCode: vm.schemeCode,
        };
        modeOfHoldingService.fetchGuardianDetails(requestData).then(
          (response) => {
            vm.loader = false;
            vm.guardianDetailsListThree = response.data.result;
            angular.element("#guardianModalThree").modal("toggle");
          },
          function (error) {
            vm.loader = false;
            handleErrorResponse(error);
          }
        );
      } else {
        var requestData = {
          schemeCode: vm.schemeCode,
          guardianId: vm.customerMasterDetailsThree.guardianId,
        };
        modeOfHoldingService.fetchGuardianDetails(requestData).then(
          (response) => {
            vm.loader = false;
            vm.guardianDetailThree = response.data.result[0];

            vm.customerMasterDetailsThree.guardianFirstName = vm.guardianDetailThree.guardianFirstName;
            vm.customerMasterDetailsThree.guardianMiddleName = vm.guardianDetailThree.guardianMiddleName;
            vm.customerMasterDetailsThree.guardianLastName = vm.guardianDetailThree.guardianLastName;
            vm.customerMasterDetailsThree.relationWithMinor = vm.guardianDetailThree.relationWithMinor;
            //                            vm.customerMasterDetailsThree.guardianDateOfBirth = convertToDateObject(vm.guardianDetailThree.dateOfBirth);
            vm.guardianDateOfBirth = convertDateFormat(vm.guardianDetailThree.guardianDateOfBirth);
            vm.customerMasterDetailsThree.guardianDateOfBirth = vm.guardianDateOfBirth;
            vm.customerMasterDetailsThree.guardianCommunicationAddress = vm.guardianDetailThree.address;
            vm.customerMasterDetailsThree.guardianMobileNo = vm.guardianDetailThree.mobileNo;
            vm.customerMasterDetailsThree.guardianEmailId = vm.guardianDetailThree.email;
          },
          function (error) {
            vm.loader = false;
            handleErrorResponse(error);
          }
        );
      }
    }

    function optionToggledThree() {
      vm.isAllSelected = [];
      angular.forEach(vm.kycList, function (item) {
        vm.isAllSelected.push(item.selected);
      });
    }

    function formFifteenGValidationThree(value) {
      debugger;
      if (vm.customerMasterDetailsThree.bondHldDob) {
        vm.customerMasterDetailsThree.dateSubmission15g15htax = "";
        var year = parseInt($filter("date")(vm.customerMasterDetailsThree.bondHldDob, "yyyy"));
        var cYear = parseInt($filter("date")(new Date(), "yyyy"));
        var age = cYear - year;
        if (value == "TF001") {
          //15g
          if (age > 60) {
            Swal.fire("Error!", "Age of the holder is NOT BELOW 60 years", "error");
            vm.customerMasterDetailsThree.form15g15htaxExeCerti = "";
          }
          vm.customerMasterDetailsThree.tdsRateApplicable = "0";
        }
        if (value == "TF002") {
          //15h
          if (age <= 60) {
            Swal.fire("Error!", "Age of the holder is BELOW 60 years", "error");
            vm.customerMasterDetailsThree.form15g15htaxExeCerti = "";
          }
          vm.customerMasterDetailsThree.tdsRateApplicable = "0";
        }
        if (value == "TF003") {
          //tax excemption cert
          vm.customerMasterDetailsThree.tdsRateApplicable = "0";
        }
        if (value == "NONE") {
          vm.customerMasterDetailsThree.tdsRateApplicable = vm.tdsRateApplicableResponse;
        }
      } else {
        Swal.fire("Error!", "Please select a age", "error");
        vm.customerMasterDetailsThree.form15g15htaxExeCerti = "";
      }
    }

    function formFifteenGValidationThreenM(value) {
      if (vm.customerMasterDetailsThree.C013.oldValue) {
        var year = parseInt($filter("date")(vm.customerMasterDetailsThree.C013.oldValue, "yyyy"));
        var cYear = parseInt($filter("date")(new Date(), "yyyy"));
        var age = cYear - year;
        if (value == "TF001") {
          //15g
          if (age > 60) {
            Swal.fire("Error!", "Age of the holder is NOT BELOW 60 years", "error");
            vm.customerMasterDetailsThree.C038.oldValue = "";
          }
          vm.customerMasterDetailsThree.C064.oldValue = "0";
        }
        if (value == "TF002") {
          //15h
          if (age <= 60) {
            Swal.fire("Error!", "Age of the holder is BELOW 60 years", "error");
            vm.customerMasterDetailsThree.C038.oldValue = "";
          }
          vm.customerMasterDetailsThree.C064.oldValue = "0";
        }
        if (value == "TF003") {
          //tax excemption cert
          vm.customerMasterDetailsThree.C064.oldValue = "0";
        }
        if (value == "NONE") {
          try {
            vm.customerMasterDetailsThree.C039.oldValue = "";
          } catch (e) {}

          vm.customerMasterDetailsThree.C064.oldValue = vm.tdsRateApplicableResponse;
        }
      } else {
        Swal.fire("Error!", "Please select a age", "error");
        vm.customerMasterDetailsThree.C038.oldValue = "";
      }
    }

    function getSchemeTypeListOperativeTwo(acctNum, flag) {
      vm.loader = true;
      vm.customerMasterDetailsTwo.operativeAccBranchId = "";

      if (acctNum) {
        commonService.getCustomAcctInqApi(
          acctNum.toUpperCase(),
          function (response) {
            if (response.success) {
              vm.accList = response.data;
              vm.loader = false;
              if (vm.accList.length > 0) {
                if (vm.accList[0].status == "S") {
                  if (vm.accList[0].accountFreezeStatus) {
                    vm.cbsErrorMessageTwo = "Account is Friezed";
                  } else {
                    vm.customerMasterDetailsTwo.operativeAccBranchId = vm.accList[0].solID;
                    vm.cbsErrorMessageTwo = null;
                  }
                }
                if (vm.accList[0].status == "F") {
                  vm.cbsErrorMessageTwo = "Invalid  Account  Number";
                }
              } else {
                vm.cbsErrorMessageTwo = "Invalid  Account  Number";
              }
            } else {
              Swal.fire("Error!", response.message, "error");
              vm.loader = false;
            }
          },
          function (err) {
            handleErrorResponse(err);
            vm.loader = false;
          }
        );
      } else {
        vm.loader = false;
      }
    }

    function getSchemeTypeListOperativeThree(acctNum, flag) {
      vm.loader = true;
      vm.customerMasterDetailsThree.operativeAccBranchId = "";
      vm.cbsErrorMessageThree = "";
      if (acctNum) {
        commonService.getCustomAcctInqApi(
          acctNum.toUpperCase(),
          function (response) {
            if (response.success) {
              vm.accList = response.data;
              vm.loader = false;
              if (vm.accList.length > 0) {
                if (vm.accList[0].status == "S") {
                  if (vm.accList[0].accountFreezeStatus) {
                    vm.cbsErrorMessageThree = "Account is Friezed";
                  } else {
                    vm.customerMasterDetailsThree.operativeAccBranchId = vm.accList[0].solID;
                    vm.cbsErrorMessageThree = null;
                  }
                }
                if (vm.accList[0].status == "F") {
                  vm.cbsErrorMessageThree = "Invalid  Account  Number";
                }
              } else {
                vm.cbsErrorMessageThree = "Invalid  Account  Number";
              }
            } else {
              Swal.fire("Error!", response.message, "error");
              vm.loader = false;
            }
          },
          function (err) {
            handleErrorResponse(err);
            vm.loader = false;
          }
        );
      } else {
        vm.loader = false;
      }
    }

    function getCbsCustFstJoinDetailsModify() {
      vm.loader = true;

      var requestData = {
        cifId: vm.customerMasterDetails.C004.oldValue,
        operativeAccNo: vm.customerMasterDetails.C034.oldValue,
        operativeAccBranchId: vm.customerMasterDetails.C035.oldValue,
      };

      modeOfHoldingService.fetchCbsCustId(requestData).then(
        (response) => {
          vm.loader = false;
          vm.cbsDetail = response.data.result;

          vm.customerMasterDetails.C006.oldValue = vm.cbsDetail.firstName;
          vm.customerMasterDetails.C007.oldValue = vm.cbsDetail.middleName;
          vm.customerMasterDetails.C008.oldValue = vm.cbsDetail.lastName;
          vm.customerMasterDetails.C010.oldValue = vm.cbsDetail.fatherName;
          vm.customerMasterDetails.C011.oldValue = vm.cbsDetail.motherName;
          vm.customerMasterDetails.C012.oldValue = vm.cbsDetail.txtCustSex;
          vm.customerMasterDetails.C013.oldValue = convertToDateObject(vm.cbsDetail.dateOfBirth);
          vm.customerMasterDetails.C014.oldValue = vm.cbsDetail.isMinor;
          vm.customerMasterDetails.C015.oldValue = vm.cbsDetail.addressOne;
          vm.customerMasterDetails.C016.oldValue = vm.cbsDetail.addressTwo;
          vm.customerMasterDetails.C017.oldValue = vm.cbsDetail.addressThree;
          vm.customerMasterDetails.C018.oldValue = vm.cbsDetail.city;
          vm.customerMasterDetails.C020.oldValue = vm.cbsDetail.pinCode;
          vm.customerMasterDetails.C046.oldValue = vm.cbsDetail.phoneNumber;
          vm.customerMasterDetails.C021.oldValue = vm.cbsDetail.mobileNumber;
          vm.customerMasterDetails.C022.oldValue = vm.cbsDetail.email;
          vm.customerMasterDetails.C034.oldValue = vm.cbsDetail.operativeAccNo;
          vm.customerMasterDetails.C035.oldValue = vm.cbsDetail.operativeBranchId;
          vm.customerMasterDetails.C036.oldValue = vm.cbsDetail.pan;
          vm.customerMasterDetails.C037.oldValue = vm.cbsDetail.aadhar;
          vm.customerMasterDetailsThree.C023.oldValue = vm.blaAccDetailsThree.hufRegPowerOfAttorney;
          vm.customerMasterDetailsThree.C024.oldValue = vm.blaAccDetailsThree.powerOfAttorneyKartaName;
        },
        function (error) {
          vm.loader = false;
          vm.errmsgSingle = "CBS Failure";
          handleErrorResponse(error);
          vm.customerMasterDetails.C004.oldValue = "";
        }
      );
    }

    function getCbsCustFstJoinDetailsTwoModify() {
      vm.loader = true;

      var requestData = {
        cifId: vm.customerMasterDetailsTwo.C004.oldValue,
        operativeAccNo: vm.customerMasterDetailsTwo.C034.oldValue,
        operativeAccBranchId: vm.customerMasterDetailsTwo.C035.oldValue,
      };

      modeOfHoldingService.fetchCbsCustId(requestData).then(
        (response) => {
          vm.loader = false;
          vm.cbsDetailTwo = response.data.result;
          vm.customerMasterDetailsTwo.C006.oldValue = vm.cbsDetailTwo.firstName;
          vm.customerMasterDetailsTwo.C007.oldValue = vm.cbsDetailTwo.middleName;
          vm.customerMasterDetailsTwo.C008.oldValue = vm.cbsDetailTwo.lastName;
          vm.customerMasterDetailsTwo.C010.oldValue = vm.cbsDetailTwo.fatherName;
          vm.customerMasterDetailsTwo.C011.oldValue = vm.cbsDetailTwo.motherName;
          vm.customerMasterDetailsTwo.C012.oldValue = vm.cbsDetailTwo.gender;
          vm.customerMasterDetailsTwo.C013.oldValue = convertToDateObject(vm.cbsDetailTwo.dateOfBirth);
          vm.customerMasterDetailsTwo.C014.oldValue = vm.cbsDetailTwo.isMinor;
          vm.customerMasterDetailsTwo.C015.oldValue = vm.cbsDetailTwo.addressOne;
          vm.customerMasterDetailsTwo.C016.oldValue = vm.cbsDetailTwo.addressTwo;
          vm.customerMasterDetailsTwo.C017.oldValue = vm.cbsDetailTwo.addressThree;
          vm.customerMasterDetailsTwo.C018.oldValue = vm.cbsDetailTwo.city;
          vm.customerMasterDetailsTwo.C020.oldValue = vm.cbsDetailTwo.pinCode;
          vm.customerMasterDetailsTwo.C046.oldValue = vm.cbsDetailTwo.phoneNumber;
          vm.customerMasterDetailsTwo.C021.oldValue = vm.cbsDetailTwo.mobileNumber;
          vm.customerMasterDetailsTwo.C022.oldValue = vm.cbsDetailTwo.email;
          vm.customerMasterDetailsTwo.C034.oldValue = vm.cbsDetailTwo.operativeAccNo;
          vm.customerMasterDetailsTwo.C035.oldValue = vm.cbsDetailTwo.operativeBranchId;
          vm.customerMasterDetailsTwo.C036.oldValue = vm.cbsDetailTwo.pan;
          vm.customerMasterDetailsTwo.C037.oldValue = vm.cbsDetailTwo.aadhar;
          vm.customerMasterDetailsThree.C023.oldValue = vm.blaAccDetailsThree.hufRegPowerOfAttorney;
          vm.customerMasterDetailsThree.C024.oldValue = vm.blaAccDetailsThree.powerOfAttorneyKartaName;
        },
        function (error) {
          vm.loader = false;
          vm.errmsgSingle = "CBS Failure";
          handleErrorResponse(error);
          vm.customerMasterDetailsTwo.C004.oldValue = "";
        }
      );
    }

    function getCbsCustFstJoinDetailsThreeModify() {
      vm.loader = true;

      var requestData = {
        cifId: vm.customerMasterDetailsThree.C004.oldValue,
        operativeAccNo: vm.customerMasterDetailsThree.C034.oldValue,
        operativeAccBranchId: vm.customerMasterDetailsThree.C035.oldValue,
      };

      modeOfHoldingService.fetchCbsCustId(requestData).then(
        (response) => {
          vm.loader = false;
          vm.cbsDetailThree = response.data.result;

          vm.customerMasterDetailsThree.C006.oldValue = vm.cbsDetailThree.firstName;
          vm.customerMasterDetailsThree.C007.oldValue = vm.cbsDetailThree.middleName;
          vm.customerMasterDetailsThree.C008.oldValue = vm.cbsDetailThree.lastName;
          vm.customerMasterDetailsThree.C010.oldValue = vm.cbsDetailThree.fatherName;
          vm.customerMasterDetailsThree.C011.oldValue = vm.cbsDetailThree.motherName;
          vm.customerMasterDetailsThree.C012.oldValue = vm.cbsDetailThree.gender;
          vm.customerMasterDetailsThree.C013.oldValue = convertToDateObject(vm.cbsDetailThree.dateOfBirth);
          vm.customerMasterDetailsThree.C014.oldValue = vm.cbsDetailThree.isMinor;
          vm.customerMasterDetailsThree.C015.oldValue = vm.cbsDetailThree.addressOne;
          vm.customerMasterDetailsThree.C016.oldValue = vm.cbsDetailThree.addressTwo;
          vm.customerMasterDetailsThree.C017.oldValue = vm.cbsDetailThree.addressThree;
          vm.customerMasterDetailsThree.C018.oldValue = vm.cbsDetailThree.city;
          vm.customerMasterDetailsThree.C020.oldValue = vm.cbsDetailThree.pinCode;
          vm.customerMasterDetailsThree.C046.oldValue = vm.cbsDetailThree.phoneNumber;
          vm.customerMasterDetailsThree.C021.oldValue = vm.cbsDetailThree.mobileNumber;
          vm.customerMasterDetailsThree.C022.oldValue = vm.cbsDetailThree.email;
          vm.customerMasterDetailsThree.C034.oldValue = vm.cbsDetailThree.operativeAccNo;
          vm.customerMasterDetailsThree.C035.oldValue = vm.cbsDetailThree.operativeBranchId;
          vm.customerMasterDetailsThree.C036.oldValue = vm.cbsDetailThree.pan;
          vm.customerMasterDetailsThree.C037.oldValue = vm.cbsDetailThree.aadhar;
          vm.customerMasterDetailsThree.C023.oldValue = vm.blaAccDetailsThree.hufRegPowerOfAttorney;
          vm.customerMasterDetailsThree.C024.oldValue = vm.blaAccDetailsThree.powerOfAttorneyKartaName;
        },
        function (error) {
          vm.loader = false;
          vm.errmsgSingle = "CBS Failure";
          handleErrorResponse(error);
          vm.customerMasterDetailsThree.C004.oldValue = "";
        }
      );
    }

    function checkAge(item) {
      if (typeof vm.customerMasterDetails.C013.oldValue != "undefined") {
        var dob = new Date(vm.customerMasterDetails.C013.oldValue);
        var month_diff = Date.now() - dob.getTime();
        var age_dt = new Date(month_diff);
        var year = age_dt.getUTCFullYear();

        var age = Math.abs(year - 1970);

        if (age >= 18) {
          Swal.fire("Not a Minor!");
          vm.customerMasterDetails.C013.oldValue = "";
        }
      }
    }
    function checkAge(isMinor, dateOfBirth, mode, list) {
      if (typeof dateOfBirth != "undefined") {
        var dob = new Date(dateOfBirth);
        var month_diff = Date.now() - dob.getTime();
        var age_dt = new Date(month_diff);
        var year = age_dt.getUTCFullYear();

        var age = Math.abs(year - 1970);

        if (age >= 18 && isMinor == "Y") {
          Swal.fire("Not a Minor!", "Please enter the correct DOB", "info");
          if (mode == "add") {
            if (list == "1") {
              vm.customerMasterDetails.bondHldDob = "";
            }
            if (list == "2") {
              vm.customerMasterDetailsTwo.bondHldDob = "";
            }
            if (list == "3") {
              vm.customerMasterDetailsThree.bondHldDob = "";
            }
          }
          if (mode == "modify") {
            if (list == "1") {
              vm.customerMasterDetails.C016.oldValue = '';
              vm.customerMasterDetailsTwo.C013.oldValue = "";
            }
            if (list == "2") {
              vm.customerMasterDetailsTwo.C013.oldValue = "";
            }
            if (list == "3") {
              vm.customerMasterDetailsThree.C013.oldValue = "";
            }
          }
        } else if (age < 18 && isMinor == "N") {
          Swal.fire("Not a Major!", "Please enter the correct DOB", "info");
          if (mode == "add") {
            if (list == "1") {
              vm.customerMasterDetails.bondHldDob = "";
            }
            if (list == "2") {
              vm.customerMasterDetailsTwo.bondHldDob = "";
            }
            if (list == "3") {
              vm.customerMasterDetailsThree.bondHldDob = "";
            }
          }
          if (mode == "modify") {
            if (list == "1") {
              vm.customerMasterDetails.C016.oldValue = '';
              vm.customerMasterDetails.C013.oldValue = "";
            }
            if (list == "2") {
              vm.customerMasterDetailsTwo.C013.oldValue = "";
            }
            if (list == "3") {
              vm.customerMasterDetailsThree.C013.oldValue = "";
            }
          }
        }
      }
    }

    function validateOperativeAccNumForDebit(acctNum) {
      vm.cbsErrorMessageForDebit = "";
      if (acctNum) {
        vm.cbsErrorMessage = "";
        vm.loader = true;
        commonService.getCustomAcctInqApi(
          acctNum,
          function (response) {
            if (response.success) {
              vm.accList = response.data;
              vm.loader = false;
              if (vm.accList.length >= 0) {
                if (vm.accList[0].status == "S") {
                  if (vm.accList[0].accountFreezeStatus) {
                    vm.cbsErrorMessageForDebit = "Account is Freezed";
                    vm.debitAccNo = "";
                    vm.debitBranchId = "";
                  } else {
                    vm.debitBranchId = vm.accList[0].solID;
                    vm.cbsErrorMessageForDebit = "";
                  }
                }
                if (vm.accList[0].status == "F") {
                  vm.cbsErrorMessageForDebit = "Invalid Account Number";
                  vm.debitAccNo = "";
                  vm.debitBranchId = "";
                }
              } else {
                vm.cbsErrorMessageForDebit = "Invalid Account Number";
                vm.debitAccNo = "";
                vm.debitBranchId = "";
              }
            } else {
              Swal.fire("Error!", response.message, "error");
              vm.loader = false;
              vm.debitAccNo = "";
              vm.debitBranchId = "";
            }
          },
          function (err) {
            handleErrorResponse(err);
            vm.loader = false;
            vm.debitAccNo = "";
            vm.operativeAccBranchId = "";
          }
        );
      }
    }

    /*Operative Acct Num*/
    function validateOperativeAccNumForDebitModify(acctNum) {
      vm.cbsErrorMessageForDebit = "";
      if (acctNum) {
        vm.cbsErrorMessage = "";
        vm.loader = true;
        commonService.getCustomAcctInqApi(
          acctNum,
          function (response) {
            if (response.success) {
              vm.accList = response.data;
              vm.loader = false;
              if (vm.accList.length >= 0) {
                if (vm.accList[0].status == "S") {
                  if (vm.accList[0].accountFreezeStatus) {
                    vm.cbsErrorMessageForDebit = "Account is Friezed";
                    vm.getDetails.C032.oldValue = "";
                    vm.getDetails.C033.oldValue = "";
                  } else {
                    vm.getDetails.C033.oldValue = vm.accList[0].solID;
                    vm.cbsErrorMessageForDebit = "";
                  }
                }
                if (vm.accList[0].status == "F") {
                  vm.cbsErrorMessageForDebit = "Invalid Account Number";
                  vm.getDetails.C032.oldValue = "";
                  vm.getDetails.C033.oldValue = "";
                }
              } else {
                vm.cbsErrorMessageForDebit = "Invalid Account Number";
                vm.getDetails.C032.oldValue = "";
                vm.getDetails.C033.oldValue = "";
              }
            } else {
              Swal.fire("Error!", response.message, "error");
              vm.loader = false;
              vm.getDetails.C032.oldValue = "";
              vm.getDetails.C033.oldValue = "";
            }
          },
          function (err) {
            handleErrorResponse(err);
            vm.loader = false;
            vm.getDetails.C032.oldValue = "";
            vm.getDetails.C033.oldValue = "";
          }
        );
      }
    }

    function validateOperativeAccNumM(acctNum) {
      vm.loader = true;
      vm.cbsErrorMessage = "";
      if (acctNum) {
        commonService.getCustomAcctInqApi(
          acctNum,
          function (response) {
            if (response.success) {
              vm.accList = response.data;
              vm.loader = false;
              if (vm.accList.length > 0) {
                if (vm.accList[0].status == "S") {
                  if (vm.accList[0].accountFreezeStatus) {
                    vm.cbsErrorMessage = "Account is Friezed";
                    vm.getDetails.C023.oldValue = null;
                    vm.getDetails.C024.oldValue = null;
                  } else {
                    if (vm.getDetails.C024) {
                      vm.getDetails.C024.oldValue = vm.accList[0].solID;
                    } else {
                      vm.getDetails.C024 = {};
                      vm.getDetails.C024.oldValue = "";
                      vm.getDetails.C024.oldValue = vm.accList[0].solID;
                    }
                  }
                }
                if (vm.accList[0].status == "F") {
                  vm.cbsErrorMessage = vm.accList[0].errDesc;
                  vm.getDetails.C024.oldValue = null;
                }
              } else {
                vm.getDetails.C023.oldValue = null;
                vm.getDetails.C024.oldValue = null;
                vm.cbsErrorMessage = "Invalid Account Number";
              }
            } else {
              vm.getDetails.C023.oldValue = null;
              vm.getDetails.C024.oldValue = null;
              Swal.fire("Error!", response.message, "error");
              vm.loader = false;
            }
          },
          function (err) {
            vm.getDetails.C023.oldValue = null;
            vm.getDetails.C024.oldValue = null;
            handleErrorResponse(err);
            vm.loader = false;
          }
        );
      } else {
        vm.loader = false;
      }
    }

    function validateOperativeAccNumNominee(acctNum) {
      vm.loader = true;
      if (acctNum) {
        commonService.getCustomAcctInqApi(
          acctNum,
          function (response) {
            if (response.success) {
              vm.accList = response.data;
              vm.loader = false;
              if (vm.accList.length > 0) {
                if (vm.accList[0].status == "S") {
                  if (vm.accList[0].accountFreezeStatus) {
                    vm.cbsErrorMessage = "Account is Friezed";
                  } else {
                    vm.operativeAccBranchId = vm.accList[0].solID;
                  }
                }
                if (vm.accList[0].status == "F") {
                  vm.cbsErrorMessage = vm.accList[0].errDesc;
                }
              } else {
                vm.cbsErrorMessage = "Invalid Account Number";
              }
            } else {
              Swal.fire("Error!", response.message, "error");
              vm.loader = false;
            }
          },
          function (err) {
            handleErrorResponse(err);
            vm.loader = false;
          }
        );
      } else {
        vm.loader = false;
      }
    }

    function inquirySubscriptionTorch() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        blaNo: vm.blaNo,
      };
      modeOfHoldingService.getInquiryDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.subscriptionModalList = response.data.result.torchValue;
          angular.element("#subscriptionModal").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function modifyBlaTorch() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
      };
      modeOfHoldingService.getModifySelect(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaModalList = response.data.result.torchValue;
          angular.element("#blaModal").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function modifySubscriptionTorch() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        blaNo: vm.blaNo,
      };
      modeOfHoldingService.getModifySelect(requestData).then(
        function (response) {
          vm.loader = false;
          vm.subscriptionModalList = response.data.result.torchValue;
          angular.element("#subscriptionModal").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

     function getOtherBankDetailsTorch(flag) {
              vm.loader = true;
              vm.searchSolId = "";
              var requestData = {};
              if(flag == 'bankName'){
              requestData.nonBankName = vm.customerMasterDetails.bankName;
              }
              if(flag == 'ifsc'){
              requestData.bankCode = vm.customerMasterDetails.bankCode;
              requestData.instIfscCode = vm.customerMasterDetails.ifscCode;
              }
              if(flag == 'micrCodeBank'){
              requestData.bankCode = vm.customerMasterDetails.bankCode;
              requestData.micrBankCode = vm.customerMasterDetails.micrCode;
              }
              if(flag == 'micrBranchCode'){
              requestData.bankCode = vm.customerMasterDetails.bankCode;
              requestData.micrBranchCode = vm.customerMasterDetails.branchCode;
              }
              modeOfHoldingService.getNonHdfcTorch(requestData).then(
                function (response) {
                  vm.loader = false;
                  vm.nonHdfcTorchList = response.data.result.torchValue;
                  if(flag == 'bankName'){
                  angular.element("#rbiCodeModal").modal("toggle");
                  }else if(vm.customerMasterDetails.modeOfInterestPayment == 'MP003'){
                  angular.element("#rbiCodeModalIfscNeft").modal("toggle");
                  }else{
                  angular.element("#rbiCodeModalIfsc").modal("toggle");
                  }
                },
                function (error) {
                  handleErrorResponse(error);
                  vm.loader = false;
                }
              );
        }
        function getOtherBankDetailsTorchM(flag) {
                  vm.loader = true;
                  vm.searchSolId = "";
                  var requestData = {};
                  if (flag === 'bankName') {
                    requestData.nonBankName = vm.customerMasterDetails?.C050?.oldValue ?? null;
                  }
                  if (flag === 'ifsc') {
                    requestData.bankCode = vm.customerMasterDetails?.C050?.oldValue ?? null;
                    requestData.instIfscCode = vm.customerMasterDetails?.C031?.oldValue ?? null;
                  }
                  if (flag === 'micrCodeBank') {
                    requestData.bankCode = vm.customerMasterDetails?.C050?.oldValue ?? null;
                    requestData.micrBankCode = vm.customerMasterDetails?.C054?.oldValue ?? null;
                  }
                  if (flag === 'micrBranchCode') {
                    requestData.bankCode = vm.customerMasterDetails?.C050?.oldValue ?? null;
                    requestData.micrBranchCode = vm.customerMasterDetails?.C051?.oldValue ?? null;
                  }
                  modeOfHoldingService.getNonHdfcTorch(requestData).then(
                    function (response) {
                      vm.loader = false;
                      vm.nonHdfcTorchList = response.data.result.torchValue;
                      if(flag == 'bankName'){
                      angular.element("#rbiCodeModal").modal("toggle");
                      }else if(vm.customerMasterDetails.modeOfInterestPayment == 'MP003'){
                      angular.element("#rbiCodeModalIfscNeft").modal("toggle");
                      }else{
                      angular.element("#rbiCodeModalIfsc").modal("toggle");
                      }
                    },
                    function (error) {
                      handleErrorResponse(error);
                      vm.loader = false;
                    }
                  );
            }
            vm.getOtherBankDetailsTorchDAD=getOtherBankDetailsTorchDAD;
function getOtherBankDetailsTorchDAD(flag) {
          vm.loader = true;
          vm.searchSolId = "";
          var requestData = {};
          if(flag == 'bankName'){
          requestData.nonBankName = vm.bankCodeNonMicr;
          }
          if(flag == 'micrBranchCode'){
          requestData.bankCode =  vm.bankCode;
          requestData.micrBranchCode = vm.branchCodeNonMicr;
          }
          modeOfHoldingService.getNonHdfcTorch(requestData).then(
            function (response) {
              vm.loader = false;
              vm.nonHdfcTorchList = response.data.result.torchValue;
              if(flag == 'bankName'){
              angular.element("#rbiCodeModalDAD").modal("toggle");
              }else{
              angular.element("#rbiCodeModalIfscDAD").modal("toggle");
              }
            },
            function (error) {
              handleErrorResponse(error);
              vm.loader = false;
            }
          );
    }
vm.getOtherBankDetailsTorchDADM=getOtherBankDetailsTorchDADM;
function getOtherBankDetailsTorchDADM(flag) {
          vm.loader = true;
          vm.searchSolId = "";
          var requestData = {};
          if(flag == 'bankName'){
          requestData.nonBankName = vm.subscriptionDetailsValueModel.C036.oldValue;
          }
          if(flag == 'micrBranchCode'){
          requestData.bankCode =  vm.subscriptionDetailsValueModel.C036.oldValue;
          requestData.micrBranchCode = vm.subscriptionDetailsValueModel?.C037?.oldValue;
          }
          modeOfHoldingService.getNonHdfcTorch(requestData).then(
            function (response) {
              vm.loader = false;
              vm.nonHdfcTorchList = response.data.result.torchValue;
              if(flag == 'bankName'){
              angular.element("#rbiCodeModalDAD").modal("toggle");
              }else{
              angular.element("#rbiCodeModalIfscDAD").modal("toggle");
              }
            },
            function (error) {
              handleErrorResponse(error);
              vm.loader = false;
            }
          );
    }
    function getOtherBankDetailsMicrTorchM() {
      if (flag == "torch") {
        vm.loader = true;
        vm.searchSolId = "";
        var requestData = {};
        modeOfHoldingService.getNonHdfcTorch(requestData).then(
          function (response) {
            vm.loader = false;
            vm.otherBankDetailsTorchList = response.data.result.torchValue;
            angular.element("#OtherBankDetailsMicrTorchM").modal("toggle");
          },
          function (error) {
            handleErrorResponse(error);
            vm.loader = false;
          }
        );
      } else {
        vm.loader = true;
        vm.searchSolId = "";
        var requestData = {
          bankCodeNonMicr: vm.subscriptionDetailsValueModel.C036,
        };
        modeOfHoldingService.getNonHdfcBranchTorch(requestData).then(
          function (response) {
            vm.loader = false;
            vm.otherBankDetailsTorchList = response.data.result.torchValue;
            angular.element("#OtherBankDetailsMicrTorchM").modal("toggle");
          },
          function (error) {
            handleErrorResponse(error);
            vm.loader = false;
          }
        );
      }
    }

    function verifyBlaTorch() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
      };
      modeOfHoldingService.getVerifySelect(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaModalList = response.data.result.torchValue;
          angular.element("#blaModal").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function validateMultiples(amount, flag) {
      vm.bondCashLimit = parseInt(vm.bondCashLimit);
      vm.bondClearingTransferLimit = parseInt(vm.bondClearingTransferLimit);
      vm.depositMultiples = parseInt(vm.depositMultiples);
      vm.minCashLimit = parseInt(vm.minCashLimit);
      amount = parseInt(amount);

      if (amount) {
        if (amount % vm.depositMultiples == 0) {
          vm.depositMultiplesErrMsg = "";
        } else {
          vm.depositMultiplesErrMsg = "Subscription Amount must be multiples of " + vm.depositMultiples;
          if (flag == "add") vm.subscriptionAmount = "";
          else vm.subscriptionDetailsValueModel.C012.oldValue = "";
        }
      }

      if (amount && vm.transactionMode) {
        if (vm.transactionMode == "C") {
          if (amount > vm.bondCashLimit || amount < vm.minCashLimit) {
            vm.depositMultiplesErrMsg = "Subscription Amount must be between " + vm.minCashLimit + " and " + vm.bondCashLimit;
            if (flag == "add") vm.subscriptionAmount = "";
            else vm.subscriptionDetailsValueModel.C012.oldValue = "";
          } else {
            vm.depositMultiplesErrMsg = "";
          }
        } else if (vm.transactionMode == "T" || vm.transactionMode == "CL") {
          if (amount > vm.bondClearingTransferLimit) {
            vm.depositMultiplesErrMsg = "Subscription Amount must be between " + vm.minCashLimit + " and " + vm.bondClearingTransferLimit;

            if (flag == "add") vm.subscriptionAmount = "";
            else vm.subscriptionDetailsValueModel.C012.oldValue = "";
          } else {
            vm.depositMultiplesErrMsg = "";
          }
        }
      }
      if (!amount) {
        vm.depositMultiplesErrMsg = "Subscription Amount must not be zero";
      }
    }

    function verifySubscriptionTorch() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        blaNo: vm.blaNo,
      };
      modeOfHoldingService.getVerifySelect(requestData).then(
        function (response) {
          vm.loader = false;
          vm.subscriptionModalList = response.data.result.torchValue;
          angular.element("#subscriptionModal").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function getAddBlaDetails() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        blaNo: vm.blaNo,
      };
      modeOfHoldingService.getValidationAdd(requestData).then(
        function (response) {
          vm.accountDetails = response.data.result;
          vm.bondHolderDetails = vm.accountDetails.torchValue;
          vm.periodOfDeposit = vm.accountDetails.periodOfDeposit;
          vm.rateOfInterest = vm.accountDetails.rateOfInterest;
          vm.creditAccNo = vm.accountDetails.neftPlaceHolderAccount;
          vm.bondCashAccount = vm.accountDetails.bondCashAccount;
          vm.bondClearingAccount = vm.accountDetails.bondClearingAccount;
          vm.bondCollectionAccount = vm.accountDetails.bondCollectionAccount;
          vm.bondCashLimit = vm.accountDetails.cashLimitForCash;
          vm.bondClearingTransferLimit = vm.accountDetails.cashLimitForClearingTransfer;
          vm.depositMultiples = vm.accountDetails.depositMultiples;
          vm.minCashLimit = vm.accountDetails.minCashLimit;
          vm.bondCashAccFlgOut = vm.accountDetails.bondCashAccFlgOut;
          vm.bondClearingAccFlgOut = vm.accountDetails.bondClearingAccFlgOut;
          vm.bondCollectionAccFlgOut = vm.accountDetails.bondCollectionAccFlgOut;
          vm.ddPoAccFlgOut = vm.accountDetails.ddPoAccFlgOut;
          vm.ddPoAccountNo = vm.accountDetails.ddPoAccountNo;
          vm.neftPlaceholderFlgOut = vm.accountDetails.neftPlaceholderFlgOut;
          vm.tranMode = vm.accountDetails.tranMode;

          vm.accountOpeningDate = $filter("date")(convertToDateObject($rootScope.globals.currentUser.solDate), "dd/MM/yyyy");

          var numberOfMonths = vm.periodOfDeposit; // Number of months to add
          var currentDateTime = new Date(convertToDateObject($rootScope.globals.currentUser.solDate)); // Convert current date string to Date object
          //  var nextDate = new Date(currentDateTime.getFullYear(), currentDateTime.getMonth() + numberOfMonths, currentDateTime.getDate());

          var convertedDate = new Date(currentDateTime.setMonth(parseInt(currentDateTime.getMonth()) + parseInt(numberOfMonths)));
          vm.maturityDate = $filter("date")(convertedDate, "dd/MM/yyyy");

          angular.forEach(vm.bondHolderDetails, function (item) {
            if (item.jointHolderNo == "S") {
              vm.bondHolderDetailSingleBlaNo = item.blaNo;
              vm.bondHolderDetailSingleBondHolderName = item.nameOfBondHolder;
            }
            if (item.jointHolderNo == "1") {
              vm.bondHolderDetailFirstBlaNo = item.blaNo;
              vm.bondHolderDetailFirstBondHolderName = item.nameOfBondHolder;
            }
            if (item.jointHolderNo == "2") {
              vm.bondHolderDetailSecondBlaNo = item.blaNo;
              vm.bondHolderDetailSecondBondHolderName = item.nameOfBondHolder;
            }
          });

          vm.loader = false;
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
          $state.go("bla_acc_opening_cum_subscription.add");
        }
      );
    }

    function modeOfInterestPaymentValidation(value) {
      vm.customerMasterDetails.operativeAccNumber = "";
      vm.customerMasterDetails.cbsErrorMessage = "";
      vm.customerMasterDetails.operativeAccBranchId = "";
      vm.customerMasterDetails.bankCode = "";
      vm.customerMasterDetails.branchCode = "";
      vm.customerMasterDetails.creditBankIfscCode = "";
      vm.customerMasterDetails.nameOfthePayee = "";
      vm.customerMasterDetails.creditAccNo = "";

      if (value == "MP002") {
        vm.ddPoAccountNo = vm.accountDetails.ddPoAccountNo;
      }
    }

    function modeOfInterestPaymentValidationM(value) {
      vm.getDetails.C023 ? (vm.getDetails.C023.oldValue = "") : "";
      vm.cbsErrorMessage = "";
      vm.getDetails.C024 ? (vm.getDetails.C024.oldValue = "") : "";
      vm.getDetails.C026 ? (vm.getDetails.C026.oldValue = "") : "";
      vm.getDetails.C027 ? (vm.getDetails.C027.oldValue = "") : "";
      vm.getDetails.C028 ? (vm.getDetails.C028.oldValue = "") : "";
      vm.getDetails.C029 ? (vm.getDetails.C029.oldValue = "") : "";
      vm.getDetails.C030 ? (vm.getDetails.C030.oldValue = "") : "";

      if (value == "MP002") {
        vm.getDetails.C025 = {};
        vm.getDetails.C025.oldValue = parseInt(vm.ddPoAccountNo);
      }
      if (value == "MP003") {
        vm.getDetails.C030 = {};
        vm.getDetails.C030.oldValue = parseInt(vm.neftCreditAccNo);
      }
    }

    function getApplicationNoTorch() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        //                        "applicationNo": vm.blaNo,
      };
      modeOfHoldingService.getApplicationTorch(requestData).then(
        function (response) {
          vm.loader = false;
          vm.applicationDetailsList = response.data.result.torchValue;
          angular.element("#applicationModal").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function getApplicationDetails(value) {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        applicationNo: vm.applicationNo,
      };
      if (value == "M") {
        requestData.applicationNo = vm.getDetails.C011.oldValue;
      }
      modeOfHoldingService.getApplicationTorch(requestData).then(
        function (response) {
          vm.loader = false;
          vm.applicationDetails = response.data.result;
          vm.applicationDate = vm.applicationDetails.torchValue[0].applicationDate;

          vm.subscriptionAmount = vm.applicationDetails.torchValue[0].subscriptionAmount;
          vm.debitAccNo = vm.applicationDetails.torchValue[0].debitAccNo;

          vm.transactionMode = vm.applicationDetails.torchValue[0].tranMode;

          if (value == "M") {
            vm.getDetails.C012.oldValue = vm.applicationDetails.torchValue[0].applicationDate;
            vm.getDetails.C014.oldValue = vm.applicationDetails.torchValue[0].subscriptionAmount;
            vm.getDetails.C032.oldValue = vm.applicationDetails.torchValue[0].debitAccNo;
            vm.getDetails.C031.oldValue = vm.applicationDetails.torchValue[0].tranMode;
          }
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function getBrokerTorch() {
      vm.loader = true;
      var requestData = {};

      modeOfHoldingService.getBrokerTorch(requestData).then(
        function (response) {
          vm.loader = false;
          vm.brokerDetailsList = response.data.result.torchValue;
          angular.element("#brokerDetailsModal").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function getModifyBrokerTorch(invoiceFlag, paymentFlag) {
      if (invoiceFlag == "Y"){
        Swal.fire("Warning!", "Payment Completed.", "warning");
      }else if(invoiceFlag == "I"|| invoiceFlag=="N") {
//        Swal.fire({
//         title: "Modify Broker Details?",
//         text: invoiceFlag=="I"?"Invoice Generated.Do you really want to modify the broker details?":"Trail Run Completed.Do you really want to modify the broker details?",
//         type: "warning",
//         showCancelButton: true,
//         cancelButtonClass: "btn-cancel",
//         confirmButtonClass: "btn-primary",
//         confirmButtonText: "Yes",
//         closeOnConfirm: true
//        },function() {



           Swal.fire({
            title: "Modify Broker Details?",

           text: invoiceFlag=="I"?"Invoice Generated.Do you really want to modify the broker details?":"Trail Run Completed.Do you really want to modify the broker details?",
             icon: "warning", // use "icon", not "type"
             showCancelButton: true,
             confirmButtonText: "Yes",
             cancelButtonText: "Cancel",
             customClass: {
               confirmButton: "btn-primary",
               cancelButton: "btn-cancel"
             },
             buttonsStyling: false
           }).then((result) => {

                  if (result.isConfirmed) {
              vm.loader = true;
              var requestData = {};
              modeOfHoldingService.getBrokerTorch(requestData).then(
                function (response) {
                  vm.loader = false;
                  invoiceFlag = null;
                  vm.brokerDetailsList = response.data.result.torchValue;
                  angular.element("#brokerDetailsModal").modal("toggle");
                },
                function (error) {
                  vm.loader = false;
                  handleErrorResponse(error);
                }
              );
     }   })
      } else {
        getBrokerTorch();
      }
    }

    vm.mofDateOptions = {
      showWeeks: false,
      maxDate: vm.systemDate,
    };

    // Clear not required fields
    function updateTransactionMode() {
      if (vm.accountOpening.tranMode == "C") {
        vm.accountOpening.acctName = "";
        vm.accountOpening.interestPaymentMode = "";
        vm.accountOpening.interestCreditAccountNumber = "";
        vm.accountOpening.zoneBankCode = "";
        vm.accountOpening.zoneBranchCode = "";
        vm.accountOpening.codeOfCreditBank = "";
        vm.accountOpening.payeeName = "";
        vm.accountOpening.operativeAccNo = "";
        vm.accountOpening.instType = "";
        vm.accountOpening.instAlpha = "";
        vm.accountOpening.instNumber = "";
        vm.accountOpening.instDate = "";
      } else if (vm.accountOpening.tranMode == "T") {
        vm.accountOpening.tranCode = "";
        vm.accountOpening.zoneCode = "";
        vm.accountOpening.zoneDate = "";
        vm.accountOpening.bankCode = "";
        vm.accountOpening.branchCode = "";
        vm.accountOpening.sortCode = "";
        vm.accountOpening.interestPaymentMode = "";
        vm.accountOpening.interestCreditAccountNumber = "";
        vm.accountOpening.zoneBankCode = "";
        vm.accountOpening.zoneBranchCode = "";
        vm.accountOpening.codeOfCreditBank = "";
        vm.accountOpening.payeeName = "";
        vm.accountOpening.operativeAccNo = "";
        vm.accountOpening.instType = "";
        vm.accountOpening.instAlpha = "";
        vm.accountOpening.instNumber = "";
        vm.accountOpening.instDate = "";
      } else if (vm.accountOpening.tranMode == "CL") {
        vm.accountOpening.solId = "";
        vm.accountOpening.acctName = "";
        vm.accountOpening.instType = "";
        vm.accountOpening.instAlpha = "";
        vm.accountOpening.instNumber = "";
        vm.accountOpening.instDate = "";
        vm.accountOpening.interestPaymentMode = "";
        vm.accountOpening.interestCreditAccountNumber = "";
        vm.accountOpening.zoneBankCode = "";
        vm.accountOpening.zoneBranchCode = "";
        vm.accountOpening.codeOfCreditBank = "";
        vm.accountOpening.payeeName = "";
        vm.accountOpening.operativeAccNo = "";
      }
    }

    //Get Customer ID
    function getCustomerId() {
      vm.loader = true;
      var requestData = {};
      modeOfHoldingService.getValidationAdd(requestData).then(
        function (response) {
          vm.loader = false;
          angular.element("#customerIdModal").modal("toggle");
          vm.customerIdList = response.data.result.customerIdOut;
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    //Get validate Add Select
    function validateAddSelect() {
      var requestData = {
        scssCustomerId: vm.accountOpening.customerId,
      };
      modeOfHoldingService.getValidationAdd(requestData).then(
        function (response) {
          vm.customerMasterList = response.data.result.customerMasterOut;
          vm.totalAmount = response.data.result.totalAmount;
          angular.forEach(vm.customerMasterList, function (item) {
            if (item.accountHolderType == "MH001") {
              vm.accountOpening.customerId = item.scssCustomerId;
              vm.accountOpening.accountHolderType = item.accountHolderType;
              vm.customerName = item.name;
              vm.accountOpening.holderNum = item.accountHolderNumber;
            }

            if (item.accountHolderType == "MH002") {
              if (item.accountHolderNumber == "1") {
                vm.accountOpening.holderNum = item.accountHolderNumber;
                vm.accountOpening.customerId = item.scssCustomerId;
                vm.accountOpening.accountHolderType = item.accountHolderType;
                vm.primaryCustomerName = item.name;
              } else if (item.accountHolderNumber == "2") {
                vm.secondaryHolderName = item.name;
                vm.accountOpening.accountHolderType = item.accountHolderType;
                //vm.accountOpening.holderNum=item.accountHolderNumber;
              }
            }
          });
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }

    //Get Transaction Modes
    function getTransactionModes() {
      vm.referenceId = "TRANSACTION_TYPE";
      var requestData = {
        referenceId: "RBI_TRAN_MODES",
      };
      modeOfHoldingService.getReferenceData(requestData).then(
        function (response) {
          vm.transactionModeList = response.data.result;
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }

    function validateInstNum(instNum) {
      if (vm.transactionMode == "CL") {
        vm.insErrorMessage = "";
      }

      if (instNum) {
        if (vm.transactionMode == "T") {
          //                            			console.log(vm.instrumentNo);
          //                            					if(vm.instrumentNo.length >= 6){
          //                            					vm.insErrorMessage = false;
          //                            					} else {
          //                                                    var zeros = "0".repeat(6);
          //                                                    vm.instrumentNo = (zeros + vm.model.instNumber).slice(-1 * 6)
          //                            					}
          commonService.validateInstNum(instNum, vm.debitAccNo, function (response) {
            if (response.success) {
              if (response.data.instrumentStatus != "S") {
                vm.instrumentNo = "";
                vm.insErrorMessage = "Enter valid instrument Number";
              } else {
                vm.insErrorMessage = false;
              }
            } else {
              $scope.$emit("showAlert", {
                type: "false",
                msg: response.message,
              });
            }
          });
        }
      }
    }

    //Get Transaction Modes
    function getModeOfInterestPayment() {
      var requestData = {
        referenceId: "MODE_OF_PAYMENT",
      };
      modeOfHoldingService.getReferenceData(requestData).then(
        function (response) {
          vm.modeOfInterestPaymentList = response.data.result;
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }

    function getAccountType() {
      var requestData = {
        referenceId: "ACC_TYP_CD",
      };
      modeOfHoldingService.getReferenceData(requestData).then(
        function (response) {
          vm.accountTypeList = response.data.result;
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }

    function getSegment() {
      var requestData = {
        referenceId: "SEGMENT",
      };
      modeOfHoldingService.getReferenceData(requestData).then(
        function (response) {
          vm.segmentList = response.data.result;
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }
    function getInterestPaymentMode() {
      var requestData = {
        referenceId: "INT_PAY_MODE",
      };
      modeOfHoldingService.getReferenceData(requestData).then(
        function (response) {
          vm.interestPaymentModeList = response.data.result;
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }

    function getRelationWithHolder() {
      var requestData = {
        referenceId: "RELATIONSHIP",
      };
      modeOfHoldingService.getReferenceData(requestData).then(
        function (response) {
          vm.relationHolderList = response.data.result;
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }

    function getStateList() {
      var requestData = {
        referenceId: "STATES OF INDIA",
      };
      modeOfHoldingService.getReferenceData(requestData).then(
        function (response) {
          vm.stateList = response.data.result;
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }

    function instrumentTypeForClearing() {
      var requestData = {
        referenceId: "INSTRUMENT_TYPE",
      };
      modeOfHoldingService.getReferenceData(requestData).then(
        function (response) {
          vm.instrumentTypeClearingList = response.data.result;
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }

    /* Get Sol Details */
    function getSolDetails(solId, scopeVariable) {
      solId = "";
      vm.tempSolIDScopeVariable = scopeVariable;
      vm.searchSolId = "";
      vm.loader = true;
      commonService.getSolAllDetails(solId, function (response) {
        if (response.success) {
          angular.element("#solIDModal").modal("toggle");
          vm.solDetails = response.data;
          vm.loader = false;
        } else {
          Swal.fire("Error!", response.message, "error");
          vm.loader = false;
        }
      });
    }

    function getTranCode(tranCode) {
      if (!tranCode) {
        tranCode = "";
      }
      vm.loader = true;
      commonService.getTranCode(tranCode, function (response) {
        if (response.success) {
          angular.element("#tranCodeModal").modal("toggle");
          vm.tranCodeList = response.data;
          vm.loader = false;
        } else {
          Swal.fire("Error!", response.message, "error");
          vm.loader = false;
        }
      });
    }

    function getZoneCode(solId, zoneCode) {
      if (!zoneCode) {
        zoneCode = "";
      }
      vm.loader = true;
      commonService.getZoneCode(solId, zoneCode, function (response) {
        if (response.success) {
          angular.element("#zoneModal").modal("toggle");
          vm.loader = false;
          vm.zoneCodeList = response.data;
          /* vm.zoneCodeList.forEach((item) => {
                           			         item.clgZoneDate =
                                           });*/
        } else {
          Swal.fire("Error!", response.message, "error");
        }
      });
    }

    function getBankCode(bankCode) {
      if (!bankCode) {
        bankCode = "";
      }
      vm.loader = true;
      commonService.getBankCode(bankCode, function (response) {
        if (response.success) {
          angular.element("#bankCodeModal").modal("toggle");
          vm.bankCodeList = response.data;
          vm.loader = false;
        } else {
          Swal.fire("Error!", response.message, "error");
          vm.loader = false;
        }
      });
    }

    function getBranchCode(bankCode, branchCode) {
      if (!bankCode) {
        Swal.fire("Please select Bank Code");
      } else {
        if (!branchCode) {
          branchCode = "";
        }
        vm.loader = true;
        commonService.getBranchCode(bankCode, branchCode, function (response) {
          if (response.success) {
            angular.element("#branchCodeModal").modal("toggle");
            vm.branchCodeList = response.data;
            vm.loader = false;
          } else {
            Swal.fire("Error!", response.message, "error");
            vm.loader = false;
          }
        });
      }
    }

    function getZoneBankCode(bankCode) {
      if (!bankCode) {
        bankCode = "";
      }
      vm.loader = true;
      commonService.getBankCode(bankCode, function (response) {
        if (response.success) {
          angular.element("#zoneBankCodeModal").modal("toggle");
          vm.bankCodeList = response.data;
          vm.loader = false;
        } else {
          Swal.fire("Error!", response.message, "error");
          vm.loader = false;
        }
      });
    }

    function getZoneBranchCode(bankCode, branchCode) {
      if (!bankCode) {
        Swal.fire("Please select Bank Code");
      } else {
        if (!branchCode) {
          branchCode = "";
        }
        vm.loader = true;
        commonService.getBranchCode(bankCode, branchCode, function (response) {
          if (response.success) {
            angular.element("#zoneBranchCodeModal").modal("toggle");
            vm.branchCodeList = response.data;
            vm.loader = false;
          } else {
            Swal.fire("Error!", response.message, "error");
            vm.loader = false;
          }
        });
      }
    }

    function getTranCodeDetails() {
      vm.tranCodeList = [];
      vm.loader = true;
      commonService.getTranCode("", function (response) {
        //                           				vm.tranCodeList = response.data;
        vm.tranCodeList = [
          {
            clgTranCode: "10",
            clgTranCodeDesc: "Savings",
          },
          {
            clgTranCode: "11",
            clgTranCodeDesc: "Current",
          },
          {
            clgTranCode: "13",
            clgTranCodeDesc: "Cash Credit",
          },
        ];
        vm.loader = false;
        angular.element("#tranCodeModal").modal("toggle");
      });
    }

    function getBankCodeDetails() {
      vm.bankCodeList = [];
      commonService.getBankCode("", function (response) {
        vm.bankCodeList = response.data;
      });
    }

    function getZoneCodeDetails() {
      vm.zoneCodeList = [];
      commonService.getZoneCode(vm.loginSolId, "", function (response) {
        vm.zoneCodeList = response.data;
      });
    }

    function getInstrumentType() {
      if (vm.accountOpening.acctNum != "" && vm.accountOpening.interSolId != "") {
        vm.instrumentTypeList = "";

        commonService.getInstrumentType(vm.accountOpening.acctNum, vm.accountOpening.interSolId, function (response) {
          if (response.success) {
            vm.instrumentTypeList = response.data;
          } else {
            Swal.fire("Error!", response.message, "error");
          }
        });
      }
    }

    vm.nominationDetails = [{}];

    // Remove Nominee
    vm.removeNominee = removeNominee;

    function removeNominee(index) {
      if (vm.nominationDetails.length > 1) {
        vm.nominationDetails.splice(index, 1);
      } else {
        Swal.fire("You will not allowed to delete first nominee");
        return false;
      }
    }

    vm.dateToOptionsInstrumentDate = {
      //              minDate: new Date(),
      //              minDate: $filter('date')($rootScope.globals.currentUser.systemDate, 'dd/MM/yyyy'),
      //              minDate: $rootScope.globals.currentUser.systemDate,
      maxDate: calculatePriorDate(3),
    };

    function calculatePriorDate(priorMonths) {
      const date = new Date();

      // accessing current month of the date
      const currentMonth = date.getMonth();

      // subtracting required number of months
      date.setMonth(currentMonth - priorMonths);

      return date;
    }

    function calculateAge(birthday) {
      // birthday is a date
      var ageDifMs = Date.now() - birthday.getTime();
      var ageDate = new Date(ageDifMs); // miliseconds from epoch
      var age = Math.abs(ageDate.getUTCFullYear() - 1970);

      if (age >= 18) {
        var msg = "Not a minor";
        Swal.fire("Warning!", msg, "warning");
        getAccountPage();
      }
    }

    function dateValidation() {
      vm.dateOptions = {
        maxDate: vm.systemDate,
      };
    }

    function dateOfBirthOptionsFunc() {
      vm.curtYear = vm.currentDate.getUTCFullYear();
      vm.minimumYear = vm.curtYear - 18;
      vm.minimumDate = vm.currentDate.getMonth() + 1 + "/" + vm.currentDate.getDate() + "/" + vm.minimumYear;
      vm.minimumDate = new Date(vm.minimumDate);
      vm.dateOfBirthOptions = {
        minDate: vm.minimumDate,
        maxDate: vm.systemDate,
      };
    }

    function dateOfBirthOptionsFuncMajor() {
      vm.curtYear = vm.currentDate.getUTCFullYear();

      vm.minimumYear = vm.curtYear - 18;

      vm.minimumDate = new Date(vm.currentDate);
      vm.minimumDate.setFullYear(vm.minimumYear);

      vm.dateOfBirthOptions = {
        minDate: null,
        maxDate: vm.minimumDate,
      };
    }

    function instrumentDateValidation() {
      commonService.getSolDate().then(
        function (response) {
          vm.solDate = response.data.solDate;
          vm.effFromDateOptions = {
            minDate: moment(vm.solDate).subtract(3, "M").toDate(),
            maxDate: vm.solDate,
          };
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }
    function instrumentDateValidation() {
      commonService.getSolDate().then(
        function (response) {
          vm.solDate = response.data.solDate;
          vm.effFromDateOptions = {
            minDate: moment(vm.solDate).subtract(3, "M").toDate(),
            maxDate: vm.solDate,
          };
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }

    function addRange() {
      vm.nominationDetails.push({});
    }

    // Remove Nominee
    function removeNominee(index) {
      if (vm.nominationDetails.length > 1) {
        vm.nominationDetails.splice(index, 1);
      } else {
        Swal.fire("You will not allowed to delete first nominee");
        return false;
      }
    }

    function getSubCodeList() {
      //            modeOfHoldingService.getSubCodeList().then(function(response){
      //                vm.subCodeList= response.data.result;
      //            },function(error){
      //                handleErrorResponse(error);
      //            })
    }

    function getDDPOAccountNumberForModify() {
      var requestData = {
        scssCustomerId: vm.accountOpening.customerId,
        transactionType: vm.accountOpening.tranMode,
      };
      modeOfHoldingService.getValidationAdd(requestData).then(
        function (response) {
          vm.accountNumberDetails = response.data.result;

          if (vm.accountNumberDetails.creditAccountNumberFlag == "N") {
            modeOfHoldingService.getForacidAccountNumber(vm.accountNumberDetails.creditAccountNumber, vm.loginSolId).then(
              function (response) {
                vm.accountOpening.creditAccountNumber = response.data[0].forAccountId;
              },
              function (error) {
                vm.loader = false;
                handleErrorResponse(error);
              }
            );
          } else {
            vm.accountOpening.creditAccountNumber = vm.accountNumberDetails.creditAccountNumber;
          }
          if (vm.accountNumberDetails.ddPayAccountNumberFlag == "N") {
            modeOfHoldingService.getForacidAccountNumber(vm.accountNumberDetails.ddPayAccountNumber, vm.loginSolId).then(
              function (response) {
                vm.accountOpening.ddPoAccountNumber = response.data[0].forAccountId;
              },
              function (error) {
                vm.loader = false;
                handleErrorResponse(error);
              }
            );
          } else {
            vm.accountOpening.ddPoAccountNumber = vm.accountNumberDetails.ddPayAccountNumber;
          }
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    //Get depositAmountValidation
    function depositAmountValidation() {
      if (vm.accountOpening.depositAmount) {
        var requestData = {
          scssCustomerId: vm.accountOpening.customerId,
          depositAmount: vm.accountOpening.depositAmount,
        };
        modeOfHoldingService.getDepositAmountValidation(requestData).then(
          function (response) {
            vm.accountOpening.totalAmount = parseInt(response.data.result);
            vm.accountOpening.totalAmountInquiry = vm.accountOpening.totalAmount - vm.accountOpening.depositAmount;
            vm.errorMessage = false;
          },
          function (error) {
            if (error.data.message != "SUCCESS") {
              vm.errorMessage = error.data.message;
              vm.accountOpening.depositAmount = "";
              //vm.accountOpening.totalAmount = '';
            }
          }
        );
      }
    }

    //Operative Acct Num
    function getSchemeTypeListOperative(solId, acctNum, flag) {
      if (acctNum) {
        commonService.getSchemeTypeListAcc(
          acctNum,
          function (response) {
            if (response.success) {
              vm.accList = response.data;
              if (flag == "acctNum") {
                if (vm.accList.length > 0) {
                  vm.accountOpening.acctNum = vm.accList[0].forAccountId;
                  vm.accountOpening.interSolId = vm.accList[0].solId;
                  if (vm.accountOpening.acctNum && vm.accountOpening.interSolId) {
                    getInstrumentType();
                  }
                  vm.errorAccountNumber = false;
                } else {
                  vm.errorAccountNumber = true;
                  vm.accountOpening.acctNum = "";
                }
              } else if (flag == "creditAccountNumber") {
                if (vm.accList.length > 0) {
                  vm.accountOpening.interestCreditAccountNumber = vm.accList[0].forAccountId;
                  vm.errorCreditAccountNumber = false;
                } else {
                  vm.errorCreditAccountNumber = true;
                  vm.accountOpening.interestCreditAccountNumber = "";
                }
              }
            } else {
              Swal.fire("Error!", response.message, "error");
            }
          },
          function (err) {
            handleErrorResponse(err);
          }
        );
      }
    }

    function getSchemeTypeListOperativeNominee(solId, acctNum, flag, index) {
      vm.index = index;
      vm.errorNomineeOperativeAcctNo[vm.index] = "";
      commonService.getCustomAcctInqApi(
        acctNum,
        function (response) {
          if (response.success) {
            vm.accList = response.data;
            vm.loader = false;
            if (vm.accList.length > 0) {
              if (vm.accList[0].status == "S") {
                if (vm.accList[0].accountFreezeStatus) {
                  vm.errorNomineeOperativeAcctNo[vm.index] = "Account is Friezed";
                  vm.nominationDetails[vm.index].nomineeOperativeAcctNo = "";
                  vm.nominationDetails[vm.index].operativeAccBranchId = "";
                } else {
                  vm.nominationDetails[vm.index].operativeAccBranchId = vm.accList[0].solID;
                }
              }
              if (vm.accList[0].status == "F") {
                vm.errorNomineeOperativeAcctNo[vm.index] = vm.accList[0].errDesc;
                vm.nominationDetails[vm.index].nomineeOperativeAcctNo = "";
                vm.nominationDetails[vm.index].operativeAccBranchId = "";
              }
            } else {
              vm.errorNomineeOperativeAcctNo[vm.index] = "Invalid Account Number";
              vm.nominationDetails[vm.index].nomineeOperativeAcctNo = "";
              vm.nominationDetails[vm.index].operativeAccBranchId = "";
            }
          } else {
            Swal.fire("Error!", response.message, "error");
            vm.loader = false;
            vm.nominationDetails[vm.index].nomineeOperativeAcctNo = "";
            vm.nominationDetails[vm.index].operativeAccBranchId = "";
          }
        },
        function (err) {
          handleErrorResponse(err);
          vm.loader = false;
          vm.nominationDetails[vm.index].nomineeOperativeAcctNo = "";
          vm.nominationDetails[vm.index].operativeAccBranchId = "";
        }
      );
    }

    function getSchemeTypeListOperativeNomineeM(solId, acctNum, flag, index) {
      vm.index = index;
      if (acctNum) {
        commonService.getCustomAcctInqApi(
          acctNum,
          function (response) {
            if (response.success) {
              vm.accList = response.data;

              if (vm.accList.length > 0) {
                vm.errorNomineeOperativeAcctNo[vm.index] = false;
                vm.nominationDetails[vm.index].C014.oldValue = vm.accList[0].solID;
              } else {
                vm.errorNomineeOperativeAcctNo[vm.index] = true;
                for (var i = 0; i < vm.nominationDetails.length; i++) {
                  if (vm.index == i) {
                    vm.nominationDetails[vm.index].C013.oldValue = "";
                    break;
                  }
                }
              }
            } else {
              Swal.fire("Error!", response.message, "error");
            }
          },
          function (err) {
            handleErrorResponse(err);
            vm.loader = false;
          }
        );
      }
    }

    function validateSortNum() {
      if (vm.accountOpening.tranMode == "CL") {
        commonService.validatesortNum(vm.accountOpening.sortCode, function (response) {
          if (response.success) {
            vm.validateMsg = false;
          } else {
            vm.validateMsg = response.data.message;
            vm.accountOpening.sortCode = "";
          }
        });
      }
    }

    function calculateSum() {
      vm.total = 0;
      for (var i = 0; i < vm.nominationDetails.length; i++) {
        vm.total = vm.total + parseFloat(vm.nominationDetails[i].sharePercentage ? vm.nominationDetails[i].sharePercentage : "0");
      }
    }

    function calculateSumM() {
      //                                   vm.total = 0;
      for (var i = 0; i < vm.nominationDetails.length; i++) {
        vm.total = 100;
      }
    }

    function validateNominee() {
      if (vm.modeOfHolding === "MH004") {
        vm.isNominee = "N";
      } else {
        vm.nominationDetails = [{}];
      }
    }

    function validateSubscriptionAmountM() {
      if (vm.getDetails.C014.oldValue == vm.getDetails.C037.oldValue) {
      } else {
        vm.getDetails.C037.oldValue = "";
        Swal.fire("Warning!", "Deposit Amount and Instrument Amount Differs", "warning");
      }
    }

    function validateSubscriptionAmount() {
      if (vm.instrumentAmount == vm.subscriptionAmount) {
      } else {
        vm.instrumentAmount = "";
        Swal.fire("Warning!", "Deposit Amount and Instrument Amount Differs", "warning");
      }
    }
       function getInquiryRoles() {
            vm.loader = true;
            let requestData = {
                schemeCode: vm.schemeCode
            }
            modeOfHoldingService.getInquiryRoles(requestData)
                .then(
                    function(response) {
                        vm.mapUnmMppedDocuments = response.data.result;
                        vm.loader = false;
                    },function (error) {
                        vm.loader = false;
                        handleErrorResponse(error);
                      }
                );
}
    function submitAddForm() {
      var proceed = true;
      var message = "";
      if (proceed) {
               Swal.fire({
                 title: "Are you sure?",
                 text: "Do you want to Really Add this record?",
                 icon: "warning", // use "icon", not "type"
                 showCancelButton: true,
                 confirmButtonText: "Yes",
                 cancelButtonText: "Cancel",
                 customClass: {
                   confirmButton: "btn-primary",
                   cancelButton: "btn-cancel"
                 },
                 buttonsStyling: false
               }).then((result) => {

                      if (result.isConfirmed) {
            vm.loader = true;
             vm.resultArray = [];
                        vm.resultArray2 = [];

                        vm.customerMasterDetails.bondHldDob = $filter("date")(vm.customerMasterDetails.bondHldDob, "dd-MMM-yyyy");
                        vm.customerMasterDetails.guardianDateOfBirth = $filter("date")(vm.customerMasterDetails.guardianDateOfBirth, "dd-MMM-yyyy");

                        try {
                          if (vm.customerMasterDetails) {
                            vm.customerMasterDetails.birthCertificate = vm.customerMasterDetails.birthCertificate ? "Y" : "N";
                            vm.customerMasterDetails.passport = vm.customerMasterDetails.passport ? "Y" : "N";
                            vm.customerMasterDetails.seniorCitizenCard = vm.customerMasterDetails.seniorCitizenCard ? "Y" : "N";
                            vm.customerMasterDetails.voterIdCard = vm.customerMasterDetails.voterIdCard ? "Y" : "N";
                            vm.customerMasterDetails.panCard = vm.customerMasterDetails.panCard ? "Y" : "N";
                            vm.customerMasterDetails.drivingLicence = vm.customerMasterDetails.drivingLicence ? "Y" : "N";
                            vm.customerMasterDetails.rationCard = vm.customerMasterDetails.rationCard ? "Y" : "N";
                            vm.customerMasterDetails.otherDocuments = vm.customerMasterDetails.otherDocuments ? "Y" : "N";
                          }
                        } catch (e) {}

                        try {
                          if (vm.customerMasterDetailsTwo) {
                            vm.customerMasterDetailsTwo.birthCertificate = vm.customerMasterDetailsTwo.birthCertificate ? "Y" : "N";
                            vm.customerMasterDetailsTwo.passport = vm.customerMasterDetailsTwo.passport ? "Y" : "N";
                            vm.customerMasterDetailsTwo.seniorCitizenCard = vm.customerMasterDetailsTwo.seniorCitizenCard ? "Y" : "N";
                            vm.customerMasterDetailsTwo.voterIdCard = vm.customerMasterDetailsTwo.voterIdCard ? "Y" : "N";
                            vm.customerMasterDetailsTwo.panCard = vm.customerMasterDetailsTwo.panCard ? "Y" : "N";
                            vm.customerMasterDetailsTwo.drivingLicence = vm.customerMasterDetailsTwo.drivingLicence ? "Y" : "N";
                            vm.customerMasterDetailsTwo.rationCard = vm.customerMasterDetailsTwo.rationCard ? "Y" : "N";
                            vm.customerMasterDetailsTwo.otherDocuments = vm.customerMasterDetailsTwo.otherDocuments ? "Y" : "N";
                          }
                        } catch (e) {}

                        try {
                          if (vm.customerMasterDetailsThree) {
                            vm.customerMasterDetailsThree.birthCertificate = vm.customerMasterDetailsThree.birthCertificate ? "Y" : "N";
                            vm.customerMasterDetailsThree.passport = vm.customerMasterDetailsThree.passport ? "Y" : "N";
                            vm.customerMasterDetailsThree.seniorCitizenCard = vm.customerMasterDetailsThree.seniorCitizenCard ? "Y" : "N";
                            vm.customerMasterDetailsThree.voterIdCard = vm.customerMasterDetailsThree.voterIdCard ? "Y" : "N";
                            vm.customerMasterDetailsThree.panCard = vm.customerMasterDetailsThree.panCard ? "Y" : "N";
                            vm.customerMasterDetailsThree.drivingLicence = vm.customerMasterDetailsThree.drivingLicence ? "Y" : "N";
                            vm.customerMasterDetailsThree.rationCard = vm.customerMasterDetailsThree.rationCard ? "Y" : "N";
                            vm.customerMasterDetailsThree.otherDocuments = vm.customerMasterDetailsThree.otherDocuments ? "Y" : "N";
                          }
                        } catch (e) {}

                        try {
                          vm.customerMasterDetailsTwo.bondHldDob = $filter("date")(vm.customerMasterDetailsTwo.bondHldDob, "dd-MMM-yyyy");
                          vm.customerMasterDetailsTwo.isMinor = vm.customerMasterDetailsTwoFlag;
                        } catch (err) {}
                        try {
                          vm.customerMasterDetailsTwo.dateSubmission15g15htax = $filter("date")(vm.customerMasterDetailsTwo.dateSubmission15g15htax, "dd-MMM-yyyy");
                        } catch (err) {}
                        try {
                          vm.customerMasterDetailsTwo.guardianDateOfBirth = $filter("date")(vm.customerMasterDetailsTwo.guardianDateOfBirth, "dd-MMM-yyyy");
                        } catch (err) {}

                        try {
                          vm.customerMasterDetailsThree.bondHldDob = $filter("date")(vm.customerMasterDetailsThree.bondHldDob, "dd-MMM-yyyy");
                          vm.customerMasterDetailsThree.isMinor = vm.customerMasterDetailsThreeFlag;
                        } catch (err) {}
                        try {
                          vm.customerMasterDetailsThree.dateSubmission15g15htax = $filter("date")(vm.customerMasterDetailsThree.dateSubmission15g15htax, "dd-MMM-yyyy");
                        } catch (err) {}
                        try {
                          vm.customerMasterDetailsThree.guardianDateOfBirth = $filter("date")(vm.customerMasterDetailsThree.guardianDateOfBirth, "dd-MMM-yyyy");
                        } catch (err) {}

                        var objOne = Object.fromEntries(Object.entries(vm.customerMasterDetails));
                        objOne.jointHolderType = "S";
                        if (vm.customerMasterDetailsTwo) {
                          var objTwo = Object.fromEntries(Object.entries(vm.customerMasterDetailsTwo));
                          objOne.jointHolderType = "1";
                          objTwo.jointHolderType = "2";
                          vm.resultArray.push(objTwo);
                        }
                        if (vm.customerMasterDetailsThree) {
                          var objThree = Object.fromEntries(Object.entries(vm.customerMasterDetailsThree));
                          objThree.jointHolderType = "3";
                          vm.resultArray.push(objThree);
                        }
                        angular.forEach(vm.coparcenerslist, function (item) {
                          // Remove $$hashKey from each item
                          delete item.$$hashKey;
                          // Check if coparcenersDob exists and if it's valid before applying the date filter
                          if (item.coparcenersDob) {
                            item.coparcenersDob = $filter("date")(item.coparcenersDob, "dd-MMM-yyyy");
                          }

                          // Push the item to resultArray2
                          vm.resultArray2.push(item);
                        });
                        vm.resultArray.push(objOne);


            if(vm.customerMasterDetails.bondHldDob){
            vm.customerMasterDetails.bondHldDob=$filter("date")(vm.customerMasterDetails.bondHldDob, "dd-MMM-yyyy");
            }
            if(vm.blaFlag==='Y'){
            var requestData = {
              fromBlaNo: vm.blaAccNo,
              toBlaNo: vm.blaAccNoTo,
              panNum: vm.panNo,
              reversalRemarks: vm.reversalRemarks,
              schemeCode: vm.schemeCode,
              modeOfHolding: vm.modeOfHolding,
              applicationNo: vm.applicationNo,
              brokerAgencyCode: vm.brokerId,
              subscriptionAmount: vm.subscriptionAmount,
              blaType: vm.blaFlag,
              periodOfDeposit: vm.periodOfDeposit,
              rateOfInterest: vm.rateOfInterest,
              modeOfInterestPayment: vm.modeOfInterestPayment,
              tranMode: vm.transactionMode,
              instrumentAmount: vm.instrumentAmount,
              remarks: vm.remarks,
              isNominee: vm.isNominee,
              totalSharingPercent: vm.total,
              dataList:vm.resultArray
              //isNri: vm.bondHolderDetails[0].isNri,
            };}
            if(vm.blaFlag==='N'){
            var requestData = {
              fromBlaNo: vm.blaAccNo,
              toBlaNo: vm.blaAccNoTo,
              panNum: vm.panNo,
              reversalRemarks: vm.reversalRemarks,
              schemeCode: vm.schemeCode,
              modeOfHolding: vm.modeOfHolding,
              applicationNo: vm.applicationNo,
              brokerAgencyCode: vm.brokerId,
              subscriptionAmount: vm.subscriptionAmount,
              blaType: vm.blaFlag,
              dataList:vm.resultArray
            };}
            if (vm.modeOfHolding == "MH001") {
              requestData.singleBondAccNo = vm.bondHolderDetailSingleBlaNo;
              requestData.bondHolderName = vm.bondHolderDetailSingleBondHolderName;
            }
            if (vm.modeOfInterestPayment == "MP001") {
              requestData.operativeAccNo = vm.operativeAccNum;
              requestData.operativeAccBranchId = vm.operativeAccBranchId;
            }
            var cbsRequestData = {
              creditAccountNumber: requestData.creditAccNo,
              depositAmount: requestData.subscriptionAmount,
              transactionType: requestData.tranMode,
              debitAccountNumber: requestData.debitAccNo,
            };
            modeOfHoldingService.addSubmit(requestData).then(function(response){
                vm.loader = false;
                vm.responseResult = response.data.result;
                if(vm.blaFlag==='N'){
                if(null!=vm.responseResult.firstGuardianId&&null!=vm.responseResult.blaNo){
                var msgForAdd ="Reversal Transaction REV1 Added Successfully \n Guardian Id : " +
                  vm.responseResult.firstGuardianId +
                  "\nFrom BLANo: " + vm.blaAccNo +
                  "\nTo BLANo: " + vm.responseResult.blaNo +
                  "\nSubscription Id: " + vm.responseResult.subscriptionId;
                Swal.fire("Added!", msgForAdd, "success");
                }else{
                var msgForAdd = "Reversal Transaction REV1 Added Successfully"
                    + "\nFrom BLANo: " + vm.blaAccNo
                    + "\nTo BLANo: " + vm.responseResult.blaNo
                    + "\nSubscription Id: " + vm.responseResult.subscriptionId;
                Swal.fire("Added!", msgForAdd, "success");
                }
                }
                if(vm.blaFlag==='Y'){
                var msgForAdd = "Reversal Transaction Added Successfully\nFrom BlaNo: " + vm.blaAccNo + "\nTo BlaNo: " + vm.blaAccNoTo + "\nSubscription Id: " + vm.responseResult.subscriptionId;
                Swal.fire("Added!", msgForAdd, "success");
                }
                location.href = "#!/mode_of_holding_change/action/inquiry";
              },
              function (error) {
                vm.loader = false;
                if(error.message=="From BLA not found. Please select a valid BLA in Torch."){
                Swal.fire("Warning!", error.message, "warning");
                $state.go("mode_of_holding_change.add_detail");
                }else if(error.message=="To BLA not found. Please select a valid BLA in Torch."){
                Swal.fire("Warning!", error.message, "warning");
                $state.go("mode_of_holding_change.add_detail");
                }else{
                handleErrorResponse(error);
                $state.go("mode_of_holding_change.add_input");
                }
              }
            );
        }  }
        );
      } else {
        Swal.fire("Warning!", message, "warning");
      }
    }

    function getCbsDetails() {
      var requestData = {
        bondCashAccount: vm.bondCashAccount,
        bondClearingAccount: vm.bondClearingAccount,
        bondCollectionAccount: vm.bondCollectionAccount,
        ddPoAccountNo: vm.ddPoAccountNo,
        neftPlaceHolderAccount: vm.creditAccNo,
        neftPlaceholderFlgOut: vm.neftPlaceholderFlgOut,
        ddPoAccFlgOut: vm.ddPoAccFlgOut,
        bondCashAccFlgOut: vm.bondCashAccFlgOut,
        bondClearingAccFlgOut: vm.bondClearingAccFlgOut,
        bondCollectionAccFlgOut: vm.bondCollectionAccFlgOut,
        tranMode: vm.transactionMode,
      };
      modeOfHoldingService.getCbsDetails(requestData).then(
        function (response) {
          vm.message = response.data.message;
          if (vm.message == "SUCCESS") {
            vm.cbsStatus = response.data.result.status;
            vm.cbsDetails = response.data.result.tranDetailsDTOList;
            if (vm.cbsStatus == "S") {
              angular.forEach(vm.cbsDetails, function (item) {
                if (item.accountNumber == vm.accountOpening.creditAccountNumber) {
                  vm.creditAccountNumberType = item.isSysAcct;
                } else if (item.accountNumber == vm.accountOpening.acctNum) {
                  vm.debitAccountNumberType = item.isSysAcct;
                }
              });
            }
            if (vm.accountOpening.creditAccountNumber == vm.accountOpening.acctNum) {
              vm.debitAccountNumberType = vm.creditAccountNumberType;
            }

            requestData.creditAccountNumberType = vm.creditAccountNumberType; //'N';
            requestData.debitAccountNumberType = vm.debitAccountNumberType; //'N';
          }
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function decimalConvert(value, index, event) {
      let decimal = parseFloat(value);

      vm.index = index;
      if (decimal == undefined || decimal == null || decimal == NaN) {
        if (event.target.value == "") {
          vm.errorMsg[vm.index] = false;
        } else if (event.target.id == vm.index) {
          if (event.target.value <= 100) {
            vm.errorMsg[vm.index] = false;
          } else {
            vm.errorMsg[vm.index] = true;

            for (var i = 0; i < vm.nominationDetails.length; i++) {
              if (vm.index == i) {
                vm.nominationDetails[vm.index].sharePercentage = "";
                break;
              }
            }
          }
        }
      } else {
        if (decimal <= 100) {
          vm.errorMsg[vm.index] = false;
        } else {
          vm.errorMsg[vm.index] = true;
          for (var i = 0; i < vm.nominationDetails.length; i++) {
            if (vm.index == i) {
              vm.nominationDetails[vm.index].sharePercentage = "";
              break;
            }
          }
        }
      }
    }

    //Get Customer ID for verify
    function getCustomerIdForVerify() {
      vm.loader = true;
      var requestData = {};
      modeOfHoldingService.getVerifySelect(requestData).then(
        function (response) {
          vm.loader = false;
          angular.element("#customerIdModalVerify").modal("toggle");
          vm.customerIdVerifyList = response.data.result.customerIdOut;
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    //Get Account Number for verify
    function getAccountNumberForVerify() {
      vm.loader = true;
      var requestData = {
        scssCustomerId: vm.accountOpening.customerId,
      };
      modeOfHoldingService.getVerifySelect(requestData).then(
        function (response) {
          vm.loader = false;
          angular.element("#accountNumberModal").modal("toggle");
          vm.scssAccountNumberList = response.data.result.scssAccountNumberOut;
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    //Get Customer ID for Inquiry
    function getCustomerIdForInquiry() {
      vm.loader = true;
      var requestData = {};
      modeOfHoldingService.getInquiryDetails(requestData).then(
        function (response) {
          vm.loader = false;
          angular.element("#customerIdModalInquiry").modal("toggle");
          vm.customerIdInquiryList = response.data.result.customerIdOut;
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    //Get Account Number for Inquiry
    function getAccountNumberForInquiry() {
      vm.loader = true;
      var requestData = {
        scssCustomerId: vm.accountOpening.customerId,
      };
      modeOfHoldingService.getInquiryDetails(requestData).then(
        function (response) {
          vm.loader = false;
          angular.element("#accountNumberModal").modal("toggle");
          vm.scssAccountNumberList = response.data.result.scssAccountNumberOut;
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function getVerifyDeleteSelect() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        blaNo: vm.blaNo,
        subscriptionId: vm.subscriptionId,
      };
      modeOfHoldingService.getVerifySelect(requestData).then(
        function (response) {
          vm.loader = false;
          vm.auditInfo = response.data.result;
          vm.getSubscriptionDetails = vm.auditInfo.masterDetailsValueModel[0];
          vm.getNomineeDetails = vm.auditInfo.nomineeValueModel;
          vm.lastVerifiedBy = vm.auditInfo.lastVerifiedBy;
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
          $state.go("bla_acc_opening_cum_subscription.list");
        }
      );
    }

    function getInquirySelect() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        blaNo: vm.blaNo,
        subscriptionId: vm.subscriptionId,
      };
      modeOfHoldingService.getInquiryDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.auditInfo = response.data.result;
          vm.getSubscriptionDetails = vm.auditInfo.masterDetailsValueModel[0];
          vm.getNomineeDetails = vm.auditInfo.nomineeValueModel;
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
          $state.go("bla_acc_opening_cum_subscription.list");
        }
      );
    }

    /* Verify Submit */
    function submitVerifyForm() {
//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to Really Verify this record?",
//          type: "warning",
//          showCancelButton: true,
//          cancelButtonClass: "btn-cancel",
//          confirmButtonClass: "btn-primary",
//          confirmButtonText: "Yes",
//          closeOnConfirm: false,
//        },
//
//        function () {



               Swal.fire({
                 title: "Are you sure?",
                 text: "Do you want to Really Verify this record?",
                 icon: "warning", // use "icon", not "type"
                 showCancelButton: true,
                 confirmButtonText: "Yes",
                 cancelButtonText: "Cancel",
                 customClass: {
                   confirmButton: "btn-primary",
                   cancelButton: "btn-cancel"
                 },
                 buttonsStyling: false
               }).then((result) => {

                      if (result.isConfirmed) {
          vm.loader = true;
          let requestData = {
            schemeCode: vm.auditInfo.schemeCode,
            subscriptionId: vm.auditInfo.subscriptionId,
            lastVerifiedBy: vm.lastVerifiedBy,
          };

          modeOfHoldingService.submitVerifyForm(requestData).then(
            (response) => {
              vm.loader = false;
              vm.responseResult = response.data.result;
              if (vm.lastVerifiedBy) {
                vm.message = "BOND Deposit Account Verified Successfully";
              } else {
                if (vm.getSubscriptionDetails.C003) {
                  vm.message =
                    "BOND Deposit Account Verified Successfully \n" +
                    "Subscription ID: " +
                    vm.responseResult.subscriptionId +
                    "\nGBS Tran ID: " +
                    vm.responseResult.gbmTranId +
                    "\nCBS Tran ID: " +
                    vm.responseResult.cbsTranId +
                    "\nBLA No: " +
                    vm.getSubscriptionDetails.C003.oldValue;
                }
                if (vm.getSubscriptionDetails.C006) {
                  vm.message =
                    "BOND Deposit Account Verified Successfully \n" +
                    "Subscription ID: " +
                    vm.responseResult.subscriptionId +
                    "\nGBS Tran ID: " +
                    vm.responseResult.gbmTranId +
                    "\nCBS Tran ID: " +
                    vm.responseResult.cbsTranId +
                    "\nBLA No: " +
                    vm.getSubscriptionDetails.C006.oldValue;
                }
              }

              Swal.fire("Verified!", vm.message, "success");

              location.href = "#!/bla_acc_opening_cum_subscription/action/inquiry";
            },
            function (error) {
              vm.loader = false;
              handleErrorResponse(error);
              location.href = "#!/bla_acc_opening_cum_subscription/action/inquiry";
            }
          );
      }  },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    /* Delete Submit */
    function submitDeleteForm() {
//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to Really Delete this record?",
//          type: "warning",
//          showCancelButton: true,
//          cancelButtonClass: "btn-cancel",
//          confirmButtonClass: "btn-primary",
//          confirmButtonText: "Yes",
//          closeOnConfirm: false,
//        },
//
//        function () {

       Swal.fire({
         title: "Are you sure?",
         text: "Do you want to Really Delete this record?",
         icon: "warning", // use "icon", not "type"
         showCancelButton: true,
         confirmButtonText: "Yes",
         cancelButtonText: "Cancel",
         customClass: {
           confirmButton: "btn-primary",
           cancelButton: "btn-cancel"
         },
         buttonsStyling: false
       }).then((result) => {

              if (result.isConfirmed) {
          let requestData = {
            schemeCode: vm.auditInfo.schemeCode,
            subscriptionId: vm.auditInfo.subscriptionId,
          };

          modeOfHoldingService.submitDeleteForm(requestData).then(
            (response) => {
              vm.loader = false;
              Swal.fire("Deleted!", "BOND Deposit Account Deleted Successfully", "success");
              location.href = "#!/bla_acc_opening_cum_subscription/action/inquiry";
            },
            function (error) {
              vm.loader = false;
              handleErrorResponse(error);
            }
          );
      }  },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function getModifySelect() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        blaNo: vm.blaNo,
        subscriptionId: vm.subscriptionId,
      };
      modeOfHoldingService.getModifySelect(requestData).then(
        function (response) {
          result = response.data.result;
          vm.getDetails = result.masterDetailsValueModel[0];
          vm.nominationDetails = result.nomineeValueModel;

          vm.bondCashAccount = result.bondCashAccount;
          vm.bondClearingAccount = result.bondClearingAccount;
          vm.bondCollectionAccount = result.bondCollectionAccount;

          vm.ddPoAccountNo = result.ddPoAccountNo;
          vm.neftCreditAccNo = result.neftCreditAccNo;
          vm.depositMultiples = result.depositMultiples;

          vm.bondCashLimit = result.cashLimitForCash;
          vm.bondClearingTransferLimit = result.cashLimitForClearingTransfer;
          vm.depositMultiples = result.depositMultiples;
          vm.minCashLimit = result.minCashLimit;

          if (vm.getDetails.C036) {
            vm.getDetails.C036.oldValue = convertToDateObject(vm.getDetails.C036.oldValue);
          }
          if (vm.getDetails.C040) {
            vm.getDetails.C040.oldValue = convertToDateObject(vm.getDetails.C040.oldValue);
          }
          if (vm.getDetails.C037) {
            vm.getDetails.C037.oldValue = parseInt(vm.getDetails.C037.oldValue);
          }

          angular.forEach(vm.nominationDetails, function (item) {
            if (item.C017) {
              item.C017.oldValue = convertToDateObject(item.C017.oldValue);
            }
            if (item.C010) {
              item.C010.oldValue = parseInt(item.C010.oldValue);
            }
            if (item.C011) {
              item.C011.oldValue = parseInt(item.C011.oldValue);
            }
            if (item.C025) {
              item.C025.oldValue = parseInt(item.C025.oldValue);
            }
            if (item.C026) {
              item.C026.oldValue = parseInt(item.C026.oldValue);
            }
          });

          vm.loader = false;
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
          $state.go("bla_acc_opening_cum_subscription.modify");
        }
      );
    }

    const convertToColumnValuesArrayList = (objectToBeConvert) => {
      if (typeof objectToBeConvert === "object") {
        let resultArray = [];
        for (let key of Object.keys(objectToBeConvert)) {
          if (objectToBeConvert[key])
            resultArray.push({
              colName: key,
              value: objectToBeConvert[key].oldValue,
            });
        }

        return resultArray;
      } else {
        throw "Only objects can be converted";
      }
    };

    //Get Customer ID
    function getCustomerIdForModify() {
      vm.loader = true;
      var requestData = {};
      modeOfHoldingService.getModifySelect(requestData).then(
        function (response) {
          vm.loader = false;
          angular.element("#customerIdModal").modal("toggle");
          vm.customerIdList = response.data.result.customerIdOut;
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    //Get Account Number for Modify
    function getAccountNumberForModify() {
      vm.loader = true;
      var requestData = {
        scssCustomerId: vm.accountOpening.customerId,
      };
      modeOfHoldingService.getModifySelect(requestData).then(
        function (response) {
          vm.loader = false;
          angular.element("#accountNumberModal").modal("toggle");
          vm.scssAccountNumberList = response.data.result.scssAccountNumberOut;
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function submitModifyNominationForm() {
      if (vm.total != 100) {
        var msg = "Sum of sharing percentage should be equal to 100";
        Swal.fire("Warning!", msg, "warning");
      } else {
//        Swal.fire(
//          {
//            title: "Are you sure?",
//            text: "Do you want to Really Modify this record?",
//            type: "warning",
//            showCancelButton: true,
//            cancelButtonClass: "btn-cancel",
//            confirmButtonClass: "btn-primary",
//            confirmButtonText: "Yes",
//            closeOnConfirm: false,
//          },
//          function () {



                   Swal.fire({
                     title: "Are you sure?",
                     text: "Do you want to Really Modify this record?",
                     icon: "warning", // use "icon", not "type"
                     showCancelButton: true,
                     confirmButtonText: "Yes",
                     cancelButtonText: "Cancel",
                     customClass: {
                       confirmButton: "btn-primary",
                       cancelButton: "btn-cancel"
                     },
                     buttonsStyling: false
                   }).then((result) => {

                          if (result.isConfirmed) {
            vm.loader = true;
            var requestData = {};
            requestData.scssCustomerId = vm.accountOpening.customerId;
            requestData.scssAccountNumber = vm.scssAccountNumber;
            if (vm.accountOpening.tranMode != "T") {
              requestData.branchId = vm.loginSolId;
            } else {
              requestData.branchId = vm.accountOpening.interSolId;
            }
            requestData.depositAmount = vm.accountOpening.depositAmount;
            requestData.isNominee = vm.accountOpening.isNomineeRequired;
            requestData.transactionType = vm.accountOpening.tranMode;
            requestData.neftOperativeAccountNumber = vm.accountOpening.operativeAccNo;
            requestData.neftIfscCode = vm.accountOpening.codeOfCreditBank;
            requestData.neftNameOfThePayee = vm.accountOpening.payeeName;
            requestData.interestPaymentMode = vm.accountOpening.interestPaymentMode;
            if (vm.accountOpening.tranMode == "C" || vm.accountOpening.tranMode == "T") {
              requestData.micrCode = null;
            } else {
              requestData.micrCode = vm.accountOpening.sortCode;
            }
            vm.accountOpeningMaster.C002 = vm.accountOpening.holderNum;
            vm.accountOpeningMaster.C005 = vm.accountOpening.depositAmount;
            vm.accountOpeningMaster.C004 = vm.accountOpening.accountHolderType;

            vm.accountOpeningMaster.C010 = vm.accountOpening.C010;

            requestData.accountDetails = [convertToColumnValuesArray(vm.accountOpeningMaster)];
            angular.forEach(vm.nominationDetails, function (item) {
              item.C011 = parseInt(item.C011);
              item.C012 = parseInt(item.C012);
              item.C025 = parseInt(item.C025);
              item.C017 = $filter("date")(item.C017, "DD/MMM/YYYY");
              vm.nominationDetailsList.push(convertToColumnValuesArray(item));
            });
            requestData.nomineeDetails = vm.nominationDetailsList;

            requestData.creditAccountNumber = vm.accountOpening.creditAccountNumber;
            requestData.debitAccountNumber = vm.accountOpening.acctNum;
            if (vm.accountOpening.interestPaymentMode == "PM001") {
              requestData.creditAccountNumber = vm.accountOpening.interestCreditAccountNumber;
            }
            requestData.setNum = vm.setId;
            requestData.instrumentType = vm.accountOpening.instType;
            requestData.instrumentNumber = vm.accountOpening.instNumber;
            requestData.instrumentDate = $filter("date")(vm.accountOpening.instDate, "DD/MMM/YYYY");
            requestData.tranCode = vm.accountOpening.tranCode;
            requestData.zoneCode = vm.accountOpening.zoneCode;
            requestData.instrumentBankCode = vm.accountOpening.bankCode;
            requestData.instrumentBranchCode = vm.accountOpening.branchCode;
            requestData.zoneDate = $filter("date")(vm.accountOpening.zoneDate, "DD/MMM/YYYY");
            requestData.neftBankCode = vm.accountOpening.zoneBankCode;
            requestData.neftBranchCode = vm.accountOpening.zoneBranchCode;

            modeOfHoldingService.getCbsDetails(requestData).then(
              function (response) {
                vm.message = response.data.message;
                if (vm.message == "SUCCESS") {
                  vm.cbsStatus = response.data.result.status;
                  vm.cbsDetails = response.data.result.tranDetailsDTOList;
                  if (vm.cbsStatus == "S") {
                    angular.forEach(vm.cbsDetails, function (item) {
                      if (item.accountNumber == vm.accountOpening.creditAccountNumber) {
                        vm.creditAccountNumberType = item.isSysAcct;
                      } else if (item.accountNumber == vm.accountOpening.acctNum) {
                        vm.debitAccountNumberType = item.isSysAcct;
                      }
                    });
                  }
                  if (vm.accountOpening.creditAccountNumber == vm.accountOpening.acctNum) {
                    vm.debitAccountNumberType = vm.creditAccountNumberType;
                  }

                  requestData.creditAccountNumberType = vm.creditAccountNumberType; //'N';
                  requestData.debitAccountNumberType = vm.debitAccountNumberType; //'N';

                  modeOfHoldingService.getModifySubmit(requestData).then(
                    (response) => {
                      vm.loader = false;
                      Swal.fire("Modified!", "Details Modified Successfully", "success");
                      location.href = "#!/bla_acc_opening_cum_subscription/action/inquiry";
                    },
                    function (error) {
                      vm.loader = false;
                      handleErrorResponse(error);
                    }
                  );
                } else {
                  $timeout(Swal.fire("Warning", vm.message, "warning"), 30000);
                  $state.go("bla_acc_opening_cum_subscription.modify");
                  vm.loader = false;
                }
              },
              function (error) {
                vm.loader = false;
                handleErrorResponse(error);
              }
            );
         } }
        );
      }
    }

    function convertToColumnValuesArrayOldNew(objectToBeConvert) {
      if (typeof objectToBeConvert === "object") {
        let resultArray = [];
        for (let key of Object.keys(objectToBeConvert)) {
          if (objectToBeConvert[key])
            resultArray.push({
              colName: key,
              value: objectToBeConvert[key].oldValue,
            });
        }
        return resultArray;
      } else {
        throw "Only objects can be converted";
      }
    }

    function submitModify() {
      var proceed = false;
      var messageM = "";
      if (vm.getDetails.C045.oldValue == "Y") {
        if (vm.total != 100) {
          proceed = false;
          messageM = "Sum of sharing percentage should be equal to 100";
        } else {
          proceed = true;
        }
      } else {
        proceed = true;
      }

      if (proceed) {
//        Swal.fire(
//          {
//            title: "Are you sure?",
//            text: "Do you want to Really Modify this record?",
//            type: "warning",
//            showCancelButton: true,
//            cancelButtonClass: "btn-cancel",
//            confirmButtonClass: "btn-primary",
//            confirmButtonText: "Yes",
//            closeOnConfirm: false,
//          },
//          function () {
Swal.fire({
  title: "Are you sure?",
  text: "Do you want to Really Modify this record?",
  icon: "warning", // use "icon", not "type"
  showCancelButton: true,
  confirmButtonText: "Yes",
  cancelButtonText: "Cancel",
  customClass: {
    confirmButton: "btn-primary",
    cancelButton: "btn-cancel"
  },
  buttonsStyling: false
}).then((result) => {

       if (result.isConfirmed) {
            vm.loader = true;

            var requestData = {
              schemeCode: vm.schemeCode,
            };
            var subsList = [];

            if (vm.getDetails.C031.oldValue == "CL") {
              requestData.debitAccNo = vm.bondClearingAccount;
            }
            if (vm.getDetails.C031.oldValue == "C") {
              requestData.debitAccNo = vm.bondCashAccount;
            }
            if (vm.getDetails.C031.oldValue == "T") {
              requestData.debitAccNo = vm.getDetails.C032.oldValue;
            }

            if (vm.getDetails.C036) {
              vm.getDetails.C036.oldValue = $filter("date")(vm.getDetails.C036.oldValue, "dd-MMM-yyyy");
            }
            if (vm.getDetails.C017) {
              vm.getDetails.C017.oldValue = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.getDetails.C017.oldValue));
            }
            if (vm.getDetails.C020) {
              vm.getDetails.C020.oldValue = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.getDetails.C020.oldValue));
            }
            if (vm.getDetails.C018) {
              vm.getDetails.C018.oldValue = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.getDetails.C018.oldValue));
            }
            if (vm.getDetails.C040) {
              vm.getDetails.C040.oldValue = $filter("date")(vm.getDetails.C040.oldValue, "dd-MMM-yyyy");
            }

            angular.forEach(vm.nominationDetails, function (item) {
              if (item.C017) {
                item.C017.oldValue = $filter("date")(item.C017.oldValue, "dd-MMM-yyyy");
              }
            });

            vm.subscriptionModalListJSON = JSON.parse(JSON.stringify(vm.getDetails));
            subsList.push(convertToColumnValuesArrayOldNew(vm.subscriptionModalListJSON));

            requestData.tranDetails = subsList;

            if (vm.nominationDetails) {
              var nomList = [];
              vm.nominationModalListJSON = JSON.parse(JSON.stringify(vm.nominationDetails));
              nomList.push(convertToColumnValuesArrayOldNew(vm.nominationModalListJSON));
              requestData.nomineeDetails = nomList;
            }

            modeOfHoldingService.getModifySubmit(requestData).then(
              (response) => {
                vm.loader = false;
                var message = "";
                if (result.verifiedStatus == "N" && result.lastVerifiedBy) {
                  message = "Bond Subscription – Mode of Interest Payment/Principal Repayment details Modified Successfully";
                } else {
                  message = "Bond Subscription Workflow Modified Successfully";
                }

                Swal.fire("Modified!", message, "success");
                location.href = "#!/bla_acc_opening_cum_subscription/action/inquiry";
              },
              function (error) {
                vm.loader = false;
                handleErrorResponse(error);
              }
            );
        }  }
        );
      } else {
        Swal.fire("Warning!", messageM, "warning");
      }
    }

    function submitModifyInterestDetail() {
//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to Really Modify this record?",
//          type: "warning",
//          showCancelButton: true,
//          cancelButtonClass: "btn-cancel",
//          confirmButtonClass: "btn-primary",
//          confirmButtonText: "Yes",
//          closeOnConfirm: false,
//        },
//        function () {

            Swal.fire({
              title: "Are you sure?",
              text: "Do you want to Really Modify this record?",
              icon: "warning", // use "icon", not "type"
              showCancelButton: true,
              confirmButtonText: "Yes",
              cancelButtonText: "Cancel",
              customClass: {
                confirmButton: "btn-primary",
                cancelButton: "btn-cancel"
              },
              buttonsStyling: false
            }).then((result) => {

                   if (result.isConfirmed) {
          var requestData = {};
          requestData.scssCustomerId = vm.accountOpening.customerId;
          requestData.scssAccountNumber = vm.scssAccountNumber;
          if (vm.accountOpening.tranMode != "T") {
            requestData.branchId = vm.loginSolId;
          } else {
            requestData.branchId = vm.accountOpening.interSolId;
          }
          requestData.depositAmount = vm.accountOpening.depositAmount;
          requestData.isNominee = vm.accountOpening.isNomineeRequired;
          requestData.transactionType = vm.accountOpening.tranMode;
          requestData.neftOperativeAccountNumber = vm.accountOpening.operativeAccNo;
          requestData.neftIfscCode = vm.accountOpening.codeOfCreditBank;
          requestData.neftNameOfThePayee = vm.accountOpening.payeeName;
          requestData.interestPaymentMode = vm.accountOpening.interestPaymentMode;
          if (vm.accountOpening.tranMode == "C" || vm.accountOpening.tranMode == "T") {
            requestData.micrCode = null;
          } else {
            requestData.micrCode = vm.accountOpening.sortCode;
          }
          vm.accountOpeningMaster.C002 = vm.accountOpening.holderNum;
          vm.accountOpeningMaster.C005 = vm.accountOpening.depositAmount;
          vm.accountOpeningMaster.C004 = vm.accountOpening.accountHolderType;
          //vm.accountOpeningMaster.C009=vm.accountOpening.interestPaymentMode;
          vm.accountOpeningMaster.C010 = vm.accountOpening.C010;
          vm.accountOpeningMaster.C012 = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.getDetails.C012.oldValue));
          vm.accountOpeningMaster.C017 = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.getDetails.C017.oldValue));
          vm.accountOpeningMaster.C020 = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.getDetails.C020.oldValue));
          vm.accountOpeningMaster.C036 = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.getDetails.C036.oldValue));
          vm.accountOpeningMaster.C040 = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.getDetails.C036.oldValue));

          requestData.accountDetails = [convertToColumnValuesArray(vm.accountOpeningMaster)];
          requestData.nomineeDetails = [];
          requestData.creditAccountNumber = vm.accountOpening.creditAccountNumber;
          requestData.debitAccountNumber = vm.accountOpening.acctNum;
          requestData.setNum = vm.setId;
          requestData.instrumentType = vm.accountOpening.instType;
          requestData.instrumentNumber = vm.accountOpening.instNumber;
          requestData.instrumentDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.accountOpening.instDate));
          requestData.tranCode = vm.accountOpening.tranCode;
          requestData.zoneCode = vm.accountOpening.zoneCode;
          requestData.instrumentBankCode = vm.accountOpening.bankCode;
          requestData.instrumentBranchCode = vm.accountOpening.branchCode;
          requestData.zoneDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.accountOpening.zoneDate));
          requestData.neftBankCode = vm.accountOpening.zoneBankCode;
          requestData.neftBranchCode = vm.accountOpening.zoneBranchCode;
          requestData.mofDate = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.accountOpening.mofDate));
          requestData.mofReferenceNumber = vm.accountOpening.mofReference;
          //requestData.creditAccountNumberType = 'N'; //vm.creditAccountNumberType;
          //requestData.debitAccountNumberType = 'N'; //vm.debitAccountNumberType;

          modeOfHoldingService.getModifySubmit(requestData).then(
            (response) => {
              vm.loader = false;
              Swal.fire("Modified!", "Details Modified Successfully", "success");
              location.href = "#!/bla_acc_opening_cum_subscription/action/inquiry";
            },
            function (error) {
              vm.loader = false;
              handleErrorResponse(error);
            }
          );
       } }
      );
    }

    function getAccountPage() {
      vm.accountDetails = true;
      vm.nomination = false;
      if ($state.current.name == "bla_acc_opening_cum_subscription.inquiry_detail") {
        vm.matrix = false;
      }
    }

    function getNominationPage() {
      vm.accountDetails = false;
      vm.nomination = true;
      if ($state.current.name == "bla_acc_opening_cum_subscription.inquiry_detail") {
        vm.matrix = false;
      }
    }

    function getMatrixPage() {
      vm.accountDetails = false;
      vm.nomination = false;
      vm.matrix = true;
    }

    function getDebitAccountName(acctNum) {
      if (acctNum) {
        commonService.getSchemeTypeListAcc(
          acctNum,
          function (response) {
            if (response.success) {
              vm.accList = response.data;
              if (vm.accList.length > 0) {
                vm.accountOpening.accountName = vm.accList[0].accountName;
              }
            } else {
              Swal.fire("Error!", response.message, "error");
            }
          },
          function (err) {
            handleErrorResponse(err);
          }
        );
      }
    }

    function getCashValidation() {
      if (vm.accountOpening.tranMode == "C") {
        if (vm.accountOpening.depositAmount > vm.maximumCashLimit) {
          vm.errorMessage = "Deposit Amount could not exceed cash limit";
          vm.accountOpening.depositAmount = "";
          //vm.accountOpening.totalAmount = '';
        }
      }
    }

    function getCodeDescription() {
      if (vm.tranCodeList.length > 0) {
        for (let i = 0; i <= vm.tranCodeList.length - 1; i++) {
          if (vm.tranCodeList[i].clgTranCode == vm.tranCode) {
            if (vm.tranCodeList[i].clgTranCodeDesc) {
              vm.tranCodeDescription = vm.tranCodeList[i].clgTranCode + "-" + vm.tranCodeList[i].clgTranCodeDesc;
              break;
            } else {
              vm.tranCodeDescription = vm.tranCodeList[i].clgTranCode;
              break;
            }
          }
        }
      }

      if (vm.zoneCodeList.length > 0) {
        for (let i = 0; i <= vm.zoneCodeList.length - 1; i++) {
          if (vm.zoneCodeList[i].clgZoneCode == vm.zoneCode) {
            if (vm.zoneCodeList[i].clgZoneDesc) {
              vm.zoneCodeDescription = vm.zoneCodeList[i].clgZoneCode + "-" + vm.zoneCodeList[i].clgZoneDesc;
              break;
            } else {
              vm.zoneCodeDescription = vm.zoneCodeList[i].clgZoneCode;
              break;
            }
          }
        }
      }

      if (vm.instrumentBankCode) {
        if (vm.bankCodeList.length > 0) {
          for (let i = 0; i <= vm.bankCodeList.length - 1; i++) {
            if (vm.bankCodeList[i].bankCode == vm.instrumentBankCode) {
              if (vm.bankCodeList[i].bankName) {
                vm.bankCodeDescription = vm.bankCodeList[i].bankCode + "-" + vm.bankCodeList[i].bankName;
                break;
              } else {
                vm.bankCodeDescription = vm.bankCodeList[i].bankCode;
                break;
              }
            }
          }
        }
      }

      if (vm.instrumentBranchCode) {
        if (vm.branchCodeList.length > 0) {
          for (let i = 0; i <= vm.branchCodeList.length - 1; i++) {
            if (vm.branchCodeList[i].branchCode == vm.instrumentBranchCode) {
              if (vm.branchCodeList[i].branchName) {
                vm.branchCodeDescription = vm.branchCodeList[i].branchCode + "-" + (vm.branchCodeList[i].branchName ? vm.branchCodeList[i].branchName : "");
                break;
              } else {
                vm.branchCodeDescription = vm.branchCodeList[i].branchCode;
                break;
              }
            }
          }
        }
      }
    }

    function getMicrFlag(solId, zoneCode) {
      vm.zoneCodeList = [];
      commonService.getZoneCode(solId, zoneCode, function (response) {
        vm.zoneCodeList = response.data;
        for (var i = 0; i <= vm.zoneCodeList.length - 1; i++) {
          vm.zoneCodeList[i].clgZoneDate = new Date(vm.zoneCodeList[i].clgZoneDate);
          if (vm.zoneCodeList[i].clgZoneDate.getTime() == vm.accountOpening.zoneDate.getTime()) {
            vm.accountOpening.micrClgFlag = vm.zoneCodeList[i].micrClgFlag;
            break;
          }
        }
      });
    }

    function zoneDateValidation() {
      vm.zoneDateOptions = {
        showWeeks: false,
        maxDate: vm.systemDate,
      };
    }

    function setIdValidation() {
      if (vm.setId == 0) {
        var msg = "SetId should not be zero";
        Swal.fire("Warning!", msg, "warning");
        vm.setId = "";
      }
    }

    function instrumentAmountValidation() {
      if (vm.accountOpening.depositAmount != vm.instrumentAmt) {
        vm.errorMessageInstrumentAmount = "Instrument Amount should be equal to Deposit Amount";
        vm.instrumentAmt = "";
      } else {
        vm.errorMessageInstrumentAmount = "";
      }
    }


    function nextTab(elem) {
      $(elem).next().find('a[data-bs-toggle="tab"]').click();
    }

    function prevStep() {
      var $active = $(".from_wrapper .nav-tabs li.active");
      prevTab($active);
    }

    function prevTab(elem) {
      $(elem).prev().find('a[data-bs-toggle="tab"]').click();
    }

    function validatePanLetter() {
      vm.validationMessages = "";
      if (null!=vm.panNo&&vm.panNo.length >= 4) {
        var fourthLetter = vm.panNo.charAt(3).toUpperCase();
        if (["MH001"].includes(vm.selectedModeOfHolding) && fourthLetter !== "P") {
          vm.validationMessages = "Fourth letter of The Pan should be P.";
           vm.panNo = '';
        }
      }
    }
    function isMinor(date) {
      // Parse the input date (format: dd/MM/yyyy)
      var dobInput = $filter("date")(date, "dd/MM/yyyy");
      const [day, month, year] = dobInput.split("/");
      const birthDate = new Date(`${year}-${month}-${day}`); // Create a Date object from the input
      // Calculate the difference in years between the current date and the birth date
      const today = new Date();
      const age = today.getFullYear() - birthDate.getFullYear();
      // Adjust the age if the current date is before the birth date this year
      const monthDiff = today.getMonth() - birthDate.getMonth();
      const dayDiff = today.getDate() - birthDate.getDate();
      const isMinor = age < 18 || (age === 18 && (monthDiff < 0 || (monthDiff === 0 && dayDiff < 0)));
      return isMinor;
    }

    function updateMinorStatus2() {
      vm.customerMasterDetailsTwoFlag = vm.isMinor(vm.fromDate2) ? "Y" : "N";

    }

    function updateMinorStatus3() {
      vm.customerMasterDetailsThreeFlag = vm.isMinor(vm.fromDate3) ? "Y" : "N";

    }

    function updateMinorStatusForAll() {
      switch (vm.noOfJointAcc) {
        case 2:
          vm.updateMinorStatus2();
          break;
        case 3:
          vm.updateMinorStatus2();
          vm.updateMinorStatus3();
          break;
        default:
      }
    }

    function isMinorSingle() {
      // Parse the input date (format: dd/MM/yyyy)
      var dobInput = $filter("date")(vm.fromDate1, "dd/MM/yyyy");
      const [day, month, year] = dobInput.split("/");
      const birthDate = new Date(`${year}-${month}-${day}`); // Create a Date object from the input

      // Calculate the difference in years between the current date and the birth date
      const today = new Date();
      const age = today.getFullYear() - birthDate.getFullYear();

      // Adjust the age if the current date is before the birth date this year
      const monthDiff = today.getMonth() - birthDate.getMonth();
      const dayDiff = today.getDate() - birthDate.getDate();
      const isMinor = age < 18 || (age === 18 && (monthDiff < 0 || (monthDiff === 0 && dayDiff < 0)));

      vm.customerMasterDetails.isMinor = isMinor ? "Y" : "N";
    }

    vm.isAgeValid = function () {
      var solDate = vm.loginSolDate;
      var maxYear = convertToDateObject(solDate);
      maxYear.setFullYear(maxYear.getFullYear() - 18);
      var maxDate = maxYear;
      vm.dobCalenderOptions = {
        //minDate: minDate,
        maxDate: maxDate,
        showWeeks: false,
      };
    };


  function convertAmountToWords(amount) {
        if (amount == "0") {
          vm.wordsInRupees = "Zero";
        } else if (amount && !isNaN(amount)) {
          vm.wordsInRupees = amountToWords(amount);
          console.log(vm.wordsInRupees); // Debugging log
        } else if ((amount == "")) {
          vm.wordsInRupees = "";
        } else {
          vm.wordsInRupees = "Zero";
        }
      }

    function convertNumberToWords(amount) {
      if (amount === 0) return "Zero"; // Handle zero explicitly
      const below20 = [
        "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
        "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
      ];
      const tens = ["", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"];
      // Use only Lakhs, Crores, and Thousands
      const placeValues = ["", "Thousand", "Lakh", "Crore"];
      function convertChunk(number) {
        let chunk = "";
        if (number >= 100) {
          chunk += below20[Math.floor(number / 100)] + " Hundred ";
          number %= 100;
        }
        if (number >= 20) {
          chunk += tens[Math.floor(number / 10)] + " ";
          number %= 10;
        }
        if (number > 0) {
          chunk += below20[number] + " ";
        }
        return chunk.trim();
      }

      let words = "";
      let placeIndex = 0;
      while (amount > 0) {
        const chunkSize = placeIndex === 1 ? 2 : 3; // Indian numbering system
        const chunk = amount % Math.pow(10, chunkSize);
        if (chunk !== 0) {
          const chunkWords = convertChunk(chunk);
          const place = placeValues[placeIndex] ? " " + placeValues[placeIndex] : "";
          words = chunkWords + place + " " + words;
        }
        amount = Math.floor(amount / Math.pow(10, chunkSize));
        placeIndex++;
      }
      return words.trim();
    }

    // Helper function to convert a chunk (0-999) into words
    function convertChunk(num) {
      const below20 = [
        "Zero",
        "One",
        "Two",
        "Three",
        "Four",
        "Five",
        "Six",
        "Seven",
        "Eight",
        "Nine",
        "Ten",
        "Eleven",
        "Twelve",
        "Thirteen",
        "Fourteen",
        "Fifteen",
        "Sixteen",
        "Seventeen",
        "Eighteen",
        "Nineteen",
      ];
      const tens = ["", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"];
      if (num === 0) return ""; // Return empty for zero, handled in main function
      if (num < 20) return below20[num];
      if (num < 100) {
        return tens[Math.floor(num / 10)] + (num % 10 ? " " + below20[num % 10] : "");
      }
      return below20[Math.floor(num / 100)] + " Hundred" + (num % 100 ? " and " + convertChunk(num % 100) : "");
    }

  //Migration fix start
  function amountToWords(price) {
    var sglDigit = ["Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"],
      dblDigit = ["Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"],
      tensPlace = ["", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"],
      handle_tens = function(dgt, prevDgt) {
        return 0 == dgt ? "" : " " + (1 == dgt ? dblDigit[prevDgt] : tensPlace[dgt])
      },
      handle_utlc = function(dgt, nxtDgt, denom) {
        return (0 != dgt && 1 != nxtDgt ? " " + sglDigit[dgt] : "") + (0 != nxtDgt || dgt > 0 ? " " + denom : "")
      };

    var str = "",
      digitIdx = 0,
      digit = 0,
      nxtDigit = 0,
      words = [];
    if (price += "", isNaN(parseInt(price))) str = "";
    else if (parseInt(price) > 0 && price.length <= 10) {
      for (digitIdx = price.length - 1; digitIdx >= 0; digitIdx--) switch (digit = price[digitIdx] - 0, nxtDigit = digitIdx > 0 ? price[digitIdx - 1] - 0 : 0, price.length - digitIdx - 1) {
        case 0:
          words.push(handle_utlc(digit, nxtDigit, ""));
          break;
        case 1:
          words.push(handle_tens(digit, price[digitIdx + 1]));
          break;
        case 2:
          words.push(0 != digit ? " " + sglDigit[digit] + " Hundred" + (0 != price[digitIdx + 1] && 0 != price[digitIdx + 2] ? " and" : "") : "");
          break;
        case 3:
          words.push(handle_utlc(digit, nxtDigit, "Thousand"));
          break;
        case 4:
          words.push(handle_tens(digit, price[digitIdx + 1]));
          break;
        case 5:
          words.push(handle_utlc(digit, nxtDigit, "Lakh"));
          break;
        case 6:
          words.push(handle_tens(digit, price[digitIdx + 1]));
          break;
        case 7:
          words.push(handle_utlc(digit, nxtDigit, "Crore"));
          break;
        case 8:
          words.push(handle_tens(digit, price[digitIdx + 1]));
          break;
        case 9:
          words.push(0 != digit ? " " + sglDigit[digit] + " Hundred" + (0 != price[digitIdx + 1] || 0 != price[digitIdx + 2] ? " and" : " Crore") : "")
      }
      str = words.reverse().join("") + " Rupees"; // Append " Rupees"
    } else str = "";
    return str
  }//End

    vm.isMinorAge = function (dateOfBirth) {
      var today = new Date();
      var birthDate = new Date(dateOfBirth);
      var age = today.getFullYear() - birthDate.getFullYear();
      var m = today.getMonth() - birthDate.getMonth();
      if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
        age--;
      }
      vm.isMinor = age < 18;
    };
  }
})();
