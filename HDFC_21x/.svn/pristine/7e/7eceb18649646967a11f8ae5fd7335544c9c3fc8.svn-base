(function() {
    'use strict';

    angular
        .module('gbmUltraApp')
        .config(CBSConfig);

    CBSConfig.$inject = ['$stateProvider', '$ocLazyLoadProvider'];

    function CBSConfig($stateProvider, $ocLazyLoadProvider) {
        $stateProvider


         /* cbs master Menu */
        .state('cbs_master_updation', {
            url: '/cbs_master_updation',
            templateUrl: 'app/cbs/master_updation/cbs_master_updation.html'
        })

        /* CBS Integration General Setup */
        .state('cbs_general_setup', {
            abstract: true,
            url: '/cbs_general_setup',
            params: {
                views: ['modify', 'verify', 'inquiry'],
                permissions: ['GBM_CBS_GENERAL_SETUP:CBS_INTEGRATION_GENERAL_SETUP:MODIFY', 'GBM_CBS_GENERAL_SETUP:CBS_INTEGRATION_GENERAL_SETUP:VERIFY', 'GBM_CBS_GENERAL_SETUP:CBS_INTEGRATION_GENERAL_SETUP:ENQUIRY']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/cbs/common/left-menu.directive.js',
                            'app/cbs/cbs_general_setup/cbs-general-setup.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })

           .state('cbs_general_setup.modify', {
            url: '/action/modify',
            templateUrl: 'app/cbs/cbs_general_setup/modify_cbs_general_setup.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_GENERAL_SETUP:CBS_INTEGRATION_GENERAL_SETUP:MODIFY'
            },
            controller: 'cbsGeneralSetupController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/cbs_general_setup/cbs-general-setup.controller.js?v='+versionNum]
                    });
                }
            }
        })


        .state('cbs_general_setup.verify', {
            url: '/action/verify',
            templateUrl: 'app/cbs/cbs_general_setup/verify_cbs_general_setup.html',
              data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_GENERAL_SETUP:CBS_INTEGRATION_GENERAL_SETUP:VERIFY'
            },
            controller: 'cbsGeneralSetupController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/cbs_general_setup/cbs-general-setup.controller.js?v='+versionNum
                        ]
                    });
                }
            }
        })

         .state('cbs_general_setup.list', {
            url: '/action/inquiry',
            templateUrl: 'app/cbs/cbs_general_setup/cbs_general_setup.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_GENERAL_SETUP:CBS_INTEGRATION_GENERAL_SETUP:ENQUIRY'
            },
            controller: 'cbsGeneralSetupController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/cbs_general_setup/cbs-general-setup.controller.js?v='+versionNum]
                    });
                }
            }
        })

        /* Population of Sol date */
        .state('population_of_sol_date', {
            abstract: true,
            url: '/population_of_sol_date',
            params: {
                views: ['modify', 'inquiry'],
                permissions: ['GBM_CBS_MASTER_UPDATION:POPULATION_OF_SOL_DATE:MODIFY', 'GBM_CBS_MASTER_UPDATION:POPULATION_OF_SOL_DATE:ENQUIRY']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/cbs/common/left-menu.directive.js',
                            'app/cbs/master_updation/population_of_sol_date/population-of-sol-date.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })

           .state('population_of_sol_date.modify', {
            url: '/action/modify',
            templateUrl: 'app/cbs/master_updation/population_of_sol_date/modify_population_of_sol_date.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:POPULATION_OF_SOL_DATE:MODIFY'
            },
            controller: 'populationOfSolDateController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/population_of_sol_date/population-of-sol-date.controller.js?v='+versionNum]
                    });
                }
            }
        })



         .state('population_of_sol_date.list', {
            url: '/action/inquiry',
            templateUrl: 'app/cbs/master_updation/population_of_sol_date/population_of_sol_date.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:POPULATION_OF_SOL_DATE:ENQUIRY'
            },
            controller: 'populationOfSolDateController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/population_of_sol_date/population-of-sol-date.controller.js?v='+versionNum]
                    });
                }
            }
        })

         /* instrument details */
        .state('instrument_details', {
            abstract: true,
            url: '/instrument_details',
            params: {
                views: ['add', 'modify', 'verify', 'delete', 'inquiry'],
                permissions: ['GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:ADD', 'GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:MODIFY', 'GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:VERIFY', 'GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:DELETE', 'GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:ENQUIRY']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/cbs/common/left-menu.directive.js',
                            'app/cbs/master_updation/instrument_details/instrument-details.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('instrument_details.add', {
            url: '/add',
            templateUrl: 'app/cbs/master_updation/instrument_details/add_instrument_details.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:ADD'
            },
            controller: 'instrumentDetailsController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/instrument_details/instrument-details.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('instrument_details.modify', {
            url: '/action/modify',
            templateUrl: 'app/cbs/master_updation/instrument_details/modify_instrument_details_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:MODIFY'
            },
            controller: 'instrumentDetailsController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/instrument_details/instrument-details.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('instrument_details.modify_form', {
            url: '/action/modify/:id',
            templateUrl: 'app/cbs/master_updation/instrument_details/modify_instrument_details_form.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:MODIFY'
            },
            controller: 'instrumentDetailsController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/instrument_details/instrument-details.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('instrument_details.verify', {
            url: '/action/verify',
            templateUrl: 'app/cbs/master_updation/instrument_details/verify_instrument_details_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:VERIFY'
            },
            controller: 'instrumentDetailsController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/instrument_details/instrument-details.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('instrument_details.verify_detail', {
            url: '/action/verify/:id',
            templateUrl: 'app/cbs/master_updation/instrument_details/verify_instrument_details.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:VERIFY'
            },
            controller: 'instrumentDetailsController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/instrument_details/instrument-details.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('instrument_details.delete', {
            url: '/action/delete',
            templateUrl: 'app/cbs/master_updation/instrument_details/delete_instrument_details_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:DELETE'
            },
            controller: 'instrumentDetailsController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/instrument_details/instrument-details.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('instrument_details.delete_detail', {
            url: '/action/delete/:id',
            templateUrl: 'app/cbs/master_updation/instrument_details/delete_instrument_details.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:DELETE'
            },
            controller: 'instrumentDetailsController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/instrument_details/instrument-details.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('instrument_details.list', {
            url: '/action/inquiry',
            templateUrl: 'app/cbs/master_updation/instrument_details/instrument_details_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:ENQUIRY'
            },
            controller: 'instrumentDetailsController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/instrument_details/instrument-details.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('instrument_details.list_detail', {
            url: '/action/inquiry/:id',
            templateUrl: 'app/cbs/master_updation/instrument_details/instrument_details.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:ENQUIRY'
            },
            controller: 'instrumentDetailsController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/instrument_details/instrument-details.controller.js?v='+versionNum]
                    });
                }
            }
        })


        /* Scheme Codes */
        .state('scheme_codes', {
            abstract: true,
            url: '/scheme_codes',
            params: {
                views: ['add', 'modify', 'verify', 'delete', 'inquiry'],
                permissions: ['GBM_CBS_MASTER_UPDATION:SCHEME_CODES:ADD', 'GBM_CBS_MASTER_UPDATION:SCHEME_CODES:MODIFY', 'GBM_CBS_MASTER_UPDATION:SCHEME_CODES:VERIFY', 'GBM_CBS_MASTER_UPDATION:SCHEME_CODES:DELETE', 'GBM_CBS_MASTER_UPDATION:SCHEME_CODES:ENQUIRY']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/cbs/common/left-menu.directive.js',
                            'app/cbs/master_updation/scheme_codes/scheme-codes.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('scheme_codes.add', {
            url: '/add',
            templateUrl: 'app/cbs/master_updation/scheme_codes/add_scheme_codes.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:SCHEME_CODES:ADD'
            },
            controller: 'schemeCodesController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/scheme_codes/scheme-codes.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('scheme_codes.modify', {
            url: '/action/modify',
            templateUrl: 'app/cbs/master_updation/scheme_codes/modify_scheme_codes_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:SCHEME_CODES:MODIFY'
            },
            controller: 'schemeCodesController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/scheme_codes/scheme-codes.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('scheme_codes.modify_form', {
            url: '/action/modify/form',
            templateUrl: 'app/cbs/master_updation/scheme_codes/modify_scheme_codes_form.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:SCHEME_CODES:MODIFY'
            },
            params: {obj: null},
            controller: 'schemeCodesController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/scheme_codes/scheme-codes.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('scheme_codes.verify', {
            url: '/action/verify',
            templateUrl: 'app/cbs/master_updation/scheme_codes/verify_scheme_codes_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:SCHEME_CODES:VERIFY'
            },
            controller: 'schemeCodesController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/scheme_codes/scheme-codes.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('scheme_codes.verify_detail', {
            url: '/action/verify/detail',
            templateUrl: 'app/cbs/master_updation/scheme_codes/verify_scheme_codes.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:SCHEME_CODES:VERIFY'
            },
            params: {obj: null},
            controller: 'schemeCodesController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/scheme_codes/scheme-codes.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('scheme_codes.delete', {
            url: '/action/delete',
            templateUrl: 'app/cbs/master_updation/scheme_codes/delete_scheme_codes_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:SCHEME_CODES:DELETE'
            },
            controller: 'schemeCodesController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/scheme_codes/scheme-codes.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('scheme_codes.delete_detail', {
            url: '/action/delete/detail',
            templateUrl: 'app/cbs/master_updation/scheme_codes/delete_scheme_codes.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:SCHEME_CODES:DELETE'
            },
            params: {obj: null},
            controller: 'schemeCodesController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/scheme_codes/scheme-codes.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('scheme_codes.list', {
            url: '/action/inquiry',
            templateUrl: 'app/cbs/master_updation/scheme_codes/scheme_codes_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:SCHEME_CODES:ENQUIRY'
            },
            controller: 'schemeCodesController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/scheme_codes/scheme-codes.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('scheme_codes.list_detail', {
            url: '/action/inquiry/detail',
            templateUrl: 'app/cbs/master_updation/scheme_codes/scheme_codes.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:SCHEME_CODES:ENQUIRY'
            },
            params: {obj: null},
            controller: 'schemeCodesController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/scheme_codes/scheme-codes.controller.js?v='+versionNum]
                    });
                }
            }
        })


         /* CBS Bulk Upload */
        .state('cbs_bulk_upload', {
            abstract: true,
            url: '/cbs_bulk_upload',
            params: {
                views: ['upload', 'verify', 'delete', 'inquiry', 'download'],
                permissions: ['CBS:CBS_BULK_UPLOAD:UPLOAD', 'CBS:CBS_BULK_UPLOAD:VERIFY', 'CBS:CBS_BULK_UPLOAD:DELETE', 'CBS:CBS_BULK_UPLOAD:ENQUIRY', 'CBS:CBS_BULK_UPLOAD:UPLOAD']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/cbs/common/left-menu.directive.js',
                            'app/cbs/cbs_bulk_upload/cbs-bulk-upload.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('cbs_bulk_upload.upload', {
            url: '/action/upload_feature',
            templateUrl: 'app/cbs/cbs_bulk_upload/upload_cbs_bulk_upload.html',
            data: {
                authRequired: true,
                requiredPermission: 'CBS:CBS_BULK_UPLOAD:UPLOAD'
            },
            controller: 'cbsBulkUploadController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/cbs_bulk_upload/cbs-bulk-upload.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_bulk_upload.list', {
            url: '/action/inquiry',
            templateUrl: 'app/cbs/cbs_bulk_upload/inquiry_cbs_bulk_upload.html',
            data: {
                authRequired: true,
                requiredPermission: 'CBS:CBS_BULK_UPLOAD:ENQUIRY'
            },
            controller: 'cbsBulkUploadController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/cbs_bulk_upload/cbs-bulk-upload.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_bulk_upload.list_detail', {
            url: '/action/inquiry/:functionality/:file',
            templateUrl: 'app/cbs/cbs_bulk_upload/inquiry_cbs_bulk_upload_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'CBS:CBS_BULK_UPLOAD:ENQUIRY'
            },
            controller: 'cbsBulkUploadController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/cbs_bulk_upload/cbs-bulk-upload.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_bulk_upload.delete', {
            url: '/action/delete',
            templateUrl: 'app/cbs/cbs_bulk_upload/delete_cbs_bulk_upload.html',
            data: {
                authRequired: true,
                requiredPermission: 'CBS:CBS_BULK_UPLOAD:DELETE'
            },
            controller: 'cbsBulkUploadController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/cbs_bulk_upload/cbs-bulk-upload.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_bulk_upload.delete_detail', {
            url: '/action/delete/:functionality/:file',
            templateUrl: 'app/cbs/cbs_bulk_upload/delete_cbs_bulk_upload_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'CBS:CBS_BULK_UPLOAD:DELETE'
            },
            controller: 'cbsBulkUploadController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/cbs_bulk_upload/cbs-bulk-upload.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_bulk_upload.verify', {
            url: '/action/verify',
            templateUrl: 'app/cbs/cbs_bulk_upload/verify_cbs_bulk_upload.html',
            data: {
                authRequired: true,
                requiredPermission: 'CBS:CBS_BULK_UPLOAD:VERIFY'
            },
            controller: 'cbsBulkUploadController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/cbs_bulk_upload/cbs-bulk-upload.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_bulk_upload.verify_detail', {
            url: '/action/verify/:functionality/:file',
            templateUrl: 'app/cbs/cbs_bulk_upload/verify_cbs_bulk_upload_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'CBS:CBS_BULK_UPLOAD:VERIFY'
            },
            controller: 'cbsBulkUploadController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/cbs_bulk_upload/cbs-bulk-upload.controller.js?v='+versionNum]
                    });
                }
            }
        })

         .state('cbs_bulk_upload.download', {
                            url: '/download',
                            templateUrl: 'app/cbs/cbs_bulk_upload/download_cbs_bulk_upload.html',
                            data: {
                                authRequired: true,
                                requiredPermission: 'CBS:CBS_BULK_UPLOAD:UPLOAD'
                            },
                            controller: 'cbsBulkUploadController',
                            controllerAs: 'vm',
                            resolve: {
                                load: function($ocLazyLoad) {
                                    return $ocLazyLoad.load({
                                        files: ['app/cbs/cbs_bulk_upload/cbs-bulk-upload.controller.js?v='+versionNum]
                                    });
                                }
                            }
                        })

                  /* Office Account Place Holder */
        .state('office_account_place_holder', {
            abstract: true,
            url: '/office_account_place_holder',
            params: {
                views: ['add', 'modify', 'verify', 'delete', 'inquiry'],
                permissions: ['GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:ADD', 'GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:MODIFY', 'GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:VERIFY', 'GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:DELETE', 'GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:ENQUIRY']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/cbs/common/left-menu.directive.js',
                            'app/cbs/master_updation/office_account_place_holder/office-account-place-holder.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('office_account_place_holder.add', {
            url: '/add',
            templateUrl: 'app/cbs/master_updation/office_account_place_holder/add_office_account_place_holder.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:ADD'
            },
            controller: 'officeAccountPlaceHolderController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/office_account_place_holder/office-account-place-holder.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('office_account_place_holder.modify', {
            url: '/action/modify',
            templateUrl: 'app/cbs/master_updation/office_account_place_holder/modify_office_account_place_holder_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:MODIFY'
            },
            controller: 'officeAccountPlaceHolderController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/office_account_place_holder/office-account-place-holder.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('office_account_place_holder.modify_form', {
            url: '/action/modify/detail',
            templateUrl: 'app/cbs/master_updation/office_account_place_holder/modify_office_account_place_holder_form.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:MODIFY'
            },
            params: {obj: null},
            controller: 'officeAccountPlaceHolderController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/office_account_place_holder/office-account-place-holder.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('office_account_place_holder.verify', {
            url: '/action/verify',
            templateUrl: 'app/cbs/master_updation/office_account_place_holder/verify_office_account_place_holder_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:VERIFY'
            },
            controller: 'officeAccountPlaceHolderController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/office_account_place_holder/office-account-place-holder.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('office_account_place_holder.verify_detail', {
            url: '/action/verify/detail',
            templateUrl: 'app/cbs/master_updation/office_account_place_holder/verify_office_account_place_holder.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:VERIFY'
            },
            params: {obj: null},
            controller: 'officeAccountPlaceHolderController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/office_account_place_holder/office-account-place-holder.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('office_account_place_holder.delete', {
            url: '/action/delete',
            templateUrl: 'app/cbs/master_updation/office_account_place_holder/delete_office_account_place_holder_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:DELETE'
            },
            controller: 'officeAccountPlaceHolderController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/office_account_place_holder/office-account-place-holder.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('office_account_place_holder.delete_detail', {
            url: '/action/delete/detail',
            templateUrl: 'app/cbs/master_updation/office_account_place_holder/delete_office_account_place_holder.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:DELETE'
            },
            params: {obj: null},
            controller: 'officeAccountPlaceHolderController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/office_account_place_holder/office-account-place-holder.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('office_account_place_holder.list', {
            url: '/action/inquiry',
            templateUrl: 'app/cbs/master_updation/office_account_place_holder/office_account_place_holder_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:ENQUIRY'
            },
            controller: 'officeAccountPlaceHolderController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/office_account_place_holder/office-account-place-holder.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('office_account_place_holder.list_detail', {
            url: '/action/inquiry/detail',
            templateUrl: 'app/cbs/master_updation/office_account_place_holder/office_account_place_holder.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:ENQUIRY'
            },
            params: {obj: null},
            controller: 'officeAccountPlaceHolderController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/office_account_place_holder/office-account-place-holder.controller.js?v='+versionNum]
                    });
                }
            }
        })

           /* Branche of other Bank */
        .state('cbs_branches_of_other_bank', {
            abstract: true,
            url: '/cbs_branches_of_other_bank',
            params: {
                views: ['add', 'modify', 'verify', 'delete', 'inquiry'],
                permissions: ['GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:ADD', 'GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:MODIFY', 'GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:VERIFY', 'GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:DELETE', 'GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:ENQUIRY']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/cbs/common/left-menu.directive.js',
                            'app/cbs/master_updation/cbs_branches_of_other_bank/cbs-branches-of-other-bank.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('cbs_branches_of_other_bank.add', {
            url: '/add',
            templateUrl: 'app/cbs/master_updation/cbs_branches_of_other_bank/add_cbs_branches_of_other_bank.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:ADD'
            },
            controller: 'cbsBranchesOfOtherBankController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_branches_of_other_bank/cbs-branches-of-other-bank.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_branches_of_other_bank.modify', {
            url: '/action/modify',
            templateUrl: 'app/cbs/master_updation/cbs_branches_of_other_bank/modify_cbs_branches_of_other_bank_input.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:MODIFY'
            },
            controller: 'cbsBranchesOfOtherBankController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_branches_of_other_bank/cbs-branches-of-other-bank.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_branches_of_other_bank.modify_form', {
            url: '/action/modify/detail',
            templateUrl: 'app/cbs/master_updation/cbs_branches_of_other_bank/modify_cbs_branches_of_other_bank_input_detail.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:MODIFY'
            },
            params: {obj: null},
            controller: 'cbsBranchesOfOtherBankController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_branches_of_other_bank/cbs-branches-of-other-bank.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_branches_of_other_bank.verify', {
            url: '/action/verify',
            templateUrl: 'app/cbs/master_updation/cbs_branches_of_other_bank/verify_cbs_branches_of_other_bank_input.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:VERIFY'
            },
            controller: 'cbsBranchesOfOtherBankController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_branches_of_other_bank/cbs-branches-of-other-bank.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_branches_of_other_bank.verify_detail', {
            url: '/action/verify/detail',
            templateUrl: 'app/cbs/master_updation/cbs_branches_of_other_bank/verify_cbs_branches_of_other_bank_input_detail.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:VERIFY'
            },
            params: {obj: null},
            controller: 'cbsBranchesOfOtherBankController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_branches_of_other_bank/cbs-branches-of-other-bank.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_branches_of_other_bank.delete', {
            url: '/action/delete',
            templateUrl: 'app/cbs/master_updation/cbs_branches_of_other_bank/delete_cbs_branches_of_other_bank_input.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:DELETE'
            },
            controller: 'cbsBranchesOfOtherBankController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_branches_of_other_bank/cbs-branches-of-other-bank.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_branches_of_other_bank.delete_detail', {
            url: '/action/delete/details',
            templateUrl: 'app/cbs/master_updation/cbs_branches_of_other_bank/delete_cbs_branches_of_other_bank_input_detail.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:DELETE'
            },
            params: {obj: null},
            controller: 'cbsBranchesOfOtherBankController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_branches_of_other_bank/cbs-branches-of-other-bank.controller.js?v='+versionNum]
                    });
                }
            }
        })

       .state('cbs_branches_of_other_bank.inquiry', {
            url: '/action/inquiry',
            templateUrl: 'app/cbs/master_updation/cbs_branches_of_other_bank/inquiry_cbs_branches_of_other_bank.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:ENQUIRY'
            },
            controller: 'cbsBranchesOfOtherBankController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_branches_of_other_bank/cbs-branches-of-other-bank.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_branches_of_other_bank.inquiry_detail', {
            url: '/action/inquiry/detail',
            templateUrl: 'app/cbs/master_updation/cbs_branches_of_other_bank/inquiry_cbs_branches_of_other_bank_detail.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:ENQUIRY'
            },
            params: {obj: null},
            controller: 'cbsBranchesOfOtherBankController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_branches_of_other_bank/cbs-branches-of-other-bank.controller.js?v='+versionNum]
                    });
                }
            }
        })


        /* Branche of the Bank */
        .state('cbs_branches_of_the_bank', {
            abstract: true,
            url: '/cbs_branches_of_the_bank',
            params: {
                views: ['add', 'modify', 'verify', 'delete', 'inquiry'],
                permissions: ['GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:ADD', 'GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:MODIFY', 'GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:VERIFY', 'GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:DELETE', 'GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:ENQUIRY']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/cbs/common/left-menu.directive.js',
                            'app/cbs/master_updation/cbs_branches_of_the_bank/cbs-branches-of-the-bank.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('cbs_branches_of_the_bank.add', {
            url: '/add',
            templateUrl: 'app/cbs/master_updation/cbs_branches_of_the_bank/add_cbs_branches_of_the_bank.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:ADD'
            },
            controller: 'cbsBranchesOfTheBankController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_branches_of_the_bank/cbs-branches-of-the-bank.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_branches_of_the_bank.modify', {
            url: '/action/modify',
            templateUrl: 'app/cbs/master_updation/cbs_branches_of_the_bank/modify_cbs_branches_of_the_bank_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:MODIFY'
            },
            controller: 'cbsBranchesOfTheBankController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_branches_of_the_bank/cbs-branches-of-the-bank.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_branches_of_the_bank.modify_form', {
            url: '/action/modify/detail',
            templateUrl: 'app/cbs/master_updation/cbs_branches_of_the_bank/modify_cbs_branches_of_the_bank_form.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:MODIFY'
            },
            params: {obj: null},
            controller: 'cbsBranchesOfTheBankController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_branches_of_the_bank/cbs-branches-of-the-bank.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_branches_of_the_bank.verify', {
            url: '/action/verify',
            templateUrl: 'app/cbs/master_updation/cbs_branches_of_the_bank/verify_cbs_branches_of_the_bank_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:VERIFY'
            },
            controller: 'cbsBranchesOfTheBankController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_branches_of_the_bank/cbs-branches-of-the-bank.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_branches_of_the_bank.verify_detail', {
            url: '/action/verify/detail',
            templateUrl: 'app/cbs/master_updation/cbs_branches_of_the_bank/verify_cbs_branches_of_the_bank.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:VERIFY'
            },
            params: {obj: null},
            controller: 'cbsBranchesOfTheBankController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_branches_of_the_bank/cbs-branches-of-the-bank.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_branches_of_the_bank.delete', {
            url: '/action/delete',
            templateUrl: 'app/cbs/master_updation/cbs_branches_of_the_bank/delete_cbs_branches_of_the_bank_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:DELETE'
            },
            controller: 'cbsBranchesOfTheBankController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_branches_of_the_bank/cbs-branches-of-the-bank.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_branches_of_the_bank.delete_detail', {
            url: '/action/delete/detail',
            templateUrl: 'app/cbs/master_updation/cbs_branches_of_the_bank/delete_cbs_branches_of_the_bank.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:DELETE'
            },
            params: {obj: null},
            controller: 'cbsBranchesOfTheBankController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_branches_of_the_bank/cbs-branches-of-the-bank.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_branches_of_the_bank.list', {
            url: '/action/inquiry',
            templateUrl: 'app/cbs/master_updation/cbs_branches_of_the_bank/cbs_branches_of_the_bank_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:ENQUIRY'
            },
            controller: 'cbsBranchesOfTheBankController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_branches_of_the_bank/cbs-branches-of-the-bank.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_branches_of_the_bank.list_detail', {
            url: '/action/inquiry/detail',
            templateUrl: 'app/cbs/master_updation/cbs_branches_of_the_bank/cbs_branches_of_the_bank.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:ENQUIRY'
            },
            params: {obj: null},
            controller: 'cbsBranchesOfTheBankController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_branches_of_the_bank/cbs-branches-of-the-bank.controller.js?v='+versionNum]
                    });
                }
            }
        })


        /* List of Transaction Codes*/
        .state('cbs_list_of_transaction_codes', {
            abstract: true,
            url: '/cbs_list_of_transaction_codes',
            params: {
                views: ['add', 'modify', 'verify', 'delete', 'inquiry'],
                permissions: ['GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:ADD', 'GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:MODIFY', 'GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:VERIFY', 'GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:DELETE', 'GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:ENQUIRY']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/cbs/common/left-menu.directive.js',
                            'app/cbs/master_updation/cbs_list_of_transaction_codes/cbs-list-of-transaction-codes.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('cbs_list_of_transaction_codes.add', {
            url: '/add',
            templateUrl: 'app/cbs/master_updation/cbs_list_of_transaction_codes/add_cbs_list_of_transaction_codes.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:ADD'
            },
            controller: 'cbsListOfTransactionCodesController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_list_of_transaction_codes/cbs-list-of-transaction-codes.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_list_of_transaction_codes.modify', {
            url: '/action/modify',
            templateUrl: 'app/cbs/master_updation/cbs_list_of_transaction_codes/modify_cbs_list_of_transaction_codes_input.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:MODIFY'
            },
            controller: 'cbsListOfTransactionCodesController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_list_of_transaction_codes/cbs-list-of-transaction-codes.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_list_of_transaction_codes.modify_form', {
            url: '/action/modify/detail',
            templateUrl: 'app/cbs/master_updation/cbs_list_of_transaction_codes/modify_cbs_list_of_transaction_codes_input_detail.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:MODIFY'
            },
            params: {obj: null},
            controller: 'cbsListOfTransactionCodesController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_list_of_transaction_codes/cbs-list-of-transaction-codes.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_list_of_transaction_codes.verify', {
            url: '/action/verify',
            templateUrl: 'app/cbs/master_updation/cbs_list_of_transaction_codes/verify_cbs_list_of_transaction_codes_input.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:VERIFY'
            },
            controller: 'cbsListOfTransactionCodesController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_list_of_transaction_codes/cbs-list-of-transaction-codes.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_list_of_transaction_codes.verify_detail', {
            url: '/action/verify/detail',
            templateUrl: 'app/cbs/master_updation/cbs_list_of_transaction_codes/verify_cbs_list_of_transaction_codes_input_detail.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:VERIFY'
            },
            params: {obj: null},
            controller: 'cbsListOfTransactionCodesController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_list_of_transaction_codes/cbs-list-of-transaction-codes.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_list_of_transaction_codes.delete', {
            url: '/action/delete',
            templateUrl: 'app/cbs/master_updation/cbs_list_of_transaction_codes/delete_cbs_list_of_transaction_codes_input.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:DELETE'
            },
            controller: 'cbsListOfTransactionCodesController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_list_of_transaction_codes/cbs-list-of-transaction-codes.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_list_of_transaction_codes.delete_detail', {
            url: '/action/delete/detail',
            templateUrl: 'app/cbs/master_updation/cbs_list_of_transaction_codes/delete_cbs_list_of_transaction_codes_input_detail.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:DELETE'
            },
            params: {obj: null},
            controller: 'cbsListOfTransactionCodesController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_list_of_transaction_codes/cbs-list-of-transaction-codes.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_list_of_transaction_codes.list', {
            url: '/action/inquiry',
            templateUrl: 'app/cbs/master_updation/cbs_list_of_transaction_codes/inquiry_cbs_list_of_transaction_codes_input.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:ENQUIRY'
            },
            controller: 'cbsListOfTransactionCodesController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_list_of_transaction_codes/cbs-list-of-transaction-codes.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_list_of_transaction_codes.list_detail', {
            url: '/action/inquiry/detail',
            templateUrl: 'app/cbs/master_updation/cbs_list_of_transaction_codes/inquiry_cbs_list_of_transaction_codes_input_detail.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:ENQUIRY'
            },
            params: {obj: null},
            controller: 'cbsListOfTransactionCodesController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_list_of_transaction_codes/cbs-list-of-transaction-codes.controller.js?v='+versionNum]
                    });
                }
            }
        })

        /* Maintenance of calendar entries*/
        .state('cbs_maintenance_of_calendar_entries', {
            abstract: true,
            url: '/cbs_maintenance_of_calendar_entries',
            params: {
                views: ['add', 'modify', 'verify', 'inquiry'],
                permissions: ['GBM_CBS_MASTER_UPDATION:MAINTAINENCE_OF_CALENDER_ENTRIES:ADD','GBM_CBS_MASTER_UPDATION:MAINTAINENCE_OF_CALENDER_ENTRIES:MODIFY','GBM_CBS_MASTER_UPDATION:MAINTAINENCE_OF_CALENDER_ENTRIES:VERIFY','GBM_CBS_MASTER_UPDATION:MAINTAINENCE_OF_CALENDER_ENTRIES:ENQUIRY']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/cbs/common/left-menu.directive.js',
                            'app/cbs/master_updation/cbs_maintenance_of_calendar_entries/cbs-maintenance-of-calendar-entries.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('cbs_maintenance_of_calendar_entries.add', {
            url: '/add',
            templateUrl: 'app/cbs/master_updation/cbs_maintenance_of_calendar_entries/add_cbs_maintenance_of_calendar_entries.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:MAINTAINENCE_OF_CALENDER_ENTRIES:ADD'
            },
            params: {obj: null},
            controller: 'cbsMaintenanceOfCalendarEntriesController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_maintenance_of_calendar_entries/cbs-maintenance-of-calendar-entries.controller.js?v='+versionNum]
                    });
                }
            }
        })
          .state('cbs_maintenance_of_calendar_entries.add_detail', {
                    url: '/add/detail',
                    templateUrl: 'app/cbs/master_updation/cbs_maintenance_of_calendar_entries/add_cbs_maintenance_of_calendar_entries_detail.html',
                    data: {
                        authRequired: true,
                        requiredPermission: 'GBM_CBS_MASTER_UPDATION:MAINTAINENCE_OF_CALENDER_ENTRIES:ADD'
                    },
                    params: {obj: null},
                    controller: 'cbsMaintenanceOfCalendarEntriesController',
                    controllerAs: 'vm',
                    resolve: {
                        load: function($ocLazyLoad) {
                            return $ocLazyLoad.load({
                                files: ['app/cbs/master_updation/cbs_maintenance_of_calendar_entries/cbs-maintenance-of-calendar-entries.controller.js?v='+versionNum]
                            });
                        }
                    }
                })

        .state('cbs_maintenance_of_calendar_entries.modify', {
            url: '/action/modify',
            templateUrl: 'app/cbs/master_updation/cbs_maintenance_of_calendar_entries/modify_cbs_maintenance_of_calendar_entries_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:MAINTAINENCE_OF_CALENDER_ENTRIES:MODIFY'
            },
            params: {obj: null},
            controller: 'cbsMaintenanceOfCalendarEntriesController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_maintenance_of_calendar_entries/cbs-maintenance-of-calendar-entries.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_maintenance_of_calendar_entries.modify_detail', {
            url: '/action/modify',
            templateUrl: 'app/cbs/master_updation/cbs_maintenance_of_calendar_entries/modify_cbs_maintenance_of_calendar_entries.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:MAINTAINENCE_OF_CALENDER_ENTRIES:MODIFY'
            },
            params: {obj: null},
            controller: 'cbsMaintenanceOfCalendarEntriesController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_maintenance_of_calendar_entries/cbs-maintenance-of-calendar-entries.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_maintenance_of_calendar_entries.modify_form', {
            url: '/action/modify',
            templateUrl: 'app/cbs/master_updation/cbs_maintenance_of_calendar_entries/modify_cbs_maintenance_of_calendar_entries_form.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:MAINTAINENCE_OF_CALENDER_ENTRIES:MODIFY'
            },
            controller: 'cbsMaintenanceOfCalendarEntriesController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_maintenance_of_calendar_entries/cbs-maintenance-of-calendar-entries.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_maintenance_of_calendar_entries.verify', {
            url: '/action/verify',
            templateUrl: 'app/cbs/master_updation/cbs_maintenance_of_calendar_entries/verify_cbs_maintenance_of_calendar_entries_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:MAINTAINENCE_OF_CALENDER_ENTRIES:VERIFY'
            },
            controller: 'cbsMaintenanceOfCalendarEntriesController',
            controllerAs: 'vm',
              params: {obj: null},
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_maintenance_of_calendar_entries/cbs-maintenance-of-calendar-entries.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_maintenance_of_calendar_entries.verify_detail', {
            url: '/action/verify',
            templateUrl: 'app/cbs/master_updation/cbs_maintenance_of_calendar_entries/verify_cbs_maintenance_of_calendar_entries.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:MAINTAINENCE_OF_CALENDER_ENTRIES:VERIFY'
            },
              params: {obj: null},
            controller: 'cbsMaintenanceOfCalendarEntriesController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_maintenance_of_calendar_entries/cbs-maintenance-of-calendar-entries.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_maintenance_of_calendar_entries.list', {
            url: '/action/inquiry',
            templateUrl: 'app/cbs/master_updation/cbs_maintenance_of_calendar_entries/cbs_maintenance_of_calendar_entries_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:MAINTAINENCE_OF_CALENDER_ENTRIES:ENQUIRY'
            },
              params: {obj: null},
            controller: 'cbsMaintenanceOfCalendarEntriesController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_maintenance_of_calendar_entries/cbs-maintenance-of-calendar-entries.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_maintenance_of_calendar_entries.list_detail', {
            url: '/action/inquiry/detail',
            templateUrl: 'app/cbs/master_updation/cbs_maintenance_of_calendar_entries/cbs_maintenance_of_calendar_entries.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:MAINTAINENCE_OF_CALENDER_ENTRIES:ENQUIRY'
            },
              params: {obj: null},
            controller: 'cbsMaintenanceOfCalendarEntriesController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_maintenance_of_calendar_entries/cbs-maintenance-of-calendar-entries.controller.js?v='+versionNum]
                    });
                }
            }
        })
           /* Other Bank Details */
        .state('cbs_other_bank_details', {
            abstract: true,
            url: '/cbs_other_bank_details',
            params: {
                views: ['add', 'modify', 'verify', 'delete', 'inquiry'],
                permissions: ['GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:ADD', 'GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:MODIFY', 'GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:VERIFY', 'GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:DELETE', 'GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:ENQUIRY']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/cbs/common/left-menu.directive.js',
                            'app/cbs/master_updation/cbs_other_bank_details/cbs-other-bank-details.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('cbs_other_bank_details.add', {
            url: '/add',
            templateUrl: 'app/cbs/master_updation/cbs_other_bank_details/add_cbs_other_bank_details.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:ADD'
            },
            controller: 'cbsOtherBankDetailsController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_other_bank_details/cbs-other-bank-details.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_other_bank_details.modify', {
            url: '/action/modify',
            templateUrl: 'app/cbs/master_updation/cbs_other_bank_details/modify_cbs_other_bank_details_input.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:MODIFY'
            },
            controller: 'cbsOtherBankDetailsController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_other_bank_details/cbs-other-bank-details.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_other_bank_details.modify_form', {
            url: '/action/modify/detail',
            templateUrl: 'app/cbs/master_updation/cbs_other_bank_details/modify_cbs_other_bank_details_input_detail.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:MODIFY'
            },
            params: {obj: null},
            controller: 'cbsOtherBankDetailsController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_other_bank_details/cbs-other-bank-details.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_other_bank_details.verify', {
            url: '/action/verify',
            templateUrl: 'app/cbs/master_updation/cbs_other_bank_details/verify_cbs_other_bank_details_input.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:VERIFY'
            },
            controller: 'cbsOtherBankDetailsController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_other_bank_details/cbs-other-bank-details.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_other_bank_details.verify_detail', {
            url: '/action/verify/detail',
            templateUrl: 'app/cbs/master_updation/cbs_other_bank_details/verify_cbs_other_bank_details_input_detail.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:VERIFY'
            },
            params: {obj: null},
            controller: 'cbsOtherBankDetailsController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_other_bank_details/cbs-other-bank-details.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_other_bank_details.delete', {
            url: '/action/delete',
            templateUrl: 'app/cbs/master_updation/cbs_other_bank_details/delete_cbs_other_bank_details_input.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:DELETE'
            },
            controller: 'cbsOtherBankDetailsController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_other_bank_details/cbs-other-bank-details.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_other_bank_details.delete_detail', {
            url: '/action/delete/detail',
            templateUrl: 'app/cbs/master_updation/cbs_other_bank_details/delete_cbs_other_bank_details_input_detail.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:DELETE'
            },
            params: {obj: null},
            controller: 'cbsOtherBankDetailsController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_other_bank_details/cbs-other-bank-details.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_other_bank_details.list', {
            url: '/action/inquiry',
            templateUrl: 'app/cbs/master_updation/cbs_other_bank_details/inquiry_cbs_other_bank_details_input.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:ENQUIRY'
            },
            controller: 'cbsOtherBankDetailsController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_other_bank_details/cbs-other-bank-details.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_other_bank_details.list_detail', {
            url: '/action/inquiry/detail',
            templateUrl: 'app/cbs/master_updation/cbs_other_bank_details/inquiry_cbs_other_bank_details_input_detail.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:ENQUIRY'
            },

            params: {obj: null},
            controller: 'cbsOtherBankDetailsController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_other_bank_details/cbs-other-bank-details.controller.js?v='+versionNum]
                    });
                }
            }
        })

        /* User work class information*/
        .state('cbs_user_work_class_information', {
            abstract: true,
            url: '/cbs_user_work_class_information',
            params: {
                views: ['add', 'modify', 'verify', 'delete', 'inquiry'],
                permissions: ['GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:ADD', 'GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:MODIFY', 'GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:VERIFY', 'CBS:CBS_USER_WORK_CLASS_INFORMATION:DELETE', 'GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:ENQUIRY']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/cbs/common/left-menu.directive.js',
                            'app/cbs/master_updation/cbs_user_work_class_information/cbs-user-work-class-information.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('cbs_user_work_class_information.add', {
            url: '/add',
            templateUrl: 'app/cbs/master_updation/cbs_user_work_class_information/add_cbs_user_work_class_information.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:ADD'
            },
            controller: 'cbsUserWorkClassInformationController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_user_work_class_information/cbs-user-work-class-information.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_user_work_class_information.modify', {
            url: '/action/modify',
            templateUrl: 'app/cbs/master_updation/cbs_user_work_class_information/modify_cbs_user_work_class_information_input.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:MODIFY'
            },
            controller: 'cbsUserWorkClassInformationController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_user_work_class_information/cbs-user-work-class-information.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_user_work_class_information.modify_form', {
            url: '/action/modify',
            templateUrl: 'app/cbs/master_updation/cbs_user_work_class_information/modify_cbs_user_work_class_information_input_detail.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:MODIFY'
            },
            params: {obj: null},
            controller: 'cbsUserWorkClassInformationController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_user_work_class_information/cbs-user-work-class-information.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_user_work_class_information.verify', {
            url: '/action/verify',
            templateUrl: 'app/cbs/master_updation/cbs_user_work_class_information/verify_cbs_user_work_class_information_input.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:VERIFY'
            },
            controller: 'cbsUserWorkClassInformationController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_user_work_class_information/cbs-user-work-class-information.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_user_work_class_information.verify_detail', {
            url: '/action/verify/detail',
            templateUrl: 'app/cbs/master_updation/cbs_user_work_class_information/verify_cbs_user_work_class_information_input_detail.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:VERIFY'
            },
            params: {obj: null},
            controller: 'cbsUserWorkClassInformationController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_user_work_class_information/cbs-user-work-class-information.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_user_work_class_information.delete', {
            url: '/action/delete',
            templateUrl: 'app/cbs/master_updation/cbs_user_work_class_information/delete_cbs_user_work_class_information_input.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:DELETE'
            },
            controller: 'cbsUserWorkClassInformationController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_user_work_class_information/cbs-user-work-class-information.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_user_work_class_information.delete_detail', {
            url: '/action/delete/detail',
            templateUrl: 'app/cbs/master_updation/cbs_user_work_class_information/delete_cbs_user_work_class_information_input_detail.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:DELETE'
            },
            params: {obj: null},
            controller: 'cbsUserWorkClassInformationController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_user_work_class_information/cbs-user-work-class-information.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_user_work_class_information.list', {
            url: '/action/inquiry',
            templateUrl: 'app/cbs/master_updation/cbs_user_work_class_information/inquiry_cbs_user_work_class_information_input.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:ENQUIRY'
            },
            controller: 'cbsUserWorkClassInformationController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_user_work_class_information/cbs-user-work-class-information.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('cbs_user_work_class_information.list_detail', {
            url: '/action/inquiry/detail',
            templateUrl: 'app/cbs/master_updation/cbs_user_work_class_information/inquiry_cbs_user_work_class_information_input_detail.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:ENQUIRY'
            },
            params: {obj: null},
            controller: 'cbsUserWorkClassInformationController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/cbs_user_work_class_information/cbs-user-work-class-information.controller.js?v='+versionNum]
                    });
                }
            }
        })

        /* Branch ifsc codes */
        .state('branch_ifsc_codes', {
            abstract: true,
            url: '/branch_ifsc_codes',
            params: {
                views: ['add', 'modify', 'verify', 'delete', 'inquiry'],
                permissions: ['GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:ADD', 'GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:MODIFY', 'GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:VERIFY', 'GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:DELETE', 'GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:ENQUIRY']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/cbs/common/left-menu.directive.js',
                            'app/cbs/master_updation/branch_ifsc_codes/branch_ifsc_codes.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('branch_ifsc_codes.add', {
            url: '/add',
            templateUrl: 'app/cbs/master_updation/branch_ifsc_codes/add_branch_ifsc_codes_details.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:ADD'
            },
            controller: 'branchIfscCodeController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/branch_ifsc_codes/branch_ifsc_codes.controller.js?v='+versionNum]
                    });
                }
            }
        })
        .state('branch_ifsc_codes.modify', {
                    url: '/action/modify',
                    templateUrl: 'app/cbs/master_updation/branch_ifsc_codes/modify_branch_ifsc_codes.html',
                    data: {
                        authRequired: true,
                        requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:MODIFY'
                    },
                    controller: 'branchIfscCodeController',
                    controllerAs: 'vm',
                    resolve: {
                        load: function($ocLazyLoad) {
                            return $ocLazyLoad.load({
                                files: ['app/cbs/master_updation/branch_ifsc_codes/branch_ifsc_codes.controller.js?v='+versionNum]
                            });
                        }
                    }
                })

          .state('branch_ifsc_codes.delete', {
            url: '/action/delete',
            templateUrl: 'app/cbs/master_updation/branch_ifsc_codes/delete_branch_ifsc_codes.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:DELETE'
            },
            controller: 'branchIfscCodeController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/branch_ifsc_codes/branch_ifsc_codes.controller.js?v='+versionNum]
                    });
                }
            }
        })

         .state('branch_ifsc_codes.delete_detail', {
            url: '/action/delete/detail',
            templateUrl: 'app/cbs/master_updation/branch_ifsc_codes/delete_branch_ifsc_codes_details.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:DELETE'
            },
            params: {obj: null},
            controller: 'branchIfscCodeController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/branch_ifsc_codes/branch_ifsc_codes.controller.js?v='+versionNum]
                    });
                }
            }
        })

           .state('branch_ifsc_codes.verify', {
            url: '/action/verify',
            templateUrl: 'app/cbs/master_updation/branch_ifsc_codes/verify_branch_ifsc_codes.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:VERIFY'
            },
            controller: 'branchIfscCodeController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/branch_ifsc_codes/branch_ifsc_codes.controller.js?v='+versionNum]
                    });
                }
            }
        })

         .state('branch_ifsc_codes.verify_detail', {
            url: '/action/verify/detail',
            templateUrl: 'app/cbs/master_updation/branch_ifsc_codes/verify_branch_ifsc_codes_details.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:VERIFY'
            },
            params: {obj: null},
            controller: 'branchIfscCodeController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/branch_ifsc_codes/branch_ifsc_codes.controller.js?v='+versionNum]
                    });
                }
            }
        })



         .state('branch_ifsc_codes.modify_detail', {
            url: '/action/modify/detail',
            templateUrl: 'app/cbs/master_updation/branch_ifsc_codes/modify_branch_ifsc_codes_details.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:MODIFY'
            },
            params: {obj: null},
            controller: 'branchIfscCodeController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/branch_ifsc_codes/branch_ifsc_codes.controller.js?v='+versionNum]
                    });
                }
            }
        })

          .state('branch_ifsc_codes.list', {
            url: '/action/inquiry',
            templateUrl: 'app/cbs/master_updation/branch_ifsc_codes/inquiry_branch_ifsc_codes.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:ENQUIRY'
            },
            controller: 'branchIfscCodeController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/branch_ifsc_codes/branch_ifsc_codes.controller.js?v='+versionNum]
                    });
                }
            }
        })
        .state('branch_ifsc_codes.list_detail', {
            url: '/action/inquiry/detail',
            templateUrl: 'app/cbs/master_updation/branch_ifsc_codes/inquiry_branch_ifsc_codes_detail.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:ENQUIRY'
            },
            params: {obj: null},
            controller: 'branchIfscCodeController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/cbs/master_updation/branch_ifsc_codes/branch_ifsc_codes.controller.js?v='+versionNum]
                    });
                }
            }
        })


    }
})();
