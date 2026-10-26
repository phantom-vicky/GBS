(function () {

	'use strict';

	angular.module('gbmUltraApp').controller('rbiBondCentralizedEodController',
		rbiBondCentralizedEodController);


	rbiBondCentralizedEodController.$inject = ['$scope', '$state', '$stateParams', '$timeout', '$rootScope', 'rbiBondCentralizedEodService', 'commonService'];

	function rbiBondCentralizedEodController($scope, $state, $stateParams, $timeout,
		$rootScope,rbiBondCentralizedEodService , commonService) {

		var vm = this;
		vm.levelCode = '';
		vm.selectedCount = 0;

		vm.startEodProcess = startEodProcess;
		vm.getMasterSolDate = getMasterSolDate;
		vm.navigateToNextState = navigateToNextState;
		vm.navigateToPreviousState = navigateToPreviousState;
		vm.getBranchId = getBranchId;
		//vm.getDate = getDate;
		vm.getLevelName = getLevelName;
		vm.optionToggled = optionToggled;
		vm.selectInputVal = selectInputVal;
		vm.timeValidation = timeValidation;

		function getParametersFromState() {
			if ($state.current.name == 'rbibond_centralized_eod.inquiry') {
				vm.getMasterSolDate();
			} else if ($state.current.name == 'rbibond_centralized_eod.inquiry_detail') {
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
				vm.startEodProcess();

			}
		}
		getParametersFromState();

		function getMasterSolDate() {
			vm.loader = true;
			var requestData = {}
			rbiBondCentralizedEodService.getGbmSolDate(requestData).then(
				function (response) {
					vm.loader = false;
					vm.masterSolDate = response.data.result.eodDate;
				},
				function (error) {
					vm.loader = false;
					handleErrorResponse(error);
					 location.href = "#!/dc_rbi_bond_menu"

				}
			)
		}
		function timeValidation(){
             vm.loader = true;
            rbiBondCentralizedEodService.getTimeValidation().then(function (response) {
                        vm.loader = false;
                    }, function (error) {
                        vm.loader = false;
                        handleErrorResponse(error);
                        $state.go('dc_rbi_bond_menu')
                    })
        }

//		function startEodProcess() {
//		                if(vm.branchLevelName==1){
//                		vm.blevelName="LINK CELL";
//                		}
//                		else{
//                		vm.blevelName="COLLECTING BRANCH";
//                		}
//			swal({
//				title: "Are you sure?",
//				text: "Do you want to initiate ' "+vm.blevelName+" EOD ' for " +vm.masterSolDate+" ?" ,
//				type: "warning",
//				showCancelButton: true,
//				cancelButtonClass: "btn-cancel",
//				confirmButtonClass: "btn-primary",
//				confirmButtonText: "Yes",
//				closeOnConfirm: false
//			}, function (result) {
//				Swal.fire.close();
//				if(result == false){
//				$state.go('rbibond_centralized_eod.inquiry');
//				}else {
//				vm.loader = true
//                				vm.selectedFilesList = [];
//                				if(!vm.branchId){
//                				angular.forEach(vm.branchIdList, function (item) {
//
//                                 vm.selectedFilesList.push({
//                                 branchId: item.branchId
//                                 })
//
//                                 })
//                				}
//                                else{
//
//                                angular.forEach(vm.branchIdList, function (item) {
//                                if (item.selected) {
//                                     vm.selectedFilesList.push({
//                                      branchId: item.branchId
//                                      })
//                                    }
//                                   })
//
//                                }
//                				var requestData = {
//                					'levelName': vm.branchLevelName,
//                					'eodDate': moment(vm.masterSolDate, "DD/MM/YYYY").format("DD-MMM-YYYY"),
//                					'centalizedEodBodDTOList': vm.selectedFilesList,
//                					'typeFlag' : 'ACC'
//                				}
//                				rbiBondCentralizedEodService.startEodProcess(requestData).then(function (response) {
//                					vm.loader = false
//                					vm.processingPage = true;
////                                    vm.eodResponseModel = response.data.result;
//		         	vm.status = response.data.result;
//					vm.messageSucceed = response.data.message;
//
//                				}, function (error) {
//                					vm.loader = false
//                					  $timeout(function(){
//                                      handleErrorResponse(error);
//                                      }, 250);
//                					vm.processingPage = true;
//                					vm.errorMessage = error.data.message;
//                					vm.navigateToPreviousState();
//                				});
//				}
//
//			})
//		}




function startEodProcess() {
    if (vm.branchLevelName == 1) {
        vm.blevelName = "LINK CELL";
    } else {
        vm.blevelName = "COLLECTING BRANCH";
    }

    Swal.fire({
        title: "Are you sure?",
        text: "Do you want to initiate '" + vm.blevelName + " EOD' for " + vm.masterSolDate + "?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Yes",
        cancelButtonText: "Cancel",
        customClass: {
            confirmButton: "btn btn-primary",
            cancelButton: "btn btn-cancel"
        },
        buttonsStyling: false
    }).then((result) => {
        if (!result.isConfirmed) {
            $state.go('rbibond_centralized_eod.inquiry');
            return;
        }

        vm.loader = true;
        vm.selectedFilesList = [];

        if (!vm.branchId) {
            angular.forEach(vm.branchIdList, function (item) {
                vm.selectedFilesList.push({
                    branchId: item.branchId
                });
            });
        } else {
            angular.forEach(vm.branchIdList, function (item) {
                if (item.selected) {
                    vm.selectedFilesList.push({
                        branchId: item.branchId
                    });
                }
            });
        }

        var requestData = {
            levelName: vm.branchLevelName,
            eodDate: moment(vm.masterSolDate, "DD/MM/YYYY").format("DD-MMM-YYYY"),
            centalizedEodBodDTOList: vm.selectedFilesList,
            typeFlag: 'ACC'
        };

        rbiBondCentralizedEodService.startEodProcess(requestData).then(function (response) {
            vm.loader = false;
            vm.processingPage = true;
            vm.status = response.data.result;
            vm.messageSucceed = response.data.message;
        }, function (error) {
            vm.loader = false;
            $timeout(function () {
                handleErrorResponse(error);
            }, 250);
            vm.processingPage = true;
            vm.errorMessage = error.data.message;
            vm.navigateToPreviousState();
        });
    });
}

		function navigateToNextState() {

		if(!vm.branchId){

		    			vm.loader = true;
            			vm.searchTreasuryId = '';

            			var requestData = {
            			    'eodDate': moment(vm.masterSolDate, "DD/MM/YYYY").format("DD-MMM-YYYY"),
            				'levelName': vm.branchLevelName
            			};

            			rbiBondCentralizedEodService.getBranchId(requestData).then(function (response) {
            				vm.loader = false;
            				vm.branchIdList = response.data.result.branchCode;
for(var i=0;i<vm.branchIdList.length;i++)
{

}
                            $state.go('rbibond_centralized_eod.inquiry_detail', {
                                obj: {
                                    masterSolDate: vm.masterSolDate,
                                    moduleName: vm.moduleName,
                                    branchLevelName: vm.branchLevelName,
                                    branchId: vm.branchId,
                                    branchId: vm.branchId,
                                    branchIdList: vm.branchIdList
                                }
                            });

            			}, function (error) {
            				vm.loader = false;
            				handleErrorResponse(error);
            				location.href= '#!/dc_rbi_bond_menu';

            			});
		}else{
		   			$state.go('rbibond_centralized_eod.inquiry_detail', {
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
			$state.go('rbibond_centralized_eod.inquiry');
		}

		function getBranchId(flag) {
			vm.loader = true;
			vm.searchTreasuryId = '';

			var requestData = {
			    'eodDate': moment(vm.masterSolDate, "DD/MM/YYYY").format("DD-MMM-YYYY"),
				'levelName': vm.branchLevelName
			}

			rbiBondCentralizedEodService.getBranchId(requestData).then(function (response) {
				vm.loader = false;
				vm.branchIdList = response.data.result.branchCode;
				if(flag!='F'){
				angular.element('#treasurySearcher').modal('toggle');
				} else {
				vm.optionToggled('ALLBRANCH');
				}

			}, function (error) {
				vm.loader = false;
				handleErrorResponse(error);
				$state.go('rbibond_centralized_eod.inquiry');
			});
		}

		function getLevelName() {
			vm.loader = true;
			vm.searchTreasuryId = '';

			var requestData = {
			'eodDate': moment(vm.masterSolDate, "DD/MM/YYYY").format("DD-MMM-YYYY")
			}

			rbiBondCentralizedEodService.getBranchId(requestData).then(function (response) {
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

		 function navigateToPreviousState() {
              $state.go('rbibond_centralized_eod.inquiry');
            }

	}
})();