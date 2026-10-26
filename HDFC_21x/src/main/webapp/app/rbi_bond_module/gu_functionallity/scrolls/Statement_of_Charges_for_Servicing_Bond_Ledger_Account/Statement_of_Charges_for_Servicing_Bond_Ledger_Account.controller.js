(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'StatementOfChargesForServicingController',
			StatementOfChargesForServicingController);

	StatementOfChargesForServicingController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','StatementOfChargesServicingService', 'commonService' ];

	function StatementOfChargesForServicingController($scope, $filter, $state,
			$stateParams, $location, $rootScope, StatementOfChargesServicingService,
			commonService) {

		var vm = this;

        vm.currentPage = 1;
        vm.itemsPerPage = '5';
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
         vm.currentDate = new Date();
         vm.currentDate = $filter('date')(vm.currentDate, 'dd/MM/yyyy');
        vm.getStatementOfChargesServicingReport = getStatementOfChargesServicingReport;
        vm.getModuleName=getModuleName;
        vm.printpage = printpage;

         if ($state.current.name == 'Statement_of_Charges_for_Servicing_Bond_Ledger_Account.inquiry_details')
                {
                vm.fromdate = $stateParams.obj.fromdate;
                vm.todate = $stateParams.obj.todate;
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;

                 getStatementOfChargesServicingReport();

                }



        function configDataTable() {
                    vm.getTitleDescription = {
                        title: 'Statement of Charges for Servicing Bond Ledger Account',
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
                    StatementOfChargesServicingService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                        vm.loader = false;
                        vm.schemeList = response.data.result;
                    }, function (error) {
                        handleErrorResponse(error);
                        vm.loader = false;
                    });
                }





         function getStatementOfChargesServicingReport(){
                       vm.loader = true;

                                  var requestData = {

                                  			'fromdate' : moment(vm.fromdate).format("DD/MMM/YYYY"),
                                  	         'todate' : moment(vm.todate).format("DD/MMM/YYYY"),
                                               'schemeCode': vm.schemeCode

                                  			}

                                  StatementOfChargesServicingService.getDetails(requestData).then(function(response) {
                                      vm.loader = false;

                                 vm.StatementBondChargesServicingDetails =response.data.result.item;
                                 vm.totalServicedetails1 =response.data.result.total;
                                 vm.total2Servicedetails2 =response.data.result.total2;

                              configDataTable();
                         }, function(error){
                             vm.loader = false;
                             if(error.data.message == 'No Data Found' | error.data.message == 'NDF'){
                                   vm.noDataFound = error.data.message;
                             }else{
                               handleErrorResponse(error);
                               $state.go('Statement_of_Charges_for_Servicing_Bond_Ledger_Account.inquiry');
                             }

                         })
                     }





	}
})();