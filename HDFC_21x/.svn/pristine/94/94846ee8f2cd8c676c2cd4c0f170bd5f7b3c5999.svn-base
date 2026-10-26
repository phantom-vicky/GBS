(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'cbsBranchesOfOtherBankController',
			cbsBranchesOfOtherBankController);

	cbsBranchesOfOtherBankController.$inject = [ '$scope', '$filter', '$state',
			'$stateParams', '$location', 'cbsBranchesOfOtherBankService',
			'commonService' ];

	function cbsBranchesOfOtherBankController($scope, $filter, $state,
			$stateParams, $location, cbsBranchesOfOtherBankService,
			commonService) {

		var vm = this;

		vm.cbsBranchesOfOtherBank = {};

		vm.itemsPerPage = "10";
		vm.list = list;
		vm.listUnverifiedRecords = listUnverifiedRecords;
		vm.listVerifiedRecords = listVerifiedRecords;
		vm.getRecord = getRecord;
		vm.deleteBranch = deleteBranch;
		vm.add = add;
		vm.modify = modify;
		vm.verify = verify;
		vm.getRemarksList = getRemarksList;
		vm.showCancelConfirmation	= showCancelConfirmation;
		vm.inquiryTorch	= inquiryTorch;
		vm.verifyDeleteTorch	= verifyDeleteTorch;
		vm.deleteSubmit	= deleteSubmit;
		vm.verifySubmit	= verifySubmit;
		vm.modifyTorch	= modifyTorch;
		vm.modifySubmit	= modifySubmit;
		vm.rejectReason =  rejectReason

		if ($state.current.name == 'cbs_branches_of_other_bank.inquiry'
			|| $state.current.name == 'cbs_branches_of_other_bank.modify') {
			//list();
		} else if ($state.current.name == 'cbs_branches_of_other_bank.verify'
				|| $state.current.name == 'cbs_branches_of_other_bank.delete'
				) {
			//listUnverifiedRecords();
		} else if ($state.current.name == 'cbs_branches_of_other_bank.inquiry_detail') {
		            vm.bankCode = $stateParams.obj.bankCode,
                    vm.bankIfscCode = $stateParams.obj.bankIfscCode;
					inquiryTorch();
		} else if ($state.current.name == 'cbs_branches_of_other_bank.verify_detail') {
		            vm.bankCode = $stateParams.obj.bankCode,
                    vm.bankIfscCode = $stateParams.obj.bankIfscCode;
                    verifyDeleteTorch();
		} else if ($state.current.name == 'cbs_branches_of_other_bank.delete_detail') {
		            vm.bankCode = $stateParams.obj.bankCode,
                    vm.bankIfscCode = $stateParams.obj.bankIfscCode;
		} else if ($state.current.name == 'cbs_branches_of_other_bank.modify_form') {
//				 vm.resultInquiry = $stateParams.obj.resultInquiry;
//				 vm.verifyDeleteLists = $stateParams.obj.verifyDeleteLists;
				 vm.bankCode = $stateParams.obj.bankCode,
                 vm.bankIfscCode = $stateParams.obj.bankIfscCode;
//                 vm.result= $stateParams.obj.result;
                 modifyTorch();
//				 vm.result = $stateParams.obj.result;
		}


		function modifyTorch() {
            vm.loader = true;
             var requestData = {};
              if(vm.bankCode =='') {vm.bankCode=null;}
              if(vm.bankIfscCode =='') {vm.bankIfscCode=null;}
                requestData.bankCode = vm.bankCode;
                requestData.bankIfscCode = vm.bankIfscCode;
//                requestData.branchCode = vm.branchCode;
            cbsBranchesOfOtherBankService.modifyTorch(requestData).then(function(response) {
                vm.modifyLists = response.data.result;
                vm.loader = false;
                if(response.data.message == 'SUCCESS') {
                if(vm.bankCode == null){
                angular.element('#bankCodeModel').modal('toggle');
                    vm.modifyLists = response.data.result;
                    vm.loader = false;
                }
                else if(vm.bankIfscCode == null){
                angular.element('#bankBranchModel').modal('toggle');
                    vm.modifyLists = response.data.result;
                    vm.loader = false;
                }
                else{
                vm.loader = false;
                vm.result = response.data.result[0];
                vm.cbsBranchesOfOtherBank.C001= vm.result.branchCode;
                vm.cbsBranchesOfOtherBank.C002= vm.result.bankCode;
                vm.cbsBranchesOfOtherBank.C003= vm.result.micrCentreCode;
                vm.cbsBranchesOfOtherBank.C004= vm.result.micrBankCode;
                vm.cbsBranchesOfOtherBank.C005= vm.result.micrBranchCode;
                vm.cbsBranchesOfOtherBank.C006= vm.result.branchName;
                vm.cbsBranchesOfOtherBank.C007= vm.result.branchAddr1;
                vm.cbsBranchesOfOtherBank.C008= vm.result.branchAddr2;
                vm.cbsBranchesOfOtherBank.C009= vm.result.branchAddr3;
                vm.cbsBranchesOfOtherBank.C010= vm.result.branchCityCode;
                vm.cbsBranchesOfOtherBank.C011= vm.result.branchStateCode;
                vm.cbsBranchesOfOtherBank.C012= vm.result.branchPinCode;
                vm.cbsBranchesOfOtherBank.C013= vm.result.additionalText1;
                vm.cbsBranchesOfOtherBank.C014= vm.result.additionalText2;
                vm.cbsBranchesOfOtherBank.C015= vm.result.additionalText3;
                vm.cbsBranchesOfOtherBank.C017= vm.result.bankIfscCode;
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
//                text : "Are you sure want to modify this Branch IFSC Codes Details? ",
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
                                  text : "Are you sure want to modify this Branch IFSC Codes Details? ",
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
                formData.columnValues = convertToColumnValuesArray(vm.cbsBranchesOfOtherBank)
                cbsBranchesOfOtherBankService.modifySubmit(formData).then(function(response) {
                if(response.data.message == 'SUCCESS') {
                Swal.fire("Modified!", response.data.message, "success");
                $state.go('cbs_branches_of_other_bank.inquiry');
                }
                else{
                vm.loader = false;
                Swal.fire("WARNING!", response.data.message, "error");
                $state.go('cbs_branches_of_other_bank.modify');
                }
                }, handleErrorResponse);
           } });
        }
		function verifyDeleteTorch() {
            vm.loader = true;
             var requestData = {};
              if(vm.bankCode =='') {vm.bankCode=null;}
              if(vm.bankIfscCode =='') {vm.bankIfscCode=null;}
                requestData.bankCode = vm.bankCode;
                requestData.bankIfscCode = vm.bankIfscCode;
            cbsBranchesOfOtherBankService.verifyDeleteTorch(requestData).then(function(response) {
                vm.verifyDeleteLists = response.data.result;
                vm.loader = false;
                if(response.data.message == 'SUCCESS' || response.data.message == 'success' ) {
                if(vm.bankCode == null){
                angular.element('#bankCodeModelVerifyDelete').modal('toggle');
                    vm.verifyDeleteLists1 = response.data.result;
                    vm.loader = false;
                }
                else if(vm.bankIfscCode == null){
                angular.element('#branchCodeModelVerifyDelete').modal('toggle');
                    vm.verifyDeleteLists2 = response.data.result;
                    vm.loader = false;

                }
                else{
                vm.loader = false;
                vm.verifyDeleteLists = response.data.result[0];
                console.log('vm.verifyDeleteLists',vm.verifyDeleteLists)
                }
                }else {
                    Swal.fire("Error!", response.data.message, "error");
                    vm.loader = false;
                }
            }, handleErrorResponse);
        }


        function rejectReason() {
                     angular.element('#permissionMappingDelete').modal('toggle');
                    }



        function getRemarksList() {
                                            vm.referenceId = '058';
                                            var requestData = { 'referenceId': vm.referenceId };

                                            cbsBranchesOfOtherBankService.getRemarksList(requestData).then(function(response) {
                                                vm.remarksList = response.data.result || []; // Ensure it's an array
                                            }, function(error) {
                                                handleErrorResponse(error);
                                            });
                                      }

        function inquiryTorch() {
            vm.loader = true;
             var requestData = {};
               if(vm.bankCode =='') {vm.bankCode=null;}
               if(vm.bankIfscCode =='') {vm.bankIfscCode=null;}
                requestData.bankCode = vm.bankCode;
                requestData.bankIfscCode = vm.bankIfscCode;
                cbsBranchesOfOtherBankService.inquiryTorch(requestData).then(function(response) {
                vm.inquiryLists = response.data.result;
                vm.loader = false;
                if(response.data.message == 'SUCCESS' || response.data.message == 'success' ) {

                if(vm.bankCode == null){
                angular.element('#bankCodeModelInquiry').modal('toggle');
                    vm.loader = false;
                }
                else if(vm.bankIfscCode == null){
                angular.element('#bankBranchModelInquiry').modal('toggle');
                    vm.loader = false;
                }
                else{
                vm.loader = false;
                vm.resultInquiry = response.data.result[0];
                vm.verifyDeleteLists= response.data.result[0];
                }
                }else {
                    Swal.fire("Error!", response.data.message, "error");
                    vm.loader = false;
                }
            }, handleErrorResponse);
        }

        function verifySubmit() {
//            Swal.fire({
//                title : "Are you sure?",
//                text : "Are you sure want to verify this Record? ",
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
                var formData = {};
                /*if(vm.branchIfscCodeResponse.auditLogDto){
                    formData = vm.branchIfscCodeNewScope;
                } else {
                    formData = vm.branchIfscCode;
                }*/
                formData.id = vm.verifyDeleteLists.id;
                cbsBranchesOfOtherBankService.verifySubmit(formData).then(function(response) {
                if(response.data.message == 'SUCCESS') {
                Swal.fire("Verified!", response.data.message, "success");
                $state.go('cbs_branches_of_other_bank.inquiry');
                }
                else{
                vm.loader = false;
                Swal.fire("WARNING!", response.data.message, "error");
                $state.go('cbs_branches_of_other_bank.verify');
                }
                }, handleErrorResponse);
         }   });
        }

         function deleteSubmit(remarks) {


             vm.loader = true;
                var formData = {

                 rejectionRemarks : remarks
                };
                /*if(vm.branchIfscCodeResponse.auditLogDto){
                    formData = vm.branchIfscCodeNewScope;
                } else {
                    formData = vm.branchIfscCode;
                }*/
                formData.id = vm.verifyDeleteLists.id;
                cbsBranchesOfOtherBankService.deleteSubmit(formData).then(function(response) {
                if(response.data.message == 'SUCCESS') {
                Swal.fire("Rejected", response.data.message, "success");
                $state.go('cbs_branches_of_other_bank.inquiry');
                }
                else{
                vm.loader = false;
                Swal.fire("WARNING!", response.data.message, "error");
                $state.go('cbs_branches_of_other_bank.delete');
                }
                }, handleErrorResponse);

        }


		function list() {
			vm.loader = true;
			cbsBranchesOfOtherBankService.getlist().then(function(response) {
				vm.recordsList = response.data;
				vm.loader = false;
			}, handleErrorResponse);
		}

		function listUnverifiedRecords() {
			vm.loader = true;
			cbsBranchesOfOtherBankService.getunverifiedrecords().then(
					function(response) {
						vm.recordsList = response.data;
						vm.loader = false;
					}, handleErrorResponse);
		}

		function listVerifiedRecords() {
			vm.loader = true;
			cbsBranchesOfOtherBankService.getverifiedrecords().then(
					function(response) {
						vm.recordsList = response.data;
						vm.loader = false;
					}, handleErrorResponse);
		}

		function getRecord(id) {
			vm.loader = true;
			vm.newValueDispay = false;
			vm.verified = false;
			vm.cbsBranchesOfOtherBankNewScope	= {};
			
			cbsBranchesOfOtherBankService.getrecord(id).then(
					function(response) {
						vm.cbsBranchesOfOtherBankResponse = response.data;
						vm.cbsBranchesOfOtherBank = vm.cbsBranchesOfOtherBankResponse.dto;
						if(vm.cbsBranchesOfOtherBankResponse.auditLogDto){
							vm.newValueDispay = true;
							vm.verified = true;
							vm.lastModifiedBy = vm.cbsBranchesOfOtherBankResponse.auditLogDto.lastModifiedBy;
							vm.lastModifiedDate = vm.cbsBranchesOfOtherBankResponse.auditLogDto.lastModifiedDate;
							
							angular.forEach(vm.cbsBranchesOfOtherBankResponse.dto, function(value, key){
								vm.cbsBranchesOfOtherBankNewScope[key] = value;
							});
							
							angular.forEach(vm.cbsBranchesOfOtherBankResponse.auditLogDto.modifiedFieldsData, function(modifiedFieldsData){
								vm.cbsBranchesOfOtherBankNewScope[modifiedFieldsData.propertyName] = modifiedFieldsData.newValue;
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
//			Swal.fire(
//            {
//                title : "Are you sure?",
//                text : "Are you sure want to add this Branches of Other Bank? ",
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
                                      text : "Are you sure want to add this Branches of Other Bank? ",
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
                formData.bankCode = vm.cbsBranchesOfOtherBank.C002;
                formData.branchCode = vm.cbsBranchesOfOtherBank.C001;
                formData.bankIfscCode= vm.cbsBranchesOfOtherBank.C017;
                formData.columnValues = convertToColumnValuesArray(vm.cbsBranchesOfOtherBank);
                cbsBranchesOfOtherBankService.add(
                        formData).then(
                        function(response) {
                            if(response.data.message == 'SUCCESS') {
                            Swal.fire("Added!", response.data.message, "success");
                            $state.go('cbs_branches_of_other_bank.inquiry');
                            }
                            else{
                            vm.loader = false;
                            Swal.fire("WARNING!", response.data.message, "error");
                            $state.go('cbs_branches_of_other_bank.add');
                            }
                        }, handleErrorResponse);
        }    });
		}

		// Verify
		function verify() {
//			Swal.fire(
//					{
//						title : "Are you sure?",
//						text : "Are you sure want to verify this Branches of Other Bank? ",
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
                                         text : "Are you sure want to verify this Branches of Other Bank? ",
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
						if(vm.cbsBranchesOfOtherBankResponse.auditLogDto){
							formData = vm.cbsBranchesOfOtherBankNewScope;
						} else {
							formData = vm.cbsBranchesOfOtherBank;
						}					
						cbsBranchesOfOtherBankService.verify(formData)
								.then(
										function(response) {
											Swal.fire("Verified!",
													response.data.message,
													"success");
											location.href = '#!/cbs_branches_of_other_bank/action/inquiry';
										}, handleErrorResponse);
				}	});
		}

		// delete
		function deleteBranch(id) {
//			Swal.fire(
//					{
//						title : "Are you sure?",
//						text : "Are you sure want to delete this Branches of Other Bank? ",
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
                                    text : "Are you sure want to delete this Branches of Other Bank? ",
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
						if(vm.cbsBranchesOfOtherBankResponse.auditLogDto){
							formData = vm.cbsBranchesOfOtherBankNewScope;
						} else {
							formData = vm.cbsBranchesOfOtherBank;
						}	
						cbsBranchesOfOtherBankService.deleteBranch(formData)
								.then(
										function(response) {
											Swal.fire("Deleted!",
													response.data.message,
													"success");
											location.href = '#!/cbs_branches_of_other_bank/action/inquiry';
										}, handleErrorResponse);
				}	});
		}

		// Modify
		function modify() {
//			Swal.fire(
//					{
//						title : "Are you sure?",
//						text : "Are you sure want to modify this Branches of Other Bank? ",
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
                                      text : "Are you sure want to modify this Branches of Other Bank? ",
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
						if(vm.cbsBranchesOfOtherBankResponse.auditLogDto){
							formData = vm.cbsBranchesOfOtherBankNewScope;
						} else {
							formData = vm.cbsBranchesOfOtherBank;
						}	
						cbsBranchesOfOtherBankService.modify(formData)
								.then(
										function(response) {
											Swal.fire("Modified!",
													response.data.message,
													"success");
											location.href = '#!/cbs_branches_of_other_bank/action/inquiry';
										}, handleErrorResponse);
			}		});
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
                    title: "Confirm  Cancel?",
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
						S.No
                    $state.go(state);

              }  });
        }
	}

})();
