(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('estimationService', estimationService);

    estimationService.$inject = ['$http'];

    function estimationService($http) {

    	var estimationService = this;

 estimationService.getDetails = getDetails;
 estimationService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

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
                url: 'gbmultra/rbibond/scrolls/estimateOfExpenses',
                data: requestData
            })
        }


    }

})();