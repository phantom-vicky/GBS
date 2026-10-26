(function() {

    'use strict';

    angular.module('gbmUltraApp').controller('bondTransferInController',
        bondTransferInController);

    bondTransferInController.$inject = ['$scope', '$filter', '$state', '$rootScope',
        '$stateParams', '$location', '$timeout', 'bondTransferInService', 'commonService'
    ];

    function bondTransferInController($scope, $filter, $state, $rootScope, $stateParams,
        $location, $timeout, bondTransferInService, commonService) {

		var vm = this;
		vm.nomineeList = [];
		vm.moduleName = 'RBI Bond';
//		vm.moduleId = '0012';
        vm.formatCBSDate = formatCBSDate;
        vm.showError = showError;
		vm.solDate = $rootScope.globals.currentUser.systemDate;
		vm.systemDate = $rootScope.globals.currentUser.systemDate;
        vm.branchDate = moment(vm.solDate).format("DD-MMM-YYYY")
        vm.isMinor = isMinor;
        vm.getModeofHolding = getModeofHolding;
        vm.solDate1 = $rootScope.globals.currentUser.solDate;
var parts = vm.solDate1.split('/');
 vm.year = parts[2];
 vm.ftsDetailFetch=true;
        vm.otherBankDetails = otherBankDetails;
        vm.convertBooleanToYN = convertBooleanToYN;
        vm.operativeAccNumValidation = operativeAccNumValidation;
        vm.instAmountValidation = instAmountValidation;
        vm.fromToDateValidation = fromToDateValidation;
        vm.guardDobValidation = guardDobValidation;
        vm.removeNominee = removeNominee;
        vm.calculateSum =calculateSum;
        vm.checkAge = checkAge;
        vm.getWboBranchCode = getWboBranchCode;
        vm.getSharePer = getSharePer;
        vm.getTotalSharePer = getTotalSharePer;
        vm.getRemarksList = getRemarksList;
        vm.rejectReason=rejectReason;
		vm.addRange = addRange;
		vm.nextStep = nextStep;
        vm.convertChunk = convertChunk;
        vm.convertDateFormat = convertDateFormat;
        vm.convertNumberToWords = convertNumberToWords;
		vm.updateMinorStatus2 = updateMinorStatus2
        vm.updateMinorStatus3 = updateMinorStatus3
        vm.updateMinorStatusForAll = updateMinorStatusForAll;
        vm.removeRange = removeRange;
        vm.getCbsCustFstJoinDetailsTwo = getCbsCustFstJoinDetailsTwo;
        vm.getCbsCustFstJoinDetailsThree = getCbsCustFstJoinDetailsThree;
		vm.getCbsCustFstJoinDetails = getCbsCustFstJoinDetails;
        vm.getCbsCustFstJoinDetailsModify = getCbsCustFstJoinDetailsModify;
        vm.getCbsCustFstJoinDetailsTwoModify = getCbsCustFstJoinDetailsTwoModify;
        vm.getCbsCustFstJoinDetailsThreeModify = getCbsCustFstJoinDetailsThreeModify;
        vm.getModuleName = getModuleName;
        vm.validatePan = validatePan;
        vm.bankBranchCodeMicr=bankBranchCodeMicr;
        vm.validateCount = validateCount;
        vm.addBondTransferIn = addBondTransferIn;
        vm.showAccountValidationSuccess = showAccountValidationSuccess;
        vm.showCBSServiceWarning = showCBSServiceWarning;
        vm.modifycustomerMaster = modifycustomerMaster;
        vm.verifyCustomerMaster = verifyCustomerMaster;
        vm.deleteDetails = deleteDetails;
        vm.validateNominee = validateNominee;
        vm.removeRanges = removeRanges;
        vm.addRanges = addRanges;
        vm.validateMultiples=validateMultiples;
        vm.convertLabelValue = convertLabelValue;
        vm.splitName = splitName;
        vm.futureNotAllowed = futureNotAllowed;
        	vm.customerMasterDetails = [];
        	vm.customerMasterJointDetails = [];
            		vm.customerMasterFormDetails = {};
        vm.getGuardianDetailsMetaData=getGuardianDetailsMetaData;
        vm.getNomineeStatusMetaData = getNomineeStatusMetaData;
		vm.getGuardianDetails = getGuardianDetails;

//        vm.oprAccBranchId = '';
//        vm.validatePoolAcct = validatePoolAcct;
        vm.tabReferenceAdd = '';
        vm.tabReferenceInquiry = '';
        vm.tabReferenceVerify = '';
        vm.tabReferenceModify = '';
                                vm.interestPaidUpto1 = "";
        						vm.interestPaidUpto2 = "";
        						vm.intrstPaidMonthUpto = "";


        vm.getTorchDetails = getTorchDetails;
        vm.addDet = addDet;
        vm.getverifyTorchDetails = getverifyTorchDetails;
        vm.verifyValResponse = verifyValResponse;
        vm.getInquiryTorchDetails = getInquiryTorchDetails;
        vm.getModifySelectDetails = getModifySelectDetails;
        vm.modifyValResponse = modifyValResponse;
        vm.otherBankDetailsModify = otherBankDetailsModify;
        		vm.getInterestPaymentMode = getInterestPaymentMode;
        vm.getSegment = getSegment;
		vm.convertYNToBoolean = convertYNToBoolean;

vm.textableDatePicker=textableDatePicker;
vm.autoslashing=autoslashing;

vm.textableDatePickerManual=textableDatePickerManual;
function textableDatePickerManual(id,valueDate,item)
{
 commonService.textableDatePicker(id,valueDate);
 if (!valueDate) return;
     // Convert input and system date to Date objects
     var enteredDate = new Date(valueDate);
     var systemDate = new Date(vm.systemDate);
     // Validate if entered DOB is greater than system date
     if (enteredDate > systemDate) {
     if (id && id.startsWith('coparcenersDob')) {
        if(item?.C006){
         Swal.fire("Warning", "Date of Birth cannot be greater than the current date.", "warning");
         item.C006.oldValue = ''; // Clear invalid input
         return;
         }
     }
     if(id && id.startsWith('dateofBirth')){
         if(item?.C016){
         Swal.fire("Warning", "Date of Birth cannot be greater than the current date.", "warning");
         item.C016.oldValue = ''; // Clear invalid input
         return;
         }else{
         Swal.fire("Warning", "Date of Birth cannot be greater than the current date.", "warning");
         item.dateOfBirthOfMinor='';
         return;
         }
     }}
}

function textableDatePicker(id,valueDate)
{
 commonService.textableDatePicker(id,valueDate);
}


function autoslashing(id)
{
 commonService.autoslashing(id);
}
function futureNotAllowed(id,valueDate)
{
 commonService.futureNotAllowed(id,valueDate);
 var today = new Date();
       var birthDate = new Date(valueDate);
       var age = today.getFullYear() - birthDate.getFullYear();
       var m = today.getMonth() - birthDate.getMonth();
       if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
         age--;
       }
       vm.isMinor = age < 18;
        if (!vm.isMinor && vm.modeOfHolding === 'MH005') {
           vm.fromDate1 = null;
           Swal.fire("Warning","Legal Guardian is not allowed for above 18 years.","warning");
         }else if(valueDate){
             var today = new Date();
             today.setHours(0, 0, 0, 0);
             var birthDate = new Date(valueDate);
             if (isNaN(birthDate.getTime())) {
                 return false;
             }
             birthDate.setHours(0, 0, 0, 0);
             // Future date validation
             if (birthDate > today) {
                 if (id === 'fromDate1' || id === 'dateofBirth1') {
                     vm.fromDate1 = null;
                 } else if (id === 'fromDate2') {
                     vm.fromDate2 = null;
                 } else if (id === 'fromDate3') {
                     vm.fromDate3 = null;
                 }
                 document.getElementById(id).value = '';
                 Swal.fire("Error!", "Please don't enter future date ", "error");
                 return false;
             }
             }
}
vm.minorChecking=minorChecking;
function minorChecking(id,valueDate,item){
   var input = document.getElementById(id);
   if (!input) return;
   let formattedValue = '';
   // 👇 Format if valueDate is a Date object
   if (Object.prototype.toString.call(valueDate) === '[object Date]' && !isNaN(valueDate)) {
     const day = String(valueDate.getDate()).padStart(2, '0');
     const month = String(valueDate.getMonth() + 1).padStart(2, '0');
     const year = valueDate.getFullYear();
     formattedValue = `${day}/${month}/${year}`;
   } else if (typeof valueDate === 'string') {
     formattedValue = valueDate.replace(/[^0-9]/g, '');
     if (formattedValue.length > 2 && formattedValue.length <= 4) {
       formattedValue = formattedValue.slice(0, 2) + '/' + formattedValue.slice(2);
     } else if (formattedValue.length > 4) {
       formattedValue = formattedValue.slice(0, 2) + '/' + formattedValue.slice(2, 4) + '/' + formattedValue.slice(4, 8);
     }
   }
   input.value = formattedValue;
   // Proceed with validation
   vm.validateMinorDOB(id, formattedValue,item);
}
vm.validateMinorDOB = function(id,dobStr,item) {
  if (!dobStr || dobStr.length !== 10) return;
  var parts = dobStr.split('/');
  if (parts.length !== 3) return;
  var day = parseInt(parts[0], 10);
  var month = parseInt(parts[1], 10) - 1; // JavaScript month is 0-based
  var year = parseInt(parts[2], 10);
  var dob = new Date(year, month, day);
  var today = new Date();
  var age = today.getFullYear() - dob.getFullYear();
  var m = today.getMonth() - dob.getMonth();
  var d = today.getDate() - dob.getDate();
  if (m < 0 || (m === 0 && d < 0)) {
    age--;
  }
   if (age < 18) {
   if (item?.C014) {
   item.C014.oldValue='';
   item.C014.oldValue='Y';
      }else{
     item.isNomineeMinor='';
     item.isNomineeMinor = "Y";
     }
     }else if(age >= 18) {
   /* Swal.fire("Warning","Only minors (below 18 years) are allowed.","warning");
    var inputElement = document.getElementById(id);
    if (inputElement) {
    inputElement.value = ''; // Clear the textbox
    item.isNomineeMinor = "N";
     }*/
        if (item?.C014) {
        item.C014.oldValue='';
        item.C014.oldValue='N';
       }else{
    item.isNomineeMinor='';
    item.isNomineeMinor = "N";
    }
 }
    let index = id.replace("dateofBirth", ""); // → "5"
    index = parseInt(index); // convert to number
    vm.onNomineeMinorChange(item.isNomineeMinor,index);
};
vm.onNomineeMinorChange = function(value,index) {
        // remove or reset nominee minor data for that index
        if(null!=value&&value==='N'){
        if (vm.nominationDetails && vm.nominationDetails[index]) {
            vm.nominationDetails[index].guardianFirstName = '';
            vm.nominationDetails[index].guardianMiddleName = '';
            vm.nominationDetails[index].guardianLastName = '';
            vm.nominationDetails[index].guardianAddressOne = '';
            vm.nominationDetails[index].guardianAddressTwo = '';
            vm.nominationDetails[index].guardianCity = '';
            vm.nominationDetails[index].guardianState = '';
            vm.nominationDetails[index].guardianPinCode = '';
            vm.nominationDetails[index].guardianMobileNo = '';
            vm.nominationDetails[index].guardianEmail = '';
            vm.nominationDetails[index].relationWithMinorNominee = '';
        }}
};
    function redirectToInputPage(state) {
      $state.go(state);
    }









		vm.getAccountType = getAccountType;
		vm.getState = getState;
        vm.isMinorSingle = isMinorSingle;
//        vm.getInquiryTorchDetails = getInquiryTorchDetails;
//        vm.verifyDetails = verifyDetails;
//        vm.modifyDetails = modifyDetails;
        vm.addValResponse = addValResponse;
        vm.getVerifySelectDetails = getVerifySelectDetails;
        vm.schemeList = $stateParams.schemeList;
        vm.prevTab = prevTab;
        vm.prevStep = prevStep;
        vm.nextTab = nextTab;
		vm.addcustomerMaster = addcustomerMaster;
        vm.getPantorch = getPantorch;
        vm.validateInput = validateInput;
        vm.schemeListNew = [];
        vm.schemeListNeww = [];
        vm.maturityDateFun  = maturityDateFun;
        vm.inquiryValResponse = inquiryValResponse;

//        $stateParams.schemeList;

        vm.getModeOfInterestPayment = getModeOfInterestPayment ;
        vm.getsubscriptionIdInq=getsubscriptionIdInq;
        vm.getblaNoInq = getblaNoInq;
        vm.otherBankDetailsObjList = [];
        vm.cursorValListObjList = [];
        vm.nominationDetailsObjList = [];
        vm.getTranCode = getTranCode;
        vm.getMaturityDate = getMaturityDate;
		vm.orderArray = orderArray;
		vm.getAddressOne = getAddressOne;
		vm.getOtherBankDetailsTorch = getOtherBankDetailsTorch;
		vm.getOtherBankDetailsTorchM = getOtherBankDetailsTorchM;
		vm.getTypeofHolding = getTypeofHolding;
		vm.dateOfBirthOptionsFunc = dateOfBirthOptionsFunc;
		vm.dateOfBirthOptionsFuncMajor = dateOfBirthOptionsFuncMajor;
        vm.currentDate = new Date();
        vm.showWarning = showWarning;

vm.otherBankDetailsList = [];
vm.cursorValListAdd = [];
vm.nominationDetailsAdd = [];


        vm.today=new Date();
        vm.loginSolDate = $rootScope.globals.currentUser.solDate;
		vm.loginSolId = $rootScope.globals.currentUser.solId;
		vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
 const currentYear = new Date().getFullYear();
  $scope.availableYears = [currentYear, currentYear - 1, currentYear - 2];

  // Initialize selected values
  $scope.selectedMonth = null;
  $scope.selectedYear = null;
  vm.monthList = [
      {
        DESCRIPTION: "JANUARY",
        SUBCODE: "JAN",
      },
      {
        DESCRIPTION: "JULY",
        SUBCODE: "JUL",
      }
    ];
  // Submit function
  $scope.submitPeriod = function () {
    if ($scope.selectedMonth && $scope.selectedYear) {
      alert(
        'Interest Paid Period: Month - ' +
          $scope.selectedMonth +
          ', Year - ' +
          $scope.selectedYear
      );
    } else {
      alert('Please select both Month and Year.');
    }
  };
  vm.ftsFunction=ftsFunction;
  vm.ftsIndex=0;
function ftsFunction(flag) {
      if (flag == 'right') {
          if (vm.ftsFile.length - 2 >= vm.ftsIndex) {
              vm.ftsIndex++;
          } else {
              vm.ftsIndex = 0;
          }
      } else {
          if (vm.ftsIndex == 0) {
              vm.ftsIndex = vm.ftsFile.length - 1;
          } else {
              vm.ftsIndex--;
          }
      }

      // Reset image transformations on navigation
      vm.imageRotation = 0;
      vm.imageZoom = 1;
  }
  vm.imageRotation = 0;
  vm.imageZoom = 1;

  vm.rotateImage = function(deg) {
      vm.imageRotation = (vm.imageRotation + deg) % 360;
  };

  vm.zoomImage = function(factor) {
      vm.imageZoom *= factor;
      if (vm.imageZoom < 0.5) vm.imageZoom = 0.5;
      if (vm.imageZoom > 5) vm.imageZoom = 5;
  };
        vm.relationListMetaData = [
            {label : "Select",value : null},
            {label : "Father",value : "FATHER"},
            {label : "Mother",value : "MOTHER"},
            {label : "Son",value : "SON"},
            {label : "Daughter",value : "DAUGHTER"},
            {label : "Brother",value : "BROTHER"},
            {label : "Sister",value : "SISTER"},
            {label : "Spouse",value : "SPOUSE"},
            {label : "Others",value : "OTHERS"},
        ];

        vm.yesNoMetaData = [
            {label : "Select",value : null},
            {label : "Yes",value : "Y"},
            {label : "No",value : 'N'}
        ];

        vm.statusData = [
                    {label : "Select",value : null},
                    {label : "NRI",value : "NRI"},
                    {label : "Resident",value : 'RESIDENT'}
                ];

function getTypeofHolding() {
      var requestData = {
        referenceId: "HOLDER_TYPE",
      };
      bondTransferInService.getReferenceData(requestData).then(
        function (response) {
          vm.holderTypeList = response.data.result;
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }

   function checkAge(item){

           if(typeof(item.C017) != 'undefined'){
            var dob = new Date(item.C017.oldValue);
            var month_diff = Date.now() - dob.getTime();
            var age_dt = new Date(month_diff);
            var year = age_dt.getUTCFullYear();

            var age = Math.abs(year - 1970);

            if(age >= 18){
            Swal.fire("Not a Minor!", "Go back to the previous screen", "info");
            item.C017.oldValue = "";
            }
          }
          }

		function nextStep() {
			var $active = $('.from_wrapper .nav-tabs li.active');
			nextTab($active);
		}


		function nextTab(elem) {
			$(elem).next().find('a[data-bs-toggle="tab"]').click();
		}


		function prevStep() {
			var $active = $('.from_wrapper .nav-tabs li.active');
			prevTab($active);
		}

		function prevTab(elem) {
			$(elem).prev().find('a[data-bs-toggle="tab"]').click();
		}
vm.indianNumberFormat=indianNumberFormat;
                function indianNumberFormat(value) {
                  if (isNaN(value)|| null == value) return value;
                  let numStr = value.toString();
                  let [whole, decimal] = numStr.split('.');
                  if (whole.length <= 3) {
                      return decimal ? whole + '.' + decimal : whole + '.00';
                    }
                  let lastThree = whole.slice(-3);  // last three digits
                  let otherNumbers = whole.slice(0, whole.length - 3);
                  if (otherNumbers !== '') {
                    lastThree = ',' + lastThree;
                  }
                  let formattedWhole = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ',') + lastThree;
                if (decimal) {
                    return formattedWhole + '.' + decimal;
                  } else {
                    return formattedWhole+'.00';
                  }
                }
        function validateMultiples(amount, flag) {

        			vm.bondCashLimit = parseInt(vm.bondCashLimit);
        			vm.bondClearingTransferLimit = parseInt(vm.bondClearingTransferLimit);
        			vm.depositMultiples = parseInt(vm.depositMultiples);
        			vm.minCashLimit = parseInt(vm.minCashLimit);
        			amount = parseInt(amount);

        			if (amount) {
        				if ((amount % vm.depositMultiples) == 0) {
        					vm.depositMultiplesErrMsg = ""
        				} else {
        					vm.depositMultiplesErrMsg = "Subscription Amount must be multiples of " + vm.depositMultiples;
        					if (flag == 'add')
        						vm.subscriptionAmount = '';

        					else
        						vm.subscriptionDetailsValueModel.C012.oldValue = '';
        				}
        			}

        			if (amount && vm.transactionMode) {

        				if (vm.transactionMode == 'C') {

        					if (amount > vm.bondCashLimit || amount < vm.minCashLimit) {
        						vm.depositMultiplesErrMsg = "Subscription Amount must be between " + vm.minCashLimit + " and " + vm.bondCashLimit;
        						if (flag == 'add')
        							vm.subscriptionAmount = '';

        						else
        							vm.subscriptionDetailsValueModel.C012.oldValue = '';
        					} else {
        						vm.depositMultiplesErrMsg = '';
        					}
        				} else if (vm.transactionMode == 'T' || vm.transactionMode == 'CL') {
        					if (amount > vm.bondClearingTransferLimit) {
        						vm.depositMultiplesErrMsg = "Subscription Amount must be between " + vm.minCashLimit + " and " + vm.bondClearingTransferLimit;

        						if (flag == 'add')
        							vm.subscriptionAmount = '';

        						else
        							vm.subscriptionDetailsValueModel.C012.oldValue = '';
        					} else {
        						vm.depositMultiplesErrMsg = '';
        					}
        				}

        			}
        			if (!amount) {
        				vm.depositMultiplesErrMsg = "Subscription Amount must not be zero";

        			}
        			if(vm.maturityAmountScheme && amount){
        			    vm.maturityAmt = (amount/1000) * vm.maturityAmountScheme;
        			} else {
        			vm.maturityAmt = '';
        			}
        		}
 // Function to convert number to words
  /*  vm.convertAmountToWords = function(amount) {
        if (amount && !isNaN(amount)) {
            vm.wordsInRupees = convertNumberToWords(amount);
        } else {
            vm.wordsInRupees = '';
        }
    };*/

     vm.convertAmountToWords = function(amount) {
            if (amount == "0") {
              vm.wordsInRupees = "Zero";
            } else if (amount && !isNaN(amount)) {
              vm.wordsInRupees = amountToWords(amount);
              console.log(vm.wordsInRupees); // Debugging log
            } else if ((amount == "")) {
              vm.wordsInRupees = "";
            } else {
              vm.wordsInRupees = "Zero";
            }
          }
     //Migration fix start
       function amountToWords(price) {
         var sglDigit = ["Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"],
           dblDigit = ["Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"],
           tensPlace = ["", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"],
           handle_tens = function(dgt, prevDgt) {
             return 0 == dgt ? "" : " " + (1 == dgt ? dblDigit[prevDgt] : tensPlace[dgt])
           },
           handle_utlc = function(dgt, nxtDgt, denom) {
             return (0 != dgt && 1 != nxtDgt ? " " + sglDigit[dgt] : "") + (0 != nxtDgt || dgt > 0 ? " " + denom : "")
           };

         var str = "",
           digitIdx = 0,
           digit = 0,
           nxtDigit = 0,
           words = [];
         if (price += "", isNaN(parseInt(price))) str = "";
         else if (parseInt(price) > 0 && price.length <= 10) {
           for (digitIdx = price.length - 1; digitIdx >= 0; digitIdx--) switch (digit = price[digitIdx] - 0, nxtDigit = digitIdx > 0 ? price[digitIdx - 1] - 0 : 0, price.length - digitIdx - 1) {
             case 0:
               words.push(handle_utlc(digit, nxtDigit, ""));
               break;
             case 1:
               words.push(handle_tens(digit, price[digitIdx + 1]));
               break;
             case 2:
               words.push(0 != digit ? " " + sglDigit[digit] + " Hundred" + (0 != price[digitIdx + 1] && 0 != price[digitIdx + 2] ? " and" : "") : "");
               break;
             case 3:
               words.push(handle_utlc(digit, nxtDigit, "Thousand"));
               break;
             case 4:
               words.push(handle_tens(digit, price[digitIdx + 1]));
               break;
             case 5:
               words.push(handle_utlc(digit, nxtDigit, "Lakh"));
               break;
             case 6:
               words.push(handle_tens(digit, price[digitIdx + 1]));
               break;
             case 7:
               words.push(handle_utlc(digit, nxtDigit, "Crore"));
               break;
             case 8:
               words.push(handle_tens(digit, price[digitIdx + 1]));
               break;
             case 9:
               words.push(0 != digit ? " " + sglDigit[digit] + " Hundred" + (0 != price[digitIdx + 1] || 0 != price[digitIdx + 2] ? " and" : " Crore") : "")
           }
           str = words.reverse().join("") + " Rupees"; // Append " Rupees"
         } else str = "";
         return str
       }//End

function convertYNToBoolean(value) {
			return value === 'Y' || value === true;
		}
    // Function to convert number to words (handles large numbers like thousands, millions, billions)
    function convertNumberToWords(amount) {
        if (amount === 0) return 'Zero';

        const below20 = [
            'Zero', 'One', 'Two', 'Three', 'Four', 'Five', 'Six', 'Seven', 'Eight', 'Nine',
            'Ten', 'Eleven', 'Twelve', 'Thirteen', 'Fourteen', 'Fifteen', 'Sixteen', 'Seventeen',
            'Eighteen', 'Nineteen'
        ];

        const tens = [
            '', '', 'Twenty', 'Thirty', 'Forty', 'Fifty', 'Sixty', 'Seventy', 'Eighty', 'Ninety'
        ];

        const thousands = ['', 'Thousand', 'Million', 'Billion', 'Trillion'];

        let words = '';

        // Split the number into chunks of 3 digits
        let i = 0;
        while (amount > 0) {
            if (amount % 1000 !== 0) {
                words = convertChunk(amount % 1000) + (thousands[i] ? ' ' + thousands[i] : '') + ' ' + words;
            }
            amount = Math.floor(amount / 1000);
            i++;
        }

        return words.trim();
    }

    // Helper function to convert a chunk (0-999) into words
    function convertChunk(num) {
        const below20 = [
            'Zero', 'One', 'Two', 'Three', 'Four', 'Five', 'Six', 'Seven', 'Eight', 'Nine',
            'Ten', 'Eleven', 'Twelve', 'Thirteen', 'Fourteen', 'Fifteen', 'Sixteen', 'Seventeen',
            'Eighteen', 'Nineteen'
        ];

        const tens = [
            '', '', 'Twenty', 'Thirty', 'Forty', 'Fifty', 'Sixty', 'Seventy', 'Eighty', 'Ninety'
        ];

        if (num === 0) return '';
        if (num < 20) return below20[num];
        if (num < 100) return tens[Math.floor(num / 10)] + (num % 10 ? ' ' + below20[num % 10] : '');
        return below20[Math.floor(num / 100)] + ' Hundred' + (num % 100 ? ' and ' + convertChunk(num % 100) : '') + ' Rupees only';
    }


  function validatePan() {
      vm.loader = true;

                                var requestData = {};
                                 vm.list=[];
                                 vm.singleList=[];

                                // Ensure the panNo is not empty or undefined
                                if(vm.modeOfHolding == 'MH001' || vm.modeOfHolding == 'MH004' || vm.modeOfHolding == 'MH005' ){
                          vm.list.push({      pan: vm.pan_No1,
                                                      schemeCode: vm.schemeCode,
                                                      bondHolderName: vm.bondHolderName,
                                                      dob: $filter("date")(vm.fromDate1, "dd-MMM-yyyy")});
                                                    } else if(vm.modeOfHolding == 'MH002' || vm.modeOfHolding == 'MH003'){
                                                    if(vm.noOfJointAcc == 2){
                                                    vm.list.push({      pan: vm.pan_No1,
                                                      schemeCode: vm.schemeCode,
                                                      bondHolderName: vm.bondHolderName,
                                                      dob: $filter("date")(vm.fromDate1, "dd-MMM-yyyy")})
                                                      vm.list.push({      pan: vm.pan_No2,
                                                            schemeCode: vm.schemeCode,
                                                            bondHolderName: vm.secondJointHolderName,
                                                            dob: $filter("date")(vm.fromDate2, "dd-MMM-yyyy")})
                                                            } else if(vm.noOfJointAcc == 3){
                                                             vm.list.push({      pan: vm.pan_No1,
                                                              schemeCode: vm.schemeCode,
                                                              bondHolderName: vm.bondHolderName,
                                                              dob: $filter("date")(vm.fromDate1, "dd-MMM-yyyy")})
                                                              vm.list.push({      pan: vm.pan_No2,
                                                                    schemeCode: vm.schemeCode,
                                                                    bondHolderName: vm.secondJointHolderName,
                                                                    dob: $filter("date")(vm.fromDate2, "dd-MMM-yyyy")})
                                                             vm.list.push({      pan: vm.pan_No3,
                                                                     schemeCode: vm.schemeCode,
                                                                     bondHolderName: vm.thirdJointHolderName,
                                                                     dob: $filter("date")(vm.fromDate3, "dd-MMM-yyyy")})
                                                            }
                                                    }

      var requestData = {
        panValidation: vm.list,
        schemeCode : vm.schemeCode
      };

      bondTransferInService.validatePan(requestData).then(
        function (response) {
         vm.loader = false;
      vm.objList = response.data.result; // Success PANs
      vm.failedList = response.data.result1; // Failed PANs
      vm.allPANsValid = true; // ✅ Assume all PANs are valid initially

if (vm.objList.length === 0 && vm.failedList.length === 0) {
    Swal.fire("Error!", "Invalid response received. No PAN validation data found.", "error");

        //vm.allPANsValid = false; // ❌ Disable "Next" button on error

} else {
    let panMessages = [];

    if (null!=vm.objList&&vm.objList.length > 0) {
        vm.objList.forEach(obj => panMessages.push(`'${obj.panNumber}' - Success`));
    }

    if (null!=vm.failedList&&vm.failedList.length > 0) {
        vm.failedList.forEach(obj => panMessages.push(`'${obj.panNumber}' - Failure`));
       //vm.allPANsValid = false;
    }

    Swal.fire({
        title: "PAN Validation Result",
        text: panMessages.join("\n"),
        icon: vm.allPANsValid ? "success" : "warning"
    });
}

}
        ,
        function (error) {
        if(error.data.message.toLowerCase().startsWith('trigger')){
        vm.panValid = false;
        vm.loader = false;
        vm.allPANsValid = true;
        handleErrorResponse(error);
        }
        else{
        vm.loader = false;
        handleErrorResponse(error);
        vm.allPANsValid = true;
        $state.go('bla_acc_opening_cum_subscription.inquiry');
        }
        }
      );
    }

function addRange() {
                if (vm.scssOtherBanksDetails.length <= 14) {
                    vm.scssOtherBanksDetails.push({});
                    vm.disableDelete = true;
                }
            }

              function addRange() {
                  if (null!=vm.nominationDetails&&vm.nominationDetails.length >= vm.schemeNomineeCount) {
                    Swal.fire("Limit Reached", `You can only add up to ${vm.schemeNomineeCount} nominees.`, "warning");
                    return;
                  }
                    vm.nominationDetails.push({});
                  }
               	// Remove Nominee
                                         	function removeNominee(index)
                                         	{
                                         		if(vm.nominationDetails.length > 1){
                                         			vm.nominationDetails.splice(index,1);
                                         		} else {
                                         			Swal.fire("You will not allowed to delete first nominee");
                                         			return false;
                                         		}

                                         	}
            function removeRange() {
                if (vm.scssOtherBanksDetails.length > 1) {
                    vm.lastRow = vm.scssOtherBanksDetails.length - 1;
                    vm.scssOtherBanksDetails.splice(vm.lastRow, 1);
                    if (vm.lastRow == 1) {
                        vm.disableDelete = false;
                    }
                } else {
                    vm.disableDelete = false;
                }
            }


             function addRanges() {
                                       if (!vm.coparcenerslist || !Array.isArray(vm.coparcenerslist)) {
                                           vm.coparcenerslist = [];
                                       }

                                       vm.coparcenerslist.push({});
                                       vm.enableDelete = true;
                                   }

                                   function removeRanges(id, index) {
                                       if (!vm.coparcenerslist || !Array.isArray(vm.coparcenerslist) || index < 0 || index >= vm.coparcenerslist.length) {
                                           return;
                                       }

                                       vm.coparcenerslist.splice(index, 1);

                                       if (id) {
                                           vm.resultDelArray.push({ colName: 'Id', value: id });
                                       }

                                       if (vm.coparcenerslist.length === 0) {
                                           vm.enableDelete = false;
                                       }
                                   }


function instAmountValidation(instAmt,subsAmt)
{

if(instAmt != subsAmt){
Swal.fire("Error!", "Deposit Amount and Instrument Amount differs", "error");
vm.instAmount ='';
}
}
  vm.today=new Date();
        function fromToDateValidation() {
 let minDate1 =  new Date(vm.solDate) ;
  minDate1.setMonth(minDate1.getMonth() - 3);
             vm.dateOptions = {
                maxDate: vm.solDate,
            }
            vm.dateValidation = {
                            maxDate: vm.solDate,
                            minDate: minDate1,
                        }
                          vm.futureDateNot = {

                                        maxDate: vm.today
                                    };
        }
         function guardDobValidation() {
          var today = new Date(vm.solDate);
             var majorDate = new Date(today.getFullYear() - 18, today.getMonth(), today.getDate());

             vm.dobOptions = {
                 maxDate: majorDate
             };
                   }

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


function addBondTransferIn(){


//    Swal.fire({
//                   				title: "Are you sure?",
//                   				text: "Do you want to Really Add this record?",
//                   				type: "warning",
//                   				showCancelButton: true,
//                   				cancelButtonClass: "btn-cancel",
//                   				confirmButtonClass: "btn-primary",
//                   				confirmButtonText: "Yes",
//                   				closeOnConfirm: false
//                   			}, function () {




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





                             vm.nominationDetailsAdd = [];
                   			 angular.forEach(vm.nomineeList, function(item, key){
                                                        let items =[];
                                                        if(typeof(item.C017) != 'undefined'){

                                                         if(item.C017.oldValue != null){
                                                           items.C017 = moment(item.C017.oldValue).format("DD-MMM-YYYY")
                                                         }
                                                         else{
                                                           items.C017 = null;
                                                         }
                                                        }
                                                        else{
                                                           items.C017 = null;
                                                        }

                                                        items.C003 = typeof item.C003 === "undefined" ? null : item.C003.oldValue;
                                                        items.C004 = typeof item.C004 === "undefined" ? null : item.C004.oldValue;
                                                        items.C005 = typeof item.C005 === "undefined" ? null : item.C005.oldValue;
                                                        items.C006 = typeof item.C006 === "undefined" ? null : item.C006.oldValue;
                                                        items.C007 = typeof item.C007 === "undefined" ? null : item.C007.oldValue;
                                                        items.C008 = typeof item.C008 === "undefined" ? null : item.C008.oldValue;
                                                        items.C009 = typeof item.C009 === "undefined" ? null : item.C009.oldValue;
                                                        items.C010 = typeof item.C010 === "undefined" ? null : item.C010.oldValue;
                                                        items.C011 = typeof item.C011 === "undefined" ? null : item.C011.oldValue;
                                                        items.C012 = typeof item.C012 === "undefined" ? null : item.C012.oldValue;
														items.C013 = typeof item.C013 === "undefined" ? null : item.C013.oldValue;
                                                        items.C014 = typeof item.C014 === "undefined" ? null : item.C014.oldValue;
                                                        items.C015 = typeof item.C015 === "undefined" ? null : item.C015.oldValue;
														items.C016 = typeof item.C016 === "undefined" ? null : item.C016.oldValue;
                                                        items.C018 = typeof item.C018 === "undefined" ? null : item.C018.oldValue;
                                                        items.C019 = typeof item.C019 === "undefined" ? null : item.C019.oldValue;
                                                        items.C020 = typeof item.C020 === "undefined" ? null : item.C020.oldValue;
                                                        items.C021 = typeof item.C021 === "undefined" ? null : item.C021.oldValue;
                                                        items.C022 = typeof item.C022 === "undefined" ? null : item.C022.oldValue;
                                                        items.C023 = typeof item.C023 === "undefined" ? null : item.C023.oldValue;
                                                        items.C024 = typeof item.C024 === "undefined" ? null : item.C024.oldValue;
                                                        items.C025 = typeof item.C025 === "undefined" ? null : item.C025.oldValue;
														items.C026 = typeof item.C026 === "undefined" ? null : item.C026.oldValue;
														items.C027 = typeof item.C027 === "undefined" ? null : item.C027.oldValue;
														items.C029 = typeof item.C029 === "undefined" ? null : item.C029.oldValue;
														if(typeof(item.id) != 'undefined'){
														items.id = item.id.oldValue;
														}
                                                        else{
                                                        items.id = null;
                                                        }

                                                        vm.nominationDetailsAdd.push(items);
                                                   })

                                                    angular.forEach(vm.nominationDetailsAdd, function(item){

                                                     vm.nominationDetailsObjList.push(convertToColumnValuesArray(item));

                                                   });

vm.blaNumber = vm.blaNo;
if(vm.modeOfHoldingCode == "MH002" || vm.modeOfHoldingCode == "MH003"){

vm.nameOfTheBondHolder = '';
vm.blaNo = '';
}

vm.loader = true;
var requestData ={

'moduleCode' : vm.schemeCode,
'blaNumber' :vm.blaNo,
'modeOfHolding' :vm.modeOfHoldingCode,
'bondHolderName' : vm.nameOfTheBondHolder,
'fJointBlaNo' : typeof(vm.datalist.blaDetails[0].firstJointBondHolderBLA)!='undefined'? vm.datalist.blaDetails[0].firstJointBondHolderBLA:'',
'fJointHolderName' : typeof(vm.datalist.blaDetails[0].firstJointBondHolderName) !='undefined'?vm.datalist.blaDetails[0].firstJointBondHolderName :'',
'sJointBlaNo' : typeof(vm.datalist.blaDetails[1])!='undefined'? vm.datalist.blaDetails[1].secondJointBondHolderBLA:'',
'sJointHolderName' :typeof(vm.datalist.blaDetails[1])!='undefined'?vm.datalist.blaDetails[1].secondJointBondHolderName :'',
'isNri': vm.datalist.blaDetails[0].isNri == 'NO' ? 'N':'Y',
'transferorBankName':vm.transferorBankName,
'transferorBranchName' : vm.transferorBranchName,
'accTransfrInDate' : moment(vm.solDate).format("DD-MMM-YYYY"),
'oldBlaNum' : vm.oldBlaNo,
'rbiCode' : vm.rbiCode,
'intrstPaidUpto': vm.intrstPaidMonthUpto + vm.intrstPaidYearUpto,
'bondSubAmount' : vm.bondSubAmount,
'bondType' : vm.bondType,
'accOpenedDate' : moment(vm.accOpenedDate).format("DD-MMM-YYYY"),
'periodOfDeposit' : vm.bondDetails.periodOfDeposite,
'maturityDate' : moment(convertToDateObject(vm.maturityDate)).format("DD-MMM-YYYY"),
'rateOfInterest' : vm.bondDetails.interestRate,
'modeOfInterestPay' : vm.modeOfInterestPay,
'opprAccNum' : vm.opprAccNum,
'oprAccBranchId' : vm.oprAccBranchId,
'ddppAccNum' : vm.datalist.bondDetails.ddPoAccNum,
'debitAccNumFlag' : vm.datalist.bondDetails.debitAccNumFlag,
'ddPoAccNumFlag' : vm.datalist.bondDetails.ddPoAccNumFlag,
'creditAccNumFlag' : vm.datalist.bondDetails.creditAccNumFlag,
'nftCreditAccNumFlag' : vm.datalist.bondDetails.nftCreditAccNumFlag,
'oprBankCode' : vm.oprBankCode,
'oprBranchCode' : vm.oprBranchCode,
'ifscCodeCreditBank' : vm.ifscCodeCreditBank,
'payeeName' : vm.payeeName,
'nftCreditAccNum' : vm.datalist.bondDetails.nftCreditAccNum,
'tranMode' : vm.tranMode,
'creditAccNum' : vm.datalist.bondDetails.creditAccNum,
'debitAccNum' : vm.datalist.bondDetails.debitAccNum,
'debitAccType': '',
'creditAccType' : '',
'instAmt': vm.instAmount,
'instNum' : vm.instNum,
'instDate' : moment(vm.instDate).format("DD-MMM-YYYY"),
'tranCode' : vm.clgTranCode,
'zoneCode' : vm.zoneCode,
'zoneCodeDate' : moment(vm.zoneCodeDate).format("DD-MMM-YYYY"),
'bankCodeMicr' : vm.bankCodeMicr,
'branchCodeMicr' : vm.branchCodeMicr,
'sortCode' : vm.sortCode,
'remarks' : vm.remarks ,
'isNominee' : vm.isNominee,
'nominationDetailsAdd' : vm.nominationDetailsObjList,
'totalSharePercen':vm.totalPer,

 }
 bondTransferInService.bondTransferInAdd(requestData).then(function (response){


 vm.loader = false;
 vm.resultset = response.data.message;
 if(vm.resultset =='SUCCESS'){

Swal.fire("Added!","BOND Transfer in from Other Bank Details Added Successfully \n (GBM Tran Id : " + response.data.result.gbmTranId + ")\n (Subscription Id : " + response.data.result.subscriptionId + ")" + ")\n (BLA No : " + vm.blaNumber + ")", "success");
$state.go('rbi_bond_transfer_in.inquiry');
 }else{
 Swal.fire("Error!",resultset.msg, "error");
 }
}, function (error) {
          handleErrorResponse(error);
          vm.loader = false;
      });
}
})
}


function addValResponse(schemeCode,schemeName,modeOfHolding,noOfJointAcc,firstPanNum,secondPanNum,thirdPanNum)
{
 vm.loader = true;

 var requestData ={
 'schemeCode':schemeCode,
 'modeOfHolding':modeOfHolding,
 'noOfJointAcc':noOfJointAcc,
 'firstPanNum':firstPanNum,
 'secondPanNum':secondPanNum,
 'thirdPanNum':thirdPanNum
 }
console.log(requestData);
 bondTransferInService.addValResponse(requestData).then(function (response) {
   vm.loader = false;
   vm.schemeListNew = response.data.result;
   vm.schemeListNeww = response.data.result.bondDetails;
   vm.modeOfHolding=modeOfHolding;
   vm.maxLitCashDeptRsOut=response.data.result.bondDetails.maxLitCashDeptRsOut;
   vm.maxDeptAmtRsOut=response.data.result.bondDetails.maxDeptAmtRsOut;
   vm.deptMultRsOut=response.data.result.bondDetails.deptMultRsOut;
   vm.minDepositRsOut=response.data.result.bondDetails.minDepositRsOut;

   vm.dataList = {
    'schemeCode':schemeCode,
     'modeOfHolding':modeOfHolding,
     'noOfJointAcc':noOfJointAcc,
     'firstPanNum':firstPanNum,
     'secondPanNum':secondPanNum,
     'thirdPanNum':thirdPanNum,
   datalist     :vm.schemeListNew
   };

         $state.go('rbi_bond_transfer_in.add_detail',{obj:vm.dataList});
                                                                }, function (error) {
                                                                    handleErrorResponse(error);
                                                                    vm.loader = false;
                                                                });

}


if($state.current.name=='rbi_bond_transfer_in.add_detail'){
if($stateParams.obj){
if($stateParams.obj.ftsDetailFetch == false)
           {
                vm.ftsAppNum = $stateParams.obj.ftsAppNum;
                setTimeout(function() {
                vm.loader=true;
               getFtsDetails();
           }, 1000);
               }
vm.modeOfHolding = $stateParams.obj.modeOfHolding;
				vm.schemeCode = $stateParams.obj.schemeCode;
				vm.schemeName = $stateParams.obj.schemeName;
				//                vm.noOfJointAcc = parseInt($stateParams.obj.noOfJointAcc);
				vm.noOfJointAcc = parseInt($stateParams.obj.noOfJointAcc);
				vm.blaFlag = $stateParams.obj.blaFlag;
				vm.ftsAppNum = $stateParams.obj.ftsAppNum;
				vm.id = $stateParams.obj.id;
				vm.pan_No1 = $stateParams.obj.pan_No1;
				vm.pan_No2 = $stateParams.obj.pan_No2;
				vm.pan_No3 = $stateParams.obj.pan_No3;
				vm.moduleCode = $stateParams.obj.moduleCode;
				vm.hdfcOtherBank = $stateParams.obj.hdfcOtherBank;
				vm.schemeBondType = $stateParams.obj.schemeBondType;
				vm.bondHolderName = $stateParams.obj.bondHolderName;
				vm.secondJointHolderName = $stateParams.obj.secondJointHolderName;
				vm.thirdJointHolderName = $stateParams.obj.thirdJointHolderName;
				vm.fromDate1 = $stateParams.obj.fromDate1;
				vm.fromDate2 = $stateParams.obj.fromDate2;
				vm.fromDate3 = $stateParams.obj.fromDate3;
				vm.maturityDate = $stateParams.obj.maturityDate;

        getNomineeStatusMetaData();
				addDet();
getModeOfInterestPayment();
				getSegment();
				getAccountType();
				getState();
				isMinorSingle();
				updateMinorStatusForAll();
				validateNominee();
if (vm.bondHolderName) {
					splitName(vm.bondHolderName);
				}
				if (vm.secondJointHolderName) {
					splitName(vm.secondJointHolderName);
				}

				if (vm.thirdJointHolderName) {
					splitName(vm.thirdJointHolderName);
				}vm.getGuardianDetailsMetaData();
var parts = vm.solDate1.split('/');
 vm.year = parts[2];
}
}
if($state.current.name=='rbi_bond_transfer_in.verify'){
getVerifySelectDetails();
}

        function rejectReason() {
                      angular.element('#permissionMappingDelete').modal('toggle');
                      }


	/* Verify Submit */
		function verifyCustomerMaster() {
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

					let requestData = {
						'schemeCode': vm.schemeCode,
						'subscriptionId': vm.subscriptionId,
						'blaGroupId': vm.blaGroupId,
					}
					bondTransferInService.verifySubmit(requestData).then(
						(response) => {
							vm.loader = false;
							if (vm.getDetails.lastVerifiedBy != null) {
								Swal.fire("Success!", "BOND Transfer in from Other Bank Verified Successfully", "success");
							} else {
								var message = 'BOND Transfer in from Other Bank Verified Successfully';
								vm.modeOfHolding = vm.getDetails.masterDetailsValueModel[0].C003.oldValue;
								if (vm.modeOfHolding == 'MH001' || vm.modeOfHolding == 'MH004' || vm.modeOfHolding == 'MH005') {
									message = message + '\nBLA No: ' + vm.getDetails.subscriptionDetailsValueModel[0].C003.oldValue;
									if (vm.getDetails.subscriptionDetailsValueModel[0].C014) {
										message = message + '\nSubscription Id: ' + vm.getDetails.subscriptionDetailsValueModel[0].C014.oldValue;
									}
								} else {
									if (vm.getDetails.subscriptionDetailsValueModel[0].C005) {
										message = message + '\nFirst Joint BLA No: ' + vm.getDetails.subscriptionDetailsValueModel[0].C005.oldValue;
										//                                                if(vm.getDetails.masterDetailsValueModel[0].C014.oldValue == 'Y'){
										//                                                    message = message + '\nFirst Joint Guardian ID: ' + vm.getDetails.masterDetailsValueModel[0].C025.oldValue;
										//                                                }
									}

									if (vm.getDetails.subscriptionDetailsValueModel[0].C007) {
										message = message + '\nSecond Joint BLA No: ' + vm.getDetails.subscriptionDetailsValueModel[0].C007.oldValue;
										//                                                if(vm.getDetails.masterDetailsValueModel[1].C014.oldValue == 'Y'){
										//                                                    message = message + '\nSecond Joint Guardian ID: ' + vm.getDetails.masterDetailsValueModel[1].C025.oldValue;
										//                                                }
									}

									if (vm.getDetails.subscriptionDetailsValueModel[0].C009) {
										message = message + '\nThird Joint BLA No: ' + vm.getDetails.subscriptionDetailsValueModel[0].C009.oldValue;
										//                                                if(vm.getDetails.masterDetailsValueModel[2].C014.oldValue == 'Y'){
										//                                                    message = message + '\nThird Joint Guardian ID: ' + vm.getDetails.masterDetailsValueModel[2].C025.oldValue;
										//                                                }
									}

									if (vm.getDetails.subscriptionDetailsValueModel[0].C014) {
										message = message + '\nSubscription Id: ' + vm.getDetails.subscriptionDetailsValueModel[0].C014.oldValue;

									}

								}
								Swal.fire("Success!", message, "success");
							}

							location.href = '#!/rbi_bond_transfer_in/action/inquiry';
						},
						function(error) {
							vm.loader = false;
							handleErrorResponse(error);
						});

			}	});
		}

