(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'rbiBondTrialRunForRedemptionController',
			rbiBondTrialRunForRedemptionController);

	rbiBondTrialRunForRedemptionController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','rbiBondTrialRunForRedemptionService', 'commonService' ];

	function rbiBondTrialRunForRedemptionController($scope, $filter, $state,
			$stateParams, $location, $rootScope, rbiBondTrialRunForRedemptionService,
			commonService) {

		var vm = this;
		vm.moduleName = 'RBI Bond';
        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
		vm.customerDetailsList = [];
		vm.accountDetailsList = [];
		vm.extensionDetailsList = [];
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.getTdsDeductionDetail = getTdsDeductionDetail;
        vm.getblaNo = getblaNo;
        vm.getRemarksList =  getRemarksList;

vm.goPreviousPage = goPreviousPage;
                 vm.goNextPage = goNextPage;
                 vm.paginationControls = paginationControls;
                 vm.rejectReason = rejectReason;
                 vm.deleteSubmit = deleteSubmit;
                 vm.verifySubmit = verifySubmit;
                 vm.showNext = false;
                 vm.showPrevious = false;
        vm.getPantorch = getPantorch;

                 $scope.sortReverse = false;
                 $scope.sort = function(key){
                 $scope.sortKey = key;
                 $scope.sortReverse = ($scope.sortKey == key) ? !$scope.sortReverse : $scope.sortReverse;
                 vm.sortOrder = $scope.sortReverse ?  'DESC' :  'ASC';
                 vm.sortField = key;
                 vm.getTorchDetailsVerify();
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

        vm.currentTime = new Date()
        vm.currentyear = vm.currentTime.getFullYear();
        vm.financeyear= vm.currentyear;

        vm.currentYear = new Date();
        vm.getModuleName = getModuleName;
        vm.getTorchDetails = getTorchDetails;
        vm.getTorchDetailsVerify = getTorchDetailsVerify;
        vm.getTrialRunProcess = getTrialRunProcess;
        vm.selectInputVal = selectInputVal;
                vm.download = download;

        vm.selectedCount = 0
        vm.isAllSelected = [];
        vm.getUnverifiedBlaList = getUnverifiedBlaList;
        vm.getUnverifiedDSubsList = getUnverifiedDSubsList;
        vm.getUnverifiedDetailsPage = getUnverifiedDetailsPage;
        vm.updateSelection = updateSelection;
        vm.interestForHalfYear = '';
        vm.getUnverifiedDetails = getUnverifiedDetails;


        vm.getHalfYearInterestDetails = getHalfYearInterestDetails;

         if ($state.current.name == 'rbi_bond_trial_run_for_redemption.add_detail') {

                if($stateParams.obj){

                    vm.year = $stateParams.obj.year;
                    vm.interestForHalfYear = $stateParams.obj.interestForHalfYear;
                    vm.schemeCode = $stateParams.obj.schemeCode;
                    vm.schemeName = $stateParams.obj.schemeName;
                        getUnverifiedDetails();
                        vm.getUnverifiedDetailsPage();

            }else {
                $state.go('rbi_bond_trial_run_for_redemption.add');
            }
        }
 if ($state.current.name == 'rbi_bond_trial_run_for_redemption.verify_detail') {

                if($stateParams.obj){

                     vm.trailRunType = $stateParams.obj.trailRunType;
                    vm.schemeCode = $stateParams.obj.schemeCode;
                    vm.schemeName = $stateParams.obj.schemeName;
                             getTorchDetailsVerify('submit');

            }else {
                $state.go('rbi_bond_trial_run_for_redemption.verify');
            }
        }

        function getHalfYearInterestDetails(){
            var requestData = {};

            rbiBondTrialRunForRedemptionService.getValidateDetails(requestData).then(function(response){

            },function(error){
                handleErrorResponse(error);
            })
        }




                  function getRemarksList() {
                                    vm.referenceId = '056';
                                    var requestData = { 'referenceId': vm.referenceId };

                                    rbiBondTrialRunForRedemptionService.getRemarksList(requestData).then(function(response) {
                                        vm.remarksList = response.data.result || []; // Ensure it's an array
                                    }, function(error) {
                                        handleErrorResponse(error);
                                    });
                              }

  function configDataTable(title) {
                    vm.getTitleDescription = {
                        title: title,
                        solId: $rootScope.globals.currentUser.solId,
                        solDesc: $rootScope.globals.currentUser.solDesc
                    }
                    vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
                }

                function goPreviousPage() {

                 vm.pageNo = parseInt(vm.pageNo) - 1;
                 vm.getTorchDetailsVerify();
                 }

                 function goNextPage() {

                 vm.pageNo = parseInt(vm.pageNo) + 1;
                 vm.getTorchDetailsVerify();
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




function deleteSubmit() {
                   					vm.loader = true
                   					var requestData = {
                   						                         'schemeCode': vm.schemeCode,
                                             'trailRunType':vm.trailRunType,
                                       'uncheckedList': vm.selectedAccounts,
                                       'rejectionRemarks': vm.remarks
                   					}
                   					rbiBondTrialRunForRedemptionService.submitDeleteForm(requestData).then(function (response) {
                   						vm.loader = false;
                   						   const backdrop = document.querySelector('.modal-backdrop');
                                            if (backdrop) {
                                              backdrop.parentNode.removeChild(backdrop);
                                              document.body.classList.remove('modal-open');
                                            }
                                           Swal.fire("Deleted!","Trail Run for Redemption Deleted Successfully","success");
                   						$state.go('rbi_bond_trial_run_for_redemption.verify')
                   					}, function (error) {
                   						vm.loader = false;
                   						handleErrorResponse(error);
                   						   const backdrop = document.querySelector('.modal-backdrop');
                                            if (backdrop) {
                                              backdrop.parentNode.removeChild(backdrop);
                                              document.body.classList.remove('modal-open');
                                            }
                   						$state.go('rbi_bond_trial_run_for_redemption.verify')
                   					})

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



                                      };

                                      rbiBondTrialRunForRedemptionService.getDetails(requestData)
                                          .then(
                                              function(response) {
                                                  vm.loader = false;
 // Ensure every account is selected by default
                if (Array.isArray(response.data.result.gstAckFileDownloadDTOList)) {
                    vm.tdsDeductionList  = response.data.result.gstAckFileDownloadDTOList.map(function(x) {
                        x.selected = true; // Default the checkbox to be checked
                        vm.checkAll = true;
                        return x;
                    });
                } else {
                    console.warn("Invalid response data.");
                }
                                                 vm.tdsDeductionList =response.data.result.gstAckFileDownloadDTOList;
                                                  vm.records = response.data.result.gstAckFileDownloadDTOList;
                                                                     vm.filteredRecords = response.data.result.gstAckFileDownloadDTOList;
                                                                     console.log(vm.filteredRecords);

                                                                     vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
                                                                     console.log("totalPages: "+vm.totalPages);
                                                                     updatePagination();
                                                                     //BASHA CODE END
                                              },
                                              function(error) {
                                               vm.loader = false;
                                               handleErrorResponse(error);
                                               $state.go('rbi_bond_trial_run_for_redemption.verify');

                                              }
                                          )
                                  }

                              // Create an array to store the unchecked items separately
                                vm.uncheckedItems = [];
         vm.selectedAccounts = [];
         vm.otherAccounts = [];
          function updateSelection(){
                 vm.selectedAccounts = [];
                 vm.otherAccounts = [];

                 // Separate the selected and unselected accounts
                 if (Array.isArray(vm.schemeList)) {
                    vm.schemeList.forEach(function(x) {
                   if (x.selected) {
                     vm.selectedAccounts.push(x.branchRefNo);
                   } else {
                     vm.otherAccounts.push(x.branchRefNo);
                   }
                 });
                  } else {
                     console.warn("tdsDeductionList is not defined or not an array.");
                   }
               };


vm.toggleAllSelection = function() {
    if (vm.checkAll) {
        // Select all records
        vm.schemeList.forEach(function(record) {
            record.selected = true;
        });
    } else {
        // Deselect all records
        vm.schemeList.forEach(function(record) {
            record.selected = false;
        });
    }
    // Call updateSelection to handle the state after toggling
    vm.updateSelection();
};




                                  //BASHA CODE START
                                          vm.setPage = function(page) {
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
                                              vm.filteredRecords = vm.schemeList.filter(function(record) {
                                                  return (
                                                      record.branchRefNo.toLowerCase().includes(vm.searchText.toLowerCase()) ||
                                                      String(record.blaNo).includes(vm.searchText) ||
                                                      String(record.tdsAmount).includes(vm.searchText)
                                                  );
                                              });
                                              vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
                                              vm.setPage(1);
                                          };

                                          vm.updatePageSize = function() {
                                              vm.totalPages = Math.ceil(vm.schemeList.length / vm.pageSize);
                                              console.log("totalPages: "+vm.totalPages);
                                              vm.setPage(1);
                                          };

       function getTrialRunProcess() {

                   let requestData = {
                       'schemeCode': vm.schemeCode,
                       'bondHolderName': vm.bondHolderName,
                       'panNum': vm.panNum,
                       'blaNo': vm.blaNo,
                       'fromDate': $filter('date')(vm.fromDate, "dd-MMM-yyyy"),
                       'toDate': $filter('date')(vm.toDate, "dd-MMM-yyyy")
                   };
                   rbiBondTrialRunForRedemptionService.getValidateDetails(requestData).then(function(response) {
                       vm.loader = false;
                       Swal.fire("Success!", "Trail Run for Redemption Done Successfully", "success");
                       location.href = '#!/rbi_bond_trial_run_for_redemption/add';
                   }, function(error) {
                       vm.loader = false;
                       handleErrorResponse(error);
                   });


       }

       function getUnverifiedDetails() {
           return new Promise(function(resolve, reject) {
               vm.loader = true;
               var requestData = {
                  'schemeCode': vm.schemeCode,
                                        'bondHolderName': vm.bondHolderName,
                                        'panNum': vm.panNum,
                                        'blaNo': vm.blaNo,
                                        'fromDate': vm.fromDate,
                                        'toDate': vm.toDate
               };
               rbiBondTrialRunForRedemptionService.getTrialRunDetails(requestData).then(function(response) {
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
                    const errorMessage = error && error.data && error.data.message
                                                ? error.data.message
                                                : "An unexpected error occurred.";
                                        if(null!=errorMessage && errorMessage.startsWith("Invalid")){
                                        Swal.fire("Warning!", errorMessage , "warning");
                                        $state.go("rbi_bond_trial_run_for_redemption.add");
                                        }else{
                                        handleErrorResponse(error);
                                        $state.go("rbi_bond_trial_run_for_redemption.add");
                                        }
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

function rejectReason() {
              angular.element('#permissionMappingDelete').modal('toggle');
              }

		   function getModuleName() {
                    vm.loader = true;
                    vm.referenceId = 'TAX';
                    var requestData = {
                        'referenceId': vm.referenceId
                    };
                    rbiBondTrialRunForRedemptionService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                        vm.loader = false;
                        vm.schemeList = response.data.result;
                                                        angular.element('#branchIdSearcher').modal('toggle');

                    }, function (error) {
                        vm.loader = false;
                        handleErrorResponse(error);
                    });
                }

                 function getTorchDetails(flag){
                                    vm.loader = true;

                                    if(flag == 'scheme'){
                                    var requestData = {

                                                                            'operationType' : flag,

                                                                        };
                                    }

                             else if(flag == 'submit'){
                                    var requestData = {
                                           'schemeCode': vm.schemeCode,
                                            'schemeName': vm.schemeName,
                                               'fromDate': $filter('date')(vm.fromDate, "dd-MMM-yyyy"),
                                             'toDate': $filter('date')(vm.toDate, "dd-MMM-yyyy"),
                                            'panNum': vm.panNum,
                                            'bondHolderName': vm.bondHolderName,
                                             'trailRunType':vm.trailRunType,
                                            'blaNo': vm.blaNo,
                                    };
                                      }
                                    rbiBondTrialRunForRedemptionService.getTorchDetails(requestData).then(function (response) {
                                        vm.loader = false;
                                         if(flag == "scheme"){
                                        vm.schemeList = response.data.result.schemeCodeOut;
                                        angular.element('#branchIdSearcher').modal('toggle');
                                        }
                                        if(flag == "submit"){
                                                                                                      Swal.fire("Success!", "Trail Run for Redemption Done Successfully", "success");
                                                                                                      location.href = '#!/rbi_bond_trial_run_for_redemption/add';                                         }



                                    }, function (error) {
//                                        handleErrorResponse(error);
                                        vm.loader = false;
                                         const errorMessage = error && error.data && error.data.message
                                                                                                    ? error.data.message
                                                                                                    : "An unexpected error occurred.";
                                                                                            if(null!=errorMessage && errorMessage.startsWith("Invalid")){
                                                                                            Swal.fire("Warning!", errorMessage , "warning");
                                                                                            $state.go("rbi_bond_trial_run_for_redemption.add");
                                                                                            }else{
                                                                                            handleErrorResponse(error);
                                                                                            $state.go("rbi_bond_trial_run_for_redemption.add");
                                                                                            }
                                    });
                                   }

 function getTorchDetailsVerify(flag){
                                    vm.loader = true;

                                    if(flag == 'scheme'){
                            var requestData = {

                                    'operationType' : flag

                                };
                                    }
                             else if(flag == 'submit'){
                                    var requestData = {
                                           'schemeCode': vm.schemeCode,
                                            'schemeName': vm.schemeName,
                                    'operationType' : flag,

                                             'trailRunType':vm.trailRunType

                                    };
                                      }
                                    rbiBondTrialRunForRedemptionService.getTorchDetailsVerify(requestData).then(function (response) {
                                        vm.loader = false;
                                         if(flag == "scheme"){
                                        vm.schemeList = response.data.result.schemeCodeOut;
                                        angular.element('#branchIdSearcher').modal('toggle');
                                        }
                                         if(flag == "submit"){
                                             vm.schemeList = response.data.result;
                                             vm.filteredRecords = response.data.result;
                                              if (Array.isArray(response.data.result)) {
                                                                vm.tdsDeductionList  = response.data.result.map(function(x) {
                                                                    x.selected = true; // Default the checkbox to be checked
                                                                    vm.checkAll = true;
                                                                    return x;
                                                                });
                                                                 vm.updateSelection();
                                                            } else {
                                                                console.warn("Invalid response data.");
                                                            }
                                                                    vm.totalRecords = response.data.result.length;
                                                                    vm.pageSize = 10;  // Set records per page
                                                                    vm.totalPages = Math.ceil(vm.totalRecords / vm.pageSize);

                                                                    // ✅ Generate Page Numbers Dynamically
                                                                    vm.pages = Array.from({ length: vm.totalPages }, (_, i) => i + 1);


                                                             vm.setPage(1);  // Start at first page
                                                                    vm.updatePagination();  // Refresh pagination
                                          }


                                    }, function (error) {
                                        vm.loader = false;
                                        handleErrorResponse(error);
                                        location.href = '#!/rbi_bond_trial_run_for_redemption/action/verify';
                                    });
                                   }


                vm.toggleAll = function(selectAll) {
                    angular.forEach(vm.schemeList, function(item) {
                        item.selected = selectAll;
                    });
                };

                vm.isAnyCheckboxSelected = function() {
                    return vm.schemeList.some(function(item) {
                        return item.selected;
                    });
                };
                        	function selectInputVal() {
                        	 if (vm.selectedCount == '0' && vm.isAllSelected == '') {
                        		Swal.fire("Please Select At least One Bond Subscription Id")
                        		}
                        	 }

vm.downloadVerify = downloadVerify ;
function download(schemeCode, blaNo, bondHolderName, panNum, fromDate, toDate,flag) {
    let requestData = {
        'schemeCode':schemeCode,
        'bondHolderName': bondHolderName,
        'panNum': panNum,
        'blaNo': blaNo,
        'fromDate': $filter('date')(fromDate, "dd-MMM-yyyy"),
        'toDate': $filter('date')(toDate, "dd-MMM-yyyy"),
        "flag":flag
    };

    vm.loader = true;
    rbiBondTrialRunForRedemptionService.download(requestData).then(
        function(response) {
            vm.loader = false;
            vm.message = response.data.result.message;
            vm.checkResult = response.data.result;
                 if (response.data.result.fileName) {
                        vm.fileData = response.data.result.fileData;
                        vm.fileName = response.data.result.fileName;
                        saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
                        Swal.fire("Downloaded", response.data.message, "success");
                   }
            // Extract DTO list from response
            /*let downloadResponseDTOList = response.data.result;

            // Prepare subHeaders for Excel
            let subHeaders = [
                "Sl.No", "Mode of Payment", "Branch Ref No", "Bla_no", "Ref No",
                "Appl_Name1", "Appl_Name2", "Appl_Name3", "Addr1", "Addr2",
                "Rec_Name", "Rec_Parent_Name", "Pin_Code", "Phone_no", "appl_status",
                "Investment Amt", "CFD", "Mat Date", "Account_No", "Subscription Count",
                "BankSortCode", "Bank_name", "Pan_No", "8% interest payout",
                "Non Cumulative Int 8%", "Cumulative Int 8%", "7.75% interest payout",
                "Non Cumulative Int 7.75%", "Cumulative Int 7.75%","FRSB interest payout",
                "Non Cumulative Int FRSB", "Cumulative Int FRSB", "Total",
                "In-operative PAN", "Non-filers", "Age", "Taxable Limit",
                "Waiver Document", "Waiver Document Ref No.", "RRB-TDS rate",
                "Threshold_Exceed_Amount", "Threshold_Exceed_TDS_Amount", "Exemption Rate %",
                "Exemption_Amount", "Exemption_TDS_Amount", "Surcharge %", "Edu Cess %",
                "Base Rate Amt", "Surcharge Amt", "Edu Cess Amt", "TDS Amount",
                "TDS Recovery Amount", "TDS Refund Amount", "Int_Rate", "Last Int Paid",
                "Pending Interest", "Current Half Yearly Interest", "Future Interest",
                "Gross Interest Amount", "Int Paid (Net Interest Amount)", "Principal Amount",
                "Total Amount to be paid", "Payment Date","Payout Of The Year"
            ];

            // Prepare data for the Excel file
            let data = [subHeaders];
            downloadResponseDTOList.forEach((item, index) => {
                let row = [
                    index + 1, item.modeOfPayment, item.branchRefNo, item.blaNo, item.refNo,
                    item.applName1, item.applName2, item.applName3, item.addr1, item.addr2,
                    item.recName, item.recParentName, item.pinCode, item.phoneNo, item.applStatus,
                    item.investmentAmt, item.cfd, item.matDate, item.accountNo, item.subscriptionCount,
                    item.bankSortCode, item.bankName, item.panNo, item.interestPayout8Percent,
                    item.nonCumulativeInt8, item.cumulativeInt8, item.interestPayout775Percent,
                    item.nonCumulativeInt775, item.cumulativeInt775,item.interestPayout805Percent,item.nonCumulativeInt805,item.cumulativeInt805, item.totalInterestPayout,
                    item.inOperativePan, item.nonFilers, item.age, item.taxableLimit,
                    item.waiverDocument, item.waiverDocumentRefNo, item.rrbTdsRate,
                    item.thresholdExceedAmount, item.thresholdExceedTdsAmount, item.exemptionRate,
                    item.exemptionAmount, item.exemptionTdsAmount, item.surchargePercent,
                    item.eduCessPercent, item.baseRateAmt, item.surchargeAmt, item.eduCessAmt,
                    item.tdsAmount, item.tdsRecoveryAmount, item.tdsRefundAmount, item.intRate,
                    item.lastIntPaid, item.pendingInterest, item.currentHalfYearlyInterest,
                    item.futureInterest, item.grossInterestAmount, item.intPaidNet,
                    item.principleAmount, item.totalAmountToBePaid, item.paymentDate,item.payoutOfTheyear
                ];


                data.push(row);
            });

            // Create a workbook and append the data
            let workbook = XLSX.utils.book_new();
            let worksheet = XLSX.utils.aoa_to_sheet(data);
            XLSX.utils.book_append_sheet(workbook, worksheet, "Trail run for Redemption");

            // Write the workbook and trigger download
            XLSX.writeFile(workbook, "Trail_Run_For_Redemption.xlsx");*/
        },
        function(err) {
    vm.loader = false;
     const errorMessage = err && err.data && err.data.message
        ? err.data.message
        : "An unexpected error occurred.";
        if(null!=errorMessage && errorMessage.startsWith("Invalid")){
        Swal.fire("Warning!", errorMessage , "warning");
        $state.go("rbi_bond_trial_run_for_redemption.add");
        }else{
        handleErrorResponse(err);
        $state.go("rbi_bond_trial_run_for_redemption.add");
        }
        }
    );
}
function downloadVerify(schemeCode,flag) {
    let requestData = {
        'schemeCode':vm.schemeCode,
        'trailRunType':'OM001',
         'operationType' : 'submit',
         'flag':flag
    };
    vm.loader = true;
    rbiBondTrialRunForRedemptionService.getTorchDetailsVerify(requestData).then(
        function(response) {
            vm.loader = false;
            vm.message = response.data.result.message;
            vm.checkResult = response.data.result;
                 if (response.data.result.fileName) {
                        vm.fileData = response.data.result.fileData;
                        vm.fileName = response.data.result.fileName;
                        saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
                        Swal.fire("Downloaded", response.data.message, "success");
                   }
           /* // Extract DTO list from response
            let downloadResponseDTOList = response.data.result;

            // Prepare subHeaders for Excel
            let subHeaders = [
                "Sl.No", "Mode of Payment", "Branch Ref No", "Bla_no", "Ref No",
                "Appl_Name1", "Appl_Name2", "Appl_Name3", "Addr1", "Addr2",
                "Rec_Name", "Rec_Parent_Name", "Pin_Code", "Phone_no", "appl_status",
                "Investment Amt", "CFD", "Mat Date", "Account_No", "Subscription Count",
                "BankSortCode", "Bank_name", "Pan_No", "8% interest payout",
                "Non Cumulative Int 8%", "Cumulative Int 8%", "7.75% interest payout",
                "Non Cumulative Int 7.75%", "Cumulative Int 7.75%","FRSB interest payout",
                "Non Cumulative Int FRSB", "Cumulative Int FRSB", "Total",
                "In-operative PAN", "Non-filers", "Age", "Taxable Limit",
                "Waiver Document", "Waiver Document Ref No.", "RRB-TDS rate",
                "Threshold_Exceed_Amount", "Threshold_Exceed_TDS_Amount", "Exemption Rate %",
                "Exemption_Amount", "Exemption_TDS_Amount", "Surcharge %", "Edu Cess %",
                "Base Rate Amt", "Surcharge Amt", "Edu Cess Amt", "TDS Amount",
                "TDS Recovery Amount", "TDS Refund Amount", "Int_Rate", "Last Int Paid",
                "Pending Interest", "Current Half Yearly Interest", "Future Interest",
                "Gross Interest Amount", "Int Paid (Net Interest Amount)", "Principal Amount",
                "Total Amount to be paid", "Payment Date","Total Payout Of The Year"
            ];

            // Prepare data for the Excel file
            let data = [subHeaders];
            downloadResponseDTOList.forEach((item, index) => {
                let row = [
                    index + 1, item.modeOfPayment, item.branchRefNo, item.blaNo, item.refNo,
                    item.applName1, item.applName2, item.applName3, item.addr1, item.addr2,
                    item.recName, item.recParentName, item.pinCode, item.phoneNo, item.applStatus,
                    item.investmentAmt, item.cfd, item.matDate, item.accountNo, item.subscriptionCount,
                    item.bankSortCode, item.bankName, item.panNo, item.interestPayout8Percent,
                    item.nonCumulativeInt8, item.cumulativeInt8, item.interestPayout775Percent,
                    item.nonCumulativeInt775, item.cumulativeInt775,item.interestPayout85Percent,item.nonCumulativeInt85,item.cumulativeInt85, item.totalInterestPayout,
                    item.inOperativePan, item.nonFilers, item.age, item.taxableLimit,
                    item.waiverDocument, item.waiverDocumentRefNo, item.rrbTdsRate,
                    item.thresholdExceedAmount, item.thresholdExceedTdsAmount, item.exemptionRate,
                    item.exemptionAmount, item.exemptionTdsAmount, item.surchargePercent,
                    item.eduCessPercent, item.baseRateAmt, item.surchargeAmt, item.eduCessAmt,
                    item.tdsAmount, item.tdsRecoveryAmount, item.tdsRefundAmount, item.intRate,
                    item.lastIntPaid, item.pendingInterest, item.currentHalfYearlyInterest,
                    item.futureInterest, item.grossInterestAmount, item.intPaidNet,
                    item.principalAmount, item.totalAmountToBePaid, item.paymentDate,item.totalPayoutOfTheyear
                ];


                data.push(row);
            });

            // Create a workbook and append the data
            let workbook = XLSX.utils.book_new();
            let worksheet = XLSX.utils.aoa_to_sheet(data);
            XLSX.utils.book_append_sheet(workbook, worksheet, "Trail run for Redemption");

            // Write the workbook and trigger download
            XLSX.writeFile(workbook, "Trail_Run_For_Redemption.xlsx");*/
        },
        function(err) {
            vm.loader = false;
            handleErrorResponse(err);
        }
    );
}


                 function verifySubmit() {
//                     Swal.fire({
//                         title: "Are you sure?",
//                         text: "Do you want to Verify this record?",
//                         type: "warning",
//                         showCancelButton: true,
//                         cancelButtonClass: "btn-cancel",
//                         confirmButtonClass: "btn-primary",
//                         confirmButtonText: "Yes",
//                         closeOnConfirm: false
//                     },
//                     function () {

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

                             // Prepare request data
                             let requestData = {
                                 'schemeCode' : vm.schemeCode,  // Ensure schemeCode is from vm context
                                 'trailRunType': vm.trailRunType,
                                 'uncheckedList' : vm.otherAccounts // Attach cleaned data
                             };

                             // Call the service
                             rbiBondTrialRunForRedemptionService.verifySubmit(requestData).then(function (response) {
                                 vm.loader = false;
                                 Swal.fire("Verified!", "Trail Run for Redemption Verified Successfully", "success");
                                 $state.go('rbi_bond_trial_run_for_redemption.verify');
                             }, function (error) {
                                 vm.loader = false;
                                 handleErrorResponse(error);
                                 $state.go('rbi_bond_trial_run_for_redemption.verify');
                             });

                   }  });
                 }



               function getPantorch(){
                                        vm.loader = true;

                                    let requestData = {
                                   'schemeCode' : vm.schemeCode,
                                    'fromDate': $filter('date')(vm.fromDate, "dd-MMM-yyyy"),
                                   'toDate': $filter('date')(vm.toDate, "dd-MMM-yyyy"),
                                   'bondHolderName':vm.bondHolderName,
                                   'panNum' : vm.panNum
                                       }
                                rbiBondTrialRunForRedemptionService.getPanTorchDetails(requestData).then(function(response) {
                                                                                vm.loader = false;
                                                                                vm.panDataList = response.data.result;
                                                                                 angular.element('#pan1').modal('toggle');
                                                                            }, function(error) {
                                                                                vm.loader = false;
                                                                                handleErrorResponse(error);
                                                                            });


                                                    }

                       function getblaNo(){
                                                 vm.loader = true;

                                                                let requestData = {
                                                                    'schemeCode': vm.schemeCode,
                                                                    'schemeName': vm.schemeName,
                                                                       'fromDate': $filter('date')(vm.fromDate, "dd-MMM-yyyy"),
                                                                     'toDate': $filter('date')(vm.toDate, "dd-MMM-yyyy"),
                                                                    'panNum': vm.panNum,
                                                                    'bondHolderName': vm.bondHolderName,
                                                                    'blaNo': vm.blaNo,
                                                                };
                                                                 rbiBondTrialRunForRedemptionService.blaNo(requestData).then(function(response){
                                                                  vm.loader = false;
                                                                  vm.blaDataList = response.data.result.blaNumberTorchOut;
                                                                  angular.element('#blaModel').modal('toggle');
                                                                },function(error) {
                                                                    vm.loader = false;
                                                                    handleErrorResponse(error);
                                                                  });

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
                        commonService.autoslashing(id)
                       ;
                       }



                       function futureNotAllowed(id,valueDate)
                       {
                        commonService.futureNotAllowed(id,valueDate);
                       }



	}
})();