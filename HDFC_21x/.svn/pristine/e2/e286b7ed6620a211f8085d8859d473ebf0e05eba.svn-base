(function () {
  "use strict";

  angular
    .module("gbmUltraApp")
    .controller("callbackReportController", callbackReportController);

  callbackReportController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$rootScope",
    "$stateParams",
    "$location",
    "$timeout",
    "callbackReportService",
    "commonService",
  ];

  function callbackReportController(
    $scope,
    $filter,
    $state,
    $rootScope,
    $stateParams,
    $location,
    $timeout,
    callbackReportService,
    commonService
  ) {
    var vm = this;
    vm.moduleName = "RBI Bond";

    vm.getModuleName = getModuleName;
    vm.getCallBackReportDetails = getCallBackReportDetails;

    vm.solDate = $rootScope.globals.currentUser.systemDate;
    vm.subsDate = moment(vm.solDate).format("DD-MMM-YYYY");
    vm.subsDate1 = moment(vm.solDate).format("DD-MM-YYYY");
    vm.solDate1 = $rootScope.globals.currentUser.solDate;

    vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
    vm.today = new Date();
    vm.loginSolDate = $rootScope.globals.currentUser.solDate;
    vm.loginSolId = $rootScope.globals.currentUser.solId;

    vm.fromDateVal = fromDateVal;
    vm.toDateVal = toDateVal;
   //vm.textableDatePicker=textableDatePicker;
    vm.autoslashing=autoslashing;

   /* function textableDatePicker(id,valueDate)
    {
     commonService.textableDatePicker(id,valueDate);
      if (id === 'effToDate') {
         if (!valueDate || isNaN(new Date(valueDate))) {
           vm.toDate = null;
           Swal.fire("Missing Date!", "Please select a valid date.", "warning");
           return false;
         } else if (!isWithin6Months(vm.fromDate, valueDate)) {
           vm.toDate = null;
           Swal.fire("Invalid Date!", "To Date cannot be more than 6 months after the From Date.", "error");
           return false;
         }
       }
       return true;
    }*/
    function isWithin6Months(fromDate, toDate) {
      const from = new Date(fromDate);
      const to = new Date(toDate);
      if (isNaN(from) || isNaN(to)) return false;
      const sixMonthsLater = new Date(from);
      sixMonthsLater.setMonth(sixMonthsLater.getMonth() + 6);
      return to <= sixMonthsLater && to >= from;
    }
    function autoslashing(id)
    {
     commonService.autoslashing(id);
    }
    vm.records = [];
    vm.filteredRecords = [];
    vm.filteredRecord = [];
    vm.currentPage = 1;
    vm.maxPagesToShow = 5;
    vm.searchValue = vm.searchValue || "";
    vm.loading = true;
    vm.showExportOptions = false;
    vm.itemsPerPage = "5";
            vm.goPreviousPage = goPreviousPage;
            vm.goNextPage = goNextPage;
            vm.paginationControls = paginationControls;
            vm.showNext = false;
            vm.showPrevious = false;
            $scope.sortReverse = false;
            $scope.sort = function(key){
            $scope.sortKey = key;
            $scope.sortReverse = ($scope.sortKey == key) ? !$scope.sortReverse : $scope.sortReverse;
            $scope.sortReverse ? vm.sortOrder = 'DESC' : vm.sortOrder = 'ASC';
            vm.sortField = key;
            vm.getCallBackReportDetails();
            }
            vm.pageSize = 5;
            vm.pageNo = 1;
                    function goPreviousPage() {
                     vm.pageNo = parseInt(vm.pageNo) - 1;
                     vm.getCallBackReportDetails();
                     }
                     function goNextPage() {
                     vm.pageNo = parseInt(vm.pageNo) + 1;
                     vm.getCallBackReportDetails();
                     }
                     function paginationControls () {
                     vm.pages = [];
                     for(var i=1; i<= parseInt(vm.maxPages); i++){
                        vm.pages.push(parseInt(i));
                      }
                     if(vm.pageNo != 1){
                         vm.showPrevious = true;
                     }
                     else{
                         vm.showPrevious = false;
                     }

                     if(vm.pageNo != vm.maxPages){
                          vm.showNext = true;
                     }
                     else{
                          vm.showNext = false;
                     }
                     }

$scope.formatDate = function (dateString) {
    if (!dateString) return ''; // Handle null or undefined date
    // Parse the input date in the format DD-MMM-YYYY
    const months = {
        JAN: '01', FEB: '02', MAR: '03', APR: '04', MAY: '05', JUN: '06',
        JUL: '07', AUG: '08', SEP: '09', OCT: '10', NOV: '11', DEC: '12'
    };

    const parts = dateString.split('-'); // Split into [day, month, year]
    if (parts.length !== 3) return ''; // Invalid date format

    const day = parts[0].padStart(2, '0'); // Ensure day is two digits
    const month = months[parts[1].toUpperCase()]; // Convert month to MM format
    const year = parts[2];

    if (!month) return ''; // Invalid month

    // Return the formatted date
    return `${day}/${month}/${year}`;
};

$scope.formatGender = function (gender) {
    if (!gender) return ''; // Handle undefined or null values
    const genderMap = {
        M: 'Male',
        F: 'Female',
        O: 'Others'
    };
    return genderMap[gender.toUpperCase()] || 'Unknown'; // Default to 'Unknown' if not matched
};

      if ($state.current.name == "callback_report.inquiry_details") {
          if ($stateParams.obj) {
            vm.loader = true;
            vm.dataList = [];
            vm.schemeName = $stateParams.obj.schemeName;
            vm.schemeCode = $stateParams.obj.schemeCode;
            vm.fromDate = $stateParams.obj.fromDate;
            vm.toDate = $stateParams.obj.toDate;
                vm.getCallBackReportDetails();
          }
        }

    vm.downloadPDF = function () {
      const { jsPDF } = window.jspdf;
      const doc = new jsPDF("l"); // Landscape mode for wide tables

      const additionalHeaders = [
        ["CALL BACK REPORT"],
        ["Bank Name : HDFC BANK LTD"],
        ["Sol ID : 101 / BRANCH OF THE BANK"],
        ["Name of the Bond Scheme : " + vm.schemeName],
        [
          "From Date : " +
            $filter("date")(vm.fromDate, "dd-MM-yyyy") +
            " To Date : " +
            $filter("date")(vm.toDate, "dd-MM-yyyy"),
        ],
      ];

      doc.autoTable({
        body: additionalHeaders,
        theme: "grid",
        styles: {
          fontSize: 12,
        },
        headStyles: {
          fontSize: 14,
        },
        startY: 10,
        margin: { left: 14 },
        didParseCell: function (data) {
          // Center-align the first row ("CALL BACK REPORT")
          if (data.row.index === 0) {
            data.cell.styles.halign = "center";
          }
        },
      });

      doc.autoTableSetDefaults({
        columnStyles: { 0: { halign: "left" } }, // Align text to the left
      });

      const tableHeader = [
        "Branch_Ref_No (Subscription Id)",
        "Bla_No",
        "Branch_Name",
        "Application No",
        "Scheme",
        "Investment_Date",
        "CAS Date",
        "Appl_Name1",
        "Gender",
        "DOB",
        "PAN_no",
        "Addr1",
        "Addr2",
        "City",
        "State",
        "Pin_Code",
        "Tele Phone No",
        "Mobile_No",
        "Email_Id",
        "Appl_Name2",
        "Pan_No2",
        "Appl_Name3",
        "Pan_No3",
        "Applicant_Status",
        "Mode_Holding",
        "Guardian_Name",
        "Mode of Payment",
        "Cheque Number",
        "Invest_Amt",
        "Account_No",
        "MIRCCODE_IFSCCODE",
        "Bank_name",
        "Nominee1",
        "Nominee2",
        "Nominee3",
        "Nominee4",
        "Nomination Ref. No.",
        "Status",
        "Maturity_Date",
        "Actual Paid date ( Maturity )",
        "Maturity_Amt",
        "Half Yearly Interest Amount",
        "Last_int_Paid_date ( Broken Interest )",
        "Net Paid ( Gross - TDS ) Acutal paid",
        "TDS @ 10%",
        "TDS_amt",
        "Exempt_Doc Status Yes / No",
        "Exempt_Doc Type",
        "Limit",
        "FY",
        "Age",
        "Whether assessed to tax",
        "Date on which Declaration is received",
        "Estimated income for which this declaration is made",
        "Estimated total income of the P.Y",
        "Total No. of Forms",
        "Aggregate amount of income",
        "Certificate No Low Tax / No Tax",
        "Exem Doc Maker Id",
        "Exem Doc Checker Id",
        "Last_Update_Date & Time",
        "Current_Status",
        "CIF",
        "Account_Type_Code",
        "Application/Data Entry Maker Id",
        "Application/Data Entry Checker ID",
        "appl_status",
        "DataEntry By HO",
        "Certificate No",
        "Certificate Type ( COH / COI )",
        "Section Code ( 193 )",
        "Reason for Non-deduction / Lower Deduction",
        "Interet Payout of the Year",
        "Deductee Code ( 02 )",
        "Pan Status Active / Inactive",
        "Filer / Non Filer",
        "Transfer cases Old Branch Ref Number",
        "Transfer cases Old Blas",
        "Transfer cases Old Customer Name",
        "Transfer cases Old Pan Number",
        "Bond Certificate Dispath Date",
        "Bond Certificate Email trigger date",
        "Bond Certificate Status",
        "Half Yearly Interest Advice Email trigger date",
        "Half Yearly Interest Advice Status",
        "Nominee DOB",
        "Nominee Relationship",
        "Nominee Sharing %",
        "If Nominee minor / Guardian details",
        "Co parceners details 1",
        "Co parceners details 2",
        "Co parceners details 3",
        "Co parceners 1 DOB",
        "Co parceners 1 Relationship",
        "Co parceners 2 DOB",
        "Co parceners 2 Relationship",
        "Co parceners 3 DOB",
        "Co parceners 3 Relationship",
        "Broker_code",
        "Broker_Name",
        "Address 1",
        "Address 2",
        "Location",
        "State",
        "Pin_Code",
        "Mobile Number",
        "Pan Number",
        "GST Number",
        "Account Number",
        "MIRCCODE_IFSCCODE",
        "Gross Brokerage",
        "NET Brokerage",
        "GST @ 18%",
        "Broker Code Status ( Active / Inactive )",
        "Brokerage letter - Email triggered status",
        "Brokerage Payment status",
        "POA",
      ];

      const tableData = vm.filteredRecord.map((row, index) => [
        row.branchRefNoSubsId,
        row.blaNo,
//        row.branchNo,
        row.branchName,
        row.applicationNo,
        row.schemeName,
        row.investmentDate,
        row.casdate,
        row.applicantName1,
        row.gender,
        row.dob,
        row.panNo,
        row.address1,
        row.address2,
        row.city,
        row.state,
        row.pincode,
        row.telephoneNo,
        row.mobileNo,
        row.emailId,
        row.applicantName2,
        row.panNo2,
        row.applicantName3,
        row.panNo3,
        row.applicantStatus,
        row.modeOfHolding,
        row.guardianName,
        row.modeOfPayment,
        row.chequeNumber,
        row.investmentAmount,
        row.accountNo,
        row.micrCode,
        row.bankName,
        row.nominee1,
        row.nominee2,
        row.nominee3,
        row.nominee4,
        row.nominationRefNo,
        row.status,
        row.maturityDate,
        row.actualPaidDate,
        row.maturityAmount,
        row.halfYearlyInterestAmount,
        row.lastIntrPaidDateBrokenInterest,
        row.netPaidGrossTDSAcutalPaid,
        row.tds_At_10Percent,
        row.tds_amt,
        row.exempt_Doc_Status_Yes_No,
        row.exempt_Doc_Type,
        row.limit,
        row.fy,
        row.age,
        row.whether_assessed_to_tax,
        row.date_on_which_Declaration_is_received,
        row.estimated_income_for_which_this_declaration_is_ma,
        row.estimated_total_income_of_the_P_Y,
        row.total_No__of_Forms,
        row.aggregate_amount_of_income,
        row.certificate_No_Low_Tax___No_Tax,
        row.exem_Doc_Maker_Id,
        row.exem_Doc_Checker_Id,
        row.last_Update_Date_And_Time,
        row.current_Status,
        row.cif,
        row.account_Type_Code,
        row.application_Data_Entry_Maker_id,
        row.application_Data_Entry_Checker_ID,
        row.appl_status,
        row.dataEntry_By_HO,
        row.certificate_No,
        row.certificate_Type_COH__COI,
        row.section_Code_193,
        row.reason_for_Non_deduction_Lower_Deduction,
        row.interet_Payout_of_the_Year,
        row.deductee_Code_02,
        row.pan_Status_Active_Inactive,
        row.filer_Non_Filer,
        row.transfer_cases_Old_Branch_Ref_Number,
        row.transfer_cases_Old_Blas,
        row.transfer_cases_Old_Customer_Name,
        row.transfer_cases_Old_Pan_Number,
        row.bond_Certificate_Dispatch_Date,
        row.bond_Certificate_Email_trigger_date,
        row.bond_Certificate_Status,
        row.half_Yearly_Interest_Advice_Email_trigger_date,
        row.half_Yearly_Interest_Advice_Status,
        row.nominee_DOB,
        row.nominee_Relationship,
        row.nominee_Sharing_Percent,
        row.if_Nominee_minor_Guardian_details,
        row.co_parceners_details_1,
        row.co_parceners_details_2,
        row.co_parceners_details_3,
        row.co_parceners_1_DOB,
        row.co_parceners_1_Relationship,
        row.co_parceners_2_DOB,
        row.co_parceners_2_Relationship,
        row.co_parceners_3_DOB,
        row.co_parceners_3_Relationship,
        row.broker_code,
        row.broker_Name,
        row.address_1,
        row.address_2,
        row.location,
        row.state,
        row.pin_Code,
        row.mobile_Number,
        row.pan_Number,
        row.gst_Number,
        row.account_Number,
        row.mirccode_IFSCCODE,
        row.gross_Brokerage,
        row.net_Brokerage,
        row.gst_At_18Percent,
        row.broker_Code_Status_Active_Inactive,
        row.brokerage_letter_Email_triggered_status,
        row.brokerage_Payment_status,
        row.poa,
      ]);

      const chunkSize = 10;
      const columnGroups = [];
      for (let i = 0; i < tableHeader.length; i += chunkSize) {
        columnGroups.push(tableHeader.slice(i, i + chunkSize));
      }

      const renderTableChunks = (doc, data, columnGroups, startY) => {
        let currentY = startY;

        for (let rowIndex = 0; rowIndex < data.length; rowIndex += 10) {
          const rowChunk = data.slice(rowIndex, rowIndex + 10); // Get next 10 rows

          columnGroups.forEach((columns, groupIndex) => {
            const body = rowChunk.map((row) =>
              columns.map((header) => row[tableHeader.indexOf(header)])
            );

            doc.autoTable({
              head: [columns], // Current chunk of headers
              body: body, // Row data corresponding to current chunk
              startY: currentY, // Print continuously from currentY
              styles: {
                fontSize: 7,
                cellPadding: 1,
                overflow: "linebreak",
              },
              headStyles: {
                fillColor: [230, 230, 250],
                textColor: [0, 0, 0],
                fontStyle: "bold",
                fontSize: 7,
              },
              theme: "grid",
              pageBreak: "auto",
              rowPageBreak: "avoid",
              showHead: "firstPage",
            });

            currentY = doc.lastAutoTable.finalY + 15;
          });
        }
      };

      const startY = 60;
      renderTableChunks(doc, tableData, columnGroups, startY);

      doc.save("CALL_BACK_REPORT.pdf");
    };
vm.indianFormat = function (input) {
       if (!input) return "";
        // Convert input to a number
        var num = parseFloat(input);
        if (isNaN(num)) return input;
        // Format number in Indian style
      return num.toLocaleString('en-IN', {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    });
        };
    vm.downloadCsv = function () {
      const columnWidth = 50;

      const tableHeader = [
        "Branch_Ref_No (Subscription Id)",
        "Bla_No",
        "Branch_Name",
        "Application No",
        "Scheme",
        "Investment_Date",
        "CAS Date",
        "Appl_Name1",
        "Gender",
        "DOB",
        "PAN_no",
        "Addr1",
        "Addr2",
        "City",
        "State",
        "Pin_Code",
        "Tele Phone No",
        "Mobile_No",
        "Email_Id",
        "Appl_Name2",
        "Pan_No2",
        "Appl_Name3",
        "Pan_No3",
        "Applicant_Status",
        "Mode_Holding",
        "Guardian_Name",
        "Mode of Payment",
        "Cheque Number",
        "Invest_Amt",
        "Account_No",
        "MIRCCODE_IFSCCODE",
        "Bank_name",
        "Nominee1",
        "Nominee2",
        "Nominee3",
        "Nominee4",
        "Nomination Ref. No.",
        "Status",
        "Maturity_Date",
        "Actual Paid date ( Maturity )",
        "Maturity_Amt",
        "Half Yearly Interest Amount",
        "Last_int_Paid_date ( Broken Interest )",
        "Net Paid ( Gross - TDS ) Acutal paid",
        "TDS @ 10%",
        "TDS_amt",
        "Exempt_Doc Status Yes / No",
        "Exempt_Doc Type",
        "Limit",
        "FY",
        "Age",
        "Whether assessed to tax",
        "Date on which Declaration is received",
        "Estimated income for which this declaration is made",
        "Estimated total income of the P.Y",
        "Total No. of Forms",
        "Aggregate amount of income",
        "Certificate No Low Tax / No Tax",
        "Exem Doc Maker Id",
        "Exem Doc Checker Id",
        "Last_Update_Date & Time",
        "Current_Status",
        "CIF",
        "Account_Type_Code",
        "Application/Data Entry Maker Id",
        "Application/Data Entry Checker ID",
        "appl_status",
        "DataEntry By HO",
        "Certificate No",
        "Certificate Type ( COH / COI )",
        "Section Code ( 193 )",
        "Reason for Non-deduction / Lower Deduction",
        "Interet Payout of the Year",
        "Deductee Code ( 02 )",
        "Pan Status Active / Inactive",
        "Filer / Non Filer",
        "Transfer cases Old Branch Ref Number",
        "Transfer cases Old Blas",
        "Transfer cases Old Customer Name",
        "Transfer cases Old Pan Number",
        "Bond Certificate Dispath Date",
        "Bond Certificate Email trigger date",
        "Bond Certificate Status",
        "Half Yearly Interest Advice Email trigger date",
        "Half Yearly Interest Advice Status",
        "Nominee DOB",
        "Nominee Relationship",
        "Nominee Sharing %",
        "If Nominee minor / Guardian details",
        "Co parceners details 1",
        "Co parceners details 2",
        "Co parceners details 3",
        "Co parceners 1 DOB",
        "Co parceners 1 Relationship",
        "Co parceners 2 DOB",
        "Co parceners 2 Relationship",
        "Co parceners 3 DOB",
        "Co parceners 3 Relationship",
        "Broker_code",
        "Broker_Name",
        "Address 1",
        "Address 2",
        "Location",
        "State",
        "Pin_Code",
        "Mobile Number",
        "Pan Number",
        "GST Number",
        "Account Number",
        "MIRCCODE_IFSCCODE",
        "Gross Brokerage",
        "NET Brokerage",
        "GST @ 18%",
        "Broker Code Status ( Active / Inactive )",
        "Brokerage letter - Email triggered status",
        "Brokerage Payment status",
        "POA",
      ];

      function centerAlign(text, width) {
        if (text.length > width) {
          text = text.slice(0, width - 3) + "...";
        }

        const padding = width - text.length;
        const leftPadding = Math.floor(padding / 2);
        const rightPadding = padding - leftPadding;
        return " ".repeat(leftPadding) + text + " ".repeat(rightPadding);
      }

      function splitIntoLines(text, width) {
        const words = text.split(" ");
        const lines = [];
        let currentLine = "";

        words.forEach((word) => {
          if ((currentLine + word).length > width) {
            currentLine = word + " ";
            lines.push(centerAlign(currentLine.trim(), width));
          } else {
            currentLine += word + " ";
          }
        });

        if (currentLine.trim()) {
          lines.push(centerAlign(currentLine.trim(), width));
        }

        return lines;
      }

      const wrappedHeaders = tableHeader.map((header) =>
        splitIntoLines(header, columnWidth)
      );
      const maxHeaderLines = Math.max(
        ...wrappedHeaders.map((lines) => lines.length)
      );

      const headerRows = [];
      for (let i = 0; i < maxHeaderLines; i++) {
        const row = wrappedHeaders
          .map((lines) => lines[i] || centerAlign("", columnWidth))
          .join(" | ");
        headerRows.push("| " + row + " |");
      }

      const separatorLine =
        "+" +
        tableHeader.map(() => "-".repeat(columnWidth + 2)).join("+") +
        "+";

      const tableData = vm.filteredRecords.map(
        (record) =>
          "| " +
          [
            record.branchRefNoSubsId || "",
            record.blaNo || "",
//            record.branchNo || "",
            record.branchName || "",
            record.applicationNo || "",
            record.schemeName || "",
            record.investmentDate || "",
            record.casdate || "",
            record.applicantName1 || "",
            record.gender || "",
            record.dob || "",
            record.panNo || "",
            record.address1 || "",
            record.address2 || "",
            record.city || "",
            record.state1 || "",
            record.pincode || "",
            record.telephoneNo || "",
            record.mobileNo || "",
            record.emailId || "",
            record.applicantName2 || "",
            record.panNo2 || "",
            record.applicantName3 || "",
            record.panNo3 || "",
            record.applicantStatusDescription || "",
            record.modeOfHoldingDescription || "",
            record.guardianName || "",
            record.modeOfPaymentDescription || "",
            record.chequeNumber || "",
            record.investmentAmount || "",
            record.accountNo || "",
            record.micrCode || "",
            record.bankName || "",
            record.nominee1 || "",
            record.nominee2 || "",
            record.nominee3 || "",
            record.nominee4 || "",
            record.nominationRefNo || "",
            record.applicantStatusDescription || "",
            record.maturityDate || "",
            record.actualPaidDate || "",
            record.maturityAmount || "",
            record.halfYearlyInterestAmount || "",
            record.lastIntrPaidDateBrokenInterest || "",
            record.netPaidGrossTDSAcutalPaid || "",
            record.tds_At_10Percent || "",
            record.tds_amt || "",
            record.exempt_Doc_Status_Yes_No || "",
            record.exempt_Doc_Type || "",
            record.limit || "",
            record.fy || "",
            record.age || "",
            record.whether_assessed_to_tax || "",
            record.date_on_which_Declaration_is_received || "",
            record.estimated_income_for_which_this_declaration_is_ma || "",
            record.estimated_total_income_of_the_P_Y || "",
            record.total_No__of_Forms || "",
            record.aggregate_amount_of_income || "",
            record.certificate_No_Low_Tax___No_Tax || "",
            record.exem_Doc_Maker_Id || "",
            record.exem_Doc_Checker_Id || "",
            record.last_Update_Date_And_Time || "",
            record.current_Status || "",
            record.cif || "",
            record.accountTypeDescription || "",
            record.application_Data_Entry_Maker_id || "",
            record.application_Data_Entry_Checker_ID || "",
            record.appl_status || "",
            record.dataEntry_By_HO || "",
            record.certificate_No || "",
            record.certificate_Type_COH__COI || "",
            record.section_Code_193 || "",
            record.reason_for_Non_deduction_Lower_Deduction || "",
            record.interet_Payout_of_the_Year || "",
            record.deductee_Code_02 || "",
            record.pan_Status_Active_Inactive || "",
            record.filer_Non_Filer || "",
            record.transfer_cases_Old_Branch_Ref_Number || "",
            record.transfer_cases_Old_Blas || "",
            record.transfer_cases_Old_Customer_Name || "",
            record.transfer_cases_Old_Pan_Number || "",
            record.bond_Certificate_Dispatch_Date || "",
            record.bond_Certificate_Email_trigger_date || "",
            record.bond_Certificate_Status || "",
            record.half_Yearly_Interest_Advice_Email_trigger_date || "",
            record.half_Yearly_Interest_Advice_Status || "",
            record.nominee_DOB1 || "",
            record.nomineeRelationshipDescription || "",
            record.nominee_Sharing_Percent1 || "",
            record.if_Nominee_minor_Guardian_details || "",
            record.co_parceners_details_1 || "",
            record.co_parceners_details_2 || "",
            record.co_parceners_details_3 || "",
            record.co_parceners_1_DOB || "",
            record.co_parceners_1_Relationship || "",
            record.co_parceners_2_DOB || "",
            record.co_parceners_2_Relationship || "",
            record.co_parceners_3_DOB || "",
            record.co_parceners_3_Relationship || "",
            record.broker_code || "",
            record.broker_Name || "",
            record.address_1 || "",
            record.address_2 || "",
            record.location || "",
            record.state2 || "",
            record.pin_Code || "",
            record.mobile_Number || "",
            record.pan_Number || "",
            record.gst_Number || "",
            record.account_Number || "",
            record.mirccode_IFSCCODE || "",
            record.gross_Brokerage || "",
            record.net_Brokerage || "",
            record.gst_At_18Percent || "",
            record.broker_Code_Status_Active_Inactive || "",
            record.brokerage_letter_Email_triggered_status || "",
            record.brokerage_Payment_status || "",
            record.poa || "",
          ]
            .map((value) => centerAlign(value.toString(), columnWidth))
            .join(" | ") +
          " |"
      );

      const formatDate = (date, withDay = false) => {
        const d = new Date(date);
        const options = { day: "2-digit", month: "2-digit", year: "numeric" };
        return d.toLocaleDateString("en-GB", options).replace(/\//g, "/");
      };

//      const textData = [
//        "Call Back Report",
//        "Bank Name: HDFC BANK LTD",
//        "Sol ID: 101 / BRANCH OF THE BANK",
//        "Name of the Bond Scheme: " + vm.schemeName,
//        "Account Opened Date from " +
//          formatDate(vm.fromDate) +
//          " to " +
//          formatDate(vm.toDate),
//        "",
//        separatorLine,
//        ...headerRows, // Add the multi-line header rows
//        separatorLine,
//        ...tableData,
//        separatorLine, // Add a bottom border
//      ].join("\n");

const textData = [
  "Call Back Report",
  "Bank Name: HDFC BANK LTD",
  "Sol ID: 101 / BRANCH OF THE BANK",
  "Name of the Bond Scheme: " + vm.schemeName,
  "Account Opened Date from " +
    formatDate(vm.fromDate) +
    " to " +
    formatDate(vm.toDate),
  "",
  separatorLine,
  headerRows.join("\n"), // Add the multi-line header rows as a single instance
  separatorLine,
  ...tableData,
  separatorLine, // Add a bottom border
].join("\n");


      const blob = new Blob([textData], { type: "text/plain" });
      const link = document.createElement("a");
      link.href = URL.createObjectURL(blob);
      link.download = "CALL_BACK_REPORT.txt";
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    };

    function getModuleName() {
      vm.loader = true;
      var requestData = {};

      callbackReportService.getBrokerageInvoiceBondScheme(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result.callbackreportDtoList;
          console.log("schemeList", vm.schemeList);
          angular.element("#schemeModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

vm.getDownload=getDownload;
function getDownload() {
    vm.loader = true;
    var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: moment(vm.fromDate).format("DD-MMM-YYYY"),
        toDate: moment(vm.toDate).format("DD-MMM-YYYY"),
        flag: 'D',
        pageNo : 1,
        pageSize: vm.total,
        searchValue:vm.searchValue
    };
    callbackReportService.getCallBackReportDownload(requestData).then(
        function (response) {
            vm.loader = false;
            vm.filteredRecord = response.data.result;
            if (vm.selectedOption === "excel") {
              if (response.data.result.fileName) {
                 vm.fileData = response.data.result.fileData;
                 vm.fileName = response.data.result.fileName;
                 saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
                 Swal.fire("Downloaded", response.data.message, "success");
            }
          } else if (vm.selectedOption === "csv") {
            vm.downloadCsv();
          }
    //       else if (vm.selectedOption == "pdf") {
    //        vm.downloadPDF();
    //      }
          vm.selectedOption = "";
            },function (error) {
           vm.loader = false;
           if ((error.data.message == "No Data Found") || (error.data.message == "NDF")) {
             vm.noDataFound = error.data.message;
           }else {
             handleErrorResponse(error);
             $state.go("callback_report.inquiry");
           }
          });
       }

function getCallBackReportDetails() {
    vm.loader = true;
    if(null!=vm.searchValue){
    var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: moment(vm.fromDate).format("DD-MMM-YYYY"),
        toDate: moment(vm.toDate).format("DD-MMM-YYYY"),
        flag: 'D',
        pageNo : vm.pageNo,
        pageSize:vm.pageSize,
        searchValue:vm.searchValue,
        sortOrder:vm.sortOrder,
        sortField:vm.sortField
    };
    }else{
    var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: moment(vm.fromDate).format("DD-MMM-YYYY"),
        toDate: moment(vm.toDate).format("DD-MMM-YYYY"),
        flag: 'D',
        pageNo : vm.pageNo,
        pageSize:vm.pageSize,
        searchValue:vm.searchValue,
        sortOrder:vm.sortOrder,
        sortField:vm.sortField
    };
   }

    // Fetch the first response
    callbackReportService.getCallBackReportDetails(requestData).then(
        function (response) {
            vm.loader = false;
            vm.filteredRecords = response.data.result.callbackreportDtoList;
            vm.records = response.data.result.callbackreportDtoList;
            vm.total = response.data.total;
            vm.maxPages =  Math.ceil(response.data.total/vm.pageSize);
            if (vm.records.length === 0) {
                vm.noDataFoundInSearch = true;
            } else {
                vm.noDataFoundInSearch = false;
            }
            vm.paginationControls();
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
                        $state.go("callback_report.inquiry");
                      }
        }
    );
}


    vm.dateToOptions = {
      maxDate: new Date(),
    };

    vm.toDatePicker = {
      opened: false,
    };

    vm.maxValue = function () {
      return Math.min(vm.currentPage * vm.pageSize, vm.filteredRecords.length);
    };

    function fromDateVal() {
      vm.fromDateOptions = {
        maxDate: vm.toDate ? new Date(vm.toDate) : new Date(),
        showWeeks: false,
      };
    }

     function toDateVal() {
          vm.toDateOptions = {
            maxDate: vm.toDate ? new Date(vm.toDate) : new Date(),
            showWeeks: false,
          };
        }

/* function toDateVal(fromDate) {
     var sixMonthsFromFromDate = new Date(fromDate);
     sixMonthsFromFromDate.setMonth(sixMonthsFromFromDate.getMonth() + 6);

     var today = new Date();
     today.setHours(0, 0, 0, 0);

     // Set max date to either 6 months from fromDate or today — whichever is earlier
     var maxToDate = (sixMonthsFromFromDate < today) ? sixMonthsFromFromDate : today;
     vm.toDateOptions = {
         minDate: new Date(fromDate),
         maxDate: maxToDate,
         showWeeks: false,
     };
if (vm.toDate && vm.toDate > maxToDate) {
    vm.toDate = null;
     }
    }*/
  }
})();