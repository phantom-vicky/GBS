(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'rbibondInterRunCretCollBranController',
			rbibondInterRunCretCollBranController);

	rbibondInterRunCretCollBranController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','rbibondInterRunCretCollBranService', 'commonService' ];

	function rbibondInterRunCretCollBranController($scope, $filter, $state,
			$stateParams, $location, $rootScope, rbibondInterRunCretCollBranService,
			commonService) {

		var vm = this;

        vm.currentPage = 1;
        vm.itemsPerPage = '5';

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
        vm.currentDate = new Date();
        vm.currentDate = $filter('date')(vm.currentDate, 'dd/MM/yyyy');
        vm.getTdsDeductionDetail = getTdsDeductionDetail;
        vm.getTrialRunEligibleAccountsFile = getTrialRunEligibleAccountsFile;
        vm.bankName = $rootScope.globals.currentUser.bankName;
        vm.getModuleName=getModuleName;
//        vm.getBranchId=getBranchId;
        vm.getBlaAccNo=getBlaAccNo;
        vm.getSubscriptionId=getSubscriptionId;
         vm.dateValidation = dateValidation;
         vm.alert=alert;
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

                 vm.pageSize = 5;
                 vm.pageNo = 1;


                function goPreviousPage() {

                 vm.pageNo = parseInt(vm.pageNo) - 1;
                 vm.getTdsDeductionDetail();
                 }

                 function goNextPage() {

                 vm.pageNo = parseInt(vm.pageNo) + 1;
                 vm.getTdsDeductionDetail();
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




         if ($state.current.name == 'rbibond_interest_run_credit_collect_branch_report.inquiry_details')
                {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName =  $stateParams.obj.schemeName;
                 vm.fromDate = $stateParams.obj.interestPayFrom;
                 vm.toDate = $stateParams.obj.interestPayTo;
                vm.branchId = $stateParams.obj.branchId;
                vm.blaAccNo = $stateParams.obj.blaNo;
                vm.bondScbId = $stateParams.obj.subscriptionId;


                             getTdsDeductionDetail();

                }


        function configDataTable() {
                    vm.getTitleDescription = {
                        title: 'TDS Deduction Report',
                        solId: $rootScope.globals.currentUser.solId,
                        solDesc: $rootScope.globals.currentUser.solDesc
                    }
                    vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
                }

 function getModuleName() {
            vm.loader = true;
            vm.referenceId = 'TAX';
            var requestData = {
                'referenceId': vm.referenceId
            };
            rbibondInterRunCretCollBranService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                vm.loader = false;
                vm.schemeList = response.data.result;
            }, function (error) {
                handleErrorResponse(error);
                vm.loader = false;
            });
        }

          function alert(item){

                                         for (let i = 0;i<vm.schemeList.length;i++){
                                          if(vm.schemeList[i].subCode == item){
                                                vm.schemeName = vm.schemeList[i].subCodeDescription;

                                           }
                                         }
                                      }

//        function getBranchId() {
//                                                   vm.loader = true;
//
//                                                   var requestData = {
//                                                   'isTorch' : 'Y',
//                                                        'schemeCode':vm.schemeCode
//
//
//                                                   }
//                                                   rbibondInterestRunCreditService.getDetails(requestData).then(function(response) {
//                                                       vm.loader = false;
//                                                       vm.branchIdList = response.data.result;
//                                                       angular.element('#branchIdModal').modal('toggle');
//                                                   }, function(error) {
//                                                       handleErrorResponse(error);
//                                                       vm.loader = false;
//                                                   });
//                                               }


         function getBlaAccNo() {
                                            vm.loader = true;

                                            var requestData = {
                                            'isTorch' : 'Y',
                                                'schemeCode': vm.schemeCode


                                            }
                                            rbibondInterRunCretCollBranService.getDetails(requestData).then(function(response) {
                                                vm.loader = false;
                                                vm.blaAccNoList = response.data.result;
                                                //                       vm.tocSlabListEffctDate=response.data.result.effeFromDateList;

                                                angular.element('#blaAccNoModal').modal('toggle');
                                            }, function(error) {
                                                handleErrorResponse(error);
                                                vm.loader = false;
                                            });
                                        }

