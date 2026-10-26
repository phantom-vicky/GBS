(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'rbibondNriAccountDetailsInqController',
			rbibondNriAccountDetailsInqController);

	rbibondNriAccountDetailsInqController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','rbibondNriAccountDetailsInqService', 'commonService' ];

	function rbibondNriAccountDetailsInqController($scope, $filter, $state,
			$stateParams, $location, $rootScope, rbibondNriAccountDetailsInqService,
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
        vm.getModuleName=getModuleName;
        vm.getNriAccDetails = getNriAccDetails;
        vm.alert=alert;

if($state.current.name == 'rbibond_nri_account_details_inquiry_report.inquiry_detail'){
          vm.title = 'NRI Account Details';
             if($stateParams.obj){
                 vm.schemeCode = $stateParams.obj.schemeCode;
                 vm.schemeName = $stateParams.obj.schemeName;
                 vm.getNriAccDetails();
              }
}
        function configDataTable() {
                    vm.getTitleDescription = {
                        title: 'NRI Account Details',
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
            rbibondNriAccountDetailsInqService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                vm.loader = false;
                    vm.schemeNameList = response.data.result;
                    angular.element('#levelNameModal').modal('toggle');
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


         function getNriAccDetails(){
                             vm.loader = true;

                           var requestData = {
                           'schemeCode': vm.schemeCode,
                           }

                        rbibondNriAccountDetailsInqService.getNriAccDetails(requestData).then(function(response) {
                         vm.loader = false

                         vm.nriDetList = response.data.result;
                          configDataTable();

                       }, function(error){
                          vm.loader = false;
                           if(error.data.message == 'No Data Found' | error.data.message == 'NDF'){
                           vm.noDataFound = error.data.message;
                           }
                           else{
                           handleErrorResponse(error);
                           $state.go('rbiBond_bond_inquiry.inquiry');
                           }

                         })
                         }

	}
})();