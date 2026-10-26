(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("cbsBranchesOfTheBankController", cbsBranchesOfTheBankController);

  cbsBranchesOfTheBankController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "cbsBranchesOfTheBankService", "commonService"];

  function cbsBranchesOfTheBankController($scope, $filter, $state, $stateParams, $location, cbsBranchesOfTheBankService, commonService) {
    var vm = this;
    //vm.emailPattern = /^(?!\s)[a-zA-Z0-9._%+-]+@(hdfcbank\.com|hdfcbank\.co\.in)$/i;

    vm.emailPattern = /^(?!\s)[a-zA-Z0-9_%+-][a-zA-Z0-9._%+-]*@(hdfcbank\.com|hdfcbank\.co\.in)$/i;

    vm.ifscPattern = /[a-zA-Z]{4}[0-9]{1}[a-zA-Z0-9]{6}/;
    vm.cbsBranchesOfTheBank = {};
    vm.itemsPerPage = "10";
    vm.add = add;
    vm.list = list;
    vm.listUnverifiedRecords = listUnverifiedRecords;
    vm.listVerifiedRecords = listVerifiedRecords;
    vm.modify = modify;
    vm.verify = verify;
    vm.deleteBranchesOfTheBank = deleteBranchesOfTheBank;
    vm.getRecord = getRecord;
    vm.getStatus = getStatus;
    vm.getState = getState;
    vm.getInquiryDetails = getInquiryDetails;
    vm.getVerDelDetails = getVerDelDetails;
    vm.showCancelConfirmation = showCancelConfirmation;
    vm.rejectDetails = rejectDetails;
    vm.getModifyDetails = getModifyDetails;
    vm.commonbankstatus = COMMON_BANK_STATUS;
    vm.rejectReason = rejectReason;
    vm.getRemarksList = getRemarksList;

//    vm.stateMetaData = [
//      {
//        label: "ANDHRA PRADESH",
//        value: "S037",
//      },
//      {
//        label: "ARUNACHAL PRADESH",
//        value: "S012",
//      },
//      {
//        label: "ASSAM",
//        value: "S018",
//      },
//      {
//        label: "BIHAR",
//        value: "S010",
//      },
//      {
//        label: "ANDAMAN AND NICOBAR",
//        value: "S035",
//      },
//      {
//        label: "CHANDIGARH",
//        value: "S004",
//      },
//      {
//        label: "DADRA AND NAGAR HAVELI",
//        value: "S026",
//      },
//      {
//        label: "DAMAN AND DIU",
//        value: "S025",
//      },
//      {
//        label: "DELHI",
//        value: "S007",
//      },
//      {
//        label: "GOA",
//        value: "S030",
//      },
//      {
//        label: "GUJARAT",
//        value: "S024",
//      },
//      {
//        label: "HARYANA",
//        value: "S006",
//      },
//      {
//        label: "HIMACHAL PRADESH",
//        value: "S002",
//      },
//      {
//        label: "JAMMU AND KASHMIR",
//        value: "S001",
//      },
//      {
//        label: "KARNATAKA",
//        value: "S029",
//      },
//      {
//        label: "KERALA",
//        value: "S032",
//      },
//      {
//        label: "LAKSHADWEEP",
//        value: "S031",
//      },
//      {
//        label: "MADHYA PRADESH",
//        value: "S023",
//      },
//      {
//        label: "MAHARASHTRA",
//        value: "S027",
//      },
//      {
//        label: "MANIPUR",
//        value: "S014",
//      },
//      {
//        label: "MEGHALAYA",
//        value: "S017",
//      },
//      {
//        label: "MIZORAM",
//        value: "S015",
//      },
//      {
//        label: "NAGALAND",
//        value: "S013",
//      },
//      {
//        label: "ORISSA",
//        value: "S021",
//      },
//      {
//        label: "PUDUCHERRY",
//        value: "S034",
//      },
//      {
//        label: "PUNJAB",
//        value: "S003",
//      },
//      {
//        label: "RAJASTHAN",
//        value: "S008",
//      },
//      {
//        label: "SIKKIM",
//        value: "S011",
//      },
//      {
//        label: "TAMIL NADU",
//        value: "S033",
//      },
//      {
//        label: "TRIPURA",
//        value: "S016",
//      },
//      {
//        label: "UTTARAKHAND",
//        value: "S005",
//      },
//      {
//        label: "UTTAR PRADESH",
//        value: "S009",
//      },
//      {
//        label: "WEST BENGAL",
//        value: "S019",
//      },
//      {
//        label: "CHHATTISGARH",
//        value: "S022",
//      },
//      {
//        label: "JHARKHAND",
//        value: "S020",
//      },
//      {
//        label: "OTHER TERRITORY",
//        value: "S097",
//      },
//      {
//        label: "OTHER COUNTRY",
//        value: "S096",
//      },
//    ];

        function getState() {
        console.log("inside get state ");
          vm.listType = "STATES OF INDIA";

          var requestData = {
            referenceId: vm.listType,
          };

          cbsBranchesOfTheBankService.getReferenceData(requestData).then(
            function (response) {
              vm.stateListValues = response.data.result;
              vm.stateList = [];
              angular.forEach(vm.stateListValues, function (item) {
                vm.stateList.push({
                  subCodeDescription: item.DESCRIPTION,
                  subCode: item.SUBCODE,
                });
              });
            },
            function (error) {
              handleErrorResponse(error);
            }
          );
        }

            vm.convertLabelValue= function (list) {
              for (var item in list) {
                list[item].value = list[item].SUBCODE;
                list[item].label = list[item].DESCRIPTION;
              }
              return list;
            }

    if ($state.current.name == "cbs_branches_of_the_bank.list" || $state.current.name == "cbs_branches_of_the_bank.modify") {
      //list();
    } else if ($state.current.name == "cbs_branches_of_the_bank.list_detail") {
      if ($stateParams.obj) {
        vm.solId = $stateParams.obj.solId;
        if (vm.solId) {
          getInquiryDetails();
        }
      }
    } else if ($state.current.name == "cbs_branches_of_the_bank.verify_detail") {
      if ($stateParams.obj) {
        vm.solId = $stateParams.obj.solId;
        if (vm.solId) {
          getVerDelDetails();
        }
      }
    } else if ($state.current.name == "cbs_branches_of_the_bank.delete_detail") {
      if ($stateParams.obj) {
        vm.solId = $stateParams.obj.solId;
        if (vm.solId) {
          getVerDelDetails();
        }
      }
    } else if ($state.current.name == "cbs_branches_of_the_bank.modify_form") {
      if ($stateParams.obj) {
        vm.solId = $stateParams.obj.solId;
        if (vm.solId) {
          getModifyDetails();
        }
      }
    } else if ($state.current.name == "cbs_branches_of_the_bank.delete" || $state.current.name == "cbs_branches_of_the_bank.verify") {
      //listUnverifiedRecords();
    } else if (
      $state.current.name == "cbs_branches_of_the_bank.list_detail" ||
      $state.current.name == "cbs_branches_of_the_bank.delete_detail" ||
      $state.current.name == "cbs_branches_of_the_bank.verify_detail" ||
      $state.current.name == "cbs_branches_of_the_bank.modify_form"
    ) {
      if ($stateParams.obj) {
        vm.id = $stateParams.obj.id;
        if (vm.id) {
          getRecord(vm.id);
        }
      } else {
        $state.go("cbs_branches_of_the_bank.list");
      }
    }

    // get status
    function getStatus() {
      vm.loader = true;
      cbsBranchesOfTheBankService.getStatus().then(function (response) {
        vm.statusList = response.data;
        vm.loader = false;
      }, handleErrorResponse);
    }

    // Add data to database
    function add() {
//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to add this record? ",
//          type: "warning",
//          showCancelButton: true,
//          cancelButtonClass: "btn-cancel",
//          confirmButtonClass: "btn-primary",
//          confirmButtonText: "Yes!",
//          closeOnConfirm: false,
//          showLoaderOnConfirm: true,
//        },
//        function () {


           Swal.fire({
                              title: "Are you sure?",
                              text: "Do you want to Add This Record?",
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
          cbsBranchesOfTheBankService.addbranchesOfTheBank(vm.cbsBranchesOfTheBank).then(function (response) {
            vm.loader = false;
            Swal.fire("Added!", "Branch Added Successfully", "success");
            vm.cbsBranchesOfTheBank = {};
            location.href = "#!/cbs_branches_of_the_bank/action/inquiry";
          }, handleErrorResponse);
     }   }
      );
    }

    function list() {
      vm.loader = true;
      cbsBranchesOfTheBankService.getlist().then(function (response) {
        vm.recordsList = response.data;
        vm.loader = false;
      }, handleErrorResponse);
    }

    function listUnverifiedRecords() {
      vm.loader = true;
      cbsBranchesOfTheBankService.getunverifiedrecords().then(function (response) {
        vm.recordsList = response.data;
        vm.loader = false;
      }, handleErrorResponse);
    }

    function listVerifiedRecords() {
      vm.loader = true;
      cbsBranchesOfTheBankService.getverifiedrecords().then(function (response) {
        vm.recordsList = response.data;
        vm.loader = false;
      }, handleErrorResponse);
    }

    function getRecord(id) {
      vm.loader = true;

      vm.newValueDisplay = false;
      vm.verified = false;
      vm.cbsBranchesOfTheBankNewScope = {};

      cbsBranchesOfTheBankService.getrecord(id).then(function (response) {
        vm.cbsBranchesOfTheBankResponse = response.data;
        vm.cbsBranchesOfTheBank = vm.cbsBranchesOfTheBankResponse.dto;

        if (vm.cbsBranchesOfTheBankResponse.auditLogDto) {
          vm.newValueDisplay = true;
          vm.verified = true;
          vm.lastModifiedBy = vm.cbsBranchesOfTheBankResponse.auditLogDto.lastModifiedBy;
          vm.lastModifiedDate = vm.cbsBranchesOfTheBankResponse.auditLogDto.lastModifiedDate;

          angular.forEach(vm.cbsBranchesOfTheBankResponse.dto, function (value, key) {
            vm.cbsBranchesOfTheBankNewScope[key] = value;
          });

          angular.forEach(vm.cbsBranchesOfTheBankResponse.auditLogDto.modifiedFieldsData, function (modifiedFieldsData) {
            vm.cbsBranchesOfTheBankNewScope[modifiedFieldsData.propertyName] = modifiedFieldsData.newValue;
          });
        }

        if (!response.data.auditLogDto && response.data.dto.verifiedFlag == false) {
          vm.verified = true;
        }

        vm.loader = false;
      }, handleErrorResponse);
    }

    function modify() {
//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to modify this record?",
//          type: "warning",
//          showCancelButton: true,
//          cancelButtonClass: "btn-cancel",
//          confirmButtonClass: "btn-primary",
//          confirmButtonText: "Yes!",
//          closeOnConfirm: false,
//          showLoaderOnConfirm: true,
//        },
//        function () {



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
          var formData = {};

          if (vm.cbsBranchesOfTheBankResponse.auditLogDto) {
            formData = vm.cbsBranchesOfTheBankNewScope;
          } else {
            formData = vm.cbsBranchesOfTheBank;
          }

          cbsBranchesOfTheBankService.modify(formData).then(function (response) {
            vm.loader = true;
            Swal.fire("Modified!", "Branch Modified Successfully", "success");
            location.href = "#!/cbs_branches_of_the_bank/action/modify";
          }, handleErrorResponse);
       } }
      );
    }

    function deleteBranchesOfTheBank(id) {
//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Are you sure want to delete this record? ",
//          type: "warning",
//          showCancelButton: true,
//          cancelButtonClass: "btn-cancel",
//          confirmButtonClass: "btn-primary",
//          confirmButtonText: "Yes!",
//          closeOnConfirm: false,
//          showLoaderOnConfirm: true,
//        },
//        function () {


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
          var formData = {};
          if (vm.cbsBranchesOfTheBankResponse.auditLogDto) {
            formData = vm.cbsBranchesOfTheBankNewScope;
          } else {
            formData = vm.cbsBranchesOfTheBank;
          }

          cbsBranchesOfTheBankService.deletebranchesOfTheBank(formData).then(function (response) {
            vm.loader = false;
            Swal.fire("Rejected!", "Branch Rejected Successfully", "success");
            location.href = "#!/cbs_branches_of_the_bank/action/delete";
          }, handleErrorResponse);
     }   }
      );
    }

    function getInquiryDetails() {
      vm.loader = true;
      var requestData = {};
      if (vm.solId != "" && vm.solId != null) {
        requestData.solId = vm.solId;
      } else {
        requestData.solId = null;
      }

      cbsBranchesOfTheBankService.inquiry(requestData).then(
        function (response) {
          vm.loader = false;
          if (vm.solId != null && vm.solId != "") {
            vm.result = response.data.result;
            vm.oldNewValueList = response.data.result.oldNewValueList[0];
          } else {
            vm.result = response.data.result;
            angular.element("#solIdModal").modal("toggle");
          }
        },
        function (error) {
          vm.loader = false;
          if ((error.data.message == "No Records Found") | (error.data.message == "NDF")) {
            vm.noDataFound = error.data.message;
          }
          handleErrorResponse(error);
          vm.result = "";
        }
      );
    }

    function getVerDelDetails() {
      vm.loader = true;
      var requestData = {};
      if (vm.solId != "" && vm.solId != null) {
        requestData.solId = vm.solId;
      } else {
        requestData.solId = null;
      }
      cbsBranchesOfTheBankService.verDelDetails(requestData).then(
        function (response) {
          vm.loader = false;
          if (vm.solId != null && vm.solId != "") {
            vm.result = response.data.result;
            vm.oldNewValueList = response.data.result.oldNewValueList[0];
            vm.pkColVal = response.data.result.pkColVal;
          } else {
            vm.result = response.data.result;
            angular.element("#solIdModal").modal("toggle");
          }
        },
        function (error) {
          vm.loader = false;
          if ((error.data.message == "No Records Found") | (error.data.message == "NDF")) {
            vm.noDataFound = error.data.message;
          }
          handleErrorResponse(error);
          vm.result = "";
        }
      );
    }
    function verify() {
//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to verify this record?",
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
            id1: vm.pkColVal,
          };
          cbsBranchesOfTheBankService.verifySubmit(requestData).then(
            function (response) {
              vm.loader = false;
              Swal.fire("Verified!", "Branch Verified Successfully", "success");
              $state.go("cbs_branches_of_the_bank.list");
            },
            function (error) {
              vm.loader = false;
              handleErrorResponse(error);
              vm.result = "";
              $state.go("cbs_branches_of_the_bank.list");
            }
          );
      }  }
      );
    }

    function rejectReason() {
      angular.element("#permissionMappingDelete").modal("toggle");
    }
    function rejectDetails() {
      /* function handleKeyPress(event) {
                var key = event.key;
                var input = event.target.value;
                var cursorPosition = event.target.selectionStart; // Get the cursor position

                // Prevent leading space at the beginning of the input
                if (key === ' ' && cursorPosition === 0) {
                    event.preventDefault();
                    return;
                }

                // Prevent multiple spaces
                if (key === ' ' && input.slice(-1) === ' ') {
                    event.preventDefault();
                    return;
                }

                // Allow only letters, space, backspace, and enter
                if (!key.match(/^[a-zA-Z\s]$/) && key !== 'Backspace' && key !== 'Enter') {
                    event.preventDefault();
                }
            }

            // Add keypress event listener
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
                    // Check for valid input: only alphabetic characters and single spaces
                    if (value.trim() === "") {
                        return "Please enter the remarks!";
                    }

                    // Check for consecutive spaces
                    if (/\s\s/.test(value)) {
                        return "Please enter only single spaces between words!";
                    }

                    // Check if the input is valid
                    if (/^[a-zA-Z\s]*$/.test(value)) {
                        // Remove keypress event listener
                        document.removeEventListener('keypress', handleKeyPress);
                        return true;
                    } else {
                        return "Please enter only alphabetic characters!";
                    }
                }
            }, function(inputValue) {
                // Handle cancel button
                if (inputValue === false) {
                    document.removeEventListener('keypress', handleKeyPress);
                    return false;
                }

                // Handle empty input
                if (inputValue === "") {
                    Swal.fire.showInputError("Please enter the remarks!");
                    return false;
                }*/

      vm.loader = true;
      var requestData = {
        id1: vm.pkColVal,
        remarks: vm.remarks,
      };

      cbsBranchesOfTheBankService.deleteSubmit(requestData).then(
        function (response) {
          vm.loader = false;
          Swal.fire("Rejected!", "Branch Rejected Successfully", "success");
          $state.go("cbs_branches_of_the_bank.list");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
          $state.go("cbs_branches_of_the_bank.list");
        }
      );
      //            });
    }

    function getRemarksList() {
      vm.referenceId = "058";
      var requestData = { referenceId: vm.referenceId };

      cbsBranchesOfTheBankService.getRemarksList(requestData).then(
        function (response) {
          vm.remarksList = response.data.result || []; // Ensure it's an array
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }

    function getModifyDetails() {
      vm.loader = true;
      var requestData = {};
      if (vm.solId != "" && vm.solId != null) {
        requestData.solId = vm.solId;
      } else {
        requestData.solId = null;
      }
      cbsBranchesOfTheBankService.modifyDetails(requestData).then(
        function (response) {
          vm.loader = false;
          if (vm.solId != null && vm.solId != "") {
            vm.result = response.data.result;
            vm.cbsBranchesOfTheBank = response.data.result;
            vm.id1 = response.data.result.id1;
          } else {
            vm.result = response.data.result;
            angular.element("#solIdModal").modal("toggle");
          }
        },
        function (error) {
          vm.loader = false;
          if ((error.data.message == "No Records Found") | (error.data.message == "NDF")) {
            vm.noDataFound = error.data.message;
          }
          handleErrorResponse(error);
          vm.result = "";
        }
      );
    }

    function modify() {
//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to modify this record?",
//          type: "warning",
//          showCancelButton: true,
//          cancelButtonClass: "btn-cancel",
//          confirmButtonClass: "btn-primary",
//          confirmButtonText: "Yes!",
//          closeOnConfirm: false,
//          showLoaderOnConfirm: true,
//        },
//        function () {


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
          cbsBranchesOfTheBankService.modify(vm.cbsBranchesOfTheBank).then(function (response) {
            vm.loader = false;
            Swal.fire("Modified!", " Branch Modified Successfully", "success");
            $state.go("cbs_branches_of_the_bank.list");
            vm.cbsBranchesOfTheBank = {};
          }, handleErrorResponse);
    }    }
      );
    }

    // Handle error response
    function handleErrorResponse(response) {
      vm.loader = false;
      var msg = response.data.message ? response.data.message : "Server Error";
      Swal.fire("Error!", msg, "error");
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
                               text: "Do you Really Want To Cancel?",
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
      }  }
      );
    }

    function getState() {
      vm.listType = "STATES OF INDIA";
      var requestData = {
        referenceId: vm.listType,
      };
      cbsBranchesOfTheBankService.getReferenceData(requestData).then(
        function (response) {
          vm.stateListValues = response.data.result;
          vm.stateList = [];
          angular.forEach(vm.stateListValues, function (item) {
            vm.stateList.push({
              subCodeDescription: item.DESCRIPTION,
              subCode: item.SUBCODE,
            });
          });
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }
  }
})();
