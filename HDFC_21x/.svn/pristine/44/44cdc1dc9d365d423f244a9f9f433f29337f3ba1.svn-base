(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('bondActualInterestPaymentController',
        bondActualInterestPaymentController);

    bondActualInterestPaymentController.$inject = ['$scope', '$filter', '$state',
        '$stateParams', '$location', '$rootScope', '$timeout', 'bondActualInterestPaymentService', 'commonService'
    ];

    function bondActualInterestPaymentController($scope, $filter, $state, $stateParams,
            $location, $rootScope, $timeout, bondActualInterestPaymentService, commonService) {

    		var vm = this;
    		vm.getModuleNameAdd = getModuleNameAdd;
    		vm.inquiry = inquiry;
    		vm.downloadFile = downloadFile;
    		vm.addDetails = addDetails;
    		vm.solDate = $filter('date')($rootScope.globals.currentUser.systemDate, "dd/MM/yyyy");
    		vm.addSubmit = addSubmit;
    		vm.verifySubmit = verifySubmit;
    		vm.rejectReason = rejectReason;
    		vm.rejectDetails = rejectDetails;
    		vm.downloadExcelFile = downloadExcelFile;
    		vm.moduleName = 'RBI Bond';
    		vm.verifySelect = verifySelect;
    		vm.uploadFile = uploadFile;
    		vm.getMergeUnMergeDownload = getMergeUnMergeDownload;

//    		For Inquiry Pagination Starts
            vm.inquiryDetails = [];
            vm.inquiryDetailsPagination = [];
            vm.pageSizeOptions = [10, 25, 50, 100];
            vm.pageSizeForInq = vm.pageSizeOptions[0];
            vm.currentPageForInq = 1;
            vm.maxPagesToShowForInq = 10;
            vm.searchTextForInq = '';
//         For Inquiry Pagination Ends
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
//        For Add Pagination Starts
            vm.addDetail = [];
            vm.addDetailsPagination = [];
            vm.pageSizeForAdd = vm.pageSizeOptions[0];
            vm.currentPageForAdd = 1;
            vm.maxPagesToShowForAdd = 10;
            vm.searchTextForAdd = '';
//       For Add Pagination Ends

//      For Verify Pagination Starts
            vm.verifyDetails = [];
            vm.verifyDetailsPagination = [];
            vm.pageSizeForVer = vm.pageSizeOptions[0];
            vm.currentPageForVer = 1;
            vm.maxPagesToShowForVer = 10;
            vm.searchTextForVer = '';
//      For Verify Pagination Ends

//       Not Selected Accounts For Rejection/Verification.
         vm.otherAccounts = [];
         vm.selectedAccounts = [];



    		if($state.current.name == 'bond_actual_interest_payment.add_detail') {
                if($stateParams.obj){
                    vm.schemeCode = $stateParams.obj.schemeCode;
                    vm.bodDate = $stateParams.obj.bodDate;
                    vm.halfYear = $stateParams.obj.halfYear;
                    vm.year = $stateParams.obj.year;
                    vm.schemeName = $stateParams.obj.schemeName;
                    vm.schemeStatus = $stateParams.obj.schemeStatus;
                    vm.addDetails();
                }
            } else if ($state.current.name == 'bond_actual_interest_payment.inquiry_detail'){
                if($stateParams.obj){
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;
                vm.schemeStatus = $stateParams.obj.schemeStatus;
                vm.bodDate = $stateParams.obj.bodDate;
                vm.halfYear = $stateParams.obj.halfYear;
                vm.year = $stateParams.obj.year;
                vm.inquiry();
                }
            } else if ($state.current.name == 'bond_actual_interest_payment.verify_detail'){
                 if($stateParams.obj){
                 vm.schemeCode = $stateParams.obj.schemeCode;
                 vm.schemeName = $stateParams.obj.schemeName;
                 vm.schemeStatus = $stateParams.obj.schemeStatus;
                 vm.bodDate = $stateParams.obj.bodDate;
                 vm.halfYear = $stateParams.obj.halfYear;
                 vm.year = $stateParams.obj.year;
                 vm.verifySelect();
                 }
            }

         function getModuleNameAdd(flag) {
            vm.loader = true;
            var requestData = {};
            if(flag == 'S'){
            requestData.schemeCode = null;
            requestData.detailsOrSubmit='details'
            }else{
            requestData.schemeCode = vm.schemeCode;
            requestData.detailsOrSubmit='details'
            }

            bondActualInterestPaymentService.addDetails(requestData).then(function (response) {
                vm.loader = false;
                vm.schemeList = response.data.result;
                if(flag=='S')
                    angular.element('#schemeModel').modal('toggle');
                else
                    angular.element('#halfYearModel').modal('toggle');

            }, function (error) {
                vm.loader = false;
                handleErrorResponse(error);
                $state.go('bond_actual_interest_payment.inquiry');
            });
        }


        function inquiry(flag) {
            vm.loader = true;
            var requestData = {};
            if(flag == 'S'){
            requestData.schemeCode = null;
            requestData.detailsOrSubmit='details'
            }else if(flag=='I'){
            requestData.schemeCode = vm.schemeCode;
            requestData.detailsOrSubmit='details'
            }else{
            requestData.schemeCode = vm.schemeCode;
            requestData.halfYear = vm.halfYear;
            requestData.year = vm.year;
            }

            bondActualInterestPaymentService.inquiry(requestData).then(function (response) {
                vm.loader = false;
                if(flag=='S'){
                    vm.schemeList = response.data.result;
                    angular.element('#schemeModel').modal('toggle');
                } else if(flag=='I'){
                    vm.schemeList = response.data.result;
                    angular.element('#halfYearModel').modal('toggle');
                }else{
                 vm.inquiryDetails = response.data.result;
                 vm.inquiryDetailsForPagination = response.data.result;
                 vm.totalPagesForInq = Math.ceil(vm.inquiryDetailsForPagination.length / vm.pageSizeForInq);
                 console.log("totalPages: "+vm.totalPagesForInq);
                 vm.updatePaginationForInq();
                 vm.loader = false;
                }

            }, function (error) {
                vm.loader = false;
                handleErrorResponse(error);
                $state.go('bond_actual_interest_payment.inquiry');
            });
        }

        function uploadFile(textFile){
          vm.loader = true;
          var fd = new FormData();
          fd.append('textFile', textFile);
          fd.append('schemeCode',vm.schemeCode);
          fd.append('halfYear',vm.halfYear);
          fd.append('year',vm.year);

         bondActualInterestPaymentService.uploadTextFile(fd)
            .then(function (response) {
                   vm.loader = false;
                   vm.responseMessage = response.data.message;
              Swal.fire("Uploaded!", "Bond Actual Interest Payment File Uploaded Successfully.", "success");
                    }, function (response){
                        vm.loader = false;
                        Swal.fire("Error!", response.data.message, "error");
                        $state.go('bond_ctual_interest_payment.inquiry');
                    });
        }

        function downloadFile(){
         vm.loader = true;
           var requestData = {};
           requestData.schemeCode = vm.schemeCode;
           requestData.halfYear = vm.halfYear;
           requestData.year = vm.year;
        bondActualInterestPaymentService.downloadFile(requestData).then(function (response) {
            vm.loader = false;
            vm.fileName = response.data.result.fileName;
            vm.fileData = response.data.result.fileData;
            vm.message = response.data.message;
            if(vm.message == 'SUCCESS'){
            saveAs(getBlobFromBase64String(vm.fileData), vm.fileName);
            Swal.fire("Success!", vm.fileName +"\n Downloaded Successfully", "success");
            }else{
            Swal.fire("Warning!",vm.message,"warning");
            }
            }, function (error) {
            vm.loader = false;
          if (error.data.message == "No Data Found" || error.data.message == "NDF") {
          vm.noDataFound = error.data.message;
          Swal.fire("Warning!", vm.noDataFound, "warning");
          $state.go('bond_actual_interest_payment.inquiry');
          } else {
            handleErrorResponse(error);
            $state.go('bond_actual_interest_payment.inquiry');
          }
            });

        }
        function getMergeUnMergeDownload(schemeCode,halfYear,year,downloadType){
        if(!downloadType){
        return false;
        }
                 vm.loader = true;
                   var requestData = {};
                   requestData.schemeCode = schemeCode;
                   requestData.halfYear = halfYear;
                   requestData.year = year;
                   requestData.flag = downloadType;
                bondActualInterestPaymentService.getMergeUnMergeDownload(requestData).then(function (response) {
                    vm.loader = false;
                    vm.fileName = response.data.result.fileName;
                    vm.fileData = response.data.result.fileData;
                    vm.message = response.data.message;
                    if(vm.message == 'SUCCESS'){
                    saveAs(getBlobFromBase64String(vm.fileData), vm.fileName);
                    swal("Success!", vm.fileName +"\n Downloaded Successfully", "success");
                    }else{
                    swal("Warning!",vm.message,"warning");
                    }
                    vm.downloadType='';
                    }, function (error) {
                    vm.loader = false;
                  if (error.data.message == "No Data Found" || error.data.message == "NDF") {
                  vm.noDataFound = error.data.message;
                  vm.downloadType='';
                  swal("Warning!", vm.noDataFound, "warning");
                  $state.go('bond_actual_interest_payment.add_detail');
                  } else {
                    handleErrorResponse(error);
                    vm.downloadType='';
                    $state.go('bond_actual_interest_payment.add_detail');
                  }
                    });
                }
// For Inquiry Pagination Starts
        vm.setPageForInq = function(page) {
            console.log("Page Number in setPage: " + page);
            if (page < 1) {
                // If the page number is less than 1, set it to the first page
                vm.currentPageForInq = 1;
            } else if (page > vm.totalPagesForInq) {
                // If the page number is greater than the total pages, set it to the last page
                vm.currentPageForInq = vm.totalPagesForInq;
            } else {
                // If the page number is within the valid range, set it normally
                vm.currentPageForInq = page;
            }
            console.log("vm.currentPage: "+vm.currentPageForInq)
            vm.updatePaginationForInq();
        };

        vm.updatePaginationForInq = function() {
            try {
                var startPageForInq, endPageForInq;
                console.log("Inside updatePagination function...");

                if (vm.totalPagesForInq <= vm.maxPagesToShowForInq) {
                    startPageForInq = 1;
                    endPageForInq = vm.totalPagesForInq;
                } else {
                    var maxPagesBeforeCurrentPageForInq = Math.floor(vm.maxPagesToShowForInq / 2);
                    var maxPagesAfterCurrentPageForInq = Math.ceil(vm.maxPagesToShowForInq / 2) - 1;

                    if (vm.currentPageForInq <= maxPagesBeforeCurrentPageForInq) {
                        startPageForInq = 1;
                        endPageForInq = vm.maxPagesToShowForInq;
                    } else if (vm.currentPageForInq + maxPagesAfterCurrentPageForInq >= vm.totalPagesForInq) {
                        startPageForInq = vm.totalPagesForInq - vm.maxPagesToShowForInq + 1;
                        endPageForInq = vm.totalPagesForInq;
                    } else {
                        startPageForInq = vm.currentPageForInq - maxPagesBeforeCurrentPageForInq;
                        endPageForInq = vm.currentPageForInq + maxPagesAfterCurrentPageForInq;
                    }
                }

                console.log("vm.totalPagesForInq: "+vm.totalPagesForInq);
                console.log("startPage: "+startPageForInq);
                console.log("endPage: "+endPageForInq);

                vm.pagesForInq = [];
                for (var i = startPageForInq; i <= endPageForInq; i++) {
                    vm.pagesForInq.push(i);
                }
                console.log("vm.pagesForInq: "+vm.pagesForInq);

            } catch (error) {
                console.error("An error occurred in updatePagination:", error);
            }
        };

        vm.prevPageForInq = function() {
            vm.setPageForInq(vm.currentPageForInq - 1);
        };

        vm.nextPageForInq = function() {
            vm.setPageForInq(vm.currentPageForInq + 1);
        };

        vm.firstPageForInq = function() {
            vm.setPageForInq(1);
        };

        vm.lastPageForInq = function() {
            vm.setPageForInq(vm.totalPagesForInq);
        };

        vm.updateInquiryDetailsForPagination = function() {
            vm.inquiryDetailsForPagination = vm.inquiryDetails.filter(function(record) {
                return (
                    record.branchRefNo.toLowerCase().includes(vm.searchTextForInq.toLowerCase()) ||
                    record.blaNo.toLowerCase().includes(vm.searchTextForInq.toLowerCase()) ||
                    record.appName1.toLowerCase().includes(vm.searchTextForInq.toLowerCase()) ||
                    String(record.settlementDate).includes(vm.searchTextForInq) ||
                    String(record.total).includes(vm.searchTextForInq)
                );
            });
            vm.totalPagesForInq = Math.ceil(vm.inquiryDetailsForPagination.length / vm.pageSizeForInq);
            vm.setPageForInq(1);
        };

        vm.updatePageSizeForInq = function() {
            vm.totalPagesForInq = Math.ceil(vm.inquiryDetailsForPagination.length / vm.pageSizeForInq);
            console.log("totalPages: "+vm.totalPagesForInq);
            vm.setPageForInq(1);
        };
// For Inquiry Pagination Ends


        function verifySelect(flag) {
                    vm.loader = true;
                    var requestData = {};
                    if(flag == 'S'){
                    requestData.schemeCode = null;
                    requestData.detailsOrSubmit='details'
                    }else if(flag=='I'){
                    requestData.schemeCode = vm.schemeCode;
                    requestData.detailsOrSubmit='details'
                    }else{
                    requestData.schemeCode = vm.schemeCode;
                    requestData.halfYear = vm.halfYear;
                    requestData.year = vm.year;
                    }
                    bondActualInterestPaymentService.verifySelect(requestData).then(function (response) {
                        vm.loader = false;
                        if(flag=='S'){
                            vm.schemeList = response.data.result;
                            angular.element('#schemeModel').modal('toggle');
                        } else if(flag=='I'){
                            vm.schemeList = response.data.result;
                            angular.element('#halfYearModel').modal('toggle');
                        }else{
                         vm.verifyDetails = response.data.result;
                         vm.checkBoxPayload = response.data.result;
                         vm.verifyDetailsForPagination = response.data.result;
                         if(Array.isArray(vm.verifyDetailsForPagination)){
                           vm.checkAll = true
                           vm.verifyDetailsForPagination.map((element)=>{
                                element.selected = true;
                                return element;
                           });
                         }else
                            console.warn("Invalid response data");

                         vm.totalPagesForVer = Math.ceil(vm.verifyDetailsForPagination.length / vm.pageSizeForVer);
                         console.log("totalPages for ver : "+vm.totalPagesForVer);
                         vm.updatePaginationForVer();
                         vm.loader = false;

                        }
                    }, function (error) {
                        vm.loader = false;
                        handleErrorResponse(error);
                        $state.go('bond_actual_interest_payment.inquiry');
                    });
        }
//  For Verify Pagination Starts

vm.setPageForVer = function(page) {
            console.log("Page Number in setPage Ver: " + page);
            if (page < 1) {
                // If the page number is less than 1, set it to the first page
                vm.currentPageForVer = 1;
            } else if (page > vm.totalPagesForInq) {
                // If the page number is greater than the total pages, set it to the last page
                vm.currentPageForVer = vm.totalPagesForVer;
            } else {
                // If the page number is within the valid range, set it normally
                vm.currentPageForVer = page;
            }
            console.log("vm.currentPageForVer: "+vm.currentPageForVer)
            vm.updatePaginationForVer();
        };

        vm.updatePaginationForVer = function() {
            try {
                var startPageForVer, endPageForVer;
                console.log("Inside updatePagination function Ver...");

                if (vm.totalPagesForVer <= vm.maxPagesToShowForVer) {
                    startPageForVer = 1;
                    endPageForVer = vm.totalPagesForVer;
                } else {
                    var maxPagesBeforeCurrentPageForVer = Math.floor(vm.maxPagesToShowForVer / 2);
                    var maxPagesAfterCurrentPageForVer = Math.ceil(vm.maxPagesToShowForVer / 2) - 1;

                    if (vm.currentPageForVer <= maxPagesBeforeCurrentPageForVer) {
                        startPageForVer = 1;
                        endPageForVer = vm.maxPagesToShowForVer;
                    } else if (vm.currentPageForVer + maxPagesAfterCurrentPageForVer >= vm.totalPagesForVer) {
                        startPageForVer = vm.totalPagesForVer - vm.maxPagesToShowForVer + 1;
                        endPageForVer = vm.totalPagesForVer;
                    } else {
                        startPageForVer = vm.currentPageForVer - maxPagesBeforeCurrentPageForVer;
                        endPageForVer = vm.currentPageForVer + maxPagesAfterCurrentPageForVer;
                    }
                }

                console.log("vm.totalPagesForInq: "+vm.totalPagesForVer);
                console.log("startPage: "+startPageForVer);
                console.log("endPage: "+endPageForVer);

                vm.pagesForVer = [];
                for (var i = startPageForVer; i <= endPageForVer; i++) {
                    vm.pagesForVer.push(i);
                }
                console.log("vm.pagesForVer: "+vm.pagesForVer);

            } catch (error) {
                console.error("An error occurred in updatePagination:", error);
            }
        };

        vm.prevPageForVer = function() {
           vm.setPageForVer(vm.currentPageForVer - 1);
        };

        vm.nextPageForVer = function() {
            vm.setPageForVer(vm.currentPageForVer + 1);
        };

        vm.firstPageForVer = function() {
            vm.setPageForVer(1);
        };

        vm.lastPageForVer = function() {
            vm.setPageForVer(vm.totalPagesForVer);
        };

        vm.updateVerifyDetailsForPagination = function() {
            vm.verifyDetailsForPagination = vm.verifyDetails.filter(function(record) {
                return (
                    record.branchRefNo.toLowerCase().includes(vm.searchTextForVer.toLowerCase()) ||
                    record.blaNo.toLowerCase().includes(vm.searchTextForVer.toLowerCase()) ||
                    record.appName1.toLowerCase().includes(vm.searchTextForVer.toLowerCase()) ||
                    String(record.settlementDate).includes(vm.searchTextForVer) ||
                    String(record.total).includes(vm.searchTextForVer)
                );
            });
            vm.totalPagesForVer = Math.ceil(vm.verifyDetailsForPagination.length / vm.pageSizeForVer);
            vm.setPageForVer(1);
        };
        vm.updatePageSizeForVer = function() {
                    vm.totalPagesForVer = Math.ceil(vm.verifyDetailsForPagination.length / vm.pageSizeForVer);
                    console.log("totalPages Ver: "+vm.totalPagesForVer);
                    vm.setPageForVer(1);
        };
//  For Verify Pagination Ends




         function addDetails() {
                    vm.loader = true;
                    var requestData = {
                        'schemeCode': vm.schemeCode,
                        'halfYear':vm.halfYear,
                        'year':vm.year,
                        'detailsOrSubmit':'details'
                    };
                    bondActualInterestPaymentService.addDetails(requestData).then(function (response) {
                        vm.loader = false;
                        vm.addDetail = response.data.result;
                        vm.addDetailsForPagination = response.data.result;
                        vm.totalPagesForAdd = Math.ceil(vm.addDetailsForPagination.length / vm.pageSizeForAdd);
                         vm.updatePaginationForAdd();
                         vm.loader = false;

                    }, function (error) {
                        vm.loader = false;
                        handleErrorResponse(error);
                        $state.go('bond_actual_interest_payment.add');
                    });
                }
// For Add Pagination Starts

vm.setPageForAdd = function(page) {
            console.log("Page Number in setPage: " + page);
            if (page < 1) {
                // If the page number is less than 1, set it to the first page
                vm.currentPageForAdd = 1;
            } else if (page > vm.totalPagesForAdd) {
                // If the page number is greater than the total pages, set it to the last page
                vm.currentPageForAdd = vm.totalPagesForAdd;
            } else {
                // If the page number is within the valid range, set it normally
                vm.currentPageForAdd = page;
            }
            console.log("vm.currentPageAdd: "+vm.currentPageForAdd)
            vm.updatePaginationForAdd();
        };

vm.updatePaginationForAdd = function() {
            try {
                var startPageForAdd, endPageForAdd;
                console.log("Inside updatePagination function Add...");

                if (vm.totalPagesForAdd <= vm.maxPagesToShowForAdd) {
                    startPageForAdd = 1;
                    endPageForAdd = vm.totalPagesForAdd;
                } else {
                    var maxPagesBeforeCurrentPageForAdd = Math.floor(vm.maxPagesToShowForAdd / 2);
                    var maxPagesAfterCurrentPageForAdd = Math.ceil(vm.maxPagesToShowForAdd / 2) - 1;

                    if (vm.currentPageForAdd <= maxPagesBeforeCurrentPageForAdd) {
                        startPageForAdd = 1;
                        endPageForAdd = vm.maxPagesToShowForAdd;
                    } else if (vm.currentPageForAdd + maxPagesAfterCurrentPageForAdd >= vm.totalPagesForAdd) {
                        startPageForAdd = vm.totalPagesForAdd - vm.maxPagesToShowForAdd + 1;
                        endPageForAdd = vm.totalPagesForAdd;
                    } else {
                        startPageForAdd = vm.currentPageForAdd - maxPagesBeforeCurrentPageForAdd;
                        endPageForAdd = vm.currentPageForAdd + maxPagesAfterCurrentPageForAdd;
                    }
                }

                console.log("vm.totalPagesForAdd: "+vm.totalPagesForAdd);
                console.log("startPage: "+startPageForAdd);
                console.log("endPage: "+endPageForAdd);

                vm.pagesForAdd = [];
                for (var i = startPageForAdd; i <= endPageForAdd; i++) {
                    vm.pagesForAdd.push(i);
                }
                console.log("vm.pagesForAdd: "+vm.pagesForAdd);

            } catch (error) {
            console.error("Error in updatePaginationForAdd:", error);
            // Reset pagination to a safe state
            vm.pagesForAdd = [];
            vm.currentPageForAdd = 1;
            vm.totalPagesForAdd = 0;
            // Optional: Display error to user
            Swal.fire("Error", error.message || "Unable to update pagination.", "error" );
            }
};

        vm.prevPageForAdd = function() {
        vm.setPageForAdd(vm.currentPageForAdd - 1);
        };

        vm.nextPageForAdd = function() {
        vm.setPageForAdd(vm.currentPageForAdd + 1);
        };

        vm.firstPageForAdd = function() {
        vm.setPageForAdd(1);
        };

        vm.lastPageForAdd = function() {
        vm.setPageForAdd(vm.totalPagesForAdd);
        };

     vm.updateAddDetailsForPagination = function() {
             vm.addDetailsForPagination = vm.addDetail.filter(function(record) {
                 return (
                     record.branchRefNo.toLowerCase().includes(vm.searchTextForAdd.toLowerCase()) ||
                     record.blaNo.toLowerCase().includes(vm.searchTextForAdd.toLowerCase()) ||
                     record.appName1.toLowerCase().includes(vm.searchTextForAdd.toLowerCase()) ||
                     String(record.settlementDate).includes(vm.searchTextForAdd) ||
                     String(record.total).includes(vm.searchTextForAdd)
                 );
             });
             vm.totalPagesForAdd = Math.ceil(vm.addDetailsForPagination.length / vm.pageSizeForAdd);
             vm.setPageForAdd(1);
     };
     vm.updatePageSizeForAdd = function() {
         vm.totalPagesForAdd = Math.ceil(vm.addDetailsForPagination.length / vm.pageSizeForAdd);
         console.log("totalPages: "+vm.totalPagesForAdd);
         vm.setPageForAdd(1);
     };

// For Add Pagination Ends





                function downloadExcelFile(){
                    vm.loader = true;
                    bondActualInterestPaymentService.downloadExcel().then(function (response) {
                    vm.loader = false;
                    vm.fileData = response.data.result.fileData;
                    vm.fileName = response.data.result.fileName;
                    saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
                    }, function (error) {
                        vm.loader = false;
                        handleErrorResponse(error);
                        $state.go('bond_actual_interest_payment.inquiry');
                    });

                }


                function addSubmit(){
//                     Swal.fire({
//                        title: "Are you sure?",
//                        text: "Do you want to Add this record?",
//                        type: "warning",
//                        showCancelButton: true,
//                        cancelButtonClass: "btn-cancel",
//                        confirmButtonClass: "btn-primary",
//                        confirmButtonText: "Yes",
//                        closeOnConfirm: false
//                        }, function(){


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
                            'schemeCode': vm.schemeCode,
                            'halfYear':vm.halfYear,
                            'year':vm.year,
                            'detailsOrSubmit':'submit'
                           };
                      bondActualInterestPaymentService.addSubmit(requestData).then( (response) => {
                      vm.loader=false;
                      vm.addResultResponse = response.data.message;
                      Swal.fire("Success!", "Actual Bond Interest Payment Added Successfully", "success");
                       //location.href = '#!/bond_actual_interest_payment/action/inquiry';
                       $state.go('bond_actual_interest_payment.inquiry');
                       },function(error) {
                         vm.loader = false;
                          if(error.status =='400')
                          {
                              Swal.fire("Warning", error.message, "warning");
                          }else{
                              handleErrorResponse(error);
                          }
                         $state.go('bond_actual_interest_payment.add');
                       });
}
                     });
                }

           function verifySubmit(){
//             Swal.fire({
//                title: "Are you sure?",
//                text: "Do you want to Verify this record?",
//                type: "warning",
//                showCancelButton: true,
//                cancelButtonClass: "btn-cancel",
//                confirmButtonClass: "btn-primary",
//                confirmButtonText: "Yes",
//                closeOnConfirm: false
//                }, function(){



                   Swal.fire({
                     title: "Are you sure?",
                     text: "Do you want to Verify Verify this record?",
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
                           'schemeCode': vm.schemeCode,
                           'halfYear':vm.halfYear,
                           'year':vm.year,
                           'recordsToBeVerified':vm.otherAccounts
                    };
                   bondActualInterestPaymentService.verifySubmit(requestData).then(function (response) {
                       if (response.data.message == 'SUCCESS') {
                           Swal.fire("Verified!", "Verified Successfully", "success");
                           $state.go('bond_actual_interest_payment.inquiry');
                       } else {
                           Swal.fire("WARNING!", response.data.message, "error");
                            $state.go('bond_actual_interest_payment.verify');
                       }
                       const backdrop = document.querySelector('.modal-backdrop');
                       if (backdrop) {
                         backdrop.parentNode.removeChild(backdrop);
                         document.body.classList.remove('modal-open');
                       }
                   }, function (error) {
                       vm.loader = false;
                       if(error.status =='400')
                       {
                           Swal.fire("Warning", error.message, "warning");
                       }else{
                           handleErrorResponse(error);
                       }
                       const backdrop = document.querySelector('.modal-backdrop');
                       if (backdrop) {
                        backdrop.parentNode.removeChild(backdrop);
                        document.body.classList.remove('modal-open');
                        }
                        location.href = '#!/bond_actual_interest_payment/action/inquiry';
                  });

}
                }
             );
           }

            function rejectReason() {
             angular.element('#permissionMappingDelete').modal('toggle');
            }



            function rejectDetails(){
//              Swal.fire({
//                title: "Are you sure?",
//                text: "Do you want to Really Reject this record?",
//                type: "warning",
//                showCancelButton: true,
//                cancelButtonClass: "btn-cancel",
//                confirmButtonClass: "btn-primary",
//                confirmButtonText: "Yes",
//                closeOnConfirm: false
//                }, function () {



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
                       'schemeCode': vm.schemeCode,
                       'halfYear':vm.halfYear,
                       'year':vm.year,
                       'deleteRemark': vm.rejectionRemarks,
                       'recordsToBeRejected': vm.selectedAccounts.length > 0 ? vm.selectedAccounts : null
                };
                bondActualInterestPaymentService.reject(requestData).then(function (response) {
                    if (response.data.message == 'SUCCESS') {
                        Swal.fire("Rejected!", "Rejected Successfully", "success");
                        $state.go('bond_actual_interest_payment.inquiry');
                    } else {
                        Swal.fire("WARNING!", response.data.message, "error");
                         $state.go('bond_actual_interest_payment.verify');
                    }
                    const backdrop = document.querySelector('.modal-backdrop');
                    if (backdrop) {
                      backdrop.parentNode.removeChild(backdrop);
                      document.body.classList.remove('modal-open');
                    }
                }, function (error) {
                    vm.loader = false;
                    if(error.status =='400')
                    {
                        Swal.fire("Warning", error.message, "warning");
                    }else{
                        handleErrorResponse(error);
                    }
                    const backdrop = document.querySelector('.modal-backdrop');
                    if (backdrop) {
                     backdrop.parentNode.removeChild(backdrop);
                     document.body.classList.remove('modal-open');
                     }
                     location.href = '#!/bond_actual_interest_payment/action/inquiry';
               });

}
              });
            }

            vm.toggleAllSelection = function() {
                if (vm.checkAll) {
                    // Select all records
                    vm.verifyDetailsForPagination.forEach(function(record) {
                        record.selected = true;
                    });
                } else {
                    // Deselect all records
                    vm.verifyDetailsForPagination.forEach(function(record) {
                        record.selected = false;
                    });
                }
                // Call updateSelection to handle the state after toggling
                vm.updateSelection();
            };



            vm.updateSelection = function() {
                    vm.otherAccounts = [];
                     vm.selectedAccounts = [];
                 // Separate the selected and unselected accounts
                 if (Array.isArray(vm.verifyDetailsForPagination)) {
                    vm.verifyDetailsForPagination.forEach(function(x) {
                    if (x.selected) {
                     vm.selectedAccounts.push(x.branchRefNo);  // For Rejection Selected Records need to be sent
                    } else {
                     vm.otherAccounts.push(x.branchRefNo);     // For Verification UnSelected Records need to be Sent
                    }
                    });
                    console.log(vm.otherAccounts);
                    console.log(vm.selectedAccounts);
                 } else {
                     console.warn("verifyDetailsForPagination is not defined or not an array.");
                 }
            };

 }
 })();
