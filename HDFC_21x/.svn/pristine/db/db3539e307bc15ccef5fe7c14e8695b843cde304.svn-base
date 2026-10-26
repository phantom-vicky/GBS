(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('CumToNonCumChangeOfBondTypeController',
        CumToNonCumChangeOfBondTypeController);

    CumToNonCumChangeOfBondTypeController.$inject = ['$scope', '$filter', '$state', '$rootScope',
        '$stateParams', '$location', '$timeout', 'cumToNonCumChangeOfBondTypeService', 'commonService'
    ];

    function CumToNonCumChangeOfBondTypeController($scope, $filter, $state, $rootScope, $stateParams,
        $location, $timeout, cumToNonCumChangeOfBondTypeService, commonService) {


	var vm = this;
//	    vm.panPattern = /[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}/;



    vm.rejectReason = function () {
      angular.element("#permissionMappingDelete").modal("toggle");
    };
      vm.getRemarksList = function () {
          vm.referenceId = "003";
          var requestData = { referenceId: vm.referenceId };

          cumToNonCumChangeOfBondTypeService.getRemarksList(requestData).then(
            function (response) {
              vm.remarksList = response.data.result || []; // Ensure it's an array
            },
            function (error) {
              handleErrorResponse(error);
            }
          );
        };

        vm.deleteSubmit = function () {
              vm.loader = true;

                                                                       let requestData = {
                                                                                                          'schemeCode': vm.getDetails.schemeCode,
                                                                                                          'subscriptionId' : vm.getDetails.subscriptionId,
                                                                                                                                           'blaNo' : vm.getDetails.blaNo,
                                                                                                                                           'rejectionRemarks':vm.rejectionRemarks
                                                                                                          };
              cumToNonCumChangeOfBondTypeService.deleteSubmit(requestData).then(
                function (response) {
                  if (response.data.message == "SUCCESS") {
                    Swal.fire("Success!", "Cumulative to Non-Cumulative change of Bond Type Rejected Successfully", "success");
                    location.href = "#!/cum_to_non_cum_change_of_bond_type/action/inquiry";
                  } else {
                    Swal.fire("WARNING!", response.data.message, "error");
                    location.href = "#!/cum_to_non_cum_change_of_bond_type/action/verify/detail";
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
                  location.href = "#!/cum_to_non_cum_change_of_bond_type/action/verify/detail";
                }
              );
            };

        vm.validationMessages ='';
        vm.modeOfHolding = ''; // "Single", "Joint", "Jointly", or "HUF"
        vm.validatePanLetter = validatePanLetter;


		vm.showCancelConfirmation = showCancelConfirmation;
		vm.redirectToInputPage  = redirectToInputPage;
		//vm.getScssCommonMetaData = getScssCommonMetaData;
        vm.itemsPerPage = "5";
		vm.scssOtherBanksDetails = [{}];
		vm.customerMasterDetails=[];
		vm.customerMasterJointDetails=[];
		vm.customerMasterFormDetails={};
		vm.getState=getState;
		vm.getClearAllData=getClearAllData;
		vm.getClearAllDatas=getClearAllDatas;
		vm.getSolDetails=getSolDetails;
		vm.getSchemeTypeListOperative=getSchemeTypeListOperative;
		vm.getSchemeTypeListOperativeJoint=getSchemeTypeListOperativeJoint;
		vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
		vm.panPattern = /[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}/;
        vm.emailPattern = /[^@]+@[^@]+\.[a-zA-Z]{2,6}/;
        vm.optionToggled =optionToggled;
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
        vm.getsubscriptionId = getsubscriptionId;
        vm.getsubscriptionIdInq = getsubscriptionIdInq;
        vm.getsubscriptionIdVer = getsubscriptionIdVer;
        vm.getblaNoVer = getblaNoVer;
        vm.getPantorchVer = getPantorchVer;
        vm.getRbiBondModuleNameDetailsVer = getRbiBondModuleNameDetailsVer;
        vm.alert = alert;
        vm.getModeofHolding = getModeofHolding;
        vm.addcustomerMaster = addcustomerMaster;
        vm.convertYNToBoolean = convertYNToBoolean;
        vm.getDashBoard = getDashBoard;
        vm.getMaturityDate = getMaturityDate;
        vm.handleHdfcSelection = handleHdfcSelection;

        vm.getCbsCustFstJoinDetails = getCbsCustFstJoinDetails;
        vm.getCbsCustScndJoinDetails = getCbsCustScndJoinDetails;
        vm.getBlaAccNoDetails = getBlaAccNoDetails;
        vm.getScssJointCustDetails = getScssJointCustDetails;
        vm.getCurrentFinancialYear = getCurrentFinancialYear;
        vm.getVerifySelectDetails = getVerifySelectDetails;
        vm.verifyCustomerMaster = verifyCustomerMaster;
//        vm.deleteCustomerMaster = deleteCustomerMaster;
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
        vm.getModuleNameAdd = getModuleNameAdd;
        vm.formFifteenGValidation = formFifteenGValidation;
        vm.convertBooleanToYN = convertBooleanToYN;
        vm.minAgeC=minAgeC;
        vm.nextStep=nextStep;
        vm.nextTab=nextTab;
        vm.convertDateFormat=convertDateFormat;

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
//sub

  vm.errorMsg = [];
        vm.accountOpening = [];
		vm.nominationDetails = [{}];
		vm.accountNumberDetails = [];
		vm.accountOpeningMaster = [];
		vm.errorNomineeOperativeAcctNo = [];
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.systemDate = $rootScope.globals.currentUser.systemDate;
        vm.currentDate = $rootScope.globals.currentUser.systemDate;
        vm.solDate = $filter('date')(vm.systemDate, 'dd/MM/yyyy');
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
        vm.getSubCodeList = getSubCodeList;
        vm.getOperativeCreditAccount = getOperativeCreditAccount;
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
        vm.getTorchDetails = getTorchDetails
        vm.calculateAge = calculateAge;
        vm.instrumentDateValidation = instrumentDateValidation;
        vm.getSchemeTypeListOperativeNominee = getSchemeTypeListOperativeNominee;
        vm.getAccountPage = getAccountPage;
        vm.getNominationPage = getNominationPage;
        vm.getMatrixPage = getMatrixPage;
        vm.getDebitAccountName = getDebitAccountName;
        vm.getCbsDetails = getCbsDetails;
        vm.neftPlaceholderFlgOut;

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
        vm.getModuleName = getModuleName;
                vm.getModuleNameInq = getModuleNameInq;
        vm.getModuleNameAdd = getModuleNameAdd;
//        vm.getAddBlaTorch = getAddBlaTorch;
        vm.getAddBlaDetails = getAddBlaDetails;
        vm.convertLabelValue = convertLabelValue;
        vm.getApplicationNoTorch = getApplicationNoTorch;
        vm.getApplicationDetails = getApplicationDetails;
        vm.getBrokerTorch = getBrokerTorch;
        vm.getPantorch = getPantorch;
        vm.getPantorchInq = getPantorchInq;

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

        vm.alert = alert;
        vm.verifyBlaTorch = verifyBlaTorch;
        vm.verifySubscriptionTorch = verifySubscriptionTorch;
      vm.validateOperativeAccNumForDebit = validateOperativeAccNumForDebit;
      vm.getOtherBankDetailsMicrTorch = getOtherBankDetailsMicrTorch;
      vm.getOtherBankDetailsMicrTorchM = getOtherBankDetailsMicrTorchM;
      vm.isMinor=isMinor;


      //
        getCurrentFinancialYear();

        vm.loginSolDate = $rootScope.globals.currentUser.solDate;

        vm.genderListMetaData = [
            {label : "MALE",value : "M"},
            {label : "FEMALE",value : "F"},

        ];

        vm.supportingDocumentsMetaData = [
            {label : "No",value : 'N'},
            {label : "Yes",value : 'Y'},
        ];
       vm.yesNo = [
            {label : "No",value : 'N'},
            {label : "Yes",value : 'Y'},
        ];
      vm.bondTypeMetaData = [

             {label : "Cummulative",value : 'N'},
             {label : "Non-Cummulative",value : 'NC'},
             {label : "Both",value : 'B'},
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

		if($state.current.name == 'cum_to_non_cum_change_of_bond_type.add_detail') {
		    if($stateParams.obj){
                    vm.blaNo = $stateParams.obj.blaNo;
                    vm.schemeCode = $stateParams.obj.schemeCode;
                    vm.panNum = $stateParams.obj.panNum;
                    vm.subscriptionId = $stateParams.obj.subscriptionId;
                    vm.bondHolderName = $stateParams.obj.bondHolderName;
                vm.getTorchDetails('submit1',vm.blaNo,vm.schemeCode,vm.panNum,vm.subscriptionId,vm.bondHolderName);

//                getCurrentFinancialYear();
//                getModuleName();
//		        titleChange();
//		        getTypeofHolding();
//		        getFormDetails();
//		        getKycDetails();
//		        getGuardianDetailsMetaData();
//		        getTransactionModes();
//		        getSchemeTypeListOperative();
//
//		        addDet();
//
////		        getTdsApplicableRate();
//		        getModeOfInterestPayment();
//		        getState();
//		        isMinor();
		    }
		}else if($state.current.name == 'cum_to_non_cum_change_of_bond_type.verify') {
//          getVerifySelectDetails();
        }else if($state.current.name == 'cum_to_non_cum_change_of_bond_type.verify_detail') {
        if ($stateParams.obj) {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.blaNo = $stateParams.obj.blaNo;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.subscriptionId = $stateParams.obj.subscriptionId;
        getVerifySelectDetails('select1');





}
         }else if($state.current.name == 'cum_to_non_cum_change_of_bond_type.delete_detail') {
               if($stateParams.obj){
                   vm.schemeCode = $stateParams.obj.schemeCode;
                   vm.blaNo = $stateParams.obj.blaNo;
                   vm.schemeName = $stateParams.obj.schemeName;
                   getVerifySelectDetails('select');
                   getCurrentFinancialYear();
                   getTypeofHolding();
                   getFormDetails();
                   getKycDetails();
                   getGuardianDetailsMetaData();
                   getModeofHolding();
                   getState();
               }
         }
         else if($state.current.name == 'cum_to_non_cum_change_of_bond_type.list_detail') {
                if($stateParams.obj){

                    vm.blaNo = $stateParams.obj.blaNo;
                    vm.schemeCode = $stateParams.obj.schemeCode;
                    vm.schemeName = $stateParams.obj.schemeName;
                    vm.panNum = $stateParams.obj.panNum;
                    vm.subscriptionId = $stateParams.obj.subscriptionId;
                    vm.bondHolderName = $stateParams.obj.bondHolderName;
                    getInquirySelectDetails('select1',vm.blaNo,vm.schemeCode,vm.schemeName,vm.panNum,vm.subscriptionId,vm.bondHolderName);
                    getCurrentFinancialYear();
                    titleChange();
                    getTypeofHolding();
                    getFormDetails();
                    getKycDetails();
                    getGuardianDetailsMetaData();
                    getModeofHolding();
                    getState();
//                    getPantorch();
//                    getModeOfInterestPayment();
//                    getTransactionModes();



                }
          }else if($state.current.name == 'cum_to_non_cum_change_of_bond_type.add') {
              getDashBoard()
              }
              else if($state.current.name == 'cum_to_non_cum_change_of_bond_type.add_input') {
              if($stateParams.obj){
                vm.id = $stateParams.obj.id;
                 vm.modeOfHolding = $stateParams.obj.modeOfHolding;
             getDashBoard();
                   }
             }




                function redirectToInputPage(state) {
                            $state.go(state);
                         }

        function convertLabelValue(list){
            for(var item in list){
                list[item].value = list[item].SUBCODE;
                list[item].label = list[item].DESCRIPTION;
            }
            return list;
        }

        function formFifteenGValidation(value){
            if(vm.customerMasterDetails.bondHldDob){
                vm.customerMasterDetails.dateSubmission15g15htax = '';
                var year = parseInt($filter('date')(vm.customerMasterDetails.bondHldDob, "yyyy"));
                var cYear = parseInt($filter('date')(new Date(), "yyyy"));
                var age = cYear - year;
                            if(value == 'TF001'){
                                //15g
                                if(age > 60){
                                    Swal.fire("Error!", "Age of the holder is NOT BELOW 60 years", "error");
                                    vm.customerMasterDetails.form15g15htaxExeCerti = '';
                                }
                                vm.customerMasterDetails.tdsRateApplicable = '0';
                            }
                            if(value == 'TF002'){
                                //15h
                                if(age <= 60){
                                    Swal.fire("Error!", "Age of the holder is BELOW 60 years", "error");
                                    vm.customerMasterDetails.form15g15htaxExeCerti = '';
                                }
                                vm.customerMasterDetails.tdsRateApplicable = '0';
                            }
                            if(value == 'TF003'){
                                //tax excemption cert
                                vm.customerMasterDetails.tdsRateApplicable = '0';
                            }
                            if(value == 'NONE'){
                                    vm.customerMasterDetails.tdsRateApplicable = vm.tdsRateApplicableResponse;
                            }
            }else{
                 Swal.fire("Error!", "Please select a age", "error");
                  vm.customerMasterDetails.form15g15htaxExeCerti = '';
            }


        }

        function formFifteenGValidationM(value){
                    if(vm.customerMasterDetails.C013.oldValue){

                        var year = parseInt($filter('date')(vm.customerMasterDetails.C013.oldValue, "yyyy"));
                        var cYear = parseInt($filter('date')(new Date(), "yyyy"));
                        var age = cYear - year;
                                    if(value == 'TF001'){
                                        //15g
                                        if(age > 60){
                                            Swal.fire("Error!", "Age of the holder is NOT BELOW 60 years", "error");
                                            vm.customerMasterDetails.C038.oldValue = '';
                                        }
                                        vm.customerMasterDetails.C064.oldValue = '0';
                                    }
                                    if(value == 'TF002'){
                                        //15h
                                        if(age <= 60){
                                            Swal.fire("Error!", "Age of the holder is BELOW 60 years", "error");
                                            vm.customerMasterDetails.C038.oldValue = '';
                                        }
                                        vm.customerMasterDetails.C064.oldValue = '0';
                                    }
                                    if(value == 'TF003'){
                                        //tax excemption cert
                                        vm.customerMasterDetails.C064.oldValue = '0';
                                    }
                                    if(value == 'NONE'){
                                            try{
                                                vm.customerMasterDetails.C039.oldValue = '';
                                            }catch(e){}

                                            vm.customerMasterDetails.C064.oldValue = vm.tdsRateApplicableResponse;
                                    }
                    }else{
                         Swal.fire("Error!", "Please select a age", "error");
                          vm.customerMasterDetails.C038.oldValue = '';
                    }


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
                               vm.resultDelArray.push({ colName: 'Id', value: id });
                           }

                           if (vm.coparcenerslist.length === 0) {
                               vm.enableDelete = false;
                           }
                       }
      function alert(item){

                         for (let i = 0 ;i < vm.schemeList.length;i++){
                          if(vm.schemeList[i].subCode == item){
                                vm.schemeName = vm.schemeList[i].subCodeDescription;
                               //vm.cusName = vm.custList[i].cusName;
                          //  vm.cusAlphaCode = vm.custList[i].cusAlphaCode;
                           // vm.id = vm.custList[i].id;
                           }
                         }
                      }


     vm.guardianDetails = [];
     vm.showGuardianDetails = false;


 function addGuardian() {
     if (vm.guardianDetailsValueModel.C014 && vm.guardianDetailsValueModel.C014.oldValue) {
                  // Log data before adding
                  console.log("Adding Guardian Details: ", vm.guardianDetailsValueModel);

                  // Push a copy of the new guardian details to the array
                  vm.guardianDetails.push(angular.copy(vm.guardianDetailsValueModel));

                  // Log the updated array
                  console.log("Updated Guardian Details Array: ", vm.guardianDetails);

                  // Show the guardian details table
                  vm.showGuardianDetails = true;
                     // Clear or reset the guardianDetailsValueModel if needed
                     vm.guardianDetailsValueModelOne.C003.oldValue=vm.guardianDetailsValueModel.C003.oldValue;
                     vm.guardianDetailsValueModelOne = {
                     C003: { oldValue: vm.guardianDetailsValueModelOne.C003.oldValue },
                     C005: { oldValue: vm.guardianDetailsValueModelOne.C005.oldValue },
                     C006: { oldValue: vm.guardianDetailsValueModelOne.C006.oldValue },
                     C007: { oldValue: vm.guardianDetailsValueModelOne.C007.oldValue },
                     C008: { oldValue: vm.guardianDetailsValueModelOne.C008.oldValue },
                     C009: { oldValue: vm.guardianDetailsValueModelOne.C009.oldValue },
                     C010: { oldValue: vm.guardianDetailsValueModelOne.C010.oldValue },
                     C011: { oldValue: vm.guardianDetailsValueModelOne.C011.oldValue },
                     C012: { oldValue: vm.guardianDetailsValueModelOne.C012.oldValue },
                     C013: { oldValue: vm.guardianDetailsValueModelOne.C013.oldValue },
                     C014: { oldValue: vm.guardianDetailsValueModelOne.C014.oldValue },  // Clear only the Date of Death of Guardian
                     C015: { oldValue: vm.guardianDetailsValueModelOne.C015.oldValue },
                     C016: { oldValue: vm.guardianDetailsValueModelOne.C016.oldValue }
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
                             vm.guardianDetailsValueModelTwo.C003.oldValue=vm.guardianDetailsValueModelTwo.C003.oldValue;
                             vm.guardianDetailsValueModelTwo = {
                             C003: { oldValue: vm.guardianDetailsValueModelTwo.C003.oldValue },
                             C005: { oldValue: vm.guardianDetailsValueModelTwo.C005.oldValue },
                             C006: { oldValue: vm.guardianDetailsValueModelTwo.C006.oldValue },
                             C007: { oldValue: vm.guardianDetailsValueModelTwo.C007.oldValue },
                             C008: { oldValue: vm.guardianDetailsValueModelTwo.C008.oldValue },
                             C009: { oldValue: vm.guardianDetailsValueModelTwo.C009.oldValue },
                             C010: { oldValue: vm.guardianDetailsValueModelTwo.C010.oldValue },
                             C011: { oldValue: vm.guardianDetailsValueModelTwo.C011.oldValue },
                             C012: { oldValue: vm.guardianDetailsValueModelTwo.C012.oldValue },
                             C013: { oldValue: vm.guardianDetailsValueModelTwo.C013.oldValue },
                             C014: { oldValue: vm.guardianDetailsValueModelTwo.C014.oldValue },  // Clear only the Date of Death of Guardian
                             C015: { oldValue: vm.guardianDetailsValueModelTwo.C015.oldValue },
                             C016: { oldValue: vm.guardianDetailsValueModelTwo.C016.oldValue }
                             };
                              vm.guardianDetails.push(convertToColumnValuesArrayList(vm.guardianDetailsValueModelTwo));
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
                             vm.guardianDetailsValueModelThree.C003.oldValue=vm.guardianDetailsValueModelThree.C003.oldValue;
                             vm.guardianDetailsValueModelThree = {
                             C003: { oldValue: vm.guardianDetailsValueModelThree.C003.oldValue },
                             C005: { oldValue: vm.guardianDetailsValueModelThree.C005.oldValue },
                             C006: { oldValue: vm.guardianDetailsValueModelThree.C006.oldValue },
                             C007: { oldValue: vm.guardianDetailsValueModelThree.C007.oldValue },
                             C008: { oldValue: vm.guardianDetailsValueModelThree.C008.oldValue },
                             C009: { oldValue: vm.guardianDetailsValueModelThree.C009.oldValue },
                             C010: { oldValue: vm.guardianDetailsValueModelThree.C010.oldValue },
                             C011: { oldValue: vm.guardianDetailsValueModelThree.C011.oldValue },
                             C012: { oldValue: vm.guardianDetailsValueModelThree.C012.oldValue },
                             C013: { oldValue: vm.guardianDetailsValueModelThree.C013.oldValue },
                             C014: { oldValue: vm.guardianDetailsValueModelThree.C014.oldValue },  // Clear only the Date of Death of Guardian
                             C015: { oldValue: vm.guardianDetailsValueModelThree.C015.oldValue },
                             C016: { oldValue: vm.guardianDetailsValueModelThree.C016.oldValue }
                             };
                              vm.guardianDetails.push(convertToColumnValuesArrayList(vm.guardianDetailsValueModelThree));
                         }
    }

function getDashBoard() {
    vm.loader = true;

    if (vm.id == null || vm.id == undefined) {
        var requestData = {};
        cumToNonCumChangeOfBondTypeService.getAddDashBoard(requestData).then(function (response) {
            vm.loader = false;
            vm.netBankingList = response.data.result;

        }, function (error) {
            vm.loader = false;
            handleErrorResponse(error);
        });
    } else {
        var requestData = {
            'id': vm.id,
            'modeOfHolding': vm.modeOfHolding,
        };

        cumToNonCumChangeOfBondTypeService.getAddDashBoard(requestData).then((response) => {
            vm.loader = false;
            vm.netBankingList = response.data.result[0];
            vm.pan_No1 = response.data.result[0].pan_No1;
            vm.bondHolderName = response.data.result[0].bondHolderName;
            vm.modeOfHolding = response.data.result[0].modeOfHolding;
            vm.schemeCode = response.data.result[0].schemeCode;
            vm.schemeName = response.data.result[0].schemeName;
            vm.moduleCode = response.data.result[0].moduleCode;
            vm.liquidationDate = response.data.result[0].liquidationDate;

            const bondHldDob = response.data.result[0].bondHldDob;
            console.log('bondHldDob:', bondHldDob); // Log to check the value

            if (bondHldDob) { // Check if bondHldDob is defined
                const dateParts = bondHldDob.split('-');
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
                        DEC: 11
                };

                const day = parseInt(dateParts[0], 10);
                const month = monthMap[dateParts[1]];
                const year = parseInt(dateParts[2], 10);

                vm.fromDate1 = new Date(year, month, day);
                console.log('Parsed fromDate1:', vm.fromDate1); // Log the parsed date
            } else {
                console.warn('bondHldDob is not defined or empty'); // Warn if the value is not available
            }

        }, function (error) {
            vm.loader = false;
            handleErrorResponse(error);
        });
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

        function validateCount(value) {
            try{
                var intee = parseInt(value);
                if(intee <= 3 && intee > 1){

                }else{
                    vm.noOfJointAcc = '';
                }
            }catch(error){
                vm.noOfJointAcc = '';
            }
        }

        function getModuleNameInq() {
            vm.loader = true;

            var requestData = {
            };
            cumToNonCumChangeOfBondTypeService.getRbiBondModuleNameDetailsInq(requestData).then(function (response) {
                vm.loader = false;
                vm.schemeList = response.data.result;
                angular.element('#schemeModel').modal('toggle');
            }, function (error) {
                handleErrorResponse(error);
                vm.loader = false;
            });
        }
                function getRbiBondModuleNameDetailsVer() {
                    vm.loader = true;

                    var requestData = {
                    };
                    cumToNonCumChangeOfBondTypeService.getRbiBondModuleNameDetailsVer(requestData).then(function (response) {
                        vm.loader = false;
                        vm.schemeList = response.data.result;
                        angular.element('#schemeModel').modal('toggle');
                    }, function (error) {
                        handleErrorResponse(error);
                        vm.loader = false;
                    });
                }
        function getModuleName() {
            vm.loader = true;

            var requestData = {
            };
            cumToNonCumChangeOfBondTypeService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                vm.loader = false;
                vm.schemeList = response.data.result;
                angular.element('#schemeModel').modal('toggle');
            }, function (error) {
                handleErrorResponse(error);
                vm.loader = false;
            });
        }
function getFtsDetails() {
    vm.loader = true;
    var objRequest;

   // Define the request data object
    var requestData = {
             objRequest: {
            "Cust_ID": "123456",
            "Account_Number": "7890",
            "Barcode": "ABC123456",
            "Form_Status": "processed",
            "Status": "Production",
            "System_Type": "CRM",
            "IsData": "Yes"
        }
    };

    var RequestBody= JSON.parse(JSON.stringify(requestData));
    cumToNonCumChangeOfBondTypeService.getFtsDetails(RequestBody).then(function (response) {
        vm.loader = false;
        if (response.data && response.data.result) {
            vm.ftsDetails = response.data.result;
            }
    }, function (error) {
        handleErrorResponse(error);
        vm.loader = false;
    });
}

function validatePan() {
    vm.loader = true;
    vm.validationCompleted = false; // Reset validation status

    var requestData = {
        "Source_System": "YourSourceSystem",
        "inputData": [
            {
                "pan": vm.pan_No1,
                "name": vm.bondHolderName,
                "fatherName": "",
                "dob": $filter('date')(vm.fromDate1, "dd/MM/yyyy"),
            },
            {
                "pan": vm.pan_No2,
                "name": vm.secondJointHolderName,
                "fatherName": "",
                "dob": $filter('date')(vm.fromDate2, "dd/MM/yyyy"),
            },
            {
                "pan": vm.pan_No3,
                "name": vm.thirdJointHolderName,
                "fatherName": "",
                "dob": $filter('date')(vm.fromDate3, "dd/MM/yyyy"),
            }
        ]
    };

    cumToNonCumChangeOfBondTypeService.validatePan(requestData).then(function (response) {
        vm.loader = false;

        if (response.data && response.data.outputData) {
            vm.panDetails = response.data.outputData; // Assuming outputData contains an array of results

            // Clear previous validation messages
            vm.validationMessages = [];

            // Prepare messages based on pan_status
            var hasErrors = false;
            vm.panDetails.forEach(function(detail) {
                if (detail.pan_status === 'E') {
                    vm.validationMessages.push("Pan " + detail.pan + "  already exists.");
                    hasErrors = true;
                } else if (detail.pan_status === 'N') {
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

    }, function (error) {
        handleErrorResponse(error);
        vm.loader = false;
    });
}


function addDet() {
    vm.loader = true;  // Show loader while processing

    var requestData = {
        'schemeCode': vm.schemeCode,
        'modeOfHolding': vm.modeOfHolding,
        'hdfcOtherBank': vm.hdfcOtherBank,
        'schemeBondType': vm.schemeBondType,
        'pan_No1': vm.pan_No1,
        'pan_No2': vm.pan_No2,
        'pan_No3': vm.pan_No3,
        'id': vm.id,
        'bondHolderName': vm.bondHolderName,
        'secondJointHolderName': vm.secondJointHolderName,
        'thirdJointHolderName': vm.thirdJointHolderName,
        'fromDate1' : $filter('date')(vm.fromDate1, "dd/MM/yyyy"),
        'fromDate2' : $filter('date')(vm.fromDate2, "dd/MM/yyyy"),
        'fromDate3' : $filter('date')(vm.fromDate3, "dd/MM/yyyy"),

    };

    cumToNonCumChangeOfBondTypeService.addDet(requestData).then(function(response) {
        vm.loader = false;


        if (response && response.data && response.data.result) {
            var result = response.data.result;


            if (result.dataCopersondetailsList1 && result.dataCopersondetailsList1.length > 0) {
                var firstDetails = result.dataCopersondetailsList1[0];
                vm.DetailsList = firstDetails;
                vm.accountOpeningDate = firstDetails.accountOpeningDate;
                vm.modeOfInterestPayment = firstDetails.modeOfInterestPayment;
                vm.operativeAccNum = firstDetails.operativeAccNum;
                vm.bondType = firstDetails.bondType;
                vm.subscriptionAmount = firstDetails.subscriptionAmount;
            }
           if (result.instrumentDetailsOut && result.instrumentDetailsOut.length > 0) {
                var firstDetails = result.instrumentDetailsOut[0];
                vm.DetailsList = firstDetails;
//                vm.accountOpeningDate = firstDetails.accountOpeningDate;
                vm.accountOpeningDate = convertToDateObject(vm.cbsDetail.dateOfBirth);
                vm.modeOfInterestPayment = firstDetails.modeOfInterestPayment;
                vm.operativeAccNum = firstDetails.operativeAccNum;
                vm.bondType = firstDetails.bondType;
                vm.subscriptionAmount = firstDetails.subscriptionAmount;
            }
            vm.blaFlag = result.blaFlag;
            vm.periodOfDeposit = result.periodOfDeposit;
            vm.rateOfInterest = result.rateOfInterest;
            vm.schemeBondType = result.schemeBondType;
            vm.BlaFlagNo = result.instrumentDetailsOut;
            vm.cashinDet = result.customerMasterOut;
            vm.bondCashLimit = result.cashLimitForCash;
            vm.bondClearingTransferLimit = result.cashLimitForClearingTransfer;
            vm.depositMultiples = result.depositMultiples;
            vm.minCashLimit = result.minCashLimit;
            vm.blaNoOut = result.blaNoOut;
            if(vm.blaNoOut){
            if(vm.modeOfHolding == 'MH001' || vm.modeOfHolding == 'MH004' || vm.modeOfHolding == 'MH005'){
            if(vm.blaNoOut[0].blaNo){
            vm.singleBondAccNo = vm.blaNoOut[0].blaNo;
             }
            if(vm.blaNoOut[0].nameOfTheBondHolder){
            vm.bondHolderName = vm.blaNoOut[0].nameOfTheBondHolder;
              }

              }

            else{
            if(vm.blaNoOut[0].blaNo){
            vm.firstJointHolderBlaNo = vm.blaNoOut[0].blaNo;

            if(vm.blaNoOut[0].nameOfTheBondHolder){
            vm.firstJointHolderName = vm.blaNoOut[0].nameOfTheBondHolder;
            }
            }

            if(vm.blaNoOut[1].blaNo){
            vm.secondJointHolderBlaNo = vm.blaNoOut[1].blaNo;

           if(vm.blaNoOut[1].nameOfTheBondHolder){
           vm.secondJointHolderName = vm.blaNoOut[1].nameOfTheBondHolder;
            }
             }

            if(vm.blaNoOut[2].blaNo){
            vm.thirdJointHolderBlaNo = vm.blaNoOut[2].blaNo;
            if(vm.blaNoOut[2].nameOfTheBondHolder){
            vm.thirdJointHolderName = vm.blaNoOut[2].nameOfTheBondHolder;
            }
            }
            }
            }

         if (vm.bondHolderName) {
                vm.customerMasterDetails.bondHldFirstName = vm.bondHolderName;
                vm.customerMasterDetails.bondHldDob = vm.fromDate1;
                vm.customerMasterDetails.panNo = vm.pan_No1;
            }
            if (vm.secondJointHolderName) {
                vm.customerMasterDetailsTwo.bondHldFirstName = vm.secondJointHolderName;
                vm.customerMasterDetailsTwo.bondHldDob = vm.fromDate2;
                vm.customerMasterDetailsTwo.panNo = vm.pan_No2;
            }
            if (vm.thirdJointHolderName) {
                vm.customerMasterDetailsThree.bondHldFirstName = vm.thirdJointHolderName;
                vm.customerMasterDetailsThree.bondHldDob = vm.fromDate3;
                vm.customerMasterDetailsThree.panNo = vm.pan_No3;
            }

//            if (vm.blaFlag === 'Y') {
//                Swal.fire({
//                    title: "Warning!",
//                    text: "Bond Ledger Account Number Already Exists",
//                    type: "warning",
//                    confirmButtonText: "OK"
//                }, function() {
//
//                    $timeout(function() {
//                        var $tabLink = $('#subscriptionForm');
//                        if ($tabLink.length) {
//                            $tabLink.tab('show');
//                        } else {
//                            console.warn("Element with ID 'subscriptionForm' not found.");
//                        }
//                    }, 100);
//                });
//            }

if (vm.blaFlag === 'Y') {
    Swal.fire({
        title: "Warning!",
        text: "Bond Ledger Account Number Already Exists",
        icon: "warning",
        confirmButtonText: "OK"
    }).then((result) => {
        if (result.isConfirmed) {
            $timeout(function () {
                var $tabLink = $('#subscriptionForm');
                if ($tabLink.length) {
                    $tabLink.tab('show');
                } else {
                    console.warn("Element with ID 'subscriptionForm' not found.");
                }
            }, 100);
        }
    });
}


            if (Array.isArray(vm.BlaFlagNo)) {
                vm.titleText = 'BOND HOLDER DETAILS';

                vm.customerMasterDetails = vm.BlaFlagNo[0] || {};
                vm.customerMasterDetailsTwo = vm.BlaFlagNo[1] || {};
                vm.customerMasterDetailsThree = vm.BlaFlagNo[2] || {};

                if (vm.customerMasterDetails.bondHldDob) {
                    vm.customerMasterDetails.bondHldDob = $filter('date')(vm.customerMasterDetails.bondHldDob, "dd-MM-yyyy");
                }
                if (vm.customerMasterDetailsTwo.bondHldDob) {
                    vm.customerMasterDetailsTwo.bondHldDob = $filter('date')(vm.customerMasterDetailsTwo.bondHldDob, "dd-MM-yyyy");
                }
                if (vm.customerMasterDetailsThree.bondHldDob) {
                    vm.customerMasterDetailsThree.bondHldDob = $filter('date')(vm.customerMasterDetailsThree.bondHldDob, "dd-MMM-yyyy");
                }
            }

            if (Array.isArray(vm.cashinDet)) {
                if (vm.cashinDet.length === 1) {
                    vm.titleText = 'BOND HOLDER DETAILS';
                    vm.customerMasterDetails = vm.cashinDet[0];
                } else {
                    vm.titleText = 'BOND HOLDER DETAILS';

                    vm.customerMasterDetails = vm.cashinDet[0] || {};
                    vm.customerMasterDetailsTwo = vm.cashinDet[1] || {};
                    vm.customerMasterDetailsThree = vm.cashinDet[2] || {};

                    if (vm.customerMasterDetails.bondHldDob) {
                        vm.customerMasterDetails.bondHldDob = $filter('date')(vm.customerMasterDetails.bondHldDob, "dd-MM-yyyy");
                    }
                    if (vm.customerMasterDetailsTwo.bondHldDob) {
                        vm.customerMasterDetailsTwo.bondHldDob = $filter('date')(vm.customerMasterDetailsTwo.bondHldDob, "dd-MM-yyyy");
                    }
                    if (vm.customerMasterDetailsThree.bondHldDob) {
                        vm.customerMasterDetailsThree.bondHldDob = $filter('date')(vm.customerMasterDetailsThree.bondHldDob, "dd-MMM-yyyy");
                    }
                }
            } else {
                console.warn("Expected 'customerMasterOut' to be an array, but got:", vm.cashinDet);
            }
        } else {
            console.error("Response structure is not as expected:", response);
        }
    }).catch(function(error) {
        handleErrorResponse(error);
         $state.go('cum_to_non_cum_change_of_bond_type.list');
        vm.loader = false;
    });
}

   function getMaturityDate() {
        if (vm.periodOfDeposit !== null && vm.accountOpeningDate) {
            var accountOpeningDate = new Date(vm.accountOpeningDate);

            var numberOfMonths = vm.periodOfDeposit;

            var maturityDate = new Date(vm.accountOpeningDate);

//            maturityDate.setMonth(maturityDate.getMonth() + numberOfMonths);
             maturityDate.setMonth(parseInt(maturityDate.getMonth()) + parseInt(numberOfMonths));
            vm.maturityDate = $filter('date')(maturityDate, "dd/MM/yyyy");


        }
    };



        function getModuleNameAdd() {
                    vm.loader = true;
                    vm.referenceId = 'ACTSCH';
                    var requestData = {
                        'referenceId': vm.referenceId
                    };
                    cumToNonCumChangeOfBondTypeService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                        vm.loader = false;
                        vm.schemeList = response.data.result;
                    }, function (error) {
                        handleErrorResponse(error);
                        $state.go('cum_to_non_cum_change_of_bond_type.list');
                        vm.loader = false;
                    });
                }


                                                    function getPantorch(){

                                                     var requestData = {
                                                         'schemeCode': vm.schemeCode,
                                                         'bondHolderName': vm.bondHolderName,
                                                         'panNum': vm.panNum

                                                     };
                                                      cumToNonCumChangeOfBondTypeService.getPantorch(requestData).then(function(response){
                                                       vm.loader = false;
//                                                       vm.PanTorch = response.data.result;
                                                       vm.PanTorch = response.data.result.torchValue;
                                                       angular.element('#panModel').modal('toggle');
                                                     },function(error) {
                                                         vm.loader = false;
                                                         handleErrorResponse(error);
                                                       });
                                                 }


   function getPantorchInq(){

                                                     var requestData = {
                                                         'schemeCode': vm.schemeCode,
                                                         'bondHolderName': vm.bondHolderName,
                                                         'panNum': vm.panNum

                                                     };
                                                      cumToNonCumChangeOfBondTypeService.getPantorchInq(requestData).then(function(response){
                                                       vm.loader = false;
//                                                       vm.PanTorch = response.data.result;
                                                       vm.PanTorch = response.data.result.torchValue;
                                                       angular.element('#panModel').modal('toggle');
                                                     },function(error) {
                                                         vm.loader = false;
                                                         handleErrorResponse(error);
                                                       });
                                                 }
   function getPantorchVer(){

                                                     var requestData = {
                                                         'schemeCode': vm.schemeCode,
                                                         'bondHolderName': vm.bondHolderName,
                                                         'panNum': vm.panNum

                                                     };
                                                      cumToNonCumChangeOfBondTypeService.getPantorchVer(requestData).then(function(response){
                                                       vm.loader = false;
//                                                       vm.PanTorch = response.data.result;
                                                       vm.PanTorch = response.data.result.torchValue;
                                                       angular.element('#panModel').modal('toggle');
                                                     },function(error) {
                                                         vm.loader = false;
                                                         handleErrorResponse(error);
                                                       });
                                                 }



        function getCurrentFinancialYear() {
          vm.fiscalyear = "";
          var today = new Date();
          if ((today.getMonth() + 1) <= 3) {
            vm.fiscalyear = (today.getFullYear() - 1) + "-" + today.getFullYear()
          } else {
            vm.fiscalyear = today.getFullYear() + "-" + (today.getFullYear() + 1)
          }
          vm.currentFinancialYear = vm.fiscalyear;
          vm.customerMasterDetails.finYear = vm.fiscalyear;
//          vm.customerMasterDetailsTwo.finYear = vm.fiscalyear;
//          vm.customerMasterDetailsThree.finYear = vm.fiscalyear;
          return vm.fiscalyear

        }
       function getModeofHolding() {
           var requestData = {
                 'referenceId': 'MODE_OF_HOLDING'
             }
           cumToNonCumChangeOfBondTypeService.getReferenceData(requestData).then(function(response){
              vm.modeOfHoldingList = response.data.result;
            },function(error) {
               handleErrorResponse(error);
             });
        }
       function getTypeofHolding() {
           var requestData = {
                 'referenceId': 'HOLDER_TYPE'
             }
           cumToNonCumChangeOfBondTypeService.getReferenceData(requestData).then(function(response){
              vm.holderTypeList = response.data.result;
            },function(error) {
               handleErrorResponse(error);
             });
        }
       function getFormDetails() {
           var requestData = {
                 'referenceId': 'FORM'
             }
           cumToNonCumChangeOfBondTypeService.getReferenceData(requestData).then(function(response){
              vm.formList = response.data.result;
            },function(error) {
               handleErrorResponse(error);
             });
        }
       function getKycDetails() {
           var requestData = {
                 'referenceId': 'KYC_DOCS'
             }
           cumToNonCumChangeOfBondTypeService.getReferenceData(requestData).then(function(response){
              vm.kycList = response.data.result;
            },function(error) {
               handleErrorResponse(error);
             });
        }
           function getGuardianDetailsMetaData() {
               var requestData = {
                     'referenceId': 'RELATION_WITH_MINOR'
                 }
               cumToNonCumChangeOfBondTypeService.getReferenceData(requestData).then(function(response){
                  vm.guardianDetailsListMetaData = response.data.result;
                },function(error) {
                   handleErrorResponse(error);
                 });
            }
        /* Check box selection */
        function selectedChecklist() {
            angular.forEach(vm.serviceCodeDetailsList, function(item) {
                if (item.selected) {
                    vm.selectedCheckListArray.push(item.serCode);
                }
            });
            if(vm.selectedCheckListArray.length == '0'){
                Swal.fire('Please select atleast one request type')
            }else{
                getMonitorServiceReport();
            }
        }

        vm.displayRemarks = true;
        vm.reqField = true;

        function optionToggled(){

         vm.isAllSelected = [];
            angular.forEach(vm.kycList, function(item)
            {
                vm.isAllSelected.push(item.selected);
            });
            console.log(vm.isAllSelected);
            if(vm.isAllSelected[0] == true){
                vm.customerMasterDetails.birthCertificate = 'Yes'
            }
            if(vm.isAllSelected[1] == true){
                vm.customerMasterDetails.passport = 'Yes'
            }
            if(vm.isAllSelected[2] == true){
                vm.customerMasterDetails.seniorCitizenCard = 'Yes'
            }
             if(vm.isAllSelected[3] == true){
                vm.customerMasterDetails.voterIdCard = 'Yes'
            }
            if(vm.isAllSelected[4] == true){
                vm.customerMasterDetails.panCard = 'Yes'
            }
            if(vm.isAllSelected[5] == true){
                vm.customerMasterDetails.drivingLicence = 'Yes'
            }
            if(vm.isAllSelected[6] == true){
                vm.customerMasterDetails.rationCard = 'Yes'
            }
            if(vm.isAllSelected[7] == true){
                vm.customerMasterDetails.otherDocuments = 'Yes'
            }


//            if(vm.isAllSelected.indexOf(true) != -1){
//                vm.reqField = false;
//            }else{
//                vm.reqField = true;
//            }

        }

        function getState() {

                 vm.listType = 'STATES OF INDIA';
                 var requestData = {
                 referenceId:vm.listType
                 }
           cumToNonCumChangeOfBondTypeService.getReferenceData(requestData).then(function(response){
              vm.stateListValues = response.data.result;
                vm.stateList = [];
                angular.forEach(vm.stateListValues, function(item){
                     vm.stateList.push({subCodeDescription: item.DESCRIPTION, subCode: item.SUBCODE})
                })
            },function(error) {
               handleErrorResponse(error);
             });
        }

        /* Get Sol Details */
        function getSolDetails(solId, scopeVariable, flag){
            vm.tempSolIDScopeVariable = scopeVariable;
            vm.searchSolId = '';
            vm.loader = true;
            commonService.getSolAllDetails(solId, function(response){
                if(response.success) {
                    vm.loader = false;
                    vm.solDetails = response.data;
                    if(flag == 'normal'){
                    angular.element('#solIDModal').modal('toggle');
                    }else{
                    angular.element('#solIDJointModal').modal('toggle');
                    }
                } else {
                    Swal.fire("Error!", response.message, "error");
                    vm.loader = false;
                }
            });
        }

        /* Get CBS Customer Id Details */
        function getCbsCustFstJoinDetails(){
            vm.loader = true;
              var requestData = {

                'operativeAccNo' : vm.customerMasterDetails.operativeAccNum

               };

          cumToNonCumChangeOfBondTypeService.fetchCbsCustId(requestData).then( (response) => {
                    vm.loader = false;
                    vm.cbsDetail = response.data.result;

                    vm.customerMasterDetails.bondHldFirstName = vm.cbsDetail.firstName;
                    vm.customerMasterDetails.bondHldMidName = vm.cbsDetail.middleName;
                    vm.customerMasterDetails.bondHldLastName = vm.cbsDetail.lastName;
                    vm.customerMasterDetails.fatherName = vm.cbsDetail.fatherName;
                    vm.customerMasterDetails.motherName = vm.cbsDetail.motherName;
                    vm.customerMasterDetails.gender = vm.cbsDetail.gender;
                    vm.customerMasterDetails.bondHldDob = convertToDateObject(vm.cbsDetail.dateOfBirth);
                    vm.customerMasterDetails.isMinor = vm.cbsDetail.isMinor;
                    vm.customerMasterDetails.bondHldAddOne = vm.cbsDetail.addressOne;
                    vm.customerMasterDetails.bondHldAddTwo = vm.cbsDetail.addressTwo;
                    vm.customerMasterDetails.bondHldAddThree = vm.cbsDetail.addressThree;
                    vm.customerMasterDetails.bondHldCity = vm.cbsDetail.city;
                    vm.customerMasterDetails.bondHldPinCode = vm.cbsDetail.pinCode;
                    vm.customerMasterDetails.bondHolderPhoneNumber = vm.cbsDetail.phoneNumber;
                    vm.customerMasterDetails.bondHldMobNum = vm.cbsDetail.mobileNumber;
                    vm.customerMasterDetails.bondHldEmail = vm.cbsDetail.email;
                    vm.customerMasterDetails.operativeAccNum = vm.cbsDetail.operativeAccNo;
                    vm.customerMasterDetails.operativeAccBranchId = vm.cbsDetail.operativeBranchId;
                    vm.customerMasterDetails.panNo = vm.cbsDetail.pan;
                    vm.customerMasterDetails.aadhaarNo = vm.cbsDetail.aadhar;

                }, function(error) {
                   vm.loader = false;
                   handleErrorResponse(error);
                   vm.errmsgSingle = 'CBS Failure';
                   vm.customerMasterDetails.cbsCustId = '';
                });

        }


    function minAgeC(){
              var currentDate=new Date();
              var result = subtractYears(currentDate, 18);


    }



        /* Get CBS Customer Id Second Join Details */
        function getCbsCustScndJoinDetails(){
            vm.loader = true;
            vm.custId = vm.customerMasterJointDetails.cbsCustId;
            cumToNonCumChangeOfBondTypeService.fetchCbsCustId(vm.custId).then(function(response){
                if(response) {
                    vm.getCbsScndJoinDetailsChck = response.data.status;
                    vm.getCbsScndJoinDetails = response.data.customerDetails;
                    if(vm.getCbsScndJoinDetailsChck != 'N' && vm.getCbsScndJoinDetails != null){
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
                        vm.errmsgJoint ='';

                        getTdsApplicableRate();
                    }else{
                        vm.errmsgJoint = 'No Data Found';
                    }

                    vm.loader = false;
                } else {
                    Swal.fire("Error!", response.message, "error");
                    vm.loader = false;
                }
            });
        }
        /* Get bla acc Details */
        function getBlaAccNoDetails(flag){
        vm.loader = true;

        if(flag=='torch'){


                    var requestData = {
                        'schemeCode': vm.schemeCode,
                        'modeOfHolding': vm.modeOfHolding,
                    };
                      cumToNonCumChangeOfBondTypeService.fetchBlaAccNoDetails(requestData).then( (response) => {
                                vm.loader = false;
                                vm.blaNoList = response.data.result;
                                if(vm.modeOfHolding == 'MH002' || vm.modeOfHolding == 'MH003'){
                                        angular.element('#blaJointModal').modal('toggle');
                                }else{
                                       angular.element('#blaModal').modal('toggle');
                                }
                            }, function(error) {
                               vm.loader = false;
                               handleErrorResponse(error);
                            });
        }
        else{
                  var requestData = {
                        'schemeCode': vm.schemeCode,
                        'modeOfHolding': vm.modeOfHolding,
                        'blaNum' : vm.customerMaster.blaAccNo,
                        'jointHolderNo': vm.jointHolderTypeCode,
                        };
                      cumToNonCumChangeOfBondTypeService.fetchBlaAccNoDetails(requestData).then( (response) => {
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

                            }, function(error) {
                               vm.loader = false;
                               handleErrorResponse(error);
                            });
        }


        }



        function getGuardianDetails(flag){
                vm.loader = true;

                if(flag == 'select'){
                    var requestData = {
                        'schemeCode' : vm.schemeCode,
                    };
                  cumToNonCumChangeOfBondTypeService.fetchGuardianDetails(requestData).then( (response) => {
                            vm.loader = false;
                            vm.guardianDetailsList = response.data.result;
                            angular.element('#guardianModal').modal('toggle');
                        }, function(error) {
                           vm.loader = false;
                           handleErrorResponse(error);
                        });
                }else{
                    var requestData = {
                        'schemeCode' : vm.schemeCode,
                        'guardianId' : vm.customerMasterDetails.guardianId,
                    };
                  cumToNonCumChangeOfBondTypeService.fetchGuardianDetails(requestData).then( (response) => {
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
                        }, function(error) {
                           vm.loader = false;
                           handleErrorResponse(error);
                        });
                }




                }

function convertDateFormat(dateString) {
    var monthMap = {
        "JAN": 1, "FEB": 2, "MAR": 3, "APR": 4, "MAY": 5, "JUN": 6,
        "JUL": 7, "AUG": 8, "SEP": 9, "OCT": 10, "NOV": 11, "DEC": 12
    };

    if (!dateString || typeof dateString !== 'string') {
        throw new Error('Invalid date string');
    }

    var parts = dateString.split("-");
    if (parts.length !== 3) {
        throw new Error('Invalid date format. Expected "dd-MMM-yyyy".');
    }

    var day = parseInt(parts[0], 10);
    var month = monthMap[parts[1].toUpperCase()];
    var year = parseInt(parts[2], 10);

    if (!month) {
        throw new Error('Invalid month abbreviation');
    }

    return new Date(year, month - 1, day); // Convert to Date object
}





// Function to clear guardian details
vm.clearGuardianDetails = function() {
    vm.customerMasterDetails.guardianId = '';
    vm.customerMasterDetails.guardianFirstName = '';
    vm.customerMasterDetails.guardianMiddleName = '';
    vm.customerMasterDetails.guardianLastName = '';
    vm.customerMasterDetails.guardianDateOfBirth = '';
    vm.customerMasterDetails.guardianCommunicationAddress = '';
    vm.customerMasterDetails.guardianMobileNo = '';
    vm.customerMasterDetails.guardianEmailId = '';
};

        /* Get SCSS Joint Customer Id Details */
        function getScssJointCustDetails(){
            vm.loader = true;
                vm.scsscustId = vm.customerMasterJointDetails.scssCustId;
                vm.holderNum = vm.holderNum='2';
            cumToNonCumChangeOfBondTypeService.fetchScssCustId(vm.scsscustId, vm.holderNum).then(function(response){
                if(response) {
                    if(vm.scsscustId !='' && vm.holderNum ==2){
                        vm.getScssJointDetailsChck = response.data.result;
                        vm.getScssJointDetails = response.data.result[0];
                        if(vm.getScssJointDetailsChck.length > 0){
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
                            vm.errorMessageJoint ='';

                            getTdsApplicableRate();
                        }else{
                             vm.errorMessageJoint = 'No Data Found';
                        }
                   }


                    vm.loader = false;
                } else {
                    Swal.fire("Error!", response.message, "error");
                    vm.loader = false;
                }
            });
        }

        function getblaNo(){
         vm.loader = true;

                                            var requestData = {
                                                'schemeCode': vm.schemeCode,
                                                'schemeName': vm.schemeName,
                                                'panNum': vm.panNum,
                                                'bondHolderName': vm.bondHolderName,
                                                'blaNo': vm.blaNo,
                                            };
                                             cumToNonCumChangeOfBondTypeService.blaNo(requestData).then(function(response){
                                              vm.loader = false;
                                              vm.blaDataList = response.data.result.torchValue;
                                              angular.element('#blaModel').modal('toggle');
                                            },function(error) {
                                                vm.loader = false;
                                                handleErrorResponse(error);
                                              });

        }

        function getblaNoInq(){
         vm.loader = true;

                                            var requestData = {
                                                'schemeCode': vm.schemeCode,
                                                'schemeName': vm.schemeName,
                                                'panNum': vm.panNum,
                                                'bondHolderName': vm.bondHolderName,
                                                'blaNo': vm.blaNo,
                                            };
                                             cumToNonCumChangeOfBondTypeService.getblaNoInq(requestData).then(function(response){
                                              vm.loader = false;
                                              vm.blaDataList = response.data.result.torchValue;
                                              angular.element('#blaModel').modal('toggle');
                                            },function(error) {
                                                vm.loader = false;
                                                handleErrorResponse(error);
                                              });

        }
  function getblaNoVer(){
         vm.loader = true;

                                            var requestData = {
                                                'schemeCode': vm.schemeCode,
                                                'schemeName': vm.schemeName,
                                                'panNum': vm.panNum,
                                                'bondHolderName': vm.bondHolderName,
                                                'blaNo': vm.blaNo,
                                            };
                                             cumToNonCumChangeOfBondTypeService.getblaNoVer(requestData).then(function(response){
                                              vm.loader = false;
                                              vm.blaDataList = response.data.result.torchValue;
                                              angular.element('#blaModel').modal('toggle');
                                            },function(error) {
                                                vm.loader = false;
                                                handleErrorResponse(error);
                                              });

        }

        function getsubscriptionId(){
                                                     vm.loader = true;

                                                                     var requestData = {
                                                                         'schemeCode': vm.schemeCode,
                                                                         'schemeName': vm.schemeName,
                                                                         'panNum': vm.panNum,
                                                                         'bondHolderName': vm.bondHolderName,
                                                                         'blaNo': vm.blaNo,
                                                                         'subscriptionId': vm.subscriptionId,
                                                                     };
                                                                      cumToNonCumChangeOfBondTypeService.getsubscriptionId(requestData).then(function(response){
                                                                       vm.loader = false;
                                                                        vm.subscriptionList = response.data.result.torchValue;

                                                                            angular.element('#subscriptionModel').modal('toggle');
                                                                     },function(error) {
                                                                         vm.loader = false;
                                                                         handleErrorResponse(error);
                                                                       });

                                 }
        function getsubscriptionIdMod(){
                  vm.loader = true;

                                                     var requestData = {
                                                         'schemeCode': vm.schemeCode,
                                                         'schemeName': vm.schemeName,
                                                         'panNum': vm.panNum,
                                                         'bondHolderName': vm.bondHolderName,
                                                         'blaNo': vm.blaNo,
                                                          'subscriptionId': vm.subscriptionId,
                                                     };
                                                      cumToNonCumChangeOfBondTypeService.getsubscriptionIdMod(requestData).then(function(response){
                                                       vm.loader = false;
                                                                vm.subscriptionList = response.data.result.torchValue;
                                                        angular.element('#subscriptionModel').modal('toggle');
                                                     },function(error) {
                                                         vm.loader = false;
                                                         handleErrorResponse(error);
                                                       });

                 }

                                          function getsubscriptionIdInq(){
                                             vm.loader = true;

                                                             var requestData = {
                                                                 'schemeCode': vm.schemeCode,
                                                                 'schemeName': vm.schemeName,
                                                                 'panNum': vm.panNum,
                                                                 'bondHolderName': vm.bondHolderName,
                                                                 'blaNo': vm.blaNo,
                                                                 'subscriptionId': vm.subscriptionId,
                                                             };
                                                              cumToNonCumChangeOfBondTypeService.getsubscriptionIdInq(requestData).then(function(response){
                                                               vm.loader = false;
                                                                vm.subscriptionList = response.data.result.torchValue;

                                                                    angular.element('#subscriptionModel').modal('toggle');
                                                             },function(error) {
                                                                 vm.loader = false;
                                                                 handleErrorResponse(error);
                                                               });

                         }
                          function getsubscriptionIdVer(){
                                                                      vm.loader = true;

                                                                                      var requestData = {
                                                                                          'schemeCode': vm.schemeCode,
                                                                                          'schemeName': vm.schemeName,
                                                                                          'panNum': vm.panNum,
                                                                                          'bondHolderName': vm.bondHolderName,
                                                                                          'blaNo': vm.blaNo,
                                                                                          'subscriptionId': vm.subscriptionId,
                                                                                      };
                                                                                       cumToNonCumChangeOfBondTypeService.getsubscriptionIdVer(requestData).then(function(response){
                                                                                        vm.loader = false;
                                                                                         vm.subscriptionList = response.data.result.torchValue;

                                                                                             angular.element('#subscriptionModel').modal('toggle');
                                                                                      },function(error) {
                                                                                          vm.loader = false;
                                                                                          handleErrorResponse(error);
                                                                                        });

                                                  }
	/*Operative Acct Num*/
           function getSchemeTypeListOperative (acctNum, flag){
               vm.loader = true;
               vm.customerMasterDetails.operativeAccBranchId='';
               if(acctNum){

               commonService.getCustomAcctInqApi(acctNum.toUpperCase(), function (response) {
       					if (response.success) {
       						vm.accList = response.data;
       						vm.loader = false;
       						if (vm.accList.length > 0) {
       						if (vm.accList[0].status == 'S') {


                                   if(vm.accList[0].accountFreezeStatus) {
                                     vm.cbsErrorMessage = 'Account is Friezed';
                                     vm.customerMasterDetails.operativeAccNum='';
                                   }else{
                                       vm.customerMasterDetails.operativeAccBranchId = vm.accList[0].solID;
                                       vm.cbsErrorMessage = null;
                                   }
       							}
       							if (vm.accList[0].status == 'F') {
       								vm.cbsErrorMessage = 'Invalid  Account  Number';
       							}
       						}
       						else {
                                   vm.cbsErrorMessage = 'Invalid  Account  Number';
       						}
       					} else {
       						Swal.fire("Error!", response.message, "error");
       						vm.loader = false;
       						vm.customerMasterDetails.operativeAccNum='';
       					}
       				}, function (err) {
       					handleErrorResponse(err);
       					vm.loader = false;
       					vm.customerMasterDetails.operativeAccNum='';
       				});



        }else{
             vm.loader = false;
         }
    }
    /*Operative Acct Num Joint*/
        function getSchemeTypeListOperativeJoint (solId, acctNum, flag){
            vm.loader = true;
            if(acctNum){
            commonService.getSchemeTypeListAcc(acctNum.toUpperCase(), function(response){
                if(response.success) {
                    vm.accList = response.data;
                    vm.loader = false;
                    if(vm.accList.length>0)
                    {
                    vm.customerMasterJointDetails.acctNum = vm.accList[0].forAccountId;
                    vm.customerMasterJointDetails.C025 = vm.accList[0].forAccountId;
                    vm.customerMasterJointDetails.acctName= vm.accList[0].accountName;
                    vm.errorMessageJointAcnt ='';
                    }else
                    {
                    vm.errorMessageJointAcnt = 'Account Number failed to validate';
                    vm.customerMasterJointDetails.acctNum='';
                    }
                } else {
                    Swal.fire("Error!", response.message, "error");
                    vm.loader = false;
                            }
            }, function(err) {
                     handleErrorResponse(err);
                 vm.loader = false;
             });
            }else{
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
            function titleChange(){
                if(vm.modeOfHolding == 'MH002' || vm.modeOfHolding == 'MH003'){
                    vm.titleText="BOND HOLDER DETAILS";

                }
                else {
                    vm.titleText="BOND HOLDER DETAILS";
                }
            }

            const convertToColumnValuesArrayTemp = (objectToBeConvert) => {
              if(typeof(objectToBeConvert) === "object") {
                 let resultArray = [];
                 for(let key of Object.keys(objectToBeConvert)) {
                    if(objectToBeConvert[key])
                        resultArray.push({colName : 'C003',value : objectToBeConvert[key].value})
                         if(objectToBeConvert[key].value == 'KD009'){
                          resultArray.push({colName : 'C004',value : vm.documentsDetailsList.C004});//Remarks
                         }
                 }
                  if(vm.modeOfHolding=='MH001')
                  { //Holder Number
                  resultArray.push({colName : 'C002',value : '1'});
                  }else{
                  resultArray.push({colName : 'C002',value : '2'});
                  }
                 return resultArray;
              } else {
                throw "Only objects can be converted"
              }
            }
             const convertToColumnValuesArrayTempModify = (objectToBeConvert) => {
              if(typeof(objectToBeConvert) === "object") {
                 let resultArray = [];
                 for(let key of Object.keys(objectToBeConvert)) {
                    if(objectToBeConvert[key])
                        resultArray.push({colName : 'C003',value : objectToBeConvert[key].value})
                        resultArray.push({colName : 'ID',value : objectToBeConvert[key].ID? objectToBeConvert[key].ID : ''})
                    if(objectToBeConvert[key].value == 'KD009'){
                    resultArray.push({colName : 'C004',value : vm.documentsDetailsList.C004});//Remarks
                    }
                 }

                  if(vm.getScssDetails.masterDetailsValueModel[0].C002 =='MH001')
                  { //Holder Number
                  resultArray.push({colName : 'C002',value : '1'});
                  }else{
                  resultArray.push({colName : 'C002',value : '2'});
                  }
                 return resultArray;
              } else {
                throw "Only objects can be converted"
              }
            }

        function addcustomerMaster() {
//            Swal.fire({
//                    title: "Are you sure?",
//                    text: "Do you really want to add this record?",
//                    type: "warning",
//                    showCancelButton: true,
//                    cancelButtonClass: "btn-cancel",
//                    confirmButtonClass: "btn-primary",
//                    confirmButtonText: "Yes",
//                    closeOnConfirm: false,
//                },
//                function() {

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
                        panNum: vm.panNum,
                        blaNo: vm.blaNo,
                        subscriptionId: vm.subscriptionId,
                    };
                    cumToNonCumChangeOfBondTypeService.addcustomerMaster(requestData).then(function(response) {
                        vm.loader = false;
                        if (response.data.message == "SUCCESS") {
                            Swal.fire("Added!", "Added Successfully", "success");
                            $state.go('cum_to_non_cum_change_of_bond_type.list');
                        } else {
                            Swal.fire("Warning!", response.data.message, "warning");
                            $state.go('cum_to_non_cum_change_of_bond_type.add');
                        }
                    }, function(error) {
                        vm.loader = false;
 const errorMessage = error && error.data && error.data.message
                             ? error.data.message
                             : "An unexpected error occurred.";
                     if(null!=errorMessage && errorMessage.startsWith("Invalid")){
                     Swal.fire("Warning!", errorMessage , "warning");
                     $state.go("cum_to_non_cum_change_of_bond_type.list");
                     }else{
                     handleErrorResponse(error);
                     $state.go("cum_to_non_cum_change_of_bond_type.list");
                     }
                    });
                    }
                });
        }


            /* Add Submit */
          function handleHdfcSelection() {
                // Clear the operative account number when selection changes
                if (vm.customerMasterDetails.hdfcOtherBank === 'N') {
                        vm.customerMasterDetails.operativeAccNum = '';
                    }
                    if (vm.customerMasterDetailsTwo.hdfcOtherBank === 'N') {
                        vm.customerMasterDetailsTwo.operativeAccNum = '';
                    }
                    if (vm.customerMasterDetailsThree.hdfcOtherBank === 'N') {
                        vm.customerMasterDetailsThree.operativeAccNum = '';
                    }
            };

            function orderArray(list){
               //this function will come joint holder greater than or equal to 2
               vm.tempArray = [];
               vm.tempArray.push('0');
               vm.tempArray.push('1');
               if(list.length > 2){
                   vm.tempArray.push('2');
               }
                angular.forEach(list,function(item){

                    if(item.jointHolderType == '1'){
                        vm.tempArray.splice(0,'0', item);
                    }
                    if(item.jointHolderType == '2'){
                        vm.tempArray.splice(1,'1', item);
                    }
                    if(item.jointHolderType == '3'){
                        vm.tempArray.splice(2,'2', item);
                    }

                });

                var value = vm.tempArray.filter((value) => {
                    if(value == '0'){

                    }else
                    if(value == '1'){

                    }else
                     if(value == '2'){

                    }else{
                        return value;
                    }
                });

                return value;

            }
function convertYNToBoolean(value) {
       return value === 'Y' || value === true;
}


function convertBooleanToYN(value) {
    return value ? 'Y' : 'N';;
}

            //Verify Select
                   function getVerifySelectDetails(flag){
                                   vm.loader = true;

                                   if(vm.schemeCode == null || vm.schemeCode == undefined){
                                       var requestData = {

                                       };
                                        cumToNonCumChangeOfBondTypeService.verifySelect(requestData).then(function(response){
                                         vm.loader = false;
                                         vm.getVerifyDetails = response.data.result;

                                       },function(error) {
                                           vm.loader = false;
                                           handleErrorResponse(error);
                                         });
                                   }



                                   else{

                                       var requestData = {
                                           'schemeCode': vm.schemeCode,
                                           'blaNo': vm.blaNo,

                                           'subscriptionId': vm.subscriptionId,
                                           'modeOfHolding': vm.modeOfHolding

                                       };
                                        cumToNonCumChangeOfBondTypeService.verifySelect(requestData).then(function(response){
                                                                                 vm.getDetails = response.data.result;
                                        //                                         vm.nomineeDetailsValueModel = response.data.result.nomineeDetailsValueModel;

                                                                                                       vm.loader = false;
                                         if(vm.getDetails && flag == "select"){
                                        $state.go('cum_to_non_cum_change_of_bond_type.verify_detail',({obj:{
                                                  								schemeName: vm.schemeName,
                                                  								schemeCode: vm.schemeCode,
                                                  								blaNo: vm.blaNo,
                                                  								bondSubsId : vm.subscriptionId,
                                                  								modeOfHolding : vm.modeOfHolding,
                                                  								panNum : vm.panNum,
                                                  								moduleId : vm.moduleName,
                                                  								subscriptionId: vm.subscriptionId,
                                                  								bondHolderName: vm.bondHolderName,
                                                  								}}));


                                        }


                                                              if(vm.getDetails.masterDetailsValueModel.length == 1){
                                                                   vm.titleText = 'BOND HOLDER DETAILS';
                                                              }else{
                                                                    vm.titleText = 'BOND HOLDER DETAILS';

                                                                   vm.tempArray = [];
                                                                   vm.tempArray.push('0');
                                                                   vm.tempArray.push('1');
                                                                   if(vm.getDetails.masterDetailsValueModel.length > 2){
                                                                       vm.tempArray.push('2');
                                                                   }

                                                                   angular.forEach(vm.getDetails.masterDetailsValueModel,function(item){

                                                          if (item.C004) {
                                                                                                   if(item.C004.oldValue == '1'){
                                                                                                       vm.tempArray.splice(0,'0', item);
                                                                                                   }
                                                                                                   if(item.C004.oldValue == '2'){
                                                                                                       vm.tempArray.splice(1,'1', item);
                                                                                                   }
                                                                                                   if(item.C004.oldValue == '3'){
                                                                                                       vm.tempArray.splice(2,'2', item);

                                                                                                   }
                                                                                                   }
                                                                                               });

//                                                                   var value = vm.tempArray.filter((value) => {
//                                                                       if(value == '0'){
//
//                                                                       }else
//                                                                       if(value == '1'){
//
//                                                                       }else
//                                                                        if(value == '2'){
//
//                                                                       }else{
//                                                                           return value;
//                                                                       }
//                                                                   });
//                                                                       vm.getDetails.masterDetailsValueModel = value;

                                                              }








                                                           },function(error) {
                                                               vm.loader = false;
                                                               handleErrorResponse(error);
                                                               $state.go('cum_to_non_cum_change_of_bond_type.list');
                                                             });
                                                       }
                                                       }

            /* Verify Submit */
            function verifyCustomerMaster() {
//                    Swal.fire({
//                            title: "Are you sure?",
//                            text: "Do you want to Really Verify this record?",
//                            type: "warning",
//                            showCancelButton: true,
//                            cancelButtonClass: "btn-cancel",
//                            confirmButtonClass: "btn-primary",
//                            confirmButtonText: "Yes",
//                            closeOnConfirm: false
//                        },
//                        function() {


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
                                'schemeCode': vm.schemeCode,
                                'subscriptionId': vm.subscriptionId,
                                'blaGroupId': vm.getDetails.blaGroupId,
                            }
                            cumToNonCumChangeOfBondTypeService.verifySubmit(requestData).then(
                                (response) => {
                                    vm.loader = false;
                                    if(vm.getDetails.lastVerifiedBy != null){
                                        Swal.fire("Success!","Cumulative to Non-Cumulative change of Bond Type Verified Successfully","success");
                                    }else{
                                        var message = 'Bond Ledger Account Verified Successfully';
                                        vm.modeOfHolding = vm.getDetails.masterDetailsValueModel[0].C003.oldValue;
                                        if(vm.modeOfHolding == 'MH001' || vm.modeOfHolding == 'MH004' || vm.modeOfHolding == 'MH005'){
                                            message = message + '\nBLA No: ' + vm.getDetails.subscriptionDetailsValueModel[0].C003.oldValue;
                                           if(vm.getDetails.subscriptionDetailsValueModel[0].C014){
                                             message = message + '\nSubscription Id: ' + vm.getDetails.subscriptionDetailsValueModel[0].C014.oldValue;
                                                }
                                        }
                                        else{
                                            if(vm.getDetails.subscriptionDetailsValueModel[0].C005){
                                                message = message + '\nFirst Joint BLA No: ' + vm.getDetails.subscriptionDetailsValueModel[0].C005.oldValue;
//                                                if(vm.getDetails.masterDetailsValueModel[0].C014.oldValue == 'Y'){
//                                                    message = message + '\nFirst Joint Guardian ID: ' + vm.getDetails.masterDetailsValueModel[0].C025.oldValue;
//                                                }
                                            }

                                            if(vm.getDetails.subscriptionDetailsValueModel[0].C007){
                                                message = message + '\nSecond Joint BLA No: ' + vm.getDetails.subscriptionDetailsValueModel[0].C007.oldValue;
//                                                if(vm.getDetails.masterDetailsValueModel[1].C014.oldValue == 'Y'){
//                                                    message = message + '\nSecond Joint Guardian ID: ' + vm.getDetails.masterDetailsValueModel[1].C025.oldValue;
//                                                }
                                            }

                                              if(vm.getDetails.subscriptionDetailsValueModel[0].C009){
                                                message = message + '\nThird Joint BLA No: ' + vm.getDetails.subscriptionDetailsValueModel[0].C009.oldValue;
//                                                if(vm.getDetails.masterDetailsValueModel[2].C014.oldValue == 'Y'){
//                                                    message = message + '\nThird Joint Guardian ID: ' + vm.getDetails.masterDetailsValueModel[2].C025.oldValue;
//                                                }
                                            }

                                              if(vm.getDetails.subscriptionDetailsValueModel[0].C014){
                                                                                            message = message + '\nSubscription Id: ' + vm.getDetails.subscriptionDetailsValueModel[0].C014.oldValue;

                                                                                        }

                                        }
                                        Swal.fire("Success!", message ,"success");
                                    }

                                    location.href = '#!/cum_to_non_cum_change_of_bond_type/action/inquiry';
                                }, function(error) {
                                   vm.loader = false;
                                   handleErrorResponse(error);
                                });
                                }

                        });
            }


