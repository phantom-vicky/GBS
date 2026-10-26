(function () {
  "use strict";

  angular.module("gbmUltraApp").service("PANValidationReportService", PANValidationReportService);

  PANValidationReportService.$inject = ["$http"];

  function PANValidationReportService($http) {
    var PANValidationReportService = this;

    PANValidationReportService.getDetails = getDetails;

     PANValidationReportService.getPANValidationReportDownload = getPANValidationReportDownload;

    function getDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getPanValidationBondSchemeReport",
        data: requestData,
      });
    }


  function getPANValidationReportDownload(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/reports/getPanValidationReportDownload',
                data: requestData
            });
        }

  }
})();
