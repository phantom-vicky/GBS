(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('userActivityReportService', userActivityReportService);

    userActivityReportService.$inject = ['$http'];

    function userActivityReportService($http) {

    	var userActivityReportService = this;

 userActivityReportService.getDetails = getDetails;
  userActivityReportService.getSubcriptionDetails = getSubcriptionDetails;

userActivityReportService.getReferenceData = getReferenceData;
 userActivityReportService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

 function getRbiBondModuleNameDetails(requestData){
             return $http({
                 method: 'POST',
                 url: 'rbiBond/getModuleDetails',
                 data :requestData
             })
       }


        function getDetails(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/user/report/getUserActivityDetails',
                data: requestData
            })
        }


 function getSubcriptionDetails(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/user/report/getModuleName',
                data: requestData
            })
        }

            function getReferenceData(requestData)
                        {
                           return	$http({
                               method : "POST",
                               url : 'rbiBond/getReferenceData',
                               data :requestData
                           });
                        }


    }

})();