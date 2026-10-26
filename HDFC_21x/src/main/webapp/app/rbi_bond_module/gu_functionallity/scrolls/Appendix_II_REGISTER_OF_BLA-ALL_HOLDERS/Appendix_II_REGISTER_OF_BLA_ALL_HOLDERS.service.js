(function () {
  "use strict";

  angular.module("gbmUltraApp").service("AppendixIIRegisterOfBlaService", AppendixIIRegisterOfBlaService);

  AppendixIIRegisterOfBlaService.$inject = ["$http"];

  function AppendixIIRegisterOfBlaService($http) {
    var AppendixIIRegisterOfBlaService = this;

    // Scheme list for torch
    AppendixIIRegisterOfBlaService.getRbiBondModuleNameDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getModuleDetails",
        data: requestData,
      });
    };

    // Own API - Appendix II Register of BLA - All Holders
    AppendixIIRegisterOfBlaService.getDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/scrolls/getAppendixIIRegisterOfBlaDetails",
        data: requestData,
      });
    };
  }
})();
