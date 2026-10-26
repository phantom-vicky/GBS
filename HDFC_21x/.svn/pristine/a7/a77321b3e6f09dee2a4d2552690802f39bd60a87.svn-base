(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('BondInterestReportService', BondInterestReportService);

    BondInterestReportService.$inject = ['$http'];

    function BondInterestReportService($http) {

    	var BondInterestReportService = this;

  BondInterestReportService.getDetails = getDetails;

 BondInterestReportService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

 function getRbiBondModuleNameDetails(requestData){
             return $http({
                 method: 'POST',
                 url: 'rbiBond/getBondCollectingdReportDetails',
                 data :requestData
             })
       }

     function getDetails(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/reports/getBondCollectingdReportDetails',
                data: requestData
            })
        }

       BondInterestReportService.getReferenceData = function (requestData) {
           return $http({
             method: "POST",
             url: "rbiBond/getReferenceData",
             data: requestData,
           });
         };
    }

})();


