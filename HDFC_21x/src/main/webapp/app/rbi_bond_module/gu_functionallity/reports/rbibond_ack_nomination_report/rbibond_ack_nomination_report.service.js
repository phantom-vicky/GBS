(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('rbibondAckNominationService', rbibondAckNominationService);

    rbibondAckNominationService.$inject = ['$http'];

    function rbibondAckNominationService($http) {

    	var rbibondAckNominationService = this;

 rbibondAckNominationService.getDetails = getDetails;
 rbibondAckNominationService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

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
                url: 'gbmultra/rbibond/reports/getBondAckNominiReport',
                data: requestData
            })
        }


    }

})();