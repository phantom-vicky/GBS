(function() {

	'use strict';

	angular.module('gbmUltraApp').service('bondInterestPayMisReportService',bondInterestPayMisReportService);

	bondInterestPayMisReportService.$inject = [ '$http' ];

	function bondInterestPayMisReportService($http) {
		var bondInterestPayMisReportService = this;


    bondInterestPayMisReportService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
    bondInterestPayMisReportService.getFinancialYear = getFinancialYear;
    bondInterestPayMisReportService.getBondInterestPayMisReportValues = getBondInterestPayMisReportValues;



     function getBondInterestPayMisReportValues(requestData){
                  return $http({
                     method: 'POST',
                     url: 'gbmultra/rbibond/reports/getBondInterestPayMisReportValues',
                     data :requestData
                  })
     }

     function getRbiBondModuleNameDetails(requestData){
                 return $http({
                    method: 'POST',
                    url: 'rbiBond/getModuleDetails',
                    data :requestData
                 })
     }

     function getFinancialYear(requestData){
                 return $http({
                         method: 'POST',
                         url: 'rbiBond/getFinancialYear',
                         data :requestData
                 })
     }







		}

		})();