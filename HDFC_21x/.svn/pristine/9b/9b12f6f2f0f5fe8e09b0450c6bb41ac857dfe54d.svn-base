(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('activeUserService', activeUserService);

    activeUserService.$inject = ['$http'];

    function activeUserService($http) {

        var activeUserService = this;
        activeUserService.modifyUsers = modifyUsers;
        activeUserService.verifySubmit = verifySubmit;
        activeUserService.deleteSubmit = deleteSubmit;
        activeUserService.getUsers = getUsers;
        activeUserService.getVerify = getVerify;





         function getUsers() {
                    return $http({
                        method: "POST",
                        url: "gbmultra/admin/activeUser/getUsers",

                                });
                              }


//         function modifyUsers(requestData){
//                                  return $http({
//                                  method : "POST",
//                                  url : "gbmultra/admin/activeUser/modifysubmit",
//                                     data:requestData
//                                  });
//                               }

function modifyUsers(requestData) {
    return $http({
        method: "POST",
        url: "gbmultra/admin/activeUser/modifysubmit",
        data: requestData
    });
}

         function getVerify() {
                   return $http({
                       method: "POST",
                       url: "gbmultra/admin/activeUser/getVerifyDetails"
                   });
               }




         function verifySubmit(requestData){
                     return $http({
                        method: 'POST',
                        url: "gbmultra/admin/activeUser/verifyUsers",
                       data: requestData
                               });
                        }

          function deleteSubmit(requestData) {
                     return $http({
                       method: "POST",
                       url: "gbmultra/admin/activeUser/deleteUsers",
                       data: requestData
    });
}

            activeUserService.getRemarksList = function (requestData) {
                                    return $http({
                                      method: "POST",
                                      url: "rbiBond/getRemarksList",
                                      data: requestData,
                                    });
                                  };

    }

})();