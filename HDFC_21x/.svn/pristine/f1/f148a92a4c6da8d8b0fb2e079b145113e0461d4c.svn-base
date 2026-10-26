(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('syncForVerifiedService', syncForVerifiedService);

    syncForVerifiedService.$inject = ['$http'];

    function syncForVerifiedService($http) {

    	var syncForVerifiedService = this;

    	syncForVerifiedService.getTransactionDetails = getTransactionDetails;
    	syncForVerifiedService.syncWithTransaction = syncWithTransaction;
    	syncForVerifiedService.getGbmTranIdModal = getGbmTranIdModal;

    	function getTransactionDetails(tranDate) {
    	    return	$http({
                method: "post",
                url: 'gbmultra/common/synchronization/getSyncDetails',
                data : {tranDate : tranDate}
            });
    	}

    	function syncWithTransaction(requestData) {
            return	$http({
                method: "post",
                url: 'gbmultra/common/synchronization/syncupdate',
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