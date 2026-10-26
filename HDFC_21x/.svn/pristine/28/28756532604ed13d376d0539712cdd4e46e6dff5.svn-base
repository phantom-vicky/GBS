(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'rbiBondCentralizedTrialRunController',
			rbiBondCentralizedTrialRunController);

	rbiBondCentralizedTrialRunController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','rbiBondCentralizedTrialRunService', 'commonService' ];

	function rbiBondCentralizedTrialRunController($scope, $filter, $state,
			$stateParams, $location, $rootScope, rbiBondCentralizedTrialRunService,
			commonService) {

		var vm = this;
		vm.moduleName = 'RBI Bond';
//        vm.moduleId = '0012';
        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
        vm.getRemarksList = getRemarksList;
		vm.customerDetailsList = [];
		vm.accountDetailsList = [];
		vm.extensionDetailsList = [];
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.getTdsDeductionDetail = getTdsDeductionDetail;
        vm.callTrialRunProcess = callTrialRunProcess;
         vm.uncheckedItems = [];
         vm.selectedAccounts = [];
         vm.otherAccounts = [];
        //pagination start
vm.records = [];
    vm.filteredRecords = [];
    vm.filteredRecord = [];
    vm.currentPage = 1;
    vm.maxPagesToShow = 10;
    vm.searchText = vm.searchText || "";
    vm.loading = true;
    vm.showExportOptions = false;
    vm.itemsPerPage = "10";
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
            vm.getTdsDeductionDetail();
            }
            vm.pageSize = 10;
            vm.pageNo = 1;
                    function goPreviousPage() {
                     vm.pageNo = parseInt(vm.pageNo) - 1;
                     vm.getTdsDeductionDetail();
                     }
                     function goNextPage() {
//                     Swal.fire({
//                        title: "Are you sure?",
//                        text: "Reject these records and proceed?",
//                        type: "warning",
//                        showCancelButton: true,
//                        cancelButtonClass: "btn-cancel",
//                        confirmButtonClass: "btn-primary",
//                        confirmButtonText: "Yes",
//                        closeOnConfirm: true
//                    },function(){


                    Swal.fire({
                      title: "Are you sure?",
                     text: "Reject these records and proceed?",
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
                     vm.pageNo = parseInt(vm.pageNo) + 1;
                     vm.getTdsDeductionDetail();
                   }  });
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
// pagination end

        vm.currentTime = new Date();
        vm.currentyear = vm.currentTime.getFullYear();
        vm.financeyear= vm.currentyear;

        vm.currentYear = new Date();
        vm.getModuleName = getModuleName;
        vm.getTorchDetails = getTorchDetails;
        vm.getTorchDetailsVerify = getTorchDetailsVerify;
        vm.getTrialRunProcess = getTrialRunProcess;
//        vm.optionToggled = optionToggled;
        vm.selectInputVal = selectInputVal;
        vm.download = download;

        vm.selectedCount = 0
        vm.isAllSelected = [];
        vm.getUnverifiedBlaList = getUnverifiedBlaList;
        vm.getUnverifiedDSubsList = getUnverifiedDSubsList;
        vm.getUnverifiedDetailsPage = getUnverifiedDetailsPage;

        vm.interestForHalfYear = '';
        vm.getUnverifiedDetails = getUnverifiedDetails;


        vm.getHalfYearInterestDetails = getHalfYearInterestDetails;
       // getUnverifiedDetails();

         if ($state.current.name == 'rbi_bond_centralized_trial_run.add_detail') {

                if($stateParams.obj){

                    vm.year = $stateParams.obj.year;
                    vm.interestForHalfYear = $stateParams.obj.interestForHalfYear;
                    vm.schemeCode = $stateParams.obj.schemeCode;
                    vm.schemeName = $stateParams.obj.schemeName;
                        getUnverifiedDetails();
                        vm.getUnverifiedDetailsPage();

            }else {
                $state.go('rbi_bond_centralized_trial_run.add');
            }
        }
 if ($state.current.name == 'rbi_bond_centralized_trial_run.verify_detail') {

                if($stateParams.obj){

                    vm.year = $stateParams.obj.year;
                    vm.interestForHalfYear = $stateParams.obj.interestForHalfYear;
                    vm.schemeCode = $stateParams.obj.schemeCode;
                    vm.schemeName = $stateParams.obj.schemeName;
                             getTdsDeductionDetail();

            }else {
                $state.go('rbi_bond_centralized_trial_run.verify');
            }
        }

            function getRemarksList() {
                            vm.referenceId = '055';
                            var requestData = { 'referenceId': vm.referenceId };

                            rbiBondCentralizedTrialRunService.getRemarksList(requestData).then(function(response) {
                                vm.remarksList = response.data.result || []; // Ensure it's an array
                            }, function(error) {
                                handleErrorResponse(error);
                            });
                        }

        function getHalfYearInterestDetails(){
            var requestData = {};

            rbiBondCentralizedTrialRunService.getValidateDetails(requestData).then(function(response){

            },function(error){
                handleErrorResponse(error);
            })
        }

  function configDataTable(title) {
                    vm.getTitleDescription = {
                        title: title,
                        solId: $rootScope.globals.currentUser.solId,
                        solDesc: $rootScope.globals.currentUser.solDesc
                    }
                    vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
                }






