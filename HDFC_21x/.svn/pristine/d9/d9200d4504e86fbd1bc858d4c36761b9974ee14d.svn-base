(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('RoleRightReportService', RoleRightReportService);

    RoleRightReportService.$inject = ['$http'];

    function RoleRightReportService($http) {

    	var RoleRightReportService = this;

           RoleRightReportService.getRoleRightsReport = function(requestData) {
                      return $http({
                          method: "post",
                          url: 'gbmultra/user/report/getRoleRightsReport',
                          data: requestData
                      });
                }
         RoleRightReportService.getRoleRightsReportDownload = function(requestData) {
                              return $http({
                                  method: "post",
                                  url: 'gbmultra/user/report/getRoleRightsReportDownload',
                                  data: requestData
                              });
                        }
    }

})();