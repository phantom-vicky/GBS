(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("brokerAppointmentController", brokerAppointmentController);

  brokerAppointmentController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "BrokerAppointmentService", "commonService"];

  function brokerAppointmentController($scope, $filter, $state, $stateParams, $location, $rootScope, BrokerAppointmentService, commonService) {
    var vm = this;
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
     vm.totalBrokerAmountt = "";
      if ($state.current.name == "Broker_Appointment_Enrolled_Report_Letter.inquiry_details") {
           if ($stateParams.obj) {
             vm.brokerAgencyCode = $stateParams.obj.brokerAgencyCode;
             vm.schemeCode = $stateParams.obj.schemeCode;
             vm.schemeName = $stateParams.obj.schemeName;
             vm.fromDate = $stateParams.obj.fromDate;
             vm.toDate = $stateParams.obj.toDate;
             vm.getDetails();
           }
         }

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

    vm.getBrokerAgencyCode = function () {
      vm.loader = true;
      var requestData = {
        flag: "B",
        detailsFlag : "D",
      };

      BrokerAppointmentService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.brokerCode = response.data.result.list;
          BrokerAppointmentService.getReferenceData({ referenceId: "STATES OF INDIA" }).then(function (stateRes) {
            var stateMap = {};
            stateRes.data.result.forEach(function (item) {
              stateMap[item.SUBCODE] = item.DESCRIPTION;
            });

            vm.brokerAgencyCodeList = vm.brokerCode.map(function (record) {
              return {
                ...record,
                branchID: stateMap[record.branchID] || "",
              };
            });
            angular.element("#brokerAgencyModel").modal("toggle");
          });
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    };

    vm.getSchemeName = function () {
      vm.loader = true;
      var requestData = {
        schemeCode: "",
        flag: "S",
        detailsFlag : "D",
      };
      BrokerAppointmentService.getDetails(requestData).then(
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

    function getDetails() {
      vm.loader = true;

      var requestData = {
        brokerAgencyCode: vm.brokerAgencyCode,
        schemeCode: vm.schemeCode,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "D",
        pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchText,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
        schemeName :vm.schemeName,
        detailsFlag : "D",
      };

      BrokerAppointmentService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.model = response.data.result.list;
           vm.totalBrokerAmount = vm.model.reduce((sum, record) => sum + (record.brokerAmount ? parseFloat(record.brokerAmount) : 0), 0);

          BrokerAppointmentService.getReferenceData({ referenceId: "MODE_OF_PAYMENT" }).then(function (paymentRes) {
            var paymentMap = {};
            paymentRes.data.result.forEach(function (item) {
              paymentMap[item.SUBCODE] = item.DESCRIPTION;
            });

            BrokerAppointmentService.getReferenceData({ referenceId: "STATES OF INDIA" }).then(function (stateRes) {
              var stateMap = {};
              stateRes.data.result.forEach(function (item) {
                stateMap[item.SUBCODE] = item.DESCRIPTION;
              });

              BrokerAppointmentService.getReferenceData({ referenceId: "ACC_TYP_CD" }).then(function (stateRes) {
                var accountTypeCode = {};
                stateRes.data.result.forEach(function (item) {
                  accountTypeCode[item.SUBCODE] = item.DESCRIPTION;
                });
                vm.filteredRecords = vm.model.map(function (record) {
                  return {
                    ...record,
                    paymentMode: paymentMap[record.paymentMode] || "",
                    state: stateMap[record.state] || "",
                    brokerState: stateMap[record.brokerState] || "",
                    accountTypeCode: accountTypeCode[record.accountTypeCode] || "",
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
            });
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
            $state.go("Broker_Appointment_Enrolled_Report_Letter.inquiry_details");
          }
        }
      );
    }







    vm.indianNumberFormat=indianNumberFormat;
                    function indianNumberFormat(value) {
                      if (isNaN(value)|| null == value) return value;
                      let numStr = value.toString();
                      let [whole, decimal] = numStr.split('.');
                      if (whole.length <= 3) {
                          return decimal ? whole + '.' + decimal : whole + '.00';
                        }
                      let lastThree = whole.slice(-3);  // last three digits
                      let otherNumbers = whole.slice(0, whole.length - 3);
                      if (otherNumbers !== '') {
                        lastThree = ',' + lastThree;
                      }
                      let formattedWhole = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ',') + lastThree;
                    if (decimal) {
                        return formattedWhole + '.' + decimal;
                      } else {
                        return formattedWhole+'.00';
                      }
                    }
    //    date validation
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

    vm.formatDate = function (tempDate) {
      return $filter("date")(tempDate, "dd/MM/yyyy");
    };

       // Download report
    vm.reports = {
      BrokerAppointmentEnrolledReportLetter: {
        filename: "Broker appointment /enrolled report and broker appointment letter to brokers",
        mainHeaders: ["Broker appointment /enrolled report and broker appointment letter to brokers", "Bank Name: INSPIRISY BANK LTD", "Sol ID: 101 / BRANCH OF THE BANK"],
        subHeaders: [vm.brokerAgencyCode ? "Broker Agency Code: " + vm.brokerAgencyCode : "", "Name of the Bond Scheme: " + vm.schemeName, "From Date: " + vm.formatDate(vm.fromDate), "To Date: " + vm.formatDate(vm.toDate)].filter(Boolean),
        tableHeaders: [
          { title: "S. No.", width: 10, type: "numeric" },

          { title: "BLA no", width: 20, type: "string" },
          { title: "Invest Amount", width: 20, type: "numeric" },
          { title: "CAS Transfer Date", width: 20, type: "string" },
          { title: "Maturity Date", width: 20, type: "string" },
          { title: "Scheme", width: 20, type: "string" },
          { title: "CFD", width: 20, type: "string" },
          { title: "Branch Ref No", width: 20, type: "string" },
          { title: "Advice no", width: 20, type: "string" },
          { title: "Applicant Name", width: 20, type: "string" },
          { title: "Address 1", width: 20, type: "string" },
          { title: "Address 2", width: 20, type: "string" },
          { title: "City", width: 20, type: "string" },
          { title: "State", width: 20, type: "string" },
          { title: "Pin code", width: 20, type: "string" },
          { title: "Branch Name", width: 20, type: "string" },
          { title: "RCPT Date", width: 20, type: "string" },
          { title: "Group Code", width: 20, type: "string" },
          { title: "HDFC Broker Code", width: 20, type: "string" },
          { title: "Broker Name", width: 20, type: "string" },
          { title: "Broker PAN No", width: 20, type: "string" },
          { title: "Address 1", width: 20, type: "string" },
          { title: "Address 2", width: 20, type: "string" },
          { title: "City", width: 20, type: "string" },
          { title: "State", width: 20, type: "string" },
          { title: "Pin code", width: 20, type: "string" },
          { title: "Phone", width: 20, type: "string" },
          { title: "Payment Mode", width: 20, type: "string" },
          { title: "Account Type Code", width: 20, type: "string" },
          { title: "Bank Account", width: 20, type: "string" },
          { title: "MICR Code", width: 20, type: "string" },
          { title: "Application No", width: 20, type: "string" },
          { title: "Status", width: 20, type: "string" },
          { title: "DE Status", width: 20, type: "string" },
          { title: "Broker Amount", width: 20, type: "numeric" },
          { title: "HDFC Branch Code", width: 20, type: "string" },
        ],

        get tableData() {
          return (vm.downloadFilteredRecords || []).map((record, index) => [
            (index + 1).toString(),

            record.blaNumber || "",
            record.investAmount || "",
            record.casTransferDate || "",
            record.maturityDate || "",
            record.scheme || "",
            record.cfd || "",
            record.branchRefNo || "",
            record.adviceNo || "",
            record.applicantName || "",
            record.address1 || "",
            record.address2 || "",
            record.city || "",
            record.state || "",
            record.pinCode || "",
            record.branchName || "",
            record.rcptDate || "",
            record.groupCode || "",
            record.hdfcBrokerCode || "",
            record.brokerName || "",
            record.brokerPanNumber || "",
            record.brokerAddress1 || "",
            record.brokerAddress2 || "",
            record.brokerCity || "",
            record.brokerState || "",
            record.brokerPinCode || "",
            record.phone || "",
            record.paymentMode || "",
            record.accountTypeCode || "",
            record.bankAccount || "",
            record.micrCode || "",
            record.applNo || "",
            record.status || "",
            record.deStatus || "",
            record.brokerAmount || "",
            record.hdfcBranchCode || "",
          ]);
        },






      },
    };

    vm.downloadReport = function () {
      vm.loader = true;
      var requestData = {
        brokerAgencyCode: vm.brokerAgencyCode,
        schemeCode: vm.schemeCode,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "D",
        pageNo: "1",
        pageSize: vm.total,
        searchValue: vm.searchText,
        downloadType : vm.downloadType,
        schemeName : vm.schemeName,
      };
      BrokerAppointmentService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.modelDownload = response.data.result.list;

//           vm.totalBrokerAmountt = vm.modelDownload.reduce((sum, record) => sum + (record.brokerAmount ? parseFloat(record.brokerAmount) : 0), 0);
//
//           const newEntry = { deStatus: "total Amount", brokerAmount: vm.totalBrokerAmountt};
//
//           vm.modelDownload.push(newEntry);




          BrokerAppointmentService.getReferenceData({ referenceId: "MODE_OF_PAYMENT" }).then(function (paymentRes) {
            var paymentMap = {};
            paymentRes.data.result.forEach(function (item) {
              paymentMap[item.SUBCODE] = item.DESCRIPTION;
            });

            BrokerAppointmentService.getReferenceData({ referenceId: "STATES OF INDIA" }).then(function (stateRes) {
              var stateMap = {};
              stateRes.data.result.forEach(function (item) {
                stateMap[item.SUBCODE] = item.DESCRIPTION;
              });

              BrokerAppointmentService.getReferenceData({ referenceId: "ACC_TYP_CD" }).then(function (stateRes) {
                var accountTypeCode = {};
                stateRes.data.result.forEach(function (item) {
                  accountTypeCode[item.SUBCODE] = item.DESCRIPTION;
                });

//                if(vm.downloadType === "csv"){
//
//                vm.downloadFilteredRecords = vm.modelDownload.map(function (record) {
//                  return {
//                    ...record,
//                    paymentMode: paymentMap[record.paymentMode] || "",
//                    state: stateMap[record.state] || "",
//                    brokerState: stateMap[record.brokerState] || "",
//                    accountTypeCode: accountTypeCode[record.accountTypeCode] || "",
//                  };
//                });
//                const currentReport = vm.reports["BrokerAppointmentEnrolledReportLetter"];
//   }

    if (response.data.result.fileName) {


                        vm.fileData = response.data.result.fileData;
                        vm.fileName = response.data.result.fileName;
                        saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
                   }
             else if (vm.downloadType === "csv") {



              vm.downloadFilteredRecords = vm.modelDownload.map(function (record) {
                               return {
                                 ...record,
                                 paymentMode: paymentMap[record.paymentMode] || "",
                                 state: stateMap[record.state] || "",
                                 brokerState: stateMap[record.brokerState] || "",
                                 accountTypeCode: accountTypeCode[record.accountTypeCode] || "",
                               };
                             });
                             const currentReport = vm.reports["BrokerAppointmentEnrolledReportLetter"];





                  vm.downloadCSV(currentReport);
                }
                vm.downloadType = "";
              });
            });
          });
        },
        function (error) {
          vm.loader = false;
          if (error.data.message == "No Data Found" || error.data.message == "NDF") {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
            $state.go("Broker_Appointment_Enrolled_Report_Letter.inquiry");
          }
        }
      );
    };

    vm.downloadCSV = function (currentReport) {
      const { filename, mainHeaders, subHeaders, tableHeaders, tableData } = currentReport;
      const columnWidths = tableHeaders.map((col, index) => {
        const headerLength = col.title.length;
        const maxDataLength = Math.max(headerLength, ...tableData.map((row) => (row[index] ? row[index].toString().length : 0)));
        return Math.max(col.width || 0, maxDataLength);
      });
      const formatRow = (values) => {
        return values
          .map((value, i) => {
            const col = tableHeaders[i];
            const width = columnWidths[i];
            const alignment = col.type === "numeric" ? "right" : "left";
            const stringValue = value ? value.toString() : "";

            if (alignment === "right") {
              return stringValue.padStart(width, " ");
            } else {
              return stringValue.padEnd(width, " ");
            }
          })
          .join(" | "); // Use " | " as a delimiter
      };
      const formattedHeader = formatRow(tableHeaders.map((col) => col.title));
      const formattedData = tableData.map((row) => formatRow(row)).join("\n");
      const textData = [mainHeaders.join("\n"), subHeaders.join("\n"), "-".repeat(formattedHeader.length), formattedHeader, "-".repeat(formattedHeader.length), formattedData, "-".repeat(formattedHeader.length)].join("\n");

      const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData);
      const link = document.createElement("a");
      link.setAttribute("href", textContent);
      link.setAttribute("download", filename + ".txt");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    };

    vm.downloadExcel = function (currentReport) {
      const { filename, mainHeaders, subHeaders, tableHeaders, tableData} = currentReport;
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
        { s: { r: 0, c: 0 }, e: { r: 0, c: tableHeaders.length - 1 } },
        { s: { r: 1, c: 0 }, e: { r: 1, c: tableHeaders.length - 1 } },
        { s: { r: 2, c: 0 }, e: { r: 2, c: tableHeaders.length - 1 } },
        { s: { r: 3, c: 0 }, e: { r: 3, c: tableHeaders.length - 1 } },
        { s: { r: 4, c: 0 }, e: { r: 4, c: tableHeaders.length - 1 } },
        { s: { r: 5, c: 0 }, e: { r: 5, c: tableHeaders.length - 1 } },
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
    };
  }
})();