/**
 * Function to handle the delete submission process.
 */
 vm.deleteSubmit=deleteSubmit;
function deleteSubmit() {
    vm.updateSelection(); // Update the selected and unselected accounts
    vm.loader = true;

    // Prepare the request payload
    var requestData = {
        schemeCode: vm.schemeCode,
        interestForHalfYear: vm.interestForHalfYear,
        year: vm.financeyear,
        uncheckedList: vm.selectedAccounts,
    };

    // Call the service to submit the delete request
    rbiBondCentralizedTrialRunService.submitDeleteForm(requestData)
        .then(function (response) {
            vm.loader = false;
            Swal.fire("Rejected!", "Bond Centralized Trial Run Rejected Successfully", "success");
                       location.href = '#!/rbi_bond_centralized_trial_run/add';

                               const backdrop = document.querySelector('.modal-backdrop');
if (backdrop) {
    backdrop.remove(); // Removes the backdrop from the DOM
}

        }, function (error) {
                 vm.loader = false;
                 handleErrorResponse(error);
                 const backdrop = document.querySelector('.modal-backdrop');
                 if (backdrop) {
                   backdrop.parentNode.removeChild(backdrop);
                   document.body.classList.remove('modal-open');
                 }
                 location.href = '#!/rbi_bond_centralized_trial_run/add';
               });

}

                 function getTdsDeductionDetail() {
                                      vm.loader = true;

                                      vm.totalInterestRupees = 0;
                                      vm.totalTdsAmount = 0;
                                      vm.totalNetInterestAmount = 0;
                                      var requestData = {
                                        'schemeCode':vm.schemeCode,
                                        'schemeName' :vm.schemeName,
                                        'interestForHalfYear' : vm.interestForHalfYear,
                                        'year': vm.financeyear,
                                        'pageSize' : vm.pageSize,
                                        'pageNo' : vm.pageNo,
                                        'searchValue' : vm.searchValue,
                                        'sortOrder' : vm.sortOrder,
                                        'sortField' : vm.sortField,
                                        'checkedList':vm.selectedAccounts,
                                        'uncheckedList':vm.otherAccounts
                                      };

                                      rbiBondCentralizedTrialRunService.getDetails(requestData)
                                          .then(
                                              function(response) {
                                                  vm.loader = false;
 // Ensure every account is selected by default
                if (Array.isArray(response.data.result.gstAckFileDownloadDTOList)) {
                           vm.selectedAccounts = [];
                           vm.otherAccounts = [];
                    vm.tdsDeductionList = response.data.result.gstAckFileDownloadDTOList.map(function(x) {
                        x.selected = (x.verifyStatus === 'I');
                        if (x.selected) {
                            vm.selectedAccounts.push(x.branchRefNo); // 'I' means selected
                        } else {
                            vm.otherAccounts.push(x.branchRefNo);    // 'N' or others mean unselected
                        }
                        return x;
                    });
                } else {
                    console.log("Invalid response data.");
                }
                             vm.tdsDeductionList =response.data.result.gstAckFileDownloadDTOList;
                             vm.verifyDetail = response.data.result;
                              vm.records = response.data.result.gstAckFileDownloadDTOList;
                             vm.filteredRecords = response.data.result.gstAckFileDownloadDTOList;
                             vm.total = response.data.total;
                             vm.maxPages = Math.ceil(response.data.total / vm.pageSize);
                             vm.paginationControls();
                          },
                          function(error) {
                           vm.loader = false;
                           if ((error.data.message == "No Data Found" || error.data.message == "NDF") && vm.searchValue) {
                             vm.noDataFoundInSearch = true;
                           } else if (error.data.message == "No Data Found" || error.data.message == "NDF") {
                             vm.noDataFound = error.data.message;
                           }else{
                             handleErrorResponse(error);
                             $state.go('rbi_bond_centralized_trial_run.verify');
                             }
                          })
                    }

       /**
        * Function to update the selection lists based on user input.
        * Separates the selected and unselected accounts.
        */
       vm.updateSelection = function () {
           vm.selectedAccounts = [];
           vm.otherAccounts = [];
           // Validate if the list is defined and is an array
           if (Array.isArray(vm.tdsDeductionList)) {
               vm.tdsDeductionList.forEach(function (item) {
                   if (item.selected) {
                       vm.selectedAccounts.push(item.branchRefNo); // Add to selected accounts
                   } else {
                       vm.otherAccounts.push(item.branchRefNo); // Add to unselected accounts
                   }
               });
           } else {
               console.error("tdsDeductionList is not defined or not an array.");
           }
       };

      vm.updateSelection();

