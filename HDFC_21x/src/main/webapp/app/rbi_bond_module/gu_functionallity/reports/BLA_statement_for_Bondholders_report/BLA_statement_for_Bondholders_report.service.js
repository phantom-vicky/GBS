(function () {
  "use strict";

  angular.module("gbmUltraApp").service("blaStatementBondHolderService", blaStatementBondHolderService);

  blaStatementBondHolderService.$inject = ["$http"];

  function blaStatementBondHolderService($http) {
    var blaStatementBondHolderService = this;

    blaStatementBondHolderService.getDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getBlaStatementReport",
        data: requestData,
      });
    };
  }
})();
