(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('rbibondPrincipalRepayService', rbibondPrincipalRepayService);

    rbibondPrincipalRepayService.$inject = ['$http'];

    function rbibondPrincipalRepayService($http) {

    	var rbibondPrincipalRepayService = this;

 rbibondPrincipalRepayService.getDetails = getDetails;
 rbibondPrincipalRepayService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

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
                url: 'gbmultra/rbibond/scrolls/getBondPrincipalRepay',
                data: requestData
            })
        }


    }

})();