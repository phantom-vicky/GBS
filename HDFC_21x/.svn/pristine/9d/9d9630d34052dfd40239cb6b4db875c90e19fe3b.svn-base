(function () {
  "use strict";

  angular.module("gbmUltraApp").service("StatementOfBrokerageService", StatementOfBrokerageService);

  StatementOfBrokerageService.$inject = ["$http"];

  function StatementOfBrokerageService($http) {
    var StatementOfBrokerageService = this;

    StatementOfBrokerageService.getDetails = function (requestData) {
      return $http({
        method  : "POST",
        url     : "gbmultra/rbibond/reports/getStatementBrokerageDetails",
        data    : requestData,
      });
    };

    StatementOfBrokerageService.getDetailsDownload = function (requestData) {
      return $http({
        method  : "POST",
        url     : "gbmultra/rbibond/reports/getStatementBrokerageDownload",
        data    : requestData,
      });
    };

  }
})();
