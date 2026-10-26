(function() {

    'use strict';

    angular.module('gbmUltraApp').service('pendingBrokeragePaymentClaimStatusService', pendingBrokeragePaymentClaimStatusService);

    pendingBrokeragePaymentClaimStatusService.$inject= ['$http'];

    function pendingBrokeragePaymentClaimStatusService($http) {

        var pendingBrokeragePaymentClaimStatusService=this;

        pendingBrokeragePaymentClaimStatusService.getDetails= getDetails;
        pendingBrokeragePaymentClaimStatusService.getModule= getModule;
        pendingBrokeragePaymentClaimStatusService.getValidationAdd= getValidationAdd;


        function getDetails(requestData) {
                 return $http({
                    method: 'POST',
                    url: 'gbmultra/rbibond/reports/getPendingBrokeragePaymentClaimDetails',
                    data: requestData
                 })
        }

        function getModule(requestData) {
                            return $http({
                                method: 'POST',
                                url: 'gbmultra/rbiBond/CafuFileDownload/getCafuFile',
                                data: requestData
                            })
                        }

        function getValidationAdd(requestData) {
                           return $http({
                               method : "POST",
                               url : 'gbmultra/rbiBond/brokeragePayment/addValidation',
                               data :requestData
                           });
                        }
    }
})();

