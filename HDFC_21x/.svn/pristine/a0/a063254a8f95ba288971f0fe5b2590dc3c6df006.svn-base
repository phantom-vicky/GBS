(function() {

	'use strict';

	angular.module('gbmUltraApp').controller('populationOfSolDateController',
			populationOfSolDateController);

	populationOfSolDateController.$inject = [ '$scope', '$filter', '$state',
			'$stateParams', '$location', 'populationOfSolDateService',
			'commonService' ];

	function populationOfSolDateController($scope, $filter, $state,
			$stateParams, $location, populationOfSolDateService, commonService) {

		var vm = this;
		vm.modify = modify;
		vm.getSolDate = getSolDate;
		vm.getRecord = getRecord;
		vm.showCancelConfirmation = showCancelConfirmation;
		getRecord();

		function modify() {
			Swal.fire({
				title : "Are you sure?",
				text : "Are you sure want to modify this Sol Date? ",
				type : "warning",
				showCancelButton : true,
				cancelButtonClass : "btn-cancel",
				confirmButtonClass : "btn-primary",
				confirmButtonText : "Yes!",
				closeOnConfirm : false,
				showLoaderOnConfirm : true
			}, function() {
				var data = generateData();
				populationOfSolDateService.modify(data)
						.then(
								function(response) {
									Swal.fire("Modified!", response.data.message,
											"success");
									location.href = '#!/population_of_sol_date/action/inquiry';
								}, handleErrorResponse);
			});
		}

		// get Sol Date
		function getSolDate() {
			populationOfSolDateService
					.getpopulationOfSolDate()
					.then(
							function(response) {
								vm.cbsPopulationOfSolDate.solDate1 = new Date(response.data.result[0].solDate1);
								getDatePickerOptions(vm.cbsPopulationOfSolDate.solDate1);
							}, handleErrorResponse);
		}

		// sol Date picker options
		function getDatePickerOptions(solDate1) {
			vm.dateOptions = {
				minDate : solDate1,
				showWeeks : false
			};
		}

		   function generateData() {
                var data = {};
                angular.forEach(vm.cbsPopulationOfSolDate, function(value,
                        item) {
                    if (item == 'solDate1') {
                        var getSolDate = $filter('date')(value, "dd-MMM-yyyy");
                        /*var getsolDate = dateFormat[2] + '-' + dateFormat[1] + '-'
                                + dateFormat[0];*/
                        data[item] = getSolDate;
                    } if(item == 'addlText1' || item == 'addlText2' || item == 'addlText3'){
                        data[item] = value.toUpperCase();
                    }
                });
                return data;
         	}
		

		function getRecord() {
			populationOfSolDateService
					.getpopulationOfSolDate()
					.then(
							function(response) {
								vm.cbsPopulationOfSolDate = response.data.result[0];
								vm.cbsPopulationOfSolDate.solDate1 = new Date(response.data.result[0].solDate1);
								getDatePickerOptions(vm.cbsPopulationOfSolDate.solDate1);
							}, handleErrorResponse);
		}
		

		
		// Handle error response
		function handleErrorResponse(response) {
			vm.loader = false;
			var msg = response.data.message ? response.data.message
					: "Server Error";
			Swal.fire("Error!", msg, "error");
		}

		// Cancel Confirmation
        function showCancelConfirmation(state) {
            Swal.fire({
                    title: "Confirm Cancel",
                    text: "Do you really want to cancel?",
                    type: "warning",
                    showCancelButton: true,
                    cancelButtonClass: "btn-cancel",
                    confirmButtonClass: "btn-danger",
                    confirmButtonText: "Yes",
                    cancelButtonText: "No",
                    closeOnConfirm: true
                },
                function() {
                    $state.go(state);

                });
        }
	}
})();