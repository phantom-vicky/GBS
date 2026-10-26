(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('rbibondHUFDeathMarkingMenuService', rbibondHUFDeathMarkingMenuService);

    rbibondHUFDeathMarkingMenuService.$inject = ['$http'];

    function rbibondHUFDeathMarkingMenuService($http) {

    	var rbibondHUFDeathMarkingMenuService = this;



 rbibondHUFDeathMarkingMenuService.getInquiry = getInquiry;
 rbibondHUFDeathMarkingMenuService.getVerify = getVerify;
 rbibondHUFDeathMarkingMenuService.getVerifySubmit = getVerifySubmit;
 rbibondHUFDeathMarkingMenuService.getDeleteSubmit = getDeleteSubmit;
 rbibondHUFDeathMarkingMenuService.getRemarksList = getRemarksList;

  rbibondHUFDeathMarkingMenuService.getCoParcenerDtls = getCoParcenerDtls;
   rbibondHUFDeathMarkingMenuService.getAddPanNumberDtls = getAddPanNumberDtls;
  rbibondHUFDeathMarkingMenuService.getVerDelPanNumberDtls=getVerDelPanNumberDtls;
  rbibondHUFDeathMarkingMenuService.getInqPanNumberDtls=getInqPanNumberDtls;
  rbibondHUFDeathMarkingMenuService.getAdd = getAdd;
  rbibondHUFDeathMarkingMenuService.getAddSubmit = getAddSubmit;




        function getAddPanNumberDtls(requestData) {
        console.log(requestData);
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/hufDeathMarking/addSelect',
                data: requestData
            })
        }


         function getCoParcenerDtls(requestData) {
                console.log(requestData);
                    return $http({
                        method: 'POST',
                        url: 'gbmultra/rbibond/hufDeathMarking/coParcenrSelect',
                        data: requestData
                    })
                }

  function getVerDelPanNumberDtls(requestData) {
                    return $http({
                        method: 'POST',
                        url: 'gbmultra/rbibond/hufDeathMarking/verDelSelect',
                        data: requestData
                    })
                }
function getAddSubmit(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/hufDeathMarking/add',
                data: requestData
            })
        }

function getVerify(requestData) {
                    return $http({
                        method: 'POST',
                        url: 'gbmultra/rbibond/hufDeathMarking/verifyDetails',
                        data: requestData
                    })
                }




            function getVerifySubmit(requestData) {
                  return $http({
                                    method: 'POST',
                                    url: 'gbmultra/rbibond/hufDeathMarking/verify',
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
 function getDeleteSubmit(requestData) {
                              return $http({
                                  method: 'POST',
                                  url: 'gbmultra/rbibond/hufDeathMarking/delete',
                                  data: requestData
                              })
                          }

         function getInqPanNumberDtls(requestData) {
                                     return $http({
                                         method: 'POST',
                                         url: 'gbmultra/rbibond/hufDeathMarking/inquirySelect',
                                         data: requestData
                                     })
                                 }
     function getInquiry(requestData) {
                     return $http({
                         method: 'POST',
                         url: 'gbmultra/rbibond/hufDeathMarking/inquiryDetails',
                         data: requestData
                     })
                 }
















    function getAdd(requestData) {
                        return $http({
                            method: 'POST',
                            url: 'gbmultra/rbibond/hufDeathMarking/addDetails',
                            data: requestData
                        })
                    }

















}
})();