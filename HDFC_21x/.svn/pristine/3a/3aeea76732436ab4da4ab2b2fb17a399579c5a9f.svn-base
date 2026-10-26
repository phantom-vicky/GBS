(function() {

	'use strict';

	angular.module('gbmUltraApp').controller('tdsParameterRbiBondController',
		tdsParameterRbiBondController);

	tdsParameterRbiBondController.$inject = ['$scope', '$filter', '$state', '$rootScope',
		'$stateParams', '$location', 'tdsParameterRbiBondService', 'commonService'
	];

	function tdsParameterRbiBondController($scope, $filter, $state, $rootScope, $stateParams,
		$location, tdsParameterRbiBondService, commonService) {
		var vm = this;
		vm.getRemarksList = getRemarksList;
		vm.solId = $rootScope.globals.currentUser.solId;
		vm.solDesc = $rootScope.globals.currentUser.solDesc;
		vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
		vm.showCancelConfirmation = showCancelConfirmation;
		vm.decimalConvert = decimalConvert;
		vm.dateValidation = dateValidation;
		vm.currentDate = new Date();
		vm.fetchModuleName = fetchModuleName;
		vm.versionEmpty = versionEmpty;
		  		vm.rejectReason = rejectReason;
		//Add Functions
		vm.submitAddForm = submitAddForm;
		vm.addValidation = addValidation;
		vm.getModuleName = getModuleName;
		//Verify and Delete Functions
		vm.submitVerifyForm = submitVerifyForm;
		vm.getVerifyDeleteSelect = getVerifyDeleteSelect;
		vm.getVerifyDeleteTorch = getVerifyDeleteTorch;
		vm.submitDeleteForm = submitDeleteForm;
		//Inquiry Functions
		vm.getInquiryTdsCodeTourch = getInquiryTdsCodeTourch;
		vm.getInquiryVersionNoTourch = getInquiryVersionNoTourch;
		vm.getInquirySelect = getInquirySelect;
		//Modify Functions
		vm.modifySubmit = modifySubmit;
		vm.modifySelectDetails = modifySelectDetails;
		vm.getModifyTorch = getModifyTorch;

		if ($state.current.name == 'tds_parameter_rbi_bond.add_detail') {
			if ($stateParams.obj) {
			vm.schemeName = $stateParams.obj.schemeName;
			vm.schemeCode = $stateParams.obj.schemeCode;
				vm.solId = $stateParams.obj.solId;
				vm.solDesc = $stateParams.obj.solDesc;
				vm.moduleId = $stateParams.obj.moduleId;
				vm.tdsParamCodeDesc = $stateParams.obj.tdsParamCodeDesc;
				vm.tdsParamCode = $stateParams.obj.tdsParamCode;
				vm.effFromDate = $stateParams.obj.effFromDate;
				vm.moduleName = $stateParams.obj.moduleName;
				addValidation();
			} else {
				$state.go('tds_parameter_rbi_bond.add');
			}
		} else if ($state.current.name == 'tds_parameter_rbi_bond.modify_detail') {
			if ($stateParams.obj) {
			vm.schemeName = $stateParams.obj.schemeName;
			vm.schemeCode = $stateParams.obj.schemeCode;
				vm.moduleId = $stateParams.obj.moduleId;
				vm.tdsParamCodeDesc = $stateParams.obj.tdsParamCodeDesc;
				vm.tdsParamCode = $stateParams.obj.tdsParamCode;
				vm.effFromDate = $stateParams.obj.effFromDate;
				vm.moduleName = $stateParams.obj.moduleName;
				modifySelectDetails();
			} else {
				$state.go('tds_parameter_rbi_bond.modify');
			}
		} else if ($state.current.name == 'tds_parameter_rbi_bond.verify_detail') {
			if ($stateParams.obj) {
			vm.schemeName = $stateParams.obj.schemeName;
			vm.schemeCode = $stateParams.obj.schemeCode;
				vm.moduleId = $stateParams.obj.moduleId;
				vm.tdsParamCodeDesc = $stateParams.obj.tdsParamCodeDesc;
				vm.tdsParamCode = $stateParams.obj.tdsParamCode;
				vm.effFromDate = $stateParams.obj.effFromDate;
				vm.versionNo = $stateParams.obj.versionNo;
				vm.moduleName = $stateParams.obj.moduleName;
				getVerifyDeleteSelect('V');
			} else {
				$state.go('tds_parameter_rbi_bond.verify');
			}
		} else if ($state.current.name == 'tds_parameter_rbi_bond.inquiry_detail') {
			if ($stateParams.obj) {
			vm.schemeName = $stateParams.obj.schemeName;
			vm.schemeCode = $stateParams.obj.schemeCode;
				vm.moduleId = $stateParams.obj.moduleId;
				vm.tdsParamCodeDesc = $stateParams.obj.tdsParamCodeDesc;
				vm.tdsParamCode = $stateParams.obj.tdsParamCode;
				vm.effFromDate = $stateParams.obj.effFromDate;
				vm.versionNo = $stateParams.obj.versionNo;
				vm.moduleName = $stateParams.obj.moduleName;
				getInquirySelect();
			} else {
				$state.go('tds_parameter_rbi_bond.inquiry');
			}
		}
		//Get Module Name
		function getModuleName() {
			vm.referenceId = 'RBI Bond';
			var requestData = {
				'referenceId': vm.referenceId
			}
			tdsParameterRbiBondService.getRbiBondModuleNameDetails(requestData).then(function(response) {
				vm.moduleNameList = response.data.result;
			}, function(error) {
				handleErrorResponse(error);
			});
		}
		function fetchModuleName(item) {
			for (let i = 0; i < vm.moduleNameList.length; i++) {
				if (vm.moduleNameList[i].moduleId == item) {
					vm.moduleName = vm.moduleNameList[i].moduleName;
					break;
				}
			}
		}

		  function getRemarksList() {
              vm.referenceId = '055';
              var requestData = { 'referenceId': vm.referenceId };
              tdsParameterRbiBondService.getRemarksList(requestData).then(function (response) {
                vm.remarksList = response.data.result || []; // Ensure it's an array
              }, function (error) {
                handleErrorResponse(error);
              });
            }

		// Modify Select details
		function modifySelectDetails() {
			vm.loader = true;
			vm.searchSolId = '';
			var requestData = {
				'moduleId': vm.moduleId,
				'effFromDate': $filter('date')(vm.effFromDate, 'dd-MMM-yyyy'),
				'tdsParamCode': vm.tdsParamCode
			}
			tdsParameterRbiBondService.getModifySelect(requestData).then(function(response) {
				vm.loader = false;
				vm.verifyDetailsList = response.data.result;
				vm.verificationDetails = response.data.result.prevOperType;
				vm.tdsParameterList = response.data.result.masterOldNewValueModel;
				vm.tdsParameterList.C005.oldValue = convertToDateObject(vm.tdsParameterList.C005.oldValue);
				vm.tdsParameterList.effectiveFromOldDate = vm.tdsParameterList.C005.oldValue;
				vm.id = vm.verifyDetailsList.pkColumnValue;
				vm.dateOptionsM = {
					minDate: vm.effFromDate,
					showWeeks: false
				};
			}, function(error) {
				handleErrorResponse(error);
				$state.go('tds_parameter_rbi_bond.modify');
				vm.loader = false;
			});
		}
		/* Verify/Delete Select Details */
		function getVerifyDeleteSelect(flg) {
			vm.loader = true;
			vm.searchSolId = '';
			var requestData = {
				'moduleId': vm.moduleId,
				'versionNo': vm.versionNo,
				'tdsParamCode': vm.tdsParamCode
			}
			tdsParameterRbiBondService.getVerifyDelSelectDetails(requestData).then(function(response) {
				vm.loader = false;
				vm.verifyDetailsList = response.data.result;
				vm.verificationDetails = response.data.result.prevOperType;
				vm.tdsParameterList = response.data.result.masterOldNewValueModel;
			}, function(error) {
				handleErrorResponse(error);
				if (flg == 'V')
					$state.go('tds_parameter_rbi_bond.verify');
				else
					$state.go('tds_parameter_rbi_bond.delete');
				vm.loader = false;
			});
		}
		vm.percentageCheck = percentageCheck;
//		function percentageCheck(event) {
//            // Get the current value and the key pressed
//            let inputValue = event.target.value;
//            let key = event.key;
//
//            // Combine the existing value with the new key
//            let combinedValue = inputValue + key;
//
//            // Regular expressions to match the allowed formats
//    let regex = /^\d{0,3}(\.\d{0,2})?$/;
//            // Check if the combined value matches the regex pattern
//            if (!regex.test(combinedValue)) {
//                // Prevent the default action if the value doesn't match the pattern
//                event.preventDefault();
//            }
//        }

vm.formatPercentage=formatPercentage;
                              function formatPercentage(value,mode) {
                                  // Ensure the value is a valid number
                                  let sanitizedValue = String(value).trim();  // Convert value to string if it's not already
                                      // Remove any non-digit characters except for the decimal point
                                      sanitizedValue = sanitizedValue.replace(/[^0-9.]/g, '');
                                      // If there is a decimal, separate it
                                      let [integerPart, decimalPart] = sanitizedValue.split('.');
                                       if(mode=="PA"){
                                      // Limit integer part to two digits
                                      if (integerPart.length > 2&& integerPart !== "100") {
                                          integerPart = integerPart.substring(0, 2); // Take only the first two digits
                                          vm.tdsRatePan=integerPart;
                                          return;
                                      }
                                      }else if(mode=="PN"){
                                      // Limit integer part to two digits
                                    if (integerPart.length > 2&& integerPart !== "100") {
                                        integerPart = integerPart.substring(0, 2); // Take only the first two digits
                                        vm.tdsRateNoPan=integerPart;
                                        return;
                                    }
                                    }else if(mode=="MPA"){
                                       if (integerPart.length > 2&& integerPart !== "100") {
                                           integerPart = integerPart.substring(0, 2); // Take only the first two digits
                                           vm.tdsParameterList.C008.oldValue=integerPart;
                                           return;
                                       }
                                       }else if(mode=="MPN"){
                                     if (integerPart.length > 2&& integerPart !== "100") {
                                         integerPart = integerPart.substring(0, 2); // Take only the first two digits
                                         vm.tdsParameterList.C009.oldValue=integerPart;
                                         return;
                                     }
                                     }
                                  return value; // Return the original value if it's invalid or empty
                              };
function percentageCheck(event) {
    let inputValue = event.target.value;
    let key = event.key;

    // Combine the existing value with the new key
    let combinedValue = inputValue + key;

    // Regular expression to match allowed formats
    let regex = /^(100(\.0{1,2})?|[1-9]?\d(\.\d{0,2})?)$/;

    // Check if the combined value matches the regex pattern
    if (!regex.test(combinedValue)) {
        event.preventDefault();
    }
}
		/*function percentageCheck(event) {
			let decimal = event.target.value + event.key;
			let strValueLen = decimal.length;
			let dot = decimal.lastIndexOf(".");
			let dotCount = decimal.split('.').length - 1;

			if (!( (dot == -1 && strValueLen <= 2) || (dot == 1 && strValueLen <= 4) || (dot == 2 && strValueLen <= 5)))
			{	event.preventDefault();}
			if(dotCount == 2){	event.preventDefault();}
		    }*/
vm.percentageCheckChange = percentageCheckChange;
		function percentageCheckChange(id) {

		if(vm.tdsRatePan){
if(id&&id[0]==='.'){
event.preventDefault();
vm.tdsRatePan='';
return;
}
}
if(vm.tdsRateNoPan){
if(id&&id[0]==='.'){
event.preventDefault();
vm.tdsRateNoPan='';
return;
}
}
if(vm.educationCess){
if(id&&id[0]==='.'){
event.preventDefault();
vm.educationCess='';
return;
}
}
if(vm.otherCess){
if(id&&id[0]==='.'){
event.preventDefault();
vm.otherCess='';
return;
}
}
}



		    vm.percentageCheckChangeM = percentageCheckChangeM;
		function percentageCheckChangeM(id) {

		if(vm.tdsParameterList.C008.oldValue){
	if(id&&id[0]==='.'){
    event.preventDefault();
    vm.tdsParameterList.C008.oldValue='';
    return;
    }
    }
        		if(vm.tdsParameterList.C010.oldValue){
        	if(id&&id[0]==='.'){
            event.preventDefault();
            vm.tdsParameterList.C010.oldValue='';
            return;
            }
            }
            		if(vm.tdsParameterList.C011.oldValue){
            	if(id&&id[0]==='.'){
                event.preventDefault();
                vm.tdsParameterList.C011.oldValue='';
                return;
                }
                }
		    }
		    vm.percentageCheckChangeM1=percentageCheckChangeM1;
function percentageCheckChangeM1(id) {
	if(id&&id[0]==='.'){
    event.preventDefault();
    vm.tdsParameterList.C009.oldValue='';
    return;
    }
		    }

		/* Add Submit */
		function submitAddForm() {
//			Swal.fire({
//					title: "Are you sure?",
//					text: "Do you want to Really Add this record?",
//					type: "warning",
//					showCancelButton: true,
//					cancelButtonClass: "btn-cancel",
//					confirmButtonClass: "btn-primary",
//					confirmButtonText: "Yes, Add it!",
//					closeOnConfirm: false,
//					showLoaderOnConfirm: true
//				},
//				function() {



                 Swal.fire({
                                     title: "Are you sure?",
                                     text: "Do you want to Really Add this record?",
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
					var requestData = {
						'moduleId': vm.moduleId,
						'versionNo': vm.versionNo,
						'tdsParamCode': vm.tdsParamCode,
						'tdsParamCodeDesc': vm.tdsParamCodeDesc,
						'effFromDate': $filter('date')(vm.effFromDate, 'dd/MM/yyyy'),
						'exceptionForSeniorCitizen': vm.tdsAnnualInterest,
						'tdsRateWithPan': vm.tdsRatePan,
						'tdsRateWithoutPan': vm.tdsRateNoPan,
						'educationCess': vm.educationCess,
						'otherCess': vm.otherCess,
						'fifteenGlimit': vm.fifteenGlimit,
						'fifteenHlimit': vm.fifteenHlimit,
						'remark': vm.remark
					};
					tdsParameterRbiBondService.submitAddForm(requestData).then(function(response) {
						Swal.fire("Success", 'TDS Parameters for BOND created successfully', "success");
						$state.go('tds_parameter_rbi_bond.inquiry');
					}, function(error) {
						handleErrorResponse(error);
						$state.go('tds_parameter_rbi_bond.add');
					})
			}	});
		}

		function convertToColumnValuesArrayOldNew(objectToBeConvert) {
			if (typeof(objectToBeConvert) === "object") {
				let resultArray = [];
				for (let key of Object.keys(objectToBeConvert)) {
					if (objectToBeConvert[key])
						resultArray.push({
							colName: key,
							value: objectToBeConvert[key].oldValue
						})
				}
				return resultArray;
			} else {
				throw "Only objects can be converted"
			}
		}

		/* Submit Modify*/
		function modifySubmit() {
			var currentStr = $filter('date')(vm.tdsParameterList.C005.oldValue, 'dd-MMM-yyyy');
			var dbStr = $filter('date')(vm.tdsParameterList.effectiveFromOldDate, 'dd-MMM-yyyy');
			if (currentStr == dbStr) {
				var titleString = "Record already exists for the effective date";
				var textString = "Do you wish to continue?Yes/No";
			} else {
				var titleString = "Are you sure?";
				var textString = "Do you want to Really Modify this record?";
			}
//			Swal.fire({
//					title: titleString,
//					text: textString,
//					type: "warning",
//					showCancelButton: true,
//					cancelButtonClass: "btn-cancel",
//					confirmButtonClass: "btn-primary",
//					confirmButtonText: "Yes",
//					closeOnConfirm: false
//				},
//				function() {


             Swal.fire({
                                 title: "Are you sure?",
                                 text: "Do you want to Really Modify this record?",
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
					vm.loader = true;
					vm.tdsParameterList.effectiveFromOldDate = null;
					vm.tdsParameterListModel = JSON.parse(JSON.stringify(vm.tdsParameterList));
					vm.tdsParameterListModel.C005.oldValue = $filter('date')(vm.tdsParameterList.C005.oldValue, 'dd-MMM-yyyy');
					var toDateObj = convertToDateObject(vm.tdsParameterList.C006.oldValue);
					vm.tdsParameterListModel.C006.oldValue = $filter('date')(toDateObj, 'dd-MMM-yyyy');
					var requestData = {
						'id': vm.id,
						'columnValues': convertToColumnValuesArrayOldNew(vm.tdsParameterListModel),
					};
					tdsParameterRbiBondService.submitModifyForm(requestData).then(function(response) {
						vm.loader = false;
						Swal.fire("Success", 'TDS Parameter Modified Successfully', "success");
						$state.go('tds_parameter_rbi_bond.inquiry');
					}, function(error) {
						vm.loader = false;
						handleErrorResponse(error);
						$state.go('tds_parameter_rbi_bond.modify');
					})
			}	});
		}

  function rejectReason() {
              angular.element('#permissionMappingDelete').modal('toggle');
              }

     vm.validateInput=validateInput;
              function validateInput(event,id){

                  const textBox = document.getElementById(id);

                    var charCode = event.which || event.keyCode;
                      var charTyped = String.fromCharCode(charCode);

              if(textBox)
              {
               const cursorPosition = textBox.selectionStart;
                  if(cursorPosition == 0 && charTyped === ' ')
                  {
                          event.preventDefault();
                          return;

                  }
                  }
                    var currentValue = event.target.value + charTyped;
                      var pattern = /^[a-zA-Z\s]*'?([a-zA-Z\s]*'?)*$/;


                      if (!pattern.test(currentValue)) {
                          event.preventDefault();
                      }
                  }

		/* Verify Submit */
		function submitVerifyForm() {
//			Swal.fire({
//					title: "Are you sure?",
//					text: "Do you want to Really Verify this record?",
//					type: "warning",
//					showCancelButton: true,
//					cancelButtonClass: "btn-cancel",
//					confirmButtonClass: "btn-primary",
//					confirmButtonText: "Yes",
//					closeOnConfirm: false
//				},
//
//				function() {

                Swal.fire({
                                    title: "Are you sure?",
                                    text: "Do you want to Really Verify this record?",
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
					vm.loader = true;
					var requestData = {
						'id': vm.verifyDetailsList.pkColumnValue
					};
					tdsParameterRbiBondService.submitVerifyForm(requestData).then(function(response) {
						vm.loader = false;
						Swal.fire("Success", 'TDS Parameter Verified Successfully', "success");
						$state.go('tds_parameter_rbi_bond.inquiry')
					}, function(error) {
						vm.loader = false;
						handleErrorResponse(error);
						$state.go('tds_parameter_rbi_bond.verify');
					})
			}	});
		}


