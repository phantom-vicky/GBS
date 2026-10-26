(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("BondNominationRegisterController", BondNominationRegisterController);

  BondNominationRegisterController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "BondNominationRegisterService", "commonService"];

  function BondNominationRegisterController($scope, $filter, $state, $stateParams, $location, $rootScope, BondNominationRegisterService, commonService) {
    var vm = this;
    vm.getDetails = getDetails;

    // Pagination
    vm.filteredRecords = [];
    vm.currentPage = 1;
    vm.maxPagesToShow = 5;
    vm.searchValue = "";
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

      BondNominationRegisterService.getDetails(requestData).then(
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

    vm.getPanNumber = function () {
      var requestData = {
        schemeCode: vm.schemeCode,
        holderName: vm.holderName,
        panNumber: vm.panNumber,
        flag: "P",
      };
      BondNominationRegisterService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.panNumberList = response.data.result.list;
          angular.element("#panModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    };

    vm.getBLANumber = function () {
      var requestData = {
        schemeCode: vm.schemeCode,
        holderName: vm.holderName,
        panNumber: vm.panNumber,
        blaNumber: vm.blaNumber,
        flag: "B",
      };
      BondNominationRegisterService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaNumberList = response.data.result.list;
          angular.element("#blaModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    };

    vm.getSubscriptionID = function () {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        holderName: vm.holderName,
        panNumber: vm.panNumber,
        blaNumber: vm.blaNumber,
        flag: "BR",
        subscriptionID: vm.subscriptionID,
      };
      BondNominationRegisterService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.subscriptionIDList = response.data.result.list;
          angular.element("#subscriptionIDModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    };

    function getDetails() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        holderName: vm.holderName,
        panNumber: vm.panNumber,
        blaNumber: vm.blaNumber,
        subscriptionID: vm.subscriptionID,
        fromDate:$filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "D",
        pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchValue,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
      };

      BondNominationRegisterService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.model = response.data.result.list;
          vm.totalAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.amount ? parseFloat(record.amount) : 0), 0);



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
          if ((error.data.message == "No Data Found" || error.data.message == "NDF") && vm.searchValue) {
            vm.total = '0';
            vm.noDataFoundInSearch = true;
          } else if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("Bond_Nomination_Register.inquiry");
          }
        }
      );
    }

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

    if ($state.current.name == "Bond_Nomination_Register.inquiry_details") {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.fromDate = $stateParams.obj.fromDate;
      vm.toDate = $stateParams.obj.toDate;
      vm.subscriptionID = $stateParams.obj.subscriptionID;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.panNumber = $stateParams.obj.panNumber;
      vm.holderName = $stateParams.obj.holderName;
      vm.blaNumber = $stateParams.obj.blaNumber;
        vm.getDetails();
    }

    vm.downloadReport = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        holderName: vm.holderName,
        panNumber: vm.panNumber,
        blaNumber: vm.blaNumber,
        subscriptionID: vm.subscriptionID,
        fromDate:$filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "D",
        pageNo: "1",
        pageSize: vm.total,
        searchValue: vm.searchValue,
        downloadType :  vm.downloadType,
      };
      BondNominationRegisterService.getBondNominationDownload(requestData).then(
        function (response) {
          vm.loader = false;
          vm.downloadFilteredRecords = response.data.result.list;
          const currentReport = vm.reports["BondNominationRegister"];
          if (vm.downloadType === "excel") {
//            vm.downloadExcel(currentReport);
            if (response.data.result.fileName) {
                 vm.fileData = response.data.result.fileData;
                 vm.fileName = response.data.result.fileName;
                 saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
            }
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
            $state.go("Bond_Nomination_Register.inquiry");
          }
        }
      );
    };

    // Downloading the report
    vm.reports = {
      BondNominationRegister: {
        filename: "Bond Nomination Register",
        mainHeaders: ["Bond Nomination Register", "Bank Name: INSPIRISY BANK LTD", "Sol ID: 101 / BRANCH OF THE BANK"],
        subHeaders: ["Name of the Bond Scheme: " + vm.schemeName, vm.holderName ? "Name: " + vm.holderName : "", vm.panNumber ? "PAN No: " + vm.panNumber : "", "BLA No: " + vm.blaNumber, "Subscription ID: " + vm.subscriptionID].filter(Boolean),
        tableHeaders: [
          { title: "S. No.", width: 10, type: "numeric" },
          { title: "PARTICULARS OF INVESTMENT", width: 30, type: "numeric" },
          { title: "", width: 30, type: "string" },
          { title: "PARTICULARS OF NOMINEE", width: 35, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
          { title: "", width: 30, type: "string" },
        ],
        tableSubHeaders: [
          { title: "", width: 30, type: "string" },
          { title: "Date of Issue", width: 30, type: "string" },
          { title: "Amount (Rs.)", width: 30, type: "string" },
          { title: "Date of Repayment", width: 30, type: "string" },
          { title: "Nominee Name", width: 30, type: "string" },
          { title: "Nominee Address", width: 30, type: "string" },
          { title: "Date of Birth", width: 30, type: "string" },
          { title: "Relationship to holder", width: 30, type: "string" },
          { title: "STATUS Resident/NRI", width: 30, type: "string" },
          { title: "Particulars of Bank Account", width: 30, type: "string" },
        ],
        get tableData() {
          return (vm.downloadFilteredRecords || []).map((record, index) => [
            (index + 1).toString(),
            record.dateOfIssue || "",
            record.amount || "",
            record.dateOfRepayment || "",
            record.nomineeName || "",
            record.nomineeAddress || "",
            record.dob || "",
            record.relationshipHolder || "",
            record.status || "",
            record.particularsOfBankAccount || "",
          ]);
        },
      },
    };

    vm.downloadCSV = function (currentReport) {
      const { filename, mainHeaders, subHeaders, tableHeaders, tableSubHeaders, tableData } = currentReport;
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
          .join(" | "); // Use " | " as a delimiter
      };
      const formattedHeader = formatRow(tableHeaders.map((col) => col.title));
      const formattedSubHeaders = formatRow(tableSubHeaders.map((col) => col.title));
      const formattedData = tableData.map((row) => formatRow(row)).join("\n");

      const textData = [
        mainHeaders.join("\n"),
        subHeaders.join("\n"),
        "-".repeat(formattedHeader.length),
        formattedHeader,
        "-".repeat(formattedHeader.length),
        formattedSubHeaders,
        "-".repeat(formattedHeader.length),
        formattedData,
        "-".repeat(formattedHeader.length),
      ].join("\n");

      const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData);
      const link = document.createElement("a");
      link.setAttribute("href", textContent);
      link.setAttribute("download", filename + ".txt");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    };

    vm.downloadExcel = function (currentReport) {
      const { filename, mainHeaders, subHeaders, tableHeaders, tableSubHeaders, tableData } = currentReport;
      const excelData = [];

      mainHeaders.forEach((header) => {
        excelData.push([header, ...Array(tableHeaders.length - 1).fill("")]);
      });

      subHeaders.forEach((header) => {
        excelData.push([header, ...Array(tableHeaders.length - 1).fill("")]);
      });
      excelData.push([]);
      excelData.push(tableHeaders.map((header) => header.title));
      excelData.push(tableSubHeaders.map((header) => header.title));
      tableData.forEach((record) => {
        excelData.push(Object.values(record));
      });

      const ws = XLSX.utils.aoa_to_sheet(excelData);
      const mergeRanges = [
        { s: { r: 0, c: 0 }, e: { r: 0, c: tableHeaders.length - 1 } },
        { s: { r: 1, c: 0 }, e: { r: 1, c: tableHeaders.length - 1 } },
        { s: { r: 2, c: 0 }, e: { r: 2, c: tableHeaders.length - 1 } },
        { s: { r: 3, c: 0 }, e: { r: 3, c: tableHeaders.length - 1 } },
        { s: { r: 4, c: 0 }, e: { r: 4, c: tableHeaders.length - 1 } },
        { s: { r: 5, c: 0 }, e: { r: 5, c: tableHeaders.length - 1 } },
        { s: { r: 6, c: 0 }, e: { r: 6, c: tableHeaders.length - 1 } },
        { s: { r: 7, c: 0 }, e: { r: 7, c: tableHeaders.length - 1 } },
      ];
      ws["!merges"] = mergeRanges;
      mergeRanges.forEach((merge) => {
        const cell = ws[XLSX.utils.encode_cell(merge.s)];
        if (cell) {
          cell.s = { alignment: { horizontal: "center", vertical: "center" }, font: { bold: true } };
        }
      });
      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb, ws, "Bond Nomination Register");
      XLSX.writeFile(wb, filename + ".xlsx");
    };

    vm.downloadPDF = function (currentReport) {
      const { filename, mainHeaders, subHeaders, tableHeaders, tableSubHeaders, tableData } = currentReport;
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
        head: [tableHeaders, tableSubHeaders],
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


        vm.textableDatePicker = textableDatePicker;
        vm.autoslashing = autoslashing;

        function textableDatePicker(id, valueDate) {
          commonService.textableDatePicker(id, valueDate);
        }
        function autoslashing(id) {
          commonService.autoslashing(id);
        }
    // Date validation

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

  }
})();