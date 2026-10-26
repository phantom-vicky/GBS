(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('successFailureBrokerageService', successFailureBrokerageService);

    successFailureBrokerageService.$inject = ['$http'];

    function successFailureBrokerageService($http) {

    	var successFailureBrokerageService = this;


 successFailureBrokerageService.getModule = getModule;

 successFailureBrokerageService.getReportDetails = getReportDetails;

        function getReportDetails(requestData)
        {
           return $http({
                           method: 'POST',
                           url: 'gbmultra/rbibond/reports/getSuccessFailureReportDetails',
                           data: requestData
                       })
        }

                function getModule(requestData) {
                    return $http({
                        method: 'POST',
                        url: 'gbmultra/rbibond/reports/getSuccessFailureReportDetails',
                        data: requestData
                    })
                }

    }

})();