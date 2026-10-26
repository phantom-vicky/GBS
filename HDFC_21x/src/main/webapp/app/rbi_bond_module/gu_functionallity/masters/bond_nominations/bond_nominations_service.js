(function () {
  "use strict";

  angular.module("gbmUltraApp").service("bondNominationsService", bondNominationsService);

  bondNominationsService.$inject = ["$http"];

  function bondNominationsService($http) {
    var bondNominationsService = this;
    bondNominationsService.getTorchDetails = getTorchDetails;
    bondNominationsService.getmodifyDetails = getmodifyDetails;
    bondNominationsService.getInqTorchDetails = getInqTorchDetails;
    //bondNominationsService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
    bondNominationsService.getInquiryTorchDetails = getInquiryTorchDetails;
    bondNominationsService.getVerDelDetails = getVerDelDetails;
    bondNominationsService.blaNo = blaNo;
    bondNominationsService.deleteDetails = deleteDetails;
    bondNominationsService.verifyDetails = verifyDetails;
    bondNominationsService.getCommonMetaDatList = getCommonMetaDatList;

    bondNominationsService.getReferenceData = getReferenceData;
    bondNominationsService.getPanTorchDetails = getPanTorchDetails;

    bondNominationsService.inquiryPanTorch = inquiryPanTorch;
    bondNominationsService.getblaNoInq = getblaNoInq;
    bondNominationsService.getsubscriptionIdInq = getsubscriptionIdInq;

    bondNominationsService.getsubscriptionIdMod = getsubscriptionIdMod;
    bondNominationsService.getMaxNoOfNomineeValidation = getMaxNoOfNomineeValidation;
    bondNominationsService.modifySubmit = modifySubmit;
    bondNominationsService.getAddressByPinCode = getAddressByPinCode;


    function getReferenceData(requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getReferenceData",
        data: requestData,
      });
    }

    function getCommonMetaDatList(requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getReferenceData",
        data: requestData,
      });
    }

    function getmodifyDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/bondNomination/modifySelect",
        data: requestData,
      });
    }

    function getInquiryTorchDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/bondNomination/inquiry",
        data: requestData,
      });
    }

    function getVerDelDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/bondNomination/verifyDeleteSelect",
        data: requestData,
      });
    }

    function verifyDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/bondNomination/verify",
        data: requestData,
      });
    }

    function deleteDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/bondNomination/delete",
        data: requestData,
      });
    }

    function modifySubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/bondNomination/addModify",
        data: requestData,
      });
    }

    function getPanTorchDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/bondNomination/modifyPanTorch",
        data: requestData,
      });
    }
    function inquiryPanTorch(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/bondNomination/inquiryPanTorch",
        data: requestData,
      });
    }

    function getblaNoInq(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/bondNomination/getInqBlaNo",
        data: requestData,
      });
    }

    function getTorchDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/bondNomination/modifySelect",
        data: requestData,
      });
    }

    function getsubscriptionIdMod(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/bondNomination/getsubscriptionIdMod",
        data: requestData,
      });
    }

    function blaNo(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/bondNomination/blaNo",
        data: requestData,
      });
    }

    function getsubscriptionIdInq(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/bondNomination/getsubscriptionIdInq",
        data: requestData,
      });
    }

    function getInqTorchDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/bondNomination/inquiry",
        data: requestData,
      });
    }

    function getMaxNoOfNomineeValidation(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/bondNomination/nomineeValidation",
        data: requestData,
      });
    }

    bondNominationsService.getRemarksList = function (requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getRemarksList",
        data: requestData,
      });
    }

     function getAddressByPinCode(requestData) {
              return $http({
                method: "POST",
                url: "gbmultra/rbiBond/bondNomination/fetchState",
                data: requestData,
              });
            }
  }
})();
