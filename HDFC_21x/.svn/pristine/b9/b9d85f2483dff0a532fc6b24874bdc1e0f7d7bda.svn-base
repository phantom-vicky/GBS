(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('rbiBondAccTransferOutController',
        rbiBondAccTransferOutController);

    rbiBondAccTransferOutController.$inject = ['$scope', '$filter', '$state',
        '$stateParams', '$location', '$rootScope', '$timeout', 'rbiBondAccTransferOutService', 'commonService'
    ];

    function rbiBondAccTransferOutController($scope, $filter, $state, $stateParams,
        $location, $rootScope, $timeout, rbiBondAccTransferOutService, commonService) {

		var vm = this;

        vm.getRemarksList = getRemarksList;
        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
        //Hari//
        vm.getModuleName = getModuleName;
        vm.getPanTorchDetails = getPanTorchDetails;
        vm.getModifyDetailsBla = getModifyDetailsBla;

        vm.alert = alert;
        vm.convertLabelValue = convertLabelValue;
        vm.getAddBlaTorch = getAddBlaTorch;
        vm.getAddValidation = getAddValidation;
        vm.getNomineeDetails = getNomineeDetails;
        vm.getBankDetailsTorch = getBankDetailsTorch;
        vm.transactionValidation=transactionValidation;
        vm.addSubmit = addSubmit;
        vm.inquiryBlaTorch = inquiryBlaTorch;
        vm.inquiryGbmTranIdTorch = inquiryGbmTranIdTorch;
        vm.verifySelectGbmTranIdTorch = verifySelectGbmTranIdTorch;
        vm.getInquiryDetails = getInquiryDetails;
        vm.getVerifySelect = getVerifySelect;
        vm.deleteSubmit = deleteSubmit;
        vm.verifySubmit = verifySubmit;
        vm.modifySelectGbmTranIdTorch = modifySelectGbmTranIdTorch;
        vm.modifySelectBlaTorch = modifySelectBlaTorch;
        vm.getModifySelectDetails = getModifySelectDetails;
        vm.modifySubmit = modifySubmit;
        vm.getTorchDetails=getTorchDetails;
        vm.emailPattern = /[^@]+@[^@]+\.[a-zA-Z]{2,6}/;
        vm.selectInputVal = selectInputVal;
        vm.subscriptionDetailsList = [];
        vm.subscriptionDetailsList1 = [];

vm.isTranOut = vm.isTranOutToOtherBank;

        vm.defaultSelectOption = defaultSelectOption;

        function defaultSelectOption() {
            vm.isTranOutToOtherBank = 'Y';
        }
        if ($state.current.name == 'rbi_bnd_acc_trans_out.add_detail') {
            if ($stateParams.obj) {

		        vm.schemeCode = $stateParams.obj.schemeCode;
		        vm.schemeName = $stateParams.obj.schemeName;
		        vm.blaNo = $stateParams.obj.blaNo;
		        vm.panNum = $stateParams.obj.panNum;
		        vm.bondHolderName = $stateParams.obj.bondHolderName;
                vm.isTranOutToOtherBank = $stateParams.obj.isTranOutToOtherBank;

                getAddValidation();

            } else {
                $state.go('rbi_bnd_acc_trans_out.add');
            }
        }
        else if ($state.current.name == 'rbi_bnd_acc_trans_out.modify_detail') {
            if ($stateParams.obj) {
                 vm.schemeCode = $stateParams.obj.schemeCode;
                 vm.schemeName = $stateParams.obj.schemeName;
                 vm.blaNo = $stateParams.obj.blaNo;
                 vm.isTranOutToOtherBank = $stateParams.obj.isTransferToOtherBank;
                 vm.gbsTranId = $stateParams.obj.gbsTranId;

                getModifySelectDetails();
            } else {
                $state.go('rbi_bnd_acc_trans_out.modify');
            }
        }
        else if ($state.current.name == 'rbi_bnd_acc_trans_out.verify_detail') {
            if ($stateParams.obj) {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;
                   		        vm.modeOfHolding = $stateParams.obj.modeOfHolding;
                vm.blaNo = $stateParams.obj.blaNo;


                getVerifySelect('D');
            } else {
                $state.go('rbi_bnd_acc_trans_out.verify');
            }
        }
        else if ($state.current.name == 'rbi_bnd_acc_trans_out.delete_detail') {
            if ($stateParams.obj) {
                 vm.schemeCode = $stateParams.obj.schemeCode;
                 vm.schemeName = $stateParams.obj.schemeName;
                 vm.blaNo = $stateParams.obj.blaNo;
                 vm.isTranOutToOtherBank = $stateParams.obj.isTransferToOtherBank;
                 vm.gbsTranId = $stateParams.obj.gbsTranId;

                 getVerifySelectDetails();


            } else {
                $state.go('rbi_bnd_acc_trans_out.delete');
            }
        }
        else if ($state.current.name == 'rbi_bnd_acc_trans_out.inquiry_detail') {
            if ($stateParams.obj) {
                    vm.schemeCode = $stateParams.obj.schemeCode;
                   		        vm.schemeName = $stateParams.obj.schemeName;
                   		        vm.blaNo = $stateParams.obj.blaNo;
                   		        vm.panNum = $stateParams.obj.panNum;
                   		        vm.modeOfHolding = $stateParams.obj.modeOfHolding;
                   		        vm.bondHolderName = $stateParams.obj.bondHolderName;
                   		        vm.bondSubsId = $stateParams.obj.bondSubsId;
                                   vm.isTranOutToOtherBank = $stateParams.obj.isTranOutToOtherBank;
                getInquiryDetails();

            } else {
                $state.go('rbi_bnd_acc_trans_out.inquiry');
            }
        }

        function getRemarksList() {
                            vm.referenceId = '056';
                            var requestData = { 'referenceId': vm.referenceId };

                            rbiBondAccTransferOutService.getRemarksList(requestData).then(function(response) {
                                vm.remarksList = response.data.result || []; // Ensure it's an array
                            }, function(error) {
                                handleErrorResponse(error);
                            });
                        }

        function alert(item){

                                 for (let i = 0 ;i < vm.schemeList.length;i++){
                                  if(vm.schemeList[i].subCode == item){
                                        vm.schemeName = vm.schemeList[i].subCodeDescription;
                                   }
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
        function convertLabelValue(list){
                    for(var item in list){
                        list[item].value = list[item].SUBCODE;
                        list[item].label = list[item].DESCRIPTION;
                    }
                    return list;
                }

       function getTypeofHolding() {
           var requestData = {
                 'referenceId': 'HOLDER_TYPE'
             }
           rbiBondAccTransferOutService.getReferenceData(requestData).then(function(response){
              vm.holderTypeList = response.data.result;
            },function(error) {
               handleErrorResponse(error);
             });
        }



        function getAddBlaTorch(){
            vm.loader = true;
                var requestData = {
                "schemeCode": vm.schemeCode,
                };
               rbiBondAccTransferOutService.getValidationAdd(requestData).then(function(response){
                    vm.loader = false;
                    vm.blaTorchList = response.data.result;
                  angular.element('#blaModal').modal('toggle');

                },function(error) {
                vm.loader = false;
                   handleErrorResponse(error);
                 });

        }

 this.getValidationAdd = function (requestData) {
        // Simulate the API call with mocked data for testing
        var deferred = $q.defer();

        // Mocked data structure
        var mockResponse = {
            data: {
                result: {
                    bondHolderDetails: [
                        {
                            nameOfTheBondHolder: "John Doe",
                            blaNo: "BLA12345",
                            isNri: "N",
                            jointHolderType: "Primary"
                        },
                        {
                            nameOfTheBondHolder: "Jane Smith",
                            blaNo: "BLA67890",
                            isNri: "Y",
                            jointHolderType: "Secondary"
                        }
                    ],
                    bondSubscriptionDetails: [
                        {
                            subscriptionDate: "2024-11-10",
                            subscriptionId: "SUB001",
                            subscriptionAmount: "10000",
                            bondType: "Type A",
                            maturityDate: "2025-11-10",
                            rateOfInterest: "5.5",
                            interestPaidUpTo: "2024-10-01",
                            transferAmount: "9000",
                            isSelected: true
                        },
                        {
                            subscriptionDate: "2024-11-11",
                            subscriptionId: "SUB002",
                            subscriptionAmount: "15000",
                            bondType: "Type B",
                            maturityDate: "2026-11-11",
                            rateOfInterest: "6.0",
                            interestPaidUpTo: "2024-10-15",
                            transferAmount: "13000",
                            isSelected: true
                        }
                    ],
                    bondNomineeDetails: [
                        {
                            subscriptionId: "SUB001",
                            nomineeName: "Alice Johnson",
                            nomineeShare: "50"
                        },
                        {
                            subscriptionId: "SUB002",
                            nomineeName: "Bob Williams",
                            nomineeShare: "50"
                        }
                    ]
                }
            }
        };

        // Return the mock response
        deferred.resolve(mockResponse);
        return deferred.promise;
    };
            function getAddValidation(){
                vm.loader = true;
                    var requestData = {
                   'schemeCode': vm.schemeCode,
                    'panNum': vm.panNum,
                    'blaNo': vm.blaNo,
                     'detFlag': 'D',
                     'tranFlag': vm.isTranOutToOtherBank
                    };
                   rbiBondAccTransferOutService.addValidation(requestData).then(function(response){

                     vm.getDetails = response.data.result;
                       vm.bondHolderDetails = vm.getDetails.bondHolderDetails;
                       vm.modeOfHolding = vm.bondHolderDetails[0].modeOfHolding;
                       vm.bondSubscriptionDetails = vm.getDetails.bondSubscriptionDetails;
                       vm.getNomineeDetailsList = vm.getDetails.bondNomineeDetails;

                    if(vm.isTranOutToOtherBank == 'Y'){
                       vm.creditAccNo = vm.getDetails.creditAccNo;
                       vm.debitAccNo = vm.getDetails.debitAccNo;
                       vm.bondSuspenseAccFlgOut=vm.getDetails.bondSuspenseAccFlgOut;
                       vm.ddPoAccFlg=vm.getDetails.ddPoAccFlg;
                    }

                    vm.totalSubscriptionAmount = 0;
                    angular.forEach(vm.bondSubscriptionDetails, function(item){
                           item.isSelected = false;
                           vm.totalSubscriptionAmount = vm.totalSubscriptionAmount + parseInt(item.subscriptionAmount);
                    });
                    vm.loader = false;
                    },function(error) {
        					 const errorMessage = error && error.data && error.data.message
                                                  ? error.data.message
                                                  : "An unexpected error occurred.";
                                          if(null!=errorMessage && errorMessage.startsWith("Invalid")){
                                          Swal.fire("Warning!", errorMessage , "warning");
                                          $state.go("rbi_bnd_acc_trans_out.add");
                                          }else{
                                          handleErrorResponse(error);
                                          $state.go("rbi_bnd_acc_trans_out.add");
                                          }
                     });

            }


                    function transactionValidation(){
                                var requestData={
                                    'creditAccNo':vm.creditAccNo,
                                    'debitAccNo':vm.debitAccNo,
                                    'bondSuspenseAccFlgOut':vm.bondSuspenseAccFlgOut,
                                    'ddPoAccFlg' :  vm.ddPoAccFlg
                                }
                               rbiBondAccTransferOutService.transactionValidation(requestData).then(function(response)
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

            function getNomineeDetails(){


                vm.loader = true;
                vm.getNomineeDetailsList = null;
                vm.subscriptionList = [];

                angular.forEach(vm.bondSubscriptionDetails, function(item){
                            if(item.isSelected){
                                vm.subscriptionList.push(item.subscriptionId);
                            }
                                    });



                    var requestData = {
                    "schemeCode": vm.schemeCode,
                    "subscriptionIds": vm.subscriptionList,
                     "blaNo": vm.blaNo,
                    };
                   rbiBondAccTransferOutService.getNomineeDetails(requestData).then(function(response){
                        vm.loader = false;
                        vm.getNomineeDetailsList = response.data.result;

                    },function(error) {
                    vm.loader = false;
                       handleErrorResponse(error);

//                           angular.forEach(vm.bondSubscriptionDetails, function(item){
//                                    item.isSelected = false;
//                                   });
                     });
            }

            function getBankDetailsTorch(){
                vm.loader = true;
                   var requestData = {
                                       "schemeCode": vm.schemeCode,
                                       };
                   rbiBondAccTransferOutService.getBankDetails(requestData).then(function(response){
                        vm.loader = false;
                        vm.bankDetailsList = response.data.result;
                        angular.element('#branchModal').modal('toggle');
                    },function(error) {
                    vm.loader = false;
                       handleErrorResponse(error);
                     });
            }


            function addSubmit() {
vm.subscriptionDetailsList1=[];

 // Collect selected subscriptions
  angular.forEach(vm.bondSubscriptionDetails, function (item) {
    if (item.isSelected) {
      vm.subscriptionDetailsList1.push(item);
    }
  });

  // Remove unnecessary $$hashKey property
  vm.subscriptionDetailsList = vm.subscriptionDetailsList1.map(function (item) {
    var cleanedItem = Object.assign({}, item); // Shallow copy the item
    delete cleanedItem.$$hashKey;
    return cleanedItem;
  });

  // Check if no subscriptions are selected
  if (vm.subscriptionDetailsList.length === 0) {
    Swal.fire("Warning!", "Please select at least one Subscription ID.", "warning");
    return;
  }

  // Check for partial selection
  const allSelected = vm.bondSubscriptionDetails.every((item) => item.isSelected);
  const partialSelection = !allSelected && vm.subscriptionDetailsList.length > 0;

  if (partialSelection) {
    // Show popup for partial selection
//    Swal.fire(
//      {
//        title: "Partial Selection Detected",
//        text: "You have partially selected the subscription list. Do you want to proceed?",
//        type: "warning",
//        showCancelButton: true,
//        cancelButtonClass: "btn-cancel",
//        confirmButtonClass: "btn-primary",
//        confirmButtonText: "Yes",
//        cancelButtonText: "No",
//        closeOnConfirm: false,
//        closeOnCancel: true,
//      },
//      function (isConfirm) {
//        if (isConfirm) {
//          // User confirmed to proceed
//          submitRequest();
//        } else {
//          // User cancelled
//          return;
//        }
//        }
//      }
//    );



   Swal.fire({
     title: "Are you sure?",
     text: "You have partially selected the subscription list. Do you want to proceed?",
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
       // User confirmed to proceed
       submitRequest();
     } else {
       // User cancelled
       return;
     }
   });










  } else {
    // If fully selected, proceed directly
    submitRequest();
  }

  // Function to handle the request submission
  function submitRequest() {

//                     Swal.fire({
//                            title : "Are you sure?",
//                            text : "Do you want to Really Add this record?",
//                            type : "warning",
//                            showCancelButton : true,
//                            cancelButtonClass : "btn-cancel",
//                            confirmButtonClass : "btn-primary",
//                            confirmButtonText : "Yes",
//                            closeOnConfirm : false
//                        },
//                     function() {






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
      blaNo: vm.blaNo,
      remarks: vm.remarks,
      tranFlag: vm.isTranOutToOtherBank,
      subscriptionDetailsList: vm.subscriptionDetailsList,
    };

    // Add additional fields based on `tranFlag`
    if (vm.isTranOutToOtherBank === "Y") {
      requestData.transferToBankName = vm.transferToBankName;
      requestData.transferToBranchName = vm.transferToBranchName;
    } else if (vm.isTranOutToOtherBank === "N") {
      requestData.transferToBranchId = vm.transferToBranchId;
      requestData.transferToSameBranchName = vm.transferToSameBranchName;
    }

    console.log(requestData); // Debugging output to check the request data

    rbiBondAccTransferOutService.addSubmit(requestData).then(
      function (response) {
        vm.loader = false;
        vm.addResultResponse = response.data.result;

        var msgForAdd = "Bond Transfer Out Details Added Successfully";

        Swal.fire("Success!", msgForAdd, "success");
        location.href = "#!/rbi_bnd_acc_trans_out/action/inquiry";
      },
      function (error) {
        vm.loader = false;
        handleErrorResponse(error);
        $state.go("rbi_bnd_acc_trans_out.add");
      }
    );

    }
    });
  }
        }


        function inquiryBlaTorch(){
                vm.loader = true;
                    var requestData = {
                    "schemeCode": vm.schemeCode,
                    "isFinalSubmit": 'N',
                    };
                   rbiBondAccTransferOutService.inquiry(requestData).then(function(response){
                        vm.loader = false;
                        vm.blaTorchList = response.data.result;
                        angular.element('#blaModal').modal('toggle');

                    },function(error) {
                    vm.loader = false;
                       handleErrorResponse(error);
                     });
        }

        function inquiryGbmTranIdTorch(isTransferToOtherBank){
                vm.loader = true;
                    var requestData = {
                    "schemeCode": vm.schemeCode,
                    "blaNo": vm.blaNo,
                    "isFinalSubmit": 'N',
                    "bondSubsId" : vm.bondSubsId,
                    "isTransferToOtherBank": isTransferToOtherBank
                    };
                   rbiBondAccTransferOutService.inquiry(requestData).then(function(response){
                        vm.loader = false;
                        vm.gbmTranIdList = response.data.result;
                        angular.element('#gbsTranModal').modal('toggle');

                    },function(error) {
                    vm.loader = false;
                       handleErrorResponse(error);
                     });
        }

        function selectInputVal() {
        			if (vm.selectedCount == '0' && vm.isAllSelected == '') {
        				Swal.fire("Please Select At least One Subscription Id")
        			}
        		}

        function getInquiryDetails() {
                vm.loader = true;
                    var requestData = {
                    "schemeCode": vm.schemeCode,
                    "blaNo": vm.blaNo,
                     'detFlag': 'D',
                    "bondSubsId" : vm.bondSubsId,
                    "tranFlag": vm.isTranOutToOtherBank
                    };
                   rbiBondAccTransferOutService.getTorchDetails(requestData).then(function(response){
                        vm.loader = false;

                        vm.getDetails  = response.data.result;
                        vm.bondHolderDetails  = vm.getDetails.bondHolderDetails;
                        vm.bondSubscriptionDetails  = vm.getDetails.bondSubscriptionDetails;
                        vm.bondNomineeDetails  = vm.getDetails.bondNomineeDetails;
                        vm.bondTransferOutDetails  = vm.getDetails.bondTransferOutDetails[0];

                    },function(error) {
                   	 const errorMessage = error && error.data && error.data.message
                                                                     ? error.data.message
                                                                     : "An unexpected error occurred.";
                                                             if(null!=errorMessage && errorMessage.startsWith("Invalid")){
                                                             Swal.fire("Warning!", errorMessage , "warning");
                                                             $state.go("rbi_bnd_acc_trans_out.inquiry");
                                                             }else{
                                                             handleErrorResponse(error);
                                                             $state.go("rbi_bnd_acc_trans_out.inquiry");
                                                             }
                     });
        }


        /*function verifySelect(){
                vm.loader = true;
                    var requestData = {
                    "schemeCode": vm.schemeCode,
                    "isFinalSubmit": 'N',
                    };
                   rbiBondAccTransferOutService.verifySelect(requestData).then(function(response){
                        vm.loader = false;
                        vm.blaTorchList = response.data.result;
                        angular.element('#blaModal').modal('toggle');

                    },function(error) {
                    vm.loader = false;
                       handleErrorResponse(error);
                     });
        }*/


        function verifySelectGbmTranIdTorch(){
                vm.loader = true;
                    var requestData = {
                    "schemeCode": vm.schemeCode,
                    "blaNo": vm.blaNo,
                    "isFinalSubmit": 'N',
                    };
                   rbiBondAccTransferOutService.verifySelect(requestData).then(function(response){
                        vm.loader = false;
                        vm.gbmTranIdList = response.data.result;
                        angular.element('#gbsTranModal').modal('toggle');

                    },function(error) {
                    vm.loader = false;
                       handleErrorResponse(error);
                     });
        }


            function getVerifySelect(flag) {
                    vm.loader = true;
                   if(flag == 'S'){
                   var requestData = {
                   };
                   } else  if(flag == 'B'){
                      var requestData = {
                       "schemeCode": vm.schemeCode,
                  };
                  }  else  if(flag == 'D'){
                                                       var requestData = {
                                                        "schemeCode": vm.schemeCode,
                                                        "blaNo":vm.blaNo
                                                   };
                                                   }

                       rbiBondAccTransferOutService.verifySelect(requestData).then(function(response){
  vm.loader = false;
                                     if(flag == 'S'){
                                    vm.schemeList = response.data.result;
                                    angular.element('#schemeModel').modal('toggle');
                                    }  else  if(flag == 'B'){
                                    vm.blaTorchList = response.data.result;
                                    angular.element('#blaModal').modal('toggle');

                                    }else {
                            vm.getDetails  = response.data.result;
                            vm.bondHolderDetails  = vm.getDetails.bondHolderDetails;
                            vm.bondSubscriptionDetails  = vm.getDetails.bondSubscriptionDetails;
                            vm.getNomineeDetailsList  = response.data.result.bondNomineeDetails;
                            vm.bondTransferOutDetails  = vm.getDetails.bondTransferOutDetails[0];
                            }

                        },function(error) {
                          vm.loader = false;
                           handleErrorResponse(error);
                           $state.go('rbi_bnd_acc_trans_out.verify_detail');
                         });
            }


            function deleteSubmit() {
            vm.subscriptionDetailsList1=[];

// Collect selected subscriptions
  angular.forEach(vm.bondSubscriptionDetails, function (item) {
    if (item.isSelected) {
      vm.subscriptionDetailsList1.push(item);
    }
  });

  // Remove unnecessary $$hashKey property
  vm.subscriptionDetailsList = vm.subscriptionDetailsList1.map(function (item) {
    var cleanedItem = Object.assign({}, item); // Shallow copy the item
    delete cleanedItem.$$hashKey;
    return cleanedItem;
  });

  // Check if no subscriptions are selected
  if (vm.subscriptionDetailsList.length === 0) {
    Swal.fire("Warning!", "Please select at least one Subscription ID.", "warning");
    return;
  }

  // Check for partial selection
  const allSelected = vm.bondSubscriptionDetails.every((item) => item.isSelected);
  const partialSelection = !allSelected && vm.subscriptionDetailsList.length > 0;

  if (partialSelection) {
    // Show popup for partial selection
//    Swal.fire(
//      {
//        title: "Partial Selection Detected",
//        text: "You have partially selected the subscription list. Do you want to proceed?",
//        type: "warning",
//        showCancelButton: true,
//        cancelButtonClass: "btn-cancel",
//        confirmButtonClass: "btn-primary",
//        confirmButtonText: "Yes",
//        cancelButtonText: "No",
//        closeOnConfirm: false,
//        closeOnCancel: true,
//      },
//      function (isConfirm) {
//        if (isConfirm) {
//          // User confirmed to proceed
//          submitRequest();
//        } else {
//          // User cancelled
//          return;
//        }
//      }
//    );




Swal.fire({
  title: "Are you sure?",
  text: "You have partially selected the subscription list. Do you want to proceed?",
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
    // User confirmed to proceed
    submitRequest();
  } else {
    // User cancelled
    return;
  }
});
 } else {
    // If fully selected, proceed directly
    submitRequest();
  }

  // Function to handle the request submission
  function submitRequest() {


//                     Swal.fire({
//                            title : "Are you sure?",
//                            text : "Do you want to Really Reject this record?",
//                            type : "warning",
//                            showCancelButton : true,
//                            cancelButtonClass : "btn-cancel",
//                            confirmButtonClass : "btn-primary",
//                            confirmButtonText : "Yes",
//                            closeOnConfirm : false
//                        },
//                     function() {


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
                                var requestData = {
                                "schemeCode": vm.schemeCode,
                                "blaNo": vm.blaNo,
                    "subscriptionDetailsList": vm.subscriptionDetailsList,
                                "remarks": vm.remarks
                                };
                               rbiBondAccTransferOutService.deleteSubmit(requestData).then(function(response){
                                    vm.loader = false;
                                    Swal.fire("Success", "Bond Account Transfer out Rejected Successfully", "success");
                                     $state.go('rbi_bnd_acc_trans_out.inquiry');

                                      const backdrop = document.querySelector('.modal-backdrop');
                                                                                                                             if (backdrop) {
                                                                                                                               backdrop.parentNode.removeChild(backdrop);
                                                                                                                               document.body.classList.remove('modal-open');
                                                                                                                             }

                                },function(error) {
                                  vm.loader = false;
                                   handleErrorResponse(error);
                                   $state.go('rbi_bnd_acc_trans_out.inquiry');
                                 });
                                 }

                    });


}

            }
 vm.validateInput=validateInput;
              function validateInput(event,id){

                  const textBox = document.getElementById(id);

                    var charCode = event.which || event.keyCode;
                      var charTyped = String.fromCharCode(charCode);

              if(textBox)
              {
               const cursorPosition = textBox.selectionStart;
                  if(cursorPosition == 0 && charTyped === ' ')
                  {
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


vm.rejectReason=rejectReason;
function rejectReason() {
  angular.element('#permissionMappingDelete').modal('toggle');
  }

            function verifySubmit() {/*
vm.subscriptionDetailsList1 = [];

              angular.forEach(vm.bondSubscriptionDetails, function(item){
                                                         if(item.isSelected){
                                                             vm.subscriptionDetailsList1.push(item);
                                                         }
                                                    });

                vm.subscriptionDetailsList = vm.subscriptionDetailsList1.map(function(item) {
                  var cleanedItem = Object.assign({}, item);  // Shallow copy the item
                  delete cleanedItem.$$hashKey;  // Delete the $$hashKey property
                  return cleanedItem;
                });
//
//                       if(vm.transactionValidation)
//                       {
//                           if(vm.cbsStatus !=  "S"){
//                               proceed = false;
//
//                           }else{
//                               proceed = true;
//                           }
//                       else{
//                           proceed = true;
//                       }

              if(vm.subscriptionDetailsList.length ==0 ){
              Swal.fire("Warning!", "Please Select At least one Subscription Id", "warning");
              }
              else{*/
vm.subscriptionDetailsList1 = [];

 // Collect selected subscriptions
  angular.forEach(vm.bondSubscriptionDetails, function (item) {
    if (item.isSelected) {
      vm.subscriptionDetailsList1.push(item);
    }
  });

  // Remove unnecessary $$hashKey property
  vm.subscriptionDetailsList = vm.subscriptionDetailsList1.map(function (item) {
    var cleanedItem = Object.assign({}, item); // Shallow copy the item
    delete cleanedItem.$$hashKey;
    return cleanedItem;
  });

  // Check if no subscriptions are selected
  if (vm.subscriptionDetailsList.length === 0) {
    Swal.fire("Warning!", "Please select at least one Subscription ID.", "warning");
    return;
  }

  // Check for partial selection
  const allSelected = vm.bondSubscriptionDetails.every((item) => item.isSelected);
  const partialSelection = !allSelected && vm.subscriptionDetailsList.length > 0;

  if (partialSelection) {
    // Show popup for partial selection
//    Swal.fire(
//      {
//        title: "Partial Selection Detected",
//        text: "You have partially selected the subscription list. Do you want to proceed?",
//        type: "warning",
//        showCancelButton: true,
//        cancelButtonClass: "btn-cancel",
//        confirmButtonClass: "btn-primary",
//        confirmButtonText: "Yes",
//        cancelButtonText: "No",
//        closeOnConfirm: false,
//        closeOnCancel: true,
//      },
//      function (isConfirm) {
//        if (isConfirm) {
//          // User confirmed to proceed
//          submitRequest();
//        } else {
//          // User cancelled
//          return;
//        }
//      }
//    );








    Swal.fire({
      title: "Are you sure?",
      text: "You have partially selected the subscription list. Do you want to proceed?",
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
        // User confirmed to proceed
        submitRequest();
      } else {
        // User cancelled
        return;
      }
    });
  } else {
    // If fully selected, proceed directly
    submitRequest();
  }
function submitRequest() {

// Swal.fire({
//                                            title : "Are you sure?",
//                                            text : "Do you want to Really Verify this record?",
//                                            type : "warning",
//                                            showCancelButton : true,
//                                            cancelButtonClass : "btn-cancel",
//                                            confirmButtonClass : "btn-primary",
//                                            confirmButtonText : "Yes",
//                                            closeOnConfirm : false
//                                        },
//                                     function() {





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

                                            vm.loader = true;
                                             var requestData = {
                                             "schemeCode": vm.schemeCode,
                                             "blaNo": vm.blaNo,
                                            "subscriptionDetailsList": vm.subscriptionDetailsList
                                             };
                                            rbiBondAccTransferOutService.verifySubmit(requestData).then(function(response){
                                                 vm.loader = false;
                                                 if(vm.isTranOutToOtherBank == 'Y'){
                                                     vm.responseResult = response.data.result;
                                                     var message = "Bond Account Transfer out Verified Successfully\nGBS Tran Id: " + vm.gbsTranId + "\nCBS Tran Id: " +  vm.responseResult.cbsTranId;
                                                     Swal.fire("Success", message, "success");
                                                 }else{
                                                     Swal.fire("Success", "Bond Account Transfer out Verified Successfully", "success");
                                                 }

                                               $state.go('rbi_bnd_acc_trans_out.inquiry');

                                             },function(error) {
                                               vm.loader = false;
                                                handleErrorResponse(error);
                                                $state.go('rbi_bnd_acc_trans_out.inquiry_detail');
                                              });
                                              }
                                              });

    }

            }


            function modifySelectBlaTorch(){
                    vm.loader = true;
                        var requestData = {
                        "schemeCode": vm.schemeCode,
                        "isFinalSubmit": 'N',
                        };
                       rbiBondAccTransferOutService.modifySelect(requestData).then(function(response){
                            vm.loader = false;
                            vm.blaTorchList = response.data.result;
                            angular.element('#blaModal').modal('toggle');

                        },function(error) {
                        vm.loader = false;
                           handleErrorResponse(error);
                         });
            }

            function modifySelectGbmTranIdTorch(){
                    vm.loader = true;
                        var requestData = {
                        "schemeCode": vm.schemeCode,
                        "blaNo": vm.blaNo,
                        "isFinalSubmit": 'N',
                        };
                       rbiBondAccTransferOutService.modifySelect(requestData).then(function(response){
                            vm.loader = false;
                            vm.gbmTranIdList = response.data.result;
                            angular.element('#gbsTranModal').modal('toggle');

                        },function(error) {
                        vm.loader = false;
                           handleErrorResponse(error);
                         });
            }


            function getModifySelectDetails() {
                                vm.loader = true;
                                    var requestData = {
                                    "schemeCode": vm.schemeCode,
                                    "blaNo": vm.blaNo,
                                    "isFinalSubmit": 'Y',
                                    "gbmTranId": vm.gbsTranId,
                                    };
                                   rbiBondAccTransferOutService.modifySelect(requestData).then(function(response){

                                        vm.getDetails  = response.data.result;

                                        vm.bondHolderDetails  = vm.getDetails.bondHolderDetails[0];
                                        vm.bondSubscriptionDetails  = vm.getDetails.bondSubscriptionDetails;
                                        vm.getNomineeDetailsList  = vm.getDetails.bondNomineeDetails;
                                        vm.bondTransferOutDetails  = vm.getDetails.bondTransferOutDetails[0];

                                        angular.forEach(vm.bondSubscriptionDetails, function(item){
                                            item.isSelected = true;
                                        });

                                        if(vm.isTranOutToOtherBank =='Y'){
                                            vm.transferToBankName = vm.bondTransferOutDetails.transferOutToBankName;
                                            vm.transferToBranchName = vm.bondTransferOutDetails.transferOutToBranchName;
                                            vm.transferThrough = vm.bondTransferOutDetails.transferOutThrough;
                                            vm.creditAccNo = vm.bondTransferOutDetails.creditAccDdpoNum;
                                        }
                                        if(vm.isTranOutToOtherBank =='N'){
                                            vm.transferToBranchId = vm.bondTransferOutDetails.transferOutToBankName;
                                            vm.transferToBranchId = vm.bondTransferOutDetails.transferOutToBranchName;
                                        }

                                        vm.remarks = vm.bondTransferOutDetails.remarks;

                                        vm.loader = false;

                                    },function(error) {
                                      vm.loader = false;
                                       handleErrorResponse(error);
                                       $state.go('rbi_bnd_acc_trans_out.verify_detail');
                                     });
                        }


            function modifySubmit() {

//                             Swal.fire({
//                                                        title : "Are you sure?",
//                                                        text : "Do you want to Really Modify this record?",
//                                                        type : "warning",
//                                                        showCancelButton : true,
//                                                        cancelButtonClass : "btn-cancel",
//                                                        confirmButtonClass : "btn-primary",
//                                                        confirmButtonText : "Yes",
//                                                        closeOnConfirm : false
//                                                    },
//                                                 function() {









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
                                                         "schemeCode": vm.schemeCode,
                                                         "blaNo": vm.blaNo,
                                                         "subscriptionIds": validateSubscriptionDetailsList(),
                                                         "isChanged": validateSubscriptionDetailsTickDetails(),
                                                         "totTransferAmount": getTotalSubscriptionAmount(),
                                                         };

                                                        if(vm.isTranOutToOtherBank == 'Y'){
                                                            requestData.transferToOutBankName = vm.transferToBankName;
                                                            requestData.transferToOutBranchName = vm.transferToBranchName;
                                                            requestData.transferOutThroughIn = vm.transferThrough;
                                                            requestData.creditAccNumDdPpo = vm.creditAccNo;
                                                        }
                                                        if(vm.isTranOutToOtherBank == 'N'){
                                                            requestData.transferToInBankName = vm.transferToBranchId;
                                                            requestData.transferToInBranchName = vm.transferToSameBranchName;
                                                        }



                                                        requestData.remarks = vm.remarks;

                                                        rbiBondAccTransferOutService.modifySubmit(requestData).then(function(response){
                                                             vm.loader = false;
                                                             Swal.fire("Success", "Bond Account Transfer out Modified Successfully", "success");

                                                         },function(error) {
                                                           vm.loader = false;
                                                            handleErrorResponse(error);
                                                            $state.go('rbi_bnd_acc_trans_out.inquiry_detail');
                                                          });
                                                          }

                                                });


                        }

           function validateSubscriptionDetailsList(){
            var list = [];
            angular.forEach(vm.bondSubscriptionDetails, function(item){
                if(!item.isSelected){
                    list.push(item.subscriptionId);
                }
            });
            return list;
        }

           function validateSubscriptionDetailsTickDetails(){
                var result = 'N';
                angular.forEach(vm.bondSubscriptionDetails, function(item){
                    if(!item.isSelected){
                        result = 'Y';
                    }
                });
                return result;
            }

            function getTotalSubscriptionAmount(){
                var total = 0;
                angular.forEach(vm.bondSubscriptionDetails, function(item){
                    total = total + parseInt(item.transferAmount);
                });
                return total;
            }

