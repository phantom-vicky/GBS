(function () {
  "use strict";

  angular.module("gbmUltraApp").config(RbiBondGuFunctionalityConfig);

  RbiBondGuFunctionalityConfig.$inject = ["$stateProvider", "$ocLazyLoadProvider"];

  function RbiBondGuFunctionalityConfig($stateProvider, $ocLazyLoadProvider) {
    $stateProvider

      //bla account opening cum subscription start

      .state("bla_acc_opening_cum_subscription", {
        abstract: true,
        url: "/bla_acc_opening_cum_subscription",
        params: {
          views: ["add", "modify", "verify", "inquiry"],
          permissions: [
            "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:ADD",
            "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:MODIFY",
            "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:VERIFY",
            "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:ENQUIRY",
          ],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/bla_acc_opening_cum_subscription/bla_acc_opening_cum_subscription.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bla_acc_opening_cum_subscription.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bla_acc_opening_cum_subscription/add_bla_acc_opening_cum_subscription.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:ADD",
        },
        controller: "bondLedgerAccSubscriptionController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bla_acc_opening_cum_subscription/bla_acc_opening_cum_subscription.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("bla_acc_opening_cum_subscription.add_input", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bla_acc_opening_cum_subscription/add_bla_acc_opening_cum_subscription_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:ADD",
        },
        params: { obj: null },
        controller: "bondLedgerAccSubscriptionController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bla_acc_opening_cum_subscription/bla_acc_opening_cum_subscription.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("bla_acc_opening_cum_subscription.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bla_acc_opening_cum_subscription/add_bla_acc_opening_cum_subscription_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:ADD",
        },
        params: { obj: null },
        controller: "bondLedgerAccSubscriptionController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bla_acc_opening_cum_subscription/bla_acc_opening_cum_subscription.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bla_acc_opening_cum_subscription.modify", {
        url: "/action/modify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bla_acc_opening_cum_subscription/modify_bla_acc_opening_cum_subscription.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:MODIFY",
        },
        controller: "bondLedgerAccSubscriptionController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bla_acc_opening_cum_subscription/bla_acc_opening_cum_subscription.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bla_acc_opening_cum_subscription.modify_detail", {
        url: "/action/modify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bla_acc_opening_cum_subscription/modify_bla_acc_opening_cum_subscription_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:MODIFY",
        },
        params: { obj: null },
        controller: "bondLedgerAccSubscriptionController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bla_acc_opening_cum_subscription/bla_acc_opening_cum_subscription.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bla_acc_opening_cum_subscription.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bla_acc_opening_cum_subscription/verify_reject_bla_acc_opening_cum_subscription.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:VERIFY",
        },
        controller: "bondLedgerAccSubscriptionController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bla_acc_opening_cum_subscription/bla_acc_opening_cum_subscription.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bla_acc_opening_cum_subscription.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bla_acc_opening_cum_subscription/verify_reject_bla_acc_opening_cum_subscription_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:VERIFY",
        },
        params: { obj: null },
        controller: "bondLedgerAccSubscriptionController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bla_acc_opening_cum_subscription/bla_acc_opening_cum_subscription.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("bla_acc_opening_cum_subscription.list", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bla_acc_opening_cum_subscription/inquiry_bla_acc_opening_cum_subscription.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:ENQUIRY",
        },
        controller: "bondLedgerAccSubscriptionController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bla_acc_opening_cum_subscription/bla_acc_opening_cum_subscription.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bla_acc_opening_cum_subscription.list_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bla_acc_opening_cum_subscription/inquiry_bla_acc_opening_cum_subscription_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:ENQUIRY",
        },
        params: { obj: null },
        controller: "bondLedgerAccSubscriptionController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bla_acc_opening_cum_subscription/bla_acc_opening_cum_subscription.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //end bla subscription
      //start Cum to Non Cum change of bond type change

      .state("cum_to_non_cum_change_of_bond_type", {
        abstract: true,
        url: "/cum_to_non_cum_change_of_bond_type",
        params: {
          views: ["add", "verify", "inquiry"],
          permissions: ["GBM_GU_RBIBOND:RBIBOND_CUM_TO_NON_CUM_BOND_TYPE:ADD", "GBM_GU_RBIBOND:RBIBOND_CUM_TO_NON_CUM_BOND_TYPE:VERIFY", "GBM_GU_RBIBOND:RBIBOND_CUM_TO_NON_CUM_BOND_TYPE:ENQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/cum_to_non_cum_change_of_bond_type/cum_to_non_cum_change_of_bond_type.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("cum_to_non_cum_change_of_bond_type.add_input", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/cum_to_non_cum_change_of_bond_type/add_cum_to_non_cum_change_of_bond_type.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_CUM_TO_NON_CUM_BOND_TYPE:ADD",
        },
        params: { obj: null },
        controller: "CumToNonCumChangeOfBondTypeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/cum_to_non_cum_change_of_bond_type/cum_to_non_cum_change_of_bond_type.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("cum_to_non_cum_change_of_bond_type.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/cum_to_non_cum_change_of_bond_type/add_cum_to_non_cum_change_of_bond_type_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_CUM_TO_NON_CUM_BOND_TYPE:ADD",
        },
        params: { obj: null },
        controller: "CumToNonCumChangeOfBondTypeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/cum_to_non_cum_change_of_bond_type/cum_to_non_cum_change_of_bond_type.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("cum_to_non_cum_change_of_bond_type.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/cum_to_non_cum_change_of_bond_type/verify_reject_cum_to_non_cum_change_of_bond_type.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_CUM_TO_NON_CUM_BOND_TYPE:VERIFY",
        },
        controller: "CumToNonCumChangeOfBondTypeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/cum_to_non_cum_change_of_bond_type/cum_to_non_cum_change_of_bond_type.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("cum_to_non_cum_change_of_bond_type.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/cum_to_non_cum_change_of_bond_type/verify_reject_cum_to_non_cum_change_of_bond_type_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_CUM_TO_NON_CUM_BOND_TYPE:VERIFY",
        },
        params: { obj: null },
        controller: "CumToNonCumChangeOfBondTypeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/cum_to_non_cum_change_of_bond_type/cum_to_non_cum_change_of_bond_type.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("cum_to_non_cum_change_of_bond_type.list", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/cum_to_non_cum_change_of_bond_type/inquiry_cum_to_non_cum_change_of_bond_type.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_CUM_TO_NON_CUM_BOND_TYPE:ENQUIRY",
        },
        controller: "CumToNonCumChangeOfBondTypeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/cum_to_non_cum_change_of_bond_type/cum_to_non_cum_change_of_bond_type.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("cum_to_non_cum_change_of_bond_type.list_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/cum_to_non_cum_change_of_bond_type/inquiry_cum_to_non_cum_change_of_bond_type_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_CUM_TO_NON_CUM_BOND_TYPE:ENQUIRY",
        },
        params: { obj: null },
        controller: "CumToNonCumChangeOfBondTypeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/cum_to_non_cum_change_of_bond_type/cum_to_non_cum_change_of_bond_type.controller.js?v=" + versionNum],
            });
          },
        },
      })
      // End
      //start Mode of holding change

      .state("mode_of_holding_change", {
        abstract: true,
        url: "/mode_of_holding_change",
        params: {
          views: ["add", "verify", "inquiry"],
          permissions: ["GBM_GU_RBIBOND:RBIBOND_MODE_OF_HOLDING_CHANGE_RBI_BOND:ADD", "GBM_GU_RBIBOND:RBIBOND_MODE_OF_HOLDING_CHANGE_RBI_BOND:VERIFY", "GBM_GU_RBIBOND:RBIBOND_MODE_OF_HOLDING_CHANGE_BOND:ENQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/Mode_Of_Holding_Change/mode_of_holding_change.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("mode_of_holding_change.add_input", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/Mode_Of_Holding_Change/add_mode_of_holding_change_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_MODE_OF_HOLDING_CHANGE_RBI_BOND:ADD",
        },
        params: { obj: null },
        controller: "modeOfHoldingChangeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/Mode_Of_Holding_Change/mode_of_holding_change.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("mode_of_holding_change.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/Mode_Of_Holding_Change/add_mode_of_holding_change_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_MODE_OF_HOLDING_CHANGE_RBI_BOND:ADD",
        },
        params: { obj: null },
        controller: "modeOfHoldingChangeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/Mode_Of_Holding_Change/mode_of_holding_change.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("mode_of_holding_change.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/Mode_Of_Holding_Change/verify_reject_mode_of_holding_change.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_MODE_OF_HOLDING_CHANGE_RBI_BOND:VERIFY",
        },
        controller: "modeOfHoldingChangeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/Mode_Of_Holding_Change/mode_of_holding_change.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("mode_of_holding_change.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/Mode_Of_Holding_Change/verify_reject_mode_of_holding_change_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_MODE_OF_HOLDING_CHANGE_RBI_BOND:VERIFY",
        },
        params: { obj: null },
        controller: "modeOfHoldingChangeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/Mode_Of_Holding_Change/mode_of_holding_change.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("mode_of_holding_change.list", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/Mode_Of_Holding_Change/inquiry_mode_of_holding_change.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_MODE_OF_HOLDING_CHANGE_BOND:ENQUIRY",
        },
        controller: "modeOfHoldingChangeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/Mode_Of_Holding_Change/mode_of_holding_change.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("mode_of_holding_change.list_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/Mode_Of_Holding_Change/inquiry_mode_of_holding_change_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_MODE_OF_HOLDING_CHANGE_BOND:ENQUIRY",
        },
        params: { obj: null },
        controller: "modeOfHoldingChangeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/Mode_Of_Holding_Change/mode_of_holding_change.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //end Mode of holding change

      ////bond ledger acc maintenance starts
      .state("bond_ledger_acc_main", {
        abstract: true,
        url: "/bond_ledger_acc_main",
        params: {
          views: ["add", "modify", "verify", "delete", "inquiry"],
          permissions: [
            "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD",
            "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:MODIFY",
            "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:VERIFY",
            "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:DELETE",
            "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ENQUIRY",
          ],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/bond_ledger_acc_maintenance/bond_ledger_detail.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_ledger_acc_main.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_ledger_acc_maintenance/add_bond_ledger_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD",
        },
        controller: "bondLedgerAccMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_ledger_acc_maintenance/bond_ledger_detail.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_ledger_acc_main.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_ledger_acc_maintenance/add_bond_ledger_detail_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD",
        },
        params: { obj: null },
        controller: "bondLedgerAccMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_ledger_acc_maintenance/bond_ledger_detail.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_ledger_acc_main.modify", {
        url: "/action/modify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_ledger_acc_maintenance/modify_bond_ledger_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:MODIFY",
        },
        controller: "bondLedgerAccMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_ledger_acc_maintenance/bond_ledger_detail.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_ledger_acc_main.modify_detail", {
        url: "/action/modify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_ledger_acc_maintenance/modify_bond_ledger_detail_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:MODIFY",
        },
        params: { obj: null },
        controller: "bondLedgerAccMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_ledger_acc_maintenance/bond_ledger_detail.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_ledger_acc_main.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_ledger_acc_maintenance/verify_bond_ledger_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:VERIFY",
        },
        controller: "bondLedgerAccMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_ledger_acc_maintenance/bond_ledger_detail.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_ledger_acc_main.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_ledger_acc_maintenance/verify_bond_ledger_detail_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:VERIFY",
        },
        params: { obj: null },
        controller: "bondLedgerAccMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_ledger_acc_maintenance/bond_ledger_detail.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_ledger_acc_main.delete", {
        url: "/action/delete",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_ledger_acc_maintenance/delete_bond_ledger_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:DELETE",
        },
        controller: "bondLedgerAccMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_ledger_acc_maintenance/bond_ledger_detail.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_ledger_acc_main.delete_detail", {
        url: "/action/delete/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_ledger_acc_maintenance/delete_add_bond_ledger_detail_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:DELETE",
        },
        params: { obj: null },
        controller: "bondLedgerAccMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_ledger_acc_maintenance/bond_ledger_detail.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_ledger_acc_main.list", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_ledger_acc_maintenance/inquiry_bond_ledger_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ENQUIRY",
        },
        controller: "bondLedgerAccMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_ledger_acc_maintenance/bond_ledger_detail.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_ledger_acc_main.list_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_ledger_acc_maintenance/inquiry_bond_ledger_detail_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ENQUIRY",
        },
        params: { obj: null },
        controller: "bondLedgerAccMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_ledger_acc_maintenance/bond_ledger_detail.controller.js?v=" + versionNum],
            });
          },
        },
      })
      ////bond ledger acc maintenance ends

      /* EOD BOD inquiry Link cell */

      .state("rbibond_bod_eod_status_inquiry_eFpb", {
        abstract: true,
        url: "/rbibond_bod_eod_status_inquiry_eFpb",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBI_GU_FUNCTIONALITY:BOD_EOD_STATUS_INQUIRY_LC:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_bod_eod_status_inquiry/rbibond_bod_eod_status_inquiry.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_bod_eod_status_inquiry_eFpb.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/dc/rbi_bond/rbibond_bod_eod_status_inquiry/rbibond_bod_eod_status_inquiry.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBI_GU_FUNCTIONALITY:BOD_EOD_STATUS_INQUIRY_LC:REPORT",
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
      .state("rbibond_bod_eod_status_inquiry_eFpb.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/dc/rbi_bond/rbibond_bod_eod_status_inquiry/rbibond_bod_eod_status_inquiry_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBI_GU_FUNCTIONALITY:BOD_EOD_STATUS_INQUIRY_LC:REPORT",
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

      /* Brokerage Invoice Confirmation starts */

      .state("rbibond_brokerage_invoice_confirmation", {
        abstract: true,
        url: "/rbibond_brokerage_invoice_confirmation",
        params: {
          views: ["add", "upload", "verify", "inquiry"],
          permissions: ["GBM_RBI_GU_FUNCTIONALITY:BRKGE_INVC_CNFRM:ADD", "GBM_RBI_GU_FUNCTIONALITY:BRKGE_INVC_CNFRM:UPLOAD", "GBM_RBI_GU_FUNCTIONALITY:BRKGE_INVC_CNFRM:VERIFY", "GBM_RBI_GU_FUNCTIONALITY:BRKGE_INVC_CNFRM:INQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/brokerage_invoice_confirmation/brokerage_invoice_confirmation.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_brokerage_invoice_confirmation.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/brokerage_invoice_confirmation/add_brokerage_invoice_confirmation_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBI_GU_FUNCTIONALITY:BRKGE_INVC_CNFRM:ADD",
        },
        controller: "brokerageInvoiceConfirmationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/brokerage_invoice_confirmation/brokerage_invoice_confirmation.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_brokerage_invoice_confirmation.add_details", {
        url: "/add/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/brokerage_invoice_confirmation/add_brokerage_invoice_confirmation_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBI_GU_FUNCTIONALITY:BRKGE_INVC_CNFRM:ADD",
        },
        params: {
          obj: null,
        },
        controller: "brokerageInvoiceConfirmationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/brokerage_invoice_confirmation/brokerage_invoice_confirmation.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_brokerage_invoice_confirmation.upload", {
        url: "/action/upload_feature",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/brokerage_invoice_confirmation/brokerage_invoice_confirmation_upload.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBI_GU_FUNCTIONALITY:BRKGE_INVC_CNFRM:UPLOAD",
        },
        controller: "brokerageInvoiceConfirmationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/brokerage_invoice_confirmation/brokerage_invoice_confirmation.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_brokerage_invoice_confirmation.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/brokerage_invoice_confirmation/verify_brokerage_invoice_confirmation_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBI_GU_FUNCTIONALITY:BRKGE_INVC_CNFRM:VERIFY",
        },
        controller: "brokerageInvoiceConfirmationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/brokerage_invoice_confirmation/brokerage_invoice_confirmation.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_brokerage_invoice_confirmation.verify_details", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/brokerage_invoice_confirmation/verify_brokerage_invoice_confirmation_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBI_GU_FUNCTIONALITY:BRKGE_INVC_CNFRM:VERIFY",
        },
        params: {
          obj: null,
        },
        controller: "brokerageInvoiceConfirmationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/brokerage_invoice_confirmation/brokerage_invoice_confirmation.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_brokerage_invoice_confirmation.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/brokerage_invoice_confirmation/inquiry_brokerage_invoice_confirmation_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBI_GU_FUNCTIONALITY:BRKGE_INVC_CNFRM:INQUIRY",
        },
        controller: "brokerageInvoiceConfirmationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/brokerage_invoice_confirmation/brokerage_invoice_confirmation.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_brokerage_invoice_confirmation.inquiry_details", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/brokerage_invoice_confirmation/inquiry_brokerage_invoice_confirmation_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBI_GU_FUNCTIONALITY:BRKGE_INVC_CNFRM:INQUIRY",
        },
        params: {
          obj: null,
        },
        controller: "brokerageInvoiceConfirmationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/brokerage_invoice_confirmation/brokerage_invoice_confirmation.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /* Brokerage Invoice Confirmation ends */

      /* EOD BOD inquiry Collecting Branch */

      .state("rbibond_bod_eod_status_inquiry_cb", {
        abstract: true,
        url: "/rbibond_bod_eod_status_inquiry_cb",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBI_GU_FUNCTIONALITY:BOD_EOD_STATUS_INQUIRY_CB:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbibond_bod_eod_status_inquiry/rbibond_bod_eod_status_inquiry.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_bod_eod_status_inquiry_cb.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/dc/rbi_bond/rbibond_bod_eod_status_inquiry/rbibond_bod_eod_status_inquiry.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBI_GU_FUNCTIONALITY:BOD_EOD_STATUS_INQUIRY_CB:REPORT",
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

      .state("rbibond_bod_eod_status_inquiry_cb.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/dc/rbi_bond/rbibond_bod_eod_status_inquiry/rbibond_bod_eod_status_inquiry_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBI_GU_FUNCTIONALITY:BOD_EOD_STATUS_INQUIRY_CB:REPORT",
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

      .state("bond_repayment_report", {
        abstract: true,
        url: "/bond_repayment_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_REPAYMENT_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/bond_repayment_report/bond_repayment_report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("bond_repayment_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/bond_repayment_report/bond_repayment_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_REPAYMENT_REPORT:REPORT",
        },
        controller: "bondRepaymentReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/bond_repayment_report/bond_repayment_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("bond_repayment_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/bond_repayment_report/bond_repayment_report_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_REPAYMENT_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "bondRepaymentReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/bond_repayment_report/bond_repayment_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /* REGISTER OF BOND LEDGER ACCOUNTS- INDIVIDUAL ACCOUNT - START */

      .state("register_of_bond_ledger_accounts_single_holders", {
        abstract: true,
        url: "/register_of_bond_ledger_accounts_single_holders",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_REG_BOND_LEDGER_ACCT_IND_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/register_of_bond _ledger_accounts_individual_account/register_of_Bond _Ledger_Accounts.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("register_of_bond_ledger_accounts_single_holders.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/register_of_bond _ledger_accounts_individual_account/register_of_Bond _Ledger_Accounts.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_REG_BOND_LEDGER_ACCT_IND_REPORT:REPORT",
        },
        controller: "rbibondBondLedgeAccController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/register_of_bond _ledger_accounts_individual_account/register_of_Bond _Ledger_Accounts.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("register_of_bond_ledger_accounts_single_holders.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/register_of_bond _ledger_accounts_individual_account/register_of_Bond _Ledger_Accounts_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_REG_BOND_LEDGER_ACCT_IND_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "rbibondBondLedgeAccController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/register_of_bond _ledger_accounts_individual_account/register_of_Bond _Ledger_Accounts.controller.js?v=" + versionNum],
            });
          },
        },
      })
      /* REGISTER OF BOND LEDGER ACCOUNTS- INDIVIDUAL ACCOUNT - END */

      /* NOMINEE CONTINUATION REPORT - START */
      .state("nominee_continuation_report", {
        abstract: true,
        url: "/nominee_continuation_report",
        params: {
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:NOMINEE_CONTINUATION_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_nominee_continuation_report/nominee_continuation_report_service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("nominee_continuation_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_nominee_continuation_report/nominee_continuation_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:NOMINEE_CONTINUATION_REPORT:REPORT",
        },
        controller: "rbibondNomineeContinuationReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_nominee_continuation_report/nominee_continuation_report_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("nominee_continuation_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_nominee_continuation_report/nominee_continuation_report_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:NOMINEE_CONTINUATION_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "rbibondNomineeContinuationReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_nominee_continuation_report/nominee_continuation_report_controller.js?v=" + versionNum],
            });
          },
        },
      })
      /* NOMINEE CONTINUATION REPORT - END */

      //bond nomination register report starts//
      //---------------------------------------------------------------------------------------

      /*rbi bond transfer in  starts*/
      .state("rbi_bond_transfer_in", {
        abstract: true,
        url: "/rbi_bond_transfer_in",
        params: {
          views: ["add", "modify", "verify", "delete", "inquiry"],
          permissions: ["GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:ADD", "GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:MODIFY", "GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:VERIFY", "GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:DELETE", "GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:INQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_transfer_in/rbi_bond_transfer_in_service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_transfer_in.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_transfer_in/rbi_bond_transfer_in_add.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:ADD",
        },
        controller: "bondTransferInController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_transfer_in/rbi_bond_transfer_in_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_transfer_in.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_transfer_in/rbi_bond_transfer_in_add_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:ADD",
        },
        params: { obj: null },
        controller: "bondTransferInController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_transfer_in/rbi_bond_transfer_in_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_transfer_in.modify", {
        url: "/action/modify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_transfer_in/rbi_bond_transfer_in_modify.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:MODIFY",
        },
        controller: "bondTransferInController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_transfer_in/rbi_bond_transfer_in_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_transfer_in.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_transfer_in/rbi_bond_transfer_in_inquiry.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:INQUIRY",
        },
        controller: "bondTransferInController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_transfer_in/rbi_bond_transfer_in_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_transfer_in.delete", {
        url: "/action/delete",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_transfer_in/rbi_bond_transfer_in_delete.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:DELETE",
        },
        params: { obj: null },
        controller: "bondTransferInController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_transfer_in/rbi_bond_transfer_in_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_transfer_in.delete_detail", {
        url: "/action/delete/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_transfer_in/rbi_bond_transfer_in_delete_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:DELETE",
        },
        params: { obj: null },
        controller: "bondTransferInController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_transfer_in/rbi_bond_transfer_in_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_transfer_in.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_transfer_in/rbi_bond_transfer_in_verify.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:VERIFY",
        },
        controller: "bondTransferInController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_transfer_in/rbi_bond_transfer_in_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_transfer_in.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_transfer_in/rbi_bond_transfer_in_verify_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:VERIFY",
        },
        params: { obj: null },
        controller: "bondTransferInController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_transfer_in/rbi_bond_transfer_in_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_transfer_in.modify_detail", {
        url: "/action/modify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_transfer_in/rbi_bond_transfer_in_modify_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:MODIFY",
        },
        params: { obj: null },
        controller: "bondTransferInController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_transfer_in/rbi_bond_transfer_in_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_transfer_in.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_transfer_in/rbi_bond_transfer_in_inquiry_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:INQUIRY",
        },
        params: { obj: null },
        controller: "bondTransferInController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_transfer_in/rbi_bond_transfer_in_controller.js?v=" + versionNum],
            });
          },
        },
      })
      /*rbi bond transfer in  end*/

      /* bond subscription reversal change accno - starts */

      .state("bond_subs_reversal_change_acc_no", {
        abstract: true,
        url: "/bond_subs_reversal_change_acc_no",
        params: {
          views: ["add", "verify", "delete", "inquiry"],
          permissions: [
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_SUBS_REVERSAL_CHANGE_ACC_NO:ADD",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_SUBS_REVERSAL_CHANGE_ACC_NO:DELETE",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_SUBS_REVERSAL_CHANGE_ACC_NO:VERIFY",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_SUBS_REVERSAL_CHANGE_ACC_NO:INQUIRY",
          ],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/bond_subs_reversal_change_acc_no/bond_subs_reversal_change_acc_no.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("bond_subs_reversal_change_acc_no.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_subs_reversal_change_acc_no/add_bond_subs_reversal_change_acc_no.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_SUBS_REVERSAL_CHANGE_ACC_NO:ADD",
        },
        controller: "rbibondReversalChangeAccNoController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_subs_reversal_change_acc_no/bond_subs_reversal_change_acc_no.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("bond_subs_reversal_change_acc_no.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_subs_reversal_change_acc_no/add_bond_subs_reversal_change_acc_no_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_SUBS_REVERSAL_CHANGE_ACC_NO:ADD",
        },
        params: { obj: null },
        controller: "rbibondReversalChangeAccNoController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_subs_reversal_change_acc_no/bond_subs_reversal_change_acc_no.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("bond_subs_reversal_change_acc_no.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_subs_reversal_change_acc_no/verify_bond_subs_reversal_change_acc_no.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_SUBS_REVERSAL_CHANGE_ACC_NO:VERIFY",
        },
        controller: "rbibondReversalChangeAccNoController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_subs_reversal_change_acc_no/bond_subs_reversal_change_acc_no.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("bond_subs_reversal_change_acc_no.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_subs_reversal_change_acc_no/verify_bond_subs_reversal_change_acc_no_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_SUBS_REVERSAL_CHANGE_ACC_NO:VERIFY",
        },
        params: { obj: null },
        controller: "rbibondReversalChangeAccNoController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_subs_reversal_change_acc_no/bond_subs_reversal_change_acc_no.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("bond_subs_reversal_change_acc_no.delete", {
        url: "/action/delete",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_subs_reversal_change_acc_no/delete_bond_subs_reversal_change_acc_no.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_SUBS_REVERSAL_CHANGE_ACC_NO:DELETE",
        },
        controller: "rbibondReversalChangeAccNoController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_subs_reversal_change_acc_no/bond_subs_reversal_change_acc_no.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("bond_subs_reversal_change_acc_no.delete_detail", {
        url: "/action/delete/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_subs_reversal_change_acc_no/delete_bond_subs_reversal_change_acc_no_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_SUBS_REVERSAL_CHANGE_ACC_NO:DELETE",
        },
        params: { obj: null },
        controller: "rbibondReversalChangeAccNoController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_subs_reversal_change_acc_no/bond_subs_reversal_change_acc_no.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("bond_subs_reversal_change_acc_no.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_subs_reversal_change_acc_no/inquiry_bond_subs_reversal_change_acc_no.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_SUBS_REVERSAL_CHANGE_ACC_NO:INQUIRY",
        },
        controller: "rbibondReversalChangeAccNoController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_subs_reversal_change_acc_no/bond_subs_reversal_change_acc_no.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("bond_subs_reversal_change_acc_no.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_subs_reversal_change_acc_no/inquiry_bond_subs_reversal_change_acc_no_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_SUBS_REVERSAL_CHANGE_ACC_NO:INQUIRY",
        },
        params: { obj: null },
        controller: "rbibondReversalChangeAccNoController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_subs_reversal_change_acc_no/bond_subs_reversal_change_acc_no.controller.js?v=" + versionNum],
            });
          },
        },
      })
      /* bond subscription reversal change accno - end */

      // starts
      .state("pre_maturity_closure_marking", {
        abstract: true,
        url: "/pre_maturity_closure_marking",
        params: {
          views: ["add", "modify", "verify", "inquiry"],
          permissions: [
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRE_MATURITY_CLOSURE_MARKING:ADD",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRE_MATURITY_CLOSURE_MARKING:MODIFY",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRE_MATURITY_CLOSURE_MARKING:VERIFY",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRE_MATURITY_CLOSURE_MARKING:INQUIRY",
          ],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/bond_pre_maturity_closure_marking/bond_pre_maturity_closure_marking_service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("pre_maturity_closure_marking.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_pre_maturity_closure_marking/add_pre_maturity_closure_marking_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRE_MATURITY_CLOSURE_MARKING:ADD",
        },
        controller: "preMaturityClosureMarkingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_pre_maturity_closure_marking/bond_pre_maturity_closure_marking.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("pre_maturity_closure_marking.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_pre_maturity_closure_marking/add_pre_maturity_closure_marking_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRE_MATURITY_CLOSURE_MARKING:ADD",
        },
        params: { obj: null },
        controller: "preMaturityClosureMarkingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_pre_maturity_closure_marking/bond_pre_maturity_closure_marking.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("pre_maturity_closure_marking.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_pre_maturity_closure_marking/verify_pre_maturity_closure_marking_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRE_MATURITY_CLOSURE_MARKING:VERIFY",
        },
        controller: "preMaturityClosureMarkingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_pre_maturity_closure_marking/bond_pre_maturity_closure_marking.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("pre_maturity_closure_marking.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_pre_maturity_closure_marking/verify_pre_maturity_closure_marking_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRE_MATURITY_CLOSURE_MARKING:VERIFY",
        },
        params: { obj: null },
        controller: "preMaturityClosureMarkingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_pre_maturity_closure_marking/bond_pre_maturity_closure_marking.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("pre_maturity_closure_marking.modify", {
        url: "/action/modify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_pre_maturity_closure_marking/modify_pre_maturity_closure_marking_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRE_MATURITY_CLOSURE_MARKING:MODIFY",
        },
        controller: "preMaturityClosureMarkingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_pre_maturity_closure_marking/bond_pre_maturity_closure_marking.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("pre_maturity_closure_marking.modify_detail", {
        url: "/action/modify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_pre_maturity_closure_marking/modify_pre_maturity_closure_marking_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRE_MATURITY_CLOSURE_MARKING:MODIFY",
        },
        params: { obj: null },
        controller: "preMaturityClosureMarkingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_pre_maturity_closure_marking/bond_pre_maturity_closure_marking.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("pre_maturity_closure_marking.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_pre_maturity_closure_marking/inquiry_pre_maturity_closure_marking_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRE_MATURITY_CLOSURE_MARKING:INQUIRY",
        },
        controller: "preMaturityClosureMarkingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_pre_maturity_closure_marking/bond_pre_maturity_closure_marking.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("pre_maturity_closure_marking.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_pre_maturity_closure_marking/inquiry_pre_maturity_closure_marking_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRE_MATURITY_CLOSURE_MARKING:INQUIRY",
        },
        params: { obj: null },
        controller: "preMaturityClosureMarkingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_pre_maturity_closure_marking/bond_pre_maturity_closure_marking.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //end
      ////bond subscription reversal change accno ends
       ////Pan Status Update for tax Starts
            .state("rbi_bond_pan_status_update_for_tax", {
              abstract: true,
              url: "/rbi_bond_pan_status_update_for_tax",
              params: {
                views: ["upload", "download"],
                permissions: [
                  "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PAN_STATUS_UPDATE_FOR_TDS_SCREEN:UPLOAD",
                  "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PAN_STATUS_UPDATE_FOR_TDS_SCREEN:DOWNLOAD",
                ],
              },
              resolve: {
                load: function ($ocLazyLoad) {
                  return $ocLazyLoad.load({
                    files: ["app/rbi_bond_module/gu_functionallity/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_pan_status_update_for_tax/rbi_bond_pan_status_update_for_tax_service.js?v=" + versionNum],
                  });
                },
              },
            })
            .state("rbi_bond_pan_status_update_for_tax.download", {
              url: "/download",
              templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_pan_status_update_for_tax/download_rbi_bond_pan_status_update_for_tax.html",
              data: {
                authRequired: true,
                requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PAN_STATUS_UPDATE_FOR_TDS_SCREEN:DOWNLOAD",
              },
              controller: "rbiBondPanStatusUpdateForTdsController",
              controllerAs: "vm",
              resolve: {
                load: function ($ocLazyLoad) {
                  return $ocLazyLoad.load({
                    files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_pan_status_update_for_tax/rbi_bond_pan_status_update_for_tax_controller.js?v=" + versionNum],
                  });
                },
              },
            })
            .state("rbi_bond_pan_status_update_for_tax.upload", {
              url: "/action/upload_feature",
              templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_pan_status_update_for_tax/upload_rbi_bond_pan_status_update_for_tax.html",
              data: {
                authRequired: true,
                requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PAN_STATUS_UPDATE_FOR_TDS_SCREEN:UPLOAD",
              },
              controller: "rbiBondPanStatusUpdateForTdsController",
              controllerAs: "vm",
              resolve: {
                load: function ($ocLazyLoad) {
                  return $ocLazyLoad.load({
                    files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_pan_status_update_for_tax/rbi_bond_pan_status_update_for_tax_controller.js?v=" + versionNum],
                  });
                },
              },
            })
            ////Pan Status Update for tax ends
      ////NSDL Pan Validation Starts
      .state("bond_nsdl_pan_validation", {
        abstract: true,
        url: "/bond_nsdl_pan_validation",
        params: {
          views: ["api", "upload", "verify", "download", "inquiry"],
          permissions: [
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_SCREEN:API",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_SCREEN:UPLOAD",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_SCREEN:VERIFY",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_SCREEN:DOWNLOAD",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_SCREEN:ENQUIRY",
          ],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/bond_Nsdl_Pan_Validation/bond_nsdl_pan_validation_service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_nsdl_pan_validation.api", {
        url: "/api",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_Nsdl_Pan_Validation/api_bond_nsdl_pan_validation.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_SCREEN:API",
        },
        controller: "bondNsdlPanValidationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_Nsdl_Pan_Validation/bond_nsdl_pan_validation_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_nsdl_pan_validation.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_Nsdl_Pan_Validation/verify_bond_nsdl_pan_validation.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_SCREEN:VERIFY",
        },
        controller: "bondNsdlPanValidationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_Nsdl_Pan_Validation/bond_nsdl_pan_validation_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_nsdl_pan_validation.download", {
        url: "/download",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_Nsdl_Pan_Validation/download_bond_nsdl_pan_validation.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_SCREEN:DOWNLOAD",
        },
        controller: "bondNsdlPanValidationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_Nsdl_Pan_Validation/bond_nsdl_pan_validation_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_nsdl_pan_validation.upload", {
        url: "/action/upload_feature",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_Nsdl_Pan_Validation/upload_bond_nsdl_pan_validation.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_SCREEN:UPLOAD",
        },
        controller: "bondNsdlPanValidationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_Nsdl_Pan_Validation/bond_nsdl_pan_validation_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_nsdl_pan_validation.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_Nsdl_Pan_Validation/inquiry_bond_nsdl_pan_validation.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_SCREEN:ENQUIRY",
        },
        controller: "bondNsdlPanValidationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_Nsdl_Pan_Validation/bond_nsdl_pan_validation_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_nsdl_pan_validation.inquiry_details", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_Nsdl_Pan_Validation/inquiry_bond_nsdl_pan_validation_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_SCREEN:ENQUIRY",
        },
        params: { obj: null },
        controller: "bondNsdlPanValidationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_Nsdl_Pan_Validation/bond_nsdl_pan_validation_controller.js?v=" + versionNum],
            });
          },
        },
      })
      ////NSDL Pan Validation ends
      ////NSDL Pan Validation for Redemption Starts
      .state("bond_nsdl_pan_validation_redemption_on_maturity", {
        abstract: true,
        url: "/bond_nsdl_pan_validation_redemption_on_maturity",
        params: {
          views: ["api", "upload", "verify", "download", "inquiry"],
          permissions: [
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_SCREEN:API",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_SCREEN:UPLOAD",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_SCREEN:VERIFY",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_SCREEN:DOWNLOAD",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_SCREEN:ENQUIRY",
          ],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: [
                "app/rbi_bond_module/gu_functionallity/common/left-menu.directive.js",
                "app/rbi_bond_module/gu_functionallity/masters/bond_Nsdl_Pan_Validation_For_Redemption_On_Maturity/bond_nsdl_pan_validation_redemption_on_maturity_service.js?v=" + versionNum,
              ],
            });
          },
        },
      })
      .state("bond_nsdl_pan_validation_redemption_on_maturity.api", {
        url: "/api",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_Nsdl_Pan_Validation_For_Redemption_On_Maturity/api_bond_nsdl_pan_validation_redemption_on_maturity.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_SCREEN:API",
        },
        controller: "bondNsdlPanValidationRedemptionOnMaturityController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_Nsdl_Pan_Validation_For_Redemption_On_Maturity/bond_nsdl_pan_validation_redemption_on_maturity_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_nsdl_pan_validation_redemption_on_maturity.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_Nsdl_Pan_Validation_For_Redemption_On_Maturity/verify_bond_nsdl_pan_validation_redemption_on_maturity.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_SCREEN:VERIFY",
        },
        controller: "bondNsdlPanValidationRedemptionOnMaturityController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_Nsdl_Pan_Validation_For_Redemption_On_Maturity/bond_nsdl_pan_validation_redemption_on_maturity_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_nsdl_pan_validation_redemption_on_maturity.download", {
        url: "/download",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_Nsdl_Pan_Validation_For_Redemption_On_Maturity/download_bond_nsdl_pan_validation_redemption_on_maturity.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_SCREEN:DOWNLOAD",
        },
        controller: "bondNsdlPanValidationRedemptionOnMaturityController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_Nsdl_Pan_Validation_For_Redemption_On_Maturity/bond_nsdl_pan_validation_redemption_on_maturity_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_nsdl_pan_validation_redemption_on_maturity.upload", {
        url: "/action/upload_feature",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_Nsdl_Pan_Validation_For_Redemption_On_Maturity/upload_bond_nsdl_pan_validation_redemption_on_maturity.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_SCREEN:UPLOAD",
        },
        controller: "bondNsdlPanValidationRedemptionOnMaturityController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_Nsdl_Pan_Validation_For_Redemption_On_Maturity/bond_nsdl_pan_validation_redemption_on_maturity_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_nsdl_pan_validation_redemption_on_maturity.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_Nsdl_Pan_Validation_For_Redemption_On_Maturity/inquiry_bond_nsdl_pan_validation_redemption_on_maturity.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_SCREEN:ENQUIRY",
        },
        controller: "bondNsdlPanValidationRedemptionOnMaturityController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: [
                "app/rbi_bond_module/gu_functionallity/common/left-menu.directive.js",
                "app/rbi_bond_module/gu_functionallity/masters/bond_Nsdl_Pan_Validation_For_Redemption_On_Maturity/bond_nsdl_pan_validation_redemption_on_maturity_controller.js?v=" + versionNum,
              ],
            });
          },
        },
      })
      .state("bond_nsdl_pan_validation_redemption_on_maturity.inquiry_details", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_Nsdl_Pan_Validation_For_Redemption_On_Maturity/inquiry_bond_nsdl_pan_validation_redemption_on_maturity_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_SCREEN:ENQUIRY",
        },
        params: { obj: null },
        controller: "bondNsdlPanValidationRedemptionOnMaturityController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: [
                "app/rbi_bond_module/gu_functionallity/common/left-menu.directive.js",
                "app/rbi_bond_module/gu_functionallity/masters/bond_Nsdl_Pan_Validation_For_Redemption_On_Maturity/bond_nsdl_pan_validation_redemption_on_maturity_controller.js?v=" + versionNum,
              ],
            });
          },
        },
      })
      ////NSDL Pan Validation for Redemption ends
      ////cash in file upload and net file upload starts
      .state("cash_file_upload_and_net_banking_file_upload_processing", {
        abstract: true,
        url: "/cash_file_upload_and_net_banking_file_upload_processing",
        params: {
          views: ["add", "verify", "inquiry"],
          permissions: [
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CASH_FILE_UPLOAD_NET_BANKING_UPLOAD:ADD",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CASH_FILE_UPLOAD_NET_BANKING_UPLOAD:VERIFY",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CASH_FILE_UPLOAD_NET_BANKING_UPLOAD:INQUIRY",
          ],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: [
                "app/dc/common/left-menu.directive.js",
                "app/rbi_bond_module/gu_functionallity/masters/Cash_in_File_Upload_and_net_banking_file_upload_Processing/cash_in_file_upload_and_net_banking_file_upload_processing.service.js?v=" + versionNum,
              ],
            });
          },
        },
      })
      .state("cash_file_upload_and_net_banking_file_upload_processing.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/Cash_in_File_Upload_and_net_banking_file_upload_Processing/add_cash_in_file_upload_and_net_banking_file_upload_processing.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CASH_FILE_UPLOAD_NET_BANKING_UPLOAD:ADD",
        },
        controller: "cashInFileUploadAndNetBankingFileUploadController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/Cash_in_File_Upload_and_net_banking_file_upload_Processing/cash_in_file_upload_and_net_banking_file_upload_processing.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("cash_file_upload_and_net_banking_file_upload_processing.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/Cash_in_File_Upload_and_net_banking_file_upload_Processing/verify_cash_in_file_upload_and_net_banking_file_upload_processing_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CASH_FILE_UPLOAD_NET_BANKING_UPLOAD:VERIFY",
        },
        controller: "cashInFileUploadAndNetBankingFileUploadController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/Cash_in_File_Upload_and_net_banking_file_upload_Processing/cash_in_file_upload_and_net_banking_file_upload_processing.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("cash_file_upload_and_net_banking_file_upload_processing.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/Cash_in_File_Upload_and_net_banking_file_upload_Processing/inquiry_cash_in_file_upload_and_net_banking_file_upload_processing_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CASH_FILE_UPLOAD_NET_BANKING_UPLOAD:INQUIRY",
        },
        controller: "cashInFileUploadAndNetBankingFileUploadController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/Cash_in_File_Upload_and_net_banking_file_upload_Processing/cash_in_file_upload_and_net_banking_file_upload_processing.controller.js?v=" + versionNum],
            });
          },
        },
      })
      ////cash in file upload and net file upload ends

      ////Dash Board View For Physical And Inet starts
      .state("dash_board_view_for_physical_and_inet", {
        abstract: true,
        url: "/dash_board_view_for_physical_and_inet",
        params: {
          views: ["add", "verify", "inquiry"],
          permissions: [
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_DASH_BOARD_VIEW_PHYSICAL_INET:ADD",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_DASH_BOARD_VIEW_PHYSICAL_INET:VERIFY",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_DASH_BOARD_VIEW_PHYSICAL_INET:INQUIRY",
          ],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/Dash_board_view_for_physical_and_inet/dash_board_view_for_physical_and_inet.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("dash_board_view_for_physical_and_inet.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/Dash_board_view_for_physical_and_inet/add_dash_board_view_for_physical_and_inet_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_DASH_BOARD_VIEW_PHYSICAL_INET:ADD",
        },
        controller: "dashBoardViewForPhysicalAndInetController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/Dash_board_view_for_physical_and_inet/dash_board_view_for_physical_and_inet.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("dash_board_view_for_physical_and_inet.add_detail", {
        url: "/action/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/Dash_board_view_for_physical_and_inet/add_dash_board_view_for_physical_and_inet_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_DASH_BOARD_VIEW_PHYSICAL_INET:ADD",
        },
        params: { obj: null },
        controller: "dashBoardViewForPhysicalAndInetController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/Dash_board_view_for_physical_and_inet/dash_board_view_for_physical_and_inet.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("dash_board_view_for_physical_and_inet.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/Dash_board_view_for_physical_and_inet/verify_dash_board_view_for_physical_and_inet_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_DASH_BOARD_VIEW_PHYSICAL_INET:VERIFY",
        },
        controller: "dashBoardViewForPhysicalAndInetController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/Dash_board_view_for_physical_and_inet/dash_board_view_for_physical_and_inet.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("dash_board_view_for_physical_and_inet.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/Dash_board_view_for_physical_and_inet/verify_dash_board_view_for_physical_and_inet_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_DASH_BOARD_VIEW_PHYSICAL_INET:VERIFY",
        },
        params: { obj: null },
        controller: "dashBoardViewForPhysicalAndInetController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/Dash_board_view_for_physical_and_inet/dash_board_view_for_physical_and_inet.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("dash_board_view_for_physical_and_inet.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/Dash_board_view_for_physical_and_inet/inquiry_dash_board_view_for_physical_and_inet_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_DASH_BOARD_VIEW_PHYSICAL_INET:INQUIRY",
        },
        controller: "dashBoardViewForPhysicalAndInetController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/Dash_board_view_for_physical_and_inet/dash_board_view_for_physical_and_inet.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("dash_board_view_for_physical_and_inet.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/Dash_board_view_for_physical_and_inet/inquiry_dash_board_view_for_physical_and_inet_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_DASH_BOARD_VIEW_PHYSICAL_INET:INQUIRY",
        },
        params: { obj: null },
        controller: "dashBoardViewForPhysicalAndInetController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/Dash_board_view_for_physical_and_inet/dash_board_view_for_physical_and_inet.controller.js?v=" + versionNum],
            });
          },
        },
      })
      ////Dash Board View For Physical And Inet ends
      //---------------------------------------------------------------------------------------------------

      //tds_deduction_report_cb starts //
      .state("rbibond_tds_deduction_report_cb", {
        abstract: true,
        url: "/rbibond_tds_deduction_report_cb",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TDS_DEDUCTION_CB_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_tds_deduction_report_cb/rbibond_tds_deduction_report_cb.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_tds_deduction_report_cb.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_tds_deduction_report_cb/rbibond_tds_deduction_report_cb_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TDS_DEDUCTION_CB_REPORT:REPORT",
        },
        controller: "rbiBondTdsDeductionCbController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_tds_deduction_report_cb/rbibond_tds_deduction_report_cb.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_tds_deduction_report_cb.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_tds_deduction_report_cb/rbibond_tds_deduction_report_cb_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TDS_DEDUCTION_CB_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "rbiBondTdsDeductionCbController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_tds_deduction_report_cb/rbibond_tds_deduction_report_cb.controller.js?v=" + versionNum],
            });
          },
        },
      })

      //tds_deduction_report_cb ends //
      //Pan Validation ReTrigger starts //
      .state("pan_validation_retrigger", {
        abstract: true,
        url: "/pan_validation_retrigger",
        params: {
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PAN_VALIDATION_RETRIGGER:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/pan_validation_retrigger/pan_validation_retrigger.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("pan_validation_retrigger.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/pan_validation_retrigger/pan_validation_retrigger_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PAN_VALIDATION_RETRIGGER:REPORT",
        },
        controller: "panValidationRetriggerController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/pan_validation_retrigger/pan_validation_retrigger.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("pan_validation_retrigger.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/pan_validation_retrigger/pan_validation_retrigger_details.html",

        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PAN_VALIDATION_RETRIGGER:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "panValidationRetriggerController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/pan_validation_retrigger/pan_validation_retrigger.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //Pan Validation ReTrigger ends //

      //---------------------------------------------------------------------------------------------------------------------------------------

      //---------------------------------------------------------------------------------------------------

      /*rbi_bond_sub_acc_closure_before_matur*/
      .state("rbi_bond_sub_acc_closure_before_matur", {
        abstract: true,
        url: "/rbi_bond_sub_acc_closure_before_matur",
        params: {
          views: ["add", "verify", "delete", "inquiry"],
          permissions: [
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:ADD",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:VERIFY",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:DELETE",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:INQUIRY",
          ],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur/rbi_bond_sub_acc_closure_before_matur_service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_sub_acc_closure_before_matur.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur/rbi_bond_sub_acc_closure_before_matur_add.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:ADD",
        },
        controller: "bondSubAccCloseBeforeMatur",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur/rbi_bond_sub_acc_closure_before_matur_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_sub_acc_closure_before_matur.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur/rbi_bond_sub_acc_closure_before_matur_add_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:ADD",
        },
        params: { obj: null },
        controller: "bondSubAccCloseBeforeMatur",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur/rbi_bond_sub_acc_closure_before_matur_controller.js?v=" + versionNum],
            });
          },
        },
      })

      //        .state('rbi_bond_sub_acc_closure_before_matur.modify', {
      //        url: '/action/modify',
      //        templateUrl: 'app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur/rbi_bond_sub_acc_closure_before_matur_modify.html',
      //        data: {
      //        authRequired: true,
      //        requiredPermission: 'GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:ADD'
      //        },
      //        controller: 'bondSubAccCloseBeforeMatur',
      //        controllerAs: 'vm',
      //        resolve: {
      //        load: function($ocLazyLoad) {
      //        return $ocLazyLoad.load({
      //        files: ['app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur/rbi_bond_sub_acc_closure_before_matur_controller.js?v='+versionNum]
      //        });
      //        }
      //        }
      //        })
      //
      //
      //        .state('rbi_bond_sub_acc_closure_before_matur.modify_detail', {
      //        url: '/action/modify/detail',
      //        templateUrl: 'app/rbi_bond_module/gu_functionallity/masters/rbi_bond_transfer_in/rbi_bond_transfer_in_modify_detail.html',
      //        data: {
      //        authRequired: true,
      //        requiredPermission: 'GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:ADD'
      //        },
      //        params : {obj : null},
      //        controller: 'bondSubAccCloseBeforeMatur',
      //        controllerAs: 'vm',
      //        resolve: {
      //        load: function($ocLazyLoad) {
      //        return $ocLazyLoad.load({
      //        files: ['app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur/rbi_bond_sub_acc_closure_before_matur_controller.js?v='+versionNum]
      //        });
      //        }
      //        }
      //        })

      .state("rbi_bond_sub_acc_closure_before_matur.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur/rbi_bond_sub_acc_closure_before_matur_inquiry.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:INQUIRY",
        },
        controller: "bondSubAccCloseBeforeMatur",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur/rbi_bond_sub_acc_closure_before_matur_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_sub_acc_closure_before_matur.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur/rbi_bond_sub_acc_closure_before_matur_inquriy_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:INQUIRY",
        },
        params: { obj: null },
        controller: "bondSubAccCloseBeforeMatur",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur/rbi_bond_sub_acc_closure_before_matur_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_sub_acc_closure_before_matur.delete", {
        url: "/action/delete",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur/rbi_bond_sub_acc_closure_before_matur_delete.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:DELETE",
        },
        params: { obj: null },
        controller: "bondSubAccCloseBeforeMatur",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur/rbi_bond_sub_acc_closure_before_matur_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_sub_acc_closure_before_matur.delete_detail", {
        url: "/action/delete/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur/rbi_bond_sub_acc_closure_before_matur_delete_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:DELETE",
        },
        params: { obj: null },
        controller: "bondSubAccCloseBeforeMatur",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur/rbi_bond_sub_acc_closure_before_matur_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_sub_acc_closure_before_matur.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur/rbi_bond_sub_acc_closure_before_matur_verify.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:VERIFY",
        },
        controller: "bondSubAccCloseBeforeMatur",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur/rbi_bond_sub_acc_closure_before_matur_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_sub_acc_closure_before_matur.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur/rbi_bond_sub_acc_closure_before_matur_verify_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:VERIFY",
        },
        params: { obj: null },
        controller: "bondSubAccCloseBeforeMatur",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur/rbi_bond_sub_acc_closure_before_matur_controller.js?v=" + versionNum],
            });
          },
        },
      })

      //---------------------------------------------------------------------------------------------------------------------------------------

      /// BOND Subscription Account Closure Before Maturity  starts/////

      .state("BOND_Subscription_Account_Closure_Before_Maturity", {
        abstract: true,
        url: "/BOND_Subscription_Account_Closure_Before_Maturity",
        params: {
          views: ["add", "verify", "delete", "inquiry"],
          permissions: [
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:ADD",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:VERIFY",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:DELETE",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:INQUIRY",
          ],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/BOND_Subscription_Account_Closure_Before_Maturity/BOND Subscription Account Closure Before Maturity_service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("BOND_Subscription_Account_Closure_Before_Maturity.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/BOND_Subscription_Account_Closure_Before_Maturity/add_BOND Subscription Account Closure Before Maturity.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:ADD",
        },
        controller: "bondSubAccCloseBeforeMaturity",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/BOND_Subscription_Account_Closure_Before_Maturity/BOND Subscription Account Closure Before Maturity_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("BOND_Subscription_Account_Closure_Before_Maturity.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/BOND_Subscription_Account_Closure_Before_Maturity/add_BOND Subscription Account Closure Before Maturity_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:ADD",
        },
        params: { obj: null },
        controller: "bondSubAccCloseBeforeMaturity",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/BOND_Subscription_Account_Closure_Before_Maturity/BOND Subscription Account Closure Before Maturity_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("BOND_Subscription_Account_Closure_Before_Maturity.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/BOND_Subscription_Account_Closure_Before_Maturity/inquiry_BOND Subscription Account Closure Before Maturity.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:INQUIRY",
        },
        controller: "bondSubAccCloseBeforeMaturity",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/BOND_Subscription_Account_Closure_Before_Maturity/BOND Subscription Account Closure Before Maturity_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("BOND_Subscription_Account_Closure_Before_Maturity.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/BOND_Subscription_Account_Closure_Before_Maturity/inquiry_BOND Subscription Account Closure Before Maturity_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:INQUIRY",
        },
        params: { obj: null },
        controller: "bondSubAccCloseBeforeMaturity",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/BOND_Subscription_Account_Closure_Before_Maturity/BOND Subscription Account Closure Before Maturity_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("BOND_Subscription_Account_Closure_Before_Maturity.delete", {
        url: "/action/delete",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/BOND_Subscription_Account_Closure_Before_Maturity/delete_BOND Subscription Account Closure Before Maturity.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:DELETE",
        },
        params: { obj: null },
        controller: "bondSubAccCloseBeforeMaturity",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/BOND_Subscription_Account_Closure_Before_Maturity/BOND Subscription Account Closure Before Maturity_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("BOND_Subscription_Account_Closure_Before_Maturity.delete_detail", {
        url: "/action/delete/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/BOND_Subscription_Account_Closure_Before_Maturity/delete_BOND Subscription Account Closure Before Maturity_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:DELETE",
        },
        params: { obj: null },
        controller: "bondSubAccCloseBeforeMaturity",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/BOND_Subscription_Account_Closure_Before_Maturity/BOND Subscription Account Closure Before Maturity_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("BOND_Subscription_Account_Closure_Before_Maturity.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/BOND_Subscription_Account_Closure_Before_Maturity/verify_BOND Subscription Account Closure Before Maturity.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:VERIFY",
        },
        controller: "bondSubAccCloseBeforeMaturity",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/BOND_Subscription_Account_Closure_Before_Maturity/BOND Subscription Account Closure Before Maturity_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("BOND_Subscription_Account_Closure_Before_Maturity.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/BOND_Subscription_Account_Closure_Before_Maturity/verify_BOND Subscription Account Closure Before Maturity_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:VERIFY",
        },
        params: { obj: null },
        controller: "bondSubAccCloseBeforeMaturity",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/BOND_Subscription_Account_Closure_Before_Maturity/BOND Subscription Account Closure Before Maturity_controller.js?v=" + versionNum],
            });
          },
        },
      })

      ///BOND Subscription Account Closure Before Maturity ends///

      ////broker master starts
      /* code mappping Start*/
      .state("broker_master_rbi_bond", {
        abstract: true,
        url: "/broker_master_rbi_bond",
        params: {
          views: ["add", "modify", "verify", "delete", "inquiry"],
          permissions: [
            "GBM_MASTER_RBIBOND:RBIBOND_BROKER_MASTER_MAINT:ADD",
            "GBM_MASTER_RBIBOND:RBIBOND_BROKER_MASTER_MAINT:MODIFY",
            "GBM_MASTER_RBIBOND:RBIBOND_BROKER_MASTER_MAINT:VERIFY",
            "GBM_MASTER_RBIBOND:RBIBOND_BROKER_MASTER_MAINT:DELETE",
            "GBM_MASTER_RBIBOND:RBIBOND_BROKER_MASTER_MAINT:ENQUIRY",
          ],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/rbi_broker_master_maintenance/broker_master_rbi_bond.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("broker_master_rbi_bond.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_broker_master_maintenance/add_broker_master_rbi_bond.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_MASTER_RBIBOND:RBIBOND_BROKER_MASTER_MAINT:ADD",
        },
        controller: "brokerMasterRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_broker_master_maintenance/broker_master_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("broker_master_rbi_bond.modify", {
        url: "/action/modify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_broker_master_maintenance/modify_broker_master_rbi_bond.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_MASTER_RBIBOND:RBIBOND_BROKER_MASTER_MAINT:MODIFY",
        },
        controller: "brokerMasterRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_broker_master_maintenance/broker_master_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("broker_master_rbi_bond.modify_detail", {
        url: "/action/modify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_broker_master_maintenance/modify_broker_master_rbi_bond_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_MASTER_RBIBOND:RBIBOND_BROKER_MASTER_MAINT:MODIFY",
        },
        params: { obj: null },
        controller: "brokerMasterRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_broker_master_maintenance/broker_master_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("broker_master_rbi_bond.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_broker_master_maintenance/verify_broker_master_rbi_bond.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_MASTER_RBIBOND:RBIBOND_BROKER_MASTER_MAINT:VERIFY",
        },
        controller: "brokerMasterRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_broker_master_maintenance/broker_master_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("broker_master_rbi_bond.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_broker_master_maintenance/verify_broker_master_rbi_bond_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_MASTER_RBIBOND:RBIBOND_BROKER_MASTER_MAINT:VERIFY",
        },
        params: { obj: null },
        controller: "brokerMasterRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_broker_master_maintenance/broker_master_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("broker_master_rbi_bond.delete", {
        url: "/action/delete",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_broker_master_maintenance/delete_broker_master_rbi_bond.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_MASTER_RBIBOND:RBIBOND_BROKER_MASTER_MAINT:VERIFY",
        },
        controller: "brokerMasterRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_broker_master_maintenance/broker_master_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("broker_master_rbi_bond.delete_detail", {
        url: "/action/delete/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_broker_master_maintenance/delete_broker_master_rbi_bond_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_MASTER_RBIBOND:RBIBOND_BROKER_MASTER_MAINT:VERIFY",
        },
        params: { obj: null },
        controller: "brokerMasterRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_broker_master_maintenance/broker_master_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("broker_master_rbi_bond.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_broker_master_maintenance/inquiry_broker_master_rbi_bond.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_MASTER_RBIBOND:RBIBOND_BROKER_MASTER_MAINT:ENQUIRY",
        },
        controller: "brokerMasterRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_broker_master_maintenance/broker_master_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("broker_master_rbi_bond.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_broker_master_maintenance/inquiry_broker_master_rbi_bond_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_MASTER_RBIBOND:RBIBOND_BROKER_MASTER_MAINT:ENQUIRY",
        },
        params: { obj: null },
        controller: "brokerMasterRbiBondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_broker_master_maintenance/broker_master_rbi_bond.controller.js?v=" + versionNum],
            });
          },
        },
      })
      ////broker master ends

      /*  subscription workflow Starts*/
      .state("rbi_bnd_subs_workFlw", {
        abstract: true,
        url: "/rbi_bnd_subs_workFlw",
        params: {
          views: ["add", "modify", "verify", "delete", "inquiry"],
          permissions: [
            "GBM_GU_RBIBOND:RBIBOND_SUBS_WRK_FLOW_RBI_BOND:ADD",
            "GBM_GU_RBIBOND:RBIBOND_SUBS_WRK_FLOW_RBI_BOND:MODIFY",
            "GBM_GU_RBIBOND:RBIBOND_SUBS_WRK_FLOW_RBI_BOND:VERIFY",
            "GBM_GU_RBIBOND:RBIBOND_SUBS_WRK_FLOW_RBI_BOND:DELETE",
            "GBM_GU_RBIBOND:RBIBOND_SUBS_WRK_FLOW_RBI_BOND:ENQUIRY",
          ],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/subscription_workflow/subscription_workflow_service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_subs_workFlw.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/subscription_workflow/add_subscription_workflow.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_SUBS_WRK_FLOW_RBI_BOND:ADD",
        },
        controller: "rbiBondSubscriptionWorkFlowController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/subscription_workflow/subscription_workflow_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_subs_workFlw.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/subscription_workflow/add_subscription_workflow_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_SUBS_WRK_FLOW_RBI_BOND:ADD",
        },
        params: { obj: null },
        controller: "rbiBondSubscriptionWorkFlowController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/subscription_workflow/subscription_workflow_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_subs_workFlw.modify", {
        url: "/action/modify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/subscription_workflow/modify_subscription_workflow.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_SUBS_WRK_FLOW_RBI_BOND:MODIFY",
        },
        controller: "rbiBondSubscriptionWorkFlowController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/subscription_workflow/subscription_workflow_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_subs_workFlw.modify_detail", {
        url: "/action/modify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/subscription_workflow/modify_subscription_workflow_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_SUBS_WRK_FLOW_RBI_BOND:MODIFY",
        },
        params: { obj: null },
        controller: "rbiBondSubscriptionWorkFlowController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/subscription_workflow/subscription_workflow_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_subs_workFlw.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/subscription_workflow/verify_subscription_workflow.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_SUBS_WRK_FLOW_RBI_BOND:VERIFY",
        },
        controller: "rbiBondSubscriptionWorkFlowController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/subscription_workflow/subscription_workflow_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_subs_workFlw.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/subscription_workflow/verify_subscription_workflow_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_SUBS_WRK_FLOW_RBI_BOND:VERIFY",
        },
        params: { obj: null },
        controller: "rbiBondSubscriptionWorkFlowController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/subscription_workflow/subscription_workflow_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_subs_workFlw.delete", {
        url: "/action/delete",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/subscription_workflow/delete_subscription_workflow.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_SUBS_WRK_FLOW_RBI_BOND:DELETE",
        },
        controller: "rbiBondSubscriptionWorkFlowController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/subscription_workflow/subscription_workflow_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_subs_workFlw.delete_detail", {
        url: "/action/delete/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/subscription_workflow/delete_subscription_workflow_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_SUBS_WRK_FLOW_RBI_BOND:DELETE",
        },
        params: { obj: null },
        controller: "rbiBondSubscriptionWorkFlowController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/subscription_workflow/subscription_workflow_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_subs_workFlw.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/subscription_workflow/inquiry_subscription_workflow.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_SUBS_WRK_FLOW_RBI_BOND:ENQUIRY",
        },
        controller: "rbiBondSubscriptionWorkFlowController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/subscription_workflow/subscription_workflow_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_subs_workFlw.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/subscription_workflow/inquiry_subscription_workflow_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_SUBS_WRK_FLOW_RBI_BOND:ENQUIRY",
        },
        params: { obj: null },
        controller: "rbiBondSubscriptionWorkFlowController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/subscription_workflow/subscription_workflow_controller.js?v=" + versionNum],
            });
          },
        },
      })
      /* subscription workflow End*/

      /*  Bond Actual Interest Payment Starts */
      .state("bond_actual_interest_payment", {
        abstract: true,
        url: "/bond_actual_interest_payment",
        params: {
          views: ["upload", "add", "verify", "download", "inquiry"],
          permissions: [
            "GBM_GU_RBIBOND:BOND_ACTUAL_INTEREST_PAYMENT:UPLOAD",
            "GBM_GU_RBIBOND:BOND_ACTUAL_INTEREST_PAYMENT:ADD",
            "GBM_GU_RBIBOND:BOND_ACTUAL_INTEREST_PAYMENT:VERIFY",
            "GBM_GU_RBIBOND:BOND_ACTUAL_INTEREST_PAYMENT:DOWNLOAD",
            "GBM_GU_RBIBOND:BOND_ACTUAL_INTEREST_PAYMENT:ENQUIRY",
          ],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/bond_actual_interest_payment/bond_actual_interest_payment_service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_actual_interest_payment.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_actual_interest_payment/add_bond_actual_interest_payment.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:BOND_ACTUAL_INTEREST_PAYMENT:ADD",
        },
        controller: "bondActualInterestPaymentController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_actual_interest_payment/bond_actual_interest_payment_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_actual_interest_payment.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_actual_interest_payment/add_detail_bond_actual_interest_payment.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:BOND_ACTUAL_INTEREST_PAYMENT:ADD",
        },
        params: { obj: null },
        controller: "bondActualInterestPaymentController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_actual_interest_payment/bond_actual_interest_payment_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_actual_interest_payment.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_actual_interest_payment/verify_bond_actual_interest_payment.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:BOND_ACTUAL_INTEREST_PAYMENT:VERIFY",
        },
        controller: "bondActualInterestPaymentController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_actual_interest_payment/bond_actual_interest_payment_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_actual_interest_payment.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_actual_interest_payment/verify_detail_bond_actual_interest_payment.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:BOND_ACTUAL_INTEREST_PAYMENT:VERIFY",
        },
        params: { obj: null },
        controller: "bondActualInterestPaymentController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_actual_interest_payment/bond_actual_interest_payment_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_actual_interest_payment.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_actual_interest_payment/inquiry_bond_actual_interest_payment.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:BOND_ACTUAL_INTEREST_PAYMENT:ENQUIRY",
        },
        controller: "bondActualInterestPaymentController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_actual_interest_payment/bond_actual_interest_payment_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_actual_interest_payment.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_actual_interest_payment/inquiry_detail_bond_actual_interest_payment.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:BOND_ACTUAL_INTEREST_PAYMENT:ENQUIRY",
        },
        params: { obj: null },
        controller: "bondActualInterestPaymentController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_actual_interest_payment/bond_actual_interest_payment_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_actual_interest_payment.download", {
        url: "/download",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_actual_interest_payment/download_bond_actual_interest_payment.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:BOND_ACTUAL_INTEREST_PAYMENT:DOWNLOAD",
        },
        controller: "bondActualInterestPaymentController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_actual_interest_payment/bond_actual_interest_payment_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("bond_actual_interest_payment.upload_feature", {
        url: "/action/upload_feature",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_actual_interest_payment/upload_bond_actual_interest_payment.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:BOND_ACTUAL_INTEREST_PAYMENT:UPLOAD",
        },
        controller: "bondActualInterestPaymentController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_actual_interest_payment/bond_actual_interest_payment_controller.js?v=" + versionNum],
            });
          },
        },
      })
      /*  Bond Actual Interest Payment Ends*/

      /* Freeze and unFreeze Starts */

      .state("freeze_unfreeze", {
        abstract: true,
        url: "/freeze_unfreeze",
        params: {
          views: ["add", "modify", "verify", "inquiry"],
          permissions: ["GBM_GU_RBIBOND:RBIBOND_FREEZE_UNFREEZE:ADD", "GBM_GU_RBIBOND:RBIBOND_FREEZE_UNFREEZE:MODIFY", "GBM_GU_RBIBOND:RBIBOND_FREEZE_UNFREEZE:VERIFY", "GBM_GU_RBIBOND:RBIBOND_FREEZE_UNFREEZE:INQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/freeze_unfreeze/freeze_unfreeze.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("freeze_unfreeze.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/freeze_unfreeze/add_freeze_unfreeze.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_FREEZE_UNFREEZE:ADD",
        },
        controller: "freezeUnfreezeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/freeze_unfreeze/freeze_unfreeze.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("freeze_unfreeze.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/freeze_unfreeze/add_freeze_unfreeze_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_FREEZE_UNFREEZE:ADD",
        },
        params: { obj: null },
        controller: "freezeUnfreezeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/freeze_unfreeze/freeze_unfreeze.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("freeze_unfreeze.modify", {
        url: "/action/modify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/freeze_unfreeze/modify_freeze_unfreeze.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_FREEZE_UNFREEZE:MODIFY",
        },
        controller: "freezeUnfreezeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/freeze_unfreeze/freeze_unfreeze.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("freeze_unfreeze.modify_detail", {
        url: "/action/modify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/freeze_unfreeze/modify_freeze_unfreeze_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_FREEZE_UNFREEZE:MODIFY",
        },
        params: { obj: null },
        controller: "freezeUnfreezeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/freeze_unfreeze/freeze_unfreeze.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("freeze_unfreeze.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/freeze_unfreeze/verify_freeze_unfreeze.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_FREEZE_UNFREEZE:VERIFY",
        },
        controller: "freezeUnfreezeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/freeze_unfreeze/freeze_unfreeze.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("freeze_unfreeze.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/freeze_unfreeze/verify_freeze_unfreeze_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_FREEZE_UNFREEZE:VERIFY",
        },
        params: { obj: null },
        controller: "freezeUnfreezeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/freeze_unfreeze/freeze_unfreeze.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("freeze_unfreeze.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/freeze_unfreeze/inquiry_freeze_unfreeze.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_FREEZE_UNFREEZE:INQUIRY",
        },
        controller: "freezeUnfreezeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/freeze_unfreeze/freeze_unfreeze.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("freeze_unfreeze.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/freeze_unfreeze/inquiry_freeze_unfreeze_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_FREEZE_UNFREEZE:INQUIRY",
        },
        params: { obj: null },
        controller: "freezeUnfreezeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/freeze_unfreeze/freeze_unfreeze.controller.js?v=" + versionNum],
            });
          },
        },
      })
      /* Freeze and unFreeze Ends */

      /*  Bond Subscription Account Closure Before Maturity Starts */
      .state("bond_sub_acc_closure_before_maturity", {
        abstract: true,
        url: "/bond_sub_acc_closure_before_maturity",
        params: {
          views: ["upload", "add", "verify", "download", "inquiry"],
          permissions: [
            "GBM_GU_RBIBOND:BOND_SUB_ACCOUNT_CLOSURE_BEFORE_MATURITY:UPLOAD",
            "GBM_GU_RBIBOND:BOND_SUB_ACCOUNT_CLOSURE_BEFORE_MATURITY:ADD",
            "GBM_GU_RBIBOND:BOND_SUB_ACCOUNT_CLOSURE_BEFORE_MATURITY:VERIFY",
            "GBM_GU_RBIBOND:BOND_SUB_ACCOUNT_CLOSURE_BEFORE_MATURITY:DOWNLOAD",
            "GBM_GU_RBIBOND:BOND_SUB_ACCOUNT_CLOSURE_BEFORE_MATURITY:ENQUIRY",
          ],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/bond_sub_acc_closure_before_maturity/bond_sub_acc_closure_before_maturity.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_sub_acc_closure_before_maturity.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_sub_acc_closure_before_maturity/add_bond_sub_acc_closure_before_maturity.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:BOND_SUB_ACCOUNT_CLOSURE_BEFORE_MATURITY:ADD",
        },
        controller: "bondSubAccClosureBeforeMaturityController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_sub_acc_closure_before_maturity/bond_sub_acc_closure_before_maturity.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_sub_acc_closure_before_maturity.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_sub_acc_closure_before_maturity/add_details_bond_sub_acc_closure_before_maturity.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:BOND_SUB_ACCOUNT_CLOSURE_BEFORE_MATURITY:ADD",
        },
        params: { obj: null },
        controller: "bondSubAccClosureBeforeMaturityController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_sub_acc_closure_before_maturity/bond_sub_acc_closure_before_maturity.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_sub_acc_closure_before_maturity.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_sub_acc_closure_before_maturity/verify_bond_sub_acc_closure_before_maturity.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:BOND_SUB_ACCOUNT_CLOSURE_BEFORE_MATURITY:VERIFY",
        },
        controller: "bondSubAccClosureBeforeMaturityController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_sub_acc_closure_before_maturity/bond_sub_acc_closure_before_maturity.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_sub_acc_closure_before_maturity.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_sub_acc_closure_before_maturity/verify_details_bond_sub_acc_closure_before_maturity.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:BOND_SUB_ACCOUNT_CLOSURE_BEFORE_MATURITY:VERIFY",
        },
        params: { obj: null },
        controller: "bondSubAccClosureBeforeMaturityController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_sub_acc_closure_before_maturity/bond_sub_acc_closure_before_maturity.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_sub_acc_closure_before_maturity.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_sub_acc_closure_before_maturity/inquiry_bond_sub_acc_closure_before_maturity.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:BOND_SUB_ACCOUNT_CLOSURE_BEFORE_MATURITY:ENQUIRY",
        },
        controller: "bondSubAccClosureBeforeMaturityController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_sub_acc_closure_before_maturity/bond_sub_acc_closure_before_maturity.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_sub_acc_closure_before_maturity.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_sub_acc_closure_before_maturity/inquiry_details_bond_sub_acc_closure_before_maturity.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:BOND_SUB_ACCOUNT_CLOSURE_BEFORE_MATURITY:ENQUIRY",
        },
        params: { obj: null },
        controller: "bondSubAccClosureBeforeMaturityController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_sub_acc_closure_before_maturity/bond_sub_acc_closure_before_maturity.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_sub_acc_closure_before_maturity.download", {
        url: "/download",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_sub_acc_closure_before_maturity/download_bond_sub_acc_closure_before_maturity.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:BOND_SUB_ACCOUNT_CLOSURE_BEFORE_MATURITY:DOWNLOAD",
        },
        controller: "bondSubAccClosureBeforeMaturityController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_sub_acc_closure_before_maturity/bond_sub_acc_closure_before_maturity.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("bond_sub_acc_closure_before_maturity.upload_feature", {
        url: "/action/upload_feature",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_sub_acc_closure_before_maturity/upload_bond_sub_acc_closure_before_maturity.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:BOND_SUB_ACCOUNT_CLOSURE_BEFORE_MATURITY:UPLOAD",
        },
        controller: "bondSubAccClosureBeforeMaturityController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_sub_acc_closure_before_maturity/bond_sub_acc_closure_before_maturity.controller.js?v=" + versionNum],
            });
          },
        },
      })
      /* Bond Subscription Account Closure Before Maturity Ends */

      ////bond ledger acc maintenance starts
      .state("rbi_bond_ack_receipt", {
        abstract: true,
        url: "/rbi_bond_ack_receipt",
        params: {
          views: ["add", "inquiry"],
          permissions: ["GBM_GU_RBIBOND:RBIBOND_BOND_ACK_RECEIPT:ADD", "GBM_GU_RBIBOND:RBIBOND_BOND_ACK_RECEIPT:ENQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_ack_receipt/rbibond_ack_receipt.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bond_ack_receipt.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_ack_receipt/add_rbibond_ack_receipt.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BOND_ACK_RECEIPT:ADD",
        },
        controller: "rbibondAckReceiptController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_ack_receipt/rbibond_ack_receipt.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bond_ack_receipt.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_ack_receipt/add_rbibond_ack_receipt_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BOND_ACK_RECEIPT:ADD",
        },
        params: { obj: null },
        controller: "rbibondAckReceiptController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_ack_receipt/rbibond_ack_receipt.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bond_ack_receipt.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_ack_receipt/inquiry_rbibond_ack_receipt.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BOND_ACK_RECEIPT:ENQUIRY",
        },
        controller: "rbibondAckReceiptController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_ack_receipt/rbibond_ack_receipt.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bond_ack_receipt.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_ack_receipt/inquiry_rbibond_ack_receipt_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BOND_ACK_RECEIPT:ENQUIRY",
        },
        params: { obj: null },
        controller: "rbibondAckReceiptController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_ack_receipt/rbibond_ack_receipt.controller.js?v=" + versionNum],
            });
          },
        },
      })

      //bond ledger acc maintenance Ends

      ///bond nomination register report
      .state("Bond_Nomination_Register", {
        abstract: true,
        url: "/Bond_Nomination_Register",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:BOND_NOMINATION_REGISTER:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Nomination_Register/Bond_Nomination_Register.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Bond_Nomination_Register.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Nomination_Register/Bond_Nomination_Register_Input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BOND_NOMINATION_REGISTER:REPORT",
        },
        controller: "BondNominationRegisterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Nomination_Register/Bond_Nomination_Register.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Bond_Nomination_Register.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Nomination_Register/Bond_Nomination_Register_Detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BOND_NOMINATION_REGISTER:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "BondNominationRegisterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Nomination_Register/Bond_Nomination_Register.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //bond nomination register report ends///

      ////bond nominations starts
      .state("bond_nominations", {
        abstract: true,
        url: "/bond_nominations",
        params: {
          views: ["modify", "verify", "inquiry", "delete"],
          permissions: ["GBM_GU_RBIBOND:RBIBOND_BOND_NOMINATIONS:MODIFY", "GBM_GU_RBIBOND:RBIBOND_BOND_NOMINATIONS:VERIFY", "GBM_GU_RBIBOND:RBIBOND_BOND_NOMINATIONS:INQUIRY", "GBM_GU_RBIBOND:RBIBOND_BOND_NOMINATIONS:DELETE"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/bond_nominations/bond_nominations_service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_nominations.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_nominations/add_bond_nominations.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD",
        },
        controller: "bondNominationsController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_nominations/bond_nominations_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_nominations.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_nominations/add_bond_nominations_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD",
        },
        params: { obj: null },
        controller: "bondNominationsController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_nominations/bond_nominations_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_nominations.modify", {
        url: "/action/modify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_nominations/modify_bond_nominations.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BOND_NOMINATIONS:MODIFY",
        },
        controller: "bondNominationsController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_nominations/bond_nominations_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_nominations.modify_detail", {
        url: "/action/modify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_nominations/modify_bond_nominations_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BOND_NOMINATIONS:MODIFY",
        },
        params: { obj: null },
        controller: "bondNominationsController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_nominations/bond_nominations_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_nominations.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_nominations/verify_bond_nominations.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BOND_NOMINATIONS:VERIFY",
        },
        controller: "bondNominationsController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_nominations/bond_nominations_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_nominations.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_nominations/verify_bond_nominations_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BOND_NOMINATIONS:VERIFY",
        },
        params: { obj: null },
        controller: "bondNominationsController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_nominations/bond_nominations_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_nominations.delete", {
        url: "/action/delete",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_nominations/delete_bond_nominations.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BOND_NOMINATIONS:VERIFY",
        },
        controller: "bondNominationsController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_nominations/bond_nominations_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_nominations.delete_detail", {
        url: "/action/delete/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_nominations/delete_bond_nominations_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BOND_NOMINATIONS:VERIFY",
        },
        params: { obj: null },
        controller: "bondNominationsController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_nominations/bond_nominations_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_nominations.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_nominations/inquire_bond_nominations.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BOND_NOMINATIONS:INQUIRY",
        },
        controller: "bondNominationsController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_nominations/bond_nominations_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_nominations.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_nominations/inquire_bond_nominations_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BOND_NOMINATIONS:INQUIRY",
        },
        params: { obj: null },
        controller: "bondNominationsController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_nominations/bond_nominations_controller.js?v=" + versionNum],
            });
          },
        },
      })
      ////bond nominations ends

      //bond ack nomination  report starts//

      .state("rbibond_ack_nomination_report", {
        abstract: true,
        url: "/rbibond_ack_nomination_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACK_NOMINATION_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_ack_nomination_report/rbibond_ack_nomination_report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_ack_nomination_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_ack_nomination_report/rbibond_ack_noination_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACK_NOMINATION_REPORT:REPORT",
        },
        controller: "rbibondAckNominationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_ack_nomination_report/rbibond_ack_nomination_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_ack_nomination_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_ack_nomination_report/rbibond_ack_nomination_report_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACK_NOMINATION_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "rbibondAckNominationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_ack_nomination_report/rbibond_ack_nomination_report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //bond ack nomination  report ends///

      //bond notice Cancellation report starts//
      .state("rbibond_notice_cancel_report", {
        abstract: true,
        url: "/rbibond_notice_cancel_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NOTICE_CANCEL_NOMINATION_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_notice_cancel_report/rbibond_notice_cancel_report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_notice_cancel_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_notice_cancel_report/rbibond_notice_cancel_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NOTICE_CANCEL_NOMINATION_REPORT:REPORT",
        },
        controller: "rbibondNoticeCancelController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_notice_cancel_report/rbibond_notice_cancel_repot.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_notice_cancel_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_notice_cancel_report/rbibond_notice_cancel_report_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NOTICE_CANCEL_NOMINATION_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "rbibondNoticeCancelController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_notice_cancel_report/rbibond_notice_cancel_repot.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_notice_cancel_report.print_details", {
        url: "/action/print_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_notice_cancel_report/rbibond_notice_cancel_report_print.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NOTICE_CANCEL_NOMINATION_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "rbibondNoticeCancelController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_notice_cancel_report/rbibond_notice_cancel_repot.controller.js?v=" + versionNum],
            });
          },
        },
      })

      //bond notice Cancellation report ends//

      //bond notice Cancellation report starts//
      .state("rbibond_ack_cancel_nominee_report", {
        abstract: true,
        url: "/rbibond_ack_cancel_nominee_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACK_CANCEL_NOMINATION_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_ack_cancel_nominee_report/rbibond_ack_cancel_nominee_report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_ack_cancel_nominee_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_ack_cancel_nominee_report/rbibond_ack_cancel_nominee_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACK_CANCEL_NOMINATION_REPORT:REPORT",
        },
        controller: "rbibondAckCancelNomineeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_ack_cancel_nominee_report/rbibond_ack_cancel_nominee_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_ack_cancel_nominee_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_ack_cancel_nominee_report/rbibond_ack_cancel_nominee_report_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACK_CANCEL_NOMINATION_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "rbibondAckCancelNomineeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_ack_cancel_nominee_report/rbibond_ack_cancel_nominee_report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //bond notice Cancellation report ends//

      //bond certificate of investment report starts//
      .state("rbibond_certificate_of_investment_report", {
        abstract: true,
        url: "/rbibond_certificate_of_investment_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CERTIFICATE_OF_INVEST_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_certificate_of_investment_report/rbibond_certificate_of_investment.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_certificate_of_investment_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_certificate_of_investment_report/rbibond_certificate_of_investment_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CERTIFICATE_OF_INVEST_REPORT:REPORT",
        },
        controller: "rbibondCertificateInvtController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_certificate_of_investment_report/rbibond_certificate_of_investment.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_certificate_of_investment_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_certificate_of_investment_report/rbibond_certificate_of_investment_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CERTIFICATE_OF_INVEST_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "rbibondCertificateInvtController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_certificate_of_investment_report/rbibond_certificate_of_investment.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //bond certificate of investment report ends//

      .state("List_of_Bond_Investments_Pending_Receipt_of_CAS_Advice_No_report", {
        abstract: true,
        url: "/List_of_Bond_Investments_Pending_Receipt_of_CAS_Advice_No_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:LIST_OF_BOND_INVESTMENTS_PENDING_RECEIPT_OF_CAS_ADVICE_NO_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/List_of_Bond_Investments_Pending_Receipt_of_CAS_Advice_No_report/List_of_Bond_Investments_Pending_Receipt_of_CAS_Advice_No_report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("List_of_Bond_Investments_Pending_Receipt_of_CAS_Advice_No_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/List_of_Bond_Investments_Pending_Receipt_of_CAS_Advice_No_report/List_of_Bond_Investments_Pending_Receipt_of_CAS_Advice_No_report_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:LIST_OF_BOND_INVESTMENTS_PENDING_RECEIPT_OF_CAS_ADVICE_NO_REPORT:REPORT",
        },
        controller: "listofbondinvestmentsController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/List_of_Bond_Investments_Pending_Receipt_of_CAS_Advice_No_report/List_of_Bond_Investments_Pending_Receipt_of_CAS_Advice_No_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("List_of_Bond_Investments_Pending_Receipt_of_CAS_Advice_No_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/List_of_Bond_Investments_Pending_Receipt_of_CAS_Advice_No_report/List_of_Bond_Investments_Pending_Receipt_of_CAS_Advice_No_report_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:LIST_OF_BOND_INVESTMENTS_PENDING_RECEIPT_OF_CAS_ADVICE_NO_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "listofbondinvestmentsController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/List_of_Bond_Investments_Pending_Receipt_of_CAS_Advice_No_report/List_of_Bond_Investments_Pending_Receipt_of_CAS_Advice_No_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      //bond certificate of investment Hold report starts//
      .state("rbibond_certificate_investment_hold_report", {
        abstract: true,
        url: "/rbibond_certificate_investment_hold_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CERTIFICATE_OF_HOLD_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_certificate_investment_hold_report/rbibond_certificate_investment_hold_report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_certificate_investment_hold_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_certificate_investment_hold_report/rbibond_certificate_investment_hold_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CERTIFICATE_OF_HOLD_REPORT:REPORT",
        },
        controller: "CertificateOfHoldingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_certificate_investment_hold_report/rbibond_certificate_investment_hold_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_certificate_investment_hold_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_certificate_investment_hold_report/rbibond_certificate_investment_hold_report_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CERTIFICATE_OF_HOLD_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "CertificateOfHoldingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_certificate_investment_hold_report/rbibond_certificate_investment_hold_report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_certificate_investment_hold_report.print_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_certificate_investment_hold_report/rbibond_certificate_investment_hold_report_print.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CERTIFICATE_OF_HOLD_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "CertificateOfHoldingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_certificate_investment_hold_report/rbibond_certificate_investment_hold_report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //bond certificate of investment Hold report ends//

      //bond transfer from same bank report starts//
      .state("rbibond_transfer_same_bank_report", {
        abstract: true,
        url: "/rbibond_transfer_same_bank_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TRANSFER_SAME_BANK_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_transfer_same_bank_report/rbibond_transfer_same_bank.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_transfer_same_bank_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_transfer_same_bank_report/rbibond_transfer_same_bank_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TRANSFER_SAME_BANK_REPORT:REPORT",
        },
        controller: "rbibondTranSameBankController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_transfer_same_bank_report/rbibond_transfer_same_bank.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_transfer_same_bank_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_transfer_same_bank_report/rbibond_transfer_same_bank_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TRANSFER_SAME_BANK_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "rbibondTranSameBankController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_transfer_same_bank_report/rbibond_transfer_same_bank.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //bond transfer from same bank report ends//

      .state("BLA_statement_for_Bondholders_report", {
        abstract: true,
        url: "/BLA_statement_for_Bondholders_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:BLA_STATEMENT_FOR_BONDHOLDERS_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/BLA_statement_for_Bondholders_report/BLA_statement_for_Bondholders_report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("BLA_statement_for_Bondholders_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/BLA_statement_for_Bondholders_report/BLA_statement_for_Bondholders_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BLA_STATEMENT_FOR_BONDHOLDERS_REPORT:REPORT",
        },
        controller: "blaStatementBondHolderController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/BLA_statement_for_Bondholders_report/BLA_statement_for_Bondholders_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("BLA_statement_for_Bondholders_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/BLA_statement_for_Bondholders_report/BLA_statement_for_Bondholders_report_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BLA_STATEMENT_FOR_BONDHOLDERS_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "blaStatementBondHolderController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/BLA_statement_for_Bondholders_report/BLA_statement_for_Bondholders_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("BLA_Status_Report", {
        abstract: true,
        url: "/BLA_Status_Report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:BLA_STATUS_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/BLA_Status_Report/BLA_Status_Report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("BLA_Status_Report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/BLA_Status_Report/BLA_Status_Report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BLA_STATUS_REPORT:REPORT",
        },
        controller: "blaStatusReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/BLA_Status_Report/BLA_Status_Report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("BLA_Status_Report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/BLA_Status_Report/BLA_Status_Report_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BLA_STATUS_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "blaStatusReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/BLA_Status_Report/BLA_Status_Report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("Bond_Balance_Report", {
        abstract: true,
        url: "/Bond_Balance_Report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:BOND_BALANCE_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Balance_Report/Bond_Balance_Report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("Bond_Balance_Report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Balance_Report/Bond_Balance_Report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BOND_BALANCE_REPORT:REPORT",
        },
        controller: "bondbalanceReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Balance_Report/Bond_Balance_Report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("Bond_Balance_Report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Balance_Report/Bond_Balance_Report_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BOND_BALANCE_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "bondbalanceReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Balance_Report/Bond_Balance_Report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("Report_on_Transaction_Details_for_Bond", {
        abstract: true,
        url: "/Report_on_Transaction_Details_for_Bond",
        params: {
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:REPORT_ON_TRANSACTION_DETAILS_FOR_BOND:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Report_on_Transaction_Details_for_Bond/Report_on_Transaction_Details_for_Bond.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("Report_on_Transaction_Details_for_Bond.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Report_on_Transaction_Details_for_Bond/Report_on_Transaction_Details_for_Bond_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:REPORT_ON_TRANSACTION_DETAILS_FOR_BOND:REPORT",
        },
        controller: "ReportTransactionDetailsController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Report_on_Transaction_Details_for_Bond/Report_on_Transaction_Details_for_Bond.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("Report_on_Transaction_Details_for_Bond.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Report_on_Transaction_Details_for_Bond/Report_on_Transaction_Details_for_Bond_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:REPORT_ON_TRANSACTION_DETAILS_FOR_BOND:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "ReportTransactionDetailsController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Report_on_Transaction_Details_for_Bond/Report_on_Transaction_Details_for_Bond.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("Bond_Investment_Due_Date_Notice_report", {
        abstract: true,
        url: "/Bond_Investment_Due_Date_Notice_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:BOND_INVESTMENT_DUE_DATE_NOTICE_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Investment_Due_Date_Notice_report/Bond_Investment_Due_Date_Notice_report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("Bond_Investment_Due_Date_Notice_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Investment_Due_Date_Notice_report/Bond_Investment_Due_Date_Notice_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BOND_INVESTMENT_DUE_DATE_NOTICE_REPORT:REPORT",
        },
        controller: "BondInvestDueDateNoticeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Investment_Due_Date_Notice_report/Bond_Investment_Due_Date_Notice_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("Bond_Investment_Due_Date_Notice_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Investment_Due_Date_Notice_report/Bond_Investment_Due_Date_Notice_report_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BOND_INVESTMENT_DUE_DATE_NOTICE_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "BondInvestDueDateNoticeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Investment_Due_Date_Notice_report/Bond_Investment_Due_Date_Notice_report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      // start of the Mode of holding change - report
      .state("Mode_Of_Holding_Change_Report", {
        abstract: true,
        url: "/Mode_Of_Holding_Change_Report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:MODE_OF_HOLDING_CHANGE_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Mode_of_Holding_Change_Report/Mode_of_Holding_Change_Report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("Mode_Of_Holding_Change_Report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Mode_of_Holding_Change_Report/Mode_of_Holding_Change_Report.input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:MODE_OF_HOLDING_CHANGE_REPORT:REPORT",
        },
        controller: "ModeOfHoldingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Mode_of_Holding_Change_Report/Mode_of_Holding_Change_Report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("Mode_Of_Holding_Change_Report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Mode_of_Holding_Change_Report/Mode_of_Holding_Change_Report.details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:MODE_OF_HOLDING_CHANGE_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "ModeOfHoldingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Mode_of_Holding_Change_Report/Mode_of_Holding_Change_Report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      // end of the mode of holding change - report
      // starts the bond calculated collecting branch//

      .state("Bond_Interest_NOT_Calculated_Report_collecting_branch", {
        abstract: true,
        url: "/Bond_Interest_NOT_Calculated_Report_collecting_branch",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:BOND_INTEREST_NOT_CALCULATED_REPORT_COLLECTING_BRANCH:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Interest_NOT_Calculated_Report_collecting_branch/Bond_Interest_NOT_Calculated_Report_collecting_branch.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("Bond_Interest_NOT_Calculated_Report_collecting_branch.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Interest_NOT_Calculated_Report_collecting_branch/Bond_Interest_NOT_Calculated_Report_collecting_branch_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BOND_INTEREST_NOT_CALCULATED_REPORT_COLLECTING_BRANCH:REPORT",
        },
        controller: "bondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Interest_NOT_Calculated_Report_collecting_branch/Bond_Interest_NOT_Calculated_Report_collecting_branch.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("Bond_Interest_NOT_Calculated_Report_collecting_branch.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Interest_NOT_Calculated_Report_collecting_branch/Bond_Interest_NOT_Calculated_Report_collecting_branch_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BOND_INTEREST_NOT_CALCULATED_REPORT_COLLECTING_BRANCH:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "bondController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Interest_NOT_Calculated_Report_collecting_branch/Bond_Interest_NOT_Calculated_Report_collecting_branch.controller.js?v=" + versionNum],
            });
          },
        },
      })
      // ends the bond calculated collecting branch//

      // starts of the statement of brokerage paid///
      .state("Statement_of_Brokerage_Paid_report", {
        abstract: true,
        url: "/Statement_of_Brokerage_Paid_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:STATEMENT_OF_BROKERAGE_PAID_AND_UNPAID:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Statement_of_Brokerage_Paid_report/Statement_of_Brokerage_Paid_report.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Statement_of_Brokerage_Paid_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Statement_of_Brokerage_Paid_report/Statement_of_Brokerage_Paid_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:STATEMENT_OF_BROKERAGE_PAID_AND_UNPAID:REPORT",
        },
        controller: "StatementOfBrokerageController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Statement_of_Brokerage_Paid_report/Statement_of_Brokerage_Paid_report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Statement_of_Brokerage_Paid_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Statement_of_Brokerage_Paid_report/Statement_of_Brokerage_Paid_report_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:STATEMENT_OF_BROKERAGE_PAID_AND_UNPAID:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "StatementOfBrokerageController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Statement_of_Brokerage_Paid_report/Statement_of_Brokerage_Paid_report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      /// ends the statement of brokerage paid///

      //start of Interest Payment to Other Bank Report(Non-hdfc)

      .state("interest_payment_to_other_bank_report", {
        abstract: true,
        url: "/interest_payment_to_other_bank_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:INTEREST_PAYMENT_TO_OTHER_BANK_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Interest_Payment_to_Other_Bank_Report (Non-HDFC)/Interest_Payment_to_Other_Bank_Report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("interest_payment_to_other_bank_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Interest_Payment_to_Other_Bank_Report (Non-HDFC)/Interest_Payment_to_Other_Bank_Report.input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:INTEREST_PAYMENT_TO_OTHER_BANK_REPORT:REPORT",
        },
        controller: "interestpaymentReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Interest_Payment_to_Other_Bank_Report (Non-HDFC)/Interest_Payment_to_Other_Bank_Report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("interest_payment_to_other_bank_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Interest_Payment_to_Other_Bank_Report (Non-HDFC)/Interest_Payment_to_Other_Bank_Report.details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:INTEREST_PAYMENT_TO_OTHER_BANK_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "interestpaymentReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Interest_Payment_to_Other_Bank_Report (Non-HDFC)/Interest_Payment_to_Other_Bank_Report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      //End of Interest Payment to Other Bank Report(Non-hdfc)

      ///bla modification report//

      .state("bla_modification_report", {
        abstract: true,
        url: "/bla_modification_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:BLA_MODIFICATION_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/BLA_Modificatin_Report/Bla_Modification_Report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("bla_modification_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/BLA_Modificatin_Report/Bla_Modification_Report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BLA_MODIFICATION_REPORT:REPORT",
        },
        controller: "blaModificationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/BLA_Modificatin_Report/Bla_Modification_Report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("bla_modification_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/BLA_Modificatin_Report/Bla_Modification_Report_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BLA_MODIFICATION_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "blaModificationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/BLA_Modificatin_Report/Bla_Modification_Report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //end of bla modification report//

      ///start of Report on Pan-aadhar non-linkage//

      .state("report_on_pan_aadhar", {
        abstract: true,
        url: "/report_on_pan_aadhar",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:REPORT_ON_PAN_AADHAR:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Report_on_PAN_Aadhaar_non_linkage_Invalid_PAN/Report_on_PAN_Aadhaar_non_linkage_Invalid_PAN.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("report_on_pan_aadhar.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Report_on_PAN_Aadhaar_non_linkage_Invalid_PAN/Report_on_PAN_Aadhaar_non_linkage_Invalid_PAN_Input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:REPORT_ON_PAN_AADHAR:REPORT",
        },
        controller: "ReportOnPanAadharController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Report_on_PAN_Aadhaar_non_linkage_Invalid_PAN/Report_on_PAN_Aadhaar_non_linkage_Invalid_PAN.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("report_on_pan_aadhar.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Report_on_PAN_Aadhaar_non_linkage_Invalid_PAN/Report_on_PAN_Aadhaar_non_linkage_Invalid_PAN_Details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:REPORT_ON_PAN_AADHAR:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "ReportOnPanAadharController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Report_on_PAN_Aadhaar_non_linkage_Invalid_PAN/Report_on_PAN_Aadhaar_non_linkage_Invalid_PAN.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //end of Report on Pan-aadhar non-linkage//

      ///start of the bond interest trial run-eligible accounts report//

      .state("bond_interest_trial_run_eligible_accounts_report", {
        abstract: true,
        url: "/bond_interest_trial_run_eligible_accounts_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:BOND_INTEREST_TRIAL_RUN_ELIGIBLE_ACCOUNTS_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Interest_Trial_Run_Eligible_Accounts_Report/Bond_Interest_Trial_Run_Eligible_Accounts_Report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("bond_interest_trial_run_eligible_accounts_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Interest_Trial_Run_Eligible_Accounts_Report/Bond_Interest_Trial_Run_Eligible_Accounts_Report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BOND_INTEREST_TRIAL_RUN_ELIGIBLE_ACCOUNTS_REPORT:REPORT",
        },
        controller: "bondInterestTrialRunController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Interest_Trial_Run_Eligible_Accounts_Report/Bond_Interest_Trial_Run_Eligible_Accounts_Report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("bond_interest_trial_run_eligible_accounts_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Interest_Trial_Run_Eligible_Accounts_Report/Bond_Interest_Trial_Run_Eligible_Accounts_Report_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BOND_INTEREST_TRIAL_RUN_ELIGIBLE_ACCOUNTS_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "bondInterestTrialRunController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Interest_Trial_Run_Eligible_Accounts_Report/Bond_Interest_Trial_Run_Eligible_Accounts_Report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //end of the bond interest trial run-eligible accounts report//

      ///start of tds summary for tax audit//

      .state("tds_summary_for_tax_audit", {
        abstract: true,
        url: "/tds_summary_for_tax_audit",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:TDS_SUMMARY_TAX_AUDIT_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/TDS_Summary_for_Tax_Audit/TDS_Summary_For_Tax Audit.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("tds_summary_for_tax_audit.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/TDS_Summary_for_Tax_Audit/TDS_Summary_For_Tax Audit.input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:TDS_SUMMARY_TAX_AUDIT_REPORT:REPORT",
        },
        controller: "tdsSummaryTaxAuditController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/TDS_Summary_for_Tax_Audit/TDS_Summary_For_Tax Audit.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("tds_summary_for_tax_audit.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/TDS_Summary_for_Tax_Audit/TDS_Summary_For_Tax Audit.details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:TDS_SUMMARY_TAX_AUDIT_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "tdsSummaryTaxAuditController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/TDS_Summary_for_Tax_Audit/TDS_Summary_For_Tax Audit.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //end of the start of tds summary for tax audit//

      ///start of the success failure for brokerage payment//
      ///start of the success failure for brokerage payment//

      .state("success_failure_report_for_brokerage_payment", {
        abstract: true,
        url: "/success_failure_report_for_brokerage_payment",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:SUCCESS_FAILURE_FOR_BROKERAGE_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/success_failure_report_for_brokerage_payment/success_failure_report_for_brokerage_payment.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("success_failure_report_for_brokerage_payment.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/success_failure_report_for_brokerage_payment/success_failure_report_for_brokerage_payment_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:SUCCESS_FAILURE_FOR_BROKERAGE_REPORT:REPORT",
        },
        controller: "successFailureBrokerageController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/success_failure_report_for_brokerage_payment/success_failure_report_for_brokerage_payment.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("success_failure_report_for_brokerage_payment.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/success_failure_report_for_brokerage_payment/success_failure_report_for_brokerage_payment_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:SUCCESS_FAILURE_FOR_BROKERAGE_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "successFailureBrokerageController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/success_failure_report_for_brokerage_payment/success_failure_report_for_brokerage_payment.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //end of the success failure report brokerage payment//
      // starts of the ststement of brokerage paid///

      //unlock Bla
      .state("unlock_bla", {
        abstract: true,
        url: "/unlock_bla",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:UNLOCK_BLA:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/unlock_bla/unlock_bla.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("unlock_bla.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/unlock_bla/unlock_bla_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:UNLOCK_BLA:REPORT",
        },
        controller: "UnlockBlaController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/unlock_bla/unlock_bla.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("unlock_bla.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/unlock_bla/unlock_bla_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:UNLOCK_BLA:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "UnlockBlaController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/unlock_bla/unlock_bla.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /*  rbi bond acc transfer out Starts*/
      .state("rbi_bnd_acc_trans_out", {
        abstract: true,
        url: "/rbi_bnd_acc_trans_out",
        params: {
          views: ["add", "verify", "delete", "inquiry"],
          permissions: ["GBM_GU_RBIBOND:RBIBOND_ACC_TRN_OUT_RBI_BOND:ADD", "GBM_GU_RBIBOND:RBIBOND_ACC_TRN_OUT_RBI_BOND:VERIFY", "GBM_GU_RBIBOND:RBIBOND_ACC_TRN_OUT_RBI_BOND:VERIFY", "GBM_GU_RBIBOND:RBIBOND_ACC_TRN_OUT_RBI_BOND:ENQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/bond_acc_transfer_out/bond_acc_transfer_out_service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_acc_trans_out.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_acc_transfer_out/add_bond_acc_transfer_out.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_ACC_TRN_OUT_RBI_BOND:ADD",
        },
        controller: "rbiBondAccTransferOutController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_acc_transfer_out/bond_acc_transfer_out_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_acc_trans_out.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_acc_transfer_out/add_bond_acc_transfer_out_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_ACC_TRN_OUT_RBI_BOND:ADD",
        },
        params: { obj: null },
        controller: "rbiBondAccTransferOutController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_acc_transfer_out/bond_acc_transfer_out_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bnd_acc_trans_out.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_acc_transfer_out/verify_bond_acc_transfer_out.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_ACC_TRN_OUT_RBI_BOND:VERIFY",
        },
        controller: "rbiBondAccTransferOutController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_acc_transfer_out/bond_acc_transfer_out_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_acc_trans_out.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_acc_transfer_out/verify_bond_acc_transfer_out_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_ACC_TRN_OUT_RBI_BOND:VERIFY",
        },
        params: { obj: null },
        controller: "rbiBondAccTransferOutController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_acc_transfer_out/bond_acc_transfer_out_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_acc_trans_out.delete", {
        url: "/action/delete",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_acc_transfer_out/delete_bond_acc_transfer_out.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_ACC_TRN_OUT_RBI_BOND:VERIFY",
        },
        controller: "rbiBondAccTransferOutController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_acc_transfer_out/bond_acc_transfer_out_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_acc_trans_out.delete_detail", {
        url: "/action/delete/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_acc_transfer_out/delete_bond_acc_transfer_out_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_ACC_TRN_OUT_RBI_BOND:VERIFY",
        },
        params: { obj: null },
        controller: "rbiBondAccTransferOutController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_acc_transfer_out/bond_acc_transfer_out_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_acc_trans_out.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_acc_transfer_out/inquiry_bond_acc_transfer_out.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_ACC_TRN_OUT_RBI_BOND:ENQUIRY",
        },
        controller: "rbiBondAccTransferOutController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_acc_transfer_out/bond_acc_transfer_out_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_acc_trans_out.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_acc_transfer_out/inquiry_bond_acc_transfer_out_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_ACC_TRN_OUT_RBI_BOND:ENQUIRY",
        },
        params: { obj: null },
        controller: "rbiBondAccTransferOutController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_acc_transfer_out/bond_acc_transfer_out_controller.js?v=" + versionNum],
            });
          },
        },
      })
      /*  rbi bond acc transfer out End*/

      /*cac ack number maintenance starts*/

      .state("cas_acknowledgement_number_maintenance", {
        abstract: true,
        url: "/cas_acknowledgement_number_maintenance",
        params: {
          views: ["add", "verify", "inquiry"],
          permissions: ["GBM_GU_RBIBOND:RBIBOND_CAS_ACK_NUM_MAINTENANCE:ADD", "GBM_GU_RBIBOND:RBIBOND_CAS_ACK_NUM_MAINTENANCE:VERIFY", "GBM_GU_RBIBOND:RBIBOND_CAS_ACK_NUM_MAINTENANCE:ENQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/cas_acknowledgement_number_maintenance/cas_acknowledgement_number_maintenance_service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("cas_acknowledgement_number_maintenance.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/cas_acknowledgement_number_maintenance/add_cas_acknowledgement_number_maintenance.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_CAS_ACK_NUM_MAINTENANCE:ADD",
        },
        controller: "casAckNumMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/cas_acknowledgement_number_maintenance/cas_acknowledgement_number_maintenance_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("cas_acknowledgement_number_maintenance.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/cas_acknowledgement_number_maintenance/add_cas_acknowledgement_number_maintenance_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_CAS_ACK_NUM_MAINTENANCE:ADD",
        },
        params: { obj: null },
        controller: "casAckNumMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/cas_acknowledgement_number_maintenance/cas_acknowledgement_number_maintenance_controller.js?v=" + versionNum],
            });
          },
        },
      })
      //                    .state('cas_acknowledgement_number_maintenance.modify', {
      //                        url: '/action/modify',
      //                        templateUrl: 'app/rbi_bond_module/gu_functionallity/masters/cas_acknowledgement_number_maintenance/modify_cas_acknowledgement_number_maintenance.html',
      //                        data: {
      //                            authRequired: true,
      //                            requiredPermission: 'GBM_GU_RBIBOND:RBIBOND_CAS_ACK_NUM_MAINTENANCE:MODIFY'
      //                        },
      //                        controller: 'casAckNumMaintenanceController',
      //                        controllerAs: 'vm',
      //                        resolve: {
      //                            load: function($ocLazyLoad) {
      //                                return $ocLazyLoad.load({
      //                                    files: ['app/rbi_bond_module/gu_functionallity/masters/cas_acknowledgement_number_maintenance/cas_acknowledgement_number_maintenance_controller.js?v='+versionNum]
      //                                });
      //                            }
      //                        }
      //                    })
      //                    .state('cas_acknowledgement_number_maintenance.modify_detail', {
      //                        url: '/action/modify/detail',
      //                        templateUrl: 'app/rbi_bond_module/gu_functionallity/masters/cas_acknowledgement_number_maintenance/modify_cas_acknowledgement_number_maintenance_detail.html',
      //                        data: {
      //                            authRequired: true,
      //                            requiredPermission: 'GBM_GU_RBIBOND:RBIBOND_CAS_ACK_NUM_MAINTENANCE:MODIFY'
      //                        },
      //                        params: {obj: null},
      //                        controller: 'casAckNumMaintenanceController',
      //                        controllerAs: 'vm',
      //                        resolve: {
      //                            load: function($ocLazyLoad) {
      //                                return $ocLazyLoad.load({
      //                                    files: ['app/rbi_bond_module/gu_functionallity/masters/cas_acknowledgement_number_maintenance/cas_acknowledgement_number_maintenance_controller.js?v='+versionNum]
      //                                });
      //                            }
      //                        }
      //                    })
      .state("cas_acknowledgement_number_maintenance.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/cas_acknowledgement_number_maintenance/verify_cas_acknowledgement_number_maintenance.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_CAS_ACK_NUM_MAINTENANCE:VERIFY",
        },
        controller: "casAckNumMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/cas_acknowledgement_number_maintenance/cas_acknowledgement_number_maintenance_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("cas_acknowledgement_number_maintenance.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/cas_acknowledgement_number_maintenance/verify_cas_acknowledgement_number_maintenance_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_CAS_ACK_NUM_MAINTENANCE:VERIFY",
        },
        params: { obj: null },
        controller: "casAckNumMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/cas_acknowledgement_number_maintenance/cas_acknowledgement_number_maintenance_controller.js?v=" + versionNum],
            });
          },
        },
      })
      //                    .state('cas_acknowledgement_number_maintenance.delete', {
      //                        url: '/action/delete',
      //                        templateUrl: 'app/rbi_bond_module/gu_functionallity/masters/cas_acknowledgement_number_maintenance/delete_cas_acknowledgement_number_maintenance.html',
      //                        data: {
      //                            authRequired: true,
      //                            requiredPermission: 'GBM_GU_RBIBOND:RBIBOND_CAS_ACK_NUM_MAINTENANCE:DELETE'
      //                        },
      //                        controller: 'casAckNumMaintenanceController',
      //                        controllerAs: 'vm',
      //                        resolve: {
      //                            load: function($ocLazyLoad) {
      //                                return $ocLazyLoad.load({
      //                                    files: ['app/rbi_bond_module/gu_functionallity/masters/cas_acknowledgement_number_maintenance/cas_acknowledgement_number_maintenance_controller.js?v='+versionNum]
      //                                });
      //                            }
      //                        }
      //                    })
      //                    .state('cas_acknowledgement_number_maintenance.delete_detail', {
      //                        url: '/action/delete/detail',
      //                        templateUrl: 'app/rbi_bond_module/gu_functionallity/masters/cas_acknowledgement_number_maintenance/delete_cas_acknowledgement_number_maintenance_detail.html',
      //                        data: {
      //                            authRequired: true,
      //                            requiredPermission: 'GBM_GU_RBIBOND:RBIBOND_CAS_ACK_NUM_MAINTENANCE:DELETE'
      //                        },
      //                        params: {obj: null},
      //                        controller: 'casAckNumMaintenanceController',
      //                        controllerAs: 'vm',
      //                        resolve: {
      //                            load: function($ocLazyLoad) {
      //                                return $ocLazyLoad.load({
      //                                    files: ['app/rbi_bond_module/gu_functionallity/masters/cas_acknowledgement_number_maintenance/cas_acknowledgement_number_maintenance_controller.js?v='+versionNum]
      //                                });
      //                            }
      //                        }
      //                    })
      .state("cas_acknowledgement_number_maintenance.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/cas_acknowledgement_number_maintenance/inquiry_cas_acknowledgement_number_maintenance.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_CAS_ACK_NUM_MAINTENANCE:ENQUIRY",
        },
        controller: "casAckNumMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/cas_acknowledgement_number_maintenance/cas_acknowledgement_number_maintenance_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("cas_acknowledgement_number_maintenance.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/cas_acknowledgement_number_maintenance/inquiry_cas_acknowledgement_number_maintenance_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_CAS_ACK_NUM_MAINTENANCE:ENQUIRY",
        },
        params: { obj: null },
        controller: "casAckNumMaintenanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/cas_acknowledgement_number_maintenance/cas_acknowledgement_number_maintenance_controller.js?v=" + versionNum],
            });
          },
        },
      })

      //
      //                                           /*UnverifiedRecordLc starts*/
      //                                                   .state('unverified_records_for_rbi_bond_lc', {
      //                                                                                  abstract: true,
      //                                                                                  url: '/unverified_records_for_rbi_bond_lc',
      //                                                                                  params: {
      //                                                                                      views: ['inquiry'],
      //                                                                                      permissions: ['GBM_GU_RBIBOND:UNVERIFIED_RECORD_LC:ENQUIRY']
      //                                                                                  },
      //                                                                                  resolve: {
      //                                                                                      load: function($ocLazyLoad) {
      //                                                                                          return $ocLazyLoad.load({
      //                                                                                              files: [
      //                                                                                                  'app/dc/common/left-menu.directive.js',
      //                                                                                                  'app/rbi_bond_module/gu_functionallity/reports/unverified_records_for_rbi_bond_lc/unverified_records_for_rbi_bond_lc.service.js?v='+versionNum
      //                                                                                              ]
      //                                                                                          });
      //                                                                                      }
      //                                                                                  }
      //                                                                              })
      //                                                   .state('unverified_records_for_rbi_bond_lc.inquiry', {
      //                                                                                  url: '/action/inquiry',
      //                                                                                  templateUrl: 'app/rbi_bond_module/gu_functionallity/reports/unverified_records_for_rbi_bond_lc/unverified_records_for_rbi_bond_lc.html',
      //                                                                                  data: {
      //                                                                                      authRequired: true,
      //                                                                                      requiredPermission: 'GBM_GU_RBIBOND:UNVERIFIED_RECORD_LC:ENQUIRY'
      //                                                                                  },
      //                                                                                  controller: 'unverifiedRecordReportLcController',
      //                                                                                  controllerAs: 'vm',
      //                                                                                  resolve: {
      //                                                                                      load: function($ocLazyLoad) {
      //                                                                                          return $ocLazyLoad.load({
      //                                                                                              files: ['app/rbi_bond_module/gu_functionallity/reports/unverified_records_for_rbi_bond_lc/unverified_records_for_rbi_bond_lc.controller.js?v='+versionNum]
      //                                                                                          });
      //                                                                                      }
      //                                                                                  }
      //                                                                              })
      //                                                   .state('unverified_records_for_rbi_bond_lc.inquiry_detail', {
      //                                                                                  url: '/action/inquiry/detail',
      //                                                                                  templateUrl: 'app/rbi_bond_module/gu_functionallity/reports/unverified_records_for_rbi_bond_lc/unverified_records_for_rbi_bond_lc_detail.html',
      //                                                                                  data: {
      //                                                                                      authRequired: true,
      //                                                                                      requiredPermission: 'GBM_GU_RBIBOND:UNVERIFIED_RECORD_LC:ENQUIRY'
      //                                                                                  },
      //                                                                                  params : {obj : null},
      //                                                                                  controller: 'unverifiedRecordReportLcController',
      //                                                                                  controllerAs: 'vm',
      //                                                                                  resolve: {
      //                                                                                      load: function($ocLazyLoad) {
      //                                                                                          return $ocLazyLoad.load({
      //                                                                                              files: ['app/rbi_bond_module/gu_functionallity/reports/unverified_records_for_rbi_bond_lc/unverified_records_for_rbi_bond_lc.controller.js?v='+versionNum]
      //                                                                                          });
      //                                                                                      }
      //                                                                                  }
      //                                                                              })

      /*UnverifiedRecordLc starts*/
      .state("unverified_records_for_rbi_bond_lc", {
        abstract: true,
        url: "/unverified_records_for_rbi_bond_lc",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_GU_RBIBOND:UNVERIFIED_RECORD_LC:ENQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/reports/unverified_records_for_rbi_bond_lc/unverified_records_for_rbi_bond_lc.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("unverified_records_for_rbi_bond_lc.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/unverified_records_for_rbi_bond_lc/unverified_records_for_rbi_bond_lc.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:UNVERIFIED_RECORD_LC:ENQUIRY",
        },
        controller: "unverifiedRecordReportLcController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/unverified_records_for_rbi_bond_lc/unverified_records_for_rbi_bond_lc.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("unverified_records_for_rbi_bond_lc.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/unverified_records_for_rbi_bond_lc/unverified_records_for_rbi_bond_lc_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:UNVERIFIED_RECORD_LC:ENQUIRY",
        },
        params: { obj: null },
        controller: "unverifiedRecordReportLcController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/unverified_records_for_rbi_bond_lc/unverified_records_for_rbi_bond_lc.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /*UnverifiedRecordCb starts*/
      .state("unverified_records_for_rbi_bond_cb", {
        abstract: true,
        url: "/unverified_records_for_rbi_bond_cb",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_GU_RBIBOND:UNVERIFIED_RECORD_CB:ENQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/reports/unverified_records_for_rbi_bond_cb/unverified_records_for_rbi_bond_cb.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("unverified_records_for_rbi_bond_cb.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/unverified_records_for_rbi_bond_cb/unverified_records_for_rbi_bond_cb.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:UNVERIFIED_RECORD_CB:ENQUIRY",
        },
        controller: "unverifiedRecordReportCbController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/unverified_records_for_rbi_bond_cb/unverified_records_for_rbi_bond_cb.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("unverified_records_for_rbi_bond_cb.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/unverified_records_for_rbi_bond_cb/unverified_records_for_rbi_bond_cb_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:UNVERIFIED_RECORD_CB:ENQUIRY",
        },
        params: { obj: null },
        controller: "unverifiedRecordReportCbController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/unverified_records_for_rbi_bond_cb/unverified_records_for_rbi_bond_cb.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("Financial_Transaction_Inquiry_report_dc", {
        abstract: true,
        url: "/Financial_Transaction_Inquiry_report_dc",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CERTIFICATE_OF_INVEST_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Financial_Transaction_Inquiry_report/Financial_Transaction_Inquiry_report.service.js"],
            });
          },
        },
      })

      .state("Financial_Transaction_Inquiry_report_dc.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Financial_Transaction_Inquiry_report/Financial_Transaction_Inquiry_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CERTIFICATE_OF_INVEST_REPORT:REPORT",
        },
        controller: "FinancialTransactionController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Financial_Transaction_Inquiry_report/Financial_Transaction_Inquiry_report.controller.js"],
            });
          },
        },
      })

      .state("Financial_Transaction_Inquiry_report_dc.list_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Financial_Transaction_Inquiry_report/Financial_Transaction_Inquiry_report_list.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CERTIFICATE_OF_INVEST_REPORT:REPORT",
        },
        params: { obj: null },
        controller: "FinancialTransactionController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Financial_Transaction_Inquiry_report/Financial_Transaction_Inquiry_report.controller.js"],
            });
          },
        },
      })
      /* financial transaction inquiry Link cell */

      .state("Financial_Transaction_Inquiry_report_lc", {
        abstract: true,
        url: "/Financial_Transaction_Inquiry_report_lc",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CERTIFICATE_OF_INVEST_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Financial_Transaction_Inquiry_report/Financial_Transaction_Inquiry_report.service.js"],
            });
          },
        },
      })

      .state("Financial_Transaction_Inquiry_report_lc.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Financial_Transaction_Inquiry_report/Financial_Transaction_Inquiry_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CERTIFICATE_OF_INVEST_REPORT:REPORT",
        },
        controller: "FinancialTransactionController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Financial_Transaction_Inquiry_report/Financial_Transaction_Inquiry_report.controller.js"],
            });
          },
        },
      })

      .state("Financial_Transaction_Inquiry_report_lc.list_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Financial_Transaction_Inquiry_report/Financial_Transaction_Inquiry_report_list.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CERTIFICATE_OF_INVEST_REPORT:REPORT",
        },
        params: { obj: null },
        controller: "FinancialTransactionController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Financial_Transaction_Inquiry_report/Financial_Transaction_Inquiry_report.controller.js"],
            });
          },
        },
      })

      /* financial transaction inquiry Collecting Branch */

      .state("Financial_Transaction_Inquiry_report_cb", {
        abstract: true,
        url: "/Financial_Transaction_Inquiry_report_cb",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CERTIFICATE_OF_INVEST_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Financial_Transaction_Inquiry_report/Financial_Transaction_Inquiry_report.service.js"],
            });
          },
        },
      })

      .state("Financial_Transaction_Inquiry_report_cb.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Financial_Transaction_Inquiry_report/Financial_Transaction_Inquiry_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CERTIFICATE_OF_INVEST_REPORT:REPORT",
        },
        controller: "FinancialTransactionController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Financial_Transaction_Inquiry_report/Financial_Transaction_Inquiry_report.controller.js"],
            });
          },
        },
      })

      .state("Financial_Transaction_Inquiry_report_cb.list_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Financial_Transaction_Inquiry_report/Financial_Transaction_Inquiry_report_list.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CERTIFICATE_OF_INVEST_REPORT:REPORT",
        },
        params: { obj: null },
        controller: "FinancialTransactionController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Financial_Transaction_Inquiry_report/Financial_Transaction_Inquiry_report.controller.js"],
            });
          },
        },
      })

      /*  rbi bond brokerage Starts*/
      .state("rbi_bnd_brk_payment", {
        abstract: true,
        url: "/rbi_bnd_brk_payment",
        params: {
          views: ["add", "verify", "delete", "inquiry"],
          permissions: ["GBM_GU_RBIBOND:RBIBOND_BROK_PAYMNT_RBI_BOND:ADD", "GBM_GU_RBIBOND:RBIBOND_BROK_PAYMNT_RBI_BOND:VERIFY", "GBM_GU_RBIBOND:RBIBOND_BROK_PAYMNT_RBI_BOND:DELETE", "GBM_GU_RBIBOND:RBIBOND_BROK_PAYMNT_RBI_BOND:ENQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/bond_brokerage_payment/bond_brokerage_payment_service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_brk_payment.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_brokerage_payment/add_bond_brokerage_payment.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BROK_PAYMNT_RBI_BOND:ADD",
        },
        controller: "rbiBondBrokeragePaymentController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_brokerage_payment/bond_brokerage_payment_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_brk_payment.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_brokerage_payment/add_bond_brokerage_payment_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BROK_PAYMNT_RBI_BOND:ADD",
        },
        params: { obj: null },
        controller: "rbiBondBrokeragePaymentController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_brokerage_payment/bond_brokerage_payment_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_brk_payment.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_brokerage_payment/verify_bond_brokerage_payment.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BROK_PAYMNT_RBI_BOND:VERIFY",
        },
        controller: "rbiBondBrokeragePaymentController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_brokerage_payment/bond_brokerage_payment_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_brk_payment.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_brokerage_payment/verify_bond_brokerage_payment_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BROK_PAYMNT_RBI_BOND:VERIFY",
        },
        params: { obj: null },
        controller: "rbiBondBrokeragePaymentController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_brokerage_payment/bond_brokerage_payment_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_brk_payment.delete", {
        url: "/action/delete",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_brokerage_payment/delete_bond_brokerage_payment.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BROK_PAYMNT_RBI_BOND:DELETE",
        },
        controller: "rbiBondBrokeragePaymentController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_brokerage_payment/bond_brokerage_payment_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_brk_payment.delete_detail", {
        url: "/action/delete/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_brokerage_payment/delete_bond_brokerage_payment_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BROK_PAYMNT_RBI_BOND:DELETE",
        },
        params: { obj: null },
        controller: "rbiBondBrokeragePaymentController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_brokerage_payment/bond_brokerage_payment_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_brk_payment.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_brokerage_payment/inquiry_bond_brokerage_payment.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BROK_PAYMNT_RBI_BOND:ENQUIRY",
        },
        controller: "rbiBondBrokeragePaymentController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_brokerage_payment/bond_brokerage_payment_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_brk_payment.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_brokerage_payment/inquiry_bond_brokerage_payment_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BROK_PAYMNT_RBI_BOND:ENQUIRY",
        },
        params: { obj: null },
        controller: "rbiBondBrokeragePaymentController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_brokerage_payment/bond_brokerage_payment_controller.js?v=" + versionNum],
            });
          },
        },
      })
      /*  rbi bond bond brokerage out End*/

      /*  rbi bond acc closure on maturity Starts*/
      .state("rbi_bnd_acc_cls_mty", {
        abstract: true,
        url: "/rbi_bnd_acc_cls_mty",
        params: {
          views: ["upload", "add", "verify", "download", "inquiry"],
          permissions: [
            "GBM_GU_RBIBOND:RBIBOND_ACC_CLS_MTY_RBI_BOND:UPLOAD",
            "GBM_GU_RBIBOND:RBIBOND_ACC_CLS_MTY_RBI_BOND:ADD",
            "GBM_GU_RBIBOND:RBIBOND_ACC_CLS_MTY_RBI_BOND:VERIFY",
            "GBM_GU_RBIBOND:RBIBOND_ACC_CLS_MTY_RBI_BOND:UPLOAD",
            "GBM_GU_RBIBOND:RBIBOND_ACC_CLS_MTY_RBI_BOND:ENQUIRY",
          ],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/bond_subs_acc_closure_on_maturity/bond_subs_acc_closure_on_maturity_service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_acc_cls_mty.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_subs_acc_closure_on_maturity/add_bond_subs_acc_closure_on_maturity.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_ACC_CLS_MTY_RBI_BOND:ADD",
        },
        controller: "rbiBondSubsAccClsOnMtyController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_subs_acc_closure_on_maturity/bond_subs_acc_closure_on_maturity_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_acc_cls_mty.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_subs_acc_closure_on_maturity/add_bond_subs_acc_closure_on_maturity_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_ACC_CLS_MTY_RBI_BOND:ADD",
        },
        params: { obj: null },
        controller: "rbiBondSubsAccClsOnMtyController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_subs_acc_closure_on_maturity/bond_subs_acc_closure_on_maturity_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_acc_cls_mty.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_subs_acc_closure_on_maturity/verify_bond_subs_acc_closure_on_maturity.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_ACC_CLS_MTY_RBI_BOND:VERIFY",
        },
        controller: "rbiBondSubsAccClsOnMtyController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_subs_acc_closure_on_maturity/bond_subs_acc_closure_on_maturity_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_acc_cls_mty.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_subs_acc_closure_on_maturity/verify_bond_subs_acc_closure_on_maturity_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_ACC_CLS_MTY_RBI_BOND:VERIFY",
        },
        params: { obj: null },
        controller: "rbiBondSubsAccClsOnMtyController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_subs_acc_closure_on_maturity/bond_subs_acc_closure_on_maturity_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_acc_cls_mty.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_subs_acc_closure_on_maturity/inquiry_bond_subs_acc_closure_on_maturity.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_ACC_CLS_MTY_RBI_BOND:ENQUIRY",
        },
        controller: "rbiBondSubsAccClsOnMtyController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_subs_acc_closure_on_maturity/bond_subs_acc_closure_on_maturity_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_acc_cls_mty.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_subs_acc_closure_on_maturity/inquiry_bond_subs_acc_closure_on_maturity_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_ACC_CLS_MTY_RBI_BOND:ENQUIRY",
        },
        params: { obj: null },
        controller: "rbiBondSubsAccClsOnMtyController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_subs_acc_closure_on_maturity/bond_subs_acc_closure_on_maturity_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bnd_acc_cls_mty.download", {
        url: "/download",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_subs_acc_closure_on_maturity/download_bond_subs_acc_closure_on_maturity.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_ACC_CLS_MTY_RBI_BOND:DOWNLOAD",
        },
        controller: "rbiBondSubsAccClsOnMtyController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_subs_acc_closure_on_maturity/bond_subs_acc_closure_on_maturity_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bnd_acc_cls_mty.upload_feature", {
        url: "/action/upload_feature",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/bond_subs_acc_closure_on_maturity/upload_bond_subs_acc_closure_on_maturity.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_ACC_CLS_MTY_RBI_BOND:UPLOAD",
        },
        controller: "rbiBondSubsAccClsOnMtyController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/bond_subs_acc_closure_on_maturity/bond_subs_acc_closure_on_maturity_controller.js?v=" + versionNum],
            });
          },
        },
      })
      /*  rbi bond acc closure on maturity End*/

      /* Rbi Bond Centralized Trial Run - Eligible Accounts GU Starts*/
      .state("rbibond_centralized_trial_run_gu_report", {
        abstract: true,
        url: "/rbibond_centralized_trial_run_gu_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_INTEREST_TRAIL_RUN_ELIGIBLE_ACCOUNTS:ENQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_centralized_trial_run_eligible_accounts/rbi_bond_centralized_trial_run_eligible_accounts.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_centralized_trial_run_gu_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/dc/rbi_bond/rbi_bond_centralized_trial_run_eligible_accounts/rbi_bond_centralized_trial_run_eligible_accounts_gu.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_INTEREST_TRAIL_RUN_ELIGIBLE_ACCOUNTS:ENQUIRY",
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

      .state("rbibond_centralized_trial_run_gu_report.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/dc/rbi_bond/rbi_bond_centralized_trial_run_eligible_accounts/rbi_bond_centralized_trial_run_eligible_accounts_list_gu.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_INTEREST_TRAIL_RUN_ELIGIBLE_ACCOUNTS:ENQUIRY",
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
      /* Rbi Bond Centralized Trial Run - Eligible Accounts GU Ends*/

      /// bond intrerest not calculated report starts///

      .state("Bond_Interest_NOT_Calculated_Report", {
        abstract: true,
        url: "/Bond_Interest_NOT_Calculated_Report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_DC:BOND_INTEREST_NOT_CALCULATED_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/Bond_Interest_NOT_Calculated_Report/Bond_Interest_NOT_Calculated_Report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("Bond_Interest_NOT_Calculated_Report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/dc/rbi_bond/Bond_Interest_NOT_Calculated_Report/Bond_Interest_NOT_Calculated_Report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC:BOND_INTEREST_NOT_CALCULATED_REPORT:REPORT",
        },
        controller: "BondInterestController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/Bond_Interest_NOT_Calculated_Report/Bond_Interest_NOT_Calculated_Report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("Bond_Interest_NOT_Calculated_Report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/dc/rbi_bond/Bond_Interest_NOT_Calculated_Report/Bond_Interest_NOT_Calculated_Report_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_DC:BOND_INTEREST_NOT_CALCULATED_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "BondInterestController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/Bond_Interest_NOT_Calculated_Report/Bond_Interest_NOT_Calculated_Report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /// bond interest not calculated report ends///

      ///////////////////// Scrolls ///////////////////////////////

      // rbibond principal repayment scroll  starts //
      .state("rbibond_principal_repayment_scroll", {
        abstract: true,
        url: "/rbibond_principal_repayment_scroll",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRINCIPAL_REPAYMENT_SCROLL:SCROLL"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_principal_repayment_scroll/rbibond_principal_repayment_scroll.service.js"],
            });
          },
        },
      })
      .state("rbibond_principal_repayment_scroll.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/rbibond_principal_repayment_scroll/rbibond_principal_repayment_scroll_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRINCIPAL_REPAYMENT_SCROLL:SCROLL",
        },
        controller: "rbibondPrincipalRepayController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_principal_repayment_scroll/rbibond_principal_repayment_scroll.controller.js"],
            });
          },
        },
      })
      .state("rbibond_principal_repayment_scroll.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/rbibond_principal_repayment_scroll/rbibond_principal_repayment_scroll_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRINCIPAL_REPAYMENT_SCROLL:SCROLL",
        },
        params: { obj: null },
        controller: "rbibondPrincipalRepayController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_principal_repayment_scroll/rbibond_principal_repayment_scroll.controller.js"],
            });
          },
        },
      })
      //rbibond principal repayment scroll ends//

      // rbibond principal repayment scroll  starts //
      .state("rbibond_turn_over_commission_scroll", {
        abstract: true,
        url: "/rbibond_turn_over_commission_scroll",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TURN_OVER_COMMISSION_SCROLL:SCROLL"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/turn_over_commission_scroll/turn_over_commission_scroll.service.js"],
            });
          },
        },
      })
      .state("rbibond_turn_over_commission_scroll.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/turn_over_commission_scroll/turn_over_commission_scroll_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TURN_OVER_COMMISSION_SCROLL:SCROLL",
        },
        controller: "turnOverCommissionScrollController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/turn_over_commission_scroll/turn_over_commission_scroll.controller.js"],
            });
          },
        },
      })
      .state("rbibond_turn_over_commission_scroll.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/turn_over_commission_scroll/turn_over_commission_scroll_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TURN_OVER_COMMISSION_SCROLL:SCROLL",
        },
        params: { obj: null },
        controller: "turnOverCommissionScrollController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/turn_over_commission_scroll/turn_over_commission_scroll.controller.js"],
            });
          },
        },
      })
      //rbibond principal repayment scroll ends//

      // rbibond Interest repayment scroll  starts //
      .state("rbibond_interest_repayment_scroll", {
        abstract: true,
        url: "/rbibond_interest_repayment_scroll",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_INTEREST_REPAYMENT_SCROLL:SCROLL"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_interest_repayment_scroll/rbibond_interest_repayment_scroll.service.js"],
            });
          },
        },
      })
      .state("rbibond_interest_repayment_scroll.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/rbibond_interest_repayment_scroll/rbibond_interest_repayment_scroll_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_INTEREST_REPAYMENT_SCROLL:SCROLL",
        },
        controller: "rbibondInterestRepayController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_interest_repayment_scroll/rbibond_interest_repayment_scroll.controller.js"],
            });
          },
        },
      })
      .state("rbibond_interest_repayment_scroll.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/rbibond_interest_repayment_scroll/rbibond_interest_repayment_scroll_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_INTEREST_REPAYMENT_SCROLL:SCROLL",
        },
        params: { obj: null },
        controller: "rbibondInterestRepayController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_interest_repayment_scroll/rbibond_interest_repayment_scroll.controller.js"],
            });
          },
        },
      })
      //rbibond Interest repayment scroll ends//

      // Register of Bond Ledger Accounts  scroll collecting branch starts //
      .state("rbibond_register_ledger_acc_scroll", {
        abstract: true,
        url: "/rbibond_register_ledger_acc_scroll",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_REGISTER_LEDGER_ACC_COLL_BRANCH_SCROLL:SCROLL"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_register_ledger_acc_scroll/rbibond_register_ledger_acc_scroll.service.js"],
            });
          },
        },
      })
      .state("rbibond_register_ledger_acc_scroll.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/rbibond_register_ledger_acc_scroll/rbibond_register_ledger_acc_scroll_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_REGISTER_LEDGER_ACC_COLL_BRANCH_SCROLL:SCROLL",
        },
        controller: "rbibondRegisterLedgerController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_register_ledger_acc_scroll/rbibond_register_ledger_acc_scroll.controller.js"],
            });
          },
        },
      })
      .state("rbibond_register_ledger_acc_scroll.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/rbibond_register_ledger_acc_scroll/rbibond_register_ledger_acc_scroll_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_REGISTER_LEDGER_ACC_COLL_BRANCH_SCROLL:SCROLL",
        },
        params: { obj: null },
        controller: "rbibondRegisterLedgerController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_register_ledger_acc_scroll/rbibond_register_ledger_acc_scroll.controller.js"],
            });
          },
        },
      })

      //Register of Bond Ledger Accounts  scroll collecting branch ends//

      //Statement of Bonds Issued/Repaid for the month starts//

      .state("Statement_of_Bonds_Issued_Repaid_for_the_month", {
        abstract: true,
        url: "/Statement_of_Bonds_Issued_Repaid_for_the_month",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:STATEMENT_OF_BONDS_ISSUED_REPAID_FOR_THE_MONTH_SCROLL:SCROLL"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Statement_of_Bonds_Issued_Repaid_for_the_month/Statement_of_Bonds_Issued_Repaid_for_the_month.service.js"],
            });
          },
        },
      })
      .state("Statement_of_Bonds_Issued_Repaid_for_the_month.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Statement_of_Bonds_Issued_Repaid_for_the_month/Statement_of_Bonds_Issued_Repaid_for_the_month_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:STATEMENT_OF_BONDS_ISSUED_REPAID_FOR_THE_MONTH_SCROLL:SCROLL",
        },
        controller: "StatementOfBondsController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Statement_of_Bonds_Issued_Repaid_for_the_month/Statement_of_Bonds_Issued_Repaid_for_the_month.controller.js"],
            });
          },
        },
      })
      .state("Statement_of_Bonds_Issued_Repaid_for_the_month.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Statement_of_Bonds_Issued_Repaid_for_the_month/Statement_of_Bonds_Issued_Repaid_for_the_month_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:STATEMENT_OF_BONDS_ISSUED_REPAID_FOR_THE_MONTH_SCROLL:SCROLL",
        },
        params: { obj: null },
        controller: "StatementOfBondsController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Statement_of_Bonds_Issued_Repaid_for_the_month/Statement_of_Bonds_Issued_Repaid_for_the_month.controller.js"],
            });
          },
        },
      })
      //Statement of Bonds Issued/Repaid for the month ends//

      /// statrs the Stement of charges servicing bond ledger account///
      .state("Statement_of_Charges_for_Servicing_Bond_Ledger_Account", {
        abstract: true,
        url: "/Statement_of_Charges_for_Servicing_Bond_Ledger_Account",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:STATEMENT_OF_BONDS_ISSUED_REPAID_FOR_THE_MONTH_SCROLL:SCROLL"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Statement_of_Charges_for_Servicing_Bond_Ledger_Account/Statement_of_Charges_for_Servicing_Bond_Ledger_Account.service.js"],
            });
          },
        },
      })
      .state("Statement_of_Charges_for_Servicing_Bond_Ledger_Account.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Statement_of_Charges_for_Servicing_Bond_Ledger_Account/Statement_of_Charges_for_Servicing_Bond_Ledger_Account_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:STATEMENT_OF_BONDS_ISSUED_REPAID_FOR_THE_MONTH_SCROLL:SCROLL",
        },
        controller: "StatementOfChargesForServicingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Statement_of_Charges_for_Servicing_Bond_Ledger_Account/Statement_of_Charges_for_Servicing_Bond_Ledger_Account.controller.js"],
            });
          },
        },
      })
      .state("Statement_of_Charges_for_Servicing_Bond_Ledger_Account.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Statement_of_Charges_for_Servicing_Bond_Ledger_Account/Statement_of_Charges_for_Servicing_Bond_Ledger_Account_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:STATEMENT_OF_BONDS_ISSUED_REPAID_FOR_THE_MONTH_SCROLL:SCROLL",
        },
        params: { obj: null },
        controller: "StatementOfChargesForServicingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Statement_of_Charges_for_Servicing_Bond_Ledger_Account/Statement_of_Charges_for_Servicing_Bond_Ledger_Account.controller.js"],
            });
          },
        },
      })

      /// ends the Statement of charges servicing bond ledger account///

      // Register of Bond Ledger Accounts  scroll Linkcell starts //
      .state("rbibond_register_ledger_acc_linkcell_scroll", {
        abstract: true,
        url: "/rbibond_register_ledger_acc_linkcell_scroll",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_REGISTER_LEDGER_ACC_LINK_CELL_SCROLL:SCROLL"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_register_ledger_acc_linkcell_scroll/rbibond_register_ledger_acc_linkcell_scroll.service.js"],
            });
          },
        },
      })
      .state("rbibond_register_ledger_acc_linkcell_scroll.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/rbibond_register_ledger_acc_linkcell_scroll/rbibond_register_ledger_acc_linkcell_scroll_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_REGISTER_LEDGER_ACC_LINK_CELL_SCROLL:SCROLL",
        },
        controller: "rbibondRegLedgerLinkCellController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_register_ledger_acc_linkcell_scroll/rbibond_register_ledger_acc_linkcell_scroll.controller.js"],
            });
          },
        },
      })
      .state("rbibond_register_ledger_acc_linkcell_scroll.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/rbibond_register_ledger_acc_linkcell_scroll/rbibond_register_ledger_acc_linkcell_scroll_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_REGISTER_LEDGER_ACC_LINK_CELL_SCROLL:SCROLL",
        },
        params: { obj: null },
        controller: "rbibondRegLedgerLinkCellController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_register_ledger_acc_linkcell_scroll/rbibond_register_ledger_acc_linkcell_scroll.controller.js"],
            });
          },
        },
      })
      //Register of Bond Ledger Accounts  scroll Linkcell ends//

      //bond interest run credited  collecting branch report starts//
      .state("rbibond_interest_run_credit_collect_branch_report", {
        abstract: true,
        url: "/rbibond_interest_run_credit_collect_branch_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACTUAL_INTEREST_CRDT_COLLECT_BRANCH_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_interest_run_credit_collect_branch_report/rbibond_interest_run_credit_collect_branch_report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_interest_run_credit_collect_branch_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_interest_run_credit_collect_branch_report/rbibond_interest_run_credit_collect_branch_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACTUAL_INTEREST_CRDT_COLLECT_BRANCH_REPORT:REPORT",
        },
        controller: "rbibondInterRunCretCollBranController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_interest_run_credit_collect_branch_report/rbibond_interest_run_credit_collect_branch_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_interest_run_credit_collect_branch_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_interest_run_credit_collect_branch_report/rbibond_interest_run_credit_collect_branch_report_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACTUAL_INTEREST_CRDT_COLLECT_BRANCH_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "rbibondInterRunCretCollBranController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_interest_run_credit_collect_branch_report/rbibond_interest_run_credit_collect_branch_report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //bond interest run credited collecting branch report ends//

      //bond Account Holder Inquiry report starts//
      .state("rbibond_acc_holder_inquiry_report", {
        abstract: true,
        url: "/rbibond_acc_holder_inquiry_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_HOLDER_INQUIRY_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_acc_holder_inquiry_report/rbibond_acc_holder_inquiry_report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_acc_holder_inquiry_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_acc_holder_inquiry_report/rbibond_acc_holder_inquiry_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_HOLDER_INQUIRY_REPORT:REPORT",
        },
        controller: "rbibondAccHolderInqController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_acc_holder_inquiry_report/rbibond_acc_holder_inquiry_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_acc_holder_inquiry_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_acc_holder_inquiry_report/rbibond_acc_holder_inquiry_report_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NRI_ACCOUNT_DETAILS_INQUIRY_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "rbibondAccHolderInqController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_acc_holder_inquiry_report/rbibond_acc_holder_inquiry_report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //bond Account Holder Inquiry report ends//
      //NRI Account Details Inquiry report starts//
      .state("rbibond_nri_account_details_inquiry_report", {
        abstract: true,
        url: "/rbibond_nri_account_details_inquiry_report",
        params: {
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Nri_Account_Details_Report/rbibond_nri_account_details_inquiry_report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_nri_account_details_inquiry_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Nri_Account_Details_Report/rbibond_nri_account_details_inquiry_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT",
        },
        controller: "rbibondNriAccountDetailsInqController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Nri_Account_Details_Report/rbibond_nri_account_details_inquiry_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_nri_account_details_inquiry_report.inquiry_detail", {
        url: "/action/inquiry_detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Nri_Account_Details_Report/rbibond_nri_account_details_inquiry_report_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "rbibondNriAccountDetailsInqController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Nri_Account_Details_Report/rbibond_nri_account_details_inquiry_report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //NRI Account Details Inquiry report ends//
      //bond ledger inquiry name search starts//
      .state("rbibond_ledger_inquiry_name_search_report", {
        abstract: true,
        url: "/rbibond_ledger_inquiry_name_search_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_LEDGER_INQUIRY_NAME_SEARCH_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_ledger_inquiry_name_search_report/rbibond_ledger_inquiry_name_search_report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_ledger_inquiry_name_search_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_ledger_inquiry_name_search_report/rbibond_ledger_inquiry_name_search_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_LEDGER_INQUIRY_NAME_SEARCH_REPORT:REPORT",
        },
        controller: "rbibondLedgerNameSearchController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_ledger_inquiry_name_search_report/rbibond_ledger_inquiry_name_search_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_ledger_inquiry_name_search_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_ledger_inquiry_name_search_report/rbibond_ledger_inquiry_name_search_report_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_LEDGER_INQUIRY_NAME_SEARCH_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "rbibondLedgerNameSearchController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_ledger_inquiry_name_search_report/rbibond_ledger_inquiry_name_search_report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //bond ledger inquiry name search Inquiry report ends//

      ///bond brokerage handling scroll  starts//
      .state("rbibond_appendixVII_brokerage_handling_scroll", {
        abstract: true,
        url: "/rbibond_appendixVII_brokerage_handling_scroll",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_STATEMENT_CHARGES_BROKERAGE_HANDLE_COMM_SCROLL:SCROLL"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_appendixVII_brokerage_handling_scroll/rbibond_appendixVII_brokerage_handling_scroll.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_appendixVII_brokerage_handling_scroll.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/rbibond_appendixVII_brokerage_handling_scroll/rbibond_appendixVII_brokerage_handling_scroll_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_STATEMENT_CHARGES_BROKERAGE_HANDLE_COMM_SCROLL:SCROLL",
        },
        controller: "rbibondAppendixVIIBrokerageController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_appendixVII_brokerage_handling_scroll/rbibond_appendixVII_brokerage_handling_scroll.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_appendixVII_brokerage_handling_scroll.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/rbibond_appendixVII_brokerage_handling_scroll/rbibond_appendixVII_brokerage_handling_scroll_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_STATEMENT_CHARGES_BROKERAGE_HANDLE_COMM_SCROLL:SCROLL",
        },
        params: {
          obj: null,
        },
        controller: "rbibondAppendixVIIBrokerageController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_appendixVII_brokerage_handling_scroll/rbibond_appendixVII_brokerage_handling_scroll.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //bond brokerage handling scroll  ends//

      ///bond precloser Repayment starts//
      .state("rbibond_precloser_repayment_report", {
        abstract: true,
        url: "/rbibond_precloser_repayment_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRECLOSURE_REPAYMENT_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_precloser_repayment_report/rbibond_precloser_repayment_report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_precloser_repayment_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_precloser_repayment_report/rbibond_precloser_repayment_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRECLOSURE_REPAYMENT_REPORT:REPORT",
        },
        controller: "bondPreClosureRepaymentController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_precloser_repayment_report/rbibond_precloser_repayment_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_precloser_repayment_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_precloser_repayment_report/rbibond_precloser_repayment_report_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRECLOSURE_REPAYMENT_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "bondPreClosureRepaymentController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_precloser_repayment_report/rbibond_precloser_repayment_report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //bond precloser Repayment  ends//

      //---------------------------------------------------------------------------------------------------

      /*rbi_bond_sub_acc_closure_before_matur - death starts*/
      .state("rbi_bond_sub_acc_closure_before_matur_death", {
        abstract: true,
        url: "/rbi_bond_sub_acc_closure_before_matur_death",
        params: {
          views: ["add", "verify", "delete", "inquiry"],
          permissions: [
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_DEATH:ADD",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_DEATH:VERIFY",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_DEATH:DELETE",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_DEATH:INQUIRY",
          ],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur_death/rbi_bond_sub_acc_closure_before_matur_death_service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_sub_acc_closure_before_matur_death.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur_death/rbi_bond_sub_acc_closure_before_matur_death_add.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_DEATH:ADD",
        },
        controller: "bondSubAccCloseBeforeMaturDeath",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur_death/rbi_bond_sub_acc_closure_before_matur_death_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_sub_acc_closure_before_matur_death.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur_death/rbi_bond_sub_acc_closure_before_matur_death_add_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_DEATH:ADD",
        },
        params: { obj: null },
        controller: "bondSubAccCloseBeforeMaturDeath",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur_death/rbi_bond_sub_acc_closure_before_matur_death_controller.js?v=" + versionNum],
            });
          },
        },
      })

      //

      .state("rbi_bond_sub_acc_closure_before_matur_death.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur_death/rbi_bond_sub_acc_closure_before_matur_death_inquiry.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_DEATH:INQUIRY",
        },
        controller: "bondSubAccCloseBeforeMaturDeath",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur_death/rbi_bond_sub_acc_closure_before_matur_death_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_sub_acc_closure_before_matur_death.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur_death/rbi_bond_sub_acc_closure_before_matur_death_inquriy_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_DEATH:INQUIRY",
        },
        params: { obj: null },
        controller: "bondSubAccCloseBeforeMaturDeath",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur_death/rbi_bond_sub_acc_closure_before_matur_death_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_sub_acc_closure_before_matur_death.delete", {
        url: "/action/delete",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur_death/rbi_bond_sub_acc_closure_before_matur_death_delete.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_DEATH:DELETE",
        },
        params: { obj: null },
        controller: "bondSubAccCloseBeforeMaturDeath",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur_death/rbi_bond_sub_acc_closure_before_matur_death_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_sub_acc_closure_before_matur_death.delete_detail", {
        url: "/action/delete/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur_death/rbi_bond_sub_acc_closure_before_matur_death_delete_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_DEATH:DELETE",
        },
        params: { obj: null },
        controller: "bondSubAccCloseBeforeMaturDeath",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur_death/rbi_bond_sub_acc_closure_before_matur_death_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_sub_acc_closure_before_matur_death.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur_death/rbi_bond_sub_acc_closure_before_matur_death_verify.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_DEATH:VERIFY",
        },
        controller: "bondSubAccCloseBeforeMaturDeath",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur_death/rbi_bond_sub_acc_closure_before_matur_death_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_sub_acc_closure_before_matur_death.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur_death/rbi_bond_sub_acc_closure_before_matur_death_verify_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_DEATH:VERIFY",
        },
        params: { obj: null },
        controller: "bondSubAccCloseBeforeMaturDeath",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_before_matur_death/rbi_bond_sub_acc_closure_before_matur_death_controller.js?v=" + versionNum],
            });
          },
        },
      })

      //---------------------------------------------------------------------------------------------------------------------------------------

      /*tds_exemption_master - death starts*/
      .state("tds_exemption_master", {
        abstract: true,
        url: "/tds_exemption_master",
        params: {
          views: ["add", "verify", "modify", "inquiry"],
          permissions: [
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TDS_EXEMPTION_MASTER:ADD",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TDS_EXEMPTION_MASTER:VERIFY",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TDS_EXEMPTION_MASTER:MODIFY",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TDS_EXEMPTION_MASTER:INQUIRY",
          ],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/tds_exemption_master/tds_exemption_master_service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("tds_exemption_master.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/tds_exemption_master/tds_exemption_master_add.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TDS_EXEMPTION_MASTER:ADD",
        },
        controller: "tdsExcemptionMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/tds_exemption_master/tds_exemption_master_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("tds_exemption_master.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/tds_exemption_master/tds_exemption_master_add_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TDS_EXEMPTION_MASTER:ADD",
        },
        params: { obj: null },
        controller: "tdsExcemptionMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/tds_exemption_master/tds_exemption_master_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("tds_exemption_master.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/tds_exemption_master/tds_exemption_master_inquiry.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TDS_EXEMPTION_MASTER:INQUIRY",
        },
        controller: "tdsExcemptionMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/tds_exemption_master/tds_exemption_master_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("tds_exemption_master.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/tds_exemption_master/tds_exemption_master_inquiry_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TDS_EXEMPTION_MASTER:INQUIRY",
        },
        params: { obj: null },
        controller: "tdsExcemptionMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/tds_exemption_master/tds_exemption_master_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("tds_exemption_master.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/tds_exemption_master/tds_exemption_master_verify_reject.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TDS_EXEMPTION_MASTER:VERIFY",
        },
        controller: "tdsExcemptionMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/tds_exemption_master/tds_exemption_master_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("tds_exemption_master.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/tds_exemption_master/tds_exemption_master_verify_reject_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TDS_EXEMPTION_MASTER:VERIFY",
        },
        params: { obj: null },
        controller: "tdsExcemptionMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/tds_exemption_master/tds_exemption_master_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("tds_exemption_master.modify", {
        url: "/action/modify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/tds_exemption_master/tds_exemption_master_modify.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TDS_EXEMPTION_MASTER:MODIFY",
        },
        controller: "tdsExcemptionMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/tds_exemption_master/tds_exemption_master_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("tds_exemption_master.modify_detail", {
        url: "/action/modify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/tds_exemption_master/tds_exemption_master_modify_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TDS_EXEMPTION_MASTER:MODIFY",
        },
        params: { obj: null },
        controller: "tdsExcemptionMasterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/tds_exemption_master/tds_exemption_master_controller.js?v=" + versionNum],
            });
          },
        },
      })

      //---------------------------------------------------------------------------------------------------

      /*rbi_bond_sub_acc_closure_on_matur - death starts*/
      .state("rbi_bond_sub_acc_closure_on_matur_death", {
        abstract: true,
        url: "/rbi_bond_sub_acc_closure_on_matur_death",

        params: {
          views: ["upload", "add", "verify", "download", "inquiry"],
          permissions: [
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:UPLOAD",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:ADD",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:VERIFY",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:DOWNLOAD",
            "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:INQUIRY",
          ],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_on_maturity_death/rbi_bond_sub_acc_closure_on_maturity_death_service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_sub_acc_closure_on_matur_death.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_on_maturity_death/rbi_bond_sub_acc_closure_on_maturity_death_add.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:ADD",
        },
        controller: "bondSubAccCloseOnMaturDeath",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_on_maturity_death/rbi_bond_sub_acc_closure_on_maturity_death_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_sub_acc_closure_on_matur_death.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_on_maturity_death/rbi_bond_sub_acc_closure_on_maturity_death_add_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:ADD",
        },
        params: { obj: null },
        controller: "bondSubAccCloseOnMaturDeath",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_on_maturity_death/rbi_bond_sub_acc_closure_on_maturity_death_controller.js?v=" + versionNum],
            });
          },
        },
      })

      //

      .state("rbi_bond_sub_acc_closure_on_matur_death.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_on_maturity_death/rbi_bond_sub_acc_closure_on_maturity_death_inquiry.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:INQUIRY",
        },
        controller: "bondSubAccCloseOnMaturDeath",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_on_maturity_death/rbi_bond_sub_acc_closure_on_maturity_death_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_sub_acc_closure_on_matur_death.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_on_maturity_death/rbi_bond_sub_acc_closure_on_maturity_death_inquriy_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:INQUIRY",
        },
        params: { obj: null },
        controller: "bondSubAccCloseOnMaturDeath",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_on_maturity_death/rbi_bond_sub_acc_closure_on_maturity_death_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_sub_acc_closure_on_matur_death.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_on_maturity_death/rbi_bond_sub_acc_closure_on_maturity_death_verify.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:VERIFY",
        },
        controller: "bondSubAccCloseOnMaturDeath",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_on_maturity_death/rbi_bond_sub_acc_closure_on_maturity_death_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_sub_acc_closure_on_matur_death.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_on_maturity_death/rbi_bond_sub_acc_closure_on_maturity_death_verify_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:VERIFY",
        },
        params: { obj: null },
        controller: "bondSubAccCloseOnMaturDeath",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_on_maturity_death/rbi_bond_sub_acc_closure_on_maturity_death_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_sub_acc_closure_on_matur_death.download", {
        url: "/download",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_on_maturity_death/rbi_bond_sub_acc_closure_on_maturity_death_download.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:DOWNLOAD",
        },
        controller: "bondSubAccCloseOnMaturDeath",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_on_maturity_death/rbi_bond_sub_acc_closure_on_maturity_death_download.html?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_sub_acc_closure_on_matur_death.upload_feature", {
        url: "/action/upload_feature",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_on_maturity_death/rbi_bond_sub_acc_closure_on_maturity_death_upload.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:UPLOAD",
        },
        controller: "bondSubAccCloseOnMaturDeath",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_sub_acc_closure_on_maturity_death/rbi_bond_sub_acc_closure_on_maturity_death_controller.js?v=" + versionNum],
            });
          },
        },
      })

      //bond interest payment pending Collecting Branch report starts//
      .state("rbibond_interest_payment_pending_coll_report", {
        abstract: true,
        url: "/rbibond_interest_payment_pending_coll_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PAYMENT_PENDING_COLL_BRANCH_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_interest_payment_pending_coll_report/rbibond_interest_payment_pending_coll_report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_interest_payment_pending_coll_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_interest_payment_pending_coll_report/rbibond_interest_payment_pending_coll_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PAYMENT_PENDING_COLL_BRANCH_REPORT:REPORT",
        },
        controller: "rbibondInterestPaymentPendingCollController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_interest_payment_pending_coll_report/rbibond_interest_payment_pending_coll_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_interest_payment_pending_coll_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_interest_payment_pending_coll_report/rbibond_interest_payment_pending_coll_report_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PAYMENT_PENDING_COLL_BRANCH_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "rbibondInterestPaymentPendingCollController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_interest_payment_pending_coll_report/rbibond_interest_payment_pending_coll_report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //bond interest payment pending Collecting Branch report ends//

      /************************* BOND INQUIRY Starts*********************************/

      .state("rbiBond_bond_inquiry", {
        abstract: true,
        url: "/rbiBond_bond_inquiry",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Bond_inquiry_report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbiBond_bond_inquiry.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Bond_inquiry_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT",
        },
        controller: "bondInquiryReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Bond_inquiry_report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      /***********************************************************BLA Account Number Details Starts************************************************************/
      .state("rbiBond_bond_inquiry_bla_acc_det_report", {
        abstract: true,
        url: "/rbiBond_bond_inquiry_bla_acc_det_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Bond_inquiry_report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbiBond_bond_inquiry_bla_acc_det_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Bla_account_number_details_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT",
        },
        controller: "bondInquiryReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Bond_inquiry_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbiBond_bond_inquiry_bla_acc_det_report.inquiry_detail", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Bla_account_number_details_report_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT",
        },
        params: { obj: null },
        controller: "bondInquiryReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Bond_inquiry_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /***********************************************************BLA Account Number Details ends************************************************************/

      /***********************************************************Subscription Amount Details Starts************************************************************/

      .state("rbiBond_bond_inquiry_subs_amt_det_report", {
        abstract: true,
        url: "/rbiBond_bond_inquiry_subs_amt_det_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Bond_inquiry_report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbiBond_bond_inquiry_subs_amt_det_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Subs_amount_details_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT",
        },
        controller: "bondInquiryReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Bond_inquiry_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbiBond_bond_inquiry_subs_amt_det_report.inquiry_detail", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Subs_amount_details_report_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT",
        },
        params: { obj: null },
        controller: "bondInquiryReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Bond_inquiry_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /***********************************************************Subscription Amount Details ends************************************************************/

      /***********************************************************Matured Account Details Starts************************************************************/

      .state("rbiBond_bond_inquiry_matured_acc_det_report", {
        abstract: true,
        url: "/rbiBond_bond_inquiry_matured_acc_det_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Bond_inquiry_report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbiBond_bond_inquiry_matured_acc_det_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Matured_account_details_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT",
        },
        controller: "bondInquiryReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Bond_inquiry_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbiBond_bond_inquiry_matured_acc_det_report.inquiry_detail", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Matured_account_details_report_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT",
        },
        params: { obj: null },
        controller: "bondInquiryReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Bond_inquiry_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /***********************************************************Matured Account Details Details ends************************************************************/

      /***********************************************************Account Closed Details Starts************************************************************/

      .state("rbiBond_bond_inquiry_acc_closed_det_report", {
        abstract: true,
        url: "/rbiBond_bond_inquiry_acc_closed_det_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Bond_inquiry_report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbiBond_bond_inquiry_acc_closed_det_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Account_closed_details_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT",
        },
        controller: "bondInquiryReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Bond_inquiry_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbiBond_bond_inquiry_acc_closed_det_report.inquiry_detail", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Account_closed_details_report_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT",
        },
        params: { obj: null },
        controller: "bondInquiryReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Bond_inquiry_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /***********************************************************Account Closed Details ends************************************************************/

      /***********************************************************NRI Account Details Starts************************************************************/

      .state("rbiBond_bond_inquiry_nri_acc_det_report", {
        abstract: true,
        url: "/rbiBond_bond_inquiry_nri_acc_det_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Bond_inquiry_report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbiBond_bond_inquiry_nri_acc_det_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/NRI_account_details_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT",
        },
        controller: "bondInquiryReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Bond_inquiry_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbiBond_bond_inquiry_nri_acc_det_report.inquiry_detail", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/NRI_account_details_report_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT",
        },
        params: { obj: null },
        controller: "bondInquiryReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Bond_inquiry_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /***********************************************************NRI Account Details ends************************************************************/

      /***********************************************************Holder Type Details Starts************************************************************/

      .state("rbiBond_bond_inquiry_holder_type_det_report", {
        abstract: true,
        url: "/rbiBond_bond_inquiry_holder_type_det_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Bond_inquiry_report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbiBond_bond_inquiry_holder_type_det_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Holder_type_details_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT",
        },
        controller: "bondInquiryReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Bond_inquiry_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbiBond_bond_inquiry_holder_type_det_report.inquiry_detail", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Holder_type_details_report_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT",
        },
        params: { obj: null },
        controller: "bondInquiryReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Inquiry_Report/Bond_inquiry_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /***********************************************************Holder Type Details ends************************************************************/

      /***********************************************************Transfer In/Transfer Out Details Starts************************************************************/

      .state("Transfer_In_Transfer_Out", {
        abstract: true,
        url: "/Transfer_In_Transfer_Out",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TRANSFER_IN_TRANSFER_OUT_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Transfer_In_Transfer_Out/Transfer_In_Transfer_Out.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("Transfer_In_Transfer_Out.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Transfer_In_Transfer_Out/Transfer_In_Transfer_Out_Input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TRANSFER_IN_TRANSFER_OUT_REPORT:REPORT",
        },
        controller: "TransferInOutController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Transfer_In_Transfer_Out/Transfer_In_Transfer_Out.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("Transfer_In_Transfer_Out.inquiry_details", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Transfer_In_Transfer_Out/Transfer_In_Transfer_Out_Details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TRANSFER_IN_TRANSFER_OUT_REPORT:REPORT",
        },
        params: { obj: null },
        controller: "TransferInOutController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Transfer_In_Transfer_Out/Transfer_In_Transfer_Out.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /***********************************************************Transfer In/Transfer Out Details ends************************************************************/

      /************************* BOND INQUIRY ends*********************************/

      /************************* BOND DAILY REMITTANCE LC STARTS*********************************/

      .state("rbiBond_daily_remit_lc", {
        abstract: true,
        url: "/rbiBond_daily_remit_lc",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_DAILY_REMITTANCE_LC_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Daily_remittance_lc_report/daily_remittance_lc.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbiBond_daily_remit_lc.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Daily_remittance_lc_report/daily_remittance_lc_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_DAILY_REMITTANCE_LC_REPORT:REPORT",
        },
        controller: "dailyRemittanceLcController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Daily_remittance_lc_report/daily_remittance_lc.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbiBond_daily_remit_lc.inquiry_detail", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Daily_remittance_lc_report/daily_remittance_lc_report_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_DAILY_REMITTANCE_LC_REPORT:REPORT",
        },
        params: { obj: null },
        controller: "dailyRemittanceLcController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Daily_remittance_lc_report/daily_remittance_lc.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /************************* BOND DAILY REMITTANCE LC ENDS*********************************/

      /************************* BOND MINOR TO MAJOR NOTICE INTIMATES STARTS*********************************/
      .state("rbiBond_minor_major_intimation_notice", {
        abstract: true,
        url: "/rbiBond_minor_major_intimation_notice",
        params: {
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:BOND_MINOR_MAJOR_INITIMATION_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_minor_to_major_intimation_notice/rbi_bond_minor_to_major_intimation_notice_service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbiBond_minor_major_intimation_notice.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_minor_to_major_intimation_notice/rbi_bond_minor_to_major_intimation_notice_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BOND_MINOR_MAJOR_INITIMATION_REPORT:REPORT",
        },
        controller: "bondMinorToMajorIntimationNoticeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_minor_to_major_intimation_notice/rbi_bond_minor_to_major_intimation_notice_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbiBond_minor_major_intimation_notice.inquiry_detail", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbibond_minor_to_major_intimation_notice/rbi_bond_minor_to_major_intimation_notice_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BOND_MINOR_MAJOR_INITIMATION_REPORT:REPORT",
        },
        params: { obj: null },
        controller: "bondMinorToMajorIntimationNoticeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbibond_minor_to_major_intimation_notice/rbi_bond_minor_to_major_intimation_notice_controller.js?v=" + versionNum],
            });
          },
        },
      })

      /************************* BOND MINOR TO MAJOR NOTICE INTIMATES ENDS*********************************/

      /************************* Appendix VIII - Statement of Charges for Turnover Commission  STARTS*********************************/
      .state("rbibond_appendixVIII_Statement_of_Charges_for_Turnover_Commission", {
        abstract: true,
        url: "/rbibond_appendixVIII_Statement_of_Charges_for_Turnover_Commission",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_STATEMENT_OF_CHARGES_TURNOVER_COMMISSION:SCROLL"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_appendixVIII_Statement_of_Charges_for_Turnover_Commission/rbibond_AppendixVIII_Statement_of_Charges_for_Turnover_Commission.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_appendixVIII_Statement_of_Charges_for_Turnover_Commission.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/rbibond_appendixVIII_Statement_of_Charges_for_Turnover_Commission/rbibond_AppendixVIII_Statement_of_Charges_for_Turnover_Commission_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_STATEMENT_OF_CHARGES_TURNOVER_COMMISSION:SCROLL",
        },
        controller: "rbibondTurnoverCommissionController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_appendixVIII_Statement_of_Charges_for_Turnover_Commission/rbibond_AppendixVIII_Statement_of_Charges_for_Turnover_Commission.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_appendixVIII_Statement_of_Charges_for_Turnover_Commission.inquiry_details", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/rbibond_appendixVIII_Statement_of_Charges_for_Turnover_Commission/rbibond_AppendixVIII_Statement_of_Charges_for_Turnover_Commission_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_STATEMENT_OF_CHARGES_TURNOVER_COMMISSION:SCROLL",
        },
        params: { obj: null },
        controller: "rbibondTurnoverCommissionController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_appendixVIII_Statement_of_Charges_for_Turnover_Commission/rbibond_AppendixVIII_Statement_of_Charges_for_Turnover_Commission.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /************************* Appendix VIII - Statement of Charges for Turnover Commission  STARTS*********************************/
      .state("Interest_Scroll_report_Appendix_XI", {
        abstract: true,
        url: "/Interest_Scroll_report_Appendix_XI",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_STATEMENT_OF_CHARGES_TURNOVER_COMMISSION:SCROLL"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Interest_Scroll_report_Appendix_XI/Interest_Scroll_report_Appendix_XI.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("Interest_Scroll_report_Appendix_XI.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Interest_Scroll_report_Appendix_XI/Interest_Scroll_report_Appendix_XI.input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_STATEMENT_OF_CHARGES_TURNOVER_COMMISSION:SCROLL",
        },
        controller: "rbibondAppendixXIInterestScrollController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Interest_Scroll_report_Appendix_XI/Interest_Scroll_report_Appendix_XI.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("Interest_Scroll_report_Appendix_XI.inquiry_details", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Interest_Scroll_report_Appendix_XI/Interest_Scroll_report_Appendix_XI.detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_STATEMENT_OF_CHARGES_TURNOVER_COMMISSION:SCROLL",
        },
        params: { obj: null },
        controller: "rbibondAppendixXIInterestScrollController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Interest_Scroll_report_Appendix_XI/Interest_Scroll_report_Appendix_XI.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("bond_subscription_without_nomination", {
        abstract: true,
        url: "/bond_subscription_without_nomination",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_SUBSCRIPTION_WITHOUT_NOMINATION_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports//reports/bond_subscription_without_nomination/bond_subscription_without_nomination.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("bond_subscription_without_nomination.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/bond_subscription_without_nomination/bond_subscription_without_nomination_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_SUBSCRIPTION_WITHOUT_NOMINATION_REPORT:REPORT",
        },
        controller: "bondSubscriptionwithoutnominationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/bond_subscription_without_nomination/bond_subscription_without_nomination.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("bond_subscription_without_nomination.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/bond_subscription_without_nomination/bond_subscription_without_nomination_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_SUBSCRIPTION_WITHOUT_NOMINATION_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "bondSubscriptionwithoutnominationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/bond_subscription_without_nomination/bond_subscription_without_nomination.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //bond_subscription_without_nomination.inquiry ends//

      //CAFU DOWNLOAD FILE//

      .state("cafu_file_download", {
        abstract: true,
        url: "/cafu_file_download",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CAFU_FILE_DOWNLOAD:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/cafu_file_download/cafu_file_download.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("cafu_file_download.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/cafu_file_download/cafu_file_download_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CAFU_FILE_DOWNLOAD:REPORT",
        },
        controller: "cafuFileDownloadController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/cafu_file_download/cafu_file_download.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /// bond karta coparceners details starts here

      .state("karta_coparceners_details", {
        abstract: true,
        url: "/karta_coparceners_details",
        params: {
          views: ["modify", "verify", "inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:KARTA_COPARCENERS_DETAILS:MODIFY", "GBM_RBIBOND_GU_FUNCTIONALITY:KARTA_COPARCENERS_DETAILS:VERIFY", "GBM_RBIBOND_GU_FUNCTIONALITY:KARTA_COPARCENERS_DETAILS:INQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/karta_coparceners_details/karta_coparceners_details.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("karta_coparceners_details.modify", {
        url: "/action/modify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/karta_coparceners_details/modify_karta_coparceners.html",

        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:KARTA_COPARCENERS_DETAILS:MODIFY",
        },
        controller: "rbibondKartaCoparcenersController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/karta_coparceners_details/karta_coparceners_details.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("karta_coparceners_details.modify_detail", {
        url: "/action/modify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/karta_coparceners_details/modify_karta_coparceners_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:KARTA_COPARCENERS_DETAILS:MODIFY",
        },
        params: { obj: null },
        controller: "rbibondKartaCoparcenersController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/karta_coparceners_details/karta_coparceners_details.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("karta_coparceners_details.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/karta_coparceners_details/verify_karta_coparceners.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:KARTA_COPARCENERS_DETAILS:VERIFY",
        },
        controller: "rbibondKartaCoparcenersController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/karta_coparceners_details/karta_coparceners_details.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("karta_coparceners_details.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/karta_coparceners_details/verify_karta_coparceners_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:KARTA_COPARCENERS_DETAILS:VERIFY",
        },
        params: { obj: null },
        controller: "rbibondKartaCoparcenersController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/karta_coparceners_details/karta_coparceners_details.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("karta_coparceners_details.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/karta_coparceners_details/inquiry_karta_coparceners.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:KARTA_COPARCENERS_DETAILS:INQUIRY",
        },
        controller: "rbibondKartaCoparcenersController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/karta_coparceners_details/karta_coparceners_details.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("karta_coparceners_details.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/karta_coparceners_details/inquiry_karta_coparceners_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:KARTA_COPARCENERS_DETAILS:INQUIRY",
        },
        params: { obj: null },
        controller: "rbibondKartaCoparcenersController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/karta_coparceners_details/karta_coparceners_details.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /// bond karta coparceners details ends here

      /*State Wise SGST Master for Brokerage starts here*/
      .state("state_wise_sgst_master_for_brokerage", {
        abstract: true,
        url: "/state_wise_sgst_master_for_brokerage",
        params: {
          views: ["add", "modify", "verify", "inquiry"],
          permissions: [
            "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:ADD",
            "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:MODIFY",
            "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:VERIFY",
            "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:ENQUIRY",
          ],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/state_wise_sgst_master_for_brokerage/state_wise_sgst_master_for_brokerage.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("state_wise_sgst_master_for_brokerage.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/state_wise_sgst_master_for_brokerage/add_state_wise_sgst_master_for_brokerage.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:ADD",
        },
        controller: "StatewiseSgstMasterForBrokerageController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/state_wise_sgst_master_for_brokerage/state_wise_sgst_master_for_brokerage.controller.js?v=" + versionNum],
            });
          },
        },
      })

      //                               .state('state_wise_sgst_master_for_brokerage.add_detail', {
      //                                 url: '/add/detail',
      //                                 templateUrl: 'app/rbi_bond_module/gu_functionallity/masters/state_wise_sgst_master_for_brokerage/add_state_wise_sgst_master_for_brokerage_detail.html',
      //                                 data: {
      //                                     authRequired: true,
      //                                     requiredPermission: 'GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:ADD'
      //                                 },
      //                                 params : {obj : null},
      //                                 controller: 'StatewiseSgstMasterForBrokerageController',
      //                                 controllerAs: 'vm',
      //                                 resolve: {
      //                                     load: function($ocLazyLoad) {
      //                                         return $ocLazyLoad.load({
      //                                             files:  ['app/rbi_bond_module/gu_functionallity/masters/state_wise_sgst_master_for_brokerage/state_wise_sgst_master_for_brokerage.controller.js?v='+versionNum]
      //                                         });
      //                                     }
      //                                 }
      //                             })
      .state("state_wise_sgst_master_for_brokerage.modify", {
        url: "/action/modify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/state_wise_sgst_master_for_brokerage/modify_state_wise_sgst_master_for_brokerage.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:MODIFY",
        },
        controller: "StatewiseSgstMasterForBrokerageController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/state_wise_sgst_master_for_brokerage/state_wise_sgst_master_for_brokerage.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("state_wise_sgst_master_for_brokerage.modify_detail", {
        url: "/action/modify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/state_wise_sgst_master_for_brokerage/modify_state_wise_sgst_master_for_brokerage_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:MODIFY",
        },
        params: { obj: null },
        controller: "StatewiseSgstMasterForBrokerageController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/state_wise_sgst_master_for_brokerage/state_wise_sgst_master_for_brokerage.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("state_wise_sgst_master_for_brokerage.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/state_wise_sgst_master_for_brokerage/verify_state_wise_sgst_master_for_brokerage.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:VERIFY",
        },
        controller: "StatewiseSgstMasterForBrokerageController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/state_wise_sgst_master_for_brokerage/state_wise_sgst_master_for_brokerage.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("state_wise_sgst_master_for_brokerage.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/state_wise_sgst_master_for_brokerage/verify_state_wise_sgst_master_for_brokerage_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:VERIFY",
        },
        params: { obj: null },
        controller: "StatewiseSgstMasterForBrokerageController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/state_wise_sgst_master_for_brokerage/state_wise_sgst_master_for_brokerage.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("state_wise_sgst_master_for_brokerage.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/state_wise_sgst_master_for_brokerage/inquiry_state_wise_sgst_master_for_brokerage.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:ENQUIRY",
        },
        controller: "StatewiseSgstMasterForBrokerageController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/state_wise_sgst_master_for_brokerage/state_wise_sgst_master_for_brokerage.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("state_wise_sgst_master_for_brokerage.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/state_wise_sgst_master_for_brokerage/inquiry_state_wise_sgst_master_for_brokerage_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:ENQUIRY",
        },
        params: { obj: null },
        controller: "StatewiseSgstMasterForBrokerageController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/state_wise_sgst_master_for_brokerage/state_wise_sgst_master_for_brokerage.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /*State Wise SGST Master for Brokerage ends here*/

      /*************************Brokerage Invoice Geneartion Starts*********************************/
      .state("brokerage_invoice_generation", {
        abstract: true,
        url: "/brokerage_invoice_generation",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_REPORT:BOND_BROKERAGE_INVOICE_GENEARTION_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Brokerage_Invoice_Generation/brokerage_invoice_generation_service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("brokerage_invoice_generation.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Brokerage_Invoice_Generation/brokerage_invoice_generation_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_REPORT:BOND_BROKERAGE_INVOICE_GENEARTION_REPORT:REPORT",
        },
        controller: "brokerageInvoiceGenerationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Brokerage_Invoice_Generation/brokerage_invoice_generation_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("brokerage_invoice_generation.inquiry_detail", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Brokerage_Invoice_Generation/brokerage_invoice_generation_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_REPORT:BOND_BROKERAGE_INVOICE_GENEARTION_REPORT:REPORT",
        },
        params: { obj: null },
        controller: "brokerageInvoiceGenerationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Brokerage_Invoice_Generation/brokerage_invoice_generation_controller.js?v=" + versionNum],
            });
          },
        },
      })

      /************************* Brokerage Invoice Geneartion ENDS*********************************/
      /* Rbi Bond Centralized Trial Run - Eligible Accounts GU Starts*/
      .state("rbibond_trail_run_brokerage", {
        abstract: true,
        url: "/rbibond_trail_run_brokerage",
        params: {
          views: ["add", "verify"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TRAIL_RUN_BROKERAGE:ADD", "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TRAIL_RUN_BROKERAGE:VERIFY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/trail_run_brokerage/trail_run_brokerage_service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_trail_run_brokerage.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/trail_run_brokerage/add_trail_run_brokerage.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TRAIL_RUN_BROKERAGE:ADD",
        },
        controller: "rbibondTrailRunBrokerageController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/trail_run_brokerage/trail_run_brokerage_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_trail_run_brokerage.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/trail_run_brokerage/verify_trail_run_brokerage.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TRAIL_RUN_BROKERAGE:VERIFY",
        },
        params: { obj: null },
        controller: "rbibondTrailRunBrokerageController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/trail_run_brokerage/trail_run_brokerage_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbibond_trail_run_brokerage.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/trail_run_brokerage/verify_trail_run_brokerage_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TRAIL_RUN_BROKERAGE:VERIFY",
        },
        params: { obj: null },
        controller: "rbibondTrailRunBrokerageController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/trail_run_brokerage/trail_run_brokerage_controller.js?v=" + versionNum],
            });
          },
        },
      })
      /* Rbi Bond Centralized Trial Run - Eligible Accounts GU Ends*/
      /* Rbi Bond Appendix4 Starts*/
      .state("rbibond_scroll_appendix_IV", {
        abstract: true,
        url: "/rbibond_scroll_appendix_IV",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_SCROLL_RBIBOND:RBIBOND_APPENDIX_FOUR:SCROLL"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_appendix_IV_scroll/rbibond_appendix_IV.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_scroll_appendix_IV.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/rbibond_appendix_IV_scroll/rbibond_appendix_IV_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:RBIBOND_APPENDIX_FOUR:SCROLL",
        },
        controller: "rbibondScrollAppendixIVController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_appendix_IV_scroll/rbiBond_appendix_IV.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbibond_scroll_appendix_IV.inquiry_details", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/rbibond_appendix_IV_scroll/rbibond_appendix_IV_6.5_Mar 2024_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:RBIBOND_APPENDIX_FOUR:SCROLL",
        },
        params: { obj: null },
        controller: "rbibondScrollAppendixIVController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_appendix_IV_scroll/rbiBond_appendix_IV.controller.js?v=" + versionNum],
            });
          },
        },
      })
      /* Rbi Bond Appendix4 Ends*/
      // Subscription Text file reporting RBI (Daily scroll) Starts
      .state("subscription_text_file_reporting_rbi", {
        abstract: true,
        url: "/subscription_text_file_reporting_rbi",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_SCROLL_RBIBOND:SUBSCRIPTION_TEXT_FILE_REPORTING_RBI:SCROLL"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Subscription_Text_file_reporting_RBI(Daily scroll)/Subscription_Text_file_reporting_RBI.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("subscription_text_file_reporting_rbi.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Subscription_Text_file_reporting_RBI(Daily scroll)/Subscription_Text_file_reporting_RBI_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:SUBSCRIPTION_TEXT_FILE_REPORTING_RBI:SCROLL",
        },
        controller: "SubscriptionTextFileController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Subscription_Text_file_reporting_RBI(Daily scroll)/Subscription_Text_file_reporting_RBI.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //  Subscription Text file reporting RBI (Daily scroll) Ends

      // Redemption Text file reporting RBI (Daily scroll) Starts
      .state("redemption_text_file_reporting_rbi", {
        abstract: true,
        url: "/redemption_text_file_reporting_rbi",
        params: {
          views: ["inquiry", "inquiry_details"],
          permissions: ["GBM_SCROLL_RBIBOND:REDEMPTION_TEXT_FILE_REPORTING_RBI:SCROLL"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Redemption_Text_file_reporting_RBI(Daily scroll)/Redemption_Text_file_reporting_RBI.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("redemption_text_file_reporting_rbi.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Redemption_Text_file_reporting_RBI(Daily scroll)/Redemption_Text_file_reporting_RBI_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:REDEMPTION_TEXT_FILE_REPORTING_RBI:SCROLL",
        },
        params: { obj: null },

        controller: "RedemptionTextFileController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Redemption_Text_file_reporting_RBI(Daily scroll)/Redemption_Text_file_reporting_RBI.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("redemption_text_file_reporting_rbi.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Redemption_Text_file_reporting_RBI(Daily scroll)/Redemption_Text_file_reporting_RBI_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:REDEMPTION_TEXT_FILE_REPORTING_RBI:SCROLL",
        },
        controller: "RedemptionTextFileController",
        params: { obj: null },
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Redemption_Text_file_reporting_RBI(Daily scroll)/Redemption_Text_file_reporting_RBI.controller.js?v=" + versionNum],
            });
          },
        },
      })
      // Redemption Text file reporting RBI (Daily scroll) Ends

      // Scheme Wise Outstanding Balance Report Starts
      .state("scheme_wise_outstanding_balance_report", {
        abstract: true,
        url: "/scheme_wise_outstanding_balance_report",
        params: {
          views: ["inquiry", "inquiry_details"],
          permissions: ["GBM_SCROLL_RBIBOND:SCHEME_WISE_OUTSTANDING_BALANCE_REPORT:SCROLL"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Scheme_Wise_Outstanding_Balanace_Report/Scheme_Wise_Outstanding_Balanace_Report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("scheme_wise_outstanding_balance_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Scheme_Wise_Outstanding_Balanace_Report/Scheme_Wise_Outstanding_Balanace_Report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:SCHEME_WISE_OUTSTANDING_BALANCE_REPORT:SCROLL",
        },
        params: { obj: null },
        controller: "SchemeWiseOutstandingBalanceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Scheme_Wise_Outstanding_Balanace_Report/Scheme_Wise_Outstanding_Balanace_Report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("scheme_wise_outstanding_balance_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Scheme_Wise_Outstanding_Balanace_Report/Scheme_Wise_Outstanding_Balanace_Report_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:SCHEME_WISE_OUTSTANDING_BALANCE_REPORT:SCROLL",
        },
        controller: "SchemeWiseOutstandingBalanceController",
        params: { obj: null },
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Scheme_Wise_Outstanding_Balanace_Report/Scheme_Wise_Outstanding_Balanace_Report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      // Scheme Wise Outstanding Balance Report Ends

      // Appendix II - Register of BLA - All Holders Starts
      .state("appendix_ii_register_of_bla_all_holders", {
        abstract: true,
        url: "/appendix_ii_register_of_bla_all_holders",
        params: {
          views: ["inquiry", "inquiry_details"],
          permissions: ["GBM_SCROLL_RBIBOND:APPENDIX_II_REGISTER_OF_BLA_ALL_HOLDERS:SCROLL"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Appendix_II_REGISTER_OF_BLA-ALL_HOLDERS/Appendix_II_REGISTER_OF_BLA_ALL_HOLDERS.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("appendix_ii_register_of_bla_all_holders.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Appendix_II_REGISTER_OF_BLA-ALL_HOLDERS/Appendix_II_REGISTER_OF_BLA_ALL_HOLDERS_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:APPENDIX_II_REGISTER_OF_BLA_ALL_HOLDERS:SCROLL",
        },
        params: { obj: null },
        controller: "AppendixIIRegisterOfBlaController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Appendix_II_REGISTER_OF_BLA-ALL_HOLDERS/Appendix_II_REGISTER_OF_BLA_ALL_HOLDERS.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("appendix_ii_register_of_bla_all_holders.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Appendix_II_REGISTER_OF_BLA-ALL_HOLDERS/Appendix_II_REGISTER_OF_BLA_ALL_HOLDERS_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:APPENDIX_II_REGISTER_OF_BLA_ALL_HOLDERS:SCROLL",
        },
        controller: "AppendixIIRegisterOfBlaController",
        params: { obj: null },
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Appendix_II_REGISTER_OF_BLA-ALL_HOLDERS/Appendix_II_REGISTER_OF_BLA_ALL_HOLDERS.controller.js?v=" + versionNum],
            });
          },
        },
      })
      // Appendix II - Register of BLA - All Holders Ends

      // Appendix VI - Statement of Bonds Issued/Repaid Starts
      .state("appendix_vi", {
        abstract: true,
        url: "/appendix_vi",
        params: {
          views: ["inquiry", "inquiry_details"],
          permissions: ["GBM_SCROLL_RBIBOND:APPENDIX_VI:SCROLL"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Appendix_VI/Appendix_VI.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("appendix_vi.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Appendix_VI/Appendix_VI_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:APPENDIX_VI:SCROLL",
        },
        params: { obj: null },
        controller: "AppendixVIController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Appendix_VI/Appendix_VI.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("appendix_vi.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Appendix_VI/Appendix_VI_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:APPENDIX_VI:SCROLL",
        },
        controller: "AppendixVIController",
        params: { obj: null },
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Appendix_VI/Appendix_VI.controller.js?v=" + versionNum],
            });
          },
        },
      })
      // Appendix VI - Statement of Bonds Issued/Repaid Ends

      // Appendix III - Register of Nomination Starts
      .state("appendix_iii_register_of_nomination", {
        abstract: true,
        url: "/appendix_iii_register_of_nomination",
        params: {
          views: ["inquiry", "inquiry_details"],
          permissions: ["GBM_SCROLL_RBIBOND:APPENDIX_III_REGISTER_OF_NOMINATION:SCROLL"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Appendix_III_REGISTER_OF_NOMINATION/Appendix_III_REGISTER_OF_NOMINATION.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("appendix_iii_register_of_nomination.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Appendix_III_REGISTER_OF_NOMINATION/Appendix_III_REGISTER_OF_NOMINATION_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:APPENDIX_III_REGISTER_OF_NOMINATION:SCROLL",
        },
        params: { obj: null },
        controller: "AppendixIIIRegisterOfNominationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Appendix_III_REGISTER_OF_NOMINATION/Appendix_III_REGISTER_OF_NOMINATION.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("appendix_iii_register_of_nomination.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Appendix_III_REGISTER_OF_NOMINATION/Appendix_III_REGISTER_OF_NOMINATION_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:APPENDIX_III_REGISTER_OF_NOMINATION:SCROLL",
        },
        controller: "AppendixIIIRegisterOfNominationController",
        params: { obj: null },
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Appendix_III_REGISTER_OF_NOMINATION/Appendix_III_REGISTER_OF_NOMINATION.controller.js?v=" + versionNum],
            });
          },
        },
      })
      // Appendix III - Register of Nomination Ends

      // Brokerage Claim & Handling Commission Starts
      .state("brokerage_claim_handling_commission", {
        abstract: true,
        url: "/brokerage_claim_handling_commission",
        params: {
          views: ["inquiry", "inquiry_details"],
          permissions: ["GBM_SCROLL_RBIBOND:BROKERAGE_CLAIM_HANDLING_COMMISSION:SCROLL"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Brokerage_Claim_&_Handling_Commission/Brokerage_Claim_&_Handling_Commission.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("brokerage_claim_handling_commission.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Brokerage_Claim_&_Handling_Commission/Brokerage_Claim_&_Handling_Commission_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:BROKERAGE_CLAIM_HANDLING_COMMISSION:SCROLL",
        },
        params: { obj: null },
        controller: "BrokerageClaimHandlingCommissionController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Brokerage_Claim_&_Handling_Commission/Brokerage_Claim_&_Handling_Commission.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("brokerage_claim_handling_commission.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Brokerage_Claim_&_Handling_Commission/Brokerage_Claim_&_Handling_Commission_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:BROKERAGE_CLAIM_HANDLING_COMMISSION:SCROLL",
        },
        controller: "BrokerageClaimHandlingCommissionController",
        params: { obj: null },
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Brokerage_Claim_&_Handling_Commission/Brokerage_Claim_&_Handling_Commission.controller.js?v=" + versionNum],
            });
          },
        },
      })
      // Brokerage Claim & Handling Commission Ends

      // Monthly Brokerage Claim Starts
      .state("monthly_brokerage_claim_scroll", {
        abstract: true,
        url: "/monthly_brokerage_claim_scroll",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_SCROLL_RBIBOND:MONTHLY_BROKERAGE_CLAIM_SCROLL:SCROLL"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Monthly_Brokerage_Claim/Monthly_Brokerage_Claim.Service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("monthly_brokerage_claim_scroll.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Monthly_Brokerage_Claim/Monthly_Brokerage_Claim_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:MONTHLY_BROKERAGE_CLAIM_SCROLL:SCROLL",
        },
        controller: "MonthlyBrokerageClaimController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Monthly_Brokerage_Claim/Monthly_Brokerage_Claim.Controller.js?v=" + versionNum],
            });
          },
        },
      })
      // Monthly Brokerage Claim ends

      //scroll-principal scroll report XII starts
      .state("Principal_scrollreport_5106_Mar_24_Appendix_XII", {
        abstract: true,
        url: "/Principal_scrollreport_5106_Mar_24_Appendix_XII",
        params: {
          permissions: ["GBM_SCROLL_RBIBOND:PRINCIPAL_SCROLLREPORT_5106_MAR_24_APPENDIX_XII:SCROLL"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Principal_scrollreport_5106_Mar 24_Appendix_XII/Principal_scrollreport_5106_Mar 24_Appendix_XII.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Principal_scrollreport_5106_Mar_24_Appendix_XII.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Principal_scrollreport_5106_Mar 24_Appendix_XII/Principal_scrollreport_5106_Mar 24_Appendix_XII_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:PRINCIPAL_SCROLLREPORT_5106_MAR_24_APPENDIX_XII:SCROLL",
        },
        controller: "principalScrollreportAppendixXIIController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Principal_scrollreport_5106_Mar 24_Appendix_XII/Principal_scrollreport_5106_Mar 24_Appendix_XII.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Principal_scrollreport_5106_Mar_24_Appendix_XII.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Principal_scrollreport_5106_Mar 24_Appendix_XII/Principal_scrollreport_5106_Mar 24_Appendix_XII_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:PRINCIPAL_SCROLLREPORT_5106_MAR_24_APPENDIX_XII:SCROLL",
        },
        params: {
          obj: null,
        },
        controller: "principalScrollreportAppendixXIIController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Principal_scrollreport_5106_Mar 24_Appendix_XII/Principal_scrollreport_5106_Mar 24_Appendix_XII.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //scroll-principal scroll report XII ends

      //scroll-Annual Information Report Starts
      .state("Annual_Informatation_Report", {
        abstract: true,
        url: "/Annual_Informatation_Report",
        params: {
          permissions: ["GBM_SCROLL_RBIBOND:ANNUAL_INFORMATATION_REPORT:SCROLL"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/AIR_Reporting_Annual_Information_Report/AIR_Reporting_Annual_Information_Report.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Annual_Informatation_Report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/AIR_Reporting_Annual_Information_Report/AIR_Reporting_Annual_Information_Report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:ANNUAL_INFORMATATION_REPORT:SCROLL",
        },
        controller: "AnnualInfoController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/AIR_Reporting_Annual_Information_Report/AIR_Reporting_Annual_Information_Report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Annual_Informatation_Report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/AIR_Reporting_Annual_Information_Report/AIR_Reporting_Annual_Information_Report_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:ANNUAL_INFORMATATION_REPORT:SCROLL",
        },
        params: {
          obj: null,
        },
        controller: "AnnualInfoController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/AIR_Reporting_Annual_Information_Report/AIR_Reporting_Annual_Information_Report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //scroll-Annual Informtion Report Ends




   //scroll-Appendix_IV_8_5_Mar_2024 Starts
         .state("Appendix_IV_8_5_Mar_2024", {
           abstract: true,
           url: "/Appendix_IV_8_5_Mar_2024",
           params: {
             views: ["inquiry"],
             permissions: ["GBM_SCROLL_RBIBOND:Appendix_IV_8_5_Mar_2024:SCROLL"],
           },
           resolve: {
             load: function ($ocLazyLoad) {
               return $ocLazyLoad.load({
                 files: ["app/rbi_bond_module/gu_functionallity/scrolls/Appendix_IV_8_5_Mar_2024/Appendix_IV_8_5_Mar_2024.service.js?v=" + versionNum],
               });
             },
           },
         })
         .state("Appendix_IV_8_5_Mar_2024.inquiry", {
           url: "/action/inquiry",
           templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Appendix_IV_8_5_Mar_2024/Appendix_IV_8_5_Mar_2024_input.html",
           data: {
             authRequired: true,
             requiredPermission: "GBM_SCROLL_RBIBOND:Appendix_IV_8_5_Mar_2024:SCROLL",
           },
           controller: "AppendixIVController",
           controllerAs: "vm",
           resolve: {
             load: function ($ocLazyLoad) {
               return $ocLazyLoad.load({
                 files: ["app/rbi_bond_module/gu_functionallity/scrolls/Appendix_IV_8_5_Mar_2024/Appendix_IV_8_5_Mar_2024.controller.js?v=" + versionNum],
               });
             },
           },
         })
         .state("Appendix_IV_8_5_Mar_2024.inquiry_details", {
           url: "/action/inquiry_details",
           templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Appendix_IV_8_5_Mar_2024/Appendix_IV_8_5_Mar_2024_details.html",
           data: {
             authRequired: true,
             requiredPermission: "GBM_SCROLL_RBIBOND:Appendix_IV_8_5_Mar_2024:SCROLL",
           },
           params: {
             obj: null,
           },
           controller: "AppendixIVController",
           controllerAs: "vm",
           resolve: {
             load: function ($ocLazyLoad) {
               return $ocLazyLoad.load({
                 files: ["app/rbi_bond_module/gu_functionallity/scrolls/Appendix_IV_8_5_Mar_2024/Appendix_IV_8_5_Mar_2024.controller.js?v=" + versionNum],
               });
             },
           },
         })
         //scroll-Appendix_IV_8_5_Mar_2024 Ends


      /* EOD BOD inquiry Link cell */

      .state("pending_brokerage_payment_status_brokerage_claim_status", {
        abstract: true,
        url: "/pending_brokerage_payment_status_brokerage_claim_status",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:PENDING_BRKGE_PAYMENT_CLAIM_DETAILS:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/pending_brokerage_payment_status_brokerage_claim_status/pending_brokerage_payment_status_brokerage_claim_status.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("pending_brokerage_payment_status_brokerage_claim_status.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/pending_brokerage_payment_status_brokerage_claim_status/pending_brokerage_payment_status_brokerage_claim_status_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:PENDING_BRKGE_PAYMENT_CLAIM_DETAILS:REPORT",
        },
        controller: "pendingBrokeragePaymentClaimStatusController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/pending_brokerage_payment_status_brokerage_claim_status/pending_brokerage_payment_status_brokerage_claim_status.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("pending_brokerage_payment_status_brokerage_claim_status.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/pending_brokerage_payment_status_brokerage_claim_status/pending_brokerage_payment_status_brokerage_claim_status_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:PENDING_BRKGE_PAYMENT_CLAIM_DETAILS:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "pendingBrokeragePaymentClaimStatusController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/pending_brokerage_payment_status_brokerage_claim_status/pending_brokerage_payment_status_brokerage_claim_status.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /* Rbi Bond INWARD AND OUTWARD Starts*/
      .state("inward_and_outward", {
        abstract: true,
        url: "/inward_and_outward",
        params: {
          views: ["add"],
          permissions: ["GBM_GU_RBIBOND:RBIBOND_INWARD_OUTWARD:ADD"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/inward_and_outward/inward_and_outward.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("inward_and_outward.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/inward_and_outward/add_inward_and_ouutward.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_INWARD_OUTWARD:ADD",
        },
        controller: "bondInwardOutwardController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/inward_and_outward/inward_and_outward.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("inward_and_outward.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/inward_and_outward/add_inward_and_outward_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_INWARD_OUTWARD:ADD",
        },
        params: { obj: null },
        controller: "bondInwardOutwardController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/inward_and_outward/inward_and_outward.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /* Rbi Bond INWARD AND OUTWARD ENDS*/

      /* Rbi Bond Centralized Trial Run Starts*/
      .state("rbi_bond_centralized_trial_run", {
        abstract: true,
        url: "/rbi_bond_centralized_trial_run",
        params: {
          views: ["add", "verify"],
          permissions: ["GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_TRIAL_RUN:ADD", "GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_TRIAL_RUN:VERIFY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/dc/rbi_bond/rbi_bond_centralized_trial_run/rbi_bond_centralized_trial_run.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_centralized_trial_run.add", {
        url: "/add",
        templateUrl: "app/dc/rbi_bond/rbi_bond_centralized_trial_run/rbi_bond_centralized_trial_run.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_TRIAL_RUN:ADD",
        },
        controller: "rbiBondCentralizedTrialRunController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_centralized_trial_run/rbi_bond_centralized_trial_run.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_centralized_trial_run.add_detail", {
        url: "/add/detail",
        templateUrl: "app/dc/rbi_bond/rbi_bond_centralized_trial_run/rbi_bond_centralized_trial_run_list.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_TRIAL_RUN:ADD",
        },
        params: { obj: null },
        controller: "rbiBondCentralizedTrialRunController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_centralized_trial_run/rbi_bond_centralized_trial_run.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bond_centralized_trial_run.verify", {
        url: "/action/verify",
        templateUrl: "app/dc/rbi_bond/rbi_bond_centralized_trial_run/verify_rbi_bond_centralized_trial_run.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_TRIAL_RUN:VERIFY",
        },
        controller: "rbiBondCentralizedTrialRunController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_centralized_trial_run/rbi_bond_centralized_trial_run.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bond_centralized_trial_run.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/dc/rbi_bond/rbi_bond_centralized_trial_run/verify_rbi_bond_centralized_trial_run_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_TRIAL_RUN:VERIFY",
        },
        params: { obj: null },
        controller: "rbiBondCentralizedTrialRunController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/rbi_bond/rbi_bond_centralized_trial_run/rbi_bond_centralized_trial_run.controller.js?v=" + versionNum],
            });
          },
        },
      })
      /* Rbi Bond Centralized Trial Run Ends*/
      /* Rbi Bond Trial Run for Redemption Starts*/
      .state("rbi_bond_trial_run_for_redemption", {
        abstract: true,
        url: "/rbi_bond_trial_run_for_redemption",
        params: {
          views: ["add", "verify"],
          permissions: ["GBM_GU_RBIBOND:RBI_BOND_TRIAL_RUN_FOR_REDEMPTION:ADD", "GBM_GU_RBIBOND:RBI_BOND_TRIAL_RUN_FOR_REDEMPTION:VERIFY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_trial_run_for_redemption/rbi_bond_trial_run_for_redemption.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_trial_run_for_redemption.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_trial_run_for_redemption/rbi_bond_trial_run_for_redemption.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBI_BOND_TRIAL_RUN_FOR_REDEMPTION:ADD",
        },
        controller: "rbiBondTrialRunForRedemptionController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_trial_run_for_redemption/rbi_bond_trial_run_for_redemption.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_trial_run_for_redemption.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_trial_run_for_redemption/verify_rbi_bond_trial_run_for_redemption.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBI_BOND_TRIAL_RUN_FOR_REDEMPTION:VERIFY",
        },
        controller: "rbiBondTrialRunForRedemptionController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_trial_run_for_redemption/rbi_bond_trial_run_for_redemption.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bond_trial_run_for_redemption.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_trial_run_for_redemption/verify_rbi_bond_trial_run_for_redemption_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBI_BOND_TRIAL_RUN_FOR_REDEMPTION:VERIFY",
        },
        params: { obj: null },
        controller: "rbiBondTrialRunForRedemptionController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_trial_run_for_redemption/rbi_bond_trial_run_for_redemption.controller.js?v=" + versionNum],
            });
          },
        },
      })
      /* Rbi Bond Centralized Trial Run Ends*/

      /// Death marking for BLA starts here

      .state("death_marking_for_bla", {
        abstract: true,
        url: "/death_marking_for_bla",
        params: {
          views: ["add", "verify", "inquiry"],
          permissions: ["GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:ADD", "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:VERIFY", "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:ENQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/death_marking_for_bla/death_marking_for_bla.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("death_marking_for_bla.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/death_marking_for_bla/add_death_marking_for_bla.html",

        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:ADD",
        },
        controller: "rbibondBLADeathMarkingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/death_marking_for_bla/death_marking_for_bla.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("death_marking_for_bla.add_detail", {
        url: "/action/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/death_marking_for_bla/add_death_marking_for_bla_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:ADD",
        },
        params: { obj: null },
        controller: "rbibondBLADeathMarkingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/death_marking_for_bla/death_marking_for_bla.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("death_marking_for_bla.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/death_marking_for_bla/verify_death_marking_for_bla.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:VERIFY",
        },
        controller: "rbibondBLADeathMarkingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/death_marking_for_bla/death_marking_for_bla.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("death_marking_for_bla.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/death_marking_for_bla/verify_death_marking_for_bla_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:VERIFY",
        },
        params: { obj: null },
        controller: "rbibondBLADeathMarkingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/death_marking_for_bla/death_marking_for_bla.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("death_marking_for_bla.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/death_marking_for_bla/inquiry_death_marking_for_bla.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:ENQUIRY",
        },
        controller: "rbibondBLADeathMarkingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/death_marking_for_bla/death_marking_for_bla.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("death_marking_for_bla.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/death_marking_for_bla/inquiry_death_marking_for_bla_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_BLA_SUBSCRIPTION_RBI_BOND:ENQUIRY",
        },
        params: { obj: null },
        controller: "rbibondBLADeathMarkingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/death_marking_for_bla/death_marking_for_bla.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /// Death marking for BLA ends here

      ////NetBankingTransactionforallholdersreportpdf  Start
      .state("Net_Banking_Transaction_for_all_holders_report_pdf", {
        abstract: true,
        url: "/Net_Banking_Transaction_for_all_holders_report_pdf",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:NET_BANKING_TRANSECTION_FOR_ALL_HOLDERS:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Net_Banking_Transaction_for_all_holders_report_pdf/Net_Banking_Transaction_for_all_holders_report_pdf.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("Net_Banking_Transaction_for_all_holders_report_pdf.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Net_Banking_Transaction_for_all_holders_report_pdf/Net_Banking_Transaction_for_all_holders_report_pdf_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:NET_BANKING_TRANSECTION_FOR_ALL_HOLDERS:REPORT",
        },
        controller: "netBankingTransactionforallholdersreportpdfController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Net_Banking_Transaction_for_all_holders_report_pdf/Net_Banking_Transaction_for_all_holders_report_pdf.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("Net_Banking_Transaction_for_all_holders_report_pdf.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Net_Banking_Transaction_for_all_holders_report_pdf/Net_Banking_Transaction_for_all_holders_report_pdf_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:NET_BANKING_TRANSECTION_FOR_ALL_HOLDERS:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "netBankingTransactionforallholdersreportpdfController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Net_Banking_Transaction_for_all_holders_report_pdf/Net_Banking_Transaction_for_all_holders_report_pdf.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //      End
      //    Start of TDS Exemption Submitted / Non-Submitted report
      .state("TDS_Exemption_Submitted_Non_Submitted", {
        abstract: true,
        url: "/TDS_Exemption_Submitted_Non_Submitted",
        params: {
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:TDS_Exemption_Submitted_Non_Submitted:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/TDS_Exemption_Submitted_Non_Submitted/TDS_Exemption_Submitted_Non_Submitted.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("TDS_Exemption_Submitted_Non_Submitted.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/TDS_Exemption_Submitted_Non_Submitted/TDS_Exemption_Submitted_Non_Submitted_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:TDS_Exemption_Submitted_Non_Submitted:REPORT",
        },
        controller: "TDSExemptionSubmitController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/TDS_Exemption_Submitted_Non_Submitted/TDS_Exemption_Submitted_Non_Submitted.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("TDS_Exemption_Submitted_Non_Submitted.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/TDS_Exemption_Submitted_Non_Submitted/TDS_Exemption_Submitted_Non_Submitted_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:TDS_Exemption_Submitted_Non_Submitted:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "TDSExemptionSubmitController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/TDS_Exemption_Submitted_Non_Submitted/TDS_Exemption_Submitted_Non_Submitted.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //      End of TDS Exemption Submitted / Non-Submitted report
      //            Bond Actual Interest Run Credited Report
      .state("bond_actual_interest_run_credited_report", {
        abstract: true,
        url: "/bond_actual_interest_run_credited_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:BOND_ACTUAL_INTEREST_RUN_CREDITED_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Actual_Interest_Run_Credited_Report/Bond_Actual_Interest_Run_Credited_Report.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_actual_interest_run_credited_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Actual_Interest_Run_Credited_Report/Bond_Actual_Interest_Run_Credited_Report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BOND_ACTUAL_INTEREST_RUN_CREDITED_REPORT:REPORT",
        },
        controller: "bondActualInterestRunCreditedReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Actual_Interest_Run_Credited_Report/Bond_Actual_Interest_Run_Credited_Report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("bond_actual_interest_run_credited_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Actual_Interest_Run_Credited_Report/Bond_Actual_Interest_Run_Credited_Report_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BOND_ACTUAL_INTEREST_RUN_CREDITED_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "bondActualInterestRunCreditedReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Actual_Interest_Run_Credited_Report/Bond_Actual_Interest_Run_Credited_Report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      // End of Bond Actual Interest Run Credited Report

      //           START OF TDS Remittance Fincon file
      .state("tds_remittance_fincon_file_report", {
        abstract: true,
        url: "/tds_remittance_fincon_file_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:TDS_REMITTANCE_FINCON_FILE_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/TDS_Remittance_Fincon_File_Report/TDS_Remittance_Fincon_File_Report.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("tds_remittance_fincon_file_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/TDS_Remittance_Fincon_File_Report/TDS_Remittance_Fincon_File_Report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:TDS_REMITTANCE_FINCON_FILE_REPORT:REPORT",
        },
        controller: "tdsRemittanceFinconFileReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/TDS_Remittance_Fincon_File_Report/TDS_Remittance_Fincon_File_Report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("tds_remittance_fincon_file_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/TDS_Remittance_Fincon_File_Report/TDS_Remittance_Fincon_File_Report_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:TDS_REMITTANCE_FINCON_FILE_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "tdsRemittanceFinconFileReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/TDS_Remittance_Fincon_File_Report/TDS_Remittance_Fincon_File_Report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      // End of TDS Remittance Fincon file

      /* RBI Bond Brokerage Invoice & Email Body Letter - Start */
      .state("brokerage_invoice_and_email_body_letter", {
        abstract: true,
        url: "/brokerage_invoice_and_email_body_letter",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:BROKERAGE_INVOICE_EMAIL_BODY_LETTER_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/brokerage_invoice_and_email_body_letter/brokerage_invoice_and_email_body_letter_service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("brokerage_invoice_and_email_body_letter.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/brokerage_invoice_and_email_body_letter/brokerage_invoice_and_email_body_letter_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BROKERAGE_INVOICE_EMAIL_BODY_LETTER_REPORT:REPORT",
        },
        controller: "brokerageInvoiceAndEmailBodyLetterController",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/brokerage_invoice_and_email_body_letter/brokerage_invoice_and_email_body_letter_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("brokerage_invoice_and_email_body_letter.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/brokerage_invoice_and_email_body_letter/brokerage_invoice_and_email_body_letter_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BROKERAGE_INVOICE_EMAIL_BODY_LETTER_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "brokerageInvoiceAndEmailBodyLetterController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/brokerage_invoice_and_email_body_letter/brokerage_invoice_and_email_body_letter_controller.js?v=" + versionNum],
            });
          },
        },
      })
      /* RBI Bond Brokerage Invoice & Email Body Letter - End */
      /* CALL BACK REPORT - START */

      .state("callback_report", {
        abstract: true,
        url: "/callback_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:CALLBACK_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/callback_report/callback_report_service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("callback_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/callback_report/callback_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:CALLBACK_REPORT:REPORT",
        },
        controller: "callbackReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/callback_report/callback_report_controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("callback_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/callback_report/callback_report_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:CALLBACK_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "callbackReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/callback_report/callback_report_controller.js?v=" + versionNum],
            });
          },
        },
      })
      /* CALL BACK REPORT - END */

      //Subscription Account Opened Details starts
      .state("rbiBond_bond_inquiry_subs_acc_det_report", {
        abstract: true,
        url: "/rbiBond_bond_inquiry_subs_acc_det_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_SUBS_ACCOUNT_INQUIRY_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Subscription_Account_Opened_Details/Subscription_Account_Opened_Details.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbiBond_bond_inquiry_subs_acc_det_report.ENQUIRY", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Subscription_Account_Opened_Details/Subscription_Account_Opened_Details_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_SUBS_ACCOUNT_INQUIRY_REPORT:REPORT",
        },
        controller: "SubscriptionAccountController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Subscription_Account_Opened_Details/Subscription_Account_Opened_Details.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbiBond_bond_inquiry_subs_acc_det_report.inquiry_detail", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Subscription_Account_Opened_Details/Subscription_Account_Opened_Details_output.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_SUBS_ACCOUNT_INQUIRY_REPORT:REPORT",
        },
        params: { obj: null },
        controller: "SubscriptionAccountController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Subscription_Account_Opened_Details/Subscription_Account_Opened_Details.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //Subscription Account Opened Details ends

      // start of Future TDS Calculation Report
            .state("Future_TDS_Calculation_Report", {
              abstract: true,
              url: "/Future_TDS_Calculation_Report",
              params: {
                views: ["inquiry"],
                permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:FUTURE_TDS_CALCULATION_REPORT:REPORT"],
              },
              resolve: {
                load: function ($ocLazyLoad) {
                  return $ocLazyLoad.load({
                    files: ["app/rbi_bond_module/gu_functionallity/reports/Future_TDS_Calculation_Report/Future_TDS_Calculation_Report.service.js?v=" + versionNum],
                  });
                },
              },
            })
            .state("Future_TDS_Calculation_Report.inquiry", {
              url: "/action/inquiry",
              templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Future_TDS_Calculation_Report/Future_TDS_Calculation_Report_input.html",
              data: {
                authRequired: true,
                requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:FUTURE_TDS_CALCULATION_REPORT:REPORT",
              },
              controller: "FutureTdsController",
              controllerAs: "vm",
              resolve: {
                load: function ($ocLazyLoad) {
                  return $ocLazyLoad.load({
                    files: ["app/rbi_bond_module/gu_functionallity/reports/Future_TDS_Calculation_Report/Future_TDS_Calculation_Report.controller.js?v=" + versionNum],
                  });
                },
              },
            })

