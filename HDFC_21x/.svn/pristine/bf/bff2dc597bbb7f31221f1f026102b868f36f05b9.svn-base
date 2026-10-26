(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('rbiBondAgeBasedService', rbiBondAgeBasedService);

    rbiBondAgeBasedService.$inject = ['$http'];

    function rbiBondAgeBasedService($http) {

    	var rbiBondAgeBasedService = this;

    	rbiBondAgeBasedService.checkRecordExisted = checkRecordExisted;
    	rbiBondAgeBasedService.addAgeBasedCreation = addAgeBasedCreation;
    	rbiBondAgeBasedService.getUnverifiedRecords = getUnverifiedRecords;
    	rbiBondAgeBasedService.getModifySelect = getModifySelect;
    	rbiBondAgeBasedService.getRemarksList = getRemarksList;

    	rbiBondAgeBasedService.modifyAgeBasedCreation = modifyAgeBasedCreation;
    	rbiBondAgeBasedService.verifyAgeBasedCreation = verifyAgeBasedCreation;
    	rbiBondAgeBasedService.deleteAgeBasedCreation = deleteAgeBasedCreation;
    	rbiBondAgeBasedService.inquiryAfeBasedLockInPeriod = inquiryAfeBasedLockInPeriod;
//    	rbiBondAgeBasedService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
//    	rbiBondAgeBasedService.getInterestRateCodeTorch = getInterestRateCodeTorch;


        function checkRecordExisted(requestData)
        {
           return	$http({
               method : "post",
               url : 'gbmultra/rbiBond/ageBasedEncasement/addValidation',
               data :requestData
           });
        }

        /* Add Submit */
        function addAgeBasedCreation(requestData)
        {
           return	$http({
               method : "post",
               url : 'gbmultra/rbiBond/ageBasedEncasement/add',
               data :requestData
           });
        }
        /* Modify Submit */
        function modifyAgeBasedCreation(requestData)
        {
           return	$http({
               method : "post",
               url : 'gbmultra/rbiBond/ageBasedEncasement/modify',
               data :requestData
           });
        }

        /* Verify Submit */
        function verifyAgeBasedCreation(requestData)
        {
           return	$http({
               method : "post",
               url : 'gbmultra/rbiBond/ageBasedEncasement/verify',
               data : requestData
           });
        }

        /* Delete Submit */
        function deleteAgeBasedCreation(requestData)
        {
           return	$http({
               method : "post",
               url : 'gbmultra/rbiBond/ageBasedEncasement/delete',
               data : requestData
           });
        }

        /* Inquiry Submit */
        function inquiryAfeBasedLockInPeriod(requestData)
        {
           return	$http({
               method : "post",
               url : 'gbmultra/rbiBond/ageBasedEncasement/inquiry',
               data : requestData
           });
        }

        /* Select Modify / inquiry*/
        function getModifySelect(requestData)
        {
           return	$http({
           method : "post",
           url : 'gbmultra/rbiBond/ageBasedEncasement/modifySelect',
           data : requestData
           });
        }


        function getUnverifiedRecords(requestData){
            return	$http({
               method : "post",
               url : 'gbmultra/rbiBond/ageBasedEncasement/verifySelect',
               data : requestData
            });
        }

          function getRemarksList(requestData){
                                 return $http({
                                      method:'POST',
                                      url:'rbiBond/getRemarksList',
                                      data:requestData
                                 })
                    }

//
//        function getRbiBondModuleNameDetails(requestData){
//            return $http({
//                method: 'POST',
//                url: 'rbiBond/getModuleDetails',
//                data :requestData
//            })
//      }


//              function getInterestRateCodeTorch(requestData){
//                  return $http({
//                      method: 'POST',
//                      url: 'gbmultra/rbiBond/schemeParameter/interestRtCdTorch',
//                      data :requestData
//                  })
//            }



    }

})();
