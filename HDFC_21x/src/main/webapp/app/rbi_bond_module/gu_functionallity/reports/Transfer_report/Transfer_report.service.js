(function () {
  "use strict";

  angular.module("gbmUltraApp").service("TransferReportService", TransferReportService);

  TransferReportService.$inject = ["$http"];

  function TransferReportService($http) {
    var TransferReportService = this;

    TransferReportService.getDetails = getDetails;

    function getDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/latestReports/getTransferReport",
        data: requestData,
      });
    }
  }
})();
