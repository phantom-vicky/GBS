(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('rbiBondBrokeragePaymentController',
        rbiBondBrokeragePaymentController);

    rbiBondBrokeragePaymentController.$inject = ['$scope', '$filter', '$state',
        '$stateParams', '$location', '$rootScope', '$timeout', 'rbiBondBrokeragePaymentService', 'commonService'
    ];

    function rbiBondBrokeragePaymentController($scope, $filter, $state, $stateParams,
        $location, $rootScope, $timeout, rbiBondBrokeragePaymentService, commonService) {

		var vm = this;

        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
        vm.alert = alert;
        vm.convertLabelValue = convertLabelValue;
        vm.getModuleName = getModuleName;
        vm.getModuleNameFindByCode = getModuleNameFindByCode;

        vm.getAddBrokerTorch = getAddBrokerTorch;
        vm.getAddValidation = getAddValidation;
        vm.calculateTotalPaidAmount = calculateTotalPaidAmount;
        vm.addSubmit = addSubmit;

        vm.inquiryBrokerTorch = inquiryBrokerTorch;
        vm.inquiryGbsTranIDTorch = inquiryGbsTranIDTorch;
        vm.getInquiryDetails = getInquiryDetails;

        vm.verifySelectBrokerTorch = verifySelectBrokerTorch;
        vm.verifyGbsTranIDTorch = verifyGbsTranIDTorch;
        vm.getVerifySelectDetails = getVerifySelectDetails;

        vm.verifySubmit = verifySubmit;
        vm.deleteSubmit = deleteSubmit;
        vm.transactionValidation=transactionValidation;



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


        if ($state.current.name == 'rbi_bnd_brk_payment.add_detail') {
            if ($stateParams.obj) {

		        vm.schemeCode = $stateParams.obj.schemeCode;
		        vm.schemeName = $stateParams.obj.schemeName;
		        vm.brokerId = $stateParams.obj.brokerId;
		        vm.fromDate = $stateParams.obj.fromDate;
		        vm.toDate = $stateParams.obj.toDate;

                getAddValidation();

            } else {
                $state.go('rbi_bnd_brk_payment.add');
            }
        }
        else if ($state.current.name == 'rbi_bnd_brk_payment.verify_detail') {
            if ($stateParams.obj) {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;
                vm.brokerId = $stateParams.obj.brokerId;
                vm.gbsTranId = $stateParams.obj.gbsTranId;
                vm.fromDate = $stateParams.obj.fromDate;
                vm.toDate = $stateParams.obj.toDate;

                getModuleNameFindByCode(vm.schemeCode);

                getVerifySelectDetails();
            } else {
                $state.go('rbi_bnd_brk_payment.verify');
            }
        }
        else if ($state.current.name == 'rbi_bnd_brk_payment.delete_detail') {
            if ($stateParams.obj) {
                 vm.schemeCode = $stateParams.obj.schemeCode;
                                vm.schemeName = $stateParams.obj.schemeName;
                                vm.brokerId = $stateParams.obj.brokerId;
                                vm.gbsTranId = $stateParams.obj.gbsTranId;
                                vm.fromDate = $stateParams.obj.fromDate;
                                vm.toDate = $stateParams.obj.toDate;

                                getModuleNameFindByCode(vm.schemeCode);

                                getVerifySelectDetails();


            } else {
                $state.go('rbi_bnd_brk_payment.delete');
            }
        }
        else if ($state.current.name == 'rbi_bnd_brk_payment.inquiry_detail') {
            if ($stateParams.obj) {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;
                vm.brokerId = $stateParams.obj.brokerId;
                vm.gbsTranId = $stateParams.obj.gbsTranId;
                vm.fromDate = $stateParams.obj.fromDate;
                vm.toDate = $stateParams.obj.toDate;

                getModuleNameFindByCode(vm.schemeCode);

                getInquiryDetails();

            } else {
                $state.go('rbi_bnd_brk_payment.inquiry');
            }
        }


        function getModuleName() {
                    vm.loader = true;
                    vm.referenceId = 'TAX';
                    var requestData = {
                        'referenceId': vm.referenceId
                    };
                    rbiBondBrokeragePaymentService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                        vm.loader = false;
                        vm.schemeList = response.data.result;
                    }, function (error) {
                        handleErrorResponse(error);
                        vm.loader = false;
                    });
                }


                function transactionValidation(){
                                        var requestData={

                                            'debitAccNo':vm.debitAccNo,
                                            'debitAccNumFlgOut':vm.debitAccNumFlgOut,
                                        }
                                       rbiBondBrokeragePaymentService.transactionValidation(requestData).then(function(response)
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



        function getModuleNameFindByCode(schemeCode) {
                    vm.loader = true;
                    vm.referenceId = 'TAX';
                    var requestData = {
                        'referenceId': vm.referenceId
                    };
                    rbiBondBrokeragePaymentService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                        vm.loader = false;
                        vm.schemeList = response.data.result;
                        for (let i = 0 ;i < vm.schemeList.length;i++){
                          if(vm.schemeList[i].subCode == schemeCode){
                                vm.schemeName = vm.schemeList[i].subCodeDescription;
                           }
                         }

                    }, function (error) {
                        handleErrorResponse(error);
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
           rbiBondBrokeragePaymentService.getReferenceData(requestData).then(function(response){
              vm.holderTypeList = response.data.result;
            },function(error) {
               handleErrorResponse(error);
             });
        }

        function getAddBrokerTorch(){
            vm.loader = true;
                var requestData = {
                "schemeCode": vm.schemeCode,
                };
               rbiBondBrokeragePaymentService.getValidationAdd(requestData).then(function(response){
                    vm.loader = false;
                    vm.brokerTorchList = response.data.result;
                    angular.element('#brokerModal').modal('toggle');

                },function(error) {
                vm.loader = false;
                   handleErrorResponse(error);
                 });

        }

        function getAddValidation(){
            vm.loader = true;
                var requestData = {
                "schemeCode": vm.schemeCode,
                "brokerId": vm.brokerId,
                "fromDate": $filter('date')(vm.fromDate, "dd/MM/yyyy"),
                "toDate": $filter('date')(vm.toDate, "dd/MM/yyyy"),
                };
               rbiBondBrokeragePaymentService.getValidationAdd(requestData).then(function(response){

                vm.getDetails = response.data.result;
                vm.brokerDetailsList = vm.getDetails.brokerDetailsList;
                vm.creditAccNo = vm.getDetails.creditAccNo;
                vm.debitAccNo = vm.getDetails.debitAccNo;
                vm.debitAccNumFlgOut=vm.getDetails.debitAccNumFlgOut;

                vm.totalBrokerageAmount = 0;
                vm.totalPaidAmount = 0;
                angular.forEach(vm.brokerDetailsList, function(item){
                       item.isSelected = true;
                       vm.totalBrokerageAmount = vm.totalBrokerageAmount + parseInt(item.brokerAmount);
                       vm.totalPaidAmount = vm.totalPaidAmount + parseInt(item.paidAmount);
                });

                vm.loader = false;
                },function(error) {
                vm.loader = false;
                   handleErrorResponse(error);
                   $state.go('rbi_bnd_brk_payment.add');
                 });

        }

        function calculateTotalPaidAmount(){
            vm.totalPaidAmount = 0;
            angular.forEach(vm.brokerDetailsList, function(item){
                   vm.totalPaidAmount = vm.totalPaidAmount + parseInt(item.paidAmount);
            });
        }

        function addSubmit() {
            transactionValidation();
             Swal.fire({
                title : "Are you sure?",
                text : "Do you want to Really Add this record?",
                type : "warning",
                showCancelButton : true,
                cancelButtonClass : "btn-cancel",
                confirmButtonClass : "btn-primary",
                confirmButtonText : "Yes",
                closeOnConfirm : false
            },
            function() {

            vm.loader= true;

             var requestData = {
                "schemeCode": vm.schemeCode,
                "brokerId": vm.brokerId,
                "fromDate": $filter('date')(vm.fromDate, "dd/MM/yyyy"),
                "toDate": $filter('date')(vm.toDate, "dd/MM/yyyy"),
                "brokerDetailsList": vm.brokerDetailsList,
                "totalAmount": vm.totalPaidAmount,
                "creditAccNo": vm.creditAccNo,
                "debitAccNo": vm.debitAccNo,
             };

              rbiBondBrokeragePaymentService.addSubmit(requestData).then( (response) => {
                    vm.loader=false;
                    vm.addResultResponse = response.data.result;
                    Swal.fire("Success!", "Brokerage Payment Added Successfully\nGBS Tran Id:" + vm.addResultResponse.gbsTranId , "success");
                    location.href = '#!/rbi_bnd_brk_payment/action/inquiry';
                                                             }
                 ,function(error) {
                   vm.loader = false;
                      handleErrorResponse(error);
                      $state.go('rbi_bnd_brk_payment.add');
                    });


            });

    }

        function inquiryBrokerTorch(){
                vm.loader = true;
                    var requestData = {
                    "schemeCode": vm.schemeCode,
                    };
                   rbiBondBrokeragePaymentService.inquiry(requestData).then(function(response){
                        vm.loader = false;
                        vm.brokerTorchList = response.data.result;
                        angular.element('#brokerModal').modal('toggle');

                    },function(error) {
                    vm.loader = false;
                       handleErrorResponse(error);
                     });
        }

            function inquiryGbsTranIDTorch(){
                    vm.loader = true;
                        var requestData = {
                        "schemeCode": vm.schemeCode,
                        "brokerId": vm.brokerId,
                        };
                       rbiBondBrokeragePaymentService.inquiry(requestData).then(function(response){
                            vm.loader = false;
                            vm.gbsTranIdTorchList = response.data.result;
                            angular.element('#gbsTranModal').modal('toggle');

                        },function(error) {
                        vm.loader = false;
                           handleErrorResponse(error);
                         });
            }

        function getInquiryDetails() {
                vm.loader = true;
                    var requestData = {
                    "schemeCode": vm.schemeCode,
                    "brokerId": vm.brokerId,
                    "gbsTranId": vm.gbsTranId,
                    "fromDate": vm.fromDate,
                    "toDate": vm.toDate,
                    };
                   rbiBondBrokeragePaymentService.inquiry(requestData).then(function(response){

                        vm.getDetails  = response.data.result;

                        vm.brokerDetailsList  = vm.getDetails.brokerDetailsList;

                        vm.totalBrokerageAmount = 0;
                        vm.totalPaidAmount = 0;
                        angular.forEach(vm.brokerDetailsList, function(item){
                               item.isSelected = true;
                                vm.totalBrokerageAmount = vm.totalBrokerageAmount + parseInt(item.brokerAmount);
                                vm.totalPaidAmount = vm.totalPaidAmount + parseInt(item.paidAmount);

                        });

                        vm.loader = false;

                    },function(error) {
                    vm.loader = false;
                       handleErrorResponse(error);
                       $state.go('rbi_bnd_brk_payment.inquiry');
                     });
        }

        function verifySelectBrokerTorch(){
                vm.loader = true;
                    var requestData = {
                    "schemeCode": vm.schemeCode,
                    };

                   rbiBondBrokeragePaymentService.verifySelect(requestData).then(function(response){
                        vm.loader = false;
                        vm.brokerTorchList = response.data.result;
                        angular.element('#brokerModal').modal('toggle');

                    },function(error) {
                    vm.loader = false;
                       handleErrorResponse(error);
                     });

        }

            function verifyGbsTranIDTorch(){
                    vm.loader = true;
                        var requestData = {
                        "schemeCode": vm.schemeCode,
                        "brokerId": vm.brokerId,
                        };
                       rbiBondBrokeragePaymentService.verifySelect(requestData).then(function(response){
                            vm.loader = false;
                            vm.gbsTranIdTorchList = response.data.result;
                            angular.element('#gbsTranModal').modal('toggle');

                        },function(error) {
                        vm.loader = false;
                           handleErrorResponse(error);
                         });
            }

        function getVerifySelectDetails() {
                vm.loader = true;
                 var requestData = {
                     "schemeCode": vm.schemeCode,
                     "brokerId": vm.brokerId,
                     "gbsTranId": vm.gbsTranId,
                     "fromDate": vm.fromDate,
                     "toDate": vm.toDate,
                     };
                   rbiBondBrokeragePaymentService.inquiry(requestData).then(function(response){

                      vm.getDetails  = response.data.result;

                        vm.brokerDetailsList  = vm.getDetails.brokerDetailsList;

                        vm.totalBrokerageAmount = 0;
                        vm.totalPaidAmount = 0;
                        angular.forEach(vm.brokerDetailsList, function(item){
                               item.isSelected = true;
                                vm.totalBrokerageAmount = vm.totalBrokerageAmount + parseInt(item.brokerAmount);
                                vm.totalPaidAmount = vm.totalPaidAmount + parseInt(item.paidAmount);

                        });

                        vm.loader = false;

                    },function(error) {
                      vm.loader = false;
                       handleErrorResponse(error);
                       $state.go('rbi_bnd_brk_payment.verify');
                     });
        }

        function deleteSubmit() {


//                 Swal.fire({
//                        title : "Are you sure?",
//                        text : "Do you want to Really Delete this record?",
//                        type : "warning",
//                        showCancelButton : true,
//                        cancelButtonClass : "btn-cancel",
//                        confirmButtonClass : "btn-primary",
//                        confirmButtonText : "Yes",
//                        closeOnConfirm : false
//                    },
//                 function() {
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
                            vm.loader = true;
                            var requestData = {
                            "schemeCode": vm.schemeCode,
                             "gbsTranId": vm.gbsTranId,
                            };
                           rbiBondBrokeragePaymentService.deleteSubmit(requestData).then(function(response){
                                vm.loader = false;
                                Swal.fire("Success", "Brokerage Payment Deleted Successfully", "success");
                            },function(error) {
                              vm.loader = false;
                               handleErrorResponse(error);
                               $state.go('rbi_bnd_brk_payment.inquiry_detail');
                             });

                             }

                });




        }

        function verifySubmit() {

//             Swal.fire({
//                                        title : "Are you sure?",
//                                        text : "Do you want to Really Verify this record?",
//                                        type : "warning",
//                                        showCancelButton : true,
//                                        cancelButtonClass : "btn-cancel",
//                                        confirmButtonClass : "btn-primary",
//                                        confirmButtonText : "Yes",
//                                        closeOnConfirm : false
//                                    },
//                                 function() {



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
                                         "schemeCode": vm.schemeCode,
                                         "gbsTranId": vm.gbsTranId,
                                         };
                                        rbiBondBrokeragePaymentService.verifySubmit(requestData).then(function(response){
                                             vm.loader = false;
                                             var message = "Brokerage Payment Verified Successfully\nCBS Tran Id: " + response.data.result.cbsTranId;
                                             Swal.fire("Success", message , "success");
                                             $state.go('rbi_bnd_brk_payment.inquiry');

                                         },function(error) {
                                           vm.loader = false;
                                            handleErrorResponse(error);
                                            $state.go('rbi_bnd_brk_payment.verify');
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




    }

})();