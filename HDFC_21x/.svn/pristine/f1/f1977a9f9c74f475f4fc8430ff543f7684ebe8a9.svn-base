(function () {
  "use strict";

  angular.module("gbmUltraApp").service("AppendixIVService", AppendixIVService);

  AppendixIVService.$inject = ["$http"];

  function AppendixIVService($http) {
    var AppendixIVService = this;

    AppendixIVService.getDetails = getDetails;

    function getDetails(requestData) {
      return $http({
        method  :   "POST",
        url     :   "gbmultra/rbibond/scrolls/getAppendixIV85Details",
        data    :   requestData,
      });
    }
  }
})();
