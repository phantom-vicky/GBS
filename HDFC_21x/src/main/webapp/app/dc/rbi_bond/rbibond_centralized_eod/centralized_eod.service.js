(function () {

	'use strict';

	angular
		.module('gbmUltraApp')
		.service('rbiBondCentralizedEodService', rbiBondCentralizedEodService);

	rbiBondCentralizedEodService.$inject = ['$http'];

	function rbiBondCentralizedEodService($http) {

		var rbiBondCentralizedEodService = this;

		rbiBondCentralizedEodService.startEodProcess = function (requestData) {
			return $http({
				method: "POST",
				url: 'gbmultra/rbiBond/centailized/Eod/startEodProcess',
				data: requestData
			});
		}

		rbiBondCentralizedEodService.getBranchId = function (requestData) {
			return $http({
				method: "post",
				url: 'gbmultra/rbiBond/centailized/Eod/getLevelBranch',
				data: requestData
			});
		}
		rbiBondCentralizedEodService.getGbmSolDate = function (requestData) {
			return $http({
				method: "post",
				url: 'gbmultra/rbiBond/centailized/Eod/getDate',
				data: requestData
			});
		}
		rbiBondCentralizedEodService.getTimeValidation = function () {
        			return $http({
        				method: "post",
        				url: 'gbmultra/rbiBond/centailized/Eod/getTimeValidation'
        			});
        		}

	}
})();