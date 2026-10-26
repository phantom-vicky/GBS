(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("PortfolioController", PortfolioController);

  PortfolioController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "PortfolioService", "commonService"];

  function PortfolioController($scope, $filter, $state, $stateParams, $location, $rootScope, PortfolioService, commonService) {
    var vm = this;
    vm.getDetails = getDetails;
     vm.dateValidation = dateValidation;
     vm.autoslashing=autoslashing;
      vm.textableDatePicker=textableDatePicker;
    vm.solDate = $rootScope.globals.currentUser.systemDate;
    vm.getSchemeName = function () {
      vm.loader = true;
      var requestData = {
      };
      PortfolioService.getDetails(requestData).then(
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


function textableDatePicker(id, valueDate) {

    commonService.textableDatePicker(id, valueDate);

    if (id === 'effToDate') {

        if (!valueDate) {
            vm.toDate = null;
            swal("Missing Date!", "Please select a valid date.", "warning");
            return false;
        }

        var parts = valueDate.split('/');
        var parsedDate = new Date(parts[2], parts[1] - 1, parts[0]);

        if (isNaN(parsedDate.getTime())) {
            vm.toDate = null;
            swal("Missing Date!", "Please select a valid date.", "warning");
            return false;
        }

        else if (!isWithin7Days(parsedDate)) {
            vm.toDate = null;
            swal("Invalid Date!", "To Date cannot be more than 7 days after the From Date.", "error");
            return false;
        }
    }

    return true;
}
function autoslashing(id)
{
 commonService.autoslashing(id);
}
 function dateValidation() {
      vm.today = new Date();

           // Datepicker options
           vm.dateOptions = {
               maxDate: vm.today
           };

           vm.dateToOptions = {
               minDate: vm.fromDate ? new Date(vm.fromDate) : null,
               maxDate: vm.today,
               showWeeks: false
           };

           // Validation
           if (vm.fromDate && vm.toDate) {

               var from = new Date(vm.fromDate);
               var to = new Date(vm.toDate);

               if (to < from) {
                   vm.toDate = null;
                   vm.dateError = "To Date should not be less than From Date";
               } else {
                   vm.dateError = "";
               }
           }
    }




    function getDetails() {
      vm.loader = true;
       var requestData = {
              schemeCode: vm.schemeCode,
                 fromDate: vm.fromDate ? moment(vm.fromDate).format("DD-MMM-YYYY") : "",
                        toDate: vm.toDate ? moment(vm.toDate).format("DD-MMM-YYYY") : ""
            };
      PortfolioService.getDetails(requestData).then(function (response) {
                vm.loader = false;
                vm.schemeName=null;
                vm.fromDate=null;
                 vm.toDate=null;
          if (response.data && response.data.result && response.data.result.fileName && response.data.result.fileData) {
              var fileData = response.data.result.fileData;
              var fileName = response.data.result.fileName;
              saveAs(getBlobFromBase64String(fileData), fileName);
              Swal.fire("Downloaded",response.data.message || "File downloaded successfully","success");
              return;
          }
      },
        function (error) {
          vm.loader = false;
           vm.schemeName=null;
            vm.fromDate=null;
            vm.toDate=null;
            handleErrorResponse(error);
            $state.go("Portfolio_Report.inquiry");
        }
      );
    }
  }
})();
