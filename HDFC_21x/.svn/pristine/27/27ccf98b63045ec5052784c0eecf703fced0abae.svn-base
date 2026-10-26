(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('rbiBondSubsAccClsOnMtyController',
        rbiBondSubsAccClsOnMtyController);

    rbiBondSubsAccClsOnMtyController.$inject = ['$scope', '$filter', '$state',
        '$stateParams', '$location', '$rootScope', '$timeout', 'rbiBondSubsAccClsOnMtyService', 'commonService'
    ];

    function rbiBondSubsAccClsOnMtyController($scope, $filter, $state, $stateParams,
        $location, $rootScope, $timeout, rbiBondSubsAccClsOnMtyService, commonService) {

		var vm = this;

        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
        vm.changeDateFormat = changeDateFormat;
        vm.getAddValidation = getAddValidation;
        vm.verifyDetailPage = verifyDetailPage;
        vm.addSubmit = addSubmit;
        vm.formatDate = formatDate;
        vm.downloadFile = downloadFile;
        vm.uploadFile = uploadFile;
        vm.getRemarksList = getRemarksList;
        vm.getInquiryDetails = getInquiryDetails;
        vm.verifySubscriptionTorch = verifySubscriptionTorch;
        vm.verifyBlaTorch = verifyBlaTorch;
        vm.getVerifySelectDetails = getVerifySelectDetails;
        vm.verifySubmit = verifySubmit;
        vm.deleteSubmit = deleteSubmit;
        vm.toggleAllSelectionForAdd = toggleAllSelectionForAdd;
        vm.toggleAllSelectionForVer = toggleAllSelectionForVer;
        vm.updateSelectionForAdd = updateSelectionForAdd;
        vm.updateSelectionForVer = updateSelectionForVer;
        vm.rejectReason = rejectReason;
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




        if ($state.current.name == 'rbi_bnd_acc_cls_mty.add_detail') {
            if ($stateParams.obj) {
		        vm.schemeCode = $stateParams.obj.schemeCode;
		        vm.schemeName = $stateParams.obj.schemeName;
		        vm.panNoName = $stateParams.obj.panNoName;
		        vm.name = $stateParams.obj.name;
		        vm.blaNo = $stateParams.obj.blaNo;
		        vm.fromDate = $stateParams.obj.fromDate;
		        vm.toDate = $stateParams.obj.toDate;
                getAddValidation('D');
            } else {
                $state.go('rbi_bnd_acc_cls_mty.add');
            }
        }
        else if ($state.current.name == 'rbi_bnd_acc_cls_mty.verify_detail') {
            if ($stateParams.obj) {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;
                vm.panNoName = $stateParams.obj.panNoName;
                vm.name = $stateParams.obj.name;
                vm.blaNo = $stateParams.obj.blaNo;
                vm.fromDate = $stateParams.obj.fromDate;
                vm.toDate = $stateParams.obj.toDate;
                verifyDetailPage('D');
            } else {
                $state.go('rbi_bnd_acc_cls_mty.verify');
            }
        }
//        else if ($state.current.name == 'rbi_bnd_acc_cls_mty.delete_detail') {
//            if ($stateParams.obj) {
//               vm.schemeCode = $stateParams.obj.schemeCode;
//                vm.schemeName = $stateParams.obj.schemeName;
//                vm.blaNo = $stateParams.obj.blaNo;
//                vm.subscriptionId = $stateParams.obj.subscriptionId;
//                getVerifySelectDetails();
//
//            } else {
//                $state.go('rbi_bnd_acc_cls_mty.delete');
//            }
//        }
        else if ($state.current.name == 'rbi_bnd_acc_cls_mty.inquiry_detail') {
            if ($stateParams.obj) {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;
                vm.panNoName = $stateParams.obj.panNoName;
                vm.name = $stateParams.obj.name;
                vm.blaNo = $stateParams.obj.blaNo;
                vm.fromDate = $stateParams.obj.fromDate;
                vm.toDate = $stateParams.obj.toDate;
              getInquiryDetails('D');

            } else {
                $state.go('rbi_bnd_acc_cls_mty.inquiry');
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
            } else if (flag == 'D') {
                requestData.schemeCode = vm.schemeCode;
                requestData.panNoName = vm.panNoName;
                requestData.name = vm.name;
                requestData.blaNo = vm.blaNo;
                requestData.pageType = 'scheme';
                requestData.fromDate = $filter('date')(vm.fromDate, "dd-MMM-yyyy");
                requestData.toDate = $filter('date')(vm.toDate, "dd-MMM-yyyy");
            }
           rbiBondSubsAccClsOnMtyService.getValidationAdd(requestData).then(function(response){
                if (flag == 'S'){
                    vm.schemeList = response.data.result;
                    angular.element('#schemeModel').modal('toggle');
                } else if (flag == 'P'){
                    vm.PanTorch = response.data.result;
                    angular.element('#panModel').modal('toggle');
                } else if (flag == 'B'){
                    vm.subscriptionList = response.data.result;
                    angular.element('#subscriptionModel').modal('toggle');
                } else if (flag == 'D'){
                      vm.responseResultForAdd = response.data.result;
                      vm.checkAll = true;
                      vm.responseResultForAdd.forEach(function(record) {
                          record.selected = true;
                      });
                }

                vm.loader = false;

            },function(error) {
                vm.loader = false;
                const errorMessage = error && error.data && error.data.message
                                             ? error.data.message
                                             : "An unexpected error occurred.";
                                     if(null!=errorMessage && errorMessage.startsWith("Invalid")){
                                     Swal.fire("Warning!", errorMessage , "warning");
                                     $state.go("rbi_bnd_acc_cls_mty.add");
                                     }else{
                                     handleErrorResponse(error);
                                     $state.go("rbi_bnd_acc_cls_mty.add");
                                     }

            });

        }

        function updateSelectionForAdd(){
              vm.selectedAccountsForAdd = [];
              vm.otherAccountsForAdd = [];
               if (Array.isArray(vm.responseResultForAdd)) {
                vm.responseResultForAdd.forEach(function(x) {
//                    vm.panNoName = x.panNoName;
                    vm.paymentDate = x.paymentDateAtEnd;
                    if (x.selected) {
                      vm.selectedAccountsForAdd.push(x.branchRefNo);
                      if(vm.responseResultForAdd.length === vm.selectedAccountsForAdd.length)
                      vm.checkAll = true;
                      else
                      vm.checkAll = false;
                    } else {
                      vm.otherAccountsForAdd.push(x.branchRefNo);
                      vm.checkAll = false;
                    }
                });
               } else
                  console.warn("preMaturity is not defined or not an array.");
        }

        function updateSelectionForVer(){
              vm.selectedAccountsForVer = [];
              vm.otherAccountsForVer = [];
               if (Array.isArray(vm.responseResultForVer)) {
                vm.responseResultForVer.forEach(function(x) {
                    if (x.selectedForVer) {
                      vm.selectedAccountsForVer.push(x.branchRefNo);
                      if(vm.selectedAccountsForVer.length === vm.otherAccountsForVer.length)
                      vm.checkAllForVer = true;
                      else
                      vm.checkAllForVer = false;
                    } else {
                      vm.otherAccountsForVer.push(x.branchRefNo);
                      vm.checkAllForVer = false;
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

         function toggleAllSelectionForVer() {
             if (vm.checkAllForVer) {
                 vm.responseResultForVer.forEach(function(record) {
                 record.selectedForVer = true;
                 });
             } else {
                  vm.responseResultForVer.forEach(function(record) {
                      record.selectedForVer = false;
                 });
             }
         }


        function verifyDetailPage(flag){
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
                    } else if (flag == 'D'){
                        requestData.schemeCode = vm.schemeCode;
                        requestData.panNoName = vm.panNoName;
                        requestData.name = vm.name;
                        requestData.blaNo = vm.blaNo;
                        requestData.pageType = 'scheme';
                        requestData.fromDate = $filter('date')(vm.fromDate, "dd-MMM-yyyy");
                        requestData.toDate = $filter('date')(vm.toDate, "dd-MMM-yyyy");

                    }
                   rbiBondSubsAccClsOnMtyService.verifySelect(requestData).then(function(response){
                        if (flag == 'S'){
                            vm.schemeList = response.data.result;
                            angular.element('#schemeModel').modal('toggle');
                        } else if (flag == 'P'){
                            vm.PanTorch = response.data.result;
                            angular.element('#panModel').modal('toggle');
                        } else if (flag == 'B'){
                            vm.subscriptionList = response.data.result;
                            angular.element('#subscriptionModel').modal('toggle');
                        } else if (flag == 'D'){
                             vm.responseResultForVer =  response.data.result;
                             vm.checkAllForVer = true;
                             vm.responseResultForVer.forEach(function(record) {
                                 record.selectedForVer = true;
                             });
                        }

                        vm.loader = false;

                    },function(error) {
                        vm.loader = false;
                         const errorMessage = error && error.data && error.data.message
                                                   ? error.data.message
                                                   : "An unexpected error occurred.";
                                           if(null!=errorMessage && errorMessage.startsWith("Invalid")){
                                           Swal.fire("Warning!", errorMessage , "warning");
                                           $state.go("rbi_bnd_acc_cls_mty.verify");
                                           }else{
                                           handleErrorResponse(error);
                                           $state.go("rbi_bnd_acc_cls_mty.verify");
                                           }
                    });

        }



        function getRemarksList() {
              vm.referenceId = '056';
              var requestData = { 'referenceId': vm.referenceId };
              rbiBondSubsAccClsOnMtyService.getRemarksList(requestData).then(function(response) {
                  vm.remarksList = response.data.result || []; // Ensure it's an array
              }, function(error) {
                  handleErrorResponse(error);
              });
        }

        function addSubmit(closureRemarks) {
          updateSelectionForAdd();
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
                 requestData.schemeCode = vm.schemeCode;
                 requestData.panNoName = vm.panNoName;
                 requestData.name = vm.name;
                 requestData.blaNo = vm.blaNo;
                 requestData.listOfStrings = vm.selectedAccountsForAdd;
                 requestData.paymentDate =  formatDate(vm.paymentDate);
                 requestData.closureRemarks = closureRemarks;
                 requestData.pageType = 'ADD';
                 requestData.fromDate = $filter('date')(vm.fromDate, "dd-MMM-yyyy");
                 requestData.toDate = $filter('date')(vm.toDate, "dd-MMM-yyyy");

              rbiBondSubsAccClsOnMtyService.getValidationAdd(requestData).then( (response) => {
                    vm.loader=false;
                    vm.addResultResponse = response.data.message;
                    if( vm.addResultResponse == 'SUCCESS'){
                        Swal.fire("Success!", "Redemption Payment Added Successfully’ ", "success");
                       location.href = '#!/rbi_bnd_acc_cls_mty/action/inquiry';
                    }
                 },function(error) {
                   vm.loader = false;
                      const errorMessage = error && error.data && error.data.message
                           ? error.data.message
                           : "An unexpected error occurred.";
                   if(null!=errorMessage && errorMessage.startsWith("Invalid")){
                   Swal.fire("Warning!", errorMessage , "warning");
                   $state.go("rbi_bnd_acc_cls_mty.add");
                   }else{
                   handleErrorResponse(error);
                   $state.go("rbi_bnd_acc_cls_mty.add");
                   }
                    });

                    }
            });

    }
    function downloadFile(){
             vm.loader = true;
               var requestData = {};
               requestData.schemeCode = vm.schemeCode;
               requestData.fromDate = $filter('date')(vm.fromDate, "dd-MMM-yyyy");
               requestData.toDate = $filter('date')(vm.fromDate, "dd-MMM-yyyy");
            rbiBondSubsAccClsOnMtyService.downloadFile(requestData).then(function (response) {
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
                    $state.go('rbi_bnd_acc_cls_mty.inquiry');
                    vm.loader = false;
                 }
            );
    }
     function rejectReason() {
                 angular.element('#permissionMappingDelete').modal('toggle');
     }

    function uploadFile(textFile){
          vm.loader = true;
          var fd = new FormData();
          fd.append('textFile', textFile);
          fd.append('schemeCode',vm.schemeCode);
          fd.append('fromDate', $filter('date')(vm.fromDate, "dd-MMM-yyyy"));
          fd.append('toDate',$filter('date')(vm.fromDate, "dd-MMM-yyyy"));
        rbiBondSubsAccClsOnMtyService.uploadTextFile(fd)
            .then(function (response) {
                   vm.loader = false;
                   vm.responseMessage = response.data.message;
                   if(vm.responseMessage == 'SUCCESS'){
                        Swal.fire("Uploaded!","File Uploaded Successfully", "success");
                        vm.textFileModel = null;
                   }
                    }, function (error){
                        Swal.fire("Error!", error.data.message, "error");
                        $state.go('rbi_bnd_acc_cls_mty.inquiry');
        });
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
            } else if (flag == 'D'){
                requestData.schemeCode = vm.schemeCode;
                requestData.panNoName = vm.panNoName;
                requestData.name = vm.name;
                requestData.blaNo = vm.blaNo;
                requestData.pageType = 'scheme';
                requestData.fromDate = $filter('date')(vm.fromDate, "dd-MMM-yyyy");
                requestData.toDate = $filter('date')(vm.toDate, "dd-MMM-yyyy");
            }
           rbiBondSubsAccClsOnMtyService.inquiry(requestData).then(function(response){
               if (flag == 'S'){
                   vm.schemeList = response.data.result;
                   angular.element('#schemeModel').modal('toggle');
               } else if (flag == 'P'){
                   vm.PanTorch = response.data.result;
                   angular.element('#panModel').modal('toggle');
               } else if (flag == 'B'){
                   vm.subscriptionList = response.data.result;
                   angular.element('#subscriptionModel').modal('toggle');
               } else if (flag == 'D'){
                vm.responseResultForInquiry = response.data.result;
               }
                vm.loader = false;
           },function(error) {
               vm.loader = false;
               const errorMessage = error && error.data && error.data.message
                                         ? error.data.message
                                         : "An unexpected error occurred.";
                                 if(null!=errorMessage && errorMessage.startsWith("Invalid")){
                                 Swal.fire("Warning!", errorMessage , "warning");
                                 $state.go("rbi_bnd_acc_cls_mty.inquiry");
                                 }else{
                                 handleErrorResponse(error);
                                 $state.go("rbi_bnd_acc_cls_mty.inquiry");
                                 }
            });
        }

        function verifyBlaTorch(){
            vm.loader = true;
                var requestData = {
                "schemeCode": vm.schemeCode,
                };
               rbiBondSubsAccClsOnMtyService.verifySelect(requestData).then(function(response){
                    vm.loader = false;
                    vm.blaModalList = response.data.result;
                    angular.element('#blaModal').modal('toggle');

                },function(error) {
                vm.loader = false;
                   handleErrorResponse(error);
                 });

        }

        function verifySubscriptionTorch(){
            vm.loader = true;
                var requestData = {
                "schemeCode": vm.schemeCode,
                "blaNo": vm.blaNo,
                };
               rbiBondSubsAccClsOnMtyService.verifySelect(requestData).then(function(response){
                    vm.loader = false;
                    vm.subscriptionModalList = response.data.result;
                    angular.element('#subscriptionModal').modal('toggle');

                },function(error) {
                vm.loader = false;
                   handleErrorResponse(error);
                 });
        }

        function getVerifySelectDetails() {
            vm.loader = true;
                var requestData = {
                    "schemeCode": vm.schemeCode,
                    "blaNo": vm.blaNo,
                    "subscriptionId": vm.subscriptionId,
                    };
               rbiBondSubsAccClsOnMtyService.verifySelect(requestData).then(function(response){
                    vm.getDetails  = response.data.result;
                    vm.model  = vm.getDetails.dataValues;
                    vm.loader = false;

                },function(error) {
                vm.loader = false;
                   handleErrorResponse(error);
                   $state.go('rbi_bnd_acc_cls_mty.inquiry');
                 });
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

        function deleteSubmit(rejectionRemarks) {
            updateSelectionForVer();
//             Swal.fire({
//                    title : "Are you sure?",
//                    text : "Do you want to Really Delete this record?",
//                    type : "warning",
//                    showCancelButton : true,
//                    cancelButtonClass : "btn-cancel",
//                    confirmButtonClass : "btn-primary",
//                    confirmButtonText : "Yes",
//                    closeOnConfirm : false
//                },function() {

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
                    var requestData = {};
                      requestData.schemeCode = vm.schemeCode;
                      requestData.name = vm.name;
                      requestData.panNoName = vm.panNoName;
                      requestData.blaNo = vm.blaNo;
                      requestData.fromDate = $filter('date')(vm.fromDate, "dd-MMM-yyyy");
                      requestData.toDate = $filter('date')(vm.toDate, "dd-MMM-yyyy");
                      requestData.listOfStrings = vm.selectedAccountsForVer;
                      requestData.closureRemarks = rejectionRemarks;
                      requestData.pageType = 'delete';
                   rbiBondSubsAccClsOnMtyService.verifySelect(requestData).then(function(response){
                        vm.loader = false;
                        Swal.fire("Success", "Rejected Successfully", "success");
                        $state.go('rbi_bnd_acc_cls_mty.inquiry');
                   },function(error) {
                      vm.loader = false;
                      const errorMessage = error && error.data && error.data.message
                                                ? error.data.message
                                                : "An unexpected error occurred.";
                                        if(null!=errorMessage && errorMessage.startsWith("Invalid")){
                                        Swal.fire("Warning!", errorMessage , "warning");
                                        $state.go("rbi_bnd_acc_cls_mty.inquiry");
                                        }else{
                                        handleErrorResponse(error);
                                        $state.go("rbi_bnd_acc_cls_mty.inquiry");
                                        }
                   });
                   }
             });

        }

        function verifySubmit(closureRemarks) {
               updateSelectionForVer();
//             Swal.fire({
//                    title : "Are you sure?",
//                    text : "Do you want to Really Verify this record?",
//                    type : "warning",
//                    showCancelButton : true,
//                    cancelButtonClass : "btn-cancel",
//                    confirmButtonClass : "btn-primary",
//                    confirmButtonText : "Yes",
//                    closeOnConfirm : false
//                },
//             function() {


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
                  requestData.name = vm.name;
                  requestData.panNoName = vm.panNoName;
                  requestData.blaNo = vm.blaNo;
                  requestData.fromDate = $filter('date')(vm.fromDate, "dd-MMM-yyyy");
                  requestData.toDate = $filter('date')(vm.toDate, "dd-MMM-yyyy");
                  requestData.listOfStrings = vm.selectedAccountsForVer;
//                  requestData.paymentDate =  formatDate(vm.paymentDateForVer);
                  requestData.closureRemarks = closureRemarks;
                  requestData.pageType = 'verify';
                rbiBondSubsAccClsOnMtyService.verifySelect(requestData).then(function(response){
                     vm.loader = false;
                      var message = "Redemption Calculation Verified Successfully";
                     Swal.fire("Success", message , "success");
                     $state.go('rbi_bnd_acc_cls_mty.inquiry');
                 },function(error) {
                   vm.loader = false;
                     const errorMessage = error && error.data && error.data.message
                                               ? error.data.message
                                               : "An unexpected error occurred.";
                                       if(null!=errorMessage && errorMessage.startsWith("Invalid")){
                                       Swal.fire("Warning!", errorMessage , "warning");
                                       $state.go("rbi_bnd_acc_cls_mty.verify");
                                       }else{
                                       handleErrorResponse(error);
                                       $state.go("rbi_bnd_acc_cls_mty.verify");
                                       }
                    $state.go('rbi_bnd_acc_cls_mty.verify');
                });
                }
             });
        }

        function changeDateFormat(value){
            var dateObj = convertToDateObject(value);
            return $filter('date')(dateObj, 'dd-MMM-yyyy');
        }


                                    vm.textableDatePicker=textableDatePicker;
                                    vm.autoslashing=autoslashing;
                                    vm.futureNotAllowedValidation=futureNotAllowedValidation;


                            function textableDatePicker(id,valueDate)
                            {
                             commonService.textableDatePicker(id,valueDate);
                            }


                            function autoslashing(id)

                            {
                             commonService.autoslashing(id);

                            }
vm.fromDateVal=fromDateVal;
 function fromDateVal() {
      vm.fromDateOptions = {
        maxDate: vm.toDate ? new Date(vm.toDate) : new Date(),
        showWeeks: false,
      };
    }
vm.toDateVal=toDateVal;
    function toDateVal(fromDate) {
      vm.toDateOptions = {
        minDate: new Date(fromDate),
        maxDate: new Date(),
        showWeeks: false,
      };
    }

                            function futureNotAllowedValidation(scope,id,valueDate)
                            {
                             commonService.futureNotAllowedValidation(scope,id,valueDate);
                            }






    }

})();