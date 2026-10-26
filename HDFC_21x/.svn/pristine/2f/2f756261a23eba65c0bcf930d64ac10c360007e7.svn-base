(function () {
  "use strict";

  angular.module("gbmUltraApp").service("CertificateOfHoldingService", CertificateOfHoldingService);
  CertificateOfHoldingService.$inject = ["$http"];
  function CertificateOfHoldingService($http) {
    var CertificateOfHoldingService = this;
    CertificateOfHoldingService.getDetails = getDetails;
    function getDetails(requestData) {
                return $http({
                    method: 'POST',
                    url: 'gbmultra/rbibond/reports/getCertificateInvtHoldReport',
                    data: requestData
                })
            }
  }
})();