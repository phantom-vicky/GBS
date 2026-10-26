(function() {

	'use strict';

	angular.module('gbmUltraApp').controller(
			'AnnualInfoController',
			AnnualInfoController);

	AnnualInfoController.$inject = [ '$scope', '$filter',
			'$state', '$stateParams','$location','$rootScope','AnnualInfoService', 'commonService' ];

	function AnnualInfoController($scope, $filter, $state,
			$stateParams, $location, $rootScope, AnnualInfoService,
			commonService) {

		var vm = this;

        vm.currentPage = 1;
        vm.itemsPerPage = '5';
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
         vm.bankName = $rootScope.globals.currentUser.bankName;
        vm.solDate = $rootScope.globals.currentUser.systemDate;
        vm.currentDate = new Date();
        vm.currentDate = $filter('date')(vm.currentDate, 'dd/MM/yyyy');
        vm.getAnnualInfoReport = getAnnualInfoReport;
        vm.getModuleName=getModuleName;
        vm.dateValidation=dateValidation;


    // Pagination integration
    vm.showNext = false;
    vm.showPrevious = false;
    $scope.sortReverse = false;
    $scope.sort = function (key) {
      $scope.sortKey = key;
      $scope.sortReverse = $scope.sortKey == key ? !$scope.sortReverse : $scope.sortReverse;
      $scope.sortReverse ? (vm.sortOrder = "DESC") : (vm.sortOrder = "ASC");
      vm.sortField = key;
      vm.getAnnualInfoReport();
    };
    vm.pageNo = 1;
    vm.filteredRecords = [];
    vm.pageSizeOptions = [5,10,25,50,100];
    vm.pageSize = vm.pageSizeOptions[0];
    vm.currentPage = 1;
    vm.maxPagesToShow = 5;
    vm.searchText = "";
    vm.loading = true;
    vm.showExportOptions = false;

    vm.goPreviousPage = function () {
      vm.pageNo = parseInt(vm.pageNo) - 1;
      vm.getAnnualInfoReport();
    };

    vm.goNextPage = function () {
            vm.pageNo = parseInt(vm.pageNo) + 1;
            vm.getAnnualInfoReport();
    };

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
    };

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

    vm.updateFilteredRecords = function () {
      const searchText = vm.searchText ? vm.searchText.toLowerCase() : "";
      vm.filteredRecords = vm.model.filter((record) => {
        return Object.values(record).some((value) => value !== null && value !== undefined && String(value).toLowerCase().includes(searchText));
      });
      vm.totalPages = Math.ceil(vm.filteredRecords.length / vm.pageSize);
      vm.setPage(1);
    };

        if ($state.current.name == 'Annual_Informatation_Report.inquiry_details')
        {
        vm.schemeCode = $stateParams.obj.schemeCode;
        vm.schemeName = $stateParams.obj.schemeName;
        vm.financialYear = $stateParams.obj.financialYear;
         getAnnualInfoReport();

        }

        vm.downloadReport = function () {
                      if (vm.downloadType === "excel") {
                        vm.downloadExcel();
                      }
                      if (vm.downloadType === "csv") {
                        vm.downloadTXT();
                      }
                       vm.downloadType='';
                    };

        vm.downloadExcel = function () {
            var currentPageRecords = vm.filteredRecords.slice((vm.currentPage - 1) * vm.pageSize, vm.currentPage * vm.pageSize);

            var exportData = currentPageRecords.map(function (x, index) {
                return {
                    "Report Serial Number": (vm.currentPage - 1) * vm.pageSize + index + 1,
                    "Original Report Serial Number": x.orginalSerialNo,
                    "Person Name": x.personName,
                    "Person Type": x.personType,
                    "Customer Identity": x.customerIdentity,
                    "Gender": x.gender,
                    "Father’s Name": x.fatherName,
                    "PAN": x.pan,
                    "Aadhaar Number": x.aadharNo,
                    "Form 60 Acknowledgment": x.form60Acknowledgment,
                    "Identification Type": x.identificationType,
                    "Identification Number": x.identificationNo,
                    "Date of birth/Incorporation": x.dateOfBirth,
                    "Nationality/Country of Incorporation": x.nationality,
                    "Address Type": x.addressType,
                    "Fix Address": x.fixedAddress,
                    "City/Town": x.city,
                    "Post Code": x.postCode,
                    "State": x.state,
                    "Country Code": x.countryCode,
                    "Business or Occupation": x.business,
                    "Primary STD Code": x.primaryStdCode,
                    "Primary Phone No": x.primaryPhoneNo,
                    "Primary Mobile No": x.primaryMobileNo,
                    "Secondary STD Code": x.secondaryStdCode,
                    "Secondary Phone No": x.secondaryPhoneNo,
                    "Secondary Mobile No": x.secondaryMobileNo,
                    "Email": x.email,
                    "Remarks": x.remarks,
                    "Product Type": x.productType,
                    "Aggregate Gross Amount Received from the Person": x.aggregateGrossAmountRecieved,
                    "Aggregate Gross Amount Received from the Person in Cash": x.aggregateGrossAmountCash,
                    "Aggregate Gross Amount Paid to the Person": x.aggregateGrossAmountPaid,
                    "Transaction Summary Remarks": x.transactionSummaryRemarks,
                    "Product Identifier": x.productIdentifier,
                    "Last Date of Transaction": x.lastDateOfTransaction,
                    "Amount Received From Person": x.amountRecieved,
                    "Cash Amount Received From Person": x.cashAmountRecieved,
                    "Amount Paid To Person": x.amountPaidToPerson,
                    "Related Account Number": x.relatedAccountNumber,
                    "Related Institute Name": x.relatedInstituteName,
                    "Related Institute Ref Number": x.relatedRefNo,
                    "Financial Transaction Details Remarks": x.financialTransactionDtlsRemarks
                };
            });

            var ws = XLSX.utils.json_to_sheet(exportData);
            var wb = XLSX.utils.book_new();
            XLSX.utils.book_append_sheet(wb, ws, "Report");

            XLSX.writeFile(wb, "AnnualInformationReport.xlsx");
        };

       vm.downloadTXT = function () {
           const currentPageRecords = vm.filteredRecords.slice((vm.currentPage - 1) * vm.pageSize, vm.currentPage * vm.pageSize);

           const headers = [
               "Report Serial Number", "Original Report Serial Number", "Person Name", "Person Type",
               "Customer Identity", "Gender", "Father’s Name", "PAN", "Aadhaar Number",
               "Form 60 Acknowledgment", "Identification Type", "Identification Number",
               "Date of birth/Incorporation", "Nationality/Country of Incorporation", "Address Type",
               "Fix Address", "City/Town", "Post Code", "State", "Country Code", "Business or Occupation",
               "Primary STD Code", "Primary Phone No", "Primary Mobile No", "Secondary STD Code",
               "Secondary Phone No", "Secondary Mobile No", "Email", "Remarks", "Product Type",
               "Aggregate Gross Amount Received", "Cash Received", "Amount Paid", "Transaction Summary Remarks",
               "Product Identifier", "Last Date of Transaction", "Amount Received", "Cash Received",
               "Amount Paid", "Related Account Number", "Institute Name", "Institute Ref Number",
               "Financial Remarks"
           ];

           const pad = (val, width = 30) => {
               val = (val || '').toString().replace(/[\n\r]/g, ' ');
               return val.length > width ? val.substring(0, width - 3) + '...' : val.padEnd(width, ' ');
           };

           let textContent = headers.map(h => pad(h)).join('') + '\n';

           currentPageRecords.forEach((x, index) => {
               const row = [
                   (vm.currentPage - 1) * vm.pageSize + index + 1,
                   x.originalSerialNo, x.personName, x.personType, x.customerIdentity,
                   x.gender, x.fatherName, x.pan, x.aadharNo, x.form60Acknowledgment,
                   x.identificationType, x.identificationNo, x.dateOfBirth, x.nationality,
                   x.addressType, x.fixedAddress, x.city, x.postCode, x.state, x.countryCode,
                   x.business, x.primaryStdCode, x.primaryPhoneNo, x.primaryMobileNo,
                   x.secondaryStdCode, x.secondaryPhoneNo, x.secondaryMobileNo, x.email,
                   x.remarks, x.productType, x.aggregateGrossAmountReceived,
                   x.aggregateGrossAmountCash, x.aggregateGrossAmountPaid,
                   x.transactionSummaryRemarks, x.productIdentifier, x.lastDateOfTransaction,
                   x.amountReceived, x.cashAmountReceived, x.amountPaidToPerson,
                   x.relatedAccountNumber, x.relatedInstituteName, x.relatedRefNo,
                   x.financialTransactionDtlsRemarks
               ].map(val => pad(val));

               textContent += row.join('') + '\n';
           });

           const blob = new Blob([textContent], { type: "text/plain;charset=utf-8" });
           const link = document.createElement("a");
           link.href = URL.createObjectURL(blob);
           link.download = "AnnualInformationReport.txt";
           link.click();
       };






     function getModuleName() {
            vm.loader = true;
                 var requestData = {
                   flag:"S",
                 };
                 AnnualInfoService.getDetails(requestData).then(
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
        vm.financialYears = function (){
          vm.loader = true;
          var requestData = {
            flag:'FY'
          };
          AnnualInfoService.getDetails(requestData).then(
                             function (response) {
                               vm.loader = false;
                               vm.finYearList = response.data.result.list;
                             },
                             function (error) {
                               handleErrorResponse(error);
                               vm.loader = false;
                             }
                  );

        }

        function getAnnualInfoReport(){

                          vm.loader = true;
                          var requestData = {
                              schemeCode:vm.schemeCode,
                              flag:'T',
                              financialYear: vm.financialYear
                          }

                          AnnualInfoService.getDetails(requestData).then(function(response) {
//                              vm.loader = false;
//
//                               vm.model = response.data.result.list;
//                               vm.filteredRecords =  response.data.result.list;
//                               vm.totalPages         = Math.ceil(vm.model.length / vm.pageSize);
//                               vm.pageDetails        = response.data.result.list;
//                               vm.updatePagination();

vm.loader = false;
          vm.model = response.data.result.list;
          vm.totalPages = Math.ceil(vm.model.length / vm.pageSize);
          vm.pageDetails = response.data.result.list;
          AnnualInfoService.getReferenceData({ referenceId: "HOLDER_TYPE" }).then(function (holderTypeResult) {
            var holderTypeDesc = {};
            holderTypeResult.data.result.forEach(function (item) {
              holderTypeDesc[item.SUBCODE] = item.DESCRIPTION;
            });

            AnnualInfoService.getReferenceData({ referenceId: "STATES OF INDIA" }).then(function (stateRes) {
              var stateMap = {};
              stateRes.data.result.forEach(function (item) {
                stateMap[item.SUBCODE] = item.DESCRIPTION;
              });

              vm.filteredRecords = vm.model.map(function (record) {
                return {
                  ...record,
                  personType: holderTypeDesc[record.personType] || "",
                  state: stateMap[record.state] || "",
                };
              });

               vm.model = vm.model.map(function (record) {
                              return {
                                ...record,
                                personType: holderTypeDesc[record.personType] || "",
                                state: stateMap[record.state] || "",
                              };
                            });

              vm.updatePagination();
              });
              });
                 },
                  function(error){

                            vm.loader = false;
                            if (error.data.message == 'No Data Found') {
                                vm.noDataFound = error.data.message;
                            } else {
                                handleErrorResponse(error);
                                $state.go('Annual_Informatation_Report.inquiry')
                            }
                        })
             }
              function dateValidation() {
                              vm.toDate = '';

                              vm.dateToOptions = {
                                  minDate: vm.fromDate,
                                  maxDate:convertToDateObject(vm.currentDate)
                              };
                              vm.dateOptions = {
                               maxDate:convertToDateObject(vm.currentDate)
                              }


                          }

            vm.totalPrincipalAmount = function () {
            return vm.filteredRecords.reduce((sum, record) => sum + (record.amountOfRepayment ? parseFloat(record.amountOfRepayment) : 0), 0);
            };

	}
})();