function submitDeleteForm() {
        vm.loader = true;
        var requestData = {
            'id': vm.verifyDetailsList.pkColumnValue,
            'rejectionRemarks': vm.remarks
        };
        tdsParameterRbiBondService.submitDeleteForm(requestData).then(function(response) {
                        if (response.data.message === 'SUCCESS') {
                            Swal.fire("Rejected!", "TDS Parameter Rejected Successfully", "success");
                            location.href = '#!/tds_parameter_rbi_bond/action/inquiry';
                            const backdrop = document.querySelector('.modal-backdrop');
                            if (backdrop) {
                              backdrop.parentNode.removeChild(backdrop);
                              document.body.classList.remove('modal-open');
                            }
                        } else {
                            vm.loader = false;
                            Swal.fire("WARNING!", response.data.message, "error");
                            location.href = '#!/tds_parameter_rbi_bond/action/inquiry';
                            const backdrop = document.querySelector('.modal-backdrop');
                            if (backdrop) {
                              backdrop.parentNode.removeChild(backdrop);
                              document.body.classList.remove('modal-open');
                            }
                        }
                           }, function(error) {
                           vm.loader = false;
                           handleErrorResponse(error);
                           const backdrop = document.querySelector('.modal-backdrop');
                           if (backdrop) {
                             backdrop.parentNode.removeChild(backdrop);
                             document.body.classList.remove('modal-open');
                           }
                              location.href = '#!/rbi_bond_toc_slab_maintenance/action/inquiry';
        });
        }


		// Cancel Confirmation
		function showCancelConfirmation(state) {
//			Swal.fire({
//					title: "Confirm Cancel",
//					text: "Do you really want to cancel?",
//					type: "warning",
//					showCancelButton: true,
//					cancelButtonClass: "btn-cancel",
//					confirmButtonClass: "btn-danger",
//					confirmButtonText: "Yes",
//					cancelButtonText: "No",
//					closeOnConfirm: true
//				},
//				function() {


              Swal.fire({
                                  title: "Confirm Cancel?",
                                  text: "Do you Really Want To Cancel",
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
			}	});
		}

		//Get Inquiry Version No Tourch Details
		function getInquiryTdsCodeTourch(moduleId) {
			vm.loader = true;
			vm.searchSolId = '';
			var requestData = {
				'moduleId': moduleId
			}
			tdsParameterRbiBondService.getInquirySelectDetails(requestData).then(function(response) {
				vm.loader = false;
				if(moduleId){
				vm.tdsCodeList = response.data.result.tdsCode;
				angular.element('#tdsCodeModal').modal('toggle');
				}else{
				vm.schemeNameList = response.data.result.tdsCode;
                angular.element('#schemeNameSearcherPopUp').modal('toggle');
				}
			}, function(error) {
				handleErrorResponse(error);
				vm.loader = false;
			});
		}

		//Get Inquiry Version No Tourch Details
		function getInquiryVersionNoTourch(moduleId) {
			vm.loader = true;
			vm.searchSolId = '';
			var requestData = {
				'moduleId': moduleId,
				'tdsParamCode': vm.tdsParamCode
			}
			tdsParameterRbiBondService.getInquirySelectDetails(requestData).then(function(response) {
				vm.loader = false;
				vm.versionNoList = response.data.result.versionNoList;
				angular.element('#versionModal').modal('toggle');
			}, function(error) {
				handleErrorResponse(error);
				vm.loader = false;
			});
		}

		function versionEmpty() {
			vm.tdsParamCode = '';
			vm.tdsParamCodeDesc = '';
			vm.versionNo = '';
			vm.effFromDate = '';
		}
		//Get Inquiry Select Details
		function getInquirySelect() {
			vm.loader = true;
			vm.searchSolId = '';
			var requestData = {
				'moduleId': vm.moduleId,
				'versionNo': vm.versionNo,
				'tdsParamCode': vm.tdsParamCode
			}
			tdsParameterRbiBondService.getInquirySelectDetails(requestData).then(function(response) {
				vm.loader = false;
				vm.verifyDetailsList = response.data.result;
				vm.verificationDetails = response.data.result.prevOperType;
				vm.tdsParameterList = response.data.result.masterOldNewValueModel;
			}, function(error) {
				handleErrorResponse(error);
				vm.loader = false;
			});
		}

		//add validation function
		function addValidation() {
			if (vm.moduleId) {
				var requestData = {
					'moduleId': vm.moduleId,
					'moduleName': vm.moduleName,
					'tdsParamCode': vm.tdsParamCode,
					'tdsParamCodeDesc': vm.tdsParamCodeDesc
				}
			} else {
				var requestData = {
					'moduleId': null
				}
			}
			tdsParameterRbiBondService.addValidation(requestData).then(function(response) {
				vm.loader = false;
				if (!vm.moduleId) {
					vm.schemeNameList = response.data.result.tdsCode;
                    angular.element('#schemeNameSearcherPopUp').modal('toggle');
				}
			}, function(error) {
				handleErrorResponse(error);
				$state.go('tds_parameter_rbi_bond.inquiry');
			})
		}

		function decimalConvert(flg, errorMsg, modifyFlg) {
			let decimal = parseFloat(flg);

			if (decimal < 100) {
				if (errorMsg == 'P1')
					vm.tdsRatePanError = false;
				if (errorMsg == 'P2')
					vm.tdsRateNoPanError = false;
				if (errorMsg == 'P3')
					vm.educationCessError = false;
				if (errorMsg == 'P4')
					vm.otherCessError = false;

				return true;
			} else {
				if (errorMsg == 'P1') {
					vm.tdsRatePanError = 'Min 2 Digit Decimal Number(' + decimal + ')';
					vm.tdsRatePan = '';
					if (modifyFlg) {
						if (vm.tdsParameterList.tdsRatePan)
							vm.tdsParameterList.tdsRatePan = '';
					}
					return false;
				}
				if (errorMsg == 'P2') {
					vm.tdsRateNoPanError = 'Min 2 Digit Decimal Number(' + decimal + ')';
					vm.tdsRateNoPan = '';
					if (modifyFlg) {
						if (vm.tdsParameterList.tdsRateNoPan)
							vm.tdsParameterList.tdsRateNoPan = '';
					}
					return false;
				}
				if (errorMsg == 'P3') {
					vm.educationCessError = 'Min 2 Digit Decimal Number(' + decimal + ')';
					vm.educationCess = '';
					if (modifyFlg) {
						if (vm.tdsParameterList.educationCess)
							vm.tdsParameterList.educationCess = '';
					}
					return false;
				}
				if (errorMsg == 'P4') {
					vm.otherCessError = 'Min 2 Digit Decimal Number(' + decimal + ')';
					vm.otherCess = '';
					if (modifyFlg) {
						if (vm.tdsParameterList.otherCess)
							vm.tdsParameterList.otherCess = '';
					}
					return false;
				}
			}
		}

		//verify and delete torch function
		function getVerifyDeleteTorch(moduleId) {
			vm.loader = true;
			vm.searchSolId = '';
			var requestData = {
				'moduleId': moduleId
			}
			tdsParameterRbiBondService.getVerifyDelSelectDetails(requestData).then(function(response) {
				vm.loader = false;
				if(moduleId){
				 vm.tdsCodeList = response.data.result.tdsCode;
                 angular.element('#tdsCodeModal').modal('toggle');
				}else{
				vm.schemeNameList = response.data.result.tdsCode;
                angular.element('#schemeNameSearcherPopUp').modal('toggle');
				}
			}, function(error) {
				handleErrorResponse(error);
				vm.loader = false;
			});
		}

		function getModifyTorch(moduleId) {
			vm.loader = true;
			vm.searchSolId = '';
			var requestData = {
				'moduleId': moduleId
			}
			tdsParameterRbiBondService.getModifySelect(requestData).then(function(response) {
				vm.loader = false;
				if(moduleId){
				vm.tdsCodeList = response.data.result.tdsCode;
				angular.element('#tdsCodeModal').modal('toggle');
				vm.effFromDates = convertToDateObject(vm.tdsCodeList[0].effFromDate);
				}
				else{
                vm.schemeNameList = response.data.result.tdsCode;
                angular.element('#schemeNameSearcherPopUp').modal('toggle');
                }
			}, function(error) {
				handleErrorResponse(error);
				vm.loader = false;
			});
		}
		function dateValidation() {
			vm.dateOptions = {
				minDate: vm.currentDate
			}
		}

		vm.getInquiryVersionTourch=getInquiryVersionTourch;
         function getInquiryVersionTourch() {
                    vm.loader = true;
                    vm.searchSolId = '';
                    var requestData = {
                        'schemeCode': "",
                        'versionNo': "",
                        'effeFromDate': ""
                    }
                    tdsParameterRbiBondService.getInquirySelectDetails1(requestData).then(function(response) {
                        vm.loader = false;
                       vm.schemeNameList = response.data.result.schemeTorchList;
                        angular.element('#schemeNameSearcherPopUp').modal('toggle');
                    }, function(error) {
                        handleErrorResponse(error);
                        vm.loader = false;
                    });
                }
	}
})();