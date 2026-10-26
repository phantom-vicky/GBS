(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("HalfYearlyInterestScrollController", HalfYearlyInterestScrollController);

  HalfYearlyInterestScrollController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$stateParams",
    "$rootScope",
    "halfYearlyInterestScrollService",
    "commonService",
  ];

  function HalfYearlyInterestScrollController($scope, $filter, $state, $stateParams, $rootScope, halfYearlyInterestScrollService, commonService) {
    var vm = this;

    vm.interestPaymentTypes = [
      { code: "HY", label: "Half Yearly Payment" },
//      { code: "RR", label: "Reject Re-initiate Payment" },
    ];

    vm.init = function () {
      vm.branchId = $rootScope.globals.currentUser.solId || $rootScope.solId || "";
      vm.branchName = $rootScope.globals.currentUser.solDesc || $rootScope.solDesc || "";
      vm.moduleName = "RBI Bond";
      vm.financialYear = getCurrentFinancialYear();
      vm.halfYearMonthOptions = [];
      vm.pageSizeOptions = [10, 25, 50, 100];
      vm.pageSize = 10;
      vm.currentPage = 1;
    };
    vm.init();

    vm.getSchemeName = getSchemeName;
    vm.onInterestPaymentTypeChange = onInterestPaymentTypeChange;
    vm.onSchemeSelected = onSchemeSelected;
    vm.onHalfYearMonthChange = onHalfYearMonthChange;
    vm.fromDateVal = fromDateVal;
    vm.toDateVal = toDateVal;
    vm.textableDatePicker = textableDatePicker;
    vm.textableDatePickerTo = textableDatePickerTo;
    vm.autoslashing = autoslashing;
    vm.allowOnlyNumber = allowOnlyNumber;
    vm.formatFinancialYear = formatFinancialYear;
    vm.goToDetails = goToDetails;
    vm.updatePagination = updatePagination;
    vm.updatePageSize = updatePageSize;
    vm.printpage = function () {
      window.print();
    };
    vm.downloadCertificationLetter = downloadCertificationLetter;
    vm.downloadAnnexureLetter = downloadAnnexureLetter;
    vm.downloadPdoTallyLetter = downloadPdoTallyLetter;
    vm.downloadPdoInterestFile = downloadPdoInterestFile;

    if ($state.current.name === "half_yearly_interest_scroll.inquiry_details" && $stateParams.obj) {
      applyStateParams($stateParams.obj);
      loadScrollDetails();
    }

    function applyStateParams(obj) {
      vm.schemeCode = obj.schemeCode;
      vm.schemeName = obj.schemeName;
      vm.schemeStatus = obj.schemeStatus;
      vm.interestPaymentType = obj.interestPaymentType;
      vm.interestPaymentTypeLabel = obj.interestPaymentTypeLabel;
      vm.halfYearMonth = obj.halfYearMonth;
      vm.year = obj.year;
      vm.fromDate = obj.fromDate;
      vm.toDate = obj.toDate;
      vm.financialYear = obj.financialYear;
    }

    function getSchemeName() {
      vm.loader = true;
      halfYearlyInterestScrollService
        .getDetails({ flag: "S" })
        .then(
          function (response) {
            vm.loader = false;
            vm.schemeList = response.data.result.list || [];
            angular.element("#schemeModel").modal("toggle");
          },
          function (error) {
            vm.loader = false;
            handleErrorResponse(error);
          }
        );
    }

    function onSchemeSelected() {
      vm.halfYearMonth = "";
      vm.year = "";
      vm.halfYearMonthOptions = [];
      if (vm.schemeCode && vm.interestPaymentType === "HY") {
        loadHalfYearMonths();
      }
    }

    function onInterestPaymentTypeChange() {
      vm.halfYearMonth = "";
      vm.year = "";
      vm.fromDate = "";
      vm.toDate = "";
      vm.halfYearMonthOptions = [];
      if (vm.interestPaymentType === "HY" && vm.schemeCode) {
        loadHalfYearMonths();
      }
    }

    function loadHalfYearMonths() {
      vm.loader = true;
      halfYearlyInterestScrollService.getHalfYearMonthsForScheme(vm.schemeCode).then(
        function (response) {
          vm.loader = false;
          vm.halfYearMonthOptions = response.data.result || [];
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function onHalfYearMonthChange() {
      var selected = (vm.halfYearMonthOptions || []).find(function (item) {
        return item.halfYear === vm.halfYearMonth;
      });
      vm.year = selected ? selected.year : "";
    }

    function goToDetails() {
      var typeLabel = "";
      vm.interestPaymentTypes.forEach(function (t) {
        if (t.code === vm.interestPaymentType) {
          typeLabel = t.label;
        }
      });
      $state.go("half_yearly_interest_scroll.inquiry_details", {
        obj: {
          schemeCode: vm.schemeCode,
          schemeName: vm.schemeName,
          schemeStatus: vm.schemeStatus,
          interestPaymentType: vm.interestPaymentType,
          interestPaymentTypeLabel: typeLabel,
          halfYearMonth: vm.halfYearMonth,
          year: vm.year,
          fromDate: vm.fromDate,
          toDate: vm.toDate,
          financialYear: vm.financialYear,
        },
      });
    }

    function loadScrollDetails() {
      vm.loader = true;
      vm.noDataFound = false;
      var requestData = {
        schemeCode: vm.schemeCode,
        interestPaymentType: vm.interestPaymentType,
        halfYearMonth: vm.halfYearMonth,
        year: vm.year,
        fromDate: vm.fromDate || "",
        toDate: vm.toDate || "",
        financialYear: vm.financialYear,
        flag: "A",
      };

      halfYearlyInterestScrollService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          var list = (response.data.result && response.data.result.list) || [];
          var message = response.data && response.data.message;
          if (!list.length || message === "No Data Found" || message === "NDF") {
            vm.noDataFound = message || "NDF";
            vm.model = [];
            return;
          }
          vm.model = list;
          vm.filteredRecords = vm.model;
          vm.totalPages = Math.ceil(vm.model.length / vm.pageSize) || 1;
          vm.currentPage = 1;
          vm.updatePagination();
        },
        function (error) {
          vm.loader = false;
          if (error.data && (error.data.message === "No Data Found" || error.data.message === "NDF")) {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
          }
        }
      );
    }

    function updatePagination() {
      vm.totalPages = Math.ceil((vm.filteredRecords || []).length / vm.pageSize) || 1;
      if (vm.currentPage > vm.totalPages) {
        vm.currentPage = vm.totalPages;
      }
      var start = (vm.currentPage - 1) * vm.pageSize;
      vm.pageDetails = (vm.filteredRecords || []).slice(start, start + vm.pageSize);
    }

    function updatePageSize() {
      vm.currentPage = 1;
      vm.updatePagination();
    }

    $scope.$watch("vm.searchText", function () {
      if (!vm.model) {
        return;
      }
      var text = (vm.searchText || "").toLowerCase();
      vm.filteredRecords = vm.model.filter(function (row) {
        return JSON.stringify(row).toLowerCase().indexOf(text) !== -1;
      });
      vm.updatePagination();
    });

    function fromDateVal() {
      vm.fromDateOptions = {
        maxDate: new Date(),
        showWeeks: false,
      };
    }

    function toDateVal(fromDate) {
      var from = fromDate ? new Date(fromDate) : null;
      vm.toDateOptions = {
        minDate: from,
        maxDate: new Date(),
        showWeeks: false,
      };
    }

    function textableDatePicker(id, valueDate) {
      commonService.textableDatePicker(id, valueDate);
    }

    function textableDatePickerTo(id, valueDate, valueDateFrom) {
      if (valueDate != null && valueDateFrom != null && valueDate < valueDateFrom) {
        Swal.fire("Warning", "To Date should be greater than or equal to From Date", "warning");
        vm.toDate = "";
        return;
      }
      commonService.textableDatePicker(id, valueDate);
    }

    function autoslashing(id) {
      commonService.autoslashing(id);
    }

    function allowOnlyNumber(event) {
      var charCode = event.which || event.keyCode;
      if (charCode < 48 || charCode > 57) {
        event.preventDefault();
      }
    }

    function formatFinancialYear() {
      vm.financialYearError = "";
      if (!vm.financialYear) {
        return;
      }
      var value = vm.financialYear.replace(/\D/g, "");
      var currentYear = new Date().getFullYear();
      if (value.length >= 4) {
        var firstYear = parseInt(value.substring(0, 4), 10);
        if (firstYear > currentYear) {
          vm.financialYearError = "Future financial year is not allowed";
          vm.financialYear = "";
          return;
        }
        vm.financialYear = firstYear + "-" + (firstYear + 1);
      } else {
        vm.financialYear = value;
      }
    }

    function getCurrentFinancialYear() {
      var d = new Date();
      var year = d.getFullYear();
      var month = d.getMonth();
      if (month >= 3) {
        return year + "-" + (year + 1);
      }
      return year - 1 + "-" + year;
    }

    function downloadCertificationLetter() {
      downloadScrollFile("CERTIFICATION");
    }

    function downloadAnnexureLetter() {
      downloadScrollFile("ANNEXURE");
    }

    function downloadPdoTallyLetter() {
      downloadScrollFile("PDO_TALLY");
    }

    function downloadPdoInterestFile() {
      downloadScrollFile("PDO_22_DIGIT");
    }

    function buildDownloadRequest(downloadType) {
      return {
        schemeCode: vm.schemeCode,
        schemeName: vm.schemeName,
        interestPaymentType: vm.interestPaymentType,
        halfYearMonth: vm.halfYearMonth,
        year: vm.year,
        fromDate: vm.fromDate || "",
        toDate: vm.toDate || "",
        financialYear: vm.financialYear,
        downloadType: downloadType,
      };
    }

    function downloadScrollFile(downloadType) {
      vm.loader = true;
      halfYearlyInterestScrollService.downloadFile(buildDownloadRequest(downloadType)).then(
        function (response) {
          vm.loader = false;
          var result = response.data && response.data.result;
          if (result && result.fileName && result.fileData) {
            saveAs(getBlobFromBase64String(result.fileData), result.fileName);
            return;
          }
          Swal.fire(
            "Warning",
            (response.data && response.data.message) || "Unable to generate download file.",
            "warning"
          );
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }
  }
})();
