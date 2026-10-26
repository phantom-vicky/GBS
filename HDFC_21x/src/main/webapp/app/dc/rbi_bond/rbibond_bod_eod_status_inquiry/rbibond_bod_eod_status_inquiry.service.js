(function() {

        'use strict';

        angular
            .module('gbmUltraApp')
            .service('rbiBondBodEodStatusService', rbiBondBodEodStatusService);

        rbiBondBodEodStatusService.$inject = ['$http'];

        function rbiBondBodEodStatusService($http) {

            var rbiBondBodEodStatusService = this;
              rbiBondBodEodStatusService.getDetails=getDetails;

            function getDetails(requestData){
                  return $http({
                   method : 'POST',
                   url : 'gbmultra/rbibond/reports/getBodEodStatusInquiryDetails',
                   data : requestData
                  })
            }

        }
})();