function updateMinorStatusForAll() {
			switch (vm.noOfJointAcc) {

				case 2:

					vm.updateMinorStatus2();
					break;
				case 3:

					vm.updateMinorStatus2();
					vm.updateMinorStatus3();
					break;
				default:
			}
		};

function updateMinorStatus2() {
			vm.customerMasterDetailsTwoFlag = vm.isMinor(vm.fromDate2) ? 'Y' : 'N';
			//                         if(vm.minorStatus2 === 'YES'){
			//                         vm.customerMasterDetailsTwoFlag='Y';
			//                         }else{
			//                         vm.customerMasterDetailsTwo.isMinor='N';
			//
			//                         }

		};

		function updateMinorStatus3() {
			vm.customerMasterDetailsThreeFlag = vm.isMinor(vm.fromDate3) ? 'Y' : 'N';
			//                          if(vm.minorStatus2 === 'YES'){
			//                                                  vm.customerMasterDetailsThree.isMinor='Y';
			//                                                  }else{
			//                                                  vm.customerMasterDetailsThree.isMinor='N';
			//
			//                                                  }

		};
function isMinor(date) {
			// Parse the input date (format: dd/MM/yyyy)
			var dobInput = $filter('date')(date, 'dd/MM/yyyy');
			const [day, month, year] = dobInput.split('/');
			const birthDate = new Date(`${year}-${month}-${day}`); // Create a Date object from the input

			// Calculate the difference in years between the current date and the birth date
			const today = new Date();
			const age = today.getFullYear() - birthDate.getFullYear();

			// Adjust the age if the current date is before the birth date this year
			const monthDiff = today.getMonth() - birthDate.getMonth();
			const dayDiff = today.getDate() - birthDate.getDate();
			const isMinor = age < 18 || (age === 18 && (monthDiff < 0 || (monthDiff === 0 && dayDiff < 0)));


			return isMinor;


		}
function getState() {

			vm.listType = 'STATES OF INDIA';
			var requestData = {
				referenceId: vm.listType
			}
			bondTransferInService.getMetaData(requestData).then(function(response) {
				vm.stateListValues = response.data.result;
				vm.stateList = [];
				angular.forEach(vm.stateListValues, function(item) {
					vm.stateList.push({
						subCodeDescription: item.DESCRIPTION,
						subCode: item.SUBCODE
					})
				})
			}, function(error) {
				handleErrorResponse(error);
			});
		}
		vm.getAddressOneNom = getAddressOneNom;
    function getAddressOneNom(pinCode,index, holder) {
        vm.loader = true;
        var requestData = { pinCode: pinCode };

        bondTransferInService.fetchState(requestData)
            .then(function (response) {
                vm.loader = false;
                if (response.data.result) {
                    var cbsDetail = response.data.result[0];

                    // Update the corresponding holder's details dynamically
                    if (holder === 'nominee') {
                        vm.nominationDetails[index].state = cbsDetail.bondHldState;
                        vm.nominationDetails[index].city = cbsDetail.bondHldCity;
                    } else if (holder === 'guard') {
                        vm.nominationDetails[index].guardianState = cbsDetail.bondHldState;
                        vm.nominationDetails[index].guardianCity = cbsDetail.bondHldCity;
                    }
                } else {
                    showErrorNominee(holder,index);
                }
            })
            .catch(function (error) {
                vm.loader = false;
                handleErrorResponse(error);
                showError(holder);
            });
    }
vm.showErrorNominee = showErrorNominee;
function getAddressOne(pinCode, holder) {
    vm.loader = true;
    var requestData = { pinCode: pinCode };

    bondTransferInService.fetchState(requestData)
        .then(function (response) {
            vm.loader = false;
            if (response.data.result) {
                var cbsDetail = response.data.result[0];

                // Update the corresponding holder's details dynamically
                if (holder === 'one') {
                    vm.customerMasterDetails.bondHldState = cbsDetail.bondHldState;
                    vm.customerMasterDetails.bondHldCity = cbsDetail.bondHldCity;
                } else if (holder === 'two') {
                    vm.customerMasterDetailsTwo.bondHldState = cbsDetail.bondHldState;
                    vm.customerMasterDetailsTwo.bondHldCity = cbsDetail.bondHldCity;
                } else if (holder === 'three') {
                    vm.customerMasterDetailsThree.bondHldState = cbsDetail.bondHldState;
                    vm.customerMasterDetailsThree.bondHldCity = cbsDetail.bondHldCity;
                }
            } else {
                showError(holder);
            }
        })
        .catch(function (error) {
            vm.loader = false;
            handleErrorResponse(error);
            showError(holder);
        });
}
function showErrorNominee(holder,index) {
    if (holder === 'nominee') {
        vm.nominationDetails[index].state = "";
        vm.nominationDetails[index].city = "";
    } else if (holder === 'guard') {
        vm.nominationDetails[index].guardianState = "";
        vm.nominationDetails[index].guardianCity = "";
    }
    vm.errmsgSingle = "No data found for the given pin code. Enter manually.";
}

