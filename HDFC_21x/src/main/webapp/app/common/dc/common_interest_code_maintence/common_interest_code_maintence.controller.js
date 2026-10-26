(function () {

	'use strict';

	angular.module('gbmUltraApp').controller('interestRateCodeMaintenanceforInvestmentSchemesController',
		interestRateCodeMaintenanceforInvestmentSchemesController);

	interestRateCodeMaintenanceforInvestmentSchemesController.$inject = ['$scope', '$filter', '$state','$timeout',
		'$stateParams', '$location', 'interestRateCodeMaintenanceforInvestmentSchemesService', 'commonService'
	];

	function interestRateCodeMaintenanceforInvestmentSchemesController($scope, $filter, $state,$timeout, $stateParams,
		$location, interestRateCodeMaintenanceforInvestmentSchemesService, commonService) {

		var vm = this;
		vm.errorMsg = [];
		vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
		vm.showCancelConfirmation = showCancelConfirmation;
		vm.getModuleName = getModuleName;
		vm.addSubmit = addSubmit;
		vm.getModifyInterestRateCode = getModifyInterestRateCode;
		vm.getModifySelectDetails = getModifySelectDetails;
		vm.modifySubmit = modifySubmit;
		vm.getInquiryInterestTourch = getInquiryInterestTourch;
		vm.getInquiryVersionTourch = getInquiryVersionTourch;
		vm.getVerifyDeleteInterestRateCode = getVerifyDeleteInterestRateCode;
		vm.getVerifyDeleteDetails = getVerifyDeleteDetails;
		vm.verifySubmit = verifySubmit;
		vm.deleteSubmit = deleteSubmit;
		vm.getInquirySelectDetails = getInquirySelectDetails;
		vm.dateValidationForModify = dateValidationForModify;
		vm.decimalConvert = decimalConvert;
		vm.decimalConvertForModify = decimalConvertForModify;
		vm.nextState = '';

		vm.goNextState = goNextState;
		vm.goPreviousState = goPreviousState;
		vm.cancel = cancel;
		vm.successState = successState;
		vm.staySameInState = staySameInState;

		function getInterestRateCodeFromState() {

            if ($state.current.name == 'kvp_interest_code_maintence.add' || $state.current.name == 'kvp_interest_code_maintence.modify' || $state.current.name == 'kvp_interest_code_maintence.verify' || $state.current.name == 'kvp_interest_code_maintence.delete' ||$state.current.name == 'kvp_interest_code_maintence.inquiry'){
                vm.screenModule = 'KVP'
                if($state.current.name == 'kvp_interest_code_maintence.modify')
                    vm.nextState = 'kvp_interest_code_maintence.modify_detail';
                else if($state.current.name == 'kvp_interest_code_maintence.verify')
                    vm.nextState = 'kvp_interest_code_maintence.verify_detail';
                else if($state.current.name == 'kvp_interest_code_maintence.delete')
                    vm.nextState = 'kvp_interest_code_maintence.delete_detail';
                else if($state.current.name == 'kvp_interest_code_maintence.inquiry')
                    vm.nextState = 'kvp_interest_code_maintence.inquiry_detail';
            }else if($state.current.name == 'ssa_interest_code_maintence.add' || $state.current.name == 'ssa_interest_code_maintence.modify' || $state.current.name == 'ssa_interest_code_maintence.verify' || $state.current.name == 'ssa_interest_code_maintence.delete' ||$state.current.name == 'ssa_interest_code_maintence.inquiry'){
                vm.screenModule = 'SSA'
                if($state.current.name == 'ssa_interest_code_maintence.modify')
                    vm.nextState = 'ssa_interest_code_maintence.modify_detail';
                else if($state.current.name == 'ssa_interest_code_maintence.verify')
                    vm.nextState = 'ssa_interest_code_maintence.verify_detail';
                else if($state.current.name == 'ssa_interest_code_maintence.delete')
                    vm.nextState = 'ssa_interest_code_maintence.delete_detail';
                else if($state.current.name == 'ssa_interest_code_maintence.inquiry')
                    vm.nextState = 'ssa_interest_code_maintence.inquiry_detail';
            }else if($state.current.name == 'ppf_interest_code_maintence.add' || $state.current.name == 'ppf_interest_code_maintence.modify' || $state.current.name == 'ppf_interest_code_maintence.verify' || $state.current.name == 'ppf_interest_code_maintence.delete' ||$state.current.name == 'ppf_interest_code_maintence.inquiry'){
                vm.screenModule = 'PPF'
                if($state.current.name == 'ppf_interest_code_maintence.modify')
                    vm.nextState = 'ppf_interest_code_maintence.modify_detail';
                else if($state.current.name == 'ppf_interest_code_maintence.verify')
                    vm.nextState = 'ppf_interest_code_maintence.verify_detail';
                else if($state.current.name == 'ppf_interest_code_maintence.delete')
                    vm.nextState = 'ppf_interest_code_maintence.delete_detail';
                else if($state.current.name == 'ppf_interest_code_maintence.inquiry')
                    vm.nextState = 'ppf_interest_code_maintence.inquiry_detail';
            }else if($state.current.name == 'scss_interest_code_maintence.add' || $state.current.name == 'scss_interest_code_maintence.modify' || $state.current.name == 'scss_interest_code_maintence.verify' || $state.current.name == 'scss_interest_code_maintence.delete' ||$state.current.name == 'scss_interest_code_maintence.inquiry'){
                vm.screenModule = 'SCSS'
                if($state.current.name == 'scss_interest_code_maintence.modify')
                    vm.nextState = 'scss_interest_code_maintence.modify_detail';
                else if($state.current.name == 'scss_interest_code_maintence.verify')
                    vm.nextState = 'scss_interest_code_maintence.verify_detail';
                else if($state.current.name == 'scss_interest_code_maintence.delete')
                    vm.nextState = 'scss_interest_code_maintence.delete_detail';
                else if($state.current.name == 'scss_interest_code_maintence.inquiry')
                    vm.nextState = 'scss_interest_code_maintence.inquiry_detail'; 
            }else if ($state.current.name == 'scss_interest_code_maintence.modify_detail' || $state.current.name == 'kvp_interest_code_maintence.modify_detail' || $state.current.name == 'ppf_interest_code_maintence.modify_detail' || $state.current.name == 'ssa_interest_code_maintence.modify_detail') {
                if ($stateParams.obj.interestRateCode) {
                    vm.moduleId = $stateParams.obj.moduleId;
                    vm.interestRateCode = $stateParams.obj.interestRateCode;
                    vm.interestRateCodeDesc = $stateParams.obj.interestRateCodeDesc;
                    vm.versionNo = $stateParams.obj.versionNo;
                    vm.remarks = $stateParams.obj.remarks;
                    vm.screenModule = $stateParams.obj.screenModule;
                    getModifySelectDetails();

                } else {
                    if($state.current.name == 'kvp_interest_code_maintence.modify_detail')
                        $state.go('kvp_interest_code_maintence.modify')
                    else if($state.current.name == 'ppf_interest_code_maintence.modify_detail')
                        $state.go('ppf_interest_code_maintence.modify')
                    else if($state.current.name == 'ssa_interest_code_maintence.modify_detail')
                        $state.go('ssa_interest_code_maintence.modify')
                    else if($state.current.name == 'scss_interest_code_maintence.modify_detail')
                        $state.go('scss_interest_code_maintence.modify')
                }
            } else if ($state.current.name == 'scss_interest_code_maintence.verify_detail' || $state.current.name == 'kvp_interest_code_maintence.verify_detail' || $state.current.name == 'ppf_interest_code_maintence.verify_detail' || $state.current.name == 'ssa_interest_code_maintence.verify_detail') {
                if ($stateParams.obj.interestRateCode) {
                    vm.moduleId = $stateParams.obj.moduleId;
                    vm.interestRateCode = $stateParams.obj.interestRateCode;
                    vm.interestRateCodeDesc = $stateParams.obj.interestRateCodeDesc;
                    vm.versionNo = $stateParams.obj.versionNo;
                    vm.remarks = $stateParams.obj.remarks;
                    vm.screenModule = $stateParams.obj.screenModule;
                    getVerifyDeleteDetails();
                } else {
                    if($state.current.name == 'kvp_interest_code_maintence.verify_detail')
                        $state.go('kvp_interest_code_maintence.verify')
                    else if($state.current.name == 'ppf_interest_code_maintence.verify_detail')
                        $state.go('ppf_interest_code_maintence.verify')
                    else if($state.current.name == 'ssa_interest_code_maintence.verify_detail')
                        $state.go('ssa_interest_code_maintence.verify')
                    else if($state.current.name == 'scss_interest_code_maintence.verify_detail')
                        $state.go('scss_interest_code_maintence.verify')
                }
            } else if ($state.current.name == 'scss_interest_code_maintence.delete_detail' || $state.current.name == 'kvp_interest_code_maintence.delete_detail' || $state.current.name == 'ppf_interest_code_maintence.delete_detail' || $state.current.name == 'ssa_interest_code_maintence.delete_detail') {
                if ($stateParams.obj.interestRateCode) {
                    vm.moduleId = $stateParams.obj.moduleId;
                    vm.interestRateCode = $stateParams.obj.interestRateCode;
                    vm.interestRateCodeDesc = $stateParams.obj.interestRateCodeDesc;
                    vm.versionNo = $stateParams.obj.versionNo;
                    vm.remarks = $stateParams.obj.remarks;
                    vm.screenModule = $stateParams.obj.screenModule;
                    getVerifyDeleteDetails();
                } else {
                    if($state.current.name == 'kvp_interest_code_maintence.delete_detail')
                        $state.go('kvp_interest_code_maintence.delete')
                    else if($state.current.name == 'ppf_interest_code_maintence.delete_detail')
                        $state.go('ppf_interest_code_maintence.delete')
                    else if($state.current.name == 'ssa_interest_code_maintence.delete_detail')
                        $state.go('ssa_interest_code_maintence.delete')
                    else if($state.current.name == 'scss_interest_code_maintence.delete_detail')
                        $state.go('scss_interest_code_maintence.delete')
                }
            } else if ($state.current.name == 'scss_interest_code_maintence.inquiry_detail' || $state.current.name == 'kvp_interest_code_maintence.inquiry_detail' || $state.current.name == 'ppf_interest_code_maintence.inquiry_detail' || $state.current.name == 'ssa_interest_code_maintence.inquiry_detail') {
                if ($stateParams.obj.interestRateCode) {
                    vm.moduleId = $stateParams.obj.moduleId;
                    vm.interestRateCode = $stateParams.obj.interestRateCode;
                    vm.interestRateCodeDesc = $stateParams.obj.interestRateCodeDesc;
                    vm.intrstRateCodeDescMain = $stateParams.obj.intrstRateCodeDescMain;
                    vm.versionNo = $stateParams.obj.versionNo;
                    vm.screenModule = $stateParams.obj.screenModule;
                    getInquirySelectDetails();
                } else {
                    if($state.current.name == 'kvp_interest_code_maintence.inquiry_detail')
                        $state.go('kvp_interest_code_maintence.inquiry')
                    else if($state.current.name == 'ppf_interest_code_maintence.inquiry_detail')
                        $state.go('ppf_interest_code_maintence.inquiry')
                    else if($state.current.name == 'ssa_interest_code_maintence.inquiry_detail')
                        $state.go('ssa_interest_code_maintence.inquiry')
                    else if($state.current.name == 'scss_interest_code_maintence.inquiry_detail')
                        $state.go('scss_interest_code_maintence.inquiry')
                }
            }
        }
        getInterestRateCodeFromState();
        //Get Module Name
        function getModuleName() {
            vm.referenceId = 'MOD_FOR_INT';
            var requestData = {
                'referenceId': vm.referenceId
            }
            interestRateCodeMaintenanceforInvestmentSchemesService.getModuleDetails(requestData).then(function (response) {
                vm.moduleNameList = response.data.result;
                vm.moduleList = [];
                if(vm.screenModule == 'KVP'){
                  angular.forEach(vm.moduleNameList, function(item){
                      if(item.subCode == '0502'){
                          vm.moduleList.push(item);
                      }
                  })
                }else if(vm.screenModule == 'SSA'){
                  angular.forEach(vm.moduleNameList, function(item){
                      if(item.subCode == '0600'){
                          vm.moduleList.push(item);
                      }
                  })
                }else if(vm.screenModule == 'PPF'){
                  angular.forEach(vm.moduleNameList, function(item){
                      if(item.subCode == '0900'){
                          vm.moduleList.push(item);
                      }
                  })
                }else if(vm.screenModule == 'SCSS'){
                     angular.forEach(vm.moduleNameList, function(item){
                          if(item.subCode == '0501'){
                              vm.moduleList.push(item);
                          }
                      })
                }
            }, function (error) {
                handleErrorResponse(error);
            });
		}
		//Add Submit
		function addSubmit() {
                vm.msg = "Interest Version for "+vm.screenModule+" created successfully";
			Swal.fire({
				title: "Are you sure?",
				text: "Do you want to Really Add this record?",
				type: "warning",
				showCancelButton: true,
				cancelButtonClass: "btn-cancel",
				confirmButtonClass: "btn-primary",
				confirmButtonText: "Yes",
				closeOnConfirm: false
			}, () => {
				vm.loader = true;
				var requestData = {
					'interestRateCode': vm.interestRateCode,
					'interestRateCodeDescription': vm.interestRateCodeDesc,
					'effectiveFromDate': $filter('date')(new Date(vm.effFromDate), 'dd-MMM-yyyy'),
					'interestRate': vm.interestRate,
					'remarks': vm.remarks,
					'moduleId': vm.moduleId
				}
				interestRateCodeMaintenanceforInvestmentSchemesService.addInterestRate(requestData).then(function (response) {
						Swal.fire("Added", vm.msg, "success");
						vm.successState();
						vm.loader = false;
					},
					function (error) {
						handleErrorResponse(error);
						vm.loader = false;
						vm.goPreviousState();
					});
			})


		}
		//Modify Interest rate code Tourch
		function getModifyInterestRateCode() {
			vm.loader = true;
			vm.searchSolId = '';
			var requestData = {
				'moduleId': vm.moduleId
			}
			interestRateCodeMaintenanceforInvestmentSchemesService.getModifyInterestRateCode(requestData).then(function (response) {
				vm.loader = false;
				vm.interestRateCodeList = response.data.result[0].scssInterestRateCodeTorchList;
				angular.forEach(vm.interestRateCodeList, function (item) {
					item.INT_EFF_FROM_DATE = getDateWithoutTime(item.INT_EFF_FROM_DATE);
				});
				angular.element('#interestRateCodeModel').modal('toggle');
			}, function (error) {
				handleErrorResponse(error);
				vm.loader = false;
			});
		}
		//Get Modify Select Details
		function getModifySelectDetails() {
			vm.loader = true;
			vm.searchSolId = '';
			var requestData = {
				'interestRateCode': vm.interestRateCode,
				'moduleId': vm.moduleId
			}
			interestRateCodeMaintenanceforInvestmentSchemesService.getModifyInterestRateCode(requestData).then(function (response) {

			    vm.loader = false;
				//                        vm.getModifyDetails = response.data.result[0].scssInterestRateCodeMaintenanceList[0];
				//                        vm.getModifyDetailsIntRatCode = response.data.result[0].scssInterestRateCodeMaintenanceList[1];
				//                        vm.getModifyDetails.OLD_VAL = convertDateFormat(vm.getModifyDetails.OLD_VAL);

				vm.getModifyDetails = response.data.result[0].scssInterestRateCodeMaintenanceList;
				if (vm.getModifyDetails) {
					vm.effeFromDates = convertToDateObject(vm.getModifyDetails[0].C007.oldValue);
				}
				vm.getModifyDetail = response.data.result[0];
				angular.forEach(vm.getModifyDetails, function (item) {
					item.C007.oldValue = convertToDateObject(item.C007.oldValue);
				});


                for(var key in vm.getModifyDetails[0]){
                    if(key == 'C003'){
                      vm.remarksFlag = true;
                      break;
                    }else{
                      vm.remarksFlag = false;
                    }
                }

			}, function (error) {
				handleErrorResponse(error);
				vm.loader = false;
			});
		}
		//Modify Submit
		function modifySubmit() {
		  var effFromDate = $filter('date')(vm.effFromDate, 'dd-MMM-yyyy');
                                 var dbStr = $filter('date')(vm.effeFromDates, 'dd-MMM-yyyy');
                                  if (effFromDate == dbStr) {
                                       var titleString = "Record already exists for the effective date";
                                       var textString = "Do you wish to continue?Yes/No";
                                   } else {
                                       var titleString = "Are you sure?";
                                       var textString = "Do you want to Really Modify this record?";
                                   }

		 vm.msg = vm.screenModule+ " Interest Rate Code Maintenance for Investment schemes Modified successfully";
			angular.forEach(vm.getModifyDetails, function (item) {
				vm.effeFromDate = item.C007.oldValue;
				vm.effectiveFromDate = formatDate(item.C007.oldValue);
//				vm.effectiveFromDate= moment(item.C007.oldValue).format("DD/MM/YYYY"),
				vm.interestRate = item.C005.oldValue;
				vm.remarks = item.C003.oldValue;
			});
			var postdata = {};
			postdata.interestRateCode = vm.interestRateCode;
			postdata.interestRateCodeDescription = vm.interestRateCodeDesc;
			postdata.effectiveFromDate = vm.effectiveFromDate;
			postdata.interestRate = vm.interestRate;
			postdata.remarks = vm.remarks;
			postdata.moduleId = vm.moduleId;

			if (vm.effeFromDates.getTime() == vm.effeFromDate.getTime()) {
				Swal.fire({
				    title: titleString,
                  text: textString,
                  type: "warning",
                  showCancelButton: true,
                  cancelButtonClass: "btn-cancel",
                  confirmButtonClass: "btn-primary",
                  confirmButtonText: "Yes",
                  closeOnConfirm: false
//				closeOnConfirm: true
				}, function (inputValue) {
					if (!inputValue) {
						vm.staySameInState();
					} else {
						vm.loader = true;
						interestRateCodeMaintenanceforInvestmentSchemesService.modifySubmitDetails(postdata).then(function (response) {
//							  $timeout(function(){
                            Swal.fire("Modified!", vm.msg, "success");
//                            },250);
							vm.successState();
							vm.loader = false;
						}, function (error) {
							handleErrorResponse(error);
							vm.goPreviousState();
							vm.loader = false;
						});
					}
				});
			} else {
                Swal.fire({
                    title: "Are you sure?",
                    text: "Do you want to Really Modify this record?",
                    type: "warning",
                    showCancelButton: true,
                    cancelButtonClass: "btn-cancel",
                    confirmButtonClass: "btn-primary",
                    confirmButtonText: "Yes",
                    closeOnConfirm: false
                }, () => {
                    vm.loader = true;
                    var requestData = {
                        'interestRateCode': vm.interestRateCode,
                        'interestRateCodeDescription': vm.interestRateCodeDesc,
                        'effectiveFromDate': $filter('date')(new Date(vm.effFromDate), 'dd-MMM-yyyy'),
                        'interestRate': vm.interestRate,
                        'remarks': vm.remarks,
                        'moduleId': vm.moduleId
                    }
                    interestRateCodeMaintenanceforInvestmentSchemesService.modifySubmitDetails(postdata).then(function (response) {
//                        $timeout(function(){
                        Swal.fire("Modified!", vm.msg, "success");
//                        },250);

                        vm.successState();
                        vm.loader = false;
                    }, function (error) {
                        handleErrorResponse(error);
                        vm.goPreviousState();
                        vm.loader = false;
                    });
                })
			}
			}
//		}

		//Verify/Delete Interest rate code Tourch
		function getVerifyDeleteInterestRateCode() {
			vm.loader = true;
			vm.searchSolId = '';
			var requestData = {
				'moduleId': vm.moduleId
			}
			interestRateCodeMaintenanceforInvestmentSchemesService.getVerifyDeleteSelectDetails(requestData).then(function (response) {
				vm.loader = false;
				vm.interestRateCodeList = response.data.result[0].scssInterestRateCodeTorchList;
				angular.forEach(vm.interestRateCodeList, function (item) {
					item.INT_EFF_FROM_DATE = convertDate(item.INT_EFF_FROM_DATE);
				});

				angular.element('#interestRateCodeModel').modal('toggle');
			}, function (error) {
				handleErrorResponse(error);
				vm.loader = false;
			});
		}
		//Get Verify Select Details
		function getVerifyDeleteDetails() {
			vm.loader = true;
			vm.searchSolId = '';
			var requestData = {
				'interestRateCode': vm.interestRateCode,
				'moduleId': vm.moduleId
			}
			interestRateCodeMaintenanceforInvestmentSchemesService.getVerifyDeleteSelectDetails(requestData).then(function (response) {
				vm.getVerifyDeleteDetails = response.data.result[0];
				if (response.data.result[0].previousOperationType == 'M') {
					angular.forEach(vm.getVerifyDeleteDetails.scssInterestRateCodeMaintenanceList, function (item) {
                      item.C007.newValue ? item.C007.newValue = convertDateFormat(item.C007.newValue) : item.C007.newValue = '-';

                          if(item.C003){
                                     if(item.C003.newValue == item.C003.oldValue ){
                                           item.C003.newValue = "-";
                                              }
                                              }
                                              if(item.C007.newValue == convertDateFormat(item.C007.newValue)){
                                                 item.C007.newValue = "-";
                                              }

					});
				}
				vm.loader = false;
			}, function (error) {
				handleErrorResponse(error);
				vm.loader = false;
			});
		}
		//Get Inquiry Select Details
		function getInquiryInterestTourch() {
			vm.loader = true;
			vm.searchSolId = '';
			var requestData = {
				'moduleId': vm.moduleId
			}
			interestRateCodeMaintenanceforInvestmentSchemesService.getInquirySelectDetails(requestData).then(function (response) {
				vm.loader = false;
				vm.interestRateCodeList = response.data.result.scssInterestRateCodeTorchList;
				angular.element('#interestRateCodeModel').modal('toggle');
			}, function (error) {
				handleErrorResponse(error);
				vm.loader = false;
			});
		}
		//Get Inquiry Version No Tourch Details
		function getInquiryVersionTourch() {
			vm.loader = true;
			vm.searchSolId = '';
			vm.isVersionFlag = 'Y';
			var requestData = {
				'interestRateCode': vm.interestRateCode,
				'isVersionFlag': vm.isVersionFlag,
				'moduleId': vm.moduleId
			}
			interestRateCodeMaintenanceforInvestmentSchemesService.getInquirySelectDetails(requestData).then(function (response) {
				vm.loader = false;
				vm.versionNoList = response.data.result.versionTorch;
				//                       angular.forEach(vm.versionNoList, function (item) {
				//                           item.effectiveFromDate = getDateWithoutTime(item.effectiveFromDate);
				//                       });

				angular.element('#versionNoModal').modal('toggle');
			}, function (error) {
				handleErrorResponse(error);
				vm.loader = false;
			});
		}
		//Get Inquiry Select Details
		function getInquirySelectDetails() {
			vm.loader = true;
			var requestData = {};
			vm.isVersionFlag = 'N';
			if (vm.versionNo != undefined) {
				requestData.interestRateCode = vm.interestRateCode,
					requestData.version = vm.versionNo,
					requestData.isVersionFlag = vm.isVersionFlag,
					requestData.moduleId = vm.moduleId
			} else {
				requestData.interestRateCode = vm.interestRateCode,
					requestData.isVersionFlag = vm.isVersionFlag,
					requestData.moduleId = vm.moduleId
			}

			interestRateCodeMaintenanceforInvestmentSchemesService.getInquirySelectDetails(requestData).then(function (response) {
				vm.getInquiryDetails = response.data.result.oldList;
				vm.getInquiry = response.data.result;
				vm.getInquiryDetailsOperation = response.data.result.previousOperationType;
				if (vm.getInquiryDetailsOperation == 'M') {
                        vm.getInquiryOldNewDetails = response.data.result.scssInterestRateCodeMaintenanceList;

					angular.forEach(vm.getInquiryOldNewDetails, function (item) {
//

                       item.C007.newValue ? item.C007.newValue = convertDateFormat(item.C007.newValue) : item.C007.newValue = '-';

                       if(item.C003){
                                  if(item.C003.newValue == item.C003.oldValue ){
                                        item.C003.newValue = "-";
                                           }
                                           }
                                           if(item.C007.newValue == convertDateFormat(item.C007.newValue)){
                                                                       item.C007.newValue = "-";
                                                                    }
//						item.C006.newValue = convertDateFormat(item.C006.newValue);
						//item.C006.newValue = $filter('date')(item.C006.newValue, "dd-MMM-yyyy");;
					});
				}

				vm.loader = false;
			}, function (error) {
				handleErrorResponse(error);
				vm.loader = false;
				vm.goPreviousState();
			});
		}


		function convertDateFormat(inputDate) {
          // Parse the input date string in the original format
          var parsedDate = new Date(inputDate);
          console.log(parsedDate);

          // Get the day, month, and year components
          var day = parsedDate.getDate();
          var month = parsedDate.getMonth() + 1; // Months are zero-based, so add 1
          var year = parsedDate.getFullYear();

          // Format the day and month to have leading zeros if needed
          var formattedDay = (day < 10) ? '0' + day : day;
          var formattedMonth = (month < 10) ? '0' + month : month;

          // Extract the last two digits of the year
          var formattedYear = year.toString();

          // Construct the final formatted date string in "dd-MM-yy" format
          var formattedDate = formattedDay + '/' + formattedMonth + '/' + formattedYear;

          return formattedDate;
        }
//





		//Verify Sunbmit
		function verifySubmit() {
				 vm.msg = vm.screenModule+ " Interest Rate Code Maintenance for Investment schemes Verified successfully";
			Swal.fire({
				title: "Are you sure?",
				text: "Do you want to Really Verify this record?",
				type: "warning",
				showCancelButton: true,
				cancelButtonClass: "btn-cancel",
				confirmButtonClass: "btn-primary",
				confirmButtonText: "Yes",
				closeOnConfirm: false
			}, () => {
				vm.loader = true;
				var postdata = {};
				postdata.interestRateCode = vm.interestRateCode;
				postdata.remarks = vm.remarks;
				postdata.moduleId = vm.moduleId;

				interestRateCodeMaintenanceforInvestmentSchemesService.verifySubmitDetails(postdata).then(function (response) {
					Swal.fire("Verified!", vm.msg, "success");
					vm.successState();
					vm.loader = false;
				}, function (error) {
					handleErrorResponse(error);
					vm.goPreviousState();
					vm.loader = false;
				});
			})

		}
		//Delete Sunbmit
		function deleteSubmit() {
			vm.msg = vm.screenModule+ " Interest Rate Code Maintenance for Investment schemes Deleted successfully";
			Swal.fire({
				title: "Are you sure?",
				text: "Do you want to Really Delete this record?",
				type: "warning",
				showCancelButton: true,
				cancelButtonClass: "btn-cancel",
				confirmButtonClass: "btn-primary",
				confirmButtonText: "Yes",
				closeOnConfirm: false
			}, () => {
				vm.loader = true;
				var postdata = {};
				postdata.interestRateCode = vm.interestRateCode;
				postdata.remarks = vm.remarks;

				interestRateCodeMaintenanceforInvestmentSchemesService.deleteSubmitDetails(postdata).then(function (response) {
					Swal.fire("Deleted!", vm.msg, "success");
					vm.successState();
					vm.loader = false;
				}, function (error) {
					handleErrorResponse(error);
					vm.goPreviousState();
					vm.loader = false;
				});
			})

		}
		// Cancel Confirmation
		function showCancelConfirmation() {
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
					vm.goPreviousState();
				});
		}
		// format date from dd/MM/yyyy to yyyy-MM-dd
		function formatDate(tempDate) {
			return $filter('date')(tempDate, "dd-MMM-yyyy");
		}

		function convertDate(str) {
			var date = new Date(str),
				mnth = ("0" + (date.getMonth() + 1)).slice(-2),
				day = ("0" + date.getDate()).slice(-2);
			return [day, mnth, date.getFullYear()].join("/");
		}

		function dateValidationForModify(value) {
			commonService.getSolDate()
				.then(
					function (response) {
						vm.solDate = response.data.solDate;
						vm.effFromDateOptions = {
							minDate: value
						}
					}, handleErrorResponse
				);
		}

		function decimalConvert(value) {

			let decimal = parseFloat(value);
			if (decimal <= 100) {
				vm.errorMsgAddInterestRate = false;
			} else {
				vm.errorMsgAddInterestRate = true;
				vm.interestRate = '';
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

						for (var i = 0; i < vm.getModifyDetails.length; i++) {
							if (vm.index == i) {
								vm.getModifyDetails[vm.index].C005.oldValue = '';
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
					for (var i = 0; i < vm.getModifyDetails.length; i++) {
						if (vm.index == i) {
							vm.getModifyDetails[vm.index].C005.oldValue = '';
							break;
						}
					}

				}
			}
		}

		function goNextState(stateObj) {
			$state.go(vm.nextState, {
				obj: stateObj
			})
		}

		function goPreviousState() {
		    if(vm.screenModule == 'KVP'){
                if ($state.current.name == 'kvp_interest_code_maintence.inquiry_detail' || $state.current.name == 'kvp_interest_code_maintence.add' || $state.current.name == 'kvp_interest_code_maintence.verify' || $state.current.name == 'kvp_interest_code_maintence.delete' || $state.current.name == 'kvp_interest_code_maintence.modify') {
                    $state.go('kvp_interest_code_maintence.inquiry');
                }else if($state.current.name == 'kvp_interest_code_maintence.modify_detail'){
                    $state.go('kvp_interest_code_maintence.modify');
                }else if($state.current.name == 'kvp_interest_code_maintence.verify_detail'){
                    $state.go('kvp_interest_code_maintence.verify');
                }else if($state.current.name == 'kvp_interest_code_maintence.delete_detail'){
                    $state.go('kvp_interest_code_maintence.delete');
                }
		    }else if(vm.screenModule == 'SSA'){
    			if ($state.current.name == 'ssa_interest_code_maintence.inquiry_detail' || $state.current.name == 'ssa_interest_code_maintence.add' || $state.current.name == 'ssa_interest_code_maintence.verify' || $state.current.name == 'ssa_interest_code_maintence.delete' || $state.current.name == 'ssa_interest_code_maintence.modify') {
    				$state.go('ssa_interest_code_maintence.inquiry');
    			}else if($state.current.name == 'ssa_interest_code_maintence.modify_detail'){
    			    $state.go('ssa_interest_code_maintence.modify');
    			}else if($state.current.name == 'ssa_interest_code_maintence.verify_detail'){
    			    $state.go('ssa_interest_code_maintence.verify');
    			}else if($state.current.name == 'ssa_interest_code_maintence.delete_detail'){
                    $state.go('ssa_interest_code_maintence.delete');
    			}
		    }else if(vm.screenModule == 'PPF'){
    			if ($state.current.name == 'ppf_interest_code_maintence.inquiry_detail' || $state.current.name == 'ppf_interest_code_maintence.add' || $state.current.name == 'ppf_interest_code_maintence.verify' || $state.current.name == 'ppf_interest_code_maintence.delete' || $state.current.name == 'ppf_interest_code_maintence.modify') {
    				$state.go('ppf_interest_code_maintence.inquiry');
    			}else if($state.current.name == 'ppf_interest_code_maintence.modify_detail'){
    			    $state.go('ppf_interest_code_maintence.modify');
    			}else if($state.current.name == 'ppf_interest_code_maintence.verify_detail'){
    			    $state.go('ppf_interest_code_maintence.verify');
    			}else if($state.current.name == 'ppf_interest_code_maintence.delete_detail'){
                    $state.go('ppf_interest_code_maintence.delete');
    			}
		    }else if(vm.screenModule == 'SCSS'){
    			if ($state.current.name == 'scss_interest_code_maintence.inquiry_detail' || $state.current.name == 'scss_interest_code_maintence.add' || $state.current.name == 'scss_interest_code_maintence.verify' || $state.current.name == 'scss_interest_code_maintence.delete' || $state.current.name == 'scss_interest_code_maintence.modify') {
    				$state.go('scss_interest_code_maintence.inquiry');
    			}else if($state.current.name == 'scss_interest_code_maintence.modify_detail'){
    			    $state.go('scss_interest_code_maintence.modify');
    			}else if($state.current.name == 'scss_interest_code_maintence.verify_detail'){
    			    $state.go('scss_interest_code_maintence.verify');
    			}else if($state.current.name == 'scss_interest_code_maintence.delete_detail'){
                    $state.go('scss_interest_code_maintence.delete');
    			}
		    }
		}

		function cancel() {
			if ($state.current.name == 'kvp_interest_code_maintence.inquiry') {
				location.href = '#!/dc_kvp_menu';
			}else if($state.current.name == 'ppf_interest_code_maintence.inquiry'){
			    location.href = '#!/dc_ppf_menu';
			}else if($state.current.name == 'ssa_interest_code_maintence.inquiry'){
			     location.href = '#!/dc_ssa_menu';
			}else if($state.current.name == 'scss_interest_code_maintence.inquiry'){
			    location.href = '#!/dc_scss_menu';
			}
		}

		function successState(){
			if ($state.current.name == 'kvp_interest_code_maintence.add' || $state.current.name == 'kvp_interest_code_maintence.verify_detail' || $state.current.name == 'kvp_interest_code_maintence.delete_detail' || $state.current.name == 'kvp_interest_code_maintence.modify_detail') {
				$state.go('kvp_interest_code_maintence.inquiry');
			}else if($state.current.name == 'ppf_interest_code_maintence.add' || $state.current.name == 'ppf_interest_code_maintence.verify_detail' || $state.current.name == 'ppf_interest_code_maintence.delete_detail' || $state.current.name == 'ppf_interest_code_maintence.modify_detail'){
			    $state.go('ppf_interest_code_maintence.inquiry');
			}else if($state.current.name == 'ssa_interest_code_maintence.add' || $state.current.name == 'ssa_interest_code_maintence.verify_detail' || $state.current.name == 'ssa_interest_code_maintence.delete_detail' || $state.current.name == 'ssa_interest_code_maintence.modify_detail'){
			    $state.go('ssa_interest_code_maintence.inquiry');
			}else if($state.current.name == 'scss_interest_code_maintence.add' || $state.current.name == 'scss_interest_code_maintence.verify_detail' || $state.current.name == 'scss_interest_code_maintence.delete_detail' || $state.current.name == 'scss_interest_code_maintence.modify_detail'){
			    $state.go('scss_interest_code_maintence.inquiry');
			}
		}

		function staySameInState(){
		    if($state.current.name == 'kvp_interest_code_maintence.modify_detail'){
		        $state.go('kvp_interest_code_maintence.modify_detail')
		    }else if($state.current.name == 'ssa_interest_code_maintence.modify_detail'){
		        $state.go('ssa_interest_code_maintence.modify_detail')
		    }else if($state.current.name == 'ppf_interest_code_maintence.modify_detail'){
		        $state.go('ppf_interest_code_maintence.modify_detail')
		    }else if($state.current.name == 'scss_interest_code_maintence.modify_detail'){
		        $state.go('scss_interest_code_maintence.modify_detail')
		    }
		}


	}
})();