(function () {
  "use strict";

  angular.module("gbmUltraApp").service("rbibondNoticeCancelService", rbibondNoticeCancelService);

  rbibondNoticeCancelService.$inject = ["$http"];

  function rbibondNoticeCancelService($http) {
    var rbibondNoticeCancelService = this;

    rbibondNoticeCancelService.getDetails = function (requestData) {
      return $http({
        method  : "POST",
        url     : "gbmultra/rbibond/reports/getNoticeCancelReport",
        data    : requestData,
      });
    }

  }
})();
