(function() {

	'use strict';

	angular.module('gbmUltraApp').controller('cbsOtherBankDetailsController',
			cbsOtherBankDetailsController);

	cbsOtherBankDetailsController.$inject = [ '$scope', '$filter', '$state',
			'$stateParams', '$location', 'cbsOtherBankDetailsService',
			'commonService' ];

	function cbsOtherBankDetailsController($scope, $filter, $state,
			$stateParams, $location, cbsOtherBankDetailsService, commonService) {

		var vm = this;

		vm.cbsOtherBankDetails = {};
		vm.itemsPerPage = "10";
		vm.list = list;
		vm.listUnverifiedRecords = listUnverifiedRecords;
		vm.listVerifiedRecords = listVerifiedRecords;
		vm.getRecord = getRecord;
		vm.deleteOtherBank = deleteOtherBank;
		vm.modify = modify;
		vm.add = add;
		vm.modifyTorch = modifyTorch;
		vm.modifySubmit = modifySubmit;
		vm.verifyDeleteTorch = verifyDeleteTorch;
		vm.inquiryTorch = inquiryTorch;
		vm.verifySubmit = verifySubmit;
		vm.deleteSubmit = deleteSubmit;
		vm.showCancelConfirmation	= showCancelConfirmation;
		vm.rejectReason	= rejectReason;
		vm.rejectDetails = rejectDetails;
		vm.getRemarksList  = getRemarksList;

		if ($state.current.name == 'cbs_other_bank_details.list_detail'
        			) {

        			vm.bankCode = $stateParams.obj.bankCode;
        			//list();

        			inquiryTorch();
        		}

		if (
			$state.current.name == 'cbs_other_bank_details.modify') {
			//list();
		} else if ($state.current.name == 'cbs_other_bank_details.verify'
				|| $state.current.name == 'cbs_other_bank_details.delete'
				) {
			//listUnverifiedRecords();
		} else if ($state.current.name == 'cbs_other_bank_details.verify_detail'
				|| $state.current.name == 'cbs_other_bank_details.delete_detail'
				|| $state.current.name == 'cbs_other_bank_details.modify_form') {

                vm.rejectionRemarks = $stateParams.obj.rejectionRemarks;
				vm.result = $stateParams.obj.result;
			//	vm.resultInquiry = $stateParams.obj.resultInquiry;
				vm.verifyDeleteLists = $stateParams.obj.verifyDeleteLists;
			/*if($stateParams.obj){
				vm.id = $stateParams.obj.id;
				if (vm.id) {
					getRecord(vm.id);
				}
			} else {
				$state.go('cbs_other_bank_details.list');
			}*/
		}
        function modifyTorch() {
            vm.loader = true;
             var requestData = {};
              if(vm.bankCode =='') {vm.bankCode=null;}
                requestData.bankCode = vm.bankCode;
            cbsOtherBankDetailsService.modifyTorch(requestData).then(function(response) {
            vm.loader = false;
                vm.result = response.data.result;

                if(response.data.message == 'SUCCESS') {
                if(vm.bankCode == null){
                angular.element('#bankCodeModel').modal('toggle');
                    vm.modifyLists = response.data.result;
                    vm.loader = false;
                }
                else{
                vm.loader = false;
                vm.result = response.data.result[0];
                vm.rejectionRemarks = response.data.result[0].rejectionRemarks;
                }
                }else {
                    Swal.fire("Error!", response.data.message, "error");
                    vm.loader = false;
                }
            }, handleErrorResponse);
        }

        function modifySubmit() {
//            Swal.fire({
//                title : "Are you sure?",
//                text : "Are you sure want to modify this Other Bank Details? ",
//                type : "warning",
//                showCancelButton : true,
//                cancelButtonClass : "btn-cancel",
//                confirmButtonClass : "btn-primary",
//                confirmButtonText : "Yes!",
//                closeOnConfirm : false,
//                showLoaderOnConfirm : true
//            }, function() {


Swal.fire({
                    title: "Are you sure?",
                   text : "Are you sure want to modify this Other Bank Details? ",
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
                var formData = {};
                /*if(vm.branchIfscCodeResponse.auditLogDto){
                    formData = vm.branchIfscCodeNewScope;
                } else {
                    formData = vm.branchIfscCode;
                }*/
                formData.id = vm.result.id;
                formData.columnValues = convertToColumnValuesArray(vm.cbsOtherBankDetails)
                cbsOtherBankDetailsService.modifySubmit(formData).then(function(response) {
                if(response.data.message == 'SUCCESS') {
                Swal.fire("Modified!", response.data.message, "success");
                $state.go('cbs_other_bank_details.modify');
                }
                else{
                vm.loader = false;
                Swal.fire("WARNING!", response.data.message, "error");
                $state.go('cbs_other_bank_details.modify');
                }
                }, handleErrorResponse);
          }  });
        }
		function verifyDeleteTorch() {
            vm.loader = true;
             var requestData = {};
              if(vm.bankCode =='') {vm.bankCode=null;}
                requestData.bankCode = vm.bankCode;
            cbsOtherBankDetailsService.verifyDeleteTorch(requestData).then(function(response) {
                vm.verifyDeleteLists = response.data.result;
                vm.loader = false;
                if(response.data.message == 'SUCCESS' || response.data.message == 'success' ) {
                if(vm.bankCode == null){
                angular.element('#bankCodeModelVerifyDelete').modal('toggle');
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
               if(vm.bankCode =='') {vm.bankCode=null;}
                requestData.bankCode = vm.bankCode;
                cbsOtherBankDetailsService.inquiryTorch(requestData).then(function(response) {
                 vm.loader = false;
                vm.inquiryLists = response.data.result;
                  vm.rejectionRemarks = vm.inquiryLists.rejectionRemarks;

                if(response.data.message == 'SUCCESS' || response.data.message == 'success' ) {
                if(vm.bankCode == null){
                angular.element('#bankCodeModelInquiry').modal('toggle');
                    vm.inquiryLists = response.data.result;
                     vm.rejectionRemarks = '';
                     vm.rejectionRemarks = vm.inquiryLists.rejectionRemarks;
                    vm.loader = false;
                }
                else{
                vm.loader = false;
                vm.resultInquiry = response.data.result[0];
                  vm.rejectionRemarks = '';

                  vm.rejectionRemarks = vm.resultInquiry.rejectionRemarks;



                }
                }else {
                    Swal.fire("Error!", response.data.message, "error");
                    vm.loader = false;
                }
            }, handleErrorResponse);
        }

            function rejectReason() {
                    angular.element("#permissionMappingDelete").modal("toggle");
                }
        		function rejectDetails() {
        			/*function handleKeyPress(event) {
        				var key = event.key;
        				var input = event.target.value;

        				// Prevent entering more than one consecutive space
        				if (key === ' ' && (input.length === 0 || input.slice(-1) === ' ')) {
        					event.preventDefault();
        				}

        				// Allow only alphabetic characters, single spaces, and backspace
        				if (!key.match(/^[a-zA-Z\s]$/) && key !== 'Backspace' && key !== 'Enter') {
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
        				inputValidator: function(value) {
        					if (!value || value.trim() === "") {
        						return "Please enter the remarks!";
        					}

        					// Trim spaces and check for leading or trailing spaces
        					const trimmedValue = value.trim();

        					// Ensure no leading/trailing spaces and valid content
        					if (trimmedValue.length > 0 && /^[a-zA-Z\s]*$/.test(trimmedValue)) {
        						if (/\s\s/.test(trimmedValue)) {
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

        				// Check if input is empty after trimming
        				const trimmedValue = inputValue.trim();
        				if (trimmedValue === "") {
        					Swal.fire.showInputError("Please enter the remarks!");
        					return false;
        				}*/
        				vm.loader = true;
                var formData = {};
        				vm.rejectionRemarks = vm.remarks;
                        formData.id = vm.verifyDeleteLists.id;
        				var requestData = {

        					'rejectionRemarks': vm.rejectionRemarks,
        					'id': formData.id
        				};

        				  cbsOtherBankDetailsService.deleteSubmit(requestData).then(
        					(response) => {
        						vm.loader = false;
        						Swal.fire("Rejected!", "Rejected Successfully", "success");
                                $state.go('cbs_other_bank_details.list');        					},
        					function(error) {
        						vm.loader = false;
        						handleErrorResponse(error);
        					}
        				);
//        			});
        		}


        function getRemarksList() {
              vm.referenceId = "058";
              var requestData = { referenceId: vm.referenceId };

              cbsOtherBankDetailsService.getRemarksList(requestData).then(
                function (response) {
                  vm.remarksList = response.data.result || []; // Ensure it's an array
                },
                function (error) {
                  handleErrorResponse(error);
                }
              );
            };
        function verifySubmit() {
//            Swal.fire({
//                title : "Are you sure?",
//                text : "Are you sure want to verify this Other Bank Details?",
//                type : "warning",
//                showCancelButton : true,
//                cancelButtonClass : "btn-cancel",
//                confirmButtonClass : "btn-primary",
//                confirmButtonText : "Yes!",
//                closeOnConfirm : false,
//                showLoaderOnConfirm : true
//            }, function() {

 Swal.fire({
                    title: "Are you sure?",
                    text : "Are you sure want to verify this Other Bank Details?",
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
                var formData = {};
                /*if(vm.branchIfscCodeResponse.auditLogDto){
                    formData = vm.branchIfscCodeNewScope;
                } else {
                    formData = vm.branchIfscCode;
                }*/
                formData.id = vm.verifyDeleteLists.id;
                cbsOtherBankDetailsService.verifySubmit(formData).then(function(response) {
                if(response.data.message == 'SUCCESS') {
                Swal.fire("Verified!", response.data.message, "success");
                $state.go('cbs_other_bank_details.verify');
                }
                else{
                vm.loader = false;
                Swal.fire("WARNING!", response.data.message, "error");
                $state.go('cbs_other_bank_details.verify');
                }
                }, handleErrorResponse);
          }  });
        }

         function deleteSubmit() {
//            Swal.fire({
//                title : "Are you sure?",
//                text : "Are you sure want to delete this Other Bank Details? ",
//                type : "warning",
//                showCancelButton : true,
//                cancelButtonClass : "btn-cancel",
//                confirmButtonClass : "btn-primary",
//                confirmButtonText : "Yes!",
//                closeOnConfirm : false,
//                showLoaderOnConfirm : true
//            }, function() {

       Swal.fire({
                          title: "Are you sure?",
                          text : "Are you sure want to Delete this Other Bank Details?",
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
                var formData = {};
                /*if(vm.branchIfscCodeResponse.auditLogDto){
                    formData = vm.branchIfscCodeNewScope;
                } else {
                    formData = vm.branchIfscCode;
                }*/
                formData.id = vm.verifyDeleteLists.id;
                cbsOtherBankDetailsService.deleteSubmit(formData).then(function(response) {
                if(response.data.message == 'SUCCESS') {
                Swal.fire("Deleted!", response.data.message, "success");
                $state.go('cbs_other_bank_details.delete');
                }
                else{
                vm.loader = false;
                Swal.fire("WARNING!", response.data.message, "error");
                $state.go('cbs_other_bank_details.delete');
                }
                }, handleErrorResponse);
           } });
        }

		function list() {
			vm.loader = true;
			cbsOtherBankDetailsService.getlist().then(function(response) {
				vm.recordsList = response.data;
				vm.loader = false;
			}, handleErrorResponse);
		}

		function listUnverifiedRecords() {
			vm.loader = true;
			cbsOtherBankDetailsService.getunverifiedrecords().then(
					function(response) {
						vm.recordsList = response.data;
						vm.loader = false;
					}, handleErrorResponse);
		}

		function listVerifiedRecords() {
			vm.loader = true;
			cbsOtherBankDetailsService.getverifiedrecords().then(
					function(response) {
						vm.recordsList = response.data;
						vm.loader = false;
					}, handleErrorResponse);
		}

		function getRecord(id) {
			vm.loader = true;
			
			vm.newValueDisplay = false;
			vm.verified = false;
			vm.cbsOtherBankDetailsNewScope	= {};
			
			cbsOtherBankDetailsService.getrecord(id).then(function(response) {
				
				vm.cbsOtherBankDetailsResponse = response.data;
				vm.cbsOtherBankDetails = vm.cbsOtherBankDetailsResponse.dto;
				
				if(vm.cbsOtherBankDetailsResponse.auditLogDto){
					vm.newValueDisplay = true;
					vm.verified = true;
					vm.lastModifiedBy = vm.cbsOtherBankDetailsResponse.auditLogDto.lastModifiedBy;
					vm.lastModifiedDate = vm.cbsOtherBankDetailsResponse.auditLogDto.lastModifiedDate;
					
					angular.forEach(vm.cbsOtherBankDetailsResponse.dto, function(value, key){
						vm.cbsOtherBankDetailsNewScope[key] = value;
					});
					
					angular.forEach(vm.cbsOtherBankDetailsResponse.auditLogDto.modifiedFieldsData, function(modifiedFieldsData){
						vm.cbsOtherBankDetailsNewScope[modifiedFieldsData.propertyName] = modifiedFieldsData.newValue;
					});
					
				}
				
				if(!response.data.auditLogDto && response.data.dto.verified == false){
					vm.verified = true;
				}
				vm.loader = false;
			}, handleErrorResponse);
		}

	
		// Add data to database
		function add() {
//			Swal.fire({
//				title : "Are you sure?",
//				text : "Are you sure want to add this Other Bank Details? ",
//				type : "warning",
//				showCancelButton : true,
//				cancelButtonClass : "btn-cancel",
//				confirmButtonClass : "btn-primary",
//				confirmButtonText : "Yes!",
//				closeOnConfirm : false,
//				showLoaderOnConfirm : true
//			}, function() {


                Swal.fire({
                                   title: "Are you sure?",
                                   text : "Are you sure want to Add this Other Bank Details?",
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
                var formData = {};
                formData.bankCode = vm.cbsOtherBankDetails.C002;
                formData.columnValues = convertToColumnValuesArray(vm.cbsOtherBankDetails);
				cbsOtherBankDetailsService.add(formData).then(
						function(response) {
						vm.cbsOtherBankDetails = {};
						 if(response.data.message == 'SUCCESS') {
                            Swal.fire("Added!", response.data.message, "success");
                            $state.go('cbs_other_bank_details.add');
                            }
                            else{
                            vm.loader = false;
                            Swal.fire("WARNING!", response.data.message, "error");
                            $state.go('cbs_other_bank_details.add');
                            }
						}, handleErrorResponse);
		}	});
		}


		// Verify
		function verify() {
//			Swal.fire(
//					{
//						title : "Are you sure?",
//						text : "Are you sure want to verify this Other Bank Details? ",
//						type : "warning",
//						showCancelButton : true,
//						cancelButtonClass : "btn-cancel",
//						confirmButtonClass : "btn-primary",
//						confirmButtonText : "Yes!",
//						closeOnConfirm : false,
//						showLoaderOnConfirm : true
//					},
//					function() {



                     Swal.fire({
                                        title: "Are you sure?",
                                        text : "Are you sure want to verify this Other Bank Details?",
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
						var formData = {};
						
						if(vm.cbsOtherBankDetailsResponse.auditLogDto){
							formData = vm.cbsOtherBankDetailsNewScope;
						} else {
							formData = vm.cbsOtherBankDetails;
						}
						cbsOtherBankDetailsService.verify(formData)
								.then(
										function(response) {
											Swal.fire("Verified!",
													response.data.message,
													"success");
											location.href = '#!/cbs_other_bank_details/action/verify';
										}, handleErrorResponse);
				}	});
		}

		// delete
		function deleteOtherBank(id) {
//			Swal.fire({
//				title : "Are you sure?",
//				text : "Are you sure want to delete this Other Bank Details? ",
//				type : "warning",
//				showCancelButton : true,
//				cancelButtonClass : "btn-cancel",
//				confirmButtonClass : "btn-primary",
//				confirmButtonText : "Yes!",
//				closeOnConfirm : false,
//				showLoaderOnConfirm : true
//			}, function() {


             Swal.fire({
                                title: "Are you sure?",
                                text : "Are you sure want to Delete this Other Bank Details?",
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
				cbsOtherBankDetailsService.deleteOtherBank(
						vm.cbsOtherBankDetails).then(function(response) {
					Swal.fire("Deleted!", response.data.message, "success");
					location.href = '#!/cbs_other_bank_details/action/delete';
				}, handleErrorResponse);
		}	});
		}

		// Modify
		function modify() {
//			Swal.fire(
//					{
//						title : "Are you sure?",
//						text : "Are you sure want to modify this Other Bank Details? ",
//						type : "warning",
//						showCancelButton : true,
//						cancelButtonClass : "btn-cancel",
//						confirmButtonClass : "btn-primary",
//						confirmButtonText : "Yes!",
//						closeOnConfirm : false,
//						showLoaderOnConfirm : true
//					},
//					function() {



               Swal.fire({
                                  title: "Are you sure?",
                                  text : "Are you sure want to Modify this Other Bank Details?",
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
						var formData = {};
						
						if(vm.cbsOtherBankDetailsResponse.auditLogDto){
							formData = vm.cbsOtherBankDetailsNewScope;
						} else {
							formData = vm.cbsOtherBankDetails;
						}
						cbsOtherBankDetailsService
								.modify(formData)
								.then(
										function(response) {
											Swal.fire("Modified!",
													response.data.message,
													"success");
											location.href = '#!/cbs_other_bank_details/action/modify';
										}, handleErrorResponse);
				}	});
		}

		// Handle error response
		function handleErrorResponse(response) {
			vm.loader = false;
			var msg = response.data.message ? response.data.message
					: "Server Error";
			//Swal.fire("Error!", msg, "error");

            if(response.status == 422)
               Swal.fire("Warning",msg,"warning");
            else
                Swal.fire("Error!", msg, "error");
		}
		
		// Cancel Confirmation
        function showCancelConfirmation(state) {
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
                             title: "Confirm Cancel?",
                             text : "Do you Really Want To Cancel?",
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

               } });
        }

	}

})();