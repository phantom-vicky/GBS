(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'StatementOfBondsController',
			StatementOfBondsController);

	StatementOfBondsController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','StatementOfBondsService', 'commonService' ];

	function StatementOfBondsController($scope, $filter, $state,
			$stateParams, $location, $rootScope, StatementOfBondsService,
			commonService) {

		var vm = this;

        vm.currentPage = 1;
        vm.itemsPerPage = '5';
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
         vm.currentDate = new Date();
         vm.currentDate = $filter('date')(vm.currentDate, 'dd/MM/yyyy');
        vm.getStatementBondsIssusedReport = getStatementBondsIssusedReport;
        vm.getModuleName=getModuleName;
        vm.printpage = printpage;

         if ($state.current.name == 'Statement_of_Bonds_Issued_Repaid_for_the_month.inquiry_details')
                {
                vm.fromdate = $stateParams.obj.fromdate;
                vm.todate = $stateParams.obj.todate;
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;

                 getStatementBondsIssusedReport();

                }


        function configDataTable() {
                    vm.getTitleDescription = {
                        title: 'Statement of Bonds Issued Repaid for the month',
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
                    StatementOfBondsService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                        vm.loader = false;
                        vm.schemeList = response.data.result;
                    }, function (error) {
                        handleErrorResponse(error);
                        vm.loader = false;
                    });
                }





         function getStatementBondsIssusedReport(){
                       vm.loader = true;

                                  var requestData = {

                                  			'fromdate' : moment(vm.fromdate).format("DD/MMM/YYYY"),
                                  	         'todate' : moment(vm.todate).format("DD/MMM/YYYY"),
                                               'schemeCode': vm.schemeCode

                                  			}

                                  StatementOfBondsService.getDetails(requestData).then(function(response) {
                                      vm.loader = false;

                                 vm.StatementBondDetailsOne =response.data.result.cursorOne;
                                 vm.StatementBondDetailsTwo =response.data.result.cursorTwo;
                                 vm.StatementBondDetailsThree =response.data.result.cursorThree;


//                              configDataTable();
                         }, function(error){
                             vm.loader = false;
                             if(error.data.message == 'No Data Found' | error.data.message == 'NDF'){
                                   vm.noDataFound = error.data.message;
                             }else{
                               handleErrorResponse(error);
                               $state.go('Statement_of_Bonds_Issued_Repaid_for_the_month.inquiry');
                             }

                         })
                     }





	}
})();