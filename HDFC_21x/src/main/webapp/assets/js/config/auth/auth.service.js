(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('authService', authService);

    authService.$inject = ['$http', '$rootScope', '$window', 'base64Service'];

    function authService($http, $rootScope, $window, base64Service) {

        var authService = this;
        authService.setGlobals = setGlobals;
        authService.setPerms = setPerms;
        authService.getPerms = getPerms;
        authService.logout = logout;
        authService.isAuthorized = isAuthorized;
        authService.login = login;
        authService.sendHeartBeat = sendHeartBeat;

        function logout(reason) {
            $window.sessionStorage.removeItem('perms');
            $rootScope.globals = {}
            return $http({
                method: "GET",
                url: window.getApiUrl("gbmultra/user/logout"),
                params : {
                    logOutReason : reason
                }
            });
        }

        function login(credentials) {
            var userCredentails = {};
            angular.copy(credentials, userCredentails);
            //userCredentails.password = aesEncrypt(userCredentails.password);
            userCredentails.password = $rootScope.toEncrypt(userCredentails.password);

            return $http({
                method: 'POST',
                url: window.getApiUrl("gbmultra/user/login"),
                headers: {
                    'Content-Type': 'application/json'
                },
                data: userCredentails
            })
        }

        function sendHeartBeat(){
            return $http({
                method: "GET",
                url: window.getApiUrl("gbmultra/user/heartbeat")
            });
        }

        function setGlobals(globals){
           	$rootScope.globals = {
                currentUser: {
                    username: globals.username,
                    lastLoginTime: globals.lastLoginTime,
                    solId: globals.solId,
                    solDesc: globals.solDesc,
                    solDate: globals.solDate,
                    bankName: globals.bankName,
                    systemDate: moment(globals.solDate,"DD/MM/YYYY").toDate(),
                    warDeploymentFlag : globals.warDeploymentFlag,
                    warDeploymentDate : globals.warDeploymentDate,
                }
            };
            //$window.sessionStorage.setItem('globals', JSON.stringify($rootScope.globals));
        }

        function setPerms(permissions){
            var perms = [];
            angular.forEach(permissions, function(value, key) {
                perms.push(base64Service.encode(value));
            });
            $window.sessionStorage.setItem('perms', JSON.stringify(perms));
        }

        function getPerms(){
            return $window.sessionStorage.getItem('perms');
        }

        function isAuthorized(permission){
            return getPerms() != null && getPerms().indexOf(base64Service.encode(permission)) != -1;
        }  

    }

})();