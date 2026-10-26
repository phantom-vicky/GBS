(function () {
  "use strict";

  angular.module("gbmUltraApp").service("blaModificationService", blaModificationService);

  blaModificationService.$inject = ["$http"];

  function blaModificationService($http) {
    var blaModificationService = this;

    blaModificationService.getDetails = function (requestData) {
      return $http({
        method  : "POST",
        url     : "gbmultra/rbibond/reports/getBlaModificationReport",
        data    : requestData,
      });
    };

    blaModificationService.getDetailsDownload = function (requestData) {
      return $http({
        method  : "POST",
        url     : "gbmultra/rbibond/reports/getBlaModificationReportDownload",
        data    : requestData,
      });
    };

    blaModificationService.getReferenceData = function (requestData) {
      return $http({
        method  : "POST",
        url     : "rbiBond/getReferenceData",
        data    : requestData,
      });
    };
  }
})();
