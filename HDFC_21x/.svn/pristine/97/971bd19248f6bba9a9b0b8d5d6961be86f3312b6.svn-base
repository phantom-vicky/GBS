(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('rbibondAccHolderInqService', rbibondAccHolderInqService);

    rbibondAccHolderInqService.$inject = ['$http'];

    function rbibondAccHolderInqService($http) {

    	var rbibondAccHolderInqService = this;

 rbibondAccHolderInqService.getDetails = getDetails;
 rbibondAccHolderInqService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

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
                url: 'gbmultra/rbibond/reports/getAccHolderInquiryReport',
                data: requestData
            })
        }


    }

})();