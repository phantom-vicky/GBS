(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('scrollNumberMaintenanceService', scrollNumberMaintenanceService);

    scrollNumberMaintenanceService.$inject = ['$http'];

    function scrollNumberMaintenanceService($http) {

    	var scrollNumberMaintenanceService = this;
    	scrollNumberMaintenanceService.inquiry = inquiry;

        scrollNumberMaintenanceService.insertScrollNumber = function(requestData) {
                    return $http({
                        method: 'POST',
                        url: "gbmultra/stateTax/scroll/number/maintenance/save",
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        data: requestData
                    })
            }

        scrollNumberMaintenanceService.modifyScrollNumber = function(requestData) {
                    return $http({
                        method: 'POST',
                        url: "gbmultra/stateTax/scroll/number/maintenance/modify",
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        data: requestData
                    })
        }
        scrollNumberMaintenanceService.verifyScrollNumber = function(requestData) {
                    return $http({
                        method: 'POST',
                        url: "gbmultra/stateTax/scroll/number/maintenance/verify",
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        data: requestData
                    })
        }
        function inquiry(requestData)
        {
           return	$http({
           method : "POST",
            url : 'gbmultra/stateTax/scroll/number/maintenance/inquiry',
             data: requestData
           });
        }


    }

})();