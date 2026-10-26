(function () {

	'use strict';

	angular.module('gbmUltraApp').controller('efreightCentralizedBodController',
		efreightCentralizedBodController);


	efreightCentralizedBodController.$inject = ['$scope', '$state', '$stateParams', '$timeout', '$rootScope', 'rbiBondCentralizedBodService', 'commonService'];

	function efreightCentralizedBodController($scope, $state, $stateParams, $timeout,
		$rootScope, rbiBondCentralizedBodService, commonService) {

		var vm = this;
		vm.levelCode = '';
		vm.selectedCount = 0;

		vm.startBodProcess = startBodProcess;
		vm.getMasterSolDate = getMasterSolDate;
		vm.navigateToNextState = navigateToNextState;
		vm.navigateToPreviousState = navigateToPreviousState;
		vm.getBranchId = getBranchId;
		//vm.getDate = getDate;
		vm.getLevelName = getLevelName;
		vm.optionToggled = optionToggled;
		vm.selectInputVal = selectInputVal;

		function getParametersFromState() {
			if ($state.current.name == 'rbibond_centralized_bod.inquiry') {
				vm.getMasterSolDate();
			} else if ($state.current.name == 'rbibond_centralized_bod.inquiry_detail') {
				if ($stateParams.obj && $stateParams.obj.masterSolDate) {
					vm.masterSolDate = $stateParams.obj.masterSolDate
					vm.moduleName = $stateParams.obj.moduleName
					vm.branchLevelName = $stateParams.obj.branchLevelName
					vm.branchId = $stateParams.obj.branchId
					vm.branchIdList = $stateParams.obj.branchIdList
				} else {
					vm.navigateToPreviousState();
				}
				if(!vm.branchIdList){
                     vm.getBranchId('F');
                }
				vm.startBodProcess();
			}
		}
		getParametersFromState();

		function getMasterSolDate() {
			vm.loader = true;
			var requestData = {}
			rbiBondCentralizedBodService.getGbmSolDate(requestData).then(
				function (response) {
					vm.loader = false;
					vm.masterSolDate = response.data.result.bodDate;
				},
				function (error) {
					vm.loader = false;
					handleErrorResponse(error);
					$state.go('dc_rbi_bond_menu');
				}
			)
		}

//		function startBodProcess() {
//		if(vm.branchLevelName==1){
//                        		vm.blevelName="LINK CELL";
//                        		}
//                        		if(vm.branchLevelName==2){
//                        		vm.blevelName="COLLECTING BRANCH";
//                        		}
//			swal({
//				title: "Are you sure?",
//				text: "Do you want to initiate ' "+vm.blevelName +" BOD ' for " + vm.masterSolDate+" ?",
//				type: "warning",
//				showCancelButton: true,
//				cancelButtonClass: "btn-cancel",
//				confirmButtonClass: "btn-primary",
//				confirmButtonText: "Yes",
//				closeOnConfirm: false
//			}, function (result) {
//			Swal.fire.close();
//			if(result == false){
//			$state.go('rbibond_centralized_bod.inquiry');
//			}else {
//			vm.loader = true
//            				vm.selectedFilesList = [];
//            				angular.forEach(vm.branchIdList, function (item) {
//            					//if (item.selected) {
//            						vm.selectedFilesList.push({
//            							branchId: item.branchId
//            						})
//            					//}
//            				})
//            				var requestData = {
//            					'levelName': vm.branchLevelName,
//            					'bodDate': moment(vm.masterSolDate, "DD/MM/YYYY").format("DD-MMM-YYYY"),
//            					'centalizedEodBodDTOList': vm.selectedFilesList
//            				}
//            				rbiBondCentralizedBodService.startBodProcess(requestData).then(function (response) {
//            					vm.loader = false
//            					vm.processingPage = true;
//            					//vm.bodStatusList = response.data.result.bodStatusList;
//            					vm.status = response.data.result;
//            					vm.messageSucceed = response.data.message;
//            				}, function (error) {
//            					vm.loader = false
//            					vm.processingPage = true;
//                                vm.errorMessage = error.data.message;
//                               	handleErrorResponse(error);
//            //					vm.navigateToPreviousState();
//            				});
//			}
//
//
//			})
//		}



function startBodProcess() {
    if (vm.branchLevelName == 1) {
        vm.blevelName = "LINK CELL";
    }
    if (vm.branchLevelName == 2) {
        vm.blevelName = "COLLECTING BRANCH";
    }

    Swal.fire({
        title: "Are you sure?",
        text: "Do you want to initiate '" + vm.blevelName + " BOD' for " + vm.masterSolDate + " ?",
        icon: "warning",
        showCancelButton: true,
        cancelButtonText: "Cancel",
        confirmButtonText: "Yes",
        customClass: {
            cancelButton: "btn-cancel",
            confirmButton: "btn-primary"
        },
        buttonsStyling: false
    }).then((result) => {
        if (!result.isConfirmed) {
            $state.go('rbibond_centralized_bod.inquiry');
        } else {
            vm.loader = true;
            vm.selectedFilesList = [];

            angular.forEach(vm.branchIdList, function (item) {
                vm.selectedFilesList.push({
                    branchId: item.branchId
                });
            });

            var requestData = {
                'levelName': vm.branchLevelName,
                'bodDate': moment(vm.masterSolDate, "DD/MM/YYYY").format("DD-MMM-YYYY"),
                'centalizedEodBodDTOList': vm.selectedFilesList
            };

            rbiBondCentralizedBodService.startBodProcess(requestData).then(function (response) {
                vm.loader = false;
                vm.processingPage = true;
                vm.status = response.data.result;
                vm.messageSucceed = response.data.message;
            }, function (error) {
                vm.loader = false;
                vm.processingPage = true;
                vm.errorMessage = error.data.message;
                handleErrorResponse(error);
            });
        }
    });
}

		function navigateToNextState() {
		if(!vm.branchId){

					vm.loader = true;
        			vm.searchTreasuryId = '';

        			var requestData = {
        				'levelName': vm.branchLevelName,
        				'bodDate': moment(vm.masterSolDate, "DD/MM/YYYY").format("DD-MMM-YYYY")
        			}

					rbiBondCentralizedBodService.getBranchId(requestData).then(function (response) {
        				vm.loader = false;
        				vm.branchIdList = response.data.result.branchCode;

      		    			$state.go('rbibond_centralized_bod.inquiry_detail', {
                  				obj: {
                  					masterSolDate: vm.masterSolDate,
                  					moduleName: vm.moduleName,
                  					branchLevelName: vm.branchLevelName,
                  					branchId: vm.branchId,
                  					branchIdList: vm.branchIdList
                  				}
                  			});

        			}, function (error) {
        				vm.loader = false;
        				handleErrorResponse(error);
        				vm.navigateToPreviousState();
        			});




		}
		else{
			$state.go('rbibond_centralized_bod.inquiry_detail', {
				obj: {
					masterSolDate: vm.masterSolDate,
					moduleName: vm.moduleName,
					branchLevelName: vm.branchLevelName,
					branchId: vm.branchId,
					branchIdList: vm.branchIdList
				}
			});
			}
		}

		function navigateToPreviousState() {
			$state.go('rbibond_centralized_bod.inquiry');
		}

		function getBranchId(flag) {
			vm.loader = true;
			vm.searchTreasuryId = '';

			var requestData = {
				'levelName': vm.branchLevelName,
				'bodDate': moment(vm.masterSolDate, "DD/MM/YYYY").format("DD-MMM-YYYY")
			}

			rbiBondCentralizedBodService.getBranchId(requestData).then(function (response) {
				vm.loader = false;
				vm.branchIdList = response.data.result.branchCode;
				if(flag!='F'){
				angular.element('#treasurySearcher').modal('toggle');
				//vm.optionToggled('ALLBRANCH');
				} else {
				vm.optionToggled('ALLBRANCH');
				}

			}, function (error) {
				vm.loader = false;
				handleErrorResponse(error);
				vm.navigateToPreviousState();
			})
		}

		function getLevelName() {
			vm.loader = true;
			vm.searchTreasuryId = '';

			var requestData = {
			'bodDate': moment(vm.masterSolDate, "DD/MM/YYYY").format("DD-MMM-YYYY")
			}

			rbiBondCentralizedBodService.getBranchId(requestData).then(function (response) {
				vm.loader = false;
				vm.levelCodeList = response.data.result.levelCodeList;
			}, function (error) {
				vm.loader = false;
				handleErrorResponse(error);
			})
		}

		function selectInputVal() {
			if (vm.selectedCount == '0' && vm.isAllSelected == '') {
				Swal.fire("Please Select At least One SOl")
			}
		}

		function optionToggled(flag) {
			vm.isAllSelected = [];

			if (flag == 'ALL') {
				if (vm.selectedAllItem) {
					angular.forEach(vm.branchIdList, function (item) {
						item.selected = true;
						vm.branchId = "ALL";
						vm.isAllSelected.push(item.selected)
					});
				} else {
					angular.forEach(vm.branchIdList, function (item) {
						item.selected = false;
						vm.isAllSelected.push(item.selected)
					});
				}
			}else if (flag == 'ALLBRANCH') {
					angular.forEach(vm.branchIdList, function (item) {
						item.selected = true;
						vm.isAllSelected.push(item.selected)
					});
			} else {
				vm.selectedCount = 0;
				var branchId = "";
				angular.forEach(vm.branchIdList, function (item) {
					vm.isAllSelected.push(item.selected)
					if (item.selected) {
						vm.selectedCount = vm.selectedCount + 1;
						if(branchId==""){
						branchId =  item.branchId;
						}else{
						branchId = branchId + "," + item.branchId;
						}
					}
					vm.branchId = branchId;
				});

				if (vm.branchIdList.length == vm.selectedCount) {
					vm.selectedAllItem = true;
				} else {
					vm.selectedAllItem = false;
				}
			}
		}
	}
})();