(function() {

	'use strict';

	angular.module('gbmUltraApp').service(
			'schemeMasterRbiBondService',
			schemeMasterRbiBondService);

	schemeMasterRbiBondService.$inject = [ '$http' ];

	function schemeMasterRbiBondService($http) {

		var schemeMasterRbiBondService = this;

		schemeMasterRbiBondService.submitAddForm = submitAddForm;
		schemeMasterRbiBondService.getRemarksList = getRemarksList;
		schemeMasterRbiBondService.inquiry = inquiry;
	    schemeMasterRbiBondService.modifySubmit = modifySubmit;
		schemeMasterRbiBondService.modifySelectDetails = modifySelectDetails;
		schemeMasterRbiBondService.verifySelectDetails = verifySelectDetails;
		schemeMasterRbiBondService.deleteSubmit = deleteSubmit;
		schemeMasterRbiBondService.verifySubmit = verifySubmit;

        //Add submit
        function submitAddForm(requestData) {
             return $http({
                  method: 'POST',
                  url: "gbmultra/rbiBond/schemeMaster/add",
                  headers: {
                      'Content-Type': 'application/json'
                  },
                  data: requestData
              })
        }

         function getRemarksList(requestData){
                                 return $http({
                                      method:'POST',
                                      url:'rbiBond/getRemarksList',
                                      data:requestData
                                 })
                    }

        function modifySelectDetails(requestData) {
                     return $http({
                          method: 'POST',
                          url: "gbmultra/rbiBond/schemeMaster/modifySelect",
                          headers: {
                              'Content-Type': 'application/json'
                          },
                          data: requestData
                      })
                }
                 function verifySelectDetails(requestData) {
                     return $http({
                          method: 'POST',
                          url: "gbmultra/rbiBond/schemeMaster/verifySelect",
                          headers: {
                              'Content-Type': 'application/json'
                          },
                          data: requestData
                      })
                }
                 function verifySubmit(requestData) {
                                     return $http({
                                          method: 'POST',
                                          url: "gbmultra/rbiBond/schemeMaster/verify",
                                          headers: {
                                              'Content-Type': 'application/json'
                                          },
                                          data: requestData
                                      })
                                }
                       function modifySubmit(requestData) {
                                 return $http({
                                           method: 'POST',
                                           url: "gbmultra/rbiBond/schemeMaster/modify",
                                           headers: {
                                                 'Content-Type': 'application/json'
                                                    },
                                           data: requestData
                                          })
                             }
                        function deleteSubmit(requestData) {
                                              return $http({
                                                       method: 'POST',
                                                       url: "gbmultra/rbiBond/schemeMaster/delete",
                                                       headers: {
                                                       'Content-Type': 'application/json'
                                                                 },
                                                       data: requestData
                                                       })
                                                       }
                                 function inquiry(requestData) {
                                                     return $http({
                                                          method: 'POST',
                                                          url: "gbmultra/rbiBond/schemeMaster/inquiry",
                                                          headers: {
                                                              'Content-Type': 'application/json'
                                                          },
                                                          data: requestData
                                                      })
                                                }
	}

})();