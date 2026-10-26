(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
		    'panValidationRetriggerController',
			panValidationRetriggerController);
	panValidationRetriggerController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','panValidationRetriggerService', 'commonService' ];

	function panValidationRetriggerController($scope, $filter, $state,
			$stateParams, $location, $rootScope, panValidationRetriggerService,
			commonService) {
        var vm = this;
        vm.currentPage = 1;
        vm.itemsPerPage = '20';
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.getModuleName=getModuleName;
        vm.submitVerifyForm=submitVerifyForm;
        vm.panStatusDateValidation = panStatusDateValidation;
        vm.submitValidateForm = submitValidateForm;
        vm.newDobValidation = newDobValidation;
        vm.openDatePicker = openDatePicker;
        vm.validationAlphaNumeric = validationAlphaNumeric;
        vm.currentDate = new Date();
        vm.getDetails=getDetails;
        vm.panValidation=panValidation;
        vm.datePickerStates = [];
        vm.panPattern = /[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}/;
         if ($state.current.name == 'pan_validation_retrigger.inquiry_details'){
                 vm.panStatusValidateDate = $stateParams.obj.panStatusValidateDate;
                 vm.fileMode = $stateParams.obj.fileMode;
                 vm.fileStatus = $stateParams.obj.fileStatus;
                 vm.schemeCode = $stateParams.obj.schemeCode;
                 vm.schemeName = $stateParams.obj.schemeName;
                 getDetails();
                }
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
        vm.pageSizeOptions = [20, 50, 100, 200];
        vm.pageSize = vm.pageSizeOptions[0];
        vm.currentPage = 1;
        vm.maxPagesToShow = 5;
        vm.searchText = '';
        vm.loading = true;
        vm.showExportOptions = false;
        vm.itemsPerPage = "20";
        //END
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
            vm.filteredRecords = vm.records.filter(function(record, index) {
                // Check if the record matches the search criteria
                const isMatch =
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
                    (record.newPan && String(record.newPan).toUpperCase().includes(vm.searchText.toUpperCase()));
                    if (isMatch) {
                    if (record.nameStatus === 'Y' && (record.panStatus === 'E' || record.panStatus === 'Y') && record.dobStatus === 'Y' && null!=vm.searchText) {
                        //vm.filteredRecords[index].panRetriggerFormItr = record.newPan ? record.newPan : record.oldPan;
                        if (!vm.shouldHideButton1) {
                            vm.shouldHideButton1 = [];
                        }
                        vm.shouldHideButton1[index] = true;
                    }else if (record.nameStatus === 'Y' && (record.panStatus === 'E' || record.panStatus === 'Y') && record.dobStatus === 'Y') {
                        //vm.filteredRecords[index].panRetriggerFormItr = record.newPan ? record.newPan : record.oldPan;
                        if (!vm.shouldHideButton) {
                            vm.shouldHideButton = [];
                        }
                        vm.shouldHideButton[index] = true;
                    }else{
                        if (!vm.shouldHideButton) {
                            vm.shouldHideButton = [];
                        }
                        vm.shouldHideButton[index] =false;
                    }
                }
                return isMatch;
            });
            vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
            vm.setPage(1);
        };

        vm.updatePageSize = function() {
            vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
            console.log("totalPages: "+vm.totalPages);
            vm.itemsPerPage = vm.pageSize;
            vm.setPage(1);
        };
    function openDatePicker(index) {
      // Set the open state for the specific date picker
      vm.datePickerStates[index] = !vm.datePickerStates[index];
    }
function panValidation(event) {
                       var key = event.key;
                          var input = event.target.value;
                          // Disallow spaces
                          if ((key === ' ' || input.includes(' ')) ||(key === '.' || input.includes('.'))) {
                              event.preventDefault();
                          }
        }
        function validationAlphaNumeric(event) {
        				var key = event.key;
        				var input = event.target.value;
        				if (key === ' ' && input.slice(-1) === ' ') {
        					event.preventDefault();
        				}
        				if (!key.match(/^[a-zA-Z\s]$/) && key !== 'Backspace' && key !== 'Enter') {
        					event.preventDefault();
        				}
        			}
