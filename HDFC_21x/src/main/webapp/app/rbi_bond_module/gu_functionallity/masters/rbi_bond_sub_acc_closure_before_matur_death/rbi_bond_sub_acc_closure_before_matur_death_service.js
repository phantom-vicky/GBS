(function() {

	'use strict';

	angular.module('gbmUltraApp').service(
			'bondSubAccCloseBeforeMaturDeathService',
			bondSubAccCloseBeforeMaturDeathService);

	bondSubAccCloseBeforeMaturDeathService.$inject = [ '$http' ];

	function bondSubAccCloseBeforeMaturDeathService($http) {
		var bondSubAccCloseBeforeMaturDeathService = this;


        bondSubAccCloseBeforeMaturDeathService.getAddDetails = getAddDetails;
        bondSubAccCloseBeforeMaturDeathService.getVerifyDeleteDetails = getVerifyDeleteDetails;
        bondSubAccCloseBeforeMaturDeathService.getInquiryDetails = getInquiryDetails;
        bondSubAccCloseBeforeMaturDeathService.addClosureBefMaturDeath = addClosureBefMaturDeath;
        bondSubAccCloseBeforeMaturDeathService.verifyClosureBefMaturDeath = verifyClosureBefMaturDeath;
        bondSubAccCloseBeforeMaturDeathService.deleteClosureBefMaturDeath = deleteClosureBefMaturDeath;
        bondSubAccCloseBeforeMaturDeathService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
        bondSubAccCloseBeforeMaturDeathService.getReferenceData=getReferenceData;
		            function getRbiBondModuleNameDetails(requestData){
                                 return $http({
                                    method: 'POST',
                                    url: 'rbiBond/getModuleDetails',
                                    data :requestData
                                 })
                          }

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
                                      url: 'rbiBond/accClosureBefMaturityDeath/getAddDetails',
                                      data :requestData
                                  })
                            }

                       function getVerifyDeleteDetails(requestData){
                                  return $http({
                                      method: 'POST',
                                      url: 'rbiBond/accClosureBefMaturityDeath/getVerDelDetails',
                                      data :requestData
                                  })
                            }

                       function getInquiryDetails(requestData){
                                  return $http({
                                      method: 'POST',
                                      url: 'rbiBond/accClosureBefMaturityDeath/getInqDetails',
                                      data :requestData
                                  })
                            }
                       function addClosureBefMaturDeath(requestData){
                                  return $http({
                                      method: 'POST',
                                      url: 'rbiBond/accClosureBefMaturityDeath/add',
                                      data :requestData
                                  })
                            }

                       function verifyClosureBefMaturDeath(requestData){
                                  return $http({
                                      method: 'POST',
                                      url: 'rbiBond/accClosureBefMaturityDeath/verify',
                                      data :requestData
                                  })
                            }
                       function deleteClosureBefMaturDeath(requestData){
                                                           return $http({
                                                               method: 'POST',
                                                               url: 'rbiBond/accClosureBefMaturityDeath/delete',
                                                               data :requestData
                                                           })
                            }





		}

		})();