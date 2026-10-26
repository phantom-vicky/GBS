(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('bondbalanceReportService', bondbalanceReportService);

    bondbalanceReportService.$inject = ['$http'];

    function bondbalanceReportService($http) {

    	var bondbalanceReportService = this;

 bondbalanceReportService.getDetails = getDetails;
 bondbalanceReportService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
 bondbalanceReportService.blaTorch = blaTorch;
  bondbalanceReportService.blaNo = blaNo;
  bondbalanceReportService.getsubscriptionIdInq = getsubscriptionIdInq;
  bondbalanceReportService.getBondBalanceReportDownload= getBondBalanceReportDownload;


 function getRbiBondModuleNameDetails(requestData){
             return $http({
                 method: 'POST',
                 url: 'gbmultra/rbibond/reports/getBondBalanceReport',
                 data :requestData
             })
       }
  function blaNo(requestData)
                      {
                         return	$http({
                             method : "POST",
                             url : 'gbmultra/rbibond/reports/getBondBalanceReport',
                             data :requestData
                         });
                      }
  function getsubscriptionIdInq(requestData)
                                                                  {
                                                                     return	$http({
                                                                         method : "POST",
                                                                         url : 'gbmultra/rbibond/reports/getBondBalanceReport',
                                                                         data :requestData
                                                                     });
                                                                  }


        function getDetails(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/reports/getBondBalanceReport',
                data: requestData
            })
        }
       function blaTorch(requestData)
                      {
                         return	$http({
                             method : "POST",
                             url : 'gbmultra/rbibond/reports/getBondBalanceReport',
                             data :requestData
                         });
                      }

        function getBondBalanceReportDownload(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/reports/getBondBalanceReportDownload',
                data: requestData
            });
        }

    }


})();