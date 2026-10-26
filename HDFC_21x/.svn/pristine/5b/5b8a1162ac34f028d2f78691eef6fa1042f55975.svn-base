(function () {
  "use strict";

  angular.module("gbmUltraApp").service("MonthlyBrokerageClaimService", MonthlyBrokerageClaimService);

  MonthlyBrokerageClaimService.$inject = ["$http"];

  function MonthlyBrokerageClaimService($http) {
    var MonthlyBrokerageClaimService = this;

    MonthlyBrokerageClaimService.getMonthBrokerageDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/scrolls/getMonthBrokerageDetails",
        data: requestData,
      });
    };
  }
})();
