(function () {
  "use strict";

  angular
    .module("gbmUltraApp")
    .controller(
      "pendingBrokeragePaymentClaimStatusController",
      pendingBrokeragePaymentClaimStatusController
    );

  pendingBrokeragePaymentClaimStatusController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$stateParams",
    "$location",
    "$rootScope",
    "pendingBrokeragePaymentClaimStatusService",
    "commonService",
  ];

  function pendingBrokeragePaymentClaimStatusController(
    $scope,
    $filter,
    $state,
    $stateParams,
    $location,
    $rootScope,
    pendingBrokeragePaymentClaimStatusService,
    commonService
  ) {
    var vm = this;

    vm.loginSolId = $rootScope.globals.currentUser.solId;
    vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
    vm.solDate = $rootScope.globals.currentUser.systemDate;

    vm.getblastatementforbondholdersReport =getblastatementforbondholdersReport;
    vm.getModuleName = getModuleName;
    vm.getBlaAccNo = getBlaAccNo;
    vm.BrokerTorch = BrokerTorch;
    vm.printpage = printpage;
    vm.bondholdername;
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





    vm.loginSolDate = $rootScope.globals.currentUser.solDate;
    vm.fromDateVal = fromDateVal;
    vm.toDateVal = toDateVal;

    //    Pagination Integration
    vm.goPreviousPage = goPreviousPage;
    vm.goNextPage = goNextPage;
    vm.paginationControls = paginationControls;
    vm.showNext = false;
    vm.showPrevious = false;
    $scope.sortReverse = false;

    $scope.sort = function (key) {
      $scope.sortKey = key;
      $scope.sortReverse =
        $scope.sortKey == key ? !$scope.sortReverse : $scope.sortReverse;
      $scope.sortReverse ? (vm.sortOrder = "DESC") : (vm.sortOrder = "ASC");
      vm.sortField = key;
      vm.getblastatementforbondholdersReport();
    };
    vm.pageNo = 1;
    vm.pageData = null;

    vm.records = [];
    vm.filteredRecords = [];
    vm.pageSizeOptions = [10, 25, 50, 100];
    vm.pageSize = vm.pageSizeOptions[0];
    vm.currentPage = 1;
    vm.maxPagesToShow = 5;
    vm.searchText = "";
    vm.loading = true;
    vm.showExportOptions = false;


    function goPreviousPage() {
      vm.pageNo = parseInt(vm.pageNo) - 1;
      vm.getblastatementforbondholdersReport();
    }

    function goNextPage() {
      vm.pageNo = parseInt(vm.pageNo) + 1;
      vm.getblastatementforbondholdersReport();
    }

    function paginationControls() {
      vm.pages = [];
      vm.maxPages = parseInt(vm.pageDetails.maxPages);
      for (var i = 1; i <= parseInt(vm.pageDetails.maxPages); i++) {
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
    }

    vm.setPage = function (page) {
      console.log("Page Number in setPage: " + page);
      if (page < 1) {
        vm.currentPage = 1;
      } else if (page > vm.totalPages) {
        vm.currentPage = vm.totalPages;
      } else {
        vm.currentPage = page;
      }
      console.log("vm.currentPage: " + vm.currentPage);
      vm.updatePagination();
    };

    vm.updatePagination = function () {
      try {
        var startPage, endPage;
        console.log("Inside updatePagination function...");

        if (vm.totalPages <= vm.maxPagesToShow) {
          startPage = 1;
          endPage = vm.totalPages;
        } else {
          var maxPagesBeforeCurrentPage = Math.floor(vm.maxPagesToShow / 2);
          var maxPagesAfterCurrentPage = Math.ceil(vm.maxPagesToShow / 2) - 1;

          if (vm.currentPage <= maxPagesBeforeCurrentPage) {
            startPage = 1;
            endPage = vm.maxPagesToShow;
          } else if (
            vm.currentPage + maxPagesAfterCurrentPage >=
            vm.totalPages
          ) {
            startPage = vm.totalPages - vm.maxPagesToShow + 1;
            endPage = vm.totalPages;
          } else {
            startPage = vm.currentPage - maxPagesBeforeCurrentPage;
            endPage = vm.currentPage + maxPagesAfterCurrentPage;
          }
        }

        console.log("vm.totalPages: " + vm.totalPages);
        console.log("startPage: " + startPage);
        console.log("endPage: " + endPage);

        vm.pages = [];
        for (var i = startPage; i <= endPage; i++) {
          vm.pages.push(i);
        }
        console.log("vm.pages: " + vm.pages);
      } catch (error) {
        console.error("An error occurred in updatePagination:", error);
      }
    };

    vm.updatePageSize = function () {
      vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
      console.log("totalPages: " + vm.totalPages);
      vm.setPage(1);
    };

    vm.prevPage = function () {
      vm.setPage(vm.currentPage - 1);
    };

    vm.nextPage = function () {
      vm.setPage(vm.currentPage + 1);
    };

    vm.firstPage = function () {
      vm.setPage(1);
    };

    vm.lastPage = function () {
      vm.setPage(vm.totalPages);
    };


vm.updateFilteredRecords = function () {
           const searchText = vm.searchText ? vm.searchText.toLowerCase() : '';
           vm.filteredRecords = vm.records.filter(record => {
               return Object.values(record).some(value =>
                   value !== null &&
                   value !== undefined &&
                   String(value).toLowerCase().includes(searchText)
               );
           });
           vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
           vm.setPage(1);
       };

    vm.downloadExcel1 = function () {
      var headers = [
        "S.No.",
        "Broker Code",
        "Broker Name",
        "Sum of Investment Amount",
        "Gross Brokerage",
        "Address 1",
        "Address 2",
        "City",
        "Pincode",
        "State",
        "Net Brokerage",
        "GST @ 18%",
        "Vendor Codes",
        "HSN Codes",
        "Status",
        "RCM Status",
        "Invoice Number",
        "Invoice Date",
        "VBMS",
        "Date",
        "Maker",
        "Credit Date",
        "Amount",
        "Difference",
        "Received On",
        "Rejected On",
        "POD",
        "RCM",
        "Remarks",
        "TAT",
        "CPU Remarks",
        "Remarks",
        "Reason",
        "Phone",
        "Email id",
        "Status",
        "Query Remarks",
      ];

      var additionalHeaders = [
        ["PENDING BROKERAGE PAYMENT STATUS REPORT"],
        ["Bank Name : INSPIRISY BANK LTD"],
        ["Sol ID : 101 / BRANCH OF THE BANK"],
        ["Name of the Bond Scheme: " + vm.schemeName],
        ["For the period from " + $filter("date")(vm.fromDate, "dd-MMM-yyyy")+ " to " + $filter("date")(vm.toDate, "dd-MMM-yyyy")],
        [],
      ];

      var rows = vm.result.map(function (row, index) {
        return [
          index + 1,
          row.brokerCode,
          row.brokerName,
          row.sumOfInvestmentAmountHeader1,
          row.sumOfGrossBrokerage,
          row.address1,
          row.address2,
          row.city,
          row.pinCode,
          row.state,
          row.netBrokerage,
          row.gst18Percent,
          row.vendorCodes,
          row.hsnCodes,
          row.status,
          row.rcmStatus,
          row.invoiceNumber,
          row.invoiceDate,
          row.vbms,
          row.date,
          row.maker,
          row.creditDate,
          row.amount,
          row.difference,
          row.receivedOn,
          row.rejectedOn,
          row.pod,
          row.rcm,
          row.remarks,
          row.tat,
          row.cpuRemarks,
          row.remarks,
          row.reason,
          row.phone,
          row.emailId,
          row.status,
          row.queryRemarks,
        ];
      });

      var allData = [...additionalHeaders, headers, ...rows];

      var worksheet = XLSX.utils.aoa_to_sheet(allData);
      var workbook = XLSX.utils.book_new();

      additionalHeaders.forEach((header, idx) => {
        const cell = worksheet[`A${idx + 1}`];
        if (cell) {
          cell.s = {
            font: { bold: true, sz: 14 },
            alignment: { horizontal: "center" },
          };
        }
      });

      worksheet["!merges"] = additionalHeaders.map((_, idx) => ({
        s: { r: idx, c: 0 },
        e: { r: idx, c: headers.length - 1 },
      }));
      XLSX.utils.book_append_sheet(workbook, worksheet, "Report");
      XLSX.writeFile(workbook, "PENDING_BROKERAGE_PAYMENT_STATUS_REPORT.xlsx");
    };


    vm.downloadExcel2 = function () {
        const additionalHeaders = [
            ["PENDING BROKERAGE CLAIM STATUS REPORT"],
            ["Bank Name: INSPIRISY BANK LTD"],
            ["Sol ID: 101 / BRANCH OF THE BANK"],
            ["Name of the Bond Scheme: " + vm.schemeName],
            ["For the period from " + $filter("date")(vm.fromDate, "dd-MMM-yyyy") + " to " + $filter("date")(vm.toDate, "dd-MMM-yyyy")],
            []
        ];

        const columnHeaders = [
            "S.No.", "BLA_NO", "INVESTMENT_AMOUNT", "CAS_TRANSFER_DATE", "MATURITY_DATE",
            "SCHEME", "CFD", "BRANCH_REF_NO", "ADVICE_NO", "APPLICANT_NAME",
            "ADDRESS1", "ADDRESS2", "CITY", "STATE", "PIN_CODE", "BRANCH_NAME",
            "RECEIPT_DATE", "GROUP_CODE", "HDFC_BROKER_CODE", "BROKER_NAME",
            "ADDRESS1", "ADDRESS2", "CITY", "STATE", "PINCODE", "PHONE",
            "PAYMENT_MODE", "ACCOUNT_TYPE_CODE", "BANK_ACCOUNT", "MICR_CODE",
            "APPLICANT_NO", "STATUS", "DE_STATUS", "BROKER_AMOUNT", "HDFC_BRANCH_CODE"
        ];

        const rows = vm.filteredRecords.map((row, index) => [
            index + 1, row.blaNo, row.investmentAmount, row.casTransferDate, row.maturityDate,
            row.schemeName, row.cfd, row.branchRefNo, row.adviceNo, row.applicantName,
            row.address1, row.address2, row.city, row.state, row.pinCode, row.branchName,
            row.receiptDate, row.groupCode, row.brokerCode, row.brokerName,
            row.address1, row.address2, row.city, row.state, row.pinCode, row.phone,
            row.paymentMode, row.accountTypeCode, row.bankAccount, row.micrCode,
            row.applicantNO, row.status, row.deStatus, row.brokerAmount, row.hdfcBranchCode
        ]);

        const worksheetData = [...additionalHeaders, columnHeaders, ...rows];
        const worksheet = XLSX.utils.aoa_to_sheet(worksheetData);
        const workbook = XLSX.utils.book_new();

        XLSX.utils.book_append_sheet(workbook, worksheet, "Report");
        XLSX.writeFile(workbook, "PENDING_BROKERAGE_CLAIM_STATUS_REPORT.xlsx");
    };



    vm.downloadPDF1 = function () {
      const { jsPDF } = window.jspdf;
      const doc = new jsPDF();
      doc.setFont("Times", "normal");

      const tableData = vm.filteredRecords.map((row, index) => [
        (vm.currentPage - 1) * vm.pageSize + index + 1,
        row.brokerCode,
        row.brokerName,
        row.sumOfInvestmentAmountHeader1,
        row.sumOfGrossBrokerage,
        row.address1,
        row.address2,
        row.city,
        row.pinCode,
        row.state,
        row.netBrokerage,
        row.gst18Percent,
        row.vendorCodes,
        row.hsnCodes,
        row.status,
        row.rcmStatus,
        row.invoiceNumber,
        row.invoiceDate,
        row.vbms,
        row.date,
        row.maker,
        row.creditDate,
        row.amount,
        row.difference,
        row.receivedOn,
        row.rejectedOn,
        row.pod,
        row.rcm,
        row.remarks,
        row.tat,
        row.cpuRemarks,
        row.remarks,
        row.reason,
        row.phone,
        row.emailId,
        row.status,
        row.queryRemarks,
      ]);

      const tableHeader = [
        "S.No.",
        "Broker Code",
        "Broker Name",
        "Sum of Investment Amount",
        "Gross Brokerage",
        "Address 1",
        "Address 2",
        "City",
        "Pincode",
        "State",
        "Net Brokerage",
        "GST @ 18%",
        "Vendor Codes",
        "HSN Codes",
        "Status",
        "RCM Status",
        "Invoice Number",
        "Invoice Date",
        "VBMS",
        "Date",
        "Maker",
        "Credit Date",
        "Amount",
        "Difference",
        "Received On",
        "Rejected On",
        "POD",
        "RCM",
        "Remarks",
        "TAT",
        "CPU Remarks",
        "Remarks",
        "Reason",
        "Phone",
        "Email id",
        "Status",
        "Query Remarks",
      ];

      const additionalHeaders = [
        {
          text: "PENDING BROKERAGE PAYMENT STATUS REPORT",
          fontSize: 14,
          yPosition: 10,
        },
        { text: "Bank Name : INSPIRISY BANK LTD", fontSize: 12, yPosition: 17 },
        {
          text: "Sol ID : 101 / BRANCH OF THE BANK",
          fontSize: 12,
          yPosition: 22,
        },
        {
          text: "For the period from " + $filter("date")(vm.fromDate, "dd-MMM-yyyy") + " to " + $filter("date")(vm.toDate, "dd-MMM-yyyy"),
          fontSize: 12,
          yPosition: 27,
        },
        {
          text: "Name of the Bond Scheme " + vm.schemeName,
          fontSize: 12,
          yPosition: 32,
        },
      ];

      additionalHeaders.forEach((header) => {
        const pageWidth = doc.internal.pageSize.width;
        const textWidth = doc.getTextWidth(header.text);
        const xPosition = (pageWidth - textWidth) / 2;
        doc.setFontSize(header.fontSize);
        doc.text(header.text, xPosition, header.yPosition);
      });

      doc.autoTable({
        head: [tableHeader],
        body: tableData,
        startY: 40,
        margin: { top: 20 },
        showHead: "firstPage",
      });
      doc.save("PENDING BROKERAGE PAYMENT STATUS REPORT.pdf");
    };

    vm.downloadPDF2 = function () {
      const { jsPDF } = window.jspdf;
      const doc = new jsPDF();
      doc.setFont("Times", "normal");

      const tableData = vm.filteredRecords.map((row, index) => [
        (vm.currentPage - 1) * vm.pageSize + index + 1,
        row.blaNo,
        row.investmentAmount,
        row.casTransferDate,
        row.maturityDate,
        row.schemeName,
        row.cfd,
        row.branchRefNo,
        row.adviceNo,
        row.applicantName,
        row.address1,
        row.address2,
        row.city,
        row.state,
        row.pinCode,
        row.branchName,
        row.receiptDate,
        row.groupCode,
        row.brokerCode,
        row.brokerName,
        row.address1,
        row.address2,
        row.city,
        row.state,
        row.pinCode,
        row.phone,
        row.paymentMode,
        row.accountTypeCode,
        row.bankAccount,
        row.micrCode,
        row.applicantNO,
        row.status,
        row.deStatus,
        row.brokerAmount,
        row.hdfcBranchCode,
      ]);

      const tableHeader = [
        "S.No.",
        "BLA_NO",
        "INVESTMENT_AMOUNT",
        "CAS_TRANSFER_DATE",
        "MATURITY_DATE",
        "SCHEME",
        "CFD",
        "BRANCH_REF_NO",
        "ADVICE_NO",
        "APPLICANT_NAME",
        "ADDRESS1",
        "ADDRESS2",
        "CITY",
        "STATE",
        "PIN_CODE",
        "BRANCH_NAME",
        "RECEIPT_DATE",
        "GROUP_CODE",
        "HDFC_BROKER_CODE",
        "BROKER_NAME",
        "ADDRESS1",
        "ADDRESS2",
        "CITY",
        "STATE",
        "PINCODE",
        "PHONE",
        "PAYMENT_MODE",
        "ACCOUNT_TYPE_CODE",
        "BANK_ACCOUNT",
        "MICR_CODE",
        "APPLICANT_NO",
        "STATUS",
        "DE_STATUS",
        "BROKER_AMOUNT",
        "HDFC_BRANCH_CODE",
      ];

      const additionalHeaders = [
        {
          text: "PENDING BROKERAGE CLAIM STATUS REPORT",
          fontSize: 14,
          yPosition: 10,
        },
        { text: "Bank Name : INSPIRISY BANK LTD", fontSize: 12, yPosition: 17 },
        {
          text: "Sol ID : 101 / BRANCH OF THE BANK",
          fontSize: 12,
          yPosition: 22,
        },
        {
          text: "For the period from " +$filter("date")(vm.fromDate, "dd-MMM-yyyy") + " to " + $filter("date")(vm.toDate, "dd-MMM-yyyy"),
          fontSize: 12,
          yPosition: 27,
        },
        {
          text: "Name of the Bond Scheme " + vm.schemeName,
          fontSize: 12,
          yPosition: 32,
        },
      ];

      additionalHeaders.forEach((header) => {
        const pageWidth = doc.internal.pageSize.width;
        const textWidth = doc.getTextWidth(header.text);
        const xPosition = (pageWidth - textWidth) / 2;
        doc.setFontSize(header.fontSize);
        doc.text(header.text, xPosition, header.yPosition);
      });

      doc.autoTable({
        head: [tableHeader],
        body: tableData,
        startY: 40,
        margin: { top: 20 },
        showHead: "firstPage",
      });
      doc.save("PENDING BROKERAGE CLAIM STATUS REPORT.pdf");
    };

    vm.downloadCsv1 = function () {

      var additionalHeaders = [
        "PENDING BROKERAGE PAYMENT STATUS REPORT",
        "Bank Name : INSPIRISY BANK LTD",
        "Sol ID : 101 / BRANCH OF THE BANK",
        "Name of the Bond Scheme: " + vm.schemeName,
        "For the period from " + $filter("date")(vm.fromDate, "dd-MMM-yyyy") + " to " +$filter("date")(vm.toDate, "dd-MMM-yyyy"),
        "",
      ];


var fixedColumnWidths = [
  5, // S.No.
  15, // Broker Code
  20, // Broker Name
  25, // Sum of Investment Amount
  20, // Gross Brokerage
  20, // Address 1
  20, // Address 2
  15, // City
  10, // Pincode
  15, // State
  20, // Net Brokerage
  15, // GST @ 18%
  20, // Vendor Codes
  15, // HSN Codes
  15, // Status
  15, // RCM Status
  20, // Invoice Number
  15, // Invoice Date
  10, // VBMS
  10, // Date
  15, // Maker
  15, // Credit Date
  15, // Amount
  15, // Difference
  15, // Received On
  15, // Rejected On
  10, // POD
  10, // RCM
  15, // Remarks
  10, // TAT
  20, // CPU Remarks
  15, // Remarks
  15, // Reason
  15, // Phone
  30, // Email id
  15, // Status
  20, // Query Remarks
];

var csvContent =
  "data:text/csv;charset=utf-8," +
  additionalHeaders.join("\n") +
  "\n" +
  [
    // Dynamically adjust header spacing
    [
      "S.No.",
      "Broker Code",
      "Broker Name",
      "Sum of Investment Amount",
      "Gross Brokerage",
      "Address 1",
      "Address 2",
      "City",
      "Pincode",
      "State",
      "Net Brokerage",
      "GST @ 18%",
      "Vendor Codes",
      "HSN Codes",
      "Status",
      "RCM Status",
      "Invoice Number",
      "Invoice Date",
      "VBMS",
      "Date",
      "Maker",
      "Credit Date",
      "Amount",
      "Difference",
      "Received On",
      "Rejected On",
      "POD",
      "RCM",
      "Remarks",
      "TAT",
      "CPU Remarks",
      "Remarks",
      "Reason",
      "Phone",
      "Email id",
      "Status",
      "Query Remarks",
    ]
      .map(function (header, index) {
        return header.padEnd(fixedColumnWidths[index]); // Adjust header width
      })
      .join(" | ") + "\n", // Add column borders
  ].join("") +
  vm.result
    .map(function (row, index) {
      return (
        row &&
        [ index + 1,
          row.brokerCode,
          row.brokerName,
          row.sumOfInvestmentAmountHeader1,
          row.sumOfGrossBrokerage,
          row.address1,
          row.address2,
          row.city,
          row.pinCode,
          row.state,
          row.netBrokerage,
          row.gst18Percent,
          row.vendorCodes,
          row.hsnCodes,
          row.status,
          row.rcmStatus,
          row.invoiceNumber,
          row.invoiceDate,
          row.vbms,
          row.date,
          row.maker,
          row.creditDate,
          row.amount,
          row.difference,
          row.receivedOn,
          row.rejectedOn,
          row.pod,
          row.rcm,
          row.remarks,
          row.tat,
          row.cpuRemarks,
          row.remarks,
          row.reason,
          row.phone,
          row.emailId,
          row.status,
          row.queryRemarks,
        ]
          .map(function (value, index) {
            var formattedValue = value
              ? value.toString().replace(/,/g, " ") // Remove commas
              : "";
            return formattedValue.padEnd(fixedColumnWidths[index]); // Adjust value width
          })
          .join(" | ") // Add borders between columns
      );
    })
    .join("\n");

      var encodedUri = encodeURI(csvContent);
      var link = document.createElement("a");
      link.setAttribute("href", encodedUri);
      link.setAttribute(
        "download",
        "PENDING_BROKERAGE_PAYMENT_STATUS_REPORT.txt"
      );

      document.body.appendChild(link);

      link.click();
      document.body.removeChild(link);
    };

vm.downloadCsv2 = function () {
  const additionalHeaders = [
    "PENDING BROKERAGE CLAIM STATUS REPORT",
    "Bank Name: INSPIRISY BANK LTD",
    "Sol ID: 101 / BRANCH OF THE BANK",
    "For the period from " +
      $filter("date")(vm.fromDate, "dd-MMM-yyyy") +
      " to " +
      $filter("date")(vm.toDate, "dd-MMM-yyyy"),
    "Name of the Bond Scheme: " + vm.schemeName,
    "",
  ];

  const fixedColumnWidths = [
    5,  // S.No.
    15, // BLA_NO
    20, // INVESTMENT_AMOUNT
    20, // CAS_TRANSFER_DATE
    20, // MATURITY_DATE
    42, // SCHEME
    10, // CFD
    15, // BRANCH_REF_NO
    25, // ADVICE_NO
    20, // APPLICANT_NAME
    20, // ADDRESS1
    20, // ADDRESS2
    15, // CITY
    15, // STATE
    10, // PIN_CODE
    15, // BRANCH_NAME
    15, // RECEIPT_DATE
    10, // GROUP_CODE
    15, // HDFC_BROKER_CODE
    20, // BROKER_NAME
    20, // ADDRESS1
    20, // ADDRESS2
    15, // CITY
    15, // STATE
    10, // PINCODE
    15, // PHONE
    15, // PAYMENT_MODE
    20, // ACCOUNT_TYPE_CODE
    20, // BANK_ACCOUNT
    15, // MICR_CODE
    10, // APPLICANT_NO
    10, // STATUS
    15, // DE_STATUS
    20, // BROKER_AMOUNT
    15, // HDFC_BRANCH_CODE
  ];

  const headerContent = additionalHeaders.join("\n");

  const columnHeaders = [
    "S.No.",
    "BLA_NO",
    "INVESTMENT_AMOUNT",
    "CAS_TRANSFER_DATE",
    "MATURITY_DATE",
    "SCHEME",
    "CFD",
    "BRANCH_REF_NO",
    "ADVICE_NO",
    "APPLICANT_NAME",
    "ADDRESS1",
    "ADDRESS2",
    "CITY",
    "STATE",
    "PIN_CODE",
    "BRANCH_NAME",
    "RECEIPT_DATE",
    "GROUP_CODE",
    "HDFC_BROKER_CODE",
    "BROKER_NAME",
    "ADDRESS1",
    "ADDRESS2",
    "CITY",
    "STATE",
    "PINCODE",
    "PHONE",
    "PAYMENT_MODE",
    "ACCOUNT_TYPE_CODE",
    "BANK_ACCOUNT",
    "MICR_CODE",
    "APPLICANT_NO",
    "STATUS",
    "DE_STATUS",
    "BROKER_AMOUNT",
    "HDFC_BRANCH_CODE",
  ]
    .map((header, index) => header.padEnd(fixedColumnWidths[index]))
    .join(" | ");

  const tableData = vm.filteredRecords.map((row, index) =>
    [
      index + 1,
      row.blaNo,
      row.investmentAmount,
      row.casTransferDate,
      row.maturityDate,
      row.schemeName,
      row.cfd,
      row.branchRefNo,
      row.adviceNo,
      row.applicantName,
      row.address1,
      row.address2,
      row.city,
      row.state,
      row.pinCode,
      row.branchName,
      row.receiptDate,
      row.groupCode,
      row.brokerCode,
      row.brokerName,
      row.address1,
      row.address2,
      row.city,
      row.state,
      row.pinCode,
      row.phone,
      row.paymentMode,
      row.accountTypeCode,
      row.bankAccount,
      row.micrCode,
      row.applicantNO,
      row.status,
      row.deStatus,
      row.brokerAmount,
      row.hdfcBranchCode,
    ]
      .map((value, index) => {
        const formattedValue = value
          ? value.toString().replace(/,/g, " ")
          : "";
        return formattedValue.padEnd(fixedColumnWidths[index]);
      })
      .join(" | ")
  );

  const csvContent =
    "data:text/csv;charset=utf-8," +
    headerContent +
    "\n" +
    columnHeaders +
    "\n" +
    tableData.join("\n");

  const encodedUri = encodeURI(csvContent);
  const link = document.createElement("a");
  link.setAttribute("href", encodedUri);
  link.setAttribute("download", "PENDING_BROKERAGE_CLAIM_STATUS_REPORT.txt");
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
};


    vm.handleDownloadOption = function () {
      if (vm.selectedOption === "excel") {
        if (vm.status == "P") {
          vm.downloadExcel1();
          vm.selectedOption= ""
        } else if (vm.status == "C") {
          vm.downloadExcel2();
          vm.selectedOption= ""
        }

      } else if (vm.selectedOption === "csv") {
        if (vm.status == "P") {
          vm.downloadCsv1();
          vm.selectedOption= ""
        } else if (vm.status == "C") {
          vm.downloadCsv2();
          vm.selectedOption= ""
        }
        vm.handleDownloadOption= ""
      } else if (vm.selectedOption == "pdf") {
        if (vm.status == "P") {
          vm.downloadPDF1();
        } else if (vm.status == "C") {
          vm.downloadPDF2();
        }
        vm.selectedOption= "";
      }
    };

    vm.goNextState = function (stateObj) {
      $state.go(vm.nextState, {
        obj: stateObj,
      });
    };
    vm.goPreviousState = function () {
      if ($state.current.name == "pending_brokerage_payment_status_brokerage_claim_status.inquiry_details") {
        $state.go(
          "pending_brokerage_payment_status_brokerage_claim_status.inquiry"
        );
      } else if (
        $state.current.name ==
        "pending_brokerage_payment_status_brokerage_claim_status.inquiry_details"
      ) {
        $state.go(
          "pending_brokerage_payment_status_brokerage_claim_status.inquiry"
        );
      } else if (
        $state.current.name ==
        "pending_brokerage_payment_status_brokerage_claim_status.inquiry_details"
      ) {
        $state.go(
          "pending_brokerage_payment_status_brokerage_claim_status.inquiry"
        );
      }
    };
    function cancel() {
      if (
        $state.current.name ==
        "pending_brokerage_payment_status_brokerage_claim_status.inquiry"
      ) {
        location.href = "#!/dc_rbi_bond_menu";
      } else if (
        $state.current.name ==
        "pending_brokerage_payment_status_brokerage_claim_status.inquiry"
      ) {
        location.href = "#!/rbibond_gu_functionality";
      } else if (
        $state.current.name ==
        "pending_brokerage_payment_status_brokerage_claim_status.inquiry"
      ) {
        location.href = "#!/rbibond_gu_functionality";
      }
    }
    if ($state.current.name == "pending_brokerage_payment_status_brokerage_claim_status.inquiry_details") {
      vm.fromDate = $stateParams.obj.fromDate;
      vm.toDate = $stateParams.obj.toDate;
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.status = $stateParams.obj.status;

      getblastatementforbondholdersReport();
    }

    function configDataTable() {
      vm.getTitleDescription = {
        title: "BLA statement for bondholders",
        solId: $rootScope.globals.currentUser.solId,
        solDesc: $rootScope.globals.currentUser.solDesc,
      };
      vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
    }

    function printpage() {
      window.print();
    }

    function getModuleName() {
      vm.loader = true;
      var requestData = {};
      pendingBrokeragePaymentClaimStatusService.getModule(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result;
          vm.schemeCode = response.data.result[0].subCode;

          console.log("schemeList", vm.schemeList);
          console.log("schemeCode", vm.schemeCode);
          angular.element("#schemeModel").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function getBlaAccNo() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
      };
      pendingBrokeragePaymentClaimStatusService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaAccNoList = response.data.result;
          angular.element("#blaAccNoModal").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    function BrokerTorch() {
      vm.loader = true;
      var requestData = {
        channelType: "B",
      };
      pendingBrokeragePaymentClaimStatusService
        .getValidationAdd(requestData)
        .then(
          function (response) {
            vm.loader = false;
            vm.brokerTorchList =
              response.data.result.bondBrokeragePaymentDtoList;

            console.log("brokertorchlist", vm.brokerTorchList);
            angular.element("#brokerModal").modal("toggle");
          },
          function (error) {
            vm.loader = false;
            handleErrorResponse(error);
          }
        );
    }

    vm.calculateTotalSum = function (resultList) {
      if (!Array.isArray(resultList) || resultList.length === 0) {
        return 0;
      }

      return resultList
        .reduce((totalSum, item) => {
          let value = item.sumOfInvestmentAmountHeader1;

          const num = parseFloat(value);
          return !isNaN(num) ? totalSum + num : totalSum;
        }, 0)
        .toFixed(2);
    };

    vm.calculateTotalGrossBrokerage = function (resultList) {
      if (!Array.isArray(resultList) || resultList.length === 0) {
        return 0;
      }

      return resultList
        .reduce((totalSum, item) => {
          let value = item.sumOfGrossBrokerage;

          const num = parseFloat(value);
          return !isNaN(num) ? totalSum + num : totalSum;
        }, 0)
        .toFixed(2);
    };

    function getblastatementforbondholdersReport() {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        status: vm.status,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),

        pageSize: vm.pageSize,
        pageNo: vm.pageNo,
        searchValue: vm.searchValue,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
      };

      pendingBrokeragePaymentClaimStatusService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;

          vm.model = response.data;
          vm.result = response.data.result.pendingBrokeragePaymentStatusDtoList;

          console.log("vm.result", vm.result);
          console.log("vm.model", vm.model);

          vm.filteredRecords = response.data.result.pendingBrokeragePaymentStatusDtoList;
          vm.records = response.data.result.pendingBrokeragePaymentStatusDtoList;
          vm.totalPages = Math.ceil(vm.result.length / vm.pageSize);
          vm.pageDetails = response.data.result.pendingBrokeragePaymentStatusDtoList;
          vm.updatePagination();

          configDataTable();
        },
        function (error) {
          vm.loader = false;
          console.log("error.data1", error.data);
          if (
            (error.data.message == "No Data Found") |
            (error.data.message == "NDF")
          ) {
            console.log("error.data2", error.data.message);
            vm.noDataFound = error.data.message;
          } else {
            console.log("error.data3", error.data.message);
            handleErrorResponse(error);
            $state.go(
              "pending_brokerage_payment_status_brokerage_claim_status.inquiry"
            );
          }
        }
      );
    }

       vm.maxValue = function () { return Math.min(vm.currentPage * vm.pageSize, vm.filteredRecords.length); };

            function fromDateVal() {
              vm.fromDateOptions = {
//                maxDate: new Date(),
                maxDate: vm.toDate ? new Date(vm.toDate) : new Date(),
                showWeeks: false,
              };
            }

            function toDateVal(fromDate) {
              vm.toDateOptions = {
//                minDate: fromDate,
                minDate: new Date(fromDate),
                maxDate: new Date(),
                showWeeks: false,
              };
            }
  }
})();
