(function () {

	'use strict';

	angular
		.module('gbmUltraApp')
		.service('auditReportAtDcLevelForAnySolService', auditReportAtDcLevelForAnySolService);

	auditReportAtDcLevelForAnySolService.$inject = ['$http'];

	function auditReportAtDcLevelForAnySolService($http) {

		var auditReportAtDcLevelForAnySolService = this;


		auditReportAtDcLevelForAnySolService.getReportsAtDCLevelForAnySol = getReportsAtDCLevelForAnySol;
		auditReportAtDcLevelForAnySolService.getSolDetails = getSolDetails;

		auditReportAtDcLevelForAnySolService.getRecordTypes = getRecordTypes;
		auditReportAtDcLevelForAnySolService.getModuleDetails = getModuleDetails;
		auditReportAtDcLevelForAnySolService.getOperation = getOperation;
		auditReportAtDcLevelForAnySolService.getTreasuryCode = getTreasuryCode;
		auditReportAtDcLevelForAnySolService.getStateCode = getStateCode;


		/* Get Sol Details */
		function getSolDetails(requestData) {
			return $http({
				method: "post",
				url: "gbmultra/users/reports/audit/getBranchId",
				data: requestData
			});
		}


		/* Get record types */
		function getRecordTypes() {
			return $http({
				method: "post",
				url: "gbmultra/users/reports/audit/getRecordStatus",
				data: {}
			});
		}

		function getOperation(requestData) {
			return $http({
				method: "post",
				url: "gbmultra/users/reports/getOperations",
				data: requestData
			});
		}

		function getTreasuryCode(requestData) {
			return $http({
				method: "post",
				url: "gbmultra/users/reports/audit/getTreasuryCode",
				data: requestData
			});
		}

		function getStateCode(requestData) {
			return $http({
				method: "post",
				url: "gbmultra/users/reports/audit/getStateTaxCode",
				data: requestData
			});
		}

		auditReportAtDcLevelForAnySolService.getLinkName = function (requestData) {
			return $http({
				method: "post",
				url: 'gbmultra/users/reports/getLinkName',
				data: requestData
			});
		}

		function getReportsAtDCLevelForAnySol(postdata) {
			return $http({
				method: "POST",
				url: "gbmultra/users/reports/getReportsAtDCLevelForAnySol",
				headers: {
					'Content-Type': 'application/json'
				},
				data: postdata
			});
		}

		/* Get Module Details */
		function getModuleDetails() {
			return $http({
				method: "post",
				url: 'gbmultra/users/reports/audit/getModuleId',
				data: {}
			});
		}


	}


})();