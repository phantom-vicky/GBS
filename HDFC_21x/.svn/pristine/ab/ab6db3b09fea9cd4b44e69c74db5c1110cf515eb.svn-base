(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('rbibondInterestPaymentPendingCollService', rbibondInterestPaymentPendingCollService);

    rbibondInterestPaymentPendingCollService.$inject = ['$http'];

    function rbibondInterestPaymentPendingCollService($http) {

    	var rbibondInterestPaymentPendingCollService = this;

 rbibondInterestPaymentPendingCollService.getDetails = getDetails;
 rbibondInterestPaymentPendingCollService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

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
                url: 'gbmultra/rbibond/reports/getInterPayPendingCollReport',
                data: requestData
            })
        }


    }

})();