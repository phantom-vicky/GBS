(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("userCreationRoleMappingController", userCreationRoleMappingController);

  userCreationRoleMappingController.$inject = ["$scope", "$state", "$stateParams", "$filter", "$timeout", "$rootScope", "userCreationRoleMappingService", "$location"];

  function userCreationRoleMappingController($scope, $state, $stateParams, $filter, $timeout, $rootScope, userCreationRoleMappingService, $location) {
    var vm = this;

    //            vm.emailPattern = /[^@]+@[^@]+\.[a-zA-Z]{2,6}/;
    //            vm.emailPattern = /^[a-zA-Z0-9._%+-]+@(hdfcbank\.com|hdfcbank\.co\.in)$/i;
    //            vm.emailPattern = /^(?!\s)[a-zA-Z0-9._%+-]+@(hdfcbank\.com|hdfcbank\.co\.in)$/i;
    vm.emailPattern = /^(?!.*\s)[a-zA-Z0-9._%+-]+@(hdfcbank\.com|hdfcbank\.co\.in)$/i;

    vm.showCancelConfirmationToHome = showCancelConfirmationToHome;
    vm.getBranchDetails = getBranchDetails;
    vm.validateUsername = validateUsername;
    vm.validateEmployeeCode = validateEmployeeCode;
    vm.validateEmail = validateEmail;
    vm.getAllRoles = getAllRoles;
    vm.addUserConfirmation = addUserConfirmation;
    vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
    vm.itemsPerPage = "10";
    vm.deleteSubmit = deleteSubmit;
    vm.verifySubmit = verifySubmit;
    vm.verifyUserConfirmation = verifyUserConfirmation;
    vm.deleteUserConfirmation = deleteUserConfirmation;
    vm.modifyUserConfirmation = modifyUserConfirmation;
    vm.validateInput = validateInput;

    vm.userStatusMetaData = [
      { label: "INACTIVE", value: "I01" },
      { label: "ACTIVE", value: "A01" },
      { label: "DISABLED", value: "D01" },
      { label: "DORMANT", value: "D02" },
      { label: "LOCKED", value: "L01" },
      { label: "DELETED", value: "D03" },
      { label: "EXPIRED", value: "E01" },
      { label: "REVOKED", value: "R01" },
      { label: "TERMINATED", value: "T01" },
    ];
vm.userStatusMetaData2 = [
      { label: "INACTIVE", value: "I01" },
      { label: "ENABLED", value: "A01" },
      { label: "DISABLED", value: "D01" },
      { label: "DORMANT", value: "D02" },
      { label: "LOCKED", value: "L01" },
      { label: "DELETED", value: "D03" },
      { label: "EXPIRED", value: "E01" },
      { label: "REVOKED", value: "R01" },
      { label: "TERMINATED", value: "T01" },
      { label: "DISABLED", value: "D01" },
    ];
    vm.userStatusMetaDataForEnable = [
      { label: "ENABLED", value: "A01" },
      { label: "DISABLED", value: "D01" },
      { label: "TERMINATED", value: "T01" },
    ];
    vm.userStatusMetaDataForRevoke = [
      { label: "REVOKED", value: "R01" },
      { label: "DISABLED", value: "D01" },
      { label: "TERMINATED", value: "T01" },
    ];
    vm.userStatusMetaDataForTerminate = [
      { label: "TERMINATED", value: "T01" },
      { label: "REVOKED", value: "R01" },
    ];
    vm.userStatusMetaDataForDormant = [
        { label: "DISABLED", value: "D01" },
        { label: "TERMINATED", value: "T01" },
    ];

    if ($state.current.name == "usrCrtRleMpm.add") {
      vm.userDetails = {};
    }

    if ($state.current.name == "usrCrtRleMpm.list") {
      getAllUsers();
    }
    if ($state.current.name == "usrCrtRleMpm.verify") {
      getAllUsersVerifySelect();
    }
    if ($state.current.name == "usrCrtRleMpm.modify") {
      getAllUsersModifySelect();
    }
    if ($state.current.name == "usrCrtRleMpm.list_detail") {
      if ($stateParams.obj) {
        vm.userId = $stateParams.obj.id;
        if (vm.userId) {
          getUserDetails();
        } else {
          $state.go("usrCrtRleMpm.list");
        }
      } else {
        $state.go("usrCrtRleMpm.list");
      }
    }
    if ($state.current.name == "usrCrtRleMpm.verify_detail") {
      if ($stateParams.obj) {
        vm.userId = $stateParams.obj.id;
        if (vm.userId) {
          getUserDetailsVerifySelect();
        } else {
          $state.go("usrCrtRleMpm.verify");
        }
      } else {
        $state.go("usrCrtRleMpm.verify");
      }
    }
    if ($state.current.name == "usrCrtRleMpm.modify_detail") {
      if ($stateParams.obj) {
        vm.userId = $stateParams.obj.id;
        if (vm.userId) {
          getUserDetailsModifySelect();
        } else {
          $state.go("usrCrtRleMpm.modify");
        }
      } else {
        $state.go("usrCrtRleMpm.modify");
      }
    }

    function getBranchDetails() {
      vm.loader = true;

      userCreationRoleMappingService.getBranchCodes().then(
        function (response) {
          vm.loader = false;
          vm.branchCodeList = response.data.result;
          angular.element("#branchCodeModal").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function validateUsername() {
      vm.loader = true;

      userCreationRoleMappingService.getValidateUsername(vm.userDetails.username).then(
        function (response) {
          vm.loader = false;
        },
        function (error) {
          vm.loader = false;
          vm.userDetails.username = "";
          handleErrorResponse(error);
        }
      );
    }

    function validateEmployeeCode() {
      vm.loader = true;

      userCreationRoleMappingService.getValidateEmployeeCode(vm.userDetails.employeeCode).then(
        function (response) {
          vm.loader = false;
        },
        function (error) {
          vm.loader = false;
          vm.userDetails.employeeCode = "";
          handleErrorResponse(error);
        }
      );
    }

    function validateEmail() {
      vm.loader = true;

      userCreationRoleMappingService.getValidateEmail(vm.userDetails.email).then(
        function (response) {
          vm.loader = false;
        },
        function (error) {
          vm.loader = false;
          vm.userDetails.email = "";
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
      if (event.key == "'") {
        var count = 0;
        for (var i = 0; i <= currentValue.length; i++) {
          if (currentValue[i] == "'") {
            count++;
          }
        }
        if (count == 2) {
          event.preventDefault();
        }
      }

      if (!pattern.test(currentValue)) {
        event.preventDefault();
      }
    }
    vm.validateInputAddress = validateInputAddress;

    function validateInputAddress(event, id) {
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

      //        // Check if more than one apostrophe is present
      //        if ((currentValue.match(/'/g) || []').length > 1) {
      //            event.preventDefault();
      //            return;
      //        }

      //        if (!pattern.test(currentValue)) {
      //            event.preventDefault();
      //        }
    }

    function getAllRoles() {
      vm.loader = true;
      userCreationRoleMappingService.getAllRoles().then(function (response) {
        vm.loader = false;
        vm.rolesResultSet = response.data.result;
        angular.element("#roleModal").modal("toggle");
      }, handleErrorResponse);
    }

    function addUserConfirmation() {
//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to add this record ? ",
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
          addUser();
       } }
      );
    }

    function addUser() {
      var requestDate = {};
      vm.userDetails.password='Hdfcbank@1234';
      requestDate.username = vm.userDetails.username;
      requestDate.employeeCode = vm.userDetails.employeeCode;
      requestDate.employeeName = vm.userDetails.employeeName;
      requestDate.branchCode = vm.userDetails.branchCode;
      requestDate.branchName = vm.userDetails.branchName;
      requestDate.departmentCode = vm.userDetails.departmentCode;
      requestDate.departmentName = vm.userDetails.departmentName;
      requestDate.email = vm.userDetails.email;
      requestDate.mobileNo = vm.userDetails.MobileNo;
      requestDate.address = vm.userDetails.address;
      requestDate.supervisorCode = vm.userDetails.supervisorCode;
      requestDate.roleId = vm.userDetails.roleId;
      requestDate.password = $rootScope.toEncrypt(vm.userDetails.password);

      userCreationRoleMappingService.addService(requestDate).then(
        function (response) {
          //Swal.fire("Added!", vm.userDetails.username + "\tIs Added", "success");
          Swal.fire("Added!", "User Added Successfully \n 'Pending for Authorization'", "success");
          vm.userDetails = {};
          $state.go("usrCrtRleMpm.list");
        },
        function (error) {
          handleErrorResponse(error);
          $state.go("usrCrtRleMpm.list");
        }
      );
    }
    vm.validatePassword=validatePassword;
function validatePassword(password) {
  const specialCharRegex = /[!@#$%^&*(),.?":{}|<>]/;

  if (!password || password.length < 12) {
    return "Password must be at least 12 characters long.";
  }
  if (!/[A-Z]/.test(password)) {
    return "Password must contain at least one uppercase letter.";
  }
  if (!/[a-z]/.test(password)) {
    return "Password must contain at least one lowercase letter.";
  }
  if (!specialCharRegex.test(password)) {
    return "Password must contain at least one special character.";
  }
  return ""; // Password is valid
}
    function getAllUsers() {
      vm.loader = true;
      var requestData = {};
      userCreationRoleMappingService.inquiryService(requestData).then(
        function (response) {
          vm.users = response.data.result.userList;
          console.log("vm.users", vm.users);
          vm.loader = false;
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
          location.href = "#!/";
        }
      );
    }

    function getAllUsersVerifySelect() {
      vm.loader = true;
      var requestData = {};
      userCreationRoleMappingService.verifySelectService(requestData).then(
        function (response) {
          vm.users = response.data.result.userList;
          vm.loader = false;
        },
        function (error) {
          handleErrorResponse(error);
          $state.go("usrCrtRleMpm.list");
        }
      );
    }

    function getAllUsersModifySelect() {
      vm.loader = true;
      var requestData = {};
      userCreationRoleMappingService.modifySelectService(requestData).then(
        function (response) {
          vm.users = response.data.result.userList;
          vm.loader = false;
        },
        function (error) {
          handleErrorResponse(error);
          $state.go("usrCrtRleMpm.list");
        }
      );
    }

    function getUserDetails() {
      vm.loader = true;
      let requestData = {};
      requestData.userId = vm.userId;
      userCreationRoleMappingService.inquiryService(requestData).then(
        function (response) {
          vm.users = response.data.result;
          vm.users.previousOperationType=response.data.result.previousOperationType;
          console.log("vm.users", vm.users);
          vm.loader = false;
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }

    function getUserDetailsVerifySelect() {
      vm.loader = true;
      let requestData = {};
      requestData.userId = vm.userId;
      userCreationRoleMappingService.verifySelectService(requestData).then(
        function (response) {
          vm.users = response.data.result;
          console.log("vm.users", vm.users);
          vm.loader = false;
        },
        function (error) {
          handleErrorResponse(error);
          $state.go("usrCrtRleMpm.verify");
        }
      );
    }

    function getUserDetailsModifySelect() {
      vm.loader = true;
      let requestData = {};
      requestData.userId = vm.userId;
      userCreationRoleMappingService.modifySelectService(requestData).then(
        function (response) {
          //                                                            vm.users = response.data.result;
          vm.userDetails = response.data.result;
          vm.userStatus = response.data.result.userStatus;

          vm.loader = false;
        },
        function (error) {
          handleErrorResponse(error);
          $state.go("usrCrtRleMpm.modify");
        }
      );
    }

    function showCancelConfirmationToHome() {
      Swal.fire(
        {
          title: "Confirm Cancel",
          text: "Do you really want to cancel?",
          type: "warning",
          showCancelButton: true,
          cancelButtonClass: "btn-cancel",
          confirmButtonClass: "btn-danger",
          confirmButtonText: "Yes",
          cancelButtonText: "No",
          closeOnConfirm: true,
        },
        function () {
          $state.go("usrCrtRleMpm.list");
        }
      );
    }

    function handleErrorResponse(response) {
      vm.loader = false;
      var msg = response.data.message ? response.data.message : "Server Error";
      Swal.fire("Error!", msg, "error");
    }

    vm.rejectReason = rejectReason;
    function rejectReason() {
      angular.element("#permissionMappingDelete").modal("toggle");
    }

    vm.getRemarksList = function () {
      vm.referenceId = "058";
      var requestData = { referenceId: vm.referenceId };

      userCreationRoleMappingService.getRemarksList(requestData).then(
        function (response) {
          vm.remarksList = response.data.result || []; // Ensure it's an array
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    };

    function deleteSubmit() {
      vm.loader = true;
      let requestData = {};
      requestData.userId = vm.userId;
      requestData.remarks = vm.deleteRemark;
      userCreationRoleMappingService.deleteService(requestData).then(
        function (response) {
          vm.loader = false;
          Swal.fire("Rejected!", "User Data Rejected Successfully", "success");
          const backdrop = document.querySelector(".modal-backdrop");
          if (backdrop) {
            backdrop.parentNode.removeChild(backdrop);
            document.body.classList.remove("modal-open");
          }
          $state.go("usrCrtRleMpm.list");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
          const backdrop = document.querySelector(".modal-backdrop");
          if (backdrop) {
            backdrop.parentNode.removeChild(backdrop);
            document.body.classList.remove("modal-open");
          }
          $state.go("usrCrtRleMpm.verify");
        }
      );
    }

    function verifySubmit() {
      vm.loader = true;
      let requestData = {};
      requestData.userId = vm.userId;
      userCreationRoleMappingService.verifyService(requestData).then(
        function (response) {
          vm.loader = false;
          if (vm.users?.newValue?.userStatus == "T01" && vm.users.previousOperationType == "M" && vm.users?.userStatus != vm.users?.newValue?.userStatus) {
            Swal.fire("Verified!", "User id Terminated Successfully", "success");
          } else if (vm.users?.newValue?.userStatus == "D01" && vm.users.previousOperationType == "M" && vm.users?.userStatus != vm.users?.newValue?.userStatus) {
            Swal.fire("Verified!", "User id Disabled Successfully", "success");
          } else if (vm.users?.newValue?.userStatus == "A01" && vm.users.previousOperationType == "M" && vm.users?.userStatus != vm.users?.newValue?.userStatus) {
            Swal.fire("Verified!", "User id Enabled Successfully", "success");
          } else if (vm.users?.newValue?.userStatus == "R01" && vm.users.previousOperationType == "M" && vm.users?.userStatus != vm.users?.newValue?.userStatus) {
            Swal.fire("Verified!", "User id Revoked Successfully", "success");
          } else if(vm.users.previousOperationType == "M") {
            Swal.fire("Verified!", "User ID Modified Successfully", "success");
          }else {
            Swal.fire("Verified!", "User ID Created Successfully", "success");
          }
          $state.go("usrCrtRleMpm.list");
        },
        function (error) {
          handleErrorResponse(error);
          $state.go("usrCrtRleMpm.verify");
        }
      );
    }

    function modifySubmit() {
      vm.loader = true;
      var requestData = JSON.stringify(vm.userDetails);
      userCreationRoleMappingService.modifyService(requestData).then(
        function (response) {
          vm.loader = false;
          if (vm.userDetails.userStatus == "T01") {
            Swal.fire("Modified!", "User id Terminated and pending for authorization", "success");
          } else if (vm.userDetails.userStatus == "D01") {
            Swal.fire("Modified!", "User id Disabled and pending for authorization", "success");
          } else if (vm.userDetails.userStatus == "R01") {
            Swal.fire("Modified!", "User id Revoked and pending for authorization", "success");
          } else {
            Swal.fire("Modified!", "User id Enabled and pending for authorization", "success");
          }
          $state.go("usrCrtRleMpm.list");
        },
        function (error) {
          handleErrorResponse(error);
          $state.go("usrCrtRleMpm.modify");
        }
      );
    }

    function verifyUserConfirmation() {
//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to verify this record ? ",
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
          verifySubmit();
       } }
      );
    }
    //
    //            function deleteUserConfirmation(){
    //
    //                           Swal.fire({
    //                               title: "Rejection Remarks",
    //                               text: "",
    //                               type: "input",
    //                               inputPlaceholder: "Enter your remarks here",
    //                               showCancelButton: true,
    //                               cancelButtonClass: "btn-cancel",
    //                               confirmButtonClass: "btn-primary",
    //                               confirmButtonText: "Submit",
    //                               animation: "slide-from-top",
    //                               closeOnConfirm: false,
    //                               showLoaderOnConfirm: true
    //
    //
    //                           },
    //                           function(inputValue) {
    //                               if (inputValue === false) return false;
    //
    //                               if (inputValue === "") {
    //                                   Swal.fire.showInputError("Please enter the remarks!");
    //                                   return false;
    //                               }
    //
    //                               deleteSubmit(inputValue);
    //                           });
    //
    //                            }
    function deleteUserConfirmation() {
      function handleKeyPress(event) {
        var key = event.key;
        var input = event.target.value;

        if (key === " " && input.slice(-1) === " ") {
          event.preventDefault();
        }

        if (!key.match(/^[a-zA-Z\s]$/)) {
          event.preventDefault();
        }
      }

      document.addEventListener("keypress", handleKeyPress);

      Swal.fire(
        {
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
          inputValidator: function (value) {
            if (/^[a-zA-Z\s]*$/.test(value.trim())) {
              document.removeEventListener("keypress", handleKeyPress);
              return true;
            } else {
              return "Please enter only alphabetic characters!";
            }
          },
        },
        function (inputValue) {
          if (inputValue === false) {
            document.removeEventListener("keypress", handleKeyPress);
            return false;
          }

          if (inputValue === "") {
            Swal.fire.showInputError("Please enter the remarks!");
            return false;
          }

          deleteSubmit(inputValue);
        }
      );
    }

    function modifyUserConfirmation() {
//      Swal.fire(
//        {
//          title: "Are you sure?",
//          text: "Do you want to modify this record ? ",
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
          modifySubmit();
       } }
      );
    }

    // Controller function
    vm.clearFields = function () {
      if (vm.userDetails.userStatus !== "T01" && vm.userDetails.userStatus !== "D01") {
        vm.userDetails.roleName = "";
        vm.userDetails.roleId = "";
        vm.userDetails.roleDescription = "";
      }
    };

    vm.clearFields1 = function () {
      if (vm.userDetails.userStatus !== "T01" && vm.userDetails.userStatus !== "D01") {
        vm.userDetails.branchCode = "";
        vm.userDetails.branchName = "";
      }
    };
  }
})();
