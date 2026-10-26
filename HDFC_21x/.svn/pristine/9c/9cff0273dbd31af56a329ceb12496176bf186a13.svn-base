(function () {

    'use strict';

    angular.module('gbmUltraApp').controller('tdsDeductionTdsRateController',
        tdsDeductionTdsRateController);

    tdsDeductionTdsRateController.$inject = ['$scope', '$filter', '$state',
        '$stateParams', '$location', '$rootScope', '$timeout', 'tdsDeductionTdsRateService', 'commonService'
    ];

    function tdsDeductionTdsRateController($scope, $filter, $state, $stateParams,
        $location, $rootScope, $timeout, tdsDeductionTdsRateService, commonService) {

        var vm = this;
        vm.currentPage = 1;
        vm.itemsPerPage = '5';
        vm.getModuleName = getModuleName;
//        vm.loginSolId = $rootScope.globals.currentUser.solId;
//        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
//        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.currentDate = new Date();
//        vm.solDate = $filter('date')($rootScope.globals.currentUser.systemDate, "dd/MM/yyyy");
        vm.moduleName = 'RBI Bond';
        vm.searchValue = "";
        vm.getPanTorch = getPanTorch;
        vm.getDetailspage = getDetailspage;
        vm.getblaTorch = getblaTorch;

        // Pagination starts
        vm.goPreviousPage = goPreviousPage;
        vm.goNextPage = goNextPage;
        vm.paginationControls = paginationControls;
        vm.showNext = false;
        vm.showPrevious = false;
        $scope.sortReverse = false;
        $scope.sort = function (key) {
            $scope.sortKey = key;
            $scope.sortReverse = ($scope.sortKey == key) ? !$scope.sortReverse : $scope.sortReverse;
            $scope.sortReverse ? vm.sortOrder = 'DESC' : vm.sortOrder = 'ASC';
            vm.sortField = key;
            vm.getDetailspage();
        }
        vm.pageNo = 1;
        vm.pageData = null;
        vm.records = [];
        vm.filteredRecords = [];
        vm.pageSizeOptions = [5,10, 25, 50, 100];
        vm.pageSize = vm.pageSizeOptions[1];
        vm.currentPage = 1;
        vm.maxPagesToShow = 5;
        vm.searchText = '';
        vm.loading = true;
        vm.showExportOptions = false;
        vm.itemsPerPage = "10";

     // Pan Validation
     vm.panPattern = /[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}/;

        vm.maxValue = function () {
            return Math.min(vm.currentPage * vm.pageSize, vm.filteredRecords.length);
        };

        function goPreviousPage() {
            vm.pageNo = parseInt(vm.pageNo) - 1;
            vm.getDetailspage();
        }

        function goNextPage() {
            vm.pageNo = parseInt(vm.pageNo) + 1;
            vm.getDetailspage();
        }


        function paginationControls() {
            vm.pages = [];
//            vm.maxPages = parseInt(vm.pageDetails.maxPages);

            for (var i = 1; i <= parseInt(vm.maxPages); i++) {
                vm.pages.push(parseInt(i));
            }

            if (vm.pageNo != 1) {
                vm.showPrevious = true;
            }
            else {
                vm.showPrevious = false;
            }

            if (vm.pageNo != vm.maxPages) {
                vm.showNext = true;
            }
            else {
                vm.showNext = false;
            }
        }

        vm.setPage = function (page) {
            if (page < 1) {
                vm.currentPage = 1;
            } else if (page > vm.totalPages) {
                vm.currentPage = vm.totalPages;
            } else {
                vm.currentPage = page;
            }
            vm.updatePagination();
        };

        vm.updatePagination = function () {
            try {
                var startPage, endPage;

                if (vm.totalPages <= vm.maxPagesToShow) {
                    startPage = 1;
                    endPage = vm.totalPages;
                } else {
                    var maxPagesBeforeCurrentPage = Math.floor(vm.maxPagesToShow / 2);
                    var maxPagesAfterCurrentPage = Math.ceil(vm.maxPagesToShow / 2) - 1;

                    if (vm.currentPage <= maxPagesBeforeCurrentPage) {
                        startPage = 1;
                        endPage = vm.maxPagesToShow;
                    } else if (vm.currentPage + maxPagesAfterCurrentPage >= vm.totalPages) {
                        startPage = vm.totalPages - vm.maxPagesToShow + 1;
                        endPage = vm.totalPages;
                    } else {
                        startPage = vm.currentPage - maxPagesBeforeCurrentPage;
                        endPage = vm.currentPage + maxPagesAfterCurrentPage;
                    }
                }

                vm.pages = [];
                for (var i = startPage; i <= endPage; i++) {
                    vm.pages.push(i);
                }

            } catch (error) {
                console.error("An error occurred in updatePagination:", error);
            }
        };

        vm.updatePageSize = function () {
            vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
            vm.setPage(1);
        };

        vm.prevPage = function () {
            vm.setPage(vm.currentPage - 1);
        };

        vm.nextPage = function () {
            vm.setPage(vm.currentPage + 1);
        };

        vm.firstPage = function () {
            vm.setPage(1);
        };

        vm.lastPage = function () {
            vm.setPage(vm.totalPages);
        };

        //----Pagination ends

        //Search Text
        vm.updateFilteredRecords = function () {
            const searchText = vm.searchText ? vm.searchText.toLowerCase() : '';
            vm.filteredRecords = vm.records.filter(record => {
                return Object.values(record).some(value =>
                    value !== null &&
                    value !== undefined &&
                    String(value).toLowerCase().includes(searchText)
                );
            });
            vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
            vm.setPage(1);
        };

        vm.getDownload=getDownload;
        function getDownload() {
            vm.loader = true;
            var requestData = {
                schemeCode : vm.schemeCode,
                        holderName: vm.holderName,
                        panNo: vm.panNo,
                        blaNo : vm.blaNo,
                        financialYear : vm.financialYear,
                        flag: "D",
                        downloadType : vm.downloadType,
//
                pageNo : '1',
                pageSize: vm.total,
                searchValue:vm.searchValue,
            };
            tdsDeductionTdsRateService.getTdsDeductionTdsrateDownload(requestData).then(
                function (response) {
                    vm.loader = false;
                    vm.downloadfilteredRecords = response.data.result.list;

                        if (vm.downloadType === "excel") {
                            if (response.data.result.fileName) {
                                             vm.fileData = response.data.result.fileData;
                                             vm.fileName = response.data.result.fileName;
                                             saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
                            }
                        } else if (vm.downloadType === "csv") {
                            vm.downloadCSV();
                        }
//                        else if (vm.downloadType === "pdf") {
//                            vm.downloadPDF();
//                        }
                        vm.downloadType ="";
                    },function (error) {
                   vm.loader = false;
                   if ((error.data.message == "No Data Found") || (error.data.message == "NDF")) {
                     vm.noDataFound = error.data.message;
                   }else {
                     handleErrorResponse(error);
                     $state.go("tds_deduction_at_other_than_normal_tds_rate_report.inquiry");
                   }
                  });
               }
        //        Download reports

        vm.downloadReport = function () {
            if (vm.downloadType === "excel") {
                vm.downloadExcel();
            } else if (vm.downloadType === "csv") {
                vm.downloadCSV();
            }
            else if (vm.downloadType === "pdf") {
                vm.downloadPDF();
            }
            vm.downloadType ="";
        };

        vm.downloadExcel = function () {

            const additionalDetails = [
                ["TDS Deduction at other than normal TDS rate report "],
                ["Bank Name: INSPIRISY BANK LTD"],
                ["Sol ID: 101 / BRANCH OF THE BANK"],
                ...(vm.schemeName ? [["Name of the Bond Scheme: " + vm.schemeName]] : []),
                ...(vm.holderName ? [["Name: "+vm.holderName]] : []),
                ...(vm.panNo ? [["PAN No: " + vm.panNo]] : []),
                ...(vm.blaNo ? [["BLA No: "+ vm.blaNo]] : []),
                ...(vm.financialYear ? [["Financial Year: "+ vm.financialYear]] : []),
                [],
            ];

            var headers = [[
                "S.No.", "PAN No", "BLA No", "Customer Name", "Bond Subscription Id", "Financial year", "Type of Certificate",
                 "Tax Excemption Limit", "Low/No Tax Percentage", "PAN Status", "Interest Payment Date", "Total Interest Rs",
                "TDS Amount Rs", "TDS Deducted Percentage", "TDS Deducted Amount(Rs)", "Ner Interest Amount Paid"]];
            var rows = vm.downloadfilteredRecords.map(function (row, index) {
                return [
                    index + 1,
                    row.panNo,
                    row.blaNo,
                    row.holderName,
                    row.subsId,
                    row.financialYear,
                    row.typeofCertificate,
                    row.taxExcemlimit,
                    row.lownotaxper,
                    row.panStatus,
                    row.interestPaymentDate,
                    row.totalInterest,
                    row.tdsAmt,
                    row.tdsDeductionper,
                    row.tdsDeductionAmt,
                    row.netInterestAmt,
                ];
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
            XLSX.writeFile(wb, "tds_deduction_at_other_than_normal_tds_rate_report.xlsx");
        };


        vm.downloadCSV = function () {
            const tableHeader = [
                { title: "S.No.", width: 10 },
                { title: "PAN No", width: 20 },
                { title: "BLA No", width: 20 },
                { title: "Customer Name", width: 30 },
                { title: "Bond Subscription Id", width: 30 },
                { title: "Financial year", width: 30 },
                { title: "Type of Certificate", width: 30 },
                { title: "Tax Excemption Limit", width: 30 },
                { title: "Low/No Tax Percentage", width: 30 },
                { title: "PAN Status", width: 20 },
                { title: "Interest Payment Date", width: 30 },
                { title: "Total Interest Rs", width: 30 },
                { title: "TDS Amount Rs", width: 25 },
                { title: "TDS Deducted Percentage", width: 25 },
                { title: "TDS Deducted Amount(Rs)", width: 25 },
                { title: "NeT Interest Amount Paid", width: 20 },
            ];

            const formattedHeader = tableHeader
                .map(col => col.title.padEnd(col.width, " "))
                .join("");
            let grandTotal = 0;

                const tableData = vm.downloadfilteredRecords.map((record, index) => {
                    // Add to grand total
                    const interest = parseFloat(record.totalInterest) || 0;
                    grandTotal += interest;

                    return tableHeader
                        .map((col, colIndex) => {
                            const value = [
                                index + 1, // S.No.
                                record.panNo || "",
                                record.blaNo || "",
                                record.holderName || "",
                                record.subsId || "",
                                record.financialYear || "",
                                record.typeofCertificate || "",
                                record.taxExcemlimit || "",
                                record.lownotaxper || "",
                                record.panStatus || "",
                                record.interestPaymentDate || "",
                                record.totalInterest || "",
                                record.tdsAmt || "",
                                record.tdsDeductionper || "",
                                record.tdsDeductionAmt || "",
                                record.netInterestAmt || "",
                            ][colIndex];
                            return value.toString().padEnd(col.width, " ");
                        })
                        .join("");
                });
             const totalInterestIndex = tableHeader.findIndex(col => col.title.includes("Interest Payment Date"));
                 const totalInterestWidth = tableHeader[totalInterestIndex].width;
                 const prefixSpaces = tableHeader
                     .slice(0, totalInterestIndex)
                     .reduce((sum, col) => sum + col.width, 0);

                 const grandTotalRow =
                     "".padEnd(prefixSpaces, " ") +
                     "Grand Total (RS)      " +
                     grandTotal.toFixed(2).padStart(totalInterestWidth - "Grand Total :- ".length, " ");

            const textData = [
                "TDS Deduction at other than normal TDS rate report",
                "Bank Name: INSPIRISY BANK LTD",
                "Sol ID: 101 / BRANCH OF THE BANK",
                ...(vm.schemeName ? [["Name of the Bond Scheme: " + vm.schemeName]] : []),
                ...(vm.holderName ? [["Name: "+vm.holderName]] : []),
                ...(vm.panNo ? [["PAN No: " + vm.panNo]] : []),
                ...(vm.blaNo ? [["BLA No: "+ vm.blaNo]] : []),
                ...(vm.financialYear ? [["Financial Year: "+ vm.financialYear]] : []),
                formattedHeader,
                "-".repeat(formattedHeader.length),
                ...tableData,
                grandTotalRow,
                "".padEnd(formattedHeader.length, " "), // Empty row
            ].join("\n");

            const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData);
            const link = document.createElement("a");
            link.setAttribute("href", textContent);
            link.setAttribute("download", "tds_deduction_at_other_than_normal_tds_rate_report.txt");
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
        };


        //        End of download report methods


        if ($state.current.name == 'tds_deduction_at_other_than_normal_tds_rate_report.inquiry_detail') {
            if ($stateParams.obj) {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;
                vm.schemeStatus = $stateParams.obj.schemeStatus;
                vm.blaNo = $stateParams.obj.blaNo;
                vm.financialYear = $stateParams.obj.financialYear;
                vm.panNo = $stateParams.obj.panNo;
                vm.holderName = $stateParams.obj.holderName;
                getDetailspage();
            }
        }

        //Get Scheme name
        function getModuleName() {
            vm.loader = true;

            var requestData = {
                flag : "S",
               schemeCode :'',
            };
            tdsDeductionTdsRateService.detailsPage(requestData).then(
                function (response) {
                    vm.loader = false;
                    vm.schemeList = response.data.result.list;
                    console.log("vm.schemeList",vm.schemeList);
                    angular.element("#schemeModel").modal("toggle");
                },
                function (error) {
                    handleErrorResponse(error);
                    vm.loader = false;
                }
            );
        }
        //Get Report data (Details page)
        function getDetailspage() {
            vm.loader = true;
             if(null!=vm.searchValue){
                var requestData = {
                        schemeCode : vm.schemeCode,
                        holderName: vm.holderName,
                        panNo: vm.panNo,
                        blaNo : vm.blaNo,
                        financialYear : vm.financialYear,
                        flag: "D",

                        pageNo : vm.pageNo,
                        pageSize:vm.pageSize,
                        searchValue:vm.searchValue,
                        sortOrder:vm.sortOrder,
                        sortField:vm.sortField,
                };
                }else{
                var requestData = {
                        schemeCode : vm.schemeCode,
                        holderName: vm.holderName,
                        panNo: vm.panNo,
                        blaNo : vm.blaNo,
                        financialYear : vm.financialYear,
                        flag: "D",
                        //
                        pageNo : vm.pageNo,
                        pageSize:vm.pageSize,
                        searchValue:vm.searchValue,
                        sortOrder:vm.sortOrder,
                        sortField:vm.sortField,
                };
               }

            tdsDeductionTdsRateService.detailsPage(requestData).then(
                function (response) {
                    vm.loader = false;
//                    vm.detailsList = response.data.result.list;
                    vm.filteredRecords = response.data.result.list;

                     vm.totalIntrest = vm.filteredRecords.reduce((sum, record) => sum + (record.totalInterest ? parseFloat(record.totalInterest) : 0), 0);
                      vm.tdsAmt = vm.filteredRecords.reduce((sum, record) => sum + (record.tdsAmt ? parseFloat(record.tdsAmt) : 0), 0);
                       vm.tdsDeductionAmt = vm.filteredRecords.reduce((sum, record) => sum + (record.tdsDeductionAmt ? parseFloat(record.tdsDeductionAmt) : 0), 0);
                        vm.netInterestAmt = vm.filteredRecords.reduce((sum, record) => sum + (record.netInterestAmt ? parseFloat(record.netInterestAmt) : 0), 0);





                    vm.records = response.data.result.list;
                    vm.totalPages = Math.ceil(vm.records.length / vm.pageSize);
                    vm.pageDetails = response.data.result.list;
                   // vm.updatePagination();

                    vm.total = response.data.total;
                    vm.maxPages =  Math.round(response.data.total/vm.pageSize);
                    vm.updatePagination();
                     vm.paginationControls();
                       if (vm.filteredRecords.length === 0) {
                        vm.noDataFoundInSearch = true;
                      } else {
                        vm.noDataFoundInSearch = false;
                      }

                    angular.element("#schemeModel").modal("toggle");
                },
                function (error) {

                    vm.loader = false;
                    if ((error.data.message == "No Data Found") || (error.data.message == "NDF") && vm.searchValue) {




                                  vm.pageNo ='0';
                                 vm.maxPages ='0';
                                 vm.total = '0';
                                  vm.showNext = false;
                        vm.noDataFound = error.data.message;
                        vm.noDataFoundInSearch = true;
                    } else if((error.data.message == "No Data Found") || (error.data.message == "NDF")) {
                        vm.noDataFound = error.data.message;
                    }
                    else {
                        handleErrorResponse(error);
                        $state.go("tds_deduction_at_other_than_normal_tds_rate_report.inquiry");
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


        //Get Pan Number
        function getPanTorch() {
            var requestData = {
                schemeCode: vm.schemeCode,
                holderName: vm.holderName,
                panNo: vm.panNo,
                flag: "P",
            };
            tdsDeductionTdsRateService.detailsPage(requestData).then(
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

        //Get BLA Number
               function getblaTorch() {
                   var requestData = {
                       schemeCode: vm.schemeCode,
                       holderName: vm.holderName,
                       panNo: vm.panNo,
                       blaNo : vm.blaNo,
                       flag: "B",
                   };
                   tdsDeductionTdsRateService.detailsPage(requestData).then(
                       function (response) {
                           vm.loader = false;
                           vm.blaList = response.data.result.list;
                           angular.element("#blaModel").modal("toggle");
                       },
                       function (error) {
                           vm.loader = false;
                           console.log("error", error);
                           handleErrorResponse(error);
                       }
                   );
               }

         //Get Financial Year
               vm.getFinancialYears = function () {
                     vm.loader = true;
                     var requestData = {
                       flag: "FY",
                     };
                     tdsDeductionTdsRateService.detailsPage(requestData).then(
                       function (response) {
                         vm.loader = false;
                         vm.financialYears = response.data.result.list;
                       },
                       function (error) {
                         handleErrorResponse(error);
                         vm.loader = false;
                       }
                     );
                   };
    }
})();
