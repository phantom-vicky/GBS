(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('rbibondInterestRunCreditService', rbibondInterestRunCreditService);

    rbibondInterestRunCreditService.$inject = ['$http'];

    function rbibondInterestRunCreditService($http) {

    	var rbibondInterestRunCreditService = this;

 rbibondInterestRunCreditService.getDetails = getDetails;
 rbibondInterestRunCreditService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;


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
                url: 'gbmultra/rbibond/reports/getInterestRunCreditReport',
                data: requestData
            })
        }

        }

})();