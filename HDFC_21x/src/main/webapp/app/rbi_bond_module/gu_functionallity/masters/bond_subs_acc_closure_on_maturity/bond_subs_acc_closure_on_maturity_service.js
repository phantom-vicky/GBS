(function() {

	'use strict';

	angular.module('gbmUltraApp').service(
			'rbiBondSubsAccClsOnMtyService',
			rbiBondSubsAccClsOnMtyService);

	rbiBondSubsAccClsOnMtyService.$inject = [ '$http' ];

	function rbiBondSubsAccClsOnMtyService($http) {
		var rbiBondSubsAccClsOnMtyService = this;
		rbiBondSubsAccClsOnMtyService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
		rbiBondSubsAccClsOnMtyService.getReferenceData = getReferenceData;
		rbiBondSubsAccClsOnMtyService.getValidationAdd = getValidationAdd;
		rbiBondSubsAccClsOnMtyService.addSubmit = addSubmit;
		rbiBondSubsAccClsOnMtyService.downloadFile = downloadFile;
		rbiBondSubsAccClsOnMtyService.uploadTextFile = uploadTextFile;
		rbiBondSubsAccClsOnMtyService.inquiry = inquiry;
		rbiBondSubsAccClsOnMtyService.verifySelect = verifySelect;
		rbiBondSubsAccClsOnMtyService.deleteSubmit = deleteSubmit;
		rbiBondSubsAccClsOnMtyService.verifySubmit = verifySubmit;
		rbiBondSubsAccClsOnMtyService.getRemarksList = getRemarksList;


                function getRbiBondModuleNameDetails(requestData){
                    return $http({
                        method: 'POST',
                        url: 'rbiBond/getModuleDetails',
                        data :requestData
                    })
                }

		        function getReferenceData(requestData)
                {
                   return	$http({
                       method : "POST",
                       url : 'rbiBond/getReferenceData',
                       data :requestData
                   });
                }
                function getValidationAdd(requestData) {
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/subsAccClsOnMry/addValidation',
                       data :requestData
                   });
                }
                function getRemarksList(requestData) {
                   return $http({
                        method:'POST',
                        url:'rbiBond/getRemarksList',
                        data:requestData
                   });
                }

                function addSubmit(requestData){
                       return	$http({
                           method : "POST",
                           url : 'gbmultra/rbiBond/subsAccClsOnMry/add',
                           data :requestData
                       });
                }
               function inquiry(requestData){
                   return	$http({
                       method : "POST",
                       url : 'gbmultra/rbiBond/subsAccClsOnMry/inquiry',
                       data :requestData
                   });
               }

          function verifySelect(requestData){
               return	$http({
                   method : "POST",
                   url : 'gbmultra/rbiBond/subsAccClsOnMry/verifySelect',
                   data :requestData
               });
          }

          function downloadFile(requestData){
                return $http({
                    method: 'POST',
                    url: 'gbmultra/rbiBond/subsAccClsOnMry/download-file',
                     data :requestData
                });
          }

          function uploadTextFile(formData) {
                 return $http.post('gbmultra/rbiBond/subsAccClsOnMry/upload-text-file', formData, {
                         transformRequest: angular.identity,
                         headers: {'Content-Type': undefined,'Custom-Content-Type': 'upload'}
                 });
          }

          function deleteSubmit(requestData){
               return	$http({
                   method : "POST",
                   url : 'gbmultra/rbiBond/subsAccClsOnMry/delete',
                   data :requestData
               });
            }

             function verifySubmit(requestData){
               return	$http({
                   method : "POST",
                   url : 'gbmultra/rbiBond/subsAccClsOnMry/verify',
                   data :requestData
               });
            }

		}

    })();