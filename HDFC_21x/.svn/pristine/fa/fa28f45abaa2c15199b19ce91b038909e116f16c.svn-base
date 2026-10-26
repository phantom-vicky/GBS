(function () {

	'use strict';

	angular
		.module('gbmUltraApp')
		.service('rbiBondCentralizedBodService', rbiBondCentralizedBodService);

	rbiBondCentralizedBodService.$inject = ['$http'];

	function rbiBondCentralizedBodService($http) {

		var rbiBondCentralizedBodService = this;

		rbiBondCentralizedBodService.startBodProcess = function (requestData) {
			return $http({
				method: "POST",
				url: 'gbmultra/rbiBond/centailized/bod/startBodProcess',
				data: requestData
			});
		}

		rbiBondCentralizedBodService.getBranchId = function (requestData) {
			return $http({
				method: "post",
				url: 'gbmultra/rbiBond/centailized/bod/getLevelBranch',
				data: requestData
			});
		}
		rbiBondCentralizedBodService.getGbmSolDate = function (requestData) {
			return $http({
				method: "post",
				url: 'gbmultra/rbiBond/centailized/bod/getDate',
				data: requestData
			});
		}

	}
})();