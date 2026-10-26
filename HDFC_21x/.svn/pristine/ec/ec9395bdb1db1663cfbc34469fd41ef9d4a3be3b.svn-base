(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('rbibondBodService', rbibondBodService);

    rbibondBodService.$inject = ['$http'];

    function rbibondBodService($http) {

    	var rbibondBodService = this;

        rbibondBodService.startBodProcess = function(requestData) {
    	    return $http({
                    method: "POST",
                    url: 'gbmultra/rbibond/bodEod/startBodProcess',
                    data : requestData
            });
        }

    }
})();