gbmUltraApp.service('branchesOfTheBankService', function ($http) {
	// Return public API.
    return ({
    	addbranchesOfTheBank: addbranchesOfTheBank
    });

    function addbranchesOfTheBank(data) {
        var parameter = JSON.stringify(data);

        return $http({
            method: "post",
            url: 'deletedTransactionInFinacleReconciliation/add',
            params: {}, data: parameter
        });

        //return (request.then(handleSuccess, handleError));
    }
});