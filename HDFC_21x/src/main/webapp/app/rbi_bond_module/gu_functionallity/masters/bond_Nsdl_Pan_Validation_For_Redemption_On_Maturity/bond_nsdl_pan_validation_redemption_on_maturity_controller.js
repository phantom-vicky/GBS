(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('bondNsdlPanValidationRedemptionOnMaturityController',
        bondNsdlPanValidationRedemptionOnMaturityController);
    bondNsdlPanValidationRedemptionOnMaturityController.$inject = ['$scope', '$filter', '$state', '$rootScope',
        '$stateParams', '$location', '$timeout', 'bondNsdlPanValidationRedemptionOnMaturityService', 'commonService'
    ];
    function bondNsdlPanValidationRedemptionOnMaturityController($scope, $filter, $state, $rootScope, $stateParams,
        $location, $timeout, bondNsdlPanValidationRedemptionOnMaturityService, commonService) {
		var vm = this;
		vm.moduleName="RBI BOND";
		vm.getRemarksList = getRemarksList;
		vm.getModuleName=getModuleName;
		vm.fileUploadSubmit=fileUploadSubmit;
		vm.apiPanTriggerSubmit=apiPanTriggerSubmit;
		vm.downloadSubmit=downloadSubmit;
		vm.getInquiryDetails=getInquiryDetails;
		vm.submitVerifyForm=submitVerifyForm;
		vm.validateInput=validateInput;
		vm.rejectReason=rejectReason;
		vm.deleteSubmit=deleteSubmit;
		vm.verifyDownload=verifyDownload;
		vm.verifyApiPanTriggerSubmit=verifyApiPanTriggerSubmit;
		vm.template=template;
        vm.solDate = new Date($rootScope.globals.currentUser.solDate);
        //Pagination related function start
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
                         vm.pageNo = 1;
                         vm.pageData=null;
                          //BASHA CODE START
                                         vm.records = [];
                                         vm.filteredRecords = [];
                                         vm.pageSizeOptions = [10, 25, 50, 100];
                                         vm.pageSize = vm.pageSizeOptions[0];
                                         vm.currentPage = 1;
                                         vm.maxPagesToShow = 5;
                                         vm.searchText = '';
                                         vm.loading = true;
                                         vm.showExportOptions = false;
                                         vm.itemsPerPage = "10";
                                         //END

                           function getRemarksList() {
                                             vm.referenceId = '056';
                                             var requestData = { 'referenceId': vm.referenceId };

                                             bondNsdlPanValidationRedemptionOnMaturityService.getRemarksList(requestData).then(function(response) {
                                                 vm.remarksList = response.data.result || []; // Ensure it's an array
                                             }, function(error) {
                                                 handleErrorResponse(error);
                                             });
                                         }

                        function goPreviousPage() {
                         vm.pageNo = parseInt(vm.pageNo) - 1;
                         vm.getInquiryDetails();
                         }
                         function goNextPage() {
                         vm.pageNo = parseInt(vm.pageNo) + 1;
                         vm.getInquiryDetails();
                         }
                         function paginationControls () {
                         vm.pages = [];
                         vm.maxPages = parseInt(vm.pageDetails.maxPages);
                         for(var i=1; i<= parseInt(vm.pageDetails.maxPages); i++){
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
        //end
        //BASHA CODE START
                                                  vm.setPage = function(page) {
                                                      console.log("Page Number in setPage: " + page);
                                                      if (page < 1) {
                                                          // If the page number is less than 1, set it to the first page
                                                          vm.currentPage = 1;
                                                      } else if (page > vm.totalPages) {
                                                          // If the page number is greater than the total pages, set it to the last page
                                                          vm.currentPage = vm.totalPages;
                                                      } else {
                                                          // If the page number is within the valid range, set it normally
                                                          vm.currentPage = page;
                                                      }
                                                      console.log("vm.currentPage: "+vm.currentPage)
                                                      vm.updatePagination();
                                                  };

                                                  vm.updatePagination = function() {
                                                      try {
                                                          var startPage, endPage;
                                                          console.log("Inside updatePagination function...");

                                                          if (vm.totalPages <= vm.maxPagesToShow) {
                                                              startPage = 1;
                                                              endPage = vm.totalPages;
                                                          } else {
                                                              var maxPagesBeforeCurrentPage = Math.floor(vm.maxPagesToShow / 2);
                                                              var maxPagesAfterCurrentPage = Math.ceil(vm.maxPagesToShow / 2) - 1;

                                                              if (vm.currentPage <= maxPagesBeforeCurrentPage) {
                                                                  startPage = 1;
                                                                  endPage = vm.maxPagesToShow;
                                                              } else if (vm.currentPage + maxPagesAfterCurrentPage >= vm.totalPages) {
                                                                  startPage = vm.totalPages - vm.maxPagesToShow + 1;
                                                                  endPage = vm.totalPages;
                                                              } else {
                                                                  startPage = vm.currentPage - maxPagesBeforeCurrentPage;
                                                                  endPage = vm.currentPage + maxPagesAfterCurrentPage;
                                                              }
                                                          }

                                                          console.log("vm.totalPages: "+vm.totalPages);
                                                          console.log("startPage: "+startPage);
                                                          console.log("endPage: "+endPage);

                                                          vm.pages = [];
                                                          for (var i = startPage; i <= endPage; i++) {
                                                              vm.pages.push(i);
                                                          }
                                                          console.log("vm.pages: "+vm.pages);

                                                      } catch (error) {
                                                          console.error("An error occurred in updatePagination:", error);
                                                      }
                                                  };


                                                  vm.prevPage = function() {
                                                      vm.setPage(vm.currentPage - 1);
                                                  };

                                                  vm.nextPage = function() {
                                                      vm.setPage(vm.currentPage + 1);
                                                  };

                                                  vm.firstPage = function() {
                                                      vm.setPage(1);
                                                  };

                                                  vm.lastPage = function() {
                                                      vm.setPage(vm.totalPages);
                                                  };

                                                  vm.updateFilteredRecords = function() {
                                                      vm.filteredRecords = vm.records.filter(function(record) {
                                                          return (
                                                              (record.oldPan && record.oldPan.toUpperCase().includes(vm.searchText.toUpperCase())) ||
                                                              (record.dob && String(record.dob).toUpperCase().includes(vm.searchText.toUpperCase())) ||
                                                              (record.typeOfMatch && String(record.typeOfMatch).toUpperCase().includes(vm.searchText.toUpperCase())) ||
                                                              (record.name && String(record.name).toUpperCase().includes(vm.searchText.toUpperCase())) ||
                                                              (record.newName && String(record.newName).toUpperCase().includes(vm.searchText.toUpperCase())) ||
                                                              (record.newDob && String(record.newDob).toUpperCase().includes(vm.searchText.toUpperCase())) ||
                                                              (record.panStatusValidateDate && String(record.panStatusValidateDate).toUpperCase().includes(vm.searchText.toUpperCase())) ||
                                                              (record.userId && String(record.userId).toUpperCase().includes(vm.searchText.toUpperCase())) ||
                                                              (record.remarks && String(record.remarks).toUpperCase().includes(vm.searchText.toUpperCase())) ||
                                                              (record.panStatus && String(record.panStatus).toUpperCase().includes(vm.searchText.toUpperCase())) ||
                                                              (record.newPan && String(record.newPan).toUpperCase().includes(vm.searchText.toUpperCase()))
                                                          );
                                                      });

                                                      vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
                                                      vm.setPage(1);
                                                  };

                                                  vm.updatePageSize = function() {
                                                      vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
                                                      console.log("totalPages: "+vm.totalPages);
                                                      vm.setPage(1);
                                                  };
if ($state.current.name == 'bond_nsdl_pan_validation_redemption_on_maturity.inquiry_details'){
                 vm.type = $stateParams.obj.type;
                 vm.fileStatus = $stateParams.obj.fileStatus;
                 vm.schemeCode = $stateParams.obj.schemeCode;
                 vm.schemeName = $stateParams.obj.schemeName;
                 getInquiryDetails();
                }
		function deleteSubmit(){
		vm.loader = true;
                var requestData = {
                     'schemeCode' : vm.schemeCode,
                     'schemeName' : vm.schemeName,
                     'fileType' : vm.fileType,
                     'type' : vm.type,
                     'deleteRemarks':vm.remarks
                };
                bondNsdlPanValidationRedemptionOnMaturityService.deleteSubmit(requestData)
                    .then(function(response) {
                        vm.loader = false;
                           const backdrop = document.querySelector('.modal-backdrop');
                        if (backdrop) {
                          backdrop.parentNode.removeChild(backdrop);
                          document.body.classList.remove('modal-open');
                        }
                        Swal.fire("Rejected!", "File Rejected Successfully", "success");
                        $state.go('bond_nsdl_pan_validation_redemption_on_maturity.inquiry');
                    })
                    .catch(function(error) {
                        vm.loader = false;
                        handleErrorResponse(error);
                            const backdrop = document.querySelector('.modal-backdrop');
                            if (backdrop) {
                            backdrop.parentNode.removeChild(backdrop);
                            document.body.classList.remove('modal-open');
                           }
                        $state.go('bond_nsdl_pan_validation_redemption_on_maturity.verify');
                    });
		}
		function submitVerifyForm(){
//		 Swal.fire({
//                                    title: "Are you sure?",
//                                    text: "Do you want to Really Verify this record?",
//                                    type: "warning",
//                                    showCancelButton: true,
//                                    cancelButtonClass: "btn-cancel",
//                                    confirmButtonClass: "btn-primary",
//                                    confirmButtonText: "Yes",
//                                    closeOnConfirm: false
//                                },
//                                function() {


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
                                        'schemeCode' : vm.schemeCode,
                                        'schemeName' : vm.schemeName,
                                        'type' : vm.type,
                                        'fileType' : vm.fileType
                                    };
                                    bondNsdlPanValidationRedemptionOnMaturityService.submitVerifyForm(requestData).then(function(response) {
                                        vm.loader = false;
                                        vm.responseResult = response.data.result;
                                        Swal.fire("Verified!", "File Verified Successfully","success");
                                        $state.go('bond_nsdl_pan_validation_redemption_on_maturity.inquiry')
                                    }, function(error) {
                                        vm.loader = false;
                                        if(error.data.message == 'No Records To Verify/Reject' | error.data.message == 'NDF'){
                                       vm.noDataFound = error.data.message;
                                       Swal.fire("Warning!",vm.noDataFound,"warning");
                                        }
                                      else{
                                      handleErrorResponse(error);
                                      $state.go("bond_nsdl_pan_validation_redemption_on_maturity.verify");
                                      }
                                    })
                                    }
                                });
		}
		function getInquiryDetails(){
       vm.loader = true;
       if(vm.fileStatus=='A'){
       var requestData = {
                  'schemeCode' : vm.schemeCode,
                  'schemeName' : vm.schemeName,
                  'fileStatus' : null,
                   'type' : vm.type,
                    'pageSize' : vm.pageSize,
                   'pageNo' : vm.pageNo,
                  'searchValue' : vm.searchValue,
                 'sortOrder' : vm.sortOrder,
                 'sortField' : vm.sortField
              };
              }else{
       var requestData = {
           'schemeCode' : vm.schemeCode,
           'schemeName' : vm.schemeName,
           'fileStatus' : vm.fileStatus,
           'type' : vm.type,
           'pageSize' : vm.pageSize,
           'pageNo' : vm.pageNo,
           'searchValue' : vm.searchValue,
           'sortOrder' : vm.sortOrder,
           'sortField' : vm.sortField
       };
       }
       bondNsdlPanValidationRedemptionOnMaturityService.getInquiryDetails(requestData).then(function(response) {
            vm.loader = false;
            vm.filteredRecords=response.data.result.gstAckFileDownloadDTOList;
            vm.records = response.data.result.gstAckFileDownloadDTOList;
            vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
            console.log("totalPages: "+vm.totalPages);
            vm.updatePagination();
            $state.go('bond_nsdl_pan_validation_redemption_on_maturity.inquiry_details');
       }, function(error) {
           vm.loader = false;
           if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
           vm.noDataFound = error.data.message;
           Swal.fire("Warning!",vm.noDataFound,"warning");
           }
         else{
           handleErrorResponse(error);
           $state.go("bond_nsdl_pan_validation_redemption_on_maturity.inquiry");
         }
       })
		}
		vm.errorMessage = "Please upload a .xlsx file.";
		vm.handleFiles = function(input) {
        if(vm.fileType){
        if (vm.files && (vm.files.type!== "application/vnd.ms-excel")) {
           vm.invalidFile = true;
            document.getElementById('fileInput').value = ''; // Clear the file input
           }else{
            vm.invalidFile = false;
           }
           }else{
           document.getElementById('fileInput').value = ''; // Clear the file input
           Swal.fire("Warning!","Kindly Select FileType", "warning");
           $state.go('bond_nsdl_pan_validation_redemption_on_maturity.upload');
           }
   $scope.$apply(); // Ensure scope is updated in AngularJS
};
		function fileUploadSubmit(){
//            Swal.fire({
//                 title: "Are you sure?",
//                 text: "Do you want to Really Upload this record?",
//                 type: "warning",
//                 showCancelButton: true,
//                 cancelButtonClass: "btn-cancel",
//                 confirmButtonClass: "btn-primary",
//                 confirmButtonText: "Yes, Add it!",
//                 closeOnConfirm: false,
//                 showLoaderOnConfirm: true
//           },
//               function(){


                Swal.fire({
                  title: "Are you sure?",
                  text: "Do you want to Really Upload this record?",
                  icon: "warning", // use "icon", not "type"
                  showCancelButton: true,
                  confirmButtonText: "Yes,Add it!",
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
                       'schemeCode' : vm.schemeCode,
                       'schemeName' : vm.schemeName,
                       'type' : vm.type,
                       'fileType' : vm.fileType
                   };
                   bondNsdlPanValidationRedemptionOnMaturityService.fileUploadSubmit(vm.files,requestData).then(function (response){
                       vm.loader = false;
                       var value=JSON.parse(response.data);
                       Swal.fire("Uploaded!", "File Upload Successfully","success");
                       $state.go('bond_nsdl_pan_validation_redemption_on_maturity.inquiry');
                   },function(error){
                   vm.loader = false;
                  if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
                  vm.noDataFound = error.data.message;
                  Swal.fire("Warning!",vm.noDataFound,"warning");
                   } else{
                   handleErrorResponse1(error);
                   $state.go('bond_nsdl_pan_validation_redemption_on_maturity.upload');
                 }
              })
              }
             });
            }
                    function handleErrorResponse1(e) {
                        if (typeof e.data === 'string') {
                            var value = JSON.parse(e.data);
                            var t = value.message ? value.message : "Server Error";
                            422 == e.status ? Swal.fire("Warning", t, "warning") : Swal.fire("Warning!", t, "warning")
                        }else if(typeof e.data === 'object' && e.data !== null){
                            var value = e.data;
                            var t = value.message ? value.message : "Server Error";
                            422 == e.status ? Swal.fire("Warning", t, "warning") : Swal.fire("Warning!", t, "warning")
                        }
                                       }
                   vm.getUploadModuleName=getUploadModuleName;
                   function getUploadModuleName(){
                   		        vm.loader = true;
                                   vm.referenceId = 'RBI Bond';
                                   var requestData = {
                                       'referenceId': vm.referenceId
                                   }
                                   bondNsdlPanValidationRedemptionOnMaturityService.getUploadModuleName(requestData).then(function(response) {
                                       vm.loader = false;
                                       vm.schemeNameList = response.data.result;
                                       angular.element('#levelNameModal').modal('toggle');
                                   }, function(error) {
                                       vm.loader = false;
                                       handleErrorResponse(error);
                                   });
                   }
                                      vm.getVerifyModuleName=getVerifyModuleName;
                                      function getVerifyModuleName(){
                                      		        vm.loader = true;
                                                      vm.referenceId = 'RBI Bond';
                                                      var requestData = {
                                                          'referenceId': vm.referenceId
                                                      }
                                                      bondNsdlPanValidationRedemptionOnMaturityService.getVerifyModuleName(requestData).then(function(response) {
                                                          vm.loader = false;
                                                          vm.schemeNameList = response.data.result;
                                                          angular.element('#levelNameModal').modal('toggle');
                                                      }, function(error) {
                                                         vm.loader = false;
                                                          handleErrorResponse(error);
                                                      });
                                      }
                                     vm.getInquiryModuleName=getInquiryModuleName;
                   function getInquiryModuleName(){
                    vm.loader = true;
                       vm.referenceId = 'RBI Bond';
                       var requestData = {
                           'referenceId': vm.referenceId
                       }
                       bondNsdlPanValidationRedemptionOnMaturityService.getInquiryModuleName(requestData).then(function(response) {
                           vm.loader = false;
                           vm.schemeNameList = response.data.result;
                           angular.element('#levelNameModal').modal('toggle');
                       }, function(error) {
                       vm.loader = false;
                           handleErrorResponse(error);
                       });
                   }
                   vm.getDownloadModuleName=getDownloadModuleName;