function panStatusDateValidation() {
                   // Set the minimum date to be 7 days before the current date
                   vm.panStatusValidateDateCalenderOptions = {
                       minDate: vm.fromDate,
                       maxDate: vm.currentDate
                   };
                   // Set the minimum date for liquidationDate
                   vm.panStatusValidateDate = vm.solDate;
        }
        function newDobValidation(x) {
                           // Set the minimum date to be 7 days before the current date
                           vm.newDobCalenderOptions = {
                               minDate: vm.fromDate,
                               maxDate: vm.currentDate
                           };
                           // Set the minimum date for liquidationDate
                           vm.newDob = vm.currentDate;
                }

                       vm.fileModeList=
                   [
                          {
                              "subCodeDescription": "Cash-in",
                              "subCode": "C"
                          },
                          {
                              "subCodeDescription": "Net Banking",
                              "subCode": "NB"
                          }
                      ];
                      vm.netBankingFileTypeList=
                       [
                              {
                                  "subCodeDescription": "RRBNB",
                                  "subCode": "RRBNB"
                              },
                              {
                                  "subCodeDescription": "RRBNB_BB",
                                  "subCode": "RRBNB_BB"
                              }
                          ];
                     vm.cashInFileTypeList=
                       [
                              {
                                  "subCodeDescription": "TAXBOND",
                                  "subCode": "TAXBOND"
                              }
                          ];

                     vm.fileStatusList=
                       [
                              {
                                  "fileStatus": "ALL",
                                  "fileStatusValue": "A"
                              },
                              {
                                  "fileStatus": "SUCCESS",
                                  "fileStatusValue": "S"
                              },
                              {
                                  "fileStatus": "MISSMATCH",
                                  "fileStatusValue": "M"
                              }
                          ];
 function getModuleName() {
            vm.loader = true;
            vm.referenceId = 'TAX';
            var requestData = {
                'referenceId': vm.referenceId
            };
            panValidationRetriggerService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                vm.loader = false;
                vm.schemeNameList = response.data.result;
                angular.element('#levelNameModal').modal('toggle');
                }, function (error) {
                handleErrorResponse(error);
                vm.loader = false;
            });
        }
         function getDetails(){
          vm.model='';
                                 vm.loader = true;

                                 var requestData = {
                                'panStatusValidateDate':$filter('date')(vm.panStatusValidateDate,'dd-MMM-yyyy'),
                                'fileStatus': vm.fileStatus,
                                'fileMode' : vm.fileMode,
                                'schemeName':vm.schemeName,
                                'schemeCode': vm.schemeCode
                                     }

                                 panValidationRetriggerService.getDetails(requestData).then(function(response) {
                                 vm.loader = false;
                                 if(vm.fileStatus=='S'){
                                     vm.model1 =response.data.result;
                                     vm.model='';
                                 }else{
                                     vm.filteredRecords =response.data.result;
                                     vm.model1='';
                                     if (!vm.shouldHideButton) {
                                         vm.shouldHideButton = [];
                                     }
                                     if (!vm.shouldHideButton1) {
                                         vm.shouldHideButton1 = [];
                                     }
                                     vm.filteredRecords.forEach((record, index) => {
                                         if (record.processStatus === 'Y' || record.processStatus === 'F') {
                                             // Disable the button for the record at this index
                                             vm.shouldHideButton[index] = true;
                                             vm.shouldHideButton1[index] = true;
                                         } else {
                                             // Optionally, reset to false or perform other logic for records where processStatus !== 'Y'
                                             vm.shouldHideButton[index] = false;
                                             vm.shouldHideButton1[index] = false;
                                         }
                                     });
                                     vm.records = response.data.result;
                                     vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
                                     console.log("totalPages: "+vm.totalPages);
                                     vm.updatePagination();
                                 }
                                 $state.go('pan_validation_retrigger.inquiry_details');
                         }, function(error){
                             vm.loader = false;
                             if(error.data.message == 'No Data Found' | error.data.message == 'NDF'){
                               vm.noDataFound = error.data.message;
                               $state.go('pan_validation_retrigger.inquiry');
                             }else{
                               handleErrorResponse(error);
                               $state.go('pan_validation_retrigger.inquiry');
                             }
                         })
                     }

                            /* Verify Submit */
    function submitVerifyForm(value,model) {
        const needsDobUpdate = value.dobStatus === 'N' && value.newDob;
        const needsNameUpdate = value.nameStatus === 'N' && value.newName;
        const needsPanUpdate = value.panStatus === 'N' && value.newPan;
        const noDobUpdate = value.dobStatus === 'Y';
        const noNameUpdate = value.nameStatus === 'Y';
        const noPanUpdate = value.panStatus === 'E' || value.panStatus === 'Y';
        //const forceMatch = value.processStatus === 'FM' && value.remarks;
                  if(
                      (needsDobUpdate && needsNameUpdate && needsPanUpdate) ||
                      (needsDobUpdate && needsNameUpdate && noPanUpdate) ||
                      (needsDobUpdate && noNameUpdate && noPanUpdate) ||
                      (needsNameUpdate &&noDobUpdate && noPanUpdate) ||
                      (needsPanUpdate && noDobUpdate && noNameUpdate) ||
                      (needsPanUpdate&&needsNameUpdate&&noDobUpdate) ||
                      (noDobUpdate && noNameUpdate && noPanUpdate)
                  ){
                      if(!value.remarks && value.processStatus === 'FM'){
                          Swal.fire("Warning!","Necessary field is Required", "warning");
                          $state.go('pan_validation_retrigger.inquiry_details');
                          return false;
                      }
//                   Swal.fire({
//                           title: "Are you sure?",
//                           text: "Do you want to Really Verify this record?",
//                           type: "warning",
//                           showCancelButton: true,
//                           cancelButtonClass: "btn-cancel",
//                           confirmButtonClass: "btn-primary",
//                           confirmButtonText: "Yes",
//                           closeOnConfirm: false
//                       },
//                       function() {



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
                           vm.finalList=[];
                           if(null!=value.newDob&&value.newDob.length==10){
                             value.newDob=moment(value.newDob,"DD/MM/YYYY").format("DD-MMM-YYYY");
                           }else if(null!=value.newDob){
                           value.newDob=$filter('date')(value.newDob,'dd-MMM-yyyy');
                           }
                           if(null!=value.dob){
                               value.dob=moment(value.dob,"DD/MM/YYYY").format("DD-MMM-YYYY");
                           }
                           value.panStatus='E';
                           if(null!=value.processStatus&&value.processStatus === 'FM'){
                               value.nameStatus='Y';
                               value.dobStatus='Y';
                               value.processStatus='F';
                           }
                           vm.finalList.push(value);
                           vm.finalList1 = vm.finalList.map(function(item) {
                            var cleanedItem = Object.assign({}, item);  // Shallow copy the item
                            delete cleanedItem.$$hashKey;  // Delete the $$hashKey property
                            return cleanedItem;
                          });
                           var requestData = {
                               'panStatusValidateDate':$filter('date')(vm.panStatusValidateDate,'dd-MMM-yyyy'),
                               'fileStatus': vm.fileStatus,
                               'fileStatusFlg': 'A',
                               'fileMode' : vm.fileMode,
                               'schemeName':vm.schemeName,
                               'schemeCode': vm.schemeCode,
                               'list' : vm.finalList1
                           };
                           panValidationRetriggerService.submitVerifyForm(requestData).then(function(response) {
                                vm.loader = false;
                                vm.filteredRecords=model;
                               const recordIndex = vm.filteredRecords.findIndex(record => record.oldPan === value.oldPan);
                               if (recordIndex !== -1) {
                                   if (!vm.shouldHideButton) {
                                       vm.shouldHideButton = [];
                                   } if (!vm.shouldHideButton1) {
                                       vm.shouldHideButton1 = [];
                                   }
                                   vm.filteredRecords[recordIndex].panRetriggerFormItr = value.newPan?value.newPan:value.oldPan;
                                   vm.shouldHideButton[recordIndex] =true;
                                   vm.shouldHideButton1[recordIndex] =true;
                               } else {
                                   console.log('Record not found for PAN:');
                               }
                               if(null!=value.processStatus&&value.panStatus==='E'){
                               vm.filteredRecords[recordIndex].panStatus='Y';}
                                var message = "File Data Updated Successfully";
                                Swal.fire("Success!", message ,"success");
                                //getDetails("S");
                           }, function(error) {
                               vm.loader = false;
                               handleErrorResponse(error);
                               $state.go('pan_validation_retrigger.inquiry_details');
                           })
                      }   });
                     }else{
                     Swal.fire("Warning!","Necessary field is Required", "warning");
                     $state.go('pan_validation_retrigger.inquiry_details');
                   }
              }
                                           /* Validate Submit */
          function submitValidateForm(value,model) {
              const needsDobUpdate = value.dobStatus === 'N' && value.newDob;
              const needsNameUpdate = value.nameStatus === 'N' && value.newName;
              const needsPanUpdate = value.panStatus === 'N' && value.newPan;
              const noDobUpdate = value.dobStatus === 'Y';
              const noNameUpdate = value.nameStatus === 'Y';
              const noPanUpdate = value.panStatus === 'Y' || value.panStatus === 'E';
              if(
                  (needsDobUpdate && needsNameUpdate && needsPanUpdate) ||
                  (needsDobUpdate && needsNameUpdate && noPanUpdate) ||
                  (needsDobUpdate && noNameUpdate && noPanUpdate) ||
                  (needsNameUpdate &&noDobUpdate && noPanUpdate) ||
                  (needsPanUpdate && noDobUpdate && noNameUpdate) ||
                  (needsPanUpdate&&needsNameUpdate&&noDobUpdate)
              ){
//                                           Swal.fire({
//                                              title: "Are you sure?",
//                                              text: "Do you want to Really Validate this record?",
//                                              type: "warning",
//                                              showCancelButton: true,
//                                              cancelButtonClass: "btn-cancel",
//                                              confirmButtonClass: "btn-primary",
//                                              confirmButtonText: "Yes",
//                                              closeOnConfirm: false
//                                          },
//                                          function() {



                                Swal.fire({
                                  title: "Are you sure?",
                                  text: "Do you want to Really validate this record?",
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
                                                  'name': value.name,
                                                  'nameStatus' : value.nameStatus,
                                                  'newName' : value.newName,
                                                  'dob' : value.dob,
                                                  'dobStatus' : value.dobStatus,
                                                  'newDob' :  $filter('date')(value.newDob,'dd/MM/yyyy'),
                                                  'oldPan' : value.oldPan,
                                                  'panStatus' : value.panStatus,
                                                  'newPan' : value.newPan,
                                                  'processStatus' : value.processStatus
                                              };
                                              panValidationRetriggerService.submitValidateForm(requestData).then(function(response) {
                                                  vm.loader = false;
                                                  vm.obj = response.data.result;
                                                  vm.filteredRecords=model;
                                                  if(null!=vm.obj&&vm.obj.panStatus==='E'&&vm.obj.nameStatus==='Y'&&vm.obj.dobStatus==='Y'&&vm.obj.processStatus==='Y'){
                                                  const recordIndex = vm.filteredRecords.findIndex(record => record.oldPan === vm.obj.panNumber);
                                                  if (recordIndex !== -1) {
                                                      vm.filteredRecords[recordIndex].panStatus = vm.obj.panStatus;
                                                      vm.filteredRecords[recordIndex].nameStatus = vm.obj.nameStatus;
                                                      vm.filteredRecords[recordIndex].dobStatus = vm.obj.dobStatus;
                                                      vm.filteredRecords[recordIndex].processStatus = vm.obj.processStatus;
                                                      vm.filteredRecords[recordIndex].seedingStatus = vm.obj.seedingStatus;
                                                      vm.filteredRecords[recordIndex].seedingStatusDescription = vm.obj.seedingStatusDescription;
                                                    } else {
                                                      var message ="Record not found for PAN";
                                                      Swal.fire("Warning!", message ,"warning");
                                                    }
                                                  var message = "File Data Validated Successfully";
                                                  Swal.fire("Success!", message ,"success");
                                                  $state.go('pan_validation_retrigger.inquiry_details');
                                                  }else{
                                                  const recordIndex = vm.filteredRecords.findIndex(record => record.oldPan === vm.obj.panNumber);
                                                  if (recordIndex !== -1) {
                                                      vm.filteredRecords[recordIndex].panStatus = vm.obj.panStatus;
                                                      vm.filteredRecords[recordIndex].nameStatus = vm.obj.nameStatus;
                                                      vm.filteredRecords[recordIndex].dobStatus = vm.obj.dobStatus;
                                                      vm.filteredRecords[recordIndex].processStatus = vm.obj.processStatus;
                                                      vm.filteredRecords[recordIndex].seedingStatus = vm.obj.seedingStatus;
                                                      vm.filteredRecords[recordIndex].seedingStatusDescription = vm.obj.seedingStatusDescription;
                                                    }
                                                  var msg = "Pan Validation Failure \n" +
                                                            "PanStatus: " + vm.obj.panStatus + "\n" +
                                                            "NameStatus: " + vm.obj.nameStatus + "\n" +
                                                            "DobStatus: " + vm.obj.dobStatus + "\n" +
                                                            "ProcessStatus: " + vm.obj.processStatus;
                                                  Swal.fire("Warning!",msg, 'warning');
                                                  $state.go('pan_validation_retrigger.inquiry_details');
                                                  }
                                              }, function(error) {
                                                  vm.loader = false;
                                                  handleErrorResponse(error);
                                                  $state.go('pan_validation_retrigger.inquiry_details');
                                              })
                                         } });
                                          }else{
                                            Swal.fire("Warning!","Necessary field is Required", "warning");
                                            $state.go('pan_validation_retrigger.inquiry_details');
                                          }
                                      }





                                                    vm.textableDatePicker=textableDatePicker;
                                                                     vm.autoslashing=autoslashing;
                                                                     vm.futureNotAllowed=futureNotAllowed;
