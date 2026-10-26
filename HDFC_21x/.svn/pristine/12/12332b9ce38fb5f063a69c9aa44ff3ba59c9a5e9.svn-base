(function() {
    'use strict';

    angular
        .module('gbmUltraApp')
        .config(DCConfig);

    DCConfig.$inject = ['$stateProvider', '$ocLazyLoadProvider'];

    function DCConfig($stateProvider, $ocLazyLoadProvider) {
        $stateProvider

        .state('dc_nps_menu', {
            url: '/dc_nps_menu',
            templateUrl: 'app/dc/nps/dc_nps_menu.html'
        })



        /* CBS Schemes Master Mapping Start */
               .state('restricted_cbs_scheme', {
                        abstract: true,
                        url: '/restricted_cbs_scheme',
                        params: {
                            views: ['add','modify','verify', 'delete', 'inquiry'],
                                           permissions: ['DC:RESTRICTED_CBS_SCHEME:ADD','DC:RESTRICTED_CBS_SCHEME:MODIFY','DC:RESTRICTED_CBS_SCHEME:VERIFY', 'DC:RESTRICTED_CBS_SCHEME:DELETE','DC:RESTRICTED_CBS_SCHEME:ENQUIRY']
                                       },
                        resolve: {
                            load: function($ocLazyLoad) {
                                return $ocLazyLoad.load({
                                    files: [
                                        'app/dc/common/left-menu.directive.js',
                                        'app/dc/cbdt/cbs_schemes_master/cbs_schemes_master.service.js?v='+versionNum
                                    ]
                                });
                            }
                        }
                    })

               .state('restricted_cbs_scheme.add', {
                        url: '/add',
                        templateUrl: 'app/dc/cbdt/cbs_schemes_master/add_cbs_schemes_master.html',
        //                data: {
        //                    authRequired: true,
        //                   // requiredPermission: 'GBM_DC_CBDT:CBDT_LEVEL_MASTER_MAPPING:ADD'
        //                },
                        controller: 'cbsSchemesMasterController',
                        controllerAs: 'vm',
                        resolve: {
                            load: function($ocLazyLoad) {
                                return $ocLazyLoad.load({
                                    files: ['app/dc/cbdt/cbs_schemes_master/cbs_schemes_master.controller.js?v='+versionNum]
                                });
                            }
                        }
                    })

               .state('restricted_cbs_scheme.modify', {
                        url: '/action/modify',
                        templateUrl: 'app/dc/cbdt/cbs_schemes_master/modify_cbs_schemes_master.html',
        //                data: {
        //                    authRequired: true,
        //                   // requiredPermission: 'GBM_DC_CBDT:CBDT_LEVEL_MASTER_MAPPING:MODIFY'
        //                },
                        controller: 'cbsSchemesMasterController',
                        controllerAs: 'vm',
                        resolve: {
                            load: function($ocLazyLoad) {
                                return $ocLazyLoad.load({
                                    files: ['app/dc/cbdt/cbs_schemes_master/cbs_schemes_master.controller.js?v='+versionNum]
                                });
                            }
                        }
                    })

               .state('restricted_cbs_scheme.verify', {
                        url: '/action/verify',
                        templateUrl: 'app/dc/cbdt/cbs_schemes_master/verify_cbs_schemes_master.html',
        //                data: {
        //                    authRequired: true,
        //                   // requiredPermission: 'GBM_DC_CBDT:CBDT_LEVEL_MASTER_MAPPING:VERIFY'
        //                },
                        controller: 'cbsSchemesMasterController',
                        controllerAs: 'vm',
                        resolve: {
                            load: function($ocLazyLoad) {
                                return $ocLazyLoad.load({
                                    files: ['app/dc/cbdt/cbs_schemes_master/cbs_schemes_master.controller.js?v='+versionNum]
                                });
                            }
                        }
                    })

               .state('restricted_cbs_scheme.list', {
                    url: '/action/inquiry',
                    templateUrl: 'app/dc/cbdt/cbs_schemes_master/inquiry_cbs_schemes_master.html',
        //            data: {
        //                authRequired: true,
        //                //requiredPermission: 'GBM_DC_CBDT:CBDT_LEVEL_MASTER_MAPPING:ENQUIRY'
        //            },
                    controller: 'cbsSchemesMasterController',
                    controllerAs: 'vm',
                    resolve: {
                        load: function($ocLazyLoad) {
                            return $ocLazyLoad.load({
                                files: ['app/dc/cbdt/cbs_schemes_master/cbs_schemes_master.controller.js?v='+versionNum]
                            });
                        }
                    }
               })


                .state('restricted_cbs_scheme.delete', {
                           url: '/action/delete',
                           templateUrl: 'app/dc/cbdt/cbs_schemes_master/delete_cbs_schemes_master.html',
        //                   data: {
        //                       authRequired: true,
        //                      // requiredPermission: 'GBM_DC_CBDT:CBDT_LEVEL_MASTER_MAPPING:DELETE'
        //                   },
                           controller: 'cbsSchemesMasterController',
                           controllerAs: 'vm',
                           resolve: {
                               load: function($ocLazyLoad) {
                                   return $ocLazyLoad.load({
                                       files: ['app/dc/cbdt/cbs_schemes_master/cbs_schemes_master.controller.js?v='+versionNum]
                                   });
                               }
                           }
                      })
               /* CBS Schemes Master Mapping End */



        /* New Pension Scheme Parameter */
        .state('scheme_parameter', {
            abstract: true,
            url: '/scheme_parameter',
            params: {
                views: ['modify', 'verify', 'inquiry'],
                permissions: ['GBM_DC_NPS:NPS_NEW_PENSION_SCHEME_PARAMETER:MODIFY', 'GBM_DC_NPS:NPS_NEW_PENSION_SCHEME_PARAMETER:VERIFY', 'GBM_DC_NPS:NPS_NEW_PENSION_SCHEME_PARAMETER:ENQUIRY']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/dc/common/left-menu.directive.js',
                            'app/dc/nps/nps_parameter/scheme-parameter.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('scheme_parameter.modify', {
            url: '/action/modify',
            templateUrl: 'app/dc/nps/nps_parameter/modify_scheme_parameter.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NEW_PENSION_SCHEME_PARAMETER:MODIFY'
            },
            controller: 'schemeParameterController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_parameter/scheme-parameter.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('scheme_parameter.verify', {
            url: '/action/verify',
            templateUrl: 'app/dc/nps/nps_parameter/verify_scheme_parameter.html',
              data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NEW_PENSION_SCHEME_PARAMETER:VERIFY'
            },
            controller: 'schemeParameterController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_parameter/scheme-parameter.controller.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('scheme_parameter.list', {
            url: '/action/inquiry',
            templateUrl: 'app/dc/nps/nps_parameter/scheme_parameters.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NEW_PENSION_SCHEME_PARAMETER:ENQUIRY'
            },
            controller: 'schemeParameterController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_parameter/scheme-parameter.controller.js?v='+versionNum]
                    });
                }
            }
        })

        /* NPS scheme parameter lite */
         .state('scheme_parameter_lite', {
            abstract: true,
            url: '/scheme_parameter_lite',
            params: {
                views: ['modify', 'verify', 'inquiry'],
                permissions: ['GBM_DC_NPS:NPS_LITE_SCHEME_PARAMETER:MODIFY', 'GBM_DC_NPS:NPS_LITE_SCHEME_PARAMETER:VERIFY', 'GBM_DC_NPS:NPS_LITE_SCHEME_PARAMETER:ENQUIRY']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/dc/common/left-menu.directive.js',
                            'app/dc/nps/new_pension_scheme_parameter_lite/new-pension-scheme-parameter-lite.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })


        .state('scheme_parameter_lite.modify', {
            url: '/action/modify',
            templateUrl: 'app/dc/nps/new_pension_scheme_parameter_lite/modify_new_pension_scheme_parameter_lite.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_LITE_SCHEME_PARAMETER:MODIFY'
            },
            controller: 'liteSchemeParameterController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/new_pension_scheme_parameter_lite/new-pension-scheme-parameter-lite.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('scheme_parameter_lite.verify', {
            url: '/action/verify',
            templateUrl: 'app/dc/nps/new_pension_scheme_parameter_lite/verify_new_pension_scheme_parameter_lite.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_LITE_SCHEME_PARAMETER:VERIFY'
            },
            controller: 'liteSchemeParameterController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/new_pension_scheme_parameter_lite/new-pension-scheme-parameter-lite.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('scheme_parameter_lite.list', {
            url: '/action/inquiry',
            templateUrl: 'app/dc/nps/new_pension_scheme_parameter_lite/new_pension_scheme_parameter_lite.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_LITE_SCHEME_PARAMETER:ENQUIRY'
            },
            controller: 'liteSchemeParameterController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/new_pension_scheme_parameter_lite/new-pension-scheme-parameter-lite.controller.js?v='+versionNum]
                    });
                }
            }
        })

        /* New Pension Hierarchy */
        .state('new_pension_hierarchy', {
            abstract: true,
            url: '/new_pension_hierarchy',
            params: {
                views: ['add', 'modify', 'verify', 'inquiry', 'delete'],
                permissions: ['GBM_DC_NPS:NPS_NEW_PENSION_HIERARCHY:ADD', 'GBM_DC_NPS:NPS_NEW_PENSION_HIERARCHY:MODIFY', 'GBM_DC_NPS:NPS_NEW_PENSION_HIERARCHY:VERIFY', 'GBM_DC_NPS:NPS_NEW_PENSION_HIERARCHY:ENQUIRY', 'GBM_DC_NPS:NPS_NEW_PENSION_HIERARCHY:DELETE']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/dc/common/left-menu.directive.js',
                            'app/dc/nps/new_pension_hierarchy/new-pension-hierarchy.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('new_pension_hierarchy.add', {
            url: '/add',
            templateUrl: 'app/dc/nps/new_pension_hierarchy/add_new_pension_hierarchy.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NEW_PENSION_HIERARCHY:ADD'
            },
            controller: 'newPensionHierarchyController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/new_pension_hierarchy/new-pension-hierarchy.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('new_pension_hierarchy.modify_list', {
            url: '/action/modify/:parameter',
            templateUrl: 'app/dc/nps/new_pension_hierarchy/modify_pension_hierarchy_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NEW_PENSION_HIERARCHY:MODIFY'
            },
            controller: 'newPensionHierarchyController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/new_pension_hierarchy/new-pension-hierarchy.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('new_pension_hierarchy.modify', {
            url: '/action/modify',
            templateUrl: 'app/dc/nps/new_pension_hierarchy/modify_new_pension_hierarchy.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NEW_PENSION_HIERARCHY:MODIFY'
            },
            controller: 'newPensionHierarchyController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/new_pension_hierarchy/new-pension-hierarchy.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('new_pension_hierarchy.modify_form', {
            url: '/:id/:level/modify',
            templateUrl: 'app/dc/nps/new_pension_hierarchy/modify_new_pension_hierarchy_form.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NEW_PENSION_HIERARCHY:MODIFY'
            },
            controller: 'newPensionHierarchyController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/new_pension_hierarchy/new-pension-hierarchy.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('new_pension_hierarchy.delete_list', {
            url: '/action/delete/:parameter',
            templateUrl: 'app/dc/nps/new_pension_hierarchy/delete_new_pension_hierarchy_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NEW_PENSION_HIERARCHY:DELETE'
            },
            controller: 'newPensionHierarchyController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/new_pension_hierarchy/new-pension-hierarchy.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('new_pension_hierarchy.delete', {
            url: '/action/delete',
            templateUrl: 'app/dc/nps/new_pension_hierarchy/delete_new_pension_hierarchy.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NEW_PENSION_HIERARCHY:DELETE'
            },
            controller: 'newPensionHierarchyController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/new_pension_hierarchy/new-pension-hierarchy.controller.js?v='+versionNum]
                    });
                }
            }
        })

         .state('new_pension_hierarchy.delete_form', {
            url: '/:id/:level/delete',
            templateUrl: 'app/dc/nps/new_pension_hierarchy/delete_new_pension_hierarchy_form.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NEW_PENSION_HIERARCHY:DELETE'
            },
            controller: 'newPensionHierarchyController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/new_pension_hierarchy/new-pension-hierarchy.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('new_pension_hierarchy.verify_list', {
            url: '/action/verify/:parameter',
            templateUrl: 'app/dc/nps/new_pension_hierarchy/verify_new_pension_hierarchy_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NEW_PENSION_HIERARCHY:VERIFY'
            },
            controller: 'newPensionHierarchyController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/new_pension_hierarchy/new-pension-hierarchy.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('new_pension_hierarchy.verify', {
            url: '/action/verify',
            templateUrl: 'app/dc/nps/new_pension_hierarchy/verify_new_pension_hierarchy.html',
              data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NEW_PENSION_HIERARCHY:VERIFY'
            },
            controller: 'newPensionHierarchyController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/new_pension_hierarchy/new-pension-hierarchy.controller.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('new_pension_hierarchy.verify_form', {
            url: '/:id/:level/verify',
            templateUrl: 'app/dc/nps/new_pension_hierarchy/verify_new_pension_hierarchy_form.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NEW_PENSION_HIERARCHY:VERIFY'
            },
            controller: 'newPensionHierarchyController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/new_pension_hierarchy/new-pension-hierarchy.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('new_pension_hierarchy.list', {
            url: '/action/inquiry',
            templateUrl: 'app/dc/nps/new_pension_hierarchy/new_pension_hierarchy.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NEW_PENSION_HIERARCHY:ENQUIRY'
            },
            controller: 'newPensionHierarchyController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/new_pension_hierarchy/new-pension-hierarchy.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('new_pension_hierarchy.list_detail', {
            url: '/action/inquiry/:parameter',
            templateUrl: 'app/dc/nps/new_pension_hierarchy/new_pension_hierarchy_detail.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NEW_PENSION_HIERARCHY:ENQUIRY'
            },
            controller: 'newPensionHierarchyController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/new_pension_hierarchy/new-pension-hierarchy.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('new_pension_hierarchy.form', {
            url: '/:id/:level/inquiry',
            templateUrl: 'app/dc/nps/new_pension_hierarchy/new_pension_hierarchy_form.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NEW_PENSION_HIERARCHY:ENQUIRY'
            },
            controller: 'newPensionHierarchyController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/new_pension_hierarchy/new-pension-hierarchy.controller.js?v='+versionNum]
                    });
                }
            }
        })

        /* NPS BOD Status Inquiry */
        .state('nps_bod_status_inquiry', {
            abstract: true,
            url: '/nps_bod_status_inquiry',
            params: {
                views: ['inquiry'],
                permissions: ['GBM_DC_NPS:NPS_NPS_BOD_STATUS_INQUIRY:ENQUIRY']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/dc/common/left-menu.directive.js',
                            'app/dc/nps/nps_bod_status_inquiry/nps-bod-status-inquiry.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('nps_bod_status_inquiry.list', {
            url: '/action/inquiry',
            templateUrl: 'app/dc/nps/nps_bod_status_inquiry/nps_bod_status_inquiry.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NPS_BOD_STATUS_INQUIRY:ENQUIRY'
            },
            controller: 'npsBodStatusInquiryController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_bod_status_inquiry/nps-bod-status-inquiry.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('nps_bod_status_inquiry.list_detail', {
            url: '/action/inquiry/search/:solId/:status/:bodDate',
            templateUrl: 'app/dc/nps/nps_bod_status_inquiry/nps_bod_status_inquiry_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NPS_BOD_STATUS_INQUIRY:ENQUIRY'
            },
            controller: 'npsBodStatusInquiryController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_bod_status_inquiry/nps-bod-status-inquiry.controller.js?v='+versionNum]
                    });
                }
            }
        })

        /* NPS MCP Status Inquiry */
        .state('nps_mcp_status_inquiry', {
            abstract: true,
            url: '/nps_mcp_status_inquiry',
            params: {
                views: ['inquiry'],
                permissions: ['GBM_DC_NPS:NPS_NPS_MCP_STATUS_INQUIRY:ENQUIRY']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/dc/common/left-menu.directive.js',
                            'app/dc/nps/nps_mcp_status_inquiry/nps-mcp-status-inquiry.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('nps_mcp_status_inquiry.list', {
            url: '/action/inquiry',
            templateUrl: 'app/dc/nps/nps_mcp_status_inquiry/nps_mcp_status_inquiry.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NPS_MCP_STATUS_INQUIRY:ENQUIRY'
            },
            controller: 'npsMcpStatusInquiryController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_mcp_status_inquiry/nps-mcp-status-inquiry.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('nps_mcp_status_inquiry.list_detail', {
            url: '/action/inquiry/search/:solId/:status/:mcpFromDate/:mcpToDate',
            templateUrl: 'app/dc/nps/nps_mcp_status_inquiry/nps_mcp_status_inquiry_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NPS_MCP_STATUS_INQUIRY:ENQUIRY'
            },
            controller: 'npsMcpStatusInquiryController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_mcp_status_inquiry/nps-mcp-status-inquiry.controller.js?v='+versionNum]
                    });
                }
            }
        })

        /* NPS Transaction Inquiry */
        .state('nps_transaction_inquiry', {
            abstract: true,
            url: '/nps_transaction_inquiry',
            params: {
                views: ['inquiry'],
                permissions: ['GBM_DC_NPS:NPS_NPS_TRANSACTION_INQUIRY:ENQUIRY']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/dc/common/left-menu.directive.js',
                            'app/dc/nps/nps_transaction_inquiry/nps-transaction-inquiry.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('nps_transaction_inquiry.list', {
            url: '/action/inquiry',
            templateUrl: 'app/dc/nps/nps_transaction_inquiry/nps_transaction_inquiry.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NPS_TRANSACTION_INQUIRY:ENQUIRY'
            },
            controller: 'npsTransactionInquiryController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_transaction_inquiry/nps-transaction-inquiry.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('nps_transaction_inquiry.list_detail', {
            url: '/action/inquiry/search',
            templateUrl: 'app/dc/nps/nps_transaction_inquiry/nps_transaction_inquiry_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NPS_TRANSACTION_INQUIRY:ENQUIRY'
            },
            controller: 'npsTransactionInquiryController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_transaction_inquiry/nps-transaction-inquiry.controller.js?v='+versionNum]
                    });
                }
            }
        })

         /* System Parameter */
                .state('system_parameter', {
                    abstract: true,
                    url: '/system_parameter',
                    params: {
                        views: ['modify', 'verify', 'inquiry'],
                        permissions: ['DC:SYSTEM_PARAMETER:MODIFY', 'DC:SYSTEM_PARAMETER:VERIFY', 'DC:SYSTEM_PARAMETER:ENQUIRY']
                    },
                    resolve: {
                        load: function($ocLazyLoad) {
                            return $ocLazyLoad.load({
                                files: [
                                    'app/dc/common/left-menu.directive.js',
                                    'app/common/dc/system_parameter/system-parameter.service.js?v='+versionNum
                                ]
                            });
                        }
                    }
                })

                .state('system_parameter.modify', {
                    url: '/action/modify',
                    templateUrl: 'app/common/dc/system_parameter/modify_system_parameter.html',
                    data: {
                        authRequired: true,
                        requiredPermission: 'DC:SYSTEM_PARAMETER:MODIFY'
                    },
                    controller: 'systemParameterController',
                    controllerAs: 'vm',
                    resolve: {
                        load: function($ocLazyLoad) {
                            return $ocLazyLoad.load({
                                files: ['app/common/dc/system_parameter/system-parameter.controller.js?v='+versionNum]
                            });
                        }
                    }
                })

                .state('system_parameter.list', {
                    url: '/action/inquiry',
                    templateUrl: 'app/common/dc/system_parameter/system_parameter.html',
                    data: {
                        authRequired: true,
                        requiredPermission: 'DC:SYSTEM_PARAMETER:ENQUIRY'
                    },
                    controller: 'systemParameterController',
                    controllerAs: 'vm',
                    resolve: {
                        load: function($ocLazyLoad) {
                            return $ocLazyLoad.load({
                                files: ['app/common/dc/system_parameter/system-parameter.controller.js?v='+versionNum]
                            });
                        }
                    }
                })

                .state('system_parameter.verify', {
                    url: '/action/verify',
                    templateUrl: 'app/common/dc/system_parameter/verify_system_parameter.html',
                    data: {
                        authRequired: true,
                        requiredPermission: 'DC:SYSTEM_PARAMETER:VERIFY'
                    },
                    controller: 'systemParameterController',
                    controllerAs: 'vm',
                    resolve: {
                        load: function($ocLazyLoad) {
                            return $ocLazyLoad.load({
                                files: ['app/common/dc/system_parameter/system-parameter.controller.js?v='+versionNum]
                            });
                        }
                    }
                })

        /* Hierarchy Bulk Upload */
        .state('hierarchy_bulk_upload', {
            abstract: true,
            url: '/hierarchy_bulk_upload',
            params: {
                views: ['upload'],
                permissions: ['DC:HIERARCHY_BULK_UPLOAD:UPLOAD']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/dc/common/left-menu.directive.js',
                            'app/dc/nps/hierarchy_bulk_upload/hierarchy-bulk-upload.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('hierarchy_bulk_upload.upload', {
            url: '/action/upload',
            templateUrl: 'app/dc/nps/hierarchy_bulk_upload/hierarchy_bulk_upload.html',
            data: {
                authRequired: true,
                requiredPermission: 'DC:HIERARCHY_BULK_UPLOAD:UPLOAD'
            },
            controller: 'hierarchyBulkUploadController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/hierarchy_bulk_upload/hierarchy-bulk-upload.controller.js?v='+versionNum]
                    });
                }
            }
        })

        /* lite service request master */
        .state('lite_service_request_master', {
            abstract: true,
            url: '/lite_service_request_master',
            params: {
                views: ['modify', 'verify', 'inquiry'],
                permissions: ['GBM_DC_NPS:NPS_LITE_SERVICE_REQUEST_MASTER:MODIFY', 'GBM_DC_NPS:NPS_LITE_SERVICE_REQUEST_MASTER:VERIFY', 'GBM_DC_NPS:NPS_LITE_SERVICE_REQUEST_MASTER:ENQUIRY']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/dc/common/left-menu.directive.js',
                            'app/dc/nps/lite_service_request_master/lite-service-request-master.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('lite_service_request_master.modify', {
            url: '/action/modify',
            templateUrl: 'app/dc/nps/lite_service_request_master/modify_lite_service_request_master.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_LITE_SERVICE_REQUEST_MASTER:MODIFY'
            },
            controller: 'liteServiceRequestController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/lite_service_request_master/lite-service-request-master.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('lite_service_request_master.modify_form', {
            url: '/action/modify/:aggregatorId/:serviceCode/:tierType',
            templateUrl: 'app/dc/nps/lite_service_request_master/modify_lite_service_request_master_form.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_LITE_SERVICE_REQUEST_MASTER:MODIFY'
            },
            controller: 'liteServiceRequestController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/lite_service_request_master/lite-service-request-master.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('lite_service_request_master.list', {
            url: '/action/inquiry',
            templateUrl: 'app/dc/nps/lite_service_request_master/lite_service_request_master.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_LITE_SERVICE_REQUEST_MASTER:ENQUIRY'
            },
            controller: 'liteServiceRequestController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/lite_service_request_master/lite-service-request-master.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('lite_service_request_master.list_detail', {
            url: '/action/inquiry/:aggregatorId/:serviceCode/:tierType',
            templateUrl: 'app/dc/nps/lite_service_request_master/lite_service_request_master_detail.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_LITE_SERVICE_REQUEST_MASTER:ENQUIRY'
            },
            controller: 'liteServiceRequestController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/lite_service_request_master/lite-service-request-master.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('lite_service_request_master.verify', {
            url: '/action/verify',
            templateUrl: 'app/dc/nps/lite_service_request_master/verify_lite_service_request_master.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_LITE_SERVICE_REQUEST_MASTER:VERIFY'
            },
            controller: 'liteServiceRequestController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/lite_service_request_master/lite-service-request-master.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('lite_service_request_master.verify_detail', {
            url: '/action/verify_detail/:aggregatorId/:serviceCode/:tierType',
            templateUrl: 'app/dc/nps/lite_service_request_master/verify_lite_service_request_master_details.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_LITE_SERVICE_REQUEST_MASTER:VERIFY'
            },
            controller: 'liteServiceRequestController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/lite_service_request_master/lite-service-request-master.controller.js?v='+versionNum]
                    });
                }
            }
        })

        /* uos service request master */
        .state('uos_service_request_master', {
            abstract: true,
            url: '/uos_service_request_master',
            params: {
                views: ['modify', 'verify', 'inquiry'],
                permissions: ['GBM_DC_NPS:NPS_UOS_SERVICE_REQUEST_MASTER:MODIFY', 'GBM_DC_NPS:NPS_UOS_SERVICE_REQUEST_MASTER:VERIFY', 'GBM_DC_NPS:NPS_UOS_SERVICE_REQUEST_MASTER:ENQUIRY']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/dc/common/left-menu.directive.js',
                            'app/dc/nps/uos_service_request_master/uos-service-request-master.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('uos_service_request_master.modify', {
            url: '/action/modify',
            templateUrl: 'app/dc/nps/uos_service_request_master/modify_uos_service_request_master.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_UOS_SERVICE_REQUEST_MASTER:MODIFY'
            },
            controller: 'uosServiceRequestController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/uos_service_request_master/uos-service-request-master.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('uos_service_request_master.modify_form', {
            url: '/action/modify/:serviceCode',
            templateUrl: 'app/dc/nps/uos_service_request_master/modify_uos_service_request_master_form.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_UOS_SERVICE_REQUEST_MASTER:MODIFY'
            },
            controller: 'uosServiceRequestController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/uos_service_request_master/uos-service-request-master.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('uos_service_request_master.list', {
            url: '/action/inquiry',
            templateUrl: 'app/dc/nps/uos_service_request_master/uos_service_request_master.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_UOS_SERVICE_REQUEST_MASTER:ENQUIRY'
            },
            controller: 'uosServiceRequestController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/uos_service_request_master/uos-service-request-master.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('uos_service_request_master.list_detail', {
            url: '/action/inquiry/:serviceCode',
            templateUrl: 'app/dc/nps/uos_service_request_master/uos_service_request_master_details.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_UOS_SERVICE_REQUEST_MASTER:ENQUIRY'
            },
            controller: 'uosServiceRequestController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/uos_service_request_master/uos-service-request-master.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('uos_service_request_master.verify', {
            url: '/action/verify',
            templateUrl: 'app/dc/nps/uos_service_request_master/verify_uos_service_request_master.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_UOS_SERVICE_REQUEST_MASTER:VERIFY'
            },
            controller: 'uosServiceRequestController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/uos_service_request_master/uos-service-request-master.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('uos_service_request_master.verify_detail', {
            url: '/action/verify/:serviceCode',
            templateUrl: 'app/dc/nps/uos_service_request_master/verify_uos_service_request_master_details.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_UOS_SERVICE_REQUEST_MASTER:VERIFY'
            },
            controller: 'uosServiceRequestController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/uos_service_request_master/uos-service-request-master.controller.js?v='+versionNum]
                    });
                }
            }
        })

        /* corporate service request master */
        .state('corporate_service_request_master', {
            abstract: true,
            url: '/corporate_service_request_master',
            params: {
                views: ['modify', 'verify', 'inquiry'],
                permissions: ['GBM_DC_NPS:NPS_CORPORATE_SERVICE_REQUEST_MASTER:MODIFY', 'GBM_DC_NPS:NPS_CORPORATE_SERVICE_REQUEST_MASTER:VERIFY', 'GBM_DC_NPS:NPS_CORPORATE_SERVICE_REQUEST_MASTER:ENQUIRY']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/dc/common/left-menu.directive.js',
                            'app/dc/nps/corporate_service_request_master/corporate-service-request-master.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('corporate_service_request_master.modify', {
            url: '/action/modify',
            templateUrl: 'app/dc/nps/corporate_service_request_master/modify_corporate_service_request_master.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_CORPORATE_SERVICE_REQUEST_MASTER:MODIFY'
            },
            controller: 'corporateServiceRequestController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/corporate_service_request_master/corporate-service-request-master.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('corporate_service_request_master.modify_detail', {
            url: '/action/modify/:corporateId/:serviceCode/:tierType',
            templateUrl: 'app/dc/nps/corporate_service_request_master/modify_corporate_service_request_master_form.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_CORPORATE_SERVICE_REQUEST_MASTER:MODIFY'
            },
            controller: 'corporateServiceRequestController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/corporate_service_request_master/corporate-service-request-master.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('corporate_service_request_master.list', {
            url: '/action/inquiry',
            templateUrl: 'app/dc/nps/corporate_service_request_master/corporate_service_request_master.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_CORPORATE_SERVICE_REQUEST_MASTER:ENQUIRY'
            },
            controller: 'corporateServiceRequestController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/corporate_service_request_master/corporate-service-request-master.controller.js?v='+versionNum]
                    });
                }
            }
        })


        .state('corporate_service_request_master.list_detail', {
            url: '/action/inquiry/:id',
            templateUrl: 'app/dc/nps/corporate_service_request_master/corporate_service_request_master_detail.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_CORPORATE_SERVICE_REQUEST_MASTER:ENQUIRY'
            },
            controller: 'corporateServiceRequestController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/corporate_service_request_master/corporate-service-request-master.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('corporate_service_request_master.verify', {
            url: '/action/verify',
            templateUrl: 'app/dc/nps/corporate_service_request_master/verify_corporate_service_request_master.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_CORPORATE_SERVICE_REQUEST_MASTER:VERIFY'
            },
            controller: 'corporateServiceRequestController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/corporate_service_request_master/corporate-service-request-master.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('corporate_service_request_master.verify_detail', {
            url: '/action/verify/:id',
            templateUrl: 'app/dc/nps/corporate_service_request_master/verify_corporate_service_request_master_detail.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_CORPORATE_SERVICE_REQUEST_MASTER:VERIFY'
            },
            controller: 'corporateServiceRequestController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/corporate_service_request_master/corporate-service-request-master.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('corporate_service_request_master.verify_form', {
            url: '/action/verify/:corporateId/:serviceCode/:tierType',
            templateUrl: 'app/dc/nps/corporate_service_request_master/verify_corporate_service_request_master_form.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_CORPORATE_SERVICE_REQUEST_MASTER:VERIFY'
            },
            controller: 'corporateServiceRequestController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/corporate_service_request_master/corporate-service-request-master.controller.js?v='+versionNum]
                    });
                }
            }
        })

        /* Receipt Number */
        .state('nps_receipt_number', {
            abstract: true,
            url: '/nps_receipt_number',
            params: {
                views: ['add', 'modify', 'verify', 'inquiry', 'delete'],
                permissions: ['GBM_DC_NPS:NPS_RECEIPT_NUMBER:ADD', 'GBM_DC_NPS:NPS_RECEIPT_NUMBER:MODIFY', 'GBM_DC_NPS:NPS_RECEIPT_NUMBER:VERIFY', 'GBM_DC_NPS:NPS_RECEIPT_NUMBER:ENQUIRY', 'GBM_DC_NPS:NPS_RECEIPT_NUMBER:DELETE']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/dc/common/left-menu.directive.js',
                            'app/dc/nps/nps_receipt_number/nps-receipt-number.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('nps_receipt_number.add', {
            url: '/add',
            templateUrl: 'app/dc/nps/nps_receipt_number/add_nps_receipt_number.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_RECEIPT_NUMBER:ADD'
            },
            controller: 'receiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_receipt_number/nps-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('nps_receipt_number.modify', {
            url: '/action/modify',
            templateUrl: 'app/dc/nps/nps_receipt_number/modify_nps_receipt_number.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_RECEIPT_NUMBER:MODIFY'
            },
            controller: 'receiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_receipt_number/nps-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('nps_receipt_number.modify_detail', {
            url: '/action/modify/:solId/:serviceCode/:registrationNumber',
            templateUrl: 'app/dc/nps/nps_receipt_number/modify_nps_receipt_number_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_RECEIPT_NUMBER:MODIFY'
            },
            controller: 'receiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_receipt_number/nps-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('nps_receipt_number.modify_form', {
            url: '/action/modify/:id',
            templateUrl: 'app/dc/nps/nps_receipt_number/modify_nps_receipt_number_form.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_RECEIPT_NUMBER:MODIFY'
            },
            controller: 'receiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_receipt_number/nps-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('nps_receipt_number.verify_form', {
            url: '/action/verify/:id',
            templateUrl: 'app/dc/nps/nps_receipt_number/verify_nps_receipt_number_form.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_RECEIPT_NUMBER:VERIFY'
            },
            controller: 'receiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_receipt_number/nps-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('nps_receipt_number.delete_form', {
            url: '/action/delete/:id',
            templateUrl: 'app/dc/nps/nps_receipt_number/delete_nps_receipt_number_form.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_RECEIPT_NUMBER:DELETE'
            },
            controller: 'receiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_receipt_number/nps-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('nps_receipt_number.form', {
            url: '/action/inquiry/:id',
            templateUrl: 'app/dc/nps/nps_receipt_number/nps_receipt_number_form.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_RECEIPT_NUMBER:ENQUIRY'
            },
            controller: 'receiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_receipt_number/nps-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('nps_receipt_number.verify', {
            url: '/action/verify',
            templateUrl: 'app/dc/nps/nps_receipt_number/verify_nps_receipt_number.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_RECEIPT_NUMBER:VERIFY'
            },
            controller: 'receiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_receipt_number/nps-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('nps_receipt_number.verify_detail', {
            url: '/action/verify/:solId/:serviceCode/:registrationNumber',
            templateUrl: 'app/dc/nps/nps_receipt_number/verify_nps_receipt_number_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_RECEIPT_NUMBER:VERIFY'
            },
            controller: 'receiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_receipt_number/nps-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('nps_receipt_number.delete', {
            url: '/action/delete',
            templateUrl: 'app/dc/nps/nps_receipt_number/delete_nps_receipt_number.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_RECEIPT_NUMBER:DELETE'
            },
            controller: 'receiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_receipt_number/nps-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('nps_receipt_number.delete_detail', {
            url: '/action/delete/:solId/:serviceCode/:registrationNumber',
            templateUrl: 'app/dc/nps/nps_receipt_number/delete_nps_receipt_number_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_RECEIPT_NUMBER:DELETE'
            },
            controller: 'receiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_receipt_number/nps-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('nps_receipt_number.list', {
            url: '/action/inquiry',
            templateUrl: 'app/dc/nps/nps_receipt_number/nps_receipt_number.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_RECEIPT_NUMBER:ENQUIRY'
            },
            controller: 'receiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_receipt_number/nps-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('nps_receipt_number.list_detail', {
            url: '/action/inquiry/:solId/:serviceCode/:registrationNumber',
            templateUrl: 'app/dc/nps/nps_receipt_number/nps_receipt_number_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_RECEIPT_NUMBER:ENQUIRY'
            },
            controller: 'receiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_receipt_number/nps-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        /* Corporate Receipt Number */
        .state('corporate_receipt_number', {
            abstract: true,
            url: '/corporate_receipt_number',
            params: {
                views: ['add', 'modify', 'verify', 'inquiry', 'delete'],
                permissions: ['GBM_DC_NPS:NPS_CORPORATE_RECEIPT_NUMBER:ADD', 'GBM_DC_NPS:NPS_CORPORATE_RECEIPT_NUMBER:MODIFY', 'GBM_DC_NPS:NPS_CORPORATE_RECEIPT_NUMBER:VERIFY', 'GBM_DC_NPS:NPS_CORPORATE_RECEIPT_NUMBER:ENQUIRY', 'GBM_DC_NPS:NPS_CORPORATE_RECEIPT_NUMBER:DELETE']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/dc/common/left-menu.directive.js',
                            'app/dc/nps/corporate_receipt_number/corporate-receipt-number.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('corporate_receipt_number.add', {
            url: '/add',
            templateUrl: 'app/dc/nps/corporate_receipt_number/add_corporate_receipt_number.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_CORPORATE_RECEIPT_NUMBER:ADD'
            },
            controller: 'corporateReceiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/corporate_receipt_number/corporate-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('corporate_receipt_number.modify', {
            url: '/action/modify',
            templateUrl: 'app/dc/nps/corporate_receipt_number/modify_corporate_receipt_number.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_CORPORATE_RECEIPT_NUMBER:MODIFY'
            },
            controller: 'corporateReceiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/corporate_receipt_number/corporate-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('corporate_receipt_number.delete', {
            url: '/action/delete',
            templateUrl: 'app/dc/nps/corporate_receipt_number/delete_corporate_receipt_number.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_CORPORATE_RECEIPT_NUMBER:DELETE'
            },
            controller: 'corporateReceiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/corporate_receipt_number/corporate-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('corporate_receipt_number.verify', {
            url: '/action/verify',
            templateUrl: 'app/dc/nps/corporate_receipt_number/verify_corporate_receipt_number.html',
              data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_CORPORATE_RECEIPT_NUMBER:VERIFY'
            },
            controller: 'corporateReceiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/corporate_receipt_number/corporate-receipt-number.controller.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('corporate_receipt_number.list', {
            url: '/action/inquiry',
            templateUrl: 'app/dc/nps/corporate_receipt_number/corporate_receipt_number.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_CORPORATE_RECEIPT_NUMBER:ENQUIRY'
            },
            controller: 'corporateReceiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/corporate_receipt_number/corporate-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('corporate_receipt_number.list_detail', {
            url: '/action/inquiry/:corporateId/:serCode/:corporateBoNum',
            templateUrl: 'app/dc/nps/corporate_receipt_number/corporate_receipt_number_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_CORPORATE_RECEIPT_NUMBER:ENQUIRY'
            },
            controller: 'corporateReceiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/corporate_receipt_number/corporate-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

         .state('corporate_receipt_number.modify_details', {
            url: '/action/modify/:corporateId/:serCode/:corporateBoNum/:corporateRegNumber',
            templateUrl: 'app/dc/nps/corporate_receipt_number/modify_corporate_receipt_number_details.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_CORPORATE_RECEIPT_NUMBER:MODIFY'
            },
            controller: 'corporateReceiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/corporate_receipt_number/corporate-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('corporate_receipt_number.delete_details', {
            url: '/action/delete/:corporateId/:serCode/:corporateBoNum',
            templateUrl: 'app/dc/nps/corporate_receipt_number/delete_corporate_receipt_number_details.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_CORPORATE_RECEIPT_NUMBER:DELETE'
            },
            controller: 'corporateReceiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/corporate_receipt_number/corporate-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('corporate_receipt_number.verify_details', {
            url: '/action/verify/:corporateId/:serCode/:corporateBoNum',
            templateUrl: 'app/dc/nps/corporate_receipt_number/verify_corporate_receipt_number_details.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_CORPORATE_RECEIPT_NUMBER:VERIFY'
            },
            controller: 'corporateReceiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/corporate_receipt_number/corporate-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })


         /* Lite Receipt Number */
        .state('lite_receipt_number', {
            abstract: true,
            url: '/lite_receipt_number',
            params: {
                views: ['add', 'modify', 'verify', 'inquiry', 'delete'],
                permissions: ['GBM_DC_NPS:NPS_LITE_RECEIPT_NUMBER:ADD', 'GBM_DC_NPS:NPS_LITE_RECEIPT_NUMBER:MODIFY', 'GBM_DC_NPS:NPS_LITE_RECEIPT_NUMBER:VERIFY', 'GBM_DC_NPS:NPS_LITE_RECEIPT_NUMBER:ENQUIRY', 'GBM_DC_NPS:NPS_LITE_RECEIPT_NUMBER:DELETE']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/dc/common/left-menu.directive.js',
                            'app/dc/nps/lite_receipt_number/lite-receipt-number.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('lite_receipt_number.add', {
            url: '/add',
            templateUrl: 'app/dc/nps/lite_receipt_number/add_lite_receipt_number.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_LITE_RECEIPT_NUMBER:ADD'
            },
            controller: 'liteReceiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/lite_receipt_number/lite-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('lite_receipt_number.modify', {
            url: '/action/modify',
            templateUrl: 'app/dc/nps/lite_receipt_number/modify_lite_receipt_number.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_LITE_RECEIPT_NUMBER:MODIFY'
            },
            controller: 'liteReceiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/lite_receipt_number/lite-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('lite_receipt_number.delete', {
            url: '/action/delete',
            templateUrl: 'app/dc/nps/lite_receipt_number/delete_lite_receipt_number.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_LITE_RECEIPT_NUMBER:DELETE'
            },
            controller: 'liteReceiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/lite_receipt_number/lite-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('lite_receipt_number.verify', {
            url: '/action/verify',
            templateUrl: 'app/dc/nps/lite_receipt_number/verify_lite_receipt_number.html',
              data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_LITE_RECEIPT_NUMBER:VERIFY'
            },
            controller: 'liteReceiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/lite_receipt_number/lite-receipt-number.controller.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('lite_receipt_number.list', {
            url: '/action/inquiry',
            templateUrl: 'app/dc/nps/lite_receipt_number/lite_receipt_number.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_LITE_RECEIPT_NUMBER:ENQUIRY'
            },
            controller: 'liteReceiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/lite_receipt_number/lite-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('lite_receipt_number.list_detail', {
            url: '/action/inquiry/:solId/:serviceCode/:registrationNumber',
            templateUrl: 'app/dc/nps/lite_receipt_number/lite_receipt_number_list.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_LITE_RECEIPT_NUMBER:ENQUIRY'
            },
            controller: 'liteReceiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/lite_receipt_number/lite-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

         .state('lite_receipt_number.modify_detail', {
            url: '/action/modify/:solId/:serviceCode/:registrationNumber',
            templateUrl: 'app/dc/nps/lite_receipt_number/modify_lite_receipt_number_details.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_LITE_RECEIPT_NUMBER:MODIFY'
            },
            controller: 'liteReceiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/lite_receipt_number/lite-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('lite_receipt_number.delete_detail', {
            url: '/action/delete/:solId/:serviceCode/:registrationNumber',
            templateUrl: 'app/dc/nps/lite_receipt_number/delete_lite_receipt_number_details.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_LITE_RECEIPT_NUMBER:DELETE'
            },
            controller: 'liteReceiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/lite_receipt_number/lite-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('lite_receipt_number.verify_detail', {
            url: '/action/verify/:solId/:serviceCode/:registrationNumber',
            templateUrl: 'app/dc/nps/lite_receipt_number/verify_lite_receipt_number_details.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_LITE_RECEIPT_NUMBER:VERIFY'
            },
            controller: 'liteReceiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/lite_receipt_number/lite-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

                .state('lite_receipt_number.modify_form', {
            url: '/action/modify/:id',
            templateUrl: 'app/dc/nps/lite_receipt_number/modify_lite_receipt_number_form.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_LITE_RECEIPT_NUMBER:MODIFY'
            },
            controller: 'liteReceiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/lite_receipt_number/lite-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('lite_receipt_number.verify_form', {
            url: '/action/verify/:id',
            templateUrl: 'app/dc/nps/lite_receipt_number/verify_lite_receipt_number_form.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_LITE_RECEIPT_NUMBER:VERIFY'
            },
            controller: 'liteReceiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/lite_receipt_number/lite-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('lite_receipt_number.delete_form', {
            url: '/action/delete/:id',
            templateUrl: 'app/dc/nps/lite_receipt_number/delete_lite_receipt_number_form.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_LITE_RECEIPT_NUMBER:DELETE'
            },
            controller: 'liteReceiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/lite_receipt_number/lite-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('lite_receipt_number.form', {
            url: '/action/inquiry/:id',
            templateUrl: 'app/dc/nps/lite_receipt_number/lite_receipt_number_form.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_LITE_RECEIPT_NUMBER:ENQUIRY'
            },
            controller: 'liteReceiptNumberController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/lite_receipt_number/lite-receipt-number.controller.js?v='+versionNum]
                    });
                }
            }
        })



        /* commission share definition */
        .state('commission_share_definition', {
            abstract: true,
            url: '/commission_share_definition',
            params: {
                views: ['add', 'modify', 'inquiry', 'verify', 'delete'],
                permissions: ['GBM_DC_NPS:NPS_NPS_COMMISSION_SHARE_DEFINITION:ADD', 'GBM_DC_NPS:NPS_NPS_COMMISSION_SHARE_DEFINITION:MODIFY', 'GBM_DC_NPS:NPS_NPS_COMMISSION_SHARE_DEFINITION:ENQUIRY', 'GBM_DC_NPS:NPS_NPS_COMMISSION_SHARE_DEFINITION:VERIFY', 'GBM_DC_NPS:NPS_NPS_COMMISSION_SHARE_DEFINITION:DELETE']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/dc/common/left-menu.directive.js',
                            'app/dc/nps/nps_commission_share_definition/nps-commission-share-definition.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })

        .state('commission_share_definition.add', {
            url: '/add',
            templateUrl: 'app/dc/nps/nps_commission_share_definition/add_nps_commission_share_definition.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NPS_COMMISSION_SHARE_DEFINITION:ADD'
            },
            controller: 'npsCommissionShareDefinitionController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_commission_share_definition/nps-commission-share-definition.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('commission_share_definition.modify', {
            url: '/action/modify',
            templateUrl: 'app/dc/nps/nps_commission_share_definition/modify_nps_commission_share_definition.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NPS_COMMISSION_SHARE_DEFINITION:MODIFY'
            },
            controller: 'npsCommissionShareDefinitionController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_commission_share_definition/nps-commission-share-definition.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('commission_share_definition.modify_form', {
            url: '/action/modify/:id',
            templateUrl: 'app/dc/nps/nps_commission_share_definition/modify_nps_commission_share_definition_form.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NPS_COMMISSION_SHARE_DEFINITION:MODIFY'
            },
            controller: 'npsCommissionShareDefinitionController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_commission_share_definition/nps-commission-share-definition.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('commission_share_definition.delete', {
            url: '/action/delete',
            templateUrl: 'app/dc/nps/nps_commission_share_definition/delete_nps_commission_share_definition.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NPS_COMMISSION_SHARE_DEFINITION:DELETE'
            },
            controller: 'npsCommissionShareDefinitionController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_commission_share_definition/nps-commission-share-definition.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('commission_share_definition.delete_detail', {
            url: '/action/delete/:id',
            templateUrl: 'app/dc/nps/nps_commission_share_definition/delete_nps_commission_share_definition_detail.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NPS_COMMISSION_SHARE_DEFINITION:DELETE'
            },
            controller: 'npsCommissionShareDefinitionController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_commission_share_definition/nps-commission-share-definition.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('commission_share_definition.verify', {
            url: '/action/verify',
            templateUrl: 'app/dc/nps/nps_commission_share_definition/verify_nps_commission_share_definition.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NPS_COMMISSION_SHARE_DEFINITION:VERIFY'
            },
            controller: 'npsCommissionShareDefinitionController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_commission_share_definition/nps-commission-share-definition.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('commission_share_definition.verify_detail', {
            url: '/action/verify/:id',
            templateUrl: 'app/dc/nps/nps_commission_share_definition/verify_nps_commission_share_definition_detail.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NPS_COMMISSION_SHARE_DEFINITION:VERIFY'
            },
            controller: 'npsCommissionShareDefinitionController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_commission_share_definition/nps-commission-share-definition.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('commission_share_definition.list', {
            url: '/action/inquiry',
            templateUrl: 'app/dc/nps/nps_commission_share_definition/nps_commission_share_definition.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NPS_COMMISSION_SHARE_DEFINITION:ENQUIRY'
            },
            controller: 'npsCommissionShareDefinitionController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_commission_share_definition/nps-commission-share-definition.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('commission_share_definition.list_detail', {
            url: '/action/inquiry/:id',
            templateUrl: 'app/dc/nps/nps_commission_share_definition/nps_commission_share_definition_detail.html',
            data: {
                authRequired: true,
                requiredPermission: 'GBM_DC_NPS:NPS_NPS_COMMISSION_SHARE_DEFINITION:ENQUIRY'
            },
            controller: 'npsCommissionShareDefinitionController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/nps/nps_commission_share_definition/nps-commission-share-definition.controller.js?v='+versionNum]
                    });
                }
            }
        })


        /* General Scheme Parameter Maintenance */
        .state('general_scheme_parameter_maintenance', {
            abstract: true,
            url: '/general_scheme_parameter_maintenance',
            params: {
                views: ['modify', 'inquiry', 'verify'],
                permissions: ['DC:GENERAL_SCHEME_PARAMETER_MAINTENANCE:MODIFY', 'DC:GENERAL_SCHEME_PARAMETER_MAINTENANCE:ENQUIRY', 'DC:GENERAL_SCHEME_PARAMETER_MAINTENANCE:VERIFY']
            },
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: [
                            'app/dc/common/left-menu.directive.js',
                            'app/dc/general_scheme_parameter_maintenance/general-scheme-parameter-maintenance.service.js?v='+versionNum
                        ]
                    });
                }
            }
        })


        .state('general_scheme_parameter_maintenance.modify', {
            url: '/action/modify',
            templateUrl: 'app/dc/general_scheme_parameter_maintenance/modify_general_scheme_parameter_maintenance.html',
            data: {
                authRequired: true,
                requiredPermission: 'DC:GENERAL_SCHEME_PARAMETER_MAINTENANCE:MODIFY'
            },
            controller: 'generalSchemeParameterMaintenanceController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/general_scheme_parameter_maintenance/general-scheme-parameter-maintenance.controller.js?v='+versionNum]
                    });
                }
            }
        })


         .state('general_scheme_parameter_maintenance.modify_form', {
            url: '/action/modify/detail',
            templateUrl: 'app/dc/general_scheme_parameter_maintenance/modify_general_scheme_parameter_maintenance_form.html',
            data: {
                authRequired: true,
                requiredPermission: 'DC:GENERAL_SCHEME_PARAMETER_MAINTENANCE:MODIFY'
            },
            params: {obj: null},
            controller: 'generalSchemeParameterMaintenanceController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/general_scheme_parameter_maintenance/general-scheme-parameter-maintenance.controller.js?v='+versionNum]
                    });
                }
            }
        })


        .state('general_scheme_parameter_maintenance.list', {
            url: '/action/inquiry',
            templateUrl: 'app/dc/general_scheme_parameter_maintenance/general_scheme_parameter_maintenance.html',
            data: {
                authRequired: true,
                requiredPermission: 'DC:GENERAL_SCHEME_PARAMETER_MAINTENANCE:ENQUIRY'
            },
            controller: 'generalSchemeParameterMaintenanceController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/general_scheme_parameter_maintenance/general-scheme-parameter-maintenance.controller.js?v='+versionNum]
                    });
                }
            }
        })

        .state('general_scheme_parameter_maintenance.verify', {
            url: '/action/verify',
            templateUrl: 'app/dc/general_scheme_parameter_maintenance/verify_general_scheme_parameter_maintenance.html',
            data: {
                authRequired: true,
                requiredPermission: 'DC:GENERAL_SCHEME_PARAMETER_MAINTENANCE:VERIFY'
            },
            controller: 'generalSchemeParameterMaintenanceController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/general_scheme_parameter_maintenance/general-scheme-parameter-maintenance.controller.js?v='+versionNum]
                    });
                }
            }
        })

         .state('general_scheme_parameter_maintenance.verify_detail', {
            url: '/action/verify/detail',
            templateUrl: 'app/dc/general_scheme_parameter_maintenance/verify_general_scheme_parameter_maintenance_detail.html',
            data: {
                authRequired: true,
                requiredPermission: 'DC:GENERAL_SCHEME_PARAMETER_MAINTENANCE:VERIFY'
            },
            params: {obj: null},
            controller: 'generalSchemeParameterMaintenanceController',
            controllerAs: 'vm',
            resolve: {
                load: function($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        files: ['app/dc/general_scheme_parameter_maintenance/general-scheme-parameter-maintenance.controller.js?v='+versionNum]
                    });
                }
            }
        })



        }
})();