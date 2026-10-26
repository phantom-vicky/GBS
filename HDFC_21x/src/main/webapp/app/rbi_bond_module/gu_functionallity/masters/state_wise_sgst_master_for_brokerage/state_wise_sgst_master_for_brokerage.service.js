(function () {
  "use strict";

  angular.module("gbmUltraApp").service("StatewiseSgstMasterForBrokerage", StatewiseSgstMasterForBrokerage);

  StatewiseSgstMasterForBrokerage.$inject = ["$http"];

  function StatewiseSgstMasterForBrokerage($http) {
    var StatewiseSgstMasterForBrokerage = this;

    // StatewiseSgstMasterForBrokerage.getStatewiseSgstMasterForBrokerageDetails = getStatewiseSgstMasterForBrokerageDetails;
    StatewiseSgstMasterForBrokerage.addSubmitForm = addSubmitForm;
    StatewiseSgstMasterForBrokerage.modifySubmit = modifySubmit;
    StatewiseSgstMasterForBrokerage.getStateCodeDetails = getStateCodeDetails;
    StatewiseSgstMasterForBrokerage.deleteSubmit = deleteSubmit;
    StatewiseSgstMasterForBrokerage.verifySubmit = verifySubmit;
    //  StatewiseSgstMasterForBrokerage.getModuleName = getModuleName;
    StatewiseSgstMasterForBrokerage.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
    StatewiseSgstMasterForBrokerage.getMetaData = getMetaData;
    StatewiseSgstMasterForBrokerage.getInquiryDetails = getInquiryDetails;
    StatewiseSgstMasterForBrokerage.modifyDetails = modifyDetails;
    StatewiseSgstMasterForBrokerage.verifyDetails = verifyDetails;

    //  function getStatewiseSgstMasterForBrokerageDetails(requestData) {
    //   return $http({
    //    method: 'POST',
    //    url: 'gbmultra/dc/master/StatewiseSgstMasterForBrokerage/addTorch',
    //    data: requestData
    //   })
    //  }

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
        url: "gbmultra/rbiBond/blaCumSub/getModuleDetails",
        data: requestData,
      });
    }

    function getStateCodeDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/StatewiseSgstMasterForBrokerage/getStateDetails",
        data: requestData,
      });
    }

    function addSubmitForm(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/StatewiseSgstMasterForBrokerage/add",
        data: requestData,
      });
    }
    //         function getModuleName(requestData) {
    //        return $http({
    //        method: 'POST',
    //        url: '/gbmultra/rbiBond/StatewiseSgstMasterForBrokerage/add',
    //        data: requestData
    //        })
    //        }

    function getInquiryDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/StatewiseSgstMasterForBrokerage/inquiry",
        data: requestData,
      });
    }

    function verifySubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/StatewiseSgstMasterForBrokerage/verify",
        data: requestData,
      });
    }

    function deleteSubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/StatewiseSgstMasterForBrokerage/delete",
        data: requestData,
      });
    }

    function modifySubmit(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/StatewiseSgstMasterForBrokerage/modify",
        data: requestData,
      });
    }

    function modifyDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/StatewiseSgstMasterForBrokerage/modifyDetails",
        data: requestData,
      });
    }
    function verifyDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/StatewiseSgstMasterForBrokerage/verifyDetails",
        data: requestData,
      });
    }

    StatewiseSgstMasterForBrokerage.getRemarksList = function (requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getRemarksList",
        data: requestData,
      });
    };
  }
})();
