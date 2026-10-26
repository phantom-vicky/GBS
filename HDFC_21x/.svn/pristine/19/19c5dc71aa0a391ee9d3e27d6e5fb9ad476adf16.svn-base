(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('rbiBondEodService', rbiBondEodService);

    rbiBondEodService.$inject = ['$http'];

    function rbiBondEodService($http) {
    	
    	var rbiBondEodService = this;
    	
        rbiBondEodService.startEodProcess = function(requestData) {
    	    return $http({
                    method: "POST",
                    url: 'gbmultra/rbibond/bodEod/startEodProcess',
                    data : requestData
            });
        }

        rbiBondEodService.getTimeValidation = function () {
                			return $http({
                				method: "post",
                				url: 'gbmultra/rbibond/bodEod/getTimeValidation'
                			});
                		}
    	
    }
})();    