(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("RedemptionTextFileController", RedemptionTextFileController);
  RedemptionTextFileController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$stateParams",
    "$location",
    "$rootScope",
    "RedemptionTextFileService",
    "commonService",
  ];

  function RedemptionTextFileController($scope, $filter, $state, $stateParams, $location, $rootScope, RedemptionTextFileService, commonService) {
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
    if ($state.current.name == 'redemption_text_file_reporting_rbi.inquiry_details')
      {
        vm.fromDate = $stateParams.obj.fromDate;
        vm.toDate = $stateParams.obj.toDate;
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.loader = true;

        var requestData = {
          schemeCode: vm.schemeCode,
          fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
          toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
          flag: "D",
        };
  
        RedemptionTextFileService.getRedemptionDetails(requestData).then(
          function (response) {
            vm.loader = false;
            if (response.data && response.data.result) {
              vm.redemptionData = response.data.result;
              vm.showDetails = true;
              vm.noDataFound = false;
              // Navigate to detail page
            } else {
              vm.showDetails = false;
              vm.noDataFound = true;
              Swal.fire(" ", "No records for the given input date", "warning");
            }
          },
          function (error) {
            vm.loader = false;
            vm.showDetails = false;
            if (error.data && (error.data.message == "No Records Found" || error.data.message == "NDF")) {
              vm.noDataFound = true;
              Swal.fire(" ", "No records for the given input date", "warning");
            } else {
              Swal.fire(" ", "Error occurred while fetching data", "error");
            }
          }
        );
      }
    // Submit and navigate to detail page
    vm.getRedemptionDetails = function () {
      $state.go("redemption_text_file_reporting_rbi.inquiry_details",{obj:{
        schemeCode: vm.schemeCode,
        fromdate: vm.fromdate,
        todate: vm.todate,
        schemeName: vm.schemeName
      }});
    };

    // Download Redemption Scroll 274.txt
    vm.downloadRedemptionScroll274 = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "274",
      };

      RedemptionTextFileService.getRedemptionDetails(requestData).then(
        function (response) {
          vm.loader = false;
          if (response.data && response.data.result) {
            var dataList = response.data.result.map((item) => item.number || item);
            var textContent = dataList.join("\n");
            downloadFile(textContent, "Redemption Scroll 274.txt", "text/plain");
            Swal.fire("Success", "Redemption Scroll 274.txt Generated Successfully", "success");
          } else {
            Swal.fire("Warning", "No data available for download", "warning");
          }
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    };

    // Download  Prematurity Redemption Scroll 275.txt
vm.downloadPrematurityRedemptionScroll275 = function () {
                                               vm.loader = true;
                                               var requestData = {
                                                 schemeCode: vm.schemeCode,
                                                 fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
                                                 toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
                                                 flag: "275",
                                               };

                                               RedemptionTextFileService.getRedemptionDetails(requestData).then(
                                                 function (response) {
                                                   vm.loader = false;
                                                   if (response.data && response.data.result) {
                                                     var dataList = response.data.result.map((item) => item.number || item);
                                                     var textContent = dataList.join("\n");
                                                     downloadFile(textContent, "Prematurity Redemption Scroll 275.txt", "text/plain");
                                                     Swal.fire("Success", "Prematurity Redemption Scroll 275.txt Generated Successfully", "success");
                                                   } else {
                                                     Swal.fire("Warning", "No data available for download", "warning");
                                                   }
                                                 },
                                                 function (error) {
                                                   vm.loader = false;
                                                   handleErrorResponse(error);
                                                 }
                                               );
                                             };


    // Download Redemption Interest Scroll 276.txt
    vm.downloadRedemptionInterestScroll276 = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "276",
      };

      RedemptionTextFileService.getRedemptionDetails(requestData).then(
        function (response) {
          vm.loader = false;
          if (response.data && response.data.result) {
            var dataList = response.data.result.map((item) => item.number || item);
            var textContent = dataList.join("\n");
            downloadFile(textContent, "Redemption Interest Scroll 276.txt", "text/plain");
            Swal.fire("Success", "Redemption Interest Scroll 276.txt Generated Successfully", "success");
          } else {
            Swal.fire("Warning", "No data available for download", "warning");
          }
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    };



  // Download Prematurity Redemption Interest Scroll 277.txt
vm.downloadPrematurityRedemptionInterestScroll277= function () {
                                                       vm.loader = true;
                                                       var requestData = {
                                                         schemeCode: vm.schemeCode,
                                                         fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
                                                         toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
                                                         flag: "277",
                                                       };

                                                       RedemptionTextFileService.getRedemptionDetails(requestData).then(
                                                         function (response) {
                                                           vm.loader = false;
                                                           if (response.data && response.data.result) {
                                                             var dataList = response.data.result.map((item) => item.number || item);
                                                             var textContent = dataList.join("\n");
                                                             downloadFile(textContent, "Prematurity Redemption Interest Scroll 277.txt", "text/plain");
                                                             Swal.fire("Success", "Prematurity Redemption Interest Scroll 277.txt Generated Successfully", "success");
                                                           } else {
                                                             Swal.fire("Warning", "No data available for download", "warning");
                                                           }
                                                         },
                                                         function (error) {
                                                           vm.loader = false;
                                                           handleErrorResponse(error);
                                                         }
                                                       );
                                                     };



    // Download Redemption Covering Letter as DOCX
    vm.downloadRedemptionCoveringLetter = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "CL",
      };

      RedemptionTextFileService.getRedemptionDetails(requestData).then(
        function (response) {
          vm.loader = false;
          if (response.data && response.data.result) {
            var data = response.data.result;
            generateCoveringLetterDocx(data);
            Swal.fire("", "Redemption Covering Letter Generated Successfully", "success");
          } else {
            Swal.fire(" ", "No data available for download", "warning");
          }
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    };



 // Download Prematurity Redemption Covering Letter as DOCX
vm.downloadPrematurityRedemptionLetter = function () {

    vm.loader = true;

    var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "PCL"
    };

    RedemptionTextFileService.getRedemptionDetails(requestData).then(
        function (response) {

            vm.loader = false;

            if (response.data && response.data.result && response.data.result.length > 0) {

                generatePrematurityRedemptionDoc(response.data.result);

                Swal.fire(
                    "",
                    "Prematurity Redemption Letter Generated Successfully",
                    "success"
                );

            } else {

                Swal.fire("", "No data available", "warning");
            }

        },
        function (error) {

            vm.loader = false;

            Swal.fire("", "Error generating document", "error");
        }
    );
};


