(function () {
  "use strict";

  angular.module("gbmUltraApp").service("rbibondTocSlabMaintenanceService", rbibondTocSlabMaintenanceService);

  rbibondTocSlabMaintenanceService.$inject = ["$http"];

  function rbibondTocSlabMaintenanceService($http) {
    var rbibondTocSlabMaintenanceService = this;

    rbibondTocSlabMaintenanceService.submitAddForm = submitAddForm;
    rbibondTocSlabMaintenanceService.getAddDetails = getAddDetails;
    rbibondTocSlabMaintenanceService.submitModifyForm = submitModifyForm;
    rbibondTocSlabMaintenanceService.submitVerifyForm = submitVerifyForm;
    rbibondTocSlabMaintenanceService.submitDeleteForm = submitDeleteForm;

    rbibondTocSlabMaintenanceService.checkRecordExisted = checkRecordExisted;
    rbibondTocSlabMaintenanceService.VerifyDeleteTocSlab = VerifyDeleteTocSlab;
    rbibondTocSlabMaintenanceService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
    rbibondTocSlabMaintenanceService.getInquirySelectDetails = getInquirySelectDetails;
    rbibondTocSlabMaintenanceService.getRbiBondRoundFactor = getRbiBondRoundFactor;
    rbibondTocSlabMaintenanceService.modifySelect = modifySelect;
    rbibondTocSlabMaintenanceService.getRemarksList = getRemarksList;

    //New
    function checkRecordExisted(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/tocSlab/selectVerifySlab",
        headers: {
          "Content-Type": "application/json",
        },
        data: requestData,
      });
    }
    //Add submit
    function submitAddForm(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/tocSlab/save",
        headers: {
          "Content-Type": "application/json",
        },
        data: requestData,
      });
    }

    //getAddDetails
    function getAddDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/tocSlab/getAddDetails",
        headers: {
          "Content-Type": "application/json",
        },
        data: requestData,
      });
    }
    //Get Modify Select Details
    function modifySelect(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/tocSlab/selectModifySlab",
        headers: {
          "Content-Type": "application/json",
        },
        data: requestData,
      });
    }
    //Modify Submit
    function submitModifyForm(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/tocSlab/modify",
        headers: {
          "Content-Type": "application/json",
        },
        data: requestData,
      });
    }
    //Get Verify/Delete Select Details
    function VerifyDeleteTocSlab(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/tocSlab/selectVerifySlab",
        headers: {
          "Content-Type": "application/json",
        },
        data: requestData,
      });
    }
    //Verify Submit
    function submitVerifyForm(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/tocSlab/verify",
        headers: {
          "Content-Type": "application/json",
        },
        data: requestData,
      });
    }
    function submitDeleteForm(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/tocSlab/delete",
        headers: {
          "Content-Type": "application/json",
        },
        data: requestData,
      });
    }
    //Get Inquiry Select Details
    function getInquirySelectDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/tocSlab/inquiry",
        headers: {
          "Content-Type": "application/json",
        },
        data: requestData,
      });
    }

    function getRbiBondModuleNameDetails(requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getModuleDetails",
        data: requestData,
      });
    }
    function getRbiBondRoundFactor(requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getReferenceData",
        data: requestData,
      });
    }

    function getRemarksList(requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getRemarksList",
        data: requestData,
      });
    }
  }
})();