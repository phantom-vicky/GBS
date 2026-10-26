(function () {
  "use strict";

  angular.module("gbmUltraApp").service("disablingDeactivatingService", disablingDeactivatingService);

  disablingDeactivatingService.$inject = ["$http"];
  function disablingDeactivatingService($http) {
    var disablingDeactivatingService = this;

    disablingDeactivatingService.getDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getDisablingDeactivating",
        data: requestData,
      });
    };
    disablingDeactivatingService.getReferenceData = function (requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getReferenceData",
        data: requestData,
      });
    };

    disablingDeactivatingService.getDetailsDownload = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getDisablingDeactivatingDownload",
        data: requestData,
      });
    };

  }
})();
