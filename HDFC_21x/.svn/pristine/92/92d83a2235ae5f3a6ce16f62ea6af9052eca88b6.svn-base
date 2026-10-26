(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('rbibondRegisterLedgerService', rbibondRegisterLedgerService);

    rbibondRegisterLedgerService.$inject = ['$http'];

    function rbibondRegisterLedgerService($http) {

    	var rbibondRegisterLedgerService = this;

 rbibondRegisterLedgerService.getDetails = getDetails;
 rbibondRegisterLedgerService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

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
                url: 'gbmultra/rbibond/scrolls/getBondRegisterLedgerAcc',
                data: requestData
            })
        }


    }

})();