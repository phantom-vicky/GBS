(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('rbiBondInterestRateCodeMaintenanceController',
        rbiBondInterestRateCodeMaintenanceController);

    rbiBondInterestRateCodeMaintenanceController.$inject = ['$scope', '$filter', '$state', '$rootScope',
        '$stateParams', '$location', '$timeout', 'rbiBondInterestRateCodeMaintenanceService', 'commonService'
    ];



    function rbiBondInterestRateCodeMaintenanceController($scope, $filter, $state, $rootScope, $stateParams,
        $location, $timeout, rbiBondInterestRateCodeMaintenanceService, commonService) {

        var vm = this;
        vm.moduleNameBefore = "Floating Rate Savings Taxable Bonds, 2020";
        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
        vm.getRemarksList = getRemarksList;

        vm.addSubmitForm = addSubmitForm;
        vm.verifySelect = verifySelect;
        vm.verify = verify;
        vm.rejectReason = rejectReason;
        vm.deleteRecord = deleteRecord;
        vm.modifySelect = modifySelect;
        vm.modifySubmit = modifySubmit;
        vm.inquirySelect = inquirySelect;
        vm.decimalConvertInterestRateNational=decimalConvertInterestRateNational;
        vm.decimalConvertspreadRate=decimalConvertspreadRate;
        vm.decimalConvertInterestRateNationalModify=decimalConvertInterestRateNationalModify;;
        vm.fromToDateValidation=fromToDateValidation;
        vm.getEffectFromDate=getEffectFromDate;
        vm.decimalConvertsCoupon=decimalConvertsCoupon;
vm.validateInputAlphaWithSpace=validateInputAlphaWithSpace;
function validateInputAlphaWithSpace(event,id)
{
 commonService.validateInputAlphaWithSpace(event,id);
}
vm.decimalConvertspreadRateModify=decimalConvertspreadRateModify;
vm.percentageCheck = percentageCheck;
		function percentageCheck(event) {
			let decimal = event.target.value + event.key;
			let strValueLen = decimal.length;
			let dot = decimal.lastIndexOf(".");
			let dotCount = decimal.split('.').length - 1;

			if (!( (dot == -1 && strValueLen <= 2) || (dot == 1 && strValueLen <= 4) || (dot == 2 && strValueLen <= 5)))
			{	event.preventDefault();}
			if(dotCount == 2){	event.preventDefault();}
		    }
		    vm.percentageCheckChange = percentageCheckChange;
		function percentageCheckChange(id) {
            let value = vm[id] || ''; // Ensure value is a string
            let dotIndex = value.lastIndexOf(".");
            let dotCount = value.split('.').length - 1;

            // Remove extra decimal points
            if (dotCount > 1) {
                value = value.replace(/\.+$/, ''); // Remove all but the first decimal
            }

            // Limit the number of characters
            if (!( (dotIndex === -1 && value.length <= 5) || (dotIndex === 1 && value.length <= 6) || (dotIndex === 2 && value.length <= 7) )) {
                value = '';
            }

            // Ensure valid decimal places
            if (dotIndex !== -1 && value.length - dotIndex > 3) {
                value = value.slice(0, dotIndex + 3); // Limit to two decimal places
            }

            // Convert value to a number and check if it's greater than 100
            let numericValue = parseFloat(value);
            if (isNaN(numericValue) || numericValue > 100) {
                value = '';
            }

            vm[id] = value;
        }
vm.percentageCheckChangeM=percentageCheckChangeM;
function percentageCheckChangeM(id) {
 let value = vm.modifyDetail[id] || ''; // Ensure value is a string
            let dotIndex = value.lastIndexOf(".");
            let dotCount = value.split('.').length - 1;
            // Remove extra decimal points
            if (dotCount > 1) {
                value = value.replace(/\.+$/, ''); // Remove all but the first decimal
            }
            // Limit the number of characters
            if (!( (dotIndex === -1 && value.length <= 5) || (dotIndex === 1 && value.length <= 6) || (dotIndex === 2 && value.length <= 7) )) {
                value = '';
            }
            // Ensure valid decimal places
            if (dotIndex !== -1 && value.length - dotIndex > 3) {
                value = value.slice(0, dotIndex + 3); // Limit to two decimal places
            }
            // Convert value to a number and check if it's greater than 100
            let numericValue = parseFloat(value);
            if (isNaN(numericValue) || numericValue > 100) {
                value = '';
            }
            vm.modifyDetail[id] = value;
		    }


		    function getRemarksList() {
                              vm.referenceId = '055';
                              var requestData = { 'referenceId': vm.referenceId };

                              rbiBondInterestRateCodeMaintenanceService.getRemarksList(requestData).then(function(response) {
                                  vm.remarksList = response.data.result || []; // Ensure it's an array
                              }, function(error) {
                                  handleErrorResponse(error);
                              });
                          }


function decimalConvertInterestRateNational(value) {

//			let decimal = parseFloat(value);
//			let strValue=value;
//			let strValueLen=value.length;
if (value === '.') {
        vm.Interest_Rate_National = '';
        return;
    }
if (!value && value !== 0) {
        vm.coupon = '';
        return;
    }
    let decimal = parseFloat(value);


    if (isNaN(decimal)) {
        vm.coupon = '';
        return;
    }


    let strValue = value.toString();
    let strValueLen = strValue.length;

//if(value ==='.'){
//vm.Interest_Rate_National='';
//}
if(strValue[strValueLen-4] == '.' || strValue[strValueLen-5] == '.')
	{
		 decimal=  (value);
       decimal= decimal.toString();
       decimal=decimal.slice(0, -1);
                    vm.Interest_Rate_National=decimal;
	}

	//to validate zero

	if(strValue[0]=='0' && strValue[1]=='0' && strValue[2]=='0'){
    	  vm.Interest_Rate_National='';
    	}
			if(strValue[strValueLen-1] == '.')
			{

for(var i=0;i<=strValueLen-2;i++)
{
if(strValue[i] == '.')
{
        decimal=  (value);
       decimal= decimal.toString();
       decimal=decimal.slice(0, -1);
            vm.Interest_Rate_National=decimal;

}
}
			}

			if(decimal>100
			)
			{
			event.preventDefault();

                   decimal= decimal.toString();
                    decimal=decimal.slice(0, -1);


                  vm.Interest_Rate_National=decimal;


			}
			  if (decimal <= 0) {

                                vm.errorMsg = 'Please enter a value greater than 0.';
                                return false;

                              } else {
                                    vm.errorMsg = '';
                                }
/*
            vm.coupon=0;
			vm.coupon=parseFloat(vm.Interest_Rate_National)+parseFloat(vm.spreadRate);
			vm.coupon=parseFloat(vm.coupon.toFixed(2));
			*/
			if(vm.spreadRate && vm.Interest_Rate_National > 0){
            	vm.coupon=parseFloat(vm.Interest_Rate_National)+parseFloat(vm.spreadRate);
            	} else if(vm.spreadRate > 0){
                       	vm.coupon=parseFloat(vm.spreadRate);
                       	} else if(vm.Interest_Rate_National > 0){
                               	vm.coupon=parseFloat(vm.Interest_Rate_National);
                               	}  else {
            	vm.coupan=0;
            	}
            				vm.coupon=parseFloat(vm.coupon.toFixed(2));


		}

function decimalConvertspreadRateModify(value) {
if (value === '.') {
        vm.modifyDetail.spreadRate = '';
        return;
    }
if (!value && value !== 0) {
        vm.modifyDetail.coupanInterestRateBond = '';
        return;
    }
    let decimal = parseFloat(value);


    if (isNaN(decimal)) {
        vm.modifyDetail.coupanInterestRateBond = '';
        return;
    }


    let strValue = value.toString();
    let strValueLen = strValue.length;
//			let decimal = parseFloat(value);
//			let strValue=value;
//			let strValueLen=value.length;
//
//			if(value=='.'){
//			vm.spreadRate='';
//			}
if(strValue[strValueLen-5] == '.' || strValue[strValueLen-4] == '.')
	{
		 decimal=  (value);
       decimal= decimal.toString();
       decimal=decimal.slice(0, -1);
                    vm.modifyDetail.spreadRate=decimal;
	}
	//to validate zero

	if(strValue[0]=='0' && strValue[1]=='0' && strValue[2]=='0'){
        	  vm.modifyDetail.spreadRate='';
        	}

			if(strValue[strValueLen-1] == '.')
			{

for(var i=0;i<=strValueLen-2;i++)
{
if(strValue[i] == '.')
{
        decimal=  (value);
       decimal= decimal.toString();
       decimal=decimal.slice(0, -1);
                   vm.modifyDetail.spreadRate=decimal;

}
}
			}

			if(decimal>100
			)
			{
			event.preventDefault();
                   decimal= decimal.toString();
                    decimal=decimal.slice(0, -1);
                   // decimal=parseInt(decimal);

                    vm.modifyDetail.spreadRate=decimal;

                    // alert(decimal.length);
			}
//			if(!vm.Interest_Rate_National)
//                                    			{
//                                    			vm.Interest_Rate_National =0;}
if(vm.modifyDetail.spreadRate && vm.modifyDetail.interestRateNatSavCertiIn > 0){
	vm.modifyDetail.coupanInterestRateBond=parseFloat(vm.modifyDetail.interestRateNatSavCertiIn)+parseFloat(vm.modifyDetail.spreadRate);
	} else if(vm.modifyDetail.spreadRate > 0){
           	vm.modifyDetail.coupanInterestRateBond=parseFloat(vm.modifyDetail.spreadRate);
           	} else if(vm.modifyDetail.interestRateNatSavCertiIn > 0){
                   	vm.modifyDetail.coupanInterestRateBond=parseFloat(vm.modifyDetail.interestRateNatSavCertiIn);
                   	}  else {
	vm.modifyDetail.coupanInterestRateBond=0;
	}
			vm.modifyDetail.coupanInterestRateBond=vm.modifyDetail.coupanInterestRateBond.toFixed(2);

}


function decimalConvertInterestRateNationalModify(value) {
if (value === '.') {
         vm.modifyDetail.interestRateNatSavCertiIn='';
        return;
    }

//			let decimal = parseFloat(value);
//			let strValue=value;
//			let strValueLen=value.length;
if (!value && value !== 0) {
        vm.modifyDetail.coupanInterestRateBond = '';
        return;
    }
    let decimal = parseFloat(value);


    if (isNaN(decimal)) {
        vm.modifyDetail.coupanInterestRateBond = '';
        return;
    }


    let strValue = value.toString();
    let strValueLen = strValue.length;
//			if(value === '.'){
//            vm.modifyDetail.interestRateNatSavCertiIn='';
//            }
if(strValue[strValueLen-4] == '.' || strValue[strValueLen-5] == '.')
	{
		 decimal=  (value);
       decimal= decimal.toString();
       decimal=decimal.slice(0, -1);
                    vm.modifyDetail.interestRateNatSavCertiIn=decimal;
	}

	if(strValue[0]=='0' && strValue[1]=='0' && strValue[2]=='0'){
    	  vm.modifyDetail.interestRateNatSavCertiIn='';
    	}
			if(strValue[strValueLen-1] == '.')
			{

for(var i=0;i<=strValueLen-2;i++)
{
if(strValue[i] == '.')
{
        decimal=  (value);
       decimal= decimal.toString();
       decimal=decimal.slice(0, -1);
                    vm.modifyDetail.interestRateNatSavCertiIn=decimal;

}
}
			}

			if(decimal>100
			)
			{
			event.preventDefault();
        /// decimal.length;
                   decimal= decimal.toString();
                    decimal=decimal.slice(0, -1);
                   // decimal=parseInt(decimal);

                    vm.modifyDetail.interestRateNatSavCertiIn=decimal;

                   // alert(decimal.length);
			}

				  if (decimal <= 0) {

                                            vm.errorMsg = 'Please enter a value greater than 0.';
                                            return false;

                                          } else {
                                                vm.errorMsg = '';
                                            }
                    if(vm.modifyDetail.spreadRate > 0 && vm.modifyDetail.interestRateNatSavCertiIn > 0){
                   vm.modifyDetail.coupanInterestRateBond = 0;
                   vm.modifyDetail.coupanInterestRateBond = parseFloat(vm.modifyDetail.interestRateNatSavCertiIn) + parseFloat(vm.modifyDetail.spreadRate);
                   vm.modifyDetail.coupanInterestRateBond = parseFloat(vm.modifyDetail.coupanInterestRateBond.toFixed(2));
                    } else if(vm.modifyDetail.spreadRate > 0){
                   vm.modifyDetail.coupanInterestRateBond = 0;
                   vm.modifyDetail.coupanInterestRateBond =  parseFloat(vm.modifyDetail.spreadRate);
                   vm.modifyDetail.coupanInterestRateBond = parseFloat(vm.modifyDetail.coupanInterestRateBond.toFixed(2));
                    } else if(vm.modifyDetail.interestRateNatSavCertiIn > 0){
                   vm.modifyDetail.coupanInterestRateBond = 0;
                   vm.modifyDetail.coupanInterestRateBond = parseFloat(vm.modifyDetail.interestRateNatSavCertiIn);
                   vm.modifyDetail.coupanInterestRateBond = parseFloat(vm.modifyDetail.coupanInterestRateBond.toFixed(2));
		            }               else {
		                               vm.modifyDetail.coupanInterestRateBond = 0;
		            }
		            }


//function decimalConvertInterestRateNational(value) {
//
//			let decimal = parseFloat(value);
//			let strValue=value;
//			let strValueLen=value.length;
//if(strValue[strValueLen-4] == '.' || strValue[strValueLen-5] == '.')
//	{
//		 decimal=  (value);
//       decimal= decimal.toString();
//       decimal=decimal.slice(0, -1);
//                    vm.Interest_Rate_National=decimal;
//	}
//			if(strValue[strValueLen-1] == '.')
//			{
//
//for(var i=0;i<=strValueLen-2;i++)
//{
//if(strValue[i] == '.')
//{
//        decimal=  (value);
//       decimal= decimal.toString();
//       decimal=decimal.slice(0, -1);
//                    vm.Interest_Rate_National=decimal;
//
//}
//}
//			}
//
//			if(decimal>100
//			)
//			{
//
//
//			event.preventDefault();
//        /// decimal.length;
//                   decimal= decimal.toString();
//                    decimal=decimal.slice(0, -1);
//                   // decimal=parseInt(decimal);
//
//                    vm.Interest_Rate_National=decimal;
//
//                   // alert(decimal.length);
//			}
//                                    			vm.coupon=0;
//	vm.coupon=parseFloat(vm.Interest_Rate_National)+parseFloat(vm.spreadRate);
//			vm.coupon=parseFloat(vm.coupon.toFixed(2));
//		}

function decimalConvertspreadRate(value) {
if (value === '.') {
        vm.spreadRate = '';
        return;
    }
if (!value && value !== 0) {
        vm.coupon = '';
        return;
    }
    let decimal = parseFloat(value);


    if (isNaN(decimal)) {
        vm.coupon = '';
        return;
    }


    let strValue = value.toString();
    let strValueLen = strValue.length;
//			let decimal = parseFloat(value);
//			let strValue=value;
//			let strValueLen=value.length;
//
//			if(value=='.'){
//			vm.spreadRate='';
//			}
if(strValue[strValueLen-5] == '.' || strValue[strValueLen-4] == '.')
	{
		 decimal=  (value);
       decimal= decimal.toString();
       decimal=decimal.slice(0, -1);
                    vm.spreadRate=decimal;
	}
	//to validate zero
	if(strValue[0]=='0' && strValue[1]=='0' && strValue[2]=='0'){
        	  vm.spreadRate='';
        	}

			if(strValue[strValueLen-1] == '.')
			{

for(var i=0;i<=strValueLen-2;i++)
{
if(strValue[i] == '.')
{
        decimal=  (value);
       decimal= decimal.toString();
       decimal=decimal.slice(0, -1);
                   vm.spreadRate=decimal;

}
}
			}

			if(decimal>100
			)
			{
			event.preventDefault();
                   decimal= decimal.toString();
                    decimal=decimal.slice(0, -1);
                   // decimal=parseInt(decimal);

                    vm.spreadRate=decimal;

                    // alert(decimal.length);
			}
//			if(!vm.Interest_Rate_National)
//                                    			{
//                                    			vm.Interest_Rate_National =0;}
if(vm.spreadRate && vm.Interest_Rate_National > 0){
	vm.coupon=parseFloat(vm.Interest_Rate_National)+parseFloat(vm.spreadRate);
	} else if(vm.spreadRate > 0){
           	vm.coupon=parseFloat(vm.spreadRate);
           	} else if(vm.Interest_Rate_National > 0){
                   	vm.coupon=parseFloat(vm.Interest_Rate_National);
                   	}  else {
	vm.coupan=0;
	}
			vm.coupon=vm.coupon.toFixed(2);

		}
function decimalConvertsCoupon(value) {

			let decimal = parseFloat(value);
			let strValue=value;
			let strValueLen=value.length;
			if(value=='.'){
			vm.coupon='';
			}
if(strValue[strValueLen-5] == '.' || strValue[strValueLen-4] == '.')
	{
		 decimal=  (value);
       decimal= decimal.toString();
       decimal=decimal.slice(0, -1);
                    vm.coupon=decimal;
	}
	//to validate zero
	if(strValue[0]=='0' && strValue[1]=='0' && strValue[2]=='0'){
        	  vm.coupon='';
        	}

			if(strValue[strValueLen-1] == '.')
			{

for(var i=0;i<=strValueLen-2;i++)
{
if(strValue[i] == '.')
{
        decimal=  (value);
       decimal= decimal.toString();
       decimal=decimal.slice(0, -1);
                   vm.coupon=decimal;

}
}
			}

			if(decimal>100
			)
			{
			event.preventDefault();
                   decimal= decimal.toString();
                    decimal=decimal.slice(0, -1);

                    vm.coupon=decimal;

			}

		}

        if ($state.current.name == 'rbi_bond_interest_rate_code_maintenance.verify_detail' || $state.current.name == 'rbi_bond_interest_rate_code_maintenance.delete_detail') {
            vm.InterestRate = $stateParams.obj.InterestRate;
            vm.schemeCode = $stateParams.obj.schemeCode;
            vm.subCodeDescription = $stateParams.obj.subCodeDescription;

            vm.verifySelect(vm.InterestRate, 'all', vm.schemeCode);
        }
        if ($state.current.name == 'rbi_bond_interest_rate_code_maintenance.modify_detail') {
            vm.InterestRate = $stateParams.obj.InterestRate;
            vm.effFromDate = $stateParams.obj.effFromDate;
            vm.schemeCode = $stateParams.obj.schemeCode;
            vm.newEffectiveDateStr = $stateParams.obj.newEffectiveDateStr;
            vm.subCodeDescription = $stateParams.obj.subCodeDescription;
            vm.versionNo = $stateParams.obj.versionNo;

            vm.effFromDate=$filter('date')(vm.effFromDate, 'dd/MM/yyyy');
            vm.oldEffFromDate=$stateParams.obj.oldEffFromDate;

            vm.modifySelect(vm.InterestRate, vm.effFromDate, 'all', vm.oldEffFromDate);
        }
        if ($state.current.name == 'rbi_bond_interest_rate_code_maintenance.inquiry_detail') {
            vm.InterestRate = $stateParams.obj.InterestRate;
            vm.version = $stateParams.obj.version;
            vm.schemeCode = $stateParams.obj.schemeCode;
            vm.subCodeDescription = $stateParams.obj.subCodeDescription;

            vm.inquirySelect(vm.InterestRate, vm.version, 'all');
        }
function modifySelect(InterestRate, effFromDate, flag, oldEffFromDate) {
    vm.loader = true;
    var requestData;

    if (flag == null) {
        requestData = {'flag':'S'};

        rbiBondInterestRateCodeMaintenanceService.modifySelect(requestData).then(function(response) {
            vm.loader = false;
            vm.schemeList = response.data.result;
            angular.element('#branchIdSearcher').modal('toggle');
        }, function(error) {
            handleErrorResponse(error);
            vm.loader = false;
            $state.go('rbi_bond_interest_rate_code_maintenance.inquiry');
        });
    } else {
        if (flag == 'select') {
            requestData = {
               'flag':'I',
                'schemeCode': vm.schemeCode
            };
            angular.element('#InterestRateModal').modal('toggle');
        } else {
            requestData = {
                'flag':'D',
                'interestRateCode': InterestRate,
                'effFromDate': $filter('date')(oldEffFromDate, 'dd/MM/yyyy'),
                'schemeCode': vm.schemeCode,
                'versionNo': vm.versionNo,
                'subCodeDescription': vm.subCodeDescription
            };
        }

        rbiBondInterestRateCodeMaintenanceService.modifySelect(requestData).then(function(response) {
            vm.loader = false;
            vm.rejectionRemarks = response.data.result.rejectionRemarks;
            if (flag == 'select') {
                vm.InterestRateModal = response.data.result;
                vm.InterestRateModal.forEach(function(item) {
                    item.effFromDatePicker = convertToDateObject(item.effFromDate);
                });
                console.log(response.data.result);
            } else {
                vm.modifyDetail = response.data.result;
                vm.modifyDetail.effFromDate = convertToDateObject(vm.effFromDate);

                var currentStr = $filter('date')(vm.modifyDetail.effFromDate, 'dd/MM/yyyy');

                if (currentStr == vm.newEffectiveDateStr) {
//                    Swal.fire({
//                        title: "Record already exists for the effective date",
//                        text: "Do you wish to continue?",
//                        type: "warning",
//                        showCancelButton: true,
//                        cancelButtonClass: "btn-cancel",
//                        confirmButtonClass: "btn-danger",
//                        confirmButtonText: "Yes",
//                        cancelButtonText: "No",
//                        closeOnConfirm: true
//                    }, function(inputValue) {
//                        if (!inputValue) {



                      Swal.fire({
                         title: "Record already exists for the effective date",
                           text: "Do you wish to continue?",
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
                                  // Perform the verification action here if needed
                              } else if (result.dismiss === Swal.DismissReason.cancel) {
                                  $state.go('rbi_bond_interest_rate_code_maintenance.modify');
                              }
                    });
                }
            }
        }, function(error) {
            vm.loader = false;
            handleErrorResponse(error);
            document.querySelector(".modal-backdrop.in").remove();
            $state.go('rbi_bond_interest_rate_code_maintenance.inquiry');
        });
    }
}


vm.textableDatePicker=textableDatePicker;
vm.autoslashing=autoslashing;

function textableDatePicker(id,valueDate)
{
 commonService.textableDatePicker(id,valueDate);
}
function autoslashing(id)
{
 commonService.autoslashing(id);
}

        function verifySelect(interestRate, flag, schemeCode) {
                    vm.loader = true;
                      var requestData;
         if (flag == null) {
        requestData = {'flag':'S'};

        rbiBondInterestRateCodeMaintenanceService.verifySelect(requestData).then(function(response) {
            vm.loader = false;
            vm.schemeList = response.data.result;
            angular.element('#branchIdSearcher').modal('toggle');
        }, function(error) {
            handleErrorResponse(error);
            vm.loader = false;
            $state.go('rbi_bond_interest_rate_code_maintenance.inquiry');
        });
      } else {

             if (flag == 'empty') {
                requestData = {
                'schemeCode': vm.schemeCode,
                };
             }else if (flag == 'select') {
                requestData = {
                'flag':'I',
                'schemeCode': vm.schemeCode,
                };
//                 angular.element('#InterestRateModal').modal('toggle');
            } else {
                requestData = {
                   'flag':'D',
                    'interestRateCode': interestRate,
                    'schemeCode': vm.schemeCode,
                    'subCodeDescription': vm.subCodeDescription,
                }
//                 angular.element('#InterestRateModal').modal('toggle');
            }


            rbiBondInterestRateCodeMaintenanceService.verifySelect(requestData).then(function(response) {
                vm.loader = false;
                vm.rejectionRemarks = response.data.result.rejectionRemarks;
                if (flag == 'select') {
                    vm.InterestRateModal = response.data.result;
                    angular.element('#InterestRateModal').modal('toggle');
                    console.log(response.data.result);
                }
                 else {
                    vm.verifyDetail = response.data.result;
                    vm.verificationDetails=vm.verifyDetail.previousOperationType;


                }


            }, function(error) {
                vm.loader = false;


                handleErrorResponse(error);

              document.querySelector(".modal-backdrop.in").remove();

                $state.go('rbi_bond_interest_rate_code_maintenance.verify');
            });


        }
        }
              vm.today = new Date();

  function fromToDateValidation() {

             vm.dateOptions = {
                minDate: vm.today,
            }
        }

function inquirySelect(interestRate, version, flag) {
    vm.loader = true;
    var requestData;

    if (flag == null) {
        requestData = {'flag':'S'};

        rbiBondInterestRateCodeMaintenanceService.inquirySelect(requestData).then(function(response) {
            vm.loader = false;
            vm.schemeList = response.data.result;
            angular.element('#branchIdSearcher').modal('toggle');
        }, function(error) {
            handleErrorResponse(error);
            vm.loader = false;
            $state.go('rbi_bond_interest_rate_code_maintenance.inquiry');
        });
    } else {
        if (flag == 'interest') {
            requestData = {
               'flag':'I',
                'schemeCode': vm.schemeCode
            };

        }
        if (flag == 'version') {
            requestData = {
            'flag':'V',
                'interestRateCode': interestRate,
                'schemeCode': vm.schemeCode
            };

        }
        if (flag == 'all') {
            requestData = {
               'flag':'D',
                'interestRateCode': interestRate,
                'versionNo': version,
                'schemeCode': vm.schemeCode,
                'subCodeDescription': vm.subCodeDescription
            };
        }

        rbiBondInterestRateCodeMaintenanceService.inquirySelect(requestData).then(function(response) {
            vm.loader = false;
             vm.rejectionRemarks = response.data.result.rejectionRemarks;
            if (flag == 'interest') {
                vm.InterestRateModal = response.data.result;
                angular.element('#InterestRateModal').modal('toggle');
            }
            if (flag == 'version') {
                vm.versionModal = response.data.result;
                 angular.element('#versionModal').modal('toggle');
            }
            if (flag == 'all') {
                vm.inquiryDetail = response.data.result;
                vm.verificationDetails = vm.inquiryDetail.previousOperationType;
            }
        }, function(error) {
            vm.loader = false;
            handleErrorResponse(error);
            document.querySelector(".modal-backdrop.in").remove();
            $state.go('rbi_bond_interest_rate_code_maintenance.inquiry');
        });
    }
}

function getEffectFromDate() {
    console.log("Selected Interest Rate code:", vm.rateCodeMaintenanceDescription);
    vm.loader = true;
    var requestData = {'flag':'S'};
    rbiBondInterestRateCodeMaintenanceService.modifySelect(requestData).then(function(response) {
        vm.loader = false;
        vm.effFromDate = null;
        vm.versionNo = null;
        vm.InterestRateModal.forEach(function(item) {

               if (item.rateCodeMaintenanceDescription === vm.rateCodeMaintenanceDescription) {
                        vm.effFromDate = convertToDateObject(item.effFromDate);
                        vm.versionNo = item.versionNo;
                        console.log("Effective from date for selected scheme:", vm.effFromDate);
                        console.log("vm.version from date for selected scheme:", vm.versionNo);
                    }
                });


        vm.effFromDateOptions = {
            minDate: vm.effFromDate,
            showWeeks: false
        };

        $scope.$apply();
    }, function (error) {
        vm.loader = false;
        handleErrorResponse(error);
    });
}
        function verify(id) {

//            Swal.fire({
//                title: "Are you sure?",
//                text: "Do you want to Really Verify this record?",
//                type: "warning",
//                showCancelButton: true,
//                cancelButtonClass: "btn-cancel",
//                    confirmButtonClass: "btn-primary",
//                confirmButtonText: "Yes",
//                closeOnConfirm: false
//            }, function() {




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
                    'id': id,

                }
                rbiBondInterestRateCodeMaintenanceService.verify(requestData).then(function(response) {
                    vm.loader = false;
                    vm.msg = "Bond Interest Rate Code Verified Successfully.";
                    Swal.fire('Verified!', vm.msg, 'success')
                    $state.go('rbi_bond_interest_rate_code_maintenance.inquiry')
                }, function(error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                  document.querySelector(".modal-backdrop.in").remove();
                    $state.go('rbi_bond_interest_rate_code_maintenance.inquiry');
                })
         }   });
        }

     function deleteRecord(id) {
//         function handleKeyPress(event) {
//             var key = event.key;
//             var input = event.target.value;
//
//             if (key === ' ' && input.slice(-1) === ' ') {
//                 event.preventDefault();
//             }
//
//             if (!key.match(/^[a-zA-Z\s]$/) && key !== 'Backspace' && key !== 'Enter') {
//                 event.preventDefault();
//             }
//             if (input.length >= 100 && key !== 'Backspace' && key !== 'Enter') {
//                     event.preventDefault();
//             }
//         }
//function handleInput(event) {
//    var input = event.target.value;
//
//    // Allow only letters and spaces
//    if (!input.match(/^[a-zA-Z\s]*$/)) {
//        event.target.value = input.replace(/[^a-zA-Z\s]/g, '');
//    }
//
//    // Prevent exceeding the length limit
//    if (input.length > 100) {
//        event.target.value = input.slice(0, 100);
//    }
//}
//         document.addEventListener('keypress', handleKeyPress);
//         document.addEventListener('input', handleInput);
//
//         Swal.fire({
//             title: "Remarks",
//             text: "",
//             type: "input",
//             inputPlaceholder: "Enter your remarks here",
//             showCancelButton: true,
//             cancelButtonClass: "btn-cancel",
//             confirmButtonClass: "btn-primary",
//             confirmButtonText: "Submit",
//             animation: "slide-from-top",
//             closeOnConfirm: false,
//             showLoaderOnConfirm: true,
//             inputValidator: function(value) {
//            // Trim the value to remove leading and trailing spaces
//            const trimmedValue = value.trim();
//
//            // Check if the trimmed value is empty
//            if (trimmedValue === "") {
//                return "Please enter your remarks!";
//            }
//
//                 if (/^[a-zA-Z\s]*$/.test(value.trim())) {
//                     if (/\s\s/.test(value)) {
//                         return "Please enter only single spaces between words!";
//                     }
//                     document.removeEventListener('keypress', handleKeyPress);
//                     return true;
//                 } else {
//                     return "Please enter only alphabetic characters!";
//                 }
//             }
//         }, function(inputValue) {
//             if (inputValue === false) {
//                 document.removeEventListener('keypress', handleKeyPress);
//                 return false;
//             }
//
//        const trimmedInputValue = inputValue.trim();
//        if (trimmedInputValue === "") {
//            Swal.fire.showInputError("Please enter the remarks!");
//            return false;
//        }


//             vm.rejectionRemarks = trimmedInputValue;

             var requestData = {
                 id: id,
                 rejectionRemarks: vm.remarks
             };

             rbiBondInterestRateCodeMaintenanceService.deleteRecord(requestData)
                 .then(function(response) {
                     vm.loader = false;
                     Swal.fire("Rejected!", "Rejected successfully", "success");
                     $state.go('rbi_bond_interest_rate_code_maintenance.inquiry');
                     const backdrop = document.querySelector('.modal-backdrop');
                     if (backdrop) {
                       backdrop.parentNode.removeChild(backdrop);
                       document.body.classList.remove('modal-open');
                     }
                 })
                 .catch(function(error) {
                     vm.loader = false;
                     handleErrorResponse(error);
                     const backdrop = document.querySelector('.modal-backdrop');
                     if (backdrop) {
                       backdrop.parentNode.removeChild(backdrop);
                       document.body.classList.remove('modal-open');
                     }
                     $state.go('rbi_bond_interest_rate_code_maintenance.inquiry');
                 });

     }

  function rejectReason() {
      angular.element("#permissionMappingDelete").modal("toggle");
    }

