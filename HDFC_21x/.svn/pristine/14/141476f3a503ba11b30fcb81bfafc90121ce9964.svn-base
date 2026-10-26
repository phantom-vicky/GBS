(function () {

    'use strict';

    angular.module('gbmUltraApp').controller('tdsEligibilityController',
        tdsEligibilityController);

    tdsEligibilityController.$inject = ['$scope', '$filter', '$state',
        '$stateParams', '$location', '$rootScope', '$timeout', 'tdsEligibilityService', 'commonService'
    ];

    function tdsEligibilityController($scope, $filter, $state, $stateParams,
        $location, $rootScope, $timeout, tdsEligibilityService, commonService) {

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




        vm.getDetailspage = getDetailspage;

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
        vm.pageSizeOptions = [10, 25, 50, 100];
        vm.pageSize = vm.pageSizeOptions[0];
        vm.currentPage = 1;
        vm.maxPagesToShow = 5;
        vm.searchText = '';
        vm.loading = true;
        vm.showExportOptions = false;
        vm.itemsPerPage = "10";


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





        // vm.maxPages = parseInt(vm.pageDetails.maxPages);

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
            vm.downloadType = '';
        };

vm.getDownload=getDownload;
function getDownload() {
    vm.loader = true;
    var requestData = {
                schemeCode: vm.schemeCode,
                panNo: vm.panNo,
                flag: "D",
                fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
                toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
                //
                pageNo : vm.pageNo,
                pageSize:vm.pageSize,
                searchValue:vm.searchValue,
                sortOrder:vm.sortOrder,
                sortField:vm.sortField,
                downloadType:vm.downloadType,
    };
    tdsEligibilityService.getDetailsDownload(requestData).then(
        function (response) {
            vm.loader = false;
            vm.downloadfilteredRecords = response.data.result.list;
            if (vm.downloadType === "excel") {
//                vm.downloadExcel();
            if (response.data.result.fileName) {
                 vm.fileData = response.data.result.fileData;
                 vm.fileName = response.data.result.fileName;
                 saveAs(getBlobFromBase64String(vm.fileData), vm.fileName)
            }
            } else if (vm.downloadType === "csv") {
                vm.downloadCSV();
            }
//            else if (vm.downloadType === "pdf") {
//                vm.downloadPDF();
//            }
            vm.downloadType = '';
            },function (error) {
           vm.loader = false;
           if ((error.data.message == "No Data Found") || (error.data.message == "NDF")) {
             vm.noDataFound = error.data.message;
           }else {
             handleErrorResponse(error);
             $state.go("tds_eligibility_report.inquiry");
           }
          });
       }

        vm.downloadExcel = function () {

            const additionalDetails = [
                ["TDS Eligibility Report"],
                ["Bank Name: INSPIRISY BANK LTD"],
                ["Sol ID: 101 / BRANCH OF THE BANK"],
                ...(vm.schemeName ? [["Name of the Bond Scheme: " + vm.schemeName]] : []),
                ...(vm.panNo ? [["PAN No: " + vm.panNo]] : []),
                ["TDS Deduction From Date : " + $filter("date")(vm.fromDate, "dd-MM-yyyy")
                    + "     TDS Deduction To Date : " + $filter("date")(vm.toDate, "dd-MM-yyyy")
                ],

                [],
            ];

            var headers = [[
                "S.No.", "Customer Name", "PAN No", "Bond Ledger Account Number", "Bond Subscription ID", "Subscription on Date", "Subscription on Amount", "Interest Rate", "TDS Deduction From Period", "TDS Deduction To period",
                "Interest Paid Date", "Interest Payment Mode", "Credit Account Number", "Type of Certificate", "TDS Exemption Limit", "Low Tax Percentage", "PAN Status", "Gross Total Interest Amount Rs",
                "TDS Amount Rs", "TDS Deducted Percentage", "New Interest Amount Rs"]];
            var rows = vm.downloadfilteredRecords.map(function (row, index) {
                return [
                    index + 1,
                    row.holderName,
                    row.panNo,
                    row.blaNo,
                    row.subsId,
                    row.subsDate,
                    row.subsAmt,
                    row.interestRate,
                    row.tdsDeductionFrom,
                    row.tdsDeductionTo,
                    row.interestPaidDate,
                    row.modeOfPaymentDescription,
                    row.creditAccNo,
                    row.typeOfCertificate,
                    row.tdsExemptionLimit,
                    row.lowTAxPercentage,
                    row.panStatus,
                    row.grosstotalInterestAmt,
                    row.tdsAmt,
                    row.tdsDeductionPercentage,
                    row.newInterestAmt,
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
            XLSX.writeFile(wb, "TDS_eligibility_report.xlsx");
        };


        vm.downloadCSV = function () {
            const tableHeader = [
                { title: "S.No.", width: 10 },
                { title: "Customer Name", width: 30 },
                { title: "PAN No", width: 30 },
                { title: "Bond Ledger Account Number", width: 30 },
                { title: "Bond Subscription ID", width: 30 },
                { title: "Subscription on Date", width: 30 },
                { title: "Subscription on Amount", width: 30 },
                { title: "Interest Rate", width: 20 },
                { title: "TDS Deduction From Period", width: 30 },
                { title: "TDS Deduction To period", width: 30 },
                { title: "Interest Paid Date", width: 30 },
                { title: "Interest Payment Mode", width: 30 },
                { title: "Credit Account Number", width: 25 },
                { title: "Type of Certificate", width: 25 },
                { title: "TDS Exemption Limit", width: 25 },
                { title: "Low Tax Percentage", width: 25 },
                { title: "PAN Status", width: 20 },
                { title: "Gross Total Interest Amount Rs", width: 35 },
                { title: "TDS Amount Rs", width: 20 },
                { title: "TDS Deducted Percentage", width: 30 },
                { title: "New Interest Amount Rs", width: 30 },
            ];

            const formattedHeader = tableHeader
                .map(col => col.title.padEnd(col.width, " "))
                .join("");

            const tableData = vm.downloadfilteredRecords.map((record, index) =>
                tableHeader
                    .map((col, colIndex) => {
                        const value = [
                            index + 1, // S.No.
                            record.holderName || "",
                            record.panNo || "",
                            record.blaNo || "",
                            record.subsId || "",
                            record.subsDate || "",
                            record.subsAmt || "",
                            record.interestRate || "",
                            record.tdsDeductionFrom || "",
                            record.tdsDeductionTo || "",
                            record.interestPaidDate || "",
                            record.modeOfPaymentDescription || "",
                            record.creditAccNo || "",
                            record.typeOfCertificate || "",
                            record.tdsExemptionLimit || "",
                            record.lowTAxPercentage || "",
                            record.panStatus || "",
                            record.grosstotalInterestAmt || "",
                            record.tdsAmt || "",
                            record.tdsDeductionPercentage || "",
                            record.newInterestAmt || "",
                        ][colIndex];
                        return value.toString().padEnd(col.width, " ");
                    })
                    .join("")
            );

            const textData = [
                "TDS Eligibility Report",
                "Bank Name: INSPIRISY BANK LTD",
                "Sol ID: 101 / BRANCH OF THE BANK",
                ...(vm.schemeName ? [["Name of the Bond Scheme: " + vm.schemeName]] : []),
                ...(vm.panNo ? [["PAN No: " + vm.panNo]] : []),
                "TDS Deduction From Date : " + $filter("date")(vm.fromDate, "dd-MM-yyyy")
                + "     TDS Deduction To Date : " + $filter("date")(vm.toDate, "dd-MM-yyyy"),
                formattedHeader,
                "-".repeat(formattedHeader.length),
                ...tableData,
                "".padEnd(formattedHeader.length, " "), // Empty row
            ].join("\n");

            const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData);
            const link = document.createElement("a");
            link.setAttribute("href", textContent);
            link.setAttribute("download", "TDS_eligibility_report.txt");
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
        };




        //        End of download report methods


        if ($state.current.name == 'tds_eligibility_report.inquiry_detail') {
            if ($stateParams.obj) {
                vm.schemeCode = $stateParams.obj.schemeCode;
                vm.schemeName = $stateParams.obj.schemeName;
                vm.schemeStatus = $stateParams.obj.schemeStatus;
                vm.fromDate = $stateParams.obj.fromDate;
                vm.toDate = $stateParams.obj.toDate;
                vm.panNo = $stateParams.obj.panNo;
                getDetailspage();
            }
        }

        //Get Scheme name
        function getModuleName() {
            vm.loader = true;

            var requestData = {
                schemeCode: vm.schemeCode,
                flag: "S",
            };
            tdsEligibilityService.detailsPage(requestData).then(
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
        //Get Report data (Details page)
        function getDetailspage() {
            vm.loader = true;
            var requestData = {
                schemeCode: vm.schemeCode,
                panNo: vm.panNo,
                flag: "D",
                fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
                toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
                //
                pageNo : vm.pageNo,
                pageSize:vm.pageSize,
                searchValue:vm.searchValue,
                sortOrder:vm.sortOrder,
                sortField:vm.sortField,
            };

            tdsEligibilityService.detailsPage(requestData).then(
                function (response) {
                    vm.loader = false;
                    vm.detailsList = response.data.result.list;
                    vm.filteredRecords = response.data.result.list;


    vm.subsAmt = vm.filteredRecords.reduce((sum, record) => sum + (record.subsAmt ? parseFloat(record.subsAmt) : 0), 0);

     vm.grosstotalInterestAmt = vm.filteredRecords.reduce((sum, record) => sum + (record.grosstotalInterestAmt ? parseFloat(record.grosstotalInterestAmt) : 0), 0);
      vm.tdsAmt = vm.filteredRecords.reduce((sum, record) => sum + (record.tdsAmt ? parseFloat(record.tdsAmt) : 0), 0);
       vm.tdsDeductionPercentage = vm.filteredRecords.reduce((sum, record) => sum + (record.tdsDeductionPercentage ? parseFloat(record.tdsDeductionPercentage) : 0), 0);
        vm.newInterestAmt = vm.filteredRecords.reduce((sum, record) => sum + (record.newInterestAmt ? parseFloat(record.newInterestAmt) : 0), 0);



                    vm.records = response.data.result.list;
                    vm.totalPages = Math.ceil(vm.records.length / vm.pageSize);
                    vm.pageDetails = response.data.result.list;

                    vm.total = response.data.total;
                    vm.maxPages = Math.ceil(response.data.total / vm.pageSize);
                           vm.updatePagination();
                           vm.paginationControls();

                     var holdingReferenceId = { referenceId: "MODE_OF_PAYMENT" };

                                                                             tdsEligibilityService.getReferenceData(holdingReferenceId).then(
                                                                                 function (holdingMappingResponse) {
                                                                                     var holdingMappings = holdingMappingResponse.data.result; // Response for mode of payment


                                                                                     var paymentMap = {};
                                                                                     holdingMappings.forEach(function (item) {
                                                                                         paymentMap[item.SUBCODE] = item.DESCRIPTION;
                                                                                     });


                                                                                     vm.filteredRecords.forEach(function (record) {
                                                                                         record.modeOfPaymentDescription = paymentMap[record.interestPaidMode] || 'Unknown';
                                                                                     });

                                                                                     console.log("Updated Records with Descriptions:", vm.filteredRecords);
                                                                                 },
                                                                                 function (error) {
                                                                                     console.error('Error fetching mode of payment descriptions:', error);
                                                                                 }
                                                                             );
                                          },


                        function (error) {
                            vm.loader = false;
                            if ((error.data.message == "No Data Found") || (error.data.message == "NDF") && vm.searchValue) {


                                     //  vm.pageNo ='0';
                                       vm.maxPages ='0';
                                        vm.total = '0';
                                        vm.showNext = false;
                                vm.noDataFound = error.data.message;
                                vm.noDataFoundInSearch = true;
                            } else if (error.data.message == "No Data Found" || error.data.message == "NDF") {
                                vm.noDataFound = error.data.message;
                            }
                             else {
                                handleErrorResponse(error);
                                $state.go("tds_eligibility_report.inquiry");
                            }
                        }
                    );
                }


        //Get Pan Number
        function getPanTorch() {
                    var requestData = {
                        schemeCode: vm.schemeCode,
                        panNo: vm.panNo,
                        flag: "P",
                    };
                    tdsEligibilityService.detailsPage(requestData).then(
                        function (response) {

                            vm.loader = false;
                            vm.PanTorch = response.data.result.list;
                            angular.element("#panModel").modal("toggle");
                        },
                        function (error) {
                            vm.loader = false;
                            console.log("error", error);
                            handleErrorResponse(error);
                        }
                    );
                }

        //Date validation
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
    }) ();
