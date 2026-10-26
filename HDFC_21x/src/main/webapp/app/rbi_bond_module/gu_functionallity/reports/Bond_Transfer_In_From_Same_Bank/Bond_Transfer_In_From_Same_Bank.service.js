(function () {
  "use strict";

  angular.module("gbmUltraApp").service("bondTransferInService", bondTransferInService);

  bondTransferInService.$inject = ["$http"];

  function bondTransferInService($http) {
    var bondTransferInService = this;

    bondTransferInService.getDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getBondTransferIn",
        data: requestData,
      });
    };
  }
})();
