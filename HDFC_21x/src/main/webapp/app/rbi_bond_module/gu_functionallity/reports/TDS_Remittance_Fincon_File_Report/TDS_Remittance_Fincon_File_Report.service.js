(function () {
  "use strict";

  angular.module("gbmUltraApp").service("tdsRemittanceFinconFileReportService", tdsRemittanceFinconFileReportService);

  tdsRemittanceFinconFileReportService.$inject = ["$http"];
  function tdsRemittanceFinconFileReportService($http) {
    var tdsRemittanceFinconFileReportService = this;

    tdsRemittanceFinconFileReportService.getDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getTdsRemittanceFinconFileDetails",
        data: requestData,
      });
    };

    tdsRemittanceFinconFileReportService.getDetailsDownload = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getTdsRemittanceFinconFileDetailsDownload",
        data: requestData,
      });
    };
  }
})();