function showError(holder) {
    if (holder === 'one') {
        vm.customerMasterDetails.bondHldState = "";
        vm.customerMasterDetails.bondHldCity = "";
    } else if (holder === 'two') {
        vm.customerMasterDetailsTwo.bondHldState = "";
        vm.customerMasterDetailsTwo.bondHldCity = "";
    } else if (holder === 'three') {
        vm.customerMasterDetailsThree.bondHldState = "";
        vm.customerMasterDetailsThree.bondHldCity = "";
    }
    vm.errmsgSingle = "No data found for the given pin code. Enter manually.";
}

		function isMinorSingle() {
			// Parse the input date (format: dd/MM/yyyy)
			var dobInput = $filter('date')(vm.fromDate1, 'dd/MM/yyyy');
			const [day, month, year] = dobInput.split('/');
			const birthDate = new Date(`${year}-${month}-${day}`); // Create a Date object from the input

			// Calculate the difference in years between the current date and the birth date
			const today = new Date();
			const age = today.getFullYear() - birthDate.getFullYear();

			// Adjust the age if the current date is before the birth date this year
			const monthDiff = today.getMonth() - birthDate.getMonth();
			const dayDiff = today.getDate() - birthDate.getDate();
			const isMinor = age < 18 || (age === 18 && (monthDiff < 0 || (monthDiff === 0 && dayDiff < 0)));

			vm.customerMasterDetails.isMinor = isMinor ? 'Y' : 'N';

		}

//Get Transaction Modes
		function getModeOfInterestPayment() {
			var requestData = {
				'referenceId': 'MODE_OF_PAYMENT'
			}
			bondTransferInService.getMetaData(requestData).then(function(response) {
				vm.modeOfInterestPaymentList = response.data.result;
			}, function(error) {
				handleErrorResponse(error);
			});
		}

		function getAccountType() {
			var requestData = {
				'referenceId': 'ACC_TYP_CD'
			}
			bondTransferInService.getMetaData(requestData).then(function(response) {
				vm.accountTypeList = response.data.result;
			}, function(error) {
				handleErrorResponse(error);
			});
		}

function getSegment() {
			var requestData = {
				'referenceId': 'SEGMENT'
			}
			bondTransferInService.getMetaData(requestData).then(function(response) {
				vm.segmentList = response.data.result;
			}, function(error) {
				handleErrorResponse(error);
			});
		}
		function getInterestPaymentMode() {
			var requestData = {
				'referenceId': 'INT_PAY_MODE'
			}
			bondTransferInService.getMetaData(requestData).then(function(response) {
				vm.interestPaymentModeList = response.data.result;
			}, function(error) {
				handleErrorResponse(error);
			});
		}

  function deleteDetails(){
//           Swal.fire({
//           				title: "Are you sure?",
//           				text: "Do you want to Really Delete this record?",
//           				type: "warning",
//           				showCancelButton: true,
//           				cancelButtonClass: "btn-cancel",
//           				confirmButtonClass: "btn-primary",
//           				confirmButtonText: "Yes",
//           				closeOnConfirm: false
//           			}, function () {





                  Swal.fire({
                    title: "Are you sure?",
                    text: "Do you want to Really Delete this record?",
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
           				     'schemeCode': vm.schemeCode,
                             						'subscriptionId': vm.subscriptionId,
                             						'blaGroupId': vm.blaGroupId,
                               'rejectionRemarks': vm.rejectionRemarks
                           };
           				bondTransferInService.deleteDetails(requestData).then(function (response) {

					         if (response.data.message == 'SUCCESS') {
                            Swal.fire("Rejected!", "BOND Transfer in from Other Bank Rejected Successfully", "success");
                         $state.go('rbi_bond_transfer_in.inquiry');

                } else {
                    Swal.fire("WARNING!", response.data.message, "error");
                     $state.go('rbi_bond_transfer_in.inquiry');
                }
                         const backdrop = document.querySelector('.modal-backdrop');
                                                                                        if (backdrop) {
                                                                                          backdrop.parentNode.removeChild(backdrop);
                                                                                          document.body.classList.remove('modal-open');
                                                                                        }

           				}, function (error) {
           					 vm.loader = false;
                                    handleErrorResponse(error);
                                     const backdrop = document.querySelector('.modal-backdrop');
                                      if (backdrop) {
                                        backdrop.parentNode.removeChild(backdrop);
                                        document.body.classList.remove('modal-open');
                                      }
                                       location.href = '#!/rbi_bond_transfer_in/action/inquiry';
               })
           		}	});

        }



function verifyValResponse(schemeCode,schemeName,modeOfHolding,holderType,nameOfTheBondHolder,blaNo,boSubsId,flag)
{
 vm.loader = true;

 var requestData ={
 'moduleId' : schemeCode,
 'blaNo'      : blaNo,
 'boSubsId'   : boSubsId
 }
console.log(schemeName);
 bondTransferInService.getverifyTorchDetails(requestData).then(function (response) {
   vm.loader = false;
   vm.modeOfHolding=modeOfHolding;

   vm.othrBankDetailList = response.data.result.othrBankDetailList;
   vm.cursorValList=response.data.result.cursorValList;
   vm.nomineeDetailList=response.data.result.nomineeDetailList;
   vm.verifyDetails=response.data.result.verifyDetails;

    if(vm.cursorValList[0].C022.oldValue=='MP001')
    vm.cursorValList[0].C022.oldValue = 'CREDIT ACCOUNT';


                                                                else if(vm.cursorValList[0].C022.oldValue=='MP002')
                                                                vm.cursorValList[0].C022.oldValue = 'DD/PO';

                                                                else if(vm.cursorValList[0].C022.oldValue=='MP003')
                                                                vm.cursorValList[0].C022.oldValue = 'NEFT';


                                                                       if(vm.cursorValList[0].C022.newValue){

                                                                              if(vm.cursorValList[0].C022.newValue=='MP001')
                                                                              vm.cursorValList[0].C022.newValue = 'CREDIT ACCOUNT';

                                                                              else if(vm.cursorValList[0].C022.newValue=='MP002')
                                                                              vm.cursorValList[0].C022.newValue = 'DD/PO';

                                                                              else if(vm.cursorValList[0].C022.newValue=='MP003')
                                                                              vm.cursorValList[0].C022.newValue = 'NEFT';

                                                                       }


   if (vm.verifyDetails.verifiedBy == null || vm.verifyDetails.verifiedBy =='' || vm.verifyDetails.verifiedBy ==' ' )
   {
   vm.nomineeDetailList=response.data.result.nomineeDetailList;
   }
   else{
   vm.nomineeDetailList= [];
   }


   vm.dataList = {
   modeOfHoldingCode :vm.modeOfHoldingCode,
   schemeName    :schemeName,
   modeOfHolding :modeOfHolding,
   blaNo         :blaNo,
   holderType    :holderType,
   nameOfTheBondHolder : nameOfTheBondHolder,
   bondSubsId :vm.bondSubsId,
   schemeCode: schemeCode,
othrBankDetailList : vm.othrBankDetailList,
cursorValList :vm.cursorValList ,
nomineeDetailList: vm.nomineeDetailList,
verifyDetails : vm.verifyDetails,
   };

if(flag=='VERIFY'){
         $state.go('rbi_bond_transfer_in.verify_detail',{obj:vm.dataList});
         }
else if(flag=='DELETE')
{
$state.go('rbi_bond_transfer_in.delete_detail',{obj:vm.dataList});
}




                                                                }, function (error) {
                                                                    handleErrorResponse(error);
                                                                    vm.loader = false;

                                                                });

}


function modifyValResponse(schemeCode,schemeName,modeOfHolding,holderType,nameOfTheBondHolder,blaNo,boSubsId,flag)
{
 vm.loader = true;

 var requestData ={
 'moduleId' : schemeCode,
 'blaNo'      : blaNo,
 'boSubsId'   : boSubsId
 }
console.log(schemeName);
 bondTransferInService.getModifyTorchDetails(requestData).then(function (response) {
   vm.loader = false;
   vm.modeOfHolding=modeOfHolding;

   vm.othrBankDetailList = response.data.result.othrBankDetailList;
   vm.cursorValList=response.data.result.cursorValList;
   vm.verifyDetails=response.data.result.verifyDetails;
   vm.maxLitCashDeptRsOut=response.data.result.verifyDetails.maxLitCashDeptRsOut;
   vm.maxDeptAmtRsOut=response.data.result.verifyDetails.maxDeptAmtRsOut;
   vm.deptMultRsOut=response.data.result.verifyDetails.deptMultRsOut;
   vm.minDepositRsOut=response.data.result.verifyDetails.minDepositRsOut;
   vm.ddppAccNum = response.data.result.verifyDetails.ddPoAccNum;


//    if(vm.cursorValList[0].C022.oldValue='MP001')
//    vm.cursorValList[0].C022.oldValue = 'CREDIT ACCOUNT';
//
//
//                                                                else if(vm.cursorValList[0].C022.oldValue='MP002')
//                                                                vm.cursorValList[0].C022.oldValue = 'DD/PO';
//
//                                                                else if(vm.cursorValList[0].C022.oldValue='MP003')
//                                                                vm.cursorValList[0].C022.oldValue = 'NEFT';


   if (vm.verifyDetails.verifiedBy == null || vm.verifyDetails.verifiedBy =='' || vm.verifyDetails.verifiedBy ==' ' )
   {
   vm.nomineeDetailList=response.data.result.nomineeDetailList;
   }
   else{
   vm.nomineeDetailList= [];
   }

   vm.dataList = {
   modeOfHoldingCode :vm.modeOfHoldingCode,
   schemeName    :schemeName,
   modeOfHolding :modeOfHolding,
   blaNo         :blaNo,
   holderType    :holderType,
   nameOfTheBondHolder : nameOfTheBondHolder,
   bondSubsId :vm.bondSubsId,
   schemeCode : schemeCode,
othrBankDetailList : vm.othrBankDetailList,
cursorValList :vm.cursorValList ,
nomineeDetailList: vm.nomineeDetailList,
verifyDetails : vm.verifyDetails,


   };


         $state.go('rbi_bond_transfer_in.modify_detail',{obj:vm.dataList});

                                                                }, function (error) {
                                                                    handleErrorResponse(error);
                                                                    vm.loader = false;

                                                                });

}


    if($state.current.name=='rbi_bond_transfer_in.modify_detail'){
        if($stateParams.obj){
        if($stateParams.obj.ftsDetailFetch == false)
                   {
                   vm.ftsAppNum = $stateParams.obj.ftsAppNum;
                         setTimeout(function() {
                          vm.loader=true;
                         getFtsDetails();
                     }, 1000);
                       }
           vm.schemeCode = $stateParams.obj.schemeCode;
           				vm.ftsAppNum = $stateParams.obj.ftsAppNum;
           				vm.blaNo = $stateParams.obj.blaNo;
           				vm.schemeName = $stateParams.obj.schemeName;
           				vm.subscriptionId = $stateParams.obj.subscriptionId;
           				vm.panNum = $stateParams.obj.panNum;
           				getModifySelectDetails('D');
           				getGuardianDetailsMetaData();
           				getModeOfInterestPayment();
           				getSegment();
           				getAccountType();
           				getNomineeStatusMetaData();
                		getState();
           				}
    }

function otherBankDetailsModify(){

bondTransferInService.otherBankDetails().then(function (response) {
vm.otherBankDetailslistMod = response.data.result;
});
}

if($state.current.name=='rbi_bond_transfer_in.verify_detail' || $state.current.name=='rbi_bond_transfer_in.delete_detail'){
if($stateParams.obj){
	vm.schemeCode = $stateParams.obj.schemeCode;
				vm.blaNo = $stateParams.obj.blaNo;
				vm.schemeName = $stateParams.obj.schemeName;
				vm.subscriptionId = $stateParams.obj.subscriptionId;
				getVerifySelectDetails('select');

				getGuardianDetailsMetaData();
				getModeofHolding();
				getState();
				getModeOfInterestPayment();
				getSegment();
				getAccountType();
				getNomineeStatusMetaData();

}
}
vm.getFtsDetails=getFtsDetails;
  function getFtsDetails() {
      vm.loader = true;
      bondTransferInService.getFtsFileService(vm.ftsAppNum).then(
        function (response) {
        vm.ftsDetailFetch=false;
          vm.loader = false;
          vm.ftsFile =response.data.body.imagesList;
          vm.ftsFileStatus =response.data.body.statusCodeValue;
          if(response.data.statusCodeValue != 200){
           vm.ftsDetailFetch=true;
             Swal.fire("Warning!","Image is not available", "warning");
          }
        },
        function (error) {
         vm.loader = false;
         Swal.fire("Warning!","Image is not available", "warning");
        }
      );
    }
function inquiryValResponse(schemeCode,schemeName,modeOfHolding,holderType,nameOfTheBondHolder,blaNo,boSubsId,flag)
{
 vm.loader = true;

 var requestData ={
 'moduleId' :  schemeCode,
 'blaNo'      : blaNo,
 'boSubsId'   : boSubsId
 }
console.log(schemeName);
 bondTransferInService.getInquiryTorchDetails(requestData).then(function (response) {
   vm.loader = false;
//   vm.schemeListNew = response.data.result;
   vm.modeOfHolding=modeOfHolding;

   vm.othrBankDetailList = response.data.result.othrBankDetailList;
   vm.cursorValList=response.data.result.cursorValList;
   vm.nomineeDetailList=response.data.result.nomineeDetailList;
   vm.verifyDetails=response.data.result.verifyDetails;

if(vm.cursorValList[0]){
       if(vm.cursorValList[0].C022.oldValue=='MP001')
       vm.cursorValList[0].C022.oldValue = 'CREDIT ACCOUNT';

       else if(vm.cursorValList[0].C022.oldValue=='MP002')
       vm.cursorValList[0].C022.oldValue = 'DD/PO';

       else if(vm.cursorValList[0].C022.oldValue=='MP003')
       vm.cursorValList[0].C022.oldValue = 'NEFT';


       if(vm.cursorValList[0].C022.newValue){

              if(vm.cursorValList[0].C022.newValue=='MP001')
              vm.cursorValList[0].C022.newValue = 'CREDIT ACCOUNT';

              else if(vm.cursorValList[0].C022.newValue=='MP002')
              vm.cursorValList[0].C022.newValue = 'DD/PO';

              else if(vm.cursorValList[0].C022.newValue=='MP003')
              vm.cursorValList[0].C022.newValue = 'NEFT';

       }

}


   if (vm.verifyDetails.verifiedBy == null || vm.verifyDetails.verifiedBy =='' || vm.verifyDetails.verifiedBy ==' ' )
   {
   vm.nomineeDetailList=response.data.result.nomineeDetailList;
   }
   else{
   vm.nomineeDetailList= [];
   }


   vm.dataList = {
   modeOfHoldingCode :vm.modeOfHoldingCode,
   schemeName    :schemeName,
   schemeCode : schemeCode,
   modeOfHolding :modeOfHolding,
   blaNo         :blaNo,
   holderType    :holderType,
   nameOfTheBondHolder : nameOfTheBondHolder,
   bondSubsId :vm.bondSubsId,
othrBankDetailList : vm.othrBankDetailList,
cursorValList :vm.cursorValList ,
nomineeDetailList: vm.nomineeDetailList,
verifyDetails : vm.verifyDetails,
   };


$state.go('rbi_bond_transfer_in.inquiry_detail',{obj:vm.dataList});
}, function (error) {
handleErrorResponse(error);
vm.loader = false;

});

}



    if($state.current.name=='rbi_bond_transfer_in.inquiry_detail' ){
            if($stateParams.obj){
            vm.blaNo = $stateParams.obj.blaNo;
            				vm.schemeCode = $stateParams.obj.schemeCode;
            				vm.schemeName = $stateParams.obj.schemeName;
            				vm.panNum = $stateParams.obj.panNum;
            				vm.subscriptionId = $stateParams.obj.subscriptionId;
            				vm.bondHolderName = $stateParams.obj.bondHolderName;
            getInquiryTorchDetails();
            				getGuardianDetailsMetaData();
            				getModeofHolding();
            				getState();
            				getModeOfInterestPayment();
            				getSegment();
            				getAccountType();
            				getNomineeStatusMetaData();

            var parts = vm.solDate1.split('/');
             vm.year = parts[2];
            }
    }




function operativeAccNumValidation(accountNum, flag){
if(accountNum){
commonService.getCustomAcctInqApi(accountNum, function (response) {
vm.message = "INVALID ACCOUNT NUMBER"
vm.accValidate = response.data;

if (vm.accValidate[0].status == 'F')
{
if(flag=='M' && vm.cursorValList[0]){
if(vm.cursorValList[0].C033){
vm.cursorValList[0].C033.oldValue = '';
}
}
vm.oprAccBranchId = '';
vm.creditBankAccountNoErrMsg = vm.message;

}
else
{
vm.oprAccBranchId =vm.accValidate[0].solID;
if(flag=='M' && vm.cursorValList[0]){
vm.cursorValList[0].C033 = {};
vm.cursorValList[0].C033.oldValue = vm.accValidate[0].solID;
}
vm.creditBankAccountNoErrMsg = '';
}
}, function (err) {
handleErrorResponse(err);
if(flag=='M' && vm.cursorValList[0]){
if(vm.cursorValList[0].C033){
vm.cursorValList[0].C033.oldValue = '';
}
}
vm.oprAccBranchId = '';

vm.loader = false;
});
}

else
{
vm.creditBankAccountNoErrMsg = '';
}
}

        function convertBooleanToYN(value) {
        			return value ? 'Y' : 'N';;
        		}

function otherBankDetails(){

bondTransferInService.otherBankDetails().then(function (response) {
vm.otherBankDetailslist = response.data.result;
});
}

function addYears(date, years) {
  date.setFullYear(date.getFullYear() + years);

  return date;
}

function maturityDateFun(months , date){

     const dateCopy = new Date(date);

       const convertedDate = dateCopy.setMonth(parseInt(dateCopy.getMonth()) + parseInt(months));

        vm.maturityDate = $filter('date')(dateCopy, "dd/MM/yyyy");
        console.log(vm.maturityDate);

}


                 /*function addRange() {
                      if (vm.nomineeList.length>= 0) {
                          vm.nomineeList.push({});
                          vm.enableDelete = true;
                      }
                  }





                  function removeRange(id, index) {
                          $scope.$evalAsync(function(){
                          vm.nomineeList.splice(index, 1);
                          vm.nomineeList = vm.nomineeList;
                          vm.getTotalSharePer();
                          if(id)
                            vm.resultDelArray.push({colName: 'Id', value: id});
                          });
                          if(vm.nomineeList.length > 0){
                           vm.enableDelete = true;
                           }
                   }
*/


        function getSharePer(item) {

          if(item.C012.oldValue == 0){
          vm.sharePerCutOffErrMsg = 'Entered Input cannot be zero';
          item.C012.oldValue = '';
          }
          else if(item.C012.oldValue > 100)
          {
          vm.sharePerCutOffErrMsg = 'Entered Input should not exceed 100';
          item.C012.oldValue = '';
          }
          else{
           vm.sharePerCutOffErrMsg = '';
          }

        }


        function getTotalSharePer() {

             vm.totalPer = 0;
             for(var i=0; i< vm.nomineeList.length; i++){

                if(typeof (vm.nomineeList[i].C012) != 'undefined'){
                vm.totalPer = parseInt(vm.totalPer) + parseInt(vm.nomineeList[i].C012.oldValue);
                }
                else{
                vm.totalPer = parseInt(vm.totalPer) + parseInt(0);
                }
                }

                  if(vm.totalPer){
                  if(vm.totalPer != 100)
                  {
                  vm.shareTotalPerCutOffErrMsg = 'Total Share Percentage should be 100';
                  vm.totalPer = '';
                  }
                  else{
                   vm.shareTotalPerCutOffErrMsg = '';

                  }
                 }
                 else{
                   vm.shareTotalPerCutOffErrMsg = '';
                 }

                }

	function modifycustomerMaster() {
//			Swal.fire({
//					title: "Are you sure?",
//					text: "Do you want to Really Modify this record?",
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
					vm.resultArray = [];
					vm.resultArray1 = [];
					vm.transDetail = [];
					vm.guardianDetails = [];
					vm.resultArray3 = [];

					try {
						if (vm.customerMasterDetails.C044.oldValue === 'N') {
							vm.customerMasterDetails.C045 ? vm.customerMasterDetails.C045.oldValue = '' : "";
						}

						vm.customerMasterDetails.C037.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetails.C037.oldValue));
						vm.customerMasterDetails.C038.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetails.C038.oldValue));
						vm.customerMasterDetails.C039.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetails.C039.oldValue));
						vm.customerMasterDetails.C040.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetails.C040.oldValue));
						vm.customerMasterDetails.C041.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetails.C041.oldValue));
						vm.customerMasterDetails.C042.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetails.C042.oldValue));
						vm.customerMasterDetails.C043.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetails.C043.oldValue));
						vm.customerMasterDetails.C044.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetails.C044.oldValue));

					} catch (e) {
						console.error(e);
					}

					try {
						if (vm.customerMasterDetailsTwo.C044.oldValue === 'N') {
							vm.customerMasterDetailsTwo.C045 ? vm.customerMasterDetailsTwo.C045.oldValue = '' : "";
						}
						vm.customerMasterDetailsTwo.C037.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsTwo.C037.oldValue));
						vm.customerMasterDetailsTwo.C038.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsTwo.C038.oldValue));
						vm.customerMasterDetailsTwo.C039.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsTwo.C039.oldValue));
						vm.customerMasterDetailsTwo.C040.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsTwo.C040.oldValue));
						vm.customerMasterDetailsTwo.C041.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsTwo.C041.oldValue));
						vm.customerMasterDetailsTwo.C042.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsTwo.C042.oldValue));
						vm.customerMasterDetailsTwo.C043.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsTwo.C043.oldValue));
						vm.customerMasterDetailsTwo.C044.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsTwo.C044.oldValue));

					} catch (e) {
						console.error(e);
					}

					try {
						if (vm.customerMasterDetailsThree.C044.oldValue === 'N') {
							vm.customerMasterDetailsThree.C045 ? vm.customerMasterDetailsThree.C045.oldValue = '' : "";
						}
						vm.customerMasterDetailsThree.C037.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsThree.C037.oldValue));
						vm.customerMasterDetailsThree.C038.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsThree.C038.oldValue));
						vm.customerMasterDetailsThree.C039.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsThree.C039.oldValue));
						vm.customerMasterDetailsThree.C040.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsThree.C040.oldValue));
						vm.customerMasterDetailsThree.C041.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsThree.C041.oldValue));
						vm.customerMasterDetailsThree.C042.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsThree.C042.oldValue));
						vm.customerMasterDetailsThree.C043.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsThree.C043.oldValue));
						vm.customerMasterDetailsThree.C044.oldValue = convertBooleanToYN(convertYNToBoolean(vm.customerMasterDetailsThree.C044.oldValue));
					} catch (e) {
						console.error(e);
					}

					if (vm.customerMasterDetails) {
						var oneObj = Object.fromEntries(Object.entries(vm.customerMasterDetails));
						oneObj.C009.oldValue = $filter('date')(oneObj.C009.oldValue, "dd-MMM-yyyy");

						oneObj.ID.oldValue = oneObj.ID.oldValue; // or rename it if needed

						vm.resultArray.push(convertToColumnValuesArrayList(oneObj));
					}
					//second joint holder
					if (vm.customerMasterDetailsTwo) {
						var twoObj = Object.fromEntries(Object.entries(vm.customerMasterDetailsTwo));
						twoObj.C009.oldValue = $filter('date')(twoObj.C009.oldValue, "dd-MMM-yyyy");
						twoObj.ID.oldValue = twoObj.ID.oldValue;


						vm.resultArray.push(convertToColumnValuesArrayList(twoObj));
					}

					//third joint holder
					if (vm.customerMasterDetailsThree) {
						var threeObj = Object.fromEntries(Object.entries(vm.customerMasterDetailsThree));
						threeObj.C009.oldValue = $filter('date')(threeObj.C009.oldValue, "dd-MMM-yyyy");
						threeObj.ID.oldValue = threeObj.ID.oldValue;

						vm.resultArray.push(convertToColumnValuesArrayList(threeObj));
					}

					//            if (vm.getDetails.guardianDetailsValueModel[2]) {
					//                var threeObj = Object.fromEntries(Object.entries(vm.getDetails.guardianDetailsValueModel[2]));
					//                threeObj.C010.oldValue = $filter('date')(threeObj.C010.oldValue, "dd-MMM-yyyy");
					//                if (threeObj.C014) {
					//                    threeObj.C014.oldValue = $filter('date')(threeObj.C014.oldValue, "dd-MMM-yyyy");
					//                }
					//                if (threeObj.C015) {
					//                    threeObj.C015.oldValue = $filter('date')(threeObj.C015.oldValue, "dd-MMM-yyyy");
					//                }
					//
					//                vm.resultArray1.push(convertToColumnValuesArrayList(threeObj));
					//            }


					if (vm.getDetails.guardianDetailsValueModel[0]) {
						var oneObj = Object.fromEntries(Object.entries(vm.getDetails.guardianDetailsValueModel[0]));
						if (oneObj.C010) {
							oneObj.C010.oldValue = $filter('date')(oneObj.C010.oldValue, "dd-MMM-yyyy");
						}
						if (oneObj.C014) {
							oneObj.C014.oldValue = $filter('date')(oneObj.C014.oldValue, "dd-MMM-yyyy");
						}
						if (oneObj.C015) {
							oneObj.C015.oldValue = $filter('date')(oneObj.C015.oldValue, "dd-MMM-yyyy");
						}

						if (oneObj.C003) {
							oneObj.C003.oldValue = oneObj.C003.oldValue;
						}
						vm.resultArray1.push(convertToColumnValuesArrayList(oneObj));

						vm.guardianDetailsValueModelOne = vm.guardianDetailsValueModelOne;
						if (oneObj.C014 && oneObj.C014.oldValue) {
							vm.showGuardianDetails = true;
						}
						if (vm.guardianDetailsValueModelOne && !vm.guardianDetailsValueModelOne.ID) {
							if (oneObj.C003) {
								// Ensure guardianDetailsValueModelOne has the C003 value
								vm.guardianDetailsValueModelOne.C003 = {
									oldValue: oneObj.C003.oldValue
								};
							}
							vm.guardianDetails.push(convertToColumnValuesArrayList(vm.guardianDetailsValueModelOne));
						}

					}
					if (vm.getDetails.guardianDetailsValueModel[1]) {
						var twoObj = Object.fromEntries(Object.entries(vm.getDetails.guardianDetailsValueModel[1]));
						if (twoObj.C010) {
							twoObj.C010.oldValue = $filter('date')(twoObj.C010.oldValue, "dd-MMM-yyyy");
						}
						if (twoObj.C014) {
							twoObj.C014.oldValue = $filter('date')(twoObj.C014.oldValue, "dd-MMM-yyyy");
						}
						if (twoObj.C015) {
							twoObj.C015.oldValue = $filter('date')(twoObj.C015.oldValue, "dd-MMM-yyyy");
						}
						vm.guardianDetailsValueModelTwo = vm.guardianDetailsValueModelTwo;
						vm.resultArray1.push(convertToColumnValuesArrayList(twoObj));

						if (twoObj.C014 && twoObj.C014.oldValue) {
							vm.showGuardianDetailsTwo = true;
						}
						if (vm.guardianDetailsValueModelTwo && !vm.guardianDetailsValueModelTwo.ID) {
							if (twoObj.C003) {
								vm.guardianDetailsValueModelTwo.C003 = {
									oldValue: twoObj.C003.oldValue
								};
							}
							vm.guardianDetails.push(convertToColumnValuesArrayList(vm.guardianDetailsValueModelTwo));
						}
					}



					if (vm.getDetails.guardianDetailsValueModel[2]) {
						var threeObj = Object.fromEntries(Object.entries(vm.getDetails.guardianDetailsValueModel[2]));
						if (threeObj.C010) {
							threeObj.C010.oldValue = $filter('date')(threeObj.C010.oldValue, "dd-MMM-yyyy");
						}
						if (threeObj.C014) {
							threeObj.C014.oldValue = $filter('date')(threeObj.C014.oldValue, "dd-MMM-yyyy");
						}
						if (threeObj.C015) {
							threeObj.C015.oldValue = $filter('date')(threeObj.C015.oldValue, "dd-MMM-yyyy");
						}
						vm.guardianDetailsValueModelThree = vm.guardianDetailsValueModelThree;
						vm.resultArray1.push(convertToColumnValuesArrayList(threeObj));

						if (threeObj.C014 && threeObj.C014.oldValue) {
							vm.showGuardianDetailsTwo = true;
						}
						if (vm.guardianDetailsValueModelThree && !vm.guardianDetailsValueModelThree.ID) {
							if (threeObj.C003) {
								// Ensure guardianDetailsValueModelOne has the C003 value
								vm.guardianDetailsValueModelThree.C003 = {
									oldValue: threeObj.C003.oldValue
								};
							}
							vm.guardianDetails.push(convertToColumnValuesArrayList(vm.guardianDetailsValueModelThree));
						}
					}


					if (vm.getDetails.subscriptionDetailsValueModel[0]) {
						var oneObj = Object.fromEntries(Object.entries(vm.getDetails.subscriptionDetailsValueModel[0]));

						if (oneObj.C016) {
							oneObj.C016.oldValue = $filter('date')(oneObj.C016.oldValue, "dd-MMM-yyyy");
						}
						if (oneObj.C015) {
							oneObj.C015.oldValue = $filter('date')(oneObj.C015.oldValue, "dd-MMM-yyyy");
						}
						if (oneObj.C018) {
							//                    oneObj.C018.oldValue = $filter('date')(oneObj.C018.oldValue, "dd-MMM-yyyy");
							if (oneObj.C018.oldValue) {
								const dateObject = convertToDateObject(oneObj.C018.oldValue);
								if (dateObject) {
									oneObj.C018.oldValue = $rootScope.formatDateToDDMMMYYYY(dateObject);
								} else {
									console.warn('Invalid date value:', oneObj.C018.oldValue);
								}
							} else {
								console.warn('oldValue is null or undefined');
							}
						}
						vm.resultArray3.push(convertToColumnValuesArrayList(oneObj));
					}

                    vm.transDetail.push(convertToColumnValuesArrayList(vm.transferDetailValueModel));

					let requestData = {
						'schemeCode': vm.schemeCode,
						'blaNo': vm.blaNo,
						'subscriptionId': vm.subscriptionId,
						'columnValueDetailsList': vm.resultArray,
						'guardianModValueDetailsList': vm.resultArray1,
						'guardianAddDetailsList': vm.guardianDetails,
						'transferDetailValueList': vm.transDetail,
						'subscriptionDetailsList': vm.resultArray3,
					}
					bondTransferInService.modifySubmit(requestData).then(
						(response) => {
							vm.loader = false;
							Swal.fire("Success!", "Bond Transfer in Modified Successfully", "success");
							location.href = '#!/rbi_bond_transfer_in/action/inquiry';
						},
						function(error) {
							vm.loader = false;
								 const errorMessage = error && error.data && error.data.message
                                      ? error.data.message
                                      : "An unexpected error occurred.";
                              if(null!=errorMessage && errorMessage.startsWith("Invalid")){
                              Swal.fire("Warning!", errorMessage , "warning");
                              $state.go("rbi_bond_transfer_in.modify");
                              }else{
                            handleErrorResponse(error);
                            $state.go('rbi_bond_transfer_in.modify');
                              }

						});
			}	});
		}
