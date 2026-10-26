(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'bondSubscriptionwithoutnominationController',
			bondSubscriptionwithoutnominationController);

	bondSubscriptionwithoutnominationController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope',bondSubscriptionwithoutnominationService', 'commonService' ];

	function bondSubscriptionwithoutnominationController($scope, $filter, $state,
			$stateParams, $location, $rootScope, bondSubscriptionwithoutnominationService,
			commonService) {

		var vm = this;

        vm.currentPage = 1;
        vm.itemsPerPage = '5';
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.getDetails = getDetails;
        vm.getModuleName=getModuleName;
        vm.getBlaAccNo=getBlaAccNo;
        vm.printpage = printpage;
        vm.bondholdername;
        vm.loginSolDate = $rootScope.globals.currentUser.solDate;


               if ($state.current.name == 'bond_subscription_without_nomination.inquiry_details')
                {

                vm.blaNumber = $stateParams.obj.blaNumber;
                vm.name = $stateParams.obj.name;
                vm.panNo = $stateParams.obj.panNo;
                vm.blaNumber = subcriptionId.obj.subcriptionId;
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;
                 getDetails();

                }


                function configDataTable() {
                    vm.getTitleDescription = {
                        title: 'Bond subscription without nomination report',
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
                  bondSubscriptionwithoutnominationService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                  vm.loader = false;
                    vm.schemeList = response.data.result;
                 }, function (error) {
                  handleErrorResponse(error);
                  vm.loader = false;
                 });
                }


                function getBlaAccNo() {
                            vm.loader = true;

                            var requestData = {
                                'schemeCode': vm.schemeCode

                            }
                            bondSubscriptionwithoutnominationService.getModuleDetails(requestData).then(function(response) {
                                vm.loader = false;
                                vm.blaAccNoList = response.data.result;
                                angular.element('#blaAccNoModal').modal('toggle');
                            }, function(error) {
                                handleErrorResponse(error);
                                vm.loader = false;
                            });
                }


                function getDetails(){
                       vm.loader = true;

                                  var requestData = {
                                        'blaNumber':vm.blaNumber,
                                       'subcriptionId': vm.subcriptionId,
                                       'panNo': vm.panNo,
                                       'name': vm.name,
                                       'schemeCode': vm.schemeCode,
                                  		                                  	}
                       bondSubscriptionwithoutnominationService.getDetails(requestData).then(function(response) {
                       vm.loader = false;

                             vm.ListOfDtos =response.data.result;
                              configDataTable();
                       }, function(error){
                             vm.loader = false;
                             if(error.data.message == 'No Data Found' | error.data.message == 'NDF'){
                                   vm.noDataFound = error.data.message;
                                   $state.go('bond_subscription_without_nomination.inquiry');
                             }else{
                               handleErrorResponse(error);
                               $state.go('bond_subscription_without_nomination.inquiry');
                             }

                       })
                }


                function fromDateVal(){
                     vm.fromDateOptions={
                         maxDate: convertToDateObject(vm.loginSolDate),
                         howWeeks: false
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
})();