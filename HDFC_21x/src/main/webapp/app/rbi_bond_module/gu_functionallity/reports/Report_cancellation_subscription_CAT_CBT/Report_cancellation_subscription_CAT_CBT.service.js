(function () {

  'use strict';

  angular.module('gbmUltraApp').service(
    'reportCancellationSubsCatCbtService',
    reportCancellationSubsCatCbtService);

  reportCancellationSubsCatCbtService.$inject = ['$http'];
  function reportCancellationSubsCatCbtService($http) {
    var reportCancellationSubsCatCbtService = this;
   reportCancellationSubsCatCbtService.getDetails = getDetails;


    function getDetails(requestData){
      return $http({
            method: 'POST',
            url: ' gbmultra/rbibond/reports/getReportCancellationSubsCatCbt',
            data: requestData,
          });
    }

        reportCancellationSubsCatCbtService.getReferenceData = function (requestData) {
          return $http({
            method: "POST",
            url: "rbiBond/getReferenceData",
            data: requestData,
          });
        };

  }
})();