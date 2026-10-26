(function () {
  "use strict";

  angular.module("gbmUltraApp").service("modeOfHoldingService", modeOfHoldingService);

  modeOfHoldingService.$inject = ["$http"];

  function modeOfHoldingService($http) {
    var modeOfHoldingService = this;
    modeOfHoldingService.getReferenceData = getReferenceData;
    modeOfHoldingService.addSubmit = addSubmit;
    modeOfHoldingService.fetchCbsCustId = fetchCbsCustId;
    modeOfHoldingService.fetchBlaAccNoDetailsfetchBlaAccNoDetails = fetchBlaAccNoDetails;
    modeOfHoldingService.fetchGuardianDetails = fetchGuardianDetails;
    modeOfHoldingService.getCommonMetaDatList = getCommonMetaDatList;
    modeOfHoldingService.modifySelect = modifySelect;
    modeOfHoldingService.modifySubmit = modifySubmit;
    modeOfHoldingService.getAddPanTorchBlaNoTorchDetails = getAddPanTorchBlaNoTorchDetails;
    modeOfHoldingService.getVerifyPanTorchBlaNoTorchDetails = getVerifyPanTorchBlaNoTorchDetails;
    modeOfHoldingService.getInquiryPanTorchBlaNoTorchDetails = getInquiryPanTorchBlaNoTorchDetails;
    modeOfHoldingService.verifySelect = verifySelect;
    modeOfHoldingService.getVerifyModuleName = getVerifyModuleName;
    modeOfHoldingService.getInquiryModuleName = getInquiryModuleName;
    modeOfHoldingService.verifySubmit = verifySubmit;
    modeOfHoldingService.deleteSubmit = deleteSubmit;
    modeOfHoldingService.inquirySelect = inquirySelect;
    modeOfHoldingService.addDet = addDet;
    modeOfHoldingService.blaTorch = blaTorch;
    modeOfHoldingService.modifyPanTorch = modifyPanTorch;
    modeOfHoldingService.getBrokerTorch = getBrokerTorch;
    modeOfHoldingService.accountTypeValid = accountTypeValid;
    modeOfHoldingService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
    modeOfHoldingService.getAddDashBoard = getAddDashBoard;
    modeOfHoldingService.validatePan = validatePan;
    modeOfHoldingService.blaNo = blaNo;
    modeOfHoldingService.getblaNoInq = getblaNoInq;
    modeOfHoldingService.getsubscriptionIdMod = getsubscriptionIdMod;
    modeOfHoldingService.getsubscriptionIdInq = getsubscriptionIdInq;
    modeOfHoldingService.getFtsDetails = getFtsDetails;
    modeOfHoldingService.getInquiryRoles = getInquiryRoles;
    modeOfHoldingService.getNonHdfcTorch = getNonHdfcTorch;
    modeOfHoldingService.getNonHdfcBranchTorch = getNonHdfcBranchTorch;
    modeOfHoldingService.getWboBranchDetails = getWboBranchDetails;
    modeOfHoldingService.unlock = unlock;
    modeOfHoldingService.getMinorDetails = getMinorDetails;
    modeOfHoldingService.fetchState = fetchState;
    modeOfHoldingService.getInquiryModifyModuleName = getInquiryModifyModuleName;
    modeOfHoldingService.getDetails = getDetails;
    modeOfHoldingService.getDocumentDetails = getDocumentDetails;
    modeOfHoldingService.getbankName = getbankName;

    modeOfHoldingService.getRemarksList = function (requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getRemarksList",
        data: requestData,
      });
    };

    function getRbiBondModuleNameDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/modeOfHolding/getAddModuleDetails",
        data: requestData,
      });
    }
    function getVerifyModuleName(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/modeOfHolding/verifySelect",
        data: requestData,
      });
    }
    function getInquiryModuleName(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/modeOfHolding/inquiry",
        data: requestData,
      });
    }
    function getDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/modeOfHolding/getDetails",
        data: requestData,
      });
    }
     function getbankName(requestData) {
              return $http({
                method: "POST",
                url: "gbmultra/rbiBond/blaCumSub/getbankName",
                data: requestData,
              });
            }
      function getInquiryModifyModuleName(requestData) {
          return $http({
            method: "POST",
            url: "gbmultra/rbiBond/modeOfHolding/getModuleDetails",
            data: requestData,
          });
        }
    function getMinorDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/modeOfHolding/getMinorDetails",
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
        url: "gbmultra/rbiBond/modeOfHolding/validatePan",
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
        url: "gbmultra/rbiBond/modeofholding/getBrokerDetails",
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
    function getWboBranchDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/modeOfHolding/getWboBranchDetails",
        data: requestData,
      });
    }
    function getAddPanTorchBlaNoTorchDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/modeOfHolding/getAddModuleDetails",
        data: requestData,
      });
    }
  function getVerifyPanTorchBlaNoTorchDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/modeOfHolding/verifySelect",
        data: requestData,
      });
    }
  function getInquiryPanTorchBlaNoTorchDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/modeOfHolding/inquiry",
        data: requestData,
      });
    }

    function addDet(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/modeOfHolding/getAddModuleDetails",
        data: requestData,
      });
    }

    function addSubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/modeOfHolding/add",
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
        url: "gbmultra/rbiBond/modeOfHolding/blaNo",
        data: requestData,
      });
    }

    function getblaNoInq(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/modeOfHolding/getblaNoInq",
        data: requestData,
      });
    }
    function getsubscriptionIdMod(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/modeOfHolding/getsubscriptionIdMod",
        data: requestData,
      });
    }

    function getsubscriptionIdInq(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/modeOfHolding/getsubscriptionIdInq",
        data: requestData,
      });
    }
    function blaTorch(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/modeOfHolding/blaTorch",
        data: requestData,
      });
    }

    function modifyPanTorch(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/modeOfHolding/ModifyPanTorch",
        data: requestData,
      });
    }
    function getAddDashBoard(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/modeOfHolding/getAddDashBoard",
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
        url: "gbmultra/rbiBond/modeOfHolding/getBlaNumDetails",
        data: requestData,
      });
    }
    function fetchGuardianDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/modeOfHolding/getGuardianDetails",
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
        url: "gbmultra/rbiBond/modeOfHolding/modifySelect",
        data: requestData,
      });
    }
    //Modify submit
    function modifySubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/modeOfHolding/modify",
        data: requestData,
      });
    }
    //Verify Select
    function verifySelect(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/modeOfHolding/verifySelect",
        data: requestData,
      });
    }
    //Verify submit
    function verifySubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/modeOfHolding/verify",
        data: requestData,
      });
    }
    //Delete submit
    function deleteSubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/modeOfHolding/delete",
        data: requestData,
      });
    }
    //Inquiry submit
    function inquirySelect(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/modeOfHolding/inquiry",
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
        url: "gbmultra/rbiBond/modeOfHolding/getNonHdfcBranchTorch",
        headers: {
          "Content-Type": "application/json",
        },
        data: requestData,
      });
    }

    function unlock(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/modeOfHolding/unlockBla",
        data: requestData,
      });
    }
    function fetchState(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/modeOfHolding/fetchState",
        data: requestData,
      });
    }
  }
})();
