(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('interestRateCodeMaintenanceforInvestmentSchemesService', interestRateCodeMaintenanceforInvestmentSchemesService);

    interestRateCodeMaintenanceforInvestmentSchemesService.$inject = ['$http'];

    function interestRateCodeMaintenanceforInvestmentSchemesService($http) {

    	var interestRateCodeMaintenanceforInvestmentSchemesService = this;

    	interestRateCodeMaintenanceforInvestmentSchemesService.addInterestRate = addInterestRate;
    	interestRateCodeMaintenanceforInvestmentSchemesService.getModuleDetails = getModuleDetails;
    	interestRateCodeMaintenanceforInvestmentSchemesService.getModifyInterestRateCode = getModifyInterestRateCode;
    	interestRateCodeMaintenanceforInvestmentSchemesService.modifySubmitDetails = modifySubmitDetails;
    	interestRateCodeMaintenanceforInvestmentSchemesService.getInquirySelectDetails = getInquirySelectDetails;
    	interestRateCodeMaintenanceforInvestmentSchemesService.getVerifyDeleteSelectDetails = getVerifyDeleteSelectDetails;
    	interestRateCodeMaintenanceforInvestmentSchemesService.verifySubmitDetails = verifySubmitDetails;
    	interestRateCodeMaintenanceforInvestmentSchemesService.deleteSubmitDetails = deleteSubmitDetails;

        /* Get Module Details */
        function getModuleDetails(requestData)
        {
           return	$http({
               method : "POST",
               url : 'cbdt/getModuleDetails',
               data :requestData
           });
        }
        /* Add Sumbit */
        function addInterestRate(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/common/master/interest/rate/code/add',
               data :requestData
           });
        }
        /* Get Modify Tourch Details */
        function getModifyInterestRateCode(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/common/master/interest/rate/code/getModify',
               data :requestData
           });
        }
        function modifySubmitDetails(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/common/master/interest/rate/code/modify',
               data :requestData
           });
        }
        /* Get Verify/Delete Select Details */
        function getVerifyDeleteSelectDetails(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/common/master/interest/rate/code/getVerifyDeleteDetails',
               data :requestData
           });
        }
        /* Get Inquiry Details */
        function getInquirySelectDetails(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/common/master/interest/rate/code/inquiry',
               data :requestData
           });
        }
        //Verify Submit
        function verifySubmitDetails(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/common/master/interest/rate/code/verify',
               data :requestData
           });
        }
        //Delete Submit
        function deleteSubmitDetails(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/common/master/interest/rate/code/delete',
               data :requestData
           });
        }

    }

})();