(function() {

	'use strict';

	angular.module('gbmUltraApp').service(
			'tdsParameterRbiBondService',
			tdsParameterRbiBondService);

	tdsParameterRbiBondService.$inject = [ '$http' ];

	function tdsParameterRbiBondService($http) {

		var tdsParameterRbiBondService = this;

		tdsParameterRbiBondService.getRemarksList = getRemarksList;

		tdsParameterRbiBondService.submitAddForm = submitAddForm;
		tdsParameterRbiBondService.submitModifyForm = submitModifyForm;
		tdsParameterRbiBondService.submitVerifyForm = submitVerifyForm;
		tdsParameterRbiBondService.submitDeleteForm = submitDeleteForm;


		tdsParameterRbiBondService.addValidation = addValidation;
		tdsParameterRbiBondService.getInquirySelectDetails = getInquirySelectDetails;
		tdsParameterRbiBondService.getVerifyDelSelectDetails = getVerifyDelSelectDetails;
		tdsParameterRbiBondService.getModifySelect = getModifySelect;
		tdsParameterRbiBondService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
		tdsParameterRbiBondService.getInquirySelectDetails1 = getInquirySelectDetails1;

		 function getInquirySelectDetails1(requestData) {
                     return $http({
                        method: 'POST',
                        url: 'gbmultra/rbiBond/tocSlab/inquiry',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        data: requestData
                    })
                }

              function getRemarksList(requestData) {
                      return $http({
                         method: 'POST',
                         url: 'rbiBond/getRemarksList',
                         data: requestData
                      })
                   }


		        function getRbiBondModuleNameDetails(requestData){
                    return $http({
                        method: 'POST',
                        url: 'rbiBond/getModuleDetails',
                        data :requestData
                    })
              }

		//New
		function addValidation(requestData){
           return	$http({
               method : "POST",
               url : 'gbmultra/rbiBond/tdsParameter/addValidation',
                headers: {
                     'Content-Type': 'application/json'
                 },
                data: requestData
           });
        }


        //Add submit
        function submitAddForm(requestData) {
             return $http({
                  method: 'POST',
                  url: "gbmultra/rbiBond/tdsParameter/add",
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
                url: 'gbmultra/rbiBond/tdsParameter/verifyDeleteDetails',
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
                  url: "gbmultra/rbiBond/tdsParameter/modifySubmit",
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
                  url: "gbmultra/rbiBond/tdsParameter/verify",
                  headers: {
                      'Content-Type': 'application/json'
                  },
                  data : requestData
              })
        }

        function submitDeleteForm(requestData) {
             return $http({
                  method: 'POST',
                  url: "gbmultra/rbiBond/tdsParameter/delete",
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
                url: 'gbmultra/rbiBond/tdsParameter/inquiryDetails',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: requestData
            })
        }

         //Get Modify Select Details
        function getModifySelect(requestData) {
             return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/tdsParameter/modifyDetails',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: requestData
            })
        }

	}

})();