(function() {
	'use strict';
	angular.module('gbmUltraApp').service(
			'preMaturityClosureMarkingService',
			preMaturityClosureMarkingService);
	preMaturityClosureMarkingService.$inject = [ '$http' ];
	function preMaturityClosureMarkingService($http) {
		var preMaturityClosureMarkingService = this;
		preMaturityClosureMarkingService.getRemarksList = getRemarksList;
        preMaturityClosureMarkingService.getAddPanTorchBlaNoTorchDetails=getAddPanTorchBlaNoTorchDetails;
        preMaturityClosureMarkingService.getVerifyPanTorchBlaNoTorchDetails=getVerifyPanTorchBlaNoTorchDetails;
        preMaturityClosureMarkingService.getModifyPanTorchBlaNoTorchDetails=getModifyPanTorchBlaNoTorchDetails;
        preMaturityClosureMarkingService.getInquiryPanTorchBlaNoTorchDetails=getInquiryPanTorchBlaNoTorchDetails;
        preMaturityClosureMarkingService.getAddDetails=getAddDetails;
        preMaturityClosureMarkingService.getModifyDetails=getModifyDetails;
        preMaturityClosureMarkingService.getVerifyDetails=getVerifyDetails;
        preMaturityClosureMarkingService.getInquiryDetails=getInquiryDetails;
        preMaturityClosureMarkingService.addSubmit=addSubmit;
        preMaturityClosureMarkingService.verifySubmit=verifySubmit;
        preMaturityClosureMarkingService.modifySubmit=modifySubmit;
        preMaturityClosureMarkingService.deleteSubmit=deleteSubmit;
          function getAddPanTorchBlaNoTorchDetails(requestData) {
                                    return $http({
                                        method: 'POST',
                                        url: 'gbmultra/rbiBond/prematurity/marking/addTorch',
                                        data :requestData,
                                        headers: {
                                            'Content-Type': 'application/json'
                                          }
                                    })
                              }

           function getRemarksList(requestData){
                                   return $http({
                                        method:'POST',
                                        url:'rbiBond/getRemarksList',
                                        data:requestData
                                   })
                      }

            function getVerifyPanTorchBlaNoTorchDetails(requestData) {
                                      return $http({
                                          method: 'POST',
                                          url: 'gbmultra/rbiBond/prematurity/marking/verifyTorch',
                                          data :requestData,
                                          headers: {
                                              'Content-Type': 'application/json'
                                            }
                                      })
                                }
             function getModifyPanTorchBlaNoTorchDetails(requestData) {
                                       return $http({
                                           method: 'POST',
                                           url: 'gbmultra/rbiBond/prematurity/marking/modifyTorch',
                                           data :requestData,
                                           headers: {
                                               'Content-Type': 'application/json'
                                             }
                                       })
                                 }
               function getInquiryPanTorchBlaNoTorchDetails(requestData) {
                                         return $http({
                                             method: 'POST',
                                             url: 'gbmultra/rbiBond/prematurity/marking/inquiryTorch',
                                             data :requestData,
                                             headers: {
                                                 'Content-Type': 'application/json'
                                               }
                                         })
                                   }
        function getAddDetails(requestData) {
                            return $http({
                                method: 'POST',
                                url: 'gbmultra/rbiBond/prematurity/marking/addDetails',
                                data :requestData,
                                headers: {
                                    'Content-Type': 'application/json'
                                  }
                            })
                      }

        function getModifyDetails(requestData) {
                            return $http({
                                method: 'POST',
                                url: 'gbmultra/rbiBond/prematurity/marking/modifyDetails',
                                data :requestData,
                                headers: {
                                    'Content-Type': 'application/json'
                                  }
                            })
                      }
        function getInquiryDetails(requestData) {
                            return $http({
                                method: 'POST',
                                url: 'gbmultra/rbiBond/prematurity/marking/inquiryDetails',
                                data :requestData,
                                headers: {
                                    'Content-Type': 'application/json'
                                  }
                            })
                      }
        function getVerifyDetails(requestData) {
                            return $http({
                                method: 'POST',
                                url: 'gbmultra/rbiBond/prematurity/marking/verifyDetails',
                                data :requestData,
                                headers: {
                                    'Content-Type': 'application/json'
                                  }
                            })
                      }
        function addSubmit(requestData) {
                            return $http({
                                method: 'POST',
                                url: 'gbmultra/rbiBond/prematurity/marking/add',
                                data :requestData,
                                headers: {
                                    'Content-Type': 'application/json'
                                  }
                            })
                      }
        function modifySubmit(requestData) {
                            return $http({
                                method: 'POST',
                                url: 'gbmultra/rbiBond/prematurity/marking/modify',
                                data :requestData,
                                headers: {
                                    'Content-Type': 'application/json'
                                  }
                            })
                      }
        function deleteSubmit(requestData) {
                            return $http({
                                method: 'POST',
                                url: 'gbmultra/rbiBond/prematurity/marking/delete',
                                data :requestData,
                                headers: {
                                    'Content-Type': 'application/json'
                                  }
                            })
                      }
        function verifySubmit(requestData) {
                            return $http({
                                method: 'POST',
                                url: 'gbmultra/rbiBond/prematurity/marking/verify',
                                data :requestData,
                                headers: {
                                    'Content-Type': 'application/json'
                                  }
                            })
                      }
		}
		})();