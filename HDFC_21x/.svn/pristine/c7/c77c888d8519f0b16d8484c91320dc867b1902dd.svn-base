(function () {
  "use strict";

  angular.module("gbmUltraApp").service("BrokerAppointmentService", BrokerAppointmentService);

  BrokerAppointmentService.$inject = ["$http"];

  function BrokerAppointmentService($http) {
    var BrokerAppointmentService = this;

    BrokerAppointmentService.getDetails = function (requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbibond/reports/getBrokerAppointmentEnrolledDetails",
        data: requestData,
      });
    };

    BrokerAppointmentService.getReferenceData = function (requestData) {
      return $http({
        method: "POST",
        url: "rbiBond/getReferenceData",
        data: requestData,
      });
    };
  }
})();
