(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('userCreationRoleMappingService', userCreationRoleMappingService);

    userCreationRoleMappingService.$inject = ['$rootScope', '$http', '$window', 'base64Service'];

    function userCreationRoleMappingService($rootScope, $http, $window, base64Service) {


                    var userCreationRoleMappingService = this;

                    userCreationRoleMappingService.getAllRoles = getAllRoles;
                    userCreationRoleMappingService.getBranchCodes = getBranchCodes;
                    userCreationRoleMappingService.getValidateUsername = getValidateUsername;
                    userCreationRoleMappingService.getValidateEmployeeCode = getValidateEmployeeCode;
                    userCreationRoleMappingService.getValidateEmail = getValidateEmail;
                    userCreationRoleMappingService.addService = addService;
                    userCreationRoleMappingService.inquiryService = inquiryService;
                    userCreationRoleMappingService.modifySelectService = modifySelectService;
                    userCreationRoleMappingService.verifySelectService = verifySelectService;
                    userCreationRoleMappingService.verifyService = verifyService;
                    userCreationRoleMappingService.deleteService = deleteService;
                    userCreationRoleMappingService.modifyService = modifyService;

                    function getAllRoles(){
                            	return $http({
                                    method: "POST",
                                    url: "gbsultra/user/usrCrtRleCreation/getRoleDetails",
                                });
                            }


                    function getBranchCodes(){
                                return $http({
                                    method: "POST",
                                    url: "gbsultra/user/usrCrtRleCreation/getBranchDetails"
                                });
                            }



                    function getValidateUsername(username){
                                return $http({
                                    method: "GET",
                                    url: "gbsultra/user/usrCrtRleCreation/validateUsername?username=" + username
                                });
                            }

                    function getValidateEmployeeCode(employeeCode){
                                    return $http({
                                        method: "GET",
                                        url: "gbsultra/user/usrCrtRleCreation/validateEmployeeCode?employeeCode=" + employeeCode
                                    });
                                }

                    function getValidateEmail(email){
                                    return $http({
                                        method: "GET",
                                        url: "gbsultra/user/usrCrtRleCreation/validateEmail?email=" + email
                                    });
                                }

                    function addService(requestData){
                               return $http({
                                   method: "post",
                                   url: "gbsultra/user/usrCrtRleCreation/add",
                                   data : requestData
                               });
                           }


                    function inquiryService(requestData){
                               return $http({
                                   method: "post",
                                   url: "gbsultra/user/usrCrtRleCreation/inquiry",
                                   data : requestData
                               });
                           }


                    function modifySelectService(requestData){
                               return $http({
                                   method: "post",
                                   url: "gbsultra/user/usrCrtRleCreation/modifySelect",
                                   data : requestData
                               });
                           }


                    function verifySelectService(requestData){
                               return $http({
                                   method: "post",
                                   url: "gbsultra/user/usrCrtRleCreation/verifySelect",
                                   data : requestData
                               });
                    }

                    function verifyService(requestData){
                               return $http({
                                   method: "post",
                                   url: "gbsultra/user/usrCrtRleCreation/verify",
                                   data : requestData
                               });
                    }

                   function deleteService(requestData){
                              return $http({
                                  method: "post",
                                  url: "gbsultra/user/usrCrtRleCreation/delete",
                                  data : requestData
                              });
                   }

                   function modifyService(requestData){
                             return $http({
                                 method: "post",
                                 url: "gbsultra/user/usrCrtRleCreation/modify",
                                 data : requestData
                             });
                  }



                   userCreationRoleMappingService.getRemarksList = function (requestData) {
                        return $http({
                          method: "POST",
                          url: "rbiBond/getRemarksList",
                          data: requestData,
                        });
                      };

    }

})();