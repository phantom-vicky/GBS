(function () {
  "use strict";

  angular.module("gbmUltraApp").service("cbsBranchesOfTheBankService", cbsBranchesOfTheBankService);

  cbsBranchesOfTheBankService.$inject = ["$http"];

  function cbsBranchesOfTheBankService($http) {
    var cbsBranchesOfTheBankService = this;

    cbsBranchesOfTheBankService.addbranchesOfTheBank = addbranchesOfTheBank;
    cbsBranchesOfTheBankService.modify = modify;
    cbsBranchesOfTheBankService.verify = verify;
    cbsBranchesOfTheBankService.deletebranchesOfTheBank = deletebranchesOfTheBank;
    cbsBranchesOfTheBankService.getrecord = getrecord;
    cbsBranchesOfTheBankService.getverifiedrecords = getverifiedrecords;
    cbsBranchesOfTheBankService.getunverifiedrecords = getunverifiedrecords;
    cbsBranchesOfTheBankService.getlist = getlist;
    cbsBranchesOfTheBankService.getStatus = getStatus;
    cbsBranchesOfTheBankService.getAllBranchId = getAllBranchId;
    cbsBranchesOfTheBankService.getRemarksList = getRemarksList;
    cbsBranchesOfTheBankService.getReferenceData = getReferenceData;

    function addbranchesOfTheBank(data) {
      var parameter = data;

      return $http({
        method: "post",
        url: "gbmultra/cbs/integration/master/bankbranch/save",
        params: {},
        data: parameter,
      });
    }

    cbsBranchesOfTheBankService.getReferenceData = function (requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getReferenceData",
        data: requestData,
      });
    };

    function getAllBranchId(requestData) {
      return $http({
        method: "post",
        url: "gbmultra/cbs/integration/master/bankbranch/getAllBranchId",
        data: requestData,
      });
    }

    function modify(data) {
      var parameter = data;
      return $http({
        method: "post",
        url: "gbmultra/cbs/integration/master/bankbranchupdate",
        params: {},
        data: parameter,
        headers: {
          "Content-type": "application/json;charset=utf-8",
        },
      });
    }

    function verify(data) {
      var parameter = JSON.stringify(data);
      return $http({
        method: "post",
        url: "gbmultra/cbs/integration/master/bankbranch/verify",
        data: parameter,
        headers: {
          "Content-type": "application/json;charset=utf-8",
        },
      });
    }

    function deletebranchesOfTheBank(data) {
      var parameter = JSON.stringify(data);
      return $http({
        method: "post",
        url: "gbmultra/cbs/integration/master/bankbranch/delete",
        data: parameter,
        headers: {
          "Content-type": "application/json;charset=utf-8",
        },
      });
    }
    function getrecord(id) {
      return $http({
        method: "post",
        url: "gbmultra/cbs/integration/master/bankbranch",
        data: { id: id },
      });
    }

    function getverifiedrecords() {
      return $http({
        method: "post",
        url: "gbmultra/cbs/integration/master/bankbranch/getVerifyRecords",
        data: {},
      });
    }

    function getunverifiedrecords() {
      return $http({
        method: "post",
        url: "gbmultra/cbs/integration/master/bankbranch/unverifiedrecords",
        data: {},
      });
    }

    function getlist() {
      return $http({
        method: "post",
        url: "gbmultra/cbs/integration/master/bankbranch/getallrecordsinquiry",
        data: {},
      });
    }

    function getStatus() {
      return $http({
        method: "post",
        url: "gbmultra/cbs/integration/master/bankbranch/status",
        data: {},
      });
    }
    function getRemarksList(requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getRemarksList",
        data: requestData,
      });
    }

    cbsBranchesOfTheBankService.inquiry = function (requestData) {
      return $http({
        method: "post",
        url: "gbmultra/cbs/integration/master/bankbranch/inquiry",
        data: requestData,
      });
    };
    cbsBranchesOfTheBankService.verDelDetails = function (requestData) {
      return $http({
        method: "post",
        url: "gbmultra/cbs/integration/master/bankbranch/verDelDetails",
        data: requestData,
      });
    };

    cbsBranchesOfTheBankService.verifySubmit = function (requestData) {
      return $http({
        method: "post",
        url: "gbmultra/cbs/integration/master/bankbranch/verifySubmit",
        data: requestData,
      });
    };

    cbsBranchesOfTheBankService.deleteSubmit = function (requestData) {
      return $http({
        method: "post",
        url: "gbmultra/cbs/integration/master/bankbranch/deleteSubmit",
        data: requestData,
      });
    };

    cbsBranchesOfTheBankService.modifyDetails = function (requestData) {
      return $http({
        method: "post",
        url: "gbmultra/cbs/integration/master/bankbranch/modifyDetails",
        data: requestData,
      });
    };

    cbsBranchesOfTheBankService.modify = function (requestData) {
      return $http({
        method: "post",
        url: "gbmultra/cbs/integration/master/bankbranch/modify",
        data: requestData,
      });
    };

    function getReferenceData(requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getReferenceData",
        data: requestData,
      });
    }
  }
})();
