(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'userActivityReportController',
			userActivityReportController);

	userActivityReportController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','userActivityReportService', 'commonService' ];

	function userActivityReportController($scope, $filter, $state,
			$stateParams, $location, $rootScope, userActivityReportService,
			commonService) {

		var vm = this;

        vm.currentPage = 1;
        vm.itemsPerPage = '5';
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.getblaStatusReport = getblaStatusReport;
        vm.getModuleName=getModuleName;
          vm.getModuleNameDetails=getModuleNameDetails;

              vm.loginSolId = $rootScope.globals.currentUser.solId;
              vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;

        vm.printpage = printpage;
         vm.currentDate = new Date();

//             vm.goPreviousPage = goPreviousPage;
//                  vm.goNextPage = goNextPage;
                  vm.paginationControls = paginationControls;
                  vm.showNext = false;
                  vm.showPrevious = false;

                  $scope.sortReverse = false;
                  $scope.sort = function(key){
                  $scope.sortKey = key;
                  $scope.sortReverse = ($scope.sortKey == key) ? !$scope.sortReverse : $scope.sortReverse;
                  $scope.sortReverse ? vm.sortOrder = 'DESC' : vm.sortOrder = 'ASC';
                  vm.sortField = key;
                  vm.getTrialRunEligibleAccountsDetails();
                  }
//         vm.currentDate = $filter('date')(vm.currentDate, 'dd/MM/yyyy');
        vm.dateValidation =dateValidation;
      vm.getModeofHolding = getModeofHolding;
         if ($state.current.name == 'User_Activity_Report.inquiry_details')
                {
                vm.branchId = $stateParams.obj.branchId;
                vm.branchDesc=$stateParams.obj.branchDesc
                vm.fromDate = $stateParams.obj.fromDate;
                vm.toDate = $stateParams.obj.Todate;
                 vm.username = $stateParams.obj.username;
                   vm.moduleId = $stateParams.obj.moduleId;

                   vm.getblaStatusReport();


//                getModeofHolding();
//                 getblaStatusReport();

                }

//else {
//                $state.go('User_Activity_Report.inquiry_details');
//            }

        function configDataTable() {
                    vm.getTitleDescription = {
                        title: 'User Activity Report',
                        solId: $rootScope.globals.currentUser.solId,
                        solDesc: $rootScope.globals.currentUser.solDesc
                    }
                    vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
                }


            function paginationControls () {

                 vm.pages = [];
                 vm.maxPages = parseInt(vm.pageDetails.maxPages);

                 for(var i=1; i<= parseInt(vm.pageDetails.maxPages); i++){
                    vm.pages.push(parseInt(i));
                  }

                 if(vm.pageNo != 1){
                     vm.showPrevious = true;
                 }
                 else{
                     vm.showPrevious = false;
                 }

                 if(vm.pageNo != vm.maxPages){
                      vm.showNext = true;
                 }
                 else{
                      vm.showNext = false;
                 }


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
            userActivityReportService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                vm.loader = false;
                vm.schemeList = response.data.result;
            }, function (error) {
                handleErrorResponse(error);
                vm.loader = false;
            });
        }

configDataTable();

         function getblaStatusReport(){
                       vm.loader = true;

                                  var requestData = {
                                  				'p_user_sol_id_in':vm.branchId,
                                                'p_user_name_in': vm.branchDesc,
                                                'p_from_date_in': vm.fromDate,
                                                'p_to_date_in': vm.toDate,
                                                'username': vm.username,
                                                'p_module_id_in': vm.moduleId,
                                                'pageSize' : vm.pageSize,
                                                               'pageNo' : vm.pageNo,
                                                               'searchValue' : vm.searchValue,
                                                               'sortOrder' : vm.sortOrder,
                                                               'sortField' : vm.sortField,
                                                               'isTorch' : 'N',

                                  			}

                                  userActivityReportService.getDetails(requestData).then(function(response) {
                                      vm.loader = false
                                 vm.model =response.data.result.userActivityList;
                                  vm.total =response.data.result.total;
                                  vm.paginationControls();


//                                 vm.modele =response.data.result[0].subcriptionamount;
                              configDataTable();
                         }, function(error){
                             vm.loader = false;
                             if(error.data.message == 'No Data Found' | error.data.message == 'NDF'){
                                   vm.noDataFound = error.data.message;
                             }else{
                               handleErrorResponse(error);
                               $state.go('User_Activity_Report.inquiry');
                             }

                         })
                     }



                        function getModeofHolding() {
                                var requestData = {
                                      'referenceId': 'RBI_SUB_STATUS'
                                  }
                                userActivityReportService.getReferenceData(requestData).then(function(response){
                                   vm.modeOfHoldingList = response.data.result;
                                 },function(error) {
                                    handleErrorResponse(error);
                                  });
                             }




                              function getModuleNameDetails() {
                                         vm.loader = true;

                                         var requestData = {
                                            'todate' :moment(vm.todate).format("DD/MM/YYYY"),
                                               'fromdate' :moment(vm.fromdate).format("DD/MM/YYYY"),
                                               'bondScbId':vm.moduleId


                                         }
                                         userActivityReportService.getSubcriptionDetails(requestData).then(function(response) {
                                             vm.loader = false;
                                             vm.subscriptionIdList = response.data.result;
                                             angular.element('#subScriptionIdModal').modal('toggle');
                                         }, function(error) {
                                             handleErrorResponse(error);
                                             vm.loader = false;
                                         });
                                     }
  function dateValidation() {
//                                      vm.toDate = '';
//
//                                      vm.dateToOptions = {
//                                          minDate: vm.fromDate,
//                                          maxDate:vm.solDate
//                                      };
//                                      vm.dateOptions = {
//                                       maxDate:vm.solDate
//                                      }


                                  }






	}
})();