(function () {
  "use strict";

  angular.module("gbmUltraApp").service("brokerageInvoiceAndEmailBodyLetterService", brokerageInvoiceAndEmailBodyLetterService);
  brokerageInvoiceAndEmailBodyLetterService.$inject = ["$http"];

  function brokerageInvoiceAndEmailBodyLetterService($http) {
    var brokerageInvoiceAndEmailBodyLetterService = this;

    brokerageInvoiceAndEmailBodyLetterService.getDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getBrokerageInvoiceEmailBodyLetter",
        data: requestData,
      });
    };
  }
})();
