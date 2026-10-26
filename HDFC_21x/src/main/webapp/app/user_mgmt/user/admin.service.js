(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('adminService', adminService);

    adminService.$inject = ['$http', 'base64Service', '$rootScope'];

    function adminService($http, base64Service, $rootScope) {
        var adminService = this;
        adminService.addUser = addUser;
        adminService.getUsers = getUsers;
        adminService.getAllUsers = getAllUsers;
        adminService.getUnVerifiedUsers = getUnVerifiedUsers;
        adminService.saveUserRoleMapping = saveUserRoleMapping;
        adminService.getRoles = getRoles;
        adminService.updateUser = updateUser;
        adminService.getUser = getUser;
        adminService.verifyUser = verifyUser;
        adminService.deleteUser = deleteUser;
        adminService.disableUser = disableUser;
        adminService.activeUser = activeUser;
        adminService.getUserByUsername = getUserByUsername;

        adminService.editModifySubmit = function(requestData) {
            return $http({
                method: "post",
                url: 'gbmultra/admin/user/modify',
                data: requestData
            });
        }

        function activeUser(userId) {
            var url = "gbmultra/admin/user/active";
            return $http({
                method: 'POST',
                url: url,
                headers: {
                    'Content-Type': 'application/json'
                },
                data : {userId : userId}
            })
        }

        function addUser(user) {
            var userDetails = {};
            angular.copy(user, userDetails);
            //userDetails.password = aesEncrypt(userCredentails.password);
            //userDetails.password = base64Service.encode(userDetails.password);
            userDetails.password = $rootScope.toEncrypt(userDetails.password);
            return $http({
                method: 'POST',
                url: "gbmultra/admin/user/add",
                headers: {
                    'Content-Type': 'application/json'
                },
                data: userDetails
            })
        }

        function updateUser(user) {
            return $http({
                method: 'POST',
                url: "gbmultra/admin/user/updateUser",
                headers: {
                    'Content-Type': 'application/json'
                },
                data: user
            })
        }

        function deleteUser(requestData) {
            return $http({
                method: "POST",
                url: "gbmultra/admin/user/delete",
                data: requestData
            });
        }

        function verifyUser(requestData) {
            return $http({
                method: "POST",
                url: "gbmultra/admin/user/verify",
                data: requestData
            });
        }

        function disableUser(userId, disabledTill) {
        var requestData = {};
        requestData.userId = userId;
        requestData.disabledTill = disabledTill;

            var url = "gbmultra/admin/user/disable";
            return $http({
                method: 'POST',
                url: url,
                headers: {
                    'Content-Type': 'application/json'
                },
                data : requestData
            })
        }

        function getUser(userId) {
            return $http({
                method: "POST",
                url: "gbmultra/admin/user/getUser",
                data : {userId : userId}
            });
        }

        function getUsers(requestData) {
            return $http({
                method: "POST",
                url: "gbmultra/admin/user/inquiry",
                data: requestData
            });
        }

        function getAllUsers(requestData) {
            return $http({
                method: "POST",
                url: "gbmultra/admin/user/modifyUserDetails",
                data: requestData
            });
        }

        function getUnVerifiedUsers(requestData) {
            return $http({
                method: "POST",
                url: "gbmultra/admin/user/verifyUserDetails",
                data: requestData
            });
        }

        function getUserByUsername(username) {
            return $http({
                method: "POST",
                url: "gbmultra/admin/user/search",
                data : {userName : username}
            });
        }

        function getRoles(userId) {
            return $http({
                method: "POST",
                url: "gbmultra/admin/user/role",
                data : {userId : userId}
            });
        }

        function saveUserRoleMapping(userId, roleList) {
        var requestData = {};
        requestData.userId = userId;
        requestData.roleIds = [];
        requestData.roleIds = roleList;

            return $http({
                method: 'POST',
                url: "gbmultra/admin/user/createUserRoleMapping",
                headers: {
                    'Content-Type': 'application/json'
                },
                data: requestData
            })
        }


    }

})();