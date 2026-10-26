(function() {

	'use strict';

	angular.module('gbmUltraApp').service('brokerageInvoiceGenerationService',brokerageInvoiceGenerationService);

	brokerageInvoiceGenerationService.$inject = [ '$http' ];

	function brokerageInvoiceGenerationService($http) {
		var brokerageInvoiceGenerationService = this;


    brokerageInvoiceGenerationService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
    brokerageInvoiceGenerationService.getAddDetails = getAddDetails;
    brokerageInvoiceGenerationService.generateBrokerRes = generateBrokerRes;



    brokerageInvoiceGenerationService.getBrokerageInvoiceBondScheme=getBrokerageInvoiceBondScheme;
    brokerageInvoiceGenerationService.getBrokerageAgencyCode = getBrokerageAgencyCode;




         function getAddDetails(requestData){
                  return $http({
                      method: 'POST',
                      url: 'gbmultra/rbibond/reports/getminorToMajorIntimationRepValues',
                      data :requestData
                  })
         }

function generateBrokerRes(requestData){
                  return $http({
                      method: 'POST',
                      url: 'gbmultra/rbibond/reports/generateBrokerRes',
                      data :requestData
                  })
         }

         function getRbiBondModuleNameDetails(requestData){
                     return $http({
                        method: 'POST',
                        url: 'rbiBond/getModuleDetails',
                        data :requestData
                     })
         }




         function getBrokerageInvoiceBondScheme(requestData){
                           return $http({
                              method: "post",
                              url: "gbmultra/rbibond/reports/getBrokerageInvoiceBondScheme",
                              data : requestData
                          });
                      }


         function getBrokerageAgencyCode(requestData){
                        return	$http({
                            method : "POST",
                            url : "gbmultra/rbibond/reports/getBrokerageAgencyCode",
                            data :requestData
                        });
                     }


}

		})();