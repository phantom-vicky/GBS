(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'rbibondLedgerNameSearchController',
			rbibondLedgerNameSearchController);

	rbibondLedgerNameSearchController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','rbibondLedgerNameSearchService', 'commonService' ];

	function rbibondLedgerNameSearchController($scope, $filter, $state,
			$stateParams, $location, $rootScope, rbibondLedgerNameSearchService,
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
        vm.getNameSearchReport = getNameSearchReport;
        vm.getModuleName=getModuleName;
        vm.getBlaAccNo = getBlaAccNo;
        vm.alert = alert;

         if ($state.current.name == 'rbibond_ledger_inquiry_name_search_report.inquiry_details')
                {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.blaAccNo= $stateParams.obj.blaNo;
                vm.bondFirstName= $stateParams.obj.firstNameHolder;
                vm.bondMiddleName= $stateParams.obj.middleNameHolder;
               vm.bondLastName= $stateParams.obj.lastNameHolder;
                vm.address= $stateParams.obj.address;
                vm.schemeName= $stateParams.obj.schemeName;
                             getNameSearchReport();

                }
                vm.modeOfHoldingData = [
                                        {label : "Single",value : "MH001"},
                                        {label : "Joint - Jointly",value : "MH002"},
                                        {label : "Joint - Anyone or Survivor",value : "MH003"},
                                        {label : "HUF",value : "MH004"},
                                        {label : "Legal Guardian",value : "MH005"},
                                    ];
             vm.holderTypeData = [
                                    {label : "Power of Attorney",value : "HT001"},
                                    {label : "Karta of HUF",value : "HT002"},
                                    {label : "Individual",value : "HT003"},
                                ];


        function configDataTable() {
                    vm.getTitleDescription = {
                        title: 'Bond Ledger Inquiry – Name Search',
                        solId: $rootScope.globals.currentUser.solId,
                        solDesc: $rootScope.globals.currentUser.solDesc
                    }
                    vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
                }
       function alert(item){

                                         for (let i = 0;i<vm.schemeList.length;i++){
                                          if(vm.schemeList[i].subCode == item){
                                                vm.schemeName = vm.schemeList[i].subCodeDescription;

                                           }
                                         }
                                      }

 function getModuleName() {
            vm.loader = true;
            vm.referenceId = 'TAX';
            var requestData = {
                'referenceId': vm.referenceId
            };
            rbibondLedgerNameSearchService.getRbiBondModuleNameDetails(requestData).then(function (response) {
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
                                                        'isTorch' : 'Y',
                                                        'schemeCode': vm.schemeCode


                                                    }
                                                    rbibondLedgerNameSearchService.getDetails(requestData).then(function(response) {
                                                        vm.loader = false;
                                                        vm.blaAccNoList = response.data.result;
                                                        //                       vm.tocSlabListEffctDate=response.data.result.effeFromDateList;

                                                        angular.element('#blaAccNoModal').modal('toggle');
                                                    }, function(error) {
                                                        handleErrorResponse(error);
                                                        vm.loader = false;
                                                    });
                                                }
         function getNameSearchReport(){
                       vm.loader = true;
                                  var requestData = {
                                      'schemeCode':vm.schemeCode,
                                       'blaNo':vm.blaAccNo,
                                      'firstNameHolder':vm.bondFirstName,
                                      'middleNameHolder' :vm.bondMiddleName,
                                      'lastNameHolder' :vm.bondLastName,
                                      'address':vm.address,
                                      'schemeName':vm.schemeName,
                                       'isTorch' : 'N',

                                  }

                                  rbibondLedgerNameSearchService.getDetails(requestData).then(function(response) {
                                      vm.loader = false;

                                       vm.model =response.data.result;

                              configDataTable();
                         }, function(error){
                             vm.loader = false;
                                                         if(error.data.message == 'No Data Found' | error.data.message == 'NDF'){
                                                               vm.noDataFound = error.data.message;
                                                         }else{
                                                           handleErrorResponse(error);
                                                           $state.go('rbibond_ledger_inquiry_name_search_report.inquiry');
                                                         }

                         })
                     }

	}
})();