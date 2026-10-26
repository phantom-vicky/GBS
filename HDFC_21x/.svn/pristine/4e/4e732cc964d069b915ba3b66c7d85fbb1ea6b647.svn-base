(function () {

  'use strict';

  angular.module('gbmUltraApp').service(
    'acknowledgementService',
    acknowledgementService);

  acknowledgementService.$inject = ['$http'];
  function acknowledgementService($http) {
    var acknowledgementService = this;
    acknowledgementService.getDetails = getDetails;



    function getDetails(requestData){
      return $http({
            method: 'POST',
            url: ' gbmultra/rbibond/reports/getAcknowledgement',
            data: requestData,
          });
    }
  }
})();