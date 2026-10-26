(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("BondInvestDueDateNoticeController", BondInvestDueDateNoticeController);

  BondInvestDueDateNoticeController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "BondInvestDueDateNoticeService", "commonService"];

  function BondInvestDueDateNoticeController($scope, $filter, $state, $stateParams, $location, $rootScope, BondInvestDueDateNoticeService, commonService) {
    var vm = this;
    vm.solDate = $rootScope.globals.currentUser.systemDate;
    vm.bankName = $rootScope.globals.currentUser.bankName;
    vm.branchOfTheBank = $rootScope.globals.currentUser.solDesc;
    vm.branchCode = $rootScope.globals.currentUser.solId;
    vm.formatDate = function (tempDate) {
      return $filter("date")(tempDate, "dd/MM/yyyy");
    };

    if ($state.current.name == "Bond_Investment_Due_Date_Notice_report.inquiry_details") {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.fromDate = $stateParams.obj.fromDate;
      vm.toDate = $stateParams.obj.toDate;
      getDetails();
    }

    vm.fromDateVal = function () {
      vm.fromDateOptions = {
        maxDate: new Date(),
        showWeeks: false,
      };
    };

    vm.toDateVal = function (fromDate) {
      vm.toDateOptions = {
        minDate: fromDate,
        maxDate: new Date(),
        showWeeks: false,
      };
    };


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




    function getDetails() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: moment(vm.fromDate).format("DD/MM/YYYY"),
        toDate: moment(vm.toDate).format("DD/MM/YYYY"),
        flag: "T",
      };
      BondInvestDueDateNoticeService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.model = response.data.result.list;
        },
        function (error) {
          vm.loader = false;
          if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
            $state.go("Bond_Investment_Due_Date_Notice_report.inquiry_details");
          } else {
            handleErrorResponse(error);
            $state.go("Bond_Investment_Due_Date_Notice_report.inquiry");
          }
        }
      );
    }

    vm.getSchemeName = function () {
      vm.loader = true;
      var requestData = {
        flag: "S",
      };

      BondInvestDueDateNoticeService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result.list;
          angular.element("#schemeModel").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    };

    vm.printPage = function () {
      window.print();
    };
  }
})();
