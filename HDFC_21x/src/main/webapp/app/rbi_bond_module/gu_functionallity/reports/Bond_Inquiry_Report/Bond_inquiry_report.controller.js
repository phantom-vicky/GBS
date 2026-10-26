(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("bondInquiryReportController", bondInquiryReportController);

  bondInquiryReportController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "bondInquiryReportService", "commonService"];

  function bondInquiryReportController($scope, $filter, $state, $stateParams, $location, $rootScope, bondInquiryReportService, commonService) {
    var vm = this;

    vm.loginSolId = $rootScope.globals.currentUser.solId;
    vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
    vm.solDate = $rootScope.globals.currentUser.systemDate;
    vm.getModuleName = getModuleName;
    vm.dateValidation = dateValidation;
    vm.getStateName = getStateName;
    vm.getNriAccDetails = getNriAccDetails;
    vm.getHolderTypeDetails = getHolderTypeDetails;
    vm.bankName = $rootScope.globals.currentUser.bankName;
    vm.getSubAccInputModuleName = getSubAccInputModuleName;


vm.textableDatePicker=textableDatePicker;
vm.autoslashing=autoslashing;

function textableDatePicker(id,valueDate)
{
 commonService.textableDatePicker(id,valueDate);
}
function autoslashing(id)
{
 commonService.autoslashing(id);
}


    if ($state.current.name == "rbiBond_bond_inquiry_nri_acc_det_report.inquiry_detail") {
      vm.title = "NRI Account Details";
      if ($stateParams.obj) {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.getNriAccDetails();
      }
    } else if ($state.current.name == "rbiBond_bond_inquiry_holder_type_det_report.inquiry_detail") {
      vm.title = "Holder Type Details";
      if ($stateParams.obj) {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.holderType = $stateParams.obj.holderType;
        vm.getHolderTypeDetails();
      }
    }

    function getStateName(flag) {
      if (flag == "subsAcc") {
        $state.go("rbiBond_bond_inquiry_subs_acc_det_report.inquiry");
      } else if (flag == "Bla") {
        $state.go("rbiBond_bond_inquiry_bla_acc_det_report.inquiry");
      } else if (flag == "subsAmt") {
        $state.go("rbiBond_bond_inquiry_subs_amt_det_report.inquiry");
      } else if (flag == "matAcc") {
        $state.go("rbiBond_bond_inquiry_matured_acc_det_report.inquiry");
      } else if (flag == "accCls") {
        $state.go("rbiBond_bond_inquiry_acc_closed_det_report.inquiry");
      }

      else if (flag == "nriAcc") {
        $state.go("rbiBond_bond_inquiry_nri_acc_det_report.inquiry");
      } else if (flag == "hldrType") {
        $state.go("rbiBond_bond_inquiry_holder_type_det_report.inquiry");
      } else if (flag == "tranInOut") {
        $state.go("rbiBond_bond_inquiry_transfer_in_out_det_report.inquiry");
      }
    }

    function configDataTable() {
      vm.getTitleDescription = {
        title: vm.title,
        solId: $rootScope.globals.currentUser.solId,
        solDesc: $rootScope.globals.currentUser.solDesc,
      };
      vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
    }

    function getModuleName() {
      vm.loader = true;
      vm.referenceId = "TAX";
      var requestData = {
        referenceId: vm.referenceId,
      };
      bondInquiryReportService.getRbiBondModuleNameDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result;
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function getSubAccInputModuleName() {
      vm.loader = true;
      vm.referenceId = "TAX";
      var requestData = {
        referenceId: vm.referenceId,
      };
      bondInquiryReportService.getRbiBondModuleNameDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result;
          angular.element("#schemeModel").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function getNriAccDetails() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
      };

      bondInquiryReportService.getNriAccDetails(requestData).then(
        function (response) {
          vm.loader = false;

          vm.nriDetList = response.data.result;

          configDataTable();
        },
        function (error) {
          vm.loader = false;
          if ((error.data.message == "No Data Found") | (error.data.message == "NDF")) {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("rbiBond_bond_inquiry.inquiry");
          }
        }
      );
    }

    function getHolderTypeDetails() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        holderType: vm.holderType,
      };

      bondInquiryReportService.getHolderTypeDetails(requestData).then(
        function (response) {
          vm.loader = false;

          vm.holderTypeDetList = response.data.result;

          configDataTable();
        },
        function (error) {
          vm.loader = false;
          if ((error.data.message == "No Data Found") | (error.data.message == "NDF")) {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("rbiBond_bond_inquiry.inquiry");
          }
        }
      );
    }

    function dateValidation() {
      if (vm.fromDate1) {
        vm.fromDate1.setDate(vm.fromDate1.getDate() + parseInt(30));

        if (vm.fromDate1 > vm.solDate) vm.fromDate1 = vm.solDate;
      }

      vm.dateToOptions = {
        minDate: vm.fromDate,
        maxDate: vm.solDate,
      };
      vm.dateOptions = {
        maxDate: vm.solDate,
      };

      vm.dateLimits = {
        minDate: vm.fromDate,
        maxDate: vm.fromDate1,
      };
    }
  }
})();
