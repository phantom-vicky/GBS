(function () {
  "use strict";

  angular.module("gbmUltraApp").service("ReportOnPanAadharService", ReportOnPanAadharService);

  ReportOnPanAadharService.$inject = ["$http"];

  function ReportOnPanAadharService($http) {
    var ReportOnPanAadharService = this;


    ReportOnPanAadharService.getDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getPanAadharDetails",
        data: requestData,
      });
    };

    ReportOnPanAadharService.getDetailsDownload= function (requestData) {
        return $http({
            method  : "POST",
            url     : "gbmultra/rbibond/reports/getPanAadharDetailsDownload",
            data    : requestData,
        });
    };

  }
})();
