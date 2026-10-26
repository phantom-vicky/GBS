(function () {
  "use strict";
  angular.module("gbmUltraApp").service("TDSExemptionSubmitService", TDSExemptionSubmitService);
  TDSExemptionSubmitService.$inject = ["$http"];

  function TDSExemptionSubmitService($http) {
    var TDSExemptionSubmitService = this;
    TDSExemptionSubmitService.getDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/TDSExemptionSubmitNonSubmitReport",
        data: requestData,
      });
    };

    TDSExemptionSubmitService.getDetailsDownload = function (requestData) {
          return $http({
            method: "POST",
            url: "gbmultra/rbibond/reports/TDSExemptionSubmitNonSubmitDownload",
            data: requestData,
          });
        };

  }
})();
