(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("brokerageInvoiceAndEmailBodyLetterController", brokerageInvoiceAndEmailBodyLetterController);

  brokerageInvoiceAndEmailBodyLetterController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "$timeout", "brokerageInvoiceAndEmailBodyLetterService", "commonService"];

  function brokerageInvoiceAndEmailBodyLetterController($scope, $filter, $state, $stateParams, $location, $rootScope, $timeout, brokerageInvoiceAndEmailBodyLetterService, commonService) {
    var vm = this;
    $scope.vm = vm;

    vm.moduleName = "RBI Bond";

    vm.textableDatePicker = textableDatePicker;
    vm.autoslashing = autoslashing;

    function textableDatePicker(id, valueDate) {
      commonService.textableDatePicker(id, valueDate);
    }
    function autoslashing(id) {
      commonService.autoslashing(id);
    }

    vm.getModuleName = getModuleName;
    vm.brokerAgencyCode = brokerAgencyCode;
    vm.getDetails = getDetails;

    vm.solDate = $rootScope.globals.currentUser.systemDate;
    vm.subsDate = moment(vm.solDate).format("DD-MMM-YYYY");
    vm.subsDate1 = moment(vm.solDate).format("DD-MM-YYYY");
    vm.solDate1 = $rootScope.globals.currentUser.solDate;

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

    //    vm.handleDownloadOption = function () {
    //      if (vm.selectedOption === "excel") {
    //        vm.downloadExcel();
    //      } else if (vm.selectedOption === "csv") {
    //        vm.downloadCsv();
    //      } else if (vm.selectedOption == "pdf") {
    //        vm.downloadPdf();
    //      }
    //    };

    //Pagination Ends
    //
    //    function generatePDF(data) {
    //      const { jsPDF } = window.jspdf;
    //      const doc = new jsPDF();
    //
    //      const header = "Brokerage Invoice and Email Body Letter";
    //
    //      data.forEach((entry, index) => {
    //        // Add a new page for each entry
    //        if (index > 0) {
    //          doc.addPage();
    //        }
    //
    //        // Add header
    //        doc.setFontSize(16);
    //        doc.text(header, 10, 10);
    //
    //        // Add invoice details
    //        doc.setFontSize(12);
    //        doc.text(`Name of the Broker: ${entry.brokerName}`, 10, 30);
    //        doc.text(`Broker Code: ${entry.brokerCode}`, 10, 40);
    //        doc.text(`Vendor Code: ${entry.brokerCode}`, 10, 40);
    //        doc.text(`Invoice No: ${entry.invoiceNo}`, 10, 50);
    //        doc.text(`Invoice Date: ${entry.invoiceDate}`, 10, 60);
    //
    //        doc.text(`Brokerage Commission: Rs. ${entry.grossBrokerage}`, 10, 80);
    //        doc.text(`CGST 9%: 0.00`, 10, 90);
    //        doc.text(`SGST 9%: 0.00`, 10, 100);
    //        doc.text(`IGST 18%: 0.00`, 10, 110);
    //        doc.text(`Total Invoice Value in Figures: Rs. ${entry.netBrokerage}`, 10, 120);
    //        doc.text("Total Invoice Value in Words:", 10, 130);
    //        doc.text("Broker Seal and Sign", 10, 140);
    //
    //        // Add address details
    //        doc.setFontSize(10);
    //        doc.text("REGISTERED OFFICE: HDFC BANK LTD SENAPATI BAPAT MARG,", 10, 160);
    //        doc.text("LOWER PAREL W, MUMBAI-400013.", 10, 170);
    //        doc.text("BRANCH OFFICE: HDFC Bank Ltd CMS-GOI BONDS - 2ND FLOOR,", 10, 180);
    //        doc.text("GK SHETTY TECHNOLOGY PARK, WAVIN MAIN ROAD,", 10, 190);
    //        doc.text("MOGAPPAIR, CHENNAI - 600058.", 10, 200);
    //        doc.text("GST IN - 27AAAXXXXXH1Z0, PAN - AAAXXXXXH", 10, 210);
    //      });
    //
    //      // Save the PDF
    //      doc.save("Brokerage_Invoice.pdf");
    //    }

    //-------------------------------==================================

    /*    function printpage() {
      if (vm.invoiceType == "invoice") {
        vm.selectedCustomers = angular.copy(vm.records);
        const printContainer = document.getElementById("printContainer1");
        printContainer.style.display = "block";

        setTimeout(() => {
          window.print();
          printContainer.style.display = "none";
        }, 100);
      } else if (vm.invoiceType == "email") {
        vm.selectedCustomers = angular.copy(vm.records);
        const printContainer = document.getElementById("printContainer2");
        printContainer.style.display = "block";

        setTimeout(() => {
          window.print();
          printContainer.style.display = "none";
        }, 100);
      }
    }

    vm.downloadCustomerRecord = function (customer) {
      vm.selectedCustomers = [customer];
      const printContainer = document.getElementById("printContainer");
      printContainer.style.display = "block";

      setTimeout(() => {
        window.print();
        printContainer.style.display = "none";
      }, 100);
    };*/

    if ($state.current.name == "brokerage_invoice_and_email_body_letter.inquiry_details") {
      vm.loader = true;
      vm.dataList = [];

      vm.fromDate = $stateParams.obj.fromDate;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.brokerId = $stateParams.obj.brokerId;
      vm.invoiceType = $stateParams.obj.invoiceType;
      vm.toDate = $stateParams.obj.toDate;
      vm.blaNo = $stateParams.obj.blaNo;
      vm.getDetails();
    }

    function getModuleName() {
      vm.loader = true;
      var requestData = {
        flag: "S",
      };

      brokerageInvoiceAndEmailBodyLetterService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result.list;
          console.log("schemeList", vm.schemeList);
          angular.element("#schemeModel").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    vm.getInvoiceType = function () {
      vm.loader = true;
      var requestData = {
        flag: "I",
      };

      brokerageInvoiceAndEmailBodyLetterService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.InvoiceType = response.data.result.list;
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
        invoiceType: vm.invoiceType,
        brokerCode: vm.brokerId,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "D",
        pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchText,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
      };

      brokerageInvoiceAndEmailBodyLetterService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.records = response.data.result.list;
          vm.filteredRecords = response.data.result.list;
          vm.total = response.data.total;
          vm.maxPages = Math.ceil(response.data.total / vm.pageSize);
          vm.paginationControls();
          if (vm.records.length === 0) {
            vm.noDataFoundInSearch = true;
          } else {
            vm.noDataFoundInSearch = false;
          }
        },
        function (error) {
          vm.loader = false;
          if ((error.data.message == "No Data Found" || error.data.message == "NDF") && vm.searchText) {
                 vm.pageNo ='0';
                  vm.maxPages ='0';
                  vm.total ='0';
                   vm.showNext = false;
            vm.noDataFoundInSearch = true;
          } else if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("brokerage_invoice_and_email_body_letter.inquiry");
          }
        }
      );
    }

    function brokerAgencyCode() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        brokerCode: vm.brokerId,
        flag: "AC",
      };

      brokerageInvoiceAndEmailBodyLetterService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.brokerTorchList = response.data.result.list;
          angular.element("#brokerModal").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }

   vm.fromDateVal= function () {
      vm.fromDateOptions = {
        maxDate: new Date(),
        showWeeks: false,
      };
    }

    vm.toDateVal=function (fromDate) {
      vm.toDateOptions = {
        minDate: fromDate,
        maxDate: new Date(),
        showWeeks: false,
      };
    }

    vm.numberToWords = function (num) {
      const a = ["", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"];
      const b = ["", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"];

      if ((num = num.toString()).length > 9) return "overflow";

      let n = ("000000000" + num).substr(-9).match(/^(\d{2})(\d{2})(\d{2})(\d{1})(\d{2})$/);
      if (!n) return;

      let str = "";
      str += n[1] != 0 ? (a[Number(n[1])] || b[n[1][0]] + " " + a[n[1][1]]) + " crore " : "";
      str += n[2] != 0 ? (a[Number(n[2])] || b[n[2][0]] + " " + a[n[2][1]]) + " lakh " : "";
      str += n[3] != 0 ? (a[Number(n[3])] || b[n[3][0]] + " " + a[n[3][1]]) + " thousand " : "";
      str += n[4] != 0 ? (a[Number(n[4])] || b[n[4][0]] + " " + a[n[4][1]]) + " hundred " : "";
      str += n[5] != 0 ? (str != "" ? "and " : "") + (a[Number(n[5])] || b[n[5][0]] + " " + a[n[5][1]]) : "";
      return str.trim();
    };

    vm.downloadInvoice = function (invoiceDetails) {
      if (!invoiceDetails) {
        alert("No record selected for download.");
        return;
      }
      if (vm.invoiceType == "INVOICE") {
        vm.netBrokerageValue = Number(invoiceDetails.netBrokerage || 0);
        vm.netBrokerageWord = vm.numberToWords(vm.netBrokerageValue);

        loadImageAsBase64("assets/images/HDFC-Logo.png", function (imgData) {
          if (!imgData) {
            console.error("Failed to load the logo. Proceeding without it.");
          }

          const { jsPDF } = window.jspdf;
          const pdf = new jsPDF();

          const marginLeft = 10;
          let currentY = 10;

          const addHeader = () => {
            //                pdf.setFont("arial", "bold");
            //                pdf.setFontSize(12);
            //                pdf.text("Bank Name", 105, currentY, { align: "center" });
            //                currentY += 10;
            //                pdf.text("Branch Name: BRANCH OF THE BANK", 105, currentY, { align: "center" });
            //                currentY += 10;
            //
            //                pdf.text("Branch Id: 101", 105, currentY, { align: "center" });
            //                currentY += 5;
            //                const pageWidth = pdf.internal.pageSize.getWidth();
            //                pdf.setDrawColor(0);
            //                pdf.setLineWidth(0.5);
            //                pdf.line(marginLeft, currentY, pageWidth - marginLeft, currentY);
            //                currentY += 5;
          };

          const addText = (label, value, bold = false) => {
            pdf.setFont("helvetica", bold ? "bold" : "normal");
            pdf.text(label, marginLeft, currentY);
            pdf.setFont("helvetica", "normal");
            pdf.text(value, marginLeft + 120, currentY);
            currentY += 8;
          };

          addHeader();
          if (imgData) {
            pdf.addImage(imgData, "PNG", 10, 10, 40, 10); // Adding the image
          }

          currentY += 20;
          pdf.setFont("helvetica", "bold");
          pdf.setFontSize(12);

          const pageWidths = pdf.internal.pageSize.getWidth();
          const contents = "RBI Bonds Brokerage Invoice";

          pdf.setFont("helvetica", "bold");
          pdf.setFontSize(12);

          const textWidth = pdf.getTextWidth(contents);
          const xPosition = (pageWidths - textWidth) / 2;

          pdf.text(contents, xPosition, currentY);

          currentY += 10;

          addText("Name of the Broker:", invoiceDetails.brokerName || "N/A", true);
          addText("Broker Code:", invoiceDetails.brokerCode || "N/A", true);

          pdf.setFont("helvetica", "bold");
          pdf.text("Vendor Code:", marginLeft, currentY);

          pdf.setFont("helvetica", "normal");
          pdf.text(`${invoiceDetails.vendorCode || "N/A"}`, marginLeft + 120, currentY);

          currentY += 8;

          pdf.setFont("helvetica", "bold");
          pdf.text("Bank Name:", marginLeft, currentY);
          pdf.setFont("helvetica", "normal");
          pdf.text(`${invoiceDetails.bankName || "N/A"}`, marginLeft + 120, currentY);
          currentY += 8;

          pdf.setFont("helvetica", "bold");
          pdf.text("Account Number:", marginLeft, currentY);
          pdf.setFont("helvetica", "normal");
          pdf.text(`${invoiceDetails.accountNumber || "N/A"}`, marginLeft + 120, currentY);
          currentY += 8;

          addText("HSN Code:", invoiceDetails.hsnCode || "N/A", true);
          addText("Address:", invoiceDetails.address || "N/A", true);
          addText("Phone #:", invoiceDetails.phone || "N/A", true);
          addText("Email Id:", invoiceDetails.emailId || "N/A", true);
          addText("GSTIN :", invoiceDetails.gstIn || "N/A", true);
          addText("PAN No  :", invoiceDetails.brokerPanNo || "N/A", true);

          pdf.setFont("helvetica", "bold");
          pdf.text("Registered Office (Billing Address):", marginLeft, currentY);
          currentY += 8;
          pdf.setFont("helvetica", "normal");
          pdf.setFontSize(12);
          pdf.text("HDFC BANK LTD SENAPATI BAPAT MARG, LOWER PAREL W, MUMBAI-400013.", marginLeft, currentY);
          currentY += 10;

          pdf.setFont("helvetica", "bold");
          pdf.setFontSize(12);
          pdf.text("Branch Office (Mailing Address):", marginLeft, currentY);
          currentY += 8;

          const width = pdf.internal.pageSize.getWidth();
          const text = "CMS-GOI Bonds - 2nd Floor, GKS Technology Park, No- 94, SP Estate Bus Stand, Wavin Main Road, Mogappair, Chennai - 600058.";
          const maxWidth = width - marginLeft * 2;

          const wrappedText = pdf.splitTextToSize(text, maxWidth);

          pdf.setFont("helvetica", "normal");
          wrappedText.forEach((line, index) => {
            pdf.text(line, marginLeft, currentY + index * 8);
          });

          currentY += wrappedText.length * 8;

          pdf.setFont("helvetica", "bold");
          pdf.text("GST IN:", marginLeft, currentY);

          pdf.setFont("helvetica", "normal");
          pdf.text(`${invoiceDetails.gstin || "N/A"}`, marginLeft + 120, currentY);
          currentY += 8;

          pdf.setFont("helvetica", "bold");
          pdf.text("PAN:", marginLeft, currentY);

          pdf.setFont("helvetica", "normal");
          pdf.text(`${invoiceDetails.pan || "N/A"}`, marginLeft + 120, currentY);
          currentY += 8;

          addText("Invoice No:", invoiceDetails.invoiceNo || "N/A", true);
          addText("Invoice Date:", invoiceDetails.invoiceDate || "N/A", true);
          addText("Brokerage Commission for the month of Dec-23:", `Rs. ${invoiceDetails.grossBrokerage || "0.00"}`, true);
          addText("CGST 9%:", "Rs. 0.00", true);
          addText("SGST 9%:", "Rs. 0.00", true);
          addText("IGST 18%:", "Rs. 0.00", true);
          addText("Total Invoice Value in Figures:", `Rs. ${invoiceDetails.netBrokerage || "0.00"}`, true);
          addText("Total Invoice Value in Words :", `Rs. ${vm.netBrokerageword || "N/A"}`, true);
          const pageWidth = pdf.internal.pageSize.getWidth();
          currentY += 15;
          pdf.setFont("helvetica", "bold");
          pdf.text("Broker Seal and Sign", pageWidth - marginLeft, currentY, { align: "right" });

          pdf.save(`Invoice_${invoiceDetails.invoiceNo || "Default"}.pdf`);
        });
      } else if (vm.invoiceType == "E-MAIL BODY") {
        const { jsPDF } = window.jspdf;


        const doc = new jsPDF();
        const fallback = (value) => (value !== undefined && value !== null ? value : "NA");

        // Dynamic values
        const brokerCode = fallback(vm.brokerCode);
        const month = fallback(vm.month);
        const investmentAmount = fallback(vm.investmentAmount);
        const brokerageAmount = fallback(vm.brokerageAmount);
        const gstAmount = fallback(vm.gstAmount);
        const netPayable = fallback(vm.netPayable);

        const marginLeft = 10;
        const marginTop = 10;
        const lineHeight = 8;
        const fontSize = 10;

        doc.setFontSize(fontSize);

        // Full unmodified message
        const fullContent = `
Dear Sir / Madam,

We write to you in regard to your brokerage credit of Rs. ${netPayable} for the investments sourced under the
broker code ${brokerCode} during the month of ${month}.

For your easy reference, we have provided payment method working for both GST Registered and
Unregistered brokers:
• Registered brokers (Individuals) pay 82% of brokerage amount and 18% GST would be paid by the bank to
Government on your behalf. You are requested to raise an invoice for 82% of the brokerage amount only as
given in the below table. Please note that invoice should contain the clause as “Whether GST payable under
Reverse Charge Mechanism – Yes”.
• Registered brokers (Non Individuals) are requested to raise an invoice for the entire brokerage amount
(consisting of 82% brokerage and 18% GST), bank will pay 100% to the brokers. The liability of GST rests with
the brokers. You are also advised to update the invoice in the GSTIR portal as soon as the amount is settled
to you to avoid reconciliation issues later.
• Unregistered brokers (both individuals / Non Individuals): Brokers are requested to raise an invoice for 82%
of the brokerage amount as given in the below table. Invoice to be raised mandatorily for release of the
brokerage amount.
Note - The GST number of HDFC Bank should be clearly mentioned as 27AXXXXXXXXH1Z0. GST registered
brokers are requested to quote the GST number and apportion GST rate as IGST / (CGST and SGST) as per
the POP and POS. Unregistered brokers to mention the GST number as Not Applicable.
Please submit the valid Invoice on or before 20-May-24 to ensure prompt payment of Brokerage.
Looking forward to strengthening our relationship.
Request you to kindly submit the original brokerage invoices at our collecting branches address given in the
link click here.
Encl : Tax invoice.
The attached Invoice for RBI Bonds Brokerage is Password protected, and the password is your latest 6 Digit
Broker Code.

Warm Regards,
HDFC Bank
`;

        const splitText = "Please submit the valid Invoice on or before 20-May-24 to ensure prompt payment of Brokerage.";
        const [beforeTable, afterTable] = fullContent.split(splitText);

        const linesBefore = doc.splitTextToSize(beforeTable.trim(), 180);
        linesBefore.forEach((line, i) => {
          doc.text(line, marginLeft, marginTop + i * lineHeight);
        });

        const tableY = marginTop + linesBefore.length * lineHeight + 2;
        const colWidths = [30, 45, 40, 30, 40];
        const rowHeight = 10;
        const tableX = marginLeft;

        const headers = ["Month", "Investment Amount", "Brokerage Amt (100%)", "GST @18%", "Net Payable (82%)"];
        const values = [month, investmentAmount, brokerageAmount, gstAmount, netPayable];

        let currentX = tableX;
        headers.forEach((header, i) => {
          doc.setFont(undefined, "bold");
          doc.rect(currentX, tableY, colWidths[i], rowHeight);
          doc.text(header, currentX + 2, tableY + 6);
          currentX += colWidths[i];
        });

        currentX = tableX;
        const rowY = tableY + rowHeight;
        doc.setFont(undefined, "normal");
        values.forEach((val, i) => {
          doc.rect(currentX, rowY, colWidths[i], rowHeight);
          doc.text(val.toString(), currentX + 2, rowY + 6);
          currentX += colWidths[i];
        });

        const afterY = rowY + rowHeight + 10;
        const remaining = `${splitText}\n${afterTable.trim()}`;
        const linesAfter = doc.splitTextToSize(remaining, 180);
        linesAfter.forEach((line, i) => {
          doc.text(line, marginLeft, afterY + i * lineHeight);
        });

        doc.save("Brokerage_Details.pdf");
      }
    };

    vm.downloadEmailBody = function (rowData) {
      if (!rowData) {
        alert("No record selected for download.");
        return;
      }
    };


    function loadImageAsBase64(url, callback) {
      const img = new Image();
      img.crossOrigin = "Anonymous";
      img.src = url;

      img.onload = function () {
        const canvas = document.createElement("canvas");
        canvas.width = img.width;
        canvas.height = img.height;
        const ctx = canvas.getContext("2d");
        ctx.drawImage(img, 0, 0);
        const dataURL = canvas.toDataURL("image/png");
        callback(dataURL);
      };

      img.onerror = function () {
        console.error("Error loading image:", url);
        callback(null);
      };
    }
  }
})();
