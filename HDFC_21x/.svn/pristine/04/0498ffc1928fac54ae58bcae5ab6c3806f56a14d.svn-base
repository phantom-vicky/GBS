(function () {
  "use strict";

  angular.module("gbmUltraApp").service("bondInquiryReportService", bondInquiryReportService);

  bondInquiryReportService.$inject = ["$http"];

  function bondInquiryReportService($http) {
    var bondInquiryReportService = this;

    bondInquiryReportService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
    bondInquiryReportService.getHolderTypeDetails = getHolderTypeDetails;
    bondInquiryReportService.getNriAccDetails = getNriAccDetails;

    function getRbiBondModuleNameDetails(requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getModuleDetails",
        data: requestData,
      });
    }

    function getHolderTypeDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getHolderTypeDetails",
        data: requestData,
      });
    }

    function getNriAccDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getNriAccDetails",
        data: requestData,
      });
    }
  }
})();
