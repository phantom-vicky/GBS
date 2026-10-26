(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('cbsCommonService', cbsCommonService);

    cbsCommonService.$inject = ['$state', 'authService', 'base64Service'];

    function cbsCommonService($state, authService, base64Service) {

        var cbsCommonService = this;
        cbsCommonService.getLeftMenuItems = getLeftMenuItems;

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
            leftMenu.bulkadd = { display: views.indexOf('bulkadd') != -1 && isPermitted(views.indexOf('bulkadd')), href: parentState + '/bulkadd' };
            leftMenu.download = { display: views.indexOf('download') != -1 && isPermitted(views.indexOf('download')), href: parentState + '/download' };
            leftMenu.modify = { display: views.indexOf('modify') != -1 && isPermitted(views.indexOf('modify')), href: parentState + '/action/modify' };
            leftMenu.delete = { display: views.indexOf('delete') != -1 && isPermitted(views.indexOf('delete')), href: parentState + '/action/delete' };
            leftMenu.inquiry = { display: views.indexOf('inquiry') != -1 && isPermitted(views.indexOf('inquiry')), href: parentState + '/action/inquiry' };
            leftMenu.verify = { display: views.indexOf('verify') != -1 && isPermitted(views.indexOf('verify')), href: parentState + '/action/verify' };
            leftMenu.bulkverify = { display: views.indexOf('bulkverify') != -1 && isPermitted(views.indexOf('bulkverify')), href: parentState + '/action/bulkverify' };
            leftMenu.disable = { display: views.indexOf('disable') != -1 && isPermitted(views.indexOf('disable')), href: parentState + '/action/disable' };
            leftMenu.upload = { display: views.indexOf('upload') != -1 && isPermitted(views.indexOf('upload')), href: parentState + '/action/upload_feature' }
            leftMenu.api = { display: views.indexOf('api') != -1 && isPermitted(views.indexOf('api')), href: parentState + '/action/api' }

            return leftMenu;
        }

        function isPermitted(index){                     
            return getAvailablePermissions().indexOf(base64Service.encode(getRequiredPermissions()[index])) != -1;
        }
    }

})();