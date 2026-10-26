(function () {
  "use strict";

  angular.module("gbmUltraApp").service("FTIService", FTIService);

  FTIService.$inject = ["$http"];

  function FTIService($http) {
    var FTIService = this;

    FTIService.getDetails = function (requestData) {
      return $http({
        method  : "post",
        url     : "gbmultra/rbibond/reports/financialTransactionInquiry",
        data    : requestData,
      });
    };
  }
})();