//     End of Future TDS Calculation Report


      //           START OF E-TDS Report
      .state("e-tds_report", {
        abstract: true,
        url: "/e-tds_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:E-TDS_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/E-TDS_Report/E-TDS_Report.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("e-tds_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/E-TDS_Report/E-TDS_Report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:E-TDS_REPORT:REPORT",
        },
        controller: "eTDSReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/E-TDS_Report/E-TDS_Report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("e-tds_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/E-TDS_Report/E-TDS_Report_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:E-TDS_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "eTDSReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/E-TDS_Report/E-TDS_Report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      // End of E-TDS Report
      // Start of Subscription Amount Details/ BLA Account Number Details / Matured Account Details/ Account Closed Details
      .state("Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed", {
        abstract: true,
        url: "/Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed",
        params: {
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Subscription_Amount_BLA_Matured_Account_Closed/Subs_Amt_BLA_Matured_Acc_Closed.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Subscription_Amount_BLA_Matured_Account_Closed/Subs_Amt_BLA_Matured_Acc_Closed_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed:REPORT",
        },
        controller: "SubsAmtBLAMaturedAccClosedController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Subscription_Amount_BLA_Matured_Account_Closed/Subs_Amt_BLA_Matured_Acc_Closed.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed.subscription_amount_details", {
        url: "/action/subscription_amount_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Subscription_Amount_BLA_Matured_Account_Closed/Subscription_Amount_Details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "SubsAmtBLAMaturedAccClosedController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Subscription_Amount_BLA_Matured_Account_Closed/Subs_Amt_BLA_Matured_Acc_Closed.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed.bla_account_details", {
        url: "/action/bla_account_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Subscription_Amount_BLA_Matured_Account_Closed/BLA_Account_Number_Details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "SubsAmtBLAMaturedAccClosedController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Subscription_Amount_BLA_Matured_Account_Closed/Subs_Amt_BLA_Matured_Acc_Closed.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed.matured_account_details", {
        url: "/action/matured_account_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Subscription_Amount_BLA_Matured_Account_Closed/Matured_Account_Details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "SubsAmtBLAMaturedAccClosedController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Subscription_Amount_BLA_Matured_Account_Closed/Subs_Amt_BLA_Matured_Acc_Closed.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed.account_closed_details", {
        url: "/action/account_closed_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Subscription_Amount_BLA_Matured_Account_Closed/Account_Closed_Details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "SubsAmtBLAMaturedAccClosedController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Subscription_Amount_BLA_Matured_Account_Closed/Subs_Amt_BLA_Matured_Acc_Closed.controller.js?v=" + versionNum],
            });
          },
        },
      })
      // End of Subscription Amount Details/ BLA Account Number Details / Matured Account Details/ Account Closed Details

      //Bond Interest Not Credited Report
      .state("rbiBond_bond_interest_not_credited_report", {
        abstract: true,
        url: "/rbiBond_bond_interest_not_credited_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INTEREST_NOT_CREDITED_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Interest_not_Credited/Bond_Interest_not_Credited.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbiBond_bond_interest_not_credited_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Interest_not_Credited/Bond_Interest_not_Credited_Input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INTEREST_NOT_CREDITED_REPORT:REPORT",
        },
        controller: "InterestNotCreditedController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Interest_not_Credited/Bond_Interest_not_Credited.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbiBond_bond_interest_not_credited_report.inquiry_detail", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Interest_not_Credited/Bond_Interest_not_Credited_Detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INTEREST_NOT_CREDITED_REPORT:REPORT",
        },
        params: { obj: null },
        controller: "InterestNotCreditedController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Interest_not_Credited/Bond_Interest_not_Credited.controller.js?v=" + versionNum],
            });
          },
        },
      }) // End of E-TDS Report
      //Nominee Continuation Screen

      .state("rbi_bond_nominee_continuation", {
        abstract: true,
        url: "/rbi_bond_nominee_continuation",
        params: {
          views: ["add", "verify", "inquiry"],
          permissions: ["GBM_GU_RBIBOND:RBIBOND_NOMINEE_CONTINUATION:ADD", "GBM_GU_RBIBOND:RBIBOND_NOMINEE_CONTINUATION:VERIFY", "GBM_GU_RBIBOND:RBIBOND_NOMINEE_CONTINUATION:ENQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_nominee_continuation/rbi_bond_nominee_continuation.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_nominee_continuation.add_input", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_nominee_continuation/add_rbi_bond_nominee_continuation_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_NOMINEE_CONTINUATION:ADD",
        },
        params: { obj: null },
        controller: "rbibondNomineeContinuationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_nominee_continuation/rbi_bond_nominee_continuation.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_nominee_continuation.add_detail", {
        url: "/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_nominee_continuation/add_rbi_bond_nominee_continuation_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_NOMINEE_CONTINUATION:ADD",
        },
        params: { obj: null },
        controller: "rbibondNomineeContinuationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_nominee_continuation/rbi_bond_nominee_continuation.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bond_nominee_continuation.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_nominee_continuation/verify_reject_rbi_bond_nominee_continuation.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_NOMINEE_CONTINUATION:VERIFY",
        },
        controller: "rbibondNomineeContinuationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_nominee_continuation/rbi_bond_nominee_continuation.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bond_nominee_continuation.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_nominee_continuation/verify_reject_rbi_bond_nominee_continuation_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_NOMINEE_CONTINUATION:VERIFY",
        },
        params: { obj: null },
        controller: "rbibondNomineeContinuationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_nominee_continuation/rbi_bond_nominee_continuation.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("rbi_bond_nominee_continuation.list", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_nominee_continuation/inquiry_rbi_bond_nominee_continuation.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_NOMINEE_CONTINUATION:ENQUIRY",
        },
        controller: "rbibondNomineeContinuationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_nominee_continuation/rbi_bond_nominee_continuation.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("rbi_bond_nominee_continuation.list_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/rbi_bond_nominee_continuation/inquiry_rbi_bond_nominee_continuation_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_NOMINEE_CONTINUATION:ENQUIRY",
        },
        params: { obj: null },
        controller: "rbibondNomineeContinuationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/rbi_bond_nominee_continuation/rbi_bond_nominee_continuation.controller.js?v=" + versionNum],
            });
          },
        },
      })

      //Bond Interest Not Credited Report ends

      //  START OF Report for Cancellation of Subscription CAT & CBT

      .state("report_for_cancellation_subscription_cat_cbt", {
        abstract: true,
        url: "/report_for_cancellation_subscription_cat_cbt",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:REPORT_FOR_CANCELLATION_SUBSCRIPTION_CAT_CBT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Report_cancellation_subscription_CAT_CBT/Report_cancellation_subscription_CAT_CBT.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("report_for_cancellation_subscription_cat_cbt.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Report_cancellation_subscription_CAT_CBT/Report_cancellation_subscription_CAT_CBT_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:REPORT_FOR_CANCELLATION_SUBSCRIPTION_CAT_CBT:REPORT",
        },
        controller: "reportCancellationSubsCatCbtController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Report_cancellation_subscription_CAT_CBT/Report_cancellation_subscription_CAT_CBT.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //           End of Report for Cancellation of Subscription CAT & CBT

      //Brokerage Claim_Monthly_Broker Wise Income & Expense starts
      .state("brokerage_claim_monthly_broker_wise_income&expense", {
        abstract: true,
        url: "/brokerage_claim_monthly_broker_wise_income&expense",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:BROKERAGE_CLAIM_MONTHLY_BROKER_WISE_INCOME&EXPENCE:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Brokerage_Claim_Monthly_Broker_Wise_Income_Expense/Brokerage_Claim_Monthly_Broker_Wise_Income_Expense.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("brokerage_claim_monthly_broker_wise_income&expense.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Brokerage_Claim_Monthly_Broker_Wise_Income_Expense/Brokerage_Claim_Monthly_Broker_Wise_Income_Expense_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BROKERAGE_CLAIM_MONTHLY_BROKER_WISE_INCOME&EXPENCE:REPORT",
        },
        controller: "BrokerageClaimController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Brokerage_Claim_Monthly_Broker_Wise_Income_Expense/Brokerage_Claim_Monthly_Broker_Wise_Income_Expense.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("brokerage_claim_monthly_broker_wise_income&expense.inquiry_detail", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Brokerage_Claim_Monthly_Broker_Wise_Income_Expense/Brokerage_Claim_Monthly_Broker_Wise_Income_Expense_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BROKERAGE_CLAIM_MONTHLY_BROKER_WISE_INCOME&EXPENCE:REPORT",
        },
        params: { obj: null },
        controller: "BrokerageClaimController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Brokerage_Claim_Monthly_Broker_Wise_Income_Expense/Brokerage_Claim_Monthly_Broker_Wise_Income_Expense.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //Brokerage Claim_Monthly_Broker Wise Income & Expense ends

      // starts of the Broker_Appointment_Enrolled_Report_Letter
      .state("Broker_Appointment_Enrolled_Report_Letter", {
        abstract: true,
        url: "/Broker_Appointment_Enrolled_Report_Letter",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:BROKER_APPOINTMENT_ENROLLED_REPORT_LETTER:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Broker_Appointment_Enrolled_Report_Letter/Broker_Appointment_Enrolled_Report_Letter.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Broker_Appointment_Enrolled_Report_Letter.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Broker_Appointment_Enrolled_Report_Letter/Broker_Appointment_Enrolled_Report_Letter_Input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BROKER_APPOINTMENT_ENROLLED_REPORT_LETTER:REPORT",
        },
        controller: "brokerAppointmentController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Broker_Appointment_Enrolled_Report_Letter/Broker_Appointment_Enrolled_Report_Letter.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Broker_Appointment_Enrolled_Report_Letter.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Broker_Appointment_Enrolled_Report_Letter/Broker_Appointment_Enrolled_Report_Letter_Output.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BROKER_APPOINTMENT_ENROLLED_REPORT_LETTER:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "brokerAppointmentController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Broker_Appointment_Enrolled_Report_Letter/Broker_Appointment_Enrolled_Report_Letter.controller.js?v=" + versionNum],
            });
          },
        },
      })
      // ends the Broker_Appointment_Enrolled_Report_Letter

      // starts of the freeze and un-freeze report
      .state("Freeze_Un_Freeze_Report", {
        abstract: true,
        url: "/Freeze_Un_Freeze_Report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:FREEZE_UN_FREEZE:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Freeze_Un_Freeze_Report/Freeze_Un_Freeze_Report.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Freeze_Un_Freeze_Report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Freeze_Un_Freeze_Report/Freeze_Un_Freeze_Report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:FREEZE_UN_FREEZE:REPORT",
        },
        controller: "FreezeUnfreezeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Freeze_Un_Freeze_Report/Freeze_Un_Freeze_Report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Freeze_Un_Freeze_Report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Freeze_Un_Freeze_Report/Freeze_Un_Freeze_Report_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:FREEZE_UN_FREEZE:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "FreezeUnfreezeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Freeze_Un_Freeze_Report/Freeze_Un_Freeze_Report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      // ends the freeze and un-freeze report

      //Minor Account Details Starts
      .state("minor_account_details", {
        abstract: true,
        url: "/minor_account_details",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:MINOR_ACCOUNT_DETAILS:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Minor_Account_Details/Minor_Account_Details.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("minor_account_details.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Minor_Account_Details/Minor_Account_Details_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:MINOR_ACCOUNT_DETAILS:REPORT",
        },
        controller: "minorAccDetailsController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Minor_Account_Details/Minor_Account_Details.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("minor_account_details.inquiry_detail", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Minor_Account_Details/Minor_Account_Details_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:MINOR_ACCOUNT_DETAILS:REPORT",
        },
        params: { obj: null },
        controller: "minorAccDetailsController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Minor_Account_Details/Minor_Account_Details.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //Minor Account Details ends

      // starts of the BLA/Subscription/Nomination Detailed Report//
      .state("BLA_Subscription_Nomination_Detailed_Report", {
        abstract: true,
        url: "/BLA_Subscription_Nomination_Detailed_Report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:BLA_SUBSCRIPTION_NOMINATION_DETAILED:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/BLA_Subscription_Nomination_Detailed_Report/BLA_Subscription_Nomination.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("BLA_Subscription_Nomination_Detailed_Report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/BLA_Subscription_Nomination_Detailed_Report/BLA_Subscription_Nomination_Input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BLA_SUBSCRIPTION_NOMINATION_DETAILED:REPORT",
        },
        controller: "BLASubsNominationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/BLA_Subscription_Nomination_Detailed_Report/BLA_Subscription_Nomination.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("BLA_Subscription_Nomination_Detailed_Report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/BLA_Subscription_Nomination_Detailed_Report/BLA_Subscription_Nomination_Output.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BLA_SUBSCRIPTION_NOMINATION_DETAILED:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "BLASubsNominationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/BLA_Subscription_Nomination_Detailed_Report/BLA_Subscription_Nomination.controller.js?v=" + versionNum],
            });
          },
        },
      })
      // ends the BLA/Subscription/Nomination Detailed Report

      //Acknowledgement of Appointment of Nomination Start
      .state("acknowledgement_of_appointment_of_nomination", {
        abstract: true,
        url: "/acknowledgement_of_appointment_of_nomination",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:ACKNOWLEDGEMENT_OF_APPOINTMENT_OF_NOMINATION:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Acknowledgement_of_Appointment_of_Nomination/Acknowledgement_of_Appointment_of_Nomination.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("acknowledgement_of_appointment_of_nomination.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Acknowledgement_of_Appointment_of_Nomination/Acknowledgement_of_Appointment_of_Nomination_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:ACKNOWLEDGEMENT_OF_APPOINTMENT_OF_NOMINATION:REPORT",
        },
        controller: "acknowledgementController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Acknowledgement_of_Appointment_of_Nomination/Acknowledgement_of_Appointment_of_Nomination.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //Acknowledgement of Appointment of Nomination End

      //Acknowledgement and confirmation letters to customer upon updation / rejection Starts
      .state("acknowledgement_and_confirmation_letters_to_customer_upon_updation_rejection", {
        abstract: true,
        url: "/acknowledgement_and_confirmation_letters_to_customer_upon_updation_rejection",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:ACKNOWLEDGEMENT_AND_CONFIRMATION_LETTERS_TO_CUSTOMER_UPON_UPDATION_REJECTION:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: [
                "app/rbi_bond_module/gu_functionallity/reports/Acknowledgement_and_confirmation_letters_to_customer_upon_updation_rejection/Acknowledgement_and_confirmation_letters_to_customer_upon_updation_rejection.service.js?v=" + versionNum,
              ],
            });
          },
        },
      })
      .state("acknowledgement_and_confirmation_letters_to_customer_upon_updation_rejection.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Acknowledgement_and_confirmation_letters_to_customer_upon_updation_rejection/Acknowledgement_and_confirmation_letters_to_customer_upon_updation_rejection_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:ACKNOWLEDGEMENT_AND_CONFIRMATION_LETTERS_TO_CUSTOMER_UPON_UPDATION_REJECTION:REPORT",
        },
        controller: "acknowledgementconfirmationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: [
                "app/rbi_bond_module/gu_functionallity/reports/Acknowledgement_and_confirmation_letters_to_customer_upon_updation_rejection/Acknowledgement_and_confirmation_letters_to_customer_upon_updation_rejection.controller.js?v=" + versionNum,
              ],
            });
          },
        },
      })
      .state("acknowledgement_and_confirmation_letters_to_customer_upon_updation_rejection.inquiry_detail", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Acknowledgement_and_confirmation_letters_to_customer_upon_updation_rejection/Acknowledgement_and_confirmation_letters_to_customer_upon_updation_rejection_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:ACKNOWLEDGEMENT_AND_CONFIRMATION_LETTERS_TO_CUSTOMER_UPON_UPDATION_REJECTION:REPORT",
        },
        params: { obj: null },
        controller: "acknowledgementconfirmationController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: [
                "app/rbi_bond_module/gu_functionallity/reports/Acknowledgement_and_confirmation_letters_to_customer_upon_updation_rejection/Acknowledgement_and_confirmation_letters_to_customer_upon_updation_rejection.controller.js?v=" + versionNum,
              ],
            });
          },
        },
      })

      //Acknowledgement and confirmation letters to customer upon updation / rejection ends

      // starts of the Pre Intimation Advice Notice for ON Maturity Cases
      .state("Pre_Intimation_Advice_Notice_Maturity_Cases", {
        abstract: true,
        url: "/Pre_Intimation_Advice_Notice_Maturity_Cases",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:PRE_INTIMATION_ADVICE_NOTICE_MATURITY_CASES:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Pre_Intimation_Advice_Notice_Maturity_Cases/Pre_Intimation_Advice_Notice.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Pre_Intimation_Advice_Notice_Maturity_Cases.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Pre_Intimation_Advice_Notice_Maturity_Cases/Pre_Intimation_Advice_Notice_Input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:PRE_INTIMATION_ADVICE_NOTICE_MATURITY_CASES:REPORT",
        },
        controller: "preIntimationAdviceNoticeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Pre_Intimation_Advice_Notice_Maturity_Cases/Pre_Intimation_Advice_Notice.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Pre_Intimation_Advice_Notice_Maturity_Cases.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Pre_Intimation_Advice_Notice_Maturity_Cases/Pre_Intimation_Advice_Notice_Detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:PRE_INTIMATION_ADVICE_NOTICE_MATURITY_CASES:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "preIntimationAdviceNoticeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Pre_Intimation_Advice_Notice_Maturity_Cases/Pre_Intimation_Advice_Notice.controller.js?v=" + versionNum],
            });
          },
        },
      })

      // ends the Post Intimation Advice Notice for ON Maturity Cases
       // starts of the Post Intimation Advice Notice for ON Maturity Cases

 .state("Post_Intimation_Advice_Notice_Maturity_Cases", {
        abstract: true,
        url: "/Post_Intimation_Advice_Notice_Maturity_Cases",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:POST_INTIMATION_ADVICE_NOTICE_MATURITY_CASES:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Post_Maturity_Intimation_Advice_Notice_Maturity_Cases/Post_Intimation_Advice_Notice.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Post_Intimation_Advice_Notice_Maturity_Cases.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Post_Maturity_Intimation_Advice_Notice_Maturity_Cases/Post_Intimation_Advice_Notice_Input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:POST_INTIMATION_ADVICE_NOTICE_MATURITY_CASES:REPORT",
        },
        controller: "postIntimationAdviceNoticeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Post_Maturity_Intimation_Advice_Notice_Maturity_Cases/Post_Intimation_Advice_Notice.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Post_Intimation_Advice_Notice_Maturity_Cases.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Post_Maturity_Intimation_Advice_Notice_Maturity_Cases/Post_Intimation_Advice_Notice_Detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:POST_INTIMATION_ADVICE_NOTICE_MATURITY_CASES:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "postIntimationAdviceNoticeController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Post_Maturity_Intimation_Advice_Notice_Maturity_Cases/Post_Intimation_Advice_Notice.controller.js?v=" + versionNum],
            });
          },
        },
      })



