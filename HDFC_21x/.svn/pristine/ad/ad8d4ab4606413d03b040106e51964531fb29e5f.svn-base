(function () {
  "use strict";

  angular.module("gbmUltraApp").service("SubscriptionAccountService", SubscriptionAccountService);

  SubscriptionAccountService.$inject = ["$http"];

  function SubscriptionAccountService($http) {
    var SubscriptionAccountService = this;

    SubscriptionAccountService.getDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getSubsAccDetails",
        data: requestData,
      });
    };

    SubscriptionAccountService.getDetailsDownload = function (requestData) {
          return $http({
            method: "POST",
            url: "gbmultra/rbibond/reports/getSubsAccDetailsDownload",
            data: requestData,
          });
        };

  }
})();
