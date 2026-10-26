(function () {

  'use strict';

  angular.module('gbmUltraApp').controller('reportCancellationSubsCatCbtController',
    reportCancellationSubsCatCbtController);

  reportCancellationSubsCatCbtController.$inject = ['$scope', '$filter', '$state',
    '$stateParams', '$location', '$rootScope', '$timeout', 'reportCancellationSubsCatCbtService', 'commonService'
  ];

  function reportCancellationSubsCatCbtController($scope, $filter, $state, $stateParams,
    $location, $rootScope, $timeout, reportCancellationSubsCatCbtService, commonService) {

    var vm = this;

    vm.loginSolId = $rootScope.globals.currentUser.solId;
    vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
    vm.solDate = $filter('date')($rootScope.globals.currentUser.systemDate, "dd/MM/yyyy");
    vm.currentDate = new Date();
    vm.panTorch = panTorch;
    vm.getModuleName = getModuleName;
    //    vm.nameTorch = nameTorch;
    vm.blaTorch = blaTorch;
    vm.branchRefTorch = branchRefTorch;
    vm.getDetailspage = getDetailspage;

    // Download Excel function
    vm.downloadExcelCA = downloadExcelCA;
    vm.downloadExcelCB = downloadExcelCB;


    // Pan Validation
    vm.panPattern = /[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}/;

    function getModuleName() {
      vm.loader = true;
      var requestData = {
        //       schemeCode:'',
        flag: "S",
      };
      reportCancellationSubsCatCbtService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result.list;
          console.log("vm.schemeList", vm.schemeList);
          angular.element("#schemeModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          handleErrorResponse(error);
        }
      );
    }
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




    vm.clearFields = function () {
      vm.holderName = '';
      vm.panNo = '';
      vm.fromdate = '';
      vm.todate = '';
      vm.branchRefNo = '';
      vm.blaNo = '';
    };

    //    function getDetailspage() {
    //      vm.loader = true;
    //      var requestData = {
    //        schemeCode:vm.schemeCode,
    //        flag: vm.closureType,
    //        panNo:vm.panNo,
    //        bla : vm.blaNo,
    //        branchRefno:vm.branchRefNo,
    //        holderName:vm.holderName,
    //        fromDate: $filter("date")(vm.fromdate, "dd-MMM-yyyy"),
    //        toDate: $filter("date")(vm.todate, "dd-MMM-yyyy"),
    //      };
    //
    //      reportCancellationSubsCatCbtService.getDetails(requestData).then(
    //        function (response) {
    //          vm.loader = false;
    //          vm.DetailsList = response.data.result.list;
    //          console.log("vm.DetailsList",vm.DetailsList);
    //           if (vm.closureType === "A") {
    //             downloadExcelCA(vm.DetailsList);
    //             console.log("downloadExcelCA");
    //             } else if (vm.closureType === "B") {
    //             downloadExcelCB(vm.DetailsList);
    //             console.log("downloadExcelCB");
    //             }
    //
    //           // Clear the form fields
    //             vm.schemeCode = '';
    //             vm.schemeName = '';
    //             vm.schemeStatus = '';
    //             vm.holderName = '';
    //             vm.panNo = '';
    //             vm.fromdate = '';
    //             vm.todate = '';
    //             vm.closureType = '';
    //             vm.branchRefNo = '';
    //             vm.blaNo = '';
    //        },
    //        function (error) {
    //          vm.loader = false;
    //          if (
    //            error.data.message == "No Data Found" ||
    //            error.data.message == "NDF"
    //          ) {
    //            vm.noDataFound = error.data.message;
    //            Swal.fire("No Records Found", "There are no records available to download.", "warning");
    //          } else {
    //            handleErrorResponse(error);
    //
    ////            $state.go("report_for_cancellation_subscription_cat_cbt.inquiry");
    //          }
    //        }
    //      );
    //    }

   function downloadExcelCA(detailsList) {

     if (!detailsList || detailsList.length === 0) {
       Swal.fire("No Records Found", "There are no records available to download.", "warning");
       return;
     }

     const additionalDetails = [
       ["Report for Cancellation of Subscription CAT"],
       ["Bank Name: HDFC BANK LTD"],
       ["Sol ID: 101 / BRANCH OF THE BANK"],
       ["Name of the Bond Scheme: " + vm.schemeName],
       ["From Date : " + $filter("date")(vm.fromdate, "dd-MM-yyyy")
         + "  To Date : " + $filter("date")(vm.todate, "dd-MM-yyyy")
       ],
       [],
     ];

     const mainHeaders = [
       ["Transaction Details"]
     ];

     const headers = [[
       "S.No", "Name of the Bond Scheme", "Mode of Holding", "BLA Number","Branch Ref No",
       "Bond Holder Name", "Subscription Amount", "Bond Type",
       "Account Open Date (dd/mm/yyyy)", "Value Date (dd/mm/yyyy)",
       "Maturity Date (dd/mm/yyyy)", "Rate of Interest (%)",
       "Transaction Mode", "Remarks", "Maker Id", "Maker Date",
       "Checker Id", "Checker Date"
     ]];

     const rows = detailsList.map(function (row, index) {
       return [
         index + 1,
         row.schemeName,
         row.modeofHolding,
         row.bla,
         row.refNo,
         row.holderName,
         row.subsamt,
         row.bondtype,
         row.accOpenDate,
         row.valueDate,
         row.maturityDate,
         row.rateofInterest,
         row.transactionMode,
         row.remarks,
         row.makerId,
         row.makerDate,
         row.checkerId,
         row.checkerDate
       ];
     });

     const excelData = [...additionalDetails, ...mainHeaders, ...headers, ...rows];

     const ws = XLSX.utils.aoa_to_sheet(excelData);

     /* ====== MERGE ====== */
     const mergeRanges = [
       { s: { r: 0, c: 0 }, e: { r: 0, c: 16 } },
       { s: { r: 1, c: 0 }, e: { r: 1, c: 16 } },
       { s: { r: 2, c: 0 }, e: { r: 2, c: 16 } },
       { s: { r: 3, c: 0 }, e: { r: 3, c: 16 } },
       { s: { r: 4, c: 0 }, e: { r: 4, c: 16 } },
       { s: { r: 6, c: 0 }, e: { r: 6, c: 16 } } // Transaction Details row
     ];

     ws["!merges"] = mergeRanges;

     /* ====== ALIGN CENTER ====== */
     mergeRanges.forEach((merge) => {
       const cell = ws[XLSX.utils.encode_cell(merge.s)];
       if (cell) {
         cell.s = {
           alignment: { horizontal: "center", vertical: "center" },
           font: { bold: true }
         };
       }
     });

     /* ====== 🔥 HEADER BOLD FIX ====== */
     const headerRowIndex = additionalDetails.length + mainHeaders.length; // dynamic index

     for (let col = 0; col < headers[0].length; col++) {
       const cellAddress = XLSX.utils.encode_cell({ r: headerRowIndex, c: col });

       if (ws[cellAddress]) {
         ws[cellAddress].s = {
           font: { bold: true }
         };
       }
     }

     const wb = XLSX.utils.book_new();
     XLSX.utils.book_append_sheet(wb, ws, "Data");

     XLSX.writeFile(wb, "Report for Cancellation of Subscription CAT.xlsx");

     Swal.fire("Success", "Report downloaded successfully", "success");
   }

   function downloadExcelCB(detailsList) {

     if (!detailsList || detailsList.length === 0) {
       Swal.fire("No Records Found", "There are no records available to download.", "warning");
       return;
     }

     const additionalDetails = [
       ["Report for Cancellation of Subscription CBT"],
       ["Bank Name: HDFC BANK LTD"],
       ["Sol ID: 101 / BRANCH OF THE BANK"],
       ["Name of the Bond Scheme: " + vm.schemeName],
       ["From Date : " + $filter("date")(vm.fromdate, "dd-MM-yyyy")
         + "  To Date : " + $filter("date")(vm.todate, "dd-MM-yyyy")
       ],
       [],
     ];

     /* ✅ MISSING IN YOUR CODE */
     const mainHeaders = [
       ["Transaction Details"]
     ];

     const headers = [[
       "S.No", "Name of the Bond Scheme", "Mode of Holding", "BLA Number","Branch Ref No",
       "Bond Holder Name", "Subscription Amount", "Bond Type",
       "Account Open Date (dd/mm/yyyy)", "Value Date (dd/mm/yyyy)",
       "Maturity Date (dd/mm/yyyy)", "Rate of Interest (%)",
       "Transaction Mode", "Remarks", "Maker Id", "Maker Date",
       "Checker Id", "Checker Date"
     ]];

     const rows = detailsList.map(function (row, index) {
       return [
         index + 1,
         row.schemeName,
         row.modeofHolding,
         row.bla,
         row.refNo,
         row.holderName,
         row.subsamt,
         row.bondtype,
         row.accOpenDate,
         row.valueDate,
         row.maturityDate,
         row.rateofInterest,
         row.transactionMode,
         row.remarks,
         row.makerId,
         row.makerDate,
         row.checkerId,
         row.checkerDate
       ];
     });

     const excelData = [...additionalDetails, ...mainHeaders, ...headers, ...rows];

     const ws = XLSX.utils.aoa_to_sheet(excelData);

     /* ===== MERGE ===== */
     const mergeRanges = [
       { s: { r: 0, c: 0 }, e: { r: 0, c: 16 } },
       { s: { r: 1, c: 0 }, e: { r: 1, c: 16 } },
       { s: { r: 2, c: 0 }, e: { r: 2, c: 16 } },
       { s: { r: 3, c: 0 }, e: { r: 3, c: 16 } },
       { s: { r: 4, c: 0 }, e: { r: 4, c: 16 } },
       { s: { r: 6, c: 0 }, e: { r: 6, c: 16 } } // Transaction Details row
     ];

     ws["!merges"] = mergeRanges;

     /* ===== CENTER + BOLD FOR TITLES ===== */
     mergeRanges.forEach((merge) => {
       const cell = ws[XLSX.utils.encode_cell(merge.s)];
       if (cell) {
         cell.s = {
           alignment: { horizontal: "center", vertical: "center" },
           font: { bold: true }
         };
       }
     });

     /* ===== 🔥 HEADER BOLD ===== */
     const headerRowIndex = additionalDetails.length + mainHeaders.length;

     for (let col = 0; col < headers[0].length; col++) {
       const cellAddress = XLSX.utils.encode_cell({ r: headerRowIndex, c: col });

       if (ws[cellAddress]) {
         ws[cellAddress].s = {
           font: { bold: true }
         };
       }
     }

     const wb = XLSX.utils.book_new();
     XLSX.utils.book_append_sheet(wb, ws, "Data");

     XLSX.writeFile(wb, "Report for Cancellation of Subscription CBT.xlsx");

     Swal.fire("Success", "Report downloaded successfully", "success");
   }
//    async function getDetailspage() {
//      try {
//        vm.loader = true;
//        var requestData = {
//          schemeCode: vm.schemeCode,
//          flag: vm.closureType,
//          panNo: vm.panNo,
//          bla: vm.blaNo,
//          branchRefno: vm.branchRefNo,
//          holderName: vm.holderName,
//          fromDate: $filter("date")(vm.fromdate, "dd-MMM-yyyy"),
//          toDate: $filter("date")(vm.todate, "dd-MMM-yyyy"),
//        };
//
//        const response = await reportCancellationSubsCatCbtService.getDetails(requestData);
//        vm.result = response.data.result.list;
////        vm.DetailsList = response.data.result.list;
// vm.loader = false;
//
//        const columnMapping = {
//          MODE_OF_PAYMENT: ["modeofInterestPayment"],
//        };
//        for (const [apiColumn, localColumns] of Object.entries(columnMapping)) {
//          await updateColumnDescriptions(apiColumn, localColumns);
//        }
//
//        if (vm.closureType === "A") {
//                  downloadExcelCA(vm.DetailsList);
//                  console.log("downloadExcelCA");
//                } else if (vm.closureType === "B") {
//                  downloadExcelCB(vm.DetailsList);
//                  console.log("downloadExcelCB");
//                }
//  // Clear the form fields
//                                vm.schemeCode = '';
//                                vm.schemeName = '';
//                                vm.schemeStatus = '';
//                                vm.holderName = '';
//                                vm.panNo = '';
//                                vm.fromdate = '';
//                                vm.todate = '';
//                                vm.closureType = '';
//                                vm.branchRefNo = '';
//                                vm.blaNo = '';
//
//
//
//
//      } catch (error) {
//        vm.loader = false;
//        handleError(error);
//      }
//    }

    async function getDetailspage() {
        try {
            vm.loader = true;
            var requestData = {
                schemeCode: vm.schemeCode,
                flag: vm.closureType,
                panNo: vm.panNo,
                bla: vm.blaNo,
                branchRefno: vm.branchRefNo,
                holderName: vm.holderName,
                fromDate: $filter("date")(vm.fromdate, "dd-MMM-yyyy"),
                toDate: $filter("date")(vm.todate, "dd-MMM-yyyy"),
            };

            const response = await reportCancellationSubsCatCbtService.getDetails(requestData);
            vm.result = response.data.result.list;
            vm.loader = false;

            const columnMapping = {
                MODE_OF_PAYMENT: ["modeofInterestPayment"],
                RELATION_WITH_MINOR :["relationWithHolder"],
                STATES_OF_INDIA :["state"],
            };
            for (const [apiColumn, localColumns] of Object.entries(columnMapping)) {
                await updateColumnDescriptions(apiColumn, localColumns);
            }

            if (vm.closureType === "A") {
                downloadExcelCA(vm.DetailsList);
                console.log("downloadExcelCA");
            } else if (vm.closureType === "B") {
                downloadExcelCB(vm.DetailsList);
                console.log("downloadExcelCB");
            }

            // Use $timeout to clear fields and reflect changes in UI
            $timeout(function () {
                vm.schemeCode = '';
                vm.schemeName = '';
                vm.schemeStatus = '';
                vm.holderName = '';
                vm.panNo = '';
                vm.fromdate = null;
                vm.todate = null;
                vm.closureType = '';
                vm.branchRefNo = '';
                vm.blaNo = '';
            });

        } catch (error) {
            vm.loader = false;
            handleError(error);
        }finally {
             vm.loader = false; // Ensure loader stops even if an error occurs
              resetFormFields();
           }
    }



    async function updateColumnDescriptions(apiColumnName, localColumnNames) {
      try {
      let response;
      if(apiColumnName=="STATES_OF_INDIA"){
      response = await reportCancellationSubsCatCbtService.getReferenceData({ referenceId: "STATES OF INDIA" });
      }else{
        response = await reportCancellationSubsCatCbtService.getReferenceData({ referenceId: apiColumnName });
        }
        const descriptionMap = response.data.result.reduce((map, item) => {
          map[item.SUBCODE] = item.DESCRIPTION;
          return map;
        }, {});
        vm.DetailsList = vm.result.map((record) => {
          localColumnNames.forEach((localColumnName) => {
            const value = record[localColumnName];
            if (value && descriptionMap[value]) {
              record[localColumnName] = descriptionMap[value];
            }
          });
          return record;
        });
      } catch (error) {
        console.error(`Error updating descriptions for column ${apiColumnName}:`, error);
      }
    }

function handleError(error) {
  vm.loader = false;

  if (error.data && error.data.message) {
    if (error.data.message === "No Data Found" || error.data.message === "NDF") {
      vm.noDataFound = error.data.message;
      Swal.fire("No Records Found", "There are no records available to download.", "warning");
      resetFormFields();
    } else {
      try {
        handleErrorResponse(error);
      } catch (err) {
        console.error("Error handling response:", err);
      }
    }
  } else {
    Swal.fire("Error", "An unexpected error occurred.", "error");
  }
}

function resetFormFields() {
  $timeout(() => {
    vm.schemeCode = '';
    vm.schemeName = '';
    vm.schemeStatus = '';
    vm.holderName = '';
    vm.panNo = '';
    vm.fromdate = null;
    vm.todate = null;
    vm.closureType = '';
    vm.branchRefNo = '';
    vm.blaNo = '';
  });
}

//function handleError(error) {
//  vm.loader = false;
//  if (error.data && error.data.message) {
//    if (error.data.message == "No Data Found" || error.data.message == "NDF") {
//      vm.noDataFound = error.data.message;
//      Swal.fire("No Records Found", "There are no records available to download.", "warning");
//    } else {
//      handleErrorResponse(error);
//      // $state.go("report_for_cancellation_subscription_cat_cbt.inquiry");
//    }
//  } else {
//    Swal.fire("Error", "An unexpected error occurred.", "error");
//  }
//}


    vm.fromDateVal = function () {
      vm.fromDateOptions = {
        maxDate: new Date(),
        showWeeks: false,
      };
    }

    vm.toDateVal = function (fromDate) {
      vm.toDateOptions = {
        minDate: fromDate,
        maxDate: new Date(),
        showWeeks: false,
      };
    }

    function panTorch() {
      var requestData = {
        schemeCode: vm.schemeCode,
        panNo: vm.panNo,
        holderName: vm.holderName,
        flag: "P",
      };
      reportCancellationSubsCatCbtService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.panList = response.data.result.list;
          angular.element("#panModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          console.log("error", error);
          handleErrorResponse(error);
        }
      );
    }


    function blaTorch() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        bla: vm.blaNo,
        panNo: vm.panNo,
        holderName: vm.holderName,
        flag: "C",
      };
      reportCancellationSubsCatCbtService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.blaNoList = response.data.result.list;
          angular.element("#blaModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          console.log("error", error);
          handleErrorResponse(error);
        }
      );
    }

    function branchRefTorch() {
      vm.loader = true;
      var requestData = {
        schemeCode: vm.schemeCode,
        branchRefno: vm.branchRefNo,
        panNo: vm.panNo,
        bla: vm.blaNo,
        flag: "BR",
      };
      reportCancellationSubsCatCbtService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.branchRefNoList = response.data.result.list;
          console.log("vm.branchRefNoList", vm.branchRefNoList);
          angular.element("#branchrefModel").modal("toggle");
        },
        function (error) {
          vm.loader = false;
          console.log("error", error);
          handleErrorResponse(error);
        }
      );
    }

  }
})();