(function () {
  "use strict";

  angular.module("gbmUltraApp").service("PortfolioService", PortfolioService);

  PortfolioService.$inject = ["$http"];

  function PortfolioService($http) {
    var PortfolioService = this;

    PortfolioService.getDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/latestReports/getPortfolio",
        data: requestData,
      });
    };

  }
})();
