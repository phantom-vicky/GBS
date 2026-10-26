(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('panValidationRetriggerService', panValidationRetriggerService);

    panValidationRetriggerService.$inject = ['$http'];

    function panValidationRetriggerService($http) {

    	var panValidationRetriggerService = this;

 panValidationRetriggerService.getDetails = getDetails;
 panValidationRetriggerService.getReferenceData = getReferenceData;
 panValidationRetriggerService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
 panValidationRetriggerService.submitVerifyForm = submitVerifyForm;
 panValidationRetriggerService.submitValidateForm = submitValidateForm;

 function getRbiBondModuleNameDetails(requestData){
             return $http({
                 method: 'POST',
                 url: 'gbmultra/rbiBond/panValidation/retrigger/details',
                 data :requestData
             })
       }

       function submitVerifyForm(requestData){
                    return $http({
                        method: 'POST',
                        url: 'gbmultra/rbiBond/panValidation/retrigger/verify',
                        data :requestData
                    })
              }
        function getDetails(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/panValidation/retrigger/details',
                data: requestData
            })
        }
         function submitValidateForm(requestData) {
                    return $http({
                        method: 'POST',
                        url: 'gbmultra/rbiBond/panValidation/retrigger/validate',
                        data: requestData
                    })
                }
        function getReferenceData(requestData){
           return	$http({
               method : "POST",
               url : 'rbiBond/getReferenceData',
               data :requestData
           });
        }
    }

})();