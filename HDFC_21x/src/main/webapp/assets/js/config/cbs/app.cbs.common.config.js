(function() {
    'use strict';

    angular
        .module('gbmUltraApp')
        .config(CBSCommonConfig);

    CBSCommonConfig.$inject = ['$stateProvider', '$ocLazyLoadProvider'];

    function CBSCommonConfig($stateProvider, $ocLazyLoadProvider) {
        $stateProvider


         /* cbs master Menu */
        .state('cbs_master_updation', {
            url: '/cbs_master_updation',
            templateUrl: 'app/cbs/master_updation/cbs_master_updation.html',
            resolve: {
                                                                                        load: function($ocLazyLoad) {
                                                                                            return $ocLazyLoad.load({
                                                                                                files: [
                                                                                              'assets/js/config/cbs/app.cbs.config.js?v='+versionNum
                                                                                                ]
                                                                                            });
                                                                                        }
                                                                                    }
        })






    }

})();