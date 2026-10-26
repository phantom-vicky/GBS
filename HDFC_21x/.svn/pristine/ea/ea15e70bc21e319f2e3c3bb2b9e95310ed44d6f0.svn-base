(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('rbibondInterRunCretCollBranService', rbibondInterRunCretCollBranService);

    rbibondInterRunCretCollBranService.$inject = ['$http'];

    function rbibondInterRunCretCollBranService($http) {

    	var rbibondInterRunCretCollBranService = this;

 rbibondInterRunCretCollBranService.getDetails = getDetails;
 rbibondInterRunCretCollBranService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;


 function getRbiBondModuleNameDetails(requestData){
             return $http({
                 method: 'POST',
                 url: 'rbiBond/getModuleDetails',
                 data :requestData
             })
       }


        function getDetails(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/reports/getInterRunCrdtCollBranReport',
                data: requestData
            })
        }

        }

})();