(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'rbibondRegLedgerLinkCellController',
			rbibondRegLedgerLinkCellController);

	rbibondRegLedgerLinkCellController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','rbibondRegLedgerLinkCellService', 'commonService' ];

	function rbibondRegLedgerLinkCellController($scope, $filter, $state,
			$stateParams, $location, $rootScope, rbibondRegLedgerLinkCellService,
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
        vm.getRegLedgerLinkCell = getRegLedgerLinkCell;
        vm.getModuleName=getModuleName;
        vm.getBranchId=getBranchId;
        vm.dateValidation=dateValidation;
        vm.alert=alert;

         if ($state.current.name == 'rbibond_register_ledger_acc_linkcell_scroll.inquiry_details')
                {
                vm.schemeCode = $stateParams.obj.schemeCode;
                 vm.schemeName = $stateParams.obj.schemeName;
                vm.branchId = $stateParams.obj.branchId;
                vm.branchName=$stateParams.obj.branchName
                vm.fromDate = $stateParams.obj.fromDate;
                vm.toDate = $stateParams.obj.toDate;
                             getRegLedgerLinkCell();

                }


        function configDataTable() {
                    vm.getTitleDescription = {
                        title: 'Register of Bond Ledger Accounts - All holders',
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
            rbibondRegLedgerLinkCellService.getRbiBondModuleNameDetails(requestData).then(function (response) {
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
                                                                'schemeCode':vm.schemeCode


                                                           }
                                                           rbibondRegLedgerLinkCellService.getDetails(requestData).then(function(response) {
                                                               vm.loader = false;
                                                               vm.branchIdList = response.data.result;
                                                               angular.element('#branchIdModal').modal('toggle');
                                                           }, function(error) {
                                                               handleErrorResponse(error);
                                                               vm.loader = false;
                                                           });
                                                       }
         function getRegLedgerLinkCell(){
                       vm.loader = true;
                                  var requestData = {
                                      'schemeCode':vm.schemeCode,
                                      'schemeName' :vm.schemeName,
                                      'branchId' :vm.branchId,
                                      'branchName' :vm.branchName,
                                       'fromDate' :moment(vm.fromDate).format("DD-MMM-YYYY"),
                                        'toDate'  :moment(vm.toDate).format("DD-MMM-YYYY"),
                                  }

                                  rbibondRegLedgerLinkCellService.getDetails(requestData).then(function(response) {
                                      vm.loader = false;

                                       vm.model =response.data.result;
//                                       vm.mod =response.data.result.grandTotal;
//
//                                         angular.forEach(vm.mod, function(item){
//                                                           item.grandTotal = Number(item.grandTotal).toLocaleString("en-IN");
//                                            })
//                                              vm.mod = Number(vm.mod).toLocaleString("en-IN");

                              configDataTable();
                         }, function(error){
                         vm.loader = false;
                                                                    				if (error.data.message == 'No Data Found'){
                                           vm.noDataFound = error.data.message;
                                                           } else {
                                                               handleErrorResponse(error);
                                           $state.go('rbibond_register_ledger_acc_linkcell_scroll.inquiry')
                                                         }
                                                                         	})
                     }
     function dateValidation() {
                 vm.toDate = '';

                 vm.dateToOptions = {
                     minDate: vm.fromDate,
                     maxDate:convertToDateObject(vm.currentDate)
                 };
                 vm.dateOptions = {
                 maxDate : convertToDateObject(vm.currentDate)
                 }


             }

	}
})();