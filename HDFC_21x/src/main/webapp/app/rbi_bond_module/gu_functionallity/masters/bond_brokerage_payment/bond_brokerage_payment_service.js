(function() {

	'use strict';

	angular.module('gbmUltraApp').service(
			'rbiBondBrokeragePaymentService',
			rbiBondBrokeragePaymentService);

	rbiBondBrokeragePaymentService.$inject = [ '$http' ];

	function rbiBondBrokeragePaymentService($http) {
		var rbiBondBrokeragePaymentService = this;


		rbiBondBrokeragePaymentService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
		rbiBondBrokeragePaymentService.getReferenceData = getReferenceData;
		rbiBondBrokeragePaymentService.getValidationAdd = getValidationAdd;
		rbiBondBrokeragePaymentService.addSubmit = addSubmit;
		rbiBondBrokeragePaymentService.inquiry = inquiry;
		rbiBondBrokeragePaymentService.verifySelect = verifySelect;
		rbiBondBrokeragePaymentService.deleteSubmit = deleteSubmit;
		rbiBondBrokeragePaymentService.verifySubmit = verifySubmit;
        rbiBondBrokeragePaymentService.transactionValidation=transactionValidation;

		        function getRbiBondModuleNameDetails(requestData){
                    return $http({
                        method: 'POST',
                        url: 'rbiBond/getModuleDetails',
                        data :requestData
                    })
              }


                function transactionValidation(requestData){
                       return	$http({
                           method : "POST",
                           url : 'gbmultra/rbiBond/brokeragePayment/tranValidation',
                           data :requestData
                       });
                    }

		        function getReferenceData(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'rbiBond/getReferenceData',
                       data :requestData
                   });
                }
              function getValidationAdd(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/brokeragePayment/addValidation',
                       data :requestData
                   });
                }
               function addSubmit(requestData){
                       return	$http({
                           method : "POST",
                           url : 'gbmultra/rbiBond/brokeragePayment/add',
                           data :requestData
                       });
                    }


           function inquiry(requestData){
               return	$http({
                   method : "POST",
                   url : 'gbmultra/rbiBond/brokeragePayment/inquiry',
                   data :requestData
               });
            }

          function verifySelect(requestData){
               return	$http({
                   method : "POST",
                   url : 'gbmultra/rbiBond/brokeragePayment/verifySelect',
                   data :requestData
               });
            }

          function deleteSubmit(requestData){
               return	$http({
                   method : "POST",
                   url : 'gbmultra/rbiBond/brokeragePayment/delete',
                   data :requestData
               });
            }

         function verifySubmit(requestData){
           return	$http({
               method : "POST",
               url : 'gbmultra/rbiBond/brokeragePayment/verify',
               data :requestData
           });
        }




		}

		})();