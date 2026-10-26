(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('rbibondLedgerNameSearchService', rbibondLedgerNameSearchService);

    rbibondLedgerNameSearchService.$inject = ['$http'];

    function rbibondLedgerNameSearchService($http) {

    	var rbibondLedgerNameSearchService = this;

 rbibondLedgerNameSearchService.getDetails = getDetails;
 rbibondLedgerNameSearchService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

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
                url: 'gbmultra/rbibond/reports/getBlaNameSearchReport',
                data: requestData
            })
        }


    }

})();