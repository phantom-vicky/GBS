(function () {
  "use strict";

  angular.module("gbmUltraApp").service("InterestNotCreditedService", InterestNotCreditedService);

  InterestNotCreditedService.$inject = ["$http"];

  function InterestNotCreditedService($http) {
    var InterestNotCreditedService = this;

    InterestNotCreditedService.getDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getBondInterestNotCredited",
        data: requestData,
      });
    };

    InterestNotCreditedService.getDetailsDownload = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getBondInterestNotCreditedDownload",
        data: requestData,
      });
    };

    InterestNotCreditedService.getReferenceData = function (requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getReferenceData",
        data: requestData,
      });
    };

      		 InterestNotCreditedService.getTorchDetails = function(requestData){
                      return $http({
                          method: "post",
                          url: "rbiBond/interest/trial/run/addTorch",
                          data : requestData
                      });
                  }
  }
})();
