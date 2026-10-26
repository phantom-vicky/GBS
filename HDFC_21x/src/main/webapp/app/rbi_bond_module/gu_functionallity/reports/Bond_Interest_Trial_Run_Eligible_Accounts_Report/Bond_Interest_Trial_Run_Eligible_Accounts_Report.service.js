(function () {
  "use strict";

  angular.module("gbmUltraApp").service("bondInterestTrialRunService", bondInterestTrialRunService);

  bondInterestTrialRunService.$inject = ["$http"];

  function bondInterestTrialRunService($http) {
    var bondInterestTrialRunService = this;

    bondInterestTrialRunService.getDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getBondInterestTrialRunEligibleAccountsReport",
        data: requestData,
      });
    }

     bondInterestTrialRunService.getDownload = function (requestData) {
          return $http({
            method: "POST",
            url: "gbmultra/rbibond/reports/getBondInterestTrialRunEligibleAccountsReport/getDownload",
            data: requestData,
          });
        }

  }
})();
