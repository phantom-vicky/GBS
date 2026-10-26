(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("AppendixIV805Controller", AppendixIV805Controller);
  AppendixIV805Controller.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "AppendixIV805Service", "commonService"];

  function AppendixIV805Controller($scope, $filter, $state, $stateParams, $location, $rootScope, AppendixIV805Service, commonService) {
    var vm = this;
    vm.textableDatePicker=textableDatePicker;
    vm.autoslashing=autoslashing;

    function textableDatePicker(id,valueDate){
     commonService.textableDatePicker(id,valueDate);
    }
    function autoslashing(id){
     commonService.autoslashing(id);
    }

    vm.getSchemeName = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: "",
        flag: "S",
      };
      AppendixIV805Service.getDetails(requestData).then(
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

    // Date Validation
    vm.fromDateVal = function () {
      vm.fromDateOptions = {
        maxDate: new Date(),
        showWeeks: false,
      };
    };
    vm.toDateVal = function (fromDate) {



    // Convert to Date object (if fromDate is string)
           var from = new Date(fromDate);

           // Last day of the same month as fromDate
           var lastDayOfMonth = new Date(
               from.getFullYear(),
               from.getMonth() + 1,
               0
           );


      vm.toDateOptions = {
        minDate: from,
        showWeeks: false,
      };
    };

    vm.getDetails = function () {
      vm.loader = true;

      var requestData = {
        schemeCode: vm.schemeCode,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "D",
      };

      AppendixIV805Service.getDetails(requestData).then(
        function (response) {
          vm.loader = false;

          vm.partANonCumulativeList = response.data.result.partANonCumulativeList;
          vm.partACumulativeList = response.data.result.partACumulativeList;
          vm.partBNonCumulativeList = response.data.result.partBNonCumulativeList;
          vm.partBCumulativeList = response.data.result.partBCumulativeList;
          vm.partCNonCumulativeList = response.data.result.partCNonCumulativeList;
          vm.partCCumulativeList = response.data.result.partCCumulativeList;
          vm.partDNonCumulativeList = response.data.result.partDNonCumulativeList;
          vm.partDCumulativeList = response.data.result.partDCumulativeList;
          vm.partTotalAmtAcc = response.data.result.partTotalAmtAcc;

          vm.partCAcc = vm.partTotalAmtAcc[0].totalaPartiAccounts;
           vm.partCAmount = vm.partTotalAmtAcc[0].totalaPartiAmount;

                   vm.partNcAcc = vm.partTotalAmtAcc[1].totalaPartiAccounts;
                      vm.partNcAmount = vm.partTotalAmtAcc[1].totalaPartiAmount;

                    vm.noDataFound =
                      vm.partACumulativeList.length === 0 &&
                      vm.partANonCumulativeList.length === 0 &&
                      vm.partBCumulativeList.length === 0 &&
                      vm.partBNonCumulativeList.length === 0 &&
                      vm.partCCumulativeList.length === 0 &&
                      vm.partCNonCumulativeList.length === 0 &&
                      vm.partDCumulativeList.length === 0 &&
                      vm.partDNonCumulativeList.length === 0;

          vm.partANonCumulativeTotalAmount= vm.partANonCumulativeList.reduce((sum, record) => sum + (record.partANcAmount ? parseFloat(record.partANcAmount) : 0), 0);
          vm.partACumulativeTotalAmount= vm.partACumulativeList.reduce((sum, record) => sum + (record.partACAmount ? parseFloat(record.partACAmount) : 0), 0);
          vm.partBNonCumulativeTotalAmount= vm.partBNonCumulativeList.reduce((sum, record) => sum + (record.partBNcAmount ? parseFloat(record.partBNcAmount) : 0), 0);
          vm.partBCumulativeTotalAmount= vm.partBCumulativeList.reduce((sum, record) => sum + (record.partBCAmount ? parseFloat(record.partBCAmount) : 0), 0);
          vm.partCNonCumulativeTotalAmount= vm.partCNonCumulativeList.reduce((sum, record) => sum + (record.partCNcAmount ? parseFloat(record.partCNcAmount) : 0), 0);
          vm.partCCumulativeTotalAmount= vm.partCCumulativeList.reduce((sum, record) => sum + (record.partCCAmount ? parseFloat(record.partCCAmount) : 0), 0);
          vm.partDNonCumulativeTotalAmount= vm.partDNonCumulativeList.reduce((sum, record) => sum + (record.partDNcAmount ? parseFloat(record.partDNcAmount) : 0), 0);
          vm.partDCumulativeTotalAmount= vm.partDCumulativeList.reduce((sum, record) => sum + (record.partDCAmount ? parseFloat(record.partDCAmount) : 0), 0);

          vm.nonCumulativeNetTotal=(vm.partANonCumulativeTotalAmount -vm.partDNonCumulativeTotalAmount);
          vm.cumulativeNetTotal= (vm.partACumulativeTotalAmount-vm.partDCumulativeTotalAmount);




           vm.partNcTotalAcc= (vm.partANonCumulativeList.length-vm.partDNonCumulativeList.length);
                    vm.partCTotalAcc=  (vm.partACumulativeList.length-vm.partDCumulativeList.length);


//            vm.prasadd=vm.partCAmount+vm.cumulativeNetTotal;
            vm.totalCumAmount = (Number(vm.partCAmount) || 0) + (Number(vm.cumulativeNetTotal) || 0);
             vm.totalNCumAmount=  (Number(vm.partNcAmount) || 0) + (Number(vm.nonCumulativeNetTotal) || 0);
              vm.totalCumAccount = (Number(vm.partCAcc) || 0) + (Number(vm.partCTotalAcc) || 0);
              vm.totalNCumAccount=  (Number(vm.partNcAcc) || 0) + (Number(vm.partNcTotalAcc) || 0);


          vm.mergedPartAList = [];

          var maxLength = Math.max(vm.partANonCumulativeList.length, vm.partACumulativeList.length);
          for (var i = 0; i < maxLength; i++) {
            vm.mergedPartAList.push({
              nonCumulative: vm.partANonCumulativeList[i] || {},
              cumulative: vm.partACumulativeList[i] || {},
            });
          }





// TOTAL MAPS
vm.ncDateTotals = {};
vm.cDateTotals = {};

// LAST INDEX MAPS (MAIN FIX)
vm.ncLastIndex = {};
vm.cLastIndex = {};

// LOOP THROUGH MERGED LIST
angular.forEach(vm.mergedPartAList, function (item, index) {

    // NON-CUMULATIVE
    var ncDate = item.nonCumulative.partANcDueDate;

    if (ncDate) {

        if (!vm.ncDateTotals[ncDate]) {
            vm.ncDateTotals[ncDate] = 0;
        }

        vm.ncDateTotals[ncDate] += Number(item.nonCumulative.partANcAmount || 0);

        // store LAST occurrence index
        vm.ncLastIndex[ncDate] = index;
    }

    // CUMULATIVE
    var cDate = item.cumulative.partACDueDate;

    if (cDate) {

        if (!vm.cDateTotals[cDate]) {
            vm.cDateTotals[cDate] = 0;
        }

        vm.cDateTotals[cDate] += Number(item.cumulative.partACAmount || 0);

        // store LAST occurrence index
        vm.cLastIndex[cDate] = index;
    }
});


// CHECK FUNCTION FOR NON-CUMULATIVE
vm.showNcTotal = function (b, index) {

    var date = b.nonCumulative.partANcDueDate;

    return date && vm.ncLastIndex[date] === index;
};


// CHECK FUNCTION FOR CUMULATIVE
vm.showCTotal = function (b, index) {

    var date = b.cumulative.partACDueDate;

    return date && vm.cLastIndex[date] === index;
    };

          vm.mergedPartBList = [];

          var maxLength = Math.max(vm.partBNonCumulativeList.length, vm.partBCumulativeList.length);
          for (var i = 0; i < maxLength; i++) {
            vm.mergedPartBList.push({
              nonCumulative: vm.partBNonCumulativeList[i] || {},
              cumulative: vm.partBCumulativeList[i] || {},
            });
          }


          vm.mergedPartCList = [];

          var maxLength = Math.max(vm.partCNonCumulativeList.length, vm.partCCumulativeList.length);
          for (var i = 0; i < maxLength; i++) {
            vm.mergedPartCList.push({
              nonCumulative: vm.partCNonCumulativeList[i] || {},
              cumulative: vm.partCCumulativeList[i] || {},
            });
          }

          vm.mergedPartDList = [];

          var maxLength = Math.max(vm.partDNonCumulativeList.length, vm.partDCumulativeList.length);
          for (var i = 0; i < maxLength; i++) {
            vm.mergedPartDList.push({
              nonCumulative: vm.partDNonCumulativeList[i] || {},
              cumulative: vm.partDCumulativeList[i] || {},
            });
          }



        vm.partNctotalandsub=(vm.partANonCumulativeList.length + vm.partBNonCumulativeList.length)
                                             - (vm.partCNonCumulativeList.length + vm.partDNonCumulativeList.length);

        },
        function (error) {
          vm.loader = false;
          if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("Appendix_IV_805_Mar_2024.inquiry_details");
          }
        }
      );
    };

    if ($state.current.name == "Appendix_IV_805_Mar_2024.inquiry_details") {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.fromDate = $stateParams.obj.fromDate;
      vm.toDate = $stateParams.obj.toDate;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.getDetails();
    }

    vm.downloadReport = function () {
      if (vm.downloadType === "excel") {
        vm.downloadExcel();
      } else if (vm.downloadType === "csv") {
        vm.downloadTXT();
      }
      vm.downloadType = "";
    };


    vm.formatCasDate = function(dateStr) {
      if (!dateStr) return '';

      var months = {
        JAN: '01', FEB: '02', MAR: '03', APR: '04',
        MAY: '05', JUN: '06', JUL: '07', AUG: '08',
        SEP: '09', OCT: '10', NOV: '11', DEC: '12'
      };

      var parts = dateStr.split('-'); // [02, APR, 2026]

      return parts[0] + months[parts[1]] + parts[2];
    };


    vm.formatIndianCurrency = function(amount) {
                 if (amount === undefined) {
                     return null;
                 }

                        return new Intl.NumberFormat('en-IN', {
                                                                    minimumFractionDigits: 2,
                                                                    maximumFractionDigits: 2
                                                                }).format(amount);
                    }


                   vm.toDatee = $filter('date')(vm.toDate, 'MMM yyyy');

    vm.downloadExcel = function () {
          setTimeout(function () {
            const wb = XLSX.utils.book_new();
            const table1 = document.getElementById("myTable");
            if (!table1) return;

            // Convert HTML table -> sheet data while preserving colspan/rowspan positions.
            const rows = table1.querySelectorAll("thead tr, tbody tr");
            const occupied = {}; // marks positions taken by previous rowspans
            let maxCols = 0;
            let rIndex = 0;
            const sheetData = [];
            const merges = [];
            const styleUpdates = []; // { r, c, font, alignment, fill? }

            const normalizeText = (t) => (t === null || t === undefined ? "" : String(t).replace(/\s+/g, " ").trim());

            rows.forEach((tr) => {
              const rowArr = [];
              let cIndex = 0;
              const cellEls = Array.from(tr.querySelectorAll("th, td"));

              cellEls.forEach((cellEl) => {
                // Skip columns that are already occupied by rowspan cells from previous rows.
                while (occupied[`${rIndex},${cIndex}`]) cIndex++;

                const colspan = parseInt(cellEl.getAttribute("colspan") || "1", 10) || 1;
                const rowspan = parseInt(cellEl.getAttribute("rowspan") || "1", 10) || 1;
                const text = normalizeText(cellEl.innerText);
                const isTH = (cellEl.tagName || "").toLowerCase() === "th";

                // Determine horizontal alignment from inline style if available.
                let horizontalAlign = isTH ? "center" : "left";
                if (cellEl.style && cellEl.style.textAlign) {
                  horizontalAlign = cellEl.style.textAlign.toLowerCase();
                } else {
                  const styleAttr = (cellEl.getAttribute && cellEl.getAttribute("style")) || "";
                  const m = styleAttr.match(/text-align\s*:\s*(left|right|center)/i);
                  if (m && m[1]) horizontalAlign = m[1].toLowerCase();
                }

                rowArr[cIndex] = text;
                for (let k = 1; k < colspan; k++) rowArr[cIndex + k] = "";

                // Add merge only for top-left cell.
                if (colspan > 1 || rowspan > 1) {
                  merges.push({
                    s: { r: rIndex, c: cIndex },
                    e: { r: rIndex + rowspan - 1, c: cIndex + colspan - 1 },
                  });
                }

                // Mark the cells covered by rowspan for the following rows.
                for (let dr = 1; dr < rowspan; dr++) {
                  for (let k = 0; k < colspan; k++) {
                    occupied[`${rIndex + dr},${cIndex + k}`] = true;
                  }
                }

                cIndex += colspan;

                // Save styling for the top row cells covered by colspan.
                for (let k = 0; k < colspan; k++) {
                  styleUpdates.push({
                    r: rIndex,
                    c: cIndex - colspan + k,
                    isTH,
                    horizontalAlign,
                  });
                }
              });

              maxCols = Math.max(maxCols, cIndex, rowArr.length);
              sheetData.push(rowArr);
              rIndex++;
            });

            // Pad all rows to the same column count.
            for (let r = 0; r < sheetData.length; r++) {
              for (let c = 0; c < maxCols; c++) {
                if (sheetData[r][c] === undefined) sheetData[r][c] = "";
              }
            }

            // Create worksheet + merges
            const ws = XLSX.utils.aoa_to_sheet(sheetData);
            ws["!merges"] = merges;

            // Basic styling so borders/alignment match more closely.
            const thinBlack = { style: "thin", color: { rgb: "FF000000" } };
            const borderStyle = { top: thinBlack, bottom: thinBlack, left: thinBlack, right: thinBlack };
            const noBorder = { top: { style: "none" }, bottom: { style: "none" }, left: { style: "none" }, right: { style: "none" } };
            const defaultFont = { name: "Times New Roman", sz: 10, color: { rgb: "FF000000" } };
            const headingFont = { name: "Times New Roman", sz: 9, color: { rgb: "FF000000" }, bold: true };
            // Never wrap text in cells (headings and data).
            const defaultAlignment = { horizontal: "center", vertical: "center", wrapText: false };

            const headingFill = {
              patternType: "solid",
              fgColor: { rgb: "FFE6E6E6" },
              bgColor: { rgb: "FFE6E6E6" },
            };

            const colCount = maxCols;
            const rowCount = sheetData.length;

            const ensureCell = (r, c) => {
              const addr = XLSX.utils.encode_cell({ r, c });
              if (!ws[addr]) ws[addr] = { t: "s", v: "" };
              return ws[addr];
            };

            // Apply borders + alignment for the whole sheet region (no wrap on any cell).
            for (let r = 0; r < rowCount; r++) {
              for (let c = 0; c < colCount; c++) {
                const cell = ensureCell(r, c);
                // Remove borders for the last (L) column only.
                const border = c === colCount  ? noBorder : borderStyle;
                cell.s = {
                  font: defaultFont,
                  alignment: { ...defaultAlignment, wrapText: false },
                  border: border,
                };
              }
            }

            // Apply saved style updates (font/alignment/fill) to the computed top-left positions.
            styleUpdates.forEach((u) => {
              const cell = ensureCell(u.r, u.c);
              if (u.isTH) {
                cell.s.font = headingFont;
                cell.s.alignment = { horizontal: "center", vertical: "center", wrapText: false };
                cell.s.fill = headingFill;
              } else {
                cell.s.font = defaultFont;
                cell.s.alignment = { horizontal: u.horizontalAlign || "left", vertical: "center", wrapText: false };
              }
            });

            // Column L (last column): remove grey fill on rows where APPENDIX IV appears (reference: L4 style).
            const lastCol = colCount - 1;
            for (let r = 0; r < rowCount; r++) {
              const rowText = (sheetData[r] || []).map((x) => (x == null ? "" : String(x))).join(" ");
              if (rowText.indexOf("APPENDIX IV") !== -1) {
                const cellL = ensureCell(r, lastCol);
                if (cellL.s) delete cellL.s.fill;
              }
            }

            // Widen columns slightly so text does not need to wrap (wrapText stays false).
            const wchMax = 45;
            const wchMin = 8;
            for (let c = 0; c < colCount; c++) {
              let maxLen = 0;
              for (let r = 0; r < rowCount; r++) {
                const v = sheetData[r][c];
                const s = v === null || v === undefined ? "" : String(v);
                maxLen = Math.max(maxLen, s.length);
              }
              const wch = Math.min(wchMax, Math.max(wchMin, maxLen + 2));
              if (!ws["!cols"]) ws["!cols"] = [];
              ws["!cols"][c] = { wch: wch };
            }

            // Final pass: force wrapText false on every styled cell (merged cells included).
            for (let r = 0; r < rowCount; r++) {
              for (let c = 0; c < colCount; c++) {
                const addr = XLSX.utils.encode_cell({ r, c });
                const cell = ws[addr];
                if (cell && cell.s && cell.s.alignment) {
                  cell.s.alignment.wrapText = false;
                }
              }
            }



           XLSX.utils.book_append_sheet(wb, ws, "Appendix IV" + vm.toDatee);
           XLSX.writeFile(wb, "Appendix IV" + vm.toDatee + ".xlsx");
            if (!$scope.$$phase) $scope.$apply();
          }, 0);
    };

    vm.formatIndianNumber = function (value) {
        if (!value) return "0";
        return Number(value).toLocaleString('en-IN');
    };

    vm.downloadTXT = function () {
      setTimeout(function () {
        var table = document.getElementById("myTable1");
        if (!table) return;

        var rows = table.querySelectorAll("tr");
        var data = Array.from(rows).map((row) => {
          return Array.from(row.children).flatMap((cell) => {
            var span = parseInt(cell.getAttribute("colspan") || "1", 10);
            var text = cell.innerText.trim();

            if (span > 1) {
              return [text, ...Array(span - 1).fill("")];
            } else {
              return [text];
            }
          });
        });

        var colWidths = [];
        data.forEach((row) => {
          row.forEach((cell, i) => {
            colWidths[i] = Math.max(colWidths[i] || 0, cell.length);
          });
        });

        var txt = "";
        data.forEach((row) => {
          row.forEach((cell, i) => {
            txt += cell.padEnd(colWidths[i] + 2, " ");
          });
          txt += "\n";
        });

        var blob = new Blob([txt], { type: "text/plain" });
        var link = document.createElement("a");
        link.download = "Appendix_IV_8.05%_Mar_2024.txt";
        link.href = window.URL.createObjectURL(blob);
        link.click();
        if (!$scope.$$phase) {
          $scope.$apply();
        }
      }, 0);
    };

  }
})();
