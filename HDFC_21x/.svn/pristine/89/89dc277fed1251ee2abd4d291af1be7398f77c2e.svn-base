(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('blaStatusReportService', blaStatusReportService);

    blaStatusReportService.$inject = ['$http'];

    function blaStatusReportService($http) {

    	var blaStatusReportService = this;

 blaStatusReportService.getDetails = getDetails;
blaStatusReportService.getReferenceData = getReferenceData;
 blaStatusReportService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

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
                url: 'gbmultra/rbibond/reports/getBlaStatusReport',
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