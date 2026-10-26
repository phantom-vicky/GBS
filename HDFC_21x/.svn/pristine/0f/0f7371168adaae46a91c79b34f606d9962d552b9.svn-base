(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('rbiBondCentralizedTrialRunService', rbiBondCentralizedTrialRunService);

    rbiBondCentralizedTrialRunService.$inject = ['$http'];

    function rbiBondCentralizedTrialRunService($http) {

    	var rbiBondCentralizedTrialRunService = this;
        rbiBondCentralizedTrialRunService.getRemarksList = getRemarksList;
    	rbiBondCentralizedTrialRunService.getTrialRunDetails = getTrialRunDetails;
    	rbiBondCentralizedTrialRunService.getTorchDetails = getTorchDetails;
    	rbiBondCentralizedTrialRunService.getTorchDetailsVerify = getTorchDetailsVerify;
    	rbiBondCentralizedTrialRunService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
        rbiBondCentralizedTrialRunService.getValidateDetails = getValidateDetails;
        rbiBondCentralizedTrialRunService.download = download;
 rbiBondCentralizedTrialRunService.getDetails = getDetails;
 rbiBondCentralizedTrialRunService.verifySubmit = verifySubmit;
 rbiBondCentralizedTrialRunService.submitDeleteForm = submitDeleteForm;
 rbiBondCentralizedTrialRunService.getDownload = getDownload;


    	function getRbiBondModuleNameDetails(requestData){
                            return $http({
                                method: 'POST',
                                url: 'rbiBond/getModuleDetails',
                                data :requestData
                            })
                      }


        function getRemarksList(requestData){
                         return $http({
                              method:'POST',
                              url:'rbiBond/getRemarksList',
                              data:requestData
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
                    url: "rbiBond/interest/trial/run/addTorch",
                    data : requestData
                });
            }

             function getTorchDetailsVerify(requestData){
                            return $http({
                                method: "post",
                                url: "rbiBond/interest/trial/run/verifySelect",
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
                                                          url : 'rbiBond/interest/trial/run/download',
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

        function getDownload(requestData) {
                  return $http({
                      method: 'POST',
                      url: 'rbiBond/interest/trial/run/getInterestTrailRunVerifyDownload',
                      data: requestData
                  })
              }

/* Verify Submit */
        function verifySubmit(requestData)
        {
           return	$http({
               method : "post",
               url : 'rbiBond/interest/trial/run/verifySubmit',
               data : requestData
           });
        }


  /* Delete Submit */
          function submitDeleteForm(requestData)
          {
             return	$http({
                 method : "post",
                 url : 'rbiBond/interest/trial/run/delete',
                 data : requestData
             });
          }
}

})();