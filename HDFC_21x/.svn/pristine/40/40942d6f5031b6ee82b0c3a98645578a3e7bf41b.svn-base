(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('rbiBondSchemeParameterService', rbiBondSchemeParameterService);

    rbiBondSchemeParameterService.$inject = ['$http'];

    function rbiBondSchemeParameterService($http) {

    	var rbiBondSchemeParameterService = this;

    	rbiBondSchemeParameterService.checkRecordExisted = checkRecordExisted;
    	rbiBondSchemeParameterService.addCBDTSchemeParameter = addCBDTSchemeParameter;
    	rbiBondSchemeParameterService.getUnverifiedRecords = getUnverifiedRecords;
    	rbiBondSchemeParameterService.getModifySelect = getModifySelect;
    	rbiBondSchemeParameterService.getRemarksList = getRemarksList;

    	rbiBondSchemeParameterService.modifyCBDTParameterCreation = modifyCBDTParameterCreation;
    	rbiBondSchemeParameterService.verifyCBDTParameterCreation = verifyCBDTParameterCreation;
    	rbiBondSchemeParameterService.deleteCBDTParameterCreation = deleteCBDTParameterCreation;
    	rbiBondSchemeParameterService.inquiryCBDTParameterCreation = inquiryCBDTParameterCreation;
    	rbiBondSchemeParameterService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
    	rbiBondSchemeParameterService.getRbiBondModule = getRbiBondModule;
    	rbiBondSchemeParameterService.getInterestRateCodeTorch = getInterestRateCodeTorch;
    	rbiBondSchemeParameterService.getInquirySelectDetails = getInquirySelectDetails;
 //Get Inquiry Select Details
        function getInquirySelectDetails(requestData) {
             return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/tocSlab/inquiry',
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
        function checkRecordExisted(requestData)
        {
           return	$http({
               method : "post",
               url : 'gbmultra/rbiBond/schemeParameter/isRecordExists',
               data :requestData
           });
        }

        /* Add Submit */
        function addCBDTSchemeParameter(requestData)
        {
           return	$http({
               method : "post",
               url : 'gbmultra/rbiBond/schemeParameter/add',
               data :requestData
           });
        }
        /* Modify Submit */
        function modifyCBDTParameterCreation(requestData)
        {
           return	$http({
               method : "post",
               url : 'gbmultra/rbiBond/schemeParameter/modify',
               data :requestData
           });
        }

        /* Verify Submit */
        function verifyCBDTParameterCreation(requestData)
        {
           return	$http({
               method : "post",
               url : 'gbmultra/rbiBond/schemeParameter/verify',
               data : requestData
           });
        }

        /* Delete Submit */
        function deleteCBDTParameterCreation(requestData)
        {
           return	$http({
               method : "post",
               url : 'gbmultra/rbiBond/schemeParameter/delete',
               data : requestData
           });
        }

        /* Inquiry Submit */
        function inquiryCBDTParameterCreation(requestData)
        {
           return	$http({
               method : "post",
               url : 'gbmultra/rbiBond/schemeParameter/inquiry',
               data : requestData
           });
        }

        /* Select Modify / inquiry*/
        function getModifySelect(requestData)
        {
           return	$http({
           method : "post",
           url : 'gbmultra/rbiBond/schemeParameter/modifyDetails',
           data : requestData
           });
        }


        function getUnverifiedRecords(requestData){
            return	$http({
               method : "post",
               url : 'gbmultra/rbiBond/schemeParameter/verifyDeleteSelect',
               data : requestData
            });
        }


        function getRbiBondModuleNameDetails(requestData){
            return $http({
                method: 'POST',
                url: 'rbiBond/getModuleDetails',
                data :requestData
            })
      }


        function getRbiBondModule(requestData){
            return $http({
                method: 'POST',
                url: 'rbiBond/getModule',
                data :requestData
            })
      }


              function getInterestRateCodeTorch(requestData){
                  return $http({
                      method: 'POST',
                      url: 'gbmultra/rbiBond/schemeParameter/interestRtCdTorch',
                      data :requestData
                  })
            }



    }

})();
