(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('rbibondTrailRunBrokerageController',
        rbibondTrailRunBrokerageController);

    rbibondTrailRunBrokerageController.$inject = ['$scope', '$filter', '$state',
        '$stateParams', '$location', '$rootScope', '$timeout', 'rbibondTrailRunBrokerageService', 'commonService'
    ];

    function rbibondTrailRunBrokerageController($scope, $filter, $state, $stateParams,
        $location, $rootScope, $timeout, rbibondTrailRunBrokerageService, commonService) {

		var vm = this;

        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
        vm.alert = alert;
          // Initialize pagination settings
          vm.pageSizeOptions = [5, 10, 25, 50, 100];
          vm.itemsPerPage = 10; // Default items per page for first table
          vm.currentPage1 = 1;

          vm.pageSizeOptionsBLA = [5, 10, 25, 50];
          vm.itemsPerPageBLA = 10; // Default items per page for second table
          vm.currentPage2 = 1;

          // Initialize search models
          vm.search1 = '';
          vm.search2 = '';
//        vm.currentPageBla = 1;
        vm.getRemarksList = getRemarksList;
        vm.selectionError = false; // To control error visibility

        vm.convertLabelValue = convertLabelValue;
        vm.getModuleName = getModuleName;
        vm.getModuleNameFindByCode = getModuleNameFindByCode;
        vm.getAgencyCodeAddTor = getAgencyCodeAddTor;
        vm.getAgencyCodeVerTor = getAgencyCodeVerTor;
        vm.getAddBrokerTorch = getAddBrokerTorch;
        vm.getAddValidation = getAddValidation;
        vm.calculateTotalPaidAmount = calculateTotalPaidAmount;
        vm.addSubmit = addSubmit;
        vm.getTrailRun = getTrailRun;
        vm.getTrailRunVerDetail = getTrailRunVerDetail;
        vm.download = download;
        vm.downloadVerify = downloadVerify;
        vm.inquiryGbsTranIDTorch = inquiryGbsTranIDTorch;
        vm.getInquiryDetails = getInquiryDetails;
        vm.solDatee = $rootScope.globals.currentUser.systemDate;
        vm.solDate = $filter('date')(new Date(vm.solDatee), 'dd/MM/yyyy');
        vm.currentDate = new Date();
        vm.currentDate = $filter("date")(vm.currentDate, "dd/MM/yyyy");
        vm.optionToggled = optionToggled;
        vm.verifySelectBrokerTorch = verifySelectBrokerTorch;
        vm.verifyGbsTranIDTorch = verifyGbsTranIDTorch;
        vm.getVerifySelectDetails = getVerifySelectDetails;

        vm.verifySubmit = verifySubmit;
        vm.deleteSubmit = deleteSubmit;
vm.textableDatePicker=textableDatePicker;
vm.autoslashing=autoslashing;
vm.futureNotAllowed  =   futureNotAllowed;



function textableDatePicker(id,valueDate)
{
 commonService.textableDatePicker(id,valueDate);
}


function autoslashing(id)
{
 commonService.autoslashing(id);
}

function futureNotAllowed(id,valueDate)     {
                 commonService.futureNotAllowed(id,valueDate);
                }




       /* if ($state.current.name == 'rbibond_trail_run_brokerage.add') {
            if ($stateParams.obj) {

		        vm.schemeCode = $stateParams.obj.schemeCode;
		        vm.schemeName = $stateParams.obj.schemeName;
		        vm.agencyCode = $stateParams.obj.agencyCode;
		        vm.fromDate = $stateParams.obj.fromDate;
		        vm.toDate = $stateParams.obj.toDate;

                getTrailRun('submit');

            } else {
                $state.go('rbi_bnd_brk_payment.add');
            }
        }**/
         if ($state.current.name == 'rbibond_trail_run_brokerage.verify_detail') {
            if ($stateParams.obj) {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.agencyCode = $stateParams.obj.agencyCode;
                vm.schemeName = $stateParams.obj.schemeName;



                getTrailRunVerDetail('submit');
            } else {
                $state.go('rbibond_trail_run_brokerage.verify');
            }
        }
      /**  else if ($state.current.name == 'rbi_bnd_brk_payment.delete_detail') {
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

*/
         function getRemarksList() {
                             vm.referenceId = '056';
                             var requestData = { 'referenceId': vm.referenceId };

                             rbibondTrailRunBrokerageService.getRemarksList(requestData).then(function(response) {
                                 vm.remarksList = response.data.result || []; // Ensure it's an array
                             }, function(error) {
                                 handleErrorResponse(error);
                             });
                         }

        function getModuleName() {
                    vm.loader = true;
                    vm.referenceId = 'TAX';
                    var requestData = {
                        'referenceId': vm.referenceId
                    };
                    rbibondTrailRunBrokerageService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                        vm.loader = false;
                        vm.schemeList = response.data.result;
                    }, function (error) {
                        handleErrorResponse(error);
                        vm.loader = false;
                    });
                }
