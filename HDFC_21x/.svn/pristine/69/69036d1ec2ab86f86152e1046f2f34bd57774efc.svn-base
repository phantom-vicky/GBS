(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("SubscriptionMandateController", SubscriptionMandateController);
  SubscriptionMandateController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$stateParams",
    "$location",
    "$rootScope",
    "SubscriptionMandateService",
    "commonService",
  ];

  function SubscriptionMandateController($scope, $filter, $state, $stateParams, $location, $rootScope, SubscriptionMandateService, commonService) {
    var vm = this;

    // Initialize Branch details from logged in user
    vm.init = function () {
      vm.branchId = $rootScope.solId || '';
      vm.branchName = $rootScope.solDesc || '';
      vm.moduleName = 'RBI Bond';
      vm.showDetails = false;
      vm.noDataFound = false;
    };
 vm.init();
    vm.subMandateDetails=subMandateDetails;

    var date=new Date();
    vm.fileDateOne =$filter("date")(date, "dd-MM-yyyy");


  vm.goToInquiryDetails = function () {

      var obj = {
          schemeCode: vm.schemeCode,
          fromDate: vm.fromDate,
          toDate: vm.toDate,
          schemeName: vm.schemeName
      };

      $state.go("subscription_mandate.inquiry_details", {
          obj: obj
      });
  };


  if ($state.current.name === 'subscription_mandate.inquiry_details') {

      if ($stateParams.obj) {

          vm.fromDate = $stateParams.obj.fromDate;
          vm.toDate = $stateParams.obj.toDate;
          vm.schemeCode = $stateParams.obj.schemeCode;
          vm.schemeName = $stateParams.obj.schemeName;

          subMandateDetails();
      }


  }


      function subMandateDetails(){
       vm.loader = true;

       var requestData = {
          schemeCode: vm.schemeCode,
          fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
          toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
          flag: "D",
            };
              SubscriptionMandateService.getRedemptionDetails(requestData).then(
          function (response) {
            vm.loader = false;

                         vm.redemptionData = response.data.result;

                         vm.totalCumAmount = vm.redemptionData[0].cumAmount;
                         vm.totalCumNonCumAmount = vm.redemptionData[0].cumNonCumAmount;

                         vm.totalCumTran=vm.redemptionData[0].cumTotalTran;
                           vm.totalCumNonTran = vm.redemptionData[0].nonCumTotalTran;
              // Final Totvm.grandTotalAmount =
                              vm.grandTotalAmount =Number(vm.totalCumAmount) + Number(vm.totalCumNonCumAmount);

                          vm.grandTotalTran =
                              Number(vm.totalCumTran) + Number(vm.totalCumNonTran);

          },
          function (error) {
            vm.loader = false;
                                       if (error.data.message == 'No Data Found') {
                                           vm.noDataFound = error.data.message;
                                       } else {
                                           handleErrorResponse(error);
                                           $state.go('subscription_mandate.inquiry')
                                       }
          }
        );
      }


      vm.printpage =function(){
                          window.print()
                        }
    // Submit and navigate to detail page
    vm.getRedemptionDetails = function () {
      $state.go("subscription_mandate.inquiry_details",{obj:{
        schemeCode: vm.schemeCode,
        fromdate: vm.fromdate,
        todate: vm.todate,
        schemeName: vm.schemeName
      }});
    };

  vm.formatIndianNumber =formatIndianNumber;

    function formatIndianNumber(num) {
      if (num === 0 || num === null || num === undefined) return "0.00";
      var number = parseFloat(num).toFixed(2);
      var parts = number.split(".");
      var intPart = parts[0];
      var decPart = parts[1];
      var lastThree = intPart.substring(intPart.length - 3);
      var otherNumbers = intPart.substring(0, intPart.length - 3);
      if (otherNumbers !== "") {
        lastThree = "," + lastThree;
      }
      var formatted = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ",") + lastThree;
      return formatted + "." + decPart;
    }


