(function () {
  "use strict";

  angular
    .module("gbmUltraApp")
    .controller("DashboardInwardOutwardController", DashboardInwardOutwardController);

  DashboardInwardOutwardController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$stateParams",
    "$location",
    "$rootScope",
    "DashBoardReportForInwardOutwardService",
    "commonService",
  ];

  function DashboardInwardOutwardController(
    $scope,
    $filter,
    $state,
    $stateParams,
    $location,
    $rootScope,
    DashBoardReportForInwardOutwardService,
    commonService
  ) {
    var vm = this;
      vm.textableDatePicker=textableDatePicker;
      vm.autoslashing=autoslashing;

             function textableDatePicker(id,valueDate)
             {
              commonService.textableDatePicker(id,valueDate);
             }
             function autoslashing(id)
             {
              commonService.autoslashing(id);
             }



   vm.currentPage = 1;
    vm.itemsPerPage = "5";
    vm.loginSolId = $rootScope.globals.currentUser.solId;
    vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
    vm.solDate = $rootScope.globals.currentUser.systemDate;
    vm.currentDate = new Date();

    vm.getDashBoardReportForInwardOutward = getDashBoardReportForInwardOutward;
    vm.getModuleName = getModuleName;
    vm.printpage = printpage;
    vm.searchValue = "";
    vm.getblaNo = getblaNo;
    vm.getPanTorch = getPanTorch;
//    vm.getsubscriptionIdInq = getsubscriptionIdInq;
//    vm.dateValidation = dateValidation;
//  vm.schemeList = schemeList ;
//
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
          vm.getDashBoardReportForInwardOutward();
        };

        vm.goNextPage = function () {
          vm.pageNo = parseInt(vm.pageNo) + 1;
          vm.getDashBoardReportForInwardOutward();
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


    if ($state.current.name == "DashBoard_Report_for_Inward_and_Outward.inquiry_detail") {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.fromDate = $stateParams.obj.fromDate;
      vm.toDate = $stateParams.obj.toDate;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.status=$stateParams.obj.status;
      vm.panNo = $stateParams.obj.panNo;
      vm.blaNum = $stateParams.obj.blaNo;
//      vm.subscriptionId = $stateParams.obj.subscriptionId;
      vm.HolderName = $stateParams.obj.name;
         getDashBoardReportForInwardOutward();
    }


   vm.downloadExcel = function () {
     const additionalHeaders = [
       ["Dashboard Report For Inward And Outward"],
       ["Sol ID: 101 / BRANCH OF THE BANK"],
       ["Name of the Bond Scheme: " + vm.schemeName],
       ["From Date: " + $filter('date')(vm.fromDate, 'dd/MM/yyyy')],  // From Date
       ["To Date: " + $filter('date')(vm.toDate, 'dd/MM/yyyy')],      // To Date
       [],]

     const tableHeader = [
        "S.No",
        "FTS Bar Code Number",
        "Scheme Name",
        "BLA number",
        "Subscription",
        "User Name",
        "Request Code",
        "Request Description",
        "Inward Reference Number",
        "Inward Date",
        "Outward Reference Number",
        "Outward Date",
        "Outward Reason",
        "Status",
     ];

     const tableData = vm.filteredRecords.map((record, index) => [
       (vm.currentPage - 1) * vm.pageSize + index + 1,
       record.blaNo,
//       record.subscriptionId,
       record.bondType,
       record.subscriptionDate,
       record.subscriptionMaturityDate,
       record.currentBalance,
     ]);

     const totalAmount = vm.filteredRecord.reduce((total, record) => total + parseFloat(record.currentBalance || 0), 0);
     tableData.push(["", "", "", "", "", "Grand Total(Rs)", totalAmount]);

     const wsData = [...additionalHeaders, tableHeader, ...tableData];
     const ws = XLSX.utils.aoa_to_sheet(wsData);
     const wb = XLSX.utils.book_new();
     XLSX.utils.book_append_sheet(wb, ws, "Subscription Details");
     XLSX.writeFile(wb, "Dashboard Report For Inward And Outward.xlsx");
        };

vm.downloadCSV = function () {
      const additionalHeaders = [
        "RBI Bond Balance Report",
        "Bank Name: INSPIRISY BANK LTD",
        "Sol ID: 101 / BRANCH OF THE BANK",
        "Name of the Bond Scheme: " + vm.schemeName,
       "From Date: " + $filter('date')(vm.fromDate, 'dd/MM/yyyy'),  // From Date
        "To Date: " + $filter('date')(vm.toDate, 'dd/MM/yyyy'),      // To Date
        "", // Blank line for spacing
      ];

  const tableHeader = [
    "S.No.",
    "BLA No",
    "Subscription ID",
    "Bond Type",
    "Subscription Date",
    "Subscription Maturity Date",
    "Current Balance (Rs)",
     "Subscription Status",
  ];

  function padString(str, width) {
    return str.toString().padEnd(width, " ");
  }


  const columnWidths = [10, 25, 25, 25, 25, 30, 25]; // Adjust as necessary for proper alignment

  const formattedHeader = tableHeader
    .map((header, index) => padString(header, columnWidths[index]))
    .join("");

  const tableData = vm.filteredRecord.map((record, index) =>
    [
      padString((vm.currentPage - 1) * vm.pageSize + index + 1, columnWidths[0]),
      padString(record.blaNo || "", columnWidths[1]),
//      padString(record.subscriptionId || "", columnWidths[2]),
      padString(record.bondType || "", columnWidths[3]),
      padString(record.subscriptionDate || "", columnWidths[4]),
      padString(record.subscriptionMaturityDate || "", columnWidths[5]),
      padString(record.currentBalance || "", columnWidths[6]),
      padString(record.subStatus || "", columnWidths[7]),
    ].join("")
  );

  const totalAmount = vm.filteredRecords.reduce(
    (total, record) => total + parseFloat(record.currentBalance || 0),
    0
  );


  tableData.push(
    [
      padString("", columnWidths[0]),
      padString("", columnWidths[1]),
      padString("", columnWidths[2]),
      padString("", columnWidths[3]),
      padString("", columnWidths[4]),
      padString("Grand Total (Rs)", columnWidths[5]),
      padString(totalAmount, columnWidths[6]),
      padString("", columnWidths[7]),
    ].join("")
  );


  const textData = [
    ...additionalHeaders,
    formattedHeader,
    ...tableData,
  ].join("\n"); // Use newline to separate rows

  // Create and download the text file
  const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData);
  const link = document.createElement("a");
  link.setAttribute("href", textContent);
  link.setAttribute("download", "RBI_Dash_Board_Report_For_Inward_Outward.txt");
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
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

    vm.downloadReport = function () {
      vm.loader = true;
                  var requestData = {
                   schemeCode: vm.schemeCode,
                   fromDate: $filter('date')(vm.fromDate,'dd-MMM-yyyy'),
                   toDate: $filter('date')(vm.toDate,'dd-MMM-yyyy'),
                   blaNo: vm.blaNum,
                   panNo: vm.panNo,
                   name: vm.HolderName,
                 status:vm.status,
                   flag: "D",
                   pageNo: 1,
                   pageSize: vm.total,
                   searchValue: vm.searchValue,
                  };
                  if(vm.downloadType === "excel") {
                 DashBoardReportForInwardOutwardService.getDashBoardReportForInwardOutwardDownload(requestData)
                 .then(function (response) {

                     vm.loader = false;
                     vm.filteredRecord = response.data.result.dashBoardReportList;

                     if (vm.downloadType === "excel") {
                         if (response.data.result.fileName) {
                             vm.fileData = response.data.result.fileData;
                             vm.fileName = response.data.result.fileName;

                             saveAs(getBlobFromBase64String(vm.fileData), vm.fileName);
                             swal("Downloaded", response.data.message, "success");
                         }
                     }

                 }, function (error) {

                     vm.loader = false;

                     if (error.data.message == "No Data Found" || error.data.message == "NDF") {
                         vm.noDataFound = error.data.message;
                     } else {
                         handleErrorResponse(error);
                         $state.go("Dash_Board_Report_For_Inward_Outward.inquiry");
                     }

                 })
                 .finally(function () {
                     vm.downloadType = "";
                 });
                  }

                  else if(vm.downloadType === "csv") {
                  DashBoardReportForInwardOutwardService.getDetails(requestData).then(
                                      function (response) {
                                        vm.loader = false;
                                        vm.filteredRecord = response.data.result.DashBoardReportForInwardOutwardDTOList;
                                        if (vm.downloadType === "csv") {
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
                                          $state.go("Dash_Board_Report_For_Inward_Outward.inquiry");
                                        }
                                      }
                                    );
                  }
    };

vm.downloadPDF = function () {
  const { jsPDF } = window.jspdf;
  const doc = new jsPDF({ orientation: "landscape", unit: "mm", format: "a4" });
  doc.setFont("Times","normal");

  //Header details
  const additionalHeaders = [
    { text: "RBI Bond Balance Report", fontSize: 14, yPosition: 10 },
    { text: "Bank Name : INSPIRISY BANK LTD", fontSize: 12, yPosition: 17 },
    { text: "Sol ID : 101 / BRANCH OF THE BANK", fontSize: 12, yPosition: 22 },
  ];

  //Render additional headers
  additionalHeaders.forEach((header) => {
    const pageWidth = doc.internal.pageSize.width;
    const textWidth = doc.getTextWidth(header.text);
    const xPosition = (pageWidth - textWidth) / 2;
    doc.setFontSize(header.fontSize);
    doc.text(header.text, xPosition, header.yPosition);
  });

  //Summary Table
  const summaryTableData = [
    ["Name of the Bond Scheme", vm.schemeName],
    ["From Date", $filter("date")(vm.fromDate, "dd/MM/yyyy")],// From Date
    ["To Date", $filter("date")(vm.toDate, "dd/MM/yyyy")],// To Date
  ];

  doc.autoTable({
    body: summaryTableData,
    startY: 30,
    theme: "grid",
    styles: {
      fontSize: 10,
    },
    columnStyles: {
      0: { cellWidth: 70, halign: "left", fontStyle: "bold" }, // Label column
      1: { cellWidth: 110, halign: "left" }, // Value column
    },
  });

  // Main Table Data
  const tableData = vm.filteredRecords.map((record, index) => [
    index + 1,
    record.bondBlaNumber || "",
//    record.subscriptionId || "",
    record.bondType || "",
    $filter("date")(record.subscriptionDate, "dd/MM/yyyy"),
    $filter("date")(record.subscriptionMaturityDate, "dd/MM/yyyy"),
    record.currentBalance ? parseFloat(record.currentBalance).toFixed(2) : "0.00",
  ]);

  // Calculate total balance
  const totalAmount = vm.filteredRecords.reduce(
    (total, record) => total + parseFloat(record.currentBalance || 0),
    0
  );

  // Add total row
  tableData.push(["", "", "", "", "", "Grand Total (Rs)", totalAmount]);

  // Table Header
  const tableHeader = [
    "S.No.",
    "BLA No",
    "Subscription ID",
    "Bond Type",
    "Subscription Date",
    "Subscription Maturity Date",
    "Current Balance (Rs)",
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
    columnStyles: {
      0: { cellWidth: 20 }, // S.No.
      1: { cellWidth: 30 }, // BLA No
      2: { cellWidth: 40 }, // Subscription ID
      3: { cellWidth: 30 }, // Bond Type
      4: { cellWidth: 30 }, // Subscription Date
      5: { cellWidth: 35 }, // Maturity Date
      6: { cellWidth: 30, align: "right" }, // Current Balance
    },
  });

  // Save the PDF
  doc.save("RBI_Dash_Board_Report_For_Inward_Outward.pdf");
};





    function printpage() {
      window.print();
    }
    function getModuleName() {
      vm.loader = true;
      var requestData = {
        flag:'SC'
      };
      DashBoardReportForInwardOutwardService.getRbiBondModuleNameDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result.dashBoardReportList;
          angular.element("#schemeModel").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function getPanTorch() {
      var requestData = {
        schemeCode: vm.schemeCode,
        panNo: vm.panNo,
        name: vm.HolderName,
        flag: "P",
      };
      DashBoardReportForInwardOutwardService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.PanTorch = response.data.result.dashBoardReportList;
          angular.element("#panModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

    function getblaNo() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        panNo: vm.panNo,
        name: vm.HolderName,
        blaNo: vm.blaNum,
        flag: "B",
      };
      DashBoardReportForInwardOutwardService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaDataList = response.data.result.dashBoardReportList;
          angular.element("#blaModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

//    function getsubscriptionIdInq() {
//      vm.loader = true;
//
//      var requestData = {
//        schemeCode: vm.schemeCode,
//        panNo: vm.panNo,
//        name: vm.HolderName,
//        blaNo: vm.blaNum,
//        subsId: vm.subscriptionId,
//        flag: "S",
//      };
//      DashBoardReportForInwardOutwardService.getsubscriptionIdInq(requestData).then(
//        function (response) {
//          vm.loader = false;
//          vm.subscriptionList = response.data.result.DashBoardReportForInwardOutwardDTOList;
//
//          angular.element("#subscriptionModel").modal("toggle");
//        },
//        function (error) {
//          vm.loader = false;
//          handleErrorResponse(error);
//        }
//      );
//    }

    function getDashBoardReportForInwardOutward() {
      vm.loader = true;
      if (!vm.schemeCode || !vm.panNo) {
        vm.flag = "";
      }

      var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: $filter('date')(vm.fromDate,'dd-MMM-yyyy'),
        toDate: $filter('date')(vm.toDate,'dd-MMM-yyyy'),
        blaNo: vm.blaNum,
        panNo: vm.panNo,
        name: vm.HolderName,
//      subsId: vm.subscriptionId,
status:vm.status,
        flag: "D",
        pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchValue,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
      };

      DashBoardReportForInwardOutwardService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.model = response.data.result.dashBoardReportList;
          vm.filteredRecords = response.data.result.dashBoardReportList;
          vm.records = response.data.result.dashBoardReportList;
          vm.total = response.data.result.total;
          vm.inwardsCount=response.data.result.inwardsCount;
          vm.outwardsCount=response.data.result.outwardsCount;
          vm.pendingForOutwardsCount=response.data.result.pendingForOutwardsCount;
          vm.maxPages = Math.ceil(vm.total / vm.pageSize);
          vm.paginationControls();
          if (vm.model.length === 0) {
            vm.noDataFoundInSearch = true;
          } else {
            vm.noDataFoundInSearch = false;
          }
        },
        function (error) {
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
            $state.go("Dash_Board_Report_For_Inward_Outward.inquiry_details");
          }
        }
      );
    }

vm.maxValue = function () {
      return Math.min(vm.currentPage * vm.pageSize, vm.filteredRecords.length);
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

  }

})();
