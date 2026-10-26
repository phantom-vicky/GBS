(function () {
  "use strict";
  angular.module("gbmUltraApp").service("rbiBondInterestTrailRunPaymentWiseService", rbiBondInterestTrailRunPaymentWiseService);
  rbiBondInterestTrailRunPaymentWiseService.$inject = ["$http"];

  function rbiBondInterestTrailRunPaymentWiseService($http) {
    var rbiBondInterestTrailRunPaymentWiseService = this;

    rbiBondInterestTrailRunPaymentWiseService.getRbiBondModuleNameDetails = function (requestData) {
      return $http({
        method  : "POST",
        url     : "rbiBond/getModuleDetails",
        data    : requestData,
      });
    };

    rbiBondInterestTrailRunPaymentWiseService.getDetails = function (requestData) {
      return $http({
        method  : "POST",
        url     : "gbmultra/rbibond/reports/getInterestTrailRunReportPayment",
        data    : requestData,
      });
    };
  }
})();
