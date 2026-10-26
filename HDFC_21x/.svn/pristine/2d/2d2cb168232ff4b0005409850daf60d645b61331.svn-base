(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("PANValidationReportController", PANValidationReportController);

  PANValidationReportController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "PANValidationReportService", "commonService"];

  function PANValidationReportController($scope, $filter, $state, $stateParams, $location, $rootScope, PANValidationReportService, commonService) {
    var vm = this;
  vm.filteredRecords = [];
    vm.currentPage = 1;
    vm.itemsPerPage = "5";
    vm.loginSolId = $rootScope.globals.currentUser.solId;
    vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
    vm.solDate = $rootScope.globals.currentUser.systemDate;
    vm.currentDate = new Date();
    vm.formatIndianCurrency = formatIndianCurrency;
    vm.currentDate = $filter("date")(vm.currentDate, "dd/MM/yyyy");
    vm.getCertificateDetail = getCertificateDetail;
    vm.getModuleName = getModuleName;
    vm.printpage = printpage;
    vm.dateValidation = dateValidation;
vm.textableDatePicker=textableDatePicker;
vm.autoslashing=autoslashing;
vm.convertNumberToWords = convertNumberToWords;


 vm.filteredRecords = [];
        vm.currentPage = 1;
        vm.maxPagesToShow = 5;
        vm.searchText = "";
        vm.loading = true;
        vm.showExportOptions = false;
        vm.itemsPerPage = "5";
        vm.showNext = false;
        vm.showPrevious = false;
        $scope.sortReverse = false;
        $scope.sort = function (key) {
          $scope.sortKey = key;
          $scope.sortReverse = $scope.sortKey == key ? !$scope.sortReverse : $scope.sortReverse;
          $scope.sortReverse ? (vm.sortOrder = "DESC") : (vm.sortOrder = "ASC");
          vm.sortField = key;
          vm.getDetails();
        };

        vm.pageNo = 1;
        vm.pageSize = 5;

        vm.goPreviousPage = function () {
          vm.pageNo = parseInt(vm.pageNo) - 1;
          vm.getCertificateDetail();
        };

        vm.goNextPage = function () {
          vm.pageNo = parseInt(vm.pageNo) + 1;
          vm.getCertificateDetail();
        };

        vm.paginationControls = function () {
          vm.pages = [];
          for (var i = 1; i <= parseInt(vm.maxPages); i++) {
            vm.pages.push(parseInt(i));
          }
          if (vm.pageNo != 1) {
            vm.showPrevious = true;
          } else {
            vm.showPrevious = false;
          }
          if (vm.pageNo != vm.maxPages) {
            vm.showNext = true;
          } else {
            vm.showNext = false;
          }
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
function isWithin7Days(valueDate){
if (!valueDate || !vm.fromDate) {
     return false;
   }
   const fromDate = new Date(vm.fromDate);
   const valDate = new Date(valueDate);
    const diffTime = valDate.getTime() - fromDate.getTime();
    const diffDays = diffTime / (1000 * 60 * 60 * 24);
    return diffDays >= 0 && diffDays <= 7;
}
function autoslashing(id)
{
 commonService.autoslashing(id);
}




    if ($state.current.name == "pan_validation_report.inquiry_details") {
      vm.schemeCode = $stateParams.obj.schemeCode;
vm.panTriggered=$stateParams.obj.panTriggered;
vm.status=$stateParams.obj.status;
      vm.subscriptionId = $stateParams.obj.subscriptionId;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.fromDate = $stateParams.obj.fromDate;
      vm.toDate = $stateParams.obj.toDate;
      if (vm.schemeCode == "0012") {
        vm.schemeName = "Floating Rate Savings Bonds, 2020 (Taxable)";
      }

      getCertificateDetail();
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

    function configDataTable() {
      vm.getTitleDescription = {
        title: "Certificate of Investment",
        solId: $rootScope.globals.currentUser.solId,
        solDesc: $rootScope.globals.currentUser.solDesc,
      };
      vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
    }

    function getModuleName() {
      vm.loader = true;

      var requestData = {};
      PANValidationReportService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result.panValidationReportDTOList;
          angular.element("#schemeModel").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }




    function printpage(fileName) {
       const originalTitle = document.title;
          document.title = fileName;
          window.print();
          setTimeout(() => {
            document.title = originalTitle;
          }, 1000);
    }



vm.downloadReport = function () {

    vm.loader = true;
    vm.noDataFound = "";

    var requestData = {
        schemeCode: vm.schemeCode,
        schemeName: vm.schemeName,
        pageNoIn: '1',
        pageSizeIn: vm.total,
        panTriggered:vm.panTriggered,
        status:vm.status,

        fromDate: vm.fromDate ? moment(vm.fromDate).format("DD-MMM-YYYY") : "",
        toDate: vm.toDate ? moment(vm.toDate).format("DD-MMM-YYYY") : ""
    };

    if (vm.downloadType === "excel") {

        PANValidationReportService.getPANValidationReportDownload(requestData).then(

            function (response) {

                vm.loader = false;
                if (response.data &&
                    response.data.result &&
                    response.data.result.fileName &&
                    response.data.result.fileData) {

                    var fileData = response.data.result.fileData;
                    var fileName = response.data.result.fileName;

                    saveAs(getBlobFromBase64String(fileData), fileName);

                    Swal.fire(
                        "Downloaded",
                        response.data.message || "File downloaded successfully",
                        "success"
                    );

                    return;
                }

                vm.noDataFound = "No Data Found";
            },

            function (error) {

                vm.loader = false;

                if (error.data &&
                    (error.data.message === "No Data Found" || error.data.message === "NDF")) {

                    vm.noDataFound = error.data.message;

                } else {

                    handleErrorResponse(error);
                    $state.go("PAN_Validation_Report.inquiry");
                }
            }
        );
    }
                           vm.downloadType = "";

};

    function getCertificateDetail() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
         schemeName:vm.schemeName ,
         panTriggered:vm.panTriggered,
                 status:vm.status,
       fromDate            : vm.fromDate ? moment(vm.fromDate).format("DD-MMM-YYYY") : "",
        toDate              : vm.toDate ? moment(vm.toDate).format("DD-MMM-YYYY") : "",
     pageNoIn: vm.pageNo,
            pageSizeIn: vm.pageSize,
            searchValIn: vm.searchValue,
      };

      PANValidationReportService.getDetails(requestData).then(
        function (response) {
        console.log(response);
          vm.loader = false;
          vm.schemeFlag=response.data.result.schemeFlag;
          vm.filteredRecords=response.data.result.panValidationReportDTOList;
vm.total=response.data.total;
  vm.maxPages = Math.ceil(response.data.total / vm.pageSize);
          vm.paginationControls();
 if (vm.filteredRecords.length === 0) {
            vm.noDataFoundInSearch = true;
          } else {
            vm.noDataFoundInSearch = false;
          }

        },
        function (error) {
          vm.loader = false;
           vm.noDataFoundInSearch = true;
           vm.total= error.data.total;
                                             vm.pageNo= 0;
                                             vm.maxPages = Math.ceil(error.data.total / vm.pageSize);
                                             vm.paginationControls();
                                             vm.showPrevious= false;
          if ((error.data.message == "No Data Found") | (error.data.message == "NDF")) {
            vm.noDataFound = error.data.message;
            $state.go("pan_validation_report.inquiry_details");
          } else {
            handleErrorResponse(error);
            $state.go("pan_validation_report.inquiry_details");
          }
        }
      );
    }


       vm.paginationControls = function () {
              vm.pages = [];
              for (var i = 1; i <= parseInt(vm.maxPages); i++) {
                vm.pages.push(parseInt(i));
              }
              if (vm.pageNo != 1) {
                vm.showPrevious = true;
              } else {
                vm.showPrevious = false;
              }
              if (vm.pageNo != vm.maxPages) {
                vm.showNext = true;
              } else {
                vm.showNext = false;
              }
            };


         function convertNumberToWords (num) {
               if (num === 0) return "Rupees zero ONLY";

               const belowTwenty = ["one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                                    "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"];
               const tens = ["twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"];
               const denominations = ["", "thousand", "lakh", "crore"];

               function convertChunk(num) {
                   let result = "";
                   if (num >= 100) {
                       result += belowTwenty[Math.floor(num / 100) - 1] + " hundred ";
                       num %= 100;
                   }
                   if (num >= 20) {
                       result += tens[Math.floor(num / 10) - 2] + " ";
                       num %= 10;
                   }
                   if (num > 0) {
                       result += belowTwenty[num - 1] + " ";
                   }
                   return result.trim();
               }

 vm.indianFormat = function (input) {
        if (!input) return "";

         // Convert input to a number
         var num = parseFloat(input);
         if (isNaN(num)) return input;
         // Format number in Indian style
       return num.toLocaleString('en-IN', {
         minimumFractionDigits: 2,
         maximumFractionDigits: 2
     });
         };
               function convertToIndianFormat(num) {
                   if (num === 0) return "";
                   const parts = [];
                   let counter = 0;

                   // Extract crore, lakh, thousand, and remaining part
                   const crore = Math.floor(num / 10000000);
                   num %= 10000000;
                   const lakh = Math.floor(num / 100000);
                   num %= 100000;
                   const thousand = Math.floor(num / 1000);
                   num %= 1000;
                   const remaining = num;

                   if (crore > 0) parts.push(convertChunk(crore) + " crore");
                   if (lakh > 0) parts.push(convertChunk(lakh) + " lakh");
                   if (thousand > 0) parts.push(convertChunk(thousand) + " thousand");
                   if (remaining > 0) parts.push(convertChunk(remaining));

                   return parts.join(" ").trim();
               }

               const result = convertToIndianFormat(num);
               vm.netPaymentInWords = "Rupees " + result + " ONLY";
               return vm.netPaymentInWords;
           };

        function formatIndianCurrency(amount) {
            if (amount === undefined) {
                return null;
            }

            return new Intl.NumberFormat('en-IN', {
                                                        minimumFractionDigits: 2,
                                                        maximumFractionDigits: 2
                                                    }).format(amount);
        }

  }
})();
