(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'loginLogoutReportController',
			loginLogoutReportController);

	loginLogoutReportController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', '$rootScope','loginLogoutReportService', 'commonService' ];

	function loginLogoutReportController($scope, $filter, $state,
			$stateParams, $location, $rootScope, loginLogoutReportService,
			commonService) {

		var vm = this;
		vm.dateValidation = dateValidation;
        vm.currentDate = new Date();


        if ($state.current.name == 'login_logout_report.inquiry_details') {
            if($stateParams.obj){
                    vm.fromDate = $filter('date')($stateParams.obj.fromDate, 'dd-MMM-yyyy');
                    vm.toDate = $filter('date')($stateParams.obj.toDate, 'dd-MMM-yyyy');
                    if(vm.fromDate && vm.toDate){
                        getDetails();
                    }else{
                        $state.go('login_logout_report.inquiry')
                    }
            }else{
                $state.go('login_logout_report.inquiry')
            }

        }



        function getDetails() {
            vm.loader = true;
            var requestData = {
                'fromDate' : vm.fromDate,
                'toDate' : vm.toDate
            }
            loginLogoutReportService.getDetails(requestData)
                .then(
                    function(response) {
                        vm.resultSetList = response.data.result;
                        configDataTable();
                        vm.loader = false;
                    }, function(error){
                        vm.loader = false;
                        handleErrorResponse(error);
                        $state.go('login_logout_report.inquiry');
                    }
                );
        }




        function configDataTable() {
            vm.getTitleDescription = {
                title: 'Login & Logout Report',
                solId: $rootScope.globals.currentUser.solId,
                solDesc: $rootScope.globals.currentUser.solDesc
            }
            vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
        }



        function dateValidation() {
                         vm.toDate = '';
                         vm.dateToOptions = {
                             minDate: vm.fromDate,
                             maxDate: vm.currentDate,
                             //maxDate: (vm.currentDate < moment(vm.fromDate).endOf('month').toDate())?(vm.currentDate) : (moment(vm.fromDate).add(1, 'M').toDate()),
                         };
                          vm.dateOptions = {
                             maxDate: vm.currentDate,
                         }
        }





	}
})();