(function() {

        'use strict';

        angular.module('gbmUltraApp').service('bondSubAccClosureBeforeMaturityService',
         bondSubAccClosureBeforeMaturityService);

         bondSubAccClosureBeforeMaturityService.$inject = [ '$http' ];
   function bondSubAccClosureBeforeMaturityService($http) {

         var bondSubAccClosureBeforeMaturityService = this;
         bondSubAccClosureBeforeMaturityService.addDetails = addDetails;
         bondSubAccClosureBeforeMaturityService.verifyDetails = verifyDetails;
         bondSubAccClosureBeforeMaturityService.inquiry = inquiry;
         bondSubAccClosureBeforeMaturityService.verifySubmit = verifySubmit;
         bondSubAccClosureBeforeMaturityService.downloadFile = downloadFile;
         bondSubAccClosureBeforeMaturityService.uploadTextFile = uploadTextFile;
         bondSubAccClosureBeforeMaturityService.inquiryFromPaymentScreen = inquiryFromPaymentScreen;
         bondSubAccClosureBeforeMaturityService.getRemarksList = getRemarksList;

            function addDetails(requestData){
                return $http({
                  method: 'POST',
                  url: 'apis/bond-sub-acc-before-closure-maturity/add-details',
                  data :requestData
                });
            }

            function verifyDetails(requestData){
                return $http({
                  method: 'POST',
                  url: 'apis/bond-sub-acc-before-closure-maturity/verify-details',
                  data :requestData
                });
            }

            function getRemarksList(requestData){
               return $http({
                    method:'POST',
                    url:'rbiBond/getRemarksList',
                    data:requestData
               });
            }

            function inquiry(requestData){
                return $http({
                    method: 'POST',
                    url: 'apis/bond-sub-acc-before-closure-maturity/inquiry',
                    data :requestData
                });
            }

            function inquiryFromPaymentScreen(){
               let requestData = {
                "schemeCode": "0012",
                 "halfYear": "JUL",
                 "year": "2024"
               };

                return $http({
                    method: 'POST',
                    url: 'apis/bond-actual-payments/inquiry',
                    data :requestData
                });
            }

            function downloadFile(requestData){
                  return $http({
                      method: 'POST',
                      url: 'apis/bond-sub-acc-before-closure-maturity/download-file',
                       data :requestData
                  });
            }

            function uploadTextFile(formData) {
                   return $http.post('apis/bond-sub-acc-before-closure-maturity/upload-text-file', formData, {
                           transformRequest: angular.identity,
                           headers: {'Content-Type': undefined,'Custom-Content-Type': 'upload'}
                   });
            }

            function verifySubmit(requestData) {
                    return $http({
                        method: 'POST',
                        url: 'apis/bond-sub-acc-before-closure-maturity/verify-details',
                        data :requestData
                    });
            }


   }
})();