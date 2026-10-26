(function() {

	'use strict';

	angular.module('gbmUltraApp').service(
			'cashInFileUploadAndNetBankingFileUploadService',
			cashInFileUploadAndNetBankingFileUploadService);

	cashInFileUploadAndNetBankingFileUploadService.$inject = [ '$http' ];

	function cashInFileUploadAndNetBankingFileUploadService($http) {
		var cashInFileUploadAndNetBankingFileUploadService = this;
		cashInFileUploadAndNetBankingFileUploadService.getRemarksList = getRemarksList;
		cashInFileUploadAndNetBankingFileUploadService.submitAddForm = submitAddForm;
		cashInFileUploadAndNetBankingFileUploadService.deleteSubmit = deleteSubmit;
		cashInFileUploadAndNetBankingFileUploadService.submitVerifyForm = submitVerifyForm;
		cashInFileUploadAndNetBankingFileUploadService.verifySelect = verifySelect;
		cashInFileUploadAndNetBankingFileUploadService.getRbiBondModuleNameDetails = getRbiBondModuleNameDetails;
		cashInFileUploadAndNetBankingFileUploadService.getMetaData = getMetaData;
		cashInFileUploadAndNetBankingFileUploadService.inquiry = inquiry;
    function getMetaData(requestData)
    {
       return	$http({
           method : "POST",
           url : 'rbiBond/getReferenceData',
           data :requestData
       });
    }


 function getRemarksList(requestData){
                         return $http({
                              method:'POST',
                              url:'rbiBond/getRemarksList',
                              data:requestData
                         })
            }

		        function getRbiBondModuleNameDetails(requestData) {
                    return $http({
                        method: 'POST',
                        url: 'gbmultra/rbiBond/blaCumSub/getModuleDetails',
                        data :requestData
                    })
              }

		        function submitVerifyForm(requestData) {
                    return $http({
                        method: 'POST',
                        url: 'gbmultra/rbiBond/cashAndNetBanking/fileUpload/verify',
                        data :requestData
                    })
              }
               function inquiry(requestData) {
                                  return $http({
                                      method: 'POST',
                                      url: 'gbmultra/rbiBond/cashAndNetBanking/fileUpload/inquiry',
                                      data :requestData
                                  })
                            }
               function deleteSubmit(requestData) {
                                  return $http({
                                      method: 'POST',
                                      url: 'gbmultra/rbiBond/cashAndNetBanking/fileUpload/delete',
                                      data :requestData
                                  })
                            }
              function verifySelect(requestData) {
                                  return $http({
                                      method: 'POST',
                                      url: 'gbmultra/rbiBond/cashAndNetBanking/fileUpload/verifySelect',
                                      data :requestData
                                  })
                            }
        //Add submit
        function submitAddForm(file1,file2,file3,file4,file5,file6,file7,file8,file9,requestData) {
    	var fd = new FormData();
        fd.append('file', file1);
        fd.append('file', file2);
        fd.append('file', file3);
        fd.append('file', file4);
        fd.append('file', file5);
        fd.append('file', file6);
        fd.append('file', file7);
        fd.append('file', file8);
        fd.append('file', file9);
        fd.append('cashFileUploadAndNetBankingFileUploadRequestDTO', new Blob([JSON.stringify(requestData)], {
        type: "application/json"
        }));
        return $http.post('gbmultra/rbiBond/cashAndNetBanking/fileUpload/add', fd, {
            transformRequest: angular.identity,
    	   	transformResponse: angular.identity,
    	   	headers: {'Content-Type': undefined,
    	   		'Custom-Content-Type': 'upload'}
        });
        }

	}

})();