(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'rbibondAckNominationController',
			rbibondAckNominationController);

	rbibondAckNominationController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','rbibondAckNominationService', 'commonService' ];

	function rbibondAckNominationController($scope, $filter, $state,
			$stateParams, $location, $rootScope, rbibondAckNominationService,
			commonService) {

		var vm = this;

        vm.currentPage = 1;
        vm.itemsPerPage = '5';
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.currentDate = new Date();
        vm.currentDate = $filter('date')(vm.currentDate, 'dd/MM/yyyy');
        vm.getAckNominationReport = getAckNominationReport;
        vm.getModuleName=getModuleName;
        vm.getBlaAccNo=getBlaAccNo;
        vm.getSubscriptionId=getSubscriptionId;
        vm.getNomineeId=getNomineeId;
        vm.printpage = printpage;

         if ($state.current.name == 'rbibond_ack_nomination_report.inquiry_details')
                {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.blaAccNo = $stateParams.obj.blaNo;
                vm.bondScbId = $stateParams.obj.bondSubscriptionId;
                vm.nominiId = $stateParams.obj.nomineeId;
                 getAckNominationReport();

                }


        function configDataTable() {
                    vm.getTitleDescription = {
                        title: 'Acknowledgement of Nomination',
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
            rbibondAckNominationService.getRbiBondModuleNameDetails(requestData).then(function (response) {
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
                            rbibondAckNominationService.getDetails(requestData).then(function(response) {
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
                                                        'schemeCode': vm.schemeCode,
                                                        'blaNo' : vm.blaAccNo

                                                    }
                                                    rbibondAckNominationService.getDetails(requestData).then(function(response) {
                                                        vm.loader = false;
                                                        vm.subscriptionIdList = response.data.result;
                                                        //                       vm.tocSlabListEffctDate=response.data.result.effeFromDateList;

                                                        angular.element('#subScriptionIdModal').modal('toggle');
                                                    }, function(error) {
                                                        handleErrorResponse(error);
                                                        vm.loader = false;
                                                    });
                                                }
              function getNomineeId() {
                                                                 vm.loader = true;

                                                                 var requestData = {
                                                                     'schemeCode': vm.schemeCode,
                                                                     'blaNo' : vm.blaAccNo,
                                                                     'bondSubscriptionId':vm.bondScbId

                                                                 }
                                                                 rbibondAckNominationService.getDetails(requestData).then(function(response) {
                                                                     vm.loader = false;
                                                                     vm.nomineeIdIdList = response.data.result;
                                                                     //                       vm.tocSlabListEffctDate=response.data.result.effeFromDateList;

                                                                     angular.element('#nomineeIdModal').modal('toggle');
                                                                 }, function(error) {
                                                                     handleErrorResponse(error);
                                                                     vm.loader = false;
                                                                 });
                                                             }
         function getAckNominationReport(){
                       vm.loader = true;
                                  var requestData = {

                                       'schemeCode':vm.schemeCode,
                                        'blaNo':vm.blaAccNo,
                                        'bondSubscriptionId':vm.bondScbId,
                                          'nomineeId':vm.nominiId
                                  }

                                  rbibondAckNominationService.getDetails(requestData).then(function(response) {
                                      vm.loader = false;

                                       vm.model =response.data.result;

                              configDataTable();
                         }, function(error){
                             vm.loader = false;
                             if(error.data.message == 'No Data Found' | error.data.message == 'NDF'){
                                   vm.noDataFound = error.data.message;
                             }else{
                               handleErrorResponse(error);
                               $state.go('rbibond_ack_nomination_report.inquiry_details');
                             }

                         })
                     }

	}
})();