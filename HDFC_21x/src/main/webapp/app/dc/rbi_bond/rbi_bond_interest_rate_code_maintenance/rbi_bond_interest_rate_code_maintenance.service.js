(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('rbiBondInterestRateCodeMaintenanceService', rbiBondInterestRateCodeMaintenanceService);

    rbiBondInterestRateCodeMaintenanceService.$inject = ['$http'];

    function rbiBondInterestRateCodeMaintenanceService($http) {

    	var rbiBondInterestRateCodeMaintenanceService = this;
 rbiBondInterestRateCodeMaintenanceService.getRemarksList = getRemarksList;
 rbiBondInterestRateCodeMaintenanceService.addSubmitForm = addSubmitForm;
 rbiBondInterestRateCodeMaintenanceService.verifySelect=verifySelect;
 rbiBondInterestRateCodeMaintenanceService.verify=verify;
 rbiBondInterestRateCodeMaintenanceService.deleteRecord=deleteRecord;
 rbiBondInterestRateCodeMaintenanceService.modifySelect=modifySelect;
 rbiBondInterestRateCodeMaintenanceService.modifySubmit=modifySubmit;
  rbiBondInterestRateCodeMaintenanceService.inquirySelect=inquirySelect;
//  rbiBondInterestRateCodeMaintenanceService.getRbiBondModuleNameDetails=getRbiBondModuleNameDetails;

 function addSubmitForm(requestData)
 {
        return	$http({
            method : "POST",
            url : 'gbsultra/rbiBond/intRtCdMain/add',
            data :requestData
        });

 }

 function getRemarksList(requestData){
                          return $http({
                               method:'POST',
                               url:'rbiBond/getRemarksList',
                               data:requestData
                          })
             }

function inquirySelect(requestData)
 {
        return	$http({
             method : "POST",
             url : 'gbsultra/rbiBond/intRtCdMain/inquiry',
             data :requestData
         });


 }

///rbiBond/getModuleDetails
function getModuleDetails(requestData)
 {
        return	$http({
            method : "POST",
            url : 'rbiBond/getModuleDetails',
            data :requestData
        });

 }
 //modifySubmit
 function modifySubmit(requestData)
  {
         return	$http({
             method : "POST",
             url : 'gbsultra/rbiBond/intRtCdMain/modify',
             data :requestData
         });

  }
 function verifySelect(requestData)
 {
        return	$http({
            method : "POST",
            url : 'gbsultra/rbiBond/intRtCdMain/verifyDeleteSelect',
            data :requestData
        });

 }
  function verify(requestData)
  {
         return	$http({
             method : "POST",
             url : 'gbsultra/rbiBond/intRtCdMain/verify',
             data :requestData
         });

  }
  function deleteRecord(requestData)
    {
           return	$http({
               method : "POST",
               url : 'gbsultra/rbiBond/intRtCdMain/delete',
               data :requestData
           });

    }
function modifySelect(requestData)
{
///gbsultra/rbiBond/intRtCdMain/modifyDetails
     return	$http({
               method : "POST",
               url : 'gbsultra/rbiBond/intRtCdMain/modifyDetails',
               data :requestData
           });
}




//
//        function getRbiBondModuleNameDetails(requestData){
//            return $http({
//                method: 'POST',
//                url: 'rbiBond/getModuleDetails',
//                data :requestData
//            })
//      }













    }

})();
