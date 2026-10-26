(function () {
    'use strict';

    angular.module('gbmUltraApp').service('interestpaymentReportService', interestpaymentReportService);

    interestpaymentReportService.$inject = ['$http'];

    function interestpaymentReportService($http) {

        var interestpaymentReportService= this;

        interestpaymentReportService.getinterestPaymentScheme= getinterestPaymentScheme;
        interestpaymentReportService.getinterestPaymentDetails= getinterestPaymentDetails;
        interestpaymentReportService.getinterestPaymentDetailsDownload= getinterestPaymentDetailsDownload;
        interestpaymentReportService.getReferenceData = getReferenceData;

        function getinterestPaymentScheme(requestData) {
            return $http({
                method: 'POST',
                url:'gbmultra/rbibond/reports/getInterestPaymentReport',
                data: requestData
            });
        }

        function getinterestPaymentDetails(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/reports/getInterestPaymentReport',
                data: requestData
            });
        }

        function getinterestPaymentDetailsDownload(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/reports/getinterestPaymentDetailsDownload',
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