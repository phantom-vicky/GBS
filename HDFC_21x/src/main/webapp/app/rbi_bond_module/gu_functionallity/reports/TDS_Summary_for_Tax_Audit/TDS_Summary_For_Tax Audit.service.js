(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('tdsSummaryTaxAuditService', tdsSummaryTaxAuditService);

    tdsSummaryTaxAuditService.$inject = ['$http'];

    function tdsSummaryTaxAuditService($http) {
    var tdsSummaryTaxAuditService = this;

// this.getReportDetails = getReportDetails;

        tdsSummaryTaxAuditService.getReportDetails = function (requestData)
        {
           return $http({
                           method: 'POST',
                           url: 'gbmultra/rbibond/reports/getTdsSummaryForTaxAudit',
                           data: requestData
                       })
        }

        tdsSummaryTaxAuditService.getReportDetailsDownload = function (requestData)
                {
                   return $http({
                                   method: 'POST',
                                   url: 'gbmultra/rbibond/reports/getReportDetailsDownload',
                                   data: requestData
                               })
                }

    }

})();