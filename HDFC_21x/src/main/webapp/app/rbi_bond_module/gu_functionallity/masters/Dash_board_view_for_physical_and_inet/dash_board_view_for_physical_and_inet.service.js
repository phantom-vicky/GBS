(function() {

	'use strict';

	angular.module('gbmUltraApp').service(
			'dashBoardViewForPhysicalAndInetService',
			dashBoardViewForPhysicalAndInetService);

	dashBoardViewForPhysicalAndInetService.$inject = [ '$http' ];

	function dashBoardViewForPhysicalAndInetService($http) {
		var dashBoardViewForPhysicalAndInetService = this;
		dashBoardViewForPhysicalAndInetService.getRemarksList = getRemarksList;
		dashBoardViewForPhysicalAndInetService.submitAddForm = submitAddForm;
		dashBoardViewForPhysicalAndInetService.deleteSubmit = deleteSubmit;
		dashBoardViewForPhysicalAndInetService.submitVerifyForm = submitVerifyForm;
		dashBoardViewForPhysicalAndInetService.verifySelect = verifySelect;
		dashBoardViewForPhysicalAndInetService.getAddValidation = getAddValidation;
		dashBoardViewForPhysicalAndInetService.addDeleteSubmit = addDeleteSubmit;
		dashBoardViewForPhysicalAndInetService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

		dashBoardViewForPhysicalAndInetService.inquiry = inquiry;
      function getRbiBondModuleNameDetails(requestData) {
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
                                       })
                          }

               function getAddValidation(requestData) {
                    return $http({
                        method: 'POST',
                        url: 'gbmultra/rbiBond/dashBoardView/addValidation',
                        data :requestData
                    })
              }
               function addDeleteSubmit(requestData) {
                                  return $http({
                                      method: 'POST',
                                      url: 'gbmultra/rbiBond/dashBoardView/addRejection',
                                      data :requestData
                                  })
                            }
		        function submitVerifyForm(requestData) {
                    return $http({
                        method: 'POST',
                        url: 'gbmultra/rbiBond/dashBoardView/verify',
                        data :requestData
                    })
              }
               function deleteSubmit(requestData) {
                                  return $http({
                                      method: 'POST',
                                      url: 'gbmultra/rbiBond/dashBoardView/delete',
                                      data :requestData
                                  })
                            }
                             function inquiry(requestData) {
                                                              return $http({
                                                                  method: 'POST',
                                                                  url: 'gbmultra/rbiBond/dashBoardView/inquiry',
                                                                  data :requestData
                                                              })
                                                        }
              function verifySelect(requestData) {
                                  return $http({
                                      method: 'POST',
                                      url: 'gbmultra/rbiBond/dashBoardView/verifySelect',
                                      data :requestData
                                  })
                            }
        //Add submit
      function submitAddForm(requestData) {
                                        return $http({
                                            method: 'POST',
                                            url: 'gbmultra/rbiBond/dashBoardView/add',
                                            data :requestData
                                        })
                                  }

	}

})();