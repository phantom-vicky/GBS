(function () {
  "use strict";

  angular.module("gbmUltraApp").service("SubscriptionTextFileService", SubscriptionTextFileService);

  SubscriptionTextFileService.$inject = ["$http"];

  function SubscriptionTextFileService($http) {
    var SubscriptionTextFileService = this;

    SubscriptionTextFileService.getSubsAccDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/scrolls/getSubsTextFileDetails",
//        getSubsTextFileDetails
        data: requestData,
      });
    };
  }
})();