//Portfolio reports Starts

 .state("Portfolio_Report", {
        abstract: true,
        url: "/Portfolio_Report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:PORTFOLIO_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Portfolio_report/Portfolio.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Portfolio_Report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Portfolio_report/Portfolio_Input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:PORTFOLIO_REPORT:REPORT",
        },
        controller: "PortfolioController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Portfolio_report/Portfolio.controller.js?v=" + versionNum],
            });
          },
        },
      })


//Portfolio reports end












      // ends the Pre Intimation Advice Notice for ON Maturity Cases
      //TDS Deduction at other than normal TDS rate report start
      .state("tds_deduction_at_other_than_normal_tds_rate_report", {
        abstract: true,
        url: "/tds_deduction_at_other_than_normal_tds_rate_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:TDS_DEDUCTION_AT_OTHER_THAN_NORMAL_TDS_RATE_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/TDS_Deduction_at_other_than_normal_TDS_rate_report/TDS_Deduction_at_other_than_normal_TDS_rate_report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("tds_deduction_at_other_than_normal_tds_rate_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/TDS_Deduction_at_other_than_normal_TDS_rate_report/TDS_Deduction_at_other_than_normal_TDS_rate_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:TDS_DEDUCTION_AT_OTHER_THAN_NORMAL_TDS_RATE_REPORT:REPORT",
        },
        controller: "tdsDeductionTdsRateController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/TDS_Deduction_at_other_than_normal_TDS_rate_report/TDS_Deduction_at_other_than_normal_TDS_rate_report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("tds_deduction_at_other_than_normal_tds_rate_report.inquiry_detail", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/TDS_Deduction_at_other_than_normal_TDS_rate_report/TDS_Deduction_at_other_than_normal_TDS_rate_report_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:TDS_DEDUCTION_AT_OTHER_THAN_NORMAL_TDS_RATE_REPORT:REPORT",
        },
        params: { obj: null },
        controller: "tdsDeductionTdsRateController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/TDS_Deduction_at_other_than_normal_TDS_rate_report/TDS_Deduction_at_other_than_normal_TDS_rate_report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //TDS Deduction at other than normal TDS rate report end

      //TDS eligibility report Start
      .state("tds_eligibility_report", {
        abstract: true,
        url: "/tds_eligibility_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:TDS_ELIGIBILITY_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/TDS_eligibility_report/TDS_eligibility_report.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("tds_eligibility_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/TDS_eligibility_report/TDS_eligibility_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:TDS_ELIGIBILITY_REPORT:REPORT",
        },
        controller: "tdsEligibilityController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/TDS_eligibility_report/TDS_eligibility_report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("tds_eligibility_report.inquiry_detail", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/TDS_eligibility_report/TDS_eligibility_report_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:TDS_ELIGIBILITY_REPORT:REPORT",
        },
        params: { obj: null },
        controller: "tdsEligibilityController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/TDS_eligibility_report/TDS_eligibility_report.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //TDS eligibility report end
      //Half- Yearly interest advice- Post payment of interest- report and adviceStart
      .state("Half_Yearly_Interest_Advice", {
        abstract: true,
        url: "/Half_Yearly_Interest_Advice",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:HALF_YEARLY_INTEREST_ADVICE:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Half_Yearly_Interest_Advice/Half_Yearly_Interest_Advice.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Half_Yearly_Interest_Advice.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Half_Yearly_Interest_Advice/Half_Yearly_Interest_Advice_Input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:HALF_YEARLY_INTEREST_ADVICE:REPORT",
        },
        controller: "HalfYearlyInterestAdviceController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Half_Yearly_Interest_Advice/Half_Yearly_Interest_Advice.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //Half- Yearly interest advice- Post payment of interest- report and advice end
      // Interest Scrollreport 5106_Mar_24_Appendix_XI
      .state("Interest_Scroll_report_5106", {
        abstract: true,
        url: "/Interest_Scroll_report_5106",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_SCROLL_RBIBOND:INTEREST_SCROLL_REPORT_5106:SCROLL"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Interest_Scrollreport_5106_Mar_24_Appendix_XI/Interest_Scrollreport_5106.Service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Interest_Scroll_report_5106.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Interest_Scrollreport_5106_Mar_24_Appendix_XI/Interest_Scrollreport_5106_Input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:INTEREST_SCROLL_REPORT_5106:SCROLL",
        },
        controller: "InterestScrollReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Interest_Scrollreport_5106_Mar_24_Appendix_XI/Interest_Scrollreport_5106.Controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Interest_Scroll_report_5106.inquiry_details", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Interest_Scrollreport_5106_Mar_24_Appendix_XI/Interest_Scrollreport_5106_Details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:INTEREST_SCROLL_REPORT_5106:SCROLL",
        },
        params: { obj: null },
        controller: "InterestScrollReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Interest_Scrollreport_5106_Mar_24_Appendix_XI/Interest_Scrollreport_5106.Controller.js?v=" + versionNum],
            });
          },
        },
      })

      // Interest Scrollreport 5106_Mar_24_Appendix_XI

      //Bond Transfer-In from Same Bank Start
      .state("bond_transfer-in_from_same_bank", {
        abstract: true,
        url: "/bond_transfer-in_from_same_bank",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:BOND_TRANSFER_IN_FROM_SAME_BANK:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Transfer_In_From_Same_Bank/Bond_Transfer_In_From_Same_Bank.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_transfer-in_from_same_bank.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Transfer_In_From_Same_Bank/Bond_Transfer_In_From_Same_Bank_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BOND_TRANSFER_IN_FROM_SAME_BANK:REPORT",
        },
        controller: "bondTransferInController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Transfer_In_From_Same_Bank/Bond_Transfer_In_From_Same_Bank.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("bond_transfer-in_from_same_bank.inquiry_detail", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Bond_Transfer_In_From_Same_Bank/Bond_Transfer_In_From_Same_Bank.detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:BOND_TRANSFER_IN_FROM_SAME_BANK:REPORT",
        },
        params: { obj: null },
        controller: "bondTransferInController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Bond_Transfer_In_From_Same_Bank/Bond_Transfer_In_From_Same_Bank.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //Bond Transfer-In from Same Bank end

      //Disabling /Deactivating of brokers after 2 years of non-sourcing Start
      .state("disabling_deactivating_of_brokers", {
        abstract: true,
        url: "/disabling_deactivating_of_brokers",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:DISABLING_DEACTIVATING_OF_BROKERS:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Disabling_Deactivating_of_brokers_after_2years_of_non-sourcing/Disabling_Deactivating_of_brokers_after_2years_of_non-sourcing.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("disabling_deactivating_of_brokers.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Disabling_Deactivating_of_brokers_after_2years_of_non-sourcing/Disabling_Deactivating_of_brokers_after_2years_of_non-sourcing_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:DISABLING_DEACTIVATING_OF_BROKERS:REPORT",
        },
        controller: "disablingDeactivatingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Disabling_Deactivating_of_brokers_after_2years_of_non-sourcing/Disabling_Deactivating_of_brokers_after_2years_of_non-sourcing.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("disabling_deactivating_of_brokers.inquiry_detail", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Disabling_Deactivating_of_brokers_after_2years_of_non-sourcing/Disabling_Deactivating_of_brokers_after_2years_of_non-sourcing_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:DISABLING_DEACTIVATING_OF_BROKERS:REPORT",
        },
        params: { obj: null },
        controller: "disablingDeactivatingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Disabling_Deactivating_of_brokers_after_2years_of_non-sourcing/Disabling_Deactivating_of_brokers_after_2years_of_non-sourcing.controller.js?v=" + versionNum],
            });
          },
        },
      })
      //Disabling /Deactivating of brokers after 2 years of non-sourcing end


                                 // SFT SCROLL Start
                                        .state("sft_scroll", {
                                          abstract: true,
                                          url: "/sft_scroll",
                                          params: {
                                            views: ["inquiry"],
                                            permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:SFT_SCROLL:SCROLL"],
                                          },
                                          resolve: {
                                            load: function ($ocLazyLoad) {
                                              return $ocLazyLoad.load({
                                                files: ["app/rbi_bond_module/gu_functionallity/scrolls/sft_scroll/sft_scroll.service.js?v=" + versionNum],
                                              });
                                            },
                                          },
                                        })
                                        .state("sft_scroll.inquiry", {
                                          url: "/action/inquiry",
                                          templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/sft_scroll/sft_scroll_input.html",
                                          data: {
                                            authRequired: true,
                                            requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:SFT_SCROLL:SCROLL",
                                          },
                                          controller: "sftController",
                                          controllerAs: "vm",
                                          resolve: {
                                            load: function ($ocLazyLoad) {
                                              return $ocLazyLoad.load({
                                                files: ["app/rbi_bond_module/gu_functionallity/scrolls/sft_scroll/sft_scroll.controller.js?v=" + versionNum],
                                              });
                                            },
                                          },
                                        })
                                        .state("sft_scroll.inquiry_detail", {
                                          url: "/action/inquiry/details",
                                          templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/sft_scroll/sft_scroll_detail.html",
                                          data: {
                                            authRequired: true,
                                            requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:SFT_SCROLL:SCROLL",
                                          },
                                          params: { obj: null },
                                          controller: "sftController",
                                          controllerAs: "vm",
                                          resolve: {
                                            load: function ($ocLazyLoad) {
                                              return $ocLazyLoad.load({
                                                files: ["app/rbi_bond_module/gu_functionallity/scrolls/sft_scroll/sft_scroll.controller.js?v=" + versionNum],
                                              });
                                            },
                                          },
                                        })
                                      //SFT SCROLL end

                                      // Estimation of Expenses SCROLL Start
                                        .state("estimation_of_expenses_scroll", {
                                          abstract: true,
                                          url: "/estimation_of_expensesll_scroll",
                                          params: {
                                            views: ["inquiry"],
                                            permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:ESTIMATION_OF_EXPENSES_SCROLL:SCROLL"],
                                          },
                                          resolve: {
                                            load: function ($ocLazyLoad) {
                                              return $ocLazyLoad.load({
                                                files: ["app/rbi_bond_module/gu_functionallity/scrolls/estimation_of_expenses/estimation_of_expenses.service.js?v=" + versionNum],
                                              });
                                            },
                                          },
                                        })
                                        .state("estimation_of_expenses_scroll.inquiry", {
                                          url: "/action/inquiry",
                                          templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/estimation_of_expenses/estimation_of_expenses_input.html",
                                          data: {
                                            authRequired: true,
                                            requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:ESTIMATION_OF_EXPENSES_SCROLL:SCROLL",
                                          },
                                          controller: "estimationController",
                                          controllerAs: "vm",
                                          resolve: {
                                            load: function ($ocLazyLoad) {
                                              return $ocLazyLoad.load({
                                                files: ["app/rbi_bond_module/gu_functionallity/scrolls/estimation_of_expenses/estimation_of_expenses.controller.js?v=" + versionNum],
                                              });
                                            },
                                          },
                                        })
                                        .state("estimation_of_expenses_scroll.inquiry_detail", {
                                          url: "/action/inquiry/details",
                                          templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/estimation_of_expenses/estimation_of_expenses_detail.html",
                                          data: {
                                            authRequired: true,
                                            requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:ESTIMATION_OF_EXPENSES_SCROLL:SCROLL",
                                          },
                                          params: { obj: null },
                                          controller: "estimationController",
                                          controllerAs: "vm",
                                          resolve: {
                                            load: function ($ocLazyLoad) {
                                              return $ocLazyLoad.load({
                                                files: ["app/rbi_bond_module/gu_functionallity/scrolls/estimation_of_expenses/estimation_of_expenses.controller.js?v=" + versionNum],
                                              });
                                            },
                                          },
                                        })
                                      //Estimation of Expenses SCROLL end

      // AIR
      .state("AIR", {
        abstract: true,
        url: "/AIR",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_SCROLL_RBIBOND:AIR:SCROLL"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/AIR/AIR.Service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("AIR.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/AIR/AIR_Input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:AIR:SCROLL",
        },
        controller: "AIRController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/AIR/AIR.Controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("AIR.inquiry_details", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/AIR/AIR_Details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:AIR:SCROLL",
        },
        params: { obj: null },
        controller: "AIRController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/AIR/AIR.Controller.js?v=" + versionNum],
            });
          },
        },
      })
    // AIR
 // APPENDIX_IV_7.5%_MAR_2024 START
       .state("appendix_iv_75_mar_2024", {
         abstract: true,
         url: "/appendix_iv_75_mar_2024",
         params: {
           views: ["inquiry"],
           permissions: ["GBM_SCROLL_RBIBOND:APPENDIX_IV_75_MAR_2024:SCROLL"],
         },
         resolve: {
           load: function ($ocLazyLoad) {
             return $ocLazyLoad.load({
               files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_appendix_IV_75_Mar_2024_scroll/rbibond_appendix_IV_75_Mar_2024_scroll.service.js?v=" + versionNum],
             });
           },
         },
       })
       .state("appendix_iv_75_mar_2024.inquiry", {
         url: "/action/inquiry",
         templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/rbibond_appendix_IV_75_Mar_2024_scroll/rbibond_appendix_IV_75_Mar_2024_scroll_input.html",
         data: {
           authRequired: true,
           requiredPermission: "GBM_SCROLL_RBIBOND:APPENDIX_IV_75_MAR_2024:SCROLL",
         },
         controller: "AppendixIV75March2024Controller",
         controllerAs: "vm",
         resolve: {
           load: function ($ocLazyLoad) {
             return $ocLazyLoad.load({
               files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_appendix_IV_75_Mar_2024_scroll/rbibond_appendix_IV_75_Mar_2024_scroll.controller.js?v=" + versionNum],
             });
           },
         },
       })
       .state("appendix_iv_75_mar_2024.inquiry_detail", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/rbibond_appendix_IV_75_Mar_2024_scroll/rbibond_appendix_IV_75_Mar_2024_scroll_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:APPENDIX_IV_75_MAR_2024:SCROLL",
        },
        params: { obj: null },
        controller: "AppendixIV75March2024Controller",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_appendix_IV_75_Mar_2024_scroll/rbibond_appendix_IV_75_Mar_2024_scroll.controller.js?v=" + versionNum],
            });
          },
        },
        })
