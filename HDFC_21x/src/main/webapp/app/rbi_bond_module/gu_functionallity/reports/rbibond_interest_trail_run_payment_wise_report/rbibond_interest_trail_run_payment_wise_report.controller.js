(function () {
  "use strict";

  angular.module("gbmUltraApp").controller("rbibondInterestTrailRunPaymentWiseController", rbibondInterestTrailRunPaymentWiseController);

  rbibondInterestTrailRunPaymentWiseController.$inject = ["$scope", "$filter", "$state", "$stateParams", "$location", "$rootScope", "rbiBondInterestTrailRunPaymentWiseService", "commonService"];

  function rbibondInterestTrailRunPaymentWiseController($scope, $filter, $state, $stateParams, $location, $rootScope, rbiBondInterestTrailRunPaymentWiseService, commonService) {
    var vm = this;
//    vm.currentPage = 1;
//    vm.itemsPerPage = "5";
//    vm.loginSolId = $rootScope.globals.currentUser.solId;
    vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
//    vm.bankName = $rootScope.globals.currentUser.bankName;
//    vm.currentDate = new Date();
//    vm.currentDate = $filter("date")(vm.currentDate, "dd/MM/yyyy");
//    vm.getTrialRunEligibleAccountsFile = getTrialRunEligibleAccountsFile;
    vm.getModuleName = getModuleName;
    vm.moduleName = "RBI Bond";
    vm.getInterestTrailPaymentWise=getInterestTrailPaymentWise;
    vm.solDate = $filter("date")($rootScope.globals.currentUser.systemDate, "dd/MM/yyyy");

    vm.showNext = false;
    vm.showPrevious = false;
    $scope.sortReverse = false;

    $scope.sort = function (key) {
      $scope.sortKey = key;
      $scope.sortReverse = $scope.sortKey == key ? !$scope.sortReverse : $scope.sortReverse;
      $scope.sortReverse ? (vm.sortOrder = "DESC") : (vm.sortOrder = "ASC");
      vm.sortField = key;
      vm.getInquiryDetails();
    };
    vm.pageNo = 1;
    vm.pageData = null;
    vm.records = [];
    vm.filteredRecords = [];
    vm.pageSizeOptions = [10, 25, 50, 100];
    vm.pageSize = vm.pageSizeOptions[0];
    vm.currentPage = 1;
    vm.maxPagesToShow = 5;
    vm.searchText = "";
    vm.loading = true;
    vm.showExportOptions = false;
    vm.itemsPerPage = "10";

    vm.goPreviousPage= function () {
      vm.pageNo = parseInt(vm.pageNo) - 1;
      vm.getSubsAccDetails();
    }

    vm.goNextPage = function () {
      vm.pageNo = parseInt(vm.pageNo) + 1;
      vm.getSubsAccDetails();
    }

    vm.paginationControls = function () {
      vm.pages = [];
      vm.maxPages = parseInt(vm.pageDetails.maxPages);
      for (var i = 1; i <= parseInt(vm.pageDetails.maxPages); i++) {
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
    }

    vm.setPage = function (page) {
      console.log("Page Number in setPage: " + page);
      if (page < 1) {
        vm.currentPage = 1;
      } else if (page > vm.totalPages) {
        vm.currentPage = vm.totalPages;
      } else {
        vm.currentPage = page;
      }
      console.log("vm.currentPage: " + vm.currentPage);
      vm.updatePagination();
    };

    vm.updatePagination = function () {
      try {
        var startPage, endPage;
        console.log("Inside updatePagination function...");

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

        console.log("vm.totalPages: " + vm.totalPages);
        console.log("startPage: " + startPage);
        console.log("endPage: " + endPage);

        vm.pages = [];
        for (var i = startPage; i <= endPage; i++) {
          vm.pages.push(i);
        }
        console.log("vm.pages: " + vm.pages);
      } catch (error) {
        console.error("An error occurred in updatePagination:", error);
      }
    };

    vm.updatePageSize = function () {
      vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
      console.log("totalPages: " + vm.totalPages);
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

    vm.updateFilteredRecords = function () {
      vm.filteredRecords = vm.records.filter(function (record) {
        return (
          record.holderPanNo.toLowerCase().includes(vm.searchText.toLowerCase()) ||
          String(record.modeOfInvestment).includes(vm.searchText) ||
          String(record.holderName).includes(vm.searchText) ||
          String(record.blaNo).includes(vm.searchText) ||
          String(record.subsId).includes(vm.searchText) ||
          String(record.dateOfSubs).includes(vm.searchText) ||
          String(record.maturityDate).includes(vm.searchText) ||
          String(record.modeOfHolding).includes(vm.searchText) ||
          String(record.subsAmount).includes(vm.searchText) ||
          String(record.subsStatus).includes(vm.searchText)
        );
      });
      vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
      vm.setPage(1);
    };

    if ($state.current.name == "rbibond_interest_trail_run_payment_wise_report.inquiry_details") {
      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.schemeName = $stateParams.obj.schemeName;
      vm.interestPaid = $stateParams.obj.intertestPaymentFor;
      vm.financialYear = $stateParams.obj.finacialYear;
      vm.interestPaymentMode = $stateParams.obj.interestPaymentMode;

            getInterestTrailPaymentWise();
    }

//    function configDataTable() {
//      vm.getTitleDescription = {
//        title: "TDS Deduction Report",
//        solId: $rootScope.globals.currentUser.solId,
//        solDesc: $rootScope.globals.currentUser.solDesc,
//      };
//      vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
//    }

    function getModuleName() {
      vm.loader = true;
      vm.referenceId = "TAX";
      var requestData = {
        referenceId: vm.referenceId,
      };
      rbiBondInterestTrailRunPaymentWiseService.getRbiBondModuleNameDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.schemeList = response.data.result;
          console.log("schemeList", vm.schemeList);
          angular.element("#schemeModel").modal("toggle");
        },
        function (error) {
          handleErrorResponse(error);
          vm.loader = false;
        }
      );
    }

    vm.alert = function (item) {
      for (let i = 0; i < vm.schemeList.length; i++) {
        if (vm.schemeList[i].subCode == item) {
          vm.schemeName = vm.schemeList[i].subCodeDescription;
        }
      }
    }

    function getInterestTrailPaymentWise() {
      vm.loader = true;

      vm.totalInterestRupees = 0;
      vm.totalTdsAmount = 0;
      vm.totalNetInterestAmount = 0;

      var requestData = {
        schemeCode: vm.schemeCode,
//        interestPaymentFor: vm.interestPaid,
//        financialYear: vm.financialYear,
//        interestPaymentMode: vm.interestPaymentMode,
//        schemeName: vm.schemeName,
//                pageSize: vm.pageSize,
//                pageNo: vm.pageNo,
//                searchValue: vm.searchValue,
//                sortOrder: vm.sortOrder,
//                sortField: vm.sortField,
      };

console.log("the front end started");
      rbiBondInterestTrailRunPaymentWiseService.getDetails(requestData).then(
        function (response) {
          vm.loader = false;
          vm.pageDetails = response.data.result.list;
          vm.paymentWiseList = response.data.result.list;
          vm.filteredRecords=response.data.result.list;
//
//          vm.paginationControls();
//
//          if (vm.paymentWiseList) {
//            vm.interestTotal = 0;
//            vm.TdsTotal = 0;
//            vm.netAmtTotal = 0;
//
//            angular.forEach(vm.paymentWiseList, function (item) {
//              item.GROSS_INTEREST == null ? (item.GROSS_INTEREST = 0) : item.GROSS_INTEREST;
//              //   item.ARREAR_TDS_AMOUNT == null ? item.ARREAR_TDS_AMOUNT = 0 : item.ARREAR_TDS_AMOUNT;
//              item.CURRENT_TDS_AMOUNT == null ? (item.CURRENT_TDS_AMOUNT = 0) : item.CURRENT_TDS_AMOUNT;
//              item.NET_INTEREST_AMOUNT == null ? (item.NET_INTEREST_AMOUNT = 0) : item.NET_INTEREST_AMOUNT;
//
//              vm.interestTotal = vm.interestTotal + parseInt(item.GROSS_INTEREST);
//              //                                 var totalTdsAmount = parseInt(item.ARREAR_TDS_AMOUNT) + parseInt(item.CURRENT_TDS_AMOUNT);
//              vm.TdsTotal = vm.TdsTotal + parseInt(item.CURRENT_TDS_AMOUNT);
//              vm.netAmtTotal = vm.netAmtTotal + parseInt(item.NET_INTEREST_AMOUNT);
//
//              item.accountOpenDate = new Date(item.accountOpenDate);
//              item.GROSS_INTEREST = Number(item.GROSS_INTEREST).toLocaleString("en-IN");
//              item.CURRENT_TDS_AMOUNT = Number(item.CURRENT_TDS_AMOUNT).toLocaleString("en-IN");
//              //                                                             item.TDS_AMT = totalTdsAmount;
//              item.NET_INTEREST_AMOUNT = Number(item.NET_INTEREST_AMOUNT).toLocaleString("en-IN");
//            });
//          }
//          vm.interestTotal = Number(vm.interestTotal).toLocaleString("en-IN");
//          vm.TdsTotal = Number(vm.TdsTotal).toLocaleString("en-IN");
//          vm.netAmtTotal = Number(vm.netAmtTotal).toLocaleString("en-IN");
        },
        function (error) {
          vm.loader = false;
          if ((error.data.message == "No Data Found") | (error.data.message == "NDF")) {
            vm.noDataFound = error.data.message;
          } else {
            handleErrorResponse(error);
//            vm.goPreviousState();
          }
        }
      );
    }
//
//    function getTrialRunEligibleAccountsFile() {
//      vm.loader = true;
//
//      var requestData = {
//        schemeCode: vm.schemeCode,
//        intertestPaymentFor: vm.interestPaid,
//        finacialYear: vm.financialYear,
//        interestPaymentMode: vm.interestPaymentMode,
//        schemeName: vm.schemeName,
//        schemeName: vm.schemeName,
//        pageSize: vm.pageSize,
//        pageNo: vm.pageNo,
//        searchValue: vm.searchValue,
//        sortOrder: vm.sortOrder,
//        sortField: vm.sortField,
//        downloadType: vm.downloadType,
//        isDownload: "Y",
//        branchName: vm.loginSolDesc,
//      };
//
//      rbiBondInterestTrailRunPaymentWiseService.getDetails(requestData).then(
//        function (response) {
//          vm.loader = false;
//          vm.trialRunDetailsRepList = response.data.result;
//
//          if (response.data.message != "SUCCESS") {
//            Swal.fire("Warning!", "No Record Found", "warning");
//          } else {
//            vm.fileData = vm.trialRunDetailsRepList.fileData;
//            vm.fileName = vm.trialRunDetailsRepList.fileName;
//            saveAs(getBlobFromBase64String(vm.fileData), vm.fileName);
//            Swal.fire("Success!", vm.fileName + "\n Downloaded Successfully", "success");
//          }
//        },
//        function (error) {
//          vm.loader = false;
//
//          handleErrorResponse(error);
//          Swal.fire("Error!", "Download unsuccessful", "error");
//
//          vm.goPreviousState();
//        }
//      );
//    }

    //         function getTdsDeductionDetail(){
    //                       vm.loader = true;
    //                                  var requestData = {
    //                                      'schemeCode':vm.schemeCode,
    //                                      'branchId': vm.branchId,
    //                                      'blaNo': vm.blaAccNo,
    //                                      'tdsDeductFrom' : vm.fromDate,
    //                                         'tdsDeductTo': vm.toDate
    //
    //                                  }
    //
    //                                  rbibondTdsDeductionService.getTdsDetails(requestData).then(function(response) {
    //                                      vm.loader = false;
    //
    //                                       vm.model =response.data.result;
    //
    //                              configDataTable();
    //                         }, function(error){
    //                             vm.loader = false;
    //                             //Swal.fire
    //                            Swal.fire("No Data Found", msg, "success");
    ////                             if(error.data.message == 'No Data Found' | error.data.message == 'NDF'){
    ////                                   vm.noDataFound = error.data.message;
    ////                             }else{
    ////                               handleErrorResponse(error);
    ////                               $state.go('rbibond_nomination_register.inquiry_details');
    ////                             }
    //
    //                         })
    //                     }
//    vm.goNextState = function (stateObj) {
//      $state.go(vm.nextState, {
//        obj: stateObj,
//      });
//    };
//
//    vm.goPreviousState = function () {
//      if ($state.current.name == "rbibond_interest_trail_run_payment_wise_report.inquiry_details") {
//        $state.go("rbibond_interest_trail_run_payment_wise_report.inquiry");
//      } else if ($state.current.name == "rbibond_interest_trail_run_payment_wise_report.inquiry_details") {
//        $state.go("rbibond_interest_trail_run_payment_wise_report.inquiry");
//      } else if ($state.current.name == "rbibond_interest_trail_run_payment_wise_report.inquiry_details") {
//        $state.go("rbibond_interest_trail_run_payment_wise_report.inquiry");
//      }
//    };
//
//    function cancel() {
//      if ($state.current.name == "rbibond_interest_trail_run_payment_wise_report.inquiry") {
//        location.href = "#!/dc_rbi_bond_menu";
//      } else if ($state.current.name == "rbibond_interest_trail_run_payment_wise_report.inquiry") {
//        location.href = "#!/rbibond_gu_functionality";
//      } else if ($state.current.name == "rbibond_interest_trail_run_payment_wise_report.inquiry") {
//        location.href = "#!/rbibond_gu_functionality";
//      }
//    }
//
//    vm.dateValidation = function () {
//      vm.toDate = "";
//
//      vm.dateToOptions = {
//        minDate: vm.fromDate,
//        maxDate: convertToDateObject(vm.currentDate),
//      };
//      vm.dateOptions = {
//        maxDate: convertToDateObject(vm.currentDate),
//      };
//    }
//
  }
})();
