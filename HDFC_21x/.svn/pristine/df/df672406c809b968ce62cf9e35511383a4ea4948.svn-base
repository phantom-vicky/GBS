(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('userCommonService', userCommonService);

    userCommonService.$inject = ['$state', 'authService', 'base64Service'];

    function userCommonService($state, authService, base64Service) {

        var userCommonService = this;
        userCommonService.getLeftMenuItems = getLeftMenuItems;

        var requiredPermissions = [];
        var availablePermissions = [];

        function getRequiredPermissions(){
            return requiredPermissions;
        }
  
        function setRequiredPermissions(){
            requiredPermissions = $state.params.permissions;   
        }

        function getAvailablePermissions(){
            return availablePermissions;
        }

         function setAvailablePermissions(){
            availablePermissions = authService.getPerms();
        }

        function getLeftMenuItems() {
            setRequiredPermissions();
            setAvailablePermissions();

            var views = $state.params.views;
            var parentState = '#!/' + $state.$current.parent.name;

            var leftMenu = {};
            leftMenu.add = { display: views.indexOf('add') != -1 && isPermitted(views.indexOf('add')), href: parentState + '/add' };
            leftMenu.modify = { display: views.indexOf('modify') != -1 && isPermitted(views.indexOf('modify')), href: parentState + '/action/modify' };
            leftMenu.delete = { display: views.indexOf('delete') != -1 && isPermitted(views.indexOf('delete')), href: parentState + '/action/delete' };
            leftMenu.inquiry = { display: views.indexOf('inquiry') != -1 && isPermitted(views.indexOf('inquiry')), href: parentState + '/action/inquiry' };
            leftMenu.verify = { display: views.indexOf('verify') != -1 && isPermitted(views.indexOf('verify')), href: parentState + '/action/verify' };
            leftMenu.disable = { display: views.indexOf('disable') != -1 && isPermitted(views.indexOf('disable')), href: parentState + '/action/disable' };

            return leftMenu;
        }

        function isPermitted(index){                     
            return getAvailablePermissions().indexOf(base64Service.encode(getRequiredPermissions()[index])) != -1;
        }
    }

})();