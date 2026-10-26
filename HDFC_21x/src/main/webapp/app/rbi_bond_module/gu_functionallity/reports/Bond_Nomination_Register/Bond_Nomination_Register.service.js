(function () {
  "use strict";

  angular.module("gbmUltraApp").service("BondNominationRegisterService", BondNominationRegisterService);

  BondNominationRegisterService.$inject = ["$http"];

  function BondNominationRegisterService($http) {
    var BondNominationRegisterService = this;

    BondNominationRegisterService.getDetails= function (requestData) {
      return $http({
        method  :   "POST",
        url     :   "gbmultra/rbibond/reports/getBondNominiRegsReport",
        data    :   requestData,
      });
    }

    BondNominationRegisterService.getBondNominationDownload= function (requestData) {
        return $http({
            method  : "POST",
            url     : 'gbmultra/rbibond/reports/getBondNominationDownload',
            data    : requestData
        });
    }
  }
})();
