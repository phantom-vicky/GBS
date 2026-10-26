(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('loginLogoutReportService', loginLogoutReportService);

    loginLogoutReportService.$inject = ['$http'];

    function loginLogoutReportService($http) {

    	var loginLogoutReportService = this;

        loginLogoutReportService.getDetails = getDetails;



        function getDetails(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/user/report/loginLogoutReport',
                data: requestData
            })
        }



    }

})();