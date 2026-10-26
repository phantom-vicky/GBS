(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('rbibondReversalChangeAccNoService', rbibondReversalChangeAccNoService);

    rbibondReversalChangeAccNoService.$inject = ['$http'];

    function rbibondReversalChangeAccNoService($http) {

    	var rbibondReversalChangeAccNoService = this;

 rbibondReversalChangeAccNoService.getDetails = getDetails;
 rbibondReversalChangeAccNoService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
 rbibondReversalChangeAccNoService.getAddSubmit = getAddSubmit;
 rbibondReversalChangeAccNoService.getInquiry = getInquiry;
 rbibondReversalChangeAccNoService.getVerify = getVerify;
 rbibondReversalChangeAccNoService.getVerifySubmit = getVerifySubmit;
 rbibondReversalChangeAccNoService.getDeleteSubmit = getDeleteSubmit;
 rbibondReversalChangeAccNoService.getDelete = getDelete;
 rbibondReversalChangeAccNoService.getRemarksList= getRemarksList;

       function getRbiBondModuleNameDetails(requestData){
             return $http({
                 method: 'POST',
                 url: 'rbiBond/getModuleDetails',
                 data :requestData
             })
       }


        function getDetails(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/subReveral/addValidation',
                data: requestData
            })
        }

        function getAddSubmit(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/subReveral/add',
                data: requestData
            })
        }

``

        function getInquiry(requestData) {
                return $http({
                    method: 'POST',
                    url: 'gbmultra/rbibond/subReveral/inquiry',
                    data: requestData
                })
        }



        function getVerify(requestData) {
                    return $http({
                        method: 'POST',
                        url: 'gbmultra/rbibond/subReveral/verifySelect',
                        data: requestData
                    })
        }


        function getVerifySubmit(requestData) {
                         return $http({
                             method: 'POST',
                             url: 'gbmultra/rbibond/subReveral/verify',
                             data: requestData
                         })
        }

        function getDelete(requestData) {
                              return $http({
                                  method: 'POST',
                                  url: 'gbmultra/rbibond/subReveral/verifySelect',
                                  data: requestData
                              })
        }

        function getDeleteSubmit(requestData) {
                              return $http({
                                  method: 'POST',
                                  url: 'gbmultra/rbibond/subReveral/delete',
                                  data: requestData
                              })
        }

        function getRemarksList (requestData){
                              return $http({
                                   method:'POST',
                                   url:'rbiBond/getRemarksList',
                                   data:requestData
                              })
        }
}
})();