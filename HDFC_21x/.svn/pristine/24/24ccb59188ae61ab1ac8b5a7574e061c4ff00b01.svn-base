(function() {

	'use strict';

	angular.module('gbmUltraApp').service(
			'rbiBondAccTransferOutService',
			rbiBondAccTransferOutService);

	rbiBondAccTransferOutService.$inject = [ '$http' ];

	function rbiBondAccTransferOutService($http) {
		var rbiBondAccTransferOutService = this;


		rbiBondAccTransferOutService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
		rbiBondAccTransferOutService.getReferenceData = getReferenceData;
		rbiBondAccTransferOutService.getValidationAdd = getValidationAdd;
		rbiBondAccTransferOutService.getNomineeDetails = getNomineeDetails;
		rbiBondAccTransferOutService.getBankDetails = getBankDetails;
		rbiBondAccTransferOutService.transactionValidation = transactionValidation;
		rbiBondAccTransferOutService.addSubmit = addSubmit;
		rbiBondAccTransferOutService.inquiry = inquiry;
		rbiBondAccTransferOutService.verifySelect = verifySelect;
		rbiBondAccTransferOutService.deleteSubmit = deleteSubmit;
		rbiBondAccTransferOutService.verifySubmit = verifySubmit;
		rbiBondAccTransferOutService.modifySelect = modifySelect;
		rbiBondAccTransferOutService.modifySubmit = modifySubmit;
		rbiBondAccTransferOutService.getTorchDetails = getTorchDetails;
		rbiBondAccTransferOutService.getCbsDetails=getCbsDetails;
		rbiBondAccTransferOutService.getRemarksList = getRemarksList;

//Hari//
		rbiBondAccTransferOutService.addValidation=addValidation;
		rbiBondAccTransferOutService.getPanTorchDetails=getPanTorchDetails;


		function addValidation(requestData){
                                              return $http({
                                                 method: "post",
                                                 url: "gbmultra/rbiBond/accTranFrOut/addValidation",
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

        function getPanTorchDetails(requestData){
                                                return $http({
                                                method: "post",
                                                url: "gbmultra/rbiBond/ledgerKarta/getTorchPan",
                                                data : requestData
                                                });
                                                }

        function getTorchDetails(requestData){
                                              return $http({
                                                 method: "post",
                                                 url: "gbmultra/rbiBond/accTranFrOut/inquiry",
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




		        function getReferenceData(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'rbiBond/getReferenceData',
                       data :requestData
                   });
                }

                //Add Input Submit//
              function getValidationAdd(requestData)
                {
                  // Return a mock promise with duplicate data
                      return {
                          then: function (callback) {
                              var mockResponse = {
                                  data: {
                                      result: {
                                          bondHolderDetails: [
                                              {
                                                  nameOfTheBondHolder: "John Doe",
                                                  blaNo: "BLA12345",
                                                  isNri: "N",
                                                  jointHolderType: "Primary"
                                              },
                                              {
                                                  nameOfTheBondHolder: "John Doe", // Duplicate record for testing
                                                  blaNo: "BLA12345",
                                                  isNri: "N",
                                                  jointHolderType: "Primary"
                                              }
                                          ],
                                          bondSubscriptionDetails: [
                                              {
                                                  subscriptionDate: "2024-11-10",
                                                  subscriptionId: "SUB001",
                                                  subscriptionAmount: "10000",
                                                  bondType: "Type A",
                                                  maturityDate: "2025-11-10",
                                                  rateOfInterest: "5.5",
                                                  interestPaidUpTo: "2024-10-01",
                                                  transferAmount: "9000",
                                                  isSelected: true
                                              }
                                          ],
                                          bondNomineeDetails: [
                                              {
                                                  subscriptionId: "SUB001",
                                                  nomineeName: "Alice Johnson",
                                                  nomineeShare: "50"
                                              },
                                              {
                                                  subscriptionId: "SUB001", // Duplicate nominee for testing
                                                  nomineeName: "Alice Johnson",
                                                  nomineeShare: "50"
                                              }
                                          ]
                                      }
                                  }
                              };
                              callback(mockResponse);
                          }
                      };


                }
              function getNomineeDetails(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/accTranFrOut/getNomineeDetails',
                       data :requestData
                   });
                }
              function getBankDetails(requestData){
                           return	$http({
                               method : "POST",
                               url : 'gbmultra/rbiBond/accTranFrOut/getOtherBankDetails',
                               data :requestData
                           });
                        }
               function addSubmit(requestData){
                       return	$http({
                           method : "POST",
                           url : 'gbmultra/rbiBond/accTranFrOut/add',
                           data :requestData
                       });
                    }


               function transactionValidation(requestData){
                       return	$http({
                           method : "POST",
                           url : 'gbmultra/rbiBond/accTranFrOut/tranValidation',
                           data :requestData
                       });
                    }
           function inquiry(requestData){
               return	$http({
                   method : "POST",
                   url : 'gbmultra/rbiBond/accTranFrOut/inquiry',
                   data :requestData
               });
            }

          function verifySelect(requestData){
               return	$http({
                   method : "POST",
                   url : 'gbmultra/rbiBond/accTranFrOut/verifySelect',
                   data :requestData
               });
            }

              function getCbsDetails(requestData)
                    {
                       return	$http({
                           method : "POST",
                           url : 'gbmultra/rbiBond/subsWrkFlow/getTranValidation',
                           data :requestData
                       });
                    }

          function deleteSubmit(requestData){
               return	$http({
                   method : "POST",
                   url : 'gbmultra/rbiBond/accTranFrOut/delete',
                   data :requestData
               });
            }

         function verifySubmit(requestData){
           return	$http({
               method : "POST",
               url : 'gbmultra/rbiBond/accTranFrOut/verify',
               data :requestData
           });
        }

                 function modifySelect(requestData){
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/accTranFrOut/modifySelect',
                       data :requestData
                   });
                }
                   function modifySubmit(requestData){
                                   return	$http({
                                       method : "POST",
                                       url : 'gbmultra/rbiBond/accTranFrOut/modify',
                                       data :requestData
                                   });
                                }


		}

		})();