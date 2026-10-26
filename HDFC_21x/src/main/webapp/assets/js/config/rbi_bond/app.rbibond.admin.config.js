(function() {
    'use strict';

    angular
        .module('gbmUltraApp')
        .config(rbiBondAdminConfig);

    rbiBondAdminConfig.$inject = ['$stateProvider', '$ocLazyLoadProvider'];

    function rbiBondAdminConfig($stateProvider, $ocLazyLoadProvider) {

  $stateProvider
    
              
 .state('rbibond_link_cell_eod', {
                abstract: true,
                url: '/rbibond_link_cell_eod',
                params: {
                    views: ['inquiry'],
                    permissions: ['GBM_RBIBOND_ADMIN_FUNCTIONALITY:RBIBOND_LINK_CELL_EOD:ENQUIRY']
                },
                resolve: {
                    load: function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: [
                                'app/dc/common/left-menu.directive.js',
                                'app/rbi_bond_module/admin_functionallity/system_operations/rbibond_eod/rbibond_eod.service.js?v='+versionNum
                            ]
                        });
                    }
                }
            })

            .state('rbibond_link_cell_eod.inquiry', {
                url: '/action/inquiry',
                templateUrl: 'app/rbi_bond_module/admin_functionallity/system_operations/rbibond_eod/rbibond_eod_inquiry.html',
                data: {
                    authRequired: true,
                    requiredPermission: 'GBM_RBIBOND_ADMIN_FUNCTIONALITY:RBIBOND_LINK_CELL_EOD:ENQUIRY'
                },
                controller: 'rbiBondEodController',
                controllerAs: 'vm',
                resolve: {
                    load: function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['app/rbi_bond_module/admin_functionallity/system_operations/rbibond_eod/rbibond_eod.controller.js?v='+versionNum]
                        });
                    }
                }
            })

            .state('rbibond_link_cell_eod.inquiry_detail', {
                url: '/action/inquiry/detail',
                templateUrl: 'app/rbi_bond_module/admin_functionallity/system_operations/rbibond_eod/rbibond_eod_inquiry_detail.html',
                data: {
                    authRequired: true,
                    requiredPermission: 'GBM_RBIBOND_ADMIN_FUNCTIONALITY:RBIBOND_LINK_CELL_EOD:ENQUIRY'
                },
                params : {obj : null},
                controller: 'rbiBondEodController',
                controllerAs: 'vm',
                resolve: {
                    load: function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['app/rbi_bond_module/admin_functionallity/system_operations/rbibond_eod/rbibond_eod.controller.js?v='+versionNum]
                        });
                    }
                }
            })

            .state('rbibond_cb_eod', {
                            abstract: true,
                            url: '/rbibond_cb_eod',
                            params: {
                                views: ['inquiry'],
                                permissions: ['GBM_RBIBOND_ADMIN_FUNCTIONALITY:RBIBOND_CB_EOD:ENQUIRY']
                            },
                            resolve: {
                                load: function($ocLazyLoad) {
                                    return $ocLazyLoad.load({
                                        files: [
                                            'app/dc/common/left-menu.directive.js',
                                            'app/rbi_bond_module/admin_functionallity/system_operations/rbibond_eod/rbibond_eod.service.js?v='+versionNum
                                        ]
                                    });
                                }
                            }
                        })

                        .state('rbibond_cb_eod.inquiry', {
                            url: '/action/inquiry',
                            templateUrl: 'app/rbi_bond_module/admin_functionallity/system_operations/rbibond_eod/rbibond_eod_inquiry.html',
                            data: {
                                authRequired: true,
                                requiredPermission: 'GBM_RBIBOND_ADMIN_FUNCTIONALITY:RBIBOND_CB_EOD:ENQUIRY'
                            },
                            controller: 'rbiBondEodController',
                            controllerAs: 'vm',
                            resolve: {
                                load: function($ocLazyLoad) {
                                    return $ocLazyLoad.load({
                                        files: ['app/rbi_bond_module/admin_functionallity/system_operations/rbibond_eod/rbibond_eod.controller.js?v='+versionNum]
                                    });
                                }
                            }
                        })

                        .state('rbibond_cb_eod.inquiry_detail', {
                            url: '/action/inquiry/detail',
                            templateUrl: 'app/rbi_bond_module/admin_functionallity/system_operations/rbibond_eod/rbibond_eod_inquiry_detail.html',
                            data: {
                                authRequired: true,
                                requiredPermission: 'GBM_RBIBOND_ADMIN_FUNCTIONALITY:RBIBOND_CB_EOD:ENQUIRY'
                            },
                            params : {obj : null},
                            controller: 'rbiBondEodController',
                            controllerAs: 'vm',
                            resolve: {
                                load: function($ocLazyLoad) {
                                    return $ocLazyLoad.load({
                                        files: ['app/rbi_bond_module/admin_functionallity/system_operations/rbibond_eod/rbibond_eod.controller.js?v='+versionNum]
                                    });
                                }
                            }
                        })

