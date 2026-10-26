(function () {
  "use strict";

  angular.module("gbmUltraApp").service("jointTypeChangeThreeToTwoReportService", jointTypeChangeThreeToTwoReportService);

  jointTypeChangeThreeToTwoReportService.$inject = ["$http"];

  function jointTypeChangeThreeToTwoReportService($http) {
    var jointTypeChangeThreeToTwoReportService = this;

    jointTypeChangeThreeToTwoReportService.getDetails = getDetails;

    function getDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getJointTypeDetails",
        data: requestData,
      });
    }
  }
})();
