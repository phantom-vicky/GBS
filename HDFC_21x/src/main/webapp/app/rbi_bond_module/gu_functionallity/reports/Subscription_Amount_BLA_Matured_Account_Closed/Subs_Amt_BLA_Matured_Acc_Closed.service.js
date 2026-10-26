(function () {
  "use strict";

  angular.module("gbmUltraApp").service("SubsAmtBLAMaturedAccClosedService", SubsAmtBLAMaturedAccClosedService);

  SubsAmtBLAMaturedAccClosedService.$inject = ["$http"];

  function SubsAmtBLAMaturedAccClosedService($http) {
    var SubsAmtBLAMaturedAccClosedService = this;
    SubsAmtBLAMaturedAccClosedService.getDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/subsAmtBLAMaturedAccClosed",
        data: requestData,
      });
    };

    SubsAmtBLAMaturedAccClosedService.getDetailsDownload = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/subsAmtBLAMaturedAccClosedDownload",
        data: requestData,
      });
    };

    SubsAmtBLAMaturedAccClosedService.getReferenceData = function (requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getReferenceData",
        data: requestData,
      });
    };
  }
})();
