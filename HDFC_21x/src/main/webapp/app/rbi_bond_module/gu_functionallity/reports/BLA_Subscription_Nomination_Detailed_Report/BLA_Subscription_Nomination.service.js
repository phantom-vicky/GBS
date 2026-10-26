(function () {
  "use strict";
  angular.module("gbmUltraApp").service("BLASubsNominationService", BLASubsNominationService);
  BLASubsNominationService.$inject = ["$http"];

  function BLASubsNominationService($http) {
    var BLASubsNominationService = this;

    BLASubsNominationService.getDetails = function getDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getBLASubscriptionNominationDetails",
        data: requestData,
      });
    };
    BLASubsNominationService.getReferenceData = function (requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getReferenceData",
        data: requestData,
      });
    };
  }
})();
