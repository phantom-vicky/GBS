(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('rbiBondTdsDeductionCbService', rbiBondTdsDeductionCbService);

    rbiBondTdsDeductionCbService.$inject = ['$http'];

    function rbiBondTdsDeductionCbService($http) {

    	var rbiBondTdsDeductionCbService = this;

    	rbiBondTdsDeductionCbService.getTdsDeductionDet = getTdsDeductionDet;
    	rbiBondTdsDeductionCbService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
    	rbiBondTdsDeductionCbService.getTorchDetails = getTorchDetails;

    	function getRbiBondModuleNameDetails(requestData){
                                    return $http({
                                        method: 'POST',
                                        url: 'rbiBond/getModuleDetails',
                                        data :requestData
                                    })
                              }


        function getTdsDeductionDet(requestData){
                return $http({
                    method: "post",
                    url: "gbmultra/rbibond/reports/getTdsDeductionReportCb",
                    data : requestData
                });
            }

        function getTorchDetails(requestData){
                 return $http({
                    method: "post",
                    url: "gbmultra/rbibond/reports/getReportTorchBla",
                    data : requestData
                });
            }

    }

})();