vm.toggleAllSelection = function() {
    if (vm.checkAll) {
        // Select all records
        vm.filteredRecords.forEach(function(record) {
            record.selected = true;
        });
    } else {
        // Deselect all records
        vm.filteredRecords.forEach(function(record) {
            record.selected = false;
        });
    }
    // Call updateSelection to handle the state after toggling
    vm.updateSelection();
};
                  function getTrialRunProcess() {
                      getUnverifiedDetails().then(function () {
                          console.log("Unverified flag value: ", vm.unverifiedFlag); // Debugging log

                          if (vm.unverifiedFlag === 'Y') {
                              Swal.fire("Warning!", "Unverified Records Exist. Please verify the records to continue the Trial Run", "warning");
                          } else {
                              callTrialRunProcess('0'); // Initial call with centralizedFlag as '0'
                          }
                      }).catch(function (error) {
                          // Handle any errors from getUnverifiedDetails
                          handleErrorResponse(error);
                          location.href = '#!/rbi_bond_centralized_trial_run/add';
                      });
                  }
                                          function callTrialRunProcess(centralizedFlag) {
                                              var requestData = {
                                                  'schemeCode': vm.schemeCode,
                                                  'interestForHalfYear': vm.interestForHalfYear,
                                                  'year': vm.financeyear,
                                                  'centralizedFlag': centralizedFlag
                                              };
                                              vm.loader = true;
                                              rbiBondCentralizedTrialRunService.getValidateDetails(requestData).then(function (response) {
                                                  vm.loader = false;
                                                  vm.result = response.data.result;
                                                  if (vm.result === 'Y') {
                                                      Swal.fire("Success!", "Interest Calculation Done Successfully", "success");
                                                      location.href = '#!/rbi_bond_centralized_trial_run/add';
                                                  } else if (vm.result === 'N') {
                                                            // Show popup for partial selection
//                                                            Swal.fire(
//                                                              {
//                                                                title: "ITR Records does not Exists",
//                                                                text: "You have not uploaded ITR File. Do you want to proceed?",
//                                                                type: "warning",
//                                                                showCancelButton: true,
//                                                                cancelButtonClass: "btn-cancel",
//                                                                confirmButtonClass: "btn-primary",
//                                                                confirmButtonText: "Yes",
//                                                                cancelButtonText: "No",
//                                                                closeOnConfirm: false,
//                                                                closeOnCancel: true,
//                                                              },
//                                                              function (isConfirm) {
//                                                                if (isConfirm) {



                                                        Swal.fire({
                                                          title: "Are you sure?",
                                                          text: "You have not uploaded ITR File. Do you want to proceed?",
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
                                                                  callTrialRunProcess('1');
                                                                } else {
                                                                  // User cancelled
                                                                  return;
                                                                }
                                                              }
                                                            );
                                                  }
                                              }).catch(function (error) {
                                                  vm.loader = false;
                                                  handleErrorResponse(error);
                                              });
                                          }

       function getUnverifiedDetails() {
           return new Promise(function(resolve, reject) {
               vm.loader = true;
               var requestData = {
                   'schemeCode': vm.schemeCode,
                   'interestForHalfYear': vm.interestForHalfYear,
                   'year': vm.financeyear,
               };
               rbiBondCentralizedTrialRunService.getTrialRunDetails(requestData).then(function(response) {
                   vm.loader = false;
                   vm.unverifiedBlaList = response.data.result.bondLedgerOut;
                   vm.unverifiedSubsList = response.data.result.bondSubsIdOut;

                   console.log("BLA List: ", vm.unverifiedBlaList); // Debugging log
                   console.log("Subs List: ", vm.unverifiedSubsList); // Debugging log

                   if (vm.unverifiedBlaList.length > 0 || vm.unverifiedSubsList.length > 0) {
                       vm.unverifiedFlag = "Y";
                       console.log("Unverified flag set to Y"); // Debugging log
                   } else {
                       vm.unverifiedFlag = "N";
                       console.log("Unverified flag set to N"); // Debugging log
                   }

                   if (vm.unverifiedBlaList.length > 0) {
                       vm.title = 'BOND Ledger Account Maintenance';
                   } else if (vm.unverifiedSubsList.length > 0) {
                       vm.title = 'BOND Subscription Workflow';
                   }
                   configDataTable(vm.title);
                   resolve();
               }, function(error) {
                   vm.loader = false;
                   reject(error);
               });
           });
       }

        function getUnverifiedDetailsPage(){
        vm.unverifiedDetails = true;
        vm.unverifiedBla = false;
        vm.unverifiedSubs = false;
        }

        function getUnverifiedBlaList(){
        vm.unverifiedDetails = false;
        vm.unverifiedBla = true;
        vm.unverifiedSubs = false;
        }

        function getUnverifiedDSubsList(){
        vm.unverifiedDetails = false;
        vm.unverifiedBla = false;
        vm.unverifiedSubs = true;
        }
