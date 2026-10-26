(function () {

    'use strict';

    angular.module('gbmUltraApp').controller(
        'listofbondinvestmentsController',
        listofbondinvestmentsController);

    listofbondinvestmentsController.$inject = ['$scope', '$filter',
        '$state', '$stateParams', '$location', '$rootScope', 'listofbondinvestmentsService', 'commonService'];

    function listofbondinvestmentsController($scope, $filter, $state,
        $stateParams, $location, $rootScope, listofbondinvestmentsService,
        commonService) {

        var vm = this;

        vm.currentPage = 1;
        vm.itemsPerPage = '5';
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.currentDate = new Date();
        vm.currentDate = $filter('date')(vm.currentDate, 'dd/MM/yyyy');
        vm.getListofbondInvestmentReport = getListofbondInvestmentReport;
        vm.searchValue = "";


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
         vm.getListofbondInvestmentReport();
       };

       vm.pageNo = 1;
       vm.pageSize = 5;

       vm.goPreviousPage = function () {
         vm.pageNo = parseInt(vm.pageNo) - 1;
         vm.getListofbondInvestmentReport();
       };

       vm.goNextPage = function () {
         vm.pageNo = parseInt(vm.pageNo) + 1;
         vm.getListofbondInvestmentReport();
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

        vm.downloadReport = function () {
        vm.loader = true;

                    var requestData = {
                        solId: vm.solId,
                        solDesc: vm.solDesc,
                        pageNo: "1",
                        pageSize: vm.total,
                        searchValue: vm.searchValue,
                    };

                    if(vm.downloadType == "excel") {
                                        listofbondinvestmentsService.getDetailsDownload(requestData).then(function (response) {

                                            vm.loader = false;
                                            vm.filteredRecord = response.data.result.listOfBondInvestmentsDTOList;
                                            vm.totalAmountDownload = vm.filteredRecords.reduce((sum, record) => sum + (record.subsAmount ? parseFloat(record.subsAmount) : 0), 0);

                                            if (vm.downloadType === "excel") {
                    //                            vm.downloadExcel();
                                                    if (response.data.result.fileName) {
                                                         vm.fileData = response.data.result.fileData;
                                                         vm.fileName = response.data.result.fileName;
                                                         saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
                                                         Swal.fire("Downloaded", response.data.message, "success");
                                                    }
                                            }
                                            vm.downloadType = "";
                                        }, function (error) {
                                            vm.loader = false;
                                            if (error.data.message == "No Data Found" || error.data.message == "NDF") {
                                                vm.noDataFound = error.data.message;
                                            } else {
                                                handleErrorResponse(error);
                                                $state.go('List_of_Bond_Investments_Pending_Receipt_of_CAS_Advice_No_report.inquiry');
                                            }

                                        })
                    } else if(vm.downloadType != "excel") {
                                        listofbondinvestmentsService.getDetails(requestData).then(function (response) {

                                            vm.loader = false;
                                            vm.filteredRecord = response.data.result.listOfBondInvestmentsDTOList;
                                            vm.totalAmountDownload = vm.filteredRecords.reduce((sum, record) => sum + (record.subsAmount ? parseFloat(record.subsAmount) : 0), 0);

                                            if (vm.downloadType === "csv") {
                                                      vm.downloadCSV();
                                            }
                                            else if (vm.downloadType === "pdf") {
                                                     vm.downloadPDF();
                                            }
                                            vm.downloadType = "";
                                        }, function (error) {
                                            vm.loader = false;
                                            if (error.data.message == "No Data Found" || error.data.message == "NDF") {
                                                vm.noDataFound = error.data.message;
                                            } else {
                                                handleErrorResponse(error);
                                                $state.go('List_of_Bond_Investments_Pending_Receipt_of_CAS_Advice_No_report.inquiry');
                                            }

                                        })
                    }
        };


vm.downloadPDF = function () {
  const { jsPDF } = window.jspdf;
  const doc = new jsPDF();
  doc.setFont("Times", "normal");

  // Header details
  const additionalHeaders = [
    { text: "List of Bond Investments Pending Receipt of CAS Advice No", fontSize: 14, yPosition: 10 },
    { text: "Bank Name : INSPIRISY BANK LTD", fontSize: 12, yPosition: 17 },
    { text: "Sol ID : 101 / BRANCH OF THE BANK", fontSize: 12, yPosition: 22 },
  ];

  // Render additional headers
  additionalHeaders.forEach((header) => {
    const pageWidth = doc.internal.pageSize.width;
    const textWidth = doc.getTextWidth(header.text);
    const xPosition = (pageWidth - textWidth) / 2;
    doc.setFontSize(header.fontSize);
    doc.text(header.text, xPosition, header.yPosition);
  });


  // Main Table Data
  const tableData = vm.filteredRecord.map((record, index) => [
    index + 1, // Serial Number
       record.blaNumber || "",
                    record.schemeName || "",
                    record.subsDate || "",
                    record.subsAmount || "",

  ]);

//  // Calculate total balance
//  const totalAmount = vm.filteredRecord.reduce(
//    (total, record) => total + parseFloat(record.subsAmount || 0),
//    0
//  );

  // Add total row
  tableData.push(["", "", "", "Total", Math.round(vm.totalAmountDownload)]);

  // Table Header
  const tableHeader = [
                "S.NO",
                "BLA Number",
                "Bond Scheme",
                "Subscription Date",
                "Subscription Amount",
  ];

  // Render Main Table
  doc.autoTable({
    head: [tableHeader],
    body: tableData,
    startY: doc.lastAutoTable ? doc.lastAutoTable.finalY + 10 : 50, // Position below summary table
    margin: { top: 20 },
    showHead: "everyPage",
    theme: "grid",
    styles: {
      fontSize: 8,
      overflow: "linebreak",
      halign: "center",
      valign: "middle",
    },
    pageBreak: "auto",
    rowPageBreak: "avoid",
    showHead: "firstPage",
//    columnStyles: {
//      0: { cellWidth: 20 }, // S.No.
//      1: { cellWidth: 30 }, // BLA No
//      2: { cellWidth: 40 }, // Bond Scheme
//      3: { cellWidth: 30 }, // Subscription Date
//      4: { cellWidth: 30, halign: "right" }, // Subscription Amount
//    },
  });

  // Save the PDF
  doc.save("List_of_Bond_Investments.pdf");
};


vm.downloadExcel = function () {
            var mainHeader = ["List of Bond Investments Pending Receipt of CAS Advice No"];
            var bankDetails = [
                ["Bank Name: INSPIRISY BANK LTD"],
                ["Sol ID: 101 / BRANCH OF THE BANK"]
            ];
            var headers = [
                "S.No.", "BLA Number", "Bond Scheme", "Subscription Date", "Subscription Amount"
            ];

            var rows = vm.filteredRecord.map(function (record, index) {
                return [
                    index + 1,
                    record.blaNumber,
                    record.schemeName,
                    record.subsDate,
                    parseFloat(record.subsAmount) || 0
                ];
            });

            var totalAmount = vm.filteredRecord.reduce((total, record) => total + parseFloat(record.subsAmount || 0), 0);
            rows.push(["", "", "", "Total", Math.round(vm.totalAmountDownload)]);

            rows.unshift(headers);
            rows.unshift([]);
            bankDetails.reverse().forEach(detail => rows.unshift(detail));
            rows.unshift(mainHeader);

            var worksheet = XLSX.utils.aoa_to_sheet(rows);
            var workbook = XLSX.utils.book_new();
            XLSX.utils.book_append_sheet(workbook, worksheet, "Report");

            worksheet["A1"].s = {
                font: { bold: true, sz: 14 },
                alignment: { horizontal: "center", vertical: "center" }
            };
            worksheet["A2"].s = {
                font: { bold: true, sz: 12 },
                alignment: { horizontal: "center", vertical: "center" }
            };
            worksheet["A3"].s = {
                font: { bold: true, sz: 12 },
                alignment: { horizontal: "center", vertical: "center" }
            };

            worksheet["!merges"] = [
                { s: { r: 0, c: 0 }, e: { r: 0, c: headers.length - 1 } },
                { s: { r: 1, c: 0 }, e: { r: 1, c: headers.length - 1 } },
                { s: { r: 2, c: 0 }, e: { r: 2, c: headers.length - 1 } }
            ];

            for (var rowIndex = 4; rowIndex < rows.length; rowIndex++) {
                var cellAddress = { r: rowIndex, c: 4 };
                var cell = worksheet[XLSX.utils.encode_cell(cellAddress)];

                if (cell) {
                    cell.s = {
                        alignment: { horizontal: "right" }
                    };
                }
            }

            var totalCellAddress = { r: rows.length - 1, c: 4 };
            var totalCell = worksheet[XLSX.utils.encode_cell(totalCellAddress)];

            if (totalCell) {
                totalCell.s = {
                    alignment: { horizontal: "right" }
                };
            }
            XLSX.writeFile(workbook, "List_of_Bond_Investments_Pending_Receipt_of_CAS_Advice_No.xlsx");
        };

vm.downloadCSV = function () {
  const tableHeader = [
    { title: "S.NO", width: 10 },
    { title: "BLA No.", width: 30 },
    { title: "Bond Scheme", width: 45 },
    { title: "Subscription Date", width: 40 },
    { title: "Subscription Amount", width: 40 }, // Subscription Amount column
  ];

  const formattedHeader = tableHeader
    .map(col => col.title.padEnd(col.width, " "))
    .join("");

  const tableData = vm.filteredRecord.map((record, index) =>
    tableHeader
      .map((col, colIndex) => {
        const value = [
          index + 1, // S.NO
          record.blaNumber || "", // BLA No.
          record.schemeName || "", // Bond Scheme
          record.subsDate || "", // Subscription Date
          record.subsAmount || "", // Subscription Amount
        ][colIndex];

        // Align 'Subscription Amount' (last column) to the right
        if (col.title === "Subscription Amount") {
          return value.toString().padStart(col.width, " ");
        }

        // Default: Align other columns to the left
        return value.toString().padEnd(col.width, " ");
      })
      .join("")
  );

//  const totalAmount = vm.filteredRecord.reduce(
//        (total, record) => total + parseFloat(record.subsAmount || 0),
//        0
//      );

  const totalAmountWithoutDecimal = Math.floor(vm.totalAmountDownload);

  const emptyRow = tableHeader.map(col => "".padEnd(col.width, " ")).join("");

  const totalRow = tableHeader
    .map((col, colIndex) => {
      if (colIndex === tableHeader.length - 2) {
        // "Total" text in the second-to-last column
        return "Total".padEnd(col.width, " ");
      }
      if (colIndex === tableHeader.length - 1) {
        // Total amount in the last column, right-aligned
        const totalStr = totalAmountWithoutDecimal.toString();
        return totalStr.padStart(col.width, " ");
      }
      return "".padEnd(col.width, " "); // Empty for other columns
    })
    .join("");

  const textData = [
    "List_of_Bond_Investments_Pending_Receipt_of_CAS_Advice_No",
    "Bank Name: INSPIRISY BANK LTD",
    "Sol ID: 101 / BRANCH OF THE BANK",
    "",
    formattedHeader,
    "-".repeat(formattedHeader.length),
    ...tableData,
    emptyRow, // Add an empty row for spacing
    totalRow,
  ].join("\n");

  const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData);
  const link = document.createElement("a");
  link.setAttribute("href", textContent);
  link.setAttribute("download", "List_of_Bond_Investments_Pending_Receipt_of_CAS_Advice_No.txt");
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
};

if ($state.current.name == "List_of_Bond_Investments_Pending_Receipt_of_CAS_Advice_No_report.inquiry") {
    vm.getListofbondInvestmentReport();
}



        function getListofbondInvestmentReport() {
        console.log("Working List_of_Bond_Investments_Pending_Receipt_of_CAS_Advice_No_report/action/inquiry");
            vm.loader = true;

            var requestData = {
                solId: vm.solId,
                solDesc: vm.solDesc,
               pageNo: vm.pageNo,
               pageSize: vm.pageSize,
               searchValue: vm.searchValue,
               sortOrder: vm.sortOrder,
               sortField: vm.sortField,
            };


            listofbondinvestmentsService.getDetails(requestData).then(function (response) {

                vm.loader = false;
                vm.pageDetails = response.data.result;
                vm.model = response.data.result.listOfBondInvestmentsDTOList;
                vm.filteredRecords = response.data.result.listOfBondInvestmentsDTOList;
                vm.records = response.data.result.listOfBondInvestmentsDTOList;
                 vm.SubscriptionTotalAmount = vm.filteredRecords.reduce((sum, record) => sum + (record.subsAmount ? parseFloat(record.subsAmount) : 0), 0);
                 vm.total = response.data.total;
                 vm.maxPages = Math.ceil(response.data.total / vm.pageSize);
                 vm.paginationControls();
                 if (vm.model.length === 0) {
                   vm.noDataFoundInSearch = true;
                 } else {
                   vm.noDataFoundInSearch = false;
                 }


            }, function (error) {
                vm.loader = false;
                if (vm.searchValue && (error.data.message == "No Data Found" || error.data.message == "NDF")) {
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
                    $state.go('List_of_Bond_Investments_Pending_Receipt_of_CAS_Advice_No_report.inquiry');
                }

            })
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
    }
})();