vm.supportingDocumentsMetaData = [{
				label: "No",
				value: 'N'
			},
			{
				label: "Yes",
				value: 'Y'
			},
		];
		vm.yesNo = [{
				label: "No",
				value: 'N'
			},
			{
				label: "Yes",
				value: 'Y'
			},
		];
		vm.bondTypeMetaData = [

			{
				label: "Cummulative",
				value: 'C'
			},
			{
				label: "Non-Cummulative",
				value: 'NC'
			},
			{
				label: "Both",
				value: 'B'
			},
		];

			vm.stateMetaData = [
                  { label: "JAMMU AND KASHMIR", value: "S001" },
                  { label: "HIMACHAL PRADESH", value: "S002" },
                  { label: "PUNJAB", value: "S003" },
                  { label: "CHANDIGARH", value: "S004" },
                  { label: "UTTARANCHAL", value: "S005" },
                  { label: "HARYANA", value: "S006" },
                  { label: "NEW DELHI", value: "S007" },
                  { label: "RAJASTHAN", value: "S008" },
                  { label: "UTTAR PRADESH", value: "S009" },
                  { label: "BIHAR", value: "S010" },
                  { label: "SIKKIM", value: "S011" },
                  { label: "ARUNACHAL PRADESH", value: "S012" },
                  { label: "NAGALAND", value: "S013" },
                  { label: "MANIPUR", value: "S014" },
                  { label: "MIZORAM", value: "S015" },
                  { label: "TRIPURA", value: "S016" },
                  { label: "MEGHALAYA", value: "S017" },
                  { label: "ASSAM", value: "S018" },
                  { label: "WEST BENGAL", value: "S019" },
                  { label: "JHARKHAND", value: "S020" },
                  { label: "ORISSA", value: "S021" },
                  { label: "CHHATTISGARH", value: "S022" },
                  { label: "MADHYA PRADESH", value: "S023" },
                  { label: "GUJARAT", value: "S024" },
                  { label: "DIU AND DAMAN", value: "S025" },
                  { label: "DADRA AND NAGAR HAVELI", value: "S026" },
                  { label: "MAHARASHTRA", value: "S027" },
                  { label: "KARNATAKA", value: "S029" },
                  { label: "GOA", value: "S030" },
                  { label: "LAKSHADWEEP", value: "S031" },
                  { label: "KERALA", value: "S032" },
                  { label: "TAMIL NADU", value: "S033" },
                  { label: "PUDUCHERRY", value: "S034" },
                  { label: "ANDAMAN AND NICOBAR ISLANDS", value: "S035" },
                  { label: "TELANGANA", value: "S036" },
                  { label: "ANDHRA PRADESH", value: "S037" },
                  { label: "UTTARAKHAND", value: "S038" },
                  { label: "DELHI", value: "S039" },
                  { label: "ODISHA", value: "S040" },
                  { label: "OTHER COUNTRY", value: "S096" },
                  { label: "OTHER TERRITORY", value: "S097" }
                ];



function bankBranchCodeMicr(){
angular.element('#otherBankDetailslist').modal('toggle');

}


//        function getModuleName() {
//                       vm.loader = true;
//                       vm.referenceId = 'TAX';
//                       var requestData = {
//                           'referenceId': vm.referenceId
//                       };
//                       bondTransferInService.getRbiBondModuleNameDetails(requestData).then(function (response) {
//                           vm.loader = false;
//                           vm.schemeList = response.data.result;
//                           vm.schemeCode=response.data.result.subCodeDescription;
//                            vm.schemeName=response.data.result.subCode;
//                       }, function (error) {
//                           handleErrorResponse(error);
//                           vm.loader = false;
//                       });
//                   }


            	function getCbsCustFstJoinDetailsTwo(accountNumber) {
                    vm.loader = true;



            			bondTransferInService.fetchCbsCustId(accountNumber).then((response) => {
            				vm.loader = false;

                                 if (response.data.statusCode === "200") {
                                     if (response.data.result.responseString && response.data.result) {
                                         if (response.data.result.status.replyText === "0") {
                                             console.log("ReplyText is 0");

            							vm.cbsDetail = response.data.result.responseString.customerDetailsDTO[0];
                                         if (vm.customerMasterDetailsTwo.bondHldDob === null) {
                                                       vm.customerMasterDetailsTwo.bondHldDob = convertToDateObject(vm.formatCBSDate(vm.cbsDetail.datBirthCust));
                                                   }
                        				vm.customerMasterDetailsTwo.bondHldFirstName = vm.cbsDetail.customerFullName;
                        				vm.customerMasterDetailsTwo.bondHldMidName = vm.cbsDetail.customerMiddleName;
                        				vm.customerMasterDetailsTwo.bondHldLastName = vm.cbsDetail.customerLastName;
                        				vm.customerMasterDetailsTwo.fatherName = vm.cbsDetail.fatherName;
                        				vm.customerMasterDetailsTwo.motherName = vm.cbsDetail.motherName;
                        				vm.customerMasterDetailsTwo.gender = vm.cbsDetail.txtCustSex;
                        				vm.customerMasterDetailsTwo.bondHldDob = convertToDateObject(vm.cbsDetail.dateOfBirth);
                        				vm.customerMasterDetailsTwo.isMinor = vm.cbsDetail.isMinor;
                        				vm.customerMasterDetailsTwo.bondHldAddOne = vm.cbsDetail.txtCustadrAdd1;
                        				vm.customerMasterDetailsTwo.bondHldAddTwo = vm.cbsDetail.txtCustadrAdd2;
                        				vm.customerMasterDetailsTwo.bondHldAddThree = vm.cbsDetail.txtCustadrAdd3;
                        				vm.customerMasterDetailsTwo.bondHldCity = vm.cbsDetail.namHoldadrCity;
                        				vm.customerMasterDetailsTwo.bondHldPinCode = vm.cbsDetail.pinCode;
                        				vm.customerMasterDetailsTwo.bondHolderPhoneNumber = vm.cbsDetail.rmPhoneNo;
                        				vm.customerMasterDetailsTwo.bondHldMobNum = vm.cbsDetail.refPhoneMobile;
                        				vm.customerMasterDetailsTwo.bondHldEmail = vm.cbsDetail.refCustEmail;
                        				vm.customerMasterDetailsTwo.operativeAccNum = vm.cbsDetail.operativeAccNo;
                        				vm.customerMasterDetailsTwo.operativeAccBranchId = vm.cbsDetail.operativeBranchId;
                        				vm.customerMasterDetailsTwo.aadhaarNo = vm.cbsDetail.codAadhaarNo;
                                        vm.showAccountValidationSuccess();
                                                  } else {
                                                 vm.showCBSServiceWarning(response.data.result.status.replyText);
                                                   console.log("ReplyText is not 0, it is: " + response.data.statusCode.result.status.replyText);
                                               }
                                             } else {
                                                      vm.showCBSServiceWarning(response.data.result.status.replyText);
                                                    console.log("Missing result or status in response.");
                                                }
                                            } else {
                                                vm.showCBSServiceWarning(response.data.result.status.replyText);
                                                console.log("Status Code is not 200, it is: " + response.data.statusCode);
                                            }

            			}, function(error) {
            				   vm.loader = false;
                             vm.errmsgSingle = "CBS Failure";
                               vm.showCBSServiceWarning(error.data.message);

            			});


            		}
            function getCbsCustFstJoinDetailsThree(accountNumber) {
                        vm.loader = true;


                        bondTransferInService.fetchCbsCustId(accountNumber).then((response) => {
                            vm.loader = false;

                                 if (response.data.statusCode === "200") {
                                     if (response.data.result.responseString && response.data.result) {
                                         if (response.data.result.status.replyText === "0") {
                                             console.log("ReplyText is 0");

            				vm.cbsDetail = response.data.result.responseString.customerDetailsDTO[0];
                                                      if (vm.customerMasterDetailsThree.bondHldDob === null) {
                                                       vm.customerMasterDetailsThree.bondHldDob = convertToDateObject(vm.formatCBSDate(vm.cbsDetail.datBirthCust));
                                                   }
                                    				vm.customerMasterDetailsThree.bondHldFirstName = vm.cbsDetail.customerFullName;
                                    				vm.customerMasterDetailsThree.bondHldMidName = vm.cbsDetail.customerMiddleName;
                                    				vm.customerMasterDetailsThree.bondHldLastName = vm.cbsDetail.customerLastName;
                                    				vm.customerMasterDetailsThree.fatherName = vm.cbsDetail.fatherName;
                                    				vm.customerMasterDetailsThree.motherName = vm.cbsDetail.motherName;
                                    				vm.customerMasterDetailsThree.gender = vm.cbsDetail.txtCustSex;
                                    				vm.customerMasterDetailsThree.isMinor = vm.cbsDetail.isMinor;
                                    				vm.customerMasterDetailsThree.bondHldAddOne = vm.cbsDetail.txtCustadrAdd1;
                                    				vm.customerMasterDetailsThree.bondHldAddTwo = vm.cbsDetail.txtCustadrAdd2;
                                    				vm.customerMasterDetailsThree.bondHldAddThree = vm.cbsDetail.txtCustadrAdd3;
                                    				vm.customerMasterDetailsThree.bondHldCity = vm.cbsDetail.namHoldadrCity;
                                    				vm.customerMasterDetailsThree.bondHldPinCode = vm.cbsDetail.pinCode;
                                    				vm.customerMasterDetailsThree.bondHolderPhoneNumber = vm.cbsDetail.rmPhoneNo;
                                    				vm.customerMasterDetailsThree.bondHldMobNum = vm.cbsDetail.refPhoneMobile;
                                    				vm.customerMasterDetailsThree.bondHldEmail = vm.cbsDetail.refCustEmail;
                                    				vm.customerMasterDetailsThree.operativeAccNum = vm.cbsDetail.operativeAccNo;
                                    				vm.customerMasterDetailsThree.operativeAccBranchId = vm.cbsDetail.operativeBranchId;
                                    				vm.customerMasterDetailsThree.aadhaarNo = vm.cbsDetail.codAadhaarNo;
                                                    vm.showAccountValidationSuccess();
                                                  } else {
                                                 vm.showCBSServiceWarning(response.data.result.status.replyText);
                                                   console.log("ReplyText is not 0, it is: " + response.data.statusCode.result.status.replyText);
                                               }
                                             } else {
                                                      vm.showCBSServiceWarning(response.data.result.status.replyText);
                                                    console.log("Missing result or status in response.");
                                                }
                                            } else {
                                                vm.showCBSServiceWarning(response.data.result.status.replyText);
                                                console.log("Status Code is not 200, it is: " + response.data.statusCode);
                                            }

            			}, function(error) {
                             vm.loader = false;
                             vm.errmsgSingle = "CBS Failure";
                               vm.showCBSServiceWarning(error.data.message);
            			});


            		}
            			function getCbsCustFstJoinDetails(accountNumber) {
                    			vm.loader = true;


                    			bondTransferInService.fetchCbsCustId(accountNumber).then((response) => {
                    				vm.loader = false;

                                 if (response.data.statusCode === "200") {
                                     if (response.data.result.responseString && response.data.result) {
                                         if (response.data.result.status.replyText === "0") {
                                             console.log("ReplyText is 0");
                                             //fetch response
                                                vm.cbsDetail = response.data.result.responseString.customerDetailsDTO[0];
                                                  if (vm.customerMasterDetails.bondHldDob === null) {
                                                       vm.customerMasterDetails[index].bondHldDob = convertToDateObject(vm.formatCBSDate(vm.cbsDetail.datBirthCust));
                                                   }
                                                vm.customerMasterDetails.bondHldFirstName = vm.cbsDetail.customerFullName;
                                                vm.customerMasterDetails.bondHldMidName = vm.cbsDetail.customerMiddleName;
                                                vm.customerMasterDetails.bondHldLastName = vm.cbsDetail.customerLastName;
                                                vm.customerMasterDetails.fatherName = vm.cbsDetail.fatherName;
                                                vm.customerMasterDetails.motherName = vm.cbsDetail.motherName;
                                                vm.customerMasterDetails.gender = vm.cbsDetail.txtCustSex;
            //                    				vm.customerMasterDetails.bondHldDob = convertToDateObject(vm.cbsDetail.dateOfBirth);
                                                 vm.customerMasterDetails.isMinor = vm.cbsDetail.isMinor;
                                                vm.customerMasterDetails.bondHldAddOne = vm.cbsDetail.txtCustadrAdd1;
                                                vm.customerMasterDetails.bondHldAddTwo = vm.cbsDetail.txtCustadrAdd2;
                                                vm.customerMasterDetails.bondHldAddThree = vm.cbsDetail.txtCustadrAdd3;
                                                vm.customerMasterDetails.bondHldCity = vm.cbsDetail.namHoldadrCity;
                                                vm.customerMasterDetails.bondHldPinCode = vm.cbsDetail.txtCustadrZip;
                                                vm.customerMasterDetails.bondHolderPhoneNumber = vm.cbsDetail.rmPhoneNo;
                                                vm.customerMasterDetails.bondHldMobNum = vm.cbsDetail.refPhoneMobile;
                                                vm.customerMasterDetails.bondHldEmail = vm.cbsDetail.refCustEmail;
//                                                vm.customerMasterDetails.operativeAccNum = vm.cbsDetail.operativeAccNo;
                                                vm.customerMasterDetails.operativeAccBranchId = vm.cbsDetail.operativeBranchId;
                                                vm.customerMasterDetails.aadhaarNo = vm.cbsDetail.codAadhaarNo;
                                                  vm.showAccountValidationSuccess();
                                                  } else {
                                                 vm.showCBSServiceWarning(response.data.result.status.replyText);
                                                   console.log("ReplyText is not 0, it is: " + response.data.statusCode.result.status.replyText);
                                               }
                                             } else {
                                                      vm.showCBSServiceWarning(response.data.result.status.replyText);
                                                    console.log("Missing result or status in response.");
                                                }
                                            } else {
                                                vm.showCBSServiceWarning(response.data.result.status.replyText);
                                                console.log("Status Code is not 200, it is: " + response.data.statusCode);
                                            }

                    			}, function(error) {

                                     vm.loader = false;
                                     vm.errmsgSingle = "CBS Failure";
                                       vm.showCBSServiceWarning(error.data.message);
                    			});

                    		}
 function validateCount(value) {
      try {
        var intee = parseInt(value);
        if (intee <= 3 && intee > 1) {
        } else {
          vm.noOfJointAcc = "";
        }
      } catch (error) {
        vm.noOfJointAcc = "";
      }
    }
                    		// Success alert for account validation
                                function showAccountValidationSuccess() {
                                  Swal.fire("Success", "Data has been fetched successfully!", "success");
                                }
                    		  function showCBSServiceWarning(msg) {
                                       if(msg == 'empty'){
                                       Swal.fire("Warning", "CBS service is down. Please try again after some time.", "warning");
                                     }else{

                                       Swal.fire("Warning", msg, "warning");

                                     }
                                       }

            function getCbsCustFstJoinDetailsModify() {
            			vm.loader = true;

            			var requestData = {
            				'cifId': vm.customerMasterDetails.C004.oldValue,
            				'operativeAccNo': vm.customerMasterDetails.C034.oldValue,
            				'operativeAccBranchId': vm.customerMasterDetails.C035.oldValue
            			};

            			bondTransferInService.fetchCbsCustId(requestData).then((response) => {
            				vm.loader = false;

                         if (response.data.statusCode === "200") {
                             if (response.data.result.responseString && response.data.result) {
                                 if (response.data.result.status.replyText === "0") {

            				vm.cbsDetail = response.data.result;

            				vm.customerMasterDetails.C006.oldValue = vm.cbsDetail.firstName;
            				vm.customerMasterDetails.C007.oldValue = vm.cbsDetail.middleName;
            				vm.customerMasterDetails.C008.oldValue = vm.cbsDetail.lastName;
            				vm.customerMasterDetails.C010.oldValue = vm.cbsDetail.fatherName;
            				vm.customerMasterDetails.C011.oldValue = vm.cbsDetail.motherName;
            				vm.customerMasterDetails.C012.oldValue = vm.cbsDetail.txtCustSex;
            				vm.customerMasterDetails.C013.oldValue = convertToDateObject(vm.cbsDetail.dateOfBirth);
            				vm.customerMasterDetails.C014.oldValue = vm.cbsDetail.isMinor;
            				vm.customerMasterDetails.C015.oldValue = vm.cbsDetail.addressOne;
            				vm.customerMasterDetails.C016.oldValue = vm.cbsDetail.addressTwo;
            				vm.customerMasterDetails.C017.oldValue = vm.cbsDetail.addressThree;
            				vm.customerMasterDetails.C018.oldValue = vm.cbsDetail.city;
            				vm.customerMasterDetails.C020.oldValue = vm.cbsDetail.pinCode;
            				vm.customerMasterDetails.C046.oldValue = vm.cbsDetail.phoneNumber;
            				vm.customerMasterDetails.C021.oldValue = vm.cbsDetail.mobileNumber;
            				vm.customerMasterDetails.C022.oldValue = vm.cbsDetail.email;
            				vm.customerMasterDetails.C034.oldValue = vm.cbsDetail.operativeAccNo;
            				vm.customerMasterDetails.C035.oldValue = vm.cbsDetail.operativeBranchId;
            				vm.customerMasterDetails.C036.oldValue = vm.cbsDetail.pan;
            				vm.customerMasterDetails.C037.oldValue = vm.cbsDetail.aadhar;
            				vm.customerMasterDetailsThree.C023.oldValue = vm.blaAccDetailsThree.hufRegPowerOfAttorney;
            				vm.customerMasterDetailsThree.C024.oldValue = vm.blaAccDetailsThree.powerOfAttorneyKartaName;
                               vm.showAccountValidationSuccess();
                                                  } else {
                                                 vm.showCBSServiceWarning(response.data.result.status.replyText);
                                                   console.log("ReplyText is not 0, it is: " + response.data.statusCode.result.status.replyText);
                                               }
                                             } else {
                                                      vm.showCBSServiceWarning(response.data.result.status.replyText);
                                                    console.log("Missing result or status in response.");
                                                }
                                            } else {
                                                vm.showCBSServiceWarning(response.data.result.status.replyText);
                                                console.log("Status Code is not 200, it is: " + response.data.statusCode);
                                            }
            			}, function(error) {
            				vm.loader = false;
            				vm.errmsgSingle = 'CBS Failure';
            				handleErrorResponse(error);
            				vm.customerMasterDetails.C004.oldValue = '';
            			});
            		}
