(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("TransferReportController", TransferReportController);

  TransferReportController.$inject = ["$filter", "$state", "$stateParams", "$rootScope", "TransferReportService", "commonService"];

  function TransferReportController($filter, $state, $stateParams, $rootScope, TransferReportService, commonService) {
    var vm = this;

    vm.loader = false;
    vm.branchId = $rootScope.globals.currentUser.solId || "";
    vm.branchName = $rootScope.globals.currentUser.solDesc || "";
    vm.fromDatePicker = { opened: false };
    vm.effToDatePicker = { opened: false };
    vm.detailRows = [];
    vm.noDataFound = false;
    vm.formatIndianCurrency = formatIndianCurrency;
    vm.transferTypeLabels = {
      A: "Mode of Holding",
      B: "Joint Type change",
      C: "Nominee continuation",
      D: "HUF Transfer",
    };

    vm.textableDatePicker = textableDatePicker;
    vm.autoslashing = autoslashing;
    vm.fromDateVal = fromDateVal;
    vm.toDateVal = toDateVal;
    vm.getModuleName = getModuleName;
    vm.getPanNumber = getPanNumber;
    vm.getBLANumber = getBLANumber;
    vm.clearScheme = clearScheme;
    vm.clearPan = clearPan;
    vm.clearBla = clearBla;
    vm.canSubmit = canSubmit;
    vm.goToDetails = goToDetails;
    vm.isPanMandatory = isPanMandatory;
    vm.isBlaMandatory = isBlaMandatory;
    vm.isFromDateMandatory = isFromDateMandatory;
    vm.isToDateMandatory = isToDateMandatory;
    vm.showPanMandatoryBorder = showPanMandatoryBorder;
    vm.showBlaMandatoryBorder = showBlaMandatoryBorder;
    vm.showFromDateMandatoryBorder = showFromDateMandatoryBorder;
    vm.showToDateMandatoryBorder = showToDateMandatoryBorder;
    vm.canShowPanTorch = canShowPanTorch;
    vm.canShowBlaTorch = canShowBlaTorch;
    vm.isPanFieldDisabled = isPanFieldDisabled;
    vm.isBlaFieldDisabled = isBlaFieldDisabled;
    vm.sanitizePanInput = sanitizePanInput;
    vm.sanitizeBlaInput = sanitizeBlaInput;

    if ($state.current.name === "transfer_report.inquiry") {
      fromDateVal();
    }
    vm.getTransferReportDetail = getTransferReportDetail;
    vm.displayTransferType = displayTransferType;
    vm.formatDisplayDate = formatDisplayDate;
    vm.printpage = printpage;

    if ($state.current.name === "transfer_report.inquiry_details" && $stateParams.obj) {
      vm.schemeCode = $stateParams.obj.schemeCode || "";
      vm.schemeName = $stateParams.obj.schemeName || "";
      vm.transferType = $stateParams.obj.transferType || "";
      vm.panNum = $stateParams.obj.panNum || "";
      vm.blaNo = $stateParams.obj.blaNo || "";
      vm.fromDate = $stateParams.obj.fromDate || "";
      vm.toDate = $stateParams.obj.toDate || "";
      getTransferReportDetail();
    }

    function textableDatePicker(id, valueDate) {
      commonService.textableDatePicker(id, valueDate);

      if (id === "effToDateR" && valueDate && vm.fromDate) {
        var parts = valueDate.split("/");
        var toParsed = new Date(parts[2], parts[1] - 1, parts[0]);
        var fromParsed = vm.fromDate instanceof Date ? vm.fromDate : parseDdMmYyyy(vm.fromDate);
        if (fromParsed && toParsed < fromParsed) {
          vm.toDate = null;
          swal("Invalid Date!", "To Date should not be less than From Date.", "error");
          return false;
        }
      }
      return true;
    }

    function parseDdMmYyyy(value) {
      if (!value) {
        return null;
      }
      if (value instanceof Date) {
        return value;
      }
      var p = String(value).split("/");
      if (p.length !== 3) {
        return null;
      }
      return new Date(p[2], p[1] - 1, p[0]);
    }

    function autoslashing(id) {
      commonService.autoslashing(id);
    }

    function fromDateVal() {
      vm.fromDateOptions = {
        maxDate: new Date(),
        showWeeks: false,
      };
    }

    function toDateVal(fromDate) {
      vm.toDateOptions = {
        minDate: fromDate || undefined,
        maxDate: new Date(),
        showWeeks: false,
      };
    }

    function formatApiDate(value) {
      if (!value) {
        return "";
      }
      var d = value instanceof Date ? value : parseDdMmYyyy(value);
      if (!d || isNaN(d.getTime())) {
        return "";
      }
      return moment(d).format("DD/MM/YYYY");
    }

    function trimmed(value) {
      return value ? String(value).trim() : "";
    }

    var TORCH_SEARCH_MIN_LEN = 4;
    var TORCH_SEARCH_PATTERN = /^[A-Za-z0-9]+$/;

    function normalizeTorchSearchValue(value) {
      return trimmed(value).toUpperCase().replace(/[^A-Z0-9]/g, "");
    }

    function isTorchSearchInput(value) {
      var normalized = normalizeTorchSearchValue(value);
      return normalized.length >= TORCH_SEARCH_MIN_LEN && TORCH_SEARCH_PATTERN.test(normalized);
    }

    function hasDateFilterActive() {
      return !!(vm.fromDate || vm.toDate);
    }

    function isPanFieldDisabled() {
      return !!trimmed(vm.blaNo) || hasDateFilterActive();
    }

    function isBlaFieldDisabled() {
      return !!trimmed(vm.panNum) || hasDateFilterActive();
    }

    function canShowPanTorch() {
      return isTorchSearchInput(vm.panNum) && !trimmed(vm.blaNo) && !hasDateFilterActive();
    }

    function canShowBlaTorch() {
      return isTorchSearchInput(vm.blaNo) && !trimmed(vm.panNum) && !hasDateFilterActive();
    }

    function sanitizePanInput() {
      vm.panNum = normalizeTorchSearchValue(vm.panNum);
    }

    function sanitizeBlaInput() {
      vm.blaNo = normalizeTorchSearchValue(vm.blaNo);
    }

    function validateTorchSearchInput(value, fieldLabel) {
      if (!isTorchSearchInput(value)) {
        swal(
          "Validation",
          "Enter at least 4 characters (" + fieldLabel + ") using letters and/or numbers only.",
          "warning"
        );
        return null;
      }
      return normalizeTorchSearchValue(value);
    }

    function isPanMandatory() {
      return !trimmed(vm.panNum) && !trimmed(vm.blaNo) && !hasDateFilterActive();
    }

    function isBlaMandatory() {
      return !trimmed(vm.panNum) && !trimmed(vm.blaNo) && !hasDateFilterActive();
    }

    function isFromDateMandatory() {
      return !trimmed(vm.panNum) && !trimmed(vm.blaNo);
    }

    function isToDateMandatory() {
      return !trimmed(vm.panNum) && !trimmed(vm.blaNo);
    }

    function showPanMandatoryBorder() {
      return isPanMandatory() && !trimmed(vm.panNum);
    }

    function showBlaMandatoryBorder() {
      return isBlaMandatory() && !trimmed(vm.blaNo);
    }

    function showFromDateMandatoryBorder() {
      return isFromDateMandatory() && !vm.fromDate;
    }

    function showToDateMandatoryBorder() {
      return isToDateMandatory() && !vm.toDate;
    }

    function resolveSearchCriteria() {
      var pan = trimmed(vm.panNum).toUpperCase();
      var bla = trimmed(vm.blaNo).toUpperCase();

      if (pan) {
        return { panNum: pan, blaNo: "", fromDate: "", toDate: "" };
      }
      if (bla) {
        return { panNum: "", blaNo: bla, fromDate: "", toDate: "" };
      }
      return { panNum: "", blaNo: "", fromDate: vm.fromDate, toDate: vm.toDate };
    }

    function canSubmit() {
      var pan = trimmed(vm.panNum);
      var bla = trimmed(vm.blaNo);
      if (pan || bla) {
        return true;
      }
      return !!(vm.fromDate && vm.toDate);
    }

    function goToDetails() {
      var pan = trimmed(vm.panNum);
      var bla = trimmed(vm.blaNo);

      if (pan && bla) {
        swal("Validation", "Enter either PAN No or BLA No, not both.", "warning");
        return;
      }
      if (!canSubmit()) {
        swal("Validation", "Enter PAN No, BLA No, or both Transfer From Date and Transfer To Date.", "warning");
        return;
      }
      if (!pan && !bla && ((vm.fromDate && !vm.toDate) || (!vm.fromDate && vm.toDate))) {
        swal("Validation", "Both Transfer From Date and Transfer To Date are required when using date range.", "warning");
        return;
      }

      var criteria = resolveSearchCriteria();
      $state.go("transfer_report.inquiry_details", {
        obj: {
          schemeCode: vm.schemeCode || "",
          schemeName: vm.schemeName || "",
          transferType: vm.transferType || "",
          panNum: criteria.panNum,
          blaNo: criteria.blaNo,
          fromDate: criteria.fromDate,
          toDate: criteria.toDate,
        },
      });
    }

    function clearScheme() {
      vm.schemeCode = "";
      vm.schemeName = "";
      vm.schemeStatus = "";
    }

    function clearPan() {
      vm.panNum = "";
      vm.bondHolderName = "";
    }

    function clearBla() {
      vm.blaNo = "";
      vm.modeOfHolding = "";
    }

    function getModuleName() {
      vm.loader = true;
      TransferReportService.getDetails({ flag: "S" }).then(
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
    }

    function getPanNumber() {
      var pan = validateTorchSearchInput(vm.panNum, "PAN No");
      if (!pan) {
        return;
      }
      vm.loader = true;
      var requestData = {
        panNo: pan,
        flag: "P",
      };
      TransferReportService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.PanTorch = response.data.result.list;
          angular.element("#panModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function getBLANumber() {
      if (!trimmed(vm.schemeCode)) {
        swal("Validation", "Select Name of the Bond Scheme before BLA search.", "warning");
        return;
      }
      var bla = validateTorchSearchInput(vm.blaNo, "BLA No");
      if (!bla) {
        return;
      }
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode || "",
        blaNo: bla,
        flag: "B",
      };
      TransferReportService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaAccNoList = response.data.result.list;
          angular.element("#blaModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function buildDetailRows(rows) {
      return rows.map(function (row, index) {
        return {
          sNo: index + 1,
          bondSchemeName: row.bondSchemeName,
          transferType: row.transferType,
          fromBlaModeOfHolding: row.fromBlaModeOfHolding,
          fromBlaAccountNumber: row.fromBlaAccountNumber,
          transferorHolderName1: row.transferorHolderName1,
          transferorHolder1PanNo: row.transferorHolder1PanNo,
          transferorHolderName2: row.transferorHolderName2,
          transferorHolder2PanNo: row.transferorHolder2PanNo,
          transferorHolderName3: row.transferorHolderName3,
          transferorHolder3PanNo: row.transferorHolder3PanNo,
          toBlaAccountNumber: row.toBlaAccountNumber,
          toBlaModeOfHolding: row.toBlaModeOfHolding,
          transfereeHolderName1: row.transfereeHolderName1,
          transfereeHolder1PanNo: row.transfereeHolder1PanNo,
          transfereeHolderName2: row.transfereeHolderName2,
          transfereeHolder2PanNo: row.transfereeHolder2PanNo,
          transfereeHolderName3: row.transfereeHolderName3,
          transfereeHolder3PanNo: row.transfereeHolder3PanNo,
          dateOfTransfer: row.dateOfTransfer,
          remarks: row.remarks,
          transferorSubscriptionId: row.transferorSubscriptionId,
          transferredSubscriptionId: row.transferredSubscriptionId,
          cfdDate: row.cfdDate || row.transferredCfdDate,
          maturityDate: row.maturityDate || row.transferredMaturityDate,
          subscriptionAmount: row.subscriptionAmount || row.transferredSubscriptionAmount,
        };
      });
    }

    function getTransferReportDetail() {
      vm.loader = true;
      vm.noDataFound = false;
      vm.detailRows = [];

      var requestData = {
        flag: "T",
        schemeCode: vm.schemeCode || "",
        transferType: vm.transferType || "",
        panNo: vm.panNum ? String(vm.panNum).trim().toUpperCase() : "",
        blaNo: vm.blaNo ? String(vm.blaNo).trim() : "",
        fromDate: formatApiDate(vm.fromDate),
        toDate: formatApiDate(vm.toDate),
      };

      TransferReportService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          var list = (response.data.result && response.data.result.list) || [];
          vm.detailRows = buildDetailRows(list);
          vm.noDataFound = vm.detailRows.length === 0;
        },
        function (error) {
          vm.loader = false;
          vm.noDataFound = true;
          vm.detailRows = [];
          if (error.data && (error.data.message === "No Data Found" || error.data.message === "NDF")) {
            return;
          }
          handleErrorResponse(error);
        }
      );
    }

    function displayTransferType(codeOrLabel) {
      if (!codeOrLabel) {
        return "";
      }
      return vm.transferTypeLabels[codeOrLabel] || codeOrLabel;
    }

    function formatDisplayDate(value) {
      if (!value) {
        return "";
      }
      var normalized = String(value).replace(" ", "T");
      var parsed = new Date(normalized);
      if (!isNaN(parsed.getTime())) {
        return $filter("date")(parsed, "dd/MM/yyyy");
      }
      return value;
    }

    function printpage(fileName) {
      var originalTitle = document.title;
      document.title = fileName || "Transfer Report";
      window.print();
      setTimeout(function () {
        document.title = originalTitle;
      }, 1000);
    }

    function formatIndianCurrency(amount) {
      if (amount === undefined || amount === null || amount === "") {
        return "";
      }
      var num = parseFloat(String(amount).replace(/,/g, ""));
      if (isNaN(num)) {
        return amount;
      }
      return new Intl.NumberFormat("en-IN", {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2,
      }).format(num);
    }
  }
})();
