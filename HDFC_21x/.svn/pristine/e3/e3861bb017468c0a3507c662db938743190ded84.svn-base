(function() {

	'use strict';

	angular.module('gbmUltraApp').controller('generalSchemeParameterMaintenanceController',
			generalSchemeParameterMaintenanceController);
	
	  
	    
	generalSchemeParameterMaintenanceController.$inject = [ '$scope', '$filter', '$state',
			'$stateParams', '$location', 'generalSchemeParameterMaintenanceService', 'commonService' ];

	function generalSchemeParameterMaintenanceController($scope, $filter, $state, $stateParams,
			$location, generalSchemeParameterMaintenanceService, commonService) {

		var vm = this;
		
		vm.generalSchemeParameterMaintenance = {};
		vm.modify					= modify;
		vm.showCancelConfirmation	= showCancelConfirmation;
		vm.getSchemeTypes			= getSchemeTypes;
		vm.getSchemeDetails			= getSchemeDetails;
		vm.verify					= verify;
		vm.getAccountDetails		= getAccountDetails;
		vm.setAccountDetails		= setAccountDetails;
		vm.validateFinancialYear	= validateFinancialYear;
		vm.validateBudgetYear		= validateBudgetYear;
		
		vm.validFinancialYear = true;
		vm.validBudgetYear = true;
		
		 if ($state.current.name == 'general_scheme_parameter_maintenance.modify_form') {
        	if($stateParams.obj){
        		vm.schemeTypeInput = $stateParams.obj.schemeType;
    			if (vm.schemeTypeInput) {
    				getSchemeDetails();
    			}
        	} else {
        		$state.go('general_scheme_parameter_maintenance.modify');
        	}
        } else if ($state.current.name == 'general_scheme_parameter_maintenance.verify_detail'){
        	if($stateParams.obj){
        		vm.schemeTypeInput = $stateParams.obj.schemeType;
    			if (vm.schemeTypeInput) {
    				getSchemeDetails();
    			}
        	} else {
        		$state.go('general_scheme_parameter_maintenance.verify');
        	}
        }
		
		// Get list of Scheme Types
		function getSchemeTypes(){
			vm.loader = true;
			generalSchemeParameterMaintenanceService.getSchemeTypes()
				.then(function(response) {
					vm.schemeTypeList = response.data;
					vm.loader = false;
				}, handleErrorResponse);
		}
		
		function getAccountDetails(id){
			vm.searchInput = '';
			vm.fieldID = id;
			vm.loader = true;
			generalSchemeParameterMaintenanceService.getAccountDetails()
				.then(function(response) {
					vm.accountList = response.data;
					vm.loader = false;
				}, handleErrorResponse);
		}
		
		function setAccountDetails(r){
			vm.schemeType[vm.fieldID] = r.bankAccountId;
    		vm.schemeDescription[vm.fieldID] = r.bankAccountIdDescription;
    		angular.element('#accountSearchModal').modal('hide');
    	}
		
		// Get values for selected scheme type 
		function getSchemeDetails(){
			vm.schemeType = {};
			vm.schemeTypeNew = {};
			vm.schemeDescription = {};
			vm.schemeTypeResponse = {};
			vm.newValueDisplay = false;
			if(vm.schemeTypeInput){
				vm.loader = true;
				generalSchemeParameterMaintenanceService.getSchemeDetails(vm.schemeTypeInput)
				.then(function(response) {
					vm.schemeTypeResponse = response.data;
					
					angular.forEach(response.data, function(key){
						vm.schemeType[key.dto.paramName.toLowerCase()] = key.dto.paramValue;
						vm.schemeDescription[key.dto.paramName.toLowerCase()] = key.dto.accountTitle;
						
						if(key.auditLogDto!=null){vm.newValueDispay = true;
						vm.schemeType[key.dto.paramName.toLowerCase()] = key.auditLogDto.modifiedFieldsData[0].newValue;
						vm.schemeTypeNew[key.dto.paramName.toLowerCase()] = key.auditLogDto.modifiedFieldsData[0].oldValue;
						console.log('data to diplay in console' +key.auditLogDto.modifiedFieldsData[0].oldValue);
						}
						else
							{vm.schemeTypeNew[key.dto.paramName.toLowerCase()] = key.dto.paramValue;}
					});
					
					if(vm.schemeTypeInput == '0000'){
						if(vm.schemeType.finyrstdate){
							var finyrstdate = vm.schemeType.finyrstdate.split('/');
							vm.financialYearStartDate = parseInt(finyrstdate[0]);
							vm.financialYearStartMonth = parseInt(finyrstdate[1]);
						}
						if(vm.schemeType.finyrenddate){
							var finyrenddate = vm.schemeType.finyrenddate.split('/');
							vm.financialYearEndDate = parseInt(finyrenddate[0]);
							vm.financialYearEndMonth = parseInt(finyrenddate[1]);
						}
						if(vm.schemeType.budyrstdate){
							var budyrstdate = vm.schemeType.budyrstdate.split('/');
							vm.budgetStartDate = parseInt(budyrstdate[0]);
							vm.budgetStartMonth = parseInt(budyrstdate[1]);
						}
						if(vm.schemeType.budyrenddate){
							var budyrenddate = vm.schemeType.budyrenddate.split('/');
							vm.budgetEndDate = parseInt(budyrenddate[0]);
							vm.budgetEndMonth = parseInt(budyrenddate[1]);
						}
					} else if(vm.schemeTypeInput == '0004'){
						if(vm.schemeType.resyrstdate){
							var resyrstdate = vm.schemeType.resyrstdate.split('/');
							vm.residualYearStartDate = parseInt(resyrstdate[0]);
							vm.residualYearStartMonth = parseInt(resyrstdate[1]);
						}
						if(vm.schemeType.resyrenddate){
							var resyrenddate = vm.schemeType.resyrenddate.split('/');
							vm.residualYearEndDate = parseInt(resyrenddate[0]);
							vm.residualYearEndMonth = parseInt(resyrenddate[1]);
						}
					} else if(vm.schemeTypeInput == '0005'){
						if(vm.schemeType.resyrstdate){
							var resyrsstdate = vm.schemeType.resyrstdate.split('/');
							vm.residualYearStartDate = parseInt(resyrsstdate[0]);
							vm.residualYearStartMonth = parseInt(resyrsstdate[1]);
						}
						if(vm.schemeType.resyrstdate){
							var resyrstdate = vm.schemeType.resyrenddate.split('/');
							vm.residualYearEndDate = parseInt(resyrstdate[0]);
							vm.residualYearEndMonth = parseInt(resyrstdate[1]);
						}
					}
					
					vm.loader = false;
				}, handleErrorResponse);
			}
		}
		
		
		//modify
		function modify() {
//			Swal.fire({
//				title : "Are you sure?",
//				text : "Are you sure want to Update? ",
//				type : "warning",
//				showCancelButton : true,
//				cancelButtonClass : "btn-cancel",
//				confirmButtonClass : "btn-primary",
//				confirmButtonText : "Yes!",
//				closeOnConfirm : true,
//				showLoaderOnConfirm : true
//			}, function() {

Swal.fire({
  title: "Are you sure?",
  text: "Are you sure want to update?",
  icon: "warning", // use "icon", not "type"
  showCancelButton: true,
  confirmButtonText: "Yes",
  cancelButtonText: "Cancel",
  customClass: {
    confirmButton: "btn-primary",
    cancelButton: "btn-cancel"
  },
  buttonsStyling: false
}).then((result) => {

       if (result.isConfirmed) {
				vm.loader = true;
				if(vm.schemeTypeInput == '0000'){
					vm.schemeType.finyrstdate = vm.financialYearStartDate + "/" + vm.financialYearStartMonth;
					vm.schemeType.finyrenddate = vm.financialYearEndDate + "/" + vm.financialYearEndMonth;
					vm.schemeType.budyrstdate = vm.budgetStartDate + "/" + vm.budgetStartMonth;
					vm.schemeType.budyrenddate = vm.budgetEndDate + "/" + vm.budgetEndMonth;
				} else if(vm.schemeTypeInput == '0004' || vm.schemeTypeInput == '0005'){
					vm.schemeType.resyrstdate = vm.residualYearStartDate + "/" + vm.residualYearStartMonth;
					vm.schemeType.resyrenddate = vm.residualYearEndDate + "/" + vm.residualYearEndMonth;
				} /*else if(vm.schemeTypeInput == '0005'){
					vm.schemeType.resyrstdate = vm.residualYearStartDate + "/" + vm.residualYearStartMonth;
					vm.schemeType.resyrenddate = vm.residualYearEndDate + "/" + vm.residualYearEndMonth;
				}*/
				
				var formData = [];
				
				angular.forEach(vm.schemeTypeResponse, function(response){
					angular.forEach(vm.schemeType, function(value, key){
						if(response.dto.paramName.toLowerCase() == key){
							response.dto.paramValue = value;
							var res={};
							res.dto=response.dto;
							res.auditLogDto = null;
							//formData.push(response);
							formData.push(res);
						}
					});
				});
				
				generalSchemeParameterMaintenanceService.modify(formData)
				.then(function(response){
					Swal.fire("Modified!", response.data.message, "success");
					location.href = '#!/general_scheme_parameter_maintenance/action/inquiry';
					vm.loader = false;
				}, handleErrorResponse);
			}
			});
		}
		
		// Verify 
		function verify(){
//			Swal.fire({
//				title : "Are you sure?",
//				text : "Are you sure want to Verify? ",
//				type : "warning",
//				showCancelButton : true,
//				cancelButtonClass : "btn-cancel",
//				confirmButtonClass : "btn-primary",
//				confirmButtonText : "Yes!",
//				closeOnConfirm : true,
//				showLoaderOnConfirm : true
//			}, function() {

Swal.fire({
  title: "Are you sure?",
  text: "Are You Sure Want To Verify?",
  icon: "warning", // use "icon", not "type"
  showCancelButton: true,
  confirmButtonText: "Yes",
  cancelButtonText: "Cancel",
  customClass: {
    confirmButton: "btn-primary",
    cancelButton: "btn-cancel"
  },
  buttonsStyling: false
}).then((result) => {

       if (result.isConfirmed) {
				vm.loader = true;
				generalSchemeParameterMaintenanceService.verify(vm.schemeTypeResponse)
				.then(function(response){
					Swal.fire("Verified!", response.data.message, "success");
					location.href = '#!/general_scheme_parameter_maintenance/action/inquiry';
					vm.loader = false;
				}, handleErrorResponse);
		}	});
		}
		
		// validate financial year not same as the start period
		function validateFinancialYear(dateId){
			var financialYearStart = vm.financialYearStartDate + '/' + vm.financialYearStartMonth;
			var financialYearEnd = vm.financialYearEndDate + '/' + vm.financialYearEndMonth;
			if(financialYearStart == financialYearEnd){
				vm.validFinancialYear = false;
				angular.element("#"+dateId).addClass('ng-invalid').focus();
			} else {
				vm.validFinancialYear = true;
				angular.element("#financialYearStartDdate, #financialYearEndDate").removeClass('ng-invalid').addClass('ng-valid');
			}
		}

		// validate Budget year not same as the start period
		function validateBudgetYear(dateId){
			var budgetYearStart = vm.budgetStartDate + '/' + vm.budgetStartMonth;
			var budgetYearEnd = vm.budgetEndDate + '/' + vm.budgetEndMonth;
			if(budgetYearStart == budgetYearEnd){
				vm.validBudgetYear = false;
				angular.element("#"+dateId).addClass('ng-invalid').focus();
			} else {
				vm.validBudgetYear = true;
				angular.element("#budgetStartDate, #budgetEndDate").removeClass('ng-invalid').addClass('ng-valid');
			}
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
        
     // Handle error response
		function handleErrorResponse(response) {
			vm.loader = false;
			var msg = response.data.message ? response.data.message
					: "Server Error";
			Swal.fire("Error!", msg, "error");
		}

	}
})();