(function () {

   'use strict';

   angular
      .module('gbmUltraApp')
      .service('StateDistrictPinService', StateDistrictPinService);

   StateDistrictPinService.$inject = ['$http'];

   function StateDistrictPinService($http) {

      var StateDistrictPinService = this;
      StateDistrictPinService.getRemarksList = getRemarksList;
      StateDistrictPinService.downloadPincode = downloadPincode;

      StateDistrictPinService.save = function (file) {
         var fd = new FormData();
         fd.append('file', file);
         return $http.post('gbmultra/cbs/integration/bulkupload/stCyPin/save', fd, {
            transformRequest: angular.identity,
            transformResponse: angular.identity,
            headers: {
               'Content-Type': undefined,
               'Custom-Content-Type': 'upload'
            }
         });
      }

      StateDistrictPinService.getInquiryDetails = function (requestData) {
         return $http({
            method: "POST",
            url: 'gbmultra/cbs/integration/bulkupload/stCyPin/getInquiryDetails',
            data: requestData
         });
      }


      StateDistrictPinService.getVerifyDetails = function (requestData) {
         return $http({
            method: "POST",
            url: 'gbmultra/cbs/integration/bulkupload/stCyPin/getVerifyDetails',
             data: requestData
         });
      }

      StateDistrictPinService.verifySubmit = function () {
         return $http({
            method: "GET",
            url: 'gbmultra/cbs/integration/bulkupload/stCyPin/verifySubmit'
         });
      }

      StateDistrictPinService.dataExistsCheck = function () {
         return $http({
            method: "GET",
            url: 'gbmultra/cbs/integration/bulkupload/stCyPin/dataExistsCheck'
         });
      }

      StateDistrictPinService.deleteSubmit = function (requestData) {
         return $http({
            method: "POST",
            url: 'gbmultra/cbs/integration/bulkupload/stCyPin/deleteSubmit',
            data: requestData
         });
      }

      function getRemarksList(requestData) {
         return $http({
            method: 'POST',
            url: 'rbiBond/getRemarksList',
            data: requestData
         })
      }
      function downloadPincode() {
         return $http({
            method: 'POST',
            url: 'gbmultra/cbs/integration/bulkupload/stCyPin/downloadPincode',
         })
      }


   }
})();