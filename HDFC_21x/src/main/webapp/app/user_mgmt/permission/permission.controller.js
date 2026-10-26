(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .controller('PermissionController', PermissionController);

    PermissionController.$inject = ['$filter', '$state', '$stateParams', 'permissionService', 'commonService'];

    function PermissionController($filter, $state, $stateParams, permissionService, commonService) {

        var vm = this;
        vm.getFeatures = getFeatures;
        vm.generatePermissionDispName = generatePermissionDispName;
        vm.addPermissionConfirmation = addPermissionConfirmation;
        vm.modifyPermissionConfirmation = modifyPermissionConfirmation;
        vm.getPermission = getPermission;
        vm.showCancelConfirmation = showCancelConfirmation;

        vm.modules = [];
        vm.features = [];
        vm.permissionTypes = [];

        if ($state.current.name == 'permission.modify') {
            getPermission();
        } else if ($state.current.name == 'permission.add') {
            getModules();
            getPermissionTypes();
        }

        function getModules() {
        	commonService.getAllModules()
                .then(
                    function(response) {
                        vm.modules = response.data;
                    }, handleErrorResponse
                );
        }

        function getFeatures() {
            permissionService.getAllFeatures(vm.permission.moduleId)
                .then(
                    function(response) {
                        vm.features = response.data;
                    }, handleErrorResponse
                );
        }

        function getPermissionTypes() {
            permissionService.getPermissionTypes()
                .then(
                    function(response) {
                        vm.permissionTypes = response.data;
                    }, handleErrorResponse
                );
        }

        function getPermission() {
            permissionService.getPermission($stateParams.id)
                .then(
                    function(response) {
                        vm.permission = response.data;
                    }, handleErrorResponse
                );
        }

        function addPermissionConfirmation() {
//            Swal.fire({
//                    title: "Are you sure?",
//                    text: "Do You Really Want to Add This Permission? ",
//                    type: "warning",
//                    showCancelButton: true,
//                    cancelButtonClass: "btn-cancel",
//                    confirmButtonClass: "btn-primary",
//                    confirmButtonText: "Yes!",
//                    closeOnConfirm: false,
//                    showLoaderOnConfirm: true
//                },
//                function() {




                      Swal.fire({
                                         title: "Are you sure?",
                                         text: "Do you Really Want To Add This Permision?",
                                         icon: "warning", // use "icon", not "type"
                                         showCancelButton: true,
                                         confirmButtonText: "Yes",
                                         cancelButtonText: "Cancel",
                                         customClass: {
                                           confirmButton: "btn-primary",
                                           cancelButton: "btn-cancel"
                                         },
                                         buttonsStyling: false
                                       }).then((result) => {

                                              if (result.isConfirmed) {
                    addPermission();
             }   });
        }

        function addPermission() {
            vm.permission.description = vm.permission.description ? (vm.permission.description).toUpperCase() : '';
            permissionService.addPermission(vm.permission)
                .then(
                    function(response) {
                        vm.permission = {};
                        Swal.fire("Added!", response.data.message, "success");
                    }, handleErrorResponse
                );
        }

        function modifyPermissionConfirmation() {
//            Swal.fire({
//                    title: "Are you sure?",
//                    text: "Do You Really Want to Change This Permission? ",
//                    type: "warning",
//                    showCancelButton: true,
//                    cancelButtonClass: "btn-cancel",
//                    confirmButtonClass: "btn-primary",
//                    confirmButtonText: "Yes!",
//                    closeOnConfirm: false,
//                    showLoaderOnConfirm: true
//                },
//                function() {




                      Swal.fire({
                                         title: "Are you sure?",
                                         text: "Do you Really Want To Change This Permmission?",
                                         icon: "warning", // use "icon", not "type"
                                         showCancelButton: true,
                                         confirmButtonText: "Yes",
                                         cancelButtonText: "Cancel",
                                         customClass: {
                                           confirmButton: "btn-primary",
                                           cancelButton: "btn-cancel"
                                         },
                                         buttonsStyling: false
                                       }).then((result) => {

                                              if (result.isConfirmed) {
                    modifyPermission();
               } });
        }

        function modifyPermission() {
            vm.permission.description = vm.permission.description ? (vm.permission.description).toUpperCase() : '';
            permissionService.modifyPermission(vm.permission)
                .then(
                    function(response) {
                        vm.permission = {};
                        location.href = '#!/permission/action/modify';
                        Swal.fire("Modified!", response.data.message, "success");
                    }, handleErrorResponse
                );
        }

        function generatePermissionDispName() {
            if (angular.isUndefined(vm.permission.moduleId) || angular.isUndefined(vm.permission.featureId) || angular.isUndefined(vm.permission.type)) {
                vm.permission.displayName = "";
                return;
            }
            var module = $filter('filter')(vm.modules, {
                'id': vm.permission.moduleId
            }, true);
            var feature = $filter('filter')(vm.features, {
                'id': vm.permission.featureId
            }, true);
            vm.permission.displayName = module[0].displayName + ":" + feature[0].displayName + ":" + vm.permission.type;
        }

        function handleErrorResponse(response) {
            var msg = response.data.message ? response.data.message : "Server Error";
            Swal.fire("Error!", msg, "error");
        }

        function showCancelConfirmation() {
//            Swal.fire({
//                    title: "Confirm Cancel",
//                    text: "Do you really want to cancel?",
//                    type: "warning",
//                    showCancelButton: true,
//                    cancelButtonClass: "btn-cancel",
//                    confirmButtonClass: "btn-danger",
//                    confirmButtonText: "Yes",
//                    cancelButtonText: "No",
//                    closeOnConfirm: true
//                },
//                function() {

    Swal.fire({
                       title: "Are you sure?",
                       text: "Do you Really Want To Cancel?",
                       icon: "warning", // use "icon", not "type"
                       showCancelButton: true,
                       confirmButtonText: "Yes",
                       cancelButtonText: "Cancel",
                       customClass: {
                         confirmButton: "btn-primary",
                         cancelButton: "btn-cancel"
                       },
                       buttonsStyling: false
                     }).then((result) => {

                            if (result.isConfirmed) {
                    location.href = '#!/permission/action/modify';

               } });
        }

    }

})();