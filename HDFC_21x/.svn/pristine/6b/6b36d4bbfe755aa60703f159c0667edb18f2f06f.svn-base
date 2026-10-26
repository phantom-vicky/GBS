(function () {
  "use strict";

  angular.module("gbmUltraApp").config(RbiBondConfig);

  RbiBondConfig.$inject = ["$stateProvider", "$ocLazyLoadProvider"];

  function RbiBondConfig($stateProvider, $ocLazyLoadProvider) {
    $stateProvider

      /* rbi bond scheme parameter */
      .state("rbibond_scheme_parameter", {
        abstract: true,
        url: "/rbibond_scheme_parameter",
        params: {
          views: ["add", "modify", "verify", "inquiry"],
          permissions: ["GBM_DC_RBI:GBM_rbi_bond_scheme_parameter:ADD", "GBM_DC_RBI:GBM_rbi_bond_scheme_parameter:MODIFY", "GBM_DC_RBI:GBM_rbi_bond_scheme_parameter:VERIFY", "GBM_DC_RBI:GBM_rbi_bond_scheme_parameter:ENQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/dc/rbi_bond/rbibond_scheme_parameter/rbibond-scheme-parameter.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_scheme_parameter.add", {
        url: "/add",
        templateUrl: "app/dc/rbi_bond/rbibond_scheme_parameter/add_rbibond_scheme_parameter.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBI:GBM_rbi_bond_scheme_parameter:ADD",
        },
        controller: "rbiBondSchemeParameterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_scheme_parameter/rbibond-scheme-parameter.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_scheme_parameter.modify", {
        url: "/action/modify",
        templateUrl: "app/dc/rbi_bond/rbibond_scheme_parameter/modify_rbibond_scheme_parameter.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBI:GBM_rbi_bond_scheme_parameter:MODIFY",
        },
        controller: "rbiBondSchemeParameterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_scheme_parameter/rbibond-scheme-parameter.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_scheme_parameter.modify_details", {
        url: "/action/modify/details",
        templateUrl: "app/dc/rbi_bond/rbibond_scheme_parameter/modify_rbibond_scheme_parameter_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBI:GBM_rbi_bond_scheme_parameter:MODIFY",
        },
        params: {
          obj: null,
        },
        controller: "rbiBondSchemeParameterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_scheme_parameter/rbibond-scheme-parameter.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_scheme_parameter.verify", {
        url: "/action/verify",
        templateUrl: "app/dc/rbi_bond/rbibond_scheme_parameter/verify_rbibond_scheme_parameter_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBI:GBM_rbi_bond_scheme_parameter:VERIFY",
        },
        controller: "rbiBondSchemeParameterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_scheme_parameter/rbibond-scheme-parameter.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_scheme_parameter.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/dc/rbi_bond/rbibond_scheme_parameter/inquiry_rbibond_scheme_parameter.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBI:GBM_rbi_bond_scheme_parameter:ENQUIRY",
        },
        controller: "rbiBondSchemeParameterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_scheme_parameter/rbibond-scheme-parameter.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_scheme_parameter.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/dc/rbi_bond/rbibond_scheme_parameter/inquiry_rbibond_scheme_parameter_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBI:GBM_rbi_bond_scheme_parameter:ENQUIRY",
        },
        params: {
          obj: null,
        },
        controller: "rbiBondSchemeParameterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_scheme_parameter/rbibond-scheme-parameter.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_scheme_parameter.verify_detail", {
        url: "/verify_detail",
        templateUrl: "app/dc/rbi_bond/rbibond_scheme_parameter/verify_rbibond_scheme_parameter_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBI:GBM_rbi_bond_scheme_parameter:VERIFY",
        },
        params: {
          obj: null,
        },
        controller: "rbiBondSchemeParameterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_scheme_parameter/rbibond-scheme-parameter.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_scheme_parameter.delete_detail", {
        url: "/action/delete/detail",
        templateUrl: "app/dc/rbi_bond/rbibond_scheme_parameter/delete_rbibond_scheme_parameter_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBI:GBM_rbi_bond_scheme_parameter:DELETE",
        },
        params: {
          obj: null,
        },
        controller: "rbiBondSchemeParameterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_scheme_parameter/rbibond-scheme-parameter.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /* EOD BOD Status Inquiry dc */

      .state("rbibond_bod_eod_status_inquiry_dc", {
        abstract: true,
        url: "/rbibond_bod_eod_status_inquiry_dc",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBI:BOD_EOD_STATUS_INQUIRY_DC:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_bod_eod_status_inquiry/rbibond_bod_eod_status_inquiry.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_bod_eod_status_inquiry_dc.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/dc/rbi_bond/rbibond_bod_eod_status_inquiry/rbibond_bod_eod_status_inquiry.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBI:BOD_EOD_STATUS_INQUIRY_DC:REPORT",
        },
        controller: "rbiBondBodEodStatusController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_bod_eod_status_inquiry/rbibond_bod_eod_status_inquiry.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_bod_eod_status_inquiry_dc.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/dc/rbi_bond/rbibond_bod_eod_status_inquiry/rbibond_bod_eod_status_inquiry_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBI:BOD_EOD_STATUS_INQUIRY_DC:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "rbiBondBodEodStatusController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_bod_eod_status_inquiry/rbibond_bod_eod_status_inquiry.controller.js?v=" + versionNum],
            });
          },
        },
      })
