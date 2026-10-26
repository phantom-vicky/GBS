gbmUltraApp.service('schemeCodesService', function ($http) {
	// Return public API.
    return ({
    	addschemeCodes: addschemeCodes
    });

    function addschemeCodes(data) {
        var parameter = JSON.stringify(data);

        return $http({
            method: "post",
            url: 'gbmultra/cbs/integration/master/schemecodes',
            params: {}, data: parameter
        });

        //return (request.then(handleSuccess, handleError));
    }
});