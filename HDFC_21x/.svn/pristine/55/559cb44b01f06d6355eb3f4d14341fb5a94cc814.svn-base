(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('PopulationReportService', PopulationReportService);

    PopulationReportService.$inject = ['$http'];

    function PopulationReportService($http) {

    	var PopulationReportService = this;

    	PopulationReportService.getReportDetails = function(requestData) {
              return $http({
                  method: "post",
                  url: 'gbmultra/users/reports/getReportOnBranchWiseDeptWiseUserDetails',
                  data: requestData
              });
        }
           PopulationReportService.getPopulationData = function(requestData) {
                      return $http({
                          method: "post",
                          url: 'gbmultra/user/report/getPopulationData',
                          data: requestData
                      });
                }



                PopulationReportService.getPopulationReportDownload = function(requestData) {
                                      return $http({
                                          method: "post",
                                          url: 'gbmultra/user/report/getPopulationDataDownload',
                                          data: requestData
                                      });
                                }
    }

})();