function generatePrematurityRedemptionDoc(data) {

   // var currentDate = $filter("date")(new Date(), "dd/MM/yyyy");

    const today = new Date();

    const day = String(today.getDate()).padStart(2, '0');
    const month = today.toLocaleString('en-US', { month: 'short' });
    const year = today.getFullYear();

    const currentDate = `${day}/${month}/${year}`;

const formattedSchemeName = vm.schemeName
    .toLowerCase()
    .replace(/\b\w/g, c => c.toUpperCase());
    var totalPrincipal = 0;
    var totalInterest = 0;
    var grandTotal = 0;

    var accountNo = "32105001001";

    // Dynamic table rows
    var tableRows = "";
function formatDate(dateString) {
    if (!dateString) return '';

    const date = new Date(dateString);

    const day = String(date.getDate()).padStart(2, '0');
    const month = date.toLocaleString('en-US', { month: 'short' });
    const year = String(date.getFullYear()).slice(-2);

    return `${day}-${month}-${year}`;
}
    angular.forEach(data, function (item, index) {

        var principal = parseFloat(item.principleAmount || 0);
        var interest = parseFloat(item.interestAmount || 0);
        var total = parseFloat(item.totalAmount || 0);

        totalPrincipal += principal;
        totalInterest += interest;
        grandTotal += total;

        tableRows += `
            <tr>
                <td>${index + 1}</td>

                <td>${item.blaNo || ''}</td>

                <td class="amount">
                    ${formatIndianNumber(principal.toFixed(2))}
                </td>

                <td class="amount">
                    ${formatIndianNumber(interest.toFixed(2))}
                </td>

                <td class="amount">
                    ${formatIndianNumber(total.toFixed(2))}
                </td>

                <td>${formatDate(item.paymentDate)}</td>

                <td>${item.bondType || ''}</td>
            </tr>
        `;
    });

totalPrincipal = parseFloat(totalPrincipal.toFixed(2));
totalInterest = parseFloat(totalInterest.toFixed(2));
grandTotal = parseFloat(grandTotal.toFixed(2));

    var totalInWords = convertNumberToWords(grandTotal);

    var htmlContent = `
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<style>

@page {
    size: A4;
    margin: 0.4in;
}

body{
    font-family: "Times New Roman", Times, serif;
    font-size: 12pt;
    line-height: 1.2;
}

p{
    margin:0;
}

.header{
    text-align:center;
    font-weight:bold;
    margin-bottom:15px;
}

.subject{
    font-weight:bold;
    margin-top:10px;
    margin-bottom:10px;
}

table{
    border-collapse:collapse;
    width:100%;
    margin-top:10px;
    font-size:12pt;
}

th, td{
    border:1px solid black;
    padding:4px;
    text-align:center;
}

th{
    background:#f0f0f0;
}

.amount{
    text-align:right;
}

.footer{
    margin-top:20px;
}

</style>

</head>

<body>



<p style="text-align:left;">
    Date:${currentDate}
</p>

<br>

<p>To</p>
<br><br>
<p style="margin-left:20px;">
    The Manager,<br><br>
    Central Accounts Section,<br><br>
    Reserve Bank of India, Nagpur
</p>

<br>

<p class="subject">
    Subject: Premature Redemption Claim under ${formattedSchemeName}
</p>
<p>
Sir/Madam,<br><br>
</p>
<p>
We are submitting herewith the claim for reimbursement on account of premature redemption of investments under the ${formattedSchemeName} scheme.
</p>

<p style="margin-top:10px;">
The details are as under:
<br><br>
</p>

<table>

    <tr>
        <th>Sr. No</th>
        <th>BLA Details</th>
        <th>Principal Amount Claimed</th>
        <th>Interest Amount Claimed</th>
        <th>Total Amount</th>
        <th>Date of Payment</th>
        <th>Interest Option (C/NC)</th>
    </tr>

    ${tableRows}

    <tr>
        <td colspan="2"><b>TOTAL</b></td>

        <td class="amount">
            <b>${formatIndianNumber(totalPrincipal.toFixed(2))}</b>
        </td>

        <td class="amount">
            <b>${formatIndianNumber(totalInterest.toFixed(2))}</b>
        </td>

        <td class="amount">
        <b>${formatIndianNumber(grandTotal.toFixed(2))}</b>
        </td>

        <td colspan="2"></td>
    </tr>

    <!-- Amount in Words Row -->
    <tr>
        <td colspan="2">
            <b>Total Amount in words</b>
        </td>

        <td colspan="5" style="text-align:center;">
            <b>
                ${convertNumberToWords(grandTotal)} only
            </b>
        </td>
    </tr>

</table>
<br>

<p>
Total Claim Amount:
Rs. ${formatIndianNumber(grandTotal)}
<br>
<br></p>


<p style="margin-top:5px;">
Amount in words:
(Rupees ${totalInWords} only)
<br>
<br>
</p>


<p>
Kindly credit our Current Account (${accountNo}) maintained with RBI towards the reimbursement of premature redemption proceeds.
<br></p>


<p>
We hereby certify that:
<br>
</p>
<ol>
    <li style="margin-bottom:10px;">
        Documentary evidence for date of birth has been verified and eligibility criteria met.
    </li>

    <li style="margin-bottom:10px;">
        Principal and interest (after deducting 50% penalty on last 6 months’ interest) have been paid to investors.
    </li>

    <li style="margin-bottom:10px;">
        This claim has not been submitted earlier.
    </li>

    <li style="margin-bottom:10px;">
        Branch-wise scrolls have been forwarded to concerned Public Debt Offices and Pay and Accounts Office.
    </li>

    <li style="margin-bottom:10px;">
        All payments have been made to eligible investors as per the provisions of the Bond scheme.
    </li>

    <li style="margin-bottom:10px;">
        The principal and interest have been actually paid by our branches.
    </li>
</ol>

<div class="footer">

    <p>
        We request you to process the reimbursement at the earliest.
    </p>

    <br><br>

    <p>
        Yours faithfully,
    </p>

    <br>

    <p>
        [Signature & Seal]
    </p>
<br>
    <p>
        (Branch/Department Head)
    </p>
<br>
    <p>
       [Bank Name]
    </p>

    <br>

    <p>
       Enclosures:
    </p>
<br>
    <p>
        1. CA/Concurrent Auditor Certificate
    </p>

</div>

</body>
</html>
`;

    var blob = new Blob(
        ['\ufeff', htmlContent],
        {
            type: 'application/msword'
        }
    );

    var link = document.createElement("a");

    link.href = window.URL.createObjectURL(blob);

    link.download = "Premature_Redemption_Letter.doc";

    document.body.appendChild(link);

    link.click();

    document.body.removeChild(link);
}




        vm.textableDatePicker = textableDatePicker;
        vm.autoslashing = autoslashing;
        function textableDatePicker(id, valueDate) {
          commonService.textableDatePicker(id, valueDate);
        }
        function autoslashing(id) {
          commonService.autoslashing(id);
        }

    // Generate Covering Letter DOCX with proper format
    function generateCoveringLetterDocx(data) {
      var currentDate = $filter("date")(new Date(), "dd-MMM-yyyy").toUpperCase();
      var formattedToDate = $filter("date")(vm.toDate, "dd-MMM-yyyy").toUpperCase();
      
      // Extract data from response - data is array with bondType: NC, C, AC
      var cumulativePrincipal = 0;
      var nonCumulativePrincipal = 0;
      var cumulativeInterest = 0;
      var nonCumulativeInterest = 0;
      var accountNo = "32105001001";
      
      // Process the array data
      if (Array.isArray(data)) {
        data.forEach(function(item) {
          if (item.bondType === "C") {
            cumulativePrincipal = parseFloat(item.investmentAmount) || 0;
            cumulativeInterest = parseFloat(item.interestAmount) || 0;
          } else if (item.bondType === "NC") {
            nonCumulativePrincipal = parseFloat(item.investmentAmount) || 0;
            nonCumulativeInterest = parseFloat(item.interestAmount) || 0;
          } else if (item.bondType === "AC") {
            accountNo = item.investmentAmount || "32105001001";
          }
        });
      }
      
      // Calculate totals
      var grandTotalPrincipal = cumulativePrincipal + nonCumulativePrincipal;
      var grandTotalInterest = cumulativeInterest + nonCumulativeInterest;
      var cumulativeTotal = cumulativePrincipal + cumulativeInterest;
      var nonCumulativeTotal = nonCumulativePrincipal + nonCumulativeInterest;
      var grandTotal = grandTotalPrincipal + grandTotalInterest;
      
      // Format numbers with commas and 2 decimal places
      cumulativePrincipal = formatIndianNumber(cumulativePrincipal);
      nonCumulativePrincipal = formatIndianNumber(nonCumulativePrincipal);
      grandTotalPrincipal = formatIndianNumber(grandTotalPrincipal);
      cumulativeInterest = formatIndianNumber(cumulativeInterest);
      nonCumulativeInterest = formatIndianNumber(nonCumulativeInterest);
      grandTotalInterest = formatIndianNumber(grandTotalInterest);
      cumulativeTotal = formatIndianNumber(cumulativeTotal);
      nonCumulativeTotal = formatIndianNumber(nonCumulativeTotal);
      var grandTotalFormatted = formatIndianNumber(grandTotal);
      var totalInWords = convertNumberToWords(grandTotal);

      var htmlContent = `
<!DOCTYPE html>
<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:w='urn:schemas-microsoft-com:office:word' xmlns='http://www.w3.org/TR/REC-html40'>
<head>
<meta charset='utf-8'>
<!--[if gte mso 9]>
<xml>
<w:WordDocument>
<w:View>Print</w:View>
<w:Zoom>100</w:Zoom>
<w:DoNotOptimizeForBrowser/>
</w:WordDocument>
</xml>
<![endif]-->
<style>
  @page { size: A4; margin: 0.4in 0.5in 0.3in 0.5in; }
  body { font-family: 'Times New Roman', Times, serif; font-size: 10pt; line-height: 1.1; margin: 0; }
  p { margin: 0 0 2pt 0; }
  .header { text-align: left; font-weight: bold; font-size: 10pt; }
  .date { text-align: right; margin-bottom: 5pt; }
  .to-section { margin-bottom: 4pt; }
  .to-section p { margin: 0; }
  .subject { font-weight: bold; margin: 4pt 0; font-size: 10pt; }
  table { border-collapse: collapse; width: 100%; margin: 4pt 0; font-size: 9pt; }
  th, td { border: 1px solid black; padding: 1pt 3pt; text-align: center; }
  th { background-color: #f0f0f0; font-weight: bold; }
  td.desc { text-align: left; }
  td.amount { text-align: right; }
  .signature { margin-top: 10pt; }
  ul { margin: 2pt 0 0 0; padding-left: 15pt; }
  li { margin-bottom: 1pt; font-size: 9pt; }
</style>
</head>
<body>

<p class="header" style="text-align: center;"><u>HDFC Bank Ltd, Link Cell Nagpur</u></p>
<p class="date"><b>${currentDate}</b></p>

<div class="to-section">
<p>To,</p>
<p style="margin-left: 20pt;"><b>The General Manager,</b></p>
<p style="margin-left: 20pt;">Reserve Bank of India,</p>
<p style="margin-left: 20pt;">Central Accounts Section,</p>
<p style="margin-left: 20pt;">Nagpur</p>
</div>

<p>Madam/Sir</p>

<p class="subject"><u>Redemption claim under ${vm.schemeName || "7.75% Savings (Taxable) Bonds, 2018"} Scheme</u></p>

<p style="text-align: justify;">We Submit herewith the redemption claim for reimbursement in respect of ${vm.schemeName || "7.75% Savings (Taxable) Bonds, 2018"}. The particulars are mentioned below:</p>

<table>
  <tr>
    <th>S.No</th>
    <th>Description</th>
    <th>Cumulative</th>
    <th>Non-Cumulative</th>
    <th>Grand Total</th>
  </tr>
  <tr>
    <td>1</td>
    <td class="desc">Investment Amount (Principal) Rs.</td>
    <td class="amount">${cumulativePrincipal}</td>
    <td class="amount">${nonCumulativePrincipal}</td>
    <td class="amount">${grandTotalPrincipal}</td>
  </tr>
  <tr>
    <td>2</td>
    <td class="desc">Interest Amount Rs.</td>
    <td class="amount">${cumulativeInterest}</td>
    <td class="amount">${nonCumulativeInterest}</td>
    <td class="amount">${grandTotalInterest}</td>
  </tr>
  <tr>
    <td>3</td>
    <td class="desc">Total Claim Rs.</td>
    <td class="amount">${cumulativeTotal}</td>
    <td class="amount">${nonCumulativeTotal}</td>
    <td class="amount">${grandTotalFormatted}</td>
  </tr>
</table>

<p style="text-align: justify;">Please credit our Current Account No. ${accountNo} With the total <b>Rs. ${grandTotalFormatted} (${totalInWords}).</b></p>

<p style="margin-top: 6pt;">2. The original certificate/s received from the concurrent / Internal Auditors is / are enclosed. We have forwarded via digitally signed e-mail the data in respect of these transactions on <b>${formattedToDate}</b></p>

<p style="margin-top: 6pt;">We certify as under:</p>

<ul>
  <li>The Claim has not been submitted by us earlier.</li>
  <li>Branch wise scrolls have been forwarded to the concerned Public Debt Offices of Reserve Bank of India and Pay and Accounts Office, Ministry of Finance, Government of India, New Delhi.</li>
  <li>The amount of withdrawals paid to the investors are as per provisions and rules laid down in this scheme.</li>
  <li>The Principal amount and the interest have been actually paid to the investors by our branches.</li>
  <li>BLAs under non-cumulative Scheme,<p style="margin-left:10px;margin-bottom:0px;margin-top:0px">(i). For the BLAs, where the discharge is received well in advance, the interest for last broken period has been paid along with the principal.</p><p style="margin-left:10px;margin-bottom:0px;margin-top:0px">(ii). For the BLAs, where the discharge is not received in advance, the interest for the last broken period has been paid on due date.</p></li>
  <li>BLAs held under cumulative Scheme, the interest has been paid along with principal.</li>
  <li>No amount of interest is paid by these branches beyond the date of maturity.</li>
  <li>The claim amount paid to the investors by our branches does not include any amount arising out of payment for compensation towards delayed dispatch of warrants etc.</li>
  <li>We certify that the claim does not contain any interest payment(s) that violate Section 23 of the Government Securities Act, 2006.</li>
</ul>

<div class="signature">
<p>Sincerely,</p>
<p style="margin-top: 20pt;"><b>For HDFC BANK LTD</b></p>
<p style="margin-top: 25pt;">Authorized Signatory</p>
</div>

</body>
</html>`;

      var blob = new Blob(['\ufeff', htmlContent], {
        type: 'application/msword'
      });
      var link = document.createElement("a");
      link.href = window.URL.createObjectURL(blob);
      link.download = "Redemption Covering Letter.doc";
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    }

    // Generic download file function for text files
    function downloadFile(content, fileName, contentType) {
      var blob = new Blob([content], { type: contentType });
      var link = document.createElement("a");
      link.href = window.URL.createObjectURL(blob);
      link.download = fileName;
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    }

    // Format number in Indian number system with 2 decimal places
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

    // Convert number to words (Indian system)
    function convertNumberToWords(num) {
      if (num === 0) return "Zero Only";
      
      var ones = ["", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
                  "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"];
      var tens = ["", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"];
      
      function convertLessThanThousand(n) {
        if (n === 0) return "";
        if (n < 20) return ones[n];
        if (n < 100) return tens[Math.floor(n / 10)] + (n % 10 !== 0 ? " " + ones[n % 10] : "");
        return ones[Math.floor(n / 100)] + " Hundred" + (n % 100 !== 0 ? " " + convertLessThanThousand(n % 100) : "");
      }
      
      var number = Math.floor(num);
      var paise = Math.round((num - number) * 100);
      var result = "";
      
      if (number >= 10000000) {
        result += convertLessThanThousand(Math.floor(number / 10000000)) + " Crore ";
        number %= 10000000;
      }
      if (number >= 100000) {
        result += convertLessThanThousand(Math.floor(number / 100000)) + " Lakh ";
        number %= 100000;
      }
      if (number >= 1000) {
        result += convertLessThanThousand(Math.floor(number / 1000)) + " Thousand ";
        number %= 1000;
      }
      if (number > 0) {
        result += convertLessThanThousand(number);
      }
      
      result = "Rupees " + result.trim();
      if (paise > 0) {
        result += " and Paise " + convertLessThanThousand(paise);
      } else {
        result += " and Paise Zero";
      }
      result += " Only";
      
      return result;
    }

    // Get Scheme Name for Torch
    vm.getSchemeName = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: "",
        flag: "S",
      };
      RedemptionTextFileService.getRedemptionDetails(requestData).then(
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
