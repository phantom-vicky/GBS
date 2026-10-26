(function () {
  "use strict";

  angular.module("gbmUltraApp").service("BrokerageClaimHandlingCommissionService", BrokerageClaimHandlingCommissionService);

  BrokerageClaimHandlingCommissionService.$inject = ["$http"];

  function BrokerageClaimHandlingCommissionService($http) {
    var BrokerageClaimHandlingCommissionService = this;

    BrokerageClaimHandlingCommissionService.getRbiBondModuleNameDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getModuleDetails",
        data: requestData,
      });
    };

    BrokerageClaimHandlingCommissionService.getDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/scrolls/getBrokerageClaimHandlingCommissionDetails",
        data: requestData,
      });
    };
  }
})();
