(function () {

  'use strict';

  angular.module('gbmUltraApp').controller(
    'UnlockBlaController',
    UnlockBlaController);

  UnlockBlaController.$inject = ['$scope', '$filter',
    '$state', '$stateParams', '$location', '$rootScope', 'UnlockBlaService', 'commonService'];

  function UnlockBlaController($scope, $filter, $state,
    $stateParams, $location, $rootScope, UnlockBlaService,
    commonService) {

    var vm = this;

//    vm.currentPage = 1;
//    vm.itemsPerPage = '5';
    vm.loginSolId = $rootScope.globals.currentUser.solId;
    vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
    vm.solDate = $rootScope.globals.currentUser.systemDate;
    vm.currentDate = new Date();
    vm.currentDate = $filter('date')(vm.currentDate, 'dd/MM/yyyy');
    vm.unlockBla = unlockBla;
    vm.unlockBlaSubmit = unlockBlaSubmit;

    vm.CheckUncheckHeader = CheckUncheckHeader;
    vm.CheckUncheckAll = CheckUncheckAll;
    vm.validateSubmitCheckbox = validateSubmitCheckbox;

    vm.loginSolDate = $rootScope.globals.currentUser.solDate;


    //    Pagination Integration
    vm.goPreviousPage = goPreviousPage;
    vm.goNextPage = goNextPage;
    vm.paginationControls = paginationControls;
    vm.showNext = false;
    vm.showPrevious = false;
    $scope.sortReverse = false;
    $scope.sort = function (key) {
      $scope.sortKey = key;
      $scope.sortReverse =
        $scope.sortKey == key ? !$scope.sortReverse : $scope.sortReverse;
      $scope.sortReverse ? (vm.sortOrder = "DESC") : (vm.sortOrder = "ASC");
      vm.sortField = key;
      vm.getEtdsDetails();
    };
    vm.pageNo = 1;
    vm.pageData = null;

    vm.records = [];
    vm.filteredRecords = [];
    vm.pageSizeOptions = [5, 10, 25, 50, 100];
    vm.pageSize = vm.pageSizeOptions[0];
    vm.currentPage = 1;
    vm.maxPagesToShow = 5;
    vm.searchText = "";
    vm.loading = true;
    vm.showExportOptions = false;
//    vm.itemsPerPage = "10";

      vm.maxValue = function () {
                return Math.min(vm.currentPage * vm.pageSize, vm.filteredRecords.length);
            };

    function goPreviousPage() {
      vm.pageNo = parseInt(vm.pageNo) - 1;
      vm.getEtdsDetails();
    }

    function goNextPage() {
      vm.pageNo = parseInt(vm.pageNo) + 1;
      vm.getEtdsDetails();
    }

    function paginationControls() {
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
    if (page < 1) {
      vm.currentPage = 1;
    } else if (page > vm.totalPages) {
      vm.currentPage = vm.totalPages;
    } else {
      vm.currentPage = page;
    }

    // Re-evaluate the global checkbox state
    CheckUncheckHeader();

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
          } else if (
            vm.currentPage + maxPagesAfterCurrentPage >=
            vm.totalPages
          ) {
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



    if ($state.current.name == 'unlock_bla.inquiry_details') {

      vm.schemeCode = $stateParams.obj.schemeCode;
      vm.schemeName = $stateParams.obj.schemeName;

      unlockBla();

    }


    function unlockBlaSubmit() {
      var userList = [];

      angular.forEach(vm.filteredRecords, function (user) {
        if (user.Selected === true) {
          userList.push(user.panNum);
        }
      });


      var requestData = {
        'schemeCode': vm.schemeCode,
        'panDet': userList
      };

      Swal.fire({
        title: "Are you sure?",
        text: "Do you want to Really Modify this record?",
        type: "warning",
        showCancelButton: true,
        cancelButtonClass: "btn-cancel",
        confirmButtonClass: "btn-primary",
        confirmButtonText: "Yes",
        closeOnConfirm: false
      }). then((result) => {
        if (result.isConfirmed) {
        vm.loader = true;
        UnlockBlaService.unlock(requestData).then(
        function (response) {
          vm.loader = false;
if (response.data.message === 'SUCCESS') {
            // Adjust based on your backend response structure
            Swal.fire(" ", "BLA Unlocked Successfully", "success");
        }
//          Swal.fire(" ", "BLA Unlocked Successfully", "success");
          $state.go('unlock_bla.inquiry');
        }).catch(function (error) {
          handleErrorResponse(error);
          vm.loader = false;
          $state.go('unlock_bla.inquiry');
        });
        }
      });
    }


function unlockBla(flag) {
  vm.loader = true;
  if (flag == 'schemeCode') {
    var requestData = {};
    UnlockBlaService.getDetails(requestData).then(function (response) {
      vm.loader = false;
      vm.schemeList = response.data.result;
      angular.element('#schemeModel').modal('toggle');
    }, function (error) {
      handleErrorResponse(error);
      vm.loader = false;
    });
  } else {
    var requestData = {
      'schemeCode': vm.schemeCode
    };
    UnlockBlaService.getDetails(requestData).then(function (response) {
      vm.loader = false;

      // Initialize users and set Selected property to true
      vm.users = response.data.result.map(user => {
        user.Selected = true;
        return user;
      });

      vm.filteredRecords = vm.users;
      vm.pageDetails = response.data.resultt;
      vm.records = vm.users;

      // Ensure all checkboxes are checked by default
      vm.IsAllChecked = true;

      vm.totalPages = Math.ceil(vm.users.length / vm.pageSize);
      vm.updatePagination();
    }, function (error) {
      vm.loader = false;
      if (error.data.message == 'No Data Found' || error.data.message == 'NDF') {
        vm.noDataFound = error.data.message;
      } else {
        handleErrorResponse(error);
        $state.go('unlock_bla.inquiry');
      }
    });
  }
}


    function CheckUncheckHeader() {

    vm.selectedRecords=vm.filteredRecords.slice((vm.currentPage-1)*vm.pageSize, vm.currentPage*vm.pageSize);
    console.log("selectedRecords  ", vm.selectedRecords);

      vm.IsAllChecked = vm.selectedRecords.every(function (item) {
        return item.Selected;
      });
    };



function CheckUncheckAll() {
  var toggleStatus = !vm.IsAllChecked;
  // Update all records globally
  angular.forEach(vm.filteredRecords, function (item) {
    item.Selected = toggleStatus;
  });

  // Update the global checkbox state
  vm.IsAllChecked = toggleStatus;
}

    function validateSubmitCheckbox() {
    vm.selectedRecords=vm.filteredRecords.slice((vm.currentPage-1)*vm.pageSize, vm.currentPage*vm.pageSize);
     console.log("validateSubmitCheckbox  ", vm.selectedRecords);

      var unCheckedList = vm.selectedRecords.filter(i => i.Selected);
      console.log("unCheckedList",unCheckedList);
      if (unCheckedList.length <= 0) {
        return true;
      } else {
        return false;
      }
    }


  }
})();