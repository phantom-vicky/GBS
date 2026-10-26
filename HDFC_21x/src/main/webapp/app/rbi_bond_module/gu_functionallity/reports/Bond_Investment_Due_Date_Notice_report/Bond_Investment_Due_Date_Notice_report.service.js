(function () {
  "use strict";

  angular.module("gbmUltraApp").service("BondInvestDueDateNoticeService", BondInvestDueDateNoticeService);

  BondInvestDueDateNoticeService.$inject = ["$http"];

  function BondInvestDueDateNoticeService($http) {
    var BondInvestDueDateNoticeService = this;

    BondInvestDueDateNoticeService.getDetails = function (requestData) {
      return $http({
        method  :   "POST",
        url     :   "gbmultra/rbibond/reports/getBondInvestmentDueNoticeReport",
        data    :   requestData,
      });
    }
  }
})();
