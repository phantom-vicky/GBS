(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('dailyRemittanceLcService', dailyRemittanceLcService);

    dailyRemittanceLcService.$inject = ['$http'];

    function dailyRemittanceLcService($http) {

    	var dailyRemittanceLcService = this;

 dailyRemittanceLcService.getDailyRemittanceLcReport = getDailyRemittanceLcReport;
 dailyRemittanceLcService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

 function getRbiBondModuleNameDetails(requestData){
             return $http({
                 method: 'POST',
                 url: 'rbiBond/getModuleDetails',
                 data :requestData
             })
       }


        function getDailyRemittanceLcReport(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/reports/getDailyRemittanceLcReport',
                data: requestData
            })
        }



    }

})();