//function deleteCustomerMaster() {
//    function handleKeyPress(event) {
//        var key = event.key;
//        var input = event.target.value;
//
//        // Prevent entering more than one consecutive space
//        if (key === ' ' && input.slice(-1) === ' ') {
//            event.preventDefault();
//        }
//
//        // Allow only alphabetic characters, single spaces, and backspace
//        if (!key.match(/^[a-zA-Z\s]$/) && key !== 'Backspace' && key !== 'Enter') {
//            event.preventDefault();
//        }
//    }
//
//    document.addEventListener('keypress', handleKeyPress);
//
//    Swal.fire({
//        title: "Rejection Remarks",
//        text: "",
//        type: "input",
//        inputPlaceholder: "Enter your remarks here",
//        showCancelButton: true,
//        cancelButtonClass: "btn-cancel",
//        confirmButtonClass: "btn-primary",
//        confirmButtonText: "Submit",
//        animation: "slide-from-top",
//        closeOnConfirm: false,
//        inputValidator: function(value) {
//            if (value.trim() === "") {
//                return "Please enter the remarks!";
//            }
//
//            if (/^[a-zA-Z\s]*$/.test(value.trim())) {
//                if (vm.getDetails.masterDetailsValueModel.length == 1) {
//                    vm.titleText = 'BOND HOLDER DETAILS';
//                    if (/\s\s/.test(value)) {
//                        return "Please enter only single spaces between words!";
//                    }
//                    document.removeEventListener('keypress', handleKeyPress);
//                    return true;
//                } else {
//                    return "Please enter only alphabetic characters!";
//                }
//            } else {
//                return "Please enter only alphabetic characters!";
//            }
//        }
//    }, function(inputValue) {
//        if (inputValue === false) {
//            document.removeEventListener('keypress', handleKeyPress);
//            return false;
//        }
//
//        if (inputValue === "") {
//            Swal.fire.showInputError("Please enter the remarks!");
//            return false;
//        }
//
//        vm.rejectionRemarks = inputValue;
//
//        var requestData = {
//            'schemeCode': vm.schemeCode,
//            'blaGroupId': vm.getDetails.blaGroupId,
//            'subscriptionId': vm.subscriptionId,
//            'rejectionRemarks': vm.rejectionRemarks
//        };
//
//        cumToNonCumChangeOfBondTypeService.deleteSubmit(requestData).then(
//            (response) => {
//                vm.loader = false;
//                Swal.fire("Success!", "Bond Ledger Account Deleted Successfully", "success");
//                location.href = '#!/cum_to_non_cum_change_of_bond_type/action/inquiry';
//            }, function(error) {
//                vm.loader = false;
//                handleErrorResponse(error);
//            }
//        );
//    });
//}

            //Inquiry Select
            function getInquirySelectDetails(flag,blaNo,schemeCode,schemeName,panNum,subscriptionId,bondHolderName){
                vm.loader = true;

                if(flag =='torch'){
                    var requestData = {
                        'schemeCode': vm.schemeCode,

                                                'schemeName': vm.schemeName,
                                                'panNum': vm.panNum,
                                                'bondHolderName': vm.bondHolderName,
                    };
                     cumToNonCumChangeOfBondTypeService.inquirySelect(requestData).then(function(response){
                      vm.loader = false;
                      vm.blaDataList = response.data.result;
                      angular.element('#blaModel').modal('toggle');
                    },function(error) {
                        vm.loader = false;
                        handleErrorResponse(error);
                      });
                }
                else if(flag =='subTorch'){
                                            var requestData = {
                                                'schemeCode': vm.schemeCode,
                                                       'blaNo': vm.blaNo,
                                                                        'schemeName': vm.schemeName,
                                                                        'panNum': vm.panNum,
                                                                        'bondHolderName': vm.bondHolderName,
                                            };
                                             cumToNonCumChangeOfBondTypeService.inquirySelect(requestData).then(function(response){
                                              vm.loader = false;
                                              vm.subscriptionList = response.data.result;
                                              angular.element('#subscriptionModel').modal('toggle');
                                            },function(error) {
                                                vm.loader = false;
                                                handleErrorResponse(error);
                                              });
                                        }


                else{

                    var requestData = {
                        'schemeCode': vm.schemeCode,
                        'blaNo': vm.blaNo,
                        'schemeName': vm.schemeName,
                        'panNum': vm.panNum,
                        'bondHolderName': vm.bondHolderName,
                        'subscriptionId': vm.subscriptionId,

                    };
                     cumToNonCumChangeOfBondTypeService.inquirySelect(requestData).then(function(response){

                       vm.getDetails = response.data.result;
                        vm.loader = false;
                         if(vm.getDetails && flag == "select"){
                        $state.go('cum_to_non_cum_change_of_bond_type.list_detail',({obj:{
                                  								schemeName: vm.schemeName,
                                  								schemeCode: vm.schemeCode,
                                  								blaNo: vm.blaNo,
                                  								bondSubsId : vm.subscriptionId,
                                  								modeOfHolding : vm.modeOfHolding,
                                  								panNum : vm.panNum,
                                  								moduleId : vm.moduleName,
                                  								subscriptionId: vm.subscriptionId,
                                  								bondHolderName: vm.bondHolderName,
                                  								}}));


                        }
                       if(vm.getDetails.masterDetailsValueModel.length == 1){
                            vm.titleText = 'BOND HOLDER DETAILS';
                       }else{
                             vm.titleText = 'BOND HOLDER DETAILS';

                            vm.tempArray = [];
                            vm.tempArray.push('0');
                            vm.tempArray.push('1');
                            if(vm.getDetails.masterDetailsValueModel.length > 2){
                                vm.tempArray.push('2');
                            }

                            angular.forEach(vm.getDetails.masterDetailsValueModel,function(item){

                                if(item.C004.oldValue == '1'){
                                    vm.tempArray.splice(0,'0', item);
                                }
                                if(item.C004.oldValue == '2'){
                                    vm.tempArray.splice(1,'1', item);
                                }
                                if(item.C004.oldValue == '3'){
                                    vm.tempArray.splice(2,'2', item);
                                }

                            });
//
//                            var value = vm.tempArray.filter((value) => {
//                                if(value == '0'){
//
//                                }else
//                                if(value == '1'){
//
//                                }else
//                                 if(value == '2'){
//
//                                }else{
//                                    return value;
//                                }
//                            });
//                                vm.getDetails.masterDetailsValueModel = value;

                       }








                    },function(error) {
                        vm.loader = false;

                   	 const errorMessage = error && error.data && error.data.message
                             ? error.data.message
                             : "An unexpected error occurred.";
                     if(null!=errorMessage && errorMessage.startsWith("Invalid")){
                     Swal.fire("Warning!", errorMessage , "warning");
                     $state.go("cum_to_non_cum_change_of_bond_type.list");
                     }else{
                     handleErrorResponse(error);
                     $state.go("cum_to_non_cum_change_of_bond_type.list");
                     }
                      });
                }

//                cumToNonCumChangeOfBondTypeService.inquirySelect(requestData).then(function(response){
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
//                    $state.go('cum_to_non_cum_change_of_bond_type.list');
//                  });
            }


             function getValidations(flag) {
//                vm.flagVal = flag;
//
//                cumToNonCumChangeOfBondTypeService.accountTypeValid().then(function(response){
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
             function accountHolderDateValidation(minAge){
                    var solDate = vm.loginSolDate;
                    var maxYear = convertToDateObject(solDate);
                    maxYear.setFullYear(maxYear.getFullYear() - 18);
                    var maxDate = maxYear;
                    vm.dobCalenderOptions  = {
                        //minDate: minDate,
                        maxDate: maxDate,
                        showWeeks: false
                    };
                    vm.customerMasterDetails.C020 = '';
             }
             function depositDateValidation(){
                  vm.depositDate={
                        maxDate: convertToDateObject(vm.loginSolDate),
                        showWeeks: false
                  }

             }
              function maturityDateValid(tempMatDate){
                    vm.maturityDate={
                          minDate: tempMatDate,
                          showWeeks: false
                    }

              }
              function dateOfRetirementValid(){
                    vm.customerMasterDetails.C022 ={
                        minDate: convertToDateObject(vm.loginSolDate),
                        showWeeks: false
                    }

              }
              function maturityDateValidation() {
                angular.forEach(vm.scssOtherBanksDetails, function(item){
                    item.C006 = item.C006;
                    vm.maturityDate = {
                          minDate: item.C006,
                          showWeeks: false
                    }
                })

              }
              vm.retirDatePickerValidation = {
                  showWeeks : false,
                  maxDate : convertToDateObject(vm.loginSolDate)
              }
              // format date from dd/MM/yyyy to yyyy-MM-dd
               function formatDate(tempDate){
                       return $filter('date')(tempDate, "dd-MMM-yyyy");
               }

               function dodVal(){
                     vm.dodCalenderOptions={
                       maxDate: convertToDateObject(vm.loginSolDate),
                       showWeeks: false
                      }

                }

                 function dodRecVal(recDate){
                          vm.dodRecCalenderOptions={
                           minDate: recDate,
                           showWeeks: false
                                      }

                  }

             // Cancel Confirmation
             function showCancelConfirmation(state) {
//                 Swal.fire({
//                         title: "Confirm Cancel",
//                         text: "Do you really want to cancel?",
//                         type: "warning",
//                         showCancelButton: true,
//                         cancelButtonClass: "btn-cancel",
//                         confirmButtonClass: "btn-danger",
//                         confirmButtonText: "Yes",
//                         cancelButtonText: "No",
//                         closeOnConfirm: true
//                     },
//                     function() {
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
                   }  });
             }

        function getDisableTorchFlag(customerId){
          if(customerId){
            if(customerId.length>=5){
                   vm.disableTorchFlag = false;
            }else{
                   vm.disableTorchFlag = true;
            }
          }else{
                   vm.disableTorchFlag = true;
          }
        }


        function getCbsCustFstJoinDetailsTwo(){
                    vm.loader = true;
                    var requestData = {
                        'cifId': vm.customerMasterDetailsTwo.cbsCustId,
                                               'operativeAccNo' : vm.customerMasterDetailsTwo.operativeAccNum,
                                               'operativeAccBranchId' : vm.customerMasterDetailsTwo.operativeAccBranchId
                                           };

                  cumToNonCumChangeOfBondTypeService.fetchCbsCustId(requestData).then( (response) => {
                            vm.loader = false;
                            vm.cbsDetailTwo = response.data.result;

                            vm.customerMasterDetailsTwo.bondHldFirstName = vm.cbsDetailTwo.firstName;
                            vm.customerMasterDetailsTwo.bondHldMidName = vm.cbsDetailTwo.middleName;
                            vm.customerMasterDetailsTwo.bondHldLastName = vm.cbsDetailTwo.lastName;
                            vm.customerMasterDetailsTwo.fatherName = vm.cbsDetailTwo.fatherName;
                            vm.customerMasterDetailsTwo.motherName = vm.cbsDetailTwo.motherName;
                            vm.customerMasterDetailsTwo.gender = vm.cbsDetailTwo.gender;
                            vm.customerMasterDetailsTwo.bondHldDob = convertToDateObject(vm.cbsDetailTwo.dateOfBirth);
                            vm.customerMasterDetailsTwo.isMinor = vm.cbsDetailTwo.isMinor;
                            vm.customerMasterDetailsTwo.bondHldAddOne = vm.cbsDetailTwo.addressOne;
                            vm.customerMasterDetailsTwo.bondHldAddTwo = vm.cbsDetailTwo.addressTwo;
                            vm.customerMasterDetailsTwo.bondHldAddThree = vm.cbsDetailTwo.addressThree;
                            vm.customerMasterDetailsTwo.bondHldCity = vm.cbsDetailTwo.city;
                            vm.customerMasterDetailsTwo.bondHldPinCode = vm.cbsDetailTwo.pinCode;
                            vm.customerMasterDetailsTwo.bondHolderPhoneNumber = vm.cbsDetailTwo.phoneNumber;
                            vm.customerMasterDetailsTwo.bondHldMobNum = vm.cbsDetailTwo.mobileNumber;
                            vm.customerMasterDetailsTwo.bondHldEmail = vm.cbsDetailTwo.email;
                            vm.customerMasterDetailsTwo.operativeAccNum = vm.cbsDetailTwo.operativeAccNo;
                            vm.customerMasterDetailsTwo.operativeAccBranchId = vm.cbsDetailTwo.operativeBranchId;
                            vm.customerMasterDetailsTwo.panNo = vm.cbsDetailTwo.pan;
                            vm.customerMasterDetailsTwo.aadhaarNo = vm.cbsDetailTwo.aadhar;

                        }, function(error) {
                           vm.loader = false;
                           vm.errmsgSingle = 'CBS Failure';
                           handleErrorResponse(error);
                           vm.customerMasterDetailsTwo.cbsCustId = '';
                        });


                }

        function getBlaAccNoDetailsTwo(flag){
                 vm.loader = true;

                 if(flag=='torch'){


                             var requestData = {
                                 'schemeCode': vm.schemeCode,
                                 'modeOfHolding': vm.modeOfHolding,
                             };
                               cumToNonCumChangeOfBondTypeService.fetchBlaAccNoDetails(requestData).then( (response) => {
                                         vm.loader = false;
                                         vm.blaNoListTwo = response.data.result;
                                         if(vm.modeOfHolding == 'MH002' || vm.modeOfHolding == 'MH003'){
                                                 angular.element('#blaJointModalTwo').modal('toggle');
                                         }else{
                                                angular.element('#blaModalTwo').modal('toggle');
                                         }
                                     }, function(error) {
                                        vm.loader = false;
                                        handleErrorResponse(error);
                                     });
                 }
                 else{
                           var requestData = {
                                 'schemeCode': vm.schemeCode,
                                 'modeOfHolding': vm.modeOfHolding,
                                 'blaNum' : vm.customerMasterTwo.blaAccNo,
                                 'jointHolderNo': vm.jointHolderTypeCodeTwo,
                                 };
                               cumToNonCumChangeOfBondTypeService.fetchBlaAccNoDetails(requestData).then( (response) => {
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

                                     }, function(error) {
                                        vm.loader = false;
                                        handleErrorResponse(error);
                                     });
                 }


                 }
                  function getClearAllData(x){
                  vm.customerMasterDetails.blaAccNo=null;
                            if(x ==1)
                            {
                            var z= Object.keys(vm.customerMasterDetails);
                            for(var i=0;i<z.length;i++)
                            {
                            vm.customerMasterDetails[z[i]]=''
                            }
                            }else  if(x ==2)
                            {
                           var z = Object.keys(vm.customerMasterDetailsTwo);
                             for(var i=0;i<z.length;i++)
                            {
                            vm.customerMasterDetailsTwo[z[i]]=''
                            }
                            }else if(x ==3)
                            {
                            var z =Object.keys(vm.customerMasterDetailsThree);
                              for(var i=0;i<z.length;i++)
                                                        {
                                                        vm.customerMasterDetailsTwo[z[i]]=''
                                                        }
                            }
                         }

             function getClearAllDatas(){
//                                         if(x ==1)
//                                         {
                                         var z= Object.keys(vm.customerMasterDetails);
                                         for(var i=0;i<z.length;i++)
                                         {
                                         vm.customerMasterDetails[z[i]]=''
                                         }

                                      }

                                           // Add coperson function
                                       vm.copersons = [{
                                           name: '',
                                           relationship: '',
                                           dob: '',
                                           datePickerOpened: false
                                       }];



                                          function addCoperson(){
                                              vm.copersons.push({
                                                  name: '',
                                                  relationship: '',
                                                  dob: '',
                                                  datePickerOpened: false
                                              });
                                          };

                                          // Delete coperson function
