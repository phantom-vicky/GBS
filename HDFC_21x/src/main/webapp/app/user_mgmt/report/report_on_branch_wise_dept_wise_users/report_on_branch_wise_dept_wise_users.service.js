(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('reportOnBranchWiseDeptWiseUsersService', reportOnBranchWiseDeptWiseUsersService);

    reportOnBranchWiseDeptWiseUsersService.$inject = ['$http'];

    function reportOnBranchWiseDeptWiseUsersService($http) {

    	var reportOnBranchWiseDeptWiseUsersService = this;


    	reportOnBranchWiseDeptWiseUsersService.getReportDetails = function(requestData) {
              return $http({
                  method: "post",
                  url: 'gbmultra/users/reports/getReportOnBranchWiseDeptWiseUserDetails',
                  data: requestData
              });
        }
    }

})();