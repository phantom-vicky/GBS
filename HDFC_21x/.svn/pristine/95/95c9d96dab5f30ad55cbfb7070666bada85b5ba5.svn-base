(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'rbibondInterestRepayController',
			rbibondInterestRepayController);

	rbibondInterestRepayController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','rbibondInterestRepayService', 'commonService' ];

	function rbibondInterestRepayController($scope, $filter, $state,
			$stateParams, $location, $rootScope, rbibondInterestRepayService,
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
        vm.getInterestRepayment = getInterestRepayment;
        vm.getModuleName=getModuleName;
        vm.alert=alert;

         if ($state.current.name == 'rbibond_interest_repayment_scroll.inquiry_details')
                {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;
                vm.interestPaid = $stateParams.obj.intertestPaidFor;
                vm.financialYear = $stateParams.obj.finacialYear;
                             getInterestRepayment();

                }


        function configDataTable() {
                    vm.getTitleDescription = {
                        title: 'Appendix IV Interest Payment Scroll',
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
            rbibondInterestRepayService.getRbiBondModuleNameDetails(requestData).then(function (response) {
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
                                       //vm.cusName = vm.custList[i].cusName;
                                  //  vm.cusAlphaCode = vm.custList[i].cusAlphaCode;
                                   // vm.id = vm.custList[i].id;schemeList
                                   }
                                 }
                              }
         function getInterestRepayment(){
                       vm.loader = true;
                                  var requestData = {
                                      'schemeCode':vm.schemeCode,
                                      'schemeName' :vm.schemeName,
                                       'intertestPaidFor' : vm.interestPaid,
                                        'finacialYear'  :vm.financialYear
                                  }

                                  rbibondInterestRepayService.getDetails(requestData).then(function(response) {
                                      vm.loader = false;

                                       vm.model =response.data.result.bondInterestRepayDTOList;
                                       vm.mod =response.data.result.Total;

                                         angular.forEach(vm.mod, function(item){
                                                           item.Total = Number(item.Total).toLocaleString("en-IN");
                                            })
                                              vm.mod = Number(vm.mod).toLocaleString("en-IN");

                              configDataTable();
                         }, function(error){

                                           				vm.loader = false;
                                           				if (error.data.message == 'No Data Found') {
                                           					vm.noDataFound = error.data.message;
                                           				} else {
                                           					handleErrorResponse(error);
                                           					$state.go('rbibond_interest_repayment_scroll.inquiry')
                                           				}
                                           			}

                         )
                     }

	}
})();