function getDownloadModuleName(){
		        vm.loader = true;
                vm.referenceId = 'RBI Bond';
                var requestData = {
                    'referenceId': vm.referenceId
                }
                bondNsdlPanValidationRedemptionOnMaturityService.getDownloadModuleName(requestData).then(function(response) {
                    vm.loader = false;
                    vm.schemeNameList = response.data.result;
                    angular.element('#levelNameModal').modal('toggle');
                }, function(error) {
                vm.loader = false;
                    handleErrorResponse(error);
                });
}
function verifyDownload(){
		vm.loader = true;
		var requestData = {
       'schemeCode' : vm.schemeCode,
       'schemeName' : vm.schemeName,
       'type' : vm.type,
       'fileType' : vm.fileType
        };
       bondNsdlPanValidationRedemptionOnMaturityService.verifyDownload(requestData).then(function(response){
       vm.loader = false;
       if(response.data.result.fileName) {
      vm.fileData = response.data.result.file;
      vm.fileName = response.data.result.fileName;
      saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
      }
       Swal.fire("Downloaded", response.data.message, "success");
            }, function(error) {
             vm.loader = false;
             if(error.data.message == 'No Records To Download' | error.data.message == 'NDF'){
           vm.noDataFound = error.data.message;
           Swal.fire("Warning!",vm.noDataFound,"warning");
            }
          else{
          handleErrorResponse(error);
          $state.go("bond_nsdl_pan_validation_redemption_on_maturity.verify");
          }
         })
		}
		function downloadSubmit(){
		vm.loader = true;
		var requestData = {
           'schemeCode' : vm.schemeCode,
           'schemeName' : vm.schemeName,
           'fileType' : vm.fileType,
           'type':vm.type
       };
         bondNsdlPanValidationRedemptionOnMaturityService.downloadSubmit(requestData).then(function(response){
                   vm.loader = false;
                    if(response.data.result.fileName) {
                   vm.fileData = response.data.result.file;
                   vm.fileName = response.data.result.fileName;
                   saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
                   }
                   Swal.fire("Downloaded", response.data.message, "success");
                   }, function(error) {
                   vm.loader = false;
                  if(error.data.message == 'No Records Found' | error.data.message == 'No PAN To Download'| error.data.message == 'NDF'){
                   vm.noDataFound = error.data.message;
                   Swal.fire("Warning!",vm.noDataFound,"warning");
                  }
                  else{
                  handleErrorResponse(error);
                  $state.go("bond_nsdl_pan_validation_redemption_on_maturity.download");
                  }
           })
		}
		function template(){
        		if(vm.fileType){
        		vm.loader = true;
        		var requestData = {
                   'schemeCode' : vm.schemeCode,
                   'schemeName' : vm.schemeName,
                   'fileType' : vm.fileType
               };
                 bondNsdlPanValidationRedemptionOnMaturityService.template(requestData).then(function(response){
                   vm.loader = false;
                   vm.verifiedFileData = response.data.result.fileData;
                   vm.verifiedFileName = response.data.result.fileName;
                   saveAs(getBlobFromBase64String(vm.verifiedFileData), vm.verifiedFileName)
                   Swal.fire("Downloaded", response.data.message, "success");
                        }, function(error) {
                         vm.loader = false;
                      if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
                       vm.noDataFound = error.data.message;
                       Swal.fire("Warning!",vm.noDataFound,"warning");
                        }
                      else{
                      handleErrorResponse(error);
                      $state.go("bond_nsdl_pan_validation_redemption_on_maturity.upload");
                      }
                     })
                     }else{
                     Swal.fire("Warning!","Kindly Select FileType", "warning");
                     $state.go('bond_nsdl_pan_validation_redemption_on_maturity.upload');
                     }
        		}
		function apiPanTriggerSubmit(){
        		vm.loader = true;
        		var requestData = {
                   'schemeCode' : vm.schemeCode,
                   'schemeName' : vm.schemeName,
                   'type':vm.type,
                   'fromDate' :  $filter('date')(vm.fromDate, "dd-MMM-yyyy"),
                   'toDate' : $filter('date')(vm.toDate, "dd-MMM-yyyy")
               };
             bondNsdlPanValidationRedemptionOnMaturityService.apiPanTriggerSubmit(requestData).then(function(response){
              vm.loader = false;
              Swal.fire(response.data.message, "API initiated for PAN Status with NSDL", "success");
              $state.go('bond_nsdl_pan_validation_redemption_on_maturity.inquiry');
                }, function(error) {
                    vm.loader = false;
                    if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
                   vm.noDataFound = error.data.message;
                   Swal.fire("Warning!",vm.noDataFound,"warning");
                    }
                  else{
                   vm.noDataFound = error.data.message;
                   Swal.fire("Warning!",vm.noDataFound,"warning");
                   $state.go('bond_nsdl_pan_validation_redemption_on_maturity.api');
                  }
                })
		}
		function verifyApiPanTriggerSubmit(){
		if(vm.fileType=='P001'){
                		vm.loader = true;
                		var requestData = {
                           'schemeCode' : vm.schemeCode,
                           'schemeName' : vm.schemeName,
                           'type':vm.type,
                           'fileType':vm.fileType
                       };
                                 bondNsdlPanValidationRedemptionOnMaturityService.verifyApiPanTriggerSubmit(requestData).then(function(response){
                                          vm.loader = false;
                                     Swal.fire(response.data.message, "API initiated for PAN Status with NSDL", "success");
                                          $state.go('bond_nsdl_pan_validation_redemption_on_maturity.inquiry');
                                            }, function(error) {
                                                vm.loader = false;
                                                if(error.data.message == 'No Records Found' | error.data.message == 'NDF'){
                                               vm.noDataFound = error.data.message;
                                               Swal.fire("Warning!",vm.noDataFound,"warning");
                                                }
                                              else{
                                               vm.noDataFound = error.data.message;
                                               Swal.fire("Warning!",vm.noDataFound,"warning");
                                               $state.go('bond_nsdl_pan_validation_redemption_on_maturity.verify');
                                              }
                                            })
                                            }else{
                                             Swal.fire("Warning!","Kindly Select FileType NSDL", "warning");
                                             $state.go('bond_nsdl_pan_validation_redemption_on_maturity.verify');
                                            }
        		}
		function rejectReason() {
          angular.element('#permissionMappingDelete').modal('toggle');
          }
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

