(function () {
  "use strict";
  angular.module("gbmUltraApp").service("TransferInOutService", TransferInOutService);
  TransferInOutService.$inject = ["$http"];

  function TransferInOutService($http) {
    var TransferInOutService = this;

    TransferInOutService.getDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getTransferInOutDetails",
        data: requestData,
      });
    };

    TransferInOutService.getDetailsDownload = function (requestData) {
          return $http({
            method: "POST",
            url: "gbmultra/rbibond/reports/getTransferInOutDownload",
            data: requestData,
          });
        };

  }
})();
