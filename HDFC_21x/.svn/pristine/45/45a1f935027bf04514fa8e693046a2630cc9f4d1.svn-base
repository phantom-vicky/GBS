(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'unverifiedRecordReportCbController',
			unverifiedRecordReportCbController);

	unverifiedRecordReportCbController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','unverifiedRecordReportCbService', 'commonService' ];

	function unverifiedRecordReportCbController($scope, $filter, $state,
			$stateParams, $location, $rootScope, unverifiedRecordReportCbService,
			commonService) {

		var vm = this;

        vm.currentPage = 1;
        vm.itemsPerPage = '5';
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.schemeName=$rootScope.globals.currentUser.schemeName;
        vm.systemDate = formatDate(vm.solDate);
        vm.getModuleName=getModuleName;
        vm.currentDate = formatDate(new Date());
        vm.reportDetails = reportDetails;

       function configDataTable(schemeName) {
                        var tableDesc =vm.schemeName;

                           vm.getTitleDescription = {

                               solId: $rootScope.globals.currentUser.solId,
                               solDesc: $rootScope.globals.currentUser.solDesc,
                               title: 'Report on Un-verified Records on ' + vm.currentDate,
                                tableDesc: tableDesc                    }


                           vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
                       }


         if ($state.current.name == 'unverified_records_for_rbi_bond_cb.inquiry_detail') {

                if($stateParams.obj){
                    vm.schemeName= $stateParams.obj.schemeName,
                     vm.currentDate= $stateParams.obj.currentDate;
                     vm.createdBy= $stateParams.obj.createdBy;
					 reportDetails('detailsPage');
                   // return true;
                   // }
            }else {
                $state.go('unverified_records_for_rbi_bond_cb.inquiry');
            }
        }

		 /*Get Certificate Receipt Details*/
        function reportDetails(flag){
        vm.loader = true;
        if(flag == 'branchId'){
           var requestData = {
               'loginSolId' : $rootScope.globals.currentUser.solId,
               'operationFlag' : 'B',
               'createdBy':vm.createdBy
           };
        }
        else if(flag == 'createdModifiedBy'){
            var requestData = {
               'loginSolId' : $rootScope.globals.currentUser.solId,
               'branchId' : vm.branchId,
               'operationFlag' : 'C'
            };
        }
        else if(flag == 'detailsPage'){
            var requestData = {
               'schemeName':vm.schemeName,
                'loginSolId' : $rootScope.globals.currentUser.solId,
               'createdOrModifiedBy' : vm.createdBy,
               'operationFlag' : 'R'
            };
        }
        unverifiedRecordReportCbService.reportDetails(requestData).then(function (response){
             vm.loader = false;
             var createdModifiedBy =' ';
                if(flag == 'branchId'){
                  angular.element('#branchIdModel').modal('toggle');
                  vm.branchIdList = response.data.result;
                }
                else if(flag == 'createdModifiedBy'){
                  angular.element('#createdModifiedByModel').modal('toggle');
                  vm.createdModifiedByList = response.data.result;
                  angular.forEach(vm.createdModifiedByList, function(item) {
                            if(item.modifiedBy != null){
                                createdModifiedBy = item.createdBy + '/' + item.modifiedBy;
                                item["createdModifiedBy"] = createdModifiedBy;
                            }
                            else if(item.modifiedBy == null){
                                createdModifiedBy = item.createdBy;
                                item["createdModifiedBy"] = createdModifiedBy;
                            }

                  });
                }
                else if(flag == 'detailsPage'){
                    vm.reportDetailsList = response.data.result;
                      if(vm.reportDetailsList.length ==  0){
                             vm.errorMessage = 'No records Found';
                             Swal.fire("Error!", vm.errorMessage, "error");
                             $state.go("unverified_records_for_rbi_bond_cb.inquiry");
                             vm.loader = false;

                      }
                     angular.forEach(vm.reportDetailsList, function(item) {
                        item.dateOfCreationOrModification = getDateWithoutTime(item.dateOfCreationOrModification);
                     });
                }
                configDataTable();
        },function (error){
                handleErrorResponse(error);
                vm.loader = false;
                $state.go("unverified_records_for_rbi_bond_cb.inquiry");
        });

        }
        function formatDate(tempDate){
          return $filter('date')(tempDate, "dd/MM/yyyy");
        }

        function getModuleName() {
                                vm.referenceId = 'RBI Bond';
                                var requestData = {
                                    'referenceId': vm.referenceId
                                }
                                unverifiedRecordReportCbService.getRbiBondModuleNameDetails(requestData).then(function(response) {
                                    vm.schemeList = response.data.result;
                                }, function(error) {
                                    handleErrorResponse(error);
                                });
                            }


	}
})();