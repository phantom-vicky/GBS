(function() {

	'use strict';

	angular.module('gbmUltraApp').service(
			'bondSubAccCloseBeforeMaturService',
			bondSubAccCloseBeforeMaturService);

	bondSubAccCloseBeforeMaturService.$inject = [ '$http' ];

	function bondSubAccCloseBeforeMaturService($http) {
		var bondSubAccCloseBeforeMaturService = this;


        bondSubAccCloseBeforeMaturService.getAddDetails = getAddDetails;
        bondSubAccCloseBeforeMaturService.getVerifyDeleteDetails = getVerifyDeleteDetails;
        bondSubAccCloseBeforeMaturService.getInquiryDetails = getInquiryDetails;
        bondSubAccCloseBeforeMaturService.addClosureBefMatur = addClosureBefMatur;
        bondSubAccCloseBeforeMaturService.verifyClosureBefMatur = verifyClosureBefMatur;
        bondSubAccCloseBeforeMaturService.deleteClosureBefMatur = deleteClosureBefMatur;
        bondSubAccCloseBeforeMaturService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

		            function getRbiBondModuleNameDetails(requestData){
                                 return $http({
                                    method: 'POST',
                                    url: 'rbiBond/getModuleDetails',
                                    data :requestData
                                 })
                          }


                        function getAddDetails(requestData){
                                  return $http({
                                      method: 'POST',
                                      url: 'rbiBond/accClosureBefMaturity/getAddDetails',
                                      data :requestData
                                  })
                            }

                       function getVerifyDeleteDetails(requestData){
                                  return $http({
                                      method: 'POST',
                                      url: 'rbiBond/accClosureBefMaturity/getVerDelDetails',
                                      data :requestData
                                  })
                            }

                       function getInquiryDetails(requestData){
                                  return $http({
                                      method: 'POST',
                                      url: 'rbiBond/accClosureBefMaturity/getInqDetails',
                                      data :requestData
                                  })
                            }
                       function addClosureBefMatur(requestData){
                                  return $http({
                                      method: 'POST',
                                      url: 'rbiBond/accClosureBefMaturity/add',
                                      data :requestData
                                  })
                            }

                       function verifyClosureBefMatur(requestData){
                                  return $http({
                                      method: 'POST',
                                      url: 'rbiBond/accClosureBefMaturity/verify',
                                      data :requestData
                                  })
                            }
                       function deleteClosureBefMatur(requestData){
                                                           return $http({
                                                               method: 'POST',
                                                               url: 'rbiBond/accClosureBefMaturity/delete',
                                                               data :requestData
                                                           })
                            }





		}

		})();