(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('rbibondAppendixVIIBrokerageService', rbibondAppendixVIIBrokerageService);

    rbibondAppendixVIIBrokerageService.$inject = ['$http'];

    function rbibondAppendixVIIBrokerageService($http) {

    	var rbibondAppendixVIIBrokerageService = this;

 rbibondAppendixVIIBrokerageService.getDetails = getDetails;
 rbibondAppendixVIIBrokerageService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

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
                url: 'gbmultra/rbibond/scrolls/getBrokerageHandlingReport',
                data: requestData
            })
        }


    }

})();