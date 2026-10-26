(function () {
  "use strict";

  angular.module("gbmUltraApp").service("TaxWaiverDocumentsReportService", TaxWaiverDocumentsReportService);

  TaxWaiverDocumentsReportService.$inject = ["$http"];

  function TaxWaiverDocumentsReportService($http) {
    var TaxWaiverDocumentsReportService = this;

    TaxWaiverDocumentsReportService.getDetails = getDetails;

     TaxWaiverDocumentsReportService.getPANValidationReportDownload = getPANValidationReportDownload;

    function getDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getTaxWaiverDocumentsReport",
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