// APPENDIX_IV_7.5%_MAR_2024 END

      // Appendix_IV_8.05_Mar_2024 start
      .state("Appendix_IV_805_Mar_2024", {
        abstract: true,
        url: "/Appendix_IV_805_Mar_2024",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_SCROLL_RBIBOND:APPENDIX_IV_805_MAR_2024:SCROLL"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Appendix_IV_805_Mar_2024/Appendix_IV_805_Mar_2024.Service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Appendix_IV_805_Mar_2024.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Appendix_IV_805_Mar_2024/Appendix_IV_805_Mar_2024_Input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:APPENDIX_IV_805_MAR_2024:SCROLL",
        },
        controller: "AppendixIV805Controller",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Appendix_IV_805_Mar_2024/Appendix_IV_805_Mar_2024.Controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Appendix_IV_805_Mar_2024.inquiry_details", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Appendix_IV_805_Mar_2024/Appendix_IV_805_Mar_2024_Details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:APPENDIX_IV_805_MAR_2024:SCROLL",
        },
        params: { obj: null },
        controller: "AppendixIV805Controller",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Appendix_IV_805_Mar_2024/Appendix_IV_805_Mar_2024.Controller.js?v=" + versionNum],
            });
          },
        },
      })
    // Appendix_IV_8.05_Mar_2024 end

