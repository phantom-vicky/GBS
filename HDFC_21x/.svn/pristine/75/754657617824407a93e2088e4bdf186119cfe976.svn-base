(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .controller('systemParameterController', systemParameterController);

    systemParameterController.$inject = ['$scope', '$filter', '$state', '$stateParams', '$location', 'systemParameterService', 'commonService'];

    function systemParameterController($scope, $filter, $state, $stateParams, $location, systemParameterService, commonService) {
    
    	var vm = this;
    	vm.systemParameters = {};
    	vm.systemParameterScope = {};
    	vm.systemParameterNewScope = {};
    	vm.newValueDispay = false;
		vm.loader = false;
    	vm.getModuleName                 =getModuleName;
    	vm.getSystemParameters			= getSystemParameters;
    	vm.getSystemParameterDetails	= getSystemParameterDetails;
    	vm.updateSysetmParameter		= updateSysetmParameter;
    	vm.verifySystemParameter		= verifySystemParameter;
    	vm.verifySystemParameter		= verifySystemParameter;
    	vm.getCommonList		= getCommonList;
    	vm.valid = valid;
    	
    	getSystemParameters();
    	getSystemParameterDetails();

    	function getSystemParameters(){
    		vm.loader = true;
    		vm.lastModifiedBy = '';
			vm.lastModifiedDate = '';
    		systemParameterService.getSystemParameters()
    		.then(
    			function(response){
					vm.systemParameters = response.data;
					angular.forEach(vm.systemParameters, function(value){
						if(value.auditLogDto){
							vm.newValueDispay = true;
							vm.lastModifiedBy = value.auditLogDto.lastModifiedBy;
							vm.lastModifiedDate = value.auditLogDto.lastModifiedDate;
							angular.forEach(value.auditLogDto.modifiedFieldsData, function(modifiedFieldsData){
								if(modifiedFieldsData.columnName == "PARAMETER_VALUE"){
									vm.systemParameterNewScope[value.dto.parameterId] = modifiedFieldsData.newValue;
								}	
							});
						}
						if(!vm.systemParameterNewScope[value.dto.parameterId]){
							vm.systemParameterNewScope[value.dto.parameterId] = value.dto.parameterValue;
						}
						vm.systemParameterScope[value.dto.parameterId] = value.dto.parameterValue;
					});
					vm.loader = false;
    			}, handleErrorResponse
    		);
    	}

    	function valid(amount) {
                        if (amount != "00" && amount != "0") {
                            vm.errorMsgTotalAmount = '';
                              } else {
                              vm.systemParameterScope['INS_VALPER'] = '';
                              vm.errorMsgTotalAmount = 'Invalid Input';
                        }
                    }
    	function getSystemParameterDetails(){
    		systemParameterService.getSystemParameterDetails()
    		.then(
    			function(response){
    				vm.systemParametersDetails = response.data;
    			}, handleErrorResponse
    		);
    	}

    	function getModuleName()
    	{
    			vm.loader = true;
            		vm.lastModifiedBy = '';
        			vm.lastModifiedDate = '';
        			console.log(vm.systemParameterScope);
            		systemParameterService.getSysParamModuleList(vm.systemParameterCommon)
            		.then(
            			function(response){
        					vm.systemParameters = response.data;
        					angular.forEach(vm.systemParameters, function(value){
        					if(value.auditLogDto){
                            vm.newValueDispay = true;
                            vm.lastModifiedBy = value.auditLogDto.lastModifiedBy;
                            vm.lastModifiedDate = value.auditLogDto.lastModifiedDate;
                            angular.forEach(value.auditLogDto.modifiedFieldsData, function(modifiedFieldsData){
                                if(modifiedFieldsData.columnName == "PARAMETER_VALUE"){
                                    vm.systemParameterNewScope[value.dto.parameterId] = modifiedFieldsData.newValue;
                                }
                            });
                        }
                       if(!vm.systemParameterNewScope[value.dto.parameterId]){
                       							vm.systemParameterNewScope[value.dto.parameterId] = value.dto.parameterValue;
                       						}
                       						vm.systemParameterScope[value.dto.parameterId] = value.dto.parameterValue;
                        });
                        vm.loader = false;
                        }, handleErrorResponse
                    );
    	}
    	function getCommonDetailsList()
    	{
                systemParameterService.getCommonDetailsList().then(
                function(response){
                vm.commonServiceDetails=response.data;
                },
                handleErrorResponse);
    	}

    	function getCommonList()
            	{
                        systemParameterService.getCommonDetailsList().then(
                        function(response){
                        vm.commonList=response.data;
                        },
                        handleErrorResponse);
            	}
    	
    	function updateSysetmParameter(){
//    		Swal.fire({
//				  title: "Are you sure?",
//				  text: "Are you sure want to modify System Parameter? ",
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
                    text: "Do you want to Really Modify System Parameter?",
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
					var formData = [];
		    		angular.forEach(vm.systemParameters, function(value){
		    			var temp = {};
		    			temp.dto = {};
		    			temp.dto.parameterId = value.dto.parameterId;
		    			if(vm.newValueDispay && (vm.systemParameterScope[value.dto.parameterId] != vm.systemParameterNewScope[value.dto.parameterId])){
		    				temp.dto.parameterValue = vm.systemParameterNewScope[value.dto.parameterId];
		    			} else {
		    				temp.dto.parameterValue = vm.systemParameterScope[value.dto.parameterId];
		    			}
		    			temp.dto.parameterType = value.dto.parameterType;
		    			temp.dto.parameterDesc = value.dto.parameterDesc;
		    			temp.dto.schCode=value.dto.schCode;
		    			temp.auditLogDto = null;
		    			formData.push(temp);
		    		});
		    		systemParameterService.addsystemParameter(formData)
		            .then(
		                    function (response) {
		                    	Swal.fire("Modified!", response.data.message, "success");
		                    	$location.path('/system_parameter/action/inquiry');
		                    }, handleErrorResponse
		            );
			}	});
    	}
    	
    	function verifySystemParameter(){
//    		Swal.fire({
//				  title: "Are you sure?",
//				  text: "Are you sure want to verify System Parameter? ",
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
                    text: "Do you want to Really Verify System Parameter",
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
					var formData = [];
		    		angular.forEach(vm.systemParameters, function(value){
		    			var temp = {};
		    			temp.dto = {};
		    			temp.dto.parameterId = value.dto.parameterId;
		    			if(vm.newValueDispay && (vm.systemParameterScope[value.dto.parameterId] != vm.systemParameterNewScope[value.dto.parameterId])){
		    				temp.dto.parameterValue = vm.systemParameterNewScope[value.dto.parameterId];
		    			} else {
		    				temp.dto.parameterValue = vm.systemParameterScope[value.dto.parameterId];
		    			}
		    			temp.dto.parameterType = value.dto.parameterType;
		    			temp.dto.parameterDesc = value.dto.parameterDesc;
		    			temp.dto.schCode=value.dto.schCode;
		    			temp.auditLogDto = null;
		    			formData.push(temp);
		    		});

					systemParameterService.verifysystemParameter(formData)
			        .then(
			                function (response) {
		                		Swal.fire("Verified!", response.data.message, "success");
		                    	$location.path('/system_parameter/action/inquiry');
			                }, handleErrorResponse
			        );
			}	});
    	}

    	function handleErrorResponse(response) {
        	vm.loader = false;
            var msg = response.data.message ? response.data.message : "Server Error";
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
                                                 title: "Confirm Cancel",
                                                  text: "Do you really want to cancel?",
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