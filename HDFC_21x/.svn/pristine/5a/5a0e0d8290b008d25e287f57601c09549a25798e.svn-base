(function () {
  "use strict";

  angular.module("gbmUltraApp").service("cancellationSubscriptionCatCbtService", cancellationSubscriptionCatCbtService);

  cancellationSubscriptionCatCbtService.$inject = ["$http"];

  function cancellationSubscriptionCatCbtService($http) {
    var cancellationSubscriptionCatCbtService = this;
    cancellationSubscriptionCatCbtService.addCloserSubCatCbt = addCloserSubCatCbt;
    cancellationSubscriptionCatCbtService.verifyRejectCloserSubCatCbt = verifyRejectCloserSubCatCbt;
    cancellationSubscriptionCatCbtService.inquiryCloserSubCatCbt = inquiryCloserSubCatCbt;
    cancellationSubscriptionCatCbtService.rejectCloserSubCatCbt = rejectCloserSubCatCbt;
    cancellationSubscriptionCatCbtService.verifyCloserSubCatCbt = verifyCloserSubCatCbt;
    cancellationSubscriptionCatCbtService.addSubCloserSubCatCbt = addSubCloserSubCatCbt;
    cancellationSubscriptionCatCbtService.getReferenceData = getReferenceData;

    cancellationSubscriptionCatCbtService.getRemarksList = function (requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getRemarksList",
        data: requestData,
      });
    };

    function addCloserSubCatCbt(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/CancelSub/addCloserSubCatCbt",
        data: requestData,
      });
    }
    function inquiryCloserSubCatCbt(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/CancelSub/inquiryCloserSubCatCbt",
        data: requestData,
      });
    }

    function verifyRejectCloserSubCatCbt(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/CancelSub/verifyRejectCloserSubCatCbt",
        data: requestData,
      });
    }

    function rejectCloserSubCatCbt(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/CancelSub/rejectCloserSubCatCbt",
        data: requestData,
      });
    }

    function verifyCloserSubCatCbt(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/CancelSub/verifyCloserSubCatCbt",
        data: requestData,
      });
    }

    function addSubCloserSubCatCbt(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/CancelSub/addSubCloserSubCatCbt",
        data: requestData,
      });
    }
    function getReferenceData(requestData) {
          return $http({
            method: "POST",
            url: "rbiBond/getReferenceData",
            data: requestData,
          });
        }
  }
})();
