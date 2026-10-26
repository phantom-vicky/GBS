(function () {
  "use strict";

  angular
    .module("gbmUltraApp")
    .service("rbibondBondLedgeAccService", rbibondBondLedgeAccService);
  rbibondBondLedgeAccService.$inject = ["$http"];

  function rbibondBondLedgeAccService($http) {
    var rbibondBondLedgeAccService = this;

    rbibondBondLedgeAccService.getRegBondLedgeAcc = getRegBondLedgeAcc;
    rbibondBondLedgeAccService.getRbiBondModuleNameDetails =
      getRbiBondModuleNameDetails;
    rbibondBondLedgeAccService.getBlaNum = getBlaNum;
    rbibondBondLedgeAccService.getBondDet = getBondDet;
    rbibondBondLedgeAccService.getPantorch = getPantorch;
    rbibondBondLedgeAccService.getRegBondLedgeAccDownload= getRegBondLedgeAccDownload;

    function getRbiBondModuleNameDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getBondLedgerAccDet",
        data: requestData,
      });
    }

    function getRegBondLedgeAcc(requestData) {
      return $http({
        method: "post",
        url: "gbmultra/rbibond/reports/getBondLedgerAccDet",
        data: requestData,
      });
    }

    function getRegBondLedgeAccDownload(requestData) {
      return $http({
        method: "post",
        url: "gbmultra/rbibond/reports/getRegBondLedgeAccDownload",
        data: requestData,
      });
    }

    function getPantorch(requestData) {
      return $http({
        method: "post",
        url: "gbmultra/rbibond/reports/getBondLedgerAccDet",
        data: requestData,
      });
    }

    function getBlaNum(requestData) {
      return $http({
        method: "post",
        url: "gbmultra/rbibond/reports/getBondLedgerAccDet",
        data: requestData,
      });
    }

    function getBondDet(requestData) {
      return $http({
        method: "post",
        url: "gbmultra/rbibond/reports/getBondLedgerAccDet",
        data: requestData,
      });
    }
  }
})();