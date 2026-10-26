(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('principalScrollreportAppendixXIIService', principalScrollreportAppendixXIIService);

    principalScrollreportAppendixXIIService.$inject = ['$http'];

    function principalScrollreportAppendixXIIService($http) {

    	var principalScrollreportAppendixXIIService = this;

 principalScrollreportAppendixXIIService.getDetails = getDetails;

        function getDetails(requestData) {
            return $http({
                method: 'POST',
                url: 'gbmultra/rbibond/scrolls/PrincipleScrollreportAppendixXII',
                data: requestData
            })
        }


    }

})();