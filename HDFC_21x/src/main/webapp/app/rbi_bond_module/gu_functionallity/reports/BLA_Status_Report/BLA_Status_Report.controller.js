(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'blaStatusReportController',
			blaStatusReportController);

	blaStatusReportController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','blaStatusReportService', 'commonService' ];

	function blaStatusReportController($scope, $filter, $state,
			$stateParams, $location, $rootScope, blaStatusReportService,
			commonService) {

		var vm = this;

        vm.currentPage = 1;
        vm.itemsPerPage = '5';
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.getblaStatusReport = getblaStatusReport;
        vm.getModuleName=getModuleName;
        vm.printpage = printpage;
         vm.currentDate = new Date();


vm.textableDatePicker=textableDatePicker;
vm.autoslashing=autoslashing;

function textableDatePicker(id,valueDate)
{
 commonService.textableDatePicker(id,valueDate);
}
function autoslashing(id)
{
 commonService.autoslashing(id);
}




         vm.currentDate = $filter('date')(vm.currentDate, 'dd/MM/yyyy');
        vm.dateValidation =dateValidation;
      vm.getModeofHolding = getModeofHolding;
         if ($state.current.name == 'BLA_Status_Report.inquiry_details')
                {
                vm.subscriptionStatus = $stateParams.obj.subscriptionStatus;
                vm.fromDate = $stateParams.obj.fromDate;
                vm.toDate = $stateParams.obj.Todate;
                 vm.schemeCode = $stateParams.obj.schemeCode;
                   vm.schemeName = $stateParams.obj.schemeName;


//                getModeofHolding();
                 getblaStatusReport();

                }


        function configDataTable() {
                    vm.getTitleDescription = {
                        title: 'BLA Status Report',
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
            blaStatusReportService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                vm.loader = false;
                vm.schemeList = response.data.result;
            }, function (error) {
                handleErrorResponse(error);
                vm.loader = false;
            });
        }



         function getblaStatusReport(){
                       vm.loader = true;

                                  var requestData = {
//
                                  			'fromDate' : moment(vm.fromDate).format("DD/MM/YYYY"),
                                  	         'toDate' : moment(vm.toDate).format("DD/MM/YYYY"),
                                  				'p_sub_sts_in':vm.subscriptionStatus,
                                                'schemeCode': vm.schemeCode
                                  			}

                                  blaStatusReportService.getDetails(requestData).then(function(response) {
                                      vm.loader = false
                                 vm.model =response.data.result.blaStatusReportDTOList;
                                  vm.total =response.data.result.total;

//                                 vm.modele =response.data.result[0].subcriptionamount;
                              configDataTable();
                         }, function(error){
                             vm.loader = false;
                             if(error.data.message == 'No Data Found' | error.data.message == 'NDF'){
                                   vm.noDataFound = error.data.message;
                             }else{
                               handleErrorResponse(error);
                               $state.go('BLA_Status_Report.inquiry');
                             }

                         })
                     }



                        function getModeofHolding() {
                                var requestData = {
                                      'referenceId': 'RBI_SUB_STATUS'
                                  }
                                blaStatusReportService.getReferenceData(requestData).then(function(response){
                                   vm.modeOfHoldingList = response.data.result;
                                 },function(error) {
                                    handleErrorResponse(error);
                                  });
                             }
  function dateValidation() {
                                      vm.toDate = '';

                                      vm.dateToOptions = {
                                          minDate: vm.fromDate,
                                          maxDate:vm.solDate
                                      };
                                      vm.dateOptions = {
                                       maxDate:vm.solDate
                                      }


                                  }






	}
})();