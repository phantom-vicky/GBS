(function() {

	'use strict';

	angular.module('gbmUltraApp').controller('cbsGeneralSetupController',
			cbsGeneralSetupController);
	
	  
	    
	cbsGeneralSetupController.$inject = [ '$scope', '$filter', '$state',
			'$stateParams', '$location', 'cbsGeneralSetupService', 'commonService' ];

	function cbsGeneralSetupController($scope, $filter, $state, $stateParams,
			$location, cbsGeneralSetupService, commonService) {

		var vm = this;
		
		vm.cbsGeneralSetup = {};
		vm.generalSetup = {};
		vm.generalSetupScope = {};
		vm.getGeneralSetupParameters = getGeneralSetupParameters;
		vm.modify = modify;
		vm.verify = verify;
		vm.showCancelConfirmation	= showCancelConfirmation;

		getGeneralSetupParameters();
		
		function generateData(){
			var data = [];
			angular.forEach(vm.generalSetup, function(value, item){
				var temp = {};
    			temp.dto = {};
    			temp.dto = value.dto;
    			if(vm.newValueDispay && (vm.generalSetupScope[value.dto.parameterName] != vm.generalSetupNewScope[value.dto.parameterName])){
    				temp.dto.parameterValue = vm.generalSetupNewScope[value.dto.parameterName];
    			} else {
    				temp.dto.parameterValue = vm.generalSetupScope[value.dto.parameterName];
    			}
    			temp.auditLogDto = null;
    			data.push(temp);
    			
	        });
			return data;
			

		};

		function modify() {
//			Swal.fire({
//				title : "Are you sure?",
//				text : "Are you sure want to modify this cbs general setup? ",
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
                                	text : "Are you sure want to modify this cbs general setup? ",
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
				var data = generateData();
				cbsGeneralSetupService.modify(data).then(function(response) {
					Swal.fire("Modified!", response.data.message, "success");
					location.href = '#!/cbs_general_setup/action/inquiry';
				}, handleErrorResponse);
		}	});
		}

		function verify() {
//			Swal.fire({
//				title : "Are you sure?",
//				text : "Are you sure want to verify this cbs general setup? ",
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
                              text : "Are you sure want to verify this cbs general setup? ",
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
				var data = generateData();
				cbsGeneralSetupService.verify(data).then(function(response) {
					Swal.fire("Verified!", response.data.message, "success");
					location.href = '#!/cbs_general_setup/action/inquiry';
				}, handleErrorResponse);
		}	});
		}

		function getGeneralSetupParameters() {
			vm.loader = true;
			vm.newValueDispay = false;
			vm.verified = false;
			vm.generalSetupNewScope	= {};
			cbsGeneralSetupService.getcbsGeneralSetup()
				.then(
					function(response) {
        				vm.generalSetup = response.data;
						vm.enableVerifyButton = true;
						
						angular.forEach(vm.generalSetup, function(value){
							if(value.auditLogDto){
								vm.newValueDispay = true;
								vm.verified = true;
								vm.lastModifiedBy = value.auditLogDto.lastModifiedBy;
								vm.lastModifiedDate = value.auditLogDto.lastModifiedDate;
								
								angular.forEach(value.auditLogDto.modifiedFieldsData, function(modifiedFieldsData){
									if(modifiedFieldsData.columnName == "PARAM_VALUE"){
										vm.generalSetupNewScope[value.dto.parameterName] = modifiedFieldsData.newValue;
									}	
								});
							}
							if(!vm.generalSetupNewScope[value.dto.parameterName]){
								vm.generalSetupNewScope[value.dto.parameterName] = value.dto.parameterValue;
							}
							
							if (value.dto.isVerified && !value.auditLogDto) {
					     		vm.enableVerifyButton = false;
							}
							
							vm.generalSetupScope[value.dto.parameterName] = value.dto.parameterValue;
							
						});
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
                    $state.go(state);

             }   });
        }
	}
})();