//Hari//
            function getModuleName() {
                                vm.loader = true;

                                var requestData = {
                                };
                                rbiBondAccTransferOutService.addValidation(requestData).then(function (response) {
                                    vm.loader = false;
                                    vm.schemeList = response.data.result;
                                    console.log("SchemeList",vm.schemeList);
                                    angular.element('#schemeModel').modal('toggle');
                                }, function (error) {
                                    handleErrorResponse(error);
                                    vm.loader = false;
                                });
                            }
                             function getTorchDetails(flag) {
                                vm.loader = true;
                                if(flag == 'SC'){
                                var requestData = {
                                };
                                } else  if(flag == 'N'){
                                   var requestData = {
                                    "schemeCode": vm.schemeCode,
                                   "detFlag" : flag,
                                   "bondHolderName": vm.bondHolderName
                               };
                               } else if(flag == 'P'){
                                 var requestData = {
                                "schemeCode": vm.schemeCode,
                               "detFlag" : flag,
                                "panNum": vm.panNum,
                               "bondHolderName": vm.bondHolderName
                               };
                                }
                                else if( flag == 'B'){
                                  var requestData = {
                                                                "schemeCode": vm.schemeCode,
                                                               "detFlag" : flag,
                                                                "panNum": vm.panNum,
                                                               "bondHolderName": vm.bondHolderName,
                                                                    "blaNo": vm.blaNo
                                                               };
                                }
                                else  if(flag == 'S'){
                                                                    var requestData = {
                                                                     "schemeCode": vm.schemeCode,
                                                                    "detFlag" : flag,
                                                                    "tranFlag": vm.isTranOutToOtherBank,
                                                                     "bondHolderName": vm.bondHolderName,
                                                                    "panNum": vm.panNum,
                                                                    "blaNo": vm.blaNo
                                                                };
                                                                }
                                rbiBondAccTransferOutService.getTorchDetails(requestData).then(function (response) {
                                    vm.loader = false;
                                     if(flag == 'SC'){
                                    vm.schemeList = response.data.result;
                                    angular.element('#schemeModel').modal('toggle');
                                    }  else  if(flag == 'N'){
                                    vm.nameTorchList = response.data.result;
                                    angular.element('#nameModal').modal('toggle');

                                    } else  if( flag == 'P' ){
                                    vm.PanTorch = response.data.result;
                                    angular.element('#panModel').modal('toggle');

                                    } else  if(flag == 'B'){
                                    vm.blaTorchList = response.data.result;
                                    angular.element('#blaModal').modal('toggle');

                                    }else  if(flag == 'S'){
                                                                         vm.subsTorchList = response.data.result;
                                                                         angular.element('#subsModel').modal('toggle');

                                                                         }
                                }, function (error) {
                                    handleErrorResponse(error);
                                     if(flag == 'N'){
                                            vm.bondHolderName='';vm.blaNo='';vm.panNum='';vm.bondSubsId='';
                                    } else  if( flag == 'P' ){
                                            vm.blaNo='';vm.panNum='';vm.bondSubsId='';
                                    } else  if(flag == 'B'){
                                              vm.blaNo='';vm.bondSubsId='';
                                    }

                                    vm.loader = false;
                                });
                            }
