(function () {
  "use strict";

  angular.module("gbmUltraApp").service("PreMaturityMarkingReportService", PreMaturityMarkingReportService);
  PreMaturityMarkingReportService.$inject = ["$http"];
  function PreMaturityMarkingReportService($http) {
    var PreMaturityMarkingReportService = this;
    PreMaturityMarkingReportService.getDetails = getDetails;
        PreMaturityMarkingReportService.getPreMaturityMarkingReportDownload = getPreMaturityMarkingReportDownload;

    function getDetails(requestData) {
                return $http({
                    method: 'POST',
                    url: 'gbmultra/rbibond/reports/getPreMaturityMarkingReport',
                    data: requestData
                })
            }
            function getPreMaturityMarkingReportDownload(requestData) {
                        return $http({
                            method: 'POST',
                            url: 'gbmultra/rbibond/reports/getPreMaturityMarkingReportDownload',
                            data: requestData
                        });


              }
  }
})();