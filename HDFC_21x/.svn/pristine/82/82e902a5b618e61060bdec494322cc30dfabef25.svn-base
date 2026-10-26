(function () {
  "use strict";

  angular.module("gbmUltraApp").service("jointTypeThreeToTwoService", jointTypeThreeToTwoService);

  jointTypeThreeToTwoService.$inject = ["$http"];

  function jointTypeThreeToTwoService($http) {
    var jointTypeThreeToTwoService = this;
    jointTypeThreeToTwoService.getReferenceData = getReferenceData;
    jointTypeThreeToTwoService.addSubmit = addSubmit;
    jointTypeThreeToTwoService.fetchCbsCustId = fetchCbsCustId;
    jointTypeThreeToTwoService.fetchBlaAccNoDetailsfetchBlaAccNoDetails = fetchBlaAccNoDetails;
    jointTypeThreeToTwoService.fetchGuardianDetails = fetchGuardianDetails;
    jointTypeThreeToTwoService.getCommonMetaDatList = getCommonMetaDatList;
    jointTypeThreeToTwoService.modifySelect = modifySelect;
    jointTypeThreeToTwoService.modifySubmit = modifySubmit;
    jointTypeThreeToTwoService.getAddPanTorchBlaNoTorchDetails = getAddPanTorchBlaNoTorchDetails;
    jointTypeThreeToTwoService.getVerifyPanTorchBlaNoTorchDetails = getVerifyPanTorchBlaNoTorchDetails;
    jointTypeThreeToTwoService.getInquiryPanTorchBlaNoTorchDetails = getInquiryPanTorchBlaNoTorchDetails;
    jointTypeThreeToTwoService.verifySelect = verifySelect;
    jointTypeThreeToTwoService.getVerifyModuleName = getVerifyModuleName;
    jointTypeThreeToTwoService.getInquiryModuleName = getInquiryModuleName;
    jointTypeThreeToTwoService.verifySubmit = verifySubmit;
    jointTypeThreeToTwoService.deleteSubmit = deleteSubmit;
    jointTypeThreeToTwoService.inquirySelect = inquirySelect;
    jointTypeThreeToTwoService.addDet = addDet;
    jointTypeThreeToTwoService.blaTorch = blaTorch;
    jointTypeThreeToTwoService.modifyPanTorch = modifyPanTorch;
    jointTypeThreeToTwoService.getBrokerTorch = getBrokerTorch;
    jointTypeThreeToTwoService.accountTypeValid = accountTypeValid;
    jointTypeThreeToTwoService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
    jointTypeThreeToTwoService.getAddDashBoard = getAddDashBoard;
    jointTypeThreeToTwoService.validatePan = validatePan;
    jointTypeThreeToTwoService.blaNo = blaNo;
    jointTypeThreeToTwoService.getblaNoInq = getblaNoInq;
    jointTypeThreeToTwoService.getsubscriptionIdMod = getsubscriptionIdMod;
    jointTypeThreeToTwoService.getsubscriptionIdInq = getsubscriptionIdInq;
    jointTypeThreeToTwoService.getFtsDetails = getFtsDetails;
    jointTypeThreeToTwoService.getInquiryRoles = getInquiryRoles;
    jointTypeThreeToTwoService.getNonHdfcTorch = getNonHdfcTorch;
    jointTypeThreeToTwoService.getNonHdfcBranchTorch = getNonHdfcBranchTorch;
    jointTypeThreeToTwoService.getWboBranchDetails = getWboBranchDetails;
    jointTypeThreeToTwoService.unlock = unlock;
    jointTypeThreeToTwoService.getMinorDetails = getMinorDetails;
    jointTypeThreeToTwoService.fetchState = fetchState;
    jointTypeThreeToTwoService.getInquiryModifyModuleName = getInquiryModifyModuleName;
    jointTypeThreeToTwoService.getDetails = getDetails;
    jointTypeThreeToTwoService.getDocumentDetails = getDocumentDetails;
    jointTypeThreeToTwoService.getbankName = getbankName;

    jointTypeThreeToTwoService.getRemarksList = function (requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getRemarksList",
        data: requestData,
      });
    };
    function getbankName(requestData) {
              return $http({
                method: "POST",
                url: "gbmultra/rbiBond/blaCumSub/getbankName",
                data: requestData,
              });
            }
    function getRbiBondModuleNameDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/getAddModuleDetails",
        data: requestData,
      });
    }
    function getVerifyModuleName(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/verifySelect",
        data: requestData,
      });
    }
    function getInquiryModuleName(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/inquiry",
        data: requestData,
      });
    }
    function getDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/getDetails",
        data: requestData,
      });
    }
      function getInquiryModifyModuleName(requestData) {
          return $http({
            method: "POST",
            url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/getModuleDetails",
            data: requestData,
          });
        }
    function getMinorDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/getMinorDetails",
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

    function validatePan(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/validatePan",
        data: requestData,
      });
    }

    function getDocumentDetails(requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getDocumentDetails",
        data: requestData,
      });
    }

    function getFtsDetails(requestData) {
      return $http({
        method: "POST",
        url: "/external-service/fts/process-ci-data                                                                                                                                                                  ",
        data: requestData,
      });
    }

    function getBrokerTorch(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/getBrokerDetails",
        data: requestData,
      });
    }

    function getWboBranchDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/getWboBranchDetails",
        data: requestData,
      });
    }
    function getAddPanTorchBlaNoTorchDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/getAddModuleDetails",
        data: requestData,
      });
    }
  function getVerifyPanTorchBlaNoTorchDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/verifySelect",
        data: requestData,
      });
    }
  function getInquiryPanTorchBlaNoTorchDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/inquiry",
        data: requestData,
      });
    }

    function addDet(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/getAddModuleDetails",
        data: requestData,
      });
    }

    function addSubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/add",
        data: requestData,
      });
    }

    function getInquiryRoles(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/gu/commonFlag/inquiry",
        data: requestData,
      });
    }

    function blaNo(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/blaNo",
        data: requestData,
      });
    }

    function getblaNoInq(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/getblaNoInq",
        data: requestData,
      });
    }
    function getsubscriptionIdMod(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/getsubscriptionIdMod",
        data: requestData,
      });
    }

    function getsubscriptionIdInq(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/getsubscriptionIdInq",
        data: requestData,
      });
    }
    function blaTorch(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/blaTorch",
        data: requestData,
      });
    }

    function modifyPanTorch(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/ModifyPanTorch",
        data: requestData,
      });
    }
    function getAddDashBoard(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/getAddDashBoard",
        data: requestData,
      });
    }
    function fetchCbsCustId(accountNumber) {
      return $http({
        method: "POST",
        url: `external-service/api/account-validation?accountNumber=${accountNumber}`, // Pass accountNumber as a query parameter
        data: {},
      });
    }

    function fetchBlaAccNoDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/getBlaNumDetails",
        data: requestData,
      });
    }
    function fetchGuardianDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/getGuardianDetails",
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
    //Modify Select
    function modifySelect(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/modifySelect",
        data: requestData,
      });
    }
    //Modify submit
    function modifySubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/modify",
        data: requestData,
      });
    }
    //Verify Select
    function verifySelect(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/verifySelect",
        data: requestData,
      });
    }
    //Verify submit
    function verifySubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/verify",
        data: requestData,
      });
    }
    //Delete submit
    function deleteSubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/delete",
        data: requestData,
      });
    }
    //Inquiry submit
    function inquirySelect(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/inquiry",
        data: requestData,
      });
    }
    /* getValidations */
    function accountTypeValid() {
      return $http({
        method: "POST",
        url: "scss/customerMaster/getScssSchemeParameter",
      });
    }

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
    function getNonHdfcBranchTorch(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/getNonHdfcBranchTorch",
        headers: {
          "Content-Type": "application/json",
        },
        data: requestData,
      });
    }

    function unlock(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/unlockBla",
        data: requestData,
      });
    }
    function fetchState(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/jointTypeChangeThreeToTwo/fetchState",
        data: requestData,
      });
    }
  }
})();
