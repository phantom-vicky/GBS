(function () {
  "use strict";

  angular.module("gbmUltraApp").service("rbibondCertificateInvtService", rbibondCertificateInvtService);

  rbibondCertificateInvtService.$inject = ["$http"];

  function rbibondCertificateInvtService($http) {
    var rbibondCertificateInvtService = this;

    rbibondCertificateInvtService.getDetails = getDetails;
    rbibondCertificateInvtService.getPanNo = getPanNo;
    rbibondCertificateInvtService.getBlaNo = getBlaNo;
    rbibondCertificateInvtService.getSubscrId = getSubscrId;

    function getDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getCertificateInvtReport",
        data: requestData,
      });
    }

    function getPanNo(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getPanNo",
        data: requestData,
      });
    }

    function getSubscrId(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getSubscripID",
        data: requestData,
      });
    }

    function getBlaNo(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getBlaAcNo",
        data: requestData,
      });
    }
  }
})();
