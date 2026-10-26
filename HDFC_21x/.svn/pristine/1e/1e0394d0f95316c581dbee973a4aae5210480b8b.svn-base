(function () {
  "use strict";

  angular.module("gbmUltraApp").service("bondRepaymentReportService", bondRepaymentReportService);

  bondRepaymentReportService.$inject = ["$http"];

  function bondRepaymentReportService($http) {
    var bondRepaymentReportService = this;

    bondRepaymentReportService.getDetails = getDetails;
    bondRepaymentReportService.getDetailsDownload = getDetailsDownload;
    bondRepaymentReportService.getReferenceData = getReferenceData;
    function getDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getBondRepaymentReport",
        data: requestData,
      });
    }

    function getDetailsDownload(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getBondRepaymentReportDownload",
        data: requestData,
      });
    }

    function getReferenceData(referenceId) {
      return $http({
        method: "POST",
        url: "rbiBond/getReferenceData",
        data: referenceId,
      });
    }
  }
})();
