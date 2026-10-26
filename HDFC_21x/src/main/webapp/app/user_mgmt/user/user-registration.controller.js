(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .controller('UserRegistrationController', UserRegistrationController);

    UserRegistrationController.$inject = ['$state', 'adminService'];

    function UserRegistrationController($state, adminService) {

        var vm = this;
        vm.showCancelConfirmation = showCancelConfirmation;
        vm.addUserConfirmation = addUserConfirmation;

        vm.userTypeList = [
            {value: 'B', label: 'BANK'},
            {value: 'C', label: 'CONTRACT'}
        ]

       vm.dateOptions = {
            minDate: new Date(),
            showWeeks: false
        };

        function addUserConfirmation() {
//            Swal.fire({
//                    title: "Are you sure?",
//                    text: "Are you sure want to do user registration? ",
//                    type: "warning",
//                    showCancelButton: true,
//                    cancelButtonClass: "btn-cancel",
//                    confirmButtonClass: "btn-primary",
//                    confirmButtonText: "Yes!",
//                    closeOnConfirm: false,
//                    showLoaderOnConfirm: true
//                },
//                function() {


                    Swal.fire({
                                        title: "Are you sure?",
                                          text: "Are you sure want to do user registration? ",
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
              }  });
        }

        function addUser() {
           /* if (vm.userDetails.userDomain == 'Finacle') {
                vm.userDetails.confirmPassword = 'Accel@123';
                vm.userDetails.password = 'Accel@123';
            } else {
            vm.userDetails.confirmPassword = 'Accel@123';
            vm.userDetails.password = 'Accel@123';
            }*/
            adminService.addUser(vm.userDetails)
                .then(
                    function(response) {
                    Swal.fire("Added!",vm.userDetails.username + "\tIs Added And Same Is Pending For Authorization", "success");
                       // Swal.fire(vm.userDetails.username+" Is Added And Same Is Pending For Authorization", 'success');
                        vm.userDetails = {};
                        $state.go('user.list', {'action': 'inquiry'})
                    }, handleErrorResponse
                );
        }

        function handleErrorResponse(response) {
            vm.loader = false;
            var msg = response.data.message ? response.data.message : "Server Error";
            Swal.fire("Error!", msg, "error");

        }

        function showCancelConfirmation() {
//            Swal.fire({
//                    title: "Confirm Cancel",
//                    text: "Do you really want to cancel?",
//                    type: "warning",
//                    showCancelButton: true,
//                    cancelButtonClass: "btn-cancel",
//                    confirmButtonClass: "btn-danger",
//                    confirmButtonText: "Yes",
//                    cancelButtonText: "No",
//                    closeOnConfirm: true
//                },
//                function() {

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
                    $state.go('user.list', {
                        'action': 'inquiry'
                    })

              }  });
        }


    }

})();