.state('rbibond_lc_bod', {
         				abstract: true,
         				url: '/rbibond_lc_bod',
         				params: {
         					views: ['inquiry'],
         					permissions: ['GBM_RBIBOND_ADMIN_FUNCTIONALITY:RBIBOND_LINK_CELL_BOD:ENQUIRY']
         				},
         				resolve: {
         					load: function ($ocLazyLoad) {
         						return $ocLazyLoad.load({
         							files: [
         								'app/rbi_bond_module/admin_functionallity/system_operations/rbibond_bod/rbibond_bod.service.js?v='+versionNum
         							]
         						});
         					}
         				}
         			})
         			.state('rbibond_lc_bod.inquiry', {
         				url: '/action/inquiry',
         				templateUrl: 'app/rbi_bond_module/admin_functionallity/system_operations/rbibond_bod/rbibond_bod_input.html',
         				data: {
         					authRequired: true,
         					requiredPermission: 'GBM_RBIBOND_ADMIN_FUNCTIONALITY:RBIBOND_LINK_CELL_BOD:ENQUIRY'
         				},
         				controller: 'rbibondBodController',
         				controllerAs: 'vm',
         				resolve: {
         					load: function ($ocLazyLoad) {
         						return $ocLazyLoad.load({
         							files: ['app/rbi_bond_module/admin_functionallity/system_operations/rbibond_bod/rbibond_bod.controller.js?v='+versionNum]
         						});
         					}
         				}
         			})
         			.state('rbibond_lc_bod.inquiry_details', {
         				url: '/action/inquiry_details',
         				templateUrl: 'app/rbi_bond_module/admin_functionallity/system_operations/rbibond_bod/rbibond_bod_detail.html',
         				data: {
         					authRequired: true,
         					requiredPermission: 'GBM_RBIBOND_ADMIN_FUNCTIONALITY:RBIBOND_LINK_CELL_BOD:ENQUIRY'
         				},
         				params: {
         					obj: null
         				},
         				controller: 'rbibondBodController',
         				controllerAs: 'vm',
         				resolve: {
         					load: function ($ocLazyLoad) {
         						return $ocLazyLoad.load({
         							files: ['app/rbi_bond_module/admin_functionallity/system_operations/rbibond_bod/rbibond_bod.controller.js?v='+versionNum]
         						});
         					}
         				}
         			})



		.state('rbibond_cb_bod', {
         				abstract: true,
         				url: '/rbibond_cb_bod',
         				params: {
         					views: ['inquiry'],
         					permissions: ['GBM_RBIBOND_ADMIN_FUNCTIONALITY:RBIBOND_CB_BOD:ENQUIRY']
         				},
         				resolve: {
         					load: function ($ocLazyLoad) {
         						return $ocLazyLoad.load({
         							files: [
         								'app/rbi_bond_module/admin_functionallity/system_operations/rbibond_bod/rbibond_bod.service.js?v='+versionNum
         							]
         						});
         					}
         				}
         			})
         			.state('rbibond_cb_bod.inquiry', {
         				url: '/action/inquiry',
         				templateUrl: 'app/rbi_bond_module/admin_functionallity/system_operations/rbibond_bod/rbibond_bod_input.html',
         				data: {
         					authRequired: true,
         					requiredPermission: 'GBM_RBIBOND_ADMIN_FUNCTIONALITY:RBIBOND_CB_BOD:ENQUIRY'
         				},
         				controller: 'rbibondBodController',
         				controllerAs: 'vm',
         				resolve: {
         					load: function ($ocLazyLoad) {
         						return $ocLazyLoad.load({
         							files: ['app/rbi_bond_module/admin_functionallity/system_operations/rbibond_bod/rbibond_bod.controller.js?v='+versionNum]
         						});
         					}
         				}
         			})
         			.state('rbibond_cb_bod.inquiry_details', {
         				url: '/action/inquiry_details',
         				templateUrl: 'app/rbi_bond_module/admin_functionallity/system_operations/rbibond_bod/rbibond_bod_detail.html',
         				data: {
         					authRequired: true,
         					requiredPermission: 'GBM_RBIBOND_ADMIN_FUNCTIONALITY:RBIBOND_CB_BOD:ENQUIRY'
         				},
         				params: {
         					obj: null
         				},
         				controller: 'rbibondBodController',
         				controllerAs: 'vm',
         				resolve: {
         					load: function ($ocLazyLoad) {
         						return $ocLazyLoad.load({
         							files: ['app/rbi_bond_module/admin_functionallity/system_operations/rbibond_bod/rbibond_bod.controller.js?v='+versionNum]
         						});
         					}
         				}
         			})


    }
})();