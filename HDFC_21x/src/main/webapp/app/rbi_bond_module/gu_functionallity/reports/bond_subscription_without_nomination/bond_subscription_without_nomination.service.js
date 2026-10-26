(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('bondSubscriptionwithoutnominationService', bondSubscriptionwithoutnominationService);

    bondSubscriptionwithoutnominationService.$inject = ['$http'];

    function bondSubscriptionwithoutnominationService($http) {

    	var bondSubscriptionwithoutnominationService = this;

 bondSubscriptionwithoutnominationService.getDetails = getDetails;
 bondSubscriptionwithoutnominationService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

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
                url: 'gbmultra/rbibond/reports/bondSubscriptionwithoutnomination',
                data: requestData
            })
        }


    }

})();