vm.formatSchemeName = function(name) {
    return name.toLowerCase().replace(/\b\w/g, function(c) {
        return c.toUpperCase();
    });
};

// vm.downloadPdf = function () {
//
//     const pdf = new window.jspdf.jsPDF({
//         orientation: "p",
//         unit: "mm",
//         format: "a4"
//     });
//
//     let y = 12;
//
//     pdf.setFont("times", "normal");
//     pdf.setFontSize(11);
//
//                // =====================================================
//                // HEADER CENTER ADDRESS
//                // =====================================================
//
//                const centerX = 105;
//
//                pdf.text("HDFC BANK LTD", centerX, y, {align: "center"});
//                y += 7;
//
//                pdf.text("Link Cell", centerX, y, {align: "center"});
//                y += 7;
//
//                pdf.text("18/3, 1st floor,", centerX, y, {align: "center"});
//                y += 7;
//
//                pdf.text("IT Park, Near Persistant", centerX, y, {align: "center"});
//                y += 7;
//
//                pdf.text("System Software Technology Park", centerX, y, {align: "center"});
//                y += 7;
//
//                pdf.text("Near Mate Square, Gayatri Nagar", centerX, y, {align: "center"});
//                y += 7;
//
//                pdf.text("NAGPUR - 440022", centerX, y, {align: "center"});
//
//                // =====================================================
//                // REPORT DATE
//                // =====================================================
//
//              pdf.text(
//                  "Reporting Date: " + vm.fileDateOne,
//                  160,
//                  y + 8
//              );
//
//
////                pdf.text(
////                    "Reporting Date: " + vm.fileDateOne,
////                    150,
////                    52,
////                );
//
//                // =====================================================
//                // TO ADDRESS
//                // =====================================================
//
//                y = 75;
//
//                pdf.text("To", 15, y);
//                y += 10;
//
//                pdf.text("General Manager", 15, y);
//                y += 10;
//
//                pdf.text("Central Accounts section", 15, y);
//                y += 10;
//
//                pdf.text("Reserve Bank of India", 15, y);
//                y += 10;
//
//                pdf.text("Nagpur - 440001", 15, y);
//
//                // =====================================================
//                // SUBJECT
//                // =====================================================
//
//                y += 14;
//
//              pdf.text(
//                  "Reg: Subscription of " + vm.formatSchemeName(vm.schemeName),
//                  15,
//                  y
//               );
//
//                // =====================================================
//                // TITLE
//                // =====================================================
//
//                y += 18;
//
//                pdf.setFont("times", "bold");
//                pdf.setFontSize(22);
//
//                pdf.text("Debit Advice", 105, y, {align: "center"});
//
//                // underline
//                pdf.line(75, y + 1, 135, y + 1);
//
//                // =====================================================
//                // BODY CONTENT
//                // =====================================================
//
//                pdf.setFont("times", "normal");
//                pdf.setFontSize(11);
//
//                y += 12;
//
//                const para1 =
//                   "Please find enclosed debit advice for Rs: " + formatIndianNumber(vm.grandTotalAmount) +
//                   " (" + vm.formatSchemeName(vm.convertNumberToWords(vm.grandTotalAmount)) + ") " +
//                   "towards " + vm.formatSchemeName(vm.schemeName)+ "Please acknowledge the receipt."
//
//                const lines1 = pdf.splitTextToSize(para1, 175);
//
//                pdf.text(lines1, 15, y);
//
//                y += (lines1.length * 6) + 4;
//
//                pdf.text(
//                    "The Total amount under non-cumulative and cumulative are",
//                    15,
//                    y
//                );
//
//                // =====================================================
//                // TABLE
//                // =====================================================
//
//                y += 8;
//
//                // table outer border
//
//                pdf.rect(15, y, 170, 30);
//
//                // verticals
//
//                pdf.line(55, y, 55, y + 30);
//                pdf.line(95, y, 95, y + 30);
//
//                // horizontals
//
//                pdf.line(15, y + 7, 185, y + 7);
//                pdf.line(15, y + 15, 185, y + 15);
//                pdf.line(15, y + 23, 185, y + 23);
//
//                // headers
//
//                pdf.setFont("times", "normal");
//
//                pdf.text("Scheme", 16, y + 5);
//                pdf.text("Amount", 56, y + 5);
//                pdf.text("No of Transactions (Total BLAs)", 96, y + 5);
//
//                // row 1
//
//               pdf.text("Cumulative", 16, y + 13);
////               pdf.text(formatIndianNumber(String(vm.totalCumAmount)), 56, y + 13);
//               pdf.text(formatIndianNumber(vm.totalCumAmount || 0),56,y + 13);
//               pdf.text(String(vm.totalCumTran), 96, y + 13);
//
//               // row 2
//
//               pdf.text("Non-cumulative", 16, y + 21);
//               pdf.text(formatIndianNumber(vm.totalCumNonCumAmount||0), 56, y + 21);
//               pdf.text(String(vm.totalCumNonTran), 96, y + 21);
//
//               // row 3
//
//               pdf.text("Total", 16, y + 29);
//               pdf.text(formatIndianNumber(vm.grandTotalAmount||0), 56, y + 29);
//               pdf.text(String(vm.grandTotalTran), 96, y + 29);
//
//
//                // =====================================================
//                // AUTHORIZATION TEXT
//                // =====================================================
//
//                y += 45;
//
//                const para2 =
//
//
//              "We authorize You to debit our Current Account No 32105001001 Maintained " +
//              "with DAD Nagpur For Rs " +formatIndianNumber(vm.grandTotalAmount) +
//              " (" + vm.formatSchemeName(vm.convertNumberToWords(vm.grandTotalAmount)) + ")";
//
//                const lines2 = pdf.splitTextToSize(para2, 175);
//
//                pdf.text(lines2, 15, y);
//
//                y += (lines2.length * 6) + 25;
//
//                // =====================================================
//                // SIGNATURE SECTION
//                // =====================================================
//
//                pdf.setFont("times", "bold");
//
//                pdf.text("For HDFC BANK LTD", 15, y);
//                pdf.text("For HDFC BANK LTD", 115, y);
//
//                // signature images
//
////                if (signData) {
////
////                    pdf.addImage(signData, "PNG", 10, y + 5, 45, 22);
////
////                    pdf.addImage(signData, "PNG", 110, y + 5, 45, 22);
////                }
//
//                y += 20;
//
//                pdf.text("Authorized Signatory", 15, y);
//                pdf.text("Authorized Signatory", 115, y);
//
////            });
//
//            pdf.save("Debit_Advice.pdf");
////        });
//    }




