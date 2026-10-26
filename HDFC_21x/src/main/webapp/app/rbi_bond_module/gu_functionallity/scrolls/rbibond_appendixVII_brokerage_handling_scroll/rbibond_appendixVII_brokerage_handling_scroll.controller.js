(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'rbibondAppendixVIIBrokerageController',
			rbibondAppendixVIIBrokerageController);

	rbibondAppendixVIIBrokerageController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','rbibondAppendixVIIBrokerageService', 'commonService' ];

	function rbibondAppendixVIIBrokerageController($scope, $filter, $state,
			$stateParams, $location, $rootScope, rbibondAppendixVIIBrokerageService,
			commonService) {

		var vm = this;

        vm.currentPage = 1;
        vm.itemsPerPage = '5';
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
         vm.bankName = $rootScope.globals.currentUser.bankName;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.currentDate = new Date();
        vm.currentDate = $filter('date')(vm.currentDate, 'dd/MM/yyyy');
        vm.getAppendixVIIBrokHand = getAppendixVIIBrokHand;
        vm.getModuleName=getModuleName;
        vm.dateValidation=dateValidation;
        vm.alert=alert;

        if ($state.current.name == 'rbibond_appendixVII_brokerage_handling_scroll.inquiry_details')
        {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.fromDate = $stateParams.obj.fromDate;
        vm.toDate = $stateParams.obj.toDate;
                     getAppendixVIIBrokHand();

        }


        function configDataTable() {
            vm.getTitleDescription = {
                title: 'Appendix VII - Statement of Charges for Brokerage and Handling Commission',
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
            rbibondAppendixVIIBrokerageService.getRbiBondModuleNameDetails(requestData).then(function (response) {
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
            function getAppendixVIIBrokHand(){
                          vm.loader = true;
                          var requestData = {
                              'schemeCode':vm.schemeCode,
                              'schemeName' :vm.schemeName,
                               'fromDate' :moment(vm.fromDate).format("DD/MMM/YYYY"),
                                'toDate'  :moment(vm.toDate).format("DD/MMM/YYYY"),
                          }

                          rbibondAppendixVIIBrokerageService.getDetails(requestData).then(function(response) {
                              vm.loader = false;

                               vm.model =response.data.result.list;
                               vm.BrokeAmtAlPaidTotal=response.data.result.BrokeAmtAlPaidTotal;
                               vm.BrokeAmtPaidTotal=response.data.result.BrokeAmtPaidTotal;
                               vm.HandlingCommTotal =response.data.result.HandlingCommTotal;
                               vm.TotalAmt=response.data.result.TotalAmt;

                      configDataTable();
                 }, function(error){

                            vm.loader = false;
                            if (error.data.message == 'No Data Found') {
                                vm.noDataFound = error.data.message;
                            } else {
                                handleErrorResponse(error);
                                $state.go('rbibond_appendixVII_brokerage_handling_scroll.inquiry')
                            }
                        })
             }

              function dateValidation() {
                              vm.toDate = '';

                               // Convert to Date object (if fromDate is string)
                                                                   var from = new Date(vm.fromDate);

                                                                   // Last day of the same month as fromDate
                                                                   var lastDayOfMonth = new Date(
                                                                       from.getFullYear(),
                                                                       from.getMonth() + 1,
                                                                       0
                                                                   );

                              vm.dateToOptions = {
                                  minDate: vm.from,
                                  maxDate: lastDayOfMonth
                              };
                              vm.dateOptions = {
                               maxDate:convertToDateObject(vm.currentDate)
                              }


                          }


	}
})();