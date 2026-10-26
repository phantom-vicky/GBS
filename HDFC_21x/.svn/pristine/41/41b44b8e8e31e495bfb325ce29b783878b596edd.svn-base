(function () {

    'use strict';

    angular.module('gbmUltraApp').controller('hufDeathMarkingReportController', hufDeathMarkingReportController);

    hufDeathMarkingReportController.$inject = [
        '$filter', '$state', '$stateParams', '$rootScope', 'hufDeathMarkingReportService', 'commonService'
    ];

    function hufDeathMarkingReportController($filter, $state, $stateParams, $rootScope, hufDeathMarkingReportService, commonService) {

        var vm = this;

        vm.fromDatePicker = { opened: false };
        vm.toDatePicker = { opened: false };
        vm.detailsList = [];
        vm.noDataFound = false;
        vm.noDataFoundInSearch = false;
        vm.pageNo = 1;
        vm.pageSize = '5';
        vm.maxPages = 0;
        vm.total = 0;
        vm.showNext = false;
        vm.showPrevious = false;
        vm.branchId = $rootScope.globals.currentUser.solId || '';
        vm.branchName = $rootScope.globals.currentUser.solDesc || '';
        vm.branchIdList = [];
        vm.panNumberDetailList = [];
        vm.blaNumberDetailList = [];
        vm.searchSolId = '';
        vm.panSearchValue = '';
        vm.blaSearchValue = '';
        vm.searchValue = '';

        vm.textableDatePicker = textableDatePicker;
        vm.autoslashing = autoslashing;
        vm.fromDateVal = fromDateVal;
        vm.toDateVal = toDateVal;
        vm.getBranchId = getBranchId;
        vm.getPanTorch = getPanTorch;
        vm.getBlaTorch = getBlaTorch;
        vm.selectPan = selectPan;
        vm.selectBla = selectBla;
        vm.getDownload = getDownload;
        vm.downloadExcel = downloadExcel;
        vm.getDetails = getDetails;
        vm.goPreviousPage = goPreviousPage;
        vm.goNextPage = goNextPage;
        vm.paginationControls = paginationControls;

        if ($state.current.name == 'HUF_Death_Marking_Report.inquiry_details' && $stateParams.obj) {
            vm.panNo = $stateParams.obj.panNo || '';
            vm.blaNo = $stateParams.obj.blaNo || '';
            vm.fromDate = $stateParams.obj.fromDate || '';
            vm.toDate = $stateParams.obj.toDate || '';
            vm.pageNo = 1;
            getDetails();
        }

        function textableDatePicker(id, valueDate) {
            commonService.textableDatePicker(id, valueDate);
        }

        function autoslashing(id) {
            commonService.autoslashing(id);
        }

        // From Date: no future date
        function fromDateVal() {
            vm.fromDateOptions = {
                maxDate: new Date(),
                showWeeks: false
            };
        }

        // To Date: cannot be before From Date, no future date
        function toDateVal(fromDate) {
            vm.toDateOptions = {
                minDate: fromDate || undefined,
                maxDate: new Date(),
                showWeeks: false
            };
        }

        function getBranchId() {
            vm.loader = true;

            hufDeathMarkingReportService.getBranchDetails().then(function (response) {
                vm.loader = false;
                vm.branchIdList = (response.data && response.data.result) || [];
                angular.element('#branchIdModal').modal('toggle');
            }, function (error) {
                vm.loader = false;
                handleErrorResponse(error);
            });
        }

        function getPanTorch() {

            if (vm.panNo?.length> 0 && vm.panNo?.charAt(3).toUpperCase() !== 'H') {
                Swal.fire('Warning!', 'HUF PAN No only allowed.', 'warning');
                return;
            }
            const requestData= {
                   panNo : vm.panNo,
                   flag  : "P"
            }
            vm.loader = true;
            hufDeathMarkingReportService.getPanTorch(requestData).then(function (response) {
                vm.loader = false;
                vm.panNumberDetailList = (response.data && response.data.result.list) || [];
                angular.element('#panNoModal').modal('toggle');
            }, function (error) {
                vm.loader = false;
                handleErrorResponse(error);
            });
        }

        function selectPan(item) {
            vm.panNo = item.panNo || '';
            angular.element('#panNoModal').modal('hide');
        }

        function getBlaTorch() {

            vm.loader = true;
            const requestData= {
                  panNo: vm.panNo,
                  blaNo: vm.blaNo,
                  flag : "B"
            }
            hufDeathMarkingReportService.getBlaTorch(requestData).then(function (response) {
                vm.loader = false;
                vm.blaNumberDetailList = (response.data && response.data.result.list) || [];
                angular.element('#blaNoModal').modal('toggle');
            }, function (error) {
                vm.loader = false;
                handleErrorResponse(error);
            });
        }

        function selectBla(item) {
            vm.blaNo = item.blaNo || '';
            vm.panNo = item.panNo || vm.panNo;
            angular.element('#blaNoModal').modal('hide');
        }

        function goPreviousPage() {
            vm.pageNo = parseInt(vm.pageNo, 10) - 1;
            getDetails();
        }

        function goNextPage() {
            vm.pageNo = parseInt(vm.pageNo, 10) + 1;
            getDetails();
        }

        function paginationControls() {
            vm.showPrevious = parseInt(vm.pageNo, 10) !== 1;
            vm.showNext = parseInt(vm.pageNo, 10) !== parseInt(vm.maxPages, 10) && parseInt(vm.maxPages, 10) > 0;
        }

        function getDetails() {
            vm.loader = true;
            vm.noDataFound = false;

            var requestData = {
                panNo: vm.panNo || '',
                blaNo: vm.blaNo || '',
                fromDate: vm.fromDate ? $filter('date')(vm.fromDate, 'dd-MMM-yyyy') : '',
                toDate: vm.toDate ? $filter('date')(vm.toDate, 'dd-MMM-yyyy') : '',
                flag: 'D',
                pageNo: vm.pageNo || 1,
                pageSize: vm.pageSize || '5',
                searchValue: vm.searchValue || ''
            };

            hufDeathMarkingReportService.getDetails(requestData).then(function (response) {
                vm.loader = false;

                if (response.data && response.data.result && response.data.result.list) {
                    vm.detailsList = response.data.result.list;
                    vm.total = response.data.total || vm.detailsList.length;
                    vm.maxPages = Math.ceil(vm.total / vm.pageSize) || 0;
                    vm.noDataFound = vm.detailsList.length === 0;
                    vm.noDataFoundInSearch = vm.detailsList.length === 0;
                    paginationControls();
                } else {
                    vm.detailsList = [];
                    vm.total = 0;
                    vm.maxPages = 0;
                    vm.noDataFound = true;
                    vm.noDataFoundInSearch = true;
                    vm.showNext = false;
                    vm.showPrevious = false;
                }
            }, function (error) {
                vm.loader = false;
                vm.detailsList = [];
                vm.total = 0;
                vm.maxPages = 0;
                vm.noDataFound = true;
                vm.noDataFoundInSearch = true;
                vm.showNext = false;
                vm.showPrevious = false;
                handleErrorResponse(error);
            });
        }

        function getDownload() {
            if (vm.downloadType == 'excel') {
                downloadExcel();
            }
            vm.downloadType = '';
        }

        function downloadExcel() {
            if (!vm.detailsList || vm.detailsList.length == 0) {
                Swal.fire('Warning!', 'No records available for download.', 'warning');
                return;
            }

            var headers = [[
                'S. No.',
                'Branch Id',
                'Branch Name',
                'Date of Death of Karta',
                'Karta Death Marking Date',
                'Karta Death Marking Remarks',
                'Old Karta Name',
                'Next Eligible Co-Parcener Name (New Karta Name)',
                'DOB of New Karta',
                'Relationship with Karta',
                'Karta Change Reference Number (KCRN)'
            ]];

            var rows = vm.detailsList.map(function (row, index) {
                return [
                    index + 1,
                    row.branchId || '',
                    row.branchName || '',
                    row.dateOfDeathOfKarta || '',
                    row.kartaDeathMarkingDate || row.kartaDeathMarking || '',
                    row.kartaDeathMarkingRemarks || '',
                    row.oldKartaName || '',
                    row.nextEligibleCoParcenerName || row.newKartaName || '',
                    row.dobOfNewKarta || '',
                    row.relationshipWithKarta || '',
                    row.kartaChangeReferenceNumber || row.kartaChangeReferenceNo || row.kcrn || ''
                ];
            });

            var worksheet = XLSX.utils.aoa_to_sheet(headers.concat(rows));
            var workbook = XLSX.utils.book_new();
            XLSX.utils.book_append_sheet(workbook, worksheet, 'HUF Death Marking');
            XLSX.writeFile(workbook, 'HUF_Death_Marking_Report.xlsx');
        }

    }
})();