//                                          function deleteCoperson(index){
////                                          vm.deleteCoperson = function(index) {
//                                              vm.copersons.splice(index, 1);
//                                          };

                                          vm.deleteLastCoperson = function() {
                                              if (vm.copersons.length > 0) {
                                                  vm.copersons.splice(-1, 1); // Remove the last item from the array
                                              }
                                          };


//                                          // Initialize modeOfHolding (assuming it comes from your data or service)
//                                          vm.modeOfHolding = 'HUF';
//                                      };

        function getGuardianDetailsTwo(flag){
                        vm.loader = true;

                        if(flag == 'select'){
                            var requestData = {
                                'schemeCode' : vm.schemeCode,
                            };
                          cumToNonCumChangeOfBondTypeService.fetchGuardianDetails(requestData).then( (response) => {
                                    vm.loader = false;
                                    vm.guardianDetailsListTwo = response.data.result;
                                     angular.element('#guardianModalTwo').modal('toggle');
                                                      }, function(error) {
                                                         vm.loader = false;
                                                         handleErrorResponse(error);
                                                      });
                        }else{
                            var requestData = {
                                'schemeCode' : vm.schemeCode,
                                'guardianId' : vm.customerMasterDetailsTwo.guardianId,
                            };
                          cumToNonCumChangeOfBondTypeService.fetchGuardianDetails(requestData).then( (response) => {
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
                                }, function(error) {
                                   vm.loader = false;
                                   handleErrorResponse(error);
                                });
                        }




                        }

        function optionToggledTwo(){

                 vm.isAllSelected = [];
                    angular.forEach(vm.kycList, function(item)
                    {
                        vm.isAllSelected.push(item.selected);
                    });


                }

        function formFifteenGValidationTwo(value){
                    if(vm.customerMasterDetailsTwo.bondHldDob){
                        vm.customerMasterDetailsTwo.dateSubmission15g15htax = '';
                        var year = parseInt($filter('date')(vm.customerMasterDetailsTwo.bondHldDob, "yyyy"));
                        var cYear = parseInt($filter('date')(new Date(), "yyyy"));
                        var age = cYear - year;
                                    if(value == 'TF001'){
                                        //15g
                                        if(age > 60){
                                            Swal.fire("Error!", "Age of the holder is NOT BELOW 60 years", "error");
                                            vm.customerMasterDetailsTwo.form15g15htaxExeCerti = '';
                                        }
                                        vm.customerMasterDetailsTwo.tdsRateApplicable = '0';
                                    }
                                    if(value == 'TF002'){
                                        //15h
                                        if(age <= 60){
                                            Swal.fire("Error!", "Age of the holder is BELOW 60 years", "error");
                                            vm.customerMasterDetailsTwo.form15g15htaxExeCerti = '';
                                        }
                                        vm.customerMasterDetailsTwo.tdsRateApplicable = '0';
                                    }
                                    if(value == 'TF003') {
                                        //tax excemption cert
                                        vm.customerMasterDetailsTwo.tdsRateApplicable = '0';
                                    }
                                    if(value == 'NONE'){
                                        vm.customerMasterDetailsTwo.tdsRateApplicable = vm.tdsRateApplicableResponse;
                                    }
                    }else{
                         Swal.fire("Error!", "Please select a age", "error");
                         vm.customerMasterDetailsTwo.form15g15htaxExeCerti = '';
                    }


                }

        function formFifteenGValidationTwoM(value){
                            if(vm.customerMasterDetailsTwo.C013.oldValue){

                                var year = parseInt($filter('date')(vm.customerMasterDetailsTwo.C013.oldValue, "yyyy"));
                                var cYear = parseInt($filter('date')(new Date(), "yyyy"));
                                var age = cYear - year;
                                            if(value == 'TF001'){
                                                //15g
                                                if(age > 60){
                                                    Swal.fire("Error!", "Age of the holder is NOT BELOW 60 years", "error");
                                                    vm.customerMasterDetailsTwo.C038.oldValue = '';
                                                }
                                                vm.customerMasterDetailsTwo.C064.oldValue = '0';
                                            }
                                            if(value == 'TF002'){
                                                //15h
                                                if(age <= 60){
                                                    Swal.fire("Error!", "Age of the holder is BELOW 60 years", "error");
                                                    vm.customerMasterDetailsTwo.C038.oldValue = '';
                                                }
                                                vm.customerMasterDetailsTwo.C064.oldValue = '0';
                                            }
                                            if(value == 'TF003'){
                                                //tax excemption cert
                                                vm.customerMasterDetailsTwo.C064.oldValue = '0';
                                            }
                                            if(value == 'NONE'){
                                                    try{
                                                         vm.customerMasterDetailsTwo.C039.oldValue = '';
                                                    }catch(e){}
                                                    vm.customerMasterDetailsTwo.C064.oldValue = vm.tdsRateApplicableResponse;
                                            }
                            }else{
                                 Swal.fire("Error!", "Please select a age", "error");
                                  vm.customerMasterDetailsTwo.C038.oldValue = '';
                            }


                        }


