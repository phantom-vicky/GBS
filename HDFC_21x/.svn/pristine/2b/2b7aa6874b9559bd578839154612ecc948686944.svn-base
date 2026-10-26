(function () {
  "use strict";
  angular.module("gbmUltraApp").service("FreezeUnfreezeService", FreezeUnfreezeService);
  FreezeUnfreezeService.$inject = ["$http"];

  function FreezeUnfreezeService($http) {
    var FreezeUnfreezeService = this;

    FreezeUnfreezeService.getDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getFreezeUnfreezeDetails",
        data: requestData,
      });
    };
    FreezeUnfreezeService.getReferenceData = function (requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getReferenceData",
        data: requestData,
      });
    };
  }
})();
