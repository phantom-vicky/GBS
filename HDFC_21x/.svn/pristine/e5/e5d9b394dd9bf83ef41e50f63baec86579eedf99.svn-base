(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('rbibondNriAccountDetailsInqService', rbibondNriAccountDetailsInqService);

    rbibondNriAccountDetailsInqService.$inject = ['$http'];

    function rbibondNriAccountDetailsInqService($http) {

    	var rbibondNriAccountDetailsInqService = this;

 rbibondNriAccountDetailsInqService.getNriAccDetails = getNriAccDetails;
 rbibondNriAccountDetailsInqService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

 function getRbiBondModuleNameDetails(requestData){
             return $http({
                 method: 'POST',
                 url: 'gbmultra/rbiBond/blaCumSub/getModuleDetails',
                 data :requestData
             })
       }


         function getNriAccDetails(requestData) {
                   return $http({
                       method: 'POST',
                       url: 'gbmultra/rbibond/reports/getNriAccDetails',
                       data: requestData
                   })
               }


    }

})();