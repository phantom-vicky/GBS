(function () {

  'use strict';

  angular.module('gbmUltraApp').controller('StateDistrictPinController',
    StateDistrictPinController);

  StateDistrictPinController.$inject = ['$scope', '$state', '$stateParams', '$timeout', '$rootScope', 'StateDistrictPinService', 'commonService'];

  function StateDistrictPinController($scope, $state, $stateParams, $timeout,
    $rootScope, StateDistrictPinService, commonService) {

    var vm = this;
    vm.getRemarksList = getRemarksList;

    vm.uploadFile = uploadFile;
    vm.getInquiryDetails = getInquiryDetails;
    vm.getVerifyDetails = getVerifyDetails;
    vm.verifySubmit = verifySubmit;
    vm.deleteSubmit = deleteSubmit;
    vm.downloadPincode = downloadPincode;
    vm.uploadFileExistCheck = uploadFileExistCheck;
    vm.itemsPerPage = '5';
    vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;

    vm.goPreviousPage = goPreviousPage;
                vm.goNextPage = goNextPage;
                vm.paginationControls = paginationControls;
                vm.showNext = false;
                vm.showPrevious = false;
                $scope.sortReverse = false;
                $scope.sort = function(key){
                $scope.sortKey = key;
                $scope.sortReverse = ($scope.sortKey == key) ? !$scope.sortReverse : $scope.sortReverse;
                $scope.sortReverse ? vm.sortOrder = 'DESC' : vm.sortOrder = 'ASC';
                vm.sortField = key;
                vm.getInquiryDetails();
                }

     vm.pageSize = 5;
     vm.pageNo = 1;

    if ($state.current.name == 'state_district_pin.inquiry') {
      getInquiryDetails();
    }
    else if ($state.current.name == 'state_district_pin.verify') {
      getVerifyDetails();
    }

    function getVerifyDetails() {

      vm.loader = true;
         var requestData = {
      //              schemeCode: vm.schemeCode,
      //              fromDate: moment(vm.fromDate).format("DD-MMM-YYYY"),
      //              toDate: moment(vm.toDate).format("DD-MMM-YYYY"),
                    flag: 'D',
                    pageNo : vm.pageNo,
                    pageSize:vm.pageSize,
                    searchValue:vm.searchValue,
                    sortOrder:vm.sortOrder,
                    sortField:vm.sortField
                }
      StateDistrictPinService.getVerifyDetails(requestData).then(function (response) {
        vm.loader = false;
        vm.auditDetails = response.data.result;
        vm.stateMasterDetails = response.data.result.stateReportDTOList;
         vm.total = response.data.total;
                            vm.maxPages =  Math.ceil(response.data.total/vm.pageSize);
                            if (vm.stateMasterDetails.length === 0) {
                                vm.noDataFoundInSearch = true;
                            } else {
                                vm.noDataFoundInSearch = false;
                            }
                            vm.paginationControls();

      }, function (error) {
        vm.loader = false;
           if (vm.searchValue && (error.data.message == "No Data Found" || error.data.message == "NDF")) {
                                           vm.noDataFoundInSearch = true;
                                           vm.total= error.data.total;
                                           vm.pageNo= 0;
                                           vm.maxPages = Math.ceil(error.data.total / vm.pageSize);
                                           vm.paginationControls();
                                           vm.showPrevious= false;
                                      } else if (error.data.message == "No Data Found" || error.data.message == "NDF") {
                                              vm.noDataFound = error.data.message;
                                      } else {
                                        handleErrorResponse(error);
                                        $state.go("state_district_pin.verify");
                                        }
      });
    }

    function getInquiryDetails() {
      vm.loader = true;
       if(null!=vm.searchValue){
      var requestData = {
              pageNo : vm.pageNo,
              pageSize:vm.pageSize,
              searchValue:vm.searchValue,
              sortOrder:vm.sortOrder,
              sortField:vm.sortField
          }
          }else{
             var requestData = {
                        pageNo : vm.pageNo,
                        pageSize:vm.pageSize,
                        searchValue:vm.searchValue,
                        sortOrder:vm.sortOrder,
                        sortField:vm.sortField
                    }
          }
      StateDistrictPinService.getInquiryDetails(requestData).then(function (response) {
        vm.loader = false;
        vm.auditDetails = response.data.result;
        vm.stateMasterDetails = response.data.result.stateReportDTOList;
        vm.total = response.data.total;
                    vm.maxPages =  Math.ceil(response.data.total/vm.pageSize);
                    if (vm.stateMasterDetails.length === 0) {
                        vm.noDataFoundInSearch = true;
                    } else {
                        vm.noDataFoundInSearch = false;
                    }
                    vm.paginationControls();
      }, function (error) {
        vm.loader = false;
        if (vm.searchValue && (error.data.message == "No Data Found" || error.data.message == "NDF")) {
                                   vm.noDataFoundInSearch = true;
                                   vm.total= error.data.total;
                                   vm.pageNo= 0;
                                   vm.maxPages = Math.ceil(error.data.total / vm.pageSize);
                                   vm.paginationControls();
                                   vm.showPrevious= false;
                              } else if (error.data.message == "No Data Found" || error.data.message == "NDF") {
                                      vm.noDataFound = error.data.message;
                              } else {
                                handleErrorResponse(error);
                                $state.go("state_district_pin.inquiry");
                                }
      });
    }

     function paginationControls () {
                         vm.pages = [];
                         for(var i=1; i<= parseInt(vm.maxPages); i++){
                            vm.pages.push(parseInt(i));
                          }
                         if(vm.pageNo != 1){
                             vm.showPrevious = true;
                         }
                         else{
                             vm.showPrevious = false;
                         }

                         if(vm.pageNo != vm.maxPages){
                              vm.showNext = true;
                         }
                         else{
                              vm.showNext = false;
                         }
                         }

                          function goPreviousPage() {
                                              vm.pageNo = parseInt(vm.pageNo) - 1;
                                               if ($state.current.name == 'state_district_pin.inquiry') {
                                                    getInquiryDetails();
                                                  }
                                                  else if ($state.current.name == 'state_district_pin.verify') {
                                                    getVerifyDetails();
                                                  }
                                              }
                                              function goNextPage() {
                                              vm.pageNo = parseInt(vm.pageNo) + 1;
                                               if ($state.current.name == 'state_district_pin.inquiry') {
                                                    getInquiryDetails();
                                                  }
                                                  else if ($state.current.name == 'state_district_pin.verify') {
                                                    getVerifyDetails();
                                                  }
                                              }

    function getRemarksList() {
      vm.referenceId = '055';
      var requestData = { 'referenceId': vm.referenceId };
      StateDistrictPinService.getRemarksList(requestData).then(function (response) {
        vm.remarksList = response.data.result || []; // Ensure it's an array
      }, function (error) {
        handleErrorResponse(error);
      });
    }


    function uploadFileExistCheck() {

      StateDistrictPinService.dataExistsCheck().then(function (response) {
        if (response.data.result.flag == "Y") {
          uploadFile("Record already exists, Are you sure want to upload this file?");
        }
        else {
          uploadFile("Are you sure want to upload this file?");
        }
      }, function (error) {
        handleErrorResponse(error);
      });

    }
vm.fileValidations = function (input) {
    const file = input.files[0];
    vm.fileSizeError = false;
    if (file) {
        const maxSizeInMB = 5;
        const maxSizeInBytes = maxSizeInMB * 1024 * 1024;
        if (file.size > maxSizeInBytes) {
            vm.fileSizeError = true;
            input.value = ""; // Clear the file input
            vm.file = null;
        } else {
            vm.file = file;
            vm.fileSizeError = false;
        }
    }
};
    function uploadFile(msg) {

      var $el = angular.element("input[type='file']");
//      Swal.fire({
//        title: "Are you sure?",
//        text: msg,
//        type: "warning",
//        showCancelButton: true,
//        cancelButtonClass: "btn-cancel",
//        confirmButtonClass: "btn-primary",
//        confirmButtonText: "Yes!",
//        closeOnConfirm: false,
//        showLoaderOnConfirm: true
//      },
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
          StateDistrictPinService.save(vm.file).then(function (response) {
                vm.loader = false;
                clearFileInput($el);
                vm.file = "";
                //var json = JSON.parse(response.data);
                Swal.fire("Uploaded!", "File Uploaded Successfully", "success");
              }, function (error) {
                vm.loader = false;
                var json = JSON.parse(error.data);
                Swal.fire("Error!", json.message, "error");
                clearFileInput($el);
              });
      }  });
    }


    function verifySubmit() {
//      Swal.fire({
//        title: "Confirm Verify",
//        text: "Are you sure want to verify?",
//        type: "warning",
//        showCancelButton: true,
//        cancelButtonClass: "btn-cancel",
//        confirmButtonClass: "btn-primary",
//        confirmButtonText: "Yes",
//        cancelButtonText: "No",
//        closeOnConfirm: false,
//        showLoaderOnConfirm: true
//      },
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
          StateDistrictPinService.verifySubmit()
            .then(
              function (response) {
                vm.loader = false;
                Swal.fire("Verified!", "Verified Successfully", "success");
                location.href = '#!/state_district_pin/action/inquiry';
              }, function (error) {
                handleErrorResponse(error);
                vm.loader = false;
              });
      }  });
    }
    vm.rejectReason = rejectReason;
    function rejectReason() {
      angular.element('#permissionMappingDelete').modal('toggle');
    }
    vm.validateInput = validateInput;
    function validateInput(event, id) {

      const textBox = document.getElementById(id);

      var charCode = event.which || event.keyCode;
      var charTyped = String.fromCharCode(charCode);

      if (textBox) {
        const cursorPosition = textBox.selectionStart;
        if (cursorPosition == 0 && charTyped === ' ') {
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


//            function getVerifyDeleteSelect() {
//                vm.loader = true;
//                var requestData = {
//                    'schemeCode': vm.schemeCode,
//                    'versionNo': vm.versionNo,
//                    'effeFromDate': vm.effeFromDate
//                };
//                StateDistrictPinService.VerifyDeleteTocSlab(requestData).then(
//                    (response) => {
//                        vm.loader = false;
//                        vm.auditInfo = response.data.result;
//                         vm.rejectionRemarks = response.data.result.rejectionRemarks;
//                        vm.tocSlabList = response.data.result.oldNewValueList;
//                        vm.effeFromDate = vm.tocSlabList[0].C003.oldValue;
//                        vm.versionNo = vm.tocSlabList[0].C002.oldValue;
//                        //                            vm.bankGstTinNo=vm.tocSlabList[0].C015.oldValue;
//    //                    vm.cgst = vm.tocSlabList.C015.oldValue;
//    //                    vm.sgst = vm.tocSlabList.C016.oldValue;
//                        vm.cgst = vm.tocSlabList[0].C015.oldValue;
//                        vm.sgst = vm.tocSlabList[0].C016.oldValue;
//                        vm.verifySubmitList = response.data.result.oldNewValueList;
//
//                    },
//                    function(error) {
//                        vm.loader = false;
//                        handleErrorResponse(error);
//                        $state.go('rbi_bond_toc_slab_maintenance.inquiry');
//                    });
//            }


    function deleteSubmit() {
      vm.loader = true;
      let requestData ={};
     requestData.remarks=vm.remarks;
      StateDistrictPinService.deleteSubmit(requestData).then(function (response) {
        if (response.data.message === 'SUCCESS') {
          vm.loader = false;
          Swal.fire("Rejected!", "Rejected Successfully", "success");
          location.href = '#!/state_district_pin/action/inquiry';
        } else {
          vm.loader = false;
          Swal.fire("WARNING!", response.data.message, "error");
          location.href = '#!/state_district_pin/action/inquiry';
        }
        const backdrop = document.querySelector('.modal-backdrop');
        if (backdrop) {
          backdrop.parentNode.removeChild(backdrop);
          document.body.classList.remove('modal-open');
        }
      }, function (error) {
        vm.loader = false;
        handleErrorResponse(error);
        const backdrop = document.querySelector('.modal-backdrop');
        if (backdrop) {
          backdrop.parentNode.removeChild(backdrop);
          document.body.classList.remove('modal-open');
        }
        location.href = '#!/state_district_pin/action/inquiry';
      });
    }
   function downloadPincode() {
       vm.loader = true;
       StateDistrictPinService.downloadPincode().then(function (response) {
           vm.loader = false;
           var fileName = response.data.result.fileName;
           var fileData = response.data.result.fileData;
           var byteCharacters = atob(fileData);
           var byteNumbers = new Array(byteCharacters.length);
           for (var i = 0; i < byteCharacters.length; i++) {
               byteNumbers[i] = byteCharacters.charCodeAt(i);
           }
           var byteArray = new Uint8Array(byteNumbers);
           var blob = new Blob([byteArray], { type: "application/vnd.ms-excel" });
           var blobUrl = window.URL.createObjectURL(blob);
           var link = document.createElement("a");
           link.href = blobUrl;
           link.download = fileName;
           document.body.appendChild(link);
           link.click();
           document.body.removeChild(link);
           setTimeout(function () {
               window.URL.revokeObjectURL(blobUrl);
           }, 100);

           swal("Downloaded", "Downloaded successfully.", "success");
       }, function (error) {
           vm.loader = false;
           handleErrorResponse(error);
       });
   }

  }
})();