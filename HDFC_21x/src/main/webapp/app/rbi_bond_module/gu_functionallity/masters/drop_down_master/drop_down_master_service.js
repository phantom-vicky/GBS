(function () {
  "use strict";

  angular.module("gbmUltraApp").service("dropDownMasterService", dropDownMasterService);

  dropDownMasterService.$inject = ["$http"];
  function dropDownMasterService($http) {
    var dropDownMasterService = this;
    //            dropDownMasterService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
    dropDownMasterService.dropDownList = dropDownList;
    dropDownMasterService.addDetails = addDetails;
    dropDownMasterService.reject = reject;
    dropDownMasterService.modifyDetails = modifyDetails;
    dropDownMasterService.modifySubmit = modifySubmit;
    dropDownMasterService.verifyDetails = verifyDetails;
    dropDownMasterService.verifySubmit = verifySubmit;
    dropDownMasterService.inquiry = inquiry;
    -function getRbiBondModuleNameDetails(requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getModuleDetails",
        data: requestData,
      });
    };

    function dropDownList(requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getModuleDetails",
        data: requestData,
      });
    }

    function addDetails(requestData) {
      return $http({
        method: "POST",
        url: "apis/drop-down-master/add-details",
        data: requestData,
      });
    }

    function reject(requestData) {
      return $http({
        method: "POST",
        url: "apis/drop-down-master/reject",
        data: requestData,
      });
    }
    function modifyDetails(requestData) {
      return $http({
        method: "POST",
        url: "apis/drop-down-master/modify-details",
        data: requestData,
      });
    }
    function modifySubmit(requestData) {
      return $http({
        method: "POST",
        url: "apis/drop-down-master/modify-submit",
        data: requestData,
      });
    }
    function verifyDetails(requestData) {
      return $http({
        method: "POST",
        url: "apis/drop-down-master/verify-details",
        data: requestData,
      });
    }

    function verifySubmit(requestData) {
      return $http({
        method: "POST",
        url: "apis/drop-down-master/verify-submit",
        data: requestData,
      });
    }

    function inquiry(requestData) {
      return $http({
        method: "POST",
        url: "apis/drop-down-master/inquiry",
        data: requestData,
      });
    }
    dropDownMasterService.getRemarksList = function (requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getRemarksList",
        data: requestData,
      });
    };
  }
})();