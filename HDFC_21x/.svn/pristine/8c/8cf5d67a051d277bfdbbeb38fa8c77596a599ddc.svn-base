(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('unlockUsersService', unlockUsersService);

    unlockUsersService.$inject = ['$rootScope', '$http', '$window', 'base64Service'];

    function unlockUsersService($rootScope, $http, $window, base64Service) {


                    var unlockUsersService = this;

                    unlockUsersService.modifySelectService = modifySelectService;
                    unlockUsersService.verifySelectService = verifySelectService;
                    unlockUsersService.verifyService = verifyService;
                    unlockUsersService.deleteService = deleteService;
                    unlockUsersService.modifyService = modifyService;




                    function modifySelectService(){
                               return $http({
                                   method: "GET",
                                   url: "gbmultra/userMgmt/unlock/modifySelect",
//                                   data : requestData
                               });
                           }


                    function verifySelectService(requestData){
                               return $http({
                                   method: "post",
                                   url: "gbmultra/userMgmt/unlock/verifySelect",
                                   data : requestData
                               });
                    }

                    function verifyService(requestData){
                               return $http({
                                   method: "post",
                                   url: "gbmultra/userMgmt/unlock/verify",
                                   data : requestData
                               });
                    }

                   function deleteService(requestData){
                              return $http({
                                  method: "post",
                                  url: "gbmultra/userMgmt/unlock/delete",
                                  data : requestData
                              });
                   }

                   function modifyService(requestData){
                             return $http({
                                 method: "post",
                                 url: "gbmultra/userMgmt/unlock/modify",
                                 data : requestData
                             });
                  }



                    unlockUsersService.getRemarksList = function (requestData) {
                          return $http({
                            method: "POST",
                            url: "rbiBond/getRemarksList",
                            data: requestData,
                          });
                        };

    }

})();