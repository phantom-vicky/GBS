(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("rbibondNomineeContinuationController", rbibondNomineeContinuationController);

  rbibondNomineeContinuationController.$inject = ["$scope", "$filter", "$state", "$rootScope", "$stateParams", "$location", "$timeout", "rbibondNomineeContinuationService", "commonService"];

  function rbibondNomineeContinuationController($scope, $filter, $state, $rootScope, $stateParams, $location, $timeout, rbibondNomineeContinuationService, commonService) {
    var vm = this;
    //	    vm.panPattern = /[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}/;
vm.tabReferenceAdd='';
vm.tabReferenceVerify='';
vm.tabReferenceInquiry='';
vm.selectedSubsIndex = null;
    vm.ifscPattern = /[a-zA-Z]{4}[0-9]{1}[a-zA-Z0-9]{6}/;
    vm.validationMessages = "";
    vm.modeOfHolding = ""; // "Single", "Joint", "Jointly", or "HUF"
    vm.validatePanLetter = validatePanLetter;
    vm.validatePanLetter1 = validatePanLetter1;
    vm.validatePanLetter2 = validatePanLetter2;
		vm.checkIfMinor=checkIfMinor;
       vm.isMinorSingle = isMinorSingle;
    vm.showCancelConfirmation = showCancelConfirmation;
    vm.redirectToInputPage = redirectToInputPage;
    vm.getMinorFlag = getMinorFlag;
    //vm.getScssCommonMetaData = getScssCommonMetaData;
    vm.itemsPerPage = "5";
    vm.scssOtherBanksDetails = [{}];
    vm.getInputNomineeAdd = getInputNomineeAdd;
    vm.getInputNomineeVer = getInputNomineeVer;
    vm.getInputNomineeInq = getInputNomineeInq;
    vm.getInputNomineeMod = getInputNomineeMod;
    vm.customerMasterDetails = [];
    vm.customerMasterJointDetails = [];
    vm.customerMasterFormDetails = {};
    vm.getState = getState;
    vm.getClearAllData = getClearAllData;
    vm.getClearAllDatas = getClearAllDatas;
    vm.getSolDetails = getSolDetails;
    vm.showCBSServiceWarning = showCBSServiceWarning;
    vm.getSchemeTypeListOperative = getSchemeTypeListOperative;
    vm.getSchemeTypeListOperativeJoint = getSchemeTypeListOperativeJoint;
    vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
    vm.panPattern = /[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}/;
    vm.emailPattern = /[^@]+@[^@]+\.[a-zA-Z]{2,6}/;
    vm.optionToggled = optionToggled;
    vm.showAccountValidationSuccess = showAccountValidationSuccess;
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
    vm.getblaNoVer = getblaNoVer;
    vm.getboSubsIdAdd = getboSubsIdAdd;
    vm.getboSubsIdMod = getboSubsIdMod;
    vm.getboSubsIdInq = getboSubsIdInq;
    vm.getboSubsIdVer = getboSubsIdVer;
    vm.alert = alert;
    vm.getModeofHolding = getModeofHolding;
    vm.getTorch = getTorch;
    vm.addcustomerMaster = addcustomerMaster;
    vm.convertYNToBoolean = convertYNToBoolean;
    vm.getDashBoard = getDashBoard;
    vm.getMaturityDate = getMaturityDate;
    vm.handleHdfcSelection = handleHdfcSelection;
    vm.getAccountType = getAccountType;
    vm.convertNumberToWords = convertNumberToWords;
    vm.convertChunkToWords = convertChunkToWords;
    vm.getbankName = getbankName;
    vm.getbankNameInq = getbankNameInq;

    vm.getCbsCustFstJoinDetails = getCbsCustFstJoinDetails;
    vm.getCbsCustScndJoinDetails = getCbsCustScndJoinDetails;
    vm.getBlaAccNoDetails = getBlaAccNoDetails;
    vm.getScssJointCustDetails = getScssJointCustDetails;
    vm.getCurrentFinancialYear = getCurrentFinancialYear;
    vm.getModifySelectDetails = getModifySelectDetails;
    vm.modifycustomerMaster = modifycustomerMaster;
    vm.getVerifySelectDetails = getVerifySelectDetails;
    vm.verifyCustomerMaster = verifyCustomerMaster;
    vm.getInquirySelectDetails = getInquirySelectDetails;
    vm.depositDateValidation = depositDateValidation;
    vm.maturityDateValid = maturityDateValid;
    vm.maturityDateValidation = maturityDateValidation;
    vm.getValidations = getValidations;
    vm.accountHolderDateValidation = accountHolderDateValidation;
    vm.dateOfRetirementValid = dateOfRetirementValid;
    vm.getDisableTorchFlag = getDisableTorchFlag;
    vm.getTypeofHolding = getTypeofHolding;
    vm.getFormDetails = getFormDetails;
    vm.getKycDetails = getKycDetails;
    vm.getGuardianDetails = getGuardianDetails;
    vm.getGuardianDetailsMetaData = getGuardianDetailsMetaData;
    vm.getModuleName = getModuleName;
    vm.getWboBranchCode = getWboBranchCode;
    vm.getModuleNameAdd = getModuleNameAdd;
    vm.formFifteenGValidation = formFifteenGValidation;
    vm.convertBooleanToYN = convertBooleanToYN;
    vm.minAgeC = minAgeC;
    vm.nextStep = nextStep;
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
		vm.getBlaAccNoDetailsModify = getBlaAccNoDetailsModify;
		vm.getBlaAccNoDetailsTwoModify = getBlaAccNoDetailsTwoModify;
		vm.getBlaAccNoDetailsThreeModify = getBlaAccNoDetailsThreeModify;
		vm.getGuardianDetailsModify = getGuardianDetailsModify;
		vm.getGuardianDetailsTwoModify = getGuardianDetailsTwoModify;
		vm.getGuardianDetailsThreeModify = getGuardianDetailsThreeModify;
		vm.formFifteenGValidationM = formFifteenGValidationM;
		vm.formFifteenGValidationTwoM = formFifteenGValidationTwoM;
		vm.getModeOfInterestPayment = getModeOfInterestPayment;
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
    vm.nextTab = nextTab;
           vm.getPanValidation = getPanValidation;

    vm.prevTab = prevTab;
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
    vm.isMinor = isMinor;
    vm.calculateAge = calculateAge;
    vm.instrumentDateValidation = instrumentDateValidation;
    vm.getSchemeTypeListOperativeNominee = getSchemeTypeListOperativeNominee;
    vm.getAccountPage = getAccountPage;
    vm.getNominationPage = getNominationPage;
    vm.getMatrixPage = getMatrixPage;
    vm.getDebitAccountName = getDebitAccountName;
    vm.getCbsDetails = getCbsDetails;
    vm.neftPlaceholderFlgOut;
    vm.prevTab = prevTab;
    vm.prevStep = prevStep;
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
    vm.submitAddForm = submitAddForm;
    vm.validateSubscriptionAmount = validateSubscriptionAmount;
vm.getVerPantorch = getVerPantorch;

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
    vm.unlockBlaSubmit = unlockBlaSubmit;
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
 vm.getFtsFile = getFtsFile;
  vm.ftsFunction=ftsFunction;
    vm.ftsIndex=0;
    function ftsFunction(flag) {
        if (flag == 'right') {
            if (vm.ftsFile.length - 2 >= vm.ftsIndex) {
                vm.ftsIndex++;
            } else {
                vm.ftsIndex = 0;
            }
        } else {
            if (vm.ftsIndex == 0) {
                vm.ftsIndex = vm.ftsFile.length - 1;
            } else {
                vm.ftsIndex--;
            }
        }

        // Reset image transformations on navigation
        vm.imageRotation = 0;
        vm.imageZoom = 1;
    }
    vm.imageRotation = 0;
    vm.imageZoom = 1;

    vm.rotateImage = function(deg) {
        vm.imageRotation = (vm.imageRotation + deg) % 360;
    };

    vm.zoomImage = function(factor) {
        vm.imageZoom *= factor;
        if (vm.imageZoom < 0.5) vm.imageZoom = 0.5;
        if (vm.imageZoom > 5) vm.imageZoom = 5;
    };
vm.ftsDetailFetch=true;
      function getFtsFile() {
      vm.loader = true;
if(!vm.ftsAppNum){
      vm.loader = false;
      vm.ftsDetailFetch=false;
      return false;
}
//vm.ftsDetailFetch=false;
      rbibondNomineeContinuationService.getFtsFileService(vm.ftsAppNum).then(
        function (response) {
        vm.ftsDetailFetch=false;
          vm.loader = false;
          vm.ftsFile =response.data.body.imagesList;
            if(response.data.statusCodeValue != 200){
                       Swal.fire("Warning!", "FTS APP NUM was wrong or check with your administrator", "warning");
                    vm.ftsDetailFetch=true;
                    }
        },
        function (error) {
         // handleErrorResponse(error);
//          vm.ftsDetailFetch=false;
 Swal.fire("Warning!", "FTS APP NUM was wrong or check with your administrator", "warning");

          vm.loader = false;
        }
      );
    }


     vm.textableDatePicker=textableDatePicker;
             vm.autoslashing=autoslashing;
             vm.futureNotAllowed=futureNotAllowed;

vm.textableDatePickerYear = textableDatePickerYear;

function textableDatePickerYear(id, item, index) {
    var element = document.getElementById(id);
    if (!element) {
        return false;
    }
    var dateValue = element.value;
    // If empty, keep AngularJS required validation
    if (!dateValue) {
        item.nomineeDob = null;
        return false;
    }
    commonService.textableDatePicker(id, dateValue);
    dateValue = element.value;
    var datePattern =/^(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[0-2])\/(17|18|19|20|21)\d{2}$/;
    if (!datePattern.test(dateValue)) {
        item.nomineeDob = null;
        element.value = '';
        Swal.fire("Error!","Please enter a valid date in DD/MM/YYYY format.","error");
        return false;
    }
    return true;
}

    function textableDatePicker(id,valueDate)
    {
     commonService.textableDatePicker(id,valueDate);
    }
    function futureNotAllowed(id, item, index)
    {
           if(item.nomineeDob){
                 var today = new Date();
                 today.setHours(0, 0, 0, 0);
                 var birthDate = new Date(item.nomineeDob);
                 if (isNaN(birthDate.getTime())) {
                     return false;
                 }
                 birthDate.setHours(0, 0, 0, 0);
                 // Future date validation
                 if (birthDate > today) {
                     item.nomineeDob = null;
                     document.getElementById(id).value = '';
                     Swal.fire("Error!", "Please don't enter future date ", "error");
                     return false;
                 }
                 }
    }

    function autoslashing(id)
    {
     commonService.autoslashing(id);
    }

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
    ];
    if ($state.current.name == "rbi_bond_nominee_continuation.add_detail") {
      if ($stateParams.obj) {

    
     vm.modeOfHolding =  $stateParams.obj.modeOfHolding,
     vm.ftsAppNum =  $stateParams.obj.ftsAppNum,
     vm.ftsDetailFetch =  $stateParams.obj.ftsDetailFetch,
    		                        vm.nominationDetails1 =  $stateParams.obj.nominationDetails1,
    		                        vm.schemeCode =  $stateParams.obj.schemeCode,
    								vm.schemeName =  $stateParams.obj.schemeName,
    								vm.panNum =  $stateParams.obj.panNum,
    								vm.blaNo =  $stateParams.obj.blaNo,
    								vm.boSubsId =  $stateParams.obj.boSubsId,
    								vm.bondHolderName1 = $stateParams.obj.bondHolderName,
getModeOfInterestPayment();
 getSegment();

  if($stateParams.obj.ftsDetailFetch == false)
           {
              vm.ftsAppNum = $stateParams.obj.ftsAppNum;

                 setTimeout(function() {
                              vm.loader=true;
                                                             getFtsFile();
                                                         }, 1000);
               }
        getAccountType();
        getState();
//        isMinorSingle();
//        getCurrentFinancialYear();
//        //                getModuleName();
//        titleChange();
        getTypeofHolding();
//        getFormDetails();
//        getKycDetails();
        getGuardianDetailsMetaData();
//        getTransactionModes();
//        getSchemeTypeListOperative();

        addDet();



      /*  if (vm.bondHolderName) {
          splitName(vm.bondHolderName);
        }
        if (vm.secondJointHolderName) {
          splitName(vm.secondJointHolderName);
        }

        if (vm.thirdJointHolderName) {
          splitName(vm.thirdJointHolderName);
        }
        //		        getTdsApplicableRate();
        getModeOfInterestPayment();
        getSegment();
        getAccountType();
        getState();
        isMinorSingle();
        updateMinorStatusForAll();
        validateNominee();*//*
         getInputNomineeAdd();*/
      }
    } else if ($state.current.name == "rbi_bond_nominee_continuation.verify_detail") {
      if ($stateParams.obj) {

       vm.blaNo = $stateParams.obj.blaNo;
               vm.schemeCode = $stateParams.obj.schemeCode;
               vm.schemeName = $stateParams.obj.schemeName;
               vm.panNum = $stateParams.obj.panNum;
               vm.boSubsId = $stateParams.obj.boSubsId;

               vm.nominationDetails1 = $stateParams.obj.nominationDetails1;
               getVerifySelectDetails("D");
               vm.bondHolderName = $stateParams.obj.bondHolderName;
               vm.ftsAppNum = $stateParams.obj.ftsAppNum;
               vm.ftsDetailFetch = $stateParams.obj.ftsDetailFetch;
               if($stateParams.obj.ftsDetailFetch == false)
                                      {
                                 //vm.ftsAppNum = '1234567890';


                                 setTimeout(function() {
                                 vm.loader=true;
                                 getFtsFile();
                                 }, 1000);
                                          }
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
    } else if ($state.current.name == "rbi_bond_nominee_continuation.delete_detail") {
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
    } else if ($state.current.name == "rbi_bond_nominee_continuation.list_detail") {
      if ($stateParams.obj) {
        vm.blaNo = $stateParams.obj.blaNo;
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.panNum = $stateParams.obj.panNum;
        vm.boSubsId = $stateParams.obj.boSubsId;
        vm.bondHolderName = $stateParams.obj.bondHolderName;
        vm.nominationDetails1 = $stateParams.obj.nominationDetails1;
        getInquirySelectDetails("D");
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
    } else if($state.current.name == "rbi_bond_nominee_continuation.list"){
//           vm.schemeCode = '0012';
//               vm.panNum = 'AJYPG4768J';
//                       vm.bondHolderName= 'GEETHA VENKATRAM';
//                       vm.blaNo= 'RBI00080000271';
//                       vm.boSubsId= '101BS00003003';
//          getInputNomineeInq();
//          getModeOfInterestPayment();
//          getInquirySelectDetails("select");

          }else if ($state.current.name == "rbi_bond_nominee_continuation.add") {
      getDashBoard();
    } else if ($state.current.name == "rbi_bond_nominee_continuation.add_input") {
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

        maxDate: vm.currentDate
      };

      vm.valueDateOptions = {
        minDate: vm.accountOpeningDate, // Min date based on Account Open Date
        maxDate: vm.currentDate, // Set maximum date to current date
      };


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
        rbibondNomineeContinuationService.getNonHdfcTorch(requestData).then(
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
        rbibondNomineeContinuationService.getNonHdfcBranchTorch(requestData).then(
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
    vm.getYesNoLabel = function(value) {
        return value === 'Y' ? 'Yes' : 'No';
    };
 function getbankName(branchCode,index) {
       var requestData = {
                       referenceId: branchCode,
                     };
                     rbibondNomineeContinuationService.getbankName(requestData).then(
                       function (response) {
                         vm.customerMasterDetails[index].bankCode = response.data.result[0].bank_name;
                       },
                       function (error) {

                       }
                     );

        }
        function getbankNameInq(branchCode,index) {
               var requestData = {
                               referenceId: branchCode,
                             };
                             rbibondNomineeContinuationService.getbankName(requestData).then(
                               function (response) {
                                 vm.getBlaDetail[index].bankCode = response.data.result[0].bank_name;
                               },
                               function (error) {

                               }
                             );

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
    if(vm.schemeList){
      for (let i = 0; i < vm.schemeList.length; i++) {
        if (vm.schemeList[i].subCode == item) {
          vm.schemeName = vm.schemeList[i].subCodeDescription;
          //vm.cusName = vm.custList[i].cusName;
          //  vm.cusAlphaCode = vm.custList[i].cusAlphaCode;
          // vm.id = vm.custList[i].id;
        }
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


    vm.isAnyFormInvalid = function() {
        if (!vm.nominationDetails1 || vm.nominationDetails1.length === 0) {
            return true;  // Disable if no nominees
        }
        for (let i = 0; i < vm.nominationDetails1.length; i++) {
            if (vm.customerMasterForm && vm.customerMasterForm[i] && vm.customerMasterForm[i].$invalid) {
                return true;  // Disable submit button if any form is invalid
            }
        }
        return false;  // Enable submit button only if all forms are valid
    };

    function getDashBoard() {
      vm.loader = true;

      if (vm.id == null || vm.id == undefined) {
        var requestData = {};
        rbibondNomineeContinuationService.getAddDashBoard(requestData).then(
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

        rbibondNomineeContinuationService.getAddDashBoard(requestData).then(
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
      rbibondNomineeContinuationService.getInquiryModifyModuleName(requestData).then(
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
       vm.getVerifyModifyModuleName=getVerifyModifyModuleName;
        function getVerifyModifyModuleName(flag) {
          vm.loader = true;
          vm.referenceId = flag;
          var requestData = {
          referenceId: vm.referenceId,
          };
          rbibondNomineeContinuationService.getInquiryModifyModuleName(requestData).then(
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
    function getModuleName() {
      vm.loader = true;

      var requestData = {};
      rbibondNomineeContinuationService.getRbiBondModuleNameDetails(requestData).then(
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
        pan_No1: vm.pan_No1,
        pan_No2: vm.pan_No2,
        pan_No3: vm.pan_No3,
        modeOfHolding: vm.modeOfHolding,
      };
      rbibondNomineeContinuationService.getMinorDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result.torchValue;
          if (vm.schemeList.length == 0) {
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
      rbibondNomineeContinuationService.getWboBranchDetails(requestData).then(
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
      rbibondNomineeContinuationService.getFtsDetails(RequestBody).then(
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
      vm.validationCompleted = false; // Reset validation status

      var requestData = {
        Source_System: "YourSourceSystem",
        inputData: [
          {
            pan: vm.pan_No1,
            name: vm.bondHolderName,
            fatherName: "",
            dob: $filter("date")(vm.fromDate1, "dd/MM/yyyy"),
          },
          {
            pan: vm.pan_No2,
            name: vm.secondJointHolderName,
            fatherName: "",
            dob: $filter("date")(vm.fromDate2, "dd/MM/yyyy"),
          },
          {
            pan: vm.pan_No3,
            name: vm.thirdJointHolderName,
            fatherName: "",
            dob: $filter("date")(vm.fromDate3, "dd/MM/yyyy"),
          },
        ],
      };

      rbibondNomineeContinuationService.validatePan(requestData).then(
        function (response) {
          vm.loader = false;

          if (response.data && response.data.outputData) {
            vm.panDetails = response.data.outputData; // Assuming outputData contains an array of results

            // Clear previous validation messages
            vm.validationMessages = [];

            // Prepare messages based on pan_status
            var hasErrors = false;
            vm.panDetails.forEach(function (detail) {
              if (detail.pan_status === "E") {
                vm.validationMessages.push("Pan " + detail.pan + "  already exists.");
                hasErrors = true;
              } else if (detail.pan_status === "N") {
                vm.validationMessages.push("Pan " + detail.pan + ": No record found.");
                hasErrors = true;
              } else {
                vm.validationMessages.push("Pan " + detail.pan + ": Unknown status.");
                hasErrors = true;
              }
            });

            // Update validation status based on errors
            vm.validationCompleted = !hasErrors;
          } else {
            vm.panDetails = null; // Handle the case where no output data is returned
            console.warn("No output data returned in response.");
            vm.validationMessages = ["No validation messages available."];
            vm.validationCompleted = false;
          }
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }
 function checkIfMinor(dateOfBirth) {
        var today = new Date();
        var birthDate = new Date(dateOfBirth);
        var age = today.getFullYear() - birthDate.getFullYear();
        var m = today.getMonth() - birthDate.getMonth();

        if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
            age--;
        }

        return age < 18;
    };
    function addDet() {
      vm.loader = true;

        vm.nominationDetails2 = [];
         angular.forEach(vm.nominationDetails1, function (value, key) {
//      const isMinorPan = checkIfMinor(value.nomineeDob);


                                   vm.nominationDetails2.push({ nomineeId: value.nomineeId  ,
                                    nomineeName: value.nomineeName  ,
        nomineeDob: $filter("date")(value.nomineeDob, "dd-MM-yyyy"), // Format using $filter and append fractional seconds
                                    panNum: value.panNum
//                                     isMinorPan:isMinorPan
                                      });
                                });

        var requestData = {
          schemeCode: vm.schemeCode,
          modeOfHolding: vm.modeOfHolding,
          nomineeDetList: vm.nominationDetails2,
          boSubsId: vm.boSubsId,
          panNum: vm.panNum
        };

        rbibondNomineeContinuationService
          .addDet(requestData)
          .then(function (response) {
            vm.loader = false;

            if (response && response.data && response.data.result) {
              var result = response.data.result;



              vm.blaFlag = result.blaFlag;

              vm.guardDet = result.guardianDetailList;
              vm.subsDet = result.subsDetailsList;

               for(var i =0;i<vm.subsDet.length;i++)
                                            {
    vm.subsDet[i].wordsInRupees = convertAmountToWords(vm.subsDet[i].subscriptionAmount);
                            vm.subsDet[i].accountOpenDate = moment(vm.subsDet[i].accountOpenDate, "DD-MM-YYYY").toDate();
                            vm.subsDet[i].valueDate = moment(vm.subsDet[i].valueDate, "DD-MM-YYYY").toDate();
                                             }
                for(var i =0;i<vm.guardDet.length;i++)
                                                          {
                  vm.guardDet[i].guardianDob = moment(vm.guardDet[i].guardianDob, "DD-MMM-YYYY").toDate();
                                   }
//              vm.subscriptionAmount = vm.subsDet.subscriptionAmount;

              vm.bondCashLimit = result.cashLimitForCash;
              vm.bondClearingTransferLimit = result.cashLimitForClearingTransfer;
              vm.depositMultiples = result.depositMultiples;
              vm.minCashLimit = result.minCashLimit;
              vm.aadharCapture = result.aadharCapture;
              vm.aadharMandatory = result.aadharMandatory;
              vm.aadharDisplay = result.aadharDisplay;
              vm.blaNoOut = result.blaDetailsList;
              vm.customerMasterDetails = result.blaDetailsList;

               for (let i = 0; i < vm.nominationDetails2.length; i++) {
                   const nominee = vm.nominationDetails2[i];
                   const nameComponents = splitName(nominee.nomineeName);

                   // Find if the nomineeId exists in customerMasterDetails
                   const existingCustomerDetail = vm.customerMasterDetails.find(detail => String(detail.nomineeId) === String(nominee.nomineeId));

                   if (!existingCustomerDetail) {
                       // If nomineeId does not exist in customerMasterDetails, add the new entry
                       vm.customerMasterDetails.push({
                           nomineeId: nominee.nomineeId,
                           holderFirstName: nameComponents.firstName,
                           holderMiddleName: nameComponents.middleName,
                           holderLastName: nameComponents.lastName,
                           bondHolderDob: moment(nominee.nomineeDob, "DD-MM-YYYY").toDate(),
                           panNum: nominee.panNum
//                           isMinor: 'N'

                       });
                   } else {
                       // If nomineeId exists, use the existing customer detail instead of creating duplicate
                       // Update the existing entry if needed
                       if(existingCustomerDetail.flag == 'NON-EXISTING'){
                           existingCustomerDetail.bondHolderDob = moment(nominee.nomineeDob, "DD-MMM-YYYY").toDate();
                       } else {
                           // Find the index of existingCustomerDetail in the array for isMinor assignment
                           const existingIndex = vm.customerMasterDetails.findIndex(detail => String(detail.nomineeId) === String(nominee.nomineeId));
                           if(existingIndex !== -1) {
                               vm.isMinor[existingIndex] = vm.customerMasterDetails[existingIndex].isMinor;
                           }
                       }
                   }
               }

               // Remove duplicates based on nomineeId - keep only the first occurrence
               const seenNomineeIds = new Set();
               vm.customerMasterDetails = vm.customerMasterDetails.filter((item, index) => {
                   if (item.nomineeId) {
                       if (seenNomineeIds.has(String(item.nomineeId))) {
                           return false; // Duplicate, remove it
                       }
                       seenNomineeIds.add(String(item.nomineeId));
                       return true; // First occurrence, keep it
                   }
                   return true; // No nomineeId, keep it (might be a bond holder)
               });

               for(var i =0;i<vm.customerMasterDetails.length;i++)
                                                          {
                  vm.customerMasterDetails[i].bondHolderDob = moment(vm.customerMasterDetails[i].bondHolderDob, "DD-MMM-YYYY").toDate();
                  if(vm.customerMasterDetails[i] &&
                                  vm.customerMasterDetails[i].bankCode)
                                {
                                    vm.getbankName(vm.customerMasterDetails[i].bankCode,i);
                                }
                    vm.customerMasterDetails[i].nameOfThePayee = (vm.customerMasterDetails[i].holderFirstName || '') + ' ' + (vm.customerMasterDetails[i].holderMiddleName || '') + ' ' + (vm.customerMasterDetails[i].holderLastName || '');
                                   }
              if (vm.valueDate && vm.periodOfDeposit) {
                getMaturityDate(vm.valueDate, vm.periodOfDeposit);
              }
              if (vm.blaNoOut) {
                // if (vm.modeOfHolding == "MH001" ) {
                   if (vm.blaNoOut && vm.blaNoOut.length > 1) {
                    vm.singleBondAccNo = vm.blaNoOut
                      .map(function(item) {
                        return item.blaNo;
                      }).filter(function(bla) {
                        return bla && bla.trim() !== "";
                      }).join(", ");
                  }
                  if (vm.blaNoOut[0]) {
                    vm.bondHolderName = vm.blaNoOut[0].nameOfTheBondHolder;
                    vm.singleBondAccNo= vm.blaNoOut[0].blaNo;
                  }
                // }
                }
              if (vm.blaFlag == "N") {
                if (vm.bondHolderName) {
                  //                vm.customerMasterDetails.bondHldFirstName = vm.bondHolderName;
                  const nameComponents = splitName(vm.bondHolderName);
                  vm.customerMasterDetails.bondHldFirstName = nameComponents.firstName;

                  vm.customerMasterDetails.bondHldMidName = nameComponents.middleName;
                  vm.customerMasterDetails.bondHldLastName = nameComponents.lastName;
                  vm.customerMasterDetails.bondHldDob = vm.fromDate1;
                  vm.customerMasterDetails.panNo = vm.pan_No1;
                }
                updateMinorStatus1();

              }

              if (vm.blaFlag === "Y") {
//                Swal.fire(
//                  {
//                    title: "Warning!",
//                    text: "Bond Ledger Account Number Already Exists " + vm.singleBondAccNo,
//                    type: "warning",
//                    showCancelButton: true,
//                    cancelButtonClass: "btn-danger",
//
//                    confirmButtonText: "OK",
//                    cancelButtonText: "CANCEL",
//                  },
//                  function (isConfirm) {


           Swal.fire({
             title: "Are you sure?",
           text: "Bond Ledger Account Number Already Exists " + vm.singleBondAccNo,
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
                      $state.go("rbi_bond_nominee_continuation.add_input");
                    }
                  }
                );
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

                if (vm.BlaFlagNo[2]) {
                  vm.customerMasterDetailsThree = vm.BlaFlagNo[2];
                }

                if (vm.bondHolderName && vm.customerMasterDetails) {
                  const nameComponents = splitName(vm.bondHolderName);
                  vm.customerMasterDetails.bondHldFirstName = nameComponents.firstName;
                  vm.customerMasterDetails.bondHldMidName = nameComponents.middleName;
                  vm.customerMasterDetails.bondHldLastName = nameComponents.lastName;
                  vm.customerMasterDetails.bondHldDob = vm.fromDate1;
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
                  } else if (detail.jointHolderType == 3) {
                    orderedDetails[2] = detail; // Third joint holder
                  }
                }

                vm.titleText =
                  vm.cashinDet.length === 1 && (vm.noOfJointAcc === undefined || vm.noOfJointAcc === null || isNaN(vm.noOfJointAcc)) ? "BOND HOLDER DETAILS" : "FIRST JOINT BOND HOLDER DETAILS";

                // Bind customer details based on orderedDetails
                if (orderedDetails[0]) {
                  vm.customerMasterDetails = orderedDetails[0]; // Bind first detail if it exists
                }





                if (vm.customerMasterDetails.bondHldDob) {
                  vm.dateOfBirth = convertDateFormat(vm.customerMasterDetails.bondHldDob);
                  vm.customerMasterDetails.bondHldDob = vm.dateOfBirth;

                  //                                                                    if (vm.customerMasterDetails.bondHldFirstName && vm.customerMasterDetails) {
                  //                                                                         const nameComponents = splitName(vm.customerMasterDetails.bondHldFirstName);
                  //                                                                         vm.customerMasterDetails.bondHldFirstName = nameComponents.firstName;
                  //                                                                         vm.customerMasterDetails.bondHldMidName = nameComponents.middleName;
                  //                                                                         vm.customerMasterDetails.bondHldLastName = nameComponents.lastName;
                  //
                  //                                                                     }
                }


              }
              if (Array.isArray(vm.guardDet)) {
                let orderedDetails1 = [];

                for (let i = 0; i < vm.guardDet.length; i++) {
                  const detail = vm.guardDet[i];


                }



                if (vm.guardDet.length > 0) {
                  if (vm.guardianDetailsValueModel) {
                    vm.guardDet.guardianId = vm.guardianDetailsValueModel.guardianId;
                    vm.guardDet.guardianFirstName = vm.guardianDetailsValueModel.guardianFirstName;
                    vm.guardDet.guardianMiddleName = vm.guardianDetailsValueModel.guardianMiddleName;
                    vm.guardDet.guardianLastName = vm.guardianDetailsValueModel.guardianLastName;
                    vm.guardDet.relationWithMinor = vm.guardianDetailsValueModel.relationWithMinor;
                    if (vm.guardianDetailsValueModel.guardianDateOfBirth) {
                      vm.guardianDateOfBirth = convertDateFormat(vm.guardianDetailsValueModel.guardianDateOfBirth);
                      vm.guardDet.guardianDateOfBirth = vm.guardianDateOfBirth;
                    }
                    vm.guardDet.guardianCommunicationAddress = vm.guardianDetailsValueModel.guardianCommunicationAddress;
                    vm.guardDet.guardianMobileNo = vm.guardianDetailsValueModel.mobileNo;
                    vm.guardDet.guardianEmailId = vm.guardianDetailsValueModel.guardianEmailId;
                  }


                }
              }
              //                vm.solDate = new Date(); // Set this to your actual system date
              vm.accountOpeningDate = vm.systemDate;
//              isMinorSingle();

              //             vm.accountOpeningDate =  $filter('date')(vm.systemDate, 'dd/MM/yyyy');
            } else {
              console.error("Response structure is not as expected:", response);
            }
          })
          .catch(function (err) {
            vm.loader = false;

            const errorMessage = err && err.data && err.data.message
                    ? err.data.message
                    : "An unexpected error occurred.";
            if(null!=errorMessage && errorMessage.startsWith("Invalid")){
            Swal.fire("Warning!", errorMessage , "warning");
            $state.go("rbi_bond_nominee_continuation.list");
            }else{
            handleErrorResponse(err);
            $state.go("rbi_bond_nominee_continuation.list");
            }
          });

    }

    function unlockBlaSubmit() {
      if (vm.id !== null && vm.id !== undefined) {
        var requestData = {
          id: vm.id,
        };

//        Swal.fire(
//          {
//            title: "Are you sure?",
//            text: "Do you want to Cancel?",
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
               text: "Do you want to Cncel?",
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

            rbibondNomineeContinuationService
              .unlock(requestData)
              .then(function (response) {
                vm.loader = false;
                //                Swal.fire("Unlocked!", "Selected users are unlocked successfully", "success");
                vm.responseResult = response.data.result;
                Swal.fire("Success!", vm.responseResult, "success");
                location.href = "#!/rbi_bond_nominee_continuation/action/inquiry";
              })
              .catch(function (error) {
                handleErrorResponse(error);
                vm.loader = false;
              });
       }   }
        );
      } else {
        $state.go("rbi_bond_nominee_continuation.add");
      }
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
      rbibondNomineeContinuationService.getRbiBondModuleNameDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result;
        },
        function (error) {
          handleErrorResponse(error);
          $state.go("rbi_bond_nominee_continuation.list");
          vm.loader = false;
        }
      );
    }

    function getPantorch() {
      var requestData = {
        schemeCode: vm.schemeCode,
//        bondHolderName: vm.bondHolderName,
        panNum: vm.panNum,
      };
      rbibondNomineeContinuationService.blaTorch(requestData).then(
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
 function getVerPantorch() {
      var requestData = {
        schemeCode: vm.schemeCode,
//        bondHolderName: vm.bondHolderName,
        panNum: vm.panNum,
      };
      rbibondNomineeContinuationService.blaTorch(requestData).then(
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

    function getTorch(flag) {
    if(flag==''){
    var requestData = {};
    }
    else if(flag=='P'){
      var requestData = {
        schemeCode: vm.schemeCode,
        flag: flag,
        panNum: vm.panNum,
      };
      }
      else if(flag=='B'){
            var requestData = {
              schemeCode: vm.schemeCode,
              flag: flag,
              panNum: vm.panNum,
              blaNo: vm.blaNo,
            };
            }
     else if(flag=='S'){
                 var requestData = {
                   schemeCode: vm.schemeCode,
                   flag: flag,
                   panNum: vm.panNum,
                   blaNo: vm.blaNo,
                   boSubsId: vm.boSubsId,
                 };
                 }
   else if(flag=='NOM'){
    var requestData = {
                      schemeCode: vm.schemeCode,
                      flag: 'S',
                      panNum: vm.panNum,
                      blaNo: vm.blaNo,
                      boSubsId: vm.boSubsId,
                    };
   }
      rbibondNomineeContinuationService.getAddDetails(requestData).then(
        function (response) {
          vm.loader = false;
          //                                                       vm.PanTorch = response.data.result;
          if(flag==''){
             vm.schemeList = response.data.result;
                    angular.element("#schemeModel").modal("toggle");

          }
          else if(flag=='P'){
          vm.PanTorch = response.data.result;
          angular.element("#panModel").modal("toggle");
          }
          else if(flag=='B'){
                    vm.blaDataList = response.data.result;
                    angular.element("#blaModel").modal("toggle");
                    }
          else if(flag=='S'){
                              vm.subscriptionList = response.data.result.subsDetailsList;
 for(var i =0;i<vm.subscriptionList.length;i++)
                              {
                               vm.subscriptionList[i].valueDate =  new Date(vm.subscriptionList[i].valueDate);
                               vm.subscriptionList[i].maturityDate =  new Date(vm.subscriptionList[i].maturityDate);
                               }
                                angular.element("#subscriptionModel").modal("toggle");
                              }else if(flag=='NOM'){
                              for(var i =0;i<response.data.result.nomineeDetList.length;i++)

                              {	  if(response.data.result.nomineeDetList[i].nomineeDob){
                               response.data.result.nomineeDetList[i].nomineeDob =  new Date(response.data.result.nomineeDetList[i].nomineeDob.split('-').reverse().join('-'));
                               }}
                               if(response.data.result.nomineeDetList.length>1){
                                vm.modeOfHoldingList = [
                                  {
                                    DESCRIPTION: "JOINT - JOINTLY",
                                    SUBCODE: "MH002",
                                  },
                                  {
                                    DESCRIPTION: "JOINT - ANYONE OR SURVIVOR",
                                    SUBCODE: "MH003",
                                  },
                                ];
                               }
                               else{
                                vm.modeOfHoldingList = [
                                  {
                                    DESCRIPTION: "SINGLE",
                                    SUBCODE: "MH001",
                                  },
                                ];
                               }
                                                 vm.nominationDetails1 = response.data.result.nomineeDetList;
                                                 console.log("Nominee Details List:", vm.nominationDetails1);

                              }
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }
vm.activeIndex = null;

 function getOtherBankDetailsTorch(flag,index) {
 vm.activeIndex = index;
          vm.loader = true;
          vm.searchSolId = "";
          var requestData = {};
          if(flag == 'bankName'){
          requestData.nonBankName = vm.customerMasterDetails[index].bankName;
//           requestData.bankFlag = "B";

          }
          if(flag == 'ifsc'){
          requestData.bankCode = vm.customerMasterDetails[index].bankCode;
          requestData.instIfscCode = vm.customerMasterDetails[index].ifscCode;
          }
          if(flag == 'micrCodeBank'){
          requestData.bankCode = vm.customerMasterDetails[index].bankCode;
          requestData.micrBankCode = vm.customerMasterDetails[index].micrCode;
          }
          if(flag == 'micrBranchCode'){
          requestData.bankCode = vm.customerMasterDetails[index].bankCode;
          requestData.micrBranchCode = vm.customerMasterDetails[index].branchCode;
          }
          rbibondNomineeContinuationService.getNonHdfcTorch(requestData).then(
            function (response) {
              vm.loader = false;
              vm.nonHdfcTorchList = response.data.result.torchValue;
              if(flag == 'bankName'){
              angular.element("#rbiCodeModal").modal("toggle");
              }else if(vm.customerMasterDetails[index].modeOfInterestPayment == 'MP003'){
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

    vm.setSelectedBank = function (selectedBank, index) {
          if (index !== undefined && vm.customerMasterDetails[index]) {
              vm.customerMasterDetails[index].bankCode = selectedBank.bankCode;
              vm.customerMasterDetails[index].bankName = selectedBank.bankName;
          }
      };
        vm.setSelectedBranch = function (selectedBranchIndex, index) {
              if (index !== undefined && vm.customerMasterDetails[index]) {
                  vm.customerMasterDetails[index].bankName = selectedBranchIndex.bankName;
                  vm.customerMasterDetails[index].ifscCode = selectedBranchIndex.instIfscCode;
                  vm.customerMasterDetails[index].micrCode = selectedBranchIndex.micrBankCode;
                  vm.customerMasterDetails[index].branchCode = selectedBranchIndex.micrBranchCode;
              }
          };

         /* vm.setSelectedBranchNeft = function (selectedBranchNeft, index) {
                        if (index !== undefined && vm.customerMasterDetails[index]) {
                            vm.customerMasterDetails[index].bankName = selectedBranchNeft.bankName;
                            vm.customerMasterDetails[index].instIfscCode = selectedBranchNeft.instIfscCode;
                            vm.customerMasterDetails[index].micrBranchCode = selectedBranchIndex.micrBranchCode;
                        }
                    };*/

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
      rbibondNomineeContinuationService.getReferenceData(requestData).then(
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
      rbibondNomineeContinuationService.getReferenceData(requestData).then(
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
      rbibondNomineeContinuationService.getReferenceData(requestData).then(
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
      rbibondNomineeContinuationService.getReferenceData(requestData).then(
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
      rbibondNomineeContinuationService.getReferenceData(requestData).then(
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

      //            if(vm.isAllSelected.indexOf(true) != -1){
      //                vm.reqField = false;
      //            }else{
      //                vm.reqField = true;
      //            }
    }

    function getState() {
      vm.listType = "STATES OF INDIA";
      var requestData = {
        referenceId: vm.listType,
      };
      rbibondNomineeContinuationService.getReferenceData(requestData).then(
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
       function getCbsCustFstJoinDetails(accountNumber,index) {
         vm.loader = true;
        /* const accountNumberPattern = /^\d{10,15}$/;

         if (!accountNumberPattern.test(accountNumber)) {
           alert("Account number must be numeric and between 10 to 15 digits.");
           return;
         }*/

         rbibondNomineeContinuationService.fetchCbsCustId(accountNumber).then(
           (response) => {
             vm.loader = false;

             if (response.data.statusCode === "200") {
                 if (response.data.result.responseString && response.data.result) {
                     if (response.data.result.status.replyText === "0") {
                         console.log("ReplyText is 0");
                         //fetch response
                                   vm.cbsDetail = response.data.result.responseString.customerDetailsDTO[0];
                                   if (vm.customerMasterDetails[index].bondHldDob === null) {
                                   vm.customerMasterDetails[index].bondHldDob = convertToDateObject(vm.formatCBSDate(vm.cbsDetail.datBirthCust));
                                   }
                                   vm.customerMasterDetails[index].bondHldPinCode=vm.cbsDetail.txtCustadrZip;
                                   vm.customerMasterDetails[index].holderFirstName = vm.cbsDetail.customerFullName;
                                   vm.customerMasterDetails[index].holderMiddleName = vm.cbsDetail.customerMiddleName;
                                   vm.customerMasterDetails[index].holderLastName = vm.cbsDetail.customerLastName;
                                   vm.customerMasterDetails[index].fatherName = vm.cbsDetail.fatherName;
                                   vm.customerMasterDetails[index].motherName = vm.cbsDetail.motherName;
                                   vm.customerMasterDetails[index].gender = vm.cbsDetail.txtCustSex;
//                                   vm.customerMasterDetails[index].isMinor = vm.cbsDetail.isMinor;
                                   vm.customerMasterDetails[index].bondHldAddOne = vm.cbsDetail.txtCustadrAdd1;
                                   vm.customerMasterDetails[index].bondHldAddTwo = vm.cbsDetail.txtCustadrAdd2;
                                   vm.customerMasterDetails[index].bondHldAddThree = vm.cbsDetail.txtCustadrAdd3;
                                   vm.customerMasterDetails[index].bondHldCity = vm.cbsDetail.namHoldadrCity;
                                   vm.customerMasterDetails[index].bondHolderPhoneNumber = vm.cbsDetail.rmPhoneNo;
                                   vm.customerMasterDetails[index].bondHldMobNum = vm.cbsDetail.refPhoneMobile;
                                   vm.customerMasterDetails[index].bondHldEmail = vm.cbsDetail.refCustEmail;
                                   vm.customerMasterDetails[index].operativeAccNo = vm.cbsDetail.operativeAccNo;
//                                   vm.customerMasterDetails[index].operativeAccBranchId = vm.cbsDetail.operativeBranchId;
//                                   vm.customerMasterDetails[index].aadhaarNo = vm.cbsDetail.codAadhaarNo;
                                   vm.showAccountValidationSuccess();
                     } else {
                       vm.showCBSServiceWarning(response.data.result.status.replyText);
                         console.log("ReplyText is not 0, it is: " + response.data.statusCode.result.status.replyText);
                     }
                 } else {
                       vm.showCBSServiceWarning(response.data.result.status.replyText);
                     console.log("Missing result or status in response.");
                 }
             } else {
                 vm.showCBSServiceWarning(response.data.result.status.replyText);
                 console.log("Status Code is not 200, it is: " + response.data.statusCode);
             }

           },
           function (error) {
             vm.loader = false;
             vm.errmsgSingle = "CBS Failure";
             vm.customerMasterDetails.cbsCustId = "";
               vm.showCBSServiceWarning(error.data.message);
           }
         );
       }
       // Warning alert for CBS service down
           function showCBSServiceWarning(msg) {
           if(msg == 'empty'){
           Swal.fire("Warning", "CBS service is down. Please try again after some time.", "warning");
         }else{

           Swal.fire("Warning", msg, "warning");

         }
           }
// Success alert for account validation
    function showAccountValidationSuccess() {
      Swal.fire("Success", "Data has been fetched successfully!", "success");
    }
 function formatCBSDate(dateStr) {
        // Extract day, month, and year from the string
        const day = dateStr.substring(6, 8);   // Extracts "06"
        const month = dateStr.substring(4, 6); // Extracts "02"
        const year = dateStr.substring(0, 4);  // Extracts "1988"

        // Return the formatted date as DD/MM/YYYY
        return `${day}/${month}/${year}`;
    }
      function prevStep() {
          var $active = $(".from_wrapper .nav-tabs li.active");
          prevTab($active);
        }

        function prevTab(elem) {
          $(elem).prev().find('a[data-bs-toggle="tab"]').click();
        }

    function getAddressOne(pinCode,index) {
      vm.loader = true;

      // Include the pin code in the requestData object
      var requestData = {
        pinCode: pinCode,
      };

      // Call the fetchState method and pass the requestData with pinCode
      rbibondNomineeContinuationService
        .fetchState(requestData)
        .then(function (response) {
          vm.loader = false;
          // Check if response contains the expected data
          if (response.data.result) {
            vm.cbsDetail = response.data.result[0];
            vm.customerMasterDetails[index].bondHldState = vm.cbsDetail.bondHldState;
            vm.customerMasterDetails[index].bondHldCity = vm.cbsDetail.bondHldCity;
          } else {
            vm.errmsgSingle = "No data found for the given pin code Enter Manually";
            vm.customerMasterDetails[index].bondHldState='';
             vm.customerMasterDetails[index].bondHldCity ='';
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
          rbibondNomineeContinuationService
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
                  rbibondNomineeContinuationService
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
      rbibondNomineeContinuationService
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
      rbibondNomineeContinuationService
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
          rbibondNomineeContinuationService
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
              rbibondNomineeContinuationService
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
                      rbibondNomineeContinuationService
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
          rbibondNomineeContinuationService
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
          rbibondNomineeContinuationService
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
      rbibondNomineeContinuationService.fetchCbsCustId(vm.custId).then(function (response) {
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
        rbibondNomineeContinuationService.fetchBlaAccNoDetails(requestData).then(
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
        rbibondNomineeContinuationService.fetchBlaAccNoDetails(requestData).then(
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

    function getGuardianDetails(flag,index) {
      vm.loader = true;

      if (flag == "select") {
        var requestData = {
          schemeCode: vm.schemeCode,
        };
        rbibondNomineeContinuationService.fetchGuardianDetails(requestData).then(
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
        rbibondNomineeContinuationService.fetchGuardianDetails(requestData).then(
          (response) => {
            vm.loader = false;
            vm.guardianDetail = response.data.result[0];
            vm.guardDet[index].guardianId = vm.customerMasterDetails.guardianId;
            vm.guardDet[index].guardFName = vm.guardianDetail.guardianFirstName;
            vm.guardDet[index].guardMName = vm.guardianDetail.guardianMiddleName;
            vm.guardDet[index].guardLName = vm.guardianDetail.guardianLastName;
            vm.guardDet[index].relationWithMinor = vm.guardianDetail.relationWithMinor;
            vm.guardianDateOfBirth = convertDateFormat(vm.guardianDetail.guardianDateOfBirth);
            vm.guardDet[index].guardianDateOfBirth = vm.guardianDateOfBirth;

            vm.guardDet[index].guardianCommunicationAddress = vm.guardianDetail.guardianCommunicationAddress;
            vm.guardDet[index].guardMobileNo = vm.guardianDetail.mobileNo;
            vm.guardDet[index].guardEmailId = vm.guardianDetail.email;
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
      rbibondNomineeContinuationService.fetchScssCustId(vm.scsscustId, vm.holderNum).then(function (response) {
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
      rbibondNomineeContinuationService.blaNo(requestData).then(
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
      rbibondNomineeContinuationService.getblaNoInq(requestData).then(
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
     function getblaNoVer() {
          vm.loader = true;

          var requestData = {
            schemeCode: vm.schemeCode,
            schemeName: vm.schemeName,
            panNum: vm.panNum,
            bondHolderName: vm.bondHolderName,
            blaNo: vm.blaNo,
          };
          rbibondNomineeContinuationService.getblaNoInq(requestData).then(
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
  function getboSubsIdAdd() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        schemeName: vm.schemeName,
        panNum: vm.panNum,
        bondHolderName: vm.bondHolderName,
        blaNo: vm.blaNo,
        boSubsId: vm.boSubsId,
      };
      rbibondNomineeContinuationService.getboSubsIdMod(requestData).then(
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
 function getInputNomineeAdd() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        panNum: vm.panNum,
        blaNo: vm.blaNo,
        boSubsId: vm.boSubsId,
        mofFlag : 'S'
      };
      rbibondNomineeContinuationService.getAddDetails(requestData).then(
        function (response) {
            vm.loader = false;

         //          vm.subscriptionList = response.data.result.torchValue;

         				vm.nominationDetails1=[];

         console.log(vm.nominationDetails1.length > 0);
                   vm.nominationDetails1 = response.data.result.nomineeDetList;
         			  console.log(vm.nominationDetails1.length > 0);

        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }
    function getInputNomineeVer() {
          vm.loader = true;

          var requestData = {
            schemeCode: vm.schemeCode,
            schemeName: vm.schemeName,
            panNum: vm.panNum,
            bondHolderName: vm.bondHolderName,
            blaNo: vm.blaNo,
            boSubsId: vm.boSubsId,
          };
          rbibondNomineeContinuationService.getboSubsIdMod(requestData).then(
            function (response) {
                vm.loader = false;
               if(vm.boSubsId){

             //          vm.subscriptionList = response.data.result.torchValue;

             				vm.nominationDetails1=[];
                                  let nomineeDetailsList = [
                                       {
                                           "firstName": "Geetha",
                                           "dateOfBirthOfMinor": "20/05/2010",
                                           "panNo": "ABCDE1234F",
                                           "percentage": "50",
                                           "amount": "500"
                                       },{
                                         "firstName": "SNEHA",
                                         "dateOfBirthOfMinor": "20/05/2009",
                                         "panNo": "ASCDE3234F",
                                         "percentage": "50",
                                         "amount": "500"
                                       }
                                   ];
             console.log(vm.nominationDetails1.length > 0);
                       vm.nominationDetails1 = nomineeDetailsList;
             			  console.log(vm.nominationDetails1.length > 0);
                       }
            },
            function (error) {
              vm.loader = false;
              handleErrorResponse(error);
            }
          );
        }
 function getInputNomineeInq() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        schemeName: vm.schemeName,
        panNum: vm.panNum,
        bondHolderName: vm.bondHolderName,
        blaNo: vm.blaNo,
        boSubsId: vm.boSubsId,
      };
      rbibondNomineeContinuationService.getboSubsIdMod(requestData).then(
        function (response) {
            vm.loader = false;
           if(vm.boSubsId){

         //          vm.subscriptionList = response.data.result.torchValue;

         				vm.nominationDetails1=[];
                              let nomineeDetailsList = [
                                   {
                                       "blaNo": "RBI00080000209",
                                       "boSubsId": "101BS00002663"
                                   },{
                                     "blaNo": "RBI00080000210",
                                     "boSubsId": "101BS00002664"
                                   }
                               ];
         console.log(vm.nominationDetails1.length > 0);
                   vm.nominationDetails1 = nomineeDetailsList;
         			  console.log(vm.nominationDetails1.length > 0);
                   }
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }
function getInputNomineeMod() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        schemeName: vm.schemeName,
        panNum: vm.panNum,
        bondHolderName: vm.bondHolderName,
        blaNo: vm.blaNo,
        boSubsId: vm.boSubsId,
      };
      rbibondNomineeContinuationService.getboSubsIdMod(requestData).then(
        function (response) {
            vm.loader = false;
           if(vm.boSubsId){

         //          vm.subscriptionList = response.data.result.torchValue;

         				vm.nominationDetails1=[];
                              let nomineeDetailsList = [
                                   {
                                       "blaNo": "RBI00080000209",
                                       "boSubsId": "101BS00002663"
                                   },{
                                     "blaNo": "RBI00080000210",
                                     "boSubsId": "101BS00002664"
                                   }
                               ];
         console.log(vm.nominationDetails1.length > 0);
                   vm.nominationDetails1 = nomineeDetailsList;
         			  console.log(vm.nominationDetails1.length > 0);
                   }
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function getboSubsIdMod() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        schemeName: vm.schemeName,
        panNum: vm.panNum,
        bondHolderName: vm.bondHolderName,
        blaNo: vm.blaNo,
        boSubsId: vm.boSubsId,
      };
      rbibondNomineeContinuationService.getboSubsIdMod(requestData).then(
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

    function getboSubsIdInq() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        schemeName: vm.schemeName,
        panNum: vm.panNum,
        bondHolderName: vm.bondHolderName,
        blaNo: vm.blaNo,
        boSubsId: vm.boSubsId,
      };
      rbibondNomineeContinuationService.getboSubsIdInq(requestData).then(
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
   function getboSubsIdVer() {
         vm.loader = true;

         var requestData = {
           schemeCode: vm.schemeCode,
           schemeName: vm.schemeName,
           panNum: vm.panNum,
           bondHolderName: vm.bondHolderName,
           blaNo: vm.blaNo,
           boSubsId: vm.boSubsId,
         };
         rbibondNomineeContinuationService.getboSubsIdInq(requestData).then(
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
vm.removeNullValues = removeNullValues;
function removeNullValues(obj) {
    return Object.fromEntries(
        Object.entries(obj).filter(([_, value]) => value !== null)
    );
}
    /* Add Submit */
    function addcustomerMaster() {
      var proceed = false;
      var message = "";
      if (vm.isNominee === "Y") {
        if (vm.total !== 100) {
          proceed = false;
          message = "Sum of sharing percentage should be equal to 100";
          Swal.fire({
            title: "Warning",
            text: message,
            type: "warning",
            confirmButtonText: "OK",
            confirmButtonClass: "btn-primary",
          });
          return;
        } else {
          proceed = true;
        }
      } else {
        proceed = true;
      }

      // If validation passes, show confirmation dialog
      if (proceed) {
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


try {
    if (vm.customerMasterDetails && Array.isArray(vm.customerMasterDetails)) {
        vm.customerMasterDetails.forEach((customer) => {
            // Formatting dates
            customer.bondHldDob = $filter("date")(customer.bondHldDob, "dd-MMM-yyyy");
            customer.guardianDateOfBirth = $filter("date")(customer.guardianDateOfBirth, "dd-MMM-yyyy");
            // Converting boolean values to "Y" or "N"
            customer.birthCertificate = customer.birthCertificate ? "Y" : "N";
            customer.passport = customer.passport ? "Y" : "N";
            customer.seniorCitizenCard = customer.seniorCitizenCard ? "Y" : "N";
            customer.voterIdCard = customer.voterIdCard ? "Y" : "N";
            customer.panCard = customer.panCard ? "Y" : "N";
            customer.drivingLicence = customer.drivingLicence ? "Y" : "N";
            customer.rationCard = customer.rationCard ? "Y" : "N";
            customer.otherDocuments = customer.otherDocuments ? "Y" : "N";
        });
    }
} catch (e) {
    console.error("Error updating customer details: ", e);
}


vm.DetailsList = vm.customerMasterDetails.map(item => item[0] ?? item);
if(vm.blaFlag == 'Y'){
// Separate bond holders and nominees
var bondHolders = [];
var nominees = [];

vm.DetailsList.forEach(item => {
    // Check if item is a nominee (has nomineeId) or a bond holder (has schemeCode or modeOfHolding)
    if (item.nomineeId) {
        // This is a nominee
        nominees.push(item);
    } else if (item.schemeCode || item.modeOfHolding) {
        // This is a bond holder
        bondHolders.push(item);
    }
});

// Match nominees with bond holders based on panNum
bondHolders.forEach(bondHolder => {
    if (bondHolder.panNum) {
        // Find matching nominee by panNum
        var matchingNominee = nominees.find(nominee => nominee.panNum === bondHolder.panNum);
        if (matchingNominee && matchingNominee.nomineeId) {
            bondHolder.nomineeId = matchingNominee.nomineeId;
        }
    }
});
vm.DetailsList = bondHolders;
}
// Filter out nominees - only include bond holders in the final list
//            var objTwo = Object.fromEntries(Object.entries(vm.subsDet));
//            var objThree = Object.fromEntries(Object.entries(vm.nominationDetails));




vm.index = 0;
angular.forEach(vm.DetailsList, function (item) {
                // Remove $$hashKey from each nominee object
                  delete item.subsDetailsList;
                  delete item.blaDetailsList;
                delete item.nomineeDetList;
                if (item.flag && item.flag.toLowerCase() === "existing") {
                item.blaFlag = "Y";
                }else{
                item.blaFlag = "N";
                }
                if (null!=vm.nominationDetails1[vm.index] && null!=vm.nominationDetails1[vm.index].panNum) {
                    item.panNum = vm.nominationDetails1[vm.index].panNum;
                }
                item.bondHolderDob = $filter("date")(item.bondHolderDob, "dd-MMM-yyyy");
                vm.index++;
              });
         /*var nomineeHandled=false;
         angular.forEach(vm.DetailsList, function (item) {
               if (!nomineeHandled && vm.DetailsList.length === 1) {
               vm.DetailsList;
               nomineeHandled = true;
               }else if (!nomineeHandled && vm.DetailsList.length === 2) {
               const first = vm.DetailsList[0];
               const second = vm.DetailsList[1];
               if (first.blaFlag === "Y") {
               const lastItem = vm.DetailsList[vm.DetailsList.length - 1];
               if (lastItem?.nomineeId) {
                   vm.DetailsList[0].nomineeId = lastItem.nomineeId;
               }
               vm.DetailsList.pop();
               nomineeHandled = true;
               }else if(first.blaFlag === "N" && second.blaFlag === "N"){
               vm.DetailsList=[];
               vm.DetailsList.push(first,second);
               nomineeHandled = true;
               }
               }else if (!nomineeHandled && vm.DetailsList.length === 3) {
               const first = vm.DetailsList[0];
                const second = vm.DetailsList[1];
                const third = vm.DetailsList[2];
               if (second.blaFlag === "Y" && first.blaFlag === "N") {
                   if (third?.nomineeId) {
                       vm.DetailsList[1].nomineeId = third.nomineeId;
                   }
                   vm.DetailsList.pop();
                   nomineeHandled = true;
               } else if (first.blaFlag === "Y" &&second.blaFlag === "N") {
                   if (third?.nomineeId) {
                       vm.DetailsList[0].nomineeId = third.nomineeId;
                   }
                   vm.DetailsList.pop();
                   nomineeHandled = true;
               }else if(first.blaFlag === "N" &&second.blaFlag === "N"&& third.blaFlag === "N"){
                  vm.DetailsList=[];
                  vm.DetailsList.push(first,second,third);
                  nomineeHandled = true;
               }
              }else if (!nomineeHandled && vm.DetailsList.length === 4) {
                 const first = vm.DetailsList[0];
                 const second = vm.DetailsList[1];
                 const third = vm.DetailsList[2];
                 const fourth = vm.DetailsList[3];
                 if (first.blaFlag === "Y" && second.blaFlag === "Y") {
                     vm.DetailsList=[];
                     if(first.panNum===third.panNum){
                     first.nomineeId=third.nomineeId;
                     }else{
                     first.nomineeId=fourth.nomineeId;
                     }
                     if(second.panNum===fourth.panNum){
                     second.nomineeId=fourth.nomineeId;
                     }else{
                     second.nomineeId=third.nomineeId;
                     }
                     vm.DetailsList.push(first,second);
                     nomineeHandled = true;
                 } else if (first.blaFlag === "Y" && second.blaFlag === "N" && third.blaFlag === "N") {
                     vm.DetailsList=[];
                     if(first.panNum===fourth.panNum){
                      first.nomineeId=fourth.nomineeId;
                     }
                     vm.DetailsList.push(first,second,third);
                     nomineeHandled = true;
                 } else if (first.blaFlag === "N" && second.blaFlag === "Y" && third.blaFlag === "N") {
                      vm.DetailsList=[];
                      if(second.panNum===fourth.panNum){
                        second.nomineeId=fourth.nomineeId;
                       }
                      vm.DetailsList.push(first,second,third);
                      nomineeHandled = true;
                  } else if (first.blaFlag === "N" && second.blaFlag === "N" && third.blaFlag === "Y") {
                   vm.DetailsList=[];
                   if(third.panNum===fourth.panNum){
                     third.nomineeId=fourth.nomineeId;
                    }
                   vm.DetailsList.push(first,second,third);
                   nomineeHandled = true;
                 }else if (first.blaFlag === "N" && second.blaFlag === "N" && third.blaFlag === "N" && fourth.blaFlag=== "N") {
                       vm.DetailsList=[];
                       vm.DetailsList.push(first,second,third,fourth);
                       nomineeHandled = true;
                   }
                }
                if (!nomineeHandled && vm.DetailsList.length === 5) {
                    const first = vm.DetailsList[0];
                    const second = vm.DetailsList[1];
                    const third = vm.DetailsList[2];
                    const fourth = vm.DetailsList[3];
                    const fifth = vm.DetailsList[4];
                    if (first.blaFlag === "Y" && second.blaFlag === "Y" && third.blaFlag === "N") {
                        vm.DetailsList = [];
                        if (first.panNum === fourth.panNum) {
                            first.nomineeId = fourth.nomineeId;
                        } else {
                            first.nomineeId = fifth.nomineeId;
                        }
                        if (second.panNum === fifth.panNum) {
                            second.nomineeId = fifth.nomineeId;
                        } else {
                            second.nomineeId = fourth.nomineeId;
                        }
                        vm.DetailsList.push(first, second, third);
                        nomineeHandled = true;
                    }if (first.blaFlag === "Y" && second.blaFlag === "N" && third.blaFlag === "Y") {
                         vm.DetailsList = [];
                         if (first.panNum === fourth.panNum) {
                             first.nomineeId = fourth.nomineeId;
                         } else {
                             first.nomineeId = fifth.nomineeId;
                         }
                         if (third.panNum === fifth.panNum) {
                             third.nomineeId = fifth.nomineeId;
                         } else {
                             third.nomineeId = fourth.nomineeId;
                         }
                         vm.DetailsList.push(first, second, third);
                         nomineeHandled = true;
                     }if (first.blaFlag === "N" && second.blaFlag === "Y" && third.blaFlag === "Y") {
                       vm.DetailsList = [];
                       if (second.panNum === fourth.panNum) {
                           second.nomineeId = fourth.nomineeId;
                       } else {
                           second.nomineeId = fifth.nomineeId;
                       }
                       if (third.panNum === fifth.panNum) {
                           third.nomineeId = fifth.nomineeId;
                       } else {
                           third.nomineeId = fourth.nomineeId;
                       }
                       vm.DetailsList.push(first, second, third);
                       nomineeHandled = true;
                   } else if (first.blaFlag === "N" && second.blaFlag === "N" && third.blaFlag === "N" && fourth.blaFlag === "N" && fifth.blaFlag === "N") {
                        vm.DetailsList = [];
                        vm.DetailsList.push(first, second, third, fourth, fifth);
                        nomineeHandled = true;
                    }
                  }if (!nomineeHandled && vm.DetailsList.length === 6) {
                       const first = vm.DetailsList[0];
                       const second = vm.DetailsList[1];
                       const third = vm.DetailsList[2];
                       const fourth = vm.DetailsList[3]; // nominee
                       const fifth = vm.DetailsList[4];  // nominee
                       const sixth = vm.DetailsList[5];  // nominee
                   if (first.blaFlag === "Y" && second.blaFlag === "Y" && third.blaFlag === "Y") {
                       vm.DetailsList = [];
                       first.nomineeId = (first.panNum === fourth.panNum) ? fourth.nomineeId :(first.panNum === fifth.panNum) ? fifth.nomineeId :sixth.nomineeId;
                       second.nomineeId = (second.panNum === fifth.panNum) ? fifth.nomineeId :(second.panNum === sixth.panNum) ? sixth.nomineeId : fourth.nomineeId;
                       third.nomineeId = (third.panNum === fifth.panNum) ? fifth.nomineeId :(third.panNum === sixth.panNum) ? sixth.nomineeId :fourth.nomineeId;
                       vm.DetailsList.push(first, second, third);
                       nomineeHandled = true;
                  } else if (first.blaFlag === "Y" && second.blaFlag === "Y" && third.blaFlag === "N" && fourth.blaFlag === "N") {
                      vm.DetailsList = [];
                      first.nomineeId = (first.panNum === fifth.panNum) ? fifth.nomineeId :sixth.nomineeId;
                      second.nomineeId = (second.panNum === sixth.panNum) ? sixth.nomineeId :fifth.nomineeId;
                      vm.DetailsList.push(first, second, third, fourth);
                      nomineeHandled = true;
                 }else if (first.blaFlag === "N" && second.blaFlag === "N" && third.blaFlag === "Y" && fourth.blaFlag === "Y") {
                    vm.DetailsList = [];
                    third.nomineeId = (third.panNum === fifth.panNum) ? fifth.nomineeId :sixth.nomineeId;
                    fourth.nomineeId = (fourth.panNum === sixth.panNum) ? sixth.nomineeId : fifth.nomineeId;
                    vm.DetailsList.push(first, second, third, fourth);
                    nomineeHandled = true;
                 }else if (first.blaFlag === "N" && second.blaFlag === "Y" && third.blaFlag === "Y" && fourth.blaFlag === "N") {
                    vm.DetailsList = [];
                    second.nomineeId = (second.panNum === fifth.panNum) ? fifth.nomineeId :sixth.nomineeId;
                    third.nomineeId = (third.panNum === sixth.panNum) ? sixth.nomineeId : fifth.nomineeId;
                    vm.DetailsList.push(first, second, third, fourth);
                    nomineeHandled = true;
                 }else if (first.blaFlag === "Y" && second.blaFlag === "N" && third.blaFlag === "N" && fourth.blaFlag === "Y") {
                  vm.DetailsList = [];
                  first.nomineeId = (first.panNum === fifth.panNum) ? fifth.nomineeId :sixth.nomineeId;
                  fourth.nomineeId = (fourth.panNum === sixth.panNum) ? sixth.nomineeId : fifth.nomineeId;
                  vm.DetailsList.push(first, second, third, fourth);
                  nomineeHandled = true;
                }else if (first.blaFlag === "N" && second.blaFlag === "N" && third.blaFlag === "N" && fourth.blaFlag === "N" && fifth.blaFlag === "N" && sixth.blaFlag === "N") {
                  vm.DetailsList = [first, second, third, fourth, fifth, sixth];
                  nomineeHandled = true;
              }
              }if (!nomineeHandled && vm.DetailsList.length === 7) {
                   const first = vm.DetailsList[0];
                   const second = vm.DetailsList[1];
                   const third = vm.DetailsList[2];
                   const fourth = vm.DetailsList[3];
                   const fifth = vm.DetailsList[4];   // nominee
                   const sixth = vm.DetailsList[5];   // nominee
                   const seventh = vm.DetailsList[6]; // nominee
                   if (first.blaFlag === "Y" && second.blaFlag === "Y" && third.blaFlag === "Y" && fourth.blaFlag === "N") {
                       vm.DetailsList = [];
                       first.nomineeId = (first.panNum === fourth.panNum) ? fourth.nomineeId :
                                         (first.panNum === fifth.panNum) ? fifth.nomineeId :
                                         (first.panNum === sixth.panNum) ? sixth.nomineeId : seventh.nomineeId;
                       second.nomineeId = (second.panNum === fifth.panNum) ? fifth.nomineeId :
                                          (second.panNum === sixth.panNum) ? sixth.nomineeId :
                                          (second.panNum === seventh.panNum) ? seventh.nomineeId : fourth.nomineeId;
                       third.nomineeId = (third.panNum === sixth.panNum) ? sixth.nomineeId :
                                         (third.panNum === seventh.panNum) ? seventh.nomineeId :
                                         (third.panNum === fourth.panNum) ? fourth.nomineeId : fifth.nomineeId;
                       vm.DetailsList.push(first, second, third, fourth);
                       nomineeHandled = true;
                   } else if (first.blaFlag === "Y" && second.blaFlag === "Y" && third.blaFlag === "N" && fourth.blaFlag === "Y") {
                     vm.DetailsList = [];
                     first.nomineeId = (first.panNum === fourth.panNum) ? fourth.nomineeId :
                                       (first.panNum === fifth.panNum) ? fifth.nomineeId :
                                       (first.panNum === sixth.panNum) ? sixth.nomineeId : seventh.nomineeId;
                     second.nomineeId = (second.panNum === fifth.panNum) ? fifth.nomineeId :
                                        (second.panNum === sixth.panNum) ? sixth.nomineeId :
                                        (second.panNum === seventh.panNum) ? seventh.nomineeId : fourth.nomineeId;
                     fourth.nomineeId = (fourth.panNum === sixth.panNum) ? sixth.nomineeId :
                                       (fourth.panNum === seventh.panNum) ? seventh.nomineeId :
                                       (fourth.panNum === fourth.panNum) ? fourth.nomineeId : fifth.nomineeId;
                     vm.DetailsList.push(first, second, third, fourth);
                     nomineeHandled = true;
                 }else if (first.blaFlag === "N" && second.blaFlag === "Y" && third.blaFlag === "Y" && fourth.blaFlag === "Y") {
                    vm.DetailsList = [];
                    second.nomineeId = (second.panNum === fifth.panNum) ? fifth.nomineeId :
                                       (second.panNum === sixth.panNum) ? sixth.nomineeId :
                                       (second.panNum === seventh.panNum) ? seventh.nomineeId : fourth.nomineeId;
                    third.nomineeId = (third.panNum === sixth.panNum) ? sixth.nomineeId :
                                      (third.panNum === seventh.panNum) ? seventh.nomineeId :
                                      (third.panNum === fourth.panNum) ? fourth.nomineeId : fifth.nomineeId;
                    fourth.nomineeId = (fourth.panNum === sixth.panNum) ? sixth.nomineeId :
                                      (fourth.panNum === seventh.panNum) ? seventh.nomineeId :
                                      (fourth.panNum === fourth.panNum) ? fourth.nomineeId : fifth.nomineeId;
                    vm.DetailsList.push(first, second, third, fourth);
                    nomineeHandled = true;
                } else if ([first, second, third, fourth, fifth, sixth, seventh].every(i => i.blaFlag === "N")) {
                       vm.DetailsList = [first, second, third, fourth, fifth, sixth, seventh];
                       nomineeHandled = true;
                   }
               }if (!nomineeHandled && vm.DetailsList.length === 8) {
                    const first = vm.DetailsList[0];
                    const second = vm.DetailsList[1];
                    const third = vm.DetailsList[2];
                    const fourth = vm.DetailsList[3];
                    const fifth = vm.DetailsList[4];   // nominee
                    const sixth = vm.DetailsList[5];   // nominee
                    const seventh = vm.DetailsList[6]; // nominee
                    const eighth = vm.DetailsList[7];  // nominee
                    if (first.blaFlag === "Y" && second.blaFlag === "Y" && third.blaFlag === "Y" && fourth.blaFlag === "Y") {
                        vm.DetailsList = [];
                        first.nomineeId = (first.panNum === fifth.panNum) ? fifth.nomineeId :
                                          (first.panNum === sixth.panNum) ? sixth.nomineeId :
                                          (first.panNum === seventh.panNum) ? seventh.nomineeId : eighth.nomineeId;
                        second.nomineeId = (second.panNum === fifth.panNum) ? fifth.nomineeId :
                                           (second.panNum === sixth.panNum) ? sixth.nomineeId :
                                           (second.panNum === seventh.panNum) ? seventh.nomineeId : eighth.nomineeId;
                        third.nomineeId = (third.panNum === fifth.panNum) ? fifth.nomineeId :
                                          (third.panNum === sixth.panNum) ? sixth.nomineeId :
                                          (third.panNum === seventh.panNum) ? seventh.nomineeId : eighth.nomineeId;
                        fourth.nomineeId = (fourth.panNum === fifth.panNum) ? fifth.nomineeId :
                                          (fourth.panNum === sixth.panNum) ? sixth.nomineeId :
                                          (fourth.panNum === seventh.panNum) ? seventh.nomineeId : eighth.nomineeId;
                        vm.DetailsList.push(first, second, third, fourth);
                        nomineeHandled = true;
                    } else if ([first, second, third, fourth, fifth, sixth, seventh, eighth].every(i => i.blaFlag === "N")) {
                        vm.DetailsList = [first, second, third, fourth, fifth, sixth, seventh, eighth];
                        nomineeHandled = true;
                    }
                }
});*/

 angular.forEach(vm.subsDet, function (item) {
                // Remove $$hashKey from each nominee object
                delete item.subsDetailsList;
                delete item.blaDetailsList;
                delete item.nomineeDetList;
                // Format date of birth of minor
                item.accountOpenDate = $filter("date")(item.accountOpenDate, "dd-MMM-yyyy");
                        let dateObj = new Date(item.maturityDate.split("-").reverse().join("-"));

                item.maturityDate = $filter("date")(dateObj, "dd-MMM-yyyy");

                item.valueDate = $filter("date")(item.valueDate, "dd-MMM-yyyy");
                if (item.instrumentDate) {
                    let instrumentDateObj = new Date(item.instrumentDate);
                    item.instrumentDate = !isNaN(instrumentDateObj.getTime())
                        ? $filter("date")(instrumentDateObj, "dd-MMM-yyyy")
                        : null;
                }
                if (item.zoneDate) {
                    let zoneDateObj = new Date(item.zoneDate);
                    item.zoneDate = !isNaN(zoneDateObj.getTime())
                        ? $filter("date")(zoneDateObj, "dd-MMM-yyyy")
                        : null;
                }
                item.schemeCode = vm.schemeCode;
              });
vm.resultArray.push(vm.DetailsList);
var requestData = removeNullValues({
    blaDetailsList: vm.DetailsList || [],
    schemeCode: vm.schemeCode || "",
    modeOfHolding: vm.modeOfHolding || "",
    blaNo: vm.blaNo || "",
    boSubsId: vm.boSubsId || "",
    subsDetailsList: vm.subsDet || []
//    nomineeDetList: vm.nominationDetails1.length > 0 ? vm.nominationDetails1 : null
});
         /*   requestData.nomineeDetList = [];
            angular.forEach(vm.subsDet, function (item1) {
            if (item1.isNominee == "N") {
              requestData.nomineeDetList = null;
            } else {
              // Process nomination details
              angular.forEach(vm.nominationDetails, function (item) {
                // Remove $$hashKey from each nominee object
                delete item.$$hashKey;
                delete item.dateOfBirthOptions;
                // Format date of birth of minor
                item.dateOfBirthOfMinor = $filter("date")(item.dateOfBirthOfMinor, "dd-MMM-yyyy");
                  requestData.nomineeDetList.push({
                    nomineeId: item1.nomineeId
                  });
              });
            }
            });*/
function removeNullValues(obj) {
    return Object.fromEntries(
        Object.entries(obj).filter(([_, value]) => value !== null)
    );
}
console.log("Filtered requestData:", JSON.stringify(requestData, null, 2));
            rbibondNomineeContinuationService.addSubmit(requestData).then(
              (response) => {
               vm.loader = false;
                vm.responseResult = response.result;
                var message = "Bond Nominee Continuation Added Successfully";
                // === Format BLA No(s) ===
                if (vm.responseResult && vm.responseResult.blaNo) {
                  var blaArray = vm.responseResult.blaNo
                    .split(",")
                    .map(function(item) {
                      return item.trim();
                    })
                    .filter(function(item) {
                      return item !== "";
                    });
                  if (blaArray.length > 0) {
                    message += "\n\n" + formatLines(blaArray, "BLA No(s)");
                  }
                }
                // === Format Subscription ID(s) ===
                if (vm.responseResult && vm.responseResult.boSubsId) {
                  var subArray = vm.responseResult.boSubsId
                    .split(",")
                    .map(function(item) {
                      return item.trim();
                    })
                    .filter(function(item) {
                      return item !== "";
                    });
                  if (subArray.length > 0) {
                    message += "\n\n" + formatLines(subArray, "Subscription ID(s)");
                  }
                }
                // === Show final alert ===
                Swal.fire("Success!", message, "success");
                $state.go("rbi_bond_nominee_continuation.list");
              },
              function (error) {
                vm.loader = false;
                handleErrorResponse(error);
                $state.go("rbi_bond_nominee_continuation.list");
              }
            );
        }  }
        );
      }
    }

// === Helper to group array into lines of 2 items ===
                function formatLines(array, label) {
                  var result = label + ":";
                  for (var i = 0; i < array.length; i += 2) {
                    var line = array[i];
                    if (array[i + 1]) {
                      line += ", " + array[i + 1];
                    }
                    result += "\n" + line;
                  }
                  return result;
                }
    function handleHdfcSelection(index) {
      // Clear the operative account number when selection changes
      if (vm.customerMasterDetails[index].hdfcOtherBank === "N") {
        vm.customerMasterDetails[index].operativeAccNum = "";
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
    //Modify Select
    function getModifySelectDetails(flag) {
      vm.loader = true;

      if (flag == "torch") {
        var requestData = {
          schemeCode: vm.schemeCode,
          schemeName: vm.schemeName,
          panNum: vm.panNum,
          bondHolderName: vm.bondHolderName,
        };
        rbibondNomineeContinuationService.modifySelect(requestData).then(
          function (response) {
            vm.loader = false;
            vm.blaDataList = response.data.result;
            angular.element("#blaModel").modal("toggle");
          },
          function (error) {
            vm.loader = false;
            handleErrorResponse(error);
          }
        );
      } else if (flag == "subTorch") {
        var requestData = {
          schemeCode: vm.schemeCode,
          blaNo: vm.blaNo,
          schemeName: vm.schemeName,
          panNum: vm.panNum,
          bondHolderName: vm.bondHolderName,
        };
        rbibondNomineeContinuationService.modifySelect(requestData).then(
          function (response) {
            vm.loader = false;
            vm.subscriptionList = response.data.result;
            angular.element("#subscriptionModel").modal("toggle");
          },
          function (error) {
            vm.loader = false;
            handleErrorResponse(error);
          }
        );
      } else {
        var requestData = {
          schemeCode: vm.schemeCode,
          blaNo: vm.blaNo,
          boSubsId: vm.boSubsId,
        };

        rbibondNomineeContinuationService.modifySelect(requestData).then(
          (response) => {
            vm.loader = false;
            vm.getDetails = response.data.result;

            if (vm.getDetails.masterDetailsValueModel.length == 1) {
              vm.titleText = "BOND HOLDER DETAILS";
            } else {
              vm.titleText = "FIRST JOINT BOND HOLDER DETAILS";

              vm.tempArray = [];
              vm.tempArray.push("0");
              vm.tempArray.push("1");
              if (vm.getDetails.masterDetailsValueModel.length > 2) {
                vm.tempArray.push("2");
              }

              angular.forEach(vm.getDetails.masterDetailsValueModel, function (item) {
                // Directly assign values based on C048.oldValue
                if (item.C048.oldValue == "1") {
                  vm.tempArray[0] = item; // Assign item to index 0
                }
                if (item.C048.oldValue == "2") {
                  vm.tempArray[1] = item; // Assign item to index 1
                }
                if (item.C048.oldValue == "3") {
                  vm.tempArray[2] = item; // Assign item to index 2
                }
              });

              // Filter the array to remove '0', '1', '2' and only keep items
              var value = vm.tempArray.filter(function (value) {
                // Only return actual items, remove '0', '1', '2' placeholders
                return value !== "0" && value !== "1" && value !== "2";
              });

              // Update master details with the filtered value
              vm.getDetails.masterDetailsValueModel = value;

              if (vm.getDetails.guardianDetailsValueModel.length == "2") {
                vm.tempArray1 = [];
                vm.tempArray1.push("0");
                vm.tempArray1.push("1");
                if (vm.getDetails.guardianDetailsValueModel.length > 2) {
                  vm.tempArray1.push("2");
                }
                // Processing guardian details
                angular.forEach(vm.getDetails.guardianDetailsValueModel, function (item) {
                  if (item.C003.oldValue == "1") {
                    vm.tempArray1.splice(0, "0", item);
                  }
                  if (item.C003.oldValue == "2") {
                    vm.tempArray1.splice(1, "1", item);
                  }
                  if (item.C003.oldValue == "3") {
                    vm.tempArray1.splice(2, "2", item);
                  }
                });

                var value1 = vm.tempArray1.filter((value1) => {
                  if (value1 == "0") {
                  } else if (value1 == "1") {
                  } else if (value1 == "2") {
                  } else {
                    return value1;
                  }
                });

                // Updating the guardian details
                vm.getDetails.guardianDetailsValueModel = vm.tempArray1;
              }
            }

            vm.modeOfHolding = vm.getDetails.masterDetailsValueModel[0].C003.oldValue;

            vm.customerMasterDetails = vm.getDetails.masterDetailsValueModel[0];
            vm.customerMasterDetailsTwo = vm.getDetails.masterDetailsValueModel[1];
            vm.customerMasterDetailsThree = vm.getDetails.masterDetailsValueModel[2];
            vm.guardianDetailsValueModel = vm.getDetails.guardianDetailsValueModel[0];
            vm.guardianDetailsValueModel1 = vm.getDetails.guardianDetailsValueModel[1];
            vm.guardianDetailsValueModel2 = vm.getDetails.guardianDetailsValueModel[2];
            vm.subscriptionDetailsValueModel = vm.getDetails.subscriptionDetailsValueModel[0];
            vm.customerMasterDetails.C009.oldValue = convertToDateObject(vm.customerMasterDetails.C009.oldValue);

            vm.subscriptionDetailsValueModel.C015.oldValue = convertToDateObject(vm.subscriptionDetailsValueModel.C015.oldValue);
            vm.subscriptionDetailsValueModel.C016.oldValue = convertToDateObject(vm.subscriptionDetailsValueModel.C016.oldValue);
            if (vm.subscriptionDetailsValueModel.C035) {
              vm.subscriptionDetailsValueModel.C035.oldValue = convertToDateObject(vm.subscriptionDetailsValueModel.C035.oldValue);
            }
            if (vm.subscriptionDetailsValueModel.C031) {
              vm.subscriptionDetailsValueModel.C031.oldValue = convertToDateObject(vm.subscriptionDetailsValueModel.C031.oldValue);
            }

            if (vm.getDetails.guardianDetailsValueModel[0]) {
              if (vm.getDetails.guardianDetailsValueModel[0].C010) {
                vm.guardianDetailsValueModel.C010.oldValue = convertToDateObject(vm.getDetails.guardianDetailsValueModel[0].C010.oldValue);
              }
            }

            if (vm.getDetails.masterDetailsValueModel.length >= 2) {
              vm.customerMasterDetailsTwo = vm.getDetails.masterDetailsValueModel[1];
              vm.guardianDetailsValueModel2 = vm.getDetails.guardianDetailsValueModel[1];
              vm.customerMasterDetailsTwo.C009.oldValue = convertToDateObject(vm.customerMasterDetailsTwo.C009.oldValue);

              if (vm.getDetails.guardianDetailsValueModel) {
                if (vm.guardianDetailsValueModel2 !== "0") {
                  if (vm.getDetails.guardianDetailsValueModel[1]) {
                    if (vm.getDetails.guardianDetailsValueModel[1].C010) {
                      vm.guardianDetailsValueModel2.C010.oldValue = convertToDateObject(vm.guardianDetailsValueModel2.C010.oldValue);
                    }
                  }
                }
              }
              //                                                                 if(vm.guardianDetailsValueModel2.C014){
              //                                                                       vm.guardianDetailsValueModel2.C014.oldValue = convertToDateObject(vm.guardianDetailsValueModel2.C014.oldValue);
              //                                                                 }
              //                                                                 if(vm.guardianDetailsValueModel2.C015){
              //                                                                      vm.guardianDetailsValueModel2.C015.oldValue = convertToDateObject(vm.guardianDetailsValueModel2.C015.oldValue);
              //                                                                 }
            }
            if (vm.getDetails.masterDetailsValueModel.length >= 3) {
              vm.customerMasterDetailsThree = vm.getDetails.masterDetailsValueModel[2];
              vm.guardianDetailsValueModel3 = vm.getDetails.guardianDetailsValueModel[2];
              vm.customerMasterDetailsThree.C009.oldValue = convertToDateObject(vm.customerMasterDetailsThree.C009.oldValue);

              if (vm.getDetails.guardianDetailsValueModel) {
                if (vm.guardianDetailsValueModel3 !== "0") {
                  if (vm.getDetails.guardianDetailsValueModel[2]) {
                    if (vm.getDetails.guardianDetailsValueModel[2].C010) {
                      vm.guardianDetailsValueModel3.C010.oldValue = convertToDateObject(vm.guardianDetailsValueModel3.C010.oldValue);
                    }
                  }
                }
              }
              //                                                                 if(vm.guardianDetailsValueModel3){
              //                                                                       vm.guardianDetailsValueModel3.C014.oldValue = convertToDateObject(vm.guardianDetailsValueModel3.C014.oldValue);
              //                                                                 }
              //                                                                 if(vm.guardianDetailsValueModel3){
              //                                                                      vm.guardianDetailsValueModel3.C015.oldValue = convertToDateObject(vm.guardianDetailsValueModel3.C015.oldValue);
              //                                                                 }
            }

            try {
              if (vm.customerMasterDetails) {
                vm.customerMasterDetails.C037.oldValue = convertYNToBoolean(vm.customerMasterDetails.C037.oldValue) ? "Y" : "N";
                vm.customerMasterDetails.C038.oldValue = convertYNToBoolean(vm.customerMasterDetails.C038.oldValue) ? "Y" : "N";
                vm.customerMasterDetails.C039.oldValue = convertYNToBoolean(vm.customerMasterDetails.C039.oldValue) ? "Y" : "N";
                vm.customerMasterDetails.C040.oldValue = convertYNToBoolean(vm.customerMasterDetails.C040.oldValue) ? "Y" : "N";
                vm.customerMasterDetails.C041.oldValue = convertYNToBoolean(vm.customerMasterDetails.C041.oldValue) ? "Y" : "N";
                vm.customerMasterDetails.C042.oldValue = convertYNToBoolean(vm.customerMasterDetails.C042.oldValue) ? "Y" : "N";
                vm.customerMasterDetails.C043.oldValue = convertYNToBoolean(vm.customerMasterDetails.C043.oldValue) ? "Y" : "N";
                vm.customerMasterDetails.C044.oldValue = convertYNToBoolean(vm.customerMasterDetails.C044.oldValue) ? "Y" : "N";
              }
            } catch (e) {
              console.error("Error processing customerMasterDetails: ", e);
            }

            try {
              if (vm.customerMasterDetailsTwo) {
                vm.customerMasterDetailsTwo.C037.oldValue = convertYNToBoolean(vm.customerMasterDetailsTwo.C037.oldValue) ? "Y" : "N";
                vm.customerMasterDetailsTwo.C038.oldValue = convertYNToBoolean(vm.customerMasterDetailsTwo.C038.oldValue) ? "Y" : "N";
                vm.customerMasterDetailsTwo.C039.oldValue = convertYNToBoolean(vm.customerMasterDetailsTwo.C039.oldValue) ? "Y" : "N";
                vm.customerMasterDetailsTwo.C040.oldValue = convertYNToBoolean(vm.customerMasterDetailsTwo.C040.oldValue) ? "Y" : "N";
                vm.customerMasterDetailsTwo.C041.oldValue = convertYNToBoolean(vm.customerMasterDetailsTwo.C041.oldValue) ? "Y" : "N";
                vm.customerMasterDetailsTwo.C042.oldValue = convertYNToBoolean(vm.customerMasterDetailsTwo.C042.oldValue) ? "Y" : "N";
                vm.customerMasterDetailsTwo.C043.oldValue = convertYNToBoolean(vm.customerMasterDetailsTwo.C043.oldValue) ? "Y" : "N";
                vm.customerMasterDetailsTwo.C044.oldValue = convertYNToBoolean(vm.customerMasterDetailsTwo.C044.oldValue) ? "Y" : "N";
              }
            } catch (e) {
              console.error("Error processing customerMasterDetailsTwo: ", e);
            }

            try {
              if (vm.customerMasterDetailsThree) {
                vm.customerMasterDetailsThree.C037.oldValue = convertYNToBoolean(vm.customerMasterDetailsThree.C037.oldValue) ? "Y" : "N";
                vm.customerMasterDetailsThree.C038.oldValue = convertYNToBoolean(vm.customerMasterDetailsThree.C038.oldValue) ? "Y" : "N";
                vm.customerMasterDetailsThree.C039.oldValue = convertYNToBoolean(vm.customerMasterDetailsThree.C039.oldValue) ? "Y" : "N";
                vm.customerMasterDetailsThree.C040.oldValue = convertYNToBoolean(vm.customerMasterDetailsThree.C040.oldValue) ? "Y" : "N";
                vm.customerMasterDetailsThree.C041.oldValue = convertYNToBoolean(vm.customerMasterDetailsThree.C041.oldValue) ? "Y" : "N";
                vm.customerMasterDetailsThree.C042.oldValue = convertYNToBoolean(vm.customerMasterDetailsThree.C042.oldValue) ? "Y" : "N";
                vm.customerMasterDetailsThree.C043.oldValue = convertYNToBoolean(vm.customerMasterDetailsThree.C043.oldValue) ? "Y" : "N";
                vm.customerMasterDetailsThree.C044.oldValue = convertYNToBoolean(vm.customerMasterDetailsThree.C044.oldValue) ? "Y" : "N";
              }
            } catch (e) {
              console.error("Error processing customerMasterDetailsThree: ", e);
            }
          },
          function (error) {
            vm.loader = false;
            handleErrorResponse(error);
            //                               $state.go('rbi_bond_nominee_continuation.modify');
            $state.go("rbi_bond_nominee_continuation.list");
          }
        );
      }
    }

    function convertBooleanToYN(value) {
      return value ? "Y" : "N";
    }

    function modifycustomerMaster() {
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
          vm.loader = true;
          vm.resultArray = [];
          vm.resultArray1 = [];
          vm.guardianDetails = [];
          vm.resultArray3 = [];

          try {
            if (vm.customerMasterDetails.C044.oldValue === "N") {
              vm.customerMasterDetails.C045 ? (vm.customerMasterDetails.C045.oldValue = "") : "";
            }

            vm.customerMasterDetails.C037.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetails.C037.oldValue));
            vm.customerMasterDetails.C038.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetails.C038.oldValue));
            vm.customerMasterDetails.C039.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetails.C039.oldValue));
            vm.customerMasterDetails.C040.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetails.C040.oldValue));
            vm.customerMasterDetails.C041.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetails.C041.oldValue));
            vm.customerMasterDetails.C042.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetails.C042.oldValue));
            vm.customerMasterDetails.C043.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetails.C043.oldValue));
            vm.customerMasterDetails.C044.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetails.C044.oldValue));
          } catch (e) {
            console.error(e);
          }

          try {
            if (vm.customerMasterDetailsTwo.C044.oldValue === "N") {
              vm.customerMasterDetailsTwo.C045 ? (vm.customerMasterDetailsTwo.C045.oldValue = "") : "";
            }
            vm.customerMasterDetailsTwo.C037.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsTwo.C037.oldValue));
            vm.customerMasterDetailsTwo.C038.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsTwo.C038.oldValue));
            vm.customerMasterDetailsTwo.C039.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsTwo.C039.oldValue));
            vm.customerMasterDetailsTwo.C040.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsTwo.C040.oldValue));
            vm.customerMasterDetailsTwo.C041.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsTwo.C041.oldValue));
            vm.customerMasterDetailsTwo.C042.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsTwo.C042.oldValue));
            vm.customerMasterDetailsTwo.C043.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsTwo.C043.oldValue));
            vm.customerMasterDetailsTwo.C044.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsTwo.C044.oldValue));
          } catch (e) {
            console.error(e);
          }

          try {
            if (vm.customerMasterDetailsThree.C044.oldValue === "N") {
              vm.customerMasterDetailsThree.C045 ? (vm.customerMasterDetailsThree.C045.oldValue = "") : "";
            }
            vm.customerMasterDetailsThree.C037.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsThree.C037.oldValue));
            vm.customerMasterDetailsThree.C038.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsThree.C038.oldValue));
            vm.customerMasterDetailsThree.C039.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsThree.C039.oldValue));
            vm.customerMasterDetailsThree.C040.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsThree.C040.oldValue));
            vm.customerMasterDetailsThree.C041.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsThree.C041.oldValue));
            vm.customerMasterDetailsThree.C042.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsThree.C042.oldValue));
            vm.customerMasterDetailsThree.C043.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsThree.C043.oldValue));
            vm.customerMasterDetailsThree.C044.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsThree.C044.oldValue));
          } catch (e) {
            console.error(e);
          }

          if (vm.customerMasterDetails) {
            var oneObj = Object.fromEntries(Object.entries(vm.customerMasterDetails));
            oneObj.C009.oldValue = $filter("date")(oneObj.C009.oldValue, "dd-MMM-yyyy");

            oneObj.ID.oldValue = oneObj.ID.oldValue; // or rename it if needed

            vm.resultArray.push(convertToColumnValuesArrayList(oneObj));
          }
          //second joint holder
          if (vm.customerMasterDetailsTwo) {
            var twoObj = Object.fromEntries(Object.entries(vm.customerMasterDetailsTwo));
            twoObj.C009.oldValue = $filter("date")(twoObj.C009.oldValue, "dd-MMM-yyyy");
            twoObj.ID.oldValue = twoObj.ID.oldValue;

            vm.resultArray.push(convertToColumnValuesArrayList(twoObj));
          }

          //third joint holder
          if (vm.customerMasterDetailsThree) {
            var threeObj = Object.fromEntries(Object.entries(vm.customerMasterDetailsThree));
            threeObj.C009.oldValue = $filter("date")(threeObj.C009.oldValue, "dd-MMM-yyyy");
            threeObj.ID.oldValue = threeObj.ID.oldValue;

            vm.resultArray.push(convertToColumnValuesArrayList(threeObj));
          }

          //            if (vm.getDetails.guardianDetailsValueModel[2]) {
          //                var threeObj = Object.fromEntries(Object.entries(vm.getDetails.guardianDetailsValueModel[2]));
          //                threeObj.C010.oldValue = $filter('date')(threeObj.C010.oldValue, "dd-MMM-yyyy");
          //                if (threeObj.C014) {
          //                    threeObj.C014.oldValue = $filter('date')(threeObj.C014.oldValue, "dd-MMM-yyyy");
          //                }
          //                if (threeObj.C015) {
          //                    threeObj.C015.oldValue = $filter('date')(threeObj.C015.oldValue, "dd-MMM-yyyy");
          //                }
          //
          //                vm.resultArray1.push(convertToColumnValuesArrayList(threeObj));
          //            }

          if (vm.getDetails.guardianDetailsValueModel[0]) {
            var oneObj = Object.fromEntries(Object.entries(vm.getDetails.guardianDetailsValueModel[0]));
            if (oneObj.C010) {
              oneObj.C010.oldValue = $filter("date")(oneObj.C010.oldValue, "dd-MMM-yyyy");
            }
            if (oneObj.C014) {
              oneObj.C014.oldValue = $filter("date")(oneObj.C014.oldValue, "dd-MMM-yyyy");
            }
            if (oneObj.C015) {
              oneObj.C015.oldValue = $filter("date")(oneObj.C015.oldValue, "dd-MMM-yyyy");
            }

            if (oneObj.C003) {
              oneObj.C003.oldValue = oneObj.C003.oldValue;
            }
            vm.resultArray1.push(convertToColumnValuesArrayList(oneObj));

            vm.guardianDetailsValueModelOne = vm.guardianDetailsValueModelOne;
            if (oneObj.C014 && oneObj.C014.oldValue) {
              vm.showGuardianDetails = true;
            }
            if (vm.guardianDetailsValueModelOne && !vm.guardianDetailsValueModelOne.ID) {
              if (oneObj.C003) {
                // Ensure guardianDetailsValueModelOne has the C003 value
                vm.guardianDetailsValueModelOne.C003 = {
                  oldValue: oneObj.C003.oldValue,
                };
              }
              vm.guardianDetails.push(convertToColumnValuesArrayList(vm.guardianDetailsValueModelOne));
            }
          }
          if (vm.getDetails.guardianDetailsValueModel[1]) {
            var twoObj = Object.fromEntries(Object.entries(vm.getDetails.guardianDetailsValueModel[1]));
            if (twoObj.C010) {
              twoObj.C010.oldValue = $filter("date")(twoObj.C010.oldValue, "dd-MMM-yyyy");
            }
            if (twoObj.C014) {
              twoObj.C014.oldValue = $filter("date")(twoObj.C014.oldValue, "dd-MMM-yyyy");
            }
            if (twoObj.C015) {
              twoObj.C015.oldValue = $filter("date")(twoObj.C015.oldValue, "dd-MMM-yyyy");
            }
            vm.guardianDetailsValueModelTwo = vm.guardianDetailsValueModelTwo;
            vm.resultArray1.push(convertToColumnValuesArrayList(twoObj));

            if (twoObj.C014 && twoObj.C014.oldValue) {
              vm.showGuardianDetailsTwo = true;
            }
            if (vm.guardianDetailsValueModelTwo && !vm.guardianDetailsValueModelTwo.ID) {
              if (twoObj.C003) {
                vm.guardianDetailsValueModelTwo.C003 = {
                  oldValue: twoObj.C003.oldValue,
                };
              }
              vm.guardianDetails.push(convertToColumnValuesArrayList(vm.guardianDetailsValueModelTwo));
            }
          }

          if (vm.getDetails.guardianDetailsValueModel[2]) {
            var threeObj = Object.fromEntries(Object.entries(vm.getDetails.guardianDetailsValueModel[2]));
            if (threeObj.C010) {
              threeObj.C010.oldValue = $filter("date")(threeObj.C010.oldValue, "dd-MMM-yyyy");
            }
            if (threeObj.C014) {
              threeObj.C014.oldValue = $filter("date")(threeObj.C014.oldValue, "dd-MMM-yyyy");
            }
            if (threeObj.C015) {
              threeObj.C015.oldValue = $filter("date")(threeObj.C015.oldValue, "dd-MMM-yyyy");
            }
            vm.guardianDetailsValueModelThree = vm.guardianDetailsValueModelThree;
            vm.resultArray1.push(convertToColumnValuesArrayList(threeObj));

            if (threeObj.C014 && threeObj.C014.oldValue) {
              vm.showGuardianDetailsTwo = true;
            }
            if (vm.guardianDetailsValueModelThree && !vm.guardianDetailsValueModelThree.ID) {
              if (threeObj.C003) {
                // Ensure guardianDetailsValueModelOne has the C003 value
                vm.guardianDetailsValueModelThree.C003 = {
                  oldValue: threeObj.C003.oldValue,
                };
              }
              vm.guardianDetails.push(convertToColumnValuesArrayList(vm.guardianDetailsValueModelThree));
            }
          }

          if (vm.getDetails.subscriptionDetailsValueModel[0]) {
            var oneObj = Object.fromEntries(Object.entries(vm.getDetails.subscriptionDetailsValueModel[0]));

            if (oneObj.C016) {
              oneObj.C016.oldValue = $filter("date")(oneObj.C016.oldValue, "dd-MMM-yyyy");
            }
            if (oneObj.C015) {
              oneObj.C015.oldValue = $filter("date")(oneObj.C015.oldValue, "dd-MMM-yyyy");
            }
            if (oneObj.C018) {
              //                    oneObj.C018.oldValue = $filter('date')(oneObj.C018.oldValue, "dd-MMM-yyyy");
              if (oneObj.C018.oldValue) {
                const dateObject = convertToDateObject(oneObj.C018.oldValue);
                if (dateObject) {
                  oneObj.C018.oldValue = $rootScope.formatDateToDDMMMYYYY(dateObject);
                } else {
                  console.warn("Invalid date value:", oneObj.C018.oldValue);
                }
              } else {
                console.warn("oldValue is null or undefined");
              }
            }
            vm.resultArray3.push(convertToColumnValuesArrayList(oneObj));
          }

          let requestData = {
            schemeCode: vm.schemeCode,
            blaNo: vm.blaNo,
            boSubsId: vm.boSubsId,
            columnValueDetailsList: vm.resultArray,
            guardianModValueDetailsList: vm.resultArray1,
            guardianAddDetailsList: vm.guardianDetails,
            subscriptionDetailsList: vm.resultArray3,
          };
          rbibondNomineeContinuationService.modifySubmit(requestData).then(
            (response) => {
              vm.loader = false;
              Swal.fire("Success!", "Bond Ledger Account Modified Successfully", "success");
              location.href = "#!/rbi_bond_nominee_continuation/action/inquiry";
            },
            function (error) {
              vm.loader = false;
              handleErrorResponse(error);
              $state.go("rbi_bond_nominee_continuation.modify");
            }
          );
       } }
      );
    }

    //Verify Select
    function getVerifySelectDetails(flag) {
      vm.loader = true;

     if(flag==''){
         var requestData = {};
         }
         else if(flag=='P'){
           var requestData = {
             schemeCode: vm.schemeCode,
             flag: flag,
             panNum: vm.panNum,
           };
           }
           else if(flag=='B'){
                 var requestData = {
                   schemeCode: vm.schemeCode,
                   flag: flag,
                   panNum: vm.panNum,
                   blaNo: vm.blaNo,
                 };
                 }
          else if(flag=='S'){
                      var requestData = {
                        schemeCode: vm.schemeCode,
                        flag: flag,
                        panNum: vm.panNum,
                        blaNo: vm.blaNo,
                        boSubsId: vm.boSubsId,
                      };
                      }

     else if(flag=='N'){
                        var requestData = {
                          schemeCode: vm.schemeCode,
                          flag: flag,
                          panNum: vm.panNum,
                          blaNo: vm.blaNo,
                          boSubsId: vm.boSubsId,
                        };
                        }else if(flag=='D'){
                         vm.blaDetSingle = [];
                              vm.subsDetSingle = [];


                               angular.forEach(vm.nominationDetails1, function (item) {
                                   vm.blaDetSingle.push(item.nomineeBlaNo);
                                   vm.subsDetSingle.push(item.nomineeBoSubsId);
                               });

                                                 var requestData = {
                                                   schemeCode: vm.schemeCode,
                                                   flag: flag,
                                                   panNum: vm.panNum,
                                                   blaNo: vm.blaNo,
                                                   boSubsId: vm.boSubsId,
                                                   blaDetSingle: vm.blaDetSingle,
                                                   subsDetSingle: vm.subsDetSingle
                                                 };
                                                 }
        rbibondNomineeContinuationService.verifySelect(requestData).then(
          function (response) {
            vm.loader = false;
                    //                                                       vm.PanTorch = response.data.result;
                    if(flag==''){
                       vm.schemeList = response.data.result;
                              angular.element("#schemeModel").modal("toggle");

                    }
                    else if(flag=='P'){
                    vm.PanTorch = response.data.result;
                    angular.element("#panModel").modal("toggle");
                    }
                    else if(flag=='B'){
                              vm.blaDataList = response.data.result;
                              angular.element("#blaModel").modal("toggle");
                              }
                    else if(flag=='S'){
                                        vm.subscriptionList = response.data.result;
                                           angular.element("#subscriptionModel").modal("toggle");
                                        }
                    else if(flag=='N'){

                        vm.nominationDetails1 = response.data.result;

                    }

                                        else{
                                        vm.loader = false;

                                                  vm.getBlaDetail=[];
                                                  vm.getSubscriptionDetail=[];
                                                  vm.getNomineeDetail=[];
                                                  vm.auditDetails = response.data.result;

                                                  vm.getBlaDetail = response.data.result.blaDetailsList;
                                                  vm.getBlaGuardDetail = response.data.result.blaGuardianDetailList;
                                                  vm.getSubscriptionDetail = response.data.result.subsDetailsList;
                                                    for(var i =0;i<vm.getBlaDetail.length;i++)
                                                     {
                                                     if(null!=vm.getBlaDetail&&null!=vm.getBlaDetail[i].bankCode){
                                                    getbankNameInq(vm.getBlaDetail[i].bankCode,i);
                                                    }
                                                    }
                                                  for(var i =0;i<vm.getSubscriptionDetail.length;i++)
                                                                                              {
                                                      vm.getSubscriptionDetail[i].wordsInRupees = convertAmountToWords(vm.getSubscriptionDetail[i].subscriptionAmount);
                                                                                     }
                                                  vm.getNomineeDetail = response.data.result.nomineeDetList;

          }
          },
          function (err) {
            vm.loader = false;
               const errorMessage = err && err.data && err.data.message
                                ? err.data.message
                                : "An unexpected error occurred.";
                        if(null!=errorMessage && errorMessage.startsWith("Invalid")){
                        Swal.fire("Warning!", errorMessage , "warning");
                        $state.go("rbi_bond_nominee_continuation.list");
                        }else{
                        handleErrorResponse(err);
                        $state.go("rbi_bond_nominee_continuation.list");
                        }

          }
        );
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

         vm.blaDetSingle = [];
                                       vm.subsDetSingle = [];


                                        angular.forEach(vm.nominationDetails1, function (item) {
                                            vm.blaDetSingle.push(item.nomineeBlaNo);
                                            vm.subsDetSingle.push(item.nomineeBoSubsId);
                                        });

                                                          var requestData = {
                                                            schemeCode: vm.schemeCode,
                                                            blaNo: vm.blaNo,
                                                            boSubsId: vm.boSubsId,
                                                            blaDetSingle: vm.blaDetSingle,
                                                            subsDetSingle: vm.subsDetSingle,
                                                            remarks:''
                                                          };
          rbibondNomineeContinuationService.verifySubmit(requestData).then(
            (response) => {
              vm.loader = false;
                Swal.fire("Success!", "Bond Nominee Continuation Verified Successfully", "success");

                $state.go("rbi_bond_nominee_continuation.list");
              },
              function (error) {
                vm.loader = false;
                handleErrorResponse(error);
                $state.go("rbi_bond_nominee_continuation.list");
              }
            );
       }   }
        );
      }

    vm.getRemarksList = function () {
      vm.referenceId = "056";
      var requestData = { referenceId: vm.referenceId };

      rbibondNomineeContinuationService.getRemarksList(requestData).then(
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

vm.currentStep = 1;  // Start from the first step
vm.nominationDetails2 = vm.nominationDetails2 || [];  // Ensure it's an array
vm.totalSteps = vm.nominationDetails2.length || 1;
vm.nextStep = function () {
    if (vm.currentStep < vm.totalSteps) vm.currentStep++;
          var $active = $(".from_wrapper .nav-tabs li.active");
          nextTab($active);
};
 function nextTab(elem) {
      $(elem).next().find('a[data-bs-toggle="tab"]').click();
    }
vm.prevStep = function () {
    if (vm.currentStep > 1) vm.currentStep--;

      var $active = $(".from_wrapper .nav-tabs li.active");
          prevTab($active);
};
 function prevTab(elem) {
      $(elem).prev().find('a[data-bs-toggle="tab"]').click();
    }
    vm.deleteSubmit = function () {
      vm.loader = true;
      vm.blaDetSingle = [];
                                    vm.subsDetSingle = [];


                                     angular.forEach(vm.nominationDetails1, function (item) {
                                         vm.blaDetSingle.push(item.nomineeBlaNo);
                                         vm.subsDetSingle.push(item.nomineeBoSubsId);
                                     });

                                                       var requestData = {
                                                         schemeCode: vm.schemeCode,
                                                         panNum: vm.panNum,
                                                         blaNo: vm.blaNo,
                                                         boSubsId: vm.boSubsId,
                                                         blaDetSingle: vm.blaDetSingle,
                                                         subsDetSingle: vm.subsDetSingle,
                                                         remarks: vm.rejectionRemarks
                                                       };
      rbibondNomineeContinuationService.deleteSubmit(requestData).then(
        function (response) {
          if (response.data.message == "SUCCESS") {
            Swal.fire("Success!", "Bond Nominee Continuation Rejected Successfully", "success");
            location.href = "#!/rbi_bond_nominee_continuation/action/inquiry";
          } else {
            Swal.fire("WARNING!", response.data.message, "error");
            location.href = "#!/rbi_bond_nominee_continuation/action/verify/detail";
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
          location.href = "#!/rbi_bond_nominee_continuation/action/verify/detail";
        }
      );
    };

    //Inquiry Select
    function getInquirySelectDetails(flag) {
      vm.loader = true;

     if(flag==''){
         var requestData = {};
         }
         else if(flag=='P'){
           var requestData = {
             schemeCode: vm.schemeCode,
             flag: flag,
             panNum: vm.panNum,
           };
           }
           else if(flag=='B'){
                 var requestData = {
                   schemeCode: vm.schemeCode,
                   flag: flag,
                   panNum: vm.panNum,
                   blaNo: vm.blaNo,
                 };
                 }
          else if(flag=='S'){
                      var requestData = {
                        schemeCode: vm.schemeCode,
                        flag: flag,
                        panNum: vm.panNum,
                        blaNo: vm.blaNo,
                        boSubsId: vm.boSubsId,
                      };
                      }

     else if(flag=='N'){
                        var requestData = {
                          schemeCode: vm.schemeCode,
                          flag: flag,
                          panNum: vm.panNum,
                          blaNo: vm.blaNo,
                          boSubsId: vm.boSubsId,
                        };
                        }else if(flag=='D'){
                         vm.blaDetSingle = [];
                              vm.subsDetSingle = [];


                               angular.forEach(vm.nominationDetails1, function (item) {
                                   vm.blaDetSingle.push(item.nomineeBlaNo);
                                   vm.subsDetSingle.push(item.nomineeBoSubsId);
                               });

                                                 var requestData = {
                                                   schemeCode: vm.schemeCode,
                                                   flag: flag,
                                                   panNum: vm.panNum,
                                                   blaNo: vm.blaNo,
                                                   boSubsId: vm.boSubsId,
                                                   blaDetSingle: vm.blaDetSingle,
                                                   subsDetSingle: vm.subsDetSingle
                                                 };
                                                 }
        rbibondNomineeContinuationService.getInquiryDetails(requestData).then(
          function (response) {
            vm.loader = false;
                    //                                                       vm.PanTorch = response.data.result;
                    if(flag==''){
                       vm.schemeList = response.data.result;
                              angular.element("#schemeModel").modal("toggle");

                    }
                    else if(flag=='P'){
                    vm.PanTorch = response.data.result;
                    angular.element("#panModel").modal("toggle");
                    }
                    else if(flag=='B'){
                              vm.blaDataList = response.data.result;
                              angular.element("#blaModel").modal("toggle");
                              }
                    else if(flag=='S'){
                                        vm.subscriptionList = response.data.result;
                                           angular.element("#subscriptionModel").modal("toggle");
                                        }
                    else if(flag=='N'){

                        vm.nominationDetails1 = response.data.result;

                    }

                                        else{
                                        vm.loader = false;

                                                  vm.getBlaDetail=[];
                                                  vm.getSubscriptionDetail=[];
                                                  vm.getNomineeDetail=[];
                                                  vm.auditDetails = response.data.result;
                                                  vm.getBlaDetail = response.data.result.blaDetailsList;
                                                  vm.getBlaGuardDetail = response.data.result.blaGuardianDetailList;
                                                  vm.getSubscriptionDetail = response.data.result.subsDetailsList;

                                                   for(var i =0;i<vm.getBlaDetail.length;i++)
                                                   {
                                                   if(null!=vm.getBlaDetail&&null!=vm.getBlaDetail[i].bankCode){
                                                  getbankNameInq(vm.getBlaDetail[i].bankCode,i);
                                                  }
                                                  }
                                                  for(var i =0;i<vm.getSubscriptionDetail.length;i++)
                                                                                              {
                                                      vm.getSubscriptionDetail[i].wordsInRupees = convertAmountToWords(vm.getSubscriptionDetail[i].subscriptionAmount);
                                                                                     }
                                                  vm.getNomineeDetail = response.data.result.nomineeDetList;

          }
          },
          function (err) {
            vm.loader = false;
              const errorMessage = err && err.data && err.data.message
                               ? err.data.message
                               : "An unexpected error occurred.";
                       if(null!=errorMessage && errorMessage.startsWith("Invalid")){
                       Swal.fire("Warning!", errorMessage , "warning");
                       $state.go("rbi_bond_nominee_continuation.list");
                       }else{
                       handleErrorResponse(err);
                       $state.go("rbi_bond_nominee_continuation.list");
                       }
          }
        );
      }


      //                rbibondNomineeContinuationService.inquirySelect(requestData).then(function(response){
      //                        if(flag =='tourch'){
      //                            vm.custIdList = response.data.result.custIdList;
      //                            angular.element('#custIdModel').modal('toggle');
      //                        }
      //                        else{
      //                             vm.getScssVerifyDetails = response.data.result;
      //                             vm.propertyCheck = vm.getScssVerifyDetails.masterDetailsValueModel[0];
      //                              if(vm.propertyCheck.hasOwnProperty('C021') == true){
      //                                  vm.fieldShow21 = true;
      //                                  if(vm.getScssVerifyDetails.masterDetailsValueModel[0].C021.newValue !=null){
      //                                        vm.getScssVerifyDetails.masterDetailsValueModel[0].C021.newValue = new Date(vm.getScssVerifyDetails.masterDetailsValueModel[0].C021.newValue);
      //                                  }
      //                               }
      //                               if(vm.propertyCheck.hasOwnProperty('C036') == true){
      //                                   vm.fieldShow36 = true;
      //                                   if(vm.getScssVerifyDetails.masterDetailsValueModel[0].C036.newValue !=null){
      //                                        vm.getScssVerifyDetails.masterDetailsValueModel[0].C036.newValue = new Date(vm.getScssVerifyDetails.masterDetailsValueModel[0].C036.newValue);
      //                                   }
      //                               }
      //                             vm.getScssKycVerifyDetails = response.data.result.formDetailsValueModel;
      //                             vm.getScssKycOtherVerifyDetails = response.data.result.formDetailsValueModel;
      //                             vm.getScssOtherBankVerifyDetails = response.data.result.otherBanksDetailsValueModel;
      //                                if(vm.getScssVerifyDetails.masterDetailsValueModel[0].C002.oldValue == 'MH002'){
      //                                    vm.titleText="FIRST JOINT ACCOUNT HOLDER DETAILS";
      //                                    vm.holdingStatus ="JOINT";
      //                                }else{
      //                                    vm.titleText="CUSTOMER MASTER DETAILS";
      //                                    vm.holdingStatus ="SINGLE";
      //                                }
      //                                getVerifyTdsApplicableRate();
      //                             }
      //
      //                        vm.loader = false;
      //
      //                },function(error) {
      //                    vm.loader = false;
      //                    handleErrorResponse(error);
      //                    $state.go('rbi_bond_nominee_continuation.list');
      //                  });


    function getValidations(flag) {
      //                vm.flagVal = flag;
      //
      //                rbibondNomineeContinuationService.accountTypeValid().then(function(response){
      //                    if(response) {
      //                        vm.basicValidations = response.data.result[0];
      //                        vm.minAgeSenior = vm.basicValidations.minAgeSeniorCitizen;
      //                        vm.minAgeVolRetire = vm.basicValidations.minAgeVolRetire;
      //                        vm.minAgeDefPerson = vm.basicValidations.minAgeDefPerson;
      //                        if(vm.flagVal == 'AT001'){
      //                            vm.minAge = vm.minAgeSenior;
      //                        }else if(vm.flagVal == 'AT002'){
      //                            vm.minAge = vm.minAgeVolRetire;
      //                        }else if(vm.flagVal == 'AT003'){
      //                            vm.minAge = vm.minAgeDefPerson;
      //                        }
      //                        accountHolderDateValidation(vm.minAge);
      //                    }else {
      //                        Swal.fire("Error!", response.message, "error");
      //                        vm.loader = false;
      //                    }
      //
      //                 }, function(error) {
      //                    handleErrorResponse(error);
      //                  });
    }
    //             vm.dobCalenderOptionsTwo  = {
    //                                     //minDate: minDate,
    //                                     maxDate: convertToDateObject(vm.loginSolDate),
    //                                     showWeeks: false
    //                                 };
    //                                 vm.dobCalenderOptionsThree  = {
    //                                                         //minDate: minDate,
    //                                                         maxDate: convertToDateObject(vm.loginSolDate),
    //                                                         showWeeks: false
    //                                                     };
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
  text: "Do you Really want to cancel ?",
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
        }}
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

      rbibondNomineeContinuationService.fetchCbsCustId(accountNumber).then(
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
        rbibondNomineeContinuationService.fetchBlaAccNoDetails(requestData).then(
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
        rbibondNomineeContinuationService.fetchBlaAccNoDetails(requestData).then(
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
        rbibondNomineeContinuationService.fetchGuardianDetails(requestData).then(
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
        rbibondNomineeContinuationService.fetchGuardianDetails(requestData).then(
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

      rbibondNomineeContinuationService.fetchCbsCustId(accountNumber).then(
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
        rbibondNomineeContinuationService.fetchBlaAccNoDetails(requestData).then(
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

        rbibondNomineeContinuationService.fetchBlaAccNoDetails(requestData).then(
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
        rbibondNomineeContinuationService.fetchGuardianDetails(requestData).then(
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
        rbibondNomineeContinuationService.fetchGuardianDetails(requestData).then(
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

      rbibondNomineeContinuationService.fetchCbsCustId(requestData).then(
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

      rbibondNomineeContinuationService.fetchCbsCustId(requestData).then(
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

      rbibondNomineeContinuationService.fetchCbsCustId(requestData).then(
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

    function getBlaAccNoDetailsModify(flag) {
      vm.loader = true;

      if (flag == "torch") {
        var requestData = {
          schemeCode: vm.schemeCode,
          modeOfHolding: vm.modeOfHolding,
        };
        rbibondNomineeContinuationService.fetchBlaAccNoDetails(requestData).then(
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
          blaNum: vm.customerMasterDetails.C005.oldValue,
          jointHolderNo: "1",
        };
        rbibondNomineeContinuationService.fetchBlaAccNoDetails(requestData).then(
          (response) => {
            vm.loader = false;
            vm.blaAccDetails = response.data.result[0];
            vm.customerMasterDetails.C005.oldValue = vm.blaAccDetails.blaNo;
            vm.customerMasterDetails.C006.oldValue = vm.blaAccDetails.bondHolderFirstName;
            vm.customerMasterDetails.C007.oldValue = vm.blaAccDetails.bondHolderMiddleName;
            vm.customerMasterDetails.C008.oldValue = vm.blaAccDetails.bondHolderLastName;
            vm.customerMasterDetails.C010.oldValue = vm.blaAccDetails.fatherName;
            vm.customerMasterDetails.C011.oldValue = vm.blaAccDetails.motherName;
            vm.customerMasterDetails.C012.oldValue = vm.blaAccDetails.gender;
            vm.customerMasterDetails.C013.oldValue = convertToDateObject(vm.blaAccDetails.dateOfBirth);
            vm.customerMasterDetails.C014.oldValue = vm.blaAccDetails.isMinor;
            vm.customerMasterDetails.C015.oldValue = vm.blaAccDetails.addressOne;
            vm.customerMasterDetails.C016.oldValue = vm.blaAccDetails.addressTwo;
            vm.customerMasterDetails.C017.oldValue = vm.blaAccDetails.addressThree;
            vm.customerMasterDetails.C018.oldValue = vm.blaAccDetails.city;
            vm.customerMasterDetails.C020.oldValue = vm.blaAccDetails.pinCode;
            vm.customerMasterDetails.C046.oldValue = vm.blaAccDetails.phoneNumber;
            vm.customerMasterDetails.C021.oldValue = vm.blaAccDetails.mobileNumber;
            vm.customerMasterDetails.C022.oldValue = vm.blaAccDetails.email;
            vm.customerMasterDetails.C034.oldValue = vm.blaAccDetails.operativeAccNo;
            vm.customerMasterDetails.C035.oldValue = vm.blaAccDetails.operativeBranchId;
            vm.customerMasterDetails.C036.oldValue = vm.blaAccDetails.pan;
            vm.customerMasterDetailsThree.C023.oldValue = vm.blaAccDetailsThree.hufRegPowerOfAttorney;
            vm.customerMasterDetailsThree.C024.oldValue = vm.blaAccDetailsThree.powerOfAttorneyKartaName;
          },
          function (error) {
            vm.loader = false;
            vm.customerMasterDetails.C005.oldValue = "";
            handleErrorResponse(error);
          }
        );
      }
    }

    function getBlaAccNoDetailsTwoModify(flag) {
      vm.loader = true;

      if (flag == "torch") {
        var requestData = {
          schemeCode: vm.schemeCode,
          modeOfHolding: vm.modeOfHolding,
        };
        rbibondNomineeContinuationService.fetchBlaAccNoDetails(requestData).then(
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
          blaNum: vm.customerMasterDetailsTwo.C005.oldValue,
          jointHolderNo: "2",
        };
        rbibondNomineeContinuationService.fetchBlaAccNoDetails(requestData).then(
          (response) => {
            vm.loader = false;
            vm.blaAccDetailsTwo = response.data.result[0];
            vm.customerMasterDetailsTwo.C005.oldValue = vm.blaAccDetailsTwo.blaNo;
            vm.customerMasterDetailsTwo.C006.oldValue = vm.blaAccDetailsTwo.bondHolderFirstName;
            vm.customerMasterDetailsTwo.C007.oldValue = vm.blaAccDetailsTwo.bondHolderMiddleName;
            vm.customerMasterDetailsTwo.C008.oldValue = vm.blaAccDetailsTwo.bondHolderLastName;
            vm.customerMasterDetailsTwo.C010.oldValue = vm.blaAccDetailsTwo.fatherName;
            vm.customerMasterDetailsTwo.C011.oldValue = vm.blaAccDetailsTwo.motherName;
            vm.customerMasterDetailsTwo.C012.oldValue = vm.blaAccDetailsTwo.gender;
            vm.customerMasterDetailsTwo.C013.oldValue = convertToDateObject(vm.blaAccDetailsTwo.dateOfBirth);
            vm.customerMasterDetailsTwo.C014.oldValue = vm.blaAccDetailsTwo.isMinor;
            vm.customerMasterDetailsTwo.C015.oldValue = vm.blaAccDetailsTwo.addressOne;
            vm.customerMasterDetailsTwo.C016.oldValue = vm.blaAccDetailsTwo.addressTwo;
            vm.customerMasterDetailsTwo.C017.oldValue = vm.blaAccDetailsTwo.addressThree;
            vm.customerMasterDetailsTwo.C018.oldValue = vm.blaAccDetailsTwo.city;
            vm.customerMasterDetailsTwo.C020.oldValue = vm.blaAccDetailsTwo.pinCode;
            vm.customerMasterDetailsTwo.C046.oldValue = vm.blaAccDetailsTwo.phoneNumber;
            vm.customerMasterDetailsTwo.C021.oldValue = vm.blaAccDetailsTwo.mobileNumber;
            vm.customerMasterDetailsTwo.C022.oldValue = vm.blaAccDetailsTwo.email;
            vm.customerMasterDetailsTwo.C034.oldValue = vm.blaAccDetailsTwo.operativeAccNo;
            vm.customerMasterDetailsTwo.C035.oldValue = vm.blaAccDetailsTwo.operativeBranchId;
            vm.customerMasterDetailsTwo.C036.oldValue = vm.blaAccDetailsTwo.pan;
            vm.customerMasterDetailsThree.C023.oldValue = vm.blaAccDetailsThree.hufRegPowerOfAttorney;
            vm.customerMasterDetailsThree.C024.oldValue = vm.blaAccDetailsThree.powerOfAttorneyKartaName;
          },
          function (error) {
            vm.loader = false;
            vm.customerMasterDetailsTwo.C005.oldValue = "";
            handleErrorResponse(error);
          }
        );
      }
    }

    function getBlaAccNoDetailsThreeModify(flag) {
      vm.loader = true;

      if (flag == "torch") {
        var requestData = {
          schemeCode: vm.schemeCode,
          modeOfHolding: vm.modeOfHolding,
        };
        rbibondNomineeContinuationService.fetchBlaAccNoDetails(requestData).then(
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
          blaNum: vm.customerMasterDetailsThree.C005.oldValue,
          jointHolderNo: "3",
        };
        rbibondNomineeContinuationService.fetchBlaAccNoDetails(requestData).then(
          (response) => {
            vm.loader = false;
            vm.blaAccDetailsThree = response.data.result[0];
            vm.customerMasterDetailsThree.C005.oldValue = vm.blaAccDetailsTwo.blaNo;
            vm.customerMasterDetailsThree.C006.oldValue = vm.blaAccDetailsThree.bondHolderFirstName;
            vm.customerMasterDetailsThree.C007.oldValue = vm.blaAccDetailsThree.bondHolderMiddleName;
            vm.customerMasterDetailsThree.C008.oldValue = vm.blaAccDetailsThree.bondHolderLastName;
            vm.customerMasterDetailsThree.C010.oldValue = vm.blaAccDetailsThree.fatherName;
            vm.customerMasterDetailsThree.C011.oldValue = vm.blaAccDetailsThree.motherName;
            vm.customerMasterDetailsThree.C012.oldValue = vm.blaAccDetailsThree.gender;
            vm.customerMasterDetailsThree.C013.oldValue = convertToDateObject(vm.blaAccDetailsThree.dateOfBirth);
            vm.customerMasterDetailsThree.C014.oldValue = vm.blaAccDetailsThree.isMinor;
            vm.customerMasterDetailsThree.C015.oldValue = vm.blaAccDetailsThree.addressOne;
            vm.customerMasterDetailsThree.C016.oldValue = vm.blaAccDetailsThree.addressTwo;
            vm.customerMasterDetailsThree.C017.oldValue = vm.blaAccDetailsThree.addressThree;
            vm.customerMasterDetailsThree.C018.oldValue = vm.blaAccDetailsThree.city;
            vm.customerMasterDetailsThree.C020.oldValue = vm.blaAccDetailsThree.pinCode;
            vm.customerMasterDetailsThree.C046.oldValue = vm.blaAccDetailsThree.phoneNumber;
            vm.customerMasterDetailsThree.C021.oldValue = vm.blaAccDetailsThree.mobileNumber;
            vm.customerMasterDetailsThree.C022.oldValue = vm.blaAccDetailsThree.email;
            vm.customerMasterDetailsThree.C034.oldValue = vm.blaAccDetailsThree.operativeAccNo;
            vm.customerMasterDetailsThree.C035.oldValue = vm.blaAccDetailsThree.operativeBranchId;
            vm.customerMasterDetailsThree.C036.oldValue = vm.blaAccDetailsThree.pan;
            vm.customerMasterDetailsThree.C023.oldValue = vm.blaAccDetailsThree.hufRegPowerOfAttorney;
            vm.customerMasterDetailsThree.C024.oldValue = vm.blaAccDetailsThree.powerOfAttorneyKartaName;
          },
          function (error) {
            vm.loader = false;
            vm.customerMasterDetailsThree.C005.oldValue = "";

            handleErrorResponse(error);
          }
        );
      }
    }

    function getGuardianDetailsModify(flag) {
      vm.loader = true;

      if (flag == "select") {
        var requestData = {
          schemeCode: vm.schemeCode,
        };
        rbibondNomineeContinuationService.fetchGuardianDetails(requestData).then(
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
          guardianId: vm.customerMasterDetails.C025.oldValue,
        };
        rbibondNomineeContinuationService.fetchGuardianDetails(requestData).then(
          (response) => {
            vm.loader = false;
            vm.guardianDetail = response.data.result[0];

            vm.customerMasterDetails.C026.oldValue = vm.guardianDetail.guardianFirstName;
            vm.customerMasterDetails.C027.oldValue = vm.guardianDetail.guardianMiddleName;
            vm.customerMasterDetails.C028.oldValue = vm.guardianDetail.guardianLastName;
            vm.customerMasterDetails.C030.oldValue = convertToDateObject(vm.guardianDetail.dateOfBirth);
            vm.customerMasterDetails.C031.oldValue = vm.guardianDetail.address;
            vm.customerMasterDetails.C032.oldValue = vm.guardianDetail.mobileNo;
            vm.customerMasterDetails.C033.oldValue = vm.guardianDetail.email;
          },
          function (error) {
            vm.loader = false;
            handleErrorResponse(error);
          }
        );
      }
    }

    function getGuardianDetailsTwoModify(flag) {
      vm.loader = true;

      if (flag == "select") {
        var requestData = {
          schemeCode: vm.schemeCode,
        };
        rbibondNomineeContinuationService.fetchGuardianDetails(requestData).then(
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
          guardianId: vm.customerMasterDetailsTwo.C025.oldValue,
        };
        rbibondNomineeContinuationService.fetchGuardianDetails(requestData).then(
          (response) => {
            vm.loader = false;
            vm.guardianDetailTwo = response.data.result[0];

            vm.customerMasterDetailsTwo.C026.oldValue = vm.guardianDetailTwo.guardianFirstName;
            vm.customerMasterDetailsTwo.C027.oldValue = vm.guardianDetailTwo.guardianMiddleName;
            vm.customerMasterDetailsTwo.C028.oldValue = vm.guardianDetailTwo.guardianLastName;
            vm.customerMasterDetailsTwo.C030.oldValue = convertToDateObject(vm.guardianDetailTwo.dateOfBirth);
            vm.customerMasterDetailsTwo.C031.oldValue = vm.guardianDetailTwo.address;
            vm.customerMasterDetailsTwo.C032.oldValue = vm.guardianDetailTwo.mobileNo;
            vm.customerMasterDetailsTwo.C033.oldValue = vm.guardianDetailTwo.email;
          },
          function (error) {
            vm.loader = false;
            handleErrorResponse(error);
          }
        );
      }
    }

    function getGuardianDetailsThreeModify(flag) {
      vm.loader = true;

      if (flag == "select") {
        var requestData = {
          schemeCode: vm.schemeCode,
        };
        rbibondNomineeContinuationService.fetchGuardianDetails(requestData).then(
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
          guardianId: vm.customerMasterDetailsThree.C025.oldValue,
        };
        rbibondNomineeContinuationService.fetchGuardianDetails(requestData).then(
          (response) => {
            vm.loader = false;
            vm.guardianDetailThree = response.data.result[0];
            vm.customerMasterDetailsThree.C026.oldValue = vm.guardianDetailThree.guardianFirstName;
            vm.customerMasterDetailsThree.C027.oldValue = vm.guardianDetailThree.guardianMiddleName;
            vm.customerMasterDetailsThree.C028.oldValue = vm.guardianDetailThree.guardianLastName;
            vm.customerMasterDetailsThree.C030.oldValue = convertToDateObject(vm.guardianDetailThree.dateOfBirth);
            vm.customerMasterDetailsThree.C031.oldValue = vm.guardianDetailThree.address;
            vm.customerMasterDetailsThree.C032.oldValue = vm.guardianDetailThree.mobileNo;
            vm.customerMasterDetailsThree.C033.oldValue = vm.guardianDetailThree.email;
          },
          function (error) {
            vm.loader = false;
            handleErrorResponse(error);
          }
        );
      }
    }

    function checkAge(isMinor, dateOfBirth, mode, list,index) {
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
              vm.customerMasterDetails[index].bondHldDob = "";
            }
            if (list == "2") {
              vm.customerMasterDetailsTwo[index].bondHldDob = "";
            }
            if (list == "3") {
              vm.customerMasterDetailsThree[index].bondHldDob = "";
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
      rbibondNomineeContinuationService.getInquiryDetails(requestData).then(
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
      rbibondNomineeContinuationService.getModifySelect(requestData).then(
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
      rbibondNomineeContinuationService.getModifySelect(requestData).then(
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

    vm.setBankCode = function(bankCode) {
        if (vm.activeIndex !== null && vm.customerMasterDetails[vm.activeIndex]) {
            vm.customerMasterDetails[vm.activeIndex].bankCode = bankCode;
        }
    };
      vm.setBranchCode = function(branchCode) {
            if (vm.activeIndex !== null && vm.customerMasterDetails[vm.activeIndex]) {
                vm.customerMasterDetails[vm.activeIndex].branchCode = branchCode;
            }
        };


  /*  function getOtherBankDetailsTorch(flag, index) {
        vm.activeIndex = index; // Track which nominee clicked

      if (flag == "torch") {
        vm.loader = true;
        vm.searchSolId = "";
        var requestData = {};
        rbibondNomineeContinuationService.getNonHdfcTorch(requestData).then(
          function (response) {
            vm.loader = false;
            vm.otherBankDetailsTorchList = response.data.result.torchValue;
            angular.element("#otherBankDetailsModal").modal("toggle");
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
          bankCodeNonMicr: vm.customerMasterDetails.bankCode,
        };
        rbibondNomineeContinuationService.getNonHdfcBranchTorch(requestData).then(
          function (response) {
            vm.loader = false;
            vm.otherBankDetailsTorchList = response.data.result.torchValue;
            angular.element("#otherBankDetailsModal1").modal("toggle");
          },
          function (error) {
            handleErrorResponse(error);
            vm.loader = false;
          }
        );
      }
    }
*/
    function getOtherBankDetailsTorchM(flag) {
      if (flag == "torch") {
        vm.loader = true;
        vm.searchSolId = "";
        var requestData = {};
        rbibondNomineeContinuationService.getNonHdfcTorch(requestData).then(
          function (response) {
            vm.loader = false;
            vm.otherBankDetailsTorchList = response.data.result.torchValue;
            angular.element("#otherBankDetailsModalM").modal("toggle");
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
          bankCodeNonMicr: vm.customerMasterDetails.C050.oldValue,
        };
        rbibondNomineeContinuationService.getNonHdfcBranchTorch(requestData).then(
          function (response) {
            vm.loader = false;
            vm.otherBankDetailsTorchList = response.data.result.torchValue;
            angular.element("#otherBankDetailsModalM1").modal("toggle");
          },
          function (error) {
            handleErrorResponse(error);
            vm.loader = false;
          }
        );
      }
    }

    function getOtherBankDetailsMicrTorchM() {
      if (flag == "torch") {
        vm.loader = true;
        vm.searchSolId = "";
        var requestData = {};
        rbibondNomineeContinuationService.getNonHdfcTorch(requestData).then(
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
        rbibondNomineeContinuationService.getNonHdfcBranchTorch(requestData).then(
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
      rbibondNomineeContinuationService.getVerifySelect(requestData).then(
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
      rbibondNomineeContinuationService.getVerifySelect(requestData).then(
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
      rbibondNomineeContinuationService.getValidationAdd(requestData).then(
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
          $state.go("rbi_bond_nominee_continuation.add");
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
      vm.customerMasterDetails.nameOfThePayee = "";
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
      rbibondNomineeContinuationService.getApplicationTorch(requestData).then(
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
      rbibondNomineeContinuationService.getApplicationTorch(requestData).then(
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

      rbibondNomineeContinuationService.getBrokerTorch(requestData).then(
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
      rbibondNomineeContinuationService.getValidationAdd(requestData).then(
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
      rbibondNomineeContinuationService.getValidationAdd(requestData).then(
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
      rbibondNomineeContinuationService.getReferenceData(requestData).then(
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
      rbibondNomineeContinuationService.getReferenceData(requestData).then(
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
      rbibondNomineeContinuationService.getReferenceData(requestData).then(
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
      rbibondNomineeContinuationService.getReferenceData(requestData).then(
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
      rbibondNomineeContinuationService.getReferenceData(requestData).then(
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
      rbibondNomineeContinuationService.getReferenceData(requestData).then(
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
      rbibondNomineeContinuationService.getReferenceData(requestData).then(
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
      rbibondNomineeContinuationService.getReferenceData(requestData).then(
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
//    vm.nominationDetails[0] = [];
function addRange(tabIndex, parentIndex)
{      if (!vm.nominationDetails[tabIndex]) {
            vm.nominationDetails[tabIndex] = [];
        }
        if (!vm.nominationDetails[tabIndex][parentIndex]) {
            vm.nominationDetails[tabIndex][parentIndex] = [];
        }

                                    vm.nominationDetails[tabIndex][parentIndex].push({   firstName: '',
                                                                                              middleName: '',
                                                                                              lastName: '',
                                                                                              addressOne: '',
                                                                                              addressTwo: '',
                                                                                              city: '',
                                                                                              state: '',
                                                                                              pinCode: '',
                                                                                              phoneNo: '',
                                                                                              sharePercentage: 0,
                                                                                              operativeAccNo: '',
                                                                                              isNomineeMinor: '',
                                                                                              relationWithHolder: '',
                                                                                              dateOfBirthOfMinor: ''});
calculateSum(tabIndex);
}

function removeNominee(tabIndex, parentIndex, nomineeIndex) {
   if (vm.nominationDetails[tabIndex] && vm.nominationDetails[tabIndex][parentIndex]) {
          if (vm.nominationDetails[tabIndex][parentIndex].length > 1) {
              vm.nominationDetails[tabIndex][parentIndex].splice(nomineeIndex, 1);
          } else {
              vm.nominationDetails[tabIndex][parentIndex] = []; // Clear if last nominee
          }
      }
      calculateSum(tabIndex);
}


vm.errorMsg = [];


    function getSubCodeList() {
      //            rbibondNomineeContinuationService.getSubCodeList().then(function(response){
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
      rbibondNomineeContinuationService.getValidationAdd(requestData).then(
        function (response) {
          vm.accountNumberDetails = response.data.result;

          if (vm.accountNumberDetails.creditAccountNumberFlag == "N") {
            rbibondNomineeContinuationService.getForacidAccountNumber(vm.accountNumberDetails.creditAccountNumber, vm.loginSolId).then(
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
            rbibondNomineeContinuationService.getForacidAccountNumber(vm.accountNumberDetails.ddPayAccountNumber, vm.loginSolId).then(
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
        rbibondNomineeContinuationService.getDepositAmountValidation(requestData).then(
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

    function calculateSum(tabIndex) {
    vm.total[tabIndex] = 0; // Initialize total for the tab

    if (vm.nominationDetails[tabIndex]) {
        for (var parentIndex in vm.nominationDetails[tabIndex]) {
            if (vm.nominationDetails[tabIndex].hasOwnProperty(parentIndex)) {
                for (var nominee of vm.nominationDetails[tabIndex][parentIndex]) {
                    vm.total[tabIndex] += parseFloat(nominee.sharePercentage || "0");
                }
            }
        }
    }

    // Ensure total percentage does not exceed 100
    if (vm.total[tabIndex] > 100) {
        vm.errorMsg[tabIndex] = true;
    } else {
        vm.errorMsg[tabIndex] = false;
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

    function submitAddForm() {
      var proceed = false;
      var message = "";

      vm.getCbsDetails();
      if (vm.getCbsDetails)
        if (vm.isNominee == "Y") {
          if (vm.total != 100) {
            proceed = false;
            message = "Sum of sharing percentage should be equal to 100";
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
//            text: "Do you want to Really Add this record?",
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
            var requestData = {
              schemeCode: vm.schemeCode,
              modeOfHolding: vm.bondHolderDetails[0].modeOfHoldingKeyword,
              applicationNo: vm.applicationNo,
              brokerAgencyCode: vm.brokerId,
              subscriptionAmount: vm.subscriptionAmount,
              bondType: vm.bondType,
              accountOpenDate: $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.accountOpeningDate)),
              valueDate: $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.accountOpeningDate)),
              periodOfDeposit: vm.periodOfDeposit,
              maturityDate: $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.maturityDate)),
              rateOfInterest: vm.rateOfInterest,
              modeOfInterestPayment: vm.modeOfInterestPayment,
              tranMode: vm.transactionMode,
              instrumentAmount: vm.instrumentAmount,
              remarks: vm.remarks,
              isNominee: vm.isNominee,
              totalSharingPercent: vm.total,
              isNri: vm.bondHolderDetails[0].isNri,
              applicationDate: $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.applicationDate)),
            };

            if (vm.bondHolderDetails[0].modeOfHoldingKeyword == "MH001" || vm.bondHolderDetails[0].modeOfHoldingKeyword == "MH004" || vm.bondHolderDetails[0].modeOfHoldingKeyword == "MH005") {
              requestData.singleBondAccNo = vm.bondHolderDetailSingleBlaNo;
              requestData.bondHolderName = vm.bondHolderDetailSingleBondHolderName;
            }
            if (vm.bondHolderDetails[0].modeOfHoldingKeyword == "MH002" || vm.bondHolderDetails[0].modeOfHoldingKeyword == "MH003") {
              requestData.firstJointHolderBlaNo = vm.bondHolderDetailFirstBlaNo;
              requestData.firstJointHolderName = vm.bondHolderDetailFirstBondHolderName;
              if (vm.bondHolderDetails[1]) {
                requestData.secondJointHolderBlaNo = vm.bondHolderDetailSecondBlaNo;
                requestData.secondJointHolderName = vm.bondHolderDetailSecondBondHolderName;
              }
            }

            if (vm.modeOfInterestPayment == "MP001") {
              requestData.operativeAccNo = vm.operativeAccNum;
              requestData.operativeAccBranchId = vm.operativeAccBranchId;
            }
            if (vm.modeOfInterestPayment == "MP002") {
              requestData.ddPoAccNo = vm.ddpoAccountNo;
            }
            if (vm.modeOfInterestPayment == "MP003") {
              requestData.bankCode = vm.bankCode;
              requestData.branchCode = vm.branchCode;
              requestData.ifscCodeOfCreditBank = vm.creditBankIfscCode;
              requestData.nameOfThePayee = vm.nameOfThePayee;
              requestData.neftCreditAccNo = vm.creditAccNo;
            }

            if (vm.transactionMode == "T") {
              requestData.branchid = vm.debitBranchId;
              requestData.instrumentType = vm.instrumentType;
              requestData.instrumentNo = vm.instrumentNo;
              requestData.instrumentDate = $filter("date")(vm.instrumentDate, "dd-MMM-yyyy");
              requestData.debitAccNo = vm.debitAccNo;
              requestData.creditAccNo = vm.bondCollectionAccount;
              requestData.tranDebitAccNo = vm.debitAccNo;
            }
            if (vm.transactionMode == "CL") {
              requestData.branchid = vm.debitBranchId;
              requestData.instrumentType = vm.instrumentType;
              requestData.instrumentNo = vm.instrumentNo;
              (requestData.instrumentDate = $rootScope.formatDateToDDMMMYYYY(vm.instrumentDate)), (requestData.tranCode = vm.tranCode);
              requestData.zoneCode = vm.zoneCode;
              (requestData.zoneDate = $rootScope.formatDateToDDMMMYYYY(vm.zoneDate)), (requestData.bankCodeNonMicr = vm.bankCodeNonMicr);
              requestData.branchCodeNonMicr = vm.branchCodeNonMicr;
              requestData.sortCode = vm.sortCode;
              requestData.debitAccNo = vm.bondClearingAccount;
              requestData.creditAccNo = vm.bondCollectionAccount;
            }
            if (vm.transactionMode == "C") {
              requestData.debitAccNo = vm.bondCashAccount;
              requestData.creditAccNo = vm.bondCollectionAccount;
            }

            if (vm.isNominee == "N") {
              requestData.nomineeDetailsList = null;
            } else {
              //nomination details
              angular.forEach(vm.nominationDetails, function (item) {
                item.dateOfBirthOfMinor = $filter("date")(item.dateOfBirthOfMinor, "dd-MMM-yyyy");
              });
              requestData.nomineeDetailsList = vm.nominationDetails;
            }

            var cbsRequestData = {
              creditAccountNumber: requestData.creditAccNo,
              depositAmount: requestData.subscriptionAmount,
              transactionType: requestData.tranMode,
              debitAccountNumber: requestData.debitAccNo,
            };

            rbibondNomineeContinuationService.submitAddForm(requestData).then(
              (response) => {
                vm.loader = false;
                vm.responseResult = response.data.result;
                //
                var msgForAdd =
                  "Bond Deposit Account Created Successfully \n Subscription Id : " +
                  vm.responseResult.boSubsId +
                  "\n GBM Tran Id : " +
                  vm.responseResult.gbmTranId +
                  "\n BLA No: " +
                  vm.responseResult.blaNo;
                Swal.fire("Added!", msgForAdd, "success");
                location.href = "#!/rbi_bond_nominee_continuation/action/inquiry";
              },
              function (error) {
                vm.loader = false;
                handleErrorResponse(error);
                $state.go("rbi_bond_nominee_continuation.add");
              }
            );
         } }
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
      rbibondNomineeContinuationService.getCbsDetails(requestData).then(
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
      rbibondNomineeContinuationService.getVerifySelect(requestData).then(
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
      rbibondNomineeContinuationService.getVerifySelect(requestData).then(
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
      rbibondNomineeContinuationService.getInquiryDetails(requestData).then(
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
      rbibondNomineeContinuationService.getInquiryDetails(requestData).then(
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
        boSubsId: vm.boSubsId,
      };
      rbibondNomineeContinuationService.getVerifySelect(requestData).then(
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
          $state.go("rbi_bond_nominee_continuation.list");
        }
      );
    }

    function getInquirySelect() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        blaNo: vm.blaNo,
        boSubsId: vm.boSubsId,
      };
      rbibondNomineeContinuationService.getInquiryDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.auditInfo = response.data.result;
          vm.getSubscriptionDetails = vm.auditInfo.masterDetailsValueModel[0];
          vm.getNomineeDetails = vm.auditInfo.nomineeValueModel;
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
          $state.go("rbi_bond_nominee_continuation.list");
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
            boSubsId: vm.auditInfo.boSubsId,
            lastVerifiedBy: vm.lastVerifiedBy,
          };

          rbibondNomineeContinuationService.submitVerifyForm(requestData).then(
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
                    vm.responseResult.boSubsId +
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
                    vm.responseResult.boSubsId +
                    "\nGBS Tran ID: " +
                    vm.responseResult.gbmTranId +
                    "\nCBS Tran ID: " +
                    vm.responseResult.cbsTranId +
                    "\nBLA No: " +
                    vm.getSubscriptionDetails.C006.oldValue;
                }
              }

              Swal.fire("Verified!", vm.message, "success");

              location.href = "#!/rbi_bond_nominee_continuation/action/inquiry";
            },
            function (error) {
              vm.loader = false;
              handleErrorResponse(error);
              location.href = "#!/rbi_bond_nominee_continuation/action/inquiry";
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
            boSubsId: vm.auditInfo.boSubsId,
          };

          rbibondNomineeContinuationService.submitDeleteForm(requestData).then(
            (response) => {
              vm.loader = false;
              Swal.fire("Deleted!", "BOND Deposit Account Deleted Successfully", "success");
              location.href = "#!/rbi_bond_nominee_continuation/action/inquiry";
            },
            function (error) {
              vm.loader = false;
              handleErrorResponse(error);
            }
          );
       } },
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
        boSubsId: vm.boSubsId,
      };
      rbibondNomineeContinuationService.getModifySelect(requestData).then(
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
          $state.go("rbi_bond_nominee_continuation.modify");
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
      rbibondNomineeContinuationService.getModifySelect(requestData).then(
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

function getPanValidation() {
                                vm.loader = true;
                                var requestData = {};
                                 vm.list=[];
                                 vm.singleList=[];

                                  vm.nominationDetails1.forEach(item => {
                                          if (item.panNum) { // Ensure the panNo is not empty or undefined
                                              vm.list.push({  nomineeName: item.nomineeName,
                                                                        dateOfBirthOfMinor: $filter("date")(item.nomineeDob, "dd-MMM-yyyy"),
                                                                        panNo: item.panNum});
                                          }
                                      });
                                 requestData.schemeCode = vm.schemeCode;
                                 requestData.proceedsToBePaid="N";
                                 requestData.detailsOrSubmit='valid';
                                requestData.panNumList = vm.list;
                                rbibondNomineeContinuationService.getPanValidation(requestData).then(function (response) {
                                           vm.loader = false;
 vm.objList = response.data.result; // Success PANs
      vm.failedList = response.data.result1; // Failed PANs
      vm.panValid = true; // ✅ Assume all PANs are valid initially
if (vm.objList.length === 0 && vm.failedList.length === 0) {
    Swal.fire("Error!", "Invalid response received. No PAN validation data found.", "error");
} else {
    let panMessages = [];
    if (null!=vm.objList&&vm.objList.length > 0) {
        vm.objList.forEach(obj => panMessages.push(`'${obj.panNumber}' - Success`));
    }
    if (null!=vm.failedList&&vm.failedList.length > 0) {
        vm.failedList.forEach(obj => panMessages.push(`'${obj.panNumber}' - Failure`));
     }
    Swal.fire({
        title: "PAN Validation Result",
        text: panMessages.join("\n"),
        icon: vm.allPANsValid ? "success" : "warning"
    });
}
}, function (error) {
                if(error.data.message.toLowerCase().startsWith('trigger')){
                    handleErrorResponse(error);
                    vm.loader = false;
                    }
                    else{
                    vm.panValid = true;
                    handleErrorResponse(error);
                    $state.go('rbi_bond_nominee_continuation.add');
                    vm.loader = false;
                    }
                    });
                            }

    //Get Account Number for Modify
    function getAccountNumberForModify() {
      vm.loader = true;
      var requestData = {
        scssCustomerId: vm.accountOpening.customerId,
      };
      rbibondNomineeContinuationService.getModifySelect(requestData).then(
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

            rbibondNomineeContinuationService.getCbsDetails(requestData).then(
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

                  rbibondNomineeContinuationService.getModifySubmit(requestData).then(
                    (response) => {
                      vm.loader = false;
                      Swal.fire("Modified!", "Details Modified Successfully", "success");
                      location.href = "#!/rbi_bond_nominee_continuation/action/inquiry";
                    },
                    function (error) {
                      vm.loader = false;
                      handleErrorResponse(error);
                    }
                  );
                } else {
                  $timeout(Swal.fire("Warning", vm.message, "warning"), 30000);
                  $state.go("rbi_bond_nominee_continuation.modify");
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

            rbibondNomineeContinuationService.getModifySubmit(requestData).then(
              (response) => {
                vm.loader = false;
                var message = "";
                if (result.verifiedStatus == "N" && result.lastVerifiedBy) {
                  message = "Bond Subscription – Mode of Interest Payment/Principal Repayment details Modified Successfully";
                } else {
                  message = "Bond Subscription Workflow Modified Successfully";
                }

                Swal.fire("Modified!", message, "success");
                location.href = "#!/rbi_bond_nominee_continuation/action/inquiry";
              },
              function (error) {
                vm.loader = false;
                handleErrorResponse(error);
              }
            );
         } }
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

          rbibondNomineeContinuationService.getModifySubmit(requestData).then(
            (response) => {
              vm.loader = false;
              Swal.fire("Modified!", "Details Modified Successfully", "success");
              location.href = "#!/rbi_bond_nominee_continuation/action/inquiry";
            },
            function (error) {
              vm.loader = false;
              handleErrorResponse(error);
            }
          );
      }  }
      );
    }

    function getAccountPage() {
      vm.accountDetails = true;
      vm.nomination = false;
      if ($state.current.name == "rbi_bond_nominee_continuation.inquiry_detail") {
        vm.matrix = false;
      }
    }

    function getNominationPage() {
      vm.accountDetails = false;
      vm.nomination = true;
      if ($state.current.name == "rbi_bond_nominee_continuation.inquiry_detail") {
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

    function nextStep() {
      var $active = $(".from_wrapper .nav-tabs li.active");
      nextTab($active);
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

     vm.validationMessages = [];
        function validatePanLetter(panNum,index) {
    		 vm.validationMessages = [];
           vm.validationMessages[index] = ""; // Clear previous message for this index

             if (null!=panNum&&panNum.length >= 4) {
               var fourthLetter = panNum.charAt(3).toUpperCase();

        if (fourthLetter !== "P") {
                 vm.validationMessages[index] = "Fourth letter of The Pan should be P.";
       	  vm.nominationDetails1[index].panNum = '';
        }

             }
           }

    function validatePanLetter1() {
      vm.validationMessages1 = "";

      if (vm.pan_No2.length >= 4) {
        var fourthLetter = vm.pan_No2.charAt(3).toUpperCase();

        if (["MH001", "MH002", "MH003", "MH005"].includes(vm.modeOfHolding) && fourthLetter !== "P") {
          vm.validationMessages1 = "Fourth letter of The Pan should be P.";
        } else if (["MH004"].includes(vm.modeOfHolding) && fourthLetter !== "H") {
          vm.validationMessages1 = "For HUF, the fourth letter should be H.";
        }
      }
    }

    function validatePanLetter2() {
      vm.validationMessages2 = "";

      if (vm.pan_No3.length >= 4) {
        var fourthLetter = vm.pan_No3.charAt(3).toUpperCase();

        if (["MH001", "MH002", "MH003", "MH005"].includes(vm.modeOfHolding) && fourthLetter !== "P") {
          vm.validationMessages2 = "Fourth letter of The Pan should be P.";
        } else if (["MH004"].includes(vm.modeOfHolding) && fourthLetter !== "H") {
          vm.validationMessages2 = "For HUF, the fourth letter should be H.";
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
      //                         if(vm.minorStatus2 === 'YES'){
      //                         vm.customerMasterDetailsTwoFlag='Y';
      //                         }else{
      //                         vm.customerMasterDetailsTwo.isMinor='N';
      //
      //                         }
    }

    function updateMinorStatus1() {
    vm.isMinor=[];
           for(let i=0;i<vm.nominationDetails1.length;i++){
          vm.isMinor[i] = isMinor(vm.nominationDetails1[i].nomineeDob) ? "Y" : "N";
 // Ensure customerMasterDetails array is initialized
        if (!vm.customerMasterDetails[i]) {
            vm.customerMasterDetails[i] = {};
        }

        // Convert Y/N to Yes/No and store it
        vm.customerMasterDetails[i].isMinor = vm.isMinor[i];
            }
          //                         if(vm.minorStatus2 === 'YES'){
          //                         vm.customerMasterDetailsTwoFlag='Y';
          //                         }else{
          //                         vm.customerMasterDetailsTwo.isMinor='N';
          //
          //                         }
        }

    function updateMinorStatus3() {
      vm.customerMasterDetailsThreeFlag = vm.isMinor(vm.fromDate3) ? "Y" : "N";
      //                          if(vm.minorStatus2 === 'YES'){
      //                                                  vm.customerMasterDetailsThree.isMinor='Y';
      //                                                  }else{
      //                                                  vm.customerMasterDetailsThree.isMinor='N';
      //
      //                                                  }
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

    function isMinorSingle(index) {
      // Parse the input date (format: dd/MM/yyyy)
      var dobInput = $filter("date")(vm.nominationDetails1[index].nomineeDob, "dd/MM/yyyy");
      if(dobInput){
      const [day, month, year] = dobInput.split("/");
      const birthDate = new Date(`${year}-${month}-${day}`); // Create a Date object from the input

      // Calculate the difference in years between the current date and the birth date
      const today = new Date();
      const age = today.getFullYear() - birthDate.getFullYear();

      // Adjust the age if the current date is before the birth date this year
      const monthDiff = today.getMonth() - birthDate.getMonth();
      const dayDiff = today.getDate() - birthDate.getDate();
      const isMinor = age < 18 || (age === 18 && (monthDiff < 0 || (monthDiff === 0 && dayDiff < 0)));

      vm.isMinor = isMinor ? "Y" : "N";
            vm.customerMasterDetails.push({ isMinor: vm.isMinor});
            }
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

    //   // Function to convert number to words
    //   function convertNumberToWords(amount) {
    //       if (amount === 0) return 'Zero';
    //
    //       const below20 = [
    //           'Zero', 'One', 'Two', 'Three', 'Four', 'Five', 'Six', 'Seven', 'Eight', 'Nine',
    //           'Ten', 'Eleven', 'Twelve', 'Thirteen', 'Fourteen', 'Fifteen', 'Sixteen', 'Seventeen',
    //           'Eighteen', 'Nineteen'
    //       ];
    //
    //       const tens = [
    //           '', '', 'Twenty', 'Thirty', 'Forty', 'Fifty', 'Sixty', 'Seventy', 'Eighty', 'Ninety'
    //       ];
    //
    //       const thousands = ['', 'Thousand', 'Million', 'Billion', 'Trillion'];
    //
    //       let words = '';
    //
    //       let i = 0;
    //       while (amount > 0) {
    //           if (amount % 1000 !== 0) {
    //               words = convertChunk(amount % 1000) + (thousands[i] ? ' ' + thousands[i] : '') + ' ' + words;
    //           }
    //           amount = Math.floor(amount / 1000);
    //           i++;
    //       }
    //
    //       return words.trim();
    //   }
function convertAmountToWords(amount) {
    if (amount == "0") {
        return "Zero";
    } else if (amount && !isNaN(amount)) {
        return convertNumberToWords(parseFloat(amount)); // Ensure it's a number
    } else if (amount === "") {
        return "";
    } else {
        return "Zero";
    }
}

function convertNumberToWords(amount) {
    if (amount === 0) return "Zero";

    let rupees = Math.floor(amount); // Get integer part (rupees)
    let paise = Math.round((amount - rupees) * 100); // Get decimal part (paise)

    let rupeeWords = convertLargeNumberToWords(rupees);
    let paiseWords = paise > 0 ? convertChunkToWords(paise) + " Paise" : "";

    if (rupeeWords && paiseWords) {
        return rupeeWords + " and " + paiseWords;
    } else if (rupeeWords) {
        return rupeeWords + " Rupees";
    } else {
        return paiseWords;
    }
}

// Converts large numbers into words (handles thousands, lakhs, crores)
function convertLargeNumberToWords(num) {
    const scales = ["", "Thousand", "Lakh", "Crore"];
    let scaleIndex = 0;
    let words = [];

    while (num > 0) {
        let chunk;
        if (scaleIndex === 1 || scaleIndex === 2) {
            chunk = num % 100; // Thousand and Lakh have two-digit chunks
            num = Math.floor(num / 100);
        } else {
            chunk = num % 1000; // Handle Hundreds normally
            num = Math.floor(num / 1000);
        }

        if (chunk > 0) {
            words.unshift(convertChunkToWords(chunk) + (scales[scaleIndex] ? " " + scales[scaleIndex] : ""));
        }

        scaleIndex++;
    }

    return words.join(" ").trim();
}

// Converts numbers into words (handles up to 999)
function convertChunkToWords(num) {
    const below20 = [
        "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
        "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
        "Seventeen", "Eighteen", "Nineteen"
    ];

    const tens = ["", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"];

    if (num === 0) return "";
    if (num < 20) return below20[num];
    if (num < 100) return tens[Math.floor(num / 10)] + (num % 10 ? " " + below20[num % 10] : "");
    return below20[Math.floor(num / 100)] + " Hundred" + (num % 100 ? " and " + convertChunkToWords(num % 100) : "");
}

// Example usage:
console.log(convertAmountToWords(15000)); // "Fifteen Thousand Rupees"
console.log(convertAmountToWords(1250000)); // "Twelve Lakh Fifty Thousand Rupees"
console.log(convertAmountToWords(152.75)); // "One Hundred and Fifty-Two Rupees and Seventy-Five Paise"



    vm.isMinorAge = function (dateOfBirth,index) {
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