function getCbsCustFstJoinDetailsThree(){
            vm.loader = true;


            var requestData = {
                                        'cifId': vm.customerMasterDetailsThree.cbsCustId,
                                        'operativeAccNo' : vm.customerMasterDetailsThree.operativeAccNum,
                                        'operativeAccBranchId' : vm.customerMasterDetailsThree.operativeAccBranchId
                                    };

          cumToNonCumChangeOfBondTypeService.fetchCbsCustId(requestData).then( (response) => {
                    vm.loader = false;
                    vm.cbsDetailThree = response.data.result;

                    vm.customerMasterDetailsThree.bondHldFirstName = vm.cbsDetailThree.firstName;
                    vm.customerMasterDetailsThree.bondHldMidName = vm.cbsDetailThree.middleName;
                    vm.customerMasterDetailsThree.bondHldLastName = vm.cbsDetailThree.lastName;
                    vm.customerMasterDetailsThree.fatherName = vm.cbsDetailThree.fatherName;
                    vm.customerMasterDetailsThree.motherName = vm.cbsDetailThree.motherName;
                    vm.customerMasterDetailsThree.gender = vm.cbsDetailThree.gender;
                    vm.customerMasterDetailsThree.bondHldDob = convertToDateObject(vm.cbsDetailThree.dateOfBirth);
                    vm.customerMasterDetailsThree.isMinor = vm.cbsDetailThree.isMinor;
                    vm.customerMasterDetailsThree.bondHldAddOne = vm.cbsDetailThree.addressOne;
                    vm.customerMasterDetailsThree.bondHldAddTwo = vm.cbsDetailThree.addressTwo;
                    vm.customerMasterDetailsThree.bondHldAddThree = vm.cbsDetailThree.addressThree;
                    vm.customerMasterDetailsThree.bondHldCity = vm.cbsDetailThree.city;
                    vm.customerMasterDetailsThree.bondHldPinCode = vm.cbsDetailThree.pinCode;
                    vm.customerMasterDetailsThree.bondHolderPhoneNumber = vm.cbsDetailThree.phoneNumber;
                    vm.customerMasterDetailsThree.bondHldMobNum = vm.cbsDetailThree.mobileNumber;
                    vm.customerMasterDetailsThree.bondHldEmail = vm.cbsDetailThree.email;
                    vm.customerMasterDetailsThree.operativeAccNum = vm.cbsDetailThree.operativeAccNo;
                    vm.customerMasterDetailsThree.operativeAccBranchId = vm.cbsDetailThree.operativeBranchId;
                    vm.customerMasterDetailsThree.panNo = vm.cbsDetailThree.pan;
                    vm.customerMasterDetailsThree.aadhaarNo = vm.cbsDetailThree.aadhar;

                }, function(error) {
                   vm.loader = false;
                   vm.errmsgSingle = 'CBS Failure';
                   handleErrorResponse(error);
                   vm.customerMasterDetailsThree.cbsCustId = '';
                });


        }

function getBlaAccNoDetailsThree(flag){
         vm.loader = true;

         if(flag=='torch'){


                     var requestData = {
                         'schemeCode': vm.schemeCode,
                         'modeOfHolding': vm.modeOfHolding,
                     };
                       cumToNonCumChangeOfBondTypeService.fetchBlaAccNoDetails(requestData).then( (response) => {
                                 vm.loader = false;
                                 vm.blaNoListThree = response.data.result;
                                 if(vm.modeOfHolding == 'MH002' || vm.modeOfHolding == 'MH003'){
                                         angular.element('#blaJointModalThree').modal('toggle');
                                 }else{
                                        angular.element('#blaModal').modal('toggle');
                                 }
                             }, function(error) {
                                vm.loader = false;
                                handleErrorResponse(error);
                             });
         }
         else{
                   var requestData = {
                         'schemeCode': vm.schemeCode,
                         'modeOfHolding': vm.modeOfHolding,
                         'blaNum' : vm.customerMasterThree.blaAccNo,
                         'jointHolderNo': vm.jointHolderTypeCodeThree,
                         };

                       cumToNonCumChangeOfBondTypeService.fetchBlaAccNoDetails(requestData).then( (response) => {
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

                             }, function(error) {
                                vm.loader = false;
                                handleErrorResponse(error);
                             });
         }


         }

function getGuardianDetailsThree(flag){
                vm.loader = true;

                if(flag == 'select'){
                    var requestData = {
                        'schemeCode' : vm.schemeCode,
                    };
                  cumToNonCumChangeOfBondTypeService.fetchGuardianDetails(requestData).then( (response) => {
                            vm.loader = false;
                            vm.guardianDetailsListThree = response.data.result;
                            angular.element('#guardianModalThree').modal('toggle');
                        }, function(error) {
                           vm.loader = false;
                           handleErrorResponse(error);
                        });
                }else{
                    var requestData = {
                        'schemeCode' : vm.schemeCode,
                        'guardianId' : vm.customerMasterDetailsThree.guardianId,
                    };
                  cumToNonCumChangeOfBondTypeService.fetchGuardianDetails(requestData).then( (response) => {
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
                        }, function(error) {
                           vm.loader = false;
                           handleErrorResponse(error);
                        });
                }




                }

