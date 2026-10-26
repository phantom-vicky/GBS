(function() {

	'use strict';

	angular.module('gbmUltraApp').service('populationOfSolDateService',
			populationOfSolDateService);

	populationOfSolDateService.$inject = [ '$http' ];

	function populationOfSolDateService($http) {

		var populationOfSolDateService = this;

		populationOfSolDateService.getCBSExcelFileNames = getCBSExcelFileNames;
		populationOfSolDateService.modify = modify;
		populationOfSolDateService.getpopulationOfSolDate = getpopulationOfSolDate;

		function modify(data) {
			var parameter = JSON.stringify(data);
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/soldate/save',
				params : {},
				data : parameter,
				headers : {
					'Content-type' : 'application/json;charset=utf-8'
				}
			});
		}

		function getCBSExcelFileNames(functionality) {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/bulkupload/' + functionality
						+ '/unverifiedfiles'
			});
		}

		function getpopulationOfSolDate() {
			return $http({
				method : "post",
				url : 'gbmultra/cbs/integration/master/soldate',
				data : {}
			});
		}

	}

})();