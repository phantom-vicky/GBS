(function () {
  "use strict";

  angular.module("gbmUltraApp").service("postIntimationAdviceNoticeService", postIntimationAdviceNoticeService);

  postIntimationAdviceNoticeService.$inject = ["$http"];

  function postIntimationAdviceNoticeService($http) {
    var postIntimationAdviceNoticeService = this;

    postIntimationAdviceNoticeService.getDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getPostIntimationAdviceNoticeDetails",
        data: requestData,
      });
    };
        postIntimationAdviceNoticeService.getReferenceData = function (requestData) {
          return $http({
            method: "POST",
            url: "rbiBond/getReferenceData",
            data: requestData,
          });
        };
  }
})();
