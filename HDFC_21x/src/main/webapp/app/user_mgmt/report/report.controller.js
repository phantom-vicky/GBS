(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .controller('ReportController', ReportController);

    ReportController.$inject = ['$scope', '$state', '$stateParams', '$filter', 'reportService','$location', '$rootScope'];

    function ReportController($scope, $state, $stateParams, $filter, reportService,$location,$rootScope) {

        var vm = this;
        vm.action = $stateParams.action;
        vm.itemsPerPage = "10";
        vm.currentPage = 1;
        vm.record = {};
        vm.showList = false;

        vm.getToDatePickerOptions = getToDatePickerOptions;
        vm.getFromDatePickerOptions = getFromDatePickerOptions;

        vm.getUnSuncessfulReport = getUnSuncessfulReport;
        vm.getUnSuncessfulReportExport = getUnSuncessfulReportExport;


        vm.getSolID = getSolID;
        vm.record.solId = '';
        vm.getUserLoggedInIpAddress = getUserLoggedInIpAddress;
        vm.getUserLoggedInIpAddressExport = getUserLoggedInIpAddressExport;

        vm.getListOfUserRelatedActivityCarrierOnDay = getListOfUserRelatedActivityCarrierOnDay
        vm.getListOfUserRelatedActivityCarrierOnDayExport = getListOfUserRelatedActivityCarrierOnDayExport;

        vm.getUserSignOnSIgnOff = getUserSignOnSIgnOff;
        vm.getUserSignOnSIgnOffExport = getUserSignOnSIgnOffExport;


        vm.getDormantUserIdReport = getDormantUserIdReport;
        vm.getDormantUserIdReportExport = getDormantUserIdReportExport;

        vm.sortType = 'userId'; // set the default sort type
        vm.sortReverse = false; // set the default sort order
        vm.searchInquiry = ''; // set the default search/filter term
        vm.back = back;
        vm.loginSolId = $rootScope.globals.currentUser.solId;
        vm.loginSolDesc = $rootScope.globals.currentUser.solDesc;
        vm.configDataTable = configDataTable;
        vm.getDetails = getDetails;
        vm.activateUser = activateUser;
         vm.currentDate = new Date();
         vm.currentDate1 = $filter('date')(vm.currentDate, 'dd/MM/yyyy');


        if ($state.current.name == 'report_on_user_sign_on_sign_off' ||
            $state.current.name == 'report_on_list_of_user_related_activities_carried_out_on_a_day' ||
            $state.current.name == 'reports_on_user_logged_in_more_than_one_ip_address_during_the_day' ||
            $state.current.name == 'reports_on_unsucessful_login_attempts' ||
            $state.current.name == 'report_on_user') {
            configDataTable();
            if (vm.showList = false) {
                vm.records = '';
            }

        }

        function configDataTable() {
            vm.getTitleDescription = {
                title: 'User Report',
                solId: $rootScope.globals.currentUser.solId,
                solDesc: $rootScope.globals.currentUser.solDesc
            }
            vm.dataTableOption = configDataTableOptions(vm.getTitleDescription);
        }
        //  From Date picker options
        function getFromDatePickerOptions() {
            vm.dateFromOptions = {
                maxDate: new Date(),
                showWeeks: false
            };
        }

        // To Date picker options
        function getToDatePickerOptions() {
            vm.dateToOptions = {
                minDate: vm.report.dateFrom,
                maxDate: new Date(),
                showWeeks: false
            };
        }

        function formatDate(tempDate) {
            return $filter('date')(tempDate, "yyyy-MM-dd");
        }

        function back() {
           vm.showList = false;
            vm.report.solId = '';
             vm.report.dateFrom = '';
            vm.report.username = '';
            vm.report.dateTo = '';

        }

        function getUnSuncessfulReport() {

            var fromDate = Date.parse(vm.report.dateFrom);
            var toDate = Date.parse(vm.report.dateTo);
            vm.loader = true;
            reportService.getUnSuncessfulReport(fromDate, toDate)
                .then(
                    function(response) {
                        vm.showList = true;
                        vm.reportList = response.data;
                        vm.loader = false;

                    }, handleErrorResponse
                );
        }


        function getUnSuncessfulReportExport() {
            vm.loader = true;
            var fromDate = Date.parse(vm.report.dateFrom);
            var toDate = Date.parse(vm.report.dateTo);
            var xhr = new XMLHttpRequest();
            var postData = {from : fromDate, to : toDate}
            xhr.open('POST', 'gbmultra/user/report/login/unsuccessful/export', true);
            xhr.responseType = 'arraybuffer';
            xhr.onload = function(e) {
                if (this.status == 200) {
                    downloadFile(this.response, "Report on Unsuccessful login attempts_" + new Date() + ".pdf");
                }
            };

            xhr.send(JSON.stringify(postData));
        }


        function downloadFile(file, filename) {
            var blob = new Blob([file], {type: "application/pdf"});
            if (navigator.appVersion.toString().indexOf('.NET') > 0)
                window.navigator.msSaveBlob(blob, "Report_" + new Date() + ".pdf");
            else {

                var link = document.createElement('a');
                var url = window.URL.createObjectURL(blob);
                link.href = url;
                link.download = filename;
                document.body.appendChild(link);
                link.click();

                setTimeout(function() {
                    document.body.removeChild(link);
                    window.URL.revokeObjectURL(url);
                }, 100);
            }

            vm.loader = false;
            $scope.$apply();
        }


        //Get solId
        function getSolID(solId) {
            vm.loader = true;
            reportService.getSolDetails(vm.record.solId).then(
                function(response) {
                    $('#solIDModal').modal('toggle');
                    vm.list = response.data;
                    vm.loader = false;
                });
        }

        function getUserLoggedInIpAddress() {
            console.log(vm.report.solId);
            var fromDate = Date.parse(vm.report.dateFrom);

            vm.loader = true;
            reportService.getUserLoggedInIpAddress(vm.report.solId, fromDate)
                .then(
                    function(response) {
                        vm.showList = true;
                        vm.reportList = response.data;
                        vm.loader = false;
                    }, handleErrorResponse
                );
        }

        function getUserLoggedInIpAddressExport() {
            vm.loader = true;
            var fromDate = Date.parse(vm.report.dateFrom);
            var solId = vm.report.solId;
            var xhr = new XMLHttpRequest();
            var postData = {
                solId : solId,
                from : fromDate
            }
            xhr.open('post', 'gbmultra/user/report/login/multipledevices/export', true);
            xhr.responseType = 'arraybuffer';
            xhr.onload = function(e) {
                if (this.status == 200) {
                   downloadFile(this.response, "Report on Users logged in from more than one IP address during the day_" + new Date() + ".pdf");
                }
            };
            xhr.send(JSON.stringify(postData));
        }


        function getListOfUserRelatedActivityCarrierOnDay() {
            var fromDate = Date.parse(vm.report.dateFrom);
            vm.loader = true;
            reportService.getListOfUserRelatedActivityCarrierOnDay(fromDate)
                .then(
                    function(response) {
                        vm.showList = true;
                        vm.reportList = response.data;
                        vm.loader = false;
                    }, handleErrorResponse
                );
        }

        function getListOfUserRelatedActivityCarrierOnDayExport() {
            vm.loader = true;
            var fromDate = Date.parse(vm.report.dateFrom);
            var xhr = new XMLHttpRequest();
            xhr.open('post', 'gbmultra/user/report/activities/admin/export', true);
            var postData = {
                from : fromDate
            }
            xhr.responseType = 'arraybuffer';
            xhr.onload = function(e) {
                if (this.status == 200) {
                   downloadFile(this.response, "Report on List of user related activities carried out on a day_"+ new Date() + ".pdf");
                }
            };
            xhr.send(JSON.stringify(postData));
        }

        function getDormantUserIdReport() {

            var fromDate = Date.parse(vm.report.dateFrom);
            var toDate = Date.parse(vm.report.dateTo);
            vm.loader = true;
            reportService.getDormantUserIdReport(fromDate, toDate)
                .then(
                    function(response) {
                        vm.showList = true;
                        vm.reportList = response.data;
                        vm.loader = false;

                    }, handleErrorResponse
                );
        }

        function getDormantUserIdReportExport() {
            vm.loader = true;
            var fromDate = Date.parse(vm.report.dateFrom);
            var toDate = Date.parse(vm.report.dateTo);
            var xhr = new XMLHttpRequest();
            xhr.open('post', 'gbmultra/user/report/dormant/export', true);
            var postData = {
                from : fromDate,
                to : toDate
            }
            xhr.responseType = 'arraybuffer';
            xhr.onload = function(e) {
                if (this.status == 200) {
                   downloadFile(this.response, "Report on Dormant User-IDs_" + new Date() + ".pdf");
                }
            };
            xhr.send(JSON.stringify(postData));
        }


        function getUserSignOnSIgnOff() {

            var fromDate = Date.parse(vm.report.dateFrom);
            var toDate = Date.parse(vm.report.dateTo);
            var data = {};
            data.solId = vm.report.solId;
            data.fromDate = fromDate;
            data.toDate = toDate;
            data.username = vm.report.username;
            vm.loader = true;
            reportService.getUserSignOnSIgnOff(data)
                .then(
                    function(response) {
                        vm.showList = true;
                        vm.reportList = response.data;
                        vm.loader = false;
                    }, handleErrorResponse
                );
        }

        function getUserSignOnSIgnOffExport() {
            vm.loader = true;
            var fromDate = Date.parse(vm.report.dateFrom);
            var toDate = Date.parse(vm.report.dateTo);

            var xhr = new XMLHttpRequest();
            xhr.open('post', 'gbmultra/user/report/login/export', true);
            xhr.responseType = 'arraybuffer';
            xhr.setRequestHeader("Content-type", "application/json");
            xhr.send(JSON.stringify({
                solId: vm.report.solId,
                fromDate: fromDate,
                toDate: toDate,
                username: vm.report.username !=  undefined ? vm.report.username.toUpperCase() : ""
            }));
            xhr.onload = function(e) {
                if (this.status == 200) {
                   downloadFile(this.response, "Report on User sign on/sign off_"+ new Date() + ".pdf");
                }
            };
            //xhr.send();
        }

       function getDetails(flg) {
                  vm.loader = true;
                 if(flg == 'B'){
                   var requestData = {
                                      'branchFlag' : 'Y'
                                     //  'branchId' : '',
                                  };
                  }else {
                      var requestData = {
                         // 'branchFlag' : 'Y',
                          'branchID' : vm.searchBranchId
                      }
                  }
                  reportService.getDetails(requestData).then(function(response) {
                      vm.loader = false;
                    if (flg == 'B') {
                         vm.branchIdList = response.data.result;
                         angular.element('#branchIdSearcherPopUp').modal('toggle');
                           }
                          else {
                          configDataTable();
                           vm.showList = true;
                          vm.userList = response.data.result;
                      }
                  }, function(error) {
                      vm.loader = false;
                      handleErrorResponse(error);
                  })
              }

                     function activateUser() {
                                vm.loader = true;
                                    var requestData = {
                                        'username' : vm.username
                                    }
                                reportService.activateUser(requestData).then(function(response) {
                                    vm.loader = false;
                                    Swal.fire("Status!", response.data.message, "success");
                                }, function(error) {
                                    vm.loader = false;
                                    handleErrorResponse(error);
                                })
                            }


        function handleErrorResponse(response) {
            vm.loader = false;
            var msg = response.data.message ? response.data.message : "Server Error";
            Swal.fire("Error!", msg, "error");
        }


    }

})();