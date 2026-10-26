(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('rbibondInterestPaymentPendingService', rbibondInterestPaymentPendingService);

    rbibondInterestPaymentPendingService.$inject = ['$http'];

    function rbibondInterestPaymentPendingService($http) {

    	var rbibondInterestPaymentPendingService = this;

 rbibondInterestPaymentPendingService.getDetails = getDetails;
 rbibondInterestPaymentPendingService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

 function getRbiBondModuleNameDetails(requestData){
             return $http({
                 method: 'POST',
                 url: 'rbiBond/getModuleDetails',
                 data :requestData
             })
       }


        function getDetails(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/reports/getInterPayPendingReport',
                data: requestData
            })
        }


    }

})();