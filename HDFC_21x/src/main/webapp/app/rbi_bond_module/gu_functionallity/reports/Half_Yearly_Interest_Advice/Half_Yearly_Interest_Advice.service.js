(function () {
  "use strict";

  angular.module("gbmUltraApp").service("HalfYearlyInterestAdviceService", HalfYearlyInterestAdviceService);
  HalfYearlyInterestAdviceService.$inject = ["$http"];
  function HalfYearlyInterestAdviceService($http) {
    var HalfYearlyInterestAdviceService = this;

    HalfYearlyInterestAdviceService.getDetails = function (requestData) {
      return $http({
        method  : "POST",
        url     : "gbmultra/rbibond/reports/getHalfYearlyInterestAdvice",
        data    : requestData,
      });
    };
    HalfYearlyInterestAdviceService.getReferenceData = function (referenceId) {
      return $http({
        method  : "POST",
        url     : "rbiBond/getReferenceData",
        data    : referenceId,
      });
    };
  }
})();
