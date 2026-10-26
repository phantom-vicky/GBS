(function () {
  "use strict";

  angular.module("gbmUltraApp").service("AppendixIV8Service", AppendixIV8Service);

  AppendixIV8Service.$inject = ["$http"];

  function AppendixIV8Service($http) {
    var AppendixIV8Service = this;

    AppendixIV8Service.getDetails = function (requestData) {
      return $http({
        method  : "POST",
        url     : "gbmultra/rbibond/scrolls/getAppendixIV8Details",
        data    : requestData,
      });
    };

    AppendixIV8Service.getReferenceData = function (requestData) {
      return $http({
        method  : "POST",
        url     : "rbiBond/getReferenceData",
        data    : requestData,
      });
    };
  }
})();
