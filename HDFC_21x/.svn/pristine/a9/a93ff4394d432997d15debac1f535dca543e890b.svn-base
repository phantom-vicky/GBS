(function () {
  "use strict";

  angular.module("gbmUltraApp").service("NomineeContinuationReportService", NomineeContinuationReportService);
  NomineeContinuationReportService.$inject = ["$http"];

  function NomineeContinuationReportService($http) {
    var NomineeContinuationReportService = this;
    NomineeContinuationReportService.getDetails = function (requestData) {
      return $http({
        method  : "POST",
        url     : "gbmultra/rbibond/reports/getNomineeReport",
        data    : requestData,
      });
    };

    NomineeContinuationReportService.getDetailsDownload = function (requestData) {
      return $http({
        method  : "POST",
        url     : "gbmultra/rbibond/reports/getNomineeReportDownload",
        data    : requestData,
      });
    };
  }
})();
