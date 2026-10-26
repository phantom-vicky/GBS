(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('permissionService', permissionService);

    permissionService.$inject = ['$http'];

    function permissionService($http) {

        var permissionService = this;
        permissionService.getAllFeatures = getAllFeatures;
        permissionService.addPermission = addPermission;
        permissionService.getPermissionTypes = getPermissionTypes;
        permissionService.getAllPermissions = getAllPermissions;
        permissionService.deletePermission = deletePermission;  
        permissionService.getPermission = getPermission;
        permissionService.modifyPermission = modifyPermission;

        function getAllFeatures(moduleId) {
            return $http({
                method: "GET",
                url: "gbmultra/user/module/" + moduleId + "/feature/all"
            });
        }

        function getPermissionTypes() {
            return $http({
                method: "GET",
                url: "gbmultra/user/permission/types"
            });
        }


        function getAllPermissions() {
            return $http({
                method: "GET",
                url: "gbmultra/user/permission/all"
            });
        }

        function addPermission(permission) {
            return $http({
                method: 'POST',
                url: "gbmultra/user/permission",
                headers: {
                    'Content-Type': 'application/json'
                },
                data: permission
            })
        }

        function getPermission(id){
            return $http({
                method: "GET",
                url: "gbmultra/user/permission/" + id
            });
        }

        function deletePermission(id) {
            return $http({
                method: "DELETE",
                url: "gbmultra/user/permission/" + id
            });
        }

        function modifyPermission(permission){
            return $http({
                method: 'PUT',
                url: "gbmultra/user/permission",
                headers: {
                    'Content-Type': 'application/json'
                },
                data: permission
            })
        }

    }

})();