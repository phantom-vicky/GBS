(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('rbiBondAckReceiptService', rbiBondAckReceiptService);

    rbiBondAckReceiptService.$inject = ['$http'];

    function rbiBondAckReceiptService($http) {

    	var rbiBondAckReceiptService = this;

    	rbiBondAckReceiptService.getCustomerIdForInquiry = getCustomerIdForInquiry;

	rbiBondAckReceiptService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
    rbiBondAckReceiptService.getBrokerId = getBrokerId;
    rbiBondAckReceiptService.getReferenceData = getReferenceData;
     rbiBondAckReceiptService.getAddDetails = getAddDetails;


        function getRbiBondModuleNameDetails(requestData){
            return $http({
                method: 'POST',
                url: 'rbiBond/getModuleDetails',
                data :requestData
            })
      }

        function getReferenceData(requestData)
                      {
                         return	$http({
                             method : "POST",
                             url : 'rbiBond/getReferenceData',
                             data :requestData
                         });
                      }

        function getBrokerId(requestData)
        {
           return	$http({
               method : "post",
               url : 'gbmultra/rbibond/ackReceipt/addTorch',
               data : requestData
           });
        }

function getAddDetails(requestData)
        {
           return	$http({
               method : "post",
               url : 'gbmultra/rbibond/ackReceipt/add',
               data : requestData
           });
        }


        /* Inquiry Submit */
        function getCustomerIdForInquiry(requestData)
        {
           return	$http({
               method : "post",
               url : 'gbmultra/rbibond/ackReceipt/inquiry',
               data : requestData
           });
        }


    }

})();