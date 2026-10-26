(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("ReportOnUnverifiedController", ReportOnUnverifiedController);

  ReportOnUnverifiedController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "ReportOnUnverifiedService", "commonService"];

  function ReportOnUnverifiedController($scope, $filter, $state, $stateParams, $location, $rootScope, ReportOnUnverifiedService, commonService) {
    var vm = this;
    vm.solDate = $rootScope.globals.currentUser.solDate;
    vm.systemDate = formatDate(new Date());

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

    function formatDate(tempDate) {
      return $filter("date")(tempDate, "dd/MM/yyyy");
    }

    vm.getSchemeName = function () {
      vm.loader = true;
      var requestData = {
        flag: "S",
      };

      ReportOnUnverifiedService.getDetails(requestData).then(
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

    vm.getDetails = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        userID: vm.userID,
        flag: "D",
        pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchText,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
      };

      ReportOnUnverifiedService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.model = response.data.result.list;
          vm.filteredRecords = response.data.result.list;
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
            $state.go("Report_On_Unverified_Records_DC.inquiry");
          }
        }
      );
    };

    if ($state.current.name == "Report_On_Unverified_Records_DC.inquiry_details") {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.systemDate = $stateParams.obj.systemDate;
      vm.userID = $stateParams.obj.userID;
      vm.getDetails();
    }

    // Downloading the report
    vm.reports = {
      ReportOnUnverifiedRecords: {
        filename: "Report on Un-verified Records - DC",
        mainHeaders: ["Report on Un-verified Records - DC", "Bank Name: INSPIRISY BANK LTD", "Sol ID: 101 / BRANCH OF THE BANK"],
        subHeaders: ["Name of the Bond Scheme: " + vm.schemeName],
        tableHeaders: [
          { title: "S. No.", width: 20, type: "numeric" },
          { title: "Bond BLA No", width: 20, type: "string" },
          { title: "Subscription ID", width: 20, type: "string" },
          { title: "Bond Type", width: 20, type: "string" },
          { title: "Bond Holder Name", width: 20, type: "string" },
          { title: "Screen Name", width: 20, type: "string" },
          { title: "Date of Creation/Modification", width: 20, type: "string" },
          { title: "Created/ Modified By", width: 20, type: "string" },
        ],
        get tableData() {
          return (vm.filteredRecordsDownload || []).map((record, index) => [
            (index + 1).toString(),
            record.blaNumber || "",
            record.subscriptionID || "",
            record.bondType || "",
            record.holderName || "",
            record.screenName || "",
            record.dateOfCreation || "",
            record.createdModifiedBy || "",
          ]);
        },
      },
    };

    vm.downloadReport = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        userID: vm.userID,
        flag: "D",
        pageNo: "1",
                pageSize: vm.total,
                searchValue: vm.searchText,
      };

      ReportOnUnverifiedService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;

          vm.filteredRecordsDownload = response.data.result.list;
          const currentReport = vm.reports["ReportOnUnverifiedRecords"];
          if (vm.downloadType === "excel") {
            vm.downloadExcel(currentReport);
          } else if (vm.downloadType === "csv") {
            vm.downloadCSV(currentReport);
          } else if (vm.downloadType === "pdf") {
            vm.downloadPDF(currentReport);
          }
          vm.downloadType = "";
        },
        function (error) {
          vm.loader = false;
          if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("Report_On_Unverified_Records_DC.inquiry");
          }
        }
      );
    };

    vm.downloadCSV = function (currentReport) {
      const { filename, mainHeaders, subHeaders, tableHeaders, tableData } = currentReport;
      const columnWidths = tableHeaders.map((col, index) => {
        const headerLength = col.title.length;
        const maxDataLength = Math.max(headerLength, ...tableData.map((row) => (row[index] ? row[index].toString().length : 0)));
        return Math.max(col.width || 0, maxDataLength);
      });
      const formatRow = (values) => {
        return values
          .map((value, i) => {
            const col = tableHeaders[i];
            const width = columnWidths[i];
            const alignment = col.type === "numeric" ? "right" : "left";
            const stringValue = value ? value.toString() : "";

            if (alignment === "right") {
              return stringValue.padStart(width, " ");
            } else {
              return stringValue.padEnd(width, " ");
            }
          })
          .join(" | ");
      };
      const formattedHeader = formatRow(tableHeaders.map((col) => col.title));
      const formattedData = tableData.map((row) => formatRow(row));
      const textData = [mainHeaders.join("\n"), subHeaders.join("\n"), "-".repeat(formattedHeader.length), formattedHeader, "-".repeat(formattedHeader.length), ...formattedData].join("\n");
      const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData);
      const link = document.createElement("a");
      link.setAttribute("href", textContent);
      link.setAttribute("download", filename + ".txt");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    };

    vm.downloadExcel = function (currentReport) {
      const { filename, mainHeaders, subHeaders, tableHeaders, tableData } = currentReport;
      const excelData = [];

      mainHeaders.forEach((header) => {
        excelData.push([header, ...Array(tableHeaders.length - 1).fill("")]);
      });
      subHeaders.forEach((header) => {
        excelData.push([header, ...Array(tableHeaders.length - 1).fill("")]);
      });
      excelData.push([]);
      excelData.push(tableHeaders.map((header) => header.title));
      tableData.forEach((record) => {
        excelData.push(Object.values(record));
      });

      const ws = XLSX.utils.aoa_to_sheet(excelData);
      const mergeRanges = [
        { s: { r: 0, c: 0 }, e: { r: 0, c: 7 } },
        { s: { r: 1, c: 0 }, e: { r: 1, c: 7 } },
        { s: { r: 2, c: 0 }, e: { r: 2, c: 7 } },
        { s: { r: 3, c: 0 }, e: { r: 3, c: 7 } },
      ];
      ws["!merges"] = mergeRanges;
      mergeRanges.forEach((merge) => {
        const cell = ws[XLSX.utils.encode_cell(merge.s)];
        if (cell) {
          cell.s = { alignment: { horizontal: "center", vertical: "center" }, font: { bold: true } };
        }
      });
      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb, ws, "Data");
      XLSX.writeFile(wb, filename + ".xlsx");
    };

    vm.downloadPDF = function (currentReport) {
      const { filename, mainHeaders, subHeaders, tableHeaders, tableData } = currentReport;
      const { jsPDF } = window.jspdf;
      const doc = new jsPDF({ orientation: "landscape", unit: "mm", format: "a4" });

      doc.setFont("Times", "bold");
      if (Array.isArray(mainHeaders)) {
        let yPosition = 10;

        mainHeaders.forEach((header) => {
          if (header) {
            const pageWidth = doc.internal.pageSize.width;
            doc.setFontSize(13);
            doc.text(header, pageWidth / 2, yPosition, { align: "center" });
            yPosition += 10;
          } else {
            console.error("Invalid header text:", header);
          }
        });
      }
      const formattedSubHeaders = subHeaders.map((header) => [header]);

      doc.autoTable({
        body: formattedSubHeaders,
        startY: 40,
        margin: { left: 20, right: 20 },
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

      const afterFirstTableY = doc.lastAutoTable.finalY + 10;
      doc.autoTable({
        head: [tableHeaders],
        body: tableData,
        startY: afterFirstTableY,
        theme: "grid",
        margin: { left: 20, right: 20 },
        styles: { fontSize: 10, overflow: "linebreak", cellPadding: 2 },
        pageBreak: "auto",
        tableWidth: "auto",
        rowPageBreak: "avoid",
        showHead: "firstPage",
      });

      doc.save(filename + ".pdf");
    };
  }
})();
