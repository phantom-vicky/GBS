(function() {

    'use strict';

    angular.module('gbmUltraApp').service(
        'cbsMaintenanceOfCalendarEntriesService',
        cbsMaintenanceOfCalendarEntriesService);

    cbsMaintenanceOfCalendarEntriesService.$inject = ['$http'];

    function cbsMaintenanceOfCalendarEntriesService($http) {

        var cbsMaintenanceOfCalendarEntriesService = this;

        cbsMaintenanceOfCalendarEntriesService.getrecord = getrecord;
        cbsMaintenanceOfCalendarEntriesService.getverifiedrecords = getverifiedrecords;
        cbsMaintenanceOfCalendarEntriesService.getunverifiedrecords = getunverifiedrecords;
        cbsMaintenanceOfCalendarEntriesService.getlist = getlist;
        cbsMaintenanceOfCalendarEntriesService.modify = modify;
        cbsMaintenanceOfCalendarEntriesService.verify = verify;
        cbsMaintenanceOfCalendarEntriesService.add = add;
        cbsMaintenanceOfCalendarEntriesService.deletemaintenanceOfCalendarEntries = deletemaintenanceOfCalendarEntries;
        cbsMaintenanceOfCalendarEntriesService.addSubmit = addSubmit;
        cbsMaintenanceOfCalendarEntriesService.modifySelect = modifySelect;
        cbsMaintenanceOfCalendarEntriesService.modifySubmit = modifySubmit;
        cbsMaintenanceOfCalendarEntriesService.verifySelect = verifySelect;
        cbsMaintenanceOfCalendarEntriesService.verifySubmit = verifySubmit;
        cbsMaintenanceOfCalendarEntriesService.deleteSelect = deleteSelect;
        cbsMaintenanceOfCalendarEntriesService.deleteSubmit = deleteSubmit;
        cbsMaintenanceOfCalendarEntriesService.inquirySelect =inquirySelect;
        cbsMaintenanceOfCalendarEntriesService.getRemarksList = getRemarksList;
        function addSubmit(requestData) {
            return $http({
                method: "POST",
                url: 'gbmultra/cbs/integration/master/addDetails',
                data: requestData
            });
        }

        function modifySelect(requestData) {
            return $http({
                method: "POST",
                url: 'gbmultra/cbs/integration/master/getModifyDetails',
                data: requestData
            });
        }
         function inquirySelect(requestData) {
                    return $http({
                        method: "POST",
                        url: 'gbmultra/cbs/integration/master/inquiry',
                        data: requestData
                    });
                }

        function verifySelect(requestData) {
            return $http({
                method: "POST",
                url: 'gbmultra/cbs/integration/master/getVerifyDetails',
                data: requestData
            });
        }

        function modifySubmit(requestData) {
            return $http({
                method: "POST",
                url: 'gbmultra/cbs/integration/master/modify',
                data: requestData
            });
        }
 function deleteSelect(requestData) {
            return $http({
                method: "POST",
                url: 'gbmultra/cbs/integration/master/deleteDetails',
                data: requestData
            });
        }

        function deleteSubmit(requestData) {
            return $http({
                method: "POST",
                url: 'gbmultra/cbs/integration/master/modify',
                data: requestData
            });
        }
        function verifySubmit(requestData) {
            return $http({
                method: "POST",
                url: 'gbmultra/cbs/integration/master/verifyDetails',
                data: requestData
            });
        }

        function add(data) {
            var parameter = JSON.stringify(data);

            return $http({
                method: "post",
                url: 'gbmultra/cbs/integration/master/calendarentries/create',
                params: {},
                data: parameter,
                headers: {
                    'Content-type': 'application/json;charset=utf-8'
                }
            });
        }

        function modify(data) {
            var parameter = JSON.stringify(data);
            return $http({
                method: "post",
                url: 'gbmultra/cbs/integration/master/calendarentriesupdate',
                params: {},
                data: parameter,
                headers: {
                    'Content-type': 'application/json;charset=utf-8'
                }
            });
        }

        function verify(data) {
            var parameter = JSON.stringify(data);
            return $http({
                method: "post",
                url: 'gbmultra/cbs/integration/master/calendarentries/verify',
                data: parameter,
                headers: {
                    'Content-type': 'application/json;charset=utf-8'
                }
            });
        }

        function deletemaintenanceOfCalendarEntries(data) {
            var parameter = JSON.stringify(data);
            return $http({
                method: "post",
                url: 'gbmultra/cbs/integration/master/calendarentries/delete',
                data: parameter,
                headers: {
                    'Content-type': 'application/json;charset=utf-8'
                }
            });
        }

        function getrecord(id) {
            return $http({
                method: "post",
                url: 'gbmultra/cbs/integration/master/calendarentries',
                data: {id:id}
            });
        }

        function getverifiedrecords() {
            return $http({
                method: "post",
                url: 'gbmultra/cbs/integration/master/calendarentries',
                data: {}
            });
        }

        function getunverifiedrecords() {
            return $http({
                method: "post",
                url: 'gbmultra/cbs/integration/master/calendarentries/unverifiedrecords',
                data: {}
            });
        }

        function getlist() {
            return $http({
                method: "post",
                url: 'gbmultra/cbs/integration/master/calendarentries/getallrecords',
                data: {}
            });
        }


        	function getRemarksList(requestData){
                                      return $http({
                                           method:'POST',
                                           url:'rbiBond/getRemarksList',
                                           data:requestData
                                      });
                                   }

    }

})();