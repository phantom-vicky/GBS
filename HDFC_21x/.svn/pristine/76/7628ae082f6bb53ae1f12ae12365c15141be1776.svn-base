(function() {

	'use strict';

	angular.module('gbmUltraApp').service(
			'codeMappingRbiBondService',
			codeMappingRbiBondService);

	codeMappingRbiBondService.$inject = [ '$http' ];

	function codeMappingRbiBondService($http) {

		var codeMappingRbiBondService = this;

		codeMappingRbiBondService.submitAddForm = submitAddForm;
		codeMappingRbiBondService.getRemarksList = getRemarksList;
		codeMappingRbiBondService.submitModifyForm = submitModifyForm;
		codeMappingRbiBondService.submitVerifyForm = submitVerifyForm;
		codeMappingRbiBondService.submitDeleteForm = submitDeleteForm;


		codeMappingRbiBondService.addValidation = addValidation;
		codeMappingRbiBondService.getInquirySelectDetails = getInquirySelectDetails;
		codeMappingRbiBondService.getVerifyDelSelectDetails = getVerifyDelSelectDetails;
		codeMappingRbiBondService.getModifySelect = getModifySelect;
		codeMappingRbiBondService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
		codeMappingRbiBondService.getBranches = getBranches;



		        function getRbiBondModuleNameDetails(requestData) {
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

		//New
				function getBranches(requestData){
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/codeMapping/getBranchCode',
                        headers: {
                             'Content-Type': 'application/json'
                         },
                        data: requestData
                   });
                }

		function addValidation(){
           return	$http({
               method : "GET",
               url : 'gbmultra/rbiBond/codeMapping/addValidation',
                headers: {
                     'Content-Type': 'application/json'
                 }
           });
        }
        //Add submit
        function submitAddForm(requestData) {
             return $http({
                  method: 'POST',
                  url: "gbmultra/rbiBond/codeMapping/add",
                  headers: {
                      'Content-Type': 'application/json'
                  },
                  data: requestData
              })
        }
        //Get Modify Select Details
        function getVerifyDelSelectDetails(requestData) {
             return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/codeMapping/verifyDeleteDetails',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: requestData
            })
        }
        //Modify Submit
        function submitModifyForm(requestData) {
             return $http({
                  method: 'POST',
                  url: "gbmultra/rbiBond/codeMapping/modifySubmit",
                  headers: {
                      'Content-Type': 'application/json'
                  },
                  data: requestData
              })
        }

        //Verify Submit
        function submitVerifyForm(requestData) {
             return $http({
                  method: 'POST',
                  url: "gbmultra/rbiBond/codeMapping/verify",
                  headers: {
                      'Content-Type': 'application/json'
                  },
                  data : requestData
              })
        }
        function submitDeleteForm(requestData) {
             return $http({
                  method: 'POST',
                  url: "gbmultra/rbiBond/codeMapping/delete",
                  headers: {
                      'Content-Type': 'application/json'
                  },
                  data: requestData
              })
        }
        //Get Inquiry Select Details
        function getInquirySelectDetails(requestData) {
             return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/codeMapping/inquiryDetails',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: requestData
            })
        }
        function recordExists(){
                     return $http({
                        method: 'POST',
                        url: 'gbmultra/rbiBond/codeMapping/recordExists',
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    })
        }

         //Get Modify Select Details
        function getModifySelect(requestData) {
             return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/codeMapping/modifyDetails',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: requestData
            })
        }

	}

})();