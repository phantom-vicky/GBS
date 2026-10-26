(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("SubsAmtBLAMaturedAccClosedController", SubsAmtBLAMaturedAccClosedController);
  SubsAmtBLAMaturedAccClosedController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "SubsAmtBLAMaturedAccClosedService", "commonService"];

  function SubsAmtBLAMaturedAccClosedController($scope, $filter, $state, $stateParams, $location, $rootScope, SubsAmtBLAMaturedAccClosedService, commonService) {
    var vm = this;
    vm.isInvalidFromAmount = false;
    vm.isInvalidToAmount = false;
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
      vm.getDetails(vm.type);
    };

    vm.pageNo = 1;
    vm.pageSize = 5;

    vm.goPreviousPage = function () {
      vm.pageNo = parseInt(vm.pageNo) - 1;
      vm.getDetails(vm.type);
    };

    vm.goNextPage = function () {
      vm.pageNo = parseInt(vm.pageNo) + 1;
      vm.getDetails(vm.type);
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

    vm.validateAmount = function (amount, field) {
      if (amount) {
        if (field === "fromAmount") {
          vm.isInvalidFromAmount = !(amount >= 1000 && parseInt(amount) % 100 === 0);
        } else if (field === "toAmount") {
          vm.isInvalidToAmount = !(amount >= 1000 && parseInt(amount) % 100 === 0 && amount <= 10000000000);
        }
      } else {
        vm.isInvalidFromAmount = false;
        vm.isInvalidToAmount = false;
      }
    };

    vm.getSchemeDetails = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: "",
      };
      SubsAmtBLAMaturedAccClosedService.getDetails(requestData).then(
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

    vm.getBLANumberFrom = function (type) {
      var requestData = {
        schemeCode: vm.schemeCode,
        fromAmount: vm.blaNumberFrom,
        flag: type,
      };
      SubsAmtBLAMaturedAccClosedService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaFromList = response.data.result.list;
          angular.element("#blaFromModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    };

    vm.getBLANumberTo = function (type) {
      var requestData = {
        schemeCode: vm.schemeCode,
        toAmount: vm.blaNumberTo,
        flag: type,
      };
      SubsAmtBLAMaturedAccClosedService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaToList = response.data.result.list;
          angular.element("#blaToModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    };

    vm.getDetails = function (type) {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        fromAmount: vm.fromAmount || vm.blaFrom,
        toAmount: vm.toAmount || vm.blaTo,
        flag: type,
        pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchText,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
      };
      SubsAmtBLAMaturedAccClosedService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.model = response.data.result.list;
          vm.filteredRecords = response.data.result.list;
          vm.totalSubscriptionAmount = vm.model.reduce((sum, record) => sum + (record.subscriptionAmount ? parseFloat(record.subscriptionAmount) : 0), 0);

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
            $state.go("Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed.inquiry");
          }
        }
      );
    };

    vm.getType = function (type) {
      const descriptions = {
        B: "BLA No",
        S: "Subscription Amount",
        M: "Maturity Date",
        A: "Account Closed",
      };
      return descriptions[type] || "";
    };

    vm.submitReport = function (type, description) {
      var data = {
        schemeName: vm.schemeName,
        schemeCode: vm.schemeCode,
        blaFrom: vm.blaNumberFrom,
        blaTo: vm.blaNumberTo,
        fromDate: vm.fromDate,
        toDate: vm.toDate,
        fromAmount: vm.fromAmount,
        toAmount: vm.toAmount,
        type: type,
        description: description,
      };

      switch (type) {
        case "B":
          $state.go("Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed.bla_account_details", { obj: { data } });
          break;
        case "S":
          $state.go("Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed.subscription_amount_details", { obj: { data } });
          break;
        case "M":
          $state.go("Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed.matured_account_details", { obj: { data } });
          break;
        case "A":
          $state.go("Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed.account_closed_details", { obj: { data } });
          break;
      }
    };

    if ($stateParams.obj) {
      vm.schemeName = $stateParams.obj.data?.schemeName;
      vm.schemeCode = $stateParams.obj.data?.schemeCode;
      vm.blaFrom = $stateParams.obj.data?.blaFrom;
      vm.blaTo = $stateParams.obj.data?.blaTo;
      vm.fromDate = $stateParams.obj.data?.fromDate;
      vm.toDate = $stateParams.obj.data?.toDate;
      vm.fromAmount = $stateParams.obj.data?.fromAmount;
      vm.toAmount = $stateParams.obj.data?.toAmount;
      vm.type = $stateParams.obj.data?.type;
      vm.typeDescription = $stateParams.obj.data?.description;
    }

    if ($state.current.name == "Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed.bla_account_details") {
      vm.getDetails(vm.type);
    } else if ($state.current.name == "Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed.subscription_amount_details") {
      vm.getDetails(vm.type);
    } else if ($state.current.name == "Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed.matured_account_details") {
      vm.getDetails(vm.type);
    } else if ($state.current.name == "Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed.account_closed_details") {
      vm.getDetails(vm.type);
    }

    // Date Validation
    vm.fromDateVal = function () {
      vm.fromDateOptions = {
        maxDate: new Date(),
        showWeeks: false,
      };
    };

    vm.toDateVal = function (fromDate) {
      vm.toDateOptions = {
        minDate: fromDate,
        maxDate: vm.toDate,
        showWeeks: false,
      };
    };

    // Export Options
    vm.reports = {
      BLAAccountDetails: {
        filename: "BLA Account Number Details",
        mainHeaders: ["BLA Account Number Details", "Bank Name: INSPIRISY BANK LTD", "Sol ID: 101 / BRANCH OF THE BANK"],
        subHeaders: ["Name of the Bond Scheme: " + vm.schemeName, "Bond Ledger Account No. From: " + vm.blaFrom, "Bond Ledger Account No. To: " + vm.blaTo],
        tableHeaders: [
          { title: "S. No.", width: 15, type: "numeric" },
          { title: "BLA No.", width: 30, type: "string" },
          { title: "Holder Name", width: 40, type: "string" },
          { title: "Address", width: 30, type: "string" },
          { title: "Is NRI", width: 20, type: "string" },
          { title: "Is Minor", width: 20, type: "string" },
          { title: "Mode of Holding", width: 30, type: "string" },
          { title: "Holder Type", width: 20, type: "string" },
        ],
        get tableData() {
          return (vm.filteredRecordsDownload || []).map((record, index) => [
            (index + 1).toString(),
            record.blaNumber || "",
            record.holderName || "",
            record.address || "",
            record.isNRI || "",
            record.isMinor || "",
            record.modeOfHolding || "",
            record.typeOfHolder || "",
          ]);
        },
      },
      SubscriptionAmountDetails: {
        filename: "Subscription Amount Details",
        mainHeaders: ["Subscription Amount Details", "Bank Name: INSPIRISY BANK LTD", "Sol ID: 101 / BRANCH OF THE BANK"],
        subHeaders: ["Name of the Bond Scheme: " + vm.schemeName, "Bond Subscription Amount From: " + vm.fromAmount, "Bond Subscription Amount To: " + vm.toAmount],
        tableHeaders: [
          { title: "S. No.", width: 10, type: "numeric" },
          { title: "BLA No.", width: 25, type: "string" },
          { title: "PAN No.", width: 25, type: "string" },
          { title: "Holder Name", width: 40, type: "string" },
          { title: "Subscription ID", width: 20, type: "string" },
          { title: "Date of Subscription", width: 20, type: "string" },
          { title: "Maturity Date", width: 20, type: "string" },
          { title: "Mode of Holding", width: 20, type: "string" },
          { title: "Subscription Amount Rs.", width: 20, type: "numeric" },
        ],
        get tableData() {
          return (vm.filteredRecordsDownload || []).map((record, index) => [
            (index + 1).toString(),
            record.blaNumber || "",
            record.panNumber || "",
            record.holderName || "",
            record.subscriptionID || "",
            record.dateOfSubscription || "",
            record.maturityDate || "",
            record.modeOfHolding || "",
            record.subscriptionAmount.toString() || "",
          ]);
        },
      },
      MaturedAccountDetails: {
        filename: "Matured Account Details",
        mainHeaders: ["Matured Account Details", "Bank Name: INSPIRISY BANK LTD", "Sol ID: 101 / BRANCH OF THE BANK"],
        subHeaders: ["Name of the Bond Scheme: " + vm.schemeName, "Maturity Date From: " + $filter("date")(vm.fromDate, "dd/MM/yyyy"), "Maturity Date To: " + $filter("date")(vm.toDate, "dd/MM/yyyy")],
        tableHeaders: [
          { title: "S. No.", width: 10, type: "numeric" },
          { title: "BLA No.", width: 25, type: "string" },
          { title: "Holder Name", width: 40, type: "string" },
          { title: "Subscription ID", width: 20, type: "string" },
          { title: "Date of Subscription", width: 20, type: "string" },
          { title: "Maturity Date", width: 20, type: "string" },
          { title: "Mode of Holding", width: 20, type: "string" },
          { title: "Subscription Amount Rs.", width: 20, type: "numeric" },
        ],
        get tableData() {
          return (vm.filteredRecordsDownload || []).map((record, index) => [
            (index + 1).toString(),
            record.blaNumber || "",
            record.holderName || "",
            record.subscriptionID || "",
            record.dateOfSubscription || "",
            record.maturityDate || "",
            record.modeOfHolding || "",
            record.subscriptionAmount.toString() || "",
          ]);
        },
      },
      AccountClosedDetails: {
        filename: "Account Closed Details",
        mainHeaders: ["Account Closed Details", "Bank Name: INSPIRISY BANK LTD", "Sol ID: 101 / BRANCH OF THE BANK"],
        subHeaders: ["Name of the Bond Scheme: " + vm.schemeName, "Account Closed Date From: " + $filter("date")(vm.fromDate, "dd/MM/yyyy"), "Account Closed Date To: " + $filter("date")(vm.toDate, "dd/MM/yyyy")],
        tableHeaders: [
          { title: "S. No.", width: 10, type: "numeric" },
          { title: "BLA No.", width: 25, type: "string" },
          { title: "Holder Name", width: 40, type: "string" },
          { title: "Subscription ID", width: 20, type: "string" },
          { title: "Date of Subscription", width: 20, type: "string" },
          { title: "Maturity Date", width: 20, type: "string" },
          { title: "Mode of Holding", width: 20, type: "string" },
          { title: "Subscription Amount Rs.", width: 20, type: "string" },
          { title: "Account Closed Date", width: 20, type: "numeric" },
        ],
        get tableData() {
          return (vm.filteredRecordsDownload || []).map((record, index) => [
            (index + 1).toString(),
            record.blaNumber || "",
            record.holderName || "",
            record.subscriptionID || "",
            record.dateOfSubscription || "",
            record.maturityDate || "",
            record.modeOfHolding || "",
            record.subscriptionAmount.toString() || "",
            record.accountClosedDate || "",
          ]);
        },
      },
    };

    vm.downloadReport = function (screenName) {
      if ($state.current.name == "Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed.bla_account_details") {
        vm.getDownloadDetails("B", screenName);
      } else if ($state.current.name == "Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed.subscription_amount_details") {
        vm.getDownloadDetails("S", screenName);
      } else if ($state.current.name == "Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed.matured_account_details") {
        vm.getDownloadDetails("M", screenName);
      } else if ($state.current.name == "Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed.account_closed_details") {
        vm.getDownloadDetails("A", screenName);
      }
    };

    vm.getDownloadDetails = function (type, screenName) {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        fromAmount: vm.fromAmount || vm.blaFrom,
        toAmount: vm.toAmount || vm.blaTo,
        flag: type,
        pageNo: 1,
        pageSize: vm.total,
        searchValue: vm.searchText,
      };
      if(vm.downloadType=== "excel") {
        SubsAmtBLAMaturedAccClosedService.getDetailsDownload(requestData).then(
              function (response) {
                vm.loader = false;
//                vm.model = response.data.result.list;

                    if (vm.downloadType === "excel") {
                      //                vm.downloadExcel(currentReport);
                      if (response.data.result.fileName) {
                        vm.fileData = response.data.result.fileData;
                        vm.fileName = response.data.result.fileName;
                        saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
                        Swal.fire("Downloaded", response.data.message, "success");
                      }
                      if (type === null || type === undefined) {
                          console.log("Type is null or undefined.");
                      }else if(type==="A"){
                    $state.go('Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed.account_closed_details');
                    }else if(type==="M"){
                    $state.go('Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed.matured_account_details');
                    }else if(type==="S"){
                     $state.go('Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed.subscription_amount_details');
                    }else if(type==="B"){
                   $state.go('Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed.bla_account_details');
                   }else {
                        console.log("Type is invalid: " + type);
                    }
                    }
                    vm.downloadType = "";
              },
              function (error) {
                vm.loader = false;
                if (error.data.message == "No Data Found" || error.data.message == "NDF") {
                  vm.noDataFound = error.data.message;
                } else {
                  handleErrorResponse(error);
                  $state.go("Subscription_Amount_BLA_Account_Number_Matured_Account_Account_Closed.inquiry");
                }
              }
            )
      }
    };

    vm.downloadCSV = function (currentReport) {
      const { filename, mainHeaders, subHeaders, tableHeaders, tableData } = currentReport;
      const formatRow = (values) => {
        return tableHeaders
          .map((col, i) => {
            const value = values[i] ? values[i].toString() : "";
            const alignment = col.type === "numeric" ? "right" : "left";
            if (alignment === "right") {
              return value.padStart(col.width, " ");
            } else {
              return value.padEnd(col.width, " ");
            }
          })
          .join(" | ");
      };
      const formattedHeader = formatRow(tableHeaders.map((col) => col.title));
      const formattedData = tableData.map((row) => formatRow(row)).join("\n");

      const totalRow = formatRow(
        tableHeaders.map((col, i) => {
          if (i === tableHeaders.length - 2) return "Total";
          if (i === tableHeaders.length - 1) return vm.totalSubsAmount;
          return "";
        })
      );
      let textData = [];
      if (filename != "BLA Account Number Details") {
        textData = [
          mainHeaders.join("\n"),
          subHeaders.join("\n"),
          "-".repeat(formattedHeader.length),
          formattedHeader,
          "-".repeat(formattedHeader.length),
          formattedData,
          "-".repeat(formattedHeader.length),
          totalRow,
          "-".repeat(formattedHeader.length),
        ].join("\n");
      } else {
        textData = [mainHeaders.join("\n"), subHeaders.join("\n"), "-".repeat(formattedHeader.length), formattedHeader, "-".repeat(formattedHeader.length), formattedData, "-".repeat(formattedHeader.length)].join("\n");
      }

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
      let totalRow = [];
      if (filename === "Subscription Amount Details") {
        totalRow = ["", "", "", "", "", "", "", "Total", vm.totalSubsAmount];
      } else {
        totalRow = ["", "", "", "", "", "", "Total", vm.totalSubsAmount];
      }
      if (filename != "BLA Account Number Details") {
        excelData.push(totalRow);
      }
      const ws = XLSX.utils.aoa_to_sheet(excelData);

      const mergeRanges = [
        { s: { r: 0, c: 0 }, e: { r: 0, c: 10 } },
        { s: { r: 1, c: 0 }, e: { r: 1, c: 10 } },
        { s: { r: 2, c: 0 }, e: { r: 2, c: 10 } },
        { s: { r: 3, c: 0 }, e: { r: 3, c: 10 } },
        { s: { r: 4, c: 0 }, e: { r: 4, c: 10 } },
        { s: { r: 5, c: 0 }, e: { r: 5, c: 10 } },
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
      let totalRow = [];
      if (filename === "Subscription Amount Details") {
        totalRow = ["", "", "", "", "", "", "", "Total", vm.totalSubsAmount];
      } else {
        totalRow = ["", "", "", "", "", "", "Total", vm.totalSubsAmount];
      }
      if (filename != "BLA Account Number Details") {
        tableData.push(totalRow);
      }

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
