(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('commonLevelMasterService', commonLevelMasterService);

    commonLevelMasterService.$inject = ['$http'];

    function commonLevelMasterService($http) {

    var commonLevelMasterService = this;


    commonLevelMasterService.addLevelMaster = addLevelMaster;
    commonLevelMasterService.verifyDetailsSubmit = verifyDetailsSubmit;

    commonLevelMasterService.getModuleDetails  =   getModuleDetails;
    commonLevelMasterService.getUnVerifyRecords  =   getUnVerifyRecords;
    commonLevelMasterService.getInquiryRecords  =   getInquiryRecords;
    commonLevelMasterService.deleteDetailsSubmit  =   deleteDetailsSubmit;

    /* Get Module Details */
    function getModuleDetails(requestData)
    {
       return	$http({
           method : "POST",
           url : 'scss/getModuleDetails',
           data :requestData
       });
    }




    function addLevelMaster(postData){
        return $http({
              method: 'post',
              url: "gbmultra/common/level/master/add",
              headers: {
                  'Content-Type': 'application/json'
              },
              data : postData
          })
    }
    //Get UnVerified Records
    function getUnVerifyRecords(requestData){
        return $http({
              method: 'GET',
              url: "gbmultra/common/level/master/getVerifyDeleteDetails",
              headers: {
                  'Content-Type': 'application/json'
              },
              params : requestData
          })
    }
    function verifyDetailsSubmit(postData){
        return $http({
              method: 'post',
              url: "gbmultra/common/level/master/verify",
              headers: {
                  'Content-Type': 'application/json'
              },
              data : postData
          })
    }
    function deleteDetailsSubmit(postData){
            return $http({
                  method: 'delete',
                  url: "gbmultra/common/level/master/delete",
                  headers: {
                      'Content-Type': 'application/json'
                  },
                  params : postData
              })
        }

    //Get Inquiry Records
    function getInquiryRecords(postData){
        return $http({
              method: 'GET',
              url: "gbmultra/common/level/master/inquiry",
              headers: {
                  'Content-Type': 'application/json'
              },
              params : postData
          })
    }

    }

})();