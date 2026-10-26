(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('rbibondRegLedgerLinkCellService', rbibondRegLedgerLinkCellService);

    rbibondRegLedgerLinkCellService.$inject = ['$http'];

    function rbibondRegLedgerLinkCellService($http) {

    	var rbibondRegLedgerLinkCellService = this;

 rbibondRegLedgerLinkCellService.getDetails = getDetails;
 rbibondRegLedgerLinkCellService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

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
                url: 'gbmultra/rbibond/scrolls/getBondRegLedgerLinkCell',
                data: requestData
            })
        }


    }

})();