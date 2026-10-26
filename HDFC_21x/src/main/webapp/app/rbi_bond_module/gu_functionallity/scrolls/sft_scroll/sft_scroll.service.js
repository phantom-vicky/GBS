(function () {
  "use strict";

  angular.module("gbmUltraApp").service("sftService", sftService);

  sftService.$inject = ["$http"];

  function sftService($http) {
    var sftService = this;

    sftService.getDetails = function (requestData) {
      return $http({
        method  : "POST",
        url     : "gbmultra/rbibond/scrolls/getSft",
        data    : requestData,
      });
    };

//    sftService.getRbiBondModuleNameDetails = function(requestData) {
//      return $http({
//        method: "POST",
//        url: "rbiBond/getModuleDetails",
//        data: requestData,
//      });
//    }
//    InterestScrollReportService.getReferenceData = function (requestData) {
//      return $http({
//        method  : "POST",
//        url     : "rbiBond/getReferenceData",
//        data    : requestData,
//      });
//    };
  }
})();

