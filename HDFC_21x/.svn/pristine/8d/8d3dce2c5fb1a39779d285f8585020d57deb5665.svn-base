(function () {
  "use strict";

  // Register the service with the exact name used in the controller
  angular.module("gbmUltraApp")
    .service("DashBoardReportForInwardOutwardService", DashBoardReportForInwardOutwardService);

  DashBoardReportForInwardOutwardService.$inject = ["$http"];

  function DashBoardReportForInwardOutwardService($http) {
    var service = this;

    service.getDetails = getDetails;
    service.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
    service.getDashBoardReportForInwardOutwardDownload=getDashBoardReportForInwardOutwardDownload;

    // Fetch dashboard details
    function getDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getDashBoardReportForInwardOutward",
        data: requestData,
      });
    }

    // Fetch RBI Bond module names (example placeholder)
    function getRbiBondModuleNameDetails(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getDashBoardReportForInwardOutward",
        data: requestData,
      });
    }

   function blaTorch(requestData)
                         {
                            return	$http({
                                method : "POST",
                                url : 'gbmultra/rbibond/reports/getDashBoardReportForInwardOutward',
                                data :requestData
                            });
                         }


   function blaNo(requestData)
                         {
                            return	$http({
                                method : "POST",
                                url : 'gbmultra/rbibond/reports/getDashBoardReportForInwardOutward',
                                data :requestData
                            });
                         }

    function getDashBoardReportForInwardOutwardDownload(requestData) {
                return $http({
                    method: 'POST',
                    url: 'gbmultra/rbibond/reports/getDashBoardReportForInwardOutwardDownload',
                    data: requestData
                });
            }

  }
})();
