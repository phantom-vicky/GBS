(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$state', 'authService', '$filter', '$rootScope', 'commonService'];

    function LoginController($state, authService, $filter, $rootScope, commonService) {

        var vm = this;
        vm.login = login;
             vm.eyeLogin = eyeLogin;

        vm.disableLoginClick = false;
        vm.credentials = {};
        vm.tempGlobals = {};

 function eyeLogin()
 {
 if(document.getElementById('login-password').type == "password")
 {
document.getElementById("login-password").setAttribute("type","text");
}else
{
document.getElementById("login-password").setAttribute("type","password");
}
 }

        function login() {
            vm.loader = true;
            vm.disableLoginClick = true;
            vm.credentials.userDomain = 'STAND_ALONE';
            authService.login(vm.credentials)
                .then(loginSuccessHandler,
                    function(response) {
                        vm.loader = false;
                        vm.disableLoginClick = false;
                        if(response.data.message == "FORCING_LOGOUT"){
                            forcingLogoutConfirmation();
                        }if(response.data.message == "Failed to logout the user"){
                            vm.errorMsg = "Failed to logout the user";
                        }if(response.data.message == "Successfully Logged out previous user"){
                            vm.credentials.isForcingLogoutPreviousUser = '';
                            vm.errorMsg = "Successfully Logged out previous user";
                              //angular.element("#customAlert").modal("toggle");

                          // angular.element("#customAlert").modal("toggle");
                          // alert("Successfully Logged out previous user");
                            Swal.fire("Success!", "Successfully Logged out previous user", "success");
                           }
                        else if(vm.credentials.isForcingLogoutPreviousUser == "Y"){
                            vm.credentials.isForcingLogoutPreviousUser = '';
                            vm.errorMsg = "Failed to out previous user";
                             //alert(vm.errorMsg);
                            Swal.fire("Error!", "Failed to out previous user", "error");
                         }
                        else{
                            vm.errorMsg = response.data.message ? response.data.message : "Server Error";
                        }
                        //vm.errorMsg = response.data.message ? response.data.message : "Server Error";
                    }
                );
        }

        function loginSuccessHandler(response) {
            var result = response.data.result;
            if (result.redirectTo == 'RESET_PASSWORD') {
                $state.go('reset_password');
            } else {
                authService.sendHeartBeat().then(
                    function(res) {
                        var globals = {};
                        globals.username = vm.credentials.username;
                        globals.lastLoginTime = result.lastLoginTime;
                        globals.solDate = $filter('date')(result.solDate, "dd/MM/yyyy");
                        globals.solId = result.solId;
                        globals.solDesc = result.solDesc;
                        globals.bankName = result.bankName;
                        vm.tempGlobals = globals;
                        authService.setGlobals(globals);
                        authService.setPerms(result.permissions);
                        $rootScope.menuLists = result.menuLinks.menuItemList;

                        $state.go('index');
                        $rootScope.startIdleService();
                       // getLatestWarDeploymentInfo();

                    },
                    function(error) {
                        vm.loader = false;
                        vm.disableLoginClick = false;
                        vm.errorMsg = "Something Went Wrong...Please Try Again";
                    }
                )
            }
        }

        function getLatestWarDeploymentInfo() {
            commonService.getLatestWarDeploymentDetails()
                .then(
                    function(response) {
                        vm.warDeploymentDate = moment(response.data.result.warDeploymentDate, "DD/MM/YYYY HH:mm:ss").toDate();
                        vm.cutOffDate = moment(vm.warDeploymentDate).add(1, 'days').toDate();
                        vm.currentDate = new Date();
                        if (vm.currentDate.getTime() >= vm.warDeploymentDate.getTime() && vm.currentDate.getTime() < vm.cutOffDate.getTime()) {
                            vm.tempGlobals.warDeploymentFlag = 'Y';
                        } else {
                            vm.tempGlobals.warDeploymentFlag = 'N';
                        }
                        vm.tempGlobals.warDeploymentDate = vm.warDeploymentDate;
                        authService.setGlobals(vm.tempGlobals);
                    }
                );
        }




    function forcingLogoutConfirmation() {
//                                                         Swal.fire({
//                                                                 title: "Are you sure?",
//                                                                 text: "Are you sure want to Logout the Previous user",
//                                                                 type: "warning",
//                                                                 showCancelButton: true,
//                                                                 cancelButtonClass: "btn-cancel",
//                                                                 confirmButtonClass: "btn-primary",
//                                                                 confirmButtonText: "Yes!",
//                                                                 closeOnConfirm: false,
//                                                                 showLoaderOnConfirm: true
//                                                             },
//                                                             function() {


                                                 Swal.fire({
                                                                    title: "Are you sure?",
                                                                    text: "Are you sure want to Logout the Previous user",
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
                                                                 forcingLogout();
                                                            } });


//  if (confirm("Are you sure you want to logout the previous user?")) {
//    forcingLogout();
//}
                                                     }

        function forcingLogout() {
        vm.credentials.isForcingLogoutPreviousUser = 'Y';
        login();
            }

    }

})();