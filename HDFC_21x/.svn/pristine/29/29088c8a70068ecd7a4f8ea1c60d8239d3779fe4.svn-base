(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("minorAccDetailsController", minorAccDetailsController);

  minorAccDetailsController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "$timeout", "minorAccDetailsService", "commonService"];

  function minorAccDetailsController($scope, $filter, $state, $stateParams, $location, $rootScope, $timeout, minorAccDetailsService, commonService) {
    var vm = this;
    vm.currentPage = 1;
    vm.itemsPerPage = "5";
    vm.getModuleName = getModuleName;

    vm.currentDate = new Date();

    vm.moduleName = "RBI Bond";
    vm.searchValue = "";
    vm.getDetails = getDetails;

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

    //        Download reports
    vm.downloadReport = function () {
    vm.loader = true;
          var requestData = {
        schemeCode: vm.schemeCode,
        flag: "D",
            pageNo: "1",
            pageSize: vm.total,
            searchValue: vm.searchText,
          };
          minorAccDetailsService.getDetails(requestData).then(
            function (response) {
              vm.loader = false;
              vm.modelDownload = response.data.result.list;

                minorAccDetailsService.getReferenceData({ referenceId: "HOLDER_TYPE" }).then(function (holderTypeResult) {
                  var holderTypeDesc = {};
                  holderTypeResult.data.result.forEach(function (item) {
                    holderTypeDesc[item.SUBCODE] = item.DESCRIPTION;
                  });

                  vm.downloadFilteredRecords = vm.modelDownload.map(function (record) {
                    return {
                      ...record,
                      holderType: holderTypeDesc[record.holderType] || "",
                    };
                  });

      if (vm.downloadType === "excel") {
        vm.downloadExcel();
      } else if (vm.downloadType === "csv") {
        vm.downloadCSV();
      } else if (vm.downloadType === "pdf") {
        vm.downloadPDF();
      }
      vm.downloadType = "";
                });
            },
            function (error) {
              vm.loader = false;
              if (error.data.message == "No Data Found" || error.data.message == "NDF") {
                vm.noDataFound = error.data.message;
              } else {
                handleErrorResponse(error);
                $state.go("minor_account_details.inquiry");
              }
            }
          );

    };

    vm.downloadExcel = function () {
      const additionalDetails = [["Minor Account Details"], ["Bank Name: INSPIRISY BANK LTD"], ["Sol ID: 101 / BRANCH OF THE BANK"], ["Name of the Bond Scheme: " + vm.schemeName], []];

      var headers = [["S.No.", "BLA No", "Holder Name", "Subscription ID", "Guardian id", "Guardian Name", "Holder Type", "Investment Dat", "Amount" ,"Minor DOB","Minor Age"]];
      var rows = vm.downloadFilteredRecords.map(function (row, index) {
        return [index + 1, row.blaNo, row.holderName, row.subsId, row.guadId, row.guadName, row.holderType,  row.investmentDate, row.amount, row.minorDOB ,row.minorAge];
      });
      const excelData = [...additionalDetails, ...headers, ...rows];

      const ws = XLSX.utils.aoa_to_sheet(excelData);
      const mergeRanges = [
        { s: { r: 0, c: 0 }, e: { r: 0, c: 10 } },
        { s: { r: 1, c: 0 }, e: { r: 1, c: 10 } },
        { s: { r: 2, c: 0 }, e: { r: 2, c: 10 } },
        { s: { r: 3, c: 0 }, e: { r: 3, c: 10 } },
        { s: { r: 4, c: 0 }, e: { r: 4, c: 10 } },
      ];
      ws["!merges"] = mergeRanges;
      mergeRanges.forEach((merge) => {
        const cell = ws[XLSX.utils.encode_cell(merge.s)];
        if (cell) {
          cell.s = { alignment: { horizontal: "center", vertical: "center" } };
        }
      });
      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb, ws, "Data");
      XLSX.writeFile(wb, "Minor Account Details.xlsx");
    };

    vm.downloadCSV = function () {
      const tableHeader = [
        { title: "S.No.", width: 10 },
        { title: "BLA No", width: 20 },
        { title: "Holder Name", width: 30 },
        { title: "Subscription ID", width: 20 },
        { title: "Guardian id", width: 20 },
        { title: "Guardian Name", width: 30 },
        { title: "Holder Type", width: 30 },
        { title: "Investment Date", width: 20 },
        { title: "Amount", width: 20 },
        { title: "Minor DOB", width: 20 },
        { title: "Minor Age", width: 20 },
      ];

      const formattedHeader = tableHeader.map((col) => col.title.padEnd(col.width, " ")).join("");

      const tableData = vm.downloadFilteredRecords.map((record, index) =>
        tableHeader
          .map((col, colIndex) => {
            const value = [
              index + 1, // S.No.
              record.blaNo || "",
              record.holderName || "",
              record.subsId || "",
              record.guadId || "",
              record.guadName || "",
              record.holderType || "",
              record.investmentDate || "",
               record.amount || "",
                record.minorDOB || "",
                 record.minorAge || "",
            ][colIndex];
            return value.toString().padEnd(col.width, " ");
          })
          .join("")
      );

      const textData = [
        "Minor Account Details",
        "Bank Name: INSPIRISY BANK LTD",
        "Sol ID: 101 / BRANCH OF THE BANK",
        ["Name of the Bond Scheme: " + vm.schemeName],

        formattedHeader,
        "-".repeat(formattedHeader.length),
        ...tableData,
        "".padEnd(formattedHeader.length, " "), // Empty row
      ].join("\n");

      const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData);
      const link = document.createElement("a");
      link.setAttribute("href", textContent);
      link.setAttribute("download", "Minor Account Details.txt");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    };

    vm.downloadPDF = function () {
      const { jsPDF } = window.jspdf;
      const doc = new jsPDF({
        orientation: "landscape",
        unit: "mm",
        format: "a4", // Use standard A4 size
      });

      doc.setFont("Times", "normal");

      // Header details
      const additionalHeaders = [
        { text: "Minor Account Details", fontSize: 14, yPosition: 10 },
        { text: "Bank Name : INSPIRISY BANK LTD", fontSize: 12, yPosition: 17 },
        { text: "Sol ID : 101 / BRANCH OF THE BANK", fontSize: 12, yPosition: 22 },
        { text: "Name of the Bond Scheme: " + (vm.schemeName || "N/A"), fontSize: 12, yPosition: 27 },
      ];

      // Render additional headers
      additionalHeaders.forEach((header) => {
        const pageWidth = doc.internal.pageSize.width;
        const textWidth = doc.getTextWidth(header.text);
        const xPosition = (pageWidth - textWidth) / 2;
        doc.setFontSize(header.fontSize);
        doc.text(header.text, xPosition, header.yPosition);
      });

      // Table Data
      if (!vm.downloadFilteredRecords || vm.downloadFilteredRecords.length === 0) {
        Swal.fire("No Data", "There are no records available to download.", "warning");
        return;
      }

      const tableData = vm.downloadFilteredRecords.map((record, index) => [index + 1, record.blaNo || "", record.holderName || "", record.subsId || "", record.guadId || "", record.guadName || "", record.holderType || "",record.investmentDate || "", record.amount || "", record.minorDOB || "",record.minorAge || "",]);

      const tableHeader = ["S.NO", "BLA No", "Holder Name", "Subscription ID", "Guardian ID", "Guardian Name", "Holder Type", "Investment Date" , "Amount" , "Minor DOB" , "Minor Age"];

      let startY = additionalHeaders[additionalHeaders.length - 1].yPosition + 10;

      doc.autoTable({
        head: [tableHeader],
        body: tableData,
        startY: startY,
        margin: { top: 10, left: 10, right: 10 },
        showHead: "everyPage",
        theme: "grid",
        styles: {
          fontSize: 8,
          overflow: "linebreak",
          halign: "center",
          valign: "middle",
        },
        columnStyles: {
          0: { cellWidth: 15 },
          1: { cellWidth: 30 },
          2: { cellWidth: 35 },
          3: { cellWidth: 35 },
          4: { cellWidth: 35 },
          5: { cellWidth: 35 },
          6: { cellWidth: 30 },
          7: { cellWidth: 20 },
           8: { cellWidth: 30 },
            9: { cellWidth: 30 },
             10: { cellWidth: 30 },
              11: { cellWidth: 30 },
        },
      });

      if (doc.internal.pages.length > 1 && doc.internal.getNumberOfPages() > 1) {
        let lastPage = doc.internal.getNumberOfPages();
        if (doc.internal.pages[lastPage].length === 0) {
          doc.deletePage(lastPage);
        }
      }
      doc.save("Minor Account Details.pdf");
    };

    if ($state.current.name == "minor_account_details.inquiry_detail") {
      if ($stateParams.obj) {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.schemeStatus = $stateParams.obj.schemeStatus;
        getDetails();
      }
    }

    function getModuleName() {
      vm.loader = true;

      var requestData = {
        flag: "S",
      };
      minorAccDetailsService.getDetails(requestData).then(
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


    function getDetails() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        flag: "D",
        pageNo: vm.pageNo,
                pageSize: vm.pageSize,
                searchValue: vm.searchText,
                sortOrder: vm.sortOrder,
                sortField: vm.sortField,
      };

      minorAccDetailsService.getDetails(requestData).then(
        function (response) {
         vm.loader = false;
          vm.model = response.data.result.list;

          minorAccDetailsService.getReferenceData({ referenceId: "HOLDER_TYPE" }).then(function (holderTypeResult) {
            var holderTypeDesc = {};
            holderTypeResult.data.result.forEach(function (item) {
              holderTypeDesc[item.SUBCODE] = item.DESCRIPTION;
            });

            vm.filteredRecords = vm.model.map(function (record) {
              return {
                ...record,
                holderType: holderTypeDesc[record.holderType] || "",
              };
            });

            vm.model = vm.model.map(function (record) {
              return {
                ...record,
                holderType: holderTypeDesc[record.holderType] || "",
              };
            });

            vm.total = response.data.total;
                      vm.maxPages = Math.ceil(response.data.total / vm.pageSize);
                      vm.paginationControls();
                      if (vm.model.length === 0) {
                        vm.noDataFoundInSearch = true;
                      } else {
                        vm.noDataFoundInSearch = false;
                      }
          });
        },
        function (error) {
          vm.loader = false;
          if ((error.data.message == "No Data Found" || error.data.message == "NDF") && vm.searchText) {

                      vm.pageNo ='0';
                      vm.maxPages ='0';
                      vm.total = '0';
                       vm.showNext = false;
            vm.noDataFoundInSearch = true;
          } else if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("minor_account_details.inquiry");
          }
        }
      );
    }
  }
})();
