(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("freezeUnfreezeController", freezeUnfreezeController);

  freezeUnfreezeController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$rootScope",
    "$stateParams",
    "$location",
    "$timeout",
    "freezeUnfreezeService",
    "commonService",
  ];

  function freezeUnfreezeController($scope, $filter, $state, $rootScope, $stateParams, $location, $timeout, freezeUnfreezeService, commonService) {
    var vm = this;
    vm.getAddValidation = getAddValidation;
    vm.addSubmit = addSubmit;
    vm.getInquiryDetails = getInquiryDetails;
    vm.modifyDetails = modifyDetails;
    vm.modifySubmit = modifySubmit;
    vm.verifyDetails = verifyDetails;
    vm.verifySubmit = verifySubmit;
    vm.deleteSubmit = deleteSubmit;
    vm.rejectReason = rejectReason;
    vm.getRemarksList = getRemarksList;
    vm.validateInput = validateInput;
    vm.validatePaste = validatePaste;


    if ($state.current.name == "freeze_unfreeze.add_detail") {
      if ($stateParams.obj) {
          vm.schemeCode = $stateParams.obj.schemeCode;
          vm.schemeName = $stateParams.obj.schemeName;
          vm.name = $stateParams.obj.name;
          vm.panNoName = $stateParams.obj.panNoName;
          vm.modeOfHolding = $stateParams.obj.modeOfHolding;
          vm.blaNo = $stateParams.obj.blaNo;
          vm.subId = $stateParams.obj.subId;
           getAddValidation('D');
//            vm.responseResultForAdd = [];
//            let array = {};
//            array.name = 'john';
//            array.dob = '09/06/2000';
//            array.blaNo = 'BLA9087651';
//            array.subscriptionId = '1234567890';
//            array.bondType = 'Joint';
//            array.subscriptionAmount = '2025';
//            array.accountOpenDate = '09/01/2020';
//            array.maturityDate = '09/01/2052';
//            array.lastInterestPaid = '09/01/2025';
//            vm.responseResultForAdd.push(array);
      }
    } else if ($state.current.name == "freeze_unfreeze.modify_detail") {
      if ($stateParams.obj) {
         vm.schemeCode = $stateParams.obj.schemeCode;
         vm.schemeName = $stateParams.obj.schemeName;
         vm.name = $stateParams.obj.name;
         vm.panNoName = $stateParams.obj.panNoName;
         vm.modeOfHolding = $stateParams.obj.modeOfHolding;
         vm.blaNo = $stateParams.obj.blaNo;
         vm.subId = $stateParams.obj.subId;
         modifyDetails('D');
//            vm.responseResultForMod = [];
//            let arrayOfMod = {};
//            arrayOfMod.name = 'john';
//            arrayOfMod.dob = '09/06/2000';
//            arrayOfMod.blaNo = 'BLA9087651';
//            arrayOfMod.subscriptionId = '1234567890';
//            arrayOfMod.bondType = 'Joint';
//            arrayOfMod.subscriptionAmount = '2025';
//            arrayOfMod.accountOpenDate = '09/01/2020';
//            arrayOfMod.maturityDate = '09/01/2052';
//            arrayOfMod.lastInterestPaid = '09/01/2025';
//            vm.responseResultForMod.push(arrayOfMod);
//            vm.amountFreeze = 'Y';
//            vm.interestFreeze = 'N';
//            vm.transferAnotherBank = 'Y';
//            vm.makerRemarks = 'Yes Hello World From Java Geek';

      }
    } else if ($state.current.name == "freeze_unfreeze.verify_detail") {
      if ($stateParams.obj) {
         vm.schemeCode = $stateParams.obj.schemeCode;
         vm.schemeName = $stateParams.obj.schemeName;
         vm.name = $stateParams.obj.name;
         vm.panNoName = $stateParams.obj.panNoName;
         vm.modeOfHolding = $stateParams.obj.modeOfHolding;
         vm.blaNo = $stateParams.obj.blaNo;
         vm.subId = $stateParams.obj.subId;
         verifyDetails('D');
//         vm.responseResultForVer = [];
//         let arrayOfVer = {};
//         arrayOfVer.name = 'john';
//         arrayOfVer.dob = '09/06/2000';
//         arrayOfVer.blaNo = 'BLA9087651';
//         arrayOfVer.subscriptionId = '1234567890';
//         arrayOfVer.bondType = 'Joint';
//         arrayOfVer.subscriptionAmount = '2025';
//         arrayOfVer.accountOpenDate = '09/01/2020';
//         arrayOfVer.maturityDate = '09/01/2052';
//         arrayOfVer.lastInterestPaid = '09/01/2025';
//         vm.responseResultForMod.push(arrayOfVer);
//         vm.amountFreeze = 'Y';
//         vm.interestFreeze = 'N';
//         vm.transferAnotherBank = 'Y';
//         vm.makerRemarks = 'Yes Hello World From Java Geek';
      }
      } else if ($state.current.name == "freeze_unfreeze.inquiry_detail") {
      if ($stateParams.obj) {
         vm.schemeCode = $stateParams.obj.schemeCode;
         vm.schemeName = $stateParams.obj.schemeName;
         vm.name = $stateParams.obj.name;
         vm.panNoName = $stateParams.obj.panNoName;
         vm.modeOfHolding = $stateParams.obj.modeOfHolding;
         vm.blaNo = $stateParams.obj.blaNo;
         vm.subId = $stateParams.obj.subId;
         getInquiryDetails('D');
//        vm.responseResultForInquiry = [];
//            let arrayInq = {};
//            arrayInq.name = 'john';
//            arrayInq.dob = '09/06/2000';
//            arrayInq.blaNo = 'BLA9087651';
//            arrayInq.subscriptionId = '1234567890';
//            arrayInq.bondType = 'Joint';
//            arrayInq.subscriptionAmount = '2025';
//            arrayInq.accountOpenDate = '09/01/2020';
//            arrayInq.maturityDate = '09/01/2052';
//            arrayInq.lastInterestPaid = '09/01/2025';
//            vm.responseResultForInquiry.push(arrayInq);
//            vm.amountFreeze = 'Y';
//            vm.interestFreeze = 'Y';
//            vm.transferAnotherBank = 'Y';
//            vm.makerRemarks = 'Yes Hello World From Java Geek';
//            vm.checkerRemarks = 'No Hello World From JS Geek';
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
        function getAddValidation(flag){
                vm.loader = true;
                var requestData = {};
                if(flag == 'S'){
                   requestData.schemeCode = null;
                   requestData.pageType = 'scheme';
                } else if (flag == 'P'){
                    requestData.schemeCode = vm.schemeCode;
                    requestData.panNoName = vm.panNoName;
                    requestData.name = vm.name;
                    requestData.pageType = 'pan';
                } else if (flag == 'B'){
                    requestData.schemeCode = vm.schemeCode;
                    requestData.panNoName = vm.panNoName;
                    requestData.name = vm.name;
                    requestData.blaNo = vm.blaNo;
                    requestData.pageType = 'bla';
                } else if (flag == 'BS'){
                    requestData.schemeCode = vm.schemeCode;
                    requestData.panNoName = vm.panNoName;
                    requestData.name = vm.name;
                    requestData.blaNo = vm.blaNo;
                    requestData.subId = vm.subId;
                    requestData.pageType = 'sub';
                } else if (flag == 'D') {
                    requestData.schemeCode = vm.schemeCode;
                    requestData.panNoName = vm.panNoName;
                    requestData.name = vm.name;
                    requestData.blaNo = vm.blaNo;
                    requestData.subId = vm.subId;
                    requestData.pageType = 'scheme';
//                    requestData.fromDate = $filter('date')(vm.fromDate, "dd-MMM-yyyy");
//                    requestData.toDate = $filter('date')(vm.toDate, "dd-MMM-yyyy");
                }

           freezeUnfreezeService.getValidationAdd(requestData).then(function(response){
                if (flag == 'S'){
                    vm.schemeList = response.data.result;
                    angular.element('#schemeModel').modal('toggle');
                } else if (flag == 'P'){
                    vm.PanTorch = response.data.result;
                    angular.element('#panModel').modal('toggle');
                } else if (flag == 'B'){
                    vm.subscriptionList = response.data.result;
                    angular.element('#subscriptionModel').modal('toggle');
                } else if (flag == 'BS'){
                    vm.subscriptionList = response.data.result;
                    angular.element('#subscriptionIdModel').modal('toggle');
                } else if (flag == 'D'){
                      vm.responseResultForAdd = response.data.result;
                }
                vm.loader = false;
            },function(error) {
                vm.loader = false;
                handleErrorResponse(error);
                $state.go('freeze_unfreeze.add');
           });
        }

        function addSubmit(closureRemarks) {
//             Swal.fire({
//                title : "Are you sure?",
//                text : "Do you want to Really Add this record?",
//                type : "warning",
//                showCancelButton : true,
//                cancelButtonClass : "btn-cancel",
//                confirmButtonClass : "btn-primary",
//                confirmButtonText : "Yes",
//                closeOnConfirm : false
//            },
//            function() {


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
                vm.loader= true;
                var requestData = {};
                vm.addArray = [];
                 vm.addArray.push(vm.subId);
                 requestData.schemeCode = vm.schemeCode;
                 requestData.panNoName = vm.panNoName;
                 requestData.name = vm.name;
                 requestData.blaNo = vm.blaNo;
                 requestData.subId = vm.subId;
                 requestData.listOfStrings = vm.addArray;
                 requestData.amountFreeze = vm.amountFreeze;
                 requestData.interestFreeze = vm.interestFreeze;
                 requestData.transferAnotherBank = vm.transferAnotherBank;
                 requestData.makerRemarks = vm.remarks;
                 requestData.pageType = 'add';

                freezeUnfreezeService.getValidationAdd(requestData).then( (response) => {
                    vm.loader=false;
                    vm.addResultResponse = response.data.message;
                    if( vm.addResultResponse == 'SUCCESS'){
                        Swal.fire("Success!", "Details Added successfully’ ", "success");
                       location.href = '#!/freeze_unfreeze/action/inquiry';
                    }
                 },function(error) {



                       vm.loader = false;
                              		  if(error.data.message == 'No Records Found' | error.data.message == 'NDF')
                              		  {
                              		  vm.noDataFound = error.data.message;
                              		  Swal.fire("Warning!",vm.noDataFound,"warning");
                              		   $state.go("freeze_unfreeze.add");
                              		  }
                              		  else if(error.statusCode == '0' | error.status =='500')
                              		  {
                              		  Swal.fire("Warning!",error.message,"warning");
                              		   $state.go("freeze_unfreeze.add");
                              		  }else{  handleErrorResponse(error);
                              		  $state.go("freeze_unfreeze.add");
                              		  }


                });
          }  });
        }




        function getInquiryDetails(flag) {
            vm.loader = true;
            var requestData = {};
            if(flag == 'S'){
               requestData.schemeCode = null;
               requestData.pageType = 'scheme';
            } else if (flag == 'P'){
                requestData.schemeCode = vm.schemeCode;
                requestData.panNoName = vm.panNoName;
                requestData.name = vm.name;
                requestData.pageType = 'pan';
            } else if (flag == 'B'){
                requestData.schemeCode = vm.schemeCode;
                requestData.panNoName = vm.panNoName;
                requestData.name = vm.name;
                requestData.blaNo = vm.blaNo;
                requestData.pageType = 'bla';
            } else if (flag == 'BS'){
                  requestData.schemeCode = vm.schemeCode;
                  requestData.panNoName = vm.panNoName;
                  requestData.name = vm.name;
                  requestData.blaNo = vm.blaNo;
                  requestData.subId = vm.subId;
                  requestData.pageType = 'sub';
            }else if (flag == 'D'){
                requestData.schemeCode = vm.schemeCode;
                requestData.panNoName = vm.panNoName;
                requestData.name = vm.name;
                requestData.blaNo = vm.blaNo;
                requestData.subId = vm.subId;
                requestData.pageType = 'scheme';

            }
           freezeUnfreezeService.inquiryDetails(requestData).then(function(response){
               if (flag == 'S'){
                   vm.schemeList = response.data.result;
                   angular.element('#schemeModel').modal('toggle');
               } else if (flag == 'P'){
                   vm.PanTorch = response.data.result;
                   angular.element('#panModel').modal('toggle');
               } else if (flag == 'B'){
                   vm.subscriptionList = response.data.result;
                   angular.element('#subscriptionModel').modal('toggle');
               } else if (flag == 'BS'){
                 vm.subscriptionList = response.data.result;
                 angular.element('#subscriptionIdModel').modal('toggle');
               } else if (flag == 'D'){
                     vm.resultSet = response.data.result;
                     vm.audit = response.data.result;
                     vm.responseResultForInquiry = response.data.result.freezeUnfreezeDTOList;
                     vm.preOrpTyp = response.data.result.preOrpTyp;


                     vm.amountFreezeOldValue = response.data.result.amountFreezeOldValue;
                     vm.amountFreezeNewValue = response.data.result.amountFreezeNewValue;
                     vm.interestFreezeOldValue = response.data.result.interestFreezeOldValue;
                     vm.interestFreezeNewValue = response.data.result.interestFreezeNewValue;
                     vm.transferAnotherBankOldValue = response.data.result.transferAnotherBankOldValue;
                     vm.transferAnotherBankNewValue = response.data.result.transferAnotherBankNewValue;

                    vm.makerRemarksOldValue = response.data.result.makerRemarksOldValue;
                    vm.makerRemarksNewValue = response.data.result.makerRemarksNewValue;
                    vm.checkerRemarksOldValue = response.data.result.checkerRemarksOldValue;
                    vm.checkerRemarksNewValue = response.data.result.checkerRemarksNewValue;

//                    vm.makerRemarks =  vm.resultSet.makerRemarks;
//                    vm.checkerRemarks =  vm.resultSet.checkerRemarks;
               }
                vm.loader = false;
           },function(error) {
               vm.loader = false;
               handleErrorResponse(error);
               $state.go('freeze_unfreeze.inquiry');
            });
        }

        function modifyDetails(flag){
                vm.loader = true;
                var requestData = {};
                if(flag == 'S'){
                   requestData.schemeCode = null;
                   requestData.pageType = 'scheme';
                } else if (flag == 'P'){
                    requestData.schemeCode = vm.schemeCode;
                    requestData.panNoName = vm.panNoName;
                    requestData.name = vm.name;
                    requestData.pageType = 'pan';
                } else if (flag == 'B'){
                    requestData.schemeCode = vm.schemeCode;
                    requestData.panNoName = vm.panNoName;
                    requestData.name = vm.name;
                    requestData.blaNo = vm.blaNo;
                    requestData.pageType = 'bla';
                } else if (flag == 'BS'){
                    requestData.schemeCode = vm.schemeCode;
                    requestData.panNoName = vm.panNoName;
                    requestData.name = vm.name;
                    requestData.blaNo = vm.blaNo;
                    requestData.subId = vm.subId;
                    requestData.pageType = 'sub';
                } else if (flag == 'D') {
                    requestData.schemeCode = vm.schemeCode;
                    requestData.panNoName = vm.panNoName;
                    requestData.name = vm.name;
                    requestData.blaNo = vm.blaNo;
                    requestData.subId = vm.subId;
                    requestData.pageType = 'scheme';
                }

           freezeUnfreezeService.modifyDetails(requestData).then(function(response){
                if (flag == 'S'){
                    vm.schemeList = response.data.result;
                    angular.element('#schemeModel').modal('toggle');
                } else if (flag == 'P'){
                    vm.PanTorch = response.data.result;
                    angular.element('#panModel').modal('toggle');
                } else if (flag == 'B'){
                    vm.subscriptionList = response.data.result;
                    angular.element('#subscriptionModel').modal('toggle');
                } else if (flag == 'BS'){
                    vm.subscriptionList = response.data.result;
                    angular.element('#subscriptionIdModel').modal('toggle');
                } else if (flag == 'D'){
                     vm.resultSet = response.data.result;
                     vm.responseResultForMod = response.data.result.freezeUnfreezeDTOList;
                     vm.audit = response.data.result;
                     vm.amountFreeze = vm.resultSet.amountFreeze;
                     vm.interestFreeze = vm.resultSet.interestFreeze;
                     vm.transferAnotherBank = vm.resultSet.transferAnotherBank;

//                     vm.amountFreezeOldValue = response.data.result.amountFreezeOldValue;
//                     vm.amountFreezeNewValue = response.data.result.amountFreezeNewValue;
//                     vm.interestFreezeOldValue = response.data.result.interestFreezeOldValue;
//                     vm.interestFreezeNewValue = response.data.result.interestFreezeNewValue;
//                     vm.transferAnotherBankOldValue = response.data.result.transferAnotherBankOldValue;
//                     vm.transferAnotherBankNewValue = response.data.result.transferAnotherBankNewValue;

//                      if(vm.amountFreezeOldValue != null)
//                            vm.amountFreeze = vm.amountFreezeOldValue;
//                      else
//                            vm.amountFreeze = vm.amountFreezeNewValue;
//
//                      if(vm.interestFreezeOldValue != null)
//                           vm.interestFreeze = vm.interestFreezeOldValue;
//                      else
//                           vm.interestFreeze = vm.interestFreezeNewValue;
//
//                      if(vm.transferAnotherBankOldValue != null)
//                            vm.transferAnotherBank = vm.transferAnotherBankOldValue;
//                      else
//                          vm.transferAnotherBank = vm.transferAnotherBankNewValue;

                     vm.makerRemarks =  vm.resultSet.makerRemarks;
                     vm.checkerRemarks =  vm.resultSet.checkerRemarks;
                }
                vm.loader = false;
            },function(error) {
                vm.loader = false;
                handleErrorResponse(error);
                $state.go('freeze_unfreeze.modify');
           });
        }

        function modifySubmit() {
//             Swal.fire({
//                title : "Are you sure?",
//                text : "Do you want to Really Modify this record?",
//                type : "warning",
//                showCancelButton : true,
//                cancelButtonClass : "btn-cancel",
//                confirmButtonClass : "btn-primary",
//                confirmButtonText : "Yes",
//                closeOnConfirm : false
//            },
//            function() {


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
                vm.loader= true;
                var requestData = {};
                vm.selectedAccountsForMod = [];
                vm.selectedAccountsForMod.push(vm.subId);
                 requestData.schemeCode = vm.schemeCode;
                 requestData.panNoName = vm.panNoName;
                 requestData.name = vm.name;
                 requestData.blaNo = vm.blaNo;
                 requestData.listOfStrings = vm.selectedAccountsForMod;
                 requestData.makerRemarks = vm.makerRemarks;
                 requestData.pageType = 'modify';
                 requestData.amountFreeze = vm.amountFreeze;
                 requestData.interestFreeze = vm.interestFreeze;
                 requestData.transferAnotherBank = vm.transferAnotherBank;

                freezeUnfreezeService.modifyDetails(requestData).then( (response) => {
                    vm.loader=false;
                    vm.addResultResponse = response.data.message;
                    if( vm.addResultResponse == 'SUCCESS'){
                        Swal.fire("Success!", "Details Modified Successfully’ ", "success");
                       location.href = '#!/freeze_unfreeze/action/inquiry';
                    }
                 },function(error) {
                     vm.loader = false;
                                                		  if(error.data.message == 'No Records Found' | error.data.message == 'NDF')
                                                		  {
                                                		  vm.noDataFound = error.data.message;
                                                		  Swal.fire("Warning!",vm.noDataFound,"warning");
                                                		   $state.go("freeze_unfreeze.modify");
                                                		  }
                                                		  else if(error.statusCode == '0' | error.status =='500')
                                                		  {
                                                		  Swal.fire("Warning!",error.message,"warning");
                                                		   $state.go("freeze_unfreeze.modify");
                                                		  }else{  handleErrorResponse(error);
                                                		  $state.go("freeze_unfreeze.modify");
                                                		  }
                });
          }  });
        }

        function verifyDetails(flag){
                vm.loader = true;
                var requestData = {};
                if(flag == 'S'){
                   requestData.schemeCode = null;
                   requestData.pageType = 'scheme';
                } else if (flag == 'P'){
                    requestData.schemeCode = vm.schemeCode;
                    requestData.panNoName = vm.panNoName;
                    requestData.name = vm.name;
                    requestData.pageType = 'pan';
                } else if (flag == 'B'){
                    requestData.schemeCode = vm.schemeCode;
                    requestData.panNoName = vm.panNoName;
                    requestData.name = vm.name;
                    requestData.blaNo = vm.blaNo;
                    requestData.pageType = 'bla';
                } else if (flag == 'BS'){
                    requestData.schemeCode = vm.schemeCode;
                    requestData.panNoName = vm.panNoName;
                    requestData.name = vm.name;
                    requestData.blaNo = vm.blaNo;
                    requestData.subId = vm.subId;
                    requestData.pageType = 'sub';
                } else if (flag == 'D') {
                    requestData.schemeCode = vm.schemeCode;
                    requestData.panNoName = vm.panNoName;
                    requestData.name = vm.name;
                    requestData.blaNo = vm.blaNo;
                    requestData.subId = vm.subId;
                    requestData.pageType = 'scheme';
                }
           freezeUnfreezeService.verifyDetails(requestData).then(function(response){
                if (flag == 'S'){
                    vm.schemeList = response.data.result;
                    angular.element('#schemeModel').modal('toggle');
                } else if (flag == 'P'){
                    vm.PanTorch = response.data.result;
                    angular.element('#panModel').modal('toggle');
                } else if (flag == 'B'){
                    vm.subscriptionList = response.data.result;
                    angular.element('#subscriptionModel').modal('toggle');
                } else if (flag == 'BS'){
                    vm.subscriptionList = response.data.result;
                    angular.element('#subscriptionIdModel').modal('toggle');
                } else if (flag == 'D'){
                     vm.resultSet = response.data.result;
                     vm.audit = response.data.result;
                     vm.responseResultForVer = response.data.result.freezeUnfreezeDTOList;
                     vm.preOrpTyp = response.data.result.preOrpTyp;

                     vm.amountFreezeOldValue = response.data.result.amountFreezeOldValue;
                     vm.amountFreezeNewValue = response.data.result.amountFreezeNewValue;

                     vm.interestFreezeOldValue = response.data.result.interestFreezeOldValue;
                     vm.interestFreezeNewValue = response.data.result.interestFreezeNewValue;

                     vm.transferAnotherBankOldValue = response.data.result.transferAnotherBankOldValue;
                     vm.transferAnotherBankNewValue = response.data.result.transferAnotherBankNewValue;

                    vm.makerRemarksOldValue = response.data.result.makerRemarksOldValue;
                    vm.makerRemarksNewValue = response.data.result.makerRemarksNewValue;

                   vm.checkerRemarksOldValue = response.data.result.checkerRemarksOldValue;
                   vm.checkerRemarksNewValue = response.data.result.checkerRemarksNewValue;
//                     vm.makerRemarks =  vm.resultSet.makerRemarks;
//                     vm.checkerRemarks =  vm.resultSet.checkerRemarks;
                }
                vm.loader = false;
            },function(error) {
                vm.loader = false;
                                           		  if(error.data.message == 'No Records Found' | error.data.message == 'NDF')
                                           		  {
                                           		  vm.noDataFound = error.data.message;
                                           		  Swal.fire("Warning!",vm.noDataFound,"warning");
                                           		   $state.go("freeze_unfreeze.verify");
                                           		  }
                                           		  else if(error.statusCode == '0' | error.status =='500')
                                           		  {
                                           		  Swal.fire("Warning!",error.message,"warning");
                                           		   $state.go("freeze_unfreeze.verify");
                                           		  }else{  handleErrorResponse(error);
                                           		  $state.go("freeze_unfreeze.verify");
                                           		  }
           });
        }

        function verifySubmit() {
//             Swal.fire({
//                title : "Are you sure?",
//                text : "Do you want to Really Verify this record?",
//                type : "warning",
//                showCancelButton : true,
//                cancelButtonClass : "btn-cancel",
//                confirmButtonClass : "btn-primary",
//                confirmButtonText : "Yes",
//                closeOnConfirm : false
//            },
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
                vm.loader= true;
                var requestData = {};
                 vm.selectedAccountsForVer = [];
                  vm.selectedAccountsForVer.push(vm.subId);
                 requestData.schemeCode = vm.schemeCode;
                 requestData.panNoName = vm.panNoName;
                 requestData.name = vm.name;
                 requestData.blaNo = vm.blaNo;
                 requestData.listOfStrings = vm.selectedAccountsForVer;
                 requestData.checkerRemarks = vm.checkerRemarks;
                 requestData.pageType = 'verify';

                freezeUnfreezeService.verifyDetails(requestData).then( (response) => {
                    vm.loader=false;
                    vm.addResultResponse = response.data.message;
                    if( vm.addResultResponse == 'SUCCESS'){
                        Swal.fire("Success!", "Details Verified Successfully’ ", "success");
                       location.href = '#!/freeze_unfreeze/action/inquiry';
                    }
                 },function(error) {
                   vm.loader = false;
                      handleErrorResponse(error);
                      $state.go('freeze_unfreeze.inquiry');
                });
           } });
        }

        function deleteSubmit(rejectionRemarks) {
//             Swal.fire({
//                    title : "Are you sure?",
//                    text : "Do you want to Really Reject this record?",
//                    type : "warning",
//                    showCancelButton : true,
//                    cancelButtonClass : "btn-cancel",
//                    confirmButtonClass : "btn-primary",
//                    confirmButtonText : "Yes",
//                    closeOnConfirm : false
//                },function() {


                      Swal.fire({
                        title: "Are you sure?",
                        text: "Do you want to Really Reject this record?",
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
                    vm.selectedAccountsForVer = []
                    vm.selectedAccountsForVer.push(vm.subId);
                    var requestData = {};
                      requestData.schemeCode = vm.schemeCode;
                      requestData.name = vm.name;
                      requestData.panNoName = vm.panNoName;
                      requestData.blaNo = vm.blaNo;
                      requestData.listOfStrings = vm.selectedAccountsForVer;
                      requestData.deleteRemarks = rejectionRemarks;
                      requestData.pageType = 'delete';
                   freezeUnfreezeService.verifyDetails(requestData).then(function(response){
                        vm.loader = false;
                        Swal.fire("Success", "Rejected Successfully", "success");
                        $state.go('freeze_unfreeze.inquiry');
                   },function(error) {
                      vm.loader = false;
                       handleErrorResponse(error);
                       $state.go('freeze_unfreeze.inquiry');
                   });
           }  });

        }
         function rejectReason() {
            angular.element('#permissionMappingDelete').modal('toggle');
         }
          function getRemarksList() {
               vm.referenceId = '056';
               var requestData = { 'referenceId': vm.referenceId };
               freezeUnfreezeService.getRemarksList(requestData).then(function(response) {
                   vm.remarksList = response.data.result || []; // Ensure it's an array
               }, function(error) {
                   handleErrorResponse(error);
               });
          }


    var MAKER_REASON_PATTERN = /^[a-zA-Z0-9]+(?: [a-zA-Z0-9]+)*$/;

    function validateInput(event, id) {
        var charCode = event.which || event.keyCode;
        if (charCode <= 31 || charCode === 127) {
            return;
        }

        var textBox = document.getElementById(id) || event.target;
        var charTyped = String.fromCharCode(charCode);

        if (!/^[a-zA-Z0-9 ]$/.test(charTyped)) {
            event.preventDefault();
            return;
        }

        var value = textBox.value;
        var pos = textBox.selectionStart;

        if (charTyped === ' ') {
            if (pos === 0 || value.charAt(pos - 1) === ' ' || value.charAt(pos) === ' ') {
                event.preventDefault();
            }
        }
    }

    function validatePaste(event, id) {
        var textBox = document.getElementById(id) || event.target;
        var pasteData = (event.clipboardData || window.clipboardData).getData('text');
        var start = textBox.selectionStart;
        var end = textBox.selectionEnd;
        var newValue = textBox.value.substring(0, start) + pasteData + textBox.value.substring(end);

        if (newValue !== '' && !MAKER_REASON_PATTERN.test(newValue)) {
            event.preventDefault();
        }
    }



 } })();