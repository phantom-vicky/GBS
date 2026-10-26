(function () {

  'use strict';

  angular.module('gbmUltraApp').service(
    'tdsEligibilityService',
    tdsEligibilityService);

  tdsEligibilityService.$inject = ['$http'];
  function tdsEligibilityService($http) {
    var tdsEligibilityService = this;

 tdsEligibilityService.getReferenceData = function (referenceId) {
                                 return $http({
                                     method  : 'POST',
                                     url     : 'rbiBond/getReferenceData',
                                     data    : referenceId
                                 });
                }

    tdsEligibilityService.detailsPage = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getTdsEligibility",
        data: requestData,
      });
    }

    tdsEligibilityService.getDetailsDownload = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getTdsEligibilityDownload",
        data: requestData,
      });
    }

  }
})();