vm.minValueOne=minValueOne;
    function minValueOne(event,entity) {


        if(parseInt(event.target.value) == 0){

                Swal.fire("Error!","Minimum value is 1", "error");
              vm[entity]='';

                }

        }


        function addSubmitForm(flag) {
            vm.loader = true;
            vm.modalSearch = '';
            if (flag == 'Y') {
                var requestData = {'flag': 'Y'};

                rbiBondInterestRateCodeMaintenanceService.addSubmitForm(requestData).then(function(response) {
                    vm.loader = false;
                    vm.schemeList = response.data.result;
                    angular.element('#branchIdSearcher').modal('toggle');
                }, function(error) {
                    handleErrorResponse(error);
                    vm.loader = false;
                    $state.go('rbi_bond_interest_rate_code_maintenance.inquiry');
                });
            }  else {

                       vm.loader = false;

//                       Swal.fire({
//                           title: "Are you sure?",
//                           text: "Do you want to Really Add this record?",
//                           type: "warning",
//                           showCancelButton: true,
//                           cancelButtonClass: "btn-cancel",
//                           confirmButtonClass: "btn-primary",
//                           confirmButtonText: "Yes",
//                           closeOnConfirm: false,
//                           allowOutsideClick: false,
//                           allowEscapeKey: false
//                       }, function(isConfirm) {
//                           if (isConfirm) {



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
                                    'flag': 'D',
                                   'moduleName': vm.moduleName,
                                   'schemeCode': vm.schemeCode,
                                   'rateCodeMaintenance': vm.interestRate,
                                   'rateCodeMaintenanceDescription': vm.interestRateCodeDes,
                                   'versionNo': vm.version,
                                   'effFromDate': $filter('date')(vm.effectiveFromDateObj, "dd/MM/yyyy"),
                                   'fixedFloatingInterest': vm.fixedFloatingInterest,
                                   'interestRateNatSavCertiIn': vm.Interest_Rate_National,
                                   'spreadRate': vm.spreadRate,
                                   'remarks': vm.remarks,
                                   'couponCode': vm.coupon,
                               };


                               rbiBondInterestRateCodeMaintenanceService.addSubmitForm(requestData).then(function(response) {
                                   vm.loader = false;
                                   vm.msg = "Interest Version for BOND created successfully";
                                   Swal.fire('Added!', vm.msg, 'success');
                                   $state.go('rbi_bond_interest_rate_code_maintenance.inquiry');
                               }, function(error) {
                                   vm.loader = false;
                                   handleErrorResponse(error);
                                   $state.go('rbi_bond_interest_rate_code_maintenance.inquiry');
                               });
                           } else {

                               vm.loader = false;
                               Swal.fire.close();
                           }
                       });
                   }
        }


        function modifySubmit() {

//            Swal.fire({
//                title: "Are you sure?",
//                text: "Do you want to Really Modify this record?",
//                type: "warning",
//                showCancelButton: true,
//                cancelButtonClass: "btn-cancel",
//                confirmButtonClass: "btn-primary",
//                confirmButtonText: "Yes",
//                closeOnConfirm: false
//            }, function() {


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


                var requestData = {
                    'id': vm.modifyDetail.pkColumnValue,
                    'interestRateNatSavCertiIn': vm.modifyDetail.interestRateNatSavCertiIn,
                    'spreadRate': vm.modifyDetail.spreadRate,
                    'remarks': vm.modifyDetail.remarks,
                    'effFromDate': $filter('date')(vm.modifyDetail.effFromDate, 'dd-MMM-yyyy'),
                    'schemeCode': vm.schemeCode,
                    'couponCode': "",
                    'fixedFloatingInterest' : vm.modifyDetail.fixedFloatingInterest,
                    'coupanInterestRateBond' : vm.modifyDetail.coupanInterestRateBond,
                }
                rbiBondInterestRateCodeMaintenanceService.modifySubmit(requestData).then(function(response) {
                    vm.loader = false;
                    vm.msg = "Interest Rate  Modified Successfully’"
                    Swal.fire('Modified!', vm.msg, 'success')
                    $state.go('rbi_bond_interest_rate_code_maintenance.inquiry')
                }, function(error) {
                    vm.loader = false;
                    handleErrorResponse(error);

                    $state.go('rbi_bond_interest_rate_code_maintenance.inquiry');
                })
          }  });
        }



vm.clearCoupon = function() {
    if (vm.fixedFloatingInterest === 'Y' || vm.fixedFloatingInterest === 'N') {
        vm.coupon = '';
        vm.spreadRate = '';
        vm.Interest_Rate_National = '';
    }
};


$scope.$watch('vm.fixedFloatingInterest', function(newValue, oldValue) {
    vm.clearCoupon();
});
        vm.effFromDateOptions = {
         minDate: vm.effFromDatepicker,

            showWeeks: false
        }



    }
})();