function formatCBSDate(dateStr) {
        // Extract day, month, and year from the string
        const day = dateStr.substring(6, 8);   // Extracts "06"
        const month = dateStr.substring(4, 6); // Extracts "02"
        const year = dateStr.substring(0, 4);  // Extracts "1988"

        // Return the formatted date as DD/MM/YYYY
        return `${day}/${month}/${year}`;
    }
            		function getCbsCustFstJoinDetailsTwoModify() {
            			vm.loader = true;


            			var requestData = {
            				'cifId': vm.customerMasterDetailsTwo.C004.oldValue,
            				'operativeAccNo': vm.customerMasterDetailsTwo.C034.oldValue,
            				'operativeAccBranchId': vm.customerMasterDetailsTwo.C035.oldValue
            			};

            			bondTransferInService.fetchCbsCustId(requestData).then((response) => {
            				vm.loader = false;
            				vm.cbsDetailTwo = response.data.result;
            				vm.customerMasterDetailsTwo.C006.oldValue = vm.cbsDetailTwo.firstName;
            				vm.customerMasterDetailsTwo.C007.oldValue = vm.cbsDetailTwo.middleName;
            				vm.customerMasterDetailsTwo.C008.oldValue = vm.cbsDetailTwo.lastName;
            				vm.customerMasterDetailsTwo.C010.oldValue = vm.cbsDetailTwo.fatherName;
            				vm.customerMasterDetailsTwo.C011.oldValue = vm.cbsDetailTwo.motherName;
            				vm.customerMasterDetailsTwo.C012.oldValue = vm.cbsDetailTwo.gender;
            				vm.customerMasterDetailsTwo.C013.oldValue = convertToDateObject(vm.cbsDetailTwo.dateOfBirth);
            				vm.customerMasterDetailsTwo.C014.oldValue = vm.cbsDetailTwo.isMinor;
            				vm.customerMasterDetailsTwo.C015.oldValue = vm.cbsDetailTwo.addressOne;
            				vm.customerMasterDetailsTwo.C016.oldValue = vm.cbsDetailTwo.addressTwo;
            				vm.customerMasterDetailsTwo.C017.oldValue = vm.cbsDetailTwo.addressThree;
            				vm.customerMasterDetailsTwo.C018.oldValue = vm.cbsDetailTwo.city;
            				vm.customerMasterDetailsTwo.C020.oldValue = vm.cbsDetailTwo.pinCode;
            				vm.customerMasterDetailsTwo.C046.oldValue = vm.cbsDetailTwo.phoneNumber;
            				vm.customerMasterDetailsTwo.C021.oldValue = vm.cbsDetailTwo.mobileNumber;
            				vm.customerMasterDetailsTwo.C022.oldValue = vm.cbsDetailTwo.email;
            				vm.customerMasterDetailsTwo.C034.oldValue = vm.cbsDetailTwo.operativeAccNo;
            				vm.customerMasterDetailsTwo.C035.oldValue = vm.cbsDetailTwo.operativeBranchId;
            				vm.customerMasterDetailsTwo.C036.oldValue = vm.cbsDetailTwo.pan;
            				vm.customerMasterDetailsTwo.C037.oldValue = vm.cbsDetailTwo.aadhar;
            				vm.customerMasterDetailsThree.C023.oldValue = vm.blaAccDetailsThree.hufRegPowerOfAttorney;
            				vm.customerMasterDetailsThree.C024.oldValue = vm.blaAccDetailsThree.powerOfAttorneyKartaName;

            			}, function(error) {
            				vm.loader = false;
            				vm.errmsgSingle = 'CBS Failure';
            				handleErrorResponse(error);
            				vm.customerMasterDetailsTwo.C004.oldValue = '';
            			});


            		}

            		function getCbsCustFstJoinDetailsThreeModify() {
            			vm.loader = true;


            			var requestData = {
            				'cifId': vm.customerMasterDetailsThree.C004.oldValue,
            				'operativeAccNo': vm.customerMasterDetailsThree.C034.oldValue,
            				'operativeAccBranchId': vm.customerMasterDetailsThree.C035.oldValue
            			};


            			bondTransferInService.fetchCbsCustId(requestData).then((response) => {
            				vm.loader = false;
            				vm.cbsDetailThree = response.data.result;

            				vm.customerMasterDetailsThree.C006.oldValue = vm.cbsDetailThree.firstName;
            				vm.customerMasterDetailsThree.C007.oldValue = vm.cbsDetailThree.middleName;
            				vm.customerMasterDetailsThree.C008.oldValue = vm.cbsDetailThree.lastName;
            				vm.customerMasterDetailsThree.C010.oldValue = vm.cbsDetailThree.fatherName;
            				vm.customerMasterDetailsThree.C011.oldValue = vm.cbsDetailThree.motherName;
            				vm.customerMasterDetailsThree.C012.oldValue = vm.cbsDetailThree.gender;
            				vm.customerMasterDetailsThree.C013.oldValue = convertToDateObject(vm.cbsDetailThree.dateOfBirth);
            				vm.customerMasterDetailsThree.C014.oldValue = vm.cbsDetailThree.isMinor;
            				vm.customerMasterDetailsThree.C015.oldValue = vm.cbsDetailThree.addressOne;
            				vm.customerMasterDetailsThree.C016.oldValue = vm.cbsDetailThree.addressTwo;
            				vm.customerMasterDetailsThree.C017.oldValue = vm.cbsDetailThree.addressThree;
            				vm.customerMasterDetailsThree.C018.oldValue = vm.cbsDetailThree.city;
            				vm.customerMasterDetailsThree.C020.oldValue = vm.cbsDetailThree.pinCode;
            				vm.customerMasterDetailsThree.C046.oldValue = vm.cbsDetailThree.phoneNumber;
            				vm.customerMasterDetailsThree.C021.oldValue = vm.cbsDetailThree.mobileNumber;
            				vm.customerMasterDetailsThree.C022.oldValue = vm.cbsDetailThree.email;
            				vm.customerMasterDetailsThree.C034.oldValue = vm.cbsDetailThree.operativeAccNo;
            				vm.customerMasterDetailsThree.C035.oldValue = vm.cbsDetailThree.operativeBranchId;
            				vm.customerMasterDetailsThree.C036.oldValue = vm.cbsDetailThree.pan;
            				vm.customerMasterDetailsThree.C037.oldValue = vm.cbsDetailThree.aadhar;
            				vm.customerMasterDetailsThree.C023.oldValue = vm.blaAccDetailsThree.hufRegPowerOfAttorney;
            				vm.customerMasterDetailsThree.C024.oldValue = vm.blaAccDetailsThree.powerOfAttorneyKartaName;

            			}, function(error) {
            				vm.loader = false;
            				vm.errmsgSingle = 'CBS Failure';
            				handleErrorResponse(error);
            				vm.customerMasterDetailsThree.C004.oldValue = '';
            			});


            		}

  function getverifyTorchDetails(flag){
                    vm.loader = true;


                   if(flag == "bla"){

                    var requestData = {
                        'moduleId' : vm.schemeCode,
                        'blaNo' : vm.blaNo,
//                        'bondSubsId' : null

                    };
                    bondTransferInService.getverifyTorchDetails(requestData).then(function (response) {
                        vm.loader = false;
                        vm.blaDataList = response.data.result;
                        angular.element('#blaModel').modal('toggle');

                    }, function (error) {
                        handleErrorResponse(error);
                        vm.loader = false;
                    });
                   }

                   if(flag == "bondSubsId"){

                       var requestData = {
                       'moduleId' : vm.schemeCode,
                       'blaNo' : vm.blaNo,
                       'boSubsId' : vm.subscriptionId,
                       'blaFlag' : flag
                       };
                    bondTransferInService.getverifyTorchDetails(requestData).then(function (response) {
                       vm.loader = false;
                       vm.bondSubsList = response.data.result;
                       angular.element('#bondSubsIdSearch').modal('toggle');
                       }, function (error) {
                         handleErrorResponse(error);
                          vm.loader = false;
                    });
                  }

        }

  function getModifySelectDetails(flag){
                    vm.loader = true;
 if(flag == "pan"){



                                       var requestData = {
                                           'schemeCode' : vm.schemeCode,
                                           'bondHolderName' : vm.bondHolderName,
                       'panNum' : vm.panNum

                                       };
                    bondTransferInService.modifyPan(requestData).then(function (response) {
                        vm.loader = false;
                        vm.panDataList = response.data.result;
                        angular.element('#panModel').modal('toggle');

                    }, function (error) {
                        handleErrorResponse(error);
                        vm.loader = false;
                    });
                   }


                   if(flag == "bla"){



                                       var requestData = {
                                           'schemeCode' : vm.schemeCode,
                                           'blaNo' : vm.blaNo,
                                            'blaFlag' : 'B',
                                            'panNum' : vm.panNum

                                       };
                    bondTransferInService.modifySelect(requestData).then(function (response) {
                        vm.loader = false;
                        vm.blaDataList = response.data.result;
                        angular.element('#blaModel').modal('toggle');

                    }, function (error) {
                        handleErrorResponse(error);
                        vm.loader = false;
                    });
                   }

                   if(flag == "bondSubsId"){


                       var requestData = {
                       'schemeCode' : vm.schemeCode,
                       'blaNo' : vm.blaNo,
                       'boSubsId' : vm.subscriptionId,
                       'blaFlag' : 'S'
                       };
                    bondTransferInService.modifySelect(requestData).then(function (response) {
                       vm.loader = false;
                       vm.bondSubsList = response.data.result;
                       angular.element('#subscriptionModel').modal('toggle');
                       }, function (error) {
                         handleErrorResponse(error);
                          vm.loader = false;
                    });
                  } if(flag == "D"){


				var requestData = {
					'schemeCode': vm.schemeCode,
					'blaNo': vm.blaNo,
					'subscriptionId': vm.subscriptionId,
                       'blaFlag' : 'D',
                      'panNum' : vm.panNum

				};

				                    bondTransferInService.modifySelect(requestData).then(function (response) {

						vm.loader = false;
						vm.getDetails = response.data.result;
                      vm.amtInWords ="NDF";
              if(vm.getDetails.subscriptionDetailsValueModel[0] &&
                          vm.getDetails.subscriptionDetailsValueModel[0].C012)
                        {
                   vm.amtInWords = vm.getDetails.subscriptionDetailsValueModel[0].C044?.oldValue || amountToWords(vm.getDetails.subscriptionDetailsValueModel[0].C012.oldValue);
                        }
                        if(null!=vm.getDetails.masterDetailsValueModel[0].C056){
                   vm.authRemarks = vm.getDetails.masterDetailsValueModel[0].C056.oldValue;
                        }
                        if(vm.getDetails.masterDetailsValueModel[0] &&
                        vm.getDetails.masterDetailsValueModel[0].C050)
                      {
                      var flag="Y";
                         vm.getbankNameM(vm.getDetails.masterDetailsValueModel[0].C050?.oldValue,flag);
                      }
                        if(null!=vm.getDetails&&null!=vm.getDetails.nomineeDetailsValueModel){
                        let oldVal1 = vm.getDetails.nomineeDetailsValueModel[0]?.C016?.oldValue;
                        if (null!=oldVal1) {
                            vm.getDetails.nomineeDetailsValueModel[0].C016.oldValue = convertToDateObject(oldVal1);
                        }
                        let oldVal2 = vm.getDetails.nomineeDetailsValueModel[1]?.C016?.oldValue;
                       if (null!=oldVal2) {
                           vm.getDetails.nomineeDetailsValueModel[1].C016.oldValue = convertToDateObject(oldVal2);
                       }
                     let oldVal3 = vm.getDetails.nomineeDetailsValueModel[2]?.C016?.oldValue;
                     if (null!=oldVal3) {
                         vm.getDetails.nomineeDetailsValueModel[2].C016.oldValue = convertToDateObject(oldVal3);
                     }
                     let oldVal4 = vm.getDetails.nomineeDetailsValueModel[3]?.C016?.oldValue;
                     if (null!=oldVal4) {
                         vm.getDetails.nomineeDetailsValueModel[3].C016.oldValue = convertToDateObject(oldVal4);
                     }
                        }
						if (vm.getDetails.masterDetailsValueModel.length == 1) {
							vm.titleText = 'BOND HOLDER DETAILS';
						} else {
							vm.titleText = 'FIRST JOINT BOND HOLDER DETAILS';

							vm.tempArray = [];
							vm.tempArray.push('0');
							vm.tempArray.push('1');
							if (vm.getDetails.masterDetailsValueModel.length > 2) {
								vm.tempArray.push('2');
							}

							angular.forEach(vm.getDetails.masterDetailsValueModel, function(item) {

								// Directly assign values based on C048.oldValue
								if (item.C048.oldValue == '1') {
									vm.tempArray[0] = item; // Assign item to index 0
								}
								if (item.C048.oldValue == '2') {
									vm.tempArray[1] = item; // Assign item to index 1
								}
								if (item.C048.oldValue == '3') {
									vm.tempArray[2] = item; // Assign item to index 2
								}

							});

							// Filter the array to remove '0', '1', '2' and only keep items
							var value = vm.tempArray.filter(function(value) {
								// Only return actual items, remove '0', '1', '2' placeholders
								return value !== '0' && value !== '1' && value !== '2';
							});

							// Update master details with the filtered value
							vm.getDetails.masterDetailsValueModel = value;

							if (vm.getDetails.guardianDetailsValueModel.length == '2') {
								vm.tempArray1 = [];
								vm.tempArray1.push('0');
								vm.tempArray1.push('1');
								if (vm.getDetails.guardianDetailsValueModel.length > 2) {
									vm.tempArray1.push('2');
								}
								// Processing guardian details
								angular.forEach(vm.getDetails.guardianDetailsValueModel, function(item) {
									if (item.C003.oldValue == '1') {
										vm.tempArray1.splice(0, '0', item);
									}
									if (item.C003.oldValue == '2') {
										vm.tempArray1.splice(1, '1', item);
									}
									if (item.C003.oldValue == '3') {
										vm.tempArray1.splice(2, '2', item);
									}

								});


								var value1 = vm.tempArray1.filter((value1) => {
									if (value1 == '0') {

									} else
									if (value1 == '1') {

									} else
									if (value1 == '2') {

									} else {
										return value1;
									}
								});


								// Updating the guardian details
								vm.getDetails.guardianDetailsValueModel = vm.tempArray1;
							}
						}




						vm.modeOfHolding = vm.getDetails.masterDetailsValueModel[0].C003.oldValue;

						vm.customerMasterDetails = vm.getDetails.masterDetailsValueModel[0];
						vm.transferDetailValueModel = vm.getDetails.transferDetailValueModel[0];
						vm.customerMasterDetailsTwo = vm.getDetails.masterDetailsValueModel[1];
						vm.customerMasterDetailsThree = vm.getDetails.masterDetailsValueModel[2];
						vm.guardianDetailsValueModel = vm.getDetails.guardianDetailsValueModel[0];
						vm.guardianDetailsValueModel1 = vm.getDetails.guardianDetailsValueModel[1];
						vm.guardianDetailsValueModel2 = vm.getDetails.guardianDetailsValueModel[2];
						vm.subscriptionDetailsValueModel = vm.getDetails.subscriptionDetailsValueModel[0];
						vm.customerMasterDetails.C009.oldValue = convertToDateObject(vm.customerMasterDetails.C009.oldValue);

						vm.subscriptionDetailsValueModel.C015.oldValue = convertToDateObject(vm.subscriptionDetailsValueModel.C015.oldValue);
						vm.subscriptionDetailsValueModel.C016.oldValue = convertToDateObject(vm.subscriptionDetailsValueModel.C016.oldValue);
						if (vm.subscriptionDetailsValueModel.C035) {
							vm.subscriptionDetailsValueModel.C035.oldValue = convertToDateObject(vm.subscriptionDetailsValueModel.C035.oldValue);
						}
						if (vm.subscriptionDetailsValueModel.C031) {
							vm.subscriptionDetailsValueModel.C031.oldValue = convertToDateObject(vm.subscriptionDetailsValueModel.C031.oldValue);

						}

						if (vm.getDetails.guardianDetailsValueModel[0]) {
							if (vm.getDetails.guardianDetailsValueModel[0].C010) {
								vm.guardianDetailsValueModel.C010.oldValue = convertToDateObject(vm.getDetails.guardianDetailsValueModel[0].C010.oldValue);
							}
						}


						if (vm.getDetails.masterDetailsValueModel.length >= 2) {
							vm.customerMasterDetailsTwo = vm.getDetails.masterDetailsValueModel[1];
							vm.guardianDetailsValueModel2 = vm.getDetails.guardianDetailsValueModel[1];
							vm.customerMasterDetailsTwo.C009.oldValue = convertToDateObject(vm.customerMasterDetailsTwo.C009.oldValue);

							if (vm.getDetails.guardianDetailsValueModel) {
								if (vm.guardianDetailsValueModel2 !== '0') {
									if (vm.getDetails.guardianDetailsValueModel[1]) {
										if (vm.getDetails.guardianDetailsValueModel[1].C010) {
											vm.guardianDetailsValueModel2.C010.oldValue = convertToDateObject(vm.guardianDetailsValueModel2.C010.oldValue);
										}
									}
								}
							}
							//                                                                 if(vm.guardianDetailsValueModel2.C014){
							//                                                                       vm.guardianDetailsValueModel2.C014.oldValue = convertToDateObject(vm.guardianDetailsValueModel2.C014.oldValue);
							//                                                                 }
							//                                                                 if(vm.guardianDetailsValueModel2.C015){
							//                                                                      vm.guardianDetailsValueModel2.C015.oldValue = convertToDateObject(vm.guardianDetailsValueModel2.C015.oldValue);
							//                                                                 }

						}
						if (vm.getDetails.masterDetailsValueModel.length >= 3) {
							vm.customerMasterDetailsThree = vm.getDetails.masterDetailsValueModel[2];
							vm.guardianDetailsValueModel3 = vm.getDetails.guardianDetailsValueModel[2];
							vm.customerMasterDetailsThree.C009.oldValue = convertToDateObject(vm.customerMasterDetailsThree.C009.oldValue);

							if (vm.getDetails.guardianDetailsValueModel) {
								if (vm.guardianDetailsValueModel3 !== '0') {
									if (vm.getDetails.guardianDetailsValueModel[2]) {
										if (vm.getDetails.guardianDetailsValueModel[2].C010) {
											vm.guardianDetailsValueModel3.C010.oldValue = convertToDateObject(vm.guardianDetailsValueModel3.C010.oldValue);
										}
									}
								}
							}
							//                                                                 if(vm.guardianDetailsValueModel3){
							//                                                                       vm.guardianDetailsValueModel3.C014.oldValue = convertToDateObject(vm.guardianDetailsValueModel3.C014.oldValue);
							//                                                                 }
							//                                                                 if(vm.guardianDetailsValueModel3){
							//                                                                      vm.guardianDetailsValueModel3.C015.oldValue = convertToDateObject(vm.guardianDetailsValueModel3.C015.oldValue);
							//                                                                 }

						}



						try {
							if (vm.customerMasterDetails) {

								vm.customerMasterDetails.C037.oldValue = convertYNToBoolean(vm.customerMasterDetails.C037.oldValue) ? 'Y' : 'N';
								vm.customerMasterDetails.C038.oldValue = convertYNToBoolean(vm.customerMasterDetails.C038.oldValue) ? 'Y' : 'N';
								vm.customerMasterDetails.C039.oldValue = convertYNToBoolean(vm.customerMasterDetails.C039.oldValue) ? 'Y' : 'N';
								vm.customerMasterDetails.C040.oldValue = convertYNToBoolean(vm.customerMasterDetails.C040.oldValue) ? 'Y' : 'N';
								vm.customerMasterDetails.C041.oldValue = convertYNToBoolean(vm.customerMasterDetails.C041.oldValue) ? 'Y' : 'N';
								vm.customerMasterDetails.C042.oldValue = convertYNToBoolean(vm.customerMasterDetails.C042.oldValue) ? 'Y' : 'N';
								vm.customerMasterDetails.C043.oldValue = convertYNToBoolean(vm.customerMasterDetails.C043.oldValue) ? 'Y' : 'N';
								vm.customerMasterDetails.C044.oldValue = convertYNToBoolean(vm.customerMasterDetails.C044.oldValue) ? 'Y' : 'N';
							}
						} catch (e) {
							console.error("Error processing customerMasterDetails: ", e);
						}

						try {
							if (vm.customerMasterDetailsTwo) {
								vm.customerMasterDetailsTwo.C037.oldValue = convertYNToBoolean(vm.customerMasterDetailsTwo.C037.oldValue) ? 'Y' : 'N';
								vm.customerMasterDetailsTwo.C038.oldValue = convertYNToBoolean(vm.customerMasterDetailsTwo.C038.oldValue) ? 'Y' : 'N';
								vm.customerMasterDetailsTwo.C039.oldValue = convertYNToBoolean(vm.customerMasterDetailsTwo.C039.oldValue) ? 'Y' : 'N';
								vm.customerMasterDetailsTwo.C040.oldValue = convertYNToBoolean(vm.customerMasterDetailsTwo.C040.oldValue) ? 'Y' : 'N';
								vm.customerMasterDetailsTwo.C041.oldValue = convertYNToBoolean(vm.customerMasterDetailsTwo.C041.oldValue) ? 'Y' : 'N';
								vm.customerMasterDetailsTwo.C042.oldValue = convertYNToBoolean(vm.customerMasterDetailsTwo.C042.oldValue) ? 'Y' : 'N';
								vm.customerMasterDetailsTwo.C043.oldValue = convertYNToBoolean(vm.customerMasterDetailsTwo.C043.oldValue) ? 'Y' : 'N';
								vm.customerMasterDetailsTwo.C044.oldValue = convertYNToBoolean(vm.customerMasterDetailsTwo.C044.oldValue) ? 'Y' : 'N';

							}
						} catch (e) {
							console.error("Error processing customerMasterDetailsTwo: ", e);
						}

						try {
							if (vm.customerMasterDetailsThree) {
								vm.customerMasterDetailsThree.C037.oldValue = convertYNToBoolean(vm.customerMasterDetailsThree.C037.oldValue) ? 'Y' : 'N';
								vm.customerMasterDetailsThree.C038.oldValue = convertYNToBoolean(vm.customerMasterDetailsThree.C038.oldValue) ? 'Y' : 'N';
								vm.customerMasterDetailsThree.C039.oldValue = convertYNToBoolean(vm.customerMasterDetailsThree.C039.oldValue) ? 'Y' : 'N';
								vm.customerMasterDetailsThree.C040.oldValue = convertYNToBoolean(vm.customerMasterDetailsThree.C040.oldValue) ? 'Y' : 'N';
								vm.customerMasterDetailsThree.C041.oldValue = convertYNToBoolean(vm.customerMasterDetailsThree.C041.oldValue) ? 'Y' : 'N';
								vm.customerMasterDetailsThree.C042.oldValue = convertYNToBoolean(vm.customerMasterDetailsThree.C042.oldValue) ? 'Y' : 'N';
								vm.customerMasterDetailsThree.C043.oldValue = convertYNToBoolean(vm.customerMasterDetailsThree.C043.oldValue) ? 'Y' : 'N';
								vm.customerMasterDetailsThree.C044.oldValue = convertYNToBoolean(vm.customerMasterDetailsThree.C044.oldValue) ? 'Y' : 'N';
							}
						} catch (e) {
							console.error("Error processing customerMasterDetailsThree: ", e);
						}


					},
					function(error) {
						vm.loader = false;
							 const errorMessage = error && error.data && error.data.message
                                  ? error.data.message
                                  : "An unexpected error occurred.";
                          if(null!=errorMessage && errorMessage.startsWith("Invalid")){
                          Swal.fire("Warning!", errorMessage , "warning");
                          $state.go("rbi_bond_transfer_in.inquiry");
                          }else{
                          handleErrorResponse(error);
                          $state.go("rbi_bond_transfer_in.inquiry");
                          }

					});
                  }

        }

	const convertToColumnValuesArrayList = (objectToBeConvert) => {
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
vm.getbankNameM=getbankNameM;
 function getbankNameM( branchCode,flag) {
                  var requestData = {
                    referenceId: branchCode,
                  };
                  bondTransferInService.getbankName(requestData).then(
                    function (response) {
                    if(flag=="Y"){
                      vm.customerMasterDetails.bankName = response.data.result[0].bank_name;}
                    else if(flag=="N"){
                     vm.customerMasterDetails.C051.oldValue = response.data.result[0].bank_name;}
                    },
                    function (error) {
              handleErrorResponse(error);
                    });
                }
  function getInquiryTorchDetails(){
                      vm.loader = true;
                         var requestData = {
                        'schemeCode': vm.schemeCode,
                        					'blaNo': vm.blaNo,
                        					'schemeName': vm.schemeName,
                        					'panNum': vm.panNum,
                        					'bondHolderName': vm.bondHolderName,
                        					'boSubsId': vm.subscriptionId,
        				'blaFlag': 'D'
                         };
                      bondTransferInService.getInquiryTorchDetails(requestData).then(function (response) {

					vm.getDetails = response.data.result;
					vm.loader = false;

					if (vm.getDetails.masterDetailsValueModel.length == 1) {
						vm.titleText = 'BOND HOLDER DETAILS';
					} else {
						vm.titleText = 'FIRST JOINT BOND HOLDER DETAILS';

						vm.tempArray = [];
						vm.tempArray.push('0');
						vm.tempArray.push('1');
						if (vm.getDetails.masterDetailsValueModel.length > 2) {
							vm.tempArray.push('2');
						}

						angular.forEach(vm.getDetails.masterDetailsValueModel, function(item) {

							if (item.C048.oldValue == '1') {
								vm.tempArray.splice(0, '0', item);
							}
							if (item.C048.oldValue == '2') {
								vm.tempArray.splice(1, '1', item);
							}
							if (item.C048.oldValue == '3') {
								vm.tempArray.splice(2, '2', item);
							}

						});
						//
						var value = vm.tempArray.filter((value) => {
							if (value == '0') {

							} else
							if (value == '1') {

							} else
							if (value == '2') {

							} else {
								return value;
							}
						});
						vm.getDetails.masterDetailsValueModel = value;

						angular.forEach(vm.getDetails.guardianDetailsValueModel, function(item) {

							if (item.C003.oldValue == '1') {
								vm.tempArray.splice(0, '0', item);
							}
							if (item.C003.oldValue == '2') {
								vm.tempArray.splice(1, '1', item);
							}
							if (item.C003.oldValue == '3') {
								vm.tempArray.splice(2, '2', item);
							}

						});
						//
						var value1 = vm.tempArray.filter((value1) => {
							if (value1 == '0') {

							} else
							if (value1 == '1') {

							} else
							if (value1 == '2') {

							} else {
								return value1;
							}
						});
						vm.getDetails.guardianDetailsValueModel = value1;

					}


               if (vm.getDetails.masterDetailsValueModel.length >= 1) {
                   // Use the ternary operator correctly by assigning the result
                   vm.getDetails.masterDetailsValueModel[0].C015.oldValue =
                       vm.getDetails.masterDetailsValueModel[0].C015.oldValue == 'M' ? 'MALE' :
                       vm.getDetails.masterDetailsValueModel[0].C015.oldValue == 'F' ? 'FEMALE' :
                       vm.getDetails.masterDetailsValueModel[0].C015.oldValue == 'O' ? 'OTHERS' : '';




                       if(vm.getDetails.previousOperationType == 'M'){

                        vm.getDetails.masterDetailsValueModel[0].C015.newValue =
                                              vm.getDetails.masterDetailsValueModel[0].C015.newValue == 'M' ? 'MALE' :
                                              vm.getDetails.masterDetailsValueModel[0].C015.newValue == 'F' ? 'FEMALE' :
                                              vm.getDetails.masterDetailsValueModel[0].C015.newValue == 'O' ? 'OTHERS' : '';

             vm.getDetails.masterDetailsValueModel[0].C012.newValue =
                                                            vm.getDetails.masterDetailsValueModel[0].C012.newValue == 'HT001' ? 'Power of Attorney' :
                                                            vm.getDetails.masterDetailsValueModel[0].C012.newValue == 'HT002' ? 'Karta of HUF' :
                                                            vm.getDetails.masterDetailsValueModel[0].C012.newValue == 'HT003' ? 'Individual' : '';

                                     }









                       vm.getDetails.masterDetailsValueModel[0].C012.oldValue =
                                                               vm.getDetails.masterDetailsValueModel[0].C012.oldValue == 'HT001' ? 'Power of Attorney' :
                                                               vm.getDetails.masterDetailsValueModel[0].C012.oldValue == 'HT002' ? 'Karta of HUF' :
                                                             vm.getDetails.masterDetailsValueModel[0].C012.oldValue == 'HT003' ? 'Individual' : '';



               }

               if (vm.getDetails.masterDetailsValueModel.length >= 2) {
                   // Again, use the ternary operator properly
                   vm.getDetails.masterDetailsValueModel[1].C015.oldValue =
                       vm.getDetails.masterDetailsValueModel[1].C015.oldValue == 'M' ? 'MALE' :
                       vm.getDetails.masterDetailsValueModel[1].C015.oldValue == 'F' ? 'FEMALE' :
                       vm.getDetails.masterDetailsValueModel[1].C015.oldValue == 'O' ? 'OTHERS' : '';


                                        if(vm.getDetails.previousOperationType == 'M'){

                                               vm.getDetails.masterDetailsValueModel[1].C015.newValue =
                                                                     vm.getDetails.masterDetailsValueModel[1].C015.newValue == 'M' ? 'MALE' :
                                                                     vm.getDetails.masterDetailsValueModel[1].C015.newValue == 'F' ? 'FEMALE' :
                                                                     vm.getDetails.masterDetailsValueModel[1].C015.newValue == 'O' ? 'OTHERS' : '';

                                                                                    vm.getDetails.masterDetailsValueModel[1].C012.newValue =
                                                                                                                  (vm.getDetails.masterDetailsValueModel[1].C012.newValue === 'HT001') ? 'Individual' :
                                                                                                                  (vm.getDetails.masterDetailsValueModel[1].C012.newValue === 'HT002' &&
                                                                                                                   vm.getDetails.masterDetailsValueModel[1].C003.newValue === 'MH001') ? 'Power of Attorney' :
                                                                                                                  (vm.getDetails.masterDetailsValueModel[1].C012.newValue === 'HT002' &&
                                                                                                                   vm.getDetails.masterDetailsValueModel[1].C003.newValue === 'MH004') ? 'Karta of HUF' :
                                                                                                                  (vm.getDetails.masterDetailsValueModel[1].C012.newValue === 'HT003') ? 'Individual' :
                                                                                                                  '';


                                                 }
                                                  vm.getDetails.masterDetailsValueModel[1].C012.oldValue =
                                                                                                                                                  (vm.getDetails.masterDetailsValueModel[1].C012.oldValue === 'HT001') ? 'Individual' :
                                                                                                                                                  (vm.getDetails.masterDetailsValueModel[1].C012.oldValue === 'HT002' &&
                                                                                                                                                   vm.getDetails.masterDetailsValueModel[1].C003.oldValue === 'MH001') ? 'Power of Attorney' :
                                                                                                                                                  (vm.getDetails.masterDetailsValueModel[1].C012.oldValue === 'HT002' &&
                                                                                                                                                   vm.getDetails.masterDetailsValueModel[1].C003.oldValue === 'MH004') ? 'Karta of HUF' :
                                                                                                                                                  (vm.getDetails.masterDetailsValueModel[1].C012.oldValue === 'HT003') ? 'Individual' :
                                                                                                                                                  '';













               }

               if (vm.getDetails.masterDetailsValueModel.length >= 3) {
                   // Use the ternary operator correctly here as well
                   vm.getDetails.masterDetailsValueModel[2].C015.oldValue =
                       vm.getDetails.masterDetailsValueModel[2].C015.oldValue == 'M' ? 'MALE' :
                       vm.getDetails.masterDetailsValueModel[2].C015.oldValue == 'F' ? 'FEMALE' :
                       vm.getDetails.masterDetailsValueModel[2].C015.oldValue == 'O' ? 'OTHERS' : '';



                       if(vm.getDetails.previousOperationType == 'M'){

                                                                      vm.getDetails.masterDetailsValueModel[1].C015.newValue =
                                                            vm.getDetails.masterDetailsValueModel[2].C015.newValue == 'M' ? 'MALE' :
                                                           vm.getDetails.masterDetailsValueModel[2].C015.newValue == 'F' ? 'FEMALE' :
                                                            vm.getDetails.masterDetailsValueModel[2].C015.newValue == 'O' ? 'OTHERS' : '';

                                                                      vm.getDetails.masterDetailsValueModel[2].C012.newValue =
                                                                                                                                         (vm.getDetails.masterDetailsValueModel[2].C012.newValue === 'HT001') ? 'Individual' :
                                                                                                                                        (vm.getDetails.masterDetailsValueModel[2].C012.newValue === 'HT002' &&
                                                                                                                                      vm.getDetails.masterDetailsValueModel[2].C003.newValue === 'MH001') ? 'Power of Attorney' :
                                                                                                                                         (vm.getDetails.masterDetailsValueModel[2].C012.newValue === 'HT002' &&
                                                                                                                                    vm.getDetails.masterDetailsValueModel[2].C003.newValue === 'MH004') ? 'Karta of HUF' :
                                                                                                                                                          (vm.getDetails.masterDetailsValueModel[2].C012.newValue === 'HT003') ? 'Individual' :
                                                                                                                                                 '';



                                                                                                                            }



                                                                                            vm.getDetails.masterDetailsValueModel[2].C012.oldValue =
                                                                                                                                            (vm.getDetails.masterDetailsValueModel[2].C012.oldValue === 'HT001') ? 'Individual' :
                                                                                                                                            (vm.getDetails.masterDetailsValueModel[2].C012.oldValue === 'HT002' &&
                                                                                                                                             vm.getDetails.masterDetailsValueModel[2].C003.oldValue === 'MH001') ? 'Power of Attorney' :
                                                                                                                                            (vm.getDetails.masterDetailsValueModel[2].C012.oldValue === 'HT002' &&
                                                                                                                                             vm.getDetails.masterDetailsValueModel[2].C003.oldValue === 'MH004') ? 'Karta of HUF' :
                                                                                                                                            (vm.getDetails.masterDetailsValueModel[2].C012.oldValue === 'HT003') ? 'Individual' :
                                                                                                                                            '';











               }




                         }, function (error) {
                           handleErrorResponse(error);
                            vm.loader = false;
                            $state.go('rbi_bond_transfer_in.inquiry');
                      });


          }
   function validateNominee() {

                       if (vm.modeOfHolding === 'MH004') {

                           vm.isNominee = 'N';

                       } else {
                           vm.nominationDetails = [{}]; // or however you want to handle it
                       }
                   }

		vm.modeOfHoldingList = [{
        				DESCRIPTION: 'SINGLE',
        				SUBCODE: 'MH001'
        			},
        			{
        				DESCRIPTION: 'JOINT - JOINTLY',
        				SUBCODE: 'MH002'
        			},
        			{
        				DESCRIPTION: 'JOINT - ANYONE OR SURVIVOR',
        				SUBCODE: 'MH003'
        			},
        			{
        				DESCRIPTION: 'HUF',
        				SUBCODE: 'MH004'
        			},
        			{
        				DESCRIPTION: 'LEGAL GUARDIAN',
        				SUBCODE: 'MH005'
        			}
        		];
        		function showWarning(message) {
                    // Create warning modal HTML that matches your application style
                    const warningHtml = `
                        <div class="inspirys-warning-modal">
                            <div class="inspirys-modal-header">
                                <h3>Warning</h3>
                            </div>
                            <div class="inspirys-modal-body">
                                <div class="warning-icon">
                                    <i class="fa fa-exclamation-triangle"></i>
                                </div>
                                <div class="warning-message">${message}</div>
                            </div>
                            <div class="inspirys-modal-footer">
                                <button class="inspirys-btn inspirys-btn-ok">OK</button>
                            </div>
                        </div>
                    `;

                    // Add to DOM and show
                    const warningElement = angular.element(warningHtml);
                    angular.element('body').append(warningElement);

                    // Add click handler for OK button
                    warningElement.find('.inspirys-btn-ok').on('click', function() {
                        warningElement.remove();
                    });

                    // Optional: Auto-close after 5 seconds
                    setTimeout(() => warningElement.remove(), 5000);
                }
        function getTorchDetails(flag){
                    vm.loader = true;
                      if(flag == "S"){

                    var requestData = {
                    'blaFlag' : flag
                    };
                    }
                   if(flag == "bla"){

                    var requestData = {
                        'moduleId' : vm.schemeCode,
                        'blaNo' : null,
//                        'bondSubsId' : null

                    };
                    }
                     if(flag == "bondSubsId"){

                                           var requestData = {
                                           'moduleId' : vm.schemeCode,
                                           'blaNo' : vm.blaNo,
                                           'bondSubsId' : null
                                           };
                    }   if(flag == "submit"){

                                                  var requestData = {
                                                    'moduleId' : vm.schemeCode,
                                                    'blaNo' : vm.blaNo,
                                                    'bondSubsId' : vm.bondSubsId
                                                   };
                    }
                    bondTransferInService.getTorchDetails(requestData).then(function (response) {
                        vm.loader = false;
                      if(flag == "S"){
                      vm.schemeList = response.data.result.blaNoOut;
                                              angular.element('#schemeModel').modal('toggle');
                        }
                         if(flag == "bla"){
                        vm.blaDataList = response.data.result;
                        angular.element('#blaModel').modal('toggle');
                        }
                         if(flag == "bondSubsId"){
                         vm.bondSubsList = response.data.result;
                                               angular.element('#bondSubsIdSearch').modal('toggle');
                        }
                        if(flag == "submit"){
                        }

                    }, function (error) {
                        handleErrorResponse(error);
                        vm.loader = false;
                    });



        }
vm.onModeOfHoldingChange = function () {
    vm.pan_No1='';
    vm.pan_No2='';
    vm.pan_No3='';
      console.log("Selected SUBCODE:", vm.modeOfHolding);
      // Add any additional logic needed when selection changes
    };
        //Verify Select
        		function getVerifySelectDetails() {
        			vm.loader = true;

        			if (vm.schemeCode == null || vm.schemeCode == undefined) {
        				var requestData = {

        				};
        				bondTransferInService.verifySelect(requestData).then(function(response) {
        					vm.loader = false;
        					vm.getVerifyDetails = response.data.result;

        				}, function(error) {
        					vm.loader = false;
        					handleErrorResponse(error);
        					$state.go('rbi_bond_transfer_in.inquiry');

        				});
        			} else {

        				var requestData = {
        					'schemeCode': vm.schemeCode,
        					'blaNo': vm.blaNo,

        					'subscriptionId': vm.subscriptionId,
        					'modeOfHolding': vm.modeOfHolding

        				};
        				bondTransferInService.verifySelect(requestData).then(function(response) {

        					vm.getDetails = response.data.result;
        					vm.blaGroupId = vm.getDetails.blaGroupId;
        					//                                         vm.nomineeDetailsValueModel = response.data.result.nomineeDetailsValueModel;

        					vm.loader = false;

        					if (vm.getDetails.masterDetailsValueModel.length == 1) {
        						vm.titleText = 'BOND HOLDER DETAILS';
        					} else {
        						vm.titleText = 'FIRST JOINT BOND HOLDER DETAILS';

        						vm.tempArray = [];
        						vm.tempArray.push('0');
        						vm.tempArray.push('1');
        						if (vm.getDetails.masterDetailsValueModel.length > 2) {
        							vm.tempArray.push('2');
        						}

        						angular.forEach(vm.getDetails.masterDetailsValueModel, function(item) {

        							if (item.C048) {
        								if (item.C048.oldValue == '1') {
        									vm.tempArray.splice(0, '0', item);
        								}
        								if (item.C048.oldValue == '2') {
        									vm.tempArray.splice(1, '1', item);
        								}
        								if (item.C048.oldValue == '3') {
        									vm.tempArray.splice(2, '2', item);

        								}
        							}
        						});

        						var value = vm.tempArray.filter((value) => {
        							if (value == '0') {

        							} else
        							if (value == '1') {

        							} else
        							if (value == '2') {

        							} else {
        								return value;
        							}
        						});
        						vm.getDetails.masterDetailsValueModel = value;


        						angular.forEach(vm.getDetails.guardianDetailsValueModel, function(item) {

        							if (item.C003.oldValue == '1') {
        								vm.tempArray.splice(0, '0', item);
        							}
        							if (item.C003.oldValue == '2') {
        								vm.tempArray.splice(1, '1', item);
        							}
        							if (item.C003.oldValue == '3') {
        								vm.tempArray.splice(2, '2', item);
        							}

        						});
        						//
        						var value1 = vm.tempArray.filter((value1) => {
        							if (value1 == '0') {

        							} else
        							if (value1 == '1') {

        							} else
        							if (value1 == '2') {

        							} else {
        								return value1;
        							}
        						});
        						vm.getDetails.guardianDetailsValueModel = value1;

        					}






        					  if (vm.getDetails.masterDetailsValueModel.length >= 1) {
                                               // Use the ternary operator correctly by assigning the result
                                               vm.getDetails.masterDetailsValueModel[0].C015.oldValue =
                                                   vm.getDetails.masterDetailsValueModel[0].C015.oldValue == 'M' ? 'MALE' :
                                                   vm.getDetails.masterDetailsValueModel[0].C015.oldValue == 'F' ? 'FEMALE' :
                                                   vm.getDetails.masterDetailsValueModel[0].C015.oldValue == 'O' ? 'OTHERS' : '';




                                                   if(vm.getDetails.previousOperationType == 'M'){

                                                    vm.getDetails.masterDetailsValueModel[0].C015.newValue =
                                                                          vm.getDetails.masterDetailsValueModel[0].C015.newValue == 'M' ? 'MALE' :
                                                                          vm.getDetails.masterDetailsValueModel[0].C015.newValue == 'F' ? 'FEMALE' :
                                                                          vm.getDetails.masterDetailsValueModel[0].C015.newValue == 'O' ? 'OTHERS' : '';


                                                  vm.getDetails.masterDetailsValueModel[0].C012.newValue =
                                                                                                          vm.getDetails.masterDetailsValueModel[0].C012.newValue == 'HT001' ? 'Power of Attorney' :
                                                                                                          vm.getDetails.masterDetailsValueModel[0].C012.newValue == 'HT002' ? 'Karta of HUF' :
                                                                                                         vm.getDetails.masterDetailsValueModel[0].C012.newValue == 'HT003' ? 'Individual' : '';




                                                   }


                                                    vm.getDetails.masterDetailsValueModel[0].C012.oldValue =
                                                                                  vm.getDetails.masterDetailsValueModel[0].C012.oldValue == 'HT001' ? 'Power of Attorney' :
                                                                                  vm.getDetails.masterDetailsValueModel[0].C012.oldValue == 'HT002' ? 'Karta of HUF' :
                                                                                  vm.getDetails.masterDetailsValueModel[0].C012.oldValue == 'HT003' ? 'Individual' : '';














                                           }

                                           if (vm.getDetails.masterDetailsValueModel.length >= 2) {
                                               // Again, use the ternary operator properly
                                               vm.getDetails.masterDetailsValueModel[1].C015.oldValue =
                                                   vm.getDetails.masterDetailsValueModel[1].C015.oldValue == 'M' ? 'MALE' :
                                                   vm.getDetails.masterDetailsValueModel[1].C015.oldValue == 'F' ? 'FEMALE' :
                                                   vm.getDetails.masterDetailsValueModel[1].C015.oldValue == 'O' ? 'OTHERS' : '';


                                                                    if(vm.getDetails.previousOperationType == 'M'){

                                                                           vm.getDetails.masterDetailsValueModel[1].C015.newValue =
                                                                                                 vm.getDetails.masterDetailsValueModel[1].C015.newValue == 'M' ? 'MALE' :
                                                                                                 vm.getDetails.masterDetailsValueModel[1].C015.newValue == 'F' ? 'FEMALE' :
                                                                                                 vm.getDetails.masterDetailsValueModel[1].C015.newValue == 'O' ? 'OTHERS' : '';



                                                             vm.getDetails.masterDetailsValueModel[1].C012.newValue =
                                                                 (vm.getDetails.masterDetailsValueModel[1].C012.newValue === 'HT001') ? 'Individual' :
                                                                 (vm.getDetails.masterDetailsValueModel[1].C012.newValue === 'HT002' &&
                                                                  vm.getDetails.masterDetailsValueModel[1].C003.newValue === 'MH001') ? 'Power of Attorney' :
                                                                 (vm.getDetails.masterDetailsValueModel[1].C012.newValue === 'HT002' &&
                                                                  vm.getDetails.masterDetailsValueModel[1].C003.newValue === 'MH004') ? 'Karta of HUF' :
                                                                 (vm.getDetails.masterDetailsValueModel[1].C012.newValue === 'HT003') ? 'Individual' :
                                                                 '';









                                                                          }



                                            vm.getDetails.masterDetailsValueModel[1].C012.oldValue =
                                                (vm.getDetails.masterDetailsValueModel[1].C012.oldValue === 'HT001') ? 'Individual' :
                                                (vm.getDetails.masterDetailsValueModel[1].C012.oldValue === 'HT002' &&
                                                 vm.getDetails.masterDetailsValueModel[1].C003.oldValue === 'MH001') ? 'Power of Attorney' :
                                                (vm.getDetails.masterDetailsValueModel[1].C012.oldValue === 'HT002' &&
                                                 vm.getDetails.masterDetailsValueModel[1].C003.oldValue === 'MH004') ? 'Karta of HUF' :
                                                (vm.getDetails.masterDetailsValueModel[1].C012.oldValue === 'HT003') ? 'Individual' :
                                                '';




                                           }

                                           if (vm.getDetails.masterDetailsValueModel.length >= 3) {
                                               // Use the ternary operator correctly here as well
                                               vm.getDetails.masterDetailsValueModel[2].C015.oldValue =
                                                   vm.getDetails.masterDetailsValueModel[2].C015.oldValue == 'M' ? 'MALE' :
                                                   vm.getDetails.masterDetailsValueModel[2].C015.oldValue == 'F' ? 'FEMALE' :
                                                   vm.getDetails.masterDetailsValueModel[2].C015.oldValue == 'O' ? 'OTHERS' : '';



                                                   if(vm.getDetails.previousOperationType == 'M'){

                                                                                                  vm.getDetails.masterDetailsValueModel[1].C015.newValue =
                                                                                        vm.getDetails.masterDetailsValueModel[2].C015.newValue == 'M' ? 'MALE' :
                                                                                       vm.getDetails.masterDetailsValueModel[2].C015.newValue == 'F' ? 'FEMALE' :
                                                                                        vm.getDetails.masterDetailsValueModel[2].C015.newValue == 'O' ? 'OTHERS' : '';



                                                                                         vm.getDetails.masterDetailsValueModel[2].C012.newValue =
                                                                                                              (vm.getDetails.masterDetailsValueModel[2].C012.newValue === 'HT001') ? 'Individual' :
                                                                                                             (vm.getDetails.masterDetailsValueModel[2].C012.newValue === 'HT002' &&
                                                                                                           vm.getDetails.masterDetailsValueModel[2].C003.newValue === 'MH001') ? 'Power of Attorney' :
                                                                                                              (vm.getDetails.masterDetailsValueModel[2].C012.newValue === 'HT002' &&
                                                                                                         vm.getDetails.masterDetailsValueModel[2].C003.newValue === 'MH004') ? 'Karta of HUF' :
                                                                                                                               (vm.getDetails.masterDetailsValueModel[2].C012.newValue === 'HT003') ? 'Individual' :
                                                                                                                      '';



                                                                                                 }



                                                                 vm.getDetails.masterDetailsValueModel[2].C012.oldValue =
                                                                                                                 (vm.getDetails.masterDetailsValueModel[2].C012.oldValue === 'HT001') ? 'Individual' :
                                                                                                                 (vm.getDetails.masterDetailsValueModel[2].C012.oldValue === 'HT002' &&
                                                                                                                  vm.getDetails.masterDetailsValueModel[2].C003.oldValue === 'MH001') ? 'Power of Attorney' :
                                                                                                                 (vm.getDetails.masterDetailsValueModel[2].C012.oldValue === 'HT002' &&
                                                                                                                  vm.getDetails.masterDetailsValueModel[2].C003.oldValue === 'MH004') ? 'Karta of HUF' :
                                                                                                                 (vm.getDetails.masterDetailsValueModel[2].C012.oldValue === 'HT003') ? 'Individual' :
                                                                                                                 '';
                        }
        				}, function(error) {
        					vm.loader = false;
        					handleErrorResponse(error);
        					$state.go('rbi_bond_transfer_in.verify');
        				});
        			}
        		}
 vm.calculateSumM=calculateSumM;
 function calculateSumM() {
           vm.total = 0;
           for (var i = 0; i < vm.getDetails.nomineeDetailsValueModel.length; i++) {
             var share = parseFloat(vm.getDetails.nomineeDetailsValueModel[i].C012.oldValue || "0");
              if (vm.total + share <= 100) {
               vm.total += share;
             } else {
               vm.getDetails.nomineeDetailsValueModel[i].C012.oldValue = "";
               Swal.fire("Warning!", "Total share percentage cannot exceed 100%. Please adjust your inputs. Current total: " + vm.total + "%", "warning");
             }
      }
    }
    vm.decimalConvert=decimalConvert;
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
                for (var i = 0; i < vm.nominationDetails.length; i++) {
                  if (vm.index == i) {
                    vm.nominationDetails[vm.index].sharePercentage = "";
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
              for (var i = 0; i < vm.nominationDetails.length; i++) {
                if (vm.index == i) {
                  vm.nominationDetails[vm.index].sharePercentage = "";
                  break;
                }
              }
            }
          }
        }

        	function addDet() {
        			vm.loader = true;

        			const pan1 = vm.pan_No1;
        			const pan2 = vm.pan_No2 || '';
        			const pan3 = vm.pan_No3 || '';

        			if ((pan1 && pan2 && pan1 === pan2) ||
        				(pan1 && pan3 && pan1 === pan3) ||
        				(pan2 && pan3 && pan2 === pan3)) {

        				Swal.fire("Warning!", "Pan number should not be same for all the holders", "warning");
        				$state.go('rbi_bond_transfer_in.add');
        				return;
        			} else {
        				var requestData = {
        					'schemeCode': vm.schemeCode,
        					'modeOfHolding': vm.modeOfHolding,
        					'hdfcOtherBank': vm.hdfcOtherBank,
        					'schemeBondType': vm.schemeBondType,
        					'panNum1': vm.pan_No1,
        					'panNum2': vm.pan_No2,
        					'panNum3': vm.pan_No3,
        					'id': vm.id,
        					'bondHolderName': vm.bondHolderName,
        					'secondJointBondHolderName': vm.secondJointHolderName,
        					'thirdJointBondHolderName': vm.thirdJointHolderName,
        					'fromDate1': $filter('date')(vm.fromDate1, "dd/MM/yyyy"),
        					'fromDate2': $filter('date')(vm.fromDate2, "dd/MM/yyyy"),
        					'fromDate3': $filter('date')(vm.fromDate3, "dd/MM/yyyy"),

        				};

        				bondTransferInService.getTorchDetails(requestData).then(function(response) {
        					vm.loader = false;


        					if (response && response.data && response.data.result) {
        						var result = response.data.result;
        						if(response.data.result.schemeNomineeCount!=null){
                                   vm.schemeNomineeCount=response.data.result.schemeNomineeCount;
                                   }
        						if (result.dataCopersondetailsList1 && result.dataCopersondetailsList1.length > 0) {
        							var firstDetails = result.dataCopersondetailsList1[0];
        							vm.DetailsList = firstDetails;

        							vm.OpeningDate = convertDateFormat(firstDetails.accountOpeningDate);
        							vm.accountOpeningDate = vm.OpeningDate;
        							if (firstDetails.valueDate) {
        								vm.valueOpeningDate = convertDateFormat(firstDetails.valueDate);
        								vm.valueDate = vm.valueOpeningDate;
        							}
        							vm.customerMasterDetails.modeOfInterestPayment = firstDetails.modeOfInterestPayment;
        							vm.customerMasterDetails.operativeAccNumber = firstDetails.operativeAccNumber;
        							vm.bondType = firstDetails.bondType;
        							vm.brokerId = firstDetails.brokerAgencyCode;
        							vm.subscriptionAmount = firstDetails.subscriptionAmount;
        							vm.instrumentAmount = firstDetails.subscriptionAmount;
        							vm.instrumentNo = firstDetails.instrumentNo;
        							vm.transactionMode = firstDetails.transactionMode;
        							if (firstDetails.instrumentDate) {
        								vm.instruDate = convertDateFormat(firstDetails.instrumentDate);
        								vm.instrumentDate = vm.instruDate;
        							}
        						}
        						if (result.instrumentDetailsOut && result.instrumentDetailsOut.length > 0) {
        							var firstDetails = result.instrumentDetailsOut[0];
        							vm.DetailsList = firstDetails;


        							vm.OpeningDate = convertDateFormat(firstDetails.accountOpeningDate);
        							vm.accountOpeningDate = vm.OpeningDate;
        							vm.valueOpeningDate = convertDateFormat(firstDetails.valueDate);
        							vm.valueDate = vm.valueOpeningDate;
        							vm.customerMasterDetails.modeOfInterestPayment = firstDetails.modeOfInterestPayment;
        							vm.customerMasterDetails.operativeAccNumber = firstDetails.operativeAccNumber;
        							vm.bondType = firstDetails.bondType;
        							vm.brokerId = firstDetails.brokerAgencyCode;
        							vm.subscriptionAmount = firstDetails.subscriptionAmount;
        							vm.instrumentAmount = firstDetails.subscriptionAmount;
        							vm.instrumentNo = firstDetails.instrumentNo;
        							vm.transactionMode = firstDetails.transactionMode;
        							if (firstDetails.instrumentDate) {
        								vm.instruDate = convertDateFormat(firstDetails.instrumentDate);
        								vm.instrumentDate = vm.instruDate;
        							}
        						}
        						vm.blaFlag = result.blaFlag;
        						vm.periodOfDeposit = result.periodOfDeposit;
        						vm.rateOfInterest = result.rateOfInterest;
        						vm.schemeBondType = result.schemeBondType;
        						vm.BlaFlagNo = result.instrumentDetailsOut;
        						vm.cashinDet = result.customerMasterOut;
        						vm.guardDet = result.guardianDetailsOut;
        						vm.bondCashLimit = result.cashLimitForCash;
        						vm.bondClearingTransferLimit = result.cashLimitForClearingTransfer;
        						vm.depositMultiples = result.depositMultiples;
        						vm.minCashLimit = result.minCashLimit;
        						vm.aadharCapture = result.aadharCapture;
        						vm.aadharMandatory = result.aadharMandatory;
        						vm.aadharDisplay = result.aadharDisplay;
        						vm.interestPaidUpto1 = result.interestPaidUpto1.trim();
        						vm.interestPaidUpto2 = result.interestPaidUpto2.trim();

        						vm.blaNoOut = result.blaNoOut;
        						vm.maturityAmountScheme = result.maturityAmt;
        						if (vm.periodOfDeposit) {
        							getMaturityDate(vm.periodOfDeposit);
        						}
if (vm.blaNoOut) {
							if (vm.modeOfHolding == 'MH001' || vm.modeOfHolding == 'MH004' || vm.modeOfHolding == 'MH005') {
								if (vm.blaNoOut[0]) {
									vm.singleBondAccNo = vm.blaNoOut[0].blaNo;
								}
								if (vm.blaNoOut[0]) {
									vm.bondHolderName = vm.blaNoOut[0].nameOfTheBondHolder;
								}

							} else {
								if (vm.blaNoOut[0]) {
									vm.firstJointHolderBlaNo = vm.blaNoOut[0].blaNo;

									if (vm.blaNoOut[0]) {
										vm.firstJointHolderName = vm.blaNoOut[0].nameOfTheBondHolder;
									}
								}

								if (vm.blaNoOut[1]) {
									vm.secondJointHolderBlaNo = vm.blaNoOut[1].blaNo;

									if (vm.blaNoOut[1]) {
										vm.secondJointHolderName = vm.blaNoOut[1].nameOfTheBondHolder;
									}
								}

								if (vm.blaNoOut[2]) {
									vm.thirdJointHolderBlaNo = vm.blaNoOut[2].blaNo;
									if (vm.blaNoOut[2]) {
										vm.thirdJointHolderName = vm.blaNoOut[2].nameOfTheBondHolder;
									}
								}
							}
						}
        						if (vm.blaFlag == 'N') {
        							if (vm.bondHolderName) {
        								//                vm.customerMasterDetails.bondHldFirstName = vm.bondHolderName;
        								const nameComponents = splitName(vm.bondHolderName);
        								vm.customerMasterDetails.bondHldFirstName = nameComponents.firstName;

        								vm.customerMasterDetails.bondHldMidName = nameComponents.middleName;
        								vm.customerMasterDetails.bondHldLastName = nameComponents.lastName;
//        								vm.customerMasterDetails.bondHldDob = vm.fromDate1;
                                        vm.customerMasterDetails.bondHldDob = vm.fromDate1 instanceof Date
                                            ? vm.fromDate1
                                            : (typeof vm.fromDate1 === 'string' && /^\d{2}\/\d{2}\/\d{4}$/.test(vm.fromDate1)
                                                ? new Date(vm.fromDate1.split('/')[2], vm.fromDate1.split('/')[1] - 1, vm.fromDate1.split('/')[0])
                                                : new Date(vm.fromDate1));
        								vm.customerMasterDetails.panNo = vm.pan_No1;
        								vm.customerMasterDetails.isMinor = vm.isMinor(vm.fromDate1) ? "Y" : "N";
                                                          vm.customerMasterDetails.detailsOfBirthCertificate = vm.customerMasterDetails.isMinor == 'Y' ? 'YES' : vm.customerMasterDetails.detailsOfBirthCertificate
                                                          console.log(vm.customerMasterDetails.isMinor);
                                                          vm.customerMasterDetails.nameOfthePayee = nameComponents.firstName+' '+nameComponents.middleName+' '+nameComponents.lastName;

        								console.log('line 3190 ' , vm.customerMasterDetails.bondHldDob);
        							}
        							if (vm.secondJointHolderName) {
        								//                vm.customerMasterDetailsTwo.bondHldFirstName = vm.secondJointHolderName;
        								const nameComponents2 = splitName(vm.secondJointHolderName);
        								vm.customerMasterDetailsTwo.bondHldFirstName = nameComponents2.firstName;
        								vm.customerMasterDetailsTwo.bondHldMidName = nameComponents2.middleName;
        								vm.customerMasterDetailsTwo.bondHldLastName = nameComponents2.lastName;
        								vm.customerMasterDetailsTwo.bondHldDob = vm.fromDate2;
        								vm.customerMasterDetailsTwo.panNo = vm.pan_No2;
        								  vm.customerMasterDetailsTwo.isMinor = vm.customerMasterDetailsTwoFlag;
                                                          vm.customerMasterDetailsTwo.detailsOfBirthCertificate = vm.customerMasterDetailsTwoFlag == 'Y' ? 'YES' : vm.customerMasterDetailsTwo.detailsOfBirthCertificate

        							}
        							if (vm.thirdJointHolderName) {
        								//                vm.customerMasterDetailsThree.bondHldFirstName = vm.thirdJointHolderName;
        								const nameComponents3 = splitName(vm.thirdJointHolderName);
        								vm.customerMasterDetailsThree.bondHldFirstName = nameComponents3.firstName;
        								vm.customerMasterDetailsThree.bondHldMidName = nameComponents3.middleName;
        								vm.customerMasterDetailsThree.bondHldLastName = nameComponents3.lastName;
                                        vm.customerMasterDetailsThree.isMinor = vm.customerMasterDetailsThreeFlag;
                                        vm.customerMasterDetailsThree.detailsOfBirthCertificate = vm.customerMasterDetailsThreeFlag == 'Y' ? 'YES' : vm.customerMasterDetailsThree.detailsOfBirthCertificate

        								vm.customerMasterDetailsThree.bondHldDob = vm.fromDate3;
        								vm.customerMasterDetailsThree.panNo = vm.pan_No3;
        							}
        						}


        						if (vm.blaFlag === 'Y') {
        							Swal.fire({
        								title: "Warning!",
        								text: "Bond Ledger Account Number Already Exists",
        								type: "warning",
        								showCancelButton: true,
        								cancelButtonClass: "btn-danger",

        								confirmButtonText: "OK",
        								cancelButtonText: "CANCEL"
        							}, function(isConfirm) {
        								if (isConfirm) {


//                             Swal.fire({
//                               title: "Are you sure?",
//                               text: "Bond Ledger Account Number Already Exists?",
//                               icon: "warning", // use "icon", not "type"
//                               showCancelButton: true,
//                               confirmButtonText: "Yes",
//                               cancelButtonText: "Cancel",
//                               customClass: {
//                                 confirmButton: "btn-primary",
//                                 cancelButton: "btn-cancel"
//                               },
//                               buttonsStyling: false
//                             }).then((result) => {
//
//                                    if (result.isConfirmed) {










        									$timeout(function() {
        										var $tabLink = $('#subscriptionForm');
        										if ($tabLink.length) {
        											$tabLink.tab('show');
        										}
        									}, 100);
        								} else {
        									$state.go('rbi_bond_transfer_in.add');
        								}
        							});
        						}


        							vm.titleText =
        								(vm.noOfJointAcc == undefined || vm.noOfJointAcc == null || isNaN(vm.noOfJointAcc)) ? 'BOND HOLDER DETAILS' : 'FIRST JOINT BOND HOLDER DETAILS';

        							// Check if the properties exist before assigning them

        							if (vm.bondHolderName && vm.customerMasterDetails) {
        								const nameComponents = splitName(vm.bondHolderName);
        								vm.customerMasterDetails.bondHldFirstName = nameComponents.firstName;
        								vm.customerMasterDetails.bondHldMidName = nameComponents.middleName;
        								vm.customerMasterDetails.bondHldLastName = nameComponents.lastName;
        								vm.customerMasterDetails.bondHldDob =  vm.fromDate1;
//                                        vm.customerMasterDetails.bondHldDob = vm.fromDate1 instanceof Date
//                                            ? vm.fromDate1
//                                            : (typeof vm.fromDate1 === 'string' && /^\d{2}\/\d{2}\/\d{4}$/.test(vm.fromDate1)
//                                                ? new Date(vm.fromDate1.split('/')[2], vm.fromDate1.split('/')[1] - 1, vm.fromDate1.split('/')[0])
//                                                : new Date(vm.fromDate1));
        								console.log('line 3256 ' , vm.customerMasterDetails.bondHldDob);

        								vm.customerMasterDetails.panNo = vm.pan_No1;
        							}

        							if (vm.secondJointHolderName && vm.customerMasterDetailsTwo) {
        								const nameComponents2 = splitName(vm.secondJointHolderName);
        								vm.customerMasterDetailsTwo.bondHldFirstName = nameComponents2.firstName;
        								vm.customerMasterDetailsTwo.bondHldMidName = nameComponents2.middleName;
        								vm.customerMasterDetailsTwo.bondHldLastName = nameComponents2.lastName;
        								vm.customerMasterDetailsTwo.bondHldDob = vm.fromDate2;
        								vm.customerMasterDetailsTwo.panNo = vm.pan_No2;
        							}

        							if (vm.thirdJointHolderName && vm.customerMasterDetailsThree) {
        								const nameComponents3 = splitName(vm.thirdJointHolderName);
        								vm.customerMasterDetailsThree.bondHldFirstName = nameComponents3.firstName;
        								vm.customerMasterDetailsThree.bondHldMidName = nameComponents3.middleName;
        								vm.customerMasterDetailsThree.bondHldLastName = nameComponents3.lastName;
        								vm.customerMasterDetailsThree.bondHldDob = vm.fromDate3;
        								vm.customerMasterDetailsThree.panNo = vm.pan_No3;
        							}

		if (Array.isArray(vm.cashinDet)) {



							let orderedDetails = [];

							for (let i = 0; i < vm.cashinDet.length; i++) {
								const detail = vm.cashinDet[i];

								if (detail.jointHolderType == 1 || detail.jointHolderType == 'S') {
									orderedDetails[0] = detail; // First joint holder
								} else if (detail.jointHolderType == 2) {
									orderedDetails[1] = detail; // Second joint holder
								} else if (detail.jointHolderType == 3) {
									orderedDetails[2] = detail; // Third joint holder
								}
							}

							vm.titleText = vm.cashinDet.length === 1 &&
								(vm.noOfJointAcc === undefined || vm.noOfJointAcc === null || isNaN(vm.noOfJointAcc)) ?
								'BOND HOLDER DETAILS' : 'FIRST JOINT BOND HOLDER DETAILS';

							// Bind customer details based on orderedDetails
							if (orderedDetails[0]) {
								vm.customerMasterDetails = orderedDetails[0]; // Bind first detail if it exists
							}

							if (vm.noOfJointAcc >= 2 && orderedDetails[1]) {
								vm.customerMasterDetailsTwo = orderedDetails[1]; // Bind second detail if it exists
							}

							if (vm.noOfJointAcc === 3 && orderedDetails[2]) {
								vm.customerMasterDetailsThree = orderedDetails[2]; // Bind third detail if it exists
							}
                            if (vm.customerMasterDetails) {
                            								vm.dateOfBirth = convertDateFormat(vm.customerMasterDetails.bondHldDob);
                            								vm.customerMasterDetails.bondHldDob = vm.dateOfBirth;
                            								if (vm.customerMasterDetails.isMinor) {
                            									vm.customerMasterDetailsFlag = vm.customerMasterDetails.isMinor
                            								}
                            								//                        vm.customerMasterDetailsTwo.bondHldDob = $filter('date')(vm.customerMasterDetailsTwo.bondHldDob, "dd-MM-yyyy");
                            							}
							if (vm.customerMasterDetailsTwo) {
								vm.dateOfBirth = convertDateFormat(vm.customerMasterDetailsTwo.bondHldDob);
								vm.customerMasterDetailsTwo.bondHldDob = vm.dateOfBirth;
								if (vm.customerMasterDetailsTwo.isMinor) {
									vm.customerMasterDetailsTwoFlag = vm.customerMasterDetailsTwo.isMinor
								}
								//                        vm.customerMasterDetailsTwo.bondHldDob = $filter('date')(vm.customerMasterDetailsTwo.bondHldDob, "dd-MM-yyyy");
							}
							if (vm.customerMasterDetailsThree) {

								vm.dateOfBirth = convertDateFormat(vm.customerMasterDetailsThree.bondHldDob);
								vm.customerMasterDetailsThree.bondHldDob = vm.dateOfBirth;
								if (vm.customerMasterDetailsThree.isMinor) {
									vm.customerMasterDetailsThree = vm.customerMasterDetailsThree.isMinor
								}


							}



						}
        						if (Array.isArray(vm.guardDet)) {


        							let orderedDetails1 = [];

        							for (let i = 0; i < vm.guardDet.length; i++) {
        								const detail = vm.guardDet[i];

        								if (detail.jointHolderType == 1 || detail.jointHolderType == 'S') {
        									orderedDetails1[0] = detail; // First joint holder
        								} else if (detail.jointHolderType == 2) {
        									orderedDetails1[1] = detail; // Second joint holder
        								} else if (detail.jointHolderType == 3) {
        									orderedDetails1[2] = detail; // Third joint holder
        								}
        							}


        							// Bind customer details based on orderedDetails
        							if (orderedDetails1[0]) {
        								vm.guardianDetailsValueModel = orderedDetails1[0]; // Bind first detail if it exists
        							}

        							if (vm.noOfJointAcc >= 2 && orderedDetails1[1]) {
        								vm.guardianDetailsValueModel2 = orderedDetails1[1]; // Bind second detail if it exists
        							}

        							if (vm.noOfJointAcc === 3 && orderedDetails1[2]) {
        								vm.guardianDetailsValueModel3 = orderedDetails1[2]; // Bind third detail if it exists
        							}



        							if (vm.guardDet.length > 0) {
        								if (vm.guardianDetailsValueModel) {
        									vm.customerMasterDetails.guardianId = vm.guardianDetailsValueModel.guardianId;
        									vm.customerMasterDetails.guardianFirstName = vm.guardianDetailsValueModel.guardianFirstName;
        									vm.customerMasterDetails.guardianMiddleName = vm.guardianDetailsValueModel.guardianMiddleName;
        									vm.customerMasterDetails.guardianLastName = vm.guardianDetailsValueModel.guardianLastName;
        									vm.customerMasterDetails.relationWithMinor = vm.guardianDetailsValueModel.relationWithMinor;
        									if (vm.guardianDetailsValueModel.guardianDateOfBirth) {
        										vm.guardianDateOfBirth = convertDateFormat(vm.guardianDetailsValueModel.guardianDateOfBirth);
        										vm.customerMasterDetails.guardianDateOfBirth = vm.guardianDateOfBirth;

        									}
        									vm.customerMasterDetails.guardianCommunicationAddress = vm.guardianDetailsValueModel.guardianCommunicationAddress;
        									vm.customerMasterDetails.guardianMobileNo = vm.guardianDetailsValueModel.mobileNo;
        									vm.customerMasterDetails.guardianEmailId = vm.guardianDetailsValueModel.guardianEmailId;
        								}
        								if (vm.guardianDetailsValueModel2) {
        									vm.customerMasterDetailsTwo.guardianId = vm.guardianDetailsValueModel2.guardianId;
        									vm.customerMasterDetailsTwo.guardianFirstName = vm.guardianDetailsValueModel2.guardianFirstName;
        									vm.customerMasterDetailsTwo.guardianMiddleName = vm.guardianDetailsValueModel2.guardianMiddleName;
        									vm.customerMasterDetailsTwo.guardianLastName = vm.guardianDetailsValueModel2.guardianLastName;
        									vm.customerMasterDetailsTwo.relationWithMinor = vm.guardianDetailsValueModel2.relationWithMinor;
        									if (vm.guardianDetailsValueModel2.guardianDateOfBirth) {
        										vm.guardianDateOfBirth = convertDateFormat(vm.guardianDetailsValueModel2.guardianDateOfBirth);
        										vm.customerMasterDetailsTwo.guardianDateOfBirth = vm.guardianDateOfBirth;

        									}
        									vm.customerMasterDetailsTwo.guardianCommunicationAddress = vm.guardianDetailsValueModel2.guardianCommunicationAddress;
        									vm.customerMasterDetailsTwo.guardianMobileNo = vm.guardianDetailsValueModel2.mobileNo;
        									vm.customerMasterDetailsTwo.guardianEmailId = vm.guardianDetailsValueModel2.guardianEmailId;
        								}

        								if (vm.guardianDetailsValueModel3) {
        									vm.customerMasterDetailsThree.guardianId = vm.guardianDetailsValueModel3.guardianId;
        									vm.customerMasterDetailsThree.guardianFirstName = vm.guardianDetailsValueModel3.guardianFirstName;
        									vm.customerMasterDetailsThree.guardianMiddleName = vm.guardianDetailsValueModel3.guardianMiddleName;
        									vm.customerMasterDetailsThree.guardianLastName = vm.guardianDetailsValueModel3.guardianLastName;
        									vm.customerMasterDetailsThree.relationWithMinor = vm.guardianDetailsValueModel3.relationWithMinor;
        									if (vm.guardianDetailsValueModel3.guardianDateOfBirth) {
        										vm.guardianDateOfBirth = convertDateFormat(vm.guardianDetailsValueModel3.guardianDateOfBirth);
        										vm.customerMasterDetailsThree.guardianDateOfBirth = vm.guardianDateOfBirth;

        									}
        									vm.customerMasterDetailsThree.guardianCommunicationAddress = vm.guardianDetailsValueModel3.guardianCommunicationAddress;
        									vm.customerMasterDetailsThree.guardianMobileNo = vm.guardianDetailsValueModel3.mobileNo;
        									vm.customerMasterDetailsThree.guardianEmailId = vm.guardianDetailsValueModel3.guardianEmailId;
        								}
        							}


        						}
        						//                vm.solDate = new Date(); // Set this to your actual system date
        						vm.accountOpeningDate = vm.systemDate;
        						//             vm.accountOpeningDate =  $filter('date')(vm.systemDate, 'dd/MM/yyyy');
        					} else {
        						console.error("Response structure is not as expected:", response);
        					}
        				}).catch(function(error) {
        					 const errorMessage = error && error.data && error.data.message
                                                  ? error.data.message
                                                  : "An unexpected error occurred.";
                                          if(null!=errorMessage && errorMessage.startsWith("Invalid")){
                                          Swal.fire("Warning!", errorMessage , "warning");
                                          $state.go("rbi_bond_transfer_in.add");
                                          }else{
                                          handleErrorResponse(error);
                                          $state.go("rbi_bond_transfer_in.add");
                                          }
        				});
        			}
        		}
        		vm.convertToDate = convertToDate;
        		function convertToDate(dateStr) {
                    var parts = dateStr.split('/'); // Split "dd/MM/yyyy"
                    return new Date(parts[2], parts[1] - 1, parts[0]); // (YYYY, MM-1, DD)
                }
        		function getMaturityDate(accountOpeningDate, periodOfDeposit) {
                	if (vm.periodOfDeposit !== null) {

                                                                				var numberOfMonths = vm.periodOfDeposit;

                                                								if(vm.accountOpeningDate){
                                                                				var maturityDate = new Date(vm.accountOpeningDate);
                                                								} else{
                                                								var sol = convertToDate(vm.solDate1);
                                                										var maturityDate = sol;
                                                								}

                                                                				//            maturityDate.setMonth(maturityDate.getMonth() + numberOfMonths);
                                                                				maturityDate.setMonth(parseInt(maturityDate.getMonth()) + parseInt(numberOfMonths));
                                                                				vm.maturityDate = $filter('date')(maturityDate, "dd/MM/yyyy");


                                                                			}
                                                                			};
