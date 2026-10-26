(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'rbibondAccHolderInqController',
			rbibondAccHolderInqController);

	rbibondAccHolderInqController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','rbibondAccHolderInqService', 'commonService' ];

	function rbibondAccHolderInqController($scope, $filter, $state,
			$stateParams, $location, $rootScope, rbibondAccHolderInqService,
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
        vm.getAccHolderInquiry = getAccHolderInquiry;
        vm.getModuleName=getModuleName;
        vm.getBlaAccNo=getBlaAccNo;
        vm.alert=alert;


         if ($state.current.name == 'rbibond_acc_holder_inquiry_report.inquiry_details')
                {
                vm.schemeCode = $stateParams.obj.schemeCode;
               vm.blaAccNo = $stateParams.obj.blaNo;
                vm.schemeName =  $stateParams.obj.schemeName;
                vm.bondHolderName =  $stateParams.obj.bondHolderName;
                vm.modeOfHolding =  $stateParams.obj.modeOfHolding;
                vm.holderType = $stateParams.obj.holderType;

                             getAccHolderInquiry();

                }


        function configDataTable() {
                    vm.getTitleDescription = {
                        title: 'Account Holder Inquiry',
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
            rbibondAccHolderInqService.getRbiBondModuleNameDetails(requestData).then(function (response) {
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

         function getBlaAccNo() {
                                    vm.loader = true;

                                    var requestData = {
                                        'schemeCode': vm.schemeCode

                                    }
                                    rbibondAccHolderInqService.getDetails(requestData).then(function(response) {
                                        vm.loader = false;
                                        vm.blaAccNoList = response.data.result;
                                        //                       vm.tocSlabListEffctDate=response.data.result.effeFromDateList;

                                        angular.element('#blaAccNoModal').modal('toggle');
                                    }, function(error) {
                                        handleErrorResponse(error);
                                        vm.loader = false;
                                    });
                                }
         function getAccHolderInquiry(){
                       vm.loader = true;
                                  var requestData = {
                                      'schemeCode':vm.schemeCode,
                                      'blaNo':vm.blaAccNo,
                                      'schemeName':vm.schemeName,
                                        'bondHolderName' : vm.bondHolderName,
                                        'modeOfHolding' : vm.modeOfHolding,
                                        'holderType':vm.holderType
                                  }

                                  rbibondAccHolderInqService.getDetails(requestData).then(function(response) {
                                      vm.loader = false;

                                       vm.model =response.data.result;

                              configDataTable();
                         }, function(error){
                                                     vm.loader = false;
                                                     if (error.data.message == 'No Data Found'){
                                                                           vm.noDataFound = error.data.message;
                                                                         } else {
                                                                    handleErrorResponse(error);
                                                              $state.go('rbibond_acc_holder_inquiry_report.inquiry')
                                                                   }
                                                          	})
                     }

	}
})();