(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('UnlockBlaService', UnlockBlaService);

    UnlockBlaService.$inject = ['$http'];

    function UnlockBlaService($http) {

    	var UnlockBlaService = this;

 UnlockBlaService.getDetails = getDetails;
 UnlockBlaService.unlock = unlock;

 function unlock(requestData){
             return $http({
                 method: 'POST',
                 url: 'gbmultra/rbibond/reports/unlockBlaSubmit',
                 data :requestData
             })
       }


        function getDetails(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/reports/unlockBla',
                data: requestData
            })
        }


    }

})();