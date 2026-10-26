(function() {

    'use strict';

    angular.module('gbmUltraApp').controller(
        'rbiBondTdsDeductionCbController',
        rbiBondTdsDeductionCbController);

    rbiBondTdsDeductionCbController.$inject = ['$scope', '$filter',
        '$state', '$stateParams', '$location', '$rootScope', 'rbiBondTdsDeductionCbService', 'commonService'
    ];

    function rbiBondTdsDeductionCbController($scope, $filter, $state,
        $stateParams, $location, $rootScope, rbiBondTdsDeductionCbService,
        commonService) {

        var vm = this;

        vm.textableDatePicker=textableDatePicker;
        vm.autoslashing=autoslashing;

        function textableDatePicker(id,valueDate)
        {
         commonService.textableDatePicker(id,valueDate);
        }
        function autoslashing(id)
        {
         commonService.autoslashing(id);
        }




        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.moduleName = 'RBI Bond';
//        vm.moduleId = '';
        vm.nextState = '';
        vm.cancel = cancel;
        vm.getBlaNum =getBlaNum;
//        vm.getTrialRunEligibleAccountsDetails = getTrialRunEligibleAccountsDetails;
        vm.getModuleName = getModuleName;
//        vm.getTorchDetails = getTorchDetails;
        vm.getTrialRunEligibleAccountsFile = getTrialRunEligibleAccountsFile;
        vm.getTdsDeductionDetails=getTdsDeductionDetails;
        vm.optionToggled = optionToggled;
        vm.selectInputVal = selectInputVal;
        vm.alert=alert;


        vm.loginSolDate = $rootScope.globals.currentUser.solDate;

                vm.fromDateVal = fromDateVal;
                vm.toDateVal =  toDateVal;

        vm.selectedCount = 0
        vm.isAllSelected = [];


        vm.goPreviousPage = goPreviousPage;
        vm.goNextPage = goNextPage;
        vm.paginationControls = paginationControls;
        vm.showNext = false;
        vm.showPrevious = false;

        $scope.sortReverse = false;
        $scope.sort = function(key) {
            $scope.sortKey = key;
            $scope.sortReverse = ($scope.sortKey == key) ? !$scope.sortReverse : $scope.sortReverse;
            $scope.sortReverse ? vm.sortOrder = 'DESC' : vm.sortOrder = 'ASC';
            vm.sortField = key;
//            vm.getTrialRunEligibleAccountsDetails();
        }

        vm.pageSize = 5;
        vm.pageNo = 1;


        function goPreviousPage() {

            vm.pageNo = parseInt(vm.pageNo) - 1;
//            vm.getTrialRunEligibleAccountsDetails();
        }

        function goNextPage() {

            vm.pageNo = parseInt(vm.pageNo) + 1;
//            vm.getTrialRunEligibleAccountsDetails();
        }


        function paginationControls() {

            vm.pages = [];
            vm.maxPages = parseInt(vm.pageDetails.maxPages);

            for (var i = 1; i <= parseInt(vm.pageDetails.maxPages); i++) {
                vm.pages.push(parseInt(i));
            }

            if (vm.pageNo != 1) {
                vm.showPrevious = true;
            } else {
                vm.showPrevious = false;
            }

            if (vm.pageNo != vm.maxPages) {
                vm.showNext = true;
            } else {
                vm.showNext = false;
            }


        }

        function configDataTable() {
            vm.getTitleDescription = {
                title: 'TDS Deduction Report',
                solId: $rootScope.globals.currentUser.solId,
                solDesc: $rootScope.globals.currentUser.solDesc
            }
            vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
        }

       if ($state.current.name == 'rbibond_tds_deduction_report_cb.inquiry_details') {

                       if($stateParams.obj){
                           vm.schemeCode= $stateParams.obj.schemeCode;
                            vm.blaNo= $stateParams.obj.blaNo;
                            vm.fromDate= $stateParams.obj.tdsDeductFrom;
                            vm.toDate= $stateParams.obj.tdsDeductTo;
                            vm.schemeName =  $stateParams.obj.schemeName;

       					 vm.getTdsDeductionDetails();
                          // return true;
                          // }
//                   }else {
//                       $state.go('rbibond_tds_deduction_report_cb.inquiry');
//                   }
               }
               }
 function alert(item){

                                         for (let i = 0;i<vm.schemeList.length;i++){
                                          if(vm.schemeList[i].subCode == item){
                                                vm.schemeName = vm.schemeList[i].subCodeDescription;

                                           }
                                         }
                                      }


        function getModuleName() {
            vm.loader = true;
            vm.referenceId = 'TAX';
            var requestData = {
                'referenceId': vm.referenceId
            };
            rbiBondTdsDeductionCbService.getRbiBondModuleNameDetails(requestData).then(function(response) {
                vm.loader = false;
                vm.schemeList = response.data.result;
            }, function(error) {
                handleErrorResponse(error);
                vm.loader = false;
            });
        }


        function getTdsDeductionDetails() {
            vm.loader = true;

            vm.totalInterestRupees = 0;
            vm.totalTdsAmount = 0;
            vm.totalNetInterestAmount = 0;
            vm.totalArrearTdsAmount = 0;

            var requestData = {

                'blaNo': vm.blaNo,
                'tdsDeductFrom' :  moment(vm.fromDate).format("DD/MM/YYYY"),
                 'tdsDeductTo':  moment(vm.toDate).format("DD/MM/YYYY"),
                'pageSize': vm.pageSize,
                'pageNo': vm.pageNo,
                'searchValue': vm.searchValue,
                'sortOrder': vm.sortOrder,
                'sortField': vm.sortField,
                'schemeCode':vm.schemeCode,
                 'schemeName':vm.schemeName


            };

            rbiBondTdsDeductionCbService.getTdsDeductionDet(requestData)
                .then(
                    function(response) {
                        vm.loader = false;
                        vm.tdsDeductionCbList = response.data.result.tdsDeductionList;
                        vm.pageDetails = response.data.result.PageDetails;


                        vm.paginationControls();


                        if (vm.tdsDeductionCbList) {
                            angular.forEach(vm.tdsDeductionCbList, function(item) {

                                item.INTEREST_AMT == null ? item.INTEREST_AMT = 0 : item.INTEREST_AMT;
                                item.CURRENT_TDS_AMOUNT == null ? item.CURRENT_TDS_AMOUNT = 0 : item.CURRENT_TDS_AMOUNT;
                                item.NET_INTEREST_AMOUNT == null ? item.NET_INTEREST_AMOUNT = 0 : item.NET_INTEREST_AMOUNT;
//                                item.arrearTdsAmount == null ? item.arrearTdsAmount = 0 : item.arrearTdsAmount;



                                vm.totalInterestRupees += parseInt(item.INTEREST_AMT);
                                vm.totalTdsAmount += parseInt(item.CURRENT_TDS_AMOUNT);
                                vm.totalNetInterestAmount += parseInt(item.NET_INTEREST_AMOUNT);
//                                vm.totalArrearTdsAmount += parseInt(item.arrearTdsAmount);

                                item.interestPeriodFrom = new Date(item.interestPeriodFrom);
                                item.interestPeriodTo = new Date(item.interestPeriodTo);
                                item.accountOpenDate = new Date(item.accountOpenDate);
                                item.INTEREST_AMT = Number(item.INTEREST_AMT).toLocaleString('en-IN');
                                item.CURRENT_TDS_AMOUNT = Number(item.CURRENT_TDS_AMOUNT).toLocaleString('en-IN');
                                item.NET_INTEREST_AMOUNT = Number(item.NET_INTEREST_AMOUNT).toLocaleString('en-IN');
//                                item.arrearTdsAmount = Number(item.arrearTdsAmount).toLocaleString('en-IN');

                            })
                        }
                        vm.totalInterestRupees = Number(vm.totalInterestRupees).toLocaleString('en-IN');
                        vm.totalTdsAmount = Number(vm.totalTdsAmount).toLocaleString('en-IN');
                        vm.totalNetInterestAmount = Number(vm.totalNetInterestAmount).toLocaleString('en-IN');
//                        vm.totalArrearTdsAmount = Number(vm.totalArrearTdsAmount).toLocaleString('en-IN');
                    },
                    function(error) {
                        vm.loader = false;
                        if (error.data.message == 'No Data Found' | error.data.message == 'NDF') {
                            vm.noDataFound = error.data.message;
                        } else {
                            handleErrorResponse(error);
                            vm.goPreviousState();
                        }
                    }
                );
        }


        function getTrialRunEligibleAccountsFile() {
            vm.loader = true;

                                          var requestData = {
                                          'schemeCode':vm.schemeCode,
                                           'blaNo': vm.blaNo,
                                           'tdsDeductFrom' : moment(vm.fromDate).format("DD/MM/YYYY"),
                                          'tdsDeductTo': moment(vm.toDate).format("DD/MM/YYYY"),
                                          'pageSize' : vm.pageSize,
                                          'pageNo' : vm.pageNo,
                                          'searchValue' : vm.searchValue,
                                          'sortOrder' : vm.sortOrder,
                                          'sortField' : vm.sortField,
                                          'downloadType' : vm.downloadType,
                                          'isDownload' : "Y",
                                          'branchName': vm.loginSolDesc,
                                           'schemeName':vm.schemeName

                                          };

                                          rbiBondTdsDeductionCbService.getTdsDeductionDet(requestData)
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
                   if ($state.current.name == 'rbibond_tds_deduction_report_cb.inquiry_details') {
                      $state.go('rbibond_tds_deduction_report_cb.inquiry');
                    } else if ($state.current.name == 'rbibond_tds_deduction_report_cb.inquiry_details') {
                      $state.go('rbibond_tds_deduction_report_cb.inquiry');
                      } else if ($state.current.name == 'rbibond_tds_deduction_report_cb.inquiry_details') {
                      $state.go('rbibond_tds_deduction_report_cb.inquiry');
                }
                }

        function cancel() {
            if ($state.current.name == 'rbibond_tds_deduction_report_cb.inquiry') {
                location.href = '#!/rbibond_gu_functionality';
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

                if (vm.selectedAllItem) {
                    angular.forEach(vm.bondSubsList, function(item, index) {
                        vm.selectedCount = vm.selectedCount + 1;
                        item.selected = true;
                        vm.isAllSelected.push(item.selected)
                        if (index == 0) {
                            bondSubsId = item.bondSubsId;
                        } else {
                            bondSubsId = bondSubsId + "," + item.bondSubsId;
                        }
                    });
                    vm.bondSubsId = bondSubsId;
                } else {
                    angular.forEach(vm.bondSubsList, function(item) {
                        item.selected = false;
                    });
                    vm.bondSubsId = null;
                }

            } else {

                angular.forEach(vm.bondSubsList, function(item) {
                    vm.isAllSelected.push(item.selected)
                    if (item.selected) {
                        vm.selectedCount = vm.selectedCount + 1;
                        if (bondSubsId == "") {
                            bondSubsId = item.bondSubsId;
                        } else {
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


        function getBlaNum() {
            vm.loader = true;

                var requestData = {
                    'moduleId': vm.schemeCode
                };
                rbiBondTdsDeductionCbService.getTorchDetails(requestData).then(function(response) {
                    vm.loader = false;
                    vm.blaDataList = response.data.result;
                    angular.element('#blaModel').modal('toggle');

                }, function(error) {
                    handleErrorResponse(error);
                    vm.loader = false;
                });
            }


                                     function fromDateVal(){
                                          vm.fromDateOptions={
                                            maxDate: convertToDateObject(vm.loginSolDate),
                                            showWeeks: false
                                           }

                                     }

                                      function toDateVal(fromDate){
                                               vm.toDateOptions={
                                                minDate: fromDate,
                                                maxDate: convertToDateObject(vm.loginSolDate),
                                                showWeeks: false
                                                }

                                       }




        }

    }  )();