//E-filing Scroll - Starts
       .state("e_filling", {
         abstract: true,
         url: "/e_filling",
         params: {
           permissions: ["GBM_SCROLL_RBIBOND:E_FILLING:SCROLL"],
         },
         resolve: {
           load: function ($ocLazyLoad) {
             return $ocLazyLoad.load({
               files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_e_filling/e_filling_service.js?v=" + versionNum],
             });
           },
         },
       })
       .state("e_filling.inquiry", {
         url: "/action/inquiry",
         templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/rbibond_e_filling/e_filling_input.html",
         data: {
           authRequired: true,
           requiredPermission: "GBM_SCROLL_RBIBOND:E_FILLING:SCROLL",
         },
         controller: "EfilingController",
         controllerAs: "vm",
         resolve: {
           load: function ($ocLazyLoad) {
             return $ocLazyLoad.load({
               files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_e_filling/e_filling_controller.js?v=" + versionNum],
             });
           },
         },
       })
       .state("e_filling.inquiry_detail", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/rbibond_e_filling/e_filling_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:E_FILLING:SCROLL",
        },
        params: { obj: null },
        controller: "EfilingController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/rbibond_e_filling/e_filling_controller.js?v=" + versionNum],
            });
          },
        },
        })
////E-filing Scroll - Ends

      // Appendix_IV_6.5_Mar_2024 start
      .state("Appendix_IV_65_Mar_2024", {
        abstract: true,
        url: "/Appendix_IV_65_Mar_2024",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_SCROLL_RBIBOND:APPENDIX_IV_65_MAR_2024:SCROLL"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Appendix_IV_65_Mar_2024/Appendix_IV_65_Mar_2024.Service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Appendix_IV_65_Mar_2024.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Appendix_IV_65_Mar_2024/Appendix_IV_65_Mar_2024_Input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:APPENDIX_IV_65_MAR_2024:SCROLL",
        },
        controller: "AppendixIV65Controller",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Appendix_IV_65_Mar_2024/Appendix_IV_65_Mar_2024.Controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("Appendix_IV_65_Mar_2024.inquiry_details", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Appendix_IV_65_Mar_2024/Appendix_IV_65_Mar_2024_Details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:APPENDIX_IV_65_MAR_2024:SCROLL",
        },
        params: { obj: null },
        controller: "AppendixIV65Controller",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/scrolls/Appendix_IV_65_Mar_2024/Appendix_IV_65_Mar_2024.Controller.js?v=" + versionNum],
            });
          },
        },
      })
    // Appendix_IV_6.5_Mar_2024 end

    // Appendix_IV_8_Mar_2024 start
          .state("Appendix_IV_8_Mar_2024", {
            abstract: true,
            url: "/Appendix_IV_8_Mar_2024",
            params: {
              views: ["inquiry"],
              permissions: ["GBM_SCROLL_RBIBOND:APPENDIX_IV_8_MAR_2024:SCROLL"],
            },
            resolve: {
              load: function ($ocLazyLoad) {
                return $ocLazyLoad.load({
                  files: ["app/rbi_bond_module/gu_functionallity/scrolls/Appendix_IV_8_Mar_2024/Appendix_IV_8_Mar_2024.Service.js?v=" + versionNum],
                });
              },
            },
          })
          .state("Appendix_IV_8_Mar_2024.inquiry", {
            url: "/action/inquiry",
            templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Appendix_IV_8_Mar_2024/Appendix_IV_8_Mar_2024_Input.html",
            data: {
              authRequired: true,
              requiredPermission: "GBM_SCROLL_RBIBOND:APPENDIX_IV_8_MAR_2024:SCROLL",
            },
            controller: "AppendixIV8Controller",
            controllerAs: "vm",
            resolve: {
              load: function ($ocLazyLoad) {
                return $ocLazyLoad.load({
                  files: ["app/rbi_bond_module/gu_functionallity/scrolls/Appendix_IV_8_Mar_2024/Appendix_IV_8_Mar_2024.Controller.js?v=" + versionNum],
                });
              },
            },
          })
          .state("Appendix_IV_8_Mar_2024.inquiry_details", {
            url: "/action/inquiry/details",
            templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/Appendix_IV_8_Mar_2024/Appendix_IV_8_Mar_2024_Details.html",
            data: {
              authRequired: true,
              requiredPermission: "GBM_SCROLL_RBIBOND:APPENDIX_IV_8_MAR_2024:SCROLL",
            },
            params: { obj: null },
            controller: "AppendixIV8Controller",
            controllerAs: "vm",
            resolve: {
              load: function ($ocLazyLoad) {
                return $ocLazyLoad.load({
                  files: ["app/rbi_bond_module/gu_functionallity/scrolls/Appendix_IV_8_Mar_2024/Appendix_IV_8_Mar_2024.Controller.js?v=" + versionNum],
                });
              },
            },
          })
        // Appendix_IV_8_Mar_2024 end

