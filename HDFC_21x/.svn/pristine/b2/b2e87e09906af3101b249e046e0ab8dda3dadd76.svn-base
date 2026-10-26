(function () {
  "use strict";

  angular.module("gbmUltraApp").service("hufTransferToSingleAndJointService", hufTransferToSingleAndJointService);

  hufTransferToSingleAndJointService.$inject = ["$http"];

  function hufTransferToSingleAndJointService($http) {
    var hufTransferToSingleAndJointService = this;
    hufTransferToSingleAndJointService.getReferenceData = getReferenceData;
    hufTransferToSingleAndJointService.addSubmit = addSubmit;
    hufTransferToSingleAndJointService.fetchCbsCustId = fetchCbsCustId;
    hufTransferToSingleAndJointService.fetchBlaAccNoDetailsfetchBlaAccNoDetails = fetchBlaAccNoDetails;
    hufTransferToSingleAndJointService.fetchGuardianDetails = fetchGuardianDetails;
    hufTransferToSingleAndJointService.getCommonMetaDatList = getCommonMetaDatList;
    hufTransferToSingleAndJointService.verifySelect = verifySelect;
    hufTransferToSingleAndJointService.verifySubmit = verifySubmit;
    hufTransferToSingleAndJointService.deleteSubmit = deleteSubmit;
    hufTransferToSingleAndJointService.inquirySelect = inquirySelect;
    hufTransferToSingleAndJointService.getbankName = getbankName;
    hufTransferToSingleAndJointService.getbranchName = getbranchName;
    hufTransferToSingleAndJointService.addDet = addDet;
    hufTransferToSingleAndJointService.blaTorch = blaTorch;
    hufTransferToSingleAndJointService.getBrokerTorch = getBrokerTorch;
    hufTransferToSingleAndJointService.accountTypeValid = accountTypeValid;
    hufTransferToSingleAndJointService.getModuleNameAdd = getModuleNameAdd;
    hufTransferToSingleAndJointService.getAddDashBoard = getAddDashBoard;
    hufTransferToSingleAndJointService.validatePan = validatePan;
    hufTransferToSingleAndJointService.blaNo = blaNo;
    hufTransferToSingleAndJointService.getblaNoInq = getblaNoInq;
    hufTransferToSingleAndJointService.getsubscriptionIdInq = getsubscriptionIdInq;
    hufTransferToSingleAndJointService.getNonHdfcTorch = getNonHdfcTorch;
    hufTransferToSingleAndJointService.getNonHdfcBranchTorch = getNonHdfcBranchTorch;
    hufTransferToSingleAndJointService.getWboBranchDetails = getWboBranchDetails;
    hufTransferToSingleAndJointService.unlock = unlock;
    hufTransferToSingleAndJointService.getMinorDetails = getMinorDetails;
    hufTransferToSingleAndJointService.fetchState = fetchState;
    hufTransferToSingleAndJointService.getModuleNameInquiry = getModuleNameInquiry;
    hufTransferToSingleAndJointService.getDocumentDetails = getDocumentDetails;
    hufTransferToSingleAndJointService.getDetails = getDetails;

    hufTransferToSingleAndJointService.getFtsFileService = function (ftsDetailFetch) {
      return $http({
        method: "POST",
        url: "external-service/fts-hcp-integrated-service?applicationNumber="+ftsDetailFetch
        });
    };
     hufTransferToSingleAndJointService.getRemarksList = function (requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getRemarksList",
        data: requestData,
      });
    };

    function getModuleNameAdd(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/hufTransfer/getModuleNameAdd",
        data: requestData,
      });
    }
    function getDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/hufTransfer/getDetails",
        data: requestData,
      });
    }
      function getModuleNameInquiry(requestData) {
          return $http({
            method: "POST",
            url: "gbmultra/rbiBond/hufTransfer/inquiry",
            data: requestData,
          });
        }
    function getMinorDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/hufTransfer/getMinorDetails",
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
   function getDocumentDetails(requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getDocumentDetails",
        data: requestData,
      });
    }

    function validatePan(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/hufTransfer/validatePan                                                                                                                                                                  ",
        data: requestData,
      });
    }

    function getBrokerTorch(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/hufTransfer/getBrokerDetails",
        data: requestData,
      });
    }

    function getWboBranchDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/hufTransfer/getWboBranchDetails",
        data: requestData,
      });
    }

    function addDet(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/hufTransfer/addVal",
        data: requestData,
      });
    }

    function addSubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/hufTransfer/add",
        data: requestData,
      });
    }

    function blaNo(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/hufTransfer/blaNo",
        data: requestData,
      });
    }

    function getblaNoInq(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/hufTransfer/getblaNoInq",
        data: requestData,
      });
    }

    function getsubscriptionIdInq(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/hufTransfer/getsubscriptionIdInq",
        data: requestData,
      });
    }
    function blaTorch(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/hufTransfer/blaTorch",
        data: requestData,
      });
    }

    function getAddDashBoard(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/hufTransfer/getAddDashBoard",
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
        url: "gbmultra/rbiBond/hufTransfer/getBlaNumDetails",
        data: requestData,
      });
    }
    function fetchGuardianDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/hufTransfer/getGuardianDetails",
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

    //Verify Select
    function verifySelect(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/hufTransfer/verifySelect",
        data: requestData,
      });
    }
    //Verify submit
    function verifySubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/hufTransfer/verify",
        data: requestData,
      });
    }
    //Delete submit
    function deleteSubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/hufTransfer/delete",
        data: requestData,
      });
    }
    //Inquiry submit
    function inquirySelect(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/hufTransfer/inquiry",
        data: requestData,
      });
    }
    //getbankName submit
        function getbankName(requestData) {
          return $http({
            method: "POST",
            url: "gbmultra/rbiBond/hufTransfer/getbankName",
            data: requestData,
          });
        }
        //getbranchName submit
            function getbranchName(requestData) {
              return $http({
                method: "POST",
                url: "gbmultra/rbiBond/hufTransfer/getbranchName",
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


    function getNonHdfcBranchTorch(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/hufTransfer/getNonHdfcBranchTorch",
        headers: {
          "Content-Type": "application/json",
        },
        data: requestData,
      });
    }

    function unlock(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/hufTransfer/unlockBla",
        data: requestData,
      });
    }
    function fetchState(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/hufTransfer/fetchState",
        data: requestData,
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
  }
})();
