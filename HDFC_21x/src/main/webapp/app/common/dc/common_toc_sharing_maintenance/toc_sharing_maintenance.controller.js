 (function () {

	'use strict';

	angular.module('gbmUltraApp').controller('tocSharingMaintenanceController',
		tocSharingMaintenanceController);

	tocSharingMaintenanceController.$inject = ['$scope', '$filter', '$state',
		'$stateParams', '$location', 'tocSharingMaintenanceService', 'commonService'
	];

	function tocSharingMaintenanceController($scope, $filter, $state, $stateParams,
		$location, tocSharingMaintenanceService, commonService) {

		var vm = this;

		vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;


		vm.errorMsg = [];
		vm.levelDetailsTocSharingDto = [];

		vm.decimalConvert = decimalConvert;
		vm.getModuleName = getModuleName;
		vm.calculateSum = calculateSum;
		vm.submitAddForm = submitAddForm;
		vm.showCancelConfirmation = showCancelConfirmation;
		vm.dateValidationForModify = dateValidationForModify;
		vm.validateAddSelect = validateAddSelect;
		vm.getInquiryVersionTorch = getInquiryVersionTorch;
		vm.getInquirySelect = getInquirySelect;
		vm.getRoundFactList = getRoundFactList;
		vm.checkRecordExisted = checkRecordExisted;
		vm.getVerifyDeleteSelect = getVerifyDeleteSelect;
		vm.getVersionEffFromDate = getVersionEffFromDate;
		vm.submitDeleteForm = submitDeleteForm;
		vm.submitVerifyForm = submitVerifyForm;
		vm.getModifySelect = getModifySelect;
		vm.getVersionEffFromDateForModify = getVersionEffFromDateForModify;
		vm.dateValidationForModify = dateValidationForModify;
		vm.calculateSumForModify = calculateSumForModify;
		vm.decimalConvertForModify = decimalConvertForModify;
		vm.modifySubmit = modifySubmit;
		vm.formatDate = formatDate;
		/* vm.getSubCodeList = getSubCodeList;

		 getSubCodeList();*/
		getModuleName();
		getRoundFactList();
		
        vm.nextState = '';

        vm.goNextState = goNextState;
        vm.goPreviousState = goPreviousState;
        vm.cancel = cancel;
        vm.successState = successState;
        vm.staySameInState = staySameInState;


		if ($state.current.name == 'kvp_toc_sharing_maintenance.add' || $state.current.name == 'kvp_toc_sharing_maintenance.modify' || $state.current.name == 'kvp_toc_sharing_maintenance.verify' || $state.current.name == 'kvp_toc_sharing_maintenance.delete' || $state.current.name == 'kvp_toc_sharing_maintenance.inquiry') {
			vm.screenModule = 'KVP'
			if ($state.current.name == 'kvp_toc_sharing_maintenance.modify')
				vm.nextState = 'kvp_toc_sharing_maintenance.modify_detail';
			else if ($state.current.name == 'kvp_toc_sharing_maintenance.verify')
				vm.nextState = 'kvp_toc_sharing_maintenance.verify_detail';
			else if ($state.current.name == 'kvp_toc_sharing_maintenance.delete')
				vm.nextState = 'kvp_toc_sharing_maintenance.delete_detail';
			else if ($state.current.name == 'kvp_toc_sharing_maintenance.inquiry')
				vm.nextState = 'kvp_toc_sharing_maintenance.inquiry_detail';
			else if ($state.current.name == 'kvp_toc_sharing_maintenance.add')
				vm.nextState = 'kvp_toc_sharing_maintenance.add_detail';            
		} else if ($state.current.name == 'ssa_toc_sharing_maintenance.add' || $state.current.name == 'ssa_toc_sharing_maintenance.modify' || $state.current.name == 'ssa_toc_sharing_maintenance.verify' || $state.current.name == 'ssa_toc_sharing_maintenance.delete' || $state.current.name == 'ssa_toc_sharing_maintenance.inquiry') {
			vm.screenModule = 'SSA'
			if ($state.current.name == 'ssa_toc_sharing_maintenance.modify')
				vm.nextState = 'ssa_toc_sharing_maintenance.modify_detail';
			else if ($state.current.name == 'ssa_toc_sharing_maintenance.verify')
				vm.nextState = 'ssa_toc_sharing_maintenance.verify_detail';
			else if ($state.current.name == 'ssa_toc_sharing_maintenance.delete')
				vm.nextState = 'ssa_toc_sharing_maintenance.delete_detail';
			else if ($state.current.name == 'ssa_toc_sharing_maintenance.inquiry')
				vm.nextState = 'ssa_toc_sharing_maintenance.inquiry_detail';
			else if ($state.current.name == 'ssa_toc_sharing_maintenance.add')
				vm.nextState = 'ssa_toc_sharing_maintenance.add_detail';            
		} else if ($state.current.name == 'ppf_toc_sharing_maintenance.add' || $state.current.name == 'ppf_toc_sharing_maintenance.modify' || $state.current.name == 'ppf_toc_sharing_maintenance.verify' || $state.current.name == 'ppf_toc_sharing_maintenance.delete' || $state.current.name == 'ppf_toc_sharing_maintenance.inquiry') {
			vm.screenModule = 'PPF'
			if ($state.current.name == 'ppf_toc_sharing_maintenance.modify')
				vm.nextState = 'ppf_toc_sharing_maintenance.modify_detail';
			else if ($state.current.name == 'ppf_toc_sharing_maintenance.verify')
				vm.nextState = 'ppf_toc_sharing_maintenance.verify_detail';
			else if ($state.current.name == 'ppf_toc_sharing_maintenance.delete')
				vm.nextState = 'ppf_toc_sharing_maintenance.delete_detail';
			else if ($state.current.name == 'ppf_toc_sharing_maintenance.inquiry')
				vm.nextState = 'ppf_toc_sharing_maintenance.inquiry_detail';
			else if ($state.current.name == 'ppf_toc_sharing_maintenance.add')
				vm.nextState = 'ppf_toc_sharing_maintenance.add_detail';            
		} else if ($state.current.name == 'gst_toc_sharing_maintenance.add' || $state.current.name == 'gst_toc_sharing_maintenance.modify' || $state.current.name == 'gst_toc_sharing_maintenance.verify' || $state.current.name == 'gst_toc_sharing_maintenance.delete' || $state.current.name == 'gst_toc_sharing_maintenance.inquiry') {
			vm.screenModule = 'GST'
			if ($state.current.name == 'gst_toc_sharing_maintenance.modify')
				vm.nextState = 'gst_toc_sharing_maintenance.modify_detail';
			else if ($state.current.name == 'gst_toc_sharing_maintenance.verify')
				vm.nextState = 'gst_toc_sharing_maintenance.verify_detail';
			else if ($state.current.name == 'gst_toc_sharing_maintenance.delete')
				vm.nextState = 'gst_toc_sharing_maintenance.delete_detail';
			else if ($state.current.name == 'gst_toc_sharing_maintenance.inquiry')
				vm.nextState = 'gst_toc_sharing_maintenance.inquiry_detail';
			else if ($state.current.name == 'gst_toc_sharing_maintenance.add')
				vm.nextState = 'gst_toc_sharing_maintenance.add_detail';
		} else if ($state.current.name == 'cbdt_toc_sharing_maintenance.add' || $state.current.name == 'cbdt_toc_sharing_maintenance.modify' || $state.current.name == 'cbdt_toc_sharing_maintenance.verify' || $state.current.name == 'cbdt_toc_sharing_maintenance.delete' || $state.current.name == 'cbdt_toc_sharing_maintenance.inquiry') {
			vm.screenModule = 'CBDT'
			if ($state.current.name == 'cbdt_toc_sharing_maintenance.modify')
				vm.nextState = 'cbdt_toc_sharing_maintenance.modify_detail';
			else if ($state.current.name == 'cbdt_toc_sharing_maintenance.verify')
				vm.nextState = 'cbdt_toc_sharing_maintenance.verify_detail';
			else if ($state.current.name == 'cbdt_toc_sharing_maintenance.delete')
				vm.nextState = 'cbdt_toc_sharing_maintenance.delete_detail';
			else if ($state.current.name == 'cbdt_toc_sharing_maintenance.inquiry')
				vm.nextState = 'cbdt_toc_sharing_maintenance.inquiry_detail';
			else if ($state.current.name == 'cbdt_toc_sharing_maintenance.add')
				vm.nextState = 'cbdt_toc_sharing_maintenance.add_detail';
		}else if ($state.current.name == 'kvp_toc_sharing_maintenance.add_detail' || $state.current.name == 'ppf_toc_sharing_maintenance.add_detail' || $state.current.name == 'ssa_toc_sharing_maintenance.add_detail' || $state.current.name == 'gst_toc_sharing_maintenance.add_detail' || $state.current.name == 'cbdt_toc_sharing_maintenance.add_detail') {
			if ($stateParams.obj) {
				vm.moduleId = $stateParams.obj.moduleId;
				vm.versionNo = $stateParams.obj.versionNo;
				vm.screenModule = $stateParams.obj.screenModule;
				vm.effFromDate = $stateParams.obj.effFromDate;
				if (vm.moduleId && vm.effFromDate) {
					validateAddSelect();
				} else {
                    if($state.current.name == 'kvp_toc_sharing_maintenance.add_detail')
                        $state.go('kvp_toc_sharing_maintenance.add')
                    else if($state.current.name == 'ppf_toc_sharing_maintenance.add_detail')
                        $state.go('ppf_toc_sharing_maintenance.add')
                    else if($state.current.name == 'ssa_toc_sharing_maintenance.add_detail')
                        $state.go('ssa_toc_sharing_maintenance.add')
                    else if($state.current.name == 'gst_toc_sharing_maintenance.add_detail')
                        $state.go('gst_toc_sharing_maintenance.add')
                    else if($state.current.name == 'cbdt_toc_sharing_maintenance.add_detail')
                        $state.go('cbdt_toc_sharing_maintenance.add')
				}
			} else {
                if($state.current.name == 'kvp_toc_sharing_maintenance.add_detail')
                    $state.go('kvp_toc_sharing_maintenance.add')
                else if($state.current.name == 'ppf_toc_sharing_maintenance.add_detail')
                    $state.go('ppf_toc_sharing_maintenance.add')
                else if($state.current.name == 'ssa_toc_sharing_maintenance.add_detail')
                    $state.go('ssa_toc_sharing_maintenance.add')
                else if($state.current.name == 'gst_toc_sharing_maintenance.add_detail')
                    $state.go('gst_toc_sharing_maintenance.add')
                else if($state.current.name == 'cbdt_toc_sharing_maintenance.add_detail')
                    $state.go('cbdt_toc_sharing_maintenance.add')
			}
		} else if ($state.current.name == 'kvp_toc_sharing_maintenance.modify_detail' || $state.current.name == 'ppf_toc_sharing_maintenance.modify_detail' || $state.current.name == 'ssa_toc_sharing_maintenance.modify_detail' || $state.current.name == 'gst_toc_sharing_maintenance.modify_detail' || $state.current.name == 'cbdt_toc_sharing_maintenance.modify_detail') {
			if ($stateParams.obj) {
				vm.moduleId = $stateParams.obj.moduleId;
				vm.versionNo = $stateParams.obj.versionNo;
				vm.effeFromDate = $stateParams.obj.effeFromDate;
				vm.screenModule = $stateParams.obj.screenModule;
				getModifySelect();
			} else {
                if($state.current.name == 'kvp_toc_sharing_maintenance.modify_detail')
                    $state.go('kvp_toc_sharing_maintenance.modify')
                else if($state.current.name == 'ppf_toc_sharing_maintenance.modify_detail')
                    $state.go('ppf_toc_sharing_maintenance.modify')
                else if($state.current.name == 'ssa_toc_sharing_maintenance.modify_detail')
                    $state.go('ssa_toc_sharing_maintenance.modify')
                else if($state.current.name == 'gst_toc_sharing_maintenance.modify_detail')
                    $state.go('gst_toc_sharing_maintenance.modify')
                else if($state.current.name == 'cbdt_toc_sharing_maintenance.modify_detail')
                    $state.go('cbdt_toc_sharing_maintenance.modify')
			}
		} else if ($state.current.name == 'kvp_toc_sharing_maintenance.verify_detail' || $state.current.name == 'ppf_toc_sharing_maintenance.verify_detail' || $state.current.name == 'ssa_toc_sharing_maintenance.verify_detail' || $state.current.name == 'gst_toc_sharing_maintenance.verify_detail' || $state.current.name == 'cbdt_toc_sharing_maintenance.verify_detail') {
			if ($stateParams.obj) {
				vm.moduleId = $stateParams.obj.moduleId;
				vm.versionNo = $stateParams.obj.versionNo;
				vm.effeFromDate = $stateParams.obj.effeFromDate;
				vm.screenModule = $stateParams.obj.screenModule;
				getVerifyDeleteSelect();
			} else {
                if($state.current.name == 'kvp_toc_sharing_maintenance.verify_detail')
                    $state.go('kvp_toc_sharing_maintenance.verify')
                else if($state.current.name == 'ppf_toc_sharing_maintenance.verify_detail')
                    $state.go('ppf_toc_sharing_maintenance.verify')
                else if($state.current.name == 'ssa_toc_sharing_maintenance.verify_detail')
                    $state.go('ssa_toc_sharing_maintenance.verify')
                else if($state.current.name == 'gst_toc_sharing_maintenance.verify_detail')
                    $state.go('gst_toc_sharing_maintenance.verify')
                else if($state.current.name == 'cbdt_toc_sharing_maintenance.verify_detail')
                    $state.go('cbdt_toc_sharing_maintenance.verify')
			}
		} else if ($state.current.name == 'kvp_toc_sharing_maintenance.delete_detail' || $state.current.name == 'ppf_toc_sharing_maintenance.delete_detail' || $state.current.name == 'ssa_toc_sharing_maintenance.delete_detail' || $state.current.name == 'gst_toc_sharing_maintenance.delete_detail' || $state.current.name == 'cbdt_toc_sharing_maintenance.delete_detail') {
			if ($stateParams.obj) {
				vm.moduleId = $stateParams.obj.moduleId;
				vm.versionNo = $stateParams.obj.versionNo;
				vm.effeFromDate = $stateParams.obj.effeFromDate;
				vm.screenModule = $stateParams.obj.screenModule;
				getVerifyDeleteSelect();
			} else {
                if($state.current.name == 'kvp_toc_sharing_maintenance.delete_detail')
                    $state.go('kvp_toc_sharing_maintenance.delete')
                else if($state.current.name == 'ppf_toc_sharing_maintenance.delete_detail')
                    $state.go('ppf_toc_sharing_maintenance.delete')
                else if($state.current.name == 'ssa_toc_sharing_maintenance.delete_detail')
                    $state.go('ssa_toc_sharing_maintenance.delete')
                else if($state.current.name == 'gst_toc_sharing_maintenance.delete_detail')
                    $state.go('gst_toc_sharing_maintenance.delete')
                else if($state.current.name == 'cbdt_toc_sharing_maintenance.delete_detail')
                    $state.go('cbdt_toc_sharing_maintenance.delete')
			}
		} else if ($state.current.name == 'kvp_toc_sharing_maintenance.inquiry_detail' || $state.current.name == 'ppf_toc_sharing_maintenance.inquiry_detail' || $state.current.name == 'ssa_toc_sharing_maintenance.inquiry_detail' || $state.current.name == 'gst_toc_sharing_maintenance.inquiry_detail' || $state.current.name == 'cbdt_toc_sharing_maintenance.inquiry_detail') {
			if ($stateParams.obj) {
				vm.moduleId = $stateParams.obj.moduleId;
				vm.versionNo = $stateParams.obj.versionNo;
				vm.effeFromDate = $stateParams.obj.effeFromDate;
				vm.screenModule = $stateParams.obj.screenModule;

				getInquirySelect();
			} else {
                if($state.current.name == 'kvp_toc_sharing_maintenance.inquiry_detail')
                    $state.go('kvp_toc_sharing_maintenance.inquiry')
                else if($state.current.name == 'ppf_toc_sharing_maintenance.inquiry_detail')
                    $state.go('ppf_toc_sharing_maintenance.inquiry')
                else if($state.current.name == 'ssa_toc_sharing_maintenance.inquiry_detail')
                    $state.go('ssa_toc_sharing_maintenance.inquiry')
                else if($state.current.name == 'gst_toc_sharing_maintenance.inquiry_detail')
                    $state.go('gst_toc_sharing_maintenance.inquiry')
                else if($state.current.name == 'cbdt_toc_sharing_maintenance.inquiry_detail')
                    $state.go('cbdt_toc_sharing_maintenance.inquiry')
			}
		}
		//Get Module Name
		function getModuleName() {
			vm.referenceId = 'MOD_FOR_INT';
			var requestData = {
				'referenceId': vm.referenceId
			}
			commonService.getModuleNameDetails(requestData).then(function (response) {
				vm.moduleNameList = response.data.result;
				vm.moduleNameDetailsLabelValue = [];
				vm.moduleList = []
				angular.forEach(vm.moduleNameList, function (item) {
				    if(vm.screenModule == 'KVP'){
				        if(item.subCode == '0502'){
                            vm.moduleNameDetailsLabelValue.push({
                                label: item.subCodeDescription,
                                value: item.subCode
                            })
                            vm.moduleList.push(item);
				        }
				    }else if(vm.screenModule == 'PPF'){
				        if(item.subCode == '0900'){
                            vm.moduleNameDetailsLabelValue.push({
                                label: item.subCodeDescription,
                                value: item.subCode
                            })
                            vm.moduleList.push(item);
                        }
				    }else if(vm.screenModule == 'SSA'){
				        if(item.subCode == '0600'){
                            vm.moduleNameDetailsLabelValue.push({
                                label: item.subCodeDescription,
                                value: item.subCode
                            })
                            vm.moduleList.push(item);
                        }
				    }else if(vm.screenModule == 'GST'){
				        if(item.subCode == '0500'){
                            vm.moduleNameDetailsLabelValue.push({
                                label: item.subCodeDescription,
                                value: item.subCode
                            })
                            vm.moduleList.push(item);
                        }
				    }else if(vm.screenModule == 'CBDT'){
				        if(item.subCode == '0400'){
                            vm.moduleNameDetailsLabelValue.push({
                                label: item.subCodeDescription,
                                value: item.subCode
                            })
                            vm.moduleList.push(item);
                        }
				    }
				})
			}, function (error) {
				handleErrorResponse(error);
			});
		}

		function decimalConvert(value, index, event) {

			let decimal = parseFloat(value);

			vm.index = index;
			if (decimal == undefined || decimal == null || decimal == NaN) {
				if (event.target.value == "") {
					vm.errorMsg[vm.index] = false;
				} else if (event.target.id == vm.index) {

					if (event.target.value <= 100) {
						vm.errorMsg[vm.index] = false;
					} else {
						vm.errorMsg[vm.index] = true;

						for (var i = 0; i < vm.levelDetailsTocSharingDto.length; i++) {
							if (vm.index == i) {
								vm.levelDetailsTocSharingDto[vm.index].sharingPercentage = '';
								break;
							}
						}
					}
				}
			} else {
				if (decimal <= 100) {
					vm.errorMsg[vm.index] = false;

				} else {
					vm.errorMsg[vm.index] = true;
					for (var i = 0; i < vm.levelDetailsTocSharingDto.length; i++) {
						if (vm.index == i) {
							vm.levelDetailsTocSharingDto[vm.index].sharingPercentage = '';
							break;
						}
					}

				}
			}
		}

		function decimalConvertForModify(value, index, event) {

			let decimal = parseFloat(value);

			vm.index = index;
			if (decimal == undefined || decimal == null || decimal == NaN) {
				if (event.target.value == "") {
					vm.errorMsg[vm.index] = false;
				} else if (event.target.id == vm.index) {

					if (event.target.value <= 100) {
						vm.errorMsg[vm.index] = false;
					} else {
						vm.errorMsg[vm.index] = true;

						for (var i = 0; i < vm.levelDetailsTocSharingDto.length; i++) {
							if (vm.index == i) {
								vm.levelDetailsTocSharingDto[vm.index].C007.oldValue = '';
								break;
							}
						}
					}
				}
			} else {
				if (decimal <= 100) {
					vm.errorMsg[vm.index] = false;

				} else {
					vm.errorMsg[vm.index] = true;
					for (var i = 0; i < vm.levelDetailsTocSharingDto.length; i++) {
						if (vm.index == i) {
							vm.levelDetailsTocSharingDto[vm.index].C007.oldValue = '';
							break;
						}
					}

				}
			}
		}

		function calculateSum() {
			vm.total = 0;
			for (var i = 0; i < vm.levelDetailsTocSharingDto.length; i++) {
				let lastRow = vm.levelDetailsTocSharingDto.length - 1;
				if (lastRow) {
					vm.sum = parseFloat(vm.levelDetailsTocSharingDto[i].sharingPercentage);
					vm.total += vm.sum;
				}
			}
		}

		function calculateSumForModify() {
			vm.total = 0;
			for (var i = 0; i < vm.levelDetailsTocSharingDto.length; i++) {
				let lastRow = vm.levelDetailsTocSharingDto.length - 1;
				if (lastRow) {
					vm.sum = parseFloat(vm.levelDetailsTocSharingDto[i].C007.oldValue);
					vm.total += vm.sum;
				}
			}
		}

		function submitAddForm() {
			calculateSum();
			if (vm.total != 100) {
				var msg = 'Sum of sharing percentage should be equal to 100';
				Swal.fire('Warning!', msg, 'warning');
				/*                for(var i = 0; i < vm.levelDetailsTocSharingDto.length; i++){
				                   vm.levelDetailsTocSharingDto[i].sharingPercentage = '';
				                   vm.total = '';
				                }*/
			} else {
				Swal.fire({
						title: "Are you sure?",
						text: "Do you want to Really Add this record?",
						type: "warning",
						showCancelButton: true,
						cancelButtonClass: "btn-cancel",
						confirmButtonClass: "btn-primary",
						confirmButtonText: "Yes",
						closeOnConfirm: false
					},
					function () {
						vm.loader = true;
						var requestData = {
							"schemeCode": vm.moduleId,
							"versionNo": vm.versionNo,
							"effeFromDate": $filter('date')(vm.effFromDate, 'dd-MMM-yyyy'),
							"roundOff": vm.roundOffValue,
							"levelDetailsTocSharingDto": vm.levelDetailsTocSharingDto
						};
						tocSharingMaintenanceService.submitAddForm(requestData).then(
							(response) => {
								vm.loader = false;
								Swal.fire("Added!", "TOC Sharing Parameters created Successfully", "success");
								successState();
							}, function(error){
							    vm.loader = false;
							    handleErrorResponse(error);
							    goPreviousState();
							});
					});
			}
		}

		// Cancel Confirmation
		function showCancelConfirmation(state) {
			Swal.fire({
					title: "Confirm Cancel",
					text: "Do you really want to cancel?",
					type: "warning",
					showCancelButton: true,
					cancelButtonClass: "btn-cancel",
					confirmButtonClass: "btn-danger",
					confirmButtonText: "Yes",
					cancelButtonText: "No",
					closeOnConfirm: true
				},
				function () {
					goPreviousState();
				});
		}

		//Get Inquiry Version No Tourch Details
		function getInquiryVersionTorch() {
			vm.loader = true;
			vm.searchSolId = '';
			var requestData = {
				'schemeCode': vm.moduleId,
				'versionNo': "",
				'effeFromDate': ""
			}
			tocSharingMaintenanceService.getInquirySelectDetails(requestData).then(function (response) {
				vm.loader = false;
				vm.versionNoList = response.data.result.versionOut;
				angular.forEach(vm.versionNoList, function (item) {
					if (item.verifiedStatus == 'Y') {
						item.verifiedStatus = 'Yes';
					} else {
						item.verifiedStatus = 'No';
					}

				});
				angular.element('#versionNoModal').modal('toggle');
			}, function (error) {
				handleErrorResponse(error);
				vm.loader = false;
			});
		}

		/*        //Check Record already is there or not
		        function checkRecordExisted(){
		            var requestData = {};
		            tocSharingMaintenanceService.checkRecordExisted(requestData).then(function(response){
		                if(response.data.message=='SUCCESS')
		                 {
		                    Swal.fire("Warning!", "Record already added, Do Changes in modify", "warning");
		                                 $state.go('common_toc_sharing_maintenance.modify');
		                 }
		            },function(error){
		                if(error.data.message=='NDF') {
		                    $state.go('common_toc_sharing_maintenance.add');
		                 }else{
		                         handleErrorResponse(error);
		                 }
		            })
		        }*/

		function dateValidationForModify() {
			commonService.getSolDate()
				.then(
					function (response) {
						vm.solDate = response.data.solDate;
						vm.effFromDateOptions = {
							minDate: vm.solDate
						}
					}, handleErrorResponse
				);
		}

		function validateAddSelect() {
			vm.loader = true;
			var requestData = {
				'schemeCode': vm.moduleId
			}
			tocSharingMaintenanceService.validateAddSelect(requestData).then(function (response) {
				vm.loader = false;
				vm.levelDetailsTocSharingDto = response.data.result.levelsOut;
			}, function (error) {
				handleErrorResponse(error);
				vm.loader = false;
				goPreviousState();
			});
		}

		function checkRecordExisted() {
			var requestData = {
				'schemeCode': vm.moduleId
			}
			vm.versionNo = '00001';
			tocSharingMaintenanceService.validateAddSelect(requestData).then(function (response) {

			}, function (error) {
				handleErrorResponse(error);
				goPreviousState();
			});
		}

		//Get Inquiry Select Details
		function getInquirySelect() {
			vm.loader = true;
			vm.searchSolId = '';
			var requestData = {
				'schemeCode': vm.moduleId,
				'versionNo': vm.versionNo,
				'effeFromDate': $filter('date')(vm.effeFromDate, 'dd-MMM-yyyy')
			}
			tocSharingMaintenanceService.getInquirySelectDetails(requestData).then(function (response) {
				vm.loader = false;
				vm.auditInfo = response.data.result;
				vm.tocSharingList = response.data.result.oldNewValueList;
				vm.versionEffDateDetails = response.data.result.oldNewValueList[0];
				vm.roundOffValue = response.data.result.oldNewValueList[0].C008;
			}, function (error) {
				handleErrorResponse(error);
				vm.loader = false;
				goPreviousState();
			});
		}

		//Get Rounding Factor
		function getRoundFactList() {
			vm.referenceId = 'ROUNDING_FACTOR';
			var requestData = {
				'referenceId': vm.referenceId
			}
			commonService.getModuleNameDetails(requestData).then(function (response) {
				vm.roudingFactorList = response.data.result;
				vm.roundOff = [];
				angular.forEach(vm.roudingFactorList, function (item) {
					vm.roundOff.push({
						label: item.subCodeDescription,
						value: item.subCode
					})
				})
			}, function (error) {
				handleErrorResponse(error);
			});
		}

		/* Verify/Delete Select Details */
		function getVerifyDeleteSelect() {
			vm.loader = true;
			var requestData = {
				'schemeCode': vm.moduleId,
				'versionNo': vm.versionNo,
				'effeFromDate': $filter('date')(vm.effeFromDate, 'dd-MMM-yyyy')
			}
			tocSharingMaintenanceService.VerifyDeleteTocSharing(requestData).then(
				(response) => {
					vm.loader = false;
					vm.auditInfo = response.data.result;
					vm.tocSharingList = response.data.result.oldNewValueList;
					vm.versionEffDateDetails = response.data.result.oldNewValueList[0];
					vm.roundOffValue = response.data.result.oldNewValueList[0].C008;

				},
				function (error) {
					vm.loader = false;
					handleErrorResponse(error);
					goPreviousState();
				});
		}

		/* Version/EffFromDate  Details */
		function getVersionEffFromDate() {
			vm.loader = true;
			var requestData = {
				'schemeCode': vm.moduleId
			}
			tocSharingMaintenanceService.VerifyDeleteTocSharing(requestData).then(
				(response) => {
					vm.loader = false;
					vm.versionNo = response.data.result.versionOut[0].versionNo;
					vm.effeFromDate = response.data.result.versionOut[0].effectiveFromDate;

				},
				function (error) {
					vm.loader = false;
					handleErrorResponse(error);
				});
		}


		/* Delete Submit */
		function submitDeleteForm() {
			Swal.fire({
					title: "Are you sure?",
					text: "Do you want to Really Delete this record?",
					type: "warning",
					showCancelButton: true,
					cancelButtonClass: "btn-cancel",
					confirmButtonClass: "btn-primary",
					confirmButtonText: "Yes",
					closeOnConfirm: false
				},

				function () {
					vm.loader = true;
					let requestData = {
						'schemeCode': vm.moduleId,
						'versionNo': vm.versionNo,
						'effeFromDate': $filter('date')(vm.effeFromDate, 'dd-MMM-yyyy')
					}
					tocSharingMaintenanceService.submitDeleteForm(requestData).then(
						(response) => {
							vm.loader = false;
							Swal.fire("Deleted!", "Details Deleted Successfully", "success");
							successState();
						}, function(error){
                                vm.loader = false;
                                handleErrorResponse(error);
                                goPreviousState();
						});
				});

		}

		/* Verify Submit */
		function submitVerifyForm() {
			Swal.fire({
					title: "Are you sure?",
					text: "Do you want to Really Verify this record?",
					type: "warning",
					showCancelButton: true,
					cancelButtonClass: "btn-cancel",
					confirmButtonClass: "btn-primary",
					confirmButtonText: "Yes",
					closeOnConfirm: false
				},

				function () {
					vm.loader = true;
					let requestData = {
						'schemeCode': vm.moduleId,
						'versionNo': vm.versionNo,
						'effeFromDate': $filter('date')(vm.effeFromDate, 'dd-MMM-yyyy')
					}
					tocSharingMaintenanceService.submitVerifyForm(requestData).then(
						(response) => {
							vm.loader = false;
							Swal.fire("Verified!", "Details Verified Successfully", "success");
							successState();
						}, function(error){
						    vm.loader = false;
						    handleErrorResponse(error)
						    goPreviousState();
						});
				});

		}

		function getVersionEffFromDateForModify() {
			vm.loader = true;
			var requestData = {
				'schemeCode': vm.moduleId
			}
			tocSharingMaintenanceService.getModifySelectSharing(requestData).then(
				(response) => {
					vm.loader = false;
					vm.versionNo = response.data.result.versionOut[0].versionNo;
					vm.effeFromDate = new Date(response.data.result.versionOut[0].effectiveFromDate);
					vm.effeFromDate = $filter('date')(vm.effeFromDate, 'dd/MM/yyyy');
				},
				function (error) {
					vm.loader = false;
					handleErrorResponse(error);
				});
		}

		function getModifySelect() {
			vm.loader = true;
			var requestData = {
				'schemeCode': vm.moduleId,
				'versionNo': vm.versionNo,
				'effeFromDate': $filter('date')(vm.effeFromDate, 'dd/MMM/yyyy')
			}
			vm.loader = true;
			tocSharingMaintenanceService.getModifySelectSharing(requestData).then(
				(response) => {
					vm.loader = false;
					vm.all = response.data.result;
					vm.levelDetailsTocSharingDto = response.data.result.oldNewValueList;
					if (vm.levelDetailsTocSharingDto)
						vm.effeFromDate = convertToDateObject(vm.levelDetailsTocSharingDto[0].C004.oldValue);
					vm.effeFromDates = vm.effeFromDate;
					vm.roundOffValue = vm.levelDetailsTocSharingDto[0].C008.oldValue;
					vm.verifiedStatus = vm.all.verifiedStatus;
					calculateSumForModify();

				},
				function (error) {
					vm.loader = false;
					handleErrorResponse(error);
					goPreviousState();
				}
			);
		}

		function dateValidationForModify() {
			commonService.getSolDate()
				.then(
					function (response) {
						vm.solDate = response.data.solDate;
						vm.effFromDateOptions = {
							minDate: vm.solDate
						}
					}, handleErrorResponse
				);
		}

		/*        */
		/* Submit Modify*/
		/*
		        function modifySubmit() {
		                Swal.fire({
		                        title: "Are you sure?",
		                        text: "Do you want to Really Modify this record?",
		                        type: "warning",
		                        showCancelButton: true,
		                        cancelButtonClass: "btn-cancel",
		                        confirmButtonClass: "btn-primary",
		                        confirmButtonText: "Yes",
		                        closeOnConfirm: false
		                    },

		                    function() {

		                        vm.resultArray = [];
		                        angular.forEach(vm.levelDetailsTocSharingDto, function(item){
		                             vm.resultArray.push({"id" : item.ID.oldValue, "levelId" : item.C006.oldValue , "levelName" : item.C003.oldValue ,"sharingPercentage" : item.C007.oldValue });
		                        })
		                            let requestData = {
		                                'schemeCode': vm.moduleId,
		                                'roundOff': vm.roundOffValue,
		                                'effeFromDate': $filter('date')(vm.effeFromDate, 'dd/MM/yyyy'),
		                                'levelDetailsTocSharingDto': vm.resultArray
		                            }
		                            tocSharingMaintenanceService.submitModifyForm(requestData).then(
		                                (response) => {
		                                    vm.loader = false;
		                                    Swal.fire("Modified!","Details Modified Successfully","success");
		                                    location.href = '#!/common_toc_sharing_maintenance/action/inquiry';
		                                }, function(error) {
		                                     vm.loader = false;
		                                     handleErrorResponse(error);
		                                 });

		                    });
		        }*/

		function modifySubmit() {
			vm.resultArray = [];
			angular.forEach(vm.levelDetailsTocSharingDto, function (item) {
				vm.resultArray.push({
					"id": item.ID.oldValue,
					"levelId": item.C006.oldValue,
					"levelName": item.C003.oldValue,
					"sharingPercentage": item.C007.oldValue
				});
			})

			let requestData = {
				'schemeCode': vm.moduleId,
				'roundOff': vm.roundOffValue,
				'effeFromDate': $filter('date')(vm.effeFromDate, 'dd/MMM/yyyy'),
				'levelDetailsTocSharingDto': vm.resultArray
			}

			if (vm.effeFromDates.getTime() == vm.effeFromDate.getTime()) {
				Swal.fire({
					title: "Record already exists for the effective date",
					text: "Do you wish to continue?",
					type: "warning",
					showCancelButton: true,
					cancelButtonClass: "btn-cancel",
					confirmButtonClass: "btn-danger",
					confirmButtonText: "Yes",
					cancelButtonText: "No",
					closeOnConfirm: true
				}, function (inputValue) {
					vm.loader = true;
					if (!inputValue) {
						staySameInState();
					} else {
						tocSharingMaintenanceService.submitModifyForm(requestData).then(function (response) {
							vm.loader = false;
							showSuccessAlert('MODIFY');
							successState();
						}, function (error) {
							vm.loader = false;
							handleErrorResponse(error);
							if (error.data.message == 'Record Not Modified') {
								staySameInState();
							} else {
								goPreviousState();
							}
						})
					}
				});
			} else {
				vm.loader = true;
				tocSharingMaintenanceService.submitModifyForm(requestData).then(function (response) {
					vm.loader = false;
					showSuccessAlert('MODIFY');
					successState();
				}, function (error) {
					vm.loader = false;
					handleErrorResponse(error);
					if (error.data.message == 'Record Not Modified') {
						staySameInState();
					} else {
						goPreviousState();
					}
				})
			}


		}


		function formatDate(tempDate) {
			return $filter('date')(tempDate, 'dd/MMM/yyyy');
		}

		/*function getSubCodeList(){

            tocSharingMaintenanceService.getSubCodeList().then(function(response){
                vm.subCodeList= response.data.result;
            },function(error){
                handleErrorResponse(error);
            })
		}*/
		
		function goNextState(stateObj) {
			$state.go(vm.nextState, {
				obj: stateObj
			})
		}

		function goPreviousState() {
		    if(vm.screenModule == 'KVP'){
                if ($state.current.name == 'kvp_toc_sharing_maintenance.inquiry_detail' || $state.current.name == 'kvp_toc_sharing_maintenance.add' || $state.current.name == 'kvp_toc_sharing_maintenance.verify' || $state.current.name == 'kvp_toc_sharing_maintenance.delete' || $state.current.name == 'kvp_toc_sharing_maintenance.modify') {
                    $state.go('kvp_toc_sharing_maintenance.inquiry');
                }else if($state.current.name == 'kvp_toc_sharing_maintenance.modify_detail'){
                    $state.go('kvp_toc_sharing_maintenance.modify');
                }else if($state.current.name == 'kvp_toc_sharing_maintenance.verify_detail'){
                    $state.go('kvp_toc_sharing_maintenance.verify');
                }else if($state.current.name == 'kvp_toc_sharing_maintenance.delete_detail'){
                    $state.go('kvp_toc_sharing_maintenance.delete');
                }else if($state.current.name == 'kvp_toc_sharing_maintenance.add_detail'){
                    $state.go('kvp_toc_sharing_maintenance.add');
                }
		    }else if(vm.screenModule == 'SSA'){
    			if ($state.current.name == 'ssa_toc_sharing_maintenance.inquiry_detail' || $state.current.name == 'ssa_toc_sharing_maintenance.add' || $state.current.name == 'ssa_toc_sharing_maintenance.verify' || $state.current.name == 'ssa_toc_sharing_maintenance.delete' || $state.current.name == 'ssa_toc_sharing_maintenance.modify') {
    				$state.go('ssa_toc_sharing_maintenance.inquiry');
    			}else if($state.current.name == 'ssa_toc_sharing_maintenance.modify_detail'){
    			    $state.go('ssa_toc_sharing_maintenance.modify');
    			}else if($state.current.name == 'ssa_toc_sharing_maintenance.verify_detail'){
    			    $state.go('ssa_toc_sharing_maintenance.verify');
    			}else if($state.current.name == 'ssa_toc_sharing_maintenance.delete_detail'){
                    $state.go('ssa_toc_sharing_maintenance.delete');
    			}else if($state.current.name == 'ssa_toc_sharing_maintenance.add_detail'){
                    $state.go('ssa_toc_sharing_maintenance.add');
                }
		    }else if(vm.screenModule == 'PPF'){
    			if ($state.current.name == 'ppf_toc_sharing_maintenance.inquiry_detail' || $state.current.name == 'ppf_toc_sharing_maintenance.add' || $state.current.name == 'ppf_toc_sharing_maintenance.verify' || $state.current.name == 'ppf_toc_sharing_maintenance.delete' || $state.current.name == 'ppf_toc_sharing_maintenance.modify') {
    				$state.go('ppf_toc_sharing_maintenance.inquiry');
    			}else if($state.current.name == 'ppf_toc_sharing_maintenance.modify_detail'){
    			    $state.go('ppf_toc_sharing_maintenance.modify');
    			}else if($state.current.name == 'ppf_toc_sharing_maintenance.verify_detail'){
    			    $state.go('ppf_toc_sharing_maintenance.verify');
    			}else if($state.current.name == 'ppf_toc_sharing_maintenance.delete_detail'){
                    $state.go('ppf_toc_sharing_maintenance.delete');
    			}else if($state.current.name == 'ppf_toc_sharing_maintenance.add_detail'){
                     state.go('ppf_toc_sharing_maintenance.add');
                }
		    }else if(vm.screenModule == 'GST'){
    			if ($state.current.name == 'gst_toc_sharing_maintenance.inquiry_detail' || $state.current.name == 'gst_toc_sharing_maintenance.add' || $state.current.name == 'gst_toc_sharing_maintenance.verify' || $state.current.name == 'gst_toc_sharing_maintenance.delete' || $state.current.name == 'gst_toc_sharing_maintenance.modify') {
    				$state.go('gst_toc_sharing_maintenance.inquiry');
    			}else if($state.current.name == 'gst_toc_sharing_maintenance.modify_detail'){
    			    $state.go('gst_toc_sharing_maintenance.modify');
    			}else if($state.current.name == 'gst_toc_sharing_maintenance.verify_detail'){
    			    $state.go('gst_toc_sharing_maintenance.verify');
    			}else if($state.current.name == 'gst_toc_sharing_maintenance.delete_detail'){
                    $state.go('gst_toc_sharing_maintenance.delete');
    			}else if($state.current.name == 'gst_toc_sharing_maintenance.add_detail'){
                     state.go('gst_toc_sharing_maintenance.add');
                }
		    }else if(vm.screenModule == 'CBDT'){
    			if ($state.current.name == 'cbdt_toc_sharing_maintenance.inquiry_detail' || $state.current.name == 'cbdt_toc_sharing_maintenance.add' || $state.current.name == 'cbdt_toc_sharing_maintenance.verify' || $state.current.name == 'cbdt_toc_sharing_maintenance.delete' || $state.current.name == 'cbdt_toc_sharing_maintenance.modify') {
    				$state.go('cbdt_toc_sharing_maintenance.inquiry');
    			}else if($state.current.name == 'cbdt_toc_sharing_maintenance.modify_detail'){
    			    $state.go('cbdt_toc_sharing_maintenance.modify');
    			}else if($state.current.name == 'cbdt_toc_sharing_maintenance.verify_detail'){
    			    $state.go('cbdt_toc_sharing_maintenance.verify');
    			}else if($state.current.name == 'cbdt_toc_sharing_maintenance.delete_detail'){
                    $state.go('cbdt_toc_sharing_maintenance.delete');
    			}else if($state.current.name == 'cbdt_toc_sharing_maintenance.add_detail'){
                     state.go('cbdt_toc_sharing_maintenance.add');
                }
		    }
		}

		function cancel() {
			if ($state.current.name == 'kvp_toc_sharing_maintenance.inquiry') {
				location.href = '#!/dc_kvp_menu';
			}else if($state.current.name == 'ppf_toc_sharing_maintenance.inquiry'){
			    location.href = '#!/dc_ppf_menu';
			}else if($state.current.name == 'ssa_toc_sharing_maintenance.inquiry'){
			     location.href = '#!/dc_ssa_menu';
			}else if($state.current.name == 'gst_toc_sharing_maintenance.inquiry'){
			     location.href = '#!/dc_gst_menu';
			}else if($state.current.name == 'cbdt_toc_sharing_maintenance.inquiry'){
			     location.href = '#!/dc_cbdt_menu';
			}
		}

		function successState(){
			if ($state.current.name == 'kvp_toc_sharing_maintenance.add_detail' || $state.current.name == 'kvp_toc_sharing_maintenance.verify_detail' || $state.current.name == 'kvp_toc_sharing_maintenance.delete_detail' || $state.current.name == 'kvp_toc_sharing_maintenance.modify_detail') {
				$state.go('kvp_toc_sharing_maintenance.inquiry');
			}else if($state.current.name == 'ppf_toc_sharing_maintenance.add_detail' || $state.current.name == 'ppf_toc_sharing_maintenance.verify_detail' || $state.current.name == 'ppf_toc_sharing_maintenance.delete_detail' || $state.current.name == 'ppf_toc_sharing_maintenance.modify_detail'){
			    $state.go('ppf_toc_sharing_maintenance.inquiry');
			}else if($state.current.name == 'ssa_toc_sharing_maintenance.add_detail' || $state.current.name == 'ssa_toc_sharing_maintenance.verify_detail' || $state.current.name == 'ssa_toc_sharing_maintenance.delete_detail' || $state.current.name == 'ssa_toc_sharing_maintenance.modify_detail'){
			    $state.go('ssa_toc_sharing_maintenance.inquiry');
			}else if($state.current.name == 'gst_toc_sharing_maintenance.add_detail' || $state.current.name == 'gst_toc_sharing_maintenance.verify_detail' || $state.current.name == 'gst_toc_sharing_maintenance.delete_detail' || $state.current.name == 'gst_toc_sharing_maintenance.modify_detail'){
			    $state.go('gst_toc_sharing_maintenance.inquiry');
			}else if($state.current.name == 'cbdt_toc_sharing_maintenance.add_detail' || $state.current.name == 'cbdt_toc_sharing_maintenance.verify_detail' || $state.current.name == 'cbdt_toc_sharing_maintenance.delete_detail' || $state.current.name == 'cbdt_toc_sharing_maintenance.modify_detail'){
			    $state.go('cbdt_toc_sharing_maintenance.inquiry');
			}
		}

		function staySameInState(){
		    if($state.current.name == 'kvp_toc_sharing_maintenance.modify_detail'){
		        $state.go('kvp_toc_sharing_maintenance.modify_detail')
		    }else if($state.current.name == 'ssa_toc_sharing_maintenance.modify_detail'){
		        $state.go('ssa_toc_sharing_maintenance.modify_detail')
		    }else if($state.current.name == 'ppf_toc_sharing_maintenance.modify_detail'){
		        $state.go('ppf_toc_sharing_maintenance.modify_detail')
		    }else if($state.current.name == 'gst_toc_sharing_maintenance.modify_detail'){
		        $state.go('gst_toc_sharing_maintenance.modify_detail')
		    }else if($state.current.name == 'cbdt_toc_sharing_maintenance.modify_detail'){
		        $state.go('cbdt_toc_sharing_maintenance.modify_detail')
		    }
		}


	}
})();