(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('cbsGeneralSetupService', cbsGeneralSetupService);

    cbsGeneralSetupService.$inject = ['$http'];

    function cbsGeneralSetupService($http) {
    	
    	var cbsGeneralSetupService = this;
    	cbsGeneralSetupService.verify = verify;
    	cbsGeneralSetupService.modify = modify;
    	cbsGeneralSetupService.getcbsGeneralSetup = getcbsGeneralSetup;
    	
    	
    	function modify(data) {
    		var parameter = JSON.stringify(data);
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/generalsetup/update',
				params : {},
				data : parameter,
				headers : {
					'Content-type' : 'application/json;charset=utf-8'
				}
			});
		}
		
		function verify(data) {
			var parameter = JSON.stringify(data);
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/generalsetup/verify',
				params : {},
				data : parameter,
				headers : {
					'Content-type' : 'application/json;charset=utf-8'
				}
			});
		}

		function getcbsGeneralSetup() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/generalsetup',
				data : {}
			});
		}

        
    }
    
})();