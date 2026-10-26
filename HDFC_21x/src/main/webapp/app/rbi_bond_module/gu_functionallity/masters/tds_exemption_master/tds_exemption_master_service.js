(function () {
  "use strict";

  angular.module("gbmUltraApp").service("tdsExcemptionMasterService", tdsExcemptionMasterService);

  tdsExcemptionMasterService.$inject = ["$http"];

  function tdsExcemptionMasterService($http) {
    var tdsExcemptionMasterService = this;

    tdsExcemptionMasterService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

    tdsExcemptionMasterService.getInquiry = getInquiry;
    tdsExcemptionMasterService.getVerify = getVerify;
    tdsExcemptionMasterService.getVerifySubmit = getVerifySubmit;
    tdsExcemptionMasterService.getDeleteSubmit = getDeleteSubmit;
    tdsExcemptionMasterService.getDelete = getDelete;

    tdsExcemptionMasterService.getAddPanNumberDtls = getAddPanNumberDtls;
    tdsExcemptionMasterService.getVerDelPanNumberDtls = getVerDelPanNumberDtls;
    tdsExcemptionMasterService.getModPanNumberDtls = getModPanNumberDtls;
    tdsExcemptionMasterService.getInqPanNumberDtls = getInqPanNumberDtls;
    tdsExcemptionMasterService.getVersionNoDetails = getVersionNoDetails;
    tdsExcemptionMasterService.getAdd = getAdd;
    tdsExcemptionMasterService.getModify = getModify;
    tdsExcemptionMasterService.getAddSubmit = getAddSubmit;
    tdsExcemptionMasterService.getFinancialYear = getFinancialYear;
    tdsExcemptionMasterService.getcertAgeValidation = getcertAgeValidation;
    tdsExcemptionMasterService.getcertSeqNumber = getcertSeqNumber;
    tdsExcemptionMasterService.getModifySubmit = getModifySubmit;

    function getRbiBondModuleNameDetails(requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getModuleDetails",
        data: requestData,
      });
    }

    function getAddPanNumberDtls(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/tdsexcemptionmaster/addSelect",
        data: requestData,
      });
    }

    function getVerDelPanNumberDtls(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/tdsexcemptionmaster/verDelSelect",
        data: requestData,
      });
    }
    function getModPanNumberDtls(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/tdsexcemptionmaster/modifySelect",
        data: requestData,
      });
    }

    function getInqPanNumberDtls(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/tdsexcemptionmaster/inquirySelect",
        data: requestData,
      });
    }

    function getVersionNoDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/tdsexcemptionmaster/getVersionNoDetails",
        data: requestData,
      });
    }

    function getPanDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/tdsexcemptionmaster/getPanNoDetails",
        data: requestData,
      });
    }

    function getPanNumberDtls(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/tdsexcemptionmaster/getPanNoDetails",
        data: requestData,
      });
    }

    function getAddSubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/tdsexcemptionmaster/add",
        data: requestData,
      });
    }

    function getModifySubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/tdsexcemptionmaster/modify",
        data: requestData,
      });
    }

    function getAdd(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/tdsexcemptionmaster/addDetails",
        data: requestData,
      });
    }

    function getModify(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/tdsexcemptionmaster/modifyDetails",
        data: requestData,
      });
    }

    function getFinancialYear() {
      return $http({
        method: "post",
        url: "gbmultra/rbibond/tdsexcemptionmaster/financialyear",
      });
    }

    function getcertAgeValidation(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/tdsexcemptionmaster/ageValidation",
        data: requestData,
      });
    }
    function getcertSeqNumber(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/tdsexcemptionmaster/getCertNumber",
        data: requestData,
      });
    }

    function getVerify(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/tdsexcemptionmaster/verifyDetails",
        data: requestData,
      });
    }

    function getVerifySubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/tdsexcemptionmaster/verify",
        data: requestData,
      });
    }

    function getDelete(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/tdsexcemptionmaster/deleteDetails",
        data: requestData,
      });
    }

    function getDeleteSubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/tdsexcemptionmaster/delete",
        data: requestData,
      });
    }
    function getInquiry(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/tdsexcemptionmaster/inquiryDetails",
        data: requestData,
      });
    }

    tdsExcemptionMasterService.getRemarksList = function (requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getRemarksList",
        data: requestData,
      });
    };
  }
})();
