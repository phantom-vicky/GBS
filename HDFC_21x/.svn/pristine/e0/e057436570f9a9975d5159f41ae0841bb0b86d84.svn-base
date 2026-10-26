(function () {
  "use strict";

  angular.module("gbmUltraApp").service("minorAccDetailsService", minorAccDetailsService);

  minorAccDetailsService.$inject = ["$http"];
  function minorAccDetailsService($http) {
    var minorAccDetailsService = this;
    minorAccDetailsService.getDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getMinorAccountDetails",
        data: requestData,
      });
    }

    minorAccDetailsService.getReferenceData = function (requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getReferenceData",
        data: requestData,
      });
    };
  }
})();
