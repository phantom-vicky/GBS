(function () {
  "use strict";

  angular.module("gbmUltraApp").service("bondLedgerAccSubscriptionService", bondLedgerAccSubscriptionService);

  bondLedgerAccSubscriptionService.$inject = ["$http"];

  function bondLedgerAccSubscriptionService($http) {
    var bondLedgerAccSubscriptionService = this;
    bondLedgerAccSubscriptionService.getReferenceData = getReferenceData;
    bondLedgerAccSubscriptionService.addSubmit = addSubmit;
    //		bondLedgerAccSubscriptionService.getTdsApplecableRate = getTdsApplecableRate;
    bondLedgerAccSubscriptionService.fetchCbsCustId = fetchCbsCustId;
    bondLedgerAccSubscriptionService.fetchBlaAccNoDetailsfetchBlaAccNoDetails = fetchBlaAccNoDetails;
    bondLedgerAccSubscriptionService.fetchGuardianDetails = fetchGuardianDetails;
    bondLedgerAccSubscriptionService.getCommonMetaDatList = getCommonMetaDatList;
    bondLedgerAccSubscriptionService.modifySelect = modifySelect;
    bondLedgerAccSubscriptionService.modifySubmit = modifySubmit;
    bondLedgerAccSubscriptionService.verifySelect = verifySelect;
    bondLedgerAccSubscriptionService.verifySubmit = verifySubmit;
    bondLedgerAccSubscriptionService.deleteSubmit = deleteSubmit;
    bondLedgerAccSubscriptionService.inquirySelect = inquirySelect;
    bondLedgerAccSubscriptionService.getbankName = getbankName;
    bondLedgerAccSubscriptionService.getbranchName = getbranchName;
    bondLedgerAccSubscriptionService.addDet = addDet;
    bondLedgerAccSubscriptionService.blaTorch = blaTorch;
    bondLedgerAccSubscriptionService.modifyPanTorch = modifyPanTorch;
    bondLedgerAccSubscriptionService.getBrokerTorch = getBrokerTorch;
    bondLedgerAccSubscriptionService.accountTypeValid = accountTypeValid;
    bondLedgerAccSubscriptionService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
    bondLedgerAccSubscriptionService.getAddDashBoard = getAddDashBoard;
    bondLedgerAccSubscriptionService.validatePan = validatePan;
    bondLedgerAccSubscriptionService.blaNo = blaNo;
    bondLedgerAccSubscriptionService.getblaNoInq = getblaNoInq;
    bondLedgerAccSubscriptionService.getsubscriptionIdMod = getsubscriptionIdMod;
    bondLedgerAccSubscriptionService.getsubscriptionIdInq = getsubscriptionIdInq;
    bondLedgerAccSubscriptionService.getFtsDetails = getFtsDetails;
    bondLedgerAccSubscriptionService.getNonHdfcTorch = getNonHdfcTorch;
    bondLedgerAccSubscriptionService.getNonHdfcBranchTorch = getNonHdfcBranchTorch;
    bondLedgerAccSubscriptionService.getWboBranchDetails = getWboBranchDetails;
    bondLedgerAccSubscriptionService.unlock = unlock;
    bondLedgerAccSubscriptionService.getMinorDetails = getMinorDetails;
    bondLedgerAccSubscriptionService.fetchState = fetchState;
    bondLedgerAccSubscriptionService.getInquiryModifyModuleName = getInquiryModifyModuleName;
    bondLedgerAccSubscriptionService.getDocumentDetails = getDocumentDetails;
    bondLedgerAccSubscriptionService.recordsLockORUnLock = recordsLockORUnLock;
    bondLedgerAccSubscriptionService.unlockSpringBla = unlockSpringBla;

    bondLedgerAccSubscriptionService.getFtsFileService = function (ftsDetailFetch) {
      return $http({
        method: "POST",
        url: "external-service/fts-hcp-integrated-service?applicationNumber="+ftsDetailFetch
        });
    };
     bondLedgerAccSubscriptionService.getRemarksList = function (requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getRemarksList",
        data: requestData,
      });
    };

    function getRbiBondModuleNameDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/blaCumSub/getModuleDetails",
        data: requestData,
      });
    }
    function recordsLockORUnLock(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/blaCumSub/lock",
        data: requestData,
        });
    }
    function unlockSpringBla(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/blaCumSub/unlock",
        data: requestData,
        });
    }
      function getInquiryModifyModuleName(requestData) {
          return $http({
            method: "POST",
            url: "gbmultra/rbiBond/blaCumSub/getModuleDetails",
            data: requestData,
          });
        }
    function getMinorDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/blaCumSub/getMinorDetails",
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
        url: "gbmultra/rbiBond/blaCumSub/validatePan                                                                                                                                                                  ",
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
        url: "gbmultra/rbiBond/blaCumSub/getBrokerDetails",
        data: requestData,
      });
    }

    function getWboBranchDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/blaCumSub/getWboBranchDetails",
        data: requestData,
      });
    }

    function addDet(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/blaCumSub/addVal",
        data: requestData,
      });
    }

    function addSubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/blaCumSub/add",
        data: requestData,
      });
    }

    function blaNo(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/blaCumSub/blaNo",
        data: requestData,
      });
    }

    function getblaNoInq(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/blaCumSub/getblaNoInq",
        data: requestData,
      });
    }
    function getsubscriptionIdMod(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/blaCumSub/getsubscriptionIdMod",
        data: requestData,
      });
    }

    function getsubscriptionIdInq(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/blaCumSub/getsubscriptionIdInq",
        data: requestData,
      });
    }
    function blaTorch(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/blaCumSub/blaTorch",
        data: requestData,
      });
    }

    function modifyPanTorch(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/blaCumSub/ModifyPanTorch",
        data: requestData,
      });
    }
    function getAddDashBoard(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/blaCumSub/getAddDashBoard",
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
        url: "gbmultra/rbiBond/blaCumSub/getBlaNumDetails",
        data: requestData,
      });
    }
    function fetchGuardianDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/blaCumSub/getGuardianDetails",
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
        url: "gbmultra/rbiBond/blaCumSub/modifySelect",
        data: requestData,
      });
    }
    //Modify submit
    function modifySubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/blaCumSub/modify",
        data: requestData,
      });
    }
    //Verify Select
    function verifySelect(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/blaCumSub/verifySelect",
        data: requestData,
      });
    }
    //Verify submit
    function verifySubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/blaCumSub/verify",
        data: requestData,
      });
    }
    //Delete submit
    function deleteSubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/blaCumSub/delete",
        data: requestData,
      });
    }
    //Inquiry submit
    function inquirySelect(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/blaCumSub/inquiry",
        data: requestData,
      });
    }
    //getbankName submit
        function getbankName(requestData) {
          return $http({
            method: "POST",
            url: "gbmultra/rbiBond/blaCumSub/getbankName",
            data: requestData,
          });
        }
        //getbranchName submit
            function getbranchName(requestData) {
              return $http({
                method: "POST",
                url: "gbmultra/rbiBond/blaCumSub/getbranchName",
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
        url: "gbmultra/rbiBond/blaCumSub/getNonHdfcBranchTorch",
        headers: {
          "Content-Type": "application/json",
        },
        data: requestData,
      });
    }

    function unlock(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/blaCumSub/unlockBla",
        data: requestData,
      });
    }
    function fetchState(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/blaCumSub/fetchState",
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
