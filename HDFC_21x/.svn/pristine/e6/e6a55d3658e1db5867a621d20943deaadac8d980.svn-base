(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("bondController", bondController);

  bondController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "BondInterestReportService", "commonService"];

  function bondController($scope, $filter, $state, $stateParams, $location, $rootScope, BondInterestReportService, commonService) {
    var vm = this;

    vm.currentPage = 1;
    vm.itemsPerPage = "5";
    vm.loginSolId = $rootScope.globals.currentUser.solId;
    vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
    vm.solDate = $rootScope.globals.currentUser.systemDate;
    vm.getBondInterestCalculatedReportDetails = getBondInterestCalculatedReportDetails;
    vm.getModuleName = getModuleName;
    vm.getFinancialYear = getFinancialYear;
    //        vm.getMonth = getMonth;
    //        vm.printpage = printpage;
    //        vm.alert = alert;
    //        vm.getbondInterestDetailsfiles = getbondInterestDetailsfiles;

    // Pagination
    vm.filteredRecords = [];
    vm.currentPage = 1;
    vm.maxPagesToShow = 5;
    vm.searchText = "";
    vm.subTotalAmount = "";
    vm.grossTotalAmount = "";
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
      vm.getBondInterestCalculatedReportDetails();
    };

    vm.pageNo = 1;
    vm.pageSize = 5;

    vm.goPreviousPage = function () {
      vm.pageNo = parseInt(vm.pageNo) - 1;
      vm.getBondInterestCalculatedReportDetails();
    };

    vm.goNextPage = function () {
      vm.pageNo = parseInt(vm.pageNo) + 1;
      vm.getBondInterestCalculatedReportDetails();
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

    // function getMonth(){
    //        vm.loader = true;
    //        var requestData={
    //        schemeCode:vm.schemeCode,
    //        flag:"I"
    //        };
    //        BondInterestReportService.getDetails(requestData).then(function (response) {
    //         vm.loader = false;
    //         vm.monthIns = response.data.result.list;
    //         console.log("vm.monthIns",vm.monthIns);
    //        }, function (error) {
    //         handleErrorResponse(error);
    //         vm.loader = false;
    //        });
    //        }

    function getFinancialYear() {
      vm.loader = true;
      var requestData = {
        flag: "FY",
        detailsFlag:"D",
      };
      BondInterestReportService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.financialYears = response.data.result.list;
          console.log("vm.financialYears", vm.financialYears);
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function getModuleName() {
      vm.loader = true;
      var requestData = {
        schemeCode: "",
        flag: "S",
        detailsFlag:"D",
      };
      BondInterestReportService.getDetails(requestData).then(
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

    function getBondInterestCalculatedReportDetails() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        finYear: vm.financialYear,
        month: vm.interestPaid,
        flag: "D",
        pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchValue,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
        detailsFlag:"D",
        schemeName :vm.schemeName,
      };

      BondInterestReportService.getDetails(requestData)
        .then(function (response) {
          vm.model = response.data.result.list;
          vm.filteredRecords = response.data.result.list;

             vm.subTotalAmountt = vm.filteredRecords.reduce((sum, record) => sum + (record.subscriptionAmount ? parseFloat(record.subscriptionAmount) : 0), 0);

                     vm.grossTotalAmountt = vm.filteredRecords.reduce((sum, record) => sum + (record.grossInterestAmount ? parseFloat(record.grossInterestAmount) : 0), 0);

          vm.total = response.data.total;
          vm.maxPages = Math.ceil(response.data.total / vm.pageSize);
          vm.result = response.data.result.list;
          vm.paginationControls();

          var holdingReferenceId = { referenceId: "MODE_OF_PAYMENT" };

          BondInterestReportService.getReferenceData(holdingReferenceId).then(function (holdingMappingResponse) {
            var holdingMappings = holdingMappingResponse.data.result;

            var paymentMap = {};
            holdingMappings.forEach(function (item) {
              paymentMap[item.SUBCODE] = item.DESCRIPTION;
            });

            vm.filteredRecords.forEach(function (record) {
              record.modeOfPaymentDescription = paymentMap[record.interestPaymentmode] || "Unknown";
            });
          });

          vm.loader = false;

          if (vm.model.length === 0) {
            vm.noDataFoundInSearch = true;
            vm.paginationControls();

          } else {
            vm.noDataFoundInSearch = false;
          }
        })
        .catch(function (error) {
          vm.loader = false;
          handleError(error);
        });
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

    function handleError(error) {
      vm.loader = false;
      if ((error.data.message == "No Data Found" || error.data.message == "NDF") && vm.searchValue) {
       vm.pageNo ='0';
        vm.maxPages ='0';
         vm.showNext = false;

         vm.noDataFoundInSearch = true;

      } else if (error.data.message == "No Data Found" || error.data.message == "NDF") {
        vm.noDataFound = error.data.message;
      } else {
        handleErrorResponse(error);
        $state.go("Bond_Interest_NOT_Calculated_Report_collecting_branch.inquiry");
      }
    }

    //  vm.downloadReport = function () {
    //      console.log("The download Type ", vm.downloadType);
    //      if (vm.downloadType === "excel") {
    //        console.log("The download Type Excel working");
    //        vm.downloadExcel();
    //      }
    //      if (vm.downloadType === "csv") {
    //        console.log("The download Type CSV working");
    //        vm.downloadCSV();
    //      }
    //      if (vm.downloadType === "pdf") {
    //              console.log("The download Type pdf working");
    //              vm.downloadPDF();
    //            }
    //       vm.downloadType='';
    //    };

    vm.downloadReport = function () {

      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        finYear: vm.financialYear,
        month: vm.interestPaid,
        flag: "D",
        pageNo: "1",
        pageSize: vm.total,
        searchValue: vm.searchValue,
        downloadType:vm.downloadType
      };
      BondInterestReportService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.filteredRecord = response.data.result.list;
          //    const currentReport = vm.reports["BondInterestNotCalculated"];

//           vm.subTotalAmount = vm.filteredRecord.reduce((sum, record) => sum + (record.subscriptionAmount ? parseFloat(record.subscriptionAmount) : 0), 0);
//
//           vm.grossTotalAmount = vm.filteredRecord.reduce((sum, record) => sum + (record.grossInterestAmount ? parseFloat(record.grossInterestAmount) : 0), 0);
//

          if (vm.downloadType === "excel") {
           if (response.data.result.fileName) {
                                   vm.fileData = response.data.result.fileData;
                                   vm.fileName = response.data.result.fileName;
                                   saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
                              }
                              }
          else if (vm.downloadType === "csv") {
            vm.downloadCSV();
          }
          vm.downloadType = "";
        },
        function (error) {
          vm.loader = false;
          if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("rbibond_precloser_repayment_report.inquiry");
          }
        }
      );
    };

    vm.downloadExcel = function () {
      const additionalHeaders = [
        ["Bond Interest Not Calculated Report"],
        ["Bank Name: INSPIRISY BANK LTD"],
        ["Sol ID: 101 / BRANCH OF THE BANK"],
        ["Interest payment for: " + " " + vm.interestPaid],
        ["FinancialYear: " + " " + vm.financialYear],
        ["Name of the Bond Scheme: " + vm.schemeName],
        [],
      ];

      const tableHeader = [
        "S.No.",
        "PAN No",
        "BLA No",
        "Holder Name",
        "Subscription Date",
        "Subscription Account No",
        "Subscription Amount (Rs)",
        "Interest Payment mode",
        "Gross Interest Amount",
        "Reasons",
      ];

      const tableData = vm.filteredRecord.map((record, index) => [
        (vm.currentPage - 1) * vm.pageSize + index + 1,
        record.panNo,
        record.blaNo,
        record.holderName,
        record.subscriptionDate,
        record.subscriptionAccountNo,
        record.subscriptionAmount,
        record.modeOfPaymentDescription,
        record.grossInterestAmount,
        record.reasons,
      ]);


   const tableBody = [
     ["", ""], // Placeholder rows
     ["", ""],
     ["", ""],
     ["", ""],
     ["", ""],
     ["Total Subscription Amount -->", ""],
     [vm.subTotalAmount, ""],
     ["Total Gross Interest Amount", ""],
     [vm.grossTotalAmount, ""],
     ["", ""] // Final placeholder row
   ];




      const wsData = [...additionalHeaders, tableHeader, ...tableData,tableBody];
      const ws = XLSX.utils.aoa_to_sheet(wsData);
      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb, ws, "Subscription Details");
      XLSX.writeFile(wb, "Bond Interest Not Calculated Report.xlsx");
    };

    vm.downloadCSV = function () {
      const tableHeader = [
        { title: "S.NO", width: 10 },
        { title: "PAN No", width: 30 },
        { title: "BLA No.", width: 30 },
        { title: "Holder Name", width: 45 },
        { title: "Subscription Date", width: 40 },
        { title: "Subscription Account No", width: 40 },
        { title: "Subscription Amount (Rs)", width: 40 },
        { title: "Interest Payment mode", width: 40 },
        { title: "Gross Interest Amount", width: 40 },
        { title: "Reasons", width: 40 },
      ];

      // Calculate the formatted header with column widths
      const formattedHeader = tableHeader
        .map((col) => col.title.padEnd(col.width, " ")) // Pad each column title to its width
        .join("");

      // Format table rows so data aligns with the beginning of headers
      const tableData = vm.filteredRecord.map((record, index) =>
        tableHeader
          .map((col, colIndex) => {
            const value = [
              index + 1, // S.NO
              record.panNo || "",
              record.blaNo || "",
              record.holderName || "",
              record.subscriptionDate || "",
              record.subscriptionAccountNo || "",
              record.subscriptionAmount || "",
              record.modeOfPaymentDescription || "",
              record.grossInterestAmount || "",
              record.reasons || "",
            ][colIndex];
            return value.toString().padEnd(col.width, " "); // Left-align data under each column
          })
          .join("")
      );

      //    // Calculate total subscription amount
      //    const totalAmount = vm.filteredRecords.reduce(
      //      (total, record) => total + parseFloat(record.subsAmount || 0),
      //      0
      //    );

      //    // Add total row, aligning it to the beginning of the "Total" label
      //    const totalRow = tableHeader
      //      .map((col, colIndex) => {
      //        if (colIndex === tableHeader.length - 3) return "Total".padEnd(col.width, " ");
      //        if (colIndex === tableHeader.length - 2) return totalAmount.toFixed(2).padEnd(col.width, " ");
      //        return "".padEnd(col.width, " ");
      //      })
      //      .join("");

      // Combine all data
      const textData = [
        "Bond Interest Not calculated Report",
        "Bank Name: INSPIRISY BANK LTD",
        "Sol ID: 101 / BRANCH OF THE BANK",
        "Name of the Bond Scheme: " + vm.schemeName,
        "Interest payment for: " + " " + vm.interestPaid,
        "FinancialYear: " + "  " + vm.financialYear,
        "",
        formattedHeader,
        "-".repeat(formattedHeader.length), // Separator line
        ...tableData,
        //      totalRow,
      ].join("\n");

      // Create and download the text file
      const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData);
      const link = document.createElement("a");
      link.setAttribute("href", textContent);
      link.setAttribute("download", "Bond Interest Not Calculated Report.txt");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    };

    vm.downloadPDF = function () {
      const { jsPDF } = window.jspdf;
      const doc = new jsPDF({ orientation: "landscape", unit: "mm", format: "a4" });
      doc.setFont("Times", "normal");

      // Table data
      const tableData = vm.filteredRecords.map((record, index) => [
        (vm.currentPage - 1) * vm.pageSize + index + 1, // S.No.
        record.panNo,
        record.blaNo,
        record.holderName,
        record.subscriptionDate,
        record.subscriptionAccountNo,
        record.subscriptionAmount,
        record.interestPaymentmode,
        record.grossInterestAmount,
        record.reasons,
      ]);

      // Table headers
      const tableHeader = [
        "S.No.",
        "PAN No",
        "BLA No",
        "Holder Name",
        "Subscription Date",
        "Subscription Account No",
        "Subscription Amount (Rs)",
        "Interest Payment Mode",
        "Gross Interest Amount",
        "Reasons",
      ];

      // Additional headers
      const additionalHeaders = [
        { text: "Bond Interest Not Calculated Report", fontSize: 14, yPosition: 10 },
        { text: "Bank Name : INSPIRISY BANK LTD", fontSize: 12, yPosition: 17 },
        { text: "Sol ID : 101 / BRANCH OF THE BANK", fontSize: 12, yPosition: 22 },
        { text: "Interest payment for: " + vm.interestPaid, fontSize: 12, yPosition: 27 },
        { text: "FinancialYear: " + vm.financialYear, fontSize: 12, yPosition: 32 },
        { text: "Name of the Bond Scheme: " + vm.schemeName, fontSize: 12, yPosition: 37 },
      ];

      // Center additional headers
      additionalHeaders.forEach((header) => {
        const pageWidth = doc.internal.pageSize.width;
        const textWidth = doc.getTextWidth(header.text);
        const xPosition = (pageWidth - textWidth) / 2;
        doc.setFontSize(header.fontSize);
        doc.text(header.text, xPosition, header.yPosition);
      });

      // Generate the table with aligned columns
      doc.autoTable({
        head: [tableHeader],
        body: tableData,
        startY: 40, // Start table below additional headers
        margin: { top: 20 },
        styles: {
          fontSize: 10,
          overflow: "linebreak", // Allow line breaks for long text
        },
        columnStyles: {
          columnStyles: {
            0: { cellWidth: 10 }, // S.No.
            1: { cellWidth: 20 }, // PAN No
            2: { cellWidth: 40 }, // BLA No
            3: { cellWidth: 40 }, // Holder Name
            4: { cellWidth: 30 }, // Subscription Date
            5: { cellWidth: 35 }, // Subscription Account No
            6: { cellWidth: 20 }, // Subscription Amount
            7: { cellWidth: 30 }, // Interest Payment Mode
            8: { cellWidth: 40 }, // Gross Interest Amount (increased width)
            9: { cellWidth: 40 }, // Reasons (increased width)
          },
          // Reasons (increased width)
        },
        pageBreak: "auto",
        rowPageBreak: "avoid",
        showHead: "firstPage", // Automatically handle page breaks
      });

      // Save the document
      doc.save("Bond Interest Not Calculated Report.pdf");
    };

    if ($state.current.name == "Bond_Interest_NOT_Calculated_Report_collecting_branch.inquiry_details") {
      vm.branchId = $stateParams.obj.branchId;
      vm.interestPaid = $stateParams.obj.interestPaid;
      vm.financialYear = $stateParams.obj.financialYear;
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.schemeName = $stateParams.obj.schemeName;
      getBondInterestCalculatedReportDetails();
    }
  }
})();
