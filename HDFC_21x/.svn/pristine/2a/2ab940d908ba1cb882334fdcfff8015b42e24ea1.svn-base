(function() {

	'use strict';

	angular.module('gbmUltraApp').controller('schemeCodesController',
			schemeCodesController);

	schemeCodesController.$inject = [ '$scope', '$filter', '$state',
			'$stateParams', '$location', 'schemeCodesService', 'commonService' ];

	function schemeCodesController($scope, $filter, $state, $stateParams,
			$location, schemeCodesService, commonService) {

		var vm = this;

		vm.schemeCodes = {};
		vm.itemsPerPage = "10";
		vm.add = add;
		vm.list = list;
		vm.listUnverifiedRecords = listUnverifiedRecords;
		vm.listVerifiedRecords = listVerifiedRecords;
		vm.modify = modify;
		vm.verify = verify;
		vm.deleteSchemeCodes = deleteSchemeCodes;
		vm.getRecord = getRecord;
		vm.showCancelConfirmation	= showCancelConfirmation;
		vm.getSchemeCodeInq=getSchemeCodeInq;
		vm.getInquiryDetails=getInquiryDetails;
		vm.getSchemeCodeVerDelSel=getSchemeCodeVerDelSel;
		vm.getVerDelDetails =getVerDelDetails;
		vm.deleteDetails =deleteDetails;
		vm.getModSchemeCodeSel=getModSchemeCodeSel;
		vm.getModifyDetails =getModifyDetails;

		if ($state.current.name == 'scheme_codes.list'
			|| $state.current.name == 'scheme_codes.modify') {
			//list();
		} else if ($state.current.name == 'scheme_codes.delete'
				|| $state.current.name == 'scheme_codes.verify') {
			//listUnverifiedRecords();
		} else if ($state.current.name == 'scheme_codes.list_detail'
				|| $state.current.name == 'scheme_codes.delete_detail'
				|| $state.current.name == 'scheme_codes.verify_detail'
				|| $state.current.name == 'scheme_codes.modify_form') {
			if($stateParams.obj){
				vm.id = $stateParams.obj.id;
				if (vm.id) {
					getRecord(vm.id);
				}
			} else {
				location.href = '#!/scheme_codes/action/inquiry';
			}
		}
		// Add data to database
		function add() {
			Swal.fire({
				title : "Are you sure?",
				text : "Are you sure want to add this Scheme Code? ",
				type : "warning",
				showCancelButton : true,
				cancelButtonClass : "btn-cancel",
				confirmButtonClass : "btn-primary",
				confirmButtonText : "Yes!",
				closeOnConfirm : false,
				showLoaderOnConfirm : true
			}, function() {
				schemeCodesService.addschemeCodes(vm.schemeCodes).then(
						function(response) {
							Swal.fire("Added!", response.data.message, "success");
							vm.schemeCodes = {};
							location.href = '#!/scheme_codes/action/inquiry';
						}, handleErrorResponse);
			});
		}

		function list() {
			vm.loader = true;
			schemeCodesService.getlist().then(function(response) {
				vm.recordsList = response.data;
				vm.loader = false;
			}, handleErrorResponse);
		}

		function listUnverifiedRecords() {
			vm.loader = true;
			schemeCodesService.getunverifiedrecords().then(function(response) {
				vm.recordsList = response.data;
				vm.loader = false;
			}, handleErrorResponse);
		}

		function listVerifiedRecords() {
			vm.loader = true;
			schemeCodesService.getverifiedrecords().then(function(response) {
				vm.recordsList = response.data;
				vm.loader = false;
			}, handleErrorResponse);
		}

		function getRecord(id) {
			vm.loader = true;
			vm.newValueDisplay = false;
			vm.verified = false;
			vm.schemeCodesNewScope	= {};
			schemeCodesService.getrecord(id).then(function(response) {
				vm.schemeCodesResponse = response.data;
				vm.schemeCodes = vm.schemeCodesResponse.dto;
				
				if(vm.schemeCodesResponse.auditLogDto){
					vm.newValueDisplay = true;
					vm.verified = true;
					vm.lastModifiedBy = vm.schemeCodesResponse.auditLogDto.lastModifiedBy;
					vm.lastModifiedDate = vm.schemeCodesResponse.auditLogDto.lastModifiedDate;
					
					angular.forEach(vm.schemeCodesResponse.dto, function(value, key){
						vm.schemeCodesNewScope[key] = value;
					});
					
					angular.forEach(vm.schemeCodesResponse.auditLogDto.modifiedFieldsData, function(modifiedFieldsData){
						vm.schemeCodesNewScope[modifiedFieldsData.propertyName] = modifiedFieldsData.newValue;
					});
					
				}
				
				if(!response.data.auditLogDto && !response.data.dto.verified){
					vm.verified = true;
				}
				
				vm.loader = false;
			});
		}

		/*function modify() {
			Swal.fire(
					{
						title : "Are you sure?",
						text : "Are you sure want to modify this Scheme Code? ",
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
						
						if(vm.schemeCodesResponse.auditLogDto){
							formData = vm.schemeCodesNewScope;
						} else {
							formData = vm.schemeCodes;
						}
						
						schemeCodesService
								.modify(formData)
								.then(
										function(response) {
											Swal.fire("Modified!",
													response.data.message,
													"success");
											location.href = '#!/scheme_codes/action/modify';
										}, handleErrorResponse);
					});
		}
*/
		function deleteSchemeCodes(id) {
			Swal.fire({
				title : "Are you sure?",
				text : "Are you sure want to delete this Scheme Code? ",
				type : "warning",
				showCancelButton : true,
				cancelButtonClass : "btn-cancel",
				confirmButtonClass : "btn-primary",
				confirmButtonText : "Yes!",
				closeOnConfirm : false,
				showLoaderOnConfirm : true
			}, function() {
				schemeCodesService.deleteschemeCodes(vm.schemeCodes).then(
						function(response) {
							Swal.fire("Deleted!", response.data.message, "success");
							location.href = '#!/scheme_codes/action/delete';
						}, handleErrorResponse);
			});
		}

		/*function verify() {
			Swal.fire(
					{
						title : "Are you sure?",
						text : "Are you sure want to verify this Scheme Code? ",
						type : "warning",
						showCancelButton : true,
						cancelButtonClass : "btn-cancel",
						confirmButtonClass : "btn-primary",
						confirmButtonText : "Yes!",
						closeOnConfirm : false,
						showLoaderOnConfirm : true
					},
					function() {
						schemeCodesService
								.verify(vm.schemeCodes)
								.then(
										function(response) {
											Swal.fire("Verified!",
													response.data.message,
													"success");
											location.href = '#!/scheme_codes/action/verify';
										}, handleErrorResponse);
					});
		}*/

		 function getSchemeCodeInq(){
                   vm.loader = true;
                    var requestData = {

                         }
                   schemeCodesService.inquiry(requestData).then(function(response){
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
                         'schemeCode': vm.schemeCode
                             }
                       schemeCodesService.inquiry(requestData).then(function(response){
                           vm.loader = false;
                           vm.result = response.data.result;
                           vm.oldNewValueList = response.data.result.oldNewValueList[0];

                       }, function(error){
                           vm.loader = false;
                           handleErrorResponse(error);
                           vm.result='';
                       })
                      }

             function getSchemeCodeVerDelSel(){
                 vm.loader = true;
                  var requestData = {

                       }
                 schemeCodesService.verDelDetails(requestData).then(function(response){
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
                     'schemeCode': vm.schemeCode
                         }
                   schemeCodesService.verDelDetails(requestData).then(function(response){
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
                      text: "Are you sure want to verify this Scheme Code?",
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
                      schemeCodesService.verifySubmit(requestData).then(function (response) {
                          vm.loader = false;
                          showSuccessAlert('VERIFY');
                          $state.go('scheme_codes.list')
                      }, function (error) {
                          vm.loader = false;
                          handleErrorResponse(error);
                           vm.result='';
                          $state.go('scheme_codes.list')

                      })
                  });
         }

            function deleteDetails() {
                    Swal.fire({
                        title: "Are you sure?",
                        text: "Are you sure want to delete this Scheme Code?",
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
                        schemeCodesService.deleteSubmit(requestData).then(function (response) {
                            vm.loader = false;
                            showSuccessAlert('DELETE');
                            $state.go('scheme_codes.list')
                        }, function (error) {
                            vm.loader = false;
                            handleErrorResponse(error);
                            $state.go('scheme_codes.list')

                        })
                    });
           }
           function getModSchemeCodeSel(){
                vm.loader = true;
                 var requestData = {

                      }
                schemeCodesService.modifyDetails(requestData).then(function(response){
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
                     'schemeCode': vm.schemeCode
                         }
                   schemeCodesService.modifyDetails(requestData).then(function(response){
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
                       'addText1': vm.result.addText1,
                       'addText2': vm.result.addText2,
                        'addText3': vm.result.addText3,
                        'id1': vm.id1
                    }
                    schemeCodesService.modify(requestData).then(function (response) {
                        vm.loader = false;
                        showSuccessAlert('MODIFY');
                        $state.go('scheme_codes.list')
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

	}

})();