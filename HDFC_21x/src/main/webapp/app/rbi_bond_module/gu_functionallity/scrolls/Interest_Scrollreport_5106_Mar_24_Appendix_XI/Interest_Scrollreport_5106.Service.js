(function () {
  "use strict";

  angular.module("gbmUltraApp").service("InterestScrollReportService", InterestScrollReportService);

  InterestScrollReportService.$inject = ["$http"];

  function InterestScrollReportService($http) {
    var InterestScrollReportService = this;

    InterestScrollReportService.getDetails = function (requestData) {
      return $http({
        method  : "POST",
        url     : "gbmultra/rbibond/scrolls/getInterestScrollReport5106Details",
        data    : requestData,
      });
    };

    InterestScrollReportService.getReferenceData = function (requestData) {
      return $http({
        method  : "POST",
        url     : "rbiBond/getReferenceData",
        data    : requestData,
      });
    };
  }
})();
