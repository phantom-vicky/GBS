(function () {
  "use strict";

  angular.module("gbmUltraApp").service("FutureTdsService", FutureTdsService);

  FutureTdsService.$inject = ["$http"];

  function FutureTdsService($http) {
             var FutureTdsService = this;

             FutureTdsService.getFutureTdsDetails = function (requestData) {
               return $http({
                 method: "POST",
                 url: "gbmultra/rbibond/reports/getFutureTdsDetails",
                 data: requestData,
               });
             };
           }
})();
