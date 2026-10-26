(function() {

	'use strict';

	angular.module('gbmUltraApp').service(
			'tocSharingMaintenanceService',
			tocSharingMaintenanceService);

	tocSharingMaintenanceService.$inject = [ '$http' ];

	function tocSharingMaintenanceService($http) {

		var tocSharingMaintenanceService = this;

            tocSharingMaintenanceService.validateAddSelect = validateAddSelect;
            tocSharingMaintenanceService.submitAddForm = submitAddForm;
            tocSharingMaintenanceService.getInquirySelectDetails = getInquirySelectDetails;
            tocSharingMaintenanceService.VerifyDeleteTocSharing = VerifyDeleteTocSharing;
            tocSharingMaintenanceService.submitDeleteForm = submitDeleteForm;
            tocSharingMaintenanceService.submitVerifyForm = submitVerifyForm;
            tocSharingMaintenanceService.getModifySelectSharing = getModifySelectSharing;
            tocSharingMaintenanceService.submitModifyForm = submitModifyForm;
/*            tocSharingMaintenanceService.getSubCodeList = getSubCodeList;*/



        function validateAddSelect(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/common/tocSharing/addValidation',
                headers: {
                     'Content-Type': 'application/json'
                 },
                data: requestData
           });
        }

        function submitAddForm(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/common/tocSharing/add',
                headers: {
                     'Content-Type': 'application/json'
                 },
                data: requestData
           });
        }
        //Get Inquiry Select Details
        function getInquirySelectDetails(requestData) {
             return $http({
                method: 'POST',
                url: 'gbmultra/common/tocSharing/inquiry',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: requestData
            })
        }

        //Get Verify/Delete Select Details
        function VerifyDeleteTocSharing(requestData) {
             return $http({
                method: 'POST',
                url: 'gbmultra/common/tocSharing/verifyDeleteSelect',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: requestData
            })
        }

        function submitDeleteForm(requestData) {
             return $http({
                  method: 'POST',
                  url: "gbmultra/common/tocSharing/delete",
                  headers: {
                      'Content-Type': 'application/json'
                  },
                  data: requestData
              })
        }

        function submitVerifyForm(requestData) {
             return $http({
                  method: 'POST',
                  url: "gbmultra/common/tocSharing/verify",
                  headers: {
                      'Content-Type': 'application/json'
                  },
                  data: requestData
              })
        }

        function getModifySelectSharing(requestData) {
             return $http({
                  method: 'POST',
                  url: "gbmultra/common/tocSharing/modifySelect",
                  headers: {
                      'Content-Type': 'application/json'
                  },
                  data: requestData
              })
        }

        function submitModifyForm(requestData) {
             return $http({
                  method: 'POST',
                  url: "gbmultra/common/tocSharing/modify",
                  headers: {
                      'Content-Type': 'application/json'
                  },
                  data: requestData
              })
        }

        /*function getSubCodeList(){
        return	$http({
               method : "get",
               url : '/subCodeList',
               params : {}
           });

        }*/


	}

})();