//Hari//
             function getPanTorchDetails(bondHolderName){
                              vm.loader = true;

                          var requestData = {
                          'schemeCode': vm.schemeCode,
                          'bondHolderName': bondHolderName,
                          'panNum': vm.panNum,
                          'detFlag': 'P'
                      }
                      rbiBondAccTransferOutService.addValidation(requestData).then(function(response) {
                                                                      vm.loader = false;
                                                                      vm.panDataList = response.data.result;
                                                                       angular.element('#pan1').modal('toggle');
                                                                  }, function(error) {
                                                                      handleErrorResponse(error);
                                                                      vm.loader = false;
                                                                  });


                                          }

//Hari//

          function getModifyDetailsBla(){
                                     vm.loader = true;

                                 var requestData = {
                                 'schemeCode': vm.schemeCode,
                                 'panNum': vm.panNum,
                                 'blaNo': vm.blaNo,
                                  'detFlag': 'B'
                             }
                             rbiBondAccTransferOutService.addValidation(requestData).then(function(response) {
                                                                             vm.loader = false;
                                                                             vm.blaDataList = response.data.result;
                                                                              angular.element('#blaModel2').modal('toggle');
                                                                         }, function(error) {
                                                                             handleErrorResponse(error);
                                                                             vm.loader = false;
                                                                         });


                             }




    }

})();