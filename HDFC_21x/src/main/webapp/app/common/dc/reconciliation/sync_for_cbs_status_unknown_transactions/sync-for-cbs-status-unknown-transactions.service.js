(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('syncForStatusUnknownService', syncForStatusUnknownService);

    syncForStatusUnknownService.$inject = ['$http'];

    function syncForStatusUnknownService($http) {

    	var syncForStatusUnknownService = this;

    	syncForStatusUnknownService.getTransactionDetails = getTransactionDetails;
    	syncForStatusUnknownService.syncWithTransaction = syncWithTransaction;
    	syncForStatusUnknownService.getGbmTranIdModal = getGbmTranIdModal;

    	function getTransactionDetails(tranDate) {
    	    return	$http({
                method: "post",
                url: 'gbmultra/common/synchronization/getCbsStatusUnknownTransactions',
                data : {tranDate : tranDate}
            });
    	}

    	function syncWithTransaction(requestData) {
            return	$http({
                method: "post",
                url: 'gbmultra/common/synchronization/statusUnknownTransactionsupdate',
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