function getSubscriptionId() {
                                                            vm.loader = true;

                                                            var requestData = {
                                                            'isTorch' : 'Y',
                                                                'schemeCode': vm.schemeCode,
                                                                'blaNo' : vm.blaAccNo


                                                            }
                                                            rbibondInterRunCretCollBranService.getDetails(requestData).then(function(response) {
                                                                vm.loader = false;
                                                                vm.subscriptionIdList = response.data.result;
                                                                //                       vm.tocSlabListEffctDate=response.data.result.effeFromDateList;

                                                                angular.element('#subScriptionIdModal').modal('toggle');
                                                            }, function(error) {
                                                                handleErrorResponse(error);
                                                                vm.loader = false;
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
                               'interestPayFrom' :  moment(vm.fromDate).format("DD/MMM/YYYY"),
                                'interestPayTo':  moment(vm.toDate).format("DD/MMM/YYYY"),
                                 'blaNo': vm.blaAccNo,
                                 'subscriptionId': vm.bondScbId,
                                  'pageSize' : vm.pageSize,
                                             'pageNo' : vm.pageNo,
                                             'searchValue' : vm.searchValue,
                                             'sortOrder' : vm.sortOrder,
                                             'sortField' : vm.sortField,
                                             'isTorch' : 'N'

                     };

                     rbibondInterRunCretCollBranService.getDetails(requestData)
                         .then(
                             function(response) {
                                 vm.loader = false;
                                 vm.pageDetails =response.data.result;
                                 vm.tdsDeductionList = vm.pageDetails.data;



                                 vm.paginationControls();


                                 if(vm.tdsDeductionList){

                                 vm.interestTotal = 0;
                                 vm.TdsTotal = 0;
                                 vm.netAmtTotal = 0;

                                 angular.forEach(vm.tdsDeductionList, function(item) {

                                 item.Gross_Interest_Amount == null ? item.Gross_Interest_Amount = 0 : item.Gross_Interest_Amount;
//                                 item.ARREAR_TDS_AMOUNT == null ? item.ARREAR_TDS_AMOUNT = 0 : item.ARREAR_TDS_AMOUNT;
                                 item.TDS_AMT == null ? item.TDS_AMT = 0 : item.TDS_AMT;
                                 item.NET_INTEREST_AMOUNT == null ? item.NET_INTEREST_AMOUNT = 0 : item.NET_INTEREST_AMOUNT ;


                                 vm.interestTotal = vm.interestTotal +  parseInt(item.Gross_Interest_Amount);
//                                 var totalTdsAmount = parseInt(item.ARREAR_TDS_AMOUNT) + parseInt(item.CURRENT_TDS_AMOUNT);
                                 vm.TdsTotal = vm.TdsTotal +  parseInt(item.TDS_AMT);
                                 vm.netAmtTotal = vm.netAmtTotal + parseInt(item.NET_INTEREST_AMOUNT);



                                                             item.accountOpenDate = new Date(item.accountOpenDate);
                                                             item.INTEREST_AMT = Number(item.Gross_Interest_Amount).toLocaleString('en-IN');
                                                             item.TDS_AMT = Number(item.TDS_AMT).toLocaleString('en-IN');
//                                                             item.TDS_AMT = totalTdsAmount;
                                                             item.NET_INTEREST_AMOUNT = Number(item.NET_INTEREST_AMOUNT).toLocaleString('en-IN');

                                                         });
                                 }
                                 vm.interestTotal = Number(vm.interestTotal).toLocaleString('en-IN');
                                 vm.TdsTotal = Number(vm.TdsTotal).toLocaleString('en-IN');
                                 vm.netAmtTotal = Number(vm.netAmtTotal).toLocaleString('en-IN');

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
                               'schemeCode':vm.schemeCode,
                               'interestPayFrom' :  moment(vm.fromDate).format("DD/MMM/YYYY"),
                               'interestPayTo':  moment(vm.toDate).format("DD/MMM/YYYY"),
                               'blaNo': vm.blaAccNo,
                               'subscriptionId': vm.bondScbId,
                              'pageSize' : vm.pageSize,
                              'pageNo' : vm.pageNo,
                              'searchValue' : vm.searchValue,
                              'sortOrder' : vm.sortOrder,
                              'sortField' : vm.sortField,
                              'downloadType' : vm.downloadType,
                              'isDownload' : "Y",
                              'branchName': vm.loginSolDesc,
                              'isTorch' : 'N'

                              };

                              rbibondInterRunCretCollBranService.getDetails(requestData)
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
//         function getTdsDeductionDetail(){
//                       vm.loader = true;
//                                  var requestData = {
//                                      'schemeCode':vm.schemeCode,
//                                      'branchId': vm.branchId,
//                                      'blaNo': vm.blaAccNo,
//                                      'tdsDeductFrom' : vm.fromDate,
//                                         'tdsDeductTo': vm.toDate
//
//                                  }
//
//                                  rbibondTdsDeductionService.getTdsDetails(requestData).then(function(response) {
//                                      vm.loader = false;
//
//                                       vm.model =response.data.result;
//
//                              configDataTable();
//                         }, function(error){
//                             vm.loader = false;
//                             //Swal.fire
//                            Swal.fire("No Data Found", msg, "success");
////                             if(error.data.message == 'No Data Found' | error.data.message == 'NDF'){
////                                   vm.noDataFound = error.data.message;
////                             }else{
////                               handleErrorResponse(error);
////                               $state.go('rbibond_nomination_register.inquiry_details');
////                             }
//
//                         })
//                     }
 vm.goNextState = function(stateObj) {
            $state.go(vm.nextState, {
                obj: stateObj
            })
        }
 vm.goPreviousState = function() {
           if ($state.current.name == 'rbibond_interest_run_credit_collect_branch_report.inquiry_details') {
              $state.go('rbibond_interest_run_credit_collect_branch_report.inquiry');
            } else if ($state.current.name == 'rbibond_interest_run_credit_collect_branch_report.inquiry_details') {
              $state.go('rbibond_interest_run_credit_collect_branch_report.inquiry');
              } else if ($state.current.name == 'rbibond_interest_run_credit_collect_branch_report.inquiry_details') {
              $state.go('rbibond_interest_run_credit_collect_branch_report.inquiry');
        }
        }

        function cancel() {
           if ($state.current.name == 'rbibond_interest_run_credit_collect_branch_report.inquiry') {
              location.href= '#!/dc_rbi_bond_menu';
            } else if ($state.current.name == 'rbibond_interest_run_credit_collect_branch_report.inquiry') {
             location.href= '#!/rbibond_gu_functionality';
              } else if ($state.current.name == 'rbibond_interest_run_credit_collect_branch_report.inquiry') {
              location.href= '#!/rbibond_gu_functionality';
        }
        }
 function dateValidation() {
                                                           vm.toDate = '';

                                                           vm.dateToOptions = {
                                                               minDate: vm.fromDate,
                                                                maxDate:convertToDateObject(vm.currentDate)
                                                           };
                                                           vm.dateOptions = {
                                                            maxDate:convertToDateObject(vm.currentDate)
                                                           }


                                                       }

	}
})();