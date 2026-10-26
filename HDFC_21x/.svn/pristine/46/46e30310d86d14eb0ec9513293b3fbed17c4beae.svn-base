(function() {
    "use strict";

    angular.module("gbmUltraApp").service("EfilingService", EfilingService);

    EfilingService.$inject= ["$http"];

    function EfilingService($http) {
        var EfilingService= this;

        EfilingService.getEfillingDetails= function(requestData) {
            return $http({
                method  : "POST",
                url     : "gbmultra/rbibond/scrolls/getEfillingDetails",
                data    : requestData,
            });
        };


         EfilingService.getEfillingDetailsDownload= function(requestData) {
                    return $http({
                        method  : "POST",
                        url     : "gbmultra/rbibond/scrolls/getEfillingDetailsDownload",
                        data    : requestData,
                    });
                };
    };
})();