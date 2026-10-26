(function () {
  "use strict";

  angular.module("gbmUltraApp").service("brokerMasterRbiBondService", brokerMasterRbiBondService);

  brokerMasterRbiBondService.$inject = ["$http"];

  function brokerMasterRbiBondService($http) {
    var brokerMasterRbiBondService = this;

    brokerMasterRbiBondService.submitAddForm = submitAddForm;
    brokerMasterRbiBondService.submitModifyForm = submitModifyForm;
    brokerMasterRbiBondService.submitVerifyForm = submitVerifyForm;
    brokerMasterRbiBondService.submitDeleteForm = submitDeleteForm;
    brokerMasterRbiBondService.getNonHdfcTorch = getNonHdfcTorch;

    brokerMasterRbiBondService.addValidation = addValidation;
    brokerMasterRbiBondService.getInquirySelectDetails = getInquirySelectDetails;
    brokerMasterRbiBondService.getVerifyDelSelectDetails = getVerifyDelSelectDetails;
    brokerMasterRbiBondService.getModifySelect = getModifySelect;
    brokerMasterRbiBondService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
    brokerMasterRbiBondService.getBranches = getBranches;
    brokerMasterRbiBondService.getMetaData = getMetaData;
    brokerMasterRbiBondService.getNonHdfcTorch1 = getNonHdfcTorch1;
    brokerMasterRbiBondService.getNonHdfcBranchTorch = getNonHdfcBranchTorch;

    function getNonHdfcTorch1(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/blaCumSub/getNonHdfcTorch",
        headers: {
          "Content-Type": "application/json",
        },
        data: requestData,
      });
    }

    function getNonHdfcBranchTorch(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/blaCumSub/getNonHdfcBranchTorch",
        headers: {
          "Content-Type": "application/json",
        },
        data: requestData,
      });
    }
    function getMetaData(requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getReferenceData",
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

    //New
    function getBranches(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/brokerMaster/getBranchCode",
        headers: {
          "Content-Type": "application/json",
        },
        data: requestData,
      });
    }

    function addValidation(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/brokerMaster/addValidation",
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
        url: "gbmultra/rbiBond/brokerMaster/add",
        headers: {
          "Content-Type": "application/json",
        },
        data: requestData,
      });
    }
    //Get Modify Select Details
    function getVerifyDelSelectDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/brokerMaster/verifyDeleteDetails",
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
        url: "gbmultra/rbiBond/brokerMaster/modifySubmit",
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
        url: "gbmultra/rbiBond/brokerMaster/verify",
        headers: {
          "Content-Type": "application/json",
        },
        data: requestData,
      });
    }
    function submitDeleteForm(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/brokerMaster/delete",
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
        url: "gbmultra/rbiBond/brokerMaster/inquiryDetails",
        headers: {
          "Content-Type": "application/json",
        },
        data: requestData,
      });
    }

    //Get Modify Select Details
    function getModifySelect(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/brokerMaster/modifyDetails",
        headers: {
          "Content-Type": "application/json",
        },
        data: requestData,
      });
    }

    brokerMasterRbiBondService.getRemarksList = function (requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getRemarksList",
        data: requestData,
      });
    };

    function getNonHdfcTorch(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/brokerMaster/getNonHdfcTorch",
        headers: {
          "Content-Type": "application/json",
        },
        data: requestData,
      });
    }
  }
})();
