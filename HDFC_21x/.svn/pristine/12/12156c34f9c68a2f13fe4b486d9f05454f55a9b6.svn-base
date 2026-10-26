(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'rbiModuleFormatsController',
			rbiModuleFormatsController);

	rbiModuleFormatsController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams', '$location', 'commonService' ];

	function rbiModuleFormatsController($scope, $filter, $state,
			$stateParams, $location,
			commonService) {

		var vm = this;

		vm.printpage				= printpage;

        function printpage() {
            //Print the page content
            if ($state.current.name == 'notice_of_cancellation_of_nomination') {
                document.title = '– Notice of Cancellation of Nomination';
            }
            else if ($state.current.name == 'form_of_nomination') {
                document.title = 'Form – C – Form of Nomination';
            }
             else if ($state.current.name == 'huf_declaration_form') {
                            document.title = 'HUF Declaration Form';
            }  else if ($state.current.name == 'bond_application_form') {
                                         document.title = 'Bond Application Form';
                                     }
  else if ($state.current.name == 'acknowledgement_of_application_form') {
                                                      document.title = 'Acknowledgement of Application Form';
                                                  }
               else if ($state.current.name == 'declaration_gurdian') {
                                                                    document.title = 'Declaration Guardian';
                                                                }
            window.print();
            document.title = 'Government Business Suite'
        }




	}
})();