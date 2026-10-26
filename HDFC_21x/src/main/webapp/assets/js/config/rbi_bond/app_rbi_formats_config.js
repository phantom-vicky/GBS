(function () {
	'use strict';

	angular
		.module('gbmUltraApp')
		.config(RbiFormatConfig);

	RbiFormatConfig.$inject = ['$stateProvider', '$ocLazyLoadProvider'];

	function RbiFormatConfig($stateProvider, $ocLazyLoadProvider) {
		$stateProvider




			.state('notice_of_cancellation_of_nomination', {
				url: '/notice_of_cancellation_of_nomination',
				templateUrl:'app/rbi_bond_module/gu_functionallity/formats/notice_of_cancellation_of_nomination.html',
				data: {
					authRequired: true,
					requiredPermission: 'GBM_RBI_GU_FUNCTIONALITY:GBM_RBI_BOND_NOTICE_OF_CANCELLATION:FORMATS'
				},

				controller: 'rbiModuleFormatsController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: [
								'app/rbi_bond_module/gu_functionallity/formats/rbi-module-formats.controller.js?v=' + versionNum
								]
						});
					}
				}


			})

.state('form_of_nomination', {
				url: '/form_of_nomination',
				templateUrl:'app/rbi_bond_module/gu_functionallity/formats/form_of_nomination.html',
				data: {
					authRequired: true,
					requiredPermission: 'GBM_RBI_GU_FUNCTIONALITY:GBM_RBI_BOND_FORM_OF_NOMINATION_FORM:FORMATS'
				},

				controller: 'rbiModuleFormatsController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: [
								'app/rbi_bond_module/gu_functionallity/formats/rbi-module-formats.controller.js?v=' + versionNum
								]
						});
					}
				}


			})

.state('huf_declaration_form', {
				url: '/huf_declaration_form',
				templateUrl:'app/rbi_bond_module/gu_functionallity/formats/huf_declaration_form.html',
				data: {
					authRequired: true,
					requiredPermission: 'GBM_RBI_GU_FUNCTIONALITY:GBM_RBI_BOND_HUF_DECLARATION:FORMATS'
				},

				controller: 'rbiModuleFormatsController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: [
								'app/rbi_bond_module/gu_functionallity/formats/rbi-module-formats.controller.js?v=' + versionNum
								]
						});
					}
				}


			})

			//bond_application_form

.state('bond_application_form', {
				url: '/bond_application_form',
				templateUrl:'app/rbi_bond_module/gu_functionallity/formats/bond_application_form.html',
				data: {
					authRequired: true,
					requiredPermission: 'GBM_RBI_GU_FUNCTIONALITY:GBM_RBI_BOND_APPLICATION_FORM:FORMATS'
				},

				controller: 'rbiModuleFormatsController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: [
								'app/rbi_bond_module/gu_functionallity/formats/rbi-module-formats.controller.js?v=' + versionNum
								]
						});
					}
				}


			})
//acknowledgement_of_application_form

.state('acknowledgement_of_application_form', {
				url: '/acknowledgement_of_application_form',
				templateUrl:'app/rbi_bond_module/gu_functionallity/formats/acknowledgement_of_application_form.html',
				data: {
					authRequired: true,
					requiredPermission: 'GBM_RBI_GU_FUNCTIONALITY:GBM_RBI_BOND_ACK_OF_APP_FORM:FORMATS'
				},

				controller: 'rbiModuleFormatsController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: [
								'app/rbi_bond_module/gu_functionallity/formats/rbi-module-formats.controller.js?v=' + versionNum
								]
						});
					}
				}


			})

//Declaration Guardian


.state('declaration_gurdian', {
				url: '/declaration_gurdian',
				templateUrl:'app/rbi_bond_module/gu_functionallity/formats/declaration_gurdian.html',
				data: {
					authRequired: true,
					requiredPermission: 'GBM_RBI_GU_FUNCTIONALITY:GBM_RBI_BOND_DECLARATION_GURDIAN:FORMATS'
				},

				controller: 'rbiModuleFormatsController',
				controllerAs: 'vm',
				resolve: {
					load: function ($ocLazyLoad) {
						return $ocLazyLoad.load({
							files: [
								'app/rbi_bond_module/gu_functionallity/formats/rbi-module-formats.controller.js?v=' + versionNum
								]
						});
					}
				}


			})

}
})();

