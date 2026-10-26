(function () {
  "use strict";

  angular.module("gbmUltraApp").service("casAckNumMaintenanceService", casAckNumMaintenanceService);

  casAckNumMaintenanceService.$inject = ["$http"];

  function casAckNumMaintenanceService($http) {
    var casAckNumMaintenanceService = this;
    casAckNumMaintenanceService.getAddDetails = getAddDetails;
    casAckNumMaintenanceService.getSubsDateDetails = getSubsDateDetails;
    casAckNumMaintenanceService.getSubsDateDetailsVerify = getSubsDateDetailsVerify;
    casAckNumMaintenanceService.getSubsIdDetailsModify = getSubsIdDetailsModify;
    casAckNumMaintenanceService.getSubsIdDetails = getSubsIdDetails;
    casAckNumMaintenanceService.getVerifyDeleteSelect = getVerifyDeleteSelect;
    casAckNumMaintenanceService.getInquiryDetails = getInquiryDetails;
    casAckNumMaintenanceService.getModifySelect = getModifySelect;
    casAckNumMaintenanceService.submitAddForm = submitAddForm;
    casAckNumMaintenanceService.submitVerifyForm = submitVerifyForm;
    casAckNumMaintenanceService.submitDeleteForm = submitDeleteForm;
    casAckNumMaintenanceService.getModifySubmit = getModifySubmit;
    casAckNumMaintenanceService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
    casAckNumMaintenanceService.getSubsDateInq = getSubsDateInq;
    casAckNumMaintenanceService.getSubsDateModify = getSubsDateModify;
    casAckNumMaintenanceService.getSubsDateDel = getSubsDateDel;

    casAckNumMaintenanceService.getReferenceData = getReferenceData;
    casAckNumMaintenanceService.getSolDetails = getSolDetails;
    casAckNumMaintenanceService.getValidationAdd = getValidationAdd;
    casAckNumMaintenanceService.getSubCodeList = getSubCodeList;
    casAckNumMaintenanceService.getDepositAmountValidation = getDepositAmountValidation;
    casAckNumMaintenanceService.getReferenceData = getReferenceData;
    casAckNumMaintenanceService.getForacidAccountNumber = getForacidAccountNumber;
    casAckNumMaintenanceService.getCbsDetails = getCbsDetails;
    casAckNumMaintenanceService.getApplicationTorch = getApplicationTorch;
    casAckNumMaintenanceService.getBrokerTorch = getBrokerTorch;
    casAckNumMaintenanceService.getCommonMetaDatList = getCommonMetaDatList;
    casAckNumMaintenanceService.getOtherBankDetails = getOtherBankDetails;

    function getRbiBondModuleNameDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/blaCumSub/getModuleDetails",
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

    function getReferenceData(requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getReferenceData",
        data: requestData,
      });
    }
    function getSolDetails(requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getReferenceData",
        data: requestData,
      });
    }
    function getValidationAdd(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/subsWrkFlow/addValidation",
        data: requestData,
      });
    }
    function getApplicationTorch(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/subsWrkFlow/getApplicationDetails",
        data: requestData,
      });
    }
    function getBrokerTorch(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/subsWrkFlow/getBrokerDetails",
        data: requestData,
      });
    }
    function getDepositAmountValidation(requestData) {
      return $http({
        method: "POST",
        url: "scss/account/opening/addValidation",
        data: requestData,
      });
    }

    function getSubCodeList() {
      return $http({
        method: "POST",
        url: "scss/subCodeList",
      });
    }
    function getReferenceData(requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getReferenceData",
        data: requestData,
      });
    }

    function submitAddForm(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/cacAckNum/add",
        data: requestData,
      });
    }

    function getForacidAccountNumber(AccNo, solid) {
      return $http({
        method: "post",
        url: "gbmultra/cbs/integration/bacidaccountdetails",
        data: { bacid: AccNo, solId: solid },
      });
    }

    function getCbsDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/subsWrkFlow/getTranValidation",
        data: requestData,
      });
    }
    function getOtherBankDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/subsWrkFlow/getOtherBankDetails",
        data: requestData,
      });
    }
    function getVerifyDeleteSelect(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/cacAckNum/verifyDeleteSelect",
        data: requestData,
      });
    }

    function getInquiryDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/cacAckNum/inquiry",
        data: requestData,
      });
    }

    function submitVerifyForm(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/cacAckNum/verify",
        data: requestData,
      });
    }

    function submitDeleteForm(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/cacAckNum/delete",
        data: requestData,
      });
    }
    function getModifySelect(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/cacAckNum/modifySelect",
        data: requestData,
      });
    }
    function getModifySubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/cacAckNum/modify",
        data: requestData,
      });
    }

    function getSubsIdDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/cacAckNum/addDet",
        data: requestData,
      });
    }

    function getSubsIdDetailsModify(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/cacAckNum/modifySelect",
        data: requestData,
      });
    }

    function getSubsDateInq(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/cacAckNum/inquiry",
        data: requestData,
      });
    }

    function getSubsDateDel(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/cacAckNum/verifyDeleteSelect",
        data: requestData,
      });
    }

    function getSubsDateModify(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/cacAckNum/modifySelect",
        data: requestData,
      });
    }
    function getSubsDateDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/cacAckNum/addDet",
        data: requestData,
      });
    }

    function getSubsDateDetailsVerify(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/cacAckNum/verifyDeleteSelect",
        data: requestData,
      });
    }

    function getAddDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/cacAckNum/addDet",
        data: requestData,
      });
    }

    casAckNumMaintenanceService.getRemarksList = function (requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getRemarksList",
        data: requestData,
      });
    };
  }
})();
