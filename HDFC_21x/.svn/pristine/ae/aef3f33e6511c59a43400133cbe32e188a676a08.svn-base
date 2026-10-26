(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('rbiBondCenInterestPaymentRunService', rbiBondCenInterestPaymentRunService);

    rbiBondCenInterestPaymentRunService.$inject = ['$http'];

    function rbiBondCenInterestPaymentRunService($http) {

    	var rbiBondCenInterestPaymentRunService = this;

    	rbiBondCenInterestPaymentRunService.getTorchDetails = getTorchDetails;
    	rbiBondCenInterestPaymentRunService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
        rbiBondCenInterestPaymentRunService.getInterestPaymentDetails = getInterestPaymentDetails;
        rbiBondCenInterestPaymentRunService.getIntPayUnverifiedRecords = getIntPayUnverifiedRecords;
        rbiBondCenInterestPaymentRunService.getInterestPaymentBatchTransaction = getInterestPaymentBatchTransaction;
    	rbiBondCenInterestPaymentRunService.getInterestPaymentStatus = getInterestPaymentStatus;



function getInterestPaymentBatchTransaction(requestData){
return $http({
method :'POST',
url : 'rbiBond/interest/trial/run/getInterestPayBatchTranDetails',
data : requestData
})
}


    	function getRbiBondModuleNameDetails(requestData){
                            return $http({
                                method: 'POST',
                                url: 'rbiBond/getModuleDetails',
                                data :requestData
                            })
                      }


        function getInterestPaymentStatus(requestData){
                   return $http({
                       method: "post",
                       url: "rbiBond/interest/trial/run/interestPaymentStatus",
                       data : requestData
                   });
               }

        function getInterestPaymentDetails(requestData){
                return $http({
                    method: "post",
                    url: "rbiBond/interest/trial/run/add",
                    data : requestData
                });
            }

         function getIntPayUnverifiedRecords(requestData){
                return $http({
                    method: "post",
                    url: "rbiBond/interest/trial/run/getIntPayUnverifiedRecords",
                    data : requestData
                });
            }

          function getTorchDetails(requestData){
                return $http({
                    method: "post",
                    url: "rbiBond/interest/trial/run/interestPaymentAddTorch",
                    data : requestData
                });
            }

        function actualRunProcess(requestData){
               return $http({
                  method: 'POST',
                  url: 'rbiBond/interest/trial/run/actualRunProcess',
                  data :requestData
             });
         }


  }

})();