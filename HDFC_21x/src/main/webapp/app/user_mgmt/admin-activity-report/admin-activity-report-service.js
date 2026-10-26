(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('adminActivityReportService', adminActivityReportService);

    adminActivityReportService.$inject = ['$http'];

    function adminActivityReportService($http) {

    	var adminActivityReportService = this;

        adminActivityReportService.getDetails = getDetails;
        adminActivityReportService.adminActivityReportDownload = adminActivityReportDownload;



        function getDetails(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/user/report/adminActivityReport',
                data: requestData
            })
        }


        function adminActivityReportDownload(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/user/report/adminActivityReportDownload',
                data: requestData
            })
        }



    }

})();