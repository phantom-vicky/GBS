(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('rbibondBLADeathMarkingService', rbibondBLADeathMarkingService);

    rbibondBLADeathMarkingService.$inject = ['$http'];

    function rbibondBLADeathMarkingService($http) {

    	var rbibondBLADeathMarkingService = this;


 //rbibondBLADeathMarkingService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;

 rbibondBLADeathMarkingService.getInquiry = getInquiry;
 rbibondBLADeathMarkingService.getVerify = getVerify;
 rbibondBLADeathMarkingService.getVerifySubmit = getVerifySubmit;
 rbibondBLADeathMarkingService.getDeleteSubmit = getDeleteSubmit;
 rbibondBLADeathMarkingService.getRemarksList = getRemarksList;

  rbibondBLADeathMarkingService.getAddPanNumberDtls = getAddPanNumberDtls;
  rbibondBLADeathMarkingService.getVerDelPanNumberDtls=getVerDelPanNumberDtls;
  rbibondBLADeathMarkingService.getModPanNumberDtls=getModPanNumberDtls;
  rbibondBLADeathMarkingService.getInqPanNumberDtls=getInqPanNumberDtls;
  rbibondBLADeathMarkingService.getVersionNoDetails = getVersionNoDetails;
  rbibondBLADeathMarkingService.getAdd = getAdd;
  rbibondBLADeathMarkingService.getModify = getModify;
  rbibondBLADeathMarkingService.getAddSubmit = getAddSubmit;
  rbibondBLADeathMarkingService.getFinancialYear=getFinancialYear;
  rbibondBLADeathMarkingService.getcertAgeValidation = getcertAgeValidation;
  rbibondBLADeathMarkingService.getcertSeqNumber = getcertSeqNumber;
  rbibondBLADeathMarkingService.getModifySubmit=getModifySubmit;


// function getRbiBondModuleNameDetails(requestData){
//             return $http({
//                 method: 'POST',
//                 url: 'rbiBond/getModuleDetails',
//                 data :requestData
//             })
//       }


        function getAddPanNumberDtls(requestData) {
        console.log(requestData);
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/blaDeathMarking/addSelect',
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

          function getVerDelPanNumberDtls(requestData) {
                    return $http({
                        method: 'POST',
                        url: 'gbmultra/rbibond/blaDeathMarking/verDelSelect',
                        data: requestData
                    })
                }
         function getModPanNumberDtls(requestData) {
                             return $http({
                                 method: 'POST',
                                 url: 'gbmultra/rbibond/blaDeathMarking/modifySelect',
                                 data: requestData
                             })
                         }

         function getInqPanNumberDtls(requestData) {
                                     return $http({
                                         method: 'POST',
                                         url: 'gbmultra/rbibond/blaDeathMarking/inquirySelect',
                                         data: requestData
                                     })
                                 }

         function getVersionNoDetails(requestData) {
                    return $http({
                        method: 'POST',
                        url: 'gbmultra/rbibond/blaDeathMarking/getVersionNoDetails',
                        data: requestData
                    })
                }


        function getPanDetails(requestData) {
                    return $http({
                        method: 'POST',
                        url: 'gbmultra/rbibond/blaDeathMarking/getPanNoDetails',
                        data: requestData
                    })
                }

function getPanNumberDtls(requestData)
        {
           return	$http({
           method : "POST",
           url : 'gbmultra/rbibond/blaDeathMarking/getPanNoDetails',
           data :requestData
           })
        }


function getAddSubmit(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/blaDeathMarking/add',
                data: requestData
            })
        }

 function getModifySubmit(requestData) {
             return $http({
                 method: 'POST',
                 url: 'gbmultra/rbibond/blaDeathMarking/modify',
                 data: requestData
             })
         }

    function getAdd(requestData) {
                        return $http({
                            method: 'POST',
                            url: 'gbmultra/rbibond/blaDeathMarking/addDetails',
                            data: requestData
                        })
                    }

     function getModify(requestData) {
                             return $http({
                                 method: 'POST',
                                 url: 'gbmultra/rbibond/blaDeathMarking/modifyDetails',
                                 data: requestData
                             })
                         }

    function getFinancialYear(){
            	return $http({
                    method: "post",
                    url: 'gbmultra/rbibond/blaDeathMarking/financialyear'
                });
            }

     function getcertAgeValidation(requestData) {
                             return $http({
                                 method: 'POST',
                                 url: 'gbmultra/rbibond/blaDeathMarking/ageValidation',
                                 data: requestData
                             })
                         }
      function getcertSeqNumber(requestData) {
                                  return $http({
                                      method: 'POST',
                                      url: 'gbmultra/rbibond/blaDeathMarking/getCertNumber',
                                      data: requestData
                                  })
                              }

     function getVerify(requestData) {
                    return $http({
                        method: 'POST',
                        url: 'gbmultra/rbibond/blaDeathMarking/verifyDetails',
                        data: requestData
                    })
                }


     function getVerifySubmit(requestData) {
           return $http({
                             method: 'POST',
                             url: 'gbmultra/rbibond/blaDeathMarking/verify',
                             data: requestData
                         })
               }


     function getDeleteSubmit(requestData) {
                              return $http({
                                  method: 'POST',
                                  url: 'gbmultra/rbibond/blaDeathMarking/delete',
                                  data: requestData
                              })
                          }
      function getInquiry(requestData) {
                     return $http({
                         method: 'POST',
                         url: 'gbmultra/rbibond/blaDeathMarking/inquiryDetails',
                         data: requestData
                     })
                 }



//
//                  function getRbiBondModuleNameDetails(requestData){
//                                     return $http({
//                                         method: 'POST',
//                                         url: 'gbmultra/rbiBond/blaCumSub/getModuleDetails',
//                                         data :requestData
//                                     })
//                               }
}
})();