vm.rejectReason=rejectReason;
function rejectReason() {
              angular.element('#permissionMappingDelete').modal('toggle');
              }
		   function getModuleName() {
                    vm.loader = true;
                    vm.referenceId = 'TAX';
                    var requestData = {
                        'referenceId': vm.referenceId
                    };
                    rbiBondCentralizedTrialRunService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                        vm.loader = false;
                        vm.schemeList = response.data.result;
                                                        angular.element('#branchIdSearcher').modal('toggle');

                    }, function (error) {
                        handleErrorResponse(error);
                        vm.loader = false;
                    });
                }

                 function getTorchDetails(flag){
                                    vm.loader = true;
                                    if(flag == 'scheme'){
                                    var requestData = {
                                        'operationType' : flag
                                    };
                                    }
                                    else{
                                    var requestData = {
                                        'schemeCode' : vm.schemeCode,
                                        'interestForHalfYear' : vm.interestForHalfYear,
                                        'operationType' : flag

                                    };
                                      }
                                    rbiBondCentralizedTrialRunService.getTorchDetails(requestData).then(function (response) {
                                        vm.loader = false;
                                         if(flag == "scheme"){
                                        vm.schemeList = response.data.result.schemeCodeOut;
                                        angular.element('#branchIdSearcher').modal('toggle');
                                        }
                                         if(flag == "interestHalfYear"){
                                          vm.interestHalfYearList = response.data.result.interestHalfYearTorchOut;
                                         }
                                    }, function (error) {
                                        handleErrorResponse(error);
                                        vm.loader = false;
                                    });
                                   }

 function getTorchDetailsVerify(flag){
                            vm.loader = true;
                            if(flag == 'scheme'){
                            var requestData = {
                                'operationType' : flag
                                };
                             }
                             else if(flag == 'interestHalfYear'){
                                var requestData = {
                                    'schemeCode' : vm.schemeCode,
                                    'interestForHalfYear' : vm.interestForHalfYear,
                                    'operationType' : flag

                                };
                             }
                                    rbiBondCentralizedTrialRunService.getTorchDetailsVerify(requestData).then(function (response) {
                                        vm.loader = false;
                                         if(flag == "scheme"){
                                        vm.schemeList = response.data.result.schemeCodeOut;
                                        angular.element('#branchIdSearcher').modal('toggle');
                                        }
                                         if(flag == "interestHalfYear"){
                                          vm.interestHalfYearList = response.data.result.interestHalfYearTorchOut;
                                         }
                                    }, function (error) {
                                        handleErrorResponse(error);
                                         location.href = '#!/rbi_bond_centralized_trial_run/action/verify';
                                        vm.loader = false;
                                    });
                                   }

