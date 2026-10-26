(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('rbiBondSubscriptionWorkFlowController',
        rbiBondSubscriptionWorkFlowController);

    rbiBondSubscriptionWorkFlowController.$inject = ['$scope', '$filter', '$state',
        '$stateParams', '$location', '$rootScope', '$timeout', 'rbiBondSubscriptionWorkFlowService', 'commonService'
    ];

    function rbiBondSubscriptionWorkFlowController($scope, $filter, $state, $stateParams,
        $location, $rootScope, $timeout, rbiBondSubscriptionWorkFlowService, commonService) {

		var vm = this;

        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
        vm.getScssCommonMetaData = getScssCommonMetaData;

        vm.interestMatrixStatus = [{
        label : "Paid" , value : "Y"},{
        label : "Unpaid" , value : "N"
        }]

        vm.errorMsg = [];
        vm.textableDatePicker=textableDatePicker;
        vm.autoslashing=autoslashing;

        function textableDatePicker(id,valueDate)
        {
         commonService.textableDatePicker(id,valueDate);
        }
        function autoslashing(id)
        {
         commonService.autoslashing(id);
        }

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
        vm.getModuleName = getModuleName;
        vm.getModuleNameAdd = getModuleNameAdd;
        vm.getAddBlaTorch = getAddBlaTorch;
        vm.getAddBlaDetails = getAddBlaDetails;
        vm.convertLabelValue = convertLabelValue;
        vm.getApplicationNoTorch = getApplicationNoTorch;
        vm.getApplicationDetails = getApplicationDetails;
        vm.getBrokerTorch = getBrokerTorch;
        vm.getModeOfInterestPayment = getModeOfInterestPayment;
        vm.validateOperativeAccNum = validateOperativeAccNum;
        vm.validateOperativeAccNumM = validateOperativeAccNumM;
        vm.validateOperativeAccNumNominee = validateOperativeAccNumNominee;
        vm.getGuardianDetailsMetaData = getGuardianDetailsMetaData;
        vm.modeOfInterestPaymentValidation = modeOfInterestPaymentValidation;
        vm.modeOfInterestPaymentValidationM = modeOfInterestPaymentValidationM;
        vm.validateNominee = validateNominee;
        vm.emailPattern = /[^@]+@[^@]+\.[a-zA-Z]{2,6}/;
        vm.inquiryBlaTorch = inquiryBlaTorch;
        vm.inquirySubscriptionTorch = inquirySubscriptionTorch;
        vm.getModeofHolding = getModeofHolding;
        vm.alert = alert;
        vm.verifyBlaTorch = verifyBlaTorch;
        vm.verifySubscriptionTorch = verifySubscriptionTorch;
      vm.modifyBlaTorch = modifyBlaTorch;
      vm.modifySubscriptionTorch = modifySubscriptionTorch;
      vm.validateOperativeAccNumForDebit = validateOperativeAccNumForDebit;
      vm.validateOperativeAccNumForDebitModify = validateOperativeAccNumForDebitModify;
      vm.getOtherBankDetailsMicrTorch = getOtherBankDetailsMicrTorch;
      vm.getOtherBankDetailsMicrTorchM = getOtherBankDetailsMicrTorchM;
//        vm.accountOpeningDate =  $filter('date')($rootScope.globals.currentUser.systemDate, 'dd/MM/yyyy');
//        vm.accountOpeningDate = $rootScope.globals.currentUser.systemDate;
//        vm.accountOpeningDate = $rootScope.globals.currentUser.systemDate;
//
//
//        getSubCodeList();
//        getTranCodeDetails();
//        getBankCodeDetails();
//        getZoneCodeDetails();



        if ($state.current.name == 'rbi_bnd_subs_workFlw.add_detail') {
            if ($stateParams.obj) {

		        vm.schemeCode = $stateParams.obj.schemeCode;
		        vm.schemeName = $stateParams.obj.schemeName;
		        vm.blaNo = $stateParams.obj.blaNo;
		        vm.modeOfHolding = $stateParams.obj.modeOfHolding;
		        vm.modeOfHoldingWord = $stateParams.obj.modeOfHoldingWord;

		        getModeOfInterestPayment();
		        getAddBlaDetails();
                getTransactionModes();
                getState();
                getGuardianDetailsMetaData();
            } else {
                $state.go('rbi_bnd_subs_workFlw.add');
            }
        }
        else if ($state.current.name == 'rbi_bnd_subs_workFlw.modify_detail') {
            if ($stateParams.obj) {
                 vm.schemeCode = $stateParams.obj.schemeCode;
                 vm.schemeName = $stateParams.obj.schemeName;
                 vm.blaNo = $stateParams.obj.blaNo;
                 vm.subscriptionId = $stateParams.obj.subscriptionId;
                 vm.modeOfHolding = $stateParams.obj.modeOfHolding;
                 vm.modeOfHoldingKeyword = $stateParams.obj.modeOfHoldingKeyword;

                 getModeofHolding();
                 getModeOfInterestPayment();
                 getTransactionModes();
                 getState();
                 getGuardianDetailsMetaData();
                 getModifySelect();
            } else {
                $state.go('rbi_bnd_subs_workFlw.modify');
            }
        }else if ($state.current.name == 'rbi_bnd_subs_workFlw.verify_detail') {
            if ($stateParams.obj) {
                vm.schemeCode = $stateParams.obj.schemeCode;
                 vm.schemeName = $stateParams.obj.schemeName;
                 vm.blaNo = $stateParams.obj.blaNo;
                 vm.subscriptionId = $stateParams.obj.subscriptionId;

                 getModeofHolding();
                 getModeOfInterestPayment();
                 getTransactionModes();
                 getState();
                 getGuardianDetailsMetaData();
                 getVerifyDeleteSelect();
            } else {
                $state.go('rbi_bnd_subs_workFlw.verify');
            }
        } else if ($state.current.name == 'rbi_bnd_subs_workFlw.delete_detail') {
            if ($stateParams.obj) {
                 vm.schemeCode = $stateParams.obj.schemeCode;
                 vm.schemeName = $stateParams.obj.schemeName;
                 vm.blaNo = $stateParams.obj.blaNo;
                 vm.subscriptionId = $stateParams.obj.subscriptionId;

                 getModeofHolding();
                 getModeOfInterestPayment();
                 getTransactionModes();
                 getState();
                 getGuardianDetailsMetaData();
                 getVerifyDeleteSelect();
            } else {
                $state.go('rbi_bnd_subs_workFlw.delete');
            }
        } else if ($state.current.name == 'rbi_bnd_subs_workFlw.inquiry_detail') {
            if ($stateParams.obj) {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;
                vm.blaNo = $stateParams.obj.blaNo;
                vm.subscriptionId = $stateParams.obj.subscriptionId;

                getModeofHolding();
                getModeOfInterestPayment();
                getTransactionModes();
                getState();
                getGuardianDetailsMetaData();
                getInquirySelect();
            } else {
                $state.go('rbi_bnd_subs_workFlw.inquiry');
            }
        }


        vm.tranTypeRefId = "TRANSACTION_TYPE";
        vm.intPayModeRefId = "INT_PAY_MODE";
        vm.relationRefId = "RELATIONSHIP";
        vm.statesRefId = "STATES OF INDIA";
        vm.instTypeRefId = "INSTRUMENT_TYPE";



                vm.instrumentTypeMetaData = [
                    {label : "CHEQUE",value : "CHQ"},
                    {label : "DD",value : "DD"},
                ];


        function getModuleName() {
                    vm.loader = true;
                    vm.referenceId = 'TAX';
                    var requestData = {
                        'referenceId': vm.referenceId
                    };
                    rbiBondSubscriptionWorkFlowService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                        vm.loader = false;
                        vm.schemeList = response.data.result;
                    }, function (error) {
                        handleErrorResponse(error);
                        vm.loader = false;
                    });
                }

                function getModuleNameAdd() {
                                    vm.loader = true;
                                    vm.referenceId = 'ACTSCH';
                                    var requestData = {
                                        'referenceId': vm.referenceId
                                    };
                                    rbiBondSubscriptionWorkFlowService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                                        vm.loader = false;
                                        vm.schemeList = response.data.result;
                                    }, function (error) {
                                        handleErrorResponse(error);
                                        $state.go('rbi_bnd_subs_workFlw.inquiry');
                                        vm.loader = false;
                                    });
                                }


          function alert(item){

                                 for (let i = 0 ;i < vm.schemeList.length;i++){
                                  if(vm.schemeList[i].subCode == item){
                                        vm.schemeName = vm.schemeList[i].subCodeDescription;

                                   }
                                 }
                              }


               function getModeofHolding() {
                   var requestData = {
                         'referenceId': 'MODE_OF_HOLDING'
                     }
                   rbiBondSubscriptionWorkFlowService.getReferenceData(requestData).then(function(response){
                      vm.modeOfHoldingList = response.data.result;
                    },function(error) {
                       handleErrorResponse(error);
                     });
                }

        /** Common MetaData **/
        /* request information
        */

        function convertLabelValue(list){
                var resultList = [];
                    for(var item in list){
                        list[item].value = list[item].SUBCODE;
                        list[item].label = list[item].DESCRIPTION;
                        let sam = {};
                        sam.label = list[item].label;
                        sam.value = list[item].value;
                        resultList.push(sam);
                    }
//                    return list;
                    return resultList;
                }

        function getScssCommonMetaData(){
            let requestData = {
                referenceIdList: [vm.tranTypeRefId, vm.intPayModeRefId, vm.relationRefId, vm.statesRefId, vm.instTypeRefId],
                genericRequestList: []
            }
            commonService.getScssCommonMetaData(requestData,(result) => {
                vm.referenceValueMap = mapCommonMetaDataObject(result.metaDataList);
            },
            function(error) {
                handleErrorResponse(error);
                vm.loader = false;
            });
        }


        /*Operative Acct Num*/
        function validateOperativeAccNum(acctNum){
            vm.cbsErrorMessage = '';
            vm.loader = true;
            commonService.getCustomAcctInqApi(acctNum, function (response) {
                                    if (response.success) {
                                        vm.accList = response.data;
                                        vm.loader = false;
                                        if (vm.accList.length > 0) {
                                        if (vm.accList[0].status == 'S') {


                                            if(vm.accList[0].accountFreezeStatus) {
                                              vm.cbsErrorMessage = 'Account is Friezed';
                                              vm.operativeAccNum = '';
                                              vm.operativeAccBranchId = '';
                                            }else{
                                                vm.operativeAccBranchId = vm.accList[0].solID;
                                            }


                                            }
                                            if (vm.accList[0].status == 'F') {
                                                vm.cbsErrorMessage = vm.accList[0].errDesc;
                                                vm.operativeAccNum = '';
                                                vm.operativeAccBranchId = '';
                                            }
                                        }
                                        else {
                                            vm.cbsErrorMessage = 'Invalid Account Number';
                                            vm.operativeAccNum = '';
                                            vm.operativeAccBranchId = '';
                                        }
                                    } else {
                                        Swal.fire("Error!", response.message, "error");
                                        vm.loader = false;
                                        vm.operativeAccNum = '';
                                        vm.operativeAccBranchId = '';
                                    }
                                }
                                , function (err) {
                                    handleErrorResponse(err);
                                    vm.loader = false;
                                    vm.operativeAccNum = '';
                                    vm.operativeAccBranchId = '';
                                });
        }

        /*Operative Acct Num*/
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


        function getState() {

                 vm.listType = 'STATES OF INDIA';
                 var requestData = {
                 referenceId:vm.listType
                 }
           rbiBondSubscriptionWorkFlowService.getCommonMetaDatList(requestData).then(function(response){
             vm.stateListValues = response.data.result;
                             vm.stateList = [];
                             angular.forEach(vm.stateListValues, function(item){
                                  vm.stateList.push({subCodeDescription: item.DESCRIPTION, subCode: item.SUBCODE})
                });
            },function(error) {
               handleErrorResponse(error);
             });
        }

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




    //        commonService.getSchemeTypeListAcc(acctNum.toUpperCase(), function(response){
    //            if(response.success) {
    //                vm.accList = response.data;
    //                vm.loader = false;
    //                if(vm.accList.length>0)
    //                {
    //                vm.customerMasterDetails.acctNum = vm.accList[0].forAccountId;
    //                vm.customerMasterDetails.C025 = vm.accList[0].forAccountId;
    //                vm.customerMasterDetails.acctName= vm.accList[0].accountName;
    //                vm.errorMessage ='';
    //                }else
    //                {
    //                vm.errorMessage = 'Account Number failed to validate';
    //                vm.customerMasterDetails.acctNum='';
    //                }
    //            } else {
    //                Swal.fire("Error!", response.message, "error");
    //                vm.loader = false;
    //                        }
    //        }, function(err) {
    //                 handleErrorResponse(err);
    //             vm.loader = false;
    //         });


            }else{
                 vm.loader = false;
             }
        }


        function getAddBlaTorch(){
            vm.loader = true;
                var requestData = {
                "schemeCode": vm.schemeCode,
                };
               rbiBondSubscriptionWorkFlowService.getValidationAdd(requestData).then(function(response){
                    vm.loader = false;
                    vm.blaTorchList = response.data.result.torchValue;
                  angular.element('#blaModal').modal('toggle');

                },function(error) {
                vm.loader = false;
                   handleErrorResponse(error);
                 });

        }

        function getAddBlaTorch(){
            vm.loader = true;
                var requestData = {
                "schemeCode": vm.schemeCode,
                };
               rbiBondSubscriptionWorkFlowService.getValidationAdd(requestData).then(function(response){
                    vm.loader = false;
                    vm.blaTorchList = response.data.result.torchValue;
                  angular.element('#blaModal').modal('toggle');

                },function(error) {
                vm.loader = false;
                   handleErrorResponse(error);
                 });

        }

        function inquiryBlaTorch(){
                    vm.loader = true;
                        var requestData = {
                        "schemeCode": vm.schemeCode,
                        };
                       rbiBondSubscriptionWorkFlowService.getInquiryDetails(requestData).then(function(response){
                            vm.loader = false;
                            vm.blaModalList = response.data.result.torchValue;
                            angular.element('#blaModal').modal('toggle');
                        },function(error) {
                             vm.loader = false;
                           handleErrorResponse(error);
                         });

                }

        function inquirySubscriptionTorch(){
                            vm.loader = true;
                                var requestData = {
                                "schemeCode": vm.schemeCode,
                                "blaNo": vm.blaNo,
                                };
                               rbiBondSubscriptionWorkFlowService.getInquiryDetails(requestData).then(function(response){
                                    vm.loader = false;
                                    vm.subscriptionModalList = response.data.result.torchValue;
                                    angular.element('#subscriptionModal').modal('toggle');
                                },function(error) {
                                     vm.loader = false;
                                   handleErrorResponse(error);
                                 });

                        }



      function modifyBlaTorch(){
                   vm.loader = true;
                       var requestData = {
                       "schemeCode": vm.schemeCode,
                       };
                      rbiBondSubscriptionWorkFlowService.getModifySelect(requestData).then(function(response){
                           vm.loader = false;
                           vm.blaModalList = response.data.result.torchValue;
                           angular.element('#blaModal').modal('toggle');
                       },function(error) {
                            vm.loader = false;
                          handleErrorResponse(error);
                        });

               }

      function modifySubscriptionTorch(){
                           vm.loader = true;
                               var requestData = {
                               "schemeCode": vm.schemeCode,
                               "blaNo": vm.blaNo,
                               };
                              rbiBondSubscriptionWorkFlowService.getModifySelect(requestData).then(function(response){
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
                                     rbiBondSubscriptionWorkFlowService.getOtherBankDetails(requestData).then(function(response){
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
                               rbiBondSubscriptionWorkFlowService.getOtherBankDetails(requestData).then(function(response){
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
                                                       rbiBondSubscriptionWorkFlowService.getOtherBankDetails(requestData).then(function(response){
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
                       rbiBondSubscriptionWorkFlowService.getVerifySelect(requestData).then(function(response){
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
                 vm.getDetails.C014.oldValue ='';
                }
               }

               if(amount && vm.transactionMode){

                   if(vm.transactionMode == 'C'){

                               if(amount>vm.bondCashLimit || amount<vm.minCashLimit){
                               vm.depositMultiplesErrMsg = "Subscription Amount must be between "+vm.minCashLimit +" and "+vm.bondCashLimit;
                               if(flag=='add')
                               vm.subscriptionAmount= '';

                               else
                               vm.getDetails.C014.oldValue ='';
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
                                vm.getDetails.C014.oldValue ='';
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
                               rbiBondSubscriptionWorkFlowService.getVerifySelect(requestData).then(function(response){
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
               rbiBondSubscriptionWorkFlowService.getValidationAdd(requestData).then(function(response){

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

    var numberOfMonths = vm.accountDetails.periodOfDeposit; // Number of months to add
    var currentDateTime = new Date(convertToDateObject($rootScope.globals.currentUser.solDate)); // Convert current date string to Date object
  //  var nextDate = new Date(currentDateTime.getFullYear(), currentDateTime.getMonth() + numberOfMonths, currentDateTime.getDate());

    var convertedDate = new Date(currentDateTime.setMonth(parseInt(currentDateTime.getMonth()) + parseInt(numberOfMonths)));
    vm.maturityDate = $filter('date')(convertedDate, "dd/MM/yyyy");




//                    vm.accountOpeningDate =  $filter('date')($rootScope.globals.currentUser.systemDate, 'dd/MM/yyyy');

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
                   $state.go('rbi_bnd_subs_workFlw.add');
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
                       rbiBondSubscriptionWorkFlowService.getApplicationTorch(requestData).then(function(response){
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
                           rbiBondSubscriptionWorkFlowService.getApplicationTorch(requestData).then(function(response){
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

                               rbiBondSubscriptionWorkFlowService.getBrokerTorch(requestData).then(function(response){
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
           rbiBondSubscriptionWorkFlowService.getValidationAdd(requestData).then(function(response){
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
           rbiBondSubscriptionWorkFlowService.getValidationAdd(requestData).then(function(response){
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
           rbiBondSubscriptionWorkFlowService.getReferenceData(requestData).then(function(response){
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
           rbiBondSubscriptionWorkFlowService.getReferenceData(requestData).then(function(response){
              vm.modeOfInterestPaymentList = response.data.result;
            },function(error) {
               handleErrorResponse(error);
             });
        }




        function getInterestPaymentMode() {
           var requestData = {
                 'referenceId': 'INT_PAY_MODE'
             }
           rbiBondSubscriptionWorkFlowService.getReferenceData(requestData).then(function(response){
              vm.interestPaymentModeList = response.data.result;
            },function(error) {
               handleErrorResponse(error);
             });
        }

        function getRelationWithHolder() {
           var requestData = {
                 'referenceId': 'RELATIONSHIP'
             }
           rbiBondSubscriptionWorkFlowService.getReferenceData(requestData).then(function(response){
              vm.relationHolderList = response.data.result;
            },function(error) {
               handleErrorResponse(error);
             });
        }

        function getStateList() {
           var requestData = {
                 'referenceId': 'STATES OF INDIA'
             }
           rbiBondSubscriptionWorkFlowService.getReferenceData(requestData).then(function(response){
              vm.stateList = response.data.result;
            },function(error) {
               handleErrorResponse(error);
             });
        }
        function instrumentTypeForClearing() {
           var requestData = {
                 'referenceId': 'INSTRUMENT_TYPE'
             }
           rbiBondSubscriptionWorkFlowService.getReferenceData(requestData).then(function(response){
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

//            rbiBondSubscriptionWorkFlowService.getSubCodeList().then(function(response){
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
           rbiBondSubscriptionWorkFlowService.getValidationAdd(requestData).then(function(response){
              vm.accountNumberDetails = response.data.result;
              vm.maximumCashLimit = parseInt(vm.accountNumberDetails.cashLimitOut);
              if(vm.maximumCashLimit){
                 getCashValidation();
              }

              if(vm.accountNumberDetails.debitAccountNumberFlag == 'N'){
        		rbiBondSubscriptionWorkFlowService.getForacidAccountNumber(vm.accountNumberDetails.debitAccountNumber,vm.loginSolId).then(function(response){
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
        		rbiBondSubscriptionWorkFlowService.getForacidAccountNumber(vm.accountNumberDetails.creditAccountNumber,vm.loginSolId).then(function(response){
        		   vm.accountOpening.creditAccountNumber = response.data[0].forAccountId;
        		}, function(error) {
                               vm.loader = false;
                                  handleErrorResponse(error);
                                });
              }else {
                   vm.accountOpening.creditAccountNumber = vm.accountNumberDetails.creditAccountNumber;
              }
              if(vm.accountNumberDetails.ddPayAccountNumberFlag == 'N'){
        		rbiBondSubscriptionWorkFlowService.getForacidAccountNumber(vm.accountNumberDetails.ddPayAccountNumber,vm.loginSolId).then(function(response){
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
           rbiBondSubscriptionWorkFlowService.getValidationAdd(requestData).then(function(response){
              vm.accountNumberDetails = response.data.result;

              if(vm.accountNumberDetails.creditAccountNumberFlag == 'N'){
        		rbiBondSubscriptionWorkFlowService.getForacidAccountNumber(vm.accountNumberDetails.creditAccountNumber,vm.loginSolId).then(function(response){
        		   vm.accountOpening.creditAccountNumber = response.data[0].forAccountId;
        		}, function(error) {
                               vm.loader = false;
                                  handleErrorResponse(error);
                                });
              }else {
                   vm.accountOpening.creditAccountNumber = vm.accountNumberDetails.creditAccountNumber;
              }
              if(vm.accountNumberDetails.ddPayAccountNumberFlag == 'N'){
        		rbiBondSubscriptionWorkFlowService.getForacidAccountNumber(vm.accountNumberDetails.ddPayAccountNumber,vm.loginSolId).then(function(response){
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
                rbiBondSubscriptionWorkFlowService.getDepositAmountValidation(requestData).then(function(response){
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


               function getGuardianDetailsMetaData() {
                   var requestData = {
                         'referenceId': 'RELATION_WITH_MINOR'
                     }
                   rbiBondSubscriptionWorkFlowService.getReferenceData(requestData).then(function(response){
                      vm.guardianDetailsList = response.data.result;
                    },function(error) {
                       handleErrorResponse(error);
                     });
                }

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

//             Swal.fire({
//                                title : "Are you sure?",
//                                text : "Do you want to Really Add this record?",
//                                type : "warning",
//                                showCancelButton : true,
//                                cancelButtonClass : "btn-cancel",
//                                confirmButtonClass : "btn-primary",
//                                confirmButtonText : "Yes",
//                                closeOnConfirm : false
//                            },
//            function() {

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

                    rbiBondSubscriptionWorkFlowService.submitAddForm(requestData).then(
                                                                              (response) => {
                                                                                 vm.loader=false;
                                                                                 vm.responseResult = response.data.result;
                    //
                                                                                   var msgForAdd = "Bond Deposit Account Created Successfully \n Subscription Id : " + vm.responseResult.subscriptionId+ "\n GBM Tran Id : " +vm.responseResult.gbmTranId + "\n BLA No: " + vm.responseResult.blaNo;
                                                                                 Swal.fire("Added!", msgForAdd , "success");
                                                                                location.href = '#!/rbi_bnd_subs_workFlw/action/inquiry';
                                                                             },function(error) {
                                                                                   vm.loader = false;
                                                                                   handleErrorResponse(error);
                                                                                   $state.go('rbi_bnd_subs_workFlw.add');
                                                                             });


//                    rbiBondSubscriptionWorkFlowService.getCbsDetails(cbsRequestData).then(function(cbsResponse){
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
//                                                    $state.go('rbi_bnd_subs_workFlw.add');
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
//                                                     $state.go('rbi_bnd_subs_workFlw.add');
//                                                     vm.loader=false;
//                                             }
//                                             },function(error) {
//                                                          vm.loader = false;
//                                                          handleErrorResponse(error);
//                                                          $state.go('rbi_bnd_subs_workFlw.add');
//                                            });



          }  });


        }
        else{
            Swal.fire('Warning!', message, 'warning');
        }


        }
//var requestData = {
//         'scssCustomerId' : vm.accountOpening.customerId,
//         'transactionType' : vm.accountOpening.tranMode
//        };
//           rbiBondSubscriptionWorkFlowService.getValidationAdd(requestData).then(function(response){
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
                   rbiBondSubscriptionWorkFlowService.getCbsDetails(requestData).then(function(response)
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
//                   rbiBondSubscriptionWorkFlowService.getCbsDetails(requestData).then(function(response)
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
//                     rbiBondSubscriptionWorkFlowService.submitAddForm(requestData).then(
//                      (response) => {
//                             vm.loader=false;
//                               vm.scssAccountNumber = response.data.result.scssAccountNumber;
//                               vm.gbmTranId = response.data.result.gbmTranId;
//                               var msgForAdd = "Account Created Successfully \n Account No : " +vm.scssAccountNumber+ "\n GBM Tran Id : " +vm.gbmTranId;
//                             Swal.fire("Added!", msgForAdd , "success");
//                        location.href = '#!/rbi_bnd_subs_workFlw/action/inquiry';
//                     },function(error) {
//                       vm.loader = false;
//                          handleErrorResponse(error);
//                        });
//                        }
//                           else{
//                                 $timeout(Swal.fire("Warning", vm.message ,"warning"), 30000);
//                                 $state.go('rbi_bnd_subs_workFlw.add');
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
           rbiBondSubscriptionWorkFlowService.getVerifySelect(requestData).then(function(response){
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
           rbiBondSubscriptionWorkFlowService.getVerifySelect(requestData).then(function(response){
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
           rbiBondSubscriptionWorkFlowService.getInquiryDetails(requestData).then(function(response){
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
           rbiBondSubscriptionWorkFlowService.getInquiryDetails(requestData).then(function(response){
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
                       rbiBondSubscriptionWorkFlowService.getVerifySelect(requestData).then(function(response){
                       vm.loader = false;
                          vm.auditInfo = response.data.result;
                          vm.getSubscriptionDetails = vm.auditInfo.masterDetailsValueModel[0];
                          vm.getNomineeDetails = vm.auditInfo.nomineeValueModel;
                          vm.lastVerifiedBy = vm.auditInfo.lastVerifiedBy;

                        },function(error) {
                        vm.loader = false;
                           handleErrorResponse(error);
                           $state.go('rbi_bnd_subs_workFlw.inquiry');
                         });
        }

        function getInquirySelect() {
        vm.loader = true;

        var requestData = {
        'schemeCode' : vm.schemeCode,
        'blaNo' : vm.blaNo,
        'subscriptionId' : vm.subscriptionId,
        };
           rbiBondSubscriptionWorkFlowService.getInquiryDetails(requestData).then(function(response){
           vm.loader = false;
              vm.auditInfo = response.data.result;
              vm.getSubscriptionDetails = vm.auditInfo.masterDetailsValueModel[0];
              vm.getNomineeDetails = vm.auditInfo.nomineeValueModel;

            },function(error) {
            vm.loader = false;
               handleErrorResponse(error);
               $state.go('rbi_bnd_subs_workFlw.inquiry');
             });
        }

        /* Verify Submit */
        function submitVerifyForm() {
            Swal.fire({
                    title: "Are you sure?",
                    text: "Do you want to Really Verify this record?",
                    type: "warning",
                    showCancelButton: true,
                    cancelButtonClass: "btn-cancel",
                    confirmButtonClass: "btn-primary",
                    confirmButtonText: "Yes",
                    closeOnConfirm: false
                },

                function() {
                    vm.loader = true;
                    let requestData = {
                    'schemeCode': vm.auditInfo.schemeCode,
                    'subscriptionId' : vm.auditInfo.subscriptionId,
                    'lastVerifiedBy' : vm.lastVerifiedBy,
                    };

                    rbiBondSubscriptionWorkFlowService.submitVerifyForm(requestData).then(
                        (response) => {
                            vm.loader = false;
                            vm.responseResult = response.data.result;
                            if(vm.lastVerifiedBy){
                               vm.message = "BOND Deposit Account Verified Successfully";
                            }else{
                                if(vm.getSubscriptionDetails.C003){
                                   vm.message = "BOND Deposit Account Verified Successfully \n" + "Subscription ID: " + vm.responseResult.subscriptionId  + "\nGBS Tran ID: " + vm.responseResult.gbmTranId + "\nCBS Tran ID: " + vm.responseResult.cbsTranId + "\nBLA No: " + vm.getSubscriptionDetails.C003.oldValue;
                                }
                                if(vm.getSubscriptionDetails.C006){
                                   vm.message = "BOND Deposit Account Verified Successfully \n" + "Subscription ID: " + vm.responseResult.subscriptionId  + "\nGBS Tran ID: " + vm.responseResult.gbmTranId + "\nCBS Tran ID: " + vm.responseResult.cbsTranId + "\nBLA No: " + vm.getSubscriptionDetails.C006.oldValue;
                                }
                            }

                            Swal.fire("Verified!", vm.message ,"success");

                            location.href = '#!/rbi_bnd_subs_workFlw/action/inquiry';
                        }, function(error) {
                                       vm.loader = false;
                                          handleErrorResponse(error);
                                          location.href = '#!/rbi_bnd_subs_workFlw/action/inquiry';
                                        });
             }   }, function(error) {
                   	vm.loader = false;
                   	handleErrorResponse(error);
                });

        }

        /* Delete Submit */
        function submitDeleteForm() {
//            Swal.fire({
//                    title: "Are you sure?",
//                    text: "Do you want to Really Delete this record?",
//                    type: "warning",
//                    showCancelButton: true,
//                    cancelButtonClass: "btn-cancel",
//                    confirmButtonClass: "btn-primary",
//                    confirmButtonText: "Yes",
//                    closeOnConfirm: false
//                },
//
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
                    let requestData = {
                        'schemeCode': vm.auditInfo.schemeCode,
                        'subscriptionId' : vm.auditInfo.subscriptionId,
                        };

                    rbiBondSubscriptionWorkFlowService.submitDeleteForm(requestData).then(
                        (response) => {
                            vm.loader = false;
                            Swal.fire("Deleted!","BOND Deposit Account Deleted Successfully","success");
                            location.href = '#!/rbi_bnd_subs_workFlw/action/inquiry';
                        }, function(error) {
                                       vm.loader = false;
                                          handleErrorResponse(error);
                                        });
              }  }, function(error) {
                   	vm.loader = false;
                   	handleErrorResponse(error);
                });

        }



		function getModifySelect(){
//        vm.scssAccountDetailsOut=[];
//        vm.transactionDetailsOut=[];
//        vm.instrumentDetailsOut=[];
//        vm.neftDetailsOut =[];
//        vm.nomineeDetails =[];
//        vm.mofFlag = false;
		vm.loader = true;
                var requestData = {
                            'schemeCode' : vm.schemeCode,
                          'blaNo' : vm.blaNo,
                          'subscriptionId' : vm.subscriptionId,
                };
		    rbiBondSubscriptionWorkFlowService.getModifySelect(requestData).then(function(response){

              vm.modifyList = response.data.result;
              vm.getDetails = vm.modifyList.masterDetailsValueModel[0];
              vm.nominationDetails = vm.modifyList.nomineeValueModel;

              vm.bondCashAccount = vm.modifyList.bondCashAccount;
              vm.bondClearingAccount = vm.modifyList.bondClearingAccount;
              vm.bondCollectionAccount = vm.modifyList.bondCollectionAccount;

              vm.ddPoAccountNo = vm.modifyList.ddPoAccountNo;
              vm.neftCreditAccNo = vm.modifyList.neftCreditAccNo;
              vm.depositMultiples = vm.modifyList.depositMultiples;

              vm.bondCashLimit = vm.modifyList.cashLimitForCash;
              vm.bondClearingTransferLimit = vm.modifyList.cashLimitForClearingTransfer;
              vm.depositMultiples = vm.modifyList.depositMultiples;
              vm.minCashLimit = vm.modifyList.minCashLimit;



            if(vm.getDetails.C036){
                      vm.getDetails.C036.oldValue =  convertToDateObject(vm.getDetails.C036.oldValue);
            }
            if(vm.getDetails.C040){
                     vm.getDetails.C040.oldValue =  convertToDateObject(vm.getDetails.C040.oldValue);
            }
            if(vm.getDetails.C037){
                     vm.getDetails.C037.oldValue =  parseInt(vm.getDetails.C037.oldValue);
            }


             angular.forEach(vm.nominationDetails, function(item) {
                  if(item.C017){
                       item.C017.oldValue =  convertToDateObject(item.C017.oldValue);
                  }
                  if(item.C010){
                         item.C010.oldValue =  parseInt(item.C010.oldValue);
                    }
                    if(item.C011){
                         item.C011.oldValue =  parseInt(item.C011.oldValue);
                    }
                    if(item.C025){
                           item.C025.oldValue =  parseInt(item.C025.oldValue);
                      }
                      if(item.C026){
                             item.C026.oldValue =  parseInt(item.C026.oldValue);
                        }
             });



             vm.loader = false;


            },function(error){
                vm.loader = false;
                handleErrorResponse(error);
                $state.go('rbi_bnd_subs_workFlw.modify');
            })
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
           rbiBondSubscriptionWorkFlowService.getModifySelect(requestData).then(function(response){
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
           rbiBondSubscriptionWorkFlowService.getModifySelect(requestData).then(function(response){
              vm.loader = false;
              angular.element('#accountNumberModal').modal('toggle');
              vm.scssAccountNumberList = response.data.result.scssAccountNumberOut;
            },function(error) {
               vm.loader = false;
               handleErrorResponse(error);
             });
        }

        function submitModifyNominationForm() {
//            calculateSum();
//            vm.nominationDetailsList=[];
            if(vm.total != 100){
                var msg='Sum of sharing percentage should be equal to 100';
                Swal.fire('Warning!', msg, 'warning');
/*                for(var i = 0; i < vm.nominationDetails.length; i++){
                   vm.nominationDetails[i].sharePercentage = '';
                   vm.total = '';
                }*/
            }else{
//                 Swal.fire({
//                    title : "Are you sure?",
//                    text : "Do you want to Really Modify this record?",
//                    type : "warning",
//                    showCancelButton : true,
//                    cancelButtonClass : "btn-cancel",
//                    confirmButtonClass : "btn-primary",
//                    confirmButtonText : "Yes",
//                    closeOnConfirm : false
//                },
//                function() {

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
                        if(vm.accountOpening.tranMode != 'T'){
                        requestData.branchId = vm.loginSolId;
                        } else{
                        requestData.branchId = vm.accountOpening.interSolId;
                        }
                        requestData.depositAmount = vm.accountOpening.depositAmount;
                        requestData.isNominee = vm.accountOpening.isNomineeRequired;
                        requestData.transactionType = vm.accountOpening.tranMode;
                        requestData.neftOperativeAccountNumber = vm.accountOpening.operativeAccNo;
                        requestData.neftIfscCode = vm.accountOpening.codeOfCreditBank;
                        requestData.neftNameOfThePayee = vm.accountOpening.payeeName;
                        requestData.interestPaymentMode = vm.accountOpening.interestPaymentMode;
                        if(vm.accountOpening.tranMode=='C' || vm.accountOpening.tranMode=='T')
                        {
                        requestData.micrCode = null;
                        }else{
                        requestData.micrCode = vm.accountOpening.sortCode;
                        }
                        vm.accountOpeningMaster.C002= vm.accountOpening.holderNum;
                        vm.accountOpeningMaster.C005=vm.accountOpening.depositAmount;
                        vm.accountOpeningMaster.C004=vm.accountOpening.accountHolderType;
                        //vm.accountOpeningMaster.C009=vm.accountOpening.interestPaymentMode;
                        vm.accountOpeningMaster.C010=vm.accountOpening.C010;

                        requestData.accountDetails = [convertToColumnValuesArray(vm.accountOpeningMaster)];
                        angular.forEach(vm.nominationDetails,function(item)
                        {
                        item.C011 = parseInt(item.C011);
                        item.C012 = parseInt(item.C012);
                        item.C025 = parseInt(item.C025);
                        item.C017 = $filter('date')(item.C017,'DD/MMM/YYYY');
                        vm.nominationDetailsList.push(convertToColumnValuesArray(item));
                        })
                        requestData.nomineeDetails = vm.nominationDetailsList;

                        requestData.creditAccountNumber = vm.accountOpening.creditAccountNumber;
                        requestData.debitAccountNumber = vm.accountOpening.acctNum;
                         if(vm.accountOpening.interestPaymentMode == 'PM001')
                          {
                                requestData.creditAccountNumber = vm.accountOpening.interestCreditAccountNumber;
                          }
                        requestData.setNum=vm.setId;
                        requestData.instrumentType = vm.accountOpening.instType;
                        requestData.instrumentNumber = vm.accountOpening.instNumber;
                        requestData.instrumentDate = $filter('date')(vm.accountOpening.instDate, 'DD/MMM/YYYY');
                        requestData.tranCode = vm.accountOpening.tranCode;
                        requestData.zoneCode = vm.accountOpening.zoneCode;
                        requestData.instrumentBankCode = vm.accountOpening.bankCode;
                        requestData.instrumentBranchCode = vm.accountOpening.branchCode;
                        requestData.zoneDate = $filter('date')(vm.accountOpening.zoneDate, 'DD/MMM/YYYY');
                        requestData.neftBankCode = vm.accountOpening.zoneBankCode;
                        requestData.neftBranchCode = vm.accountOpening.zoneBranchCode;
                        //requestData.creditAccountNumberType = 'N'; //vm.creditAccountNumberType;//
                        //requestData.debitAccountNumberType = 'N';// vm.debitAccountNumberType;//

                   rbiBondSubscriptionWorkFlowService.getCbsDetails(requestData).then(function(response)
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

                         rbiBondSubscriptionWorkFlowService.getModifySubmit(requestData).then(
                          (response) => {
                             vm.loader=false;
                             Swal.fire("Modified!",  "Details Modified Successfully" , "success");
                            location.href = '#!/rbi_bnd_subs_workFlw/action/inquiry';
                         },function(error) {
                           vm.loader = false;
                              handleErrorResponse(error);
                            });
                            }
                           else{
                                 $timeout(Swal.fire("Warning", vm.message ,"warning"), 30000);
                                 $state.go('rbi_bnd_subs_workFlw.modify');
                                 vm.loader=false;
                         }
                         },function(error) {
                                       vm.loader = false;
                                          handleErrorResponse(error);
                                        });

            }    });
            }
        }


/*        function getCbsDetails(requestData){

                   rbiBondSubscriptionWorkFlowService.getCbsDetails(requestData).then(function(response)
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
                                if(vm.creditAccountNumber == vm.debitAccountNumber)
                        {
                                vm.debitAccountType = vm.creditAccountType;
                        }

             }
             },function(error) {
               vm.loader = false;
                  handleErrorResponse(error);
                });

        }*/

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

        function submitModify() {
                var proceed = false;
                var messageM = '';
                if(vm.getDetails.C045.oldValue == 'Y'){
                    if(vm.total != 100){
                        proceed = false;
                        messageM = 'Sum of sharing percentage should be equal to 100';
                    }else{
                        proceed = true;
                    }
                }else{
                    proceed = true;
                }

            if(proceed){

//                Swal.fire({
//                                    title : "Are you sure?",
//                                    text : "Do you want to Really Modify this record?",
//                                    type : "warning",
//                                    showCancelButton : true,
//                                    cancelButtonClass : "btn-cancel",
//                                    confirmButtonClass : "btn-primary",
//                                    confirmButtonText : "Yes",
//                                    closeOnConfirm : false
//                                },
//                function() {




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

                var requestData =  {
                    'schemeCode' : vm.schemeCode,
                };
                var subsList = [];

                if(vm.getDetails.C031.oldValue == 'CL'){
                    requestData.debitAccNo = vm.bondClearingAccount;
                }
                if(vm.getDetails.C031.oldValue == 'C'){
                    requestData.debitAccNo = vm.bondCashAccount;
                }
                if(vm.getDetails.C031.oldValue == 'T'){
                    requestData.debitAccNo = vm.getDetails.C032.oldValue;
                }

                if(vm.getDetails.C036){
                    vm.getDetails.C036.oldValue = $filter('date')(vm.getDetails.C036.oldValue, "dd-MMM-yyyy");
                }
                if(vm.getDetails.C017){
                 vm.getDetails.C017.oldValue = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.getDetails.C017.oldValue));
                }
                if(vm.getDetails.C020){
                 vm.getDetails.C020.oldValue = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.getDetails.C020.oldValue));
                }
                if(vm.getDetails.C018){
                 vm.getDetails.C018.oldValue = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.getDetails.C018.oldValue));
                }
                if(vm.getDetails.C040){
                   vm.getDetails.C040.oldValue = $filter('date')(vm.getDetails.C040.oldValue, "dd-MMM-yyyy");
                }


                angular.forEach(vm.nominationDetails, function(item) {

                   if(item.C017){
                        item.C017.oldValue = $filter('date')(item.C017.oldValue, "dd-MMM-yyyy");
                   }
                });

                vm.subscriptionModalListJSON = JSON.parse(JSON.stringify(vm.getDetails));
                subsList.push(convertToColumnValuesArrayOldNew(vm.subscriptionModalListJSON));

                requestData.tranDetails = subsList;

                if(vm.nominationDetails){
                    var nomList = [];
                    vm.nominationModalListJSON = JSON.parse(JSON.stringify(vm.nominationDetails));
                    nomList.push(convertToColumnValuesArrayOldNew(vm.nominationModalListJSON));
                    requestData.nomineeDetails = nomList;
                }


                 rbiBondSubscriptionWorkFlowService.getModifySubmit(requestData).then((response) => {
                      vm.loader=false;
                      var message = '';
                      if(vm.modifyList.verifiedStatus == 'N' && vm.modifyList.lastVerifiedBy){
                             message = "Bond Subscription – Mode of Interest Payment/Principal Repayment details Modified Successfully";
                      }else{
                             message = "Bond Subscription Workflow Modified Successfully";
                      }

                     Swal.fire("Modified!", message , "success");
                     location.href = '#!/rbi_bnd_subs_workFlw/action/inquiry';
                  },function(error) {
                    vm.loader = false;
                       handleErrorResponse(error);
                     });

//                     requestData.scssCustomerId = vm.accountOpening.customerId;
//                     requestData.scssAccountNumber = vm.scssAccountNumber;
//                        if(vm.accountOpening.tranMode != 'T'){
//                        requestData.branchId = vm.loginSolId;
//                        } else{
//                        requestData.branchId = vm.accountOpening.interSolId;
//                        }
//                     requestData.depositAmount = vm.accountOpening.depositAmount;
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
//                       requestData.accountDetails = [convertToColumnValuesArray(vm.accountOpeningMaster)];
//                       requestData.nomineeDetails =[];
//                       requestData.creditAccountNumber = vm.accountOpening.creditAccountNumber;
//                       requestData.debitAccountNumber = vm.accountOpening.acctNum;
//                       if(vm.accountOpening.interestPaymentMode == 'PM001')
//                       {
//                               requestData.creditAccountNumber = vm.accountOpening.interestCreditAccountNumber;
//                         }
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
//                   rbiBondSubscriptionWorkFlowService.getCbsDetails(requestData).then(function(response)
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
//                     rbiBondSubscriptionWorkFlowService.getModifySubmit(requestData).then(
//                      (response) => {
//                         vm.loader=false;
//                        Swal.fire("Modified!",  "Details Modified Successfully" , "success");
//                        location.href = '#!/rbi_bnd_subs_workFlw/action/inquiry';
//                     },function(error) {
//                       vm.loader = false;
//                          handleErrorResponse(error);
//                        });
//                        }
//                           else{
//                                 $timeout(Swal.fire("Warning", vm.message ,"warning"), 30000);
//                                 $state.go('rbi_bnd_subs_workFlw.modify');
//                                 vm.loader=false;
//                         }
//                     },function(error) {
//                       vm.loader = false;
//                          handleErrorResponse(error);
//                        });

            }    });

            }else{
                 Swal.fire('Warning!', messageM, 'warning');
            }



        }
        function submitModifyInterestDetail() {

//                 Swal.fire({
//                    title : "Are you sure?",
//                    text : "Do you want to Really Modify this record?",
//                    type : "warning",
//                    showCancelButton : true,
//                    cancelButtonClass : "btn-cancel",
//                    confirmButtonClass : "btn-primary",
//                    confirmButtonText : "Yes",
//                    closeOnConfirm : false
//                },
//                function() {



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
                        if(vm.accountOpening.tranMode != 'T'){
                        requestData.branchId = vm.loginSolId;
                        } else{
                        requestData.branchId = vm.accountOpening.interSolId;
                        }
                     requestData.depositAmount = vm.accountOpening.depositAmount;
                     requestData.isNominee = vm.accountOpening.isNomineeRequired;
                     requestData.transactionType = vm.accountOpening.tranMode;
                     requestData.neftOperativeAccountNumber = vm.accountOpening.operativeAccNo;
                     requestData.neftIfscCode = vm.accountOpening.codeOfCreditBank;
                     requestData.neftNameOfThePayee = vm.accountOpening.payeeName;
                     requestData.interestPaymentMode = vm.accountOpening.interestPaymentMode;
                     if(vm.accountOpening.tranMode=='C' || vm.accountOpening.tranMode=='T')
                     {
                     requestData.micrCode = null;
                     }else{
                     requestData.micrCode = vm.accountOpening.sortCode;
                     }
                        vm.accountOpeningMaster.C002= vm.accountOpening.holderNum;
                        vm.accountOpeningMaster.C005=vm.accountOpening.depositAmount;
                        vm.accountOpeningMaster.C004=vm.accountOpening.accountHolderType;
                        //vm.accountOpeningMaster.C009=vm.accountOpening.interestPaymentMode;
                        vm.accountOpeningMaster.C010=vm.accountOpening.C010;
                        vm.accountOpeningMaster.C012 = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.getDetails.C012.oldValue));
                        vm.accountOpeningMaster.C017 = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.getDetails.C017.oldValue));
                        vm.accountOpeningMaster.C020 = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.getDetails.C020.oldValue));
                        vm.accountOpeningMaster.C036 = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.getDetails.C036.oldValue));
                        vm.accountOpeningMaster.C040 = $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.getDetails.C036.oldValue));

                       requestData.accountDetails = [convertToColumnValuesArray(vm.accountOpeningMaster)];
                       requestData.nomineeDetails =[];
                       requestData.creditAccountNumber = vm.accountOpening.creditAccountNumber;
                       requestData.debitAccountNumber = vm.accountOpening.acctNum;
                        requestData.setNum=vm.setId;
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

                     rbiBondSubscriptionWorkFlowService.getModifySubmit(requestData).then(
                      (response) => {
                         vm.loader=false;
                        Swal.fire("Modified!",  "Details Modified Successfully" , "success");
                        location.href = '#!/rbi_bnd_subs_workFlw/action/inquiry';
                     },function(error) {
                       vm.loader = false;
                          handleErrorResponse(error);
                        });
                 }     });

        }

        function getAccountPage(){
           vm.accountDetails = true;
           vm.nomination = false;
           if($state.current.name == 'rbi_bnd_subs_workFlw.inquiry_detail'){
           vm.matrix = false;
           }
        }
        function getNominationPage(){
           vm.accountDetails = false;
           vm.nomination = true;
           if($state.current.name == 'rbi_bnd_subs_workFlw.inquiry_detail'){
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

//    	function getBranchCodeDetails(bankCode){
//    	vm.branchCodeList = [];
//    		commonService.getBranchCode(bankCode,"",function(response){
//    				vm.branchCodeList = response.data;
//    				getCodeDescription();
//            });
//    	}

//    	if ($state.current.name == 'rbi_bnd_subs_workFlw.verify' ||
//    	    $state.current.name == 'rbi_bnd_subs_workFlw.delete' ||
//    	    $state.current.name == 'rbi_bnd_subs_workFlw.inquiry'){
//              getTranCodeDetails();
//              getBankCodeDetails();
//              getZoneCodeDetails();
//              getSubCodeList();
//    	}

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

    }

})();