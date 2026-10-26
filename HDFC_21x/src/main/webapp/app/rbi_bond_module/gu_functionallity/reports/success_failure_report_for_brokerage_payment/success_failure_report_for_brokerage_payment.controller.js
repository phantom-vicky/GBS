(function () {
  "use strict";

  angular
    .module("gbmUltraApp")
    .controller(
      "successFailureBrokerageController",
      successFailureBrokerageController
    );

  successFailureBrokerageController.$inject = [
    "$scope",
    "$filter",
    "$state",
    "$stateParams",
    "$location",
    "$rootScope",
    "successFailureBrokerageService",
    "commonService",
  ];

  function successFailureBrokerageController(
    $scope,
    $filter,
    $state,
    $stateParams,
    $location,
    $rootScope,
    successFailureBrokerageService,
    commonService
  ) {
    var vm = this;

    vm.currentPage = 1;
    vm.itemsPerPage = "5";
    vm.loginSolId = $rootScope.globals.currentUser.solId;
    console.log("loginid",vm.loginSolId);
    vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
    console.log("logindesc",vm.loginSolDesc);
    vm.solDate = $rootScope.globals.currentUser.systemDate;
    vm.getsuccessfailureReport = getsuccessfailureReport;
    vm.getModuleName = getModuleName;
    vm.getBlaAccNo = getBlaAccNo;
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


    vm.solDate = $filter("date")($rootScope.globals.currentUser.systemDate, "dd/MM/yyyy");

    vm.statusMapping = {
        "S": "SUCCESS",
        "F": "FAILURE",
        "I": "INITIATED",
        "A": "ALL"
    };

     vm.downloadReport = function () {
              console.log("The download Type ", vm.downloadType);
              if (vm.downloadType === "excel") {
                console.log("The download Type Excel working");
                vm.downloadExcel(vm.ListOfDtos);
              }
              if (vm.downloadType === "csv") {
                console.log("The download Type CSV working");
                vm.downloadCSV(vm.ListOfDtos);
              }
              if (vm.downloadType === "pdf") {
                          console.log("The download Type pdf is working");
                          window.print()
    //                      vm.downloadCSV();
                        }
               vm.downloadType = '';
            };

    vm.downloadExcel = function () {
        if (!vm.ListOfDtos || vm.ListOfDtos.length === 0) {
            alert("No records to download.");
            return;
        }

        var title = [["Success/Failure Report For Brokerage Payment Report"]];
        var additionalDetails = [
            ["Bank Name : HDFC"],
            ["Branch Name : BRANCH OF THE BANK"],
            ["Branch Id : 101"]
        ];
        var headers = [
            ["Invoice Number", "Invoice Date", "Broker Name", "Broker Code",
            "Broker PAN", "Broker Email ID", "Gross Brokerage", "GST",
            "Net Brokerage", "VBS Ref Number", "Invoice Received Date",
            "Invoice Raised On", "VBMS Maker", "VBMS Checker",
            "Payment Reference Date", "Paid On Date"]
        ];


        var rows = vm.ListOfDtos.map(function (record) {
            return [
                record.invoiceNumber,
                record.invoiceDate,
                record.brokerName,
                record.brokerCode,
                record.brokerPan,
                record.brokerEmailId,
                record.grossBrokerage,
                record.gst,
                record.netBrokerage,
                record.vbsRefNumber,
                record.invoiceReceiveDate,
                record.invoiceRaisedOn,
                record.vbmsMaker,
                record.vbmsChecker,
                record.paymentRefNumber,
                record.paidOnDate
            ];
        });

        var excelData = [].concat(title, additionalDetails, headers, rows);


        var wb = XLSX.utils.book_new();


        var ws = XLSX.utils.aoa_to_sheet(excelData);


        XLSX.utils.book_append_sheet(wb, ws, "Data");

        XLSX.writeFile(wb, `Success_Failure_Brokerage_Payment_Report_${new Date().toLocaleDateString()}.xlsx`);
    };




vm.downloadCSV = function () {
    if (!vm.ListOfDtos || vm.ListOfDtos.length === 0) {
        alert("No records to download.");
        return;
    }
    var title = ["Success/Failure Report For Brokerage Payment Report"];
    var additionalDetails = [
            "Bank Name : HDFC",
            "Branch Name : BRANCH OF THE BANK",
            "Branch Id : 101"
        ];
    var headers = [
        "Invoice Number", "Invoice Date", "Broker Name", "Broker Code",
        "Broker PAN", "Broker Email ID", "Gross Brokerage", "GST",
        "Net Brokerage", "VBS Ref Number", "Invoice Received Date",
        "Invoice Raised On", "VBMS Maker", "VBMS Checker",
        "Payment Reference Date", "Paid On Date"
    ];

    var rows = vm.ListOfDtos.map(function (record) {
        return [
            record.invoiceNumber,
            record.invoiceDate,
            record.brokerName,
            record.brokerCode,
            record.brokerPan,
            record.brokerEmailId,
            record.grossBrokerage,
            record.gst,
            record.netBrokerage,
            record.vbsRefNumber,
            record.invoiceReceiveDate,
            record.invoiceRaisedOn,
            record.vbmsMaker,
            record.vbmsChecker,
            record.paymentRefNumber,
            record.paidOnDate
        ];
    });

    var csvData = [
            title.join(','),
            additionalDetails[0],
            additionalDetails[1],
            additionalDetails[2],
            headers.join(',')
        ].concat(rows.map(row => row.join(',')));


        var csvContent = csvData.join("\n");
        var blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
        var link = document.createElement("a");
        link.setAttribute("href", URL.createObjectURL(blob));
        link.setAttribute("download", `Success_Failure_Brokerage_Payment_Report_${new Date().toLocaleDateString()}.csv`);
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
};


    vm.loginSolDate = $rootScope.globals.currentUser.solDate;

    vm.fromDateVal = fromDateVal;
    vm.toDateVal = toDateVal;

    if (
      $state.current.name ==
      "success_failure_report_for_brokerage_payment.inquiry_details"
    ) {
      vm.fromDate = $stateParams.obj.fromDate;
      vm.toDate = $stateParams.obj.toDate;
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.moduleName=$stateParams.obj.moduleName;
      vm.status = $stateParams.obj.status;

      getsuccessfailureReport();

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
      var requestData = {
        schemeCode: "",
      };
      successFailureBrokerageService.getModule(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result.list;
          console.log("get module ", response.data.result);
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
      successFailureBrokerageService.getDetails(requestData).then(
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

    function getsuccessfailureReport() {
      vm.loader = true;
      console.log("vm.toDate  ==>", vm.toDate);

      var requestData = {
        schemeCode: vm.schemeCode,
        status: vm.status,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
      };

      successFailureBrokerageService.getReportDetails(requestData).then(
        function (response) {
          vm.loader = false;

          vm.ListOfDtos = response.data.result.successFailureReportDtoList;
          console.log("List of DTOs ", response.data.result);
          configDataTable();
        },
        function (error) {
          vm.loader = false;
          if (
            (error.data.message == "No Data Found") |
            (error.data.message == "NDF")
          ) {
            vm.noDataFound = error.data.message;

          } else {
            handleErrorResponse(error);
            $state.go("success_failure_report_for_brokerage_payment.inquiry");
          }
        }
      );
    }

    function fromDateVal() {
      vm.fromDateOptions = {
        maxDate: convertToDateObject(),
        showWeeks: false,
      };
    }

    function toDateVal(fromDate) {
      vm.toDateOptions = {
        minDate: fromDate,
        maxDate: convertToDateObject(),
        showWeeks: false,
      };
    }
  }
})();
