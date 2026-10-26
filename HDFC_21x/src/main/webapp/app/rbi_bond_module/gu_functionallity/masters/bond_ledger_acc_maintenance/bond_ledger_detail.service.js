(function() {

	'use strict';

	angular.module('gbmUltraApp').service(
			'bondLedgerAccMaintenanceService',
			bondLedgerAccMaintenanceService);

	bondLedgerAccMaintenanceService.$inject = [ '$http' ];

	function bondLedgerAccMaintenanceService($http) {
		var bondLedgerAccMaintenanceService = this;
		bondLedgerAccMaintenanceService.getReferenceData = getReferenceData;
		bondLedgerAccMaintenanceService.addSubmit = addSubmit;
		bondLedgerAccMaintenanceService.getTdsApplecableRate = getTdsApplecableRate;
		bondLedgerAccMaintenanceService.fetchCbsCustId = fetchCbsCustId;
		bondLedgerAccMaintenanceService.fetchBlaAccNoDetails = fetchBlaAccNoDetails;
		bondLedgerAccMaintenanceService.fetchGuardianDetails = fetchGuardianDetails;
		bondLedgerAccMaintenanceService.getCommonMetaDatList = getCommonMetaDatList;
		bondLedgerAccMaintenanceService.modifySelect = modifySelect;
		bondLedgerAccMaintenanceService.modifySubmit = modifySubmit;
		bondLedgerAccMaintenanceService.verifySelect = verifySelect;
		bondLedgerAccMaintenanceService.verifySubmit = verifySubmit;
		bondLedgerAccMaintenanceService.deleteSubmit = deleteSubmit;
		bondLedgerAccMaintenanceService.inquirySelect = inquirySelect;
		bondLedgerAccMaintenanceService.accountTypeValid = accountTypeValid;
		bondLedgerAccMaintenanceService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;


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
                function addSubmit(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/bla/add',
                       data :requestData
                   });
                }
                function getTdsApplecableRate(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/bla/getTdsRateCode',
                       data: requestData
                   });
                }
                function fetchCbsCustId(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/bla/getBcifApi',
                       data: requestData
                   });
                }
                function fetchBlaAccNoDetails(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/bla/getBlaNumDetails',
                       data: requestData
                   });
                }
                function fetchGuardianDetails(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/bla/getGuardianDetails',
                       data: requestData
                   });
                }
                function getCommonMetaDatList(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : '/rbiBond/getReferenceData',
                       data: requestData
                   });
                }
                //Modify Select
                function modifySelect(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/bla/modifySelect',
                       data :requestData
                   });
                }
                //Modify submit
                function modifySubmit(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/bla/modify',
                       data :requestData
                   });
                }
                //Verify Select
                function verifySelect(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/bla/verifySelect',
                       data :requestData
                   });
                }
                //Verify submit
                function verifySubmit(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/bla/verify',
                       data :requestData
                   });
                }
                //Delete submit
                function deleteSubmit(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/bla/delete',
                       data :requestData
                   });
                }
                //Inquiry submit
                function inquirySelect(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/bla/inquiry',
                       data :requestData
                   });
                }
                /* getValidations */
                function accountTypeValid() {
                    return $http({
                         method: "POST",
                         url: 'scss/customerMaster/getScssSchemeParameter',
                    });
                }

		}

		})();