(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'cbsListOfTransactionCodesController',
			cbsListOfTransactionCodesController);

	cbsListOfTransactionCodesController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location',
			'cbsListOfTransactionCodesService', 'commonService' ];

	function cbsListOfTransactionCodesController($scope, $filter, $state,
			$stateParams, $location, cbsListOfTransactionCodesService,
			commonService) {

		var vm = this;

		vm.cbsListOfTransactionCodes = {};
		vm.itemsPerPage = "10";
		vm.list = list;
		vm.listUnverifiedRecords = listUnverifiedRecords;
		vm.listVerifiedRecords = listVerifiedRecords;
		vm.getRecord = getRecord;
		vm.modify = modify;
		vm.verify = verify;
		vm.add = add;
		vm.deleteTransactionCodes = deleteTransactionCodes;
		vm.showCancelConfirmation = showCancelConfirmation;
		vm.modifyTorch = modifyTorch;
		vm.modifySubmit = modifySubmit;
		vm.verifyDeleteTorch = verifyDeleteTorch;
		vm.inquiryTorch = inquiryTorch;
		vm.verifySubmit = verifySubmit;
		vm.deleteSubmit = deleteSubmit;

		if ($state.current.name == 'cbs_list_of_transaction_codes.list'
			|| $state.current.name == 'cbs_list_of_transaction_codes.modify') {
			//list();
		} else if ($state.current.name == 'cbs_list_of_transaction_codes.delete'
				|| $state.current.name == 'cbs_list_of_transaction_codes.verify') {

			//listUnverifiedRecords();

		} else if ($state.current.name == 'cbs_list_of_transaction_codes.list_detail'
				|| $state.current.name == 'cbs_list_of_transaction_codes.delete_detail'
				|| $state.current.name == 'cbs_list_of_transaction_codes.verify_detail'
				|| $state.current.name == 'cbs_list_of_transaction_codes.modify_form') {

				vm.clearingTransactionCode = $stateParams.obj.clearingTransactionCode;
				vm.clearingTransactionCodeDescription = $stateParams.obj.clearingTransactionCodeDescription;
				vm.verifyDeleteLists = $stateParams.obj.verifyDeleteLists;
				vm.modifyLists = $stateParams.obj.modifyLists;
				vm.resultInquiry = $stateParams.obj.resultInquiry;

		}

		// Add data to database
		function add() {
			Swal.fire({
				title : "Are you sure?",
				text : "Are you sure want to add this Transaction code? ",
				type : "warning",
				showCancelButton : true,
				cancelButtonClass : "btn-cancel",
				confirmButtonClass : "btn-primary",
				confirmButtonText : "Yes!",
				closeOnConfirm : false,
				showLoaderOnConfirm : true
			}, function() {
			         var formData = {};
			    formData.columnValues = convertToColumnValuesArray(vm.cbsListOfTransactionCodes);
				cbsListOfTransactionCodesService.add(formData).then(function(response) {
					  if(response.data.message == 'SUCCESS') {
                        Swal.fire("Added!", response.data.message, "success");
                        $state.go('cbs_list_of_transaction_codes.add');
                        }
                        else{
                        vm.loader = false;
                        Swal.fire("WARNING!", response.data.message, "error");
                        $state.go('cbs_list_of_transaction_codes.add');
                        }
				}, handleErrorResponse);
			});
		}



        function modifyTorch() {
            vm.loader = true;
             var requestData = {};
              if(vm.clearingTransactionCode =='') {vm.clearingTransactionCode=null;}
              if(vm.clearingTransactionCodeDescription =='') {vm.clearingTransactionCodeDescription=null;}
                requestData.clgTranCode = vm.clearingTransactionCode;
                requestData.clgTranCodeDesc = vm.clearingTransactionCodeDescription;
            cbsListOfTransactionCodesService.modifyTorch(requestData).then(function(response) {
                vm.modifyLists = response.data.result;
                vm.loader = false;
                if(response.data.message == 'SUCCESS') {
                if(vm.clearingTransactionCode == null){
                angular.element('#modifyModel1').modal('toggle');
                    vm.modifyLists = response.data.result;
                    vm.loader = false;
                }
                else{
                vm.loader = false;
                vm.modifyLists = response.data.result[0];
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
                text : "Are you sure want to modify this Branch IFSC Codes Details? ",
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
                formData.id = vm.modifyLists.id;
                formData.columnValues = convertToColumnValuesArray(vm.cbsListOfTransactionCodes)
                cbsListOfTransactionCodesService.modifySubmit(formData).then(function(response) {
                if(response.data.message == 'SUCCESS') {
                Swal.fire("Modified!", response.data.message, "success");
                $state.go('cbs_list_of_transaction_codes.modify');
                }
                else{
                vm.loader = false;
                Swal.fire("WARNING!", response.data.message, "error");
                $state.go('cbs_list_of_transaction_codes.modify');
                }
                }, handleErrorResponse);
            });
        }
		function verifyDeleteTorch() {
            vm.loader = true;
             var requestData = {};
               if(vm.clearingTransactionCode =='') {vm.clearingTransactionCode=null;}
               if(vm.clearingTransactionCodeDescription =='') {vm.clearingTransactionCodeDescription=null;}
                 requestData.clgTranCode = vm.clearingTransactionCode;
                 requestData.clgTranCodeDesc = vm.clearingTransactionCodeDescription;
            cbsListOfTransactionCodesService.verifyDeleteTorch(requestData).then(function(response) {
                vm.verifyDeleteLists = response.data.result;
                vm.loader = false;
                if(response.data.message == 'SUCCESS' || response.data.message == 'success' ) {
                if(vm.clearingTransactionCode == null){
                angular.element('#verifyDeleteModel1').modal('toggle');
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
             if(vm.clearingTransactionCode =='') {vm.clearingTransactionCode=null;}
             if(vm.clearingTransactionCodeDescription =='') {vm.clearingTransactionCodeDescription=null;}
             requestData.clgTranCode = vm.clearingTransactionCode;
             requestData.clgTranCodeDesc = vm.clearingTransactionCodeDescription;
                cbsListOfTransactionCodesService.inquiryTorch(requestData).then(function(response) {
                vm.inquiryLists = response.data.result;
                vm.loader = false;
                if(response.data.message == 'SUCCESS' || response.data.message == 'success' ) {
                if(vm.clearingTransactionCode == null){
                angular.element('#inquiryModel1').modal('toggle');
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
                text : "Are you sure want to verify this Branch IFSC Codes Details? ",
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
                cbsListOfTransactionCodesService.verifySubmit(formData).then(function(response) {
                if(response.data.message == 'SUCCESS') {
                Swal.fire("Verified!", response.data.message, "success");
                $state.go('cbs_list_of_transaction_codes.verify');
                }
                else{
                vm.loader = false;
                Swal.fire("WARNING!", response.data.message, "error");
                $state.go('cbs_list_of_transaction_codes.verify');
                }
                }, handleErrorResponse);
            });
        }

         function deleteSubmit() {
            Swal.fire({
                title : "Are you sure?",
                text : "Are you sure want to delete this Branch IFSC Codes Details? ",
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
                cbsListOfTransactionCodesService.deleteSubmit(formData).then(function(response) {
                if(response.data.message == 'SUCCESS') {
                Swal.fire("Deleted!", response.data.message, "success");
                $state.go('cbs_list_of_transaction_codes.delete');
                }
                else{
                vm.loader = false;
                Swal.fire("WARNING!", response.data.message, "error");
                $state.go('cbs_list_of_transaction_codes.delete');
                }
                }, handleErrorResponse);
            });
        }
        function modify() {
			Swal.fire({
					title : "Are you sure?",
					text : "Are you sure want to modify this Transaction code? ",
					type : "warning",
					showCancelButton : true,
					cancelButtonClass : "btn-cancel",
					confirmButtonClass : "btn-primary",
					confirmButtonText : "Yes!",
					closeOnConfirm : false,
					showLoaderOnConfirm : true
				},
				function() {

					var formData = {};

					if(vm.cbsListOfTransactionCodesResponse.auditLogDto){
						formData = vm.cbsListOfTransactionCodesNewScope;
					} else {
						formData = vm.cbsListOfTransactionCodes;
					}

					cbsListOfTransactionCodesService.modify(formData)
						.then(function(response) {
									Swal.fire("Modified!", response.data.message, "success");
									location.href = '#!/cbs_list_of_transaction_codes/action/modify';
						}, handleErrorResponse);
				});
		}

		function list() {
			vm.loader = true;
			cbsListOfTransactionCodesService.getlist().then(function(response) {
				vm.recordsList = response.data;
				vm.loader = false;
			}, handleErrorResponse);
		}

		function listUnverifiedRecords() {
			vm.loader = true;
			cbsListOfTransactionCodesService.getunverifiedrecords().then(
					function(response) {
						vm.recordsList = response.data;
						vm.loader = false;
					}, handleErrorResponse);
		}

		function listVerifiedRecords() {
			vm.loader = true;
			cbsListOfTransactionCodesService.getverifiedrecords().then(
					function(response) {
						vm.recordsList = response.data;
						vm.loader = false;
					}, handleErrorResponse);
		}

		function getRecord(id) {
			vm.loader = true;
			
			vm.newValueDisplay = false;
			vm.verified = false;
			vm.cbsListOfTransactionCodesNewScope	= {};
			
			cbsListOfTransactionCodesService.getrecord(id).then(
				function(response) {
					vm.cbsListOfTransactionCodesResponse = response.data;
					vm.cbsListOfTransactionCodes = vm.cbsListOfTransactionCodesResponse.dto;
					
					if(vm.cbsListOfTransactionCodesResponse.auditLogDto){
						vm.newValueDisplay = true;
						vm.verified = true;
						vm.lastModifiedBy = vm.cbsListOfTransactionCodesResponse.auditLogDto.lastModifiedBy;
						vm.lastModifiedDate = vm.cbsListOfTransactionCodesResponse.auditLogDto.lastModifiedDate;
						
						angular.forEach(vm.cbsListOfTransactionCodesResponse.dto, function(value, key){
							vm.cbsListOfTransactionCodesNewScope[key] = value;
						});
						
						angular.forEach(vm.cbsListOfTransactionCodesResponse.auditLogDto.modifiedFieldsData, function(modifiedFieldsData){
							vm.cbsListOfTransactionCodesNewScope[modifiedFieldsData.propertyName] = modifiedFieldsData.newValue;
						});
					}
					
					if(!response.data.auditLogDto && !response.data.dto.isVerified){
						vm.verified = true;
					}
					
					vm.loader = false;
					
				}, handleErrorResponse);
		}


		function deleteTransactionCodes(id) {
			Swal.fire({
					title : "Are you sure?",
					text : "Are you sure want to delete this Transaction code? ",
					type : "warning",
					showCancelButton : true,
					cancelButtonClass : "btn-cancel",
					confirmButtonClass : "btn-primary",
					confirmButtonText : "Yes!",
					closeOnConfirm : false,
					showLoaderOnConfirm : true
				},
				function() {
					cbsListOfTransactionCodesService.deletetransactioncodes(vm.cbsListOfTransactionCodes)
						.then(function(response) {
								Swal.fire("Deleted!", response.data.message, "success");
								location.href = '#!/cbs_list_of_transaction_codes/action/delete';
						}, handleErrorResponse);
				});
		}

		function verify() {
			Swal.fire({
				title : "Are you sure?",
				text : "Are you sure want to verify this Transaction code? ",
				type : "warning",
				showCancelButton : true,
				cancelButtonClass : "btn-cancel",
				confirmButtonClass : "btn-primary",
				confirmButtonText : "Yes!",
				closeOnConfirm : false,
				showLoaderOnConfirm : true
			},
			function() {
				cbsListOfTransactionCodesService.verify(vm.cbsListOfTransactionCodes)
					.then(function(response) {
						Swal.fire("Verified!", response.data.message, "success");
						location.href = '#!/cbs_list_of_transaction_codes/action/verify';
					}, handleErrorResponse);
			});
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