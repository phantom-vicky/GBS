(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('userRoleMappingService', userRoleMappingService);

    userRoleMappingService.$inject = ['$http'];

    function userRoleMappingService($http) {

    	var userRoleMappingService = this;

    	userRoleMappingService.getRoles = getRoles;
    	userRoleMappingService.getAllRoles = getAllRoles;
    	userRoleMappingService.addRoleSubmit = addRoleSubmit;
    	userRoleMappingService.getModifiedRoles = getModifiedRoles;
    	userRoleMappingService.modifyRoleSubmit = modifyRoleSubmit;
    	userRoleMappingService.getUnVerifiedRoles = getUnVerifiedRoles;
    	userRoleMappingService.verifySubmit = verifySubmit;

        function getAllRoles(requestData){
        	return $http({
                method: "POST",
                url: "gbmultra/admin/userrolemapping/savedetails",
                data: requestData
            });
        }

        function addRoleSubmit(requestData){
        	return $http({
                method: "POST",
                url: "gbmultra/admin/userrolemapping/save",
                data: requestData
            });
        }

        function modifyRoleSubmit(requestData){
        	return $http({
                method: "POST",
                url: "gbmultra/admin/userrolemapping/modify",
                data: requestData
            });
        }

        function getRoles(requestData){
        	return $http({
                method: "POST",
                url: "gbmultra/admin/userrolemapping/inquiry",
                data: requestData
            });
        }

        function getUnVerifiedRoles(requestData){
        	return $http({
                method: "POST",
                url: "gbmultra/admin/userrolemapping/verifydetails",
                data: requestData
            });
        }

        function getModifiedRoles(requestData){
        	return $http({
                method: "POST",
                url: "gbmultra/admin/userrolemapping/modifydetails",
                data: requestData
            });
        }

        function verifySubmit(requestData){
        	return $http({
                method: "POST",
                url: "gbmultra/admin/userrolemapping/verify",
                data: requestData
            });
        }

    }

})();