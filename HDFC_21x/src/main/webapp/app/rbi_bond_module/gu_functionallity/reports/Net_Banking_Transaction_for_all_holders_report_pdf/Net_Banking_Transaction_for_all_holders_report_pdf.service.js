(function() {

    'use strict';

    angular.module('gbmUltraApp')
        .service('netBankingTransactionforallholdersreportpdfService', netBankingTransactionforallholdersreportpdfService);

    netBankingTransactionforallholdersreportpdfService.$inject = ['$http'];

    function netBankingTransactionforallholdersreportpdfService($http) {

    	var netBankingTransactionforallholdersreportpdfService = this;

 netBankingTransactionforallholdersreportpdfService.getDetails = getDetails;

// function getRbiBondModuleNameDetails(requestData){
//             return $http({
//                 method: 'POST',
//             //    url: 'rbiBond/getModuleDetails',
//                 data :requestData
//             })
//       }


        function getDetails(requestData) {
            return $http({
                method: 'POST',
               url: 'gbmultra/rbibond/reports/getDetailsOfPdf',
                data: requestData
            })
        }




    }

})();