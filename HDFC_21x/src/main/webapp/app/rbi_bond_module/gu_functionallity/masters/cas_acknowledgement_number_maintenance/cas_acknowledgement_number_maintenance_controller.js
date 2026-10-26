(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("casAckNumMaintenanceController", casAckNumMaintenanceController);

  casAckNumMaintenanceController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$rootScope",
    "$stateParams",
    "$location",
    "casAckNumMaintenanceService",
    "commonService",
  ];

  function casAckNumMaintenanceController($scope, $filter, $state, $rootScope, $stateParams, $location, casAckNumMaintenanceService, commonService) {
    var vm = this;

    vm.isChecked = false;
    vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
    vm.showCancelConfirmation = showCancelConfirmation;
    vm.decimalConvert = decimalConvert;
    vm.dateValidation = dateValidation;
    vm.currentDate = new Date();
    vm.validateInput = validateInput;

    vm.currentPage = "";
    vm.itemsPerPage = "";
    //Add Functions
    vm.submitAddForm = submitAddForm;
    vm.getAddDet = getAddDet;
    vm.getSubsDate = getSubsDate;
    vm.getBondSubsId = getBondSubsId;
    vm.getModuleName = getModuleName;
    vm.showPopup1 = showPopup1;
    vm.subsIdFlg = "";
    vm.dateFlg = "";
    vm.myFunction = myFunction;
    vm.casAckDateValidation = casAckDateValidation;
    vm.flg = "";
    vm.casAckDate = "";
    vm.casAckNum = "";

    //Verify and Delete Functions
    vm.submitVerifyForm = submitVerifyForm;
    vm.getVerifyDeleteSelect = getVerifyDeleteSelect;
    vm.getVerifyDeleteTorch = getVerifyDeleteTorch;
    vm.submitDeleteForm = submitDeleteForm;
    vm.uniqueAckNum = uniqueAckNum;
    //Inquiry Functions
    vm.getInquiryTorch = getInquiryTorch;
    vm.getInquirySelect = getInquirySelect;

    //Modify Functions
    vm.modifySubmit = modifySubmit;
    vm.modifySelectDetails = modifySelectDetails;
    vm.getBondSubsIdModify = getBondSubsIdModify;
    vm.getModifyTorch = getModifyTorch;
    vm.getSubsDateVerify = getSubsDateVerify;
    vm.getSubsDateInq = getSubsDateInq;
    vm.getSubsDateModify = getSubsDateModify;
    vm.getSubsDateDel = getSubsDateDel;
    vm.rejectReason = rejectReason;
    vm.getEffectFromDate = getEffectFromDate;
    vm.validateAccNumCbs = validateAccNumCbs;
    vm.validateAccNumCbsM = validateAccNumCbsM;
    vm.showPopup = showPopup;
    vm.panPattern = /[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}/;
    vm.emailPattern = /[^@]+@[^@]+\.[a-zA-Z]{2,6}/;

    if ($state.current.name == "cas_acknowledgement_number_maintenance.add_detail") {
      if ($stateParams.obj) {
        vm.schemeName = $stateParams.obj.schemeName;
        vm.subsDate = $stateParams.obj.subsDate;
        vm.schemeCode = $stateParams.obj.schemeCode;
        getAddDet();
      } else {
        $state.go("cas_acknowledgement_number_maintenance.add");
      }
    } else if ($state.current.name == "cas_acknowledgement_number_maintenance.modify_detail") {
      if ($stateParams.obj) {
        vm.schemeName = $stateParams.obj.schemeName;
        vm.subsDate = $stateParams.obj.subsDate;
        vm.subsId = $stateParams.obj.subsId;
        if ((vm.subsId != "") & (vm.subsId != null) & (vm.subsDate != null)) {
          vm.subsFlg = "N";
        } else {
          vm.subsFlg = "Y";
        }
        modifySelectDetails();
      } else {
        $state.go("cas_acknowledgement_number_maintenance.modify");
      }
    } else if ($state.current.name == "cas_acknowledgement_number_maintenance.verify_detail") {
      if ($stateParams.obj) {
        vm.schemeName = $stateParams.obj.schemeName;
        vm.subsDate = $stateParams.obj.subsDate;
        vm.schemeCode = $stateParams.obj.schemeCode;
        getVerifyDeleteSelect("V");
      } else {
        $state.go("cas_acknowledgement_number_maintenance.verify");
      }
    } else if ($state.current.name == "cas_acknowledgement_number_maintenance.delete_detail") {
      if ($stateParams.obj) {
        vm.schemeName = $stateParams.obj.schemeName;
        vm.subsDate = $stateParams.obj.subsDate;
        getVerifyDeleteSelect("D");
      } else {
        $state.go("cas_acknowledgement_number_maintenance.delete");
      }
    } else if ($state.current.name == "cas_acknowledgement_number_maintenance.inquiry_detail") {
      if ($stateParams.obj) {
        vm.schemeName = $stateParams.obj.schemeName;
        vm.subsDate = $stateParams.obj.subsDate;
        vm.schemeCode = $stateParams.obj.schemeCode;
        getInquirySelect();
      } else {
        $state.go("cas_acknowledgement_number_maintenance.inquiry");
      }
    }
    function optionToggled(flag) {
      vm.isAllSelected = [];
      if (flag == "ALL") {
        if (vm.selectedAllItem) {
          angular.forEach(vm.rbiStatusFilesResponseList, function (item) {
            item.selected = true;
            vm.isAllSelected.push(item.selected);
          });
        } else {
          angular.forEach(vm.rbiStatusFilesResponseList, function (item) {
            item.selected = false;
            vm.isAllSelected.push(item.selected);
          });
        }
      } else {
        vm.selectedCount = 0;
        angular.forEach(vm.rbiStatusFilesResponseList, function (item) {
          vm.isAllSelected.push(item.selected);
          if (item.selected) {
            vm.selectedCount = vm.selectedCount + 1;
          }
        });

        if (vm.rbiStatusFilesResponseList.length == vm.selectedCount) {
          vm.selectedAllItem = true;
        } else {
          vm.selectedAllItem = false;
        }
      }
    }

    function getModuleName() {
      vm.referenceId = "RBI Bond";
      var requestData = {
        referenceId: vm.referenceId,
      };
      casAckNumMaintenanceService.getRbiBondModuleNameDetails(requestData).then(
        function (response) {
          vm.schemeList = response.data.result;
        },
        function (error) {
          handleErrorResponse(error);
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
    function casAckDateValidation() {
      var currentDate = moment(vm.currentDate); // Get the current date as a Moment object
      var sevenDaysAfter = currentDate.clone().add(3, "days").toDate(); // Date 7 days after

      // Set the date range options for the calendar
      vm.dateOptions = {
        minDate: currentDate, // Minimum selectable date
        maxDate: sevenDaysAfter, // Maximum selectable date
      };
      // Optionally, you can set the default liquidationDate if needed
      vm.casAckDate = currentDate.toDate(); // Set default date to current date
    }
    function uniqueAckNum(date) {
      var date1 = $filter("date")(date, "ddMMyyyy");
      var hiddenValue = document.getElementById("hiddenInputField").value;
      vm.casAckNum = date1 + hiddenValue;
    }
    vm.indianNumberFormat=indianNumberFormat;
                    function indianNumberFormat(value) {
                      if (isNaN(value)) return value;
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
    function getEffectFromDate() {
      vm.loader = true;
      var requestData = {
        table: "T226",
        columnList: ["C004"],
        conditionList: [
          {
            colName: COLUMN_SHORT_CODES.VERIFIED_STATUS,
            value: "Y",
            condition: CONDITIONS.EQUAL,
            order: 1,
          },
          {
            colName: COLUMN_SHORT_CODES.IS_DELETED,
            value: "N",
            condition: CONDITIONS.EQUAL,
            conjunction: CONJUNCTIONS.AND,
            order: 2,
          },
          {
            colName: "IS_ACT",
            value: "Y",
            condition: CONDITIONS.EQUAL,
            conjunction: CONJUNCTIONS.AND,
            order: 3,
          },
        ],
      };
      commonService.genericTableSelectRbiBond(requestData).then(
        function (response) {
          vm.loader = false;
          vm.effFromDate = new Date(response.data.result[0].C004);
          vm.effFromDateOld = new Date(response.data.result[0].C004);
          vm.effFromDateOptionsM = {
            minDate: vm.effFromDate,
            showWeeks: false,
          };
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    vm.dateOptionsM = {
      minDate: new Date(),
      showWeeks: false,
    };

    // Modify Select details
    //        function modifySelectDetails() {
    //            vm.loader = true;
    //            vm.searchSolId = '';
    //
    //            var requestData = {
    //                'brokerAgencyCode': vm.brokerAgencyCode,
    //            };
    //            brokerMasterRbiBondService.getModifySelect(requestData).then(function(response) {
    //                vm.loader = false;
    //
    //                vm.verifyDetailsList = response.data.result;
    //                vm.verificationDetails = response.data.result.prevOperType;
    //                vm.brokerMasterList = response.data.result.masterOldNewValueModel;
    //                //                 vm.tdsDateObjOld = vm.tdsParameterList.C004.oldValue;
    //                //                 vm.tdsParameterList.C004.oldValue = convertToDateObject(vm.tdsParameterList.C004.oldValue);
    //                vm.id = vm.verifyDetailsList.pkColumnValue;
    //
    //
    //            }, function(error) {
    //                handleErrorResponse(error);
    //                $state.go('broker_master_rbi_bond.modify');
    //                vm.loader = false;
    //            });
    //        }

    /* Verify/Delete Select Details */

    function rejectReason() {
      angular.element("#permissionMappingDelete").modal("toggle");
    }
    vm.getRemarksList = function () {
                vm.referenceId = '056';
                var requestData = { 'referenceId': vm.referenceId };

                casAckNumMaintenanceService.getRemarksList(requestData).then(function(response) {
                    vm.remarksList = response.data.result || []; // Ensure it's an array
                }, function(error) {
                    handleErrorResponse(error);
                });
            }

    function getVerifyDeleteSelect() {
      vm.loader = true;
      var requestData = {
        schemeName: vm.schemeName,
        subsDate: moment(vm.subsDate, "DD/MM/YYYY").format("DD-MMM-YYYY"),
        schemeCode: vm.schemeCode,
      };
      casAckNumMaintenanceService.getVerifyDeleteSelect(requestData).then(
        function (response) {
          vm.loader = false;
          vm.modelResult = response.data.result.cacAckNumMaintenanceList;
         vm.totalSubScriptionAmount = response.data.result.totalSubScriptionAmt;
         vm.brkAdviceNumber = response.data.result.brkAdviceNumber;
          vm.auditInfo = response.data.result;
        },
        function (errorOne) {
          vm.loader = false;
          handleErrorResponse(errorOne);
          if (flg == "V") $state.go("cas_acknowledgement_number_maintenance.verify");
          else $state.go("cas_acknowledgement_number_maintenance.delete");
          vm.loader = false;
        }
      );
    }

    function getInquirySelect() {
      vm.loader = true;
      var requestData = {
        schemeName: vm.schemeName,
        subsDate: moment(vm.subsDate, "DD/MM/YYYY").format("DD-MMM-YYYY"),
        schemeCode: vm.schemeCode,
      };
      casAckNumMaintenanceService.getInquiryDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.model = response.data.result.cacAckNumMaintenanceList;
         vm.totalSubScriptionAmount = response.data.result.totalSubScriptionAmt;
         vm.brkAdviceNumber = response.data.result.brkAdviceNumber;


          vm.modell = response.data.result;
          vm.auditInfo = response.data.result;
        },
        function (errorOne) {
          vm.loader = false;
          handleErrorResponse(errorOne);
        }
      );
    }

    function getAddDet() {
      vm.loader = true;
      var requestData = {
        schemeName: vm.schemeName,
        subsDate: moment(vm.subsDate, "DD/MM/YYYY").format("DD-MMM-YYYY"),
        schemeCode: vm.schemeCode,
      };
      casAckNumMaintenanceService.getAddDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.modelResult = response.data.result.list;
          vm.totalSubScriptionAmount = response.data.result.totalSubScriptionAmt;
          vm.casAckNum1 = response.data.result.casAckNum;
          vm.brkAdviceNumber = response.data.result.brkAdviceNumber;
          vm.casTranDate = new Date(response.data.result.casTranDate);
          vm.casAckDate = new Date(response.data.result.casTranDate);
          document.getElementById("hiddenInputField").value = vm.casAckNum1;
          vm.dateOptions = {
                          minDate: moment(vm.casTranDate, "DD/MM/YYYY").toDate(),

                      }
          vm.dateOptions1 = {
                          minDate: moment(vm.casAckDate, "DD/MM/YYYY").toDate(),
                      }
          uniqueAckNum(vm.currentDate);
        },
        function (errorOne) {
          vm.loader = false;
          handleErrorResponse(errorOne);
        }
      );
    }

    function myFunction() {
      var checkBox = document.getElementById("myCheck");
      var text = document.getElementById("text");
      if (checkBox.checked == false) {
        text.style.display = "block";
      } else {
        text.style.display = "none";
      }
    }

    function showPopup() {
      vm.isChecked = false;
      if (!vm.isChecked) {
        Swal.fire("Checkbox not selected!");
      } else {
        // Perform other actions when the checkbox is selected
      }
    }

    function showPopup1() {
      vm.isChecked = true;
      //                              if (!vm.isChecked) {
      //                                Swal.fire('Checkbox not selected!');
      //                              } else {
      //                                // Perform other actions when the checkbox is selected
      //                              }
    }

    function getBondSubsId() {
      vm.loader = true;
      var requestData = {
        schemeName: vm.schemeName,
        subsDate: vm.subsDate,
        subsFlg: "N",
        flgg: "B",
      };
      casAckNumMaintenanceService.getSubsIdDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.bondSubsList = response.data.result;
          angular.element("#bondSubsIdSearch").modal("toggle");
        },
        function (errorOne) {
          vm.schemeCode = "";
          vm.loader = false;
          handleErrorResponse(errorOne);
        }
      );
    }
    vm.getBondScheme = getBondScheme;
    function getBondScheme() {
      vm.loader = true;
      var requestData = { referenceId: "RBI BOND" };
      casAckNumMaintenanceService.getRbiBondModuleNameDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.bondSchemeList = response.data.result;
          angular.element("#bondSchemeSearch").modal("toggle");
        },
        function (errorOne) {
          vm.bondScheme = "";
          vm.loader = false;
          handleErrorResponse(errorOne);
        }
      );
    }

    function getBondSubsIdModify() {
      vm.loader = true;
      var requestData = {
        schemeName: vm.schemeName,
        subsDate: vm.subsDate,
        subsFlg: "N",
        flgg: "B",
      };
      casAckNumMaintenanceService.getSubsIdDetailsModify(requestData).then(
        function (response) {
          vm.loader = false;
          vm.bondSubsList = response.data.result.casAckNumMaintenanceList;
          angular.element("#bondSubsIdSearch").modal("toggle");
        },
        function (errorOne) {
          vm.schemeCode = "";
          vm.loader = false;
          handleErrorResponse(errorOne);
        }
      );
    }

    function getSubsDateInq() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
      };
      casAckNumMaintenanceService.getSubsDateInq(requestData).then(
        function (response) {
          vm.loader = false;
          vm.subsDateList = response.data.result.cacAckNumMaintenanceListt;
          angular.element("#bondSubsDateSearch").modal("toggle");
        },
        function (errorOne) {
          //                vm.schemeCode = '';
          vm.loader = false;
          handleErrorResponse(errorOne);
        }
      );
    }

    function getSubsDateDel() {
      vm.loader = true;
      var requestData = {
        schemeName: vm.schemeName,
        dateFlg: "D",
      };
      casAckNumMaintenanceService.getSubsDateDel(requestData).then(
        function (response) {
          vm.loader = false;
          vm.subsDateList = response.data.result.casAckNumMaintenanceListt;
          angular.element("#bondSubsDateSearch").modal("toggle");
        },
        function (errorOne) {
          //                vm.schemeCode = '';
          vm.loader = false;
          handleErrorResponse(errorOne);
        }
      );
    }

    function getSubsDate() {
      vm.loader = true;
      vm.subsDate='';
      var requestData = {
        schemeCode: vm.schemeCode,
        subsDate: $filter("date")(vm.subsDate, "DD-MMM-YYYY"),
      };
      casAckNumMaintenanceService.getSubsDateDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.subsDateList = response.data.result.list;

          angular.element("#bondSubsDateSearch").modal("toggle");
        },
        function (errorOne) {
          vm.schemeName = "";
          vm.loader = false;
          handleErrorResponse(errorOne);
        }
      );
    }

    function getSubsDateVerify() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
      };
      casAckNumMaintenanceService.getSubsDateDetailsVerify(requestData).then(
        function (response) {
          vm.loader = false;
          vm.subsDateList = response.data.result.cacAckNumMaintenanceListt;
          angular.element("#bondSubsDateSearch").modal("toggle");
        },
        function (errorOne) {
          //                vm.schemeCode = '';
          vm.loader = false;
          handleErrorResponse(errorOne);
        }
      );
    }

    function getSubsDateModify() {
      vm.loader = true;
      var requestData = {
        schemeName: vm.schemeName,
        dateFlg: "D",
      };
      casAckNumMaintenanceService.getSubsDateModify(requestData).then(
        function (response) {
          vm.loader = false;
          vm.subsDateList = response.data.result.casAckNumMaintenanceListt;
          angular.element("#bondSubsDateSearch").modal("toggle");
        },
        function (errorOne) {
          //                vm.schemeCode = '';
          vm.loader = false;
          handleErrorResponse(errorOne);
        }
      );
    }

    function convertToColumnValuesArrayOldNew(objectToBeConvert) {
      if (typeof objectToBeConvert === "object") {
        let resultArray = [];
        for (let key of Object.keys(objectToBeConvert)) {
          if (objectToBeConvert[key])
            resultArray.push({
              colName: key,
              value: objectToBeConvert[key].oldValue,
            });
        }
        return resultArray;
      } else {
        throw "Only objects can be converted";
      }
    }

    function allCheck() {
      var allCheck = document.getElementById("allCheck").checked;

      var elements = document.getElementsByClassName("selectedCheck");

      if (allCheck) {
        for (var i = 0, len = elements.length; i < len; i++) {
          document.getElementsByClassName("selectedCheck")[i].checked = true;
        }
      } else {
        for (var i = 0, len = elements.length; i < len; i++) {
          document.getElementsByClassName("selectedCheck")[i].checked = false;
        }
      }
    }

    function valueCheck() {
      var elements = document.getElementsByClassName("selectedCheck");
      var totalFinal = 0;
      var checkedArray = [];
      var validCheck = 0;
      for (var i = 0, len = elements.length; i < len; i++) {
        var tranMode = document.getElementsByClassName("selectedCheck")[i].getAttribute("tranMode");
        var valueSelected = document.getElementsByClassName("selectedCheck")[i].value;
        var checkedId = document.getElementsByClassName("selectedCheck")[i].id;
        var checkedOrNot = document.getElementsByClassName("selectedCheck")[i].checked;
        if (checkedOrNot) {
          var validCheck = 1;
          checkedArray.push(checkedId);

          totalFinal += parseInt(valueSelected);
        }
      }
      /*            vm.model.creditTotal = creditTotal;
                                vm.model.debitTotal = debitTotal;*/
      vm.model.totalFinal = totalFinal;
      vm.model.checkedArray = checkedArray;
      if (validCheck == 0) {
        document.getElementById("submitbutton").disabled = true;
        document.getElementById("allCheck").checked = false;
      } else {
        document.getElementById("submitbutton").disabled = false;
      }
    }

    function modifySelectDetails() {
      vm.loader = true;
      if (vm.subsFlg == "Y") {
        vm.subsId = null;
      }
      var requestData = {
        schemeName: vm.schemeName,
        subsDate: vm.subsDate,
        subsId: vm.subsId,
        subsFlg: vm.subsFlg,
        casAckNum: vm.casAckNum,
        flg: "A",
      };
      casAckNumMaintenanceService.getModifySelect(requestData).then(
        function (response) {
          vm.loader = false;
          vm.model = response.data.result.oldNewValueList;
          //                                   vm.model.C009.oldValue = convertToDateObject(vm.model.C009.oldValue);
          angular.forEach(vm.model, function (item) {
            item.C009.oldValue = convertToDateObject(item.C009.oldValue);
            item.C003.oldValue = convertToDateObject(item.C003.oldValue);
          });
          //                                    vm.model1= response.data.result[0].oldNewValueList[0].C003.oldValue;
          //                                    vm.mod= response.data.result[0].oldNewValueList[0].C008.oldValue;
          vm.auditInfo = response.data.result;

          //                                    vm.casAckDate= response.data.result[0].oldNewValueList[0].C009.oldValue;

          vm.cbdtId = response.data.result.pkColumnValue;
        },
        function (errorOne) {
          vm.loader = false;
          handleErrorResponse(errorOne);
          $state.go("cas_acknowledgement_number_maintenance.modify");
        }
      );
    }

    /* Add Submit */
    function submitAddForm() {
//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to Really Add this record?",
//          type: "warning",
//          showCancelButton: true,
//          cancelButtonClass: "btn-cancel",
//          confirmButtonClass: "btn-primary",
//          confirmButtonText: "Yes, Add it!",
//          closeOnConfirm: false,
//          showLoaderOnConfirm: true,
//        },
//        function () {



              Swal.fire({
                title: "Are you sure?",
                text: "Do you want to Really add this record?",
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
          vm.modelResult1 = vm.modelResult.map(function (item) {
            var cleanedItem = Object.assign({}, item); // Shallow copy the item
            delete cleanedItem.$$hashKey; // Delete the $$hashKey property
            return cleanedItem;
          });
          var requestData = {
            cacAckNumList: vm.modelResult1,
            schemeCode: vm.schemeCode,
            schemeName: vm.schemeName,
            subsDate: moment(vm.subsDate, "DD/MM/YYYY").format("DD-MMM-YYYY"),
            casAckDate: $filter("date")(vm.casAckDate, "dd-MMM-yyyy"),
            casTranDate: $filter("date")(vm.casTranDate, "dd-MMM-yyyy"),
            casAckNum: vm.casAckNum,
            brkAdviceNumber: vm.brkAdviceNumber,
            totalSubScriptionAmt:vm.totalSubScriptionAmount
          };
          casAckNumMaintenanceService.submitAddForm(requestData).then(
            function (response) {
              vm.loader = false;
              vm.responseResult = response.data.result;
    var message ="CAS Acknowledgement No. Added Successfully for the Subscriptions dated <" + vm.subsDate + ">.\n" +
    "Total Number of Subscriptions: " + vm.modelResult1.length + "\n" +
    "Total Amount: " + vm.totalSubScriptionAmount;
              Swal.fire("Success!", message, "success");
              $state.go("cas_acknowledgement_number_maintenance.inquiry");
            },
            function (error) {
              handleErrorResponse(error);
              $state.go("cas_acknowledgement_number_maintenance.add");
            }
          );
          }
        }
      );
    }

    /* Submit Modify*/
    function modifySubmit() {
      var x = 0;

      if (vm.checkbox) {
        vm.checkList = Object.values(vm.checkbox);
        for (var i = 0; i < Object.keys(vm.checkbox).length; ++i) {
          if (vm.checkList[i] == true) {
            x++;
          }
        }
      }

      if (x == 0) {
        Swal.fire("Check Box Not Selected");
      } else {
//        Swal.fire(
//          {
//            title: "Are you sure?",
//            text: "Do you want to Really Modify this record?",
//            type: "warning",
//            showCancelButton: true,
//            cancelButtonClass: "btn-cancel",
//            confirmButtonClass: "btn-primary",
//            confirmButtonText: "Yes",
//            closeOnConfirm: false,
//          },
//          function (yesFlg) {
           //if (yesFlg) {


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
            if (result) {
              vm.list = [];

              angular.forEach(vm.model, function (item) {
                item.C009.oldValue = moment(item.C009.oldValue).format("DD-MMM-yyyy");
                //                              				    item.C009.oldValue = convertToDateObject(item.C009.oldValue);
                //                              				    item.C009.oldValue = formatDate(convertToDateObject(item.C009.oldValue));

                //                            item.oldNewValueList[0].C003.oldValue = moment(vm.model1).format("DD-MMM-yyyy");
                //                            item.oldNewValueList[0].C003.oldValue = $filter('date')(item.oldNewValueList[0].C003.oldValue, "DD-MMM-YYYY");
                item.C003.oldValue = moment(item.C003.oldValue).format("DD-MMM-yyyy");
                //                                                                            item.oldNewValueList.C003.oldValue = moment(item.oldNewValueList.C003.oldValue).format("DD-MMM-yyyy");
                for (var i = 0; i < Object.keys(vm.checkbox).length; i++) {
                  if (Object.keys(vm.checkbox) == item.C004.oldValue && vm.checkList[i] == true) {
                    vm.list.push(convertToColumnValuesArrayList(item));
                  }
                }
              });

              //
              var requestData = {
                columnValueDetailsList: vm.list,
                schemeName: vm.schemeName,
              };
              casAckNumMaintenanceService.getModifySubmit(requestData).then(
                (response) => {
                  vm.loader = false;
                  vm.responseResult = response.data.result;
                  var message = "CAS Acknowledgement No. Modified Successfully for the Subscriptions dated <" + vm.subsDate + ">.";
                  Swal.fire("Success!", message, "success");
                  location.href = "#!/cas_acknowledgement_number_maintenance/action/inquiry";
                },
                function (error) {
                  vm.loader = false;
                  handleErrorResponse(error);
                }
              );
              //                                        }
              //                                        else{
              //                                                                   $state.go('cas_acknowledgement_number_maintenance.modify');
              //                                                              }
            }

            }
          }
        );
      }
    }

    function formatDate(tempDate) {
      return $filter("date")(tempDate, "dd-MMM-yyyy");
    }

    const convertToColumnValuesArrayList = (objectToBeConvert) => {
      if (typeof objectToBeConvert === "object") {
        let resultArray = [];
        for (let key of Object.keys(objectToBeConvert)) {
          if (objectToBeConvert[key])
            resultArray.push({
              colName: key,
              value: objectToBeConvert[key].oldValue,
            });
        }

        return resultArray;
      } else {
        throw "Only objects can be converted";
      }
    };

    /* Verify Submit */
    function submitVerifyForm() {
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
//
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
          vm.modelResult1 = vm.modelResult.map(function (item) {
            var cleanedItem = Object.assign({}, item); // Shallow copy the item
            delete cleanedItem.$$hashKey; // Delete the $$hashKey property
            return cleanedItem;
          });
          var requestData = {
            cacAckNumList: vm.modelResult1,
            schemeCode: vm.schemeCode,
            schemeName: vm.schemeName,
            subsDate: moment(vm.subsDate, "DD/MM/YYYY").format("DD-MMM-YYYY"),
            casAckDate: $filter("date")(vm.auditInfo.casAckDate, "dd-MMM-yyyy"),
            casAckNum: vm.auditInfo.casAckNum,
            casTranDate:vm.auditInfo.casTranDate,
            totalSubScriptionAmt:vm.totalSubScriptionAmount
          };
          casAckNumMaintenanceService.submitVerifyForm(requestData).then(
            function (response) {
              vm.loader = false;
              vm.responseResult = response.data.result;
              var message ="CAS Acknowledgement No. Verified Successfully for the Subscriptions dated <" + vm.subsDate + ">.\n" +
              "Total Number of Subscriptions: " + vm.modelResult1.length + "\n" +
              "Total Amount: " + vm.totalSubScriptionAmount;
              Swal.fire("Success!", message, "success");
              $state.go("cas_acknowledgement_number_maintenance.inquiry");
            },
            function (error) {
              vm.loader = false;
              handleErrorResponse(error);
              $state.go("cas_acknowledgement_number_maintenance.verify");
            }
          );
          }
        }
      );
    }
    /* Delete Submit */
    function submitDeleteForm() {
//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to Really Reject this record?",
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
                                text: "Do you want to Really Reject this record?",
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
          vm.modelResult1 = vm.modelResult.map(function (item) {
            var cleanedItem = Object.assign({}, item); // Shallow copy the item
            delete cleanedItem.$$hashKey; // Delete the $$hashKey property
            return cleanedItem;
          });
          var requestData = {
            cacAckNumList: vm.modelResult1,
            schemeCode: vm.schemeCode,
            schemeName: vm.schemeName,
            subsDate: moment(vm.subsDate, "DD/MM/YYYY").format("DD-MMM-YYYY"),
            casAckDate: $filter("date")(vm.auditInfo.casAckDate, "dd-MMM-yyyy"),
            casAckNum: vm.auditInfo.casAckNum,
            casTranDate: vm.auditInfo.casTranDate,
            deleteRemarks: vm.remarks
          };
          casAckNumMaintenanceService.submitDeleteForm(requestData).then(
            function (response) {
              vm.loader = false;
              vm.responseResult = response.data.result;
              var message = "CAS Acknowledgement No. Rejected Successfully for the subscriptions dated <" + vm.subsDate + ">.";
              Swal.fire("Success!", message, "success");
              $state.go("cas_acknowledgement_number_maintenance.inquiry");
            },
            function (error) {
              vm.loader = false;
              handleErrorResponse(error);
              $state.go("cas_acknowledgement_number_maintenance.delete");
            }
          );
        }
        }
      );
      //                        }
    }

    // Cancel Confirmation
    function showCancelConfirmation(state) {
//      Swal.fire(
//        {
//          title: "Confirm Cancel",
//          text: "Do you really want to cancel?",
//          type: "warning",
//          showCancelButton: true,
//          cancelButtonClass: "btn-cancel",
//          confirmButtonClass: "btn-danger",
//          confirmButtonText: "Yes",
//          cancelButtonText: "No",
//          closeOnConfirm: true,
//        },
//        function () {


            Swal.fire({
                            title: "Are you sure?",
                            text: "Do you really want to cancel?",
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
        }
      );
    }

    //Get Inquiry Version No Tourch Details
    function getInquiryTorch() {
      vm.loader = true;
      vm.searchSolId = "";
      var requestData = {
        //                    'brokerAgencyCode': vm.brokerAgencyCode,
      };
      brokerMasterRbiBondService.getInquirySelectDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.inquiryTorchList = response.data.result.torchValue;

          angular.element("#rbiCodeModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    //Get Inquiry Select Details
    //        function getInquirySelect() {
    //            vm.loader = true;
    //            vm.searchSolId = '';
    //            var requestData = {
    //                'brokerAgencyCode': vm.brokerAgencyCode,
    //            }
    //            brokerMasterRbiBondService.getInquirySelectDetails(requestData).then(function(response) {
    //                vm.loader = false;
    //                vm.verifyDetailsList = response.data.result;
    //                vm.verificationDetails = response.data.result.prevOperType;
    //                vm.tdsParameterList = response.data.result.masterOldNewValueModel;
    //            }, function(error) {
    //                handleErrorResponse(error);
    //                vm.loader = false;
    //            });
    //        }

    function decimalConvert(flg, errorMsg, modifyFlg) {
      let decimal = parseFloat(flg);

      if (decimal < 100) {
        if (errorMsg == "P1") vm.tdsRatePanError = false;
        if (errorMsg == "P2") vm.tdsRateNoPanError = false;
        if (errorMsg == "P3") vm.educationCessError = false;
        if (errorMsg == "P4") vm.otherCessError = false;

        return true;
      } else {
        if (errorMsg == "P1") {
          vm.tdsRatePanError = "Min 2 Digit Decimal Number(" + decimal + ")";
          vm.tdsRatePan = "";
          if (modifyFlg) {
            if (vm.tdsParameterList.tdsRatePan) vm.tdsParameterList.tdsRatePan = "";
          }
          return false;
        }
        if (errorMsg == "P2") {
          vm.tdsRateNoPanError = "Min 2 Digit Decimal Number(" + decimal + ")";
          vm.tdsRateNoPan = "";
          if (modifyFlg) {
            if (vm.tdsParameterList.tdsRateNoPan) vm.tdsParameterList.tdsRateNoPan = "";
          }
          return false;
        }
        if (errorMsg == "P3") {
          vm.educationCessError = "Min 2 Digit Decimal Number(" + decimal + ")";
          vm.educationCess = "";
          if (modifyFlg) {
            if (vm.tdsParameterList.educationCess) vm.tdsParameterList.educationCess = "";
          }
          return false;
        }
        if (errorMsg == "P4") {
          vm.otherCessError = "Min 2 Digit Decimal Number(" + decimal + ")";
          vm.otherCess = "";
          if (modifyFlg) {
            if (vm.tdsParameterList.otherCess) vm.tdsParameterList.otherCess = "";
          }
          return false;
        }
      }
    }

    //verify and delete torch function
    function getVerifyDeleteTorch(flag) {
      vm.loader = true;
      vm.searchSolId = "";
      var requestData = {};
      if (flag == "torch") {
      } else {
        requestData.brokerAgencyCode = vm.brokerAgencyCode;
      }

      brokerMasterRbiBondService.getVerifyDelSelectDetails(requestData).then(
        function (response) {
          vm.loader = false;
          if (flag == "torch") {
            vm.rbiCodeList = response.data.result.torchValue;
            angular.element("#rbiCodeModal").modal("toggle");
          } else {
            vm.branchCodeList = response.data.result.torchValue;
            vm.versionNo = vm.branchCodeList[0].versionNo;
            vm.effectiveFromDate = vm.branchCodeList[0].effectiveFromDate;
          }
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    /*Operative Acct Num*/
    function validateAccNumCbs(acctNum) {
      if (acctNum) {
        vm.loader = true;
        commonService.getCustomAcctInqApi(
          acctNum.toUpperCase(),
          function (response) {
            if (response.success) {
              vm.accList = response.data;
              vm.loader = false;
              if (vm.accList.length > 0) {
                if (vm.accList[0].status == "S") {
                  if (vm.accList[0].accountFreezeStatus) {
                    vm.cbsErrorMessage = "Account is Friezed";
                    vm.branchId = null;
                  } else {
                    vm.cbsErrorMessage = null;
                    vm.branchId = vm.accList[0].solID;
                  }
                }
                if (vm.accList[0].status == "F") {
                  vm.cbsErrorMessage = vm.accList[0].errDesc;
                  vm.branchId = null;
                }
              } else {
                vm.cbsErrorMessage = "Invalid Account Number";
                vm.branchId = null;
              }
            } else {
              vm.branchId = null;
              Swal.fire("Error!", response.message, "error");
              vm.loader = false;
            }
          },
          function (err) {
            vm.branchId = null;
            vm.loader = false;
            handleErrorResponse(err);
          }
        );
      }
    }

    function validateAccNumCbsM(acctNum) {
      if (acctNum) {
        vm.loader = true;
        commonService.getCustomAcctInqApi(
          acctNum.toUpperCase(),
          function (response) {
            if (response.success) {
              vm.accList = response.data;
              vm.loader = false;
              if (vm.accList.length > 0) {
                if (vm.accList[0].status == "S") {
                  if (vm.accList[0].accountFreezeStatus) {
                    vm.cbsErrorMessage = "Account is Friezed";
                    vm.brokerMasterList.C005.oldValue = null;
                  } else {
                    vm.cbsErrorMessage = null;
                    vm.brokerMasterList.C005.oldValue = vm.accList[0].solID;
                  }
                }
                if (vm.accList[0].status == "F") {
                  vm.cbsErrorMessage = vm.accList[0].errDesc;
                  vm.brokerMasterList.C005.oldValue = null;
                }
              } else {
                vm.cbsErrorMessage = "Invalid Account Number";
                vm.brokerMasterList.C005.oldValue = null;
              }
            } else {
              vm.brokerMasterList.C005.oldValue = null;
              Swal.fire("Error!", response.message, "error");
              vm.loader = false;
            }
          },
          function (err) {
            vm.brokerMasterList.C005.oldValue = null;
            vm.loader = false;
            handleErrorResponse(err);
          }
        );
      }
    }

    function getModifyTorch() {
      vm.loader = true;
      vm.searchSolId = "";
      var requestData = {
        //                    'brokerAgencyCode': vm.brokerAgencyCode,
      };
      brokerMasterRbiBondService.getModifySelect(requestData).then(
        function (response) {
          vm.loader = false;
          vm.rbiCodeList = response.data.result.torchValue;
          angular.element("#rbiCodeModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function dateValidation() {
      vm.dateOptions = {
        minDate: vm.currentDate,
      };
    }

    vm.dateOptionsM = {
      minDate: new Date(),
    };
  }
})();
