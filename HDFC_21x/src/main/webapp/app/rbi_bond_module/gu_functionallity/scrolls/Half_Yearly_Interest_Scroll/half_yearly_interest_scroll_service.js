(function () {
  "use strict";

  angular.module("gbmUltraApp").service("halfYearlyInterestScrollService", halfYearlyInterestScrollService);

  halfYearlyInterestScrollService.$inject = ["$http"];

  function halfYearlyInterestScrollService($http) {
    var halfYearlyInterestScrollService = this;

    halfYearlyInterestScrollService.getDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/scrolls/getHalfYearlyInterestScrollDetails",
        data: requestData,
      });
    };

    halfYearlyInterestScrollService.downloadFile = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/scrolls/downloadHalfYearlyInterestScrollFile",
        data: requestData,
      });
    };

    halfYearlyInterestScrollService.getHalfYearMonthsForScheme = function (schemeCode) {
      return $http({
        method: "POST",
        url: "apis/bond-actual-payments/add-details",
        data: {
          schemeCode: schemeCode,
          detailsOrSubmit: "details",
        },
      });
    };
  }
})();
