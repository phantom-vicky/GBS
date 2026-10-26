(function () {
  "use strict";

  angular.module("gbmUltraApp").service("bondActualInterestRunCreditedReportService", bondActualInterestRunCreditedReportService);

  bondActualInterestRunCreditedReportService.$inject = ["$http"];
  function bondActualInterestRunCreditedReportService($http) {
    var bondActualInterestRunCreditedReportService = this;

    bondActualInterestRunCreditedReportService.getDetails = function (requestData) {
      return $http({
        method  :   "POST",
        url     :   "gbmultra/rbibond/reports/getBondActualInterestRunCreditedDetails",
        data    :   requestData,
      });
    };
  }

})();
