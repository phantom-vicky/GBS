(function () {
  "use strict";

  angular.module("gbmUltraApp").service("AppendixVIService", AppendixVIService);

  AppendixVIService.$inject = ["$http"];

  function AppendixVIService($http) {
    var AppendixVIService = this;

    // Scheme list for torch
    AppendixVIService.getRbiBondModuleNameDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getModuleDetails",
        data: requestData,
      });
    };

    // Own API - Appendix VI Statement of Bonds Issued/Repaid
    AppendixVIService.getDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/scrolls/getAppendVIDetails",
        data: requestData,
      });
    };
  }
})();
