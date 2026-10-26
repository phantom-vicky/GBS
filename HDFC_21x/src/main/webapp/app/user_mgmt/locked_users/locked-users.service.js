(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('lockedUserService', lockedUserService);

    lockedUserService.$inject = ['$http'];

    function lockedUserService($http) {

        var lockedUserService = this;
        lockedUserService.getLockedUsers = getLockedUsers;
        lockedUserService.unlockUser = unlockUser;


        function getLockedUsers() {
            return $http({
                method: "POST",
                url: "gbmultra/admin/lockedusers"
            });
        }


        function unlockUser(id) {
            return $http({
                method: 'POST',
                url: "gbmultra/admin/user/unlock",
                headers: {
                    'Content-Type': 'application/json'
                },
                data : {userId : id}
            })
        }

    }

})();