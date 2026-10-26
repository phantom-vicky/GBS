(function () {
  "use strict";

  angular.module("gbmUltraApp").service("RedemptionTextFileService", RedemptionTextFileService);

  RedemptionTextFileService.$inject = ["$http"];

  function RedemptionTextFileService($http) {
    var RedemptionTextFileService = this;

    // Main API call for redemption details
    // flag: "S" - Scheme List, "D" - Details, "274" - Scroll 274, "276" - Interest Scroll 276, "CL" - Covering Letter
    RedemptionTextFileService.getRedemptionDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/scrolls/getRedemptionTextFileDetails",
        data: requestData,
      });
    };

    // Download Redemption Scroll 274
    RedemptionTextFileService.downloadScroll274 = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/scrolls/getRedemptionScroll274",
        data: requestData,
      });
    };

  // Download Redemption Scroll 275
    RedemptionTextFileService.downloadScroll275 = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/scrolls/getRedemptionScroll275",
        data: requestData,
      });
    };

    // Download Redemption Interest Scroll 276
    RedemptionTextFileService.downloadScroll276 = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/scrolls/getRedemptionInterestScroll276",
        data: requestData,
      });
    };

    // Download Redemption Covering Letter
    RedemptionTextFileService.downloadCoveringLetter = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/scrolls/getRedemptionCoveringLetter",
        data: requestData,
      });
    };
  }
})();
