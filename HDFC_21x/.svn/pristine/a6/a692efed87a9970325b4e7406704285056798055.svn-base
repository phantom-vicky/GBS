(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('AnnualInfoService', AnnualInfoService);

    AnnualInfoService.$inject = ['$http'];

    function AnnualInfoService($http) {

    	var AnnualInfoService = this;

 AnnualInfoService.getDetails = getDetails;

        function getDetails(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/scrolls/getAnnualReport',
                data: requestData
            })
        }
        AnnualInfoService.getReferenceData = function (requestData) {
              return $http({
                method  : "POST",
                url     : "rbiBond/getReferenceData",
                data    : requestData,
              });
            };
    }

})();