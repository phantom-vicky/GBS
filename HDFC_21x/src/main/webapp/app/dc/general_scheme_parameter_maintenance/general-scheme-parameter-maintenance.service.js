(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('generalSchemeParameterMaintenanceService', generalSchemeParameterMaintenanceService);

    generalSchemeParameterMaintenanceService.$inject = ['$http'];

    function generalSchemeParameterMaintenanceService($http) {
    	
    	var generalSchemeParameterMaintenanceService = this;
    	
    	generalSchemeParameterMaintenanceService.getSchemeTypes		= getSchemeTypes;
    	generalSchemeParameterMaintenanceService.getSchemeDetails	= getSchemeDetails;
    	generalSchemeParameterMaintenanceService.verify				= verify;
    	generalSchemeParameterMaintenanceService.modify				= modify;
    	generalSchemeParameterMaintenanceService.getAccountDetails	= getAccountDetails;
    	
    	function getSchemeTypes() {
			return $http({
				method : "get",
				url : 'gbmultra/dc/general_scheme_parameter_maintenance/schemedetails/schemetypes',
				params : {}
			});
		}
    	
    	function getSchemeDetails(schemeType){
    		return $http({
				method : "get",
				url : 'gbmultra/dc/general_scheme_parameter_maintenance/schemedetails/' + schemeType,
				params : {}
			});
    	}
    	
    	function getAccountDetails(){
    		return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/clearingaccount',
				data : {accountNumber : ""}
			});
    	}
    	
    	function verify(data) {
            var parameter = JSON.stringify(data);

            return $http({
                method: "post",
                url: 'gbmultra/dc/general_scheme_parameter_maintenance/verify',
                headers: {
                    'Content-type': 'application/json;charset=utf-8'
                },
                params: {}, data: parameter
            });
        }
    	

    	function modify(data) {
            var parameter = JSON.stringify(data);

            return $http({
                method: "post",
                url: 'gbmultra/dc/general_scheme_parameter_maintenance/schemedetails',
                headers: {
                    'Content-type': 'application/json;charset=utf-8'
                },
                params: {}, data: parameter
            });
        }
        
    }
    
})();