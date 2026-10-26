(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('rbiBondTrialRunForRedemptionService', rbiBondTrialRunForRedemptionService);

    rbiBondTrialRunForRedemptionService.$inject = ['$http'];

    function rbiBondTrialRunForRedemptionService($http) {

    	var rbiBondTrialRunForRedemptionService = this;

    	rbiBondTrialRunForRedemptionService.getTrialRunDetails = getTrialRunDetails;
    	rbiBondTrialRunForRedemptionService.getTorchDetails = getTorchDetails;
    	rbiBondTrialRunForRedemptionService.getTorchDetailsVerify = getTorchDetailsVerify;
    	rbiBondTrialRunForRedemptionService.blaNo = blaNo;
    	rbiBondTrialRunForRedemptionService.getPanTorchDetails = getPanTorchDetails;
        rbiBondTrialRunForRedemptionService.getValidateDetails = getValidateDetails;
        rbiBondTrialRunForRedemptionService.download = download;
 rbiBondTrialRunForRedemptionService.getDetails = getDetails;
 rbiBondTrialRunForRedemptionService.verifySubmit = verifySubmit;
 rbiBondTrialRunForRedemptionService.submitDeleteForm = submitDeleteForm;
 rbiBondTrialRunForRedemptionService.getRemarksList  =  getRemarksList;


    	function getPanTorchDetails(requestData){
                            return $http({
                                method: 'POST',
                                url: 'gbmultra/rbiBond/redemption/trial/run/getPanTorchDetails',
                                data :requestData
                            })
                      }


        function getTrialRunDetails(requestData){
                return $http({
                    method: "post",
                    url: "rbiBond/interest/trial/run/add",
                    data : requestData
                });
            }

         function getTorchDetails(requestData){
                return $http({
                    method: "post",
                    url: "gbmultra/rbiBond/redemption/trial/run/addTorch",
                    data : requestData
                });
            }
            function blaNo(requestData){
                return $http({
                    method: "post",
                    url: "gbmultra/rbiBond/redemption/trial/run/getBlaNo",
                    data : requestData
                });
            }
             function getTorchDetailsVerify(requestData){
                            return $http({
                                method: "post",
                                url: "gbmultra/rbiBond/redemption/trial/run/verifySelect",
                                data : requestData
                            });
                        }



        function getValidateDetails(requestData){
               return $http({
                  method: 'POST',
                  url: 'rbiBond/interest/trial/run/getTrialRunProcess',
                  data :requestData
             });
         }


         function download(requestData){
                                                      return $http({
                                                          method : "POST",
                                                          url : 'gbmultra/rbiBond/redemption/trial/run/download',
                                                          data : requestData
                                                      })
                                                  }


        function getDetails(requestData) {
                  return $http({
                      method: 'POST',
                      url: 'rbiBond/interest/trial/run/getInterestTrailRunVerify',
                      data: requestData
                  })
              }

/* Verify Submit */
        function verifySubmit(requestData)
        {
           return	$http({
               method : "post",
               url : 'gbmultra/rbiBond/redemption/trial/run/verifySubmit',
               data : requestData
           });
        }


  /* Delete Submit */
          function submitDeleteForm(requestData)
          {
             return	$http({
                 method : "post",
                 url : 'gbmultra/rbiBond/redemption/trial/run/delete',
                 data : requestData
             });
          }

           function getRemarksList(requestData){
                         return $http({
                              method:'POST',
                              url:'rbiBond/getRemarksList',
                              data:requestData
                         });
                      }
}

})();