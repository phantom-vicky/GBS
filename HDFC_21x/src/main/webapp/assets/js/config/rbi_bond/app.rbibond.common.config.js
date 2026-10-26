(function() {
    'use strict';

    angular
        .module('gbmUltraApp')
        .config(RbiBondCommonConfig);

   RbiBondCommonConfig.$inject = ['$stateProvider', '$ocLazyLoadProvider'];

    function RbiBondCommonConfig($stateProvider, $ocLazyLoadProvider) {
        $stateProvider
     /* Formats */
        .state('gbm_rbi_formats', {
        				url: '/gbm_rbi_formats',
        				templateUrl: 'app/rbi_bond_module/rbi_formats.html',
        				resolve: {
                                                                                                    load: function($ocLazyLoad) {
                                                                                                        return $ocLazyLoad.load({
                                                                                                            files: [
                                                                                                           'assets/js/config/rbi_bond/app_rbi_formats_config.js?v='+versionNum
                                                                                                            ]
                                                                                                        });
                                                                                                    }
                                                                                                }
        			})
         /* rbi bond DC Menu */
        .state('dc_rbi_bond_menu', {
            url: '/dc_rbi_bond_menu',
            templateUrl: 'app/dc/rbi_bond/dc_rbi_bond_menu.html',
            resolve: {
             load: function($ocLazyLoad) {
              return $ocLazyLoad.load({
             files: ['assets/js/config/rbi_bond/app.rbibond.config.js?v='+versionNum]});
                                             }
                     }
        })

////         /* Admin Functionality Menu */
//        .state('e-freight_admin_functionality', {
//                         url: '/e-freight_admin_functionality',
//                         templateUrl: 'app/efreight_module/admin_functionality/efreight_admin_functionality.html',
//                         resolve: {
//                                     load: function($ocLazyLoad) {
//                                         return $ocLazyLoad.load({
//                                             files: ['assets/js/config/efreight/app.efreight.admin.config.js?v='+versionNum
//                                             ]
//                                         });
//                                     }
//                                 }
//                     })
//
////         /* GU Functionality Menu */
        .state('rbibond_gu_functionality', {
                            url: '/rbibond_gu_functionality',
                            templateUrl: 'app/rbi_bond_module/gu_functionallity/gu_functionality.html',
                            resolve: {
                                                load: function($ocLazyLoad) {
                                                    return $ocLazyLoad.load({
                                                        files: [
                                                        'assets/js/config/rbi_bond/app.rbibond.gufunctionality.config.js?v='+versionNum
                                                        ]
                                                    });
                                                }
                                            }
                        })

////         /* ADMIN Functionality Menu */
        .state('rbibond_admin_functionality', {
                            url: '/rbibond_admin_functionality',
                            templateUrl: 'app/rbi_bond_module/admin_functionallity/rbibond_admin_functionality.html',
                            resolve: {
                                                load: function($ocLazyLoad) {
                                                    return $ocLazyLoad.load({
                                                        files: [
                                                        'assets/js/config/rbi_bond/app.rbibond.admin.config.js?v='+versionNum
                                                        ]
                                                    });
                                                }
                                            }
                        })

    }
})();