(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("BrokerageClaimHandlingCommissionController", BrokerageClaimHandlingCommissionController);

  BrokerageClaimHandlingCommissionController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$stateParams",
    "$location",
    "$rootScope",
    "BrokerageClaimHandlingCommissionService",
    "commonService",
  ];

  function BrokerageClaimHandlingCommissionController(
    $scope,
    $filter,
    $state,
    $stateParams,
    $location,
    $rootScope,
    BrokerageClaimHandlingCommissionService,
    commonService
  ) {
    var vm = this;

    vm.init = function () {
      vm.branchId = ($rootScope.globals && $rootScope.globals.currentUser && $rootScope.globals.currentUser.solId) || $rootScope.solId || "";
      vm.branchName = ($rootScope.globals && $rootScope.globals.currentUser && $rootScope.globals.currentUser.solDesc) || $rootScope.solDesc || "";
      vm.bankName = ($rootScope.globals && $rootScope.globals.currentUser && $rootScope.globals.currentUser.bankName) || "";
      vm.moduleName = "RBI Bond";
      vm.schemeList = [];
      vm.showDetails = false;
      vm.noDataFound = false;
      vm.claimData = null;
      vm.solDate = ($rootScope.globals && $rootScope.globals.currentUser && $rootScope.globals.currentUser.systemDate) || "";
      vm.currentDate = $filter("date")(new Date(), "dd/MM/yyyy");
    };
    vm.init();

    if ($state.current.name == "brokerage_claim_handling_commission.inquiry_details") {
      if ($stateParams.obj) {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.fromDate = $stateParams.obj.fromDate;
        vm.toDate = $stateParams.obj.toDate;
        vm.showDetails = true;
        vm.noDataFound = false;
      }
    }

    var DOWNLOAD_PID_MAP = {
      TAX_INVOICE: "1",
      RBI_LETTER_1: "2",
      RBI_LETTER_2: "3",
      STATEMENT_1_PDF: "4",
      TEXT_FILE: "5",
    };

    vm.downloadFile = function (flag) {
      var pidId = DOWNLOAD_PID_MAP[flag];
      if (!pidId) {
        Swal.fire("Warning", "Invalid download option", "warning");
        return;
      }

      fetchScrollData(pidId, function (result) {
        var normalized = normalizeClaimData(result);

        if (flag === "TEXT_FILE") {
          downloadBlob(buildBrokerageTextFileContent(result), "Brokerage_Text_File.txt", "text/plain");
          Swal.fire("Success", "Brokerage Text File Generated Successfully", "success");
          return;
        }

        if (flag === "TAX_INVOICE") {
          downloadHandlingCommissionTaxInvoicePdf(normalized, function () {
            Swal.fire("Success", "Part1_Tax_Invoice.pdf Generated Successfully", "success");
          });
          return;
        }

        if (flag === "RBI_LETTER_1") {
          downloadBrokerageHandlingCommissionLetterPdf(normalized, function () {
            Swal.fire("Success", "RBI_Letter_1.pdf Generated Successfully", "success");
          });
          return;
        }

        if (flag === "RBI_LETTER_2") {
          downloadStatementOfChargesPdf(normalized, function () {
            Swal.fire("Success", "RBI_Letter_2.pdf Generated Successfully", "success");
          });
          return;
        }

        if (flag === "STATEMENT_1_PDF") {
          downloadStatement1BillLetterPdf(normalized, function () {
            Swal.fire("Success", "Statement_1_Bill_Letter.pdf Generated Successfully", "success");
          });
          return;
        }
      });
    };

    vm.downloadAllAsZip = function () {
      if (!window.JSZip) {
        Swal.fire("Warning", "Zip library not available.", "warning");
        return;
      }

      var pidOrder = ["1", "2", "3", "4", "5"];
      var scrollResults = {};
      var currentIndex = 0;

      function fetchNextPid() {
        if (currentIndex >= pidOrder.length) {
          buildZipPackage(scrollResults);
          return;
        }

        fetchScrollData(pidOrder[currentIndex], function (result) {
          scrollResults[pidOrder[currentIndex]] = result;
          currentIndex++;
          fetchNextPid();
        });
      }

      function buildZipPackage(results) {
        loadImageAsBase64("assets/images/HDFC-Logo.png", function (logoData) {
          var zip = new JSZip();
          var normalized1 = normalizeClaimData(results["1"]);
          var normalized2 = normalizeClaimData(results["2"]);
          var normalized3 = normalizeClaimData(results["3"]);
          var normalized4 = normalizeClaimData(results["4"]);

          var taxInvoiceDoc = downloadHandlingCommissionTaxInvoicePdf(normalized1, null, { skipSave: true });
          if (taxInvoiceDoc) {
            zip.file("Part1_Tax_Invoice.pdf", taxInvoiceDoc.output("arraybuffer"));
          }

          var rbiLetter1Doc = downloadBrokerageHandlingCommissionLetterPdf(normalized2, null, { skipSave: true });
          if (rbiLetter1Doc) {
            zip.file("RBI_Letter_1.pdf", rbiLetter1Doc.output("arraybuffer"));
          }

          var rbiLetter2Doc = downloadStatementOfChargesPdf(normalized3, null, { skipSave: true });
          if (rbiLetter2Doc) {
            zip.file("RBI_Letter_2.pdf", rbiLetter2Doc.output("arraybuffer"));
          }

          var statement1Doc = getJsPdfInstance();
          if (statement1Doc) {
            renderStatement1BillLetterPdf(normalized4, statement1Doc, logoData);
            zip.file("Statement_1_Bill_Letter.pdf", statement1Doc.output("arraybuffer"));
          }

          zip.file("Brokerage_Text_File.txt", buildBrokerageTextFileContent(results["5"]));

          zip.generateAsync({ type: "blob" }).then(function (content) {
            downloadBlob(content, "Brokerage_Claim_Handling_Commission.zip", "application/zip");
            Swal.fire("Success", "All files downloaded successfully in zip", "success");
          });
        });
      }

      fetchNextPid();
    };

    function formatScrollRequestDate(dateValue) {
      if (!dateValue) {
        return "";
      }
      var parsed = moment(
        dateValue,
        ["DD/MM/YYYY", "DD/MMM/YYYY", "DD-MMM-YYYY", "YYYY-MM-DD", moment.ISO_8601],
        true
      );
      if (!parsed.isValid()) {
        parsed = moment(dateValue);
      }
      return parsed.isValid() ? parsed.format("DD-MMM-YYYY").toUpperCase() : "";
    }

    function fetchScrollData(pidId, callback) {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        schemeName: vm.schemeName,
        fromDate: formatScrollRequestDate(vm.fromDate),
        toDate: formatScrollRequestDate(vm.toDate),
        pidId: pidId,
      };

      BrokerageClaimHandlingCommissionService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          if (response.data && response.data.result && hasScrollData(response.data.result, pidId)) {
            callback(mapScrollResponse(response.data.result, pidId));
          } else {
            var message =
              (response.data && response.data.message) ||
              "No records for the given input date";
            Swal.fire(" ", message, "warning");
          }
        },
        function (error) {
          vm.loader = false;
          if (error.data && (error.data.message == "No Data Found" || error.data.message == "NDF" || error.data.message == "No Records Found")) {
            Swal.fire(" ", "No records for the given input date", "warning");
          } else {
            handleErrorResponse(error);
          }
        }
      );
    }

    function hasScrollData(result, pidId) {
      if (!result) {
        return false;
      }

      if (pidId === "1") {
        return hasRows(result.curValOut);
      }
      if (pidId === "2") {
        return hasRows(result.curValOut2);
      }
      if (pidId === "3") {
        return hasRows(result.curValOut3);
      }
      if (pidId === "4") {
        return hasRows(result.curValOut4);
      }
      if (pidId === "5") {
        return hasRows(result.curValOut5);
      }

      return (
        hasRows(result.curValOut) ||
        hasRows(result.curValOut2) ||
        hasRows(result.curValOut3) ||
        hasRows(result.curValOut4) ||
        hasRows(result.curValOut5)
      );
    }

    function hasRows(list) {
      return Array.isArray(list) && list.length > 0;
    }

    function getScrollRows(result) {
      var rows = [];
      if (!result) {
        return rows;
      }
      [result.curValOut, result.curValOut2, result.curValOut3, result.curValOut4, result.curValOut5].forEach(function (list) {
        if (hasRows(list)) {
          rows = rows.concat(list);
        }
      });
      return rows;
    }

    function getPrimaryScrollList(result, pidId) {
      if (!result) {
        return [];
      }
      if (pidId === "1") {
        return result.curValOut || [];
      }
      if (pidId === "2") {
        return result.curValOut2 || [];
      }
      if (pidId === "3") {
        return result.curValOut3 || [];
      }
      if (pidId === "4") {
        return result.curValOut4 || [];
      }
      if (pidId === "5") {
        return result.curValOut5 || [];
      }
      return getScrollRows(result);
    }

    function mapScrollResponse(result, pidId) {
      var mapped = angular.extend({}, result);
      var primaryList = getPrimaryScrollList(result, pidId);
      mapped.pidId = pidId;
      mapped.list = primaryList;
      if (primaryList.length) {
        angular.extend(mapped, primaryList[0]);
      }
      mapped.taxInvoice = angular.extend({}, mapped);
      if (pidId === "2") {
        mapped.rbiLetter = primaryList.length ? angular.extend({}, primaryList[0]) : {};
      }
      return mapped;
    }

    function getRbiLetter1Row(data) {
      if (!data) {
        return {};
      }
      if (hasRows(data.curValOut2)) {
        return data.curValOut2[0];
      }
      if (data.pidId === "2" && hasRows(data.list)) {
        return data.list[0];
      }
      if (data.rbiLetter) {
        return data.rbiLetter;
      }
      return data;
    }

    function getRbiLetter1Amounts(data) {
      var row = getRbiLetter1Row(data);
      var source = angular.extend({}, data || {}, row || {});

      return {
        netSubscription:
          parseIndianAmount(getScrollField(source, "NET_AMOUNT_COLLECTED", "NET_SUBSCRIPTION", "AMOUNT_COLLECTED")) || 0,
        totalBrokerage:
          parseIndianAmount(getScrollField(source, "TOTAL_BROKERAGE", "BROKERAGE_CLAIMED")) || 0,
        brokerage90:
          parseIndianAmount(getScrollField(source, "BROKERAGE_90_RECEIVED", "BROKERAGE_90_ALREADY_PAID")) || 0,
        brokerage10:
          parseIndianAmount(getScrollField(source, "BROKERAGE_10_TO_RECEIVE", "BROKERAGE_10_CLAIMED")) || 0,
        handlingCharges:
          parseIndianAmount(getScrollField(source, "HANDLING_CHARGES", "HANDLING_COMMISSION")) || 0,
      };
    }

    function getScrollField(data) {
      if (!data) {
        return "";
      }

      var keys = Array.prototype.slice.call(arguments, 1);
      var lists = [data.curValOut, data.curValOut2, data.curValOut3, data.curValOut4];

      for (var i = 0; i < keys.length; i++) {
        var key = keys[i];
        var upperKey = String(key).toUpperCase();

        if (data[key] != null && data[key] !== "") {
          return data[key];
        }

        for (var j = 0; j < lists.length; j++) {
          if (!hasRows(lists[j])) {
            continue;
          }
          var row = lists[j][0];
          var rowKeys = Object.keys(row);
          for (var k = 0; k < rowKeys.length; k++) {
            var rowKey = rowKeys[k];
            if (rowKey.toUpperCase() === upperKey && row[rowKey] != null && row[rowKey] !== "") {
              return row[rowKey];
            }
          }
        }
      }

      return "";
    }

    function normalizeClaimData(data) {
      if (!data) {
        return {};
      }
      if (data.curValOut || data.curValOut2 || data.curValOut3 || data.curValOut4 || data.curValOut5) {
        return mapScrollResponse(data, data.pidId);
      }
      if (Array.isArray(data)) {
        return {
          list: data,
          cumAmount: data.length ? data[0].cumAmount : "",
          cumNonCumAmount: data.length ? data[0].cumNonCumAmount : "",
        };
      }
      return data || {};
    }

    function parseIndianAmount(value) {
      if (value === null || value === undefined || value === "") {
        return null;
      }
      var normalized = String(value).replace(/,/g, "").trim();
      var num = parseFloat(normalized);
      return isNaN(num) ? null : num;
    }

    function formatIndianAmount(num) {
      if (num === null || isNaN(num)) {
        return "";
      }
      var rounded = Math.round(num * 100) / 100;
      return rounded.toLocaleString("en-IN", {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2,
      });
    }

    function getRoundedAmountParts(value) {
      var num = typeof value === "number" ? value : parseIndianAmount(value);
      if (num === null || isNaN(num)) {
        return { value: 0, rupees: 0, paise: 0 };
      }

      var rounded = Math.round(num * 100) / 100;
      var rupees = Math.floor(rounded);
      var paise = Math.round((rounded - rupees) * 100);

      if (paise === 100) {
        rupees += 1;
        paise = 0;
      }

      return { value: rounded, rupees: rupees, paise: paise };
    }

    function convertIntegerToIndianWords(num) {
      if (!num || num === 0) {
        return "";
      }

      var belowTwenty = [
        "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
        "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen",
      ];
      var tens = ["Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"];

      function convertChunk(chunk) {
        var result = "";
        if (chunk >= 100) {
          result += belowTwenty[Math.floor(chunk / 100) - 1] + " Hundred ";
          chunk %= 100;
        }
        if (chunk >= 20) {
          result += tens[Math.floor(chunk / 10) - 2] + " ";
          chunk %= 10;
        }
        if (chunk > 0) {
          result += belowTwenty[chunk - 1] + " ";
        }
        return result.trim();
      }

      var parts = [];
      var crore = Math.floor(num / 10000000);
      num %= 10000000;
      var lakh = Math.floor(num / 100000);
      num %= 100000;
      var thousand = Math.floor(num / 1000);
      num %= 1000;
      var remaining = num;

      if (crore > 0) {
        parts.push(convertChunk(crore) + " Crore" + (crore > 1 ? "s" : ""));
      }
      if (lakh > 0) {
        parts.push(convertChunk(lakh) + " Lakh" + (lakh > 1 ? "s" : ""));
      }
      if (thousand > 0) {
        parts.push(convertChunk(thousand) + " Thousand");
      }
      if (remaining > 0) {
        parts.push(convertChunk(remaining));
      }

      return parts.join(" ").trim();
    }

    function convertAmountToWords(value) {
      var num = parseIndianAmount(value);
      if (num === null || isNaN(num)) {
        return "";
      }
      if (num === 0) {
        return "Zero";
      }

      var rupees = Math.floor(num);
      var paise = Math.round((num - rupees) * 100);
      var words = convertIntegerToIndianWords(rupees) || "Zero";

      if (paise > 0) {
        words += " and " + convertIntegerToIndianWords(paise) + " Paise";
      } else {
        words += " and Paise Zero";
      }

      return words.replace(/Lakh/g, "Lac");
    }

    function parseInputToDate(dateValue) {
      if (!dateValue) {
        return null;
      }
      var parsed = moment(
        dateValue,
        ["DD/MM/YYYY", "DD/MMM/YYYY", "DD-MMM-YYYY", "YYYY-MM-DD", moment.ISO_8601],
        true
      );
      if (!parsed.isValid()) {
        parsed = moment(dateValue);
      }
      return parsed.isValid() ? parsed : null;
    }

    function getMonthEndedLabel() {
      var parsed = parseInputToDate(vm.toDate);
      return parsed ? parsed.format("MMMM YYYY") : moment().format("MMMM YYYY");
    }

    function getTaxInvoiceDateFromInput() {
      var parsed = parseInputToDate(vm.toDate);
      return parsed ? parsed.format("DD-MM-YYYY") : moment().format("DD-MM-YYYY");
    }

    function formatRateValue(rate) {
      var num = parseFloat(rate);
      if (isNaN(num)) {
        return rate || "";
      }
      return num % 1 === 0 ? String(Math.round(num)) : String(num);
    }

    function getTaxInvoiceAmounts(data) {
      var gstRate = formatRateValue(getScrollField(data, "GST_RATE", "gstRate") || "18");
      var cgstRate = formatRateValue(getScrollField(data, "CGST_RATE", "cgstRate") || String(parseFloat(gstRate) / 2));
      var sgstRate = formatRateValue(getScrollField(data, "SGST_RATE", "sgstRate") || String(parseFloat(gstRate) / 2));
      var gstTotal = parseIndianAmount(getScrollField(data, "GST_TOTAL", "gstAmount", "GST")) || 0;
      var cgstAmount = parseIndianAmount(getScrollField(data, "CGST_AMOUNT", "cgstAmount", "CGST"));
      var sgstAmount = parseIndianAmount(getScrollField(data, "SGST_AMOUNT", "sgstAmount", "SGST", "UTGST"));

      if (cgstAmount === null && gstTotal) {
        cgstAmount = gstTotal / 2;
      }
      if (sgstAmount === null && gstTotal) {
        sgstAmount = gstTotal / 2;
      }

      return {
        netSubscription: parseIndianAmount(getScrollField(data, "NET_SUBSCRIPTION", "netSubscription")) || 0,
        handlingCommission: parseIndianAmount(getScrollField(data, "HANDLING_COMMISSION", "handlingCommission")) || 0,
        halfHandlingCommission:
          parseIndianAmount(getScrollField(data, "HANDLING_COMMISSION_50", "halfHandlingCommission")) || 0,
        gstAmount: gstTotal,
        cgstAmount: cgstAmount || 0,
        sgstAmount: sgstAmount || 0,
        totalHalfWithGst: parseIndianAmount(getScrollField(data, "TOTAL_AMOUNT", "totalHalfWithGst", "TOTAL")) || 0,
        cgstRate: cgstRate,
        sgstRate: sgstRate,
        gstRate: gstRate,
      };
    }

    function getHandlingCommissionInvoiceDate() {
      return getTaxInvoiceDateFromInput();
    }

    function calculateHandlingCommissionAmounts(data) {
      var apiNetSubscription = parseIndianAmount(
        getScrollField(data, "NET_SUBSCRIPTION", "netSubscription", "AMOUNT_COLLECTED")
      );
      var apiHandlingCommission = parseIndianAmount(
        getScrollField(data, "HANDLING_COMMISSION", "handlingCommission", "HANDLING_CHARGES")
      );
      var apiHalfHandlingCommission = parseIndianAmount(
        getScrollField(data, "HANDLING_COMMISSION_50", "halfHandlingCommission")
      );
      var apiGstAmount = parseIndianAmount(
        getScrollField(data, "GST_TOTAL", "gstAmount", "GST")
      );
      var apiCgstAmount = parseIndianAmount(
        getScrollField(data, "CGST_AMOUNT", "cgstAmount", "CGST")
      );
      var apiSgstAmount = parseIndianAmount(
        getScrollField(data, "SGST_AMOUNT", "sgstAmount", "SGST", "UTGST")
      );
      var apiTotalHalfWithGst = parseIndianAmount(
        getScrollField(data, "TOTAL_AMOUNT", "totalHalfWithGst")
      );
      var apiCgstRate = getScrollField(data, "CGST_RATE", "cgstRate");
      var apiSgstRate = getScrollField(data, "SGST_RATE", "sgstRate");
      var apiGstRate = getScrollField(data, "GST_RATE", "gstRate");

      var netSubscription = apiNetSubscription || 0;
      var handlingCommission = apiHandlingCommission;
      if (handlingCommission === null) {
        handlingCommission = netSubscription / 1600;
      }

      var halfHandlingCommission = apiHalfHandlingCommission;
      if (halfHandlingCommission === null) {
        halfHandlingCommission = handlingCommission / 2;
      }

      var gstAmount = apiGstAmount;
      if (gstAmount === null) {
        gstAmount = halfHandlingCommission * 0.18;
      }

      var cgstAmount = apiCgstAmount;
      if (cgstAmount === null) {
        cgstAmount = gstAmount / 2;
      }

      var sgstAmount = apiSgstAmount;
      if (sgstAmount === null) {
        sgstAmount = gstAmount / 2;
      }

      var totalHalfWithGst = apiTotalHalfWithGst;
      if (totalHalfWithGst === null) {
        totalHalfWithGst = halfHandlingCommission + gstAmount;
      }

      return {
        netSubscription: netSubscription,
        handlingCommission: handlingCommission,
        halfHandlingCommission: halfHandlingCommission,
        gstAmount: gstAmount,
        cgstAmount: cgstAmount,
        sgstAmount: sgstAmount,
        totalHalfWithGst: totalHalfWithGst,
        cgstRate: apiCgstRate || "9",
        sgstRate: apiSgstRate || "9",
        gstRate: apiGstRate || "18",
      };
    }

    function calculateBrokerageLetterAmounts(data) {
      var netSubscription = parseIndianAmount(
        getScrollField(data, "NET_AMOUNT_COLLECTED", "NET_SUBSCRIPTION", "AMOUNT_COLLECTED")
      );
      var totalBrokerage = parseIndianAmount(
        getScrollField(data, "TOTAL_BROKERAGE", "BROKERAGE_CLAIMED")
      );
      var brokerage90 = parseIndianAmount(
        getScrollField(data, "BROKERAGE_90_RECEIVED", "BROKERAGE_90_ALREADY_PAID")
      );
      var brokerage10 = parseIndianAmount(
        getScrollField(data, "BROKERAGE_10_TO_RECEIVE", "BROKERAGE_10_CLAIMED")
      );
      var handlingCharges = parseIndianAmount(
        getScrollField(data, "HANDLING_CHARGES", "HANDLING_COMMISSION")
      );
      var totalAmount = parseIndianAmount(getScrollField(data, "TOTAL_AMOUNT"));

      var handlingAmounts = calculateHandlingCommissionAmounts(data);

      if (netSubscription === null) {
        netSubscription = handlingAmounts.netSubscription;
      }
      if (totalBrokerage === null) {
        totalBrokerage = netSubscription * 0.005;
      }
      if (brokerage90 === null) {
        brokerage90 = totalBrokerage * 0.9;
      }
      if (brokerage10 === null) {
        brokerage10 = totalBrokerage * 0.1;
      }
      if (handlingCharges === null) {
        handlingCharges = handlingAmounts.handlingCommission;
      }
      if (totalAmount === null) {
        totalAmount = totalBrokerage + handlingCharges;
      }

      return {
        netSubscription: netSubscription,
        totalBrokerage: totalBrokerage,
        brokerage90: brokerage90,
        brokerage10: brokerage10,
        handlingCharges: handlingCharges,
        totalAmount: totalAmount,
        branchOffice: getScrollField(data, "BRANCH_OFFICE"),
      };
    }

    function formatRbiLetterAmountInWords(amount) {
      var parts = getRoundedAmountParts(amount);
      if (!parts.rupees && !parts.paise) {
        return "Zero Rupees and Paise Zero Only.";
      }

      var words = convertIntegerToIndianWords(parts.rupees) || "Zero";
      var paiseWords = parts.paise === 0 ? "Zero" : convertIntegerToIndianWords(parts.paise);

      return words + " Rupees and Paise " + paiseWords + " Only.";
    }

    function getSchemePeriodLabel() {
      var schemeName = vm.schemeName || "";
      var parsedToDate = parseInputToDate(vm.toDate);
      var monthYear = parsedToDate ? parsedToDate.format("MMMM YYYY") : moment().format("MMMM YYYY");
      var shortMonthYear = parsedToDate ? parsedToDate.format("MMM YYYY") : moment().format("MMM YYYY");
      return {
        schemeName: schemeName,
        monthYear: monthYear,
        shortMonthYear: shortMonthYear,
        collectionMonthYear: monthYear,
      };
    }

    function getSolDateLabel() {
      if (!vm.solDate) {
        return moment().format("MMM DD, YYYY");
      }

      var parsed = moment(
        vm.solDate,
        ["DD/MM/YYYY", "DD-MMM-YYYY", "DD-MMM-yyyy", "YYYY-MM-DD", moment.ISO_8601],
        true
      );
      if (!parsed.isValid()) {
        parsed = moment(vm.solDate);
      }

      return parsed.isValid() ? parsed.format("MMM DD, YYYY") : String(vm.solDate);
    }

    function writeUnderlinedText(doc, text, x, y, fontSize) {
      doc.setFont("helvetica", "bold");
      doc.setFontSize(fontSize || 10);
      doc.text(text, x, y);
      var textWidth = doc.getTextWidth(text);
      doc.setLineWidth(0.3);
      doc.line(x, y + 1, x + textWidth, y + 1);
    }

    function loadImageAsBase64(url, callback) {
      var xhr = new XMLHttpRequest();
      xhr.onload = function () {
        var reader = new FileReader();
        reader.onloadend = function () {
          callback(reader.result);
        };
        reader.readAsDataURL(xhr.response);
      };
      xhr.onerror = function () {
        callback(null);
      };
      xhr.open("GET", url);
      xhr.responseType = "blob";
      xhr.send();
    }

    function extractTextFileLine(row) {
      if (!row) {
        return "";
      }
      if (typeof row === "string") {
        return row;
      }

      var preferredKeys = ["number", "NUMBER", "TEXT_LINE", "TEXT", "LINE", "textLine", "text"];
      for (var i = 0; i < preferredKeys.length; i++) {
        if (row[preferredKeys[i]] != null && row[preferredKeys[i]] !== "") {
          return row[preferredKeys[i]];
        }
      }

      var rowKeys = Object.keys(row);
      for (var j = 0; j < rowKeys.length; j++) {
        var rowKey = rowKeys[j];
        if (row[rowKey] != null && row[rowKey] !== "") {
          return row[rowKey];
        }
      }

      return "";
    }

    function buildBrokerageTextFileContent(data) {
      var normalized = normalizeClaimData(data);
      var list = normalized.curValOut5 || normalized.list || [];

      if (!hasRows(list)) {
        return "";
      }

      return list
        .map(function (row) {
          return extractTextFileLine(row);
        })
        .filter(function (line) {
          return line !== "";
        })
        .join("\n");
    }

    function getTaxInvoiceGridStyles() {
      return {
        fontSize: 8,
        cellPadding: 2,
        lineWidth: 0.1,
        lineColor: [0, 0, 0],
        valign: "middle",
      };
    }

    function getTaxInvoiceHeaderCell(content, halign) {
      return {
        content: content,
        styles: {
          fontStyle: "bold",
          fillColor: [255, 255, 255],
          textColor: [0, 0, 0],
          halign: halign || "left",
          lineWidth: 0.1,
          lineColor: [0, 0, 0],
        },
      };
    }

    function getAmountColumnHeader() {
      return "Amount in Rs.";
    }

    function getJsPdfInstance() {
      if (!window.jspdf || !window.jspdf.jsPDF) {
        Swal.fire("Warning", "PDF library not available.", "warning");
        return null;
      }
      return new window.jspdf.jsPDF({ orientation: "portrait", unit: "mm", format: "a4" });
    }

    function downloadHandlingCommissionTaxInvoicePdf(data, callback, options) {
      options = options || {};
      var doc = options.doc || getJsPdfInstance();
      if (!doc) {
        return null;
      }

      if (options.doc && options.newPage) {
        doc.addPage();
      }

      var invoice = data.taxInvoice || data || {};
      var amounts = getTaxInvoiceAmounts(data);
      var margin = 14;
      var pageWidth = doc.internal.pageSize.getWidth();
      var contentWidth = pageWidth - margin * 2;
      var monthEnded = getMonthEndedLabel();
      var invoiceBillNo =
        getScrollField(data, "invoiceBillNo", "INVOICE_BILL_NO", "BILL_NO", "INVOICE_NO") ||
        invoice.invoiceBillNo ||
        invoice.billNo ||
        "NCB" + moment().format("YYYYMMDDHHmmss");
      var invoiceDate = getTaxInvoiceDateFromInput();
      var amountWords = "(Rupees " + convertAmountToWords(amounts.totalHalfWithGst) + " Only)";
      var gstRateLabel = formatRateValue(amounts.gstRate);

      var recipientAddress =
        "Reserve Bank of India, Additional office Building East High Court Road Nagpur-440001";
      var supplierAddress =
        "HDFC Bank Ltd, HDFC Bank House, Senapati Bapat Marg, Lower Parel W";
      var supplierState = "Mumbai";
      var supplierStateCode = "27";
      var recipientGstn = "27AAIFR5286M1ZG";
      var supplierGstn = "27AAACH2702H1Z0";
      var placeOfSupplyState = "Maharashtra";
      var placeOfSupplyStateCode = "27";

      var y = 16;

      doc.setFont("helvetica", "bold");
      doc.setFontSize(12);
      doc.text("Formats for claiming Handling Commission on Bond from RBI", pageWidth / 2, y, { align: "center" });
      y += 7;

      doc.setFont("helvetica", "bold");
      doc.setFontSize(10);
      doc.text("Handling Commission Claim for the month ended " + monthEnded + ".", pageWidth / 2, y, { align: "center" });
      y += 8;

      doc.setFont("helvetica", "bold");
      doc.setFontSize(10);
      doc.text("PART 1 - Tax Invoice**", pageWidth / 2, y, { align: "center" });
      y += 8;

      doc.setFont("helvetica", "normal");
      doc.setFontSize(9);
      doc.text("Invoice / Bill No: " + invoiceBillNo, margin, y);
      doc.text("Date: " + invoiceDate, pageWidth - margin, y, { align: "right" });
      y += 6;

      doc.autoTable({
        startY: y,
        margin: { left: margin, right: margin },
        tableWidth: contentWidth,
        theme: "grid",
        styles: { fontSize: 8, cellPadding: 2, valign: "top" },
        headStyles: { fillColor: [255, 255, 255], textColor: [0, 0, 0], fontStyle: "bold", halign: "left" },
        head: [[
          "Reserve Bank of India (Recipient of Services)",
          "Name of the Agency Bank (Supplier of services):",
        ]],
        body: [[
          "Address: " + recipientAddress,
          "Address of Registered Office of the agency bank submitting the claim: " + supplierAddress +
            "\nState: " + supplierState +
            "\nState Code: " + supplierStateCode,
        ], [
          "GSTN: " + recipientGstn,
          "GSTN: " + supplierGstn,
        ], [
          "PAN: ",
          "PAN: ",
        ], [
          "Place of Supply\nSate Name: " + placeOfSupplyState + "\nState Code: " + placeOfSupplyStateCode,
          "",
        ]],
      });

      y = doc.lastAutoTable.finalY + 6;

      doc.setFont("helvetica", "bold");
      doc.setFontSize(9);
      doc.text("Invoice Details (Handling Commission along with GST)", margin, y);
      y += 4;

      doc.autoTable({
        startY: y,
        margin: { left: margin, right: margin },
        tableWidth: contentWidth,
        theme: "grid",
        showHead: "never",
        styles: getTaxInvoiceGridStyles(),
        columnStyles: {
          0: { cellWidth: contentWidth * 0.68, halign: "left" },
          1: { cellWidth: contentWidth * 0.32, halign: "right" },
        },
        body: [
          [
            getTaxInvoiceHeaderCell("Particulars", "left"),
            getTaxInvoiceHeaderCell(getAmountColumnHeader(), "right"),
          ],
          [
            "A: -- Net Subscription",
            formatIndianAmount(amounts.netSubscription),
          ],
          [
            "B = A * 1/16 * 0.01 -- Handling Commission (1/16 of 1% of Net subscription)",
            formatIndianAmount(amounts.handlingCommission),
          ],
          [
            "C = B / 2 -- 50% portion of Handling Commission",
            formatIndianAmount(amounts.halfHandlingCommission),
          ],
          [
            "D = C * " + gstRateLabel + "% -- GST @ " + gstRateLabel + "% on 50% portion of Handling Commission",
            formatIndianAmount(amounts.gstAmount),
          ],
          [
            { content: "50% Handling Commission + GST", styles: { fontStyle: "bold" } },
            { content: formatIndianAmount(amounts.totalHalfWithGst), styles: { fontStyle: "bold", halign: "right" } },
          ],
        ],
      });

      y = doc.lastAutoTable.finalY + 8;

      if (y > 230) {
        doc.addPage();
        y = 16;
      }

      doc.autoTable({
        startY: y,
        margin: { left: margin, right: margin },
        tableWidth: contentWidth,
        theme: "grid",
        showHead: "never",
        styles: getTaxInvoiceGridStyles(),
        columnStyles: {
          0: { cellWidth: contentWidth * 0.55, halign: "left" },
          1: { cellWidth: contentWidth * 0.15, halign: "center" },
          2: { cellWidth: contentWidth * 0.30, halign: "right" },
        },
        body: [
          [
            getTaxInvoiceHeaderCell("Particulars of Supply", "left"),
            getTaxInvoiceHeaderCell("Rate", "center"),
            getTaxInvoiceHeaderCell(getAmountColumnHeader(), "right"),
          ],
          [
            "Service Description: Undertaking Government banking transaction - RBI Bond",
            "",
            formatIndianAmount(amounts.halfHandlingCommission),
          ],
          [
            "HSN/SAC Code: 997119",
            "",
            "",
          ],
          [
            "HSN/SAC Code Description: Other financial services (except investment banking, insurance services and pension services)",
            "",
            "",
          ],
          [
            "CGST (%)",
            String(amounts.cgstRate),
            formatIndianAmount(amounts.cgstAmount),
          ],
          [
            "SGST/UTGST (%)\nOr\nIGST (%)",
            String(amounts.sgstRate) + "\nOr " + gstRateLabel + "%",
            formatIndianAmount(amounts.sgstAmount) + "\n" + formatIndianAmount(amounts.gstAmount),
          ],
          [
            { content: "GST Total", styles: { halign: "right", fontStyle: "bold" } },
            "",
            { content: formatIndianAmount(amounts.gstAmount), styles: { halign: "right", fontStyle: "bold" } },
          ],
          [
            { content: "Total", styles: { fontStyle: "bold" } },
            "",
            { content: "Rs. " + formatIndianAmount(amounts.totalHalfWithGst), styles: { fontStyle: "bold", halign: "right" } },
          ],
        ],
      });

      y = doc.lastAutoTable.finalY + 8;

      doc.setFont("helvetica", "normal");
      doc.setFontSize(9);
      var wordsLines = doc.splitTextToSize(amountWords, contentWidth);
      doc.text(wordsLines, margin, y);
      y += wordsLines.length * 5 + 8;

      doc.setFont("helvetica", "bold");
      doc.setFontSize(9);
      doc.text("Signature, Name, Designation of Authorized official", margin, y);

      if (!options.doc && !options.skipSave) {
        doc.save("Part1_Tax_Invoice.pdf");
      }
      if (typeof callback === "function") {
        callback();
      }
      return doc;
    }

    function downloadBrokerageHandlingCommissionLetterPdf(data, callback, options) {
      options = options || {};
      var doc = options.doc || getJsPdfInstance();
      if (!doc) {
        return null;
      }

      if (options.doc && options.newPage) {
        doc.addPage();
      }

      var letter = data.rbiLetter || getRbiLetter1Row(data) || {};
      var letterAmounts = getRbiLetter1Amounts(data);
      var period = getSchemePeriodLabel();
      var schemeName = period.schemeName || letter.schemeName || "";
      var margin = 14;
      var pageWidth = doc.internal.pageSize.getWidth();
      var maxWidth = pageWidth - margin * 2;
      var y = 18;

      function writeWrapped(text, bold, xPos, maxLineWidth) {
        doc.setFont("helvetica", bold ? "bold" : "normal");
        doc.setFontSize(10);
        var lines = doc.splitTextToSize(text || "", maxLineWidth || maxWidth);
        lines.forEach(function (line) {
          if (y > 280) {
            doc.addPage();
            y = 18;
          }
          doc.text(line, xPos == null ? margin : xPos, y);
          y += 6;
        });
      }

      writeWrapped("To,", false);
      writeWrapped("The Regional Director,", true);
      writeWrapped("Reserve Bank of India,", true);
      writeWrapped("Central Accounts Section,", false);
      writeWrapped(letter.rbiOffice || "Nagpur", false);
      y += 4;

      writeWrapped("Respected Sir,", false);
      y += 2;

      var subjectText =
        "Ref: Brokerage & Handling Commission \u2013 " + schemeName + " " + period.monthYear;
      doc.setFont("helvetica", "bold");
      doc.setFontSize(10);
      doc.text(subjectText, margin, y);
      var subjectWidth = doc.getTextWidth(subjectText);
      doc.setLineWidth(0.3);
      doc.line(margin, y + 1, margin + subjectWidth, y + 1);
      y += 8;

      writeWrapped(
        "We request you to please release the balance 10% brokerage and handling charges under " + schemeName + " " + period.monthYear + ".",
        false
      );
      y += 4;

      doc.autoTable({
        startY: y,
        margin: { left: margin, right: margin },
        tableWidth: maxWidth,
        theme: "grid",
        showHead: "never",
        styles: { fontSize: 7, cellPadding: 2, halign: "center", valign: "middle", lineWidth: 0.1, lineColor: [0, 0, 0] },
        body: [
          [
            {
              content: "Net Amount Collected During " + period.collectionMonthYear,
              styles: { fontStyle: "bold", halign: "center", fillColor: [255, 255, 255] },
            },
            {
              content: "Total Amount of Brokerage",
              styles: { fontStyle: "bold", halign: "center", fillColor: [255, 255, 255] },
            },
            {
              content: "90% of Brokerage already received",
              styles: { fontStyle: "bold", halign: "center", fillColor: [255, 255, 255] },
            },
            {
              content: "10% of brokerage to be Received",
              styles: { fontStyle: "bold", halign: "center", fillColor: [255, 255, 255] },
            },
            {
              content: "Handling Charges 1/16 of 1% Subscription",
              styles: { fontStyle: "bold", halign: "center", fillColor: [255, 255, 255] },
            },
          ],
          [
            formatIndianAmount(letterAmounts.netSubscription),
            formatIndianAmount(letterAmounts.totalBrokerage),
            formatIndianAmount(letterAmounts.brokerage90),
            formatIndianAmount(letterAmounts.brokerage10),
            formatIndianAmount(letterAmounts.handlingCharges),
          ],
        ],
      });

      y = doc.lastAutoTable.finalY + 8;

      function writeUnderlinedLabelValue(label, value) {
        doc.setFont("helvetica", "bold");
        doc.setFontSize(9);

        var prefix = label + ":- ";
        var prefixWidth = doc.getTextWidth(prefix);
        var valueLines = doc.splitTextToSize(value || "", maxWidth - prefixWidth);

        doc.text(prefix, margin, y);
        doc.setLineWidth(0.2);
        doc.line(margin, y + 0.8, margin + doc.getTextWidth(label), y + 0.8);

        if (valueLines.length > 0) {
          doc.text(valueLines[0], margin + prefixWidth, y);
          y += 6;
          for (var i = 1; i < valueLines.length; i++) {
            doc.text(valueLines[i], margin + prefixWidth, y);
            y += 6;
          }
      } else {
          y += 6;
        }

        y += 2;
      }

      writeUnderlinedLabelValue(
        "10% of brokerage to be Received",
        formatRbiLetterAmountInWords(letterAmounts.brokerage10)
      );
      writeUnderlinedLabelValue(
        "Handling Charges 1/16 of brokerage",
        formatRbiLetterAmountInWords(letterAmounts.handlingCharges)
      );

      y += 6;

      var signY = y;
      var leftX = margin;
      var rightX = pageWidth / 2 + 10;

      doc.setFont("helvetica", "bold");
      doc.setFontSize(10);
      doc.text("For HDFC Bank Ltd.", leftX, signY);
      doc.text("For HDFC Bank Ltd.", rightX, signY);

      signY += 20;

      doc.text(letter.signatoryLeft || "Aniruddha Dive", leftX, signY);
      doc.text(letter.signatoryRight || "Nitin Nimawat", rightX, signY);

      y = signY + 12;

      doc.setFont("helvetica", "normal");
      doc.setFontSize(8);
      writeWrapped("Board Line :- 0712-2454417-2451746 Ext 121, Mob :- 9372499438,", false);

      doc.setFont("helvetica", "normal");
      doc.setFontSize(8);
      var emailPrefix = "Email ID :- ";
      var email1 = "nitin.nimawat@hdfc.bank.in";
      var emailSeparator = ", ";
      var email2 = "Aniruddha.dive@hdfc.bank.in";
      var emailSuffix = ",";
      var emailX = margin;

      doc.text(emailPrefix, emailX, y);
      emailX += doc.getTextWidth(emailPrefix);

      doc.text(email1, emailX, y);
      doc.setLineWidth(0.2);
      doc.line(emailX, y + 0.8, emailX + doc.getTextWidth(email1), y + 0.8);
      emailX += doc.getTextWidth(email1);

      doc.text(emailSeparator, emailX, y);
      emailX += doc.getTextWidth(emailSeparator);

      doc.text(email2, emailX, y);
      doc.line(emailX, y + 0.8, emailX + doc.getTextWidth(email2), y + 0.8);
      emailX += doc.getTextWidth(email2);

      doc.text(emailSuffix, emailX, y);
      y += 6;

      if (!options.doc && !options.skipSave) {
        doc.save("RBI_Letter_1.pdf");
      }
      if (typeof callback === "function") {
        callback();
      }
      return doc;
    }

    function downloadStatementOfChargesPdf(data, callback, options) {
      options = options || {};
      var doc = options.doc || getJsPdfInstance();
      if (!doc) {
        return null;
      }

      if (options.doc && options.newPage) {
        doc.addPage();
      }

      var letterAmounts = calculateBrokerageLetterAmounts(data);
      var period = getSchemePeriodLabel();
      var schemeName = period.schemeName || vm.schemeName || "";
      var margin = 14;
      var pageWidth = doc.internal.pageSize.getWidth();
      var maxWidth = pageWidth - margin * 2;
      var y = 18;
      var monthYear = period.monthYear;

      doc.setFont("helvetica", "bold");
      doc.setFontSize(11);
      doc.text("STATEMENT OF CHARGES FOR", pageWidth / 2, y, { align: "center" });
      y += 6;
      doc.text("BROKERAGE AND HANDLING COMMISSION", pageWidth / 2, y, { align: "center" });
      y += 6;
      doc.setFontSize(10);
      doc.text(vm.schemeName || schemeName || "", pageWidth / 2, y, { align: "center" });
      y += 10;

      doc.setFont("helvetica", "normal");
      doc.setFontSize(10);
      var receivingOfficeLabel = "Receiving Office";
      doc.text(receivingOfficeLabel, margin, y);
      var receivingOfficeLineStart = margin + doc.getTextWidth(receivingOfficeLabel + " ");
      doc.setLineWidth(0.3);
      doc.line(receivingOfficeLineStart, y + 1, receivingOfficeLineStart + 55, y + 1);
      y += 8;

      var billPrefix = "Bill for the month of ";
      doc.text(billPrefix, margin, y);
      var billMonthWidth = doc.getTextWidth(billPrefix);
      doc.setFont("helvetica", "bold");
      doc.text(monthYear, margin + billMonthWidth, y);
      var monthTextWidth = doc.getTextWidth(monthYear);
      doc.setLineWidth(0.3);
      doc.line(margin + billMonthWidth, y + 1, margin + billMonthWidth + monthTextWidth, y + 1);
      y += 8;

      doc.setFont("helvetica", "normal");
      doc.setFontSize(9);
      var introText =
        "Brokerage and Handling Commission claim for the branches/Receiving Offices \u2013 to be compiled by the Controlling Office.";
      var introLines = doc.splitTextToSize(introText, maxWidth);
      introLines.forEach(function (line) {
        doc.text(line, margin, y);
        y += 5;
      });
      y += 4;

      var tableHeaderStyle = {
        fontStyle: "bold",
        halign: "center",
        valign: "middle",
        fillColor: [255, 255, 255],
      };

      doc.autoTable({
        startY: y,
        margin: { left: margin, right: margin },
        tableWidth: maxWidth,
        theme: "grid",
        showHead: "never",
        styles: { fontSize: 7, cellPadding: 2, valign: "middle", lineWidth: 0.1, lineColor: [0, 0, 0] },
        columnStyles: {
          0: { halign: "center", cellWidth: 12 },
          1: { halign: "left" },
          2: { halign: "right" },
          3: { halign: "right" },
          4: { halign: "right" },
          5: { halign: "right" },
          6: { halign: "right" },
        },
        body: [
          [
            { content: "Sr.No", rowSpan: 2, styles: tableHeaderStyle },
            { content: "Branch/ Office", rowSpan: 2, styles: tableHeaderStyle },
            { content: "Amount collected during the month (in Rs)", rowSpan: 2, styles: tableHeaderStyle },
            { content: "Brokerage @ 0.50 Paise per 100 (in Rs)", colSpan: 2, styles: tableHeaderStyle },
            { content: "Handling Commission @ 1/16th of one per cent of total subscription amount received (in Rs)", rowSpan: 2, styles: tableHeaderStyle },
            { content: "Total Amount (in Rs)", rowSpan: 2, styles: tableHeaderStyle },
          ],
          [
            { content: "90% of the brokerage amount already paid", styles: tableHeaderStyle },
            { content: "10% of the brokerage amount claimed", styles: tableHeaderStyle },
          ],
          [
            "1",
            letterAmounts.branchOffice || "Brokerage Claim for " + monthYear,
            formatIndianAmount(letterAmounts.netSubscription),
            formatIndianAmount(letterAmounts.brokerage90),
            formatIndianAmount(letterAmounts.brokerage10),
            formatIndianAmount(letterAmounts.handlingCharges),
            formatIndianAmount(letterAmounts.totalAmount),
          ],
          [
            "",
            "",
            {
              content: "Total Claim: " + formatIndianAmount(letterAmounts.totalAmount),
              styles: { fontStyle: "bold", halign: "right" },
            },
            "",
            "",
            "",
            "",
          ],
        ],
      });

      y = doc.lastAutoTable.finalY + 12;

      doc.setFont("helvetica", "normal");
      doc.setFontSize(9);
      doc.text(
        "Signature of the Controlling Office \u2013 in \u2013 Charge with date and Round Stamp",
        margin,
        y
      );

      if (!options.doc && !options.skipSave) {
        doc.save("RBI_Letter_2.pdf");
      }
      if (typeof callback === "function") {
        callback();
      }
      return doc;
    }

    function renderStatement1BillLetterPdf(data, doc, logoData) {
      var letterAmounts = calculateBrokerageLetterAmounts(data);
        var period = getSchemePeriodLabel();
        var schemeName = period.schemeName || vm.schemeName || "";
        var margin = 14;
        var pageWidth = doc.internal.pageSize.getWidth();
        var maxWidth = pageWidth - margin * 2;
        var shortMonthYear = period.shortMonthYear;
        var y = 16;

        if (logoData) {
          doc.addImage(logoData, "PNG", margin, y - 4, 42, 10);
        }

        doc.setFont("helvetica", "normal");
        doc.setFontSize(10);
        doc.text(getSolDateLabel(), margin, y + 10);
        y += 22;

        doc.text("The Chief General Manager,", margin, y);
        y += 6;
        doc.text("Reserve Bank of India,", margin, y);
        y += 6;
        doc.text("Central Accounts Section,", margin, y);
        y += 6;
        doc.text("Nagpur", margin, y);
        y += 10;

        doc.setFont("helvetica", "bold");
        doc.text("Statement 1", margin, y);
        y += 8;

        writeUnderlinedText(doc, schemeName, margin, y, 10);
        y += 8;

        writeUnderlinedText(doc, "Bill for the month of " + shortMonthYear + ",", margin, y, 10);
        y += 10;

        doc.setFont("helvetica", "normal");
        doc.setFontSize(9);
        var introText =
          "Brokerage/handling commission claim for the branches/offices of bank/corporation - to be compiled by the Controlling office";
        var introLines = doc.splitTextToSize(introText, maxWidth);
        introLines.forEach(function (line) {
          doc.text(line, margin, y);
          y += 5;
        });
        y += 4;

        var claimRateText = "Brokerage claimed @ of Re. 0.50 per Subscription of Rs. 100";
        doc.setFont("helvetica", "bold");
        var claimRateWidth = doc.getTextWidth(claimRateText);
        doc.text(claimRateText, (pageWidth - claimRateWidth) / 2, y);
        doc.setLineWidth(0.3);
        doc.line((pageWidth - claimRateWidth) / 2, y + 1, (pageWidth + claimRateWidth) / 2, y + 1);
        y += 8;

        var tableHeaderStyle = {
          fontStyle: "bold",
          halign: "center",
          valign: "middle",
          fillColor: [255, 255, 255],
        };

        doc.autoTable({
          startY: y,
          margin: { left: margin, right: margin },
          tableWidth: maxWidth,
          theme: "grid",
          showHead: "never",
          styles: { fontSize: 7, cellPadding: 2, valign: "middle", lineWidth: 0.1, lineColor: [0, 0, 0] },
          columnStyles: {
            0: { halign: "left" },
            1: { halign: "right" },
            2: { halign: "right" },
            3: { halign: "right" },
            4: { halign: "right" },
          },
          body: [
            [
              { content: "Branch / Office", styles: tableHeaderStyle },
              { content: "Amount Collected during the month (Rs.)", styles: tableHeaderStyle },
              { content: "Brokerage @ of Re. 0.50 per Rs.100 (Rs.)", styles: tableHeaderStyle },
              { content: "Handling Commission on applications @ 1/16th of 1 percent (Rs.)", styles: tableHeaderStyle },
              { content: "Total Amount (Rs.)", styles: tableHeaderStyle },
            ],
            [
              "1.",
              formatIndianAmount(letterAmounts.netSubscription),
              formatIndianAmount(letterAmounts.totalBrokerage),
              formatIndianAmount(letterAmounts.handlingCharges),
              formatIndianAmount(letterAmounts.totalAmount),
            ],
            [
              "",
              {
                content: "Total Claim: " + formatIndianAmount(letterAmounts.totalAmount),
                styles: { fontStyle: "bold", halign: "right" },
              },
              "",
              "",
              "",
            ],
          ],
        });

        y = doc.lastAutoTable.finalY + 10;

        doc.setFont("helvetica", "normal");
        doc.setFontSize(9);
        doc.text("Signature of the controlling Office", margin, y);
        y += 6;
        doc.text("\u2022 in - charge with date", margin, y);
        y += 12;

        doc.setFont("helvetica", "bold");
        doc.text("CC: The Regional Director", margin, y);
        y += 5;
        doc.setFont("helvetica", "normal");
        doc.text("Reserve Bank of India", margin, y);
        y += 5;
        doc.text("Public Debt Office,", margin, y);
        y += 5;
        doc.text("Main Building", margin, y);
        y += 5;
        doc.text("Shahid Bhagat Singh Road", margin, y);
        y += 5;
        doc.text("Fort Mumbai", margin, y);
        y += 10;

        doc.text("Encl: Appendix -VII for the month of " + shortMonthYear + ".", margin, y);
    }

    function downloadStatement1BillLetterPdf(data, callback, options) {
      options = options || {};

      function finish(doc) {
        if (!options.doc && !options.skipSave) {
          doc.save("Statement_1_Bill_Letter.pdf");
        }
        if (typeof callback === "function") {
          callback(doc);
        }
        return doc;
      }

      if (options.logoData !== undefined) {
        var sharedDoc = options.doc || getJsPdfInstance();
        if (!sharedDoc) {
          return null;
        }
        if (options.doc && options.newPage) {
          sharedDoc.addPage();
        }
        renderStatement1BillLetterPdf(data, sharedDoc, options.logoData);
        return finish(sharedDoc);
      }

      loadImageAsBase64("assets/images/HDFC-Logo.png", function (logoData) {
        var doc = options.doc || getJsPdfInstance();
        if (!doc) {
          if (typeof callback === "function") {
            callback();
          }
          return;
        }

        if (options.doc && options.newPage) {
          doc.addPage();
        }

        renderStatement1BillLetterPdf(data, doc, logoData);
        finish(doc);
      });
    }

