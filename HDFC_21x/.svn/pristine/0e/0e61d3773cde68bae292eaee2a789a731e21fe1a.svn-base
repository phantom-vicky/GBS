(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('rbiBondTrialRunEligibleAccountsService', rbiBondTrialRunEligibleAccountsService);

    rbiBondTrialRunEligibleAccountsService.$inject = ['$http'];

    function rbiBondTrialRunEligibleAccountsService($http) {

    	var rbiBondTrialRunEligibleAccountsService = this;

    	rbiBondTrialRunEligibleAccountsService.getTrialRunDetailsReport = getTrialRunDetailsReport;
    	rbiBondTrialRunEligibleAccountsService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
    	rbiBondTrialRunEligibleAccountsService.getTorchDetails = getTorchDetails;

    	function getRbiBondModuleNameDetails(requestData){
                                    return $http({
                                        method: 'POST',
                                        url: 'rbiBond/getModuleDetails',
                                        data :requestData
                                    })
                              }


        function getTrialRunDetailsReport(requestData){
                return $http({
                    method: "post",
                    url: "rbiBond/interest/trial/run/getTrialRunDetailsReport",
                    data : requestData
                });
            }


        function getTorchDetails(requestData){
                 return $http({
                    method: "post",
                    url: "rbiBond/interest/trial/run/getReportTorch",
                    data : requestData
                });
            }

    }

})();