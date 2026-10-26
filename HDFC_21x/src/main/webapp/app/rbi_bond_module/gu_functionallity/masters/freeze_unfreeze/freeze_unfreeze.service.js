(function () {
  "use strict";
  angular.module("gbmUltraApp").service("freezeUnfreezeService", freezeUnfreezeService);

  freezeUnfreezeService.$inject = ["$http"];

    function freezeUnfreezeService($http) {
        var freezeUnfreezeService = this;
        freezeUnfreezeService.getValidationAdd = getValidationAdd;
        freezeUnfreezeService.modifyDetails = modifyDetails;
        freezeUnfreezeService.verifyDetails = verifyDetails;
        freezeUnfreezeService.inquiryDetails = inquiryDetails;
        freezeUnfreezeService.getRemarksList = getRemarksList;

        function getValidationAdd(requestData) {
           return	$http({
               method : "POST",
               url : 'gbsultra/rbiBond/freezeUnfreeze/getAddDetails',
               data :requestData
           });
        }
        function modifyDetails(requestData) {
           return	$http({
               method : "POST",
               url : 'gbsultra/rbiBond/freezeUnfreeze/modifySelect',
               data :requestData
           });
        }
        function verifyDetails(requestData) {
           return	$http({
               method : "POST",
               url : 'gbsultra/rbiBond/freezeUnfreeze/verify',
               data :requestData
           });
        }
        function getRemarksList(requestData) {
           return $http({
                method:'POST',
                url:'rbiBond/getRemarksList',
                data:requestData
           });
        }
        function inquiryDetails(requestData) {
           return	$http({
               method : "POST",
               url : 'gbsultra/rbiBond/freezeUnfreeze/inquiry',
               data :requestData
           });
        }
  }
})();