function getWboBranchCode() {
			vm.loader = true;

			var requestData = {};
			bondTransferInService.getWboBranchDetails(requestData).then(function(response) {
				vm.loader = false;
				vm.getWboBranchDetails = response.data.result;
				angular.element('#branchDetailsModal').modal('toggle');
			}, function(error) {
				handleErrorResponse(error);
				vm.loader = false;
			});
		}
vm.textableDatePickerYear=textableDatePickerYear;


function textableDatePickerYear(id,valueDate){
 commonService.textableDatePicker(id,valueDate);
 // Pattern validation after commonService
     var dateValue = document.getElementById(id).value;
     if (dateValue) {
         var datePattern = /^(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[0-2])\/(17|18|19|20|21)\d\d$/;
         if (!datePattern.test(dateValue)) {
             if (id === 'fromDate1') {
             vm.fromDate1 = null;
             }else if(id === 'dateofBirth1'){
             vm.fromDate1 = null;
             }else if(id === 'fromDate2'){
             vm.fromDate2 = null;
             }else if(id === 'fromDate3'){
             vm.fromDate3 = null;
             }
             document.getElementById(id).value = '';
             Swal.fire("Error!", "Please enter a valid date in DD/MM/YYYY format.", "error");
             return false;
         }
     }
     return true;
}
		    function dateOfBirthOptionsFunc() {
              vm.curtYear = vm.currentDate.getUTCFullYear();
              vm.minimumYear = vm.curtYear - 18;
              vm.minimumDate = vm.currentDate.getMonth() + 1 + "/" + vm.currentDate.getDate() + "/" + vm.minimumYear;
              vm.minimumDate = new Date(vm.minimumDate);
              vm.dateOfBirthOptions = {
                minDate: vm.minimumDate,
                maxDate: vm.systemDate,
              };
            }

            function dateOfBirthOptionsFuncMajor() {
              vm.curtYear = vm.currentDate.getUTCFullYear();

              vm.minimumYear = vm.curtYear - 18;

              vm.minimumDate = new Date(vm.currentDate);
              vm.minimumDate.setFullYear(vm.minimumYear);

              vm.dateOfBirthOptions = {
                minDate: null,
                maxDate: vm.minimumDate,
              };
            }
