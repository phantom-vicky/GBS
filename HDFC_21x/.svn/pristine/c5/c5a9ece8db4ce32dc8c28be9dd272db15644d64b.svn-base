(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('BondInterestCalculatedService', BondInterestCalculatedService);

    BondInterestCalculatedService.$inject = ['$http'];

    function BondInterestCalculatedService($http) {

    	var BondInterestCalculatedService = this;

 BondInterestCalculatedService.getDetails = getDetails;
  BondInterestCalculatedService.getBondInterestCalculatedDetails = getBondInterestCalculatedDetails;

 BondInterestCalculatedService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

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
                url: 'gbmultra/rbibond/reports/getBondInterestCalculatedReport',
                data: requestData
            })
        }


     function getBondInterestCalculatedDetails(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/reports/getBondInterestCalculatedReportDetails',
                data: requestData
            })
        }


    }

})();