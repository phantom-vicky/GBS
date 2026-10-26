(function () {
  "use strict";

  angular.module("gbmUltraApp").service("rbibondNomineeContinuationService", rbibondNomineeContinuationService);

  rbibondNomineeContinuationService.$inject = ["$http"];

  function rbibondNomineeContinuationService($http) {
    var rbibondNomineeContinuationService = this;
    rbibondNomineeContinuationService.getReferenceData = getReferenceData;
    rbibondNomineeContinuationService.addSubmit = addSubmit;
    rbibondNomineeContinuationService.fetchState = fetchState;
    rbibondNomineeContinuationService.fetchCbsCustId = fetchCbsCustId;
    rbibondNomineeContinuationService.fetchBlaAccNoDetailsfetchBlaAccNoDetails = fetchBlaAccNoDetails;
    rbibondNomineeContinuationService.fetchGuardianDetails = fetchGuardianDetails;
    rbibondNomineeContinuationService.getCommonMetaDatList = getCommonMetaDatList;
    rbibondNomineeContinuationService.modifySelect = modifySelect;
    rbibondNomineeContinuationService.modifySubmit = modifySubmit;
    rbibondNomineeContinuationService.verifySelect = verifySelect;
    rbibondNomineeContinuationService.verifySubmit = verifySubmit;
    rbibondNomineeContinuationService.deleteSubmit = deleteSubmit;
        rbibondNomineeContinuationService.getPanValidation = getPanValidation;

    rbibondNomineeContinuationService.getInquiryDetails = getInquiryDetails;
    rbibondNomineeContinuationService.addDet = addDet;
    rbibondNomineeContinuationService.blaTorch = blaTorch;
    rbibondNomineeContinuationService.modifyPanTorch = modifyPanTorch;
    rbibondNomineeContinuationService.getAddDetails = getAddDetails;
    rbibondNomineeContinuationService.getBrokerTorch = getBrokerTorch;
    rbibondNomineeContinuationService.accountTypeValid = accountTypeValid;
    rbibondNomineeContinuationService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
    rbibondNomineeContinuationService.getAddDashBoard = getAddDashBoard;
    rbibondNomineeContinuationService.validatePan = validatePan;
    rbibondNomineeContinuationService.blaNo = blaNo;
    rbibondNomineeContinuationService.getblaNoInq = getblaNoInq;
    rbibondNomineeContinuationService.getsubscriptionIdMod = getsubscriptionIdMod;
    rbibondNomineeContinuationService.getsubscriptionIdInq = getsubscriptionIdInq;
    rbibondNomineeContinuationService.getFtsDetails = getFtsDetails;
    rbibondNomineeContinuationService.getNonHdfcTorch = getNonHdfcTorch;
    rbibondNomineeContinuationService.getNonHdfcBranchTorch = getNonHdfcBranchTorch;
    rbibondNomineeContinuationService.getWboBranchDetails = getWboBranchDetails;
    rbibondNomineeContinuationService.unlock = unlock;
    rbibondNomineeContinuationService.getMinorDetails = getMinorDetails;
    rbibondNomineeContinuationService.fetchState = fetchState;
    rbibondNomineeContinuationService.getInquiryModifyModuleName = getInquiryModifyModuleName;
       rbibondNomineeContinuationService.getbankName=getbankName;
    rbibondNomineeContinuationService.getRemarksList = function (requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getRemarksList",
        data: requestData,
      });
    };

  rbibondNomineeContinuationService.getFtsFileService = function (ftsDetailFetch) {
      return $http({
        method: "POST",
        url: "external-service/fts-hcp-integrated-service?applicationNumber="+ftsDetailFetch
        });
    };

  rbibondNomineeContinuationService.getFtsFileService = function (ftsDetailFetch) {
      return $http({
        method: "POST",
        url: "external-service/fts-hcp-integrated-service?applicationNumber="+ftsDetailFetch
        });
    };
    function getRbiBondModuleNameDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/nomineeContinuation/getModuleDetails",
        data: requestData,
      });
    }
      function getInquiryModifyModuleName(requestData) {
          return $http({
            method: "POST",
            url: "gbmultra/rbiBond/nomineeContinuation/getModuleDetails",
            data: requestData,
          });
        }
    function getMinorDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/nomineeContinuation/getMinorDetails",
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
        url: "external-service/validatePan/validate-pan                                                                                                                                                                  ",
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
        url: "gbmultra/rbiBond/nomineeContinuation/getBrokerDetails",
        data: requestData,
      });
    }

    function getWboBranchDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/nomineeContinuation/getWboBranchDetails",
        data: requestData,
      });
    }

    function addDet(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/nomineeContinuation/getAddValidation",
        data: requestData,
      });
    }

  function addSubmit(requestData) {
      // Log requestData to check its structure
      console.log("Request Data being sent: ", requestData);

      return $http({
          method: "POST",
          url: "gbmultra/rbiBond/nomineeContinuation/add",
          data: requestData,
      })
      .then(function(response) {
          // Handle successful response
          console.log("Successfully received response: ", response);
          return response.data;  // Return response data if needed
      })
      .catch(function(error) {
          // Log any errors for debugging
          console.error("Error during API call: ", error);
          // Optionally, you can show a message to the user here
          throw error;  // Throw the error so it can be handled elsewhere
      });
  }

    function blaNo(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/nomineeContinuation/blaNo",
        data: requestData,
      });
    }

    function getblaNoInq(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/nomineeContinuation/getblaNoInq",
        data: requestData,
      });
    }
    function getsubscriptionIdMod(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/nomineeContinuation/getsubscriptionIdMod",
        data: requestData,
      });
    }

    function getsubscriptionIdInq(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/nomineeContinuation/getsubscriptionIdInq",
        data: requestData,
      });
    }
    function blaTorch(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/nomineeContinuation/blaTorch",
        data: requestData,
      });
    }

    function modifyPanTorch(requestData) {
          return $http({
            method: "POST",
            url: "gbmultra/rbiBond/nomineeContinuation/ModifyPanTorch",
            data: requestData,
          });
        }

    function getAddDetails(requestData) {
           return $http({
             method: "POST",
             url: "gbmultra/rbiBond/nomineeContinuation/getAddDetails",
             data: requestData,
           });
         }
    function getAddDashBoard(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/nomineeContinuation/getAddDashBoard",
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
        url: "gbmultra/rbiBond/nomineeContinuation/getBlaNumDetails",
        data: requestData,
      });
    }
    function fetchGuardianDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/bondTransferIn/getGuardianDetails",
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
        url: "gbmultra/rbiBond/nomineeContinuation/modifySelect",
        data: requestData,
      });
    }
    //Modify submit
    function modifySubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/nomineeContinuation/modify",
        data: requestData,
      });
    }
    //Verify Select
    function verifySelect(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/nomineeContinuation/getVerDelDetails",
        data: requestData,
      });
    }
    //Verify submit
    function verifySubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/nomineeContinuation/verify",
        data: requestData,
      });
    }
         function getPanValidation(requestData){
              return $http({
                  method: 'POST',
                  url: 'gbmultra/rbiBond/nomineeContinuation/getPanValidation',
                  data :requestData
              })
        }
    //Delete submit
    function deleteSubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/nomineeContinuation/delete",
        data: requestData,
      });
    }
    //Inquiry submit
    function getInquiryDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/nomineeContinuation/getInqDetails",
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
     //getbankName submit
            function getbankName(requestData) {
              return $http({
                method: "POST",
                url: "gbmultra/rbiBond/blaCumSub/getbankName",
                data: requestData,
              });
            }
    function getNonHdfcBranchTorch(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/nomineeContinuation/getNonHdfcBranchTorch",
        headers: {
          "Content-Type": "application/json",
        },
        data: requestData,
      });
    }

    function unlock(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/nomineeContinuation/unlockBla",
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


  }
})();
