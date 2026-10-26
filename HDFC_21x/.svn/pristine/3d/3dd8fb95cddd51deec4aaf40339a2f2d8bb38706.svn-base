(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'rbiBondTrialRunEligibleAccountsController',
			rbiBondTrialRunEligibleAccountsController);

	rbiBondTrialRunEligibleAccountsController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','rbiBondTrialRunEligibleAccountsService', 'commonService' ];

	function rbiBondTrialRunEligibleAccountsController($scope, $filter, $state,
			$stateParams, $location, $rootScope, rbiBondTrialRunEligibleAccountsService,
			commonService) {

		var vm = this;
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.moduleName = 'RBI Bond';
//        vm.moduleId = '0012';
        vm.nextState = '';
        vm.cancel = cancel;
        vm.getTrialRunEligibleAccountsDetails = getTrialRunEligibleAccountsDetails;
        vm.getModuleName = getModuleName;
        vm.getTorchDetails = getTorchDetails;
        vm.getTrialRunEligibleAccountsFile = getTrialRunEligibleAccountsFile;

         vm.optionToggled = optionToggled;
         vm.selectInputVal = selectInputVal;

         vm.selectedCount = 0
         vm.isAllSelected = [];


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
         vm.getTrialRunEligibleAccountsDetails();
         }

         vm.pageSize = 5;
         vm.pageNo = 1;


        function goPreviousPage() {

         vm.pageNo = parseInt(vm.pageNo) - 1;
         vm.getTrialRunEligibleAccountsDetails();
         }

         function goNextPage() {

         vm.pageNo = parseInt(vm.pageNo) + 1;
         vm.getTrialRunEligibleAccountsDetails();
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

        function configDataTable() {
            vm.getTitleDescription = {
                title: 'Rbi Bond Interest Trial Run – Eligible Accounts',
                solId: $rootScope.globals.currentUser.solId,
                solDesc: $rootScope.globals.currentUser.solDesc
            }
            vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
        }

        if ($state.current.name == 'rbibond_centralized_trial_run_dc_report.inquiry') {
            vm.nextState = 'rbibond_centralized_trial_run_dc_report.inquiry_detail'
        } else if ($state.current.name == 'rbibond_centralized_trial_run_gu_report.inquiry') {
            vm.nextState = 'rbibond_centralized_trial_run_gu_report.inquiry_detail'
        } else if ($state.current.name == 'rbibond_centralized_trial_run_dc_report.inquiry_detail' || $state.current.name == 'rbibond_centralized_trial_run_gu_report.inquiry_detail') {

            if ($stateParams.obj) {
                  vm.branchId = $stateParams.obj.branchId;
                  vm.blaNo = $stateParams.obj.blaNo;
                  vm.schemeName = $stateParams.obj.schemeName;
                  vm.schemeCode = $stateParams.obj.schemeCode;
                  vm.bondSubsId = $stateParams.obj.bondSubsId;
                  vm.interestForHalfYear = $stateParams.obj.month;
                  vm.year = $stateParams.obj.year;

                  getTrialRunEligibleAccountsDetails();

            } else {
                if ($state.current.name == 'rbibond_centralized_trial_run_dc_report.inquiry_detail') {
                    $state.go('rbibond_centralized_trial_run_dc_report.inquiry');
                } else if ($state.current.name == 'rbibond_centralized_trial_run_gu_report.inquiry_detail') {
                    $state.go('rbibond_centralized_trial_run_gu_report.inquiry');
                }
            }
        }


            function getModuleName() {
                    vm.loader = true;
                    vm.referenceId = 'TAX';
                    var requestData = {
                        'referenceId': vm.referenceId
                    };
                    rbiBondTrialRunEligibleAccountsService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                        vm.loader = false;
                        vm.schemeList = response.data.result;
                    }, function (error) {
                        handleErrorResponse(error);
                        vm.loader = false;
                    });
                }


        function getTrialRunEligibleAccountsDetails() {
            vm.loader = true;

            vm.totalInterestRupees = 0;
            vm.totalTdsAmount = 0;
            vm.totalNetInterestAmount = 0;
            vm.totalArrearTdsAmount = 0;

            var requestData = {

            'moduleId' : vm.schemeCode,
            'branchId' : vm.branchId,
            'blaNo' : vm.blaNo,
            'bondSubsId' : vm.bondSubsId,
            'year' : vm.year,
            'interestForHalfYear' : vm.interestForHalfYear,
            'pageSize' : vm.pageSize,
            'pageNo' : vm.pageNo,
            'searchValue' : vm.searchValue,
            'sortOrder' : vm.sortOrder,
            'sortField' : vm.sortField

            };

            rbiBondTrialRunEligibleAccountsService.getTrialRunDetailsReport(requestData)
                .then(
                    function(response) {
                        vm.loader = false;

                        vm.trialRunDetailsList = response.data.result.trialRunEligibleList;
                        vm.pageDetails = response.data.result.PageDetails;

                        for(var i=0; i<vm.trialRunDetailsList.length;i++){
                        if(vm.trialRunDetailsList[i].modeOfInterestPayment=='MP001'){
                        vm.trialRunDetailsList[i].modeOfInterestPayment='CREDIT ACCOUNT';

                        }else if(vm.trialRunDetailsList[i].modeOfInterestPayment=='MP002'){
                        vm.trialRunDetailsList[i].modeOfInterestPayment='DD/PO';
                        }else if(vm.trialRunDetailsList[i].modeOfInterestPayment=='MP003'){
                        vm.trialRunDetailsList[i].modeOfInterestPayment='NEFT';
                        }else{
                        vm.trialRunDetailsList[i].modeOfInterestPayment='';
                        }
                        }


                        vm.paginationControls();


                        if(vm.trialRunDetailsList){
                        angular.forEach(vm.trialRunDetailsList, function(item) {

                        item.interestAmount == null ? item.interestAmount = 0 : item.interestAmount;
                        item.currentTdsAmount == null ? item.currentTdsAmount = 0 : item.currentTdsAmount;
                        item.netInterestAmount == null ? item.netInterestAmount = 0 : item.netInterestAmount;
                        item.arrearTdsAmount == null ? item.arrearTdsAmount = 0 : item.arrearTdsAmount;



                        vm.totalInterestRupees += parseInt(item.interestAmount);
                        vm.totalTdsAmount += parseInt(item.currentTdsAmount);
                        vm.totalNetInterestAmount += parseInt(item.netInterestAmount);
                        vm.totalArrearTdsAmount += parseInt(item.arrearTdsAmount);

                                                    item.interestPeriodFrom = new Date(item.interestPeriodFrom);
                                                    item.interestPeriodTo = new Date(item.interestPeriodTo);
                                                    item.accountOpenDate = new Date(item.accountOpenDate);
                                                    item.interestAmount = Number(item.interestAmount).toLocaleString('en-IN');
                                                    item.currentTdsAmount = Number(item.currentTdsAmount).toLocaleString('en-IN');
                                                    item.netInterestAmount = Number(item.netInterestAmount).toLocaleString('en-IN');
                                                    item.arrearTdsAmount = Number(item.arrearTdsAmount).toLocaleString('en-IN');

                                                })
                        }
                        vm.totalInterestRupees = Number(vm.totalInterestRupees).toLocaleString('en-IN');
                        vm.totalTdsAmount = Number(vm.totalTdsAmount).toLocaleString('en-IN');
                        vm.totalNetInterestAmount = Number(vm.totalNetInterestAmount).toLocaleString('en-IN');
                        vm.totalArrearTdsAmount = Number(vm.totalArrearTdsAmount).toLocaleString('en-IN');
                    },
                    function(error) {
                       vm.loader = false;
                        if(error.data.message == 'No Data Found' | error.data.message == 'NDF'){
                            vm.noDataFound = error.data.message;
                        }
                        else{
                            handleErrorResponse(error);
                             vm.goPreviousState();
                        }
                    }
                );
        }


                function getTrialRunEligibleAccountsFile() {
                    vm.loader = true;

                    var requestData = {
                    'moduleId' : vm.schemeCode,
                    'branchId' : vm.branchId,
                    'blaNo' : vm.blaNo,
                    'bondSubsId' : vm.bondSubsId,
                    'year' : vm.year,
                    'interestForHalfYear' : vm.interestForHalfYear,
                    'pageSize' : vm.pageSize,
                    'pageNo' : vm.pageNo,
                    'searchValue' : vm.searchValue,
                    'sortOrder' : vm.sortOrder,
                    'sortField' : vm.sortField,
                    'downloadType' : vm.downloadType,
                    'isDownload' : "Y",
                    'branchName': vm.loginSolDesc

                    };

                    rbiBondTrialRunEligibleAccountsService.getTrialRunDetailsReport(requestData)
                        .then(
                            function(response) {


                            vm.loader = false;
                                          vm.trialRunDetailsRepList = response.data.result;

                                           if(response.data.message != 'SUCCESS'){
                                             Swal.fire('Warning!', "No Record Found", 'warning');
                                           }
                                           else{
                                            vm.fileData = vm.trialRunDetailsRepList.fileData;
                                            vm.fileName = vm.trialRunDetailsRepList.fileName;
                                            saveAs(getBlobFromBase64String(vm.fileData), vm.fileName);
                                              Swal.fire("Success!", vm.fileName+"\n Downloaded Successfully", "success");
                                           }


                            			}, function (error) {

                            				vm.loader = false;

                            					handleErrorResponse(error);
                            			         Swal.fire("Error!", "Download unsuccessful", "error");

                                             vm.goPreviousState();
                            			})

                            }





        vm.goNextState = function(stateObj) {
            $state.go(vm.nextState, {
                obj: stateObj
            })
        }

        vm.goPreviousState = function() {
           if ($state.current.name == 'rbibond_centralized_trial_run_dc_report.inquiry_detail') {
              $state.go('rbibond_centralized_trial_run_dc_report.inquiry');
            } else if ($state.current.name == 'rbibond_centralized_trial_run_gu_report.inquiry_detail') {
              $state.go('rbibond_centralized_trial_run_gu_report.inquiry');
              }
        }

       function cancel() {
           if ($state.current.name == 'rbibond_centralized_trial_run_dc_report.inquiry') {
              location.href= '#!/dc_rbi_bond_menu';
            } else if ($state.current.name == 'rbibond_centralized_trial_run_gu_report.inquiry') {
             location.href= '#!/rbibond_gu_functionality';
              }
        }

             	function selectInputVal() {
                               	 if (vm.selectedCount == '0' && vm.isAllSelected == '') {
                               		Swal.fire("Please Select At least One Bond Subscription Id")
                               		}
                               	 }

                          function optionToggled(flag) {
                              vm.isAllSelected = [];
                              var bondSubsId = "";
                              vm.selectedCount = 0;

                                 if (flag == "ALL") {

                                    if(vm.selectedAllItem){
                                    angular.forEach(vm.bondSubsList, function (item, index) {
                                        vm.selectedCount = vm.selectedCount + 1;
                                        item.selected = true;
                                        vm.isAllSelected.push(item.selected)
                                        if(index == 0){bondSubsId =  item.bondSubsId;}
                                        else{bondSubsId = bondSubsId + "," + item.bondSubsId;}
                                         });
                                        vm.bondSubsId = bondSubsId;
                                    }
                                    else{
                                    angular.forEach(vm.bondSubsList, function (item) {
                                    item.selected = false;
                                    });
                                    vm.bondSubsId = null;
                                    }

       				         } else {

                               		angular.forEach(vm.bondSubsList, function (item) {
                               					vm.isAllSelected.push(item.selected)
                               					 if (item.selected) {
                               						vm.selectedCount = vm.selectedCount + 1;
                               						if(bondSubsId==""){
                               						bondSubsId =  item.bondSubsId;
                               						}else{
                               						bondSubsId = bondSubsId + "," + item.bondSubsId;
                               						}
                               					}
                               					vm.bondSubsId = bondSubsId;
                               				});

                               				if (vm.bondSubsList.length == vm.selectedCount) {
                               					vm.selectedAllItem = true;
                               				} else {
                               					vm.selectedAllItem = false;
                               				}
                                       }
                               		}


                               		                         function getTorchDetails(flag){
                                                                        vm.loader = true;


                                                                       if(flag == "sol"){

                                                                        var requestData = {
                                                                            'moduleId' : vm.schemeCode,
                                                                            'year' : vm.data.year,
                                                                            'interestForHalfYear' : vm.data.interestForHalfYear,
                                                                            'operationType' : flag
                                                                        };
                                                                        rbiBondTrialRunEligibleAccountsService.getTorchDetails(requestData).then(function (response) {
                                                                            vm.loader = false;
                                                                            vm.branchIdList = response.data.result.branchIdTorchOut;
                                                                            angular.element('#branchSearcher').modal('toggle');

                                                                        }, function (error) {
                                                                            handleErrorResponse(error);
                                                                            vm.loader = false;
                                                                        });
                                                                       }

                                                                       if(flag == "blaNo"){

                                                                           var requestData = {
                                                                           'moduleId' : vm.schemeCode,
                                                                           'branchId' : vm.branchId,
                                                                           'year' : vm.data.year,
                                                                           'interestForHalfYear' : vm.data.interestForHalfYear,
                                                                           'operationType' : flag

                                                                           };
                                                                        rbiBondTrialRunEligibleAccountsService.getTorchDetails(requestData).then(function (response) {
                                                                           vm.loader = false;
                                                                           vm.blaDataList = response.data.result.blaNumberTorchOut;
                                                                           angular.element('#blaModel').modal('toggle');

                                                                           }, function (error) {
                                                                             handleErrorResponse(error);
                                                                              vm.loader = false;
                                                                        });
                                                                      }


                                                                      if(flag == "date"){

                                                                        var requestData = {
                                                                          'moduleId' : vm.schemeCode,
                                                                          'operationType' : flag
                                                                        };
                                                                     rbiBondTrialRunEligibleAccountsService.getTorchDetails(requestData).then(function (response) {
                                                                     vm.loader = false;
                                                                     vm.data = response.data.result;

                                                                      }, function (error) {
                                                                         handleErrorResponse(error);
                                                                         vm.loader = false;
                                                                         });
                                                                     }

                                                                     if(flag == "bondSubsId"){

                                                                      var requestData = {
                                                                     'moduleId' : vm.schemeCode,
                                                                     'branchId' : vm.branchId,
                                                                     'blaNo' : vm.blaNo,
                                                                     'year' : vm.data.year,
                                                                     'interestForHalfYear' : vm.data.interestForHalfYear,
                                                                     'operationType' : flag

                                                                       };
                                                                      rbiBondTrialRunEligibleAccountsService.getTorchDetails(requestData).then(function (response) {
                                                                        vm.loader = false;
                                                                        vm.bondSubsList = response.data.result.bondSubsIdTorchOut;
                                                                        angular.element('#bondSubsIdSearch').modal('toggle');
                                                                      }, function (error) {
                                                                         handleErrorResponse(error);
                                                                         vm.loader = false;
                                                                      });
                                                                  }

                                                            }

	}
})();