function selectInputVal() {
 if (vm.selectedCount == '0' && vm.isAllSelected == '') {
    Swal.fire("Please Select At least One Bond Subscription Id")
    }
 }
 function download(schemeCode, year, interestForHalfYear,flag) {
           let requestData = {
               "schemeCode": schemeCode,
               "year": year,
               "interestForHalfYear": interestForHalfYear,
               "flag":flag
           };

           vm.loader = true;
           rbiBondCentralizedTrialRunService.download(requestData).then(
               function(response) {
                   vm.loader = false;
                   vm.message = response.data.message;
                  if (response.data.result.fileName) {
                        vm.fileData = response.data.result.fileData;
                        vm.fileName = response.data.result.fileName;
                        saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
                        Swal.fire("Downloaded", response.data.message, "success");
                   }
               },function(err) {
                   vm.loader = false;
                   handleErrorResponse(err);
               }
           );
       }
 vm.getDownload=getDownload;
 function getDownload(schemeCode, year, interestForHalfYear) {
           vm.loader = true;
          let requestData = {
              "schemeCode": schemeCode,
              "year": year,
              "interestForHalfYear": interestForHalfYear,
              "pageNo" : 1,
              "pageSize": vm.total,
              "searchValue":vm.searchValue,
              "flag":vm.selectedOption
          };
          rbiBondCentralizedTrialRunService.getDownload(requestData).then(
              function(response) {
                  vm.loader = false;
                   vm.filteredRecord = response.data.result;
                  if (vm.selectedOption === "UNMERGED" || vm.selectedOption === "MERGED") {
                    if (response.data.result.fileName) {
                       vm.fileData = response.data.result.fileData;
                       vm.fileName = response.data.result.fileName;
                       saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
                       Swal.fire("Downloaded", response.data.message, "success");
                  }
                }
                   vm.selectedOption = "";
                   },function(error) {
                    vm.loader = false;
                    vm.selectedOption = "";
                 if((error.data.message == "No Data Found") || (error.data.message == "No data to download") || (error.data.message == "NDF")) {
                     vm.noDataFound = error.data.message;
                     Swal.fire("Warning", vm.noDataFound, "warning");
                     $state.go("rbi_bond_centralized_trial_run.verify_detail");
                   }else {
                   handleErrorResponse(error);
                   $state.go("rbi_bond_centralized_trial_run.verify");
                   }
               }
           );
       }
vm.verifySubmit=verifySubmit;
                   function verifySubmit() {
//                   			Swal.fire({
//                   					title: "Are you sure?",
//                   					text: "Do you want to Verify this record?",
//                   					type: "warning",
//                   					showCancelButton: true,
//                   					cancelButtonClass: "btn-cancel",
//                   					confirmButtonClass: "btn-primary",
//                   					confirmButtonText: "Yes",
//                   					closeOnConfirm: false
//                   				},function () {


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
                   					vm.loader = true
                   					let requestData = {
                   					   'schemeCode':vm.schemeCode,
                                       'interestForHalfYear' : vm.interestForHalfYear,
                                       'year': vm.financeyear,
                                       'checkedList':vm.selectedAccounts
                   					}
                   					rbiBondCentralizedTrialRunService.verifySubmit(requestData).then(function (response) {
                   						vm.loader = false;
                                        Swal.fire("Verified!","Bond Centralized Trial Run Verified Successfully","success");
                   						$state.go('rbi_bond_centralized_trial_run.verify')
                   					}, function (error) {
                   						vm.loader = false;
                   						handleErrorResponse(error);
                   						$state.go('rbi_bond_centralized_trial_run.verify')
                   					})
                   			}	})
                   		}
	}
})();