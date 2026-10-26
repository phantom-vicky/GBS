(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('rbibondAckCancelNomineeService', rbibondAckCancelNomineeService);

    rbibondAckCancelNomineeService.$inject = ['$http'];

    function rbibondAckCancelNomineeService($http) {

    	var rbibondAckCancelNomineeService = this;

 rbibondAckCancelNomineeService.getDetails = getDetails;
 rbibondAckCancelNomineeService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

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
                url: 'gbmultra/rbibond/reports/getAckCancelNomineReport',
                data: requestData
            })
        }


    }

})();