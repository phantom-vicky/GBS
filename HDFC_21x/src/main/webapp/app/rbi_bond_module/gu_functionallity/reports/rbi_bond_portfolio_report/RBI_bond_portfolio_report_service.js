(function () {

  'use strict';

  angular.module('gbmUltraApp').service(
    'rbiInetPortfolioService',
    rbiInetPortfolioService);

  rbiInetPortfolioService.$inject = ['$http'];
  function rbiInetPortfolioService($http) {
    var rbiInetPortfolioService = this;


    rbiInetPortfolioService.detailsPage = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/latestReports/inet/portfolio",
        data: requestData,
      });
    }

    rbiInetPortfolioService.getDetailsDownload = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/latestReports/inet/portfolio",
        data: requestData,
      });
    }

  }
})();