(function() {

	'use strict';

	angular.module('gbmUltraApp').service(
			'bondTransferInService',
			bondTransferInService);

	bondTransferInService.$inject = [ '$http' ];

	function bondTransferInService($http) {
		var bondTransferInService = this;
		bondTransferInService.getTorchDetails = getTorchDetails;
		bondTransferInService.addValResponse = addValResponse;
		bondTransferInService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
		bondTransferInService.verifySelect = verifySelect;
		bondTransferInService.deleteDetails = deleteDetails;
		bondTransferInService.verifyDetails = verifyDetails;
		bondTransferInService.modifyDetails = modifyDetails;
		bondTransferInService.otherBankDetails = otherBankDetails;
		bondTransferInService.bondTransferInAdd = bondTransferInAdd;
		bondTransferInService.getverifyTorchDetails = getverifyTorchDetails;
		bondTransferInService.verifySubmit = verifySubmit;
        bondTransferInService.getInquiryTorchDetails= getInquiryTorchDetails;
        bondTransferInService.modifySelect = modifySelect;
        bondTransferInService.modifySubmit = modifySubmit;
        bondTransferInService.fetchCbsCustId = fetchCbsCustId;
        bondTransferInService.getReferenceData=getReferenceData;
        bondTransferInService.fetchGuardianDetails = fetchGuardianDetails;
        bondTransferInService.getMetaData=getMetaData;
        bondTransferInService.getWboBranchDetails=getWboBranchDetails;
        bondTransferInService.panTorch=panTorch;
        bondTransferInService.getbankName=getbankName;
        bondTransferInService.getsubscriptionIdInq=getsubscriptionIdInq;
        bondTransferInService.getblaNoInq=getblaNoInq;
        bondTransferInService.fetchState=fetchState;
        bondTransferInService.getNonHdfcTorch=getNonHdfcTorch;
        bondTransferInService.getNonHdfcBranchTorch=getNonHdfcBranchTorch;
        bondTransferInService.validatePan=validatePan;
        bondTransferInService.modifyPan = modifyPan;
        bondTransferInService.getRemarksList = getRemarksList;
 bondTransferInService.getFtsFileService = function (ftsDetailFetch) {
      return $http({
        method: "POST",
        url: "external-service/fts-hcp-integrated-service?applicationNumber="+ftsDetailFetch
        });
    };
             function validatePan(requestData) {
                  return $http({
                    method: "POST",
                    url: "gbmultra/rbiBond/bondTransferIn/validatePan                                                                                                                                                                  ",
                    data: requestData,
                  });
                }
                //getbankName submit
                        function getbankName(requestData) {
                          return $http({
                            method: "POST",
                            url: "gbmultra/rbiBond/blaCumSub/getbankName",
                            data: requestData,
                          });
                        }
		function getRbiBondModuleNameDetails(requestData){
                            return $http({
                                method: 'POST',
                                url: 'rbiBond/getModuleDetails',
                                data :requestData
                            })
                      }
                      function getMetaData(requestData){
                                           return $http({
                                               method: 'POST',
                                               url: 'rbiBond/getReferenceData',
                                               data :requestData
                                           })
                                     }
function getWboBranchDetails(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/blaCumSub/getWboBranchDetails',
                       data :requestData
                   });
                }

    function otherBankDetails(){
     return $http ({
        method: 'POST',
                        url: 'gbmultra/rbiBond/bondTransferIn/otherBankDetails',
//                        data :.
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

function bondTransferInAdd(requestData){
 return $http ({
    method: 'POST',
                    url: 'gbmultra/rbiBond/bondTransferIn/bondTransferInAdd',
                    data :requestData
    })

}
    function addValResponse(requestData) {
    return $http ({
    method: 'POST',
                    url: 'gbmultra/rbiBond/bondTransferIn/addTorch',
                    data :requestData
    })

    }



        function getTorchDetails(requestData){
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/bondTransferIn/addTorch',
                data :requestData
            })
           }

  function verifySubmit(requestData){
        return $http({
        method: 'POST',
        url: 'gbmultra/rbiBond/bondTransferIn/verify',
        data :requestData
        })
        }

  function modifySubmit(requestData){
        return $http({
        method: 'POST',
        url: 'gbmultra/rbiBond/bondTransferIn/modify',
        data :requestData
        })
        }


    function deleteDetails(requestData){
          return $http({
          method: 'POST',
          url: 'gbmultra/rbiBond/bondTransferIn/delete',
          data :requestData
          })
          }

  function getverifyTorchDetails(requestData){
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/bondTransferIn/verifyTorch',
                data :requestData
            })
           }

function fetchGuardianDetails(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/blaCumSub/getGuardianDetails',
                       data: requestData
                   });
                }
  function modifySelect(requestData){
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/bondTransferIn/modifySelect',
                data :requestData
            })
           }
           function modifyPan(requestData){
                       return $http({
                           method: 'POST',
                           url: 'gbmultra/rbiBond/bondTransferIn/modifyPAN',
                           data :requestData
                       })
                      }
            function verifySelect(requestData){
                  return $http({
                      method: 'POST',
                      url: 'gbmultra/rbiBond/bondTransferIn/verifySelect',
                      data :requestData
                  })
                 }


        function getInquiryTorchDetails(requestData){
            return $http({
                method: 'POST',
                url: 'gbmultra/rbiBond/bondTransferIn/inquiry',
                data :requestData
            })
           }


           function fetchCbsCustId(accountNumber) {
                             return $http({
                                 method: "POST",
                                 url: `external-service/api/account-validation?accountNumber=${accountNumber}`,  // Pass accountNumber as a query parameter
                                 data: {}
                             });
                         }


  function panTorch(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/bondTransferIn/panTorch',
                       data :requestData
                   });
                }
    function getblaNoInq(requestData)
                                {
                                   return	$http({
                                       method : "POST",
                                       url : 'gbmultra/rbiBond/bondTransferIn/getblaNoInq',
                                       data :requestData
                                   });
                                }
       function getsubscriptionIdInq(requestData)
                  {
                     return	$http({
                         method : "POST",
                         url : 'gbmultra/rbiBond/bondTransferIn/getsubscriptionIdInq',
                         data :requestData
                     });
                  }


           function verifyDetails(requestData){
                      return $http({
                          method: 'POST',
                          url: 'gbmultra/rbiBond/bondNomination/verify',
                          data :requestData
                       })
                     }



            function modifyDetails(requestData){
                       return $http({
                           method: 'POST',
                           url: 'gbmultra/rbiBond/bondNomination/addModify',
                           data :requestData
                       })
                      }
             function fetchState(requestData) {
                  return $http({
                    method: "POST",
                    url: "gbmultra/rbiBond/blaCumSub/fetchState",
                    data: requestData,
                  });
                }


    function getNonHdfcTorch(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/brokerMaster/getNonHdfcTorch",
        headers: {
          "Content-Type": "application/json",
        },
        data: requestData,
      });
    }
    function getNonHdfcBranchTorch(requestData) {
      return $http({
        method: "POST",
        url: "gbmultra/rbiBond/blaCumSub/getNonHdfcBranchTorch",
        headers: {
          "Content-Type": "application/json",
        },
        data: requestData,
      });
    }


       function getRemarksList(requestData) {
          return $http({
            method: "POST",
            url: "rbiBond/getRemarksList",
            data: requestData,
          });
        };




		}

		})();