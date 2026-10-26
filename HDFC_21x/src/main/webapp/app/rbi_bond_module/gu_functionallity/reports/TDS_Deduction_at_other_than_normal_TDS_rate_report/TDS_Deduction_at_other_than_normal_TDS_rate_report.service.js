(function () {

  'use strict';

  angular.module('gbmUltraApp').service(
    'tdsDeductionTdsRateService',
    tdsDeductionTdsRateService);

  tdsDeductionTdsRateService.$inject = ['$http'];
  function tdsDeductionTdsRateService($http) {
    var tdsDeductionTdsRateService = this;

    tdsDeductionTdsRateService.detailsPage = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getTdsDeductionTdsrate",
        data: requestData,
      });
    }

    tdsDeductionTdsRateService.getTdsDeductionTdsrateDownload= function (requestData) {
        return $http({
            method  : "POST",
            url     : "gbmultra/rbibond/reports/getTdsDeductionTdsrateDownload",
            data    : requestData,
        });
    }

  }
})();