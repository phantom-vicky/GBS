(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('syncForPartiallyVerifiedService', syncForPartiallyVerifiedService);

    syncForPartiallyVerifiedService.$inject = ['$http'];

    function syncForPartiallyVerifiedService($http) {

    	var syncForPartiallyVerifiedService = this;

    	syncForPartiallyVerifiedService.getTransactionDetails = getTransactionDetails;
    	syncForPartiallyVerifiedService.syncWithTransaction = syncWithTransaction;
    	syncForPartiallyVerifiedService.getGbmTranIdModal = getGbmTranIdModal;

    	function getTransactionDetails(tranDate) {
    	    return	$http({
                method: "post",
                url: 'gbmultra/common/synchronization/getCbsStatusUnknownTransactions',
                headers: {
                    'Content-type': 'application/json;charset=utf-8'
                },
                data : {tranDate : tranDate}
            });
    	}

    	function syncWithTransaction(requestData) {
            return	$http({
                method: "post",
                url: 'gbmultra/common/synchronization/statusUnknownTransactionsupdate',
                headers: {
                    'Content-type': 'application/json;charset=utf-8'
                },
                data: requestData
            });
    	}

    	function getGbmTranIdModal(requestData) {
            return	$http({
                method: "post",
                url: 'gbmultra/common/synchronization/trandetails',
                data: requestData
            });
    	}

    }

})();