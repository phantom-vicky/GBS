(function() {

'use strict';

        angular.module('gbmUltraApp').controller('bondSubAccClosureBeforeMaturityController',
            bondSubAccClosureBeforeMaturityController);

      bondSubAccClosureBeforeMaturityController.$inject = ['$scope', '$filter', '$state',
            '$stateParams', '$location', '$rootScope', '$timeout', 'bondSubAccClosureBeforeMaturityService', 'commonService'
      ];

      function bondSubAccClosureBeforeMaturityController($scope, $filter, $state, $stateParams,
                  $location, $rootScope, $timeout, bondSubAccClosureBeforeMaturityService, commonService) {

            var vm = this;
            vm.closureType = 'Pre mature withdrawal';
            vm.solDate = $rootScope.globals.currentUser.systemDate;
            vm.addDetails = addDetails;
            vm.getRemarksList = getRemarksList;
            vm.addSubmit = addSubmit;
            vm.formatDate = formatDate;
            vm.updateSelectionForAdd = updateSelectionForAdd;
            vm.inquiry = inquiry;
            vm.downloadFile = downloadFile;
            vm.panValidation = panValidation;
            vm.getModuleNameAdd = getModuleNameAdd;
            vm.getModuleNameVer = getModuleNameVer;
            vm.uploadFile = uploadFile;
            vm.rejectReason = rejectReason;
            vm.getPanTorch = getPanTorch;
            vm.getBLANo = getBLANo;
            vm.verifySubmit = verifySubmit;
            vm.currentPage = 1;
            vm.itemsPerPage = '5';
            vm.panValid = true;
            vm.pageSizeOptions = [10, 25, 50, 100];
            vm.inquiryForPayment = inquiryForPayment;
            vm.toggleAllSelectionForAdd = toggleAllSelectionForAdd;
            vm.constructJsonList = constructJsonList;
            vm.deleteSubmit = deleteSubmit;
            vm.fromToDateValidation = fromToDateValidation;

            function fromToDateValidation() {
                  vm.dateOptions = {
                    minDate: '',
                    maxDate: vm.solDate,
                  };
            }



            if($state.current.name == 'bond_sub_acc_closure_before_maturity.add_detail') {
                if($stateParams.obj){
                    vm.schemeCode = $stateParams.obj.schemeCode;
                    vm.schemeName = $stateParams.obj.schemeName;
                    vm.schemeStatus = $stateParams.obj.schemeStatus;
                    vm.blaNo = $stateParams.obj.blaNo;
                    vm.modeOfHolding = $stateParams.obj.modeOfHolding;
                    vm.getModuleNameAdd('A');
                }
            } else if ($state.current.name == 'bond_sub_acc_closure_before_maturity.inquiry_detail'){
                if($stateParams.obj){
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;
                vm.schemeStatus = $stateParams.obj.schemeStatus;
                vm.blaNo = $stateParams.obj.blaNo;
                vm.panNoName = $stateParams.obj.panNoName;
                vm.name = $stateParams.obj.name;
                vm.inquiry('D');
                }
            } else if ($state.current.name == 'bond_sub_acc_closure_before_maturity.verify_detail'){
                 if($stateParams.obj){
                    vm.schemeCode = $stateParams.obj.schemeCode;
                    vm.schemeName = $stateParams.obj.schemeName;
                    vm.schemeStatus = $stateParams.obj.schemeStatus;
                    vm.blaNo = $stateParams.obj.blaNo;
                    vm.modeOfHolding = $stateParams.obj.modeOfHolding;
                    vm.getModuleNameVer('D');
                 }
            }

            function getModuleNameVer(flag){
                  vm.loader = true;
                  var requestData = {};
                  if(flag == 'S'){
                      requestData.schemeCode = null;
                      requestData.detailsOrSubmit='scheme'
                  } else if (flag == 'B'){
                        requestData.schemeCode = vm.schemeCode;
                        requestData.detailsOrSubmit='blaTorch'
                  } else if (flag == 'D'){
                        requestData.schemeCode = vm.schemeCode;
                        requestData.detailsOrSubmit='scheme'
                        requestData.closureType = 'BM001'
                        requestData.blaNo = vm.blaNo != null ? vm.blaNo : null;
                  }
              bondSubAccClosureBeforeMaturityService.verifyDetails(requestData).then(function (response) {
                    vm.loader = false;
                    vm.schemeList = response.data.result;
                     if(flag=='S')
                         angular.element('#schemeModel').modal('toggle');
                     else if (flag =='B'){
                            angular.element('#subscriptionModel').modal('toggle');
                     } else if (flag == 'D'){
                            vm.responseResultForVer = response.data.result;
                     }
                 }, function (error) {
                     handleErrorResponse(error);
                     $state.go('bond_sub_acc_closure_before_maturity.inquiry');
                     vm.loader = false;
              });
            }

            function deleteSubmit(closureRemarks, listOfRecords, remarks){
//                    Swal.fire({
//                             title: "Are you sure?",
//                             text: "Do you want to Really Reject this record?",
//                             type: "warning",
//                             showCancelButton: true,
//                             cancelButtonClass: "btn-cancel",
//                             confirmButtonClass: "btn-primary",
//                             confirmButtonText: "Yes",
//                             closeOnConfirm: false,
//                           },
//                      function () {


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
                            var requestData = {};
                            requestData.schemeCode = vm.schemeCode;
                            requestData.detailsOrSubmit='reject'
                            requestData.closureType='BM001'
                            requestData.verifyArrayObject= constructJsonList(listOfRecords);
                            requestData.checkerRemarkForClosure = remarks;
                           bondSubAccClosureBeforeMaturityService.verifySubmit(requestData).then(function(response) {
                               vm.loader = false;
                               Swal.fire("Rejected!","Rejected Successfully","success");
                                $state.go('bond_sub_acc_closure_before_maturity.inquiry');
                           }, function(error) {
                               vm.loader = false;
                               if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
                                 vm.noDataFound = error.data.message;
                                 Swal.fire("Warning!",vm.noDataFound,"warning");
                               }
                             else{
                                handleErrorResponse(error);
                                $state.go('bond_sub_acc_closure_before_maturity.verify');
                             }
                           });
                           }
                       });
            }



            function addSubmit(closureRemarks){
                    updateSelectionForAdd();
             if(vm.selectedAccountsForAdd.length>0){
//                Swal.fire({
//                     title: "Are you sure?",
//                     text: "Do you want to Add this record?",
//                     type: "warning",
//                     showCancelButton: true,
//                     cancelButtonClass: "btn-cancel",
//                     confirmButtonClass: "btn-primary",
//                     confirmButtonText: "Yes",
//                     closeOnConfirm: false
//                     }, function(){


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
                          requestData.schemeCode = vm.schemeCode;
                          requestData.panNoName = vm.panNoName;
                          requestData.blaNo = vm.blaNo;
                          requestData.detailsOrSubmit='submit'
                          requestData.closureType='BM001'
                          requestData.recordsToBeVerified= vm.selectedAccountsForAdd
                          requestData.paymentDate=  formatDate(vm.paymentDate);
                          requestData.closureRemark = closureRemarks
                   bondSubAccClosureBeforeMaturityService.addDetails(requestData).then( (response) => {
                   vm.loader = false;
                   vm.addResultResponse = response.data.message;
                   if(vm.addResultResponse=='SUCCESS'){
                   Swal.fire("Success!", " Added SuccessFully ","success");
                   }
                    $state.go('bond_sub_acc_closure_before_maturity.inquiry');
                    },function(error) {
                      vm.loader = false;
                      handleErrorResponse(error);
                      $state.go('bond_sub_acc_closure_before_maturity.add');
                    });
                    }
                });
             } else {
                  Swal.fire("Warning!","Please Select Any One Of The Records", "warning");
                  $state.go('bond_sub_acc_closure_before_maturity.add_detail');
             }
            }

            function constructJsonList(list){
               vm.selectedAccountsForVer=[];
                 list.forEach(function(x) {
                    let arrayOfObjects = {};
                    arrayOfObjects.blaNo = x.blaNo;
                    arrayOfObjects.subId = x.branchRefNo;
                    arrayOfObjects.panNo = x.panNoName;
                    arrayOfObjects.paymentDate = x.paymentDateAtEnd;
                    vm.selectedAccountsForVer.push(arrayOfObjects);
                 });
               return vm.selectedAccountsForVer;
            }


            function verifySubmit(closureRemarks, listOfRecords){
//                Swal.fire({
//                        title: "Are you sure?",
//                        text: "Do you want to Really Verify this record?",
//                        type: "warning",
//                        showCancelButton: true,
//                        cancelButtonClass: "btn-cancel",
//                        confirmButtonClass: "btn-primary",
//                        confirmButtonText: "Yes",
//                        closeOnConfirm: false
//                    },
//                    function() {



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
                        var requestData = {};
                         requestData.schemeCode = vm.schemeCode;
                         requestData.detailsOrSubmit='verify'
                         requestData.closureType='BM001'
                         requestData.verifyArrayObject= constructJsonList(listOfRecords);
                         requestData.checkerRemarkForClosure = closureRemarks
                        bondSubAccClosureBeforeMaturityService.verifySubmit(requestData).then(function(response) {
                            vm.loader = false;
                            Swal.fire("Verified!", "Verified Successfully","success");
                            $state.go('bond_sub_acc_closure_before_maturity.inquiry');
                        }, function(error) {
                            vm.loader = false;
                            if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
                              vm.noDataFound = error.data.message;
                              Swal.fire("Warning!",vm.noDataFound,"warning");
                            }
                          else{
                          handleErrorResponse(error);
                          $state.go("bond_sub_acc_closure_before_maturity.verify");
                          }
                        })

                        }
                });
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
            function formatDate(dateString) {
                if (!dateString || typeof dateString !== 'string') {
                    return '';
                }
                // Take only the first 10 characters (DD/MM/YYYY)
                const trimmedDate = dateString.substring(0, 10).trim();
                // Validate basic format (e.g., 10/09/2025)
                const datePattern = /^(\d{2})\/(\d{2})\/(\d{4})$/;
                const match = trimmedDate.match(datePattern);
                if (!match) {
                    return '';
                }
                const [_, day, month, year] = match;
                // Convert to number and check if valid
                const date = new Date(year, month - 1, day);
                if (isNaN(date.getTime())) {
                    return '';
                }
                const months = ["JAN", "FEB", "MAR", "APR", "MAY", "JUN",
                                "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"];
                return `${String(day).padStart(2, '0')}-${months[date.getMonth()]}-${year}`;
            }


            function getModuleNameAdd(flag) {
                vm.loader = true;
                var requestData = {};
                if(flag == 'S'){
                requestData.schemeCode = null;
                requestData.detailsOrSubmit='torch'
                }else if(flag == 'B') {
                requestData.schemeCode = vm.schemeCode;
                requestData.detailsOrSubmit='torch'
                } else if(flag == 'P'){
                 requestData.schemeCode = vm.schemeCode;
                 requestData.blaNo = vm.blaNo;
                 requestData.detailsOrSubmit='valid'
                } else if(flag == 'A'){
                 requestData.schemeCode = vm.schemeCode;
                 requestData.blaNo = vm.blaNo;
                 requestData.detailsOrSubmit='details'
                 requestData.closureType='BM001'
                }
                bondSubAccClosureBeforeMaturityService.addDetails(requestData).then(function (response) {
                   vm.loader = false;
                   vm.schemeList = response.data.result;
                    vm.objList = response.data.result; // Success PANs
                    vm.failedList = response.data.result1; // Failed PANs
                   vm.panValidationMessage = response.data.message;
                    if(flag=='S')
                        angular.element('#schemeModel').modal('toggle');
                    else if (flag =='B')
                        angular.element('#subscriptionModel').modal('toggle');
                    else if(flag == 'A'){
                        vm.responseResultForAdd = response.data.result;
                        vm.checkAll = true;
                        vm.responseResultForAdd.forEach(function(record) {
                            record.selected = true;
                        });
                    }
 else if (flag == 'P') {
    vm.allPANsValid = true;
if (vm.objList.length === 0 && vm.failedList.length === 0) {
    Swal.fire("Error!", "Invalid response received. No PAN validation data found.", "error");
} else {
    let panMessages = [];
    if (null!=vm.objList&&vm.objList.length > 0) {
        vm.objList.forEach(obj => panMessages.push(`'${obj.oldPan}' - Success`));
    }
    if (null!=vm.failedList&&vm.failedList.length > 0) {
        vm.failedList.forEach(obj => panMessages.push(`'${obj.oldPan}' - Failure`));
    }
    Swal.fire({
        title: "PAN Validation Result",
        text: panMessages.join("\n"),
        icon: vm.panValid ? "success" : "warning"
    });
}
}
                }, function (error) {
                if(error.data.message.toLowerCase().startsWith('trigger')){
                    vm.allPANsValid = true;
                    handleErrorResponse(error);
                    vm.loader = false;
                    }
                    else{
                    vm.allPANsValid = true;
                    handleErrorResponse(error);
                    $state.go('bond_sub_acc_closure_before_maturity.add');
                    vm.loader = false;
                    }
                });
            }

            function addDetails() {
                vm.loader = true;
                var requestData = {
                    'schemeCode': vm.schemeCode,
                    'halfYear':vm.halfYear,
                    'year':vm.year,
                    'detailsOrSubmit':'details'
                };
                bondSubAccClosureBeforeMaturityService.addDetails(requestData).then(function (response) {
                    vm.loader = false;
                    vm.addDetail = response.data.result;
                }, function (error) {
                    handleErrorResponse(error);
                    vm.loader = false;
                    $state.go('bond_sub_acc_closure_before_maturity.add');
                });
            }
            function getBLANo() {
                vm.loader = true;
                var requestData = {
                    'schemeCode': vm.schemeCode,
                    'schemeName': vm.schemeName,
                    'panNum': vm.panNum,
                    'bondHolderName': vm.bondHolderName,
                    'blaNo': vm.blaNo,
                };
                bondSubAccClosureBeforeMaturityService.inquiry(requestData).then(function(response) {
                    vm.loader = false;
                    vm.blaDataList = response.data.result.torchValue;
                    angular.element('#subscriptionModel').modal('toggle');
                }, function(error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                });

            }

            function inquiry(flag) {
                vm.loader = true;
                var requestData = {};
                if(flag == 'S'){
                    requestData.schemeCode = null;
                } else if (flag == 'B'){
                    requestData.schemeCode = vm.schemeCode;
                    requestData.flag = 'B';
                    requestData.name = vm.name != null ? vm.name : null ;
                    requestData.panNoName = vm.panNoName;
//                    requestData.closureType='BM001'
                } else if (flag == 'P'){
                    requestData.schemeCode = vm.schemeCode;
                    requestData.flag = 'P';
                    requestData.name = vm.name != null ? vm.name : null ;
                    requestData.panNoName = vm.panNoName != null ? vm.panNoName : null ;
//                    requestData.closureType='BM001'
                } else {
                    requestData.schemeCode = vm.schemeCode;
                    requestData.flag = 'D';
                    requestData.name = vm.name ;
                    requestData.panNoName = vm.panNoName;
                    requestData.closureType = 'BM001';
                    requestData.blaNo = vm.blaNo;
                }
                bondSubAccClosureBeforeMaturityService.inquiry(requestData).then(function (response) {
                    vm.loader = false;
                    if(flag=='S'){
                        vm.schemeList = response.data.result;
                        angular.element('#schemeModel').modal('toggle');
                    }else if (flag == 'B'){
                        vm.subscriptionList = response.data.result;
                        angular.element('#subscriptionModel').modal('toggle');
                    }else if (flag == 'P'){
                        vm.PanTorch = response.data.result;
                        angular.element('#panModel').modal('toggle');
                    }else {
                        vm.responseResultForInquiry = response.data.result;
                    }

                }, function (error) {
                    handleErrorResponse(error);
                    $state.go('bond_sub_acc_closure_before_maturity.inquiry');
                    vm.loader = false;
                });
            }


            function getPanTorch() {
                var requestData = {
                    'schemeCode': vm.schemeCode,
                    'bondHolderName': vm.bondHolderName,
                    'panNum': vm.panNum

                };
                bondSubAccClosureBeforeMaturityService.inquiry(requestData).then(function(response) {
                    vm.loader = false;
                    vm.PanTorch = response.data.result.torchValue;
                    angular.element('#panModel').modal('toggle');
                }, function(error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                });
            }

            function downloadFile(){
                 vm.loader = true;
                   var requestData = {};
                   requestData.schemeCode = vm.schemeCode;
                   requestData.paymentDate = $filter('date')(vm.paymentDate, "dd-MMM-yyyy");
                bondSubAccClosureBeforeMaturityService.downloadFile(requestData).then(function (response) {
                    vm.loader = false;
                    vm.message = response.data.message;
                    if(vm.message == 'SUCCESS'){
                        vm.fileName = response.data.result.fileName;
                        vm.fileData = response.data.result.fileData;
                        saveAs(getBlobFromBase64String(vm.fileData), vm.fileName);
                        Swal.fire("Success!", vm.fileName +"\n Downloaded Successfully", "success");
                    } else
                        Swal.fire("Warning!",vm.message,"warning");
                    }, function (error) {
                        handleErrorResponse(error);
                        $state.go('bond_sub_acc_closure_before_maturity.inquiry');
                        vm.loader = false;
                     }
                );
            }

            function uploadFile(textFile){
                  vm.loader = true;
                  var fd = new FormData();
                  let formattedDate = $filter('date')(vm.paymentDate, "dd-MMM-yyyy");
                  fd.append('textFile', textFile);
                  fd.append('schemeCode',vm.schemeCode);
                  fd.append('paymentDate', formattedDate);

             bondSubAccClosureBeforeMaturityService.uploadTextFile(fd).then(function (response) {
                           vm.loader = false;
                           vm.responseMessage = response.data.message;
                           if(vm.responseMessage == 'SUCCESS'){
                                Swal.fire("Uploaded!"," Payment File Uploaded Successfully. ", "success");
                           }
                            }, function (error){
                                Swal.fire("Error!", error.data.message, "error");
                                $state.go('bond_sub_acc_closure_before_maturity.inquiry');
             });
            }

            function panValidation(){
               console.log("Pan Validation Function");
                vm.panValid = false;
            }
            function getRemarksList() {
                  vm.referenceId = '056';
                  var requestData = { 'referenceId': vm.referenceId };

                  bondSubAccClosureBeforeMaturityService.getRemarksList(requestData).then(function(response) {
                      vm.remarksList = response.data.result || []; // Ensure it's an array
                  }, function(error) {
                      handleErrorResponse(error);
                  });
            }


            function rejectReason() {
             angular.element('#permissionMappingDelete').modal('toggle');
            }
            function inquiryForPayment(){
                vm.loader = true;
               bondSubAccClosureBeforeMaturityService.inquiryFromPaymentScreen().then(function (response) {
                vm.loader = false;
//                let array = [];
//                array.push(response.data.result[0]);
//                vm.payload = array;
                vm.payload = response.data.result;
                    vm.verifyDetails = response.data.result;
                    vm.checkBoxPayload = response.data.result;
                    vm.verifyDetailsForPagination = response.data.result;
                    if(Array.isArray(vm.verifyDetailsForPagination)){
                       vm.checkAll = true
                       vm.verifyDetailsForPagination.map((element)=>{
                            element.selected = true;
                            return element;
                       });
                    } else
                        console.warn("Invalid response data");
                     vm.totalPagesForVer = Math.ceil(vm.verifyDetailsForPagination.length / vm.pageSizeForVer);
                     console.log("totalPages for ver : "+vm.totalPagesForVer);
                     vm.updatePaginationForVer();
                     vm.loader = false;

               }, function (error) {
                    handleErrorResponse(error);
                    $state.go('bond_sub_acc_closure_before_maturity.inquiry');
                    vm.loader = false;
                });
            }

            function updateSelectionForAdd(){
              vm.selectedAccountsForAdd = [];
              vm.otherAccountsForAdd = [];
               if (Array.isArray(vm.responseResultForAdd)) {
                vm.responseResultForAdd.forEach(function(x) {
                    vm.panNoName = x.panNoName;
                    vm.paymentDate = x.paymentDateAtEnd;
                    if (x.selected) {
                      vm.selectedAccountsForAdd.push(x.branchRefNo);
                      if(vm.responseResultForAdd.length === vm.selectedAccountsForAdd.length)
                      vm.checkAll = true;
                      else
                      vm.checkAll = false;
                    } else {
                      vm.otherAccountsForAdd.push(x.refNo);
                      vm.checkAll = false;
                    }
                });
               } else
                  console.warn("preMaturity is not defined or not an array.");
            }

            function toggleAllSelectionForAdd() {
                if (vm.checkAll) {
                    vm.responseResultForAdd.forEach(function(record) {
                    record.selected = true;
                    });
                } else {
                     vm.responseResultForAdd.forEach(function(record) {
                         record.selected = false;
                    });
                }
            }



                            vm.textableDatePicker=textableDatePicker;
                            vm.autoslashing=autoslashing;
                            vm.futureNotAllowed=futureNotAllowed;


                    function textableDatePicker(id,valueDate)
                    {
                     commonService.textableDatePicker(id,valueDate);
                    }


                    function autoslashing(id)

                    {
                     commonService.autoslashing(id);

                    }



                    function futureNotAllowed(id,valueDate)
                    {
                     commonService.futureNotAllowed(id,valueDate);
                    }



      }
})();