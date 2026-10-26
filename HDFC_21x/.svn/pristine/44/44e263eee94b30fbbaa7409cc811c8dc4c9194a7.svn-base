(function () {
  "use strict";

  angular.module("gbmUltraApp").service("preIntimationAdviceNoticeService", preIntimationAdviceNoticeService);

  preIntimationAdviceNoticeService.$inject = ["$http"];

  function preIntimationAdviceNoticeService($http) {
    var preIntimationAdviceNoticeService = this;

    preIntimationAdviceNoticeService.getDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getPreIntimationAdviceNoticeDetails",
        data: requestData,
      });
    };
        preIntimationAdviceNoticeService.getReferenceData = function (requestData) {
          return $http({
            method: "POST",
            url: "rbiBond/getReferenceData",
            data: requestData,
          });
        };
  }
})();
