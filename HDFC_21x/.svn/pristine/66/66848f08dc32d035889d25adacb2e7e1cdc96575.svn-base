(function () {
  "use strict";

  angular.module("gbmUltraApp").service("eTDSReportService", eTDSReportService);

  eTDSReportService.$inject = ["$http"];

  function eTDSReportService($http) {
    var eTDSReportService = this;

    eTDSReportService.getDetails = getDetails;
    eTDSReportService.getDetailsDownload = getDetailsDownload;

    function getDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getEtdsDetails",
        data: requestData,
      });
    }

    function getDetailsDownload(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getEtdsDetailsDownload",
        data: requestData,
      });
    }
  }
})();
