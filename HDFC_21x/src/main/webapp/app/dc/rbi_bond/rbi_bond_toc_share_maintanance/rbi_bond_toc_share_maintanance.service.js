(function() {

	'use strict';

	angular.module('gbmUltraApp').service(
			'rbibondtocSharingMaintenanceService',
			rbibondtocSharingMaintenanceService);

	rbibondtocSharingMaintenanceService.$inject = [ '$http' ];

	function rbibondtocSharingMaintenanceService($http) {

		var rbibondtocSharingMaintenanceService = this;

            rbibondtocSharingMaintenanceService.validateAddSelect = validateAddSelect;
            rbibondtocSharingMaintenanceService.submitAddForm = submitAddForm;
            rbibondtocSharingMaintenanceService.getInquirySelectDetails = getInquirySelectDetails;
            rbibondtocSharingMaintenanceService.VerifyDeleteTocSharing = VerifyDeleteTocSharing;
            rbibondtocSharingMaintenanceService.submitDeleteForm = submitDeleteForm;
            rbibondtocSharingMaintenanceService.submitVerifyForm = submitVerifyForm;
            rbibondtocSharingMaintenanceService.getModifySelectSharing = getModifySelectSharing;
            rbibondtocSharingMaintenanceService.getRbiBondModuleNameDetails=getRbiBondModuleNameDetails;
            rbibondtocSharingMaintenanceService.submitModifyForm = submitModifyForm;
            rbibondtocSharingMaintenanceService.getRbiBondRoundFactor=getRbiBondRoundFactor;
/*            tocSharingMaintenanceService.getSubCodeList = getSubCodeList;*/



        function validateAddSelect(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/rbiBond/tocSharing/addValidation',
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
               url : 'gbmultra/rbiBond/tocSharing/add',
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
                url: 'gbmultra/rbiBond/tocSharing/inquiry',
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
                url: 'gbmultra/rbiBond/tocSharing/verifyDeleteSelect',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: requestData
            })
        }

        function submitDeleteForm(requestData) {
             return $http({
                  method: 'POST',
                  url: "gbmultra/rbiBond/tocSharing/delete",
                  headers: {
                      'Content-Type': 'application/json'
                  },
                  data: requestData
              })
        }

        function submitVerifyForm(requestData) {
             return $http({
                  method: 'POST',
                  url: "gbmultra/rbiBond/tocSharing/verify",
                  headers: {
                      'Content-Type': 'application/json'
                  },
                  data: requestData
              })
        }

        function getModifySelectSharing(requestData) {
             return $http({
                  method: 'POST',
                  url: "gbmultra/rbiBond/tocSharing/modifySelect",
                  headers: {
                      'Content-Type': 'application/json'
                  },
                  data: requestData
              })
        }

        function submitModifyForm(requestData) {
             return $http({
                  method: 'POST',
                  url: "gbmultra/rbiBond/tocSharing/modify",
                  headers: {
                      'Content-Type': 'application/json'
                  },
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
          function getRbiBondRoundFactor(requestData){
                             return $http({
                                  method:'POST',
                                  url:'rbiBond/getReferenceData',
                                  data:requestData
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