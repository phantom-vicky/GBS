(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('unverifiedRecordReportCbService', unverifiedRecordReportCbService);

    unverifiedRecordReportCbService.$inject = ['$http'];

    function unverifiedRecordReportCbService($http) {

    	var unverifiedRecordReportCbService = this;

  unverifiedRecordReportCbService.getRbiBondModuleNameDetails=getRbiBondModuleNameDetails;


        unverifiedRecordReportCbService.reportDetails = function (requestData){
                        return $http({
                            method: "POST",
                            url: 'gbmultra/rbibond/reports/getUnverifiedReportForCb',
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