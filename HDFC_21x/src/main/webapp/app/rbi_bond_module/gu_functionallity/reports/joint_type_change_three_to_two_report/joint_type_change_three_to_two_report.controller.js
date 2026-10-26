(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("jointTypeChangeThreeToTwoReportController", jointTypeChangeThreeToTwoReportController);

  jointTypeChangeThreeToTwoReportController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "$timeout", "jointTypeChangeThreeToTwoReportService", "commonService"];

  function jointTypeChangeThreeToTwoReportController($scope, $filter, $state, $stateParams, $location, $rootScope, $timeout, jointTypeChangeThreeToTwoReportService, commonService) {
    var vm = this;
    $scope.vm = vm;

    vm.moduleName = "RBI Bond";
    vm.getModuleName = getModuleName;
    vm.getDetails = getDetails;


    vm.textableDatePicker = textableDatePicker;
    vm.autoslashing = autoslashing;

    function textableDatePicker(id, valueDate) {
      commonService.textableDatePicker(id, valueDate);
    }
    function autoslashing(id) {
      commonService.autoslashing(id);
    }

    vm.solDate = $rootScope.globals.currentUser.systemDate;
    vm.subsDate = moment(vm.solDate).format("DD-MMM-YYYY");
    vm.subsDate1 = moment(vm.solDate).format("DD-MM-YYYY");
    vm.solDate1 = $rootScope.globals.currentUser.solDate;

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

    if ($state.current.name == "joint_type_change_three_to_two_report.inquiry_details") {
      vm.loader = true;
      vm.fromDate = $stateParams.obj.fromDate;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.toDate = $stateParams.obj.toDate;



      vm.getDetails();
    }

    function getModuleName() {
      vm.loader = true;
      var requestData = {
        flag: "S",
        downloadType:"D",
      };

      jointTypeChangeThreeToTwoReportService.getDetails(requestData).then(
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
        statusIO: vm.statusIO,
        fromDate: $filter("date")(vm.fromDate, "dd-MMM-yyyy"),
        toDate: $filter("date")(vm.toDate, "dd-MMM-yyyy"),
        flag: "D",
        pageNo: vm.pageNo,
                pageSize: vm.pageSize,
                searchValue: vm.searchText,
                sortOrder: vm.sortOrder,
                sortField: vm.sortField,
                downloadType:"D",
      };
      jointTypeChangeThreeToTwoReportService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.filteredRecords = response.data.result.list;
          vm.detailsList = response.data.result.list;
          vm.records = response.data.result.list;
          vm.total = response.data.total;
                    vm.maxPages = Math.ceil(response.data.total / vm.pageSize);
                    vm.paginationControls();
                    if (vm.detailsList.length === 0) {
                      vm.noDataFoundInSearch = true;
                    } else {
                      vm.noDataFoundInSearch = false;
                    }
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
                    $state.go("joint_type_change_three_to_two_report.inquiry");
                  }
        }
      );
    }



     vm.downloadReport = function () {
                 vm.loader = true;
                if(vm.downloadType === "excel"){
                    var requestData = {
                      schemeCode: vm.schemeCode,
                      fromDate: moment(vm.fromDate).format("DD-MMM-YYYY"),
                      toDate: moment(vm.toDate).format("DD-MMM-YYYY"),
                      pageNo: 1,
                       flag: "D",
                      pageSize: vm.total,
                      searchValue: vm.searchValue,
                       downloadType: vm.downloadType,
                    };

                    jointTypeChangeThreeToTwoReportService.getDetails(requestData).then(
                      function (response) {
                        vm.loader = false;
                        vm.filteredRecord = response.data.result.list;

                        if (vm.downloadType === "excel") {
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
                              $state.go("joint_type_change_three_to_two_report.inquiry");
                            }
                      }
                    );

                    }
                    else{

                      $state.go("joint_type_change_three_to_two_report.inquiry_details");

                    }
            };

    function fromDateVal() {
      vm.fromDateOptions = {
        maxDate: new Date(),
        showWeeks: false,
      };
    }

    function toDateVal(fromDate) {
      vm.toDateOptions = {
        minDate: fromDate,
        maxDate: new Date(),
        showWeeks: false,
      };
    }
    vm.formatDate = function (inputDate) {
      const date = new Date(inputDate);
      const day = String(date.getDate()).padStart(2, "0"); // Ensure two digits
      const month = date.toLocaleString("en-GB", { month: "short" }).toUpperCase(); // Get short month in uppercase
      const year = date.getFullYear(); // Get year

      return `${day}-${month}-${year}`;
    };






  }
})();
