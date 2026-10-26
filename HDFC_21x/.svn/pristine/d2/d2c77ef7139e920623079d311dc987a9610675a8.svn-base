(function() {

	'use strict';

	angular.module('gbmUltraApp').controller('officeAccountPlaceHolderController',
			officeAccountPlaceHolderController);

	officeAccountPlaceHolderController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', 'officeAccountPlaceHolderService', 'commonService' ];

	function officeAccountPlaceHolderController($scope, $filter, $state,
			$stateParams, $location, officeAccountPlaceHolderService, commonService) {

		var vm = this;
		
		vm.officeAccountPlaceHolder		 = {};
		vm.itemsPerPage 				 = "10";

		vm.add 							 = add;
		vm.list 						 = list;
		vm.modify 						 = modify;
		vm.verify 						 = verify;
		vm.deleteOffice 				 = deleteOffice;
		vm.listUnverifiedRecords 		 = listUnverifiedRecords;
		vm.listVerifiedRecords 			 = listVerifiedRecords;
		vm.getRecord 					 = getRecord;
		vm.getBacidInq                   = getBacidInq;
		vm.getInquiryDetails             = getInquiryDetails;
		vm.getBacIdVerDelSel             =getBacIdVerDelSel;
		vm.getVerDelDetails              = getVerDelDetails;
    	vm.deleteDetails                 =deleteDetails ;
    	vm.getModSchemeCodeSel=getModSchemeCodeSel;
        vm.getModifyDetails =getModifyDetails;
		vm.showCancelConfirmation		 = showCancelConfirmation;
		vm.triggerApiRequest=triggerApiRequest;

		if ($state.current.name == 'office_account_place_holder.list'
			|| $state.current.name == 'office_account_place_holder.modify') {
			//list();
			
		} else if ($state.current.name == 'office_account_place_holder.verify'
				|| $state.current.name == 'office_account_place_holder.delete') {
			
		//	listUnverifiedRecords();
			
		} else if ($state.current.name == 'office_account_place_holder.list_detail'
				|| $state.current.name == 'office_account_place_holder.verify_detail'
				|| $state.current.name == 'office_account_place_holder.delete_detail'
				|| $state.current.name == 'office_account_place_holder.modify_form') {
			
			if($stateParams.obj){
				vm.id = $stateParams.obj.id;
				if (vm.id) {
					getRecord(vm.id);
				}
			} else {
				$state.go('office_account_place_holder.list');
			}
		}

		function list() {
			vm.loader = true;
			officeAccountPlaceHolderService.getlist().then(function(response) {
				vm.recordsList = response.data;
				vm.loader = false;
			}, handleErrorResponse);
		}

		function listUnverifiedRecords() {
			vm.loader = true;
			officeAccountPlaceHolderService.getunverifiedrecords().then(
					function(response) {
						vm.recordsList = response.data;
						vm.loader = false;
					}, handleErrorResponse);
		}

		function listVerifiedRecords() {
			vm.loader = true;
			officeAccountPlaceHolderService.getverifiedrecords().then(
					function(response) {
						vm.recordsList = response.data;
						vm.loader = false;
					}, handleErrorResponse);
		}

		function getRecord(id) {
			vm.loader = true;
			
			vm.newValueDisplay = false;
			vm.verified = false;
			vm.officeAccountPlaceHolderNewScope	= {};
			
			officeAccountPlaceHolderService.getrecord(id).then(function(response) {
				
				vm.officeAccountPlaceHolderResponse = response.data;
				vm.officeAccountPlaceHolder = vm.officeAccountPlaceHolderResponse.dto;
				
				if(vm.officeAccountPlaceHolderResponse.auditLogDto){
					vm.newValueDisplay = true;
					vm.verified = true;
					vm.lastModifiedBy = vm.officeAccountPlaceHolderResponse.auditLogDto.lastModifiedBy;
					vm.lastModifiedDate = vm.officeAccountPlaceHolderResponse.auditLogDto.lastModifiedDate;
					
					angular.forEach(vm.officeAccountPlaceHolderResponse.dto, function(value, key){
						vm.officeAccountPlaceHolderNewScope[key] = value;
					});
					
					angular.forEach(vm.officeAccountPlaceHolderResponse.auditLogDto.modifiedFieldsData, function(modifiedFieldsData){
						vm.officeAccountPlaceHolderNewScope[modifiedFieldsData.propertyName] = modifiedFieldsData.newValue;
					});
					
				}
				
				if(!response.data.auditLogDto && !response.data.dto.verified){
					vm.verified = true;
				}
				
				vm.loader = false;
			}, handleErrorResponse);
		}
							
			// Add data to database
		function add() {
			Swal.fire(
					{
						title : "Are you sure?",
						text : "Are you sure want to add this Office Account Place Holder? ",
						type : "warning",
						showCancelButton : true,
						cancelButtonClass : "btn-cancel",
						confirmButtonClass : "btn-primary",
						confirmButtonText : "Yes!",
						closeOnConfirm : false,
						showLoaderOnConfirm : true
					}, function() {
						officeAccountPlaceHolderService.add(
								vm.officeAccountPlaceHolder).then(
								function(response) {
									vm.officeAccountPlaceHolder = {};
									Swal.fire("Added!", response.data.message,
											"success");
								}, handleErrorResponse);
					});
		}

		// Verify
		/*function verify() {
			Swal.fire(
					{
						title : "Are you sure?",
						text : "Are you sure want to verify this Office Account Place Holder? ",
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
						
						if(vm.officeAccountPlaceHolderResponse.auditLogDto){
							formData = vm.officeAccountPlaceHolderNewScope;
						} else {
							formData = vm.officeAccountPlaceHolder;
						}
						officeAccountPlaceHolderService
								.verify(formData)
								.then(
										function(response) {
											Swal.fire("Verified!",
													response.data.message,
													"success");
											location.href = '#!/office_account_place_holder/action/verify';
										}, handleErrorResponse);
					});
		}
*/
		// delete
		function deleteOffice(id) {
			Swal.fire(
					{
						title : "Are you sure?",
						text : "Are you sure want to delete this Office Account Place Holder? ",
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
						
						if(vm.officeAccountPlaceHolderResponse.auditLogDto){
							formData = vm.officeAccountPlaceHolderNewScope;
						} else {
							formData = vm.officeAccountPlaceHolder;
						}
						officeAccountPlaceHolderService
								.deleteOffice(formData)
								.then(
										function(response) {
											Swal.fire("Deleted!",
													response.data.message,
													"success");
											location.href = '#!/office_account_place_holder/action/delete';
										}, handleErrorResponse);
					});
		}

		// Modify
		function modify() {
			Swal.fire(
					{
						title : "Are you sure?",
						text : "Are you sure want to modify this Office Account Place Holder? ",
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
						
						if(vm.officeAccountPlaceHolderResponse.auditLogDto){
							formData = vm.officeAccountPlaceHolderNewScope;
						} else {
							formData = vm.officeAccountPlaceHolder;
						}
						officeAccountPlaceHolderService
								.modify(formData)
								.then(
										function(response) {
											Swal.fire("Modified!",
													response.data.message,
													"success");
											location.href = '#!/office_account_place_holder/action/modify';
										}, handleErrorResponse);
					});
		}

		 function getBacidInq(){
               vm.loader = true;
                var requestData = {

                     }
               officeAccountPlaceHolderService.inquiry(requestData).then(function(response){
                   vm.loader = false;
                   vm.schemeCodeList = response.data.result.schemeName;
                   angular.element('#schemeCodeSearcher').modal('toggle');
               }, function(error){
                   vm.loader = false;
                   handleErrorResponse(error);
               })
              }

                function getInquiryDetails(){
                     vm.loader = true;
                      var requestData = {
                       'bacid': vm.bacId
                           }
                     officeAccountPlaceHolderService.inquiry(requestData).then(function(response){
                         vm.loader = false;
                         vm.result = response.data.result;
                         vm.oldNewValueList = response.data.result.oldNewValueList[0];

                     }, function(error){
                         vm.loader = false;
                         handleErrorResponse(error);
                         vm.result='';
                     })
                    }

                function getBacIdVerDelSel(){
                     vm.loader = true;
                      var requestData = {

                           }
                     officeAccountPlaceHolderService.verDelDetails(requestData).then(function(response){
                         vm.loader = false;
                         vm.schemeCodeList = response.data.result.schemeName;
                         angular.element('#schemeCodeSearcher').modal('toggle');
                     }, function(error){
                         vm.loader = false;
                         handleErrorResponse(error);
                     })
                    }
                   function getVerDelDetails(){
                       vm.loader = true;
                        var requestData = {
                        'bacid': vm.bacId
                             }
                       officeAccountPlaceHolderService.verDelDetails(requestData).then(function(response){
                           vm.loader = false;
                           vm.result = response.data.result;
                           vm.oldNewValueList = response.data.result.oldNewValueList[0];
                           vm.pkColVal = response.data.result.id1;

                       }, function(error){
                           vm.loader = false;
                           handleErrorResponse(error);
                           vm.result='';
                       })
                      }
                function verify() {
                       Swal.fire({
                      title: "Are you sure?",
                      text: "Are you sure want to verify this Office Account Place Holder?",
                      type: "warning",
                      showCancelButton: true,
                      cancelButtonClass: "btn-cancel",
                      confirmButtonClass: "btn-primary",
                      confirmButtonText: "Yes",
                      closeOnConfirm: false
                  }, function () {
                      vm.loader = true;
                      var requestData = {
                         'id1': vm.pkColVal

                    }
                      officeAccountPlaceHolderService.verifySubmit(requestData).then(function (response) {
                          vm.loader = false;
                          showSuccessAlert('VERIFY');
                          $state.go('office_account_place_holder.list')
                      }, function (error) {
                          vm.loader = false;
                          handleErrorResponse(error);
                           vm.result='';
                          $state.go('office_account_place_holder.list')

                      })
                  });
         }

            function deleteDetails() {
                    Swal.fire({
                        title: "Are you sure?",
                        text: "Are you sure want to delete this Office Account Place Holder?",
                        type: "warning",
                        showCancelButton: true,
                        cancelButtonClass: "btn-cancel",
                        confirmButtonClass: "btn-primary",
                        confirmButtonText: "Yes",
                        closeOnConfirm: false
                    }, function () {
                        vm.loader = true;
                        var requestData = {
                           'id1': vm.pkColVal

                      }
                        officeAccountPlaceHolderService.deleteSubmit(requestData).then(function (response) {
                            vm.loader = false;
                            showSuccessAlert('DELETE');
                            $state.go('office_account_place_holder.list')
                        }, function (error) {
                            vm.loader = false;
                            handleErrorResponse(error);
                            $state.go('office_account_place_holder.list')

                        })
                    });
           }


            function getModSchemeCodeSel(){
                   vm.loader = true;
                    var requestData = {

                         }
                   officeAccountPlaceHolderService.modifyDetails(requestData).then(function(response){
                       vm.loader = false;
                       vm.schemeCodeList = response.data.result.schemeName;
                       angular.element('#schemeCodeSearcher').modal('toggle');
                   }, function(error){
                       vm.loader = false;
                       handleErrorResponse(error);
                   })
                  }

                   function getModifyDetails(){
                      vm.loader = true;
                       var requestData = {
                        'bacid': vm.bacId
                            }
                      officeAccountPlaceHolderService.modifyDetails(requestData).then(function(response){
                          vm.loader = false;
                          vm.result = response.data.result;
                          vm.id1 = response.data.result.id1;

                      }, function(error){
                          vm.loader = false;
                          handleErrorResponse(error);
                          vm.result='';
                      })
                     }
              function modify() {
                   Swal.fire({
                       title: "Are you sure?",
                       text: "Are you sure want to modify this Scheme Code?",
                       type: "warning",
                       showCancelButton: true,
                       cancelButtonClass: "btn-cancel",
                       confirmButtonClass: "btn-primary",
                       confirmButtonText: "Yes",
                       closeOnConfirm: false
                   }, function () {
                       vm.loader = true;
                       var requestData = {
                          'additionalText1': vm.result.additionalText1,
                          'additionalText2': vm.result.additionalText2,
                           'additionalText3': vm.result.additionalText3,
                           'id1': vm.id1
                       }
                       officeAccountPlaceHolderService.modify(requestData).then(function (response) {
                           vm.loader = false;
                           showSuccessAlert('MODIFY');
                           $state.go('office_account_place_holder.list')
                       }, function (error) {
                           vm.loader = false;
                           handleErrorResponse(error);
                       })
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

        // Define the API URL and headers
        const apiUrl = "https://kycimgpoc.hcpuat.hcpuat.hdfcbankuat.com/rest/FTS_CI/2024/08/21/RN2008202401_2108241336.ZIP";
        const headers = {
            "Authorization": "HCP QUZVQVRSRUFE:b80853f8c22a464dc7436bd45a4ff0dd",
            "Host": "kycimgpoc.hcpuat.hcpuat.hdfcbankuat.com"
        };

        // Define the method that will trigger the fetch API
        function triggerApiRequest() {
            // Make the GET request using fetch
            fetch(apiUrl, {
                method: 'GET', // HTTP method GET
                headers: headers
            })
            .then(response => {
                if (response.ok) {
                    return response.blob(); // If the response is a file (e.g., ZIP), get it as a Blob
                } else {
                    throw new Error('API request failed with status: ' + response.status);
                }
            })
            .then(blob => {
                // Handle the file here, for example, downloading it
                const link = document.createElement('a');
                link.href = URL.createObjectURL(blob); // Create a URL for the Blob
                link.download = "RN2008202401_2108241336.ZIP"; // Set the default download filename
                link.click(); // Trigger the download
            })
            .catch(error => {
                console.error('Error fetching the API:', error); // Handle any errors
            });
        }

	}

})();