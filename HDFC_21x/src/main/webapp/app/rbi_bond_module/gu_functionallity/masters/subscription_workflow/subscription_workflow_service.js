(function() {

	'use strict';

	angular.module('gbmUltraApp').service(
			'rbiBondSubscriptionWorkFlowService',
			rbiBondSubscriptionWorkFlowService);

	rbiBondSubscriptionWorkFlowService.$inject = [ '$http' ];

	function rbiBondSubscriptionWorkFlowService($http) {
		var rbiBondSubscriptionWorkFlowService = this;
		rbiBondSubscriptionWorkFlowService.getReferenceData = getReferenceData;
		rbiBondSubscriptionWorkFlowService.getSolDetails = getSolDetails;
		rbiBondSubscriptionWorkFlowService.getValidationAdd = getValidationAdd;
		rbiBondSubscriptionWorkFlowService.getSubCodeList = getSubCodeList;
		rbiBondSubscriptionWorkFlowService.getDepositAmountValidation = getDepositAmountValidation;
		rbiBondSubscriptionWorkFlowService.getReferenceData = getReferenceData;
		rbiBondSubscriptionWorkFlowService.submitAddForm = submitAddForm;
		rbiBondSubscriptionWorkFlowService.getForacidAccountNumber = getForacidAccountNumber;
		rbiBondSubscriptionWorkFlowService.getCbsDetails = getCbsDetails;
		rbiBondSubscriptionWorkFlowService.getVerifySelect = getVerifySelect;
		rbiBondSubscriptionWorkFlowService.getInquiryDetails = getInquiryDetails;
		rbiBondSubscriptionWorkFlowService.submitVerifyForm = submitVerifyForm;
		rbiBondSubscriptionWorkFlowService.submitDeleteForm = submitDeleteForm;
		rbiBondSubscriptionWorkFlowService.getModifySelect = getModifySelect;
		rbiBondSubscriptionWorkFlowService.getModifySubmit = getModifySubmit;
		rbiBondSubscriptionWorkFlowService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
		rbiBondSubscriptionWorkFlowService.getApplicationTorch = getApplicationTorch;
		rbiBondSubscriptionWorkFlowService.getBrokerTorch = getBrokerTorch;
		rbiBondSubscriptionWorkFlowService.getCommonMetaDatList = getCommonMetaDatList;
		rbiBondSubscriptionWorkFlowService.getOtherBankDetails = getOtherBankDetails;

		        function getRbiBondModuleNameDetails(requestData){
                    return $http({
                        method: 'POST',
                        url: 'rbiBond/getModuleDetails',
                        data :requestData
                    })
              }

                              function getCommonMetaDatList(requestData)
                              {
                                 return	$http({
                                     method : "POST",
                                     url : '/rbiBond/getReferenceData',
                                     data: requestData
                                 });
                              }


		        function getReferenceData(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'rbiBond/getReferenceData',
                       data :requestData
                   });
                }
                function getSolDetails(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'rbiBond/getReferenceData',
                       data :requestData
                   });

                }
                function getValidationAdd(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/subsWrkFlow/addValidation',
                       data :requestData
                   });
                }
                function getApplicationTorch(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/subsWrkFlow/getApplicationDetails',
                       data :requestData
                   });
                }
                function getBrokerTorch(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/subsWrkFlow/getBrokerDetails',
                       data :requestData
                   });
                }
                function getDepositAmountValidation(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'scss/account/opening/addValidation',
                       data :requestData
                   });
                }

        function getSubCodeList(){
        return	$http({
               method : "POST",
               url : 'scss/subCodeList'
           });

        }
        function getReferenceData(requestData)
        {
           return	$http({
               method : "POST",
               url : 'rbiBond/getReferenceData',
               data :requestData
           });
        }

        function submitAddForm(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/rbiBond/subsWrkFlow/add',
               data :requestData
           });
        }

			function getForacidAccountNumber(AccNo,solid){
				return $http({
					method:"post",
					url:"gbmultra/cbs/integration/bacidaccountdetails",
					data:{bacid : AccNo, solId : solid}
				})
			}

        function getCbsDetails(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/rbiBond/subsWrkFlow/getTranValidation',
               data :requestData
           });
        }
                function getOtherBankDetails(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/subsWrkFlow/getOtherBankDetails',
                       data :requestData
                   });
                }
        function getVerifySelect(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/rbiBond/subsWrkFlow/verifySelect',
               data :requestData
           });
        }

        function getInquiryDetails(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/rbiBond/subsWrkFlow/inquiry',
               data :requestData
           });
        }

        function submitVerifyForm(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/rbiBond/subsWrkFlow/verify',
               data :requestData
           });
        }

        function submitDeleteForm(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/rbiBond/subsWrkFlow/delete',
               data :requestData
           });
        }
        function getModifySelect(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/rbiBond/subsWrkFlow/modifySelect',
               data :requestData
           });
        }
        function getModifySubmit(requestData)
        {
           return	$http({
               method : "POST",
               url : 'gbmultra/rbiBond/subsWrkFlow/modify',
               data :requestData
           });
        }


		}

		})();