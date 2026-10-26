(function () {

    'use strict';

    angular.module('gbmUltraApp').controller('rbiInetPortfolioReportController',
        rbiInetPortfolioReportController);

    rbiInetPortfolioReportController.$inject = ['$scope', '$filter', '$state',
        '$stateParams', '$location', '$rootScope', '$timeout', 'rbiInetPortfolioService', 'commonService'
    ];

    function rbiInetPortfolioReportController($scope, $filter, $state, $stateParams,
        $location, $rootScope, $timeout, rbiInetPortfolioService, commonService) {

        var vm = this;
        vm.currentPage = 1;
        vm.itemsPerPage = '5';
        vm.getModuleName = getModuleName;
        vm.currentDate = new Date();
        vm.moduleName = 'RBI Bond';
        vm.searchValue = "";
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
        vm.pageSize = 5;
        vm.currentPage = 1;
        vm.maxPagesToShow = 5;
        vm.searchText = '';
        vm.noDataFoundInSearch = false;
        vm.loading = true;
        vm.showExportOptions = false;
        vm.itemsPerPage = "5";


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
    if (!vm.downloadType) {
        return;
    }
    vm.loader = true;
    var requestData = {
        fromDate: $filter("date")(vm.fromDate, "dd-MM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MM-yyyy"),
        pageNo: "1",
        pageSize: String(vm.total || vm.pageSize || 5),
        searchValue: (vm.searchValue || "").trim(),
        downloadType: vm.downloadType
    };
    rbiInetPortfolioService.getDetailsDownload(requestData).then(
        function (response) {
            vm.loader = false;
            if (response.data.result && response.data.result.fileName) {
                vm.fileData = response.data.result.fileData;
                vm.fileName = response.data.result.fileName;
                saveAs(getBlobFromBase64String(vm.fileData), vm.fileName);
                Swal.fire("Downloaded", response.data.message, "success");
            }
            vm.downloadType = '';
        },
        function (error) {
            vm.loader = false;
            vm.downloadType = '';
            handleErrorResponse(error);
        }
    );
}

        vm.downloadExcel = function () {

            const additionalDetails = [
                ["Inet Portfolio Report"],
                ["Bank Name: INSPIRISY BANK LTD"],
                ["Sol ID: 101 / BRANCH OF THE BANK"],
                ["From Date : " + $filter("date")(vm.fromDate, "dd-MM-yyyy")
                    + "     To Date : " + $filter("date")(vm.toDate, "dd-MM-yyyy")
                ],

                [],
            ];

            var headers = [[
                "S.No.","Request ID","Pan Number","Request DateTime","Response DateTime","Response Status"]];
            var rows = vm.downloadfilteredRecords.map(function (row, index) {
                return [
                    index + 1,
                    row.requestId,
                    row.panNo,
                    row.reqDateTime,
                    row.resDateTime,
                    row.resStatus
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
            XLSX.writeFile(wb, "Inet_Portfolio_Report.xlsx");
        };


        vm.downloadCSV = function () {
            const tableHeader = [
                { title: "S.No.", width: 10 },
                { title: "Request ID", width: 30 },
                { title: "Pan Number", width: 30 },
                { title: "Request DateTime", width: 30 },
                { title: "Response DateTime", width: 30 },
                { title: "Response Status", width: 30 },
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
                            record.requestId || "",
                            record.panNo || "",
                            record.reqDateTime || "",
                            record.resDateTime || "",
                            record.resStatus || ""
                        ][colIndex];
                        return value.toString().padEnd(col.width, " ");
                    })
                    .join("")
            );

            const textData = [
                "Inet Portfolio Report",
                "Bank Name: INSPIRISY BANK LTD",
                "Sol ID: 101 / BRANCH OF THE BANK",
                "From Date : " + $filter("date")(vm.fromDate, "dd-MM-yyyy")
                + "     To Date : " + $filter("date")(vm.toDate, "dd-MM-yyyy"),
                formattedHeader,
                "-".repeat(formattedHeader.length),
                ...tableData,
                "".padEnd(formattedHeader.length, " "), // Empty row
            ].join("\n");

            const textContent = "data:text/plain;charset=utf-8," + encodeURIComponent(textData);
            const link = document.createElement("a");
            link.setAttribute("href", textContent);
            link.setAttribute("download", "INET_Portfolio_Report.txt");
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
        };




        //        End of download report methods


        if ($state.current.name == 'inet_portfolio_report.inquiry_detail') {
            if ($stateParams.obj) {
                vm.fromDate = $stateParams.obj.fromDate;
                vm.toDate = $stateParams.obj.toDate;
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
            rbiInetPortfolioService.detailsPage(requestData).then(
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
                fromDate: $filter("date")(vm.fromDate, "dd-MM-yyyy"),
                toDate: $filter("date")(vm.toDate, "dd-MM-yyyy"),
                pageNo: String(vm.pageNo || 1),
                pageSize: String(vm.pageSize || 5),
                searchValue: (vm.searchValue || "").trim(),
            };

            rbiInetPortfolioService.detailsPage(requestData).then(
                function (response) {
                    vm.loader = false;
                    vm.noDataFound = false;
                    vm.detailsList = (response.data.result && response.data.result.list) ? response.data.result.list : [];
                    vm.filteredRecords = vm.detailsList;
                    vm.records = vm.detailsList;
                    vm.total = response.data.total || 0;
                    vm.maxPages = vm.total ? Math.ceil(vm.total / vm.pageSize) : 0;

                    if (!vm.filteredRecords.length) {
                        if (vm.searchValue) {
                            vm.noDataFoundInSearch = true;
                            vm.pageNo = 0;
                            vm.maxPages = 0;
                            vm.showPrevious = false;
                            vm.showNext = false;
                        } else {
                            vm.noDataFound = true;
                        }
                    } else {
                        vm.noDataFoundInSearch = false;
                    }

                    vm.updatePagination();
                    vm.paginationControls();
                },


                function (error) {
                    vm.loader = false;
                    if (vm.searchValue && (error.data.message == "No Data Found" || error.data.message == "NDF")) {
                        vm.noDataFoundInSearch = true;
                        vm.noDataFound = false;
                        vm.filteredRecords = [];
                        vm.records = [];
                        vm.detailsList = [];
                        vm.total = 0;
                        vm.pageNo = 0;
                        vm.maxPages = 0;
                        vm.showPrevious = false;
                        vm.showNext = false;
                        vm.paginationControls();
                    } else if (error.data.message == "No Data Found" || error.data.message == "NDF") {
                        vm.noDataFound = error.data.message;
                    } else {
                        handleErrorResponse(error);
                        $state.go("inet_portfolio_report.inquiry");
                    }
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

    }
    }) ();
