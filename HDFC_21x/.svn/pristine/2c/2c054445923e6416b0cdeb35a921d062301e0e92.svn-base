(function() {

	'use strict';

	angular.module('gbmUltraApp').controller('branchIfscCodeController',
			branchIfscCodeController);

	branchIfscCodeController.$inject = [ '$scope', '$filter', '$state',
			'$stateParams', '$location', 'branchIfscCodeService',
			'commonService' ];

	function branchIfscCodeController($scope, $filter, $state, $stateParams,
			$location, branchIfscCodeService, commonService) {

		var vm = this;

		vm.branchIfscCode = {};

		vm.itemsPerPage = "10";
		vm.searchInquiryFn = searchInquiryFn;
		vm.generateList = generateList;
		vm.add = add;
		vm.list = list;
		vm.listUnverifiedRecords = listUnverifiedRecords;
		vm.listVerifiedRecords = listVerifiedRecords;
		vm.modify = modify;
		vm.verify = verify;
		vm.deleteBranchIfscCodes = deleteBranchIfscCodes;
		vm.getRecord = getRecord;
		vm.showCancelConfirmation	= showCancelConfirmation;
        vm.modifyTorch=modifyTorch;
        vm.inquiryTorch=inquiryTorch;
        vm.verifyDeleteTorch=verifyDeleteTorch;
        vm.verifySubmit=verifySubmit;
        vm.deleteSubmit=deleteSubmit;
		if ($state.current.name == 'branch_ifsc_codes.list'){
			//list();
		} else if ($state.current.name == 'branch_ifsc_codes.delete' ||
			$state.current.name == 'branch_ifsc_codes.verify' ) {
			//listUnverifiedRecords();
		} else if ($state.current.name == 'branch_ifsc_codes.list_detail'
				|| $state.current.name == 'branch_ifsc_codes.delete_detail'
				|| $state.current.name == 'branch_ifsc_codes.verify_detail'
				|| $state.current.name == 'branch_ifsc_codes.modify_detail') {
                    vm.bankCode = $stateParams.obj.bankCode;
                    vm.branchCode = $stateParams.obj.branchCode;
                    vm.result = $stateParams.obj.result;
                    vm.resultInquiry = $stateParams.obj.resultInquiry;
                    vm.verifyDeleteLists = $stateParams.obj.verifyDeleteLists;
			//if(vm.bankCode && vm.branchCode){
                //vm.modifyTorch();

			//}
			/*if($stateParams.obj){
				vm.id = $stateParams.obj.id;
				if (vm.id) {
					getRecord(vm.id);
				}
			}*/
	/*		else {
				$state.go('branch_ifsc_codes.list');
			}*/
		}



		function searchInquiryFn(searchKeyword) {
			vm.searchKeyword = searchKeyword;
			vm.recordsList = $filter('filter')
					(vm.baseRecordList, vm.searchKeyword);
			vm.currentPage = 1;
			vm.totalItems = vm.recordsList.length;
		}

		function generateList(response) {
			var data = [];
			angular.forEach(response, function(value, item) {
				var tempdata = {};
				tempdata.instrumentId = value.instrumentId;
				tempdata.drCrIndicator = value.drCrIndicator;
				tempdata.instrumentType = value.instrumentType;
				tempdata.schemeCode = value.schemeCode;
				tempdata.additionalText1 = value.additionalText1;
				tempdata.additionalText2 = value.additionalText2;
				tempdata.additionalText3 = value.additionalText3;
				tempdata.createdDate = $filter('date')(value.createdDate,
						'dd/MM/yyyy');
				tempdata.verified = value.verified;
				data.push(tempdata);
			});
			vm.baseRecordList = data;
			vm.recordsList = vm.baseRecordList;
			vm.totalItems = vm.recordsList.length;
		}

		// Add data to database
		function add() {
			Swal.fire({
				title : "Are you sure?",
				text : "Are you sure want to add this Branch IFSC Codes Details? ",
				type : "warning",
				showCancelButton : true,
				cancelButtonClass : "btn-cancel",
				confirmButtonClass : "btn-primary",
				confirmButtonText : "Yes!",
				closeOnConfirm : false,
				showLoaderOnConfirm : true
			}, function() {
				branchIfscCodeService.add(vm.branchIfscCode).then(function(response) {
				    vm.loader = false;
					vm.branchIfscCode = {};
					if(response.data.message == 'SUCCESS'){
					Swal.fire("Added!", response.data.message, "success");
					$state.go('branch_ifsc_codes.add');
					}
					else{
					vm.loader = false;
					Swal.fire("WARNING!", response.data.message, "error");
                    $state.go('branch_ifsc_codes.add');
					}
				},function(error) {
                  vm.loader = false;
                  handleErrorResponse(error);
                  $state.go('branch_ifsc_codes.add')
              })
			});
		}

        function modifyTorch() {
			vm.loader = true;
			 var requestData = {};
			  if(vm.bankCode =='') {vm.bankCode=null;}
              if(vm.branchCode =='') {vm.branchCode=null;}
                requestData.bankCode = vm.bankCode;
                requestData.branchCode = vm.branchCode;
 			branchIfscCodeService.modifyTorch(requestData).then(function(response) {
				vm.modifyLists = response.data.result;
				vm.loader = false;
				if(response.data.message == 'SUCCESS') {
				if(vm.bankCode == null){
                angular.element('#bankCodeModel').modal('toggle');
                    vm.modifyLists = response.data.result;
                    vm.loader = false;
                }
                else if(vm.branchCode == null){
                angular.element('#bankBranchModel').modal('toggle');
                    vm.modifyLists = response.data.result;
                    vm.loader = false;

                }
                else{
                vm.loader = false;
                vm.result = response.data.result[0];
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
               if(vm.branchCode =='') {vm.branchCode=null;}
                requestData.bankCode = vm.bankCode;
                requestData.branchCode = vm.branchCode;
            branchIfscCodeService.inquiryTorch(requestData).then(function(response) {
                vm.inquiryLists = response.data.result;
                vm.loader = false;
                if(response.data.message == 'SUCCESS' || response.data.message == 'success' ) {
                if(vm.bankCode == null){
                angular.element('#bankCodeModelInquiry').modal('toggle');
                    vm.inquiryLists = response.data.result;
                    vm.loader = false;
                }
                else if(vm.branchCode == null){
                angular.element('#bankBranchModelInquiry').modal('toggle');
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
        function verifyDeleteTorch() {
            vm.loader = true;
             var requestData = {};
              if(vm.bankCode =='') {vm.bankCode=null;}
              if(vm.branchCode =='') {vm.branchCode=null;}
                requestData.bankCode = vm.bankCode;
                requestData.branchCode = vm.branchCode;
            branchIfscCodeService.verifyDeleteTorch(requestData).then(function(response) {
                vm.verifyDeleteLists = response.data.result;
                vm.loader = false;
                if(response.data.message == 'SUCCESS' || response.data.message == 'success' ) {
                if(vm.bankCode == null){
                angular.element('#bankCodeModelVerifyDelete').modal('toggle');
                    vm.verifyDeleteLists = response.data.result;
                    vm.loader = false;
                }
                else if(vm.branchCode == null){
                angular.element('#branchCodeModelVerifyDelete').modal('toggle');
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




		function list() {
			vm.loader = true;
			branchIfscCodeService.getlist().then(function(response) {
				vm.recordsList = response.data;
				vm.loader = false;
			}, handleErrorResponse);
		}

		function listUnverifiedRecords() {
			vm.loader = true;
			branchIfscCodeService.getunverifiedrecords().then(function(response) {
				vm.recordsList = response.data;
				vm.loader = false;
			}, handleErrorResponse);
		}

		function listVerifiedRecords() {
			vm.loader = true;
			branchIfscCodeService.getverifiedrecords().then(function(response) {
				vm.recordsList = response.data;
				vm.loader = false;
			}, handleErrorResponse);
		}

		function getRecord(id) {
			vm.loader = true;
			vm.newValueDispay = false;
			vm.verified = false;
			vm.branchIfscCodeNewScope	= {};
			
			branchIfscCodeService.getrecord(id).then(function(response) {
				vm.branchIfscCodeResponse = response.data;
				vm.branchIfscCode =vm.branchIfscCodeResponse.dto;
				console.log(vm.branchIfscCode.bankCode);
				if(vm.branchIfscCodeResponse.auditLogDto){
					vm.newValueDispay = true;
					vm.verified = true;
					vm.lastModifiedBy = vm.branchIfscCodeResponse.auditLogDto.lastModifiedBy;
					vm.lastModifiedDate = vm.branchIfscCodeResponse.auditLogDto.lastModifiedDate;
					
					angular.forEach(vm.branchIfscCodeResponse.dto, function(value, key){
						vm.branchIfscCodeNewScope[key] = value;
					});
					
					angular.forEach(vm.branchIfscCodeResponse.auditLogDto.modifiedFieldsData, function(modifiedFieldsData){
						vm.branchIfscCodeNewScope[modifiedFieldsData.propertyName] = modifiedFieldsData.newValue;
					});
				}
				if(!response.data.auditLogDto && response.data.dto.verified == false){
					vm.verified = true;
				}
				vm.loader = false;
			}, handleErrorResponse);
		}
	

		function modify() {
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
				formData.pkColumnValue = vm.result.branchDetailsId;
				formData.columnValues = convertToColumnValuesArray(vm.branchIfscCode)
				branchIfscCodeService.modify(formData).then(function(response) {
				if(response.data.message == 'SUCCESS') {
                Swal.fire("Modified!", response.data.message, "success");
                $state.go('branch_ifsc_codes.modify');
                }
                else{
                vm.loader = false;
                Swal.fire("WARNING!", response.data.message, "error");
                $state.go('branch_ifsc_codes.modify');
                }
				}, handleErrorResponse);
			});
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
				formData.pkColumnValue = vm.verifyDeleteLists.branchDetailsId;
				branchIfscCodeService.verifySubmit(formData).then(function(response) {
				if(response.data.message == 'SUCCESS') {
                Swal.fire("Verified!", response.data.message, "success");
                $state.go('branch_ifsc_codes.verify');
                }
                else{
                vm.loader = false;
                Swal.fire("WARNING!", response.data.message, "error");
                $state.go('branch_ifsc_codes.verify');
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
                formData.pkColumnValue = vm.verifyDeleteLists.branchDetailsId;
                branchIfscCodeService.deleteSubmit(formData).then(function(response) {
                if(response.data.message == 'SUCCESS') {
                Swal.fire("Deleted!", response.data.message, "success");
                $state.go('branch_ifsc_codes.delete');
                }
                else{
                vm.loader = false;
                Swal.fire("WARNING!", response.data.message, "error");
                $state.go('branch_ifsc_codes.delete');
                }
                }, handleErrorResponse);
            });
        }


		function deleteBranchIfscCodes(id) {
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
				
						if(vm.branchIfscCodeResponse.auditLogDto){
							formData = vm.branchIfscCodeNewScope;
						} else {
							formData = vm.branchIfscCode;
						}
						branchIfscCodeService.deleteBranchIfscCodes(formData).then(
								function(response) {
									Swal.fire("Deleted!", response.data.message, "success");
			    					location.href = '#!/branch_ifsc_codes/action/delete';  
								
								}, handleErrorResponse);
				});
		}

		function verify(id) {
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
				
				if(vm.branchIfscCodeResponse.auditLogDto){
					formData = vm.branchIfscCodeNewScope;
				} else {
					formData = vm.branchIfscCode;
				}
				branchIfscCodeService.verify(formData).then(function(response) {
					Swal.fire("Verified!", response.data.message, "success");
					location.href = '#!/branch_ifsc_codes/action/verify';
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