//DashBoard Report for Inward and Outward
                      .state("DashBoard_Report_for_Inward_and_Outward", {
                        abstract: true,
                        url: "/DashBoard_Report_for_Inward_and_Outward",
                        params: {
                          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:DASHBOARD_INWARD_OUTWARD_REPORT:REPORT"],
                        },
                        resolve: {
                          load: function ($ocLazyLoad) {
                            return $ocLazyLoad.load({
                              files: [
                                "app/rbi_bond_module/gu_functionallity/reports/DashBoard_Report_for_Inward_and_Outward/DashBoard_Report_for_Inward_and_Outward.service.js?v=" + versionNum,
                              ],
                            });
                          },
                        },
                      })
                      .state("DashBoard_Report_for_Inward_and_Outward.inquiry", {
                        url: "/action/inquiry",
                        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/DashBoard_Report_for_Inward_and_Outward/DashBoard_Report_for_Inward_and_Outward_input.html",
                        data: {
                          authRequired: true,
                          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:DASHBOARD_INWARD_OUTWARD_REPORT:REPORT",
                        },
                        controller: "DashboardInwardOutwardController",
                        controllerAs: "vm",
                        resolve: {
                          load: function ($ocLazyLoad) {
                            return $ocLazyLoad.load({
                              files: [
                                "app/rbi_bond_module/gu_functionallity/reports/DashBoard_Report_for_Inward_and_Outward/DashBoard_Report_for_Inward_and_Outward.controller.js?v=" + versionNum,
                              ],
                            });
                          },
                        },
                      })
                      .state("DashBoard_Report_for_Inward_and_Outward.inquiry_detail", {
                        url: "/action/inquiry/detail",
                        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/DashBoard_Report_for_Inward_and_Outward/DashBoard_Report_for_Inward_and_Outward_detail.html",
                        data: {
                          authRequired: true,
                          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:DASHBOARD_INWARD_OUTWARD_REPORT:REPORT",
                        },
                        params: { obj: null },
                        controller: "DashboardInwardOutwardController",
                        controllerAs: "vm",
                        resolve: {
                          load: function ($ocLazyLoad) {
                            return $ocLazyLoad.load({
                              files: [
                                "app/rbi_bond_module/gu_functionallity/reports/DashBoard_Report_for_Inward_and_Outward/DashBoard_Report_for_Inward_and_Outward.controller.js?v=" + versionNum,
                              ],
                            });
                          },
                        },
                      })






         // joint_type_change_three_to_two_report start
                  .state("joint_type_change_three_to_two_report", {
                    abstract: true,
                    url: "/joint_type_change_three_to_two_report",
                    params: {
                      views: ["inquiry"],
                      permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_JOINT_TYPE_CHANGE:REPORT"],
                    },
                    resolve: {
                      load: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                          files: ["app/rbi_bond_module/gu_functionallity/reports/joint_type_change_three_to_two_report/joint_type_change_three_to_two_report.service.js?v=" + versionNum],
                        });
                      },
                    },
                  })
                  .state("joint_type_change_three_to_two_report.inquiry", {
                    url: "/action/inquiry",
                    templateUrl: "app/rbi_bond_module/gu_functionallity/reports/joint_type_change_three_to_two_report/joint_type_change_three_to_two_input_report.html",
                    data: {
                      authRequired: true,
                      requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_JOINT_TYPE_CHANGE:REPORT",
                    },
                    controller: "jointTypeChangeThreeToTwoReportController",
                    controllerAs: "vm",
                    resolve: {
                      load: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                          files: ["app/rbi_bond_module/gu_functionallity/reports/joint_type_change_three_to_two_report/joint_type_change_three_to_two_report.controller.js?v=" + versionNum],
                        });
                      },
                    },
                  })
                  .state("joint_type_change_three_to_two_report.inquiry_details", {
                    url: "/action/inquiry/details",
                    templateUrl: "app/rbi_bond_module/gu_functionallity/reports/joint_type_change_three_to_two_report/joint_type_change_three_to_two_report_details.html",
                    data: {
                      authRequired: true,
                      requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_JOINT_TYPE_CHANGE:REPORT",
                    },
                    params: { obj: null },
                    controller: "jointTypeChangeThreeToTwoReportController",
                    controllerAs: "vm",
                    resolve: {
                      load: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                          files: ["app/rbi_bond_module/gu_functionallity/reports/joint_type_change_three_to_two_report/joint_type_change_three_to_two_report.controller.js?v=" + versionNum],

                        });
                      },
                    },
                  })

                  // pan validation
                  .state("pan_validation_report", {
                          abstract: true,
                          url: "/pan_validation_report",
                          params: {
                            views: ["inquiry"],
                            permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:PAN_VALIDATION_REPORT:REPORT"],
                          },
                          resolve: {
                            load: function ($ocLazyLoad) {
                              return $ocLazyLoad.load({
                                files: ["app/rbi_bond_module/gu_functionallity/reports/PAN_validation_report/PAN_validation_report.service.js?v=" + versionNum],
                              });
                            },
                          },
                        })

                        .state("pan_validation_report.inquiry", {
                          url: "/action/inquiry",
                          templateUrl: "app/rbi_bond_module/gu_functionallity/reports/PAN_validation_report/PAN_validation_report_input.html",
                          data: {
                            authRequired: true,
                            requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:PAN_VALIDATION_REPORT:REPORT",
                          },
                          controller: "PANValidationReportController",
                          controllerAs: "vm",
                          resolve: {
                            load: function ($ocLazyLoad) {
                              return $ocLazyLoad.load({
                                files: ["app/rbi_bond_module/gu_functionallity/reports/PAN_validation_report/PAN_validation_report.controller.js?v=" + versionNum],
                              });
                            },
                          },
                        })

                        .state("pan_validation_report.inquiry_details", {
                          url: "/action/inquiry_details",
                          templateUrl: "app/rbi_bond_module/gu_functionallity/reports/PAN_validation_report/PAN_validation_report_details.html",
                          data: {
                            authRequired: true,
                            requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:PAN_VALIDATION_REPORT:REPORT",
                          },
                          params: {
                            obj: null,
                          },
                          controller: "PANValidationReportController",
                          controllerAs: "vm",
                          resolve: {
                            load: function ($ocLazyLoad) {
                              return $ocLazyLoad.load({
                                files: ["app/rbi_bond_module/gu_functionallity/reports/PAN_validation_report/PAN_validation_report.controller.js?v=" + versionNum],
                              });
                            },
                          },
                        })


