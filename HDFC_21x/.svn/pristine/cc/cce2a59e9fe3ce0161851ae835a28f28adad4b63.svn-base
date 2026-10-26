(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("dropDownMasterController", dropDownMasterController);

  dropDownMasterController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$stateParams",
    "$location",
    "$rootScope",
    "$timeout",
    "dropDownMasterService",
    "commonService",
  ];

  function dropDownMasterController($scope, $filter, $state, $stateParams, $location, $rootScope, $timeout, dropDownMasterService, commonService) {
    var vm = this;
    //    		vm.getModuleNameAdd = getModuleNameAdd;
    //    		vm.dropDownList = dropDownList;
    vm.dropDownListElements = [];
    vm.dropAddDownListElements = [];
    vm.addRange = addRange;
    vm.modifyRange = modifyRange;
    vm.removeRange = removeRange;
    vm.removeModRange = removeModRange;
    vm.addSubmit = addSubmit;
    vm.modifySubmit = modifySubmit;
    vm.verifySubmit = verifySubmit;

    vm.validateInput = validateInput;
    vm.getVerifyDetails = getVerifyDetails;
    vm.getInquiryDetails = getInquiryDetails;
    vm.getModifyDetails = getModifyDetails;
    vm.rejectDetails = rejectDetails;
    vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
    if ($state.current.name == "drop_down_master.add_detail") {
      if ($stateParams.obj) {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.schemeStatus = $stateParams.obj.schemeStatus;
        vm.dropDownType = $stateParams.obj.dropDownType;
        //                    vm.getGuardianDetailsMetaData();
      }
    } else if ($state.current.name == "drop_down_master.inquiry_detail") {
      if ($stateParams.obj) {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.schemeStatus = $stateParams.obj.schemeStatus;
        vm.dropDownType = $stateParams.obj.dropDownType;
        vm.relationshipCode = $stateParams.obj.relationshipCode;
        vm.getInquiryDetails();
      }
    } else if ($state.current.name == "drop_down_master.verify_detail") {
      if ($stateParams.obj) {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.schemeStatus = $stateParams.obj.schemeStatus;
        vm.dropDownType = $stateParams.obj.dropDownType;
        vm.getVerifyDetails();
      }
    } else if ($state.current.name == "drop_down_master.modify_detail") {
      if ($stateParams.obj) {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.schemeStatus = $stateParams.obj.schemeStatus;
        vm.dropDownType = $stateParams.obj.dropDownType;
        vm.getModifyDetails();
      }
    }


 vm.dropDownList = [
                {
                    DESCRIPTION: 'APPL STATUS (BLA Holder Type)', SUBCODE: '060'
                },
                {
                    DESCRIPTION: 'BANK ACCT TYPE', SUBCODE: '061'
                },
               /* {
                    DESCRIPTION: 'FREEZE AUTHORITY', SUBCODE: '062'
                },
                {
                    DESCRIPTION: 'DOCUMENT', SUBCODE: '063'
                },
                {
                    DESCRIPTION: 'PROOF', SUBCODE: '064'
                },*/
                {
                 DESCRIPTION: 'RELATION (Nominee and Where ever)', SUBCODE: '032'
                },
                /* {
                  DESCRIPTION: 'bank Count type- SB/CD', SUBCODE: '067'
                 },*/
                  {
                   DESCRIPTION: 'Business Segment- Retail/FIG', SUBCODE: '066'
                  },
                  {
                   DESCRIPTION: 'NOMINEE STATUS', SUBCODE: '068'
                  }
            ];

vm.dropDownInquiryList = [
                {
                    DESCRIPTION: 'APPL STATUS (BLA Holder Type)', SUBCODE: '060'
                },
                {
                    DESCRIPTION: 'BANK ACCT TYPE', SUBCODE: '061'
                },
                {
                    DESCRIPTION: 'FREEZE AUTHORITY', SUBCODE: '062'
                },
                {
                    DESCRIPTION: 'DOCUMENT', SUBCODE: '063'
                },
                {
                    DESCRIPTION: 'PROOF', SUBCODE: '064'
                },
                {
                 DESCRIPTION: 'RELATION (Nominee and Where ever)', SUBCODE: '032'
                },
                /* {
                  DESCRIPTION: 'bank Count type- SB/CD', SUBCODE: '067'
                 },*/
                  {
                   DESCRIPTION: 'Business Segment- Retail/FIG', SUBCODE: '051'
                  },
                 {
                 DESCRIPTION: 'NOMINEE STATUS', SUBCODE: '068'
                }
            ];
    function addRange() {
      vm.loader = true;
      vm.dropDownListElements.push({});
      vm.loader = false;
      if (vm.dropDownListElements.length == 0) {
        vm.enableDelete = false;
        vm.enableSubmit = false;
      } else vm.enableDelete = true;
      vm.enableSubmit = true;
    }

    function modifyRange() {
      vm.loader = true;
      vm.dropDownListElements.push({});
      vm.loader = false;
      vm.enableDelete = true;
    }

    function removeRange(index) {
      if (!vm.deletedRows) {
        vm.deletedRows = [];
      }
      const removedItem = vm.dropDownListElements.splice(index, 1)[0];

      if (vm.dropDownListElements.length === 0) {
        vm.disableDelete = true;
        vm.enableSubmit = false;
      }
    }

    function removeModRange(index) {
      // Ensure deletedRows is initialized
      if (!vm.deletedRows) {
        vm.deletedRows = []; // Initialize if it doesn't exist
      }

      // Ensure resultDelArray is initialized
      if (!vm.resultDelArray) {
        vm.resultDelArray = []; // Initialize if it doesn't exist
      }

      // Remove the item and capture the removed item
      const removedItem = vm.dropAddDownListElements.splice(index, 1)[0];

      // Create deletion data object
      let deletionData = {};
      if (removedItem) {
        deletionData["fieldCode"] = removedItem.fieldCode; // Capture the field name
        // Add any other necessary data, e.g., ID if applicable
      }

      // Push deletion data to deletedRows
      vm.deletedRows.push(deletionData);
      //    vm.resultDelArray.push(deletionData]);

      // Check if we need to disable delete button
      if (vm.dropAddDownListElements.length === 0) {
        vm.disableDelete = true;
      }
    }

    function addSubmit() {
//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to Really Add this record?",
//          type: "warning",
//          showCancelButton: true,
//          cancelButtonClass: "btn-cancel",
//          confirmButtonClass: "btn-primary",
//          confirmButtonText: "Yes",
//          closeOnConfirm: false,
//                                 			}, function () {


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


         vm.resultAddArray1 = [];  // Initialize the array

         // Create a Set to track unique field names
         let fieldNamesSet = new Set();
         let duplicateFound = false;  // Flag to track if duplicates are found

         // Loop through the dropDownListElements and process the fieldNames
         angular.forEach(vm.dropDownListElements, function(item) {
             if (item.fieldName) {  // Check if fieldName exists
                 if (fieldNamesSet.has(item.fieldName)) {
                     // If the fieldName already exists in the set, it's a duplicate
                     duplicateFound = true;
                 } else {
                     // Add the fieldName to the set (will handle uniqueness automatically)
                     fieldNamesSet.add(item.fieldName.toUpperCase());
                 }
             }
         });

      // If duplicates are found, show the warning SweetAlert
      if (duplicateFound) {
          Swal.fire("Warning!", "Duplicate records in the list.", "warning");
          return;
      } else {
          // If no duplicates are found, just add all unique values to resultAddArray1
          vm.resultAddArray1 = Array.from(fieldNamesSet);
      }

          vm.loader = true;
          var requestData = {
            addedList: vm.resultAddArray1,
            relationshipCode: vm.dropDownType.SUBCODE,
          };
          dropDownMasterService.addDetails(requestData).then(
            (response) => {
              vm.loader = false;

              var message = response.data.message;

              //                               Swal.fire("Added!", `Field Name for ${vm.dropDownType} added successfully.`, "success");
//              Swal.fire("Added!", `Field Name for  ${dropDownType.DESCRIPTION} added successfully.`, "success");

        Swal.fire("Added!", `Field Name for ${vm.dropDownType.DESCRIPTION} added successfully.`, "success");

              location.href = "#!/drop_down_master/action/inquiry";
            },
            function (error) {
              vm.loader = false;
              handleErrorResponse(error);
            }
          );
       } }
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

    vm.getRemarksList = function () {
      vm.referenceId = "056";
      var requestData = { referenceId: vm.referenceId };

      dropDownMasterService.getRemarksList(requestData).then(
        function (response) {
          vm.remarksList = response.data.result || []; // Ensure it's an array
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    };

    vm.rejectReason = function () {
      angular.element("#permissionMappingDelete").modal("toggle");
    };

    function rejectDetails() {

    vm.resultAddArray = [];
    vm.resultDelArray = []; // Reset resultDelArray

    angular.forEach(vm.AddedList, function (item) {
      if (item.fieldCode) {
        // Ensure fieldCode exists
        vm.resultAddArray.push(item.fieldCode);
      }
    });

    angular.forEach(vm.deleteddList, function (item) {
      if (item.fieldCode) {
        vm.resultDelArray.push(item.fieldCode);
      }
    });
      var requestData = {
        relationshipCode: vm.dropDownType.SUBCODE,
        addedList: vm.resultAddArray,
        deletedList: vm.resultDelArray,
        rejectionRemarks: vm.rejectionRemarks,
      };

      dropDownMasterService.reject(requestData).then(
        (response) => {
          vm.loader = false;
          Swal.fire("Success!", "Rejected Successfully", "success");
          location.href = "#!/drop_down_master/action/inquiry";
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    //    function rejectDetails() {
    //      function handleKeyPress(event) {
    //        var key = event.key;
    //        var input = event.target.value;
    //
    //        if (key === " " && (input.length === 0 || input.slice(-1) === " ")) {
    //          event.preventDefault();
    //        }
    //
    //        if (!key.match(/^[a-zA-Z\s]$/) && key !== "Backspace" && key !== "Enter") {
    //          event.preventDefault();
    //        }
    //      }
    //
    //      document.addEventListener("keypress", handleKeyPress);
    //
    //      Swal.fire(
    //        {
    //          title: "Rejection Remarks",
    //          text: "",
    //          type: "input",
    //          inputPlaceholder: "Enter your remarks here",
    //          showCancelButton: true,
    //          cancelButtonClass: "btn-cancel",
    //          confirmButtonClass: "btn-primary",
    //          confirmButtonText: "Submit",
    //          animation: "slide-from-top",
    //          closeOnConfirm: false,
    //          inputValidator: function (value) {
    //            if (!value || value.trim() === "") {
    //              return "Please enter the remarks!";
    //            }
    //
    //            const trimmedValue = value.trim();
    //
    //            if (trimmedValue.length > 0 && /^[a-zA-Z\s]*$/.test(trimmedValue)) {
    //              if (/\s\s/.test(trimmedValue)) {
    //                return "Please enter only single spaces between words!";
    //              }
    //              document.removeEventListener("keypress", handleKeyPress);
    //              return true;
    //            } else {
    //              return "Please enter only alphabetic characters!";
    //            }
    //          },
    //        },
    //        function (inputValue) {
    //          if (inputValue === false) {
    //            document.removeEventListener("keypress", handleKeyPress);
    //            return false;
    //          }
    //
    //          // Check if input is empty after trimming
    //          const trimmedValue = inputValue.trim();
    //          if (trimmedValue === "") {
    //            Swal.fire.showInputError("Please enter the remarks!");
    //            return false;
    //          }
    //
    //          vm.rejectionRemarks = trimmedValue;
    //
    //          vm.resultAddArray = [];
    //          vm.resultDelArray = []; // Reset resultDelArray
    //
    //          angular.forEach(vm.AddedList, function (item) {
    //            vm.resultAddArray.push(item.fieldCode);
    //          });
    //
    //          angular.forEach(vm.deleteddList, function (item) {
    //            vm.resultDelArray.push(item.fieldCode); // Correct usage of push with parentheses
    //          });
    //
    //          var requestData = {
    //            relationshipCode: vm.dropDownType,
    //            addedList: vm.resultAddArray,
    //            deletedList: vm.resultDelArray,
    //            rejectionRemarks: vm.rejectionRemarks,
    //          };
    //
    //          dropDownMasterService.reject(requestData).then(
    //            (response) => {
    //              vm.loader = false;
    //              Swal.fire("Success!", "Rejected Successfully", "success");
    //              location.href = "#!/drop_down_master/action/inquiry";
    //            },
    //            function (error) {
    //              vm.loader = false;
    //              handleErrorResponse(error);
    //            }
    //          );
    //        }
    //      );
    //    }
    function getModifyDetails() {
      vm.loader = true;

      var requestData = {
        relationshipCode: vm.dropDownType.SUBCODE,
      };

      dropDownMasterService.modifyDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.dropAddDownListElements = response.data.result.schemeDetailList;
          vm.AddedList = response.data.result.addedDetailList;
          vm.deleteddList = response.data.result.deleteDetailList;
          vm.audit = response.data.result;
          vm.rejectionRemarks = response.data.result.rejectionRemarks;
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
          $state.go("drop_down_master.inquiry");
        }
      );
    }

    function modifySubmit() {
//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to Really Modify this record?",
//          type: "warning",
//          showCancelButton: true,
//          cancelButtonClass: "btn-cancel",
//          confirmButtonClass: "btn-primary",
//          confirmButtonText: "Yes",
//          closeOnConfirm: false,
//        },
//        function (yesFlg) {
//          if (yesFlg) {




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

            vm.resultAddArray = [];
            vm.resultDelArray = []; // Reset resultDelArray

            angular.forEach(vm.dropDownListElements, function (item) {
              if (item.fieldName) {
                // Check if fieldName exists
                vm.resultAddArray.push(item.fieldName);
              }
            });

            // Construct deletion list
            angular.forEach(vm.deletedRows, function (deletedItem) {
              angular.forEach(deletedItem, function (value) {
                vm.resultDelArray.push(value); // Push each value into resultDelArray
              });
            });

            let requestData = {
              relationshipCode: vm.dropDownType.SUBCODE,

              addedList: vm.resultAddArray,
              deletedList: vm.resultDelArray,
            };

            dropDownMasterService.modifySubmit(requestData).then(
              function (response) {
                vm.loader = false;
//                Swal.fire("Modified!", "Field Name for Relation (Nominee and Where ever) Modified Successfully", "success");
                            Swal.fire("Modified!", `Field Name for  ${vm.dropDownType.DESCRIPTION}  Modified successfully.`, "success");

                location.href = "#!/drop_down_master/action/inquiry";
              },
              function (error) {
                vm.loader = false;
                handleErrorResponse(error);
                $state.go("drop_down_master.inquiry");
              }
            );
          } else {
            $state.go("drop_down_master.inquiry");
          }
        }
      );
    }
    function getVerifyDetails() {
      vm.loader = true;

      var requestData = {
        relationshipCode: vm.dropDownType.SUBCODE,
      };

      dropDownMasterService.verifyDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.modifiedList = response.data.result.schemeDetailList;
          vm.AddedList = response.data.result.addedDetailList;
          vm.deleteddList = response.data.result.deleteDetailList;
          vm.audit = response.data.result;
          vm.rejectionRemarks = response.data.result.rejectionRemarks;
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
          $state.go("drop_down_master.inquiry");
        }
      );
    }

    function verifySubmit() {
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
//        function (yesFlg) {
//          if (yesFlg) {







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

            vm.resultAddArray = [];
            vm.resultDelArray = []; // Reset resultDelArray

            angular.forEach(vm.AddedList, function (item) {
              if (item.fieldCode) {
                // Ensure fieldCode exists
                vm.resultAddArray.push(item.fieldCode);
              }
            });

            angular.forEach(vm.deleteddList, function (item) {
              if (item.fieldCode) {
                vm.resultDelArray.push(item.fieldCode);
              }
            });

            let requestData = {
              relationshipCode: vm.dropDownType.SUBCODE,
              addedList: vm.resultAddArray,
              deletedList: vm.resultDelArray,
            };

            dropDownMasterService.verifySubmit(requestData).then(
              (response) => {
                vm.loader = false;
                //                    Swal.fire("Verified!","Field Name for ${vm.dropDownType} Verified Successfully","success");
//                Swal.fire("Verified!", "Field Name for Relation (Nominee and Where ever) Verified Successfully", "success");
                            Swal.fire("Verified!", `Field Name for  ${vm.dropDownType.DESCRIPTION}  Verified successfully.`, "success");

                location.href = "#!/drop_down_master/action/inquiry";
              },
              function (error) {
                vm.loader = false;
                handleErrorResponse(error);
                $state.go("drop_down_master.inquiry");
              }
            );
          } else {
            $state.go("drop_down_master.inquiry");
          }
        }
      );
    }
    function getInquiryDetails() {
      vm.loader = true;

      var requestData = {
        relationshipCode: vm.dropDownType.SUBCODE,
      };

      dropDownMasterService.inquiry(requestData).then(
        function (response) {
          vm.loader = false;
          vm.modifiedList = response.data.result.schemeDetailList;
          vm.AddedList = response.data.result.addedDetailList;
          vm.deleteddList = response.data.result.deleteDetailList;
          vm.audit = response.data.result;
          vm.rejectionRemarks = response.data.result.rejectionRemarks;
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
          $state.go("drop_down_master.inquiry");
        }
      );
    }

    const convertToColumnValuesArrayList = (objectToBeConvert) => {
      if (typeof objectToBeConvert === "object") {
        let resultArray = [];
        for (let key of Object.keys(objectToBeConvert)) {
          if (objectToBeConvert[key]) resultArray.push({ colName: key, value: objectToBeConvert[key].oldValue });
        }

        return resultArray;
      } else {
        throw "Only objects can be converted";
      }
    };
  }
})();
