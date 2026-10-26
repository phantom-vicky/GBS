(function() {

	'use strict';

	angular.module('gbmUltraApp').controller('instrumentDetailsController',
			instrumentDetailsController);

	instrumentDetailsController.$inject = [ '$scope', '$filter', '$state',
			'$stateParams', '$location', 'instrumentDetailsService',
			'commonService' ];

	function instrumentDetailsController($scope, $filter, $state, $stateParams,
			$location, instrumentDetailsService, commonService) {

		var vm = this;

		vm.instrumentDetails = {};

		vm.itemsPerPage = "10";
		vm.searchInquiryFn = searchInquiryFn;
		vm.generateList = generateList;
		vm.add = add;
		vm.list = list;
		vm.listUnverifiedRecords = listUnverifiedRecords;
		vm.listVerifiedRecords = listVerifiedRecords;
		vm.modifyForm = modifyForm;
		vm.modify = modify;
		vm.verifyForm = verifyForm;
		vm.verify = verify;
		vm.inquiryForm = inquiryForm;
		vm.inquiry = inquiry;
		vm.deleteForm = deleteForm;
		vm.deleteInstrument = deleteInstrument;
		vm.getRecord = getRecord;
		vm.getInstrumentTypes = getInstrumentTypes;
		vm.getInquiryDetails =getInquiryDetails;
		vm.getVerDelDetails=getVerDelDetails;
		vm.getModifyDetails =getModifyDetails;
		vm.deleteDetails=deleteDetails;
		vm.showCancelConfirmation	= showCancelConfirmation;

		if ($state.current.name == 'instrument_details.list'
			|| $state.current.name == 'instrument_details.modify') {
			//list();
		} else if ($state.current.name == 'instrument_details.delete'
				|| $state.current.name == 'instrument_details.verify') {
			listUnverifiedRecords();
		} else if ($state.current.name == 'instrument_details.list_detail'
				|| $state.current.name == 'instrument_details.delete_detail'
				|| $state.current.name == 'instrument_details.verify_detail'
				|| $state.current.name == 'instrument_details.modify_form') {
			vm.id = $stateParams.id;
			if (vm.id) {
				getRecord(vm.id);
			}
		} else if ($state.current.name == 'instrument_details.modify_form') {
			vm.id = $stateParams.id;
			if (vm.id) {
				/* getReceiptNumber(); */
			}
		}

		function searchInquiryFn(searchKeyword) {
			vm.searchKeyword = searchKeyword;
			vm.recordsList = $filter('filter')(vm.baseRecordList,
					vm.searchKeyword);
			vm.currentPage = 1;
			vm.totalItems = vm.recordsList.length;
			vm.initialize();
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

	/*	function generateData() {
			var data = {};
			angular.forEach(vm.instrumentDetails, function(value, item) {
				data[item] = value;
			});
			return data;
		}*/

		// Get Instrument Types
		function getInstrumentTypes() {
			vm.loader = true;
			instrumentDetailsService.getInstrumentTypes().then(
					function(response) {
						vm.typeOfInstrumentList = response.data;
						vm.loader = false;
					}, handleErrorResponse);
		}

		// Add data to database
		function add() {
			Swal.fire({
				title : "Are you sure?",
				text : "Are you sure want to add this Instrument Details? ",
				type : "warning",
				showCancelButton : true,
				cancelButtonClass : "btn-cancel",
				confirmButtonClass : "btn-primary",
				confirmButtonText : "Yes!",
				closeOnConfirm : false,
				showLoaderOnConfirm : true
			}, function() {
				instrumentDetailsService.addinstrumentDetails(
						vm.instrumentDetails).then(function(response) {
					vm.instrumentDetails = {};
					Swal.fire("Added!", response.data.message, "success");
				}, handleErrorResponse)
			});
		}


		function list() {
			vm.loader = true;
			instrumentDetailsService.getlist().then(function(response) {
				vm.recordsList = response.data;
				vm.loader = false;
			}, handleErrorResponse);
		}

		function listUnverifiedRecords() {
			vm.loader = true;
			instrumentDetailsService.getunverifiedrecords().then(
					function(response) {
						vm.recordsList = response.data;
						vm.loader = false;
					}, handleErrorResponse);
		}

		function listVerifiedRecords() {
			vm.loader = true;
			instrumentDetailsService.getverifiedrecords().then(
					function(response) {
						vm.recordsList = response.data;
						vm.loader = false;
					}, handleErrorResponse);
		}

		function getRecord(id) {
			vm.loader = true;
			vm.newValueDispay = false;
			vm.verified = false;
			vm.instrumentDetailsNewScope	= {};
			instrumentDetailsService.getrecord(id).then(function(response) {
				vm.instrumentDetailsResponse = response.data;
				vm.instrumentDetails = vm.instrumentDetailsResponse.dto;
				
				if(vm.instrumentDetailsResponse.auditLogDto){
					vm.newValueDispay = true;
					vm.verified = true;
					vm.lastModifiedBy = vm.instrumentDetailsResponse.auditLogDto.lastModifiedBy;
					vm.lastModifiedDate = vm.instrumentDetailsResponse.auditLogDto.lastModifiedDate;
					
					angular.forEach(vm.instrumentDetailsResponse.dto, function(value, key){
						vm.instrumentDetailsNewScope[key] = value;
					});
					
					angular.forEach(vm.instrumentDetailsResponse.auditLogDto.modifiedFieldsData, function(modifiedFieldsData){
						vm.instrumentDetailsNewScope[modifiedFieldsData.propertyName] = modifiedFieldsData.newValue;
					});
					
				}
				
				if(!response.data.auditLogDto && !response.data.dto.verified){
					vm.verified = true;
				}
				
				vm.loader = false;
			}, handleErrorResponse);
		}

		function modifyForm(id) {
			vm.initialize();
			vm.getRecord(id);
		}

		/*function modify() {
			Swal.fire(
					{
						title : "Are you sure?",
						text : "Are you sure want to modify this Instrument Details? ",
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
						
						if(vm.instrumentDetailsResponse.auditLogDto){
							formData = vm.instrumentDetailsNewScope;
						} else {
							formData = vm.instrumentDetails;
						}
						
						instrumentDetailsService
								.modify(formData)
								.then(
										function(response) {
											Swal.fire("Modified!",
													response.data.message,
													"success");
											location.href = '#!/instrument_details/action/modify';
										}, handleErrorResponse);
					});
		}*/

		function deleteForm(id) {
			vm.initialize();
			vm.getRecord(id);
		}

		function deleteInstrument(id) {
			Swal.fire(
					{
						title : "Are you sure?",
						text : "Are you sure want to delete this Instrument Details? ",
						type : "warning",
						showCancelButton : true,
						cancelButtonClass : "btn-cancel",
						confirmButtonClass : "btn-primary",
						confirmButtonText : "Yes!",
						closeOnConfirm : false,
						showLoaderOnConfirm : true
					},
					function() {
						instrumentDetailsService
								.deleteinstrument(vm.instrumentDetails)
								.then(
										function(response) {
											Swal.fire("Deleted!",
													response.data.message,
													"success");
											location.href = '#!/instrument_details/action/delete';
										}, handleErrorResponse);
					});
		}

		function inquiryForm(id) {
			vm.initialize();
			vm.getRecord(id);
		}

		function inquiry(id) {
			vm.initialize();
			vm.list();
		}

		function verifyForm(id) {
			vm.initialize();
			vm.getRecord(id);
		}

		/*function verify() {
			Swal.fire(
					{
						title : "Are you sure?",
						text : "Are you sure want to verify this Instrument Details? ",
						type : "warning",
						showCancelButton : true,
						cancelButtonClass : "btn-cancel",
						confirmButtonClass : "btn-primary",
						confirmButtonText : "Yes!",
						closeOnConfirm : false,
						showLoaderOnConfirm : true
					},
					function() {
						instrumentDetailsService
								.verify(vm.instrumentDetails)
								.then(
										function(response) {
											Swal.fire("Verified!",
													response.data.message,
													"success");
											location.href = '#!/instrument_details/action/verify';
										}, handleErrorResponse);
					});
		}*/

		        function getInquiryDetails(){
                     vm.loader = true;
                      var requestData = {
                       'drCrIndicator1': vm.instrumentDetails.drCrIndicator1,
                       'instrumentType': vm.instrumentDetails.instrumentType
                           }
                     instrumentDetailsService.inquiry(requestData).then(function(response){
                         vm.loader = false;
                         vm.result = response.data.result;
                         vm.oldNewValueList = response.data.result.oldNewValueList[0];

                     }, function(error){
                         vm.loader = false;
                         handleErrorResponse(error);
                         vm.result='';
                     })
                    }
                 function getVerDelDetails(){
                         vm.loader = true;
                          var requestData = {
                           'drCrIndicator1': vm.instrumentDetails.drCrIndicator1,
                           'instrumentType': vm.instrumentDetails.instrumentType
                               }
                         instrumentDetailsService.verDelDetails(requestData).then(function(response){
                             vm.loader = false;
                             vm.result = response.data.result;
                              vm.pkColVal = response.data.result.pkColVal;
                             vm.oldNewValueList = response.data.result.oldNewValueList[0];

                         }, function(error){
                             vm.loader = false;
                             handleErrorResponse(error);
                              vm.result='';
                         })
                        }
              function verify() {
                  Swal.fire({
                      title: "Are you sure?",
                      text: "Are you sure want to verify this Instrument Details?",
                      type: "warning",
                      showCancelButton: true,
                      cancelButtonClass: "btn-cancel",
                      confirmButtonClass: "btn-primary",
                      confirmButtonText: "Yes",
                      closeOnConfirm: false
                  }, function () {
                      vm.loader = true;
                      var requestData = {
                         'id': vm.pkColVal

                    }
                      instrumentDetailsService.verifySubmit(requestData).then(function (response) {
                          vm.loader = false;
                          showSuccessAlert('VERIFY');
                          $state.go('instrument_details.list')
                      }, function (error) {
                          vm.loader = false;
                          handleErrorResponse(error);
                           vm.result='';
                          $state.go('instrument_details.list')

                      })
                  });
         }

            function deleteDetails() {
               Swal.fire({
                   title: "Are you sure?",
                   text: "Are you sure want to delete this Instrument Details?",
                   type: "warning",
                   showCancelButton: true,
                   cancelButtonClass: "btn-cancel",
                   confirmButtonClass: "btn-primary",
                   confirmButtonText: "Yes",
                   closeOnConfirm: false
               }, function () {
                   vm.loader = true;
                   var requestData = {
                      'id': vm.pkColVal

                 }
                   instrumentDetailsService.deleteSubmit(requestData).then(function (response) {
                       vm.loader = false;
                       showSuccessAlert('DELETE');
                       $state.go('instrument_details.list')
                   }, function (error) {
                       vm.loader = false;
                       handleErrorResponse(error);
                       $state.go('instrument_details.list')

                   })
               });
      }

        function getModifyDetails(){
                 vm.loader = true;
                  var requestData = {
                   'drCrIndicator1': vm.instrumentDetails.drCrIndicator1,
                   'instrumentType': vm.instrumentDetails.instrumentType
                       }
                 instrumentDetailsService.modifyDetails(requestData).then(function(response){
                     vm.loader = false;
                     vm.result = response.data.result;
                     vm.id = response.data.result.id;
                      }, function(error){
                     vm.loader = false;
                     handleErrorResponse(error);
                 })
                }


             function modify() {
              Swal.fire({
                  title: "Are you sure?",
                  text: "Are you sure want to modify this Instrument Details?",
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
                      'id': vm.id
                  }
                  instrumentDetailsService.modify(requestData).then(function (response) {
                      vm.loader = false;
                      showSuccessAlert('MODIFY');
                      $state.go('instrument_details.list')
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