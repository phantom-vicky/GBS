(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('StatementOfBondsService', StatementOfBondsService);

    StatementOfBondsService.$inject = ['$http'];

    function StatementOfBondsService($http) {

    	var StatementOfBondsService = this;

 StatementOfBondsService.getDetails = getDetails;
 StatementOfBondsService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

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
                url: 'gbmultra/rbibond/scrolls/StatementOfBondsDetails',
                data: requestData
            })
        }


    }

})();