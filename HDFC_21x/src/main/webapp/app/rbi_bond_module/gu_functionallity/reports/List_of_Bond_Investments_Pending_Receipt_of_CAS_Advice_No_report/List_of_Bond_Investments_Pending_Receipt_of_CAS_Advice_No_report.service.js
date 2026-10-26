(function () {

    'use strict';

    angular.module('gbmUltraApp')
        .service('listofbondinvestmentsService', listofbondinvestmentsService);

    listofbondinvestmentsService.$inject = ['$http'];

    function listofbondinvestmentsService($http) {

        var listofbondinvestmentsService = this;

        listofbondinvestmentsService.getDetails = getDetails;
        listofbondinvestmentsService.getDetailsDownload = getDetailsDownload;

        function getDetails(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/reports/getListOfBondReport',
                data: requestData
            })
        }

        function getDetailsDownload(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/reports/getListOfBondReportDownload',
                data: requestData
            })
        }


    }

})();