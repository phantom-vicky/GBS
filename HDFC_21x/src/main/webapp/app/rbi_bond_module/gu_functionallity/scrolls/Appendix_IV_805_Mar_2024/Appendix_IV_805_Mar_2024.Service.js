(function () {
  "use strict";

  angular.module("gbmUltraApp").service("AppendixIV805Service", AppendixIV805Service);

  AppendixIV805Service.$inject = ["$http"];

  function AppendixIV805Service($http) {
    var AppendixIV805Service = this;

    AppendixIV805Service.getDetails = function (requestData) {
      return $http({
        method  : "POST",
        url     : "gbmultra/rbibond/scrolls/getAppendixIV805Details",
        data    : requestData,
      });
    };

    AppendixIV805Service.getReferenceData = function (requestData) {
      return $http({
        method  : "POST",
        url     : "rbiBond/getReferenceData",
        data    : requestData,
      });
    };
  }
})();
