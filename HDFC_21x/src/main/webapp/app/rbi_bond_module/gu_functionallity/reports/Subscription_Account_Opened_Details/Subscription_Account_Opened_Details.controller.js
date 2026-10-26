(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("SubscriptionAccountController", SubscriptionAccountController);
  SubscriptionAccountController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "SubscriptionAccountService", "commonService"];

  function SubscriptionAccountController($scope, $filter, $state, $stateParams, $location, $rootScope, SubscriptionAccountService, commonService) {
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

    if ($state.current.name == "rbiBond_bond_inquiry_subs_acc_det_report.inquiry_detail") {
      vm.title = "Subscription Account Opened Details";
      if ($stateParams.obj) {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.fromDate = $stateParams.obj.fromDate;
        vm.toDate = $stateParams.obj.toDate;
            vm.getDetails();
      }
    }

    function getDetails() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: $rootScope.formatDateToDDMMMYYYY(vm.fromDate),
        toDate: $rootScope.formatDateToDDMMMYYYY(vm.toDate),
        pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchText,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
      };

      SubscriptionAccountService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.subsAccDetailList = response.data.result.list;
          vm.filteredRecords = response.data.result.list;
          vm.totalSubscriptionAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.subsAmount ? parseFloat(record.subsAmount) : 0), 0);
          vm.total = response.data.total;
          vm.maxPages = Math.ceil(response.data.total / vm.pageSize);
          vm.paginationControls();
          if (vm.subsAccDetailList.length === 0) {
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
            $state.go("rbiBond_bond_inquiry_subs_acc_det_report.inquiry");
          }
        }
      );
    }

    vm.getSchemeName = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: "",
      };
      SubscriptionAccountService.getDetails(requestData).then(
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

    // Date Validation
    vm.fromDateVal = function () {
      vm.fromDateOptions = {
        maxDate: new Date(),
        showWeeks: false,
      };
    };

    vm.toDateVal = function (fromDate) {
      const solDateStr = $rootScope.globals.currentUser.solDate;
      if (!solDateStr) {
        console.error("solDate is missing or undefined.");
        return;
      }

      const dateParts = solDateStr.split("/");
      if (dateParts.length !== 3) {
        console.error("Invalid solDate format:", solDateStr);
        return;
      }

      vm.solDate = new Date(`${dateParts[2]}-${dateParts[1]}-${dateParts[0]}`);
      if (isNaN(vm.solDate)) {
        console.error("Invalid solDate format:", solDateStr);
        return;
      }

      function formatDate(date) {
        const day = ("0" + date.getDate()).slice(-2);
        const month = ("0" + (date.getMonth() + 1)).slice(-2);
        const year = date.getFullYear();
        return `${day}/${month}/${year}`;
      }

      const maxDateLimit = new Date(vm.solDate);
      maxDateLimit.setDate(maxDateLimit.getDate() + 30); // Add 30 days to solDate

      vm.toDateOptions = {
        minDate: fromDate,
        maxDate: maxDateLimit > new Date() ? maxDateLimit : new Date(),
        showWeeks: false,
      };
    };

    // Export Options
    vm.downloadReport = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: $rootScope.formatDateToDDMMMYYYY(vm.fromDate),
        toDate: $rootScope.formatDateToDDMMMYYYY(vm.toDate),
        pageNo: 1,
        pageSize: vm.total,
        searchValue: vm.searchText,
      };

      SubscriptionAccountService.getDetailsDownload(requestData).then(
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
            $state.go("rbiBond_bond_inquiry_subs_acc_det_report.inquiry");
          }
        }
      );
    };

    vm.downloadCSV = function () {
      const tableHeader = [
        { title: "S. No.", width: 10, type: "numeric" },
        { title: "Mode of Investment", width: 20, type: "string" },
        { title: "BLA No.", width: 25, type: "string" },
        { title: "Holder Name", width: 40, type: "string" },
        { title: "Holder PAN No", width: 20, type: "string" },
        { title: "Subscription ID", width: 20, type: "string" },
        { title: "Date of Subscription", width: 20, type: "string" },
        { title: "Maturity Date", width: 20, type: "string" },
        { title: "Mode of Holding", width: 30, type: "string" },
        { title: "Subscription Amount Rs", width: 20, type: "numeric" },
        { title: "Subscription Status", width: 20, type: "string" },
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
          record.modeOfInvestment || "",
          record.blaNo || "",
          record.holderName || "",
          record.holderPanNo || "",
          record.subsId || "",
          record.dateOfSubs || "",
          record.maturityDate || "",
          record.modeOfHolding || "",
          record.subsAmount ? record.subsAmount : "",
          record.subsStatus || "",
        ])
      );
      const totalRow = formatRow(
        tableHeader.map((col, i) => {
          if (i === tableHeader.length - 2) return "Total " + vm.totalSubscriptionAmt;
          return "";
        })
      );
      const textData = [
        "Subscription Account Opened Details",
        "Bank Name: INSPIRISY BANK LTD",
        "Sol ID: 101 / BRANCH OF THE BANK",
        "Name of the Bond Scheme: " + vm.schemeName,
        "Account Opened Date from " + $filter("date")(vm.fromDate, "dd/MM/yyyy") + " to " + $filter("date")(vm.toDate, "dd/MM/yyyy"),
        "",
        formattedHeader,
        ...tableData,
        totalRow,
      ].join("\n");
      const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData);
      const link = document.createElement("a");
      link.setAttribute("href", textContent);
      link.setAttribute("download", "Subscription_Account_Opened_Details.txt");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    };

    vm.downloadExcel = function () {
      const additionalDetails = [
        ["Subscription Account Opened Details"],
        ["Bank Name: INSPIRISY BANK LTD"],
        ["Sol ID: 101 / BRANCH OF THE BANK"],
        ["Name of the Bond Scheme: " + vm.schemeName],
        ["Account Opened Date from " + $filter("date")(vm.fromDate, "dd/MM/yyyy") + " Account Opened Date to " + $filter("date")(vm.toDate, "dd/MM/yyyy")],
        [],
      ];
      const tableHeaders = [["S. No.", "Mode of Investment", "BLA No.", "Holder Name", "Holder PAN No", "Subscription ID", "Date of Subscription", "Maturity Date", "Mode of Holding", "Subscription Amount Rs", "Subscription Status"]];
      const tableData = vm.filteredRecordsDownload.map((record, index) => [
        index + 1,
        record.modeOfInvestment,
        record.blaNo,
        record.holderName,
        record.holderPanNo,
        record.subsId,
        record.dateOfSubs,
        record.maturityDate,
        record.modeOfHolding,
        record.subsAmount,
        record.subsStatus,
      ]);
      const totalRow = ["", "", "", "", "", "", "", "", "Total", vm.totalSubscriptionAmt, ""];

      const excelData = [...additionalDetails, ...tableHeaders, ...tableData, totalRow];
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
          cell.s = { alignment: { horizontal: "center", vertical: "center" }, font: { bold: true } };
        }
      });
      const subscriptionAmountColumnIndex = 9;
      for (let i = additionalDetails.length + tableHeaders.length; i < excelData.length; i++) {
        const cellAddress = XLSX.utils.encode_cell({ r: i, c: subscriptionAmountColumnIndex });
        const cell = ws[cellAddress];
        if (cell) {
          cell.s = { alignment: { horizontal: "right" } };
        }
      }
      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb, ws, "Data");
      XLSX.writeFile(wb, "Subscription Account Opened Details.xlsx");
    };

    vm.downloadPDF = function () {
      const { jsPDF } = window.jspdf;
      const doc = new jsPDF({ orientation: "landscape", unit: "mm", format: "a4" });

      doc.setFont("Times", "bold");

      const tableHeader = ["S. No.", "Mode of Investment", "BLA No.", "Holder Name", "Holder PAN No", "Subscription ID", "Date of Subscription", "Maturity Date", "Mode of Holding", "Subscription Amount Rs", "Subscription Status"];
      const tableData = vm.filteredRecordsDownload.map((record, index) => [
        index + 1,
        record.modeOfInvestment,
        record.blaNo,
        record.holderName,
        record.holderPanNo,
        record.subsId,
        record.dateOfSubs,
        record.maturityDate,
        record.modeOfHolding,
        record.subsAmount,
        record.subsStatus,
      ]);

      tableData.push(["", "", "", "", "", "", "", "", "Total", vm.totalSubscriptionAmt]);
      const additionalHeaders = [
        { text: "Subscription Account Opened Details", fontSize: 13, yPosition: 10 },
        { text: "Bank Name : INSPIRISY BANK LTD", fontSize: 13, yPosition: 17 },
        { text: "Sol ID : 101 / BRANCH OF THE BANK", fontSize: 13, yPosition: 24 },
      ];

      additionalHeaders.forEach((header) => {
        const pageWidth = doc.internal.pageSize.width;
        doc.setFontSize(header.fontSize);
        doc.text(header.text, pageWidth / 2, header.yPosition, { align: "center" });
      });
      const headerTableData = [
        ["Name of the Bond Scheme", vm.schemeName, "", ""],
        ["Account Opened Date from", $filter("date")(vm.fromDate, "dd/MM/yyyy"), "Account Opened Date to", $filter("date")(vm.toDate, "dd/MM/yyyy")],
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
        0: { cellWidth: 10 }, // S. No.
        1: { cellWidth: 20 }, // Mode of Investment
        2: { cellWidth: 30 }, // BLA No.
        3: { cellWidth: 30 }, // Holder Name
        4: { cellWidth: 25 }, // Holder PAN No
        5: { cellWidth: 25 }, // Subscription ID
        6: { cellWidth: 20 }, // Date of Subscription
        7: { cellWidth: 20 }, // Maturity Date
        8: { cellWidth: 25 }, // Mode of Holding
        9: { cellWidth: 20 }, // Subscription Amount
        10: { cellWidth: 25 }, // Subscription Status
      };

      const afterFirstTableY = doc.lastAutoTable.finalY + 10;
      doc.autoTable({
        head: [tableHeader],
        body: tableData,
        startY: afterFirstTableY,
        margin: { left: 10, right: 10 },
        styles: { fontSize: 10, overflow: "linebreak", cellPadding: 2 },
        tableWidth: "auto",
        //        columnStyles: columnWidths,
        pageBreak: "auto",
        theme: "grid",
        rowPageBreak: "avoid",
        showHead: "firstPage",
      });
      doc.save("Subscription Account Opened Details.pdf");
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
