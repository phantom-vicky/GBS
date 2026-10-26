(function() {

	'use strict';

	angular.module('gbmUltraApp').controller('gbmReconciliationController',
			gbmReconciliationController);

	gbmReconciliationController.$inject = [ '$scope', '$filter', '$state',
			'$stateParams', '$location', 'gbmReconciliationService', 'commonService' ];

	function gbmReconciliationController($scope, $filter, $state, $stateParams,
			$location, gbmReconciliationService, commonService) {

		var vm = this;

		vm.showCancelConfirmation = showCancelConfirmation;
		vm.getSolDate = getSolDate;
		vm.getReconciliationDetails = getReconciliationDetails;
		vm.reconcileWithTransaction = reconcileWithTransaction;
		vm.getGbmTranIdModal = getGbmTranIdModal;
		vm.goPreviousState = goPreviousState;
		vm.cancel = cancel;
		vm.nextState = '';
		vm.showTransactionSync = false;

		vm.goNextState = function(stateObj) {
            $state.go(vm.nextState, {
                obj: stateObj
            })
        }


        if ($state.current.name == 'apy_gbm_reconciliation.list') {
            vm.nextState = 'apy_gbm_reconciliation.list_detail'
        } else if ($state.current.name == 'ppf_gbm_reconciliation.list') {
            vm.nextState = 'ppf_gbm_reconciliation.list_detail'
        } else if ($state.current.name == 'scss_gbm_reconciliation.list') {
            vm.nextState = 'scss_gbm_reconciliation.list_detail'
        }else if ($state.current.name == 'ssa_gbm_reconciliation.list') {
            vm.nextState = 'ssa_gbm_reconciliation.list_detail'
        }else if ($state.current.name == 'gst_gbm_reconciliation.list') {
            vm.nextState = 'gst_gbm_reconciliation.list_detail'
        }else if ($state.current.name == 'kvp_gbm_reconciliation.list') {
            vm.nextState = 'kvp_gbm_reconciliation.list_detail'
        }else if ($state.current.name == 'pension_gbm_reconciliation.list') {
            vm.nextState = 'pension_gbm_reconciliation.list_detail'
        }else if ($state.current.name == 'st_gbm_reconciliation.list') {
            vm.nextState = 'st_gbm_reconciliation.list_detail'
        }else if ($state.current.name == 'cbdt_gbm_reconciliation.list') {
            vm.nextState = 'cbdt_gbm_reconciliation.list_detail'
        }


         else if ($state.current.name == 'apy_gbm_reconciliation.list_detail' ||
            $state.current.name == 'ppf_gbm_reconciliation.list_detail' ||
            $state.current.name == 'scss_gbm_reconciliation.list_detail' ||
            $state.current.name == 'ssa_gbm_reconciliation.list_detail'||
            $state.current.name == 'gst_gbm_reconciliation.list_detail' ||
            $state.current.name == 'kvp_gbm_reconciliation.list_detail' ||
            $state.current.name == 'pension_gbm_reconciliation.list_detail' ||
            $state.current.name == 'st_gbm_reconciliation.list_detail' ||
            $state.current.name == 'cbdt_gbm_reconciliation.list_detail')
        {
            if($stateParams.obj){
                vm.tranDate = $filter('date') ($stateParams.obj.tranDate, 'dd-MMM-yyyy')
                getTransactionDetails();
            }else {
                if ($state.current.name == 'apy_gbm_reconciliation.list_detail') {
                    $state.go('apy_gbm_reconciliation.list');
                } else if ($state.current.name == 'ppf_gbm_reconciliation.list_detail') {
                    $state.go('ppf_gbm_reconciliation.list');
                } else if ($state.current.name == 'scss_gbm_reconciliation.list_detail') {
                    $state.go('scss_gbm_reconciliation.list');
                }else if ($state.current.name == 'ssa_gbm_reconciliation.list_detail') {
                    $state.go('ssa_gbm_reconciliation.list');
                }else if ($state.current.name == 'gst_gbm_reconciliation.list_detail') {
                    $state.go('gst_gbm_reconciliation.list');
                }else if ($state.current.name == 'kvp_gbm_reconciliation.list_detail') {
                    $state.go('kvp_gbm_reconciliation.list');
                }else if ($state.current.name == 'pension_gbm_reconciliation.list_detail') {
                    $state.go('pension_gbm_reconciliation.list');
                }else if ($state.current.name == 'st_gbm_reconciliation.list_detail') {
                    $state.go('st_gbm_reconciliation.list');
                }else if ($state.current.name == 'cbdt_gbm_reconciliation.list_detail') {
                    $state.go('cbdt_gbm_reconciliation.list');
                }
            }
        }


        function goPreviousState() {
            if ($state.current.name == 'apy_gbm_reconciliation.list_detail') {
                $state.go('apy_gbm_reconciliation.list');
            } else if ($state.current.name == 'ppf_gbm_reconciliation.list_detail') {
                $state.go('ppf_gbm_reconciliation.list');
            } else if ($state.current.name == 'scss_gbm_reconciliation.list_detail') {
                $state.go('scss_gbm_reconciliation.list');
            }else if ($state.current.name == 'ssa_gbm_reconciliation.list_detail') {
                $state.go('ssa_gbm_reconciliation.list');
            }else if ($state.current.name == 'gst_gbm_reconciliation.list_detail') {
                $state.go('gst_gbm_reconciliation.list');
            }else if ($state.current.name == 'kvp_gbm_reconciliation.list_detail') {
                $state.go('kvp_gbm_reconciliation.list');
            }else if ($state.current.name == 'pension_gbm_reconciliation.list_detail') {
                $state.go('pension_gbm_reconciliation.list');
            }else if ($state.current.name == 'st_gbm_reconciliation.list_detail') {
                $state.go('st_gbm_reconciliation.list');
            }else if ($state.current.name == 'cbdt_gbm_reconciliation.list_detail') {
                $state.go('cbdt_gbm_reconciliation.list');
            }
        }

        function cancel() {
            if ($state.current.name == 'apy_gbm_reconciliation.list') {
                location.href= '#!/apy_admin_functionality';
            } else if ($state.current.name == 'ppf_gbm_reconciliation.list') {
                location.href= '#!/ppf_admin_functionality';
            } else if ($state.current.name == 'scss_gbm_reconciliation.list') {
                location.href= '#!/scss_admin_functionality';
            }else if ($state.current.name == 'ssa_gbm_reconciliation.list') {
                location.href= '#!/ssa_admin_functionality';
            }else if ($state.current.name == 'gst_gbm_reconciliation.list') {
                location.href= '#!/gst_admin_functionality';
            }else if ($state.current.name == 'kvp_gbm_reconciliation.list') {
                location.href= '#!/kvp_admin_functionality';
            }else if ($state.current.name == 'pension_gbm_reconciliation.list') {
                location.href= '#!/pension_admin_functionality';
            }else if ($state.current.name == 'st_gbm_reconciliation.list') {
                location.href= '#!/statetax_admin_functionality';
            }else if ($state.current.name == 'cbdt_gbm_reconciliation.list') {
                location.href= '#!/cbdt_admin_functionality';
            }
        }

        function getSolDate(){
           commonService.getSolDate().then(function(response){
              vm.loader=false;
              vm.tranDate = new Date(response.data.solDate);
              vm.dateOptions = {
                minDate: vm.tranDate,
                maxDate: vm.tranDate
              }
            },function(error){
                handleErrorResponse(error);
                vm.loader = false;
             });
        }

        function getTransactionDetails(){
            vm.loader = true;
            gbmReconciliationService.getTransactionDetails(vm.tranDate)
            .then(
                function(response) {
                    vm.loader = false;
                    vm.transactionSyncList = response.data.result;
                }, function(error){
                  handleErrorResponse(error);
                  $state.go('gbm_reconciliation.list');
                  vm.loader = false;
                  goPreviousState();
               }
            );
        }


        function getReconciliationDetails(gbmTranId, cbsTranId){
            vm.loader = true;
            vm.gbmTranId = gbmTranId;
            vm.cbsTranId = cbsTranId;
            gbmReconciliationService.getReconciliationDetails(gbmTranId, cbsTranId, vm.tranDate)
            .then(
                function(response) {
                    vm.loader = false;
		            vm.showTransactionSync = true;
                    vm.reconcileTransaction = response.data.result;
                }, function(error){
                  handleErrorResponse(error);
                  goPreviousState();
                  vm.loader = false;
               }
            );
        }

        function reconcileWithTransaction(){
            vm.loader = true;
            angular.forEach(vm.reconcileTransaction, function(item){
                if(item.deleteStatus == 'Y' || item.cbsAmt == item.tranAmt){
                    item.cbsAmt = null;
                }
            })
            var requestData = {
                'gbmTranId': vm.gbmTranId,
                'cbsTranId': vm.cbsTranId,
                'tranDate': vm.tranDate,
                'tranList': vm.reconcileTransaction
            };
            gbmReconciliationService.reconcileWithTransaction(requestData)
            .then(
                function(response) {
                    vm.loader = false;
                    var msg = 'GBM Tran id ' +vm.gbmTranId+ ' reconciled successfully '
                    Swal.fire("Success", msg, "success");
                    goPreviousState();
                }, function(error){
                  handleErrorResponse(error);
                    goPreviousState();
                  vm.loader = false;
               }
            );
        }

		function getGbmTranIdModal(data){
             vm.transactionDetails = data;
             var requestData = {
                  gbmTranId: data.gbmTranId,
                  tranDate: vm.tranDate
             }
             gbmReconciliationService.getGbmTranIdModal(requestData).then(function(response){
                     vm.loader = false;
                     angular.element('#gbmTranIdModal').modal('toggle');
                     vm.transIdList = response.data.result;
                 }, function(error){
                   handleErrorResponse(error);
                   vm.loader = false;
             })
		}

         // Cancel Confirmation
        function showCancelConfirmation(state) {
//            Swal.fire({
//                title : "Confirm Cancel",
//                text : "Do you really want to cancel?",
//                type : "warning",
//                showCancelButton : true,
//                cancelButtonClass : "btn-cancel",
//                confirmButtonClass : "btn-danger",
//                confirmButtonText : "Yes",
//                cancelButtonText : "No",
//                closeOnConfirm : true
//            }, function() {
Swal.fire({
                               title : "Confirm Cancel",
                              text : "Do you really want to cancel?",
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
                $state.go(state);

          }  });
        }


	}
})();

