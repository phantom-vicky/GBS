(function () {
  "use strict";

  angular.module("gbmUltraApp").service("acknowledgementConfirmationService", acknowledgementConfirmationService);

  acknowledgementConfirmationService.$inject = ["$http"];

  function acknowledgementConfirmationService($http) {
    var acknowledgementConfirmationService = this;

    acknowledgementConfirmationService.getDetails = getDetails;

    function getDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getAcknowledgementconfirmation",
        data: requestData,
      });
    }
  }
})();
