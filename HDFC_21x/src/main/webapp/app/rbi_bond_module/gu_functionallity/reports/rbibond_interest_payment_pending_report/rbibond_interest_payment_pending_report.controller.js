(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'rbibondInterestPaymentPendingController',
			rbibondInterestPaymentPendingController);

	rbibondInterestPaymentPendingController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','rbibondInterestPaymentPendingService', 'commonService' ];

	function rbibondInterestPaymentPendingController($scope, $filter, $state,
			$stateParams, $location, $rootScope, rbibondInterestPaymentPendingService,
			commonService) {

		var vm = this;

        vm.currentPage = 1;
        vm.itemsPerPage = '5';
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
         vm.bankName = $rootScope.globals.currentUser.bankName;
        vm.currentDate = new Date();
        vm.currentDate = $filter('date')(vm.currentDate, 'dd/MM/yyyy');
        vm.getInterestTrailPaymentWise = getInterestTrailPaymentWise;
        vm.getTrialRunEligibleAccountsFile = getTrialRunEligibleAccountsFile;
        vm.getModuleName=getModuleName;
         vm.dateValidation = dateValidation;
         vm.alert=alert;
         vm.getBranchId=getBranchId;
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
                 vm.getInterestTrailPaymentWise();
                 }

                 vm.pageSize = 5;
                 vm.pageNo = 1;


                function goPreviousPage() {

                 vm.pageNo = parseInt(vm.pageNo) - 1;
                 vm.getInterestTrailPaymentWise();
                 }

                 function goNextPage() {

                 vm.pageNo = parseInt(vm.pageNo) + 1;
                 vm.getInterestTrailPaymentWise();
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




         if ($state.current.name == 'rbibond_interest_payment_pending_report.inquiry_details')
                {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.branchId = $stateParams.obj.branchId;
                vm.schemeName =  $stateParams.obj.schemeName;
                vm.interestPaid = $stateParams.obj.intertestPaymentFor;
                vm.financialYear = $stateParams.obj.finacialYear;
                vm.interestPaymentMode = $stateParams.obj.interestPaymentMode;

                             getInterestTrailPaymentWise();

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
            rbibondInterestPaymentPendingService.getRbiBondModuleNameDetails(requestData).then(function (response) {
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




function getBranchId() {
                                                   vm.loader = true;

                                                   var requestData = {
                                                   'isTorch' : 'Y',
                                                        'schemeCode':vm.schemeCode


                                                   }
                                                   rbibondInterestPaymentPendingService.getDetails(requestData).then(function(response) {
                                                       vm.loader = false;
                                                       vm.branchIdList = response.data.result;
                                                       angular.element('#branchIdModal').modal('toggle');
                                                   }, function(error) {
                                                       handleErrorResponse(error);
                                                       vm.loader = false;
                                                   });
                                               }
          function getInterestTrailPaymentWise() {
                     vm.loader = true;

                     vm.totalInterestRupees = 0;
                     vm.totalTdsAmount = 0;
                     vm.totalNetInterestAmount = 0;


                     var requestData = {
                       'isTorch' : 'N',
                      'schemeCode':vm.schemeCode,
                      'branchId':vm.branchId,
                      'intertestPaymentFor':vm.interestPaid,
                       'finacialYear':vm.financialYear,
                        'schemeName':vm.schemeName,
                          'pageSize' : vm.pageSize,
                           'pageNo' : vm.pageNo,
                           'searchValue' : vm.searchValue,
                            'sortOrder' : vm.sortOrder,
                             'sortField' : vm.sortField

                     };

                     rbibondInterestPaymentPendingService.getDetails(requestData)
                         .then(
                             function(response) {
                                 vm.loader = false;
                                 vm.pageDetails = response.data.result;
                                 vm.paymentWiseList = vm.pageDetails.data;



                                 vm.paginationControls();


                                 if(vm.paymentWiseList){

                                 vm.interestTotal = 0;
                                 vm.TdsTotal = 0;
                                 vm.netAmtTotal = 0;

                                 angular.forEach(vm.paymentWiseList, function(item) {

                                 item.GROSS_INTEREST == null ? item.GROSS_INTEREST = 0 : item.GROSS_INTEREST;
                                 //   item.ARREAR_TDS_AMOUNT == null ? item.ARREAR_TDS_AMOUNT = 0 : item.ARREAR_TDS_AMOUNT;
                                   item.CURRENT_TDS_AMOUNT == null ? item.CURRENT_TDS_AMOUNT = 0 : item.CURRENT_TDS_AMOUNT;
                             item.NET_INTEREST_AMOUNT == null ? item.NET_INTEREST_AMOUNT = 0 : item.NET_INTEREST_AMOUNT ;


                                    vm.interestTotal = vm.interestTotal +  parseInt(item.GROSS_INTEREST);
                                 //                                 var totalTdsAmount = parseInt(item.ARREAR_TDS_AMOUNT) + parseInt(item.CURRENT_TDS_AMOUNT);
                                        vm.TdsTotal = vm.TdsTotal +  parseInt(item.CURRENT_TDS_AMOUNT);
                                       vm.netAmtTotal = vm.netAmtTotal + parseInt(item.NET_INTEREST_AMOUNT);



                                               item.accountOpenDate = new Date(item.accountOpenDate);
                                                item.GROSS_INTEREST = Number(item.GROSS_INTEREST).toLocaleString('en-IN');
                                                 item.CURRENT_TDS_AMOUNT = Number(item.CURRENT_TDS_AMOUNT).toLocaleString('en-IN');
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
                                'branchId':vm.branchId,
                                'intertestPaymentFor':vm.interestPaid,
                                'finacialYear':vm.financialYear,
                                'schemeName':vm.schemeName,
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

                              rbibondInterestPaymentPendingService.getDetails(requestData)
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
           if ($state.current.name == 'rbibond_interest_payment_pending_report.inquiry_details') {
              $state.go('rbibond_interest_payment_pending_report.inquiry');
            } else if ($state.current.name == 'rbibond_interest_payment_pending_report.inquiry_details') {
              $state.go('rbibond_interest_payment_pending_report.inquiry');
              } else if ($state.current.name == 'rbibond_interest_payment_pending_report.inquiry_details') {
              $state.go('rbibond_interest_payment_pending_report.inquiry');
        }
        }

        function cancel() {
           if ($state.current.name == 'rbibond_interest_payment_pending_report.inquiry') {
              location.href= '#!/dc_rbi_bond_menu';
            } else if ($state.current.name == 'rbibond_interest_payment_pending_report.inquiry') {
             location.href= '#!/rbibond_gu_functionality';
              } else if ($state.current.name == 'rbibond_interest_payment_pending_report.inquiry') {
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