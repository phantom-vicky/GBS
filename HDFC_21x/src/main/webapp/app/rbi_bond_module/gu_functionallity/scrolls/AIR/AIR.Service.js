(function () {
  "use strict";

  angular.module("gbmUltraApp").service("AIRService", AIRService);

  AIRService.$inject = ["$http"];

  function AIRService($http) {
    var AIRService = this;

    AIRService.getDetails = function (requestData) {
      return $http({
        method  : "POST",
        url     : "gbmultra/rbibond/scrolls/getAIRDetails",
        data    : requestData,
      });
    };

    AIRService.getReferenceData = function (requestData) {
      return $http({
        method  : "POST",
        url     : "rbiBond/getReferenceData",
        data    : requestData,
      });
    };
  }
})();
