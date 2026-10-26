(function () {
  "use strict";
  angular.module("gbmUltraApp").controller("TDSExemptionSubmitController", TDSExemptionSubmitController);

  TDSExemptionSubmitController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "TDSExemptionSubmitService", "commonService"];

  function TDSExemptionSubmitController($scope, $filter, $state, $stateParams, $location, $rootScope, TDSExemptionSubmitService, commonService) {
    var vm = this;
    vm.solDate = $filter("date")($rootScope.globals.currentUser.systemDate, "dd/MM/yyyy");
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

    vm.getPanNumber = function () {
      vm.loader = true;
      var requestData = {
        panNumber: vm.panNumber,
      };
      TDSExemptionSubmitService.getDetails(requestData).then(
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
    };

    vm.getDetails = function () {
      vm.loader = true;
      var requestData = {
        panNumber: vm.panNumber,
        status: vm.status,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),

        pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchText,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
      };

      TDSExemptionSubmitService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.TDSExemptionReportList = response.data.result.list;
          vm.filteredRecords = response.data.result.list;
          vm.total = response.data.total;
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
          if (vm.searchText && (error.data.message == "No Data Found" || error.data.message == "NDF")) {
            vm.noDataFoundInSearch = true;
            vm.total= error.data.total;
            vm.pageNo= 0;
            vm.maxPages = Math.ceil(error.data.total / vm.pageSize);
            vm.paginationControls();
            vm.showPrevious= false;
          } else if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("TDS_Exemption_Submitted_Non_Submitted.inquiry_details");
          }
        }
      );
    };

    vm.getStatusDescription = function (status) {
      const descriptions = {
        S: "Submitted List",
        NS: "Non-Submitted List",
        A: "All",
      };
      return descriptions[status] || "";
    };

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

    if ($state.current.name == "TDS_Exemption_Submitted_Non_Submitted.inquiry_details") {
      vm.status = $stateParams.obj.status;
      vm.statusDescription = $stateParams.obj.statusDescription;
      vm.panNumber = $stateParams.obj.panNumber;
      vm.fromDate = $stateParams.obj.fromDate;
      vm.toDate = $stateParams.obj.toDate;
        vm.getDetails();
    }

    // Downloading the report
    vm.downloadReport = function () {
      vm.loader = true;
      var requestData = {
        panNumber: vm.panNumber,
        status: vm.status,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
                pageNo: 1,
                pageSize: vm.total,
                searchValue: vm.searchText,
      };

      TDSExemptionSubmitService.getDetailsDownload(requestData).then(
        function (response) {
          vm.loader = false;
          vm.filteredRecordsDownload = response.data.result.list;
          if (vm.downloadType === "excel") {
//            vm.downloadExcel();
            if (response.data.result.fileName) {
                 vm.fileData = response.data.result.fileData;
                 vm.fileName = response.data.result.fileName;
                 saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
                 Swal.fire("Downloaded", response.data.message, "success");
            }
          } else if (vm.downloadType === "csv") {
            vm.downloadCSV();
          } else if (vm.downloadType === "pdf") {
            vm.downloadPDF();
          }
          vm.downloadType = "";
        },
        function (error) {
          vm.loader = false;
          if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("TDS_Exemption_Submitted_Non_Submitted.inquiry_details");
          }
        }
      );
    };

    vm.downloadCSV = function () {
      const tableHeader = [
        { title: "S. No.", width: 10, type: "numeric" },
        { title: "Customer Name", width: 20, type: "string" },
        { title: "PAN No.", width: 15, type: "string" },
        { title: "Type of Exemption", width: 20, type: "string" },
        { title: "Certificate No / UIN No", width: 25, type: "string" },
        { title: "TDS Limit", width: 20, type: "numeric" },
        { title: "Low Tax Percentage", width: 20, type: "string" },
        { title: "Valid From Date", width: 15, type: "string" },
        { title: "Valid To Date  ", width: 15, type: "string" },
        { title: "Financial Year", width: 15, type: "string" },
        { title: "Certificate Submission Status", width: 15, type: "string" },
      ];

      const formatRow = (values) => {
        return tableHeader
          .map((col, i) => {
            const value = values[i] ? values[i].toString() : "";
            const alignment = col.type === "numeric" ? "right" : "left";
            if (alignment === "right") {
              return value.padStart(col.width, " ");
            } else {
              return value.padEnd(col.width, " ");
            }
          })
          .join("\t");
      };

      const formattedHeader = formatRow(tableHeader.map((col) => col.title));

      const tableData = vm.filteredRecordsDownload.map((record, index) =>
        formatRow([
          (index + 1).toString(),
          record.customerName || "",
          record.panNumber || "",
          record.typeOfExemption || "",
          record.certificateNo || "",
          record.tdsLimit || "",
          record.lowTaxPercentage || "",
          record.validFromDate || "",
          record.validToDate || "",
          record.financialYear || "",
          record.certificateSubStatus || "",
        ])
      );
      const textData = [
        "TDS Exemption Submitted / Non-Submitted Report",
        "Bank Name: INSPIRISY BANK LTD",
        "Sol ID: 101 / BRANCH OF THE BANK",
        "From Date " + $filter("date")(vm.fromDate, "dd/MM/yyyy"),
        " To Date " + $filter("date")(vm.toDate, "dd/MM/yyyy"),
        "",
        formattedHeader,
        ...tableData,
      ].join("\n");

      const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData);
      const link = document.createElement("a");
      link.setAttribute("href", textContent);
      link.setAttribute("download", "TDS Exemption Submitted / Non-Submitted Report.txt");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    };

    vm.downloadExcel = function () {
      const additionalDetails = [
        ["TDS Exemption Submitted / Non-Submitted Report"],
        ["Bank Name: INSPIRISY BANK LTD"],
        ["Sol ID: 101 / BRANCH OF THE BANK"],
        ["From Date " + $filter("date")(vm.fromDate, "dd/MM/yyyy")],
        ["To Date " + $filter("date")(vm.toDate, "dd/MM/yyyy")],
        [],
      ];
      const tableHeaders = [["S. No.", "Customer Name", "PAN No.", "Type of Exemption", "Certificate No / UIN No", "TDS Limit", "Low Tax Percentage", "Valid From Date", "Valid To Date", "Financial Year", "Certificate Submission Status"]];
      const tableData = vm.filteredRecordsDownload.map((record, index) => [
        index + 1,
        record.customerName || "",
        record.panNumber || "",
        record.typeOfExemption || "",
        record.certificateNo || "",
        record.tdsLimit || "",
        record.lowTaxPercentage || "",
        record.validFromDate || "",
        record.validToDate || "",
        record.financialYear || "",
        record.certificateSubStatus || "",
      ]);
      const excelData = [...additionalDetails, ...tableHeaders, ...tableData];
      const ws = XLSX.utils.aoa_to_sheet(excelData);
      const mergeRanges = [
        { s: { r: 0, c: 0 }, e: { r: 0, c: 10 } },
        { s: { r: 1, c: 0 }, e: { r: 1, c: 10 } },
        { s: { r: 2, c: 0 }, e: { r: 2, c: 10 } },
        { s: { r: 3, c: 0 }, e: { r: 3, c: 10 } },
        { s: { r: 4, c: 0 }, e: { r: 4, c: 10 } },
      ];
      ws["!merges"] = mergeRanges;
      mergeRanges.forEach((merge) => {
        const cell = ws[XLSX.utils.encode_cell(merge.s)];
        if (cell) {
          cell.s = { alignment: { horizontal: "center", vertical: "center" } };
        }
      });
      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb, ws, "Data");
      XLSX.writeFile(wb, "TDS Exemption Submitted / Non-Submitted Report.xlsx");
    };

    vm.downloadPDF = function () {
      const { jsPDF } = window.jspdf;
      const doc = new jsPDF({ orientation: "landscape", unit: "mm", format: "a4" });

      doc.setFont("Times", "bold");

      const tableHeader = ["S. No.", "Customer Name", "PAN No.", "Type of Exemption", "Certificate No / UIN No", "TDS Limit", "Low Tax Percentage", "Valid From Date", "Valid To Date", "Financial Year", "Certificate Submission Status"];

      const tableData = vm.filteredRecordsDownload.map((record, index) => [
        index + 1,
        record.customerName || "",
        record.panNumber || "",
        record.typeOfExemption || "",
        record.certificateNo || "",
        record.tdsLimit || "",
        record.lowTaxPercentage || "",
        record.validFromDate || "",
        record.validToDate || "",
        record.financialYear || "",
        record.certificateSubStatus || "",
      ]);

      const additionalHeaders = [
        { text: "TDS Exemption Submitted / Non-Submitted Report", fontSize: 14, yPosition: 10 },
        { text: "Bank Name : INSPIRISY BANK LTD", fontSize: 12, yPosition: 17 },
        { text: "Sol ID : 101 / BRANCH OF THE BANK", fontSize: 12, yPosition: 24 },
      ];

      additionalHeaders.forEach((header) => {
        const pageWidth = doc.internal.pageSize.width;
        doc.setFontSize(header.fontSize);
        doc.text(header.text, pageWidth / 2, header.yPosition, { align: "center" });
      });

      const headerTableData = [
        ["From Date", $filter("date")(vm.fromDate, "dd/MM/yyyy")],
        ["To Date", $filter("date")(vm.toDate, "dd/MM/yyyy")],
      ];

      doc.autoTable({
        body: headerTableData,
        startY: 30,
        margin: { left: 10, right: 10 },
        theme: "grid",
        styles: { fontSize: 10, overflow: "linebreak", cellPadding: 2 },
        tableWidth: "auto",
        columnStyles: {
          0: { fontStyle: "bold" },
          1: {},
          2: { fontStyle: "bold" },
          3: {},
        },
      });

      const columnWidths = {
        0: { cellWidth: 15 },
        1: { cellWidth: 35 },
        2: { cellWidth: 30 },
        3: { cellWidth: 35 },
        4: { cellWidth: 30 },
        5: { cellWidth: 20 },
        6: { cellWidth: 15 },
        7: { cellWidth: 25 },
        8: { cellWidth: 25 },
        9: { cellWidth: 25 },
        9: { cellWidth: 25 },
      };

      const afterFirstTableY = doc.lastAutoTable.finalY + 10;
      doc.autoTable({
        head: [tableHeader],
        body: tableData,
        startY: afterFirstTableY,
        margin: { left: 10, right: 10 },
        styles: { fontSize: 10, overflow: "linebreak", cellPadding: 2 },
        columnStyles: columnWidths,
        pageBreak: "auto",
        theme: "grid",
        rowPageBreak: "avoid",
        showHead: "firstPage",
      });
      doc.save("TDS Exemption Submitted / Non-Submitted Report.pdf");
    };
  }
})();