vm.calculateAgeMajorToMinor=calculateAgeMajorToMinor;
function calculateAgeMajorToMinor(dobStr) {
    if (!dobStr) return null;

    var parts = dobStr.split("/"); // dd/MM/yyyy
    var dob = new Date(parts[2], parts[1] - 1, parts[0]);
    var today = new Date();

    // Base age calculation
    var age = today.getFullYear() - dob.getFullYear();

    var birthdayThisYear = new Date(
        today.getFullYear(),
        dob.getMonth(),
        dob.getDate()
    );

    // If birthday not yet occurred this year
    if (today < birthdayThisYear) {
        age--;
    }
    /* ---------- 18 AGE THRESHOLD ---------- */
    var eighteenthBirthday = new Date(
        dob.getFullYear() + 18,
        dob.getMonth(),
        dob.getDate()
    );
    if (today >= eighteenthBirthday && age < 18) {
        age = 18;
    }
    return age;
}
             function checkAge(isMinor, dateOfBirth, mode, list) {
                   if (typeof dateOfBirth != "undefined") {
                     /*var dob = new Date(dateOfBirth);
                     var month_diff = Date.now() - dob.getTime();
                     var age_dt = new Date(month_diff);
                     var year = age_dt.getUTCFullYear();

                     var age = Math.abs(year - 1970);*/
                     var age=calculateAgeMajorToMinor($filter("date")(dateOfBirth, "dd/MM/yyyy"));
                     if (age >= 18 && isMinor == "Y") {
                       Swal.fire("Not a Major!", "Please enter the correct DOB", "info");
                       if (mode == "add") {
                         if (list == "1") {
                           vm.customerMasterDetails.bondHldDob = "";
                         }
                         if (list == "2") {
                           vm.customerMasterDetailsTwo.bondHldDob = "";
                         }
                         if (list == "3") {
                           vm.customerMasterDetailsThree.bondHldDob = "";
                         }
                       }
                       if (mode == "modify") {
                         if (list == "1") {
                           vm.customerMasterDetails.C009.oldValue = "";
                         }
                         if (list == "2") {
                           vm.customerMasterDetailsTwo.C009.oldValue = "";
                         }
                         if (list == "3") {
                           vm.customerMasterDetailsThree.C009.oldValue = "";
                         }
                       }
                     } else if (age < 18 && isMinor == "N") {
                       Swal.fire("Not a Minor!", "Please enter the correct DOB", "info");
                       if (mode == "add") {
                         if (list == "1") {
                           vm.customerMasterDetails.bondHldDob = "";
                         }
                         if (list == "2") {
                           vm.customerMasterDetailsTwo.bondHldDob = "";
                         }
                         if (list == "3") {
                           vm.customerMasterDetailsThree.bondHldDob = "";
                         }
                       }
                       if (mode == "modify") {
                         if (list == "1") {
                           vm.customerMasterDetails.C009.oldValue = "";
                         }
                         if (list == "2") {
                           vm.customerMasterDetailsTwo.C009.oldValue = "";
                         }
                         if (list == "3") {
                           vm.customerMasterDetailsThree.C009.oldValue = "";
                         }
                       }
                     }
                   }
                 }

            function calculateAge(birthday) {
      // birthday is a date
      var ageDifMs = Date.now() - birthday.getTime();
      var ageDate = new Date(ageDifMs); // miliseconds from epoch
      var age = Math.abs(ageDate.getUTCFullYear() - 1970);

      if (age >= 18) {
        var msg = "Not a minor";
        Swal.fire("Warning!", msg, "warning");
        getAccountPage();
      }
    }

			function getGuardianDetailsMetaData() {
        			var requestData = {
        				'referenceId': 'RELATION_WITH_MINOR'
        			}
        			bondTransferInService.getReferenceData(requestData).then(function(response) {
        				vm.guardianDetailsListMetaData = response.data.result;
        			}, function(error) {
        				handleErrorResponse(error);
        			});
        		}
        		function getNomineeStatusMetaData() {
                      var requestData = {
                        referenceId: "NOMINEE_STATUS",
                      };
                      bondTransferInService.getReferenceData(requestData).then(
                        function (response) {
                          vm.getNomineeStatusMetaData = response.data.result;
                        },
                        function (error) {
                          handleErrorResponse(error);
                        }
                      );
                    }
                    vm.getNomineeStatusDescription = function(code) {
                           var description = code;
                           angular.forEach(vm.getNomineeStatusMetaData, function(item) {
                               if (item.SUBCODE === code) {
                                   description = item.DESCRIPTION;
                               }
                           });
                           return description;
                       };
