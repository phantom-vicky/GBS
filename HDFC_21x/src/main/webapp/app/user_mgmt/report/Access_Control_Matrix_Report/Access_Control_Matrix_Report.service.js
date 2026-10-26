(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('accessControlMatrixReportService', accessControlMatrixReportService);

    accessControlMatrixReportService.$inject = ['$http'];

    function accessControlMatrixReportService($http) {

    	var accessControlMatrixReportService = this;


    	accessControlMatrixReportService.getDetails = getDetails;
    	function getDetails(requestData) {
              return $http({
                  method: "post",
                  url: 'gbmultra/user/report/getAccessControlMatrixreport',
                  data: requestData
              });
        }
    }

})();