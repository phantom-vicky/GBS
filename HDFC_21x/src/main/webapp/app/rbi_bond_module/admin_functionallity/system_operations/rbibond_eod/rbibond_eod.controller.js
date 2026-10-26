(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('rbiBondEodController',
        rbiBondEodController);



    rbiBondEodController.$inject = ['$scope', '$state', '$stateParams', '$timeout', '$rootScope', 'rbiBondEodService', 'commonService'];

    function rbiBondEodController($scope, $state, $stateParams, $timeout,
        $rootScope, rbiBondEodService, commonService) {

        var vm = this;
        vm.levelCode = '';

        vm.startEodProcess = startEodProcess;
        vm.getMasterSolDate = getMasterSolDate;
        vm.navigateToNextState = navigateToNextState;
        vm.navigateToPreviousState = navigateToPreviousState;
        vm.timeValidation = timeValidation;

        function getParametersFromState() {
            if ($state.current.name == 'rbibond_link_cell_eod.inquiry') {
                vm.levelCode = '1';
                vm.getMasterSolDate();
            } else if ($state.current.name == 'rbibond_link_cell_eod.inquiry_detail') {
                vm.levelCode = '1';
                if ($stateParams.obj && $stateParams.obj.masterSolDate) {
                    vm.masterSolDate = $stateParams.obj.masterSolDate
                } else {
                    vm.navigateToPreviousState();
                }
                vm.startEodProcess();
            } else if ($state.current.name == 'rbibond_cb_eod.inquiry') {
                vm.levelCode = '2';
                vm.getMasterSolDate();

            } else if ($state.current.name == 'rbibond_cb_eod.inquiry_detail') {
                vm.levelCode = '2';
                if ($stateParams.obj && $stateParams.obj.masterSolDate) {
                    vm.masterSolDate = $stateParams.obj.masterSolDate
                } else {
                    vm.navigateToPreviousState();
                }
                vm.startEodProcess();
            }
        }
        getParametersFromState();


        function getMasterSolDate() {
            vm.loader = true;
            commonService.getGbmSolDate().then(
                function(response) {
                    vm.loader = false;
                    vm.masterSolDate = response.data.result.solDate;
                },
                function(error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                    $state.go('rbibond_admin_functionality');
                }
            )
        }

        function startEodProcess() {
            Swal.fire({
                title: "Are you sure?",
                text: "Do you want to initiate 'EOD'?",
                type: "warning",
                showCancelButton: true,
                cancelButtonClass: "btn-cancel",
                confirmButtonClass: "btn-primary",
                confirmButtonText: "Yes",
                closeOnConfirm: false
            }, function() {
                Swal.fire.close()
                vm.loader = true;
                rbiBondEodService.startEodProcess({
                    levelCode: vm.levelCode,
                    solDate: moment(vm.masterSolDate, "DD/MM/YYYY").format("DD-MM-YYYY")
                }).then(
                    (response) => {
                        vm.loader = false;
                        vm.processingPage = true;
                        vm.eodResponseModel = response.data.result
                    },
                    (error) => {
                        vm.loader = false;
                        vm.processingPage = true;
                           $timeout(function(){
                           handleErrorResponse(error);
                           }, 250);
                         vm.navigateToPreviousState();
                    }
                )
            })

        }

        function navigateToNextState() {
            if (vm.levelCode == '1') {
                $state.go('rbibond_link_cell_eod.inquiry_detail', {
                    obj: {
                        masterSolDate: vm.masterSolDate
                    }
                });
            } else if (vm.levelCode == '2') {
                $state.go('rbibond_cb_eod.inquiry_detail', {
                    obj: {
                        masterSolDate: vm.masterSolDate
                    }
                });
            }
        }

        function navigateToPreviousState() {
            if (vm.levelCode == '1') {
                $state.go('rbibond_link_cell_eod.inquiry');
            } else if (vm.levelCode == '2') {
                $state.go('rbibond_cb_eod.inquiry');
            }
        }

        function timeValidation(){
                     vm.loader = true;
                    rbiBondEodService.getTimeValidation().then(function (response) {
                                vm.loader = false;
                            }, function (error) {
                                vm.loader = false;
                                handleErrorResponse(error);
                               if (vm.levelCode == '1') {
                                               $state.go('rbibond_link_cell_eod.inquiry');
                                           } else if (vm.levelCode == '2') {
                                               $state.go('rbibond_cb_eod.inquiry');
                                           }
                            })
                }

    }
})();