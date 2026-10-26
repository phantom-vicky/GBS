(function () {

    'use strict';

    angular.module('gbmUltraApp').controller('rbiBondSchemeParameterController',
        rbiBondSchemeParameterController);

    rbiBondSchemeParameterController.$inject = ['$scope', '$filter', '$state', '$rootScope',
        '$stateParams', '$location', '$timeout', 'rbiBondSchemeParameterService', 'commonService'
    ];



    function rbiBondSchemeParameterController($scope, $filter, $state, $rootScope, $stateParams,
        $location, $timeout, rbiBondSchemeParameterService, commonService) {

        var vm = this;
        vm.currentDate = new Date();
        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.dateValidation = dateValidation;
        vm.fromToDateValidation = fromToDateValidation;
        vm.specialNotAllowed = specialNotAllowed;
        vm.percentageCheck = percentageCheck;
        // vm.moduleNameBefore = "Floating Rate Savings Taxable Bonds, 2020";

        vm.checkRecordExisted = checkRecordExisted;
        vm.getModule = getModule;
        vm.versionEmpty = versionEmpty;
        vm.interestCodeEmpty = interestCodeEmpty;
        vm.addCBDTParameterCreation = addCBDTParameterCreation;
        vm.validatePoolAcct = validatePoolAcct;
        vm.getEffectFromDate = getEffectFromDate;
        vm.modifyCBDTParameterCreation = modifyCBDTParameterCreation;
        vm.verifyCBDTParameterCreation = verifyCBDTParameterCreation;
        vm.deleteCBDTParameterCreation = deleteCBDTParameterCreation;
        vm.getModifySelect = getModifySelect;
        vm.getInquiryDetails = getInquiryDetails;
        vm.nomineeValidation = nomineeValidation;
        vm.modNomineeValidation = modNomineeValidation;
        vm.validateNomineeAmountMin = validateNomineeAmountMin;
        vm.tranAmountValidation = tranAmountValidation;
        vm.getUnverifiedRecords = getUnverifiedRecords;
        vm.defaultSelectOption = defaultSelectOption;
        vm.validateRoundedValue = validateRoundedValue;
        vm.schemeTorchFunction = schemeTorchFunction;
        vm.timePattern = /(?:[01]\d|2[0-3]):(?:[0-5]\d):(?:[0-5]\d)/;
        vm.numVal = numVal;
        getModuleName();
        vm.panPattern = /[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}/;
        vm.showCancelConfirmation = showCancelConfirmation;
        vm.getRoundFactList = getRoundFactList;
        vm.monthList = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'];
        vm.dateList = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31'];
        vm.firstCharDot = firstCharDot;
        vm.alert = alert;
        function firstCharDot(event) {
            if (event == '.') {
                vm.cbdtSchemeParameter.C024 = '';
            }
        }

        vm.interstPayementDateM = interstPayementDateM;
        vm.interstPayementDateOneM = interstPayementDateOneM;
        function interstPayementDateM() {
            vm.cbdtSchemeParameter.C017.oldValue = day + '/' + month;;
        }

        function interstPayementDateOneM() {
            vm.cbdtSchemeParameter.C016.oldValue = day + '/' + month;;
        }


        function defaultSelectOption() {
            vm.isTdsActiveN = 'N';
            vm.isTdsActive = 'N';
            vm.isAgeBasedPrematureN = 'N';
            vm.isAgeBasedPremature = 'N';
            vm.isNormalPrematureN = 'N';
            vm.isNormalPremature = 'N';
            vm.isAadharReqN = 'N';
            vm.isAadharReq = 'N';
            vm.isAadharManN = 'N';
            vm.isAadharMan = 'N';
            vm.isAadharDisOnScreenN = 'N';
            vm.isAadharDisOnScreen = 'N';
            vm.isSeperateHolderN = 'N';
            vm.isSeperateHolder = 'N';
        }

        vm.indianNumberFormat = indianNumberFormat;
        function indianNumberFormat(value) {
            if (isNaN(value) || null == value) return value;
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
                return formattedWhole + '.00';
            }
        }
        vm.interstPayementDate = interstPayementDate;
        vm.interstPayementDateOne = interstPayementDateOne;

        function interstPayementDate() {
            var day = vm.intPaymntDay;
            var month = vm.intPaymntMonth;

            // Initialize invalidDate to false
            vm.invalidDate1 = false;

            if (month && day) {
                // Handle month-specific date validation
                var monthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]; // Days in each month
                if (month == 2) {
                    // Check for leap year if needed
                    var isLeapYear = (new Date().getFullYear() % 4 === 0 && new Date().getFullYear() % 100 !== 0) || (new Date().getFullYear() % 400 === 0);
                    if (isLeapYear) {
                        monthDays[1] = 29;
                    }
                }

                if (day > monthDays[month - 1]) {
                    vm.invalidDate1 = true;
                    return;
                }
                vm.cbdtSchemeParameter.C017 = day + '/' + month;
            }

            // Clear the invalid date message if the date is valid
            vm.invalidDate1 = false;
        };

        function interstPayementDateOne() {
            var day = vm.intPaymntDayOne;
            var month = vm.intPaymntMonthOne;

            // Initialize invalidDate to false
            vm.invalidDate = false;

            if (month && day) {
                // Handle month-specific date validation
                var monthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]; // Days in each month
                if (month == 2) {
                    // Check for leap year if needed
                    var isLeapYear = (new Date().getFullYear() % 4 === 0 && new Date().getFullYear() % 100 !== 0) || (new Date().getFullYear() % 400 === 0);
                    if (isLeapYear) {
                        monthDays[1] = 29;
                    }
                }

                if (day > monthDays[month - 1]) {
                    vm.invalidDate = true;
                    return;
                }
                vm.cbdtSchemeParameter.C016 = day + '/' + month;
            }

            // Clear the invalid date message if the date is valid
            vm.invalidDate = false;
        };

        vm.validateDateOneM = validateDateOneM;
        vm.validateDateOneMDelay = validateDateOneMDelay;
        function validateDateOneMDelay() {

            var day = vm.intPaymntDayOneM;
            var month = vm.intPaymntMonthOneM;

            // Initialize invalidDate to false
            vm.invalidDate = false;

            if (month && day) {
                // Handle month-specific date validation
                var monthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]; // Days in each month
                if (month == 2) {
                    // Check for leap year if needed
                    var isLeapYear = (new Date().getFullYear() % 4 === 0 && new Date().getFullYear() % 100 !== 0) || (new Date().getFullYear() % 400 === 0);
                    if (isLeapYear) {
                        monthDays[1] = 29;
                    }
                }

                if (day > monthDays[month - 1]) {
                    vm.invalidDate = true;
                    return;
                }
                vm.cbdtSchemeParameter.C016.oldValue = day + '/' + month;
            }





            // Clear the invalid date message if the date is valid
            vm.invalidDate = false;
        }

        function validateDateOneM() {
            setTimeout(validateDateOneMDelay, 300)

        };

        vm.validateDateM = validateDateM;
        vm.validateDateMDelay = validateDateMDelay;
        function validateDateM() {
            setTimeout(validateDateMDelay, 300)

        };
        function validateDateMDelay() {
            var day = vm.intPaymntDayM;
            var month = vm.intPaymntMonthM;

            // Initialize invalidDate to false
            vm.invalidDateM = false;

            if (month && day) {
                // Handle month-specific date validation
                var monthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]; // Days in each month
                if (month == 2) {
                    // Check for leap year if needed
                    var isLeapYear = (new Date().getFullYear() % 4 === 0 && new Date().getFullYear() % 100 !== 0) || (new Date().getFullYear() % 400 === 0);
                    if (isLeapYear) {
                        monthDays[1] = 29;
                    }
                }

                if (day > monthDays[month - 1]) {
                    vm.invalidDateM = true;
                    return;
                }
                vm.cbdtSchemeParameter.C017.oldValue = day + '/' + month;
            }





            // Clear the invalid date message if the date is valid
            vm.invalidDateM = false;
        };
        vm.decimalRbiBrokerage = decimalRbiBrokerage;
        function decimalRbiBrokerage(value) {
            if (value === '.') {
                vm.cbdtSchemeParameter.C044 = '';
                return;
            }
            if (!value && value !== 0) {
                vm.cbdtSchemeParameter.C046 = '';
                return;
            }
            let decimal = parseFloat(value);
            if (isNaN(decimal)) {
                vm.cbdtSchemeParameter.C046 = '';
                return;
            }
            let strValue = value.toString();
            let strValueLen = strValue.length;
            if (strValue[strValueLen - 4] == '.' || strValue[strValueLen - 5] == '.') {
                decimal = (value);
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C044 = decimal;
            }
            if (strValue[0] == '0' && strValue[1] == '0' && strValue[2] == '0') {
                vm.cbdtSchemeParameter.C044 = '';
            }
            if (strValue[strValueLen - 1] == '.') {
                for (var i = 0; i <= strValueLen - 2; i++) {
                    if (strValue[i] == '.') {
                        decimal = (value);
                        decimal = decimal.toString();
                        decimal = decimal.slice(0, -1);
                        vm.cbdtSchemeParameter.C044 = decimal;
                    }
                }
            }
            if (decimal > 100) {
                event.preventDefault();
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C044 = decimal;
            }
            if (decimal <= 0) {
                vm.errorMsg = 'Please enter a value greater than 0.';
                return false;
            } else {
                vm.errorMsg = '';
            }
            if (vm.cbdtSchemeParameter.C045 && vm.cbdtSchemeParameter.C044 > 0) {
                vm.cbdtSchemeParameter.C046 = parseFloat(vm.cbdtSchemeParameter.C044) - parseFloat(vm.cbdtSchemeParameter.C045);
            } else if (vm.cbdtSchemeParameter.C045 > 0) {
                vm.cbdtSchemeParameter.C046 = parseFloat(vm.cbdtSchemeParameter.C045);
            } else if (vm.cbdtSchemeParameter.C044 > 0) {
                vm.cbdtSchemeParameter.C046 = parseFloat(vm.cbdtSchemeParameter.C044);
            }
            else {
                vm.cbdtSchemeParameter.C046 = 0;
            }
            if (vm.cbdtSchemeParameter.C045 > vm.cbdtSchemeParameter.C044) {
                vm.cbdtSchemeParameter.C044 = '';
                vm.cbdtSchemeParameter.C046 = vm.cbdtSchemeParameter.C045;
            }
            vm.cbdtSchemeParameter.C046 = parseFloat(vm.cbdtSchemeParameter.C046.toFixed(2));
        }
        vm.decimalPercentageShare = decimalPercentageShare;
        function decimalPercentageShare(value) {
            if (value === '.') {
                vm.cbdtSchemeParameter.C045 = '';
                return;
            }
            if (!value && value !== 0) {
                vm.cbdtSchemeParameter.C046 = '';
                return;
            }
            let decimal = parseFloat(value);
            if (isNaN(decimal)) {
                vm.cbdtSchemeParameter.C046 = '';
                return;
            }
            let strValue = value.toString();
            let strValueLen = strValue.length;
            if (strValue[strValueLen - 5] == '.' || strValue[strValueLen - 4] == '.') {
                decimal = (value);
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C045 = decimal;
            }
            //to validate zero
            if (strValue[0] == '0' && strValue[1] == '0' && strValue[2] == '0') {
                vm.cbdtSchemeParameter.C045 = '';
            }
            if (strValue[strValueLen - 1] == '.') {
                for (var i = 0; i <= strValueLen - 2; i++) {
                    if (strValue[i] == '.') {
                        decimal = (value);
                        decimal = decimal.toString();
                        decimal = decimal.slice(0, -1);
                        vm.cbdtSchemeParameter.C045 = decimal;
                    }
                }
            }
            if (decimal > 100) {
                event.preventDefault();
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C045 = decimal;
            }
            if (vm.cbdtSchemeParameter.C045 && vm.cbdtSchemeParameter.C044 > 0) {
                vm.cbdtSchemeParameter.C046 = parseFloat(vm.cbdtSchemeParameter.C044) - parseFloat(vm.cbdtSchemeParameter.C045);
            } else if (vm.cbdtSchemeParameter.C045 > 0) {
                vm.cbdtSchemeParameter.C046 = parseFloat(vm.cbdtSchemeParameter.C045);
            } else if (vm.cbdtSchemeParameter.C044 > 0) {
                vm.cbdtSchemeParameter.C046 = parseFloat(vm.cbdtSchemeParameter.C044);
            } else {
                vm.cbdtSchemeParameter.C046 = 0;
            }
            if (vm.cbdtSchemeParameter.C045 > vm.cbdtSchemeParameter.C044) {
                vm.cbdtSchemeParameter.C045 = '';
                vm.cbdtSchemeParameter.C046 = vm.cbdtSchemeParameter.C044;
            }
            vm.cbdtSchemeParameter.C046 = vm.cbdtSchemeParameter.C046.toFixed(2);
        }

        vm.decimalRbiBrokerageM = decimalRbiBrokerageM;
        function decimalRbiBrokerageM(value) {
            if (value === '.') {
                vm.cbdtSchemeParameter.C044.oldValue = '';
                return;
            }
            if (!value && value !== 0) {
                vm.cbdtSchemeParameter.C046.oldValue = '';
                return;
            }
            let decimal = parseFloat(value);
            if (isNaN(decimal)) {
                vm.cbdtSchemeParameter.C046.oldValue = '';
                return;
            }
            let strValue = value.toString();
            let strValueLen = strValue.length;
            if (strValue[strValueLen - 4] == '.' || strValue[strValueLen - 5] == '.') {
                decimal = (value);
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C044.oldValue = decimal;
            }
            if (strValue[0] == '0' && strValue[1] == '0' && strValue[2] == '0') {
                vm.cbdtSchemeParameter.C044.oldValue = '';
            }
            if (strValue[strValueLen - 1] == '.') {
                for (var i = 0; i <= strValueLen - 2; i++) {
                    if (strValue[i] == '.') {
                        decimal = (value);
                        decimal = decimal.toString();
                        decimal = decimal.slice(0, -1);
                        vm.cbdtSchemeParameter.C044.oldValue = decimal;
                    }
                }
            }
            if (decimal > 100) {
                event.preventDefault();
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C044.oldValue = decimal;
            }
            if (decimal <= 0) {
                vm.errorMsg = 'Please enter a value greater than 0.';
                return false;
            } else {
                vm.errorMsg = '';
            }
            if (vm.cbdtSchemeParameter.C045.oldValue && vm.cbdtSchemeParameter.C044.oldValue > 0) {
                vm.cbdtSchemeParameter.C046.oldValue = parseFloat(vm.cbdtSchemeParameter.C044.oldValue) - parseFloat(vm.cbdtSchemeParameter.C045.oldValue);
            } else if (vm.cbdtSchemeParameter.C045.oldValue > 0) {
                vm.cbdtSchemeParameter.C046.oldValue = parseFloat(vm.cbdtSchemeParameter.C045.oldValue);
            } else if (vm.cbdtSchemeParameter.C044.oldValue > 0) {
                vm.cbdtSchemeParameter.C046.oldValue = parseFloat(vm.cbdtSchemeParameter.C044.oldValue);
            }
            else {
                vm.cbdtSchemeParameter.C046.oldValue = 0;
            }
            if (vm.cbdtSchemeParameter.C045.oldValue > vm.cbdtSchemeParameter.C044.oldValue) {
                vm.cbdtSchemeParameter.C044.oldValue = '';
                vm.cbdtSchemeParameter.C046.oldValue = vm.cbdtSchemeParameter.C045.oldValue;
            }
            vm.cbdtSchemeParameter.C046.oldValue = parseFloat(vm.cbdtSchemeParameter.C046.oldValue.toFixed(2));
        }
        vm.decimalPercentageShareM = decimalPercentageShareM;
        function decimalPercentageShareM(value) {
            if (value === '.') {
                vm.cbdtSchemeParameter.C045.oldValue = '';
                return;
            }
            if (!value && value !== 0) {
                vm.cbdtSchemeParameter.C046.oldValue = '';
                return;
            }
            let decimal = parseFloat(value);
            if (isNaN(decimal)) {
                vm.cbdtSchemeParameter.C046.oldValue = '';
                return;
            }
            let strValue = value.toString();
            let strValueLen = strValue.length;
            if (strValue[strValueLen - 5] == '.' || strValue[strValueLen - 4] == '.') {
                decimal = (value);
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C045.oldValue = decimal;
            }
            //to validate zero
            if (strValue[0] == '0' && strValue[1] == '0' && strValue[2] == '0') {
                vm.cbdtSchemeParameter.C045.oldValue = '';
            }
            if (strValue[strValueLen - 1] == '.') {
                for (var i = 0; i <= strValueLen - 2; i++) {
                    if (strValue[i] == '.') {
                        decimal = (value);
                        decimal = decimal.toString();
                        decimal = decimal.slice(0, -1);
                        vm.cbdtSchemeParameter.C045.oldValue = decimal;
                    }
                }
            }
            if (decimal > 100) {
                event.preventDefault();
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C045.oldValue = decimal;
            }
            if (vm.cbdtSchemeParameter.C045.oldValue && vm.cbdtSchemeParameter.C044.oldValue > 0) {
                vm.cbdtSchemeParameter.C046.oldValue = parseFloat(vm.cbdtSchemeParameter.C044.oldValue) - parseFloat(vm.cbdtSchemeParameter.C045.oldValue);
            } else if (vm.cbdtSchemeParameter.C045.oldValue > 0) {
                vm.cbdtSchemeParameter.C046.oldValue = parseFloat(vm.cbdtSchemeParameter.C045.oldValue);
            } else if (vm.cbdtSchemeParameter.C044.oldValue > 0) {
                vm.cbdtSchemeParameter.C046.oldValue = parseFloat(vm.cbdtSchemeParameter.C044.oldValue);
            } else {
                vm.cbdtSchemeParameter.C046.oldValue = 0;
            }
            if (vm.cbdtSchemeParameter.C045.oldValue > vm.cbdtSchemeParameter.C044.oldValue) {
                vm.cbdtSchemeParameter.C045.oldValue = '';
                vm.cbdtSchemeParameter.C046.oldValue = vm.cbdtSchemeParameter.C044.oldValue;
            }
            vm.cbdtSchemeParameter.C046.oldValue = vm.cbdtSchemeParameter.C046.oldValue.toFixed(2);
        }

        vm.percentageCheckNew = percentageCheckNew;
        function percentageCheckNew(event) {
            let decimal = event.target.value + event.key;
            let strValueLen = decimal.length;
            let dot = decimal.lastIndexOf(".");
            let dotCount = decimal.split('.').length - 1;

            if (!((dot == -1 && strValueLen <= 2) || (dot == 1 && strValueLen <= 6) || (dot == 2 && strValueLen <= 7))) { event.preventDefault(); }
            if (dotCount == 2) { event.preventDefault(); }
        }
        vm.percentageCheckChange1 = percentageCheckChange1;
        function percentageCheckChange1(id) {
            let value = vm[id] || ''; // Ensure value is a string
            let dotIndex = value.lastIndexOf(".");
            let dotCount = value.split('.').length - 1;
            // Remove extra decimal points
            if (dotCount > 1) {
                value = value.replace(/\.+$/, ''); // Remove all but the first decimal
            }
            // Limit the number of characters
            if (!((dotIndex === -1 && value.length <= 5) || (dotIndex === 1 && value.length <= 6) || (dotIndex === 2 && value.length <= 7))) {
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
        vm.percentageCheckChange = percentageCheckChange;
        function percentageCheckChange(value, id) {
            let decimal = value;
            let strValueLen = decimal.length;
            let dot = decimal.lastIndexOf(".");
            let dotCount = decimal.split('.').length - 1;
            if (!((dot == -1 && strValueLen <= 2) || (dot == 1 && strValueLen <= 6) || (dot == 2 && strValueLen <= 7))) { vm.cbdtSchemeParameter[id] = ''; }
            if (dotCount == 2) { vm.cbdtSchemeParameter[id] = ''; }
        }
        vm.percentageCheckChangeM = percentageCheckChangeM;
        function percentageCheckChangeM(value, id) {
            let decimal = value;
            let strValueLen = decimal.length;
            let dot = decimal.lastIndexOf(".");
            let dotCount = decimal.split('.').length - 1;
            if (!((dot == -1 && strValueLen <= 2) || (dot == 1 && strValueLen <= 6) || (dot == 2 && strValueLen <= 7))) { vm.cbdtSchemeParameter[id].oldValue = ''; }
            if (dotCount == 2) { vm.cbdtSchemeParameter[id].oldValue = ''; }
        }

        vm.handlePercentageInput = handlePercentageInput;

        function handlePercentageInput(event) {
            let inputValue = event.target.value;
            let key = event.key;
            let cursorPosition = event.target.selectionStart; // Get the cursor position

            // Prevent input if it's a decimal point at the start or if it's incorrectly placed
            if (key === "." && (cursorPosition === 0 || inputValue.startsWith("."))) {
                event.preventDefault();
                return;
            }

            // Combine the existing value with the new key
            let combinedValue = inputValue.slice(0, cursorPosition) + key + inputValue.slice(cursorPosition);

            // Regular expressions to match the allowed formats
            let regex = /^\d{0,3}(\.\d{0,2})?$/;

            // Check if the combined value matches the regex pattern
            if (!regex.test(combinedValue)) {
                event.preventDefault();
            }
        }
        vm.firstCharDotServiceRate = firstCharDotServiceRate;

        function firstCharDotServiceRate() {
            let value = vm.cbdtSchemeParameter.C031.oldValue;

            // Prevent input if it starts with a decimal
            if (value && value[0] === '.') {
                vm.cbdtSchemeParameter.C031.oldValue = '';  // Clear the input if it starts with a decimal
            }

            // Check if the input is in an invalid format after changes (e.g. ".11")
            let dotIndex = value.indexOf('.');

            // If the input starts with a decimal or only contains the decimal with no leading digits
            if (dotIndex === 0 || (dotIndex === 1 && value.length <= 4 && value[0] === '0')) {
                vm.cbdtSchemeParameter.C031.oldValue = '';  // Clear the decimal and any values after it
            }
        }

        vm.firstCharDotServiceRate1 = firstCharDotServiceRate1;

        function firstCharDotServiceRate1() {
            let value = vm.cbdtSchemeParameter.C032.oldValue;

            // Prevent input if it starts with a decimal
            if (value && value[0] === '.') {
                vm.cbdtSchemeParameter.C032.oldValue = '';  // Clear the input if it starts with a decimal
            }

            // Check if the input is in an invalid format after changes (e.g. ".11")
            let dotIndex = value.indexOf('.');

            // If the input starts with a decimal or only contains the decimal with no leading digits
            if (dotIndex === 0 || (dotIndex === 1 && value.length <= 4 && value[0] === '0')) {
                vm.cbdtSchemeParameter.C032.oldValue = '';  // Clear the decimal and any values after it
            }
        }

        vm.validatePercentageValue = validatePercentageValue;

        function validatePercentageValue(value, id) {
            // Ensure the value does not start with a decimal point
            if (value.startsWith(".")) {
                vm.cbdtSchemeParameter[id].oldValue = '';
                return;
            }

            // Regular expressions to match the allowed formats
            let regex = /^\d{0,3}(\.\d{0,2})?$/;

            // Validate the value against the regex pattern
            if (!regex.test(value)) {
                vm.cbdtSchemeParameter[id].oldValue = '';
            }
        }


        vm.handlePercentageInput1 = handlePercentageInput1;

        function handlePercentageInput1(event) {
            let inputValue = event.target.value;
            let key = event.key;
            let cursorPosition = event.target.selectionStart; // Get the cursor position

            // Prevent input if it's a decimal point at the start or if it's incorrectly placed
            if (key === "." && (cursorPosition === 0 || inputValue.startsWith("."))) {
                event.preventDefault();
                return;
            }

            // Combine the existing value with the new key
            let combinedValue = inputValue.slice(0, cursorPosition) + key + inputValue.slice(cursorPosition);

            // Regular expressions to match the allowed formats
            let regex = /^\d{0,3}(\.\d{0,2})?$/;

            // Check if the combined value matches the regex pattern
            if (!regex.test(combinedValue)) {
                event.preventDefault();
            }
        }


        vm.validatePercentageValue1 = validatePercentageValue1;

        function validatePercentageValue1(value, id) {
            // Ensure the value does not start with a decimal point
            if (value.startsWith(".")) {
                vm.cbdtSchemeParameter[id].oldValue = '';
                return;
            }

            // Regular expressions to match the allowed formats
            let regex = /^\d{0,3}(\.\d{0,2})?$/;

            // Validate the value against the regex pattern
            if (!regex.test(value)) {
                vm.cbdtSchemeParameter[id].oldValue = '';
            }
        }




        function specialNotAllowed(data) {
            if (data == '?' || data == '.') {
                vm.cbdtSchemeParameter.C026 = '';
                return;
            }
            data = data.replace(/[^\w]/g, ''); // Adjust the regex as needed
            vm.cbdtSchemeParameter.C026 = data;
        }
        function dateValidation() {

            vm.dateOptions2 = {
                minDate: new Date() // Set the minimum date to the current date
            };
        }
        function alert(item) {

            for (let i = 0; i < vm.schemeList.length; i++) {
                if (vm.schemeList[i].subCode == item) {
                    vm.schemeName = vm.schemeList[i].subCodeDescription;
                    //vm.cusName = vm.custList[i].cusName;
                    //  vm.cusAlphaCode = vm.custList[i].cusAlphaCode;
                    // vm.id = vm.custList[i].id;
                }
            }
        }
        vm.fromToDate = fromToDate;
        function fromToDate(value) {
            var fromDate = vm.cbdtSchemeParameter.C003;
            var toDate = vm.cbdtSchemeParameter.C004;
            // If From Date is after To Date, reset To Date or show error
            if (fromDate && toDate && moment(fromDate).isAfter(moment(toDate))) {
                vm.cbdtSchemeParameter.C004 = '';
                Swal.fire("Warning!", "To Date cannot be earlier than From Date.", "warning");
                return;
            }
        }
        vm.fromToDateM = fromToDateM;
        function fromToDateM() {
            var fromDate = vm.cbdtSchemeParameter.C003.oldValue;
            var toDate = vm.cbdtSchemeParameter.C004.oldValue;
            // If From Date is after To Date, reset To Date or show error
            if (fromDate && toDate && moment(fromDate).isAfter(moment(toDate))) {
                vm.cbdtSchemeParameter.C004.oldValue = '';
                Swal.fire("Warning!", "To Date cannot be earlier than From Date.", "warning");
                return;
            }
        }

        function fromToDateValidation() {
            vm.cbdtSchemeParameter.C004 = '';
            vm.dateToOptions = {
                minDate: vm.cbdtSchemeParameter.C003,
                // maxDate: vm.currentDate
            };
            vm.dateOptions = {
                maxDate: vm.currentDate
            }
        }

        function versionEmpty() {
            vm.versionNo = '';
            vm.effFromDate = '';
        }
        function interestCodeEmpty() {
            vm.cbdtSchemeParameter.C014 = '';
        }
        vm.interestPaymentFrequency = [
            { label: "Half-yearly", value: "H" },
        ];

        vm.inCaseOfHolidayAlternateInterestPaymentDate = [
            { label: "Previous", value: "P" },
            { label: "Next Day", value: "N" },
        ];

        vm.typeOfBondSchemeSubscriptionAllowedUnderBondScheme = [
            { label: "Cumulative", value: "C" },
            { label: "Non-Cumulative", value: "N" },
            { label: "Both", value: "B" },
        ];
        vm.typeOfSchemeStatus = [
            { label: "Current Scheme", value: "C" },
            { label: "Active Scheme", value: "A" },
            { label: "Closed", value: "CL" },
        ];
        vm.roundingFactorMetaData = [
            { label: "Ceil", value: "RF002" },
            { label: "Floor", value: "RF003" },
            { label: "Round", value: "RF004" },
            { label: "Actual", value: "RF001" },
        ];


        if ($state.current.name == 'rbibond_scheme_parameter.modify_details') {
            if ($stateParams.obj) {
                vm.effFromDate = $stateParams.obj.effFromDate;
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;
                if (vm.effFromDate) {
                    getModifySelect(null);
                }
            }
        }


        else if ($state.current.name == 'rbibond_scheme_parameter.verify_detail') {

            if ($stateParams.obj) {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;
                if (vm.schemeCode) {
                    vm.getUnverifiedRecords();
                }
            }
        }
        if ($state.current.name == 'rbibond_scheme_parameter.inquiry_detail') {
            vm.versionNo = $stateParams.obj.versionNo;
            vm.effFromDate = $stateParams.obj.effFromDate;
            vm.schemeCode = $stateParams.obj.schemeCode;
            vm.schemeName = $stateParams.obj.schemeName;
            if (vm.versionNo) {
                getInquiryDetails();
            }
        }
        vm.effFromDateOptions = {
            showWeeks: false
        }

        function tranAmountValidation(amt) {
            if (amt < 1) {
                vm.cbdtSchemeParameter.C030 = '';
                vm.errorMsgForTranAmountValidation = true;
            } else {
                vm.errorMsgForTranAmountValidation = false;
            }
        }


        vm.dateOptions = {
            showWeeks: false,
            minDate: new Date()
        }
        vm.dateOptionsM = {
            showWeeks: false,
            minDate: vm.cbdtSchemeParameter?.C003?.oldValue || null,
            //maxDate: new Date()
        };

        //Get Module Name
        function getModuleName() {
            vm.loader = true;
            vm.referenceId = 'TAX';
            var requestData = {
                'referenceId': vm.referenceId
            };
            rbiBondSchemeParameterService.getRbiBondModuleNameDetails(requestData).then(function (response) {
                vm.loader = false;
                vm.schemeList = response.data.result;
                //vm.schemeName = vm.schemeList[0].subCodeDescription;
            }, function (error) {
                handleErrorResponse(error);
                vm.loader = false;
            });
        }
        function getModule() {
            vm.loader = true;
            var requestData = {
                'referenceId': vm.schemeCode
            };
            rbiBondSchemeParameterService.getRbiBondModule(requestData).then(function (response) {
                vm.loader = false;
                vm.effFromDate = new Date(response.data.result);
                vm.effFromDateOptionsM = {
                    minDate: vm.effFromDate,
                    showWeeks: false
                }
            }, function (error) {
                handleErrorResponse(error);
                vm.loader = false;
            });
        }
        function getRoundFactList() {
            //                         vm.referenceId = 'ROUNDING_FACTOR';
            //                         var requestData = {
            //                              'referenceId': vm.referenceId
            //                         }
            commonService.getRbiBondMetaDataList('ROUNDING_FACTOR',
                (result) => {
                    vm.roundOff = result;

                },
                function (error) {
                    handleErrorResponse(error);
                }
            );


        }

        function getEffectFromDate() {
            vm.loader = true;
            var requestData = {
                table: 'TB003',
                columnList: ['C005'],
                conditionList: [{
                    colName: COLUMN_SHORT_CODES.VERIFIED_STATUS,
                    value: 'Y',
                    condition: CONDITIONS.EQUAL,
                    order: 1
                }, {
                    colName: COLUMN_SHORT_CODES.IS_DELETED,
                    value: 'N',
                    condition: CONDITIONS.EQUAL,
                    conjunction: CONJUNCTIONS.AND,
                    order: 2
                }, {
                    colName: 'IS_ACT',
                    value: 'Y',
                    condition: CONDITIONS.EQUAL,
                    conjunction: CONJUNCTIONS.AND,
                    order: 3
                }]
            }
            commonService.genericTableSelectRbiBond(requestData).then(function (response) {
                vm.loader = false;
                vm.effFromDate = new Date(response.data.result[0].C005);
                vm.effFromDateOptionsM = {
                    minDate: vm.effFromDate,
                    showWeeks: false
                }
            }, function (error) {
                vm.loader = false;
                handleErrorResponse(error)
            })
        }
        vm.textableDatePicker = textableDatePicker;
        vm.autoslashing = autoslashing;

        function textableDatePicker(id, valueDate) {
            commonService.textableDatePicker(id, valueDate);
        }
        function autoslashing(id) {
            commonService.autoslashing(id);
        }


        function addCBDTParameterCreation() {
            vm.cbdtSchemeParameterListModel = JSON.parse(JSON.stringify(vm.cbdtSchemeParameter));
            vm.cbdtSchemeParameterListModel.C003 = $filter('date')(vm.cbdtSchemeParameterListModel.C003, 'dd-MMM-yyyy');
            vm.cbdtSchemeParameterListModel.C004 = $filter('date')(vm.cbdtSchemeParameterListModel.C004, 'dd-MMM-yyyy');
            vm.cbdtSchemeParameterListModel.C005 = $filter('date')(vm.cbdtSchemeParameterListModel.C005, 'dd-MMM-yyyy');
            vm.cbdtSchemeParameterListModel.C027 = $filter('date')(vm.cbdtSchemeParameterListModel.C027, 'dd-MMM-yyyy');
            vm.cbdtSchemeParameterListModel.C019 = vm.isAgeBasedPremature;
            vm.cbdtSchemeParameterListModel.C020 = vm.isNormalPremature;
            vm.cbdtSchemeParameterListModel.C034 = vm.isAadharReq;
            vm.cbdtSchemeParameterListModel.C035 = vm.isAadharMan;
            vm.cbdtSchemeParameterListModel.C036 = vm.isAadharDisOnScreen;
            vm.cbdtSchemeParameterListModel.C012 = vm.isTdsActive;
            vm.cbdtSchemeParameterListModel.C037 = vm.isSeperateHolder;
            vm.cbdtSchemeParameterListModel.C050 = vm.nomineeSharableAmountMultiples;
            vm.cbdtSchemeParameterListModel.C051 = vm.nomineeMinimumAmount;
            if (vm.cbdtSchemeParameterListModel.C019 == 'Y') {
                vm.cbdtSchemeParameterListModel.C020 = 'N';
            }

            var requestData = {
                'columnValues': convertToColumnValuesArray(vm.cbdtSchemeParameterListModel),
                'schemeCode': vm.schemeCode
            };
            //			Swal.fire({
            //					title: "Are you sure?",
            //					text: "Do you want to Add this record?",
            //					type: "warning",
            //					showCancelButton: true,
            //					cancelButtonClass: "btn-cancel",
            //					confirmButtonClass: "btn-primary",
            //					confirmButtonText: "Yes",
            //					closeOnConfirm: false
            //				},
            //
            //				function () {





            Swal.fire({
                title: "Are you sure?",
                text: "Do you want to Really Add this record?",
                icon: "warning",
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
                    rbiBondSchemeParameterService.addCBDTSchemeParameter(requestData).then(function (response) {
                        vm.loader = false;
                        Swal.fire("Added!", "RBI Bond Scheme parameters Created successfully", "success");
                        $state.go('rbibond_scheme_parameter.inquiry')
                    }, function (error) {
                        vm.loader = false;
                        handleErrorResponse(error);
                    })
                }
            });
        }



        vm.getInquiryVersionTourch = getInquiryVersionTourch;
        function getInquiryVersionTourch() {
            vm.loader = true;
            vm.searchSolId = '';
            var requestData = {
                'schemeCode': "",
                'versionNo': "",
                'effeFromDate': ""
            }
            rbiBondSchemeParameterService.getInquirySelectDetails(requestData).then(function (response) {
                vm.loader = false;
                vm.schemeNameList = response.data.result.schemeTorchList;
                angular.element('#schemeNameSearcherPopUp').modal('toggle');
            }, function (error) {
                handleErrorResponse(error);
                vm.loader = false;
            });
        }
        function getModifySelect(flag) {
            if (flag == 'Torch') {
                vm.schemeCode = null;
            }
            vm.loader = true;
            vm.effFromDateFilter = $rootScope.formatDateToDDMMMYYYY(vm.effFromDate);
            //vm.effFromDateFilter = (vm.effFromDateFilter).toUpperCase();
            var requestData = {
                'effFromDate': vm.effFromDateFilter,
                'interestRateCode': 'Y',
                'schemeCode': vm.schemeCode
            };
            rbiBondSchemeParameterService.getModifySelect(requestData).then(function (response) {
                vm.loader = false;
                vm.cbdtId = response.data.result.pkColumnValue;
                vm.list = response.data.result;
                vm.cbdtSchemeParameter = response.data.result.masterOldNewValueModel;
                vm.auditInfo = response.data.result;

                if (flag == 'Torch') {
                    vm.schemeNameList = response.data.result.schemeParameterResponseDtoList;
                    angular.element('#schemeNameSearcherPopUp').modal('toggle');
                    vm.list.schemeParameterResponseDtoList.forEach(function (item) {
                        item.effFromDatePicker = convertToDateObject(item.effFromDate);
                    });
                } else {
                    if (vm.cbdtSchemeParameter.C003) { vm.cbdtSchemeParameter.C003.oldValue = convertToDateObject(vm.cbdtSchemeParameter.C003.oldValue); }
                    if (vm.cbdtSchemeParameter.C004) { vm.cbdtSchemeParameter.C004.oldValue = convertToDateObject(vm.cbdtSchemeParameter.C004.oldValue); }
                    if (vm.cbdtSchemeParameter.C005) { vm.cbdtSchemeParameter.C005.oldValue = convertToDateObject(vm.cbdtSchemeParameter.C005.oldValue); }
                    vm.cbdtSchemeParameter.C006.oldValue = parseInt(vm.cbdtSchemeParameter.C006.oldValue);
                    if (vm.cbdtSchemeParameter.C027) { vm.cbdtSchemeParameter.C027.oldValue = convertToDateObject(vm.cbdtSchemeParameter.C027.oldValue); }
                    vm.cbdtSchemeParameter.C032.oldValue = parseInt(vm.cbdtSchemeParameter.C032.oldValue);
                    vm.cbdtSchemeParameter.C031.oldValue = parseInt(vm.cbdtSchemeParameter.C031.oldValue);
                    vm.intPaymntDayOneM = vm.cbdtSchemeParameter.C016.oldValue.substring(0, 2);
                    vm.intPaymntMonthOneM = vm.cbdtSchemeParameter.C016.oldValue.substring(3, 5);
                    vm.intPaymntDayM = vm.cbdtSchemeParameter.C017.oldValue.substring(0, 2);
                    vm.intPaymntMonthM = vm.cbdtSchemeParameter.C017.oldValue.substring(3, 5);
                    vm.cbdtSchemeParameter.C024.oldValue = parseFloat(vm.cbdtSchemeParameter.C024.oldValue);
                    vm.cbdtSchemeParameter.C025.oldValue = parseInt(vm.cbdtSchemeParameter.C025.oldValue);
                    if (vm.cbdtSchemeParameter.C049) { vm.cbdtSchemeParameter.C049.oldValue = parseInt(vm.cbdtSchemeParameter.C049.oldValue); }
                    if (!vm.cbdtSchemeParameter.C050) { vm.cbdtSchemeParameter.C050 = { oldValue: '' }; }
                    if (!vm.cbdtSchemeParameter.C051) { vm.cbdtSchemeParameter.C051 = { oldValue: '' }; }
                    var currentStr = $filter('date')(vm.effFromDate, 'dd-MMM-yyyy');
                    var dbStr = $filter('date')(vm.cbdtSchemeParameter.C005.oldValue, 'dd-MMM-yyyy');
                    if (currentStr == dbStr) {
                        //                    					Swal.fire({
                        //                    						title: "Record already exists for the effective date",
                        //                    						text: "Do you wish to continue?",
                        //                    						type: "warning",
                        //                    						showCancelButton: true,
                        //                    						cancelButtonClass: "btn-cancel",
                        //                    						confirmButtonClass: "btn-danger",
                        //                    						confirmButtonText: "Yes",
                        //                    						cancelButtonText: "No",
                        //                    						closeOnConfirm: true
                        //                    					}, function (inputValue) {
                        //                    						if (!inputValue) {



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

                            }

                            else {
                                $state.go('rbibond_scheme_parameter.modify');
                            }
                        });
                    }
                    vm.cbdtSchemeParameter.C005.oldValue = convertToDateObject($filter('date')(vm.effFromDate, 'dd/MM/yyyy'));
                    vm.effFromDateOptions = {
                        minDate: vm.effFromDate,
                        showWeeks: false
                    }
                }
            }, function (error) {
                vm.loader = false;
                handleErrorResponse(error);
                $state.go('rbibond_scheme_parameter.inquiry');
            });
        }

        function convertToColumnValuesArrayOldNew(objectToBeConvert) {
            if (typeof (objectToBeConvert) === "object") {
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
        function modifyCBDTParameterCreation() {
            vm.cbdtSchemeParameterListModel = JSON.parse(JSON.stringify(vm.cbdtSchemeParameter));
            vm.cbdtSchemeParameterListModel.C003.oldValue = $filter('date')(vm.cbdtSchemeParameterListModel.C003.oldValue, 'dd-MMM-yyyy');

            if (vm.cbdtSchemeParameterListModel.C004) { vm.cbdtSchemeParameterListModel.C004.oldValue = $filter('date')(vm.cbdtSchemeParameterListModel.C004.oldValue, 'dd-MMM-yyyy'); }

            vm.cbdtSchemeParameterListModel.C005.oldValue = $filter('date')(vm.cbdtSchemeParameterListModel.C005.oldValue, 'dd-MMM-yyyy');
            if (vm.cbdtSchemeParameterListModel.C027) { vm.cbdtSchemeParameterListModel.C027.oldValue = $filter('date')(vm.cbdtSchemeParameterListModel.C027.oldValue, 'dd-MMM-yyyy'); }
            if (vm.cbdtSchemeParameterListModel.C019) {
                if (vm.cbdtSchemeParameterListModel.C019.oldValue == 'Y') { vm.cbdtSchemeParameterListModel.C020.oldValue = 'N'; }
            }
            var requestData = {
                'pkColumnValue': vm.cbdtId,
                'columnValues': convertToColumnValuesArrayOldNew(vm.cbdtSchemeParameterListModel),
                'schemeCode': vm.schemeCode
            };

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
                    rbiBondSchemeParameterService.modifyCBDTParameterCreation(requestData).then(function (response) {
                        vm.loader = false;
                        Swal.fire("Modified!", "Scheme Parameter Modified Successfully", "success");
                        $state.go('rbibond_scheme_parameter.inquiry')
                    }, function (error) {
                        vm.loader = false;
                        handleErrorResponse(error);
                        if (error.data.message == 'Record Not Modified') {
                            $state.go('rbibond_scheme_parameter.modify')
                        } else {
                            $state.go('rbibond_scheme_parameter.inquiry')
                        }
                    })
                }
            })
        }
        vm.decimalConvertBrokeragepercentage = decimalConvertBrokeragepercentage;
        vm.decimalConvertHandlingCharges = decimalConvertHandlingCharges;
        vm.decimalConvertServiceChargesSec = decimalConvertServiceChargesSec;
        vm.decimalConvertServiceRate = decimalConvertServiceRate;
        vm.decimalConvertBrokeragepercentageM = decimalConvertBrokeragepercentageM;
        vm.decimalConvertHandlingChargesM = decimalConvertHandlingChargesM;
        vm.decimalConvertServiceChargesSecM = decimalConvertServiceChargesSecM;
        vm.decimalConvertServiceRateM = decimalConvertServiceRateM;
        function decimalConvertBrokeragepercentage(value) {
            let decimal = parseFloat(value);
            let strValue = value;
            let strValueLen = value.length;
            if (strValue[1] == '.' && strValueLen == 5 || strValue[2] == '.' && strValueLen == 6) {
                decimal = (value);
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C029 = decimal;
            }
            if (strValue[strValueLen - 1] == '.') {
                for (var i = 0; i <= strValueLen - 2; i++) {
                    if (strValue[i] == '.') {
                        decimal = (value);
                        decimal = decimal.toString();
                        decimal = decimal.slice(0, -1);
                        vm.cbdtSchemeParameter.C029 = decimal;

                    }
                }
            }
            if (decimal > 100
            ) {
                event.preventDefault();
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C029 = decimal;
            }
        }
        vm.formatNumber = formatNumber;
        function formatNumber(value) {
            // Call the firstCharDotServiceRate function to handle the leading decimal logic
            firstCharDotServiceRateC031();

            let decimal = parseFloat(value);
            let strValue = value;
            let strValueLen = value.length;

            // Handle the case where the value is "?"
            if (value == '?') {
                vm.cbdtSchemeParameter.C031 = '';
            }

            // Handle cases where the value has too many digits after the decimal point
            if (strValue[1] == '.' && strValueLen == 5 ||
                strValue[2] == '.' && strValueLen == 6 ||
                strValue[3] == '.' && strValueLen == 7) {
                decimal = (value);
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C031 = decimal;
            }

            // Clear the input if it's just a decimal
            if (value == '.') {
                vm.cbdtSchemeParameter.C031 = '';
            }

            // Handle multiple decimals in the value
            if (strValue[strValueLen - 1] == '.') {
                for (var i = 0; i <= strValueLen - 2; i++) {
                    if (strValue[i] == '.') {
                        decimal = (value);
                        decimal = decimal.toString();
                        decimal = decimal.slice(0, -1);
                        vm.cbdtSchemeParameter.C031 = decimal;
                    }
                }
            }

            // Ensure the value doesn't exceed 999.99
            if (decimal > 999.99) {
                event.preventDefault();
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C031 = decimal;
            }
        }

        // Add the firstCharDotServiceRate function for handling leading decimal inputs
        function firstCharDotServiceRateC031() {
            let value = vm.cbdtSchemeParameter.C031;

            // Prevent input if it starts with a decimal
            if (value && value[0] === '.') {
                vm.cbdtSchemeParameter.C031 = '';  // Clear the input if it starts with a decimal
            }

            // Check if the input is in an invalid format after changes (e.g. ".11")
            let dotIndex = value.indexOf('.');

            // If the input starts with a decimal or only contains the decimal with no leading digits
            if (dotIndex === 0 || (dotIndex === 1 && value.length <= 4 && value[0] === '0')) {
                vm.cbdtSchemeParameter.C031 = '';  // Clear the decimal and any values after it
            }
        }
        vm.firstCharDotServiceRateForC032 = firstCharDotServiceRateForC032;

        vm.formatNumber2 = formatNumber2;
        function formatNumber2(value) {
            // Call the firstCharDotServiceRate function to handle the leading decimal logic
            firstCharDotServiceRateForC032();  // Adjusted for vm.cbdtSchemeParameter.C032

            let decimal = parseFloat(value);
            let strValue = value;
            let strValueLen = value.length;

            // Handle the case where the value is "?"
            if (value == '?') {
                vm.cbdtSchemeParameter.C032 = '';
            }

            // Handle cases where the value has too many digits after the decimal point
            if (strValue[1] == '.' && strValueLen == 5 ||
                strValue[2] == '.' && strValueLen == 6 ||
                strValue[3] == '.' && strValueLen == 7) {
                decimal = (value);
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C032 = decimal;
            }

            // Clear the input if it's just a decimal
            if (value == '.') {
                vm.cbdtSchemeParameter.C032 = '';
            }

            // Handle multiple decimals in the value
            if (strValue[strValueLen - 1] == '.') {
                for (var i = 0; i <= strValueLen - 2; i++) {
                    if (strValue[i] == '.') {
                        decimal = (value);
                        decimal = decimal.toString();
                        decimal = decimal.slice(0, -1);
                        vm.cbdtSchemeParameter.C032 = decimal;
                    }
                }
            }

            // Ensure the value doesn't exceed 999.99
            if (decimal > 999.99) {
                event.preventDefault();
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C032 = decimal;
            }
        }

        // Add the firstCharDotServiceRateForC032 function for handling leading decimal inputs for C032
        function firstCharDotServiceRateForC032() {
            let value = vm.cbdtSchemeParameter.C032;

            // Prevent input if it starts with a decimal
            if (value && value[0] === '.') {
                vm.cbdtSchemeParameter.C032 = '';  // Clear the input if it starts with a decimal
            }

            // Check if the input is in an invalid format after changes (e.g. ".11")
            let dotIndex = value.indexOf('.');

            // If the input starts with a decimal or only contains the decimal with no leading digits
            if (dotIndex === 0 || (dotIndex === 1 && value.length <= 4 && value[0] === '0')) {
                vm.cbdtSchemeParameter.C032 = '';  // Clear the decimal and any values after it
            }
        }

        vm.minValueOne = minValueOne;
        function minValueOne(event, entity) {
            if (parseInt(event.target.value) == 0) {
                Swal.fire("Error!", "Minimum value is 1", "error");
                vm.cbdtSchemeParameter[entity] = '';
            }
        }
        vm.minValueOneM = minValueOneM;
        function minValueOneM(event, entity) {
            if (parseInt(event.target.value) == 0) {
                Swal.fire("Error!", "Minimum value is 1", "error");
                vm.cbdtSchemeParameter[entity].oldValue = '';
            }
        }
        function decimalConvertHandlingCharges(value) {
            let decimal = parseFloat(value);
            let strValue = value;
            let strValueLen = value.length;
            if (value == '?') {
                vm.cbdtSchemeParameter.C030 = '';
            }
            if (strValue[1] == '.' && strValueLen == 7 || strValue[2] == '.' && strValueLen == 8 || strValue[3] == '.' && strValueLen == 9) {
                decimal = (value);
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C030 = decimal;
            } if (value == '.') {
                vm.cbdtSchemeParameter.C030 = '';
            } if (strValue[1] == '0' && strValue[0] == '0') {
                vm.cbdtSchemeParameter.C030 = '';
            } if (strValue[1] == '.') {
                vm.cbdtSchemeParameter.C030 = '';
            }
            if (strValue[strValueLen - 1] == '.') {
                for (var i = 0; i <= strValueLen - 2; i++) {
                    if (strValue[i] == '.') {
                        decimal = (value);
                        decimal = decimal.toString();
                        decimal = decimal.slice(0, -1);
                        vm.cbdtSchemeParameter.C030 = decimal;
                    }
                }
            }
            if (decimal >= 100.01) {
                event.preventDefault();
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C030 = decimal;
            }
        }
        function decimalConvertServiceRate(value) {
            let decimal = parseFloat(value);
            let strValue = value;
            let strValueLen = value.length;
            if (strValue[1] == '.' && strValueLen == 5 || strValue[2] == '.' && strValueLen == 6) {
                decimal = (value);
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C031 = decimal;
            }
            if (strValue[strValueLen - 1] == '.') {
                for (var i = 0; i <= strValueLen - 2; i++) {
                    if (strValue[i] == '.') {
                        decimal = (value);
                        decimal = decimal.toString();
                        decimal = decimal.slice(0, -1);
                        vm.cbdtSchemeParameter.C031 = decimal;
                    }
                }
            }
            if (decimal > 100
            ) {
                event.preventDefault();
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C031 = decimal;
            }
        }
        function decimalConvertServiceChargesSec(value) {
            let decimal = parseFloat(value);
            let strValue = value;
            let strValueLen = value.length;
            if (strValue[1] == '.' && strValueLen == 5 || strValue[2] == '.' && strValueLen == 6) {
                decimal = (value);
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C032 = decimal;
            }
            if (strValue[strValueLen - 1] == '.') {
                for (var i = 0; i <= strValueLen - 2; i++) {
                    if (strValue[i] == '.') {
                        decimal = (value);
                        decimal = decimal.toString();
                        decimal = decimal.slice(0, -1);
                        vm.cbdtSchemeParameter.C032 = decimal;
                    }
                }
            }
            if (decimal > 100
            ) {
                event.preventDefault();
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C032 = decimal;

            }
        }
        function decimalConvertBrokeragepercentageM(value) {
            let decimal = parseFloat(value);
            let strValue = value;
            let strValueLen = value.length;
            if (strValue[1] == '.' && strValueLen == 5 || strValue[2] == '.' && strValueLen == 6) {
                decimal = (value);
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C029.oldValue = decimal;
            }
            if (strValue[strValueLen - 1] == '.') {

                for (var i = 0; i <= strValueLen - 2; i++) {
                    if (strValue[i] == '.') {
                        decimal = (value);
                        decimal = decimal.toString();
                        decimal = decimal.slice(0, -1);
                        vm.cbdtSchemeParameter.C029.oldValue = decimal;

                    }
                }
            }
            if (decimal > 100
            ) {
                event.preventDefault();
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C029.oldValue = decimal;
            }
        }
        function decimalConvertHandlingChargesM(value) {
            let decimal = parseFloat(value);
            let strValue = value;
            let strValueLen = value.length;
            if (value == '?') {
                vm.cbdtSchemeParameter.C030 = '';
            }
            if (strValue[1] == '.' && strValueLen == 7 || strValue[2] == '.' && strValueLen == 8) {
                decimal = (value);
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C030.oldValue = decimal;
            } if (value == '.') {
                vm.cbdtSchemeParameter.C030 = '';
            } if (strValue[1] == '0' && strValue[0] == '0') {
                vm.cbdtSchemeParameter.C030 = '';
            } if (strValue[1] == '.') {
                vm.cbdtSchemeParameter.C030 = '';
            }
            if (strValue[strValueLen - 1] == '.') {
                for (var i = 0; i <= strValueLen - 2; i++) {
                    if (strValue[i] == '.') {
                        decimal = (value);
                        decimal = decimal.toString();
                        decimal = decimal.slice(0, -1);
                        vm.cbdtSchemeParameter.C030.oldValue = decimal;
                    }
                }
            }
            if (decimal > 99
            ) {
                event.preventDefault();
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C030.oldValue = decimal;
            }
        }
        function decimalConvertServiceRateM(value) {
            let decimal = parseFloat(value);
            let strValue = value;
            let strValueLen = value.length;
            if (strValue[1] == '.' && strValueLen == 5 || strValue[2] == '.' && strValueLen == 6) {
                decimal = (value);
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C031.oldValue = decimal;
            }
            if (strValue[strValueLen - 1] == '.') {
                for (var i = 0; i <= strValueLen - 2; i++) {
                    if (strValue[i] == '.') {
                        decimal = (value);
                        decimal = decimal.toString();
                        decimal = decimal.slice(0, -1);
                        vm.cbdtSchemeParameter.C031.oldValue = decimal;
                    }
                }
            }
            if (decimal > 100
            ) {
                event.preventDefault();
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C031.oldValue = decimal;
            }
        }
        function decimalConvertServiceChargesSecM(value) {
            let decimal = parseFloat(value);
            let strValue = value;
            let strValueLen = value.length;
            if (strValue[1] == '.' && strValueLen == 5 || strValue[2] == '.' && strValueLen == 6) {
                decimal = (value);
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C032.oldValue = decimal;
            }
            if (strValue[strValueLen - 1] == '.') {
                for (var i = 0; i <= strValueLen - 2; i++) {
                    if (strValue[i] == '.') {
                        decimal = (value);
                        decimal = decimal.toString();
                        decimal = decimal.slice(0, -1);
                        vm.cbdtSchemeParameter.C032.oldValue = decimal;
                    }
                }
            }
            if (decimal > 100
            ) {
                event.preventDefault();
                decimal = decimal.toString();
                decimal = decimal.slice(0, -1);
                vm.cbdtSchemeParameter.C032.oldValue = decimal;
            }
        }
        function checkRecordExisted(schemeCode) {
            console.log(vm.schemeCode);
            vm.loader = true;
            var reqDt = {
                'schemeCode': schemeCode
            };

            rbiBondSchemeParameterService.checkRecordExisted(reqDt).then(function (response) {
                vm.loader = false;
                if (response.data.result == 'F') {
                    Swal.fire("Warning!", "Record already added, Do Changes in modify", "warning");
                    $state.go('rbibond_scheme_parameter.inquiry');
                }
            }, function (error) {
                vm.loader = false;
                handleErrorResponse(error);
                $state.go('rbibond_scheme_parameter.inquiry');
            });
        }


        function schemeTorchFunction(modal) {
            vm.loader = true;
            var reqDateTwo = {
                'schemeCode': vm.schemeCode
            };
            rbiBondSchemeParameterService.getInterestRateCodeTorch(reqDateTwo).then(function (responseOne) {
                vm.loader = false;
                vm.interstRateCodeList = responseOne.data.result;
                if (modal == 'postOfficeSavingRateCode') {
                    angular.element('#postOfficeRateCodeModal').modal('toggle');
                } else {
                    angular.element('#rateCodeModal').modal('toggle');
                }
            }, function (errorOne) {
                vm.schemeCode = '';
                vm.loader = false;
                handleErrorResponse(errorOne);
                // $state.go('rbibond_scheme_parameter.inquiry');
            });
        }


        function getUnverifiedRecords() {
            vm.loader = true;
            var requestData = {
                'schemeCode': vm.schemeCode
            };
            rbiBondSchemeParameterService.getUnverifiedRecords(requestData).then(function (response) {
                vm.loader = false;
                if (response.data.result.masterOldNewValueModel) {
                    vm.auditInfo = response.data.result;
                    vm.cbdtId = response.data.result.pkColumnValue;
                    vm.verificationDetails = response.data.result.previousOperationType;
                    vm.cbdtSchemeParameter = response.data.result.masterOldNewValueModel;
                }
            }, function (error) {
                vm.loader = false;
                handleErrorResponse(error);
                if ($state.current.name == 'rbibond_scheme_parameter.verify_detail') {
                    $state.go('rbibond_scheme_parameter.verify')
                }
            })
        }

        function nomineeValidation() {
            if (!vm.cbdtSchemeParameter.C025 >= 1) {
                Swal.fire("Maximum 9 Nominees  should be allowed");
                $('#nominee').val('');
            }

        }

        function modNomineeValidation() {
            if (vm.cbdtSchemeParameter.C025.oldValue >= 1) {
                Swal.fire("Maximum 9 Nominees  should be allowed");
                $('#MaxNominee').val('');
            }
        }

        function validateNomineeAmountMin(value, mode, field) {
            if (value === '' || value === null || value === undefined) {
                return;
            }
            if (parseInt(value, 10) < 1000) {
                Swal.fire('Error!', 'Value should not be less than 1000', 'error');
                if (mode === 'A') {
                    if (field === 'C050') {
                        vm.nomineeSharableAmountMultiples = '';
                    } else if (field === 'C051') {
                        vm.nomineeMinimumAmount = '';
                    }
                } else if (mode === 'M' && vm.cbdtSchemeParameter[field]) {
                    vm.cbdtSchemeParameter[field].oldValue = '';
                }
            }
        }

        function getInquiryDetails(flag) {
            vm.loader = true;
            if (flag == 'versionNo') {
                vm.versionNo = '';
            }
            var requestData = {
                'versionNo': vm.versionNo,
                'schemeCode': vm.schemeCode
            };
            rbiBondSchemeParameterService.inquiryCBDTParameterCreation(requestData).then(function (response) {
                vm.loader = false;
                if (flag == 'versionNo') {
                    vm.versionNoList = response.data.result;
                    angular.element('#versionNoModal').modal('toggle');
                } else {
                    vm.auditInfo = response.data.result;
                    vm.cbdtId = response.data.result.pkColumnValue;
                    vm.verificationDetails = response.data.result.previousOperationType;
                    vm.cbdtSchemeParameter = response.data.result.masterOldNewValueModel;
                    if (vm.cbdtSchemeParameter.C013.oldValue == 'C') {

                    } else if (vm.cbdtSchemeParameter.C013.oldValue == 'A') {

                    } else if (vm.cbdtSchemeParameter.C013.oldValue == 'CL') {

                    }
                }
            }, function (error) {
                handleErrorResponse(error);
                if (error)
                    $state.go('rbibond_scheme_parameter.inquiry')
                vm.loader = false;
            })
        }

        function verifyCBDTParameterCreation() {
            //			Swal.fire({
            //					title: "Are you sure?",
            //					text: "Do you want to Verify this record?",
            //					type: "warning",
            //					showCancelButton: true,
            //					cancelButtonClass: "btn-cancel",
            //					confirmButtonClass: "btn-primary",
            //					confirmButtonText: "Yes",
            //					closeOnConfirm: false
            //				},
            //
            //				function () {



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
                    vm.loader = true
                    var requestData = {
                        'id': vm.cbdtId
                    }
                    rbiBondSchemeParameterService.verifyCBDTParameterCreation(requestData).then(function (response) {
                        vm.loader = false;
                        Swal.fire("Verified!", "Scheme Parameter Verified Successfully", "success");
                        $state.go('rbibond_scheme_parameter.inquiry')
                    }, function (error) {
                        vm.loader = false;
                        handleErrorResponse(error);
                        $state.go('rbibond_scheme_parameter.inquiry')
                    })
                }
            })
        }
        vm.rejectReason = rejectReason;
        function rejectReason() {
            angular.element("#permissionMappingDelete").modal("toggle");
        }
        vm.getRemarksList = getRemarksList;
        function getRemarksList() {
            vm.referenceId = '055';
            var requestData = { 'referenceId': vm.referenceId };
            rbiBondSchemeParameterService.getRemarksList(requestData).then(function (response) {
                vm.remarksList = response.data.result || []; // Ensure it's an array
            }, function (error) {
                handleErrorResponse(error);
            });
        }
        function deleteCBDTParameterCreation() {
            /* function handleKeyPress(event) {
                 var key = event.key;
                 var input = event.target.value;
 
                 if (key === ' ' && input.slice(-1) === ' ') {
                     event.preventDefault();
                 }
 
                 if (!key.match(/^[a-zA-Z\s]$/) && key !== 'Backspace' && key!=='Enter') {
                     event.preventDefault();
                 }
             }
 
             document.addEventListener('keypress', handleKeyPress);
 
             Swal.fire({
                 title: "Rejection Remarks",
                 text: "",
                 type: "input",
                 inputPlaceholder: "Enter your remarks here",
                 showCancelButton: true,
                 cancelButtonClass: "btn-cancel",
                 confirmButtonClass: "btn-primary",
                 confirmButtonText: "Submit",
                 animation: "slide-from-top",
                 closeOnConfirm: false,
                 showLoaderOnConfirm: true,
                 inputValidator: function(value) {
                     if (/^[a-zA-Z\s]*$/.test(value.trim())) {
                         if (/\s\s/.test(value)) {
                             return "Please enter only single spaces between words!";
                         }
                         document.removeEventListener('keypress', handleKeyPress);
                         return true;
                     } else {
                         return "Please enter only alphabetic characters!";
                     }
                 }
             }, function(inputValue) {
                 if (inputValue === false) {
                     document.removeEventListener('keypress', handleKeyPress);
                     return false;
                 }
 
                 if (inputValue === "") {
                     Swal.fire.showInputError("Please enter the remarks!");
                     return false;
                 }*/

            vm.loader = true;
            var requestData = {
                id: vm.cbdtId,
                remarks: vm.remarks
            };

            rbiBondSchemeParameterService.deleteCBDTParameterCreation(requestData)
                .then(function (response) {
                    vm.loader = false;
                    Swal.fire("Rejected!", "Scheme Parameter Data Rejected Successfully", "success");
                    $state.go('rbibond_scheme_parameter.inquiry');
                    const backdrop = document.querySelector('.modal-backdrop');
                    if (backdrop) {
                        backdrop.parentNode.removeChild(backdrop);
                        document.body.classList.remove('modal-open');
                    }
                })
                .catch(function (error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                    const backdrop = document.querySelector('.modal-backdrop');
                    if (backdrop) {
                        backdrop.parentNode.removeChild(backdrop);
                        document.body.classList.remove('modal-open');
                    }
                    $state.go('rbibond_scheme_parameter.inquiry');
                });
        }
        vm.minMaxInput = minMaxInput;
        function minMaxInput(event, mode) {
            if (event && event.length > 6) {
                event = event.slice(0, 6);
            }
            const parsedValue = parseInt(event, 10);
            const minValue = 100;
            const maxValue = 999900;
            function isValidMultipleOf100(value) {
                return value % 100 === 0;
            }
            if (mode == 'M') {
                if (event && (parseInt(event) < minValue || parseInt(event) > maxValue || !isValidMultipleOf100(parsedValue))) {
                    vm.cbdtSchemeParameter.C006.oldValue = '';
                    return;
                } else {
                    vm.cbdtSchemeParameter.C006.oldValue = event;
                    return;
                }
            } else if (mode == 'A') {
                if (event && (parseInt(event) < minValue || parseInt(event) > maxValue || !isValidMultipleOf100(parsedValue))) {
                    vm.cbdtSchemeParameter.C006 = '';
                    return;
                } else {
                    vm.cbdtSchemeParameter.C006 = event;
                    return;
                }
            }
        }

        vm.validateInput = validateInput;
        function validateInput(event, id) {

            const textBox = document.getElementById(id);

            var charCode = event.which || event.keyCode;
            var charTyped = String.fromCharCode(charCode);

            if (textBox) {
                const cursorPosition = textBox.selectionStart;
                if (cursorPosition == 0 && charTyped === ' ') {
                    event.preventDefault();
                    return;

                }
            }
            var currentValue = event.target.value + charTyped;
            var pattern = /^[0-9]{1,3}$/;

            if (!pattern.test(currentValue)) {
                event.preventDefault();
            }
        }


        /* Account Number Validation */
        function validatePoolAcct(accountNum, flag, errorFlag, mode) {
            if (accountNum) {
                vm.accountNum = accountNum.toUpperCase();

            }
        }

        function numVal(flag, mode, value) {
            if (typeof value === 'undefined') {
                if (flag == 'cbs') {
                    if (mode == 'add') {
                        vm.cbdtSchemeParameter.C007 = '';
                    }
                    else {
                        vm.cbdtSchemeParameter.C007.oldValue = '';
                    }
                }
                else if (flag == 'rcbs') {
                    if (mode == 'add') {
                        vm.cbdtSchemeParameter.C008 = '';
                    }
                    else {
                        vm.cbdtSchemeParameter.C008.oldValue = '';
                    }
                }
                else if (flag == 'cris') {
                    if (mode == 'add') {
                        vm.cbdtSchemeParameter.C009 = '';
                    }
                    else {
                        vm.cbdtSchemeParameter.C009.oldValue = '';
                    }
                }
                else if (flag == 'crisInterval') {
                    if (mode == 'add') {
                        vm.cbdtSchemeParameter.C010 = '';
                    }
                    else {
                        vm.cbdtSchemeParameter.C010.oldValue = '';
                    }
                }
            }
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
            //				function () {

            Swal.fire({
                title: "Confirm Cancel",
                text: "Do you Really Want to Cancel?",
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
                }
            });
        }
        function percentageCheck(event) {
            if (parseInt(event.target.value + event.key) > 100) {
                event.preventDefault();
            }
        }

        function validateRoundedValue(value, flag) {
            if (flag == 'A') {
                if (value < 100) {
                    vm.cbdtSchemeParameter.C009 = '';
                    vm.errorMessage = "Please enter a valid number";
                } else if (value % 100 !== 0) {
                    vm.cbdtSchemeParameter.C009 = '';  // Clear the field
                    vm.errorMessage = "Please enter a valid number";
                } else {
                    vm.cbdtSchemeParameter.C009 = Math.floor(value / 100) * 100;
                    vm.errorMessage = '';
                }
            } else if (flag == 'M') {
                if (value < 100) {
                    vm.cbdtSchemeParameter.C009.oldValue = '';
                    vm.errorMessage = "Please enter a valid number";
                } else if (value % 100 !== 0) {
                    vm.cbdtSchemeParameter.C009.oldValue = '';  // Clear the field
                    vm.errorMessage = "Please enter a valid number";
                } else {
                    vm.cbdtSchemeParameter.C009.oldValue = Math.floor(value / 100) * 100;
                    vm.errorMessage = '';
                }
            }
        }
    }
})();