(function () {
  "use strict";

  angular.module("gbmUltraApp").service("AppendixIIIRegisterOfNominationService", AppendixIIIRegisterOfNominationService);

  AppendixIIIRegisterOfNominationService.$inject = ["$http"];

  function AppendixIIIRegisterOfNominationService($http) {
    var AppendixIIIRegisterOfNominationService = this;

    AppendixIIIRegisterOfNominationService.getRbiBondModuleNameDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getModuleDetails",
        data: requestData,
      });
    };

    AppendixIIIRegisterOfNominationService.getDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/scrolls/getAppendixIIIRegisterOfNominationDetails",
        data: requestData,
      });
    };
  }
})();
