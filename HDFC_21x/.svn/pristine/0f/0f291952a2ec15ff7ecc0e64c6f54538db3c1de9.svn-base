(function () {

    'use strict';

    angular.module('gbmUltraApp').service('hufDeathMarkingReportService', hufDeathMarkingReportService);

    hufDeathMarkingReportService.$inject = ['$http'];

    function hufDeathMarkingReportService($http) {

        var hufDeathMarkingReportService = this;

        hufDeathMarkingReportService.getBranchDetails = getBranchDetails;
        hufDeathMarkingReportService.getPanTorch = getPanTorch;
        hufDeathMarkingReportService.getBlaTorch = getBlaTorch;
        hufDeathMarkingReportService.getDetails = getDetails;

        function getBranchDetails() {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/reports/getBranchDetails',
                data: {}
            });
        }

        function getPanTorch(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/reports/getHufDeathMarkingDetails',
                data: requestData
            });
        }

        function getBlaTorch(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/reports/getHufDeathMarkingDetails',
                data: requestData
            });
        }

        function getDetails(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/reports/getHufDeathMarkingDetails',
                data: requestData
            });
        }
    }

})();
