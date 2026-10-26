(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'dailyRemittanceLcController',
			dailyRemittanceLcController);

	dailyRemittanceLcController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','dailyRemittanceLcService', 'commonService' ];

	function dailyRemittanceLcController($scope, $filter, $state,
			$stateParams, $location, $rootScope, dailyRemittanceLcService,
			commonService) {

		var vm = this;

        vm.currentPage = 1;
        vm.itemsPerPage = '5';
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.getDailyRemittanceLcReport = getDailyRemittanceLcReport;
        vm.getModuleName=getModuleName;
        vm.printpage = printpage;
        vm.bankName=$rootScope.globals.currentUser.bankName;
        vm.dateValidation = dateValidation;

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





         if ($state.current.name == 'rbiBond_daily_remit_lc.inquiry_detail')
                {
                   if($stateParams.obj){
                   vm.schemeCode = $stateParams.obj.schemeCode;
                   vm.date = $stateParams.obj.date;
                   vm.schemeName = $stateParams.obj.schemeName;

                    if(vm.schemeCode == '0012'){
                     vm.reg = 'Statement of RBI Bond - Floating Rate Savings Bonds, 2020 (Taxable)- FRSB 2020 (T)';
                    }
                  }
                 vm.getDailyRemittanceLcReport();

                }


        function configDataTable() {
                    vm.getTitleDescription = {
                        title: 'Daily Remittance to Link Cell',
                        solId: $rootScope.globals.currentUser.solId,
                        solDesc: $rootScope.globals.currentUser.solDesc
                    }
                    vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
                }

 function printpage(){
                    window.print()
                  }
 function getModuleName() {
            vm.loader = true;
            vm.referenceId = 'TAX';
            var requestData = {
                'referenceId': vm.referenceId
            };
            dailyRemittanceLcService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                vm.loader = false;
                vm.schemeList = response.data.result;
            }, function (error) {
                handleErrorResponse(error);
                vm.loader = false;
            });
        }



         function getDailyRemittanceLcReport(){
                       vm.loader = true;
                       vm.date = $rootScope.formatDateToDDMMMYYYY(vm.date);
                                  var requestData = {
                                              'schemeCode': vm.schemeCode,
                                              'date' : vm.date

                                  			}

                                  dailyRemittanceLcService.getDailyRemittanceLcReport(requestData).then(function(response) {
                                      vm.loader = false
                                   vm.dailyRemittanceLcList = response.data.result.list;
                                   vm.totCulAmt = response.data.result.totCul;
                                   vm.totNonCulAmt = response.data.result.totNonCul;
                                   vm.totAmt = response.data.result.tot;
                                 

                              configDataTable();
                         }, function(error){
                             vm.loader = false;
                             if(error.data.message == 'No Data Found' | error.data.message == 'NDF'){
                                   vm.noDataFound = error.data.message;
                             }else{
                               handleErrorResponse(error);
                               $state.go('rbiBond_daily_remit_lc.inquiry');
                             }

                         })
                     }

                     		function dateValidation() {
                     			vm.toDate = '';

                     			vm.dateToOptions = {
                     				minDate: vm.date,
                     				maxDate: vm.solDate
                     			};
                     			vm.dateOptions = {
                     				maxDate: vm.solDate
                     			}
                     		}









	}
})();