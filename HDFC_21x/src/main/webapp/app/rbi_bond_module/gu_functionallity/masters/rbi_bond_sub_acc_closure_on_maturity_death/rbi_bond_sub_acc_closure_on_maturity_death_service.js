(function() {

	'use strict';

	angular.module('gbmUltraApp').service(
			'bondSubAccCloseOnMaturDeathService',
			bondSubAccCloseOnMaturDeathService);

	bondSubAccCloseOnMaturDeathService.$inject = [ '$http' ];

	function bondSubAccCloseOnMaturDeathService($http) {
		var bondSubAccCloseOnMaturDeathService = this;


    bondSubAccCloseOnMaturDeathService.fetchCbsCustId = fetchCbsCustId;
        bondSubAccCloseOnMaturDeathService.getAddDetails = getAddDetails;
        bondSubAccCloseOnMaturDeathService.getVerifyDeleteDetails = getVerifyDeleteDetails;
        bondSubAccCloseOnMaturDeathService.getInquiryDetails = getInquiryDetails;
        bondSubAccCloseOnMaturDeathService.getAddValidation = getAddValidation;
        bondSubAccCloseOnMaturDeathService.addClosureOnMaturDeath = addClosureOnMaturDeath;
        bondSubAccCloseOnMaturDeathService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
        bondSubAccCloseOnMaturDeathService.getReferenceData=getReferenceData;
        bondSubAccCloseOnMaturDeathService.getPanValidation = getPanValidation;
         bondSubAccCloseOnMaturDeathService.downloadFile = downloadFile;
         bondSubAccCloseOnMaturDeathService.getRemarksList = getRemarksList;

        bondSubAccCloseOnMaturDeathService.uploadTextFile = uploadTextFile;
//		            function getRbiBondModuleNameDetails(requestData){
//                                 return $http({
//                                    method: 'POST',
//                                    url: 'rbiBond/getModuleDetails',
//                                    data :requestData
//                                 })
//                          }

                        function getReferenceData(requestData)
                                {
                                   return	$http({
                                       method : "POST",
                                       url : 'rbiBond/getReferenceData',
                                       data :requestData
                                   });
                                }

                        function getAddDetails(requestData){
                                  return $http({
                                      method: 'POST',
                                      url: 'rbiBond/accClosureOnMaturityDeath/getAddDetails',
                                      data :requestData
                                  })
                            }

                            function getAddValidation(requestData){
                                                              return $http({
                                                                  method: 'POST',
                                                                  url: 'rbiBond/accClosureOnMaturityDeath/getAddValidation',
                                                                  data :requestData
                                                              })
                                                        }
                         function getPanValidation(requestData){
                                                          return $http({
                                                              method: 'POST',
                                                              url: 'rbiBond/accClosureOnMaturityDeath/getPanValidation',
                                                              data :requestData
                                                          })
                                                    }

                       function getVerifyDeleteDetails(requestData){
                                  return $http({
                                      method: 'POST',
                                      url: 'rbiBond/accClosureOnMaturityDeath/getVerDelDetails',
                                      data :requestData
                                  })
                            }
   function uploadTextFile(formData) {
                   return $http.post('rbiBond/accClosureOnMaturityDeath/upload-text-file', formData, {
                           transformRequest: angular.identity,
                           headers: {'Content-Type': undefined,'Custom-Content-Type': 'upload'}
                   });
            }
                       function getInquiryDetails(requestData){
                                  return $http({
                                      method: 'POST',
                                      url: 'rbiBond/accClosureOnMaturityDeath/getInqDetails',
                                      data :requestData
                                  })
                            }
                       function addClosureOnMaturDeath(requestData){
                                  return $http({
                                      method: 'POST',
                                      url: 'rbiBond/accClosureOnMaturityDeath/add',
                                      data :requestData
                                  })
                            }

                       function verifyClosureBefMaturDeath(requestData){
                                  return $http({
                                      method: 'POST',
                                      url: 'rbiBond/accClosureOnMaturityDeath/verify',
                                      data :requestData
                                  })
                            }
                       function deleteClosureBefMaturDeath(requestData){
                                                           return $http({
                                                               method: 'POST',
                                                               url: 'rbiBond/accClosureOnMaturityDeath/delete',
                                                               data :requestData
                                                           })
                            }




                                              function getRbiBondModuleNameDetails(requestData){
                                                                 return $http({
                                                                     method: 'POST',
                                                                     url: 'gbmultra/rbiBond/blaCumSub/getModuleDetails',
                                                                     data :requestData
                                                                 })
                                                           }
 function getRemarksList(requestData){
               return $http({
                    method:'POST',
                    url:'rbiBond/getRemarksList',
                    data:requestData
               });
            }
   function downloadFile(requestData){
                  return $http({
                      method: 'POST',
                      url: 'rbiBond/accClosureOnMaturityDeath/download-file',
                       data :requestData
                  });
            }
   function fetchCbsCustId(accountNumber) {
      return $http({
        method: "POST",
        url: `external-service/api/account-validation?accountNumber=${accountNumber}`, // Pass accountNumber as a query parameter
        data: {},
      });
    }


		}

		})();