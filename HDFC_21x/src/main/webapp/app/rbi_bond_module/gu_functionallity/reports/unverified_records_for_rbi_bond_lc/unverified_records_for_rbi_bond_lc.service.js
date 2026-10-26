(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('unverifiedRecordReportLcService', unverifiedRecordReportLcService);

    unverifiedRecordReportLcService.$inject = ['$http'];

    function unverifiedRecordReportLcService($http) {

    	var unverifiedRecordReportLcService = this;

  unverifiedRecordReportLcService.getRbiBondModuleNameDetails=getRbiBondModuleNameDetails;


        unverifiedRecordReportLcService.reportDetails = function (requestData){
                        return $http({
                            method: "POST",
                            url: 'gbmultra/rbibond/reports/getUnverifiedReportForLc',
                            data : requestData
                        });
                    }

        function getRbiBondModuleNameDetails(requestData){
                            return $http({
                                method: 'POST',
                                url: 'rbiBond/getModuleDetails',
                                data :requestData
                            })
                      }


    }

})();