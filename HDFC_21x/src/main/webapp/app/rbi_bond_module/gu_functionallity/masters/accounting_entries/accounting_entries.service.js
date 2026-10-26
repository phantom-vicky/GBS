(function () {
    "use strict";
    angular
        .module("gbmUltraApp")
        .service("accountingEntriesService", accountingEntriesService);

    accountingEntriesService.$inject = ["$http"];

    function accountingEntriesService($http) {
        var accountingEntriesService = this;

        accountingEntriesService.getRemarksList = getRemarksList;
        accountingEntriesService.getModuleName = getModuleName;
        accountingEntriesService.deleteSubmit = deleteSubmit;

        accountingEntriesService.getInquiryVersionNo = getInquiryVersionNo;
        accountingEntriesService.getModifyModuleName = getModifyModuleName;
        accountingEntriesService.getVerifyModuleName = getVerifyModuleName;

        accountingEntriesService.addAccountEntriesValues = addAccountEntriesValues;
        accountingEntriesService.modifySubmit = modifySubmit;
        accountingEntriesService.verifySubmit = verifySubmit;

        accountingEntriesService.inquiryDetails = inquiryDetails;
        accountingEntriesService.getVerifyDetails = getVerifyDetails;
        accountingEntriesService.getModifyDetails = getModifyDetails;

        function getModuleName(requestData) {
            return $http({
                method: "POST",
                url: "gbsultra/rbiBond/accountEntries/getModuleName",
                data: requestData,
            });
        }

        function getInquiryVersionNo(requestData) {
            return $http({
                method: "POST",
                url: "gbsultra/rbiBond/accountEntries/getVersionNo",
                data: requestData,
            });
        }

        function getModifyModuleName(requestData) {
            return $http({
                method: "POST",
                url: "gbsultra/rbiBond/accountEntries/getModifyModuleName",
                data: requestData,
            });
        }

        function getVerifyModuleName(requestData) {
            return $http({
                method: "POST",
                url: "gbsultra/rbiBond/accountEntries/getVerifyModuleName",
                data: requestData,
            });
        }

        function addAccountEntriesValues(requestData) {
            return $http({
                method: "POST",
                url: "gbsultra/rbiBond/accountEntries/add",
                data: requestData,
            });
        }

        function modifySubmit(requestData) {
            return $http({
                method: "POST",
                url: "gbsultra/rbiBond/accountEntries/modify",
                data: requestData,
            });
        }

        function inquiryDetails(requestData) {
            return $http({
                method: "POST",
                url: "gbsultra/rbiBond/accountEntries/getVersionNo",
                data: requestData,
            });
        }

        function getVerifyDetails(requestData) {
            return $http({
                method: "POST",
                url: "gbsultra/rbiBond/accountEntries/getVerifyDetails",
                data: requestData,
            });
        }

        function getModifyDetails(requestData) {
            return $http({
                method: "POST",
                url: "gbsultra/rbiBond/accountEntries/getModifyDetails",
                data: requestData,
            });
        }

        function verifySubmit(requestData) {
            return $http({
                method: "POST",
                url: "gbsultra/rbiBond/accountEntries/verify",
                data: requestData,
            });
        }

        function deleteSubmit(requestData) {
            return $http({
                method: "POST",
                url: "gbsultra/rbiBond/accountEntries/delete",
                data: requestData,
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