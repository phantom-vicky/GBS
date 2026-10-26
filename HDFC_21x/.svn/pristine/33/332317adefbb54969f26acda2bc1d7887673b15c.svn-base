(function() {

    'use strict';

    angular
        .module('gbmUltraApp')
        .service('gbmReconciliationService', gbmReconciliationService);

    gbmReconciliationService.$inject = ['$http'];

    function gbmReconciliationService($http) {

    	var gbmReconciliationService = this;

    	gbmReconciliationService.getTransactionDetails = getTransactionDetails;
    	gbmReconciliationService.getReconciliationDetails = getReconciliationDetails;
    	gbmReconciliationService.reconcileWithTransaction = reconcileWithTransaction;
    	gbmReconciliationService.getGbmTranIdModal = getGbmTranIdModal;

    	function getTransactionDetails(tranDate) {
    	    return	$http({
                method: "post",
                url: 'gbmultra/common/synchronization/getReconciliationTransactions',
                headers: {
                    'Content-type': 'application/json;charset=utf-8'
                },
                data : {tranDate : tranDate}
            });
    	}

    	function getReconciliationDetails(gbmTranId, cbsTranId, tranDate) {
            return	$http({
                method: "post",
                url: 'gbmultra/common/synchronization/reconcileTransactions',
                headers: {
                    'Content-type': 'application/json;charset=utf-8'
                },
                data : {tranDate : tranDate, gbmTranId : gbmTranId, cbsTranId : cbsTranId}
            });
    	}

    	function reconcileWithTransaction(requestData) {
            return	$http({
                method: "post",
                url: 'gbmultra/common/synchronization/reconcile',
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