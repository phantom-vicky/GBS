(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("rbibondNomineeContinuationReportController", rbibondNomineeContinuationReportController);

  rbibondNomineeContinuationReportController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "NomineeContinuationReportService", "commonService"];

  function rbibondNomineeContinuationReportController($scope, $filter, $state, $stateParams, $location, $rootScope, NomineeContinuationReportService, commonService) {
    var vm = this;
    vm.textableDatePicker = textableDatePicker;
    vm.autoslashing = autoslashing;

    function textableDatePicker(id, valueDate) {
      commonService.textableDatePicker(id, valueDate);
    }
    function autoslashing(id) {
      commonService.autoslashing(id);
    }

    vm.getModuleName = getModuleName;
    vm.fromDateVal = fromDateVal;
    vm.toDateVal = toDateVal;
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

    function getModuleName() {
      vm.loader = true;
      var requestData = {
        flag: "S",
      };
      NomineeContinuationReportService.getDetails(requestData).then(
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
        fromDate: moment(vm.fromDate).format("DD/MM/YYYY"),
        toDate: moment(vm.toDate).format("DD/MM/YYYY"),
        flag: "D",

        pageNo: vm.pageNo,
        pageSize: vm.pageSize,
        searchValue: vm.searchText,
        sortOrder: vm.sortOrder,
        sortField: vm.sortField,
      };

      NomineeContinuationReportService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.nomineeList = response.data.result.list;
//          vm.filteredRecords = vm.nomineeList;
//          vm.totalPages = Math.ceil(vm.nomineeList.length / vm.pageSize);
//          vm.pageDetails = response.data.result;
//
//          let groupedMap = {};
//          vm.nomineeList.forEach((record) => {
//            let key = record.nomineeID;
//            if (!groupedMap[key]) {
//              groupedMap[key] = {
//                ...record,
//                nominees: [],
//              };
//            }
//
//            record.nominees.forEach((nom) => {
//              if (!groupedMap[key].nominees.some((n) => n.nomineeSubscriptionID === nom.nomineeSubscriptionID)) {
//                groupedMap[key].nominees.push(nom);
//              }
//            });
//          });
//
//          vm.groupedCustomers = Object.values(groupedMap);
//
//          vm.nomineeDynamicHeaders = [];
//          let maxNominees = Math.max(...vm.groupedCustomers.map((c) => c.nominees.length));
//          for (let i = 0; i < maxNominees; i++) {
//            vm.nomineeDynamicHeaders.push(
//              { label: `Nominee ${i + 1} PAN`, key: `nomineePanNumber_${i}` },
//              { label: `Nominee ${i + 1} Name `, key: `nomineeName_${i}` },
//              { label: `Nominee ${i + 1} DOB`, key: `nomineeDOB_${i}` },
//              { label: `Nominee ${i + 1} BLA`, key: `nomineeBlaNumber_${i}` },
//              { label: `Nominee ${i + 1} Subscription ID`, key: `nomineeSubscriptionID_${i}` }
//            );
//          }
//
//          vm.groupedCustomers.forEach((customer) => {
//            customer.nomineeFlat = {};
//            customer.nominees.forEach((n, i) => {
//              customer.nomineeFlat[`nomineeName_${i}`] = n.nomineeName;
//              customer.nomineeFlat[`nomineePanNumber_${i}`] = n.nomineePanNumber;
//              customer.nomineeFlat[`nomineeDOB_${i}`] = n.nomineeDOB;
//              customer.nomineeFlat[`nomineeBlaNumber_${i}`] = n.nomineeBlaNumber;
//              customer.nomineeFlat[`nomineeSubscriptionID_${i}`] = n.nomineeSubscriptionID;
//            });
//          });

          vm.total = response.data.total;
                    vm.maxPages = Math.ceil(response.data.total / vm.pageSize);
                    vm.paginationControls();
                    if (vm.nomineeList.length === 0) {
                      vm.noDataFoundInSearch = true;
                    } else {
                      vm.noDataFoundInSearch = false;
                    }
        },
        function (error) {
          vm.loader = false;
          if (vm.searchText && (error.data.message == "No Data Found" || error.data.message == "NDF")) {
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
                $state.go("nominee_continuation_report.inquiry");
          }
        }
      );
    }

    //Date Validation
    function fromDateVal() {
      vm.fromDateOptions = {
        maxDate: vm.toDate ? new Date(vm.toDate) : new Date(),
        showWeeks: false,
      };
    }

    function toDateVal(fromDate) {
      vm.toDateOptions = {
        minDate: new Date(fromDate),
        maxDate: new Date(),
        showWeeks: false,
      };
    }

    if ($state.current.name == "nominee_continuation_report.inquiry_details") {
      vm.schemeName = $stateParams.obj.schemeName;
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.fromDate = $stateParams.obj.fromDate;
      vm.toDate = $stateParams.obj.toDate;
        getDetails();
    }

    // Downloading the report
    vm.downloadReport = function () {

    vm.loader = true;
          var requestData = {
                    schemeCode: vm.schemeCode,
                    fromDate: moment(vm.fromDate).format("DD/MM/YYYY"),
                    toDate: moment(vm.toDate).format("DD/MM/YYYY"),
                    flag: "D",
            pageNo: 1,
            pageSize: vm.total,
            searchValue: vm.searchText,
          };
          if(vm.downloadType=="excel") {
          NomineeContinuationReportService.getDetailsDownload(requestData).then(
            function (response) {
              vm.loader = false;
              vm.nomineeListDownload = response.data.result.list;

                    if (vm.downloadType === "excel") {
//                      vm.downloadExcel();
                        if (response.data.result.fileName) {
                             vm.fileData = response.data.result.fileData;
                             vm.fileName = response.data.result.fileName;
                             saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
                             Swal.fire("Downloaded", response.data.message, "success");
                        }
                    }
                    vm.downloadType = "";
            },
            function (error) {
              vm.loader = false;
              if (error.data.message == "No Data Found" || error.data.message == "NDF") {
                vm.noDataFound = error.data.message;
              } else {
                handleErrorResponse(error);
                $state.go("nominee_continuation_report.inquiry");
              }
            }
          );
          }
    };

    vm.downloadCSV = function () {
      const filename = "Nominee_Continuation_Report";

      const metadata = [
        "Nominee Continuation Report",
        "Bank Name: INSPIRISY BANK LTD",
        "Sol ID: 101 / BRANCH OF THE BANK",
        "Name of the Bond Scheme: " + vm.schemeName,
        "From Date: " + moment(vm.fromDate).format("DD/MM/YYYY"),
        "To Date: " + moment(vm.toDate).format("DD/MM/YYYY"),
      ];

      let txtContent = metadata.join("\n") + "\n\n";

      const staticHeaders = [
        "S.No.",
        "Name of the Bond Scheme",
        "Death Marked Holder PAN",
        "Death Holder Name",
        "Death Marked Holder BLA No",
        "Death Marked Holder Subscription ID",
        "Date of Death",
        "Date of Receipt of Death Certificate",
        "Death Marking Date",
        "Main Subscription Amount",
        "Nominee Sharable Amount & Percentage",
        "Nominee ID",
      ];

      const dynamicHeaders = vm.nomineeDynamicHeaders.map((h) => h.label);

      const extraHeaders = ["Nominee Subscription Amount", "Bond Type", "Account Open Date", "Subscription Transferred to Nominee", "Period of Deposit", "Maturity Date", "Mode of Interest Payment/Principal Repayment"];

      const allHeaders = [...staticHeaders, ...dynamicHeaders, ...extraHeaders];

      const colWidths = allHeaders.map(() => 30);

      function formatRow(row) {
        return row
          .map((val, i) => {
            const value = val != null ? val.toString() : "";
            return value.padEnd(colWidths[i], " ");
          })
          .join(" | ");
      }

      txtContent += formatRow(allHeaders) + "\n";
      txtContent += colWidths.map((w) => "-".repeat(w)).join("-+-") + "\n";

      vm.groupedCustomersDownload.forEach((x, index) => {
        const row = [
          (index + 1).toString(),
          vm.schemeName || "",
          x.deathMarkedHolderPan || "",
          x.deathHolderName || "",
          x.deathMarkedHolderBlaNo || "",
          x.deathMarkedHolderSubscriptionId || "",
          x.dateOfDeathOfBondHolder || "",
          x.dateOfReceiptOfDeathCertificate || "",
          x.deathMarkingDate || "",
          x.mainSubscriptionAmount || "",
          `${x.nomineeSharableAmount || ""} / ${x.nomineeSharablePercentage || ""}`,
          x.nomineeID || "",
        ];

        vm.nomineeDynamicHeaders.forEach((h) => {
          row.push(x.nomineeFlat?.[h.key] || "-");
        });

        row.push(
          x.nomineeSubscriptionAmount || "",
          x.bondType || "",
          x.accountOpenDate || "",
          x.subscriptionTransferredToNominee || "",
          x.periodOfDeposits || "",
          x.maturityDate || "",
          `${x.modeOfInterestPayment || ""}${x.principalRepayment ? " / " + x.principalRepayment : ""}`
        );

        txtContent += formatRow(row) + "\n";
      });

      const blob = new Blob([txtContent], { type: "text/plain;charset=utf-8;" });
      const link = document.createElement("a");
      link.setAttribute("href", URL.createObjectURL(blob));
      link.setAttribute("download", filename + ".txt");
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    };

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
  }
})();