function optionToggledThree(){

         vm.isAllSelected = [];
            angular.forEach(vm.kycList, function(item)
            {
                vm.isAllSelected.push(item.selected);
            });


        }

        function formFifteenGValidationThree(value){
        debugger;
            if(vm.customerMasterDetailsThree.bondHldDob){
                vm.customerMasterDetailsThree.dateSubmission15g15htax = '';
                var year = parseInt($filter('date')(vm.customerMasterDetailsThree.bondHldDob, "yyyy"));
                var cYear = parseInt($filter('date')(new Date(), "yyyy"));
                var age = cYear - year;
                            if(value == 'TF001'){
                                //15g
                                if(age > 60){
                                    Swal.fire("Error!", "Age of the holder is NOT BELOW 60 years", "error");
                                    vm.customerMasterDetailsThree.form15g15htaxExeCerti = '';
                                }
                                vm.customerMasterDetailsThree.tdsRateApplicable = '0';
                            }
                            if(value == 'TF002'){
                                //15h
                                if(age <= 60){
                                    Swal.fire("Error!", "Age of the holder is BELOW 60 years", "error");
                                    vm.customerMasterDetailsThree.form15g15htaxExeCerti = '';
                                }
                                vm.customerMasterDetailsThree.tdsRateApplicable = '0';
                            }
                            if(value == 'TF003'){
                                //tax excemption cert
                                vm.customerMasterDetailsThree.tdsRateApplicable = '0';
                            }
                            if(value == 'NONE'){
                                vm.customerMasterDetailsThree.tdsRateApplicable = vm.tdsRateApplicableResponse;
                            }
            }else{
                 Swal.fire("Error!", "Please select a age", "error");
                 vm.customerMasterDetailsThree.form15g15htaxExeCerti = '';
            }


        }

        function formFifteenGValidationThreenM(value){
                            if(vm.customerMasterDetailsThree.C013.oldValue){

                                var year = parseInt($filter('date')(vm.customerMasterDetailsThree.C013.oldValue, "yyyy"));
                                var cYear = parseInt($filter('date')(new Date(), "yyyy"));
                                var age = cYear - year;
                                            if(value == 'TF001'){
                                                //15g
                                                if(age > 60){
                                                    Swal.fire("Error!", "Age of the holder is NOT BELOW 60 years", "error");
                                                    vm.customerMasterDetailsThree.C038.oldValue = '';
                                                }
                                                vm.customerMasterDetailsThree.C064.oldValue = '0';
                                            }
                                            if(value == 'TF002'){
                                                //15h
                                                if(age <= 60){
                                                    Swal.fire("Error!", "Age of the holder is BELOW 60 years", "error");
                                                    vm.customerMasterDetailsThree.C038.oldValue = '';
                                                }
                                                vm.customerMasterDetailsThree.C064.oldValue = '0';
                                            }
                                            if(value == 'TF003'){
                                                //tax excemption cert
                                                vm.customerMasterDetailsThree.C064.oldValue = '0';
                                            }
                                            if(value == 'NONE'){
                                                    try{
                                                        vm.customerMasterDetailsThree.C039.oldValue = '';
                                                    }catch(e){}

                                                    vm.customerMasterDetailsThree.C064.oldValue = vm.tdsRateApplicableResponse;
                                            }
                            }else{
                                 Swal.fire("Error!", "Please select a age", "error");
                                  vm.customerMasterDetailsThree.C038.oldValue = '';
                            }


                        }

        function getSchemeTypeListOperativeTwo (acctNum, flag){
                vm.loader = true;
                vm.customerMasterDetailsTwo.operativeAccBranchId='';

                if(acctNum){

                commonService.getCustomAcctInqApi(acctNum.toUpperCase(), function (response) {
        					if (response.success) {
        						vm.accList = response.data;
        						vm.loader = false;
        						if (vm.accList.length > 0) {
        						if (vm.accList[0].status == 'S') {


                                    if(vm.accList[0].accountFreezeStatus) {
                                      vm.cbsErrorMessageTwo = 'Account is Friezed';
                                    }else{
                                        vm.customerMasterDetailsTwo.operativeAccBranchId = vm.accList[0].solID;
                                        vm.cbsErrorMessageTwo = null;
                                    }


        							}
        							if (vm.accList[0].status == 'F') {
        								vm.cbsErrorMessageTwo = 'Invalid  Account  Number';
        							}
        						}
        						else {
                                    vm.cbsErrorMessageTwo = 'Invalid  Account  Number';
        						}
        					} else {
        						Swal.fire("Error!", response.message, "error");
        						vm.loader = false;
        					}
        				}, function (err) {
        					handleErrorResponse(err);
        					vm.loader = false;
        				});
                }else{
                     vm.loader = false;
                 }
            }

        function getSchemeTypeListOperativeThree (acctNum, flag){
                vm.loader = true;
                 vm.customerMasterDetailsThree.operativeAccBranchId='';
                 vm.cbsErrorMessageThree ='';
                if(acctNum){

                commonService.getCustomAcctInqApi(acctNum.toUpperCase(), function (response) {
        					if (response.success) {
        						vm.accList = response.data;
        						vm.loader = false;
        						if (vm.accList.length > 0) {
        						if (vm.accList[0].status == 'S') {


                                    if(vm.accList[0].accountFreezeStatus) {
                                      vm.cbsErrorMessageThree = 'Account is Friezed';
                                    }else{
                                        vm.customerMasterDetailsThree.operativeAccBranchId = vm.accList[0].solID;
                                        vm.cbsErrorMessageThree = null;
                                    }


        							}
        							if (vm.accList[0].status == 'F') {
        								vm.cbsErrorMessageThree= 'Invalid  Account  Number';
        							}
        						}
        						else {
                                    vm.cbsErrorMessageThree = 'Invalid  Account  Number';
        						}
        					} else {
        						Swal.fire("Error!", response.message, "error");
        						vm.loader = false;
        					}
        				}, function (err) {
        					handleErrorResponse(err);
        					vm.loader = false;
        				});

                }else{
                     vm.loader = false;
                 }
            }

        function getCbsCustFstJoinDetailsModify(){
                    vm.loader = true;

                    var requestData = {
                                                'cifId': vm.customerMasterDetails.C004.oldValue,
                                                'operativeAccNo' : vm.customerMasterDetails.C034.oldValue,
                                                'operativeAccBranchId' : vm.customerMasterDetails.C035.oldValue
                                            };

                  cumToNonCumChangeOfBondTypeService.fetchCbsCustId(requestData).then( (response) => {
                            vm.loader = false;
                            vm.cbsDetail = response.data.result;

                            vm.customerMasterDetails.C006.oldValue = vm.cbsDetail.firstName;
                            vm.customerMasterDetails.C007.oldValue = vm.cbsDetail.middleName;
                            vm.customerMasterDetails.C008.oldValue = vm.cbsDetail.lastName;
                            vm.customerMasterDetails.C010.oldValue = vm.cbsDetail.fatherName;
                            vm.customerMasterDetails.C011.oldValue = vm.cbsDetail.motherName;
                            vm.customerMasterDetails.C012.oldValue = vm.cbsDetail.gender;
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

                        }, function(error) {
                           vm.loader = false;
                           vm.errmsgSingle = 'CBS Failure';
                           handleErrorResponse(error);
                           vm.customerMasterDetails.C004.oldValue = '';
                        });
                }

        function getCbsCustFstJoinDetailsTwoModify(){
                            vm.loader = true;


                             var requestData = {
                                                                            'cifId': vm.customerMasterDetailsTwo.C004.oldValue,
                                                                            'operativeAccNo' : vm.customerMasterDetailsTwo.C034.oldValue,
                                                                            'operativeAccBranchId' : vm.customerMasterDetailsTwo.C035.oldValue
                                                                        };

                          cumToNonCumChangeOfBondTypeService.fetchCbsCustId(requestData).then( (response) => {
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

                                }, function(error) {
                                   vm.loader = false;
                                   vm.errmsgSingle = 'CBS Failure';
                                   handleErrorResponse(error);
                                   vm.customerMasterDetailsTwo.C004.oldValue = '';
                                });


                        }

        function getCbsCustFstJoinDetailsThreeModify(){
                    vm.loader = true;


                     var requestData = {
                                                                                                'cifId': vm.customerMasterDetailsThree.C004.oldValue,
                                                                                                'operativeAccNo' : vm.customerMasterDetailsThree.C034.oldValue,
                                                                                                'operativeAccBranchId' : vm.customerMasterDetailsThree.C035.oldValue
                                                                                            };


                  cumToNonCumChangeOfBondTypeService.fetchCbsCustId(requestData).then( (response) => {
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

                        }, function(error) {
                           vm.loader = false;
                           vm.errmsgSingle = 'CBS Failure';
                           handleErrorResponse(error);
                           vm.customerMasterDetailsThree.C004.oldValue = '';
                        });


                }



          function checkAge(item){

                    if(typeof(vm.customerMasterDetails.C013.oldValue) != 'undefined'){
                     var dob = new Date(vm.customerMasterDetails.C013.oldValue);
                     var month_diff = Date.now() - dob.getTime();
                     var age_dt = new Date(month_diff);
                     var year = age_dt.getUTCFullYear();

                     var age = Math.abs(year - 1970);

                     if(age >= 18){
                     Swal.fire("Not a Minor!");
                     vm.customerMasterDetails.C013.oldValue = "";
                     }
                   }

                   }

        function getBlaAccNoDetailsModify(flag){
                vm.loader = true;

                if(flag=='torch'){


                            var requestData = {
                                'schemeCode': vm.schemeCode,
                                'modeOfHolding': vm.modeOfHolding,
                            };
                              cumToNonCumChangeOfBondTypeService.fetchBlaAccNoDetails(requestData).then( (response) => {
                                        vm.loader = false;
                                        vm.blaNoList = response.data.result;
                                        if(vm.modeOfHolding == 'MH002' || vm.modeOfHolding == 'MH003'){
                                                angular.element('#blaJointModal').modal('toggle');
                                        }else{
                                               angular.element('#blaModal').modal('toggle');
                                        }
                                    }, function(error) {
                                       vm.loader = false;
                                       handleErrorResponse(error);
                                    });
                }
                else{
                          var requestData = {
                                'schemeCode': vm.schemeCode,
                                'modeOfHolding': vm.modeOfHolding,
                                'blaNum' : vm.customerMasterDetails.C005.oldValue,
                                'jointHolderNo': '1',
                                };
                              cumToNonCumChangeOfBondTypeService.fetchBlaAccNoDetails(requestData).then( (response) => {
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

                                    }, function(error) {
                                       vm.loader = false;
                                       vm.customerMasterDetails.C005.oldValue='';
                                       handleErrorResponse(error);
                                    });
                }

                }

        function getBlaAccNoDetailsTwoModify(flag){
                         vm.loader = true;

                         if(flag=='torch'){


                                     var requestData = {
                                         'schemeCode': vm.schemeCode,
                                         'modeOfHolding': vm.modeOfHolding,
                                     };
                                       cumToNonCumChangeOfBondTypeService.fetchBlaAccNoDetails(requestData).then( (response) => {
                                                 vm.loader = false;
                                                 vm.blaNoListTwo = response.data.result;
                                                 if(vm.modeOfHolding == 'MH002' || vm.modeOfHolding == 'MH003'){
                                                         angular.element('#blaJointModalTwo').modal('toggle');
                                                 }else{
                                                        angular.element('#blaModalTwo').modal('toggle');
                                                 }
                                             }, function(error) {
                                                vm.loader = false;
                                                handleErrorResponse(error);
                                             });
                         }
                         else{
                                   var requestData = {
                                         'schemeCode': vm.schemeCode,
                                         'modeOfHolding': vm.modeOfHolding,
                                         'blaNum' : vm.customerMasterDetailsTwo.C005.oldValue,
                                         'jointHolderNo': '2',
                                         };
                                       cumToNonCumChangeOfBondTypeService.fetchBlaAccNoDetails(requestData).then( (response) => {
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

                                             }, function(error) {
                                                vm.loader = false;
                                                vm.customerMasterDetailsTwo.C005.oldValue='';
                                                handleErrorResponse(error);
                                             });
                         }


                         }


        function getBlaAccNoDetailsThreeModify(flag){
                 vm.loader = true;

                 if(flag=='torch'){


                             var requestData = {
                                 'schemeCode': vm.schemeCode,
                                 'modeOfHolding': vm.modeOfHolding,
                             };
                               cumToNonCumChangeOfBondTypeService.fetchBlaAccNoDetails(requestData).then( (response) => {
                                         vm.loader = false;
                                         vm.blaNoListThree = response.data.result;
                                         if(vm.modeOfHolding == 'MH002' || vm.modeOfHolding == 'MH003'){
                                                 angular.element('#blaJointModalThree').modal('toggle');
                                         }else{
                                                angular.element('#blaModal').modal('toggle');
                                         }
                                     }, function(error) {
                                        vm.loader = false;
                                        handleErrorResponse(error);
                                     });
                 }
                 else{
                           var requestData = {
                                 'schemeCode': vm.schemeCode,
                                 'modeOfHolding': vm.modeOfHolding,
                                 'blaNum' : vm.customerMasterDetailsThree.C005.oldValue,
                                 'jointHolderNo': '3',
                                 };
                               cumToNonCumChangeOfBondTypeService.fetchBlaAccNoDetails(requestData).then( (response) => {
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



                                     }, function(error) {
                                        vm.loader = false;
                                        vm.customerMasterDetailsThree.C005.oldValue='';

                                        handleErrorResponse(error);
                                     });
                 }


                 }

        function getGuardianDetailsModify(flag){
                        vm.loader = true;

                        if(flag == 'select'){
                            var requestData = {
                                'schemeCode' : vm.schemeCode,
                            };
                          cumToNonCumChangeOfBondTypeService.fetchGuardianDetails(requestData).then( (response) => {
                                    vm.loader = false;
                                    vm.guardianDetailsList = response.data.result;
                                    angular.element('#guardianModal').modal('toggle');
                                }, function(error) {
                                   vm.loader = false;
                                   handleErrorResponse(error);
                                });
                        }else{
                            var requestData = {
                                'schemeCode' : vm.schemeCode,
                                'guardianId' : vm.customerMasterDetails.C025.oldValue,
                            };
                          cumToNonCumChangeOfBondTypeService.fetchGuardianDetails(requestData).then( (response) => {
                                    vm.loader = false;
                                    vm.guardianDetail = response.data.result[0];

                                    vm.customerMasterDetails.C026.oldValue = vm.guardianDetail.guardianFirstName;
                                    vm.customerMasterDetails.C027.oldValue = vm.guardianDetail.guardianMiddleName;
                                    vm.customerMasterDetails.C028.oldValue = vm.guardianDetail.guardianLastName;
                                    vm.customerMasterDetails.C030.oldValue = convertToDateObject(vm.guardianDetail.dateOfBirth);
                                    vm.customerMasterDetails.C031.oldValue = vm.guardianDetail.address;
                                    vm.customerMasterDetails.C032.oldValue = vm.guardianDetail.mobileNo;
                                    vm.customerMasterDetails.C033.oldValue = vm.guardianDetail.email;
                                }, function(error) {
                                   vm.loader = false;
                                   handleErrorResponse(error);
                                });
                        }




                        }

        function getGuardianDetailsTwoModify(flag){
                                vm.loader = true;

                                if(flag == 'select'){
                                    var requestData = {
                                        'schemeCode' : vm.schemeCode,
                                    };
                                  cumToNonCumChangeOfBondTypeService.fetchGuardianDetails(requestData).then( (response) => {
                                            vm.loader = false;
                                            vm.guardianDetailsListTwo = response.data.result;
                                            angular.element('#guardianModalTwo').modal('toggle');
                                        }, function(error) {
                                           vm.loader = false;
                                           handleErrorResponse(error);
                                        });
                                }else{
                                    var requestData = {
                                        'schemeCode' : vm.schemeCode,
                                        'guardianId' : vm.customerMasterDetailsTwo.C025.oldValue,
                                    };
                                  cumToNonCumChangeOfBondTypeService.fetchGuardianDetails(requestData).then( (response) => {
                                            vm.loader = false;
                                            vm.guardianDetailTwo = response.data.result[0];


                                    vm.customerMasterDetailsTwo.C026.oldValue = vm.guardianDetailTwo.guardianFirstName;
                                    vm.customerMasterDetailsTwo.C027.oldValue = vm.guardianDetailTwo.guardianMiddleName;
                                    vm.customerMasterDetailsTwo.C028.oldValue = vm.guardianDetailTwo.guardianLastName;
                                    vm.customerMasterDetailsTwo.C030.oldValue = convertToDateObject(vm.guardianDetailTwo.dateOfBirth);
                                    vm.customerMasterDetailsTwo.C031.oldValue = vm.guardianDetailTwo.address;
                                    vm.customerMasterDetailsTwo.C032.oldValue = vm.guardianDetailTwo.mobileNo;
                                    vm.customerMasterDetailsTwo.C033.oldValue = vm.guardianDetailTwo.email;
                                        }, function(error) {
                                           vm.loader = false;
                                           handleErrorResponse(error);
                                        });
                                }




                                }

        function getGuardianDetailsThreeModify(flag){
                        vm.loader = true;

                        if(flag == 'select'){
                            var requestData = {
                                'schemeCode' : vm.schemeCode,
                            };
                          cumToNonCumChangeOfBondTypeService.fetchGuardianDetails(requestData).then( (response) => {
                                    vm.loader = false;
                                    vm.guardianDetailsListThree = response.data.result;
                                    angular.element('#guardianModalThree').modal('toggle');
                                }, function(error) {
                                   vm.loader = false;
                                   handleErrorResponse(error);
                                });
                        }else{
                            var requestData = {
                                'schemeCode' : vm.schemeCode,
                                'guardianId' : vm.customerMasterDetailsThree.C025.oldValue,
                            };
                          cumToNonCumChangeOfBondTypeService.fetchGuardianDetails(requestData).then( (response) => {
                                    vm.loader = false;
                                    vm.guardianDetailThree = response.data.result[0];
                                    vm.customerMasterDetailsThree.C026.oldValue = vm.guardianDetailThree.guardianFirstName;
                                    vm.customerMasterDetailsThree.C027.oldValue = vm.guardianDetailThree.guardianMiddleName;
                                    vm.customerMasterDetailsThree.C028.oldValue = vm.guardianDetailThree.guardianLastName;
                                    vm.customerMasterDetailsThree.C030.oldValue = convertToDateObject(vm.guardianDetailThree.dateOfBirth);
                                    vm.customerMasterDetailsThree.C031.oldValue = vm.guardianDetailThree.address;
                                    vm.customerMasterDetailsThree.C032.oldValue = vm.guardianDetailThree.mobileNo;
                                    vm.customerMasterDetailsThree.C033.oldValue = vm.guardianDetailThree.email;
                                }, function(error) {
                                   vm.loader = false;
                                   handleErrorResponse(error);
                                });
                        }




                        }

                function checkAge(isMinor, dateOfBirth,mode,list){

                        if(typeof(dateOfBirth) != 'undefined'){
                         var dob = new Date(dateOfBirth);
                         var month_diff = Date.now() - dob.getTime();
                         var age_dt = new Date(month_diff);
                         var year = age_dt.getUTCFullYear();

                         var age = Math.abs(year - 1970);

                         if(age >= 18 && isMinor == 'Y'){
                         Swal.fire("Not a Minor!", "Please enter the correct DOB", "info");
                         if(mode=='add'){

                         if(list=='1'){vm.customerMasterDetails.bondHldDob = "";}
                         if(list=='2'){vm.customerMasterDetailsTwo.bondHldDob = "";}
                         if(list=='3'){vm.customerMasterDetailsThree.bondHldDob="";}
                         }


                         }
                         else if(age < 18 && isMinor == 'N'){
                         Swal.fire("Not a Major!", "Please enter the correct DOB", "info");
                         if(mode=='add'){
                         if(list=='1'){vm.customerMasterDetails.bondHldDob = "";}
                         if(list=='2'){vm.customerMasterDetailsTwo.bondHldDob = "";}
                         if(list=='3'){vm.customerMasterDetailsThree.bondHldDob="";}
                         }

                         }
                       }

                       }



                        function validateOperativeAccNumForDebit(acctNum) {
                               vm.cbsErrorMessageForDebit = '';
                               if(acctNum){
                                   vm.cbsErrorMessage = '';
                                   vm.loader = true;
                                   commonService.getCustomAcctInqApi(acctNum, function (response) {
                                                           if (response.success) {
                                                               vm.accList = response.data;
                                                               vm.loader = false;
                                                               if (vm.accList.length >= 0) {
                                                               if (vm.accList[0].status == 'S') {


                                                                   if(vm.accList[0].accountFreezeStatus) {
                                                                     vm.cbsErrorMessageForDebit = 'Account is Freezed';
                                                                     vm.debitAccNo = '';
                                                                     vm.debitBranchId = '';

                                                                   }else{
                                                                       vm.debitBranchId = vm.accList[0].solID;
                                                                       vm.cbsErrorMessageForDebit = '';
                                                                   }


                                                                   }
                                                                   if (vm.accList[0].status == 'F') {
                                                                       vm.cbsErrorMessageForDebit = 'Invalid Account Number';
                                                                       vm.debitAccNo = '';
                                                                       vm.debitBranchId = '';
                                                                   }
                                                               }
                                                               else {
                                                                   vm.cbsErrorMessageForDebit = 'Invalid Account Number';
                                                                   vm.debitAccNo = '';
                                                                   vm.debitBranchId = '';
                                                               }
                                                           } else {
                                                               Swal.fire("Error!", response.message, "error");
                                                               vm.loader = false;
                                                               vm.debitAccNo = '';
                                                               vm.debitBranchId = '';
                                                           }
                                                       }
                                                       , function (err) {
                                                           handleErrorResponse(err);
                                                           vm.loader = false;
                                                           vm.debitAccNo = '';
                                                           vm.operativeAccBranchId = '';
                                                       });

                             }  }

                               /*Operative Acct Num*/
                               function validateOperativeAccNumForDebitModify(acctNum) {
                               vm.cbsErrorMessageForDebit = '';
                               if(acctNum){
                                   vm.cbsErrorMessage = '';
                                   vm.loader = true;
                                   commonService.getCustomAcctInqApi(acctNum, function (response) {
                                                           if (response.success) {
                                                               vm.accList = response.data;
                                                               vm.loader = false;
                                                               if (vm.accList.length >= 0) {
                                                               if (vm.accList[0].status == 'S') {


                                                                   if(vm.accList[0].accountFreezeStatus) {
                                                                     vm.cbsErrorMessageForDebit = 'Account is Friezed';
                                                                     vm.getDetails.C032.oldValue = '';
                                                                     vm.getDetails.C033.oldValue = '';
                                                                   }else{
                                                                       vm.getDetails.C033.oldValue = vm.accList[0].solID;
                                                                       vm.cbsErrorMessageForDebit = '';
                                                                   }


                                                                   }
                                                                   if (vm.accList[0].status == 'F') {
                                                                       vm.cbsErrorMessageForDebit = 'Invalid Account Number';
                                                                       vm.getDetails.C032.oldValue = '';
                                                                       vm.getDetails.C033.oldValue = '';
                                                                   }
                                                               }
                                                               else {
                                                                   vm.cbsErrorMessageForDebit = 'Invalid Account Number';
                                                                   vm.getDetails.C032.oldValue = '';
                                                                   vm.getDetails.C033.oldValue = '';
                                                               }
                                                           } else {
                                                               Swal.fire("Error!", response.message, "error");
                                                               vm.loader = false;
                                                               vm.getDetails.C032.oldValue = '';
                                                               vm.getDetails.C033.oldValue = '';
                                                           }
                                                       }
                                                       , function (err) {
                                                           handleErrorResponse(err);
                                                           vm.loader = false;
                                                           vm.getDetails.C032.oldValue = '';
                                                           vm.getDetails.C033.oldValue = '';
                                                       });

                             }  }

                               function validateOperativeAccNumM(acctNum){
                                   vm.loader = true;
                                   vm.cbsErrorMessage = '';
                                   if(acctNum){

                                   commonService.getCustomAcctInqApi(acctNum, function (response) {
                                               if (response.success) {
                                                   vm.accList = response.data;
                                                   vm.loader = false;
                                                   if (vm.accList.length > 0) {
                                                   if (vm.accList[0].status == 'S') {


                                                       if(vm.accList[0].accountFreezeStatus) {
                                                         vm.cbsErrorMessage = 'Account is Friezed';
                                                         vm.getDetails.C023.oldValue = null;
                                                         vm.getDetails.C024.oldValue = null;
                                                       }else{
                                                           if(vm.getDetails.C024) {
                                                            vm.getDetails.C024.oldValue = vm.accList[0].solID;
                                                           }
                                                           else{
                                                            vm.getDetails.C024 = {};
                                                            vm.getDetails.C024.oldValue = "";
                                                            vm.getDetails.C024.oldValue = vm.accList[0].solID;
                                                           }
                                                       }


                                                       }
                                                       if (vm.accList[0].status == 'F') {
                                                           vm.cbsErrorMessage = vm.accList[0].errDesc;
                                                           vm.getDetails.C024.oldValue = null;
                                                       }
                                                   }
                                                   else {
                                                       vm.getDetails.C023.oldValue = null;
                                                       vm.getDetails.C024.oldValue = null;
                                                       vm.cbsErrorMessage = 'Invalid Account Number';
                                                   }
                                               } else {
                                                   vm.getDetails.C023.oldValue = null;
                                                   vm.getDetails.C024.oldValue = null;
                                                   Swal.fire("Error!", response.message, "error");
                                                   vm.loader = false;
                                               }
                                           }, function (err) {
                                               vm.getDetails.C023.oldValue = null;
                                               vm.getDetails.C024.oldValue = null;
                                               handleErrorResponse(err);
                                               vm.loader = false;
                                           });

                                   }else{
                                        vm.loader = false;
                                    }
                               }


//                               function getState() {
//
//                                        vm.listType = 'STATES OF INDIA';
//                                        var requestData = {
//                                        referenceId:vm.listType
//                                        }
//                           cumToNonCumChangeOfBondTypeService.getReferenceData(requestData).then(function(response) {
//                               vm.stateListValues = response.data.result;
//                               vm.stateList = [];
//                               angular.forEach(vm.stateListValues, function(item) {
//                                   vm.stateList.push({
//                                       subCodeDescription: item.DESCRIPTION,
//                                       subCode: item.SUBCODE
//                                   });
//                               });
//                           });
//                       }

                               function validateOperativeAccNumNominee(acctNum){
                                   vm.loader = true;
                                   if(acctNum){

                                   commonService.getCustomAcctInqApi(acctNum, function (response) {
                                               if (response.success) {
                                                   vm.accList = response.data;
                                                   vm.loader = false;
                                                   if (vm.accList.length > 0) {
                                                   if (vm.accList[0].status == 'S') {


                                                       if(vm.accList[0].accountFreezeStatus) {
                                                         vm.cbsErrorMessage = 'Account is Friezed';
                                                       }else{
                                                           vm.operativeAccBranchId = vm.accList[0].solID;
                                                       }


                                                       }
                                                       if (vm.accList[0].status == 'F') {
                                                           vm.cbsErrorMessage = vm.accList[0].errDesc;
                                                       }
                                                   }
                                                   else {
                                                       vm.cbsErrorMessage = 'Invalid Account Number';
                                                   }
                                               } else {
                                                   Swal.fire("Error!", response.message, "error");
                                                   vm.loader = false;
                                               }
                                           }, function (err) {
                                               handleErrorResponse(err);
                                               vm.loader = false;
                                           });




                                   }else{
                                        vm.loader = false;
                                    }
                               }


