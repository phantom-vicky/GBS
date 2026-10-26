(function() {

	'use strict';

	angular.module('gbmUltraApp').service(
			'rejectionReasonMasterService',
			rejectionReasonMasterService);

	rejectionReasonMasterService.$inject = [ '$http' ];
	function rejectionReasonMasterService($http) {
    		var rejectionReasonMasterService = this;
    		rejectionReasonMasterService.getRemarksList = getRemarksList;
            rejectionReasonMasterService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
            rejectionReasonMasterService.dropDownList = dropDownList;
            rejectionReasonMasterService.addDetails = addDetails;
            rejectionReasonMasterService.addSubmit = addSubmit;
            rejectionReasonMasterService.modifyDetails = modifyDetails;
            rejectionReasonMasterService.modifySubmit = modifySubmit;
            rejectionReasonMasterService.verifyDetails = verifyDetails;
            rejectionReasonMasterService.verifySubmit = verifySubmit;
            rejectionReasonMasterService.inquiry = inquiry;
            rejectionReasonMasterService.reject = reject;

            function getRbiBondModuleNameDetails(requestData){
                return $http({
                    method: 'POST',
                    url: 'rbiBond/getModuleDetails',
                    data :requestData
                });
            }
            function getRemarksList(requestData){
                                     return $http({
                                          method:'POST',
                                          url:'rbiBond/getRemarksList',
                                          data:requestData
                                     })
                        }

            function dropDownList(requestData){
              return $http({
                  method: 'POST',
                  url: 'rbiBond/getModuleDetails',
                  data :requestData
              });
            }

            function addDetails(requestData){
                  return $http({
                      method: 'POST',
                      url: 'apis/rejection-reason-master/add-details',
                      data :requestData
                  });
            }

           function addSubmit(requestData){
                 return $http({
                     method: 'POST',
                     url: 'apis/rejection-reason-master/add-submit',
                     data :requestData
                 });
           }
           function modifyDetails(requestData){
                return $http({
                    method: 'POST',
                    url: 'apis/rejection-reason-master/modify-details',
                    data :requestData
                });
           }
           function modifySubmit(requestData){
               return $http({
                   method: 'POST',
                   url: 'apis/rejection-reason-master/modify-submit',
                   data :requestData
               });
           }
           function verifyDetails(requestData){
              return $http({
                  method: 'POST',
                  url: 'apis/rejection-reason-master/verify-details',
                  data :requestData
              });
           }

           function verifySubmit(requestData){
             return $http({
                 method: 'POST',
                 url: 'apis/rejection-reason-master/verify-submit',
                 data :requestData
             });
           }

           function inquiry(requestData){
                return $http({
                    method: 'POST',
                    url: 'apis/rejection-reason-master/inquiry',
                    data :requestData
                });
           }


       function reject(requestData){
                return $http({
                    method: 'POST',
                    url: 'apis/rejection-reason-master/reject',
                    data :requestData
                });
           }
	}
})();