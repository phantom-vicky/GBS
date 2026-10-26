(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('rbibondTdsDeductionService', rbibondTdsDeductionService);

    rbibondTdsDeductionService.$inject = ['$http'];

    function rbibondTdsDeductionService($http) {

    	var rbibondTdsDeductionService = this;

 rbibondTdsDeductionService.getDetails = getDetails;
 rbibondTdsDeductionService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
 rbibondTdsDeductionService.getTdsDetails = getTdsDetails;

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
                url: 'gbmultra/rbibond/reports/getTdsDeductReport',
                data: requestData
            })
        }

         function getTdsDetails(requestData) {
                    return $http({
                        method: 'POST',
                        url: 'gbmultra/rbibond/reports/getTdsDeductDetails',
                        data: requestData
                    })
                }



    }

})();