(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('rbibondInterestRepayService', rbibondInterestRepayService);

    rbibondInterestRepayService.$inject = ['$http'];

    function rbibondInterestRepayService($http) {

    	var rbibondInterestRepayService = this;

 rbibondInterestRepayService.getDetails = getDetails;
 rbibondInterestRepayService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

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
                url: 'gbmultra/rbibond/scrolls/getBondInterestRepay',
                data: requestData
            })
        }


    }

})();