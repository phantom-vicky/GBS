(function() {

	'use strict';

	angular.module('gbmUltraApp').service('bondMinorToMajorIntimationNoticeService',bondMinorToMajorIntimationNoticeService);

	bondMinorToMajorIntimationNoticeService.$inject = [ '$http' ];

	function bondMinorToMajorIntimationNoticeService($http) {
		var bondMinorToMajorIntimationNoticeService = this;


    bondMinorToMajorIntimationNoticeService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
    bondMinorToMajorIntimationNoticeService.getAddDetails = getAddDetails;



         function getAddDetails(requestData){
                  return $http({
                      method: 'POST',
                      url: 'gbmultra/rbibond/reports/getminorToMajorIntimationRepValues',
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









		}

		})();