function splitName(fullName) {

			fullName = fullName.trim();

			// Split the name by spaces
			let nameParts = fullName.split(/\s+/); // Split by one or more spaces

			let firstName = "";
			let middleName = "";
			let lastName = "";

			if (nameParts.length === 1) {
				firstName = nameParts[0];
			} else if (nameParts.length === 2) {
				firstName = nameParts[0];
				lastName = nameParts[1];
			} else if (nameParts.length >= 3) {
				// If there are three or more parts
				firstName = nameParts[0];
				lastName = nameParts[nameParts.length - 1];
				middleName = nameParts.slice(1, nameParts.length - 1).join(" "); // Join the middle parts
			}

			return {
				firstName,
				middleName,
				lastName
			};
		}
   function getTranCode(tranCode){
    commonService.getTranCode(tranCode,function(response){
                        if(response.success) {
                         // angular.element('#tranCodeModal').modal('toggle');
                            vm.tranCodeList = response.data;
                            vm.loader = false;
                        } else {
                            Swal.fire("Error!", response.message, "error");
                            vm.loader = false;
                        }
                    });

}

  function convertLabelValue(list){
                var resultList = [];
                    for(var item in list){
                        list[item].value = list[item].SUBCODE;
                        list[item].label = list[item].DESCRIPTION;
                        let sam = {};
                        sam.label = list[item].label;
                        sam.value = list[item].value;
                        resultList.push(sam);
                    }
//                    return list;
                    return resultList;
                }

 function calculateSum() {
      vm.total = 0;
     for (var i = 0; i < vm.nominationDetails.length; i++) {
       var share = parseFloat(vm.nominationDetails[i].sharePercentage || "0");
        if (vm.total + share <= 100) {
         vm.total += share;
       } else {
         vm.nominationDetails[i].sharePercentage = "";
         Swal.fire("Warning!", "Total share percentage cannot exceed 100%. Please adjust your inputs. Current total: " + vm.total + "%", "warning");
       }
       }
    }

                	function addcustomerMaster() {
                			var proceed = false;
                			var message = '';
                			if (vm.isNominee === 'Y') {
                				if (vm.total !== 100) {
                					proceed = false;
                					message = 'Sum of sharing percentage should be equal to 100';
                					Swal.fire({
                						title: "Warning",
                						text: message,
                						type: "warning",
                						confirmButtonText: "OK",
                						confirmButtonClass: "btn-primary"
                					});
                					return;
                				} else {
                					proceed = true;
                				}
                			} else {
                				proceed = true;
                			}


                			// If validation passes, show confirmation dialog
                			if (proceed) {
//                				Swal.fire({
//                						title: "Are you sure?",
//                						text: "Do you really want to add this record?",
//                						type: "warning",
//                						showCancelButton: true,
//                						cancelButtonClass: "btn-cancel",
//                						confirmButtonClass: "btn-primary",
//                						confirmButtonText: "Yes",
//                						closeOnConfirm: false
//                					},
//                					function() {



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
                						vm.loader = true;

                						vm.resultArray = [];
                						vm.resultArray2 = [];

                						vm.customerMasterDetails.bondHldDob = $filter('date')(vm.customerMasterDetails.bondHldDob, 'dd-MMM-yyyy');
                						vm.customerMasterDetails.guardianDateOfBirth1 = $filter('date')(vm.customerMasterDetails.guardianDateOfBirth1, 'dd-MMM-yyyy');

                						try {
                							if (vm.customerMasterDetails) {
                								vm.customerMasterDetails.birthCertificate = vm.customerMasterDetails.birthCertificate ? 'Y' : 'N';
                								vm.customerMasterDetails.passport = vm.customerMasterDetails.passport ? 'Y' : 'N';
                								vm.customerMasterDetails.seniorCitizenCard = vm.customerMasterDetails.seniorCitizenCard ? 'Y' : 'N';
                								vm.customerMasterDetails.voterIdCard = vm.customerMasterDetails.voterIdCard ? 'Y' : 'N';
                								vm.customerMasterDetails.panCard = vm.customerMasterDetails.panCard ? 'Y' : 'N';
                								vm.customerMasterDetails.drivingLicence = vm.customerMasterDetails.drivingLicence ? 'Y' : 'N';
                								vm.customerMasterDetails.rationCard = vm.customerMasterDetails.rationCard ? 'Y' : 'N';
                								vm.customerMasterDetails.otherDocuments = vm.customerMasterDetails.otherDocuments ? 'Y' : 'N';
                							}
                						} catch (e) {}

                						try {
                							if (vm.customerMasterDetailsTwo) {
                								vm.customerMasterDetailsTwo.birthCertificate = vm.customerMasterDetailsTwo.birthCertificate ? 'Y' : 'N';
                								vm.customerMasterDetailsTwo.passport = vm.customerMasterDetailsTwo.passport ? 'Y' : 'N';
                								vm.customerMasterDetailsTwo.seniorCitizenCard = vm.customerMasterDetailsTwo.seniorCitizenCard ? 'Y' : 'N';
                								vm.customerMasterDetailsTwo.voterIdCard = vm.customerMasterDetailsTwo.voterIdCard ? 'Y' : 'N';
                								vm.customerMasterDetailsTwo.panCard = vm.customerMasterDetailsTwo.panCard ? 'Y' : 'N';
                								vm.customerMasterDetailsTwo.drivingLicence = vm.customerMasterDetailsTwo.drivingLicence ? 'Y' : 'N';
                								vm.customerMasterDetailsTwo.rationCard = vm.customerMasterDetailsTwo.rationCard ? 'Y' : 'N';
                								vm.customerMasterDetailsTwo.otherDocuments = vm.customerMasterDetailsTwo.otherDocuments ? 'Y' : 'N';
                							}
                						} catch (e) {}

                						try {
                							if (vm.customerMasterDetailsThree) {
                								vm.customerMasterDetailsThree.birthCertificate = vm.customerMasterDetailsThree.birthCertificate ? 'Y' : 'N';
                								vm.customerMasterDetailsThree.passport = vm.customerMasterDetailsThree.passport ? 'Y' : 'N';
                								vm.customerMasterDetailsThree.seniorCitizenCard = vm.customerMasterDetailsThree.seniorCitizenCard ? 'Y' : 'N';
                								vm.customerMasterDetailsThree.voterIdCard = vm.customerMasterDetailsThree.voterIdCard ? 'Y' : 'N';
                								vm.customerMasterDetailsThree.panCard = vm.customerMasterDetailsThree.panCard ? 'Y' : 'N';
                								vm.customerMasterDetailsThree.drivingLicence = vm.customerMasterDetailsThree.drivingLicence ? 'Y' : 'N';
                								vm.customerMasterDetailsThree.rationCard = vm.customerMasterDetailsThree.rationCard ? 'Y' : 'N';
                								vm.customerMasterDetailsThree.otherDocuments = vm.customerMasterDetailsThree.otherDocuments ? 'Y' : 'N';
                							}
                						} catch (e) {}

                						try {
                							vm.customerMasterDetailsTwo.bondHldDob = $filter('date')(vm.customerMasterDetailsTwo.bondHldDob, 'dd-MMM-yyyy');
                							vm.customerMasterDetailsTwo.isMinor = vm.customerMasterDetailsTwoFlag;
                						} catch (err) {}
                						try {
                							vm.customerMasterDetailsTwo.dateSubmission15g15htax = $filter('date')(vm.customerMasterDetailsTwo.dateSubmission15g15htax, 'dd-MMM-yyyy');
                						} catch (err) {}
                						try {
                							vm.customerMasterDetailsTwo.guardianDateOfBirth = $filter('date')(vm.customerMasterDetailsTwo.guardianDateOfBirth, 'dd-MMM-yyyy');
                						} catch (err) {}

                						try {
                							vm.customerMasterDetailsThree.bondHldDob = $filter('date')(vm.customerMasterDetailsThree.bondHldDob, 'dd-MMM-yyyy');
                							vm.customerMasterDetailsThree.isMinor = vm.customerMasterDetailsThreeFlag;
                						} catch (err) {}
                						try {
                							vm.customerMasterDetailsThree.dateSubmission15g15htax = $filter('date')(vm.customerMasterDetailsThree.dateSubmission15g15htax, 'dd-MMM-yyyy');
                						} catch (err) {}
                						try {
                							vm.customerMasterDetailsThree.guardianDateOfBirth = $filter('date')(vm.customerMasterDetailsThree.guardianDateOfBirth, 'dd-MMM-yyyy');
                						} catch (err) {}

                						var objOne = Object.fromEntries(Object.entries(vm.customerMasterDetails));
                						objOne.jointHolderType = 'S';
                						if (vm.customerMasterDetailsTwo) {
                							var objTwo = Object.fromEntries(Object.entries(vm.customerMasterDetailsTwo));
                							objOne.jointHolderType = '1';
                							objTwo.jointHolderType = '2';
                							vm.resultArray.push(objTwo);
                						}
                						if (vm.customerMasterDetailsThree) {
                							var objThree = Object.fromEntries(Object.entries(vm.customerMasterDetailsThree));
                							objThree.jointHolderType = '3';
                							vm.resultArray.push(objThree);
                						}


                						//                         angular.forEach(vm.coparcenerslist, function(item) {
                						//                             // Check if coparcenersDob exists and if it's valid before applying the date filter
                						//                             if (item.coparcenersDob) {
                						//                                 item.coparcenersDob = $filter('date')(item.coparcenersDob, 'dd-MMM-yyyy');
                						//                             }
                						//                             // Push the item to resultArray2
                						//                             vm.resultArray2.push(item);
                						//                         });
                						angular.forEach(vm.coparcenerslist, function(item) {
                							// Remove $$hashKey from each item
                							delete item.$$hashKey;

                							// Check if coparcenersDob exists and if it's valid before applying the date filter
                							if (item.coparcenersDob) {
                								item.coparcenersDob = $filter('date')(item.coparcenersDob, 'dd-MMM-yyyy');
                							}

                							// Push the item to resultArray2
                							vm.resultArray2.push(item);
                						});

                						// If coparcenerslist is empty, push an empty object into resultArray2
                						if (vm.resultArray2.length === 0) {
                							//                             vm.resultArray2.push({});
                						}







                						vm.resultArray.push(objOne);

                						if (vm.modeOfHolding == 'MH002' || vm.modeOfHolding == 'MH003') {
                							vm.resultArray = orderArray(vm.resultArray);
                						}


                						var requestData = {
                							'dataList': vm.resultArray,
                							'dataCopersondetailsList': vm.resultArray2,
                							'schemeCode': vm.schemeCode,
                							'modeOfHolding': vm.modeOfHolding,
                							'blaFlag': vm.blaFlag,
                							'singleBlaNo': vm.singleBondAccNo,
                							'firstJointBondHolderBLA': vm.firstJointHolderBlaNo,
                							'firstJointBondHolderName': vm.firstJointHolderName,
                							'secondJointBondHolderBLA': vm.secondJointHolderBlaNo,
                							'secondJointBondHolderName': vm.secondJointHolderName,
                							'thirdJointBondHolderBLA': vm.thirdJointHolderBlaNo,
                							'thirdJointBondHolderName': vm.thirdJointHolderName,
                                            'oldBla':vm.oldBla,
                                            'accTransferedInDate':vm.branchDate,
                                            'transfererBranchName':vm.transfererBranchName,
                                            'transfererBankName':vm.transfererBankName,
                                            'rbiCode':vm.rbiCode,

                                            'interestPaidUpto': vm.intrstPaidMonthUpto.trim() + "-" + vm.intrstPaidYearUpto,



//                							'applicationNo': vm.applicationNo,
//                							'brokerAgencyCode': vm.brokerId,
                							'subsAmt': vm.subscriptionAmount,
                							'bondType': vm.bondType,
                							'accOpenedDate': $filter('date')(vm.accountOpeningDate, 'dd-MMM-yyyy'),
                							'valueDate': $filter('date')(vm.accountOpeningDate, 'dd-MMM-yyyy'),
                							'periodOfDeposit': vm.periodOfDeposit,
                							'maturityDate': $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.maturityDate)),
                							'rateOfInterest': vm.rateOfInterest,
                							'modeOfInterestPayment': vm.modeOfInterestPayment,
                							'tranMode': vm.isNominee,
                							'instAmount': vm.instrumentAmount,
                							'instNum': vm.instrumentNo,
//                							'instrumentType': vm.instrumentType,
                							'instDate': $filter('date')(vm.instrumentDate, 'dd-MMM-yyyy'),
                							'tranCode': vm.tranCode,
                							'zoneCode': vm.zoneCode,
                							'zoneDate': $filter('date')(vm.zoneDate, 'dd-MMM-yyyy'),
                							'sortCode': vm.sortCode,
                							'nonMicrBankCode': vm.bankCodeNonMicr,
                							'nonMicrBranchCode': vm.branchCodeNonMicr,
//                							'debitAccNo': vm.debitAccNo,
                							'remarks': vm.remarks,
                							'isNominee': vm.isNominee,
                						    'wboBranchCode': vm.wboBranchCode,
                                           'wboBranchName': vm.wboBranchName,
//                                           'segment': vm.segment,
                                           'wordsInRupees': vm.wordsInRupees,
//                							'totalSharingPercent': vm.total,
//                							'applicationDate': $rootScope.formatDateToDDMMMYYYY(convertToDateObject(vm.applicationDate))




                						};
                						requestData.noOfJointHolder = vm.noOfJointAcc;
                						if (vm.isNominee == 'N') {
                							requestData.nomineeDetailsList = null;
                						} else {
                							// Process nomination details
                							angular.forEach(vm.nominationDetails, function(item) {
                								// Remove $$hashKey from each nominee object
                								delete item.$$hashKey;
                								delete item.dateOfBirthOptions;

                								// Format date of birth of minor
                								item.dateOfBirthOfMinor = $filter('date')(item.dateOfBirthOfMinor, "dd-MMM-yyyy");
                							});
                							requestData.nomineeDetailsList = vm.nominationDetails;
                						}

                						bondTransferInService.bondTransferInAdd(requestData).then(
                							(response) => {
                								vm.loader = false;
                								vm.responseResult = response.data.result;
                								var message = 'Transfer-In Created Successfully';
                								if ((vm.modeOfHolding == "MH001" || vm.modeOfHolding == "MH004" || vm.modeOfHolding == "MH005") && vm.blaFlag == "Y") {
                                                  message = message + "\nBLA No: " + vm.blaNoOut[0].blaNo;
                                                  if (vm.responseResult.singleGuardianId) {
                                                    message = message + "\nGuardian ID: " + vm.responseResult.singleGuardianId;
                                                  }
                                                  if (vm.responseResult.subscriptionId) {
                                                    message = message + "\nSubscription ID: " + vm.responseResult.subscriptionId;
                                                  }
                                                  if (vm.responseResult.nomineeIdList) {
                                                    message = message + "\nNominee IDs: " + vm.responseResult.nomineeIdList;
                                                  }
                                                } else if ((vm.modeOfHolding == "MH001" || vm.modeOfHolding == "MH004" || vm.modeOfHolding == "MH005") && vm.blaFlag == "N") {
                                                  message = message + "\nBLA No: " + vm.responseResult.singleBlaNo;
                                                  if (vm.responseResult.singleGuardianId) {
                                                    message = message + "\nGuardian ID: " + vm.responseResult.singleGuardianId;
                                                  }
                                                  if (vm.responseResult.subscriptionId) {
                                                    message = message + "\nSubscription ID: " + vm.responseResult.subscriptionId;
                                                  }
                                                  if (vm.responseResult.nomineeIdList) {
                                                   message = message + "\nNominee IDs: " + vm.responseResult.nomineeIdList;
                                                   }
                                                } else {
                                                  if (vm.responseResult.firstJointBlaNo) {
                                                    message = message + "\n BLA No: " + vm.responseResult.firstJointBlaNo;
                                                    if (vm.responseResult.firstGuardianId) {
                                                      message = message + "\n Guardian ID: " + vm.responseResult.firstGuardianId;
                                                    }
                                                  }

                                                  if (vm.responseResult.secondJointBlaNo) {
                                                   // message = message + "\nSecond Joint BLA No: " + vm.responseResult.secondJointBlaNo;
                                                    if (vm.responseResult.secondGuardianId) {
                                                      message = message + "\nSecond Joint Guardian ID: " + vm.responseResult.secondGuardianId;
                                                    }
                                                  }

                                                  if (vm.responseResult.thirdJointBlaNo) {
                                                  //  message = message + "\n Joint BLA No: " + vm.responseResult.thirdJointBlaNo;
                                                    if (vm.responseResult.secondGuardianId) {
                                                      message = message + "\nThird Joint Guardian ID: " + vm.responseResult.thirdGuardianId;
                                                    }
                                                  }
                                                  if (vm.responseResult.subscriptionId) {
                                                    message = message + "\nSubscription ID: " + vm.responseResult.subscriptionId;
                                                    console.log('vm.responseResult',vm.responseResult)
                                                  }
                                                  if(vm.responseResult.nomineeIdList) {
                                                    message= message + "\nNominee IDs: " + vm.responseResult.nomineeIdList;
                                                  }
                                                }
                                              //  Swal.fire("Success!", message, "success");
                                                Swal.fire("Success!", message.replace(/\n/g, "<br>"), "success");
                								$state.go('rbi_bond_transfer_in.inquiry');
                							},
                							function(error) {
                								vm.loader = false;
                								 const errorMessage = error && error.data && error.data.message
                                                                                                  ? error.data.message
                                                                                                  : "An unexpected error occurred.";
                                                                                          if(null!=errorMessage && errorMessage.startsWith("Invalid")){
                                                                                          Swal.fire("Warning!", errorMessage , "warning");
                                                                                          $state.go("rbi_bond_transfer_in.inquiry");
                                                                                          }else{
                                                                                          handleErrorResponse(error);
                                                                                          $state.go("rbi_bond_transfer_in.inquiry");
                                                                                          }
                							});

                				}	});
                			}
                		}
function getModeofHolding() {
           var requestData = {
                 'referenceId': 'MODE_OF_HOLDING'
             }
           bondTransferInService.getReferenceData(requestData).then(function(response){
              vm.modeOfHoldingList = response.data.result;
            },function(error) {
               handleErrorResponse(error);
             });
        }

        	function orderArray(list) {
        			//this function will come joint holder greater than or equal to 2
        			vm.tempArray = [];
        			vm.tempArray.push('0');
        			vm.tempArray.push('1');
        			if (list.length > 2) {
        				vm.tempArray.push('2');
        			}
        			angular.forEach(list, function(item) {

        				if (item.jointHolderType == '1') {
        					vm.tempArray.splice(0, '0', item);
        				}
        				if (item.jointHolderType == '2') {
        					vm.tempArray.splice(1, '1', item);
        				}
        				if (item.jointHolderType == '3') {
        					vm.tempArray.splice(2, '2', item);
        				}

        			});

        			var value = vm.tempArray.filter((value) => {
        				if (value == '0') {

        				} else
        				if (value == '1') {

        				} else
        				if (value == '2') {

        				} else {
        					return value;
        				}
        			});

        			return value;

        		}
function getOtherBankDetailsTorchM(flag) {
              vm.loader = true;
              vm.searchSolId = "";
              var requestData = {};
              if(flag == 'bankName'){

              requestData.nonBankName = (vm.customerMasterDetails.C050) ? vm.customerMasterDetails.C050.oldValue : '';

              }
              if(flag == 'ifsc'){

              requestData.bankCode = (vm.customerMasterDetails.C050)?vm.customerMasterDetails.C050.oldValue:'';
              requestData.instIfscCode = (vm.customerMasterDetails.C031)?vm.customerMasterDetails.C031.oldValue:'';

              }
              if(flag == 'micrCodeBank'){

              requestData.bankCode = (vm.customerMasterDetails.C050)?vm.customerMasterDetails.C050.oldValue:'';
              requestData.micrBankCode = (vm.customerMasterDetails.C054) ? vm.customerMasterDetails.C054.oldValue:'';

              }
              if(flag == 'micrBranchCode'){
              requestData.bankCode = (vm.customerMasterDetails.C050) ? vm.customerMasterDetails.C050.oldValue:'';
              requestData.micrBranchCode = (vm.customerMasterDetails.C051) ? vm.customerMasterDetails.C051.oldValue:'';

              }
              bondTransferInService.getNonHdfcTorch(requestData).then(
                function (response) {
                  vm.loader = false;
                  vm.nonHdfcTorchList = response.data.result.torchValue;
                  if(flag == 'bankName'){
                  angular.element("#rbiCodeModal").modal("toggle");
                  }else if(vm.customerMasterDetails.modeOfInterestPayment == 'MP003'){
                  angular.element("#rbiCodeModalIfscNeft").modal("toggle");
                  }else{
                                 angular.element("#rbiCodeModalIfsc").modal("toggle");
                  }
                },
                function (error) {
                  handleErrorResponse(error);
                  vm.loader = false;
                }
              );
        }
        		 function getOtherBankDetailsTorch(flag) {
                          vm.loader = true;
                          vm.searchSolId = "";
                          var requestData = {};
                          if(flag == 'bankName'){
                          requestData.nonBankName = vm.customerMasterDetails.bankName;
                          }
                          if(flag == 'ifsc'){
                          requestData.bankCode = vm.customerMasterDetails.bankCode;
                          requestData.instIfscCode = vm.customerMasterDetails.ifscCode;
                          }
                          if(flag == 'micrCodeBank'){
                          requestData.bankCode = vm.customerMasterDetails.bankCode;
                          requestData.micrBankCode = vm.customerMasterDetails.micrCode;
                          }
                          if(flag == 'micrBranchCode'){
                          requestData.bankCode = vm.customerMasterDetails.bankCode;
                          requestData.micrBranchCode = vm.customerMasterDetails.branchCode;
                          }
                          bondTransferInService.getNonHdfcTorch(requestData).then(
                            function (response) {
                              vm.loader = false;
                              vm.nonHdfcTorchList = response.data.result.torchValue;
                              if(flag == 'bankName'){
                              angular.element("#rbiCodeModal").modal("toggle");
                              }else if(vm.customerMasterDetails.modeOfInterestPayment == 'MP003'){
                              angular.element("#rbiCodeModalIfscNeft").modal("toggle");
                              }else{
                                             angular.element("#rbiCodeModalIfsc").modal("toggle");
                              }


                            },
                            function (error) {
                              handleErrorResponse(error);
                              vm.loader = false;
                            }
                          );
                    }
function getModuleName() {
			vm.loader = true;

			var requestData = {
			'blaFlag':"S"
			};
			bondTransferInService.getInquiryTorchDetails(requestData).then(function(response) {
				vm.loader = false;
				vm.schemeList = response.data.result;
				angular.element('#schemeModel').modal('toggle');
			}, function(error) {
				handleErrorResponse(error);
				vm.loader = false;
			});
		}
function getblaNoInq() {
			vm.loader = true;

			var requestData = {
				'schemeCode': vm.schemeCode,
				'schemeName': vm.schemeName,
				'panNum': vm.panNum,
				'bondHolderName': vm.bondHolderName,
				'blaNo': vm.blaNo,
				        				'blaFlag': 'B'
			};
			bondTransferInService.getblaNoInq(requestData).then(function(response) {
				vm.loader = false;
				vm.blaDataList = response.data.result.blaNoOut;
				angular.element('#blaModel').modal('toggle');
			}, function(error) {
				vm.loader = false;
				handleErrorResponse(error);
			});

		}
	function getsubscriptionIdInq() {
			vm.loader = true;

			var requestData = {
				'schemeCode': vm.schemeCode,
				'schemeName': vm.schemeName,
				'panNum': vm.panNum,
				'bondHolderName': vm.bondHolderName,
				'blaNo': vm.blaNo,
				'subscriptionId': vm.subscriptionId,
				        				'blaFlag': 'S'
			};
			bondTransferInService.getsubscriptionIdInq(requestData).then(function(response) {
				vm.loader = false;
				vm.bondSubsList = response.data.result.blaNoOut;

				angular.element('#bondSubsIdSearch').modal('toggle');
			}, function(error) {
				vm.loader = false;
				handleErrorResponse(error);
			});

		}
			function getPantorch() {

        			var requestData = {
        				'schemeCode': vm.schemeCode,
        				'bondHolderName': vm.bondHolderName,
        				'panNum': vm.panNum,
        				'blaFlag': 'P'

        			};
        			bondTransferInService.panTorch(requestData).then(function(response) {
        				vm.loader = false;
        				//                                                       vm.PanTorch = response.data.result;
        				vm.PanTorch = response.data.result;
        				angular.element('#panModel').modal('toggle');
        			}, function(error) {
        				vm.loader = false;
        				handleErrorResponse(error);
        			});
        		}
	function getGuardianDetails(flag) {
			vm.loader = true;

			if (flag == 'select') {
				var requestData = {
					'schemeCode': vm.schemeCode,
				};
				bondTransferInService.fetchGuardianDetails(requestData).then((response) => {
					vm.loader = false;
					vm.guardianDetailsList = response.data.result;
					angular.element('#guardianModal').modal('toggle');
				}, function(error) {
					vm.loader = false;
					handleErrorResponse(error);
				});
			} else {
				var requestData = {
					'schemeCode': vm.schemeCode,
					'guardianId': vm.customerMasterDetails.guardianId,
				};
				bondTransferInService.fetchGuardianDetails(requestData).then((response) => {
					vm.loader = false;
					vm.guardianDetail = response.data.result[0];

					vm.customerMasterDetails.guardianFirstName = vm.guardianDetail.guardianFirstName;
					vm.customerMasterDetails.guardianMiddleName = vm.guardianDetail.guardianMiddleName;
					vm.customerMasterDetails.guardianLastName = vm.guardianDetail.guardianLastName;
					vm.customerMasterDetails.relationWithMinor = vm.guardianDetail.relationWithMinor;
					vm.guardianDateOfBirth = convertDateFormat(vm.guardianDetail.guardianDateOfBirth);
					vm.customerMasterDetails.guardianDateOfBirth = vm.guardianDateOfBirth;


					vm.customerMasterDetails.guardianCommunicationAddress = vm.guardianDetail.guardianCommunicationAddress;
					vm.customerMasterDetails.guardianMobileNo = vm.guardianDetail.mobileNo;
					vm.customerMasterDetails.guardianEmailId = vm.guardianDetail.email;
				}, function(error) {
					vm.loader = false;
					handleErrorResponse(error);
				});
			}




		}


  function convertDateFormat(dateString) {
      var monthMap = {
        JAN: 1,
        FEB: 2,
        MAR: 3,
        APR: 4,
        MAY: 5,
        JUN: 6,
        JUL: 7,
        AUG: 8,
        SEP: 9,
        OCT: 10,
        NOV: 11,
        DEC: 12,
      };

      if (!dateString || typeof dateString !== "string") {
        throw new Error("Invalid date string");
      }

      var parts = dateString.split("-");
      if (parts.length !== 3) {
        throw new Error('Invalid date format. Expected "dd-MMM-yyyy".');
      }

      var day = parseInt(parts[0], 10);
      var month = monthMap[parts[1].toUpperCase()];
      var year = parseInt(parts[2], 10);

      if (!month) {
        throw new Error("Invalid month abbreviation");
      }

      return new Date(year, month - 1, day); // Convert to Date object
    }

                                function getRemarksList() {
                                     vm.referenceId = "056";
                                     var requestData = { referenceId: vm.referenceId };

                                     bondTransferInService.getRemarksList(requestData).then(
                                       function (response) {
                                         vm.remarksList = response.data.result || []; // Ensure it's an array
                                       },
                                       function (error) {
                                         handleErrorResponse(error);
                                       }
                                     );
                                   };

                                   vm.rejectReason = rejectReason;
                                       function rejectReason() {
                                         angular.element("#permissionMappingDelete").modal("toggle");
                                       }



       } })();