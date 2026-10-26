(function() {
    'use strict';

    angular.module('gbmUltraApp').config(RBIBondGuFunctionalityCancellationCATCBTConfig);

    RBIBondGuFunctionalityCancellationCATCBTConfig.$inject = ['$stateProvider', '$ocLazyLoadProvider'];

    function RBIBondGuFunctionalityCancellationCATCBTConfig($stateProvider, $ocLazyLoadProvider) {
        $stateProvider

            .state('cancellation_subscription_cat_cbt', {
                abstract: true,
                url: '/cancellation_subscription_cat_cbt',
                params: {
                    views: ['add', 'verify', 'inquiry'],
                    permissions: ['GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:ADD', 'GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:VERIFY', 'GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:ENQUIRY']
                },
                resolve: {
                    load: function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: [
                                'app/dc/common/left-menu.directive.js',
                                'app/rbi_bond_module/gu_functionallity/masters/cancellation_subscription_cat_cbt/cancellation_subscription_cat_cbt.service.js?v=' + versionNum
                            ]
                        });
                    }
                }
            })


            .state('cancellation_subscription_cat_cbt.add', {
                url: '/add',
                templateUrl: 'app/rbi_bond_module/gu_functionallity/masters/cancellation_subscription_cat_cbt/add_cancellation_subscription_cat_cbt.html',
                data: {
                    authRequired: true,
                    requiredPermission: 'GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:ADD'
                },
                params: {
                    obj: null
                },
                controller: 'cancellationSubscriptionCatCbtController',
                controllerAs: 'vm',
                resolve: {
                    load: function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['app/rbi_bond_module/gu_functionallity/masters/cancellation_subscription_cat_cbt/cancellation_subscription_cat_cbt.controller.js?v=' + versionNum]
                        });
                    }
                }
            })

            .state('cancellation_subscription_cat_cbt.add_detail', {
                url: '/add/detail',
                templateUrl: 'app/rbi_bond_module/gu_functionallity/masters/cancellation_subscription_cat_cbt/add_cancellation_subscription_cat_cbt_detail.html',
                data: {
                    authRequired: true,
                    requiredPermission: 'GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:ADD'
                },
                params: {
                    obj: null
                },
                controller: 'cancellationSubscriptionCatCbtController',
                controllerAs: 'vm',
                resolve: {
                    load: function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['app/rbi_bond_module/gu_functionallity/masters/cancellation_subscription_cat_cbt/cancellation_subscription_cat_cbt.controller.js?v=' + versionNum]
                        });
                    }
                }
            })

            .state('cancellation_subscription_cat_cbt.verify', {
                url: '/action/verify',
                templateUrl: 'app/rbi_bond_module/gu_functionallity/masters/cancellation_subscription_cat_cbt/verify_reject_cancellation_subscription_cat_cbt.html',
                data: {
                    authRequired: true,
                    requiredPermission: 'GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:VERIFY'
                },
                controller: 'cancellationSubscriptionCatCbtController',
                controllerAs: 'vm',
                resolve: {
                    load: function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['app/rbi_bond_module/gu_functionallity/masters/cancellation_subscription_cat_cbt/cancellation_subscription_cat_cbt.controller.js?v=' + versionNum]
                        });
                    }
                }
            })

            .state('cancellation_subscription_cat_cbt.verify_detail', {
                url: '/action/verify/detail',
                templateUrl: 'app/rbi_bond_module/gu_functionallity/masters/cancellation_subscription_cat_cbt/verify_reject_cancellation_subscription_cat_cbt_detail.html',
                data: {
                    authRequired: true,
                    requiredPermission: 'GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:VERIFY'
                },
                params: {
                    obj: null
                },
                controller: 'cancellationSubscriptionCatCbtController',
                controllerAs: 'vm',
                resolve: {
                    load: function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['app/rbi_bond_module/gu_functionallity/masters/cancellation_subscription_cat_cbt/cancellation_subscription_cat_cbt.controller.js?v=' + versionNum]
                        });
                    }
                }
            })

            .state('cancellation_subscription_cat_cbt.inquiry', {
                url: '/action/inquiry',
                templateUrl: 'app/rbi_bond_module/gu_functionallity/masters/cancellation_subscription_cat_cbt/inquiry_cancellation_subscription_cat_cbt.html',
                data: {
                    authRequired: true,
                    requiredPermission: 'GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:ENQUIRY'
                },
                controller: 'cancellationSubscriptionCatCbtController',
                controllerAs: 'vm',
                resolve: {
                    load: function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['app/rbi_bond_module/gu_functionallity/masters/cancellation_subscription_cat_cbt/cancellation_subscription_cat_cbt.controller.js?v=' + versionNum]
                        });
                    }
                }
            })

            .state('cancellation_subscription_cat_cbt.inquiry_detail', {
                url: '/action/inquiry/detail',
                templateUrl: 'app/rbi_bond_module/gu_functionallity/masters/cancellation_subscription_cat_cbt/inquiry_cancellation_subscription_cat_cbt_detail.html',
                data: {
                    authRequired: true,
                    requiredPermission: 'GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:ENQUIRY'
                },
                params: {
                    obj: null
                },
                controller: 'cancellationSubscriptionCatCbtController',
                controllerAs: 'vm',
                resolve: {
                    load: function($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            files: ['app/rbi_bond_module/gu_functionallity/masters/cancellation_subscription_cat_cbt/cancellation_subscription_cat_cbt.controller.js?v=' + versionNum]
                        });
                    }
                }
            })
    }

})();