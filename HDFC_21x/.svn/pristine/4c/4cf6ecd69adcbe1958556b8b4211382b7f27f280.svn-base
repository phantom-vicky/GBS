(function () {
  "use strict";

  angular.module("gbmUltraApp").service("ModeOfHoldingService", ModeOfHoldingService);

  ModeOfHoldingService.$inject = ["$http"];

  function ModeOfHoldingService($http) {
    var ModeOfHoldingService = this;
    ModeOfHoldingService.getReferenceData = getReferenceData;


    function getReferenceData(referenceId) {
                                 return $http({
                                     method  : 'POST',
                                     url     : 'rbiBond/getReferenceData',
                                     data    : referenceId
                                 });
                }

    ModeOfHoldingService.getDetails = function (requestData) {
      return $http({
        method  :   "POST",
        url     :   "gbmultra/rbibond/reports/getModeOfHoldingChangeDetails",
        data    :   requestData,
      });
    }

    ModeOfHoldingService.getModeOfHoldingDownload= function (requestData) {
        return $http({
            method    : "POST",
            url       : "gbmultra/rbibond/reports/getModeOfHoldingDownload",
            data      : requestData,
        });
    }

  }
})();