//Get Module Name
        function getModuleName() {
                vm.referenceId = 'RBI Bond';
                var requestData = {
                    'referenceId': vm.referenceId
                }
                bondNsdlPanValidationRedemptionOnMaturityService.getRbiBondModuleNameDetails(requestData).then(function(response) {
                    vm.schemeNameList = response.data.result;
                    angular.element('#levelNameModal').modal('toggle');
                }, function(error) {
                vm.loader = false;
                    handleErrorResponse(error);
                });
            }
  vm.fileStatusList=[
              {
                  "fileStatus": "ALL",
                  "fileStatusValue": "A"
              },
              {
                  "fileStatus": "SUCCESS",
                  "fileStatusValue": "S"
              },
              {
                  "fileStatus": "MISMATCH",
                  "fileStatusValue": "M"
              }
          ];
  vm.typeList=[
        {
            "subCodeDescription": "On Maturity Redemption PAN Validation",
            "subCode": "OM001"
        },
        {
            "subCodeDescription": "Pre-Maturity PAN Validations",
            "subCode": "BM001"
        }
    ];
    vm.fileTypeList=[
            {
                "subCodeDescription": "NSDL PAN status",
                "subCode": "P001"
            },
            {
                "subCodeDescription": "Income Tax PAN Status",
                "subCode": "P002"
            }
        ];
        vm.fromDateValidation=fromDateValidation;
     function fromDateValidation() {
               vm.fromDateCalenderOptions = {
                                      minDate: vm.fromDate,
                                      maxDate:  $filter('date')(vm.solDate, 'dd/MM/yyyy')
                                  };
              vm.toDateCalenderOptions = {
                                minDate: vm.fromDate,
                                maxDate: $filter('date')(vm.solDate, 'dd/MM/yyyy')
                            };
            }
       } })();