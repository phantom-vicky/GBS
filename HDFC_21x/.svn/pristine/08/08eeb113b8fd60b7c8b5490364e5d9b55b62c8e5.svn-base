(function() {

	'use strict';

	angular.module('gbmUltraApp').service(
			'bondActualInterestPaymentService',
			bondActualInterestPaymentService);

	bondActualInterestPaymentService.$inject = [ '$http' ];
	function bondActualInterestPaymentService($http) {
    		var bondActualInterestPaymentService = this;
            bondActualInterestPaymentService.reject = reject;
            bondActualInterestPaymentService.downloadFile = downloadFile;
            bondActualInterestPaymentService.addDetails = addDetails;
            bondActualInterestPaymentService.addSubmit = addSubmit;
            bondActualInterestPaymentService.verifySelect = verifySelect;
            bondActualInterestPaymentService.verifySubmit = verifySubmit;
            bondActualInterestPaymentService.inquiry = inquiry;
            bondActualInterestPaymentService.downloadExcel = downloadExcel;
            bondActualInterestPaymentService.uploadTextFile = uploadTextFile;
            bondActualInterestPaymentService.getMergeUnMergeDownload = getMergeUnMergeDownload;

            function reject(requestData){
                return $http({
                    method: 'POST',
                    url: 'apis/bond-actual-payments/reject',
                    data :requestData
                });
            }

            function downloadFile(requestData){
              return $http({
                  method: 'POST',
                  url: 'apis/bond-actual-payments/download-file',
                   data :requestData
              });
            }
       function getMergeUnMergeDownload(requestData){
              return $http({
                  method: 'POST',
                  url: 'apis/bond-actual-payments/mergeUnMerge-download-file',
                   data :requestData
              });
            }

            function uploadTextFile(formData) {
                        return $http.post('apis/bond-actual-payments/upload-text-file', formData, {
                            transformRequest: angular.identity,
                    	   	headers: {'Content-Type': undefined,'Custom-Content-Type': 'upload'}
                        });
                    }

            function addDetails(requestData){
                  return $http({
                      method: 'POST',
                      url: 'apis/bond-actual-payments/add-details',
                      data :requestData
                  });
            }

           function addSubmit(requestData){
                 return $http({
                     method: 'POST',
                     url: 'apis/bond-actual-payments/add-submit',
                     data :requestData
                 });
           }
           function verifySelect(requestData){
              return $http({
                  method: 'POST',
                  url: 'apis/bond-actual-payments/verify-details',
                  data :requestData
              });
           }

           function verifySubmit(requestData){
             return $http({
                 method: 'POST',
                 url: 'apis/bond-actual-payments/verify-submit',
                 data :requestData
             });
           }

           function inquiry(requestData){
                return $http({
                    method: 'POST',
                    url: 'apis/bond-actual-payments/inquiry',
                    data :requestData
                });
           }

           function downloadExcel(){
               return $http({
                   method: 'GET',
                   url: 'apis/bond-actual-payments/get-Excel-File'
               });
          }

	}
})();