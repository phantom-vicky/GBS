(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('rbiBondLevelMasterService', rbiBondLevelMasterService);

    rbiBondLevelMasterService.$inject = ['$http'];

    function rbiBondLevelMasterService($http) {

    var rbiBondLevelMasterService = this;


    rbiBondLevelMasterService.addLevelMaster = addLevelMaster;
    rbiBondLevelMasterService.addValidation = addValidation;
    rbiBondLevelMasterService.verifyDetailsSubmit = verifyDetailsSubmit;

    rbiBondLevelMasterService.getModuleDetails  =   getModuleDetails;
    rbiBondLevelMasterService.getUnVerifyRecords  =   getUnVerifyRecords;
    rbiBondLevelMasterService.getInquiryRecords  =   getInquiryRecords;
    rbiBondLevelMasterService.deleteDetailsSubmit  =   deleteDetailsSubmit;

    /* Get Module Details */
    function getModuleDetails(requestData)
    {
       return	$http({
           method : "POST",
           url : 'rbiBond/getModuleDetails',
           data :requestData
       });
    }




    function addLevelMaster(postData){
        return $http({
              method: 'post',
              url: "gbmultra/rbiBond/levelMaster/add",
              headers: {
                  'Content-Type': 'application/json'
              },
              data : postData
          })
    }

        function addValidation(postData){
            return $http({
                  method: 'post',
                  url: "gbmultra/rbiBond/levelMaster/addValidation",
                  headers: {
                      'Content-Type': 'application/json'
                  },
                  data : postData
              })
        }

    //Get UnVerified Records
    function getUnVerifyRecords(requestData){
        return $http({
              method: 'POST',
              url: "gbmultra/rbiBond/levelMaster/getVerifyDeleteDetails",
              headers: {
                  'Content-Type': 'application/json'
              },
              data : requestData
          })
    }
    function verifyDetailsSubmit(postData){
        return $http({
              method: 'POST',
              url: "gbmultra/rbiBond/levelMaster/verify",
              headers: {
                  'Content-Type': 'application/json'
              },
              data : postData
          })
    }
    function deleteDetailsSubmit(requestData){
            return $http({
                  method: 'POST',
                  url: "gbmultra/rbiBond/levelMaster/delete",
                  headers: {
                      'Content-Type': 'application/json'
                  },
                  data : requestData
              })
        }

    //Get Inquiry Records
    function getInquiryRecords(postData){
        return $http({
              method: 'post',
              url: "gbmultra/rbiBond/levelMaster/inquiry",
              headers: {
                  'Content-Type': 'application/json'
              },
              data : postData
          })
    }

    rbiBondLevelMasterService.getRemarksList=function (requestData){
                                                                      return $http({
                                                                           method:'POST',
                                                                           url:'rbiBond/getRemarksList',
                                                                           data:requestData
                                                                      })
                                                         }
    }

})();