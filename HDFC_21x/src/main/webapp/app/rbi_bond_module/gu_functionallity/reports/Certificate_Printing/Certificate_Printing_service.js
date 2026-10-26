(function () {
    'use strict';

    angular.module('gbmUltraApp').service('certificateOfPrintingService', certificateOfPrintingService);

    certificateOfPrintingService.$inject = ['$http'];

    function certificateOfPrintingService($http) {

        var certificateOfPrintingService= this;

        certificateOfPrintingService.getCpTotalDetails= getCpTotalDetails;


        function getCpTotalDetails(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/reports/getCpTotalDetails',
                data: requestData
            });
        }


    }
})();