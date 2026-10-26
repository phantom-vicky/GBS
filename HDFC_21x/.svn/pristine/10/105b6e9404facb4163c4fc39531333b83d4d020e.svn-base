(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('commonFlagService', commonFlagService);

    commonFlagService.$inject = ['$http'];

    function commonFlagService($http) {

    	var commonFlagService = this;

    	commonFlagService.getRoles = getRoles;
    	commonFlagService.getAllRoles = getAllRoles;
    	commonFlagService.getInquiryRoles = getInquiryRoles;
    	commonFlagService.getModifiedRoles = getModifiedRoles;
    	commonFlagService.submitModifyForm = submitModifyForm;
    	commonFlagService.getUnVerifiedRoles = getUnVerifiedRoles;
    	commonFlagService.verifySubmit = verifySubmit;
        commonFlagService.deleteSubmit = deleteSubmit;
        commonFlagService.getRemarksList =  getRemarksList;
        function getAllRoles(requestData){
        	return $http({
                method: "POST",
                url: "gbmultra/gu/commonFlag/savedetails",
                data: requestData
            });
        }

        function getInquiryRoles(requestData){
        	return $http({
                method: "POST",
                url: "gbmultra/gu/commonFlag/inquiry",
                data: requestData
            });
        }

        function submitModifyForm(requestData){
        	return $http({
                method: "POST",
                url: "gbmultra/gu/commonFlag/modify",
                data: requestData
            });
        }

        function getRoles(requestData){
        	return $http({
                method: "POST",
                url: "gbmultra/gu/commonFlag/inquiry",
                data: requestData
            });
        }

        function getUnVerifiedRoles(requestData){
        	return $http({
                method: "POST",
                url: "gbmultra/gu/commonFlag/verifydetails",
                data: requestData
            });
        }

        function getModifiedRoles(requestData){
        	return $http({
                method: "POST",
                url: "gbmultra/gu/commonFlag/modifydetails",
                data: requestData
            });
        }

        function verifySubmit(requestData){
        	return $http({
                method: "POST",
                url: "gbmultra/gu/commonFlag/verify",
                data: requestData
            });
        }
                function deleteSubmit(requestData){
                	return $http({
                        method: "POST",
                        url: "gbmultra/gu/commonFlag/delete",
                        data: requestData
                    });
                }

                function getRemarksList(requestData) {
                      return $http({
                        method: "POST",
                        url: "rbiBond/getRemarksList",
                        data: requestData,
                      });
                    }


    }

})();