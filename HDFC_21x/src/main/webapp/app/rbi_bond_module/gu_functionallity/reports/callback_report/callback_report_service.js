(function () {
    'use strict';

    angular.module('gbmUltraApp').service('callbackReportService', callbackReportService);

    callbackReportService.$inject = ['$http'];

    function callbackReportService($http) {

        var callbackReportService= this;

        callbackReportService.getBrokerageInvoiceBondScheme= getBrokerageInvoiceBondScheme;
        callbackReportService.getCallBackReportDetails= getCallBackReportDetails;
        callbackReportService.getCallBackReportDownload= getCallBackReportDownload;
        callbackReportService.getReferenceData= getReferenceData;

        function getBrokerageInvoiceBondScheme(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/reports/getCallBackReportDetails',
                data: requestData
            });
        }
    function getCallBackReportDownload(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/reports/getCallBackReportDownload',
                data: requestData
            });
        }

        function getCallBackReportDetails(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/reports/getCallBackReportDetails',
                data: requestData
            });
        }

        function getReferenceData(referenceId) {
            return $http({
                method  : 'POST',
                url     : 'rbiBond/getReferenceData',
                data    : referenceId
            });
        }

    }
})();