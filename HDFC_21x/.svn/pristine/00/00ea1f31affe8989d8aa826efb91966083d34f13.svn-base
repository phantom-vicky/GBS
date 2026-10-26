(function () {
  "use strict";
  angular.module("gbmUltraApp").service("ReportTransactionDetailsService", ReportTransactionDetailsService);
  ReportTransactionDetailsService.$inject = ["$http"];

  function ReportTransactionDetailsService($http) {
    var ReportTransactionDetailsService = this;

    ReportTransactionDetailsService.getReportOnTransactionDetails = function getReportOnTransactionDetails(requestData) {
      return $http({
        method  : "POST",
        url     : "gbmultra/rbibond/reports/getReportOnTransactionDetails",
        data    : requestData,
      });
    }
    ReportTransactionDetailsService.getReportOnTransactionDetailsDownload = function getReportOnTransactionDetails(requestData) {
      return $http({
        method  : "POST",
        url     : "gbmultra/rbibond/reports/getReportOnTransactionDetailsDownload",
        data    : requestData,
      });
    }
  }
})();
