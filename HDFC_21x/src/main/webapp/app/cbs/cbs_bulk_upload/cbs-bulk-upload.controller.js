(function() {

	'use strict';

	angular.module('gbmUltraApp').controller('cbsBulkUploadController',
			cbsBulkUploadController);

	cbsBulkUploadController.$inject = [ '$scope', '$filter', '$state',
			'$stateParams', '$location', 'cbsBulkUploadService',
			'commonService' ];

	function cbsBulkUploadController($scope, $filter, $state, $stateParams,
			$location, cbsBulkUploadService, commonService) {

		var vm = this;
		
		vm.itemsPerPage = '10';
		vm.currentPage = 0;
		vm.items = [];
		vm.cbsVerify = {};
		
		vm.getUnverifiedFiles	= getUnverifiedFiles;
		vm.cbsDelete			= cbsDelete;
		vm.rejectReason =  rejectReason;
		vm.cbsVerify			= cbsVerify;
		vm.uploadFile			= uploadFile;
		vm.list					= list;
            vm.getRemarksList = getRemarksList;

		vm.showCancelConfirmation	= showCancelConfirmation;
		vm.downloadFile			= downloadFile;
		vm.bankBranchStatusMetaData = BANK_BRANCH_STATUS_METADATA;
		if ($state.current.name == 'cbs_bulk_upload.list_detail' ||
				$state.current.name == 'cbs_bulk_upload.delete_detail' ||
				$state.current.name == 'cbs_bulk_upload.verify_detail') {
			vm.functionality = $stateParams.functionality;
			vm.file = $stateParams.file;
			if(vm.functionality && vm.file){
				list();
			}
		}



        		function downloadFile(){
                    vm.loader = true;
                    cbsBulkUploadService.downloadCBSDataByFileName(vm.cbsVerify.functionality ,vm.cbsVerify.unverifiedFile)
                    				.then(function(response){
                    				   vm.loader = false;
                    				   let selectedFunctionality = vm.cbsVerify.functionality;

                                       let functionalityLabel = '';
                                       if (selectedFunctionality === 'bankbranch') {
                                           functionalityLabel = 'Branches of the Bank';
                                       } else if (selectedFunctionality === 'officeaccountplaceholder') {
                                           functionalityLabel = 'Office account Placeholder';
                                       } else if (selectedFunctionality === 'instruments') {
                                           functionalityLabel = 'Instrument Details';
                                       } else if (selectedFunctionality === 'transactioncodes') {
                                           functionalityLabel = 'List of Transaction Codes';
                                       } else if (selectedFunctionality === 'otherbankdetails') {
                                           functionalityLabel = 'Other Bank Details';
                                       } else if (selectedFunctionality === 'otherbankbranches') {
                                           functionalityLabel = 'Branches of Other Bank';
                                       } else if (selectedFunctionality === 'calendarentries') {
                                           functionalityLabel = 'Maintenance of Calendar Entries';
                                       } else if (selectedFunctionality === 'schemecodes') {
                                           functionalityLabel = 'Scheme Codes';
                                       } else if (selectedFunctionality === 'userdetails') {
                                           functionalityLabel = 'User Work Class Information';
                                       } else if (selectedFunctionality === 'branchifscdetails') {
                                           functionalityLabel = 'Branch IFSC Codes';
                                       }
                    				   vm.verifiedFileData = response.data.result.fileData;
                                       vm.verifiedFileName = response.data.result.fileName;
                                       saveAs(getBlobFromBase64String(vm.verifiedFileData), vm.verifiedFileName)
//                                       Swal.fire("Downloaded", response.data.message, "success");
                                        Swal.fire("Downloaded", functionalityLabel + " downloaded successfully.", "success");

                    						}, function(error) {
                                             vm.loader = false;
                                             handleErrorResponse(error);
                                         })
        		}
		
		function uploadFile(){
			var $el = angular.element("input[type='file']");
//			Swal.fire({
//				  title: "Are you sure?",
//				  text: "Are you sure want to upload? ",
//				  type: "warning",
//				  showCancelButton: true,
//				  cancelButtonClass: "btn-cancel",
//				  confirmButtonClass: "btn-primary",
//				  confirmButtonText: "Yes!",
//				  closeOnConfirm: false,
//				  showLoaderOnConfirm: true
//				},
//				function(){







Swal.fire({
                    title: "Are you sure?",
                    text: "Do you Sure Want To Upload?",
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
					cbsBulkUploadService.addcbsBulkUpload(vm.file, vm.cbsBulkUpload.functionality)
			        .then(
			                function (response) {
			                	vm.loader = false;
		                		var json = JSON.parse(response.data);
		            		    Swal.fire("Uploaded!", "File Uploaded Successfully", "success");
		            		    vm.file = "";
		                		vm.cbsBulkUpload.functionality = "";
			                }, function (response){
			                    vm.loader = false;
		            			var msg = '';
		                    	var json = JSON.parse(response.data);
		            			Swal.fire("Error!", json.message, "error");
			                }
			        );
			}	});
			
		}
		
		// get files
		function getUnverifiedFiles(){
			vm.loader = true;
			cbsBulkUploadService.getCBSExcelFileNames(vm.cbsVerify.functionality)
				.then(function(response){
							vm.loader = false;
							vm.unverifiedFileList = response.data;
							vm.cbsVerify.unverifiedFile = vm.unverifiedFileList[0];
							if(vm.unverifiedFileList.length == 0){
							var msg = "No Unverified File List Found!!!"
							 Swal.fire("Warning!", msg, "error");
                             $state.go('cbs_bulk_upload.list');
							}
						}, function(error) {
                         vm.loader = false;
                         handleErrorResponse(error);
                         $state.go('cbs_bulk_upload.list');
                     })

		}
		
		// get list of record for selected file
		function list(){
			vm.loader = true;
			cbsBulkUploadService.getCBSDataByFileName(vm.functionality, vm.file)
			.then(
					function(response){
							vm.items = response.data;
							vm.currentPage = 0;
							vm.config = {
							    itemsPerPage: '10',
							    fillLastPage: true
							}
							vm.loader = false;
					}, handleErrorResponse
			);
		}
		
		// Delete file
//		function cbsDelete(){
//
//
//		                        Swal.fire({
//                                    title: "Rejection Remarks",
//                                    text: "",
//                                    type: "input",
//                                    inputPlaceholder: "Enter your remarks here",
//                                    showCancelButton: true,
//                                    cancelButtonClass: "btn-cancel",
//                                    confirmButtonClass: "btn-primary",
//                                    confirmButtonText: "Submit",
//                                    animation: "slide-from-top",
//                                    closeOnConfirm: false,
//                                    showLoaderOnConfirm: true,
//                                    inputValidator: function(value) {
//                                                if (/^[a-zA-Z\s]*$/.test(value.trim())) {
//                                                    document.removeEventListener('keypress', handleKeyPress);
//                                                    return true;
//                                                } else {
//                                                    return "Please enter only alphabetic characters!";
//                                                }
//                                            }
//
//                                },
//                                function(inputValue) {
//                                    if (inputValue === false) return false;
//
//                                    if (inputValue === "") {
//                                        Swal.fire.showInputError("Please enter the remarks!");
//                                        return false;
//                                    }
//                                    vm.loader = true;
//
//

function rejectReason() {
             angular.element('#permissionMappingDelete').modal('toggle');
            }
function cbsDelete(remarks){
//                            Swal.fire({
//                                     title: "Are you sure?",
//                                     text: "Do you want to Really Reject this record?",
//                                     type: "warning",
//                                     showCancelButton: true,
//                                     cancelButtonClass: "btn-cancel",
//                                     confirmButtonClass: "btn-primary",
//                                     confirmButtonText: "Yes",
//                                     closeOnConfirm: false,
//                                   },
//                              function () {


                      Swal.fire({
                                          title: "Are you sure?",
                                          text: "Do you want to Really Reject this record?",
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

                                  cbsBulkUploadService.deleteCBSDataByFileName(vm.functionality, vm.file, remarks)
                                     .then(
                                     							function(response){
                                     				                Swal.fire("Rejected!", "File Rejected Successfully", "success");
                                     				                location.href = '#!/cbs_bulk_upload/action/verify';
                                     							}, handleErrorResponse
                                     					);
                              } });
                    }

		// Verify file
		function cbsVerify(){
//			Swal.fire({
//				  title: "Are you sure?",
//				  text: "Are you sure want to verify? ",
//				  type: "warning",
//				  showCancelButton: true,
//				  cancelButtonClass: "btn-cancel",
//				  confirmButtonClass: "btn-primary",
//				  confirmButtonText: "Yes!",
//				  closeOnConfirm: false,
//				  showLoaderOnConfirm: true
//				},
//				function(){

 Swal.fire({
                     title: "Are you sure?",
                     text: "Are You Sure Want To verify?",
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
					cbsBulkUploadService.verifyCBSDataByFileName(vm.functionality, vm.file)
					.then(
							function(response){
				                Swal.fire("Verified!","File Verified Successfully", "success");
				                location.href = '#!/cbs_bulk_upload/action/verify';  
							}, handleErrorResponse
					);
										
			}	});
		}
		// Handle error response
		function handleErrorResponse(response) {
			vm.loader = false;
			var msg = response.data.message ? response.data.message
					: "Server Error";
			Swal.fire("Error!", msg, "error");
		}
		
		// Pagination features
		vm.itemsPerPage = '10';
		vm.currentPage = 0;

		vm.prevPage			= prevPage;
		vm.prevPageDisabled	= prevPageDisabled;
		vm.pageCount		= pageCount;
		vm.nextPage			= nextPage;
		vm.nextPageDisabled	= nextPageDisabled;
		vm.buttonsDisabled	= buttonsDisabled;
		
		function prevPage() {
			if (vm.currentPage > 0) {
				vm.currentPage--;
			}
		}

		function prevPageDisabled() {
			return vm.currentPage === 0 ? "page-disabled" : "";
		}

		function pageCount() {
			return Math.ceil(vm.items.length / vm.itemsPerPage) - 1;
		}

		function nextPage() {
			if (vm.currentPage < vm.pageCount()) {
				vm.currentPage++;
			}
		}

		function nextPageDisabled() {
			return vm.currentPage === vm.pageCount() ? "page-disabled" : "";
		}

		function buttonsDisabled() {
			return vm.currentPage === vm.pageCount() ? "" : "disabled";
		}
		// Pagination features - Ends
		

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
                                  title: "Are you sure?",
                                  text: "Do you Really Want TO Cancel?",
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

              }  });
        }

        function getRemarksList() {
                                            vm.referenceId = '058';
                                            var requestData = { 'referenceId': vm.referenceId };

                                            cbsBulkUploadService.getRemarksList(requestData).then(function(response) {
                                                vm.remarksList = response.data.result || []; // Ensure it's an array
                                            }, function(error) {
                                                handleErrorResponse(error);
                                            });
                                      }
	}
	
})();