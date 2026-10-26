(function () {
  "use strict";
  angular.module("gbmUltraApp").service("ReportOnUnverifiedService", ReportOnUnverifiedService);
  ReportOnUnverifiedService.$inject = ["$http"];

  function ReportOnUnverifiedService($http) {
    var ReportOnUnverifiedService = this;

    ReportOnUnverifiedService.getDetails = function getDetails(requestData) {
      return $http({
        method  : "POST",
        url     : "gbmultra/rbibond/reports/getReportOnUnverifiedDetails",
        data    : requestData,
      });
    };
  }
})();
