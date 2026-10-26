(function () {
  "use strict";

  angular.module("gbmUltraApp").service("SchemeWiseOutstandingBalanceService", SchemeWiseOutstandingBalanceService);

  SchemeWiseOutstandingBalanceService.$inject = ["$http"];

  function SchemeWiseOutstandingBalanceService($http) {
    var SchemeWiseOutstandingBalanceService = this;

    // flag: "S" - Scheme List, "D" - Details, "R" - Report Download
    SchemeWiseOutstandingBalanceService.getSchemeWiseOutstandingBalanceDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/scrolls/getSchemeWiseOutstandingBalanceDetails",
        data: requestData,
      });
    };
  }
})();
