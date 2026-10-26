(function () {
  "use strict";

  angular.module("gbmUltraApp").service("rbibondKartaCoparcenersService", rbibondKartaCoparcenersService);

  rbibondKartaCoparcenersService.$inject = ["$http"];

  function rbibondKartaCoparcenersService($http) {
    var rbibondKartaCoparcenersService = this;

    rbibondKartaCoparcenersService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
    rbibondKartaCoparcenersService.deleteDetails = deleteDetails;
    rbibondKartaCoparcenersService.verifyDetails = verifyDetails;
    rbibondKartaCoparcenersService.modifyDetails = modifyDetails;
    rbibondKartaCoparcenersService.getReferenceData = getReferenceData;
    rbibondKartaCoparcenersService.getModifyTorchDetails = getModifyTorchDetails;
    rbibondKartaCoparcenersService.getInquiryTorchDetails = getInquiryTorchDetails;
    rbibondKartaCoparcenersService.getPanTorchDetails = getPanTorchDetails;
    rbibondKartaCoparcenersService.getVerDelTorchDetails = getVerDelTorchDetails;
    rbibondKartaCoparcenersService.getModifyDetails = getModifyDetails;
    rbibondKartaCoparcenersService.getInquiryDetails = getInquiryDetails;
    rbibondKartaCoparcenersService.getVerDelDetails = getVerDelDetails;
    rbibondKartaCoparcenersService.getModifyBlaTorch = getModifyBlaTorch;
    rbibondKartaCoparcenersService.getPanNo = getPanNo;
    rbibondKartaCoparcenersService.getCommonMetaDatList= getCommonMetaDatList;

    function getPanNo(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/ledgerKarta/inquiryPan",
        data: requestData,
      });
    }

    rbibondKartaCoparcenersService.getRemarksList = function (requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getRemarksList",
        data: requestData,
      });
    };

    function getRbiBondModuleNameDetails(requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getModuleDetails",
        data: requestData,
      });
    }

    function getModifyTorchDetails(requestData) {
      return $http({
        method: "post",
        url: "gbmultra/rbiBond/ledgerKarta/getModifyBla",
        data: requestData,
      });
    }
    function getModifyBlaTorch(requestData) {
      return $http({
        method: "post",
        url: "gbmultra/rbiBond/ledgerKarta/getModifyBlaTorch",
        data: requestData,
      });
    }

    function getInquiryTorchDetails(requestData) {
      return $http({
        method: "post",
        url: "gbmultra/rbiBond/ledgerKarta/getInquiryBla",
        data: requestData,
      });
    }

    function getPanTorchDetails(requestData) {
      return $http({
        method: "post",
        url: "gbmultra/rbiBond/ledgerKarta/getTorchPan",
        data: requestData,
      });
    }

    function getVerDelTorchDetails(requestData) {
      return $http({
        method: "post",
        url: "gbmultra/rbiBond/ledgerKarta/getVerDelBla",
        data: requestData,
      });
    }

    function getReferenceData(requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getReferenceData",
        data: requestData,
      });
    }

    function getModifyDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/ledgerKarta/modifySelect",
        data: requestData,
      });
    }

    function modifyDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/ledgerKarta/addModify",
        data: requestData,
      });
    }

    function getInquiryDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/ledgerKarta/inquiry",
        data: requestData,
      });
    }

    function getVerDelDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/ledgerKarta/verDelSelect",
        data: requestData,
      });
    }

    function verifyDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/ledgerKarta/verify",
        data: requestData,
      });
    }

    function deleteDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/ledgerKarta/delete",
        data: requestData,
      });
    }


    function getCommonMetaDatList(requestData) {
          return $http({
            method: "POST",
            url: "/rbiBond/getReferenceData",
            data: requestData,
          });
        }
  }
})();