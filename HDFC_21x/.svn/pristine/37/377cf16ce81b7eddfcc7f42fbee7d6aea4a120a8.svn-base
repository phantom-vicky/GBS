(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("bondNominationsController", bondNominationsController);

  bondNominationsController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$rootScope",
    "$stateParams",
    "$location",
    "$timeout",
    "bondNominationsService",
    "commonService",
  ];

  function bondNominationsController($scope, $filter, $state, $rootScope, $stateParams, $location, $timeout, bondNominationsService, commonService) {
    var vm = this;
    vm.nomineeList = [];
    vm.totalNomineeSharableAmount = "";
    vm.moduleName = "RBI Bond";
    vm.checkAge = checkAge;
    //        vm.getMaxNoOfNomineeValidation = getMaxNoOfNomineeValidation;
    vm.getModuleName1 = getModuleName1;
    vm.getModifyPantorch = getModifyPantorch;
    vm.getInqModuleName = getInqModuleName;
    vm.getModuleNameVer = getModuleNameVer;
    vm.getblaNoVer = getblaNoVer;
    vm.getsubscriptionIdVer = getsubscriptionIdVer;
    vm.validateInput = validateInput;
    vm.getInqbla = getInqbla;
    vm.getsubscriptionIdInq = getsubscriptionIdInq;
    vm.getNomineeStatusMetaData = getNomineeStatusMetaData;
    vm.modifySubmit = modifySubmit;
    vm.rejectReason = rejectReason;
    vm.getblaNo = getblaNo;
    vm.getsubscriptionIdMod = getsubscriptionIdMod;
    vm.getSharePer = getSharePer;
    vm.calculateSharePercentage = calculateSharePercentage;
    vm.recalculateSharePercentages = recalculateSharePercentages;
    vm.validateNomineeSharableAmount = validateNomineeSharableAmount;
    vm.validateTotalSharableAmount = validateTotalSharableAmount;
    vm.updateTotalNomineeSharableAmount = updateTotalNomineeSharableAmount;
    vm.dateValidation = dateValidation;
    vm.getTotalSharePer = getTotalSharePer;
    vm.addRange = addRange;
    vm.removeRange = removeRange;
    vm.isModifySubmitDisabled = isModifySubmitDisabled;
    //        vm.getModuleName = getModuleName;
    //        vm.validatePoolAcct = validatePoolAcct;
    vm.getTorchDetails = getTorchDetails;
    vm.getInquiryTorchDetails = getInquiryTorchDetails;
    vm.getVerDelTorchDetail = getVerDelTorchDetail;
    vm.verifyDetails = verifyDetails;
    vm.deleteDetails = deleteDetails;

    vm.getState = getState;
    vm.getGuardianDetailsMetaData = getGuardianDetailsMetaData;
    vm.resultDelArray = [];
    vm.resultAddArray = [];
    vm.resultModifyArray = [];
    vm.convertLabelValue = convertLabelValue;
    vm.getAddressByPinCode = getAddressByPinCode;
    vm.getAddressByGuardPinCode = getAddressByGuardPinCode;

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


    vm.today = new Date();
    vm.loginSolDate = $rootScope.globals.currentUser.solDate;
    vm.loginSolId = $rootScope.globals.currentUser.solId;
    vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
    //
    function getGuardianDetailsMetaData() {
      var requestData = {
        referenceId: "RELATION_WITH_MINOR",
      };
      bondNominationsService.getReferenceData(requestData).then(
        function (response) {
          vm.guardianDetailsList = response.data.result;
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }

    vm.relationListMetaData = [
      { label: "Select", value: null },
      { label: "Father", value: "FATHER" },
      { label: "Mother", value: "MOTHER" },
      { label: "Son", value: "SON" },
      { label: "Daughter", value: "DAUGHTER" },
      { label: "Brother", value: "BROTHER" },
      { label: "Sister", value: "SISTER" },
      { label: "Spouse", value: "SPOUSE" },
      { label: "Others", value: "OTHERS" },
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

    vm.yesNoMetaData = [
      { label: "Select", value: null },
      { label: "Yes", value: "Y" },
      { label: "No", value: "N" },
    ];

    vm.statusData = [
      { label: "Select", value: null },
      { label: "NRI", value: "NRI" },
      { label: "Resident", value: "RESIDENT" },
    ];

    if ($state.current.name == "bond_nominations.add_detail") {
      if ($stateParams.obj) {
        vm.schemeName = $stateParams.obj.schemeName;
        vm.bondSubsId = $stateParams.obj.bondSubsId;
        vm.blaNo = $stateParams.obj.blaNo;
        vm.subscriptionAmount = $stateParams.obj.subscriptionAmount;
        //                vm.getTorchDetails('submit');
        vm.getGuardianDetailsMetaData();
        vm.getNomineeStatusMetaData();
      }
    } else if ($state.current.name == "bond_nominations.modify_detail") {
      if ($stateParams.obj) {
        vm.moduleId = $stateParams.obj.moduleId;
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.bondSubsId = $stateParams.obj.bondSubsId;
        vm.blaNo = $stateParams.obj.blaNo;
        vm.modeOfHolding = $stateParams.obj.modeOfHolding;
        vm.nameOfTheBondHolder = $stateParams.obj.nameOfTheBondHolder;
        vm.subscriptionAmount = $stateParams.obj.subscriptionAmount;
        vm.getState();
        vm.getTorchDetails();
        vm.getGuardianDetailsMetaData();
        vm.getNomineeStatusMetaData();
      }
    } else if ($state.current.name == "bond_nominations.verify_detail") {
      if ($stateParams.obj) {
        vm.moduleId = $stateParams.obj.moduleId;
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.bondSubsId = $stateParams.obj.bondSubsId;
        vm.blaNo = $stateParams.obj.blaNo;
        vm.modeOfHolding = $stateParams.obj.modeOfHolding;
        vm.nameOfTheBondHolder = $stateParams.obj.nameOfTheBondHolder;
        vm.subscriptionAmount = $stateParams.obj.subscriptionAmount;
        vm.getVerDelTorchDetail();
        vm.getGuardianDetailsMetaData();
        vm.getState();
        vm.getNomineeStatusMetaData();
        //                                 vm.getModuleNameVer();
      }
    } else if ($state.current.name == "bond_nominations.delete_detail") {
      if ($stateParams.obj) {
        vm.moduleId = $stateParams.obj.moduleId;

        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.bondSubsId = $stateParams.obj.bondSubsId;
        vm.blaNo = $stateParams.obj.blaNo;
        vm.modeOfHolding = $stateParams.obj.modeOfHolding;
        vm.nameOfTheBondHolder = $stateParams.obj.nameOfTheBondHolder;
        vm.subscriptionAmount = $stateParams.obj.subscriptionAmount;
        vm.getVerDelTorchDetail();
        vm.getGuardianDetailsMetaData();
        vm.getModuleNameVer();
        vm.getNomineeStatusMetaData();
      }
    } else if ($state.current.name == "bond_nominations.inquiry_detail") {
      if ($stateParams.obj) {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.moduleId = $stateParams.obj.moduleId;

        vm.schemeName = $stateParams.obj.schemeName;
        vm.bondSubsId = $stateParams.obj.bondSubsId;
        vm.blaNo = $stateParams.obj.blaNo;
        vm.modeOfHolding = $stateParams.obj.modeOfHolding;
        vm.nameOfTheBondHolder = $stateParams.obj.nameOfTheBondHolder;
        vm.subscriptionAmount = $stateParams.obj.subscriptionAmount;
        vm.getInquiryTorchDetails();
        vm.getGuardianDetailsMetaData();
        vm.getState();
        vm.getNomineeStatusMetaData();
      }
    }

    function checkAge(item) {
      if (typeof item.C016 !== "undefined") {
        var dob = new Date(item.C016.oldValue);
        var today = new Date();

        // Calculate the exact age in years
        var age = today.getFullYear() - dob.getFullYear();

        // Check if the birthday hasn't occurred yet this year, and adjust the age
        var monthDiff = today.getMonth() - dob.getMonth();
        var dayDiff = today.getDate() - dob.getDate();
        if (monthDiff < 0 || (monthDiff === 0 && dayDiff < 0)) {
          age--;
        }

        // If age is exactly 18
        if (age > 18) {
          Swal.fire("Not a Minor!", "Go back to the previous screen", "info");
          item.C016.oldValue = "";
        }
      }
    }

    function addRange() {
      vm.loader = true;
      var requestData = {
             schemeCode: vm.schemeCode,
           };
      bondNominationsService.getMaxNoOfNomineeValidation(requestData).then(
        function (response) {
          vm.loader = false;
          vm.maxNoOfEmp = response.data.result;
          vm.maxNoOfEmpl = response.data.result[0].maxNoOfNominee;
          if (vm.nomineeList.length >= vm.maxNoOfEmpl) {
            Swal.fire("Input should not exceed scheme parameter value");
          } else {
            if (isModifyScreen()) {
              vm.nomineeList.push({
                C012: { oldValue: "", newValue: null },
                C031: { oldValue: "", newValue: null }
              });
            } else {
              vm.nomineeList.push({ C012: "", C031: "" });
            }
            recalculateSharePercentages();
          }
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }
    function getNomineeStatusMetaData() {
          var requestData = {
            referenceId: "NOMINEE_STATUS",
          };
          bondNominationsService.getReferenceData(requestData).then(
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
    function getState() {
      vm.listType = "STATES OF INDIA";
      var requestData = {
        referenceId: vm.listType,
      };
      bondNominationsService.getCommonMetaDatList(requestData).then(
        function (response) {
          vm.stateListValues = response.data.result;
          vm.stateList = [];
          angular.forEach(vm.stateListValues, function (item) {
            vm.stateList.push({ subCodeDescription: item.DESCRIPTION, subCode: item.SUBCODE });
          });
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }

    function removeRange(ID, index) {
      vm.nomineeList.splice(index, 1);
      recalculateSharePercentages(true);

      if (ID) {
        vm.resultDelArray.push(convertToColumnValuesArrayList({ ID: ID }));
      }

      vm.enableDelete = true;
      validateTotalSharableAmount(true, false);

      if (vm.bondNominationsForm) {
        vm.bondNominationsForm.$setDirty();
      }
    }

    function isModifySubmitDisabled() {
      if (vm.bondNominationsForm.$invalid || vm.debitBankAccountNoErrMsg) {
        return true;
      }

      var hasDeletions = vm.resultDelArray && vm.resultDelArray.length > 0;
      var hasRemainingNominees = vm.nomineeList && vm.nomineeList.length > 0;

      if (hasDeletions && !hasRemainingNominees) {
        return false;
      }

      if (!hasDeletions && vm.bondNominationsForm.$pristine) {
        return true;
      }

      if (hasRemainingNominees) {
        var subscriptionAmt = parseAmountValue(vm.subscriptionAmount);
        var totalSharableAmt = parseAmountValue(vm.totalNomineeSharableAmount);
        var totalsMismatch =
          isNaN(subscriptionAmt) ||
          isNaN(totalSharableAmt) ||
          Math.abs(totalSharableAmt - subscriptionAmt) > 0.001;

        return vm.totalPer != 100 || totalsMismatch;
      }

      return !hasDeletions && vm.bondNominationsForm.$pristine;
    }
vm.minorChecking=minorChecking;
function minorChecking(id,valueDate,item){
   var input = document.getElementById(id);
   if (!input) return;
   let formattedValue = '';
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
  if (!item.C014) {
    item.C014 = {};
  }
  if (age < 18) {
    item.C014.oldValue='';
    item.C014.oldValue = "Y";
  } else {
    item.C014.oldValue='';
    item.C014.oldValue = "N";
  }
    let index = id.replace("minorDOB", ""); // → "5"
    index = parseInt(index); // convert to number
    vm.onNomineeMinorChange(item.C014.oldValue,index);
};
vm.onNomineeMinorChange = function(value,index) {
        // remove or reset nominee minor data for that index
        if(null!=value&&value==='N'){
         let nominee = vm.nomineeList[index];
            ["C017","C018","C019","C020","C021","C022","C026","C024","C025","C023","C030"]
              .forEach(key => {
                if (!nominee[key]) nominee[key] = {}; // ensure object
                nominee[key].oldValue = "";
              });}
};
    function isModifyScreen() {
      return $state.current.name === "bond_nominations.modify_detail";
    }
function setSharePercentage(item, isModify) {
    var sharableAmt = parseAmountValue(getSharableAmountValue(item, isModify));
    var subscriptionAmt = parseAmountValue(vm.subscriptionAmount);
    var sharePer = "";

    if (!isNaN(sharableAmt) && sharableAmt > 0 &&
        !isNaN(subscriptionAmt) && subscriptionAmt > 0) {

        // Actual percentage - NO ROUNDING
        sharePer = (sharableAmt / subscriptionAmt) * 100;
    }

    if (isModify) {
        if (!item.C012) {
            item.C012 = {};
        }

      item.C012.oldValue = parseFloat(sharePer || 0);

    } else {
        // If C012 is used directly for display
        item.C012 = sharePer;
    }

    return sharePer;
}
    function calculateSharePercentage(item, isModify) {
      var mode = typeof isModify === "boolean" ? isModify : isModifyScreen();
      var sharePer = setSharePercentage(item, mode);
      recalculateSharePercentages(mode);
      return sharePer;
    }

    function recalculateSharePercentages(isModify) {
      var mode = typeof isModify === "boolean" ? isModify : isModifyScreen();
      angular.forEach(vm.nomineeList, function (nomineeItem) {
        setSharePercentage(nomineeItem, mode);
      });
      getTotalSharePer();
      updateTotalNomineeSharableAmount(mode);
    }

    function updateTotalNomineeSharableAmount(isModify) {
      var mode =
        typeof isModify === "boolean"
          ? isModify
          : ($state.current.name !== "bond_nominations.add_detail");
      var list = vm.nomineeList;
      if (
        $state.current.name === "bond_nominations.verify_detail" ||
        $state.current.name === "bond_nominations.delete_detail"
      ) {
        list = vm.nomineeList1 && vm.nomineeList1.length ? vm.nomineeList1 : vm.nomineeList;
      }

      var total = 0;
      var hasValue = false;
      angular.forEach(list || [], function (nomineeItem) {
        var val = "";
       /* if (nomineeItem && nomineeItem.C031 != null) {
          if (angular.isObject(nomineeItem.C031)) {

val = nomineeItem.C031.newValue != null && nomineeItem.C031.newValue !== ""
    ? nomineeItem.C031.newValue
    : (nomineeItem.C031.oldValue != null ? nomineeItem.C031.oldValue : "");
          } else {
            val = nomineeItem.C031;
          }
        }*/
        if (nomineeItem && nomineeItem.C028 &&
            nomineeItem.C028.oldValue === 'Y' &&
            nomineeItem.C028.newValue === 'Y') {

            return;

        } else if (nomineeItem && nomineeItem.C031 != null) {

            if (angular.isObject(nomineeItem.C031)) {

                val = nomineeItem.C031.newValue != null &&
                      nomineeItem.C031.newValue !== ""
                    ? nomineeItem.C031.newValue
                    : (nomineeItem.C031.oldValue != null
                        ? nomineeItem.C031.oldValue
                        : "");

            } else {
                val = nomineeItem.C031;
            }
        }
        var amt = parseAmountValue(val);
        if (!isNaN(amt)) {
          total += amt;
          hasValue = true;
        }
      });

      vm.totalNomineeSharableAmount = hasValue ? total : "";
    }

    function getSharePer(item) {
      var isObjectValue = angular.isObject(item.C012);
      var shareValue = isObjectValue ? item.C012.oldValue : item.C012;

      if (shareValue == 0) {
        vm.sharePerCutOffErrMsg = "Entered Input cannot be zero";
        if (isObjectValue) {
          item.C012.oldValue = "";
        } else {
          item.C012 = "";
        }
      } else if (shareValue > 100) {
        vm.sharePerCutOffErrMsg = "Entered Input should not exceed 100";
        if (isObjectValue) {
          item.C012.oldValue = "";
        } else {
          item.C012 = "";
        }
      } else {
        vm.sharePerCutOffErrMsg = "";
      }
    }
function getTotalSharePer() {
    vm.totalPer = 0;

    for (var i = 0; i < vm.nomineeList.length; i++) {

        var shareValue = 0;

        if (typeof vm.nomineeList[i].C012 != "undefined") {
            shareValue = angular.isObject(vm.nomineeList[i].C012)
                ? vm.nomineeList[i].C012.oldValue
                : vm.nomineeList[i].C012;
        }

        vm.totalPer += parseFloat(shareValue) || 0;
    }

    // Avoid floating-point issue
    var totalPer = Number(vm.totalPer.toFixed(10));

    if (totalPer !== 100) {
        vm.shareTotalPerCutOffErrMsg =
            "Total Share Percentage should be 100";
    } else {
        vm.shareTotalPerCutOffErrMsg = "";
        vm.totalPer = 100;
    }
}

vm.truncateSharePercentage = function(item) {
    var value = parseFloat(item.C012.oldValue);

    if (!isNaN(value)) {
        item.C012.oldValue =
            (Math.floor(value * 100) / 100).toFixed(2);
    }
};

    function parseAmountValue(value) {
      if (value === "" || value == null) {
        return NaN;
      }
      return parseFloat(String(value).replace(/,/g, ""));
    }

    function getSharableAmountValue(item, isModify) {
      if (!item || item.C031 == null || item.C031 === "") {
        return "";
      }
      if (angular.isObject(item.C031)) {
        return item.C031.oldValue != null && item.C031.oldValue !== "" ? item.C031.oldValue : "";
      }
      return item.C031;
    }

    function clearSharableAmountValue(item, isModify) {
      if (isModify || angular.isObject(item.C031)) {
        if (!item.C031) {
          item.C031 = {};
        }
        item.C031.oldValue = "";
      } else {
        item.C031 = "";
      }
    }

    function validateNomineeSharableAmount(item, isModify, showPopup) {
      var enteredAmt = getSharableAmountValue(item, isModify);
      var subscriptionAmt = parseAmountValue(vm.subscriptionAmount);
      var errorMsg = "";

      if (enteredAmt === "" || enteredAmt == null) {
        vm.nomineeSharableAmtErrMsg = "";
        calculateSharePercentage(item, isModify);
        validateTotalSharableAmount(isModify, showPopup);
        return true;
      }

      var amount = parseAmountValue(enteredAmt);

      if (isNaN(amount)) {
        errorMsg = "Enter a valid Nominee Sharable Amount";
      } else if (amount < 1000) {
        errorMsg = "Nominee Sharable Amount should not be less than 1000";
      }
      else if (amount % 1000 !== 0) {
                errorMsg = "Nominee Sharable Amount should be in multiples of 1000";
                  item.nomineeSharableAmount = "";
            }

     /*  else if (!isNaN(subscriptionAmt) && subscriptionAmt > 0 && amount > subscriptionAmt) {
        errorMsg = "Nominee Sharable Amount cannot exceed Subscription Amount";
      }*/

      if (errorMsg) {
        vm.nomineeSharableAmtErrMsg = errorMsg;
        clearSharableAmountValue(item, isModify);
        calculateSharePercentage(item, isModify);
        if (showPopup) {
          Swal.fire("WARNING!", errorMsg, "error");
        }
        validateTotalSharableAmount(isModify, false);
        return false;
      }

      vm.nomineeSharableAmtErrMsg = "";
      calculateSharePercentage(item, isModify);
      return validateTotalSharableAmount(isModify, showPopup);
    }

    function validateTotalSharableAmount(isModify, showPopup) {
      updateTotalNomineeSharableAmount(isModify);

      if (!vm.nomineeList || vm.nomineeList.length === 0) {
        vm.nomineeSharableTotalErrMsg = "";
        return true;
      }

      var total = 0;
      var filledCount = 0;
      var nomineeCount = vm.nomineeList.length;

      angular.forEach(vm.nomineeList, function (nomineeItem) {
        var val = getSharableAmountValue(nomineeItem, isModify);
        if (val !== "" && val != null) {
          var amt = parseAmountValue(val);
          if (!isNaN(amt)) {
            total += amt;
            filledCount++;
          }
        }
      });

      var subscriptionAmt = parseAmountValue(vm.subscriptionAmount);
      if (isNaN(subscriptionAmt) || subscriptionAmt <= 0) {
        vm.nomineeSharableTotalErrMsg = "";
        return true;
      }

      // 1 nominee: validate as soon as amount is entered
      // 2+ nominees: validate only after every nominee has an amount
      var readyToValidate = nomineeCount === 1 ? filledCount === 1 : filledCount === nomineeCount;
      if (!readyToValidate) {
        vm.nomineeSharableTotalErrMsg = "";
        return true;
      }

      if (Math.abs(total - subscriptionAmt) > 0.001) {
        vm.nomineeSharableTotalErrMsg = "Total Nominee Sharable Amount should be equal to Subscription Amount";
        /*if (showPopup) {
          Swal.fire("WARNING!", vm.nomineeSharableTotalErrMsg, "error");
        }*/
        return false;
      }

      vm.nomineeSharableTotalErrMsg = "";
      return true;
    }

    //        function validatePoolAcct(accountNum, flag, errorFlag) {
    //
    //
    //                                  if (accountNum) {
    //                    				vm.accountNum = accountNum.toUpperCase();
    //                    				if (flag == 'Y') {
    //                    					commonService.getCustomAcctInqApi(vm.accountNum, function (response) {
    //                    					  vm.accValidate = response.data;
    //                    						if (vm.accValidate[0].status == 'F') {
    //                    							vm.message = "INVALID ACCOUNT NUMBER";
    //                    						    vm.operativeAccountBranchId = "";
    //                    							if (errorFlag == 'debitBankAccountNo') {
    //                    								vm.debitBankAccountNoErrMsg = vm.message;
    //
    //                    							}
    //
    //
    //                    						} else {
    //                    							if (errorFlag == 'debitBankAccountNo') {
    //                    								vm.debitBankAccountNoErrMsg = '';
    //                    								vm.operativeAccountBranchId = vm.accValidate[0].solID;
    //                    							}
    //
    //                    						}
    //                    					}, function (err) {
    //                    					   vm.operativeAccountBranchId = "";
    //                                           handleErrorResponse(err);
    //                                           vm.loader = false;
    //                                           });
    //                    				}
    //
    //                    				}
    //                    				else if((typeof accountNum == "undefined" || accountNum == "") && errorFlag == 'debitBankAccountNo' ){
    //                                     vm.debitBankAccountNoErrMsg = '';
    //                                      }
    //
    //
    //
    //        }

    function getTorchDetails() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        blaNo: vm.blaNo,
        bondSubsId: vm.bondSubsId,
      };
      bondNominationsService.getmodifyDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.subDetail = response.data.result.rbiBondNominationDtoList;
          vm.bondHolderName = response.data.result.rbiBondNominationDtoList[0].jointHolderName;
          vm.modeOfHolding = response.data.result.rbiBondNominationDtoList[0].modeOfHolding;
          vm.accountOpenDate = response.data.result.accOpenDate;
          vm.maturityDate = response.data.result.maturityDate;
          vm.deleteRemark = response.data.result.deleteRemark;
          vm.nomDet = response.data.result.rbiBondNominationDtoNomDetList;
          for (var i = 0; i < vm.nomDet.length; i++) {
            var obj = vm.nomDet[i].masterOldNewValueModel;
                  if (obj.C010 && obj.C010.oldValue) {
                   if (obj.C010.oldValue.length < 6 || obj.C010.oldValue.length > 10) {
                       obj.C010.oldValue = '';
                   }
               }
               if (!obj.C031) {
                 obj.C031 = { oldValue: null, newValue: null };
               }
           vm.nomineeList.push(obj);
          }
          vm.enableDelete = true;
          if (vm.nomineeList.length > 0) {
            angular.forEach(vm.nomineeList, function (item, key) {
              let items = [];
              if (typeof item.C016 != "undefined") {
                if (item.C016.oldValue != null) {
//                  item.C016.oldValue = convertToDateObject(item.C016.oldValue);
                  item.C016.oldValue = convertToDateObject(
                    typeof item.C016.oldValue === 'string' && /^\d{4}-\d{2}-\d{2}/.test(item.C016.oldValue)
                      ? (function(d) {
                          // Extract yyyy-MM-dd part safely
                          let parts = d.split(" ")[0].split("-");
                          let yyyy = parts[0];
                          let mm = parts[1];
                          let dd = parts[2];
                          return dd + "/" + mm + "/" + yyyy; // dd/MM/yyyy format
                        })(item.C016.oldValue)
                      : item.C016.oldValue
                  );

                } else {
                  items.C016 = null;
                }
              } else {
                items.C016 = null;
              }
            });
          }
          angular.forEach(vm.nomineeList, function (item) {
            item.C009.oldValue = item.C009.oldValue;
            item.C024.oldValue = item.C024.oldValue;
          });

          recalculateSharePercentages(true);

          if (vm.nomineeList.length <= 0) {
            Swal.fire("No Nominee exists", "Do you want to add new nominee!","info") ;
            $state.go("bond_nominations.modify_details");
          }
        },
        function (error) {
          handleErrorResponse(error);
          $state.go("bond_nominations.modify");
          vm.loader = false;
        }
      );
    }

    function getInquiryTorchDetails() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        blaNo: vm.blaNo,
        bondSubsId: vm.bondSubsId,
      };
      bondNominationsService.getInquiryTorchDetails(requestData).then(
        function (response) {
          vm.loader = false;

          vm.subDetail = response.data.result.rbiBondNominationDtoSubDetList;
          vm.modeOfHolding = response.data.result.rbiBondNominationDtoSubDetList[0].modeOfHolding;
          vm.bondHolderName = response.data.result.rbiBondNominationDtoSubDetList[0].bondHolderName;
          vm.accOpenDate = response.data.result.auditDetails.accOpenDate;
          vm.maturityDate = response.data.result.auditDetails.maturityDate;
          vm.nomineeList = response.data.result.rbiBondNominationDtoNomDetList;
          vm.auditInfo = response.data.result.auditDetails;
          vm.verificationDetails = vm.auditInfo.previousOperationType;
          updateTotalNomineeSharableAmount(true);
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
          $state.go("bond_nominations.inquiry");
        }
      );
    }

    function getVerDelTorchDetail() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        blaNo: vm.blaNo,
        bondSubsId: vm.bondSubsId,
        flag: "D"
      };
      bondNominationsService.getVerDelDetails(requestData).then(
        function (response) {
          vm.loader = false;

          //vm.nomineeList = response.data.result.oldNewValueMap;
          vm.verificationDetails = response.data.result.preOrpTyp;
          vm.nomineeList1 = [];
          angular.forEach(response.data.result.oldNewValueMap, function (arrayItem) {
            vm.nomineeList1.push(arrayItem);
          });
          vm.modeOfHolding = response.data.result.rbiBondNominationDtoSubsIdList[0].modeOfHolding;
          vm.modeOfHolding1 = response.data.result.rbiBondNominationDtoSubsIdList[0].modeOfHolding1;
          vm.bondHolderName = response.data.result.rbiBondNominationDtoSubsIdList[0].bondHolderName;

          vm.accOpenDate = response.data.result.accOpenDate;
          vm.maturityDate = response.data.result.maturityDate;
          vm.modifiedBy = response.data.result.modifiedBy;
          vm.modifiedDate = response.data.result.modifiedDate;
          vm.verifiedBy = response.data.result.verifiedBy;
          vm.verifiedDate = response.data.result.verifiedDate;
          vm.createdBy = response.data.result.createdBy;
          vm.createdDate = response.data.result.createdDate;
          vm.verifiedStatus = response.data.result.verifiedStatus;
          updateTotalNomineeSharableAmount(true);
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function dateValidation() {
      vm.dateToOptions = {
        //                                              minDate: vm.subscriptionFromDate,
        maxDate: vm.today,
      };
      vm.dateOptions = {
        maxDate: vm.today,
      };
    }

    var todayDate = new Date();
    var maxDatee = new Date(todayDate.getFullYear() - 18, todayDate.getMonth(), todayDate.getDate());

    vm.dobCalenderOptionsNewGuardian = {
      minDate: null,//maxDatee,
      maxDate: todayDate,

      // Add other datepicker options here if needed
    };

    function verifyDetails() {
//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to Really Verify this record?",
//          type: "warning",
//          showCancelButton: true,
//          cancelButtonClass: "btn-cancel",
//          confirmButtonClass: "btn-primary",
//          confirmButtonText: "Yes",
//          closeOnConfirm: false,
//        },
//        function () {


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
            schemeCode: vm.schemeCode,
            bondSubsId: vm.bondSubsId,
          };
          bondNominationsService.verifyDetails(requestData).then(
            function (response) {
              vm.loader = false;
              vm.model = response.data.result;
              Swal.fire("Verified!", "Bond Nomination Verified Successfully", "success");
              $state.go("bond_nominations.inquiry");
            },
            function (error) {
              vm.loader = false;
              handleErrorResponse(error);
              $state.go("bond_nominations.verify");
            }
          );
          }
        }
      );
    }

    function validateInput(event, id) {
      const textBox = document.getElementById(id);

      var charCode = event.which || event.keyCode;
      var charTyped = String.fromCharCode(charCode);

      if (textBox) {
        const cursorPosition = textBox.selectionStart;
        if (cursorPosition == 0 && charTyped === " ") {
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

    function rejectReason() {
      angular.element("#permissionMappingDelete").modal("toggle");
    }

    function deleteDetails() {
//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to Really Delete this record?",
//          type: "warning",
//          showCancelButton: true,
//          cancelButtonClass: "btn-cancel",
//          confirmButtonClass: "btn-primary",
//          confirmButtonText: "Yes",
//          closeOnConfirm: false,
//        },
//        function () {

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
            schemeCode: vm.schemeCode,
            bondSubsId: vm.bondSubsId,
            deleteRemark: vm.deleteRemark,
          };
          bondNominationsService.deleteDetails(requestData).then(
            function (response) {
              if (response.data.message == "SUCCESS") {
                Swal.fire("Rejected!", "Bond Nomination Rejected Successfully", "success");
                $state.go("bond_nominations.inquiry");
              } else {
                Swal.fire("WARNING!", response.data.message, "error");
                $state.go("bond_nominations.verify");
              }
              const backdrop = document.querySelector(".modal-backdrop");
              if (backdrop) {
                backdrop.parentNode.removeChild(backdrop);
                document.body.classList.remove("modal-open");
              }
            },
            function (error) {
              vm.loader = false;
              handleErrorResponse(error);
              const backdrop = document.querySelector(".modal-backdrop");
              if (backdrop) {
                backdrop.parentNode.removeChild(backdrop);
                document.body.classList.remove("modal-open");
              }
              location.href = "#!/bond_nominations/action/inquiry";
            }
          );
          }
        }
      );
    }

    function convertLabelValue(list) {
      var resultList = [];
      for (var item in list) {
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

    function modifySubmit() {
//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to Really Add/Modify this record?",
//          type: "warning",
//          showCancelButton: true,
//          cancelButtonClass: "btn-cancel",
//          confirmButtonClass: "btn-primary",
//          confirmButtonText: "Yes",
//          closeOnConfirm: false,
//        },
//        function () {


                Swal.fire({
                  title: "Are you sure?",
                  text: "Do you want to Really Add/Modify this record?",
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
          angular.forEach(vm.nomineeList, function (item, key) {
            let items = [];
            if (typeof item.C016 != "undefined") {
              if (item.C016.oldValue != null) {
                items.C016 = $filter("date")(item.C016.oldValue, "dd-MMM-yyyy");
              } else {
                items.C016 = null;
              }
            } else {
              items.C016 = null;
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
            items.C031 = typeof item.C031 === "undefined" ? null : item.C031.oldValue;
            items.C013 = typeof item.C013 === "undefined" ? null : item.C013.oldValue;
            items.C014 = typeof item.C014 === "undefined" ? null : item.C014.oldValue;
            items.C015 = typeof item.C015 === "undefined" ? null : item.C015.oldValue;
            items.C017 = typeof item.C017 === "undefined" ? null : item.C017.oldValue;
            items.C018 = typeof item.C018 === "undefined" ? null : item.C018.oldValue;
            items.C019 = typeof item.C019 === "undefined" ? null : item.C019.oldValue;
            items.C020 = typeof item.C020 === "undefined" ? null : item.C020.oldValue;
            items.C021 = typeof item.C021 === "undefined" ? null : item.C021.oldValue;
            items.C022 = typeof item.C022 === "undefined" ? null : item.C022.oldValue;
            items.C023 = typeof item.C023 === "undefined" ? null : item.C023.oldValue;
            items.C024 = typeof item.C024 === "undefined" ? null : item.C024.oldValue;
            items.C025 = typeof item.C025 === "undefined" ? null : item.C025.oldValue;
            items.C026 = typeof item.C026 === "undefined" ? null : item.C026.oldValue;
            items.C029 = typeof item.C029 === "undefined" ? null : item.C029.oldValue;

            //														items.C028 = typeof item.C028 === "undefined" ? null : item.C028.oldValue;
            if (typeof item.ID != "undefined") {
              items.ID = item.ID.oldValue;
            } else {
              items.ID = null;
            }
            items.C030 = typeof item.C030 === "undefined" ? null : item.C030.oldValue;

            vm.nominationDetailsAdd.push(items);
          });

          angular.forEach(vm.nominationDetailsAdd, function (item) {
            if (item.ID) {
              vm.resultArray = [];
              vm.resultModifyArray.push(convertToColumnValuesArrayList(item));
            } else {
              vm.resultArray1 = [];
              vm.resultAddArray.push(convertToColumnValuesArrayList(item));
            }
          });
          vm.loader = true;
          var requestData = {
            moduleId: vm.moduleId,
            blaNo: vm.blaNo,
            bondSubsId: vm.bondSubsId,
            schemeCode: vm.schemeCode,
            nominationAddDtoList: vm.resultAddArray,
            nominationModifyDtoList: vm.resultModifyArray,
            nominationDelDtoList: vm.resultDelArray,
          };
          bondNominationsService.modifySubmit(requestData).then(
            function (response) {
              vm.loader = false;
              vm.model = response.data.result;
              Swal.fire("Modified!", "Bond Nomination Modified Successfully", "success");
              $state.go("bond_nominations.inquiry");
            },
            function (error) {
              vm.loader = false;
              handleErrorResponse(error);
              $state.go("bond_nominations.modify");
            }
          );
          }
        }
      );
    }

    const convertToColumnValuesArrayList = (objectToBeConvert) => {
      if (typeof objectToBeConvert === "object") {
        let resultArrayOther = [];
        for (let key of Object.keys(objectToBeConvert)) {
          // Check for null or undefined, not just truthiness
          if (objectToBeConvert[key] !== undefined) {
            resultArrayOther.push({ colName: key, value: objectToBeConvert[key] });
          }
        }
        return resultArrayOther;
      } else {
        throw "Only objects can be converted";
      }
    };

    function getModuleName1() {
      vm.loader = true;

      var requestData = {};
      bondNominationsService.getTorchDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result.rbiSchemeList;
          angular.element("#schemeModel1").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function getblaNo() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        schemeName: vm.schemeName,
        panNum: vm.panNum,
        bondHolderName: vm.bondHolderName,
        blaNo: vm.blaNo,
      };
      bondNominationsService.blaNo(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaDataList = response.data.result.torchValue;
          angular.element("#blaModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function getsubscriptionIdMod() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        schemeName: vm.schemeName,
        panNum: vm.panNum,
        bondHolderName: vm.bondHolderName,
        blaNo: vm.blaNo,
        bondSubsId: vm.subscriptionId,
      };
      bondNominationsService.getsubscriptionIdMod(requestData).then(
        function (response) {
          vm.loader = false;
          vm.subscriptionList = response.data.result.torchValue;
          angular.element("#subscriptionModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function getInqModuleName() {
      vm.loader = true;

      var requestData = {};
      bondNominationsService.getInqTorchDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result;
          angular.element("#schemeModel1").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function getInqbla() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        schemeName: vm.schemeName,
        panNum: vm.panNum,
        bondHolderName: vm.bondHolderName,
        blaNo: vm.blaNo,
      };
      bondNominationsService.getblaNoInq(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaDataList = response.data.result.torchValue;
          angular.element("#blaModel2").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function getModuleNameVer() {
      vm.loader = true;

      var requestData = {
      flag: ''
      };
      bondNominationsService.getVerDelDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result.torchValue;
          angular.element("#schemeModel").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function getblaNoVer() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        schemeName: vm.schemeName,
        blaNo: vm.blaNo,
        flag: "B"
      };
      bondNominationsService.getVerDelDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaDataList = response.data.result.torchValue;
          angular.element("#blaModel1").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function getsubscriptionIdVer() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        schemeName: vm.schemeName,
        blaNo: vm.blaNo,
        bondSubsId: vm.subscriptionId,
        flag:'S'
      };
      bondNominationsService.getVerDelDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.subscriptionList = response.data.result.torchValue;
          angular.element("#subscriptionModel1").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function getsubscriptionIdInq() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        schemeName: vm.schemeName,
        blaNo: vm.blaNo,
        bondSubsId: vm.subscriptionId,
      };
      bondNominationsService.getsubscriptionIdInq(requestData).then(
        function (response) {
          vm.loader = false;
          vm.subscriptionList = response.data.result.torchValue;
          angular.element("#subscriptionModel1").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function getModifyPantorch() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        bondHolderName: vm.bondHolderName,
        panNum: vm.panNum,
      };
      bondNominationsService.getPanTorchDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.panDataList = response.data.result;
          angular.element("#pan1").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }
    vm.getInqPantorch=getInqPantorch;
 function getInqPantorch() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        schemeName: vm.schemeName,
         bondHolderName: vm.bondHolderName,
        panNum: vm.panNum
      };
      bondNominationsService.inquiryPanTorch(requestData).then(
        function (response) {
          vm.loader = false;
          vm.PanTorch = response.data.result;
          angular.element("#panModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }
    vm.getRemarksList = function () {
      vm.referenceId = "056";
      var requestData = { referenceId: vm.referenceId };

      bondNominationsService.getRemarksList(requestData).then(
        function (response) {
          vm.remarksList = response.data.result || []; // Ensure it's an array
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }
function getAddressByPinCode(pinCode,index) {
                if (pinCode && pinCode.length > 0 && pinCode.length < 6) {
                    vm.nomineeList[index].C010.oldValue = '';
                    return false;
                }
                vm.loader = true;
              // Include the pin code in the requestData object
              var requestData = {
                pinCode: pinCode,
              };

              // Call the fetchState method and pass the requestData with pinCode
                bondNominationsService.getAddressByPinCode(requestData).then(function (response) {
                  vm.loader = false;
                  // Check if response contains the expected data
                  if (response.data.result) {
                    vm.cbsDetail = response.data.result[0];
                    vm.nomineeList[index].C009  = { oldValue: vm.cbsDetail.bondHldState};
                    vm.nomineeList[index].C008  = { oldValue: vm.cbsDetail.bondHldCity};
                  } else {
                    vm.errmsgSingle = "No data found for the given pin code Enter Manually";
                  }
                })
                .catch(function (error) {
                  vm.loader = false;
                  handleErrorResponse(error); // Handle any error that occurs
                  vm.errmsgSingle = "No data found for the given pin code Enter Manually";
                  vm.nomineeList[index].C009 = { oldValue: "" };
                  vm.nomineeList[index].C008 = { oldValue: "" };
                });
            }
         function getAddressByGuardPinCode(pinCode,index) {
              vm.loader = true;

              // Include the pin code in the requestData object
              var requestData = {
                pinCode: pinCode,
              };

              // Call the fetchState method and pass the requestData with pinCode
                bondNominationsService.getAddressByPinCode(requestData).then(function (response) {
                  vm.loader = false;
                  // Check if response contains the expected data
                  if (response.data.result) {
                    vm.cbsDetail = response.data.result[0];
                    vm.nomineeList[index].C023  = { oldValue: vm.cbsDetail.bondHldState};
                    vm.nomineeList[index].C022  = { oldValue: vm.cbsDetail.bondHldCity};
                  } else {
                    vm.errmsgSingle = "No data found for the given pin code Enter Manually";
                  }
                })
                .catch(function (error) {
                  vm.loader = false;
                  handleErrorResponse(error); // Handle any error that occurs
                  vm.errmsgSingle = "No data found for the given pin code Enter Manually";
                  vm.nomineeList[index].C023 = { oldValue: "" };
                  vm.nomineeList[index].C022 = { oldValue: "" };
                });
            }
  }
})();
