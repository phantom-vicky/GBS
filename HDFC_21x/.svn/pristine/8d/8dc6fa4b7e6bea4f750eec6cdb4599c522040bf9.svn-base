(function() {
    'use strict';

    angular
        .module('gbmUltraApp')
        .config(RbiBondReportConfig);

    RbiBondReportConfig.$inject = ['$stateProvider', '$ocLazyLoadProvider'];

    function RbiBondReportConfig($stateProvider, $ocLazyLoadProvider) {
        $stateProvider




 .state('BLA_statement_for_Bondholders_report', {
      url: '/BLA_statement_for_Bondholders_report',
      templateUrl: 'app/rbi_bond_module/reports/BLA_statement_for_Bondholders_report.html',
        params: {
            views: ['inquiry'],
            permissions: ['GBM_RBIBOND_GU_FUNCTIONALITY:BLA_STATEMENT_FOR_BONDHOLDERS_REPORT:REPORT']
       },
       GBM_RBIBOND_GU_FUNCTIONALITY:BLA_STATEMENT_FOR_BONDHOLDERS_REPORT:REPORT

       controller: 'blastatementbondholderController',
       controllerAs: 'vm',
       resolve: {
           load: function($ocLazyLoad) {
               return $ocLazyLoad.load({
                   files: [
                        'app/rbi_bond_module/reports/BLA_statement_for_Bondholders_report.Controller.js?v='+versionNum,
                        'app/rbi_bond_module/reports/BLA_statement_for_Bondholders_report.service.js?v='+versionNum

                   ]
               });
           }
       }

})





      }


  })();