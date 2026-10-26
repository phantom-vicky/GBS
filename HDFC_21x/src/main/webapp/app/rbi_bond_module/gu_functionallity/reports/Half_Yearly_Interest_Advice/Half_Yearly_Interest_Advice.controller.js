(function () {
  "use strict";
  angular.module("gbmUltraApp").controller("HalfYearlyInterestAdviceController", HalfYearlyInterestAdviceController);

  HalfYearlyInterestAdviceController.$inject = ["$scope", "$filter", "$state", "$timeout", "$stateParams", "$location", "$rootScope", "HalfYearlyInterestAdviceService", "commonService"];

  function HalfYearlyInterestAdviceController($scope, $filter, $state, $timeout, $stateParams, $location, $rootScope, HalfYearlyInterestAdviceService, commonService) {
    var vm = this;
    vm.solDate = $rootScope.globals.currentUser.systemDate;
    vm.getState = getState;

    vm.getSchemeName = function () {
      vm.loader = true;
      var requestData = {
        flag: "S",
      };

      HalfYearlyInterestAdviceService.getDetails(requestData).then(
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

    vm.getPanNumber = function () {
      var requestData = {
        schemeCode: vm.schemeCode,
        holderName: vm.holderName,
        panNumber: vm.panNumber,
        flag: "P",
      };
      HalfYearlyInterestAdviceService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.PanTorch = response.data.result.list;
          angular.element("#panModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    };
    function getState() {
      vm.listType = "STATES OF INDIA";

      var requestData = {
        referenceId: vm.listType,
      };

      HalfYearlyInterestAdviceService.getReferenceData(requestData).then(
        function (response) {
          vm.stateListValues = response.data.result;
          vm.stateList = [];
          angular.forEach(vm.stateListValues, function (item) {
            vm.stateList.push({
              subCodeDescription: item.DESCRIPTION,
              subCode: item.SUBCODE,
            });
          });
        },
        function (error) {
          handleErrorResponse(error);
        }
      );
    }

    vm.convertLabelValue = function (list) {
      for (var item in list) {
        list[item].value = list[item].SUBCODE;
        list[item].label = list[item].DESCRIPTION;
      }
      return list;
    };
    vm.getLabelFromValuesArray = function (value, list) {
      var filter = $filter("getLabelFromValuesArray");
      return filter(value, vm.convertLabelValue(list));
    };

    vm.getBlaNumber = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        holderName: vm.holderName,
        panNumber: vm.panNumber,
        blaNumber: vm.blaNumber,
        flag: "B",
      };
      HalfYearlyInterestAdviceService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaAccNoList = response.data.result.list;
          angular.element("#blaModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    };

    vm.getDetails = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        holderName: vm.holderName,
        panNumber: vm.panNumber,
        blaNumber: vm.blaNumber,
        fromDate: vm.fromDate ? moment(vm.fromDate).format("DD-MMM-YYYY") : "",
        toDate: vm.toDate ? moment(vm.toDate).format("DD-MMM-YYYY") : "",
        flag: "D",
      };

      HalfYearlyInterestAdviceService.getDetails(requestData).then(
        function (response) {
          getState();
          vm.model = response.data.result.list;
          vm.groupedCustomers = {};

          // Group by blaNumber
          vm.model.forEach(function (record) {
            if (!vm.groupedCustomers[record.blaNumber]) {
              vm.groupedCustomers[record.blaNumber] = [];
            }
            vm.groupedCustomers[record.blaNumber].push(record);
          });

          // Convert object to array
          vm.groupedCustomers = Object.values(vm.groupedCustomers);

          vm.calculateTotals(vm.groupedCustomers);
          vm.loader = false;
          vm.clearFields();
        },
        function (error) {
          vm.loader = false;
          if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
            Swal.fire("No Records Found", "There are no records available to download.", "warning");
            vm.clearFields();
          } else {
            handleErrorResponse(error);
          }
        }
      );
    };

    vm.calculateTotals = function (groupedCustomers) {
      vm.customerTotals = {}; // Reset totals

      if (!groupedCustomers || !groupedCustomers.length) {
        return;
      }

      groupedCustomers.forEach((group) => {
        if (!group.length) return; // Skip empty groups

        let blaNumber = group[0].blaNumber; // Use blaNumber as the key
        vm.customerTotals[blaNumber] = {
          totalInvAmount: group.reduce((sum, item) => sum + (parseFloat(item.invAmount) || 0), 0),
          totalIntAmount: group.reduce((sum, item) => sum + (parseFloat(item.intAmount) || 0), 0),
          totalTdsAmount: group.reduce((sum, item) => sum + (parseFloat(item.tdsAmount) || 0), 0),
          totalIntPaid: group.reduce((sum, item) => sum + (parseFloat(item.intPaid) || 0), 0),
        };
      });

      vm.downloadReport(vm.reportType);
    };

    vm.clearFields = function () {
      vm.schemeCode = "";
      vm.schemeName = "";
      vm.holderName = "";
      vm.panNumber = "";
      vm.blaNumber = "";
      vm.fromDate = "";
      vm.toDate = "";
      vm.reportType = "";
    };
    //    vm.totalInvAmount= function () {
    //          return vm.multiData.reduce((sum, record) => sum + (record.invAmount ? parseFloat(record.invAmount) : 0), 0);
    //        };
    //          vm.totalIntAmount= function () {
    //          return vm.multiData.reduce((sum, record) => sum + (record.intAmount ? parseFloat(record.intAmount) : 0), 0);
    //        };
    //vm.totalTdsAmount= function () {
    //          return vm.multiData.reduce((sum, record) => sum + (record.tdsAmount ? parseFloat(record.tdsAmount) : 0), 0);
    //        };
    //        vm.totalIntPaid= function () {
    //          return vm.multiData.reduce((sum, record) => sum + (record.intPaid ? parseFloat(record.intPaid) : 0), 0);
    //        };

    vm.downloadReport = function (reportType) {
      if (reportType === "excel") {
        vm.downloadExcel();
      } else if (reportType === "pdf") {
        vm.downloadPDF();
      }
    };

    vm.downloadPDF = function () {
      const element = document.getElementById("section-to-print");
      element.style.display = "block";

      $timeout(() => {
        html2pdf(element, {
          margin: 10,
          filename: "Report.pdf",
          image: { type: "jpeg", quality: 1 },
          html2canvas: { scale: 2, useCORS: true, scrollX: 0, scrollY: 0 },
          jsPDF: { unit: "mm", format: "a4", orientation: "portrait" },
          pagebreak: { mode: ["css", "avoid-all"] },
        }).then(() => {
          element.style.display = "none";
        });
      }, 2000); // Ensure DOM is fully rendered
      Swal.fire("Success", "Half-Yearly interest advice- Post payment of interest- report and advice", "success");
    };

    vm.downloadExcel = function () {
      vm.reports = {
        HalfYearlyInterestAdvice: {
          filename: "Half-Yearly interest advice- Post payment of interest- report and advice",
          mainHeaders: ["Half-Yearly interest advice- Post payment of interest- report and advice", "Bank Name: INSPIRISY BANK LTD", "Sol ID: 101 / BRANCH OF THE BANK"],
          subHeaders: [
            "Scheme Name: " + vm.schemeName,
            vm.holderName ? "Name: " + vm.holderName : "",
            vm.panNumber ? "PAN No: " + vm.panNumber : "",
            vm.blaNumber ? "BLA No: " + vm.blaNumber : "",
            "From Date: " + vm.fromDate,
            "To Date: " + vm.toDate,
          ].filter(Boolean),
          tableHeaders: [
            { title: "S. No.", width: 10, type: "numeric" },
            { title: "Cour Number", width: 20, type: "string" },
            { title: "Bla Number", width: 20, type: "string" },
            { title: "Holder Name", width: 20, type: "string" },
            { title: "Address1", width: 20, type: "string" },
            { title: "Address2", width: 20, type: "string" },
            { title: "City", width: 20, type: "string" },
            { title: "State", width: 20, type: "string" },
            { title: "Pincode", width: 20, type: "string" },
            { title: "Inv Amount", width: 20, type: "string" },
            { title: "Inv Date", width: 20, type: "string" },
            { title: "Account Number", width: 20, type: "string" },
            { title: "Micr Code", width: 20, type: "string" },
            { title: "Bank Name", width: 20, type: "string" },
            { title: "Int Amount", width: 20, type: "string" },
            { title: "Tds Rate", width: 20, type: "string" },
            { title: "Tds Amount", width: 20, type: "string" },
            { title: "Int Paid", width: 20, type: "string" },
            { title: "Credit Date", width: 20, type: "string" },
            { title: "Hy Int Date", width: 20, type: "string" },
            { title: "Category", width: 20, type: "string" },
            { title: "EmailID", width: 20, type: "string" },
            { title: "Pan Number", width: 20, type: "string" },
            { title: "Remarks", width: 20, type: "string" },
          ],
          get tableData() {
            return (vm.model || []).map((record, index) => [
              (index + 1).toString(),
              record.courNumber || "",
              record.blaNumber || "",
              record.holderName || "",
              record.address1 || "",
              record.address2 || "",
              record.city || "",
              record.state ? vm.getLabelFromValuesArray(record.state, vm.stateListValues) : "",
              record.pinCode || "",
              record.invAmount || "",
              record.invDate || "",
              record.accountNumber || "",
              record.micrCode || "",
              record.bankName || "",
              record.intAmount || "",
              record.tdsRate || "",
              record.tdsAmount || "",
              record.intPaid || "",
              record.creditDate || "",
              record.hyIntDate || "",
              record.category || "",
              record.emailID || "",
              record.panNumber || "",
              record.remarks || "",
            ]);
          },
        },
      };
      const { filename, mainHeaders, subHeaders, tableHeaders, tableData } = vm.reports["HalfYearlyInterestAdvice"];
      const excelData = [];
      mainHeaders.forEach((header) => {
        excelData.push([header, ...Array(tableHeaders.length - 1).fill("")]);
      });
      subHeaders.forEach((header) => {
        excelData.push([header, ...Array(tableHeaders.length - 1).fill("")]);
      });
      excelData.push([]);
      excelData.push(tableHeaders.map((header) => header.title));
      tableData.forEach((record) => {
        excelData.push(Object.values(record));
      });

      const ws = XLSX.utils.aoa_to_sheet(excelData);
      const mergeRanges = [
        { s: { r: 0, c: 0 }, e: { r: 0, c: 10 } },
        { s: { r: 1, c: 0 }, e: { r: 1, c: 10 } },
        { s: { r: 2, c: 0 }, e: { r: 2, c: 10 } },
      ];
      ws["!merges"] = mergeRanges;
      mergeRanges.forEach((merge) => {
        const cell = ws[XLSX.utils.encode_cell(merge.s)];
        if (cell) {
          cell.s = { alignment: { horizontal: "center", vertical: "center" }, font: { bold: true } };
        }
      });
      const wb = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(wb, ws, "Data");
      XLSX.writeFile(wb, filename + ".xlsx");
      Swal.fire("Success", "Half-Yearly interest advice- Post payment of interest- report and advice", "success");
    };

    //    if ($state.current.name == "rbibond_certificate_investment_hold_report.inquiry_details") {
    //      vm.schemeCode = $stateParams.obj.schemeCode;
    //      vm.holderName = $stateParams.obj.holderName;
    //      vm.panNumber = $stateParams.obj.panNumber;
    //      vm.blaNumber = $stateParams.obj.blaNumber;
    //      vm.fromDate = $stateParams.obj.fromDate;
    //      vm.toDate = $stateParams.obj.toDate;
    //      vm.schemeName = $stateParams.obj.schemeName;
    //      if (vm.schemeCode == "0012") {
    //        vm.schemeName = "Floating Rate Savings Bonds, 2020 (Taxable)";
    //      }
    //      vm.getCertificateOfHoldingDetails();
    //    }

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
