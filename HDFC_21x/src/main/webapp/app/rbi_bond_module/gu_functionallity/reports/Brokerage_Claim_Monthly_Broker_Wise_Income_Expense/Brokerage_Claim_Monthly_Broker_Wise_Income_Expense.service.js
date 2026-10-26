(function () {
  "use strict";

  angular.module("gbmUltraApp").service("BrokerageClaimService", BrokerageClaimService);

  BrokerageClaimService.$inject = ["$http"];
  function BrokerageClaimService($http) {
    var BrokerageClaimService = this;

    BrokerageClaimService.detailsPage = detailsPage;
    BrokerageClaimService.getReferenceData = getReferenceData;
    BrokerageClaimService.detailsPageDownload = detailsPageDownload;

    function detailsPage(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getBrokerageClaimMonthly",
        data: requestData,
      });
    }

    function detailsPageDownload(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getBrokerageClaimMonthlyDownload",
        data: requestData,
      });
    }

    function getReferenceData(referenceId) {
      return $http({
        method: "POST",
        url: "rbiBond/getReferenceData",
        data: referenceId,
      });
    }
  }
})();
