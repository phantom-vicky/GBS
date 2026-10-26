(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('roleService', roleService);

    roleService.$inject = ['$http'];

    function roleService($http) {

        var roleService = this;
        roleService.addRole = addRole;
        roleService.getRoles = getRoles;
        roleService.getUnVerifiedRoles = getUnVerifiedRoles;
        roleService.getModifiedRoles = getModifiedRoles;
        roleService.saveUserPermissionMapping = saveUserPermissionMapping;
        roleService.getPermissions = getPermissions;
        roleService.getRole = getRole;
        roleService.updateRole = updateRole;
        roleService.deleteRole = deleteRole;
        roleService.verifyRole = verifyRole;

        function addRole(role){
        	 return $http({
                method: 'POST',
                url: "gbmultra/user/role/create",
                data: role
            })
        }

        function updateRole(role){
        	 return $http({
                method: 'POST',
                url: "gbmultra/user/role/modify",
                data: role
            })
        }

        function getRoles(requestData){
        	return $http({
                method: "POST",
                url: "gbmultra/user/role/inquiry",
                data: requestData
            });
        }

        function getUnVerifiedRoles(requestData){
        	return $http({
                method: "POST",
                url: "gbmultra/user/role/verifydetails",
                data: requestData
            });
        }
        function getModifiedRoles(requestData){
        	return $http({
                method: "POST",
                url: "gbmultra/user/role/modifydetails",
                data: requestData
            });
        }

        function getRole(id){
			return $http({
                method: "POST",
                url: "gbmultra/user/role/getRole",
                data : {roleId : id}
            });
        }

        function deleteRole(requestData){
			return $http({
                method: "POST",
                url: "gbmultra/user/role/delete",
                data: requestData
            });
        }
        function verifyRole(requestData){
			return $http({
                method: "POST",
                url: "gbmultra/user/role/verify",
                data: requestData
            });
        }

        function saveUserPermissionMapping(roleId, permissionList){
            var requestData = {};
            requestData.roleId = roleId;
            requestData.permissionIds = [];
            requestData.permissionIds = permissionList;

        	 return $http({
                method: 'POST',
                url: "gbmultra/user/role/permission/createRolePermissionMapping",
                headers: {
                    'Content-Type': 'application/json'
                },
                data: requestData
            })
        }

        function getPermissions(roleId){
        	 return $http({
                method: "POST",
                url: "gbmultra/user/role/permission/getPermissions",
                data : {roleId : roleId}
            });
        }
    }

})();