(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('rbibondScrollAppendixIVService', rbibondScrollAppendixIVService);

    rbibondScrollAppendixIVService.$inject = ['$http'];

    function rbibondScrollAppendixIVService($http) {
    	var rbibondScrollAppendixIVService = this;
 rbibondScrollAppendixIVService.getDetails = getDetails;
 rbibondScrollAppendixIVService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

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
                url: 'gbmultra/rbibond/scrolls/appendixIV',
                data: requestData
            })
        }


    }

})();