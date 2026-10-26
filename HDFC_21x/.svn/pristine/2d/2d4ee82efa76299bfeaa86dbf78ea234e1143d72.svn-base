(function () {
  "use strict";

  angular.module("gbmUltraApp").service("bondPreClosureRepaymentService", bondPreClosureRepaymentService);

  bondPreClosureRepaymentService.$inject = ["$http"];

  function bondPreClosureRepaymentService($http) {
    var bondPreClosureRepaymentService = this;
    bondPreClosureRepaymentService.getDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getPreClosureRepaymentReport",
        data: requestData,
      });
    };

    bondPreClosureRepaymentService.getDetailsDownload = function (requestData) {
          return $http({
            method: "POST",
            url: "gbmultra/rbibond/reports/getPreClosureRepaymentDownload",
            data: requestData,
          });
        };

  }
})();
