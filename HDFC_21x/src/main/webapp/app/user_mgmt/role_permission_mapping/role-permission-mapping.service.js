(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('rolePermissionMappingService', rolePermissionMappingService);

    rolePermissionMappingService.$inject = ['$http'];

    function rolePermissionMappingService($http) {

    	var rolePermissionMappingService = this;

    	rolePermissionMappingService.getRoles = getRoles;
    	rolePermissionMappingService.getAllRoles = getAllRoles;
    	rolePermissionMappingService.addPermissionSubmit = addPermissionSubmit;
    	rolePermissionMappingService.getModifiedRoles = getModifiedRoles;
    	rolePermissionMappingService.modifyPermissionSubmit = modifyPermissionSubmit;
    	rolePermissionMappingService.getUnVerifiedRoles = getUnVerifiedRoles;
    	rolePermissionMappingService.verifySubmit = verifySubmit;
        rolePermissionMappingService.deleteSubmit = deleteSubmit;
        function getAllRoles(requestData){
        	return $http({
                method: "POST",
                url: "gbmultra/admin/rolemapping/savedetails",
                data: requestData
            });
        }

        function addPermissionSubmit(requestData){
        	return $http({
                method: "POST",
                url: "gbmultra/admin/rolemapping/save",
                data: requestData
            });
        }

        function modifyPermissionSubmit(requestData){
        	return $http({
                method: "POST",
                url: "gbmultra/admin/rolemapping/modify",
                data: requestData
            });
        }

        function getRoles(requestData){
        	return $http({
                method: "POST",
                url: "gbmultra/admin/rolemapping/inquiry",
                data: requestData
            });
        }

        function getUnVerifiedRoles(requestData){
        	return $http({
                method: "POST",
                url: "gbmultra/admin/rolemapping/verifydetails",
                data: requestData
            });
        }

        function getModifiedRoles(requestData){
        	return $http({
                method: "POST",
                url: "gbmultra/admin/rolemapping/modifydetails",
                data: requestData
            });
        }

        function verifySubmit(requestData){
        	return $http({
                method: "POST",
                url: "gbmultra/admin/rolemapping/verify",
                data: requestData
            });
        }
                function deleteSubmit(requestData){
                	return $http({
                        method: "POST",
                       url: "gbmultra/admin/rolemapping/delete",
                        data: requestData
                    });
                }


                  rolePermissionMappingService.getRemarksList = function (requestData) {
                                          return $http({
                                            method: "POST",
                                            url: "rbiBond/getRemarksList",
                                            data: requestData,
                                          });
                                        };


    }

})();