vm.textableDatePickerYear = textableDatePickerYear;
 function textableDatePickerYear(id, item, index) {
                 var element = document.getElementById(id);
                     if (!element) {
                         return false;
                     }
                     var dateValue = element.value;
                     // If empty, keep AngularJS required validation
                     if (!dateValue) {
                         item.newDob = null;
                         return false;
                     }
                     commonService.textableDatePicker(id, dateValue);
                     dateValue = element.value;
                     var datePattern =/^(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[0-2])\/(17|18|19|20|21)\d{2}$/;
                     if (!datePattern.test(dateValue)) {
                         item.newDob = null;
                         element.value = '';
                         Swal.fire("Error!","Please enter a valid date in DD/MM/YYYY format.","error");
                         return false;
                     }
                     return true;
                 }
                                                             function textableDatePicker(id,valueDate)
                                                             {
                                                              commonService.textableDatePicker(id,valueDate);
                                                             }


                                                             function autoslashing(id){
                                                             var input = document.getElementById(id);
                                                                 if (!input) return;
                                                                 var val = input.value;
                                                                 if (val.length === 2 && val.indexOf('/') === -1) {
                                                                     input.value = val + '/';
                                                                 } else if (val.length === 5 && val.lastIndexOf('/') === 2) {
                                                                     input.value = val + '/';
                                                                 }
                                                              //commonService.autoslashing(id);
                                                             }



                                                             function futureNotAllowed(id,valueDate){
                                                              if (!valueDate) return;
                                                            var today = new Date();
                                                            // Convert string (dd/MM/yyyy) → Date
                                                            if (typeof valueDate === "string") {
                                                                var parts = valueDate.split("/");
                                                                if (parts.length === 3) {
                                                                    valueDate = new Date(parts[2], parts[1] - 1, parts[0]); // yyyy, mm-1, dd
                                                                }
                                                            }
                                                            // enteredDate is now a Date object
                                                            if (valueDate > today) {
                                                                vm.panStatusValidateDate = "";
                                                                swal("Error!", "Please don't enter future date", "error");
                                                            }
                                                             }
                   vm.futureNotAllowedIndex = function(field, rowObj, index) {
                       var dob = rowObj[field];
                       if (!dob) {
                           return false;
                       }
                       var birthDate;
                       // Parse DD/MM/YYYY explicitly
                       if (typeof dob === 'string') {
                           var parts = dob.split('/');
                           if (parts.length !== 3) {
                               return false;
                           }
                           var day = parseInt(parts[0], 10);
                           var month = parseInt(parts[1], 10);
                           var year = parseInt(parts[2], 10);
                           birthDate = new Date(year, month - 1, day);
                           // Reject invalid calendar dates
                           if (birthDate.getFullYear() !== year ||
                               birthDate.getMonth() !== month - 1 ||
                               birthDate.getDate() !== day) {
                               rowObj[field] = null;
                               Swal.fire("Error!","Please enter a valid date in DD/MM/YYYY format.","error");
                               return false;
                           }
                       } else {
                           birthDate = new Date(dob);
                       }
                       var today = new Date();
                       today.setHours(0, 0, 0, 0);
                       birthDate.setHours(0, 0, 0, 0);
                       // Prevent future dates
                       if (birthDate > today) {
                           rowObj[field] = null;
                           var element = document.getElementById('dobInput' + index);
                           if (element) {
                               element.value = '';
                           }
                           Swal.fire( "Error!", "Please don't enter a future date.","error");
                           return false;
                       }
                       return true;
                   };
	}
})();