vm.downloadWord = function () {

    // =====================================================
    // HTML CONTENT
    // =====================================================

    let content = `
    <html xmlns:o='urn:schemas-microsoft-com:office:office'
          xmlns:w='urn:schemas-microsoft-com:office:word'
          xmlns='http://www.w3.org/TR/REC-html40'>

    <head>

        <meta charset="utf-8">

        <style>

            @page{
                size:A4;
                margin:15mm 12mm 15mm 12mm;
            }

            body{
                font-family:"Times New Roman";
                font-size:14px;
                line-height:1.3;
                margin:0;
                padding:0;
                width:100%;
            }

            .center{
                text-align:center;
                line-height:20px;
            }

            .right{
                text-align:right;
                margin-top:8px;
            }

            .title{
                text-align:center;
                font-size:24px;
                font-weight:bold;
                text-decoration:underline;
                margin-top:15px;
                margin-bottom:15px;
            }

            .section-gap{
                margin-top:18px;
            }

            .content{
                line-height:24px;
                text-align:justify;
            }

            table{
                width:100%;
                border-collapse:collapse;
                margin-top:10px;
                font-size:14px;
            }

            table, th, td{
                border:1px solid black;
            }

            th{
                font-weight:bold;
                text-align:center;
                padding:6px;
            }

            td{
                padding:6px;
            }

            .signature{
                width:100%;
                margin-top:40px;
            }

            .signature td{
                border:none;
                padding-top:45px;
            }

        </style>

    </head>

    <body>

        <!-- ===================================================== -->
        <!-- HEADER -->
        <!-- ===================================================== -->

        <div class="center">

            <div><b>HDFC BANK LTD</b></div>

            <div>Link Cell</div>

            <div>18/3, 1st floor,</div>

            <div>IT Park, Near Persistant</div>

            <div>System Software Technology Park</div>

            <div>Near Mate Square, Gayatri Nagar</div>

            <div>NAGPUR - 440022</div>

        </div>

        <!-- ===================================================== -->
        <!-- REPORT DATE -->
        <!-- ===================================================== -->

        <div class="left">
            Reporting Date: ${vm.fileDateOne}
        </div>

        <!-- ===================================================== -->
        <!-- TO ADDRESS -->
        <!-- ===================================================== -->

        <div class="section-gap">

            <div>To</div>

            <br>

            <div>General Manager</div>

            <div>Central Accounts section</div>

            <div>Reserve Bank of India</div>

            <div>Nagpur - 440001</div>

        </div>

        <!-- ===================================================== -->
        <!-- SUBJECT -->
        <!-- ===================================================== -->

        <div class="section-gap">

            Reg: Subscription of
            ${vm.formatSchemeName(vm.schemeName)}

        </div>

        <!-- ===================================================== -->
        <!-- TITLE -->
        <!-- ===================================================== -->

        <div class="title">
            Debit Advice
        </div>

        <!-- ===================================================== -->
        <!-- BODY -->
        <!-- ===================================================== -->

        <div class="content">

            Please find enclosed debit advice for
            Rs <b>${formatIndianNumber(vm.grandTotalAmount)}</b>

            (
            ${vm.formatSchemeName(
                vm.convertNumberToWords(vm.grandTotalAmount)
            )}
            )

            towards
            ${vm.formatSchemeName(vm.schemeName)}.

            Please acknowledge the receipt.

        </div>

        <div class="section-gap">

         <div>The Total amount under non-cumulative and cumulative are</div>

                  <br>


        </div>




        <!-- ===================================================== -->
        <!-- TABLE -->
        <!-- ===================================================== -->

        <table>

            <tr>

                <th style="width:35%;">
                    Scheme
                </th>

                <th style="width:30%;">
                    Amount
                </th>

                <th style="width:35%;">
                    No of Transactions (Total BLAs)
                </th>

            </tr>

            <tr>

                <td>Cumulative</td>

                <td>
                    ${formatIndianNumber(vm.totalCumAmount || 0)}
                </td>

                <td>
                    ${vm.totalCumTran}
                </td>

            </tr>

            <tr>

                <td>Non-cumulative</td>

                <td>
                    ${formatIndianNumber(vm.totalCumNonCumAmount || 0)}
                </td>

                <td>
                    ${vm.totalCumNonTran}
                </td>

            </tr>

            <tr>

                <td><b>Total</b></td>

                <td>
                    <b>
                        ${formatIndianNumber(vm.grandTotalAmount || 0)}
                    </b>
                </td>

                <td>
                    <b>${vm.grandTotalTran}</b>
                </td>

            </tr>


        </table>

        <div>

        </div>

        <!-- ===================================================== -->
        <!-- AUTHORIZATION -->
        <!-- ===================================================== -->

        <div class="content section-gap">
            <br>
            We authorize You to debit our Current Account
            No 32105001001 maintained with DAD Nagpur
            for Rs

            <b>
                ${formatIndianNumber(vm.grandTotalAmount)}
            </b>

            (
            ${vm.formatSchemeName(
                vm.convertNumberToWords(vm.grandTotalAmount)
            )}
            )

        </div>

        <!-- ===================================================== -->
        <!-- SIGNATURE -->
        <!-- ===================================================== -->

        <table class="signature">

            <tr>

                <td>
                    <b>For HDFC BANK LTD</b>
                </td>

                <td style="text-align:right;">
                    <b>For HDFC BANK LTD</b>
                </td>

            </tr>

            <tr>

                <td>
                    Authorized Signatory
                </td>

                <td style="text-align:right;">
                    Authorized Signatory
                </td>

            </tr>

        </table>

    </body>

    </html>
    `;

    // =====================================================
    // CREATE WORD FILE
    // =====================================================

    const blob = new Blob(
        ['\ufeff', content],
        {
            type: 'application/msword'
        }
    );

    // =====================================================
    // DOWNLOAD WORD FILE
    // =====================================================

    const url = URL.createObjectURL(blob);

    const link = document.createElement("a");

    link.href = url;

    link.download = "Debit_Advice.doc";

    document.body.appendChild(link);

    link.click();

    document.body.removeChild(link);

    URL.revokeObjectURL(url);
};

        vm.textableDatePicker = textableDatePicker;
        vm.autoslashing = autoslashing;
        function textableDatePicker(id, valueDate) {
          commonService.textableDatePicker(id, valueDate);
        }
        function autoslashing(id) {
          commonService.autoslashing(id);
        }









    // Get Scheme Name for Torch
    vm.getSchemeName = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: "",
        flag: "C",
      };
      SubscriptionMandateService.getRedemptionDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result;
          angular.element("#schemeModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          Swal.fire(" ", "Error fetching scheme list", "error");
        }
      );
    };

    // Date Validation - From Date should not allow future date
    vm.fromDateVal = function () {
      vm.fromDateOptions = {
        maxDate: new Date(),
        showWeeks: false,
      };
    };

 vm.convertNumberToWords = function (num) {
           if (num === 0) return "RUPEES ZERO ONLY";

           const belowTwenty = ["ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN",
                                "ELEVEN", "TWELVE", "THIRTEEN", "FOURTEEN", "FIFTEEN", "SIXTEEN", "SEVENTEEN", "EIGHTEEN", "NINETEEN"];
           const tens = ["TWENTY", "THIRTY", "FORTY", "FIFTY", "SIXTY", "SEVENTY", "EIGHTY", "NINETY"];
           const denominations = ["", "THOUSAND", "LAKH", "CRORE"];

           function convertChunk(num) {
               let result = "";
               if (num >= 100) {
                   result += belowTwenty[Math.floor(num / 100) - 1] + " HUNDRED ";
                   num %= 100;
               }
               if (num >= 20) {
                   result += tens[Math.floor(num / 10) - 2] + " ";
                   num %= 10;
               }
               if (num > 0) {
                   result += belowTwenty[num - 1] + " ";
               }
               return result.trim();
           }

           function convertToIndianFormat(num) {
               if (num === 0) return "";
               const parts = [];

               // Extract crore, lakh, thousand, and remaining part
               const crore = Math.floor(num / 10000000);
               num %= 10000000;
               const lakh = Math.floor(num / 100000);
               num %= 100000;
               const thousand = Math.floor(num / 1000);
               num %= 1000;
               const remaining = num;

               if (crore > 0) parts.push(convertChunk(crore) + " CRORE" + (crore > 1 ? "S" : ""));
               if (lakh > 0) parts.push(convertChunk(lakh) + " LAKH" + (lakh > 1 ? "S" : ""));
               if (thousand > 0) parts.push(convertChunk(thousand) + " THOUSAND");
               if (remaining > 0) parts.push(convertChunk(remaining));

               return parts.join(" ").trim();
           }

           const result = convertToIndianFormat(num);
           vm.netPaymentInWords = "RUPEES " + result + " ONLY";
           return vm.netPaymentInWords;
       };

    // Date Validation - To Date should not be before From Date
    vm.toDateVal = function (fromDate) {
      vm.toDateOptions = {
        minDate: fromDate,
        maxDate: new Date(),
        showWeeks: false,
      };
    };

    // Date picker objects
    vm.effFromDatePicker = { opened: false };
    vm.effToDatePicker = { opened: false };
  }
})();
