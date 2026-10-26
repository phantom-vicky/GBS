(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .controller('generalSchemeParameterMaintenanceController', generalSchemeParameterMaintenanceController);

   generalSchemeParameterMaintenanceController.$inject = ['$scope', '$stateParams', '$filter', 'generalSchemeParameterMaintenanceService', 'commonService'];

    function generalSchemeParameterMaintenanceController($scope, $filter, $stateParams, generalSchemeParameterMaintenanceService, commonService) {

	var vm = this;
    	
    	getPage();
    	
    	function getPage(){
    		initialize();
    		switch(vm.action){
    			
    			case 'modify':
    				vm.showModifyForm = false;
    				break;
    			case 'inquiry':
    				vm.showInquiryForm = false;
    				break;
    			
    		}
    	};
    	
    	function initialize(){
    		vm.generalSchemeParameterMaintenance={};
    		
    		vm.submitModifySearcherForm = submitModifySearcherForm;
    		vm.modifySubmitForm = modifySubmitForm;
    		vm.modifyCancelForm = modifyCancelForm;
    	
    		 
        
   		vm.action = $stateParams.operation;
   		
    		if(!vm.action){
    			vm.action = 'modify';
    		}
    		
    		// to initialize loader
    		vm.loader = false;
    		
    		
    		
    		$scope.leftMenu = {};
    		$scope.leftMenu.add = {display: false, href: '#!/'};
    		$scope.leftMenu.modify = {display: true, href: '#!/dc/geneal_scheme_parameter_maintenance/modify'};
    		$scope.leftMenu.delete = {display: false, href: '#!/'};
    		$scope.leftMenu.inquiry = {display: true, href: '#!/dc/geneal_scheme_parameter_maintenance/inquiry'};
    		$scope.leftMenu.verify = {display: false, href: '#!/'};	
    	
    		
    		
    	}
    	
    	 function submitModifySearcherForm(){
     		
 			vm.showModifyForm = true;
 			
 	}
    	/*Modify Submit*/
		function modifySubmitForm(){
//			Swal.fire({
//				  title: "Are you sure?",
//				  text: "Do you want to Really Modify this record?",
//				  type: "warning",
//				  showCancelButton: true,
//				  cancelButtonClass: "btn-cancel",
//				  confirmButtonClass: "btn-primary",
//				  confirmButtonText: "Yes, Modify it!",
//				  closeOnConfirm: false,
//				 /* showLoaderOnConfirm: true*/
//				},
//				function(){

Swal.fire({
  title: "Are you sure?",
  text: "Do you want to Really Modify this record?",
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
		    					
							
							
							Swal.fire("Modified!",  "success");
					}
			        });
				}
		
		

		function modifyCancelForm(){
//
//			 Swal.fire({
//				  title: "Are you sure?",
//				  text: "Do you want to Really Cancel this record?",
//				  type: "warning",
//				  showCancelButton: true,
//				  cancelButtonClass: "btn-cancel",
//				  confirmButtonClass: "btn-primary",
//				  confirmButtonText: "Yes, Cancel it!",
//				  cancelButtonText: "No",
//				  closeOnConfirm: true
//
//				},
//		    function(){

                  Swal.fire({
                    title: "Are you sure?",
                    text: "Do you want to Really Cancel this record?",
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
						vm.showModifyForm = false;
						initialize();
			
		}	});
				}

	
		
    }

})();











