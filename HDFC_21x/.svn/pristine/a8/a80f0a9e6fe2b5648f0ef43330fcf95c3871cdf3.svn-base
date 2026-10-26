(function () {

	'use strict';

	angular.module('gbmUltraApp').controller('syncForVerifiedController',
		syncForVerifiedController);

	syncForVerifiedController.$inject = ['$scope', '$filter', '$state',
		'$stateParams', '$location', 'syncForVerifiedService', 'commonService'
	];

	function syncForVerifiedController($scope, $filter, $state, $stateParams,
		$location, syncForVerifiedService, commonService) {

		var vm = this;

		vm.showCancelConfirmation = showCancelConfirmation;
		vm.getSolDate = getSolDate;
		vm.syncWithTransaction = syncWithTransaction;
		vm.getGbmTranIdModal = getGbmTranIdModal;
		vm.goPreviousState = goPreviousState;

		vm.nextState = '';
		vm.cancel = cancel;

		if ($state.current.name == 'apy_sync_for_cbs_verified_transactions.list') {
			vm.nextState = 'apy_sync_for_cbs_verified_transactions.list_detail'
		} else if ($state.current.name == 'ppf_sync_for_cbs_verified_transactions.list') {
			vm.nextState = 'ppf_sync_for_cbs_verified_transactions.list_detail'
		} else if ($state.current.name == 'scss_sync_for_cbs_verified_transactions.list') {
			vm.nextState = 'scss_sync_for_cbs_verified_transactions.list_detail'
		} else if ($state.current.name == 'ssa_sync_for_cbs_verified_transactions.list') {
			vm.nextState = 'ssa_sync_for_cbs_verified_transactions.list_detail'
		} else if ($state.current.name == 'gst_sync_for_cbs_verified_transactions.list') {
			vm.nextState = 'gst_sync_for_cbs_verified_transactions.list_detail'
		} else if ($state.current.name == 'kvp_sync_for_cbs_verified_transactions.list') {
			vm.nextState = 'kvp_sync_for_cbs_verified_transactions.list_detail'
		} else if ($state.current.name == 'st_sync_for_cbs_verified_transactions.list') {
			vm.nextState = 'st_sync_for_cbs_verified_transactions.list_detail'
		} else if ($state.current.name == 'cbdt_sync_for_cbs_verified_transactions.list') {
			vm.nextState = 'cbdt_sync_for_cbs_verified_transactions.list_detail'
		} else if ($state.current.name == 'sync_for_cbs_verified_transactions.list') {
			vm.nextState = 'sync_for_cbs_verified_transactions.list_detail'
		} else if ($state.current.name == 'apy_sync_for_cbs_verified_transactions.list_detail' ||
			$state.current.name == 'ppf_sync_for_cbs_verified_transactions.list_detail' ||
			$state.current.name == 'scss_sync_for_cbs_verified_transactions.list_detail' ||
			$state.current.name == 'ssa_sync_for_cbs_verified_transactions.list_detail' ||
			$state.current.name == 'gst_sync_for_cbs_verified_transactions.list_detail' ||
			$state.current.name == 'kvp_sync_for_cbs_verified_transactions.list_detail' ||
			$state.current.name == 'st_sync_for_cbs_verified_transactions.list_detail' ||
			$state.current.name == 'sync_for_cbs_verified_transactions.list_detail' ||
			$state.current.name == 'cbdt_sync_for_cbs_verified_transactions.list_detail') {
			if ($stateParams.obj) {
				vm.tranDate = $filter('date')($stateParams.obj.tranDate, 'dd-MMM-yyyy')
				getTransactionDetails();
			} else {
				if ($state.current.name == 'apy_sync_for_cbs_verified_transactions.list_detail') {
					$state.go('apy_sync_for_cbs_verified_transactions.list');
				} else if ($state.current.name == 'ppf_sync_for_cbs_verified_transactions.list_detail') {
					$state.go('ppf_sync_for_cbs_verified_transactions.list');
				} else if ($state.current.name == 'scss_sync_for_cbs_verified_transactions.list_detail') {
					$state.go('scss_sync_for_cbs_verified_transactions.list');
				} else if ($state.current.name == 'ssa_sync_for_cbs_verified_transactions.list_detail') {
					$state.go('ssa_sync_for_cbs_verified_transactions.list');
				} else if ($state.current.name == 'gst_sync_for_cbs_verified_transactions.list_detail') {
					$state.go('gst_sync_for_cbs_verified_transactions.list');
				} else if ($state.current.name == 'kvp_sync_for_cbs_verified_transactions.list_detail') {
					$state.go('kvp_sync_for_cbs_verified_transactions.list');
				} else if ($state.current.name == 'st_sync_for_cbs_verified_transactions.list_detail') {
					$state.go('st_sync_for_cbs_verified_transactions.list');
				} else if ($state.current.name == 'cbdt_sync_for_cbs_verified_transactions.list_detail') {
					$state.go('cbdt_sync_for_cbs_verified_transactions.list');
				} else if ($state.current.name == 'sync_for_cbs_verified_transactions.list_detail') {
					$state.go('sync_for_cbs_verified_transactions.list');
				}
			}
		}

		vm.goNextState = function (stateObj) {
			$state.go(vm.nextState, {
				obj: stateObj
			})
		}

		function goPreviousState() {
			if ($state.current.name == 'apy_sync_for_cbs_verified_transactions.list_detail') {
				$state.go('apy_sync_for_cbs_verified_transactions.list');
			} else if ($state.current.name == 'ppf_sync_for_cbs_verified_transactions.list_detail') {
				$state.go('ppf_sync_for_cbs_verified_transactions.list');
			} else if ($state.current.name == 'scss_sync_for_cbs_verified_transactions.list_detail') {
				$state.go('scss_sync_for_cbs_verified_transactions.list');
			} else if ($state.current.name == 'ssa_sync_for_cbs_verified_transactions.list_detail') {
				$state.go('ssa_sync_for_cbs_verified_transactions.list');
			} else if ($state.current.name == 'gst_sync_for_cbs_verified_transactions.list_detail') {
				$state.go('gst_sync_for_cbs_verified_transactions.list');
			} else if ($state.current.name == 'kvp_sync_for_cbs_verified_transactions.list_detail') {
				$state.go('kvp_sync_for_cbs_verified_transactions.list');
			} else if ($state.current.name == 'st_sync_for_cbs_verified_transactions.list_detail') {
				$state.go('st_sync_for_cbs_verified_transactions.list');
			} else if ($state.current.name == 'cbdt_sync_for_cbs_verified_transactions.list_detail') {
				$state.go('cbdt_sync_for_cbs_verified_transactions.list');
			} else if ($state.current.name == 'sync_for_cbs_verified_transactions.list_detail') {
				$state.go('sync_for_cbs_verified_transactions.list');
			}
		}

		function cancel() {
			if ($state.current.name == 'apy_sync_for_cbs_verified_transactions.list') {
				location.href = '#!/apy_admin_functionality';
			} else if ($state.current.name == 'ppf_sync_for_cbs_verified_transactions.list') {
				location.href = '#!/ppf_admin_functionality';
			} else if ($state.current.name == 'scss_sync_for_cbs_verified_transactions.list') {
				location.href = '#!/scss_admin_functionality';
			} else if ($state.current.name == 'ssa_sync_for_cbs_verified_transactions.list') {
				location.href = '#!/ssa_admin_functionality';
			} else if ($state.current.name == 'gst_sync_for_cbs_verified_transactions.list') {
				location.href = '#!/gst_admin_functionality';
			} else if ($state.current.name == 'kvp_sync_for_cbs_verified_transactions.list') {
				location.href = '#!/kvp_admin_functionality';
			} else if ($state.current.name == 'st_sync_for_cbs_verified_transactions.list') {
				location.href = '#!/statetax_admin_functionality';
			} else if ($state.current.name == 'cbdt_sync_for_cbs_verified_transactions.list') {
				location.href = '#!/cbdt_admin_functionality';
			} else if ($state.current.name == 'sync_for_cbs_verified_transactions.list') {
				location.href = '#!/pension_admin_functionality';
			}
		}

		function getSolDate() {
			commonService.getSolDate().then(function (response) {
				vm.loader = false;
				vm.tranDate = new Date(response.data.solDate);
				vm.dateOptions = {
					minDate: vm.tranDate,
					maxDate: vm.tranDate
				}
			}, function (error) {
				handleErrorResponse(error);
				vm.loader = false;
			});
		}

		function getTransactionDetails() {
			vm.loader = true;
			syncForVerifiedService.getTransactionDetails(vm.tranDate)
				.then(
					function (response) {
						vm.loader = false;
						vm.transactionSyncList = response.data.result;
					},
					function (error) {
						handleErrorResponse(error);
						vm.loader = false;
						goPreviousState();
					}
				);
		}


		function syncWithTransaction(gbmTranId, cbsTranId) {
			var requestData = {
				'gbmTranId': gbmTranId,
				'cbsTranId': cbsTranId
			}
			vm.loader = true;
			syncForVerifiedService.syncWithTransaction(requestData)
				.then(
					function (response) {
						vm.loader = false;
						var msg = 'GBM Tran id ' + gbmTranId + ' synced in GBM successfully'
						Swal.fire("Success", msg, "success");
						goPreviousState();
					},
					function (error) {
						handleErrorResponse(error);
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
             syncForVerifiedService.getGbmTranIdModal(requestData).then(function(response){
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