//        doc.save("Statement_1_Bill_Letter.pdf");
//        if (typeof callback === "function") {
//          callback();
//        }
//      });
//    }

//    function downloadRbiLetterPdf(data, letterNo) {
    function downloadRbiLetterPdf(data, letterNo) {
      var doc = getJsPdfInstance();
      if (!doc) {
        return;
      }

      var letter = data.rbiLetter || data || {};
      var fromDate = $filter("date")(vm.fromDate, "dd/MM/yyyy");
      var toDate = $filter("date")(vm.toDate, "dd/MM/yyyy");
      var schemeName = vm.schemeName || letter.schemeName || "";
//      var subject =
//        letterNo === "2"
//          ? "Handling Commission claim under " + schemeName + " Scheme"
//          : "Brokerage Claim & Handling Commission under " + schemeName + " Scheme";
      var subject =
        letterNo === "2"
          ? "Handling Commission claim under " + schemeName + " Scheme"
          : "Brokerage Claim & Handling Commission under " + schemeName + " Scheme";
      var margin = 14;
      var pageWidth = doc.internal.pageSize.getWidth();
      var maxWidth = pageWidth - margin * 2;
      var y = 18;

      function writeWrapped(text, bold) {
        doc.setFont("helvetica", bold ? "bold" : "normal");
        doc.setFontSize(10);
        var lines = doc.splitTextToSize(text || "", maxWidth);
        lines.forEach(function (line) {
          if (y > 280) {
            doc.addPage();
            y = 18;
          }
          doc.text(line, margin, y);
          y += 6;
        });
      }

      writeWrapped(vm.bankName || "HDFC Bank Ltd", true);
      y += 2;
      writeWrapped("Date: " + (letter.letterDate || vm.currentDate), true);
      y += 4;
      writeWrapped("To,", false);
      writeWrapped("The General Manager,", true);
      writeWrapped("Reserve Bank of India,", false);
      writeWrapped("Central Accounts Section,", false);
      writeWrapped(letter.rbiOffice || "Nagpur", false);
      y += 4;
      writeWrapped("Madam/Sir", false);
      writeWrapped(subject, true);
      y += 2;
      writeWrapped(
        "We submit herewith the Brokerage Claim and Handling Commission for reimbursement in respect of " +
          schemeName +
          " for the period from " +
          fromDate +
          " to " +
          toDate +
          ". The particulars are mentioned below:",
        false
      );
      y += 4;

      doc.autoTable({
        startY: y,
        margin: { left: margin, right: margin },
        theme: "grid",
        styles: { fontSize: 9, cellPadding: 2 },
        head: [["Particulars", "Amount (Rs.)"]],
        body: [[
          "Amount Collected",
          letter.amountCollected || letter.totalPayment || formatIndianAmount(calculateHandlingCommissionAmounts(data).netSubscription),
        ], [
          "Brokerage",
          letter.brokerageAmt || letter.totalBrokerage || "",
        ], [
          "Handling Commission",
          letter.handlingComm || letter.handlingCommission || formatIndianAmount(calculateHandlingCommissionAmounts(data).handlingCommission),
        ], [
          { content: "Total Claim", styles: { fontStyle: "bold" } },
          { content: letter.totalClaim || letter.totalAmount || formatIndianAmount(calculateHandlingCommissionAmounts(data).totalHalfWithGst), styles: { fontStyle: "bold", halign: "right" } },
        ]],
      });

      y = doc.lastAutoTable.finalY + 8;
      writeWrapped("Kindly arrange to credit the claim amount to our account.", false);
      y += 8;
      writeWrapped("Yours faithfully,", false);
      y += 12;
      writeWrapped("Authorized Signatory", false);
      if (letter.signatoryName) {
        writeWrapped(letter.signatoryName, false);
      }

      doc.save("RBI_Letter_" + letterNo + ".pdf");
    }

    function downloadBlob(content, fileName, contentType) {
      var blob = new Blob([content], { type: contentType });
      var link = document.createElement("a");
      link.href = window.URL.createObjectURL(blob);
      link.download = fileName;
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    }

    function clearInputFields() {
      vm.schemeCode = "";
      vm.schemeName = "";
      vm.schemeStatus = "";
      vm.fromDate = "";
      vm.toDate = "";
    }

    vm.submitForm = function () {
      if ($scope.brokerageClaimHandlingCommissionForm && $scope.brokerageClaimHandlingCommissionForm.$invalid) {
        return;
      }

      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        schemeName: vm.schemeName,
        fromDate: formatScrollRequestDate(vm.fromDate),
        toDate: formatScrollRequestDate(vm.toDate),
        pidId: "1",
      };

      BrokerageClaimHandlingCommissionService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          if (response.data && response.data.result && hasScrollData(response.data.result, "1")) {
            $state.go("brokerage_claim_handling_commission.inquiry_details", {
              obj: {
                schemeCode: vm.schemeCode,
                schemeName: vm.schemeName,
                fromDate: vm.fromDate,
                toDate: vm.toDate,
              },
            });
          } else {
            var message =
              (response.data && response.data.message) ||
              "No records for the given input date";
            clearInputFields();
            Swal.fire(" ", message, "warning");
          }
        },
        function (error) {
          vm.loader = false;
          if (error.data && (error.data.message == "No Data Found" || error.data.message == "NDF" || error.data.message == "No Records Found")) {
            clearInputFields();
            Swal.fire(" ", "No records for the given input date", "warning");
          } else if (error.data && error.data.message) {
            clearInputFields();
            Swal.fire(" ", error.data.message, "warning");
          } else {
            handleErrorResponse(error);
          }
        }
      );
    };

    vm.getSchemeName = function () {
      vm.loader = true;
      BrokerageClaimHandlingCommissionService.getDetails({}).then(
        function (response) {
          vm.loader = false;
          var list = (response.data && response.data.result) || [];
          vm.schemeList = list.map(function (item) {
            return {
              schemeCode: item.schemeCode,
              schemeName: item.schemeDescription,
              schemeStatus: item.schemeStatus || "",
            };
          });
          angular.element("#schemeModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          if (error.data && error.data.message) {
            Swal.fire(" ", error.data.message, "error");
          } else {
            Swal.fire(" ", "Error fetching scheme list", "error");
          }
        }
      );
    };

    vm.selectScheme = function (x) {
      vm.schemeCode = x.schemeCode || x.subCode;
      vm.schemeName = x.schemeName || x.subCodeDescription;
      vm.schemeStatus = x.schemeStatus || x.status || "";
      vm.fromDate = "";
      vm.toDate = "";
    };

    vm.dateValidation = function () {
      vm.toDate = "";
      vm.dateOptions = {
        maxDate: typeof convertToDateObject === "function" ? convertToDateObject(vm.currentDate) : new Date(),
        showWeeks: false,
      };
      vm.dateToOptions = {
        minDate: vm.fromDate,
        maxDate: typeof convertToDateObject === "function" ? convertToDateObject(vm.currentDate) : new Date(),
        showWeeks: false,
      };
    };

    vm.effFromDatePicker = { opened: false };
    vm.effToDatePicker = { opened: false };

    vm.textableDatePicker = textableDatePicker;
    vm.autoslashing = autoslashing;

    function textableDatePicker(id, valueDate) {
      commonService.textableDatePicker(id, valueDate);
    }

    function autoslashing(id) {
      commonService.autoslashing(id);
    }
  }
})();