//vm.fromToDateValidation();
vm.dateFromValidation=dateFromValidation;
                  function dateFromValidation() {
                  			    vm.dateFromOptions = {
                                       maxDate: vm.toDate ? new Date(vm.toDate) : new Date(),
                                       showWeeks: false,
                                     };
                  		}
                  		vm.dateToValidation=dateToValidation;
  function dateToValidation(fromDate) {
                  		vm.dateToOptions = {
                                minDate: new Date(fromDate),
                                maxDate: new Date(),
                                showWeeks: false,
                              };
                  		}


        function getModuleNameFindByCode(schemeCode) {
                    vm.loader = true;
                    vm.referenceId = 'TAX';
                    var requestData = {
                        'referenceId': vm.referenceId
                    };
                    rbibondTrailRunBrokerageService.getRbiBondModuleNameDetails(requestData).then(function (response) {
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
vm.rejectReason=rejectReason;
function rejectReason() {

 let anySelected = vm.trailRunBrokerDetail.some(item => item.selected);

            if (!anySelected) {
                    var msg = 'Please select a bla to verify';
                    Swal.fire('Warning!', msg, 'warning');
                } else {
                    vm.selectedBlaList = [];
                    angular.forEach(vm.trailRunBrokerDetail, function(item) {
                        if (item.selected) {
                            vm.selectedBlaList.push({
                                branchRefNo: item.brokerCode
                            });
                        }
                    });
  angular.element('#permissionMappingDelete').modal('toggle');
  }
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
           rbibondTrailRunBrokerageService.getReferenceData(requestData).then(function(response){
              vm.holderTypeList = response.data.result;
            },function(error) {
               handleErrorResponse(error);
             });
        }
// Toggle select all checkboxes for a table
vm.toggleSelectAll = function(dataKey, selectAllKey) {
    var isSelected = vm[selectAllKey];
    angular.forEach(vm[dataKey], function(item) {
        item.selected = isSelected;
    });
};

// Update select all checkbox when individual items are checked
vm.updateSelectAll = function(dataKey, selectAllKey) {
    vm[selectAllKey] = vm[dataKey].every(function(item) {
        return item.selected;
    });
};

// Get selected items from a table
vm.getSelectedItems = function(dataKey) {
    return vm[dataKey].filter(function(item) {
        return item.selected;
    });
};
        function getAddBrokerTorch(){
            vm.loader = true;
                var requestData = {
                "schemeCode": vm.schemeCode,
                };
               rbibondTrailRunBrokerageService.getValidationAdd(requestData).then(function(response){
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
               rbibondTrailRunBrokerageService.getValidationAdd(requestData).then(function(response){

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

        function configDataTable() {
                            vm.getTitleDescription = {
                                title: 'RBI BOND Trial run for Brokerage',
                                solId: $rootScope.globals.currentUser.solId,
                                solDesc: $rootScope.globals.currentUser.solDesc
                            }
                            vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
                        }
// Get dynamic page size options based on data length
vm.getPageSizeOptions = function(dataLength) {
    var options = [5, 10, 25, 50];
    if (dataLength > 50) options.push(100);
    if (dataLength > 100) options.push(dataLength);
    return options;
};
        function addSubmit() {
            transactionValidation();
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

              rbibondTrailRunBrokerageService.addSubmit(requestData).then( (response) => {
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
}

            });

    }

        function getAgencyCodeAddTor(){
                vm.loader = true;
                    var requestData = {
                    "schemeCode": vm.schemeCode,
                    };
                   rbibondTrailRunBrokerageService.getAgencyCodeAddTor(requestData).then(function(response){
                        vm.loader = false;
                        vm.AgencyCodeTorchList = response.data.result;
                        angular.element('#AgencyCodeModal').modal('toggle');

                    },function(error) {
                    vm.loader = false;
                       handleErrorResponse(error);
                     });
        }
function getAgencyCodeVerTor(){
                vm.loader = true;
                    var requestData = {
                    "schemeCode": vm.schemeCode,
                    };
                   rbibondTrailRunBrokerageService.getAgencyCodeVerTor(requestData).then(function(response){
                        vm.loader = false;
                        vm.AgencyCodeTorchList = response.data.result;
                        angular.element('#AgencyCodeModal').modal('toggle');

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
                       rbibondTrailRunBrokerageService.inquiry(requestData).then(function(response){
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
                   rbibondTrailRunBrokerageService.inquiry(requestData).then(function(response){

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

                   rbibondTrailRunBrokerageService.verifySelect(requestData).then(function(response){
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
                       rbibondTrailRunBrokerageService.verifySelect(requestData).then(function(response){
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
                   rbibondTrailRunBrokerageService.inquiry(requestData).then(function(response){

                      vm.getDetails  = response.data.result;

                        vm.brokerDetailsList  = vm.getDetails.brokerDetailsList;

                        vm.totalBrokerageAmount = 0;
                        vm.totalPaidAmount = 0;
                        angular.forEach(vm.brokerDetailsList, function(item){
                               item.isSelected = true;
                                vm.totalBrokerageAmount = vm.totalBrokerageAmount + parseInt(item.brokerAmount);
                                vm.totalPaidAmount = vm.totalPaidAmount + parseInt(item.paidAmount);

                        });
                              configDataTable();

                        vm.loader = false;

                    },function(error) {
                      vm.loader = false;
                       handleErrorResponse(error);
                       $state.go('rbi_bnd_brk_payment.verify');
                     });
        }


        function verifySubmit() {

            let anySelected = vm.trailRunBrokerDetail.some(item => item.selected);

            if (!anySelected) {
                    var msg = 'Please select a bla to verify';
                    Swal.fire('Warning!', msg, 'warning');
                } else {
                    vm.selectedBlaList = [];
                    angular.forEach(vm.trailRunBrokerDetail, function(item) {
                        if (item.selected) {
                            vm.selectedBlaList.push({
                                branchRefNo: item.brokerCode
                            });
                        }
                    });

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

                                         var requestData = {
                                         "schemeCode": vm.schemeCode,
                                         "agencyCodeList": vm.selectedBlaList
                                         };
                                        rbibondTrailRunBrokerageService.verifySubmit(requestData).then(function(response){
                                             vm.loader = false;
                                             var message = "RBI BOND Trial Run for Brokerage Verified Successfully " ;
                                             Swal.fire("Success", message , "success");
                                             $state.go('rbibond_trail_run_brokerage.add');

                                         },function(error) {
                                           vm.loader = false;
                                            handleErrorResponse(error);
                                            location.href = '#!/rbibond_trail_run_brokerage/action/verify';
                                          });

                               } });
			}

}

function deleteSubmit() {


                    vm.loader = true;
            var requestData = {
               "schemeCode": vm.schemeCode,
               "agencyCodeList": vm.selectedBlaList
              };
                    rbibondTrailRunBrokerageService.deleteSubmit(requestData).then(function(response) {
                                    if (response.data.message == 'SUCCESS') {
                                        Swal.fire("Rejected!", "Trail run for Brokerage Rejected Successfully", "success");
                                             $state.go('rbibond_trail_run_brokerage.verify');

                                    } else {
                                        Swal.fire("WARNING!", response.data.message, "error");
                                         $state.go('rbibond_trail_run_brokerage.verify');
                                    }
                                             const backdrop = document.querySelector('.modal-backdrop');
                                                                                                            if (backdrop) {
                                                                                                              backdrop.parentNode.removeChild(backdrop);
                                                                                                              document.body.classList.remove('modal-open');
                                                                                                            }
                                }, function(error){
                                                            vm.loader = false;
                                                            handleErrorResponse(error);
                                                             const backdrop = document.querySelector('.modal-backdrop');
                                                              if (backdrop) {
                                                                backdrop.parentNode.removeChild(backdrop);
                                                                document.body.classList.remove('modal-open');
                                                              }
                                                               location.href = '#!/rbibond_trail_run_brokerage/add';
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
    function getTrailRun(flag) {



                                vm.loader = true;
                                if(flag == 'torch'){
                                          var requestData = {
                                          };
                                          } else  if(flag == 'submit'){
                                          var requestData = {
                                         "schemeCode": vm.schemeCode,
                                         "agencyCode": vm.agencyCode,
                                         "fromDate": $filter('date')(vm.fromDate, "dd-MMM-yyyy"),
                                         "toDate": $filter('date')(vm.toDate, "dd-MMM-yyyy"),
                                          };
                                          }
                              rbibondTrailRunBrokerageService.getTrailRun(requestData).then(function(response) {
                                  vm.loader = false;
                                   if(flag == 'torch'){
                                  vm.schemeNameList = response.data.result;
                                  angular.element('#schemeNameSearcherPopUp').modal('toggle');
                                  } else  if(flag == 'submit'){
                                         var message = "Brokerage Commission Successfully Calculated";
                                             Swal.fire("Success", message , "success");
                                  }
                              }, function(error) {
                                  handleErrorResponse(error);
                                  vm.loader = false;
                              });
                              }
                               function getTrailRunVerDetail(flag) {



                                                              vm.loader = true;
                                                              if(flag == 'torch'){
                                                                        var requestData = {
                                                                        };
                                                                        } else  if(flag == 'submit'){
                                                                        var requestData = {
                                                                       "schemeCode": vm.schemeCode,
                                                                       "agencyCode": vm.agencyCode
                                                                        };
                                                                        }
                                                            rbibondTrailRunBrokerageService.getTrailRunVerDetail(requestData).then(function(response) {
                                                                vm.loader = false;
                                                                 if(flag == 'torch'){
                                                                vm.schemeNameList = response.data.result;
                                                                angular.element('#schemeNameSearcherPopUp').modal('toggle');
                                                                } else  if(flag == 'submit'){
                                                                       vm.trailRunBlaDetail = response.data.result.blaList;
                                                                       vm.trailRunBrokerDetail = response.data.result.brokerList;
                                                                       vm.pageSizeOptions = vm.getPageSizeOptions(vm.trailRunBlaDetail.length);
                                                                       vm.pageSizeOptionsBLA = vm.getPageSizeOptions(vm.trailRunBrokerDetail.length);
/*vm.paginateBrokerList();
vm.paginateBlaList();*/

                                                                }
                                                            }, function(error) {
                                                                vm.loader = false;
                                                                handleErrorResponse(error);
                                                                $state.go('rbibond_trail_run_brokerage.verify');
                                                            });
                                                            }


vm.paginateBrokerList = function () {
//    var start = (vm.currentPage - 1) * vm.itemsPerPage;
//    var end = start + vm.itemsPerPage;
//    vm.trailRunBrokerDetail1 = vm.trailRunBrokerDetail.slice(start, end);
};
vm.paginateBlaList = function () {
//    var startBla = (vm.currentPageBla - 1) * vm.itemsPerPageBla;
//    var endBla = startBla + vm.itemsPerPageBla;
//    vm.trailRunBlaDetail1 = vm.trailRunBlaDetail.slice(startBla, endBla);
};
vm.totalPages = function () {
   // return Math.ceil(vm.trailRunBrokerDetail.length / vm.itemsPerPage);
};
vm.totalPagesBla = function () {
  //  return Math.ceil(vm.trailRunBlaDetail.length / vm.itemsPerPageBla);
};

vm.setPage = function (page) {
//    vm.currentPage = page;
//    vm.paginateBrokerList();
};
vm.setPageBla = function (page) {
//    vm.currentPageBla = page;
//    vm.paginateBlaList();
};

vm.prevPage = function () {
//    if (vm.currentPage > 1) {
//        vm.currentPage--;
//        vm.paginateBrokerList();
//    }
};
vm.prevPageBla = function () {
//      if (vm.currentPageBla > 1) {
//          vm.currentPageBla--;
//          vm.paginateBlaList();
//      }
  };

vm.nextPage = function () {
//    if (vm.currentPage < vm.totalPages()) {
//        vm.currentPage++;
//        vm.paginateBrokerList();
//    }
};
vm.nextPageBla = function () {
//    if (vm.currentPageBla < vm.totalPagesBla()) {
//        vm.currentPageBla++;
//        vm.paginateBlaList();
//    }
};
     /*function download() {



                                vm.loader = true;

                                          var requestData = {
                                         "schemeCode": vm.schemeCode,
                                         "agencyCode": vm.agencyCode,
                                         "fromDate": $filter('date')(vm.fromDate, "dd-MMM-yyyy"),
                                         "toDate": $filter('date')(vm.toDate, "dd-MMM-yyyy"),
                                          };

                              rbibondTrailRunBrokerageService.download(requestData).then(function(response) {
                                  vm.loader = false;

                                         var message = "Brokerage Commission downloaded Successfully";
                                             Swal.fire("Success", message , "success");

                              }, function(error) {
                                  handleErrorResponse(error);
                                  vm.loader = false;
                              });
                              }*/

                              function download(fromDate,toDate,schemeCode,
                                                              agencyCode) {
                                                             var requestData = {
                                                                              "schemeCode": vm.schemeCode,
                                                                              "agencyCode": vm.agencyCode,
                                                                              "fromDate": $filter('date')(vm.fromDate, "dd-MMM-yyyy"),
                                                                              "toDate": $filter('date')(vm.toDate, "dd-MMM-yyyy"),
                                                                              "flag" : "A"

                                                             }

                                                             vm.loader = true;
                                                             rbibondTrailRunBrokerageService.download(requestData).then(
                                                                 function(response) {
                                                                     vm.loader = false;
                                                                      vm.blaResponseList = response.data.result.blaResponseList;
                                                                                vm.brokerResponseList = response.data.result.brokerResponseList;

                                                                                // Call the downloadExcel function after data is loaded
                                                                                $scope.downloadExcel(vm.blaResponseList, vm.brokerResponseList);


                                                                 },
                                                                 function(err) {
                                                                     vm.loader = false;
                                                                     handleErrorResponse(err);
                                                                 }
                                                             )
                                                         }

        function downloadVerify(schemeCode, agencyCode) {
                                                             var requestData = {
                                                                              "schemeCode": vm.schemeCode,
                                                                              "agencyCode": vm.agencyCode,
                                                                              "flag" : "V"
                                                             }

                                                             vm.loader = true;
                                                             rbibondTrailRunBrokerageService.download(requestData).then(
                                                                 function(response) {
                                                                     vm.loader = false;
                                                                       vm.blaResponseList = response.data.result.blaResponseList;
                                                                                 vm.brokerResponseList = response.data.result.brokerResponseList;

                                                                                 // Call the downloadExcel function after data is loaded
                                                                                 $scope.downloadExcel(vm.blaResponseList, vm.brokerResponseList);


                                                                 },
                                                                 function(err) {
                                                                     vm.loader = false;
                                                                     handleErrorResponse(err);
                                                                 }
                                                             )
                                                         }


                                                         $scope.downloadExcel = function (blaResponseList, brokerResponseList) {
                                                             // Prepare the BLA Details sheet
                                                             const blaSheetHeaders = [
                                                                 ['BLA Details Report'], // Title
                                                                 [], // Empty row for spacing
                                                                 ['BLA_No',	'Invest_Amt',	'CAS_Transfer_Date','Maturity_Date','Scheme','CFD','SUBSCRIPTION_ID','APPLICANT_PAN_NO_1','APPLICANT_PAN_NO_2','APPLICANT_PAN_NO_3','Bank_Name','APPLICANT_NAME','Advice_No','Appl_add1','Appl_add2','City','State','Pin_Code','Branch_Name','Rcpt_Date','Group_Code','HDFC_Broker_Code','Broker_Name','Broker Add1','Broker Add2','Broker City','State1','Broker Pin_Code','BPhone','Broker Email_Id','Broker Pan','Payment_Mode','Account_Type_Code','Bank_Account','MICR_CODE','Appl_No','Status','Broker_Status','Gross Broker','GST','Net Brok','Total Amount (Consolidated Amount)']
                                                             ];
                                                             const blaSheetData = blaResponseList.map(item => [
                                                                   item.blaNo || '',
                                                                                                                                  item.investAmt || '',
                                                                                                                                  item.casTransferDate || '',
                                                                                                                                  item.maturityDate || '',
                                                                                                                                  item.scheme || '',
                                                                                                                                  item.cfd || '',
                                                                                                                                  item.branchRefNo || '',
                                                                                                                                  item.panNo || '',
                                                                                                                                  item.applPanNo1 || '',
                                                                                                                                  item.applPanNo2 || '',
                                                                                                                                  item.bankName || '',
                                                                                                                                  item.applicantName || '',
                                                                                                                                  item.adviceNo || '',
                                                                                                                                  item.applAdd1 || '',
                                                                                                                                  item.applAdd2 || '',
                                                                                                                                  item.city || '',
                                                                                                                                  item.state || '',
                                                                                                                                  item.pincode || '',
                                                                                                                                  item.branchName || '',
                                                                                                                                  item.rcptDate || '',
                                                                                                                                  item.groupCode || '',
                                                                                                                                  item.hdfcBrokerCode || '',
                                                                                                                                  item.brokerName || '',
                                                                                                                                  item.brokerAdd1 || '',
                                                                                                                                  item.brokerAdd2 || '',
                                                                                                                                  item.brokerCity || '',
                                                                                                                                  item.state1 || '',
                                                                                                                                  item.brokerPinCode || '',
                                                                                                                                  item.bPhone || '',
                                                                                                                                  item.brokerEmailId || '',
                                                                                                                                  item.brokerPan || '',
                                                                                                                                  item.paymentMode || '',
                                                                                                                                  item.accountTypeCode || '',
                                                                                                                                  item.bankAccount || '',
                                                                                                                                  item.micrCode || '',
                                                                                                                                  item.applNo || '',
                                                                                                                                  item.status || '',
                                                                                                                                  item.deStatus || '',
                                                                                                                                  item.grossBrok || '',
                                                                                                                                  item.gst || '',
                                                                                                                                  item.netBrok || ''
                                                             ]);

                                                             // Combine headers and data for BLA sheet
                                                             const blaFinalSheet = blaSheetHeaders.concat(blaSheetData);

                                                             // Prepare the Broker Details sheet
                                                             const brokerSheetHeaders = [
                                                                 ['Broker Details Report'], // Title
                                                                 [], // Empty row for spacing
                                                                 ['Broker Name','Broker Code','GSTIN Number','Broker PAN','Broker Email id','Total Investment','Gross Brokerage','Net Brokerage','GST']
 // Column Headers
                                                             ];
                                                             const brokerSheetData = brokerResponseList.map(item => [
                                                                 item.brokerName || '',
                                                                 item.brokerCode || '',
                                                                 item.gstinNo || '',
                                                                 item.brokerPan || '',
                                                                 item.brokerEmailId || '',
                                                                 item.totalInvstAmt || '',
                                                                 item.grossBrokerage || '',
                                                                 item.netBrokerage || '',
                                                                 item.gst || ''

                                                             ]);

                                                             // Combine headers and data for Broker sheet
                                                             const brokerFinalSheet = brokerSheetHeaders.concat(brokerSheetData);

                                                             // Create worksheets
                                                             const wsBla = XLSX.utils.aoa_to_sheet(blaFinalSheet);
                                                             const wsBroker = XLSX.utils.aoa_to_sheet(brokerFinalSheet);
// Apply bold style to the headers
    const boldStyle = { font: { bold: true } };

    // Apply bold style to the title row and header row
    wsBla['A1'].s = boldStyle; // Title
    for (let i = 1; i <= blaSheetHeaders[2].length; i++) {
        const cellRef = XLSX.utils.encode_cell({ r: 2, c: i - 1 }); // Header row (index 2)
        if (wsBla[cellRef]) wsBla[cellRef].s = boldStyle;
    }
                                                             // Create a workbook
                                                             const wb = XLSX.utils.book_new();
                                                             XLSX.utils.book_append_sheet(wb, wsBla, 'BLA Details');
                                                             XLSX.utils.book_append_sheet(wb, wsBroker, 'Broker Details');

                                                             // Generate and download the Excel file
                                                             XLSX.writeFile(wb, 'Trail_Run_Added_Data.xlsx');
                                                         };
                         function optionToggled(flag) {
                                    vm.isAllSelected = [];

                                    if(flag == 'ALL'){
                                        if(vm.selectedAllItem){
                                            angular.forEach(vm.trailRunBrokerDetail, function(item) {
                                                item.selected = true;
                                                vm.isAllSelected.push(item.selected)
                                            });
                                        }else{
                                            angular.forEach(vm.trailRunBrokerDetail, function(item) {
                                                item.selected = false;
                                                vm.isAllSelected.push(item.selected)
                                            });
                                        }
                                    }
                                    else{
                                    vm.selectedCount = 0;
                                        angular.forEach(vm.trailRunBrokerDetail, function(item) {
                                            vm.isAllSelected.push(item.selected)
                                                if(item.selected){
                                                   vm.selectedCount = vm.selectedCount + 1;
                                                }
                                        });

                                        if(null!=vm.trailRunBrokerDetail&&(vm.trailRunBrokerDetail.length == vm.selectedCount)){
                                            vm.selectedAllItem = true;
                                        }
                                        else{
                                           vm.selectedAllItem = false;
                                        }
                                    }
                                }


    }






})();