// Tax Waiver Documents Report
                .state("tax_waiver_documents_report", {
                          abstract: true,
                          url: "/tax_waiver_documents_report",
                          params: {
                            views: ["inquiry"],
                            permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:Tax_Waiver_Documents_Report:REPORT"],
                          },
                          resolve: {
                            load: function ($ocLazyLoad) {
                              return $ocLazyLoad.load({
                                files: ["app/rbi_bond_module/gu_functionallity/reports/Tax_Waiver_Documents_Report/Tax_Waiver_Documents_Report.service.js?v=" + versionNum],
                              });
                            },
                          },
                        })

                        .state("tax_waiver_documents_report.inquiry", {
                          url: "/action/inquiry",
                          templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Tax_Waiver_Documents_Report/Tax_Waiver_Documents_Report_input.html",
                          data: {
                            authRequired: true,
                            requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:Tax_Waiver_Documents_Report:REPORT",
                          },
                          controller: "TaxWaiverDocumentsReportController",
                          controllerAs: "vm",
                          resolve: {
                            load: function ($ocLazyLoad) {
                              return $ocLazyLoad.load({
                                files: ["app/rbi_bond_module/gu_functionallity/reports/Tax_Waiver_Documents_Report/Tax_Waiver_Documents_Report.controller.js?v=" + versionNum],
                              });
                            },
                          },
                        })

                        .state("tax_waiver_documents_report.inquiry_details", {
                          url: "/action/inquiry_details",
                          templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Tax_Waiver_Documents_Report/Tax_Waiver_Documents_Report_details.html",
                          data: {
                            authRequired: true,
                            requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:Tax_Waiver_Documents_Report:REPORT",
                          },
                          params: {
                            obj: null,
                          },
                          controller: "TaxWaiverDocumentsReportController",
                          controllerAs: "vm",
                          resolve: {
                            load: function ($ocLazyLoad) {
                              return $ocLazyLoad.load({
                                files: ["app/rbi_bond_module/gu_functionallity/reports/Tax_Waiver_Documents_Report/Tax_Waiver_Documents_Report.controller.js?v=" + versionNum],
                              });
                            },
                          },
                        })




                // joint_type_change_three_to_two_report end