/* Accounting Entries Starts*/

      .state("accounting_entries", {
        abstract: true,
        url: "/accounting_entries",
        params: {
          views: ["add", "modify", "verify", "inquiry"],
          permissions: ["GBM_GU_RBIBOND:RBIBOND_ACCOUNTING_ENTRIES:ADD", "GBM_GU_RBIBOND:RBIBOND_ACCOUNTING_ENTRIES:MODIFY", "GBM_GU_RBIBOND:RBIBOND_ACCOUNTING_ENTRIES:VERIFY", "GBM_GU_RBIBOND:RBIBOND_ACCOUNTING_ENTRIES:INQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/accounting_entries/accounting_entries.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("accounting_entries.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/accounting_entries/add_accounting_entries.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_ACCOUNTING_ENTRIES:ADD",
        },
        controller: "accountingEntriesController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/accounting_entries/accounting_entries.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("accounting_entries.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/accounting_entries/add_accounting_entries_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_ACCOUNTING_ENTRIES:ADD",
        },
        params: { obj: null },
        controller: "accountingEntriesController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/accounting_entries/accounting_entries.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("accounting_entries.modify", {
        url: "/action/modify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/accounting_entries/modify_accounting_entries.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_ACCOUNTING_ENTRIES:MODIFY",
        },
        controller: "accountingEntriesController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/accounting_entries/accounting_entries.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("accounting_entries.modify_detail", {
        url: "/action/modify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/accounting_entries/modify_accounting_entries_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_ACCOUNTING_ENTRIES:MODIFY",
        },
        params: { obj: null },
        controller: "accountingEntriesController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/accounting_entries/accounting_entries.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("accounting_entries.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/accounting_entries/verify_accounting_entries.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_ACCOUNTING_ENTRIES:VERIFY",
        },
        controller: "accountingEntriesController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/accounting_entries/accounting_entries.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("accounting_entries.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/accounting_entries/verify_accounting_entries_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_ACCOUNTING_ENTRIES:VERIFY",
        },
        params: { obj: null },
        controller: "accountingEntriesController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/accounting_entries/accounting_entries.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("accounting_entries.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/accounting_entries/inquiry_accounting_entries.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_ACCOUNTING_ENTRIES:INQUIRY",
        },
        controller: "accountingEntriesController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/accounting_entries/accounting_entries.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("accounting_entries.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/accounting_entries/inquiry_accounting_entries_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_ACCOUNTING_ENTRIES:INQUIRY",
        },
        params: { obj: null },
        controller: "accountingEntriesController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/accounting_entries/accounting_entries.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /* Accounting Entries Ends*/
      /* Level Master Start*/
      .state("rbi_bond_level_master", {
        abstract: true,
        url: "/rbi_bond_level_master",
        params: {
          views: ["add", "verify", "inquiry"],
          permissions: ["GBM_DC_RBI:GBM_RBI_BOND_LEVEL_MASTER:ADD", "GBM_DC_RBI:GBM_RBI_BOND_LEVEL_MASTER:VERIFY", "GBM_DC_RBI:GBM_RBI_BOND_LEVEL_MASTER:ENQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/dc/rbi_bond/rbi_bond_level_master/level_master.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bond_level_master.add", {
        url: "/add",
        templateUrl: "app/dc/rbi_bond/rbi_bond_level_master/add_level_master.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBI:GBM_RBI_BOND_LEVEL_MASTER:ADD",
        },
        controller: "rbiBondLevelMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_level_master/level_master.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bond_level_master.verify", {
        url: "/action/verify",
        templateUrl: "app/dc/rbi_bond/rbi_bond_level_master/verify_level_master.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBI:GBM_RBI_BOND_LEVEL_MASTER:VERIFY",
        },
        controller: "rbiBondLevelMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_level_master/level_master.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bond_level_master.delete", {
        url: "/action/delete",
        templateUrl: "app/dc/rbi_bond/rbi_bond_level_master/delete_level_master.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBI:GBM_RBI_BOND_LEVEL_MASTER:VERIFY",
        },
        controller: "rbiBondLevelMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_level_master/level_master.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bond_level_master.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/dc/rbi_bond/rbi_bond_level_master/inquiry_level_master.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBI:GBM_RBI_BOND_LEVEL_MASTER:ENQUIRY",
        },
        controller: "rbiBondLevelMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_level_master/level_master.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /*efreight  Level Master Mapping Start*/
      .state("rbibond_level_master_mapping", {
        abstract: true,
        url: "/rbibond_level_master_mapping",
        params: {
          views: ["add", "modify", "verify", "inquiry"],
          permissions: ["GBM_DC_RBI:GBM_rbi_bond_level_master_mapping:ADD", "GBM_DC_RBI:GBM_rbi_bond_level_master_mapping:MODIFY", "GBM_DC_RBI:GBM_rbi_bond_level_master_mapping:VERIFY", "GBM_DC_RBI:GBM_rbi_bond_level_master_mapping:ENQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/dc/rbi_bond/rbibond_level_master_mapping/level-master-mapping.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_level_master_mapping.add", {
        url: "/add",
        templateUrl: "app/dc/rbi_bond/rbibond_level_master_mapping/add_level_master_mapping.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBI:GBM_rbi_bond_level_master_mapping:ADD",
        },
        controller: "rbiBondLevelMasterMappingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_level_master_mapping/level-master-mapping.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_level_master_mapping.modify", {
        url: "/action/modify",
        templateUrl: "app/dc/rbi_bond/rbibond_level_master_mapping/modify_level_master_mapping.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBI:GBM_rbi_bond_level_master_mapping:MODIFY",
        },
        controller: "rbiBondLevelMasterMappingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_level_master_mapping/level-master-mapping.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_level_master_mapping.verify", {
        url: "/action/verify",
        templateUrl: "app/dc/rbi_bond/rbibond_level_master_mapping/verify_level_master_mapping.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBI:GBM_rbi_bond_level_master_mapping:VERIFY",
        },
        controller: "rbiBondLevelMasterMappingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_level_master_mapping/level-master-mapping.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_level_master_mapping.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/dc/rbi_bond/rbibond_level_master_mapping/level_master_mapping.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBI:GBM_rbi_bond_level_master_mapping:ENQUIRY",
        },
        controller: "rbiBondLevelMasterMappingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_level_master_mapping/level-master-mapping.controller.js?v=" + versionNum],
            });
          },
        },
      })
      /* efreight Level Master Mapping End*/

      /*efreight centralized bod eod starts*/
      .state("rbibond_centralized_bod", {
        abstract: true,
        url: "/rbibond_centralized_bod",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_BOD:ENQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/dc/rbi_bond/rbibond_centralized_bod/centralized_bod.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_centralized_bod.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/dc/rbi_bond/rbibond_centralized_bod/centralized_bod_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_BOD:ENQUIRY",
        },
        controller: "efreightCentralizedBodController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_centralized_bod/centralized_bod.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_centralized_bod.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/dc/rbi_bond/rbibond_centralized_bod/centralized_bod_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_BOD:ENQUIRY",
        },
        params: { obj: null },
        controller: "efreightCentralizedBodController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_centralized_bod/centralized_bod.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_centralized_eod", {
        abstract: true,
        url: "/rbibond_centralized_eod",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_EOD:ENQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/dc/rbi_bond/rbibond_centralized_eod/centralized_eod.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_centralized_eod.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/dc/rbi_bond/rbibond_centralized_eod/centralized_eod_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_EOD:ENQUIRY",
        },
        controller: "rbiBondCentralizedEodController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_centralized_eod/centralized_eod.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_centralized_eod.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/dc/rbi_bond/rbibond_centralized_eod/centralized_eod_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_EOD:ENQUIRY",
        },
        params: { obj: null },
        controller: "rbiBondCentralizedEodController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_centralized_eod/centralized_eod.controller.js?v=" + versionNum],
            });
          },
        },
      })
      /* efreight centralized bod eod ends*/

      /* rbi_bond_interest_rate_code_maintenance  Start*/
      .state("rbi_bond_interest_rate_code_maintenance", {
        abstract: true,
        url: "/rbi_bond_interest_rate_code_maintenance",
        params: {
          views: ["add", "verify", "delete", "modify", "inquiry"],
          permissions: [
            "GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:ADD",
            "GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:VERIFY",
            "GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:DELETE",
            "GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:MODIFY",
            "GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:ENQUIRY",
          ],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/dc/rbi_bond/rbi_bond_interest_rate_code_maintenance/rbi_bond_interest_rate_code_maintenance.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bond_interest_rate_code_maintenance.add", {
        url: "/add",
        templateUrl: "app/dc/rbi_bond/rbi_bond_interest_rate_code_maintenance/add_rbi_bond_interest_rate_code_maintenance.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:ADD",
        },
        controller: "rbiBondInterestRateCodeMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_interest_rate_code_maintenance/rbi_bond_interest_rate_code_maintenance.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bond_interest_rate_code_maintenance.verify", {
        url: "/action/verify",
        templateUrl: "app/dc/rbi_bond/rbi_bond_interest_rate_code_maintenance/verify_rbi_bond_interest_rate_code_maintenance.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:VERIFY",
        },
        controller: "rbiBondInterestRateCodeMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_interest_rate_code_maintenance/rbi_bond_interest_rate_code_maintenance.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bond_interest_rate_code_maintenance.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/dc/rbi_bond/rbi_bond_interest_rate_code_maintenance/verify_rbi_bond_interest_rate_code_maintenance_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:VERIFY",
        },
        params: {
          obj: null,
        },
        controller: "rbiBondInterestRateCodeMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_interest_rate_code_maintenance/rbi_bond_interest_rate_code_maintenance.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bond_interest_rate_code_maintenance.delete_detail", {
        url: "/action/delete/detail",
        templateUrl: "app/dc/rbi_bond/rbi_bond_interest_rate_code_maintenance/delete_rbi_bond_interest_rate_code_maintenance_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:DELETE",
        },
        params: {
          obj: null,
        },
        controller: "rbiBondInterestRateCodeMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_interest_rate_code_maintenance/rbi_bond_interest_rate_code_maintenance.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bond_interest_rate_code_maintenance.modify", {
        url: "/action/modify",
        templateUrl: "app/dc/rbi_bond/rbi_bond_interest_rate_code_maintenance/modify_rbi_bond_interest_rate_code_maintenance.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:MODIFY",
        },

        controller: "rbiBondInterestRateCodeMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_interest_rate_code_maintenance/rbi_bond_interest_rate_code_maintenance.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bond_interest_rate_code_maintenance.modify_detail", {
        url: "/action/modify/detail",
        templateUrl: "app/dc/rbi_bond/rbi_bond_interest_rate_code_maintenance/modify_rbi_bond_interest_rate_code_maintenance_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:MODIFY",
        },
        params: {
          obj: null,
        },
        controller: "rbiBondInterestRateCodeMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_interest_rate_code_maintenance/rbi_bond_interest_rate_code_maintenance.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bond_interest_rate_code_maintenance.delete", {
        url: "/action/delete",
        templateUrl: "app/dc/rbi_bond/rbi_bond_interest_rate_code_maintenance/delete_rbi_bond_interest_rate_code_maintenance.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:DELETE",
        },
        controller: "rbiBondInterestRateCodeMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_interest_rate_code_maintenance/rbi_bond_interest_rate_code_maintenance.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bond_interest_rate_code_maintenance.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/dc/rbi_bond/rbi_bond_interest_rate_code_maintenance/inquiry_rbi_bond_interest_rate_code_maintenance.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:ENQUIRY",
        },
        controller: "rbiBondInterestRateCodeMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_interest_rate_code_maintenance/rbi_bond_interest_rate_code_maintenance.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bond_interest_rate_code_maintenance.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/dc/rbi_bond/rbi_bond_interest_rate_code_maintenance/inquiry_rbi_bond_interest_rate_code_maintenance_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:ENQUIRY",
        },
        params: {
          obj: null,
        },

        controller: "rbiBondInterestRateCodeMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_interest_rate_code_maintenance/rbi_bond_interest_rate_code_maintenance.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /* rbi bond age based lock in period */
      .state("rbibond_age_based", {
        abstract: true,
        url: "/rbibond_age_based",
        params: {
          views: ["add", "modify", "verify", "delete", "inquiry"],
          permissions: [
            "GBM_DC_RBIBOND:RBIBOND_AGE_BASED_LOCK_IN_PERIOD:ADD",
            "GBM_DC_RBIBOND:RBIBOND_AGE_BASED_LOCK_IN_PERIOD:MODIFY",
            "GBM_DC_RBIBOND:RBIBOND_AGE_BASED_LOCK_IN_PERIOD:VERIFY",
            "GBM_DC_RBIBOND:RBIBOND_AGE_BASED_LOCK_IN_PERIOD:DELETE",
            "GBM_DC_RBIBOND:RBIBOND_AGE_BASED_LOCK_IN_PERIOD:ENQUIRY",
          ],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/dc/rbi_bond/rbibond_age_based_lc_in_prd_pre_enhmt/rbibond-age_based.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_age_based.add", {
        url: "/add",
        templateUrl: "app/dc/rbi_bond/rbibond_age_based_lc_in_prd_pre_enhmt/add_rbibond_age_based_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_AGE_BASED_LOCK_IN_PERIOD:ADD",
        },
        controller: "rbiBondAgeBasedController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_age_based_lc_in_prd_pre_enhmt/rbibond-age_based.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_age_based.add_detail", {
        url: "/action/add_detail",
        templateUrl: "app/dc/rbi_bond/rbibond_age_based_lc_in_prd_pre_enhmt/add_rbibond_age_based_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_AGE_BASED_LOCK_IN_PERIOD:ADD",
        },
        params: {
          obj: null,
        },
        controller: "rbiBondAgeBasedController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_age_based_lc_in_prd_pre_enhmt/rbibond-age_based.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_age_based.modify", {
        url: "/action/modify",
        templateUrl: "app/dc/rbi_bond/rbibond_age_based_lc_in_prd_pre_enhmt/modify_rbibond_age_based.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_AGE_BASED_LOCK_IN_PERIOD:MODIFY",
        },
        controller: "rbiBondAgeBasedController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_age_based_lc_in_prd_pre_enhmt/rbibond-age_based.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_age_based.modify_details", {
        url: "/action/modify/details",
        templateUrl: "app/dc/rbi_bond/rbibond_age_based_lc_in_prd_pre_enhmt/modify_rbibond_age_based_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_AGE_BASED_LOCK_IN_PERIOD:MODIFY",
        },
        params: {
          obj: null,
        },
        controller: "rbiBondAgeBasedController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_age_based_lc_in_prd_pre_enhmt/rbibond-age_based.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_age_based.verify", {
        url: "/action/verify",
        templateUrl: "app/dc/rbi_bond/rbibond_age_based_lc_in_prd_pre_enhmt/verify_rbibond_age_based_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_AGE_BASED_LOCK_IN_PERIOD:VERIFY",
        },
        controller: "rbiBondAgeBasedController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_age_based_lc_in_prd_pre_enhmt/rbibond-age_based.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_age_based.delete", {
        url: "/action/delete",
        templateUrl: "app/dc/rbi_bond/rbibond_age_based_lc_in_prd_pre_enhmt/delete_rbibond_age_based_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_AGE_BASED_LOCK_IN_PERIOD:DELETE",
        },
        controller: "rbiBondAgeBasedController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_age_based_lc_in_prd_pre_enhmt/rbibond-age_based.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_age_based.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/dc/rbi_bond/rbibond_age_based_lc_in_prd_pre_enhmt/inquiry_rbibond_age_based.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_AGE_BASED_LOCK_IN_PERIOD:ENQUIRY",
        },
        controller: "rbiBondAgeBasedController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_age_based_lc_in_prd_pre_enhmt/rbibond-age_based.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_age_based.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/dc/rbi_bond/rbibond_age_based_lc_in_prd_pre_enhmt/inquiry_rbibond_age_based_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_AGE_BASED_LOCK_IN_PERIOD:ENQUIRY",
        },
        params: {
          obj: null,
        },
        controller: "rbiBondAgeBasedController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_age_based_lc_in_prd_pre_enhmt/rbibond-age_based.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_age_based.verify_detail", {
        url: "/verify_detail",
        templateUrl: "app/dc/rbi_bond/rbibond_age_based_lc_in_prd_pre_enhmt/verify_rbibond_age_based_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_AGE_BASED_LOCK_IN_PERIOD:VERIFY",
        },
        params: {
          obj: null,
        },
        controller: "rbiBondAgeBasedController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_age_based_lc_in_prd_pre_enhmt/rbibond-age_based.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_age_based.delete_detail", {
        url: "/delete_detail",
        templateUrl: "app/dc/rbi_bond/rbibond_age_based_lc_in_prd_pre_enhmt/delete_rbibond_age_based_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_AGE_BASED_LOCK_IN_PERIOD:DELETE",
        },
        params: {
          obj: null,
        },
        controller: "rbiBondAgeBasedController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_age_based_lc_in_prd_pre_enhmt/rbibond-age_based.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /*ICEGATE TOC Slab Maintenance Start*/
      .state("rbi_bond_toc_slab_maintenance", {
        abstract: true,
        url: "/rbi_bond_toc_slab_maintenance",
        params: {
          views: ["add", "modify", "verify", "delete", "inquiry"],
          permissions: [
            "GBM_DC_RBIBOND:RBIBOND_TOC_SLAB_MAINTENANCE:ADD",
            "GBM_DC_RBIBOND:RBIBOND_TOC_SLAB_MAINTENANCE:MODIFY",
            "GBM_DC_RBIBOND:RBIBOND_TOC_SLAB_MAINTENANCE:VERIFY",
            "GBM_DC_RBIBOND:RBIBOND_TOC_SLAB_MAINTENANCE:DELETE",
            "GBM_DC_RBIBOND:RBIBOND_TOC_SLAB_MAINTENANCE:ENQUIRY",
          ],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/dc/rbi_bond/rbi_bond_toc_slab_maintenance/rbi_bond_toc_slab_maintenance.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_toc_slab_maintenance.add", {
        url: "/add",
        templateUrl: "app/dc/rbi_bond/rbi_bond_toc_slab_maintenance/add_rbi_bond_toc_slab_maintenance.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TOC_SLAB_MAINTENANCE:ADD",
        },
        controller: "rbibondTocSlabMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_toc_slab_maintenance/rbi_bond_toc_slab_maintenance.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_toc_slab_maintenance.add_detail", {
        url: "/add/detail",
        templateUrl: "app/dc/rbi_bond/rbi_bond_toc_slab_maintenance/add_rbi_bond_toc_slab_maintenance_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TOC_SLAB_MAINTENANCE:ADD",
        },
        params: { obj: null },
        controller: "rbibondTocSlabMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_toc_slab_maintenance/rbi_bond_toc_slab_maintenance.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_toc_slab_maintenance.modify", {
        url: "/action/modify",
        templateUrl: "app/dc/rbi_bond/rbi_bond_toc_slab_maintenance/modify_rbi_bond_toc_slab_maintenance.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TOC_SLAB_MAINTENANCE:MODIFY",
        },
        controller: "rbibondTocSlabMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_toc_slab_maintenance/rbi_bond_toc_slab_maintenance.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_toc_slab_maintenance.modify_detail", {
        url: "/action/modify/detail",
        templateUrl: "app/dc/rbi_bond/rbi_bond_toc_slab_maintenance/modify_rbi_bond_toc_slab_maintenance_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TOC_SLAB_MAINTENANCE:MODIFY",
        },
        params: { obj: null },
        controller: "rbibondTocSlabMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_toc_slab_maintenance/rbi_bond_toc_slab_maintenance.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_toc_slab_maintenance.verify", {
        url: "/action/verify",
        templateUrl: "app/dc/rbi_bond/rbi_bond_toc_slab_maintenance/verify_rbi_bond_toc_slab_maintenance.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TOC_SLAB_MAINTENANCE:VERIFY",
        },
        controller: "rbibondTocSlabMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_toc_slab_maintenance/rbi_bond_toc_slab_maintenance.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_toc_slab_maintenance.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/dc/rbi_bond/rbi_bond_toc_slab_maintenance/verify_rbi_bond_toc_slab_maintenance_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TOC_SLAB_MAINTENANCE:VERIFY",
        },
        params: { obj: null },
        controller: "rbibondTocSlabMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_toc_slab_maintenance/rbi_bond_toc_slab_maintenance.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_toc_slab_maintenance.delete", {
        url: "/action/delete",
        templateUrl: "app/dc/rbi_bond/rbi_bond_toc_slab_maintenance/delete_rbi_bond_toc_slab_maintenance.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TOC_SLAB_MAINTENANCE:DELETE",
        },
        controller: "rbibondTocSlabMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_toc_slab_maintenance/rbi_bond_toc_slab_maintenance.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_toc_slab_maintenance.delete_detail", {
        url: "/action/delete/detail",
        templateUrl: "app/dc/rbi_bond/rbi_bond_toc_slab_maintenance/delete_rbi_bond_toc_slab_maintenance_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TOC_SLAB_MAINTENANCE:DELETE",
        },
        params: { obj: null },
        controller: "rbibondTocSlabMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_toc_slab_maintenance/rbi_bond_toc_slab_maintenance.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_toc_slab_maintenance.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/dc/rbi_bond/rbi_bond_toc_slab_maintenance/inquiry_rbi_bond_toc_slab_maintenance.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TOC_SLAB_MAINTENANCE:ENQUIRY",
        },
        controller: "rbibondTocSlabMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_toc_slab_maintenance/rbi_bond_toc_slab_maintenance.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_toc_slab_maintenance.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/dc/rbi_bond/rbi_bond_toc_slab_maintenance/inquiry_rbi_bond_toc_slab_maintenance_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TOC_SLAB_MAINTENANCE:ENQUIRY",
        },
        params: { obj: null },
        controller: "rbibondTocSlabMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_toc_slab_maintenance/rbi_bond_toc_slab_maintenance.controller.js?v=" + versionNum],
            });
          },
        },
      })
      /* ICEGATE TOC Slab Maintenance End*/
      /* CBDT TOC Sharing Maintenance Start*/
      .state("rbi_bond_toc_share_maintanance", {
        abstract: true,
        url: "/rbi_bond_toc_share_maintanance",
        params: {
          views: ["add", "modify", "verify", "delete", "inquiry"],
          permissions: [
            "GBM_DC_RBIBOND:RBIBOND_TOC_SHARING_MAINTENANCE:ADD",
            "GBM_DC_RBIBOND:RBIBOND_TOC_SHARING_MAINTENANCE:MODIFY",
            "GBM_DC_RBIBOND:RBIBOND_TOC_SHARING_MAINTENANCE:VERIFY",
            "GBM_DC_RBIBOND:RBIBOND_TOC_SHARING_MAINTENANCE:DELETE",
            "GBM_DC_RBIBOND:RBIBOND_TOC_SHARING_MAINTENANCE:ENQUIRY",
          ],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/dc/rbi_bond/rbi_bond_toc_share_maintanance/rbi_bond_toc_share_maintanance.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_toc_share_maintanance.add", {
        url: "/add",
        templateUrl: "app/dc/rbi_bond/rbi_bond_toc_share_maintanance/add_rbi_bond_toc_share_maintanance.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TOC_SHARING_MAINTENANCE:ADD",
        },
        controller: "rbibondtocSharingMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_toc_share_maintanance/rbi_bond_toc_share_maintanance.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_toc_share_maintanance.add_detail", {
        url: "/add/detail",
        templateUrl: "app/dc/rbi_bond/rbi_bond_toc_share_maintanance/add_rbi_bond_toc_share_maintanance_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TOC_SHARING_MAINTENANCE:ADD",
        },
        params: { obj: null },
        controller: "rbibondtocSharingMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_toc_share_maintanance/rbi_bond_toc_share_maintanance.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_toc_share_maintanance.modify", {
        url: "/action/modify",
        templateUrl: "app/dc/rbi_bond/rbi_bond_toc_share_maintanance/modify_rbi_bond_toc_share_maintanance.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TOC_SHARING_MAINTENANCE:MODIFY",
        },
        controller: "rbibondtocSharingMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_toc_share_maintanance/rbi_bond_toc_share_maintanance.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_toc_share_maintanance.modify_detail", {
        url: "/action/modify/detail",
        templateUrl: "app/dc/rbi_bond/rbi_bond_toc_share_maintanance/modify_rbi_bond_toc_share_maintanance_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TOC_SHARING_MAINTENANCE:MODIFY",
        },
        params: { obj: null },
        controller: "rbibondtocSharingMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_toc_share_maintanance/rbi_bond_toc_share_maintanance.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_toc_share_maintanance.verify", {
        url: "/action/verify",
        templateUrl: "app/dc/rbi_bond/rbi_bond_toc_share_maintanance/verify_rbi_bond_toc_share_maintance.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TOC_SHARING_MAINTENANCE:VERIFY",
        },
        controller: "rbibondtocSharingMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_toc_share_maintanance/rbi_bond_toc_share_maintanance.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_toc_share_maintanance.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/dc/rbi_bond/rbi_bond_toc_share_maintanance/verify_rbi_bond_toc_share_maintanance_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TOC_SHARING_MAINTENANCE:VERIFY",
        },
        params: { obj: null },
        controller: "rbibondtocSharingMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_toc_share_maintanance/rbi_bond_toc_share_maintanance.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_toc_share_maintanance.delete", {
        url: "/action/delete",
        templateUrl: "app/dc/rbi_bond/rbi_bond_toc_share_maintanance/delete_rbi_bond_toc_share_maintanance.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TOC_SHARING_MAINTENANCE:DELETE",
        },
        controller: "rbibondtocSharingMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_toc_share_maintanance/rbi_bond_toc_share_maintanance.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_toc_share_maintanance.delete_detail", {
        url: "/action/delete/detail",
        templateUrl: "app/dc/rbi_bond/rbi_bond_toc_share_maintanance/delete_rbi_bond_toc_share_maintanance_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TOC_SHARING_MAINTENANCE:DELETE",
        },
        params: { obj: null },
        controller: "rbibondtocSharingMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_toc_share_maintanance/rbi_bond_toc_share_maintanance.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_toc_share_maintanance.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/dc/rbi_bond/rbi_bond_toc_share_maintanance/inquiry_rbi_bond_toc_share_maintanance.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TOC_SHARING_MAINTENANCE:ENQUIRY",
        },
        params: { obj: null },
        controller: "rbibondtocSharingMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_toc_share_maintanance/rbi_bond_toc_share_maintanance.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_toc_share_maintanance.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/dc/rbi_bond/rbi_bond_toc_share_maintanance/inquiry_rbi_bond_toc_share_maintanance_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TOC_SHARING_MAINTENANCE:ENQUIRY",
        },
        params: { obj: null },
        controller: "rbibondtocSharingMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_toc_share_maintanance/rbi_bond_toc_share_maintanance.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /* TDS Parameter Start*/
      .state("tds_parameter_rbi_bond", {
        abstract: true,
        url: "/tds_parameter_rbi_bond",
        params: {
          views: ["add", "modify", "verify", "inquiry"],
          permissions: ["GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD", "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:MODIFY", "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:VERIFY", "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ENQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/dc/rbi_bond/tds_parameter_rbi_bond/tds_parameter_rbi_bond.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("tds_parameter_rbi_bond.add", {
        url: "/add",
        templateUrl: "app/dc/rbi_bond/tds_parameter_rbi_bond/add_tds_parameter_rbi_bond.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD",
        },
        controller: "tdsParameterRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/tds_parameter_rbi_bond/tds_parameter_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("tds_parameter_rbi_bond.add_detail", {
        url: "/add/detail",
        templateUrl: "app/dc/rbi_bond/tds_parameter_rbi_bond/add_tds_parameter_rbi_bond_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD",
        },
        params: { obj: null },
        controller: "tdsParameterRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/tds_parameter_rbi_bond/tds_parameter_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("tds_parameter_rbi_bond.modify", {
        url: "/action/modify",
        templateUrl: "app/dc/rbi_bond/tds_parameter_rbi_bond/modify_tds_parameter_rbi_bond.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:MODIFY",
        },
        controller: "tdsParameterRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/tds_parameter_rbi_bond/tds_parameter_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("tds_parameter_rbi_bond.modify_detail", {
        url: "/action/modify/detail",
        templateUrl: "app/dc/rbi_bond/tds_parameter_rbi_bond/modify_tds_parameter_rbi_bond_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:MODIFY",
        },
        params: { obj: null },
        controller: "tdsParameterRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/tds_parameter_rbi_bond/tds_parameter_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("tds_parameter_rbi_bond.verify", {
        url: "/action/verify",
        templateUrl: "app/dc/rbi_bond/tds_parameter_rbi_bond/verify_tds_parameter_rbi_bond.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:VERIFY",
        },
        controller: "tdsParameterRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/tds_parameter_rbi_bond/tds_parameter_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("tds_parameter_rbi_bond.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/dc/rbi_bond/tds_parameter_rbi_bond/verify_tds_parameter_rbi_bond_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:VERIFY",
        },
        params: { obj: null },
        controller: "tdsParameterRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/tds_parameter_rbi_bond/tds_parameter_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("tds_parameter_rbi_bond.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/dc/rbi_bond/tds_parameter_rbi_bond/inquiry_tds_parameter_rbi_bond.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ENQUIRY",
        },
        controller: "tdsParameterRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/tds_parameter_rbi_bond/tds_parameter_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("tds_parameter_rbi_bond.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/dc/rbi_bond/tds_parameter_rbi_bond/inquiry_tds_parameter_rbi_bond_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ENQUIRY",
        },
        params: { obj: null },
        controller: "tdsParameterRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/tds_parameter_rbi_bond/tds_parameter_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /*Scheme Master Start*/
      .state("scheme_master_rbi_bond", {
        abstract: true,
        url: "/scheme_master_rbi_bond",
        params: {
          views: ["add", "modify", "verify", "inquiry"],
          permissions: ["GBM_DC_RBIBOND:RBIBOND_SCHEME_MASTER_RBI_BOND:ADD", "GBM_DC_RBIBOND:RBIBOND_SCHEME_MASTER_RBI_BOND:MODIFY", "GBM_DC_RBIBOND:RBIBOND_SCHEME_MASTER_RBI_BOND:VERIFY", "GBM_DC_RBIBOND:RBIBOND_SCHEME_MASTER_RBI_BOND:ENQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/dc/rbi_bond/SchemeMaster/scheme_master_rbi_bond.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("scheme_master_rbi_bond.add", {
        url: "/add",
        templateUrl: "app/dc/rbi_bond/SchemeMaster/add_scheme_master_rbi_bond.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_SCHEME_MASTER_RBI_BOND:ADD",
        },
        controller: "schemeMasterRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/SchemeMaster/scheme_master_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("scheme_master_rbi_bond.modify", {
        url: "/action/modify",
        templateUrl: "app/dc/rbi_bond/SchemeMaster/modify_scheme_master_rbi_bond.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_SCHEME_MASTER_RBI_BOND:MODIFY",
        },
        controller: "schemeMasterRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/SchemeMaster/scheme_master_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("scheme_master_rbi_bond.modify_detail", {
        url: "/action/modify/detail",
        templateUrl: "app/dc/rbi_bond/SchemeMaster/modify_scheme_master_rbi_bond_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_SCHEME_MASTER_RBI_BOND:MODIFY",
        },
        params: { obj: null },
        controller: "schemeMasterRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/SchemeMaster/scheme_master_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("scheme_master_rbi_bond.verify", {
        url: "/action/verify",
        templateUrl: "app/dc/rbi_bond/SchemeMaster/verify_scheme_master_rbi_bond.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_SCHEME_MASTER_RBI_BOND:VERIFY",
        },
        controller: "schemeMasterRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/SchemeMaster/scheme_master_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("scheme_master_rbi_bond.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/dc/rbi_bond/SchemeMaster/verify_scheme_master_rbi_bond_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_SCHEME_MASTER_RBI_BOND:VERIFY",
        },
        params: { obj: null },
        controller: "schemeMasterRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/SchemeMaster/scheme_master_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("scheme_master_rbi_bond.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/dc/rbi_bond/SchemeMaster/inquiry_scheme_master_rbi_bond.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_SCHEME_MASTER_RBI_BOND:ENQUIRY",
        },
        controller: "schemeMasterRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/SchemeMaster/scheme_master_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("scheme_master_rbi_bond.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/dc/rbi_bond/SchemeMaster/inquiry_scheme_master_rbi_bond_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_SCHEME_MASTER_RBI_BOND:ENQUIRY",
        },
        params: { obj: null },
        controller: "schemeMasterRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/SchemeMaster/scheme_master_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      /*Scheme Master End*/

      /* code mappping Start*/
      .state("code_mapping_rbi_bond", {
        abstract: true,
        url: "/code_mapping_rbi_bond",
        params: {
          views: ["add", "modify", "verify", "delete", "inquiry"],
          permissions: [
            "GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:ADD",
            "GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:MODIFY",
            "GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:VERIFY",
            "GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:DELETE",
            "GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:ENQUIRY",
          ],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/dc/rbi_bond/rbi_code_mapping_rbi_bond/code_mapping_rbi_bond.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("code_mapping_rbi_bond.add", {
        url: "/add",
        templateUrl: "app/dc/rbi_bond/rbi_code_mapping_rbi_bond/add_code_mapping_rbi_bond.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:ADD",
        },
        controller: "codeMappingRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_code_mapping_rbi_bond/code_mapping_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("code_mapping_rbi_bond.modify", {
        url: "/action/modify",
        templateUrl: "app/dc/rbi_bond/rbi_code_mapping_rbi_bond/modify_code_mapping_rbi_bond.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:MODIFY",
        },
        controller: "codeMappingRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_code_mapping_rbi_bond/code_mapping_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("code_mapping_rbi_bond.modify_detail", {
        url: "/action/modify/detail",
        templateUrl: "app/dc/rbi_bond/rbi_code_mapping_rbi_bond/modify_code_mapping_rbi_bond_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:MODIFY",
        },
        params: { obj: null },
        controller: "codeMappingRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_code_mapping_rbi_bond/code_mapping_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("code_mapping_rbi_bond.verify", {
        url: "/action/verify",
        templateUrl: "app/dc/rbi_bond/rbi_code_mapping_rbi_bond/verify_code_mapping_rbi_bond.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:VERIFY",
        },
        controller: "codeMappingRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_code_mapping_rbi_bond/code_mapping_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("code_mapping_rbi_bond.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/dc/rbi_bond/rbi_code_mapping_rbi_bond/verify_code_mapping_rbi_bond_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:VERIFY",
        },
        params: { obj: null },
        controller: "codeMappingRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_code_mapping_rbi_bond/code_mapping_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("code_mapping_rbi_bond.delete", {
        url: "/action/delete",
        templateUrl: "app/dc/rbi_bond/rbi_code_mapping_rbi_bond/delete_code_mapping_rbi_bond.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:DELETE",
        },
        controller: "codeMappingRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_code_mapping_rbi_bond/code_mapping_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("code_mapping_rbi_bond.delete_detail", {
        url: "/action/delete/detail",
        templateUrl: "app/dc/rbi_bond/rbi_code_mapping_rbi_bond/delete_code_mapping_rbi_bond_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:DELETE",
        },
        params: { obj: null },
        controller: "codeMappingRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_code_mapping_rbi_bond/code_mapping_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("code_mapping_rbi_bond.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/dc/rbi_bond/rbi_code_mapping_rbi_bond/inquiry_code_mapping_rbi_bond.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:ENQUIRY",
        },
        controller: "codeMappingRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_code_mapping_rbi_bond/code_mapping_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("code_mapping_rbi_bond.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/dc/rbi_bond/rbi_code_mapping_rbi_bond/inquiry_code_mapping_rbi_bond_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:ENQUIRY",
        },
        params: { obj: null },
        controller: "codeMappingRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_code_mapping_rbi_bond/code_mapping_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /*State District Pincode Master*/

      .state("state_district_pin", {
        abstract: true,
        url: "/state_district_pin",
        params: {
          views: ["add", "verify", "inquiry"],
          permissions: ["GBM_DC_RBIBOND:RBIBOND_STATE_DISTRICT_PIN:ADD", "GBM_DC_RBIBOND:RBIBOND_STATE_DISTRICT_PIN:VERIFY", "GBM_DC_RBIBOND:RBIBOND_STATE_DISTRICT_PIN:ENQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/dc/rbi_bond/State_District_Pincode_Master/state_district_pincode.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("state_district_pin.add", {
        url: "/add",
        templateUrl: "app/dc/rbi_bond/State_District_Pincode_Master/add_state_district_pincode.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_STATE_DISTRICT_PIN:ADD",
        },
        controller: "StateDistrictPinController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/State_District_Pincode_Master/state_district_pincode.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("state_district_pin.verify", {
        url: "/action/verify",
        templateUrl: "app/dc/rbi_bond/State_District_Pincode_Master/verify_delete_state_district_pincode.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_STATE_DISTRICT_PIN:VERIFY",
        },
        controller: "StateDistrictPinController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/State_District_Pincode_Master/state_district_pincode.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("state_district_pin.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/dc/rbi_bond/State_District_Pincode_Master/inquiry_state_district_pincode.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_STATE_DISTRICT_PIN:ENQUIRY",
        },
        controller: "StateDistrictPinController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/State_District_Pincode_Master/state_district_pincode.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /* Rbi Bond Centralized Trial Run - Eligible Accounts DC Starts*/
      .state("rbibond_centralized_trial_run_dc_report", {
        abstract: true,
        url: "/rbibond_centralized_trial_run_dc_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_DC_RBIBOND:RBIBOND_INTEREST_TRAIL_RUN_ELIGIBLE_ACCOUNTS:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_centralized_trial_run_eligible_accounts/rbi_bond_centralized_trial_run_eligible_accounts.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("sample_pagination_employee", {
        abstract: true,
        url: "/sample_pagination_employee",
        params: {
          views: ["inquiry"],
          //					permissions: ['GBM_DC_CBDT:FINANCIAL_TRANSACTION_INQUIRY_DC:REPORT']
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/sample_pagination/sample_pagination_service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("sample_pagination_employee.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/dc/rbi_bond/sample_pagination/sample_pagination_employee.html",
        //				data: {
        //					authRequired: true,
        //					requiredPermission: 'GBM_DC_CBDT:FINANCIAL_TRANSACTION_INQUIRY_DC:REPORT'
        //				},
        controller: "samplePaginationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/sample_pagination/sample_pagination_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_centralized_trial_run_dc_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/dc/rbi_bond/rbi_bond_centralized_trial_run_eligible_accounts/rbi_bond_centralized_trial_run_eligible_accounts.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_INTEREST_TRAIL_RUN_ELIGIBLE_ACCOUNTS:REPORT",
        },
        controller: "rbiBondTrialRunEligibleAccountsController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_centralized_trial_run_eligible_accounts/rbi_bond_centralized_trial_run_eligible_accounts.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_centralized_trial_run_dc_report.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/dc/rbi_bond/rbi_bond_centralized_trial_run_eligible_accounts/rbi_bond_centralized_trial_run_eligible_accounts_list.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_INTEREST_TRAIL_RUN_ELIGIBLE_ACCOUNTS:REPORT",
        },
        params: { obj: null },
        controller: "rbiBondTrialRunEligibleAccountsController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_centralized_trial_run_eligible_accounts/rbi_bond_centralized_trial_run_eligible_accounts.controller.js?v=" + versionNum],
            });
          },
        },
      })
      /* Rbi Bond Centralized Trial Run - Eligible Accounts DC Ends*/
