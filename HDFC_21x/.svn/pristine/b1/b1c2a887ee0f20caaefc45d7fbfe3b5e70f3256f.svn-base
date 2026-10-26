(function () {
  "use strict";

  angular.module("gbmUltraApp").service("AppendixIV65Service", AppendixIV65Service);

  AppendixIV65Service.$inject = ["$http"];

  function AppendixIV65Service($http) {
    var AppendixIV65Service = this;

    AppendixIV65Service.getDetails = function (requestData) {
      return $http({
        method  : "POST",
        url     : "gbmultra/rbibond/scrolls/getAppendixIV65Details",
        data    : requestData,
      });
    };

    AppendixIV65Service.getReferenceData = function (requestData) {
      return $http({
        method  : "POST",
        url     : "rbiBond/getReferenceData",
        data    : requestData,
      });
    };
  }
})();
