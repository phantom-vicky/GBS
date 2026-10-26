(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('reportService', reportService);

    reportService.$inject = ['$http'];

    function reportService($http) {

        var reportService = this;
        reportService.getUnSuncessfulReport 						= getUnSuncessfulReport;
        reportService.getSolDetails         						= getSolDetails;
        reportService.getUserLoggedInIpAddress 						= getUserLoggedInIpAddress;
        reportService.getListOfUserRelatedActivityCarrierOnDay    	= getListOfUserRelatedActivityCarrierOnDay;
        reportService.getUserSignOnSIgnOff 							= getUserSignOnSIgnOff;
        reportService.getDormantUserIdReport 						= getDormantUserIdReport;
        reportService.getDetails = getDetails;
        reportService.activateUser = activateUser;


        function getUnSuncessfulReport(fromDate,toDate) {
            return $http({
                method: "POST",
                url: "gbmultra/user/report/login/unsuccessful",
                data : {from : fromDate, to : toDate}
            });
        }
        
        /*function getUnSuncessfulReportExport(fromDate,toDate) {
            return $http({
                method: "GET",
                url: "gbmultra/user/report/login/unsuccessful/export?from="+fromDate+"&to="+toDate
            });
        }*/

        function getSolDetails(solid,toDate) {
            return $http({
                method: "post",
                url: "gbmultra/cbs/integration/soldetails",
                data : {solId : solid}
            });
        }
        
        function getUserLoggedInIpAddress(solid,fromDate) {
            return $http({
                method: "post",
                url: "gbmultra/user/report/login/multipledevices",
                data : {solId : solid, from : fromDate}
            });
        }
        
        function getListOfUserRelatedActivityCarrierOnDay(fromDate) {
            return $http({
                method: "post",
                url: "gbmultra/user/report/activities/admin",
                data : {from : fromDate}
            });
        }
        
        function getDormantUserIdReport(fromDate,toDate) {
            return $http({
                method: "post",
                url: "gbmultra/user/report/dormant",
                data : {from : fromDate, to : toDate}
            });
        }
        
        function getUserSignOnSIgnOff(postdata) {
        	
            return $http({
                method: 'POST',
                url: "gbmultra/user/report/loginReport",
                headers: {
                    'Content-Type': 'application/json'
                },
                data: postdata
            })
        }


         function getDetails(requestData) {
                return $http({
                    method: "post",
                    url: "gbmultra/user/report/userReport",
                    data : requestData
                });
            }


         function activateUser(requestData) {
                return $http({
                    method: "post",
                    url: "gbmultra/user/report/activateUserReport",
                    data : requestData
                });
            }

}
})();