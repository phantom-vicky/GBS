gbmUltraApp.service('branchesOfOtherBankService', function ($http) {
	// Return public API.
    return ({
    	addbranchesOfOtherBank: addbranchesOfOtherBank
    });

    function addbranchesOfOtherBank(data) {
        var parameter = JSON.stringify(data);

        return $http({
            method: "post",
            url: 'gbmultra/cbs/integration/master/otherbankbranches',
            params: {}, data: parameter
        });

        //return (request.then(handleSuccess, handleError));
    }
});