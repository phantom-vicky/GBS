(function() {

	'use strict';

	angular.module('gbmUltraApp').service(
			'commonTocSlabMaintenanceService',
			commonTocSlabMaintenanceService);

	commonTocSlabMaintenanceService.$inject = [ '$http' ];

	function commonTocSlabMaintenanceService($http) {

		var commonTocSlabMaintenanceService = this;

		commonTocSlabMaintenanceService.getVersionNum = getVersionNum;
		commonTocSlabMaintenanceService.modifyVerifyTocSlab = modifyVerifyTocSlab;
		commonTocSlabMaintenanceService.deleteTocSlab = deleteTocSlab;
		commonTocSlabMaintenanceService.inquiryTocSlab = inquiryTocSlab;
		commonTocSlabMaintenanceService.submitAddForm = submitAddForm;
		commonTocSlabMaintenanceService.submitModifyForm = submitModifyForm;
		commonTocSlabMaintenanceService.submitVerifyForm = submitVerifyForm;
		commonTocSlabMaintenanceService.submitDeleteForm = submitDeleteForm;


		commonTocSlabMaintenanceService.checkRecordExisted = checkRecordExisted;
		commonTocSlabMaintenanceService.VerifyDeleteTocSlab = VerifyDeleteTocSlab;
		commonTocSlabMaintenanceService.getInquirySelectDetails = getInquirySelectDetails;
		commonTocSlabMaintenanceService.modifySelect = modifySelect;


		function getVersionNum(moduleId, treasuryCode) {
		     return $http({
                method: 'get',
                url: 'gbmultra/dc/pension/tocslabdetails/slabdetails?moduleId='+moduleId+'&trsyCode='+treasuryCode,
                headers: {
                    'Content-Type': 'application/json'
                },
            })
		}
		function modifyVerifyTocSlab(moduleId, treasuryCode) {
		     return $http({
                method: 'get',
                url: 'gbmultra/dc/pension/tocslabdetails/modifyslabdetails?moduleId='+moduleId+'&trsyCode='+treasuryCode,
                headers: {
                    'Content-Type': 'application/json'
                },
            })
		}
		function deleteTocSlab(moduleId, treasuryCode) {
		     return $http({
                method: 'get',
                url: 'gbmultra/dc/pension/tocslabdetails/deleteslabdetails?moduleId='+moduleId+'&trsyCode='+treasuryCode,
                headers: {
                    'Content-Type': 'application/json'
                },
            })
		}
		function inquiryTocSlab(moduleId, treasuryCode, versionNo, effFromDate) {
		     return $http({
                method: 'get',
                url: 'gbmultra/dc/pension/tocslabdetails/inquiryslabdetails?moduleId='+moduleId+'&trsyCode='+treasuryCode+'&versionNo='+versionNo+'&effeFromDate='+effFromDate,
                headers: {
                    'Content-Type': 'application/json'
                },
            })
		}

		//New
		function checkRecordExisted(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/common/tocSlab/getAddDetails',
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
                  url: "gbmultra/common/tocSlab/save",
                  headers: {
                      'Content-Type': 'application/json'
                  },
                  data: requestData
              })
        }
        //Get Modify Select Details
        function modifySelect(requestData) {
             return $http({
                method: 'POST',
                url: 'gbmultra/common/tocSlab/selectModifySlab',
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
                  url: "gbmultra/common/tocSlab/modify",
                  headers: {
                      'Content-Type': 'application/json'
                  },
                  data: requestData
              })
        }
        //Get Verify/Delete Select Details
        function VerifyDeleteTocSlab(requestData) {
             return $http({
                method: 'POST',
                url: 'gbmultra/common/tocSlab/selectVerifySlab',
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
                  url: "gbmultra/common/tocSlab/verify",
                  headers: {
                      'Content-Type': 'application/json'
                  },
                  data: requestData
              })
        }
        function submitDeleteForm(requestData) {
             return $http({
                  method: 'POST',
                  url: "gbmultra/common/tocSlab/delete",
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
                url: 'gbmultra/common/tocSlab/inquiry',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: requestData
            })
        }

	}

})();