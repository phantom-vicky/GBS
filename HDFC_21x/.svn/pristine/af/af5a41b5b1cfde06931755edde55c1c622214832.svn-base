(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'cbsUserWorkClassInformationController',
			cbsUserWorkClassInformationController);

	cbsUserWorkClassInformationController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location',
			'cbsUserWorkClassInformationService', 'commonService', '$rootScope'];

	function cbsUserWorkClassInformationController($scope, $filter, $state,
			$stateParams, $location, cbsUserWorkClassInformationService,
			commonService,$rootScope) {

		var vm = this;

		vm.cbsUserDetails = {};
		vm.itemsPerPage = "10";
		vm.dateOptions = '';
		vm.appDateOptions	= '';
		vm.appDateOptions1	= '';

		vm.list = list;
		vm.add = add;
		vm.getRecord = getRecord;
		vm.getSolDate = getSolDate;
		vm.verify = verify;
		vm.modify = modify;
		vm.deleteUser = deleteUser;
		vm.listUnverifiedRecords = listUnverifiedRecords;
		vm.listVerifiedRecords = listVerifiedRecords;
        vm.modifyTorch = modifyTorch;
        vm.modifySubmit = modifySubmit;
        vm.verifyDeleteTorch = verifyDeleteTorch;
        vm.inquiryTorch = inquiryTorch;
        vm.verifySubmit = verifySubmit;
        vm.deleteSubmit = deleteSubmit;
		vm.showCancelConfirmation	= showCancelConfirmation;



            if ($state.current.name == 'cbs_user_work_class_information.list_detail'
				|| $state.current.name == 'cbs_user_work_class_information.verify_detail'
				|| $state.current.name == 'cbs_user_work_class_information.delete_detail'
				|| $state.current.name == 'cbs_user_work_class_information.modify_form') {

				vm.result = $stateParams.obj.result
				vm.resultInquiry = $stateParams.obj.resultInquiry
				vm.verifyDeleteLists = $stateParams.obj.verifyDeleteLists
	}

    function modifyTorch() {
            vm.loader = true;
             var requestData = {};
              if(vm.userId =='') {vm.userId=null;}
                requestData.userId = vm.userId;
            cbsUserWorkClassInformationService.modifyTorch(requestData).then(function(response) {
                vm.result = response.data.result;
                vm.loader = false;
                if(response.data.message == 'SUCCESS') {
                if(vm.userId == null){
                angular.element('#modifyModel1').modal('toggle');
                    vm.modifyLists = response.data.result;
                    vm.loader = false;
                }
                else{
                vm.loader = false;
                vm.result = response.data.result[0];
                vm.result.userAccountExpiryDateOut = convertToDateObject(vm.result.userAccountExpiryDateOut);
                vm.result.userPasswordExpiryDateOut = convertToDateObject(vm.result.userPasswordExpiryDateOut);
                }
                }else {
                    Swal.fire("Error!", response.data.message, "error");
                    vm.loader = false;
                }
            }, handleErrorResponse);
        }

        function modifySubmit() {
            Swal.fire({
                title : "Are you sure?",
                text : "Are you sure want to modify this User Work Class Information? ",
                type : "warning",
                showCancelButton : true,
                cancelButtonClass : "btn-cancel",
                confirmButtonClass : "btn-primary",
                confirmButtonText : "Yes!",
                closeOnConfirm : false,
                showLoaderOnConfirm : true
            }, function() {
                var formData = {};
                /*if(vm.branchIfscCodeResponse.auditLogDto){
                    formData = vm.branchIfscCodeNewScope;
                } else {
                    formData = vm.branchIfscCode;
                }*/
                formData.id = vm.result.id;
                vm.cbsUserDetails.C007 = $rootScope.formatDateToDDMMMYYYY(vm.cbsUserDetails.C007);
                vm.cbsUserDetails.C008 = $rootScope.formatDateToDDMMMYYYY(vm.cbsUserDetails.C008);
                formData.columnValues = convertToColumnValuesArray(vm.cbsUserDetails)
                cbsUserWorkClassInformationService.modifySubmit(formData).then(function(response) {
                if(response.data.message == 'SUCCESS') {
                Swal.fire("Modified!", response.data.message, "success");
                $state.go('cbs_user_work_class_information.modify');
                }
                else{
                vm.loader = false;
                Swal.fire("WARNING!", response.data.message, "error");
                $state.go('cbs_user_work_class_information.modify');
                }
                }, handleErrorResponse);
            });
        }
		function verifyDeleteTorch() {
            vm.loader = true;
             var requestData = {};
              if(vm.userId =='') {vm.userId=null;}
                requestData.userId = vm.userId;
                cbsUserWorkClassInformationService.verifyDeleteTorch(requestData).then(function(response) {
                vm.verifyDeleteLists = response.data.result;
                vm.loader = false;
                if(response.data.message == 'SUCCESS' || response.data.message == 'success' ) {
                if(vm.userId == null){
                angular.element('#verifyDeleteModel').modal('toggle');
                    vm.verifyDeleteLists = response.data.result;
                    vm.loader = false;
                }
                else{
                vm.loader = false;
                vm.verifyDeleteLists = response.data.result[0];
                }
                }else {
                    Swal.fire("Error!", response.data.message, "error");
                    vm.loader = false;
                }
            }, handleErrorResponse);
        }

        function inquiryTorch() {
            vm.loader = true;
             var requestData = {};
               if(vm.userId =='') {vm.userId=null;}
                requestData.userId = vm.userId;
                cbsUserWorkClassInformationService.inquiryTorch(requestData).then(function(response) {
                vm.inquiryLists = response.data.result;
                vm.loader = false;
                if(response.data.message == 'SUCCESS' || response.data.message == 'success' ) {
                if(vm.userId == null){
                angular.element('#modelInquiry').modal('toggle');
                    vm.inquiryLists = response.data.result;
                    vm.loader = false;
                }
                else{
                vm.loader = false;
                vm.resultInquiry = response.data.result[0];
                }
                }else {
                    Swal.fire("Error!", response.data.message, "error");
                    vm.loader = false;
                }
            }, handleErrorResponse);
        }

        function verifySubmit() {
            Swal.fire({
                title : "Are you sure?",
                text : "Are you sure want to verify this User Work Class Information?",
                type : "warning",
                showCancelButton : true,
                cancelButtonClass : "btn-cancel",
                confirmButtonClass : "btn-primary",
                confirmButtonText : "Yes!",
                closeOnConfirm : false,
                showLoaderOnConfirm : true
            }, function() {
                var formData = {};
                /*if(vm.branchIfscCodeResponse.auditLogDto){
                    formData = vm.branchIfscCodeNewScope;
                } else {
                    formData = vm.branchIfscCode;
                }*/
                formData.id = vm.verifyDeleteLists.id;
                cbsUserWorkClassInformationService.verifySubmit(formData).then(function(response) {
                if(response.data.message == 'SUCCESS') {
                Swal.fire("Verified!", response.data.message, "success");
                $state.go('cbs_user_work_class_information.verify');
                }
                else{
                vm.loader = false;
                Swal.fire("WARNING!", response.data.message, "error");
                $state.go('cbs_user_work_class_information.verify');
                }
                }, handleErrorResponse);
            });
        }

         function deleteSubmit() {
            Swal.fire({
                title : "Are you sure?",
                text : "Are you sure want to delete this User Work Class Information? ",
                type : "warning",
                showCancelButton : true,
                cancelButtonClass : "btn-cancel",
                confirmButtonClass : "btn-primary",
                confirmButtonText : "Yes!",
                closeOnConfirm : false,
                showLoaderOnConfirm : true
            }, function() {
                var formData = {};
                /*if(vm.branchIfscCodeResponse.auditLogDto){
                    formData = vm.branchIfscCodeNewScope;
                } else {
                    formData = vm.branchIfscCode;
                }*/
                formData.id = vm.verifyDeleteLists.id;
                cbsUserWorkClassInformationService.deleteSubmit(formData).then(function(response) {
                if(response.data.message == 'SUCCESS') {
                Swal.fire("Deleted!", response.data.message, "success");
                $state.go('cbs_user_work_class_information.delete');
                }
                else{
                vm.loader = false;
                Swal.fire("WARNING!", response.data.message, "error");
                $state.go('cbs_user_work_class_information.delete');
                }
                }, handleErrorResponse);
            });
        }

		// get Sol Date
		function getSolDate() {
			commonService.getTranDate(function(response) {
				if (response.success) {
					vm.solDate = response.data.solDate;
					getDatePickerOptions(vm.solDate);
				} else {
					handleErrorResponse();
				}
			});
		}
        vm.appDateOptions = {
            minDate: $rootScope.globals.currentUser.systemDate,
            showWeeks: false
        }

         vm.appDateOptions1 = {
            minDate: $rootScope.globals.currentUser.systemDate,
            showWeeks: false
        }

		// sol Date picker options
		function getDatePickerOptions(solDate) {
			vm.dateOptions = {
				minDate : solDate,
				showWeeks : false
			};
		}

		// Add data to database
		function add() {
			Swal.fire(
					{
						title : "Are you sure?",
						text : "Are you sure want to add this User Work Class Information? ",
						type : "warning",
						showCancelButton : true,
						cancelButtonClass : "btn-cancel",
						confirmButtonClass : "btn-primary",
						confirmButtonText : "Yes!",
						closeOnConfirm : false,
						showLoaderOnConfirm : true
					}, function() {
					    var formData = {};
					    vm.cbsUserDetails.C007 = $rootScope.formatDateToDDMMMYYYY(vm.cbsUserDetails.C007);
					    vm.cbsUserDetails.C008 = $rootScope.formatDateToDDMMMYYYY(vm.cbsUserDetails.C008);
					    vm.cbsUserDetails.C001 = (vm.cbsUserDetails.C001).toUpperCase();
                        formData.columnValues = convertToColumnValuesArray(vm.cbsUserDetails);
						cbsUserWorkClassInformationService.add(
							formData).then(function(response) {
							vm.cbsUserDetails = {};
							if(response.data.message == 'SUCCESS') {
                            Swal.fire("Added!", response.data.message, "success");
                            $state.go('cbs_user_work_class_information.add');
                            }
                            else{
                            vm.loader = false;
                            Swal.fire("WARNING!", response.data.message, "error");
                            $state.go('cbs_user_work_class_information.add');
                            }
						}, handleErrorResponse);
					});
		}

		// Verify
		function verify() {
			Swal.fire(
					{
						title : "Are you sure?",
						text : "Are you sure want to verify this User Work Class Information? ",
						type : "warning",
						showCancelButton : true,
						cancelButtonClass : "btn-cancel",
						confirmButtonClass : "btn-primary",
						confirmButtonText : "Yes!",
						closeOnConfirm : false,
						showLoaderOnConfirm : true
					},
					function() {
						cbsUserWorkClassInformationService
								.verify(vm.cbsUserWorkClassInformation)
								.then(
										function(response) {
											Swal.fire("Verified!",
													response.data.message,
													"success");
											location.href = '#!/cbs_user_work_class_information/action/verify';
										}, handleErrorResponse);
					});
		}

		// delete
		function deleteUser(id) {
			Swal.fire(
					{
						title : "Are you sure?",
						text : "Are you sure want to delete this User Work Class Information? ",
						type : "warning",
						showCancelButton : true,
						cancelButtonClass : "btn-cancel",
						confirmButtonClass : "btn-primary",
						confirmButtonText : "Yes!",
						closeOnConfirm : false,
						showLoaderOnConfirm : true
					},
					function() {
						cbsUserWorkClassInformationService
								.deleteUser(vm.cbsUserWorkClassInformation)
								.then(
										function(response) {
											Swal.fire("Deleted!",
													response.data.message,
													"success");
											location.href = '#!/cbs_user_work_class_information/action/delete';
										}, handleErrorResponse);
					});
		}

		// Modify
		function modify() {
			Swal.fire(
					{
						title : "Are you sure?",
						text : "Are you sure want to modify this User Work Class Information? ",
						type : "warning",
						showCancelButton : true,
						cancelButtonClass : "btn-cancel",
						confirmButtonClass : "btn-primary",
						confirmButtonText : "Yes!",
						closeOnConfirm : false,
						showLoaderOnConfirm : true
					},
					function() {
						cbsUserWorkClassInformationService
								.modify(vm.cbsUserDetails)
								.then(
										function(response) {
											Swal.fire("Modified!",
													response.data.message,
													"success");
											location.href = '#!/cbs_user_work_class_information/action/modify';
										}, handleErrorResponse);
					});
		}

		function list() {
			vm.loader = true;
			cbsUserWorkClassInformationService.getlist().then(
					function(response) {
						vm.recordsList = response.data;
						vm.loader = false;
					}, handleErrorResponse);
		}

		function listUnverifiedRecords() {
			vm.loader = true;
			cbsUserWorkClassInformationService.getunverifiedrecords().then(
					function(response) {
						vm.recordsList = response.data;
						vm.loader = false;
					}, handleErrorResponse);
		}

		function listVerifiedRecords() {
			vm.loader = true;
			cbsUserWorkClassInformationService.getverifiedrecords().then(
					function(response) {
						vm.recordsList = response.data;
						vm.loader = false;
					}, handleErrorResponse);
		}

		function getRecord(id) {
			vm.loader = true;
			cbsUserWorkClassInformationService
					.getrecord(id)
					.then(
							function(response) {
								vm.cbsUserWorkClassInformation = response.data;
								vm.cbsUserDetails = response.data;
								if(vm.cbsUserDetails.userAccountExpiryDate)
								    vm.cbsUserDetails.userAccountExpiryDate = new Date(dateConversion( vm.cbsUserDetails.userAccountExpiryDate));
								if(vm.cbsUserDetails.userPasswordExpiryDate)
								    vm.cbsUserDetails.userPasswordExpiryDate = new Date(dateConversion( vm.cbsUserDetails.userPasswordExpiryDate));
								vm.cbsUserDetails.newUserFlag = vm.cbsUserDetails.newUserFlag
										.toString();
								vm.cbsUserDetails.virtualFlag = vm.cbsUserDetails.virtualFlag
										.toString();
								vm.loader = false;
							}, handleErrorResponse);
		}

		// Handle error response
		function handleErrorResponse(response) {
			vm.loader = false;
			var msg = response.data.message ? response.data.message
					: "Server Error";
			Swal.fire("Error!", msg, "error");
		}

		// Cancel Confirmation
        function showCancelConfirmation(state) {
            Swal.fire({
                    title: "Confirm Cancel",
                    text: "Do you really want to cancel?",
                    type: "warning",
                    showCancelButton: true,
                    cancelButtonClass: "btn-cancel",
                    confirmButtonClass: "btn-danger",
                    confirmButtonText: "Yes",
                    cancelButtonText: "No",
                    closeOnConfirm: true
                },
                function() {
                    $state.go(state);

                });
        }
	}

})();