.state("pre_maturity_marking_report", {
        abstract: true,
        url: "/pre_maturity_marking_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:PRE_MATURITY_MARKING_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Pre_Maturity_Marking_Report/pre_maturity_marking_report.service.js?v=" + versionNum],
            });
          },
        },
      })

      .state("pre_maturity_marking_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Pre_Maturity_Marking_Report/pre_maturity_marking_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:PRE_MATURITY_MARKING_REPORT:REPORT",
        },
        controller: "PreMaturityMarkingReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Pre_Maturity_Marking_Report/pre_maturity_marking_report.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("pre_maturity_marking_report.inquiry_details", {
        url: "/action/inquiry_details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Pre_Maturity_Marking_Report/pre_maturity_marking_report_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:PRE_MATURITY_MARKING_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "PreMaturityMarkingReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/Pre_Maturity_Marking_Report/pre_maturity_marking_report_detail.html?v=" + versionNum],
            });
          },
        },
      })



                 //  Joint_type_change_Three_to_two  Start

                                   .state("Joint_type_change_Three_to_two", {
                                          abstract: true,
                                          url: "/Joint_type_change_Three_to_two",
                                          params: {
                                            views: ["add", "verify", "inquiry"],
                                            permissions: ["GBM_GU_RBIBOND:RBIBOND_JOINT_TYPE_CHANGE:ADD", "GBM_GU_RBIBOND:RBIBOND_JOINT_TYPE_CHANGE:VERIFY", "GBM_GU_RBIBOND:RBIBOND_JOINT_TYPE_CHANGE:ENQUIRY"],
                                          },
                                          resolve: {
                                            load: function ($ocLazyLoad) {
                                              return $ocLazyLoad.load({
                                                files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/Joint_type_change_Three_to_two/Joint_type_change_Three_to_two.service.js?v=" + versionNum],

                                              });
                                            },
                                          },
                                        })
                                        .state("Joint_type_change_Three_to_two.add_input", {
                                          url: "/add",
                                          templateUrl: "app/rbi_bond_module/gu_functionallity/masters/Joint_type_change_Three_to_two/add_Joint_type_change_Three_to_two.html",
                                          data: {
                                            authRequired: true,
                                            requiredPermission: "GBM_GU_RBIBOND:RBIBOND_JOINT_TYPE_CHANGE:ADD",
                                          },
                                          params: { obj: null },
                                          controller: "jointTypeThreeToTwoController",
                                          controllerAs: "vm",
                                          resolve: {
                                            load: function ($ocLazyLoad) {
                                              return $ocLazyLoad.load({
                                                files: ["app/rbi_bond_module/gu_functionallity/masters/Joint_type_change_Three_to_two/Joint_type_change_Three_to_two.controller.js?v=" + versionNum],
                                              });
                                            },
                                          },
                                        })

                                        .state("Joint_type_change_Three_to_two.add_detail", {
                                          url: "/add/detail",
                                          templateUrl: "app/rbi_bond_module/gu_functionallity/masters/Joint_type_change_Three_to_two/add_Joint_type_change_Three_to_two_detail.html",
                                          data: {
                                            authRequired: true,
                                            requiredPermission: "GBM_GU_RBIBOND:RBIBOND_JOINT_TYPE_CHANGE:ADD",
                                          },
                                          params: { obj: null },
                                          controller: "jointTypeThreeToTwoController",
                                          controllerAs: "vm",
                                          resolve: {
                                            load: function ($ocLazyLoad) {
                                              return $ocLazyLoad.load({
                                               files: ["app/rbi_bond_module/gu_functionallity/masters/Joint_type_change_Three_to_two/Joint_type_change_Three_to_two.controller.js?v=" + versionNum],
                                              });
                                            },
                                          },
                                        })
                                        .state("Joint_type_change_Three_to_two.verify", {
                                          url: "/action/verify",
                                          templateUrl: "app/rbi_bond_module/gu_functionallity/masters/Joint_type_change_Three_to_two/verify_Joint_type_change_Three_to_two.html",
                                          data: {
                                            authRequired: true,
                                            requiredPermission: "GBM_GU_RBIBOND:RBIBOND_JOINT_TYPE_CHANGE:VERIFY",
                                          },
                                          controller: "jointTypeThreeToTwoController",
                                          controllerAs: "vm",
                                          resolve: {
                                            load: function ($ocLazyLoad) {
                                              return $ocLazyLoad.load({
                                                files: ["app/rbi_bond_module/gu_functionallity/masters/Joint_type_change_Three_to_two/Joint_type_change_Three_to_two.controller.js?v=" + versionNum],
                                              });
                                            },
                                          },
                                        })
                                        .state("Joint_type_change_Three_to_two.verify_detail", {
                                          url: "/action/verify/detail",
                                          templateUrl: "app/rbi_bond_module/gu_functionallity/masters/Joint_type_change_Three_to_two/verify_Joint_type_change_Three_to_two_detail.html",
                                          data: {
                                            authRequired: true,
                                            requiredPermission: "GBM_GU_RBIBOND:RBIBOND_JOINT_TYPE_CHANGE:VERIFY",
                                          },
                                          params: { obj: null },
                                          controller: "jointTypeThreeToTwoController",
                                          controllerAs: "vm",
                                          resolve: {
                                            load: function ($ocLazyLoad) {
                                              return $ocLazyLoad.load({
                                                 files: ["app/rbi_bond_module/gu_functionallity/masters/Joint_type_change_Three_to_two/Joint_type_change_Three_to_two.controller.js?v=" + versionNum],
                                              });
                                            },
                                          },
                                        })

                                        .state("Joint_type_change_Three_to_two.list", {
                                          url: "/action/inquiry",
                                          templateUrl: "app/rbi_bond_module/gu_functionallity/masters/Joint_type_change_Three_to_two/inquiry_Joint_type_change_Three_to_two.html",
                                          data: {
                                            authRequired: true,
                                            requiredPermission: "GBM_GU_RBIBOND:RBIBOND_JOINT_TYPE_CHANGE:ENQUIRY",
                                          },
                                          controller: "jointTypeThreeToTwoController",
                                          controllerAs: "vm",
                                          resolve: {
                                            load: function ($ocLazyLoad) {
                                              return $ocLazyLoad.load({
                                              files: ["app/rbi_bond_module/gu_functionallity/masters/Joint_type_change_Three_to_two/Joint_type_change_Three_to_two.controller.js?v=" + versionNum],
                                              });
                                            },
                                          },
                                        })
                                        .state("Joint_type_change_Three_to_two.list_detail", {
                                          url: "/action/inquiry/detail",
                                          templateUrl: "app/rbi_bond_module/gu_functionallity/masters/Joint_type_change_Three_to_two/inquiry_Joint_type_change_Three_to_two_detail.html",
                                          data: {
                                            authRequired: true,
                                            requiredPermission: "GBM_GU_RBIBOND:RBIBOND_JOINT_TYPE_CHANGE:ENQUIRY",
                                          },
                                          params: { obj: null },
                                          controller: "jointTypeThreeToTwoController",
                                          controllerAs: "vm",
                                          resolve: {
                                            load: function ($ocLazyLoad) {
                                              return $ocLazyLoad.load({
                                                files: ["app/rbi_bond_module/gu_functionallity/masters/Joint_type_change_Three_to_two/Joint_type_change_Three_to_two.controller.js?v=" + versionNum],
                                              });
                                            },
                                          },
                                        })

                                          //  Joint_type_change_Three_to_two  End

                                         // Certificate_Printing start
                                                           .state("Certificate_Printing", {
                                                             abstract: true,
                                                             url: "/Certificate_Printing",
                                                             params: {
                                                               views: ["inquiry"],
                                                               permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_JOINT_TYPE_CHANGE:REPORT"],
                                                             },
                                                             resolve: {
                                                               load: function ($ocLazyLoad) {
                                                                 return $ocLazyLoad.load({
                                                                   files: ["app/rbi_bond_module/gu_functionallity/reports/Certificate_Printing/Certificate_Printing_service.js?v=" + versionNum],
                                                                 });
                                                               },
                                                             },
                                                           })
                                                           .state("Certificate_Printing.inquiry", {
                                                             url: "/action/inquiry",
                                                             templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Certificate_Printing/Certificate_Printing_input.html",
                                                             data: {
                                                               authRequired: true,
                                                               requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CERTFICATE_PRINTING:REPORT",
                                                             },
                                                             controller: "certificateOfPrintingController",
                                                             controllerAs: "vm",
                                                             resolve: {
                                                               load: function ($ocLazyLoad) {
                                                                 return $ocLazyLoad.load({
                                                                   files: ["app/rbi_bond_module/gu_functionallity/reports/Certificate_Printing/Certificate_Printing_controller.js?v=" + versionNum],
                                                                 });
                                                               },
                                                             },
                                                           })
                                                           .state("Certificate_Printing.inquiry_details", {
                                                             url: "/action/inquiry/details",
                                                             templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Certificate_Printing/Certificate_Printing_details.html",
                                                             data: {
                                                               authRequired: true,
                                                               requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CERTFICATE_PRINTING:REPORT",
                                                             },
                                                             params: { obj: null },
                                                             controller: "certificateOfPrintingController",
                                                             controllerAs: "vm",
                                                             resolve: {
                                                               load: function ($ocLazyLoad) {
                                                                 return $ocLazyLoad.load({
                                                                   files: ["app/rbi_bond_module/gu_functionallity/reports/Certificate_Printing/Certificate_Printing_controller.js?v=" + versionNum],


                                                                 });
                                                               },
                                                             },
                                                           })
                                                         // Certificate_Printing end

                                                                                     // Subscription_Mandate

                                                                                                                    .state("subscription_mandate", {
                                                                                                                      abstract: true,
                                                                                                                      url: "/subscription_mandate",
                                                                                                                      params: {
                                                                                                                        views: ["inquiry"],
                                                                                                                        permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_JOINT_TYPE_CHANGE:REPORT"],
                                                                                                                      },
                                                                                                                      resolve: {
                                                                                                                        load: function ($ocLazyLoad) {
                                                                                                                          return $ocLazyLoad.load({
                                                                                                                            files: ["app/rbi_bond_module/gu_functionallity/scrolls/subscription_mandate/subscription_mandate.service.js?v=" + versionNum],
                                                                                                                          });
                                                                                                                        },
                                                                                                                      },
                                                                                                                    })
                                                                                                                    .state("subscription_mandate.inquiry", {
                                                                                                                      url: "/action/inquiry",
                                                                                                                      templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/subscription_mandate/subscription_mandate_input.html",
                                                                                                                      data: {
                                                                                                                        authRequired: true,
                                                                                                                        requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_SUBSCRIPTION_MANDATE:SCROLL",
                                                                                                                      },
                                                                                                                      controller: "SubscriptionMandateController",
                                                                                                                      controllerAs: "vm",
                                                                                                                      resolve: {
                                                                                                                        load: function ($ocLazyLoad) {
                                                                                                                          return $ocLazyLoad.load({
                                                                                                                            files: ["app/rbi_bond_module/gu_functionallity/scrolls/subscription_mandate/subscription_mandate.controller.js?v=" + versionNum],
                                                                                                                          });
                                                                                                                        },
                                                                                                                      },
                                                                                                                    })
                                                                                                                    .state("subscription_mandate.inquiry_details", {
                                                                                                                      url: "/action/inquiry/details",
                                                                                                                      templateUrl: "app/rbi_bond_module/gu_functionallity/scrolls/subscription_mandate/subscription_mandate_details.html",
                                                                                                                      data: {
                                                                                                                        authRequired: true,
                                                                                                                        requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_SUBSCRIPTION_MANDATE:SCROLL",
                                                                                                                      },
                                                                                                                      params: { obj: null },
                                                                                                                      controller: "SubscriptionMandateController",
                                                                                                                      controllerAs: "vm",
                                                                                                                      resolve: {
                                                                                                                        load: function ($ocLazyLoad) {
                                                                                                                          return $ocLazyLoad.load({
                                                                                                                            files: ["app/rbi_bond_module/gu_functionallity/scrolls/subscription_mandate/subscription_mandate.controller.js?v=" + versionNum],


                                                                                                                          });
                                                                                                                        },
                                                                                                                      },
                                                                                                                    })
                                                                                                                // Certificate_Printing end
                                                                                                                 //huf transfer start

                                                                                                                      .state("huf_transfer_to_single_and_joint_type_change", {
                                                                                                                        abstract: true,
                                                                                                                        url: "/huf_transfer_to_single_and_joint_type_change",
                                                                                                                        params: {
                                                                                                                          views: ["add","verify","inquiry"],
                                                                                                                          permissions: [
                                                                                                                            "GBM_GU_RBIBOND:RBIBOND_HUF_TRANSFER_SINGLE_JOINT:ADD",
                                                                                                                            "GBM_GU_RBIBOND:RBIBOND_HUF_TRANSFER_SINGLE_JOINT:VERIFY",
                                                                                                                            "GBM_GU_RBIBOND:RBIBOND_HUF_TRANSFER_SINGLE_JOINT:ENQUIRY",
                                                                                                                          ],
                                                                                                                        },
                                                                                                                        resolve: {
                                                                                                                          load: function ($ocLazyLoad) {
                                                                                                                            return $ocLazyLoad.load({
                                                                                                                              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/huf_transfer_to_single_and_joint_type_change/huf_transfer_to_single_and_joint_type_change.service.js?v=" + versionNum],
                                                                                                                            });
                                                                                                                          },
                                                                                                                        },
                                                                                                                      })

                                                                                                                      .state("huf_transfer_to_single_and_joint_type_change.add_input", {
                                                                                                                        url: "/add",
                                                                                                                        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/huf_transfer_to_single_and_joint_type_change/add_huf_transfer_to_single_and_joint_type_change_input.html",
                                                                                                                        data: {
                                                                                                                          authRequired: true,
                                                                                                                          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_HUF_TRANSFER_SINGLE_JOINT:ADD",
                                                                                                                        },
                                                                                                                        params: { obj: null },
                                                                                                                        controller: "hufTransferToSingleAndJointController",
                                                                                                                        controllerAs: "vm",
                                                                                                                        resolve: {
                                                                                                                          load: function ($ocLazyLoad) {
                                                                                                                            return $ocLazyLoad.load({
                                                                                                                              files: ["app/rbi_bond_module/gu_functionallity/masters/huf_transfer_to_single_and_joint_type_change/huf_transfer_to_single_and_joint_type_change.controller.js?v=" + versionNum],
                                                                                                                            });
                                                                                                                          },
                                                                                                                        },
                                                                                                                      })

                                                                                                                      .state("huf_transfer_to_single_and_joint_type_change.add_detail", {
                                                                                                                        url: "/add/detail",
                                                                                                                        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/huf_transfer_to_single_and_joint_type_change/add_huf_transfer_to_single_and_joint_type_change_detail.html",
                                                                                                                        data: {
                                                                                                                          authRequired: true,
                                                                                                                          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_HUF_TRANSFER_SINGLE_JOINT:ADD",
                                                                                                                        },
                                                                                                                        params: { obj: null },
                                                                                                                        controller: "hufTransferToSingleAndJointController",
                                                                                                                        controllerAs: "vm",
                                                                                                                        resolve: {
                                                                                                                          load: function ($ocLazyLoad) {
                                                                                                                            return $ocLazyLoad.load({
                                                                                                                              files: ["app/rbi_bond_module/gu_functionallity/masters/huf_transfer_to_single_and_joint_type_change/huf_transfer_to_single_and_joint_type_change.controller.js?v=" + versionNum],
                                                                                                                            });
                                                                                                                          },
                                                                                                                        },
                                                                                                                      })
                                                                                                                      .state("huf_transfer_to_single_and_joint_type_change.verify", {
                                                                                                                        url: "/action/verify",
                                                                                                                        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/huf_transfer_to_single_and_joint_type_change/verify_reject_huf_transfer_to_single_and_joint_type_change_input.html",
                                                                                                                        data: {
                                                                                                                          authRequired: true,
                                                                                                                          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_HUF_TRANSFER_SINGLE_JOINT:VERIFY",
                                                                                                                        },
                                                                                                                        controller: "hufTransferToSingleAndJointController",
                                                                                                                        controllerAs: "vm",
                                                                                                                        resolve: {
                                                                                                                          load: function ($ocLazyLoad) {
                                                                                                                            return $ocLazyLoad.load({
                                                                                                                              files: ["app/rbi_bond_module/gu_functionallity/masters/huf_transfer_to_single_and_joint_type_change/huf_transfer_to_single_and_joint_type_change.controller.js?v=" + versionNum],
                                                                                                                            });
                                                                                                                          },
                                                                                                                        },
                                                                                                                      })
                                                                                                                      .state("huf_transfer_to_single_and_joint_type_change.verify_detail", {
                                                                                                                        url: "/action/verify/detail",
                                                                                                                        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/huf_transfer_to_single_and_joint_type_change/verify_reject_huf_transfer_to_single_and_joint_type_change_detail.html",
                                                                                                                        data: {
                                                                                                                          authRequired: true,
                                                                                                                          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_HUF_TRANSFER_SINGLE_JOINT:VERIFY",
                                                                                                                        },
                                                                                                                        params: { obj: null },
                                                                                                                        controller: "hufTransferToSingleAndJointController",
                                                                                                                        controllerAs: "vm",
                                                                                                                        resolve: {
                                                                                                                          load: function ($ocLazyLoad) {
                                                                                                                            return $ocLazyLoad.load({
                                                                                                                              files: ["app/rbi_bond_module/gu_functionallity/masters/huf_transfer_to_single_and_joint_type_change/huf_transfer_to_single_and_joint_type_change.controller.js?v=" + versionNum],
                                                                                                                            });
                                                                                                                          },
                                                                                                                        },
                                                                                                                      })

                                                                                                                      .state("huf_transfer_to_single_and_joint_type_change.list", {
                                                                                                                        url: "/action/inquiry",
                                                                                                                        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/huf_transfer_to_single_and_joint_type_change/inquiry_huf_transfer_to_single_and_joint_type_change_input.html",
                                                                                                                        data: {
                                                                                                                          authRequired: true,
                                                                                                                          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_HUF_TRANSFER_SINGLE_JOINT:ENQUIRY",
                                                                                                                        },
                                                                                                                        controller: "hufTransferToSingleAndJointController",
                                                                                                                        controllerAs: "vm",
                                                                                                                        resolve: {
                                                                                                                          load: function ($ocLazyLoad) {
                                                                                                                            return $ocLazyLoad.load({
                                                                                                                              files: ["app/rbi_bond_module/gu_functionallity/masters/huf_transfer_to_single_and_joint_type_change/huf_transfer_to_single_and_joint_type_change.controller.js?v=" + versionNum],
                                                                                                                            });
                                                                                                                          },
                                                                                                                        },
                                                                                                                      })
                                                                                                                      .state("huf_transfer_to_single_and_joint_type_change.list_detail", {
                                                                                                                        url: "/action/inquiry/detail",
                                                                                                                        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/huf_transfer_to_single_and_joint_type_change/inquiry_huf_transfer_to_single_and_joint_type_change_detail.html",
                                                                                                                        data: {
                                                                                                                          authRequired: true,
                                                                                                                          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_HUF_TRANSFER_SINGLE_JOINT:ENQUIRY",
                                                                                                                        },
                                                                                                                        params: { obj: null },
                                                                                                                        controller: "hufTransferToSingleAndJointController",
                                                                                                                        controllerAs: "vm",
                                                                                                                        resolve: {
                                                                                                                          load: function ($ocLazyLoad) {
                                                                                                                            return $ocLazyLoad.load({
                                                                                                                              files: ["app/rbi_bond_module/gu_functionallity/masters/huf_transfer_to_single_and_joint_type_change/huf_transfer_to_single_and_joint_type_change.controller.js?v=" + versionNum],
                                                                                                                            });
                                                                                                                          },
                                                                                                                        },
                                                                                                                      })
                                                                                                                      //end huf transfer subscription
 // Inet Portfolio Report
      .state("inet_portfolio_report", {
        abstract: true,
        url: "/inet_portfolio_report",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:INET_PORTFOLIO_REPORT:REPORT"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbi_bond_portfolio_report/RBI_bond_portfolio_report_service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("inet_portfolio_report.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbi_bond_portfolio_report/RBI_bond_portfolio_report_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:INET_PORTFOLIO_REPORT:REPORT",
        },
        controller: "rbiInetPortfolioReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbi_bond_portfolio_report/RBI_bond_portfolio_report_controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("inet_portfolio_report.inquiry_detail", {
        url: "/action/inquiry/details",
        templateUrl: "app/rbi_bond_module/gu_functionallity/reports/rbi_bond_portfolio_report/RBI_bond_portfolio_report_input_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:INET_PORTFOLIO_REPORT:REPORT",
        },
        params: {
          obj: null,
        },
        controller: "rbiInetPortfolioReportController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/reports/rbi_bond_portfolio_report/RBI_bond_portfolio_report_controller.js?v=" + versionNum],
            });
          },
        },
      })
                                                                                                                  // Certificate_Printing end

/// HUF Death marking for BLA starts here

      .state("huf_death_marking_menu", {
        abstract: true,
        url: "/huf_death_marking_menu",
        params: {
          views: ["add", "verify", "inquiry"],
          permissions: ["GBM_GU_RBIBOND:RBIBOND_HUF_DEATH_MARKING_MENU:ADD",
		  "GBM_GU_RBIBOND:RBIBOND_HUF_DEATH_MARKING_MENU:VERIFY",
		  "GBM_GU_RBIBOND:RBIBOND_HUF_DEATH_MARKING_MENU:ENQUIRY"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/dc/common/left-menu.directive.js", "app/rbi_bond_module/gu_functionallity/masters/HUF_death_marking_menu/huf_death_marking_menu.service.js?v=" + versionNum],
            });
          },
        },
      })
      .state("huf_death_marking_menu.add", {
        url: "/add",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/HUF_death_marking_menu/add_huf_death_marking_menu.html",

        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_HUF_DEATH_MARKING_MENU:ADD",
        },
        controller: "rbibondHUFDeathMarkingMenuController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/HUF_death_marking_menu/huf_death_marking_menu.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("huf_death_marking_menu.add_detail", {
        url: "/action/add/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/HUF_death_marking_menu/add_huf_death_marking_menu_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_HUF_DEATH_MARKING_MENU:ADD",
        },
        params: { obj: null },
        controller: "rbibondHUFDeathMarkingMenuController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/HUF_death_marking_menu/huf_death_marking_menu.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("huf_death_marking_menu.verify", {
        url: "/action/verify",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/HUF_death_marking_menu/verify_huf_death_marking_menu.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_HUF_DEATH_MARKING_MENU:VERIFY",
        },
        controller: "rbibondHUFDeathMarkingMenuController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/HUF_death_marking_menu/huf_death_marking_menu.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("huf_death_marking_menu.verify_detail", {
        url: "/action/verify/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/HUF_death_marking_menu/verify_huf_death_marking_menu_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_HUF_DEATH_MARKING_MENU:VERIFY",
        },
        params: { obj: null },
        controller: "rbibondHUFDeathMarkingMenuController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/HUF_death_marking_menu/huf_death_marking_menu.controller.js?v=" + versionNum],
            });
          },
        },
      })

      .state("huf_death_marking_menu.inquiry", {
        url: "/action/inquiry",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/HUF_death_marking_menu/inquiry_huf_death_marking_menu.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_HUF_DEATH_MARKING_MENU:ENQUIRY",
        },
        controller: "rbibondHUFDeathMarkingMenuController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/HUF_death_marking_menu/huf_death_marking_menu.controller.js?v=" + versionNum],
            });
          },
        },
      })
      .state("huf_death_marking_menu.inquiry_detail", {
        url: "/action/inquiry/detail",
        templateUrl: "app/rbi_bond_module/gu_functionallity/masters/HUF_death_marking_menu/inquiry_huf_death_marking_menu_detail.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_GU_RBIBOND:RBIBOND_HUF_DEATH_MARKING_MENU:ENQUIRY",
        },
        params: { obj: null },
        controller: "rbibondHUFDeathMarkingMenuController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: ["app/rbi_bond_module/gu_functionallity/masters/HUF_death_marking_menu/huf_death_marking_menu.controller.js?v=" + versionNum],
            });
          },
        },
      })

      /// HUF Death marking for BLA ends here


.state('Data_Patch_Upload_Report', {
      url: '/Data_Patch_Upload_report',
      templateUrl: 'app/rbi_bond_module/gu_functionallity/reports/Data_Patch_Upload/data_patch_upload.html',
        params: {
            views: ['inquiry'],
            permissions: ['GBM_RBIBOND_GU_FUNCTIONALITY:DATA_PATCH_UPLOAD_REPORT:REPORT']
       },
       controller: 'dataPatchUploadController',
       controllerAs: 'vm',
       resolve: {
           load: function($ocLazyLoad) {
               return $ocLazyLoad.load({
                   files: [
                        'app/rbi_bond_module/gu_functionallity/reports/Data_Patch_Upload/data_patch_upload_controller.js?v='+versionNum,
                        'app/rbi_bond_module/gu_functionallity/reports/Data_Patch_Upload/data_patch_upload_service.js?v='+versionNum
                   ]
               });
           }
       }
})
 // transfer report start
                  .state("transfer_report", {
                          abstract: true,
                          url: "/transfer_report",
                          params: {
                            views: ["inquiry"],
                            permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:TRANSFER_REPORT:REPORT"],
                          },
                          resolve: {
                            load: function ($ocLazyLoad) {
                              return $ocLazyLoad.load({
                                files: ["app/rbi_bond_module/gu_functionallity/reports/Transfer_report/Transfer_report.service.js?v=" + versionNum],
                              });
                            },
                          },
                        })

                        .state("transfer_report.inquiry", {
                          url: "/action/inquiry",
                          templateUrl: "app/rbi_bond_module/gu_functionallity/reports/Transfer_report/Transfer_report_input.html",
                          data: {
                            authRequired: true,
                            requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:TRANSFER_REPORT:REPORT",
                          },
                          controller: "TransferReportController",
                          controllerAs: "vm",
                          resolve: {
                            load: function ($ocLazyLoad) {
                              return $ocLazyLoad.load({
                                files: [" app/rbi_bond_module/gu_functionallity/reports/Transfer_report/Transfer_report.controller.js?v=" + versionNum],
                              });
                            },
                          },
                        })

                        .state("transfer_report.inquiry_details", {
                          url: "/action/inquiry_details",
                          templateUrl: " app/rbi_bond_module/gu_functionallity/reports/Transfer_report/Transfer_report_details.html",
                          data: {
                            authRequired: true,
                            requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:TRANSFER_REPORT:REPORT",
                          },
                          params: {
                            obj: null,
                          },
                          controller: "TransferReportController",
                          controllerAs: "vm",
                          resolve: {
                            load: function ($ocLazyLoad) {
                              return $ocLazyLoad.load({
                                files: [" app/rbi_bond_module/gu_functionallity/reports/Transfer_report/Transfer_report.controller.js?v=" + versionNum],
                              });
                            },
                          },
                        })
						 // transfer report ends
//-------------------------------------------------HUF DEATH MARKING REPORT - STARTS
   .state("HUF_Death_Marking_Report", {
                         abstract: true,
                         url: "/huf_death_marking_report",
                         params: {
                           views: ["inquiry"],
                           permissions: ["GBM_RBIBOND_GU_FUNCTIONALITY:HUF_DEATH_MARKING_REPORT:REPORT"],
                         },
                         resolve: {
                           load: function ($ocLazyLoad) {
                             return $ocLazyLoad.load({
                               files: ["app/rbi_bond_module/gu_functionallity/reports/HUF_Death_Marking_Report/HUF_Death_Marking_Report.service.js?v=" + versionNum],
                             });
                           },
                         },
                       })

.state("HUF_Death_Marking_Report.inquiry", {
                         url: "/action/inquiry",
                         templateUrl: "app/rbi_bond_module/gu_functionallity/reports/HUF_Death_Marking_Report/HUF_Death_Marking_Report_input.html",
                         data: {
                           authRequired: true,
                           requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:HUF_DEATH_MARKING_REPORT:REPORT",
                         },
                         controller: "hufDeathMarkingReportController",
                         controllerAs: "vm",
                         resolve: {
                           load: function ($ocLazyLoad) {
                             return $ocLazyLoad.load({
                               files: ["app/rbi_bond_module/gu_functionallity/reports/HUF_Death_Marking_Report/HUF_Death_Marking_Report.controller.js?v=" + versionNum],
                             });
                           },
                         },
                       })

.state("HUF_Death_Marking_Report.inquiry_details", {
                         url: "/action/inquiry_details",
                         templateUrl: "app/rbi_bond_module/gu_functionallity/reports/HUF_Death_Marking_Report/HUF_Death_Marking_Report_details.html",
                         data: {
                           authRequired: true,
                           requiredPermission: "GBM_RBIBOND_GU_FUNCTIONALITY:HUF_DEATH_MARKING_REPORT:REPORT",
                         },
                         params: {
                           obj: null,
                         },
                         controller: "hufDeathMarkingReportController",
                         controllerAs: "vm",
                         resolve: {
                           load: function ($ocLazyLoad) {
                             return $ocLazyLoad.load({
                               files: ["app/rbi_bond_module/gu_functionallity/reports/HUF_Death_Marking_Report/HUF_Death_Marking_Report.controller.js?v=" + versionNum],
                             });
                           },
                         },
                       })
//-------------------------------------------------HUF DEATH MARKING REPORT - ENDS
//-------------------------------------------------HALF YEARLY INTEREST SCROLL - STARTS
        .state("half_yearly_interest_scroll", {
        abstract: true,
        url: "/half_yearly_interest_scroll",
        params: {
          views: ["inquiry"],
          permissions: ["GBM_SCROLL_RBIBOND:HALF_YEARLY_INTEREST_SCROLL:SCROLL"],
        },
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: [
                "app/rbi_bond_module/gu_functionallity/scrolls/Half_Yearly_Interest_Scroll/half_yearly_interest_scroll_service.js?v=" +
                  versionNum,
              ],
            });
          },
        },
      })
      .state("half_yearly_interest_scroll.inquiry", {
        url: "/action/inquiry",
        templateUrl:
          "app/rbi_bond_module/gu_functionallity/scrolls/Half_Yearly_Interest_Scroll/half_yearly_interest_scroll_input.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:HALF_YEARLY_INTEREST_SCROLL:SCROLL",
        },
        controller: "HalfYearlyInterestScrollController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: [
                "app/rbi_bond_module/gu_functionallity/scrolls/Half_Yearly_Interest_Scroll/half_yearly_interest_scroll_controller.js?v=" +
                  versionNum,
              ],
            });
          },
        },
      })
      .state("half_yearly_interest_scroll.inquiry_details", {
        url: "/action/inquiry/details",
        templateUrl:
          "app/rbi_bond_module/gu_functionallity/scrolls/Half_Yearly_Interest_Scroll/half_yearly_interest_scroll_details.html",
        data: {
          authRequired: true,
          requiredPermission: "GBM_SCROLL_RBIBOND:HALF_YEARLY_INTEREST_SCROLL:SCROLL",
        },
        params: { obj: null },
        controller: "HalfYearlyInterestScrollController",
        controllerAs: "vm",
        resolve: {
          load: function ($ocLazyLoad) {
            return $ocLazyLoad.load({
              files: [
                "app/rbi_bond_module/gu_functionallity/scrolls/Half_Yearly_Interest_Scroll/half_yearly_interest_scroll_controller.js?v=" +
                  versionNum,
              ],
            });
          },
        },
      })
//-------------------------------------------------HALF YEARLY INTEREST SCROLL - ENDS
  }
})();