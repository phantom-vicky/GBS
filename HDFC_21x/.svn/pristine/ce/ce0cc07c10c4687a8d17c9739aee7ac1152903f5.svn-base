(function() {

    'use strict';

    angular.module('gbmUltraApp').controller(
        'cbsMaintenanceOfCalendarEntriesController',
        cbsMaintenanceOfCalendarEntriesController);

    cbsMaintenanceOfCalendarEntriesController.$inject = ['$scope', '$filter', '$timeout',
        '$state', '$stateParams', '$location',
        'cbsMaintenanceOfCalendarEntriesService', 'commonService'
    ];

    function cbsMaintenanceOfCalendarEntriesController($scope, $filter, $timeout, $state,
        $stateParams, $location, cbsMaintenanceOfCalendarEntriesService,
        commonService) {

        var vm = this;
        vm.dataList = [];
        vm.cbsMaintenanceOfCalendarEntries = {};
        vm.itemsPerPage = "10";
        vm.commonYesNoMetaData = COMMON_YES_NO_METADATA;
        vm.getYears = getYears;
        vm.list = list;
        vm.listUnverifiedRecords = listUnverifiedRecords;
        vm.listVerifiedRecords = listVerifiedRecords;
        vm.getRecord = getRecord;
        vm.modify = modify;
        vm.verify = verify;
        vm.add = add;
        vm.deleteMaintenanceOfCalendarEntries = deleteMaintenanceOfCalendarEntries;
        vm.showCancelConfirmation = showCancelConfirmation;
        vm.addSubmit = addSubmit;
        vm.modifySelect = modifySelect;
        vm.modifySubmit = modifySubmit;
        vm.verifySelect = verifySelect;
        vm.verifySubmit = verifySubmit;
        vm.deleteSelect = deleteSelect;
        vm.deleteSubmit = deleteSubmit;
        vm.inquirySelect = inquirySelect;
        vm.Selectclear = Selectclear;
        vm.getAddDetails = getAddDetails;
        vm.getVerifyDetails = getVerifyDetails;
        vm.daysInMonth = daysInMonth;
        vm.formatDate = formatDate;
        vm.clearRemarks = clearRemarks;
        vm.getRemarksList = getRemarksList;

    function daysInMonth(month, year) {
            return new Date(year, month, 0).getDate();
        }

        function formatDate(date) {
            var day = date.getDate();
            if (day < 10) {
                day = "0" + day;
            }
            var month = date.getMonth() + 1;
            if(month < 10)
            {
            month="0"+month;
            }

            var year = date.getFullYear();
            return day + "/" + month + "/" + year;
        }
        function nextDate(date,days) {
            var day = date.getDate()+days;
            if (day < 10) {
                day = "0" + day;
            }
            var month = date.getMonth() + 1;
            if(month < 10)
            {
            month="0"+month;
            }

            var year = date.getFullYear();
            return day + "/" + month + "/" + year;
        }

        function getAddDetails() {
            vm.loader = true;
            var requestData = {
                'scrollYear': vm.scrollYear,
                'scrollMonth': vm.scrollMonth
            }
            if (vm.scrollMonth == 'Jan') {
                var month = 1;
            } else if (vm.scrollMonth == 'Feb') {
                var month = 2;
            } else if (vm.scrollMonth == 'Mar') {
                var month = 3;
            } else if (vm.scrollMonth == 'Apr') {
                var month = 4;
            } else if (vm.scrollMonth == 'May') {
                var month = 5;
            } else if (vm.scrollMonth == 'June') {
                var month = 6;
            } else if (vm.scrollMonth == 'July') {
                var month = 7;
            } else if (vm.scrollMonth == 'Aug') {
                var month = 8;
            } else if (vm.scrollMonth == 'Sep') {
                var month = 9;
            } else if (vm.scrollMonth == 'Oct') {
                var month = 10;
            } else if (vm.scrollMonth == 'Nov') {
                var month = 11;
            } else if (vm.scrollMonth == 'Dec') {
                var month = 12;
            }
            var noOfDays = daysInMonth(month, vm.scrollYear);
            noOfDays++;
            var weekday = new Array(7);
            weekday[0] = "Sunday";
            weekday[1] = "Monday";
            weekday[2] = "Tuesday";
            weekday[3] = "Wednesday";
            weekday[4] = "Thursday";
            weekday[5] = "Friday";
            weekday[6] = "Saturday";
            var final = [];


            var saturdayFlag = false;
            var sundayFlag = false;

            for (var i = 1; i < noOfDays; i++) {
                var d = new Date(vm.scrollMonth + i + ", " + vm.scrollYear);

            if(d.getDay()==6){//if saturday

                   var sc = {
                        scrollDateObj: d,
                       scrollDate: formatDate(d),
                       remarks: saturdayFlag ? "HOLIDAY" :"",
                       scrollDay: weekday[d.getDay()],
                       holiday: saturdayFlag ? "Y" : "N"
                   };
                    saturdayFlag = !saturdayFlag;
            }else if(d.getDay()==5  && saturdayFlag){//if saturday && Sunday are holidays

                   var sc = {
                   scrollDateObj: d,
                       scrollDate: formatDate(d),
                       remarks: "",
                       scrollDay: weekday[d.getDay()],
                        holiday: "N"
                   };
            }
           else if(d.getDay()==0){//if Sunday

                   var sc = {
                   scrollDateObj: d,
                       scrollDate: formatDate(d),
                       remarks: "HOLIDAY",
                       scrollDay: weekday[d.getDay()],
                       holiday: "Y"
                   };

            }  else {
                 var sc = {
                 scrollDateObj: d,
                        scrollDate: formatDate(d),
                        remarks: "",
                        scrollDay: weekday[d.getDay()],
                        holiday:"N"
                    };

            }
                   vm.dataList.push(sc);
                            vm.totalCount = i;
            }
            console.log(vm.dataList);
            vm.loader = false;
        }
         function getVerifyDetails() {
                    vm.loader = true;
                    var requestData = {
                        'scrollYear': vm.scrollYear,
                        'scrollMonth': vm.scrollMonth
                    }
                    if (vm.scrollMonth == 'Jan') {
                        var month = 1;
                    } else if (vm.scrollMonth == 'Feb') {
                        var month = 2;
                    } else if (vm.scrollMonth == 'Mar') {
                        var month = 3;
                    } else if (vm.scrollMonth == 'Apr') {
                        var month = 4;
                    } else if (vm.scrollMonth == 'May') {
                        var month = 5;
                    } else if (vm.scrollMonth == 'June') {
                        var month = 6;
                    } else if (vm.scrollMonth == 'July') {
                        var month = 7;
                    } else if (vm.scrollMonth == 'Aug') {
                        var month = 8;
                    } else if (vm.scrollMonth == 'Sep') {
                        var month = 9;
                    } else if (vm.scrollMonth == 'Oct') {
                        var month = 10;
                    } else if (vm.scrollMonth == 'Nov') {
                        var month = 11;
                    } else if (vm.scrollMonth == 'Dec') {
                        var month = 12;
                    }
                    var noOfDays = daysInMonth(month, vm.scrollYear);
                    noOfDays++;
                    var weekday = new Array(7);
                    weekday[0] = "Sunday";
                    weekday[1] = "Monday";
                    weekday[2] = "Tuesday";
                    weekday[3] = "Wednesday";
                    weekday[4] = "Thursday";
                    weekday[5] = "Friday";
                    weekday[6] = "Saturday";
                    var final = [];


                    var saturdayFlag = false;
                    var sundayFlag = false;

                    for (var i = 1; i < noOfDays; i++) {
                        var d = new Date(vm.scrollMonth + i + ", " + vm.scrollYear);

                    if(d.getDay()==6){//if saturday

                           var sc = {
                               scrollDateObj: d,
                               scrollDate: formatDate(d),
                               remarks: vm.cbsMaintenanceOfCalendarEntries.remarks[i],
                               scrollDay: weekday[d.getDay()],
                               holiday: (vm.cbsMaintenanceOfCalendarEntries.option[i] == " ") ? "N":"Y"

                           };
                            if(vm.model[0].prevOprType == 'M'){
                            if(vm.cbsMaintenanceOfCalendarEntries.remarks[i] != vm.cbsMaintenanceOfCalendarEntries.remarksNew[i]){
                               sc.remarksNew = vm.cbsMaintenanceOfCalendarEntries.remarksNew[i];
                            }
                            else{
                            sc.remarksNew="";
                            }
                            if(vm.cbsMaintenanceOfCalendarEntries.optionNew[i] != vm.cbsMaintenanceOfCalendarEntries.option[i]){
                              sc.holidayNew = (vm.cbsMaintenanceOfCalendarEntries.optionNew[i] == " ") ? "N":"Y"
                            }
                            else{
                            sc.holidayNew="";
                            }

//                              sc.holidayNew = (vm.cbsMaintenanceOfCalendarEntries.optionNew[i] == " ") ? "N":"Y"
                           }
//                            vm.cbsMaintenanceOfCalendarEntries.option[i] = !vm.cbsMaintenanceOfCalendarEntries.option[i];
                    }else if(d.getDay()==5  && vm.cbsMaintenanceOfCalendarEntries.option[i].replace(/\s+/g,'')==' '){//if saturday && Sunday are holidays

                           var sc = {
                           scrollDateObj: d,
                               scrollDate: formatDate(d),
                               remarks:vm.cbsMaintenanceOfCalendarEntries.remarks[i],
                               scrollDay: weekday[d.getDay()],
                                holiday:(vm.cbsMaintenanceOfCalendarEntries.option[i] == " ") ? "N":"Y"
                           };
                              if(vm.model[0].prevOprType == 'M'){
                                  if(vm.cbsMaintenanceOfCalendarEntries.remarks[i] != vm.cbsMaintenanceOfCalendarEntries.remarksNew[i]){
                                                             sc.remarksNew = vm.cbsMaintenanceOfCalendarEntries.remarksNew[i];
                                                          }
                                                          else{
                                                          sc.remarksNew="";
                                                          }
                                                          if(vm.cbsMaintenanceOfCalendarEntries.optionNew[i] != vm.cbsMaintenanceOfCalendarEntries.option[i]){
                                                            sc.holidayNew = (vm.cbsMaintenanceOfCalendarEntries.optionNew[i] == " ") ? "N":"Y"
                                                          }
                                                          else{
                                                          sc.holidayNew="";
                                                          }
//                          sc.remarksNew = vm.cbsMaintenanceOfCalendarEntries.remarksNew[i];
//                          sc.holidayNew = (vm.cbsMaintenanceOfCalendarEntries.optionNew[i] == " ") ? "N":"Y"
                     }
                    }
                   else if(d.getDay()==0&&vm.cbsMaintenanceOfCalendarEntries.option[i].replace(/\s+/g, '')=='Y'){//if Sunday

                           var sc = {
                           scrollDateObj: d,
                               scrollDate: formatDate(d),
                               remarks: vm.cbsMaintenanceOfCalendarEntries.remarks[i],
                               scrollDay: weekday[d.getDay()],
                               holiday:(vm.cbsMaintenanceOfCalendarEntries.option[i] == " ") ? "N":"Y"
                           };
                              if(vm.model[0].prevOprType == 'M'){
                                  if(vm.cbsMaintenanceOfCalendarEntries.remarks[i] != vm.cbsMaintenanceOfCalendarEntries.remarksNew[i]){
                                                             sc.remarksNew = vm.cbsMaintenanceOfCalendarEntries.remarksNew[i];
                                                          }
                                                          else{
                                                          sc.remarksNew="";
                                                          }
                                                          if(vm.cbsMaintenanceOfCalendarEntries.optionNew[i] != vm.cbsMaintenanceOfCalendarEntries.option[i]){
                                                            sc.holidayNew = (vm.cbsMaintenanceOfCalendarEntries.optionNew[i] == " ") ? "N":"Y"
                                                          }
                                                          else{
                                                          sc.holidayNew="";
                                                          }
//                               sc.remarksNew = vm.cbsMaintenanceOfCalendarEntries.remarksNew[i];
//                               sc.holidayNew = (vm.cbsMaintenanceOfCalendarEntries.optionNew[i] == " ") ? "N":"Y"
                        }

                    }  else {
                         var sc = {
                         scrollDateObj: d,
                                scrollDate: formatDate(d),
                                remarks:vm.cbsMaintenanceOfCalendarEntries.remarks[i],
                                scrollDay: weekday[d.getDay()],
                                holiday:(vm.cbsMaintenanceOfCalendarEntries.option[i] == " ") ? "N":"Y"
                            };
                     if(vm.model[0].prevOprType == 'M'){
                         if(vm.cbsMaintenanceOfCalendarEntries.remarks[i] != vm.cbsMaintenanceOfCalendarEntries.remarksNew[i]){
                                                    sc.remarksNew = vm.cbsMaintenanceOfCalendarEntries.remarksNew[i];
                                                 }
                                                 else{
                                                 sc.remarksNew="";
                                                 }
                                                 if(vm.cbsMaintenanceOfCalendarEntries.optionNew[i] != vm.cbsMaintenanceOfCalendarEntries.option[i]){
                                                   sc.holidayNew = (vm.cbsMaintenanceOfCalendarEntries.optionNew[i] == " ") ? "N":"Y"
                                                 }
                                                 else{
                                                 sc.holidayNew="";
                                                 }
//                                 sc.remarksNew = vm.cbsMaintenanceOfCalendarEntries.remarksNew[i];
//                                 sc.holidayNew = (vm.cbsMaintenanceOfCalendarEntries.optionNew[i] == " ") ? "N":"Y"
                             }
                    }
                           vm.dataList.push(sc);
                                    vm.totalCount = i;
                    }
                    console.log(vm.dataList);
                    vm.loader = false;
                }
        function getYears() {
            var year = new Date().getFullYear();
            var range = [];
            range.push(year - 1);
            range.push(year  );
            for (var i = 1; i < 10; i++) {
                range.push(year + i);
            }
            vm.years = range;
        }

        function list() {
            cbsMaintenanceOfCalendarEntriesService.getlist().then(
                function(response) {
                    vm.recordsList = response.data;
                }, handleErrorResponse);
        }

        function listUnverifiedRecords() {
            cbsMaintenanceOfCalendarEntriesService.getunverifiedrecords().then(
                function(response) {
                    vm.recordsList = response.data;
                }, handleErrorResponse);
        }

        function listVerifiedRecords() {
            cbsMaintenanceOfCalendarEntriesService.getverifiedrecords().then(
                function(response) {
                    vm.recordsList = response.data;
                }, handleErrorResponse);
        }

        function getRecord(id) {
            cbsMaintenanceOfCalendarEntriesService
                .getrecord(id)
                .then(
                    function(response) {
                        vm.cbsMaintenanceOfCalendarEntries = response.data;
                        if (response.data.monthYear.length == '5') {
                            vm.cbsMaintenanceOfCalendarEntries.month = '0' +
                                response.data.monthYear.slice(0,
                                    1);
                            vm.cbsMaintenanceOfCalendarEntries.year = response.data.monthYear
                                .slice(1, 5);
                        } else {
                            vm.cbsMaintenanceOfCalendarEntries.month = response.data.monthYear
                                .slice(0, 2);
                            vm.cbsMaintenanceOfCalendarEntries.year = response.data.monthYear
                                .slice(2, 6);
                        }
                    }, handleErrorResponse);
        }

        // Add data to database
        function add() {
        vm.loader=true;
            var requestData = {
                "calendarType": vm.cbsMaintenanceOfCalendarEntries.calendarType,
                "calendarCode": vm.cbsMaintenanceOfCalendarEntries.calendarCode,
                "month": monthChange(vm.cbsMaintenanceOfCalendarEntries.month),
                "year": vm.cbsMaintenanceOfCalendarEntries.year,
                "holidayString": "",
                "existingFlag": "Y"
            };
            cbsMaintenanceOfCalendarEntriesService.addSubmit(requestData).then(function(response) {
                vm.loader = false;
                vm.model = response.data.message;
                vm.scrollMonth=vm.cbsMaintenanceOfCalendarEntries.month;
                vm.scrollYear=vm.cbsMaintenanceOfCalendarEntries.year;
                getAddDetails();
                document.getElementById("first_div").style.display = "none";
                document.getElementById("second_div").style.display = "block";
            }, function(error) {
                vm.loader = false;
                handleErrorResponse(error);
                location.href = '#!/cbs_maintenance_of_calendar_entries/action/inquiry';
            })

            var i, j, k = 0,
                l;

            vm.cbsMaintenanceOfCalendarEntries.dayOrder = [];
            vm.cbsMaintenanceOfCalendarEntries.test = [];
            vm.cbsMaintenanceOfCalendarEntries.monthYear = vm.cbsMaintenanceOfCalendarEntries.month +
                vm.cbsMaintenanceOfCalendarEntries.year;
            vm.cbsMaintenanceOfCalendarEntries.numberOfDays = new Date(vm.cbsMaintenanceOfCalendarEntries.year, vm.cbsMaintenanceOfCalendarEntries.month, 0).getDate();
            var day = new Date(vm.cbsMaintenanceOfCalendarEntries.month + "-01-" + vm.cbsMaintenanceOfCalendarEntries.year);
            vm.cbsMaintenanceOfCalendarEntries.startingIndexDay = day.getDay();
            for (i = 0, l = 1, j = vm.cbsMaintenanceOfCalendarEntries.startingIndexDay; i <= 42; i++) {
                if (j <= i) {
                    k = 1;
                } else {
                    vm.cbsMaintenanceOfCalendarEntries.dayOrder.push('x');

                }
                if (k == 1) {
                    if (l <= vm.cbsMaintenanceOfCalendarEntries.numberOfDays) {
                        vm.cbsMaintenanceOfCalendarEntries.dayOrder.push(l);
                        l++;
                    } else {
                        vm.cbsMaintenanceOfCalendarEntries.dayOrder.push('x');
                    }
                }

            }


        }

        function modifySelect(flg, monthYYYY) {
            if (flg == 'get') {
                var requestData = {
                    "calendarType": vm.cbsMaintenanceOfCalendarEntries.calendarType,
                    "calendarCode": vm.cbsMaintenanceOfCalendarEntries.calendarCode,
                    "monthYear": ""
                };
            } else if (flg == 'all') {
                var requestData = {
                    "calendarType": vm.cbsMaintenanceOfCalendarEntries.calendarType,
                    "calendarCode": vm.cbsMaintenanceOfCalendarEntries.calendarCode,
                    "monthYear": monthYYYY
                };
            }

            cbsMaintenanceOfCalendarEntriesService.modifySelect(requestData).then(function(response) {
                vm.loader = false;
                vm.model = response.data.result;

                if (flg == 'get') {
                    angular.element('#monthYearDetailModal').modal('toggle');
                } else if (flg == 'all') {
                    vm.monthYear = vm.model[0].monthYear;
                    vm.scrollMonth = vm.monthYear[0] + vm.monthYear[1];
                    vm.scrollYear = vm.monthYear[2] + vm.monthYear[3] + vm.monthYear[4] + vm.monthYear[5];
                    vm.numberOfDays = new Date(vm.scrollYear, vm.scrollMonth, 0).getDate();
                    var day = new Date(vm.scrollMonth + "-01-" + vm.scrollYear);
                    vm.scrollMonth=monthChange(vm.scrollMonth);
                    vm.startingIndexDay = day.getDay();
                    var i, j, k = 0,
                        l;

                    vm.cbsMaintenanceOfCalendarEntries.dayOrder = [];

                    for (i = 0, l = 1, j = vm.startingIndexDay; i <= 42; i++) {

                        if (j <= i) {
                            k = 1;
                        } else {
                            vm.cbsMaintenanceOfCalendarEntries.dayOrder.push('x');

                        }
                        if (k == 1) {
                            if (l <= vm.numberOfDays) {
                                vm.cbsMaintenanceOfCalendarEntries.dayOrder.push(l);
                                l++;
                            } else {
                                vm.cbsMaintenanceOfCalendarEntries.dayOrder.push('x');
                            }
                        }
                    }
                                        vm.cbsMaintenanceOfCalendarEntries.option = [];
                                        vm.cbsMaintenanceOfCalendarEntries.remarks = [];
                                        vm.holidayString = vm.model[0].holidayString;
                                        vm.remarks = vm.model[0].remarks;
                                       // vm.cbsMaintenanceOfCalendarEntries.option[0]='';
                                       // vm.cbsMaintenanceOfCalendarEntries.remarks[0]='';

                                        for (i = 0, j = 1; i < vm.holidayString.length; i++, j++) {
                                        if(vm.holidayString[i]){
                                            vm.cbsMaintenanceOfCalendarEntries.option[j] = vm.holidayString[i];}
                                        }
                                     vm.remarksArray = vm.remarks.split('#');
                                      for (i = 0, j = 1; i < vm.remarksArray.length; i++, j++) {
                                    if(vm.remarksArray[i]){
                                     vm.cbsMaintenanceOfCalendarEntries.remarks[j] = vm.remarksArray[i];}
                                    }
                                    getVerifyDetails();
                                    }

            }, function(error) {
                vm.loader = false;
                handleErrorResponse(error);
                location.href = '#!/cbs_maintenance_of_calendar_entries/action/modify';
            })
        }

        function deleteSelect(flg) {
            if (flg == 'sel') {
                var requestData = {
                    "calendarType": vm.cbsMaintenanceOfCalendarEntries.calendarType,
                    "calendarCode": vm.cbsMaintenanceOfCalendarEntries.calendarCode,
                    "monthYear": ""
                };
            } else if (flg == 'all') {
                var requestData = {
                    "calendarType": vm.cbsMaintenanceOfCalendarEntries.calendarType,
                    "calendarCode": vm.cbsMaintenanceOfCalendarEntries.calendarCode,
                    "monthYear": vm.cbsMaintenanceOfCalendarEntries.monthYear
                };
            }

            cbsMaintenanceOfCalendarEntriesService.verifySelect(requestData).then(function(response) {
                vm.loader = false;
                vm.model = response.data.result;

                if (flg == 'sel') {
                    angular.element('#monthYearDetailModal').modal('toggle');
                } else if (flg == 'all') {
                    vm.monthYear = vm.model[0].monthYear;
                    vm.month = vm.monthYear[0] + vm.monthYear[1];
                    vm.year = vm.monthYear[2] + vm.monthYear[3] + vm.monthYear[4] + vm.monthYear[5];
                    vm.numberOfDays = new Date(vm.year, vm.month, 0).getDate();
                    var day = new Date(vm.month + "-01-" + vm.year);
                    vm.startingIndexDay = day.getDay();
                    var i, j, k = 0,
                        l;

                    vm.cbsMaintenanceOfCalendarEntries.dayOrder = [];

                    for (i = 0, l = 1, j = vm.startingIndexDay; i <= 42; i++) {
                        if (j <= i) {
                            k = 1;
                        } else {
                            vm.cbsMaintenanceOfCalendarEntries.dayOrder.push('x');

                        }
                        if (k == 1) {
                            if (l <= vm.numberOfDays) {
                                vm.cbsMaintenanceOfCalendarEntries.dayOrder.push(l);
                                l++;
                            } else {
                                vm.cbsMaintenanceOfCalendarEntries.dayOrder.push('x');
                            }
                        }

                    }
                    vm.cbsMaintenanceOfCalendarEntries.option = [];
                    vm.holidayString = vm.model[0].holidayString;
                    vm.cbsMaintenanceOfCalendarEntries.option[0] = '';

                    for (i = 0, j = 1; i <= vm.holidayString.length; i++, j++) {
                     if(vm.holidayString[i])
                        vm.cbsMaintenanceOfCalendarEntries.option[j] = vm.holidayString[i];
                    }

                }



            }, function(error) {
                angular.element('#monthYearDetailModal').modal('toggle');
                vm.loader = false;
                handleErrorResponse(error);
                $state.go("cbs_maintenance_of_calendar_entries.delete");
            })
        }

        function inquirySelect(flg, monthYYYY) {
            vm.loader = true;
            if (flg == 'get') {
                var requestData = {
                    "calendarType": vm.cbsMaintenanceOfCalendarEntries.calendarType,
                    "calendarCode": vm.cbsMaintenanceOfCalendarEntries.calendarCode,
                    "monthYear": ""
                };

            } else if (flg == 'all') {

                var requestData = {
                    "calendarType": vm.cbsMaintenanceOfCalendarEntries.calendarType,
                    "calendarCode": vm.cbsMaintenanceOfCalendarEntries.calendarCode,
                    "monthYear": monthYYYY
                };

            }

            cbsMaintenanceOfCalendarEntriesService.inquirySelect(requestData).then(function(response) {
                vm.loader = false;
                vm.model = response.data.result;
 if (flg == 'get') {
                    angular.element('#monthYearDetailModal').modal('toggle');
                } else if (flg == 'all') {

                vm.model[0].monthYear=vm.model[0].model.C002.oldValue;

                    vm.monthYear = vm.model[0].monthYear;
                    vm.month = vm.monthYear[0] + vm.monthYear[1];
                    vm.year = vm.monthYear[2] + vm.monthYear[3] + vm.monthYear[4] + vm.monthYear[5];
                    vm.numberOfDays = new Date(vm.year, vm.month, 0).getDate();
                    var day = new Date(vm.month + "-01-" + vm.year);
                    vm.startingIndexDay = day.getDay();
                    var i, j, k = 0,
                        l;

                    vm.cbsMaintenanceOfCalendarEntries.dayOrder = [];

                    for (i = 0, l = 1, j = vm.startingIndexDay; i <= 42; i++) {

                        if (j <= i) {
                            k = 1;
                        } else {
                            vm.cbsMaintenanceOfCalendarEntries.dayOrder.push('x');

                        }
                        if (k == 1) {
                            if (l <= vm.numberOfDays) {
                                vm.cbsMaintenanceOfCalendarEntries.dayOrder.push(l);
                                l++;
                            } else {
                                vm.cbsMaintenanceOfCalendarEntries.dayOrder.push('x');
                            }
                        }

                    }
                    vm.cbsMaintenanceOfCalendarEntries.option = [];
                    vm.cbsMaintenanceOfCalendarEntries.remarks = [];
                    vm.holidayString = vm.model[0].model.C005.oldValue;
                    vm.cbsMaintenanceOfCalendarEntries.option[0] = '';
                    vm.cbsMaintenanceOfCalendarEntries.remarks[0] = '';
                    vm.remarks = vm.model[0].model.C009.oldValue;

                    for (i = 0, j = 1; i < vm.holidayString.length; i++, j++) {
                    if(vm.holidayString[i]){
                        vm.cbsMaintenanceOfCalendarEntries.option[j] = vm.holidayString[i];}
                    }
                    vm.remarksArray = vm.remarks.split('#');
                    for (i = 0, j = 1; i < vm.remarksArray.length; i++, j++) {
                    if(vm.remarksArray[i]){
                    vm.cbsMaintenanceOfCalendarEntries.remarks[j] = vm.remarksArray[i];}
                    else{
                                                         vm.cbsMaintenanceOfCalendarEntries.remarks[j] = "";
                                                         }
                    }

                if(vm.model[0].prevOprType == 'M')
                {
                vm.cbsMaintenanceOfCalendarEntries.optionNew=[];
                vm.cbsMaintenanceOfCalendarEntries.remarksNew=[];
                 vm.holidayStringNew = vm.model[0].model.C005.newValue;
                 vm.cbsMaintenanceOfCalendarEntries.optionNew[0] = '';
                 vm.cbsMaintenanceOfCalendarEntries.remarksNew[0] = '';
                 vm.remarksNew = vm.model[0].model.C009.newValue;

                 vm.remarksNewArray = vm.remarksNew.split('#');
                 for (i = 0, j = 1; i < vm.remarksNewArray.length; i++, j++) {
                 if(vm.remarksNewArray[i] != vm.cbsMaintenanceOfCalendarEntries.remarks[j]){
                                 if(vm.remarksNewArray[i]){
                                 vm.cbsMaintenanceOfCalendarEntries.remarksNew[j] = vm.remarksNewArray[i];
                                 }
                  else{
                                 vm.cbsMaintenanceOfCalendarEntries.remarksNew[j] = "";
                     }
                 }
                 else{
                      vm.cbsMaintenanceOfCalendarEntries.remarksNew[j] = "";
                 }

                 }
                for (i = 0, j = 1; i < vm.holidayStringNew.length; i++, j++) {
                 if(vm.holidayStringNew[i] != vm.cbsMaintenanceOfCalendarEntries.option[j]){
                                                  if(vm.holidayStringNew[i]){
                  vm.cbsMaintenanceOfCalendarEntries.optionNew[j] = (vm.holidayStringNew[i] == " ") ? "N":"Y"
                  }else{
                      vm.cbsMaintenanceOfCalendarEntries.optionNew[j] = "";
                  }
                 }else if((vm.holidayStringNew[i] == vm.cbsMaintenanceOfCalendarEntries.option[j])&&(vm.remarksNewArray[i] != vm.cbsMaintenanceOfCalendarEntries.remarks[j])){
                vm.cbsMaintenanceOfCalendarEntries.optionNew[j]=(vm.holidayStringNew[i] == " ") ? "N":"Y"
                 }
                 else{
                      vm.cbsMaintenanceOfCalendarEntries.optionNew[j] = "";
                  }
                 }
                }
                }
            }, function(error) {
                vm.loader = false;
                handleErrorResponse(error);
                $state.go("cbs_maintenance_of_calendar_entries.list");
            })
        }

        function Selectclear(flg) {
            if (flg == 'inquiry') {
                location.href = '#!/cbs_maintenance_of_calendar_entries/action/inquiry';
            } else if (flg == 'add') {
                location.href = '#!/cbs_maintenance_of_calendar_entries/action/inquiry';
            } else if (flg == 'verify') {
                location.href = '#!/cbs_maintenance_of_calendar_entries/action/verify';
            } else if (flg == 'delete') {
                location.href = '#!/cbs_maintenance_of_calendar_entries/action/delete';
            } else if (flg == 'modify') {
                location.href = '#!/cbs_maintenance_of_calendar_entries/action/modify';
            }

        }

        function verifySelect(flg, monthYYYY) {
            vm.loader = true;

            if (flg == 'get') {
                var requestData = {
                    "calendarType": vm.cbsMaintenanceOfCalendarEntries.calendarType,
                    "calendarCode": vm.cbsMaintenanceOfCalendarEntries.calendarCode,
                    "monthYear": ""
                };

            } else if (flg == 'all') {

                var requestData = {
                    "calendarType": vm.cbsMaintenanceOfCalendarEntries.calendarType,
                    "calendarCode": vm.cbsMaintenanceOfCalendarEntries.calendarCode,
                    "monthYear": monthYYYY
                };

            }

            cbsMaintenanceOfCalendarEntriesService.verifySelect(requestData).then(function(response) {
                vm.loader = false;
                vm.model = response.data.result;
                if (flg == 'get') {
                    angular.element('#monthYearDetailModal').modal('toggle');
                } else if (flg == 'all') {
                    vm.model[0].monthYear=vm.model[0].model.C002.oldValue;
                    vm.monthYear = vm.model[0].monthYear;
                    vm.scrollMonth = vm.monthYear[0] + vm.monthYear[1];
                    vm.scrollYear = vm.monthYear[2] + vm.monthYear[3] + vm.monthYear[4] + vm.monthYear[5];
                    vm.numberOfDays = new Date(vm.scrollYear, vm.scrollMonth, 0).getDate();
                    var day = new Date(vm.scrollMonth + "-01-" + vm.scrollYear);
                    vm.scrollMonth=monthChange(vm.scrollMonth);
                    vm.startingIndexDay = day.getDay();
                    var i, j, k = 0,
                        l;

                    vm.cbsMaintenanceOfCalendarEntries.dayOrder = [];

                    for (i = 0, l = 1, j = vm.startingIndexDay; i <= 42; i++) {

                        if (j <= i) {
                            k = 1;
                        } else {
                            vm.cbsMaintenanceOfCalendarEntries.dayOrder.push('x');

                        }
                        if (k == 1) {
                            if (l <= vm.numberOfDays) {
                                vm.cbsMaintenanceOfCalendarEntries.dayOrder.push(l);
                                l++;
                            } else {
                                vm.cbsMaintenanceOfCalendarEntries.dayOrder.push('x');
                            }
                        }

                    }
                    vm.cbsMaintenanceOfCalendarEntries.option = [];
                    vm.cbsMaintenanceOfCalendarEntries.remarks = [];
                    vm.holidayString = vm.model[0].model.C005.oldValue;
                    vm.remarks = vm.model[0].model.C009.oldValue;
                   // vm.cbsMaintenanceOfCalendarEntries.option[0]='';
                   // vm.cbsMaintenanceOfCalendarEntries.remarks[0]='';

                    for (i = 0, j = 1; i < vm.holidayString.length; i++, j++) {
                    if(vm.holidayString[i]){
                        vm.cbsMaintenanceOfCalendarEntries.option[j] = vm.holidayString[i];}
                    }
                 vm.remarksArray = vm.remarks.split('#');
                  for (i = 0, j = 1; i < vm.remarksArray.length; i++, j++) {
                                     if(vm.remarksArray[i]){
                                         vm.cbsMaintenanceOfCalendarEntries.remarks[j] = vm.remarksArray[i];}
                                     }
                if(vm.model[0].prevOprType == 'M')
                {
                   vm.cbsMaintenanceOfCalendarEntries.optionNew=[];
                   vm.cbsMaintenanceOfCalendarEntries.remarksNew=[];
                 vm.holidayStringNew = vm.model[0].model.C005.newValue;
                 vm.remarksNew = vm.model[0].model.C009.newValue;
                // vm.cbsMaintenanceOfCalendarEntries.optionNew[0]='';
                 for (i = 0, j = 1; i < vm.holidayStringNew.length; i++, j++) {
                     vm.cbsMaintenanceOfCalendarEntries.optionNew[j] = vm.holidayStringNew[i];
                 }
                 vm.remarksNewArray = vm.remarksNew.split('#');
                                   for (i = 0, j = 1; i < vm.remarksNewArray.length; i++, j++) {
                                                      if(vm.remarksNewArray[i]){
                                                          vm.cbsMaintenanceOfCalendarEntries.remarksNew[j] = vm.remarksNewArray[i];}
                                                      }
                }
                getVerifyDetails();
                }
            }, function(error) {
                vm.loader = false;
                handleErrorResponse(error);
                angular.element('#monthYearDetailModal').modal('hide');
                $timeout(function(){
                    $state.go("cbs_maintenance_of_calendar_entries.list");
                },700)
            })
        }

        function verifySubmit() {
//         Swal.fire({
//                                                                        title: "Are you sure?",
//                                                                        text: "Do you want to verify this record ? ",
//                                                                        type: "warning",
//                                                                        showCancelButton: true,
//                                                                        cancelButtonClass: "btn-cancel",
//                                                                        confirmButtonClass: "btn-primary",
//                                                                        confirmButtonText: "Yes!",
//                                                                        closeOnConfirm: false,
//                                                                        showLoaderOnConfirm: true
//                                                                    },
//                                                                    function() {



 Swal.fire({
                                title: "Are you sure?",
                                text: "Do you want to verify this record ? ",
                                icon: "warning", // use "icon", not "type"
                                showCancelButton: true,
                                confirmButtonText: "Yes",
                                cancelButtonText: "Cancel",
                                customClass: {
                                  confirmButton: "btn-primary",
                                  cancelButton: "btn-cancel"
                                },
                                buttonsStyling: false
                              }).then((result) => {

                                     if (result.isConfirmed) {

            vm.loader = true;
            var requestData = {
                "calendarType": vm.cbsMaintenanceOfCalendarEntries.calendarType,
                "calendarCode": vm.cbsMaintenanceOfCalendarEntries.calendarCode,
                "monthYear": vm.monthYear,
            };
            cbsMaintenanceOfCalendarEntriesService.verifySubmit(requestData).then(function(response) {
                vm.loader = false;
                vm.model = response.data.result;
                Swal.fire("Verify!", "Calendar Entries Verified successfully", "success");

                location.href = '#!/cbs_maintenance_of_calendar_entries/action/inquiry';
           } , function(error) {
                vm.loader = false;
                handleErrorResponse(error);
                location.href = '#!/cbs_maintenance_of_calendar_entries/action/inquiry';
            }) }});
        }
 function deleteSubmit(){

                                       vm.loader = true;
                       var requestData = {
                                    "calendarType": vm.cbsMaintenanceOfCalendarEntries.calendarType,
                                    "calendarCode": vm.cbsMaintenanceOfCalendarEntries.calendarCode,
                                    "monthYear": vm.monthYear,
                                    "rejectionRemarks" :vm.remarks
                                };
                       cbsMaintenanceOfCalendarEntriesService.deleteSelect(requestData).then(function(response) {
                             vm.loader = false;
                             vm.model = response.data.result;
                             Swal.fire("Deleted!", "Calender Entries Deleted successfully", "success");
                             location.href = '#!/cbs_maintenance_of_calendar_entries/action/inquiry';
                                           const backdrop = document.querySelector('.modal-backdrop');
                                                                                                   if (backdrop) {
                                                                                                     backdrop.parentNode.removeChild(backdrop);
                                                                                                     document.body.classList.remove('modal-open');
                                                                                                   }
                            }, function(error){
                                vm.loader = false;
                                                const backdrop = document.querySelector('.modal-backdrop');
                                                                                                        if (backdrop) {
                                                                                                          backdrop.parentNode.removeChild(backdrop);
                                                                                                          document.body.classList.remove('modal-open');
                                                                                                        }
                                handleErrorResponse(error);
                             location.href = '#!/cbs_maintenance_of_calendar_entries/action/inquiry';
                            }
                        );
                }
vm.validateInput=validateInput;
 function validateInput(event,id){

    const textBox = document.getElementById(id);

      var charCode = event.which || event.keyCode;
        var charTyped = String.fromCharCode(charCode);

if(textBox)
{
 const cursorPosition = textBox.selectionStart;
    if(cursorPosition == 0 && charTyped === ' ')
    {
            event.preventDefault();
            return;

    }
    }
      var currentValue = event.target.value + charTyped;
        var pattern = /^[a-zA-Z\s]*'?([a-zA-Z\s]*'?)*$/;

//        // Check if more than one apostrophe is present
//        if ((currentValue.match(/'/g) || []').length > 1) {
//            event.preventDefault();
//            return;
//        }

        if (!pattern.test(currentValue)) {
            event.preventDefault();
        }
    }





              function getRemarksList() {
                                                        vm.referenceId = '058';
                                                        var requestData = { 'referenceId': vm.referenceId };

                                                        cbsMaintenanceOfCalendarEntriesService.getRemarksList(requestData).then(function(response) {
                                                            vm.remarksList = response.data.result || []; // Ensure it's an array
                                                        }, function(error) {
                                                            handleErrorResponse(error);
                                                        });
                                                  }













vm.rejectReason=rejectReason;
function rejectReason() {
vm.remarks='';
  angular.element('#permissionMappingDelete').modal('toggle');
  }
        function modifySubmit() {
//         Swal.fire({
//                            title: "Are you sure?",
//                            text: "Do you want to Modify this record? ",
//                            type: "warning",
//                            showCancelButton: true,
//                            cancelButtonClass: "btn-cancel",
//                            confirmButtonClass: "btn-primary",
//                            confirmButtonText: "Yes",
//                            closeOnConfirm: false,
//                            showLoaderOnConfirm: true
//                        },
//                        function() {


                 Swal.fire({
                                     title: "Are you sure?",
                                     text: "Do you want to Really Modify this record?",
                                     icon: "warning", // use "icon", not "type"
                                     showCancelButton: true,
                                     confirmButtonText: "Yes",
                                     cancelButtonText: "Cancel",
                                     customClass: {
                                       confirmButton: "btn-primary",
                                       cancelButton: "btn-cancel"
                                     },
                                     buttonsStyling: false
                                   }).then((result) => {

                                          if (result.isConfirmed) {
            vm.loader = true;
            vm.dataFinalList = [];
                                            angular.forEach(vm.dataList, function(value, item) {
                                                var tempdata = {};
                                                tempdata.scrollDate = $filter('date')(value.scrollDateObj, 'dd-MMM-yyyy');
                                                tempdata.scrollDay = value.scrollDay;
                                                tempdata.holiday = value.holiday;
                                                tempdata.remarks = value.remarks?value.remarks : "";
                                                vm.dataFinalList.push(tempdata);
                                            });
            var i, holidayStringOp = "";
            for (i = 1; i < vm.cbsMaintenanceOfCalendarEntries.option.length-1; i++) {
                if ((vm.cbsMaintenanceOfCalendarEntries.option[i] ? vm.cbsMaintenanceOfCalendarEntries.option[i].toUpperCase() : vm.cbsMaintenanceOfCalendarEntries.option[i]) == 'Y') {
                    holidayStringOp += 'Y';
                } else {
                    holidayStringOp += ' ';
                }
            }
            var requestData = {
                "calendarType": vm.cbsMaintenanceOfCalendarEntries.calendarType,
                "calendarCode": vm.cbsMaintenanceOfCalendarEntries.calendarCode,
                "monthYear": vm.monthYear,
                "calenderEntriesResponseDto":vm.dataFinalList,
                "holidayString": holidayStringOp
            };
            cbsMaintenanceOfCalendarEntriesService.modifySubmit(requestData).then(function(response) {
                vm.loader = false;
                vm.model = response.data.result;
                Swal.fire("Modified!", "Calender Entries modified successfully", "success");
                location.href = '#!/cbs_maintenance_of_calendar_entries/action/inquiry';
            }, function(error) {
                vm.loader = false;
                handleErrorResponse(error);
                location.href = '#!/cbs_maintenance_of_calendar_entries/action/inquiry';
            })} });
        }
function monthChange(mon){
if(mon=='Jan'){
return vm.month='01';
}else if(mon=='01'){
return vm.month='Jan';
}
if(mon=='Feb'){
return vm.month='02';
}else if(mon=='02'){
 return vm.month='Feb';
 }
if(mon=='Mar'){
return vm.month='03';
}else if(mon=='03'){
 return vm.month='Mar';
 }
if(mon=='Apr'){
 return vm.month='04';
 }else if(mon=='04'){
  return vm.month='Apr';
  }
 if(mon=='May'){
 return vm.month='05';
  }else if(mon=='05'){
   return vm.month='May';
   }
  if(mon=='June'){
   return vm.month='06';
   }else if(mon=='06'){
    return vm.month='June';
    }
   if(mon=='July'){
    return vm.month='07';
    }else if(mon=='07'){
     return vm.month='July';
     }
    if(mon=='Aug'){
     return vm.month='08';
     }else if(mon=='08'){
      return vm.month='Aug';
      }
     if(mon=='Sep'){
      return vm.month='09';
      }else if(mon=='09'){
       return vm.month='Sep';
       }
      if(mon=='Oct'){
             return vm.month='10';
             }else if(mon=='10'){
              return vm.month='Oct';
              }
             if(mon=='Nov'){
                   return vm.month='11';
                    }else if(mon=='11'){
                     return vm.month='Nov';
                     }
                    if(mon=='Dec'){
                         return vm.month='12';
                           }else if(mon=='12'){
                            return vm.month='Dec';
                            }
}
function clearRemarks(index,holiday){
console.log("index-"+index);
console.log("index-"+index+"holiday-"+holiday);

   if (holiday !== 'Y') {
            vm.dataList[index].remarks = '';
        }
}
        function addSubmit() {
//         Swal.fire({
//                            title: "Are you sure?",
//                            text: "Do you want to Add this record? ",
//                            type: "warning",
//                            showCancelButton: true,
//                            cancelButtonClass: "btn-cancel",
//                            confirmButtonClass: "btn-primary",
//                            confirmButtonText: "Yes",
//                            closeOnConfirm: false,
//                            showLoaderOnConfirm: true
//                        },
//                        function() {


                   Swal.fire({
                                       title: "Are you sure?",
                                       text: "Do you want to Really Add this record?",
                                       icon: "warning", // use "icon", not "type"
                                       showCancelButton: true,
                                       confirmButtonText: "Yes",
                                       cancelButtonText: "Cancel",
                                       customClass: {
                                         confirmButton: "btn-primary",
                                         cancelButton: "btn-cancel"
                                       },
                                       buttonsStyling: false
                                     }).then((result) => {

                                            if (result.isConfirmed) {
                vm.loader = true;
                 vm.dataFinalList = [];
                                angular.forEach(vm.dataList, function(value, item) {
                                    var tempdata = {};
                                    tempdata.scrollDate = $filter('date')(value.scrollDateObj, 'dd-MMM-yyyy');
                                    tempdata.scrollDay = value.scrollDay;
                                    tempdata.holiday = value.holiday;
                                    tempdata.remarks = value.remarks.toUpperCase();
                                    vm.dataFinalList.push(tempdata);
                                });
                var op = "",
                    i, j;
                for (j = 1; j <= vm.cbsMaintenanceOfCalendarEntries.numberOfDays; j++) {

                    if (vm.cbsMaintenanceOfCalendarEntries.option[j]) {
                        op += 'Y';
                    } else {
                        op += " ";

                    }

                }
                var requestData = {
                    "calendarType": vm.cbsMaintenanceOfCalendarEntries.calendarType,
                    "calendarCode": vm.cbsMaintenanceOfCalendarEntries.calendarCode,
                    "month": monthChange(vm.cbsMaintenanceOfCalendarEntries.month),
                    "year": vm.cbsMaintenanceOfCalendarEntries.year,
                    "holidayString": op,
                    "calenderEntriesResponseDto":vm.dataFinalList,
                    "existingFlag": "N"
                };
                cbsMaintenanceOfCalendarEntriesService.addSubmit(requestData).then(function(response) {
                    vm.loader = false;
                    vm.model = response.data.result;
                    Swal.fire("Added!", "Data Added successfully", "success");
                    location.href = '#!/cbs_maintenance_of_calendar_entries/action/inquiry';
                }, function(error) {
                    vm.loader = false;
                    handleErrorResponse(error);
                    location.href = '#!/cbs_maintenance_of_calendar_entries/add';
                }) } });

        }

        function modify() {
//            Swal.fire({
//                    title: "Are you sure?",
//                    text: "Are you sure want to modify this Calendar Entries? ",
//                    type: "warning",
//                    showCancelButton: true,
//                    cancelButtonClass: "btn-cancel",
//                    confirmButtonClass: "btn-primary",
//                    confirmButtonText: "Yes!",
//                    closeOnConfirm: false,
//                    showLoaderOnConfirm: true
//                },
//                function() {


                     Swal.fire({
                                         title: "Are you sure?",
                                          text: "Are you sure want to modify this Calendar Entries? ",
                                         icon: "warning", // use "icon", not "type"
                                         showCancelButton: true,
                                         confirmButtonText: "Yes",
                                         cancelButtonText: "Cancel",
                                         customClass: {
                                           confirmButton: "btn-primary",
                                           cancelButton: "btn-cancel"
                                         },
                                         buttonsStyling: false
                                       }).then((result) => {

                                              if (result.isConfirmed) {
                    vm.cbsMaintenanceOfCalendarEntries.monthYear = vm.cbsMaintenanceOfCalendarEntries.month +
                        vm.cbsMaintenanceOfCalendarEntries.year;
                    cbsMaintenanceOfCalendarEntriesService
                        .modify(vm.cbsMaintenanceOfCalendarEntries)
                        .then(
                            function(response) {
                                Swal.fire("Modified!",
                                    response.data.message,
                                    "success");
                                location.href = '#!/cbs_maintenance_of_calendar_entries/action/modify';
                            })
               } }, handleErrorResponse);
        }

        function deleteMaintenanceOfCalendarEntries(id) {
//            Swal.fire({
//                    title: "Are you sure?",
//                    text: "Are you sure want to delete this Calendar Entries? ",
//                    type: "warning",
//                    showCancelButton: true,
//                    cancelButtonClass: "btn-cancel",
//                    confirmButtonClass: "btn-primary",
//                    confirmButtonText: "Yes!",
//                    closeOnConfirm: false,
//                    showLoaderOnConfirm: true
//                },
//                function() {




                Swal.fire({
                                    title: "Are you sure?",
                                    text: "Are you sure want to delete this Calendar Entries? ",
                                    icon: "warning", // use "icon", not "type"
                                    showCancelButton: true,
                                    confirmButtonText: "Yes",
                                    cancelButtonText: "Cancel",
                                    customClass: {
                                      confirmButton: "btn-primary",
                                      cancelButton: "btn-cancel"
                                    },
                                    buttonsStyling: false
                                  }).then((result) => {

                                         if (result.isConfirmed) {
                    cbsMaintenanceOfCalendarEntriesService
                        .deletemaintenanceOfCalendarEntries(
                            vm.cbsMaintenanceOfCalendarEntries)
                        .then(
                            function(response) {
                                Swal.fire("Deleted!",
                                    response.data.message,
                                    "success");
                                location.href = '#!/cbs_maintenance_of_calendar_entries/action/delete';
                            })
               } }, handleErrorResponse);
        }

        function verify() {
//            Swal.fire({
//                    title: "Are you sure?",
//                    text: "Are you sure want to verify this record? ",
//                    type: "warning",
//                    showCancelButton: true,
//                    cancelButtonClass: "btn-cancel",
//                    confirmButtonClass: "btn-primary",
//                    confirmButtonText: "Yes",
//                    closeOnConfirm: false,
//                    showLoaderOnConfirm: true
//                },
//                function() {


              Swal.fire({
                                 title: "Are you sure?",
                                 text: "Do you want to Really Verify this record?",
                                 icon: "warning", // use "icon", not "type"
                                 showCancelButton: true,
                                 confirmButtonText: "Yes",
                                 cancelButtonText: "Cancel",
                                 customClass: {
                                   confirmButton: "btn-primary",
                                   cancelButton: "btn-cancel"
                                 },
                                 buttonsStyling: false
                               }).then((result) => {

                                      if (result.isConfirmed) {
                    cbsMaintenanceOfCalendarEntriesService
                        .verify(vm.cbsMaintenanceOfCalendarEntries)
                        .then(
                            function(response) {
                                Swal.fire("Verified!",
                                    response.data.message,
                                    "success");
                                location.href = '#!/cbs_maintenance_of_calendar_entries/action/verify';
                            })
              }  }, handleErrorResponse);
        }

        // Handle error response
        function handleErrorResponse(response) {
            vm.loader = false;
            var msg = response.data.message ? response.data.message :
                "Server Error";
            Swal.fire("Error!", msg, "error");
        }

        // Cancel Confirmation
        function showCancelConfirmation(state) {
//            Swal.fire({
//                    title: "Confirm Cancel",
//                    text: "Do you really want to cancel?",
//                    type: "warning",
//                    showCancelButton: true,
//                    cancelButtonClass: "btn-cancel",
//                    confirmButtonClass: "btn-danger",
//                    confirmButtonText: "Yes",
//                    cancelButtonText: "No",
//                    closeOnConfirm: true
//                },
//                function() {


             Swal.fire({
                                title: "Confirm Cancel?",
                                text: "Do you Really Want To Cancel?",
                                icon: "warning", // use "icon", not "type"
                                showCancelButton: true,
                                confirmButtonText: "Yes",
                                cancelButtonText: "Cancel",
                                customClass: {
                                  confirmButton: "btn-primary",
                                  cancelButton: "btn-cancel"
                                },
                                buttonsStyling: false
                              }).then((result) => {

                                     if (result.isConfirmed) {
                    $state.go(state);

              }  });
        }
    }

})();