//
//      /*UnverifiedRecord starts*/
//      .state("unverified_records_for_rbi_bond", {
//        abstract: true,
//        url: "/unverified_records_for_rbi_bond",
//        params: {
//          views: ["inquiry"],
//          permissions: ["GBM_DC_RBIBOND:RBIBOND_UNVERIFIED_RECORD:ENQUIRY"],
//        },
//        resolve: {
//          load: function ($ocLazyLoad) {
//            return $ocLazyLoad.load({
//              files: ["app/dc/common/left-menu.directive.js", "app/dc/rbi_bond/unverified_records_for_rbi_bond/unverified_records_for_rbi_bond.service.js?v=" + versionNum],
//            });
//          },
//        },
//      })
//      .state("unverified_records_for_rbi_bond.inquiry", {
//        url: "/action/inquiry",
//        templateUrl: "app/dc/rbi_bond/unverified_records_for_rbi_bond/unverified_records_for_rbi_bond.html",
//        data: {
//          authRequired: true,
//          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_UNVERIFIED_RECORD:ENQUIRY",
//        },
//        controller: "unverifiedRecordReportController",
//        controllerAs: "vm",
//        resolve: {
//          load: function ($ocLazyLoad) {
//            return $ocLazyLoad.load({
//              files: ["app/dc/rbi_bond/unverified_records_for_rbi_bond/unverified_records_for_rbi_bond.controller.js?v=" + versionNum],
//            });
//          },
//        },
//      })
//      .state("unverified_records_for_rbi_bond.inquiry_details", {
//        url: "/action/inquiry/detail",
//        templateUrl: "app/dc/rbi_bond/unverified_records_for_rbi_bond/unverified_records_for_rbi_bond_detail.html",
//        data: {
//          authRequired: true,
//          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_UNVERIFIED_RECORD:ENQUIRY",
//        },
//        params: { obj: null },
//        controller: "unverifiedRecordReportController",
//        controllerAs: "vm",
//        resolve: {
//          load: function ($ocLazyLoad) {
//            return $ocLazyLoad.load({
//              files: ["app/dc/rbi_bond/unverified_records_for_rbi_bond/unverified_records_for_rbi_bond.controller.js?v=" + versionNum],
//            });
//          },
//        },
//      })

      //financial transaction inquiry lc starts//
      .state("rbi_bond_fti_lc", {
        abstract: true,
        url: "/rbi_bond_fti_lc",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_DC_RBIBOND:RBIBOND_FTI_REPORT_LC:ENQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/financial_transaction_inquiry/financial_transaction_inquiry.service.js"],
            });
          },
        },
      })
      .state("rbi_bond_fti_lc.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/dc/rbi_bond/financial_transaction_inquiry/financial_transaction_inquiry.html",

        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_FTI_REPORT_LC:ENQUIRY",
        },
        controller: "rbiBondFtiController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/financial_transaction_inquiry/financial_transaction_inquiry.controller.js"],
            });
          },
        },
      })
      .state("rbi_bond_fti_lc.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/dc/rbi_bond/financial_transaction_inquiry/financial_transaction_inquiry_list.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_FTI_REPORT_LC:ENQUIRY",
        },
        params: { obj: null },
        controller: "rbiBondFtiController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/financial_transaction_inquiry/financial_transaction_inquiry.controller.js"],
            });
          },
        },
      })
      //financial transaction inquiry lc ends//

      //bond Tds Deduction report starts//
      .state("rbibond_tds_deduction_report", {
        abstract: true,
        url: "/rbibond_tds_deduction_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_DC_FUNCTIONALITY:RBIBOND_TDS_DEDUCTION_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_tds_deduction_report/rbibond_tds_deduction_report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_tds_deduction_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_tds_deduction_report/rbibond_tds_deduction_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:RBIBOND_TDS_DEDUCTION_REPORT:REPORT",
        },
        controller: "rbibondTdsDeductionController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_tds_deduction_report/rbibond_tds_deduction_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_tds_deduction_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_tds_deduction_report/rbibond_tds_deduction_report_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:RBIBOND_TDS_DEDUCTION_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "rbibondTdsDeductionController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_tds_deduction_report/rbibond_tds_deduction_report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //bond Tds Deduction report ends//
      // Bond Interest Trial Run Report Mode of Payment Wise  starts //
      .state("rbibond_interest_trail_run_payment_wise_report", {
        abstract: true,
        url: "/rbibond_interest_trail_run_payment_wise_report",
        params: {
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_INTEREST_TRAIL_RUN_PAYMENT_WISE_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_interest_trail_run_payment_wise_report/rbibond_interest_trail_run_payment_wise_report.service.js"],
            });
          },
        },
      })
      .state("rbibond_interest_trail_run_payment_wise_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_interest_trail_run_payment_wise_report/rbibond_interest_trail_run_payment_wise_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_INTEREST_TRAIL_RUN_PAYMENT_WISE_REPORT:REPORT",
        },
        controller: "rbibondInterestTrailRunPaymentWiseController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_interest_trail_run_payment_wise_report/rbibond_interest_trail_run_payment_wise_report.controller.js"],
            });
          },
        },
      })
      .state("rbibond_interest_trail_run_payment_wise_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_interest_trail_run_payment_wise_report/rbibond_interest_trail_run_payment_wise_report_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_INTEREST_TRAIL_RUN_PAYMENT_WISE_REPORT:REPORT",
        },
        params: { obj: null },
        controller: "rbibondInterestTrailRunPaymentWiseController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_interest_trail_run_payment_wise_report/rbibond_interest_trail_run_payment_wise_report.controller.js"],
            });
          },
        },
      })
      //Bond Interest Trial Run Report Mode of Payment Wise ends//

      //bond interest run credited  report starts//
      .state("rbibond_interest_run_credit_report", {
        abstract: true,
        url: "/rbibond_interest_run_credit_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_DC_FUNCTIONALITY:RBIBOND_ACTUAL_INTEREST_CRDT_CENTER_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_interest_run_credit_report/rbibond_interest_run_credit_report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_interest_run_credit_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_interest_run_credit_report/rbibond_interest_run_credit_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:RBIBOND_ACTUAL_INTEREST_CRDT_CENTER_REPORT:REPORT",
        },
        controller: "rbibondInterestRunCreditController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_interest_run_credit_report/rbibond_interest_run_credit_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_interest_run_credit_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_interest_run_credit_report/rbibond_interest_run_credit_report_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:RBIBOND_ACTUAL_INTEREST_CRDT_CENTER_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "rbibondInterestRunCreditController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_interest_run_credit_report/rbibond_interest_run_credit_report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //bond interest run credited report ends//

      /* Rbi Bond Centralized Interest Payment Starts*/
      .state("rbi_bond_centralized_int_payment", {
        abstract: true,
        url: "/rbi_bond_centralized_int_payment",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_INTEREST_PAYMENT:ENQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_centralized_interest_payment/rbi_bond_centralized_interest_payment.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_centralized_int_payment.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/dc/rbi_bond/rbi_bond_centralized_interest_payment/rbi_bond_centralized_interest_payment.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_INTEREST_PAYMENT:ENQUIRY",
        },
        controller: "rbiBondCenInterestPaymentRunController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_centralized_interest_payment/rbi_bond_centralized_interest_payment.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_centralized_int_payment.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/dc/rbi_bond/rbi_bond_centralized_interest_payment/rbi_bond_centralized_interest_payment_list.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_INTEREST_PAYMENT:ENQUIRY",
        },
        params: { obj: null },
        controller: "rbiBondCenInterestPaymentRunController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_centralized_interest_payment/rbi_bond_centralized_interest_payment.controller.js?v=" + versionNum],
            });
          },
        },
      })
      /* Rbi Bond Centralized Interest Payment Ends*/

      //bond interest payment pending report starts//
      .state("rbibond_interest_payment_pending_report", {
        abstract: true,
        url: "/rbibond_interest_payment_pending_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_DC_FUNCTIONALITY:RBIBOND_PAYMENT_PENDING_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_interest_payment_pending_report/rbibond_interest_payment_pending_report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_interest_payment_pending_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_interest_payment_pending_report/rbibond_interest_payment_pending_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:RBIBOND_PAYMENT_PENDING_REPORT:REPORT",
        },
        controller: "rbibondInterestPaymentPendingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_interest_payment_pending_report/rbibond_interest_payment_pending_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_interest_payment_pending_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_interest_payment_pending_report/rbibond_interest_payment_pending_report_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:RBIBOND_PAYMENT_PENDING_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "rbibondInterestPaymentPendingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_interest_payment_pending_report/rbibond_interest_payment_pending_report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //bond interest payment pending report ends//

      /*  Rejection Reason Master Starts*/
      .state("rejection_reason_master", {
        abstract: true,
        url: "/rejection_reason_master",
        params: {
          views: ["add", "modify", "verify", "inquiry"],
          permissions: [
            "GBM_RBIBOND_DC_FUNCTIONALITY:REJECTION_REASON_MASTER:ADD",
            "GBM_RBIBOND_DC_FUNCTIONALITY:REJECTION_REASON_MASTER:MODIFY",
            "GBM_RBIBOND_DC_FUNCTIONALITY:REJECTION_REASON_MASTER:VERIFY",
            "GBM_RBIBOND_DC_FUNCTIONALITY:REJECTION_REASON_MASTER:INQUIRY",
          ],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/rejection_reason_master/rejection_reason_master_service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rejection_reason_master.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rejection_reason_master/add_rejection_reason_master.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:REJECTION_REASON_MASTER:ADD",
        },
        controller: "rejectionReasonMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rejection_reason_master/rejection_reason_master_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rejection_reason_master.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rejection_reason_master/add_detail_rejection_reason_master.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:REJECTION_REASON_MASTER:ADD",
        },
        params: { obj: null },
        controller: "rejectionReasonMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rejection_reason_master/rejection_reason_master_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rejection_reason_master.modify", {
        url: "/action/modify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rejection_reason_master/modify_rejection_reason_master.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:REJECTION_REASON_MASTER:MODIFY",
        },
        controller: "rejectionReasonMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rejection_reason_master/rejection_reason_master_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rejection_reason_master.modify_detail", {
        url: "/action/modify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rejection_reason_master/modify_detail_rejection_reason_master.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:REJECTION_REASON_MASTER:MODIFY",
        },
        params: { obj: null },
        controller: "rejectionReasonMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rejection_reason_master/rejection_reason_master_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rejection_reason_master.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rejection_reason_master/verify_rejection_reason_master.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:REJECTION_REASON_MASTER:VERIFY",
        },
        controller: "rejectionReasonMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rejection_reason_master/rejection_reason_master_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rejection_reason_master.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rejection_reason_master/verify_detail_rejection_reason_master.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:REJECTION_REASON_MASTER:VERIFY",
        },
        params: { obj: null },
        controller: "rejectionReasonMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rejection_reason_master/rejection_reason_master_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rejection_reason_master.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rejection_reason_master/inquiry_rejection_reason_master.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:REJECTION_REASON_MASTER:INQUIRY",
        },
        controller: "rejectionReasonMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rejection_reason_master/rejection_reason_master_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rejection_reason_master.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rejection_reason_master/inquiry_detail_rejection_reason_master.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:REJECTION_REASON_MASTER:INQUIRY",
        },
        params: { obj: null },
        controller: "rejectionReasonMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rejection_reason_master/rejection_reason_master_controller.js?v=" + versionNum],
            });
          },
        },
      })
      /*  Rejection Reason Master Ends*/

      /*  Drop Down Master Starts*/
      .state("drop_down_master", {
        abstract: true,
        url: "/drop_down_master",
        params: {
          views: ["add", "modify", "verify", "inquiry"],
          permissions: ["GBM_RBIBOND_DC_FUNCTIONALITY:DROP_DOWN_MASTER:ADD", "GBM_RBIBOND_DC_FUNCTIONALITY:DROP_DOWN_MASTER:MODIFY", "GBM_RBIBOND_DC_FUNCTIONALITY:DROP_DOWN_MASTER:VERIFY", "GBM_RBIBOND_DC_FUNCTIONALITY:DROP_DOWN_MASTER:INQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/drop_down_master/drop_down_master_service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("drop_down_master.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/drop_down_master/add_drop_down_master.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:DROP_DOWN_MASTER:ADD",
        },
        controller: "dropDownMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/drop_down_master/drop_down_master_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("drop_down_master.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/drop_down_master/add_detail_drop_down_master.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:DROP_DOWN_MASTER:ADD",
        },
        params: { obj: null },
        controller: "dropDownMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/drop_down_master/drop_down_master_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("drop_down_master.modify", {
        url: "/action/modify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/drop_down_master/modify_drop_down_master.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:DROP_DOWN_MASTER:MODIFY",
        },
        controller: "dropDownMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/drop_down_master/drop_down_master_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("drop_down_master.modify_detail", {
        url: "/action/modify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/drop_down_master/modify_detail_drop_down_master.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:DROP_DOWN_MASTER:MODIFY",
        },
        params: { obj: null },
        controller: "dropDownMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/drop_down_master/drop_down_master_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("drop_down_master.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/drop_down_master/verify_drop_down_master.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:DROP_DOWN_MASTER:VERIFY",
        },
        controller: "dropDownMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/drop_down_master/drop_down_master_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("drop_down_master.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/drop_down_master/verify_detail_drop_down_master.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:DROP_DOWN_MASTER:VERIFY",
        },
        params: { obj: null },
        controller: "dropDownMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/drop_down_master/drop_down_master_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("drop_down_master.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/drop_down_master/inquiry_drop_down_master.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:DROP_DOWN_MASTER:INQUIRY",
        },
        controller: "dropDownMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/drop_down_master/drop_down_master_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("drop_down_master.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/drop_down_master/inquiry_detail_drop_down_master.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:DROP_DOWN_MASTER:INQUIRY",
        },
        params: { obj: null },
        controller: "dropDownMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/drop_down_master/drop_down_master_controller.js?v=" + versionNum],
            });
          },
        },
      })

      /*  Drop Down Master Ends*/

      /*Common Flag*/

      .state("common_flag", {
        abstract: true,
        url: "/common_flag",
        params: {
          views: ["modify", "verify", "inquiry"],
          permissions: ["GBM_RBIBOND_DC_FUNCTIONALITY:RBIBOND_COMMON_FLAG:MODIFY", "GBM_RBIBOND_DC_FUNCTIONALITY:RBIBOND_COMMON_FLAG:VERIFY", "GBM_RBIBOND_DC_FUNCTIONALITY:RBIBOND_COMMON_FLAG:INQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/common_flag/commom_flag_service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("common_flag.modify", {
        url: "/action/modify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/common_flag/modify_commom_flag.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:RBIBOND_COMMON_FLAG:MODIFY",
        },
        controller: "commonFlagController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/common_flag/common_flag_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("common_flag.modify_detail", {
        url: "/action/modify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/common_flag/modify_commom_flag_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:RBIBOND_COMMON_FLAG:MODIFY",
        },
        params: { obj: null },
        controller: "commonFlagController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/common_flag/common_flag_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("common_flag.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/common_flag/verify_reject_common_flag.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:RBIBOND_COMMON_FLAG:VERIFY",
        },
        controller: "commonFlagController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/common_flag/common_flag_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("common_flag.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/common_flag/verify_reject_common_flag_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:RBIBOND_COMMON_FLAG:VERIFY",
        },
        params: { obj: null },
        controller: "commonFlagController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/common_flag/common_flag_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("common_flag.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/common_flag/inquiry_commom_flag.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:RBIBOND_COMMON_FLAG:INQUIRY",
        },
        controller: "commonFlagController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/common_flag/common_flag_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("common_flag.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/common_flag/inquiry_commom_flag_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC_FUNCTIONALITY:RBIBOND_COMMON_FLAG:INQUIRY",
        },
        params: { obj: null },
        controller: "commonFlagController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/common_flag/common_flag_controller.js?v=" + versionNum],
            });
          },
        },
      })
      /*ends common flag */

      //bond_interest_payment_mis_report_sol_wise report starts//
      .state("bond_interest_payment_mis_report_sol_wise", {
        abstract: true,
        url: "/bond_interest_payment_mis_report_sol_wise",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_DC:BOND_INTEREST_PAY_MIS_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/bond_interest_payment_mis_report_sol_wise/bond_interest_payment_mis_report_sol_wise_service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("bond_interest_payment_mis_report_sol_wise.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/dc/rbi_bond/bond_interest_payment_mis_report_sol_wise/bond_interest_payment_mis_report_sol_wise_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC:BOND_INTEREST_PAY_MIS_REPORT:REPORT",
        },
        controller: "bondInterestPayMisReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/bond_interest_payment_mis_report_sol_wise/bond_interest_payment_mis_report_sol_wise_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("bond_interest_payment_mis_report_sol_wise.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/dc/rbi_bond/bond_interest_payment_mis_report_sol_wise/bond_interest_payment_mis_report_sol_wise_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC:BOND_INTEREST_PAY_MIS_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "bondInterestPayMisReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/bond_interest_payment_mis_report_sol_wise/bond_interest_payment_mis_report_sol_wise_controller.js?v=" + versionNum],
            });
          },
        },
      })
      //bond_interest_payment_mis_report_sol_wise report ends//

      //Report on Un-verified Records - DC
      .state("Report_On_Unverified_Records_DC", {
        abstract: true,
        url: "/Report_On_Unverified_Records_DC",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_DC_RBIBOND:REPORT_ON_UNVERIFIED_RECORDS_DC:ENQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/Report_On_Unverified_Records/Report_On_Unverified_Records.service.js"],
            });
          },
        },
      })
      .state("Report_On_Unverified_Records_DC.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/dc/rbi_bond/Report_On_Unverified_Records/Report_On_Unverified_Records_Input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:REPORT_ON_UNVERIFIED_RECORDS_DC:ENQUIRY",
        },
        controller: "ReportOnUnverifiedController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/Report_On_Unverified_Records/Report_On_Unverified_Records.controller.js"],
            });
          },
        },
      })
      .state("Report_On_Unverified_Records_DC.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/dc/rbi_bond/Report_On_Unverified_Records/Report_On_Unverified_Records_Details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:REPORT_ON_UNVERIFIED_RECORDS_DC:ENQUIRY",
        },
        params: { obj: null },
        controller: "ReportOnUnverifiedController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/Report_On_Unverified_Records/Report_On_Unverified_Records.controller.js"],
            });
          },
        },
      })
    //end of Report on Un-verified Records - DC
  }
})();
