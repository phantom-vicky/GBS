(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('rbibondBodController',
        rbibondBodController);



    rbibondBodController.$inject = ['$scope', '$state','$timeout', '$stateParams', '$rootScope', 'rbibondBodService', 'commonService'];

    function rbibondBodController($scope, $state,$timeout, $stateParams,
        $rootScope, rbibondBodService, commonService) {

        var vm = this;
        vm.levelCode = '';

        vm.startBodProcess = startBodProcess;
        vm.getMasterSolDate = getMasterSolDate;
        vm.navigateToNextState = navigateToNextState;
        vm.navigateToPreviousState = navigateToPreviousState;

        function getParametersFromState() {
            if ($state.current.name == 'rbibond_lc_bod.inquiry') {
                vm.levelCode = '1';
                vm.getMasterSolDate();
            } else if ($state.current.name == 'rbibond_lc_bod.inquiry_detail') {
                vm.levelCode = '1';
                if ($stateParams.obj && $stateParams.obj.masterSolDate) {
                    vm.masterSolDate = $stateParams.obj.masterSolDate
                } else {
                    vm.navigateToPreviousState();
                }
                vm.startBodProcess();
            } else if ($state.current.name == 'rbibond_cb_bod.inquiry') {
                vm.levelCode = '2';
                vm.getMasterSolDate();

            } else if ($state.current.name == 'rbibond_cb_bod.inquiry_detail') {
                vm.levelCode = '2';
                if ($stateParams.obj && $stateParams.obj.masterSolDate) {
                    vm.masterSolDate = $stateParams.obj.masterSolDate
                } else {
                    vm.navigateToPreviousState();
                }
                vm.startBodProcess();
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
                    $state.go('rbibond_gu_functionality');
                }
            )
        }

        function startBodProcess() {
            Swal.fire({
                title: "Are you sure?",
                text: "Do you want to initiate 'BOD'?",
                type: "warning",
                showCancelButton: true,
                cancelButtonClass: "btn-cancel",
                confirmButtonClass: "btn-primary",
                confirmButtonText: "Yes",
                closeOnConfirm: false
            }, function() {
                Swal.fire.close();
                vm.loader = true
                rbibondBodService.startBodProcess({
                    levelCode: vm.levelCode,
                    solDate: moment(vm.masterSolDate, "DD/MM/YYYY").format("DD-MMM-YYYY")
                }).then(function(response) {
                    vm.loader= false
                    vm.processingPage = true;
                    //vm.bodStatusList = response.data.result.bodStatusList;
                    vm.status = response.data.result;
                    vm.messageSucceed = response.data.message;
                }, function(error) {
                    vm.loader = false
                        vm.processingPage = true;
                        $timeout(function(){
                                      handleErrorResponse(error);
                                    }, 250);


//                        vm.navigateToPreviousState();
                });
            })
        }

        function navigateToNextState() {
            if (vm.levelCode == '1') {
                $state.go('rbibond_lc_bod.inquiry_detail', {
                    obj: {
                        masterSolDate: vm.masterSolDate
                    }
                });
            } else if (vm.levelCode == '2') {
                $state.go('rbibond_cb_bod.inquiry_detail', {
                    obj: {
                        masterSolDate: vm.masterSolDate
                    }
                });
            }
        }

        function navigateToPreviousState() {
            if (vm.levelCode == '1') {
                $state.go('rbibond_lc_bod.inquiry');
            } else if (vm.levelCode == '2') {
                $state.go('rbibond_cb_bod.inquiry');
            }
        }
    }
})();