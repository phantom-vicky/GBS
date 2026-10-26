(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('rbibondTranSameBankService', rbibondTranSameBankService);

    rbibondTranSameBankService.$inject = ['$http'];

    function rbibondTranSameBankService($http) {

    	var rbibondTranSameBankService = this;

 rbibondTranSameBankService.getDetails = getDetails;
 rbibondTranSameBankService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

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
                url: 'gbmultra/rbibond/reports/getTranSameBankReport',
                data: requestData
            })
        }


    }

})();