(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('StatementOfChargesServicingService', StatementOfChargesServicingService);

    StatementOfChargesServicingService.$inject = ['$http'];

    function StatementOfChargesServicingService($http) {

    	var StatementOfChargesServicingService = this;

 StatementOfChargesServicingService.getDetails = getDetails;
 StatementOfChargesServicingService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

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
                url: 'gbmultra/rbibond/scrolls/StatementOfChargesServicingDetails',
                data: requestData
            })
        }


    }

})();