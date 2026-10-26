(function () {
  "use strict";

  angular.module("gbmUltraApp").service("SubscriptionMandateService", SubscriptionMandateService);

  SubscriptionMandateService.$inject = ["$http"];

  function SubscriptionMandateService($http) {
    var SubscriptionMandateService = this;

    // Main API call for redemption details
    // flag: "S" - Scheme List, "D" - Details, "274" - Scroll 274, "276" - Interest Scroll 276, "CL" - Covering Letter
    SubscriptionMandateService.getRedemptionDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/scrolls/getsubScriptionMandateDetails",
        data: requestData,
      });
    };






  }
})();
