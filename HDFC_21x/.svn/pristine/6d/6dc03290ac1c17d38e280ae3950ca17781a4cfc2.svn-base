(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('rbibondTurnoverCommissionService', rbibondTurnoverCommissionService);

    rbibondTurnoverCommissionService.$inject = ['$http'];

    function rbibondTurnoverCommissionService($http) {

    	var rbibondTurnoverCommissionService = this;

 rbibondTurnoverCommissionService.getDetails = getDetails;
 rbibondTurnoverCommissionService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

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
                url: 'gbmultra/rbibond/scrolls/StatementOfChargesTurnoverCommissionDetails',
                data: requestData
            })
        }


    }

})();