//                               function getAddBlaTorch(){
//                                   vm.loader = true;
//                                       var requestData = {
//                                       "schemeCode": vm.schemeCode,
//                                       };
//                                      cumToNonCumChangeOfBondTypeService.getValidationAdd(requestData).then(function(response){
//                                           vm.loader = false;
//                                           vm.blaTorchList = response.data.result.torchValue;
//                                         angular.element('#blaModal').modal('toggle');
//
//                                       },function(error) {
//                                       vm.loader = false;
//                                          handleErrorResponse(error);
//                                        });
//
//                               }
//
//                               function getAddBlaTorch(){
//                                   vm.loader = true;
//                                       var requestData = {
//                                       "schemeCode": vm.schemeCode,
//                                       };
//                                      cumToNonCumChangeOfBondTypeService.getValidationAdd(requestData).then(function(response){
//                                           vm.loader = false;
//                                           vm.blaTorchList = response.data.result.torchValue;
//                                         angular.element('#blaModal').modal('toggle');
//
//                                       },function(error) {
//                                       vm.loader = false;
//                                          handleErrorResponse(error);
//                                        });
//
//                               }
//
//                               function inquiryBlaTorch(){
//                                           vm.loader = true;
//                                               var requestData = {
//                                               "schemeCode": vm.schemeCode,
//                                               };
//                                              cumToNonCumChangeOfBondTypeService.getInquiryDetails(requestData).then(function(response){
//                                                   vm.loader = false;
//                                                   vm.blaModalList = response.data.result.torchValue;
//                                                   angular.element('#blaModal').modal('toggle');
//                                               },function(error) {
//                                                    vm.loader = false;
//                                                  handleErrorResponse(error);
//                                                });
//
//                                       }



                               function inquirySubscriptionTorch(){
                                                   vm.loader = true;
                                                       var requestData = {
                                                       "schemeCode": vm.schemeCode,
                                                       "blaNo": vm.blaNo,
                                                       };
                                                      cumToNonCumChangeOfBondTypeService.getInquiryDetails(requestData).then(function(response){
                                                           vm.loader = false;
                                                           vm.subscriptionModalList = response.data.result.torchValue;
                                                           angular.element('#subscriptionModal').modal('toggle');
                                                       },function(error) {
                                                            vm.loader = false;
                                                          handleErrorResponse(error);
                                                        });

                                               }





                                    function getOtherBankDetailsTorch(){
                                                         vm.loader = true;
                                                             var requestData = {};
                                                            cumToNonCumChangeOfBondTypeService.getOtherBankDetails(requestData).then(function(response){
                                                                 vm.loader = false;
                                                                 vm.otherBankDetailsTorchList = response.data.result.torchValue;
                                                                 angular.element('#otherBankDetailsModal').modal('toggle');
                                                             },function(error) {
                                                                  vm.loader = false;
                                                                handleErrorResponse(error);
                                                              });

                                                     }

                           function getOtherBankDetailsMicrTorch(){
                                                   vm.loader = true;
                                                       var requestData = {};
                                                      cumToNonCumChangeOfBondTypeService.getOtherBankDetails(requestData).then(function(response){
                                                           vm.loader = false;
                                                           vm.otherBankDetailsMicrTorchList = response.data.result.torchValue;
                                                           angular.element('#otherBankDetailsMicrModal').modal('toggle');
                                                       },function(error) {
                                                            vm.loader = false;
                                                          handleErrorResponse(error);
                                                        });

                                               }

                                               function getOtherBankDetailsMicrTorchM(){
                                                                           vm.loader = true;
                                                                               var requestData = {};
                                                                              cumToNonCumChangeOfBondTypeService.getOtherBankDetails(requestData).then(function(response){
                                                                                   vm.loader = false;
                                                                                   vm.otherBankDetailsMicrTorchList = response.data.result.torchValue;
                                                                                   angular.element('#otherBankDetailsMicrModal').modal('toggle');
                                                                               },function(error) {
                                                                                    vm.loader = false;
                                                                                  handleErrorResponse(error);
                                                                                });

                                                                       }

                              function verifyBlaTorch(){
                                           vm.loader = true;
                                               var requestData = {
                                               "schemeCode": vm.schemeCode,
                                               };
                                              cumToNonCumChangeOfBondTypeService.getVerifySelect(requestData).then(function(response){
                                                   vm.loader = false;
                                                   vm.blaModalList = response.data.result.torchValue;
                                                   angular.element('#blaModal').modal('toggle');
                                               },function(error) {
                                                    vm.loader = false;
                                                  handleErrorResponse(error);
                                                });

                                       }





                                       function validateMultiples(amount, flag){

                                        vm.bondCashLimit = parseInt(vm.bondCashLimit);
                                        vm.bondClearingTransferLimit = parseInt(vm.bondClearingTransferLimit);
                                        vm.depositMultiples = parseInt(vm.depositMultiples);
                                        vm.minCashLimit = parseInt(vm.minCashLimit);
                                        amount = parseInt(amount);

                                       if(amount){
                                       if((amount%vm.depositMultiples)==0){
                                        vm.depositMultiplesErrMsg = ""
                                       }
                                       else{
                                        vm.depositMultiplesErrMsg = "Subscription Amount must be multiples of "+vm.depositMultiples;
                                        if(flag=='add')
                                        vm.subscriptionAmount= '';

                                        else
                                       vm.subscriptionDetailsValueModel.C012.oldValue ='';
                                       }
                                      }

                                      if(amount && vm.transactionMode){

                                          if(vm.transactionMode == 'C'){

                                                      if(amount>vm.bondCashLimit || amount<vm.minCashLimit){
                                                      vm.depositMultiplesErrMsg = "Subscription Amount must be between "+vm.minCashLimit +" and "+vm.bondCashLimit;
                                                      if(flag=='add')
                                                      vm.subscriptionAmount= '';

                                                      else
                                                      vm.subscriptionDetailsValueModel.C012.oldValue  ='';
                                                      }
                                                      else {
                                                      vm.depositMultiplesErrMsg='';
                                                      }
                                                      }
                                                      else if(vm.transactionMode == 'T' || vm.transactionMode=='CL'){
                                                      if(amount>vm.bondClearingTransferLimit){
                                                      vm.depositMultiplesErrMsg = "Subscription Amount must be between "+vm.minCashLimit +" and "+vm.bondClearingTransferLimit;

                                                      if(flag=='add')
                                                      vm.subscriptionAmount= '';

                                                      else
                                                      vm.subscriptionDetailsValueModel.C012.oldValue ='';
                                                      }
                                                      else {
                                                      vm.depositMultiplesErrMsg='';
                                                      }
                                                      }

                                      }
                                      if(!amount){
                                     vm.depositMultiplesErrMsg = "Subscription Amount must not be zero";

                                      }
                                       }

                              function verifySubscriptionTorch(){
                                                   vm.loader = true;
                                                       var requestData = {
                                                       "schemeCode": vm.schemeCode,
                                                       "blaNo": vm.blaNo,
                                                       };
                                                      cumToNonCumChangeOfBondTypeService.getVerifySelect(requestData).then(function(response){
                                                           vm.loader = false;
                                                           vm.subscriptionModalList = response.data.result.torchValue;
                                                           angular.element('#subscriptionModal').modal('toggle');
                                                       },function(error) {
                                                            vm.loader = false;
                                                          handleErrorResponse(error);
                                                        });

                                               }

                               function getAddBlaDetails(){
                                   vm.loader = true;
                                       var requestData = {
                                       "schemeCode": vm.schemeCode,
                                       "blaNo": vm.blaNo,
                                       };
                                      cumToNonCumChangeOfBondTypeService.getValidationAdd(requestData).then(function(response){

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
                                           vm.bondCashAccFlgOut=vm.accountDetails.bondCashAccFlgOut;
                                           vm.bondClearingAccFlgOut=vm.accountDetails.bondClearingAccFlgOut;
                                           vm.bondCollectionAccFlgOut=vm.accountDetails.bondCollectionAccFlgOut;
                                           vm.ddPoAccFlgOut=vm.accountDetails.ddPoAccFlgOut;
                                           vm.ddPoAccountNo=vm.accountDetails.ddPoAccountNo;
                                           vm.neftPlaceholderFlgOut=vm.accountDetails.neftPlaceholderFlgOut;
                                           vm.tranMode=vm.accountDetails.tranMode;


                                           vm.accountOpeningDate = $filter('date')(convertToDateObject($rootScope.globals.currentUser.solDate), 'dd/MM/yyyy');

                       //                    vm.accountOpeningDate =  $filter('date')($rootScope.globals.currentUser.systemDate, 'dd/MM/yyyy')
                       //
                       //                    var curDateObj = convertToDateObject($rootScope.globals.currentUser.solDate);
                       //                    curDateObj.setMonth(curDateObj.getMonth() + vm.accountDetails.periodOfDeposit);
                       //
                       //                    vm.maturityDate = $filter('date')(curDateObj, "dd/MM/yyyy");

                           var numberOfMonths = vm.periodOfDeposit; // Number of months to add
                           var currentDateTime = new Date(convertToDateObject($rootScope.globals.currentUser.solDate)); // Convert current date string to Date object
                         //  var nextDate = new Date(currentDateTime.getFullYear(), currentDateTime.getMonth() + numberOfMonths, currentDateTime.getDate());

                           var convertedDate = new Date(currentDateTime.setMonth(parseInt(currentDateTime.getMonth()) + parseInt(numberOfMonths)));
                           vm.maturityDate = $filter('date')(convertedDate, "dd/MM/yyyy");





                                           angular.forEach(vm.bondHolderDetails, function(item){

                                               if(item.jointHolderNo == 'S'){
                                                   vm.bondHolderDetailSingleBlaNo = item.blaNo;
                                                   vm.bondHolderDetailSingleBondHolderName = item.nameOfBondHolder;
                                               }
                                               if(item.jointHolderNo == '1'){
                                                   vm.bondHolderDetailFirstBlaNo = item.blaNo;
                                                   vm.bondHolderDetailFirstBondHolderName = item.nameOfBondHolder;
                                               }
                                               if(item.jointHolderNo == '2'){
                                                   vm.bondHolderDetailSecondBlaNo = item.blaNo;
                                                   vm.bondHolderDetailSecondBondHolderName = item.nameOfBondHolder;
                                               }
                                            });

                                           vm.loader = false;
                                       },function(error) {
                                            vm.loader = false;
                                          handleErrorResponse(error);
                                          $state.go('cum_to_non_cum_change_of_bond_type.add');
                                        });

                               }


                               function modeOfInterestPaymentValidation(value){

                               vm.operativeAccNum = '';
                               vm.cbsErrorMessage = '';
                               vm.operativeAccBranchId = '';
                               vm.bankCode = '';
                               vm.branchCode = '';
                               vm.creditBankIfscCode = '';
                               vm.nameOfthePayee = '';
                               vm.creditAccNo = '';

                                   if(value == 'MP002'){
                                       vm.ddpoAccountNo = vm.accountDetails.ddPoAccountNo;
                                   }
                               }

                                     function modeOfInterestPaymentValidationM(value){



                                           vm.getDetails.C023 ? vm.getDetails.C023.oldValue = '' : "";
                                           vm.cbsErrorMessage = '';
                                           vm.getDetails.C024 ? vm.getDetails.C024.oldValue = '' : "";
                                           vm.getDetails.C026 ? vm.getDetails.C026.oldValue = '' : "";
                                           vm.getDetails.C027 ? vm.getDetails.C027.oldValue = '' : "";
                                           vm.getDetails.C028 ? vm.getDetails.C028.oldValue = '' : "";
                                           vm.getDetails.C029 ? vm.getDetails.C029.oldValue = '' : "";
                                           vm.getDetails.C030 ? vm.getDetails.C030.oldValue = '' : "";

                                           if(value == 'MP002'){
                                               vm.getDetails.C025 = {};
                                               vm.getDetails.C025.oldValue = parseInt(vm.ddPoAccountNo);
                                           }
                                           if(value == 'MP003'){
                                               vm.getDetails.C030 = {};
                                               vm.getDetails.C030.oldValue = parseInt(vm.neftCreditAccNo);
                                           }
                                       }

                                  function getApplicationNoTorch(){
                                           vm.loader = true;
                                               var requestData = {
                                               "schemeCode": vm.schemeCode,
                       //                        "applicationNo": vm.blaNo,
                                               };
                                              cumToNonCumChangeOfBondTypeService.getApplicationTorch(requestData).then(function(response){
                                                   vm.loader = false;
                                                   vm.applicationDetailsList = response.data.result.torchValue;
                                                   angular.element('#applicationModal').modal('toggle');
                                               },function(error) {
                                                    vm.loader = false;
                                                  handleErrorResponse(error);
                                                });

                                       }

                                      function getApplicationDetails(value){
                                               vm.loader = true;
                                                var requestData = {
                                                                "schemeCode": vm.schemeCode,
                                                                "applicationNo": vm.applicationNo,
                                                                };
                                                                if(value == 'M'){
                                                                   requestData.applicationNo = vm.getDetails.C011.oldValue;
                                                                }
                                                  cumToNonCumChangeOfBondTypeService.getApplicationTorch(requestData).then(function(response){
                                                       vm.loader = false;
                                                       vm.applicationDetails = response.data.result;
                                                       vm.applicationDate = vm.applicationDetails.torchValue[0].applicationDate;

                                                       vm.subscriptionAmount = vm.applicationDetails.torchValue[0].subscriptionAmount;
                                                       vm.debitAccNo = vm.applicationDetails.torchValue[0].debitAccNo;

                                                       vm.transactionMode = vm.applicationDetails.torchValue[0].tranMode;

                                                       if(value == 'M'){
                                                           vm.getDetails.C012.oldValue = vm.applicationDetails.torchValue[0].applicationDate;
                                                           vm.getDetails.C014.oldValue = vm.applicationDetails.torchValue[0].subscriptionAmount;
                                                           vm.getDetails.C032.oldValue = vm.applicationDetails.torchValue[0].debitAccNo;
                                                           vm.getDetails.C031.oldValue = vm.applicationDetails.torchValue[0].tranMode;
                                                       }

                                                   },function(error) {
                                                        vm.loader = false;
                                                      handleErrorResponse(error);
                                                    });

                                           }


                                          function getBrokerTorch(){
                                                   vm.loader = true;
                                                    var requestData = {};

                                                      cumToNonCumChangeOfBondTypeService.getBrokerTorch(requestData).then(function(response){
                                                           vm.loader = false;
                                                           vm.brokerDetailsList = response.data.result.torchValue;
                                                           angular.element('#brokerDetailsModal').modal('toggle');
                                                       },function(error) {
                                                            vm.loader = false;
                                                          handleErrorResponse(error);
                                                        });

                                               }

                               vm.mofDateOptions = {
                                   showWeeks : false,
                                   maxDate : vm.systemDate
                               }

                           	// Clear not required fields
                           	function updateTransactionMode(){
                           		if(vm.accountOpening.tranMode == 'C'){
                           			vm.accountOpening.acctName = '';
                           			vm.accountOpening.interestPaymentMode = '';
                           			vm.accountOpening.interestCreditAccountNumber = '';
                           			vm.accountOpening.zoneBankCode = '';
                           			vm.accountOpening.zoneBranchCode = '';
                           			vm.accountOpening.codeOfCreditBank = '';
                           			vm.accountOpening.payeeName = '';
                           			vm.accountOpening.operativeAccNo = '';
                           			vm.accountOpening.instType = '';
                           			vm.accountOpening.instAlpha = '';
                           			vm.accountOpening.instNumber = '';
                           			vm.accountOpening.instDate = '';
                           		} else if(vm.accountOpening.tranMode == 'T'){
                           			vm.accountOpening.tranCode = '';
                           			vm.accountOpening.zoneCode = '';
                           			vm.accountOpening.zoneDate = '';
                           			vm.accountOpening.bankCode = '';
                           			vm.accountOpening.branchCode = '';
                           			vm.accountOpening.sortCode = '';
                           			vm.accountOpening.interestPaymentMode = '';
                           			vm.accountOpening.interestCreditAccountNumber = '';
                           			vm.accountOpening.zoneBankCode = '';
                           			vm.accountOpening.zoneBranchCode = '';
                           			vm.accountOpening.codeOfCreditBank = '';
                           			vm.accountOpening.payeeName = '';
                           			vm.accountOpening.operativeAccNo = '';
                           			vm.accountOpening.instType = '';
                           			vm.accountOpening.instAlpha = '';
                           			vm.accountOpening.instNumber = '';
                           			vm.accountOpening.instDate = '';
                           		} else if(vm.accountOpening.tranMode == 'CL'){
                           			vm.accountOpening.solId = '';
                           			vm.accountOpening.acctName = '';
                           			vm.accountOpening.instType = '';
                           			vm.accountOpening.instAlpha = '';
                           			vm.accountOpening.instNumber = '';
                           			vm.accountOpening.instDate = '';
                           			vm.accountOpening.interestPaymentMode = '';
                           			vm.accountOpening.interestCreditAccountNumber = '';
                           			vm.accountOpening.zoneBankCode = '';
                           			vm.accountOpening.zoneBranchCode = '';
                           			vm.accountOpening.codeOfCreditBank = '';
                           			vm.accountOpening.payeeName = '';
                           			vm.accountOpening.operativeAccNo = '';
                           		}
                           	}

                               //Get Customer ID
                               function getCustomerId() {
                               vm.loader = true;
                               var requestData = {};
                                  cumToNonCumChangeOfBondTypeService.getValidationAdd(requestData).then(function(response){
                                  vm.loader = false;
                                     angular.element('#customerIdModal').modal('toggle');
                                     vm.customerIdList = response.data.result.customerIdOut;
                                   },function(error) {
                                   vm.loader = false;
                                      handleErrorResponse(error);
                                    });
                               }

                               //Get validate Add Select
                               function validateAddSelect() {
                               var requestData = {
                                'scssCustomerId' : vm.accountOpening.customerId
                               };
                                  cumToNonCumChangeOfBondTypeService.getValidationAdd(requestData).then(function(response){
                                     vm.customerMasterList = response.data.result.customerMasterOut;
                                     vm.totalAmount = response.data.result.totalAmount;
                                     angular.forEach(vm.customerMasterList, function(item){
                                         if(item.accountHolderType == 'MH001'){
                                            vm.accountOpening.customerId = item.scssCustomerId;
                                            vm.accountOpening.accountHolderType = item.accountHolderType;
                                            vm.customerName = item.name;
                                             vm.accountOpening.holderNum=item.accountHolderNumber;
                                         }

                                         if(item.accountHolderType == 'MH002'){
                                            if(item.accountHolderNumber == '1'){
                                            vm.accountOpening.holderNum=item.accountHolderNumber;
                                            vm.accountOpening.customerId = item.scssCustomerId;
                                            vm.accountOpening.accountHolderType = item.accountHolderType;
                                            vm.primaryCustomerName = item.name;
                                            }else if(item.accountHolderNumber == '2'){
                                            vm.secondaryHolderName = item.name;
                                            vm.accountOpening.accountHolderType = item.accountHolderType;
                                            //vm.accountOpening.holderNum=item.accountHolderNumber;
                                            }

                                         }

                                     })

                                   },function(error) {
                                      handleErrorResponse(error);
                                    });
                               }

                               //Get Transaction Modes
                               function getTransactionModes() {
                                  vm.referenceId = 'TRANSACTION_TYPE';
                                  var requestData = {
                                        'referenceId': 'RBI_TRAN_MODES',
                                    }
                                  cumToNonCumChangeOfBondTypeService.getReferenceData(requestData).then(function(response){
                                     vm.transactionModeList = response.data.result;
                                   },function(error) {
                                      handleErrorResponse(error);
                                    });
                               }

                               function validateInstNum(instNum) {

                                 if(vm.transactionMode == 'CL'){
                                    vm.insErrorMessage = '';
                                  }

                               if(instNum){
                                       if(vm.transactionMode == 'T'){
                       //                            			console.log(vm.instrumentNo);
                       //                            					if(vm.instrumentNo.length >= 6){
                       //                            					vm.insErrorMessage = false;
                       //                            					} else {
                       //                                                    var zeros = "0".repeat(6);
                       //                                                    vm.instrumentNo = (zeros + vm.model.instNumber).slice(-1 * 6)
                       //                            					}
                                                   			commonService.validateInstNum(instNum, vm.debitAccNo, function (response) {
                                                                       					if (response.success) {
                                                                       						if (response.data.instrumentStatus != 'S') {
                                                                       							vm.instrumentNo = '';
                                                                       							vm.insErrorMessage = "Enter valid instrument Number";
                                                                       						} else {
                                                                       							vm.insErrorMessage = false;
                                                                       						}
                                                                       					} else {
                                                                       						$scope.$emit('showAlert', {
                                                                       							'type': 'false',
                                                                       							'msg': response.message
                                                                       						});
                                                                       					}
                                                                       				});
                                       }


                       }
                               		}


                               //Get Transaction Modes
                               function getModeOfInterestPayment() {
                                  var requestData = {
                                        'referenceId': 'MODE_OF_PAYMENT'
                                    }
                                  cumToNonCumChangeOfBondTypeService.getReferenceData(requestData).then(function(response){
                                     vm.modeOfInterestPaymentList = response.data.result;
                                   },function(error) {
                                      handleErrorResponse(error);
                                    });
                               }




                               function getInterestPaymentMode() {
                                  var requestData = {
                                        'referenceId': 'INT_PAY_MODE'
                                    }
                                  cumToNonCumChangeOfBondTypeService.getReferenceData(requestData).then(function(response){
                                     vm.interestPaymentModeList = response.data.result;
                                   },function(error) {
                                      handleErrorResponse(error);
                                    });
                               }

                               function getRelationWithHolder() {
                                  var requestData = {
                                        'referenceId': 'RELATIONSHIP'
                                    }
                                  cumToNonCumChangeOfBondTypeService.getReferenceData(requestData).then(function(response){
                                     vm.relationHolderList = response.data.result;
                                   },function(error) {
                                      handleErrorResponse(error);
                                    });
                               }

                               function getStateList() {
                                  var requestData = {
                                        'referenceId': 'STATES OF INDIA'
                                    }
                                  cumToNonCumChangeOfBondTypeService.getReferenceData(requestData).then(function(response){
                                     vm.stateList = response.data.result;
                                   },function(error) {
                                      handleErrorResponse(error);
                                    });
                               }
                               function instrumentTypeForClearing() {
                                  var requestData = {
                                        'referenceId': 'INSTRUMENT_TYPE'
                                    }
                                  cumToNonCumChangeOfBondTypeService.getReferenceData(requestData).then(function(response){
                                     vm.instrumentTypeClearingList = response.data.result;
                                   },function(error) {
                                      handleErrorResponse(error);
                                    });
                               }



                               /* Get Sol Details */
                               function getSolDetails(solId, scopeVariable){
                                   solId = '';
                                   vm.tempSolIDScopeVariable = scopeVariable;
                                   vm.searchSolId = '';
                                   vm.loader = true;
                                   commonService.getSolAllDetails(solId, function(response){
                                       if(response.success) {
                                     angular.element('#solIDModal').modal('toggle');
                                           vm.solDetails = response.data;
                                           vm.loader = false;
                                       } else {
                                           Swal.fire("Error!", response.message, "error");
                                           vm.loader = false;
                                       }
                                   });
                               }
                                   function getTranCode(tranCode){
                                           if(!tranCode){
                                               tranCode = '';
                                           }
                                           vm.loader = true;
                                           commonService.getTranCode(tranCode,function(response){
                                               if(response.success) {
                                                 angular.element('#tranCodeModal').modal('toggle');
                                                   vm.tranCodeList = response.data;
                                                   vm.loader = false;
                                               } else {
                                                   Swal.fire("Error!", response.message, "error");
                                                   vm.loader = false;
                                               }
                                           });
                                       }
                           	function getZoneCode(solId,zoneCode){
                           		if(!zoneCode){
                           			zoneCode = '';
                           		}
                           		vm.loader = true;
                           		commonService.getZoneCode(solId,zoneCode,function(response){
                           			if(response.success) {
                                     angular.element('#zoneModal').modal('toggle');
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
                           	function getBankCode(bankCode){
                           		if(!bankCode){
                           			bankCode = '';
                           		}
                           		vm.loader = true;
                           		commonService.getBankCode(bankCode,function(response){
                           			if(response.success) {
                                     angular.element('#bankCodeModal').modal('toggle');
                           				vm.bankCodeList = response.data;
                           				vm.loader = false;
                           			} else {
                           				Swal.fire("Error!", response.message, "error");
                           				vm.loader = false;
                           			}
                                   });
                           	}
                           	function getBranchCode(bankCode, branchCode){
                           		if(!bankCode){
                           			Swal.fire('Please select Bank Code');
                           		} else {
                           			if(!branchCode){
                               			branchCode = '';
                               		}
                           			vm.loader = true;
                           			commonService.getBranchCode(bankCode, branchCode,function(response){
                               			if(response.success) {
                                               angular.element('#branchCodeModal').modal('toggle');
                               				vm.branchCodeList = response.data;
                               				vm.loader = false;
                               			} else {
                               				Swal.fire("Error!", response.message, "error");
                               				vm.loader = false;
                               			}
                                       });
                           		}
                           	}

                           	function getZoneBankCode(bankCode){
                           		if(!bankCode){
                           			bankCode = '';
                           		}
                           		vm.loader = true;
                           		commonService.getBankCode(bankCode,function(response){
                           			if(response.success) {
                                     angular.element('#zoneBankCodeModal').modal('toggle');
                           				vm.bankCodeList = response.data;
                           				vm.loader = false;
                           			} else {
                           				Swal.fire("Error!", response.message, "error");
                           				vm.loader = false;
                           			}
                                   });
                           	}
                           	function getZoneBranchCode(bankCode, branchCode){
                           		if(!bankCode){
                           			Swal.fire('Please select Bank Code');
                           		} else {
                           			if(!branchCode){
                               			branchCode = '';
                               		}
                           			vm.loader = true;
                           			commonService.getBranchCode(bankCode, branchCode,function(response){
                               			if(response.success) {
                                               angular.element('#zoneBranchCodeModal').modal('toggle');
                               				vm.branchCodeList = response.data;
                               				vm.loader = false;
                               			} else {
                               				Swal.fire("Error!", response.message, "error");
                               				vm.loader = false;
                               			}
                                       });
                           		}
                           	}

                           	function getTranCodeDetails(){
                           	    vm.tranCodeList = [];
                           	    vm.loader = true;
                           		commonService.getTranCode('',function(response){
                           				vm.tranCodeList = response.data;
                           				vm.loader = false;
                                           angular.element('#tranCodeModal').modal('toggle');
                                   });
                           	}
                           	function getBankCodeDetails(){
                           	vm.bankCodeList = [];
                           		commonService.getBankCode('',function(response){
                           				vm.bankCodeList = response.data;
                                   });
                           	}
                           	function getZoneCodeDetails(){
                           	vm.zoneCodeList = [];
                           		commonService.getZoneCode(vm.loginSolId,'',function(response){
                           				vm.zoneCodeList = response.data;
                                   });
                           	}


                           	function getInstrumentType (){
                           		if (vm.accountOpening.acctNum!='' && vm.accountOpening.interSolId!=''){
                               		vm.instrumentTypeList = '';

                       	    		commonService.getInstrumentType(vm.accountOpening.acctNum,vm.accountOpening.interSolId,function(response){
                       	    			if(response.success) {
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
                                   if(vm.nominationDetails.length > 1){
                                       vm.nominationDetails.splice(index,1);
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

                                function calculatePriorDate(priorMonths){
                                         	const date = new Date();

                                         	// accessing current month of the date
                                         	const currentMonth = date.getMonth();

                                         	// subtracting required number of months
                                         	date.setMonth(currentMonth - priorMonths);

                                         	return date;
                                }

                       //        function validateInstNum(){
                       //                    if(vm.accountOpening.tranMode == 'T'){
                       //                        commonService.validateInstNum(vm.accountOpening.instNumber, vm.accountOpening.acctNum,function(response){
                       //                            if(response.success) {
                       //                                if(response.data.instrumentStatus != 'S'){
                       //                                    vm.accountOpening.instNumber = '';
                       //                                    vm.errorMsgForInstrumentNumber = true;
                       //                                }else {
                       //                                    vm.errorMsgForInstrumentNumber = false;
                       //                                }
                       //                            } else {
                       //                                $scope.$emit('showAlert', { 'type': 'false', 'msg': response.message });
                       //                            }
                       //                        });
                       //                    }
                       //        }



                       /*     function getnominationDOB(){
                            var timestamp2 = new Date(formatDate(vm.nominationDetails.dob)).getTime();
                            var timestamp1 = new Date(vm.solDate).getTime();
                            var diff = timestamp1 - timestamp2
                            var newDate=diff/(1000 * 60 * 60  * 24 * 365)
                            var dateInt = parseInt(newDate);
                            //var newDate = new Date (diff);
                               if(dateInt>=18){
                               vm.minorDate=false;
                               }
                               else
                                vm.minorDate=true;

                            }*/

                               function calculateAge(birthday) { // birthday is a date
                                   var ageDifMs = Date.now() - birthday.getTime();
                                   var ageDate = new Date(ageDifMs); // miliseconds from epoch
                                   var age = Math.abs(ageDate.getUTCFullYear() - 1970);

                                   if(age >= 18){
                                       var msg = "Not a minor"
                                       Swal.fire("Warning!", msg, "warning");
                                       getAccountPage();
                                   }
                              }

                           function dateValidation() {
                                       vm.dateOptions = {
                                           maxDate: vm.systemDate
                                       }
                                   }
                                   function dateOfBirthOptionsFunc() {
                                       vm.curtYear = vm.currentDate.getUTCFullYear();
                                       vm.minimumYear = vm.curtYear - 18;
                                       vm.minimumDate = (vm.currentDate.getMonth()+ 1) + '/' + (vm.currentDate.getDate()) + '/' + (vm.minimumYear);
                                       vm.minimumDate = new Date(vm.minimumDate);
                                       vm.dateOfBirthOptions = {
                                           minDate : vm.minimumDate,
                                           maxDate: vm.systemDate
                                       }
                                   }

                       		function instrumentDateValidation(){
                       		commonService.getSolDate()
                               .then(
                                   function(response) {
                                       vm.solDate = response.data.solDate;
                                        vm.effFromDateOptions = {
                                                 minDate : moment(vm.solDate).subtract(3,'M').toDate(),
                                                 maxDate : vm.solDate
                                                 }
                                   }, function(error) {
                                       handleErrorResponse(error);
                                   }
                               );
                       		}

                               function addRange() {
                                       vm.nominationDetails.push({});
                               }


                           	// Remove Nominee
                           	function removeNominee(index)
                           	{
                           		if(vm.nominationDetails.length > 1){
                           			vm.nominationDetails.splice(index,1);
                           		} else {
                           			Swal.fire("You will not allowed to delete first nominee");
                           			return false;
                           		}

                           	}

                       		function getSubCodeList(){

                       //            cumToNonCumChangeOfBondTypeService.getSubCodeList().then(function(response){
                       //                vm.subCodeList= response.data.result;
                       //            },function(error){
                       //                handleErrorResponse(error);
                       //            })
                       		}

                               //Get Operative Account Number
                               function getOperativeCreditAccount() {
                               var requestData = {
                                'scssCustomerId' : vm.accountOpening.customerId,
                                'transactionType' : vm.accountOpening.tranMode
                               };
                                  cumToNonCumChangeOfBondTypeService.getValidationAdd(requestData).then(function(response){
                                     vm.accountNumberDetails = response.data.result;
                                     vm.maximumCashLimit = parseInt(vm.accountNumberDetails.cashLimitOut);
                                     if(vm.maximumCashLimit){
                                        getCashValidation();
                                     }

                                     if(vm.accountNumberDetails.debitAccountNumberFlag == 'N'){
                               		cumToNonCumChangeOfBondTypeService.getForacidAccountNumber(vm.accountNumberDetails.debitAccountNumber,vm.loginSolId).then(function(response){
                               		   vm.accountOpening.acctNum = response.data[0].forAccountId;
                                          if(vm.accountOpening.acctNum && vm.accountOpening.tranMode == 'T'){
                                               getSchemeTypeListOperative('', vm.accountOpening.acctNum, 'acctNum');
                                          }
                               		}, function(error) {
                                          vm.loader = false;
                                             handleErrorResponse(error);
                                           });
                                     }else {
                                          vm.accountOpening.acctNum = vm.accountNumberDetails.debitAccountNumber;
                                          if(vm.accountOpening.acctNum && vm.accountOpening.tranMode == 'T'){
                                          getSchemeTypeListOperative('', vm.accountOpening.acctNum, 'acctNum');
                                          }
                                     }
                                     if(vm.accountNumberDetails.creditAccountNumberFlag == 'N'){
                               		cumToNonCumChangeOfBondTypeService.getForacidAccountNumber(vm.accountNumberDetails.creditAccountNumber,vm.loginSolId).then(function(response){
                               		   vm.accountOpening.creditAccountNumber = response.data[0].forAccountId;
                               		}, function(error) {
                                                      vm.loader = false;
                                                         handleErrorResponse(error);
                                                       });
                                     }else {
                                          vm.accountOpening.creditAccountNumber = vm.accountNumberDetails.creditAccountNumber;
                                     }
                                     if(vm.accountNumberDetails.ddPayAccountNumberFlag == 'N'){
                               		cumToNonCumChangeOfBondTypeService.getForacidAccountNumber(vm.accountNumberDetails.ddPayAccountNumber,vm.loginSolId).then(function(response){
                               		   vm.accountOpening.ddPoAccountNumber = response.data[0].forAccountId;
                               		}, function(error) {
                                                      vm.loader = false;
                                                         handleErrorResponse(error);
                                                       });
                                     }else {
                                          vm.accountOpening.ddPoAccountNumber = vm.accountNumberDetails.ddPayAccountNumber;
                                     }
                                   },function(error) {
                                   vm.loader = false;
                                      handleErrorResponse(error);
                                    });
                               }

                               function getDDPOAccountNumberForModify() {
                               var requestData = {
                                'scssCustomerId' : vm.accountOpening.customerId,
                                'transactionType' : vm.accountOpening.tranMode
                               };
                                  cumToNonCumChangeOfBondTypeService.getValidationAdd(requestData).then(function(response){
                                     vm.accountNumberDetails = response.data.result;

                                     if(vm.accountNumberDetails.creditAccountNumberFlag == 'N'){
                               		cumToNonCumChangeOfBondTypeService.getForacidAccountNumber(vm.accountNumberDetails.creditAccountNumber,vm.loginSolId).then(function(response){
                               		   vm.accountOpening.creditAccountNumber = response.data[0].forAccountId;
                               		}, function(error) {
                                                      vm.loader = false;
                                                         handleErrorResponse(error);
                                                       });
                                     }else {
                                          vm.accountOpening.creditAccountNumber = vm.accountNumberDetails.creditAccountNumber;
                                     }
                                     if(vm.accountNumberDetails.ddPayAccountNumberFlag == 'N'){
                               		cumToNonCumChangeOfBondTypeService.getForacidAccountNumber(vm.accountNumberDetails.ddPayAccountNumber,vm.loginSolId).then(function(response){
                               		   vm.accountOpening.ddPoAccountNumber = response.data[0].forAccountId;
                               		}, function(error) {
                                                      vm.loader = false;
                                                         handleErrorResponse(error);
                                                       });
                                     }else {
                                          vm.accountOpening.ddPoAccountNumber = vm.accountNumberDetails.ddPayAccountNumber;
                                     }
                                   },function(error) {
                                   vm.loader = false;
                                      handleErrorResponse(error);
                                    });
                               }

                               //Get depositAmountValidation
                               function depositAmountValidation() {
                                   if(vm.accountOpening.depositAmount){
                                       var requestData = {
                                        'scssCustomerId' : vm.accountOpening.customerId,
                                        'depositAmount' : vm.accountOpening.depositAmount
                                       };
                                       cumToNonCumChangeOfBondTypeService.getDepositAmountValidation(requestData).then(function(response){
                                         vm.accountOpening.totalAmount = parseInt(response.data.result);
                                         vm.accountOpening.totalAmountInquiry = vm.accountOpening.totalAmount - vm.accountOpening.depositAmount;
                                         vm.errorMessage = false;
                                       },function(error) {
                                           if(error.data.message != 'SUCCESS'){
                                           vm.errorMessage = error.data.message;
                                           vm.accountOpening.depositAmount = '';
                                           //vm.accountOpening.totalAmount = '';
                                           }
                                       });
                                   }
                               }

                       	//Operative Acct Num
                           function getSchemeTypeListOperative (solId, acctNum, flag){
                               if(acctNum){
                               commonService.getSchemeTypeListAcc(acctNum, function(response){
                                   if(response.success) {
                                       vm.accList = response.data;
                                       if(flag=='acctNum'){
                                       if(vm.accList.length>0)
                                       {
                                       vm.accountOpening.acctNum = vm.accList[0].forAccountId;
                                       vm.accountOpening.interSolId = vm.accList[0].solId;
                                       if(vm.accountOpening.acctNum && vm.accountOpening.interSolId){
                                       getInstrumentType();
                                       }
                                       vm.errorAccountNumber = false;
                                       }else
                                       {
                                       vm.errorAccountNumber = true;
                                       vm.accountOpening.acctNum='';
                                       }
                                       }
                       /*                else if(flag=='nomineeOperativeAcctNo'){
                                       if(vm.accList.length>0)
                                       {
                                       angular.forEach(vm.nominationDetails, function(item){
                                        item.C015 = vm.accList[0].forAccountId;
                                       })
                                       vm.errorNomineeOperativeAcctNo = false;
                                       }else
                                       {
                                       vm.errorNomineeOperativeAcctNo = true;
                                       angular.forEach(vm.nominationDetails, function(item){
                                        item.C015 = '';
                                       })
                                       }
                                       }*/
                                       else if(flag=='creditAccountNumber'){
                                       if(vm.accList.length>0)
                                       {
                                       vm.accountOpening.interestCreditAccountNumber = vm.accList[0].forAccountId;
                                       vm.errorCreditAccountNumber = false;
                                       }else
                                       {
                                       vm.errorCreditAccountNumber = true;
                                       vm.accountOpening.interestCreditAccountNumber='';
                                       }
                                       }
                                   } else {
                                       Swal.fire("Error!", response.message, "error");
                                               }
                               }, function(err) {
                                        handleErrorResponse(err);
                                });
                               }
                           }


//                                      function getGuardianDetailsMetaData() {
//                                          var requestData = {
//                                                'referenceId': 'RELATION_WITH_MINOR'
//                                            }
//                                          cumToNonCumChangeOfBondTypeService.getReferenceData(requestData).then(function(response){
//                                             vm.guardianDetailsList = response.data.result;
//                                           },function(error) {
//                                              handleErrorResponse(error);
//                                            });
//                                       }

                           function getSchemeTypeListOperativeNominee (solId, acctNum, flag, index){
                                   vm.index = index;
                                   vm.errorNomineeOperativeAcctNo[vm.index] = '';
                                   commonService.getCustomAcctInqApi(acctNum, function (response) {
                                                                       if (response.success) {
                                                                           vm.accList = response.data;
                                                                           vm.loader = false;
                                                                           if (vm.accList.length > 0) {
                                                                           if (vm.accList[0].status == 'S') {


                                                                               if(vm.accList[0].accountFreezeStatus) {
                                                                                 vm.errorNomineeOperativeAcctNo[vm.index] = 'Account is Friezed';
                                                                                 vm.nominationDetails[vm.index].nomineeOperativeAcctNo = '';
                                                                                 vm.nominationDetails[vm.index].operativeAccBranchId = '';
                                                                               }else{
                                                                                   vm.nominationDetails[vm.index].operativeAccBranchId = vm.accList[0].solID;
                                                                               }


                                                                               }
                                                                               if (vm.accList[0].status == 'F') {
                                                                                   vm.errorNomineeOperativeAcctNo[vm.index] = vm.accList[0].errDesc;
                                                                                   vm.nominationDetails[vm.index].nomineeOperativeAcctNo = '';
                                                                                 vm.nominationDetails[vm.index].operativeAccBranchId = '';
                                                                               }
                                                                           }
                                                                           else {
                                                                               vm.errorNomineeOperativeAcctNo[vm.index] = 'Invalid Account Number';
                                                                               vm.nominationDetails[vm.index].nomineeOperativeAcctNo = '';
                                                                             vm.nominationDetails[vm.index].operativeAccBranchId = '';
                                                                           }
                                                                       } else {
                                                                           Swal.fire("Error!", response.message, "error");
                                                                           vm.loader = false;
                                                                           vm.nominationDetails[vm.index].nomineeOperativeAcctNo = '';
                                                                         vm.nominationDetails[vm.index].operativeAccBranchId = '';
                                                                       }
                                                                   }
                                                                   , function (err) {
                                                                       handleErrorResponse(err);
                                                                       vm.loader = false;
                                                                       vm.nominationDetails[vm.index].nomineeOperativeAcctNo = '';
                                                                     vm.nominationDetails[vm.index].operativeAccBranchId = '';
                                                                   });
                           }

                               function getSchemeTypeListOperativeNomineeM(solId, acctNum, flag, index){
                               vm.index = index;
                                   if(acctNum){

                                          commonService.getCustomAcctInqApi(acctNum, function (response){

                                                      if(response.success) {
                                                          vm.accList = response.data;

                                                          if(vm.accList.length>0){
                                                          vm.errorNomineeOperativeAcctNo[vm.index] = false;
                                                          vm.nominationDetails[vm.index].C014.oldValue = vm.accList[0].solID;

                                                          }else{
                                                          vm.errorNomineeOperativeAcctNo[vm.index] = true;
                                                              for(var i=0; i<vm.nominationDetails.length; i++){
                                                                  if(vm.index == i ){
                                                                  vm.nominationDetails[vm.index].C013.oldValue = '';
                                                              break;
                                                              }
                                                              }
                                                          }

                                                      } else {
                                                          Swal.fire("Error!", response.message, "error");
                                                                  }



                           //            					if (response.success) {
                           //            						vm.accList = response.data;
                           //            						vm.loader = false;
                           //            						if (vm.accList.length > 0) {
                           //            						if (vm.accList[0].status == 'S') {
                           //
                           //
                           //                                        if(vm.accList[0].accountFreezeStatus) {
                           //                                          vm.cbsErrorMessage = 'Account is Friezed';
                           //                                        }else{
                           //                                            vm.customerMasterDetails.operativeAccBranchId = vm.accList[0].solID;
                           //                                        }
                           //
                           //
                           //            							}
                           //            							if (vm.accList[0].status == 'F') {
                           //            								vm.cbsErrorMessage = vm.accList[0].errDesc;
                           //            							}
                           //            						}
                           //            						else {
                           //                                        vm.cbsErrorMessage = 'Invalid Account Number';
                           //            						}
                           //            					} else {
                           //            						Swal.fire("Error!", response.message, "error");
                           //            						vm.loader = false;
                           //            					}
                                       				}, function (err) {
                                       					handleErrorResponse(err);
                                       					vm.loader = false;
                                       				});


                           //        commonService.getSchemeTypeListAcc(acctNum.toUpperCase(), function(response){
                           //
                           //        },
                           //        function(err) {
                           //                 handleErrorResponse(err);
                           //         });

                                   }
                               }


                                function validateSortNum(){
                                       if(vm.accountOpening.tranMode == 'CL'){
                                           commonService.validatesortNum(vm.accountOpening.sortCode, function(response){
                                               if(response.success) {
                                                    vm.validateMsg = false;
                                               } else {
                                                   vm.validateMsg = response.data.message;
                                                   vm.accountOpening.sortCode = '';
                                               }
                                           });
                                       }
                                }

                               function calculateSum(){
                                   vm.total = 0;
                                   for(var i = 0; i < vm.nominationDetails.length; i++){

                                       vm.total = vm.total + parseFloat(vm.nominationDetails[i].sharePercentage ? vm.nominationDetails[i].sharePercentage : '0');

                                   }
                               }

                               function calculateSumM(){
                                   vm.total = 0;
                                   for(var i = 0; i < vm.nominationDetails.length; i++){

                                       vm.total = vm.total + parseFloat(vm.nominationDetails[i].C012.oldValue ? vm.nominationDetails[i].C012.oldValue : '0');

                                   }
                               }

                               function validateNominee(){
                       //            if(vm.isNominee == 'N'){
                                       vm.nominationDetails = [{}];
                       //            }
                               }

                               function validateSubscriptionAmountM(){
                                   if(vm.getDetails.C014.oldValue == vm.getDetails.C037.oldValue){

                                   }else{
                                     vm.getDetails.C037.oldValue = '';
                                     Swal.fire('Warning!', 'Deposit Amount and Instrument Amount Differs', 'warning');
                                   }
                               }

                                       function validateSubscriptionAmount(){
                                           if(vm.instrumentAmount == vm.subscriptionAmount){

                                           }else{
                                             vm.instrumentAmount = '';
                                             Swal.fire('Warning!', 'Deposit Amount and Instrument Amount Differs', 'warning');
                                           }
                                       }




                               function submitAddForm() {
                               var proceed = false;
                               var message = '';

                              vm.getCbsDetails();
                              if(vm.getCbsDetails)
                               if(vm.isNominee == 'Y'){
                                   if(vm.total != 100){
                                       proceed = false;
                                       message = 'Sum of sharing percentage should be equal to 100';
                                   }else{
                                       proceed = true;
                                   }
                               }
                               else{
                                   proceed = true;
                               }


                               if(proceed){
//
//                                    Swal.fire({
//                                                       title : "Are you sure?",
//                                                       text : "Do you want to Really Add this record?",
//                                                       type : "warning",
//                                                       showCancelButton : true,
//                                                       cancelButtonClass : "btn-cancel",
//                                                       confirmButtonClass : "btn-primary",
//                                                       confirmButtonText : "Yes",
//                                                       closeOnConfirm : false
//                                                   },
//                                   function() {







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
                                               'schemeCode' :  vm.schemeCode,
                                               'modeOfHolding' :  vm.bondHolderDetails[0].modeOfHoldingKeyword,
                                               'applicationNo' :  vm.applicationNo,
                                               'brokerAgencyCode' :  vm.brokerId,
                                               'subscriptionAmount' :  vm.subscriptionAmount,
                                               'bondType' :  vm.bondType,
                                               'accountOpenDate' :  $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.accountOpeningDate)),
                                               'valueDate' :  $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.accountOpeningDate)),
                                               'periodOfDeposit' :  vm.periodOfDeposit,
                                               'maturityDate' :  $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.maturityDate)),
                                               'rateOfInterest' :  vm.rateOfInterest,
                                               'modeOfInterestPayment' :  vm.modeOfInterestPayment,
                                               'tranMode' :  vm.transactionMode,
                                               'instrumentAmount' :  vm.instrumentAmount,
                                               'remarks' :  vm.remarks,
                                               'isNominee' :  vm.isNominee,
                                               'totalSharingPercent' :  vm.total,
                                               'isNri' : vm.bondHolderDetails[0].isNri,
                                               'applicationDate' :  $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.applicationDate)),
                                           };


                                           if(vm.bondHolderDetails[0].modeOfHoldingKeyword == 'MH001' || vm.bondHolderDetails[0].modeOfHoldingKeyword == 'MH004' || vm.bondHolderDetails[0].modeOfHoldingKeyword == 'MH005'){
                                               requestData.singleBondAccNo = vm.bondHolderDetailSingleBlaNo;
                                               requestData.bondHolderName = vm.bondHolderDetailSingleBondHolderName;
                                           }
                                           if(vm.bondHolderDetails[0].modeOfHoldingKeyword == 'MH002' || vm.bondHolderDetails[0].modeOfHoldingKeyword == 'MH003'){
                                               requestData.firstJointHolderBlaNo = vm.bondHolderDetailFirstBlaNo;
                                               requestData.firstJointHolderName = vm.bondHolderDetailFirstBondHolderName;
                                               if(vm.bondHolderDetails[1]) {
                                                   requestData.secondJointHolderBlaNo = vm.bondHolderDetailSecondBlaNo;
                                                   requestData.secondJointHolderName = vm.bondHolderDetailSecondBondHolderName;
                                               }
                                           }

                                           if(vm.modeOfInterestPayment == 'MP001'){
                                                   requestData.operativeAccNo = vm.operativeAccNum;
                                                   requestData.operativeAccBranchId = vm.operativeAccBranchId;
                                           }
                                           if(vm.modeOfInterestPayment == 'MP002'){
                                               requestData.ddPoAccNo = vm.ddpoAccountNo;
                                           }
                                           if(vm.modeOfInterestPayment == 'MP003'){
                                               requestData.bankCode = vm.bankCode;
                                               requestData.branchCode = vm.branchCode;
                                               requestData.ifscCodeOfCreditBank = vm.creditBankIfscCode;
                                               requestData.nameOfThePayee = vm.nameOfthePayee;
                                               requestData.neftCreditAccNo = vm.creditAccNo;
                                           }

                                           if(vm.transactionMode == 'T'){
                                               requestData.branchid = vm.debitBranchId;
                                               requestData.instrumentType = vm.instrumentType;
                                               requestData.instrumentNo = vm.instrumentNo;
                                               requestData.instrumentDate = $filter('date')(vm.instrumentDate, "dd-MMM-yyyy");
                                               requestData.debitAccNo = vm.debitAccNo;
                                               requestData.creditAccNo = vm.bondCollectionAccount;
                                               requestData.tranDebitAccNo = vm.debitAccNo;
                                           }
                                           if(vm.transactionMode == 'CL'){
                                               requestData.branchid = vm.debitBranchId;
                                               requestData.instrumentType = vm.instrumentType;
                                               requestData.instrumentNo = vm.instrumentNo;
                                               requestData.instrumentDate = $rootScope.formatDateToDDMMMYYYY(vm.instrumentDate),
                                               requestData.tranCode = vm.tranCode;
                                               requestData.zoneCode = vm.zoneCode;
                                               requestData.zoneDate = $rootScope.formatDateToDDMMMYYYY(vm.zoneDate),
                                               requestData.bankCodeNonMicr = vm.bankCodeNonMicr;
                                               requestData.branchCodeNonMicr = vm.branchCodeNonMicr;
                                               requestData.sortCode = vm.sortCode;
                                               requestData.debitAccNo = vm.bondClearingAccount;
                                               requestData.creditAccNo = vm.bondCollectionAccount;
                                           }
                                           if(vm.transactionMode == 'C'){
                                               requestData.debitAccNo = vm.bondCashAccount;
                                               requestData.creditAccNo = vm.bondCollectionAccount;
                                           }

                                          if(vm.isNominee == 'N'){
                                               requestData.nomineeDetailsList = null;
                                            }else{
                                               //nomination details
                                                 angular.forEach(vm.nominationDetails, function(item){
                                                         item.dateOfBirthOfMinor = $filter('date')(item.dateOfBirthOfMinor, "dd-MMM-yyyy");
                                                 });
                                                 requestData.nomineeDetailsList = vm.nominationDetails;
                                            }

                                           var cbsRequestData = {
                                                   "creditAccountNumber" : requestData.creditAccNo,
                                               "depositAmount" : requestData.subscriptionAmount,
                                               "transactionType" : requestData.tranMode,
                                                   "debitAccountNumber" : requestData.debitAccNo,
                                           };

                                           cumToNonCumChangeOfBondTypeService.submitAddForm(requestData).then(
                                                                                                     (response) => {
                                                                                                        vm.loader=false;
                                                                                                        vm.responseResult = response.data.result;
                                           //
                                                                                                          var msgForAdd = "Bond Deposit Account Created Successfully \n Subscription Id : " + vm.responseResult.subscriptionId+ "\n GBM Tran Id : " +vm.responseResult.gbmTranId + "\n BLA No: " + vm.responseResult.blaNo;
                                                                                                        Swal.fire("Added!", msgForAdd , "success");
                                                                                                       location.href = '#!/cum_to_non_cum_change_of_bond_type/action/inquiry';
                                                                                                    },function(error) {
                                                                                                          vm.loader = false;
                                                                                                          handleErrorResponse(error);
                                                                                                          $state.go('cum_to_non_cum_change_of_bond_type.add');
                                                                                                    });


                       //                    cumToNonCumChangeOfBondTypeService.getCbsDetails(cbsRequestData).then(function(cbsResponse){
                       //                                            vm.message = cbsResponse.data.message;
                       //                                            if(vm.message == 'SUCCESS'){
                       //                                                vm.cbsStatus = cbsResponse.data.result.status;
                       //                                                vm.cbsDetails = cbsResponse.data.result.tranDetailsDTOList;
                       //                                                if(vm.cbsStatus == 'S'){
                       //
                       //                                                     angular.forEach(vm.cbsDetails, function(item){
                       //                                                            if(item.accountNumber == requestData.creditAccNo) {
                       //                                                                    //vm.creditAccountNumberType = item.isSysAcct;
                       //                                                                    requestData.creditAccType = item.isSysAcct;
                       //                                                            }
                       //                                                            else if(item.accountNumber == requestData.debitAccNo){
                       //                                                                    requestData.debitAccType = item.isSysAcct;
                       //                                                            }
                       //                                                    });
                       //
                       //                                                    if(requestData.creditAccType){
                       //                                                        requestData.debitAccType = requestData.creditAccType;
                       //                                                    }
                       //                                                    if(requestData.debitAccType){
                       //                                                        requestData.creditAccType = requestData.debitAccType;
                       //                                                    }
                       //                                                    }
                       //
                       //
                       //                                                }else{
                       //                                                    Swal.fire('Warning!', "CBS Account Validation Failed", 'warning');
                       //                                                    $state.go('cum_to_non_cum_change_of_bond_type.add');
                       //                                                }
                       //
                       ////                                               requestData.creditAccType = 'N';  //'N';
                       ////                                             requestData.debitAccType = 'N'; //'N';
                       //
                       //
                       //
                       //
                       //                                              }
                       //                                               else{
                       //                                                     $timeout(Swal.fire("Warning", vm.message ,"warning"), 30000);
                       //                                                     $state.go('cum_to_non_cum_change_of_bond_type.add');
                       //                                                     vm.loader=false;
                       //                                             }
                       //                                             },function(error) {
                       //                                                          vm.loader = false;
                       //                                                          handleErrorResponse(error);
                       //                                                          $state.go('cum_to_non_cum_change_of_bond_type.add');
                       //                                            });
                         }



                                   });


                               }
                               else{
                                   Swal.fire('Warning!', message, 'warning');
                               }


                               }
                       //var requestData = {
                       //         'scssCustomerId' : vm.accountOpening.customerId,
                       //         'transactionType' : vm.accountOpening.tranMode
                       //        };
                       //           cumToNonCumChangeOfBondTypeService.getValidationAdd(requestData).then(function(response){
                       //              vm.accountNumberDetails = response.data.result;

                               function getCbsDetails(){
                                           var requestData={
                                               'bondCashAccount':vm.bondCashAccount,
                                               'bondClearingAccount':vm.bondClearingAccount,
                                               'bondCollectionAccount':vm.bondCollectionAccount,
                                               'ddPoAccountNo':vm.ddPoAccountNo,
                                               'neftPlaceHolderAccount':vm.creditAccNo,
                                               'neftPlaceholderFlgOut':vm.neftPlaceholderFlgOut,
                                               'ddPoAccFlgOut':vm.ddPoAccFlgOut,
                                               'bondCashAccFlgOut':vm.bondCashAccFlgOut,
                                               'bondClearingAccFlgOut':vm.bondClearingAccFlgOut,
                                               'bondCollectionAccFlgOut':vm.bondCollectionAccFlgOut,
                                               'tranMode' :  vm.transactionMode,
                                           }
                                          cumToNonCumChangeOfBondTypeService.getCbsDetails(requestData).then(function(response)
                                           {
                                               vm.message = response.data.message;
                                               if(vm.message == 'SUCCESS'){
                                                   vm.cbsStatus = response.data.result.status;
                                                   vm.cbsDetails = response.data.result.tranDetailsDTOList;
                                                   if( vm.cbsStatus == 'S')
                                                   {
                                                           angular.forEach(vm.cbsDetails, function(item){
                                                                   if(item.accountNumber==vm.accountOpening.creditAccountNumber)
                                                                   {
                                                                           vm.creditAccountNumberType = item.isSysAcct;
                                                                   }
                                                                   else if(item.accountNumber==vm.accountOpening.acctNum)
                                                                   {
                                                                           vm.debitAccountNumberType = item.isSysAcct;
                                                                   }
                                                           });
                                                   }
                                                       if(vm.accountOpening.creditAccountNumber == vm.accountOpening.acctNum)
                                               {
                                                       vm.debitAccountNumberType = vm.creditAccountNumberType;
                                               }

                                               requestData.creditAccountNumberType = vm.creditAccountNumberType;  //'N';
                                               requestData.debitAccountNumberType = vm.debitAccountNumberType; //'N';


                                    }
                                    },function(error) {
                                      vm.loader = false;
                                         handleErrorResponse(error);
                                       });

                               }

                       //        function submitAddAccountOpening() {
                       //
                       //                 Swal.fire({
                       //                    title : "Are you sure?",
                       //                    text : "Do you want to Really Add this record?",
                       //                    type : "warning",
                       //                    showCancelButton : true,
                       //                    cancelButtonClass : "btn-cancel",
                       //                    confirmButtonClass : "btn-primary",
                       //                    confirmButtonText : "Yes",
                       //                    closeOnConfirm : false
                       //                },
                       //                function() {
                       //
                       //                 var requestData = {};
                       //                     requestData.scssCustomerId = vm.accountOpening.customerId;
                       //                        if(vm.accountOpening.tranMode != 'T'){
                       //                        requestData.branchId = vm.loginSolId;
                       //                        } else{
                       //                        requestData.branchId = vm.accountOpening.interSolId;
                       //                        }
                       //                     requestData.depositAmount = parseInt(vm.accountOpening.depositAmount);
                       //                     requestData.isNominee = vm.accountOpening.isNomineeRequired;
                       //                     requestData.transactionType = vm.accountOpening.tranMode;
                       //                     requestData.neftOperativeAccountNumber = vm.accountOpening.operativeAccNo;
                       //                     requestData.neftIfscCode = vm.accountOpening.codeOfCreditBank;
                       //                     requestData.neftNameOfThePayee = vm.accountOpening.payeeName;
                       //                     requestData.interestPaymentMode = vm.accountOpening.interestPaymentMode;
                       //                     if(vm.accountOpening.tranMode=='C' || vm.accountOpening.tranMode=='T')
                       //                     {
                       //                     requestData.micrCode = null;
                       //                     }else{
                       //                     requestData.micrCode = vm.accountOpening.sortCode;
                       //                     }
                       //                        vm.accountOpeningMaster.C002= vm.accountOpening.holderNum;
                       //                        vm.accountOpeningMaster.C005=vm.accountOpening.depositAmount;
                       //                        vm.accountOpeningMaster.C004=vm.accountOpening.accountHolderType;
                       //                        //vm.accountOpeningMaster.C009=vm.accountOpening.interestPaymentMode;
                       //                        vm.accountOpeningMaster.C010=vm.accountOpening.C010;
                       //
                       //                       requestData.nomineeDetails =[];
                       //                       requestData.creditAccountNumber = vm.accountOpening.creditAccountNumber;
                       //                       requestData.debitAccountNumber = vm.accountOpening.acctNum;
                       //                        if(vm.accountOpening.interestPaymentMode == 'PM001')
                       //                          {
                       //                                requestData.creditAccountNumber = vm.accountOpening.interestCreditAccountNumber;
                       //                                vm.accountOpeningMaster.C026=vm.accountOpening.interestCreditAccountNumber;
                       //                          }
                       //                          requestData.accountDetails = [convertToColumnValuesArray(vm.accountOpeningMaster)];
                       //                        requestData.setNum=vm.setId;
                       //                        requestData.instrumentType = vm.accountOpening.instType;
                       //                        requestData.instrumentNumber = vm.accountOpening.instNumber;
                       //                        requestData.instrumentDate = $filter('date')(vm.accountOpening.instDate, 'dd/MMM/yyyy');
                       //                        requestData.tranCode = vm.accountOpening.tranCode;
                       //                        requestData.zoneCode = vm.accountOpening.zoneCode;
                       //                        requestData.instrumentBankCode = vm.accountOpening.bankCode;
                       //                        requestData.instrumentBranchCode = vm.accountOpening.branchCode;
                       //                        requestData.zoneDate = $filter('date')(vm.accountOpening.zoneDate, 'dd/MMM/yyyy');
                       //                        requestData.neftBankCode = vm.accountOpening.zoneBankCode;
                       //                        requestData.neftBranchCode = vm.accountOpening.zoneBranchCode;
                       //                        //requestData.creditAccountNumberType = 'N'; //vm.creditAccountNumberType;
                       //                        //requestData.debitAccountNumberType = 'N'; //vm.debitAccountNumberType;
                       //
                       //                   cumToNonCumChangeOfBondTypeService.getCbsDetails(requestData).then(function(response)
                       //                    {
                       //                        vm.message = response.data.message;
                       //                        if(vm.message == 'SUCCESS'){
                       //                            vm.cbsStatus = response.data.result.status;
                       //                            vm.cbsDetails = response.data.result.tranDetailsDTOList;
                       //                            if( vm.cbsStatus == 'S')
                       //                            {
                       //                                    angular.forEach(vm.cbsDetails, function(item){
                       //                                            if(item.accountNumber==vm.accountOpening.creditAccountNumber)
                       //                                            {
                       //                                                    vm.creditAccountNumberType = item.isSysAcct;
                       //                                            }
                       //                                            else if(item.accountNumber==vm.accountOpening.acctNum)
                       //                                            {
                       //                                                    vm.debitAccountNumberType = item.isSysAcct;
                       //                                            }
                       //                                    });
                       //                            }
                       //                                if(vm.accountOpening.creditAccountNumber == vm.accountOpening.acctNum)
                       //                        {
                       //                                vm.debitAccountNumberType = vm.creditAccountNumberType;
                       //                        }
                       //
                       //                        requestData.creditAccountNumberType = vm.creditAccountNumberType;  //'N';
                       //                        requestData.debitAccountNumberType = vm.debitAccountNumberType; //'N';
                       //
                       //                     cumToNonCumChangeOfBondTypeService.submitAddForm(requestData).then(
                       //                      (response) => {
                       //                             vm.loader=false;
                       //                               vm.scssAccountNumber = response.data.result.scssAccountNumber;
                       //                               vm.gbmTranId = response.data.result.gbmTranId;
                       //                               var msgForAdd = "Account Created Successfully \n Account No : " +vm.scssAccountNumber+ "\n GBM Tran Id : " +vm.gbmTranId;
                       //                             Swal.fire("Added!", msgForAdd , "success");
                       //                        location.href = '#!/cum_to_non_cum_change_of_bond_type/action/inquiry';
                       //                     },function(error) {
                       //                       vm.loader = false;
                       //                          handleErrorResponse(error);
                       //                        });
                       //                        }
                       //                           else{
                       //                                 $timeout(Swal.fire("Warning", vm.message ,"warning"), 30000);
                       //                                 $state.go('cum_to_non_cum_change_of_bond_type.add');
                       //                                 vm.loader=false;
                       //                         }
                       //                        },function(error) {
                       //                            vm.loader = false;
                       //                            handleErrorResponse(error);
                       //                         });
                       //
                       //                });
                       //
                       //        }

                       		function decimalConvert(value, index, event){

                                   let decimal=parseFloat(value);

                                   vm.index = index;
                                   if(decimal == undefined || decimal == null || decimal == NaN){
                                       if(event.target.value == ""){
                                           vm.errorMsg[vm.index] = false;
                                       }else if(event.target.id == vm.index){

                                           if(event.target.value <=100){
                                               vm.errorMsg[vm.index] = false;
                                           }
                                           else {
                                                vm.errorMsg[vm.index] = true;

                                               for(var i=0; i<vm.nominationDetails.length; i++){
                                                   if(vm.index == i ){
                                                   vm.nominationDetails[vm.index].sharePercentage = '';
                                               break;
                                               }
                                               }
                                           }
                                       }
                                   }
                                   else{
                                           if (decimal <= 100) {
                                               vm.errorMsg[vm.index] = false;

                                           }else{
                                               vm.errorMsg[vm.index] = true;
                                               for(var i=0; i<vm.nominationDetails.length; i++){
                                                   if(vm.index == i ){
                                                   vm.nominationDetails[vm.index].sharePercentage = '';
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
                                  cumToNonCumChangeOfBondTypeService.getVerifySelect(requestData).then(function(response){
                                  vm.loader = false;
                                     angular.element('#customerIdModalVerify').modal('toggle');
                                     vm.customerIdVerifyList = response.data.result.customerIdOut;
                                   },function(error) {
                                   vm.loader = false;
                                      handleErrorResponse(error);
                                    });
                               }

                               //Get Account Number for verify
                               function getAccountNumberForVerify() {
                               vm.loader = true;
                               var requestData = {
                               'scssCustomerId' : vm.accountOpening.customerId
                               };
                                  cumToNonCumChangeOfBondTypeService.getVerifySelect(requestData).then(function(response){
                                     vm.loader = false;
                                     angular.element('#accountNumberModal').modal('toggle');
                                     vm.scssAccountNumberList = response.data.result.scssAccountNumberOut;
                                   },function(error) {
                                      vm.loader = false;
                                      handleErrorResponse(error);
                                    });
                               }

                               //Get Customer ID for Inquiry
                               function getCustomerIdForInquiry() {
                               vm.loader = true;
                               var requestData = {};
                                  cumToNonCumChangeOfBondTypeService.getInquiryDetails(requestData).then(function(response){
                                  vm.loader = false;
                                     angular.element('#customerIdModalInquiry').modal('toggle');
                                     vm.customerIdInquiryList = response.data.result.customerIdOut;
                                   },function(error) {
                                   vm.loader = false;
                                      handleErrorResponse(error);
                                    });
                               }

                               //Get Account Number for Inquiry
                               function getAccountNumberForInquiry() {
                               vm.loader = true;
                               var requestData = {
                               'scssCustomerId' : vm.accountOpening.customerId
                               };
                                  cumToNonCumChangeOfBondTypeService.getInquiryDetails(requestData).then(function(response){
                                     vm.loader = false;
                                     angular.element('#accountNumberModal').modal('toggle');
                                     vm.scssAccountNumberList = response.data.result.scssAccountNumberOut;
                                   },function(error) {
                                      vm.loader = false;
                                      handleErrorResponse(error);
                                    });
                               }

                               function getVerifyDeleteSelect() {
                                           vm.loader = true;

                                           var requestData = {
                                           'schemeCode' : vm.schemeCode,
                                           'blaNo' : vm.blaNo,
                                           'subscriptionId' : vm.subscriptionId,
                                           };
                                              cumToNonCumChangeOfBondTypeService.getVerifySelect(requestData).then(function(response){
                                              vm.loader = false;
                                                 vm.auditInfo = response.data.result;
                                                 vm.getSubscriptionDetails = vm.auditInfo.masterDetailsValueModel[0];
                                                 vm.getNomineeDetails = vm.auditInfo.nomineeValueModel;
                                                 vm.lastVerifiedBy = vm.auditInfo.lastVerifiedBy;

                                               },function(error) {
                                               vm.loader = false;
                                                  handleErrorResponse(error);
                                                  $state.go('cum_to_non_cum_change_of_bond_type.inquiry');
                                                });
                               }

                               function getInquirySelect() {
                               vm.loader = true;

                               var requestData = {
                               'schemeCode' : vm.schemeCode,
                               'blaNo' : vm.blaNo,
                               'subscriptionId' : vm.subscriptionId,
                               };
                                  cumToNonCumChangeOfBondTypeService.getInquiryDetails(requestData).then(function(response){
                                  vm.loader = false;
                                     vm.auditInfo = response.data.result;
                                     vm.getSubscriptionDetails = vm.auditInfo.masterDetailsValueModel[0];
                                     vm.getNomineeDetails = vm.auditInfo.nomineeValueModel;

                                   },function(error) {
                                   vm.loader = false;
                                      handleErrorResponse(error);
                                      $state.go('cum_to_non_cum_change_of_bond_type.inquiry');
                                    });
                               }

                               /* Verify Submit */
                               function submitVerifyForm() {
//                                   Swal.fire({
//                                           title: "Are you sure?",
//                                           text: "Do you want to Really Verify this record?",
//                                           type: "warning",
//                                           showCancelButton: true,
//                                           cancelButtonClass: "btn-cancel",
//                                           confirmButtonClass: "btn-primary",
//                                           confirmButtonText: "Yes",
//                                           closeOnConfirm: false
//                                       },
//
//                                       function() {


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
                                           'schemeCode': vm.getDetails.schemeCode,
                                           'subscriptionId' : vm.getDetails.subscriptionId,
                                                                            'blaNo' : vm.getDetails.blaNo,
                                           };

                                           cumToNonCumChangeOfBondTypeService.submitVerifyForm(requestData).then(
                                               (response) => {
                                                   vm.loader = false;
                                                   vm.responseResult = response.data.result;
                                                   vm.message = "Cumulative to Non-Cumulative change of Bond Type Verified Successfully";
                                                   Swal.fire("Verified!", vm.message ,"success");

                                                   location.href = '#!/cum_to_non_cum_change_of_bond_type/action/inquiry';
                                               }, function(error) {
                                                              vm.loader = false;
                                                                 handleErrorResponse(error);
                                                                 location.href = '#!/cum_to_non_cum_change_of_bond_type/action/inquiry';
                                                               });
                                      } }, function(error) {
                                          	vm.loader = false;
                                          	handleErrorResponse(error);
                                       });

                               }

                               /* Delete Submit */
                               function submitDeleteForm() {
//                                   Swal.fire({
//                                           title: "Are you sure?",
//                                           text: "Do you want to Really Delete this record?",
//                                           type: "warning",
//                                           showCancelButton: true,
//                                           cancelButtonClass: "btn-cancel",
//                                           confirmButtonClass: "btn-primary",
//                                           confirmButtonText: "Yes",
//                                           closeOnConfirm: false
//                                       },
//
//                                       function() {


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
                                               'schemeCode': vm.auditInfo.schemeCode,
                                               'subscriptionId' : vm.auditInfo.subscriptionId,
                                               };

                                           cumToNonCumChangeOfBondTypeService.submitDeleteForm(requestData).then(
                                               (response) => {
                                                   vm.loader = false;
                                                   Swal.fire("Deleted!","BOND Deposit Account Deleted Successfully","success");
                                                   location.href = '#!/cum_to_non_cum_change_of_bond_type/action/inquiry';
                                               }, function(error) {
                                                              vm.loader = false;
                                                                 handleErrorResponse(error);
                                                               });
                                     }  }, function(error) {
                                          	vm.loader = false;
                                          	handleErrorResponse(error);
                                       });

                               }





                               const convertToColumnValuesArrayList = (objectToBeConvert) => {
                                         if(typeof(objectToBeConvert) === "object") {
                                            let resultArray = [];
                                            for(let key of Object.keys(objectToBeConvert)) {
                                               if(objectToBeConvert[key])
                                                   resultArray.push({colName : key,value : objectToBeConvert[key].oldValue})
                                            }

                                            return resultArray;
                                         } else {
                                           throw "Only objects can be converted"
                                         }
                                       }

                               //Get Customer ID
                               function getCustomerIdForModify() {
                               vm.loader = true;
                               var requestData = {};
                                  cumToNonCumChangeOfBondTypeService.getModifySelect(requestData).then(function(response){
                                  vm.loader = false;
                                     angular.element('#customerIdModal').modal('toggle');
                                     vm.customerIdList = response.data.result.customerIdOut;
                                   },function(error) {
                                   vm.loader = false;
                                      handleErrorResponse(error);
                                    });
                               }

                               //Get Account Number for Modify
                               function getAccountNumberForModify() {
                               vm.loader = true;
                               var requestData = {
                               'scssCustomerId' : vm.accountOpening.customerId
                               };
                                  cumToNonCumChangeOfBondTypeService.getModifySelect(requestData).then(function(response){
                                     vm.loader = false;
                                     angular.element('#accountNumberModal').modal('toggle');
                                     vm.scssAccountNumberList = response.data.result.scssAccountNumberOut;
                                   },function(error) {
                                      vm.loader = false;
                                      handleErrorResponse(error);
                                    });
                               }





                               		function convertToColumnValuesArrayOldNew(objectToBeConvert) {
                               			if (typeof (objectToBeConvert) === "object") {
                               				let resultArray = [];
                               				for (let key of Object.keys(objectToBeConvert)) {
                               					if (objectToBeConvert[key])
                               						resultArray.push({
                               							colName: key,
                               							value: objectToBeConvert[key].oldValue
                               						})
                               				}
                               				return resultArray;
                               			} else {
                               				throw "Only objects can be converted"
                               			}
                               		}


                               function getAccountPage(){
                                  vm.accountDetails = true;
                                  vm.nomination = false;
                                  if($state.current.name == 'cum_to_non_cum_change_of_bond_type.inquiry_detail'){
                                  vm.matrix = false;
                                  }
                               }
                               function getNominationPage(){
                                  vm.accountDetails = false;
                                  vm.nomination = true;
                                  if($state.current.name == 'cum_to_non_cum_change_of_bond_type.inquiry_detail'){
                                  vm.matrix = false;
                                  }
                               }
                               function getMatrixPage(){
                                  vm.accountDetails = false;
                                  vm.nomination = false;
                                  vm.matrix = true;
                               }

                           function getDebitAccountName (acctNum){
                               if(acctNum){
                               commonService.getSchemeTypeListAcc(acctNum, function(response){
                                   if(response.success) {
                                       vm.accList = response.data;
                                       if(vm.accList.length>0)
                                       {
                                       vm.accountOpening.accountName = vm.accList[0].accountName;
                                       }
                                   } else {
                                       Swal.fire("Error!", response.message, "error");
                                               }
                               }, function(err) {
                                        handleErrorResponse(err);
                                });
                               }
                           }

                               function getCashValidation(){
                                  if(vm.accountOpening.tranMode == 'C'){
                                     if(vm.accountOpening.depositAmount>vm.maximumCashLimit){
                                        vm.errorMessage = 'Deposit Amount could not exceed cash limit';
                                        vm.accountOpening.depositAmount = '';
                                        //vm.accountOpening.totalAmount = '';
                                     }
                                  }
                               }

                           function getCodeDescription(){

                               if(vm.tranCodeList.length>0){
                                 for(let i=0;i<=vm.tranCodeList.length-1;i++){
                                     if(vm.tranCodeList[i].clgTranCode == vm.tranCode){
                                        if(vm.tranCodeList[i].clgTranCodeDesc){
                                        vm.tranCodeDescription =  vm.tranCodeList[i].clgTranCode + '-' +  vm.tranCodeList[i].clgTranCodeDesc;
                                        break;
                                        }
                                        else {
                                           vm.tranCodeDescription =  vm.tranCodeList[i].clgTranCode;
                                           break;
                                        }
                                     }
                                 }
                                 }

                                 if(vm.zoneCodeList.length>0){
                                 for(let i=0;i<=vm.zoneCodeList.length-1;i++){
                                     if(vm.zoneCodeList[i].clgZoneCode== vm.zoneCode){
                                        if(vm.zoneCodeList[i].clgZoneDesc){
                                        vm.zoneCodeDescription =   vm.zoneCodeList[i].clgZoneCode + '-'  +  vm.zoneCodeList[i].clgZoneDesc;
                                        break;
                                        }
                                        else {
                                           vm.zoneCodeDescription =   vm.zoneCodeList[i].clgZoneCode;
                                           break;
                                        }
                                     }
                                 }
                                 }

                                 if(vm.instrumentBankCode){
                                 if(vm.bankCodeList.length>0){
                                 for(let i=0;i<=vm.bankCodeList.length-1;i++){
                                     if(vm.bankCodeList[i].bankCode== vm.instrumentBankCode){
                                        if(vm.bankCodeList[i].bankName){
                                        vm.bankCodeDescription =   vm.bankCodeList[i].bankCode + '-'  +  vm.bankCodeList[i].bankName;
                                        break;
                                        }
                                        else {
                                           vm.bankCodeDescription =   vm.bankCodeList[i].bankCode;
                                           break;
                                        }
                                     }
                                 }
                                 }
                                 }

                                 if(vm.instrumentBranchCode){
                                 if(vm.branchCodeList.length>0){
                                 for(let i=0;i<=vm.branchCodeList.length-1;i++){
                                     if(vm.branchCodeList[i].branchCode== vm.instrumentBranchCode){
                                        if(vm.branchCodeList[i].branchName){
                                        vm.branchCodeDescription =   vm.branchCodeList[i].branchCode + '-'  +  ((vm.branchCodeList[i].branchName) ? (vm.branchCodeList[i].branchName) : '');
                                        break;
                                        }
                                        else {
                                          vm.branchCodeDescription =   vm.branchCodeList[i].branchCode;
                                          break;
                                        }
                                     }
                                 }
                                 }
                                 }

                           }


                           	function getMicrFlag(solId,zoneCode){
                           	    vm.zoneCodeList = [];
                           		commonService.getZoneCode(solId,zoneCode,function(response){
                           				vm.zoneCodeList = response.data;
                           				for(var i=0; i<=vm.zoneCodeList.length-1;i++){
                           				      vm.zoneCodeList[i].clgZoneDate = new Date(vm.zoneCodeList[i].clgZoneDate);
                           				   if(vm.zoneCodeList[i].clgZoneDate.getTime() == vm.accountOpening.zoneDate.getTime()){
                           				      vm.accountOpening.micrClgFlag = vm.zoneCodeList[i].micrClgFlag;
                           				      break;
                           				   }
                           				}
                                   });
                           	}

                           	 function zoneDateValidation() {
                                                     vm.zoneDateOptions = {
                                                         showWeeks: false,
                                                         maxDate: vm.systemDate
                                                     }
                                                 }

                                           function setIdValidation(){
                                                     if(vm.setId == 0){
                                                             var msg = 'SetId should not be zero';
                                                             Swal.fire('Warning!', msg, 'warning');
                                                             vm.setId = '';
                                                     }
                                                  }

                               function instrumentAmountValidation(){
                                              if(vm.accountOpening.depositAmount != vm.instrumentAmt){
                                                vm.errorMessageInstrumentAmount = 'Instrument Amount should be equal to Deposit Amount';
                                                vm.instrumentAmt = '';
                                              }else{
                                                vm.errorMessageInstrumentAmount = '';
                                              }
                                       }


                                          function nextStep() {
                                                   var $active = $('.from_wrapper .nav-tabs li.active');
                                                   nextTab($active);
                                               }



                                               function nextTab(elem) {
                                                   $(elem).next().find('a[data-bs-toggle="tab"]').click();
                                               }


            function validatePanLetter() {
                   vm.validationMessages = '';

            if (vm.panNumber.length >= 4) {
                              var fourthLetter = vm.panNumber.charAt(3).toUpperCase();

                             if (['MH001', 'MH002', 'MH003', 'MH005'].includes(vm.modeOfHolding) && fourthLetter !== 'P') {
                                                 vm.validationMessages = 'For ' + vm.modeOfHolding + ', the fourth letter should be P.';
                                             }
                                             else if (['MH004'].includes(vm.modeOfHolding) && fourthLetter !== 'H') {
                                                 vm.validationMessages = 'For HUF, the fourth letter should be H.';
                                             }
                          }
                          };

                        function isMinor() {
                          // Parse the input date (format: dd/MM/yyyy)
                          var dobInput  =$filter('date')(vm.fromDate1, 'dd/MM/yyyy');
                          const [day, month, year] = dobInput.split('/');
                          const birthDate = new Date(`${year}-${month}-${day}`); // Create a Date object from the input

                          // Calculate the difference in years between the current date and the birth date
                          const today = new Date();
                          const age = today.getFullYear() - birthDate.getFullYear();

                          // Adjust the age if the current date is before the birth date this year
                          const monthDiff = today.getMonth() - birthDate.getMonth();
                          const dayDiff = today.getDate() - birthDate.getDate();
                          const isMinor = age < 18 || (age === 18 && (monthDiff < 0 || (monthDiff === 0 && dayDiff < 0)));


                          // Capture the value as "Y" or "N"
                          vm.customerMasterDetails.isMinor = isMinor ? 'Y' : 'N';
                        }


        function getTorchDetails(flag,blaNo,schemeCode,panNum,subscriptionId,bondHolderName){
                    vm.loader = true;
              if(flag == "submit"){
                     $state.go('cum_to_non_cum_change_of_bond_type.add_detail', {
                        obj: {
                            schemeName: vm.schemeName,
                            schemeCode: vm.schemeCode,
                            blaNo: vm.blaNo,
                            bondSubsId: vm.subscriptionId,
                            modeOfHolding: vm.modeOfHolding,
                            panNum: vm.panNum,
                            moduleId: vm.moduleName,
                            subscriptionId: vm.subscriptionId,
                            bondHolderName: vm.bondHolderName
                        }
                    });
                        return
                    }



                    vm.blaNo = blaNo;
                    vm.schemeCode = schemeCode;
                    vm.panNum = panNum;
                    vm.subscriptionId = subscriptionId;
                    vm.bondHolderName = bondHolderName;

                  if(flag){

                       var requestData = {
                         'schemeCode' : vm.schemeCode,
                         'blaNo' : vm.blaNo,
                         'subscriptionId' : vm.subscriptionId,
                         'bondHolderName' : vm.bondHolderName,
                         'panNum' : vm.panNum
                        };
                       cumToNonCumChangeOfBondTypeService.addDet(requestData).then(function (response) {
                       vm.loader = false;

                                              vm.subDetail = response.data.result;
//                                              if(vm.subDetail && flag == "submit"){
//$state.go('cum_to_non_cum_change_of_bond_type.add_detail', {
//    obj: {
//        schemeName: vm.schemeName,
//        schemeCode: vm.schemeCode,
//        blaNo: vm.blaNo,
//        bondSubsId: vm.subscriptionId,
//        modeOfHolding: vm.modeOfHolding,
//        panNum: vm.panNum,
//        moduleId: vm.moduleName,
//        subscriptionId: vm.subscriptionId,
//        bondHolderName: vm.bondHolderName
//    }
//});
//
//
//}
                        }, function (error) {
                              const errorMessage = error && error.data && error.data.message
                                                          ? error.data.message
                                                          : "An unexpected error occurred.";
                                                  if(null!=errorMessage && errorMessage.startsWith("Invalid")){
                                                  Swal.fire("Warning!", errorMessage , "warning");
                                                  $state.go("cum_to_non_cum_change_of_bond_type.list");
                                                  }else{
                                                  handleErrorResponse(error);
                                                  $state.go("cum_to_non_cum_change_of_bond_type.list");
                                                  }
                           });
                  }

        }




        }
        })();



