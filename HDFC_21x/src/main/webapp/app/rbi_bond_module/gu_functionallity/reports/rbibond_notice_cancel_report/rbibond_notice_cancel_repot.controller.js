(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("rbibondNoticeCancelController", rbibondNoticeCancelController);

  rbibondNoticeCancelController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "rbibondNoticeCancelService", "commonService"];

  function rbibondNoticeCancelController($scope, $filter, $state, $stateParams, $location, $rootScope, rbibondNoticeCancelService, commonService) {
    var vm = this;
    vm.getDetails = getDetails;
    vm.textableDatePicker = textableDatePicker;
    vm.autoslashing = autoslashing;

    function textableDatePicker(id, valueDate) {
      commonService.textableDatePicker(id, valueDate);
    }
    function autoslashing(id) {
      commonService.autoslashing(id);
    }

    // Pagination
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
      vm.getDetails();
    };

    vm.goNextPage = function () {
      vm.pageNo = parseInt(vm.pageNo) + 1;
      vm.getDetails();
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

    vm.getSchemeName = function () {
      vm.loader = true;

      var requestData = {
        flag: "S",
      };
      rbibondNoticeCancelService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result.list;
          angular.element("#schemeModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    };

    if ($state.current.name == "rbibond_notice_cancel_report.inquiry_details") {
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

    function getDetails() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "A",
        pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchText,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
      };

      rbibondNoticeCancelService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.model = response.data.result.list;
          vm.filteredRecords = response.data.result.list;
          vm.totalAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.amount ? parseFloat(record.amount) : 0), 0);
          vm.total = response.data.total;
          vm.maxPages = Math.ceil(response.data.total / vm.pageSize);
          vm.paginationControls();
          if (vm.model.length === 0) {
            vm.noDataFoundInSearch = true;
          } else {
            vm.noDataFoundInSearch = false;
          }
        },
        function (error) {
          vm.loader = false;
          if ((error.data.message == "No Data Found" || error.data.message == "NDF") && vm.searchText) {
            vm.noDataFoundInSearch = true;
          } else if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("rbibond_notice_cancel_report.inquiry");
          }
        }
      );
    }

    vm.getDownload = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "A",
        pageNo: "1",
        pageSize: vm.total,
        searchValue: vm.searchText,
      };
      rbibondNoticeCancelService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.downloadFilteredRecords = response.data.result.list;
          vm.downloadPDF();
        },
        function (error) {
          vm.loader = false;
          if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("rbibond_notice_cancel_report.inquiry");
          }
        }
      );
    };

    vm.downloadPDF = function () {
      const { jsPDF } = window.jspdf;
      const doc = new jsPDF({ orientation: "portrait", unit: "mm", format: "a4" });

      const headers = "Annexure 5 \n \n Form - 'D'";
      const title = `${vm.schemeName} \n \n NOTICE OF CANCELLATION OF NOMINATION`;
      const instructions =
        "Instructions – The Nomination and its Cancellation shall be governed by the provisions of the Government Securities Act 2006(38 of 2006) published in Part III, Section 4 of the Gazette of India dated December 1, 2007.\n\n";
      const footerText = "Place  : \n\n Date  : \n\n(Signature(s)/ Thumb impression of the BLA holder(s)) Signature with name and address of witnesses: \n\n 1. ………………………………………………………………………………………… \n\n 2. …………………………………………………………………………………………";

      doc.setFont("Times", "normal");
      doc.setFontSize(12);
      doc.text(headers, 200, 10, { align: "right" });

      doc.setFont("Times", "bold");
      doc.text(title, 105, 30, { align: "center" });
      doc.setFontSize(11);
      doc.setFont("Times", "normal");
      doc.text(instructions, 10, 50, { maxWidth: 190 });
      doc.setFontSize(12);

      // Table Data
      const tableHeaders = ["S.No", "Name of the Bond Scheme", "Date of Issue", "Bond Ledger Account No.", "Bond Subscription ID", "Amount Rs.", "Deleted nominees"];
      const tableData = vm.downloadFilteredRecords.map((record, index) => [index + 1, record.schemeName, record.dateOfIssue, record.blaNumber, record.subscriptionId, record.amount, record.nomineeName]);
      vm.totalAmountDownload = vm.downloadFilteredRecords.reduce((sum, record) => sum + (record.amount ? parseFloat(record.amount) : 0), 0);
      const totalRow = ["", "", "", "", "Total", vm.totalAmountDownload, ""];
      tableData.push(totalRow);

      doc.autoTable({
        head: [tableHeaders],
        body: tableData,
        startY: 60,
        theme: "grid",
        pageBreak: "auto",
        rowPageBreak: "avoid",
        showHead: "firstPage",
        margin: { left: 10, right: 10 },
        styles: { fontSize: 10, cellPadding: 3 },
      });
      doc.setFont("Times", "normal");
      doc.setFontSize(12);
      const finalY = doc.autoTable.previous.finalY;
      doc.text(footerText, 10, finalY + 10, { align: "left" });
      doc.save("Notice of Cancellation of Nomination.pdf");
    };

vm.indianNumberFormat=indianNumberFormat;
                function indianNumberFormat(value) {
                  if (isNaN(value)|| null == value) return value;
                  let numStr = value.toString();
                  let [whole, decimal] = numStr.split('.');
                  if (whole.length <= 3) {
                      return decimal ? whole + '.' + decimal : whole + '.00';
                    }
                  let lastThree = whole.slice(-3);  // last three digits
                  let otherNumbers = whole.slice(0, whole.length - 3);
                  if (otherNumbers !== '') {
                    lastThree = ',' + lastThree;
                  }
                  let formattedWhole = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ',') + lastThree;
                if (decimal) {
                    return formattedWhole + '.' + decimal;
                  } else {
                    return formattedWhole+'.00';
                  }
                }
  }
})();
